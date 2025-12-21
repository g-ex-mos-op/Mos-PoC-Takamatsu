package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.TrnDocAccess;

public interface TrnDocAccessDao {

    public Class BEAN = TrnDocAccess.class;

    public static final String getDocAccess_ARGS = "infoShu, regDate, seq, companyCd, userId";
    
    /**
     * 対象文書のアクセス権限の取得
     * @param infoShu 情報種別
     * @param regDate 登録日
     * @param seq シーケンス番号
     * @param companyCd 会社コード
     * @param userId ユーザーID
     * @return アクセス権限
     */
    public List getDocAccess(String infoShu, String regDate, String seq, String companyCd, String userId);



}
