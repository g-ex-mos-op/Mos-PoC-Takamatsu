package jp.co.isid.mos.bird.entry.nationalregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.common.code.EntryCode;
import jp.co.isid.mos.bird.entry.nationalregist.common.NationalRegistCommon;
import jp.co.isid.mos.bird.entry.nationalregist.common.NationalRegistConstants;
import jp.co.isid.mos.bird.entry.nationalregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.nationalregist.logic.SearchEntryListLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 全国大会マスタ情報リスト取得ロジック
 *
 * @author xjung
 */
public class SearchEntryListLogicImpl implements SearchEntryListLogic {
    /** 全国大会マスタ情報リスト取得ロジックID */
    public static final String LOGIC_ID = "BEN014L01";

    /** エントリーマスタ管理Dao */
    UIEntryMstDao uiEntryMstDao;

    /**
     * 全国大会マスタ情報リストを取得する
     * @param  sysDate システム日付
     * @return 全国大会マスタ情報リスト
     */
    public List execute(String sysDate) {
        //入力必須チェックを行う
        validate(sysDate);

        // 全国大会マスタ情報リスト取得
        List result = getUiEntryMstDao().selectNational(
                        EntryCode.NATIONAL,
                        getPreviousYear(sysDate));

        return result;
    }

    /**
     * 入力必須チェックする
     * @param sysDate システム日付
     */
    private void validate(String sysDate) {
        // システム日付
        if (NationalRegistCommon.isNull(sysDate)) {
            throw new NotNullException(NationalRegistConstants.MSG_SYS_DATE);
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
            throw new FtlSystemException(NationalRegistConstants.MSG_ERR_PREV_YEAR);
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