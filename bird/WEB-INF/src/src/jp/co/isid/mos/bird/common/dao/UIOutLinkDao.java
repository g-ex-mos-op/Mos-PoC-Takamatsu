/*
 * 作成日: 2009/01/09
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIOutLink;

/**
 * 外部リンク情報取得
 *
 * 作成日:2009/01/09
 * @author xkinu
 *
 */
public interface UIOutLinkDao {

    public static final Class BEAN = UIOutLink.class;
    public static final String select_ARGS = "listDispKbn, userId, outLinkId, dougaCd";

    /**
     * 外部リンク情報を取得
     *
     * @param listDispKbn
     * @param userId
     * @return
     */
    public List select(String[] listDispKbn, String userId, String outLinkId,String dougaCd);
}
