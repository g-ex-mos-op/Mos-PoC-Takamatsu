/*
 * ì¬“ú: 2008/11/27
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.dao;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIKeiyakuType;

/**
 * UIŒ_–ñƒ^ƒCƒvDao
 * @author xnkusama
 */
public interface UIKeiyakuTypeDao {

    public static final Class BEAN = UIKeiyakuType.class;
    public static final String getKeiyakuType_ARGS = "userId";

    public UIKeiyakuType getKeiyakuType(String userId);
}
