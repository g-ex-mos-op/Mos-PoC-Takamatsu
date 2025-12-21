package jp.co.isid.mos.bird.bill.demanddeposithistory.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.UIBillRirekiDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.UINyukinMeisaiDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dao.UITaxRateWakeDao;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UIBillRireki;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UINyukinMeisai;
import jp.co.isid.mos.bird.bill.demanddeposithistory.entity.UITaxRateWake;
import jp.co.isid.mos.bird.bill.demanddeposithistory.logic.GetSeikyuNyukinInfoLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 業務支援　ご請求ご入金履歴画面
 * 請求入金情報の取得ロジック
 * @author xwatanabe
 */
public class GetSeikyuNyukinInfoLogicImpl implements GetSeikyuNyukinInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS009L01";

    /** DAO */
    private UIBillRirekiDao   uiBillRirekiDao;          //請求書履歴DAO
    private UINyukinMeisaiDao uiNyukinMeisaiDao;        //入金明細DAO
// add 2023/02/07 USI範  begin
    private UITaxRateWakeDao uITaxRateWakeDao;          // 税率別の内訳DAO
// add 2023/02/07 USI範  end

    /** 年月出力月数 */
    private static int NENGETU_DISPLAY_MONTH = 14;
    /** ループ最終日付デフォルト値 */
    private static String LOOP_END_DATE_DEFAULT = "99999999";

    /** 指定オーナーの売掛先一覧を取得する。
     * @param  会社コード
     * @param  オーナーコード
     * @param  検収日付
     * @return 請求書履歴のリスト
    */
    public HashMap execute(String companyCd, String onerCd, String kensuDt) {

        //返却用マップ
        HashMap map = new HashMap();

        //パラメータチェック
        if(companyCd == null || companyCd.getBytes().length == 0){
            throw new NotNullException("会社コード");
        }
        if(onerCd == null || onerCd.getBytes().length == 0){
            throw new NotNullException("オーナーコード");
        }
        if(kensuDt == null || kensuDt.getBytes().length == 0){
            throw new NotNullException("検収日付");
        }

        //検収日付より「表示開始年月」「表示終了年月」を求める
        String startYM = "";
        String endYM   = "";
        endYM = kensuDt.substring(0,6);

        try {
            startYM = DateManager.getPrevMonth(endYM, NENGETU_DISPLAY_MONTH  - 1);
        } catch (Exception ex) {
            throw new FtlSystemException("期間指定生成",
                "アプリ日付年月["+endYM+"]から["+NENGETU_DISPLAY_MONTH+"]を引く際のDateManager.getPrevMonthメソッド処理で例外が発生しました。",
                ex);
        }

        //-----------------------------
        // <1> 請求書履歴リストの取得
        //-----------------------------
        List billList = uiBillRirekiDao.getBillList(companyCd, onerCd, startYM, endYM);
        if(billList == null || billList.isEmpty()){
            //存在しない時
            throw new NotExistException("ご請求先");
        }

        //---------------------------
        // <2> 入金明細リストの取得
        //---------------------------
        List nyukinList = uiNyukinMeisaiDao.getNyukinList(companyCd, onerCd, startYM, endYM);
        //存在しない時のチェックは必要なし

        //------------------------------------------------------------
        // <3> 表示用に編集したリスト(最新タブ用・履歴タブ用)の作成
        //------------------------------------------------------------
        //マージしたリスト作成

        List mergeList = mergeBillListWithNyukinList(billList, nyukinList);

        //リストの並び替え
        mergeList = sortList(mergeList, onerCd);

        //最新タブ表示用リスト
        List saisinTabList = makeSaisinTabList(mergeList);
// add 2023/02/07 USI範  begin
        //売掛コード、請求書IDを取得
        String urikakeCd = "";
        String seikyushoId = "";
        for (int i=0 ; i<saisinTabList.size(); i++) {
            UIBillRireki billRecord = (UIBillRireki)saisinTabList.get(i);
               urikakeCd = billRecord.getUrikakeCd();
               seikyushoId = billRecord.getSeikyushoId();
        	}
//modify 2023/05/08 USI金 begin
//        //税率別の内訳表示用リスト
//        List tenPercentList = uITaxRateWakeDao.getTenPercent(companyCd, urikakeCd, seikyushoId);
//        List gennZeiList = uITaxRateWakeDao.getGennZei(companyCd, urikakeCd, seikyushoId);
//        List keikaSotiList = uITaxRateWakeDao.getKeikaSoti(companyCd, urikakeCd, seikyushoId);
//        List hiKazeiList = uITaxRateWakeDao.getHiKazei(companyCd, urikakeCd, seikyushoId);
        //税率別の内訳表示用リスト
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
        UIBillRireki record = (UIBillRireki)saisinTabList.get(0);
        if(!tenPercentList.isEmpty()) {
        	UITaxRateWake TenRecord = (UITaxRateWake)tenPercentList.get(0);
            if(record.getUtiwakeKbn().equals("1")) {
                TenRecord.setUtiwakeKbn("1");
             }
        }
//add 2023/04/14 USI金 end
        //履歴タブ表示用リスト
        List rirekiTabList = makeRirekiTabList(mergeList);

        //返却用マップに格納
        map.put("saisinTabList" , saisinTabList);
        map.put("rirekiTabList" , rirekiTabList);
// add 2023/02/07 USI範  begin
        map.put("tenPercentList" , tenPercentList);
        map.put("gennZeiList" , gennZeiList);
        map.put("keikaSotiList" , keikaSotiList);
        map.put("hiKazeiList" , hiKazeiList);
// add 2023/02/07 USI範  end
        return map;
    }

    /**
     * 最新タブの表示内容作成処理
     * @param  請求書履歴リスト
     * @return 最新タブの表示内容のリスト
    */
    private List makeSaisinTabList(List list){

        List saisinTabList = new ArrayList();

        for (int i=0; i<list.size(); i++) {

            //１レコード取得
            UIBillRireki record = (UIBillRireki)list.get(i);

            //最新フラグが立っているレコードを取得
            if (record.getSaisinFlg()) {
                saisinTabList.add(record);
            }
        }
        return saisinTabList;
    }

    /**
     * 履歴タブの表示内容作成処理
     * @param  請求入金情報リスト
     * @return 表示用リスト
     */
    private List makeRirekiTabList(List list) {

        List rirekiTabList = new ArrayList();

        //請求先ごとのリスト
        List urikakeList = new ArrayList();

        //請求先(売掛先コード)保持用
        String urikakeCdZen = "";

        for (int i=0; i<list.size(); i++) {

            //１レコード取得
            UIBillRireki record = (UIBillRireki)list.get(i);

            //最初のレコードの場合
            if (i == 0) {
                urikakeList = new ArrayList();
                urikakeList.add(record);
                urikakeCdZen = record.getUrikakeCd();
            }
            //最後のレコードの場合
            else if (i == list.size() - 1) {

                //今までの請求先と異なる場合
                if (! (urikakeCdZen.equals(record.getUrikakeCd()))) {
                    rirekiTabList.add(urikakeList);

                    urikakeList = new ArrayList();
                    urikakeList.add(record);
                }else{
                    urikakeList.add(record);
                }

                //履歴タブリストに追加
                rirekiTabList.add(urikakeList);
            }
            //その他のレコードの場合
            else{

                //今までの請求先と同じ場合
                if(urikakeCdZen.equals(record.getUrikakeCd())){
                    urikakeList.add(record);
                }
                //今までの請求先と異なる場合
                else {
                    rirekiTabList.add(urikakeList);

                    urikakeList = new ArrayList();
                    urikakeList.add(record);
                    urikakeCdZen = record.getUrikakeCd();
                }
            }
        }
        return rirekiTabList;
    }

    /** 請求書履歴リストと入金明細リストをマージする。
     * @param  請求書履歴リスト
     * @param  入金明細リスト
     * @return マージされた請求書履歴リスト
    */
    private List mergeBillListWithNyukinList(List billList, List nyukinList){

        List outputList = new ArrayList();

        for (int i=0 ; i<billList.size(); i++) {

            //現レコード取得
            UIBillRireki billRecord = (UIBillRireki)billList.get(i);

            //請求入金日付と請求入金区分の編集
            String ym  = billRecord.getSeikyushoId().substring(0,6);
//add 2023/04/12 USI金 begin
            String ymt  = billRecord.getSeikyushoId().substring(0,8);
            if(ymt.compareTo(billRecord.changeDt) <= 0) {
            	billRecord.setUtiwakeKbn("0");
            }else {
            	billRecord.setUtiwakeKbn("1");
            }
//add 2023/04/12 USI金 end
            String hakkoDt = billRecord.getHakkoDt();
            ym = formatYMKanji(ym);
            hakkoDt = formatSlash(hakkoDt);

            billRecord.setSeikyuNyukinDt(ym + "度");
            billRecord.setHakkoDt("(" + hakkoDt + "発行)");
            billRecord.setSeikyuNyukinKbn("1");
            billRecord.setZandaka(new BigDecimal(0));

            //現レコードをリストに追加
            billRecord.setIsSeikyuInfo(true);
            outputList.add(billRecord);

            //-----------------------------------------------------------------
            //処理①
            //「ループ最終日付」と最新フラグ(請求先の最新請求か否か)を求める
            //-----------------------------------------------------------------
            String loopEndDate = LOOP_END_DATE_DEFAULT;

            if (i < billList.size() -1) {
                //最終レコード以外の時

                //次のレコード取得
                UIBillRireki billNext = (UIBillRireki)billList.get(i + 1);

                //最新フラグの設定
                if (billRecord.getUrikakeCd().equals(billNext.getUrikakeCd())){
                    billRecord.setSaisinFlg(false);     //OFF
                } else {
                    billRecord.setSaisinFlg(true);      //ON
                }

                //ループ最終日付
                if (billRecord.getUrikakeCd().equals(billNext.getUrikakeCd())){
                    loopEndDate = billNext.getSeikyushoId().substring(0,8);
                }
            } else {
                //最終レコードの時

                //最新フラグON
                billRecord.setSaisinFlg(true);
            }

            //--------------------------------
            //②追加レコード作成(入金情報)
            //--------------------------------
            makeNyukinData(billRecord, nyukinList, loopEndDate, outputList);

        }
        return outputList;
    }


    /** 入金情報より請求情報を作成する。
     * @param  請求書履歴エンティティ(UIBillRireki)
     * @param  入金明細エンティティ(UINyukinMeisai)
     * @param  ループ最終日付
     * @return UIBillRireki
    */
    private void makeNyukinData(UIBillRireki billRecord, List nyukinList, String loopEndDt, List outputList){

        if (nyukinList != null && nyukinList.size() > 0) {

            //-------------------------
            //前回レコード情報を保持用
            //-------------------------
            BigDecimal zenkaiZandaka = new BigDecimal(0);   //前回分の残高
            int syubetsu = 0;                               //種別フラグ(0:請求、1:入金)


            for (int i=0; i < nyukinList.size(); i++) {

                //入金情報レコード取得
                UINyukinMeisai nyukinRecord = (UINyukinMeisai)nyukinList.get(i);

                //----------
                //条件判定
                //----------
                boolean condFlg = true;

                //条件１
                if (! billRecord.getUrikakeCd().equals(nyukinRecord.getUrikakeCd())) {
                    condFlg = false;
                }

                //条件２
                String seikyushoid = billRecord.getSeikyushoId();
                seikyushoid = seikyushoid.substring(0, 8);
                if (! (nyukinRecord.getNyukinDt().compareTo(seikyushoid) > 0) ) {
                    condFlg = false;
                }

                //条件３
                if (! (nyukinRecord.getNyukinDt().compareTo(loopEndDt) <= 0) ){
                    condFlg = false;
                }

                //---------------------
                //新規レコード作成
                //---------------------
                //上記条件(１～３)に該当するレコードの場合のみ、新規レコードを作成。
                if (condFlg) {

                    UIBillRireki billNewRecord = new UIBillRireki();

                    billNewRecord.setUrikakeCd(nyukinRecord.getUrikakeCd());                    //売掛先コード
                    billNewRecord.setOnerNameKj(nyukinRecord.getOnerNameKj());                  //売掛先名称
                    billNewRecord.setSeikyuNyukinDt(formatYMDKanji(nyukinRecord.getNyukinDt()));//請求入金日付
                    billNewRecord.setHakkoDt("");                                               //発効日
                    billNewRecord.setSeikyuNyukinKbn("2");                                      //請求入金区分
                    billNewRecord.setNyukinGaku(nyukinRecord.getNyukinGaku());                  //入金額

                    //残高
                    if (syubetsu == 0) {
                        //前レコードが請求の時
                        BigDecimal konkaiSeikyu = billRecord.getSeikyuKon();
                        BigDecimal konkaiNyukin = nyukinRecord.getNyukinGaku();
                        zenkaiZandaka = konkaiSeikyu.subtract(konkaiNyukin);
                        billNewRecord.setZandaka(zenkaiZandaka);
                    } else {
                        //前レコードが入金の時
                        BigDecimal konkai  = nyukinRecord.getNyukinGaku();
                        zenkaiZandaka = zenkaiZandaka.subtract(konkai);
                        billNewRecord.setZandaka(zenkaiZandaka);
                    }

                    //後処理
                    syubetsu = 1;
                    outputList.add(billNewRecord);
                }
            }
        }
    }

    /** 請求書履歴リストをの順番を入れ替える。
     *  指定したオーナーコード＝リストの売掛先コードのデータを先頭になるように入れ替える。
     * @param  請求書履歴リスト
     * @param  オーナーコード
     * @return ソートされた請求書履歴リスト
    */
    private List sortList(List billList, String onerCd){

        List retList = new ArrayList();
        List tmpList = new ArrayList();

        for (int i=0; i<billList.size(); i++) {

            UIBillRireki billRecord = (UIBillRireki)billList.get(i);

            if (onerCd.equals(billRecord.getUrikakeCd())) {
                retList.add(billRecord);
            } else {
                tmpList.add(billRecord);
            }
        }
        retList.addAll(tmpList);

        return retList;
    }

    /** 日付のフォーマット。
     * @param  日付文字列(YYYYMMDD)
     * @return 日付文字列(YYYY年MM月DD日)
    */
    private String formatYMDKanji(String date){

        DateFormatter commonFormatter
            = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_KANJI);

        date = commonFormatter.format(date, DateFormatter.PATTERN_KANJI_YMD, DateFormatter.DATE_TYPE_YMD);

        return date;
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
     * @return 税率別の内訳DAO
     */
    public UITaxRateWakeDao getUITaxRateWakeDao() {
        return uITaxRateWakeDao;
    }

    /**
     * 税率別の内訳DAOを設定します。
     * @param uITaxRateWakeDao 税率別の内訳DAO
     */
    public void setUITaxRateWakeDao(UITaxRateWakeDao uITaxRateWakeDao) {
        this.uITaxRateWakeDao = uITaxRateWakeDao;
    }
// add 2023/02/07 USI範  end

	/**
	 * 入金明細DAOを取得します。
	 * @return 入金明細DAO
	 */
     public UINyukinMeisaiDao getUINyukinMeisaiDao() {
    	 return uiNyukinMeisaiDao;
}

     /**
      * 入金明細DAOを設定します。
      * @param uiNyukinMeisaiDao 入金明細DAO
      */
     public void setUINyukinMeisaiDao(UINyukinMeisaiDao uiNyukinMeisaiDao) {
    	 this.uiNyukinMeisaiDao = uiNyukinMeisaiDao;
     }

}

