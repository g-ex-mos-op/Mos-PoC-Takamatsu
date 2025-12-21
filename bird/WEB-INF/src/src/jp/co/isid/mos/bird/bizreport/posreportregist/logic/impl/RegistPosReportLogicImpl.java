package jp.co.isid.mos.bird.bizreport.posreportregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistCommon;
import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.dao.UIRealtimePosInfoDao;
import jp.co.isid.mos.bird.bizreport.posreportregist.dao.UIRealtimeStInfoDao;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIPosReportMiseInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIRealtimePosInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIRealtimeStInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.RegistPosReportLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * POS速報設定登録ロジック
 * @author Aspac
 *
 */
public class RegistPosReportLogicImpl implements RegistPosReportLogic {
    
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR007L04";
    
    /**
     * リアルタイム集信マスタDao
     */
    private UIRealtimePosInfoDao uIRealtimePosInfoDao;

    /**
     * リアルタイムステータスDao
     */
    private UIRealtimeStInfoDao uIRealtimeStInfoDao;
    

    /**
     * POS速報設定登録処理を行う
     * @return
     */
    public void execute(List listPos, String user) {
                
        List insertRtPosList = new ArrayList();
        List insertRtstPosList = new ArrayList();
        
        List deleteRtPosList = new ArrayList();
        List deleteRtstPosList = new ArrayList();
        
        
        //--------------------
        // 更新リスト生成
        //--------------------
        
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite.next();
            
            //削除対象
            if(posinfo.getDelFlg()) {
                deleteRtPosList.add(setDelRtInfo(posinfo));
                deleteRtstPosList.add(setDelRtStInfo(posinfo));
            }
            
            else {
                
                if(PosReportRegistCommon.isNull(posinfo.getProcState())) {
                    continue;
                }
                
                // 新規・変更
                if(posinfo.getProcState().equals(PosReportRegistConstants.PRO_STATE_INS) ||
                        posinfo.getProcState().equals(PosReportRegistConstants.PRO_STATE_UPD)) {
                    insertRtPosList.add(setInsRtInfo(posinfo,user));
                    insertRtstPosList.add(setInsRtStInfo(posinfo,user));
                }
                
                // 削除
                else if(posinfo.getProcState().equals(PosReportRegistConstants.PRO_STATE_DEL)) {
                    deleteRtPosList.add(setDelRtInfo(posinfo));
                    deleteRtstPosList.add(setDelRtStInfo(posinfo));
                }
                
            }
        }
        
        //-------------
        // DB更新処理
        //-------------

        // 削除処理
        if(deleteRtPosList.size() > 0 && deleteRtstPosList.size() > 0) {
                       
            for (Iterator ite = deleteRtPosList.iterator(); ite.hasNext();) {
                UIRealtimePosInfo posinfo = (UIRealtimePosInfo) ite.next();
                getUIRealtimePosInfoDao().delete(posinfo);
            }
            
            for (Iterator ite = deleteRtstPosList.iterator(); ite.hasNext();) {
                UIRealtimeStInfo stinfo = (UIRealtimeStInfo) ite.next();
                getUIRealtimeStInfoDao().delete(stinfo);
            }
            
        }
        
        
        
        // 追加処理
        if(insertRtPosList.size() > 0 && insertRtstPosList.size() > 0) {
            
            List listSeq = new ArrayList();
            
            for (Iterator ite = insertRtPosList.iterator(); ite.hasNext();) {
                UIRealtimePosInfo info = (UIRealtimePosInfo) ite.next();
                String seqNo = getUIRealtimePosInfoDao().getRealtimeBaseSeqNo(info.getHaisSijiDt());
                if(PosReportRegistCommon.isNull(seqNo)){
                    seqNo = PosReportRegistConstants.FIX_SEQ_NO;
                }
                else {
                    int seq = Integer.parseInt(seqNo);
                    seqNo = String.valueOf(++seq);
                }
                info.setHaisSijiSeq(seqNo);
                listSeq.add(seqNo);
                getUIRealtimePosInfoDao().insert(info);
            }
            
            int index = 0;
            for (Iterator ite = insertRtstPosList.iterator(); ite.hasNext();) {
                UIRealtimeStInfo stinfo = (UIRealtimeStInfo) ite.next();
                stinfo.setHaisSijiSeq((String)listSeq.get(index));
                getUIRealtimeStInfoDao().insert(stinfo);
                index++;
            }
            
        }
        
