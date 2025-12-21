/*
 * 作成日: 2006/05/18
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodEventPatternCd;


/**
 * イベントパターンコード
 * @author xayumi
 */
public interface CodEventPatternCdDao {

    public static final Class BEAN = CodEventPatternCd.class;
    public static final String selectEventPattern_ARGS = "eventCd, gyotaiKbn";

    /**
     * イベントパターンコードの検索
     * @param String eventCd   イベント種別
     * @param String gyotaiKbn 業態区分
     * @return List
     */
    public List selectEventPattern(String eventCd, String gyotaiKbn);
    
}            
