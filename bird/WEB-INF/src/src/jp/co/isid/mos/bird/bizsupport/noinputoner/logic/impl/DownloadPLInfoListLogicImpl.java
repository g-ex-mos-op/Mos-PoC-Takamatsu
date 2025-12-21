package jp.co.isid.mos.bird.bizsupport.noinputoner.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.dao.CtlPLLimitDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dao.TrnPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.noinputoner.dao.TrnPLInfoListDao;
import jp.co.isid.mos.bird.bizsupport.noinputoner.dao.TrnPLMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.noinputoner.dao.TrnUriageInfoDao;
import jp.co.isid.mos.bird.bizsupport.noinputoner.dao.TrnZenPLInfoDao;
import jp.co.isid.mos.bird.bizsupport.noinputoner.dto.NoInputOnerDto;
import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.TrnPLInfoList;
import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.TrnPLMiseInfo;
import jp.co.isid.mos.bird.bizsupport.noinputoner.logic.CheckPLDataNoInputLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

/**
 * 単月全店明細CVSダウンロードファイルを生成
 * @author Aspac
 */
public class DownloadPLInfoListLogicImpl implements CsvOutputLogic {

    /* ロジックID */
    private static final String LOGIC_ID = "BBS004L03";

    /**
     * 単月全店明細CSVダウンロード検索条件フラグ
     * false: 検索条件に支部コード・状況を含めない
     */
    private static final boolean searchStateFlg = false;

    /**
     * P/Lデータチェックロジック
     */
    private CheckPLDataNoInputLogic checkPLDataNoInputLogic;

    /**
     * P/L店舗データ取得DAO
     */
    private TrnPLMiseInfoDao trnPLMiseInfoDao;

    /**
     * P/Lデータリスト取得DAO
     */
    private TrnPLInfoListDao trnPLInfoListDao;

    /**
     * 売上情報DAO
     */
    private TrnUriageInfoDao trnUriageInfoDao;

    /**
     * 前月P/Lデータ取得DAO
     */
    private TrnZenPLInfoDao trnZenPLInfoDao;

    /**
     * P/Lデータ上下限値DAO
     */
    private CtlPLLimitDao ctlPLLimitDao;

    /**
     * P/Lデータ取得DAO
     * ※LUMP(CSV一括取り込み)DAO借用
     */
    private TrnPLInfoDao trnPLInfoDao;

    /**
     * 日付バリデータ
     */
    private DateVerifier lumpTakeInPlYmVerifier;

    /**
     * コードバリデータ
     */
    private CodeVerifier codeVerifier;



    /**
     * P/Lデータチェックロジックを取得します。
     * @return
     */
    public CheckPLDataNoInputLogic getCheckPLDataNoInputLogic() {
        return checkPLDataNoInputLogic;
    }
    /**
     * P/Lデータチェックロジックを設定します。
     * @return
     */
    public void setCheckPLDataNoInputLogic(CheckPLDataNoInputLogic checkPLDataNoInputLogic) {
        this.checkPLDataNoInputLogic = checkPLDataNoInputLogic;
    }


    /**
     * P/Lデータリスト取得DAOを取得します。
     * @return
     */
    public TrnPLInfoListDao getTrnPLInfoListDao() {
        return trnPLInfoListDao;
    }
    /**
     * P/Lデータリスト取得DAOを設定します。
     * @return
     */
    public void setTrnPLInfoListDao(TrnPLInfoListDao trnPLInfoListDao) {
        this.trnPLInfoListDao = trnPLInfoListDao;
    }


    /**
     * P/Lデータ取得DAOを取得します。
     * @return
     */
    public TrnPLInfoDao getTrnPLInfoDao() {
        return trnPLInfoDao;
    }
    /**
     * P/Lデータ取得DAOを設定します。
     * @return
     */
    public void setTrnPLInfoDao(TrnPLInfoDao trnPLInfoDao) {
        this.trnPLInfoDao = trnPLInfoDao;
    }


    /**
     * P/L店舗データ取得DAOを取得します。
     * @return
     */
    public TrnPLMiseInfoDao getTrnPLMiseInfoDao() {
        return trnPLMiseInfoDao;
    }
    /**
     * P/L店舗データ取得DAOを設定します。
     * @return
     */
    public void setTrnPLMiseInfoDao(TrnPLMiseInfoDao trnPLMiseInfoDao) {
        this.trnPLMiseInfoDao = trnPLMiseInfoDao;
    }


