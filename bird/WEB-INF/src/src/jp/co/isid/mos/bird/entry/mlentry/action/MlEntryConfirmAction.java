package jp.co.isid.mos.bird.entry.mlentry.action;

/**
 * マスターライセンス受験申込　確認画面アクションインターフェース
 * @author Aspac
 */
public interface MlEntryConfirmAction {

    /**
     * 初期処理
     */
    public String initialize();

    /**
     * 戻る
     */
    public String cancel();

    /**
     * 終了
     */
    public String terminate();
    
}