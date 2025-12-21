package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic;

import java.util.List;
import java.util.Map;

/**
 * データ登録
 * ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public interface RegistLogic {
    /**
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    /**
     * パラメーターKey：BIRD日付情報 
     */
    public static final String PK_DATEINFO = "dateInfo";
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
    public List execute(Map params);
}
