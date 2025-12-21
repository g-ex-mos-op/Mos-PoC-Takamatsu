package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.MstMosChikenFromTo;

public interface MstMosChickenFromToDao {
    /**
     *キャンペーン名称取得entity
     *@author inazawa
     */
    public static final Class BEAN = MstMosChikenFromTo.class;

    public static final String getTitle_ARGS = "sysDate";
    public static final String getCahngeTitle_ARGS = "ckanriNo";
    /**
     * キャンペーン名称取得
     * @param sysDate
     * @return
     */
    public List getTitle(String sysDate);
    /**
     * キャンペーン変更時対象期間取得
     * @param ckanriNo
     * @return
     */
    public List getChangeTitle(String ckanriNo);



}
