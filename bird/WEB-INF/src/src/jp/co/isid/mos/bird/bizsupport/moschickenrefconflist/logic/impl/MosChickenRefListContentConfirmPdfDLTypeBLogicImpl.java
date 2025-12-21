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
public class MosChickenRefListContentConfirmPdfDLTypeBLogicImpl implements PdfOutputLogic {
    /** 指定改行文字 */
    private static final String _ENTER_WORD = "`";
    /*メモもしくは備考の行チェック*/
    private static final int BIKOU_LINE= 34;
    private static final int MEMO_LINE =    42;

    /*モスチキン予約情報**/
    private TrnMosChickenInfoDao mosChickenrefconflistTrnMosChickenInfoDao;
    /*モスチキン予約明細情報**/
    private TrnMosChickenDetailInfoDao mosChickenrefconflistTrnMosChickenDetailInfoDao;
    /*店情報**/
    private CodMiseListDao codMiseListDao;
    /*行数*/
    private static final int gyouMax = 38;
    private static final int titleGyou = 4;
    private static final int headerGyou = 1;
    private static final int initGyou = 0;
    private static final int meisaiGyou = 33;
    /**代済フラグ 未：0,済：1*/
    private static final String notPay = "0";
    private static final String okPay = "1";
    /**ﾌﾟﾚﾐｱﾑフラグ 未：0,済：1*/
    private static final String FLG_NO = "0";
    private static final String FLG_OK = "1";
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
    /*モスチキンのメニューコード*/
    private static final String mosChickenCd         = "220001";
    private static final String mosChickenFCd        = "220005";
    private static final String mosChicken5PCd       = "220006";    
    private static final String mosChicken10PCd      = "220007";
    private static final String mosChickenColdCd     = "221021";
    private static final String mosChicken5PColdCd   = "221020";
    
    /*MAP用キー名称*/
    private static  String MEMO = "MEMO";
    private static  String REMARK = "BIKOU";
    private static  String MOSCHICKEN_AMT = "MOSCHICKEN_AMT";
    private static  String MOSCHICKEN5P_AMT = "MOSCHICKEN5P_AMT";
    private static  String MOSCHICKEN10P_AMT = "MOSCHICKEN10P_AMT";
    private static  String MOSCHICKEN_COLD_AMT = "MOSCHICKENCOLD_AMT";
    private static  String MOSCHICKEN_COLD5P_AMT = "MOSCHICKENCOLD10P_AMT";
    private static  String HOUR = "HOUR";
    private static  String MONEY = "MONEY";
    private static  String SEQ_NO = "SEQ_NO";
    private static  String BIKOU_ROWSPAN = "BIKOU_ROWSPAN";
    private static  String HOUR_ROWSPAN = "HOUR_ROWSPAN";

    private static final String PREMIUM = "PREMIUM";

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS020L04";    /* Documentオブジェクト設定処理 */

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
     * 確認表データ表示テーブルの各セル幅パーセンテージ値 (5%, 12%, 12%, 4%, 4%, 4%, 15%) 
     */
    private static final int PDF_LIST_CELL_WIDTH_SIZE[] = {4, 25, 4, 4, 4, 4, 4, 3, 7, 31};
    /**
     * 確認表データ表示テーブル項目数
     */
    private static final int PDF_LIST_CELL_SIZE = PDF_LIST_CELL_WIDTH_SIZE.length;

    public Document createDocument() {
        Document doc = new Document(PageSize.A4.rotate());
        doc.setMargins(3f, 3f, 3f, 2f);
        return doc;
    }

