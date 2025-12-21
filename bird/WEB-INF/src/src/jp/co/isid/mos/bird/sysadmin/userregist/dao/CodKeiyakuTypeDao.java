/*
 * 作成日: 2007/01/31
 */
package jp.co.isid.mos.bird.sysadmin.userregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.sysadmin.userregist.entity.CodKeiyakuType;

/**
 * 契約タイププルダウン取得Dao
 * @author xamaruyama
 */
public interface CodKeiyakuTypeDao {

    public static final Class BEAN = CodKeiyakuType.class;

    public static final String getKeiyaku_ARGS = "";
    
    /**
     * 契約タイププルダウンの取得
     * @return List
     */
    
    public List getKeiyaku();
}
