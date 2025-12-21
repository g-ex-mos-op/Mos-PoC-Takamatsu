
/*
 * 作成日: 2006/02/09
 */
package jp.co.isid.mos.bird.communication.documentdownload.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.communication.documentdownload.action.DocumentDownloadAction;
import jp.co.isid.mos.bird.communication.documentdownload.dto.DocumentDownloadDto;
import jp.co.isid.mos.bird.communication.documentdownload.entity.UIViewBunshoInfo;
import jp.co.isid.mos.bird.communication.documentdownload.entity.UIViewShozokuInfo;
import jp.co.isid.mos.bird.communication.documentdownload.logic.GetDocInfoLogic;
import jp.co.isid.mos.bird.communication.documentdownload.logic.GetDocShozokuLogic;
import jp.co.isid.mos.bird.communication.documentdownload.logic.GetUserDocCategoryLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 文書公開画面アクション
 * @author xnkusama
 */
public class DocumentDownloadActionImpl implements CommonAction, DocumentDownloadAction {

    /* アクションID */
    public static final String initialize_ACTION_ID   = "BCM003A01";
    public static final String searchByCate_ACTION_ID = "BCM003A02";
    public static final String searchByTitle_ACTION_ID= "BCM003A03";
    public static final String changeTab_ACTION_ID    = "BCM003A04";
    public static final String changePage_ACTION_ID   = "BCM003A05";
    public static final String viewDetail_ACTION_ID   = "BCM003A06";
    
    /* 検索モード */
    private static final int SEARCH_MODE_CATEGORY = 1;
    private static final int SEARCH_MODE_TITLE = 2;
    
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    // 検索条件：カテゴリID
    private String condCateId;
    // 検索条件：サブカテゴリ（カテゴリ検索時のみ）
    private String condSubCateId;
    // 選択ページ番号 
    private int selectPageNumber = 0;
    
    /*【ロジック】*/
    //カテゴリ一覧の取得
    private GetUserDocCategoryLogic getUserCategoryLogic;

    //照会文書の取得
    private GetDocInfoLogic getDocInfoLogic;
    //関連文書検索
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    /*【DTO】*/
    private DocumentDownloadDto documentDownloadDto;
    private PullDownMenuDto pullDownMenuDto;

    /* 情報種別 */
    private String infoShu;
    // 検索モードフラグ（true:検索ボタン  false:ページ切替)
    private boolean searchMode = true;
    
// add start xkhata
    private GetDocShozokuLogic getDocShozokuLogic;
// add end
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
//    	 2006/03/09 e-mosリンク対応 xkhata
    	S2Container container = SingletonS2ContainerFactory.getContainer();
    	setPullDownMenuDto( (PullDownMenuDto) container.getComponent(PullDownMenuDto.class) );
// end
        // ロジック【カテゴリ一覧の取得】
//        List listCategory = getGetCategoryLogic().getCategory(getInfoShu());
//        List listCategory = getGetUserCategoryLogic().execute(getInfoShu(), getBirdDateInfo().getSysDate(), getBirdUserInfo());
//        getDocumentDownloadDto().setListNaviCate(listCategory);
        
        // 初期処理（メニューから遷移された場合のみ）
        if (getPullDownMenuDto().isClearFlg()) {
            //ロジック【カテゴリ一覧の取得】
            List listCategory = getGetUserCategoryLogic().execute(getInfoShu(), getBirdDateInfo().getSysDate(), getBirdUserInfo());
            getDocumentDownloadDto().setListNaviCate(listCategory);

            getDocumentDownloadDto().setCurrentPageNumber(1);
            getDocumentDownloadDto().clearDto();
            getDocumentDownloadDto().clearCondDto();
            getDocumentDownloadDto().setViewCateName("");
            getPullDownMenuDto().setClearFlg(false);
        }
        
