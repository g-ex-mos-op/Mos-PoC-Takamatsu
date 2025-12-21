/*
 * 作成日: 2006/03/23
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.MstUserMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.MstUserMiseInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

/**
 * P/LデータCSV一括取込テンプレートダウンロードロジック
 * 
 * @author xyuchida
 */
public class LumpTakeInCsvOutputLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS002L04";

    /**
     * 店情報DAO
     */
    private MstUserMiseInfoDao mstUserMiseInfoDao;

    /**
     * 対象年月バリデータ
     */
    private DateVerifier lumpTakeInPlYmVerifier;

    /**
     * コードバリデータ
     */
    private CodeVerifier lumpTakeInCodeVerifier;

    /**
     * CSVヘッダ
     */
    private static final String[] TEMPLATE_ITEMNAME = new String[] {
            "",
            "",
            "1.売上高",
            "2.売上原価",
            "3.売上総利益",
            "4.給料手当",
            "5.家賃地代",
            "6.水道光熱費",
            "7.ロイヤルティ",
            "8.支払手数料",
            "9.広告費",
            "10.消耗品費",
            "11.法定福利費",
            "12.福利厚生費",
            "13.交際費",
            "14.旅費交通費",
            "15.通信費",
            "16.賃借リース料",
            "17.車両費",
            "18.租税公課",
            "19.保険料",
            "20.運賃",
            "21.修繕費",
            "22.予備欄",
            "23.雑費",
            "24.経費小計",
            "25.償却前利益",
            "26.減価償却費",
            "27.営業外収益",
            "28.営業外費用",
            "29.本社費配賦",
            "30.当月利益",
            "31.売上",
            "32.物販売上",
            "33.売上高合計",
            "34.電気代",
            "35.ガス代",
            "36.水道代",
            "37.その他",
            "38.水道光熱費合計",
            "39b.原材料(当月仕入)",
            "39c.原材料(当月在庫)",
            "39d.原材料(差引売上原価)",
            "40b.野菜(当月仕入)",
            "40c.野菜(当月在庫)",
            "40d.野菜(差引売上原価)",
            "41b.包材(当月仕入)",
            "41c.包材(当月在庫)",
            "41d.包材(差引売上原価)",
            "42b.物販(当月仕入)",
            "42c.物販(当月在庫)",
            "42d.物販(差引売上原価)",
            "43b.計(当月仕入)",
            "43c.計(当月在庫)",
            "43d.計(差引売上原価)",
            "44a.役員報酬(給料)",
            "44b.役員報酬(賞与)",
            "44c.役員報酬(退職金)",
            "44d.役員報酬(計)",
            "45a.給料手当(給料)",
            "45b.給料手当(賞与)",
            "45c.給料手当(退職金)",
            "45d.給料手当(計)",
            "46a.雑給(給料)",
            "46b.雑給(賞与)",
            "46c.雑給(退職金)",
            "46d.雑給(計)",
            "47a.計(給料)",
            "47b.計(賞与)",
            "47c.計(退職金)",
            "47d.計(計)",
            "48b.借入金(当月増加)",
            "48c.借入金(当月減少)",
            "48d.借入金(当月残高)",
            "49b.割賦未払金(当月増加)",
            "49c.割賦未払金(当月減少)",
            "49d.割賦未払金(当月残高)",
            "50b.リース未払金(当月増加)",
            "50c.リース未払金(当月減少)",
            "50d.リース未払金(当月残高)",
            "51b.計(当月増加)",
            "51c.計(当月減少)",
            "51d.計(当月残高)"
    };

    /**
     * 店情報DAOを取得します。
     * @return 店情報DAO
     */
    public MstUserMiseInfoDao getMstUserMiseInfoDao() {
        return mstUserMiseInfoDao;
    }

    /**
     * 店情報DAOを設定します。
     * @param lumpTakeInPlYmVerifier 店情報DAO
     */
    public void setMstUserMiseInfoDao(MstUserMiseInfoDao mstUserMiseInfoDao) {
        this.mstUserMiseInfoDao = mstUserMiseInfoDao;
    }

    /**
     * 対象年月バリデータを取得します。
     * @return 対象年月バリデータ
     */
    public DateVerifier getLumpTakeInPlYmVerifier() {
        return lumpTakeInPlYmVerifier;
    }

    /**
     * 対象年月バリデータを設定します。
     * @param lumpTakeInPlYmVerifier 対象年月バリデータ
     */
    public void setLumpTakeInPlYmVerifier(DateVerifier lumpTakeInPlYmVerifier) {
        this.lumpTakeInPlYmVerifier = lumpTakeInPlYmVerifier;
    }

    /**
     * コードバリデータを取得します。
     * @return コードバリデータ
     */
    public CodeVerifier getLumpTakeInCodeVerifier() {
        return lumpTakeInCodeVerifier;
    }

    /**
     * コードバリデータを設定します。
     * @param lumpTakeInCodeVerifier コードバリデータ
     */
    public void setLumpTakeInCodeVerifier(CodeVerifier lumpTakeInCodeVerifier) {
        this.lumpTakeInCodeVerifier = lumpTakeInCodeVerifier;
    }

    /**
     * ファイル名取得
     * @param csvOutputDto CSV出力用DTO
     * @return ファイル名
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        // テンプレートCSVファイル名
        return "PLSHEET.csv";
    }

    /**
     * 出力データ取得処理
     * @param csvOutputDto CSV出力用DTO
     * @return CSV出力データリスト
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        LumpTakeInDto lumpTakeInDto = (LumpTakeInDto) csvOutputDto;

        List outputDataList = new ArrayList();

        // テンプレート生成
        for (int i = 0; i < TEMPLATE_ITEMNAME.length; i++) {
            // 固定文言設定
            List rowItemList = new ArrayList();

            // 一番左上に対象年月を挿入
            if (i == 0) {
                DateFormatter dateFormatter = new DateFormatter(
						DateFormatter.DATE_TYPE_YM,
						DateFormatter.PATTERN_MONTH);
				String plYm = dateFormatter.format(lumpTakeInDto.getPlYm(),
						DateFormatter.PATTERN_MONTH,
						DateFormatter.DATE_TYPE_YM);
                rowItemList.add(plYm);
            } else {
                rowItemList.add(TEMPLATE_ITEMNAME[i]);
            }
            outputDataList.add(rowItemList);
        }

        List plList = new ArrayList();

        // 本社P/L列設定
        MstUserMiseInfo headPl = new MstUserMiseInfo();
        headPl.setMiseNameKj("本社P/L");
        headPl.setMiseCd(lumpTakeInDto.getOwnerCd());
        plList.add(headPl);

        // オーナー対応店舗取得
        List miseList = getMstUserMiseInfoDao().getMiseCd(
                lumpTakeInDto.getOwnerCd(), lumpTakeInDto.getPlYm());
        plList.addAll(miseList);

        // 店舗数列追加
        for (Iterator it = plList.iterator(); it.hasNext();) {
            MstUserMiseInfo mstUserMiseInfo = (MstUserMiseInfo) it.next();
            // 店名称
            ((List) outputDataList.get(0)).add(mstUserMiseInfo.getMiseNameKj());
            // 店コード
            ((List) outputDataList.get(1)).add(mstUserMiseInfo.getMiseCd());
            // 数値項目初期化
            for (int i = 2, n = outputDataList.size(); i < n; i++) {
                ((List) outputDataList.get(i)).add("");
            }
        }

        return outputDataList;
    }

    /**
     * 事前条件チェック処理
     * @param csvOutputDto CSV出力用DTO
     */
    public void validate(CsvOutputDto csvOutputDto) {

        LumpTakeInDto lumpTakeInDto = (LumpTakeInDto) csvOutputDto;

        // オーナーコード
        String ownerCd = lumpTakeInDto.getOwnerCd();
        if (ownerCd == null || ownerCd.trim().length() == 0) {
            throw new NotNullException("オーナーコード");
        }
        if (!getLumpTakeInCodeVerifier().validate(ownerCd)) {
            throw new InvalidInputException("オーナーコード");
        }

        // 対象年月
        String plYm = lumpTakeInDto.getPlYm();
        if (plYm == null || plYm.trim().length() == 0) {
            throw new NotNullException("対象年月");
        }
        if (!getLumpTakeInPlYmVerifier().validate(plYm)) {
            throw new InvalidInputException("対象年月");
        }
    }
}
