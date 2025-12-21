package jp.co.isid.mos.bird.framework.action.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * CSV出力クラス
 *  パフォーマンスを考慮し、CsvOutputLogicで取得したListをremoveしながら
 *  出力処理を行います。
 * @author xnkusama
 */
public class CsvOutput2ActionImpl implements CsvOutputAction {
    public static final String downloadCsv_ACTION_ID = "BFW010A01";
    /* HttpServletResponse */
    private HttpServletResponse _httpServletResponse = null;

    /* ロジック */
    private CsvOutputLogic _csvOutputLogic = null;

    /* DTO */
    private CsvOutputDto _csvOutputDto = null;

    /**
     * HttpServletResponse設定処理
     *
     * @param httpServletResponse
     */
    public void setResponse(HttpServletResponse httpServletResponse) {
        this._httpServletResponse = httpServletResponse;
    }

    /**
     * HttpServletResponse取得処理
     *
     * @return HttpServletResponse
     */
    private HttpServletResponse getResponse() {
        return _httpServletResponse;
    }

    /**
     * CSV出力用ロジック設定処理
     *
     * @param downloadLogic
     */
    public void setCsvOutputLogic(final CsvOutputLogic downloadLogic) {
        this._csvOutputLogic = downloadLogic;
    }

    /**
     * CSV出力用ロジック取得処理
     *
     * @return DownloadLogic
     */
    private CsvOutputLogic getCsvOutputLogic() {
        return this._csvOutputLogic;
    }

    /**
     * CSV出力用DTO設定処理
     *
     * @param downloadDto
     */
    public void setCsvOutputDto(final CsvOutputDto csvOutputDto) {
        this._csvOutputDto = csvOutputDto;
    }

    private CsvOutputDto getCsvOutputDto() {
        return _csvOutputDto;
    }

    /**
     * ダウンロード メイン処理
     *
     * @throws IOException
     * @throws ApplicationException
     */
    public void downloadCsv() throws IOException, ApplicationException {

        // 事前チェック処理
        getCsvOutputLogic().validate(getCsvOutputDto());
        // データ取得
        List allData = getCsvOutputLogic().getOutputData(getCsvOutputDto());

        PrintWriter out = null;
        String filename = new String(getCsvOutputLogic().getFileName(
                getCsvOutputDto()).getBytes("SJIS"), "ISO8859_1");
        HttpServletResponse response = getResponse();
        try {
            response.setContentType("application/x-csv;charset=Windows-31j");
            response.setHeader("Content-Disposition", "attachment;filename=\""
                    + filename + "\"");

//            out = new PrintWriter(response.getOutputStream());
            out = response.getWriter();

            for (Iterator ite  = allData.iterator(); ite.hasNext();) {
                List row = (List) ite.next();

                for (int j = 0; j < row.size(); j++) {

                    if (isFormula(row.get(j))) {
                        out.print(row.get(j));
                    } else {
                        out.print("\"" + row.get(j) + "\"");
                    }
                    if (j < row.size() - 1) {
                        out.print(",");
                    }
                }
                out.println();
                row = null;
                ite.remove();
            }
            allData = null;

            FacesContext context = FacesContext.getCurrentInstance();
            context.responseComplete();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private boolean isFormula(final Object row) {

        boolean result = false;

        if (row != null && row instanceof String) {
            String rowStr = (String) row;
            if (rowStr.indexOf("=\"") == 0) {
                result = true;
            }
        }
        return result;

    }
}