        return null;
    }
    
    /**
     * タイトルによる文書一覧検索処理
     * @return
     */
    public String searchByTitle() {
        // DTOクリア
        if (getSelectPageNumber() == 0) {
        	getDocumentDownloadDto().clearDto();
        }
        if (isSearchMode()) {
        	getDocumentDownloadDto().setCurrentPageNumber(1);
            getDocumentDownloadDto().setCondSearchedTitle(getDocumentDownloadDto().getCondTitle());
        }
        getDocumentDownloadDto().setCondCateId("");
        // 必須チェック
//        String title = getDocumentDownloadDto().getCondTitle();
        String title = getDocumentDownloadDto().getCondSearchedTitle();
        if (title == null || "".equals(title.trim())) {
            throw new NotNullException("タイトル");
        }
        MetaCharVerifier verifier = new MetaCharVerifier();
        if (!verifier.validate(title)) {
            throw new InvalidInputException("タイトル");
        }
        Map mapBunshoInfo 
                = getGetDocInfoLogic().execute(
                                         getInfoShu(),
                                         "",
                                         "",
                                         title,
                                         getBirdDateInfo().getSysDate(),
                                         getBirdUserInfo(),
                                         getDocumentDownloadDto().getCurrentPageNumber());
                                     
        //getDocumentDownloadDto().setListBunsho((List) mapBunshoInfo.get("BUNSHO_LIST"));
        
        doGetDocInfoLogicAfter(mapBunshoInfo);
        
        mapBunshoInfo.clear();
        mapBunshoInfo = null;
        
        //検索モード設定
        getDocumentDownloadDto().setSearchMode(SEARCH_MODE_TITLE);

        //1件目の詳細を表示
        doViewDetail();
        
// add start xkhata
        
        getDocumentDownloadDto().setViewCateName("");
// add end
        return null;
    }

    /**
     * カテゴリによる文書一覧検索処理
     * @return
     */
    public String searchByCate() {
        // DTOクリア
        if (getSelectPageNumber() == 0) {
            getDocumentDownloadDto().clearDto();
        }
        if (isSearchMode()) {
            getDocumentDownloadDto().setCurrentPageNumber(1);
        }
        getDocumentDownloadDto().setCondTitle(null);
        // 選択されたカテゴリIDをDTOへセット
        getDocumentDownloadDto().setCondCateId(getCondCateId());
        
        // ロジック【照会文書の取得】を実行
        Map mapBunshoInfo 
                = getGetDocInfoLogic().execute(
                                         getInfoShu(),
                                         getDocumentDownloadDto().getCondCateId(),
                                         getDocumentDownloadDto().getCondSubCateId(),
                                         getDocumentDownloadDto().getCondTitle(),
                                         getBirdDateInfo().getSysDate(),
                                         getBirdUserInfo(),
                                         getDocumentDownloadDto().getCurrentPageNumber());
        
        doGetDocInfoLogicAfter(mapBunshoInfo);
        //検索モード設定
        getDocumentDownloadDto().setSearchMode(SEARCH_MODE_CATEGORY);
        //1件目の詳細を表示
        doViewDetail();
        
// add start xkhata 
        
        String viewCateName = new String();
        for( int i = 0; i < getDocumentDownloadDto().getListNaviCate().size(); i++ ) {
            MstCategoryInfo entity = (MstCategoryInfo)getDocumentDownloadDto().getListNaviCate().get(i);
            if ( getCondCateId().equals( entity.getCateId())) {
                viewCateName = "【" + entity.getCateName() + "】";
                break;
            }
        }
        getDocumentDownloadDto().setViewCateName(viewCateName);
// add end
        
        return null;
    }
    
    /* 
     * 検索処理後の情報設定
     */
    private void doGetDocInfoLogicAfter(Map mapBunshoInfo) {

        // 表示用データ
        getDocumentDownloadDto().setListBunsho((List) mapBunshoInfo.get("BUNSHO_LIST"));
        // 表示サブカテゴリ
        if (getDocumentDownloadDto().getCondSubCateId() == null 
                || getDocumentDownloadDto().getCondSubCateId().equals(""))
        {
            getDocumentDownloadDto().setCondSubCateId((String) mapBunshoInfo.get("DEF_SUB_CATE"));
            getDocumentDownloadDto().setListSubCate((List) mapBunshoInfo.get("SUB_CATE_LIST"));
        }
        getDocumentDownloadDto().setCount(((Integer) mapBunshoInfo.get("TOTAL_COUNT")).intValue());
        
        // １件目の詳細を表示
        getDocumentDownloadDto().setIndexEntityViewDetail(0);
    }

    /**
     * ページ切替アクション
     * @return
     */
    public String changePage() {
        setSearchMode(false);
        // ページ番号
        getDocumentDownloadDto().setCurrentPageNumber(getSelectPageNumber());
        if (isNull(getDocumentDownloadDto().getCondCateId())) {
            //タイトルによる検索
            searchByTitle();
        }
        else {
            //カテゴリによる検索
            setCondCateId(getDocumentDownloadDto().getCondCateId());
            searchByCate();
        }
        setSearchMode(true);
        return null;
    }
    
    /**
     * タブ変更処理
     * @return
     */
    public String changeTab() {
        // ページクリア
        getDocumentDownloadDto().setCurrentPageNumber(1);
        setSelectPageNumber(0);
        getDocumentDownloadDto().setCondSubCateId(getCondSubCateId());
        // ロジック【照会文書の取得】を実行
        Map mapBunshoInfo 
                = getGetDocInfoLogic().execute(
                                         getInfoShu(),
                                         getDocumentDownloadDto().getCondCateId(),
                                         getCondSubCateId(),
                                         getDocumentDownloadDto().getCondTitle(),
                                         getBirdDateInfo().getSysDate(),
                                         getBirdUserInfo(),
                                         getDocumentDownloadDto().getCurrentPageNumber());

        doGetDocInfoLogicAfter(mapBunshoInfo);
        //詳細表示
        doViewDetail();
        return null;
    }

    /**
     * 文書選択
     * @return
     */
    public String viewDetail() {
        doViewDetail();
        return null;
    }
    
    private void doViewDetail() {
        int intSelectIndex = getDocumentDownloadDto().getIndexEntityViewDetail();
        UIViewBunshoInfo entity = (UIViewBunshoInfo) getDocumentDownloadDto().getListBunsho().get(intSelectIndex);
        getDocumentDownloadDto().setEntityViewDetail(entity);
// add start xkhata
        getDocumentDownloadDto().setSelectIndex(intSelectIndex);
// add end
//---2007/08/07 関連ファイルプルダウンの選択位置をクリア        
        getDocumentDownloadDto().setViewSelectedAttachFileIndex(0);

// add start xkahta
        String infoShu = "03";
        String regDate = entity.getRegDate();
        String seq = entity.getSeq();
        Map paramMap = new HashMap();
        paramMap.put("infoShu",infoShu);
        paramMap.put("regDate",regDate);
        paramMap.put("seq",seq);
        
        List shozokuList = getDocShozokuLogic.execute(paramMap);
        
        String viewShozoku = new String();
        
        for ( int i = 0; i < shozokuList.size(); i++ ) {
            UIViewShozokuInfo shozoku = (UIViewShozokuInfo)shozokuList.get(i);
            viewShozoku += shozoku.getShozokuName().trim();
            
            if ( i != shozokuList.size() - 1 ) {
                viewShozoku +=  ",";
            }
        }
        getDocumentDownloadDto().setViewShozoku(viewShozoku);
// add end
//        // 関連文書情報取得
//        List listKanren = getGetKanrenBunshoInfoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq());
//        getDocumentDownloadDto().setListKanrenBunsho(listKanren);
        
    }
    
    //
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    /**
     * @return documentDownloadDto を戻します。
     */
    public DocumentDownloadDto getDocumentDownloadDto() {
        return documentDownloadDto;
    }
    /**
     * @param documentDownloadDto documentDownloadDto を設定。
     */
    public void setDocumentDownloadDto(DocumentDownloadDto documentDownloadDto) {
        this.documentDownloadDto = documentDownloadDto;
    }
