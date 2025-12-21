/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic.impl;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

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

/**
 * 予約販売確認表 BタイプPDFファイル生成ロジッククラス
 *
 * @author xkinu
 *
 */
public class PdfBTypeLogicImpl implements PdfOutputLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS020L06";
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
    /**
     *
     * HEADERの各セル幅パーセンテージ値 (5%, 12%, 12%, 4%, 4%, 4%, 15%)
     */
    private static final int PDF_HEADER_CELL_WIDTH_SIZE[] = {5, 12, 4, 8, 4, 4, 15};
    /**
     *
     * 確認表データ表示テーブルの各セル項目名称値
     */
    private static final String LIST_HEADER_CELL_VALUES[]
         = {"お渡し", "No.備考"
    	, "チキン", "５Ｐ"
    	, "１０Ｐ"
//    ,  "冷"
//    	, "ﾓｯさんS", "ﾓｯさん単"
    	, "冷５Ｐ"
//add 2019/08/08 USI張 begin
    	,"A"
    	,"B"
//del 2023/09/22 start
//    	,"C"
//del 2023/09/22 end
//add 2019/08/08 USI張 end
//    	, "ﾊﾞﾗｴﾃｨA"
//    	, "ﾊﾞﾗｴﾃｨC"
    	, "プレ", "代金", "メモ"};
    /**
     * ５メニュー用
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 250f)
     */
//    private static final float LIST_HEADER_CELL_WIDTH_SIZE[] = {36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 250f};
    /**
     * ３メニュー用
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 250f)
     */
//    private static final float LIST_HEADER_CELL_WIDTH_SIZE[] = {36f, 200f, 47.2f, 47.2f, 47.2f, 27f, 65f, 344.4f};
    /**
     * ４メニュー用
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 250f)
     */
//delete 2019/08/08 USI張 begin
//    private static final float LIST_HEADER_CELL_WIDTH_SIZE[] = {36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 297.2f};
//delete 2019/08/08 USI張 end

    /**
     * 6メニュー用
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 250f)
     */
    private static final float LIST_HEADER_CELL_WIDTH_SIZE[] = {36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 207.3f};

//add 2019/08/08 USI張 begin
    /**
     * 7メニュー用
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 250f)
     */
