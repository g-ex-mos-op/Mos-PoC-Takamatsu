/*
 * çÏê¨ì˙: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist2.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.CodMiseList;

/**
 * @author inazawa
 *
 */
public interface CodMiseListDao {
    
    public static final Class BEAN = CodMiseList.class;
    
    public static final String getMiseInfo_ARGS = "cKanriNo, reserveDt, miseCdFrom, miseCdTo";
    
    public List getMiseInfo(String cKanriNo, String reserveDt, String miseCdFrom, String miseCdTo);
}
