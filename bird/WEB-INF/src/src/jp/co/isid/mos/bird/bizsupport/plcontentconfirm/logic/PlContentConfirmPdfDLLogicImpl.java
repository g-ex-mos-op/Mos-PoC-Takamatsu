package jp.co.isid.mos.bird.bizsupport.plcontentconfirm.logic;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plcontentconfirm.dto.PlContentConfirmDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.logic.PdfOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.ChangeLineFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.seasar.framework.container.S2Container;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;

/**
 * @author xylee
 */
public class PlContentConfirmPdfDLLogicImpl implements PdfOutputLogic {

    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS003L01";
    
    
	NumericFormatter numericFormatter = new NumericFormatter();
	
	/* Documentオブジェクト設定処理 */
	public Document createDocument() {
		Document doc = new Document(PageSize.A4.rotate());
		doc.setMargins(3f, 3f, 3f, 3f);
		return doc;
	}

	/* ファイル名取得 */
	public String getFileName(final PdfOutputDto pdfOutputDto) {
// change start 2006/08/16 xkhata ファイル名取得修正        
//		return ((PlContentConfirmDto) pdfOutputDto).getFileName();
	    PlContentConfirmDto dto = (PlContentConfirmDto)pdfOutputDto;
        String fileName = "PL" + dto.getTrnPLInfo().getOnerCd() + "_" + dto.getTrnPLInfo().getPlYm() + ".pdf";
        
        return fileName;
// end 
	}

	// SQL実行処理
	private List createData(final PdfOutputDto pdfOutputDto)
			throws ApplicationException {
		S2Container container = null;
		List alldata = null;
		try {


		} finally {
			
		}
		return alldata;
	}

	/**
	 * 出力データ取得処理
	 * 
	 * @param Document
	 *            doc 設定対象のDocumentオブジェクト
	 * @param PdfOutputDto
	 *            pdfOutputDto DTOオブジェクト
	 */
	public Document setupOutputData(Document doc,
			final PdfOutputDto pdfOutputDto) throws DocumentException,
			IOException {
		PlContentConfirmDto plContentConfirmDto = (PlContentConfirmDto) pdfOutputDto;
	    
		doc.add(getHeaderObject(plContentConfirmDto));
		// 空白行
		doc.add(new Phrase());

		doc.add(getMeisaiObject(plContentConfirmDto));

		doc.add(getSecondObject(plContentConfirmDto));

		return doc;
	}

	/**
	 *  
	 */
	public Document setupHeader(final Document doc,
			final PdfOutputDto pdfOutputDto) throws DocumentException {
		return doc;
	}

	/* フッター設定処理 */
	public Document setupFooter(final Document doc,
			final PdfOutputDto pdfOutputDto) throws DocumentException {
		return doc;
	}

	/* ヘッダーTable作成 */
	private Table getHeaderObject(PlContentConfirmDto plContentConfirmDto)
			throws DocumentException, IOException {
		
		TrnPLInfo plInfo = (TrnPLInfo) plContentConfirmDto.getTrnPLInfo();
	    TrnZenPLInfo zenPlInfo = (TrnZenPLInfo) plContentConfirmDto.getTrnZenPLInfo();
		//    	ゴシック16pt
		Font font_g16 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 16, Font.UNDERLINE);
		//    	ゴシック14pt
		Font font_g14 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 14, Font.UNDERLINE);
		//ゴシック12pt
		Font font_g12 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 12);
		//ゴシック10pt
		Font font_g10 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10);
		//ゴシック10pt（下線あり）
		Font font_underline_g10 = new Font(BaseFont.createFont(
				"HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10,
				Font.UNDERLINE);

		//条件部分
		Table headerTable = new Table(20, 1);
		headerTable.setWidth(100);
		headerTable.setBorderWidth(0f);
		headerTable.setBorderWidthTop(0f);
		headerTable.setBorderWidthBottom(0f);
		headerTable.setBorderWidthRight(0f);
		headerTable.setBorderWidthLeft(0f);
		headerTable.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		headerTable.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		headerTable.setPadding(0);
//		headerTable.setPadding(1);
		headerTable.setSpacing(0);
		headerTable.setSpaceInsideCell(0f);
		headerTable.setDefaultCellBorder(0);
		headerTable.setDefaultCellBorderWidth(0f);

        // 発行日フォーマット
        DateFormatter dateFormatter = new DateFormatter(
				DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        String plYm = dateFormatter.format(plInfo.getPlYm(),
				DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM);

		//1行目
		Cell cell0101 = new Cell(new Phrase("月次 P/L ( " + plYm + ")", font_g14));
		cell0101.setColspan(4);
		Cell cell0501 = new Cell(true);
		cell0501.setColspan(8);
		
        Cell cell0901;
		if(plInfo.getPlType().equals("1")){
			cell0901 = new Cell(new Phrase(" 店舗P/L -> ["+plInfo.getMiseCd().trim()+"  "+plInfo.getMiseNameKj().trim()+"]", font_g12));
			cell0901.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell0901.setColspan(8);
        }else{
			cell0901 = new Cell(new Phrase(" 本社P/L -> ["+plInfo.getMiseCd().trim()+"  "+plInfo.getMiseNameKj().trim()+"]", font_g12));
			cell0901.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell0901.setColspan(8);
        }
		headerTable.addCell(cell0101);
		headerTable.addCell(cell0501);
		headerTable.addCell(cell0901);

		return headerTable;
	}

	/* Table作成 */
	private Table getMeisaiObject(PlContentConfirmDto plContentConfirmDto) throws DocumentException, IOException {
		
		TrnPLInfo plInfo = (TrnPLInfo) plContentConfirmDto.getTrnPLInfo();
	    TrnZenPLInfo zenPlInfo = (TrnZenPLInfo) plContentConfirmDto.getTrnZenPLInfo();
		
		//ゴシック11pt(下線あり)
		Font font_underline_11 = new Font(BaseFont.createFont(
				"HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 11,
				Font.UNDERLINE);
		//ゴシック16pt
		Font font_g16 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 16);
		//ゴシック14pt
		Font font_g14 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 14);
		//ゴシック12pt
		Font font_g12 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 12);
		//ゴシック10pt
		Font font_g10 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10);
		//ゴシック10pt（下線あり）
		Font font_underline_g10 = new Font(BaseFont.createFont(
				"HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10,
				Font.UNDERLINE);
		//ゴシック8pt
		Font font_g08 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 8);
		//ゴシック8pt
		Font font_g06 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 6);

		/* テーブル作成 */
		// テーブルの全体定義
		Table shohinTable = new Table(15, 13);
		shohinTable.setWidth(100);
		int shohinTableWidth[] = { 1, 2, 11, 13, 3, 4, 3, 2, 11, 13, 10, 2, 11,
				13, 1 };
		shohinTable.setWidths(shohinTableWidth);
		shohinTable.setBorderWidth(0f);
		shohinTable.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		shohinTable.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		shohinTable.setPadding(0);
//		shohinTable.setPadding(1);
		shohinTable.setSpacing(0);
		shohinTable.setDefaultCellBorder(0);
		shohinTable.setBorderColor(new Color(0, 0, 0));

