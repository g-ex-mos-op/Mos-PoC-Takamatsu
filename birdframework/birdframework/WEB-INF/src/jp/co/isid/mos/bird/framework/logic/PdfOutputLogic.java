package jp.co.isid.mos.bird.framework.logic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

/**
 * PDFダウンロード用 ロジックInterface
 * @author xnkusama
 */
public interface PdfOutputLogic {

    /** Documentオブジェクト作成処理 */
    public Document createDocument();
    
    /** ファイル名取得 */
    public String getFileName(final PdfOutputDto pdfOutputDto);
    
    /** 出力データ取得処理 */
    public Document setupOutputData(final Document doc, final PdfOutputDto pdfOutputDto)
                        throws DocumentException, IOException;
    
    /** ヘッダ設定処理 */
    public Document setupHeader(final Document doc, final PdfOutputDto pdfOutputDto) throws DocumentException;
    /** フッター設定処理 */
    public Document setupFooter(final Document doc, final PdfOutputDto pdfOutputDto) throws DocumentException;
    
    /** 出力処理 */
    public void returnAction(final ByteArrayOutputStream out, 
                               final HttpServletResponse response, 
                               final PdfOutputDto dto) 
                    throws IOException;
    
    /** アクション戻り値 メイン処理 */
    public String getPageKey(final PdfOutputDto pdfOutputDto);

    /* **  PDF プロパティ ** */
//    /* タイトル設定処理 */
//    public void setTitle();
//    /* 製作者設定処理 */
//    public void setAuthor();
//    /* サブジェクト設定処理 */
//    public void setSubject();
//    /* キーワード設定処理 */
//    public void setKeyword();
    
}
