/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.common.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;

/**
 * î•ñ(UISeidoPointDao)
 * @author yushuncheng
 *
 */
public interface UISeidoPointDao {

	public static final Class  BEAN = UISeidoMst.class;

	/**
     * Š”®•ñV§“xƒŠƒXƒg‚ğæ“¾‚·‚é(getSeidoPointInfo)
     * @return UISeidoMst ŒŸõŒ‹‰Ê
     */
    public List<UISeidoMst> getSeidoPointInfo();

}