//		shohinTable.setSpaceInsideCell(0);
		
		//ヘッダ
		Cell hcell01011 = new Cell(true);
		Cell hcell02011 = new Cell(new Phrase("(イ)  月次損益計算書　       (単位 : 円)",
				font_g10));
		hcell02011.setColspan(14);
		hcell02011.setHorizontalAlignment(Element.ALIGN_LEFT);

		shohinTable.addCell(hcell01011);
		shohinTable.addCell(hcell02011);

		//1行
		Cell hcell0101 = new Cell(true);
		Cell hcell0201 = new Cell(new Phrase("No", font_g10));
		Cell hcell0301 = new Cell(new Phrase("科             目", font_g10));
		Cell hcell0401 = new Cell(new Phrase("金　　　　　　 額", font_g10));
		Cell hcell0501 = new Cell(true);
		Cell hcell0601 = new Cell(new Phrase("%", font_g10));
		Cell hcell0701 = new Cell(true);
		Cell hcell0801 = new Cell(new Phrase("No", font_g10));
		Cell hcell0901 = new Cell(new Phrase("科             目", font_g10));
		Cell hcell1001 = new Cell(new Phrase("金　　　　　　 額", font_g10));
		Cell hcell1101 = new Cell(true);
		Cell hcell1201 = new Cell(new Phrase("No", font_g10));
		Cell hcell1301 = new Cell(new Phrase("科             目", font_g10));
		Cell hcell1401 = new Cell(new Phrase("金　　　　　　 額", font_g10));
		Cell hcell1501 = new Cell(true);

		hcell0201.enableBorderSide(15);
		hcell0301.enableBorderSide(15);
		hcell0401.enableBorderSide(15);
		hcell0601.enableBorderSide(15);
		hcell0801.enableBorderSide(15);
		hcell0901.enableBorderSide(15);
		hcell1001.enableBorderSide(15);
		hcell1201.enableBorderSide(15);
		hcell1301.enableBorderSide(15);
		hcell1401.enableBorderSide(15);

		shohinTable.addCell(hcell0101);
		shohinTable.addCell(hcell0201);
		shohinTable.addCell(hcell0301);
		shohinTable.addCell(hcell0401);
		shohinTable.addCell(hcell0501);
		shohinTable.addCell(hcell0601);
		shohinTable.addCell(hcell0701);
		shohinTable.addCell(hcell0801);
		shohinTable.addCell(hcell0901);
		shohinTable.addCell(hcell1001);
		shohinTable.addCell(hcell1101);
		shohinTable.addCell(hcell1201);
		shohinTable.addCell(hcell1301);
		shohinTable.addCell(hcell1401);
		shohinTable.addCell(hcell1501);

		//2行
		Cell hcell0102 = new Cell(true);
		Cell hcell0202 = new Cell(new Phrase("1", font_g10));
		Cell hcell0302 = new Cell(new Phrase("売　 上　 高※", font_g10));
		
		Cell hcell0402 = new Cell(new Phrase(numericFormatter.format(plInfo.getUriagedaka().toString(), "##,###,###,##0"), font_g10));
		hcell0402.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0502 = new Cell(true);
		Cell hcell0602 = new Cell(new Phrase(plContentConfirmDto.getUriagedakaRatio().setScale(1).toString(), font_g10));
		Cell hcell0702 = new Cell(true);
		Cell hcell0802 = new Cell(new Phrase("11", font_g10));
		Cell hcell0902 = new Cell(new Phrase("法  定  福  利  費", font_g10));
		Cell hcell1002 = new Cell(new Phrase(numericFormatter.format(plInfo.getHouteiFukuri().toString(), "##,###,###,##0"), font_g10));
		hcell1002.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1102 = new Cell(true);
		Cell hcell1202 = new Cell(new Phrase("21", font_g10));
		Cell hcell1302 = new Cell(new Phrase("修　　繕　　費", font_g10));
		Cell hcell1402 = new Cell(new Phrase(numericFormatter.format(plInfo.getShuzen().toString(), "##,###,###,##0"), font_g10));
		hcell1402.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1502 = new Cell(true);

		hcell0202.enableBorderSide(15);
		hcell0302.enableBorderSide(15);
		hcell0402.enableBorderSide(15);
		hcell0602.enableBorderSide(15);
		hcell0802.enableBorderSide(15);
		hcell0902.enableBorderSide(15);
		hcell1002.enableBorderSide(15);
		hcell1202.enableBorderSide(15);
		hcell1302.enableBorderSide(15);
		hcell1402.enableBorderSide(15);

		shohinTable.addCell(hcell0102);
		shohinTable.addCell(hcell0202);
		shohinTable.addCell(hcell0302);
		shohinTable.addCell(hcell0402);
		shohinTable.addCell(hcell0502);
		shohinTable.addCell(hcell0602);
		shohinTable.addCell(hcell0702);
		shohinTable.addCell(hcell0802);
		shohinTable.addCell(hcell0902);
		shohinTable.addCell(hcell1002);
		shohinTable.addCell(hcell1102);
		shohinTable.addCell(hcell1202);
		shohinTable.addCell(hcell1302);
		shohinTable.addCell(hcell1402);
		shohinTable.addCell(hcell1502);

		//3行
		Cell hcell0103 = new Cell(true);
		Cell hcell0203 = new Cell(new Phrase("2", font_g10));
		Cell hcell0303 = new Cell(new Phrase("売   上   原   価※", font_g10));
		Cell hcell0403 = new Cell(new Phrase(numericFormatter.format(plInfo.getUriagegenka().toString(), "##,###,###,##0"), font_g10));
		hcell0403.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0503 = new Cell(true);
		Cell hcell0603 = new Cell(new Phrase(plContentConfirmDto.getUriagegenkaRatio().setScale(1).toString(), font_g10));
		Cell hcell0703 = new Cell(true);
		Cell hcell0803 = new Cell(new Phrase("12", font_g10));
		Cell hcell0903 = new Cell(new Phrase("福  利  厚  生  費", font_g10));
		Cell hcell1003 = new Cell(new Phrase(numericFormatter.format(plInfo.getFukuriKousei().toString(), "##,###,###,##0"), font_g10));
		hcell1003.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1103 = new Cell(true);
		Cell hcell1203 = new Cell(new Phrase("22", font_g10));
		Cell hcell1303 = new Cell(new Phrase("(予　備　欄)", font_g10));
		Cell hcell1403 = new Cell(new Phrase(numericFormatter.format(plInfo.getYobi().toString(), "##,###,###,##0"), font_g10));
		hcell1403.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1503 = new Cell(true);

		hcell0203.enableBorderSide(15);
		hcell0303.enableBorderSide(15);
		hcell0403.enableBorderSide(15);
		hcell0603.enableBorderSide(15);
		hcell0803.enableBorderSide(15);
		hcell0903.enableBorderSide(15);
		hcell1003.enableBorderSide(15);
		hcell1203.enableBorderSide(15);
		hcell1303.enableBorderSide(15);
		hcell1403.enableBorderSide(15);

		shohinTable.addCell(hcell0103);
		shohinTable.addCell(hcell0203);
		shohinTable.addCell(hcell0303);
		shohinTable.addCell(hcell0403);
		shohinTable.addCell(hcell0503);
		shohinTable.addCell(hcell0603);
		shohinTable.addCell(hcell0703);
		shohinTable.addCell(hcell0803);
		shohinTable.addCell(hcell0903);
		shohinTable.addCell(hcell1003);
		shohinTable.addCell(hcell1103);
		shohinTable.addCell(hcell1203);
		shohinTable.addCell(hcell1303);
		shohinTable.addCell(hcell1403);
		shohinTable.addCell(hcell1503);

		//4行
		Cell hcell0104 = new Cell(true);
		Cell hcell0204 = new Cell(new Phrase("3", font_g10));
		Cell hcell0304 = new Cell(new Phrase("売  上  総  利  益", font_g10));
		Cell hcell0404 = new Cell(new Phrase(numericFormatter.format(plInfo.getUriageSoRieki().toString(), "##,###,###,##0"), font_g10));
		hcell0404.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0504 = new Cell(true);
		Cell hcell0604 = new Cell(new Phrase(plContentConfirmDto.getUriageSoRiekiRatio().setScale(1).toString(), font_g10));
		Cell hcell0704 = new Cell(true);
		Cell hcell0804 = new Cell(new Phrase("13", font_g10));
		Cell hcell0904 = new Cell(new Phrase("交　　際　　費", font_g10));
		Cell hcell1004 = new Cell(new Phrase(numericFormatter.format(plInfo.getKousai().toString(), "##,###,###,##0"), font_g10));
		hcell1004.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1104 = new Cell(true);
		Cell hcell1204 = new Cell(new Phrase("23", font_g10));
		Cell hcell1304 = new Cell(new Phrase("雑　　　　　費", font_g10));
		Cell hcell1404 = new Cell(new Phrase(numericFormatter.format(plInfo.getZappi().toString(), "##,###,###,##0"), font_g10));
		hcell1404.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1504 = new Cell(true);

		hcell0204.enableBorderSide(15);
		hcell0304.enableBorderSide(15);
		hcell0404.enableBorderSide(15);
		hcell0604.enableBorderSide(15);
		hcell0804.enableBorderSide(15);
		hcell0904.enableBorderSide(15);
		hcell1004.enableBorderSide(15);
		hcell1204.enableBorderSide(15);
		hcell1304.enableBorderSide(15);
		hcell1404.enableBorderSide(15);

		shohinTable.addCell(hcell0104);
		shohinTable.addCell(hcell0204);
		shohinTable.addCell(hcell0304);
		shohinTable.addCell(hcell0404);
		shohinTable.addCell(hcell0504);
		shohinTable.addCell(hcell0604);
		shohinTable.addCell(hcell0704);
		shohinTable.addCell(hcell0804);
		shohinTable.addCell(hcell0904);
		shohinTable.addCell(hcell1004);
		shohinTable.addCell(hcell1104);
		shohinTable.addCell(hcell1204);
		shohinTable.addCell(hcell1304);
		shohinTable.addCell(hcell1404);
		shohinTable.addCell(hcell1504);

		//5行
		Cell hcell0105 = new Cell(true);
		Cell hcell0205 = new Cell(new Phrase("4", font_g10));
		Cell hcell0305 = new Cell(new Phrase("給   料    手   当※", font_g10));
		Cell hcell0405 = new Cell(new Phrase(numericFormatter.format(plInfo.getSalary().toString(), "##,###,###,##0"), font_g10));
		hcell0405.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0505 = new Cell(true);
		Cell hcell0605 = new Cell(true);
		Cell hcell0705 = new Cell(true);
		Cell hcell0805 = new Cell(new Phrase("14", font_g10));
		Cell hcell0905 = new Cell(new Phrase("旅  費  交  通  費", font_g10));
		Cell hcell1005 = new Cell(new Phrase(numericFormatter.format(plInfo.getRyohi().toString(), "##,###,###,##0"), font_g10));
		hcell1005.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1105 = new Cell(true);
		Cell hcell1205 = new Cell(new Phrase("24", font_g10));
		Cell hcell1305 = new Cell(new Phrase("経   費    小   計", font_g10));
		Cell hcell1405 = new Cell(new Phrase(numericFormatter.format(plInfo.getKeihiShokei().toString(), "##,###,###,##0"), font_g10));
		hcell1405.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1505 = new Cell(true);

		hcell0205.enableBorderSide(15);
		hcell0305.enableBorderSide(15);
		hcell0405.enableBorderSide(15);
		hcell0805.enableBorderSide(15);
		hcell0905.enableBorderSide(15);
		hcell1005.enableBorderSide(15);
		hcell1205.enableBorderSide(15);
		hcell1305.enableBorderSide(15);
		hcell1405.enableBorderSide(15);

		shohinTable.addCell(hcell0105);
		shohinTable.addCell(hcell0205);
		shohinTable.addCell(hcell0305);
		shohinTable.addCell(hcell0405);
		shohinTable.addCell(hcell0505);
		shohinTable.addCell(hcell0605);
		shohinTable.addCell(hcell0705);
		shohinTable.addCell(hcell0805);
		shohinTable.addCell(hcell0905);
		shohinTable.addCell(hcell1005);
		shohinTable.addCell(hcell1105);
		shohinTable.addCell(hcell1205);
		shohinTable.addCell(hcell1305);
		shohinTable.addCell(hcell1405);
		shohinTable.addCell(hcell1505);

		//6行/*
		Cell hcell0106 = new Cell(true);
		Cell hcell0206 = new Cell(new Phrase("5", font_g10));
		Cell hcell0306 = new Cell(new Phrase("家   賃    地   代", font_g10));
		Cell hcell0406 = new Cell(new Phrase(numericFormatter.format(plInfo.getYachin().toString(), "##,###,###,##0"), font_g10));
		hcell0406.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0506 = new Cell(true);
		Cell hcell0606 = new Cell(true);
		Cell hcell0706 = new Cell(true);
		Cell hcell0806 = new Cell(new Phrase("15", font_g10));
		Cell hcell0906 = new Cell(new Phrase("通　　信　　費", font_g10));
		Cell hcell1006 = new Cell(new Phrase(numericFormatter.format(plInfo.getTusin().toString(), "##,###,###,##0"), font_g10));
		hcell1006.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1106 = new Cell(true);
		Cell hcell1206 = new Cell(new Phrase("25", font_g10));
		Cell hcell1306 = new Cell(new Phrase("償  却  前  利  益", font_g10));
		Cell hcell1406 = new Cell(new Phrase(numericFormatter.format(plInfo.getShokyakuRieki().toString(), "##,###,###,##0"), font_g10));
		hcell1406.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1506 = new Cell(true);

		hcell0206.enableBorderSide(15);
		hcell0306.enableBorderSide(15);
		hcell0406.enableBorderSide(15);
		hcell0806.enableBorderSide(15);
		hcell0906.enableBorderSide(15);
		hcell1006.enableBorderSide(15);
		hcell1206.enableBorderSide(15);
		hcell1306.enableBorderSide(15);
		hcell1406.enableBorderSide(15);

		shohinTable.addCell(hcell0106);
		shohinTable.addCell(hcell0206);
		shohinTable.addCell(hcell0306);
		shohinTable.addCell(hcell0406);
		shohinTable.addCell(hcell0506);
		shohinTable.addCell(hcell0606);
		shohinTable.addCell(hcell0706);
		shohinTable.addCell(hcell0806);
		shohinTable.addCell(hcell0906);
		shohinTable.addCell(hcell1006);
		shohinTable.addCell(hcell1106);
		shohinTable.addCell(hcell1206);
		shohinTable.addCell(hcell1306);
		shohinTable.addCell(hcell1406);
		shohinTable.addCell(hcell1506);

		//7行
		Cell hcell0107 = new Cell(true);
		Cell hcell0207 = new Cell(new Phrase("6", font_g10));
		Cell hcell0307 = new Cell(new Phrase("水 道  光 熱 費※", font_g10));
		Cell hcell0407 = new Cell(new Phrase(numericFormatter.format(plInfo.getSuikouHi().toString(), "##,###,###,##0"), font_g10));
		hcell0407.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0507 = new Cell(true);
		Cell hcell0607 = new Cell(new Phrase("⇒", font_g16));
		hcell0607.setRowspan(2);
		Cell hcell0707 = new Cell(true);
		Cell hcell0807 = new Cell(new Phrase("16", font_g10));
		Cell hcell0907 = new Cell(new Phrase("賃 借 リース 料", font_g10));
		Cell hcell1007 = new Cell(new Phrase(numericFormatter.format(plInfo.getLease().toString(), "##,###,###,##0"), font_g10));
		hcell1007.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1107 = new Cell(new Phrase("⇒", font_g16));
		hcell1107.setRowspan(2);
		Cell hcell1207 = new Cell(new Phrase("26", font_g10));
		Cell hcell1307 = new Cell(new Phrase("減  価  償  却  費", font_g10));
		Cell hcell1407 = new Cell(new Phrase(numericFormatter.format(plInfo.getGenkaShokyaku().toString(), "##,###,###,##0"), font_g10));
		hcell1407.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1507 = new Cell(true);

		hcell0207.enableBorderSide(15);
		hcell0307.enableBorderSide(15);
		hcell0407.enableBorderSide(15);
		hcell0807.enableBorderSide(15);
		hcell0907.enableBorderSide(15);
		hcell1007.enableBorderSide(15);
		hcell1207.enableBorderSide(15);
		hcell1307.enableBorderSide(15);
		hcell1407.enableBorderSide(15);

		shohinTable.addCell(hcell0107);
		shohinTable.addCell(hcell0207);
		shohinTable.addCell(hcell0307);
		shohinTable.addCell(hcell0407);
		shohinTable.addCell(hcell0507);
		shohinTable.addCell(hcell0607);
		shohinTable.addCell(hcell0707);
		shohinTable.addCell(hcell0807);
		shohinTable.addCell(hcell0907);
		shohinTable.addCell(hcell1007);
		shohinTable.addCell(hcell1107);
		shohinTable.addCell(hcell1207);
		shohinTable.addCell(hcell1307);
		shohinTable.addCell(hcell1407);
		shohinTable.addCell(hcell1507);

		//8行
		Cell hcell0108 = new Cell(true);
		Cell hcell0208 = new Cell(new Phrase("7", font_g10));
		Cell hcell0308 = new Cell(new Phrase("ロ イ ヤ ル ティ", font_g10));
		Cell hcell0408 = new Cell(new Phrase(numericFormatter.format(plInfo.getRoyalty().toString(), "##,###,###,##0"), font_g10));
		hcell0408.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0508 = new Cell(true);
