/*
 * 作成日: 2006/04/04
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.MSTUserMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.MSTUserOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MSTUserOnerInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetUserOnerLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
/**
 * オーナー一覧取得ロジック
 * @author xnkusama
 */
public class GetUserOnerLogicImpl implements GetUserOnerLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS006L01";
    /* DAO */
    private MSTUserMiseInfoDao mstUserMiseInfoDao;
    private MSTUserOnerInfoDao mstUserOnerInfoDao;
    
    /**
     * オーナー一覧を取得する
     * @param String 企業コード
     * @param String ユーザーID
     * @param String システム日付
     * @return List オーナー一覧
     * @exception ApplicationException
     */
    public List execute(String companyCd, String userId, String sysDate) throws ApplicationException {
        validate(companyCd, userId);
        
        List listMiseAll = new ArrayList();
        
        // ユーザー対応オーナー
        List listOner = getMstUserOnerInfoDao().getOnerCd(companyCd, userId);
        if (listOner == null || listOner.size() == 0) {
            throw new FtlSystemException("ユーザー対応オーナーコード取得");
        }
        
        // ユーザー対応店舗
        for (Iterator ite = listOner.iterator(); ite.hasNext();) {
            MSTUserOnerInfo mstUserOnerInfo = (MSTUserOnerInfo) ite.next();
            String onerCd = mstUserOnerInfo.getOnerCd();
            List listMise = getMstUserMiseInfoDao().getMiseCd(companyCd, onerCd, sysDate);
            
            for (Iterator ite2 = listMise.iterator(); ite2.hasNext();) {
                listMiseAll.add(ite2.next());
            }
        }
        return listMiseAll;
    }
    
    private void validate(String companyCd, String userId) {
        if (isNull(companyCd)) {
            throw new NotNullException("企業コード");
        }
        if (isNull(userId)) {
            throw new NotNullException("ユーザーID");
        }
    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
   
	public MSTUserMiseInfoDao getMstUserMiseInfoDao() {
		return mstUserMiseInfoDao;
	}
	public void setMstUserMiseInfoDao(MSTUserMiseInfoDao mstUserMiseInfoDao) {
		this.mstUserMiseInfoDao = mstUserMiseInfoDao;
	}
	public MSTUserOnerInfoDao getMstUserOnerInfoDao() {
		return mstUserOnerInfoDao;
	}
	public void setMstUserOnerInfoDao(MSTUserOnerInfoDao mstUserOnerInfoDao) {
		this.mstUserOnerInfoDao = mstUserOnerInfoDao;
	}
}