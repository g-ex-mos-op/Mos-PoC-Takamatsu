/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoregist.common.HanyoRegistCommon;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.EntryDto;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * エントリーマスタ管理の検索ロジック
 * @author itamoto
 */
public class SearchEntryLogicImpl implements SearchEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN004L03";

    /** システム日付からのX年前の年度にする*/
    private static final int BEFORE_YEAR = 10;
    

    /**
     * エントリー日付管理（UIEntryDateDao）
     */
    private UIEntryDateDao uiEntryDateDao;

    /**
     * エントリーマスタ管理（UIEntryMstDao）
     */
    private UIEntryMstDao uiEntryMstDao;

	/**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @return uiEntryDateDao を戻します。
	 */
	public UIEntryDateDao getUiEntryDateDao() {
		return uiEntryDateDao;
	}
    
	/**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @param uiEntryDateDao uiEntryDateDao を設定。
	 */
	public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
		this.uiEntryDateDao = uiEntryDateDao;
	}

	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @return uiEntryMstDao を戻します。
	 */
	public UIEntryMstDao getUiEntryMstDao() {
		return uiEntryMstDao;
	}
	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @param uiEntryMstDao uiEntryMstDao を設定。
	 */
	public void setUiEntryMstDao(UIEntryMstDao uiEntryMstDao) {
		this.uiEntryMstDao = uiEntryMstDao;
	}

	/**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public void execute(HanyoRegistDto hanyoRegistDto) throws ApplicationException {
    	// １．必須のパラメータが満たされていること。E0506（@エントリーコード）
    	// ２．パラメータが妥当であること。E0505（@エントリーコード）
        validate(hanyoRegistDto);
        
        // 表示エントリーキーリスト作成
        List listEntryKey = createEntryKeyList(hanyoRegistDto);
        if (listEntryKey == null || listEntryKey.size() == 0) {
            throw new NotExistException("登録済みの研修");
        }
    	
    	// ２．Dao【エントリーマスタ管理．エントリーマスタ管理の取得】を実行する。
    	// パラメータ： パラメータ．エントリーコード
    	// 連結文字（1.で取得した[[エントリー日付管理]].エントリー年 ＋ 1.で取得した[[エントリー日付管理]].エントリー回）
    	// ↑複数（1.で取得した[[エントリー日付管理]]の件数分作成）
        List listEntryMst = getUiEntryMstDao().getEntry(hanyoRegistDto.getEntryCd(), listEntryKey);
        
    	// ３．Dao【エントリー日付管理．エントリー日付管理の取得】を実行する。
    	// パラメータ： パラメータ．エントリーコード
    	// 連結文字（1.で取得した[[エントリー日付管理]].エントリー年 ＋ 1.で取得した[[エントリー日付管理]].エントリー回）
    	// ↑複数（1.で取得した[[エントリー日付管理]]の件数分作成）
        List listEntryDate = getUiEntryDateDao().getEntryDate(hanyoRegistDto.getEntryCd(), listEntryKey);
        
        // ４．[[エントリー日付管理]]の情報を[[エントリーマスタ管理]]にセットする。
        for (Iterator ite = listEntryMst.iterator(); ite.hasNext();) {
            UIEntryMst entityMst = (UIEntryMst) ite.next();
            for (Iterator ite2 = listEntryDate.iterator(); ite2.hasNext();) {
                UIEntryDate entityDate = (UIEntryDate) ite2.next();
                if (entityMst.getEntryCd().equals(entityDate.getEntryCd())
                        && entityMst.getEntryYear().equals(entityDate.getEntryYear())
                        && entityMst.getEntryKai().equals(entityDate.getEntryKai()))
                {
                    if (HanyoRegistCommon.DAY_KBN_KAISAI.equals(entityDate.getDayKbn())){
                        entityMst.setKaisaiFrom(entityDate.getFromDt());
                        entityMst.setKaisaiTo(entityDate.getToDt());
                    }
                    // ユーザータイプコード＝０２（オーナー）の情報をセット
                    else if (HanyoRegistCommon.DAY_KBN_HYOJI.equals(entityDate.getDayKbn())
                                && HanyoRegistCommon.USERTYPE_CD_ONER
                                    .equals(entityDate.getUsertypeCd())) 
                    {
                        entityMst.setHyojiFrom(entityDate.getFromDt());
                        entityMst.setHyojiTo(entityDate.getToDt());
                    }
                    else if (HanyoRegistCommon.DAY_KBN_TOROKU.equals(entityDate.getDayKbn())
                                && HanyoRegistCommon.USERTYPE_CD_ONER
                                    .equals(entityDate.getUsertypeCd())) 
                    {
                        entityMst.setUketukeFrom(entityDate.getFromDt());
                        entityMst.setUketukeTo(entityDate.getToDt());
                    }
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
                return key2.compareTo(key1);
            }
        };
        Collections.sort(listEntryMst, sortComparator);

        // エントリー略称をセット
        for (Iterator ite = listEntryMst.iterator(); ite.hasNext();) {
            UIEntryMst uiEntryMst = (UIEntryMst) ite.next();
            uiEntryMst.setEntryNameRyak(getEntryNameRyak(uiEntryMst.getEntryCd()));
        }
        
    	// ５．２.で取得した[[エントリーマスタ管理]] ・ ３.で取得した[[エントリー日付管理]]をリターンする。
        hanyoRegistDto.setListEntryMst(listEntryMst);
        hanyoRegistDto.setListEntryDate(listEntryDate);
    }

    /**
     * 必須、妥当性チェック
     * @param hanyoRegistDto
     */
    private void validate(HanyoRegistDto hanyoRegistDto) throws ApplicationException{
//--- チェック削除 PI-270104-007.01 選択プルダウンに「全て」が追加された為
//        /* エントリーコード */
//        String entryCd = hanyoRegistDto.getEntryCd();
//        if (isNull(entryCd)) {
//            throw new NotNullException("エントリーコード");
//        }
    }

    /**
     * エントリー名称取得略称
     */
    public String getEntryNameRyak(String entryCd) {
        String name = "";
        if (entryCd != null) {
            S2Container container = SingletonS2ContainerFactory.getContainer();
            List listEntry = (List) container.getComponent("entryList");
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

    /**
     * 表示エントリーキーリスト作成
     * @param hanyoRegistDto 汎用研修マスタ登録DTO
     * @return 表示エントリーキーリスト
     */
    private List createEntryKeyList(HanyoRegistDto hanyoRegistDto) {

        // 表示範囲From/To年決定
        String fromYear = getPreviousYear(hanyoRegistDto.getBirdDateInfo().getSysDate());
// delete start 2007/01/17 マスタライセンス４次対応        
        //String toYear = getCurrentYear(hanyoRegistDto.getBirdDateInfo().getSysDate());
// delete end
        // 表示対象エントリーコード決定
        List entryCdList = new ArrayList();
        if (hanyoRegistDto.getEntryCd().equals("ZZ")) {
            entryCdList.add("05");
            entryCdList.add("30");
        } else {
            entryCdList.add(hanyoRegistDto.getEntryCd());
        }
        // キーリスト取得
//      change start 2007/01/17 マスタライセンス４次対応        
        //List keyList = getUiEntryDateDao().getKey(entryCdList, fromYear, toYear);
        List keyList = getUiEntryDateDao().getKey(entryCdList, fromYear );
//      change end
        // キー項目連結リスト作成
        List listEntryKey = new ArrayList();
        for (Iterator ite = keyList.iterator(); ite.hasNext();) {
            UIEntryDate entity = (UIEntryDate) ite.next();
            listEntryKey.add(entity.getEntryCd() + entity.getEntryYear() + entity.getEntryKai());
        }

        return listEntryKey;
    }
    
    /**
     * システム日付の１０年前の日付を取得
     * @param date(システム日付)
     * @return result(１０年前の年度)
     */
    private String getPreviousYear(String date) {
        String result = null;
        try {
//          add start 2007/01/17 マスタライセンス４次対応
            result = DateManager.getPrevYear(DateManager.getCurrentYear(date), BEFORE_YEAR);
//          add end
        } catch (Exception e) {
            // 計算不可能な場合はnullを返却する為無処理
        }
        return result;
    }
}
