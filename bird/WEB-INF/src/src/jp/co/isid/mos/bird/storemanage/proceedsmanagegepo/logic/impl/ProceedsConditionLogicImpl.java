package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.kaikei.dao.CtlSyukeiKbnDao;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.UserCompanyInfoDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.ProceedsConditionLogic;

/**
 * 売上金管理月報の条件部取得ロジック
 *
 * @author xjung
 */
public class ProceedsConditionLogicImpl implements ProceedsConditionLogic {

    /** 売上金管理月報の条件部取得ロジックID */
    public static final String LOGIC_ID = "BSM013L01";

    /** 会社情報取得ロジック */
    private UserCompanyInfoDao userCompanyInfoDao;
    
    /** 会計集計区分情報Dao */
    private CtlSyukeiKbnDao ctlSyukeiKbnDao;

    /**
     * 条件部情報を取得する
     * @param userId	ユーザID
     * @param appDate	アプリ日付
     * @return Map     条件部情報
     */
    public Map execute(String userId, String appDate) {
		Map map = new HashMap();

        //入力チェック
        validate(userId, appDate);

		// 会社プルダウン取得
        List companyList = getUserCompanyInfoDao().select(userId);

        // 検索結果が０件の場合
        if (companyList == null || companyList.isEmpty()) {
            throw new NotExistException(ProceedsConstants.MSG_COMPANY_INFO);
        }
        map.put(ProceedsConstants.MAP_COMPANY_LIST, companyList);
 
        // 対象年月プルダウン取得
        map.put(ProceedsConstants.MAP_TAISHO_YM_LIST, getYearMonthList(appDate));
        
        // 集計区分情報取得(表示対象なし)
        List tempSyukeiKbnList = getCtlSyukeiKbnDao().selectAll();

        List syukeiKbnList = new ArrayList();
        for(int i = 2; i < 12; i++) {
            syukeiKbnList.add(getSyukeiKbn(i, tempSyukeiKbnList));
        }
        
        map.put(ProceedsConstants.MAP_SK_LIST, syukeiKbnList);

        return map;
		
	}

    /**
     * 必須入力チェックを行う
     * @param userId    ユーザID
     * @param appDate   アプリ日付
     */
    private void validate(String userId, String appDate) {
        if (userId == null || userId.trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_USER_ID);
        }
        if (appDate == null || appDate.trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_APP_DATE);
        }
    }

    /**
     * 対象年月プルダウンを取得する
     * @param  appDate  アプリ日付
     * @return List     年月リスト
     */
    private static List getYearMonthList(String appDate) {
        // 日付フォーマッタ
        DateFormatter formatter = new DateFormatter();

        // 年月リスト
        List yyyyMMList = new ArrayList();
 
        // アプリ日付の年月      
        String appMonth = formatter.format
            (appDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
  
        // From年月
        String fromMonth = ProceedsConstants.EMPTY;
        try {
            fromMonth = DateManager.getPrevMonth(appMonth, ProceedsConstants.DISPLAY_DAY_MONTH);
        } catch(Exception ex ) {
            throw new FtlSystemException(ProceedsConstants.MSG_CONDITION_ERR);
        }

        // 年月リスト
        String yyyyMM = ProceedsConstants.EMPTY;
        int i = 0;
        while(true) {
            try {
                // 現在から指定月前の日付を取得(yyyyMM)
                yyyyMM = DateManager.getPrevMonth(appMonth, i);
            }catch (Exception ex) {
                throw new FtlSystemException(ProceedsConstants.MSG_CONDITION_ERR);
            }
 
            // 指定した年月の年度を取得し、From年度と一致した場合
            if (yyyyMM.equals(fromMonth)) {
                break;
            }

            SelectItem item = new SelectItem(yyyyMM, formatter.format(yyyyMM,
                DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM));

            yyyyMMList.add(item);
            yyyyMM = ProceedsConstants.EMPTY;
            i++;
        }

        return yyyyMMList;
    }
    

    /**
     * 集計区分名称を設定する
     * @param num            番号
     * @param syukeiInfoList 集計区分名称リスト
     * @return 集計区分
     */
    private CtlSyukeiKbn getSyukeiKbn(int num, List syukeiInfoList) {
        CtlSyukeiKbn info = null;
        String srtNum = ProceedsCommon.getStringNum(num, 2);
        if (syukeiInfoList != null && !syukeiInfoList.isEmpty()) {
            for (Iterator it = syukeiInfoList.iterator(); it.hasNext();) {
                info = (CtlSyukeiKbn)(it.next());
                if (srtNum.equals(info.getSyukeiCd())) {
                    info.setSyukeiName(info.getSyukeiName().trim());
                    return info;
                }
            }
        }
        
        // 存在しない場合は作成する
        info = new CtlSyukeiKbn();
        info.setSyukeiCd(srtNum);
        info.setSyukeiName(ProceedsConstants.LABEL_SYUKEI + String.valueOf(num));
        return info;
    }

    /**
     * 会社情報取得ロジックを取得します
     * @return UserCompanyInfoDao 会社情報取得ロジック
     */
    public UserCompanyInfoDao getUserCompanyInfoDao() {
		return userCompanyInfoDao;
	}

    /**
     * 会社情報取得ロジックを設定します
     * @param userCompanyInfoDao 会社情報取得ロジック
     */
    public void setUserCompanyInfoDao(UserCompanyInfoDao userCompanyInfoDao) {
		this.userCompanyInfoDao = userCompanyInfoDao;
	}
    
    /**
     * 会計集計区分情報Daoを取得する
     * @return 会計集計区分情報Dao
     */
    public CtlSyukeiKbnDao getCtlSyukeiKbnDao() {
        return ctlSyukeiKbnDao;
    }

    /**
     * 会計集計区分情報Daoを設定する
     * @param mstAccountKbnInfoDao 会計集計区分情報Dao
     */
    public void setCtlSyukeiKbnDao(CtlSyukeiKbnDao ctlSyukeiKbnDao) {
        this.ctlSyukeiKbnDao = ctlSyukeiKbnDao;
    }
    
}