/*
 * 作成日: 2006/08/04
 */
package jp.co.isid.mos.bird.bill.buyinglistview.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bill.buyinglistview.dao.UISeikyuMeisaiDao;
import jp.co.isid.mos.bird.bill.buyinglistview.dto.BuyingListViewDto;
import jp.co.isid.mos.bird.bill.buyinglistview.entity.UISeikyuMeisai;
import jp.co.isid.mos.bird.bill.buyinglistview.entity.UISeikyuPDFInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;


/**
 * 請求明細取得ロジック
 * 指定売掛先、年月の請求明細情報を取得する。
 *
 * @author xlee
 */
public class GetSeikyuMeisaiLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS012L03";

    /** ファイル名 */
    public static final String FILE_NAME = "MEISAI.csv";

    /** 軽減税率開始月 */
    public static final String KIKAN_TAX_START_YM = "20191002";

    /** 請求済売掛明細DAO */
    private UISeikyuMeisaiDao uiSeikyuMeisaiDao;

    // 納品書順PDFダウンロード可能基準発効日
    private String nohinPdfBaseHakkoDate;

    /**
     * 請求済売掛明細DAOを取得します。
     * @return 請求済売掛明細DAO
     */
    public UISeikyuMeisaiDao getUiSeikyuMeisaiDao() {
        return uiSeikyuMeisaiDao;
    }
    /**
     * 請求済売掛明細DAOを設定します。
     * @param uiSeikyuMeisaiDao 請求済売掛明細DAO
     */
    public void setUiSeikyuMeisaiDao(UISeikyuMeisaiDao uiSeikyuMeisaiDao) {
        this.uiSeikyuMeisaiDao = uiSeikyuMeisaiDao;
    }

    /**
     * CSV出力用のデータ取得処理
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        //出力用リスト
        List outputList = new ArrayList();

        BuyingListViewDto dto = (BuyingListViewDto)csvOutputDto;
        String companyCd = dto.getCondCompanyCd();    //会社コード
        String urikakeCd = dto.getUrikakeCd();        //売掛先コード
//        String seikyuId = dto.getSeikyushoId().substring(0,6);       //請求書ID
        String seikyuId = dto.getSeikyushoId();       //請求書ID
        String sortKbn = dto.getCondKbn();            //ソート順
        String seikyuDt = dto.getSeikyuDt();          //ダウンロードするｃｓｖの年度
        boolean isViewKikanMark = (KIKAN_TAX_START_YM.compareTo(seikyuDt) <= 0 ? true : false); //add 2019/06/13 xou.zoubun 税率マークの追加

        //--- 2006/10/19 add start 2006/09以前の納品書順PDFはダウンロード不可
        // 発行日が2006/10/01以前は不可
        if ("2".equals(sortKbn)) {
            int index = dto.getDownloadIndex();
            UISeikyuPDFInfo entity = (UISeikyuPDFInfo) dto.getBuyingDataList().get(index);
            String hakkoDate = entity.getHakkoDt();
            if (hakkoDate.compareTo(getNohinPdfBaseHakkoDate()) < 0) {
                throw new GenericMessageException("納品書順のご提供は２００６年９月以降となります。");
            }
        }
        //--- 2006/10/19 add end

        //該当データ(請求明細)の取得
        List list = uiSeikyuMeisaiDao.getSeikyuMeisai(companyCd,urikakeCd,seikyuId,sortKbn);
        if(list == null || list.size() == 0){
            //存在しない時
            throw new NoResultException();
        }

        NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
        NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);

        //CSV出力用LISTの作成
        UISeikyuMeisai uiSeikyuMeisai = null;
        int i = 0;
        for(Iterator ite = list.iterator(); ite.hasNext();){
            //エンティティ取得
            uiSeikyuMeisai = (UISeikyuMeisai) ite.next();

            //①ヘッダー部作成
            if (i == 0) {
                //ヘッダー１行目
                List header1 = new ArrayList();
                header1.add("");
                header1.add(uiSeikyuMeisai.getUrikakeCd() + " " + uiSeikyuMeisai.getUrikakeName() + "殿");
                outputList.add(header1);
                header1 = null;

                //ヘッダー２行目
                List header2 = new ArrayList();
                header2.add("");

                header2.add(formatYM(seikyuDt) + "度");
                outputList.add(header2);
                header2 = null;

                //ヘッダー３行目(空)
                List header3 = new ArrayList();
                outputList.add(header3);
                header3 = null;

                //ヘッダー４行目(空)
                List header4 = new ArrayList();
                outputList.add(header4);
                header4 = null;

                //データ部のヘッダー
                List bodyheader = new ArrayList();
                bodyheader.add("店コード");
                bodyheader.add("店舗名");
                bodyheader.add("納品日");
                bodyheader.add("伝票番号");
                bodyheader.add("商品");
                bodyheader.add("商品名");
                bodyheader.add("分類");
                bodyheader.add("税区");
                bodyheader.add("数量");
                bodyheader.add("単位");
                bodyheader.add("単価");
                bodyheader.add("金額");
                bodyheader.add("摘要");
                bodyheader.add("POS商品コード");
                if (isViewKikanMark) bodyheader.add("*=軽減/#=経過");    //add 2019/06/13 xou.zoubun 税率マークの追加
                outputList.add(bodyheader);
                bodyheader = null;
            }

            String staxUriKbn = "";
            if(uiSeikyuMeisai.getStaxUri().equals("1")) {
            	staxUriKbn = "込";
            } else if(uiSeikyuMeisai.getStaxUri().equals("3")) {
            	staxUriKbn = "非";
            } else if(uiSeikyuMeisai.getStaxUri().equals("9")) {
            	staxUriKbn = "税";
            }

            //②データ部作成
            List listRow = new ArrayList();

            //１レコード分のリストを作成
            listRow.add(uiSeikyuMeisai.getMiseCd());              //店コード
            listRow.add(uiSeikyuMeisai.getMiseNameKj());          //店舗名
            listRow.add(formatYMD(uiSeikyuMeisai.getNohinDtJ())); //納品日
            listRow.add(uiSeikyuMeisai.getDenpyoNo());            //伝票番号
            listRow.add(uiSeikyuMeisai.getShoCdJitu());           //商品
            listRow.add(uiSeikyuMeisai.getShoNameKj());           //商品名
            listRow.add(uiSeikyuMeisai.getSeBnrName());           //分類
            listRow.add(staxUriKbn);                              //税区
            listRow.add(uiSeikyuMeisai.getShoAmount());           //数量
            listRow.add(uiSeikyuMeisai.getNisuName());            //単位
            listRow.add(numFmtdgt2.format(uiSeikyuMeisai.getNohinTanka())); //単価
            listRow.add(numFmtdgt0.format(getKingaku(uiSeikyuMeisai)));              //金額
            listRow.add(uiSeikyuMeisai.getTekiyou());             //摘要
            listRow.add(uiSeikyuMeisai.getShoCdDai());            //POS商品コード
            if (isViewKikanMark) listRow.add(uiSeikyuMeisai.getMark());    //軽減税率　add 2019/06/13 xou.zoubun 税率マークの追加
            outputList.add(listRow);

            listRow = null;
            i++;
            ite.remove();
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
     * 金額を設定
     * CSV出力用の「金額」を求める
     * @param  uiSeikyuMeisai
     * @return 金額
     */
    private BigDecimal getKingaku(UISeikyuMeisai uiSeikyuMeisai){

        //金額
        BigDecimal kingaku;

        //外税消費税
        BigDecimal sotoTax = uiSeikyuMeisai.getSotoTax();


        if(sotoTax.compareTo(new BigDecimal(0)) == 0){
            kingaku = uiSeikyuMeisai.getUriKin().add(uiSeikyuMeisai.getUchiTax());
        }else{
            kingaku = uiSeikyuMeisai.getUriKin();
        }

        return kingaku;
    }

    /**
     * 日付フォーマット処理(yyyy年MM月)
     * @param  date        変換前の日付文字列(yyyymm)
     * @return formatDate　変換後の日付文字列(yyyy年MM月)
     */
    private String formatYM(String date){

        DateFormatter daFom = new DateFormatter();

        String formatDate = "";

        try {
            formatDate =
                daFom.format(
                    date,
                    DateFormatter.PATTERN_MONTH_KANJI_YM,
                    DateFormatter.DATE_TYPE_YMD);

        } catch (Exception e) {
            throw new FtlSystemException("CSV作成:日付フォーマット処理");
        }
        return formatDate;
    }

    /**
     * 日付フォーマット処理(yyyy/MM/dd)
     * @param  date        変換前の日付文字列(yyyymm)
     * @return formatDate　変換後の日付文字列(yyyy/MM/dd)
     */
    private String formatYMD(String date){

        DateFormatter daFom = new DateFormatter();

        String formatDate = "";

        try {
            formatDate =
                daFom.format(
                    date,
                    DateFormatter.PATTERN_SLASH,
                    DateFormatter.DATE_TYPE_YMD);

        } catch (Exception e) {
            throw new FtlSystemException("CSV作成:日付フォーマット処理");
        }
        return formatDate;
    }
    public String getNohinPdfBaseHakkoDate() {
        return nohinPdfBaseHakkoDate;
    }
    public void setNohinPdfBaseHakkoDate(String nohinPdfBaseHakkoDate) {
        this.nohinPdfBaseHakkoDate = nohinPdfBaseHakkoDate;
    }
}
