package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.UITakuhaiMijissi;

/**
 * 宅配未実施情報取得
 * @author kusama
 */
public interface UITakuhaiMijissiDao {

    public static final Class BEAN = UITakuhaiMijissi.class;
    public static final String select_ARGS = "companyCd, sibuCd, targetYM, closeDt";

    /**
     * 管理会社企業情報の取得
     * @param String userId ユーザーID
     * @return List
     */
    public List select(String companyCd, String sibuCd, String targetYM, String closeDt);
    
}