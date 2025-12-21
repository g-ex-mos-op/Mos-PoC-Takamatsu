/*
 * 作成日: 2008/02/19
 */
package jp.co.isid.mos.bird.communication.docform.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.docform.entity.UIViewDocFormInfo;


/**
 * 文書・フォーム検索Dao
 * 
 * 作成日:2011/02/21
 * @author xkinu
 *
 */
public interface UIDocFormDao {

    public static final Class BEAN = UIViewDocFormInfo.class;

    public static final String select_ARGS = "infoShu, cateId, subCateId, title, sysDate, userId, userTypeCd, kobetsuFlg";

    /**
     * 文書・フォーム検索情報の取得
     * 
     * @param infoShu
     * @param cateId
     * @param subCateId
     * @param title
     * @param sysDate
     * @param userId
     * @param userTypeCd
     * @param kobetsuFlg
     * @return
     */
    public List select(String infoShu, String cateId, String subCateId,
            String title, String sysDate, String userId, String userTypeCd
            , boolean kobetsuFlg);
}