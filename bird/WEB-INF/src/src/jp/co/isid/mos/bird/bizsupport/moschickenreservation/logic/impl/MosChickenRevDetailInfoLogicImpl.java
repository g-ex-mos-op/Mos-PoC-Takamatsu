package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.TrnMosChickenDetailInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenDet;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevDetailInfoLogic;
;
/**
 * モスチキン予約明細情報取得ロジック
 * @author inazawa
 * 2006/09/19
 */
public class MosChickenRevDetailInfoLogicImpl implements MosChickenRevDetailInfoLogic {
    private TrnMosChickenDetailInfoDao trnMosChickenDetailInfoDao;  
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L05";
    /*キャンセルフラグ0：未、1：済、2:新規登録*/
    public static final String cancelZumi = "1";
    /**
     * モスチキン予約明細情報取得処理
     */
    public List execute(MosChickenRevInfoDto dto){
        int index = dto.getRevIndex();
        TrnMosChikenInfo  entity = (TrnMosChikenInfo) dto.getListMosChiCkenInfo().get(index);
        dto.setSeqNo(entity.getSeqNo());
        List resultList = getTrnMosChickenDetailInfoDao().getMosChickenDetInfo(entity.getCkanriNo(),entity.getCompanyCd(),entity.getMiseCd(),entity.getSeqNo());
        List resultMstList = getTrnMosChickenDetailInfoDao().getMosChickenMstMenuDetInfo(entity.getCkanriNo(),entity.getCompanyCd(),entity.getMiseCd(),entity.getSeqNo());

        ArrayList deleteList = new ArrayList(); 
        for(int i = 0;resultList.size()>i;i++){
            TrnMosChikenDet allList = (TrnMosChikenDet) resultList.get(i);
            for(int j = 0;resultMstList.size()>j;j++){
                TrnMosChikenDet mstList = (TrnMosChikenDet) resultMstList.get(j);
                if(allList.getMenuCd().equals(mstList.getMenuCd())){
                    deleteList.add(mstList);
                    break;
                }
            }
        }
        for(int i=0;deleteList.size()>i;i++){
            TrnMosChikenDet addList = (TrnMosChikenDet) deleteList.get(i);
            for(int j = 0;resultList.size()>j;j++){
                TrnMosChikenDet mstList = (TrnMosChikenDet) resultList.get(j);
                if(addList.getMenuCd().equals(mstList.getMenuCd())){
                    resultList.remove(j);
                }
            }
        }
        for(int i=0;resultList.size()>i;i++){
            TrnMosChikenDet addList = (TrnMosChikenDet) resultList.get(i);
            resultMstList.add(addList);
        }
        if(entity.getCancelFlg().equals(cancelZumi)){
            dto.setReserveYYMM(entity.getReserveHh()+"時"+entity.getReserveMm()+"分");
        }
        dto.setCancelFlg(entity.getCancelFlg());
        for(int i = 0;resultMstList.size()>i;i++){
            TrnMosChikenDet trnMosChikenDet  = (TrnMosChikenDet)resultMstList.get(i);
            String menuCd = trnMosChikenDet.getMenuCd();
            for(int j = i+1;resultMstList.size()>j;j++){
                TrnMosChikenDet repTrnMosChikenDet  = (TrnMosChikenDet)resultMstList.get(j);
                String repMenuCd = repTrnMosChikenDet.getMenuCd();
                if(menuCd.equals(repMenuCd)){
                    resultMstList.remove(j);
                }
            }
        }
        return resultMstList;
    }
    /**
     * trnMosChickenDetailInfoDaoを戻す
     * @return trnMosChickenDetailInfoDao
     */
    public TrnMosChickenDetailInfoDao getTrnMosChickenDetailInfoDao() {
        return trnMosChickenDetailInfoDao;
    }
    /**
     * trnMosChickenDetailInfoDaoを設定
     * @param trnMosChickenDetailInfoDao
     */
    public void setTrnMosChickenDetailInfoDao(
            TrnMosChickenDetailInfoDao trnMosChickenDetailInfoDao) {
        this.trnMosChickenDetailInfoDao = trnMosChickenDetailInfoDao;
    }
}
