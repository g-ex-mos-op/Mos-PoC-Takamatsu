/*
 * 作成日: 2006/10/12
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveSumInfo;

/**
 * 予約合計情報
 * 
 * @author xlee
 */
public interface UIReserveSumInfoDao {

    public static final Class BEAN = UIReserveSumInfo.class;

    public static final String getReserveSumInfo_ARGS = "ckanriNo, companyCd, miseCd, reservDt";

    /**
     *　予約合計情報を取得
     * @param ckanriNo 管理番号
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @param reservDt お渡し日
     * @return 予約合計情報
     */
    public List getReserveSumInfo(String ckanriNo, String companyCd, String miseCd, String reservDt);
}
