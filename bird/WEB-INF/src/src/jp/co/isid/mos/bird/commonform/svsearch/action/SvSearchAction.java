/*
 * 作成日: 2006/08/22
 */
package jp.co.isid.mos.bird.commonform.svsearch.action;

/**
 * SV検索処理アクションインターフェース
 * 
 * @author kinugawa(ASPAC)
 */
public interface SvSearchAction {
    /** 画面ID：BCO009 */
    public static final String SCREEN_ID = "BCO009";

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
