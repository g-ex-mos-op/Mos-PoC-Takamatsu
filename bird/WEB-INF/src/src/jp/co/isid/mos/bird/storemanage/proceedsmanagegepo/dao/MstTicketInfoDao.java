package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstTicketInfo;

/**
 * チケット情報Daoクラス
 * 
 * @author xjung
 */
public interface MstTicketInfoDao {

    /**
     * チケット情報エンティティクラス
     */
    public static final Class BEAN = MstTicketInfo.class;

    /**
     * チケット情報リスト取得時のパラメータ
     */
    public static final String select_ARGS =
          "companyCd"
        + ", miseCd";
 
    /**
     * チケット情報リストを取得する
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @return List チケット情報リスト
     */
    public List select(String companyCd, String miseCd);  
}