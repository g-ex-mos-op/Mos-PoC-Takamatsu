package jp.co.isid.mos.bird.entry.eventlist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.eventlist.dao.CtlEntryMasterDao;
import jp.co.isid.mos.bird.entry.eventlist.logic.GetEntryMasterListLogic;

/**
 * @author xyuchida
 *
 */
public class GetEntryMasterListLogicImpl implements GetEntryMasterListLogic {

    /** ロジックID */    
    public static final String LOGIC_ID = "BEN091L01";

    private CtlEntryMasterDao ctlEntryMasterDao;

    /**
     * イベント一覧取得
     * 
     * @param entryCdList エントリーコードリスト
     * @param bunrui 画面分類
     * @param usertypeCd ユーザタイプコード
     * @param sysDate システム日付
     */
    public List execute(List entryCdList, String bunrui, String usertypeCd, String sysDate) {
        return getCtlEntryMasterDao().selectEntryMasterList(entryCdList, bunrui, usertypeCd, sysDate);
    }

    public CtlEntryMasterDao getCtlEntryMasterDao() {
        return ctlEntryMasterDao;
    }

    public void setCtlEntryMasterDao(CtlEntryMasterDao ctlEntryMasterDao) {
        this.ctlEntryMasterDao = ctlEntryMasterDao;
    }
}
