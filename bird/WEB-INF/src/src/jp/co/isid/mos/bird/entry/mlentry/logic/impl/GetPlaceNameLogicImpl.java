package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import jp.co.isid.mos.bird.entry.mlentry.dao.MstPlaceDao;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetPlaceNameLogic;

/**
 * 受験地名称取得ロジック
 * @author Aspac
 */
public class GetPlaceNameLogicImpl implements GetPlaceNameLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN008L10";

    
    /**
     * 受験希望地情報取得Dao
     */
    private MstPlaceDao mstPlaceDao;
    
    /**
     * 受験地名称を取得する
     */
    public String execute(String entryCd, String entryYear, String entryKai, String entryPlaceCd){
        
        String placeName = getMstPlaceDao().getPlaceName(entryCd, entryYear, entryKai, entryPlaceCd );
        
        return placeName;
        
    }

    public MstPlaceDao getMstPlaceDao() {
        return mstPlaceDao;
    }

    public void setMstPlaceDao(MstPlaceDao mstPlaceDao) {
        this.mstPlaceDao = mstPlaceDao;
    }
}
