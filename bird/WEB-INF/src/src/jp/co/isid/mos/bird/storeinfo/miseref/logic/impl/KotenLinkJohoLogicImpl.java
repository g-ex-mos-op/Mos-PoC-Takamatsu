package jp.co.isid.mos.bird.storeinfo.miseref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.CodKotenLinkJohoDao;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.KotenLinkJohoLogic;

/**
 * 個店リンク情報の取得ロジック インターフェイス
 * @author xnkusama
 */
public class KotenLinkJohoLogicImpl implements KotenLinkJohoLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BSI001L03";

    private CodKotenLinkJohoDao codKotenLinkJohoDao;
    
    /**
     * 個店リンク情報の取得を行う
     * @param String userId ユーザーID
     * @return List  
     * @exception ApplicationException
     */
    public List execute(String userId) throws ApplicationException {
    	validate(userId);
    	
    	return getCodKotenLinkJohoDao().selectKotenLink(userId);
    }
	public CodKotenLinkJohoDao getCodKotenLinkJohoDao() {
		return codKotenLinkJohoDao;
	}
	public void setCodKotenLinkJohoDao(CodKotenLinkJohoDao codKotenLinkJohoDao) {
		this.codKotenLinkJohoDao = codKotenLinkJohoDao;
	}
	
	private void validate(String userId) {
		if (userId == null || "".equals(userId.trim())) {
			throw new NotNullException("ユーザーID");
		}
	}
}