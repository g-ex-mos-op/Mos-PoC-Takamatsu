/*
 * 作成日: 2006/06/05
 */
package jp.co.isid.mos.bird.entry.basicentry.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.dao.MlCourseKanriDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.TrnEntryResultDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.TrnMasterResultDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryOnerDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.basicentry.dao.UIStaffDao;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.entity.MlCourseKanri;
import jp.co.isid.mos.bird.entry.basicentry.entity.TrnEntryResult;
import jp.co.isid.mos.bird.entry.basicentry.entity.TrnMasterResult;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIStaff;
import jp.co.isid.mos.bird.entry.basicentry.logic.UpdateEntryInfoLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.log.Logger;

/**
 * 研修エントリー情報の更新
 * @author xnkusama
 */
public class UpdateEntryInfoLogicImpl implements UpdateEntryInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN002L02";

    private static Logger logger_ = Logger.getLogger(UpdateEntryInfoLogicImpl.class);
    // 修了（予定）コース状況 のデフォルト値
    private static final String COURSE_STATUS_DEFAULT = "2";
    
    /* DAO */
    //店統合マスタ
    private MstMiseDao mstMiseBasicEntryDao;
    //オーナー別エントリー状況
    private UIEntryOnerStatusDao uiEntryOnerStatusBasicEntryDao;
    //エントリーオーナー宛先情報
    private UIEntryOnerDao uiEntryOnerBasicEntryDao;
    //研修エントリー状況
    private UIEntryStateDao uiEntryStateBasicEntryDao;
    //スタッフマスタ
    private UIStaffDao uiStaffBasicEntryDao;
    //研修結果状況
    private TrnEntryResultDao trnEntryResultBasicEntryDao;
    //マスターライセンス結果状況
    private TrnMasterResultDao trnMasterResultBasicEntryDao;
    //マスターライセンス研修コース
    private MlCourseKanriDao mlCourseKanriBasicEntryDao;

    /**
     * エントリーマスタ管理・エントリー日付管理の検索を行う
     * @param hanyoRegistDto     
     * */
    public void execute(BasicEntryDto dto) throws ApplicationException {
    	// ０．妥当性チェック
        validate(dto);
        
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        // 入力情報
        List listEntryState = dto.getListEntryState();
        
        // 入力情報の有無をチェック
        String entryFlg = BasicEntryCommon.ENTRY_FLG_FUSANKA;
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                entryFlg = BasicEntryCommon.ENTRY_FLG_SANKA;
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
            = getMlCourseKanriBasicEntryDao().getMlCourseInfo(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai());
        //---2006/07/10現在 エントリーコード、年、回で1件のみ取得できる前提で開発
        MlCourseKanri mlCourseKanri = (MlCourseKanri) listMlcourseKanri.get(0);
        
        
        // 研修結果状況の更新
        doTrnEntryResult(dto, currentTimestamp, mlCourseKanri);

        // BT24MLKJ→BT32MLKR対応により削除
        // マスターライセンス結果状況の更新
//        doTrnMasterResult(dto, currentTimestamp, mlCourseKanri);
    }

    /**
     * オーナー別エントリー状況の更新
     */
    private void doEntryOnerStatus(BasicEntryDto dto, Timestamp currentTimestamp, String entryFlg) {
        UIEntryOnerStatus uiEntryOnerStatus = dto.getUiEntryOnerStatus();
        
        if (entryFlg.equals(HanyoApplicationCommon.ENTRY_FLG_FUSANKA)) {
            if (!uiEntryOnerStatus.isInsertFlg()) {
                getUiEntryOnerStatusBasicEntryDao().deleteEntryOner(uiEntryOnerStatus);
            }
        }
        else {
            if (uiEntryOnerStatus.isInsertFlg()) {
                //新規
// add start xkhata 20060821 システムエラー対応
                uiEntryOnerStatus.setInsertFlg( false );
// add end
                uiEntryOnerStatus.setEntryFlg(entryFlg);
                uiEntryOnerStatus.setFirstUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerStatus.setFirstPgm(LOGIC_ID.substring(0, 8));
                uiEntryOnerStatus.setFirstTmsp(currentTimestamp);
                uiEntryOnerStatus.setLastUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerStatus.setLastPgm(LOGIC_ID.substring(0, 8));
                uiEntryOnerStatus.setLastTmsp(currentTimestamp);
                getUiEntryOnerStatusBasicEntryDao().insertEntryOner(uiEntryOnerStatus);
            }
//  add start xkhata 20060821 システムエラー対応
            else {
                getUiEntryOnerStatusBasicEntryDao().updateEntryOner( uiEntryOnerStatus );
            }
//  add end

        }
    }
    /**
     * エントリーオーナー宛先情報の更新
     */
    private void doEntryOner(BasicEntryDto dto, Timestamp currentTimestamp, String entryFlg) {
        //申込責任者情報の更新
        UIEntryOner uiEntryOwnerMosikomiSekinin = dto.getUiEntryOnerMosikomiSekinin();
        if (entryFlg.equals(BasicEntryCommon.ENTRY_FLG_FUSANKA)) {
            if (!uiEntryOwnerMosikomiSekinin.isInsertFlg()) {
                getUiEntryOnerBasicEntryDao().deleteEntryOner(uiEntryOwnerMosikomiSekinin);
            }
        }
        else {
            uiEntryOwnerMosikomiSekinin.setLastUser(dto.getBirdUserInfo().getUserID());
            uiEntryOwnerMosikomiSekinin.setLastPgm(LOGIC_ID.substring(0, 8));
            if (!uiEntryOwnerMosikomiSekinin.isInsertFlg()) {
                //更新
                getUiEntryOnerBasicEntryDao().updateEntryOner(uiEntryOwnerMosikomiSekinin);
            }
            else {
// add start xkhata 20060821 システムエラー対応
                uiEntryOwnerMosikomiSekinin.setInsertFlg( false );
// add end
                //新規
                uiEntryOwnerMosikomiSekinin.setFirstUser(dto.getBirdUserInfo().getUserID());
                uiEntryOwnerMosikomiSekinin.setFirstPgm(LOGIC_ID.substring(0, 8));
                uiEntryOwnerMosikomiSekinin.setFirstTmsp(currentTimestamp);
                uiEntryOwnerMosikomiSekinin.setTel("");
//                uiEntryOwnerMosikomiSekinin.setSoufuName("");
                uiEntryOwnerMosikomiSekinin.setSoufuName( uiEntryOwnerMosikomiSekinin.getOnerNameKj() );
                getUiEntryOnerBasicEntryDao().insertEntryOner(uiEntryOwnerMosikomiSekinin);
            }
        }
        
    }
    
    /**
     * 研修エントリー状況の登録
     */
    private void doEntryState(BasicEntryDto dto, Timestamp currentTimestamp) {
        List listEntryState = dto.getListEntryState();
        
//        // 削除データを処理
//        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
//            UIEntryState uiEntryState = (UIEntryState) ite.next();
//            if ("".equals(uiEntryState.getStaffId())) {
//                getUiEntryStateBasicEntryDao().deleteEntry(uiEntryState);
//            }
//        }
        
        // 登録済みのデータを全件削除
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            getUiEntryStateBasicEntryDao().deleteEntry(uiEntryState);
        }
        
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            uiEntryState.setLastPgm(LOGIC_ID.substring(0, 8));
            uiEntryState.setLastUser(dto.getBirdUserInfo().getUserID());
            if (BasicEntryCommon.GUIDE_KBN_OTHER.equals(uiEntryState.getGuideKbn())) {
                uiEntryState.setGuideName(uiEntryState.getGuideNameInput());
            }
