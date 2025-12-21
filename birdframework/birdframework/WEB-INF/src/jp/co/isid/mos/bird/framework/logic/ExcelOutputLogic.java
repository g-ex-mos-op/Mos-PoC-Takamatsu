package jp.co.isid.mos.bird.framework.logic;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;

/**
 * エクセル出力用Logic
 * @author xitamoto
 */
public interface ExcelOutputLogic {

    /**
     * ファイル名取得
     * @param excelOutputDto ExcelOutputDto
     * @return String
     */
    public String getFileName(ExcelOutputDto excelOutputDto);

    /**
     * Workbook読込処理
     * @param excelOutputDto ExcelOutputDto
     * @throws IOException
     * @return HSSFWorkbook
     */
    public Workbook loadWorkbook(ExcelOutputDto excelOutputDto) throws IOException;

    /**
     * 出力データ作成処理
     * @param wb HSSFWorkbook
     * @param excelOutputDto ExcelOutputDto
     * @return HSSFWorkbook
     */
    public Workbook setOutputData(Workbook wb, ExcelOutputDto excelOutputDto);
}