//		Cell hcell0608 = new Cell(true);
		Cell hcell0708 = new Cell(true);
		Cell hcell0808 = new Cell(new Phrase("17", font_g10));
		Cell hcell0908 = new Cell(new Phrase("車　　両　　費", font_g10));
		Cell hcell1008 = new Cell(new Phrase(numericFormatter.format(plInfo.getSharyo().toString(), "##,###,###,##0"), font_g10));
		hcell1008.setHorizontalAlignment(Element.ALIGN_RIGHT);
//		Cell hcell1108 = new Cell(true);
		Cell hcell1208 = new Cell(new Phrase("27", font_g10));
		Cell hcell1308 = new Cell(new Phrase("営  業  外  収  益", font_g10));
		Cell hcell1408 = new Cell(new Phrase(numericFormatter.format(plInfo.getEigaiShueki().toString(), "##,###,###,##0"), font_g10));
		hcell1408.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1508 = new Cell(true);

		hcell0208.enableBorderSide(15);
		hcell0308.enableBorderSide(15);
		hcell0408.enableBorderSide(15);
		hcell0808.enableBorderSide(15);
		hcell0908.enableBorderSide(15);
		hcell1008.enableBorderSide(15);
		hcell1208.enableBorderSide(15);
		hcell1308.enableBorderSide(15);
		hcell1408.enableBorderSide(15);

		shohinTable.addCell(hcell0108);
		shohinTable.addCell(hcell0208);
		shohinTable.addCell(hcell0308);
		shohinTable.addCell(hcell0408);
		shohinTable.addCell(hcell0508);
//		shohinTable.addCell(hcell0608);
		shohinTable.addCell(hcell0708);
		shohinTable.addCell(hcell0808);
		shohinTable.addCell(hcell0908);
		shohinTable.addCell(hcell1008);
