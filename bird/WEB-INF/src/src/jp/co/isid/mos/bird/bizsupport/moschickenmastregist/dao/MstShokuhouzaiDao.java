package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstShokuhouzai;

/**
 * 食包材マスタDaoインターフェース
 * @author xkinu
 *
 */
public interface MstShokuhouzaiDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = MstShokuhouzai.class;
    
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "cdFrom, cdTo, name, hacchuBnr";
    /**
     * 検索情報取得
     * 
     * @param cdFrom
     * @param cdTo
     * @param name
     * @param hacchuBnr
     * @return
     */
    public List select(String cdFrom, String cdTo, String name, String hacchuBnr);

}
