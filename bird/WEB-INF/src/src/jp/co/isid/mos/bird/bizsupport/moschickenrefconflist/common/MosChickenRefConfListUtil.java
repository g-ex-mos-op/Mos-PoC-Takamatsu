/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.common;

import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListDto;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnReserveInfo;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;


/**
 * @author xkinu
 *
 */
public class MosChickenRefConfListUtil {
    /** DB指定改行文字 */
    private static final String ENTER_WORD_DB = "`";
	/**
	 * 改行文字変換処理
	 * 
	 * @param msg
	 * @param beforEW　指定変換対象改行文字
	 * @param afterEW　指定変換後改行文字
	 * @return
	 */
	public static String changeEnterWord(String msg, String beforEW, String afterEW) {
		String addBrMsg = "";
		if(msg != null && !msg.equals("")){
			int index = 0;
			boolean endFlg = true;
			
			while(endFlg){
				index = msg.indexOf(beforEW);
				if(index == -1) {
					endFlg = false;
				}else{
					String topMsg = msg.substring(0,index);
					String bottomMsg = msg.substring(index+1);
					msg = topMsg + afterEW+ bottomMsg;
				}
			}
			addBrMsg = msg;
		}
			
		return addBrMsg;
	}
	/**
	 * DB指定改行文字を指定変換後改行文字へ変換処理
	 * 
	 * @param msg
	 * @param afterEW　指定変換後改行文字
	 * @return
	 */
	public static String changeDBEnterWord(String msg, String afterEW) {
		return changeEnterWord(msg, ENTER_WORD_DB, afterEW);
	}
    /**
     * 1ページ分のPDFヘッダーTable作成
     * 
     * @param baseFont
     * @param tableWidth
     * @param cellWidthSize
     * @param titleName
     * @param dto
     * @param pageCnt
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static PdfPTable getTablePdfHeader(
    		  BaseFont baseFont
    		, float tableWidth, int[] cellWidthSize
    		, String titleName
    		, MosChickenRefConfListDto dto,int pageCnt)
            throws DocumentException, IOException 
    {
        //      ゴシック16pt
        Font font_g16 = new Font(baseFont, 16, Font.UNDERLINE);
        //ゴシック10pt
        Font font_g11 = new Font(baseFont, 11);
        //条件部分
        PdfPTable headerTable = new PdfPTable(cellWidthSize.length);
        //テーブル幅を設定する。
        headerTable.setTotalWidth(tableWidth);
        headerTable.setLockedWidth(true);
        headerTable.setWidths(cellWidthSize);
        //1行目
        PdfPCell cell0101 = new PdfPCell(new Phrase(titleName,font_g16));
        cell0101.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell0101.setColspan(4);
        cell0101.setBorder(0);
        headerTable.addCell(cell0101);
        PdfPCell cell0102 = new PdfPCell(new Phrase("P." + pageCnt,font_g11));
        cell0102.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell0102.setColspan(3);
        cell0102.setBorder(0);
        headerTable.addCell(cell0102);
        //２行目
        // 発行日フォーマット
        DateFormatter dateFormatter = new DateFormatter(
                DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String createDate = sdf.format(cal.getTime());
        String createYYMMDD = createDate.substring(0,8);
        String createHH = createDate.substring(8,10);
        String createMM = createDate.substring(10,createDate.length());
        createYYMMDD = dateFormatter.format(createYYMMDD.trim(),DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD);
        createDate = "";
        createDate = createYYMMDD +"  "+ createHH +"："+createMM; 
        PdfPCell cell0202 = new PdfPCell(new Phrase("作成日時："+createDate,font_g11));
        cell0202.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell0202.setBorder(0);
        cell0202.setColspan(7);
        headerTable.addCell(cell0202);
        DateFormatter formatter = new DateFormatter();
        //3行目
        PdfPCell cell0301 = new PdfPCell(new Phrase("対象店舗:"+dto.getMiseCd() + dto.getMiseNmKj(),font_g11));
        cell0301.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0301.setColspan(3);
        cell0301.setBorder(0);
        headerTable.addCell(cell0301);
        //YYYY年MM月DD日
        String ymdKanji = formatter.format(dto.getReserveDate(), DateFormatter.PATTERN_KANJI_YMD, DateFormatter.DATE_TYPE_YMD);
        //曜日
        String dayWeekNm = "("+formatter.format(dto.getReserveDate(),DateFormatter.PATTERN_DAY_OF_WEEK,DateFormatter.DATE_TYPE_YMD)+")";
        PdfPCell cell0302 = new PdfPCell(new Phrase(ymdKanji+dayWeekNm,font_g11));
        cell0302.setColspan(1);
        cell0302.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell0302.setBorder(0);
        headerTable.addCell(cell0302);
        PdfPCell cell0303 = new PdfPCell(new Phrase("時間帯："+ dto.getReserveHourFrom() +  " ～ " + dto.getReserveHourTo(),font_g11));
        cell0303.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0303.setColspan(3);
        cell0303.setBorder(0);
        headerTable.addCell(cell0303);
        PdfPCell cell0401 = new PdfPCell(new Phrase(""));
        cell0401.setBorder(0);
        cell0401.setColspan(6);
        headerTable.addCell(cell0401);
        return headerTable;
    }
	/**
	 * 確認表データ表示テーブルのヘッダー取得処理
	 * 
	 * @param baseFont
	 * @param tableWidth
	 * @param cellWidthSize
	 * @param cellValues
	 * @param confListDto
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
    public static PdfPTable getTalbeDataHeader(BaseFont baseFont, float tableWidth, float[] cellWidthSize, String[] cellValues, MosChickenRefConfListDto confListDto) throws DocumentException, IOException {
        //ゴシック10pt
        Font font_g10 = new Font(baseFont, 10);

        /* テーブル作成 */
        // テーブルの全体定義
        PdfPTable tableHeader = new PdfPTable(cellValues.length);
        //テーブル幅を設定する。
        tableHeader.setTotalWidth(tableWidth);
        tableHeader.setLockedWidth(true);
        //テーブルの各セル幅を設定する
        tableHeader.setWidths(cellWidthSize);       
        Color backColor = new Color(204,255,255);
        //項目分のセルを作成する。
        for(int i=0; i<cellValues.length; i++) {
        	PdfPCell cell = new PdfPCell(new Phrase(cellValues[i], font_g10));
        	cell.setBackgroundColor(backColor);
        	cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	cell.setVerticalAlignment(Element.ALIGN_TOP);
	        tableHeader.addCell(cell);
        }
        return tableHeader;
    }
    /**
     * 最大行数取得処理
     * 
     * @return
     */
	public static int getMaxRowCnt(float totalHeight, float minRowSize) {
		float fRowCnt = totalHeight/minRowSize;
		String sRowCnt = String.valueOf(fRowCnt);
		int endIndex = sRowCnt.indexOf('.');
		if(endIndex>0) {
			sRowCnt = sRowCnt.substring(0, endIndex);
		}
    	return Integer.parseInt(sRowCnt);		
	}
    /**
     * 空Entity作成処理
     * 
     * @param entity
     * @return
     */
	public static TrnReserveInfo getEmptyEntity(TrnReserveInfo entity) {
    	TrnReserveInfo newEntity = new TrnReserveInfo();
    	newEntity.setReserveHh(entity.getReserveHh());
    	newEntity.setReserveMm(entity.getReserveMm());
    	newEntity.setReserveDt(entity.getReserveDt());
    	newEntity.setMemo("");
    	newEntity.setPaymentFlg("");
    	newEntity.setPremiumFlg("");
    	return newEntity;
    }

}
