package jp.co.isid.mos.bird.framework.action.impl;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.framework.action.FileDownloadAction;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FileNotFoundException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.DownloadLogic;

/**
 * ダウンロード アクションクラス
 * @author xnkusama
 *
 */
public class FileDownloadActionImpl implements FileDownloadAction {

    public static final String download_ACTION_ID = "BFW005A01";
    /* Content Disposition */
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    /* エラーメッセージ */
    private static final String ERR_MSG_SYSTEMERR = "ダウンロード";

    /* ダウンロード ロジック */
    private DownloadLogic _downloadLogic = null;
    /* HttpResponse */
    private HttpServletResponse _httpServletResponse = null;
    /* HttpServletRequest */
    private HttpServletRequest _httpServletRequest = null;
    public HttpServletRequest get_httpServletRequest() {
		return _httpServletRequest;
	}
	public void set_httpServletRequest(HttpServletRequest _httpServletRequest) {
		this._httpServletRequest = _httpServletRequest;
	}

	/* Download DTO */
    private DownloadDto _downloadDto = null;

    /**
     * ファイルセパレーター
     */
	//private static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    /**
     * ダウンロードロジック設定処理
     * @param downloadLogic
     */
    public void setDownloadLogic(final DownloadLogic downloadLogic) {
        this._downloadLogic = downloadLogic;
    }
    /**
     * ダウンロードロジック取得処理
     * @return DownloadLogic
     */
    private DownloadLogic getDownloadLogic() {
        return this._downloadLogic;
    }

    /**
     * HttpResponse設定処理
     * @param response
     */
    public void setResponse(final HttpServletResponse response) {
        this._httpServletResponse = response;
    }
    private HttpServletResponse getResponse() {
        return _httpServletResponse;
    }

    /**
     * ダウンロード用DTO設定処理
     * @param downloadDto
     */
    public void setDownloadDto(final DownloadDto downloadDto) {
        this._downloadDto = downloadDto;
    }
    private DownloadDto getDownloadDto() {
        return _downloadDto;
    }

    /**
     * ダウンロード メイン処理
     * @throws IOException
     * @throws ApplicationException
     */
    public void download() throws ApplicationException {
        FileInputStream finp = null;
        DataOutputStream dos = null;

        try {
            //出力処理
            File file = new File(getDownloadLogic().getFileFullPath(getDownloadDto()));

            // ファイル存在チェック
            if (!file.exists()) {
                throw new FileNotFoundException();
            }

            //ContentType指定
            getResponse().setContentType(getDownloadLogic().getContentType(getDownloadDto()));
            //ファイル名の指定
            String filename = null;
            if (get_httpServletRequest().getHeader("User-Agent").toUpperCase().indexOf("TRIDENT") > 0 ||
            		get_httpServletRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            	filename = new String(getDownloadLogic().getFileName(getDownloadDto()).replaceAll(" ", "").getBytes("SJIS"), "ISO8859-1");
            } else {
            	filename = new String(getDownloadLogic().getFileName(getDownloadDto()).replaceAll(" ", "").getBytes("UTF-8"), "ISO8859-1");
            }
            getResponse().setHeader(CONTENT_DISPOSITION, "attachment;filename=\"" + filename + "\"");


            finp = new FileInputStream(file);
            dos = new DataOutputStream(getResponse().getOutputStream());

            byte buf[] = new byte[256];
            int len;
            while ((len = finp.read(buf)) != -1) {
                dos.write(buf, 0, len);
            }
            //
            FacesContext context = FacesContext.getCurrentInstance();
            context.responseComplete();
        }
        catch (IOException ioe) {
            //例外処理
            throw new FtlSystemException(ERR_MSG_SYSTEMERR, "", ioe);
        }
        finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (finp != null) {
                    finp.close();
                }
            }
            catch (IOException ioe2) {
                throw new FtlSystemException(ERR_MSG_SYSTEMERR, "", ioe2);
            }
        }
    }
}