    /**
     * P/Lデータ上下限値DAOを取得します。
     * @return P/Lデータ上下限値DAO
     */
    public CtlPLLimitDao getCtlPLLimitDao() {
        return ctlPLLimitDao;
    }
    /**
     * P/Lデータ上下限値DAOを設定します。
     * @param trnPLLimitDao P/Lデータ上下限値DAO
     */
    public void setCtlPLLimitDao(CtlPLLimitDao ctlPLLimitDao) {
        this.ctlPLLimitDao = ctlPLLimitDao;
    }


    /**
     * 売上情報DAODAOを取得します。
     * @return 売上情報DAO
     */
    public TrnUriageInfoDao getTrnUriageInfoDao() {
        return trnUriageInfoDao;
    }
    /**
     * 売上情報DAOを設定します。
     * @param trnUriageInfoDao 売上情報DAO
     */
    public void setTrnUriageInfoDao(
            TrnUriageInfoDao trnUriageInfoDao) {
        this.trnUriageInfoDao = trnUriageInfoDao;
    }


    /**
     * P/LデータDAOを取得します。
     * @return P/LデータDAO
     */
    public TrnZenPLInfoDao getTrnZenPLInfoDao() {
        return trnZenPLInfoDao;
    }
    /**
     * P/LデータDAOを設定します。
     * @param trnZenPLInfoDao P/LデータDAO
     */
    public void setZenTrnPLInfoDao(TrnZenPLInfoDao trnZenPLInfoDao) {
        this.trnZenPLInfoDao = trnZenPLInfoDao;
    }


    /**
     * 日付バリデータを取得します。
     * @return
     */
    public DateVerifier getLumpTakeInPlYmVerifier() {
        return lumpTakeInPlYmVerifier;
    }

    /**
     * 日付バリデータを設定します。
     * @return
     */
    public void setLumpTakeInPlYmVerifier(DateVerifier lumpTakeInPlYmVerifier) {
        this.lumpTakeInPlYmVerifier = lumpTakeInPlYmVerifier;
    }


    /**
     * コードバリデータを取得します。
     * @return
     */
    public CodeVerifier getCodeVerifier() {
        return codeVerifier;
    }
    /**
     * 日付バリデータを設定します。
     * @return
     */
    public void setCodeVerifier(CodeVerifier codeVerifier) {
        this.codeVerifier = codeVerifier;
    }


    /**
     * CSVダウンロードファイル名を取得します。
     * @param String ファイル名
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        return "PLINFOTENPOLIST.csv";
    }


    /**
     * CSVデータレコードを生成する
     * @param CsvOutputDto
     * @return List
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {

        NoInputOnerDto noInputOnerDto = (NoInputOnerDto) csvOutputDto;
        List outputDataList = new ArrayList();

        //**************************************************
        // CSVヘッダ生成
        //**************************************************

        outputDataList.addAll(createCsvHeader(csvOutputDto));


        //**************************************************
        // P/Lデータリスト取得
        //**************************************************

        List trnPLInfoList;
        if(searchStateFlg) {

            // 単月全店明細CSVDLの検索条件に支部コード・状況を含める
            trnPLInfoList = getTrnPLInfoListDao().getPLInfoBySibu(
                                        noInputOnerDto.getCondCompanyCd(),
                                        noInputOnerDto.getCondNengetuInp(),
                                        noInputOnerDto.getCondSibuCdInp(),
                                        noInputOnerDto.isCloseMiseFlg());
            // 入力/未入力の絞込み
            for (Iterator ite = trnPLInfoList.iterator(); ite.hasNext();) {
                TrnPLInfoList trnPLInfo = (TrnPLInfoList) ite.next();
                // 『状況』選択が『エラー』
                if ("1".equals(noInputOnerDto.getCondInputInp())) {
                    if (!"1".equals(trnPLInfo.getErrFlg())) {
                        ite.remove();
                    }
                }
                // 『状況』選択が『未入力』
                else if ("0".equals(noInputOnerDto.getCondInputInp())) {
                    if (!("".equals(trnPLInfo.getErrFlg()) || "1".equals(trnPLInfo.getErrFlg()) || "9".equals(trnPLInfo.getErrFlg()))) {
                        ite.remove();
                    }
                }
            }
        }
        else {
            // 単月全店明細CSVDLの検索条件に支部コード・状況を含めない
            trnPLInfoList = getTrnPLInfoListDao().getPLInfo(
                                            noInputOnerDto.getCondCompanyCd(),
                                            noInputOnerDto.getCondNengetuInp(),
                                            noInputOnerDto.isCloseMiseFlg());
        }

        if (trnPLInfoList == null || trnPLInfoList.size() == 0) {
            throw new NotExistException("出力データ");
        }


        //***************************************************
        // チェックデータをパラメータに追加する
        //***************************************************

        // 店舗リスト生成
        List aryMise = new ArrayList(trnPLInfoList.size());
        for (Iterator it = trnPLInfoList.iterator(); it.hasNext();) {
            TrnPLInfoList trnPLInfo = (TrnPLInfoList) it.next();
            aryMise.add(trnPLInfo.getMiseCd());
        }
        // 店舗チェック用情報取得
        List listMiseInfo = getTrnPLMiseInfoDao().getExistMiseInfo(
                noInputOnerDto.getCondCompanyCd(),
                noInputOnerDto.getCondNengetuInp(),
                noInputOnerDto.isCloseMiseFlg(),
                aryMise);
        // 前月(YYYYMM形式)取得
        String prevPlYm = null;
        try {
            prevPlYm = DateManager.getPrevMonth(noInputOnerDto.getCondNengetuInp(), 1);
        } catch (Exception e) {
            // 無処理
        }

        HashMap hashMap = new HashMap();
        hashMap.put("PL_SALE",getTrnUriageInfoDao().getUriage(noInputOnerDto.getCondNengetuInp(), aryMise)); // 売上げチェック
        hashMap.put("PL_LMIT",getCtlPLLimitDao().selectPlLimitAll()); // 上下限チェック
        hashMap.put("PL_PREV",getTrnZenPLInfoDao().getZenPLInfo(prevPlYm, noInputOnerDto.getCondCompanyCd(), aryMise)); // 前月PLデータチェック


        //****************************************************
        // CSVレコード生成
        //****************************************************
        for (Iterator it = trnPLInfoList.iterator(); it.hasNext();) {
            TrnPLInfoList trnPLInfo = (TrnPLInfoList) it.next();

            hashMap.put("PL_DATA",trnPLInfo);

            // 店コードチェック
            checkPLInfoMise(trnPLInfo, listMiseInfo);

            // チェック
            getCheckPLDataNoInputLogic().execute(hashMap);

            // CSV1レコード生成
            outputDataList.add(createCsvRecord(trnPLInfo));
        }

        return outputDataList;
    }


    /**
     * パラメータチェック
     * @param csvOutputDto
     */
    public void validate(CsvOutputDto csvOutputDto) {

        NoInputOnerDto noInputOnerDto = (NoInputOnerDto) csvOutputDto;

        // 対象年月
        String plYm = noInputOnerDto.getCondNengetuInp();
        if (plYm == null || plYm.trim().length() == 0) {
            throw new NotNullException("対象年月");
        }
        if (!getLumpTakeInPlYmVerifier().validate(plYm)) {
            throw new InvalidInputException("対象年月");
        }

    }

