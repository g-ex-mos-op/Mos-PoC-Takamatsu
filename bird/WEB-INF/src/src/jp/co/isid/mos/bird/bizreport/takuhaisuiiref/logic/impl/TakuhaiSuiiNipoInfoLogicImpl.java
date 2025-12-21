package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiCommon;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiConstants;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dao.NipoInfoDao;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.NipoInfo;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TakuhaiSuiiNipoInfoLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 業績管理　宅配売上推移日次情報取得ロジック
 *
 * @author xwatanabe
 */
public class TakuhaiSuiiNipoInfoLogicImpl implements TakuhaiSuiiNipoInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR004L01";

    /** 宅配推移表日次取得DAO */
    private NipoInfoDao nipoInfoDao;

    /**
     * 宅配売上推移日次情報を取得する<br>
     * @param  Map  条件部の引数マップ
     * @param  Map  宅配対象店舗数マップ("年月(YYYYMM)"をキーに店舗数(Integer)を保持)
     * @throws Exception 想定外エラー
     */
    public Map execute(Map argsMap, Map tenpoCntMap) {

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
        String appdate       = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_APPDATE);
        String tenpoShubetu  = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TENPO_SHUBETU);
        String blockCd       = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_BLOCK_CD);

        //返却用
        Map  retMap     = new HashMap();
        List gamenList  = null;
        List nipoList   = null;
        List renipoList = null;

        //表示対象の期間
        String startYmd  = "";
        String endYmd    = "";
        String taishoYm  = "";  //表示する対象の年月
        
        //-------------------
        // 本部ユーザの場合
        //-------------------
        if (TakuhaiSuiiConstants.USERTYPE_HONBU.equals(userType)) {

            //表示対象の期間を求める
            String taishoKikan = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_KIKAN);
            if ( taishoKikan != null && TaishoKikan.CODE_MONTH.equals(taishoKikan) ) {
                //任意月指定の場合
                startYmd = kikanSitei + "01";
                endYmd   = kikanSitei + "31";
                taishoYm = kikanSitei;
                
            } else if ( taishoKikan != null && TaishoKikan.CODE_NENDO.equals(taishoKikan) ){
                //年度指定の場合
                startYmd = TakuhaiSuiiCommon.getTyokkinYm(kikanSitei, appdate) + "01";
                endYmd   = TakuhaiSuiiCommon.getTyokkinYm(kikanSitei, appdate) + "31";
                taishoYm = TakuhaiSuiiCommon.getTyokkinYm(kikanSitei, appdate);
            }

            //宅配対象店舗数マップより対象月の店舗数を取得
            Integer tenpoCnt = (Integer)tenpoCntMap.get(taishoYm);
            if(tenpoCnt == null){
                tenpoCnt = new Integer(0);
            }
            
            
            //リスト取得
            nipoList = nipoInfoDao.getNipoInfoListHonbu(companyCd, userId, tenpoShubetu, 
                    startYmd, endYmd, taishoJoken, hyojiTaisho, blockCd, zenDataShubetu, limitFlg);

            //2009/02/16 閏年翌年の2月が指定され、全店または前年対象店（前年同月）の時は、前年値を29日分取得する
            if (isNeedLeapProcess(taishoYm) && TaishoKikan.CODE_MONTH.equals(taishoKikan)) {
                String leapYearDt = taishoYm + "99";
                String tounenKizyunDt = taishoYm + "28";
                if (TenpoShubetu.CODE_ALL.equals(tenpoShubetu) 
                        || (TenpoShubetu.CODE_ZENNEN.equals(tenpoShubetu) && ZennenDataShubetu.CODE_DOGETU.equals(zenDataShubetu))) {
                    List listLeapInfo = nipoInfoDao.getNipoInfoListHonbuLeapYear(companyCd, userId, tenpoShubetu, 
                                            leapYearDt, tounenKizyunDt, taishoJoken, hyojiTaisho, blockCd, zenDataShubetu, limitFlg);
                    if (!(listLeapInfo == null || listLeapInfo.isEmpty())) {
                        nipoList.add(listLeapInfo.get(0));
                    }
                }
            }
            
            if (nipoList != null && nipoList.size() > 0) {
                //画面表示用リストの作成
                gamenList = makeGamenList(nipoList, tenpoCnt);
            }
        }

        //-------------------
        // オーナーの場合
        //-------------------
        if (TakuhaiSuiiConstants.USERTYPE_ONER.equals(userType)) {

            //オーナーは無条件で任意月指定
            startYmd = kikanSitei + "01";
            endYmd   = kikanSitei + "31";
            taishoYm = kikanSitei;
            
            nipoList = nipoInfoDao.getNipoInfoListOner(
                    companyCd, userId, onerCd, startYmd, endYmd, taishoJoken, zenDataShubetu, hyojiTaisho,limitFlg);

            //宅配対象店舗数マップより対象月の店舗数を取得
            Integer tenpoCnt = (Integer)tenpoCntMap.get(taishoYm);
            if(tenpoCnt == null){
                tenpoCnt = new Integer(0);
            }

            //2009/02/16 閏年翌年の2月が指定され、前年対象店（前年同月）の時は、前年値を29日分取得する
            if (isNeedLeapProcess(taishoYm)) {
                String leapYearDt = taishoYm + "99";
                String tounenKizyunDt = taishoYm + "28";
                if (ZennenDataShubetu.CODE_DOGETU.equals(zenDataShubetu)) {
                    List listLeapInfo = nipoInfoDao.getNipoInfoListOnerLeapYear(companyCd, userId, onerCd, leapYearDt, tounenKizyunDt, taishoJoken, zenDataShubetu, hyojiTaisho,limitFlg);
                    if (!(listLeapInfo == null || listLeapInfo.isEmpty())) {
                        nipoList.add(listLeapInfo.get(0));
                    }
                }
            }
            
            if (nipoList != null && nipoList.size() > 0) {
                //画面表示用リストの作成
                gamenList = makeGamenList(nipoList, tenpoCnt);
            }
        }

        //-------------------
        // 店舗ユーザの場合
        //-------------------
        if (TakuhaiSuiiConstants.USERTYPE_MISE.equals(userType)) {

            //店舗ユーザは無条件で任意月指定
            startYmd = kikanSitei + "01";
            endYmd   = kikanSitei + "31";
            taishoYm = kikanSitei;

            //宅配対象店舗数マップより対象月の店舗数を取得
            Integer tenpoCnt = (Integer)tenpoCntMap.get(taishoYm);
            if(tenpoCnt == null){
                tenpoCnt = new Integer(0);
            }

            nipoList = nipoInfoDao.getNipoInfoListMise(
                    companyCd, userId, startYmd, endYmd, zenDataShubetu, hyojiTaisho, limitFlg);

            //2009/02/16 閏年翌年の2月が指定され、前年対象店（前年同月）の時は、前年値を29日分取得する
            if (isNeedLeapProcess(taishoYm)) {
                String leapYearDt = taishoYm + "99";
                String tounenKizyunDt = taishoYm + "28";
                if (ZennenDataShubetu.CODE_DOGETU.equals(zenDataShubetu)) {
                    List listLeapInfo = nipoInfoDao.getNipoInfoListMiseLeapYear(companyCd, userId, leapYearDt, tounenKizyunDt, zenDataShubetu, hyojiTaisho, limitFlg);
                    if (!(listLeapInfo == null || listLeapInfo.isEmpty())) {
                        nipoList.add(listLeapInfo.get(0));
                    }
                }
            }
            
            if (nipoList != null && nipoList.size() > 0) {
                //画面表示用リストの作成
                gamenList = makeGamenList(nipoList, tenpoCnt);
            }
        }
        
        //リストを返却
        retMap.put(TakuhaiSuiiConstants.MAPKEY_NIPO_GAMEN_LIST,   gamenList);
        retMap.put(TakuhaiSuiiConstants.MAPKEY_NIPO_LIST,    nipoList);
        retMap.put(TakuhaiSuiiConstants.MAPKEY_RE_NIPO_LIST, renipoList);
        return retMap;
    }
    
    /**
     * 前年を求める（年度）
     * @param taishoYm
     * @return
     */
    private String getPrevYear(String taishoYm) {
        try {
            return DateManager.getPrevYear(taishoYm.substring(0, 4), 1);
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
    }

    /**
     * 閏年翌年2月の特別処理が必要か
     * @param taishoYm
     * @return　true：特別処理が必要
     */
    private boolean isNeedLeapProcess(String taishoYm) {
        boolean flg = false;
        if (taishoYm.substring(4, 6).equals("02")) {
            String prevYear = getPrevYear(taishoYm.substring(0, 4));
            if (DateManager.isLeapYear((new Integer(prevYear)).intValue())) {
                flg = true;
            }
        }

        return flg;
    }
    /**
     * 画面表示用のリストを作成する
     * @param  List    日次情報リスト
     * @param  Integer (対象年月の)宅配対象店舗数 --> 合計欄などで使用する
     * @return List 
     */
    private List makeGamenList(List nipoList, Integer tenpoCnt) {

        for(int i=0;i<nipoList.size();i++){
            
            NipoInfo nipoInfo = (NipoInfo)nipoList.get(i);

            //----------------------------------
            // 通常データ行の計算項目を計算する
            //----------------------------------
            nipoInfo = calculate(nipoInfo);

            //----------------------------------
            // 通常データ行の表示スタイルの指定
            //----------------------------------
            nipoInfo = setStyleNomal(nipoInfo);
        }

        //合計欄用のエンティティを作成・リストに追加
        NipoInfo sum = makeSumRow(nipoList, tenpoCnt);
        nipoList.add(sum);

        return nipoList;
    }

    /**
     * (補正以外の時の)合計欄の行データを作成する
     * @param  nipoList 日次情報リスト(計算項目は計算済み)
     * @param  tenpoCnt 宅配対象店舗数
     * @return sum      合計欄行データ 
     */
    private NipoInfo makeSumRow(List nipoList, Integer tenpoCnt) {

        //合計欄データ
        NipoInfo sum = new NipoInfo();
        sum.setRowType(TakuhaiSuiiConstants.ROW_TYPE_SUM);
        sum.setEigyoDt("月合計");
        sum.setHanTenpoCnt(tenpoCnt.intValue());    //宅配対象店舗数

        //計算用
        BigDecimal tmp1 = new BigDecimal(0);
        BigDecimal tmp2 = new BigDecimal(0);
        
        for(int i=0; i < nipoList.size(); i++) {

            //対象レコードを取り出す
            NipoInfo nipoInfo = (NipoInfo)nipoList.get(i);
            
            //-----------------
            // 各値を合計
            //-----------------
            //売上
            tmp1 = sum.getUriage();                         
            tmp2 = nipoInfo.getUriage();
            sum.setUriage(tmp1.add(tmp2));

            //前年売上
            tmp1 = sum.getUriageZen();
            tmp2 = nipoInfo.getUriageZen();
            sum.setUriageZen(tmp1.add(tmp2));

            //宅配売上
            tmp1 = sum.getUriageTaku();
            tmp2 = nipoInfo.getUriageTaku();
            sum.setUriageTaku(tmp1.add(tmp2));

            //前年宅配売上
            tmp1 = sum.getUriageTakuZen();
            tmp2 = nipoInfo.getUriageTakuZen();
            sum.setUriageTakuZen(tmp1.add(tmp2));

            //客数
            tmp1 = sum.getKyakusu();
            tmp2 = nipoInfo.getKyakusu();
            sum.setKyakusu(tmp1.add(tmp2));

            //前年客数
            tmp1 = sum.getKyakusuZen();
            tmp2 = nipoInfo.getKyakusuZen();
            sum.setKyakusuZen(tmp1.add(tmp2));

            //宅配件数
            tmp1 = sum.getKyakusuTaku();
            tmp2 = nipoInfo.getKyakusuTaku();
            sum.setKyakusuTaku(tmp1.add(tmp2));

            //前年宅配件数
            tmp1 = sum.getKyakusuTakuZen();
            tmp2 = nipoInfo.getKyakusuTakuZen();
            sum.setKyakusuTakuZen(tmp1.add(tmp2));
            //前年データ種別が『前年同月営業日補正』の場合
            if(nipoInfo.isHosei()) {
	            sum.setHoseiFlg(1);
	            /*CSVダウンロード用*/            
	            //売上(補正)
	            tmp1 = sum.getUriageHosei();
	            tmp2 = nipoInfo.getUriageHosei();
	            sum.setUriageHosei(tmp1.add(tmp2));
	
	            //前年売上(補正)
	            tmp1 = sum.getUriageZenHosei();
	            tmp2 = nipoInfo.getUriageZenHosei();
	            sum.setUriageZenHosei(tmp1.add(tmp2));
	
	            //宅配売上(補正)
	            tmp1 = sum.getUriageTakuHosei();
	            tmp2 = nipoInfo.getUriageTakuHosei();
	            sum.setUriageTakuHosei(tmp1.add(tmp2));
	
	            //前年宅配売上(補正)
	            tmp1 = sum.getUriageTakuZenHosei();
	            tmp2 = nipoInfo.getUriageTakuZenHosei();
	            sum.setUriageTakuZenHosei(tmp1.add(tmp2));
	
	            //客数(補正)
	            tmp1 = sum.getKyakusuHosei();
	            tmp2 = nipoInfo.getKyakusuHosei();
	            sum.setKyakusuHosei(tmp1.add(tmp2));
	
	            //前年客数(補正)
	            tmp1 = sum.getKyakusuZenHosei();
	            tmp2 = nipoInfo.getKyakusuZenHosei();
	            sum.setKyakusuZenHosei(tmp1.add(tmp2));
	
	            //宅配件数(補正)
	            tmp1 = sum.getKyakusuTakuHosei();
	            tmp2 = nipoInfo.getKyakusuTakuHosei();
	            sum.setKyakusuTakuHosei(tmp1.add(tmp2));
	
	            //前年宅配件数(補正)
	            tmp1 = sum.getKyakusuTakuZenHosei();
	            tmp2 = nipoInfo.getKyakusuTakuZenHosei();
	            sum.setKyakusuTakuZenHosei(tmp1.add(tmp2));
            }
        }

        //----------------------
        // 計算項目を計算
        //----------------------
        sum = calculate(sum);

        //----------------------
        // 表示スタイルの指定
        //----------------------
        sum = setStyleSum(sum);

        //返却
        return sum;
    }

    /**
     * 合計欄行の表示スタイルをセットし、結果を格納。
     * 計算項目については計算後であること。
     * @param  List 日次情報リスト(合計行)(計算後)
     * @return List 日次情報リスト(合計行)
     */
    private NipoInfo setStyleSum(NipoInfo sum) {

        BigDecimal tmp;

        //前年比売上のクラス
        tmp = sum.getZenhiUriage();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiUriage(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiUriage(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //前年比宅配売上のクラス
        tmp = sum.getZenhiUriageTaku();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //前年比客数のクラス
        tmp = sum.getZenhiKyakusu();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //前年比宅配件数のクラス
        tmp = sum.getZenhiKyakusuTaku();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //前年比客単価のクラス
        tmp = sum.getZenhiKyakuTanka();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //返却
        return sum;
    }

    /**
     * 通常データ行の表示スタイルをセットし、結果を格納。
     * 計算項目については計算後であること。
     * @param  List 日次情報リスト(通常データ行)(計算後)
     * @return List 日次情報リスト(通常データ行)
     */
    private NipoInfo setStyleNomal(NipoInfo nipoInfo) {

        BigDecimal tmp;
        //前年比売上のクラス
        tmp = nipoInfo.getZenhiUriage();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            nipoInfo.setClassZenHiUriage(TakuhaiSuiiConstants.TD_HIRITU);
        } else {
            nipoInfo.setClassZenHiUriage(TakuhaiSuiiConstants.TD_HIRITU_RED);
        }
        //前年比宅配売上のクラス
        tmp = nipoInfo.getZenhiUriageTaku();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            nipoInfo.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_HIRITU);
        } else {
            nipoInfo.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_HIRITU_RED);
        }
        //前年比客数のクラス
        tmp = nipoInfo.getZenhiKyakusu();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            nipoInfo.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_HIRITU);
        } else {
            nipoInfo.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_HIRITU_RED);
        }
        //前年比宅配件数のクラス
        tmp = nipoInfo.getZenhiKyakusuTaku();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            nipoInfo.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_HIRITU);
        } else {
            nipoInfo.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_HIRITU_RED);
        }
        //前年比客単価のクラス
        tmp = nipoInfo.getZenhiKyakuTanka();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            nipoInfo.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_HIRITU);
        } else {
            nipoInfo.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_HIRITU_RED);
        }

        //返却
        return nipoInfo;
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
        if (TakuhaiSuiiConstants.USERTYPE_HONBU.equals(userType)) {
            
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
        if (TakuhaiSuiiConstants.USERTYPE_ONER.equals(userType)) {
            
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
     * 宅配推移表日次情報取得DAOを取得します<br>
     * @return 宅配推移表日次情報取得DAO
     */
    public NipoInfoDao getNipoInfoDao() {
        return nipoInfoDao;
    }

    /**
     * 宅配推移表日次情報取得DAOを設定します<br>
     * @param 宅配推移表日次情報取得DAO
     */
    public void setNipoInfoDao(NipoInfoDao logic) {
        this.nipoInfoDao = logic;
    }

	/**
     * 計算項目を計算し、結果を格納。【合計行・平均行用】【補正以外用】
     * @param  List 日次情報リスト(計算前)
     * @return List 日次情報リスト(計算後)
     */
    private NipoInfo calculate(NipoInfo nipoInfo) {
        BigDecimal tmp1  = new BigDecimal(0);
        BigDecimal tmp2 = new BigDecimal(0);

        //前年比売上
        tmp1 = Calculator.percentage(nipoInfo.getUriage(), nipoInfo.getUriageZen(), 2);
        nipoInfo.setZenhiUriage(tmp1);
        
        //前年比宅配売上
        tmp1 = Calculator.percentage(nipoInfo.getUriageTaku(), nipoInfo.getUriageTakuZen(), 2);
        nipoInfo.setZenhiUriageTaku(tmp1);

        //構成比売上
        tmp1 = Calculator.percentage(nipoInfo.getUriageTaku(), nipoInfo.getUriage(), 2);
        nipoInfo.setKouseihiUriage(tmp1);
        
        //宅配売上平均 -> 特になにもしない(各通常データ行の合計)
        tmp1 = Calculator.divide(nipoInfo.getUriageTaku(), new BigDecimal(nipoInfo.getHanTenpoCnt()), 0);
        nipoInfo.setTakuUriageAvg(tmp1);
        
        //前年比客数
        tmp1 = Calculator.percentage(nipoInfo.getKyakusu(), nipoInfo.getKyakusuZen(), 2);
        nipoInfo.setZenhiKyakusu(tmp1);

        //前年比宅配件数
        tmp1 = Calculator.percentage(nipoInfo.getKyakusuTaku(), nipoInfo.getKyakusuTakuZen(), 2);
        nipoInfo.setZenhiKyakusuTaku(tmp1);

        //構成比客数
        tmp1 = Calculator.percentage(nipoInfo.getKyakusuTaku(), nipoInfo.getKyakusu(), 2);
        nipoInfo.setKouseihiKyakusu(tmp1);
        
        //宅配件数平均 -> 特になにもしない(各通常データ行の合計)
        tmp1 = Calculator.divide(nipoInfo.getKyakusuTaku(), new BigDecimal(nipoInfo.getHanTenpoCnt()), 0);
        nipoInfo.setTakuKensuAvg(tmp1);
      
        //客単価
        tmp1 = Calculator.divide(nipoInfo.getUriageTaku(), nipoInfo.getKyakusuTaku(), 0);
        nipoInfo.setKyakuTanka(tmp1);
        
        //前年客単価
        tmp2 = Calculator.divide(nipoInfo.getUriageTakuZen(), nipoInfo.getKyakusuTakuZen(), 0);
        nipoInfo.setKyakuTankaZen(tmp2);

        //前年比客単価
        nipoInfo.setZenhiKyakuTanka(Calculator.percentage(nipoInfo.getKyakuTanka(), nipoInfo.getKyakuTankaZen(), 2));
        
        //前年データ種別が『前年同月営業日補正』の場合
        if(nipoInfo.isHosei()) {
            /* 
             * 前年データ種別が『前年同月営業日補正』の場合、
             * 前年比の値は全て補正値(NET値)で算出します
             */
            //前年比売上
            nipoInfo.setZenhiUriage(Calculator.percentage(nipoInfo.getUriageHosei(), nipoInfo.getUriageZenHosei(), 2));
            //前年比宅配売上
            nipoInfo.setZenhiUriageTaku(Calculator.percentage(nipoInfo.getUriageTakuHosei(), nipoInfo.getUriageTakuZenHosei(), 2));
            //前年比客数
            nipoInfo.setZenhiKyakusu(Calculator.percentage(nipoInfo.getKyakusuHosei(), nipoInfo.getKyakusuZenHosei(), 2));
            //前年比宅配客数
            nipoInfo.setZenhiKyakusuTaku(Calculator.percentage(nipoInfo.getKyakusuTakuHosei(), nipoInfo.getKyakusuTakuZenHosei(), 2));
	        //客単価(補正)
	        nipoInfo.setKyakuTankaHosei(Calculator.divide(nipoInfo.getUriageTakuHosei(), nipoInfo.getKyakusuTakuHosei(), 0));
	        //前年客単価(補正)
	        nipoInfo.setKyakuTankaZenHosei(Calculator.divide(nipoInfo.getUriageTakuZenHosei(), nipoInfo.getKyakusuTakuZenHosei(), 0));
            //前年比客単価(補正)
            nipoInfo.setZenhiKyakuTanka(Calculator.percentage(nipoInfo.getKyakuTankaHosei(), nipoInfo.getKyakuTankaZenHosei(), 2));
            
        }
        //返却
        return nipoInfo;
    }
}
