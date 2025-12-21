/*
 * 作成日: 2006/3/10
 *
 */
package jp.co.isid.mos.bird.storeinfo.onerref.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.onerref.entity.CodOnerLinkJoho;

/**
 * オーナー照会リンク情報（CodOnerLinkJohoDao）
 * @author itamoto
 */
public interface CodOnerLinkJohoDao {

    public static final Class BEAN = CodOnerLinkJoho.class;
    public static final String selectOnerLink_ARGS  = "USER_ID";

    /**
     * オーナー照会リンク情報の検索(selectOnerLink)
     */
    public List selectOnerLink(String userId);
}
