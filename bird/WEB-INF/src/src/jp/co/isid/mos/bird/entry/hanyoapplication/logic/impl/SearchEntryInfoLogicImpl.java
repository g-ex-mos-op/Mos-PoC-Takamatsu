/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.MstOwnerDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryNoticeDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryOwnerDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MstMise;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MstOwner;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOwner;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchEntryInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * エントリーマスタ管理の検索ロジック
 * @author itamoto
 */
public class SearchEntryInfoLogicImpl implements SearchEntryInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN005L03";


    /**
     * 店統合マスタ
     */
    private MstMiseDao mstMiseHanyoAppDao;
    /**
     * オーナー別エントリー状況
     */
    private UIEntryOnerStatusDao uIEntryOnerStatusDao;
    /**
     * エントリーオーナー宛先情報
     */
    private UIEntryOwnerDao uiEntryOwnerHanyoAppDao;
    /**
     * 研修エントリー状況
     */
    private UIEntryStateDao uiEntryStateHanyoAppDao;
    /**
     * オーナー情報
     */
    private MstOwnerDao mstOwnerHanyoAppDao;
// add start 2007/01/09 inazawa マスタライセンス４次対応
    /**
     * 文言情報
     */
    private UIEntryNoticeDao entryNoticeDao;
    
    /*汎用研修マスタコード 30:更新研修*/
