/*
 * 作成日: 2006/02/01
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;

import jp.co.isid.mos.bird.common.dao.TrnControlCompanyDao;
import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiDao;
import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiKobetuDao;
import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiTenpoDao;
import jp.co.isid.mos.bird.common.dao.TrnControlShozokuDao;
import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.TrnControlCompany;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotai;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiKobetu;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiTenpo;
import jp.co.isid.mos.bird.common.entity.TrnControlShozoku;
import jp.co.isid.mos.bird.common.logic.RegKoukaiTaishoLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * @author xyuchida
 *
 */
public class RegKoukaiTaishoLogicImpl implements RegKoukaiTaishoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L07";
    
    private TrnControlCompanyDao trnControlCompanyDao;
    private TrnControlShozokuDao trnControlShozokuDao;
    private TrnControlGyotaiDao trnControlGyotaiDao;
    private TrnControlGyotaiKobetuDao trnControlGyotaiKobetuDao;
    private TrnControlGyotaiTenpoDao trnControlGyotaiTenpoDao;

    /**
     * @return trnControlCompanyDao を戻します。
     */
    public TrnControlCompanyDao getTrnControlCompanyDao() {
        return trnControlCompanyDao;
    }

    /**
     * @param trnControlCompanyDao trnControlCompanyDao を設定。
     */
    public void setTrnControlCompanyDao(
            TrnControlCompanyDao trnControlCompanyDao) {
        this.trnControlCompanyDao = trnControlCompanyDao;
    }

    /**
     * @return trnControlShozokuDao を戻します。
     */
    public TrnControlShozokuDao getTrnControlShozokuDao() {
        return trnControlShozokuDao;
    }

    /**
     * @param trnControlShozokuDao trnControlShozokuDao を設定。
     */
    public void setTrnControlShozokuDao(
            TrnControlShozokuDao trnControlShozokuDao) {
        this.trnControlShozokuDao = trnControlShozokuDao;
    }

    /**
     * @return trnControlGyotaiDao を戻します。
     */
    public TrnControlGyotaiDao getTrnControlGyotaiDao() {
        return trnControlGyotaiDao;
    }

    /**
     * @param trnControlGyotaiDao trnControlGyotaiDao を設定。
     */
    public void setTrnControlGyotaiDao(TrnControlGyotaiDao trnControlGyotaiDao) {
        this.trnControlGyotaiDao = trnControlGyotaiDao;
    }

    /**
     * @return trnControlGyotaiKobetuDao を戻します。
     */
    public TrnControlGyotaiKobetuDao getTrnControlGyotaiKobetuDao() {
        return trnControlGyotaiKobetuDao;
    }

    /**
     * @param trnControlGyotaiKobetuDao trnControlGyotaiKobetuDao を設定。
     */
    public void setTrnControlGyotaiKobetuDao(
            TrnControlGyotaiKobetuDao trnControlGyotaiKobetuDao) {
        this.trnControlGyotaiKobetuDao = trnControlGyotaiKobetuDao;
    }

    /**
     * @return trnControlGyotaiTenpoDao を戻します。
     */
    public TrnControlGyotaiTenpoDao getTrnControlGyotaiTenpoDao() {
        return trnControlGyotaiTenpoDao;
    }

    /**
     * @param trnControlGyotaiTenpoDao trnControlGyotaiTenpoDao を設定。
     */
    public void setTrnControlGyotaiTenpoDao(
            TrnControlGyotaiTenpoDao trnControlGyotaiTenpoDao) {
        this.trnControlGyotaiTenpoDao = trnControlGyotaiTenpoDao;
    }

    public void execute(PublicTargetDto publicTargetDto, String userId, String programId) {

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        // 制御対象会社
        // 削除
        TrnControlCompany deleteKeyCompany = new TrnControlCompany();
        // キー項目設定
        deleteKeyCompany.setInfoShu(publicTargetDto.getInfoShu());
        deleteKeyCompany.setRegDate(publicTargetDto.getRegDate());
        deleteKeyCompany.setSeq(publicTargetDto.getSeq());
        // delete
        trnControlCompanyDao.deleteControlCompany(deleteKeyCompany);
        // 登録
        for (Iterator it = publicTargetDto.getListTrnControlCompany().iterator(); it.hasNext();) {
            TrnControlCompany trnControlCompany = (TrnControlCompany) it.next();
            // キー項目設定
            trnControlCompany.setInfoShu(publicTargetDto.getInfoShu());
            trnControlCompany.setRegDate(publicTargetDto.getRegDate());
            trnControlCompany.setSeq(publicTargetDto.getSeq());
            // 共通項目設定
            trnControlCompany.setFirstUser(userId);
            trnControlCompany.setFirstPgm(programId);
            trnControlCompany.setFirstTmsp(currentTimestamp);
            trnControlCompany.setLastUser(userId);
            trnControlCompany.setLastPgm(programId);
            trnControlCompany.setLastTmsp(currentTimestamp);
            // insert
            trnControlCompanyDao.insertControlCompany(trnControlCompany);
        }

        // 制御対象所属
        // 削除
        TrnControlShozoku deleteKeyShozoku = new TrnControlShozoku();
        // キー項目設定
        deleteKeyShozoku.setInfoShu(publicTargetDto.getInfoShu());
        deleteKeyShozoku.setRegDate(publicTargetDto.getRegDate());
        deleteKeyShozoku.setSeq(publicTargetDto.getSeq());
        // delete
        trnControlShozokuDao.deleteControlShozoku(deleteKeyShozoku);
        // 登録
        for (Iterator it = publicTargetDto.getListTrnControlShozoku().iterator(); it.hasNext();) {
            TrnControlShozoku trnControlShozoku = (TrnControlShozoku) it.next();
            // キー項目設定
            trnControlShozoku.setInfoShu(publicTargetDto.getInfoShu());
            trnControlShozoku.setRegDate(publicTargetDto.getRegDate());
            trnControlShozoku.setSeq(publicTargetDto.getSeq());
            // 共通項目設定
            trnControlShozoku.setFirstUser(userId);
            trnControlShozoku.setFirstPgm(programId);
            trnControlShozoku.setFirstTmsp(currentTimestamp);
            trnControlShozoku.setLastUser(userId);
            trnControlShozoku.setLastPgm(programId);
            trnControlShozoku.setLastTmsp(currentTimestamp);
            // insert
            trnControlShozokuDao.insertControlShozoku(trnControlShozoku);
        }

        // 制御対象業態
        // 削除
        TrnControlGyotai deleteKeyGyotai = new TrnControlGyotai();
        // キー項目設定
        deleteKeyGyotai.setInfoShu(publicTargetDto.getInfoShu());
        deleteKeyGyotai.setRegDate(publicTargetDto.getRegDate());
        deleteKeyGyotai.setSeq(publicTargetDto.getSeq());
        // delete
        trnControlGyotaiDao.deleteControlGyotai(deleteKeyGyotai);
        // 登録
        for (Iterator it = publicTargetDto.getListTrnControlGyotai().iterator(); it.hasNext();) {
            TrnControlGyotai trnControlGyotai = (TrnControlGyotai) it.next();
            // キー項目設定
            trnControlGyotai.setInfoShu(publicTargetDto.getInfoShu());
            trnControlGyotai.setRegDate(publicTargetDto.getRegDate());
            trnControlGyotai.setSeq(publicTargetDto.getSeq());
            // 共通項目設定
            trnControlGyotai.setFirstUser(userId);
            trnControlGyotai.setFirstPgm(programId);
            trnControlGyotai.setFirstTmsp(currentTimestamp);
            trnControlGyotai.setLastUser(userId);
            trnControlGyotai.setLastPgm(programId);
            trnControlGyotai.setLastTmsp(currentTimestamp);
            // insert
            trnControlGyotaiDao.insertControlGyotai(trnControlGyotai);
        }

        // 制御業態個別
        // 削除
        TrnControlGyotaiKobetu deleteKeyGyotaiKobetu = new TrnControlGyotaiKobetu();
        // キー項目設定
        deleteKeyGyotaiKobetu.setInfoShu(publicTargetDto.getInfoShu());
        deleteKeyGyotaiKobetu.setRegDate(publicTargetDto.getRegDate());
        deleteKeyGyotaiKobetu.setSeq(publicTargetDto.getSeq());
        // delete
        trnControlGyotaiKobetuDao.deleteControlGyotaiKobetu(deleteKeyGyotaiKobetu);
        // 登録
        for (Iterator it = publicTargetDto.getListTrnControlGyotaiKobetu().iterator(); it.hasNext();) {
            TrnControlGyotaiKobetu trnControlGyotaiKobetu = (TrnControlGyotaiKobetu) it.next();
            // キー項目設定
            trnControlGyotaiKobetu.setInfoShu(publicTargetDto.getInfoShu());
            trnControlGyotaiKobetu.setRegDate(publicTargetDto.getRegDate());
            trnControlGyotaiKobetu.setSeq(publicTargetDto.getSeq());
            // 共通項目設定
            trnControlGyotaiKobetu.setFirstUser(userId);
            trnControlGyotaiKobetu.setFirstPgm(programId);
            trnControlGyotaiKobetu.setFirstTmsp(currentTimestamp);
            trnControlGyotaiKobetu.setLastUser(userId);
            trnControlGyotaiKobetu.setLastPgm(programId);
            trnControlGyotaiKobetu.setLastTmsp(currentTimestamp);
            // insert
            trnControlGyotaiKobetuDao.insertControlGyotaiKobetu(trnControlGyotaiKobetu);
        }

        // 制御業態店舗別
        // 削除
        TrnControlGyotaiTenpo deleteKeyGyotaiTenpo = new TrnControlGyotaiTenpo();
        // キー項目設定
        deleteKeyGyotaiTenpo.setInfoShu(publicTargetDto.getInfoShu());
        deleteKeyGyotaiTenpo.setRegDate(publicTargetDto.getRegDate());
        deleteKeyGyotaiTenpo.setSeq(publicTargetDto.getSeq());
        // delete
        trnControlGyotaiTenpoDao.deleteControlGyotaiTenpo(deleteKeyGyotaiTenpo);
        // 登録
        for (Iterator it = publicTargetDto.getListTrnControlGyotaiTenpo().iterator(); it.hasNext();) {
            TrnControlGyotaiTenpo trnControlGyotaiTenpo = (TrnControlGyotaiTenpo) it.next();
            // キー項目設定
            trnControlGyotaiTenpo.setInfoShu(publicTargetDto.getInfoShu());
            trnControlGyotaiTenpo.setRegDate(publicTargetDto.getRegDate());
            trnControlGyotaiTenpo.setSeq(publicTargetDto.getSeq());
            // 共通項目設定
            trnControlGyotaiTenpo.setFirstUser(userId);
            trnControlGyotaiTenpo.setFirstPgm(programId);
            trnControlGyotaiTenpo.setFirstTmsp(currentTimestamp);
            trnControlGyotaiTenpo.setLastUser(userId);
            trnControlGyotaiTenpo.setLastPgm(programId);
            trnControlGyotaiTenpo.setLastTmsp(currentTimestamp);
            // insert
            trnControlGyotaiTenpoDao.insertControlGyotaiTenpo(trnControlGyotaiTenpo);
        }
    }
}
