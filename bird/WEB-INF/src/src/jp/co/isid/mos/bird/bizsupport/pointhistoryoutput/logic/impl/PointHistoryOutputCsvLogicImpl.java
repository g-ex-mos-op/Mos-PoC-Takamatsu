/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dto.PointHistoryOutputDto;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.UIHuyoPointRireki;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.UIPointRuikei;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.UITaishokuSeisan;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.UIYakuinPointRireki;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic.PointHistoryOutputLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * ポイント履歴出力画面 CVSデータリストの作成
 * @author Yuichi Tamura(ISID-AO)
 */
public class PointHistoryOutputCsvLogicImpl implements CsvOutputLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BBS038L01";

    //ポイント履歴  検索ロジック
    private PointHistoryOutputLogic pointHistoryOutputLogic;

    /** ポイント履歴 (1) */
    public static final String SYAIN_POINT_RIREKI  = "1";
    /** 退職時精算   (2) */
    public static final String TAISHOKU            = "2";
    /** 役員ポイント履歴 (3) */
    public static final String YAKUIN_POINT_RIREKI = "3";
    /** 累積ポイント確認 (4) */
    public static final String RUISEKI             = "4";

    /** 退職者含む */
    public static final String TAISHOKU_YES             = "1";
    /** 退職者含まない */
    public static final String TAISHOKU_NO              = "2";



    /**
     * ファイル名取得
     */
    public String getFileName(CsvOutputDto csvOutputDto) {

        // DTO
        PointHistoryOutputDto dto = (PointHistoryOutputDto) csvOutputDto;

        String index = dto.getShoriKbnIndex();
        if (index == null ) {
            throw new NotSelectedException("処理");
        }

        String fileName = "";

        if(SYAIN_POINT_RIREKI.equals(index)){
            fileName = "ponit_rireki.csv";

        } else if(TAISHOKU.equals(index)){
            fileName = "taishoku_seisan.csv";

        } else if(YAKUIN_POINT_RIREKI.equals(index)){
            fileName = "yakuin_ponit_rireki.csv";

        } else if(RUISEKI.equals(index)){
            fileName = "ponit_ruikei.csv";

        }
        return fileName;
    }

    /* (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        // DTO
        PointHistoryOutputDto dto = (PointHistoryOutputDto) csvOutputDto;

        /** ロジック【ポイント履歴  検索】実施 ****/
        List csvList = getPointHistoryOutputLogic().execute(dto);

        if (csvList == null || csvList.size() == 0) {
            throw new NoResultException();
        }

        dto.setCsvList(csvList);

        String index = dto.getShoriKbnIndex();
        if (index == null ) {
            throw new NotSelectedException("処理");
        }

        // CSV出力用List
        List<Object> listCSV = new ArrayList<Object>();

        try {
            // ヘッダ部
            makeHeader(dto, listCSV);

            if(SYAIN_POINT_RIREKI.equals(index)){
                /** 社員付与ポイント履歴(UIHuyoPointRireki) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    UIHuyoPointRireki entity = (UIHuyoPointRireki) ite.next();
                    //１行分のリスト作成
                    List<Object> listData = new ArrayList<Object>();
                    listData.add(nullConvert(entity.getNendo()));
                    listData.add(nullConvert(entity.getUserId()));
                    listData.add(nullConvert(entity.getUserNameKj()));
                    listData.add(nullConvert(entity.getPoint()));
                    listData.add(nullConvert(entity.getPointShuName()));
                    listData.add(nullConvert(entity.getKbCompanyCd()));
                    listData.add(nullConvert(entity.getKbCompanyName()));
                    listData.add(nullConvert(entity.getRankCd()));
                    listData.add(nullConvert(entity.getRankName()));
                    listData.add(formatYMD(entity.getNyusyaDt()));
                    listData.add(formatYMD(entity.getTaishokuDt()));
                    //modify 2018/03/27 xou.zoubun 勤続年数は、「X年Yヶ月Z日」にフォーマットする begin
                    //listData.add(formatKinzokuYear(entity.getKinzokuYear()));
                    listData.add(formatKinzokuYear(entity.getKinzokuYear()));
                    //modify 2018/03/27 xou.zoubun 勤続年数は、「X年Yヶ月Z日」にフォーマットする end
                    listData.add(nullConvert(entity.getRetireCd()));
                    listData.add(nullConvert(entity.getRetireName()));
                    listData.add(nullConvert(entity.getKaigaiFlgName()));
                    listData.add(nullConvert(entity.getBikou()));

                    listCSV.add(listData);
                }

            } else if(TAISHOKU.equals(index)){
                /** 退職時精算(UITaishokuSeisan) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    UITaishokuSeisan entity = (UITaishokuSeisan) ite.next();
                    //１行分のリスト作成
                    List<Object> listData = new ArrayList<Object>();
                    listData.add(nullConvert(entity.getNendo()));
                    listData.add(nullConvert(entity.getUserId()));
                    listData.add(nullConvert(entity.getUserNameKj()));
                    listData.add(nullConvert(entity.getPointRuikei()));
                    listData.add(nullConvert(entity.getKbCompanyCd()));
                    listData.add(nullConvert(entity.getKbCompanyName()));
                    listData.add(formatYMD(entity.getNyusyaDt()));
                    listData.add(formatYMD(entity.getTaishokuDt()));
                    //modify 2018/03/27 xou.zoubun 勤続年数は、「X年Yヶ月」にフォーマットする begin
                    //listData.add(formatKinzokuYear(entity.getKinzokuYear()));
                    listData.add(formatKinzokuYear(entity.getKinzokuYear()));
                    //modify 2018/03/27 xou.zoubun 勤続年数は、「X年Yヶ月」にフォーマットする end
                    listData.add(nullConvert(entity.getRetireCd()));
                    listData.add(nullConvert(entity.getRetireName()));
                    listData.add(nullConvert(entity.getSikyuRate()));
                    listData.add(nullConvert(entity.getTSeisanPoint()));
                    listData.add(nullConvert(entity.getBikou()));
                    listCSV.add(listData);
                }

            } else if(YAKUIN_POINT_RIREKI.equals(index)){
                /** 役員付与ポイント履歴(UIYakuinPointRireki) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    UIYakuinPointRireki entity = (UIYakuinPointRireki) ite.next();
                    //１行分のリスト作成
                    List<Object> listData = new ArrayList<Object>();
                    listData.add(nullConvert(entity.getNendo()));
                    listData.add(nullConvert(entity.getUserId()));
                    listData.add(nullConvert(entity.getUserNameKj()));
                    listData.add(nullConvert(entity.getPoint()));
                    listData.add(nullConvert(entity.getPointShuName()));
                    listData.add(nullConvert(entity.getKbCompanyCd()));
                    listData.add(nullConvert(entity.getKbCompanyName()));
                    listData.add(nullConvert(entity.getBikou()));
                    listCSV.add(listData);
                }

            } else if(RUISEKI.equals(index)){
                /** 社員役員ポイント累計(UIPointRuikei) */
                for (Iterator ite = dto.getCsvList().iterator(); ite.hasNext();) {
                    //Listからentityへキャストする
                    UIPointRuikei entity = (UIPointRuikei) ite.next();
                    //１行分のリスト作成
                    List<Object> listData = new ArrayList<Object>();
                    listData.add(nullConvert(entity.getUserId()));
                    listData.add(nullConvert(entity.getUserNameKj()));
                    listData.add(nullConvert(entity.getPoint()));
                    listData.add(nullConvert(entity.getKbCompanyCd()));
                    listData.add(nullConvert(entity.getKbCompanyName()));
                    listData.add(formatYMD(entity.getTaishokuDt()));

                    listCSV.add(listData);
                }
            }

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }
        return listCSV;
    }



    /* ヘッダ部 */
    private void makeHeader(PointHistoryOutputDto dto, List listCsv) {

        List<Object> listKbCompanyCd   = new ArrayList<Object>();
        List<Object> listUserId = new ArrayList<Object>();
        List<Object> listNendo    = new ArrayList<Object>();
        List<Object> listTaishoku    = new ArrayList<Object>();
        List<Object> listKoumoku = new ArrayList<Object>();
        List<Object> listSpace   = new ArrayList<Object>();

        try{

            String index = dto.getShoriKbnIndex();
            String taishokuFlg = dto.getTaishokuIndex();
            //会社
            listKbCompanyCd.add("会社：");
            listKbCompanyCd.add(nullConvert(dto.getKbCompanyName()));

            //社員番号
            listUserId.add("社員番号：");
            listUserId.add(nullConvert(dto.getUserId()));

            //年度
            listNendo.add("年度：");
            String nendoFrom = (String)nullConvert(dto.getNendoFrom());
            if(nendoFrom.trim().length() != 0){
                nendoFrom = nendoFrom  +  "年度";
            }
            listNendo.add(nendoFrom);
            listNendo.add("～");
            String nendoTo = (String)nullConvert(dto.getNendoTo());
            if(nendoTo.trim().length() != 0){
                nendoTo = nendoTo  +  "年度";
            }
            listNendo.add(nendoTo);

            //退職者
            listTaishoku.add("退職者：");
            if(TAISHOKU_YES.equals(dto.getTaishokuIndex())){
                listTaishoku.add("含む");

            }else if(TAISHOKU_NO.equals(dto.getTaishokuIndex())){
                listTaishoku.add("含まない");
            }


            // 項目ヘッダー
            if(SYAIN_POINT_RIREKI.equals(index)){
                listKoumoku.add("年度");
                listKoumoku.add("社員番号");
                listKoumoku.add("社員名");
                listKoumoku.add("ポイント");
                listKoumoku.add("ポイント付与種別");
                listKoumoku.add("会社コード");
                listKoumoku.add("会社名");
                listKoumoku.add("等級");
                listKoumoku.add("等級名");
                listKoumoku.add("入社年月日");
                listKoumoku.add("退職年月日");
                listKoumoku.add("勤続年数");
                listKoumoku.add("退職事由");
                listKoumoku.add("退職事由名");
                listKoumoku.add("海外赴任中");
                listKoumoku.add("備考");

            } else if(TAISHOKU.equals(index)){
                listKoumoku.add("年度");
                listKoumoku.add("社員番号");
                listKoumoku.add("社員名");
                listKoumoku.add("累計ポイント");
                listKoumoku.add("会社コード");
                listKoumoku.add("会社名");
                listKoumoku.add("入社年月日");
                listKoumoku.add("退職年月日");
                listKoumoku.add("勤続年数");
                listKoumoku.add("退職事由");
                listKoumoku.add("退職事由名");
                listKoumoku.add("支給率");
                listKoumoku.add("退職精算ポイント");
                listKoumoku.add("備考");

            } else if(YAKUIN_POINT_RIREKI.equals(index)){
                listKoumoku.add("年度");
                listKoumoku.add("社員番号");
                listKoumoku.add("社員名");
                listKoumoku.add("ポイント");
                listKoumoku.add("ポイント付与種別");
                listKoumoku.add("会社コード");
                listKoumoku.add("会社名");
                listKoumoku.add("備考");

            } else if(RUISEKI.equals(index)){
                listKoumoku.add("社員番号");
                listKoumoku.add("社員名");
                listKoumoku.add("累計ポイント");
                listKoumoku.add("会社コード");
                listKoumoku.add("会社名");
                listKoumoku.add("退職年月日");

            }

            listCsv.add(listKbCompanyCd);
            listCsv.add(listUserId);
            listCsv.add(listNendo);
            if(!TAISHOKU.equals(index)){
                listCsv.add(listTaishoku);
            }
            listCsv.add(listSpace);
            listCsv.add(listKoumoku);

        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV作成");
        }

    }

    /**
     * 日付フォーマット処理(yyyy/MM/dd)
     * @param  date        変換前の日付文字列
     * @return formatDate　変換後の日付文字列
     */
    private String formatYMD(String date){

        DateFormatter daFom = new DateFormatter();
        String formatDate = "";
        if(date == null){
            return formatDate;
        }
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

    /**
     * 妥当性チェック
     */
    public void validate(CsvOutputDto csvOutputDto) {
    }

    /**
     * @return pointHistoryOutputLogicを返す
     */
    public PointHistoryOutputLogic getPointHistoryOutputLogic() {
        return pointHistoryOutputLogic;
    }

    /**
     * @param pointHistoryOutputLogic pointHistoryOutputLogicをセットする
     */
    public void setPointHistoryOutputLogic(PointHistoryOutputLogic pointHistoryOutputLogic) {
        this.pointHistoryOutputLogic = pointHistoryOutputLogic;
    }

    /**
     * nullをブランクに変換する。
     * @param obj
     * @return
     */
    private Object nullConvert(Object obj){
        if(obj == null){
            return "";
        }
        return obj;
    }

    /**
     * 勤続年数をフォーマットする
     * @param kYear 勤続年数(例:27.11)
     * @return フォーマットした勤続年数(例:27年11ヶ月)
     */
    //modify 2018/03/27 xou.zoubun 退職者ポイント付与対応 CSV出力項目「勤務年数」の表示フォーマット begin
//    public String formatKinzokuYear(BigDecimal kYear) {
//        String delim = ".";
//
//        if(kYear == null){
//            return "";
//        }
//        StringTokenizer sTokenizer = new StringTokenizer(kYear.toString(), delim);
//        StringBuffer sbuff = new StringBuffer();
//        sbuff.append(sTokenizer.nextToken());
//        sbuff.append("年");
//        sbuff.append(sTokenizer.nextToken());
//        sbuff.append("ヶ月");
//        return sbuff.toString();
//    }
    public String formatKinzokuYear(BigDecimal kYear) {
        String delim = ".";

        if(kYear == null){
            return "";
        }

        StringTokenizer sTokenizer = new StringTokenizer(kYear.toString(), delim);
        String y = sTokenizer.nextToken(); //年
        String md = sTokenizer.nextToken(); //月日
        String m = md.substring(0, 2); //月

        StringBuffer sbuff = new StringBuffer();
        sbuff.append(y);
        sbuff.append("年");
        sbuff.append(m);
        sbuff.append("ヶ月");
        return sbuff.toString();
    }
    //modify 2018/03/27 xou.zoubun 退職者ポイント付与対応 CSV出力項目「勤務年数」の表示フォーマット end


}