//            if (!uiEntryState.isInsertFlg()) {
//                if (!isNull(uiEntryState.getStaffId())) {
//                    getUiEntryStateBasicEntryDao().updateEntry(uiEntryState);
//                }
//                else {
//                    getUiEntryStateBasicEntryDao().deleteEntry(uiEntryState);
//                }
//            }
//            else {
//                //新規
//                if (!isNull(uiEntryState.getStaffId())) {
//                    uiEntryState.setFirstUser(dto.getBirdUserInfo().getUserID());
//                    uiEntryState.setFirstPgm(LOGIC_ID.substring(0, 8));
//                    uiEntryState.setFirstTmsp(currentTimestamp);
//                    uiEntryState.setLastTmsp(currentTimestamp);
//                    uiEntryState.setCourseCd("");
//                    uiEntryState.setOtherFlg3("");
//                    uiEntryState.setOtherFlg4("");
//                    uiEntryState.setOtherFlg5("");
//                    getUiEntryStateBasicEntryDao().insertEntry(uiEntryState);
//                    //スタッフマスタ更新
//                    updateMstStaff(uiEntryState);
//                }
//            }
            if (!isNull(uiEntryState.getStaffId())) {
                if (uiEntryState.isInsertFlg()) {
                    uiEntryState.setFirstUser(dto.getBirdUserInfo().getUserID());
                    uiEntryState.setFirstPgm(LOGIC_ID.substring(0, 8));
                    uiEntryState.setFirstTmsp(currentTimestamp);
                    uiEntryState.setOtherFlg3("");
                    uiEntryState.setOtherFlg4("");
                    uiEntryState.setOtherFlg5("");
                    uiEntryState.setCourseCd("");
                }
                uiEntryState.setLastUser(dto.getBirdUserInfo().getUserID());
                uiEntryState.setLastPgm(LOGIC_ID.substring(0, 8));
                uiEntryState.setLastTmsp(currentTimestamp);
                getUiEntryStateBasicEntryDao().insertEntry(uiEntryState);
                //スタッフマスタ更新
                updateMstStaff(uiEntryState);
            }
        }
    }
    
    /**
     * 研修結果状況の更新
     * @param dto
     * @param currentTimestamp
     */
    private void doTrnEntryResult(BasicEntryDto dto, Timestamp currentTimestamp, MlCourseKanri mlCourseKanri) {
        List listEntryState = dto.getListEntryState();
        // 登録済みのデータを全件削除
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                getTrnEntryResultBasicEntryDao()
                        .deleteTrnEntryResult(dto.getEntryCd(),
                                                dto.getEntryYear(),
                                                dto.getEntryKai(),
                                                uiEntryState.getStaffId());
            }
            if (!isNull(uiEntryState.getSelectedStaffId())) {
                getTrnEntryResultBasicEntryDao()
                        .deleteTrnEntryResult(dto.getEntryCd(),
                                                dto.getEntryYear(),
                                                dto.getEntryKai(),
                                                uiEntryState.getSelectedStaffId());
            }
        }
        
        for (Iterator ite = dto.getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                int count = getTrnEntryResultBasicEntryDao()
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
                    trnEntryResult.setFirstPgm(LOGIC_ID.substring(0, 8));
                    trnEntryResult.setFirstTmsp(currentTimestamp);
                    trnEntryResult.setLastUser(dto.getBirdUserInfo().getUserID());
                    trnEntryResult.setLastPgm(LOGIC_ID.substring(0, 8));
                    trnEntryResult.setLastTmsp(currentTimestamp);
                    
                    getTrnEntryResultBasicEntryDao().insertEntry(trnEntryResult);
                }
            }
        }
    }
    
    /**
     * マスターライセンス結果状況の更新
     * @param dto
     * @param currentTimestamp
     */
    private void doTrnMasterResult(BasicEntryDto dto, Timestamp currentTimestamp, MlCourseKanri mlCourseKanri) {
        for (Iterator ite = dto.getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            
            if (!isNull(uiEntryState.getStaffId())) {
                if (getTrnMasterResultBasicEntryDao().getCount(uiEntryState.getStaffId()) == 0) {
                    TrnMasterResult trnMasterResult = new TrnMasterResult();
                    trnMasterResult.setStaffId(uiEntryState.getStaffId());
                    trnMasterResult.setCourseStatus(COURSE_STATUS_DEFAULT);
                    trnMasterResult.setCompleCourseCd(mlCourseKanri.getCourseCd());
                    trnMasterResult.setCompleCourseName(mlCourseKanri.getCourseName());
                    trnMasterResult.setFirstUser(dto.getBirdUserInfo().getUserID());
                    trnMasterResult.setFirstPgm(LOGIC_ID.substring(0, 8));
                    trnMasterResult.setFirstTmsp(currentTimestamp);
                    trnMasterResult.setLastUser(dto.getBirdUserInfo().getUserID());
                    trnMasterResult.setLastPgm(LOGIC_ID.substring(0, 8));
                    trnMasterResult.setLastTmsp(currentTimestamp);
                    
                    getTrnMasterResultBasicEntryDao().insertEntry(trnMasterResult);
                }
                // 検索時のスタッフIDと入力されたスタッフIDが違う場合は、検索時のスタッフIDの情報を削除
                if (!uiEntryState.getStaffId().equals(uiEntryState.getSelectedStaffId())) {
                    getTrnMasterResultBasicEntryDao().updateEntry(uiEntryState.getSelectedStaffId());
                }
            }
            else {
                // 削除
                getTrnMasterResultBasicEntryDao().updateEntry(uiEntryState.getSelectedStaffId());
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
        
// add start 20060821 xkhata システムエラー対応
        uiEntryState.setSelectedStaffId( uiStaff.getStaffId() );
        uiEntryState.setStaffId( uiStaff.getStaffId() );
// add end
    }
    /**
     * 必須、妥当性チェック
     * @param hanyoRegistDto
     */
    private void validate(BasicEntryDto hanyoApplicationDto) throws ApplicationException{
    }

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

    public void setMstMiseBasicEntryDao(MstMiseDao mstMiseHanyoApplicationDao) {
        this.mstMiseBasicEntryDao = mstMiseHanyoApplicationDao;
    }

    public UIEntryOnerDao getUiEntryOnerBasicEntryDao() {
        return uiEntryOnerBasicEntryDao;
    }

    public void setUiEntryOnerBasicEntryDao(
            UIEntryOnerDao uiEntryOwnerDaoHanyoApplicationDao) {
        this.uiEntryOnerBasicEntryDao = uiEntryOwnerDaoHanyoApplicationDao;
    }

    public UIEntryStateDao getUiEntryStateBasicEntryDao() {
        return uiEntryStateBasicEntryDao;
    }

    public void setUiEntryStateBasicEntryDao(
            UIEntryStateDao uiEntryStateDaoHanyoApplicationDao) {
        this.uiEntryStateBasicEntryDao = uiEntryStateDaoHanyoApplicationDao;
    }

    public UIStaffDao getUiStaffBasicEntryDao() {
        return uiStaffBasicEntryDao;
    }

    public void setUiStaffBasicEntryDao(UIStaffDao uiStaffBasicEntryDao) {
        this.uiStaffBasicEntryDao = uiStaffBasicEntryDao;
    }

    public TrnEntryResultDao getTrnEntryResultBasicEntryDao() {
        return trnEntryResultBasicEntryDao;
    }

    public void setTrnEntryResultBasicEntryDao(
            TrnEntryResultDao trnEntryResultBasicEntryDao) {
        this.trnEntryResultBasicEntryDao = trnEntryResultBasicEntryDao;
    }

    public TrnMasterResultDao getTrnMasterResultBasicEntryDao() {
        return trnMasterResultBasicEntryDao;
    }

    public void setTrnMasterResultBasicEntryDao(
            TrnMasterResultDao trnMasterResultBasicEntryDao) {
        this.trnMasterResultBasicEntryDao = trnMasterResultBasicEntryDao;
    }

    public MlCourseKanriDao getMlCourseKanriBasicEntryDao() {
        return mlCourseKanriBasicEntryDao;
    }

    public void setMlCourseKanriBasicEntryDao(
            MlCourseKanriDao mlCourseKanriBasicEntryDao) {
        this.mlCourseKanriBasicEntryDao = mlCourseKanriBasicEntryDao;
    }

}
