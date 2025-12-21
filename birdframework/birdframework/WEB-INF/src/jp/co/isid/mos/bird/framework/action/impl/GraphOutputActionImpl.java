/*
 * 作成日: 2005/11/15
 */
package jp.co.isid.mos.bird.framework.action.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import jp.co.isid.mos.bird.framework.action.GraphOutputAction;
import jp.co.isid.mos.bird.framework.dto.GraphOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.GraphOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 * グラフ出力用アクション
 * @author xnkusama
 */
public class GraphOutputActionImpl implements GraphOutputAction {
    public static final String viewGraph_ACTION_ID = "BFW007A01";
    //テンポラリファイル名 フォーマットパターン
    private static final String TMP_FILE_FORMAT_PTN = "000000000000000";
    //ファイル拡張子JPEG
    private static final String FILE_EXTENSION_JPEG = ".jpg";
    //ファイル拡張子PNG
    private static final String FILE_EXTENSION_PNG = ".png";
    // ファイルセパレーター
    //private String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    /* HttpServletSession */
    private HttpSession _httpSession = null;
    /* ロジック */
    private GraphOutputLogic _graphOutputLogic = null;
    /* DTO */
    private GraphOutputDto _graphOutputDto = null;

    /**
     * CSV出力用ロジック設定処理
     * @param downloadLogic
     */
    public void setGraphOutputLogic(final GraphOutputLogic graphLogic) {
        this._graphOutputLogic = graphLogic;
    }
    /**
     * グラフ出力用ロジック取得処理
     * @return DownloadLogic
     */
    private GraphOutputLogic getGraphOutputLogic() {
        return this._graphOutputLogic;
    }

    /**
     * グラフ出力用DTO設定処理
     * @param downloadDto
     */
    public void setGraphOutputDto(final GraphOutputDto graphOutputDto) {
        this._graphOutputDto = graphOutputDto;
    }
    private GraphOutputDto getGraphOutputDto() {
        return _graphOutputDto;
    }

    /**
     * HttpSession設定処理
     * @param httpSession
     */
    public void setSession(HttpSession httpSession) {
        this._httpSession = httpSession;
    }
    /**
     * HttpSession取得処理
     * @return HttpSession
     */
    public HttpSession getHttpSession() {
        return _httpSession;
    }

    public String viewGraph() throws ApplicationException {
        FileOutputStream fos = null;
        try {
            JFreeChart objCht = getGraphOutputLogic().createOutputData(getGraphOutputDto());
            
            // ファイル情報の取得
            String fileName = getFileName();
            String filePath = getGraphOutputLogic().getFilePath();
            // DTOへファイル情報をセット
            getGraphOutputDto().setGraphUrl(getGraphOutputLogic().getImageUrl(getGraphOutputDto()) + "/" + fileName);
            // 画像ファイル作成
            File file = new File(filePath, fileName);
            fos = new FileOutputStream(file);
            if (GraphOutputLogic.FILE_TYPE_PNG == getGraphOutputLogic().getFileType()) {
                ChartUtilities.writeChartAsPNG(fos, objCht, 640, 480);
            }
            else if (GraphOutputLogic.FILE_TYPE_JPEG == getGraphOutputLogic().getFileType()) {
                ChartUtilities.writeChartAsJPEG(fos, objCht, 640, 480);
            }
            else {
                ChartUtilities.writeChartAsJPEG(fos, objCht, 640, 480);
            }
        }
        catch (IOException ioe) {
            throw new FtlSystemException("グラフ作成処理");
        }
        finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            }
            catch (IOException ioe2) {
                throw new FtlSystemException("グラフ作成処理");
            }
        }
        return null;
            
    }
    
    /*
     * テンポラリファイル名作成
     * タイムスタンプより作成
     */
    private String getFileName() {
        String filename = getGraphOutputLogic().getFileName(getGraphOutputDto());
        if (filename == null) {
            /* システムタイムスタンプを15桁の数値にし、ファイル名とする */
            CodeFormatter formatter = new CodeFormatter(TMP_FILE_FORMAT_PTN.length());
            formatter.setFormatPattern(TMP_FILE_FORMAT_PTN);
            
            // セッションよりユーザー情報を取得
            //TODO 2005/11/22 ユーザー情報の取得キーを変更する
            //MstUser user = (MstUser) getHttpSession().getAttribute("user");
            String userId = getGraphOutputLogic().getUserId(getGraphOutputDto());
            
            //filename = user.getUser_id() + "_" + formatter.format(String.valueOf(System.currentTimeMillis()), true);
            filename = userId + "_" + formatter.format(String.valueOf(System.currentTimeMillis()), true);
            filename += getExtension();
        }
        return filename;
    }
    
    private String getExtension() {
        String extension = "";
        int fileType = getGraphOutputLogic().getFileType(); 
        if (GraphOutputLogic.FILE_TYPE_JPEG == fileType) {
            extension = FILE_EXTENSION_JPEG;
        }
        else if (GraphOutputLogic.FILE_TYPE_PNG == fileType) {
            extension = FILE_EXTENSION_PNG;
        }
        else {
            extension = FILE_EXTENSION_JPEG;
        }
        return extension;
    }
}
