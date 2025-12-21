package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;


import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.CondMosChickenTitleLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.MstMosChickenFromToDao;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
/**
 * モスチキン予約キャンペーン名取得ロジック
 * @author inazawa
 * 2009/09/19
 */

public class CondMosChickenTitleLogicImpl implements CondMosChickenTitleLogic{
    /*保有店舗取得Dao*/
    private MstMosChickenFromToDao mstMosChickenFromToDao;
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L03";
  
    public List execute(MosChickenRevInfoDto dto) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        
        //検索データを戻す。
        return titleSerch(dto);
    }

    private List titleSerch(MosChickenRevInfoDto dto) {
        java.util.List resultList = null;
        resultList = getMstMosChickenFromToDao().getTitle(dto.getSysDate());
        return resultList;
    }

    private void validate(MosChickenRevInfoDto dto) {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("モスチキン モスチキン予約 画面DTO");
        }
    }
    /**
     * mstMosChickenFromToDaoを戻す
     * @return mstMosChickenFromToDao
     */
    public MstMosChickenFromToDao getMstMosChickenFromToDao() {
        return mstMosChickenFromToDao;
    }
    /**
     * mstMosChickenFromToDaoを設定
     * @param mstMosChickenFromToDao
     */
    public void setMstMosChickenFromToDao(
            MstMosChickenFromToDao mstMosChickenFromToDao) {
        this.mstMosChickenFromToDao = mstMosChickenFromToDao;
    }

    
}
