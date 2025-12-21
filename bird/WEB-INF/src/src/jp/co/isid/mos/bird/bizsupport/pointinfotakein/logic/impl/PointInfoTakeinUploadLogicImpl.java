/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.util.CheckUtils;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.common.PointInfoTakeinCommon;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.common.PointInfoTakeinConstants;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIOfficialPointReadHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIRetirePointReadInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIStaffPointReadHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.PointInfoTakeinUploadLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * ポイント情報取込ロジック
 * @author yushuncheng
 *
 */
public class PointInfoTakeinUploadLogicImpl implements PointInfoTakeinUploadLogic {

	/**
     * ポイント情報アップロード処理
     * @param PointInfoTakeinDto 画面DTO
     * @return boolean true:エラーあり
     */
	public boolean execute(PointInfoTakeinDto dto) {

        //アップロードファイルチェック
        if (dto.getUploadedFile() == null
                || !dto.getUploadedFile().getName().toLowerCase().endsWith(".csv"))
        {
            throw new NotSelectedException("CSVファイル", "uploadedFile", 0);
        }
        if (dto.getUploadedFile().getSize() == 0) {
            throw new InvalidInputException("ファイル形式", "詳しくは取込レイアウトをご確認下さい。");
        }
        /** CSVファイル読み込み */
        String[][] csvArray = null;
        try {
            csvArray = CsvInputUtil.loadCSV(
                    dto.getUploadedFile().getInputStream(),
                    CsvInputUtil.OPTION_DEFAULT);
        } catch (FileNotFoundException e) {
            throw new NotSelectedException("CSVファイル", "");
        } catch (IOException e) {
            throw new FtlSystemException("CSVファイル", "", e);
        }

        /** フォーマットチェック（項目数） **/
        int mode = dto.getProcessMode();

        if(mode == PointInfoTakeinConstants.PROCESS_MODE_KAIGAI_HPRI) {

            /** フォーマットチェック（項目数） **/
            checkCsvFormat(csvArray, PointInfoTakeinConstants.CSV_KAIGAI_HPRI_UPLOAD_COL_NUM);

            /** 各項目のチェック ＋ エラーCSVデータと登録用Entityの作成*/
            checkKaigaiHpriCsvValue(csvArray, dto);

        }else if(mode == PointInfoTakeinConstants.PROCESS_MODE_TSES) {

            /** フォーマットチェック（項目数） **/
            checkCsvFormat(csvArray, PointInfoTakeinConstants.CSV_RTSES_UPLOAD_COL_NUM);

            /** 各項目のチェック ＋ エラーCSVデータと登録用Entityの作成*/
            checkTsesCsvValue(csvArray, dto);

        }else if(mode == PointInfoTakeinConstants.PROCESS_MODE_YPRI) {

            /** フォーマットチェック（項目数） **/
            checkCsvFormat(csvArray, PointInfoTakeinConstants.CSV_YPRI_UPLOAD_COL_NUM);

            /** 各項目のチェック ＋ エラーCSVデータと登録用Entityの作成*/
            checkYpriCsvValue(csvArray, dto);

        }else if(mode == PointInfoTakeinConstants.PROCESS_MODE_HPRI) {

            /** フォーマットチェック（項目数） **/
            checkCsvFormat(csvArray, PointInfoTakeinConstants.CSV_HPRI_UPLOAD_COL_NUM);

            /** 各項目のチェック ＋ エラーCSVデータと登録用Entityの作成*/
            checkHpriCsvValue(csvArray, dto);
        }

		return false;
	}

    /**
     * CSVフォーマットチェック
     * @param csvArray
     */
    private void checkCsvFormat(String[][] csvArray, int colNum) {
        // 選択ファイルが１行の場合、エラー
        //１行目は項目ヘッダである前提なので読み込まないので、csvArrayは空になっている
        if (csvArray.length < 1) {
            throw new InvalidInputException("ファイル形式", "詳しくは取込レイアウトをご確認下さい。");
        }

        // 1行目の列数を確認
        if (csvArray[0].length != colNum) {
            throw new InvalidInputException("ファイル形式", "詳しくは取込レイアウトをご確認下さい。");
        }
    }

