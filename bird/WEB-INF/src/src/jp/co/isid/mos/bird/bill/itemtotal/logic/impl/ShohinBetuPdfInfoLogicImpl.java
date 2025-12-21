package jp.co.isid.mos.bird.bill.itemtotal.logic.impl;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public class ShohinBetuPdfInfoLogicImpl implements PdfOutputLogic {

    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS011L08";
    
    /**　フォントサイズ14 */
    public static final String FONT_SZ14 = "14";
    
    /** フォントサイズ10 */
    public static final String FONT_SZ10 = "10";
    
    /** 一ページにつき最大ライン数     */
    public static final int LINE_MAXCNT = 55;
    
//    public static final int LINE_CHKCNT = 43;
//    
//    /** 処理区分：明細*/
//    public static final String PROC_MEISAI = "MEISAI";
//    
//    /** 処理区分：合計*/
//    public static final String PROC_GOUKEI = "GOUKEI";
//    
//    /** 処理区分：テーブルの開始 */
//    public static final String LINEKBN_START = "START";
//    
//    /** 処理区分：テーブルの終了 */
//    public static final String LINEKBN_END = "END";
    
    /** 処理区分：ヘッダテーブルのセールサイズ */
    private static final int TBL_HEADER_WIDTH[] = {15, 10, 40, 10, 15};
    
    /** 処理区分：明細テーブルのセールサイズ */
    private static final int TBL_DETAIL_WIDTH[] = {52, 7, 11, 11, 12, 8, 8};
    
    /** 数字フォマット */
    private static NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
    
    /** 数字フォマット */
    private static NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);
    
    /** ヘッダ名 */
    private static final String TBL_HEADER_NM[] = {"商　品","数量","単位","単価","金額","構成比","売上比"};
    
    /** 総合計分類名 */
