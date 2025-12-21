package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action;

/**
 * 売上金管理月報売上と消費税の明細明細照会アクション
 * 2019/07/16
 * @author USI 張
 */

public interface ProceedsManageGepoUtMeisaiAction {
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
	 * 再検索
     * @return String 遷移先ビューID
	 */
    public String uriageTaxResearch();

    /**
     * 店舗検索
     * @return String 遷移先ビューID
     */
    public String searchMiseCd();

    /**
     * 売上消費税明細
     * @return String 遷移先ビューID
     */
    public String uriageTaxMeisaiSearch();
}