//    private static final float LIST_HEADER_CELL_WIDTH_SIZE[] = {36f, 200f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 47.2f, 27f, 65f, 160.1f};
//add 2019/08/08 USI張 end
    /**
     *
     * 確認表データ表示テーブルの各セル幅値 (36f, 200f, 200f, 36f, 27f, 65f, 250f)
     */
    private static final float LIST_CELL_WIDTH_SIZE[] = LIST_HEADER_CELL_WIDTH_SIZE;

    /** PDF表示対象メニューコード */
    private static final Object[] MOSCHICKEN_MENUCDS_DISP
    	= {new String[]{"220001","220005"},"220006"
    	,"220007"
//        ,"221021"
//    	,"221100","710072"
    	,"221020"
//add 2019/08/08 USI張 begin
    	,"211940"
    	,"211941"
//del 2023/09/22 start
//    	,"211945"
//del 2023/09/22 enf
//add 2019/08/08 USI張 end
    	};
    /** セル内１行の高さ */
    private static final float MAX_ROW_CNT_IN_CELL = 10.076923f;
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
        String fileName = "YOYAKUB_"+ formatter.format(String.valueOf(dto.getReserveDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD) +"_"+ dto.getMiseCd()  + ".pdf";
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
	 * @param listReseavData モスチキン予約情報データ
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
    private PdfPTable createPdf(MosChickenRefConfListDto dto,Document doc, float documentHeight,List listReseavData) throws DocumentException, IOException {
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
                detaileTableHeightMax = createPageHeader(doc, pageCnt, dto, _BASE_FONT);
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
            	List listMenuInfo = (List)listMenus.get(i);
            	//メモ欄設定メニューリストを作成し、取得する。
            	List listMemoInMenu = getListMemoInMenu(listMenuInfo);
                //お渡し時間別テーブルへセルデータを設定する。
                nextMenuStartRowNo = createPdfListCellArea(dataTable, i
                		, isFirst, isOuterFrame
            			, listReseavData, listMenus, font_g10, detaileTableHeightMax, nextMenuStartRowNo);

                //現在の詳細テーブルの高さ(ヘッダーは含まない)
                float nowDetaileTableHeight = documentHeight + dataTable.getTotalHeight();
                if(nowDetaileTableHeight>detaileTableHeightMax) {
                	if(documentHeight == 0
                			&& dataTable.getRows().size() == 1
                			&& listMemoInMenu.size() > 0 && nextMenuStartRowNo == 0) {
	                	//直近の追加行を削除する。ページ内に収まらないため。
	                	dataTable.deleteLastRow();
                		//上記のセル設定を削除したので、同SEQ設定メニュー開始行を元に戻す。
                		nextMenuStartRowNo = getLastStartIndex(i, listReseavData, listMenus, detaileTableHeightMax, nextMenuStartRowNo);
                    	//前ページが同じSEQデータの場合
                    	TrnReserveInfo eMiddle = MosChickenRefConfListUtil.getEmptyEntity(entity);
                    	int setEndRowNo = listMemoInMenu.size();
                    	//空の固定メニュー欄設定メニュー一覧を作成し取得する。
                    	List listDispMenu = getListDispMenu(new ArrayList());
                        String addMenuNameKj = getMemoInMenu(listMemoInMenu, nextMenuStartRowNo, setEndRowNo);
                    	if(nextMenuStartRowNo == 0) {
                    		listDispMenu = getListDispMenu(listMenuInfo);
	                    	eMiddle.setRemark(entity.getRemark());
	                    	eMiddle.setPaymentFlg(entity.getPaymentFlg());
	                    	eMiddle.setTotalMoney(entity.getTotalMoney());
	                    	eMiddle.setPremiumFlg(entity.getPremiumFlg());
                    	}
        		    	setCellData(dataTable, isFirst, isOuterFrame
        		    			, eMiddle
        		    			, listDispMenu, addMenuNameKj, font_g10);
	                    //お渡し時間別テーブルをドキュメントへ設定
	                    doc.add(dataTable);
	                    nextMenuStartRowNo = -1;
                	}else{
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
     *
     * @param doc
     * @param pageCnt
     * @param dto
     * @param bfont
     * @return
     * @throws IOException
     */
    private float createPageHeader(Document doc, int pageCnt, MosChickenRefConfListDto dto, BaseFont bfont)
    throws DocumentException, IOException
    {
    	//1ページ分のPDFヘッダーをDocumentへ設定する。
    	PdfPTable pageHeader = MosChickenRefConfListUtil.getTablePdfHeader(bfont
    			, PDF_TABLE_WIDTH, PDF_HEADER_CELL_WIDTH_SIZE
    			, "予約販売確認表B"
    			, dto, pageCnt);
        doc.add(pageHeader);

        PdfPTable header = MosChickenRefConfListUtil.getTalbeDataHeader(bfont
        		, PDF_TABLE_WIDTH, LIST_HEADER_CELL_WIDTH_SIZE, LIST_HEADER_CELL_VALUES
        		, dto);
        //データ表示テーブルのヘッダーをDocumentへ設定する。
        doc.add(header);
        //Documentへ設定された現在の高さを算出する。
        float pageHeaderHight = pageHeader.getTotalHeight();
        float headerHight = header.getTotalHeight();
        float detaileTableHeightMax = PDF_DISP_HIEGHT
        						- DOCUMENT_MARGIN_TOP
        						- pageHeaderHight - headerHight
        						- DOCUMENT_MARGIN_BOTTOM;
        return detaileTableHeightMax;
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
     * @param addMenuNameKj
     * @param font_g10
     * @throws DocumentException
     */
    private void setCellData(PdfPTable dataTable
    	  , boolean isFirst, boolean isOuterFrame
    	  , TrnReserveInfo entity, List listDispMenu, String addMenuNameKj, Font font_g10)
    throws DocumentException
    {
    	String nowHour = entity.getReserveHh()+":"+entity.getReserveMm();
        String bikou= (String)entity.getRemark();
        String premium= (String)entity.getPremiumFlg();
        String memo = (String)entity.getMemo();
        String paymentFlg = (String)entity.getPaymentFlg();
        BigDecimal totalmoney = (BigDecimal)entity.getTotalMoney();

        //お渡し時間をテーブルへ設定する。
        dataTable.addCell(getPdfPCellHour(isFirst, isOuterFrame, nowHour, font_g10));
        //備考をテーブルへ設定する。
        dataTable.addCell(getPdfPCellBikou(bikou, font_g10));
        //PDF表示対象メニューコードで設定されている予約数をテーブルへ設定する。
        for(int m=0; m<MOSCHICKEN_MENUCDS_DISP.length; m++){
        	PdfPCell cell = new PdfPCell(new Phrase((String)listDispMenu.get(m), font_g10));
        	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	cell.setVerticalAlignment(Element.ALIGN_TOP);
        	dataTable.addCell(cell);
	    }
        //プレミアム受渡フラグをテーブルへ設定する。
        dataTable.addCell(getPdfPCellPremiumFlg(premium, font_g10));
        //代金
        dataTable.addCell(getPdfPCellPaymentFlg(paymentFlg, totalmoney, font_g10));

        //PDF表示対象メニューコード以外のメニューコード情報が有る場合はメモの先頭へ追加する。
        memo = addMenuNameKj + memo;

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
     * @param font
     * @param detaileTableHeightMax
     * @param startMenuRowIndex
     * @return
     * @throws DocumentException
     */
    private int createPdfListCellArea(PdfPTable dataTable, int rowNo
    	  , boolean isFirst, boolean isOuterFrame
    	  , List listReseavData, List listMenus, Font font
    	  , float detaileTableHeightMax, int startMenuRowIndex)
    throws DocumentException
    {
    	TrnReserveInfo entity = (TrnReserveInfo)listReseavData.get(rowNo);
    	List listMenuInfo = (List)listMenus.get(rowNo);
    	boolean isMenuEnd = true;
    	//固定メニュー欄設定メニュー一覧
    	List listDispMenu = getListDispMenu(new ArrayList());
    	List listMemoInMenu = getListMemoInMenu(listMenuInfo);
    	int setEndRowNo = listMemoInMenu.size();
    	//メモ欄設定メニューリストを作成し、取得する。
        String addMenuNameKj = "";
    	if(startMenuRowIndex >=0) {
	    	//CELL内最大行数取得
	    	int maxRowCnt = MosChickenRefConfListUtil.getMaxRowCnt(detaileTableHeightMax, MAX_ROW_CNT_IN_CELL);
	    	float defaultMenuTableHeight = Float.parseFloat(String.valueOf(listMemoInMenu.size()-startMenuRowIndex))*MAX_ROW_CNT_IN_CELL;
	    	if(detaileTableHeightMax < defaultMenuTableHeight) {
	    		setEndRowNo = startMenuRowIndex+maxRowCnt;
	    		isOuterFrame = true;
	    	}
	    	isMenuEnd = listMemoInMenu.size() == setEndRowNo;
	    	//固定メニュー欄設定メニュー一覧
	    	listDispMenu = getListDispMenu(listMenuInfo);
	    	//メモ欄設定メニューリストを作成し、取得する。
	        addMenuNameKj = getMemoInMenu(listMemoInMenu, startMenuRowIndex, setEndRowNo);
    	}
        if(startMenuRowIndex==0) {
        	if(isMenuEnd) {
		    	setCellData(dataTable, isFirst, isOuterFrame
		    			, entity
		    			, listDispMenu, addMenuNameKj, font);
        	}
        	else {
            	//前ページが同じSEQデータの場合
            	TrnReserveInfo eMiddle = MosChickenRefConfListUtil.getEmptyEntity(entity);
            	eMiddle.setRemark(entity.getRemark());
            	eMiddle.setPaymentFlg(entity.getPaymentFlg());
            	eMiddle.setTotalMoney(entity.getTotalMoney());
            	eMiddle.setPremiumFlg(entity.getPremiumFlg());
		    	setCellData(dataTable, isFirst, isOuterFrame
		    			, eMiddle
		    			, listDispMenu, addMenuNameKj, font);
        	}
        }
        else {
        	//前ページが同じSEQデータの場合
        	TrnReserveInfo eMiddle = MosChickenRefConfListUtil.getEmptyEntity(entity);
        	//空の固定メニュー欄設定メニュー一覧を作成し取得する。
        	listDispMenu = getListDispMenu(new ArrayList());
        	if(isMenuEnd) {
        		eMiddle.setMemo(entity.getMemo());
        	}
    		eMiddle.setRemark("(前頁からの続き)");
	        setCellData(dataTable, isFirst, isOuterFrame
	        		, eMiddle
	        		, listDispMenu, addMenuNameKj, font);
        }
        if(isMenuEnd) {
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
     * @param font
     * @param detaileTableHeightMax
     * @param lastStartIndex
     * @return
     * @throws DocumentException
     */
    private int createLastPdfListCellArea(PdfPTable dataTable, int rowNo
    	  , List listReseavData, List listMenus, Font font
    	  , float detaileTableHeightMax
    	  , int setEndRowNo)
    throws DocumentException
    {
    	//前行も削除する。中間行の場合お渡し時間の底辺の枠が無いため。
    	dataTable.deleteLastRow();

    	//前行はFirstかの判断フラグを生成する。
    	boolean isLastRowFirst = (dataTable.getRows().size()==0);
    	boolean isOuterFrame = true;
    	List listMenuInfo = (List)listMenus.get(rowNo);
    	List listMemoInMenu = getListMemoInMenu(listMenuInfo);
    	int startIndex = 0;
    	if(1 > setEndRowNo) {
    		setEndRowNo = listMemoInMenu.size();
    	}
    	//最大行数取得
    	int maxRowCnt = MosChickenRefConfListUtil.getMaxRowCnt(detaileTableHeightMax, MAX_ROW_CNT_IN_CELL);
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
			listMemoInMenu = getListMemoInMenu(listMenuInfo);
			setEndRowNo = listMemoInMenu.size();
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
        		, listReseavData, listMenus, font, detaileTableHeightMax, nextStartIndex);
    }
    /**
     *
     * @param dataTable
     * @param rowNo
     * @param listReseavData
     * @param listMenus
     * @param font_g10
     * @param detaileTableHeightMax
     * @param setEndRowNo
     * @return
     * @throws DocumentException
     */
    private int getLastStartIndex(int rowNo
    	  , List listReseavData, List listMenus
    	  , float detaileTableHeightMax
    	  , int setEndRowNo)
    throws DocumentException
    {
    	int lastStartIndex = 0;
    	//前行はFirstかの判断フラグを生成する。
    	List listMenuInfo = (List)listMenus.get(rowNo);
    	List listMemoInMenu = getListMemoInMenu(listMenuInfo);
    	if(1 > setEndRowNo) {
    		setEndRowNo = listMemoInMenu.size();
    		if(setEndRowNo < 1) {
    			return 0;
    		}
    	}
    	//最大行数取得
    	int maxRowCnt = MosChickenRefConfListUtil.getMaxRowCnt(detaileTableHeightMax, MAX_ROW_CNT_IN_CELL);
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
     * 表示枠固定メニュー一覧
     *
     * @param listMenuInfo
     * @return
     */
    private List getListDispMenu(List listMenuInfo) {
    	List listDispMenu = new ArrayList();
        //PDF表示対象メニューコードで設定されている予約数をテーブルへ設定する。
        for(int m=0; m<MOSCHICKEN_MENUCDS_DISP.length; m++){
        	String amt = "　";
        	Object menuCd = MOSCHICKEN_MENUCDS_DISP[m];
        	int totalAmt = 0;
    		if(menuCd instanceof String[]) {
    			String[] menuCds = (String[])menuCd;
    			menuCdsFor:for(int s=0; s<menuCds.length; s++) {
    	        	for(int i=0; i<listMenuInfo.size(); i++) {
    	        		TrnMosChikenDet eMenu = (TrnMosChikenDet)listMenuInfo.get(i);
    	        		if(menuCds[s].equals(eMenu.getMenuCd())) {
    	        			totalAmt= totalAmt+Integer.valueOf(eMenu.getReserverAmt()).intValue();
    		            	continue menuCdsFor;
    	        		}
    		        }
    			}
    			if(totalAmt>0) {
    				amt = String.valueOf(totalAmt);
    			}
    		}
    		else {
	        	for(int i=0; i<listMenuInfo.size(); i++) {
	        		TrnMosChikenDet eMenu = (TrnMosChikenDet)listMenuInfo.get(i);
	        		if(MOSCHICKEN_MENUCDS_DISP[m].equals(eMenu.getMenuCd())) {
	        			amt = eMenu.getReserverAmt();
		            	break;
	        		}
		        }
    		}
        	listDispMenu.add(amt);
	    }
    	return listDispMenu;
    }
    /**
     * メモ欄追加メニュー情報取得処理
     *
     * @param listMenuInfo
     * @return
     */
    private List getListMemoInMenu(List listMenuInfo) {
        //PDF表示対象メニューコード以外のメニューコード情報が有る場合はメモの先頭へ追加する。
    	List listMemoInMenu = new ArrayList();
    	menuFor:for(int i=0; i<listMenuInfo.size(); i++) {
    		TrnMosChikenDet eMenu = (TrnMosChikenDet)listMenuInfo.get(i);
            for(int m=0; m<MOSCHICKEN_MENUCDS_DISP.length; m++){
            	Object menuCd = MOSCHICKEN_MENUCDS_DISP[m];
        		if(menuCd instanceof String[]) {
        			String[] menuCds = (String[])menuCd;
        			menuCdsFor:for(int s=0; s<menuCds.length; s++) {
    	        		if(menuCds[s].equals(eMenu.getMenuCd())) {
    		            	continue menuFor;
        		        }
        			}
        		}
        		else if(menuCd.equals(eMenu.getMenuCd())) {
	            	continue menuFor;
	    		}
            }//end of for(int m=0; m<MOSCHICKEN_MENUCDS_DISP.length; m++)
            listMemoInMenu.add(eMenu);
        }//end of for(int i=0; i<listMenuInfo.size(); i++)
    	return listMemoInMenu;
    }
    /**
     * メモ欄追加メニュー情報文字列取得処理
     *
     * @param listMenuInfo
     * @param startIndex
     * @param endRowNo
     * @return
     */
    private String getMemoInMenu(List listMenuInfo, int startIndex, int endRowNo) {
    	if(endRowNo>listMenuInfo.size()) {
    		endRowNo = listMenuInfo.size();
    	}
        //PDF表示対象メニューコード以外のメニューコード情報が有る場合はメモの先頭へ追加する。
        String addMenuNameKj = "";
    	menuFor:for(int i=startIndex; i<endRowNo; i++) {
    		TrnMosChikenDet eMenu = (TrnMosChikenDet)listMenuInfo.get(i);
            for(int m=0; m<MOSCHICKEN_MENUCDS_DISP.length; m++){
            	Object menuCd = MOSCHICKEN_MENUCDS_DISP[m];
        		if(menuCd instanceof String[]) {
        			String[] menuCds = (String[])menuCd;
        			menuCdsFor:for(int s=0; s<menuCds.length; s++) {
    	        		if(menuCds[s].equals(eMenu.getMenuCd())) {
    		            	continue menuFor;
        		        }
        			}
        		}
        		else if(menuCd.equals(eMenu.getMenuCd())) {
	            	continue menuFor;
	    		}
            }//end of for(int m=0; m<MOSCHICKEN_MENUCDS_DISP.length; m++)
            addMenuNameKj += eMenu.getMenuNameKj()+"("+eMenu.getReserverAmt()+")\n";
        }//end of for(int i=0; i<listMenuInfo.size(); i++)
    	return addMenuNameKj;
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
	 * @param mosChickenrefconflistTrnMenuReserveAmtInfoDao 設定する mosChickenrefconflistTrnMenuReserveAmtInfoDao。
	 */
	public void setMosChickenrefconflistTrnMenuReserveAmtInfoDao(
			TrnMenuReserveAmtInfoDao mosChickenrefconflistTrnMenuReserveAmtInfoDao) {
		this.mosChickenrefconflistTrnMenuReserveAmtInfoDao = mosChickenrefconflistTrnMenuReserveAmtInfoDao;
	}

}