//		shohinTable.addCell(hcell1108);
		shohinTable.addCell(hcell1208);
		shohinTable.addCell(hcell1308);
		shohinTable.addCell(hcell1408);
		shohinTable.addCell(hcell1508);

		//9行
		Cell hcell0109 = new Cell(true);
		Cell hcell0209 = new Cell(new Phrase("8", font_g10));
		Cell hcell0309 = new Cell(new Phrase("支  払  手  数  料", font_g10));
		Cell hcell0409 = new Cell(new Phrase(numericFormatter.format(plInfo.getTesuryo().toString(), "##,###,###,##0"), font_g10));
		hcell0409.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0509 = new Cell(true);
		Cell hcell0609 = new Cell(true);
		Cell hcell0709 = new Cell(true);
		Cell hcell0809 = new Cell(new Phrase("18", font_g10));
		Cell hcell0909 = new Cell(new Phrase("租   税    公   課", font_g10));
		Cell hcell1009 = new Cell(new Phrase(numericFormatter.format(plInfo.getSozei().toString(), "##,###,###,##0"), font_g10));
		hcell1009.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1109 = new Cell(true);
		Cell hcell1209 = new Cell(new Phrase("28", font_g10));
		Cell hcell1309 = new Cell(new Phrase("営  業  外  費  用", font_g10));
		Cell hcell1409 = new Cell(new Phrase(numericFormatter.format(plInfo.getEigaiHiyo().toString(), "##,###,###,##0"), font_g10));
		hcell1409.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1509 = new Cell(true);

		hcell0209.enableBorderSide(15);
		hcell0309.enableBorderSide(15);
		hcell0409.enableBorderSide(15);
		hcell0809.enableBorderSide(15);
		hcell0909.enableBorderSide(15);
		hcell1009.enableBorderSide(15);
		hcell1209.enableBorderSide(15);
		hcell1309.enableBorderSide(15);
		hcell1409.enableBorderSide(15);

		shohinTable.addCell(hcell0109);
		shohinTable.addCell(hcell0209);
		shohinTable.addCell(hcell0309);
		shohinTable.addCell(hcell0409);
		shohinTable.addCell(hcell0509);
		shohinTable.addCell(hcell0609);
		shohinTable.addCell(hcell0709);
		shohinTable.addCell(hcell0809);
		shohinTable.addCell(hcell0909);
		shohinTable.addCell(hcell1009);
		shohinTable.addCell(hcell1109);
		shohinTable.addCell(hcell1209);
		shohinTable.addCell(hcell1309);
		shohinTable.addCell(hcell1409);
		shohinTable.addCell(hcell1509);

		//10行
		Cell hcell0110 = new Cell(true);
		Cell hcell0210 = new Cell(new Phrase("9", font_g10));
		Cell hcell0310 = new Cell(new Phrase("広　　告　　費", font_g10));
		Cell hcell0410 = new Cell(new Phrase(numericFormatter.format(plInfo.getKoukoku().toString(), "##,###,###,##0"), font_g10));
		hcell0410.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0510 = new Cell(true);
		Cell hcell0610 = new Cell(true);
		Cell hcell0710 = new Cell(true);
		Cell hcell0810 = new Cell(new Phrase("19", font_g10));
		Cell hcell0910 = new Cell(new Phrase("保　　険　　料", font_g10));
		Cell hcell1010 = new Cell(new Phrase(numericFormatter.format(plInfo.getHoken().toString(), "##,###,###,##0"), font_g10));
		hcell1010.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1110 = new Cell(true);
		Cell hcell1210 = new Cell(new Phrase("29", font_g10));
		Cell hcell1310 = new Cell(new Phrase("本  社  費  配  賦", font_g10));
		Cell hcell1410 = new Cell(new Phrase(numericFormatter.format(plInfo.getHonshahiHai().toString(), "##,###,###,##0"), font_g10));
		hcell1410.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1510 = new Cell(true);

		hcell0210.enableBorderSide(15);
		hcell0310.enableBorderSide(15);
		hcell0410.enableBorderSide(15);
		hcell0810.enableBorderSide(15);
		hcell0910.enableBorderSide(15);
		hcell1010.enableBorderSide(15);
		hcell1210.enableBorderSide(15);
		hcell1310.enableBorderSide(15);
		hcell1410.enableBorderSide(15);

		shohinTable.addCell(hcell0110);
		shohinTable.addCell(hcell0210);
		shohinTable.addCell(hcell0310);
		shohinTable.addCell(hcell0410);
		shohinTable.addCell(hcell0510);
		shohinTable.addCell(hcell0610);
		shohinTable.addCell(hcell0710);
		shohinTable.addCell(hcell0810);
		shohinTable.addCell(hcell0910);
		shohinTable.addCell(hcell1010);
		shohinTable.addCell(hcell1110);
		shohinTable.addCell(hcell1210);
		shohinTable.addCell(hcell1310);
		shohinTable.addCell(hcell1410);
		shohinTable.addCell(hcell1510);

		//11行
		Cell hcell0111 = new Cell(true);
		Cell hcell0211 = new Cell(new Phrase("10", font_g10));
		Cell hcell0311 = new Cell(new Phrase("消   耗    品   費", font_g10));
		Cell hcell0411 = new Cell(new Phrase(numericFormatter.format(plInfo.getShoumou().toString(), "##,###,###,##0"), font_g10));
		hcell0411.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0511 = new Cell(true);
		Cell hcell0611 = new Cell(true);
		Cell hcell0711 = new Cell(true);
		Cell hcell0811 = new Cell(new Phrase("20", font_g10));
		Cell hcell0911 = new Cell(new Phrase("運　　　　　賃", font_g10));
		Cell hcell1011 = new Cell(new Phrase(numericFormatter.format(plInfo.getUnchin().toString(), "##,###,###,##0"), font_g10));
		hcell1011.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1111 = new Cell(true);
		Cell hcell1211 = new Cell(new Phrase("30", font_g10));
		Cell hcell1311 = new Cell(new Phrase("当   月    利   益", font_g10));
		Cell hcell1411 = new Cell(new Phrase(numericFormatter.format(plInfo.getRieki().toString(), "##,###,###,##0"), font_g10));
		hcell1411.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1511 = new Cell(true);

		hcell0211.enableBorderSide(15);
		hcell0311.enableBorderSide(15);
		hcell0411.enableBorderSide(15);
		hcell0811.enableBorderSide(15);
		hcell0911.enableBorderSide(15);
		hcell1011.enableBorderSide(15);
		hcell1211.enableBorderSide(15);
		hcell1311.enableBorderSide(15);
		hcell1411.enableBorderSide(15);

		shohinTable.addCell(hcell0111);
		shohinTable.addCell(hcell0211);
		shohinTable.addCell(hcell0311);
		shohinTable.addCell(hcell0411);
		shohinTable.addCell(hcell0511);
		shohinTable.addCell(hcell0611);
		shohinTable.addCell(hcell0711);
		shohinTable.addCell(hcell0811);
		shohinTable.addCell(hcell0911);
		shohinTable.addCell(hcell1011);
		shohinTable.addCell(hcell1111);
		shohinTable.addCell(hcell1211);
		shohinTable.addCell(hcell1311);
		shohinTable.addCell(hcell1411);
		shohinTable.addCell(hcell1511);
		
		//12行
		Cell hcell01012 = new Cell(true);
		Cell hcell02012 = new Cell(new Phrase("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------",
				font_g08));
		hcell02012.setColspan(13);
		hcell02012.setHorizontalAlignment(Element.ALIGN_LEFT);
		Cell hcell03012 = new Cell(true);

		shohinTable.addCell(hcell01012);
		shohinTable.addCell(hcell02012);
		shohinTable.addCell(hcell03012);
		return shohinTable;
	}

	private Table getSecondObject(PlContentConfirmDto plContentConfirmDto) throws DocumentException, IOException {

		TrnPLInfo plInfo = (TrnPLInfo) plContentConfirmDto.getTrnPLInfo();
	    TrnZenPLInfo zenPlInfo = (TrnZenPLInfo) plContentConfirmDto.getTrnZenPLInfo();
	    
		//ゴシック11pt(下線あり)
		Font font_underline_11 = new Font(BaseFont.createFont(
				"HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 11,
				Font.UNDERLINE);
		Font font_g14 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 14);
		//ゴシック12pt
		Font font_g12 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 12);
		//ゴシック10pt
		Font font_g10 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10);
		//ゴシック10pt（下線あり）
		Font font_underline_g10 = new Font(BaseFont.createFont(
				"HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10,
				Font.UNDERLINE);
		//ゴシック8pt
		Font font_g08 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 8);

		/* テーブル作成 */
		// テーブルの全体定義
		Table secondTable = new Table(15, 23);
		secondTable.setWidth(100);
		int secondTableWidth[] = { 1, 2, 8, 11, 3, 2, 8, 11, 11, 11, 11, 2, 6, 15,
				1 };
//		int secondTableWidth[] = { 1, 2, 8, 11, 3, 2, 8, 11, 11, 11, 11, 2, 18,
//				1 };
		secondTable.setWidths(secondTableWidth);
		secondTable.setBorderWidth(0f);

		secondTable.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		secondTable.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		secondTable.setPadding(0);
//		secondTable.setPadding(1);
		secondTable.setSpacing(0);
		secondTable.setDefaultCellBorder(0);
		secondTable.setBorderColor(new Color(0, 0, 0));

//		secondTable.setSpaceInsideCell(0);

		// ヘッダ
		Cell hcell0101 = new Cell(true);
		Cell hcell0201 = new Cell(new Phrase("(ロ)1.売上高の内訳", font_g10));
		hcell0201.setColspan(4);
		hcell0201.setHorizontalAlignment(Element.ALIGN_LEFT);
		Cell hcell0601 = new Cell(new Phrase("(二)2.売上原価の内訳", font_g10));
		hcell0601.setColspan(10);
