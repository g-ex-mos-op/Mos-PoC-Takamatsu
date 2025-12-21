package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic.impl;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao.CodMiseListDao;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao.TrnMosChickenDetailInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao.TrnMosChickenInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListDto;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.CodMiseList;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnMosChikenDet;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.TrnMosChikenInfo;
import jp.co.isid.mos.bird.framework.dto.PdfOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
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
 * @author inzawa
 */
public class MosChickenRefListContentConfirmPdfDLTypeALogicImpl implements PdfOutputLogic {
    /** 指定改行文字 */
    private static final String _ENTER_WORD = "`";
    /*モスチキン予約情報**/
    private TrnMosChickenInfoDao mosChickenrefconflistTrnMosChickenInfoDao;
    /*モスチキン予約明細情報**/
    private TrnMosChickenDetailInfoDao mosChickenrefconflistTrnMosChickenDetailInfoDao;
    /*店情報**/
    private CodMiseListDao codMiseListDao;
    int hourListCnt;
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS020L03";
    /*行数*/
    private static final int gyouMax = 38;
    private static final int titleGyou = 4;
    private static final int headerGyou = 1;
    private static final int initGyou = 0;
    private static final int meisaiGyou = 33;
    /**代済フラグ 未：0,済：1*/
    private static final String notPay = "0";
    private static final String okPay = "1";
    /**代済フラグ 未：0,済：1*/
    private static final String FLG_NO = "0";
    private static final String FLG_OK = "1";
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
    /*メモもしくは備考の行チェック*/
    private static final int BIKOU_LINE = 32;
    private static final int MEMO_LINE =  40;
    /*MAP用キー名称*/
    private static final String MEMO = "MEMO";
    private static final String REMARK = "BIKOU";
    private static final String SHO_NM_KJ = "SHO_NM_KJ";
    private static final String RESERVE_AMT = "RESERVE_AMT";
    private static final String HOUR = "HOUR";
    private static final String PREMIUM = "PREMIUM";
    private static final String MONEY = "MONEY";
    private static final String SEQ_NO = "SEQ_NO";
    private static final String BIKOU_ROWSPAN = "BIKOU_ROWSPAN";
    private static final String HOUR_ROWSPAN = "HOUR_ROWSPAN";
    /**
     * 確認表の表示幅値
     */
    private static final float PDF_TABLE_WIDTH_SIZE = 97f;
    /**
     * 
     * HEADERの各セル幅パーセンテージ値 (5%, 12%, 12%, 4%, 4%, 4%, 15%) 
     */
    private static final int PDF_HEADER_CELL_WIDTH_SIZE[] = {5, 12, 4, 8, 4, 4, 15};
    /**
     * HEADER表示テーブル項目数
     */
    private static final int PDF_HEADER_CELL_SIZE = PDF_HEADER_CELL_WIDTH_SIZE.length;
    /**
     * 
     * 確認表データ表示テーブルの各セル幅パーセンテージ値 (6%, 21%, 21%, 5%, 4%, 6%, 26%)
     */
    private static final int PDF_LIST_CELL_WIDTH_SIZE[] = {4, 24, 21, 4, 3, 7, 29};
    /**
     * 確認表データ表示テーブル項目数
     */
    private static final int PDF_LIST_CELL_SIZE = PDF_LIST_CELL_WIDTH_SIZE.length;
    
    
    /* Documentオブジェクト設定処理 */
    public Document createDocument() {
        Document doc = new Document(PageSize.A4.rotate());
        doc.setMargins(3f, 3f, 3f, 2f);
        return doc;
    }

