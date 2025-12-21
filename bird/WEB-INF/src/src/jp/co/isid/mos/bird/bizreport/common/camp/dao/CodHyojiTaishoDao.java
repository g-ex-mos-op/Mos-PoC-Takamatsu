/*
 * 作成日: 2006/5/30
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.entity.CodHyojiTaisho;


/**
 * 表示対象情報
 * 
 * @author xkinu
 */
public interface CodHyojiTaishoDao {

    public static final Class BEAN = CodHyojiTaisho.class;

    public static final String selectSegment_ARGS = "companyCd";
    public static final String selectJigyoHonbu_ARGS = "userId, userTypeCd, limitFlg, companyCd, campId";
    public static final String selectSlArea_ARGS = "userId, userTypeCd, limitFlg, companyCd, campId";
    public static final String selectSibu_ARGS = "userId, userTypeCd, limitFlg, companyCd, campId";
    public static final String selectAreaDai_ARGS = "userId, userTypeCd, limitFlg, companyCd, campId";
    
    /**
     * セグメント情報の取得
     * @param String companyCd  会社コード
     * @return List
     */
    public List selectSegment(String companyCd);
    /**
     * 事業本部情報の取得
     * 
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg　支部制限フラグ
     * @param companyCd　会社コード
     * @param campId　　キャンペーン識別番号
     * @return
     */
    public List selectJigyoHonbu(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId);
    /**
     * 営業エリア情報の取得
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg　支部制限フラグ
     * @param companyCd　会社コード
     * @param campId　　キャンペーン識別番号
     * @return List
     */
    public List selectSlArea(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId);
    /**
     * 支部情報の取得
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg　支部制限フラグ
     * @param companyCd　会社コード
     * @param campId　　キャンペーン識別番号
     * @return List
     */
    public List selectSibu(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId);

    /**
     * エリア大情報の取得
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg　支部制限フラグ
     * @param companyCd　会社コード
     * @param campId　　キャンペーン識別番号
     * @return List
     */
    public List selectAreaDai(String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId);
    
    public static final String selectMise_ARGS  = "sysDate, userId, userTypeCd, limitFlg, companyCd, campId";
    public static final String selectMiseSortClose_ARGS  = "sysDate, userId, userTypeCd, limitFlg, companyCd, campId";
    public static final String selectMiseCloseNotIn_ARGS  = "sysDate, userId, userTypeCd, limitFlg, companyCd, campId";
    /**
     * クローズ店を含む対象店舗取得
     * 
     * 店舗コードでソートした情報です。
     * @param sysDate
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg　支部制限フラグ
     * @param companyCd　会社コード
     * @param campId　　キャンペーン識別番号
     * @return
     */
    public List selectMise(String sysDate
    		, String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId);
    
    /**
     * クローズ店を含む対象店舗取得
     * 
     * オープン店とクローズ店舗を分けて店舗コードでソートした情報です。
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param campId
     * @return
     */
    public List selectMiseSortClose(String sysDate
    		, String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId);
    
    /**
     * クローズ店を含めない店舗取得
     * 
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param limitFlg
     * @param companyCd
     * @param campId
     * @return
     */
    public List selectMiseCloseNotIn(String sysDate
    		, String userId, String userTypeCd, boolean limitFlg
    		, String companyCd, String campId);
    
}