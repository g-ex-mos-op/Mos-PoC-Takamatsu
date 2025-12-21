/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.basicentry.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.basicentry.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.MstOnerDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIStaffDao;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.entity.MstMise;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicentry.logic.SearchStaffLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

import org.seasar.framework.log.Logger;

/**
 * スタッフ一覧の検索ロジック
 * @author kusama
 */
public class SearchStaffLogicImpl implements SearchStaffLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN002L04";

    private static Logger logger_ = Logger.getLogger(SearchStaffLogicImpl.class);
    
    /**
     * 店統合マスタ（MstMiseDao）
     */
    private MstMiseDao mstMiseBasicEntryDao;

    /**
     * オーナー統合マスタ（UIEntryMstDao）
     */
    private MstOnerDao mstOnerBasicEntryDao;

	/**
	 * 加盟店スタッフマスタ（UIEntryDateDao）の設定
	 */
	private UIStaffDao uiStaffBasicEntryDao;
    
	/**
	 * エントリーマスタ管理（UIEntryDateDao）の設定
	 */
	private UIEntryMstDao uiEntryMstBasicEntryDao;

	/**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public Map execute(BasicEntryDto basicEntryDto) throws ApplicationException {
        String companyCd = basicEntryDto.getCondCompanyCd();
        UIEntryMst entity = basicEntryDto.getSelectEntryMst();
        
        
    	// ０．パラメータの必須チェック
        validate(companyCd);
        
        // ２．対象オーナーコードの存在チェック
        checkExistOner(companyCd, basicEntryDto.getCondOnerCd());
        
        // ３．加盟店スタッフ一覧を取得する
        List listStaff = getStaffList(companyCd, basicEntryDto.getCondOnerCd(), entity);
        
        // ４．リターン
        Map map = new HashMap();
        map.put("listStaff", listStaff);
        map.put("onerCd", basicEntryDto.getCondOnerCd());
        return map;
    }

    /**
     * 対象店舗コードの存在チェック
     * @param companyCd
     * @param miseCd
     * @return
     */
    private MstMise checkExistMise(String companyCd, String miseCd) {
        List listMstMise = getMstMiseBasicEntryDao().getMiseInfo(companyCd, miseCd);
        if (listMstMise == null || listMstMise.size() == 0) {
            throw new NotExistException("店コード", "condMiseCd", 0);
        }
        return (MstMise) listMstMise.get(0);
    }
    
    /**
     * オーナーコードの存在チェック
     * @param companyCd
     * @param onerCd
     */
    private void checkExistOner(String companyCd, String onerCd) {
        List listOner = getMstOnerBasicEntryDao().getOnerInfo(companyCd, onerCd);
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
        List listStaff = getUiStaffBasicEntryDao().getStaff(mstEntry.getEntryCd(),
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

    public MstMiseDao getMstMiseBasicEntryDao() {
        return mstMiseBasicEntryDao;
    }

    public void setMstMiseBasicEntryDao(MstMiseDao mstMiseBasicEntryDao) {
        this.mstMiseBasicEntryDao = mstMiseBasicEntryDao;
    }

    public MstOnerDao getMstOnerBasicEntryDao() {
        return mstOnerBasicEntryDao;
    }

    public void setMstOnerBasicEntryDao(MstOnerDao mstOwnerBasicEntryDao) {
        this.mstOnerBasicEntryDao = mstOwnerBasicEntryDao;
    }

    public UIEntryMstDao getUiEntryMstBasicEntryDao() {
        return uiEntryMstBasicEntryDao;
    }

    public void setUiEntryMstBasicEntryDao(UIEntryMstDao uiEntryMstBasicEntryDao) {
        this.uiEntryMstBasicEntryDao = uiEntryMstBasicEntryDao;
    }

    public UIStaffDao getUiStaffBasicEntryDao() {
        return uiStaffBasicEntryDao;
    }

    public void setUiStaffBasicEntryDao(UIStaffDao uiStaffBasicEntryDao) {
        this.uiStaffBasicEntryDao = uiStaffBasicEntryDao;
    }

    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }

}