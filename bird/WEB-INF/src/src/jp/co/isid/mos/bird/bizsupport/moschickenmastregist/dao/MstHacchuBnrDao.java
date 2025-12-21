package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstHacchuBnr;

public interface MstHacchuBnrDao {
    /**
     * 格納エンティティクラス
     */
    public static final Class BEAN = MstHacchuBnr.class;
    
    /**
     * 検索情報取得時のパラメータ
     */
    public static final String select_ARGS = "";
    /**
     * 検索情報取得
     * @return List
     */
    public List select();

}
