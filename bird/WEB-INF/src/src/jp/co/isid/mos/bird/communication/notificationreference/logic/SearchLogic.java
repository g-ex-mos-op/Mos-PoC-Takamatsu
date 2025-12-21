/**
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.logic;

import java.util.Map;

/**
 * @author xkinu
 *
 */
public interface SearchLogic {
	/**
	 * 情報種別：通達:02
	 */
	public static final String INFO_SHU = "02";
   /**
    * パラメーターKey：BIRDユーザー情報
    */
   public static final String PK_USERINFO = "userInfo";
   /**
    * パラメーターKey：システム日付
    */
   public static final String PK_SYSDATE= "sysDate";
   /**
    * パラメーターKey：条件項目『公開開始月From』
    */
   public static final String PK_PUBDATE = "pubDate";
   /**
    * パラメーターKey：条件項目『公開開始月To』
    */
   public static final String PK_PUBDATE_TO = "pubDateTo";
   /**
    * パラメーターKey：会社コード
    */
   public static final String PK_GYOTAIKBN= "gyotaiCd";
   /**
    * パラメーターKey：オーナーコード
    */
   public static final String PK_TITLE= "title";
   /**
    * リターンKey：検索結果
    */
   public static final String RK_LIST_SEARCHDATA = "searchData";
   /**
    * リターンKey：検索結果のカテゴリ一覧
    */
   public static final String RK_LIST_SEARCHDATA_CATEGORY = "searchDataMiseList";
   /**
    * リターンKey：FullTextSearchDTO
    */
   public static final String PK_FULLTEXTSEARCH_DTO = "fullTextSearchDto";

    /**
     * 実行処理
     *
     * @param params パラメーター保持Map
     * @return
     */
    public Map execute(Map params);

}
