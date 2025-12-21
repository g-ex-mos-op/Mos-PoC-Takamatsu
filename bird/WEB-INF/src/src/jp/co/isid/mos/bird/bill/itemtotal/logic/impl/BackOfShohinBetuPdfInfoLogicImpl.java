package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDto;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiInfoDto;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;
import jp.co.isid.mos.bird.framework.logic.PdfOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

/**
 * 商品別合計PDF出力ロジック
 * @author xlee
 */
public class BackOfShohinBetuPdfInfoLogicImpl implements PdfOutputLogic {

	
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L08";
    
    /**　フォントサイズ14 */
    public static final String FONT_SZ14 = "14";
    
    /** フォントサイズ10 */
    public static final String FONT_SZ10 = "10";
    
    /** 一ページにつき最大ライン数     */
    public static final int LINE_MAXCNT = 50;
    
    /** 処理区分：明細*/
    public static final String PROC_MEISAI = "MEISAI";
    
    /** 処理区分：合計*/
    public static final String PROC_GOUKEI = "GOUKEI";
    
    /** 処理区分：テーブルの開始 */
    public static final String LINEKBN_START = "START";
    
    /** 処理区分：テーブルの終了 */
    public static final String LINEKBN_END = "END";
    
    /** 処理区分：ヘッダテーブルのセールサイズ */
    private static final int TBL_HEADER_WIDTH[] = {15, 10, 40, 10, 15};
    
    /** 処理区分：明細テーブルのセールサイズ */
    private static final int TBL_DETAIL_WIDTH[] = {52, 7, 12, 12, 10, 8, 8};
    
