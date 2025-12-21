/*
 * 作成日: 2007/04/16
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * ポイント情報アップロードエラーCSVロジック
 * @author yushuncheng
 *
 */
public class PointInfoUploadErrorCsvLogicImpl implements CsvOutputLogic  {

    /**
     * CSVデータの取得
     * @param CsvOutputDto
     * @return List
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
    	PointInfoTakeinDto dto = (PointInfoTakeinDto) csvOutputDto;
        return dto.getListUploadErrorData();
    }

    /* ファイル名取得 */
    public String getFileName(final CsvOutputDto csvOutputDto) {
    	PointInfoTakeinDto dto = (PointInfoTakeinDto) csvOutputDto;
        String filename = dto.getCsvErrorName();
        if (filename == null || "".equals(filename.trim())) {
            throw new NotNullException("画面情報");
        }

        return filename;
    }

    /* 事前条件チェック処理 */
    public void validate(final CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("画面情報");
        }
    }
}
