package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * エントリーマスタ管理の検索ロジック
 * エントリースタッフ情報を検索・設定する。
 * @author Aspac
 */
public class SearchEntryLogicImpl implements SearchEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN008L05";

    /**
     * 店統合マスタ
     */
    private MstMiseDao mstMiseMlEntryDao;

    /**
     * マスターライセンスエントリー状況
     */
    private UIEntryStateDao uiEntryStateDao;

    
    /**
     * 選択されたマスターライセンスの情報を検索する
     * @param MlEntryDto     
     * */
    public void execute(MlEntryDto dto) throws ApplicationException {
        
    	// 妥当性チェック
        validate(dto);
        
        // エントリー情報設定
        setEntryInfo(dto);
        
    }
    
    
    /**
     * エントリー情報を検索、設定する。
     */
    private void setEntryInfo(MlEntryDto dto) {
        
        // パラメータ
        String entryCd   = MlEntryCommon.ENTRYCD_LICENSE;
        String entryYear = dto.getSelectEntryMst().getEntryYear();
        String entryKai  = dto.getSelectEntryMst().getEntryKai();
        String companyCd = dto.getCondCompanyCd();
        String onerCd = dto.getCondOnerCd();
        String sysdate = DateManager.getCurrentYear(dto.getSysDate());
        
        String entryYearKai = entryYear + entryKai;
        
        // スタッフ別エントリー状況リスト取得
        List listEntryState = this.getUiEntryStateDao().getEntryState(entryCd, entryYear, entryKai, companyCd, onerCd, entryYearKai, sysdate);
        dto.setListEntryState(listEntryState);
        
        // エントリー状況リストデータ調整
        makeEntryList(dto);
        
        // 更新対象レコードのみ選抜
        makeRegistList(dto);
        
    }
    
    
    /**
     * 更新対象レコードのみを選抜する
     * ※更新対象件数をDTOにセットする。
     */
    private void makeRegistList(MlEntryDto dto){
        
        //更新対象レコードカウント
        int entryCnt = 1;
        
        List listEntry = dto.getListEntryState();
        List listRegst = new ArrayList();
        for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
            UIEntryState entry = (UIEntryState) ite.next();
            
            if((entry.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    entry.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD))) {
                
                entryCnt++;
                listRegst.add(entry);
            }
        }
        //新規エントリー欄を除いた更新件数を設定
        dto.setInsupEntryStateNum(listRegst.size());
        dto.setListEntryStateRegist(listRegst);
    }
    

    
    
    /**
     * エントリー状況リストデータ調整
     * ※エントリーリストに足りないデータを追加
     * ※能力・筆記・面接の各チェックが合格か免除の者をエントリー対象者から除く
     */
    private void makeEntryList(MlEntryDto dto){
        
        List listEntry = dto.getListEntryState();
        List listEntryNew = new ArrayList();
        
        //エントリーNo
        int entryCnt = 1;
        
        if(listEntry != null && !listEntry.isEmpty()) {
            for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
                UIEntryState entry = (UIEntryState) ite.next();

                // 総合結果が『発行待ち』『発行済み』のデータを除く
                if (MlEntryCommon.TOTAL_RESULT_FLG_HAKKOMATI.equals(entry.getTotalResult()) ||
                        MlEntryCommon.TOTAL_RESULT_FLG_HAKKOZUMI.equals(entry.getTotalResult())) {
                    continue;
                }
                
                // ライセンス合格者をリストから除く
                // 
                if (entry.getReentryBaseYear().equals(dto.getEntryYear())) {
                    if ((MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
                            MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())) &&
                            (MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) || 
                                    MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())) &&
                                    (MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result()) ||
                                            MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result()) || 
                                            MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA.equals(entry.getSub3Result())))
                    {
                        continue;
                    }
                }

                // 再エントリー基準年度 <> 当年度の場合
                if (!entry.getReentryBaseYear().equals(dto.getEntryYear())) {
                    if(entry.getTotalLastKai().equals("001")){
                        //例外　前提条件未終了時で全チェックが合格である場合には『新規エントリー』
                        if ((MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
                                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())) &&
                                (MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) || 
                                        MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())) &&
                                        (MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result()))) 
                        {
                            if(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD.equals(entry.getStateFlg())){
                                entry.setStateFlg(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD);
                                entry.setExamNo("");
                            }
                            if(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_RECORD.equals(entry.getStateFlg())){
                                entry.setStateFlg(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_NO_RECORD);
                                entry.setExamNo("");
                            }
                        }
                    }
                    else {
                        // 能力、筆記、面接が全て合格または免除の場合、リストの対象外
                        if ((MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
                                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())) &&
                                (MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) || 
                                        MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())) &&
                                        (MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result()) ||
                                                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result()))) 
                        {
                            continue;
                        }
                    }
                }
                
                // 前回開催の受験で能力・筆記が合格or免除、面接が不合格はリスト対象外
                // 対象外にすべきでないので、コメントアウト 2022.05.10 fukasawa  from 
