/*
 * 作成日: 2006/3/27
 */
package jp.co.isid.mos.bird.bizsupport.plcontentconfirm.action.impl;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plcontentconfirm.action.PlContentFormAction;
import jp.co.isid.mos.bird.bizsupport.common.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistStateDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.UIGroupTogoOwner;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLAuthorLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserOnerLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

/**
 * P/Lデータ内容確認　条件画面アクションクラス
 * @author itamoto
 */
public class PlContentFormActionImpl implements PlContentFormAction {

    private static Logger logger_ = Logger.getLogger(PlContentFormActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BBS003A01";
    public static String search_ACTION_ID            = "BBS003A02";
    public static String execute_ACTION_ID           = "BBS003A03";
    
    /* VIEW_ID */
    public final String VIEW_ID                  = "BBS003";
    public final String plContentForm_VIEW_ID    = "BBS003V01";
    public final String plContentConfirm_VIEW_ID = "BBS003V02";
    public final String onerSearch_VIEW_ID       = "BCO006V01";

    /* 年月出力月数 */
    private static int NENGETU_DISPLAY_MONTH = 13;

    /* P/Lデータ入力用Dto */
    private PlRegistDto plRegistSecDto;
    /* OwnerSearchDto */
    private OwnerSearchDto ownerSearchDto;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
    
    /* 登録状況確認用Dto */
    private PlRegistStateDto plRegistStateDto;
    
    /* P/Lデータエンティティ */
    private TrnPLInfo trnPLInfoSec;
    /* 前月P/Lデータエンティティ */
    private TrnZenPLInfo trnZenPLInfoSec;

    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* 日付情報 */
    private BirdDateInfo birdDateInfo;

    /* ユーザー対応オーナーの検索Logic */
    private GetUserOnerLogic getUserOnerLogic;
    /* PL作成者情報の取得Logic */
    private GetPLAuthorLogic getPLAuthorLogic;

    /* P/Lデータ取得ロジック */
    private GetPLDataLogic getPLDataLogic;

    /* オーナ情報取得Dao */
    UIGroupTogoOwnerDao uiGroupTogoOwnerDao;

    /**
	 * P/Lデータ入力用Dtoの設定
	 * @return plRegistDto を戻します。
	 */
	public PlRegistDto getPlRegistSecDto() {
		return plRegistSecDto;
	}
	/**
	 * P/Lデータ入力用Dtoの設定
	 * @param plRegistDto plRegistDto を設定。
	 */
	public void setPlRegistSecDto(PlRegistDto plRegistSecDto) {
		this.plRegistSecDto = plRegistSecDto;
	}
	
	/**
	 * オーナ選択Dtoの設定
	 * @return ownerSearchDto を戻します。
	 */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	/**
	 * オーナ選択Dtoの設定
	 * @param ownerSearchDto ownerSearchDto を設定。
	 */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}

	/**
	 * pullDownMenuDtoの設定
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	/**
	 * pullDownMenuDtoの設定
	 * @param pullDownMenuDto pullDownMenuDto を設定。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
    
    /**
     * plRegistStateDtoの設定
     * @return plRegistStateDto を戻します。
     */
    public PlRegistStateDto getPlRegistStateDto() {
        return plRegistStateDto;
    }
    /**
     * plRegistStateDtoの設定
     * @param plRegistStateDto plRegistStateDto を設定。
     */    
    public void setPlRegistStateDto(PlRegistStateDto plRegistStateDto) {
        this.plRegistStateDto = plRegistStateDto;
    }

	/**
	 * P/Lデータエンティティの設定
	 * @return trnPLInfoSec を戻します。
	 */
	public TrnPLInfo getTrnPLInfoSec() {
		return trnPLInfoSec;
	}
	/**
	 * P/Lデータエンティティの設定
	 * @param trnPLInfo trnPLInfoSec を設定。
	 */
	public void setTrnPLInfoSec(TrnPLInfo trnPLInfoSec) {
		this.trnPLInfoSec = trnPLInfoSec;
	}
	
