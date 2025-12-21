package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.impl;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UIReserveInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UIReserveSumInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UIReserveTimeInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dto.MosChickenReserveChkBytimeDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveTotalInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.util.MosChickenReserveChkBytimeUtil;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
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
 * 時間帯別予約状況確認表PDF明細情報を取得ロジック
 * @author xlee
 */
public class GetReservePdfInfoLogicImpl implements PdfOutputLogic {

	NumericFormatter numericFormatter = new NumericFormatter();
	
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS019L03";
    
    /** ファイル名 */
    public static final String FILE_NAME = "JYOYAKU";
    
    /** 時間帯別予約状況確認表-予約情報DAO */
    private UIReserveInfoDao uiReserveInfoDao;
    
    /** 時間帯別予約状況確認表-予約時間DAO */
    private UIReserveTimeInfoDao uiReserveTimeInfoDao;
    
    /** 時間帯別予約状況確認表-合計情報DAO */
    private UIReserveSumInfoDao uiReserveSumInfoDao;    

    /**
     * 時間帯別予約状況確認表-予約情報DAOを取得します。
     * @return 時間帯別予約状況確認表-予約情報DAO
     */
    public UIReserveInfoDao getUIReserveInfoDao() {
        return uiReserveInfoDao;
    }
    /**
     * 時間帯別予約状況確認表-予約情報DAOを設定します。
     * @param uiUrikakeListDao 時間帯別予約状況確認表-予約情報DAO
     */
    public void setUIReserveInfoDao(UIReserveInfoDao uiReserveInfoDao) {
        this.uiReserveInfoDao = uiReserveInfoDao;
    }
    
    /**
     * 時間帯別予約状況確認表-予約時間DAOを取得します。
     * @return 時間帯別予約状況確認表-予約時間DAO
     */
    public UIReserveTimeInfoDao getUIReserveTimeInfoDao() {
        return uiReserveTimeInfoDao;
    }
    /**
     * 時間帯別予約状況確認表-予予約時間DAOを設定します。
     * @param uiUrikakeListDao 時間帯別予約状況確認表-予約時間DAO
     */
    public void setUIReserveTimeInfoDao(UIReserveTimeInfoDao uiReserveTimeInfoDao) {
        this.uiReserveTimeInfoDao = uiReserveTimeInfoDao;
    }
    
    /**
     * 時間帯別予約状況確認表-合計情報DAOを取得します。
     * @return 時間帯別予約状況確認表-予約情報DAO
     */
    public UIReserveSumInfoDao getUIReserveSumInfoDao() {
        return uiReserveSumInfoDao;
    }
    /**
     * 時間帯別予約状況確認表-合計情報DAOを設定します。
     * @param uiUrikakeListDao 時間帯別予約状況確認表-予約情報DAO
     */
    public void setUIReserveSumInfoDao(UIReserveSumInfoDao uiReserveSumInfoDao) {
        this.uiReserveSumInfoDao = uiReserveSumInfoDao;
    }
    
