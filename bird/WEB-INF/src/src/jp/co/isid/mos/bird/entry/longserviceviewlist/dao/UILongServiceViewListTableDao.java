/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListTable;

/**
 * 永年勤続申請状況のデータ部を取得する。
 * 
 * @author xamaruyama
 * 更新日：2012/12/04 xkinu「本部申込開始日」と「ｸﾛｰｽﾞ日」を比較、
 *                     本部申込開始日より前にクローズした店舗は、画面に表示させないようにする。
 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
 *                    SQLでuserIdのパラメータありきで設定されているため、
 *                    常に支部制限のユーザは該当データ無しになっていました。
 *                    DAOにパラメータuserIdを追加し、対応しました。
 */
public interface UILongServiceViewListTableDao {

    public static final Class BEAN = UILSViewListTable.class;
    
    /**
     * パラメーター情報：
     */
    public static final String getLongServiceTable_ARGS = "userId, limit" +
    		", entryCd, entryYear, entryKai, sysDate, companyCd, taishouJokenChoice, sibuChoice, svCd, fromEntryDt";

    /**
     * 永年勤続のオーナー別申請状況
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
     * @param taishouJokenChoice
     * @param sibuChoice
     * @param svCd
     * @param fromEntryDt(2012/12/04追加)
     * @return
     */
    public List getLongServiceTable(String userId, boolean limit
    		, String entryCd, String entryYear, String entryKai
    		, String sysDate, String companyCd
    		, String taishouJokenChoice, String sibuChoice, String svCd, String fromEntryDt);

}