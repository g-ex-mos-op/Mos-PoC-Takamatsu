package jp.co.isid.mos.bird.bizreport.takuhainiporef.action;

/**
 * 宅配売上日報の条件部画面アクション・インターフェース
 * @author xjung
 */
public interface TakuhaiNipoRefConditionAction {

	/**
	 * 初期処理
     * @return String 遷移先ビューID
	 */
	public String initialize();
    /**
     * アクション【SV検索ボタン】処理
     * @return String 遷移先ビューID
     */   
    public String callSvForm();

	/**
	 * 実行
     * @return String 遷移先ビューID
	 */
	public String execution();

}