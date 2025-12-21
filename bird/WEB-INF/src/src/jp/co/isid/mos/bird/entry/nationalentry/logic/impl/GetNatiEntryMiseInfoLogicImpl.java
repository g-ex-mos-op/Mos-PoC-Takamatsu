/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.common.dao.MstEntryMiseInfoDao;
import jp.co.isid.mos.bird.entry.common.entity.MstEntryMiseInfo;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntryMiseInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public class GetNatiEntryMiseInfoLogicImpl implements GetNatiEntryMiseInfoLogic{

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN015L01";

    /**
     * 店舗情報DAOを取得します。
     */
    private MstEntryMiseInfoDao mstEntryMiseInfoDao;

    /**
     * 店舗情報DAOを取得します。
     * @return 店舗情報DAO
     */
    public MstEntryMiseInfoDao getMstEntryMiseInfoDao() {
        return mstEntryMiseInfoDao;
    }

    /**
     * 店舗情報DAOを設定します。
     * @param uiMiseInfoDao 店舗情報DAO
     */
    public void setMstEntryMiseInfoDao(MstEntryMiseInfoDao mstEntryMiseInfoDao) {
        this.mstEntryMiseInfoDao = mstEntryMiseInfoDao;
    }

    
    /**
     * 店舗情報を取得
     * @param companyCd 会社コード
     * @param　onerCd オーナーコード
     * @param sysDate システム日付
     * @return  店舗リスト
     */
    
    public List execute(String companyCd, String onerCd, String sysDate) {
    	
    	//エラー処理：
    	if(CommonUtil.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
    	if(CommonUtil.isNull(companyCd)){
            throw new NotNullException("会社コード"); //E0506 会社コード
        }
    	if(CommonUtil.isNull(onerCd)){
            throw new NotNullException("オーナーコード"); //E0506 オーナーコード
        }
    	
    	List tmpResult = getMstEntryMiseInfoDao().getEntryMiseInfo(companyCd, onerCd, sysDate);

		if (tmpResult == null || tmpResult.size() == 0) {
    		throw new NotExistException("対象店舗", "prmDutyName", 0); //E0103 店舗情報
        }
    	//空白Ｏｐｔｉｏｎの生成
		List pullDownList = new ArrayList();
		for(int i = 0; i < tmpResult.size(); i++) {
			MstEntryMiseInfo mstEntryMiseInfo = (MstEntryMiseInfo) tmpResult.get(i);
			if(i == 0) {
				pullDownList.add(new SelectItem("", ""));
			}
			pullDownList.add(new SelectItem(mstEntryMiseInfo.getMiseCd(), 
					mstEntryMiseInfo.getMiseCd() + "　" + mstEntryMiseInfo.getMiseNameKj()));
		}
        return pullDownList;
    }
}
