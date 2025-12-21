/*
 * ì¬“ú: 2006/10/12
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveTimeInfo;

/**
 * —\–ñŠÔ
 * 
 * @author xlee
 */
public interface UIReserveTimeInfoDao {

    public static final Class BEAN = UIReserveTimeInfo.class;

    public static final String getReserveTimeInfo_ARGS = "ckanriNo, miseCd, reservDt, companyCd";

    /**
     *@—\–ñŠÔ‚ğæ“¾
     * @param ckanriNo ŠÇ—”Ô†
     * @param miseCd “XƒR[ƒh
     * @param reservDt ‚¨“n‚µ“ú
     * @param companyCd ‰ïĞƒR[ƒh
     * @return —\–ñŠÔ
     */
    public List getReserveTimeInfo(String ckanriNo, String miseCd, String reservDt, String companyCd);
}
