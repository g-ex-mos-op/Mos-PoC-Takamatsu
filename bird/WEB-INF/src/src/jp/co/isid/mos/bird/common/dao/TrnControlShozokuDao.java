/*
 * 作成日: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.TrnControlShozoku;


/**
 * 制御対象所属DAO
 * @author xnkusama
 */
public interface TrnControlShozokuDao {

    public Class BEAN = TrnControlShozoku.class;

    public static final String getControlShozoku_ARGS = "infoShu, regDate, seq";
    
    /**
     * アクセス情報制御所属の取得
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @return
     */
    public List getControlShozoku(
                    String infoShu,
                    String regDate,
                    String seq);
    
    /**
     * アクセス情報制御所属の削除
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @return
     */
    public int deleteControlShozoku(TrnControlShozoku entity);
    
    /**
     * アクセス情報制御所属の挿入
     * @param entity
     * @return
     */
    public int insertControlShozoku(TrnControlShozoku entity);
    
}
