/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.EntryDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * エントリーマスタ管理の検索ロジック
 * @author itamoto
 */
public class SearchEntryLogicImpl implements SearchEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN005L05";

    /**
     * エントリー日付管理（UIEntryDateDao）
     */
    private UIEntryDateDao uiEntryDateHanyoApplicationDao;

    /**
     * エントリーマスタ管理（UIEntryMstDao）
     */
    private UIEntryMstDao uiEntryMstHanyoApplicationDao;

	/**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @return uiEntryDateDao を戻します。
	 */
	public UIEntryDateDao getUiEntryDateHanyoApplicationDao() {
		return uiEntryDateHanyoApplicationDao;
	}
	/**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @param uiEntryDateDao uiEntryDateDao を設定。
	 */
	public void setUiEntryDateHanyoApplicationDao(UIEntryDateDao uiEntryDateDao) {
		this.uiEntryDateHanyoApplicationDao = uiEntryDateDao;
	}

	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @return uiEntryMstDao を戻します。
	 */
	public UIEntryMstDao getUiEntryMstHanyoApplicationDao() {
		return uiEntryMstHanyoApplicationDao;
	}
	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @param uiEntryMstDao uiEntryMstDao を設定。
	 */
	public void setUiEntryMstHanyoApplicationDao(UIEntryMstDao uiEntryMstDao) {
		this.uiEntryMstHanyoApplicationDao = uiEntryMstDao;
	}

	/**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public void execute(HanyoApplicationDto dto) throws ApplicationException {
    	// １．必須のパラメータが満たされていること。E0506（@エントリーコード）
    	// ２．パラメータが妥当であること。E0505（@エントリーコード）
        validate(dto);
    	// １．Dao【エントリー日付管理．表示可能データKey項目の取得】を実行する。
        String sysDate = dto.getBirdDateInfo().getSysDate();
        String sysDatePlusOner = "";
        try { 
            sysDatePlusOner = DateManager.getNextDate(sysDate, 1);
        }
        catch (Exception ex) {
            throw new FtlSystemException("エントリー情報取得");
        }
    	// パラメータ： パラメータ．エントリーコード／システム日付
        List listEntry = getUiEntryDateHanyoApplicationDao()
                                .getKey(dto.getEntryCd(), sysDate, sysDatePlusOner, dto.getUserTypeCd());
        
        if (listEntry == null || listEntry.size() == 0) {
            //throw new NotExistException("登録済みの研修");
            return;
        }
        dto.setListDispEntryList(listEntry);
        
        List listEntryKey = new ArrayList();
        for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
            UIEntryDate entity = (UIEntryDate) ite.next();
            listEntryKey.add(entity.getEntryCd() + entity.getEntryYear() + entity.getEntryKai());
        }
    	
        // ２．Dao【エントリーマスタ管理．エントリーマスタ管理の取得】を実行する。
        List listEntryMst = getUiEntryMstHanyoApplicationDao().getEntry(dto.getEntryCd(), listEntryKey, dto.getCondOnerCd());
        
        // ２．１． 総申込人数の表示用値セット
        setSoMosikomiNinzu(listEntryMst);
        
    	// ３．Dao【エントリー日付管理．エントリー日付管理の取得】を実行する。
        List listEntryDate = getUiEntryDateHanyoApplicationDao().getEntryDate(
                dto.getEntryCd(), listEntryKey, dto.getUserTypeCd(), dto.getSysDate());
        
        // ４．[[エントリー日付管理]]の情報を[[エントリーマスタ管理]]にセットする。
        for (Iterator ite = listEntryMst.iterator(); ite.hasNext();) {
            UIEntryMst entityMst = (UIEntryMst) ite.next();
            for (Iterator ite2 = listEntryDate.iterator(); ite2.hasNext();) {
                UIEntryDate entityDate = (UIEntryDate) ite2.next();
                if (entityMst.getEntryCd().equals(entityDate.getEntryCd())
                        && entityMst.getEntryYear().equals(entityDate.getEntryYear())
                        && entityMst.getEntryKai().equals(entityDate.getEntryKai()))
                {
                    if (HanyoApplicationCommon.DAY_KBN_KAISAI.equals(entityDate.getDayKbn())){
                        entityMst.setKaisaiFrom(entityDate.getFromDt());
                        entityMst.setKaisaiTo(entityDate.getToDt());
                    }
                    // ユーザータイプコード＝０２（オーナー）の情報をセット
                    else if (HanyoApplicationCommon.DAY_KBN_TOROKU.equals(entityDate.getDayKbn())
                                && HanyoApplicationCommon.USERTYPE_CD_HONBU
                                    .equals(entityDate.getUsertypeCd())) 
                    {
                        entityMst.setHonbuUketukeFrom(entityDate.getFromDt());
                        entityMst.setHonbuUketukeTo(entityDate.getToDt());
                    }
                    else if (HanyoApplicationCommon.DAY_KBN_TOROKU.equals(entityDate.getDayKbn())
                                && HanyoApplicationCommon.USERTYPE_CD_ONER
                                    .equals(entityDate.getUsertypeCd())) 
                    {
                        entityMst.setOnerUketukeFrom(entityDate.getFromDt());
                        entityMst.setOnerUketukeTo(entityDate.getToDt());
                    }
                    else if (HanyoApplicationCommon.DAY_KBN_HYOJI.equals(entityDate.getDayKbn())
                                && HanyoApplicationCommon.USERTYPE_CD_HONBU
                                    .equals(entityDate.getUsertypeCd())) 
                    {
                        entityMst.setHyojiFrom(entityDate.getFromDt());
                        entityMst.setHyojiTo(entityDate.getToDt());
                    }
                    else if (HanyoApplicationCommon.DAY_KBN_HYOJI.equals(entityDate.getDayKbn())
                                && HanyoApplicationCommon.USERTYPE_CD_ONER
                                    .equals(entityDate.getUsertypeCd())) 
                    {
                        entityMst.setOnerHyojiFrom(entityDate.getFromDt());
                        entityMst.setOnerHyojiTo(entityDate.getToDt());
                    }
                    entityMst.setDisplayFlg(entityDate.getDisplayFlg());
                    entityMst.setEditFlg(entityDate.getEditFlg());
                    
                    continue;
                }
            }
        }
        
        // 開催日でソート（降順）
        Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String key1 = ((UIEntryMst) obj1).getKaisaiFrom();
                if (key1 == null) {
                    key1 = "";
                }
                String key2 = ((UIEntryMst) obj2).getKaisaiFrom();
                if (key2 == null) {
                    key2 = "";
                }
                return key1.compareTo(key2);
            }
        };
        Collections.sort(listEntryMst, sortComparator);

        // エントリー略称をセット
        for (Iterator ite = listEntryMst.iterator(); ite.hasNext();) {
            UIEntryMst uiEntryMst = (UIEntryMst) ite.next();
            uiEntryMst.setEntryNameRyak(getEntryNameRyak(uiEntryMst.getEntryCd()));
        }
        
    	// ５．２.で取得した[[エントリーマスタ管理]] ・ ３.で取得した[[エントリー日付管理]]をリターンする。
        dto.setListEntryMst(listEntryMst);
        dto.setListEntryDate(listEntryDate);
        
        // 編集、表示可能なエントリーの数を取得する
        int cntEditDisp = 0;
        for (Iterator ite = listEntryMst.iterator(); ite.hasNext();) {
            UIEntryMst entity = (UIEntryMst) ite.next();
            if ("1".equals(entity.getEditFlg()) || "1".equals(entity.getDisplayFlg())) {
                cntEditDisp++;
            }
        }
        dto.setRadioCnt(cntEditDisp);
    }

    /**
     * 総申込人数の表示用「満」をセット
     * @param listEntity
     */
    private void setSoMosikomiNinzu(List listEntity) {
        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
            UIEntryMst uiEntryMst = (UIEntryMst) ite.next();
            if (uiEntryMst.getStaffSu().compareTo(uiEntryMst.getNumberLimit()) >= 0) {
                uiEntryMst.setStaffSuDisp("満");
            }
            else {
                NumericFormatter formatter = new NumericFormatter();
                uiEntryMst.setStaffSuDisp(formatter.format(uiEntryMst.getStaffSu()).toString());
            }
        }
    }
    
    /**
     * 必須、妥当性チェック
     * @param hanyoRegistDto
     */
    private void validate(HanyoApplicationDto hanyoApplicationDto) throws ApplicationException{
    }

    /**
     * エントリー名称取得略称
     */
    public String getEntryNameRyak(String entryCd) {
        String name = "";
        if (entryCd != null) {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            List listEntry = (List) container.getComponent("entryListHanyoApplication");
            for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
                EntryDto dto = (EntryDto) ite.next();
                if (entryCd.equals(dto.getKey())) {
                    name = dto.getNameRyak();
                    break;
                }
            }
        }
        
        return name;
    }
}
