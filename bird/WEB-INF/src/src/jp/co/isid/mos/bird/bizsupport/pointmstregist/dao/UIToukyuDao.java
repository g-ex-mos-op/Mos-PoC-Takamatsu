package jp.co.isid.mos.bird.bizsupport.pointmstregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointmstregist.entity.UIPointMst;

/**
 * î•ñ(UIToukyuDao)
 * @author yushuncheng
 *
 */
public interface UIToukyuDao {

	public static final Class  BEAN = UIPointMst.class;

	/**
     * ‰ïĞ“™‹‰ƒŠƒXƒg‚ğæ“¾‚·‚é(getToukyuInfo)
     * @return UIPointMst ŒŸõŒ‹‰Ê
     */
    public List<UIPointMst> getToukyuInfo();

}
