/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.commonform.common.logic.GetSibuLogic;
import jp.co.isid.mos.bird.commonform.common.util.IndexSearchUtil;
import jp.co.isid.mos.bird.commonform.ownersearch.action.OwnerSearchAction;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchConditionDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.entity.CodCompany;
import jp.co.isid.mos.bird.commonform.ownersearch.entity.UIGroupTogoOwner;
import jp.co.isid.mos.bird.commonform.ownersearch.logic.GetCompanyLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.logic.GetCompanyNameLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.logic.SearchOwnerLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.TooManyResultException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;

/**
 * オーナー検索処理アクションクラス
 * @author itamoto
 */
public class OwnerSearchActionImpl implements OwnerSearchAction {

    /* アクションID */
    public static final String initialize_ACTION_ID   = "BCO006A01";
    public static final String select_ACTION_ID       = "BCO006A02";
    public static final String search_ACTION_ID       = "BCO006A03";
    public static final String cancel_ACTION_ID       = "BCO006A04";
    public static final String loadSibuList_ACTION_ID = "BCO006A06";

    /* オーナ情報Dto */
    private OwnerSearchDto ownerSearchDto;
    /* OwnerSearchConditionDto */
    private OwnerSearchConditionDto ownerSearchConditionDto;
    /* 今回検索条件DTO */
    private OwnerSearchConditionDto recentConditionDto;
    /* SearchOwnerLogic */
    private SearchOwnerLogic searchOwnerLogic;
    /* GetCompanyLogic */
    private GetCompanyLogic getCompanyLogic;
    /* 会社名リスト取得ロジック */
    private GetCompanyNameLogic getCompanyNameLogic;

    /* GetSibuLogic */
    private GetSibuLogic getSibuLogic;
    /* オーナコード選択index */
    private int index;
    /* 50音検索key */
    private String indexSearchKey;
    /* ユーザ情報 */
    private BirdUserInfo birdUserInfo;

