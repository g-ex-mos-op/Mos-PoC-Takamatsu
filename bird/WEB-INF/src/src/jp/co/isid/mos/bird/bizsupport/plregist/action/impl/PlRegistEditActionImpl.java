/*
 * 作成日: 2006/3/14
 */
package jp.co.isid.mos.bird.bizsupport.plregist.action.impl;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

import jp.co.isid.mos.bird.bizsupport.common.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistStateDto;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLAuthorLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserOnerLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetZenPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.action.PlRegistEditAction;
import jp.co.isid.mos.bird.bizsupport.plregist.dto.SessionKeyDto;
import jp.co.isid.mos.bird.bizsupport.plregist.dto.TrnPLInfoDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.MSTUserMiseInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.UIGroupTogoOwner;
import jp.co.isid.mos.bird.bizsupport.plregist.logic.CheckPLDataTabBetweenTabLogic;
import jp.co.isid.mos.bird.bizsupport.plregist.logic.CheckPLTabDataLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * P/Lデータ入力　編集画面アクションクラス
 * @author itamoto
 */
public class PlRegistEditActionImpl implements PlRegistEditAction {

    private static Logger logger_ = Logger.getLogger(PlRegistEditActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BBS001A04";
    public static String changeTab_ACTION_ID         = "BBS001A05";
    public static String execute_ACTION_ID           = "BBS001A06";
    public static String cancel_ACTION_ID            = "BBS001A07";
    
    private static int NENGETU_DISPLAY_MONTH = 13;

    /* session key 画面保持パラメータ */
    private SessionKeyDto plRegistSessionKeyDto;

    /* P/Lデータ入力用Dto */
    private PlRegistDto plRegistDto;
    /* P/Lデータエンティティ */
    private TrnPLInfo trnPLInfo;

    /* P/LデータDto String版 画面表示用 */
    private TrnPLInfoDto trnPLInfoDto;
    
    /* CSV一括取込DTO */
    private LumpTakeInDto lumpTakeInDto;
    
    /* 登録状況確認DTO */
    private PlRegistStateDto plRegistStateDto;

    /* 前月P/Lデータエンティティ */
    private TrnZenPLInfo trnZenPLInfo;
    /* P/Lデータ取得ロジック */
    private GetPLDataLogic getPLDataLogic;
    /* 前月P/Lデータ取得ロジック */
    private GetZenPLDataLogic getZenPLDataLogic;
    /* タブ内容チェックロジック */
    private CheckPLTabDataLogic checkPLTabDataLogic;
    /* タブ間の整合性チェック */
    private CheckPLDataTabBetweenTabLogic checkPLDataTabBetweenTabLogic;

    /* 編集画面表示時のタブ指定 
     * デフォルト：0、月次損益計算書：1、内訳：2、借入金：3 */
    private int selectedTab;
    
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
	 * P/LデータDto String版 画面表示用の設定
	 * @return trnPLInfoDto を戻します。
	 */
	public TrnPLInfoDto getTrnPLInfoDto() {
		return trnPLInfoDto;
	}
	/**
	 * P/LデータDto String版 画面表示用の設定
	 * @param trnPLInfoDto trnPLInfoDto を設定。
	 */
	public void setTrnPLInfoDto(TrnPLInfoDto trnPLInfoDto) {
		this.trnPLInfoDto = trnPLInfoDto;
	}

    /**
     * CSV一括取込DTO 
     * @return lumpTakeInDto を戻します。
     */
    public LumpTakeInDto getLumpTakeInDto() {
        return lumpTakeInDto;
    }
    /**
     * CSV一括取込DTO
     * @param LumpTakeInDto lumpTakeInDto を設定。
     */
    public void setLumpTakeInDto(LumpTakeInDto lumpTakeInDto) {
        this.lumpTakeInDto = lumpTakeInDto;
    }
    
    /**
     * 登録状況確認DTO 
     * @return plRegistStateDto を戻します。
     */
    public PlRegistStateDto getPlRegistStateDto() {
        return plRegistStateDto;
    }
    /**
     * 登録状況確認DTO
     * @param PlRegistStateDto plRegistStateDto を設定。
     */
    public void setPlRegistStateDto(PlRegistStateDto plRegistStateDto) {
        this.plRegistStateDto = plRegistStateDto;
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
	 * 前月P/Lデータ取得ロジックの設定
	 * @return getZenPLDataLogic を戻します。
	 */
	public GetZenPLDataLogic getGetZenPLDataLogic() {
		return getZenPLDataLogic;
	}
	/**
	 * 前月P/Lデータ取得ロジックの設定
	 * @param getZenPLDataLogic getZenPLDataLogic を設定。
	 */
	public void setGetZenPLDataLogic(GetZenPLDataLogic getZenPLDataLogic) {
		this.getZenPLDataLogic = getZenPLDataLogic;
	}

	/**
	 * タブ内容チェックロジックの設定
	 * @return checkPLTabDataLogic を戻します。
	 */
	public CheckPLTabDataLogic getCheckPLTabDataLogic() {
		return checkPLTabDataLogic;
	}
	/**
	 * タブ内容チェックロジックの設定
	 * @param checkPLTabDataLogic checkPLTabDataLogic を設定。
	 */
	public void setCheckPLTabDataLogic(CheckPLTabDataLogic checkPLTabDataLogic) {
		this.checkPLTabDataLogic = checkPLTabDataLogic;
	}

	/**
	 * タブ間の整合性チェックの設定
	 * @return checkPLDataTabBetweenTabLogic を戻します。
	 */
	public CheckPLDataTabBetweenTabLogic getCheckPLDataTabBetweenTabLogic() {
		return checkPLDataTabBetweenTabLogic;
	}
	/**
	 * タブ間の整合性チェックの設定
	 * @param checkPLDataTabBetweenTabLogic checkPLDataTabBetweenTabLogic を設定。
	 */
	public void setCheckPLDataTabBetweenTabLogic(
			CheckPLDataTabBetweenTabLogic checkPLDataTabBetweenTabLogic) {
		this.checkPLDataTabBetweenTabLogic = checkPLDataTabBetweenTabLogic;
	}

	/**
	 * 編集画面表示時のタブ指定の設定
	 * @return selectedTab を戻します。
	 */
	public int getSelectedTab() {
		return selectedTab;
	}
	/**
	 * 編集画面表示時のタブ指定の設定
	 * @param selectedTab selectedTab を設定。
	 */
	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
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
		
		// WARNINGメッセージセット
		if (!plRegistDto.isPlCsvFlg() && !plRegistDto.isInitFlg()) {
			plRegistDto.setWarningMsg(trnPLInfo);
		}
        
		// 外部（PLデータCSV一括取込画面）より遷移してきた場合
		if (plRegistDto.isPlCsvFlg()) {
			// エンティティ初期化
			setTrnPLInfo(new TrnPLInfo());
			setTrnZenPLInfo(new TrnZenPLInfo());
			// タブの初期化
			plRegistDto.setSelectedTab(1);

			// 整合性エラー参考値の初期化
			plRegistDto.setUriageSoRiekiReference(null);
			plRegistDto.setKeihiShokeiReference(null);
			plRegistDto.setShokyakuRiekiReference(null);
			plRegistDto.setRiekiReference(null);
			
			// PLデータ入力条件画面のデータの取得処理
			loadPlRegistSelectViewData();
			
			// 渡されたパラメータを各項目にセット
			setTrnPLInfo(plRegistDto.getTrnPLInfo());
			// ロジック【前月PLデータの取得】を実行する。
			getZenPLDataLogic.execute(plRegistDto);
			if (plRegistDto.getTrnZenPLInfo() == null) {
				setDefaultZenPlData();
			} else {
				setTrnZenPLInfo(plRegistDto.getTrnZenPLInfo());
			}
			
			// 比率の設定
			setRatio();

			// 渡されたパラメータより条件画面選択状態を復元
			plRegistDto.setOnerCd(trnPLInfo.getOnerCd());
			loadPlRegistSelectViewOnerData();
			
			// 渡されたパラメータより条件画面選択状態を復元
			
			// P/Lデータ登録後の処理実行フラグON
			plRegistDto.setPlCsvAfterCommitExeFlg(true);
			plRegistDto.setPlCsvFlg(false);

			// 画面表示用Dtoにデータセット
			convertTrnPlInfoToString();

	        // エラー情報clear＆設定
	    	trnPLInfo.getPlDataErrorInfo().clear();
	        trnPLInfoDto.setTrnPLInfo(trnPLInfo);
			// 未設定値に「0」をセット
			trnPLInfoDto.fillBlankItem();

	    	// 月次損益計算書 入力チェック
	    	plRegistDto.setSelectedTab(1);
			checkPLTabDataLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto,
					trnPLInfoDto);
	    	// エラー存在チェック
	    	if (trnPLInfo.getPlDataErrorInfo().isExistError()) {
				plRegistDto.setWarningMsg(trnPLInfo);
	        	return null;
	    	}

	    	// 内訳 入力チェック
			plRegistDto.setSelectedTab(2);
			checkPLTabDataLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto,
					trnPLInfoDto);
	    	// エラー存在チェック
	    	if (trnPLInfo.getPlDataErrorInfo().isExistError()) {
				plRegistDto.setWarningMsg(trnPLInfo);
	        	return null;
	    	}

	    	// 借入金（メモ含む） 入力チェック
			plRegistDto.setSelectedTab(3);
			checkPLTabDataLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto,
					trnPLInfoDto);
	    	// エラー存在チェック
	    	if (trnPLInfo.getPlDataErrorInfo().isExistError()) {
				plRegistDto.setWarningMsg(trnPLInfo);
	        	return null;
	    	}

