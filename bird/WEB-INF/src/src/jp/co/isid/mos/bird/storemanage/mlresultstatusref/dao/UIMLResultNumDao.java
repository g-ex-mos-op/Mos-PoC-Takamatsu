/*
 * çÏê¨ì˙: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UIMLResultNum;

/**
 * @author Noh
 */
public interface UIMLResultNumDao {
	
	public static final Class BEAN = UIMLResultNum.class;
    public static final String select_ARGS = "sysDate, userId, userTypeCd, dto";
	/**
	 * 
	 * @param sysDate
	 * @param userId
	 * @param userTypeCd
	 * @param dto
	 * @return
	 */
	public List select(String sysDate
			, String userId, String userTypeCd
			, RequestDto dto);

}
