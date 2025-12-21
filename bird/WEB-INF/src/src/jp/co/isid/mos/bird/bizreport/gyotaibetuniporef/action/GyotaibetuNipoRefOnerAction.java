package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.action;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;

public interface GyotaibetuNipoRefOnerAction {
    /** アクションID */
	public static final String ACTION_ID   = GyotaibetuNipoConstants.VIEW_ID+"A3"; 

    public String initialize();
    
    public String selectMise();
    
    public String enforcement();

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
     * CSVダウンロードアクション
     * @return
     */
    public void downloadCsvOner();
    /**
     * アクション【MOSCARD画面遷移】
     * 
     * @return String MOSCARD画面(支部一覧) VIEW_ID
     */
    public String callMoscard();
    /**
     * アクション【ネット注文画面遷移】
     * 
     * @return String ネット注文画面(支部一覧) VIEW_ID
     */
    public String callNetorder();
}
