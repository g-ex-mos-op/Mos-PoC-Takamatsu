/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListCSV;

/**
 * ダウンロード対象情報取得
 * 
 * @author xamaruyama
 * 
 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
 *                    SQLでuserIdのパラメータありきで設定されているため、
 *                    常に支部制限のユーザは該当データ無しになっていました。
 *                    DAOにパラメータuserIdを追加し、対応しました。
 */
public interface UILSViewListCsvDao {
    
    public static final Class BEAN = UILSViewListCSV.class;
    
    public static final String getOnerApply_ARGS = "userId, limit" +
    		", entryCd, entryYear, entryKai, sysDate" +
            ", companyCd, KbnChoice, fromEntryDt";

    public static final String getOnerNoApply_ARGS = "userId, limit" +
    		", entryCd, entryYear, entryKai, sysDate" +
            ", companyCd, KbnChoice, fromEntryDt";
    
    /**
     * CSVダウンロード　申請情報取得
     * 
	 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
	 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
	 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
	 *                    SQLでuserIdのパラメータありきで設定されているため、
	 *                    常に支部制限のユーザは該当データ無しになっていました。
	 *                    DAOにパラメータuserIdを追加し、対応しました。
     * @param userId
     * @param limit
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param KbnChoice
     * @param fromEntryDt(2012/12/04追加)
     * @return
     */
    public List getOnerApply(String userId, boolean limit
    		, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String KbnChoice, String fromEntryDt);
    
    /**
     * CSVダウンロード　未申請情報取得
     * 
	 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
	 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
	 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
	 *                    SQLでuserIdのパラメータありきで設定されているため、
	 *                    常に支部制限のユーザは該当データ無しになっていました。
	 *                    DAOにパラメータuserIdを追加し、対応しました。
     * @param userId
     * @param limit
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param sysDate
     * @param companyCd
     * @param KbnChoice
     * @param fromEntryDt(2012/12/04追加)
     * @return
     */
    public List getOnerNoApply(String userId, boolean limit
    		, String entryCd, String entryYear, String entryKai, String sysDate
            , String companyCd, String KbnChoice, String fromEntryDt);
    
}