//    private static final String TBL_BUNRUI_NM = "総合計";
    
    /** ファイル名 */
    public static final String FILE_NAME = "SHOHINBETU";
    
    /** 判断フラグ 1:明細分類名称2:テーブルヘッダー3:合計 4:空白行 5:明細*/
    private static final String MIESAIBUNRUI_FLG = "1"; 
    private static final String TABLE_HEADER_FLG = "2"; 
    private static final String GOUKEI_FLG = "3"; 
    private static final String KUHAKU_FLG = "4"; 
    private static final String MEISAI_FLG = "5"; 
    
    /** 請求書分類フラグ 1:課税/非課税商品合計 2:合計*/
    private static final String KAZEIGOUKEI = "1";
    private static final String GOUKEI = "2";
    
    /** 明細有り無し判断 1:明細あり2:明細なし*/
    private static final String MEISAI_ARI = "1";
    private static final String MEISAI_NASI = "2";
    
    /** 総合計判断*/
    private static final String SOUGOUKEI_CODE = "1";
    
    
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
        String fileName = FILE_NAME + "_" + dto.getResultTaishoKikanCd() + "_" + 
            dto.getResultTaishoTenpoCd() + ".pdf";
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

        List basicList  = tmpShoList(dto.getShohinbetuInfoList());
        List createBasicList  = createBasicList(basicList);
        List shohinBetuInfoList = createList(createBasicList);
        
        //List [List [ShobetuGoukeiInfoDto]]
        for(int i = 0; i < shohinBetuInfoList.size(); i++) {
            ShobetuGoukeiInfoDto shobetuInfo = (ShobetuGoukeiInfoDto) shohinBetuInfoList.get(i);
            ShobetuGoukeiInfoDto shobetuInfoBefore = new ShobetuGoukeiInfoDto();  
            String seikyuKbn = shobetuInfo.getSeikyusyoBunrui();
            if(SOUGOUKEI_CODE.equals(shobetuInfo.getShoCdJitu())){
                seikyuKbn = SOUGOUKEI_CODE;
            }
            if(i != 0 && i != 1){
                shobetuInfoBefore = (ShobetuGoukeiInfoDto) shohinBetuInfoList.get(i-2);
            }
            
            //タイトル表示
            if(shobetuInfo.getLineCnt() == 1){
                if(i != 0){
                    doc.newPage();
                }
                doc.add(getTitleObj(dto, shobetuInfo.getPageCnt()));
            }
            if(shobetuInfo.getHandanFlg().equals(MIESAIBUNRUI_FLG)){
                //分類明細                
                doc.add(getBunruiObj(shobetuInfo.getSeBnrName()));
            }else if(shobetuInfo.getHandanFlg().equals(TABLE_HEADER_FLG)){
                //テーブルヘッダー
                doc.add(getHeaderObj(shobetuInfo.getSeikyusyoBunrui(),shobetuInfoBefore.getSeikyusyoBunrui(),shobetuInfo.getSeikyuBunruiSeq(),shobetuInfoBefore.getSeikyuBunruiSeq()));
            }else if(shobetuInfo.getHandanFlg().equals(MEISAI_FLG)){
                //明細
                doc.add(makeCellInfo(shobetuInfo,shobetuInfo.getHandanFlg(),seikyuKbn));
            }else if(shobetuInfo.getHandanFlg().equals(KUHAKU_FLG)){
                PdfPTable detTable = new PdfPTable(TBL_HEADER_WIDTH.length);
                PdfPCell cell = new PdfPCell(new Phrase("　" , selectFont(FONT_SZ10)));
                detTable.setWidthPercentage(100f);
                cell.setBorderWidthLeft(0f);
                cell.setBorderWidthRight(0f);
                cell.setBorderWidthTop(0f);
                cell.setBorderWidthBottom(0f);
                cell.setColspan(7);
                detTable.addCell(cell);
                doc.add(detTable);

            }
            if(shobetuInfo.getLineCnt() == LINE_MAXCNT || i-1 == shohinBetuInfoList.size()){
            }
                
        }
        return doc;
    }
    /**
     * 
     * @param shohinbetuInfoList
     * @param basicList
     * @return
     */
    private List tmpShoList(List shohinbetuInfoList) {
        int listSize = shohinbetuInfoList.size();
        List tmpBasicList = new ArrayList();
        
        for(int j = 0; listSize > j ; j++) {
            List tmpShohinBetuInfoLst = (List) shohinbetuInfoList.get(j);
            String strBunruiSeq = "";
            for(int k = 0 ; tmpShohinBetuInfoLst.size()>k; k++) {
                ShobetuGoukeiInfoDto shobetuInfo = (ShobetuGoukeiInfoDto) tmpShohinBetuInfoLst.get(k);
                if(k==0){
                    strBunruiSeq = shobetuInfo.getSeikyuBunruiSeq(); 
                }
                if("".equals(shobetuInfo.getShoCdJitu())){
                    shobetuInfo.setMeisaiNoFlg(MEISAI_NASI);
                }else{
                    shobetuInfo.setHandanFlg(MEISAI_FLG);
                }
                shobetuInfo.setSeikyuBunruiSeq(strBunruiSeq);
                shobetuInfo.setHandanFlg(MEISAI_FLG);
                tmpBasicList.add(shobetuInfo);
            }
        }
        return tmpBasicList;
    }

    /**
     * 基本リスト作成
     * @param shohinBetuInfoList
     * @return
     */
    private List createBasicList(List shohinBetuInfoList) {
        int listSize = shohinBetuInfoList.size();
        List tmpShohinBetuInfoLst = new ArrayList();
        ShobetuGoukeiInfoDto shobetuGoukeiInfoBeforeDto = null;
        for(int i = 0; listSize > i; i++) {
            ShobetuGoukeiInfoDto shobetuGoukeiInfoDto = (ShobetuGoukeiInfoDto)shohinBetuInfoList.get(i);
            shobetuGoukeiInfoDto.setHandanFlg(MEISAI_FLG);
            if("".equals(shobetuGoukeiInfoDto.getShoCdJitu())){
                //明細無し
                shobetuGoukeiInfoDto.setMeisaiNoFlg(MEISAI_NASI);
            }else{
                shobetuGoukeiInfoDto.setMeisaiNoFlg(MEISAI_ARI);
            }
            if(i != 0){
                shobetuGoukeiInfoBeforeDto = (ShobetuGoukeiInfoDto)shohinBetuInfoList.get(i-1);
            }
            if(i == 0 || (i != 0 && shobetuGoukeiInfoBeforeDto.getSeikyusyoBunrui().equals(GOUKEI))){
                for(int j=0; 2>j;j++){
                    ShobetuGoukeiInfoDto dto = new ShobetuGoukeiInfoDto();
                    dto.setSeBnrName(shobetuGoukeiInfoDto.getSeBnrName());
                    dto.setSeikyusyoBunrui(shobetuGoukeiInfoDto.getSeikyusyoBunrui());
                    if(j==0){
                        dto.setHandanFlg(MIESAIBUNRUI_FLG);
                    }else{
                        dto.setHandanFlg(TABLE_HEADER_FLG);
                    }
                    if("".equals(shobetuGoukeiInfoDto.getShoCdJitu())){
                        //明細無し
                        dto.setMeisaiNoFlg(MEISAI_NASI);
                    }else{
                        dto.setMeisaiNoFlg(MEISAI_ARI);
                    }
                    dto.setShoCdJitu(shobetuGoukeiInfoDto.getShoCdJitu());
                    dto.setSeikyuBunruiSeq(shobetuGoukeiInfoDto.getSeikyuBunruiSeq());
                    tmpShohinBetuInfoLst.add(dto);
                }
            }
            tmpShohinBetuInfoLst.add(shobetuGoukeiInfoDto);
            if(i != listSize-1 && shobetuGoukeiInfoDto.getSeikyusyoBunrui().equals(GOUKEI)){
                ShobetuGoukeiInfoDto dto = new ShobetuGoukeiInfoDto();
                dto.setSeikyusyoBunrui(shobetuGoukeiInfoDto.getSeikyusyoBunrui());
                dto.setSeikyuBunruiSeq(shobetuGoukeiInfoDto.getSeikyuBunruiSeq());
                if("".equals(shobetuGoukeiInfoDto.getShoCdJitu())){
                    //明細無し
                    dto.setMeisaiNoFlg(MEISAI_NASI);
                }else{
                    dto.setMeisaiNoFlg(MEISAI_ARI);
                }
                dto.setHandanFlg(KUHAKU_FLG);
                tmpShohinBetuInfoLst.add(dto);
            }
        }        
        return tmpShohinBetuInfoLst;
    }


    /**
     * 改ページ処理
     * @param shohinbetuInfoList
     * @return
     */
    private List createList(List shohinbetuInfoList) {
        int lineCnt = 1;
        int pageCnt = 1;
        int listSize = shohinbetuInfoList.size();
        List tmpshohinbetuList = new ArrayList();
        for(int i = 0; listSize > i ; i++) {
            boolean kaiGyouFlg = false;
            ShobetuGoukeiInfoDto shobetuInfo = (ShobetuGoukeiInfoDto) shohinbetuInfoList.get(i);
            if(lineCnt > LINE_MAXCNT - 5){
                //明細無し
                if((SOUGOUKEI_CODE.equals(shobetuInfo.getShoCdJitu()) && shobetuInfo.getHandanFlg().equals(MIESAIBUNRUI_FLG)) 
                    || ("".equals(shobetuInfo.getShoCdJitu()) && shobetuInfo.isMeisaiFlg() && shobetuInfo.getHandanFlg().equals(MIESAIBUNRUI_FLG))){
                    for(int j=lineCnt;LINE_MAXCNT>j+1;j++){
                        ShobetuGoukeiInfoDto kuhakuInfo = new ShobetuGoukeiInfoDto();
                        kuhakuInfo.setHandanFlg(KUHAKU_FLG);
                        shobetuInfo.setLineCnt(lineCnt);
                        shobetuInfo.setSeikyusyoBunrui(shobetuInfo.getSeikyusyoBunrui());
                        tmpshohinbetuList.add(kuhakuInfo);
                        lineCnt++;
                    }
                    i--;
                    lineCnt = LINE_MAXCNT;
                    kaiGyouFlg = true;
                }else{
                    if(lineCnt > LINE_MAXCNT - 3){
                        if(!shobetuInfo.getSeikyusyoBunrui().equals(GOUKEI_FLG)){
                            ShobetuGoukeiInfoDto shobetuInfoNext = new ShobetuGoukeiInfoDto();
                            if(listSize-1 != i){
                                shobetuInfoNext = (ShobetuGoukeiInfoDto) shohinbetuInfoList.get(i+1);
                            }
                            if(shobetuInfo.getSeikyusyoBunrui().equals(KAZEIGOUKEI)
                                    && shobetuInfoNext.getSeikyusyoBunrui().equals(KAZEIGOUKEI)){
                                for(int j=lineCnt;LINE_MAXCNT>j+1;j++){
                                    ShobetuGoukeiInfoDto kuhakuInfo = new ShobetuGoukeiInfoDto();
                                    shobetuInfo.setLineCnt(lineCnt);
                                    shobetuInfo.setSeikyusyoBunrui(shobetuInfo.getSeikyusyoBunrui());
                                    kuhakuInfo.setHandanFlg(KUHAKU_FLG);
                                    tmpshohinbetuList.add(kuhakuInfo);
                                    lineCnt++;
                                }
                                i--;
                                lineCnt = LINE_MAXCNT;
                                kaiGyouFlg = true;
                            }
                            
                        }else if(lineCnt == LINE_MAXCNT && shobetuInfo.getSeikyusyoBunrui().equals(GOUKEI)){
                            for(int j=lineCnt;LINE_MAXCNT>j+1;j++){
                                ShobetuGoukeiInfoDto kuhakuInfo = new ShobetuGoukeiInfoDto();
                                kuhakuInfo.setHandanFlg(KUHAKU_FLG);
                                shobetuInfo.setSeikyusyoBunrui(shobetuInfo.getSeikyusyoBunrui());
                                kuhakuInfo.setLineCnt(lineCnt);
                                lineCnt++;
                                tmpshohinbetuList.add(kuhakuInfo);
                            }
                            i--;
                            lineCnt = LINE_MAXCNT;
                            kaiGyouFlg = true;
                        }
                        if(lineCnt > LINE_MAXCNT - 2 && shobetuInfo.getHandanFlg().equals(MIESAIBUNRUI_FLG)){
                            for(int j=lineCnt;LINE_MAXCNT>j+1;j++){
                                ShobetuGoukeiInfoDto kuhakuInfo = new ShobetuGoukeiInfoDto();
                                kuhakuInfo.setHandanFlg(KUHAKU_FLG);
                                shobetuInfo.setSeikyusyoBunrui(shobetuInfo.getSeikyusyoBunrui());
                                kuhakuInfo.setLineCnt(lineCnt);
                                lineCnt++;
                                tmpshohinbetuList.add(kuhakuInfo);
                            }
                            i--;
                            lineCnt = LINE_MAXCNT;
                            kaiGyouFlg = true;
                        }
                    }
                }                
            }
            if(!kaiGyouFlg && !("".equals(shobetuInfo.getShoCdJitu()) && shobetuInfo.getHandanFlg().equals(MEISAI_FLG) && shobetuInfo.getSeikyusyoBunrui().length() == 3)){
                shobetuInfo.setSeikyusyoBunrui(shobetuInfo.getSeikyusyoBunrui());
                shobetuInfo.setPageCnt(pageCnt);
                shobetuInfo.setLineCnt(lineCnt);
                tmpshohinbetuList.add(shobetuInfo);
            }
            if(lineCnt == LINE_MAXCNT){
                pageCnt++;
                lineCnt=0;
            }
            if(lineCnt == 0 || (!kaiGyouFlg && !("".equals(shobetuInfo.getShoCdJitu()) && shobetuInfo.getHandanFlg().equals(MEISAI_FLG) && shobetuInfo.getSeikyusyoBunrui().length() == 3))){
                lineCnt++;
            }
            if(lineCnt == 1 && listSize-1 != i){
                if(shobetuInfo.getHandanFlg().equals(MIESAIBUNRUI_FLG)){
                    shobetuInfo.setPageCnt(pageCnt);
                    shobetuInfo.setLineCnt(lineCnt);
                    lineCnt++;
                    tmpshohinbetuList.add(shobetuInfo);
                    i++;
                }else{
                    String handanFlg = "";
                    if(i != listSize-1){
                        ShobetuGoukeiInfoDto shobetuInfoBefore = (ShobetuGoukeiInfoDto) shohinbetuInfoList.get(i+1);
                        handanFlg = shobetuInfoBefore.getHandanFlg();
                    }
                    if(!handanFlg.equals(MIESAIBUNRUI_FLG)){
                        ShobetuGoukeiInfoDto newPageInfo = new ShobetuGoukeiInfoDto();
                        newPageInfo.setHandanFlg(TABLE_HEADER_FLG);
                        newPageInfo.setSeikyusyoBunrui(shobetuInfo.getSeikyusyoBunrui());
                        newPageInfo.setSeikyuBunruiSeq(shobetuInfo.getSeikyuBunruiSeq());
                        newPageInfo.setPageCnt(pageCnt);
                        newPageInfo.setLineCnt(lineCnt);
                        lineCnt++;
                        tmpshohinbetuList.add(newPageInfo);
                    }
                }
            }
        }
        return tmpshohinbetuList;
    }
    /**
     * 分類名称を生成する
     */
    private PdfPTable getBunruiObj(String bnrName)
    throws DocumentException, IOException {
        
        PdfPTable table = new PdfPTable(TBL_DETAIL_WIDTH.length);
        table.setWidths(TBL_DETAIL_WIDTH);
        table.setWidthPercentage(100f);
        PdfPCell cell = null;
        cell = new PdfPCell(new Phrase(bnrName, selectFont(FONT_SZ10)));
        cell.setBorderWidth(0f);
        cell.setColspan(7);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        return table;
    }
    
    
    /**
     *  ヘッダテーブルを生成する
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
        
        //mod 2007.01.24　李
        cell = new PdfPCell(new Phrase("商品別合計　("+ dto.getResultTaishoKikanCd().substring(0,4) + "年"
                + dto.getResultTaishoKikanCd().substring(4,6) + "月)", selectFont(FONT_SZ14))); //タイトル
        cell.setBorderWidth(0f);
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(pageNo + "頁", selectFont(FONT_SZ10))); //頁
        cell.setBorderWidth(0f);
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);
        
        //mod 2007.01.24　李
        cell = new PdfPCell(new Phrase(hankaku2zenkaku(dto.getResultOnerCd()) + "　　" + dto.getCondOnerNm(), 
                selectFont(FONT_SZ10))); //
        cell.setBorderWidth(0f);
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        
        //mod 2007.01.24　李        
        cell = new PdfPCell(new Phrase(hankaku2zenkaku(dto.getResultTaishoTenpoCd()) + "　　" + dto.getCondTaishoTenpoNm(), 
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
     *  ヘッダテーブルを生成する
     * 
     * @param tmpHeaderList　ヘッダ情報リスト
     * @param pageNo　ページ番号
     * @return PdfPTable
     * @throws DocumentException
     * @throws IOException
     */
    private PdfPTable getHeaderObj(String bunruiNo,String bunruiNoBefore,String seikyuSeqNo,String seikyuSeqNoBefore)
            throws DocumentException, IOException {
        //タイトル部　テーブル作成
        PdfPTable table = new PdfPTable(TBL_DETAIL_WIDTH.length);
        table.setWidths(TBL_DETAIL_WIDTH);
        table.setWidthPercentage(100f);
        PdfPCell cell = null;
        
        for(int i = 0 ; i < TBL_HEADER_NM.length; i++) {
            cell = new PdfPCell(new Phrase(TBL_HEADER_NM[i], selectFont(FONT_SZ10)));
            cell.setPaddingBottom(1.5f);
            cell.setPaddingTop(1.5f);
            if(i == 0) {
                cell.setBorderWidthLeft(2f);
                cell.setBorderWidthRight(0.5f);
                cell.setBorderWidthBottom(0.5f);
            } else if(i == TBL_HEADER_NM.length -1){
                cell.setBorderWidthLeft(0.5f);
                cell.setBorderWidthRight(2f);
                cell.setBorderWidthBottom(0.5f);
            } else {
                cell.setBorderWidthLeft(0.5f);
                cell.setBorderWidthRight(0.5f);
                cell.setBorderWidthBottom(0.5f);
            }
            if((seikyuSeqNo == null|| !seikyuSeqNo.equals(seikyuSeqNoBefore))){
                cell.setBorderWidthTop(2f);
            }else{
                cell.setBorderWidthTop(0.5f);
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
    private PdfPTable makeCellInfo(ShobetuGoukeiInfoDto shobetuInfo, String kbn, String indxKbn) 
        throws DocumentException, IOException {
        PdfPTable detTable = new PdfPTable(TBL_DETAIL_WIDTH.length);
        detTable.setWidths(TBL_DETAIL_WIDTH);
        detTable.setWidthPercentage(100f);

        PdfPCell cell = null;
        //商品名
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell = new PdfPCell(new Phrase(shobetuInfo.getShoNameKj(), selectFont(FONT_SZ10)));
        }else{
            cell = new PdfPCell(new Phrase(shobetuInfo.getShoCdJitu() + "    " + shobetuInfo.getShoNameKj(), selectFont(FONT_SZ10)));        
        }
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setPaddingBottom(3.5f);
        }else{
            cell.setPaddingBottom(1.5f);
        }
        cell.setPaddingLeft(5f);
        cell.setPaddingRight(0f);
        cell.setPaddingTop(1.5f);

        cell.setBorderWidthLeft(2f);
        cell.setBorderWidthRight(0.5f);
        cell.setBorderWidthTop(0.5f);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setBorderWidthBottom(2f);
        } else {
            cell.setBorderWidthBottom(0.5f);
        }
        cell.setColspan(1);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell.setBackgroundColor(new Color(192,192,192));
        }
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        detTable.addCell(cell);
        //数量
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell = new PdfPCell(new Phrase("　" , selectFont(FONT_SZ10)));
        }else{
            cell = new PdfPCell(new Phrase(numFmtdgt0.format(shobetuInfo.getShoAmount()),selectFont(FONT_SZ10)));
        }
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setPaddingBottom(3.5f);
        }else{
            cell.setPaddingBottom(1.5f);
        }
        cell.setPaddingLeft(5f);
        cell.setPaddingRight(0f);
        cell.setPaddingTop(1.5f);

        cell.setBorderWidthLeft(0.5f);
        cell.setBorderWidthRight(0.5f);
        cell.setBorderWidthTop(0.5f);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setBorderWidthBottom(2f);
        } else {
            cell.setBorderWidthBottom(0.5f);
        }
        cell.setColspan(1);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell.setBackgroundColor(new Color(192,192,192));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        detTable.addCell(cell);
        //単位
        cell = new PdfPCell(new Phrase(shobetuInfo.getNisuName(), selectFont(FONT_SZ10)));
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setPaddingBottom(3.5f);
        }else{
            cell.setPaddingBottom(1.5f);
        }
        cell.setPaddingLeft(5f);
        cell.setPaddingRight(0f);
        cell.setPaddingTop(1.5f);

        cell.setBorderWidthLeft(0.5f);
        cell.setBorderWidthRight(0.5f);
        cell.setBorderWidthTop(0.5f);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setBorderWidthBottom(2f);
        } else {
            cell.setBorderWidthBottom(0.5f);
        }
        cell.setColspan(1);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell.setBackgroundColor(new Color(192,192,192));
        }
        detTable.addCell(cell);
        //単価
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell = new PdfPCell(new Phrase("　" , selectFont(FONT_SZ10)));
        }else{
            cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getNohinTanka()),selectFont(FONT_SZ10)));
        }
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setPaddingBottom(3.5f);
        }else{
            cell.setPaddingBottom(1.5f);
        }
        cell.setPaddingLeft(5f);
        cell.setPaddingRight(0f);
        cell.setPaddingTop(1.5f);

        cell.setBorderWidthLeft(0.5f);
        cell.setBorderWidthRight(0.5f);
        cell.setBorderWidthTop(0.5f);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setBorderWidthBottom(2f);
        } else {
            cell.setBorderWidthBottom(0.5f);
        }
        cell.setColspan(1);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell.setBackgroundColor(new Color(192,192,192));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        detTable.addCell(cell);
        
        //金額
        cell = new PdfPCell(new Phrase(numFmtdgt0.format(shobetuInfo.getKingaku()) , selectFont(FONT_SZ10)));
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setPaddingBottom(3.5f);
        }else{
            cell.setPaddingBottom(1.5f);
        }
        cell.setPaddingLeft(5f);
        cell.setPaddingRight(0f);
        cell.setPaddingTop(1.5f);

        cell.setBorderWidthLeft(0.5f);
        cell.setBorderWidthRight(0.5f);
        cell.setBorderWidthTop(0.5f);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setBorderWidthBottom(2f);
        } else {
            cell.setBorderWidthBottom(0.5f);
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);

        cell.setColspan(1);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell.setBackgroundColor(new Color(192,192,192));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        detTable.addCell(cell);
        //構成比
        cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getKouseihi()) , selectFont(FONT_SZ10)));
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setPaddingBottom(3.5f);
        }else{
            cell.setPaddingBottom(1.5f);
        }
        cell.setPaddingLeft(5f);
        cell.setPaddingRight(0f);
        cell.setPaddingTop(1.5f);

        cell.setBorderWidthLeft(0.5f);
        cell.setBorderWidthRight(0.5f);
        cell.setBorderWidthTop(0.5f);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setBorderWidthBottom(2f);
        } else {
            cell.setBorderWidthBottom(0.5f);
        }
        cell.setColspan(1);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell.setBackgroundColor(new Color(192,192,192));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        detTable.addCell(cell);
        //売上比
        cell = new PdfPCell(new Phrase(numFmtdgt2.format(shobetuInfo.getUriagehi()) , selectFont(FONT_SZ10)));
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setPaddingBottom(3.5f);
        }else{
            cell.setPaddingBottom(1.5f);
        }
        cell.setPaddingTop(1.5f);

        cell.setBorderWidthLeft(0.5f);
        cell.setBorderWidthRight(2f);
        cell.setBorderWidthTop(0.5f);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals("")) {
            cell.setBorderWidthBottom(2f);
        } else {
            cell.setBorderWidthBottom(0.5f);
        }
        cell.setColspan(1);
        if(indxKbn.equals(GOUKEI) || indxKbn.equals(KAZEIGOUKEI) || indxKbn.equals("")) {
            cell.setBackgroundColor(new Color(192,192,192));
        }
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        detTable.addCell(cell);
        
        if(kbn.equals(KUHAKU_FLG)) {
            cell = new PdfPCell(new Phrase("　" , selectFont(FONT_SZ10)));
            cell.setColspan(7);
            detTable.addCell(cell);
        }
        return detTable;
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