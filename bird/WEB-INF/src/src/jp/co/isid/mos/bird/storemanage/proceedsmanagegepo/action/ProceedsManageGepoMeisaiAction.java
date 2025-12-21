package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action;

/**
 * 売上金管理月報アクション・インターフェース
 *
 * @author xogawa
 */
public interface ProceedsManageGepoMeisaiAction {

	/**
	 * 初期処理
     * @return String 遷移先ビューID
	 */
    public String initialize();

    /**
     * 明細
     * @return String 遷移先ビューID
     */
    public String meisaiSearch();

    /**
     * 対象店舗プルダウン取得
     * @return String 遷移先ビューID
     */
    public String searchMiseList();

    /**
	 * 再検索
     * @return String 遷移先ビューID
	 */
    public String search();

    /**
     * 店舗検索
     * @return String 遷移先ビューID
     */
    public String searchMiseCd();

}
