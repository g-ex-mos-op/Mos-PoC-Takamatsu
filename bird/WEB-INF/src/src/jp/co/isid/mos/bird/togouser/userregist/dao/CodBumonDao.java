/*
 * ì¬“ú: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.userregist.dao;

import java.util.List;
import jp.co.isid.mos.bird.togouser.userregist.entity.CodBumon;

/**
 * •”–åƒŠƒXƒgDAO
 * @author K.Nihonyanagi
 */
public interface CodBumonDao {

    public static final Class BEAN = CodBumon.class;
    
    public List getBumonList();
}
