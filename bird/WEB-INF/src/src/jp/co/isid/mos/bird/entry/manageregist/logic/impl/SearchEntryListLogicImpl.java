package jp.co.isid.mos.bird.entry.manageregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistCommon;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistConstants;
import jp.co.isid.mos.bird.entry.manageregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.manageregist.logic.SearchEntryListLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 全国店長勉強会マスタ情報リスト取得ロジック
 *
 * @author xjung
 */
public class SearchEntryListLogicImpl implements SearchEntryListLogic {
    /** 全国店長勉強会マスタ情報リスト取得ロジックID */
    public static final String LOGIC_ID = "BEN020L01";

    /** エントリーマスタ管理Dao */
    UIEntryMstDao uiEntryMstDao;

    /**
     * 全国店長勉強会マスタ情報リストを取得する
     * @param  sysDate システム日付
     * @return 全国店長勉強会マスタ情報リスト
     */
    public List execute(String sysDate) {
        //入力必須チェックを行う
        validate(sysDate);

        // 全国店長勉強会マスタ情報リスト取得
        List result = getUiEntryMstDao().selectManage(
                        ManageRegistConstants.ENTRY_CD_MANAGE,
                        getPreviousYear(sysDate));

        return result;
    }

    /**
     * 入力必須チェックする
     * @param sysDate システム日付
     */
    private void validate(String sysDate) {
        // システム日付
        if (ManageRegistCommon.isNull(sysDate)) {
            throw new NotNullException(ManageRegistConstants.MSG_SYS_DATE);
        }
    }
    
    /**
     * 渡された日付の前年度の年を取得する
     * @param date 日付
     * @return 渡された日付の前年度の年
     */
    private String getPreviousYear(String date) {
        try {
            return DateManager.getPrevYear(DateManager.getCurrentYear(date), 1);
        } catch (Exception e) {
            throw new FtlSystemException(ManageRegistConstants.MSG_ERR_PREV_YEAR);
        }
    }

    /**
     * エントリーマスタ管理Daoを取得する
     * @return エントリーマスタ管理Dao
     */
    public UIEntryMstDao getUiEntryMstDao() {
        return uiEntryMstDao;
    }

    /**
     * エントリーマスタ管理Daoを設定する
     * @param uiEntryMstDao エントリーマスタ管理Dao
     */
    public void setUiEntryMstDao(UIEntryMstDao uiEntryMstDao) {
        this.uiEntryMstDao = uiEntryMstDao;
    }   
 }