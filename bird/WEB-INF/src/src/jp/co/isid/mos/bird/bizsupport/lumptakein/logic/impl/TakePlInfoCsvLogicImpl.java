/*
 * 作成日: 2006/03/13
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.TakePlInfoCsvLogic;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * CSV一括取込ロジック
 * 
 * @author xyuchida
 */
public class TakePlInfoCsvLogicImpl implements TakePlInfoCsvLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L01";

    private static final int MAX_ROW = 83;
    private static final int HEADER_COLUMN = 1;

    /**
     * 店コードフォーマッタ
     */
    private CodeFormatter lumpTakeInMiseCdFormatter;

    /**
     * 日付フォーマッタ
     */
    private DateFormat dateFormatter;

    /**
     * 日付パーサリスト
     */
    private List dateParserList;

    /**
     * 店コードフォーマッタを取得します。
     * @return 店コードフォーマッタ
     */
    public CodeFormatter getLumpTakeInMiseCdFormatter() {
        return lumpTakeInMiseCdFormatter;
    }

    /**
     * 店コードフォーマッタを設定します。
     * @param lumpTakeInMiseCdFormatter 店コードフォーマッタ
     */
    public void setLumpTakeInMiseCdFormatter(
            CodeFormatter lumpTakeInMiseCdFormatter) {
        this.lumpTakeInMiseCdFormatter = lumpTakeInMiseCdFormatter;
    }

    /**
     * 日付フォーマッタを取得します。
     * @return 日付フォーマッタ
     */
    public DateFormat getDateFormatter() {
        return dateFormatter;
    }

    /**
     * 日付フォーマッタを設定します。
     * @param dateFormatter 日付フォーマッタ
     */
    public void setDateFormatter(DateFormat dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    /**
     * 日付パーサリストを取得します。
     * @return 日付パーサリスト
     */
    public List getDateParserList() {
        return dateParserList;
    }

    /**
     * 日付パーサリストを設定します。
     * @param dateParserList 日付パーサリスト
     */
    public void setDateParserList(List dateParserList) {
        this.dateParserList = dateParserList;
    }

    /**
     * P/LデータCSV取込ロジック
     * @param lumpTakeInDto CSV一括取込DTO
     * @return P/Lデータリスト
     */
    public List execute(LumpTakeInDto lumpTakeInDto) {

        // アップロードファイルチェック
        if (lumpTakeInDto.getUploadedFile() == null
                || !lumpTakeInDto.getUploadedFile().getName().toLowerCase().endsWith(".csv")) {
            throw new GenericMessageException("CSV形式で保存されたファイルを選択してください。", "uploadedFile");
        }

        // CSVデータ読込
        String[][] csvArray = null;
        try {
            csvArray = CsvInputUtil.loadCSV(
                    lumpTakeInDto.getUploadedFile().getInputStream(),
                    CsvInputUtil.OPTION_CSV_NO_DATA_HEAD_ROW);
        } catch (FileNotFoundException e) {
            // 後のnullチェックでメッセージを表示する為、無処理
        } catch (IOException e) {
            throw new InvalidInputException("CSVファイル");
        }

        // データ列数チェック
        if (csvArray == null || csvArray.length == 0 || csvArray[0].length <= HEADER_COLUMN) {
            throw new GenericMessageException("P/Lデータがありません。", "uploadedFile");
        }
        // 項目数チェック
        if (csvArray.length > MAX_ROW) {
            throw new GenericMessageException("最終行以降に不要なデータやカンマなどがありますので削除してください。", "uploadedFile");
        }
        if (csvArray.length < MAX_ROW) {
            throw new GenericMessageException("P/Lデータの必要項目数が足りません。", "uploadedFile");
        }

        // 対象年月チェック
        String targetYm = parseDate(csvArray[0][0]);
        if (targetYm == null || !targetYm.equals(lumpTakeInDto.getPlYm())) {
            throw new GenericMessageException("P/Lデータの対象年月が画面で選択されたものと一致していません", "uploadedFile");
        }

        // 列数ループ
        List plInfoList = new ArrayList();
        for (int i = 1; i < csvArray[0].length; i++) {

            // 全ての数値項目が未入力の場合は処理対象外とする
            boolean input = false;
            for (int j = 2; j < MAX_ROW; j++) {
                if (csvArray[j][i] != null && csvArray[j][i].trim().length() != 0) {
                    input = true;
                    break;
                }
            }
            if (!input) {
                continue;                                               
            }

            // P/Lデータエンティティ生成
            TrnPLInfo trnPLInfo = new TrnPLInfo();

            String miseCd = getLumpTakeInMiseCdFormatter().format(csvArray[1][i], false);

            // 本社P/L判定によりP/Lタイプ設定
            trnPLInfo.setPlType((isOwnerCode(miseCd)) ? "0" : "1");

            // キー項目設定
            trnPLInfo.setPlYm(lumpTakeInDto.getPlYm());
            trnPLInfo.setCompanyCd("00");
            trnPLInfo.setOnerCd(lumpTakeInDto.getOwnerCd());
            trnPLInfo.setMiseCd(miseCd);
            trnPLInfo.setMiseNameKj(csvArray[0][i]);

            // 数値項目設定
            trnPLInfo.setUriagedaka(parseNumberItem(csvArray[2][i]));
            trnPLInfo.setUriagegenka(parseNumberItem(csvArray[3][i]));
            trnPLInfo.setUriageSoRieki(parseNumberItem(csvArray[4][i]));
            trnPLInfo.setSalary(parseNumberItem(csvArray[5][i]));
            trnPLInfo.setYachin(parseNumberItem(csvArray[6][i]));
            trnPLInfo.setSuikouHi(parseNumberItem(csvArray[7][i]));
            trnPLInfo.setRoyalty(parseNumberItem(csvArray[8][i]));
            trnPLInfo.setTesuryo(parseNumberItem(csvArray[9][i]));
            trnPLInfo.setKoukoku(parseNumberItem(csvArray[10][i]));
            trnPLInfo.setShoumou(parseNumberItem(csvArray[11][i]));
            trnPLInfo.setHouteiFukuri(parseNumberItem(csvArray[12][i]));
            trnPLInfo.setFukuriKousei(parseNumberItem(csvArray[13][i]));
            trnPLInfo.setKousai(parseNumberItem(csvArray[14][i]));
            trnPLInfo.setRyohi(parseNumberItem(csvArray[15][i]));
            trnPLInfo.setTusin(parseNumberItem(csvArray[16][i]));
            trnPLInfo.setLease(parseNumberItem(csvArray[17][i]));
            trnPLInfo.setSharyo(parseNumberItem(csvArray[18][i]));
            trnPLInfo.setSozei(parseNumberItem(csvArray[19][i]));
            trnPLInfo.setHoken(parseNumberItem(csvArray[20][i]));
            trnPLInfo.setUnchin(parseNumberItem(csvArray[21][i]));
            trnPLInfo.setShuzen(parseNumberItem(csvArray[22][i]));
            // 22.予備欄は常に0とする
            trnPLInfo.setYobi(BigDecimal.valueOf(0));
            trnPLInfo.setZappi(parseNumberItem(csvArray[24][i]));
            trnPLInfo.setKeihiShokei(parseNumberItem(csvArray[25][i]));
            trnPLInfo.setShokyakuRieki(parseNumberItem(csvArray[26][i]));
            trnPLInfo.setGenkaShokyaku(parseNumberItem(csvArray[27][i]));
            trnPLInfo.setEigaiShueki(parseNumberItem(csvArray[28][i]));
            trnPLInfo.setEigaiHiyo(parseNumberItem(csvArray[29][i]));
            trnPLInfo.setHonshahiHai(parseNumberItem(csvArray[30][i]));
            trnPLInfo.setRieki(parseNumberItem(csvArray[31][i]));
            trnPLInfo.setUriage(parseNumberItem(csvArray[32][i]));
            trnPLInfo.setBuppan(parseNumberItem(csvArray[33][i]));
            trnPLInfo.setUriUchiwake(parseNumberItem(csvArray[34][i]));
            trnPLInfo.setElec(parseNumberItem(csvArray[35][i]));
            trnPLInfo.setGas(parseNumberItem(csvArray[36][i]));
            trnPLInfo.setWater(parseNumberItem(csvArray[37][i]));
            trnPLInfo.setSonota(parseNumberItem(csvArray[38][i]));
            trnPLInfo.setSuikouUchiwake(parseNumberItem(csvArray[39][i]));
            trnPLInfo.setGenzairyoShire(parseNumberItem(csvArray[40][i]));
            trnPLInfo.setGenzairyoZaiko(parseNumberItem(csvArray[41][i]));
            trnPLInfo.setGenzairyoKei(parseNumberItem(csvArray[42][i]));
            trnPLInfo.setYasaiShire(parseNumberItem(csvArray[43][i]));
            trnPLInfo.setYasaiZaiko(parseNumberItem(csvArray[44][i]));
            trnPLInfo.setYasaiKei(parseNumberItem(csvArray[45][i]));
            trnPLInfo.setHouzaiShire(parseNumberItem(csvArray[46][i]));
            trnPLInfo.setHouzaiZaiko(parseNumberItem(csvArray[47][i]));
            trnPLInfo.setHouzaiKei(parseNumberItem(csvArray[48][i]));
            trnPLInfo.setBuppanShire(parseNumberItem(csvArray[49][i]));
            trnPLInfo.setBuppanZaiko(parseNumberItem(csvArray[50][i]));
            trnPLInfo.setBuppanKei(parseNumberItem(csvArray[51][i]));
            trnPLInfo.setTouSiireKei(parseNumberItem(csvArray[52][i]));
            trnPLInfo.setTouZaikoKei(parseNumberItem(csvArray[53][i]));
            trnPLInfo.setSashihikiKei(parseNumberItem(csvArray[54][i]));
            trnPLInfo.setYakuinSalary(parseNumberItem(csvArray[55][i]));
            trnPLInfo.setYakuinBonus(parseNumberItem(csvArray[56][i]));
            trnPLInfo.setYakuinRetire(parseNumberItem(csvArray[57][i]));
            trnPLInfo.setYakuinKei(parseNumberItem(csvArray[58][i]));
            trnPLInfo.setSalarySalary(parseNumberItem(csvArray[59][i]));
            trnPLInfo.setSalaryBonus(parseNumberItem(csvArray[60][i]));
            trnPLInfo.setSalaryRetire(parseNumberItem(csvArray[61][i]));
            trnPLInfo.setSalaryKei(parseNumberItem(csvArray[62][i]));
            trnPLInfo.setZakkyuSalary(parseNumberItem(csvArray[63][i]));
            trnPLInfo.setZakkyuBonus(parseNumberItem(csvArray[64][i]));
            trnPLInfo.setZakkyuRetire(parseNumberItem(csvArray[65][i]));
            trnPLInfo.setZakkyuKei(parseNumberItem(csvArray[66][i]));
            trnPLInfo.setKyuryoKei(parseNumberItem(csvArray[67][i]));
            trnPLInfo.setBonusKei(parseNumberItem(csvArray[68][i]));
            trnPLInfo.setRetireKei(parseNumberItem(csvArray[69][i]));
            trnPLInfo.setSalaryUtiKei(parseNumberItem(csvArray[70][i]));
            trnPLInfo.setKashiireUp(parseNumberItem(csvArray[71][i]));
            trnPLInfo.setKashiireDown(parseNumberItem(csvArray[72][i]));
            trnPLInfo.setKashiireZandaka(parseNumberItem(csvArray[73][i]));
            trnPLInfo.setKappuUp(parseNumberItem(csvArray[74][i]));
            trnPLInfo.setKappuDown(parseNumberItem(csvArray[75][i]));
            trnPLInfo.setKappuZandaka(parseNumberItem(csvArray[76][i]));
            trnPLInfo.setLeaseUp(parseNumberItem(csvArray[77][i]));
            trnPLInfo.setLeaseDown(parseNumberItem(csvArray[78][i]));
            trnPLInfo.setLeaseZandaka(parseNumberItem(csvArray[79][i]));
            trnPLInfo.setTouZoukaKei(parseNumberItem(csvArray[80][i]));
            trnPLInfo.setTouGenshoKei(parseNumberItem(csvArray[81][i]));
            trnPLInfo.setTouZandakaKei(parseNumberItem(csvArray[82][i]));

            // 作成者情報設定
            trnPLInfo.setAuthorName(lumpTakeInDto.getAuthorName());
            trnPLInfo.setAuthDt(lumpTakeInDto.getAuthDt());
            trnPLInfo.setAuthPhoneNum(lumpTakeInDto.getAuthPhoneNum());
            trnPLInfo.setAuthOther(lumpTakeInDto.getAuthOther());

            // メモ設定
            trnPLInfo.setMemo("");

            plInfoList.add(trnPLInfo);
        }

        return plInfoList;
    }

    /**
     * 数値項目解析
     * 
     * @param target 対象文字列
     * @return 解析後数値
     */
    protected BigDecimal parseNumberItem(String target) {

        BigDecimal result = null;
        try {
            result = new BigDecimal(target);
        } catch (Exception e) {
            // 数値ではない場合、全て0として扱う
            result = BigDecimal.valueOf(0);
        }

        return result;
    }

    /**
     * オーナーコード判定
     * @param ownerCd オーナーコード
     * @return 判定結果
     */
    protected boolean isOwnerCode(String ownerCd) {
        boolean result = false;
        try {
            result = (Integer.parseInt(ownerCd) >= 30000);
        } catch (NumberFormatException e) {
            // 数値以外はオーナーコードとして扱わない
        }
        return result;
    }

    /**
     * 日付解析
     * 
     * @param date 対象日付文字列
     * @return 解析後日付文字列(YYYYMM形式)
     */
    protected String parseDate(String date) {

        String result = null;

        if (date != null) {
            // yy-MMM形式でゼロサプレスされている場合、前ZEROを補完
            // ex) 6-Sep → 06-Sep
            String target = (date.matches("\\d{1}-.+")) ? "0" + date : date;

            // パーサリストのパーサで日付解析できるまでループ
            for (Iterator it = getDateParserList().iterator(); it.hasNext();) {
                DateFormat parser = (DateFormat) it.next();
                try {
                    // 日付解析
                    Date parseDate = parser.parse(target);
                    // 解析できた場合、フォーマットして処理終了
                    result = getDateFormatter().format(parseDate);
                    break;
                } catch (ParseException e) {
                    // 解析できない場合、次のパーサで解析
                }
            }
        }

        return result;
    }
}
