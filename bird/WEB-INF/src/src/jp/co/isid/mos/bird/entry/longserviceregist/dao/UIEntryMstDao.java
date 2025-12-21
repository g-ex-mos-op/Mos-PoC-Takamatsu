/*
 * 作成日: 2006/12/18
 */
package jp.co.isid.mos.bird.entry.longserviceregist.dao;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.entry.longserviceregist.entity.UIEntryMst;


/**
 * エントリーマスタ管理（UIEntryMstDao）
 * @author itamoto
 */
public interface UIEntryMstDao {

    /**
     * エントリーマスタ管理エンティティ
     */
    public static final Class BEAN = UIEntryMst.class;

    /**
     * 永年勤続マスタ情報リスト取得時のパラメータ
     */
    public static final String getEntryMst_ARGS = "entryCd, entryYear";

    /**
     * 永年勤続マスタ情報のエントリー回の最大値の取得時のパラメータ
     */
    public static final String getMaxEntryKai_ARGS = "entryCd, entryYear";
    
    /**
     * エントリー管理の新規登録パラメータ
     */
    public static final String insertEntryMst_ARGS = "uiEntryMst";
    
    /**
     * エントリー管理の更新パラメータ
     */
    public static final String update_PERSISTENT_PROPS = "entryTitle, sakujoFlg, lastUser, lastPgm";
    
    /**
     * エントリー管理の更新パラメータ
     */
    public static final String updateEntryMstKey_ARGS = "uiEntryMst,newEntryYear,newEntryKai,lastTmsp";
       
    /**
     * エントリー管理の削除フラグ更新パラメータ
     */
    public static final String updateSakujoFlg_PERSISTENT_PROPS = "sakujoFlg, lastUser, lastPgm";
        
    /**
     * 永年勤続マスタ情報リストを取得する
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @return List 永年勤続マスタ情報リスト
     */
    public List getEntryMst(String entryCd, String entryYear);
    
    /**
     * 永年勤続マスタ情報のエントリー回の最大値を取得する
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @return int エントリー回の最大値
     */
    public int getMaxEntryKai(String entryCd, String entryYear);
    
    /**
     * エントリー管理の新規登録
     * @param uiEntryMst
     */
    public int insertEntryMst(UIEntryMst uiEntryMst);

    /**
     * エントリー管理の更新
     * @param uiEntryMst
     */
    public int update(UIEntryMst uiEntryMst);
    
    /**
     * エントリー管理の更新（キーを更新する場合）
     * @param uiEntryMst
     */
    public int updateEntryMstKey(UIEntryMst uiEntryMst,String newEntryYear,String newEntryKai,Timestamp lastTmsp);

    /**
     * エントリー管理の削除フラグ更新
     * @param uiEntryMst
     */
    public int updateSakujoFlg(UIEntryMst uiEntryMst);
        
}
