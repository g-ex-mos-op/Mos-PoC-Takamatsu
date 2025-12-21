package jp.co.isid.mos.bird.bizsupport.codbalancelist.logic;

import java.util.Map;

/**
 * COD残高一覧
 * 初期情報の取得ロジックインターフェース
 * 
 * 対象条件の『支部』選択時の支部情報を取得します。
 * @author xkinu
 *
 */
public interface ConditionLogic {
    /**
     * リターンKey：ダウンロード許可フラグ
     */
    public static final String RK_DOWNLOAD_FLG= "downloadFlg";
    
    /**
     * パラメーターKey：会社コード
     */
    public static final String COMPANY_CD = CodBalanceListDataLogic.COMPANY_CD;
    /**
     * パラメーターKey：オーナーコード
     */
    public static final String ONER_CD = CodBalanceListDataLogic.ONER_CD;
    
    /**
     * リターンKey：検索結果
     */
    public static final String RK_LIST_GETDATA = CodBalanceListDataLogic.RK_LIST_GETDATA;
    /**
     * リターンKey：メッセージ
     */
    public static final String RK_MSG= "msg";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public Map execute(Map params);
}
