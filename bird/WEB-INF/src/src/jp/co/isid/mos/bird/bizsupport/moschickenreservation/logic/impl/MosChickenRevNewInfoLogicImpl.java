package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.MstCMnueInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstCmenuInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevNewInfoLogic;

/**
 * モスチキン予約新規予約用マスタ登録商品取得ロジック
 * @author inazawa
 * 2006/09/19
 */
public class MosChickenRevNewInfoLogicImpl implements MosChickenRevNewInfoLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L07";
    private MstCMnueInfoDao mosChickenRevMstCMnueInfoDao;
    /**
     * マスタ登録商品取得ロジック
     */
    public List execute(MosChickenRevInfoDto dto){
        
        List resultList =  getMosChickenRevMstCMnueInfoDao().getMosChickenInfo(dto.getCkanriNo(),dto.getMiseCd(),dto.getCompanyCd());
        for(int i=0;resultList.size()>i;i++){
            MstCmenuInfo trnMosChikenDet = (MstCmenuInfo)resultList.get(i); 
            for(int j=i+1;resultList.size()>j;j++){
                MstCmenuInfo trnMosChikenDet2 = (MstCmenuInfo)resultList.get(j); 
                if(trnMosChikenDet2.getMenuCd().equals(trnMosChikenDet.getMenuCd())){
                    resultList.remove(j);
                    break;
                }
            }
        }
        return resultList;
    }
    /**
     * mosChickenRevMstCMnueInfoDaoを返す
     * @return mosChickenRevMstCMnueInfoDao
     */
    public MstCMnueInfoDao getMosChickenRevMstCMnueInfoDao() {
        return mosChickenRevMstCMnueInfoDao;
    }
    /**
     * mosChickenRevMstCMnueInfoDaoを設定
     * @param mosChickenRevMstCMnueInfoDao
     */
    public void setMosChickenRevMstCMnueInfoDao(
            MstCMnueInfoDao mosChickenRevMstCMnueInfoDao) {
        this.mosChickenRevMstCMnueInfoDao = mosChickenRevMstCMnueInfoDao;
    }
}
