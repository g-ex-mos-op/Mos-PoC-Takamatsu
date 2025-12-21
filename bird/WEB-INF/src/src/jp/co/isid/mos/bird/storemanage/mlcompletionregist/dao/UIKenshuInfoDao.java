/*
 * 作成日: 2006/04/11
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIKenshuInfo;

/**
 * 研修情報取得
 * @author xkinu
 */
public interface UIKenshuInfoDao {

    public static final Class BEAN = UIKenshuInfo.class;
    public static final String select_ARGS = "entryCd, sysDate, usertypeCd";

    /**
     * 研修情報の検索
     * 
     * @param entrycd エントリーコード
     * @param sysDate システム日付
     * @param usertypeCd ユーザータイプコード
     * @return
     */
    public List select(String entrycd, String sysDate, String usertypeCd);
    
}            
