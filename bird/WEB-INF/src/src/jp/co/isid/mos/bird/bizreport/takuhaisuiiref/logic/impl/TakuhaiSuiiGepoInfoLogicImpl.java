package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiCommon;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiConstants;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dao.GepoInfoDao;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.GepoInfo;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TakuhaiSuiiGepoInfoLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 業績管理　宅配売上推移月次情報取得ロジック
 *
 * @author xwatanabe
 */
public class TakuhaiSuiiGepoInfoLogicImpl implements TakuhaiSuiiGepoInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR004L03";

    /** 月別宅配売上月報取得DAO */
    private GepoInfoDao gepoInfoDao;


    /**
     * 宅配売上推移月次情報を取得する
     * @param  Map  ユーザタイプ
     * @return List 月報リスト
     * @throws Exception 想定外エラー
     */
    public Map execute(Map argsMap) {

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
        String taishoKikan   = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_KIKAN);
        String tenpoShubetu  = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_TENPO_SHUBETU);
        String blockCd       = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_BLOCK_CD);

        //返却用
        Map  retMap     = new HashMap();
        List gamenList  = null;
        List gepoList   = null;
        
        //表示対象の期間
        String startYm = "";
        String endYm   = "";

        //-------------------
        // 本部ユーザの場合
        //-------------------
        if (TakuhaiSuiiConstants.USERTYPE_HONBU.equals(userType)) {

            try {
                //表示対象の期間を求める
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
            

            //リスト取得
            gepoList = gepoInfoDao.getGepoInfoHonbuList(companyCd, userId, tenpoShubetu, startYm, endYm, taishoJoken, hyojiTaisho, blockCd, zenDataShubetu, limitFlg);
            if(gepoList != null && gepoList.size() >0) {

                //任意月指定の場合
                if (taishoKikan != null && TaishoKikan.CODE_MONTH.equals(taishoKikan)){
                    //画面表示用リストの作成
                    gamenList = createListWhenNini(gepoList, zenDataShubetu);
                }
                //年度指定の場合
                else if(taishoKikan != null && TaishoKikan.CODE_NENDO.equals(taishoKikan)){
                    //画面表示用リストの作成
                    gamenList = createListWhenNendo(startYm, gepoList, zenDataShubetu);
                }
            }
        }

        //-------------------
        // オーナーの場合
        //-------------------
        if (TakuhaiSuiiConstants.USERTYPE_ONER.equals(userType)) {
 
            try {
                //オーナーは無条件で任意月指定
                startYm = DateManager.getPrevMonth(kikanSitei,11);
                endYm   = kikanSitei;
            } catch (Exception e) {
            }

            gepoList = gepoInfoDao.getGepoInfoOnerList(
                    companyCd, userId, onerCd, startYm, endYm, taishoJoken, zenDataShubetu, hyojiTaisho,limitFlg);
            if(gepoList != null && gepoList.size() >0) {
                //画面表示用リストの作成(オーナーは無条件で任意月指定)
                gamenList = createListWhenNini(gepoList, zenDataShubetu);
            }
        }

        //-------------------
        // 店舗ユーザの場合
        //-------------------
        if (TakuhaiSuiiConstants.USERTYPE_MISE.equals(userType)) {

            try {
                //店舗ユーザは無条件で任意月指定
                startYm = DateManager.getPrevMonth(kikanSitei,11);
                endYm   = kikanSitei;
            } catch (Exception e) {
            }

            gepoList = gepoInfoDao.getGepoInfoTenpoList(
                    companyCd, userId, startYm, endYm, zenDataShubetu, limitFlg);
            if(gepoList != null && gepoList.size() >0) {
                //画面表示用リストの作成(店舗ユーザは無条件で任意月指定)
                gamenList = createListWhenNini(gepoList, zenDataShubetu);
                
            }
        }

        //-----------------------------------------------------------
        // 取得したリストを元に、「月末宅配対象店舗数」リストを作成
        //-----------------------------------------------------------
        Map tenpoCntMap  = creatTenpoCntMap(gamenList);
        
        
        //リストを返却
        retMap.put(TakuhaiSuiiConstants.MAPKEY_GEPO_GAMEN_LIST,   gamenList);
        retMap.put(TakuhaiSuiiConstants.MAPKEY_TENPO_CNT_MAP,   tenpoCntMap);
        return retMap;
    }

    /**
     * 画面表示用のリストを作成する【任意月指定・補正以外】
     * @param  List 日次情報リスト
     * @return List 
     */
    private Map creatTenpoCntMap(List gamenList) {

        if (gamenList == null || gamenList.size() == 0){
            return null;
        }
        
        //返却用
        Map retMap = new HashMap();

        for (int i=0; i<gamenList.size(); i++) {

            //１レコード取得
            GepoInfo gepoInfo = (GepoInfo)gamenList.get(i);

            //年月
            String ym = gepoInfo.getEigyoDt();
            //月末宅配対象店舗数
            int tenpoCnt = gepoInfo.getTakuTaishoTenpoCnt();

            if(ym != null && ym.length() == 6){
                retMap.put(ym, new Integer(tenpoCnt));
            }
        }
        
        return retMap;
    }

    /**
     * 画面表示用のリストを作成する【任意月指定・補正以外】
     * 
     * @param gepoList
     * @param zennenDataShubetu
     * @return
     */
    private List createListWhenNini(List gepoList, String zennenDataShubetu) {

        //返却用リスト
        List retList = new ArrayList();

        //通常データ行のサイズ
        BigDecimal dataSize   = new BigDecimal(gepoList.size());

        //---------------------
        // 通常データ行の編集
        //---------------------
        for(int i=0; i < gepoList.size(); i++){

            //１レコード取得
            GepoInfo gepoInfo = (GepoInfo)gepoList.get(i);

            //計算項目を計算する
            gepoInfo = calculateData(gepoInfo, zennenDataShubetu);
            
            //表示スタイルのセット
            gepoInfo = setStyle(gepoInfo);

            //リストに追加
            retList.add(gepoInfo);
        }
            
        //---------------------
        // 合計行・平均行の編集
        //---------------------
        //合計欄用のエンティティを作成
        GepoInfo sum = makeSumRow1(retList, zennenDataShubetu);

        //平均欄用のエンティティを作成
        GepoInfo avg = makeAvgRow1(sum, dataSize.intValue(), zennenDataShubetu);

        //リストに追加
        retList.add(avg);
        retList.add(sum);
        
        return retList;
    }
    /**
     * 画面表示用のリストを作成する【年度指定】【補正以外】
     * @param  String 表示対象年月(開始)
     * @param  List   月次情報リスト
     * @param  List   月次情報リスト(再取得)
     * @return List 
     */
    private List createListWhenNendo(String startYm, List gepoList, String zennenDataShubetu) {

        //返却用リスト
        List retList = new ArrayList();

        //対象年月分まわす
        List ymList = TakuhaiSuiiCommon.makeListMonth(startYm,12);
        for(int i=0 ; i < ymList.size() ; i++){
 
            //計算対象年月
            String yyyymm = (String)ymList.get(i);

            GepoInfo gepoInfo = new GepoInfo();
            for(int j=0 ; j<gepoList.size(); j++){

                gepoInfo   = (GepoInfo)gepoList.get(j);

                //対象YMに合致するレコードの時
                if(yyyymm.equals(gepoInfo.getEigyoDt())){
                    gepoInfo = calculateData(gepoInfo, zennenDataShubetu);
                    break;
                }
                //対象YMに合致するレコードのが見つからない時
                else if(j == gepoList.size()-1){
                    gepoInfo = new GepoInfo();
                    gepoInfo.setEigyoDt(yyyymm);
                }
            }

            //---------------------
            // 通常データ行の編集
            //---------------------
            //表示スタイルをセット
            gepoInfo = setStyle(gepoInfo);
            //返却用リストに追加
            retList.add(gepoInfo);
        }
        
        //-----------------------------------------
        // 集計行(四半期・半期・通期)の編集と追加
        //-----------------------------------------
        retList = addSyukeiRow(retList, zennenDataShubetu);

        //リスト返却
        return retList;
    }

    /**
     * 計算項目を計算し、結果を格納。【通常データ行用】【補正以外用】
     * 
     * @param gepoInfo 月次情報エンティティー(計算前)
     * @param zennenDataShubetu
     * @return GepoInfo 月次情報エンティティー(計算済)
     */
    private GepoInfo calculateData(GepoInfo gepoInfo, String zennenDataShubetu) {
        
        //宅配売上平均
        gepoInfo.setTakuUriageAvg(Calculator.divide(gepoInfo.getUriageTaku(), new BigDecimal(gepoInfo.getTakuTaishoTenpoCnt()), 0));
        //宅配件数平均
        gepoInfo.setTakuKensuAvg(Calculator.divide(gepoInfo.getKyakusuTaku(), new BigDecimal(gepoInfo.getTakuTaishoTenpoCnt()), 0));
        //返却
        return calculate(gepoInfo, zennenDataShubetu);
    }

    /**
     * 合計欄の行データを作成する【任意月指定・補正以外】
     * @param  gepoList 月次情報リスト(計算項目は計算済み)
     * @return sum      合計欄行データ 
     */
    private GepoInfo makeSumRow1(List gepoList, String zennenDataShubetu) {

        //合計欄用のエンティティを作成
        GepoInfo sum = new GepoInfo();
        sum.setRowType(TakuhaiSuiiConstants.ROW_TYPE_SUM);
//        sum.setEigyoDt(String.valueOf(gepoList.size()) + "ヶ月合計");
        sum.setEigyoDt("12ヶ月合計");       //12ヶ月で固定

        //計算用
        BigDecimal tmp1 = new BigDecimal(0);
        BigDecimal tmp2 = new BigDecimal(0);

        for (int i=0; i<gepoList.size(); i++){

            //対象レコードを取り出す
            GepoInfo gepoInfo = (GepoInfo)gepoList.get(i);

            //-----------------
            // 各値を合計
            //-----------------
            //売上
            tmp1 = sum.getUriage();
            tmp2 = gepoInfo.getUriage();
            sum.setUriage(tmp1.add(tmp2));

            //前年売上
            tmp1 = sum.getUriageZen();
            tmp2 = gepoInfo.getUriageZen();
            sum.setUriageZen(tmp1.add(tmp2));

            //宅配売上
            tmp1 = sum.getUriageTaku();
            tmp2 = gepoInfo.getUriageTaku();
            sum.setUriageTaku(tmp1.add(tmp2));

            //前年宅配売上
            tmp1 = sum.getUriageTakuZen();
            tmp2 = gepoInfo.getUriageTakuZen();
            sum.setUriageTakuZen(tmp1.add(tmp2));

            //客数
            tmp1 = sum.getKyakusu();
            tmp2 = gepoInfo.getKyakusu();
            sum.setKyakusu(tmp1.add(tmp2));

            //前年客数
            tmp1 = sum.getKyakusuZen();
            tmp2 = gepoInfo.getKyakusuZen();
            sum.setKyakusuZen(tmp1.add(tmp2));

            //宅配件数
            tmp1 = sum.getKyakusuTaku();
            tmp2 = gepoInfo.getKyakusuTaku();
            sum.setKyakusuTaku(tmp1.add(tmp2));

            //前年宅配件数
            tmp1 = sum.getKyakusuTakuZen();
            tmp2 = gepoInfo.getKyakusuTakuZen();
            sum.setKyakusuTakuZen(tmp1.add(tmp2));

            /* ここから合計行特有の計算 */
            //宅配売上平均
            tmp1 = sum.getTakuUriageAvg();
            tmp2 = gepoInfo.getTakuUriageAvg();
            sum.setTakuUriageAvg(tmp1.add(tmp2));

            //宅配件数平均
            tmp1 = sum.getTakuKensuAvg();
            tmp2 = gepoInfo.getTakuKensuAvg();
            sum.setTakuKensuAvg(tmp1.add(tmp2));
            
            //前年データ種別が『前年同月営業日補正』の場合
            if(ZennenDataShubetu.CODE_HOSEI.equals(zennenDataShubetu)) {
	            /*CSVダウンロード用*/
	            
	            //売上(補正)
	            tmp1 = sum.getUriageHosei();
	            tmp2 = gepoInfo.getUriageHosei();
	            sum.setUriageHosei(tmp1.add(tmp2));
	
	            //前年売上(補正)
	            tmp1 = sum.getUriageZenHosei();
	            tmp2 = gepoInfo.getUriageZenHosei();
	            sum.setUriageZenHosei(tmp1.add(tmp2));
	
	            //宅配売上(補正)
	            tmp1 = sum.getUriageTakuHosei();
	            tmp2 = gepoInfo.getUriageTakuHosei();
	            sum.setUriageTakuHosei(tmp1.add(tmp2));
	
	            //前年宅配売上(補正)
	            tmp1 = sum.getUriageTakuZenHosei();
	            tmp2 = gepoInfo.getUriageTakuZenHosei();
	            sum.setUriageTakuZenHosei(tmp1.add(tmp2));
	
	            //客数(補正)
	            tmp1 = sum.getKyakusuHosei();
	            tmp2 = gepoInfo.getKyakusuHosei();
	            sum.setKyakusuHosei(tmp1.add(tmp2));
	
	            //前年客数(補正)
	            tmp1 = sum.getKyakusuZenHosei();
	            tmp2 = gepoInfo.getKyakusuZenHosei();
	            sum.setKyakusuZenHosei(tmp1.add(tmp2));
	
	            //宅配件数(補正)
	            tmp1 = sum.getKyakusuTakuHosei();
	            tmp2 = gepoInfo.getKyakusuTakuHosei();
	            sum.setKyakusuTakuHosei(tmp1.add(tmp2));
	
	            //前年宅配件数(補正)
	            tmp1 = sum.getKyakusuTakuZenHosei();
	            tmp2 = gepoInfo.getKyakusuTakuZenHosei();
	            sum.setKyakusuTakuZenHosei(tmp1.add(tmp2));
            }
        }

        //----------------------------------
        // 計算項目を計算(合計行・補正以外)
        //----------------------------------
        sum = calculate(sum, zennenDataShubetu);

        //-------------------------
        // 合計欄用の表示スタイル
        //-------------------------
        //前年比売上のクラス
        tmp1 = sum.getZenhiUriage();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiUriage(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiUriage(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }
        //前年比宅配売上のクラス
        tmp1 = sum.getZenhiUriageTaku();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }
        //前年比客数のクラス
        tmp1 = sum.getZenhiKyakusu();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }
        //前年比宅配件数のクラス
        tmp1 = sum.getZenhiKyakusuTaku();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }
        //前年比客単価のクラス
        tmp1 = sum.getZenhiKyakuTanka();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            sum.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            sum.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //返却
        return sum;
    }
        
        
    /**
     * 計算項目を計算し、結果を格納。【合計行・平均行用】【補正以外用】
     * @param  List 日次情報リスト(計算前)
     * @return List 日次情報リスト(計算後)
     */
    private GepoInfo calculate(GepoInfo gepoInfo, String zennenDataShubetu) {
        BigDecimal tmp1  = new BigDecimal(0);
        BigDecimal tmp2 = new BigDecimal(0);

        //前年比売上
        tmp1 = Calculator.percentage(gepoInfo.getUriage(), gepoInfo.getUriageZen(), 2);
        gepoInfo.setZenhiUriage(tmp1);
        
        //前年比宅配売上
        tmp1 = Calculator.percentage(gepoInfo.getUriageTaku(), gepoInfo.getUriageTakuZen(), 2);
        gepoInfo.setZenhiUriageTaku(tmp1);

        //構成比売上
        tmp1 = Calculator.percentage(gepoInfo.getUriageTaku(), gepoInfo.getUriage(), 2);
        gepoInfo.setKouseihiUriage(tmp1);
        
        //宅配売上平均 -> 特になにもしない(各通常データ行の合計)
//        tmp1 = Calculator.divide(gepoInfo.getUriageTaku(), new BigDecimal(gepoInfo.getTakuTaishoTenpoCnt()), 0);
//        gepoInfo.setTakuUriageAvg(tmp1);
        
        //前年比客数
        tmp1 = Calculator.percentage(gepoInfo.getKyakusu(), gepoInfo.getKyakusuZen(), 2);
        gepoInfo.setZenhiKyakusu(tmp1);

        //前年比宅配件数
        tmp1 = Calculator.percentage(gepoInfo.getKyakusuTaku(), gepoInfo.getKyakusuTakuZen(), 2);
        gepoInfo.setZenhiKyakusuTaku(tmp1);

        //構成比客数
        tmp1 = Calculator.percentage(gepoInfo.getKyakusuTaku(), gepoInfo.getKyakusu(), 2);
        gepoInfo.setKouseihiKyakusu(tmp1);
        
        //宅配件数平均 -> 特になにもしない(各通常データ行の合計)
//        tmp1 = Calculator.divide(gepoInfo.getKyakusuTaku(), new BigDecimal(gepoInfo.getTakuTaishoTenpoCnt()), 0);
//        gepoInfo.setTakuKensuAvg(tmp1);
      
        //客単価
        tmp1 = Calculator.divide(gepoInfo.getUriageTaku(), gepoInfo.getKyakusuTaku(), 0);
        gepoInfo.setKyakuTanka(tmp1);
        
        //前年客単価
        tmp2 = Calculator.divide(gepoInfo.getUriageTakuZen(), gepoInfo.getKyakusuTakuZen(), 0);
        gepoInfo.setKyakuTankaZen(tmp2);

        //前年比客単価
        gepoInfo.setZenhiKyakuTanka(Calculator.percentage(gepoInfo.getKyakuTanka(), gepoInfo.getKyakuTankaZen(), 2));
        
        //前年データ種別が『前年同月営業日補正』の場合
        if(ZennenDataShubetu.CODE_HOSEI.equals(zennenDataShubetu)) {
            /* 
             * 前年データ種別が『前年同月営業日補正』の場合、
             * 前年比の値は全て補正値(NET値)で算出します
             */
            //前年比売上
            gepoInfo.setZenhiUriage(Calculator.percentage(gepoInfo.getUriageHosei(), gepoInfo.getUriageZenHosei(), 2));
            //前年比宅配売上
            gepoInfo.setZenhiUriageTaku(Calculator.percentage(gepoInfo.getUriageTakuHosei(), gepoInfo.getUriageTakuZenHosei(), 2));
            //前年比客数
            gepoInfo.setZenhiKyakusu(Calculator.percentage(gepoInfo.getKyakusuHosei(), gepoInfo.getKyakusuZenHosei(), 2));
            //前年比宅配客数
            gepoInfo.setZenhiKyakusuTaku(Calculator.percentage(gepoInfo.getKyakusuTakuHosei(), gepoInfo.getKyakusuTakuZenHosei(), 2));
	        //客単価(補正)
	        gepoInfo.setKyakuTankaHosei(Calculator.divide(gepoInfo.getUriageTakuHosei(), gepoInfo.getKyakusuTakuHosei(), 0));
	        //前年客単価(補正)
	        gepoInfo.setKyakuTankaZenHosei(Calculator.divide(gepoInfo.getUriageTakuZenHosei(), gepoInfo.getKyakusuTakuZenHosei(), 0));
            //前年比客単価(補正)
            gepoInfo.setZenhiKyakuTanka(Calculator.percentage(gepoInfo.getKyakuTankaHosei(), gepoInfo.getKyakuTankaZenHosei(), 2));
            
        }
        //返却
        return gepoInfo;
    }
        
    /**
     * 平均欄行データを合計欄行データを元に作成する
     * 
     * @param sum 合計欄行データ
     * @param size データ件数
     * @param zennenDataShubetu 前年データ種別
     * 
     * @return　GepoInfo 平均欄行データ 
     */
    private GepoInfo makeAvgRow1(GepoInfo sum, int size, String zennenDataShubetu) {

        //平均欄用のエンティティを作成
        GepoInfo avg = new GepoInfo();
        avg.setRowType(TakuhaiSuiiConstants.ROW_TYPE_AVG);
        avg.setEigyoDt("12ヶ月平均");       //12ヶ月で固定

        //計算用
        BigDecimal tmp1 = new BigDecimal(0);
        //データ件数
        BigDecimal dataCnt = new BigDecimal(size);
        
        //平均欄の計算
        tmp1 = Calculator.divide(sum.getUriage()        ,new BigDecimal(size), 0);
        avg.setUriage(tmp1);
        tmp1 = Calculator.divide(sum.getUriageZen()     ,new BigDecimal(size), 0);
        avg.setUriageZen(tmp1);
        tmp1 = Calculator.divide(sum.getUriageTaku()    , new BigDecimal(size), 0);
        avg.setUriageTaku(tmp1);
        tmp1 = Calculator.divide(sum.getUriageTakuZen() , new BigDecimal(size), 0);
        avg.setUriageTakuZen(tmp1);
        tmp1 = Calculator.divide(sum.getKyakusu()       , new BigDecimal(size), 0);
        avg.setKyakusu(tmp1);
        tmp1 = Calculator.divide(sum.getKyakusuZen()    , new BigDecimal(size), 0);
        avg.setKyakusuZen(tmp1);
        tmp1 = Calculator.divide(sum.getKyakusuTaku()   , new BigDecimal(size), 0);
        avg.setKyakusuTaku(tmp1);
        tmp1 = Calculator.divide(sum.getKyakusuTakuZen(), new BigDecimal(size), 0);
        avg.setKyakusuTakuZen(tmp1);
        tmp1 = Calculator.divide(sum.getTakuUriageAvg() , new BigDecimal(size), 0);
        avg.setTakuUriageAvg(tmp1);
        tmp1 = Calculator.divide(sum.getTakuKensuAvg()  , new BigDecimal(size), 0);
        avg.setTakuKensuAvg(tmp1);
        //前年データ種別が『前年同月営業日補正』の場合
        if(ZennenDataShubetu.CODE_HOSEI.equals(zennenDataShubetu)) {
	        //売上(補正)
	        avg.setUriageHosei(Calculator.divide(sum.getUriageHosei(),dataCnt,0));
	        //前年売上(補正)
	        avg.setUriageZenHosei(Calculator.divide(sum.getUriageZenHosei(),dataCnt,0));
	        //宅配売上(補正)
	        avg.setUriageTakuHosei(Calculator.divide(sum.getUriageTakuHosei(),dataCnt,0));
	        //前年宅配売上(補正)
	        avg.setUriageTakuZenHosei(Calculator.divide(sum.getUriageTakuZenHosei(),dataCnt,0));
	        //客数(補正)
	        avg.setKyakusuHosei(Calculator.divide(sum.getKyakusuHosei(),dataCnt,0));
	        //前年客数(補正)
	        avg.setKyakusuZenHosei(Calculator.divide(sum.getKyakusuZenHosei(),dataCnt,0));
	        //宅配件数(補正)
	        avg.setKyakusuTakuHosei(Calculator.divide(sum.getKyakusuTakuHosei(),dataCnt,0));
	        //前年宅配件数(補正)
	        avg.setKyakusuTakuZenHosei(Calculator.divide(sum.getKyakusuTakuZenHosei(),dataCnt,0));
        }
        //平均欄の計算項目を計算する
        avg = calculate(avg, zennenDataShubetu);   //計算

        //-------------------------
        // 合計欄用の表示スタイル
        //-------------------------
        //前年比売上のクラス
        tmp1 = avg.getZenhiUriage();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            avg.setClassZenHiUriage(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            avg.setClassZenHiUriage(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //前年比宅配売上のクラス
        tmp1 = avg.getZenhiUriageTaku();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            avg.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            avg.setClassZenHiUriageTaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //前年比客数のクラス
        tmp1 = avg.getZenhiKyakusu();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            avg.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            avg.setClassZenHiKyaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }
        
        //前年比宅配件数のクラス
        tmp1 = avg.getZenhiKyakusuTaku();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            avg.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            avg.setClassZenHiKyakuTaku(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }
        
        //前年比客単価のクラス
        tmp1 = avg.getZenhiKyakuTanka();
        if ( tmp1.compareTo(new BigDecimal(100)) >= 0 ){
            avg.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_SUM_NUM);
        } else {
            avg.setClassZenHiKyakuTanka(TakuhaiSuiiConstants.TD_SUM_NUM_RED);
        }

        //返却
        return avg;
    }

    
    /**
     * 集計行(四半期・半期・通期)の編集と追加【年度指定】【補正以外】
     * @param  gepoList 月次情報リスト(計算項目は計算済み)
     * @return gepoList 月次情報リスト(計算項目は計算済み)
     */
    private List addSyukeiRow(List gepoList, String zennenDataShubetu) {

        //返却用リスト
        List retList = new ArrayList();

        //第一四半期分レコード
        GepoInfo gepoInfoDai1Sihanki = new GepoInfo();
        gepoInfoDai1Sihanki.setRowType(TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_1);
        //第二四半期分レコード
        GepoInfo gepoInfoDai2Sihanki = new GepoInfo();
        gepoInfoDai2Sihanki.setRowType(TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_2);
        //第三四半期分レコード
        GepoInfo gepoInfoDai3Sihanki = new GepoInfo();
        gepoInfoDai3Sihanki.setRowType(TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_3);
        //第四四半期分レコード
        GepoInfo gepoInfoDai4Sihanki = new GepoInfo();
        gepoInfoDai4Sihanki.setRowType(TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_4);
        //上半期分レコード
        GepoInfo gepoInfoKamiHanki   = new GepoInfo();
        gepoInfoKamiHanki.setRowType(TakuhaiSuiiConstants.ROW_TYPE_HANKI_1);
        //下半期分レコード
        GepoInfo gepoInfoSimoHanki   = new GepoInfo();
        gepoInfoSimoHanki.setRowType(TakuhaiSuiiConstants.ROW_TYPE_HANKI_2);

        //合計行の計算用
        BigDecimal sumUriage         = new BigDecimal(0);
        BigDecimal sumUriageZen      = new BigDecimal(0);
        BigDecimal sumUriageTaku     = new BigDecimal(0);
        BigDecimal sumUriageTakuZen  = new BigDecimal(0);
        BigDecimal sumKyakusu        = new BigDecimal(0);
        BigDecimal sumKyakusuZen     = new BigDecimal(0);
        BigDecimal sumKyakusuTaku    = new BigDecimal(0);
        BigDecimal sumKyakusuTakuZen = new BigDecimal(0);
        BigDecimal sumTakuUriageAvg  = new BigDecimal(0);
        BigDecimal sumTakuKyakusuAvg = new BigDecimal(0);
        BigDecimal sumUriageHosei         = new BigDecimal(0);
        BigDecimal sumUriageZenHosei      = new BigDecimal(0);
        BigDecimal sumUriageTakuHosei     = new BigDecimal(0);
        BigDecimal sumUriageTakuZenHosei  = new BigDecimal(0);
        BigDecimal sumKyakusuHosei        = new BigDecimal(0);
        BigDecimal sumKyakusuZenHosei     = new BigDecimal(0);
        BigDecimal sumKyakusuTakuHosei    = new BigDecimal(0);
        BigDecimal sumKyakusuTakuZenHosei = new BigDecimal(0);

        //リスト件数分まわす
        for(int i=0; i < gepoList.size(); i++) {

            GepoInfo gepoInfo   = (GepoInfo)gepoList.get(i);

            //通常レコードを返却リストにセット
            retList.add(gepoInfo);

            //合計欄の計算
            sumUriage         = sumUriage.add(gepoInfo.getUriage());
            sumUriageZen      = sumUriageZen.add(gepoInfo.getUriageZen());
            sumUriageTaku     = sumUriageTaku.add(gepoInfo.getUriageTaku());
            sumUriageTakuZen  = sumUriageTakuZen.add(gepoInfo.getUriageTakuZen());
            sumKyakusu        = sumKyakusu.add(gepoInfo.getKyakusu());
            sumKyakusuZen     = sumKyakusuZen.add(gepoInfo.getKyakusuZen());
            sumKyakusuTaku    = sumKyakusuTaku.add(gepoInfo.getKyakusuTaku());
            sumKyakusuTakuZen = sumKyakusuTakuZen.add(gepoInfo.getKyakusuTakuZen());
            sumTakuUriageAvg  = sumTakuUriageAvg.add(gepoInfo.getTakuUriageAvg());
            sumTakuKyakusuAvg = sumTakuKyakusuAvg.add(gepoInfo.getTakuKensuAvg());
            //前年データ種別が『前年同月営業日補正』の場合
            if(ZennenDataShubetu.CODE_HOSEI.equals(zennenDataShubetu)) {
	            sumUriageHosei         = sumUriageHosei.add(gepoInfo.getUriageHosei());
	            sumUriageZenHosei      = sumUriageZenHosei.add(gepoInfo.getUriageZenHosei());
	            sumUriageTakuHosei     = sumUriageTakuHosei.add(gepoInfo.getUriageTakuHosei());
	            sumUriageTakuZenHosei  = sumUriageTakuZenHosei.add(gepoInfo.getUriageTakuZenHosei());
	            sumKyakusuHosei        = sumKyakusuHosei.add(gepoInfo.getKyakusuHosei());
	            sumKyakusuZenHosei     = sumKyakusuZenHosei.add(gepoInfo.getKyakusuZenHosei());
	            sumKyakusuTakuHosei    = sumKyakusuTakuHosei.add(gepoInfo.getKyakusuTakuHosei());
	            sumKyakusuTakuZenHosei = sumKyakusuTakuZenHosei.add(gepoInfo.getKyakusuTakuZenHosei());
            }

            //-----------------------------
            // 第一四半期分のレコードの時
            //-----------------------------
            if ( 0 <= i && i <= 2) {
            	addSyukeiRowSiHanki(gepoInfo, gepoInfoDai1Sihanki, zennenDataShubetu);

                if(i==2){
                    //計算項目をセット
                    gepoInfoDai1Sihanki = calculate(gepoInfoDai1Sihanki, zennenDataShubetu);

                    //表示内容をセット
                    gepoInfoDai1Sihanki = setStyle(gepoInfoDai1Sihanki);

                    //返却リストにセット
                    retList.add(gepoInfoDai1Sihanki);
                }
            }

            //-----------------------------
            // 第二四半期分のレコードの時
            //-----------------------------
            if ( 3 <= i && i <= 5) {
            	addSyukeiRowSiHanki(gepoInfo, gepoInfoDai2Sihanki, zennenDataShubetu);

                if(i==5){

                    //計算項目をセット
                    gepoInfoDai2Sihanki = calculate(gepoInfoDai2Sihanki, zennenDataShubetu);

                    //表示内容をセット
                    gepoInfoDai2Sihanki = setStyle(gepoInfoDai2Sihanki);

                    //返却リストにセット
                    retList.add(gepoInfoDai2Sihanki);
                }
            }

            //-----------------------------
            // 上半期分のレコードの時
            //-----------------------------
            if ( i == 5) {
            	addSyukeiRowHanki(gepoInfoDai1Sihanki, gepoInfoDai2Sihanki
            			, gepoInfoKamiHanki, zennenDataShubetu);
                //返却リストにセット
                retList.add(gepoInfoKamiHanki);
            }

            //-----------------------------
            // 第三四半期分のレコードの時
            //-----------------------------
            if ( 6 <= i && i <= 8) {
            	addSyukeiRowSiHanki(gepoInfo, gepoInfoDai3Sihanki, zennenDataShubetu);

                if(i==8){
                    //計算項目をセット
                    gepoInfoDai3Sihanki = calculate(gepoInfoDai3Sihanki, zennenDataShubetu);

                    //表示内容をセット
                    gepoInfoDai3Sihanki = setStyle(gepoInfoDai3Sihanki);

                    //返却リストにセット
                    retList.add(gepoInfoDai3Sihanki);
                }
            }

            //-----------------------------
            // 第四四半期分のレコードの時
            //-----------------------------
            if ( 9 <= i && i <= 11) {
            	addSyukeiRowSiHanki(gepoInfo, gepoInfoDai4Sihanki, zennenDataShubetu);

                if(i==11){
                    //計算項目をセット
                    gepoInfoDai4Sihanki = calculate(gepoInfoDai4Sihanki, zennenDataShubetu);

                    //表示内容をセット
                    gepoInfoDai4Sihanki = setStyle(gepoInfoDai4Sihanki);

                    //返却リストにセット
                    retList.add(gepoInfoDai4Sihanki);
                }
            }

            //-----------------------------
            // 下半期分のレコードの時
            //-----------------------------
            if ( i == 11) {
            	addSyukeiRowHanki(gepoInfoDai3Sihanki, gepoInfoDai4Sihanki
            			, gepoInfoSimoHanki, zennenDataShubetu);
                //返却リストにセット
                retList.add(gepoInfoSimoHanki);
            }
        }

        //-------------------------------
        // 合計欄(12ヶ月)用のエンティティを作成
        //-------------------------------
        GepoInfo sumGepoInfo = new GepoInfo();
        sumGepoInfo.setRowType(TakuhaiSuiiConstants.ROW_TYPE_SUM);
        sumGepoInfo.setUriage(sumUriage);                   //売上
        sumGepoInfo.setUriageZen(sumUriageZen);             //前年売上
        sumGepoInfo.setUriageTaku(sumUriageTaku);           //宅配売上
        sumGepoInfo.setUriageTakuZen(sumUriageTakuZen);     //前年宅配売上
        sumGepoInfo.setKyakusu(sumKyakusu);                 //客数
        sumGepoInfo.setKyakusuZen(sumKyakusuZen);           //前年客数
        sumGepoInfo.setKyakusuTaku(sumKyakusuTaku);         //宅配件数
        sumGepoInfo.setKyakusuTakuZen(sumKyakusuTakuZen);   //前年宅配件数
        sumGepoInfo.setTakuUriageAvg(sumTakuUriageAvg);     //宅配売上平均(合計欄のみ)
        sumGepoInfo.setTakuKensuAvg(sumTakuKyakusuAvg);     //宅配件数平均(合計欄のみ)
        //前年データ種別が『前年同月営業日補正』の場合
        if(ZennenDataShubetu.CODE_HOSEI.equals(zennenDataShubetu)) {
            sumGepoInfo.setUriageHosei(sumUriageHosei);                   //売上
            sumGepoInfo.setUriageZenHosei(sumUriageZenHosei);             //前年売上
            sumGepoInfo.setUriageTakuHosei(sumUriageTakuHosei);           //宅配売上
            sumGepoInfo.setUriageTakuZenHosei(sumUriageTakuZenHosei);     //前年宅配売上
            sumGepoInfo.setKyakusuHosei(sumKyakusuHosei);                 //客数
            sumGepoInfo.setKyakusuZenHosei(sumKyakusuZenHosei);           //前年客数
            sumGepoInfo.setKyakusuTakuHosei(sumKyakusuTakuHosei);         //宅配件数
            sumGepoInfo.setKyakusuTakuZenHosei(sumKyakusuTakuZenHosei);   //前年宅配件数
        }

        //計算項目をセット
        sumGepoInfo = calculate(sumGepoInfo, zennenDataShubetu);

        //表示内容をセット
        sumGepoInfo = setStyle(sumGepoInfo);

        //リストに追加
        retList.add(sumGepoInfo);

        return retList;
    }
    /**
     * 半期用集計処理
     * 
     * @return
     */
    private void addSyukeiRowSiHanki(GepoInfo gepoInfo, GepoInfo eSihanki, String zennenDataShubetu) {
        //計算用
        BigDecimal tmp1         = new BigDecimal(0);

        tmp1 = eSihanki.getUriage();             //売上
        eSihanki.setUriage(tmp1.add(gepoInfo.getUriage()));
        tmp1 = eSihanki.getUriageZen();          //前年売上
        eSihanki.setUriageZen(tmp1.add(gepoInfo.getUriageZen()));
        tmp1 = eSihanki.getUriageTaku();         //宅配売上
        eSihanki.setUriageTaku(tmp1.add(gepoInfo.getUriageTaku()));
        tmp1 = eSihanki.getUriageTakuZen();      //宅配売上前年
        eSihanki.setUriageTakuZen(tmp1.add(gepoInfo.getUriageTakuZen()));
        tmp1 = eSihanki.getKyakusu();             //客数
        eSihanki.setKyakusu(tmp1.add(gepoInfo.getKyakusu()));
        tmp1 = eSihanki.getKyakusuZen();          //前年客数
        eSihanki.setKyakusuZen(tmp1.add(gepoInfo.getKyakusuZen()));
        tmp1 = eSihanki.getKyakusuTaku();         //宅配件数
        eSihanki.setKyakusuTaku(tmp1.add(gepoInfo.getKyakusuTaku()));
        tmp1 = eSihanki.getKyakusuTakuZen();      //宅配件数前年
        eSihanki.setKyakusuTakuZen(tmp1.add(gepoInfo.getKyakusuTakuZen()));
        //前年データ種別が『前年同月営業日補正』の場合
        if(ZennenDataShubetu.CODE_HOSEI.equals(zennenDataShubetu)) {
        	//売上(補正)
            eSihanki.setUriageHosei(eSihanki.getUriageHosei().add(gepoInfo.getUriageHosei()));
            //前年売上(補正)
            eSihanki.setUriageZenHosei(eSihanki.getUriageZenHosei().add(gepoInfo.getUriageZenHosei()));
            //宅配売上(補正)
            eSihanki.setUriageTakuHosei(eSihanki.getUriageTakuHosei().add(gepoInfo.getUriageTakuHosei()));
            //宅配売上前年(補正)
            eSihanki.setUriageTakuZenHosei(eSihanki.getUriageTakuZenHosei().add(gepoInfo.getUriageTakuZenHosei()));
            //客数(補正)
            eSihanki.setKyakusuHosei(eSihanki.getKyakusuHosei().add(gepoInfo.getKyakusuHosei()));
            //前年客数(補正)
            eSihanki.setKyakusuZenHosei(eSihanki.getKyakusuZenHosei().add(gepoInfo.getKyakusuZenHosei()));
            //宅配件数(補正)
            eSihanki.setKyakusuTakuHosei(eSihanki.getKyakusuTakuHosei().add(gepoInfo.getKyakusuTakuHosei()));
            //宅配件数前年(補正)
            eSihanki.setKyakusuTakuZenHosei(eSihanki.getKyakusuTakuZenHosei().add(gepoInfo.getKyakusuTakuZenHosei()));
        }

        //四半期用レコード、独自の計算
        tmp1 = eSihanki.getTakuUriageAvg();      //宅配売上平均・四半期用
        eSihanki.setTakuUriageAvg(tmp1.add(gepoInfo.getTakuUriageAvg()));
        tmp1 = eSihanki.getTakuKensuAvg();      //宅配件数平均・四半期用
        eSihanki.setTakuKensuAvg(tmp1.add(gepoInfo.getTakuKensuAvg()));
    }
    /**
     * 半期用集計処理
     * 
     * @return
     */
    private void addSyukeiRowHanki(GepoInfo eSihanki1, GepoInfo eSihanki2, GepoInfo eHanKi, String zennenDataShubetu) {
        //計算用
        BigDecimal tmp1         = new BigDecimal(0);
        BigDecimal tmp2         = new BigDecimal(0);
        tmp1  = eSihanki1.getUriage();             //売上
        tmp2 = eSihanki2.getUriage();             //売上
        eHanKi.setUriage(tmp1.add(tmp2));
        tmp1  = eSihanki1.getUriageZen();          //前年売上
        tmp2 = eSihanki2.getUriageZen();          //前年売上
        eHanKi.setUriageZen(tmp1.add(tmp2));
        tmp1  = eSihanki1.getUriageTaku();         //宅配売上
        tmp2 = eSihanki2.getUriageTaku();         //宅配売上
        eHanKi.setUriageTaku(tmp1.add(tmp2));
        tmp1  = eSihanki1.getUriageTakuZen();      //宅配売上前年
        tmp2 = eSihanki2.getUriageTakuZen();      //宅配売上前年
        eHanKi.setUriageTakuZen(tmp1.add(tmp2));
        tmp1  = eSihanki1.getKyakusu();             //客数
        tmp2 = eSihanki2.getKyakusu();             //客数
        eHanKi.setKyakusu(tmp1.add(tmp2));
        tmp1  = eSihanki1.getKyakusuZen();          //前年客数
        tmp2 = eSihanki2.getKyakusuZen();             //前年客数
        eHanKi.setKyakusuZen(tmp1.add(tmp2));
        tmp1  = eSihanki1.getKyakusuTaku();         //宅配件数
        tmp2 = eSihanki2.getKyakusuTaku();         //宅配件数
        eHanKi.setKyakusuTaku(tmp1.add(tmp2));
        tmp1  = eSihanki1.getKyakusuTakuZen();      //宅配件数前年
        tmp2 = eSihanki2.getKyakusuTakuZen();      //宅配件数前年
        eHanKi.setKyakusuTakuZen(tmp1.add(tmp2));
        tmp1  = eSihanki1.getTakuUriageAvg();       //宅配売上平均
        tmp2 = eSihanki2.getTakuUriageAvg();       //宅配売上平均
        eHanKi.setTakuUriageAvg(tmp1.add(tmp2));
        tmp1  = eSihanki1.getTakuKensuAvg();        //宅配件数平均
        tmp2 = eSihanki2.getTakuKensuAvg();        //宅配件数平均
        eHanKi.setTakuKensuAvg(tmp1.add(tmp2));
        //前年データ種別が『前年同月営業日補正』の場合
        if(ZennenDataShubetu.CODE_HOSEI.equals(zennenDataShubetu)) {
        	//売上(補正)
        	eHanKi.setUriageHosei(eSihanki1.getUriageHosei().add(eSihanki2.getUriageHosei()));
            //前年売上(補正)
        	eHanKi.setUriageZenHosei(eSihanki1.getUriageZenHosei().add(eSihanki2.getUriageZenHosei()));
            //宅配売上(補正)
        	eHanKi.setUriageTakuHosei(eSihanki1.getUriageTakuHosei().add(eSihanki2.getUriageTakuHosei()));
            //宅配売上前年(補正)
        	eHanKi.setUriageTakuZenHosei(eSihanki1.getUriageTakuZenHosei().add(eSihanki2.getUriageTakuZenHosei()));
            //客数(補正)
        	eHanKi.setKyakusuHosei(eSihanki1.getKyakusuHosei().add(eSihanki2.getKyakusuHosei()));
            //前年客数(補正)
        	eHanKi.setKyakusuZenHosei(eSihanki1.getKyakusuZenHosei().add(eSihanki2.getKyakusuZenHosei()));
            //宅配件数(補正)
        	eHanKi.setKyakusuTakuHosei(eSihanki1.getKyakusuTakuHosei().add(eSihanki2.getKyakusuTakuHosei()));
            //宅配件数前年(補正)
        	eHanKi.setKyakusuTakuZenHosei(eSihanki1.getKyakusuTakuZenHosei().add(eSihanki2.getKyakusuTakuZenHosei()));
        }

        //計算項目をセット
        calculate(eHanKi, zennenDataShubetu);

        //表示内容をセット
        setStyle(eHanKi);
    }
    /**
     * 表示内容をセット
     * @param  GepoInfo 月次情報リスト
     * @return GepoInfo 
     */
    private GepoInfo setStyle(GepoInfo gepoInfo) {

        //作業用
        String tdClass    = "";
        String tdClassRed = "";
        BigDecimal tmp = new BigDecimal(0);
        
        //行タイプ判別
        int type = gepoInfo.getRowType();

        //タイプ：第一四半期の時
        if (type == TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_1) {
            gepoInfo.setEigyoDt("第一四半期"); 
            tdClass    = TakuhaiSuiiConstants.TD_SIHANKI_NUM;
            tdClassRed = TakuhaiSuiiConstants.TD_SIHANKI_NUM_RED;
        }
        //タイプ：第二四半期の時
        else if (type == TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_2) {
            gepoInfo.setEigyoDt("第二四半期"); 
            tdClass    = TakuhaiSuiiConstants.TD_SIHANKI_NUM;
            tdClassRed = TakuhaiSuiiConstants.TD_SIHANKI_NUM_RED;
        }
        //タイプ：第三四半期の時
        else if (type == TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_3) {
            gepoInfo.setEigyoDt("第三四半期"); 
            tdClass    = TakuhaiSuiiConstants.TD_SIHANKI_NUM;
            tdClassRed = TakuhaiSuiiConstants.TD_SIHANKI_NUM_RED;
        }
        //タイプ：第四四半期の時
        else if (type == TakuhaiSuiiConstants.ROW_TYPE_SIHANKI_4) {
            gepoInfo.setEigyoDt("第四四半期"); 
            tdClass    = TakuhaiSuiiConstants.TD_SIHANKI_NUM;
            tdClassRed = TakuhaiSuiiConstants.TD_SIHANKI_NUM_RED;
        }
        //タイプ：上半期の時
        else if (type == TakuhaiSuiiConstants.ROW_TYPE_HANKI_1) {
            gepoInfo.setEigyoDt("上期"); 
            tdClass    = TakuhaiSuiiConstants.TD_HANKI_NUM;
            tdClassRed = TakuhaiSuiiConstants.TD_HANKI_NUM_RED;
        }
        //タイプ：下半期の時
        else if (type == TakuhaiSuiiConstants.ROW_TYPE_HANKI_2){
            gepoInfo.setEigyoDt("下期"); 
            tdClass    = TakuhaiSuiiConstants.TD_HANKI_NUM;
            tdClassRed = TakuhaiSuiiConstants.TD_HANKI_NUM_RED;
        }
        //タイプ：合計の時
        else if (type == TakuhaiSuiiConstants.ROW_TYPE_SUM){
            gepoInfo.setEigyoDt("通期"); 
            tdClass    = TakuhaiSuiiConstants.TD_SUM_NUM;
            tdClassRed = TakuhaiSuiiConstants.TD_SUM_NUM_RED;
        }
        //タイプ：その他(通常レコード)の時
        else {
            tdClass    = TakuhaiSuiiConstants.TD_HIRITU;
            tdClassRed = TakuhaiSuiiConstants.TD_HIRITU_RED;
        }
        
        
        //表示スタイルの指定
        //前年比売上のクラス
        tmp = gepoInfo.getZenhiUriage();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            gepoInfo.setClassZenHiUriage(tdClass);
        } else {
            gepoInfo.setClassZenHiUriage(tdClassRed);
        }
        //前年比宅配売上のクラス
        tmp = gepoInfo.getZenhiUriageTaku();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            gepoInfo.setClassZenHiUriageTaku(tdClass);
        } else {
            gepoInfo.setClassZenHiUriageTaku(tdClassRed);
        }
        //前年比客数のクラス
        tmp = gepoInfo.getZenhiKyakusu();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            gepoInfo.setClassZenHiKyaku(tdClass);
        } else {
            gepoInfo.setClassZenHiKyaku(tdClassRed);
        }
        //前年比宅配件数のクラス
        tmp = gepoInfo.getZenhiKyakusuTaku();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            gepoInfo.setClassZenHiKyakuTaku(tdClass);
        } else {
            gepoInfo.setClassZenHiKyakuTaku(tdClassRed);
        }
        //前年比客単価のクラス
        tmp = gepoInfo.getZenhiKyakuTanka();
        if ( tmp.compareTo(new BigDecimal(100)) >= 0 ){
            gepoInfo.setClassZenHiKyakuTanka(tdClass);
        } else {
            gepoInfo.setClassZenHiKyakuTanka(tdClassRed);
        }

        //返却
        return gepoInfo;
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
            String hyojiTaisho = (String)argsMap.get(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A);
            if(TaishoJoken.CODE_MISE.equals(taishoJoken)){
                //必須チェック
                if(CommonUtil.isNull(hyojiTaisho)){
                    throw new NotNullException("店コード", "honbuSuiiMiseCd", 0);
                }          
                // 半角数字
                CodeVerifier codeVerifier = new CodeVerifier();
                if (!codeVerifier.validate(hyojiTaisho)) {
                    throw new InvalidInputException("店コード", "honbuSuiiMiseCd", 0);
                }                
                //OKの時 ---> 00000形にフォーマットして、DTOに再セット
                if(hyojiTaisho.length() < 5){                   
                    CodeFormatter cdf = new CodeFormatter(5, "00000");
                    cdf.setFormatPattern("00000");
                    hyojiTaisho = cdf.format(hyojiTaisho, true);
                    argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A, hyojiTaisho);
                }
            }
            else if (!taishoJoken.equals(TaishoJoken.CODE_ALL)) {
            	if (CommonUtil.isNull(hyojiTaisho)) {               	
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
     * 月別宅配売上月報取得DAOを取得します<br>
     * @return 月別宅配売上月報取得DAO
     */
    public GepoInfoDao getGepoInfoDao() {
        return gepoInfoDao;
    }

    /**
     * 月別宅配売上月報取得DAOを設定します<br>
     * @param 月別宅配売上月報取得DAO
     */
    public void setGepoInfoDao(GepoInfoDao logic) {
        this.gepoInfoDao = logic;
    }
}
