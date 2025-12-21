package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action;

/**
 * 売上金管理月報アクション・インターフェース
 *
 * @author xjung
 */
public interface ProceedsManageGepoAction {

	/**
	 * 初期処理
     * @return String 遷移先ビューID
	 */
    public String initialize();

    /**
     * 対象店舗プルダウン取得
     * @return String 遷移先ビューID
     */
    public String searchMiseList();

    /**
	 * 検索
     * @return String 遷移先ビューID
	 */
    public String search();
 
    /**
     * タブ変更
     * @return String 遷移先ビューID
     */
    public String changeTab();

    /**
     * 店舗検索
     * @return String 遷移先ビューID
     */
    public String searchMiseCd();
}