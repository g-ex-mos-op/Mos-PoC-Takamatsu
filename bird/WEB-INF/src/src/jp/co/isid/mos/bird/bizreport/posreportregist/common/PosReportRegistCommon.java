package jp.co.isid.mos.bird.bizreport.posreportregist.common;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIPosReportMiseInfo;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * POS速報設定登録 共通クラス
 * 
 */
public class PosReportRegistCommon {

    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }
    
    /**
     * 渡された文字列がNullの場合、空白("”)に変換する
     * 渡された文字列がNull以外場合、Trim()する
     * @param str 文字列
     * @return String パラメータがNULLの場合空白("")
     */
    public static String setEmpty(String str) {
    	if(str == null ||  str.trim().length() == 0){
    		return GyotaibetuNipoConstants.EMPTY;
    	}else{
    		return str.trim();
    	}
    }
    

    /**
     * SEQ Noを設定する
     * @param listPos
     */
    public static void setSeqNo(List listPos){

        int count = 1;
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite.next();
            posinfo.setSeqNo(String.valueOf(count));
            count++;
        }
    }
    /**
     * SEQ No(確認画面用)を設定する
     * @param listPos
     */
    public static void setSeqNoConfirm(List listPos){

        int count = 1;
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite.next();
            posinfo.setSeqNoConfirm(String.valueOf(count));
            count++;
        }
    }
    /**
     * POS速報設定リストに１行追加する
     * 
     * @param list POS速報設定リスト
     * @param sysdate システム日付
     */
    public static void addRowEntity(List list, String sysdate) {
        
        UIPosReportMiseInfo posinfo = new UIPosReportMiseInfo();
        String shuDtSta = "";
        String shuDtEnd = "";
        try {
        	//集信開始日初期値　→　パラメーターBIRD日付情報．システム日付け＋1
            shuDtSta = DateManager.getNextDate(sysdate, 1);
            //集信修了日初期値　→　パラメーターBIRD日付情報．システム日付け＋14
            shuDtEnd = DateManager.getNextDate(sysdate, 14);
        } catch (Exception e) {
        }
        posinfo.setShuDtSta(shuDtSta);
        posinfo.setShuDtEnd(shuDtEnd);
        //新規追加フラグ　→　true
        posinfo.setProcState(PosReportRegistConstants.PRO_STATE_INS);
        posinfo.setInsertFlg(true);
        //
        list.add(posinfo);
        //SEQを設定します。
        posinfo.setSeqNo(String.valueOf(list.size()));
    }   
    public static void sortListPos(List listPos) {
	    /**
	     * ソートクラス
	     * 
	     * ≪ソート順≫
	     *  1.集信開始日（昇順）
	     *  2.集信終了日（昇順）
	     *  3.店コード（昇順）
	     *  4.配信結果ステータス（昇順）
	     */
	    Comparator sortComparator = new Comparator() {
	        public boolean equals(Object obj) {
	            return (super.equals(obj));
	        }
	        public int compare(Object obj1, Object obj2) {
	            String val1 = getSortVal((UIPosReportMiseInfo)obj1);               
	            String val2 = getSortVal((UIPosReportMiseInfo)obj2);
	            
	            return val1.compareTo(val2);
	        }
	        /**
	         * ソート順判断値生成取得処理
	         * 
	         * @param obj
	         * @return
	         */
	        private String getSortVal(UIPosReportMiseInfo entity){
	            String val = "";
	            String shuDtStart = entity.getShuDtSta();
	            String shuDtEnd = entity.getShuDtEnd();
	            String miseCd = entity.getMiseCd();
	            String status = entity.getHaisRsltSt();
	            val = shuDtStart
	                + shuDtEnd
	                + miseCd
	                + status;
	            return val;
	        }
	    };
	    Collections.sort(listPos, sortComparator);
    }
    
    
}