package jp.co.isid.mos.bird.entry.nationalentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntrySyokuiInfo;

/**
 * EˆÊ‹æ•ªî•ñæ“¾ˆ—
 */
public interface UINatiEntrySyokuiInfoDao {
    
    public static final Class BEAN = UINatiEntrySyokuiInfo.class;
    
    public static final String select_ARGS = "";
    /**
     * EˆÊ‹æ•ªî•ñæ“¾
     * @return EˆÊ‹æ•ªî•ñƒŠƒXƒg
     */
    public List select();
}
