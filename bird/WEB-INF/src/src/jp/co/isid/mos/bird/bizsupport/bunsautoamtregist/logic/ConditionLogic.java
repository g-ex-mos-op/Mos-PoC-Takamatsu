package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic;

import java.util.Map;

/**
 * 事業方針説明会申込状況確認
 * 条件項目取得ロジックインターフェース
 * 
 * 対象条件の『支部』選択時の支部情報を取得します。
 * @author xkinu
 *
 */
public interface ConditionLogic {
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    /** 
     * パラメーターKey：システム日付 
     */
    public static final String PK_SYSDATE = "sysDate";
    /** 
     * パラメーターKey：会社コード
     */
    public static final String PK_COMPANY_CD = "companyCd";
    /**
     * パラメーターKey：汎用画面ロール情報DTO
     */
    public static final String PK_DTO_GAMENROE = "gamenRoleDto";
    /**
     * リターンKey：登録判断フラグ情報
     */
    public static final String RK_FLG_REGIST= "flgRegist";
    /**
     * リターンKey：登録判断フラグ情報
     */
    public static final String RK_FLG_DOWNLOAD= "flgDownload";
    /**
     * リターンKey：会社コード情報取得
     */
    public static final String RK_LIST_COMPANY= "listCompanyCd";
    /**
     * リターンKey：対象条件情報
     */
    public static final String RK_LIST_TAISHOJOKEN = "listTaishoJoken";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public Map execute(Map params);
}
