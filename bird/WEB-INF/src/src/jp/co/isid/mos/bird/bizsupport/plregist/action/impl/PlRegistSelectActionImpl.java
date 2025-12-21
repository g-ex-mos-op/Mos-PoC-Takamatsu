/*
 * 作成日: 2006/3/14
 */
package jp.co.isid.mos.bird.bizsupport.plregist.action.impl;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.bizsupport.common.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistStateDto;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLAuthorLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserOnerLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.action.PlRegistSelectAction;
import jp.co.isid.mos.bird.bizsupport.plregist.dto.SessionKeyDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.UIGroupTogoOwner;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

/**
 * P/Lデータ入力　条件画面アクションクラス
 * @author itamoto
 */
public class PlRegistSelectActionImpl implements PlRegistSelectAction {

    private static Logger logger_ = Logger.getLogger(PlRegistSelectActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BBS001A01";
    public static String search_ACTION_ID            = "BBS001A02";
    public static String execute_ACTION_ID           = "BBS001A03";
    public static String changeSelectViewMode_ACTION_ID = "BBS001A12";
    public static String changeSelectTargetYM_ACTION_ID = "BBS001A13";
    

    /* 年月出力月数 */
    private static int NENGETU_DISPLAY_MONTH = 13;

    /* session key 画面保持パラメータ */
    private SessionKeyDto plRegistSessionKeyDto;

    /* P/Lデータ入力用Dto */
    private PlRegistDto plRegistDto;
    /* OwnerSearchDto */
    private OwnerSearchDto ownerSearchDto;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
    
    /* 登録状況確認用Dto */
    private PlRegistStateDto plRegistStateDto;
    
    /* P/Lデータエンティティ */
    private TrnPLInfo trnPLInfo;
    /* 前月P/Lデータエンティティ */
    private TrnZenPLInfo trnZenPLInfo;

    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* 日付情報 */
    private BirdDateInfo birdDateInfo;

    /* ユーザー対応オーナーの検索Logic */
    private GetUserOnerLogic getUserOnerLogic;
    /* PL作成者情報の取得Logic */
    private GetPLAuthorLogic getPLAuthorLogic;

    /* オーナ情報取得Dao */
    UIGroupTogoOwnerDao uiGroupTogoOwnerDao;
    
	/**
	 * session key 画面保持パラメータDTOの設定
	 * @return plRegistSessionKeyDto を戻します。
	 */
	public SessionKeyDto getPlRegistSessionKeyDto() {
		return plRegistSessionKeyDto;
	}
	/**
	 * session key 画面保持パラメータDTOの設定
	 * @param plRegistSessionKeyDto plRegistSessionKeyDto を設定。
	 */
	public void setPlRegistSessionKeyDto(SessionKeyDto plRegistSessionKeyDto) {
		this.plRegistSessionKeyDto = plRegistSessionKeyDto;
	}
    
	/**
	 * P/Lデータ入力用Dtoの設定
	 * @return plRegistDto を戻します。
	 */
	public PlRegistDto getPlRegistDto() {
		return plRegistDto;
	}
	/**
	 * P/Lデータ入力用Dtoの設定
	 * @param plRegistDto plRegistDto を設定。
	 */
	public void setPlRegistDto(PlRegistDto plRegistDto) {
		this.plRegistDto = plRegistDto;
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
	 * P/Lデータエンティティの設定
	 * @return trnPLInfo を戻します。
	 */
	public TrnPLInfo getTrnPLInfo() {
		return trnPLInfo;
	}
	/**
	 * P/Lデータエンティティの設定
	 * @param trnPLInfo trnPLInfo を設定。
	 */
	public void setTrnPLInfo(TrnPLInfo trnPLInfo) {
		this.trnPLInfo = trnPLInfo;
	}
	
	/**
	 * 前月P/Lデータエンティティの設定
	 * @return trnZenPLInfo を戻します。
	 */
	public TrnZenPLInfo getTrnZenPLInfo() {
		return trnZenPLInfo;
	}
	/**
	 * 前月P/Lデータエンティティの設定
	 * @param trnZenPLInfo trnZenPLInfo を設定。
	 */
	public void setTrnZenPLInfo(TrnZenPLInfo trnZenPLInfo) {
		this.trnZenPLInfo = trnZenPLInfo;
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
			plRegistDto = new PlRegistDto();
			trnPLInfo = new TrnPLInfo();
			trnZenPLInfo = new TrnZenPLInfo();
			
			plRegistDto.setUserTypeCd(birdUserInfo.getMstUser().getUserTypeCd());
			plRegistDto.setBirdUserInfo(birdUserInfo);
			plRegistDto.setBirdDateInfo(birdDateInfo);
			
			// ユーザタイプコード　オーナの場合
			if (birdUserInfo.getMstUser().getUserTypeCd().equals("02")) {
				// ユーザ対応オーナーの検索
                
                //対象年月に当日を設定 店舗リストを取得設定
                plRegistDto.setTargetYM(birdDateInfo.getSysDate());
				getUserOnerLogic.execute(birdUserInfo.getUserID(), plRegistDto);
				
				// ①[[ユーザー対応オーナー]]が0件でない場合
				if (plRegistDto.getMsTUserOnerInfo() != null) {
					// ・ロジック【PL作成者情報の取得】を実行する。
					// パラメータ[[ユーザー対応オーナー]].オーナーコード
					TrnPLAuthorInfo trnPLAuthorInfo = getPLAuthorLogic
							.execute(plRegistDto.getMsTUserOnerInfo()
									.getOnerCd());
					if (trnPLAuthorInfo == null) {
						trnPLAuthorInfo = new TrnPLAuthorInfo();
					}
					plRegistDto.setTrnPLAuthorInfo(trnPLAuthorInfo);

					// 作成者情報の「年月日」については当日をデフォルト表示する。
					plRegistDto.getTrnPLAuthorInfo().setAuthDt(birdDateInfo.getSysDate());
					
					// ・結果を各項目にセット
					plRegistDto.setOnerCd(plRegistDto.getMsTUserOnerInfo().getOnerCd());
					plRegistDto.setStorePlList(plRegistDto.getMsTUserMiseInfoList());
					plRegistDto.setHeadOfficePlOnerCd(plRegistDto
							.getMsTUserOnerInfo().getOnerCd());
					plRegistDto.setHeadOfficePlOnerName(plRegistDto
							.getMsTUserOnerInfo().getOnerNameKj());
					// ・「オーナー」フィールド、「検索」ボタンを表示する。
				}
				// ③[[ユーザー対応オーナー]]の結果が0件の場合
				// ・各項目を空でセット
				// ※項目：オーナー・店舗P/L・本社P/L・作成者欄
				
				// 【セッションKey生成】
				String key = plRegistDto._makeSessionKey();
				plRegistDto.setSessionKey(key);
				plRegistSessionKeyDto.setSessionKey(key);

			} else {
				// 条件画面表示モード
				plRegistDto.setSelectViewMode(1);
			}

            // 対象年月取得
            plRegistDto.setTargetYMList(plRegistDto
    				.createTargetYMList(birdDateInfo));
            // 前月をデフォルト設定
            DateFormatter formatter = new DateFormatter();
            String sysMonth = formatter.format(birdDateInfo.getSysDate(),
					DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
            try {
				plRegistDto.setTargetYM(DateManager.getPrevMonth(sysMonth, 1));
				plRegistDto.setTargetYMSelectBox(DateManager.getPrevMonth(sysMonth, 1));
			} catch (Exception e) {
                throw new FtlSystemException("条件画面初期処理");
			}

			// 決算月表示リスト作成
	        plRegistDto.setKessanDtList(plRegistDto.creatKessanDtList());
			
			pullDownMenuDto.setClearFlg(false);
		}
		
		// オーナ選択情報取得
		if (ownerSearchDto.isActionFlag()) {
			plRegistDto.setOnerCd(ownerSearchDto.getOnerCd());
			ownerSearchDto.setActionFlag(false);

			// 条件画面表示モード
			plRegistDto.setSelectViewMode(1);
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
		ownerSearchDto.setNavigationCase(plRegistDto.select_VIEW_ID);
		ownerSearchDto.setInitFlag(true);
		return plRegistDto.onerSearch_VIEW_ID;
	}

    /**
	 * 実行
	 * @return 画面遷移情報
	 */
    public String execute(){
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!plRegistDto.isValidSessionKey(plRegistSessionKeyDto
				.getSessionKey(), plRegistDto.getSessionKey())) {
			return plRegistDto.operationErr_VIEW_ID;
		}

    	// TODO 自由入力の場合
    	if (plRegistDto.getTargetYMSelectBox() != null
				&& !plRegistDto.getTargetYMSelectBox().equals("999999")) {
			plRegistDto.setTargetYM(plRegistDto.getTargetYMSelectBox());
		}
    	
    	// 入力チェック
    	validate();
    	plRegistDto.setInitFlg(true);
    	return plRegistDto.edit_VIEW_ID;
    }

    /**
	 * 条件画面表示モード切替
	 * @return 画面遷移情報
	 */
    public String changeSelectViewMode(){
    	if (plRegistDto.getSelectViewMode() == 0) {
    		plRegistDto.setSelectViewMode(1);
            
            // オーナーコードをTEMPに保持
            plRegistDto.setOnerCdTmp(plRegistDto.getOnerCd());
    	}  
    	else if (plRegistDto.getSelectViewMode() == 1) {
        	// 必須チェック
        	if (isNull(plRegistDto.getOnerCd())) {
        		NotNullException ne = new NotNullException("オーナコード");
        		ne.setHtmlElementName("onerCd");
    			throw ne;
        	}
    		// 文字列長チェック
        	if (!isNull(plRegistDto.getOnerCd())
    				&& plRegistDto.getOnerCd().getBytes().length > 5) {
        		InvalidInputException ie = new InvalidInputException("オーナーコード");
        		ie.setHtmlElementName("onerCd");
    			throw ie;
    		}
        	// コード欄 半角数字
        	CodeVerifier codeVerifier = new CodeVerifier();
    		if (!isNull(plRegistDto.getOnerCd())
    				&& !codeVerifier.validate(plRegistDto.getOnerCd())) {
    			InvalidInputException ie = new InvalidInputException("オーナーコード");
        		ie.setHtmlElementName("onerCd");
    			throw ie;
    		}

			// オーナコード存在チェック
			// オーナ名称取得
    		UIGroupTogoOwner uiGroupTogoOwner = uiGroupTogoOwnerDao
					.select(plRegistDto.getOnerCd());
    		if (uiGroupTogoOwner == null) {
        		InvalidInputException ie = new InvalidInputException("オーナーコード");
        		ie.setHtmlElementName("onerCd");
    			throw ie;
			} else {
				// ユーザー対応店舗情報の取得
				getUserOnerLogic.execute(plRegistDto);

				// ②[[ユーザー対応オーナー]]の結果が0件だがオーナー検索画面からの遷移の場合
				// ・ロジック【PL作成者情報の取得】を実行する。
				// パラメータ選択されたオーナー
				TrnPLAuthorInfo trnPLAuthorInfo = getPLAuthorLogic
						.execute(plRegistDto.getOnerCd());
				if (trnPLAuthorInfo == null) {
					trnPLAuthorInfo = new TrnPLAuthorInfo();
				}
				plRegistDto.setTrnPLAuthorInfo(trnPLAuthorInfo);

				// 作成者情報の「年月日」については当日をデフォルト表示する。
				plRegistDto.getTrnPLAuthorInfo().setAuthDt(birdDateInfo.getSysDate());

				// ・結果を各項目にセット
				plRegistDto
						.setStorePlList(plRegistDto.getMsTUserMiseInfoList());
				plRegistDto.setHeadOfficePlOnerCd(plRegistDto.getOnerCd());
				plRegistDto.setHeadOfficePlOnerName(uiGroupTogoOwner.getOnerNameKj());

				// 条件画面表示モード
				plRegistDto.setSelectViewMode(0);
				
                // 検索条件クリア → クローズ店チェックボックスの入力状況は常に引き継ぐ
                //clearSearchInfo();
                
				// 【セッションKey生成】
				String key = plRegistDto._makeSessionKey();
				plRegistDto.setSessionKey(key);
				plRegistSessionKeyDto.setSessionKey(key);
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
        getUserOnerLogic.execute(plRegistDto);
        plRegistDto.setStorePlList(plRegistDto.getMsTUserMiseInfoList());
        return null;
    }
    
    /**
     * 登録状況確認画面へ遷移する。
     * @return
     */
    public String executeStateConfirm() {
        
        // 入力チェック
        validate();
        
        getPlRegistStateDto().setPlYm(getPlRegistDto().getTargetYM());
        getPlRegistStateDto().setOwnerCd(getPlRegistDto().getOnerCd());
        getPlRegistStateDto().setCloseMiseFlg(getPlRegistDto().isCloseMiseFlg());
        getPlRegistStateDto().setMiseLinkFlg(true);
        getPlRegistStateDto().setNavigationCase(plRegistDto.select_VIEW_ID);
        return plRegistDto.stateConfirm_VIEW_ID;
    }
    
    /**
     * 検索条件をクリア
     * 前回検索時とオーナーコードが違う場合、クローズ店チェックをOFFにする
     */
    private void clearSearchInfo() {
        if( plRegistDto.getOnerCd() != null && 
                plRegistDto.getOnerCdTmp()!=null) {
            if(!plRegistDto.getOnerCd().equals(plRegistDto.getOnerCdTmp())) {
                plRegistDto.setCloseMiseFlg(false);
            }
        }
    }
    
    /**
	 * 入力チェック
	 */
    private void validate() {
    	// 必須チェック
    	if (isNull(plRegistDto.getOnerCd())) {
    		NotNullException ne = new NotNullException("オーナコード");
    		ne.setHtmlElementName("onerCd");
			throw ne;
    	}
    	if (isNull(plRegistDto.getTargetYM())) {
    		NotNullException ne = new NotNullException("対象年月");
    		ne.setHtmlElementName("targetYM");
			throw ne;
    	}
    	if (plRegistDto.getPlFlg() == 0) {
        	if (isNull(plRegistDto.getStorePlMiseCd())) {
    			throw new NotNullException("店舗P/L");
        	}
    	} else if (plRegistDto.getPlFlg() == 1) {
        	if (isNull(plRegistDto.getHeadOfficePlOnerCd())) {
    			throw new NotNullException("本社P/L");
        	}
    	}
    	if (isNull(plRegistDto.getTrnPLAuthorInfo().getAuthDt())) {
    		NotNullException ne = new NotNullException("作成者 年月日");
    		ne.setHtmlElementName("authDt");
			throw ne;
    	}
    	if (isNull(plRegistDto.getTrnPLAuthorInfo().getAuthPhoneNum())) {
    		NotNullException ne = new NotNullException("作成者 電話番号");
    		ne.setHtmlElementName("authPhoneNum");
			throw ne;
    	}
//    	if (isNull(plRegistDto.getTrnPLAuthorInfo().getAuthOther())) {
//    		NotNullException ne = new NotNullException("作成者 会計事務所等");
//    		ne.setHtmlElementName("authOther");
//			throw ne;
//    	}
    	if (isNull(plRegistDto.getTrnPLAuthorInfo().getAuthorName())) {
    		NotNullException ne = new NotNullException("作成者 氏名");
    		ne.setHtmlElementName("author");
			throw ne;
    	}
    	if (isNull(plRegistDto.getTrnPLAuthorInfo().getKessanDt())) {
    		NotNullException ne = new NotNullException("作成者 決算月");
    		ne.setHtmlElementName("KessanDt");
			throw ne;
    	}
    	
		// 文字列長チェック
    	if (!isNull(plRegistDto.getOnerCd())
				&& plRegistDto.getOnerCd().getBytes().length > 5) {
    		InvalidInputException ie = new InvalidInputException("オーナーコード");
    		ie.setHtmlElementName("onerCd");
			throw ie;
		}
		if (!isNull(plRegistDto.getTargetYM())
				&& plRegistDto.getTargetYM().getBytes().length > 7) {
			InvalidInputException ie = new InvalidInputException("対象年月");
    		ie.setHtmlElementName("targetYM");
			throw ie;
		}
		if (!isNull(plRegistDto.getTrnPLAuthorInfo().getAuthDt())
				&& plRegistDto.getTrnPLAuthorInfo().getAuthDt().getBytes().length > 10) {
			InvalidInputException ie = new InvalidInputException("作成者 年月日");
    		ie.setHtmlElementName("authDt");
			throw ie;
		}
		if (!isNull(plRegistDto.getTrnPLAuthorInfo().getAuthPhoneNum())
				&& plRegistDto.getTrnPLAuthorInfo().getAuthPhoneNum()
						.getBytes().length > 15) {
			InvalidInputException ie = new InvalidInputException("作成者 電話番号");
    		ie.setHtmlElementName("authPhoneNum");
			throw ie;
		}
		if (!isNull(plRegistDto.getTrnPLAuthorInfo().getAuthOther())
				&& plRegistDto.getTrnPLAuthorInfo().getAuthOther().getBytes().length > 40) {
			InvalidInputException ie = new InvalidInputException("作成者 会計事務所等");
    		ie.setHtmlElementName("authOther");
			throw ie;
		}
		if (!isNull(plRegistDto.getTrnPLAuthorInfo().getAuthorName())
				&& plRegistDto.getTrnPLAuthorInfo().getAuthorName().getBytes().length > 40) {
			InvalidInputException ie = new InvalidInputException("作成者 氏名");
    		ie.setHtmlElementName("author");
			throw ie;
		}
		if (!isNull(plRegistDto.getTrnPLAuthorInfo().getKessanDt())
				&& plRegistDto.getTrnPLAuthorInfo().getKessanDt().getBytes().length > 2) {
			InvalidInputException ie = new InvalidInputException("作成者 決算月");
    		ie.setHtmlElementName("KessanDt");
			throw ie;
		}

    	// コード欄 半角数字
    	CodeVerifier codeVerifier = new CodeVerifier();
		if (!isNull(plRegistDto.getOnerCd())
				&& !codeVerifier.validate(plRegistDto.getOnerCd())) {
			InvalidInputException ie = new InvalidInputException("オーナーコード");
    		ie.setHtmlElementName("onerCd");
			throw ie;
		}

		if (!isNull(plRegistDto.getTrnPLAuthorInfo().getKessanDt())
				&& !codeVerifier.validate(plRegistDto.getTrnPLAuthorInfo().getKessanDt())) {
			InvalidInputException ie = new InvalidInputException("作成者 決算月");
    		ie.setHtmlElementName("KessanDt");
			throw ie;
		}

		// コード欄 半角英数字
		codeVerifier = new CodeVerifier(true);
		if (!checkPhoneNumber(plRegistDto.getTrnPLAuthorInfo().getAuthPhoneNum().trim())) {
//		if (!isNull(plRegistDto.getTrnPLAuthorInfo().getAuthPhoneNum())
//				&& !codeVerifier.validate(plRegistDto.getTrnPLAuthorInfo()
//						.getAuthPhoneNum().trim())) {
			InvalidInputException ie = new InvalidInputException("作成者 電話番号");
    		ie.setHtmlElementName("authPhoneNum");
			throw ie;
		}

    	// 文字型の欄に文字以外の型が入力されていないかチェック
    	DefaultJapaneseVerifier defaultJapaneseVerifier = new DefaultJapaneseVerifier();
    	if (!isNull(plRegistDto.getTrnPLAuthorInfo().getAuthOther())
				&& !defaultJapaneseVerifier.validate(plRegistDto.getTrnPLAuthorInfo()
				.getAuthOther())) {
    		InvalidInputException ie = new InvalidInputException("作成者 会計事務所等");
    		ie.setHtmlElementName("authOther");
			throw ie;
		}
    	if (!defaultJapaneseVerifier.validate(plRegistDto.getTrnPLAuthorInfo()
				.getAuthorName())) {
    		InvalidInputException ie = new InvalidInputException("作成者 氏名");
    		ie.setHtmlElementName("author");
			throw ie;
		}

		// 日付型の欄にYYYY/MMの型で入力されているかチェック
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YM);
        if (!dateVerifier.validate(plRegistDto.getTargetYM())) {
        	InvalidInputException ie = new InvalidInputException("対象年月");
    		ie.setHtmlElementName("targetYM");
			throw ie;
        }

        // 日付型の欄にYYYY/MM/DDの型で入力されているかチェック
        dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        if (!dateVerifier.validate(plRegistDto.getTrnPLAuthorInfo().getAuthDt())) {
        	InvalidInputException ie = new InvalidInputException("作成者 年月日");
    		ie.setHtmlElementName("authDt");
			throw ie;
        }
        
        // フォーマット 対象年月
        DateFormatter dateFormatter = new DateFormatter(
				DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
		plRegistDto.setTargetYM(dateFormatter.format(plRegistDto.getTargetYM(),
				DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YM));

        // フォーマット 年月日
        dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD,
				DateFormatter.PATTERN_SLASH);
		plRegistDto.getTrnPLAuthorInfo().setAuthDt(
				dateFormatter.format(plRegistDto.getTrnPLAuthorInfo()
						.getAuthDt(), DateFormatter.PATTERN_NORMAL,
						DateFormatter.DATE_TYPE_YMD));
		
		// オーナコード存在チェック
		UIGroupTogoOwner uiGroupTogoOwner = uiGroupTogoOwnerDao
				.select(plRegistDto.getOnerCd());
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
    public PlRegistStateDto getPlRegistStateDto() {
        return plRegistStateDto;
    }
    public void setPlRegistStateDto(PlRegistStateDto plRegistStateDto) {
        this.plRegistStateDto = plRegistStateDto;
    }
}

