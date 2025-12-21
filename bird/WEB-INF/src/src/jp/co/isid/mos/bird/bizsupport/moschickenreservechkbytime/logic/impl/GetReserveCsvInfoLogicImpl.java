/*
 * 作成日: 2006/10/12
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UIReserveInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UIReserveSumInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao.UIReserveTimeInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dto.MosChickenReserveChkBytimeDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveTotalInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.util.MosChickenReserveChkBytimeUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;


/**
 * 時間帯別予約状況確認表CSV明細情報を取得ロジック
 * 
 * @author xlee
 */
public class GetReserveCsvInfoLogicImpl implements CsvOutputLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS019L04";
    
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

    /**
     * CSV出力用のデータ取得処理
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        //出力用リスト
        List outputList = new ArrayList();
        MosChickenReserveChkBytimeDto dto = (MosChickenReserveChkBytimeDto)csvOutputDto;
        
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
        
        List totalList = MosChickenReserveChkBytimeUtil.makeCsvInfoList(reserveList, reserveTimeList, reserveSumList,dto);
        //リストの最初だけがCSVのタイトルになる
        NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);
        
        Map tmpTotal = (HashMap)totalList.get(0);
        UIReserveTotalInfo uiReserveTotalInfoT = (UIReserveTotalInfo) tmpTotal.get(MosChickenReserveChkBytimeUtil.KBN_TITLE);
        List header1 = new ArrayList();
        header1.add("対象店舗:");
        header1.add(uiReserveTotalInfoT.getTenpoCd()+ " " +uiReserveTotalInfoT.getTenpoNm());
        header1.add("");
        header1.add("時間帯:");
		//時間帯の範囲：
		String startTimeZn = "";
		String endTimeZn = "";
		if(uiReserveTotalInfoT.getTimeUnit().equals("15") || 
				uiReserveTotalInfoT.getTimeUnit().equals("30")) {
			startTimeZn = uiReserveTotalInfoT.getTimeStart().substring(0,2) + ":00";
			endTimeZn = ((Integer.parseInt(uiReserveTotalInfoT.getTimeEnd().substring(0,2)) + 1) < 10) ?  
    					"0" + (Integer.parseInt(uiReserveTotalInfoT.getTimeEnd().substring(0,2)) + 1) + ":00" : 
    						(Integer.parseInt(uiReserveTotalInfoT.getTimeEnd().substring(0,2)) + 1) + ":00";
		} else {
			startTimeZn = uiReserveTotalInfoT.getTimeStart().substring(0,2) + ":" 
					+ uiReserveTotalInfoT.getTimeStart().substring(2,4);
			endTimeZn = ((Integer.parseInt(uiReserveTotalInfoT.getTimeEnd().substring(0,2)) + 1) < 10) ? 
					"0" + (Integer.parseInt(uiReserveTotalInfoT.getTimeEnd().substring(0,2)) + 1) + ":" + uiReserveTotalInfoT.getTimeStart().substring(2,4) : 
						(Integer.parseInt(uiReserveTotalInfoT.getTimeEnd().substring(0,2)) + 1) + ":" + uiReserveTotalInfoT.getTimeEnd().substring(2,4);
		}
        header1.add(startTimeZn + "から" + endTimeZn);
        outputList.add(header1);

        //ヘッダー２行目
        List header2 = new ArrayList();
        header2.add("対象日付:");
        header2.add(MosChickenReserveChkBytimeUtil.formatYMD(uiReserveTotalInfoT.getReserveDt())); //YYYY/MM/DD
        header2.add("");
        header2.add("時間単位:");
        String tmpUnitNm = "";
        if(uiReserveTotalInfoT.getTimeUnit().equals("15") || uiReserveTotalInfoT.getTimeUnit().equals("30")) {
        	tmpUnitNm = uiReserveTotalInfoT.getTimeUnit()+ "分単位";
        } else {
        	tmpUnitNm = "1時間単位";
        }
        header2.add(tmpUnitNm);
        outputList.add(header2);
        //ヘッダー３行目(空)
        List space1 = new ArrayList();
        outputList.add(space1);
        
        //CSV出力用LISTの作成
        for(int i=1 ; i < totalList.size(); i++){
        	Map tmpTotalInfoMap = (HashMap)totalList.get(i);
        	
        	UIReserveTotalInfo subTltInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_SUBTITLE);
        	
        	if(subTltInfo != null && !(subTltInfo.getShohinGroupCd() == null || 
        			subTltInfo.getShohinGroupCd().equals(""))) {
        		
	            //サーブヘッダー４行目(商品グループ)
	            List header4 = new ArrayList();
	            header4.add("商品グループ");
	            header4.add(subTltInfo.getShohinGroupNm());
	            outputList.add(header4);
        	}
            
            UIReserveTotalInfo meisaiInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_MEISAI);
            if(meisaiInfo != null) {
            	if((meisaiInfo.getMenuCd() == null || 
	            		meisaiInfo.getMenuCd().equals(""))) {
		            //データ部ヘッダ
		            List bodyHeader = new ArrayList();
		            bodyHeader.add("商品名");
		            for(int j = 0; j < meisaiInfo.getTimeZn().size(); j++) {
		            	bodyHeader.add(meisaiInfo.getTimeZn().get(j));
		            }
		            outputList.add(bodyHeader);	            		
            	} else {
		            //②データ部作成
		            List bodyDataRow = new ArrayList();
		            //１レコード分のリストを作成
		            bodyDataRow.add(meisaiInfo.getMenuNm()); //メニュー名
		            for(int j = 0; j < meisaiInfo.getTimeZn().size(); j++) {
		            	bodyDataRow.add(meisaiInfo.getReserveAmt().get(j));
		            }
		            outputList.add(bodyDataRow);
            	}
            }
           
            //合計であれば下へ空行
            UIReserveTotalInfo subTotInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_SUBTOTAL);
            if(subTotInfo != null && !(subTotInfo.getMenuNm() == null || 
            		subTotInfo.getMenuNm().equals(""))) { 
            	List subSumRow = new ArrayList();
	            subSumRow.add(subTotInfo.getMenuNm());
	            for(int j = 0; j < subTotInfo.getTimeZn().size(); j++) {
	            	subSumRow.add(numFmtdgt0.format(subTotInfo.getReserveAmt().get(j)));
	            }
	            outputList.add(subSumRow);
	        	//(空)
	            List space2 = new ArrayList();
	            outputList.add(space2);
            }
            //総合計であれば下へ空行
            UIReserveTotalInfo totInfo = (UIReserveTotalInfo) tmpTotalInfoMap.get(MosChickenReserveChkBytimeUtil.KBN_TOTAL);
            if(totInfo != null && !(totInfo.getMenuNm() == null || 
            		totInfo.getMenuNm().equals(""))) { 
	            List sumRow = new ArrayList();
	            sumRow.add(totInfo.getMenuNm());
	            for(int j = 0; j < totInfo.getTimeZn().size(); j++) {
	            	sumRow.add(numFmtdgt0.format(totInfo.getReserveAmt().get(j)));
	            }
	            outputList.add(sumRow);
	        	//(空)
	            List space2 = new ArrayList();
	            outputList.add(space2);
            }
        }
        return outputList;
    }

    /**
     * validateメソッド
     */
    public void validate(CsvOutputDto csvOutputDto) {
        //自動生成されたメソッド・スタブ
    }

    /**
     * ファイル名取得
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
    	MosChickenReserveChkBytimeDto dto = (MosChickenReserveChkBytimeDto)csvOutputDto;
        String fileName = FILE_NAME + dto.getCondTaishoDt() + "_" + 
    		dto.getCondTenpoCd() + "_" + dto.getCondTimeUnitCd() + ".csv";
    	
        return fileName;
    }
}
