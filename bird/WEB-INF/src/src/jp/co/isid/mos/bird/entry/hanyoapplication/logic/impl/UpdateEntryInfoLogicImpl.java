/*
 * 作成日: 2006/06/05
 * 修正美: 2007/05/23 オーナーコード単位でスタッフを全件削除するように変更
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.MlCourseKanriDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.TrnEntryResultDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.TrnLicenseHojiStaffDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.TrnMasterResultDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryOwnerDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dao.UIStaffDao;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MlCourseKanri;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.TrnEntryResult;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.TrnLicenseHojiStaff;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOwner;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIStaff;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.UpdateEntryInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;


/**
 * 研修エントリー情報の更新
 * @author xnkusama
 */
public class UpdateEntryInfoLogicImpl implements UpdateEntryInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN005L02";
    // 修了（予定）コース状況 のデフォルト値
    private static final String COURSE_STATUS_DEFAULT = "2";


    /* DAO */
    //店統合マスタ
    private MstMiseDao mstMiseHanyoAppDao;
    //オーナー別エントリー状況
    private UIEntryOnerStatusDao uIEntryOnerStatusDao;
    //エントリーオーナー宛先情報
    private UIEntryOwnerDao uiEntryOwnerHanyoAppDao;
    //研修エントリー状況
    private UIEntryStateDao uiEntryStateHanyoAppDao;
    //研修結果状況
    private TrnEntryResultDao trnEntryResultHanyoApplicationDao;
    // マスターライセンス結果状況
    private TrnMasterResultDao trnMasterResultHanyoAppDao;
    // ライセンス保持者管理
    private TrnLicenseHojiStaffDao trnLicenseHojiStaffAppDao;
    //スタッフマスタ
    private UIStaffDao uiStaffBasicEntryDao;
    //マスターライセンス研修コース
    private MlCourseKanriDao mlCourseKanriHanyoAppDao;
    
	/**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public void execute(HanyoApplicationDto dto) throws ApplicationException {
    	// ０．妥当性チェック
        validate(dto);
        
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        // 入力情報
        List listEntryState = dto.getListEntryState();
        
        // 入力情報の有無をチェック
        String entryFlg = HanyoApplicationCommon.ENTRY_FLG_FUSANKA;
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                entryFlg = HanyoApplicationCommon.ENTRY_FLG_SANKA;
                break;
            }
        }
        
        // １．オーナー別エントリー状況の更新
        doEntryOnerStatus(dto, currentTimestamp, entryFlg);
        
        // ２．エントリーオーナー宛先情報の更新
        doEntryOner(dto, currentTimestamp, entryFlg);

        // ３．研修エントリー状況の更新
        doEntryState(dto, currentTimestamp);
        
        // マスターライセンス研修コース情報の取得
        List listMlcourseKanri 
            = getMlCourseKanriHanyoAppDao().getMlCourseInfo(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai());
        //---2006/07/10現在 エントリーコード、年、回で1件のみ取得できる前提で開発
        MlCourseKanri mlCourseKanri = (MlCourseKanri) listMlcourseKanri.get(0);
        
        // ４．研修結果状況の更新
        doTrnEntryResult(dto, currentTimestamp, mlCourseKanri);
        
        // BT24MLKJ→BT32MLKR対応により削除
        // ５．マスターライセンス結果状況の更新（出張研修のみ）
//        if (HanyoApplicationCommon.ENTRYCD_SHUTTYO.equals(dto.getEntryCd())) {
//            doTrnMasterResult(dto, currentTimestamp, mlCourseKanri);
//        }
        
        // ６．ライセンス保持者管理の更新（更新研修のみ）
        if (HanyoApplicationCommon.ENTRYCD_KOUSIN.equals(dto.getEntryCd())) {
            doTrnLicenseHojiStaff(dto, currentTimestamp, mlCourseKanri);
        }
    }

    /**
     * エントリーオーナー宛先情報の更新
     */
    private void doEntryOner(HanyoApplicationDto dto, Timestamp currentTimestamp, String entryFlg) {
        UIEntryOwner uiEntryOwnerMosikomiSekinin = dto.getUiEntryOwnerMosikomiSekinin();
        
        if (entryFlg.equals(HanyoApplicationCommon.ENTRY_FLG_FUSANKA)) {
            if (!uiEntryOwnerMosikomiSekinin.isInsertFlg()) {
                getUiEntryOwnerHanyoAppDao().deleteEntryOner(uiEntryOwnerMosikomiSekinin);
            }
        }
        else {
            //申込責任者
            uiEntryOwnerMosikomiSekinin.setLastUser(dto.getBirdUserInfo().getUserID());
            uiEntryOwnerMosikomiSekinin.setLastPgm(LOGIC_ID.substring(0, 6));
            if (!uiEntryOwnerMosikomiSekinin.isInsertFlg()) {
                getUiEntryOwnerHanyoAppDao().updateEntryOner(uiEntryOwnerMosikomiSekinin);
            }
            else {
                //新規
                uiEntryOwnerMosikomiSekinin.setFirstUser(dto.getBirdUserInfo().getUserID());
                uiEntryOwnerMosikomiSekinin.setFirstPgm(LOGIC_ID.substring(0, 6));
                uiEntryOwnerMosikomiSekinin.setFirstTmsp(currentTimestamp);
                uiEntryOwnerMosikomiSekinin.setTel("");
                getUiEntryOwnerHanyoAppDao().insertEntryOner(uiEntryOwnerMosikomiSekinin);
                uiEntryOwnerMosikomiSekinin.setInsertFlg(false);
            }
        }
    }
    
    /**
     * オーナー別エントリー状況の更新
     */
    private void doEntryOnerStatus(HanyoApplicationDto dto, Timestamp currentTimestamp, String entryFlg) {
        UIEntryOnerStatus uiEntryOnerStatus = dto.getUiEntryOnerStatus();
        
        if (entryFlg.equals(HanyoApplicationCommon.ENTRY_FLG_FUSANKA)) {
            if (!uiEntryOnerStatus.isInsertFlg()) {
                getUIEntryOnerStatusDao().deleteEntryOner(uiEntryOnerStatus);
            }
        }
        else {
            if (uiEntryOnerStatus.isInsertFlg()) {
                //新規
                uiEntryOnerStatus.setEntryFlg(entryFlg);
                uiEntryOnerStatus.setFirstUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerStatus.setFirstPgm(LOGIC_ID.substring(0, 6));
                uiEntryOnerStatus.setFirstTmsp(currentTimestamp);
                uiEntryOnerStatus.setLastUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerStatus.setLastPgm(LOGIC_ID.substring(0, 6));
                uiEntryOnerStatus.setLastTmsp(currentTimestamp);
                getUIEntryOnerStatusDao().insertEntryOner(uiEntryOnerStatus);
                uiEntryOnerStatus.setInsertFlg(false);
            }
        }
    }
    
    /**
     * 研修エントリー状況の登録
     */
    private void doEntryState(HanyoApplicationDto dto, Timestamp currentTimestamp) {
        List listEntryState = dto.getListEntryState();
        
//        // 削除データを処理
//        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
//            UIEntryState uiEntryState = (UIEntryState) ite.next();
//            if ("".equals(uiEntryState.getStaffId())) {
//                getUiEntryStateHanyoAppDao().deleteEntry(uiEntryState);
//            }
//        }

//--- 2007/05/23 オーナーコード単位でスタッフを全件削除するように変更
        // 登録済みのデータを全件削除
//        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
//            UIEntryState uiEntryState = (UIEntryState) ite.next();
//            getUiEntryStateHanyoAppDao().deleteEntry(uiEntryState);
//        }
        getUiEntryStateHanyoAppDao().deleteEntry(dto.getEntryCd(), 
                                                 dto.getEntryYear(), 
                                                 dto.getEntryKai(), 
                                                 dto.getCondOnerCd());
        
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            uiEntryState.setLastPgm(LOGIC_ID.substring(0, 6));
            uiEntryState.setLastUser(dto.getBirdUserInfo().getUserID());
            if (HanyoApplicationCommon.GUIDE_KBN_OTHER.equals(uiEntryState.getGuideKbn())) {
                uiEntryState.setGuideName(uiEntryState.getGuideNameInput());
            }
//            if (!uiEntryState.isInsertFlg()) {
//                if (!isNull(uiEntryState.getStaffId())) {
//                    getUiEntryStateHanyoAppDao().updateEntry(uiEntryState);
//                }
//                else {
//                    getUiEntryStateHanyoAppDao().deleteEntry(uiEntryState);
//                }
//            }
//            else {
//                //新規
//                if (!isNull(uiEntryState.getStaffId())) {
//                    uiEntryState.setFirstUser(dto.getBirdUserInfo().getUserID());
//                    uiEntryState.setFirstPgm(LOGIC_ID.substring(0, 8));
//                    uiEntryState.setFirstTmsp(currentTimestamp);
//                    uiEntryState.setLastUser(dto.getBirdUserInfo().getUserID());
//                    uiEntryState.setLastPgm(LOGIC_ID.substring(0, 8));
//                    uiEntryState.setLastTmsp(currentTimestamp);
//                    uiEntryState.setOtherFlg1("");
//                    uiEntryState.setOtherFlg2("");
//                    uiEntryState.setOtherFlg3("");
//                    uiEntryState.setOtherFlg4("");
//                    uiEntryState.setOtherFlg5("");
//                    uiEntryState.setBossComment("");
//                    uiEntryState.setBossGroup("");
//                    uiEntryState.setBossJobType("");
//                    uiEntryState.setBossName("");
//                    uiEntryState.setCourseCd("");
//                    getUiEntryStateHanyoAppDao().insertEntry(uiEntryState);
//                }
//            }
            if (!isNull(uiEntryState.getStaffId())) {
                if (uiEntryState.isInsertFlg()) {
                    uiEntryState.setFirstUser(dto.getBirdUserInfo().getUserID());
                    uiEntryState.setFirstPgm(LOGIC_ID.substring(0, 6));
                    uiEntryState.setFirstTmsp(currentTimestamp);
                    uiEntryState.setOtherFlg1("");
                    uiEntryState.setOtherFlg2("");
                    uiEntryState.setOtherFlg3("");
                    uiEntryState.setOtherFlg4("");
                    uiEntryState.setOtherFlg5("");
                    uiEntryState.setBossComment("");
                    uiEntryState.setBossGroup("");
                    uiEntryState.setBossJobType("");
                    uiEntryState.setBossName("");
                    uiEntryState.setCourseCd("");
                }
                uiEntryState.setLastUser(dto.getBirdUserInfo().getUserID());
                uiEntryState.setLastPgm(LOGIC_ID.substring(0, 6));
                uiEntryState.setLastTmsp(currentTimestamp);
                getUiEntryStateHanyoAppDao().insertEntry(uiEntryState);
                //スタッフマスタ更新
                updateMstStaff(uiEntryState);
            }
        }
    }
    
    /**
     * 加盟店スタッフマスタ更新
     * @param uiEntryState
     */
    private void updateMstStaff(UIEntryState uiEntryState) {
        //スタッフマスタを検索
        UIStaff uiStaff = getUiStaffBasicEntryDao().getStaffInfo(uiEntryState.getStaffId());
        
        uiStaff.setJob(uiEntryState.getJob());
        
        getUiStaffBasicEntryDao().updateStaff(uiStaff);
    }
    
    /**
     * 研修結果状況の更新
     * @param dto
     * @param currentTimestamp
     */
    private void doTrnEntryResult(HanyoApplicationDto dto, Timestamp currentTimestamp, MlCourseKanri mlCourseKanri) {
        List listEntryState = dto.getListEntryState();
        // 登録済みのデータを全件削除
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                getTrnEntryResultHanyoApplicationDao()
                        .deleteTrnEntryResult(dto.getEntryCd(),
                                              dto.getEntryYear(),
                                              dto.getEntryKai(),
                                              uiEntryState.getStaffId());
            }
            if (!isNull(uiEntryState.getSelectedStaffId())) {
                getTrnEntryResultHanyoApplicationDao()
                        .deleteTrnEntryResult(dto.getEntryCd(),
                                              dto.getEntryYear(),
                                              dto.getEntryKai(),
                                              uiEntryState.getSelectedStaffId());
            }
        }
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                int count = getTrnEntryResultHanyoApplicationDao()
                                    .getCount(dto.getEntryCd(),
                                              dto.getEntryYear(),
                                              dto.getEntryKai(),
                                              uiEntryState.getStaffId());
                if (count == 0) {
                    TrnEntryResult trnEntryResult = new TrnEntryResult();
                    trnEntryResult.setEntryCd(dto.getEntryCd());
                    trnEntryResult.setEntryYear(dto.getEntryYear());
                    trnEntryResult.setEntryKai(dto.getEntryKai());
                    trnEntryResult.setStaffId(uiEntryState.getStaffId());
                    trnEntryResult.setCourseStatus(COURSE_STATUS_DEFAULT);
                    trnEntryResult.setCompleCourseCd(mlCourseKanri.getCourseCd());
                    trnEntryResult.setCompleCourseName(mlCourseKanri.getCourseName());
                    trnEntryResult.setFirstUser(dto.getBirdUserInfo().getUserID());
                    trnEntryResult.setFirstPgm(LOGIC_ID.substring(0, 6));
                    trnEntryResult.setFirstTmsp(currentTimestamp);
                    trnEntryResult.setLastUser(dto.getBirdUserInfo().getUserID());
                    trnEntryResult.setLastPgm(LOGIC_ID.substring(0, 6));
                    trnEntryResult.setLastTmsp(currentTimestamp);
                    
                    getTrnEntryResultHanyoApplicationDao().insertEntry(trnEntryResult);
                }
            }
        }
    }

    /**
//     * マスターライセンス結果状況の更新（出張研修のみ）
//     * @param dto
//     * @param currentTimestamp
//     */
//    private void doTrnMasterResult(HanyoApplicationDto dto, Timestamp currentTimestamp, MlCourseKanri mlCourseKanri) {
//        for (Iterator ite = dto.getListEntryState().iterator(); ite.hasNext();) {
//            UIEntryState uiEntryState = (UIEntryState) ite.next();
//            
//            if (!isNull(uiEntryState.getStaffId())) {
//                if (getTrnMasterResultHanyoAppDao().getCount(uiEntryState.getStaffId()) == 0) {
//                    TrnMasterResult trnMasterResult = new TrnMasterResult();
//                    trnMasterResult.setStaffId(uiEntryState.getStaffId());
//                    trnMasterResult.setCourseStatus(COURSE_STATUS_DEFAULT);
//                    trnMasterResult.setCompleCourseCd(mlCourseKanri.getCourseCd());
//                    trnMasterResult.setCompleCourseName(mlCourseKanri.getCourseName());
//                    trnMasterResult.setFirstUser(dto.getBirdUserInfo().getUserID());
//                    trnMasterResult.setFirstPgm(LOGIC_ID.substring(0, 6));
//                    trnMasterResult.setFirstTmsp(currentTimestamp);
//                    trnMasterResult.setLastUser(dto.getBirdUserInfo().getUserID());
//                    trnMasterResult.setLastPgm(LOGIC_ID.substring(0, 6));
//                    trnMasterResult.setLastTmsp(currentTimestamp);
//                    
//                    getTrnMasterResultHanyoAppDao().insertEntry(trnMasterResult);
//                }
//                // 検索時のスタッフIDと入力されたスタッフIDが違う場合は、検索時のスタッフIDの情報を削除
//                if (!uiEntryState.getStaffId().equals(uiEntryState.getSelectedStaffId())) {
//                    getTrnMasterResultHanyoAppDao().updateEntry(uiEntryState.getSelectedStaffId());
//                }
//            }
//        }
//    }
    
    /**
     * ライセンス保持者管理の更新（更新研修のみ）
     * @param dto
     * @param currentTimestamp
     */
    private void doTrnLicenseHojiStaff(HanyoApplicationDto dto,
                                         Timestamp currentTimestamp, 
                                         MlCourseKanri mlCourseKanri) 
    {
        //今回エントリーの年度
//        String nendoThisEntry = dto.getEntryYear();
        //登録済ライセンス保持者管理の
//        String nendoDT = "";
        int targetCol = 0;

        //削除データの処理
        for (Iterator ite = dto.getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getSelectedStaffId()) && !uiEntryState.getSelectedStaffId().equals(uiEntryState.getStaffId())) {
                //ライセンス保持者管理を検索
                List listLicenseHojiStaff = getTrnLicenseHojiStaffAppDao().getLicenseHojiStaff(uiEntryState.getSelectedStaffId());
                if (listLicenseHojiStaff != null && listLicenseHojiStaff.size() > 0) {
                    // 研修結果状況履歴を検索
                    List listTrnEntryResult 
                            = getTrnEntryResultHanyoApplicationDao().getNewInfo(dto.getEntryCd(), dto.getEntryYear(), uiEntryState.getSelectedStaffId());
                    TrnLicenseHojiStaff trnLicenseHojiStaff = (TrnLicenseHojiStaff) listLicenseHojiStaff.get(0);
//                    try {
//                        nendoDT = DateManager.getPrevYear(trnLicenseHojiStaff.getLicenseValidDt().substring(0, 4), 1);
//                    }
//                    catch (Exception ex) {
//                        throw new FtlSystemException("処理", null, ex);
//                    }
//                    targetCol = nendoThisEntry.compareTo(nendoDT);
                    targetCol = 1;
                    if (listTrnEntryResult != null && listTrnEntryResult.size() > 0) {
                        // 取得できた場合は、取得した値でライセンス保持者管理を更新
                        TrnEntryResult trnEntryResult = (TrnEntryResult) listTrnEntryResult .get(0);
                        setLicenseHojiStaffInfo(trnLicenseHojiStaff, targetCol, 
                                                trnEntryResult.getEntryYear(), trnEntryResult.getEntryKai(), 
                                                trnEntryResult.getCompleCourseCd(), trnEntryResult.getCompleCourseName(),
                                                trnEntryResult.getCourseStatus(), currentTimestamp, dto.getBirdUserInfo().getUserID());
                        //更新
                        getTrnLicenseHojiStaffAppDao().updateEntry(trnLicenseHojiStaff);
                    }
                    else {
                        // 取得できない場合は、クリア
                        setLicenseHojiStaffInfo(trnLicenseHojiStaff, targetCol, "", "", "", "", "", currentTimestamp, dto.getBirdUserInfo().getUserID());
                        getTrnLicenseHojiStaffAppDao().updateEntry(trnLicenseHojiStaff);
                    }
                }
            }
        }
        //登録データの処理
        for (Iterator ite = dto.getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                List listLicenseHojiStaff = getTrnLicenseHojiStaffAppDao().getLicenseHojiStaff(uiEntryState.getStaffId());
                
                if ( listLicenseHojiStaff == null || listLicenseHojiStaff.size() == 0) {
                    // ライセンス保持者管理が存在しない場合は、何もしない
                }
                else {
                    // 存在し交信研修状況が「修了」ではない場合、ライセンス保持者管理を更新
                    TrnLicenseHojiStaff trnLicenseHojiStaff = (TrnLicenseHojiStaff) listLicenseHojiStaff.get(0);
                    // ライセンス有効期限日が空の人は更新処理を行わない（＝ライセンス保持者管理が存在しない人と同じ）
                    if (isNull(trnLicenseHojiStaff.getLicenseValidDt())) {
                        continue;
                    }
//                    try {
//                        nendoDT = DateManager.getPrevYear(trnLicenseHojiStaff.getLicenseValidDt().substring(0, 4), 1);
//                    }
//                    catch (Exception ex) {
//                        throw new FtlSystemException("処理", null, ex);
//                    }
//                    targetCol = nendoThisEntry.compareTo(nendoDT);
                    targetCol = 1;
//                    boolean needUpdate = false;
//                    switch (targetCol) {
//                    case 1:
//                        if (!"1".equals(trnLicenseHojiStaff.getRenew1Status())) {
//                            needUpdate = true;
//                            break;
//                        }
//                    case 2:
//                        if (!"1".equals(trnLicenseHojiStaff.getRenew2Status())) {
//                            needUpdate = true;
//                            break;
//                        }
//                    case 3:
//                        if (!"1".equals(trnLicenseHojiStaff.getRenew3Status())) {
//                            needUpdate = true;
//                            break;
//                        }
//                    case 4:
//                        if (!"1".equals(trnLicenseHojiStaff.getRenew4Status())) {
//                            needUpdate = true;
//                            break;
//                        }
//                    case 5:
//                        if (!"1".equals(trnLicenseHojiStaff.getRenew5Status())) {
//                            needUpdate = true;
//                            break;
//                        }
//                    }
//                    if (needUpdate) {
                        setLicenseHojiStaffInfo(trnLicenseHojiStaff, targetCol, 
                                                uiEntryState.getEntryYear(), uiEntryState.getEntryKai(),
                                                mlCourseKanri.getCourseCd(), mlCourseKanri.getCourseName(), 
                                                "2", currentTimestamp, dto.getBirdUserInfo().getUserID());
                        getTrnLicenseHojiStaffAppDao().updateEntry(trnLicenseHojiStaff);
//                    }
                }
            }
        }
    }

    /**
     * ライセンス保持者管理の情報セット
     * @param entity
     * @param nendoSa
     * @param nendo
     * @param kai
     * @param courseCd
     * @param courseName
     * @param status
     */
    private void setLicenseHojiStaffInfo(TrnLicenseHojiStaff entity, int nendoSa,
                                           String nendo, String kai, 
                                           String courseCd, String courseName, 
                                           String status, Timestamp currentTimestamp, String userId) 
    {
        switch (nendoSa) {
        case 1:
            entity.setRenew1EntYear(nendo);
            entity.setRenew1EntKai(kai);
            entity.setRenew1Course(courseCd);
            entity.setRenew1CourseName(courseName);
            entity.setRenew1Status(status);
            break;
        case 2:
            entity.setRenew2EntYear(nendo);
            entity.setRenew2EntKai(kai);
            entity.setRenew2Course(courseCd);
            entity.setRenew2CourseName(courseName);
            entity.setRenew2Status(status);
            break;
        case 3:
            entity.setRenew3EntYear(nendo);
            entity.setRenew3EntKai(kai);
            entity.setRenew3Course(courseCd);
            entity.setRenew3CourseName(courseName);
            entity.setRenew3Status(status);
            break;
        case 4:
            entity.setRenew4EntYear(nendo);
            entity.setRenew4EntKai(kai);
            entity.setRenew4Course(courseCd);
            entity.setRenew4CourseName(courseName);
            entity.setRenew4Status(status);
            break;
        case 5:
            entity.setRenew5EntYear(nendo);
            entity.setRenew5EntKai(kai);
            entity.setRenew5Course(courseCd);
            entity.setRenew5CourseName(courseName);
            entity.setRenew5Status(status);
            break;
        default:
            break;
        }
        entity.setLastUser(userId);
        entity.setLastPgm(LOGIC_ID.substring(0, 6));
        entity.setLastTmsp(currentTimestamp);
    }
    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    /**
     * 必須、妥当性チェック
     * @param hanyoRegistDto
     */
    private void validate(HanyoApplicationDto hanyoApplicationDto) throws ApplicationException{
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

    public TrnEntryResultDao getTrnEntryResultHanyoApplicationDao() {
        return trnEntryResultHanyoApplicationDao;
    }

    public void setTrnEntryResultHanyoApplicationDao(
            TrnEntryResultDao trnEntryResultHanyoApplicationDao) {
        this.trnEntryResultHanyoApplicationDao = trnEntryResultHanyoApplicationDao;
    }

    public TrnLicenseHojiStaffDao getTrnLicenseHojiStaffAppDao() {
        return trnLicenseHojiStaffAppDao;
    }

    public void setTrnLicenseHojiStaffAppDao(
            TrnLicenseHojiStaffDao trnLicenseHojiStaffAppDao) {
        this.trnLicenseHojiStaffAppDao = trnLicenseHojiStaffAppDao;
    }

    public TrnMasterResultDao getTrnMasterResultHanyoAppDao() {
        return trnMasterResultHanyoAppDao;
    }

    public void setTrnMasterResultHanyoAppDao(
            TrnMasterResultDao trnMasterResultHanyoAppDao) {
        this.trnMasterResultHanyoAppDao = trnMasterResultHanyoAppDao;
    }

    public UIStaffDao getUiStaffBasicEntryDao() {
        return uiStaffBasicEntryDao;
    }

    public void setUiStaffBasicEntryDao(UIStaffDao uiStaffBasicEntryDao) {
        this.uiStaffBasicEntryDao = uiStaffBasicEntryDao;
    }

    public MlCourseKanriDao getMlCourseKanriHanyoAppDao() {
        return mlCourseKanriHanyoAppDao;
    }

    public void setMlCourseKanriHanyoAppDao(
            MlCourseKanriDao mlCourseKanriHanyoAppDao) {
        this.mlCourseKanriHanyoAppDao = mlCourseKanriHanyoAppDao;
    }

}
