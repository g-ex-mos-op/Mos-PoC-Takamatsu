/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bizreport.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.entity.CodSegmentInfo;

/**
 * 条件部セグメント情報取得
 * 対象会社コードのセグメント情報の取得を行います。
 * 
 * @author xwatanabe
 */
public interface CodSegmentInfoDao {

    public static final Class BEAN = CodSegmentInfo.class;
    
    public static final String getSegmentInfo_ARGS    = "companyCd";

    /**
     * セグメント情報の取得
     * @param String companyCd  会社コード
     * @return List
     */
    public List getSegmentInfo(String companyCd);

}
