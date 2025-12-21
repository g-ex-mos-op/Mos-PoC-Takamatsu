/*
 * 作成日: 2008/06/19
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;



/**
 * 表示対象情報
 * 
 * @author xnkusama
 */
public interface CodHyojiTaishoDao {

    public static final Class BEAN = CodHyojiTaisho.class;

    public static final String selectJigyoHonbu_ARGS = "companyCd, userId, userTypeCd, limitFlg";
    public static final String selectSlArea_ARGS = "companyCd, userId, userTypeCd, limitFlg";
    public static final String selectSibu_ARGS = "companyCd, userId, userTypeCd, limitFlg";
    public static final String selectMise_ARGS = "companyCd, userId, userTypeCd, limitFlg, appDate";
    public static final String selectAreaDai_ARGS = "companyCd, userId, userTypeCd, limitFlg";
    public static final String selectSegment_ARGS = "companyCd";
    
    /**
     * 事業本部情報の取得
     * 
     * @param companyCd 企業コード
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg リミットフラグ
     * @return
     */
    public List selectJigyoHonbu(String companyCd, String userId, String userTypeCd, boolean limitFlg);
    /**
     * 営業エリア情報の取得
     * @param companyCd 企業コード
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @return List
     */
    public List selectSlArea(String companyCd, String userId, String userTypeCd, boolean limitFlg);
    /**
     * 支部情報の取得
     * @param companyCd 企業コード
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @return List
     */
    public List selectSibu(String companyCd, String userId, String userTypeCd, boolean limitFlg);
    /**
     * 個店情報の取得
     * 
     * @param companyCd  企業コード
     * @param userId 　  ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param limitFlg   リミットフラグ
     * @param appDate    アプリ日付
     * @return
     */
    public List selectMise(String companyCd, String userId, String userTypeCd, boolean limitFlg, String appDate);
    /**
     * 支部（エリア大）情報の取得
     * @param companyCd 企業コード
     * @param userId　ユーザーID
     * @param userTypeCd　ユーザータイプコード
     * @param limitFlg limitFlg リミットフラグ
     * @return List
     */
    public List selectAreaDai(String companyCd, String userId, String userTypeCd, boolean limitFlg);
    /**
     * セグメント情報の取得
     * @param companyCd 企業コード
     * @return List
     */
    public List selectSegment(String companyCd);
}