//    /**
//     * @return getCategoryLogic を戻します。
//     */
//    public GetCategoryLogic getGetCategoryLogic() {
//        return getCategoryLogic;
//    }
//    /**
//     * @param getCategoryLogic getCategoryLogic を設定。
//     */
//    public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
//        this.getCategoryLogic = getCategoryLogic;
//    }
    
    /**
     * @param infoShu infoShu を設定。
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }

    /**
     * @return infoShu を戻します。
     */
    public String getInfoShu() {
        return infoShu;
    }
    /**
     * @return getDocInfoLogic を戻します。
     */
    public GetDocInfoLogic getGetDocInfoLogic() {
        return getDocInfoLogic;
    }
    /**
     * @param getDocInfoLogic getDocInfoLogic を設定。
     */
    public void setGetDocInfoLogic(GetDocInfoLogic getDocInfoLogic) {
        this.getDocInfoLogic = getDocInfoLogic;
    }
    /**
     * BIRD日付情報設定処理
     * @param birdDateInfo birdDateInfo を設定。
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * BIRDユーザー情報設定処理
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    
    /**
     * カテゴリ一覧の取得ロジックを取得します。
     * @return カテゴリ一覧の取得ロジック 
     */
    public GetUserDocCategoryLogic getGetUserCategoryLogic() {
        return getUserCategoryLogic;
    }

    /**
     * カテゴリ一覧の取得ロジックを設定します。
     * @param カテゴリ一覧の取得ロジック
     */
    public void setGetUserCategoryLogic(
            GetUserDocCategoryLogic getUserCategoryLogic) {
        this.getUserCategoryLogic = getUserCategoryLogic;
    }

    /**
     * @return condCateId を戻します。
     */
    public String getCondCateId() {
        return condCateId;
    }
    /**
     * @param condCateId condCateId を設定。
     */
    public void setCondCateId(String condCateId) {
        this.condCateId = condCateId;
    }
    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    /**
     * @return condSubCateId を戻します。
     */
    public String getCondSubCateId() {
        return condSubCateId;
    }
    /**
     * @param condSubCateId condSubCateId を設定。
     */
    public void setCondSubCateId(String condSubCateId) {
        this.condSubCateId = condSubCateId;
    }
    /**
     * @return getKanrenBunshoInfoLogic を戻します。
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }
    /**
     * @param getKanrenBunshoInfoLogic getKanrenBunshoInfoLogic を設定。
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }
    /**
     * @return selectPageNumber を戻します。
     */
    public int getSelectPageNumber() {
        return selectPageNumber;
    }
    /**
     * @param selectPageNumber selectPageNumber を設定。
     */
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }
	private boolean isSearchMode() {
		return searchMode;
	}
	private void setSearchMode(boolean searchMode) {
		this.searchMode = searchMode;
	}

    public GetDocShozokuLogic getGetDocShozokuLogic() {
        return getDocShozokuLogic;
    }

    public void setGetDocShozokuLogic(GetDocShozokuLogic getDocShozokuLogic) {
        this.getDocShozokuLogic = getDocShozokuLogic;
    }
}