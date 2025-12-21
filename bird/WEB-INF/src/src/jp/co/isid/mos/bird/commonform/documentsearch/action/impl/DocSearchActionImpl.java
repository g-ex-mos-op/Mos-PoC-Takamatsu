/*
 * 作成日: 2006/01/17
 *
 */
package jp.co.isid.mos.bird.commonform.documentsearch.action.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetSubCategoryLogic;
import jp.co.isid.mos.bird.commonform.documentsearch.action.DocSearchAction;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchConditionDto;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchDto;
import jp.co.isid.mos.bird.commonform.documentsearch.logic.SearchDocLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * @author m.onodera
 *
 */
public class DocSearchActionImpl implements DocSearchAction {

	// アクションID定義
	public static final String initialize_ACTION_ID      = "BCO001A01";
	public static final String search_ACTION_ID          = "BCO001A02";
	public static final String select_ACTION_ID          = "BCO001A03";
	public static final String cancel_ACTION_ID          = "BCO001A04";
    public static final String loadCateList_ACTION_ID    = "BCO003A05";
    public static final String loadSubCateList_ACTION_ID = "BCO003A06";
    public static final String changePage_ACTION_ID      = "BCO001A07";
    /* BIRDログインユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /* 関連文書情報Dto */
    private DocumentSearchDto documentSearchDto;
    /* 関連文書検索用Dto */
    private DocumentSearchConditionDto documentSearchConditionDto;
    /* SearchDocLogic */
    private SearchDocLogic searchDocLogic;
    /* ロジック【カテゴリ一覧の取得】 */
    private GetCategoryLogic getCategoryLogic;
    /* ロジック【サブカテゴリ一覧の取得】*/
    private GetSubCategoryLogic getSubCategoryLogic;
    /* 選択ページ番号 */
    private int selectPageNumber;
    
	/**
	 * 初期処理
	 * 
	 * @return 画面遷移情報
	 */
	public String initialize() {

		// 初期処理判定
        if (documentSearchDto.isInitFlg()) {
		    // 初期化
		    documentSearchConditionDto.clear();

		    //タイププルダウン作成
		    List typeList = new ArrayList();
		    typeList.add(new SelectItem(InfoShu.TUTAU, InfoShu.TUTATU_NAME));
//		    typeList.add(new SelectItem(BUNSHO_INFOSHU,"文書"));
//		    typeList.add(new SelectItem(FORM_INFOSHU,"フォーム"));
//---2007/07/10 update start 画面名変更に伴いプルダウンの内容も変更            
//            typeList.add(new SelectItem(BUNSHO_INFOSHU,"規定集・業務文書・手順書"));
//            typeList.add(new SelectItem(FORM_INFOSHU,"申請書・依頼書・フォーム"));
            typeList.add(new SelectItem(InfoShu.BUNSHO,"情報共有（"+InfoShu.BUNSHO_NAME+"）"));
            typeList.add(new SelectItem(InfoShu.FORM,"情報共有（"+InfoShu.FORM_NAME+"）"));
//---2007/07/10 update end
		    documentSearchConditionDto.setInfoShuList(typeList);

		    //初期表示用情報種別を設定
		    documentSearchConditionDto.setInfoShu(InfoShu.TUTAU);
		    //初期表示用カテゴリの取得・設定
            documentSearchConditionDto.setCateList(getGetCategoryLogic().getCategory(
                    documentSearchConditionDto.getInfoShu()));
		    
            // 対象年月リスト作成
            documentSearchConditionDto.setTargetDateList(
            		createTargetDateList(getBirdDateInfo().getSysDate(), 36));
		    
            // 初期処理フラグOFF
		    documentSearchDto.setInitFlg(false);
		}

		return null;
	}
    /**
     * カテゴリリスト読込処理
     * @return 画面遷移情報
     */
    public String loadCateList() {
        try{
            //カテゴリリスト、カテゴリID、サブカテゴリリスト、サブカテゴリIDの初期化
            getDocumentSearchConditionDto().setCateList(null);
            getDocumentSearchConditionDto().setCateId(null);
            getDocumentSearchConditionDto().setSubCateList(null);
            getDocumentSearchConditionDto().setSubCateId(null);
            
            documentSearchConditionDto.setCateList(getGetCategoryLogic().getCategory(
                    documentSearchConditionDto.getInfoShu()));
        }catch(Exception ex){
            if(ex.equals("NotExistException")){
              //なにもしない。  
            }
        }
        return null;
    }
    /**
     * サブカテゴリリスト読込処理
     * @return 画面遷移情報
     */
    public String loadSubCateList() {
	    try{
            //サブカテゴリリスト、サブカテゴリIDの初期化
            getDocumentSearchConditionDto().setSubCateList(null);
            getDocumentSearchConditionDto().setSubCateId(null);

	        documentSearchConditionDto.setSubCateList(getGetSubCategoryLogic().getSubCategory(
	                documentSearchConditionDto.getInfoShu(), documentSearchConditionDto.getCateId()));
	    }catch(Exception ex){
	        if(ex.equals("NotExistException")){
              //なにもしない。  
	        }
	    }
        return null;
    }

	
	/**
	 * 決定
	 * 
	 * @return 画面遷移情報
	 */
	public String select() {
		// 表示ページの関連文書情報一時保存
		getDocumentSearchConditionDto().saveState();
	    // 関連文書情報読込
		loadCheckState();
        // 関連文書選択を必須とする
        List list = documentSearchConditionDto.getCheckList();
        if (list == null || list.size() <= 0) {
            throw new NotNullException("関連文書選択");
        }
        //選択関連文書を保存
		documentSearchDto.setDocumentList(documentSearchConditionDto.getCheckList());
        // アクションフラグ設定
        documentSearchDto.setActionFlg(true);
        // 呼出元画面へ遷移
        return documentSearchDto.getNavigationCase();
	}

