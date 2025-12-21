package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIResultStateDao;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryInfo;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIResultState;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetEntryInfoLogic;

/**
 * エントリー状況取得ロジック
 * @author Aspac
 */
public class GetEntryInfoLogicImpl implements GetEntryInfoLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN008L07";

    /**
     * エントリー結果状況履歴
     */
    private UIResultStateDao uIResultStateDao;
    
    /**
     * エントリー状況
     */
    private UIEntryDao uIEntryDao;
    
	/**
	 * エントリー状況取得
	 * @return 検索結果
	 */
	public UIEntryState execute(String entryYear, String entryKai, String staffId) {
        
        UIEntryState entryState = new UIEntryState();
        
		//エントリー状況取得
        UIEntryInfo entryInfo = getUIEntryDao().selectEntryInfo(entryYear, entryKai, staffId);
        
        //エントリー結果状況履歴
        UIResultState resultState = getUIResultStateDao().selectResultStateInfo(staffId, entryYear, entryYear+entryKai);
        
        // エントリー状況ステータスフラグ設定
        if(entryInfo==null){
            if(resultState==null){
                // 未エントリー＋履歴がない：0
                entryState.setStateFlg(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_NO_RECORD);
            } else {
                // 未エントリー＋履歴がある：1
                entryState.setStateFlg(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_RECORD);
            }
        } else {
            if(resultState==null){
                // エントリー済＋履歴がない：2
                entryState.setStateFlg(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD);
            } else {
                // エントリー済＋履歴がある：3
                entryState.setStateFlg(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD);
            }
        }
        
        
        if(entryInfo!=null){
            entryState.setEntryPlaceCd(entryInfo.getEntryPlaceCd());
            entryState.setExamNo(entryInfo.getExamNo());
            entryState.setJob(entryInfo.getJob());
            entryState.setNote(entryInfo.getNote());
            entryState.setAbilityChk(entryInfo.getAbilityChk());
            entryState.setInterviewChk(entryInfo.getInterviewChk());
            entryState.setExamChk(entryInfo.getExamChk());            
            entryState.setEmpExpMonth(entryInfo.getEmpExpMonth());
            entryState.setEmpExpYear(entryInfo.getEmpExpYear());
            entryState.setPaExpMonth(entryInfo.getPaExpMonth());
            entryState.setPaExpYear(entryInfo.getPaExpYear());
        }
        
        if(resultState!=null) {
            entryState.setReentryBaseYear(resultState.getReentryBaseYear());
            entryState.setSub1Result(resultState.getSub1Result());
            entryState.setSub2Result(resultState.getSub2Result());
            entryState.setSub3Result(resultState.getSub3Result());
            entryState.setSub1LastYear(resultState.getSub1LastYear());
            entryState.setSub2LastYear(resultState.getSub2LastYear());
            entryState.setSub3LastYear(resultState.getSub3LastYear());
            entryState.setSub1LastKai(resultState.getSub1LastKai());
            entryState.setSub2LastKai(resultState.getSub2LastKai());
            entryState.setSub3LastKai(resultState.getSub3LastKai());
            entryState.setEntryCount(resultState.getEntryCount());
            entryState.setTotalResult(resultState.getTotalResult());
            entryState.setTotalLastYear(resultState.getTotalLastYear());
            entryState.setTotalLastKai(resultState.getTotalLastKai());
            entryState.setBeforeFlg(resultState.getBeforeFlg());
            
            if(entryInfo==null) {
                entryState.setExamNo(resultState.getExamNo());
            }
        }
        
        //能力チェック・筆記チェック・面接を設定
        setResultState(entryState);
        
		return entryState;
	}

    
    /**
     * 能力チェック・筆記チェック・面接を設定する
     * @param UIEntryState
     */
    private void setResultState(UIEntryState entry) {
        
        // 未エントリー＋履歴がない
        if(entry.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_NO_RECORD)) {
            entry.setAbilityChk(MlEntryCommon.CHK_STATE_JYUKEN);
            entry.setExamChk(MlEntryCommon.CHK_STATE_JYUKEN);
            entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
            entry.setAbilityFlg(true);
            entry.setExamFlg(true);
            entry.setInterviewFlg(true);
        }
        
        // 未エントリー＋履歴がある
        else if(entry.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_RECORD)) {
            
            // 能力設定
            if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result()) ||
                    MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result())) {
                entry.setAbilityChk(MlEntryCommon.CHK_STATE_MENJO);
                entry.setAbilityFlg(true);
            }
            else {
                entry.setAbilityChk(MlEntryCommon.CHK_STATE_JYUKEN);
                entry.setAbilityFlg(true);
            }
            
            // 筆記設定
            if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result()) ||
                    MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result())) {
                entry.setExamChk(MlEntryCommon.CHK_STATE_MENJO);
                entry.setExamFlg(true);
            }
            else {
                entry.setExamChk(MlEntryCommon.CHK_STATE_JYUKEN);
                entry.setExamFlg(true);
            }
            
            // 面接設定
