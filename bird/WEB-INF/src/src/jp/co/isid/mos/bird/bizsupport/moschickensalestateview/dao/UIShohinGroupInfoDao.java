/*
 * 作成日: 20060/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UIShohinGroupInfo;

/**
 * 商品グループ情報
 * 
 * @author xlee
 */
public interface UIShohinGroupInfoDao {

    public static final Class BEAN = UIShohinGroupInfo.class;

    public static final String getShohinGroupInfo_ARGS = "ckanriNo";

    /**
     * 商品グループ情報を取得
     * @param ckanriNo タイトルの管理番号
     * @return 商品グループリスト
     */
    public List getShohinGroupInfo(String ckanriNo);
}