    /** 数字フォマット */
    private static NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    
    /** 数字フォマット */
    private static NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);
    
    /** ヘッダ名 */
    private static final String TBL_HEADER_NM[] = {"商　品","数量","単位","単価","金額","構成比","売上比"};
    
    /** 総合計分類名 */
    private static final String TBL_BUNRUI_NM = "総合計";
    
    /** ファイル名 */
    public static final String FILE_NAME = "SHOHINBETU";
    
	/* Documentオブジェクト設定処理 */
	public Document createDocument() {
		Document doc = new Document(PageSize.A4);
		doc.setMargins(10f, 10f, 10f, 10f);
		return doc;
	}

	/* ファイル名取得 */
	public String getFileName(final PdfOutputDto pdfOutputDto) {
		ShobetuGoukeiDto dto = (ShobetuGoukeiDto) pdfOutputDto;
		// （JYOYAKU[年月]_[店コード]_[時間単位]）
        String fileName = FILE_NAME + "_" + dto.getCondTaishoKikanCd() + "_" + 
        	dto.getCondTaishoTenpoCd() + ".pdf";
        return fileName;
	}
	
	/**
	 * Fontサイズを設定します。
	 * 
	 * @param fontSize
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	private Font selectFont(String fontSize) throws DocumentException, IOException {
		Font fontSz = null;
		
		if(fontSize.equals(FONT_SZ14)) {
			//ゴシック14pt
			fontSz = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
					"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), Integer.parseInt(FONT_SZ14));
		} else if(fontSize.equals(FONT_SZ10)) {
			//ゴシック10pt
			fontSz = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), Integer.parseInt(FONT_SZ10));
		} 
		return fontSz;
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
		//データの取得
		ShobetuGoukeiDto dto = (ShobetuGoukeiDto) pdfOutputDto;
		
		//ＰＤＦを作成
		doc = makeOutPutPdf(dto, doc);
		return doc;
	}
	
	/* ヘッダ設定処理 */
	public Document setupHeader(final Document doc,
			final PdfOutputDto pdfOutputDto) throws DocumentException {
		return doc;
	}

	/* フッター設定処理 */
	public Document setupFooter(final Document doc,
			final PdfOutputDto pdfOutputDto) throws DocumentException {
		return doc;
	}
	
	/**
	 * PDF作成
	 * @param totalMap
	 * @param doc
	 * @return　Document
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	private Document makeOutPutPdf(ShobetuGoukeiDto dto,  final Document doc) throws DocumentException, IOException {

		//ページ開始
		int pageNo = 1;
		
		List shohinBetuInfoList = dto.getShohinbetuInfoList();

		String tmpBunrui = "";
		
		int lineCnt = 1;
		
        PdfPTable detTable = new PdfPTable(TBL_DETAIL_WIDTH.length);
        detTable.setWidths(TBL_DETAIL_WIDTH);
        detTable.setWidthPercentage(100f);
        
        boolean bunruiFlg = false;
        boolean chgBunruiFlg = false;
        boolean sumFlg = false;
        
        //List [List [ShobetuGoukeiInfoDto]]
		for(int i = 0; i < shohinBetuInfoList.size(); i++) {
			List tmpShohinBetuInfoLst = (List) shohinBetuInfoList.get(i);
			bunruiFlg = true;
			chgBunruiFlg = true;
			sumFlg = true;
			for(int j = 0 ; j < tmpShohinBetuInfoLst.size(); j++) {
				ShobetuGoukeiInfoDto shobetuInfo = (ShobetuGoukeiInfoDto) tmpShohinBetuInfoLst.get(j);
				
				String lineKbn = "";
				String procKbn = "";
				String seisyusyoBunrui = shobetuInfo.getSeikyusyoBunrui();
				if(!isNull(seisyusyoBunrui)) {
					//最大ライン５２を超えるか残り行が2行未満
					if(lineCnt > LINE_MAXCNT) {
		        		doc.add(detTable);
						doc.newPage();
						++pageNo;
						lineCnt = 1;
						chgBunruiFlg = true;
					}
                    else if(seisyusyoBunrui.length() == 3 
                                && !tmpBunrui.equals(seisyusyoBunrui)
                                &&	tmpShohinBetuInfoLst.size() == 4 
                                && (LINE_MAXCNT - lineCnt) < 6) 
                    {
						doc.add(detTable);
						doc.newPage();
						++pageNo;
						lineCnt = 1;
						chgBunruiFlg = true;
					}
					
					//頁の開始
					if(lineCnt == 1) {
						//ヘッダの出力
						if(chgBunruiFlg) {
							doc.setPageCount(pageNo);
							doc.add(getTitleObj(dto, pageNo));
                            chgBunruiFlg = false;
						}
						doc.add(getHeaderObj(shobetuInfo.getSeBnrName(), bunruiFlg));
                        if(bunruiFlg){
                            lineCnt++;
                        }
						//テーブル作成
						detTable = new PdfPTable(TBL_DETAIL_WIDTH.length);
				        detTable.setWidths(TBL_DETAIL_WIDTH);
				        detTable.setWidthPercentage(100f);
		        		lineKbn = LINEKBN_START;
		        	} else {
		        		//商品分類及び総合計
		        		if((seisyusyoBunrui.length() == 3 ) &&
		        				!tmpBunrui.equals(seisyusyoBunrui)) {
		        			doc.add(detTable);
		        			doc.add(getHeaderObj(shobetuInfo.getSeBnrName(), bunruiFlg));
		        			lineCnt++;
                            if(bunruiFlg){
                                lineCnt++;
                            }
							//テーブル作成
							detTable = new PdfPTable(TBL_DETAIL_WIDTH.length);
					        detTable.setWidths(TBL_DETAIL_WIDTH);
					        detTable.setWidthPercentage(100f);
			        		lineKbn = LINEKBN_START;
		        		}
		        	}
					//データの最後
					if(j == tmpShohinBetuInfoLst.size() - 1) {
		        		lineKbn = LINEKBN_END;
		        	}
					if(seisyusyoBunrui.equals("1") || 
							seisyusyoBunrui.equals("2")) {
						if(sumFlg) {
						//合計を表示する時、合計部3行+space1行が入るかどうか
                            if(lineCnt + 4 > LINE_MAXCNT) {
				        		doc.add(detTable);
								doc.newPage();
								++pageNo;
								lineCnt = 1;
	
								//ヘッダの出力
								doc.setPageCount(pageNo);
								doc.add(getTitleObj(dto, pageNo));
								doc.add(getHeaderObj(shobetuInfo.getSeBnrName(), bunruiFlg));
                                
                                if(bunruiFlg){
                                    lineCnt++;
                                }
								//テーブル作成
								detTable = new PdfPTable(TBL_DETAIL_WIDTH.length);
						        detTable.setWidths(TBL_DETAIL_WIDTH);
						        detTable.setWidthPercentage(100f);
				        		lineKbn = LINEKBN_START;
							}
						}
						procKbn = PROC_GOUKEI;
						sumFlg = false;
					} else {
						procKbn = PROC_MEISAI;
					}
					makeCellInfo(detTable, shobetuInfo, procKbn, lineKbn);
					lineCnt++;
                    if(LINEKBN_END == lineKbn){
                        lineCnt++;
                    }
				} else {
        			doc.add(detTable);
					
					//総合計の場合
					if(lineCnt + 6 > LINE_MAXCNT) {
						doc.newPage();
						++pageNo;
						lineCnt = 1;

						//ヘッダの出力
						doc.setPageCount(pageNo);
						doc.add(getTitleObj(dto, pageNo));
					}
					if(j == 0) {
						doc.add(getHeaderObj(TBL_BUNRUI_NM, bunruiFlg));
					}
					//タイトル部　テーブル作成
					detTable = new PdfPTable(TBL_DETAIL_WIDTH.length);
			        detTable.setWidths(TBL_DETAIL_WIDTH);
			        detTable.setWidthPercentage(100f);
					//データの最後
					if(j == tmpShohinBetuInfoLst.size() - 1) {
		        		lineKbn = LINEKBN_END;
		        	} else {
		        		lineKbn = LINEKBN_START;
		        	}
					procKbn = PROC_GOUKEI;
					makeCellInfo(detTable, shobetuInfo, procKbn, lineKbn);
				}
				tmpBunrui = seisyusyoBunrui;
				bunruiFlg = false;
//				chgBunruiFlg = false;
			}
		}
		//処理の最後
		doc.add(detTable);
		return doc;
	}
	
	/**
	 * 	ヘッダテーブルを生成する
	 * 
	 * @param tmpHeaderList　ヘッダ情報リスト
	 * @param pageNo　ページ番号
	 * @return PdfPTable
	 * @throws DocumentException
	 * @throws IOException
	 */
	private PdfPTable getTitleObj(ShobetuGoukeiDto dto, int pageNo)
			throws DocumentException, IOException {

		//タイトル部　テーブル作成
        PdfPTable table = new PdfPTable(TBL_HEADER_WIDTH.length);
		table.setWidths(TBL_HEADER_WIDTH);
		table.setWidthPercentage(100f);
		
        PdfPCell cell = new PdfPCell(new Phrase("　"));  
		cell.setBorderWidth(0f);
		cell.setColspan(2);
		table.addCell(cell);
        
		cell = new PdfPCell(new Phrase("商品別合計　("+ dto.getCondTaishoKikanCd().substring(0,4) + "年"
				+ dto.getCondTaishoKikanCd().substring(4,6) + "月)", selectFont(FONT_SZ14))); //タイトル
		cell.setBorderWidth(0f);
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(pageNo + "頁", selectFont(FONT_SZ10))); //頁
		cell.setBorderWidth(0f);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(hankaku2zenkaku(dto.getCondOnerCd()) + "　　" + dto.getCondOnerNm(), 
				selectFont(FONT_SZ10))); //
		cell.setBorderWidth(0f);
		cell.setColspan(5);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(hankaku2zenkaku(dto.getCondTaishoTenpoCd()) + "　　" + dto.getCondTaishoTenpoNm(), 
				selectFont(FONT_SZ10))); //頁
		cell.setBorderWidth(0f);
		cell.setColspan(3);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("売上高", selectFont(FONT_SZ10))); //頁
		cell.setBorderWidth(0f);
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase(numFmtdgt0.format(dto.getUriageDakaInfo()) + "　円", selectFont(FONT_SZ10))); //頁
		cell.setBorderWidth(0f);
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		//空行
		cell = new PdfPCell(new Phrase("　"));
		cell.setBorderWidth(0f);
		cell.setColspan(5);
		table.addCell(cell);
		return table;
	}
	
	/**
	 * 	ヘッダテーブルを生成する
	 * 
	 * @param tmpHeaderList　ヘッダ情報リスト
	 * @param pageNo　ページ番号
	 * @return PdfPTable
	 * @throws DocumentException
	 * @throws IOException
	 */
	private PdfPTable getHeaderObj(String bunruiNm, boolean pgKbn)
			throws DocumentException, IOException {
		//タイトル部　テーブル作成
        PdfPTable table = new PdfPTable(TBL_DETAIL_WIDTH.length);
		table.setWidths(TBL_DETAIL_WIDTH);
		table.setWidthPercentage(100f);
		PdfPCell cell = null;
		if(pgKbn) {
			cell = new PdfPCell(new Phrase(bunruiNm, selectFont(FONT_SZ10)));
			cell.setBorderWidth(0f);
			cell.setColspan(7);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
		}
		
		for(int i = 0 ; i < TBL_HEADER_NM.length; i++) {
			cell = new PdfPCell(new Phrase(TBL_HEADER_NM[i], selectFont(FONT_SZ10)));
			if(i == 0) {
	        	cell.setBorderWidthLeft(2f);
	    		cell.setBorderWidthRight(0.5f);
	    		if(pgKbn) {
	    			cell.setBorderWidthTop(2f);
	    		} else {
	    			cell.setBorderWidthTop(0.5f);
	    		}
	    		cell.setBorderWidthBottom(0.5f);
			} else if(i == TBL_HEADER_NM.length -1){
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(2f);
	    		if(pgKbn) {
	    			cell.setBorderWidthTop(2f);
	    		} else {
	    			cell.setBorderWidthTop(0.5f);
	    		}
	    		
	    		cell.setBorderWidthBottom(0.5f);
			} else {
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		if(pgKbn) {
	    			cell.setBorderWidthTop(2f);
	    		} else {
	    			cell.setBorderWidthTop(0.5f);
	    		}
	    		cell.setBorderWidthBottom(0.5f);
			}
	        cell.setBackgroundColor(new Color(192,192,192));
	        cell.setColspan(1);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(cell);
		}
        
		return table;
	}
	
	/**
	 * テーブルの明細を設定する
	 * @param totalInfoList
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	private void makeCellInfo(PdfPTable detTable, ShobetuGoukeiInfoDto shobetuInfo, String kbn, String indxKbn) 
		throws DocumentException, IOException {
		
		//商品名がない場合は出力しない
		if(!isNull(shobetuInfo.getShoNameKj())) {
			if(kbn.equals(PROC_MEISAI)) {
				PdfPCell cell = new PdfPCell(new Phrase(shobetuInfo.getShoCdJitu() + "　" +
		        		shobetuInfo.getShoNameKj(), selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(2f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		cell.setBorderWidthBottom(0.5f);
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				detTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(numFmtdgt0.format(shobetuInfo.getShoAmount()), selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		cell.setBorderWidthBottom(0.5f);
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				detTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(shobetuInfo.getNisuName(), selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		cell.setBorderWidthBottom(0.5f);
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				detTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getNohinTanka()), selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		cell.setBorderWidthBottom(0.5f);
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				detTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(numFmtdgt0.format(shobetuInfo.getKingaku()) , selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		cell.setBorderWidthBottom(0.5f);
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				detTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getKouseihi()) , selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		cell.setBorderWidthBottom(0.5f);
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				detTable.addCell(cell);
				
				cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getUriagehi()) , selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(2f);
	    		cell.setBorderWidthTop(0.5f);
	    		cell.setBorderWidthBottom(0.5f);
				cell.setColspan(1);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				detTable.addCell(cell);
				
			} else {
				PdfPCell cell = new PdfPCell(new Phrase(shobetuInfo.getShoNameKj(), selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(2f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		if(indxKbn.equals(LINEKBN_END)) {
	    			cell.setBorderWidthBottom(2f);
	    		} else {
	    			cell.setBorderWidthBottom(0.5f);
	    		}
				cell.setColspan(1);
				cell.setBackgroundColor(new Color(192,192,192));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				detTable.addCell(cell);
				
	            cell = new PdfPCell(new Phrase("　"));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		if(indxKbn.equals(LINEKBN_END)) {
	    			cell.setBorderWidthBottom(2f);
	    		} else {
	    			cell.setBorderWidthBottom(0.5f);
	    		}
	            cell.setColspan(1);
	            cell.setBackgroundColor(new Color(192,192,192));
	            detTable.addCell(cell);
	
	            cell = new PdfPCell(new Phrase("　"));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		if(indxKbn.equals(LINEKBN_END)) {
	    			cell.setBorderWidthBottom(2f);
	    		} else {
	    			cell.setBorderWidthBottom(0.5f);
	    		}
	            cell.setColspan(1);
	            cell.setBackgroundColor(new Color(192,192,192));
	            detTable.addCell(cell);
	            
	            cell = new PdfPCell(new Phrase("　"));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		if(indxKbn.equals(LINEKBN_END)) {
	    			cell.setBorderWidthBottom(2f);
	    		} else {
	    			cell.setBorderWidthBottom(0.5f);
	    		}
	            cell.setColspan(1);
	            cell.setBackgroundColor(new Color(192,192,192));
	            detTable.addCell(cell);
	
	    		cell = new PdfPCell(new Phrase(numFmtdgt0.format(shobetuInfo.getKingaku()) , selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		if(indxKbn.equals(LINEKBN_END)) {
	    			cell.setBorderWidthBottom(2f);
	    		} else {
	    			cell.setBorderWidthBottom(0.5f);
	    		}
	    		cell.setColspan(1);
	    		cell.setBackgroundColor(new Color(192,192,192));
	    		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    		detTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getKouseihi()) , selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(0.5f);
	    		cell.setBorderWidthTop(0.5f);
	    		if(indxKbn.equals(LINEKBN_END)) {
	    			cell.setBorderWidthBottom(2f);
	    		} else {
	    			cell.setBorderWidthBottom(0.5f);
	    		}
	    		cell.setColspan(1);
	    		cell.setBackgroundColor(new Color(192,192,192));
	    		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    		detTable.addCell(cell);
	    		
	    		cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getUriagehi()) , selectFont(FONT_SZ10)));
	        	cell.setBorderWidthLeft(0.5f);
	    		cell.setBorderWidthRight(2f);
	    		cell.setBorderWidthTop(0.5f);
	    		if(indxKbn.equals(LINEKBN_END)) {
	    			cell.setBorderWidthBottom(2f);
	    		} else {
	    			cell.setBorderWidthBottom(0.5f);
	    		}
	    		cell.setColspan(1);
	    		cell.setBackgroundColor(new Color(192,192,192));
	    		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    		detTable.addCell(cell);
	    		
	    		if(indxKbn.equals(LINEKBN_END)) {
		            cell = new PdfPCell(new Phrase("　" , selectFont(FONT_SZ10)));
		        	cell.setBorderWidthLeft(0f);
		    		cell.setBorderWidthRight(0f);
		    		cell.setBorderWidthTop(0f);
		    		cell.setBorderWidthBottom(0f);
		            cell.setColspan(7);
		            detTable.addCell(cell);
	    		}
			}
		}
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
	
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
    private String hankaku2zenkaku(String str){
		String ret = "";
		String HANKAKU = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String ZENKAKU = "０１２３４５６７８９ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ";
		char ch;
		for (int j = 0; j < str.length() ; j++){
			ch = str.charAt(j);
			for( int i = 0; i < HANKAKU.length(); i++ ) {
				if (ch == HANKAKU.charAt(i)){
					ch = ZENKAKU.charAt(i);
				}
			}
			ret = ret + ch;
		}
		return ret;
	}
}	