    /* ファイル名取得 */
    public String getFileName(final PdfOutputDto pdfOutputDto) {
        DateFormatter formatter = new DateFormatter();
        MosChickenRefConfListDto dto = (MosChickenRefConfListDto) pdfOutputDto;
        String fileName ="YOYAKUB_"+ formatter.format(String.valueOf(dto.getReserveDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD) +"_"+ dto.getMiseCd()  + ".pdf";
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
     * お渡し時間リストセット
     * @param listPdfInfo
     * @param dto
     * @param listPdfDetHokaInfo
     * @param trnMosChikenInfo
     * @return
     */
    private List setHourList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetHokaInfo,TrnMosChikenInfo trnMosChikenInfo) {
        ArrayList hourList = new ArrayList();
        int menuCnt = 0;
        int memoCount=0;
        int iCnt = 0;
        ArrayList tempMemoList = new ArrayList();
        ArrayList tempBikouList = new ArrayList();
        tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
        int memoLinage = tempMemoList.size(); 
        tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
        int bikouLinage = tempBikouList.size();
        if(listPdfDetHokaInfo.size()>0){
            for(int j=0; listPdfDetHokaInfo.size()>j;j++){
                TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listPdfDetHokaInfo.get(j);
                if(!trnMosChikenDet.getReserverAmt().equals("0")){
                    memoCount++;
                    menuCnt++;
                    iCnt++;
                    hourList.add(trnMosChikenInfo.getReserveHh()+":"+trnMosChikenInfo.getReserveMm());
                }
            }
        }
        if((memoCount>0 && !trnMosChikenInfo.getMemo().equals(""))
                || memoCount==0){
            memoCount=memoCount+memoLinage;
            menuCnt++;
            iCnt++;
            hourList.add(trnMosChikenInfo.getReserveHh()+":"+trnMosChikenInfo.getReserveMm());
            if(bikouLinage>memoCount){
                memoCount = bikouLinage;
            }
        }
        if(memoCount>iCnt){
            for(int k=0;memoCount-iCnt>k;k++){
                menuCnt++;
                hourList.add(trnMosChikenInfo.getReserveHh()+":"+trnMosChikenInfo.getReserveMm());
            }
        }else if(bikouLinage>memoCount){
            for(int j=0; bikouLinage-memoCount>j;j++){
                hourList.add(trnMosChikenInfo.getReserveHh()+":"+trnMosChikenInfo.getReserveMm());
            }
        }
        return hourList;
    }

    //メモをセット
    private List setMemoList(List listPdfInfo, MosChickenRefConfListDto dto,List listMstMenuHokaInfo,TrnMosChikenInfo trnMosChikenInfo) {
        ArrayList memoList = new ArrayList();
        ArrayList tempMemoList = new ArrayList();
        ArrayList tempBikouList = new ArrayList();
        int memoCnt = 0;
        int count = 0;
        tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
        int memoLinage = tempMemoList.size(); 
        tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
        int bikouLinage = tempBikouList.size();
        if(listMstMenuHokaInfo.size()>0){
            for(int j=0; listMstMenuHokaInfo.size()>j;j++){
                TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listMstMenuHokaInfo.get(j);
                if(!trnMosChikenDet.getReserverAmt().equals("0")){
                    memoCnt++;
                    count++;
                    memoList.add(trnMosChikenDet.getMenuNameKj()+"("+trnMosChikenDet.getReserverAmt()+")");
                }
            }
        }
        if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                || memoCnt==0){
            if(memoLinage>1){
                if(trnMosChikenInfo.getMemo().indexOf(_ENTER_WORD) != -1){
                    memoList = (ArrayList) createMemo(memoList,trnMosChikenInfo.getMemo(),MEMO_LINE,false);
                }else{
                    memoList = (ArrayList) createBikou(memoList,trnMosChikenInfo.getMemo(),MEMO_LINE,false);
                }
            }else{
                memoList.add(trnMosChikenInfo.getMemo());
            }
            count++;
            memoCnt=memoCnt+memoLinage;
        }
        if(memoCnt == 0){
            memoCnt=1;
        }
        if(bikouLinage>memoCnt){
            for(int j=0; bikouLinage-memoCnt>j;j++){
                memoList.add("");
            }
        }
        return memoList;

    }
    /**
     * 備考用ROWSPAN
     * @param listPdfInfo
     * @param dto
     * @param listPdfDetHokaInfo
     * @param trnMosChikenInfo
     * @return
     */
    private List setBikouRowspanList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetHokaInfo,TrnMosChikenInfo trnMosChikenInfo) {
        ArrayList bikouRowsapnList = new ArrayList();
        int bikouRowspanCnt = 0;
        int count = 0;
        int memoCnt = 0;
        ArrayList tempMemoList = new ArrayList();
        ArrayList tempBikouList = new ArrayList();
        tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
        int memoLinage = tempMemoList.size(); 
        tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
        int bikouLinage = tempBikouList.size();
        if(listPdfDetHokaInfo.size()>0){
            for(int j=0; listPdfDetHokaInfo.size()>j;j++){
                TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listPdfDetHokaInfo.get(j);
                if(!trnMosChikenDet.getReserverAmt().equals("0")){
                    bikouRowspanCnt++;
                    count++;
                    memoCnt++;
                }
            }
        }
        if((bikouRowspanCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                || bikouRowspanCnt==0){
                count++;
                bikouRowspanCnt++;
                memoCnt = memoLinage+memoCnt;
                if(bikouLinage>memoCnt){
                    memoCnt = bikouLinage;
                }
        }
        if(memoCnt>count){
            for(int j=0; memoCnt-count>j;j++){
                bikouRowspanCnt++;
            }
        }else if(bikouLinage>memoCnt){
            for(int j=0; bikouLinage-memoCnt>j;j++){
                bikouRowspanCnt++;
            }
        }

        if(bikouRowspanCnt==0){
            bikouRowspanCnt=1;
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
     * 備考用結果ROWSPAN設定
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
      private List setBikouList(List listPdfInfo, MosChickenRefConfListDto dto,List listMstMenuHokaInfo,TrnMosChikenInfo trnMosChikenInfo) {
          ArrayList bikouList = new ArrayList();
          bikouList = (ArrayList) createBikou(bikouList, trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
          ArrayList memoList = new ArrayList();
          memoList = (ArrayList) createMemo(memoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
          while (memoList.size() + listMstMenuHokaInfo.size() > bikouList.size()) {
              bikouList.add("");
          }
          
//          ArrayList bikouList = new ArrayList();
//          int memoCnt = 0;
//          int count = 0;
//          ArrayList tempMemoList = new ArrayList();
//          ArrayList tempBikouList = new ArrayList();
//          tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
//          int memoLinage = tempMemoList.size(); 
//          tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
//          int bikouLinage = tempBikouList.size();
//          boolean isRemark = false;
//          if(listMstMenuHokaInfo.size()>0){
//              for(int j=0; listMstMenuHokaInfo.size()>j;j++){
//                  TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listMstMenuHokaInfo.get(j);
//                  if(!trnMosChikenDet.getReserverAmt().equals("0")){
//                      isRemark = true;
//                      memoCnt++;
//                      count++;
//                      if(memoCnt==1){
//                          if(bikouLinage>1){
//                              bikouList = (ArrayList) createBikou(bikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,false);
//                          }else{
//                              bikouList.add(trnMosChikenInfo.getRemark());
//                          }
//                      }else{
//                          if(memoCnt>bikouLinage && j!=0){
//                              bikouList.add("");
//                          }
//                      }
//                  }
//              }
//          }
//          if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
//                  || memoCnt==0){
//              memoCnt=memoLinage+memoCnt;
//              count++;
//              if(!isRemark){
//                  if(bikouLinage>1){
//                      bikouList = (ArrayList) createBikou(bikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,false);
//                  }else{
//                      bikouList.add(trnMosChikenInfo.getRemark());
//                  }
//              }else{
//                  if(memoLinage>=bikouLinage){
//                      bikouList.add("");
//                  }else if(count>bikouLinage){
//                      bikouList.add("");
//                  }
//              }
//              if(bikouLinage>memoCnt){
//                  memoCnt = bikouLinage;
//              }
//          }
//          int diff = 0;
//          if(bikouLinage>count){
//              diff = bikouLinage;
//          }else{
//              diff = count;
//          }
//          if(memoCnt>diff){
//              for(int j=0; memoCnt-diff>j;j++){
//                  if(memoCnt>bikouLinage){
//                      bikouList.add("");
//                  }
//              } 
//          }
          
          return bikouList;
    }
      /**
       * シーケンス番号設定
       * @param listPdfInfo
       * @param dto
       */
        private List setSeqList(List listPdfInfo, MosChickenRefConfListDto dto,List listMstMenuHokaInfo,TrnMosChikenInfo trnMosChikenInfo) {
            ArrayList seqList = new ArrayList();
            int memoCnt = 0;
            int count = 0;
            ArrayList tempMemoList = new ArrayList();
            ArrayList tempBikouList = new ArrayList();
            tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
            int memoLinage = tempMemoList.size(); 
            tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
            int bikouLinage = tempBikouList.size();
            if(listMstMenuHokaInfo.size()>0){
                for(int j=0; listMstMenuHokaInfo.size()>j;j++){
                    TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listMstMenuHokaInfo.get(j);
                    if(!trnMosChikenDet.getReserverAmt().equals("0")){
                        memoCnt++;
                        count++;
                        seqList.add(trnMosChikenInfo.getSeqNo());
                    }
                }
            }
            if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                    || memoCnt==0){
                memoCnt=memoLinage+memoCnt;
                count++;
                seqList.add(trnMosChikenInfo.getSeqNo());
                if(bikouLinage>memoCnt){
                    memoCnt = bikouLinage;
                }
            }
            if(memoCnt>count){
                for(int j=0; memoCnt-count>j;j++){
                    seqList.add(trnMosChikenInfo.getSeqNo());
                } 
            }else if(bikouLinage>memoCnt){
                for(int j=0; bikouLinage-memoCnt>j;j++){
                    seqList.add(trnMosChikenInfo.getSeqNo());
                }
            }
            return seqList;
      }
        /**
         * 金額設定
         * @param listPdfInfo
         * @param dto
         */
          private List setMoneyList(List listPdfInfo, MosChickenRefConfListDto dto,List listMstMenuHokaInfo,TrnMosChikenInfo trnMosChikenInfo) {
              ArrayList moneyList = new ArrayList();
              int memoCnt = 0;
              int totalMoney = 0;
              int count = 0;
              String  strtoTalMoney = "";
              ArrayList tempMemoList = new ArrayList();
              ArrayList tempBikouList = new ArrayList();
              tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
              int memoLinage = tempMemoList.size(); 
              tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
              int bikouLinage = tempBikouList.size();
              List money = getMosChickenrefconflistTrnMosChickenDetailInfoDao().getTotalMoney(trnMosChikenInfo.getCkanriNo(),trnMosChikenInfo.getCompanyCd(),trnMosChikenInfo.getMiseCd(),Integer.parseInt(trnMosChikenInfo.getSeqNo()));
              for(int k=0;money.size()>k;k++){
                  TrnMosChikenDet moneyInfo = (TrnMosChikenDet)money.get(k);
                  totalMoney = Integer.parseInt(moneyInfo.getReserverAmt()) * Integer.parseInt(moneyInfo.getTotalMoney())+totalMoney;
                  strtoTalMoney=addComma(String.valueOf(totalMoney));
              }
              if(listMstMenuHokaInfo.size()>0){
                  for(int j=0; listMstMenuHokaInfo.size()>j;j++){
                      TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listMstMenuHokaInfo.get(j);
                      if(!trnMosChikenDet.getReserverAmt().equals("0")){
                          memoCnt++;
                          count++;
                          if(trnMosChikenInfo.getPaymentFlg().equals(okPay)){
                              moneyList.add("済");
                          }else if(trnMosChikenInfo.getPaymentFlg().equals(notPay)){
                              moneyList.add(strtoTalMoney+"円");
                          }
                      }
                  }
              }
              if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                      || memoCnt==0){
                  memoCnt=memoLinage+memoCnt;
                  count++;
                  if(trnMosChikenInfo.getPaymentFlg().equals(okPay)){
                      moneyList.add("済");
                  }else if(trnMosChikenInfo.getPaymentFlg().equals(notPay)){
                      moneyList.add(strtoTalMoney+"円");
                  }
                  if(bikouLinage>memoCnt){
                      memoCnt = bikouLinage;
                  }
              }
              if(memoCnt>count){
                  for(int j=0; memoCnt-count>j;j++){
                      if(trnMosChikenInfo.getPaymentFlg().equals(okPay)){
                          moneyList.add("済");
                      }else if(trnMosChikenInfo.getPaymentFlg().equals(notPay)){
                          moneyList.add(strtoTalMoney+"円");
                      }
                  } 
              }else if(bikouLinage>memoCnt){
                  for(int j=0; bikouLinage-memoCnt>j;j++){
                      if(trnMosChikenInfo.getPaymentFlg().equals(okPay)){
                          moneyList.add("済");
                      }else if(trnMosChikenInfo.getPaymentFlg().equals(notPay)){
                          moneyList.add(strtoTalMoney+"円");
                      }
                  }
              }              
              return moneyList;
        }
          /**
           * モスチキン予約数設定
           * @param listPdfInfo
           * @param dto
           */
        private List setMoschickenCntList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetHokaInfo,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
            ArrayList moschickenCntList        = new ArrayList();
            String moschickenCnt        = "0";
            int memoCnt=0;
            boolean isMosChicken        = false;
            ArrayList tempMemoList = new ArrayList();
            ArrayList tempBikouList = new ArrayList();
            tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
            int memoLinage = tempMemoList.size(); 
            tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
            int bikouLinage = tempBikouList.size();
            int intMoschickenCnt=0;
            for(int j=0;listPdfDetInfo.size()>j;j++){
                TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listPdfDetInfo.get(j);
                if(trnMosChikenDet.getMenuCd().equals(mosChickenCd)){
                    isMosChicken = true;
                    intMoschickenCnt = Integer.parseInt(trnMosChikenDet.getReserverAmt());
                }
                if(trnMosChikenDet.getMenuCd().equals(mosChickenFCd)){
                    isMosChicken = true;
                    intMoschickenCnt = Integer.parseInt(moschickenCnt)+Integer.parseInt(trnMosChikenDet.getReserverAmt());
                }
                moschickenCnt = String.valueOf(intMoschickenCnt);
            }
            if(!isMosChicken){
                moschickenCntList.add("");
            }else{
                if(moschickenCnt.equals("0")){
                    moschickenCntList.add("");
                }else{
                    moschickenCntList.add(moschickenCnt);
                }
            }   
            if(listPdfDetHokaInfo.size()> 0){
                for(int k=0;listPdfDetHokaInfo.size()>k;k++){
                    TrnMosChikenDet trnMosChikenDetHoka = (TrnMosChikenDet)listPdfDetHokaInfo.get(k);
                    if(!trnMosChikenDetHoka.getReserverAmt().equals("0")){
                        memoCnt++;
                    }
                }
            }
            if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                    || memoCnt==0){
                memoCnt=memoCnt+memoLinage;
                if(bikouLinage>memoCnt){
                    memoCnt = bikouLinage;
                }
            }
            int diff = 0;
            if(memoCnt > bikouLinage){
                diff = memoCnt;
            }else{
                diff = bikouLinage;
            }
            if(diff>1){
                for(int j=0; diff-1>j;j++){
                    moschickenCntList.add("");
                }
            }
            return moschickenCntList;
        }
        /**
         * モスチキン5P予約数設定
         * @param listPdfInfo
         * @param dto
         */
        private List setMoschicken5PCntList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetHokaInfo,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
          ArrayList moschicken5PCntList      = new ArrayList();
          String moschicken5PCnt      = "0";
          int memoCnt=0;
          boolean isMosChicken5P      = false;
          ArrayList tempMemoList = new ArrayList();
          ArrayList tempBikouList = new ArrayList();
          tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
          int memoLinage = tempMemoList.size(); 
          tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
          int bikouLinage = tempBikouList.size();
          for(int j=0;listPdfDetInfo.size()>j;j++){
              TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listPdfDetInfo.get(j);
              if(trnMosChikenDet.getMenuCd().equals(mosChicken5PCd)){
                  isMosChicken5P = true;
                  moschicken5PCnt = trnMosChikenDet.getReserverAmt();
                  if(moschicken5PCnt.equals("0")){
                      moschicken5PCntList.add("");
                  }else{
                      moschicken5PCntList.add(moschicken5PCnt);
                  }
              }
          }
          if(!isMosChicken5P){
              moschicken5PCntList.add("");
          }
          if(listPdfDetHokaInfo.size()> 0){
              for(int k=0;listPdfDetHokaInfo.size()>k;k++){
                  TrnMosChikenDet trnMosChikenDetHoka = (TrnMosChikenDet)listPdfDetHokaInfo.get(k);
                  if(!trnMosChikenDetHoka.getReserverAmt().equals("0")){
                      memoCnt++;
                  }
              }
          }
          if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                  || memoCnt==0){
              memoCnt=memoCnt+memoLinage;
              if(bikouLinage>memoCnt){
                  memoCnt = bikouLinage;
              }
          }
          int diff = 0;
          if(memoCnt > bikouLinage){
              diff = memoCnt;
          }else{
              diff = bikouLinage;
          }
          if(diff>1){
              for(int j=0; diff-1>j;j++){
                  moschicken5PCntList.add("");
              }
          }
          return moschicken5PCntList;



      }
      /**
       * モスチキン１０Ｐ予約数設定
       * @param listPdfInfo
       * @param dto
       */
    private List setMoschicken10PCntList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetHokaInfo,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
        ArrayList moschicken10PCntList     = new ArrayList();
        String moschicken10PCnt     = "0";
        int memoCnt=0;
        boolean isMosChicken10P     = false;
        ArrayList tempMemoList = new ArrayList();
        ArrayList tempBikouList = new ArrayList();
        tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
        int memoLinage = tempMemoList.size(); 
        tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
        int bikouLinage = tempBikouList.size();
        for(int j=0;listPdfDetInfo.size()>j;j++){
            TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listPdfDetInfo.get(j);
            if(trnMosChikenDet.getMenuCd().equals(mosChicken10PCd)){
                isMosChicken10P = true;
                moschicken10PCnt = trnMosChikenDet.getReserverAmt();
                if(moschicken10PCnt.equals("0")){
                    moschicken10PCntList.add("");
                }else{
                    moschicken10PCntList.add(moschicken10PCnt);
                }
            }
            
        }
        if(!isMosChicken10P){
            moschicken10PCntList.add("");
        }
        if(listPdfDetHokaInfo.size()> 0){
            for(int k=0;listPdfDetHokaInfo.size()>k;k++){
                TrnMosChikenDet trnMosChikenDetHoka = (TrnMosChikenDet)listPdfDetHokaInfo.get(k);
                if(!trnMosChikenDetHoka.getReserverAmt().equals("0")){
                    memoCnt++;
                }
            }
        }
        if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                || memoCnt==0){
            memoCnt=memoCnt+memoLinage;
            if(bikouLinage>memoCnt){
                memoCnt = bikouLinage;
            }
        }
        int diff = 0;
        if(memoCnt > bikouLinage){
            diff = memoCnt;
        }else{
            diff = bikouLinage;
        }
        if(diff>1){
            for(int j=0; diff-1>j;j++){
                moschicken10PCntList.add("");
            }
        }
        return moschicken10PCntList;
    }
    /**
     * 冷凍モス予約数設定
     * @param listPdfInfo
     * @param dto
     */
    private List setMoschickenColdCntList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetHokaInfo,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
      ArrayList moschickenColdCntList    = new ArrayList();
      String moschickenColdCnt    = "0";
      int memoCnt=0;
      boolean isMosChickenCold    = false;
      ArrayList tempMemoList = new ArrayList();
      ArrayList tempBikouList = new ArrayList();
      tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
      int memoLinage = tempMemoList.size(); 
      tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
      int bikouLinage = tempBikouList.size();
      for(int j=0;listPdfDetInfo.size()>j;j++){
          TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listPdfDetInfo.get(j);
          if(trnMosChikenDet.getMenuCd().equals(mosChickenColdCd)){
              isMosChickenCold = true;
              moschickenColdCnt = trnMosChikenDet.getReserverAmt();
              if(moschickenColdCnt.equals("0")){
                  moschickenColdCntList.add("");
              }else{
                  moschickenColdCntList.add(moschickenColdCnt);
              }
          }
          
      }
      if(!isMosChickenCold){
          moschickenColdCntList.add("");
      }
      if(listPdfDetHokaInfo.size()> 0){
          for(int k=0;listPdfDetHokaInfo.size()>k;k++){
              TrnMosChikenDet trnMosChikenDetHoka = (TrnMosChikenDet)listPdfDetHokaInfo.get(k);
              if(!trnMosChikenDetHoka.getReserverAmt().equals("0")){
                  memoCnt++;
              }
          }
      }
      if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
              || memoCnt==0){
          memoCnt=memoCnt+memoLinage;
          if(bikouLinage>memoCnt){
              memoCnt = bikouLinage;
          }
      }
      int diff = 0;
      if(memoCnt > bikouLinage){
          diff = memoCnt;
      }else{
          diff = bikouLinage;
      }
      if(diff>1){
          for(int j=0; diff-1>j;j++){
              moschickenColdCntList.add("");
          }
      }
      return moschickenColdCntList;



  }

      /**
       * 冷凍5P予約数設定
       * @param listPdfInfo
       * @param dto
       */
    private List setMoschicken5PColdCnList(List listPdfInfo, MosChickenRefConfListDto dto,List listPdfDetHokaInfo,List listPdfDetInfo,TrnMosChikenInfo trnMosChikenInfo) {
        ArrayList moschicken5PColdCntList  = new ArrayList();
        String moschicken5PColdCnt  = "0";
        int memoCnt=0;
        boolean isMosChickenCold5P  = false;
        ArrayList tempMemoList = new ArrayList();
        ArrayList tempBikouList = new ArrayList();
        tempMemoList = (ArrayList) createMemo(tempMemoList, trnMosChikenInfo.getMemo(),MEMO_LINE,true);
        int memoLinage = tempMemoList.size(); 
        tempBikouList = (ArrayList) createBikou(tempBikouList,trnMosChikenInfo.getRemark(),BIKOU_LINE,true);
        int bikouLinage = tempBikouList.size();
        for(int j=0;listPdfDetInfo.size()>j;j++){
            TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)listPdfDetInfo.get(j);
            if(trnMosChikenDet.getMenuCd().equals(mosChicken5PColdCd)){
                isMosChickenCold5P = true;
                moschicken5PColdCnt = trnMosChikenDet.getReserverAmt();
                if(moschicken5PColdCnt.equals("0")){
                    moschicken5PColdCntList.add("");
                }else{
                    moschicken5PColdCntList.add(moschicken5PColdCnt);
                }
            }
            
        }
        if(!isMosChickenCold5P){
            moschicken5PColdCntList.add("");
        }
        if(listPdfDetHokaInfo.size()> 0){
            for(int k=0;listPdfDetHokaInfo.size()>k;k++){
                TrnMosChikenDet trnMosChikenDetHoka = (TrnMosChikenDet)listPdfDetHokaInfo.get(k);
                if(!trnMosChikenDetHoka.getReserverAmt().equals("0")){
                    memoCnt++;
                }
            }
        }
        if((memoCnt>0 && !trnMosChikenInfo.getMemo().equals(""))
                || memoCnt==0){
            memoCnt=memoCnt+memoLinage;
            if(bikouLinage>memoCnt){
                memoCnt = bikouLinage;
            }
        }
        int diff = 0;
        if(memoCnt > bikouLinage){
            diff = memoCnt;
        }else{
            diff = bikouLinage;
        }
        if(diff>1){
            for(int j=0; diff-1>j;j++){
                moschicken5PColdCntList.add("");
            }
        }
        return moschicken5PColdCntList;
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
     *  ヘッダー設定処理
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
        PdfPCell cell0101 = new PdfPCell(new Phrase("予約販売確認表B",font_g16));
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
//        PdfPTable koumokuTable = new PdfPTable(9);
//        koumokuTable.setWidthPercentage(97f);
//        int koumokuTableWidth[] = {10, 24, 5, 5, 5, 5, 5, 8, 29};
//        koumokuTable.setWidths(koumokuTableWidth);
        PdfPTable koumokuTable = new PdfPTable(PDF_LIST_CELL_SIZE);
        koumokuTable.setWidthPercentage(PDF_TABLE_WIDTH_SIZE);
        koumokuTable.setWidths(PDF_LIST_CELL_WIDTH_SIZE);
        
        Color backColor = new Color(204,255,255);

        //1行
        PdfPCell hcell0201 = new PdfPCell(new Phrase("お渡し",font_g10));
        PdfPCell hcell0301 = new PdfPCell(new Phrase("No.備考", font_g10));
        PdfPCell hcell0401 = new PdfPCell(new Phrase("チキン", font_g10));
        PdfPCell hcell0501 = new PdfPCell(new Phrase("５Ｐ", font_g10));
        PdfPCell hcell0601 = new PdfPCell(new Phrase("１０Ｐ", font_g10));
        PdfPCell hcell0701 = new PdfPCell(new Phrase("冷", font_g10));
        PdfPCell hcell0801 = new PdfPCell(new Phrase("冷５Ｐ", font_g10));
        PdfPCell hcell1001 = new PdfPCell(new Phrase("代金", font_g10));
        PdfPCell hcell1101 = new PdfPCell(new Phrase("メモ", font_g10));
        hcell0201.setBackgroundColor(backColor);
        hcell0301.setBackgroundColor(backColor);
        hcell0401.setBackgroundColor(backColor);
        hcell0501.setBackgroundColor(backColor);
        hcell0601.setBackgroundColor(backColor);
        hcell0701.setBackgroundColor(backColor);
        hcell0801.setBackgroundColor(backColor);
        hcell1001.setBackgroundColor(backColor);
        hcell1101.setBackgroundColor(backColor);

        hcell0201.enableBorderSide(15);
        hcell0301.enableBorderSide(15);
        hcell0401.enableBorderSide(15);
        hcell0501.enableBorderSide(15);
        hcell0601.enableBorderSide(15);
        hcell0701.enableBorderSide(15);
        hcell0801.enableBorderSide(15);
        hcell1001.enableBorderSide(15);
        hcell1101.enableBorderSide(15);

        hcell0201.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0301.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0401.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0501.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0601.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0701.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell0801.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell1001.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcell1101.setHorizontalAlignment(Element.ALIGN_CENTER);

        hcell0201.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0301.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0401.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0501.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0601.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0701.setVerticalAlignment(Element.ALIGN_TOP);
        hcell0801.setVerticalAlignment(Element.ALIGN_TOP);
        hcell1001.setVerticalAlignment(Element.ALIGN_TOP);
        hcell1101.setVerticalAlignment(Element.ALIGN_TOP);

        PdfPCell hcellPremium = new PdfPCell(new Phrase("プレ", font_g10));
        hcellPremium.setBackgroundColor(backColor);
        hcellPremium.enableBorderSide(15);
        hcellPremium.setHorizontalAlignment(Element.ALIGN_CENTER);
        hcellPremium.setVerticalAlignment(Element.ALIGN_TOP);

        koumokuTable.addCell(hcell0201);
        koumokuTable.addCell(hcell0301);
        koumokuTable.addCell(hcell0401);
        koumokuTable.addCell(hcell0501);
        koumokuTable.addCell(hcell0601);
        koumokuTable.addCell(hcell0701);
        koumokuTable.addCell(hcell0801);
        koumokuTable.addCell(hcellPremium);
        koumokuTable.addCell(hcell1001);
        koumokuTable.addCell(hcell1101);

        return koumokuTable;
    }

    /**
     * 明細リスト詳細
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
        int meisaiCnt = 0;
        int bikouCntSave = 0;
        int shokiCnt = 0;
        int gyouMeisaiCnt = 0;
        Map pdfList = setMeisaiInfo(listPdfInfo,dto);
        List bikouRowspanList = (List)pdfList.get(BIKOU_ROWSPAN);
        List hourRowspanList = (List)pdfList.get(HOUR_ROWSPAN);
        List memoList = (List)pdfList.get(MEMO);
        List moneyList = (List)pdfList.get(MONEY);
        List seqList = (List)pdfList.get(SEQ_NO);
        List bikouList = (List)pdfList.get(REMARK);
        List hourList = (List)pdfList.get(HOUR);
        List premiumList = (List)pdfList.get(PREMIUM);
        List moschickenCntList = (List)pdfList.get(MOSCHICKEN_AMT);
        List moschicken5PCntList = (List)pdfList.get(MOSCHICKEN5P_AMT);
        List moschicken10PCntList = (List)pdfList.get(MOSCHICKEN10P_AMT);
        List moschickenColdCntList = (List)pdfList.get(MOSCHICKEN_COLD_AMT);
        List moschickenCold5PCntList = (List)pdfList.get(MOSCHICKEN_COLD5P_AMT);
        for(int i=0;bikouRowspanList.size()>i;i++){
            NumericFormatter numericFormatter = new NumericFormatter(); 
            if(lineCnt == initGyou){
                doc.add(getHeaderObject(dto,pageCnt));
                doc.add(getMeisaiObject(dto));
                lineCnt = lineCnt + titleGyou + headerGyou;
            }
            int bikouRowspanCnt= Integer.parseInt((String)bikouRowspanList.get(i));
            int hourRowspanCnt= Integer.parseInt((String)hourRowspanList.get(i));
            int bikouRowspanCntNext=0;
            if(i!=bikouRowspanList.size()-1){
                bikouRowspanCntNext = Integer.parseInt((String)bikouRowspanList.get(i+1)) + gyouMeisaiCnt; 
            }
            if(bikouRowspanCnt>0){
                bikouCntSave=bikouRowspanCnt;
                meisaiCnt=0;
            }

            lineCnt = lineCnt + bikouRowspanCnt;
            //同SEQ内の中間行か否か判断フラグ
            boolean middleFlg = (shokiCnt==0 && i!=0 && seqList.get(i).equals(seqList.get(i-1)));
            if(gyouMax>=lineCnt){
                meisaiCnt++;
                gyouMeisaiCnt++;
//                PdfPTable mosChickenDetailTable = new PdfPTable(9);
//                mosChickenDetailTable.setWidthPercentage(97f);
//                int shohinTableWidth[] = {10, 24, 5, 5, 5, 5, 5, 8, 29};
//                mosChickenDetailTable.setWidths(shohinTableWidth);
                PdfPTable mosChickenDetailTable = new PdfPTable(PDF_LIST_CELL_SIZE);
                mosChickenDetailTable.setWidthPercentage(PDF_TABLE_WIDTH_SIZE);
                mosChickenDetailTable.setWidths(PDF_LIST_CELL_WIDTH_SIZE);
                int bikouRowspanCntJissou= Integer.parseInt((String)bikouRowspanList.get(i));
                String reserveHour = (String)hourList.get(i);
                String bikou= (String)bikouList.get(i);
                String chickenAmt = (String)moschickenCntList.get(i);
                String chicken5PAmt = (String)moschicken5PCntList.get(i);
                String chicken10PAmt = (String)moschicken10PCntList.get(i);
                String chickenColdAmt = (String)moschickenColdCntList.get(i);
                String chicken5PColdAmt = (String)moschickenCold5PCntList.get(i);
                String money= (String)moneyList.get(i);
                String memo = (String)memoList.get(i);
                String premium= (String)premiumList.get(i);
                //外枠か否か判断フラグ
                boolean outerFrameFlg = (
                		bikouRowspanCntJissou==1 
                		|| (meisaiGyou==meisaiCnt 
                				|| meisaiCnt == bikouCntSave 
                				|| i==bikouRowspanList.size()-1 
                				|| (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))
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
                    }else if(meisaiCnt == bikouCntSave && bikouRowspanCntNext>=meisaiGyou){
                        dcell0201.setBorder(6);
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
//                        }else if(nextMesaiGyou>=gyouMax){
//                            dcell0201.setBorder(6);
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
                    if(meisaiGyou==meisaiCnt || meisaiCnt==bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0301.setBorder(6);
                    }else{
                        dcell0301.setBorder(4);
                    }
                }
                dcell0301.setVerticalAlignment(Element.ALIGN_TOP);
                dcell0301.setHorizontalAlignment(Element.ALIGN_LEFT);
                mosChickenDetailTable.addCell(dcell0301);
                PdfPCell dcell0401 = null;
                //モスチキン予約数
                if(bikouRowspanCntJissou>0){
                    dcell0401 = new PdfPCell(new Phrase(chickenAmt, font_g10));
                    if(bikouRowspanCntJissou == 1){
                        dcell0401.setBorder(6);
                    }else{
                        dcell0401.setBorder(4);
                    }
                }else{
                    dcell0401 = new PdfPCell(new Phrase(""));
                    if(meisaiGyou==meisaiCnt || meisaiCnt==bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0401.setBorder(6);
                    }else{
                        dcell0401.setBorder(4);
                    }
                }
                dcell0401.setHorizontalAlignment(Element.ALIGN_RIGHT);
                dcell0401.setVerticalAlignment(Element.ALIGN_TOP);
                mosChickenDetailTable.addCell(dcell0401);
                //モスチキン5P予約数
                PdfPCell dcell0501 = null;
                if(bikouRowspanCntJissou>0){
                    dcell0501 = new PdfPCell(new Phrase(chicken5PAmt, font_g10));
                    dcell0501.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dcell0501.setVerticalAlignment(Element.ALIGN_TOP);
                    if(bikouRowspanCntJissou == 1){
                        dcell0501.setBorder(6);
                    }else{
                        dcell0501.setBorder(4);
                    }
                }else{
                    dcell0501 = new PdfPCell(new Phrase(""));
                    if(meisaiGyou==meisaiCnt || meisaiCnt==bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0501.setBorder(6);
                    }else{
                        dcell0501.setBorder(4);
                    }
                }
                mosChickenDetailTable.addCell(dcell0501);
                //モスチキン10P予約数
                PdfPCell dcell0601 = null;
                if(bikouRowspanCntJissou>0){
                    dcell0601 = new PdfPCell(new Phrase(chicken10PAmt, font_g10));
                    dcell0601.setVerticalAlignment(Element.ALIGN_TOP);
                    dcell0601.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    if(bikouRowspanCntJissou == 1){
                        dcell0601.setBorder(6);
                    }else{
                        dcell0601.setBorder(4);
                    }
                }else{
                    dcell0601 = new PdfPCell(new Phrase(""));
                    if(meisaiGyou==meisaiCnt || meisaiCnt==bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0601.setBorder(6);
                    }else{
                        dcell0601.setBorder(4);
                    }
                }
                mosChickenDetailTable.addCell(dcell0601);
                //モスチキン冷凍予約数
                PdfPCell dcell0701 = null;
                if(bikouRowspanCntJissou>0){
                    dcell0701 = new PdfPCell(new Phrase(chickenColdAmt, font_g10));
                    dcell0701.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dcell0701.setVerticalAlignment(Element.ALIGN_TOP);
                    if(bikouRowspanCntJissou == 1){
                        dcell0701.setBorder(6);
                    }else{
                        dcell0701.setBorder(4);
                    }
                }else{
                    dcell0701 = new PdfPCell(new Phrase(""));
                    if(meisaiGyou==meisaiCnt || meisaiCnt==bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0701.setBorder(6);
                    }else{
                        dcell0701.setBorder(4);
                    }
                }
                mosChickenDetailTable.addCell(dcell0701);
                //モスチキン5P冷凍予約数
                PdfPCell dcell0801 = null;
                if(bikouRowspanCntJissou>0){
                    dcell0801 = new PdfPCell(new Phrase(chicken5PColdAmt, font_g10));
                    dcell0801.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    dcell0801.setVerticalAlignment(Element.ALIGN_TOP);
                    if(bikouRowspanCntJissou == 1){
                        dcell0801.setBorder(6);
                    }else{
                        dcell0801.setBorder(4);
                    }
                }else{
                    dcell0801 = new PdfPCell(new Phrase(""));
                    if(meisaiGyou==meisaiCnt || meisaiCnt==bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell0801.setBorder(6);
                    }else{
                        dcell0801.setBorder(4);
                    }
                }
                mosChickenDetailTable.addCell(dcell0801);
                //プレミアム受渡フラグ
                PdfPCell dcellPremium = createPremiumCell(bikouRowspanCntJissou, middleFlg, outerFrameFlg, font_g10, premium);
                mosChickenDetailTable.addCell(dcellPremium);
                //代金
                PdfPCell dcell1001 = null;
                if(bikouRowspanCntJissou>0){
                    String payMoney = numericFormatter.format(money);
                    if(shokiCnt==0 && i!=0 && seqList.get(i).equals(seqList.get(i-1))){
                        dcell1001 = new PdfPCell(new Phrase(""));
                    }else{
                        dcell1001 = new PdfPCell(new Phrase(payMoney, font_g10)); 
                    }
                    dcell1001.setVerticalAlignment(Element.ALIGN_TOP);
                    dcell1001.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    if(bikouRowspanCntJissou == 1){
                        dcell1001.setBorder(6);
                    }else{
                        dcell1001.setBorder(4);
                    }
                }else{
                    dcell1001 = new PdfPCell(new Phrase(""));
                    if(meisaiGyou==meisaiCnt || meisaiCnt == bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                        dcell1001.setBorder(6);
                    }else{
                        dcell1001.setBorder(4);
                    }
                }
                mosChickenDetailTable.addCell(dcell1001);
                //メモ
                PdfPCell dcell1101 = new PdfPCell(new Phrase(memo, font_g10));
                if(meisaiGyou==meisaiCnt || meisaiCnt == bikouCntSave || i==bikouRowspanList.size()-1 || (i!=bikouRowspanList.size()-1 && !seqList.get(i).equals(seqList.get(i+1)))){
                    dcell1101.setBorder(14);
                }else{
                    dcell1101.setBorder(12);
                }
                dcell1101.setVerticalAlignment(Element.ALIGN_TOP);
                dcell1101.setHorizontalAlignment(Element.ALIGN_LEFT);
                mosChickenDetailTable.addCell(dcell1101);
                doc.add(mosChickenDetailTable);
                shokiCnt++;;
            }else{
                //改ページ
                meisaiCnt=0;
                shokiCnt=0;
                gyouMeisaiCnt=0;
                int blankCnt = (gyouMax - titleGyou-headerGyou) - lineCnt-bikouRowspanCnt;
                if(blankCnt>0){
                    doc.add(kuuhakuObject(doc, blankCnt,font_g10));
                }
                doc.newPage();
                lineCnt = 0;
                pageCnt++;
                i--;
            }
        }
        return null;
    }
    /**
     * PDF用情報取得
     * @param listPdfInfo
     * @param dto
     * @return
     */
    private Map setMeisaiInfo(List listPdfInfo, MosChickenRefConfListDto dto) {
        HashMap pdfMap = new HashMap();
        ArrayList hourList = new ArrayList();
        ArrayList memoList = new ArrayList();
        ArrayList bikouList = new ArrayList();
        ArrayList premiumList = new ArrayList();
        ArrayList moneyList = new ArrayList();
        ArrayList seqList = new ArrayList();
        ArrayList moschickenCntList = new ArrayList();
        ArrayList moschicken5PCntList = new ArrayList();
        ArrayList moschicken10PCntList = new ArrayList();
        ArrayList moschickenColdCntList = new ArrayList();
        ArrayList moschickenCold5PCntList = new ArrayList();
        ArrayList bikouRowspanList = new ArrayList();
//        ArrayList hourRowspanList = new ArrayList();
        for(int i=0;listPdfInfo.size()>i;i++){
            TrnMosChikenInfo trnMosChikenInfo =(TrnMosChikenInfo) listPdfInfo.get(i);
            List listPdfDetInfo = setMstMenuHoka(trnMosChikenInfo,dto,i);
            List listPdfDetMstMenuInfo = setMstMenuPdfList(trnMosChikenInfo,dto,i);
            hourList.add(setHourList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            seqList.add(setSeqList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            memoList.add(setMemoList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            bikouList.add(setBikouList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
            moneyList.add(setMoneyList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
//2007/10/17 ADD start T.Kinugawa 2007年度対応
            premiumList.add(createPremiumList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
//2007/10/17 ADD end T.Kinugawa 2007年度対応
            
            moschickenCntList.add(setMoschickenCntList(listPdfInfo,dto,listPdfDetInfo,listPdfDetMstMenuInfo,trnMosChikenInfo));
            moschicken5PCntList.add(setMoschicken5PCntList(listPdfInfo,dto,listPdfDetInfo,listPdfDetMstMenuInfo,trnMosChikenInfo));
            moschicken10PCntList.add(setMoschicken10PCntList(listPdfInfo,dto,listPdfDetInfo,listPdfDetMstMenuInfo,trnMosChikenInfo));
            moschickenColdCntList.add(setMoschickenColdCntList(listPdfInfo,dto,listPdfDetInfo,listPdfDetMstMenuInfo,trnMosChikenInfo));
            moschickenCold5PCntList.add(setMoschicken5PColdCnList(listPdfInfo,dto,listPdfDetInfo,listPdfDetMstMenuInfo,trnMosChikenInfo));
            
            bikouRowspanList.add(setBikouRowspanList(listPdfInfo,dto,listPdfDetInfo,trnMosChikenInfo));
        }
        ArrayList hourResultList = resultHourList(hourList);
        ArrayList seqResultList = resultSeqList(seqList);
        ArrayList memoResultList = resultMemoList(memoList);
        ArrayList bikouResultList = resultBikouList(bikouList);
        ArrayList moneyResultList = resultMoneyList(moneyList);

        ArrayList moschicknCntResultList = resultMosCknCntList(moschickenCntList);
        ArrayList moschickn5PCntResultList = resultMosCkn5PCntList(moschicken5PCntList);
        ArrayList moschickn10PCntResultList = resultMosCkn10PCntList(moschicken10PCntList);
        ArrayList moschicknColdCntResultList = resultMosCknColdCntList(moschickenColdCntList);
        ArrayList moschicknCold5PCntResultList = resultMosCknCold5PCntList(moschickenCold5PCntList);
        ArrayList bikouRowList =  (ArrayList) resultBikouRowspanList(bikouRowspanList);
        ArrayList hourRowList = (ArrayList)resultHourRowspanList(hourList,bikouRowList);

//2007/10/17 ADD start T.Kinugawa 2007年度対応
        List premiumResultList = resultPremiumList(premiumList);
        pdfMap.put(PREMIUM,premiumResultList);
// 2007/10/17 ADD end T.Kinugawa 2007年度対応
        pdfMap.put(HOUR,hourResultList);
        pdfMap.put(MEMO,memoResultList);
        pdfMap.put(REMARK,bikouResultList);
        pdfMap.put(MONEY,moneyResultList);
        pdfMap.put(SEQ_NO,seqResultList);
        pdfMap.put(MOSCHICKEN_AMT,moschicknCntResultList);
        pdfMap.put(MOSCHICKEN5P_AMT,moschickn5PCntResultList);
        pdfMap.put(MOSCHICKEN10P_AMT,moschickn10PCntResultList);
        pdfMap.put(MOSCHICKEN_COLD_AMT,moschicknColdCntResultList);
        pdfMap.put(MOSCHICKEN_COLD5P_AMT,moschicknCold5PCntResultList);
        pdfMap.put(BIKOU_ROWSPAN,bikouRowList);
        pdfMap.put(HOUR_ROWSPAN,hourRowList);
        return pdfMap; 
        }
    /**
     * 空白行
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
     * マストメニュー取得
     * @param trnMosChikenInfo
     * @param dto
     * @param i
     * @return
     */
    private List setMstMenuPdfList(TrnMosChikenInfo trnMosChikenInfo,MosChickenRefConfListDto dto,int i) {
        List resultList = getMosChickenrefconflistTrnMosChickenDetailInfoDao().getMosChickenDetInfo(trnMosChikenInfo.getCkanriNo(),trnMosChikenInfo.getCompanyCd(),trnMosChikenInfo.getMiseCd(),Integer.parseInt(trnMosChikenInfo.getSeqNo()));
        ArrayList pdfBtypeMstList = new ArrayList();
        for(int j=0;resultList.size()>j;j++){
            TrnMosChikenDet mstList = (TrnMosChikenDet) resultList.get(j);
            boolean isMstmenu=false;
            if(mstList.getMenuCd().equals(mosChicken5PColdCd)){
                isMstmenu=true;
            }
            if(mstList.getMenuCd().equals(mosChickenFCd)){
                isMstmenu=true;
            }
            if(mstList.getMenuCd().equals(mosChickenColdCd)){
                isMstmenu=true;
            }
            if(mstList.getMenuCd().equals(mosChickenCd)){
                isMstmenu=true;
            }
            if(mstList.getMenuCd().equals(mosChicken5PCd)){
                isMstmenu=true;
            }
            if(mstList.getMenuCd().equals(mosChicken10PCd)){
                isMstmenu=true;
            }
            if(isMstmenu){
                pdfBtypeMstList.add(mstList);
            }

        }
        return pdfBtypeMstList;
    }
    /**
     * 一般商品取得
     * @param trnMosChikenInfo
     * @param dto
     * @param i
     * @return
     */
    private List setMstMenuHoka(TrnMosChikenInfo trnMosChikenInfo,MosChickenRefConfListDto dto,int i) {
        ArrayList mstHokaList = new ArrayList();
        List resultList = setPdfList(trnMosChikenInfo,dto,i);
        for(int j=0;resultList.size()>j;j++){
            TrnMosChikenDet mstList = (TrnMosChikenDet) resultList.get(j);
            boolean isMstMenuHoka = true;
            if(mstList.getMenuCd().equals(mosChicken5PColdCd)){
                isMstMenuHoka = false;                
            }
            if(mstList.getMenuCd().equals(mosChickenColdCd)){
                isMstMenuHoka = false;
            }
            if(mstList.getMenuCd().equals(mosChickenCd)){
                isMstMenuHoka = false;
            }
            if(mstList.getMenuCd().equals(mosChicken5PCd)){
                isMstMenuHoka = false;
            }
            if(mstList.getMenuCd().equals(mosChicken10PCd)){
                isMstMenuHoka = false;
            }
            if(mstList.getMenuCd().equals(mosChickenFCd)){
                isMstMenuHoka = false;
            }
            if(isMstMenuHoka){
                mstHokaList.add(mstList);
            }
        }
        for(int j = 0;mstHokaList.size()>j;j++){
            TrnMosChikenDet trnMosChikenDet  = (TrnMosChikenDet)mstHokaList.get(j);
            String menuCd = trnMosChikenDet.getMenuCd();
            for(int k = j+1;mstHokaList.size()>k;k++){
                TrnMosChikenDet repTrnMosChikenDet  = (TrnMosChikenDet)mstHokaList.get(k);
                String regMenuCd = repTrnMosChikenDet.getMenuCd();
                if(menuCd.equals(regMenuCd)){
                    mstHokaList.remove(j);
                }
            }
        }

        return mstHokaList;
    }
    /**
     * PDF用情報の取得
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
        return resultMstList;
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
     * モスチキン数リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultMosCknCntList(ArrayList moschickenCntList) {
        ArrayList moschickenCntSecList = new ArrayList();
        ArrayList moschickenResultList = new ArrayList();
        for(int i=0;moschickenCntList.size()>i;i++){
            moschickenCntSecList = (ArrayList)moschickenCntList.get(i);
            for(int j=0;moschickenCntSecList.size()>j;j++){
                moschickenResultList.add((String)moschickenCntSecList.get(j));
            }
        }
        return moschickenResultList;
    }
    /**
     * モスチキン5P数リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultMosCkn5PCntList(ArrayList moschicken5PCntList) {
        ArrayList moschicken5PCntSecList = new ArrayList();
        ArrayList moschicken5PResultList = new ArrayList();
        for(int i=0;moschicken5PCntList.size()>i;i++){
            moschicken5PCntSecList = (ArrayList)moschicken5PCntList.get(i);
            for(int j=0;moschicken5PCntSecList.size()>j;j++){
                moschicken5PResultList.add((String)moschicken5PCntSecList.get(j));
            }
        }
        return moschicken5PResultList;
    }
    /**
     * モスチキン10P数リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultMosCkn10PCntList(ArrayList moschicken10PCntList) {
        ArrayList moschicken10PCntSecList = new ArrayList();
        ArrayList moschicken10PResultList = new ArrayList();
        for(int i=0;moschicken10PCntList.size()>i;i++){
            moschicken10PCntSecList = (ArrayList)moschicken10PCntList.get(i);
            for(int j=0;moschicken10PCntSecList.size()>j;j++){
                moschicken10PResultList.add((String)moschicken10PCntSecList.get(j));
            }
        }
        return moschicken10PResultList;
    }
    /**
     * モスチキン冷凍数リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultMosCknColdCntList(ArrayList moschickenColdCntList) {
        ArrayList moschickenColdCntSecList = new ArrayList();
        ArrayList moschickenColdResultList = new ArrayList();
        for(int i=0;moschickenColdCntList.size()>i;i++){
            moschickenColdCntSecList = (ArrayList)moschickenColdCntList.get(i);
            for(int j=0;moschickenColdCntSecList.size()>j;j++){
                moschickenColdResultList.add((String)moschickenColdCntSecList.get(j));
            }
        }
        return moschickenColdResultList;
    }
    /**
     * モスチキン冷凍５P数リスト作成
     * @param hourList
     * @return
     */
    private ArrayList resultMosCknCold5PCntList(ArrayList moschickenCold5PCntList) {
        ArrayList moschickenCold5PCntSecList = new ArrayList();
        ArrayList moschickenCold5PResultList = new ArrayList();
        for(int i=0;moschickenCold5PCntList.size()>i;i++){
            moschickenCold5PCntSecList = (ArrayList)moschickenCold5PCntList.get(i);
            for(int j=0;moschickenCold5PCntSecList.size()>j;j++){
                moschickenCold5PResultList.add((String)moschickenCold5PCntSecList.get(j));
            }
        }
        return moschickenCold5PResultList;
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
        return (ArrayList)setHourResultRowspan(hourRowspanResultList,bikouRowList);
        
    }

    
    /**
     * ブラウザへの出力
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
        if(isCheck){
            bottomMemo = memo;
        }
        String nextFigure = "";
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
        String topMemo = "";
        if(isCheck){
            bottomMemo = memo;
        }
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
		    	memoLinage++;
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

}
