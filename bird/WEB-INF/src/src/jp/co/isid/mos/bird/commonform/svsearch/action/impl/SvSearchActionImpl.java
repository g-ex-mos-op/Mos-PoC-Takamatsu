/*
 * 作成日: 2006/08/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.common.logic.GetSibuLogic;
import jp.co.isid.mos.bird.commonform.common.util.IndexSearchUtil;
import jp.co.isid.mos.bird.commonform.svsearch.action.SvSearchAction;
import jp.co.isid.mos.bird.commonform.svsearch.dto.SvSearchConditionDto;
import jp.co.isid.mos.bird.commonform.svsearch.dto.SvSearchDto;
import jp.co.isid.mos.bird.commonform.svsearch.entity.CodCompany;
import jp.co.isid.mos.bird.commonform.svsearch.entity.UISv;
import jp.co.isid.mos.bird.commonform.svsearch.logic.GetCompanyLogic;
import jp.co.isid.mos.bird.commonform.svsearch.logic.GetCompanyNameLogic;
import jp.co.isid.mos.bird.commonform.svsearch.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.TooManyResultException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;

/**
 * SV選択処理アクションクラス
 * 
 * @author kinugawa(ASPAC)
 */
public class SvSearchActionImpl implements SvSearchAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = SCREEN_ID+"A01";
    public static String select_ACTION_ID       = SCREEN_ID+"A02";
    public static String search_ACTION_ID       = SCREEN_ID+"A03";
    public static String cancel_ACTION_ID       = SCREEN_ID+"A04";
    public static String loadSibuList_ACTION_ID = SCREEN_ID+"A06";

    /* オーナ情報Dto */
    private SvSearchDto svSearchDto;
    /* SvSearchConditionDto */
    private SvSearchConditionDto svSearchConditionDto;
    /* SearchLogic */
    private SearchLogic searchLogic;
    /* GetCompanyLogic */
    private GetCompanyLogic getCompanyLogic;
    /* 会社名リスト取得ロジック */
    private GetCompanyNameLogic getCompanyNameLogic;
 
    /* GetSibuLogic */
    private GetSibuLogic getSibuLogic;
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
    public SvSearchDto getSvSearchDto() {
        return svSearchDto;
    }
    /**
     * オーナー情報Dto設定処理
     * @param svSearchDto
     */
    public void setSvSearchDto(SvSearchDto svSearchDto) {
        this.svSearchDto = svSearchDto;
    }

    /**
     * SV選択用Dto設定処理
     * @return svSearchConditionDto
     */
    public SvSearchConditionDto getSvSearchConditionDto() {
        return svSearchConditionDto;
    }
    /**
     * SV選択用Dto設定処理
     * @param svSearchConditionDto
     */
    public void setSvSearchConditionDto(
            SvSearchConditionDto svSearchConditionDto) {
        this.svSearchConditionDto = svSearchConditionDto;
    }
    /**
     * SV選択用ロジック設定処理
     * @return searchLogic
     */
    public SearchLogic getSearchLogic() {
        return searchLogic;
    }
    /**
     * SV選択用ロジック設定処理
     * @param searchLogic
     */
    public void setSearchLogic(SearchLogic searchLogic) {
        this.searchLogic = searchLogic;
    }
    /**
     * 会社一覧用ロジック設定処理
     * @return getCompanyLogic
     */
    public GetCompanyLogic getGetCompanyLogic() {
        return getCompanyLogic;
    }
    /**
     * 会社一覧用ロジック設定処理
     * @param getCompanyLogic
     */
    public void setGetCompanyLogic(GetCompanyLogic getCompanyLogic) {
        this.getCompanyLogic = getCompanyLogic;
    }
    
    /**
     * 会社名リスト取得ロジックを取得します。
     * 
     * @return 会社名リスト取得ロジック
     */
    public GetCompanyNameLogic getGetCompanyNameLogic() {
        return getCompanyNameLogic;
    }
    /**
     * 会社名リスト取得ロジックを設定します。
     * 
     * @param getCompanyNameLogic 会社名リスト取得ロジック
     */
    public void setGetCompanyNameLogic(GetCompanyNameLogic getCompanyNameLogic) {
        this.getCompanyNameLogic = getCompanyNameLogic;
    }

    /**
     * 支部一覧用ロジック設定処理
     * @return getSibuLogic
     */
    public GetSibuLogic getGetSibuLogic() {
        return getSibuLogic;
    }
    /**
     * 支部一覧用ロジック設定処理
     * @param getSibuLogic
     */
    public void setGetSibuLogic(GetSibuLogic getSibuLogic) {
        this.getSibuLogic = getSibuLogic;
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
        if (svSearchDto.isInitFlag()) {
            // 検索用DTO初期化
            svSearchConditionDto.clear(true);

            // 会社コードリスト取得
            List companyList = null;
            String userId = getBirdUserInfo().getUserID();
            // パラメータ.会社コードリスト有無判定
            List rCompanyCdList = svSearchDto.getRCompanyCdList();
            if (rCompanyCdList == null || rCompanyCdList.isEmpty()) {
                // 会社リスト取得
                companyList = getCompanyLogic.execute(userId);
            } else {
                // 会社名リスト取得
                companyList = getCompanyNameLogic.execute(rCompanyCdList);
                svSearchDto.setRCompanyCdList(null);
            }
            
            // 会社リスト設定
            svSearchConditionDto.setCompanyList(companyList);
            
            String rCompanyCd = null;
            if (companyList != null && !companyList.isEmpty()) {
                // 先頭会社コードを選択中とする。
                rCompanyCd = ((CodCompany) companyList.get(0)).getCompanyCd();
                // 支部スト設定
                svSearchConditionDto.setSibuList(getSibuLogic.execute(rCompanyCd));
            }
            
            svSearchConditionDto.setKaisyaCd(rCompanyCd);
            // 初期処理フラグOFF
            svSearchDto.setInitFlag(false);
            
            svSearchConditionDto.setSortSeq( "SV_CD" );
        }
        return null;
	}

    /**
     * 支部リスト読込処理
     * @return 画面遷移情報
     */
    public String loadSibuList() {
        getSvSearchConditionDto().setSibuList(null);
        svSearchConditionDto.setSibuList(getGetSibuLogic().execute(
                svSearchConditionDto.getKaisyaCd()));
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
            svSearchConditionDto.clear(false);
            // 50音検索文字列リスト取得
            indexSearchList = IndexSearchUtil
                    .getIndexSearchKeyList(new Integer(indexSearchKey)
                            .intValue());
            getSvSearchConditionDto().setSortSeq( "SV_CD" );

        }
        // 検索
        List searchList = getSearchLogic().execute(
				getSvSearchConditionDto(), indexSearchList);
        // 検索結果1000件以上はエラー
		if (searchList != null && searchList.size() > 1000) {
			throw new TooManyResultException("");
		}
        svSearchConditionDto.setSearchSvList(searchList);
        // データ件数チェック
        if (getSvSearchConditionDto().getSearchSvListSize() <= 0) {
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
        if (getSvSearchConditionDto().getSearchSvListSize() > 0) {
            UISv svEntity = (UISv) getSvSearchConditionDto()
                    .getSearchSvList().get(getIndex());
            svSearchDto.setCompanyCd(svEntity.getCompanyCd());
            svSearchDto.setSibuCd(svEntity.getSibuCd());
            svSearchDto.setSvCd(svEntity.getSvCd());
            svSearchDto.setSvNameKj(svEntity.getSvNameKj());
            svSearchDto.setSvNameKna(svEntity.getSvNameKna());
        } else {
            // 選択チェック
            throw new NotNullException("SV選択", "", true);
        }
        // 検索用DTO初期化
        svSearchConditionDto.clear(true);
        // アクションフラグtrue
        svSearchDto.setActionFlag(true);
        return svSearchDto.getNavigationCase();
	}

    /**
     * オーナ検索取消処理
     * @return 画面遷移情報
	 */
	public String cancel() {
        // 検索用DTO初期化
        svSearchConditionDto.clear(true);
        // アクションフラグfalse
        svSearchDto.setActionFlag(false);
        return svSearchDto.getNavigationCase();
	}

    /**
     * 入力チェック
     */
    private void validate() {
    	MetaCharVerifier sqlVerifier = new MetaCharVerifier();
        // 会社コード必須チェック
    	String companyCd = svSearchConditionDto.getKaisyaCd();
        if (companyCd == null || companyCd.equals("")) {
        	NotNullException notNullEx = new NotNullException("会社");
        	notNullEx.setHtmlElementName("companyCd");
            throw notNullEx;
        }
        
        CodeVerifier code5 = new CodeVerifier(5); 

	    // オーナーコード入力チェック
        String onerCd = svSearchConditionDto.getOnerCd();
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
	    String onerNameKj = svSearchConditionDto.getOnerNameKj();
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
	    String miseCd = svSearchConditionDto.getMiseCd();
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
	    String miseNameKj = svSearchConditionDto.getMiseNameKj();
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
}

