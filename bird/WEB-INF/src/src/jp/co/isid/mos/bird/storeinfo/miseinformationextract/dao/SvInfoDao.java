package jp.co.isid.mos.bird.storeinfo.miseinformationextract.dao;

import jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity.SvInfo;

/**
 * SVŒŸõî•ñæ“¾
 * @author kusama
 */
public interface SvInfoDao {

    public static final Class BEAN = SvInfo.class;
    public static final String selectSvInfo_ARGS = "svCd";

    /**
     * w’èSV‚Ìî•ñ‚ğæ“¾
     * @param svCd
     * @return
     */
    public SvInfo selectSvInfo(String svCd);
}