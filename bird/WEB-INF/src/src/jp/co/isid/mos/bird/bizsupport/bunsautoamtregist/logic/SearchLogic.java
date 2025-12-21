/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.logic;

import java.util.Map;

/**
 * 対象情報検索と結果取得ロジック インターフェイス
 * 
 * 事前処理でロジック【存在コードチェック】を呼び出します。
 * 
 * @author xkinu
 */
public interface SearchLogic {
   /**
    * パラメーターKey：BIRDユーザー情報 
    */
   public static final String PK_USERINFO = "userInfo";
   /**
    * パラメーターKey：システム日付
    */
   public static final String PK_SYSDATE= "sysDate";
   /**
    * パラメーターKey：条件項目『対象条件』
    */
   public static final String PK_TAISHOJOKEN = "taishoJoken";
   /**
    * パラメーターKey：会社コード
    */
   public static final String PK_COMPANY_CD= "companyCd";
   /**
    * パラメーターKey：オーナーコード
    */
   public static final String PK_ONER_CD= "onerCd";
   /**
    * パラメーターKey：店舗コード
    */
   public static final String PK_MISE_CD= "miseCd";
   /**
    * パラメーターKey：SVコード
    */
   public static final String PK_SV_CD= "svCd";
   /**
    * リターンKey：検索結果
    */
   public static final String RK_LIST_SEARCHDATA = "searchData";
   /**
    * リターンKey：検索結果の店舗一覧
    */
   public static final String RK_LIST_SEARCHDATA_MISELIST = "searchDataMiseList";
   /**
    * リターンKey：対象条件の対象コードの名称
    */
   public static final String RK_TAEGETCD_NAME= "targetCdName";
    /**
     * 実行処理
     * 
     * @param params パラメーター保持Map
     * @return
     */
    public Map execute(Map params);
}