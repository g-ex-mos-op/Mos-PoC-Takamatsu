/*
 * 作成日: 2006/08/04
 */
package jp.co.isid.mos.bird.bill.detailbilldownload.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import jp.co.isid.mos.bird.bill.detailbilldownload.dao.UISeikyuMeisaiDao;
import jp.co.isid.mos.bird.bill.detailbilldownload.dto.DetailBillDownloadListDto;
import jp.co.isid.mos.bird.bill.detailbilldownload.entity.UIBillList;
import jp.co.isid.mos.bird.bill.detailbilldownload.entity.UISeikyuMeisai;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;


/**
 * 請求明細取得ロジック
 * 指定売掛先、年月の請求明細情報を取得する。
 * 
 * @author xwatanabe
 */
public class GetSeikyuMeisaiLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS010L03";
    /** ファイル名 */
    public static final String FILE_NAME = "MEISAI.csv";
    /** 請求済売掛明細DAO */
    private UISeikyuMeisaiDao uiSeikyuMeisaiDao;

    /**
     * CSV出力用のデータ取得処理
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        //出力用リスト
        List outputList = new ArrayList();
        
        //----------------------------
        //パラメータの取得
        //----------------------------
        DetailBillDownloadListDto dto = (DetailBillDownloadListDto)csvOutputDto;
        String companyCd = dto.getCompanayCd();             //会社コード
        String urikakeCd = dto.getUrikakeCd();              //売掛先コード
        String radioNarabi = dto.getRadioNarabi();          //ソート順

        int radioNengetu = dto.getRadioNengetu();
        List seikyuList = dto.getSeikyuList();
        UIBillList uiBillList = (UIBillList)seikyuList.get(radioNengetu);
        //String seikyuId = uiBillList.getSeikyushoId();    //請求書ID(不要？)
        String nengetu = uiBillList.getNengetu();           //指定年月

        //----------------------------
        //該当データ(請求明細)の取得
        //----------------------------
        List list = uiSeikyuMeisaiDao.getSeikyuMeisai(companyCd,urikakeCd,nengetu,radioNarabi);
        if(list == null){
            //存在しない時
            throw new NoResultException();
        }
        
        //----------------------------
        //CSV出力用LISTの作成
        //----------------------------
        for(int i=0 ; i<list.size() ; i++){
            
            //エンティティ取得
            UISeikyuMeisai uiSeikyuMeisai = (UISeikyuMeisai)list.get(i);

            //①ヘッダー部作成
            if (i == 0) {
                //ヘッダー１行目
                List header1 = new ArrayList();
                header1.add("");
                header1.add(uiSeikyuMeisai.getUrikakeCd() + " " + uiSeikyuMeisai.getUrikakeName() + "殿");
                outputList.add(header1);

                //ヘッダー２行目
                List header2 = new ArrayList();
                header2.add("");
                header2.add(formatYM(nengetu) + "度");
                outputList.add(header2);

                //ヘッダー３行目(空)
                List header3 = new ArrayList();
                outputList.add(header3);

                //ヘッダー４行目(空)
                List header4 = new ArrayList();
                outputList.add(header4);

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
                outputList.add(bodyheader);
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
            listRow.add(uiSeikyuMeisai.getStaxUri());             //税区
            listRow.add(uiSeikyuMeisai.getShoAmount());           //数量
            listRow.add(uiSeikyuMeisai.getNisuName());            //単位
            listRow.add(uiSeikyuMeisai.getNohinTanka());          //単価
            listRow.add(getKingaku(uiSeikyuMeisai));              //金額
            listRow.add(uiSeikyuMeisai.getTekiyou());             //摘要
            listRow.add(uiSeikyuMeisai.getShoCdDai());            //POS商品コード
            
            outputList.add(listRow);
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
     * @param  UISeikyuMeisai
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
                    DateFormatter.DATE_TYPE_YM);
            
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

    ///////////////////////ここより下、ゲッター・セッター/////////////////////////////////

    /**
     * 請求済売掛明細DAOを取得します。
     * @return 請求済売掛明細DAO
     */
    public UISeikyuMeisaiDao getUiSeikyuMeisaiDao() {
        return uiSeikyuMeisaiDao;
    }
    /**
     * 請求済売掛明細DAOを設定します。
     * @param uiUrikakeListDao 請求済売掛明細DAO
     */
    public void setUiSeikyuMeisaiDao(UISeikyuMeisaiDao uiSeikyuMeisaiDao) {
        this.uiSeikyuMeisaiDao = uiSeikyuMeisaiDao;
    }
}