//		hcell0601.setColspan(9);
		hcell0601.setHorizontalAlignment(Element.ALIGN_LEFT);
		secondTable.addCell(hcell0101);
		secondTable.addCell(hcell0201);
		secondTable.addCell(hcell0601);

		//2行
		Cell hcell0103 = new Cell(true);
		Cell hcell0203 = new Cell(new Phrase("No", font_g10));
		Cell hcell0303 = new Cell(new Phrase("区   　     分", font_g10));
		Cell hcell0403 = new Cell(new Phrase("金　　　　　額", font_g10));

		Cell hcell0503 = new Cell(true);
		Cell hcell0603 = new Cell(new Phrase("No", font_g10));
		Cell hcell0703 = new Cell(new Phrase("区   　     分", font_g10));
		Cell hcell0803 = new Cell(new Phrase("(a) 前  月  在  庫", font_g10));
		Cell hcell0903 = new Cell(new Phrase("(b) 当  月  仕  入", font_g10));
		Cell hcell1003 = new Cell(new Phrase("(c) 当  月  在  庫", font_g10));
		Cell hcell1103 = new Cell(new Phrase("(d) 差引 売上 原価", font_g10));
		Cell hcell1203 = new Cell(true);
		hcell1203.setColspan(4);

		hcell0203.enableBorderSide(15);
		hcell0303.enableBorderSide(15);
		hcell0403.enableBorderSide(15);
		hcell0603.enableBorderSide(15);
		hcell0703.enableBorderSide(15);
		hcell0803.enableBorderSide(15);
		hcell0903.enableBorderSide(15);
		hcell1003.enableBorderSide(15);
		hcell1103.enableBorderSide(15);

		secondTable.addCell(hcell0103);
		secondTable.addCell(hcell0203);
		secondTable.addCell(hcell0303);
		secondTable.addCell(hcell0403);
		secondTable.addCell(hcell0503);
		secondTable.addCell(hcell0603);
		secondTable.addCell(hcell0703);
		secondTable.addCell(hcell0803);
		secondTable.addCell(hcell0903);
		secondTable.addCell(hcell1003);
		secondTable.addCell(hcell1103);
		secondTable.addCell(hcell1203);

		//4行
		Cell hcell0104 = new Cell(true);
		Cell hcell0204 = new Cell(new Phrase("31", font_g10));
		Cell hcell0304 = new Cell(new Phrase("売　　　上", font_g10));
		Cell hcell0404 = new Cell(new Phrase(numericFormatter.format(plInfo.getUriage().toString(), "##,###,###,##0"), font_g10));
		hcell0404.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Cell hcell0504 = new Cell(true);
		Cell hcell0604 = new Cell(new Phrase("39", font_g10));
		Cell hcell0704 = new Cell(new Phrase("原   材   料", font_g10));
		Cell hcell0804 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getGenzairyoZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell0804.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0904 = new Cell(new Phrase(numericFormatter.format(plInfo.getGenzairyoShire().toString(), "##,###,###,##0"), font_g10));
		hcell0904.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1004 = new Cell(new Phrase(numericFormatter.format(plInfo.getGenzairyoZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell1004.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1104 = new Cell(new Phrase(numericFormatter.format(plInfo.getGenzairyoKei().toString(), "##,###,###,##0"), font_g10));
		hcell1104.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1204 = new Cell(true);
		hcell1204.setColspan(4);

		hcell0204.enableBorderSide(15);
		hcell0304.enableBorderSide(15);
		hcell0404.enableBorderSide(15);
		hcell0604.enableBorderSide(15);
		hcell0704.enableBorderSide(15);
		hcell0804.enableBorderSide(15);
		hcell0904.enableBorderSide(15);
		hcell1004.enableBorderSide(15);
		hcell1104.enableBorderSide(15);

		secondTable.addCell(hcell0104);
		secondTable.addCell(hcell0204);
		secondTable.addCell(hcell0304);
		secondTable.addCell(hcell0404);
		secondTable.addCell(hcell0504);
		secondTable.addCell(hcell0604);
		secondTable.addCell(hcell0704);
		secondTable.addCell(hcell0804);
		secondTable.addCell(hcell0904);
		secondTable.addCell(hcell1004);
		secondTable.addCell(hcell1104);
		secondTable.addCell(hcell1204);

		//5行
		Cell hcell0105 = new Cell(true);
		Cell hcell0205 = new Cell(new Phrase("32", font_g10));
		Cell hcell0305 = new Cell(new Phrase("物 販  売 上", font_g10));
		Cell hcell0405 = new Cell(new Phrase(numericFormatter.format(plInfo.getBuppan().toString(), "##,###,###,##0"), font_g10));
		hcell0405.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Cell hcell0505 = new Cell(true);
		Cell hcell0605 = new Cell(new Phrase("40", font_g10));
		Cell hcell0705 = new Cell(new Phrase("野　　　菜", font_g10));
		Cell hcell0805 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getYasaiZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell0805.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0905 = new Cell(new Phrase(numericFormatter.format(plInfo.getYasaiShire().toString(), "##,###,###,##0"), font_g10));
		hcell0905.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1005 = new Cell(new Phrase(numericFormatter.format(plInfo.getYasaiZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell1005.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1105 = new Cell(new Phrase(numericFormatter.format(plInfo.getYasaiKei().toString(), "##,###,###,##0"), font_g10));
		hcell1105.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1205 = new Cell(true);
		hcell1205.setColspan(4);

		hcell0205.enableBorderSide(15);
		hcell0305.enableBorderSide(15);
		hcell0405.enableBorderSide(15);
		hcell0605.enableBorderSide(15);
		hcell0705.enableBorderSide(15);
		hcell0805.enableBorderSide(15);
		hcell0905.enableBorderSide(15);
		hcell1005.enableBorderSide(15);
		hcell1105.enableBorderSide(15);

		secondTable.addCell(hcell0105);
		secondTable.addCell(hcell0205);
		secondTable.addCell(hcell0305);
		secondTable.addCell(hcell0405);
		secondTable.addCell(hcell0505);
		secondTable.addCell(hcell0605);
		secondTable.addCell(hcell0705);
		secondTable.addCell(hcell0805);
		secondTable.addCell(hcell0905);
		secondTable.addCell(hcell1005);
		secondTable.addCell(hcell1105);
		secondTable.addCell(hcell1205);

		//6行
		Cell hcell0106 = new Cell(true);
		Cell hcell0206 = new Cell(new Phrase("33", font_g10));
		Cell hcell0306 = new Cell(new Phrase("計", font_g10));
		Cell hcell0406 = new Cell(new Phrase(numericFormatter.format(plInfo.getUriUchiwake().toString(), "##,###,###,##0"), font_g10));
		hcell0406.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Cell hcell0506 = new Cell(true);
		Cell hcell0606 = new Cell(new Phrase("41", font_g10));
		Cell hcell0706 = new Cell(new Phrase("包　　　材", font_g10));
		Cell hcell0806 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getHouzaiZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell0806.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0906 = new Cell(new Phrase(numericFormatter.format(plInfo.getHouzaiShire().toString(), "##,###,###,##0"), font_g10));
		hcell0906.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1006 = new Cell(new Phrase(numericFormatter.format(plInfo.getHouzaiZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell1006.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1106 = new Cell(new Phrase(numericFormatter.format(plInfo.getHouzaiKei().toString(), "##,###,###,##0"), font_g10));
		hcell1106.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1206 = new Cell(true);
		hcell1206.setColspan(4);

		hcell0206.enableBorderSide(15);
		hcell0306.enableBorderSide(15);
		hcell0406.enableBorderSide(15);
		hcell0606.enableBorderSide(15);
		hcell0706.enableBorderSide(15);
		hcell0806.enableBorderSide(15);
		hcell0906.enableBorderSide(15);
		hcell1006.enableBorderSide(15);
		hcell1106.enableBorderSide(15);

		secondTable.addCell(hcell0106);
		secondTable.addCell(hcell0206);
		secondTable.addCell(hcell0306);
		secondTable.addCell(hcell0406);
		secondTable.addCell(hcell0506);
		secondTable.addCell(hcell0606);
		secondTable.addCell(hcell0706);
		secondTable.addCell(hcell0806);
		secondTable.addCell(hcell0906);
		secondTable.addCell(hcell1006);
		secondTable.addCell(hcell1106);
		secondTable.addCell(hcell1206);

		//7行
		Cell hcell0107 = new Cell(true);
		Cell hcell0207 = new Cell(new Phrase("33 = 31 + 32", font_g08));
		hcell0207.setColspan(2);
		Cell hcell0407 = new Cell(new Phrase("1.売上高に一致", font_g08));

		Cell hcell0507 = new Cell(true);
		Cell hcell0607 = new Cell(new Phrase("42", font_g10));
		Cell hcell0707 = new Cell(new Phrase("物         販", font_g10));
		Cell hcell0807 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getBuppanZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell0807.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0907 = new Cell(new Phrase(numericFormatter.format(plInfo.getBuppanShire().toString(), "##,###,###,##0"), font_g10));
		hcell0907.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1007 = new Cell(new Phrase(numericFormatter.format(plInfo.getBuppanZaiko().toString(), "##,###,###,##0"), font_g10));
		hcell1007.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1107 = new Cell(new Phrase(numericFormatter.format(plInfo.getBuppanKei().toString(), "##,###,###,##0"), font_g10));
		hcell1107.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1207 = new Cell(true);
		hcell1207.setColspan(4);

		hcell0607.enableBorderSide(15);
		hcell0707.enableBorderSide(15);
		hcell0807.enableBorderSide(15);
		hcell0907.enableBorderSide(15);
		hcell1007.enableBorderSide(15);
		hcell1107.enableBorderSide(15);

		secondTable.addCell(hcell0107);
		secondTable.addCell(hcell0207);
		secondTable.addCell(hcell0407);
		secondTable.addCell(hcell0507);
		secondTable.addCell(hcell0607);
		secondTable.addCell(hcell0707);
		secondTable.addCell(hcell0807);
		secondTable.addCell(hcell0907);
		secondTable.addCell(hcell1007);
		secondTable.addCell(hcell1107);
		secondTable.addCell(hcell1207);

		//8行
		Cell hcell0108 = new Cell(true);
		Cell hcell0208 = new Cell(new Phrase("(ハ)6.水道光熱費の内訳", font_g10));
		hcell0208.setColspan(3);
		hcell0208.setHorizontalAlignment(Element.ALIGN_LEFT);
		Cell hcell0508 = new Cell(true);
		Cell hcell0608 = new Cell(new Phrase("43", font_g10));
		Cell hcell0708 = new Cell(new Phrase("計", font_g10));
		Cell hcell0808 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getTouZaikoKei().toString(), "##,###,###,##0"), font_g10));
		hcell0808.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0908 = new Cell(new Phrase(numericFormatter.format(plInfo.getTouSiireKei().toString(), "##,###,###,##0"), font_g10));
		hcell0908.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1008 = new Cell(new Phrase(numericFormatter.format(plInfo.getTouZaikoKei().toString(), "##,###,###,##0"), font_g10));
		hcell1008.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1108 = new Cell(new Phrase(numericFormatter.format(plInfo.getSashihikiKei().toString(), "##,###,###,##0"), font_g10));
		hcell1108.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1208 = new Cell(true);
		hcell1208.setColspan(4);

		hcell0608.enableBorderSide(15);
		hcell0708.enableBorderSide(15);
		hcell0808.enableBorderSide(15);
		hcell0908.enableBorderSide(15);
		hcell1008.enableBorderSide(15);
		hcell1108.enableBorderSide(15);

		secondTable.addCell(hcell0108);
		secondTable.addCell(hcell0208);
		secondTable.addCell(hcell0508);
		secondTable.addCell(hcell0608);
		secondTable.addCell(hcell0708);
		secondTable.addCell(hcell0808);
		secondTable.addCell(hcell0908);
		secondTable.addCell(hcell1008);
		secondTable.addCell(hcell1108);
		secondTable.addCell(hcell1208);

		//9行
		Cell hcell0109 = new Cell(true);
		Cell hcell0209 = new Cell(new Phrase("No", font_g10));
		Cell hcell0309 = new Cell(new Phrase("区   　     分", font_g10));
		Cell hcell0409 = new Cell(new Phrase("金　　　　　額", font_g10));

		Cell hcell0509 = new Cell(true);
		Cell hcell0609 = new Cell(true);
		Cell hcell0709 = new Cell(true);

		Cell hcell0809 = new Cell(new Phrase(
				"a + b - c = d, 43 = 39 + 40 + 41 + 42", font_g08));
		hcell0809.setColspan(3);

		Cell hcell1109 = new Cell(new Phrase("2.売上原価に一致", font_g08));
		Cell hcell1209 = new Cell(true);
		hcell1209.setColspan(4);

		hcell0209.enableBorderSide(15);
		hcell0309.enableBorderSide(15);
		hcell0409.enableBorderSide(15);

		secondTable.addCell(hcell0109);
		secondTable.addCell(hcell0209);
		secondTable.addCell(hcell0309);
		secondTable.addCell(hcell0409);
		secondTable.addCell(hcell0509);
		secondTable.addCell(hcell0609);
		secondTable.addCell(hcell0709);
		secondTable.addCell(hcell0809);
		secondTable.addCell(hcell1109);
		secondTable.addCell(hcell1209);

		//10行
		Cell hcell0110 = new Cell(true);
		Cell hcell0210 = new Cell(new Phrase("34", font_g10));
		Cell hcell0310 = new Cell(new Phrase("電　気　代", font_g10));
		Cell hcell0410 = new Cell(new Phrase(numericFormatter.format(plInfo.getElec().toString(), "##,###,###,##0"), font_g10));
		hcell0410.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Cell hcell0510 = new Cell(true);

		Cell hcell0610 = new Cell(new Phrase("(ホ)4.給料手当の内訳", font_g10));
		hcell0610.setColspan(7);
		hcell0610.setHorizontalAlignment(Element.ALIGN_LEFT);
		Cell hcell1310 = new Cell(new Phrase("メモ.通信(改装 大きな収入支出等)", font_g10));
		hcell1310.setColspan(3);
		hcell1310.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		hcell0210.enableBorderSide(15);
		hcell0310.enableBorderSide(15);
		hcell0410.enableBorderSide(15);

		secondTable.addCell(hcell0110);
		secondTable.addCell(hcell0210);
		secondTable.addCell(hcell0310);
		secondTable.addCell(hcell0410);
		secondTable.addCell(hcell0510);
		secondTable.addCell(hcell0610);
		secondTable.addCell(hcell1310);
		
		//11行
		Cell hcell0111 = new Cell(true);
		Cell hcell0211 = new Cell(new Phrase("35", font_g10));
		Cell hcell0311 = new Cell(new Phrase("ガ    ス    代", font_g10));
		Cell hcell0411 = new Cell(new Phrase(numericFormatter.format(plInfo.getGas().toString(), "##,###,###,##0"), font_g10));
		hcell0411.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Cell hcell0511 = new Cell(true);
		Cell hcell0611 = new Cell(new Phrase("No", font_g10));
		Cell hcell0711 = new Cell(new Phrase("区   　     分", font_g10));
		Cell hcell0811 = new Cell(new Phrase("(a) 給      　　料", font_g10));
		Cell hcell0911 = new Cell(new Phrase("(b) 賞　　　　　与", font_g10));
		Cell hcell1011 = new Cell(new Phrase("(c) 退　　職　　金", font_g10));
		Cell hcell1111 = new Cell(new Phrase("(d) 計", font_g10));
		Cell hcell1211 = new Cell(true);
		
		// メモ改行文字を変換
		ChangeLineFormatter changeLineFormatter = new ChangeLineFormatter(true);
		Cell hcell1311 = new Cell(new Phrase(changeLineFormatter.format(plInfo
				.getMemo(), true), font_g10));
		hcell1311.setColspan(2);
		hcell1311.setRowspan(5);
		Cell hcell1411 = new Cell(true);

		hcell0211.enableBorderSide(15);
		hcell0311.enableBorderSide(15);
		hcell0411.enableBorderSide(15);
		hcell0611.enableBorderSide(15);
		hcell0711.enableBorderSide(15);
		hcell0811.enableBorderSide(15);
		hcell0911.enableBorderSide(15);
		hcell1011.enableBorderSide(15);
		hcell1111.enableBorderSide(15);
		hcell1311.enableBorderSide(15);
		hcell1311.setVerticalAlignment(Element.ALIGN_TOP);
		hcell1311.setHorizontalAlignment(Element.ALIGN_LEFT);

		secondTable.addCell(hcell0111);
		secondTable.addCell(hcell0211);
		secondTable.addCell(hcell0311);
		secondTable.addCell(hcell0411);
		secondTable.addCell(hcell0511);
		secondTable.addCell(hcell0611);
		secondTable.addCell(hcell0711);
		secondTable.addCell(hcell0811);
		secondTable.addCell(hcell0911);
		secondTable.addCell(hcell1011);
		secondTable.addCell(hcell1111);
		secondTable.addCell(hcell1211);
		secondTable.addCell(hcell1311);
		secondTable.addCell(hcell1411);

		//12行
		Cell hcell0112 = new Cell(true);
		Cell hcell0212 = new Cell(new Phrase("36", font_g10));
		Cell hcell0312 = new Cell(new Phrase("水    道    代", font_g10));
		Cell hcell0412 = new Cell(new Phrase(numericFormatter.format(plInfo.getWater().toString(), "##,###,###,##0"), font_g10));
		hcell0412.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Cell hcell0512 = new Cell(true);
		Cell hcell0612 = new Cell(new Phrase("44", font_g10));
		Cell hcell0712 = new Cell(new Phrase("役  員  報  酬", font_g10));
		Cell hcell0812 = new Cell(new Phrase(numericFormatter.format(plInfo.getYakuinSalary().toString(), "##,###,###,##0"), font_g10));
		hcell0812.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0912 = new Cell(new Phrase(numericFormatter.format(plInfo.getYakuinBonus().toString(), "##,###,###,##0"), font_g10));
		hcell0912.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1012 = new Cell(new Phrase(numericFormatter.format(plInfo.getYakuinRetire().toString(), "##,###,###,##0"), font_g10));
		hcell1012.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1112 = new Cell(new Phrase(numericFormatter.format(plInfo.getYakuinKei().toString(), "##,###,###,##0"), font_g10));
		hcell1112.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1212 = new Cell(true);
		//Cell hcell1312 = new Cell(new Phrase(/*plInfo.getMemo()*/"", font_g08));
		Cell hcell1412 = new Cell(true);

		hcell0212.enableBorderSide(15);
		hcell0312.enableBorderSide(15);
		hcell0412.enableBorderSide(15);
		hcell0612.enableBorderSide(15);
		hcell0712.enableBorderSide(15);
		hcell0812.enableBorderSide(15);
		hcell0912.enableBorderSide(15);
		hcell1012.enableBorderSide(15);
		hcell1112.enableBorderSide(15);
		//hcell1312.enableBorderSide(15);
		//hcell1312.disableBorderSide(Cell.TOP);
		//hcell1312.disableBorderSide(Cell.BOTTOM);
		//hcell1312.setHorizontalAlignment(Element.ALIGN_LEFT);

		secondTable.addCell(hcell0112);
		secondTable.addCell(hcell0212);
		secondTable.addCell(hcell0312);
		secondTable.addCell(hcell0412);
		secondTable.addCell(hcell0512);
		secondTable.addCell(hcell0612);
		secondTable.addCell(hcell0712);
		secondTable.addCell(hcell0812);
		secondTable.addCell(hcell0912);
		secondTable.addCell(hcell1012);
		secondTable.addCell(hcell1112);
		secondTable.addCell(hcell1212);
		//secondTable.addCell(hcell1312);
		secondTable.addCell(hcell1412);

		//13行
		Cell hcell0113 = new Cell(true);
		Cell hcell0213 = new Cell(new Phrase("37", font_g10));
		Cell hcell0313 = new Cell(new Phrase("そ    の    他", font_g10));
		Cell hcell0413 = new Cell(new Phrase(numericFormatter.format(plInfo.getSonota().toString(), "##,###,###,##0"), font_g10));
		hcell0413.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Cell hcell0513 = new Cell(true);
		Cell hcell0613 = new Cell(new Phrase("45", font_g10));
		Cell hcell0713 = new Cell(new Phrase("給  料  手  当", font_g10));
		Cell hcell0813 = new Cell(new Phrase(numericFormatter.format(plInfo.getSalarySalary().toString(), "##,###,###,##0"), font_g10));
		hcell0813.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0913 = new Cell(new Phrase(numericFormatter.format(plInfo.getSalaryBonus().toString(), "##,###,###,##0"), font_g10));
		hcell0913.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1013 = new Cell(new Phrase(numericFormatter.format(plInfo.getSalaryRetire().toString(), "##,###,###,##0"), font_g10));
		hcell1013.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1113 = new Cell(new Phrase(numericFormatter.format(plInfo.getSalaryKei().toString(), "##,###,###,##0"), font_g10));
		hcell1113.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1213 = new Cell(true);
		//Cell hcell1313 = new Cell(new Phrase("", font_g08));
		Cell hcell1413 = new Cell(true);

		hcell0213.enableBorderSide(15);
		hcell0313.enableBorderSide(15);
		hcell0413.enableBorderSide(15);
		hcell0613.enableBorderSide(15);
		hcell0713.enableBorderSide(15);
		hcell0813.enableBorderSide(15);
		hcell0913.enableBorderSide(15);
		hcell1013.enableBorderSide(15);
		hcell1113.enableBorderSide(15);
		//hcell1313.enableBorderSide(15);
		//hcell1313.disableBorderSide(Cell.BOTTOM);
		//hcell1313.disableBorderSide(Cell.TOP);
		//hcell1313.setHorizontalAlignment(Element.ALIGN_LEFT);

		secondTable.addCell(hcell0113);
		secondTable.addCell(hcell0213);
		secondTable.addCell(hcell0313);
		secondTable.addCell(hcell0413);
		secondTable.addCell(hcell0513);
		secondTable.addCell(hcell0613);
		secondTable.addCell(hcell0713);
		secondTable.addCell(hcell0813);
		secondTable.addCell(hcell0913);
		secondTable.addCell(hcell1013);
		secondTable.addCell(hcell1113);
		secondTable.addCell(hcell1213);
		//secondTable.addCell(hcell1313);
		secondTable.addCell(hcell1413);

		//14行
		Cell hcell0114 = new Cell(true);
		Cell hcell0214 = new Cell(new Phrase("38", font_g10));
		Cell hcell0314 = new Cell(new Phrase("計", font_g10));
		Cell hcell0414 = new Cell(new Phrase(numericFormatter.format(plInfo.getSuikouUchiwake().toString(), "##,###,###,##0"), font_g10));
		hcell0414.setHorizontalAlignment(Element.ALIGN_RIGHT);
		
		Cell hcell0514 = new Cell(true);
		Cell hcell0614 = new Cell(new Phrase("46", font_g10));
		Cell hcell0714 = new Cell(new Phrase("雑   　     給", font_g10));
		Cell hcell0814 = new Cell(new Phrase(numericFormatter.format(plInfo.getZakkyuSalary().toString(), "##,###,###,##0"), font_g10));
		hcell0814.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0914 = new Cell(new Phrase(numericFormatter.format(plInfo.getZakkyuBonus().toString(), "##,###,###,##0"), font_g10));
		hcell0914.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1014 = new Cell(new Phrase(numericFormatter.format(plInfo.getZakkyuRetire().toString(), "##,###,###,##0"), font_g10));
		hcell1014.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1114 = new Cell(new Phrase(numericFormatter.format(plInfo.getZakkyuKei().toString(), "##,###,###,##0"), font_g10));
		hcell1114.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1214 = new Cell(true);
		//Cell hcell1314 = new Cell(new Phrase("", font_g08));
		Cell hcell1414 = new Cell(true);

		hcell0214.enableBorderSide(15);
		hcell0314.enableBorderSide(15);
		hcell0414.enableBorderSide(15);
		hcell0614.enableBorderSide(15);
		hcell0714.enableBorderSide(15);
		hcell0814.enableBorderSide(15);
		hcell0914.enableBorderSide(15);
		hcell1014.enableBorderSide(15);
		hcell1114.enableBorderSide(15);
		//hcell1314.enableBorderSide(15);
		//hcell1314.disableBorderSide(Cell.BOTTOM);
		//hcell1314.disableBorderSide(Cell.TOP);
		//hcell1314.setHorizontalAlignment(Element.ALIGN_LEFT);

		secondTable.addCell(hcell0114);
		secondTable.addCell(hcell0214);
		secondTable.addCell(hcell0314);
		secondTable.addCell(hcell0414);
		secondTable.addCell(hcell0514);
		secondTable.addCell(hcell0614);
		secondTable.addCell(hcell0714);
		secondTable.addCell(hcell0814);
		secondTable.addCell(hcell0914);
		secondTable.addCell(hcell1014);
		secondTable.addCell(hcell1114);
		secondTable.addCell(hcell1214);
		//secondTable.addCell(hcell1314);
		secondTable.addCell(hcell1414);

		//15行
		Cell hcell0115 = new Cell(true);
		Cell hcell0215 = new Cell(new Phrase(
				"38 = 34 + 35 + 36 + 37   6.水道光熱費に一致", font_g08));
		hcell0215.setColspan(3);

		Cell hcell0515 = new Cell(true);
		Cell hcell0615 = new Cell(new Phrase("47", font_g10));
		Cell hcell0715 = new Cell(new Phrase("計", font_g10));
		Cell hcell0815 = new Cell(new Phrase(numericFormatter.format(plInfo.getKyuryoKei().toString(), "##,###,###,##0"), font_g10));
		hcell0815.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell0915 = new Cell(new Phrase(numericFormatter.format(plInfo.getBonusKei().toString(), "##,###,###,##0"), font_g10));
		hcell0915.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1015 = new Cell(new Phrase(numericFormatter.format(plInfo.getRetireKei().toString(), "##,###,###,##0"), font_g10));
		hcell1015.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1115 = new Cell(new Phrase(numericFormatter.format(plInfo.getSalaryUtiKei().toString(), "##,###,###,##0"), font_g10));
		hcell1115.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell hcell1215 = new Cell(true);
		//Cell hcell1315 = new Cell(new Phrase("", font_g08));
		Cell hcell1415 = new Cell(true);

		hcell0615.enableBorderSide(15);
		hcell0715.enableBorderSide(15);
		hcell0815.enableBorderSide(15);
		hcell0915.enableBorderSide(15);
		hcell1015.enableBorderSide(15);
		hcell1115.enableBorderSide(15);
		///hcell1315.enableBorderSide(15);
		//hcell1315.disableBorderSide(Cell.TOP);
		//hcell1315.setHorizontalAlignment(Element.ALIGN_LEFT);

		secondTable.addCell(hcell0115);
		secondTable.addCell(hcell0215);
		secondTable.addCell(hcell0515);
		secondTable.addCell(hcell0615);
		secondTable.addCell(hcell0715);
		secondTable.addCell(hcell0815);
		secondTable.addCell(hcell0915);
		secondTable.addCell(hcell1015);
		secondTable.addCell(hcell1115);
		secondTable.addCell(hcell1215);
		//secondTable.addCell(hcell1315);
		secondTable.addCell(hcell1415);

		//16行
		Cell hcell0116 = new Cell(true);
		hcell0116.setColspan(7);
		Cell hcell0816 = new Cell(new Phrase(
				"a + b + c = d,   47 = 44 + 45 + 46", font_g08));
		hcell0816.setColspan(3);
		Cell hcell1116 = new Cell(new Phrase("4.給料手当に一致", font_g08));
		Cell hcell1216 = new Cell(true);
		Cell hcell1316 = new Cell(true);
		hcell1316.setColspan(2);
		Cell hcell1416 = new Cell(true);

		secondTable.addCell(hcell0116);
		secondTable.addCell(hcell0816);
		secondTable.addCell(hcell1116);
		secondTable.addCell(hcell1216);
		secondTable.addCell(hcell1316);
		secondTable.addCell(hcell1416);

		// ヘッダ
		Cell ghcell0100 = new Cell(true);
		ghcell0100.setColspan(5);
		Cell ghcell0600 = new Cell(new Phrase("(へ)借入金等の動き", font_g10));
		ghcell0600.setColspan(7);
		ghcell0600.setHorizontalAlignment(Element.ALIGN_LEFT);
		Cell ghcell1300 = new Cell(new Phrase("作　成　者", font_g10));
		ghcell1300.setColspan(3);
		ghcell1300.setHorizontalAlignment(Element.ALIGN_LEFT);

		secondTable.addCell(ghcell0100);
		secondTable.addCell(ghcell0600);
		secondTable.addCell(ghcell1300);

		//1行
		Cell ghcell0101 = new Cell(true);
		ghcell0101.setColspan(5);
		Cell ghcell0601 = new Cell(new Phrase("No", font_g10));
		Cell ghcell0701 = new Cell(new Phrase("区             分", font_g10));
		Cell ghcell0801 = new Cell(new Phrase("(a)前  月  残  高", font_g10));
		Cell ghcell0901 = new Cell(new Phrase("(b)当  月  増  加", font_g10));
		Cell ghcell1001 = new Cell(new Phrase("(c)当  月  減  少", font_g10));
		Cell ghcell1101 = new Cell(new Phrase("(d)当  月  残  高", font_g10));
		Cell ghcell1201 = new Cell(true);
		
		Table cellTable1 = new Table(2, 1);
		cellTable1.setWidth(100);
		int cellTable1Width[] = {40, 60};
		cellTable1.setWidths(cellTable1Width);
		cellTable1.setBorderWidth(0f);
		cellTable1.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		cellTable1.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTable1.setPadding(0);
//		cellTable1.setPadding(1);
		cellTable1.setSpacing(0);
		cellTable1.setDefaultCellBorder(0);
		cellTable1.setBorderColor(new Color(0, 0, 0));
		
		Table cellTable2 = new Table(2, 1);
		cellTable2.setWidth(100);
		int cellTable2Width[] = {40, 60};
		cellTable2.setWidths(cellTable2Width);
		cellTable2.setBorderWidth(0f);
		cellTable2.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		cellTable2.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTable2.setPadding(0);
//		cellTable2.setPadding(1);
		cellTable2.setSpacing(0);
		cellTable2.setDefaultCellBorder(0);
		cellTable2.setBorderColor(new Color(0, 0, 0));
		
		Table cellTable3 = new Table(2, 2);
		cellTable3.setWidth(100);
		int cellTable3Width[] = {40, 60};
		cellTable3.setWidths(cellTable3Width);
		cellTable3.setBorderWidth(0f);
		cellTable3.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		cellTable3.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTable3.setPadding(0);
//		cellTable3.setPadding(1);
		cellTable3.setSpacing(0);
		cellTable3.setDefaultCellBorder(0);
		cellTable3.setBorderColor(new Color(0, 0, 0));
		
		Table cellTable4 = new Table(2, 1);
		cellTable4.setWidth(100);
		int cellTable4Width[] = {40, 60};
		cellTable4.setWidths(cellTable4Width);
		cellTable4.setBorderWidth(0f);
		cellTable4.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		cellTable4.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTable4.setPadding(0);
//		cellTable4.setPadding(1);
		cellTable4.setSpacing(0);
		cellTable4.setDefaultCellBorder(0);
		cellTable4.setBorderColor(new Color(0, 0, 0));
		/*
		cellTable1.setWidths(cellTable1Width);
		cellTable1.setBorderWidth(0f);
		cellTable1.setBorderWidthTop(0f);
		cellTable1.setBorderWidthBottom(0f);
		cellTable1.setBorderWidthRight(0f);
		cellTable1.setBorderWidthLeft(0f);
		cellTable1.setDefaultHorizontalAlignment(Element.ALIGN_CENTER);
		cellTable1.setDefaultVerticalAlignment(Element.ALIGN_MIDDLE);
		cellTable1.setPadding(1);
		cellTable1.setSpacing(0);
		cellTable1.setSpaceInsideCell(0f);
		cellTable1.setDefaultCellBorder(0);
		cellTable1.setDefaultCellBorderWidth(0f);
		*/
		
		Cell ghcell1301_1 = new Cell(new Phrase("年 月 日", font_g10));
		//日フォーマット
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_NORMAL);
        String plYmd = dateFormatter.format(plInfo.getAuthDt().trim(),DateFormatter.PATTERN_KANJI_YMD, DateFormatter.DATE_TYPE_YMD);
		
        Cell ghcell1301_2 = new Cell(new Phrase(plYmd , font_g10));
        Cell ghcell1401 = new Cell(true);

		ghcell0601.enableBorderSide(15);
		ghcell0701.enableBorderSide(15);
		ghcell0801.enableBorderSide(15);
		ghcell0901.enableBorderSide(15);
		ghcell1001.enableBorderSide(15);
		ghcell1101.enableBorderSide(15);
		//ghcell1301.enableBorderSide(15);
		ghcell1301_1.enableBorderSide(15);
		ghcell1301_2.enableBorderSide(15);

		secondTable.addCell(ghcell0101);
		secondTable.addCell(ghcell0601);
		secondTable.addCell(ghcell0701);
		secondTable.addCell(ghcell0801);
		secondTable.addCell(ghcell0901);
		secondTable.addCell(ghcell1001);
		secondTable.addCell(ghcell1101);
		secondTable.addCell(ghcell1201);
//		cellTable1.addCell(ghcell1301_1);
//		cellTable1.addCell(ghcell1301_2);
//		secondTable.insertTable(cellTable1);

		secondTable.addCell(ghcell1301_1);
		secondTable.addCell(ghcell1301_2);

		//secondTable.addCell(ghcell1301);
		secondTable.addCell(ghcell1401);

		//2行
		Cell ghcell0102 = new Cell(true);
		ghcell0102.setColspan(5);
		Cell ghcell0602 = new Cell(new Phrase("48", font_g10));
		Cell ghcell0702 = new Cell(new Phrase("借      入      金", font_g10));
		Cell ghcell0802 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getKashiireZandaka().toString(), "##,###,###,##0"), font_g10));
		ghcell0802.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell0902 = new Cell(new Phrase(numericFormatter.format(plInfo.getKashiireUp().toString(), "##,###,###,##0"), font_g10));
		ghcell0902.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1002 = new Cell(new Phrase(numericFormatter.format(plInfo.getKashiireDown().toString(), "##,###,###,##0"), font_g10));
		ghcell1002.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1102 = new Cell(new Phrase(numericFormatter.format(plInfo.getKashiireZandaka().toString(), "##,###,###,##0"), font_g10));
		ghcell1102.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1202 = new Cell(true);
		Cell ghcell1302_1 = new Cell(new Phrase("phone", font_g10));
		Cell ghcell1302_2 = new Cell(new Phrase(plInfo.getAuthPhoneNum().trim(), font_g10));


		Cell ghcell1402 = new Cell(true);

		ghcell0602.enableBorderSide(15);
		ghcell0702.enableBorderSide(15);
		ghcell0802.enableBorderSide(15);
		ghcell0902.enableBorderSide(15);
		ghcell1002.enableBorderSide(15);
		ghcell1102.enableBorderSide(15);
		ghcell1302_1.enableBorderSide(15);
		ghcell1302_2.enableBorderSide(15);

		secondTable.addCell(ghcell0102);
		secondTable.addCell(ghcell0602);
		secondTable.addCell(ghcell0702);
		secondTable.addCell(ghcell0802);
		secondTable.addCell(ghcell0902);
		secondTable.addCell(ghcell1002);
		secondTable.addCell(ghcell1102);
		secondTable.addCell(ghcell1202);
//		cellTable2.addCell(ghcell1302_1);
//		cellTable2.addCell(ghcell1302_2);
//		secondTable.insertTable(cellTable2);

		secondTable.addCell(ghcell1302_1);
		secondTable.addCell(ghcell1302_2);
		
		secondTable.addCell(ghcell1402);

		//3行
		Cell ghcell0103 = new Cell(true);
		ghcell0103.setColspan(5);
		Cell ghcell0603 = new Cell(new Phrase("49", font_g10));
		Cell ghcell0703 = new Cell(new Phrase("割 賦 未 払 金", font_g10));
		Cell ghcell0803 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getKappuZandaka().toString(), "##,###,###,##0"), font_g10));
		ghcell0803.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell0903 = new Cell(new Phrase(numericFormatter.format(plInfo.getKappuUp().toString(), "##,###,###,##0"), font_g10));
		ghcell0903.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1003 = new Cell(new Phrase(numericFormatter.format(plInfo.getKappuDown().toString(), "##,###,###,##0"), font_g10));
		ghcell1003.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1103 = new Cell(new Phrase(numericFormatter.format(plInfo.getKappuZandaka().toString(), "##,###,###,##0"), font_g10));
		ghcell1103.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1203 = new Cell(true);
		//Cell ghcell1303 = new Cell(new Phrase("会計事務所等", font_g08));
		//ghcell1303.setHorizontalAlignment(Element.ALIGN_LEFT);
		Cell ghcell1303_1 = new Cell(new Phrase("会計事務所等", font_g08));
		ghcell1303_1.setRowspan(2);
		Cell ghcell1303_2 = new Cell(new Phrase(plInfo.getAuthOther().trim(), font_g10));
		ghcell1303_2.setRowspan(2);
		Cell ghcell1403 = new Cell(true);

		ghcell0603.enableBorderSide(15);
		ghcell0703.enableBorderSide(15);
		ghcell0803.enableBorderSide(15);
		ghcell0903.enableBorderSide(15);
		ghcell1003.enableBorderSide(15);
		ghcell1103.enableBorderSide(15);
		ghcell1303_1.enableBorderSide(15);
		ghcell1303_2.enableBorderSide(15);

		secondTable.addCell(ghcell0103);
		secondTable.addCell(ghcell0603);
		secondTable.addCell(ghcell0703);
		secondTable.addCell(ghcell0803);
		secondTable.addCell(ghcell0903);
		secondTable.addCell(ghcell1003);
		secondTable.addCell(ghcell1103);
		secondTable.addCell(ghcell1203);
		cellTable3.addCell(ghcell1303_1);
		cellTable3.addCell(ghcell1303_2);

