/*
 * ì¬“ú: 2006/02/10
 *
 */
package jp.co.isid.mos.bird.communication.contactreference.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.contactreference.entity.MstUserGyotai;

/**
 * @author xyuchida
 *
 */
public interface MstUserGyotaiDao {

    public static final Class BEAN = MstUserGyotai.class;

    public List getGyotai(String userId);
}
