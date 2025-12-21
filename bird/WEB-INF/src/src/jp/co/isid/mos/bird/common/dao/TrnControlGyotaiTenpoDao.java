/*
 * 作成日: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiTenpo;

/**
 * 制御業態店舗別DAO
 * @author xnkusama
 */
public interface TrnControlGyotaiTenpoDao {

    public Class BEAN = TrnControlGyotaiTenpo.class;

    public static final String getControlGyotaiTenpo_ARGS = "infoShu, regDate, seq, gyotaiKbn";
    public static final String getControlGyotaiTenpoInfoAccessByOner_ARGS = "infoShu, userId";
    public static final String getControlGyotaiTenpoInfoAccessByMise_ARGS = "infoShu, userId";
        
    /**
     * アクセス情報制御業態別店舗の取得
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @param gyotaiKbn 業態区分
     * @return
     */
    public List getControlGyotaiTenpo(
                    String infoShu,
                    String regDate,
                    String seq,
                    String gyotaiKbn);
    
    /**
     * アクセス情報制御業態別店舗の削除
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @param gyotaiKbn 業態区分
     * @return
     */
    public int deleteControlGyotaiTenpo(TrnControlGyotaiTenpo entity);
    
    /**
     * アクセス情報制御業態別店舗の挿入
     * @param entity
     * @return
     */
    public int insertControlGyotaiTenpo(TrnControlGyotaiTenpo entity);
    
    /**
     * 店舗用 個別設定 店舗
     * @param infoShu 情報種別
     * @param userId  ユーザーID
     * @return
     */
    public List getControlGyotaiTenpoInfoAccessByMise(String infoShu, String userId);

    /**
     * オーナー用 個別設定 店舗
     * @param infoShu 情報種別
     * @param userId  ユーザーID
     * @return
     */
    public List getControlGyotaiTenpoInfoAccessByOner(String infoShu, String userId);
}