    private static final int TBL_WIDTH[] = {28, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
    // ページヘッダ部分用列幅定義
    private static final int TBL_WIDTH_HEADER[] = {6, 36, 18, 18, 16, 6};

    
	/* Documentオブジェクト設定処理 */
	public Document createDocument() {
		Document doc = new Document(PageSize.A4.rotate());
		doc.setMargins(10f, 10f, 10f, 10f);
		return doc;
	}

	/* ファイル名取得 */
	public String getFileName(final PdfOutputDto pdfOutputDto) {
		MosChickenReserveChkBytimeDto dto = (MosChickenReserveChkBytimeDto) pdfOutputDto;
		// （JYOYAKU[年月]_[店コード]_[時間単位]）
        String fileName = FILE_NAME + dto.getCondTaishoDt() + "_" + 
        	dto.getCondTenpoCd() + "_" + dto.getCondTimeUnitCd() + ".pdf";
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
		
		if(fontSize.equals(MosChickenReserveChkBytimeUtil.FONT_SZ14)) {
			//ゴシック14pt
			fontSz = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
					"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 14, Font.UNDERLINE);
		} else if(fontSize.equals(MosChickenReserveChkBytimeUtil.FONT_SZ12)) {
			//ゴシック13pt
			fontSz = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
					"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 12);
		} else if(fontSize.equals(MosChickenReserveChkBytimeUtil.FONT_SZ11)) {
			//ゴシック11pt
			fontSz = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 11);
		} else if(fontSize.equals(MosChickenReserveChkBytimeUtil.FONT_SZ10)) {
			//ゴシック10pt
			fontSz = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
				"UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10);
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
		MosChickenReserveChkBytimeDto dto = (MosChickenReserveChkBytimeDto) pdfOutputDto;
		Map totalMap = createData(dto);
		//ＰＤＦを作成
		doc = makeOutPutPdf(totalMap, doc);
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
	 * 
	 * @param dto MosChickenReserveChkBytimeDto
	 * @return List　SQLの実行結果
	 * @throws ApplicationException
	 */
	// SQL実行処理
	private Map createData(MosChickenReserveChkBytimeDto dto)
			throws ApplicationException {
        //パラメータ検査
		dto = MosChickenReserveChkBytimeUtil.chkParamValue(dto);
		
        //管理番号, 店コード, お渡し日, 会社コード
        String ckanriNo = dto.getCondTitleCd();
        String miseCd = dto.getCondTenpoCd();
        String reservDt = dto.getCondTaishoDt();
        String companyCd = dto.getCondCompanyCd();
        
        //予約情報の取得
        List reserveList = getUIReserveInfoDao().getReserveInfo(ckanriNo, miseCd, reservDt, companyCd);
        
        //予約時間の取得
        List reserveTimeList = getUIReserveTimeInfoDao().getReserveTimeInfo(ckanriNo, miseCd, reservDt, companyCd);
        
        //予約合計の取得
        List reserveSumList = getUIReserveSumInfoDao().getReserveSumInfo(ckanriNo, companyCd, miseCd, reservDt);
        
        if((reserveList == null || reserveList.size() == 0) || 
        		(reserveTimeList == null || reserveTimeList.size() == 0) ||
        		(reserveSumList == null || reserveSumList.size() == 0)){
            //存在しない時
            throw new NoResultException();
        }   	
        //totalMap[List[Map]]
        Map totalMap = MosChickenReserveChkBytimeUtil.makePdfInfoList(reserveList, reserveTimeList, reserveSumList,dto);
        return totalMap;
	}
	
	/**
	 * PDF作成
	 * --------------------
	 * [0]:ヘッダ
	 * [-[1]:商品グループ
	 * [2]～[...]明細
	 * [合計]
	 * [総合計]-]
	 * ----------------------
	 * @param totalMap
	 * @param doc
	 * @return　Document
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	private Document makeOutPutPdf(Map totalMap, final Document doc) throws DocumentException, IOException {

		//ページ開始
		int pageNo = 1;
		//以前行数を保持する
		int preLineCnt = 0;
		//タイトルを表示する判断フラグ
		boolean titlePrtKbn = false;
		
		for(int i = 0 ; i < totalMap.size(); i++) {
			List totalList = (List) totalMap.get(MosChickenReserveChkBytimeUtil.MAP_NM+i);
			
			if(preLineCnt != 0) {
				//時間帯が変わった場合
				doc.newPage();
				++pageNo;
				titlePrtKbn = false;
				preLineCnt = 0;
			}
    		//商品グループの変更判断フラグ
			boolean shohinGrpKbn = false;
			
			for(int j = 0; j < totalList.size(); j++) {
				//１次分類：商品グループ
				List totalInfoList = (List) totalList.get(j);

				//２次分類：集計グループ
				List sumGrpList = makeSumGroupList(totalInfoList);
				
				/* テーブル作成 */
				PdfPTable titleArea = new PdfPTable(13);
				titleArea.setWidths(TBL_WIDTH);
				titleArea.setWidthPercentage(100f);
				
				PdfPTable reserveChkTbl = new PdfPTable(13);
	    		reserveChkTbl.setWidths(TBL_WIDTH);
	    		reserveChkTbl.setWidthPercentage(100f);
	    		
	    		List preTitleArea = new ArrayList();
	    		
	    		//商品グループ名とテーブルの見出し部を表示するかどうかの判断フラグ
	    		boolean titKbn = false;
	    		//最初のラインが空行であれば表示しない
	    		boolean initFlg = false;
	    		
	    		shohinGrpKbn = false;
	    		
	    		//商品グループの集計グループ
	    		for(int p = 0 ; p < sumGrpList.size(); p++) {
	    			List tmpSumGrpList = (List) sumGrpList.get(p);
	    			
	    			//以前集計グループと現在集計グループのサイズを出す
	    			preLineCnt += tmpSumGrpList.size();
	    			
	    			if((p != sumGrpList.size()-1) && 
	    					((!shohinGrpKbn && preLineCnt >= MosChickenReserveChkBytimeUtil.PDF_MAX_LINE - 2) || 
	    					(preLineCnt >= MosChickenReserveChkBytimeUtil.PDF_MAX_LINE))) {
	    				//一ページの基準ラインーがを超えた場合
	    				doc.newPage();
	    				++pageNo;
	    				titlePrtKbn = false;
	    				titKbn = false;
	    				initFlg = false;
	    				preLineCnt = tmpSumGrpList.size();
	    			}	    			
					doc.setPageCount(pageNo);
					
					//各頁へタイトルを取得
					if(!titlePrtKbn) {
						doc.add(getHeaderObject((List) totalList.get(0), doc.getPageNumber()));
					}
		    		
	    			for(int row = 0 ; row < tmpSumGrpList.size(); row++) {
		    			if(tmpSumGrpList.get(row).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_SUBTOT) ||
		    					tmpSumGrpList.get(row).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_TOT)) {
		    				if(tmpSumGrpList.get(row).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_SUBTOT)) {
		    					if(initFlg) {
		    						reserveChkTbl = makeSpaceCell(reserveChkTbl, 1f, 13, selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10));
		    					}
							} else if(tmpSumGrpList.get(row).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_TOT)) {
								if(initFlg) {
									reserveChkTbl = makeSpaceCell(reserveChkTbl, 0f, 0, selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10));
								}
							}
		    			} else {
		    				initFlg = true;
			    			Map tmpTotalInfo = (Map) tmpSumGrpList.get(row);
		    				//最初の行だけ、タイトルを保持しているから
			    			if(tmpTotalInfo.containsKey(MosChickenReserveChkBytimeUtil.KBN_SUBTITLE)) {
								if(preTitleArea.size() > 0) {
									preTitleArea = new ArrayList();
									titleArea.deleteBodyRows();
								}
								titleArea = getMeisaiObject(tmpTotalInfo, row, titleArea);
								titKbn = true;
							} else if(tmpTotalInfo.containsKey(MosChickenReserveChkBytimeUtil.KBN_MEISAI)) {
								UIReserveTotalInfo meisaiInfo = (UIReserveTotalInfo) tmpTotalInfo.get(MosChickenReserveChkBytimeUtil.KBN_MEISAI);
								if((meisaiInfo.getMenuCd() == null || 
										meisaiInfo.getMenuCd().equals(""))) {
									titleArea = getMeisaiObject(tmpTotalInfo, row, titleArea);
									doc.add(titleArea);
									preTitleArea.add(titleArea);
									titKbn = true;
								} else {
									if(!titKbn) {
										titleArea = (PdfPTable)preTitleArea.get(0);
										doc.add(titleArea);
										titKbn = true;
									}
									reserveChkTbl = getMeisaiObject(tmpTotalInfo, row, reserveChkTbl);
								}
							} else {
								if(!titKbn) {
									titleArea = (PdfPTable)preTitleArea.get(0);
									doc.add(titleArea);
									titKbn = true;
								}
								reserveChkTbl = getMeisaiObject(tmpTotalInfo, row, reserveChkTbl);
							}
		    			}
						doc.add(reserveChkTbl);
						reserveChkTbl.deleteBodyRows();
					}
	    			titlePrtKbn = true;
				}
	    		shohinGrpKbn = true;
			}
		}
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
	private PdfPTable getHeaderObject(List totalInfoList, int pageNo)
			throws DocumentException, IOException {
		
		//ヘッダいすとの値を取得する		
		Map tmpHeader = (HashMap)totalInfoList.get(0);
        
		UIReserveTotalInfo uiReserveTotalInfoT = (UIReserveTotalInfo) tmpHeader.get(MosChickenReserveChkBytimeUtil.KBN_TITLE);
		
		String startTimeZn = "";
		String endTimeZn = "";
		
		//現在頁の時間帯
		for(int i = 0 ; i < totalInfoList.size(); i++) {
			if(totalInfoList.get(i).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_SUBTOT) ||
					totalInfoList.get(i).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_TOT)) {
				break;
			}
			Map tmpMenu = (HashMap)totalInfoList.get(i);
			UIReserveTotalInfo meisaiInfo = (UIReserveTotalInfo) tmpMenu.get(MosChickenReserveChkBytimeUtil.KBN_MEISAI);
			if(meisaiInfo != null) {
	        	if((meisaiInfo.getMenuCd() == null || 
	            		meisaiInfo.getMenuCd().equals(""))) {
	        		
	        		List timeZn = meisaiInfo.getTimeZn();
	        		startTimeZn = (String) timeZn.get(0);
	        		endTimeZn = (String) timeZn.get(timeZn.size()-1);
	        		if(endTimeZn.equals("")) {
	        			//最後の時間帯が空白の場合は２４時間が超えた時なので、23:00で設定する
	        			endTimeZn = MosChickenReserveChkBytimeUtil.PDF_LAST_TIME;
	        		}
	        		//時間帯の範囲：
	        		if(uiReserveTotalInfoT.getTimeUnit().equals("15") || 
	        				uiReserveTotalInfoT.getTimeUnit().equals("30")) {
	        			
	        			startTimeZn = startTimeZn.substring(0,2) + ":00";
	        			endTimeZn = ((Integer.parseInt(endTimeZn.substring(0,2)) + 1) < 10) ? 
	        					"0" + (Integer.parseInt(endTimeZn.substring(0,2)) + 1) + ":00" : 
	        						(Integer.parseInt(endTimeZn.substring(0,2)) + 1) + ":00";
	        		} else {
	        			startTimeZn = startTimeZn.substring(0,5);
	        			endTimeZn = ((Integer.parseInt(endTimeZn.substring(0,2)) + 1) < 10) ? 
	        					"0" + (Integer.parseInt(endTimeZn.substring(0,2)) + 1) + ":00" : 
	        						(Integer.parseInt(endTimeZn.substring(0,2)) + 1) + ":00";
	        		}
	        	}
	        }
		}
		//タイトル部　テーブル作成
        PdfPTable table = new PdfPTable(TBL_WIDTH_HEADER.length);
		table.setWidths(TBL_WIDTH_HEADER);
		table.setWidthPercentage(100f);
		
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(0f);
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
		cell = new PdfPCell(new Phrase("時間帯別予約確認表", selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ14))); //タイトル
		cell.setBorderWidth(0f);
		cell.setColspan(4);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("P."+pageNo, selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ11))); //頁
		cell.setBorderWidth(0f);
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		
		cell = new PdfPCell(new Phrase("作成日付:" + MosChickenReserveChkBytimeUtil.formatCurrentTime(), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ11))); //作成日付
		cell.setBorderWidth(0f);
		cell.setColspan(6);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("対象店舗："
				+uiReserveTotalInfoT.getTenpoCd()+ " "+ uiReserveTotalInfoT.getTenpoNm(), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ12))); //店舗コード・店舗名
		cell.setBorderWidth(0f);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		//YYYY年MM月DD日
		String ymdKanji = MosChickenReserveChkBytimeUtil.formatYMDKanji(uiReserveTotalInfoT.getReserveDt());
		//曜日
		String dayWeekNm = "(" + MosChickenReserveChkBytimeUtil.formatDayWeek(uiReserveTotalInfoT.getReserveDt()) + ")";
		
		cell = new PdfPCell(new Phrase(ymdKanji + dayWeekNm, selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ12))); //2006年12月24日(日)
		cell.setBorderWidth(0f);
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("時間帯："+startTimeZn+"～"+endTimeZn, selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ12))); //
		cell.setBorderWidth(0f);
		cell.setColspan(1);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		
		String tanNm = "";
		if(uiReserveTotalInfoT.getTimeUnit().equals("15") || 
				uiReserveTotalInfoT.getTimeUnit().equals("30")) {
			tanNm = uiReserveTotalInfoT.getTimeUnit() + "分";
		} else if(uiReserveTotalInfoT.getTimeUnit().equals("60")) {
			tanNm = "1時間";
		}
		cell = new PdfPCell(new Phrase("("+ tanNm+"単位表示)", selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ12))); //
		cell.setBorderWidth(0f);
		cell.setColspan(2);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.addCell(cell);
		//空行
		cell = new PdfPCell(new Phrase("　", selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10)));  
		cell.setBorderWidth(0f);
		cell.setColspan(6);
		table.addCell(cell);
		return table;
	}

	/**
	 * 明細テーブルを作成します。
	 * @param totalList
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	private PdfPTable getMeisaiObject(Map tmpTotalInfoMap, int row, PdfPTable reserveChkTbl) throws DocumentException, IOException {
		//totalListから全体データを取得する
		NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);

		PdfPCell cell = null;
        	
    	if(tmpTotalInfoMap.containsKey(MosChickenReserveChkBytimeUtil.KBN_SUBTITLE)) {
    		UIReserveTotalInfo subTltInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_SUBTITLE);
    		
    		cell = new PdfPCell(new Phrase("商品グループ："+subTltInfo.getShohinGroupNm(), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10))); //商品グループ名
    		cell.setBorderWidth(0f);
    		cell.setColspan(13);
    		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    		reserveChkTbl.addCell(cell);
    	} else if(tmpTotalInfoMap.containsKey(MosChickenReserveChkBytimeUtil.KBN_MEISAI)) {
    	
        	UIReserveTotalInfo meisaiInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_MEISAI);
    		
        	if((meisaiInfo.getMenuCd() == null || 
	            		meisaiInfo.getMenuCd().equals(""))) {
            	            	
            	for(int j = 0; j < meisaiInfo.getTimeZn().size(); j++) {
	            	//見出し:font:10 BOLD
	            	if(j == 0) {
	            		//ページが変わっても商品グループ名を表示
		        		cell = new PdfPCell(new Phrase("商品名", selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10)));
		        		cell.setBorderWidthLeft(1f);
		        		cell.setBorderWidthRight(0.5f);
		        		cell.setBorderWidthTop(1f);
		        		cell.setBorderWidthBottom(0.5f);
		        		cell.setBackgroundColor(new Color(204,255,255));
		        		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		        		reserveChkTbl.addCell(cell);
	            	}
	        		cell = new PdfPCell(new Phrase((String) meisaiInfo.getTimeZn().get(j), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10)));
	        		cell.setBorderWidthLeft(0f);
	        		if(j == meisaiInfo.getTimeZn().size()-1) {
	        			cell.setBorderWidthRight(1f);
	        		} else {
	        			cell.setBorderWidthRight(0.5f);
	        		}
	        		cell.setBorderWidthTop(1f);
	        		cell.setBorderWidthBottom(0.5f);
	        		cell.setBackgroundColor(new Color(204,255,255));
	        		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        		reserveChkTbl.addCell(cell);
	            }
            } else {	            	
        		//商品名及び実際時間帯別予約数
        		cell = new PdfPCell(new Phrase(meisaiInfo.getMenuNm(), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ11)));
        		cell.setBorderWidthLeft(1f);
        		cell.setBorderWidthRight(0.5f);
        		cell.setBorderWidthTop(0.5f);
        		cell.setBorderWidthBottom(0.5f);
        		
        		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		reserveChkTbl.addCell(cell);
        		
	            for(int j = 0; j < meisaiInfo.getTimeZn().size(); j++) {
	        		cell = new PdfPCell(new Phrase(numFmtdgt0.format(meisaiInfo.getReserveAmt().get(j)), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ11)));
	        		cell.setBorderWidthLeft(0f);
	        		if(j == meisaiInfo.getTimeZn().size()-1) {
	        			cell.setBorderWidthRight(1f);
	        		} else {
	        			cell.setBorderWidthRight(0.5f);
	        		}
	        		cell.setBorderWidthTop(0.5f);
	        		cell.setBorderWidthBottom(0.5f);
	        		
	        		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        		reserveChkTbl.addCell(cell);
	            }
            }
        } else if(tmpTotalInfoMap.containsKey(MosChickenReserveChkBytimeUtil.KBN_SUBTOTAL)) {
            // 合計であれば下へ空行
            UIReserveTotalInfo subTotInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_SUBTOTAL);
            
            if(!(subTotInfo.getMenuNm() == null || 
            		subTotInfo.getMenuNm().equals(""))) {
            	
        		cell = new PdfPCell(new Phrase(subTotInfo.getMenuNm(), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10)));
        		cell.setBorderWidthLeft(1f);
        		cell.setBorderWidthRight(0.5f);
        		cell.setBorderWidthTop(2f);
        		cell.setBorderWidthBottom(1f);
        		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		reserveChkTbl.addCell(cell);
        		
            	for(int j = 0; j < subTotInfo.getTimeZn().size(); j++) {
	        		cell = new PdfPCell(new Phrase(numFmtdgt0.format(subTotInfo.getReserveAmt().get(j)), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ11)));
	        		cell.setBorderWidthLeft(0f);
	        		if(j == subTotInfo.getTimeZn().size()-1) {
	        			cell.setBorderWidthRight(1f);
	        		} else {
	        			cell.setBorderWidthRight(0.5f);
	        		}
	        		cell.setBorderWidthTop(2f);
	        		cell.setBorderWidthBottom(1f);
	        		
	        		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        		reserveChkTbl.addCell(cell);
	        		
	            }
            }
        } else if(tmpTotalInfoMap.containsKey(MosChickenReserveChkBytimeUtil.KBN_TOTAL)) {
        	// 総合計
            UIReserveTotalInfo totInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_TOTAL);
            if(!(totInfo.getMenuNm() == null || totInfo.getMenuNm().equals(""))) { 
            	//font:10 BOLD
            	cell = new PdfPCell(new Phrase(totInfo.getMenuNm(), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ10)));
            	cell.setBorderWidthLeft(1f);
        		cell.setBorderWidthRight(0.5f);
        		cell.setBorderWidthTop(2f);
        		cell.setBorderWidthBottom(1f);
        		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        		reserveChkTbl.addCell(cell);
    		
	            for(int j = 0; j < totInfo.getTimeZn().size(); j++) {
	        		cell = new PdfPCell(new Phrase(numFmtdgt0.format(totInfo.getReserveAmt().get(j)), selectFont(MosChickenReserveChkBytimeUtil.FONT_SZ11)));
	        		cell.setBorderWidthLeft(0f);
	        		if(j == totInfo.getTimeZn().size()-1) {
	        			cell.setBorderWidthRight(1f);
	        		} else {
	        			cell.setBorderWidthRight(0.5f);
	        		}
	        		cell.setBorderWidthTop(2f);
	        		cell.setBorderWidthBottom(1f);
	        		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        		reserveChkTbl.addCell(cell);
	            }
            }
        }
		return reserveChkTbl;
	}
	
	/**
	 * テーブルの明細を設定する
	 * @param totalInfoList
	 * @return
	 */
	private List makeSumGroupList(List totalInfoList) {
		
		List retTotInfoList = new ArrayList();
		List settInfoList = new ArrayList();
		
		int lineCnt = 0;
		for(int i = 0; i < totalInfoList.size(); i++) {
			if(totalInfoList.get(i).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_SUBTOT) ||
					totalInfoList.get(i).toString().equals(MosChickenReserveChkBytimeUtil.PDF_SPACE_TOT)) {
				settInfoList.add(totalInfoList.get(i));
			} else {
				Map tmpTotalInfo  = (HashMap) totalInfoList.get(i);
				
				if(!tmpTotalInfo.containsKey(MosChickenReserveChkBytimeUtil.KBN_TITLE)) {
					
					settInfoList.add(tmpTotalInfo);
					if(tmpTotalInfo.containsKey(MosChickenReserveChkBytimeUtil.KBN_SUBTOTAL)) {
						//今までの値をセット
						retTotInfoList.add(settInfoList);
						settInfoList = new ArrayList();
					} else if(tmpTotalInfo.containsKey(MosChickenReserveChkBytimeUtil.KBN_TOTAL)) {
						//今までの値をセット
						retTotInfoList.add(settInfoList);
						settInfoList = new ArrayList();
					}
				}
			}
			if(i == totalInfoList.size()-1) {
				retTotInfoList.add(settInfoList);
			}
			lineCnt++;
		}
		return retTotInfoList;
	}
	
	/**
	 * 空白Cellを生成する
	 * 
	 * @param table
	 * @param borderWidth
	 * @param repI
	 * @param font
	 */
	private PdfPTable makeSpaceCell(PdfPTable table, float borderWidth, int spanSz, Font font) {
		
		if(spanSz == 0) {
			PdfPCell cell = new PdfPCell(new Phrase("　", font));  
    		cell.setBorderWidth(borderWidth);
    		cell.setColspan(13);
    		table.addCell(cell);
		} else {
	    	for(int y = 0; y < spanSz; y++) {
	    		PdfPCell cell = new PdfPCell(new Phrase("　", font)); 
	    		if(y == 0) {
	    			cell.setBorderWidthLeft(borderWidth);
	    		} else {
	    			cell.setBorderWidthLeft(0f);
	    		}
        		if(y == spanSz-1) {
        			cell.setBorderWidthRight(borderWidth);
        		} else {
        			cell.setBorderWidthRight(0.5f);
        		}
        		cell.setBorderWidthTop(1f);
        		cell.setBorderWidthBottom(0f);
	    		table.addCell(cell);
	    	}
		}
		return table;
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