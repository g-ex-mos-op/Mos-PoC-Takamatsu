/*
 * 作成日: 2006/01/23
 *
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiKobetu;

/**
 * 制御業態個別DAO
 * @author xnkusama
 */
public interface TrnControlGyotaiKobetuDao {

    public Class BEAN = TrnControlGyotaiKobetu.class;

    public static final String getControlGyotaiKobetu_ARGS = "infoShu, regDate, seq, gyotaiKbn";
    
    public static final String getControlGyotaiByOner_ARGS = "infoShu, userId";
    
    public static final String getControlGyotaiByMise_ARGS = "infoShu, userId";
    
    /**
     * アクセス情報制御業態別個別の取得
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @param gyotaiKbn 業態区分
     * @return
     */
    public List getControlGyotaiKobetu(
                    String infoShu,
                    String regDate,
                    String seq,
                    String gyotaiKbn);
    
    /**
     * アクセス情報制御業態別個別の削除
     * @param infoShu   情報種別
     * @param regDate   登録日
     * @param seq       SEQ
     * @param gyotaiKbn 業態区分
     * @return
     */
    public int deleteControlGyotaiKobetu(TrnControlGyotaiKobetu entity);
    
    /**
     * アクセス情報制御業態の挿入
     * @param entity
     * @return
     */
    public int insertControlGyotaiKobetu(TrnControlGyotaiKobetu entity);
    
    /**
     * オーナー用 個別設定（支部取込）
     * @param infoShu 情報種別
     * @param userId  ユーザーID
     * @return
     */
    public List getControlGyotaiByOner(String infoShu, String userId);

    /**
     * 店舗用 個別設定（支部取込）
     * @param infoShu 情報種別
     * @param userId  ユーザーID
     * @return
     */
    public List getControlGyotaiByMise(String infoShu, String userId);
}
