/*
 * 作成日: 2006/06/19
 */
package jp.co.isid.mos.bird.entry.basicentry.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.MstOnerDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryOnerDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.entity.MstMise;
import jp.co.isid.mos.bird.entry.basicentry.entity.MstOner;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.basicentry.logic.SearchEntryInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;

import org.seasar.framework.log.Logger;

/**
 * エントリーマスタ管理の検索ロジック
 * 指定ベーシック情報の詳細情報を取得
 * @author kusama
 */
public class SearchEntryInfoLogicImpl implements SearchEntryInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN002L03";

    private static Logger logger_ = Logger.getLogger(SearchEntryInfoLogicImpl.class);

    /**
     * 店統合マスタ
     */
    private MstMiseDao mstMiseBasicEntryDao;
    /**
     * オーナー別エントリー状況
     */
    private UIEntryOnerStatusDao uiEntryOnerStatusBasicEntryDao;
    /**
     * エントリーオーナー宛先情報
     */
    private UIEntryOnerDao uiEntryOnerBasicEntryDao;
    /**
     * 研修エントリー状況
     */
    private UIEntryStateDao uiEntryStateBasicEntryDao;
    /**
     * オーナー情報
     */
    private MstOnerDao mstOnerBasicEntryDao;
    
    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public void execute(BasicEntryDto dto) throws ApplicationException {
    	// ０．妥当性チェック
        validate(dto);
        
        // パラメータ
        String entryCd   = BasicEntryCommon.ENTRYCD_BASIC;
        String entryYear = dto.getSelectEntryMst().getEntryYear();
        String entryKai  = dto.getSelectEntryMst().getEntryKai();
        String companyCd = dto.getCondCompanyCd();
        String miseCd    = dto.getCondMiseCd();
        
        // オーナーコード取得(本部ユーザーの場合のみ)
        if (BasicEntryCommon.USER_TYPE_CD_HONBU.equals(dto.getUserTypeCd())) {
            List listMiseInfo = getMstMiseBasicEntryDao().getMiseInfo(companyCd, miseCd);
            if (listMiseInfo == null || listMiseInfo.size() == 0) {
                throw new NotExistException("対象店舗", "condMiseCd", 0);
            }
            MstMise mstMise = (MstMise) listMiseInfo.get(0);
            String onerCd = mstMise.getOnerCd();
            dto.setCondOnerCd(onerCd);
        }
        
        // オーナー情報取得
        String onerCd = dto.getCondOnerCd();
        List listOner = getMstOnerBasicEntryDao().getOnerInfo(companyCd, onerCd);
        MstOner mstOner = (MstOner) listOner.get(0);
        dto.setMstOner(mstOner);
        dto.setCondOnerNameKj(mstOner.getOnerNameKj());
        
        // 店一覧取得
        List listMise = getMstMiseBasicEntryDao().selectMiseList(companyCd, onerCd, dto.getSysDate());
        dto.setListMstMise(listMise);
        
        // １．オーナー別エントリー状況の検索
        List listEntryOnerStatus = getUiEntryOnerStatusBasicEntryDao().getEntryOner(entryCd, entryYear, entryKai, companyCd, onerCd);
        UIEntryOnerStatus uiEntryOnerStatus = null;
        if (listEntryOnerStatus != null && listEntryOnerStatus.size() != 0) {
            uiEntryOnerStatus = (UIEntryOnerStatus) listEntryOnerStatus.get(0);
        }
        else {
            // 存在しない場合は、新規レコードをセット
            uiEntryOnerStatus = new UIEntryOnerStatus();
            uiEntryOnerStatus.setEntryCd(entryCd);
            uiEntryOnerStatus.setEntryYear(entryYear);
            uiEntryOnerStatus.setEntryKai(entryKai);
            uiEntryOnerStatus.setCompanyCd(companyCd);
            uiEntryOnerStatus.setOnerCd(onerCd);
            uiEntryOnerStatus.setInsertFlg(true);
        }
        dto.setUiEntryOnerStatus(uiEntryOnerStatus);
        
        // ２．エントリーオーナー宛先情報の検索
        List listEntryOwner = getUiEntryOnerBasicEntryDao().getEntryOner(entryCd, entryYear, entryKai, companyCd, onerCd);
        makeEntryOwner(dto, listEntryOwner, entryCd, entryYear, entryKai, companyCd, onerCd);
        dto.setListEntryOner(listEntryOwner);
        // ２．１ 結果をDTOへセット
        setEntryOwnerMosikomiSekinin(dto);
//        setEntryOwnerKekkaHokokusaki(dto);
        
        // ３．研修エントリー状況の検索
        List listEntryState = getUiEntryStateBasicEntryDao().getEntry(entryCd, entryYear, entryKai, companyCd, onerCd);
        makeEntryState(dto, listEntryState, entryCd, entryYear, entryKai);
        dto.setListEntryState(listEntryState);
        
        // ４．受講案内送付先プルダウン作成
        makeSofuPulldown(dto, companyCd, onerCd);
    }

    /**
     * 必須、妥当性チェック
     * @param hanyoRegistDto
     */
    private void validate(BasicEntryDto dto) throws ApplicationException {
        String companyCd = dto.getCondCompanyCd();
        String miseCd    = dto.getCondMiseCd();
        
        if (dto.getListBasicListDataInfo() == null) {
            throw new NotSelectedException("対象のコース", "indexFlg", 0);
        }
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード", "condCompanyCd", 0);
        }
        
        if (BasicEntryCommon.USER_TYPE_CD_HONBU.equals(dto.getUserTypeCd()) && isNull(miseCd)) {
            throw new NotNullException("対象店舗", "condMiseCd", 0);
        }
    }

    /**
     * エントリーオーナー宛先情報の不足分レコード作成
     * @param listEntryOwner
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param onerCd
     */
    private void makeEntryOwner(BasicEntryDto dto, List listEntryOwner, String entryCd, String entryYear, String entryKai, String companyCd, String onerCd) {
        if (listEntryOwner == null) {
            listEntryOwner = new ArrayList();
        }

        // データが取得できない場合は、空のEntityを作成する
        //申込責任者
        boolean flgExist = false;
        for (Iterator ite = listEntryOwner.iterator(); ite.hasNext();) {
            UIEntryOner entity = (UIEntryOner) ite.next();
            if (BasicEntryCommon.ATESAKI_KBN_MOSIKOMI_SEKININ.equals(entity.getAtesakiKbn())) {
                flgExist = true;
                break;
            }
        }
        if (!flgExist) {
            UIEntryOner uiEntryOnerMosikomi = new UIEntryOner();
            uiEntryOnerMosikomi.setEntryCd(entryCd);
            uiEntryOnerMosikomi.setEntryYear(entryYear);
            uiEntryOnerMosikomi.setEntryKai(entryKai);
            uiEntryOnerMosikomi.setCompanyCd(companyCd);
            uiEntryOnerMosikomi.setOnerCd(onerCd);
            uiEntryOnerMosikomi.setAtesakiKbn(BasicEntryCommon.ATESAKI_KBN_MOSIKOMI_SEKININ);
            uiEntryOnerMosikomi.setOnerNameKj(dto.getCondOnerNameKj());
            uiEntryOnerMosikomi.setName(dto.getMstOner().getOnerSubKj());
            uiEntryOnerMosikomi.setZip(dto.getMstOner().getSeikyuPostNo());
            uiEntryOnerMosikomi.setAddress1(dto.getMstOner().getSeikyuAdrs1());
            uiEntryOnerMosikomi.setAddress2(dto.getMstOner().getSeikyuAdrs2());
            uiEntryOnerMosikomi.setAddress3(dto.getMstOner().getSeikyuAdrs3());
// add start xkhata 20060818 
            uiEntryOnerMosikomi.setSoufuName( dto.getCondOnerNameKj() );
// end 
            uiEntryOnerMosikomi.setJobType("");
            uiEntryOnerMosikomi.setInsertFlg(true);
            listEntryOwner.add(uiEntryOnerMosikomi);
        }
//        //結果報告先
//        flgExist = false;
//        for (Iterator ite = listEntryOwner.iterator(); ite.hasNext();) {
//            UIEntryOner entity = (UIEntryOner) ite.next();
//            if (BasicEntryCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI.equals(entity.getAtesakiKbn())) {
//                flgExist = true;
//                break;
//            }
//        }
//        if (!flgExist) {
//            UIEntryOner uiEntryOwnerKekkaHokoku = new UIEntryOner();
//            uiEntryOwnerKekkaHokoku.setEntryCd(entryCd);
//            uiEntryOwnerKekkaHokoku.setEntryYear(entryYear);
//            uiEntryOwnerKekkaHokoku.setEntryKai(entryKai);
//            uiEntryOwnerKekkaHokoku.setCompanyCd(companyCd);
//            uiEntryOwnerKekkaHokoku.setOnerCd(onerCd);
//            uiEntryOwnerKekkaHokoku.setAtesakiKbn(BasicEntryCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI);
//            uiEntryOwnerKekkaHokoku.setOnerNameKj(dto.getCondOnerNameKj());
//            uiEntryOwnerKekkaHokoku.setName(dto.getMstOner().getOnerNameKj());
//            uiEntryOwnerKekkaHokoku.setZip(dto.getMstOner().getSeikyuPostNo());
//            uiEntryOwnerKekkaHokoku.setAddress1(dto.getMstOner().getSeikyuAdrs1());
//            uiEntryOwnerKekkaHokoku.setAddress2(dto.getMstOner().getSeikyuAdrs2());
//            uiEntryOwnerKekkaHokoku.setAddress3(dto.getMstOner().getSeikyuAdrs3());
//            uiEntryOwnerKekkaHokoku.setJobType("");
//            uiEntryOwnerKekkaHokoku.setInsertFlg(true);
//            listEntryOwner.add(uiEntryOwnerKekkaHokoku);
//        }
    }
    
    /**
     * 研修エントリー状況が０件の場合に、新規レコードを１件追加する
     * @param listEntryOwner
     * @param entryCd
     * @param entryYear
     * @param entryKai
     */
    private void makeEntryState(BasicEntryDto dto, List listEntryState, String entryCd, String entryYear, String entryKai) {
        if (listEntryState == null) {
            listEntryState = new ArrayList();
        }
        
        //該当データがない場合は、空のEntityを1件セット
        if (listEntryState.size() == 0) {
            UIEntryState entity = new UIEntryState();
            entity.setEntryCd(entryCd);
            entity.setEntryYear(entryYear);
            entity.setEntryKai(entryKai);
            entity.setStaffId("");
            entity.setOnerCd(dto.getCondOnerCd());
            entity.setGuideKbn(BasicEntryCommon.GUIDE_KBN_KEKKA);
            entity.setOtherFlg1("1");
            entity.setOtherFlg2("1");
            entity.setInsertFlg(true);
            listEntryState.add(entity);
        }
        
        //受講案内送付先情報と検索時のスタッフIDをセット
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState entity = (UIEntryState) ite.next();
            // 受講案内送付先情報
            String guideKbn = entity.getGuideKbn();
            if (BasicEntryCommon.GUIDE_KBN_KEKKA.equals(guideKbn)) {
                // 結果報告先
                UIEntryOner uiEntryOwner = dto.getUiEntryOnerMosikomiSekinin();
                entity.setGuideName(uiEntryOwner.getName());
                entity.setGuideZip(uiEntryOwner.getZip());
                entity.setGuideAdrs1(uiEntryOwner.getAddress1());
                entity.setGuideAdrs2(uiEntryOwner.getAddress2());
                entity.setGuideAdrs3(uiEntryOwner.getAddress3());
            }
            else if (BasicEntryCommon.GUIDE_KBN_OTHER.equals(guideKbn)) {
                // その他
                entity.setGuideNameInput(entity.getGuideName());
            }
            else {
                // 店舗
                String miseCd = entity.getGuideKbn();
                for (Iterator ite2 = dto.getListMstMise().iterator(); ite2.hasNext();) {
                    MstMise mstMise = (MstMise) ite2.next();
                    if (miseCd.equals(mstMise.getMiseCd())) {
                        entity.setGuideKbnName(mstMise.getMiseNameKj());
                        break;
                    }
                }
            }
            
            // 検索時のスタッフIDを退避
            entity.setSelectedStaffId(entity.getStaffId());
        }
        dto.setListEntryState(listEntryState);
    }

    /**
     * 受講案内送付先プルダウン作成
     */
    private void makeSofuPulldown(BasicEntryDto dto, String companyCd, String onerCd) {
        
        List listPulldown = new ArrayList();
        // 結果報告先
        SelectItem itemKekka = new SelectItem("KEKKA", "結果報告先");
        listPulldown.add(itemKekka);
        // 店舗
        for (Iterator ite = dto.getListMstMise().iterator(); ite.hasNext();) {
            MstMise mstMise = (MstMise) ite.next();
            SelectItem item = new SelectItem(mstMise.getMiseCd(), mstMise.getMiseNameKj());
            listPulldown.add(item);
        }
        // その他
        SelectItem itemSonota = new SelectItem("OTHER", "その他");
        listPulldown.add(itemSonota);
        
        dto.setListSofusakiPulldown(listPulldown);
    }
    
    /**
     * エントリーオーナー宛先情報：申込責任者
     */
    public void setEntryOwnerMosikomiSekinin(BasicEntryDto dto) {
        UIEntryOner uiEntryOner = null;
        if (dto.getListEntryOner() != null) {
            for (Iterator ite = dto.getListEntryOner().iterator(); ite.hasNext();) {
                uiEntryOner = (UIEntryOner) ite.next();
                if (BasicEntryCommon.ATESAKI_KBN_MOSIKOMI_SEKININ.equals(uiEntryOner.getAtesakiKbn())) {
                    dto.setUiEntryOnerMosikomiSekinin(uiEntryOner);
                    return;
                }
            }
        }
    }
