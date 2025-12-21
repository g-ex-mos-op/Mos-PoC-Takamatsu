/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao;

import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.PointShuInfo;

/**
 * ‘ŞE¸Z—š—ğDao
 * @author yushuncheng
 *
 */
public interface PointShuDao {

	public static final Class BEAN = PointShuInfo.class;
    public static final String selectPointShuName_SQL = "select POINT_SHU_NAME from BC39PSHU where POINT_SHU = ?";

    /**
     * ƒ|ƒCƒ“ƒgí•Ê–¼Ìæ“¾
     * @param pointShu
     * @return
     */
    public String selectPointShuName(String pointShu);
}
