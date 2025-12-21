/*
 * 作成日: 2006/12/27
 */
package jp.co.isid.mos.bird.entry.longserviceviewlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceviewlist.entity.UILSViewListEvent;

/**
 * 永年勤続申請状況のヘッダ部を取得する。
 * @author xamaruyama
 */
public interface UILSViewListEventDao {

    public static final Class BEAN = UILSViewListEvent.class;
    
    public static final String getEvent_ARGS = "entryCd, entryYear, entryKai, companyCd, sysDate";

    /**
     * 永年勤続申請状況のヘッダ部情報取得処理
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param sysDate
     * @return
     */
    public List getEvent(String entryCd, String entryYear, String entryKai, String companyCd, String sysDate);

}