/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UITitleInfo;

/**
 * タイトル情報
 * 
 * @author xlee
 */
public interface UITitleInfoDao {

    public static final Class BEAN = UITitleInfo.class;

    public static final String getTitleInfo_ARGS = "condFromDt,condToDt";

    /**
     *　タイトル情報を取得
     * @param condFromDt アプリ日付FROM
     * @param condToDt アプリ日付TO
     * @return タイトル
     */
    public List getTitleInfo(String condFromDt, String condToDt);
}
