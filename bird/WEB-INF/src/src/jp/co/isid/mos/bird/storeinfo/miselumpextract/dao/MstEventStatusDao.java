package jp.co.isid.mos.bird.storeinfo.miselumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.MstEventStatus;

/**
 * イベント実施状況
 * @author xayumi
 */
public interface MstEventStatusDao {

    public static final Class BEAN = MstEventStatus.class;
    public static final String selectEvent_ARGS     = "companyCd, miseCd";
    public static final String selectEventMise_ARGS = "companyCd, sibuCd, closeFlg";
    public static final String selectCount_ARGS     = "companyCd, sibuCd, closeFlg";

    /**
     * イベント実施状況の検索
     * @param String companyCd 会社コード
     * @param String miseCd    店コード
     * @return List
     */
    public List selectEvent(String companyCd, String miseCd);
    
    /**
     * イベント分類毎の、イベント種別の数のMAXを取得
     * @param  String companyCd 会社コード
     * @param  String sibuCd    支部コード
     * @param  String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectCount(String companyCd, String sibuCd, String closeFlg);
    
    /**
     * イベント実施状況の検索
     * @param  String companyCd 会社コード
     * @param  String sibuCd    支部コード
     * @param  String closeFlg  店クローズ（含む／含まない）フラグ
     * @return List
     */
    public List selectEventMise(String companyCd, String sibuCd, String closeFlg);
    
}