//    private static final String KOUSIN_KENSHU = "30";
// add end    
    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public void execute(HanyoApplicationDto dto) throws ApplicationException {
    	// ０．妥当性チェック
        validate(dto);
        
        // パラメータ
        String entryCd   = dto.getSelectEntryMst().getEntryCd();
        String entryYear = dto.getSelectEntryMst().getEntryYear();
        String entryKai  = dto.getSelectEntryMst().getEntryKai();
        String companyCd = dto.getCondCompanyCd();
        String miseCd    = dto.getCondMiseCd();
        
        // オーナーコード取得
        // オーナーコード取得(本部ユーザーの場合のみ)
        if (HanyoApplicationCommon.USER_TYPE_CD_HONBU.equals(dto.getUserTypeCd())) {
            List listMiseCount = getMstMiseHanyoAppDao().getCountMise(companyCd, miseCd);
            if (listMiseCount == null || listMiseCount.size() == 0) {
                throw new NotExistException("対象店舗", "condMiseCd", 0);
            }
            String onerCd = ((MstMise) listMiseCount.get(0)).getOnerCd();
            dto.setCondOnerCd(onerCd);
        }
        
        // オーナー情報取得
        String onerCd = dto.getCondOnerCd();
        List listMstOner = getMstOwnerHanyoAppDao().getOnerInfo(companyCd, onerCd);
        MstOwner mstOwner = (MstOwner) listMstOner.get(0);
        dto.setMstOner(mstOwner);
        dto.setCondOnerNameKj(mstOwner.getOnerNameKj());
        
        // 店一覧取得
        List listMise = getMstMiseHanyoAppDao().getMise(companyCd, onerCd, dto.getSysDate());
        dto.setListMstMise(listMise);
        
        // １．オーナー別エントリー状況の検索
        List listEntryOnerStatus = getUIEntryOnerStatusDao().getEntryOner(entryCd, entryYear, entryKai, companyCd, onerCd);
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
        List listEntryOwner = getUiEntryOwnerHanyoAppDao().getEntryOner(entryCd, entryYear, entryKai, companyCd, onerCd);
        makeEntryOwner(dto, listEntryOwner, entryCd, entryYear, entryKai, companyCd, onerCd);
        dto.setListEntryOner(listEntryOwner);
        // ２．１ 結果をDTOへセット
        setEntryOwnerMosikomiSekinin(dto);
//        setEntryOwnerKekkaHokokusaki(dto);
        
        // ３．研修エントリー状況の検索
        List listEntryState = getUiEntryStateHanyoAppDao().getEntry(entryCd, entryYear, entryKai, companyCd, onerCd);
        makeEntryState(dto, listEntryState, entryCd, entryYear, entryKai);
        dto.setListEntryState(listEntryState);
        
        // ４．受講案内送付先プルダウン作成
        makeSofuPulldown(dto, companyCd, onerCd);
        
// add start 2007/01/09 inazawa マスタライセンス４次対応
        // ５．文言情報取得
        if(!entryCd.equals(HanyoApplicationCommon.ENTRY_NAME_KOUSIN)){
            String notice = getEntryNoticeDao().getEntryNotice(entryCd,entryYear,entryKai);
            dto.setNotice(notice);
        }
// add end        
    }

    /**
     * 必須、妥当性チェック
     * @param hanyoRegistDto
     */
    private void validate(HanyoApplicationDto dto) throws ApplicationException {
        String companyCd = dto.getCondCompanyCd();
        String miseCd    = dto.getCondMiseCd();
        
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード", "condCompanyCd", 0);
        }
        
        if (HanyoApplicationCommon.USER_TYPE_CD_HONBU.equals(dto.getUserTypeCd()) && isNull(miseCd)) {
            throw new NotNullException("対象店舗", "condMiseCd", 0);
        }
    }

    /**
     * 受講案内送付先プルダウン作成
     */
    private void makeSofuPulldown(HanyoApplicationDto dto, String companyCd, String onerCd) {
        
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
     * エントリーオーナー宛先情報の不足分レコード作成
     * @param listEntryOwner
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param onerCd
     */
    private void makeEntryOwner(HanyoApplicationDto dto, List listEntryOwner, String entryCd, String entryYear, String entryKai, String companyCd, String onerCd) {
        if (listEntryOwner == null) {
            listEntryOwner = new ArrayList();
        }

        // データが取得できない場合は、空のEntityを作成する
        //申込責任者
        boolean flgExist = false;
        for (Iterator ite = listEntryOwner.iterator(); ite.hasNext();) {
            UIEntryOwner entity = (UIEntryOwner) ite.next();
            if (HanyoApplicationCommon.ATESAKI_KBN_MOSIKOMI_SEKININ.equals(entity.getAtesakiKbn())) {
                flgExist = true;
                break;
            }
        }
        if (!flgExist) {
            UIEntryOwner uiEntryOwnerMosikomi = new UIEntryOwner();
            uiEntryOwnerMosikomi.setEntryCd(entryCd);
            uiEntryOwnerMosikomi.setEntryYear(entryYear);
            uiEntryOwnerMosikomi.setEntryKai(entryKai);
            uiEntryOwnerMosikomi.setCompanyCd(companyCd);
            uiEntryOwnerMosikomi.setOnerCd(onerCd);
            uiEntryOwnerMosikomi.setAtesakiKbn(HanyoApplicationCommon.ATESAKI_KBN_MOSIKOMI_SEKININ);
            uiEntryOwnerMosikomi.setOnerNameKj(dto.getCondOnerNameKj());
            uiEntryOwnerMosikomi.setName(dto.getMstOner().getOnerSubKj());
            uiEntryOwnerMosikomi.setZip(dto.getMstOner().getSeikyuPostNo());
            uiEntryOwnerMosikomi.setSoufuName(dto.getMstOner().getOnerNameKj());
            uiEntryOwnerMosikomi.setAddress1(dto.getMstOner().getSeikyuAdrs1());
            uiEntryOwnerMosikomi.setAddress2(dto.getMstOner().getSeikyuAdrs2());
            uiEntryOwnerMosikomi.setAddress3(dto.getMstOner().getSeikyuAdrs3());
            uiEntryOwnerMosikomi.setInsertFlg(true);
            listEntryOwner.add(uiEntryOwnerMosikomi);
        }
//        //結果報告先
//        flgExist = false;
//        for (Iterator ite = listEntryOwner.iterator(); ite.hasNext();) {
//            UIEntryOwner entity = (UIEntryOwner) ite.next();
//            if (HanyoApplicationCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI.equals(entity.getAtesakiKbn())) {
//                flgExist = true;
//                break;
//            }
//        }
//        if (!flgExist) {
//            UIEntryOwner uiEntryOwnerKekkaHokoku = new UIEntryOwner();
//            uiEntryOwnerKekkaHokoku.setEntryCd(entryCd);
//            uiEntryOwnerKekkaHokoku.setEntryYear(entryYear);
//            uiEntryOwnerKekkaHokoku.setEntryKai(entryKai);
//            uiEntryOwnerKekkaHokoku.setCompanyCd(companyCd);
//            uiEntryOwnerKekkaHokoku.setOnerCd(onerCd);
//            uiEntryOwnerKekkaHokoku.setAtesakiKbn(HanyoApplicationCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI);
//            uiEntryOwnerKekkaHokoku.setOnerNameKj(dto.getCondOnerNameKj());
//            uiEntryOwnerKekkaHokoku.setZip(dto.getMstOner().getSeikyuPostNo());
//            uiEntryOwnerKekkaHokoku.setAddress1(dto.getMstOner().getSeikyuAdrs1());
//            uiEntryOwnerKekkaHokoku.setAddress2(dto.getMstOner().getSeikyuAdrs2());
//            uiEntryOwnerKekkaHokoku.setAddress3(dto.getMstOner().getSeikyuAdrs3());
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
    private void makeEntryState(HanyoApplicationDto dto, List listEntryState, String entryCd, String entryYear, String entryKai) {
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
            entity.setGuideKbn(HanyoApplicationCommon.GUIDE_KBN_KEKKA);
            entity.setInsertFlg(true);
            listEntryState.add(entity);
        }
        
        //受講案内送付先情報と検索時のスタッフIDをセット
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState entity = (UIEntryState) ite.next();
            // 受講案内送付先情報
            String guideKbn = entity.getGuideKbn();
            if (HanyoApplicationCommon.GUIDE_KBN_KEKKA.equals(guideKbn)) {
                // 結果報告先
                UIEntryOwner uiEntryOwner = dto.getUiEntryOwnerMosikomiSekinin();
                entity.setGuideName(uiEntryOwner.getName());
                entity.setGuideZip(uiEntryOwner.getZip());
                entity.setGuideAdrs1(uiEntryOwner.getAddress1());
                entity.setGuideAdrs2(uiEntryOwner.getAddress2());
                entity.setGuideAdrs3(uiEntryOwner.getAddress3());
                entity.setGuideKbnName("結果報告先");
            }
            else if (HanyoApplicationCommon.GUIDE_KBN_OTHER.equals(guideKbn)) {
                // その他
                entity.setGuideNameInput(entity.getGuideName());
                entity.setGuideKbnName("その他");
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
     * エントリーオーナー宛先情報：申込責任者
     */
    public void setEntryOwnerMosikomiSekinin(HanyoApplicationDto dto) {
        UIEntryOwner uiEntryOwner = null;
        if (dto.getListEntryOner() != null) {
            for (Iterator ite = dto.getListEntryOner().iterator(); ite.hasNext();) {
                uiEntryOwner = (UIEntryOwner) ite.next();
                if (HanyoApplicationCommon.ATESAKI_KBN_MOSIKOMI_SEKININ.equals(uiEntryOwner.getAtesakiKbn())) {
                    dto.setUiEntryOwnerMosikomiSekinin(uiEntryOwner);
                    return;
                }
            }
        }
    }
//    /**
//     * エントリーオーナー宛先情報：結果報告先
//     */
//    public void setEntryOwnerKekkaHokokusaki(HanyoApplicationDto dto) {
//        UIEntryOwner uiEntryOwner = null;
//        if (dto.getListEntryOner() != null) {
//            for (Iterator ite = dto.getListEntryOner().iterator(); ite.hasNext();) {
//                uiEntryOwner = (UIEntryOwner) ite.next();
//                if (HanyoApplicationCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI.equals(uiEntryOwner.getAtesakiKbn())) {
//                    dto.setUiEntryOwnerKekkaHokokusaki(uiEntryOwner);
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
    
    public UIEntryOnerStatusDao getUIEntryOnerStatusDao() {
        return uIEntryOnerStatusDao;
    }

    public void setUIEntryOnerStatusDao(
            UIEntryOnerStatusDao uiEntryOnerStatusHanyoApplicationDao) {
        this.uIEntryOnerStatusDao = uiEntryOnerStatusHanyoApplicationDao;
    }

    public MstMiseDao getMstMiseHanyoAppDao() {
        return mstMiseHanyoAppDao;
    }

    public void setMstMiseHanyoAppDao(MstMiseDao mstMiseHanyoApplicationDao) {
        this.mstMiseHanyoAppDao = mstMiseHanyoApplicationDao;
    }

    public UIEntryOwnerDao getUiEntryOwnerHanyoAppDao() {
        return uiEntryOwnerHanyoAppDao;
    }

    public void setUiEntryOwnerHanyoAppDao(
            UIEntryOwnerDao uiEntryOwnerDaoHanyoApplicationDao) {
        this.uiEntryOwnerHanyoAppDao = uiEntryOwnerDaoHanyoApplicationDao;
    }

    public UIEntryStateDao getUiEntryStateHanyoAppDao() {
        return uiEntryStateHanyoAppDao;
    }

    public void setUiEntryStateHanyoAppDao(
            UIEntryStateDao uiEntryStateDaoHanyoApplicationDao) {
        this.uiEntryStateHanyoAppDao = uiEntryStateDaoHanyoApplicationDao;
    }

    public MstOwnerDao getMstOwnerHanyoAppDao() {
        return mstOwnerHanyoAppDao;
    }

    public void setMstOwnerHanyoAppDao(MstOwnerDao mstOwnerHanyoAppDao) {
        this.mstOwnerHanyoAppDao = mstOwnerHanyoAppDao;
    }
//  add start inazawa マスタライセンス４次対応
    public UIEntryNoticeDao getEntryNoticeDao() {
        return entryNoticeDao;
    }

    public void setEntryNoticeDao(UIEntryNoticeDao entryNoticeDao) {
        this.entryNoticeDao = entryNoticeDao;
    }
// add end    
}