//		Cell ccountantOfficeEtc  = new Cell(cellTable3);
//		ccountantOfficeEtc.setRowspan(2);
//		secondTable.addCell(ccountantOfficeEtc);
//		secondTable.insertTable(cellTable3);

		secondTable.addCell(ghcell1303_1);
		secondTable.addCell(ghcell1303_2);
		
		secondTable.addCell(ghcell1403);

		//3行
		Cell ghcell0104 = new Cell(true);
		ghcell0104.setColspan(5);
		Cell ghcell0604 = new Cell(new Phrase("50", font_g10));
		Cell ghcell0704 = new Cell(new Phrase("リース未払金", font_g10));
		Cell ghcell0804 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getLeaseZandaka().toString(), "##,###,###,##0"), font_g10));
		ghcell0804.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell0904 = new Cell(new Phrase(numericFormatter.format(plInfo.getLeaseUp().toString(), "##,###,###,##0"), font_g10));
		ghcell0904.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1004 = new Cell(new Phrase(numericFormatter.format(plInfo.getLeaseDown().toString(), "##,###,###,##0"), font_g10));
		ghcell1004.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1104 = new Cell(new Phrase(numericFormatter.format(plInfo.getLeaseZandaka().toString(), "##,###,###,##0"), font_g10));
		ghcell1104.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1204 = new Cell(true);
		//Cell ghcell1304 = new Cell(new Phrase("氏名", font_g08));
		//ghcell1304.setHorizontalAlignment(Element.ALIGN_LEFT);
		Cell ghcell1304_1 = new Cell(new Phrase("氏名", font_g10));
		ghcell1304_1.setRowspan(2);
		Cell ghcell1304_2 = new Cell(new Phrase(plInfo.getAuthorName().trim(), font_g10));
		ghcell1304_2.setRowspan(2);
		Cell ghcell1404 = new Cell(true);

		ghcell0604.enableBorderSide(15);
		ghcell0704.enableBorderSide(15);
		ghcell0804.enableBorderSide(15);
		ghcell0904.enableBorderSide(15);
		ghcell1004.enableBorderSide(15);
		ghcell1104.enableBorderSide(15);
		ghcell1304_1.enableBorderSide(15);
		ghcell1304_2.enableBorderSide(15);

		secondTable.addCell(ghcell0104);
		secondTable.addCell(ghcell0604);
		secondTable.addCell(ghcell0704);
		secondTable.addCell(ghcell0804);
		secondTable.addCell(ghcell0904);
		secondTable.addCell(ghcell1004);
		secondTable.addCell(ghcell1104);
		secondTable.addCell(ghcell1204);
		cellTable4.addCell(ghcell1304_1);
		cellTable4.addCell(ghcell1304_2);
