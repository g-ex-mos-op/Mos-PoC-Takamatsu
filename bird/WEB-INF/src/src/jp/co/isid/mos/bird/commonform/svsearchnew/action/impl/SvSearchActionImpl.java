package jp.co.isid.mos.bird.commonform.svsearchnew.action.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.common.logic.GetSibuLogic;
import jp.co.isid.mos.bird.commonform.common.util.IndexSearchUtil;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchConditionDto;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchResultDto;
import jp.co.isid.mos.bird.commonform.svsearchnew.entity.CodCompany;
import jp.co.isid.mos.bird.commonform.svsearchnew.entity.UISv;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.GetCompanyLogic;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.GetCompanyNameLogic;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.GetSvInfoLogic;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.SearchLogic;
import jp.co.isid.mos.bird.commonform.svsearchnew.logic.impl.GetSvInfoLogicImpl;
import jp.co.isid.mos.bird.commonform.svsearchnew.action.SvSearchAction;
import jp.co.isid.mos.bird.commonform.svsearchnew.common.SvSearchNewConst;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.TooManyResultException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;

/**
 * SV選択処理アクションクラス
 * 
 * @author kusama
 */
public class SvSearchActionImpl implements SvSearchAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = SvSearchNewConst.VIEW_ID + "A01";
    public static String select_ACTION_ID       = SvSearchNewConst.VIEW_ID + "A02";
    public static String search_ACTION_ID       = SvSearchNewConst.VIEW_ID + "A03";
    public static String cancel_ACTION_ID       = SvSearchNewConst.VIEW_ID + "A04";
    public static String loadSibuList_ACTION_ID = SvSearchNewConst.VIEW_ID + "A06";

    /* オーナ情報Dto */
    private SvSearchDto newSvSearchDto;
    /* SvSearchConditionDto */
    private SvSearchConditionDto newSvSearchConditionDto;
    /* 検索結果用DTO */
    private SvSearchResultDto svSearchResultDto;
    /* SearchLogic */
    private SearchLogic newSearchLogic;
    /* GetCompanyLogic */
    private GetCompanyLogic newGetCompanyLogic;
    /* 会社名リスト取得ロジック */
    private GetCompanyNameLogic newGetCompanyNameLogic;
    /* SV情報取得ロジック */
    private GetSvInfoLogic newGetSvInfoLogic;
    
    /* GetSibuLogic */
    private GetSibuLogic newGetSibuLogic;
    /* SVコード選択index */
    private int index;
    /* 50音検索key */
    private String indexSearchKey;
    /* ユーザ情報 */
    private BirdUserInfo birdUserInfo;

    /**
     * オーナー情報Dto設定処理
     * @return svSearchDto
     */
    public SvSearchDto getNewSvSearchDto() {
        return newSvSearchDto;
    }
    /**
     * オーナー情報Dto設定処理
     * @param svSearchDto
     */
    public void setNewSvSearchDto(SvSearchDto svSearchDto) {
        this.newSvSearchDto = svSearchDto;
    }

    /**
     * SV選択用Dto設定処理
     * @return svSearchConditionDto
     */
    public SvSearchConditionDto getNewSvSearchConditionDto() {
        return newSvSearchConditionDto;
    }
    /**
     * SV選択用Dto設定処理
     * @param svSearchConditionDto
     */
    public void setNewSvSearchConditionDto(
            SvSearchConditionDto svSearchConditionDto) {
        this.newSvSearchConditionDto = svSearchConditionDto;
    }
    /**
     * SV選択用ロジック設定処理
     * @return searchLogic
     */
    public SearchLogic getNewSearchLogic() {
        return newSearchLogic;
    }
    /**
     * SV選択用ロジック設定処理
     * @param searchLogic
     */
    public void setNewSearchLogic(SearchLogic searchLogic) {
        this.newSearchLogic = searchLogic;
    }
    /**
     * 会社一覧用ロジック設定処理
     * @return getCompanyLogic
     */
    public GetCompanyLogic getNewGetCompanyLogic() {
        return newGetCompanyLogic;
    }
    /**
     * 会社一覧用ロジック設定処理
     * @param getCompanyLogic
     */
    public void setNewGetCompanyLogic(GetCompanyLogic getCompanyLogic) {
        this.newGetCompanyLogic = getCompanyLogic;
    }
    
    /**
     * 会社名リスト取得ロジックを取得します。
     * 
     * @return 会社名リスト取得ロジック
     */
    public GetCompanyNameLogic getNewGetCompanyNameLogic() {
        return newGetCompanyNameLogic;
    }
    /**
     * 会社名リスト取得ロジックを設定します。
     * 
     * @param getCompanyNameLogic 会社名リスト取得ロジック
     */
    public void setNewGetCompanyNameLogic(GetCompanyNameLogic getCompanyNameLogic) {
        this.newGetCompanyNameLogic = getCompanyNameLogic;
    }

    /**
     * 支部一覧用ロジック設定処理
     * @return getSibuLogic
     */
    public GetSibuLogic getNewGetSibuLogic() {
        return newGetSibuLogic;
    }
    /**
     * 支部一覧用ロジック設定処理
     * @param getSibuLogic
     */
    public void setNewGetSibuLogic(GetSibuLogic getSibuLogic) {
        this.newGetSibuLogic = getSibuLogic;
    }
   /**
     * オーナコード選択インデックス設定
     * @return index
     */
    public int getIndex() {
        return index;
    }
    /**
     * オーナコード選択インデックス設定
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * 50音検索key設定
     * @param indexSearchKey indexSearchKey を設定。
     */
    public void setIndexSearchKey(String indexSearchKey) {
        this.indexSearchKey = indexSearchKey;
    }

    /**
     * ユーザ情報を取得します。
     * 
     * @return ユーザ情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ情報を設定します。
     * 
     * @param birdUserInfo ユーザ情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * SV検索初期処理
     * @return 画面遷移情報
	 */
	public String initialize() {
        if (newSvSearchDto.isInitFlag()) {
            // ウィンドウID採番
            newSvSearchConditionDto.updateWindowid();
            // 検索用DTO初期化
            newSvSearchConditionDto.clearCondition(true);

            // 会社コードリスト取得
            List companyList = null;
            String userId = getBirdUserInfo().getUserID();
            // パラメータ.会社コードリスト有無判定
            List rCompanyCdList = newSvSearchDto.getRCompanyCdList();
            if (rCompanyCdList == null || rCompanyCdList.isEmpty()) {
                // 会社リスト取得
                companyList = newGetCompanyLogic.execute(userId);
            } else {
                // 会社名リスト取得
                companyList = newGetCompanyNameLogic.execute(rCompanyCdList);
                newSvSearchDto.setRCompanyCdList(null);
            }
            
            // 会社リスト設定
            newSvSearchConditionDto.setCompanyList(companyList);
            
            String rCompanyCd = null;
            if (companyList != null && !companyList.isEmpty()) {
                // 先頭会社コードを選択中とする。
                rCompanyCd = ((CodCompany) companyList.get(0)).getCompanyCd();
                // 支部スト設定
                newSvSearchConditionDto.setSibuList(newGetSibuLogic.execute(rCompanyCd));
            }
            
            newSvSearchConditionDto.setCompanyCd(rCompanyCd);
            newSvSearchConditionDto.setParentViewWindowId(newSvSearchDto.getWindowId());
            newSvSearchConditionDto.setNavigationCase(newSvSearchDto.getNavigationCase());
            // 初期処理フラグOFF
            newSvSearchDto.setInitFlag(false);
            
            newSvSearchConditionDto.setSortSeq( "SV_CD" );
        }
        return null;
	}

    /**
     * 支部リスト読込処理
     * @return 画面遷移情報
     */
    public String loadSibuList() {
        getNewSvSearchConditionDto().setSibuList(null);
        newSvSearchConditionDto.setSibuList(getNewGetSibuLogic().execute(
                newSvSearchConditionDto.getCompanyCd()));
        return null;
    }

    /**
     * オーナ検索処理
     * @return 画面遷移情報
	 */
	public String search() {
        // 入力チェック
        validate();
        // 50音検索判定
        List indexSearchList = new ArrayList();
        if (indexSearchKey != null && !indexSearchKey.equals("")) {
            // フォーム入力の検索条件を削除（会社コードを除く）
            newSvSearchConditionDto.clearCondition(false);
            // 50音検索文字列リスト取得
            indexSearchList = IndexSearchUtil
                    .getIndexSearchKeyList(new Integer(indexSearchKey)
                            .intValue());
            getNewSvSearchConditionDto().setSortSeq( "SV_CD" );

        }
        // 検索
        List searchList = getNewSearchLogic().execute(
				getNewSvSearchConditionDto(), indexSearchList);
        // 検索結果1000件以上はエラー
		if (searchList != null && searchList.size() > 1000) {
			throw new TooManyResultException("");
		}
        getSvSearchResultDto().setListSv(searchList);
        // データ件数チェック
        if (getSvSearchResultDto().getSvSearchListSize() <= 0) {
            throw new NoResultException("");
        }
        return null;
    }

    /**
     * SV検索決定処理
     * @return 画面遷移情報
	 */
	public String select() {
        // 選択情報設定
        //選択SVコード取得
        String selectSV = getSvSearchResultDto().getSelectSvInfo();
        if (selectSV == null || selectSV.trim().length() <= 3) {
            throw new FtlSystemException("選択したSV情報が正しくありません。");
        }
        String companyCd = selectSV.substring(0, 2);
        String svCd = selectSV.substring(3, 11);
        
        //SV情報検索 パラメータ 会社コード、SVコード
        Map mapSv = getNewGetSvInfoLogic().execute(companyCd, svCd);
        UISv entitySV = (UISv) mapSv.get(GetSvInfoLogicImpl.DATA_KEY_SVENTITY);
        List listSibu = (List) mapSv.get(GetSvInfoLogicImpl.DATA_KEY_SVSIBU);
        
        newSvSearchDto.setCompanyCd(entitySV.getCompanyCd());
        newSvSearchDto.setSvCd(entitySV.getSvCd());
        newSvSearchDto.setSvNameKj(entitySV.getSvNameKj());
        newSvSearchDto.setSvNameKna(entitySV.getSvNameKna());
        newSvSearchDto.setSibuCd(listSibu);
        newSvSearchDto.setWindowId(newSvSearchConditionDto.getParentViewWindowId());
        // アクションフラグtrue
        newSvSearchDto.setReturnKind(SvSearchDto.RETURNKIND_SELECT);
        // 戻り画面ID
        String navigationCase = newSvSearchConditionDto.getNavigationCase();
        // 検索用DTO初期化
        newSvSearchConditionDto.clear();
        
        return navigationCase;
	}

    /**
     * オーナ検索取消処理
     * @return 画面遷移情報
	 */
	public String cancel() {
        // アクションフラグfalse
        newSvSearchDto.setReturnKind(SvSearchDto.RETURNKIND_CANCEL);
        newSvSearchDto.setWindowId(newSvSearchConditionDto.getParentViewWindowId());
        // 検索用DTO初期化
        newSvSearchConditionDto.clear();
        
        return newSvSearchDto.getNavigationCase();
	}

    /**
     * 入力チェック
     */
    private void validate() {
    	MetaCharVerifier sqlVerifier = new MetaCharVerifier();
        // 会社コード必須チェック
    	String companyCd = newSvSearchConditionDto.getCompanyCd();
        if (companyCd == null || companyCd.equals("")) {
        	NotNullException notNullEx = new NotNullException("会社");
        	notNullEx.setHtmlElementName("companyCd");
            throw notNullEx;
        }
        
        CodeVerifier code5 = new CodeVerifier(5); 

	    // オーナーコード入力チェック
        String onerCd = newSvSearchConditionDto.getOnerCd();
        if(onerCd != null && onerCd.trim().length() != 0){
            if(!code5.validate(onerCd)){
                throw new InvalidInputException("オーナーコード", "onerCd", null);
            }
        }
        if (onerCd != null && onerCd.getBytes().length > 5) {
	    	NotNullException notNullEx = new NotNullException("オーナーコード");
	    	notNullEx.setHtmlElementName("onerCd");
	        throw notNullEx;
	    }
	
	    // オーナー名文字数
	    String onerNameKj = newSvSearchConditionDto.getOnerNameKj();
	    if (onerNameKj != null) {
	   		if(onerNameKj.getBytes().length > 40) {
	   			InvalidInputException invEx = new InvalidInputException("オーナー名");
	   			invEx.setHtmlElementName("onerNameKj");
	            throw invEx;
	   		}
	   		//SQLメタ文字が含まれているかチェックを行う
	   		if(!sqlVerifier.validate(onerNameKj)) {
	   			InvalidInputException invEx = new InvalidInputException("オーナー名");
	   			invEx.setHtmlElementName("onerNameKj");
	   			throw invEx;
	   		}
	    }
	
	    // 保有店コード入力チェック
	    String miseCd = newSvSearchConditionDto.getMiseCd();
        if(miseCd != null && miseCd.trim().length() != 0){
            if(!code5.validate(miseCd)){
                throw new InvalidInputException("店コード", "miseCd", null);
            }
        }
	    if (miseCd != null && miseCd.getBytes().length > 5) {
	    	NotNullException notNullEx = new NotNullException("店コード");
	    	notNullEx.setHtmlElementName("miseCd");
	        throw notNullEx;
	    }
	
	    // 店名称文字数
	    String miseNameKj = newSvSearchConditionDto.getMiseNameKj();
	    if (miseNameKj != null) {
	   		if(miseNameKj.getBytes().length > 40) {
	   			InvalidInputException invEx = new InvalidInputException("店名称");
	   			invEx.setHtmlElementName("miseNameKj");
	            throw invEx;
	   		}
	   		//SQLメタ文字が含まれているかチェックを行う
	   		if(!sqlVerifier.validate(miseNameKj)) {
	   			InvalidInputException invEx = new InvalidInputException("店名称");
	   			invEx.setHtmlElementName("miseNameKj");
	   			throw invEx;
	   		}
	   }
	    
    }//end of validate() method
    public SvSearchResultDto getSvSearchResultDto() {
        return svSearchResultDto;
    }
    public void setSvSearchResultDto(SvSearchResultDto svSearchResultDto) {
        this.svSearchResultDto = svSearchResultDto;
    }
    public GetSvInfoLogic getNewGetSvInfoLogic() {
        return newGetSvInfoLogic;
    }
    public void setNewGetSvInfoLogic(GetSvInfoLogic newGetSvInfoLogic) {
        this.newGetSvInfoLogic = newGetSvInfoLogic;
    }
}