    /**
     * PLデータの店舗コードをチェックする
     * @param trnPLInfo
     * @param list
     */
    private void checkPLInfoMise(TrnPLInfoList trnPLInfo, List listMiseInfo) {

        PlDataErrorInfo plDataErrorInfo = trnPLInfo.getPlDataErrorInfo();

        // 店舗P/Lのみチェックを行う
        if (trnPLInfo.getPlType().equals("1")) {
            String miseCd = trnPLInfo.getMiseCd();
            String compCd = trnPLInfo.getCompanyCd();

            // 必須チェック
            if (miseCd == null) {
                plDataErrorInfo.add(TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_REQUIRED);
            }

            boolean isMiseFlg = true;
            boolean isMiseCloseFlg = true;

            for (Iterator ite = listMiseInfo.iterator(); ite.hasNext();) {
                TrnPLMiseInfo entity = (TrnPLMiseInfo) ite.next();

                if (entity.getMiseCd().equals(miseCd) && entity.getClass().equals(compCd)) {
                    isMiseFlg = false;
                    if (entity.getCloseMiseFlg().equals("1")) {
                        isMiseCloseFlg = false;
                    }
                    ite.remove();
                    break;
                }
            }

            // 店存在チェック
            if (!isMiseFlg) {
                plDataErrorInfo.add(TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_INVALID);
            }
            // 店クローズチェック
            if (!isMiseCloseFlg) {
                plDataErrorInfo.add(TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_CLOSED);
            }
        }
    }


    /**
     * CSVヘッダ情報生成
     * @param csvOutputDto
     * @return
     */
    private List createCsvHeader(CsvOutputDto csvOutputDto) {

        NoInputOnerDto dto = (NoInputOnerDto) csvOutputDto;
        DateFormatter formatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);

        //条件項目ヘッダ
        List listHeaderRow1 = new ArrayList();
        List listHeaderRow2 = new ArrayList();
        List listHeaderRow3 = new ArrayList();
        listHeaderRow1.add("対象年月");
        listHeaderRow1.add(formatter.format(dto.getCondNengetuInp(), true));
        if (dto.isCloseMiseFlg()){
            listHeaderRow1.add("(クローズ店を含む)");
        }
        else {
            listHeaderRow1.add("(クローズ店を含まない)");
        }