//                if ("1".equals(entry.getBeforeFlg())) {
//                    if ((MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
//                            MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())) &&
//                            (MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) || 
//                                    MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())) &&
//                                    (MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub3Result()))) 
//                    {
//                        continue;
//                    }
//                }
                // 対象外にすべきでないので、コメントアウト 2022.05.10 fukasawa  to 
                
                
                // エントリー済＋履歴あり
                if(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD.equals(entry.getStateFlg())){
                    entry.setEntryNo(String.valueOf(entryCnt++));
                    setEntryRecordAbilityFlg(entry);
                    setEntryRecordExamFlg(entry);
                    setEntryRecordInterviewFlg(entry);
                    
                    setEntryRecordAbilityChk(entry);
                    setEntryRecordExamChk(entry);
                    setEntryRecordInterviewChk(entry);
                    
                }
                // エントリー済＋履歴なし
                else if(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD.equals(entry.getStateFlg())) {
                    entry.setEntryNo(String.valueOf(entryCnt++));
                    setEntryNoRecordAbilityFlg(entry);
                    setEntryNoRecordExamFlg(entry);
                    setEntryNoRecordInterviewFlg(entry);
                    
                    setEntryNoRecordAbilityChk(entry);
                    setEntryNoRecordExamChk(entry);
                    setEntryNoRecordInterviewChk(entry);
                }
                // エントリー未＋履歴あり
                else if(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_RECORD.equals(entry.getStateFlg())) {
                    setNoEntryRecordAbilityFlg(entry);
                    setNoEntryRecordExamFlg(entry);
                    setNoEntryRecordInterviewFlg(entry);
                    
                    setNoEntryRecordAbilityChk(entry);
                    setNoEntryRecordExamChk(entry);
                    setNoEntryRecordInterviewChk(entry);
                }
                // エントリー未＋履歴なし
                else if(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_NO_RECORD.equals(entry.getStateFlg())) {
                    setNoEntryNoRecordAbilityFlg(entry);
                    setNoEntryNoRecordExamFlg(entry);
                    setNoEntryNoRecordInterviewFlg(entry);
                    
                    setNoEntryNoRecordAbilityChk(entry);
                    setNoEntryNoRecordExamChk(entry);
                    setNoEntryNoRecordInterviewChk(entry);
                }
                listEntryNew.add(entry);
            }
        }
        dto.setListEntryState(listEntryNew);
    }
    
    //**************************************************************::
    //  能力・筆記・履歴フラグを設定する。
    //　true  : 受験・免除
    //  false : 不合格・未受験・受験不可
    //**************************************************************::
    
    
    /**
     * エントリー済み＋履歴ありレコードの能力フラグを設定する
     * @param entry
     */
    private void setEntryRecordAbilityFlg(UIEntryState entry){
        // 能力チェックフラグ設定
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())){
            entry.setAbilityFlg(true);
        }
        else if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub1Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN.equals(entry.getSub1Result())){
            if (MlEntryCommon.CHK_STATE_JYUKEN.equals(entry.getAbilityChk()) ||
                    MlEntryCommon.CHK_STATE_MENJO.equals(entry.getAbilityChk())) {
                entry.setAbilityFlg(true);
            }
            else {
                entry.setAbilityFlg(false);
            }
        }
        else if(MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA.equals(entry.getSub1Result())){
            entry.setAbilityFlg(false);
        }
    }
    /**
     * エントリー済み＋履歴ありレコードの筆記フラグを設定する
     * @param entry
     */
    private void setEntryRecordExamFlg(UIEntryState entry){
        // 筆記チェックフラグ設定
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())){
            entry.setExamFlg(true);
        }
        else if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub2Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN.equals(entry.getSub2Result())){
            if (MlEntryCommon.CHK_STATE_JYUKEN.equals(entry.getExamChk()) ||
                    MlEntryCommon.CHK_STATE_MENJO.equals(entry.getExamChk())) {
                entry.setExamFlg(true);
            }
            else {
                entry.setExamFlg(false);
            }
        }
        else if(MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA.equals(entry.getSub2Result())){
            entry.setExamFlg(false);
        }
    }
    /**
     * エントリー済み＋履歴ありレコードの面接フラグを設定する
     * @param entry
     */
    private void setEntryRecordInterviewFlg(UIEntryState entry){
        // 面接チェックフラグ設定
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result())){
            entry.setInterviewFlg(true);
        }
        else if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub3Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN.equals(entry.getSub3Result())){
            if (MlEntryCommon.CHK_STATE_JYUKEN.equals(entry.getInterviewChk()) ||
                    MlEntryCommon.CHK_STATE_MENJO.equals(entry.getInterviewChk())) {
                entry.setInterviewFlg(true);
            }
            else {
                entry.setInterviewFlg(false);
            }
        }
        else if(MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA.equals(entry.getSub3Result())){
            if (MlEntryCommon.CHK_STATE_JYUKEN.equals(entry.getInterviewChk()) ||
                    MlEntryCommon.CHK_STATE_MENJO.equals(entry.getInterviewChk())) {
                entry.setInterviewFlg(true);
            }
            else {
                entry.setInterviewFlg(false);
            }
        }
    }
    /**
     * エントリー済み＋履歴なしレコードの能力フラグを設定する
     * @param entry
     */
    private void setEntryNoRecordAbilityFlg(UIEntryState entry){
        // 能力チェック
        entry.setAbilityFlg(true);
    }
    /**
     * エントリー済み＋履歴なしレコードの筆記フラグを設定する
     * @param entry
     */
    private void setEntryNoRecordExamFlg(UIEntryState entry){
        // 筆記チェック
        entry.setExamFlg(true);
    }
    /**
     * エントリー済み＋履歴なしレコードの面接フラグを設定する
     * @param entry
     */
    private void setEntryNoRecordInterviewFlg(UIEntryState entry){
        // 面接チェック
        entry.setInterviewFlg(true);
    }
    /**
     * エントリー未＋履歴ありレコードの能力フラグを設定する
     * @param entry
     */
    private void setNoEntryRecordAbilityFlg(UIEntryState entry) {
        // 能力チェックフラグ設定
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())) {
            entry.setAbilityFlg(true);
        }
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub1Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN.equals(entry.getSub1Result()) ||
                    MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA.equals(entry.getSub1Result())) {
            entry.setAbilityFlg(false);
        }
    }
    /**
     * エントリー未＋履歴ありレコードの筆記フラグを設定する
     * @param entry
     */
    private void setNoEntryRecordExamFlg(UIEntryState entry) {
        // 筆記チェックフラグ設定
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())) {
            entry.setExamFlg(true);
        }
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub2Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN.equals(entry.getSub2Result()) ||
                    MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA.equals(entry.getSub2Result())) {
            entry.setExamFlg(false);
        }
    }
    /**
     * エントリー未＋履歴ありレコードの面接フラグを設定する
     * @param entry
     */
    private void setNoEntryRecordInterviewFlg(UIEntryState entry) {
        // 面接チェックフラグ設定
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result())) {
            entry.setInterviewFlg(true);
        }
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU.equals(entry.getSub3Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN.equals(entry.getSub3Result()) ||
                    MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA.equals(entry.getSub3Result())) {
            entry.setInterviewFlg(false);
        }
    }
    /**
     * エントリー未＋履歴なしレコードの能力フラグを設定する
     * @param entry
     */
    private void setNoEntryNoRecordAbilityFlg(UIEntryState entry) {
        entry.setAbilityFlg(true);
    }
    /**
     * エントリー未＋履歴なしレコードの筆記フラグを設定する
     * @param entry
     */
    private void setNoEntryNoRecordExamFlg(UIEntryState entry) {
        entry.setExamFlg(true);
    }
    /**
     * エントリー未＋履歴なしレコードの面接フラグを設定する
     * @param entry
     */
    private void setNoEntryNoRecordInterviewFlg(UIEntryState entry) {
        entry.setInterviewFlg(true);
    }
    
    
    //**************************************************************::
    //  能力・筆記・履歴チェックを設定する。
    //　0: 受験  1: 免除  2: 受験不可  3: 未受験 
    //**************************************************************::
    
    /**
     * エントリー済＋履歴あり
     * レコードの能力チェックを設定する
     */
    private void setEntryRecordAbilityChk(UIEntryState entry) {
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())) {
            entry.setAbilityChk(MlEntryCommon.CHK_STATE_MENJO);
        }
        else {
            if(entry.isAbilityFlg()) entry.setAbilityChk(MlEntryCommon.CHK_STATE_JYUKEN);
            else entry.setAbilityChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
        }
    }
    
    /**
     * エントリー済＋履歴あり
     * レコードの筆記チェックを設定する
     */
    private void setEntryRecordExamChk(UIEntryState entry) {
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())) {
            entry.setExamChk(MlEntryCommon.CHK_STATE_MENJO);
        }
        else {
            if(entry.isExamFlg()) entry.setExamChk(MlEntryCommon.CHK_STATE_JYUKEN);
            else entry.setExamChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
        }
    } 

    /**
     * エントリー済＋履歴あり
     * レコードの面接チェックを設定する
     */
    private void setEntryRecordInterviewChk(UIEntryState entry) {
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result())) {
            entry.setInterviewChk(MlEntryCommon.CHK_STATE_MENJO);
        }
