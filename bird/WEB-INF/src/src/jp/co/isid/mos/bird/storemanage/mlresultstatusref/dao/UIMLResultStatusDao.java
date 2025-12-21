/*
 * ì¬“ú: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UIMLResultStatus;

/**
 * @author Noh
 */
public interface UIMLResultStatusDao {
	
	public static final Class BEAN = UIMLResultStatus.class;
    public static final String select_ARGS = "sysDate, userId, userTypeCd, limitFlg, dto";
	/**
	 * ŒŸõŒ‹‰Êæ“¾ˆ—
	 * 
	 * @param sysDate
	 * @param userId
	 * @param userTypeCd
	 * @param limitFlg
	 * @param dto
	 * @return
	 */
	public List select(
			String sysDate, String userId
			, String userTypeCd, boolean limitFlg
			, RequestDto dto);
	
}
