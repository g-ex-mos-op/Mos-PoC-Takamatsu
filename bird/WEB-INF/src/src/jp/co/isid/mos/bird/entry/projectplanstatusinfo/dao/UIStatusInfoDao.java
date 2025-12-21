package jp.co.isid.mos.bird.entry.projectplanstatusinfo.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIStatusInfo;

/**
 * 初期画面を出力する。
 * @author xamaruyama
 */
public interface UIStatusInfoDao {

    public static final Class BEAN = UIStatusInfo.class;
    public static final String getCode_ARGS = "entryCd, entryYear, entryKai, companyCd, sysDate";

    /**
     * 対象コース情報取得処理
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sysDate
     * @return
     */
    public List getCode(String entryCd, String entryYear, String entryKai, String companyCd, String sysDate);

}