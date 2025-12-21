/*
 * 作成日: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.commonform.common.logic.GetSibuLogic;
import jp.co.isid.mos.bird.commonform.misesearch.action.MiseSearchAction;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchConditionDto;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.misesearch.entity.CodCompany;
import jp.co.isid.mos.bird.commonform.misesearch.entity.UIGroupTogoMise;
import jp.co.isid.mos.bird.commonform.misesearch.logic.GetCompanyLogic;
import jp.co.isid.mos.bird.commonform.misesearch.logic.GetCompanyNameLogic;
import jp.co.isid.mos.bird.commonform.misesearch.logic.SearchMiseLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.TooManyResultException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;

/**
 * @author xyuchida
 *
 */
public class MiseSearchActionImpl implements MiseSearchAction {

	// アクションID定義
	public static final String initialize_ACTION_ID = "BCO008A01";
	public static final String select_ACTION_ID = "BCO008A02";
	public static final String search_ACTION_ID = "BCO008A03";
    public static final String cancel_ACTION_ID = "BCO008A04";
	public static final String loadSibuList_ACTION_ID = "BCO008A06";
	
// start xkhata 2006/05/15 条件追加対応
	public static final String MISE_CD = "MISE_CD";
    public static final String outClose = "outClose";
    public static final String inClose = "inClose";
// end
    public static final String MISE_NAME_KNA = "MISE_NAME_KNA";

    
    private static final int MAXCOUNT_MISELIST = 1500;
    /**
	 * 店検索DTO
	 */
    private MiseSearchDto miseSearchDto;

	/**
	 * 店検索条件DTO
	 */
    private MiseSearchConditionDto miseSearchConditionDto;

    /**
     *  今回検索条件DTO
     */
    private MiseSearchConditionDto recentConditionMiseDto;

    /**
     * 店検索ロジック
     */
    private SearchMiseLogic searchMiseLogic;

    /**
     * 会社リスト取得ロジック
     */
    private GetCompanyLogic getCompanyLogic;

    /**
     * 会社名リスト取得ロジック
     */
    private GetCompanyNameLogic getCompanyNameLogic;

    /**
     * 支部リスト取得ロジック
     */
    private GetSibuLogic getSibuLogic;

    /**
     * 店選択インデックス
     */
    private int index;

    /**
     * 50音検索Key
     */
    private int indexSearchKey = -1;

    /**
     * ユーザ情報
     */
    private BirdUserInfo birdUserInfo;

