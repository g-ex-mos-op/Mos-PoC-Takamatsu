/*
 * ì¬“ú: 2008/11/10
 */
package jp.co.isid.mos.bird.togouser.userregist.dao;

import jp.co.isid.mos.bird.togouser.userregist.entity.CodBumon;

/**
 * •”–åƒŠƒXƒgDAO
 * @author K.Nihonyanagi
 */
public interface CodBumonNameDao {

    public static final Class BEAN = CodBumon.class;
    
    public String getBumon(String bumon_cd);
}
