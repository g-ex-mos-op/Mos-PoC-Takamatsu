/*
 * çÏê¨ì˙: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.contactreference.entity.UIViewRenraku;

/**
 * @author xyuchida
 *
 */
public interface UIViewRenrakuDao {

    public static final Class BEAN = UIViewRenraku.class;

    public static final String getViewFrom_ARGS = "title, pubDate, gyotaiKbn, cateId, userId, sysDate";

    public List getViewFrom(String title, String pubDate, String gyotaiKbn, String cateId, String userId, String sysDate);
}
