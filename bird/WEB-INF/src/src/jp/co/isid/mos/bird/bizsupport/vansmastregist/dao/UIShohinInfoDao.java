/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.dao;

import jp.co.isid.mos.bird.bizsupport.vansmastregist.entity.UIShohinInfo;

/**
 * 注文商品情報Dao
 * @author narita
 */
public interface UIShohinInfoDao {

    public static final Class BEAN = UIShohinInfo.class;
    
    /**
     * 商品情報のカウント取得時のパラメータ
     */
    public static final String getShohinCount_ARGS = "shoCdDai";
        
    /**
     * 商品情報の商品名取得時のパラメータ
     */
    public static final String getShohinNameKj_ARGS = "shoCdDai";
    
    /**
     * 商品情報のカウントを取得する
     * @param shoCdDai	商品コード
     * @return int カウント
     */
    public int getShohinCount(String shoCdDai);
    
    /**
     * 商品情報の商品名を取得する
     * @param shoCdDai	商品コード
     * @return String 商品名
     */
    public String getShohinNameKj(String shoCdDai);
}
