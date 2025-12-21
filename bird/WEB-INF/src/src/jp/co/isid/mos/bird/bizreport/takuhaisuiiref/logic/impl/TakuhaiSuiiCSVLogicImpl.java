/*
 * 作成日: 2006/10/06
 */
package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiCommon;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.common.TakuhaiSuiiConstants;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.dto.TakuhaiSuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.GepoInfo;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity.NipoInfo;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TakuhaiSuiiGepoInfoLogic;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TakuhaiSuiiNipoInfoLogic;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TotalMiseCntGepoLogic;
import jp.co.isid.mos.bird.bizreport.takuhaisuiiref.logic.TotalMiseCntNipoLogic;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;


/**
 * 宅配売上推移表CSVダウンロードロジック
 * 
 * @author xwatanabe
 * @modifier xkinu 2013/05/13 MOS CARD推移表対応
 */
public class TakuhaiSuiiCSVLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR004L05";
    /** ファイル名 */
    public static String FILE_NAME = "";
    private TakuhaiSuiiNipoInfoLogic takuhaiSuiiNipoInfoLogic;   //宅配売上推移日次情報取得ロジック
    private TotalMiseCntNipoLogic    totalMiseCntNipoLogic;      //日次店舗数取得ロジック
    private TakuhaiSuiiGepoInfoLogic takuhaiSuiiGepoInfoLogic;   //宅配売上推移月次情報取得ロジック
    private TotalMiseCntGepoLogic    totalMiseCntGepoLogic;      //月次店舗数取得ロジック

    
    /**
     * CSV出力用のデータ取得処理
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        //出力用リスト
        List outputList = new ArrayList();

        //空行出力用リスト
        List karaList = new ArrayList();
        karaList.add("");

        //作業用リスト
        List tmpList;

        //DTOより情報取得
        SuiiRefParameterDto parameterDto   = (SuiiRefParameterDto)csvOutputDto;
        //2.DTO【宅配売上推移表用・検索結果】から条件パラメーター値を取得する。
        String companyCd      = parameterDto.getCompanyCd();
        String tenpoShubetu   = parameterDto.getTenpoShubetu();
        String taishoKikan    = parameterDto.getTaishoKikan();
        String kikanSitei     = parameterDto.getKikanSitei();
        String taishoJoken    = parameterDto.getTaishoJoken();
        String hyojiTaisho    = parameterDto.getHyojiTaisho();
        String zenDataShubetu = parameterDto.getZennenDataShubetu();
        String blockCd        = parameterDto.getBlockCd();
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        //-----------------------------------------
        // <1>宅配売上推移月次情報取得ロジックの実行
        //-----------------------------------------
        //引数用Map作成
        Map argsMap = new HashMap();
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_COMPANY_CD,      companyCd);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_TENPO_SHUBETU,   tenpoShubetu);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_KIKAN,    taishoKikan);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_KIKAN_SITEI,     kikanSitei);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_TAISHO_JOKEN,    taishoJoken);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_HYOJITAISHO_A,   hyojiTaisho);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_ZENDATA_SHUBETU, zenDataShubetu);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_BLOCK_CD,        blockCd);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_USER_TYPE,       userTypeCd);
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_USER_ID,         getBirdUserInfo().getUserID());
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_LIMIT_FLG,       new Boolean(getBirdUserInfo().isLimit()));
        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_APPDATE,         getBirdDateInfo().getAppDate());
        //3.オーナーユーザーの場合
        if(UserType.isOner(userTypeCd)) {
 	        argsMap.put(TakuhaiSuiiConstants.PARAM_KEY_ONER_CD, hyojiTaisho);
        }
        //実行
        Map  gepoMap    = takuhaiSuiiGepoInfoLogic.execute(argsMap);
        //1-7.推移表共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
    	parameterDto.setResearchFlg(true);
    	
        List gepoInfoGamenList = (List)gepoMap.get(TakuhaiSuiiConstants.MAPKEY_GEPO_GAMEN_LIST);
        if(gepoInfoGamenList == null || gepoInfoGamenList.size()==0){
        	throw new NoResultException("");
        }
        TakuhaiSuiiRefResultDto  takuhaiSuiiRefResultDto = new TakuhaiSuiiRefResultDto();
        //宅配対象店舗数(年月(YYYYMM)をキーに店舗すうを保持)
        Map  tenpoCntMap       = (Map)gepoMap.get(TakuhaiSuiiConstants.MAPKEY_TENPO_CNT_MAP);
        //結果(月次リスト)をDTOにセット
        takuhaiSuiiRefResultDto.setGepoGamenList(gepoInfoGamenList);

        //-----------------------------------------
        // <2>月次店舗数取得ロジックの実行
        //-----------------------------------------
        //実行
        int tenpoCntGepo = totalMiseCntGepoLogic.execute(argsMap);
        
        //結果(対象店舗数)をDTOにセット
        takuhaiSuiiRefResultDto.setTotalTenpoCountGepo(tenpoCntGepo);

        //-----------------------------------------
        // <3>宅配売上推移日次情報取得ロジックの実行
        //-----------------------------------------
        //実行
        Map  nipoMap    = takuhaiSuiiNipoInfoLogic.execute(argsMap, tenpoCntMap);
        List nipoInfoGamenList = (List)nipoMap.get(TakuhaiSuiiConstants.MAPKEY_NIPO_GAMEN_LIST);
        if(nipoInfoGamenList != null && nipoInfoGamenList.size() > 0){
            //結果(日次リスト)をDTOにセット
            takuhaiSuiiRefResultDto.setNipoGamenList(nipoInfoGamenList);
            //-----------------------------------------
            // <4>日次店舗数取得ロジックの実行
            //-----------------------------------------
            //実行
            int tenpoCntNipo = totalMiseCntNipoLogic.execute(argsMap);
            //結果(対象店舗数)をDTOにセット
            takuhaiSuiiRefResultDto.setTotalTenpoCountNipo(tenpoCntNipo);
        }	

        String target             = parameterDto.getTabKbn();            //選択タブ
        String companyName        = parameterDto.getCompanyName();          //会社名
        String tenpoShubetuName   = parameterDto.getTenpoShubetuName();     //店舗種別名称
        String ZenDataShubetuName = parameterDto.getZennenDataShubetuName();   //前年データ種別名称
        String taishoJokenName    = parameterDto.getTaishoJokenName();      //対象条件
        String hyojiTaishoNipoStr = parameterDto.getHyojiTaishoLabel();   //表示対象(日報)
        int   tenpoCntNipo       = takuhaiSuiiRefResultDto.getTotalTenpoCountNipo();  //対象店舗数(日報)
        boolean isNinituki       = parameterDto.getTaishoKikan().equals(TaishoKikan.CODE_MONTH);             //任意月指定 or 年度
        String appDate            = getBirdDateInfo().getAppDate();             //アプリ日付

         //日報 or 月報
         boolean nipoFlg = true;
         if (TakuhaiSuiiConstants.TAB_AREA_0.equals(target) ||
                 TakuhaiSuiiConstants.TAB_AREA_1.equals(target) ) {
             nipoFlg = true;
         }
         else if(TakuhaiSuiiConstants.TAB_AREA_2.equals(target) ||
                 TakuhaiSuiiConstants.TAB_AREA_3.equals(target) ){
             nipoFlg = false;
         }

         //前年同月営業日補正の時
         boolean hoseiFlg        = parameterDto.isSelectHosei();

         //ファイル名
         if(nipoFlg){
             //日報の時
             //任意月指定の時
             if(isNinituki){
                 FILE_NAME = "TAKUSUIIDAY" + kikanSitei + ".csv";
             }
             //年度指定の時
             else {
                 String tyokinYm = TakuhaiSuiiCommon.getTyokkinYm(kikanSitei, appDate);
                 FILE_NAME = "TAKUSUIIDAY" + tyokinYm + ".csv";
             }
         } else {
             FILE_NAME = "GEPO.csv";
             //任意月指定の時
             if(isNinituki){
                 FILE_NAME = "TAKUSUIIMONTHtab_gepo.csv";
             }
             //年度指定の時
             else {
                 FILE_NAME = "TAKUSUIIMONTH" + kikanSitei + ".csv";
             }
         }

         //=============
         // 日報の時
         //=============
         if(nipoFlg){
             
             List nipoList = takuhaiSuiiRefResultDto.getNipoGamenList();

             //--------------
             // ヘッダ部
             //--------------
             //会社
             tmpList = new ArrayList();
             tmpList.add("会社");
             tmpList.add(companyName);
             outputList.add(tmpList);
             
             //店舗種別
             tmpList = new ArrayList();
             tmpList.add("店舗種別");
             tmpList.add(tenpoShubetuName);
             outputList.add(tmpList);
             
             //前年データ種別
             tmpList = new ArrayList();
             tmpList.add("前年データ種別");
             tmpList.add(ZenDataShubetuName);
             outputList.add(tmpList);
             
             //対象条件
             tmpList = new ArrayList();
             tmpList.add("対象条件");
             tmpList.add(taishoJokenName);
             outputList.add(tmpList);

             //表示対象
             tmpList = new ArrayList();
             tmpList.add("表示対象");
             if(UserType.isTenpo(getBirdUserInfo().getMstUser().getUserTypeCd())) {
            	 tmpList.add(parameterDto.getHyojiTaishoName());
             }
             else {
            	 tmpList.add(hyojiTaishoNipoStr);
             }
             outputList.add(tmpList);

             //期間指定
             tmpList = new ArrayList();
             tmpList.add("期間指定");
             if(isNinituki) {
                 tmpList.add(formatYMKanji(kikanSitei));
             } else {
                 tmpList.add(kikanSitei + "年度");
             }
             outputList.add(tmpList);
             
             //宅配対象店舗数
             tmpList = new ArrayList();
             tmpList.add("宅配対象店舗数");
             tmpList.add(addComma(tenpoCntNipo));
             outputList.add(tmpList);
             
             //空行
             outputList.add(karaList);
                
             //---------------------
             // データ部・項目名
             //---------------------

             tmpList = new ArrayList();

             //営業日
             tmpList.add("営業日");

             //宅配実績店舗数
             tmpList.add("宅配実績店舗数");
             
             //売上
             if(!hoseiFlg) {
                 tmpList.add("売上");
                 tmpList.add("前年実績");
                 tmpList.add("前年比(売上)");
             }else{
                 tmpList.add("売上");
                 tmpList.add("前年比対象売上");
                 tmpList.add("前年比対象前年実績");
                 tmpList.add("前年比(売上)");
             }
             
             //宅配売上
             if(!hoseiFlg) {
                 tmpList.add("宅配売上");
                 tmpList.add("前年宅配売上");
                 tmpList.add("前年比(宅配売上)");
             }else{
                 tmpList.add("宅配売上");
                 tmpList.add("前年比対象宅配売上");
                 tmpList.add("前年比対象前年宅配売上");
                 tmpList.add("前年比(宅配売上)");
             }
             
             tmpList.add("構成比(売上)");
             tmpList.add("宅配売上平均");
             
             //客数
             if(!hoseiFlg) {
                 tmpList.add("客数");
                 tmpList.add("前年客数");
                 tmpList.add("前年比(客数)");
             }else{
                 tmpList.add("客数");
                 tmpList.add("前年比対象客数");
                 tmpList.add("前年比対象前年客数");
                 tmpList.add("前年比(客数)");
             }
             
             //宅配件数
             if(!hoseiFlg) {
                 tmpList.add("宅配件数");
                 tmpList.add("前年宅配件数");
                 tmpList.add("前年比(宅配件数)");
             }else{
                 tmpList.add("宅配件数");
                 tmpList.add("前年比対象宅配件数");
                 tmpList.add("前年比対象前年宅配件数");
                 tmpList.add("前年比(宅配件数)");
             }
             
             tmpList.add("構成比(客数)");
             tmpList.add("宅配件数平均");
             
             //客単価
             if(!hoseiFlg) {
                 tmpList.add("客単価");
                 tmpList.add("前年客単価");
                 tmpList.add("前年比(客単価)");
             }else{
                 tmpList.add("客単価");
                 tmpList.add("前年比対象客単価");
                 tmpList.add("前年比対象前年客単価");
                 tmpList.add("前年比(客単価)");
             }
             outputList.add(tmpList);

             //---------------------
             // データ部・データ
             //---------------------
             for(int i=0 ; i < nipoList.size(); i++) {
                 
                 NipoInfo nipoInfo = (NipoInfo)nipoList.get(i);
                 tmpList = new ArrayList();

                 //営業日
                 tmpList.add(formatDD(nipoInfo.getEigyoDt()));
                 
                 //宅配実績店舗数
                 if(nipoInfo.getRowType() == TakuhaiSuiiConstants.ROW_TYPE_NOMAL){
                     tmpList.add(String.valueOf(nipoInfo.getHanTenpoCnt()));
                 } else {
                     tmpList.add("");
                 }

                 //売上
                 if(!hoseiFlg) {
                     tmpList.add(addComma(nipoInfo.getUriage()));             //売上
                     tmpList.add(addComma(nipoInfo.getUriageZen()));          //前年売上
                     tmpList.add(nipoInfo.getZenhiUriage());                  //前年比売上
                 }else{
                     tmpList.add(addComma(nipoInfo.getUriage()));             //売上
                     tmpList.add(addComma(nipoInfo.getUriageHosei()));        //前年比較対象売上(補正)
                     tmpList.add(addComma(nipoInfo.getUriageZenHosei()));     //前年比較対象前年実績(補正)
                     tmpList.add(nipoInfo.getZenhiUriage());                  //前年比売上
                 }
                 
                 //宅配売上
                 if(!hoseiFlg) {
                     tmpList.add(addComma(nipoInfo.getUriageTaku()));         //宅配売上
                     tmpList.add(addComma(nipoInfo.getUriageTakuZen()));      //前年宅配売上
                     tmpList.add(nipoInfo.getZenhiUriageTaku());              //前年比(宅配売上)
                 }else{
                     tmpList.add(addComma(nipoInfo.getUriageTaku()));         //宅配売上
                     tmpList.add(addComma(nipoInfo.getUriageTakuHosei()));    //前年比較対象宅配売上
                     tmpList.add(addComma(nipoInfo.getUriageTakuZenHosei())); //前年比較対象前年宅配売上
                     tmpList.add(nipoInfo.getZenhiUriageTaku());              //前年比宅配売上
                 }

                 tmpList.add(nipoInfo.getKouseihiUriage());                   //構成比(売上)
                 tmpList.add(addComma(nipoInfo.getTakuUriageAvg()));          //宅配売上平均

                 //客数
                 if(!hoseiFlg) {
                     tmpList.add(addComma(nipoInfo.getKyakusu()));            //客数
                     tmpList.add(addComma(nipoInfo.getKyakusuZen()));         //前年客数
                     tmpList.add(nipoInfo.getZenhiKyakusu());                 //前年比客数
                 }else{
                     tmpList.add(addComma(nipoInfo.getKyakusu()));            //客数
                     tmpList.add(addComma(nipoInfo.getKyakusuHosei()));       //前年比較対象客数
                     tmpList.add(addComma(nipoInfo.getKyakusuZenHosei()));    //前年比較対象前年客数
                     tmpList.add(nipoInfo.getZenhiKyakusu());                 //前年比客数
                 }

                 //宅配件数
                 if(!hoseiFlg) {
                     tmpList.add(addComma(nipoInfo.getKyakusuTaku()));        //宅配件数
                     tmpList.add(addComma(nipoInfo.getKyakusuTakuZen()));     //前年宅配件数
                     tmpList.add(nipoInfo.getZenhiKyakusuTaku());             //前年比(宅配件数)
                 }else{
                     tmpList.add(addComma(nipoInfo.getKyakusuTaku()));        //宅配件数
                     tmpList.add(addComma(nipoInfo.getKyakusuTakuHosei()));   //前年比較対象宅配件数
                     tmpList.add(addComma(nipoInfo.getKyakusuTakuZenHosei()));//前年比較対象前年宅配件数
                     tmpList.add(nipoInfo.getZenhiKyakusuTaku());             //前年比(宅配件数)
                 }
                 
                 tmpList.add(nipoInfo.getKouseihiKyakusu());                  //構成比(客数)
                 tmpList.add(addComma(nipoInfo.getTakuKensuAvg()));           //宅配件数平均
                 
                 //客単価
                 if(!hoseiFlg) {
                     tmpList.add(addComma(nipoInfo.getKyakuTanka()));         //客単価
                     tmpList.add(addComma(nipoInfo.getKyakuTankaZen()));      //前年客単価
                     tmpList.add(nipoInfo.getZenhiKyakuTanka());              //前年比(客単価)
                 }else{
                     tmpList.add(addComma(nipoInfo.getKyakuTanka()));         //客単価
                     tmpList.add(addComma(nipoInfo.getKyakuTankaHosei()));    //前年比較対象客単価
                     tmpList.add(addComma(nipoInfo.getKyakuTankaZenHosei())); //前年比較対象前年客単価
                     tmpList.add(nipoInfo.getZenhiKyakuTanka());              //前年比(客単価)
                 }
                 //行追加
                 outputList.add(tmpList);
             }
         }
         //=============
         // 月報の時
         //=============
         else {
             List gepoList = takuhaiSuiiRefResultDto.getGepoGamenList();

             //--------------
             // ヘッダ部
             //--------------
             //会社
             tmpList = new ArrayList();
             tmpList.add("会社");
             tmpList.add(companyName);
             outputList.add(tmpList);
             
             //店舗種別
             tmpList = new ArrayList();
             tmpList.add("店舗種別");
             tmpList.add(tenpoShubetuName);
             outputList.add(tmpList);
             
             //前年データ種別
             tmpList = new ArrayList();
             tmpList.add("前年データ種別");
             tmpList.add(ZenDataShubetuName);
             outputList.add(tmpList);
             
             //対象条件
             tmpList = new ArrayList();
             tmpList.add("対象条件");
             tmpList.add(taishoJokenName);
             outputList.add(tmpList);

             //表示対象
             tmpList = new ArrayList();
             tmpList.add("表示対象");
             if(UserType.isTenpo(getBirdUserInfo().getMstUser().getUserTypeCd())) {
            	 tmpList.add(parameterDto.getHyojiTaishoName());
             }
             else {
            	 tmpList.add(hyojiTaishoNipoStr);
             }
             outputList.add(tmpList);

             //期間指定
             tmpList = new ArrayList();
             tmpList.add("期間指定");
             if(isNinituki) {
                 tmpList.add(formatYMKanji(kikanSitei));
             } else {
                 tmpList.add(kikanSitei + "年度");
             }
             outputList.add(tmpList);
             
             //宅配対象店舗数
             tmpList = new ArrayList();
             tmpList.add("宅配対象店舗数");
             tmpList.add(addComma(tenpoCntGepo));
             outputList.add(tmpList);
             
             //空行
             outputList.add(karaList);
                
             //------------------
             // データ部・項目名
             //------------------
             tmpList = new ArrayList();

             //年月
             tmpList.add("年月");

             //宅配対象店舗数
             tmpList.add("宅配対象店舗数");
             
             //売上
             if(!hoseiFlg) {
                 tmpList.add("売上");
                 tmpList.add("前年実績");
                 tmpList.add("前年比(売上)");
             }else{
                 tmpList.add("売上");
                 tmpList.add("前年比対象売上");
                 tmpList.add("前年比対象前年実績");
                 tmpList.add("前年比(売上)");
             }
             
             //宅配売上
             if(!hoseiFlg) {
                 tmpList.add("宅配売上");
                 tmpList.add("前年宅配売上");
                 tmpList.add("前年比(宅配売上)");
             }else{
                 tmpList.add("宅配売上");
                 tmpList.add("前年比対象宅配売上");
                 tmpList.add("前年比対象前年宅配売上");
                 tmpList.add("前年比(宅配売上)");
             }
             
             tmpList.add("構成比(売上)");
             tmpList.add("宅配売上平均");
             
             //客数
             if(!hoseiFlg) {
                 tmpList.add("客数");
                 tmpList.add("前年客数");
                 tmpList.add("前年比(客数)");
             }else{
                 tmpList.add("客数");
                 tmpList.add("前年比対象客数");
                 tmpList.add("前年比対象前年客数");
                 tmpList.add("前年比(客数)");
             }
             
             //宅配件数
             if(!hoseiFlg) {
                 tmpList.add("宅配件数");
                 tmpList.add("前年宅配件数");
                 tmpList.add("前年比(宅配件数)");
             }else{
                 tmpList.add("宅配件数");
                 tmpList.add("前年比対象宅配件数");
                 tmpList.add("前年比対象前年宅配件数");
                 tmpList.add("前年比(宅配件数)");
             }
             
             tmpList.add("構成比(客数)");
             tmpList.add("宅配件数平均");
             
             //客単価
             if(!hoseiFlg) {
                 tmpList.add("客単価");
                 tmpList.add("前年客単価");
                 tmpList.add("前年比(客単価)");
             }else{
                 tmpList.add("客単価");
                 tmpList.add("前年比対象客単価");
                 tmpList.add("前年比対象前年客単価");
                 tmpList.add("前年比(客単価)");
             }
             outputList.add(tmpList);

             //------------------
             // データ部・データ
             //------------------
             for(int i=0 ; i < gepoList.size(); i++) {
                 
                 GepoInfo gepoInfo = (GepoInfo)gepoList.get(i);
                 tmpList = new ArrayList();
                 
                 //年月
                 tmpList.add(formatYMKanji(gepoInfo.getEigyoDt()));

                 //宅配対象店舗数
                 if(gepoInfo.getRowType() == TakuhaiSuiiConstants.ROW_TYPE_NOMAL){
                     tmpList.add(String.valueOf(gepoInfo.getTakuTaishoTenpoCnt()));
                 } else {
                     tmpList.add("");
                 }

                 //売上
                 if(!hoseiFlg) {
                     tmpList.add(addComma(gepoInfo.getUriage()));             //売上
                     tmpList.add(addComma(gepoInfo.getUriageZen()));          //前年売上
                     tmpList.add(gepoInfo.getZenhiUriage());                  //前年比売上
                 }else{
                     tmpList.add(addComma(gepoInfo.getUriage()));             //売上
                     tmpList.add(addComma(gepoInfo.getUriageHosei()));        //前年比較対象売上(補正)
                     tmpList.add(addComma(gepoInfo.getUriageZenHosei()));     //前年比較対象前年実績(補正)
                     tmpList.add(gepoInfo.getZenhiUriage());                  //前年比売上
                 }
                 
                 //宅配売上
                 if(!hoseiFlg) {
                     tmpList.add(addComma(gepoInfo.getUriageTaku()));         //宅配売上
                     tmpList.add(addComma(gepoInfo.getUriageTakuZen()));      //前年宅配売上
                     tmpList.add(gepoInfo.getZenhiUriageTaku());              //前年比(宅配売上)
                 }else{
                     tmpList.add(addComma(gepoInfo.getUriageTaku()));         //宅配売上
                     tmpList.add(addComma(gepoInfo.getUriageTakuHosei()));    //前年比較対象宅配売上
                     tmpList.add(addComma(gepoInfo.getUriageTakuZenHosei())); //前年比較対象前年宅配売上
                     tmpList.add(gepoInfo.getZenhiUriageTaku());              //前年比宅配売上
                 }

                 tmpList.add(gepoInfo.getKouseihiUriage());                   //構成比(売上)
                 tmpList.add(addComma(gepoInfo.getTakuUriageAvg()));          //宅配売上平均

                 //客数
                 if(!hoseiFlg) {
                     tmpList.add(addComma(gepoInfo.getKyakusu()));            //客数
                     tmpList.add(addComma(gepoInfo.getKyakusuZen()));         //前年客数
                     tmpList.add(gepoInfo.getZenhiKyakusu());                 //前年比客数
                 }else{
                     tmpList.add(addComma(gepoInfo.getKyakusu()));            //客数
                     tmpList.add(addComma(gepoInfo.getKyakusuHosei()));       //前年比較対象客数
                     tmpList.add(addComma(gepoInfo.getKyakusuZenHosei()));    //前年比較対象前年客数
                     tmpList.add(gepoInfo.getZenhiKyakusu());                 //前年比客数
                 }

                 //宅配件数
                 if(!hoseiFlg) {
                     tmpList.add(addComma(gepoInfo.getKyakusuTaku()));        //宅配件数
                     tmpList.add(addComma(gepoInfo.getKyakusuTakuZen()));     //前年宅配件数
                     tmpList.add(gepoInfo.getZenhiKyakusuTaku());             //前年比(宅配件数)
                 }else{
                     tmpList.add(addComma(gepoInfo.getKyakusuTaku()));        //宅配件数
                     tmpList.add(addComma(gepoInfo.getKyakusuTakuHosei()));   //前年比較対象宅配件数
                     tmpList.add(addComma(gepoInfo.getKyakusuTakuZenHosei()));//前年比較対象前年宅配件数
                     tmpList.add(gepoInfo.getZenhiKyakusuTaku());             //前年比(宅配件数)
                 }
                 
                 tmpList.add(gepoInfo.getKouseihiKyakusu());                  //構成比(客数)
                 tmpList.add(addComma(gepoInfo.getTakuKensuAvg()));           //宅配件数平均
                 
                 //客単価
                 if(!hoseiFlg) {
                     tmpList.add(addComma(gepoInfo.getKyakuTanka()));         //客単価
                     tmpList.add(addComma(gepoInfo.getKyakuTankaZen()));      //前年客単価
                     tmpList.add(gepoInfo.getZenhiKyakuTanka());              //前年比(客単価)
                 }else{
                     tmpList.add(addComma(gepoInfo.getKyakuTanka()));         //客単価
                     tmpList.add(addComma(gepoInfo.getKyakuTankaHosei()));    //前年比較対象客単価
                     tmpList.add(addComma(gepoInfo.getKyakuTankaZenHosei())); //前年比較対象前年客単価
                     tmpList.add(gepoInfo.getZenhiKyakuTanka());              //前年比(客単価)
                 }
                 //行追加
                 outputList.add(tmpList);
             }
         }
            
        return outputList;
    }

    /**
     * validateメソッド
     */
    public void validate(CsvOutputDto csvOutputDto) {
        //自動生成されたメソッド・スタブ
    }

    /**
     * ファイル名取得
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        return FILE_NAME;
    }

    /**
     * 金額を表示用に編集
     * 数値にカンマを付け加える
     * @param  数値(金額)
     * @return カンマ編集済文字列
     */
    private String addComma(BigDecimal dec){
 
        //返却用
        String ret;
        NumericFormatter formatter = new NumericFormatter();
        formatter.setMustPattern(true);
        ret = formatter.format(dec.toString(),"##,###,###,##0");
        return ret;
    }

    /**
     * 金額を表示用に編集
     * 数値にカンマを付け加える
     * @param  数値(金額)
     * @return カンマ編集済文字列
     */
    private String addComma(int num){
 
        //返却用
        String ret;
        NumericFormatter formatter = new NumericFormatter();
        ret = formatter.format(String.valueOf(num),"##,###,###,##0");
        return ret;
    }

    /** 日付のフォーマット。
     * @param  日付文字列(YYYYMM)
     * @return 日付文字列(YYYY年MM月)
    */
    private String formatYMKanji(String date){

        DateFormatter commonFormatter
            = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_KANJI);
        
        date = commonFormatter.format(date, DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM);
        
        return date;
    }

    /** 日付のフォーマット。
     * @param  日付文字列(YYYYMM)
     * @return 日付文字列(DD日(曜))
    */
    private String formatDD(String date){

        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日('E')'");
        date = dateFormatter.format(date, true);
        
        return date;
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * 日付情報を取得します。
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

	/**
	 * @return クラス変数takuhaiSuiiGepoInfoLogic を戻します。
	 */
	public TakuhaiSuiiGepoInfoLogic getTakuhaiSuiiGepoInfoLogic() {
		return takuhaiSuiiGepoInfoLogic;
	}

	/**
	 * @param takuhaiSuiiGepoInfoLogic を クラス変数takuhaiSuiiGepoInfoLogicへ設定します。
	 */
	public void setTakuhaiSuiiGepoInfoLogic(
			TakuhaiSuiiGepoInfoLogic takuhaiSuiiGepoInfoLogic) {
		this.takuhaiSuiiGepoInfoLogic = takuhaiSuiiGepoInfoLogic;
	}

	/**
	 * @return クラス変数takuhaiSuiiNipoInfoLogic を戻します。
	 */
	public TakuhaiSuiiNipoInfoLogic getTakuhaiSuiiNipoInfoLogic() {
		return takuhaiSuiiNipoInfoLogic;
	}

	/**
	 * @param takuhaiSuiiNipoInfoLogic を クラス変数takuhaiSuiiNipoInfoLogicへ設定します。
	 */
	public void setTakuhaiSuiiNipoInfoLogic(
			TakuhaiSuiiNipoInfoLogic takuhaiSuiiNipoInfoLogic) {
		this.takuhaiSuiiNipoInfoLogic = takuhaiSuiiNipoInfoLogic;
	}

	/**
	 * @return クラス変数totalMiseCntGepoLogic を戻します。
	 */
	public TotalMiseCntGepoLogic getTotalMiseCntGepoLogic() {
		return totalMiseCntGepoLogic;
	}

	/**
	 * @param totalMiseCntGepoLogic を クラス変数totalMiseCntGepoLogicへ設定します。
	 */
	public void setTotalMiseCntGepoLogic(TotalMiseCntGepoLogic totalMiseCntGepoLogic) {
		this.totalMiseCntGepoLogic = totalMiseCntGepoLogic;
	}

	/**
	 * @return クラス変数totalMiseCntNipoLogic を戻します。
	 */
	public TotalMiseCntNipoLogic getTotalMiseCntNipoLogic() {
		return totalMiseCntNipoLogic;
	}

	/**
	 * @param totalMiseCntNipoLogic を クラス変数totalMiseCntNipoLogicへ設定します。
	 */
	public void setTotalMiseCntNipoLogic(TotalMiseCntNipoLogic totalMiseCntNipoLogic) {
		this.totalMiseCntNipoLogic = totalMiseCntNipoLogic;
	}
}
