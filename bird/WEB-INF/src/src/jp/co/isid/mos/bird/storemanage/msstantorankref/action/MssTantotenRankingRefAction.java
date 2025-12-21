/*
 * 作成日: 2006/08/01
 */
package jp.co.isid.mos.bird.storemanage.msstantorankref.action;


/**
 * ミステリーショッパーズ オーナー別獲得ポイントランク
 * 画面アクション インターフェイス
 * 
 * @author xkinu
 */
public interface MssTantotenRankingRefAction {
	/** 画面ID：BSM010 */
	public static final String SCREEN_ID = "BSM010";
    /** アクションID定義 */
    public static final String initialize_ACTION_ID = SCREEN_ID+"A01";
    public static final String execute_ACTION_ID     = SCREEN_ID+"A02";
    public static final String back_ACTION_ID       = SCREEN_ID+"A03";
    public static final String viewinitialize_ACTION_ID = SCREEN_ID+"A04";
    /* モス会社コード */
    public static final String COMPANY_CD_MOS = "00";
    /* ビューID:条件画面 BSM010V01 */
    public static final String VIEWID_CONDITION = SCREEN_ID+"V01";
    /* ビューID:照会画面 BSM010V01 */
    public static final String VIEWID_REF = SCREEN_ID+"V02";
    /**
     * 条件画面初期化処理
     */
    public String initialize() throws Exception;
    /**
     * 照会画面初期化処理
     */
    public String viewinitialize() throws Exception;
    /**
     * 検索実行処理
     */
    public String execute() throws Exception;
    /**
     * 戻る実行処理
     */
    public String back() throws Exception;

    public String callMiseForm() throws Exception;
    public String callOnerForm() throws Exception;
    public String callSvForm() throws Exception;
}