	/**
	 * 取消
	 * 
	 * @return 画面遷移情報
	 */
	public String cancel() {
        // 検索情報初期化
        documentSearchConditionDto.clear();
        documentSearchDto.setDocumentList(null);
        // アクションフラグfalse
        documentSearchDto.setActionFlg(false);
        // 呼出元画面へ遷移
        return documentSearchDto.getNavigationCase();
	}

    /**
     * 関連文書検索処理
     * @return 画面遷移情報
	 */
	public String search() {
        // タイプ選択必須チェック
        if (documentSearchConditionDto.getInfoShu() == null
                || documentSearchConditionDto.getInfoShu().equals("")) {
            throw new NotNullException("タイプ", "", true);
        }
    	//２．検索取得情報クリア処理
    	getDocumentSearchConditionDto().clearSearchData();
        //３．条件フィールドの値をSQL検索用変数にセットする。
        getDocumentSearchConditionDto().settingSearchJokenData();
        
        //４．LOGIC【関連文書検索用ロジック】検索・検索結果取得処理を実行する。
        List listAll = getSearchDocLogic().execute(documentSearchConditionDto,getBirdUserInfo(), getBirdDateInfo());
        //５．処理４の[[検索結果]]をDTO【関連文書検索処理】全件検索結果へ設定する。
        getDocumentSearchConditionDto().setListAllDocument(listAll);
        //６．0件チェックを行い、0件の場合は例外を発生する。
        if (listAll.size() <= 0) {
        	
            throw new NoResultException("");
        }
		//７．指定ページ番号の表示用関連文書リスト設定処理を行う。
        getDocumentSearchConditionDto().setDispDocumentList(
        		getDocumentSearchConditionDto().getCurrentPageNumber());
        return null;
    }

	
	
	/**
	 * ページ切替
	 * 
	 * @return 画面遷移情報
	 */
	public String changePage() {
		//１．指定ページ番号の表示用関連文書リスト設定処理を行う。
		getDocumentSearchConditionDto().setDispDocumentList(getSelectPageNumber());

		return null;
	}