//delete 2019/4/16 xou.zoubun 前回不合格でも面接受験可能にする
//            // 前回の試験で面接を受験
//            if(MlEntryCommon.BEFORE_SUB3_JYUKEN.equals(entry.getBeforeFlg())) {
//                if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result()) ||
//                        MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result())) {
//                    entry.setInterviewChk(MlEntryCommon.CHK_STATE_MENJO);
//                    entry.setInterviewFlg(true);
//                }
//                else if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub3Result())){
//                    entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN_FUKA);
//                    entry.setInterviewFlg(false);
//                }
//                else {
//                    entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
//                    entry.setInterviewFlg(true);
//                }
//            }
//            //前回の試験で面接を未受験
//            else {
                if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result()) ||
                        MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result())) {
                    entry.setInterviewChk(MlEntryCommon.CHK_STATE_MENJO);
                    entry.setInterviewFlg(true);
                }
                else {
                    entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
                    entry.setInterviewFlg(true);
                }
//            }
        }
        
        // エントリー済＋履歴がない
        else if(entry.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD)) {
            entry.setAbilityFlg(true);
            entry.setExamFlg(true);
            entry.setInterviewFlg(true);
        }
        
        // エントリー済＋履歴がある
        else if(entry.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD)) {
            // 能力設定
            if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result()) ||
                    MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result())) {
                entry.setAbilityFlg(true);
            }
            else {
                entry.setAbilityFlg(true);
            }
            
            // 筆記設定
            if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result()) ||
                    MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result())) {
                entry.setExamFlg(true);
            }
            else {
                entry.setExamFlg(true);
            }
            
            // 面接設定
//delete 2019/4/16 xou.zoubun 前回不合格でも面接受験可能にする
//            // 前回の試験で面接を受験
//            if(MlEntryCommon.BEFORE_SUB3_JYUKEN.equals(entry.getBeforeFlg())) {
//                if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result()) ||
//                        MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result())) {
//                    entry.setInterviewFlg(true);
//                }
//                else if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub3Result())){
//                    entry.setInterviewFlg(false);
//                }
//                else {
//                    entry.setInterviewFlg(true);
//                }
//            }
//            //前回の試験で面接を未受験
//            else {
                if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result()) ||
                        MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result())) {
                    entry.setInterviewFlg(true);
                }
                else {
                    entry.setInterviewFlg(true);
                }
//            }
        }
        
    }
    
    
    
    
    public UIEntryDao getUIEntryDao() {
        return uIEntryDao;
    }
    public void setUIEntryDao(UIEntryDao entryDao) {
        uIEntryDao = entryDao;
    }
    public UIResultStateDao getUIResultStateDao() {
        return uIResultStateDao;
    }
    public void setUIResultStateDao(UIResultStateDao resultStateDao) {
        uIResultStateDao = resultStateDao;
    }


}
