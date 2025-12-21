/*
 * ì¬“ú: 2006/09/07
 *
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.CtlPLLimit;

/**
 * @author xyuchida
 */
public interface CtlPLLimitDao {

    public static final Class BEAN = CtlPLLimit.class;

    public List selectPlLimitAll();
}