	    	// 画面表示Dtoよりデータ取得
	    	convertTrnPlInfoToBigDecimal();
	    	// タブ間の整合性チェック
			plRegistDto.setWarningMsg(trnPLInfo);
			checkPLDataTabBetweenTabLogic.execute(trnPLInfo, trnZenPLInfo,
					plRegistDto);

	    	// タブ指定をデフォルトに戻す
	    	plRegistDto.setSelectedTab(1);
		}

		// 通常初期処理（PLデータ入力の条件画面より遷移してきた場合）
		if (plRegistDto.isInitFlg()) {
			// エンティティ初期化
			setTrnPLInfo(new TrnPLInfo());
			setTrnZenPLInfo(new TrnZenPLInfo());
			// タブの初期化
			plRegistDto.setSelectedTab(1);
			// 整合性エラー参考値の初期化
			plRegistDto.setUriageSoRiekiReference(null);
			plRegistDto.setKeihiShokeiReference(null);
			plRegistDto.setShokyakuRiekiReference(null);
			plRegistDto.setRiekiReference(null);
	
			// ロジック【PLデータの取得】を実行する。
			getPLDataLogic.execute(plRegistDto);

			// ①[PLデータ]の結果が０件の場合
			if (plRegistDto.getTrnPLInfo() == null) {
				if (plRegistDto.getTrnZenPLInfo() == null) {
					setDefaultZenPlData();
				} else {
					setTrnZenPLInfo(plRegistDto.getTrnZenPLInfo());
				}

				// [POS前月売上].売上高を、編集画面の売上高項目にセットし、他は空(0)で作成
				trnPLInfo
						.setUriagedaka((plRegistDto.getTrnPosZenUriage() == null) ? BigDecimal
								.valueOf(0)
								: plRegistDto.getTrnPosZenUriage().getUriage());

				// 条件画面入力情報セット
				trnPLInfo.setPlYm(plRegistDto.getTargetYM());
				// 本社P/Lの場合
				if (plRegistDto.getPlFlg() == 1) {
					trnPLInfo.setPlType("0");
					trnPLInfo.setMiseCd(plRegistDto.getHeadOfficePlOnerCd());
					trnPLInfo.setMiseNameKj(plRegistDto.getHeadOfficePlOnerName());
                    plRegistDto.setMiseCloseDate("");//クローズ店クリア
				}
				// 店舗P/Lの場合
				else if (plRegistDto.getPlFlg() == 0) {
					String miseNameKj = null;
		            for (Iterator i = plRegistDto.getStorePlList().iterator(); i.hasNext();) {
		            	MSTUserMiseInfo entity = (MSTUserMiseInfo) i.next();
		            	if (entity.getMiseCd().equals(plRegistDto.getStorePlMiseCd())) {
		            		miseNameKj = entity.getMiseNameKj();
		            		break;
		            	}
		            }
					trnPLInfo.setPlType("1");
					trnPLInfo.setMiseCd(plRegistDto.getStorePlMiseCd());
					trnPLInfo.setMiseNameKj(miseNameKj);
				}
                
				// オーナコード&企業コード設定
				trnPLInfo.setOnerCd(plRegistDto.getOnerCd());
				trnPLInfo.setCompanyCd("00");
				
				// 予備欄を設定デフォルト０
				trnPLInfo.setYobi(BigDecimal.valueOf(0));
			}
			// ②[PLデータ]の結果がある場合
			// [PLデータ]の結果を編集画面の各項目にセット
			else {
				setTrnPLInfo(plRegistDto.getTrnPLInfo());
				if (plRegistDto.getTrnZenPLInfo() == null) {
					setDefaultZenPlData();
				} else {
					setTrnZenPLInfo(plRegistDto.getTrnZenPLInfo());
				}
			}
			// 作成者情報セット
			trnPLInfo.setAuthDt(plRegistDto.getTrnPLAuthorInfo().getAuthDt());
			trnPLInfo.setAuthPhoneNum(plRegistDto.getTrnPLAuthorInfo()
					.getAuthPhoneNum());
			trnPLInfo.setAuthOther(plRegistDto.getTrnPLAuthorInfo()
					.getAuthOther());
			trnPLInfo.setAuthorName(plRegistDto.getTrnPLAuthorInfo().getAuthorName());
			plRegistDto.setKessanDt(plRegistDto.getTrnPLAuthorInfo().getKessanDt());
			
            //店クローズ日の設定
            getGetUserOnerLogic().getMiseCloseDt(getPlRegistDto(),trnPLInfo.getMiseCd());
            
			// 画面表示用Dtoにデータセット
			convertTrnPlInfoToString();
			// 未設定値に「0」をセット
			trnPLInfoDto.fillBlankItem();
			
			// エラー情報設定
	        trnPLInfoDto.setTrnPLInfo(trnPLInfo);

			// ４．以下の項目設定を行う
			// 比率の設定
			setRatio();
			plRegistDto.setInitFlg(false);
		}
        return null;
    }

	/**
	 * 外部（PLデータCSV一括取込画面）より遷移してきた場合
	 * PLデータ入力条件画面のデータの取得処理
	 * plRegistSelectActionより、
	 * initialize()
	 * private GetUserOnerLogic getUserOnerLogic;
	 */
	private void loadPlRegistSelectViewData() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
		HttpServletRequest request = (HttpServletRequest) container
				.getComponent("request");
		BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
				.getAttribute("birdUserInfo");
		BirdDateInfo birdDateInfo = (BirdDateInfo) request.getSession()
		.getAttribute("birdDateInfo");

		plRegistDto.setUserTypeCd(birdUserInfo.getMstUser().getUserTypeCd());
		plRegistDto.setBirdUserInfo(birdUserInfo);
		plRegistDto.setBirdDateInfo(birdDateInfo);
        
        // 対象年月取得
        plRegistDto.setTargetYMList(plRegistDto
                .createTargetYMList(birdDateInfo));

        try {
            // PLデータCSV一括取込に対象日付が設定されている
            if (!isNull(getLumpTakeInDto().getPlYm())) {
                String sysMonth = getLumpTakeInDto().getPlYm();
                plRegistDto.setTargetYM(sysMonth);
                plRegistDto.setTargetYMSelectBox(sysMonth);
            }
            // PLデータ入力・内容確認に対象日付が設定されている
            else if (!isNull(getPlRegistStateDto().getPlYm())) {
                String sysMonth = getPlRegistStateDto().getPlYm();
                plRegistDto.setTargetYM(sysMonth);
                plRegistDto.setTargetYMSelectBox(sysMonth);
                plRegistDto.setCloseMiseFlg(getPlRegistStateDto().isCloseMiseFlg());
            }
            else {
                // 前月をデフォルト設定
                DateFormatter formatter = new DateFormatter();
                String sysMonth = formatter.format(birdDateInfo.getSysDate(),
                        DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
                plRegistDto.setTargetYM(DateManager.getPrevMonth(sysMonth, 1));
                plRegistDto.setTargetYMSelectBox(DateManager.getPrevMonth(sysMonth, 1));
            }
            
        } catch (Exception e) {
            throw new FtlSystemException("条件画面初期処理");
        }

        // 決算月表示リスト作成
        plRegistDto.setKessanDtList(plRegistDto.creatKessanDtList());
        
		
		// ユーザタイプコード　オーナの場合
		if (birdUserInfo.getMstUser().getUserTypeCd().equals("02")) {
			// ユーザ対応オーナーの検索
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


	}
	
	/**
	 * 条件画面選択状態復元
	 */
	private void loadPlRegistSelectViewOnerData(){
        S2Container container = SingletonS2ContainerFactory.getContainer();
		HttpServletRequest request = (HttpServletRequest) container
				.getComponent("request");
		BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
				.getAttribute("birdUserInfo");
		BirdDateInfo birdDateInfo = (BirdDateInfo) request.getSession()
		.getAttribute("birdDateInfo");

		if (plRegistDto.getSelectViewMode() == 1) {
        	// 必須チェック
        	if (isNull(plRegistDto.getOnerCd())) {
        		NotNullException ne = new NotNullException("オーナーコード");
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
			}
    	}
    	
		// 渡されたパラメータより条件画面選択状態を復元
    	if (plRegistDto.getPlFlg() == 0) {
    		plRegistDto.setStorePlMiseCd(trnPLInfo.getMiseCd());
    	}
    	plRegistDto.setPlFlg((trnPLInfo.getPlType().equals("0")) ? 1 : 0);
    	plRegistDto.setTargetYM(trnPLInfo.getPlYm());
    	
		// 【セッションKey生成】
		String key = plRegistDto._makeSessionKey();
		plRegistDto.setSessionKey(key);
		plRegistSessionKeyDto.setSessionKey(key);

    	// P/L作成者情報
    	TrnPLAuthorInfo trnPLAuthorInfo = new TrnPLAuthorInfo();
    	trnPLAuthorInfo.setCompanyCd(trnPLInfo.getCompanyCd());
    	trnPLAuthorInfo.setOnerCd(trnPLInfo.getOnerCd());

    	trnPLAuthorInfo.setAuthDt(trnPLInfo.getAuthDt());
    	trnPLAuthorInfo.setAuthorName(trnPLInfo.getAuthorName());
    	trnPLAuthorInfo.setAuthOther(trnPLInfo.getAuthOther());
    	trnPLAuthorInfo.setAuthPhoneNum(trnPLInfo.getAuthPhoneNum());
    	trnPLAuthorInfo.setKessanDt(plRegistDto.getKessanDt());
    	plRegistDto.setTrnPLAuthorInfo(trnPLAuthorInfo);
	}

	/**
	 * 比率の設定
	 */
	private void setRatio() {
		// ４．以下の項目設定を行う
		// 売上高比率にはデフォルトで100%を表示
		plRegistDto.setUriagedakaRatio(BigDecimal.valueOf(100));

		// 売上原価比率には売上原価/売上高*100の小数点第一位まで
		BigDecimal sum = new BigDecimal("0.0");
		if (trnPLInfo.getUriagegenka() == null
				|| trnPLInfo.getUriagedaka() == null
				|| trnPLInfo.getUriagegenka().equals(BigDecimal.valueOf(0))
				|| trnPLInfo.getUriagedaka().equals(BigDecimal.valueOf(0))) {
			plRegistDto.setUriagegenkaRatio(BigDecimal.valueOf(0));
		} else {
			plRegistDto.setUriagegenkaRatio(sum.add(
					trnPLInfo.getUriagegenka()
							.multiply(BigDecimal.valueOf(100))).divide(
					trnPLInfo.getUriagedaka(), BigDecimal.ROUND_DOWN));
		}
		
		// 売上総利益比率には売上総利益/売上高*100の小数点第一位まで
		if (trnPLInfo.getUriageSoRieki() == null
				|| trnPLInfo.getUriagedaka() == null
				|| trnPLInfo.getUriageSoRieki().equals(BigDecimal.valueOf(0))
				|| trnPLInfo.getUriagedaka().equals(BigDecimal.valueOf(0))) {
			plRegistDto.setUriageSoRiekiRatio(BigDecimal.valueOf(0));
		} else {
			plRegistDto.setUriageSoRiekiRatio(sum.add(
					trnPLInfo.getUriageSoRieki().multiply(
							BigDecimal.valueOf(100))).divide(
					trnPLInfo.getUriagedaka(), BigDecimal.ROUND_DOWN));
		}
	}
	
    /**
     * タブ切り替え
     * @return 画面遷移情報
     */
    public String changeTab() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!plRegistDto.isValidSessionKey(plRegistSessionKeyDto
				.getSessionKey(), plRegistDto.getSessionKey())) {
			return plRegistDto.operationErr_VIEW_ID;
		}

        // エラー情報clear
    	trnPLInfo.getPlDataErrorInfo().clear();
    	// 未設定値に「0」をセット
		trnPLInfoDto.fillBlankItem();
        
    	// 入力チェック
        plRegistDto.setReferenceNull();
        plRegistDto.setErrSwitchFlg(true);
    	checkPLTabDataLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto, trnPLInfoDto);
        
    	// エラー有無チェック
        if(checkDefaultInput(trnPLInfo, trnPLInfoDto, plRegistDto, selectedTab)) {
            // ワーニング情報取得
            plRegistDto.setErrSwitchFlg(false);
            checkPLTabDataLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto, trnPLInfoDto);
            return null;
        }
        
        // タブ変更データ移行
        setDefaultTabDate(trnPLInfoDto);
        
    	// 編集画面表示時のタブ指定
    	plRegistDto.setSelectedTab(selectedTab);

    	// ワーニング情報取得
        plRegistDto.setErrSwitchFlg(false);
    	checkPLTabDataLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto, trnPLInfoDto);
    	
    	// 比率の設定
		setRatio();
    	return null;
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

        // エラー情報clear
    	trnPLInfo.getPlDataErrorInfo().clear();
		// 未設定値に「0」をセット
		trnPLInfoDto.fillBlankItem();
        
        // タブ変更データ移行
        setDefaultTabDate(trnPLInfoDto);
        
		// ワーニング情報取得
        plRegistDto.setReferenceNull();
		plRegistDto.setErrSwitchFlg(false);
		checkPLTabDataLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto, trnPLInfoDto);
        
        // エラー存在チェック
        if (trnPLInfo.getPlDataErrorInfo().isExistError()) {
            plRegistDto.setSelectedTab(trnPLInfo.getPlDataErrorInfo().getErrorTabIndex());
            return null;
        }
        
        // 画面表示Dtoよりデータ取得
        convertTrnPlInfoToBigDecimal();
        
        // タブ間の整合性チェック
        checkPLDataTabBetweenTabLogic.execute(trnPLInfo, trnZenPLInfo, plRegistDto);
        
		// タブ指定をデフォルトに戻す
    	plRegistDto.setSelectedTab(1);
		// 比率の設定
		setRatio();
    	return plRegistDto.confirm_VIEW_ID;
    }

    /**
	 * 戻る
	 * @return 画面遷移情報
	 */
    public String cancel(){
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!plRegistDto.isValidSessionKey(plRegistSessionKeyDto
				.getSessionKey(), plRegistDto.getSessionKey())) {
			return plRegistDto.operationErr_VIEW_ID;
		}
    	return plRegistDto.select_VIEW_ID;
    }

    /**
	 * デフォルト前月P/Lデータ設定
	 * @return 画面遷移情報
	 */
    private void setDefaultZenPlData() {
    	// 内訳
    	trnZenPLInfo.setGenzairyoZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfo.setYasaiZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfo.setHouzaiZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfo.setBuppanZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfo.setTouZaikoKei(BigDecimal.valueOf(0));

    	// 借入金(メモ含む）
    	trnZenPLInfo.setKashiireZandaka(BigDecimal.valueOf(0));
    	trnZenPLInfo.setKappuZandaka(BigDecimal.valueOf(0));
    	trnZenPLInfo.setLeaseZandaka(BigDecimal.valueOf(0));
    	trnZenPLInfo.setTouZandakaKei(BigDecimal.valueOf(0));
    }
    
    
    /**
     * エラー有無チェック
     * 
     * ※月次損益タブからの移動時にはエラーによるタブ移動制御は行わない
     * 　ワーニングも同様
     * ※内訳タブからの移動時にエラーがある場合にはタブ移動はできない
     * 　不整合チェックはエラー扱い
     * 　リミットチェックはワーニング扱い
     * 　ワーニングはタブ移動制御なし
     * ※借入金タブからの移動時にエラーがある場合にはタブ移動はできない
     * 　不整合チェックはエラーではなくワーニング扱い
     * 　リミットチェックはワーニング扱い
     * 　ワーニングはタブ移動制御なし
     * 
     * @param TrnPLInfo P/Lデータエンティティ
     * @param TrnPLInfoDto P/LデータDto
     * @param PlRegistDto P/Lデータ入力用Dto
     * @param int 移動先タブインデックス 
     * @return boolean true:エラーあり　false:エラーなし
     */
    private boolean checkDefaultInput(TrnPLInfo trnPLInfo, TrnPLInfoDto trnPLInfoDto, PlRegistDto plRegistDto,int selectedTab) {
        
        boolean chkFlg = false;
        
        if (plRegistDto.getSelectedTab() == 0) {
            chkFlg = true;
        }
        
        else if (plRegistDto.getSelectedTab() == 1) {
            ;
        }
        
        else if (plRegistDto.getSelectedTab() == 2) {
            if (trnPLInfo.getPlDataErrorInfo().isExistError())
                chkFlg = true;
        }
        
        else if (plRegistDto.getSelectedTab() == 3) {
            if (trnPLInfo.getPlDataErrorInfo().isExistError())
                chkFlg = true;            
        }
        
        return chkFlg;
    }
    
    
    /**
     * P/Lデータ変換処理(BigDecimal→String)
     * エンティティ情報を画面表示用Dtoに反映
     * @param trnPLInfo
     * @return
     */
    private void convertTrnPlInfoToString() {
    	// 編集画面の数値項目
        trnPLInfoDto.setUriagedaka((trnPLInfo.getUriagedaka() == null) ? null : trnPLInfo.getUriagedaka().toString());
        trnPLInfoDto.setUriagegenka((trnPLInfo.getUriagegenka() == null) ? null : trnPLInfo.getUriagegenka().toString());
        trnPLInfoDto.setUriageSoRieki((trnPLInfo.getUriageSoRieki() == null) ? null : trnPLInfo.getUriageSoRieki().toString());
        trnPLInfoDto.setSalary((trnPLInfo.getSalary() == null) ? null : trnPLInfo.getSalary().toString());
        trnPLInfoDto.setYachin((trnPLInfo.getYachin() == null) ? null : trnPLInfo.getYachin().toString());
        trnPLInfoDto.setSuikouHi((trnPLInfo.getSuikouHi() == null) ? null : trnPLInfo.getSuikouHi().toString());
        trnPLInfoDto.setRoyalty((trnPLInfo.getRoyalty() == null) ? null : trnPLInfo.getRoyalty().toString());
        trnPLInfoDto.setTesuryo((trnPLInfo.getTesuryo() == null) ? null : trnPLInfo.getTesuryo().toString());
        trnPLInfoDto.setKoukoku((trnPLInfo.getKoukoku() == null) ? null : trnPLInfo.getKoukoku().toString());
        trnPLInfoDto.setShoumou((trnPLInfo.getShoumou() == null) ? null : trnPLInfo.getShoumou().toString());
        trnPLInfoDto.setHouteiFukuri((trnPLInfo.getHouteiFukuri() == null) ? null : trnPLInfo.getHouteiFukuri().toString());
        trnPLInfoDto.setFukuriKousei((trnPLInfo.getFukuriKousei() == null) ? null : trnPLInfo.getFukuriKousei().toString());
        trnPLInfoDto.setKousai((trnPLInfo.getKousai() == null) ? null : trnPLInfo.getKousai().toString());
        trnPLInfoDto.setRyohi((trnPLInfo.getRyohi() == null) ? null : trnPLInfo.getRyohi().toString());
        trnPLInfoDto.setTusin((trnPLInfo.getTusin() == null) ? null : trnPLInfo.getTusin().toString());
        trnPLInfoDto.setLease((trnPLInfo.getLease() == null) ? null : trnPLInfo.getLease().toString());
        trnPLInfoDto.setSharyo((trnPLInfo.getSharyo() == null) ? null : trnPLInfo.getSharyo().toString());
        trnPLInfoDto.setSozei((trnPLInfo.getSozei() == null) ? null : trnPLInfo.getSozei().toString());
        trnPLInfoDto.setHoken((trnPLInfo.getHoken() == null) ? null : trnPLInfo.getHoken().toString());
        trnPLInfoDto.setUnchin((trnPLInfo.getUnchin() == null) ? null : trnPLInfo.getUnchin().toString());
        trnPLInfoDto.setShuzen((trnPLInfo.getShuzen() == null) ? null : trnPLInfo.getShuzen().toString());
        trnPLInfoDto.setYobi((trnPLInfo.getYobi() == null) ? null : trnPLInfo.getYobi().toString());
        trnPLInfoDto.setZappi((trnPLInfo.getZappi() == null) ? null : trnPLInfo.getZappi().toString());
        trnPLInfoDto.setKeihiShokei((trnPLInfo.getKeihiShokei() == null) ? null : trnPLInfo.getKeihiShokei().toString());
        trnPLInfoDto.setShokyakuRieki((trnPLInfo.getShokyakuRieki() == null) ? null : trnPLInfo.getShokyakuRieki().toString());
        trnPLInfoDto.setGenkaShokyaku((trnPLInfo.getGenkaShokyaku() == null) ? null : trnPLInfo.getGenkaShokyaku().toString());
        trnPLInfoDto.setEigaiShueki((trnPLInfo.getEigaiShueki() == null) ? null : trnPLInfo.getEigaiShueki().toString());
        trnPLInfoDto.setEigaiHiyo((trnPLInfo.getEigaiHiyo() == null) ? null : trnPLInfo.getEigaiHiyo().toString());
        trnPLInfoDto.setHonshahiHai((trnPLInfo.getHonshahiHai() == null) ? null : trnPLInfo.getHonshahiHai().toString());
        trnPLInfoDto.setRieki((trnPLInfo.getRieki() == null) ? null : trnPLInfo.getRieki().toString());
        trnPLInfoDto.setUriage((trnPLInfo.getUriage() == null) ? null : trnPLInfo.getUriage().toString());
        trnPLInfoDto.setBuppan((trnPLInfo.getBuppan() == null) ? null : trnPLInfo.getBuppan().toString());
        trnPLInfoDto.setUriUchiwake((trnPLInfo.getUriUchiwake() == null) ? null : trnPLInfo.getUriUchiwake().toString());
        trnPLInfoDto.setElec((trnPLInfo.getElec() == null) ? null : trnPLInfo.getElec().toString());
        trnPLInfoDto.setGas((trnPLInfo.getGas() == null) ? null : trnPLInfo.getGas().toString());
        trnPLInfoDto.setWater((trnPLInfo.getWater() == null) ? null : trnPLInfo.getWater().toString());
        trnPLInfoDto.setOther((trnPLInfo.getSonota() == null) ? null : trnPLInfo.getSonota().toString());
        trnPLInfoDto.setSuikouUchiwake((trnPLInfo.getSuikouUchiwake() == null) ? null : trnPLInfo.getSuikouUchiwake().toString());
        trnPLInfoDto.setGenzairyoKei((trnPLInfo.getGenzairyoKei() == null) ? null : trnPLInfo.getGenzairyoKei().toString());
        trnPLInfoDto.setGenzairyoShire((trnPLInfo.getGenzairyoShire() == null) ? null : trnPLInfo.getGenzairyoShire().toString());
        trnPLInfoDto.setGenzairyoZaiko((trnPLInfo.getGenzairyoZaiko() == null) ? null : trnPLInfo.getGenzairyoZaiko().toString());
        trnPLInfoDto.setYasaiKei((trnPLInfo.getYasaiKei() == null) ? null : trnPLInfo.getYasaiKei().toString());
        trnPLInfoDto.setYasaiShire((trnPLInfo.getYasaiShire() == null) ? null : trnPLInfo.getYasaiShire().toString());
        trnPLInfoDto.setYasaiZaiko((trnPLInfo.getYasaiZaiko() == null) ? null : trnPLInfo.getYasaiZaiko().toString());
        trnPLInfoDto.setHouzaiKei((trnPLInfo.getHouzaiKei() == null) ? null : trnPLInfo.getHouzaiKei().toString());
        trnPLInfoDto.setHouzaiShire((trnPLInfo.getHouzaiShire() == null) ? null : trnPLInfo.getHouzaiShire().toString());
        trnPLInfoDto.setHouzaiZaiko((trnPLInfo.getHouzaiZaiko() == null) ? null : trnPLInfo.getHouzaiZaiko().toString());
        trnPLInfoDto.setBuppanKei((trnPLInfo.getBuppanKei() == null) ? null : trnPLInfo.getBuppanKei().toString());
        trnPLInfoDto.setBuppanShire((trnPLInfo.getBuppanShire() == null) ? null : trnPLInfo.getBuppanShire().toString());
        trnPLInfoDto.setBuppanZaiko((trnPLInfo.getBuppanZaiko() == null) ? null : trnPLInfo.getBuppanZaiko().toString());
        trnPLInfoDto.setTouSiireKei((trnPLInfo.getTouSiireKei() == null) ? null : trnPLInfo.getTouSiireKei().toString());
        trnPLInfoDto.setTouZaikoKei((trnPLInfo.getTouZaikoKei() == null) ? null : trnPLInfo.getTouZaikoKei().toString());
        trnPLInfoDto.setSashihikiKei((trnPLInfo.getSashihikiKei() == null) ? null : trnPLInfo.getSashihikiKei().toString());
        trnPLInfoDto.setYakuinSalary((trnPLInfo.getYakuinSalary() == null) ? null : trnPLInfo.getYakuinSalary().toString());
        trnPLInfoDto.setYakuinBonus((trnPLInfo.getYakuinBonus() == null) ? null : trnPLInfo.getYakuinBonus().toString());
        trnPLInfoDto.setYakuinRetire((trnPLInfo.getYakuinRetire() == null) ? null : trnPLInfo.getYakuinRetire().toString());
        trnPLInfoDto.setYakuinKei((trnPLInfo.getYakuinKei() == null) ? null : trnPLInfo.getYakuinKei().toString());
        trnPLInfoDto.setSalarySalary((trnPLInfo.getSalarySalary() == null) ? null : trnPLInfo.getSalarySalary().toString());
        trnPLInfoDto.setSalaryBonus((trnPLInfo.getSalaryBonus() == null) ? null : trnPLInfo.getSalaryBonus().toString());
        trnPLInfoDto.setSalaryRetire((trnPLInfo.getSalaryRetire() == null) ? null : trnPLInfo.getSalaryRetire().toString());
        trnPLInfoDto.setSalaryKei((trnPLInfo.getSalaryKei() == null) ? null : trnPLInfo.getSalaryKei().toString());
        trnPLInfoDto.setZakkyuSalary((trnPLInfo.getZakkyuSalary() == null) ? null : trnPLInfo.getZakkyuSalary().toString());
        trnPLInfoDto.setZakkyuBonus((trnPLInfo.getZakkyuBonus() == null) ? null : trnPLInfo.getZakkyuBonus().toString());
        trnPLInfoDto.setZakkyuRetire((trnPLInfo.getZakkyuRetire() == null) ? null : trnPLInfo.getZakkyuRetire().toString());
        trnPLInfoDto.setZakkyuKei((trnPLInfo.getZakkyuKei() == null) ? null : trnPLInfo.getZakkyuKei().toString());
        trnPLInfoDto.setKyuryoKei((trnPLInfo.getKyuryoKei() == null) ? null : trnPLInfo.getKyuryoKei().toString());
        trnPLInfoDto.setBonusKei((trnPLInfo.getBonusKei() == null) ? null : trnPLInfo.getBonusKei().toString());
        trnPLInfoDto.setRetireKei((trnPLInfo.getRetireKei() == null) ? null : trnPLInfo.getRetireKei().toString());
        trnPLInfoDto.setSalaryUtiKei((trnPLInfo.getSalaryUtiKei() == null) ? null : trnPLInfo.getSalaryUtiKei().toString());
        trnPLInfoDto.setKashiireUp((trnPLInfo.getKashiireUp() == null) ? null : trnPLInfo.getKashiireUp().toString());
        trnPLInfoDto.setKashiireDown((trnPLInfo.getKashiireDown() == null) ? null : trnPLInfo.getKashiireDown().toString());
        trnPLInfoDto.setKashiireZandaka((trnPLInfo.getKashiireZandaka() == null) ? null : trnPLInfo.getKashiireZandaka().toString());
        trnPLInfoDto.setKappuUp((trnPLInfo.getKappuUp() == null) ? null : trnPLInfo.getKappuUp().toString());
        trnPLInfoDto.setKappuDown((trnPLInfo.getKappuDown() == null) ? null : trnPLInfo.getKappuDown().toString());
        trnPLInfoDto.setKappuZandaka((trnPLInfo.getKappuZandaka() == null) ? null : trnPLInfo.getKappuZandaka().toString());
        trnPLInfoDto.setLeaseUp((trnPLInfo.getLeaseUp() == null) ? null : trnPLInfo.getLeaseUp().toString());
        trnPLInfoDto.setLeaseDown((trnPLInfo.getLeaseDown() == null) ? null : trnPLInfo.getLeaseDown().toString());
        trnPLInfoDto.setLeaseZandaka((trnPLInfo.getLeaseZandaka() == null) ? null : trnPLInfo.getLeaseZandaka().toString());
        trnPLInfoDto.setTouZoukaKei((trnPLInfo.getTouZoukaKei() == null) ? null : trnPLInfo.getTouZoukaKei().toString());
        trnPLInfoDto.setTouGenshoKei((trnPLInfo.getTouGenshoKei() == null) ? null : trnPLInfo.getTouGenshoKei().toString());
        trnPLInfoDto.setTouZandakaKei((trnPLInfo.getTouZandakaKei() == null) ? null : trnPLInfo.getTouZandakaKei().toString());
    }

    /**
     * P/Lデータ変換処理(String→BigDecimal)
     * 画面表示用Dto情報をエンティティに反映
     * @param trnPLInfoDto
     * @return
     */
    private void convertTrnPlInfoToBigDecimal() {
    	// 編集画面の数値項目
    	trnPLInfo.setUriagedaka((trnPLInfoDto.getUriagedaka() == null) ?  null : new BigDecimal(trnPLInfoDto.getUriagedaka()));
    	trnPLInfo.setUriagegenka((trnPLInfoDto.getUriagegenka() == null) ?  null : new BigDecimal(trnPLInfoDto.getUriagegenka()));
    	trnPLInfo.setUriageSoRieki((trnPLInfoDto.getUriageSoRieki() == null) ?  null : new BigDecimal(trnPLInfoDto.getUriageSoRieki()));
    	trnPLInfo.setSalary((trnPLInfoDto.getSalary() == null) ?  null : new BigDecimal(trnPLInfoDto.getSalary()));
    	trnPLInfo.setYachin((trnPLInfoDto.getYachin() == null) ?  null : new BigDecimal(trnPLInfoDto.getYachin()));
    	trnPLInfo.setSuikouHi((trnPLInfoDto.getSuikouHi() == null) ?  null : new BigDecimal(trnPLInfoDto.getSuikouHi()));
    	trnPLInfo.setRoyalty((trnPLInfoDto.getRoyalty() == null) ?  null : new BigDecimal(trnPLInfoDto.getRoyalty()));
    	trnPLInfo.setTesuryo((trnPLInfoDto.getTesuryo() == null) ?  null : new BigDecimal(trnPLInfoDto.getTesuryo()));
    	trnPLInfo.setKoukoku((trnPLInfoDto.getKoukoku() == null) ?  null : new BigDecimal(trnPLInfoDto.getKoukoku()));
    	trnPLInfo.setShoumou((trnPLInfoDto.getShoumou() == null) ?  null : new BigDecimal(trnPLInfoDto.getShoumou()));
    	trnPLInfo.setHouteiFukuri((trnPLInfoDto.getHouteiFukuri() == null) ?  null : new BigDecimal(trnPLInfoDto.getHouteiFukuri()));
    	trnPLInfo.setFukuriKousei((trnPLInfoDto.getFukuriKousei() == null) ?  null : new BigDecimal(trnPLInfoDto.getFukuriKousei()));
    	trnPLInfo.setKousai((trnPLInfoDto.getKousai() == null) ?  null : new BigDecimal(trnPLInfoDto.getKousai()));
    	trnPLInfo.setRyohi((trnPLInfoDto.getRyohi() == null) ?  null : new BigDecimal(trnPLInfoDto.getRyohi()));
    	trnPLInfo.setTusin((trnPLInfoDto.getTusin() == null) ?  null : new BigDecimal(trnPLInfoDto.getTusin()));
    	trnPLInfo.setLease((trnPLInfoDto.getLease() == null) ?  null : new BigDecimal(trnPLInfoDto.getLease()));
    	trnPLInfo.setSharyo((trnPLInfoDto.getSharyo() == null) ?  null : new BigDecimal(trnPLInfoDto.getSharyo()));
    	trnPLInfo.setSozei((trnPLInfoDto.getSozei() == null) ?  null : new BigDecimal(trnPLInfoDto.getSozei()));
    	trnPLInfo.setHoken((trnPLInfoDto.getHoken() == null) ?  null : new BigDecimal(trnPLInfoDto.getHoken()));
    	trnPLInfo.setUnchin((trnPLInfoDto.getUnchin() == null) ?  null : new BigDecimal(trnPLInfoDto.getUnchin()));
    	trnPLInfo.setShuzen((trnPLInfoDto.getShuzen() == null) ?  null : new BigDecimal(trnPLInfoDto.getShuzen()));
    	trnPLInfo.setYobi((trnPLInfoDto.getYobi() == null) ?  null : new BigDecimal(trnPLInfoDto.getYobi()));
    	trnPLInfo.setZappi((trnPLInfoDto.getZappi() == null) ?  null : new BigDecimal(trnPLInfoDto.getZappi()));
    	trnPLInfo.setKeihiShokei((trnPLInfoDto.getKeihiShokei() == null) ?  null : new BigDecimal(trnPLInfoDto.getKeihiShokei()));
    	trnPLInfo.setShokyakuRieki((trnPLInfoDto.getShokyakuRieki() == null) ?  null : new BigDecimal(trnPLInfoDto.getShokyakuRieki()));
    	trnPLInfo.setGenkaShokyaku((trnPLInfoDto.getGenkaShokyaku() == null) ?  null : new BigDecimal(trnPLInfoDto.getGenkaShokyaku()));
    	trnPLInfo.setEigaiShueki((trnPLInfoDto.getEigaiShueki() == null) ?  null : new BigDecimal(trnPLInfoDto.getEigaiShueki()));
    	trnPLInfo.setEigaiHiyo((trnPLInfoDto.getEigaiHiyo() == null) ?  null : new BigDecimal(trnPLInfoDto.getEigaiHiyo()));
    	trnPLInfo.setHonshahiHai((trnPLInfoDto.getHonshahiHai() == null) ?  null : new BigDecimal(trnPLInfoDto.getHonshahiHai()));
    	trnPLInfo.setRieki((trnPLInfoDto.getRieki() == null) ?  null : new BigDecimal(trnPLInfoDto.getRieki()));
    	trnPLInfo.setUriage((trnPLInfoDto.getUriage() == null) ?  null : new BigDecimal(trnPLInfoDto.getUriage()));
    	trnPLInfo.setBuppan((trnPLInfoDto.getBuppan() == null) ?  null : new BigDecimal(trnPLInfoDto.getBuppan()));
    	trnPLInfo.setUriUchiwake((trnPLInfoDto.getUriUchiwake() == null) ?  null : new BigDecimal(trnPLInfoDto.getUriUchiwake()));
    	trnPLInfo.setElec((trnPLInfoDto.getElec() == null) ?  null : new BigDecimal(trnPLInfoDto.getElec()));
    	trnPLInfo.setGas((trnPLInfoDto.getGas() == null) ?  null : new BigDecimal(trnPLInfoDto.getGas()));
    	trnPLInfo.setWater((trnPLInfoDto.getWater() == null) ?  null : new BigDecimal(trnPLInfoDto.getWater()));
    	trnPLInfo.setSonota((trnPLInfoDto.getOther() == null) ?  null : new BigDecimal(trnPLInfoDto.getOther()));
    	trnPLInfo.setSuikouUchiwake((trnPLInfoDto.getSuikouUchiwake() == null) ?  null : new BigDecimal(trnPLInfoDto.getSuikouUchiwake()));
    	trnPLInfo.setGenzairyoKei((trnPLInfoDto.getGenzairyoKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getGenzairyoKei()));
    	trnPLInfo.setGenzairyoShire((trnPLInfoDto.getGenzairyoShire() == null) ?  null : new BigDecimal(trnPLInfoDto.getGenzairyoShire()));
    	trnPLInfo.setGenzairyoZaiko((trnPLInfoDto.getGenzairyoZaiko() == null) ?  null : new BigDecimal(trnPLInfoDto.getGenzairyoZaiko()));
    	trnPLInfo.setYasaiKei((trnPLInfoDto.getYasaiKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getYasaiKei()));
    	trnPLInfo.setYasaiShire((trnPLInfoDto.getYasaiShire() == null) ?  null : new BigDecimal(trnPLInfoDto.getYasaiShire()));
    	trnPLInfo.setYasaiZaiko((trnPLInfoDto.getYasaiZaiko() == null) ?  null : new BigDecimal(trnPLInfoDto.getYasaiZaiko()));
    	trnPLInfo.setHouzaiKei((trnPLInfoDto.getHouzaiKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getHouzaiKei()));
    	trnPLInfo.setHouzaiShire((trnPLInfoDto.getHouzaiShire() == null) ?  null : new BigDecimal(trnPLInfoDto.getHouzaiShire()));
    	trnPLInfo.setHouzaiZaiko((trnPLInfoDto.getHouzaiZaiko() == null) ?  null : new BigDecimal(trnPLInfoDto.getHouzaiZaiko()));
    	trnPLInfo.setBuppanKei((trnPLInfoDto.getBuppanKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getBuppanKei()));
    	trnPLInfo.setBuppanShire((trnPLInfoDto.getBuppanShire() == null) ?  null : new BigDecimal(trnPLInfoDto.getBuppanShire()));
    	trnPLInfo.setBuppanZaiko((trnPLInfoDto.getBuppanZaiko() == null) ?  null : new BigDecimal(trnPLInfoDto.getBuppanZaiko()));
    	trnPLInfo.setTouSiireKei((trnPLInfoDto.getTouSiireKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getTouSiireKei()));
    	trnPLInfo.setTouZaikoKei((trnPLInfoDto.getTouZaikoKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getTouZaikoKei()));
    	trnPLInfo.setSashihikiKei((trnPLInfoDto.getSashihikiKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getSashihikiKei()));
    	trnPLInfo.setYakuinSalary((trnPLInfoDto.getYakuinSalary() == null) ?  null : new BigDecimal(trnPLInfoDto.getYakuinSalary()));
    	trnPLInfo.setYakuinBonus((trnPLInfoDto.getYakuinBonus() == null) ?  null : new BigDecimal(trnPLInfoDto.getYakuinBonus()));
    	trnPLInfo.setYakuinRetire((trnPLInfoDto.getYakuinRetire() == null) ?  null : new BigDecimal(trnPLInfoDto.getYakuinRetire()));
    	trnPLInfo.setYakuinKei((trnPLInfoDto.getYakuinKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getYakuinKei()));
    	trnPLInfo.setSalarySalary((trnPLInfoDto.getSalarySalary() == null) ?  null : new BigDecimal(trnPLInfoDto.getSalarySalary()));
    	trnPLInfo.setSalaryBonus((trnPLInfoDto.getSalaryBonus() == null) ?  null : new BigDecimal(trnPLInfoDto.getSalaryBonus()));
    	trnPLInfo.setSalaryRetire((trnPLInfoDto.getSalaryRetire() == null) ?  null : new BigDecimal(trnPLInfoDto.getSalaryRetire()));
    	trnPLInfo.setSalaryKei((trnPLInfoDto.getSalaryKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getSalaryKei()));
    	trnPLInfo.setZakkyuSalary((trnPLInfoDto.getZakkyuSalary() == null) ?  null : new BigDecimal(trnPLInfoDto.getZakkyuSalary()));
    	trnPLInfo.setZakkyuBonus((trnPLInfoDto.getZakkyuBonus() == null) ?  null : new BigDecimal(trnPLInfoDto.getZakkyuBonus()));
    	trnPLInfo.setZakkyuRetire((trnPLInfoDto.getZakkyuRetire() == null) ?  null : new BigDecimal(trnPLInfoDto.getZakkyuRetire()));
    	trnPLInfo.setZakkyuKei((trnPLInfoDto.getZakkyuKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getZakkyuKei()));
    	trnPLInfo.setKyuryoKei((trnPLInfoDto.getKyuryoKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getKyuryoKei()));
    	trnPLInfo.setBonusKei((trnPLInfoDto.getBonusKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getBonusKei()));
    	trnPLInfo.setRetireKei((trnPLInfoDto.getRetireKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getRetireKei()));
    	trnPLInfo.setSalaryUtiKei((trnPLInfoDto.getSalaryUtiKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getSalaryUtiKei()));
    	trnPLInfo.setKashiireUp((trnPLInfoDto.getKashiireUp() == null) ?  null : new BigDecimal(trnPLInfoDto.getKashiireUp()));
    	trnPLInfo.setKashiireDown((trnPLInfoDto.getKashiireDown() == null) ?  null : new BigDecimal(trnPLInfoDto.getKashiireDown()));
    	trnPLInfo.setKashiireZandaka((trnPLInfoDto.getKashiireZandaka() == null) ?  null : new BigDecimal(trnPLInfoDto.getKashiireZandaka()));
    	trnPLInfo.setKappuUp((trnPLInfoDto.getKappuUp() == null) ?  null : new BigDecimal(trnPLInfoDto.getKappuUp()));
    	trnPLInfo.setKappuDown((trnPLInfoDto.getKappuDown() == null) ?  null : new BigDecimal(trnPLInfoDto.getKappuDown()));
    	trnPLInfo.setKappuZandaka((trnPLInfoDto.getKappuZandaka() == null) ?  null : new BigDecimal(trnPLInfoDto.getKappuZandaka()));
    	trnPLInfo.setLeaseUp((trnPLInfoDto.getLeaseUp() == null) ?  null : new BigDecimal(trnPLInfoDto.getLeaseUp()));
    	trnPLInfo.setLeaseDown((trnPLInfoDto.getLeaseDown() == null) ?  null : new BigDecimal(trnPLInfoDto.getLeaseDown()));
    	trnPLInfo.setLeaseZandaka((trnPLInfoDto.getLeaseZandaka() == null) ?  null : new BigDecimal(trnPLInfoDto.getLeaseZandaka()));
    	trnPLInfo.setTouZoukaKei((trnPLInfoDto.getTouZoukaKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getTouZoukaKei()));
    	trnPLInfo.setTouGenshoKei((trnPLInfoDto.getTouGenshoKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getTouGenshoKei()));
    	trnPLInfo.setTouZandakaKei((trnPLInfoDto.getTouZandakaKei() == null) ?  null : new BigDecimal(trnPLInfoDto.getTouZandakaKei()));
    }

    
    /**
     * タブ間のデータ移行
     * ※タブ切替時に整合性のとれたデータを別タブに移行する
     * ※確認ボタン押下時も同様の移行を行う
     */
    private void setDefaultTabDate(TrnPLInfoDto trnPLInfoDto) {
        
        // タブ移行事前チェック
        setDefaultPriorCheck(trnPLInfoDto);
        
        // 『月次損益タブ』→
        if (plRegistDto.getSelectedTab() == 1) {
            ;
        }
        
        // 『内訳タブ』→
        else if (plRegistDto.getSelectedTab() == 2) {
            
            // 『内訳タブ』→ 『月次損益タブ』
//            if (selectedTab == 1) {
                
                // 初期入力状態では値の引継ぎを行わない
                // 「初期入力状態」もしくは「オーナー：36387 ヴィアン様」では値の引継ぎを行わない #5277  2021.12.17 fukasawa
//                if (!trnPLInfoDto.isDefaultFlg()) {
                if (!trnPLInfoDto.isDefaultFlg() && !trnPLInfo.getOnerCd().equals("36387")) {
                    
                    if (trnPLInfoDto.isUriUchiwakeCopeFlg()) {
                        // 売上高
                        if (trnPLInfoDto.getUriUchiwake()!=null)
                            trnPLInfoDto.setUriagedaka(trnPLInfoDto.getUriUchiwake());
                        
                    }
                    if (trnPLInfoDto.isUriagegenkaUchiwakeCopeFlg()) {
                        // 売上原価
                        if (trnPLInfoDto.getSashihikiKei()!=null)
                            trnPLInfoDto.setUriagegenka(trnPLInfoDto.getSashihikiKei());
                        
                    }
                    if (trnPLInfoDto.isSuikohiUchiwakeCopeFlg()) {
                        // 光熱費
                        if (trnPLInfoDto.getSuikouUchiwake()!=null)
                            trnPLInfoDto.setSuikouHi(trnPLInfoDto.getSuikouUchiwake());
                        
                    }
                    if (trnPLInfoDto.isSalaryUchiwakeCopeFlg()) {
                        // 給料手当
                        if (trnPLInfoDto.getSalaryUtiKei()!=null)
                            trnPLInfoDto.setSalary(trnPLInfoDto.getSalaryUtiKei());
                        
                    }

                }
//            }
        }
        
        // 『借入金タブ』→
        else if (plRegistDto.getSelectedTab() == 3) {
            ;
        }    
    }
    
    
    /**
     * データ移行事前チェック
     * ※タブ間のデータ移行が可能か判断する
     * 
     * ※[内訳]タブ売上高コピー
     *  1. 月次損益タブの売上高がPOS売上げと一致していない、且つ
     *     月次損益タブの売上高以外の項目がどれか１つでも入力されている
     *  2. 内訳タブの売上高の内訳(売上/物販売上/計)がどれか１つでも入力されている
     *  
     * ※[内訳]タブ水道光熱費コピー
     *  1. 内訳タブの水道光熱費内訳がどれか１つでも入力されている
     *  
     * ※[内訳]タブ売上原価コピー
     *  1. 内訳タブの売上原価内訳がどれか１つでも入力されている
     *  
     * ※[内訳]タブ給料手当コピー
     *  1. 内訳タブの給力手当内訳がどれか１つでも入力されている
     * 
     * @param TrnPLInfoDto P/LデータDto
     */
    private void setDefaultPriorCheck(TrnPLInfoDto trnPLInfoDto){
        
        boolean uriUchiwakeCopeFlg1 = false;        //売上高コピー許可フラグ1
        boolean uriUchiwakeCopeFlg2 = false;        //売上高コピー許可フラグ2
        boolean suikohiUchiwakeCopeFlg = false;     //水道光熱費コピー許可フラグ
        boolean uriagegenkaUchiwakeCopeFlg = false; //売上原価コピー許可フラグ
        boolean salaryUchiwakeCopeFlg = false;      //給料コピー許可フラグ
        
        
        // 月次損益タブの売上高がPOS売上げと一致していない
        if ( plRegistDto.getTrnPosZenUriage() != null ) {
            if (!trnPLInfoDto.getUriagedaka()
                    .equals(String.valueOf(plRegistDto.getTrnPosZenUriage().getUriage()))) 
                uriUchiwakeCopeFlg1 = true;
        }
        
        // 月次損益タブの売上高以外の項目がどれか１つでも入力されている
        if (!isNull(trnPLInfoDto.getUriagegenka()) && !isZero(trnPLInfoDto.getUriagegenka())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getUriageSoRieki()) && !isZero(trnPLInfoDto.getUriageSoRieki())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getSalary()) && !isZero(trnPLInfoDto.getSalary())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getYachin()) && !isZero(trnPLInfoDto.getYachin())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getSuikouHi()) && !isZero(trnPLInfoDto.getSuikouHi())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getRoyalty()) && !isZero(trnPLInfoDto.getRoyalty())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getTesuryo()) && !isZero(trnPLInfoDto.getTesuryo())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getKoukoku()) && !isZero(trnPLInfoDto.getKoukoku())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getShoumou()) && !isZero(trnPLInfoDto.getShoumou())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getHouteiFukuri()) && !isZero(trnPLInfoDto.getHouteiFukuri())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getFukuriKousei()) && !isZero(trnPLInfoDto.getFukuriKousei())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getKousai()) && !isZero(trnPLInfoDto.getKousai())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getRyohi()) && !isZero(trnPLInfoDto.getRyohi())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getTusin()) && !isZero(trnPLInfoDto.getTusin())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getLease()) && !isZero(trnPLInfoDto.getLease())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getSharyo()) && !isZero(trnPLInfoDto.getSharyo())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getSozei()) && !isZero(trnPLInfoDto.getSozei())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getHoken()) && !isZero(trnPLInfoDto.getHoken())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getUnchin()) && !isZero(trnPLInfoDto.getUnchin())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getShuzen()) && !isZero(trnPLInfoDto.getShuzen())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getYobi()) && !isZero(trnPLInfoDto.getYobi())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getZappi()) && !isZero(trnPLInfoDto.getZappi())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getKeihiShokei()) && !isZero(trnPLInfoDto.getKeihiShokei())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getShokyakuRieki()) && !isZero(trnPLInfoDto.getShokyakuRieki())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getGenkaShokyaku()) && !isZero(trnPLInfoDto.getGenkaShokyaku())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getEigaiShueki()) && !isZero(trnPLInfoDto.getEigaiShueki())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getEigaiHiyo()) && !isZero(trnPLInfoDto.getEigaiHiyo())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getHonshahiHai()) && !isZero(trnPLInfoDto.getHonshahiHai())) uriUchiwakeCopeFlg1 = true;
        if (!isNull(trnPLInfoDto.getRieki()) && !isZero(trnPLInfoDto.getRieki())) uriUchiwakeCopeFlg1 = true;
        
        // 内訳タブの売上高の内訳(売上/物販売上/計)がどれか１つでも入力されている
        if (!isNull(trnPLInfoDto.getUriage()) && !isZero(trnPLInfoDto.getUriage())) uriUchiwakeCopeFlg2 = true;
        if (!isNull(trnPLInfoDto.getBuppan()) && !isZero(trnPLInfoDto.getBuppan())) uriUchiwakeCopeFlg2 = true;
        if (!isNull(trnPLInfoDto.getUriUchiwake()) && !isZero(trnPLInfoDto.getUriUchiwake())) uriUchiwakeCopeFlg2 = true;
        
        // 内訳タブの水道光熱費内訳がどれか１つでも入力されている
        if (!isNull(trnPLInfoDto.getElec()) && !isZero(trnPLInfoDto.getElec())) suikohiUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getGas()) && !isZero(trnPLInfoDto.getGas())) suikohiUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getWater()) && !isZero(trnPLInfoDto.getWater())) suikohiUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getOther()) && !isZero(trnPLInfoDto.getOther())) suikohiUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getSuikouUchiwake()) && !isZero(trnPLInfoDto.getSuikouUchiwake())) suikohiUchiwakeCopeFlg = true;
        
        // 内訳タブの売上原価内訳がどれか１つでも入力されている
        if (!isNull(trnPLInfoDto.getGenzairyoShire()) && !isZero(trnPLInfoDto.getGenzairyoShire())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getGenzairyoZaiko()) && !isZero(trnPLInfoDto.getGenzairyoZaiko())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getGenzairyoKei()) && !isZero(trnPLInfoDto.getGenzairyoKei())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getYasaiShire()) && !isZero(trnPLInfoDto.getYasaiShire())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getYasaiZaiko()) && !isZero(trnPLInfoDto.getYasaiZaiko())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getYasaiKei()) && !isZero(trnPLInfoDto.getYasaiKei())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getHouzaiShire()) && !isZero(trnPLInfoDto.getHouzaiShire())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getHouzaiZaiko()) && !isZero(trnPLInfoDto.getHouzaiZaiko())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getHouzaiKei()) && !isZero(trnPLInfoDto.getHouzaiKei())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getBuppanShire()) && !isZero(trnPLInfoDto.getBuppanShire())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getBuppanZaiko()) && !isZero(trnPLInfoDto.getBuppanZaiko())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getBuppanKei()) && !isZero(trnPLInfoDto.getBuppanKei())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getTouSiireKei()) && !isZero(trnPLInfoDto.getTouSiireKei())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getTouZaikoKei()) && !isZero(trnPLInfoDto.getTouZaikoKei())) uriagegenkaUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getSashihikiKei()) && !isZero(trnPLInfoDto.getSashihikiKei())) uriagegenkaUchiwakeCopeFlg = true;
        
        // 内訳タブの給力手当内訳がどれか１つでも入力されている
        if (!isNull(trnPLInfoDto.getYakuinSalary()) && !isZero(trnPLInfoDto.getYakuinSalary())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getYakuinBonus()) && !isZero(trnPLInfoDto.getYakuinBonus())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getYakuinRetire()) && !isZero(trnPLInfoDto.getYakuinRetire())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getYakuinKei()) && !isZero(trnPLInfoDto.getYakuinKei())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getSalarySalary()) && !isZero(trnPLInfoDto.getSalarySalary())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getSalaryBonus()) && !isZero(trnPLInfoDto.getSalaryBonus())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getSalaryRetire()) && !isZero(trnPLInfoDto.getSalaryRetire())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getSalaryKei()) && !isZero(trnPLInfoDto.getSalaryKei())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getZakkyuSalary()) && !isZero(trnPLInfoDto.getZakkyuSalary())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getZakkyuBonus()) && !isZero(trnPLInfoDto.getZakkyuBonus())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getZakkyuRetire()) && !isZero(trnPLInfoDto.getZakkyuRetire())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getZakkyuKei()) && !isZero(trnPLInfoDto.getZakkyuKei())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getKyuryoKei()) && !isZero(trnPLInfoDto.getKyuryoKei())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getBonusKei()) && !isZero(trnPLInfoDto.getBonusKei())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getRetireKei()) && !isZero(trnPLInfoDto.getRetireKei())) salaryUchiwakeCopeFlg = true;
        if (!isNull(trnPLInfoDto.getSalaryUtiKei()) && !isZero(trnPLInfoDto.getSalaryUtiKei())) salaryUchiwakeCopeFlg = true;
        
        // 内訳 売上高コピー許可
        if (uriUchiwakeCopeFlg1 &&  uriUchiwakeCopeFlg2)
            trnPLInfoDto.setUriUchiwakeCopeFlg(true);
        else
            trnPLInfoDto.setUriUchiwakeCopeFlg(false);
        
        // 内訳 売上原価コピー許可
        trnPLInfoDto.setUriagegenkaUchiwakeCopeFlg(uriagegenkaUchiwakeCopeFlg);
        
        // 内訳 水道光熱費コピー許可
        trnPLInfoDto.setSuikohiUchiwakeCopeFlg(suikohiUchiwakeCopeFlg);
        
        // 内訳 給料手当コピー許可
        trnPLInfoDto.setSalaryUchiwakeCopeFlg(salaryUchiwakeCopeFlg);
        
    }
    
    /**
     * Zeroチェック
     */
    private boolean isZero(String val) {
        if (val != null && isNumericFormat(val)
                && new BigDecimal(val).compareTo(BigDecimal.valueOf(0)) == 0) {
            return true;
        }
        return false;
    }
    /**
     * 数値チェック
     */
    private boolean isNumericFormat(String val) {
        NumericVerifier numericVerifier = new NumericVerifier();
        if (!isNull(val)
                && !numericVerifier.validate(val)) {
            return false;
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