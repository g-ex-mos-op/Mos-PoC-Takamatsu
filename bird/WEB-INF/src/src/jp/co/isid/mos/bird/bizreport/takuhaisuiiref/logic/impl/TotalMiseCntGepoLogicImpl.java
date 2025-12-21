package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiConstants;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dao.TotalMiseGepoCntDao;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.TotalMiseGepoCnt;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TotalMiseCntGepoLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 業績管理 宅配売上推移表
 * 月次店舗数取得ロジック
 *
 * @author xwatanabe
 */
public class TotalMiseCntGepoLogicImpl implements TotalMiseCntGepoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR004L04";

    /** 店舗数月次取得DAO */
    private TotalMiseGepoCntDao totalMiseGepoCntDao;

    /** ユーザータイプコード:本部(01) */
    private final String HONBU = "01";
    /** ユーザータイプコード:オーナー(02) */
    private final String ONER = "02";
    /** ユーザータイプコード店舗(03) */
    private final String TENPO = "03";


    /**
     * 日次店舗数を取得する<br>
     * @param  Map  パラメータMap
     * @return int  日次店舗数
     * @throws Exception 想定外エラー
     */
    public int execute(Map argsMap) {

        //入力チェック
        validate(argsMap);
        
        //入力値取得
        String userType      = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_USER_TYPE);
        String companyCd     = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_COMPANY_CD);
        String userId        = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_USER_ID);
        boolean limitFlg    = ((Boolean)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_LIMIT_FLG)).booleanValue();
        String onerCd        = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_ONER_CD);
        String taishoJoken   = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_JOKEN);
        String hyojiTaisho   = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A);
        String zenDataShubetu= (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_ZENDATA_SHUBETU);
        String kikanSitei    = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_KIKAN_SITEI);

        //返却用
        int tenposu = 0;

        //表示対象の期間
        String startYm = "";
        String endYm   = "";

        //-------------------
        // 本部ユーザの場合
        //-------------------
        if (HONBU.equals(userType)) {

            try {
                //表示対象の期間を求める
                String taishoKikan = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_KIKAN);
                if ( taishoKikan != null && TaishoKikan.CODE_MONTH.equals(taishoKikan) ) {
                    //任意月指定の場合
                    startYm = DateManager.getPrevMonth(kikanSitei,11);
                    endYm   = kikanSitei;
                    
                } else if ( taishoKikan != null && TaishoKikan.CODE_NENDO.equals(taishoKikan) ){
                    //年度指定の場合
                    startYm = kikanSitei + "04";
                    endYm   = DateManager.getNextYear(kikanSitei,1) + "03";
                }
            } catch (Exception e) {
            }

            //店舗種別
            String tenpoShubetu = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TENPO_SHUBETU);

            //ブロックコード
            String blockCd = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_BLOCK_CD);
            
            //ロジック実行
            List tmp = totalMiseGepoCntDao.selectHonbuTotalMiseGepoCnt(companyCd, userId, tenpoShubetu, 
                    startYm, endYm, taishoJoken, hyojiTaisho, blockCd, zenDataShubetu, limitFlg);
            if(tmp != null && !tmp.isEmpty()){
                TotalMiseGepoCnt entity = (TotalMiseGepoCnt)tmp.get(0);
                tenposu = entity.getTotalTempoCnt();
            }
        }

        //-------------------
        // オーナーの場合
        //-------------------
        if (ONER.equals(userType)) {

            try {
                //オーナーは無条件で任意月指定
                startYm = DateManager.getPrevMonth(kikanSitei,11);
                endYm   = kikanSitei;
            } catch (Exception e) {
            }

            List tmp = totalMiseGepoCntDao.selectOnerTotalMiseGepoCnt(
                    companyCd, userId, startYm, endYm, taishoJoken, hyojiTaisho, limitFlg,onerCd);
            if(tmp != null && !tmp.isEmpty()){
                TotalMiseGepoCnt entity = (TotalMiseGepoCnt)tmp.get(0);
                tenposu = entity.getTotalTempoCnt();
            }
        }

        //-------------------
        // 店舗ユーザの場合
        //-------------------
        if (TENPO.equals(userType)) {

            try {
                //店舗ユーザは無条件で任意月指定
                startYm = DateManager.getPrevMonth(kikanSitei,11);
                endYm   = kikanSitei;
            } catch (Exception e) {
            }

            List tmp = totalMiseGepoCntDao.selectTenpoTotalMiseGepoCnt(companyCd, userId, startYm, endYm, limitFlg);
            if(tmp != null && !tmp.isEmpty()){
                TotalMiseGepoCnt entity = (TotalMiseGepoCnt)tmp.get(0);
                tenposu = entity.getTotalTempoCnt();
            }
        }

        //返却
        return tenposu;
    }

    /**
     * 入力チェック
     */
    private void validate(Map argsMap) {

        //ユーザタイプ
        String userType  = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_USER_TYPE);
        if (userType == null || userType.length() == 0) {
            throw new NotNullException(TakuhaiSuiiConstants.MSG_USER_TYPE);
        }
        //会社コード
        String companyCd = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_COMPANY_CD);
        if (companyCd == null || companyCd.length() == 0) {
            throw new NotNullException(TakuhaiSuiiConstants.MSG_USER_COMPANYCD);
        }
        //ユーザID
        String userId = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_USER_ID);
        if (userId == null || userId.length() == 0) {
            throw new NotNullException(TakuhaiSuiiConstants.MSG_USER_ID);
        }
        //対象条件
        String taishoJoken = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_JOKEN);
        if (taishoJoken == null || taishoJoken.length() == 0) {
            throw new NotNullException(TakuhaiSuiiConstants.MSG_TAISHO_JOKEN);
        }
        //制限区分
        Boolean limitKbn = (Boolean)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_LIMIT_FLG);
        if (limitKbn == null) {
            throw new NotNullException(TakuhaiSuiiConstants.MSG_LIMIT_FLG);
        }
        //前年データ種別
        String zenDataShubetu = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_ZENDATA_SHUBETU);
        if (zenDataShubetu == null || zenDataShubetu.length() == 0) {
            throw new NotNullException(TakuhaiSuiiConstants.MSG_ZENDATA_SHUBETU);
        }

        //本部ユーザの時
        if (userType.equals(HONBU)) {
            
            //店舗種別
            String tenpoShubetu = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TENPO_SHUBETU);
            if (tenpoShubetu == null || tenpoShubetu.length() == 0) {
                throw new NotNullException(TakuhaiSuiiConstants.MSG_TENPO_SHUBETU);
            }

            //対象期間
            String taishoKikan = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_KIKAN);
            if (taishoKikan == null || taishoKikan.length() == 0) {
                throw new NotNullException(TakuhaiSuiiConstants.MSG_TAISHO_KIKAN);
            }

            //表示対象
            if (taishoJoken.equals(TaishoJoken.CODE_SIBU)) {
                String hyojiTaisho = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A);
                if (hyojiTaisho == null || hyojiTaisho.length() == 0) {
                    throw new NotNullException(TakuhaiSuiiConstants.MSG_HYOJI_TAISHO);
                }
            } else if (!taishoJoken.equals(TaishoJoken.CODE_ALL)) {
                String hyojiTaisho = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A);
                if (hyojiTaisho == null || hyojiTaisho.length() == 0) {
                    throw new NotNullException(TakuhaiSuiiConstants.MSG_HYOJI_TAISHO);
                }
            }
        }

        //オーナーの時
        if (userType.equals(ONER)) {
            
            //オーナーコード
            String onerCd = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_ONER_CD);
            if (onerCd == null || onerCd.length() == 0) {
                throw new NotNullException(TakuhaiSuiiConstants.MSG_ONER_CD);
            }

            //表示対象(対象条件が店舗の時のみ)
            if (taishoJoken.equals(TaishoJoken.CODE_MISE)){
                String hyojiTaisho = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A);
                if (hyojiTaisho == null || hyojiTaisho.length() == 0) {
                    throw new NotNullException(TakuhaiSuiiConstants.MSG_HYOJI_TAISHO);
                }
            }
        }
    }

    
    /**
     * 店舗数月次取得DAOを取得します<br>
     * @return 店舗数月次取得DAO
     */
    public TotalMiseGepoCntDao getTotalMiseGepoCntDao() {
        return totalMiseGepoCntDao;
    }

    /**
     * 店舗数月次取得DAOを設定します<br>
     * @param 店舗数月次取得DAO
     */
    public void setTotalMiseGepoCntDao(TotalMiseGepoCntDao dao) {
        this.totalMiseGepoCntDao = dao;
    }

}
