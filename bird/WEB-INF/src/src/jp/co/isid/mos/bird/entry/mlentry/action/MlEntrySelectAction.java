package jp.co.isid.mos.bird.entry.mlentry.action;

/**
 *  マスターライセンス受験申込　条件画面アクションインターフェース
 * @author Aspac
 */
public interface MlEntrySelectAction {

    /**
     * 初期処理
     */
	public String initialize();

    /**
     * 実行
     */
    public String regist();

    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm();
}