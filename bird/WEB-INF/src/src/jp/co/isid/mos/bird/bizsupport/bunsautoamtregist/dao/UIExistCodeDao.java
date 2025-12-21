package jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.bunsautoamtregist.entity.UIExistCode;


/**
 * 存在コード情報Dao
 * 
 * @author xkinu
 */
public interface UIExistCodeDao {

    public static final Class BEAN = UIExistCode.class;
    /**
     * オーナー存在コード取得処理SQL引数
     */
    public static final String selectOner_ARGS = "companyCd, taishoCd";
    /**
     * 店舗存在コード取得処理SQL引数
     */
    public static final String selectMise_ARGS = "companyCd, taishoCd";
    /**
     * SV存在コード取得処理SQL引数
     */
    public static final String selectSv_ARGS = "companyCd, taishoCd";
    /**
     * オーナー検索処理
     * 
     * @param companyCd
     * @param taishoCd
     * @return
     */
    public List selectOner(String companyCd,  String taishoCd);
    /**
     * 店舗検索処理
     * 
     * @param companyCd
     * @param taishoCd
     * @return
     */
    public List selectMise(String companyCd,  String taishoCd);
    /**
     * SV検索処理
     * 
     * @param companyCd
     * @param taishoCd
     * @return
     */
    public List selectSv(String companyCd,  String taishoCd);
    
}