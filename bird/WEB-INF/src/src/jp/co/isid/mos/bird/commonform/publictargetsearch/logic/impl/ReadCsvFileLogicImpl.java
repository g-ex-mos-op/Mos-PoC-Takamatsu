/*
 * 作成日: 2006/01/24
 *
 */
package jp.co.isid.mos.bird.commonform.publictargetsearch.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jp.co.isid.mos.bird.commonform.publictargetsearch.dao.CodMiseInfoDao;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchConditionDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.entity.CodMiseInfo;
import jp.co.isid.mos.bird.commonform.publictargetsearch.logic.ReadCsvFileLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.BirdMessageFormatter;
import jp.co.isid.mos.bird.framework.util.CsvInputUtil;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * CSVファイルの読み込みロジック
 * @author xytamura
 */
public class ReadCsvFileLogicImpl implements ReadCsvFileLogic {

    private static final String MESSAGE_INVALID_INPUT_ERR = "E0505";
    private static final String MESSAGE_NOT_EXIST_ERR = "E0103";
    
    public static final String LOGIC_ID = "BCO002L05";
    
    /**
     * 店情報取得Dao
     */
    private CodMiseInfoDao codMiseInfoDao;

    /**
     * CSVファイルの読み込み
     * @param publicTargetSearchDto
     * @return
     * @throws ApplicationException
     */
    public PublicTargetSearchConditionDto execute(
            PublicTargetSearchConditionDto publicTargetSearchCondionDto)
            throws ApplicationException {

        String[][] csv = CsvInputUtil.loadCSV(publicTargetSearchCondionDto
                .getUploadedFile(), CsvInputUtil.OPTION_CSV_NO_DATA_HEAD_ROW,
                true);

        List okMise = new ArrayList();
        List errMise = new ArrayList();

        CodeFormatter formatter = new CodeFormatter(5);
        formatter.setFormatPattern("00000");
        CodeVerifier codeVerifier = new CodeVerifier(5);

        for (int i = 0; i < csv.length; i++) {
            String str = formatter.format(csv[i][0], false);
            if (codeVerifier.validate(str)) {
                okMise.add(str);
            } else {
                CodMiseInfo mise = new CodMiseInfo();

                mise.setMiseCd(str);
                String message = BirdMessageFormatter.getMessage(MESSAGE_INVALID_INPUT_ERR,
                        new String[] { "店コード" });
                mise.setErrMsg(message);
                errMise.add(mise);

            }
        }
        //店情報取得
        List list = getCodMiseInfoDao().getMiseInfo(

        publicTargetSearchCondionDto.getSelectedGyotiKbn(), okMise, publicTargetSearchCondionDto.getSelectedGyotaiCompanyCd());

        Map map = errCheck(list, okMise, errMise);

        publicTargetSearchCondionDto.setListOKMise((List) map.get("ok"));
        publicTargetSearchCondionDto.setListNGMise((List) map.get("ng"));

        return publicTargetSearchCondionDto;
    }

    /**
     * 店情報取得Daoを取得します。
     * @return 店情報取得Dao
     */
    public CodMiseInfoDao getCodMiseInfoDao() {
        return codMiseInfoDao;
    }

    /**
     * 店情報取得Daoを設定します。
     * @param codMiseInfo 店情報取得Dao
     */
    public void setCodMiseInfoDao(CodMiseInfoDao codMiseInfoDao) {
        this.codMiseInfoDao = codMiseInfoDao;
    }

    /**
     * エラーチェックを行います。
     * @param miseInfo 店情報
     * @param csvMise CSVデータ
     * @param errMise エラー店舗
     * @return OK店舗情報、NG店舗情報
     */
    private Map errCheck(List selectResult, List checkOKData, List checkNGData) {

        Map check = new HashMap();
        for (int i = 0; i < selectResult.size(); i++) {
            CodMiseInfo mise = (CodMiseInfo) selectResult.get(i);
            check.put(mise.getMiseCd(), mise);
        }

        for (int i = 0; i < checkOKData.size(); i++) {
            String str = (String) checkOKData.get(i);
            if (!check.containsKey(str)) {
                CodMiseInfo mise = new CodMiseInfo();
                mise.setMiseCd(str);
                String message = BirdMessageFormatter.getMessage(MESSAGE_NOT_EXIST_ERR,
                        new String[] { "その業態の店コード" });
                mise.setErrMsg(message);
                checkNGData.add(mise);
            }
        }

        Map map = new HashMap();
        map.put("ok", selectResult);
        map.put("ng", checkNGData);

        return map;
    }

}