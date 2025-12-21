package jp.co.isid.mos.bird.entry.mlentry.logic.impl;


import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.dao.MstPlaceDao;
import jp.co.isid.mos.bird.entry.mlentry.entity.MstPlace;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetPlaceListLogic;

/**
 * 受験地情報取得ロジック
 * @author Aspac
 */
public class GetPlaceListLogicImpl implements GetPlaceListLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN008L09";

    
    /**
     * 受験希望地情報取得Dao
     */
    private MstPlaceDao mstPlaceDao;
    
    /**
     * 受験地情報を取得する
     */
    public List execute(String entryCd, String entryYear, String entryKai){
        
        List listPlace = getMstPlaceDao().getPlaceList(entryCd, entryYear, entryKai);
        
        // 一行目に「希望地を選択して下さい。」を追加
        MstPlace entity = new MstPlace();
        entity.setPlaceCd("");
        entity.setPlaceName("希望地を選択して下さい。");
        listPlace.add(0, entity);
        
        return listPlace;
        
    }

    public MstPlaceDao getMstPlaceDao() {
        return mstPlaceDao;
    }

    public void setMstPlaceDao(MstPlaceDao mstPlaceDao) {
        this.mstPlaceDao = mstPlaceDao;
    }
}
