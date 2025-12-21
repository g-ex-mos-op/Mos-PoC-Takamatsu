/*
 * 作成日: 2006/08/01
 */
package jp.co.isid.mos.bird.storemanage.mssonerpointref.action;


/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * 画面アクション インターフェイス
 * 
 * @author xkinu
 */
public interface MssOnerPointRankRefAction {
	/** 画面ID：BSM009 */
	public static final String SCREEN_ID = "BSM009";
    /** アクションID定義 */
    public static final String initialize_ACTION_ID = SCREEN_ID+"A01";
    public static final String changeCompany_ACTION_ID     = SCREEN_ID+"A02";
    public static final String downloadCsv_ACTION_ID       = SCREEN_ID+"A03";
    /* モス会社コード */
    public static final String COMPANY_CD_MOS = "00";
    /* ビューID:条件画面 BSM009V01 */
    public static final String VIEWID_CONDITION = SCREEN_ID+"V01";
    /**
     * 
     */
    public String initialize() throws Exception;
    /**
     * 対象会社変更処理
     *
     */
    public String changeCompany() throws Exception;
    /**
     * CSVダウンロード処理処理
     *
     */
    public String downloadCsv() throws Exception;
    
    public String callMiseForm() throws Exception;
    public String callOnerForm() throws Exception;
    public String callSvForm() throws Exception;
}