	/**
     * 店検索DTOを取得します。
     * 
     * @return 店検索DTO
     */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}

    /**
     * 店検索DTOを設定します。
     * 
     * @param miseSearchDto 店検索DTO
     */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}

    /**
     * 店検索条件DTOを取得します。
     * 
     * @return 店検索条件DTO
     */
	public MiseSearchConditionDto getMiseSearchConditionDto() {
		return miseSearchConditionDto;
	}

    /**
     * 店検索条件DTOを設定します。
     * 
     * @param miseSearchConditionDto 店検索条件DTO
     */
	public void setMiseSearchConditionDto(
			MiseSearchConditionDto miseSearchConditionDto) {
		this.miseSearchConditionDto = miseSearchConditionDto;
	}


    /**
     * 今回検索条件DTOを取得します。
     * 
     * @return 店検索条件DTO
     */

    public MiseSearchConditionDto getRecentConditionMiseDto() {
        return recentConditionMiseDto;
    }

    /**
     * 今回検索条件DTOを設定します。
     * 
     * @param miseSearchConditionDto 店検索条件DTO
     */
    public void setRecentConditionMiseDto(
            MiseSearchConditionDto recentConditionMiseDto) {
        this.recentConditionMiseDto = recentConditionMiseDto;
    }

    /**
     * 店検索ロジックを取得します。
     * 
     * @return 店検索ロジック
     */
	public SearchMiseLogic getSearchMiseLogic() {
        return searchMiseLogic;
    }

    /**
     * 店検索ロジックを設定します。
     * 
     * @param searchMiseLogic 店検索ロジック
     */
    public void setSearchMiseLogic(SearchMiseLogic searchMiseLogic) {
        this.searchMiseLogic = searchMiseLogic;
    }

    /**
     * 会社リスト取得ロジックを取得します。
     * 
     * @return 会社リスト取得ロジック
     */
    public GetCompanyLogic getGetCompanyLogic() {
        return getCompanyLogic;
    }

    /**
     * 会社リスト取得ロジックを設定します。
     * 
     * @param getCompanyLogic 会社リスト取得ロジック
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
     * 支部リスト取得ロジックを取得します。
     * 
     * @return 支部リスト取得ロジック
     */
	public GetSibuLogic getGetSibuLogic() {
		return getSibuLogic;
	}

    /**
     * 支部リスト取得ロジックを設定します。
     * 
     * @param getSibuLogic 支部リスト取得ロジック
     */
	public void setGetSibuLogic(GetSibuLogic getSibuLogic) {
		this.getSibuLogic = getSibuLogic;
	}

    /**
     * 店選択インデックスを取得します。
     * 
     * @return 店選択インデックス
     */
	public int getIndex() {
		return index;
	}

    /**
     * 店選択インデックスを設定します。
     * 
     * @param index 店選択インデックス
     */
	public void setIndex(int index) {
		this.index = index;
	}

    /**
     * 50音検索Keyを取得します。
     * 
     * @return 50音検索Key
     */
	public int getIndexSearchKey() {
		return indexSearchKey;
	}

    /**
     * 50音検索Keyを設定します。
     * 
     * @param indexSearchKey 50音検索Key
     */
	public void setIndexSearchKey(int indexSearchKey) {
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
	 * 初期処理
	 * 
	 * @return 画面遷移情報
	 */
	public String initialize() {

		// 初期化フラグ判定
		if (miseSearchDto.isInitialFlag()) {
            // ウインドウID採番
            getMiseSearchConditionDto().updateWindowid();

            // 検索結果初期化
			getMiseSearchConditionDto().clear();
            getRecentConditionMiseDto().clear();

            // 呼出元画面情報保持
            getMiseSearchConditionDto().setParentViewWindowId(miseSearchDto.getWindowId());
            getMiseSearchConditionDto().setReturnWindowId(miseSearchDto.getReturnWindowId());
            getMiseSearchConditionDto().setNavigationCase(miseSearchDto.getNavigationCase());
            getMiseSearchConditionDto().setNeedReturnKind(miseSearchDto.isNeedReturnKind());

            // パラメータ.会社コードリスト有無判定
            List companyList = null;
            List rCompanyCdList = getMiseSearchDto().getRCompanyCdList();
            if (rCompanyCdList == null || rCompanyCdList.isEmpty()) {
                // 会社リスト取得
                companyList = getCompanyLogic.execute(getBirdUserInfo().getUserID());
            } else {	
                // 会社名リスト取得
                companyList = getCompanyNameLogic.execute(rCompanyCdList);
                getMiseSearchDto().setRCompanyCdList(null);
            }

            // 会社リスト設定
            String rCompanyCd = null;
            getMiseSearchConditionDto().setCompanyList(companyList);
            if (companyList != null && !companyList.isEmpty()) {
                // 先頭会社コードを選択中とする。
                rCompanyCd = ((CodCompany) companyList.get(0)).getCompanyCd();
                // 支部リスト取得
                getMiseSearchConditionDto().setSibuList(getSibuLogic.execute(rCompanyCd));
            }
            getRecentConditionMiseDto().setRCompanyCd(rCompanyCd);

			// 初期化フラグ更新
	        miseSearchDto.setInitialFlag(false);
            
// add start xkhata 2006/05/18 条件追加対応
	        getRecentConditionMiseDto().setInClose( outClose );
            getRecentConditionMiseDto().setSortSeq( MISE_CD );
// end
		}

        return null;
	}

	/**
	 * 検索
	 * 
	 * @return 画面遷移情報
	 */
	public String search() {

		// 50音検索Key設定
		// 50音検索以外の場合は、初期値である-1が設定されるので検索条件とならない
        getRecentConditionMiseDto().setIndexSearchKey(indexSearchKey);
        if ( indexSearchKey != -1 ) {
            getRecentConditionMiseDto().setSibuCd(null);
            getRecentConditionMiseDto().setOnerCd(null);
            getRecentConditionMiseDto().setOnerNameKj(null);
            getRecentConditionMiseDto().setMiseCd(null);
            getRecentConditionMiseDto().setMiseNameKj(null);
            getRecentConditionMiseDto().setSortSeq( MISE_NAME_KNA );
            getRecentConditionMiseDto().setInClose(getMiseSearchConditionDto().getInNaviClose());
        } else {
            try {
                // 入力値論理チェック
                validate();
            } catch (ApplicationException e) {
                // 検索結果が残っているか判定
                if (getRecentConditionMiseDto().getResultDispSize() > 0 && !getMiseSearchConditionDto().isExistMiseSearchList()) {
                    // 保存しておいた検索条件で再検索
                    List miseSearchList = getSearchMiseLogic().execute(getMiseSearchConditionDto());
                    getMiseSearchConditionDto().setMiseSearchList(miseSearchList);
                    getRecentConditionMiseDto().setResultDispSize(getMiseSearchConditionDto().getMiseSearchListSize());
                }
                throw e;
            }
        }

		// 検索
		List miseSearchList = searchMiseLogic.execute(getRecentConditionMiseDto());
		getMiseSearchConditionDto().setMiseSearchList(miseSearchList);
        getRecentConditionMiseDto().setResultDispSize(getMiseSearchConditionDto().getMiseSearchListSize());

        // 今回検索条件を保存
        getMiseSearchConditionDto().setRCompanyCd(getRecentConditionMiseDto().getRCompanyCd());
        getMiseSearchConditionDto().setSibuCd(getRecentConditionMiseDto().getSibuCd());
        getMiseSearchConditionDto().setOnerCd(getRecentConditionMiseDto().getOnerCd());
        getMiseSearchConditionDto().setOnerNameKj(getRecentConditionMiseDto().getOnerNameKj());
        getMiseSearchConditionDto().setMiseCd(getRecentConditionMiseDto().getMiseCd());
        getMiseSearchConditionDto().setMiseNameKj(getRecentConditionMiseDto().getMiseNameKj());
        getMiseSearchConditionDto().setInClose(getRecentConditionMiseDto().getInClose());
        getMiseSearchConditionDto().setSortSeq(getRecentConditionMiseDto().getSortSeq());
        getMiseSearchConditionDto().setIndexSearchKey(getRecentConditionMiseDto().getIndexSearchKey());

        // 検索結果有無判定
		if (miseSearchList == null || miseSearchList.size() <= 0) {
			throw new NoResultException();
		}
        // 検索結果件数チェック
        if (miseSearchList.size() > MAXCOUNT_MISELIST) {
            getMiseSearchConditionDto().getMiseSearchList().clear();
            throw new TooManyResultException(MAXCOUNT_MISELIST + "件まで。");
        }

		return null;
	}

	/**
	 * 決定
	 * 
	 * @return 画面遷移情報
	 */
	public String select() {

        // 検索結果が残っているか判定
        if (getRecentConditionMiseDto().getResultDispSize() > 0 && !getMiseSearchConditionDto().isExistMiseSearchList()) {
            // 保存しておいた検索条件で再検索
            List miseSearchList = getSearchMiseLogic().execute(getMiseSearchConditionDto());
            getMiseSearchConditionDto().setMiseSearchList(miseSearchList);
            getRecentConditionMiseDto().setResultDispSize(getMiseSearchConditionDto().getMiseSearchListSize());
        }

        List miseSearchList = getMiseSearchConditionDto().getMiseSearchList();

		// 選択有効判定
		if (miseSearchList == null
				|| miseSearchList.size() <= 0
				|| index < 0
				|| index >= miseSearchList.size()) {
			throw new NotNullException("店選択");
		}

		// 選択店コード設定
		UIGroupTogoMise entity = (UIGroupTogoMise) miseSearchList.get(index);
		miseSearchDto.setMiseCd(entity.getMiseCd());

        // アクションフラグ設定
        miseSearchDto.setActionFlg(true);

        // 遷移区分要否判定
        int returnKind = MiseSearchDto.RETURNKIND_INIT;
        if (getMiseSearchConditionDto().isNeedReturnKind()) {
            returnKind = MiseSearchDto.RETURNKIND_SELECT;
            miseSearchDto.setNeedReturnKind(false);
        }
        miseSearchDto.setReturnKind(returnKind);
        // ウインドウID、画面遷移情報戻し
        miseSearchDto.setWindowId(getMiseSearchConditionDto().getParentViewWindowId());
        miseSearchDto.setReturnWindowId(getMiseSearchConditionDto().getReturnWindowId());
        miseSearchDto.setNavigationCase(getMiseSearchConditionDto().getNavigationCase());

        // 呼出元画面へ遷移
		return miseSearchDto.getNavigationCase();
	}

	/**
	 * 戻る
	 * 
	 * @return 画面遷移情報
	 */
	public String cancel() {

        // アクションフラグ設定
        miseSearchDto.setActionFlg(false);

        miseSearchDto.setMiseCd(null);

        // 遷移区分要否判定
        int returnKind = MiseSearchDto.RETURNKIND_INIT;
        if (getMiseSearchConditionDto().isNeedReturnKind()) {
            returnKind = MiseSearchDto.RETURNKIND_CANCEL;
            miseSearchDto.setNeedReturnKind(false);
        }
        miseSearchDto.setReturnKind(returnKind);
        // ウインドウID、画面遷移情報戻し
        miseSearchDto.setWindowId(getMiseSearchConditionDto().getParentViewWindowId());
        miseSearchDto.setReturnWindowId(getMiseSearchConditionDto().getReturnWindowId());
        miseSearchDto.setNavigationCase(getMiseSearchConditionDto().getNavigationCase());

        // 呼出元画面へ遷移
		return miseSearchDto.getNavigationCase();
	}

	/**
	 * 会社プルダウンの変更
	 * 
	 * @return 画面遷移情報
	 */
    public String loadSibuList() {

    	// 支部リスト取得
    	getMiseSearchConditionDto().setSibuList(getSibuLogic.execute(getRecentConditionMiseDto().getRCompanyCd()));

        // 検索結果が残っているか判定
        if (getRecentConditionMiseDto().getResultDispSize() > 0 && !getMiseSearchConditionDto().isExistMiseSearchList()) {
            // 保存しておいた検索条件で再検索
            List miseSearchList = getSearchMiseLogic().execute(getMiseSearchConditionDto());
            getMiseSearchConditionDto().setMiseSearchList(miseSearchList);
            getRecentConditionMiseDto().setResultDispSize(getMiseSearchConditionDto().getMiseSearchListSize());
        }

        return null;
    }

    /**
     * 入力値論理チェック
     * 
     */
    private void validate() {

    	MetaCharVerifier sqlVerifier = new MetaCharVerifier();
    	// 会社コード必須
    	String companyCd = getRecentConditionMiseDto().getRCompanyCd();
        if (companyCd == null || companyCd.length() <= 0) {
        	NotNullException notNullEx = new NotNullException("会社");
        	notNullEx.setHtmlElementName("companyCd");
            throw notNullEx;
        }

        CodeVerifier code5 = new CodeVerifier(5);

        // オーナーコード入力チェック
        String onerCd = getRecentConditionMiseDto().getOnerCd();
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
        String onerNameKj = getRecentConditionMiseDto().getOnerNameKj();
        if (onerNameKj != null) {
//--- 2006/03/23 delete 結合課題管理表No393 文字数制限削除
//       		if(onerNameKj.getBytes().length > 40) {
//	        	NotNullException notNullEx = new NotNullException("オーナー名");
//	        	notNullEx.setHtmlElementName("onerNameKj");
//	            throw notNullEx;
//       		}
       		//SQLメタ文字が含まれているかチェックを行う
       		if(!sqlVerifier.validate(onerNameKj)) {
       			InvalidInputException invEx = new InvalidInputException("オーナー名");
       			invEx.setHtmlElementName("onerNameKj");
       			throw invEx;
       		}
        }
        // 店コード入力チェック
        String miseCd = getRecentConditionMiseDto().getMiseCd();
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

        // 店舗名文字数
        String miseNameKj = getRecentConditionMiseDto().getMiseNameKj();
        if (miseNameKj != null) {
//--- 2006/03/23 delete 結合課題管理表No393 文字数制限削除
//       		if(miseNameKj.getBytes().length > 40) {
//	        	NotNullException notNullEx = new NotNullException("店舗名");
//	        	notNullEx.setHtmlElementName("miseNameKj");
//	            throw notNullEx;
//       		}
       		//SQLメタ文字が含まれているかチェックを行う
       		if(!sqlVerifier.validate(miseNameKj)) {
       			InvalidInputException invEx = new InvalidInputException("店舗名");
       			invEx.setHtmlElementName("miseNameKj");
       			throw invEx;
       		}
       }
    }
}
