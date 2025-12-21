/*
 * 作成日: 2005/12/27
 */
package jp.co.isid.mos.bird.communication.documentdownload.dao;

import java.util.List;

import jp.co.isid.mos.bird.communication.documentdownload.entity.UIViewShozokuInfo;

/**
 * 所属情報
 * @author itamoto
 */
public interface UIViewShozokuInfoDao {

    public static final Class BEAN = UIViewShozokuInfo.class;

    public static final String getBunshoShozoku_ARGS = "infoShu, regDate, seq";

    /**
     * 所属情報の取得
     * @param String infoShu 情報種別
     * @param String regDate 登録日
     * @param String seq シーケンス番号
     * @return List
     */
    public List getBunshoShozoku(String infoShu, String regDate, String seq);
    
}
