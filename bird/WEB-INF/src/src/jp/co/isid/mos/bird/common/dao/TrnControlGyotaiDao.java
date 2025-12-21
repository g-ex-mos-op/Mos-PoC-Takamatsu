/*
 * 作成日: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.TrnControlGyotai;


/**
 * 制御対象業態DAO
 * @author xnkusama
 */
public interface TrnControlGyotaiDao {

    public Class BEAN = jp.co.isid.mos.bird.common.entity.TrnControlGyotai.class;

    public static final String getControlGyotai_ARGS = "infoShu, regDate, seq";
    
    /**
     * アクセス情報制御業態の取得
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @return
     */
    public List getControlGyotai(
                    String infoShu,
                    String regDate,
                    String seq);
    
    /**
     * アクセス情報制御業態の削除
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @return
     */
    public int deleteControlGyotai(TrnControlGyotai entity);
    
    /**
     * アクセス情報制御業態の挿入
     * @param entity
     * @return
     */
    public int insertControlGyotai(TrnControlGyotai entity);
    
}
