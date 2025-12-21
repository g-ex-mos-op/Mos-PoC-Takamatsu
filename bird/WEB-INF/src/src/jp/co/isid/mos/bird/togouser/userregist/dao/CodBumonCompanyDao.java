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
public interface CodBumonCompanyDao {

    public static final Class BEAN = CodBumon.class;
    public static final String select_ARGS = "R_COMPANY_CD";
    
    public List getBumonListCompany(String R_COMPANY_CD);
}
