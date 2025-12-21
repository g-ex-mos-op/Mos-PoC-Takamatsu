package jp.co.isid.mos.bird.framework.dto;

import com.lowagie.text.pdf.PdfWriter;


/**
 * PDFセキュリティプロパティ用DTO
 * @author xnkusama
 */
public interface PdfSecurityDto {
    /**
     * 全許可定数
     */
    public static final int PERMISSION_ALL_ALLOW 
                                = PdfWriter.AllowAssembly |
                                  PdfWriter.AllowCopy |
                                  PdfWriter.AllowDegradedPrinting |
                                  PdfWriter.AllowFillIn |
                                  PdfWriter.AllowModifyAnnotations |
                                  PdfWriter.AllowModifyContents |
                                  PdfWriter.AllowPrinting |
                                  PdfWriter.AllowScreenReaders;
    /**
     * PDF文書パスワード取得処理
     * @return パスワード
     */
    public String getPdfPassword();
    /**
     * PDF文書パスワード設定処理
     */
    public void setPdfPassword(final String pdfPassword);
    
    /**
     * PDF文書のセキュリティ権限取得処理
     * @return
     */
    public int getPdfPermissions();
    /**
     * PDF文書のセキュリティ権限設定処理
     * @param permissions
     */
    public void setPdfPermissions(final int permissions);
}
