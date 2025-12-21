package jp.co.isid.mos.bird.entry.eventlist.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.eventlist.entity.CtlEntryMaster;

/**
 * @author xyuchida
 *
 */
public interface CtlEntryMasterDao {

    public static final Class BEAN = CtlEntryMaster.class;

    public static final String selectEntryMasterList_ARGS = "entryCdList, bunrui, usertypeCd, sysDate";

    public List selectEntryMasterList(List entryCdList, String bunrui, String usertypeCd, String sysDate);
}
