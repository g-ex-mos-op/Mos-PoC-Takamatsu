package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.CodSMnueInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.StoreMenuAddLogic;
/**
 * 商品追加ロジック
 * @author inazawa
 * 2006/09/19
 */
    
public class StoreMenuAddLogicImpl implements StoreMenuAddLogic {
    /*商品塚取得Dao*/
    private CodSMnueInfoDao mosChickenRevCodSMnueInfoDao;
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L08";

    /**
     * 検索ロジック
     */
    public List exec(MosChickenRevInfoDto dto) {
        String addMenu = dto.getAddMenuText();
        dto.setAddMenuText('%' + addMenu + '%');
        List resultList = getMosChickenRevCodSMnueInfoDao().getMosChickenSmenu(dto.getCompanyCd(),dto.getMiseCd(),dto.getAddMenuText(),dto.getSysDate());
        dto.setAddMenuText(addMenu);
        return resultList;
    }
    /**
     * mosChickenRevCodSMnueInfoDaoを戻す
     * @return mosChickenRevCodSMnueInfoDao
     */
    public CodSMnueInfoDao getMosChickenRevCodSMnueInfoDao() {
        return mosChickenRevCodSMnueInfoDao;
    }
    /**
     * mosChickenRevCodSMnueInfoDaoを設定
     * @param mosChickenRevCodSMnueInfoDao
     */
    public void setMosChickenRevCodSMnueInfoDao(CodSMnueInfoDao mosChickenRevCodSMnueInfoDao) {
        this.mosChickenRevCodSMnueInfoDao = mosChickenRevCodSMnueInfoDao;
    }

}
