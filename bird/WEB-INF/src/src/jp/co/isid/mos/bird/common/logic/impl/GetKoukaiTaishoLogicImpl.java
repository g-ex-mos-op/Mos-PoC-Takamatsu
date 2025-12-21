/*
 * 作成日: 2006/01/31
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.common.dao.TrnControlCompanyDao;
import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiDao;
import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiKobetuDao;
import jp.co.isid.mos.bird.common.dao.TrnControlGyotaiTenpoDao;
import jp.co.isid.mos.bird.common.dao.TrnControlShozokuDao;
import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.TrnControlGyotai;
import jp.co.isid.mos.bird.common.logic.GetKoukaiTaishoLogic;

/**
 * @author xyuchida
 *
 */
public class GetKoukaiTaishoLogicImpl implements GetKoukaiTaishoLogic {
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L06";
    
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

    public PublicTargetDto execute(String infoShu, String regDate, String seq) {

        PublicTargetDto publicTargetDto = new PublicTargetDto();

        // キー項目設定
        publicTargetDto.setInfoShu(infoShu);
        publicTargetDto.setRegDate(regDate);
        publicTargetDto.setSeq(seq);

        // 制御対象会社取得
        publicTargetDto.setListTrnControlCompany(trnControlCompanyDao.getControlCompany(infoShu, regDate, seq));
        // 制御対象所属取得
        publicTargetDto.setListTrnControlShozoku(trnControlShozokuDao.getControlShozoku(infoShu, regDate, seq));
        // 制御対象業態取得
        publicTargetDto.setListTrnControlGyotai(trnControlGyotaiDao.getControlGyotai(infoShu, regDate, seq));

        List trnControlGyotaiKobetuList = new ArrayList();
        List trnControlGyotaiTenpoList = new ArrayList();
        for (Iterator it = publicTargetDto.getListTrnControlGyotai().iterator(); it.hasNext();) {
            TrnControlGyotai trnControlGyotai = (TrnControlGyotai) it.next();
            // 制御業態個別取得
            trnControlGyotaiKobetuList.addAll(
                   trnControlGyotaiKobetuDao.getControlGyotaiKobetu(infoShu, regDate, seq, trnControlGyotai.getGyotaiKbn()));
            // 制御業態店舗別取得
            trnControlGyotaiTenpoList.addAll(
                    trnControlGyotaiTenpoDao.getControlGyotaiTenpo(infoShu, regDate, seq, trnControlGyotai.getGyotaiKbn()));
        }
        publicTargetDto.setListTrnControlGyotaiKobetu(trnControlGyotaiKobetuList);
        publicTargetDto.setListTrnControlGyotaiTenpo(trnControlGyotaiTenpoList);

        return publicTargetDto;
    }
}
