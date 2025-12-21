package jp.co.isid.mos.bird.entry.mlentry.dao;

import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.entity.MstPlace;

/**
 * óŒ±Šó–]’nî•ñæ“¾
 * @author Aspac
 */
public interface MstPlaceDao {

    public static final Class BEAN = MstPlace.class;
    public static final String getPlaceList_ARGS = "entryCd, entryYear, entryKai";
    public static final String getPlaceName_ARGS = "entryCd, entryYear, entryKai, entryPlaceCd";



    /**
     * óŒ±Šó–]’nî•ñæ“¾ 
     * @return List
     */
    public List getPlaceList(String entryCd, String entryYear, String entryKai);
    
    
    /**
     * óŒ±Šó–]’n–¼Ìæ“¾
     * @return List
     */
    public String getPlaceName(String entryCd, String entryYear, String entryKai, String entryPlaceCd);
    
}