/**
 * ì¬“ú: 2017/04/01
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.CodKbCompanyJoho;

/**
 * ‰ïĞî•ñ
 * @author Yuichi Tamura(ISID-AO)
 */
public interface CodKbCompanyJohoDao {

    public static final Class BEAN = CodKbCompanyJoho.class;

    /**
     * ‰ïĞî•ñ‚ÌŒŸõ
     * @return List
     */
	public List select();


}