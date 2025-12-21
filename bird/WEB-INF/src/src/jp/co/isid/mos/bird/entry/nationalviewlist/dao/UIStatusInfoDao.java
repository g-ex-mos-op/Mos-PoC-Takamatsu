package jp.co.isid.mos.bird.entry.nationalviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.nationalviewlist.entity.UIStatusInfo;

/**
 * 対象全国大会情報取得
 * 
 * @author xkinu
 */
public interface UIStatusInfoDao {

    public static final Class BEAN = UIStatusInfo.class;
    public static final String select_ARGS = "entryCd, entryYear, entryKai, sysDate";

    /**
     * 対象コース情報取得処理
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @return
     */
    public List select(String entryCd, String entryYear, String entryKai, String sysDate);

}