        return;
        
    }
    

    /**
     * 削除対象リアルタイム集信マスタEntity生成 
     * @param posinfo
     * @return
     */
    private UIRealtimePosInfo setDelRtInfo(UIPosReportMiseInfo posinfo) {
        UIRealtimePosInfo info = new UIRealtimePosInfo();
        
        info.setHaisSijiDt(posinfo.getHaisSijiDt());
        info.setHaisSijiSeq(posinfo.getHaisSijiSeq());
        info.setCompanyCd(posinfo.getCompanyCd());
        info.setMiseCd(posinfo.getMiseCd());
        
        return info;
    }


    /**
     * 削除対象リアルタイムステータスEntity生成
     * @param posinfo
     * @return
     */
    private UIRealtimeStInfo setDelRtStInfo(UIPosReportMiseInfo posinfo) {
        UIRealtimeStInfo info = new UIRealtimeStInfo();
        
        info.setHaisSijiDt(posinfo.getHaisSijiDt());
        info.setHaisSijiSeq(posinfo.getHaisSijiSeq());
        info.setCompanyCd(posinfo.getCompanyCd());
        info.setMiseCd(posinfo.getMiseCd());
        
        return info;
    }

    
    /**
     * 更新対象リアルタイム集信マスタEntity生成 
     * @param posinfo
     * @return
     */
    private UIRealtimePosInfo setInsRtInfo(UIPosReportMiseInfo posinfo,String user) {
        UIRealtimePosInfo info = new UIRealtimePosInfo();
        
        info.setHaisSijiDt(posinfo.getShuDtSta());//集信開始日と同一日を設定
        info.setHaisSijiSeq(posinfo.getHaisSijiSeq());
        info.setCompanyCd(posinfo.getCompanyCd());
        info.setMiseCd(posinfo.getMiseCd());
        info.setShuDtSta(posinfo.getShuDtSta());
        info.setShuDtEnd(posinfo.getShuDtEnd());
        info.setMstDt(posinfo.getShuDtSta());
        info.setHaisSijiName(PosReportRegistConstants.FIX_HAIS_SIJI_NAME);
        info.setShuTime1(PosReportRegistConstants.FIX_SHU_TIME1);
        info.setShuTime2(PosReportRegistConstants.FIX_SHU_TIME2);
        info.setShuTime3(PosReportRegistConstants.FIX_SHU_TIME3);
        info.setShuTime4(PosReportRegistConstants.FIX_SHU_TIME4);
        info.setShuTime5(PosReportRegistConstants.FIX_SHU_TIME5);
        info.setSakujoFlg("0");
        
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        info.setFirstUser(user);                      //登録ユーザ
        info.setFirstPgm(LOGIC_ID.substring(0, 6));   //登録プログラム
        info.setFirstTmsp(currentTimestamp);          //登録時タイムスタンプ
        info.setLastUser(user);                       //修正ユーザ
        info.setLastPgm(LOGIC_ID.substring(0, 6));    //修正プログラム
        info.setLastTmsp(currentTimestamp);           //修正時タイムスタンプ
        
        return info;
    }


    /**
     * 更新対象リアルタイムステータスEntity生成
     * @param posinfo
     * @return
     */
    private UIRealtimeStInfo setInsRtStInfo(UIPosReportMiseInfo posinfo, String user) {
        
        UIRealtimeStInfo info = new UIRealtimeStInfo();
        
        info.setHaisSijiDt(posinfo.getShuDtSta());//集信開始日と同一日を設定
        info.setHaisSijiSeq(posinfo.getHaisSijiSeq());
        info.setCompanyCd(posinfo.getCompanyCd());
        info.setMiseCd(posinfo.getMiseCd());
        info.setHaisRsltSt(PosReportRegistConstants.HAIS_RSLT_ST_MIJISO);
        info.setHaisDt(posinfo.getShuDtSta());
        info.setHaisTime("");
        
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        info.setFirstUser(user);                        //登録ユーザ
        info.setFirstPgm(LOGIC_ID.substring(0, 6));     //登録プログラム
        info.setFirstTmsp(currentTimestamp);            //登録時タイムスタンプ
        info.setLastUser(user);                         //修正ユーザ
        info.setLastPgm(LOGIC_ID.substring(0, 6));      //修正プログラム
        info.setLastTmsp(currentTimestamp);             //修正時タイムスタンプ
        
        return info;
    }
    


    public UIRealtimePosInfoDao getUIRealtimePosInfoDao() {
        return uIRealtimePosInfoDao;
    }
    public void setUIRealtimePosInfoDao(UIRealtimePosInfoDao realtimePosInfoDao) {
        uIRealtimePosInfoDao = realtimePosInfoDao;
    }

    public UIRealtimeStInfoDao getUIRealtimeStInfoDao() {
        return uIRealtimeStInfoDao;
    }
    public void setUIRealtimeStInfoDao(UIRealtimeStInfoDao realtimeStInfoDao) {
        uIRealtimeStInfoDao = realtimeStInfoDao;
    }
    
}
