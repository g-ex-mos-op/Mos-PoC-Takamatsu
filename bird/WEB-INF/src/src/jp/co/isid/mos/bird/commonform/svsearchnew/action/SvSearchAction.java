package jp.co.isid.mos.bird.commonform.svsearchnew.action;

/**
 * SV検索処理アクションインターフェース
 * 
 * @author kusama
 */
public interface SvSearchAction {
    /**
     * SV検索初期処理
     */
	public String initialize();

    /**
     * 支部リスト読込処理
     */
    public String loadSibuList();

    /**
     * SV検索処理
     */
	public String search();

    /**
     * SV検索決定処理
     */
    public String select();

    /**
     * SV検索取消処理
     */
    public String cancel();
}
