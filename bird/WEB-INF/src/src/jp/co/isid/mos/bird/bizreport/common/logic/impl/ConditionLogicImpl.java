package jp.co.isid.mos.bird.bizreport.common.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoTenpo;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.logic.CompanyListLogic;
import jp.co.isid.mos.bird.bizreport.common.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.common.logic.KikanSiteiMapLogic;
import jp.co.isid.mos.bird.bizreport.common.util.KikanSiteiUtil;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.netorderniporef.common.NetorderNipoConstants;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.AccessCtlCheckLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 総合営業日報の条件部情報取得共通ロジック
 *
 * @author xjung
 */
public class ConditionLogicImpl implements ConditionLogic {

    /** 総合営業日報の条件部情報取得共通ロジックID */
    public static final String LOGIC_ID = "BBR000L04";
    
    /** MOSCARDアクセスフラグ */
    public static final String MOSCARD_ACCESS_FLG = "moscardAccessFlg";
    /** ネット注文アクセスフラグ */
    public static final String NETORDER_ACCESS_FLG = "netorderAccessFlg";
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();

    /** 会社情報取得ロジック */
    private CompanyListLogic companyListLogic;

    /** 期間指定データ作成ロジック */
    private KikanSiteiMapLogic kikanSiteiListLogic;

    /** メニュー権限チェックロジック */
    private AccessCtlCheckLogic accessCtlCheckLogic;

