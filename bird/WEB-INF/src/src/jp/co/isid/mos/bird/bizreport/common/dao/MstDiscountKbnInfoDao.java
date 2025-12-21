package jp.co.isid.mos.bird.bizreport.common.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizreport.common.entity.MstDiscountKbnInfo;

/**
 * 値引区分情報Daoクラス
 * 
 * @author mwatanabe
 */
public interface MstDiscountKbnInfoDao {

    public static final String select_ARGS = "nkbnCd";

    /**
     * 値引区分情報エンティティクラス
     */
    public static final Class BEAN = MstDiscountKbnInfo.class;
 
    /**
     * 値引区分情報リストを取得する
     * @return List 値引区分情報リスト
     */
    public List select(String nkbnCd);

    /**
     * 値引区分情報リスト(全レコード)を取得する
     * @return List 値引区分情報リスト
     */
    public List selectAll();
}