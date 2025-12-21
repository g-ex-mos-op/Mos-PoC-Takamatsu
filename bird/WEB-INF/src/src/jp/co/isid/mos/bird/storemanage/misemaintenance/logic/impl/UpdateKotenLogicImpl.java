package jp.co.isid.mos.bird.storemanage.misemaintenance.logic.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstBukkenDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstChintaiDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstEventStatusDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstMiseDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstMiseKaisoDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstMiseYobiDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstSBDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.MstTODao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dao.TrnURLDao;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstBukken;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstChintai;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstEventStatus;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMise;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseKaiso;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseYobi;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstSB;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstTO;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.TrnURL;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.UpdateKotenLogic;

/**
 * 個店情報の更新 ロジック
 * @author xnkusama
 * 
 * 更新日:2011/07/11 xkinu 店舗ガス種別追加対応
 */
public class UpdateKotenLogicImpl implements UpdateKotenLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BSM001L04";
    /*【DAO】*/
    private MstEventStatusDao mstEventStatusDao;
    private MstMiseYobiDao mstMiseYobiDao;
    private TrnURLDao trnURLDao;
    private MstMiseDao mstMiseDao;
    private MstBukkenDao mstBukkenDao;
    private MstChintaiDao mstChintaiDao;
    private MstMiseKaisoDao mstMiseKaiso;
    private MstTODao mstTODao;
    private MstSBDao mstSBDao;
    
    /**
     * 個店情報の更新を行う
     * @param MiseMaintenanceDto 画面DTO
     * @exception ApplicationException
     * 
     * 更新日:2011/07/11 xkinu 店舗ガス種別追加対応
     */
    public void execute(MiseMaintenanceDto dto) throws ApplicationException {
        //TODO 2006/02/21 実装
        
        // パラメータチェック
        validate(dto);
        
        String miseCd = dto.getCondMiseCd();
        String companyCd = dto.getCondCompanyCd();
        
        /* １．DAO【イベント実施状況．イベント実施状況の削除】を実行 */
        getMstEventStatusDao().deleteEvent(companyCd, miseCd);
        /* ２．DAO【イベント実施状況．イベント実施状況の挿入】を実行 */
        List listMstEventStatus = dto.getListMstEventStatus();
        for (int i = 0; i < listMstEventStatus.size(); i++) {
            MstEventStatus entity = (MstEventStatus) listMstEventStatus.get(i);            
            entity.setCompanyCd(companyCd);
            entity.setMiseCd(miseCd);
            Timestamp timestamp = DateManager.getCurrentTimestamp();
            entity.setFirstUser(dto.getUserId());
            entity.setFirstPgm(LOGIC_ID.substring(0, 6));
            entity.setFirstTmsp(timestamp);
            entity.setLastTmsp(timestamp);
            entity.setLastUser(dto.getUserId());
            entity.setLastPgm(LOGIC_ID.substring(0, 6));       
            getMstEventStatusDao().insertEvent(entity);
        }
        
        /* ３．DAO【店舗拡張マスタ．店舗拡張マスタの削除】を実行 */
        getMstMiseYobiDao().deleteMiseYobi(companyCd, miseCd);
        MstMiseYobi mstMiseYobi = dto.getMstMiseYobi();
        mstMiseYobi.setCompanyCd(dto.getCondCompanyCd());
        mstMiseYobi.setMiseCd(dto.getCondMiseCd());
//		2011/07/11 xkinu ADD start 店舗ガス種別追加対応
        if (CommonUtil.isNull(mstMiseYobi.getYobiKbn1())) {
    		mstMiseYobi.setYobiKbn1("");
    	}
    	if (CommonUtil.isNull(mstMiseYobi.getYobiKbn2())) {
    		mstMiseYobi.setYobiKbn2("");
    	}
//    	2011/07/11 xkinu ADD end 店舗ガス種別追加対応
        mstMiseYobi.setFirstUser(dto.getUserId());
        mstMiseYobi.setFirstPgm(LOGIC_ID.substring(0, 6));
        mstMiseYobi.setFirstTmsp(DateManager.getCurrentTimestamp());
        mstMiseYobi.setLastPgm(LOGIC_ID.substring(0, 6));
        mstMiseYobi.setLastUser(dto.getUserId());
        mstMiseYobi.setLastTmsp(DateManager.getCurrentTimestamp());
        /* ４．DAO【店舗拡張マスタ．店舗拡張マスタの挿入】を実行 */
        getMstMiseYobiDao().insertMiseYobi(mstMiseYobi);
        
        /* ５．DAO【地図URL情報．地図URL情報の削除】を実行 */
        getTrnURLDao().deleteMapURL(companyCd, miseCd);
        TrnURL trnUrl = dto.getTrnUrl();
        trnUrl.setCompanyCd(dto.getCondCompanyCd());
        trnUrl.setMiseCd(dto.getCondMiseCd());
        trnUrl.setFirstUser(dto.getUserId());
        trnUrl.setFirstPgm(LOGIC_ID.substring(0, 6));
        trnUrl.setFirstTmsp(DateManager.getCurrentTimestamp());
        trnUrl.setLastPgm(LOGIC_ID.substring(0, 6));
        trnUrl.setLastUser(dto.getUserId());
        trnUrl.setLastTmsp(DateManager.getCurrentTimestamp());
        /* ６．DAO【地図URL情報．地図URL情報の挿入】を実行 */
        getTrnURLDao().insertMapURL(dto.getTrnUrl());
        
        /** ７．会社コードがモス以外の場合のみ処理 ************************************/
        if (!"00".equals(companyCd)) {
            // DAO【店総合マスタ．店統合マスタの更新】
            MstMise mstMise = dto.getMstMise();
            mstMise.setLastPgm(LOGIC_ID.substring(0, 6));
            mstMise.setLastUser(dto.getUserId());
            mstMise.setLastTmsp(DateManager.getCurrentTimestamp());
            getMstMiseDao().updateMiseMst(mstMise);
            
            // DAO【物件情報履歴．物件情報履歴の削除】
            getMstBukkenDao().deleteBukken(companyCd, miseCd);
            // DAO【物件情報履歴．物件情報履歴の挿入】
            List listMstBukken = dto.getListBukken();
            for (int i = 0; i < listMstBukken.size(); i++) {
                MstBukken entity = (MstBukken) listMstBukken.get(i);
                Timestamp tmspNow = DateManager.getCurrentTimestamp();
                //金額
//                if (entity.getKeiyakuYachin() == null) {
//                    entity.setKeiyakuYachin(new BigDecimal("0"));
//                }
                entity.setCompanyCd(dto.getCondCompanyCd());
                entity.setMiseCd(dto.getCondMiseCd());
                entity.setFirstTmsp(tmspNow);
                entity.setFirstUser(dto.getUserId());
                entity.setFirstPgm(LOGIC_ID.substring(0, 6));
                entity.setLastTmsp(tmspNow);
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(LOGIC_ID.substring(0, 6));
                getMstBukkenDao().insertBukken(entity);
            }
            
            // DAO【賃貸店舗履歴．賃貸店舗履歴の削除】
            getMstChintaiDao().deleteChintai(companyCd, miseCd);
            // DAO【賃貸店舗履歴．賃貸店舗履歴の挿入】
            List listMstChintai = dto.getListChintai();
            for (int i = 0; i < listMstChintai.size(); i++) {
                MstChintai entity = (MstChintai) listMstChintai.get(i);
                Timestamp tmspNow = DateManager.getCurrentTimestamp();
                entity.setCompanyCd(dto.getCondCompanyCd());
                entity.setMiseCd(dto.getCondMiseCd());
                entity.setFirstTmsp(tmspNow);
                entity.setFirstUser(dto.getUserId());
                entity.setFirstPgm(LOGIC_ID.substring(0, 6));
                entity.setLastTmsp(tmspNow);
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(LOGIC_ID.substring(0, 6));
                getMstChintaiDao().insertChintai(entity);
            }

            /**店改装履歴 -------------------------------------------------------- */
            // DAO【店改装履歴．店改装履歴の削除】
            getMstMiseKaisoDao().deleteMiseKaiso(companyCd, miseCd);
            // DAO【店改装履歴．店改装履歴の挿入】
            List listMstMiseKaiso = dto.getListMiseKaiso();
            for (int i = 0; i < listMstMiseKaiso.size(); i++) {
                MstMiseKaiso entity = (MstMiseKaiso) listMstMiseKaiso.get(i);
                Timestamp tmspNow = DateManager.getCurrentTimestamp();
                //金額
//                if (entity.getKaisoHiyo() == null) {
//                    entity.setKaisoHiyo(new BigDecimal("0"));
//                }
                entity.setKaisoKaisu(new BigDecimal(String.valueOf(listMstMiseKaiso.size() - i)));
                entity.setCompanyCd(dto.getCondCompanyCd());
                entity.setMiseCd(dto.getCondMiseCd());
                entity.setFirstTmsp(tmspNow);
                entity.setFirstUser(dto.getUserId());
                entity.setFirstPgm(LOGIC_ID.substring(0, 6));
                entity.setLastTmsp(tmspNow);
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(LOGIC_ID.substring(0, 6));
                //Insert
                getMstMiseKaisoDao().insertMiseKaiso(entity);
            }

            /**テイクオーバー履歴 ------------------------------------------------ */
            // DAO【TO履歴．TO履歴の削除】
            getMstTODao().deleteTO(companyCd, miseCd);
            // DAO【TO履歴．TO履歴の挿入】
            List listMstTO = dto.getListTO();         
            for (int i = 0; i < listMstTO.size(); i++) {
                MstTO entity = (MstTO) listMstTO.get(i);
                Timestamp tmspNow = DateManager.getCurrentTimestamp();
                entity.setToKaisu(new BigDecimal(String.valueOf(listMstTO.size() - i)));
                entity.setCompanyCd(dto.getCondCompanyCd());
                entity.setMiseCd(dto.getCondMiseCd());
                entity.setFirstTmsp(tmspNow);
                entity.setFirstUser(dto.getUserId());
                entity.setFirstPgm(LOGIC_ID.substring(0, 6));
                entity.setLastTmsp(tmspNow);
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(LOGIC_ID.substring(0, 6));
                //Insert
                getMstTODao().insertTO(entity);
            }

            /** スクラップビルド履歴 --------------------------------------------- */
            // DAO【SB履歴．SB履歴の削除】
            getMstSBDao().deleteSB(companyCd, miseCd);
            // DAO【SB履歴．SB装履歴の挿入】
            List listMstSB= dto.getListSB();
            for (int i = 0; i < listMstSB.size(); i++) {
                MstSB entity = (MstSB) listMstSB.get(i);
                Timestamp tmspNow = DateManager.getCurrentTimestamp();
                entity.setSbKaisu(new BigDecimal(String.valueOf(listMstSB.size() - i)));
                entity.setCompanyCd(dto.getCondCompanyCd());
                entity.setMiseCd(dto.getCondMiseCd());
                entity.setFirstTmsp(tmspNow);
                entity.setFirstUser(dto.getUserId());
                entity.setFirstPgm(LOGIC_ID.substring(0, 6));
                entity.setLastTmsp(tmspNow);
                entity.setLastUser(dto.getUserId());
                entity.setLastPgm(LOGIC_ID.substring(0, 6));
                //Insert
                getMstSBDao().insertSB(entity);
            }
        }
    }
    
    private void validate(MiseMaintenanceDto dto) throws ApplicationException {
        // ユーザーID
        String userId = dto.getUserId();
        if (isNull(userId)) {
            throw new NotNullException("ユーザーID");
        }
        //店コード
        String miseCd = dto.getCondMiseCd();
        if (isNull(miseCd)) {
            throw new NotNullException("店コード");
        }
        //会社コード
        String companyCd = dto.getCondCompanyCd();
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        
        //TODO イベント実施状況、店拡張ますた、地図URL情報の必須テスト
    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * @return mstEventStatusDao を戻します。
     */
    public MstEventStatusDao getMstEventStatusDao() {
        return mstEventStatusDao;
    }
    /**
     * @param mstEventStatusDao mstEventStatusDao を設定。
     */
    public void setMstEventStatusDao(MstEventStatusDao mstEventStatusDao) {
        this.mstEventStatusDao = mstEventStatusDao;
    }
    /**
     * @return mstMiseYobiDao を戻します。
     */
    public MstMiseYobiDao getMstMiseYobiDao() {
        return mstMiseYobiDao;
    }
    /**
     * @param mstMiseYobiDao mstMiseYobiDao を設定。
     */
    public void setMstMiseYobiDao(MstMiseYobiDao mstMiseYobiDao) {
        this.mstMiseYobiDao = mstMiseYobiDao;
    }
    /**
     * @return trnURLDao を戻します。
     */
    public TrnURLDao getTrnURLDao() {
        return trnURLDao;
    }
    /**
     * @param trnURLDao trnURLDao を設定。
     */
    public void setTrnURLDao(TrnURLDao trnURLDao) {
        this.trnURLDao = trnURLDao;
    }
    /**
     * @return mstBukkenDao を戻します。
     */
    public MstBukkenDao getMstBukkenDao() {
        return mstBukkenDao;
    }
    /**
     * @param mstBukkenDao mstBukkenDao を設定。
     */
    public void setMstBukkenDao(MstBukkenDao mstBukkenDao) {
        this.mstBukkenDao = mstBukkenDao;
    }
    /**
     * @return mstMiseDao を戻します。
     */
    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }
    /**
     * @param mstMiseDao mstMiseDao を設定。
     */
    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }
    /**
     * @return mstChintaiDao を戻します。
     */
    public MstChintaiDao getMstChintaiDao() {
        return mstChintaiDao;
    }
    /**
     * @param mstChintaiDao mstChintaiDao を設定。
     */
    public void setMstChintaiDao(MstChintaiDao mstChintaiDao) {
        this.mstChintaiDao = mstChintaiDao;
    }
    /**
     * @return mstMiseKaiso を戻します。
     */
    public MstMiseKaisoDao getMstMiseKaisoDao() {
        return mstMiseKaiso;
    }
    /**
     * @param mstMiseKaiso mstMiseKaiso を設定。
     */
    public void setMstMiseKaisoDao(MstMiseKaisoDao mstMiseKaiso) {
        this.mstMiseKaiso = mstMiseKaiso;
    }
    /**
     * @return mstSBDao を戻します。
     */
    public MstSBDao getMstSBDao() {
        return mstSBDao;
    }
    /**
     * @param mstSBDao mstSBDao を設定。
     */
    public void setMstSBDao(MstSBDao mstSBDao) {
        this.mstSBDao = mstSBDao;
    }
    /**
     * @return mstTODao を戻します。
     */
    public MstTODao getMstTODao() {
        return mstTODao;
    }
    /**
     * @param mstTODao mstTODao を設定。
     */
    public void setMstTODao(MstTODao mstTODao) {
        this.mstTODao = mstTODao;
    }
}
