/*
 * çÏê¨ì˙: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;

/**
 * @author xyuchida
 *
 */
public interface UIGyotaiDao {

    public static final Class BEAN = UIGyotai.class;

    public List select(String companyCd);

    public List selectGyotaiResult(List gyotaiKbnList);
}