    /**
     * 基本ポイントのCSV各項目の妥当性チェック ＋ エラーCSVと登録用Entityも同時に作成
     *
     * @param csvArray
     * @param dto
     */
    private void checkHpriCsvValue(String[][] csvArray, PointInfoTakeinDto dto) {
        boolean isError = false;
        List listErrCsv = new ArrayList();
        HashMap keyMap = new HashMap();
        List<UIStaffPointReadHisInfo> listHpri = new ArrayList<UIStaffPointReadHisInfo>();
        //フォーマッター
        CodeFormatter userIdFormatter = new CodeFormatter(8);
        userIdFormatter.setFormatPattern("00000000");
        CodeFormatter companyCdFormatter = new CodeFormatter(2);
        companyCdFormatter.setFormatPattern("00");
        CodeFormatter rankCdFormatter = new CodeFormatter(3);
        rankCdFormatter.setFormatPattern("000");

        //ヘッダ
        listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));
        //1行ずつ基本ポイントの値をチェック
        for (int i = 0; i < csvArray.length; i++) {
            String errMsg = "";
            List<Object> listRowData = new ArrayList<Object>();
            UIStaffPointReadHisInfo hpriInfo = new UIStaffPointReadHisInfo();
            for (int j = 0; j < PointInfoTakeinConstants.CSV_HPRI_UPLOAD_COL_NUM; j++) {
                listRowData.add(csvArray[i][j] == null ? "" : csvArray[i][j]);
                if (j == PointInfoTakeinConstants.CSV_COL_TORI_SAKUJO_FLG) {
                    String toriSakujoFlg = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(toriSakujoFlg)) {
                    	toriSakujoFlg = toriSakujoFlg.trim();
                    }
                    //レングスチェック
                    if (!"0".equals(toriSakujoFlg) && !"1".equals(toriSakujoFlg)) {
                        errMsg = "取込削除フラグ" + PointInfoTakeinConstants.ERROR_COL_TORI_SAKUJO_FLG;
                        continue;
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	hpriInfo.setToriSakujoFlg(toriSakujoFlg);
                    }
                }else if (j == PointInfoTakeinConstants.CSV_COL_NENDO) {
                    String nendo = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(nendo)) {
                        errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	nendo = nendo.trim();

                        //レングスチェック
                        if (nendo.getBytes().length != 4) {
                            errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }else {
                        	try{
                        		int iNendo = Integer.parseInt(nendo);
                        		if(iNendo < 1900) {
                                    errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                    continue;
                        		}
                        	}catch(NumberFormatException e) {
                                errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                continue;
                        	}
                        }
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	hpriInfo.setNendo(nendo);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_USER_ID) {
                    String userId = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(userId)) {
                        errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	userId = userId.trim();

                        //レングスチェック
                        if (userId.getBytes().length > 8) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(userId)) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                    	userId = userIdFormatter.format(userId, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	hpriInfo.setUserId(userId);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_HPRI_KB_COMPANY_CD) {
                    String companyCd = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(companyCd)) {
                        errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	companyCd = companyCd.trim();

                        //レングスチェック
                        if (companyCd.getBytes().length > 2) {
                            errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(companyCd)) {
                            errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                        companyCd = companyCdFormatter.format(companyCd, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	hpriInfo.setKbCompanyCd(companyCd);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_RANK_CD) {
                    String rankCd = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(rankCd)) {
                        errMsg += "等級" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	rankCd = rankCd.trim();

                        //レングスチェック
                        if (rankCd.getBytes().length > 3) {
                            errMsg += "等級" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(rankCd)) {
                            errMsg += "等級" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                        rankCd = rankCdFormatter.format(rankCd, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	hpriInfo.setRankCd(rankCd);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_KAIGAI_FLG) {
                    String kaigaiFlg = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(kaigaiFlg)) {
                    	kaigaiFlg = kaigaiFlg.trim();

                        //レングスチェック
                        if (!PointInfoTakeinConstants.KAIGAI_FLG_1.equals(kaigaiFlg)) {
                            errMsg += "海外赴任中" + PointInfoTakeinConstants.ERROR_COL_KAIGAI_FLG;
                            continue;
                        }
                    }else {
                    	kaigaiFlg = "";
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	hpriInfo.setKaigaiFlg(kaigaiFlg);
                    }
                }else {
                    String bikou = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(bikou)) {
                    	bikou = bikou.trim();

                        //レングスチェック
                        if (bikou.getBytes().length > 100) {
                            errMsg += "備考" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }
                    }else {
                    	bikou = "";
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	hpriInfo.setBikou(bikou);
                    }
                }
            }

            // 重複データのチェック
            String key = hpriInfo.getNendo() + ";" + hpriInfo.getUserId();
            if(keyMap.containsKey(key)) {
            	errMsg += PointInfoTakeinConstants.NENUSE_ERROR_DATA_EXIST;
            }else {
                keyMap.put(key, key);
            }
            //エラー判定
            if (!"".equals(errMsg)) {
                isError = true;
            }
            //エラー情報セット
            listRowData.add(errMsg);
            //1行分のデータをリストにセット
            //エラー判定
            if (!"".equals(errMsg)) {
            	listErrCsv.add(listRowData);
            }
            listHpri.add(hpriInfo);
        }
        //エラーがあった場合
        if (isError) {
        	listHpri = null;
            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
            dto.setListUploadData(null);
        }
        else {
            dto.setListUploadErrorData(null);
            dto.setUploadError(false);
            dto.setListUploadData(listHpri);
        }
    }

    /**
     * 海外赴任精算のCSV各項目の妥当性チェック ＋ エラーCSVと登録用Entityも同時に作成
     *
     * @param csvArray
     * @param dto
     */
    private void checkKaigaiHpriCsvValue(String[][] csvArray, PointInfoTakeinDto dto) {
        boolean isError = false;
        List listErrCsv = new ArrayList();
        HashMap keyMap = new HashMap();
        List<UIStaffPointReadHisInfo> listKaigaiHpri = new ArrayList<UIStaffPointReadHisInfo>();
        //フォーマッター
        CodeFormatter userIdFormatter = new CodeFormatter(8);
        userIdFormatter.setFormatPattern("00000000");

        //ヘッダ
        listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));
        //1行ずつ基本ポイントの値をチェック
        for (int i = 0; i < csvArray.length; i++) {
            String errMsg = "";
            List<Object> listRowData = new ArrayList<Object>();
            UIStaffPointReadHisInfo kaigaiHpriInfo = new UIStaffPointReadHisInfo();
            for (int j = 0; j < PointInfoTakeinConstants.CSV_KAIGAI_HPRI_UPLOAD_COL_NUM; j++) {
                listRowData.add(csvArray[i][j] == null ? "" : csvArray[i][j]);
                if (j == PointInfoTakeinConstants.CSV_COL_TORI_SAKUJO_FLG) {
                    String toriSakujoFlg = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(toriSakujoFlg)) {
                    	toriSakujoFlg = toriSakujoFlg.trim();
                    }
                    //レングスチェック
                    if (!"0".equals(toriSakujoFlg) && !"1".equals(toriSakujoFlg)) {
                        errMsg = "取込削除フラグ" + PointInfoTakeinConstants.ERROR_COL_TORI_SAKUJO_FLG;
                        continue;
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	kaigaiHpriInfo.setToriSakujoFlg(toriSakujoFlg);
                    }
                }else if (j == PointInfoTakeinConstants.CSV_COL_NENDO) {
                    String nendo = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(nendo)) {
                        errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	nendo = nendo.trim();

                        //レングスチェック
                        if (nendo.getBytes().length != 4) {
                            errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }else {
                        	try{
                        		int iNendo = Integer.parseInt(nendo);
                        		if(iNendo < 1900) {
                                    errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                    continue;
                        		}
                        	}catch(NumberFormatException e) {
                                errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                continue;
                        	}
                        }
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	kaigaiHpriInfo.setNendo(nendo);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_USER_ID) {
                    String userId = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(userId)) {
                        errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	userId = userId.trim();

                        //レングスチェック
                        if (userId.getBytes().length > 8) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(userId)) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                    	userId = userIdFormatter.format(userId, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	kaigaiHpriInfo.setUserId(userId);
                    }
                }
                else {
                    String bikou = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(bikou)) {
                    	bikou = bikou.trim();

                        //レングスチェック
                        if (bikou.getBytes().length > 100) {
                            errMsg += "備考" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }
                    }else {
                    	bikou = "";
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	kaigaiHpriInfo.setBikou(bikou);
                    }
                }
            }

            // 重複データのチェック
            String key = kaigaiHpriInfo.getNendo() + ";" + kaigaiHpriInfo.getUserId();
            if(keyMap.containsKey(key)) {
            	errMsg += PointInfoTakeinConstants.NENUSE_ERROR_DATA_EXIST;
            }else {
                keyMap.put(key, key);
            }
            //エラー判定
            if (!"".equals(errMsg)) {
                isError = true;
            }
            //エラー情報セット
            listRowData.add(errMsg);
            //1行分のデータをリストにセット
            //エラー判定
            if (!"".equals(errMsg)) {
            	listErrCsv.add(listRowData);
            }
            listKaigaiHpri.add(kaigaiHpriInfo);
        }
        //エラーがあった場合
        if (isError) {
        	listKaigaiHpri = null;
            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
            dto.setListUploadData(null);
        }
        else {
            dto.setListUploadErrorData(null);
            dto.setUploadError(false);
            dto.setListUploadData(listKaigaiHpri);
        }
    }

    /**
     * 退職のCSV各項目の妥当性チェック ＋ エラーCSVと登録用Entityも同時に作成
     *
     * @param csvArray
     * @param dto
     */
    private void checkTsesCsvValue(String[][] csvArray, PointInfoTakeinDto dto) {
        boolean isError = false;
        List listErrCsv = new ArrayList();
        HashMap keyMap = new HashMap();
        List<UIRetirePointReadInfo> listRtses = new ArrayList<UIRetirePointReadInfo>();
        //フォーマッター
        CodeFormatter userIdFormatter = new CodeFormatter(8);
        userIdFormatter.setFormatPattern("00000000");
        CodeFormatter companyCdFormatter = new CodeFormatter(2);
        companyCdFormatter.setFormatPattern("00");
        CodeFormatter retireCdFormatter = new CodeFormatter(2);
        retireCdFormatter.setFormatPattern("00");
    	NumericVerifier numericVerifier4_2 = new NumericVerifier(false, 4, 2);

        //ヘッダ
        listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));
        //1行ずつ基本ポイントの値をチェック
        for (int i = 0; i < csvArray.length; i++) {
            String errMsg = "";
            List<Object> listRowData = new ArrayList<Object>();
            UIRetirePointReadInfo rtsesInfo = new UIRetirePointReadInfo();
            for (int j = 0; j < PointInfoTakeinConstants.CSV_RTSES_UPLOAD_COL_NUM; j++) {
                listRowData.add(csvArray[i][j] == null ? "" : csvArray[i][j]);
                if (j == PointInfoTakeinConstants.CSV_COL_TORI_SAKUJO_FLG) {
                    String toriSakujoFlg = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(toriSakujoFlg)) {
                    	toriSakujoFlg = toriSakujoFlg.trim();
                    }
                    //レングスチェック
                    if (!"0".equals(toriSakujoFlg) && !"1".equals(toriSakujoFlg)) {
                        errMsg = "取込削除フラグ" + PointInfoTakeinConstants.ERROR_COL_TORI_SAKUJO_FLG;
                        continue;
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setToriSakujoFlg(toriSakujoFlg);
                    }
                }else if (j == PointInfoTakeinConstants.CSV_COL_NENDO) {
                    String nendo = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(nendo)) {
                        errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	nendo = nendo.trim();

                        //レングスチェック
                        if (nendo.getBytes().length != 4) {
                            errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }else {
                        	try{
                        		int iNendo = Integer.parseInt(nendo);
                        		if(iNendo < 1900) {
                                    errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                    continue;
                        		}
                        	}catch(NumberFormatException e) {
                                errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                continue;
                        	}
                        }
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setNendo(nendo);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_USER_ID) {
                    String userId = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(userId)) {
                        errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	userId = userId.trim();

                        //レングスチェック
                        if (userId.getBytes().length > 8) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(userId)) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                    	userId = userIdFormatter.format(userId, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setUserId(userId);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_TSES_KB_COMPANY_CD) {
                    String companyCd = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(companyCd)) {
                        errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	companyCd = companyCd.trim();

                        //レングスチェック
                        if (companyCd.getBytes().length > 2) {
                            errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(companyCd)) {
                            errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                        companyCd = companyCdFormatter.format(companyCd, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setKbCompanyCd(companyCd);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_TAISHOKU_DT) {
                    String taishokuDt = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(taishokuDt)) {
                        errMsg += "退職年月日" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	taishokuDt = taishokuDt.trim();

                        //レングスチェック
                        if (!CheckUtils.isDate(taishokuDt, CheckUtils.DATE_FORMAT_YYYY_MM_DD)) {
                            errMsg += "退職年月日" + PointInfoTakeinConstants.ERROR_COL_VALUE_MIS;
                            continue;
                        }
                        errMsg = checkNendo(taishokuDt,errMsg,"退職年月日");
                        if(errMsg!="" && errMsg!=null){
                        	continue;
                        }

                        taishokuDt = formatYMD(taishokuDt);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setTaishokuDt(taishokuDt);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_NYUSYA_DT) {
                    String nyusyaDt = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(nyusyaDt)) {
                        errMsg += "入社年月日" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	nyusyaDt = nyusyaDt.trim();

                        //レングスチェック
                        if (!CheckUtils.isDate(nyusyaDt, CheckUtils.DATE_FORMAT_YYYY_MM_DD)) {
                        	errMsg += "入社年月日" + PointInfoTakeinConstants.ERROR_COL_VALUE_MIS;
                            continue;
                        }
                        errMsg = checkNendo(nyusyaDt,errMsg,"入社年月日");
                        if(errMsg!="" && errMsg!=null){
                        	continue;
                        }

                        nyusyaDt  = formatYMD(nyusyaDt);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setNyusyaDt(nyusyaDt);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_RETIRE_CD) {
                    String retireCd = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(retireCd)) {
                        errMsg += "退職事由" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	retireCd = retireCd.trim();

                        //レングスチェック
                        if (retireCd.getBytes().length > 2) {
                            errMsg += "退職事由" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(retireCd)) {
                            errMsg += "退職事由" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                        retireCd = retireCdFormatter.format(retireCd, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setRetireCd(retireCd);
                    }
                }else {
                    String bikou = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(bikou)) {
                    	bikou = bikou.trim();

                        //レングスチェック
                        if (bikou.getBytes().length > 100) {
                            errMsg += "備考" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }
                    }else {
                    	bikou = "";
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	rtsesInfo.setBikou(bikou);
                    }
                }
            }

            // 重複データのチェック
            String key = rtsesInfo.getNendo() + ";" + rtsesInfo.getUserId() + ";" + rtsesInfo.getNyusyaDt();
            if(keyMap.containsKey(key)) {
            	errMsg += PointInfoTakeinConstants.ERROR_DATA_EXIST;
            }else {
                keyMap.put(key, key);
            }
            //エラー判定
            if (!"".equals(errMsg)) {
                isError = true;
            }
            //エラー情報セット
            listRowData.add(errMsg);
            //1行分のデータをリストにセット
            //エラー判定
            if (!"".equals(errMsg)) {
            	listErrCsv.add(listRowData);
            }
            listRtses.add(rtsesInfo);
        }
        //エラーがあった場合
        if (isError) {
        	listRtses = null;
            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
            dto.setListUploadData(null);
        }
        else {
            dto.setListUploadErrorData(null);
            dto.setUploadError(false);
            dto.setListUploadData(listRtses);
        }
    }

    /**
     * 役員情報のCSV各項目の妥当性チェック ＋ エラーCSVと登録用Entityも同時に作成
     *
     * @param csvArray
     * @param dto
     */
    private void checkYpriCsvValue(String[][] csvArray, PointInfoTakeinDto dto) {
        boolean isError = false;
        List listErrCsv = new ArrayList();
        HashMap keyMap = new HashMap();
        List<UIOfficialPointReadHisInfo> listYpri = new ArrayList<UIOfficialPointReadHisInfo>();
        //フォーマッター
        CodeFormatter userIdFormatter = new CodeFormatter(8);
        userIdFormatter.setFormatPattern("00000000");
        CodeFormatter companyCdFormatter = new CodeFormatter(2);
        companyCdFormatter.setFormatPattern("00");

        //ヘッダ
        listErrCsv.add(PointInfoTakeinCommon.makeHeader(dto));
        //1行ずつ基本ポイントの値をチェック
        for (int i = 0; i < csvArray.length; i++) {
            String errMsg = "";
            List<Object> listRowData = new ArrayList<Object>();
            UIOfficialPointReadHisInfo ypriInfo = new UIOfficialPointReadHisInfo();
            for (int j = 0; j < PointInfoTakeinConstants.CSV_YPRI_UPLOAD_COL_NUM; j++) {
                listRowData.add(csvArray[i][j] == null ? "" : csvArray[i][j]);
                if (j == PointInfoTakeinConstants.CSV_COL_TORI_SAKUJO_FLG) {
                    String toriSakujoFlg = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(toriSakujoFlg)) {
                    	toriSakujoFlg = toriSakujoFlg.trim();
                    }
                  //レングスチェック
                    if (!"0".equals(toriSakujoFlg) && !"1".equals(toriSakujoFlg)) {
                        errMsg = "取込削除フラグ" + PointInfoTakeinConstants.ERROR_COL_TORI_SAKUJO_FLG;
                        continue;
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	ypriInfo.setToriSakujoFlg(toriSakujoFlg);
                    }
                }else if (j == PointInfoTakeinConstants.CSV_COL_NENDO) {
                    String nendo = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(nendo)) {
                        errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	nendo = nendo.trim();

                        //レングスチェック
                        if (nendo.getBytes().length != 4) {
                            errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }else {
                        	try{
                        		int iNendo = Integer.parseInt(nendo);
                        		if(iNendo < 1900) {
                                    errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                    continue;
                        		}
                        	}catch(NumberFormatException e) {
                                errMsg += "年度" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                                continue;
                        	}
                        }
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	ypriInfo.setNendo(nendo);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_USER_ID) {
                    String userId = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(userId)) {
                        errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	userId = userId.trim();

                        //レングスチェック
                        if (userId.getBytes().length > 8) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(userId)) {
                            errMsg += "社員番号" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                    	userId = userIdFormatter.format(userId, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	ypriInfo.setUserId(userId);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_YPRI_KB_COMPANY_CD) {
                    String companyCd = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(companyCd)) {
                        errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	companyCd = companyCd.trim();

                        //レングスチェック
                        if (companyCd.getBytes().length > 2) {
                            errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else if (!CheckUtils.isNumeric(companyCd)) {
                            errMsg += "会社コード" + PointInfoTakeinConstants.ERROR_COL_NO_DATA;
                            continue;
                        }
                        companyCd = companyCdFormatter.format(companyCd, true);
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	ypriInfo.setKbCompanyCd(companyCd);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_POINT_SHU_NAME) {
                    String pointShuName = csvArray[i][j];
                    //必須チェック
                    if (CommonUtil.isNull(pointShuName)) {
                        errMsg += "ポイント付与種別" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	pointShuName = pointShuName.trim();

                        //レングスチェック
                        if (pointShuName.getBytes().length > 20) {
                            errMsg += "ポイント付与種別" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	ypriInfo.setPointShuName(pointShuName);
                    }
                }
                else if (j == PointInfoTakeinConstants.CSV_COL_POINT) {
                    String point = csvArray[i][j];
                    int intPoint = 0;
                    //必須チェック
                    if (CommonUtil.isNull(point)) {
                        errMsg += "ポイント" + PointInfoTakeinConstants.ERROR_COL_ISNULL;
                        continue;
                    }else {
                    	point = point.trim();

                        //レングスチェック
                        if (point.getBytes().length > 5) {
                            errMsg += "ポイント" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }else {
                        	try{
                        		intPoint = new Integer(point);
                        		if(intPoint < 0) {
                                    errMsg += "ポイント" + PointInfoTakeinConstants.ERROR_COL_VALUE_MIS;
                                    continue;
                        		}
                        	}catch(Exception e) {
                                errMsg += "ポイント" + PointInfoTakeinConstants.ERROR_COL_VALUE_MIS;
                                continue;
                        	}
                        }
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	ypriInfo.setPoint(new BigDecimal(intPoint));
                    }
                }else {
                    String bikou = csvArray[i][j];
                    //必須チェック
                    if (!CommonUtil.isNull(bikou)) {
                    	bikou = bikou.trim();

                        //レングスチェック
                        if (bikou.getBytes().length > 100) {
                            errMsg += "備考" + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
                            continue;
                        }
                    }else {
                    	bikou = "";
                    }
                    //正常の場合
                    if ("".equals(errMsg)) {
                    	ypriInfo.setBikou(bikou);
                    }
                }
            }

            // 重複データのチェック
            String key = ypriInfo.getNendo() + ";" + ypriInfo.getUserId()+";"+ypriInfo.getPointShuName();
            if(keyMap.containsKey(key)) {
            	errMsg += PointInfoTakeinConstants.POINT_ERROR_DATA_EXIST;
            }else {
                keyMap.put(key, key);
            }
            //エラー判定
            if (!"".equals(errMsg)) {
                isError = true;
            }
            //エラー情報セット
            listRowData.add(errMsg);
            //1行分のデータをリストにセット
            //エラー判定
            if (!"".equals(errMsg)) {
            	listErrCsv.add(listRowData);
            }
            listYpri.add(ypriInfo);
        }
        //エラーがあった場合
        if (isError) {
        	listYpri = null;
            dto.setUploadError(true);
            dto.setListUploadErrorData(listErrCsv);
            dto.setListUploadData(null);
        }
        else {
            dto.setListUploadErrorData(null);
            dto.setUploadError(false);
            dto.setListUploadData(listYpri);
        }
    }

    public String formatYMD(String data){
		String splitData [] =data.split("/");
		if(splitData[1].length()==1){
			splitData[1] = "0"+splitData[1];
		}
		if(splitData[2].length()==1){
			splitData[2]="0"+splitData[2];
		}
		return splitData[0]+splitData[1]+splitData[2];
    }

    public String checkNendo(String data,String errMsg,String colName){
    	String splitData [] =data.split("/");
		if(splitData[0].length()!=4){
			errMsg += colName + PointInfoTakeinConstants.ERROR_COL_LEN_OVER;
		}
		return errMsg;
    }
}
