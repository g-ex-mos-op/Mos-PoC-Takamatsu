/*
 * ì¬“ú: 2006/10/12
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveInfo;

/**
 * —\–ñî•ñ
 * 
 * @author xlee
 */
public interface UIReserveInfoDao {

    public static final Class BEAN = UIReserveInfo.class;

    public static final String getReserveInfo_ARGS = "ckanriNo, miseCd, reservDt, companyCd";

    /**
     *@—\–ñî•ñ‚ğæ“¾
     * @param ckanriNo ŠÇ—”Ô†
     * @param miseCd “XƒR[ƒh
     * @param reservDt ‚¨“n‚µ“ú
     * @param companyCd ‰ïĞƒR[ƒh
     * @return —\–ñî•ñ
     */
    public List getReserveInfo(String ckanriNo, String miseCd, String reservDt, String companyCd);
}