	/**
	 * 前月P/Lデータエンティティの設定
	 * @return trnZenPLInfoSec を戻します。
	 */
	public TrnZenPLInfo getTrnZenPLInfoSec() {
		return trnZenPLInfoSec;
	}
	/**
	 * 前月P/Lデータエンティティの設定
	 * @param trnZenPLInfoSec trnZenPLInfoSec を設定。
	 */
	public void setTrnZenPLInfoSec(TrnZenPLInfo trnZenPLInfoSec) {
		this.trnZenPLInfoSec = trnZenPLInfoSec;
	}

	/**
	 * BIRDログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	
	/**
	 * 日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * 日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}
	
	/**
	 * ユーザー対応オーナーの検索Logicの設定
	 * @return getUserOnerLogic を戻します。
	 */
	public GetUserOnerLogic getGetUserOnerLogic() {
		return getUserOnerLogic;
	}
	/**
	 * ユーザー対応オーナーの検索Logicの設定
	 * @param getUserOnerLogic getUserOnerLogic を設定。
	 */
	public void setGetUserOnerLogic(GetUserOnerLogic getUserOnerLogic) {
		this.getUserOnerLogic = getUserOnerLogic;
	}
	
	/**
	 * PL作成者情報の取得Logicの設定
	 * @return getPLAuthorLogic を戻します。
	 */
	public GetPLAuthorLogic getGetPLAuthorLogic() {
		return getPLAuthorLogic;
	}
	/**
	 * PL作成者情報の取得Logicの設定
	 * @param getPLAuthorLogic getPLAuthorLogic を設定。
	 */
	public void setGetPLAuthorLogic(GetPLAuthorLogic getPLAuthorLogic) {
		this.getPLAuthorLogic = getPLAuthorLogic;
	}

	/**
	 * P/Lデータ取得ロジックの設定
	 * @return getPLDataLogic を戻します。
	 */
	public GetPLDataLogic getGetPLDataLogic() {
		return getPLDataLogic;
	}
	/**
	 * P/Lデータ取得ロジックの設定
	 * @param getPLDataLogic getPLDataLogic を設定。
	 */
	public void setGetPLDataLogic(GetPLDataLogic getPLDataLogic) {
		this.getPLDataLogic = getPLDataLogic;
	}

