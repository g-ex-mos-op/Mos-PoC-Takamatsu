/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIVansShohinInfo;

/**
 * 代表商品情報Dao
 * @author narita
 */
public interface UIVansShohinInfoDao {

    public static final Class BEAN = UIVansShohinInfo.class;
    
    /**
     * 代表商品情報取得時のパラメータ
     */
    public static final String getVansShohinList_ARGS = "kanriMoto";
    
    /**
     * 代表商品情報の新規登録パラメータ
     */
    public static final String insertVansShohin_ARGS = "uIVansShohinInfo";
    
    /**
     * 代表商品情報の更新パラメータ
     */
    public static final String updateVansShohin_PERSISTENT_PROPS = "shoNisugata, lastUser, lastPgm";
           
    /**
     * 代表商品情報の削除パラメータ
     */
    public static final String deleteVansShohin_ARGS = "sokoCd, shoCdDai";
    
    /**
     * 代表商品情報を取得する
     * @param kanriMoto	管理元コード
     * @return List 代表商品情報リスト
     */
    public List getVansShohinList(String kanriMoto);
    
    /**
     * 代表商品情報の新規登録
     * @param uIVansShohinInfo　代表商品情報エンティティ
     * @return int 結果コード
     */
    public int insertVansShohin(UIVansShohinInfo uIVansShohinInfo);

    /**
     * 代表商品情報の更新
     * @param uIVansShohinInfo　代表商品情報エンティティ
     * @return int 結果コード
     */
    public int updateVansShohin(UIVansShohinInfo uIVansShohinInfo);

    /**
     * 代表商品情報の削除
     * @param uIVansShohinInfo　代表商品情報エンティティ
     * @return int 結果コード
     */
    public int deleteVansShohin(UIVansShohinInfo uIVansShohinInfo);
}
