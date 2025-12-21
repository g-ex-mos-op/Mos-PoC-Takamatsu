/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.logic.impl;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.nationalentry.dao.UINatiLongServiceInfoDao;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiLongServiceInfo;
import jp.co.isid.mos.bird.entry.nationalentry.logic.LongServiceInfoLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 店舗情報取得ロジック
 * 
 * @author xlee
 */
public class LongServiceInfoLogicImpl implements LongServiceInfoLogic{

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN015L05";

    /**
     * 店舗情報DAOを取得します。
     */
    private UINatiLongServiceInfoDao uiNatiLongServiceInfoDao;
    
    /**
     * 店舗情報DAOを取得します。
     * @return 店舗情報DAO
     */
    public UINatiLongServiceInfoDao getUINatiLongServiceInfoDao() {
        return uiNatiLongServiceInfoDao;
    }

    /**
     * 店舗情報DAOを設定します。
     * @param uiMiseInfoDao 店舗情報DAO
     */
    public void setUINatiLongServiceInfoDao(UINatiLongServiceInfoDao uiNatiLongServiceInfoDao) {
        this.uiNatiLongServiceInfoDao = uiNatiLongServiceInfoDao;
    }
    
    /**
     * 永年勤続申込情報
     * @param kbn 処理区分
     * @param entryYear エントリー年
     * @param sysDate システム日付
     * @param userTypeCd ユーザタイプ
     * @return 永年勤続申込情報
     */
    
    public UINatiLongServiceInfo execute(String entryYear, String sysDate, String userTypeCd) {
    	
    	//エラー処理：
    	if(CommonUtil.isNull(entryYear)){
            throw new NotNullException("エントリー年"); //E0506 会社コード
        }
    	if(CommonUtil.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
    	if(CommonUtil.isNull(userTypeCd)){
            throw new NotNullException("ユーザタイプ"); //E0506 オーナーコード
        }
    	UINatiLongServiceInfo uiNatiLongServiceInfo = 
			getUINatiLongServiceInfoDao().getLongServiceInfo(entryYear, sysDate, userTypeCd);
    	
    	return uiNatiLongServiceInfo;
    }
}
