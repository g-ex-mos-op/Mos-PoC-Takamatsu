package jp.co.isid.mos.bird.entry.manageregist.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistCommon;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistConstants;
import jp.co.isid.mos.bird.entry.manageregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.manageregist.dao.UIEntrySelectionDao;
import jp.co.isid.mos.bird.entry.manageregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 全国店長勉強会マスタ情報取得ロジック
 *
 * @author xjung
 */
public class SearchEntryLogicImpl implements SearchEntryLogic {
    /** 全国店長勉強会マスタ情報取得ロジックID */
    public static final String LOGIC_ID = "BEN020L02";
    
    /** エントリー日付管理Dao */
    private UIEntryDateDao uiEntryDateDao;

    /** エントリーセレクション管理Dao */
    private UIEntrySelectionDao uiEntrySelectionDao;

    /**
     * 全国店長勉強会マスタ情報を取得する。
     * @param entryCd       エントリーコード
     * @param entryYear     エントリー年
     * @param entryKai      エントリー回
     * @param dateSize      日付サイズ
     * @param selectionSize セレクションサイズ
     * @return エントリー日付情報リスト、エントリーセレクション情報リスト
     */
    public Map execute(
        String entryCd,
        String entryYear,
        String entryKai,
        int dateSize,
        int selectionSize) {

        Map result = new HashMap();

        //入力必須チェックを行う
        validate(entryCd, entryYear, entryKai);

        // エントリー日付情報リスト取得
        List dateList = getUiEntryDateDao().select(entryCd, entryYear, entryKai);

        // 日付情報が取得できなかった場合、又は取得件数≠日付サイズの場合
        if (dateList == null || dateList.size() != dateSize) {
            throw new FtlSystemException(ManageRegistConstants.MSG_ERR_DATE_INFO);
        }

        // 日付情報リスト設定
        result.put(ManageRegistConstants.MAP_DATE_LIST, dateList);

        // エントリーセレクション情報リスト取得
        List selectionList = getUiEntrySelectionDao().select(entryCd, entryYear, entryKai);
 
        // セレクション情報が取得できなかった場合、又は取得件数≠セレクションサイズの場合
        if (selectionList == null || selectionList.size() != selectionSize) {
            throw new FtlSystemException(ManageRegistConstants.MSG_ERR_SELECTION_INFO);
        }
 
        // セレクション情報リスト設定
        result.put(ManageRegistConstants.MAP_SELECTION_LIST, selectionList);

        return result;
    }

    /**
     * 入力必須チェックする
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @param entryKai  エントリー回
     */
    private void validate(String entryCd, String entryYear, String entryKai) {
        // エントリーコード
        if (ManageRegistCommon.isNull(entryCd)) {
            throw new NotNullException(ManageRegistConstants.MSG_ENTRY_CD);
        }
        // エントリー年
        if (ManageRegistCommon.isNull(entryYear)) {
            throw new NotNullException(ManageRegistConstants.MSG_ENTRY_YEAR);
        }
        // エントリー回
        if (ManageRegistCommon.isNull(entryKai)) {
            throw new NotNullException(ManageRegistConstants.MSG_ENTRY_KAI);
        }
    }

    /**
     * エントリー日付管理Daoを取得する
     * @return エントリー日付管理Dao
     */
    public UIEntryDateDao getUiEntryDateDao() {
        return uiEntryDateDao;
    }

    /**
     * エントリー日付管理Daoを設定する
     * @param uiEntryDateDao エントリー日付管理Dao
     */
    public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
        this.uiEntryDateDao = uiEntryDateDao;
    }

    /**
     * エントリーセレクション管理Daoを取得する
     * @return エントリーセレクション管理Dao
     */
    public UIEntrySelectionDao getUiEntrySelectionDao() {
        return uiEntrySelectionDao;
    }

    /**
     * エントリーセレクション管理Daoを設定する
     * @param uiEntrySelectionDao エントリーセレクション管理Dao
     */
    public void setUiEntrySelectionDao(UIEntrySelectionDao uiEntrySelectionDao) {
        this.uiEntrySelectionDao = uiEntrySelectionDao;
    }
}