package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevCancelLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.TrnMosChickenInfoDao;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
/**
 * モスチキン予約情報キャンセル/予約キャンセル取消ロジック
 * @author inazawa
 * 2006/09/19
 */
public class MosChickenRevCancelLogicImpl implements MosChickenRevCancelLogic {
    /*キャンセルフラグ0：未、1：済、2:新規登録*/
    public static final String cancelZumi = "1";
    public static final String cancelNot  = "0";
    /* VIEW_ID */
    public static final String VIEW_ID            = "BBS016";
    public static final String condition_VIEW_ID  = "BBS016V01";
    public static final String result_VIEW_ID     = "BBS016V02";
    public static final String menuAdd_VIEW_ID    = "BBS016V03";    
    /*Dao[[キャンセル状況更新Dao]]]*/
    TrnMosChickenInfoDao trnMosChickenInfoDao;
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L04";
    /**
     * 予約キャンセル／キャンセル取消し
     */
    public void cancel(MosChickenRevInfoDto dto){
        DateFormatter formatter = new DateFormatter();
        TrnMosChikenInfo trnMosChikenInfo = ((TrnMosChikenInfo)dto.getListMosChiCkenInfo().get(dto.getRevIndex()));
        if(dto.getCancelFlg().equals(cancelNot)){
            trnMosChikenInfo.setCancelFlg(cancelZumi);
            trnMosChikenInfo.setCancelDt(formatter.format(String.valueOf(dto.getSysDate()),DateFormatter.PATTERN_NORMAL,DateFormatter.DATE_TYPE_YMD));
        }else if(dto.getCancelFlg().equals(cancelZumi)){
            trnMosChikenInfo.setCancelFlg(cancelNot);
            trnMosChikenInfo.setCancelDt("");
        }
        trnMosChikenInfo.setLastUser(dto.getBirdUserInfo().getUserID());
        trnMosChikenInfo.setLastPgm(VIEW_ID);
        trnMosChikenInfo.setLastTmsp(DateManager.getCurrentTimestamp());
        getTrnMosChickenInfoDao().updateMosChickenInfoCancel(trnMosChikenInfo);
        if(trnMosChikenInfo.getCancelFlg().equals(cancelZumi)){
            trnMosChikenInfo.setCancelDt(formatter.format(String.valueOf(dto.getSysDate()),DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD));
        }
    }
    /**
     * trnMosChickenInfoDaoを返す
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
