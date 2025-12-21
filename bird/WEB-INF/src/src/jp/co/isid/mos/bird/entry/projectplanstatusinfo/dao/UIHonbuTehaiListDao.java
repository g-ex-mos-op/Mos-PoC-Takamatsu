package jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIHonbuTehaiList;

/**
 * 本部手配宿泊情報Dao
 * 
 * @author xamaruyama
 */
public interface UIHonbuTehaiListDao {

    public static final Class BEAN = UIHonbuTehaiList.class;
    /**
     * パラメーター情報：本部手配宿泊情報を取得
     */
    public static final String getHonbuTehaiList_ARGS  = "limit, userId, entryCd, entryYear, entryKai" +
    ", sysDate, companyCd, taishouJokenChoice, sibuListChoice, svCd";
    /**
     * 本部手配宿泊情報を取得する。
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param taishouJokenChoice
     * @param sibuListChoice
     * @param svCd
     * @return
     */
    public List getHonbuTehaiList(boolean limit, String userId,
            String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String taishouJokenChoice, String sibuListChoice, String svCd);
}