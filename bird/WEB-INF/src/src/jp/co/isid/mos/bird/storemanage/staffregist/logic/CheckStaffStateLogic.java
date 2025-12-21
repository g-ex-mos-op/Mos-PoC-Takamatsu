package jp.co.isid.mos.bird.storemanage.staffregist.logic;

/**
 * 編集対象のスタッフが編集可能であることを確認する
 * @aeuthor Aspac
 */
public interface CheckStaffStateLogic {

    /**
     * 編集対象のスタッフが編集可能であることを確認する
     * ※過去１度でも研修を受講している場合スタッフ情報の変更をできない
     * ※現在マスターライセンスエントリー中の場合スタッフ情報の変更はできない
     * 
     * @param staffId スタッフID
     * @return boolean true:編集可能 false:編集不可
     */
	public boolean execute(String staffId);
}