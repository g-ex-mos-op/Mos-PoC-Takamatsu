/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.hanyoapplication.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.MstOwnerDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIStaffDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchStaffLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * スタッフ一覧の検索ロジック
 * @author kusama
 */
public class SearchStaffLogicImpl implements SearchStaffLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN005L04";

    /**
     * 店統合マスタ（MstMiseDao）
     */
    private MstMiseDao mstMiseHanyoAppDao;

    /**
     * オーナー統合マスタ（UIEntryMstDao）
     */
    private MstOwnerDao mstOwnerHanyoAppDao;

	/**
	 * 加盟店スタッフマスタ（UIEntryDateDao）の設定
	 */
	private UIStaffDao uiStaffHanyoAppDao;
    
	/**
	 * エントリーマスタ管理（UIEntryDateDao）の設定
	 */
	private UIEntryMstDao uiEntryMstHanyoAppDao;

	/**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public Map execute(HanyoApplicationDto hanyoApplicationDto) throws ApplicationException {
        String companyCd = hanyoApplicationDto.getCondCompanyCd();
        UIEntryMst entity = hanyoApplicationDto.getSelectEntryMst();
        
        
    	// ０．パラメータの必須チェック
        validate(companyCd);
        
        // ２．対象オーナーコードの存在チェック
        checkExistOner(companyCd, hanyoApplicationDto.getCondOnerCd());
        
        // ３．加盟店スタッフ一覧を取得する
        List listStaff = getStaffList(companyCd, hanyoApplicationDto.getCondOnerCd(), entity);
        
        // ４．リターン
        Map map = new HashMap();
        map.put("listStaff", listStaff);
        map.put("onerCd", hanyoApplicationDto.getCondOnerCd());
        return map;
    }

    /**
     * オーナーコードの存在チェック
     * @param companyCd
     * @param onerCd
     */
    private void checkExistOner(String companyCd, String onerCd) {
        List listOner = getMstOwnerHanyoAppDao().getOnerInfo(companyCd, onerCd);
        if (listOner == null || listOner.size() == 0) {
            throw new NotExistException("オーナーコード", "condMiseCd", 0);
        }
    }
    
    /**
     * 加盟店スタッフ一覧の取得
     * @param companyCd
     * @param onerCd
     * @param mstEntry
     * @return
     */
    private List getStaffList(String companyCd, String onerCd, UIEntryMst mstEntry) {
        List listStaff = getUiStaffHanyoAppDao().getStaff(mstEntry.getEntryCd(),
                                                          mstEntry.getEntryYear(),
                                                          mstEntry.getEntryKai(),
                                                          companyCd,
                                                          onerCd);
        return listStaff;
    }
    /**
     * 必須、妥当性チェック
     * @param companyCd 会社コード
     */
    private void validate(String companyCd) throws ApplicationException{
        if (isNull(companyCd)) {
            throw new NotNullException("会社", "condCompanyCd", 0);
        }
    }

    public MstMiseDao getMstMiseHanyoAppDao() {
        return mstMiseHanyoAppDao;
    }

    public void setMstMiseHanyoAppDao(MstMiseDao mstMiseHanyoAppDao) {
        this.mstMiseHanyoAppDao = mstMiseHanyoAppDao;
    }

    public MstOwnerDao getMstOwnerHanyoAppDao() {
        return mstOwnerHanyoAppDao;
    }

    public void setMstOwnerHanyoAppDao(MstOwnerDao mstOwnerHanyoAppDao) {
        this.mstOwnerHanyoAppDao = mstOwnerHanyoAppDao;
    }

    public UIEntryMstDao getUiEntryMstHanyoAppDao() {
        return uiEntryMstHanyoAppDao;
    }

    public void setUiEntryMstHanyoAppDao(UIEntryMstDao uiEntryMstHanyoAppDao) {
        this.uiEntryMstHanyoAppDao = uiEntryMstHanyoAppDao;
    }

    public UIStaffDao getUiStaffHanyoAppDao() {
        return uiStaffHanyoAppDao;
    }

    public void setUiStaffHanyoAppDao(UIStaffDao uiStaffHanyoAppDao) {
        this.uiStaffHanyoAppDao = uiStaffHanyoAppDao;
    }

    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
}