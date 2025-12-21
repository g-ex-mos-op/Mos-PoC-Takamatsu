package jp.co.isid.mos.bird.bizreport.common.dao;

import java.util.List;
import jp.co.isid.mos.bird.bizreport.common.entity.MstAccountKbnInfo;

/**
 * 会計区分情報Daoクラス
 * 
 * @author mwatanabe
 */
public interface MstAccountKbnInfoDao {

    public static final String select_ARGS = "kkbnCd";

    /**
     * 会計区分情報エンティティクラス
     */
    public static final Class BEAN = MstAccountKbnInfo.class;
 
    /**
     * 会計区分情報リストを取得する
     * @return List 会計区分情報リスト
     */
    public List select(String kkbnCd);

    /**
     * 会計区分情報リスト(全レコード)を取得する
     * @return List 会計区分情報リスト
     */
    public List selectAll();
}