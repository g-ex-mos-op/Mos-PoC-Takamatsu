/*
 * çÏê¨ì˙: 2006/03/01
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.dao;

import java.util.List;
import jp.co.isid.mos.bird.communication.notificationreference.entity.MstUserGyotai;

/**
 * @author m.onodera
 *
 */
public interface MstUserGyotaiDao {

    public static final Class BEAN = MstUserGyotai.class;

    public List getGyotai(String userId);
}
