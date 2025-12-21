/*
 * 作成日: 2008/03/18
 *
 */
package jp.co.isid.mos.bird.config.campaignmasterregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.entity.UISibuList;

/**
 * 
 * @author xnkusama
 * 更新日:2011/07/26 オープン中の店舗が存在する支部情報を取得できるよう対応
 */
public interface UISibuListDao {

    public static final Class BEAN = UISibuList.class;

    public static final String getSibuList_ARGS = "sysDate, areaDai";

    /**
     * 支部一覧の取得
     * 
     * @param sysDate システム日付(2011/07追加)
     * @param areaDai 直営・販社含むフラグ（true：含む）
     * @return List メニュー一覧
     */
    public List getSibuList(String sysDate, boolean areaDai);
}