        if (searchStateFlg) {

            listHeaderRow2.add("状況");
            if (dto.getCondInputInp() != null && !"".equals(dto.getCondInputInp())) {
                listHeaderRow2.add(dto.getCondInputName());
            }
            else {
                listHeaderRow2.add("全状況");
            }
            listHeaderRow3.add("支部");
            if (dto.getCondSibuCdInp() != null) {
                listHeaderRow3.add(dto.getCondSibuCdInp() + " " + dto.getCondSibuName());
            }
            else {
                listHeaderRow3.add("全支部");
            }
        }
        else {
            // 単月全店明細CSVDLの検索条件に支部コード・状況を含めない
            ;
        }


        // 4行目
        List listHeaderRow4 = new ArrayList();
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("科目不整合");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("内訳不整合");
        listHeaderRow4.add("");
        listHeaderRow4.add("");
        listHeaderRow4.add("");

        // 5行目
        List listHeaderRow5 = new ArrayList();
        listHeaderRow5.add("支部コード");
        listHeaderRow5.add("支部名称");
        listHeaderRow5.add("オーナーコード");
        listHeaderRow5.add("オーナー名称");
        listHeaderRow5.add("店コード");
        listHeaderRow5.add("店名称");
        listHeaderRow5.add("クローズ日");
        listHeaderRow5.add("最終更新日");
        listHeaderRow5.add("登録状況");
        listHeaderRow5.add("月次損益");
        listHeaderRow5.add("内訳");
        listHeaderRow5.add("借入金");
        listHeaderRow5.add("売上高");
        listHeaderRow5.add("売上原価");
        listHeaderRow5.add("水光熱費");
        listHeaderRow5.add("給料手当");

        List csvHeader = new ArrayList();
        csvHeader.add(listHeaderRow1);
        csvHeader.add(listHeaderRow2);
        csvHeader.add(listHeaderRow3);

        //空白行
        csvHeader.add(new ArrayList());

        csvHeader.add(listHeaderRow4);
        csvHeader.add(listHeaderRow5);

        return csvHeader;
    }



    /**
     * CSVデータ生成
     * @param trnPLInfo
     * @return
     */
    private List createCsvRecord(TrnPLInfoList trnPLInfo) {

        List csvRecord = new ArrayList();

        // キー項目
        csvRecord.add(trnPLInfo.getSibuCd());
        csvRecord.add(trnPLInfo.getSibuName());
        csvRecord.add(trnPLInfo.getOnerCd());
        csvRecord.add(trnPLInfo.getOnerNameKj());
        csvRecord.add(trnPLInfo.getMiseCd());
        csvRecord.add(trnPLInfo.getMiseNameKj());
        csvRecord.add(trnPLInfo.getCloseDt());
        csvRecord.add((trnPLInfo.getLastTmsp() == null) ? "" : trnPLInfo.getLastTmsp().toString());

        // 未入力判定
        if (trnPLInfo.getErrFlg()==null || trnPLInfo.getErrFlg().equals("")) {
            csvRecord.add("未入力");
            csvRecord.add("");
            csvRecord.add("");
            csvRecord.add("");
            csvRecord.add("");
            csvRecord.add("");
            csvRecord.add("");
            csvRecord.add("");
        }
        else if (trnPLInfo.getErrFlg().equals("9")) {
            csvRecord.add("All 0データ");
            csvRecord.add("-");
            csvRecord.add("-");
            csvRecord.add("-");
            csvRecord.add("-");
            csvRecord.add("-");
            csvRecord.add("-");
            csvRecord.add("-");
        }
        else {
            // 登録状況
            String status = null;
            if (trnPLInfo.getErrFlg().equals("0")) {
                status = "登録済";
            }
            else {
                status = "エラー";
            }
            csvRecord.add(status);
            // チェック結果
            csvRecord.add(trnPLInfo.getPlDataErrorInfo().getResultSymbolSubjectProfit());
            csvRecord.add(trnPLInfo.getPlDataErrorInfo().getResultSymbolSubjectDetail());
            csvRecord.add(trnPLInfo.getPlDataErrorInfo().getResultSymbolSubjectLoan());
            csvRecord.add(trnPLInfo.getPlDataErrorInfo().getResultSymbolDetailSales());
            csvRecord.add(trnPLInfo.getPlDataErrorInfo().getResultSymbolDetailCost());
            csvRecord.add(trnPLInfo.getPlDataErrorInfo().getResultSymbolDetailWater());
            csvRecord.add(trnPLInfo.getPlDataErrorInfo().getResultSymbolDetailSalary());
        }

        return csvRecord;
    }
}
