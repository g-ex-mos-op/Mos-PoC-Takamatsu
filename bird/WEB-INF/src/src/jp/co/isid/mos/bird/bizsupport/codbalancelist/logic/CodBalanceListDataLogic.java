/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.bizsupport.codbalancelist.logic;

import java.util.Map;

/**
 * COD残高一覧
 * COD残高一覧取得ロジック インターフェイス
 * 
 * @author xkinu
 */
public interface CodBalanceListDataLogic {
    
    /**
     * パラメーターKey：会社コード  
     */
    public static final String COMPANY_CD = "companyCd";
    
    /**
     * パラメーターKey：オーナーコード
     */
    public static final  String ONER_CD = "onerCd";
    
   /**
    * リターンKey：検索結果
    */
   public static final String RK_LIST_GETDATA = "listCodBalanceList";
    /**
     * 実行処理
     * 
     * @param params パラメーター保持Map
     * @return
     */
    public Map execute(Map params);
}