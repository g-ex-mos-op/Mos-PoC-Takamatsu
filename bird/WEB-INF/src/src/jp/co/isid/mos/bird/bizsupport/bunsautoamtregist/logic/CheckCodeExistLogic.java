package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic;

import java.util.List;
import java.util.Map;

/**
 * バンズ自動設定数量変更
 * コード存在チェック ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface CheckCodeExistLogic {
    /**
     * パラメーターKey：条件項目『対象条件』
     */
    public static final String PK_TAISHOJOKEN = "taishoJoken";
    /**
     * パラメーターKey：会社コード
     */
    public static final String PK_COMPANY_CD= "companyCd";
    /**
     * パラメーターKey：対象コード
     */
    public static final String PK_TAISHO_CD= "taishoCd";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public List execute(Map params);
}
