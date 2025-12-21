/*
 * 作成日: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.TrnControlCompany;

/**
 * 制御対象会社DAO
 * @author xnkusama
 */
public interface TrnControlCompanyDao {

    public Class BEAN = jp.co.isid.mos.bird.common.entity.TrnControlCompany.class;

    public static final String getControlCompany_ARGS = "infoShu, regDate, seq";
    
    /**
     * 文書情報の取得
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @return
     */
    public List getControlCompany(
                    String infoShu,
                    String regDate,
                    String seq);
    
    /**
     * アクセス情報制御会社の削除
     * @param entity
     * @return
     */
    public int deleteControlCompany(TrnControlCompany entity);
    
    /**
     * アクセス情報制御会社の挿入
     * @param entity
     * @return
     */
    public int insertControlCompany(TrnControlCompany entity);
    
}
