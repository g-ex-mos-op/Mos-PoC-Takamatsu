package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.action;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;

public interface GyotaibetuNipoRefMiseAction {
    /** アクションID */
	public static final String ACTION_ID   = GyotaibetuNipoConstants.VIEW_ID+"A2"; 
	/**
	 * 初期化処理
	 * 
	 * @return
	 */
    public String initialize();
    /**
     * 再検索処理アクション
     * @return
     */
    public String enforcement();
    /**
     * 会社プルダウン変更アクション
     * @return
     */
    public String reSearchTaishoSibuList();
    /**
     * CSVダウンロードアクション
     * @return
     */
    public void downloadCsvMise();
    /**
     * 個店情報ポータルへ遷移アクション
     * @return
     */
    public String selectMise();
    public String callSvForm();
    /**
     * 営業日報画面遷移アクション
     * @return
     */
    public String dispEigyoNipo();
    /**
     * 宅配日報画面遷移アクション
     * @return
     */
    public String dispTakuhai();   
    
    /**
     * アクション【MOSCARD画面遷移】
     * 
     * @return String MOSCARD画面(支部一覧) VIEW_ID
     */
    public String callMoscard();
    /**
     * アクション【ネット注文画面遷移】
     * 
     * @return String NETORDER画面(支部一覧) VIEW_ID
     */
    public String callNetorder();
}