	/**
	 * 関連文書チェック状態読込
	 * チェックされている情報の保存、
	 * チェックされていない情報の削除
	 * @param roleSearchRoleListDto 関連文書チェック状態読込
	 */
	private void loadCheckState() {
        HashMap map = documentSearchConditionDto.getSaveMap();
        if(map != null){
	        Collection keySet = map.keySet();
	        for (Iterator iter = keySet.iterator(); iter.hasNext();) {
	            List list = (List)map.get(iter.next());
	            if(list != null){
		            for(int i=0; i<list.size(); i++){
		                UIDocSearch uIDoc = (UIDocSearch)list.get(i);
		                if(uIDoc.isCheckedDoc()){
		                    documentSearchConditionDto.getCheckList().add(uIDoc);
		                } else {
		    			    documentSearchConditionDto.getCheckList().remove(uIDoc);
		    			}
		            }
	            }
	        }
        }
	}
    /**
     * 対象年月リスト作成
     * 
     * @param baseDate
     *            基準日付
     * @param range
     *            月数
     * @return 対象年月リスト
     */
    private List createTargetDateList(String baseDate, int months) {

        // YYYYMMDD形式の文字列からカレンダー作成
        Calendar calendar = Calendar.getInstance();
        try {
            calendar
                    .setTime((new SimpleDateFormat("yyyyMMdd")).parse(baseDate));
        } catch (ParseException e) {
            throw new FtlSystemException("関連文書", null, e);
        }

        // YYYYMM形式で指定範囲の年月リストを作成
        List targetDateList = new ArrayList();
        DateFormat formatter = new SimpleDateFormat("yyyyMM");
        DateFormatter commonFormatter = new DateFormatter(
                DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
        for (int i = 0; i < months; i++) {
            targetDateList.add(new SelectItem(commonFormatter.format(formatter
                    .format(calendar.getTime()), false), commonFormatter
                    .format(formatter.format(calendar.getTime()), true)));
            calendar.add(Calendar.MONTH, -1);
        }

        return targetDateList;
    }
	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * @param birdUserInfo 設定する birdUserInfo。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}
	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * 関連文書情報Dto設定処理
	 * @return documentSearchDto
	 */
	public DocumentSearchDto getDocumentSearchDto() {
	    return documentSearchDto;
	}
	/**
	 * 関連文書情報Dto設定処理
	 * @param documentSearchDto
	 */
	public void setDocumentSearchDto(DocumentSearchDto documentSearchDto) {
	    this.documentSearchDto = documentSearchDto;
	}
	/**
	 * 関連文書検索用Dto設定処理
	 * @return documentSearchConditionDto
	 */
	public DocumentSearchConditionDto getDocumentSearchConditionDto() {
	    return documentSearchConditionDto;
	}
	/**
	 * 関連文書検索用Dto設定処理
	 * @param documentSearchConditionDto
	 */
	public void setDocumentSearchConditionDto(DocumentSearchConditionDto documentSearchConditionDto) {
	    this.documentSearchConditionDto = documentSearchConditionDto;
	}
	/**
	 * 関連文書検索用ロジック設定処理
	 * @return searchFunctionLogic
	 */
	public SearchDocLogic getSearchDocLogic() {
	    return searchDocLogic;
	}
	/**
	 *関連文書検索用ロジック設定処理
	 * @param searchFunctionLogic
	 */
	public void setSearchDocLogic(SearchDocLogic searchDocLogic) {
	    this.searchDocLogic = searchDocLogic;
	}
	/**
	 * カテゴリプルダウン検索用ロジック設定処理
	 * @return getCategoryLogic
	 */
	public GetCategoryLogic getGetCategoryLogic() {
	    return getCategoryLogic;
	}
	/**
	 *カテゴリプルダウン検索用ロジック設定処理
	 * @param getCategoryLogic
	 */
	public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
	    this.getCategoryLogic = getCategoryLogic;
	}
	/**
	 * サブカテゴリプルダウン検索用ロジック設定処理
	 * @return getSubCategoryLogic
	 */
	public GetSubCategoryLogic getGetSubCategoryLogic() {
	    return getSubCategoryLogic;
	}
	/**
	 *サブカテゴリプルダウン検索用ロジック設定処理
	 * @param getSubCategoryLogic
	 */
	public void setGetSubCategoryLogic(GetSubCategoryLogic getSubCategoryLogic) {
	    this.getSubCategoryLogic = getSubCategoryLogic;
	}

	/**
     * 選択ページ番号を取得します。
     * 
     * @return 選択ページ番号
     */
	public int getSelectPageNumber() {
		return selectPageNumber;
	}
	/**
     * 選択ページ番号を設定します。
     * 
     * @param selectPageNumber 選択ページ番号
     */
	public void setSelectPageNumber(int selectPageNumber) {
		this.selectPageNumber = selectPageNumber;
	}
}