//delete 2019/4/16 xou.zoubun 前回不合格でも面接受験可能にする
//        else {
//            if(MlEntryCommon.BEFORE_SUB3_JYUKEN.equals(entry.getBeforeFlg())) {
//                entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN_FUKA);
//            }
            else {
                if(entry.isInterviewFlg()) entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
                else entry.setInterviewChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
            }
//        }
    }
    
    /**
     * エントリー済＋履歴なし
     * レコードの能力チェックを設定する
     */
    private void setEntryNoRecordAbilityChk(UIEntryState entry) {
        if(entry.isAbilityFlg()) entry.setAbilityChk(MlEntryCommon.CHK_STATE_JYUKEN);
        else entry.setAbilityChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
    }
    
    /**
     * エントリー済＋履歴なし
     * レコードの筆記チェックを設定する
     */
    private void setEntryNoRecordExamChk(UIEntryState entry) {
        if(entry.isExamFlg()) entry.setExamChk(MlEntryCommon.CHK_STATE_JYUKEN);
        else entry.setExamChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
    }

    /**
     * エントリー済＋履歴なし
     * レコードの面接チェックを設定する
     */
    private void setEntryNoRecordInterviewChk(UIEntryState entry) {
        if(entry.isInterviewFlg()) entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
        else entry.setInterviewChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
    }
    
    
    /**
     * エントリー未＋履歴あり
     * レコードの能力チェックを設定する
     */
    private void setNoEntryRecordAbilityChk(UIEntryState entry) {
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub1Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub1Result())){
            entry.setAbilityChk(MlEntryCommon.CHK_STATE_MENJO);
        }
        else {
            if(entry.isAbilityFlg()) entry.setAbilityChk(MlEntryCommon.CHK_STATE_JYUKEN);
            else entry.setAbilityChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
        }
    }
    
    /**
     * エントリー未＋履歴あり
     * レコードの筆記チェックを設定する
     */
    private void setNoEntryRecordExamChk(UIEntryState entry) {
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub2Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub2Result())){
            entry.setExamChk(MlEntryCommon.CHK_STATE_MENJO);
        }
        else {
            if(entry.isExamFlg()) entry.setExamChk(MlEntryCommon.CHK_STATE_JYUKEN);
            else entry.setExamChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
        }
    }

    /**
     * エントリー未＋履歴あり
     * レコードの面接チェックを設定する
     */
    private void setNoEntryRecordInterviewChk(UIEntryState entry) {
        if(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU.equals(entry.getSub3Result()) ||
                MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO.equals(entry.getSub3Result())){
            entry.setInterviewChk(MlEntryCommon.CHK_STATE_MENJO);
        }
//delete 2019/4/16 xou.zoubun 前回不合格でも面接受験可能にする
//        else {
//            if(MlEntryCommon.BEFORE_SUB3_JYUKEN.equals(entry.getBeforeFlg())) {
//                entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN_FUKA);
//            }
            else {
                if(entry.isInterviewFlg()) entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
                else entry.setInterviewChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
            }
//        }
    }
    
    /**
     * エントリー未＋履歴なし
     * レコードの能力チェックを設定する
     */
    private void setNoEntryNoRecordAbilityChk(UIEntryState entry) {
        if(entry.isAbilityFlg()) entry.setAbilityChk(MlEntryCommon.CHK_STATE_JYUKEN);
        else entry.setAbilityChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
    }
    
    /**
     * エントリー未＋履歴なし
     * レコードの筆記チェックを設定する
     */
    private void setNoEntryNoRecordExamChk(UIEntryState entry) {
        if(entry.isExamFlg()) entry.setExamChk(MlEntryCommon.CHK_STATE_JYUKEN);
        else entry.setExamChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
    }

    /**
     * エントリー未＋履歴なし
     * レコードの面接チェックを設定する
     */
    private void setNoEntryNoRecordInterviewChk(UIEntryState entry) {
        if(entry.isInterviewFlg()) entry.setInterviewChk(MlEntryCommon.CHK_STATE_JYUKEN);
        else entry.setInterviewChk(MlEntryCommon.CHK_STATE_MIJYUKEN);
    }
    
    
    
    
    /**
     * 必須、妥当性チェック
     * @param MlEntryDto
     */
    private void validate(MlEntryDto dto) throws ApplicationException {
        String companyCd = dto.getCondCompanyCd();
        String miseCd    = dto.getCondMiseCd();
        
        if (dto.getListEntryMst() == null) {
            throw new NotSelectedException("対象のコース", "indexFlg", 0);
        }
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード", "condCompanyCd", 0);
        }
        
        if (MlEntryCommon.USER_TYPE_CD_HONBU.equals(dto.getUserTypeCd()) && isNull(miseCd)) {
            throw new NotNullException("対象店舗", "condMiseCd", 0);
        }
    }


    
    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    public MstMiseDao getMstMiseMlEntryDao() {
        return mstMiseMlEntryDao;
    }

    public void setMstMiseMlEntryDao(MstMiseDao mstMiseMlEntryDao) {
        this.mstMiseMlEntryDao = mstMiseMlEntryDao;
    }

        
    public UIEntryStateDao getUiEntryStateDao() {
        return uiEntryStateDao;
    }
    public void setUiEntryStateDao(
            UIEntryStateDao uiEntryStateDao) {
        this.uiEntryStateDao = uiEntryStateDao;
    }
        
    
}
