package jp.co.isid.mos.bird.bizreport.common.logic.impl;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import jp.co.isid.mos.bird.bizreport.common.logic.MstSibuInfoLogic;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.dao.MstSibuInfoDao;
import jp.co.isid.mos.bird.bizreport.common.entity.MstSibuInfo;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 対象支部情報取得共通ロジック
 * 
 * @author xjung
 */
public class MstSibuInfoLogicImpl implements MstSibuInfoLogic{
    /** 対象支部情報取得共通ロジックID */
    public static final String LOGIC_ID = "BBR000L05";

	/** 対象支部情報取得Dao */
    private MstSibuInfoDao mstSibuInfoDao;

    /**
     * 対象支部情報を取得する
     * @param companyCd 会社コード
     * @param limitFlg	 制限区分
     * @param userId    ユーザID
     * @param shukeiKbn 集計区分
     * @return List     対象支部情報
     */
    public List execute(
		String companyCd,
		boolean limitFlg,
		String userId,
		String shukeiKbn) {

    	// 会社コード入力チェック
		if (companyCd == null || companyCd.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_COMPANY_CD);
		}
		// ユーザID入力チェック
		if (userId == null || userId.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_USER_ID);
		}
		// 集計区分入力チェック
		if (userId == null || shukeiKbn.trim().length() == 0) {
			throw new NotNullException(BizReportConstants.MSG_SHUKEI_KBN);
		}

		// 対象支部情報取得
        List sibuList = getMstSibuInfoDao().selectSibuInfo(
			        		companyCd,
			        		limitFlg,
			        		userId,
			        		shukeiKbn);

        // 検索結果が０件の場合
        if (sibuList == null || sibuList.isEmpty()) {
            throw new NotExistException(BizReportConstants.MSG_TAISHO_SIBU);
        }

        // プルダウンリスト作成
        List returnList = new ArrayList();
        for (int i = 0; i < sibuList.size(); i++) {
            MstSibuInfo sibu = (MstSibuInfo) sibuList.get(i);
            SelectItem item = new SelectItem(
                sibu.getSibuCd().trim(), 
                sibu.getSibuName().trim());            
            returnList.add(item);                    
        }

        return returnList;
    }

    /**
     * 対象支部情報取得Daoを取得する
     * @return MstSibuInfoDao 対象支部情報取得Dao
     */
    public MstSibuInfoDao getMstSibuInfoDao() {
        return this.mstSibuInfoDao;
    }

    /**
     * 対象支部情報取得Dao
     * @param mstSibuInfoDao 対象支部情報取得Dao
     */
    public void setMstSibuInfoDao(MstSibuInfoDao mstSibuInfoDao) {
        this.mstSibuInfoDao = mstSibuInfoDao;
    }
}