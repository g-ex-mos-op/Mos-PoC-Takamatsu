/*
 * 作成日: 2007/04/16
 */
package jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * SV担当店アップロードエラーCSVロジック
 * @author xnkusama
 *
 */
public class TantouUploadErrorCsvLogicImpl implements CsvOutputLogic  {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA004L06";
    
    private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
    private static final String FILE_SEPARATOR_WIN = "\\";
    
    /**
     * CSVデータの取得
     * @param CsvOutputDto
     * @return　List
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        SvTantouSibuDto dto = (SvTantouSibuDto) csvOutputDto;
        return dto.getListUploadErrorData();
    }

    /* ファイル名取得 */
    public String getFileName(final CsvOutputDto csvOutputDto) {
        SvTantouSibuDto dto = (SvTantouSibuDto) csvOutputDto;
        String originalFileName = dto.getUploadedFile().getName();
        //ファイル区切り文字判別
        String fileSep = FILE_SEPARATOR_WIN;
        //  ファイル名に「\」があるかで判別
        if (originalFileName.indexOf(FILE_SEPARATOR_WIN) < 0) {
            fileSep = FILE_SEPARATOR;
        }
        String filename = originalFileName.substring(originalFileName.lastIndexOf(fileSep) + 1);
        
        return filename.substring(0, filename.toLowerCase().lastIndexOf(".csv")) + "_ERROR.csv";
    }

    /* 事前条件チェック処理 */
    public void validate(final CsvOutputDto csvOutputDto) {
        
    }
}
