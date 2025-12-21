package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic;

import java.util.Map;

/**
 * バンズ自動設定数量変更
 * 入力値チェック ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface CheckInputDataLogic {
    /** 
     * パラメーターKey：編集データ
     */
    public static final String PK_LIST_REGDATA = "listRegData";

    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public String execute(Map params);
}