//		secondTable.insertTable(cellTable4);
		secondTable.addCell(ghcell1404);

		//4行
		Cell ghcell0105 = new Cell(true);
		ghcell0105.setColspan(5);
		Cell ghcell0605 = new Cell(new Phrase("51", font_g10));
		Cell ghcell0705 = new Cell(new Phrase("計", font_g10));
		Cell ghcell0805 = new Cell(new Phrase(numericFormatter.format(zenPlInfo.getTouZandakaKei().toString(), "##,###,###,##0"), font_g10));
		ghcell0805.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell0905 = new Cell(new Phrase(numericFormatter.format(plInfo.getTouZoukaKei().toString(), "##,###,###,##0"), font_g10));
		ghcell0905.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1005 = new Cell(new Phrase(numericFormatter.format(plInfo.getTouGenshoKei().toString(), "##,###,###,##0"), font_g10));
		ghcell1005.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1105 = new Cell(new Phrase(numericFormatter.format(plInfo.getTouZandakaKei().toString(), "##,###,###,##0"), font_g10));
		ghcell1105.setHorizontalAlignment(Element.ALIGN_RIGHT);
		Cell ghcell1205 = new Cell(true);
		Cell ghcell1305 = new Cell(true);
		Cell ghcell1405 = new Cell(true);

		ghcell0605.enableBorderSide(15);
		ghcell0705.enableBorderSide(15);
		ghcell0805.enableBorderSide(15);
		ghcell0905.enableBorderSide(15);
		ghcell1005.enableBorderSide(15);
		ghcell1105.enableBorderSide(15);

		secondTable.addCell(ghcell0105);
		secondTable.addCell(ghcell0605);
		secondTable.addCell(ghcell0705);
		secondTable.addCell(ghcell0805);
		secondTable.addCell(ghcell0905);
		secondTable.addCell(ghcell1005);
		secondTable.addCell(ghcell1105);
		secondTable.addCell(ghcell1205);
