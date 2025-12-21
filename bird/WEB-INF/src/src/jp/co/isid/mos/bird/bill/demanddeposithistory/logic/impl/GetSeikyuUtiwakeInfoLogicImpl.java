package jp.co.isid.mos.bird.bill.demanddeposithistory.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import jp.co.isid.mos.bird.bill.common.dto.SeikyuBunruiKigouDto;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.CodSeikyuBnruiDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.UIBillRirekiDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.UIMiseSeikyuRirekiDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.UIShoBnrSeikyuRirekiDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.UITaxRateWakeDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.CodSeikyuBnrui;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIBillRireki;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIMiseSeikyuRireki;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIShoBnrSeikyuRireki;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UITaxRateWake;
import jp.co.isid.mos.bird.bill.demanddeposithistory.logic.GetSeikyuUtiwakeInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;


/**
 * 請求内訳情報の取得ロジック
 *
 * @author xwatanabe
 */
public class GetSeikyuUtiwakeInfoLogicImpl implements GetSeikyuUtiwakeInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS009L02";

    /**　請求書履歴DAO　*/
    private UIBillRirekiDao uiBillRirekiDao;
 // add 2023/02/07 USI範  begin
    /**　税率別の内訳DAO　*/
    private UITaxRateWakeDao uITaxRateWakeDao;
 // add 2023/02/07 USI範  end
    /**　店別請求履歴情報DAO　*/
    private UIMiseSeikyuRirekiDao uiMiseSeikyuRirekiDao;
    /**　商品分類別請求履歴DAO　*/
    private UIShoBnrSeikyuRirekiDao uiShoBnrSeikyuRirekiDao;
    /**　請求書用分類DAO　*/
    private CodSeikyuBnruiDao codSeikyuBnruiDao;

    /**　請求書分類記号シート　*/
    private List seikyuBunruiList;


    /** 指定オーナーの売掛先一覧を取得する。
     * @param  会社コード
     * @param  売掛先コード
     * @param  請求書ID
     * @return 請求書履歴のリスト
    */
    public HashMap execute(String companyCd, String urikakeCd, String seikyushoId) {

        //返却用
        HashMap outputMap = new HashMap();

        //パラメータチェック
        if(companyCd == null || companyCd.getBytes().length == 0){
            throw new NotNullException("会社コード");
        }
        if(urikakeCd == null || urikakeCd.getBytes().length == 0){
            throw new NotNullException("売掛先コード");
        }
        if(seikyushoId == null || seikyushoId.getBytes().length == 0){
            throw new NotNullException("請求書ID");
        }

        //-------------------------------
        //内訳画面請求書履歴リストの取得
        //-------------------------------
        List utiwakeBillList = uiBillRirekiDao.getUtiwakeBill(companyCd, urikakeCd, seikyushoId);
        if(utiwakeBillList == null || utiwakeBillList.isEmpty()){
            //存在しない時
            throw new NotExistException("ご請求情報");
        }
        //表示用に整える
        utiwakeBillList = formatUtiwakeBillList(utiwakeBillList);

// add 2023/02/07 USI範  begin
//modify 2023/05/08 USI金 begin
        //-------------------------------
        //税率別の内訳リストの取得
        //-------------------------------
//        List tenPercentList = uITaxRateWakeDao.getTenPercent(companyCd, urikakeCd, seikyushoId);
//        List gennZeiList = uITaxRateWakeDao.getGennZei(companyCd, urikakeCd, seikyushoId);
//        List keikaSotiList = uITaxRateWakeDao.getKeikaSoti(companyCd, urikakeCd, seikyushoId);
//        List hiKazeiList = uITaxRateWakeDao.getHiKazei(companyCd, urikakeCd, seikyushoId);
        UITaxRateWake taxRateNewRecord = new UITaxRateWake();
        List tenPercentList = uITaxRateWakeDao.getTenPercent(companyCd, urikakeCd, seikyushoId);
        if(tenPercentList == null || tenPercentList.isEmpty()) {
        	taxRateNewRecord.settaxkbn1_kazei(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn1_zeiko(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn1_zeiko_tax(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn1_uri(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn1_inv(BigDecimal.ZERO);
        	tenPercentList.add(taxRateNewRecord);
        }
        List gennZeiList = uITaxRateWakeDao.getGennZei(companyCd, urikakeCd, seikyushoId);
        if(gennZeiList == null || gennZeiList.isEmpty()) {
        	taxRateNewRecord.settaxkbn2_kazei(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn2_zeiko(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn2_zeiko_tax(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn2_uri(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn2_inv(BigDecimal.ZERO);
        	gennZeiList.add(taxRateNewRecord);
        }
        List keikaSotiList = uITaxRateWakeDao.getKeikaSoti(companyCd, urikakeCd, seikyushoId);
        if(keikaSotiList == null || keikaSotiList.isEmpty()) {
        	taxRateNewRecord.settaxkbn3_kazei(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn3_zeiko(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn3_zeiko_tax(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn3_uri(BigDecimal.ZERO);
        	taxRateNewRecord.settaxkbn3_inv(BigDecimal.ZERO);
        	keikaSotiList.add(taxRateNewRecord);
        }
        List hiKazeiList = uITaxRateWakeDao.getHiKazei(companyCd, urikakeCd, seikyushoId);
        if(hiKazeiList == null || hiKazeiList.isEmpty()) {
        	taxRateNewRecord.settaxkbn4_hizei(BigDecimal.ZERO);
        	hiKazeiList.add(taxRateNewRecord);
        }
//modify 2023/05/08 USI金 end
// add 2023/02/07 USI範  end
 //add 2023/04/14 USI金 begin
        //税率別の内訳リストのお買上額内訳区分をセット
        UIBillRireki record = (UIBillRireki)utiwakeBillList.get(0);
        if(!tenPercentList.isEmpty()) {
        	UITaxRateWake TenRecord = (UITaxRateWake)tenPercentList.get(0);
            if(record.getUtiwakeKbn().equals("1")) {
                TenRecord.setUtiwakeKbn("1");
             }
        }
//add 2023/04/14 USI金 end

        //-------------------------------
        //店別請求情報リストの取得
        //-------------------------------
        List miseSeikyuList = uiMiseSeikyuRirekiDao.getMiseSeikyu(companyCd, urikakeCd, seikyushoId);
        if (miseSeikyuList != null) {
            //税込み・非課税額の算定とセット
            miseSeikyuList = addZeikomiHikazei(miseSeikyuList);
        }

        //-------------------------------
        //請求書用分類リストの取得
        //-------------------------------
        List seikyuBnruiList = codSeikyuBnruiDao.getSeikyuBnrui();

        //-------------------------------
        //オーナー商品分類別請求履歴リストの取得
        //-------------------------------
        List onerSeikyuBnruiList = uiShoBnrSeikyuRirekiDao.getOnerSeikyuBnrui(companyCd, urikakeCd, seikyushoId);
        if(onerSeikyuBnruiList != null && !onerSeikyuBnruiList.isEmpty()){

            //請求分類のレコード(空)挿入
            onerSeikyuBnruiList = addBunruiRecord(onerSeikyuBnruiList, seikyuBnruiList);

            //記号のセット
            onerSeikyuBnruiList = kigouSet(onerSeikyuBnruiList);

            //ソート順に入れ替える
            Collections.sort(onerSeikyuBnruiList, new SortComparator());
        }

        //-------------------------------
        //店商品分類別請求履歴リストの取得
        //-------------------------------
        List miseSeikyuBnruiList = uiShoBnrSeikyuRirekiDao.getMiseSeikyuBnrui(companyCd, urikakeCd, seikyushoId);
        if(miseSeikyuBnruiList != null && !miseSeikyuBnruiList.isEmpty()){
            //店コードごとのリストに変形
            miseSeikyuBnruiList = changeMiseGotoList(miseSeikyuBnruiList);

            //店コードごとのリストを加工
            //リストを加工(外税・内税の計算、記号のセット、ソート)
            List tmpList = new ArrayList();
            for (int i=0;i<miseSeikyuBnruiList.size(); i++) {

                //店コードごとのリスト
                List miseList = (List)miseSeikyuBnruiList.get(i);

                //商品分類ごとに合計したリスト作成
                miseList = makeNewMiseList(miseList);

                //請求分類のレコード(空)挿入
                miseList = addBunruiRecord(miseList, seikyuBnruiList);

                //記号のセット
                miseList = kigouSet(miseList);

                //ソート順に入れ替える
                Collections.sort(miseList, new SortComparator());

                tmpList.add(miseList);
            }
            miseSeikyuBnruiList = tmpList;

            //店コードが売掛先コードのものを先頭にする処理
            miseSeikyuBnruiList = forwardOnerDataWhenMiseData(miseSeikyuBnruiList, urikakeCd);
        }

        //リストをDTOにセット
        outputMap.put("utiwakeBillList", utiwakeBillList);
// add 2023/02/07 USI範  begin
        outputMap.put("tenPercentList", tenPercentList);
        outputMap.put("gennZeiList", gennZeiList);
        outputMap.put("keikaSotiList", keikaSotiList);
        outputMap.put("hiKazeiList", hiKazeiList);
// add 2023/02/07 USI範  end
        outputMap.put("miseSeikyuList", miseSeikyuList);
        outputMap.put("onerSeikyuBnruiList", onerSeikyuBnruiList);
        outputMap.put("miseSeikyuBnruiList", miseSeikyuBnruiList);

        return outputMap;
    }

    /** 商品分類別請求履歴リストに請求分類にないレコード(空)を挿入する
     * @param  (加工前)店商品分類別請求履歴のリスト
     * @param  請求書用分類リスト
     * @return (加工後)店商品分類別請求履歴のリスト
     */
     private List addBunruiRecord(List rirekiList, List seikyuBnruiList){

         for(int i=0; i<seikyuBnruiList.size(); i++) {

             CodSeikyuBnrui cod = (CodSeikyuBnrui)seikyuBnruiList.get(i);
             String bunrui = cod.getSeikyuBnrui();
             String miseCd = "";
             String miseNameKj = "";

             //存在フラグ
             boolean flg = false;

             for(int j=0; j<rirekiList.size(); j++) {

                 //履歴の1レコード取得
                 UIShoBnrSeikyuRireki record = (UIShoBnrSeikyuRireki)rirekiList.get(j);
                 miseCd = record.getMiseCd();
                 miseNameKj = record.getMiseNameKj();

                 if(bunrui != null && bunrui.equals(record.getSeikyuBnrui())) {
                     //存在する時
                     flg = true;
                     break;
                 }
             }

             //存在しない場合は新規レコード(空)を作成
             if(!flg){
                 UIShoBnrSeikyuRireki newRecord = new UIShoBnrSeikyuRireki();
                 newRecord.setSeikyuBnrui(bunrui);
                 newRecord.setSeBnrName(cod.getSeBnrName());
                 newRecord.setMiseCd(miseCd);                   //店コード
                 newRecord.setMiseNameKj(miseNameKj);           //店名称
                 newRecord.setSotoKin(new BigDecimal(0));       //外税金額
                 newRecord.setUtiKin(new BigDecimal(0));        //内税金額

                 rirekiList.add(newRecord);
             }
         }
         return rirekiList;
     }

    /** 店コードが売掛先コードのものを先頭にする処理
    * @param  店商品分類別請求履歴リスト
    * @return 店商品分類別請求履歴リスト
    */
    private List forwardOnerDataWhenMiseData(List list, String urikakeCd){

        List forwardList = new ArrayList();
        List bodyList    = new ArrayList();

        for(int i=0; i<list.size(); i++) {

            List miseList = (List)list.get(i);
            UIShoBnrSeikyuRireki record = (UIShoBnrSeikyuRireki)miseList.get(0);
            String miseCd = record.getMiseCd();

            if (miseCd != null && miseCd.length() >0 && miseCd.equals(urikakeCd)) {
                forwardList = miseList;
            } else {
                bodyList.add(miseList);
            }
        }

        List retList = new ArrayList();
        retList.add(forwardList);
        retList.addAll(bodyList);

        return retList;
    }

    /** 店コードごとのリストを「商品分類ごとに合計したリスト」を作成する
     * @param  (店コードごとの)店商品分類別請求履歴リスト
     * @return (商品分類ごとに合計された)店商品分類別請求履歴リスト
     */
     private List makeNewMiseList(List miseList){

         HashMap tmpMap  = new HashMap();
         List keyList = new ArrayList();

         for(int i=0; i<miseList.size(); i++) {

             //-----------------------------------------
             //１レコード分の外税金額・内税金額を求める
             //-----------------------------------------
             UIShoBnrSeikyuRireki record = (UIShoBnrSeikyuRireki)miseList.get(i);
             String bunrui = record.getSeikyuBnrui();       //商品分類
             String staxUri = record.getStaxUri();          //消費税区分(売上)
             BigDecimal sotoKin = new BigDecimal(0);
             BigDecimal utiKin  = new BigDecimal(0);

             //内税の時
             if (staxUri.equals("1") || staxUri.equals("3")) {
                 utiKin = (record.getUriKin()).add(record.getUchiTax());
             }
             //外税
             else if (staxUri.equals("0")) {
                 sotoKin = record.getUriKin();
             }
             record.setSotoKin(sotoKin);
             record.setUtiKin(utiKin);

             //-----------------------
             //同じ商品分類の値を保持
             //-----------------------
             if (tmpMap.containsKey(bunrui)){
                 //既にレコードが存在する時

                 UIShoBnrSeikyuRireki record2 = (UIShoBnrSeikyuRireki)tmpMap.get(bunrui);

                 record2.setSotoKin(sotoKin.add(record2.getSotoKin()));
                 record2.setUtiKin(utiKin.add(record2.getUtiKin()));

                 //同じキーで再格納
                 tmpMap.put(bunrui, record2);

             } else {
                 //新規の場合(レコードとキーを保持)
                 tmpMap.put(bunrui, record);
                 keyList.add(bunrui);
             }
         }

         //リストに入れなおす
         List retList = new ArrayList();
         for(int i=0; i<keyList.size(); i++) {
             String key = (String)keyList.get(i);
             retList.add((UIShoBnrSeikyuRireki)tmpMap.get(key));
         }
         return retList;
     }

    /** 店商品分類別請求履歴リストを元に、「店コード」ごとのリストを作成し、そのリストを返却する。
     * @param  店商品分類別請求履歴リスト
     * @return (店コードごとにまとめられた)店商品分類別請求履歴リスト
    */
    private List changeMiseGotoList(List list){

        //---------------------------------------
        //「店コード」ごとのリストを作成する
        //---------------------------------------
        String miseCd = "";
        List allList = new ArrayList();
        List miseList = null;

        for (int i=0; i<list.size(); i++) {

            //加工対象1レコード分取得
            UIShoBnrSeikyuRireki record = (UIShoBnrSeikyuRireki)list.get(i);

            //前レコードと店コードが異なった時
            if (record.getMiseCd() != null && record.getMiseCd().length() > 0 && !(record.getMiseCd().equals(miseCd))) {

                if(miseList != null) {
                    //全リストに追加
                    allList.add(miseList);
                }

                //新規の店リスト作成
                miseList = new ArrayList();
            }

            //店リストに追加
            miseList.add(record);

            //店コードを保持
            miseCd   = record.getMiseCd();
        }

        if(miseList != null) {
            allList.add(miseList);
        }
        return allList;
    }

    /** 店別請求情報リストに「税込・非課税額」をセットする。
     * @param  (加工前)店別請求情報リスト
     * @return (加工後)店別請求情報リスト
    */
    private List addZeikomiHikazei(List list){

        //データ加工
        for (int i=0; i < list.size() ; i++) {

            //加工対象1レコード分取得
            UIMiseSeikyuRireki record = (UIMiseSeikyuRireki)list.get(i);

            BigDecimal taxn = record.getTaxnKin();  //非課税対象商品学
            BigDecimal komi = record.getKomiKin();  //税込商品学
            BigDecimal uchi = record.getUchiTax();  //内税(算出)消費税

            BigDecimal tmp = taxn.add(komi).add(uchi);

            //税込・非課税額をセット
            record.setZeikomiHikazei(tmp);
        }
        return list;
    }

    /**
     * [インナークラス]比較用
     */
    private class SortComparator implements Comparator {

        public int compare(Object obj1, Object obj2) {

            //比較対象エンティティ
            UIShoBnrSeikyuRireki ent1 = (UIShoBnrSeikyuRireki)obj1;
            UIShoBnrSeikyuRireki ent2 = (UIShoBnrSeikyuRireki)obj2;

            String str1 = ent1.getSeikyuBnrui();
            str1 = getSortNo(str1);

            String str2 = ent2.getSeikyuBnrui();
            str2 = getSortNo(str2);

            return str1.compareTo(str2);
        }
    }

    /**
     * 請求書分類記号シートより、[請求分類]に対応する[ソート番号]を取得する
     * @param  請求分類
     * @return ソート番号
     */
    private String getSortNo(String str) {

        List list = getSeikyuBunruiList();

        for(int i=0; i<list.size(); i++) {

            SeikyuBunruiKigouDto dto = (SeikyuBunruiKigouDto)seikyuBunruiList.get(i);

            String bunrui = dto.getBunrui();
            String sort = dto.getSort();

            if(str != null && str.equals(bunrui)) {
                return sort;
            }
        }
        return null;
    }

    /**
     * 請求書分類記号シートより、[請求分類]に対応する[請求書分類記号]を取得する
     * @param  請求分類
     * @return 請求書分類記号
     */
    private String getKigou(String str) {

        List list = getSeikyuBunruiList();

        for(int i=0; i<list.size(); i++) {

            SeikyuBunruiKigouDto dto = (SeikyuBunruiKigouDto)seikyuBunruiList.get(i);

            String bunrui = dto.getBunrui();
            String kigou = dto.getKigou();

            if(str != null && str.equals(bunrui)) {
                return kigou;
            }
        }
        return null;
    }


    /** オーナー商品分類別請求履歴リストに「請求書分類記号」をセットする。
     * @param  (加工前)オーナー商品分類別請求履歴リスト
     * @return (加工後)オーナー商品分類別請求履歴リスト
    */
    private List kigouSet(List list){

        //加工後のリスト
        List retList = new ArrayList();

        //データ加工
        for (int i=0; i < list.size() ; i++) {

            //加工対象1レコード分取得
            UIShoBnrSeikyuRireki record = (UIShoBnrSeikyuRireki)list.get(i);

            String kigou = getKigou(record.getSeikyuBnrui());

            if (kigou != null && kigou.length() >0) {
                record.setSeikyuBnruiKigo(kigou);
                retList.add(record);
            }
        }
        return retList;
    }


    /** 表示用にデータを整える。
     * @param  utiwakeBillList
     * @return list
    */
    private List formatUtiwakeBillList(List utiwakeBillList){

        List ret = new ArrayList();

        for (int i=0; i < utiwakeBillList.size() ; i++) {
            UIBillRireki record = (UIBillRireki)utiwakeBillList.get(i);

            //請求入金日付
            String ym  = record.getSeikyushoId().substring(0,6);
//add 2023/04/11 USI金 begin
            String ymt  = record.getSeikyushoId().substring(0,8);
            if(ymt.compareTo(record.changeDt) <= 0) {
            	record.setUtiwakeKbn("0");
            }else {
            	record.setUtiwakeKbn("1");
            }
//add 2023/04/11 USI金 end
            ym = formatYMKanji(ym);
            record.setSeikyuNyukinDt(ym + "度");

            //発効日
            String hakkoDt = record.getHakkoDt();
            hakkoDt = "(" + formatSlash(hakkoDt) + "発行)";
            record.setHakkoDt(hakkoDt);

            ret.add(record);
        }
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
     * @param  日付文字列(YYYYMMDD)
     * @return 日付文字列(YYYY/MM/DD)
    */
    private String formatSlash(String date){

        DateFormatter commonFormatter
            = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);

        date = commonFormatter.format(date, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);

        return date;
    }


    /////////////セッター・ゲッター///////////////

    /**
     * 請求書履歴DAOを取得します。
     * @return 請求書履歴DAO
     */
    public UIBillRirekiDao getUIBillListDao() {
        return uiBillRirekiDao;
    }
    /**
     * 請求書履歴DAOを設定します。
     * @param uiBillListDao 請求書履歴DAO
     */
    public void setUIBillRirekiDao(UIBillRirekiDao uiBillRirekiDao) {
        this.uiBillRirekiDao = uiBillRirekiDao;
    }

// add 2023/02/07 USI範  begin
    /**
     * 税率別の内訳DAOを取得します。
     * @return 請求書履歴DAO
     */
    public UITaxRateWakeDao getUITaxRateWakeDao() {
        return uITaxRateWakeDao;
    }
    /**
     * 税率別の内訳DAOを設定します。
     * @param uIZniRizuWakeDao 請求書履歴DAO
     */
    public void setUITaxRateWakeDao(UITaxRateWakeDao uITaxRateWakeDao) {
        this.uITaxRateWakeDao = uITaxRateWakeDao;
    }
// add 2023/02/07 USI範  end
    /**
     * 店別請求履歴情報DAOを取得します。
     * @return 店別請求履歴情報DAO
     */
    public UIMiseSeikyuRirekiDao getUIMiseSeikyuRirekiDao() {
        return uiMiseSeikyuRirekiDao;
    }
    /**
     * 店別請求履歴情報DAOを設定します。
     * @param uiMiseSeikyuRirekiDao 店別請求履歴情報DAO
     */
    public void setUIMiseSeikyuRirekiDao(UIMiseSeikyuRirekiDao uiMiseSeikyuRirekiDao) {
        this.uiMiseSeikyuRirekiDao = uiMiseSeikyuRirekiDao;
    }

    /**
     * 商品分類別請求履歴DAOを取得します。
     * @return 商品分類別請求履歴DAO
     */
    public UIShoBnrSeikyuRirekiDao getUIShoBnrSeikyuRirekiDao() {
        return uiShoBnrSeikyuRirekiDao;
    }
    /**
     * 商品分類別請求履歴DAOを設定します。
     * @param uiMiseSeikyuRirekiDao 商品分類別請求履歴DAO
     */
    public void setUIShoBnrSeikyuRirekiDao(UIShoBnrSeikyuRirekiDao uiShoBnrSeikyuRirekiDao) {
        this.uiShoBnrSeikyuRirekiDao = uiShoBnrSeikyuRirekiDao;
    }

    /**
     * 請求書用分類DAOを取得します。
     * @return 請求書用分類DAO
     */
    public CodSeikyuBnruiDao getCodSeikyuBnruiDao() {
        return codSeikyuBnruiDao;
    }
    /**
     * 請求書用分類DAOを設定します。
     * @param codSeikyuBnruiDao 請求書用分類DAO
     */
    public void setCodSeikyuBnruiDao(CodSeikyuBnruiDao codSeikyuBnruiDao) {
        this.codSeikyuBnruiDao = codSeikyuBnruiDao;
    }

    /**
     * 請求書分類記号を取得します。
     * @return 請求書分類記号
     */
    public List getSeikyuBunruiList() {
        return seikyuBunruiList;
    }
    /**
     * 請求書分類記号を設定します。
     * @param codSeikyuBnruiDao 請求書分類記号
     */
    public void setSeikyuBunruiList(List seikyuBunruiList) {
        this.seikyuBunruiList = seikyuBunruiList;
    }
}

