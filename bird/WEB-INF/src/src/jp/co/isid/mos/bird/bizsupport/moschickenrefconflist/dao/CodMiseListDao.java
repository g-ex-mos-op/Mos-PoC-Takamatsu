/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.CodMiseList;

/**
 * @author inazawa
 *
 */
public interface CodMiseListDao {
    
    public static final Class BEAN = CodMiseList.class;
    
    public static final String getHonbuUserMiseInfo_ARGS = "companyCd, onerCd, sysDate";
    public static final String getOnerUserMiseInfo_ARGS  = "companyCd, userId, sysDate";
    public static final String getTenpoUserMiseInfo_ARGS = "companyCd, userId, sysDate";
    public static final String getMiseInfo_ARGS = "companyCd,miseCd";
    
    /**
     * 店舗取得(本部ユーザー)
     * @param companyCd
     * @param onerCd
     * @param sysDate
     * @return
     */
    public List getHonbuUserMiseInfo(String companyCd, String onerCd, String sysDate);
    /**
     * 店舗取得（オーナーユーザー）
     * @param companyCd
     * @param userId
     * @param sysDate
     * @return
     */
    public List getOnerUserMiseInfo(String companyCd, String userId, String sysDate);
    /**
     * 店舗取得（店舗ユーザー）
     * @param companyCd
     * @param userId
     * @param sysDate
     * @return
     */
    public List getTenpoUserMiseInfo(String companyCd, String userId, String sysDate);
    /**
     * 条件で選択された店名称取得
     */
    public List getMiseInfo(String companyCd, String miseCd);
    }