//    /**
//     * エントリーオーナー宛先情報：結果報告先
//     */
//    public void setEntryOwnerKekkaHokokusaki(BasicEntryDto dto) {
//        UIEntryOner uiEntryOner = null;
//        if (dto.getListEntryOner() != null) {
//            for (Iterator ite = dto.getListEntryOner().iterator(); ite.hasNext();) {
//                uiEntryOner = (UIEntryOner) ite.next();
//                if (BasicEntryCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI.equals(uiEntryOner.getAtesakiKbn())) {
//                    dto.setUiEntryOnerKekkaHokokusaki(uiEntryOner);
//                    return;
//                }
//            }
//        }
//    }
    
    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    public UIEntryOnerStatusDao getUiEntryOnerStatusBasicEntryDao() {
        return uiEntryOnerStatusBasicEntryDao;
    }

    public void setUiEntryOnerStatusBasicEntryDao(
            UIEntryOnerStatusDao uiEntryOnerStatusBasicEntryDao) {
        this.uiEntryOnerStatusBasicEntryDao = uiEntryOnerStatusBasicEntryDao;
    }

    public MstMiseDao getMstMiseBasicEntryDao() {
        return mstMiseBasicEntryDao;
    }

    public void setMstMiseBasicEntryDao(MstMiseDao mstMiseBasicEntryDao) {
        this.mstMiseBasicEntryDao = mstMiseBasicEntryDao;
    }

    public UIEntryOnerDao getUiEntryOnerBasicEntryDao() {
        return uiEntryOnerBasicEntryDao;
    }

    public void setUiEntryOnerBasicEntryDao(
            UIEntryOnerDao uiEntryOwnerDaoBasicEntryDao) {
        this.uiEntryOnerBasicEntryDao = uiEntryOwnerDaoBasicEntryDao;
    }

    public UIEntryStateDao getUiEntryStateBasicEntryDao() {
        return uiEntryStateBasicEntryDao;
    }

    public void setUiEntryStateBasicEntryDao(
            UIEntryStateDao uiEntryStateDaoBasicEntryDao) {
        this.uiEntryStateBasicEntryDao = uiEntryStateDaoBasicEntryDao;
    }

    public MstOnerDao getMstOnerBasicEntryDao() {
        return mstOnerBasicEntryDao;
    }

    public void setMstOnerBasicEntryDao(MstOnerDao mstOnerBasicEntryDao) {
        this.mstOnerBasicEntryDao = mstOnerBasicEntryDao;
    }
}
