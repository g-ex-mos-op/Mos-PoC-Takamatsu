/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UITitleInfo;

/**
 * タイトル情報
 * 
 * @author xlee
 */
public interface UITitleInfoDao {

    public static final Class BEAN = UITitleInfo.class;

    public static final String getTitleInfo_ARGS = "sysDate";

    /**
     *　タイトル情報を取得
     * @param sysDate システム日付
     * @return タイトル
     */
    public List getTitleInfo(String sysDate);
}
