/*
 * 作成日: 2005/11/25
 */
package jp.co.isid.mos.bird.framework.action.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;

import jp.co.isid.mos.bird.framework.action.ExcelOutputAction;
import jp.co.isid.mos.bird.framework.dto.ExcelOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.ExcelOutputLogic;

/**
 * Excel出力
 * @author xkitamoto
 */
public class ExcelOutputActionImpl implements ExcelOutputAction {
    public static final String createExcel_ACTION_ID = "BFW004A01";
    public static final String downloadExcel_ACTION_ID = "BFW004A02";
    public static final String saveExcel_ACTION_ID = "BFW004A03";

    /* エラーメッセージ */
    private static final String ERR_MSG_SYSTEMERR = "Excel出力";

    private static final int MODE_DOWNLOAD = 1;
    private static final int MODE_FILESAVE = 2;
    //private static final int MODE_GENERAL  = 99;

    /* HttpServletResponse */
    private HttpServletResponse _httpServletResponse;
    /* Logic */
    private ExcelOutputLogic excelOutputLogic;
    /* Dto */
    private ExcelOutputDto excelOutputDto;

    /**
     * HttpServletResponse設定処理
     * @param httpServletResponse
     */
    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this._httpServletResponse = httpServletResponse;
    }
    /**
     * HttpServletResponse取得処理
     * @return HttpServletResponse
     */
    public HttpServletResponse getHttpServletResponse() {
        return _httpServletResponse;
    }
    /**
     * @return excelOutputLogic を戻します。
     */
    public ExcelOutputLogic getExcelOutputLogic() {
        return excelOutputLogic;
    }
    /**
     * @param excelOutputLogic excelOutputLogic を設定。
     */
    public void setExcelOutputLogic(ExcelOutputLogic excelOutputLogic) {
        this.excelOutputLogic = excelOutputLogic;
    }
    /**
     * @return excelDto を戻します。
     */
    public ExcelOutputDto getExcelDto() {
        return excelOutputDto;
    }
    /**
     * @param excelDto excelDto を設定。
     */
    public void setExcelOutputDto(ExcelOutputDto excelOutputDto) {
        this.excelOutputDto = excelOutputDto;
    }

    /**
     * ダウンロード メイン処理
     * @param int mode モード
     * @throws ApplicationException
     */
    private void mainProcess(int mode) throws ApplicationException {
        try {
            // ワークブックの作成またはテンプレートとなるExcelファイルの読み込み
            Workbook wb = excelOutputLogic.loadWorkbook(excelOutputDto);
            // 出力データ作成
            excelOutputLogic.setOutputData(wb, excelOutputDto);

            if (mode == MODE_DOWNLOAD) {
                // ブラウザへのダウンロード出力
                download(wb);
            } else if (mode == MODE_FILESAVE) {
                // サーバ保存処理
                save(wb);
            }
        } catch (IOException e) {
            //例外処理
            e.printStackTrace();
            throw new FtlSystemException(ERR_MSG_SYSTEMERR);
        }
    }

    /**
     * ダウンロード処理
     * @return
     */
    public void downloadExcel() {
        mainProcess(MODE_DOWNLOAD);
    }

    /**
     * サーバ保存処理
     * @return
     */
    public void saveExcel() {
        mainProcess(MODE_FILESAVE);
    }

    /**
     * 汎用出力用処理
     * @throws IOException
     */
    public void createExcel() {
        mainProcess(MODE_FILESAVE);
    }

    /**
     * ブラウザ出力処理
     * @throws IOException
     */
    private void download(Workbook wb) throws IOException {
        //クライアントへのレスポンスを設定
        _httpServletResponse.setHeader("Content-Disposition",
                "attachment;filename="
                        + excelOutputLogic.getFileName(excelOutputDto));
        _httpServletResponse.setContentType("application/msexcel");
		OutputStream out = _httpServletResponse.getOutputStream();
		wb.write(out);
		out.flush();
		FacesContext context = FacesContext.getCurrentInstance();
		context.responseComplete();
    }

    /**
     * サーバへエクセルファイル保存処理
     * @throws IOException
     */
    private void save(Workbook wb) throws IOException {
        FileOutputStream out = null;
        try {
            String filePath = excelOutputLogic.getFileName(excelOutputDto);
            out = new FileOutputStream(filePath);
            wb.write(out);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}