package jp.co.isid.mos.bird.bill.buyinglistview.dao;

import java.util.List;
import jp.co.isid.mos.bird.bill.buyinglistview.entity.UISeikyuMeisai;

/**
 * 請求済売掛明細情報
 * @author xwatanabe
 */
public interface UISeikyuMeisaiDao {

    public static final Class BEAN = UISeikyuMeisai.class;

    public static final String getSeikyuMeisai_ARGS = "companyCd, urikakeCd, seikyuId, sort";
    public static final String getMiseCnt_ARGS = "onerCd, fromDt, toDt";
    
    /**
     * 請求書一覧の取得
     * @param  会社コード
     * @param  売掛先コード
     * @param  請求書ID
     * @param  ソート順
     * @return List
     */
    public List getSeikyuMeisai(String companyCd, String urikakeCd, String seikyuId, String sort);
    
    /**
     * オーナー保有店舗数取得
     * @param onerCd
     * @param fromDt
     * @param toDt
     */
    public int getMiseCnt(String onerCd, String fromDt, String toDt);
}