    /**
     * 条件部情報を取得する
     * @param userType	ユーザタイプ
     * @param userId	ユーザID
     * @param appDate	アプリ日付
     * @return Map     条件部情報
     */
    public Map execute(String userType, String userId, String appDate) {
		Map map = new HashMap();
		// パラメータの入力チェック
		if (userType == null || userType.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_USER_TYPE);
		}
		if (userId == null || userId.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_USER_ID);
		}
		if (appDate == null || appDate.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_APP_DATE);
		}
		 
    	// ユーザータイプが店舗の場合
		if (!UserType.HONBU.equals(userType) && !UserType.ONER.equals(userType)) {
	    	// ユーザータイプが本部又はオーナーでない場合、権限エラー
	    	throw new CannotAccessException();
		}

		// 会社リスト取得
    	Map compMap = new HashMap();
    	compMap.put(CompanyListLogic.PK_FOREING_IN, new Boolean(true));
        List compInfoList = getCompanyListLogic().execute(compMap);

    	// 対象期間リスト取得
        List taishoKikanList = TaishoKikan.getPullDownList(userType);
  
        // 期間指定年月日(yyyy/MM/dd)リスト取得    
        List kikanYmdList = KikanSiteiUtil.getDateDayList(appDate); 

        // 期間指定年月(yyyy/MM)リスト取得
        List kikanYmList = KikanSiteiUtil.getDateMonthList(appDate);        

        // 期間指定当月取得
        String kikanCurrM = KikanSiteiUtil.getCurrentMonth(appDate);
  
		// 取得したリストをMapへ格納
        map.put(BizReportConstants.COMPANY_INFO_LIST, compInfoList);
        map.put(BizReportConstants.TAISHO_KIKAN_LIST, taishoKikanList);
        map.put(BizReportConstants.KIKAN_YMD_LIST, kikanYmdList);
        map.put(BizReportConstants.KIKAN_YM_LIST, kikanYmList);
        map.put(BizReportConstants.KIKAN_CURR_MONTH, kikanCurrM);

        // ユーザータイプが本部の場合
        if (UserType.HONBU.equals(userType)) {
        	// 店舗種別リスト取得
        	List tenpoShubetuList = TenpoShubetu.getPullDownList();

            // 集計区分リスト取得
        	List shukeiKbnList = ShukeiKbn.getPullDownList();

            // 対象店舗リスト取得
        	List taishoTempoList = TaishoTenpo.getPullDownList();

            // 期間指定期別リスト取得
	        Map kibetuParamMap = new HashMap();
	        // 期間タイプ＝期別
	        kibetuParamMap.put(BizReportConstants.PM_KIKANTYPE,
	        				   TaishoKikan.KIBETU);
	        // 年リストサイズ＝３年分
	        kibetuParamMap.put(BizReportConstants.PM_SIZE,
	        				   BizReportConstants.Y_SIZE);
	        Map kibetuMap = getKikanSiteiListLogic().execute(kibetuParamMap);
            // 期間指定年(yyyy)リスト取得
	        List kikanYList =
	        	(List) kibetuMap.get(BizReportConstants.KIBETU_Y_LIST);
	        // 期間指定期別リスト取得
	        List kikanKibetuList =
	        	(List) kibetuMap.get(BizReportConstants.KIBETU_LIST);
       	
            // 前年データ種別(前年対象店)リスト取得
	        List zenDataList = NipoZennenDataShubetu.getPullDownList
	        	(userType, TenpoShubetu.CODE_ZENNEN);
            // 前年データ種別(前年対象店以外)リスト取得
	        List zenDataOthList = NipoZennenDataShubetu.getPullDownList(userType, null);

			// 取得したリストをMapへ格納	        
        	map.put(BizReportConstants.TENPO_SHUBETU_LIST, tenpoShubetuList);
        	map.put(BizReportConstants.SHUKEI_KBN_LIST, shukeiKbnList);
        	map.put(BizReportConstants.TAISHO_TENPO_LIST, taishoTempoList);
	        map.put(BizReportConstants.KIKAN_Y_LIST, kikanYList);
	        map.put(BizReportConstants.KIKAN_KIBETU_LIST, kikanKibetuList);
	        map.put(BizReportConstants.ZEN_DATA_LIST, zenDataList);
	        map.put(BizReportConstants.ZEN_DATA_OTH_LIST, zenDataOthList);
          
            // ユーザータイプがオーナーの場合
        } else if (UserType.ONER.equals(userType)) {
            // 前年データ種別リスト取得
        	List zenDataOnerList = NipoZennenDataShubetu.getPullDownList(userType, null);
 
			// 取得したリストをMapへ格納
        	map.put(BizReportConstants.ZEN_DATA_ONER_LIST, zenDataOnerList);
            
/** 2008/10/6 追加 start *****************************************************/           
            /** 期間指定年月(yyyy/MM)リスト取得  2008/10/6 追加  **/
            // フォーマットパターン：YYYYMM、日付タイプ＝１      
            String appMonth = formatter.format
                (appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

            //アプリ日付の前月を取得(yyyyMM)
            String yyyyMM=null;
            
            try {
                yyyyMM = DateManager.getPrevMonth(appMonth, 1);
            } catch (Exception e) {
                throw new FtlSystemException("営業日報初期処理");
            }
            
            //営業月報抽出用の期間指定年月(yyyy/MM)リスト取得
            //前月以前14ヵ月を取得。
            List kikanGepoYMList = KikanSiteiUtil.getOptionalMonthList(yyyyMM,14); 
            
            // 取得したリストをMapへ格納
            map.put(BizReportConstants.KIKAN_GEPO_YM_LIST, kikanGepoYMList);
            
/** 2008/10/6 end ************************************************************/              
            
        }

        // 個店ポータルアクセスフラグ
        map.put(BizReportConstants.PORTAL_ACCESS_FLG, Boolean.valueOf(
            getAccessCtlCheckLogic().isAccessMenu(userId, BizReportConstants.SCREEN_ID_PORTAL)));
        // MOSCARDアクセスフラグ
        map.put(MOSCARD_ACCESS_FLG, Boolean.valueOf(
            getAccessCtlCheckLogic().isAccessMenu(userId, MoscardNipoConstants.SCREEN_ID)));
        //ネット注文アクセスフラグ
        map.put(NETORDER_ACCESS_FLG, Boolean.valueOf(
            getAccessCtlCheckLogic().isAccessMenu(userId, NetorderNipoConstants.SCREEN_ID)));
 
        return map;
		
	}

    /**
     * 会社情報取得ロジックを取得します
     * @return companyListLogic 会社情報取得ロジック
     */
    public CompanyListLogic getCompanyListLogic() {
		return companyListLogic;
	}

    /**
     * 会社情報取得ロジックを設定します
     * @param companyListLogic 会社情報取得ロジック
     */
    public void setCompanyListLogic(CompanyListLogic companyListLogic) {
		this.companyListLogic = companyListLogic;
	}

    /**
     * 期間指定データ作成ロジックを取得します
     * @return KikanSiteiMapLogic 期間指定データ作成ロジック
     */
	public KikanSiteiMapLogic getKikanSiteiListLogic() {
		return kikanSiteiListLogic;
	}

    /**
     * 期間指定データ作成ロジックを設定します
     * @param kikanSiteiListLogic 期間指定データ作成ロジック
     */
	public void setKikanSiteiListLogic(KikanSiteiMapLogic kikanSiteiListLogic) {
		this.kikanSiteiListLogic = kikanSiteiListLogic;
	}

    /**
     * メニュー権限チェックロジックを取得する
     * @return メニュー権限チェックロジック
     */
    public AccessCtlCheckLogic getAccessCtlCheckLogic() {
        return accessCtlCheckLogic;
    }

    /**
     * メニュー権限チェックロジックを設定する
     * @param accessCtlCheckLogic メニュー権限チェックロジック
     */
    public void setAccessCtlCheckLogic(AccessCtlCheckLogic accessCtlCheckLogic) {
        this.accessCtlCheckLogic = accessCtlCheckLogic;
    }
}