package jp.co.isid.mos.bird.entry.mlentry.action;

/**
 *  マスターライセンス受験申込　編集画面アクションインターフェース
 * @author Aspac
 */
public interface MlEntryEditAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 戻る
     */
	public String cancel();

    /**
     * 入力欄追加
     */
    public String addRow();
    
    /**
     * 登録・終了
     */
    public String regist();
    
    /**
     * 申込取消
     */
    public String registCancel();
    
    /**
     * 新規スタッフ追加
     */
    public String addStaff();

    /**
     * スタッフ編集
     */
    public String editStaff();

    /**
     * スタッフ選択
     * 
     * @return　オーナー検索画面
     */
    public String selectStaff();
}