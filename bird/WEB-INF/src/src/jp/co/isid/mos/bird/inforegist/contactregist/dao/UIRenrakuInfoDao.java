/*
 * çÏê¨ì˙: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.inforegist.contactregist.entity.UIRenrakuInfo;

/**
 * @author xyuchida
 *
 */
public interface UIRenrakuInfoDao {

    public static final Class BEAN = UIRenrakuInfo.class;
    
    public static final String getRenraku_ARGS = "pubDate, cateId, userId, companyCd";

    public static final String getRenrakuCount_ARGS = "pubDate, cateId, userId, companyCd";

    public static final String getNumber_SQL
            = "select decimal(max(seq)) from BT01INFM where REG_DATE = /*regDate*/'20060126'";

    public static final String updateRenraku_PERSISTENT_PROPS
            = "cateId, title, pubDate, message, attachName1, attachName2, attachName3, attachFl1, attachFl2, attachFl3, lastUser, lastPgm";

    public static final String deleteRenraku_ARGS = "regDate, seq";
    public static final String deleteRenraku_SQL
            = "update BT01INFM set sakujo_flg = '1' where REG_DATE = /*regDate*/'20060126' and SEQ = /*seq*/'0001'";

    public List getRenraku(String pubDate, String cateId, String userId, String companyCd);

    public int getRenrakuCount(String pubDate, String cateId, String userId, String companyCd);

    public int getNumber(String regDate);

    public void insertRenraku(UIRenrakuInfo uIRenrakuInfo);

    public void updateRenraku(UIRenrakuInfo uIRenrakuInfo);

    public void deleteRenraku(String regDate, String seq);
}
