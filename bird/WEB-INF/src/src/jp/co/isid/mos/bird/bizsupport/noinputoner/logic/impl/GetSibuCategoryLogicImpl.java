/*
 * 作成日: 2006/03/24
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.dao.MSTSibuCategoryInfoDao;
import jp.co.isid.mos.bird.bizsupport.noinputoner.logic.GetSibuCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 支部の種類を取得ロジック インターフェイス
 * @author xnkusama
 */
public class GetSibuCategoryLogicImpl implements GetSibuCategoryLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BBS004L02";
    
    private MSTSibuCategoryInfoDao mstSibuCategoryInfoDao;
    
    /**
     * 支部の種類を取得
     * @param String 企業コード
     * @return List 支部データ
     * @exception ApplicationException
     */
    public List execute(String companyCd) throws ApplicationException {
        validate(companyCd);
        
        List listSibu = getMstSibuCategoryInfoDao().getSibuInfo(companyCd);
        
        if (listSibu == null || listSibu.size() == 0) {
            throw new NotExistException("支部情報");
        }
        return listSibu;
    }
    
    private void validate(String companyCd) {
        if (isNull(companyCd)) {
            throw new NotNullException("企業コード");
        }
    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
	public MSTSibuCategoryInfoDao getMstSibuCategoryInfoDao() {
		return mstSibuCategoryInfoDao;
	}
	public void setMstSibuCategoryInfoDao(
			MSTSibuCategoryInfoDao mstSibuCategoryInfoDao) {
		this.mstSibuCategoryInfoDao = mstSibuCategoryInfoDao;
	}
}