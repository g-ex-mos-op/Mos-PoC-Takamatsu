package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import jp.co.isid.mos.bird.storemanage.staffregist.dao.TrnStaffStateDao;
import jp.co.isid.mos.bird.storemanage.staffregist.logic.CheckStaffStateLogic;

/**
 * 編集対象のスタッフが編集可能であることを確認する
 * @aeuthor Aspac
 *
 */
public class CheckStaffStateLogicImpl implements CheckStaffStateLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BSM004L07";

	private TrnStaffStateDao trnStaffStateDao;

    
    /**
     * 編集対象のスタッフが編集可能であることを確認する
     * ※過去１度でも研修を受講しているスタッフ情報は変更できない
     * ※過去１度でもMLにエントリーしているスタッフ情報は変更できない
     *   また、受験番号が採番されているスタッフ情報は変更できない
     * 
     * @param staffId スタッフID
     * @return boolean true:編集可能 false:編集不可
     */
	public boolean execute(String staffId) {
        
        // 研修履歴チェック
        if(getTrnStaffStateDao().selectKenshuState(staffId) >= 1) {
            return false;
        }
        
        // MLエントリー履歴
        if(getTrnStaffStateDao().selectEntryHistoryState(staffId) >= 1 ) {
            return false;
        }
        
        return true;
        
	}
    

    public TrnStaffStateDao getTrnStaffStateDao() {
        return trnStaffStateDao;
    }

    public void setTrnStaffStateDao(TrnStaffStateDao trnStaffStateDao) {
        this.trnStaffStateDao = trnStaffStateDao;
    }


}