//		secondTable.insertTable(cellTable4);
//		secondTable.addCell(ghcell1305);

		secondTable.addCell(ghcell1304_1);
		secondTable.addCell(ghcell1304_2);
		
		secondTable.addCell(ghcell1405);

		//5行
		Cell ghcell0106 = new Cell(true);
		ghcell0106.setColspan(7);

		Cell ghcell0806 = new Cell(new Phrase(
				"a + b - c = d,  51 = 48 + 49 + 50 ", font_g08));
		ghcell0806.setColspan(3);
		Cell ghcell1106 = new Cell(new Phrase("", font_g08));
		Cell ghcell1206 = new Cell(true);
//		ghcell1206.setColspan(4);

		secondTable.addCell(ghcell0106);
		secondTable.addCell(ghcell0806);
		secondTable.addCell(ghcell1106);
		secondTable.addCell(ghcell1206);
		secondTable.addCell(ghcell1206);

		return secondTable;
	}

	public void returnAction(final ByteArrayOutputStream out,
			final HttpServletResponse response, final PdfOutputDto dto)
			throws IOException {
		try {
			//ブラウザへの出力
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ getFileName(dto) + "\"");
			response.setContentLength(out.size());

			BufferedOutputStream bos = new BufferedOutputStream(response
					.getOutputStream());
			bos.write(out.toByteArray());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public String getPageKey(final PdfOutputDto pdfOutputDto) {
		return null;
	}
}