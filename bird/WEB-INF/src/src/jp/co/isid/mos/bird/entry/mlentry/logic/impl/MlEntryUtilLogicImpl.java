package jp.co.isid.mos.bird.entry.mlentry.logic.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIStaffDao;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.mlentry.logic.MlEntryUtilLogic;

/**
 * マスターライセンス共通ロジック
 * @author Aspac
 */
public class MlEntryUtilLogicImpl implements MlEntryUtilLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN008L04";
    
    
    /**
     * スタッフ情報取得Dao
     */
    private UIStaffDao uiStaffDao;
    
	public UIStaffDao getUiStaffDao() {
        return uiStaffDao;
    }
    public void setUiStaffDao(UIStaffDao uiStaffDao) {
        this.uiStaffDao = uiStaffDao;
    }
    
    
    /**
     * 新規エントリーレコード生成
     * ※既存エントリーデータに新規エントリー(insertFlg=true)が存在しない場合
     * 　新規エントリー入力用の空レコードを追加する。(画面に空の入力欄が表示)
     */
    public void makeNewEntryRec(MlEntryDto dto){
        
        List listEntryReg = dto.getListEntryStateRegist();
        
        boolean addFlg = true;
        
        // 既存のリストに新規エントリーが存在しない場合は追加
        // エントリーリストが存在しない場合無条件で追加
        if(listEntryReg != null && !listEntryReg.isEmpty()) {
            for (Iterator ite = listEntryReg.iterator(); ite.hasNext();) {
                UIEntryState entry = (UIEntryState) ite.next();
                if( entry.getStaffId() == null || entry.getStaffId().equals("") ) {
                    addFlg = false;
                }
            }
        }
        
        if(addFlg) {
            // 新規行追加
            UIEntryState entity = new UIEntryState();
            entity.setEntryCd(dto.getEntryCd());
            entity.setEntryYear(dto.getEntryYear());
            entity.setEntryKai(dto.getEntryKai());
            entity.setStaffId("");
            entity.setOnerCd(dto.getCondOnerCd());
            
            entity.setAbilityFlg(true);
            entity.setExamFlg(true);
            entity.setInterviewFlg(true);
            
            entity.setStateFlg("0");
            entity.setInsertFlg(true);
            
            listEntryReg.add(entity);
            dto.setListEntryStateRegist(listEntryReg);
        }
    }
    
    
    /**
     * 更新対象エントリーデータに表示用エントリー者Noを割り当てる
     * ※空データを含むためStaffIDが指定されていないデータもカウントする。
     * □エントリー済み＋履歴あり
     * □エントリー済み＋履歴なし
     * □新規エントリー者
     * @param listEntry
     * @return
     */
    public void addEntryNo(List listEntry){
        
        int count = 1;
        for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
            UIEntryState entryState = (UIEntryState) ite.next();
            if(entryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    entryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD) ||
                        entryState.isInsertFlg()){
                entryState.setEntryNo(String.valueOf(count));
                count++;
            }
        }
    }
    
    
    /**
     * 更新対象エントリーデータから空データを削除する。
     * @param listEntry
     * @return
     */
    public void delEntryNoDate(MlEntryDto dto){
        
        List listEntry = dto.getListEntryStateRegist();
        List listNewEntry = new ArrayList();
        int size = listEntry.size();
        for ( int i=0; i < size; i++ ) {
            UIEntryState entryState = (UIEntryState) listEntry.get(i);
            if ( entryState.getStaffId() != null && !entryState.getStaffId().equals("") ) {
                listNewEntry.add(entryState);
            }
        }
        dto.setListEntryStateRegist(listNewEntry);
        dto.setInsupEntryStateNum(listNewEntry.size());
    }
    
    
    
    
    /**
     * エントリー状況リストデータ調整
     * ※画面の能力・筆記・面接チェックボックスから
     * ※エントリー状況テーブルに登録する各ステータスを設定
     * @param List
     */
    public void makeEntryListChkStates(List listEntry){
        
        if(listEntry != null && !listEntry.isEmpty()) {
            for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
                UIEntryState entry = (UIEntryState) ite.next();
                
                // 能力チェックステータス設定
                if(entry.isAbilityFlg()) {
                    if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result()) ||
                            MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result())) {
                        entry.setAbilityChk(MlEntryCommon.CHK_STATE_MENJO);
                    }
                    else {
                        entry.setAbilityChk(MlEntryCommon.CHK_STATE_JYUKEN);
                    }
                }
                else {
                    if(!entry.getAbilityChk().equals(MlEntryCommon.CHK_STATE_JYUKEN_FUKA)) {
                        entry.setAbilityChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
                    }
                }
                
                // 筆記チェックステータス設定
                if(entry.isExamFlg()) {
                    if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result()) ||
                            MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result())) {
                        entry.setExamChk(MlEntryCommon.CHK_STATE_MENJO);
                    }
                    else {
                        entry.setExamChk(MlEntryCommon.CHK_STATE_JYUKEN);
                    }
                }
                else {
                    if(!entry.getExamChk().equals(MlEntryCommon.CHK_STATE_JYUKEN_FUKA)) {
                        entry.setExamChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
                    }
                }
                
                // 面接チェックステータス設定
                if(entry.isInterviewFlg()) {
                    if(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result()) ||
                            MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result())) {
                        entry.setInterviewChk(MlEntryCommon.CHK_STATE_MENJO);
                    }
                    else {
                        entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
                    }
                }
                else {
                    if(!entry.getInterviewChk().equals(MlEntryCommon.CHK_STATE_JYUKEN_FUKA)) {
                        if(MlEntryCommon.BEFORE_SUB3_JYUKEN.equals(entry.getBeforeFlg())) {
                            entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN_FUKA);
                        }
                        else {
                            entry.setInterviewChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
                        }
                    }
                }
            }
        }
        
    }
    
       
    
    /**
     * エントリー対象のスタッフリストを生成する。
     * @param MlEntryDto
     */
    public void makeListEntryEnableStaff(MlEntryDto dto) {
        
        List listStaff = (ArrayList)getUiStaffDao().getStaff(
                dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), 
                dto.getCondCompanyCd(), dto.getCondOnerCd());    
        
        dto.setListEntryEnableStaff(listStaff);
    }
    
    
}