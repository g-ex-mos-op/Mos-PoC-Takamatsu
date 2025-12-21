/*
 * ì¬“ú: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UIHoldOpenTargetInfo;


/**
 * ’Ê’BÚ×î•ñDao
 * 
 * ì¬“ú:2010/02/23
 * @author xkinu
 *
 */
public interface UIHoldOpenTargetInfoDao {

    public Class BEAN = UIHoldOpenTargetInfo.class;

    public static final String select_ARGS = "lastTmspDt";
    
    /**
     * w’è“o˜^“úî•ñæ“¾
     * 
     * @param lastTmspDt yyyy-MM-dd
     */
    public List select(String lastTmspDt);
}