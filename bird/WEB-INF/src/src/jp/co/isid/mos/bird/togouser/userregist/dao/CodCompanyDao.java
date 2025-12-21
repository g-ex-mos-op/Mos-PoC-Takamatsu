/*
 * ì¬“ú: 2008/11/11
 *
 */
package jp.co.isid.mos.bird.togouser.userregist.dao;

import java.util.List;
import jp.co.isid.mos.bird.togouser.userregist.entity.CodCompany;


/**
 * ‰ïĞƒŠƒXƒgDaoî•ñ
 * 
 * @author K.Nihonyanagi
 * 
 */
public interface CodCompanyDao {

    public static final Class BEAN = CodCompany.class;
    
    public List getCompany(String companyCd);
    
}
