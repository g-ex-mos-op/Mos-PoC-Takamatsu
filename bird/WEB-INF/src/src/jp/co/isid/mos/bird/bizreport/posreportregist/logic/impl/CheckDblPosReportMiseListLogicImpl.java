package jp.co.isid.mos.bird.bizreport.posreportregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIPosReportMiseInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.CheckDblPosReportMiseListLogic;

/**
 * POS速報設定リスト重複チェックロジック
 * @author Aspac
 *
 */
public class CheckDblPosReportMiseListLogicImpl implements CheckDblPosReportMiseListLogic {
    
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR007L03";
    
    
    /**
     * POS速報設定リストの重複チェックを行う
     * 処理ステータスの設定を行う
     * 
     * @return boolean true:重複データが存在する
     */
    public boolean execute(List listRegistData, String sysdate) {
        
        List insertList = new ArrayList();
        List existsList = new ArrayList();
        List newEntityList = new ArrayList();
        
        // 重複チェックフラグ
        boolean isDblFlg = false;
        
        // 新規更新データリスト・既存データリストを生成
        for (Iterator ite = listRegistData.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo entity = (UIPosReportMiseInfo) ite.next();
            entity.setDblFlg(false);
            if(entity.isInsertFlg()) {
                entity.setProcState(PosReportRegistConstants.PRO_STATE_INS);
                insertList.add(entity);
            }
            else {
                if(!entity.getDelFlg()){
                    existsList.add(entity);
                }
            }
        }
        
        
        // 既存データ
        for (Iterator ite1 = existsList.iterator(); ite1.hasNext();) {
            UIPosReportMiseInfo extEntity = (UIPosReportMiseInfo) ite1.next();
            
            String extMiseCd = extEntity.getMiseCd();
            boolean deleteFlg = extEntity.getDelFlg();
            
            if(deleteFlg) {
                extEntity.setProcState(PosReportRegistConstants.PRO_STATE_DEL);
            }
            
            
            // 新規更新データ
            for (Iterator ite2 = insertList.iterator(); ite2.hasNext();) {
                UIPosReportMiseInfo insEntity = (UIPosReportMiseInfo) ite2.next();
                String insMiseCd = insEntity.getMiseCd();
                
                // 店舗コード(既存) == 店舗コード(新規)
                if(extMiseCd.equals(insMiseCd)) {
                    
                    // 集信開始日(既存) == 集信開始日(新規)
                    if(extEntity.getShuDtSta().compareTo(insEntity.getShuDtSta()) == 0) {
                        insEntity.setProcState(PosReportRegistConstants.PRO_STATE_UPD);
                        extEntity.setProcState(PosReportRegistConstants.PRO_STATE_DEL);
                        extEntity.setDblFlg(true);
                        isDblFlg = true;                        
                    }
                    
                    // 集信開始日(既存) > 集信開始日(新規)
                    else if(extEntity.getShuDtSta().compareTo(insEntity.getShuDtSta()) > 0) {
                        
                        // 集信終了日(新規) >= 集信開始日(既存)
                        if(insEntity.getShuDtEnd().compareTo(extEntity.getShuDtSta()) >= 0) {
                            insEntity.setProcState(PosReportRegistConstants.PRO_STATE_UPD);
                            extEntity.setDblFlg(true);
                            isDblFlg = true;                            
                            extEntity.setProcState(PosReportRegistConstants.PRO_STATE_DEL);
                        }
                        
                        // 集信終了日(新規) < 集信開始日(既存)
                        else if(insEntity.getShuDtEnd().compareTo(extEntity.getShuDtSta()) < 0) {
                            //insEntity.setProcState(PosReportRegistConstants.PRO_STATE_INS);
                        }
                        
                    }
                    
                    // 集信開始日(既存) < 集信開始日(新規)
                    else if(extEntity.getShuDtSta().compareTo(insEntity.getShuDtSta()) < 0) {
                    
                        // 集信終了日(既存) >= 集信開始日(新規)
                        if(extEntity.getShuDtEnd().compareTo(insEntity.getShuDtSta()) >= 0) {
                            insEntity.setProcState(PosReportRegistConstants.PRO_STATE_UPD);
                            extEntity.setDblFlg(true);
                            isDblFlg = true;
                            extEntity.setProcState(PosReportRegistConstants.PRO_STATE_DEL);
                        }
                        
                        // 集信終了日(既存) < 集信開始日(新規)
                        else if(extEntity.getShuDtEnd().compareTo(insEntity.getShuDtSta()) < 0) {
                            //insEntity.setProcState(PosReportRegistConstants.PRO_STATE_INS);
                        }
                    }
                }
                
                // 店舗コード(既存) <> 店舗コード(新規)
                else {
                    //insEntity.setProcState(PosReportRegistConstants.PRO_STATE_INS);
                }
            }
        }
        
        newEntityList.addAll(existsList);
        newEntityList.addAll(insertList);
        setEntity(listRegistData, newEntityList);
        
        return isDblFlg;
    }
    
    
    /**
     * 重複チェック結果をPOSリストに反映する
     * @param listRegistData
     * @param listNewEntity
     */
    private void setEntity(List listRegistData, List listNewEntity) {
        
        for (Iterator ite1 = listRegistData.iterator(); ite1.hasNext();) {
            UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite1.next();
            
            for (Iterator ite2 = listNewEntity.iterator(); ite2.hasNext();) {
                UIPosReportMiseInfo entity = (UIPosReportMiseInfo) ite2.next();            
            
                if(entity.getSeqNo().equals(posinfo.getSeqNo())) {
                    posinfo.setProcState(entity.getProcState());
                    posinfo.setDblFlg(entity.getDblFlg());
                    break;
                }
            }
        }
    }
    
    
}