    /**
     * オーナー情報Dto設定処理
     * @return ownerSearchDto
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }
    /**
     * オーナー情報Dto設定処理
     * @param ownerSearchDto
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

    /**
     * オーナー検索用Dto設定処理
     * @return ownerSearchConditionDto
     */
    public OwnerSearchConditionDto getOwnerSearchConditionDto() {
        return ownerSearchConditionDto;
    }
    /**
     * オーナー検索用Dto設定処理
     * @param ownerSearchConditionDto
     */
    public void setOwnerSearchConditionDto(
            OwnerSearchConditionDto ownerSearchConditionDto) {
        this.ownerSearchConditionDto = ownerSearchConditionDto;
    }
    /**
     * 今回検索条件DTO取得処理
     * @return 今回検索条件DTO
     */
    public OwnerSearchConditionDto getRecentConditionDto() {
        return recentConditionDto;
    }
    /**
     * 今回検索条件DTO設定処理
     * @param recentConditionDto 今回検索条件DTO
     */
    public void setRecentConditionDto(OwnerSearchConditionDto recentConditionDto) {
        this.recentConditionDto = recentConditionDto;
    }
    /**
     * オーナー検索用ロジック設定処理
     * @return searchOwnerLogic
     */
    public SearchOwnerLogic getSearchOwnerLogic() {
        return searchOwnerLogic;
    }
    /**
     * オーナー検索用ロジック設定処理
     * @param searchOwnerLogic
     */
    public void setSearchOwnerLogic(SearchOwnerLogic searchOwnerLogic) {
        this.searchOwnerLogic = searchOwnerLogic;
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
     * オーナ検索初期処理
     * @return 画面遷移情報
	 */
	public String initialize() {
        if (ownerSearchDto.isInitFlag()) {
            // ウインドウID採番
            ownerSearchConditionDto.updateWindowid();
            // 呼出元画面情報保持
            ownerSearchConditionDto.setParentViewWindowId(ownerSearchDto.getWindowId());
            ownerSearchConditionDto.setNavigationCase(ownerSearchDto.getNavigationCase());
            ownerSearchConditionDto.setNeedReturnKind(ownerSearchDto.isNeedReturnKind());

            // 検索用DTO初期化
            ownerSearchConditionDto.clear(true);
            getRecentConditionDto().clear(true);

            // 会社コードリスト取得
            List companyList = null;
            String userId = getBirdUserInfo().getUserID();
            // パラメータ.会社コードリスト有無判定
            List rCompanyCdList = ownerSearchDto.getRCompanyCdList();
            if (rCompanyCdList == null || rCompanyCdList.isEmpty()) {
                // 会社リスト取得
                companyList = getCompanyLogic.execute(userId);
            } else {
                // 会社名リスト取得
                companyList = getCompanyNameLogic.execute(rCompanyCdList);
                ownerSearchDto.setRCompanyCdList(null);
            }

            // 会社リスト設定
            ownerSearchConditionDto.setCompanyList(companyList);

            String rCompanyCd = null;
            if (companyList != null && !companyList.isEmpty()) {
                // 先頭会社コードを選択中とする。
            	rCompanyCd = ((CodCompany) companyList.get(0)).getCompanyCd();
                // 支部リスト取得
                ownerSearchConditionDto.setSibuList(getSibuLogic.execute(rCompanyCd));
            }
            getRecentConditionDto().setKaisyaCd(rCompanyCd);
            // 初期処理フラグOFF
            ownerSearchDto.setInitFlag(false);
            
// add start xkhata 2006/05/16 条件追加対応
            getRecentConditionDto().setInEnd( "outEnd" );
            getRecentConditionDto().setSortSeq( "ONER_CD" );
// end
        }
        return null;
	}

    /**
     * 支部リスト読込処理
     * @return 画面遷移情報
     */
    public String loadSibuList() {
        getOwnerSearchConditionDto().setSibuList(null);
        ownerSearchConditionDto.setSibuList(getGetSibuLogic().execute(
                getRecentConditionDto().getKaisyaCd()));
        // 検索結果が残っているか判定
        if (getRecentConditionDto().getResultDispSize() > 0 && !ownerSearchConditionDto.isExistOnerSearchList()) {
            // 保存しておいた検索条件で再検索
            List searchList = getSearchOwnerLogic().execute(
                    ownerSearchConditionDto, ownerSearchConditionDto.getIndexSearchList());
            ownerSearchConditionDto.setOnerSearchList(searchList);
            getRecentConditionDto().setResultDispSize(ownerSearchConditionDto.getOnerSearchListSize());
        }
        return null;
    }

    /**
     * オーナ検索処理
     * @return 画面遷移情報
	 */
	public String search() {
        // 50音検索判定
        List indexSearchList = new ArrayList();
        if (indexSearchKey != null && !indexSearchKey.equals("")) {
            // フォーム入力の検索条件を削除（会社コードを除く）
            getRecentConditionDto().clear(false);
            // 50音検索文字列リスト取得
            indexSearchList = IndexSearchUtil
                    .getIndexSearchKeyList(new Integer(indexSearchKey)
                            .intValue());
//          add start xkhata 2006/05/16 条件追加対応
            getRecentConditionDto().setSortSeq( "ONER_CD" );
            getRecentConditionDto().setInEnd( "inEnd" );       
//     end

        } else {
            // 入力チェック
            try {
                validate();
            } catch (ApplicationException e) {
                // 検索結果が残っているか判定
                if (getRecentConditionDto().getResultDispSize() > 0 && !ownerSearchConditionDto.isExistOnerSearchList()) {
                    // 保存しておいた検索条件で再検索
                    List searchList = getSearchOwnerLogic().execute(
                            ownerSearchConditionDto, ownerSearchConditionDto.getIndexSearchList());
                    ownerSearchConditionDto.setOnerSearchList(searchList);
                    getRecentConditionDto().setResultDispSize(ownerSearchConditionDto.getOnerSearchListSize());
                }
                throw e;
            }
        }

        // 今回入力された検索条件で検索
        List searchList = getSearchOwnerLogic().execute(
				getRecentConditionDto(), indexSearchList);
        // 検索結果1000件以上はエラー
		if (searchList != null && searchList.size() > 1000) {
			throw new TooManyResultException("");
		}
        ownerSearchConditionDto.setOnerSearchList(searchList);
        getRecentConditionDto().setResultDispSize(ownerSearchConditionDto.getOnerSearchListSize());
        // 今回検索条件を保存
        ownerSearchConditionDto.setKaisyaCd(getRecentConditionDto().getKaisyaCd());
        ownerSearchConditionDto.setSibuCd(getRecentConditionDto().getSibuCd());
        ownerSearchConditionDto.setOnerCd(getRecentConditionDto().getOnerCd());
        ownerSearchConditionDto.setMiseCd(getRecentConditionDto().getMiseCd());
        ownerSearchConditionDto.setOnerNameKj(getRecentConditionDto().getOnerNameKj());
        ownerSearchConditionDto.setMiseNameKj(getRecentConditionDto().getMiseNameKj());
        ownerSearchConditionDto.setSvCd(getRecentConditionDto().getSvCd());
        ownerSearchConditionDto.setSvNameKj(getRecentConditionDto().getSvNameKj());
        ownerSearchConditionDto.setInEnd(getRecentConditionDto().getInEnd());
        ownerSearchConditionDto.setSortSeq(getRecentConditionDto().getSortSeq());
        ownerSearchConditionDto.setIndexSearchList(indexSearchList);
        // データ件数チェック
        if (getOwnerSearchConditionDto().getOnerSearchListSize() <= 0) {
            throw new NoResultException("");
        }
        return null;
    }

    /**
     * オーナ検索決定処理
     * @return 画面遷移情報
	 */
	public String select() {
        // 検索結果が残っているか判定
        if (getRecentConditionDto().getResultDispSize() > 0 && !ownerSearchConditionDto.isExistOnerSearchList()) {
            // 保存しておいた検索条件で再検索
            List searchList = getSearchOwnerLogic().execute(
                    ownerSearchConditionDto, ownerSearchConditionDto.getIndexSearchList());
            ownerSearchConditionDto.setOnerSearchList(searchList);
            getRecentConditionDto().setResultDispSize(ownerSearchConditionDto.getOnerSearchListSize());
        }
        // 選択情報設定
        if (getOwnerSearchConditionDto().getOnerSearchListSize() > 0) {
            UIGroupTogoOwner oner = (UIGroupTogoOwner) getOwnerSearchConditionDto()
                    .getOnerSearchList().get(getIndex());
            ownerSearchDto.setCompanyCd(oner.getCompanyCd());
            ownerSearchDto.setOnerCd(oner.getOnerCd());
            ownerSearchDto.setOnerNameKj(oner.getOnerNameKj());
            ownerSearchDto.setOnerNameKna(oner.getOnerNameKna());
        } else {
            // 選択チェック
            throw new NotNullException("オーナ選択", "", true);
        }
        // 検索用DTO初期化
        ownerSearchConditionDto.clear(true);
        // アクションフラグtrue
        ownerSearchDto.setActionFlag(true);
        // 遷移区分要否判定
        int returnKind = OwnerSearchDto.RETURNKIND_INIT;
        if (getOwnerSearchConditionDto().isNeedReturnKind()) {
            returnKind = OwnerSearchDto.RETURNKIND_SELECT;
            ownerSearchDto.setNeedReturnKind(false);
        }
        ownerSearchDto.setReturnKind(returnKind);
        // 呼出元画面情報戻し
        ownerSearchDto.setWindowId(getOwnerSearchConditionDto().getParentViewWindowId());
        ownerSearchDto.setNavigationCase(getOwnerSearchConditionDto().getNavigationCase());
        return ownerSearchDto.getNavigationCase();
	}

    /**
     * オーナ検索取消処理
     * @return 画面遷移情報
	 */
	public String cancel() {
        // 検索用DTO初期化
        ownerSearchConditionDto.clear(true);
        // アクションフラグfalse
        ownerSearchDto.setActionFlag(false);
        // 遷移区分要否判定
        int returnKind = OwnerSearchDto.RETURNKIND_INIT;
        if (getOwnerSearchConditionDto().isNeedReturnKind()) {
            returnKind = OwnerSearchDto.RETURNKIND_CANCEL;
            ownerSearchDto.setNeedReturnKind(false);
        }
        ownerSearchDto.setReturnKind(returnKind);
        // ウインドウID、画面遷移情報戻し
        ownerSearchDto.setWindowId(getOwnerSearchConditionDto().getParentViewWindowId());
        ownerSearchDto.setNavigationCase(getOwnerSearchConditionDto().getNavigationCase());
        return ownerSearchDto.getNavigationCase();
	}

    /**
     * 入力チェック
     */
    private void validate() {
    	MetaCharVerifier sqlVerifier = new MetaCharVerifier();
        // 会社コード必須チェック
    	String companyCd = getRecentConditionDto().getKaisyaCd();
        if (companyCd == null || companyCd.equals("")) {
        	NotNullException notNullEx = new NotNullException("会社");
        	notNullEx.setHtmlElementName("companyCd");
            throw notNullEx;
        }
        
        CodeVerifier code5 = new CodeVerifier(5); 
        CodeVerifier code8 = new CodeVerifier(8);

	    // オーナーコード入力チェック
        String onerCd = getRecentConditionDto().getOnerCd();
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
	    String onerNameKj = getRecentConditionDto().getOnerNameKj();
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
	    String miseCd = getRecentConditionDto().getMiseCd();
        if(miseCd != null && miseCd.trim().length() != 0){
            if(!code5.validate(miseCd)){
                throw new InvalidInputException("保有店コード", "miseCd", null);
            }
        }
	    if (miseCd != null && miseCd.getBytes().length > 5) {
	    	NotNullException notNullEx = new NotNullException("保有店コード");
	    	notNullEx.setHtmlElementName("miseCd");
	        throw notNullEx;
	    }
	
	    // 保有店名文字数
	    String miseNameKj = getRecentConditionDto().getMiseNameKj();
	    if (miseNameKj != null) {
	   		if(miseNameKj.getBytes().length > 40) {
	   			InvalidInputException invEx = new InvalidInputException("保有店名");
	   			invEx.setHtmlElementName("miseNameKj");
	            throw invEx;
	   		}
	   		//SQLメタ文字が含まれているかチェックを行う
	   		if(!sqlVerifier.validate(miseNameKj)) {
	   			InvalidInputException invEx = new InvalidInputException("保有店名");
	   			invEx.setHtmlElementName("miseNameKj");
	   			throw invEx;
	   		}
	   }
	    
	   // SVコード入力チェック
	   String svCd = getRecentConditionDto().getSvCd();
       if(svCd != null && svCd.trim().length() != 0){
//---2007/05/10 SV関連テーブル変更に伴い、桁数変更
//           if(!code5.validate(svCd)){
           if(!code8.validate(svCd)){
               throw new InvalidInputException("SVコード", "svCd", null);
           }
       }
//---2007/05/10 SV関連テーブル変更に伴い、桁数変更
//	   if (svCd != null && svCd.getBytes().length > 5) {
       if (svCd != null && svCd.getBytes().length > 8) {
			   NotNullException notNullEx = new NotNullException("SVコード");
			   notNullEx.setHtmlElementName("svCd");
			   throw notNullEx;
	   }
	   
	   // 名称文字数
	   String svNameKj = getRecentConditionDto().getSvNameKj();
	   if (svNameKj != null) {
		   if(svNameKj.getBytes().length > 40) {
			   InvalidInputException invEx = new InvalidInputException("SV名称");
			   invEx.setHtmlElementName("svNameKj");
	           throw invEx;
	   	}
	   //SQLメタ文字が含まれているかチェックを行う
	   if(!sqlVerifier.validate(svNameKj)) {
			   InvalidInputException invEx = new InvalidInputException("SV名称");
			   invEx.setHtmlElementName("svNameKj");
			   throw invEx;
	   }
	   	
	   }
    }
}

