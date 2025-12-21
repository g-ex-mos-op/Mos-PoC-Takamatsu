/*
 * 作成日: 2006/08/21
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.dao;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.entity.UIShohinBetuInfo;

/**
 * 商品別情報取得DAO
 *
 * @author xlee
 */
public interface UIShohinBetuInfoDao {

    public static final Class BEAN = UIShohinBetuInfo.class;

    public static final String getShohinBetuInfo_ARGS = "miseCd, urikakeYm, uriendYm";

    /**
     * 商品別情報を検索します
     *
     * @param miseCd 店コード
     * @param urikakeYm 売掛残高年月
     * @return 商品別情報 List
     */
    public List getShohinBetuInfo(String miseCd, String urikakeYm,String uriendYm);
}
