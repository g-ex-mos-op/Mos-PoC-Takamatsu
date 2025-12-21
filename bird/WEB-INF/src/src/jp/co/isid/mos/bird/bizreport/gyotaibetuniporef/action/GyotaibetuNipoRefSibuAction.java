package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.action;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;

public interface GyotaibetuNipoRefSibuAction {
    /** アクションID */
	public static final String ACTION_ID   = GyotaibetuNipoConstants.VIEW_ID+"A1"; 

    public String initialize();
    
    public String enforcement();
    
    public String dispEigyoNipo();
    
    public String dispTakuhai();   
    
    public String selectedTab();  
    public void csvDownload();  
    public String callSvForm();
    /**
     * アクション【CSVダウンロード(店舗別)】
     */
    public void downloadCsvMise();
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