    /* ファイル名取得 */
    public String getFileName(final PdfOutputDto pdfOutputDto) {
        DateFormatter formatter = new DateFormatter();
        MosChickenRefConfListDto dto = (MosChickenRefConfListDto) pdfOutputDto;
        String fileName = "YOYAKUA_"+ formatter.format(String.valueOf(dto.getReserveDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD) +"_"+ dto.getMiseCd()  + ".pdf";
        return fileName;
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
        MosChickenRefConfListDto dto = (MosChickenRefConfListDto) pdfOutputDto;
        DateFormatter formatter = new DateFormatter();
        if(Integer.parseInt(dto.getReserveHourFrom().substring(0,2)) >= Integer.parseInt(dto.getReserveHourTo().substring(0,2))){
            throw new NotRelevantException("時間帯FROM","時間帯TOより早い時間");
        }
        String premiumFlg = dto.getPremiumFlg();
        String paymentFlg = dto.getPaymentFlg();
        List listPdfInfo = getMosChickenrefconflistTrnMosChickenInfoDao().getMosChickenInfo(dto.getCkanriNo(),dto.getMiseCd(),COMPANY_CD_MOS,String.valueOf(formatter.format(String.valueOf(dto.getReserveDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD)),dto.getReserveHourFrom().substring(0,2),dto.getReserveHourTo().substring(0,2)
        		, premiumFlg, paymentFlg);
        if(listPdfInfo == null || listPdfInfo.size() == 0){
            throw new NotExistException("該当データ");
        }
        ArrayList removeList = (ArrayList) listPdfInfo;
        for(int i=0;removeList.size()>i;i++){
            TrnMosChikenInfo trnMosChikenInfo =(TrnMosChikenInfo) removeList.get(i);
            String reserveHour = trnMosChikenInfo.getReserveHh()+trnMosChikenInfo.getReserveMm();
            if(Integer.parseInt(reserveHour)>=Integer.parseInt(dto.getReserveHourTo().substring(0,2)+dto.getReserveHourTo().substring(3))){
                listPdfInfo.remove(i);
                i--;
            }
        }
        //店名称取得
        List miseInfo = getCodMiseListDao().getMiseInfo(COMPANY_CD_MOS,dto.getMiseCd());
        CodMiseList miseList = (CodMiseList)miseInfo.get(0);
        dto.setMiseNmKj(miseList.getMiseNameKj());
        int lineCnt = 0;
        
        //データ部
        getSecondObject(dto,doc,lineCnt,listPdfInfo);
        return doc;
    }
    /**
     * 時間をセット
     * @param listPdfInfo
     * @param dto
     */
    private List setHour(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetInfo,TrnMosChikenInfo revList) {
        ArrayList hourList = new ArrayList();
        int memoGyo=0;
        for(int j=0;listPdfDetInfo.size()>j;j++){
            TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
            if(!trnMosChikenDet.getReserverAmt().equals("0")){
                memoGyo++;
                hourList.add(revList.getReserveHh()+":"+revList.getReserveMm());
            }
        }
        ArrayList tempMemoList = new ArrayList();
        ArrayList tempBikouList = new ArrayList();
        tempMemoList = (ArrayList) createMemo(tempMemoList, revList.getMemo(),MEMO_LINE,true);
        int memoLinage = tempMemoList.size(); 
        tempBikouList = (ArrayList) createBikou(tempBikouList,revList.getRemark(),BIKOU_LINE,true);
        int bikouLinage = tempBikouList.size();
        if(memoLinage>memoGyo || bikouLinage>memoGyo){
              int diff = 0;
              if(memoLinage>bikouLinage){
                  diff = memoLinage;
              }else{
                  diff = bikouLinage;
              }
            for(int k=0;diff-memoGyo>k;k++){
                hourList.add(revList.getReserveHh()+":"+revList.getReserveMm());
            }
        }
        
        return hourList;

    }

    /**
     * 備考用ROWSPAN設定
     * @param listPdfInfo
     * @param dto
     */
    private List setBikouRowspanList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
        ArrayList bikouRowsapnList = new ArrayList();
        int bikouRowspanCnt = 0;
        int count = 0;
        for(int j=0;listPdfDetInfo.size()>j;j++){
            TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
            if(!trnMosChikenDet.getReserverAmt().equals("0")){
                bikouRowspanCnt++;
                count++;
            }
        }
        ArrayList tempMemoList = new ArrayList();
        ArrayList tempBikouList = new ArrayList();
        tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
        int memoLinage = tempMemoList.size(); 
        tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
        int bikouLinage = tempBikouList.size();
      if(memoLinage>count || bikouLinage>count){
            int diff = 0;
            if(memoLinage>bikouLinage){
                diff = memoLinage;
            }else{
                diff = bikouLinage;
            }
            for(int k=0;diff-count>k;k++){
                bikouRowspanCnt++;
            }
        }
        
        int rowspan = 0;
        for(int j=0;bikouRowspanCnt>j;j++){
            if(rowspan == 0){
                bikouRowsapnList.add(String.valueOf(bikouRowspanCnt));
            }else{
                bikouRowsapnList.add(String.valueOf(0));
            }
            rowspan--;
        }
        return bikouRowsapnList;
    }
    /**
     * 備考用ROWSPAN設定
     * @param listPdfInfo
     * @param dto
     */
    private List setBikouRowspanResultList(List bikouRowsapnList) {

        ArrayList resultBikouikouRowsapnList = new ArrayList();
        int bikouRowsapn = 0;                
        int checkGyo = 0; 

        for(int i=0;bikouRowsapnList.size()>i;i++){
            int bikouRowspanCheck = Integer.parseInt(String.valueOf(bikouRowsapnList.get(i)));
            if(bikouRowspanCheck>meisaiGyou){
                resultBikouikouRowsapnList.add(String.valueOf(meisaiGyou));
                bikouRowsapn=bikouRowspanCheck-meisaiGyou;
                checkGyo = i+meisaiGyou;
            }else{
                if(bikouRowsapn!=0 && checkGyo==i){
                    resultBikouikouRowsapnList.add(String.valueOf(bikouRowsapn));
                    checkGyo=0;
                }else{
                    resultBikouikouRowsapnList.add(String.valueOf(bikouRowspanCheck));
                }
            }
        }       
        return resultBikouikouRowsapnList;

    }

    /**
     * 備考設定
     * @param listPdfInfo
     * @param dto
     */
      private List setBikouList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
          ArrayList bikouList = new ArrayList();
          int count = 0;
          ArrayList tempMemoList = new ArrayList();
          ArrayList tempBikouList = new ArrayList();
          tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
          int memoLinage = tempMemoList.size(); 
          tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
          int bikouLinage = tempBikouList.size();
          for(int j=0;listPdfDetInfo.size()>j;j++){
              TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
              if(!trnMosChikenDet.getReserverAmt().equals("0")){
                  count++;
                  if(count==1){
                      if(bikouLinage>1){
                          bikouList = (ArrayList) createBikou(bikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,false);
                      }else{
                          bikouList.add(trnMosChikenInfo.getRemark());
                      }
                  }else{
                      if(count>bikouLinage && j!=0){
                          bikouList.add("");
                      }
                  }
              }
          }
          if(memoLinage>count || bikouLinage>count){
                int diff = 0;
                if(memoLinage>bikouLinage){
                    diff = memoLinage;
                }else{
                    diff = bikouLinage;
                }
              for(int k=0;diff-count>k;k++){
                  if(memoLinage>bikouLinage){
                      if(memoLinage-bikouLinage>k){
                          bikouList.add("");
                      }
                  }
              }
          }
         return bikouList;
    }
      /**
       * シーケンス設定
       * @param listPdfInfo
       * @param dto
       */
        private List setSeqList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
            ArrayList seqList = new ArrayList();
            int count = 0;
            ArrayList tempMemoList = new ArrayList();
            ArrayList tempBikouList = new ArrayList();
            tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
            int memoLinage = tempMemoList.size(); 
            tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
            int bikouLinage = tempBikouList.size();
            for(int j=0;listPdfDetInfo.size()>j;j++){
                TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
                if(!trnMosChikenDet.getReserverAmt().equals("0")){
                    count++;
                    seqList.add(trnMosChikenInfo.getSeqNo());
                }
            }
            if(memoLinage>count || bikouLinage>count){
                  int diff = 0;
                  if(memoLinage>bikouLinage){
                      diff = memoLinage;
                  }else{
                      diff = bikouLinage;
                  }
                for(int k=0;diff-count>k;k++){
                    seqList.add(trnMosChikenInfo.getSeqNo());
                }
            }
            return seqList;
      }
        /**
         * メモ設定
         * @param listPdfInfo
         * @param dto
         */
          private List setMemoList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
              ArrayList memoList = new ArrayList();
              int count = 0;
              ArrayList tempMemoList = new ArrayList();
              ArrayList tempBikouList = new ArrayList();
              tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
              int memoLinage = tempMemoList.size(); 
              tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
              int bikouLinage = tempBikouList.size();
              for(int j=0;listPdfDetInfo.size()>j;j++){
                  TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
                  if(!trnMosChikenDet.getReserverAmt().equals("0")){
                      count++;
                      if(count==1){
                          if(memoLinage>1){
                              if(trnMosChikenInfo.getMemo().indexOf(_ENTER_WORD) != -1){
                                  memoList = (ArrayList) createMemo(memoList,trnMosChikenInfo.getMemo(),MEMO_LINE,false);
                              }else{
                                  memoList = (ArrayList) createBikou(memoList,trnMosChikenInfo.getMemo(),MEMO_LINE,false);
                              }
                          }else{
                              memoList.add(trnMosChikenInfo.getMemo());
                          }
                      }else{
                          if(count>memoLinage && j!= 0){
                              memoList.add("");
                          }
                      }
                  }
              }
              if(memoLinage>count || bikouLinage>count){
                    int diff = 0;
                    if(memoLinage>bikouLinage){
                        diff = memoLinage;
                    }else{
                        diff = bikouLinage;
                    }
                  for(int k=0;diff-count>k;k++){
                      if(bikouLinage-memoLinage>k){
                          memoList.add("");
                      }
                  }
              }
              
              return memoList;
        }
          /**
           * 金額設定
           * @param listPdfInfo
           * @param dto
           */
            private List setMoneyList(List listPdfInfo, MosChickenRefConfListDto dto , List listPdfDetInfo , TrnMosChikenInfo trnMosChikenInfo ) {
                ArrayList moneyList = new ArrayList();
                int count = 0;
                String strTotalMoney = "";
                ArrayList tempMemoList = new ArrayList();
                ArrayList tempBikouList = new ArrayList();
                tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
                int memoLinage = tempMemoList.size(); 
                tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
                int bikouLinage = tempBikouList.size();
                List money = getMosChickenrefconflistTrnMosChickenDetailInfoDao().getTotalMoney(trnMosChikenInfo.getCkanriNo(),trnMosChikenInfo.getCompanyCd(),trnMosChikenInfo.getMiseCd(),Integer.parseInt(trnMosChikenInfo.getSeqNo()));
                int totalMoney = 0;
                for(int l=0;money.size()>l;l++){
                    TrnMosChikenDet moneyInfo = (TrnMosChikenDet)money.get(l);
                    totalMoney = Integer.parseInt(moneyInfo.getReserverAmt()) * Integer.parseInt(moneyInfo.getTotalMoney())+totalMoney;
                }
                strTotalMoney = addComma(String.valueOf(totalMoney));
                for(int j=0;listPdfDetInfo.size()>j;j++){
                    TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
                    if(!trnMosChikenDet.getReserverAmt().equals("0")){
                        count++;
                        if(trnMosChikenInfo.getPaymentFlg().equals(okPay)){
                            moneyList.add("済");
                        }else if(trnMosChikenInfo.getPaymentFlg().equals(notPay)){
                            moneyList.add(strTotalMoney+"円");
                        }
                    }
                }
                if(memoLinage>count || bikouLinage>count){
                      int diff = 0;
                      if(memoLinage>bikouLinage){
                          diff = memoLinage;
                      }else{
                          diff = bikouLinage;
                      }
                    for(int k=0;diff-count>k;k++){
                        if(trnMosChikenInfo.getPaymentFlg().equals(okPay)){
                            moneyList.add("済");
                        }else if(trnMosChikenInfo.getPaymentFlg().equals(notPay)){
                            moneyList.add(strTotalMoney+"円");
                        }
                    }
                }
                
                return moneyList;
          }

      /**
       * 予約数の設定
       * @param listPdfInfo
       * @param dto
       */
    private List setReserveList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
          ArrayList reserveAmtList = new ArrayList();
          int count = 0;
          for(int j=0;listPdfDetInfo.size()>j;j++){
              TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
              if(!trnMosChikenDet.getReserverAmt().equals("0")){
                  count++;
                  reserveAmtList.add(trnMosChikenDet.getReserverAmt());
              }
          }
          ArrayList tempMemoList = new ArrayList();
          ArrayList tempBikouList = new ArrayList();
          tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
          int memoLinage = tempMemoList.size(); 
          tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
          int bikouLinage = tempBikouList.size();
          if(memoLinage>count || bikouLinage>count){
                int diff = 0;
                if(memoLinage>bikouLinage){
                    diff = memoLinage;
                }else{
                    diff = bikouLinage;
                }
              for(int k=0;diff-count>k;k++){
                  reserveAmtList.add("");
              }
          }
          return reserveAmtList;
    }
      /**
       * お買い上げ商品の設定
       * @param listPdfInfo
       * @param dto
       */
    private List setMenuNmKJList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
          ArrayList menuList = new ArrayList();
          int count = 0;
          for(int j=0;listPdfDetInfo.size()>j;j++){
              TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
              if(!trnMosChikenDet.getReserverAmt().equals("0")){
                  count++;
                  if(j>dto.getMstMenuCont()-1){
                      menuList.add("★" + trnMosChikenDet.getMenuNameKj());
                  }else{
                      menuList.add(trnMosChikenDet.getMenuNameKj());
                  }
              }
          }
          ArrayList tempMemoList = new ArrayList();
          ArrayList tempBikouList = new ArrayList();
          tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
          int memoLinage = tempMemoList.size(); 
          tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
          int bikouLinage = tempBikouList.size();
          if(memoLinage>count || bikouLinage>count){
                int diff = 0;
                if(memoLinage>bikouLinage){
                    diff = memoLinage;
                }else{
                    diff = bikouLinage;
                }
              for(int k=0;diff-count>k;k++){
                  menuList.add("");
              }
          }

          return menuList;
    }
    /**
     * お渡し時間用ROWSPAN設定
     * @param listPdfInfo
     * @param dto
     */
    private List setHourResultRowspan(List hourList,List bikouRowsapnList) {
        ArrayList countList = new ArrayList();
        ArrayList rowspanList = new ArrayList();
        int count=1;
        String revHourMae = null;
        String revHour = null;
        for(int i=0;hourList.size()>i;i++){
            if(hourList.size() > 1){
                if(hourList.size() == i+1){
                    if(!revHour.equals(revHourMae)){
                        countList.add(String.valueOf(1));
                    }else{
                        countList.add(String.valueOf(count));
                    }
                }else{
                    revHour =(String) hourList.get(i+1);
                    revHourMae =(String) hourList.get(i);
                    if(revHour.equals(revHourMae)){
                        count++;
                    }else{
                        countList.add(String.valueOf(count));
                        count=1;
                    }
                }
            }else{
                countList.add(String.valueOf(1));
            }
        }
        int cntListSize = 0;
        int rowspan = 0;

        for(int i=0;hourList.size()>i;i++){
            if(rowspan == 0){
                rowspan = Integer.parseInt((String) countList.get(cntListSize));
                cntListSize++;
                rowspanList.add(String.valueOf(rowspan));
            }else{
                rowspanList.add(String.valueOf(0));
            }
            rowspan--;
        }
        int bikouRowsapn = 0;
        ArrayList rowspanResultList = new ArrayList();
        for(int i=0;bikouRowsapnList.size()>i;i++){
            int hourRowspanCheck = Integer.parseInt(String.valueOf(rowspanList.get(i)));
            int bikouRowspanCheck = Integer.parseInt(String.valueOf(bikouRowsapnList.get(i)));
            if(hourRowspanCheck>meisaiGyou){
                int iCnt = i;
                int bikouRowspanCheckTotal=0;
                for(int j=iCnt ;hourRowspanCheck+iCnt>j;j++){
                    bikouRowspanCheck = Integer.parseInt(String.valueOf(bikouRowsapnList.get(j)));
                    bikouRowspanCheckTotal=bikouRowspanCheckTotal+bikouRowspanCheck;
                    if(bikouRowspanCheckTotal>meisaiGyou){
                        rowspanResultList.add(String.valueOf(bikouRowspanCheckTotal-bikouRowspanCheck));
                        for(int k=0;bikouRowspanCheckTotal-bikouRowspanCheck-1>k;k++){
                            rowspanResultList.add(String.valueOf("0"));
                        }
                        bikouRowspanCheckTotal=0;
                        j--;
                    }
                    i=j;
                }
                rowspanResultList.add(String.valueOf(bikouRowspanCheckTotal));
                for(int k=0;bikouRowspanCheckTotal-1>k;k++){
                    rowspanResultList.add(String.valueOf("0"));
                }
            }else{
                if(bikouRowsapn!=0 && bikouRowspanCheck!=0){
                    if(bikouRowsapn>meisaiGyou){
                        if(meisaiGyou>bikouRowspanCheck){
                            rowspanResultList.add(String.valueOf(bikouRowspanCheck));
                        }else{
                            rowspanResultList.add(String.valueOf(meisaiGyou));
                        }
                        bikouRowsapn=bikouRowsapn-bikouRowspanCheck;
                    }else{
                        rowspanResultList.add(String.valueOf(bikouRowsapn));
                        bikouRowsapn=0;
                    }
                }else{
                    rowspanResultList.add(String.valueOf(hourRowspanCheck));
                }
            }
        }
        return rowspanResultList;
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
    private PdfPTable getHeaderObject(MosChickenRefConfListDto dto,int pageCnt)
            throws DocumentException, IOException {
        //      ゴシック16pt
        Font font_g16 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
                "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 16, Font.UNDERLINE);
        //ゴシック10pt
        Font font_g11 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
                "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 11);
        //条件部分
//        PdfPTable headerTable = new PdfPTable(7);
//        headerTable.setWidthPercentage(97f);
//        int koumokuTableWidth[] = {5, 12, 4,8, 4, 4, 15};
//        headerTable.setWidths(koumokuTableWidth);
        PdfPTable headerTable = new PdfPTable(PDF_HEADER_CELL_SIZE);
        headerTable.setWidthPercentage(PDF_TABLE_WIDTH_SIZE);
        headerTable.setWidths(PDF_HEADER_CELL_WIDTH_SIZE);
        //1行目
        PdfPCell cell0101 = new PdfPCell(new Phrase("予約販売確認表A",font_g16));
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

    
    /* Table作成 */
    private PdfPTable getMeisaiObject(MosChickenRefConfListDto mosChickenRefConfListDto) throws DocumentException, IOException {
        
        
        //ゴシック10pt
        Font font_g10 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
                "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10);

        /* テーブル作成 */
        // テーブルの全体定義
//        PdfPTable koumokuTable = new PdfPTable(6);
//        koumokuTable.setWidthPercentage(97f);
//        int koumokuTableWidth[] = {5, 12, 12, 4, 4, 15};
//        koumokuTable.setWidths(koumokuTableWidth);
        PdfPTable koumokuTable = new PdfPTable(PDF_LIST_CELL_SIZE);
        koumokuTable.setWidthPercentage(PDF_TABLE_WIDTH_SIZE);
        koumokuTable.setWidths(PDF_LIST_CELL_WIDTH_SIZE);
        
        Color backColor = new Color(204,255,255);

        //1行
        PdfPCell hcell0201 = new PdfPCell(new Phrase("お渡し",font_g10));
        PdfPCell hcell0301 = new PdfPCell(new Phrase("No.備考", font_g10));
        PdfPCell hcell0401 = new PdfPCell(new Phrase("お買い上げ商品", font_g10));
        PdfPCell hcell0501 = new PdfPCell(new Phrase("予約数", font_g10));
        PdfPCell hcellPremium = new PdfPCell(new Phrase("プレ", font_g10));
        PdfPCell hcell0601 = new PdfPCell(new Phrase("代金", font_g10));
        PdfPCell hcell0701 = new PdfPCell(new Phrase("メモ", font_g10));
        hcell0201.setBackgroundColor(backColor);
        hcell0301.setBackgroundColor(backColor);
        hcell0401.setBackgroundColor(backColor);
        hcell0501.setBackgroundColor(backColor);
        hcellPremium.setBackgroundColor(backColor);
        hcell0601.setBackgroundColor(backColor);
        hcell0701.setBackgroundColor(backColor);

        hcell0201.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0301.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0401.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0501.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcellPremium.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0601.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0701.setHorizontalAlignment(Element.ALIGN_CENTER);

        hcell0201.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0301.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0401.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0501.setVerticalAlignment(Element.ALIGN_TOP);
        hcellPremium.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0601.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0701.setVerticalAlignment(Element.ALIGN_TOP);

        koumokuTable.addCell(hcell0201);
        koumokuTable.addCell(hcell0301);
        koumokuTable.addCell(hcell0401);
        koumokuTable.addCell(hcell0501);
        koumokuTable.addCell(hcellPremium);
        koumokuTable.addCell(hcell0601);
        koumokuTable.addCell(hcell0701);

        return koumokuTable;
    }
    /**
     * 明細情報を表示する
     * @param dto
     * @param doc
     * @param lineCnt
     * @param listPdfInfo
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private PdfPTable getSecondObject(MosChickenRefConfListDto dto,Document doc,int lineCnt,List listPdfInfo) throws DocumentException, IOException {

        /* テーブル作成 */
        // テーブルの全体定義
        int pageCnt = 1;
        
//      //ゴシック10pt
        Font font_g10 = new Font(BaseFont.createFont("HeiseiKakuGo-W5",
                "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED), 10);
        int bikouCntSave = 0;
        int meisaiCnt = 0;
        int shokiCnt = 0;
        int gyouCnt = 0;
        Map pdfList = setMeisaiInfo(listPdfInfo,dto);
        List bikouRowspanList = (List)pdfList.get(BIKOU_ROWSPAN);
        List hourRowspanList = (List)pdfList.get(HOUR_ROWSPAN);
        List hourList = (List)pdfList.get(HOUR);
        List menuNmList = (List)pdfList.get(SHO_NM_KJ);
        List reservAmtList = (List)pdfList.get(RESERVE_AMT);
        List seqList = (List)pdfList.get(SEQ_NO);
        List premiumList = (List)pdfList.get(PREMIUM);
        List moneyList = (List)pdfList.get(MONEY);
        List bikouList = (List)pdfList.get(REMARK);
        List memoList = (List)pdfList.get(MEMO);
        
        for(int i=0;menuNmList.size()>i;i++){
            NumericFormatter numericFormatter = new NumericFormatter(); 
            if(lineCnt == initGyou){
                doc.add(getHeaderObject(dto,pageCnt));
                doc.add(getMeisaiObject(dto));
                lineCnt = lineCnt + titleGyou+headerGyou;
            }
            int bikouRowspanCnt= Integer.parseInt((String)bikouRowspanList.get(i));
            int hourRowspanCnt= Integer.parseInt((String)hourRowspanList.get(i));
            lineCnt = lineCnt + bikouRowspanCnt;
            int bikouRowspanCntNext=0;
            if(i!=menuNmList.size()-1){
                bikouRowspanCntNext = Integer.parseInt((String)bikouRowspanList.get(i+1)) + gyouCnt; 
            }
            if(bikouRowspanCnt>0){
                bikouCntSave=bikouRowspanCnt;
                meisaiCnt=0;
            }
            //同SEQ内の中間行か否か判断フラグ
            boolean middleFlg = (shokiCnt==0 && i!=0 && seqList.get(i).equals(seqList.get(i-1)));
            if(gyouMax>=lineCnt){
                meisaiCnt++;
                gyouCnt++;
//                PdfPTable mosChickenDetailTable = new PdfPTable(6);
//                mosChickenDetailTable.setWidthPercentage(97f);
//                int shohinTableWidth[] = {5, 12, 12, 4, 4, 15};
//                mosChickenDetailTable.setWidths(shohinTableWidth);
                PdfPTable mosChickenDetailTable = new PdfPTable(PDF_LIST_CELL_SIZE);
                mosChickenDetailTable.setWidthPercentage(PDF_TABLE_WIDTH_SIZE);
                mosChickenDetailTable.setWidths(PDF_LIST_CELL_WIDTH_SIZE);
                int bikouRowspanCntJissou= Integer.parseInt((String)bikouRowspanList.get(i));
                String reserveHour = (String)hourList.get(i);
                String bikou= (String)bikouList.get(i);
                String menuNmKj= (String)menuNmList.get(i);
                String reserveAmt = (String)reservAmtList.get(i);
                String premium= (String)premiumList.get(i);
                String money= (String)moneyList.get(i);
                String memo = (String)memoList.get(i);
                //外枠か否か判断フラグ
                boolean outerFrameFlg = (
                		bikouRowspanCntJissou==1 
                		|| (meisaiGyou==meisaiCnt 
                				|| meisaiCnt == bikouCntSave 
                				|| i==menuNmList.size()-1 
                				|| (i!=menuNmList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))
                			)
                		);
                //お渡し時間
                PdfPCell  dcell0201 = null;
                if(hourRowspanCnt>0){
                    if(i!=0 && reserveHour.equals(hourList.get(i-1)) && shokiCnt != 0){
                        dcell0201 = new PdfPCell(new Phrase("　"));
                    }else{
                        dcell0201 = new PdfPCell(new Phrase(reserveHour,font_g10));
                    }
                    if(hourRowspanCnt==1){
                        dcell0201.setBorder(6);
                    }else if(bikouCntSave==meisaiCnt && bikouRowspanCntNext>=meisaiGyou){
                        dcell0201.setBorder(6);
//                    }else if(nextMesaiGyou>=gyouMax){
//                        dcell0201.setBorder(6);
                    }else{
                        dcell0201.setBorder(4);
                    }
                }else{
                    if(shokiCnt==0){
                        dcell0201 = new PdfPCell(new Phrase(reserveHour,font_g10));
                    }else{
                        dcell0201 = new PdfPCell(new Phrase("　"));
                    }
                    if(meisaiGyou==meisaiCnt || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !reserveHour.equals(hourList.get(i+1)))){
                        dcell0201.setBorder(6);
                    }else{
                        if(i!=bikouRowspanList.size()-1 && !reserveHour.equals(hourList.get(i+1))){
                            dcell0201.setBorder(6);
                        }else if(bikouCntSave==meisaiCnt && bikouRowspanCntNext>=meisaiGyou){
                            dcell0201.setBorder(6);
                        }else{
                            dcell0201.setBorder(4);
                        }
                    }
                }

                mosChickenDetailTable.addCell(dcell0201);
                //備考
                PdfPCell  dcell0301 = null;
                if(bikouRowspanCntJissou>0){
                    if(shokiCnt==0 && i!=0 && seqList.get(i).equals(seqList.get(i-1))){
                        dcell0301 = new PdfPCell(new Phrase("(前頁からの続き)",font_g10));
                    }else{
                        dcell0301 = new PdfPCell(new Phrase(bikou, font_g10));
                    }
                    dcell0301.setVerticalAlignment(Element.ALIGN_TOP);
                    dcell0301.setHorizontalAlignment(Element.ALIGN_LEFT);
                    if(bikouRowspanCntJissou==1){
                        dcell0301.setBorder(6);
                    }else{
                        dcell0301.setBorder(4);
                    }
                }else{
                    if(bikou.equals("")){
                        dcell0301 = new PdfPCell(new Phrase(""));
                    }else{
                        dcell0301 = new PdfPCell(new Phrase(bikou, font_g10));
                    }
                    if(meisaiGyou==meisaiCnt || meisaiCnt==bikouCntSave || i==menuNmList.size()-1 || (i!=menuNmList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0301.setBorder(6);
                    }else{
                        dcell0301.setBorder(4);
                    }
                }
                mosChickenDetailTable.addCell(dcell0301);
                //メニュー名称
                PdfPCell  dcell0401 = new PdfPCell(new Phrase(menuNmKj, font_g10));
                dcell0401.setBorder(15);
                dcell0401.setHorizontalAlignment(Element.ALIGN_LEFT);
                mosChickenDetailTable.addCell(dcell0401);
                //予約数
                PdfPCell dcell0501 = new PdfPCell(new Phrase(reserveAmt, font_g10));
                dcell0501.setBorder(15);
                dcell0501.setHorizontalAlignment(Element.ALIGN_RIGHT);
                mosChickenDetailTable.addCell(dcell0501);
                
                //プレミアム受渡フラグ
                PdfPCell dcellPremium = createPremiumCell(bikouRowspanCntJissou, middleFlg, outerFrameFlg, font_g10, premium);
                
                //代金
                PdfPCell dcell0601 = null;
                PdfPCell dcell0701 = null;
                if(bikouRowspanCntJissou>0){
                    String payMoney = numericFormatter.format(money);
                    if(shokiCnt==0 && i!=0 && seqList.get(i).equals(seqList.get(i-1))){
                        dcell0601 = new PdfPCell(new Phrase(""));
                    }else{
                        dcell0601 = new PdfPCell(new Phrase(payMoney, font_g10)); 
                    }
                    dcell0601.setVerticalAlignment(Element.ALIGN_TOP);
                    dcell0601.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    //メモ
                    if(shokiCnt==0 && i!=0 && seqList.get(i).equals(seqList.get(i-1))){
                        dcell0701 = new PdfPCell(new Phrase(""));
                    }else{
                        dcell0701 = new PdfPCell(new Phrase(memo, font_g10));
                    }
                    if(bikouRowspanCntJissou == 1){
                        dcell0601.setBorder(6);
                        dcell0701.setBorder(14);
                    }else{
                        dcell0601.setBorder(4);
                        dcell0701.setBorder(12);
                    }
                    dcell0701.setVerticalAlignment(Element.ALIGN_TOP);
                    dcell0701.setHorizontalAlignment(Element.ALIGN_LEFT);
                }else{
                    dcell0601 = new PdfPCell(new Phrase(""));
                    if(memo.equals("")){
                        dcell0701 = new PdfPCell(new Phrase(""));
                    }else{
                        dcell0701 = new PdfPCell(new Phrase(memo, font_g10));
                    }
                    if(meisaiGyou==meisaiCnt || meisaiCnt == bikouCntSave || i==menuNmList.size()-1 || (i!=menuNmList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0601.setBorder(6);
                        dcell0701.setBorder(14);
                    }else{
                        dcell0601.setBorder(4);
                        dcell0701.setBorder(12);
                    }
                }
                //プレミアム受渡フラグ
                mosChickenDetailTable.addCell(dcellPremium);
                mosChickenDetailTable.addCell(dcell0601);
                mosChickenDetailTable.addCell(dcell0701);
                doc.add(mosChickenDetailTable);
                shokiCnt++;
            }else{
                //改ページ
                int blankCnt = (gyouMax - titleGyou-headerGyou) - lineCnt-bikouRowspanCnt;
                if(blankCnt>0){
                    doc.add(kuuhakuObject(doc, blankCnt,font_g10));
                }
                shokiCnt=0;
                meisaiCnt=0;
                gyouCnt=0;
                doc.newPage();
                lineCnt=0;
                pageCnt++;
                i--;
            }
        }
        return null;
    }
    
    /**
     * PDF情報をMAPに格納
     * @param listPdfInfo
     * @param dto
     * @return
     */
    private Map setMeisaiInfo(List listPdfInfo, MosChickenRefConfListDto dto) {
        HashMap pdfMap = new HashMap();
        ArrayList hourList = new ArrayList();
        ArrayList memoList = new ArrayList();
        ArrayList shoNmKjList = new ArrayList();
        ArrayList bikouList = new ArrayList();
        ArrayList premiumList = new ArrayList();
        ArrayList moneyList = new ArrayList();
        ArrayList reserveAmtList = new ArrayList();
        ArrayList seqList = new ArrayList();
        ArrayList bikouRowspanList = new ArrayList();
        for(int i=0;listPdfInfo.size()>i;i++){
            TrnMosChikenInfo trnMosChikenInfo =(TrnMosChikenInfo) listPdfInfo.get(i);
            List listPdfDetInfo = setPdfList(trnMosChikenInfo,dto,i);
            hourList.add(setHour(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            seqList.add(setSeqList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            memoList.add(setMemoList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            bikouList.add(setBikouList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            reserveAmtList.add(setReserveList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
//2007/10/17 ADD start T.Kinugawa 2007年度対応
            premiumList.add(createPremiumList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
//2007/10/17 ADD end T.Kinugawa 2007年度対応
            moneyList.add(setMoneyList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            shoNmKjList.add(setMenuNmKJList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            bikouRowspanList.add(setBikouRowspanList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
        }
        ArrayList hourResultList = resultHourList(hourList);
        ArrayList seqResultList = resultSeqList(seqList);
        ArrayList memoResultList = resultMemoList(memoList);
        ArrayList bikouResultList = resultBikouList(bikouList);
        ArrayList reserveAmtResultList = resultReserveAmtList(reserveAmtList);
// 2007/10/17 ADD start T.Kinugawa 2007年度対応
        List premiumResultList = resultPremiumList(premiumList);
// 2007/10/17 ADD end T.Kinugawa 2007年度対応
        ArrayList moneyResultList = resultMoneyList(moneyList);
        ArrayList shoNmKjResultList = resultShoNmKjList(shoNmKjList);
        ArrayList bikouRowList = resultBikouRowspanList(bikouRowspanList);
        ArrayList hourRowList = (ArrayList)resultHourRowspanList(hourList,bikouRowList);

        pdfMap.put(HOUR,hourResultList);
        pdfMap.put(MEMO,memoResultList);
        pdfMap.put(SHO_NM_KJ,shoNmKjResultList);
        pdfMap.put(REMARK,bikouResultList);
        pdfMap.put(PREMIUM,premiumResultList);
        pdfMap.put(MONEY,moneyResultList);
        pdfMap.put(RESERVE_AMT,reserveAmtResultList);
        pdfMap.put(SEQ_NO,seqResultList);
        pdfMap.put(BIKOU_ROWSPAN,bikouRowList);
        pdfMap.put(HOUR_ROWSPAN,hourRowList);
        return pdfMap;
    }
    /**
     * 時間リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultHourList(ArrayList hourList) {
        ArrayList hourSecList = new ArrayList();
        ArrayList hourResultList = new ArrayList();
        for(int i=0;hourList.size()>i;i++){
            hourSecList = (ArrayList)hourList.get(i);
            for(int j=0;hourSecList.size()>j;j++){
                hourResultList.add((String)hourSecList.get(j));
            }
        }
        return hourResultList;
    }

    /**
     * SEQNOリスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultSeqList(ArrayList seqList) {
        ArrayList seqSecList = new ArrayList();
        ArrayList seqResultList = new ArrayList();
        for(int i=0;seqList.size()>i;i++){
            seqSecList = (ArrayList)seqList.get(i);
            for(int j=0;seqSecList.size()>j;j++){
                seqResultList.add((String)seqSecList.get(j));
            }
        }
        return seqResultList;
    }
    /**
     * メモリスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultMemoList(ArrayList memoList) {
        ArrayList memoSecList = new ArrayList();
        ArrayList memoResultList = new ArrayList();
        for(int i=0;memoList.size()>i;i++){
            memoSecList = (ArrayList)memoList.get(i);
            for(int j=0;memoSecList.size()>j;j++){
                memoResultList.add((String)memoSecList.get(j));
            }
        }
        return memoResultList;
    }
    /**
     * 備考リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultBikouList(ArrayList bikouList) {
        ArrayList bikouSecList = new ArrayList();
        ArrayList bikouResultList = new ArrayList();
        for(int i=0;bikouList.size()>i;i++){
            bikouSecList = (ArrayList)bikouList.get(i);
            for(int j=0;bikouSecList.size()>j;j++){
                bikouResultList.add((String)bikouSecList.get(j));
            }
        }
        return bikouResultList;
    }
    /**
     * 商品数リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultReserveAmtList(ArrayList reserveAmtList) {
        ArrayList reserveSecList = new ArrayList();
        ArrayList reserveResultList = new ArrayList();
        for(int i=0;reserveAmtList.size()>i;i++){
            reserveSecList = (ArrayList)reserveAmtList.get(i);
            for(int j=0;reserveSecList.size()>j;j++){
                reserveResultList.add((String)reserveSecList.get(j));
            }
        }
        return reserveResultList;
    }
    /**
     * 金額リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultMoneyList(ArrayList moneyList) {
        ArrayList moneySecList = new ArrayList();
        ArrayList moneyResultList = new ArrayList();
        for(int i=0;moneyList.size()>i;i++){
            moneySecList = (ArrayList)moneyList.get(i);
            for(int j=0;moneySecList.size()>j;j++){
                moneyResultList.add((String)moneySecList.get(j));
            }
        }
        return moneyResultList;
    }
    /**
     * 商品名称リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultShoNmKjList(ArrayList shoNmKjList) {
        ArrayList shoNmKjSecList = new ArrayList();
        ArrayList shoNmKjResultList = new ArrayList();
        for(int i=0;shoNmKjList.size()>i;i++){
            shoNmKjSecList = (ArrayList)shoNmKjList.get(i);
            for(int j=0;shoNmKjSecList.size()>j;j++){
                shoNmKjResultList.add((String)shoNmKjSecList.get(j));
            }
        }
        return shoNmKjResultList;
    }
    /**
     * 備考ROWSPANリスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultBikouRowspanList(ArrayList bikouRowspanList) {
        ArrayList bikouRowspanSecList = new ArrayList();
        ArrayList bikouRowspanResultList = new ArrayList();
        for(int i=0;bikouRowspanList.size()>i;i++){
            bikouRowspanSecList = (ArrayList)bikouRowspanList.get(i);
            for(int j=0;bikouRowspanSecList.size()>j;j++){
                bikouRowspanResultList.add((String)bikouRowspanSecList.get(j));
            }
        }
        return (ArrayList) setBikouRowspanResultList(bikouRowspanResultList);
        
    }
    /**
     * 時間ROWSPANリスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultHourRowspanList(ArrayList hourRowspanList,ArrayList bikouRowList) {
        ArrayList hourRowspanSecList = new ArrayList();
        ArrayList hourRowspanResultList = new ArrayList();
        for(int i=0;hourRowspanList.size()>i;i++){
            hourRowspanSecList = (ArrayList)hourRowspanList.get(i);
            for(int j=0;hourRowspanSecList.size()>j;j++){
                hourRowspanResultList.add((String)hourRowspanSecList.get(j));
            }
        }
        return (ArrayList) setHourResultRowspan(hourRowspanResultList,bikouRowList);
        
    }
	/**
	 * プレミアム受渡フラグリスト作成
	 * 
	 * @param listPdfInfo
	 * @param dto
	 * @param listPdfDetInfo
	 * @param trnMosChikenInfo
	 * @return
	 */
	private List createPremiumList(List listPdfInfo, MosChickenRefConfListDto dto , List listPdfDetInfo , TrnMosChikenInfo trnMosChikenInfo ) {
		List list = new ArrayList();
		int count = 0;
		ArrayList tempMemoList = new ArrayList();
		ArrayList tempBikouList = new ArrayList();
		tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
		int memoLinage = tempMemoList.size(); 
		tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
		int bikouLinage = tempBikouList.size();
		String premiumFlg = trnMosChikenInfo.getPremiumFlg();
		for(int j=0;listPdfDetInfo.size()>j;j++){
		    TrnMosChikenDet trnMosChikenDet =(TrnMosChikenDet) listPdfDetInfo.get(j);
		    if(!trnMosChikenDet.getReserverAmt().equals("0")){
		    	count++;
		    }
		}
        int diff = 0;
        if(memoLinage>bikouLinage){
        	diff = memoLinage;
        }else{
        	diff = bikouLinage;
        }
        if(diff<count){
        	diff = count;
        }
        for(int k=0; k<diff; k++){
        	if(premiumFlg.equals(FLG_OK)){
        		list.add("済");
        	}else if(premiumFlg.equals(FLG_NO)){
        		list.add("★未");
        	}
        }   
        return list;
    }
    /**
     * プレミアム受渡フラグリスト作成
     * @param lists プレミアム受渡フラグリスト
     * @return
     */
    private List resultPremiumList(ArrayList lists) {
        List list = new ArrayList();
        List resultList = new ArrayList();
        for(int i=0;lists.size()>i;i++){
        	list = (ArrayList)lists.get(i);
            for(int j=0;list.size()>j;j++){
            	resultList.add((String)list.get(j));
            }
        }
        return resultList;
    }

    /**
     * 空白行設定
     * @param doc
     * @param blankCnt
     * @param font_g10
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    
    private Element kuuhakuObject(Document doc, int blankCnt, Font font_g10)  throws DocumentException, IOException {
        PdfPTable meisaiBlankTable = new PdfPTable(20);
        meisaiBlankTable.setWidthPercentage(97f);
        
        //空白行
        PdfPCell fcell1 = new PdfPCell(new Phrase("　",font_g10));
        fcell1.setColspan(20);
        
        for (int i = 0 ; i < blankCnt; i++) {
            meisaiBlankTable.addCell(fcell1);
        }
        return meisaiBlankTable;
        
    }
    /**
     * 詳細情報
     * @param trnMosChikenInfo
     * @param dto
     * @param i
     * @return
     */
    private List setPdfList(TrnMosChikenInfo trnMosChikenInfo,MosChickenRefConfListDto dto,int i) {
        List resultList = getMosChickenrefconflistTrnMosChickenDetailInfoDao().getMosChickenDetInfo(trnMosChikenInfo.getCkanriNo(),trnMosChikenInfo.getCompanyCd(),trnMosChikenInfo.getMiseCd(),Integer.parseInt(trnMosChikenInfo.getSeqNo()));
        List resultMstList = getMosChickenrefconflistTrnMosChickenDetailInfoDao().getMosChickenMstMenuDetInfo(trnMosChikenInfo.getCkanriNo(),trnMosChikenInfo.getCompanyCd(),trnMosChikenInfo.getMiseCd(),Integer.parseInt(trnMosChikenInfo.getSeqNo()));
        dto.setMstMenuCont(resultMstList.size());
        ArrayList deleteList = new ArrayList(); 
        for(int j = 0;resultList.size()>j;j++){
            TrnMosChikenDet allList = (TrnMosChikenDet) resultList.get(j);
            for(int k = 0;resultMstList.size()>k;k++){
                TrnMosChikenDet mstList = (TrnMosChikenDet) resultMstList.get(k);
                if(allList.getMenuCd().equals(mstList.getMenuCd())){
                    deleteList.add(mstList);
                    break;
                }
            }
        }
        for(int j=0;deleteList.size()>j;j++){
            TrnMosChikenDet addList = (TrnMosChikenDet) deleteList.get(j);
            for(int k = 0;resultList.size()>k;k++){
                TrnMosChikenDet mstList = (TrnMosChikenDet) resultList.get(k);
                if(addList.getMenuCd().equals(mstList.getMenuCd())){
                    resultList.remove(k);
                }
            }
        }
        for(int j=0;resultList.size()>j;j++){
            TrnMosChikenDet addList = (TrnMosChikenDet) resultList.get(j);
            resultMstList.add(addList);
        }
        for(int j=0;resultMstList.size()>j;j++){
            TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)resultMstList.get(j); 
            for(int k=j+1;resultMstList.size()>k;k++){
                TrnMosChikenDet trnMosChikenDet2 = (TrnMosChikenDet)resultMstList.get(k); 
                if(trnMosChikenDet2.getMenuCd().equals(trnMosChikenDet.getMenuCd())){
                    resultMstList.remove(j);
                    break;
                }
            }
        }

        return resultMstList;
    }
    /**
     * 戻り処理
     */
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
     * mosChickenrefconflistTrnMosChickenDetailInfoDaoを取得
     * @return mosChickenrefconflistTrnMosChickenDetailInfoDao
     */
    public TrnMosChickenDetailInfoDao getMosChickenrefconflistTrnMosChickenDetailInfoDao() {
        return mosChickenrefconflistTrnMosChickenDetailInfoDao;
    }
    /**
     * mosChickenrefconflistTrnMosChickenDetailInfoDaoを設定
     * @param mosChickenrefconflistTrnMosChickenDetailInfoDao
     */
    public void setMosChickenrefconflistTrnMosChickenDetailInfoDao(
            TrnMosChickenDetailInfoDao mosChickenrefconflistTrnMosChickenDetailInfoDao) {
        this.mosChickenrefconflistTrnMosChickenDetailInfoDao = mosChickenrefconflistTrnMosChickenDetailInfoDao;
    }
    /**
     * mosChickenrefconflistTrnMosChickenInfoDaoを取得
     * @return mosChickenrefconflistTrnMosChickenInfoDao
     */
    public TrnMosChickenInfoDao getMosChickenrefconflistTrnMosChickenInfoDao() {
        return mosChickenrefconflistTrnMosChickenInfoDao;
    }
    /**
     * mosChickenrefconflistTrnMosChickenInfoDaoを設定
     * @param mosChickenrefconflistTrnMosChickenInfoDao
     */
    public void setMosChickenrefconflistTrnMosChickenInfoDao(
            TrnMosChickenInfoDao mosChickenrefconflistTrnMosChickenInfoDao) {
        this.mosChickenrefconflistTrnMosChickenInfoDao = mosChickenrefconflistTrnMosChickenInfoDao;
    }
    /**
     * メモを改行にする
     * @param memo
     * @return
     */
    private List createMemo(ArrayList memoList ,String memo,int byteNum,boolean isCheck) {

        int index = 0;
        boolean endFlg = true;
        String topMemo = "";
        String bottomMemo = "";
        String nextFigure = "";
        if(isCheck){
            bottomMemo = memo;            
        }

        if(memo != null && !memo.equals("")){
            while(endFlg){
                index = memo.indexOf(_ENTER_WORD);
                if(index == -1) {
                    endFlg = false;
                    if(!bottomMemo.equals("")){
                        if(bottomMemo.getBytes().length/byteNum >0 && bottomMemo.getBytes().length%byteNum >0){
                            memoList = (ArrayList)createBikou(memoList,bottomMemo,byteNum,isCheck);
                        }else{
                            if(bottomMemo.getBytes().length/byteNum >1){
                                memoList = (ArrayList)createBikou(memoList,bottomMemo,byteNum,isCheck);
                            }else{
                                memoList.add(bottomMemo);
                            }
                        }
                    }
                }else{
                    topMemo = memo.substring(0,index);
                    if(topMemo.getBytes().length/byteNum >0){
                        if(byteNum/2+1 != memo.length()){
                            nextFigure = topMemo+ memo.substring(byteNum/2,byteNum/2+1);
                        }
                        topMemo = memo.substring(0,byteNum/2);
                        int i=0;
                        String divaiteChar = topMemo;
                        while(byteNum>topMemo.getBytes().length && byteNum+1>nextFigure.getBytes().length){
                            topMemo = memo.substring(0,byteNum/2+i);
                            if(i==0){
                                divaiteChar = topMemo;
                            }else{
                                divaiteChar = memo.substring(0,byteNum/2+i-1);
                            }
                            if(memo.length() > byteNum/2+i+1){
                                nextFigure = topMemo+ memo.substring(byteNum/2+i,byteNum/2+i+1);
                            }
                            i++;
                        }
                        if(i==0){
                            i++;
                        }
                        if(i==0){
                            i++;
                            bottomMemo = memo.substring(byteNum/2+(i-1));
                        }else if(i>1){
                            bottomMemo = memo.substring(byteNum/2+(i-1)-1);
                        }else{ 
                            bottomMemo = memo.substring(byteNum/2+(i-1));
                        }
                        memo = bottomMemo;
                        memoList.add(divaiteChar);
                        memo = bottomMemo;
                    }else{
                        bottomMemo = memo.substring(index+1);
                        if(bottomMemo.equals("")){
                            bottomMemo = "　";
                        }
                        memo = bottomMemo;
                        memoList.add(topMemo);
                    }
                }
            }
        }
        return memoList;
    }
    /**
     * メモ、備考を改行にする
     * @param memo
     * @return
     */
    private List createBikou(ArrayList list,String memo,int byteNum,boolean isCheck) {

        int index = 0;
        boolean endFlg = true;
        String bottomMemo = "";
        if(isCheck){
            bottomMemo = memo;
        }
        String topMemo = "";
        if(memo != null && !memo.equals("")){
            while(endFlg){
                index=memo.getBytes().length/byteNum;
                if(index == 0) {
                    endFlg = false;
                    if(!bottomMemo.equals("")){
                        list.add(bottomMemo);
                    }
                }else{
                    String nextFigure = "";
                    topMemo = memo.substring(0,byteNum/2);
                    int i=0;
                    String divaiteChar = topMemo;
                    while(byteNum>topMemo.getBytes().length && byteNum+1>nextFigure.getBytes().length){
                        topMemo = memo.substring(0,byteNum/2+i);
                        if(i==0){
                            divaiteChar = topMemo;
                        }else{
                            divaiteChar = memo.substring(0,byteNum/2+i-1);
                        }
                        if(memo.length() > byteNum/2+i+1){
                            nextFigure = topMemo+ memo.substring(byteNum/2+i,byteNum/2+i+1);
                        }
                        i++;
                    }
                    if(i==0){
                        i++;
                        bottomMemo = memo.substring(byteNum/2+(i-1));
                    }else if(i>1){
                        bottomMemo = memo.substring(byteNum/2+(i-1)-1);
                    }else{ 
                        bottomMemo = memo.substring(byteNum/2+(i-1));
                    }
                    memo = bottomMemo;
                    list.add(divaiteChar);
                }
            }
        }else{
            
        }
        return list;
    }
    /**
     * プレミアム受渡フラグCELL作成取得処理
     * 
     * @param bikouRowspanCntJissou
     * @param middleFlg
     * @param outerFrameFlg
     * @param font_g10
     * @param premium
     * @return
     */
    private PdfPCell createPremiumCell(int bikouRowspanCntJissou
    		, boolean middleFlg, boolean outerFrameFlg, final Font font_g10, String premium) 
    {
        //代金
        PdfPCell cell = null;
            
        if(middleFlg || bikouRowspanCntJissou<=0){
        	cell = new PdfPCell(new Phrase(""));
        }else{
        	cell = new PdfPCell(new Phrase(premium, font_g10)); 
        }
        if(outerFrameFlg){
        	cell.setBorder(6);
        }else{
        	cell.setBorder(4);
        }
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        
        return cell;
    }
//    /**
//     * メモが何行あるか
//     */
//    private int gyouMemo(String memo,int num) {
//         int addBrMemo = 1;
//
//        int index = 0;
//        boolean endFlg = true;
//        String topMemo = "";
//        if(memo != null && !memo.equals("")){
//            while(endFlg){
//                index = memo.indexOf(_ENTER_WORD);
//                if(index == -1) {
//                    endFlg = false;
//                    if(memo.getBytes().length%num == 0 && memo.getBytes().length > 0){
//                        addBrMemo= addBrMemo + memo.getBytes().length/num-1;
//                    }else{
//                        addBrMemo= addBrMemo + memo.getBytes().length/num;
//                    }
//                }else{
//                    topMemo = memo.substring(0,index);
//                    addBrMemo++;
//                    if(topMemo.getBytes().length/num > 0){
//                        addBrMemo = addBrMemo + topMemo.getBytes().length/num;
//                    }
//                    memo = memo.substring(index+1);
//                }
//            }
//        }else{
//            addBrMemo = 0;
//        }
//        return addBrMemo;
//    }


    /**
     * 金額を表示用に編集
     * 数値にカンマを付け加える
     * @param  数値(金額)
     * @return カンマ編集済文字列
     */
    private String addComma(String money){
 
        //返却用
        String ret;
        NumericFormatter formatter = new NumericFormatter();
        ret = formatter.format(money,"##,###,###,##0");
        return ret;
    }
    /**
     * codMiseInfoDaoを取得
     * @return codMiseInfoDao
     */
    public CodMiseListDao getCodMiseListDao() {
        return codMiseListDao;
    }
    /**
     * codMiseInfoDaoを設定
     * @param codMiseInfoDao
     */
    public void setCodMiseListDaoo(CodMiseListDao codMiseListDao) {
        this.codMiseListDao = codMiseListDao;
    }
}