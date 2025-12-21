/*
 * 作成日: 2005/11/07
 * 
 */
package jp.co.isid.mos.bird.framework.action.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.framework.action.PdfOutputAction;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;
import jp.co.isid.mos.bird.framework.dto.PdfSecurityDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.PdfOutputLogic;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;

/**
 * PDF出力アクションクラス
 * @author xnkusama
 */
public class PdfOutputActionImpl implements PdfOutputAction {

    public static final String createPdf_ACTION_ID = "BFW009A01";
    public static final String downloadPdf_ACTION_ID = "BFW009A02";
    public static final String savePdf_ACTION_ID = "BFW009A03";
    /* エラーメッセージ */
    private static final String ERR_MSG_SYSTEMERR = "PDF作成";
    
    private static final int MODE_DOWNLOAD = 1;
    private static final int MODE_FILESAVE = 2;
    private static final int MODE_GENERAL  = 99;
    
    /* HttpServletResponse */
    private HttpServletResponse _httpServletResponse;
    /* ロジック */
    private PdfOutputLogic _pdfOutputLogic;
    /* DTO */
    private PdfOutputDto _pdfOutputDto;

    /**
     * 全許可定数
     */
    private static final int PERMISSION_ALL_ALLOW 
                                = PdfWriter.AllowAssembly |
                                  PdfWriter.AllowCopy |
                                  PdfWriter.AllowDegradedPrinting |
                                  PdfWriter.AllowFillIn |
                                  PdfWriter.AllowModifyAnnotations |
                                  PdfWriter.AllowModifyContents |
                                  PdfWriter.AllowPrinting |
                                  PdfWriter.AllowScreenReaders;
    
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
     * CSV出力用ロジック設定処理
     * @param downloadLogic
     */
    public void setPdfOutputLogic(final PdfOutputLogic downloadLogic) {
        this._pdfOutputLogic = downloadLogic;
    }
    /**
     * CSV出力用ロジック取得処理
     * @return DownloadLogic
     */
    private PdfOutputLogic getPdfOutputLogic() {
        return this._pdfOutputLogic;
    }

    /**
     * CSV出力用DTO設定処理
     * @param downloadDto
     */
    public void setPdfOutputDto(final PdfOutputDto pdfOutputDto) {
        this._pdfOutputDto = pdfOutputDto;
    }
    /**
     * CSV出力用DTO取得処理
     * @return PdfOutputDto
     */
    private PdfOutputDto getPdfOutputDto() {
        return _pdfOutputDto;
    }

    /**
     * ダウンロード メイン処理
     * @param int mode モード
     * @throws IOException
     * @throws ApplicationException
     */
    private void mainProcess(int mode) throws ApplicationException {
        HttpServletResponse response = getHttpServletResponse();
        // PDFドキュメント
        Document doc;
        // PDFドキュメント用出力ストリーム
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        
        try {
            //Documentオブジェクト作成
            doc = getPdfOutputLogic().createDocument();
            
            PdfWriter pw = PdfWriter.getInstance(doc, byteOut);
            
            //セキュリティ設定
            setSecurity(pw);
            //ヘッダ設定
            getPdfOutputLogic().setupHeader(doc, getPdfOutputDto());
            //フッター設定
            getPdfOutputLogic().setupFooter(doc, getPdfOutputDto());
            //ドキュメント オープン
            doc.open();
            //出力データセット
            getPdfOutputLogic().setupOutputData(doc, getPdfOutputDto());
            //ドキュメント クローズ
            doc.close();
            
            if (mode == MODE_DOWNLOAD) {
                //ダウンロード
                download(byteOut);
            }
            else if (mode == MODE_FILESAVE) {
                //ファイル保存
                saveFile(byteOut);
            }
            else {
                //汎用出力処理
                getPdfOutputLogic().returnAction(byteOut, response, getPdfOutputDto());
            }
        }
        catch (DocumentException dex) {
            //例外処理
            dex.printStackTrace();
            throw new FtlSystemException(ERR_MSG_SYSTEMERR); 
        }
        catch (IOException ioe) {
            //例外処理
            ioe.printStackTrace();
            throw new FtlSystemException(ERR_MSG_SYSTEMERR); 
        }
    }

    /**
     * PDFダウンロードアクション
     * @return
     */
    public String downloadPDF() {
        mainProcess(MODE_DOWNLOAD);
        return getPdfOutputLogic().getPageKey(getPdfOutputDto());
    }
    
    /**
     * PDFファイル保存アクション
     * @return
     */
    public String savePDF() {
        mainProcess(MODE_FILESAVE);
        return getPdfOutputLogic().getPageKey(getPdfOutputDto());
    }

    /**
     * PDFファイル作成処理
     * 処理内容は画面ロジックで定義します
     * @return
     */
    public String createPDF() {
        mainProcess(MODE_GENERAL);
        return getPdfOutputLogic().getPageKey(getPdfOutputDto());
    }
    /*
     * ブラウザへのダウンロード処理
     */
    private void download(final ByteArrayOutputStream out) throws IOException {
        HttpServletResponse response = getHttpServletResponse();
        BufferedOutputStream bos = null;
        String filename = getPdfOutputLogic().getFileName(getPdfOutputDto());
        try {
            //ブラウザへの出力
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
            response.setContentLength(out.size());
            
            bos = new BufferedOutputStream(response.getOutputStream());
            bos.write(out.toByteArray());
//---2006/03/20 add start 保存ファイルにHTMLが表示される不具合対応 
            FacesContext context = FacesContext.getCurrentInstance();
            context.responseComplete();
//---2006/03/20 add end 
        }
        finally {
            if (out != null) {
                out.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }
    
    /*
     * サーバーへのファイル保存処理
     */
    private void saveFile(final ByteArrayOutputStream out) throws IOException {
        String filename = getPdfOutputLogic().getFileName(getPdfOutputDto());
        File file = new File(filename);
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(file);
            fout.write(out.toByteArray());
            fout.flush();
        }
        finally {
            if (fout != null) {
                fout.close();
            }
        }
    }
    
    private void setSecurity(final PdfWriter pw) throws ApplicationException {
        String password = null;
        int permissions = 0;
        if (getPdfOutputDto() instanceof PdfSecurityDto) {
            password = ((PdfSecurityDto) getPdfOutputDto()).getPdfPassword();
            permissions = ((PdfSecurityDto) getPdfOutputDto()).getPdfPermissions();
        }
        else {
            // セキュリティの設定がない場合は、全てを許可
            permissions = PERMISSION_ALL_ALLOW;
        }
        
        try {
            pw.setEncryption(PdfWriter.STRENGTH128BITS, password, password, permissions);
        }
        catch (DocumentException de) {
            throw new FtlSystemException(ERR_MSG_SYSTEMERR);
        }
            
    }
}
