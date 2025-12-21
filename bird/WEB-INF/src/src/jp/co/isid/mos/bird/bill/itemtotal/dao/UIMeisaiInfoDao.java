/*
 * 作成日: 2006/08/21
 *
 */
package jp.co.isid.mos.bird.bill.itemtotal.dao;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.entity.UIMeisaiInfo;

/**
 *　明細情報取得DAO
 *
 * @author xlee
 */
public interface UIMeisaiInfoDao {

    public static final Class BEAN = UIMeisaiInfo.class;

    public static final String getMeisaiInfo_ARGS = "miseCd, shoJituCd, condYm, uriendYm";

    /**
     * 明細情報を検索します。
     *
     * @param miseCd パラメータ対象店舗
     * @param shoJituCd 実商品コード
     * @param　condYm　パラメータ対象年月
     *
     * @return 明細情報　List
     */
    public List getMeisaiInfo(String miseCd, String shoJituCd, String condYm,String uriendYm);
}
