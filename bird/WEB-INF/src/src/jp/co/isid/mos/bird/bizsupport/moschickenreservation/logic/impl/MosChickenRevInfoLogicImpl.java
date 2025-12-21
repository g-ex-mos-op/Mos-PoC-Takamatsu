package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.TrnMosChickenInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevInfoLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
/**
 * モスチキン予約情報取得ロジック
 * @author inazawa
 * 2006/09/19
 */
public class MosChickenRevInfoLogicImpl implements MosChickenRevInfoLogic {
    /* モスチキン予約情報取得Dao*/
    private TrnMosChickenInfoDao trnMosChickenInfoDao;
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L06";

    public List execute(MosChickenRevInfoDto dto) {
        DateFormatter formatter = new DateFormatter();

        return getTrnMosChickenInfoDao().getMosChickenInfo(dto.getCkanriNo(),dto.getMiseCd(),dto.getCompanyCd(),formatter.format(String.valueOf(dto.getReservDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD));
    }
    /**
     * trnMosChickenInfoDaoを戻す
     * @return trnMosChickenInfoDao
     */ 
    public TrnMosChickenInfoDao getTrnMosChickenInfoDao() {
        return trnMosChickenInfoDao;
    }
    /**
     * trnMosChickenInfoDaoを設定
     * @param trnMosChickenInfoDao
     */
    public void setTrnMosChickenInfoDao(TrnMosChickenInfoDao trnMosChickenInfoDao) {
        this.trnMosChickenInfoDao = trnMosChickenInfoDao;
    }


}
