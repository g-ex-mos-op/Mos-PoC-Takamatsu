/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic.impl;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.common.MosChickenRefConfListUtil;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao.CodMiseListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao.TrnMenuReserveAmtInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao.TrnReserveInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListDto;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.CodMiseList;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnMosChikenDet;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnReserveInfo;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.logic.PdfOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
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
 * 予約販売確認表 AタイプPDFファイル生成ロジッククラス
 * 
 * @author xkinu
 *
 */
public class PdfATypeLogicImpl implements PdfOutputLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS020L05";
    /** モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
    /**フラグ 未：0,済：1*/
    private static final String FLG_NO = "0";
    private static final String FLG_OK = "1";
    /** １ページ分の左の余白：3f */
    private static final float DOCUMENT_MARGIN_LEFT = 3f;
    /** １ページ分の下の余白：3f */
    private static final float DOCUMENT_MARGIN_BOTTOM = 5f;
    /** １ページ分の右の余白：3f */
    private static final float DOCUMENT_MARGIN_RIGHT = 3f;
    /** １ページ分の上の余白：3f */
    private static final float DOCUMENT_MARGIN_TOP = 2f;
    /** PDF表示領域高さの値：595f */
    private static final float PDF_DISP_HIEGHT = 595f;
    /** PDFメインテーブル幅値：810f */
    private static final float PDF_TABLE_WIDTH = 814f;
    /** 詳細データ表示テーブルの行の高さ値：14f */
    private static final float PDF_DETAILE_TABLE_ROW_HEIGHT_MIN = 14f;
    /**
     * 
     * HEADERの各セル幅パーセンテージ値 (5%, 12%, 12%, 4%, 4%, 4%, 15%) 
     */
    private static final int PDF_HEADER_CELL_WIDTH_SIZE[] = {5, 12, 4, 8, 4, 4, 15};
    /**
     * 
     * 確認表データ表示テーブルの各セル項目名称値
     */
    private static final String LIST_HEADER_CELL_VALUES[] = {"お渡し", "No.備考", "お買い上げ商品", "予約数", "プレ", "代金", "メモ"};
    /**
     * 
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 200f, 36f, 27f, 65f, 250f)
     */
    private static final float LIST_HEADER_CELL_WIDTH_SIZE[] = {36f, 200f, 200f, 36f, 27f, 65f, 250f};
    /**
     * 
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 200f, 36f, 27f, 65f, 250f)
     */
    private static final float LIST_CELL_WIDTH_SIZE[] = {LIST_HEADER_CELL_WIDTH_SIZE[0]
                                                              , LIST_HEADER_CELL_WIDTH_SIZE[1]
                                                              , LIST_HEADER_CELL_WIDTH_SIZE[2]+LIST_HEADER_CELL_WIDTH_SIZE[3]
                                                              , LIST_HEADER_CELL_WIDTH_SIZE[4]
                                                              , LIST_HEADER_CELL_WIDTH_SIZE[5]
                                                              , LIST_HEADER_CELL_WIDTH_SIZE[6]};
    /**
     * 確認表データ表示テーブルのメニュー名称＆予約数セル幅値 (200f, 36f)
     */
    private static final float LIST_MENU_CELL_WIDTH_SIZE[] = {LIST_HEADER_CELL_WIDTH_SIZE[2],LIST_HEADER_CELL_WIDTH_SIZE[3]};
    
    /*Dao【モスチキン予約情報】*/
    private TrnReserveInfoDao mosChickenrefconflistTrnReserveInfoDao;
    /*Dao【モスチキン予約明細情報】*/
    private TrnMenuReserveAmtInfoDao mosChickenrefconflistTrnMenuReserveAmtInfoDao;
    /*Dao【店情報】*/
    private CodMiseListDao codMiseListDao;

	/**
	 * ドキュメント生成
	 * 
	 * A4サイズ
	 * @see jp.co.isid.mos.bird.framework.logic.PdfOutputLogic#createDocument()
	 */
	public Document createDocument() {
        Document doc = new Document(PageSize.A4.rotate());
        doc.setMargins(DOCUMENT_MARGIN_LEFT, DOCUMENT_MARGIN_BOTTOM
        		, DOCUMENT_MARGIN_RIGHT, DOCUMENT_MARGIN_TOP);
//        Rectangle rec = doc.getPageSize();
//        System.out.println("Document 幅 [ "+rec.width()+" ]");
        return doc;
	}

	/**
	 * ファイル名称取得
	 * 
	 * @see jp.co.isid.mos.bird.framework.logic.PdfOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.PdfOutputDto)
	 */
	public String getFileName(PdfOutputDto pdfOutputDto) {
        DateFormatter formatter = new DateFormatter();
        MosChickenRefConfListDto dto = (MosChickenRefConfListDto) pdfOutputDto;
        String fileName = "YOYAKUA_"+ formatter.format(String.valueOf(dto.getReserveDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD) +"_"+ dto.getMiseCd()  + ".pdf";
        return fileName;
	}

	/**
	 * データ部生成処理
	 * 
	 * @see jp.co.isid.mos.bird.framework.logic.PdfOutputLogic#setupOutputData(com.lowagie.text.Document, jp.co.isid.mos.bird.framework.dto.PdfOutputDto)
	 * 
	 */
	public Document setupOutputData(Document doc, PdfOutputDto pdfOutputDto) throws DocumentException, IOException {
		MosChickenRefConfListDto dto = (MosChickenRefConfListDto) pdfOutputDto;
		
        //1.事前条件判断処理
		validate(dto);
		
        //2.Dao【予約販売情報】検索を実行し、検索結果[[予約販売情報]]を取得する。
        DateFormatter formatter = new DateFormatter();
        //2-1.パラメーター
        String canriNo = dto.getCkanriNo();
        String miseCd = dto.getMiseCd();
        String reserveDt = formatter.format(
        		dto.getReserveDate(),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD);
        String reserveHHFrom = dto.getReserveHourFrom().substring(0,2);
        String reserveHHTo = dto.getReserveHourTo().substring(0,2);
        String premiumFlg = dto.getPremiumFlg();
        String paymentFlg = dto.getPaymentFlg();
        List listPdfInfo = getMosChickenrefconflistTrnReserveInfoDao().select(
        		canriNo, miseCd, COMPANY_CD_MOS
        		, reserveDt, reserveHHFrom, reserveHHTo
        		, premiumFlg, paymentFlg);
        //3.検索結果[[予約販売情報]]が0件の場合は、下記のExceptionを発生させる。
        if(listPdfInfo == null || listPdfInfo.size() == 0){
            throw new NotExistException("該当データ");
        }
        
        //4.Dao【店情報】店情報取得を実行し、検索結果[[店情報]]を取得する。
        List miseInfo = getMosChickenrefconflistCodMiseListDao().getMiseInfo(COMPANY_CD_MOS,dto.getMiseCd());
        //5.検索結果[[店情報]]から条件項目『対象店舗』で指定された店名称をDto店名称へ設定する。
        CodMiseList miseList = (CodMiseList)miseInfo.get(0);
        dto.setMiseNmKj(miseList.getMiseNameKj());
        
        //6.PDF作成処理を行う。
        createPdf(dto,doc,0,listPdfInfo);
        
        //7.Documentをリターンする。
        return doc;
	}

	/**
	 * ヘッダー生成処理
	 * 
	 * @see jp.co.isid.mos.bird.framework.logic.PdfOutputLogic#setupHeader(com.lowagie.text.Document, jp.co.isid.mos.bird.framework.dto.PdfOutputDto)
	 */
	public Document setupHeader(Document doc, PdfOutputDto pdfOutputDto)
			throws DocumentException {
		// TODO 自動生成されたメソッド・スタブ
        return doc;
	}

	/**
	 * フッダー生成処理
	 * 
	 * @see jp.co.isid.mos.bird.framework.logic.PdfOutputLogic#setupFooter(com.lowagie.text.Document, jp.co.isid.mos.bird.framework.dto.PdfOutputDto)
	 */
	public Document setupFooter(Document doc, PdfOutputDto pdfOutputDto)
			throws DocumentException {
		// TODO 自動生成されたメソッド・スタブ
		return doc;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.PdfOutputLogic#returnAction(java.io.ByteArrayOutputStream, javax.servlet.http.HttpServletResponse, jp.co.isid.mos.bird.framework.dto.PdfOutputDto)
	 */
	public void returnAction(ByteArrayOutputStream out,
			HttpServletResponse response, PdfOutputDto dto) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
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

	/**
	 * ページキー取得処理
	 * 
	 * @see jp.co.isid.mos.bird.framework.logic.PdfOutputLogic#getPageKey(jp.co.isid.mos.bird.framework.dto.PdfOutputDto)
	 */
	public String getPageKey(PdfOutputDto pdfOutputDto) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	/**
	 * 事前条件判断処理
	 * @param dto
	 */
    private void validate(MosChickenRefConfListDto dto) {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("予約販売確認表 画面DTO");
        }
        String reserveHH = dto.getReserveHourFrom().substring(0,2);
        String reserveMm = dto.getReserveHourTo().substring(0,2);
        if(Integer.parseInt(reserveHH) >= Integer.parseInt(reserveMm)){
            throw new NotRelevantException("時間帯FROM","時間帯TOより早い時間");
        }
    }

	/**
	 * PDF作成処理
	 * 
	 * @param dto
	 * @param doc
	 * @param documentHeight 設定済みDocumentの高さ
	 * @param listReseavData 予約販売情報データ
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
    private PdfPTable createPdf(MosChickenRefConfListDto dto,Document doc
    		, float documentHeight,List listReseavData) 
    	throws DocumentException, IOException 
    {
        BaseFont _BASE_FONT = BaseFont.createFont("HeiseiKakuGo-W5",
                "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
        //ゴシック10pt
        Font font_g10 = new Font(_BASE_FONT, 10);
        int pageCnt = 1;        

        List listMenus = getListSeqMenu(listReseavData, dto);
        float detaileTableHeightMax = 0;
        int nextMenuStartRowNo = 0;
        for(int i=0;listReseavData.size()>i;i++){
            if(documentHeight == 0){
            	//1ページ分のPDFヘッダーをDocumentへ設定する。
            	PdfPTable pageHeader = MosChickenRefConfListUtil.getTablePdfHeader(_BASE_FONT
            			, PDF_TABLE_WIDTH, PDF_HEADER_CELL_WIDTH_SIZE
            			, "予約販売確認表A"
            			, dto, pageCnt);
                doc.add(pageHeader);
                
                PdfPTable header = MosChickenRefConfListUtil.getTalbeDataHeader(_BASE_FONT
                		, PDF_TABLE_WIDTH, LIST_HEADER_CELL_WIDTH_SIZE, LIST_HEADER_CELL_VALUES
                		, dto);
                //データ表示テーブルのヘッダーをDocumentへ設定する。
                doc.add(header);
                //Documentへ設定された現在の高さを算出する。
                float pageHeaderHight = pageHeader.getTotalHeight();
                float headerHight = header.getTotalHeight();
                detaileTableHeightMax = PDF_DISP_HIEGHT
                						- DOCUMENT_MARGIN_TOP 
                						- pageHeaderHight - headerHight 
                						- DOCUMENT_MARGIN_BOTTOM;

            }
            TrnReserveInfo entity = (TrnReserveInfo)listReseavData.get(i);
        	//前行のお渡し時間
            String lastHour = "first";
            //現行のお渡し時間
            String nowHour = entity.getReserveHh()+":"+entity.getReserveMm();
            //お渡し時間別テーブル生成
            PdfPTable dataTable = new PdfPTable(LIST_CELL_WIDTH_SIZE.length);
            dataTable.setTotalWidth(PDF_TABLE_WIDTH);
            dataTable.setLockedWidth(true);
            dataTable.setWidths(LIST_CELL_WIDTH_SIZE);
            while("first".equals(lastHour) || nowHour.equals(lastHour)) {
            	
            	boolean isFirst = "first".equals(lastHour);
            	boolean isEnd = ((i+1) == listReseavData.size());
            	nowHour = entity.getReserveHh()+":"+entity.getReserveMm();
                //次行は別お渡し時間の値か判断フラグ
                boolean isNextNewHour = false;
                
                //外枠か否か判断フラグ
                boolean isOuterFrame = isEnd;
                //最終行で無い場合、下記の処理を行う。
                if(!isEnd) {
                	//次行のEntity[予約状況情報]を取得する。
                	TrnReserveInfo entityNext = (TrnReserveInfo)listReseavData.get(i+1);
	                //次行Entity[予約状況情報].お渡し時間を取得する。
	                String nextHour = entityNext.getReserveHh()+":"+entityNext.getReserveMm();
	                isNextNewHour = !nowHour.equals(nextHour);
	                isOuterFrame = isNextNewHour;
                }
                //お渡し時間別テーブルへセルデータを設定する。
                nextMenuStartRowNo = createPdfListCellArea(dataTable, i, isFirst, isOuterFrame
                		, listReseavData, listMenus, font_g10, detaileTableHeightMax, nextMenuStartRowNo);
                
                //現在の詳細テーブルの高さ(ヘッダーは含まない)
                float nowDetaileTableHeight = documentHeight + dataTable.getTotalHeight();
                if(nowDetaileTableHeight>detaileTableHeightMax) {
                	if(isFirst) {
                		//上記のセル設定を無効にするので、同SEQ設定メニュー開始行を元に戻す。
                		nextMenuStartRowNo = getLastStartIndex(i, listReseavData, listMenus, detaileTableHeightMax, nextMenuStartRowNo);
                	}else{
	                	//直近の追加行を削除する。ページ内に収まらないため。
	                	dataTable.deleteLastRow();
	                	//お渡し時間別テーブルへ外枠ありでセルデータを再度設定する。
	                	nextMenuStartRowNo = createLastPdfListCellArea(dataTable, i
	                			, listReseavData, listMenus, font_g10, detaileTableHeightMax, nextMenuStartRowNo);
	                    //お渡し時間別テーブルをドキュメントへ設定
	                    doc.add(dataTable);
                	}
                    //改ページ
                    doc.newPage();
                    documentHeight=0;
                    pageCnt++;
                    i--;
                    break;
                }
                else if(0<nextMenuStartRowNo) {
                    //お渡し時間別テーブルをドキュメントへ設定
                    doc.add(dataTable);
                	//未だ同SEQデータの出力が途中の場合
                    //改ページ
                    doc.newPage();
                    documentHeight=0;
                    pageCnt++;
                    i--;
                    break;
                }else if(isEnd || isNextNewHour) {
                    documentHeight = documentHeight + dataTable.getTotalHeight();
                    //お渡し時間別テーブルをドキュメントへ設定
                    doc.add(dataTable);
                	break;
                }
                
                i++;
                entity = (TrnReserveInfo)listReseavData.get(i);
            	//前行のお渡し時間
                lastHour = nowHour;
                //現行のお渡し時間
                nowHour = entity.getReserveHh()+":"+entity.getReserveMm();
            }
        }
        return null;
    }
    /**
     * 
     * @param listMenuInfo
     * @param startIndex
     * @param setCnt
     * @param font
     * @return
     * @throws DocumentException
     */
    private PdfPTable getPdfPTableMenu(List listMenuInfo, int startIndex, int endRowNo, Font font) throws DocumentException {
    	if(endRowNo>listMenuInfo.size()) {
    		endRowNo = listMenuInfo.size();
    	}
        /* テーブル作成 */
        // テーブルの全体定義
        PdfPTable table = new PdfPTable(LIST_MENU_CELL_WIDTH_SIZE.length);
        //テーブル幅を設定する。
        table.setTotalWidth(LIST_MENU_CELL_WIDTH_SIZE[0]+LIST_MENU_CELL_WIDTH_SIZE[1]);
        table.setLockedWidth(true);
        //テーブルの各セル幅を設定する
        table.setWidths(LIST_MENU_CELL_WIDTH_SIZE);
       
        Color backColor = new Color(255,255,255);
        //項目分のセルを作成する。
        for(int i=startIndex; i<endRowNo; i++) {
        	TrnMosChikenDet entity = (TrnMosChikenDet)listMenuInfo.get(i);
        	String menuName = entity.getMenuNameKj();
        	if(FLG_NO.equals(entity.getMasterFlg())) {
        		menuName = "★"+menuName;
        	}
        	PdfPCell cellMenuName = new PdfPCell(new Phrase(menuName, font));
        	cellMenuName.setBackgroundColor(backColor);
        	cellMenuName.setHorizontalAlignment(Element.ALIGN_LEFT);
        	cellMenuName.setVerticalAlignment(Element.ALIGN_TOP);
        	table.addCell(cellMenuName);
        	PdfPCell cellReserveAmt = new PdfPCell(new Phrase(entity.getReserverAmt(), font));
        	cellReserveAmt.setBackgroundColor(backColor);
        	cellReserveAmt.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	cellReserveAmt.setVerticalAlignment(Element.ALIGN_TOP);
        	table.addCell(cellReserveAmt);
        }
        return table;
    }
    /**
     * SEQ別メニュー情報取得処理
     * 
     * @param listReseavData
     * @param dto
     * @return [[SEQ別メニュー情報]]
     */
    private List getListSeqMenu(List listReseavData, MosChickenRefConfListDto dto) {
        List listMenu = new ArrayList();
        for(int i=0;listReseavData.size()>i;i++){
        	TrnReserveInfo entity =(TrnReserveInfo) listReseavData.get(i);
            String ckanriNo = entity.getCkanriNo();
            String companyCd = entity.getCompanyCd();
            String miseCd = entity.getMiseCd();
            int seqNo = Integer.parseInt(entity.getSeqNo());
            listMenu.add(getMosChickenrefconflistTrnMenuReserveAmtInfoDao().select(ckanriNo, companyCd, miseCd, seqNo));
        }
        return listMenu; 
    }

    /**
     * お渡し時間セル生成取得処理
     * 
     * @param isFirst
     * @param isOuterFrame
     * @param reserveHour
     * @param font_g10
     * @return
     */
    private PdfPCell getPdfPCellHour(boolean isFirst, boolean isOuterFrame, String reserveHour, Font font_g10) {
        //お渡し時間
        PdfPCell  cell = null;
        if(isFirst) {
        	cell = new PdfPCell(new Phrase(reserveHour,font_g10));
    	}
    	else {
    		cell = new PdfPCell(new Phrase("　", font_g10));
    	}
        if(isOuterFrame) {
        	cell.setBorder(6);
        }
        else {
        	cell.setBorder(4);
        }
       	cell.setVerticalAlignment(Element.ALIGN_TOP);
       	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }
    /**
     * No.備考セル生成取得処理
     * 
     * @param value
     * @param font_g10
     * @return
     */
    private PdfPCell getPdfPCellBikou(String value, Font font_g10) {
        //No.備考
        PdfPCell  cell = new PdfPCell(new Phrase(MosChickenRefConfListUtil.changeDBEnterWord(value, "\n"),font_g10));
       	cell.setBorder(6);
       	cell.setVerticalAlignment(Element.ALIGN_TOP);
       	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }
    /**
     * メモセル生成取得処理
     * 
     * @param isFirst
     * @param isOuterFrame
     * @param value
     * @param font_g10
     * @return
     */
    private PdfPCell getPdfPCellMemo(String value, Font font_g10) {
        //メモ
        PdfPCell  cell = new PdfPCell(new Phrase(MosChickenRefConfListUtil.changeDBEnterWord(value, "\n"),font_g10));
        cell.setBorder(14);
       	cell.setVerticalAlignment(Element.ALIGN_TOP);
       	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        return cell;
    }
    /**
     * 代済みフラグセル生成取得処理
     * 
     * @param value
     * @param font_g10
     * @return
     */
    private PdfPCell getPdfPCellPaymentFlg(String flg, BigDecimal value, Font font_g10) {
    	String valueName = "";
    	if(flg.equals(FLG_OK)){
    		valueName = "済";
    	}else if(flg.equals(FLG_NO)){
            NumericFormatter formatter = new NumericFormatter();
            valueName = formatter.format(String.valueOf(value),"##,###,###,##0");
            valueName += "円";
    	}
        //ﾌﾟﾚﾐｱﾑ
        PdfPCell  cell = new PdfPCell(new Phrase(valueName,font_g10));
       	cell.setBorder(6);
       	cell.setVerticalAlignment(Element.ALIGN_TOP);
       	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }
    /**
     * ﾌﾟﾚﾐｱﾑセル生成取得処理
     * 
     * @param value
     * @param font_g10
     * @return
     */
    private PdfPCell getPdfPCellPremiumFlg(String value, Font font_g10) {
    	String valueName = "";
    	if(value.equals(FLG_OK)){
    		valueName = "済";
    	}else if(value.equals(FLG_NO)){
    		valueName = "★未";
    	}
        //ﾌﾟﾚﾐｱﾑ
        PdfPCell  cell = new PdfPCell(new Phrase(valueName,font_g10));
       	cell.setBorder(6);
       	cell.setVerticalAlignment(Element.ALIGN_TOP);
       	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        return cell;
    }
    
    /**
     * 
     * @param dataTable
     * @param isFirst
     * @param isOuterFrame
     * @param entity
     * @param listMenuInfo
     * @param font_g10
     * @throws DocumentException
     */
    private void setCellData(PdfPTable dataTable
    	  , boolean isFirst, boolean isOuterFrame
    	  , TrnReserveInfo entity, PdfPTable tableMenuInfo, Font font_g10) 
    throws DocumentException 
    {
    	String nowHour = "";
        String bikou= "";
        String premium= "";
        String memo = "";
        String paymentFlg = "";
        BigDecimal totalmoney = null;
        if(entity != null) {
	    	nowHour = entity.getReserveHh()+":"+entity.getReserveMm();
	        bikou= (String)entity.getRemark();
	        premium= (String)entity.getPremiumFlg();
	        memo = (String)entity.getMemo();
	        paymentFlg = (String)entity.getPaymentFlg();
	        totalmoney = (BigDecimal)entity.getTotalMoney();
        }
        
        //お渡し時間をテーブルへ設定する。
        dataTable.addCell(getPdfPCellHour(isFirst, isOuterFrame, nowHour, font_g10));
        //備考をテーブルへ設定する。
        dataTable.addCell(getPdfPCellBikou(bikou, font_g10));
        //メニュー名称と予約数をテーブルへ設定する。
        if(tableMenuInfo != null) {
            PdfPCell  cell = new PdfPCell(tableMenuInfo);
           	cell.setPadding(0);
        	dataTable.addCell(cell);
        }
        else {
        	dataTable.addCell(new PdfPCell(new Phrase("　", font_g10)));
        }
        //プレミアム受渡フラグをテーブルへ設定する。
        dataTable.addCell(getPdfPCellPremiumFlg(premium, font_g10));
        //代金
        dataTable.addCell(getPdfPCellPaymentFlg(paymentFlg, totalmoney, font_g10));
        //メモをテーブルへ設定する。
        dataTable.addCell(getPdfPCellMemo(memo, font_g10));
        
    }
    /**
     * 
     * @param dataTable
     * @param rowNo
     * @param isFirst
     * @param isOuterFrame
     * @param listReseavData
     * @param listMenus
     * @param font_g10
     * @param detaileTableHeightMax
     * @param startMenuRowIndex
     * @return nextStartIndex 次回開始行インデックス
     * @throws DocumentException
     */
    private int createPdfListCellArea(PdfPTable dataTable, int rowNo
    	  , boolean isFirst, boolean isOuterFrame
    	  , List listReseavData, List listMenus, Font font_g10
    	  , float detaileTableHeightMax, int startMenuRowIndex) 
    throws DocumentException 
    {
    	TrnReserveInfo entity = (TrnReserveInfo)listReseavData.get(rowNo);
    	List listMenuInfo = (List)listMenus.get(rowNo);
    	int setEndRowNo = listMenuInfo.size();
    	//最大行数取得
    	int maxRowCnt = MosChickenRefConfListUtil.getMaxRowCnt(detaileTableHeightMax, PDF_DETAILE_TABLE_ROW_HEIGHT_MIN);
    	float defaultMenuTableHeight = Float.parseFloat(String.valueOf(listMenuInfo.size()-startMenuRowIndex))*PDF_DETAILE_TABLE_ROW_HEIGHT_MIN;
    	if(detaileTableHeightMax < defaultMenuTableHeight) {
    		setEndRowNo = startMenuRowIndex+maxRowCnt;
    		isOuterFrame = true;
    	}
    	//予約メニューリストテーブルを作成し、取得する。
        PdfPTable tableMenuInfo = getPdfPTableMenu(listMenuInfo, startMenuRowIndex, setEndRowNo, font_g10);
        if(startMenuRowIndex==0) {
	    	setCellData(dataTable, isFirst, isOuterFrame, entity, null, font_g10);
	        float rowHeight = dataTable.getRowHeight(dataTable.getRows().size()-1);
	        while (0<rowHeight-tableMenuInfo.getTotalHeight()) {
	        	for(int m=0; m<LIST_MENU_CELL_WIDTH_SIZE.length; m++) {
	        		tableMenuInfo.addCell(new Phrase("　", font_g10));
	        	}
	        }
	        
	        dataTable.deleteLastRow();
	        setCellData(dataTable, isFirst, isOuterFrame, entity, tableMenuInfo, font_g10);
        }
        else {
        	TrnReserveInfo eMiddle = MosChickenRefConfListUtil.getEmptyEntity(entity);
        	eMiddle.setRemark("(前頁からの続き)");
        	setCellData(dataTable, isFirst, isOuterFrame, eMiddle, tableMenuInfo, font_g10);
        }
        if(listMenuInfo.size() == setEndRowNo) {
        	//全メニューデータを設定した場合、0をリターンする。
        	return 0;
        }
        else {
        	//１つ以上のメニューデータが未設定の場合、
        	//先頭未設定メニューの行インデックスをリターンする。
        	return setEndRowNo;
        }
    }
    /**
     * 
     * @param dataTable
     * @param rowNo
     * @param listReseavData
     * @param listMenus
     * @param font_g10
     * @param detaileTableHeightMax
     * @param nextStartIndex
     * @return
     * @throws DocumentException
     */
    private int createLastPdfListCellArea(PdfPTable dataTable, int rowNo
    	  , List listReseavData, List listMenus, Font font_g10
    	  , float detaileTableHeightMax
    	  , int lastStartIndex) 
    throws DocumentException 
    {
    	//前行も削除する。中間行の場合お渡し時間の底辺の枠が無いため。
    	dataTable.deleteLastRow();

    	//前行はFirstかの判断フラグを生成する。
    	boolean isLastRowFirst = (dataTable.getRows().size()==0);
    	boolean isOuterFrame = true;
    	List listMenuInfo = (List)listMenus.get(rowNo);
    	int startIndex = 0;
		int setEndRowNo = listMenuInfo.size();
    	if(lastStartIndex>0) {
    		setEndRowNo = lastStartIndex;
    	}
    	//最大行数取得
    	int maxRowCnt = MosChickenRefConfListUtil.getMaxRowCnt(detaileTableHeightMax, PDF_DETAILE_TABLE_ROW_HEIGHT_MIN);
    	int amariRowCnt = setEndRowNo%maxRowCnt;
    	if(0==amariRowCnt) {
    		startIndex = setEndRowNo-maxRowCnt;
    	}
    	else {
    		startIndex = setEndRowNo-amariRowCnt;
    	}
    	int nextStartIndex = 0;
		//同SEQ内でのセル設定。
		if(startIndex>0) {
			nextStartIndex = startIndex -maxRowCnt;
		}
		//以下別SEQのセル設定。
		else {
			rowNo--;
	    	listMenuInfo = (List)listMenus.get(rowNo);
			setEndRowNo = listMenuInfo.size();
		    if(setEndRowNo>0) {
		    	//最大行数
		    	amariRowCnt = setEndRowNo%maxRowCnt;
		    	if(0==amariRowCnt) {
		    		nextStartIndex = setEndRowNo-maxRowCnt;
		    	}
		    	else {
		    		nextStartIndex = setEndRowNo-amariRowCnt;
		    	}
		    }
        }
		return createPdfListCellArea(dataTable, rowNo, isLastRowFirst, isOuterFrame
        		, listReseavData, listMenus, font_g10, detaileTableHeightMax, nextStartIndex);
    }
    /**
     * 
     * @param dataTable
     * @param rowNo
     * @param listReseavData
     * @param listMenus
     * @param font_g10
     * @param detaileTableHeightMax
     * @param nextStartIndex
     * @return
     * @throws DocumentException
     */
    private int getLastStartIndex(int rowNo
    	  , List listReseavData, List listMenus
    	  , float detaileTableHeightMax
    	  , int nextStartIndex) 
    throws DocumentException 
    {
    	int lastStartIndex = 0;
    	//前行はFirstかの判断フラグを生成する。
    	List listMenuInfo = (List)listMenus.get(rowNo);
		int setEndRowNo = listMenuInfo.size();
    	if(nextStartIndex>0) {
    		setEndRowNo = nextStartIndex;
    	}
    	//最大行数取得
    	int maxRowCnt = MosChickenRefConfListUtil.getMaxRowCnt(detaileTableHeightMax, PDF_DETAILE_TABLE_ROW_HEIGHT_MIN);
    	int amariRowCnt = setEndRowNo%maxRowCnt;
    	if(0==amariRowCnt) {
    		lastStartIndex = setEndRowNo-maxRowCnt;
    	}
    	else {
    		lastStartIndex = setEndRowNo-amariRowCnt;
    	}
		return lastStartIndex;
    }
	/**
	 * 店情報DAO取得処理
	 * 
	 * @return mosChickenrefconflistCodMiseListDao を戻します。
	 */
	public CodMiseListDao getMosChickenrefconflistCodMiseListDao() {
		return codMiseListDao;
	}

	/**
	 * 店情報設DAO定処理
	 * 
	 * @param mosChickenrefconflistCodMiseListDao 設定する dao。
	 */
	public void setMosChickenrefconflistCodMiseListDao(
			CodMiseListDao dao) {
		this.codMiseListDao = dao;
	}

	/**
	 * 対象SEQモスチキン予約明細情報取得Dao取得処理
	 * 
	 * @return mosChickenrefconflistTrnMenuReserveAmtInfoDao を戻します。
	 */
	public TrnMenuReserveAmtInfoDao getMosChickenrefconflistTrnMenuReserveAmtInfoDao() {
		return mosChickenrefconflistTrnMenuReserveAmtInfoDao;
	}

	/**
	 * 対象SEQモスチキン予約明細情報取得Dao設定処理
	 * 
	 * @param dao 設定する mosChickenrefconflistTrnMenuReserveAmtInfoDao。
	 */
	public void setMosChickenrefconflistTrnMenuReserveAmtInfoDao(
			TrnMenuReserveAmtInfoDao dao) {
		this.mosChickenrefconflistTrnMenuReserveAmtInfoDao = dao;
	}

	/**
	 * Dao【予約販売情報】取得処理
	 * @return mosChickenrefconflistTrnReserveInfoDao を戻します。
	 */
	public TrnReserveInfoDao getMosChickenrefconflistTrnReserveInfoDao() {
		return mosChickenrefconflistTrnReserveInfoDao;
	}

	/**
	 * Dao【予約販売情報】設定処理
	 * @param dao 設定する mosChickenrefconflistTrnReserveInfoDao。
	 */
	public void setMosChickenrefconflistTrnReserveInfoDao(
			TrnReserveInfoDao dao) {
		this.mosChickenrefconflistTrnReserveInfoDao = dao;
	}

}
