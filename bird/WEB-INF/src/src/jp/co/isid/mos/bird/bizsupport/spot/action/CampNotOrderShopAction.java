package jp.co.isid.mos.bird.bizsupport.spot.action;

/**
 * スポット未受注店画面アクション・インターフェース
 * @author xsong
 */
public interface CampNotOrderShopAction {

	/**
	 * 初期処理
	 */
	public String initialize();
	
	/**
	 * 実行 / 再検索用
	 */
	public String actionSearch();

}