	/**
	 * オーナ情報取得Daoの設定
	 * @return uiGroupTogoOwnerDao を戻します。
	 */
	public UIGroupTogoOwnerDao getUiGroupTogoOwnerDao() {
		return uiGroupTogoOwnerDao;
	}
	/**
	 * オーナ情報取得Daoの設定
	 * @param uiGroupTogoOwnerDao uiGroupTogoOwnerDao を設定。
	 */
	public void setUiGroupTogoOwnerDao(UIGroupTogoOwnerDao uiGroupTogoOwnerDao) {
		this.uiGroupTogoOwnerDao = uiGroupTogoOwnerDao;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
		// 初期処理
		if (pullDownMenuDto.isClearFlg()) {
            S2Container container = SingletonS2ContainerFactory.getContainer();
			HttpServletRequest request = (HttpServletRequest) container
					.getComponent("request");
			BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
					.getAttribute("birdUserInfo");
			BirdDateInfo birdDateInfo = (BirdDateInfo) request.getSession()
			.getAttribute("birdDateInfo");

			// 初期化
			plRegistSecDto = new PlRegistDto();
			trnPLInfoSec = new TrnPLInfo();
			trnZenPLInfoSec = new TrnZenPLInfo();
			
			plRegistSecDto.setUserTypeCd(birdUserInfo.getMstUser().getUserTypeCd());
			plRegistSecDto.setBirdUserInfo(birdUserInfo);
			plRegistSecDto.setBirdDateInfo(birdDateInfo);
			
			// ユーザタイプコード　オーナの場合
			if (birdUserInfo.getMstUser().getUserTypeCd().equals("02")) {
                
                //対象年月に当日を設定 店舗リストを取得設定
                plRegistSecDto.setTargetYM(birdDateInfo.getSysDate());
                getUserOnerLogic.execute(birdUserInfo.getUserID(), plRegistSecDto);
				
				// ①[[ユーザー対応オーナー]]が0件でない場合
				if (plRegistSecDto.getMsTUserOnerInfo() != null) {
					// ・ロジック【PL作成者情報の取得】を実行する。
					// パラメータ[[ユーザー対応オーナー]].オーナーコード
					TrnPLAuthorInfo trnPLAuthorInfo = getPLAuthorLogic
							.execute(plRegistSecDto.getMsTUserOnerInfo()
									.getOnerCd());
					if (trnPLAuthorInfo == null) {
						trnPLAuthorInfo = new TrnPLAuthorInfo();
					}
					plRegistSecDto.setTrnPLAuthorInfo(trnPLAuthorInfo);

					// ・結果を各項目にセット
					plRegistSecDto.setOnerCd(plRegistSecDto.getMsTUserOnerInfo().getOnerCd());
					plRegistSecDto.setStorePlList(plRegistSecDto.getMsTUserMiseInfoList());
					plRegistSecDto.setHeadOfficePlOnerCd(plRegistSecDto
							.getMsTUserOnerInfo().getOnerCd());
					plRegistSecDto.setHeadOfficePlOnerName(plRegistSecDto
							.getMsTUserOnerInfo().getOnerNameKj());
					// ・「オーナー」フィールド、「検索」ボタンを表示する。
				}
				// ③[[ユーザー対応オーナー]]の結果が0件の場合
				// ・各項目を空でセット
				// ※項目：オーナー・店舗P/L・本社P/L・作成者欄
			} else {
				// 条件画面表示モード
				plRegistSecDto.setSelectViewMode(1);
			}
			
            // 対象年月取得
			plRegistSecDto.setTargetYMList(plRegistSecDto
    				.createTargetYMList(birdDateInfo));
            // 前月をデフォルト設定
            DateFormatter formatter = new DateFormatter();
            String sysMonth = formatter.format(birdDateInfo.getSysDate(),
					DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
            try {
            	plRegistSecDto.setTargetYM(DateManager.getPrevMonth(sysMonth, 1));
            	plRegistSecDto.setTargetYMSelectBox(DateManager.getPrevMonth(sysMonth, 1));
			} catch (Exception e) {
                throw new FtlSystemException("条件画面初期処理");
			}

			pullDownMenuDto.setClearFlg(false);
		}
		
		// オーナ選択情報取得
		if (ownerSearchDto.isActionFlag()) {
			plRegistSecDto.setOnerCd(ownerSearchDto.getOnerCd());
			ownerSearchDto.setActionFlag(false);
			
			// 条件画面表示モード
			plRegistSecDto.setSelectViewMode(1);
		}
        return null;
    }

    /**
     * 検索
     * @return 画面遷移情報
     */
    public String search() {
		// オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
		ownerSearchDto.clear();
		ownerSearchDto.setNavigationCase(plContentForm_VIEW_ID);
		ownerSearchDto.setInitFlag(true);
		return plRegistSecDto.onerSearch_VIEW_ID;
	}

    /**
	 * 実行
	 * @return 画面遷移情報
	 */
    public String execute(){
    	// 自由入力の場合
    	if (plRegistSecDto.getTargetYMSelectBox() != null
				&& !plRegistSecDto.getTargetYMSelectBox().equals("999999")) {
    		plRegistSecDto.setTargetYM(plRegistSecDto.getTargetYMSelectBox());
		}

    	// 入力チェック
    	validate();
    	
    	// 該当データ存在チェック
		// ロジック【PLデータの取得】を実行する。
    	plRegistSecDto.setTrnPLInfo(null);
    	plRegistSecDto.setTrnZenPLInfo(null);
    	getPLDataLogic.execute(plRegistSecDto);

		// ①[PLデータ]の結果が０件の場合
		if (plRegistSecDto.getTrnPLInfo() == null) {
            throw new NoResultException("");
		}
    	
		plRegistSecDto.setInitFlg(true);
    	return plContentConfirm_VIEW_ID;
    }

    /**
	 * 条件画面表示モード切替
	 * @return 画面遷移情報
	 */
    public String changeSelectViewMode(){
    	
    	if (plRegistSecDto.getSelectViewMode() == 0) {
    		plRegistSecDto.setSelectViewMode(1);
            
            // オーナーコードをTEMPに保持
            plRegistSecDto.setOnerCdTmp(plRegistSecDto.getOnerCd());
    	}  
    	else if (plRegistSecDto.getSelectViewMode() == 1) {
        	// 必須チェック
        	if (isNull(plRegistSecDto.getOnerCd())) {
        		NotNullException ne = new NotNullException("オーナコード");
        		ne.setHtmlElementName("onerCd");
    			throw ne;
        	}
    		// 文字列長チェック
        	if (!isNull(plRegistSecDto.getOnerCd())
    				&& plRegistSecDto.getOnerCd().getBytes().length > 5) {
        		InvalidInputException ie = new InvalidInputException("オーナーコード");
        		ie.setHtmlElementName("onerCd");
    			throw ie;
    		}
        	// コード欄 半角数字
        	CodeVerifier codeVerifier = new CodeVerifier();
    		if (!isNull(plRegistSecDto.getOnerCd())
    				&& !codeVerifier.validate(plRegistSecDto.getOnerCd())) {
    			InvalidInputException ie = new InvalidInputException("オーナーコード");
        		ie.setHtmlElementName("onerCd");
    			throw ie;
    		}

			// オーナコード存在チェック
			// オーナ名称取得
    		UIGroupTogoOwner uiGroupTogoOwner = uiGroupTogoOwnerDao
					.select(plRegistSecDto.getOnerCd());
    		if (uiGroupTogoOwner == null) {
        		InvalidInputException ie = new InvalidInputException("オーナーコード");
        		ie.setHtmlElementName("onerCd");
    			throw ie;
			} else {
				// ユーザー対応店舗情報の取得
				getUserOnerLogic.execute(plRegistSecDto);

				// ②[[ユーザー対応オーナー]]の結果が0件だがオーナー検索画面からの遷移の場合
				// ・ロジック【PL作成者情報の取得】を実行する。
				// パラメータ選択されたオーナー
				TrnPLAuthorInfo trnPLAuthorInfo = getPLAuthorLogic
						.execute(plRegistSecDto.getOnerCd());
				if (trnPLAuthorInfo == null) {
					trnPLAuthorInfo = new TrnPLAuthorInfo();
				}
				plRegistSecDto.setTrnPLAuthorInfo(trnPLAuthorInfo);

				// ・結果を各項目にセット
				plRegistSecDto
						.setStorePlList(plRegistSecDto.getMsTUserMiseInfoList());
				plRegistSecDto.setHeadOfficePlOnerCd(plRegistSecDto.getOnerCd());
				plRegistSecDto.setHeadOfficePlOnerName(uiGroupTogoOwner.getOnerNameKj());

				// 条件画面表示モード
				plRegistSecDto.setSelectViewMode(0);
                
                // 検索条件クリア → クローズ店チェックボックスの入力状況は常に引き継ぐ
                //clearSearchInfo();
			}
    	}
    	return null;
    }

    /**
     * 対象年月日を選択
     * @return 
     */
    public String changeSelectTargetYM() {
        
        // 店舗情報のプルダウンを取得、設定
        getUserOnerLogic.execute(plRegistSecDto);
        plRegistSecDto.setStorePlList(plRegistSecDto.getMsTUserMiseInfoList());
        return null;
    }
    
    /**
     * 登録状況確認画面へ遷移する。
     * @return
     */
    public String executeStateConfirm() {
        getPlRegistStateDto().setPlYm(getPlRegistSecDto().getTargetYM());
        getPlRegistStateDto().setOwnerCd(getPlRegistSecDto().getOnerCd());
        getPlRegistStateDto().setCloseMiseFlg(getPlRegistSecDto().isCloseMiseFlg());
        getPlRegistStateDto().setMiseLinkFlg(false);
        getPlRegistStateDto().setNavigationCase(plContentForm_VIEW_ID);
        return plRegistSecDto.stateConfirm_VIEW_ID;
    }
    
    /**
     * 検索条件をクリア
     * 前回検索時とオーナーコードが違う場合、クローズ店チェックをOFFにする
     */
    private void clearSearchInfo() {
        if( plRegistSecDto.getOnerCd() != null && 
                plRegistSecDto.getOnerCdTmp()!=null) {
            if(!plRegistSecDto.getOnerCd().equals(plRegistSecDto.getOnerCdTmp())) {
                plRegistSecDto.setCloseMiseFlg(false);
            }
        }
    }
    
    /**
	 * 入力チェック
	 */
    private void validate() {
    	// 必須チェック
    	if (isNull(plRegistSecDto.getOnerCd())) {
    		NotNullException ne = new NotNullException("オーナコード");
    		ne.setHtmlElementName("onerCd");
			throw ne;
    	}
    	if (isNull(plRegistSecDto.getTargetYM())) {
    		NotNullException ne = new NotNullException("対象年月");
    		ne.setHtmlElementName("targetYM");
			throw ne;
    	}
    	if (plRegistSecDto.getPlFlg() == 0) {
        	if (isNull(plRegistSecDto.getStorePlMiseCd())) {
    			throw new NotNullException("店舗P/L");
        	}
    	} else if (plRegistSecDto.getPlFlg() == 1) {
        	if (isNull(plRegistSecDto.getHeadOfficePlOnerCd())) {
    			throw new NotNullException("本社P/L");
        	}
    	}
    	
		// 文字列長チェック
    	if (!isNull(plRegistSecDto.getOnerCd())
				&& plRegistSecDto.getOnerCd().getBytes().length > 5) {
    		InvalidInputException ie = new InvalidInputException("オーナーコード");
    		ie.setHtmlElementName("onerCd");
			throw ie;
		}
		if (!isNull(plRegistSecDto.getTargetYM())
				&& plRegistSecDto.getTargetYM().getBytes().length > 7) {
			InvalidInputException ie = new InvalidInputException("対象年月");
    		ie.setHtmlElementName("targetYM");
			throw ie;
		}

    	// コード欄 半角数字
    	CodeVerifier codeVerifier = new CodeVerifier();
		if (!isNull(plRegistSecDto.getOnerCd())
				&& !codeVerifier.validate(plRegistSecDto.getOnerCd())) {
			InvalidInputException ie = new InvalidInputException("オーナーコード");
    		ie.setHtmlElementName("onerCd");
			throw ie;
		}
		
		// 日付型の欄にYYYY/MMの型で入力されているかチェック
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YM);
        if (!dateVerifier.validate(plRegistSecDto.getTargetYM())) {
        	InvalidInputException ie = new InvalidInputException("対象年月");
    		ie.setHtmlElementName("targetYM");
			throw ie;
        }

        // フォーマット 対象年月
        DateFormatter dateFormatter = new DateFormatter(
				DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
        plRegistSecDto.setTargetYM(dateFormatter.format(plRegistSecDto.getTargetYM(),
				DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YM));

        // フォーマット 年月日
        dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD,
				DateFormatter.PATTERN_SLASH);
        plRegistSecDto.getTrnPLAuthorInfo().setAuthDt(
				dateFormatter.format(plRegistSecDto.getTrnPLAuthorInfo()
						.getAuthDt(), DateFormatter.PATTERN_NORMAL,
						DateFormatter.DATE_TYPE_YMD));
		
		// オーナコード存在チェック
		UIGroupTogoOwner uiGroupTogoOwner = uiGroupTogoOwnerDao
				.select(plRegistSecDto.getOnerCd());
		if (uiGroupTogoOwner == null) {
			InvalidInputException ie = new InvalidInputException("オーナーコード");
			ie.setHtmlElementName("onerCd");
			throw ie;
		}
    }

	/**
	 * 電話番号チェック
	 * @return boolean
	 */
	private boolean checkPhoneNumber(String str){
		if (!isNull(str)) {
	    	String phoneNumStr = "1234567890-";
	    	for (int i=0; i<str.length(); i++) {
	    		char checkChar = str.charAt(i);
	    		if (phoneNumStr.indexOf(checkChar,0)==-1){
	    			return false;
	    		}
	    	}
		}
		return true;
	}

    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
}

