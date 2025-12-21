package jp.co.isid.mos.bird.entry.manageregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.entry.manageregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.manageregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.manageregist.entity.UIEntrySelection;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistConstants;
import jp.co.isid.mos.bird.entry.manageregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.manageregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.manageregist.dao.UIEntrySelectionDao;
import jp.co.isid.mos.bird.entry.manageregist.dto.ManageRegistDto;
import jp.co.isid.mos.bird.entry.manageregist.logic.UpdateEntryLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 全国店長勉強会マスタ情報登録ロジック
 *
 * @author xjung
 */
public class UpdateEntryLogicImpl implements UpdateEntryLogic {
    /** 全国店長勉強会マスタ情報登録ロジックID */
    public static final String LOGIC_ID = "BEN020L04";

    /** エントリーマスタ管理Dao */
    private UIEntryMstDao uiEntryMstDao;

    /** エントリー日付管理Dao */
    private UIEntryDateDao uiEntryDateDao;

    /** エントリーセレクション管理Dao */
    private UIEntrySelectionDao uiEntrySelectionDao;

    /**
     * 全国店長勉強会マスタ情報を登録、更新する。
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    public void execute(ManageRegistDto dto) {
        // 新規登録
        if(ManageRegistConstants.EDIT_MODE_INSERT == dto.getEditMode()) {
            executeInsert(dto);
        // 編集(更新)
        } else if(ManageRegistConstants.EDIT_MODE_UPDATE == dto.getEditMode()) {
            executeUpdate(dto);
        // 削除(更新)
        } else if(ManageRegistConstants.EDIT_MODE_DELETE == dto.getEditMode()){
            executeDelete(dto);
        }        
    }

    /**
     * 全国店長勉強会マスタ登録情報の新規登録を行う。
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    private void executeInsert(ManageRegistDto dto) {
        // エントリー年 = 開催開始日の年度
        String entryYear = createEntryYear(dto.getUiEntryMst().getOpenFrom());
        // エントリー回取得
        String entryKai = createEntryKai(ManageRegistConstants.ENTRY_CD_MANAGE, entryYear);
        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // ロジックID
        String logicId = LOGIC_ID.substring(0, 7);

        /* 全国店長勉強会マスタ情報登録 */
        UIEntryMst uiEntryMst = dto.getUiEntryMst();
        // エントリーコード、年、回設定
        uiEntryMst.setEntryCd(ManageRegistConstants.ENTRY_CD_MANAGE);
        uiEntryMst.setEntryYear(entryYear);
        uiEntryMst.setEntryKai(entryKai);
        // 削除フラグ設定
        uiEntryMst.setSakujoFlg(ManageRegistConstants.SAKUJO_FLG_OFF);
        // 登録ユーザ、プログラム、タイムスタンプ、修正ユーザ、プログラム、タイムスタンプ設定
        uiEntryMst.setFirstUser(dto.getUserId());
        uiEntryMst.setFirstPgm(logicId);
        uiEntryMst.setFirstTmsp(currentTimestamp);
        uiEntryMst.setLastUser(dto.getUserId());
        uiEntryMst.setLastPgm(logicId);
        uiEntryMst.setLastTmsp(currentTimestamp);
        // 全国店長勉強会マスタ情報の新規登録
        getUiEntryMstDao().insert(uiEntryMst);

        /* 全国店長勉強会日付情報登録 */
        UIEntryDate uiEntryDate = new UIEntryDate();
        // エントリーコード、年、回
        uiEntryDate.setEntryCd(ManageRegistConstants.ENTRY_CD_MANAGE);
        uiEntryDate.setEntryYear(entryYear);
        uiEntryDate.setEntryKai(entryKai);
        // 登録ユーザ、プログラム、タイムスタンプ、修正ユーザ、プログラム、タイムスタンプ設定
        uiEntryDate.setFirstUser(dto.getUserId());
        uiEntryDate.setFirstPgm(logicId);
        uiEntryDate.setFirstTmsp(currentTimestamp);
        uiEntryDate.setLastUser(dto.getUserId());
        uiEntryDate.setLastPgm(logicId);
        uiEntryDate.setLastTmsp(currentTimestamp);

        // 開催日登録
        insertEntryDate(uiEntryDate, ManageRegistConstants.USERTYPE_CD_ETC,
            ManageRegistConstants.DAY_KBN_OPEN, uiEntryMst.getOpenFrom(), uiEntryMst.getOpenTo());

        // オーナー：表示日登録
        insertEntryDate(uiEntryDate, ManageRegistConstants.USERTYPE_CD_ONER,
            ManageRegistConstants.DAY_KBN_DISP, uiEntryMst.getOnerDispFrom(), uiEntryMst.getOnerDispTo());

        // オーナー：申込日登録
        insertEntryDate(uiEntryDate, ManageRegistConstants.USERTYPE_CD_ONER,
            ManageRegistConstants.DAY_KBN_ENTRY, uiEntryMst.getOnerEntryFrom(), uiEntryMst.getOnerEntryTo());

        // 本部：表示日登録
        insertEntryDate(uiEntryDate, ManageRegistConstants.USERTYPE_CD_HONBU,
            ManageRegistConstants.DAY_KBN_DISP, uiEntryMst.getHonbuDispFrom(), uiEntryMst.getHonbuDispTo());

        // 本部：申込日登録
        insertEntryDate(uiEntryDate, ManageRegistConstants.USERTYPE_CD_HONBU,
            ManageRegistConstants.DAY_KBN_ENTRY, uiEntryMst.getHonbuEntryFrom(), uiEntryMst.getHonbuEntryTo());

        /* 全国店長勉強会セレクション情報登録 */
        UIEntrySelection uiEntrySelection = new UIEntrySelection();
        // エントリーコード、年、回設定
        uiEntrySelection.setEntryCd(ManageRegistConstants.ENTRY_CD_MANAGE);
        uiEntrySelection.setEntryYear(entryYear);
        uiEntrySelection.setEntryKai(entryKai);
        // 登録ユーザ、プログラム、タイムスタンプ、修正ユーザ、プログラム、タイムスタンプ設定
        uiEntrySelection.setFirstUser(dto.getUserId());
        uiEntrySelection.setFirstPgm(logicId);
        uiEntrySelection.setFirstTmsp(currentTimestamp);
        uiEntrySelection.setLastUser(dto.getUserId());
        uiEntrySelection.setLastPgm(logicId);
        uiEntrySelection.setLastTmsp(currentTimestamp);

        // 申込区分登録
        insertEntrySelection(uiEntrySelection, dto.getEntryKbnList(),
            ManageRegistConstants.SEL_KBN_ENTRY);

        // オプショナル登録
        insertEntrySelection(uiEntrySelection, dto.getOptionalList(),
            ManageRegistConstants.SEL_KBN_OPTIONAL);
    }
    
    /**
     * 全国店長勉強会マスタ登録情報の更新を行う。
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    private void executeUpdate(ManageRegistDto dto) {
        // エントリー年 = 開催開始日の年度
        String entryYear = createEntryYear(dto.getUiEntryMst().getOpenFrom());
        // エントリー回取得
        String entryKai = createEntryKai(ManageRegistConstants.ENTRY_CD_MANAGE, entryYear);
        // ロジックID
        String logicId = LOGIC_ID.substring(0, 7);

        // 全国店長勉強会マスタ情報更新
        UIEntryMst uiEntryMst = dto.getUiEntryMst();
        // 修正ユーザ、プログラム設定
        uiEntryMst.setLastUser(dto.getUserId());
        uiEntryMst.setLastPgm(logicId);

        // 全国店長勉強会マスタ情報の更新
        if (entryYear.equals(uiEntryMst.getEntryYear())) {
            getUiEntryMstDao().update(uiEntryMst);
        } else {        
            int result = getUiEntryMstDao().updatePKey(uiEntryMst, entryYear, entryKai);
            // 排他チェック
            if (result == 0) {
                throw new CannotExecuteWithReasonException(
                    ManageRegistConstants.MSG_EXCLUSION_CHK,
                    ManageRegistConstants.MSG_UPDATE);
            }
        }

        // 全国店長勉強会日付情報更新
        updateEntryDate(dto.getDateInfoList(), uiEntryMst,
            dto.getUserId(), logicId, entryYear, entryKai);

        // 申込区分情報更新
        updateEntrySelection(dto.getEntryKbnList(),
            dto.getUserId(), logicId, entryYear, entryKai);

        // オプショナル情報更新
        updateEntrySelection(dto.getOptionalList(),
            dto.getUserId(), logicId, entryYear, entryKai);

    }

    /**
     * 全国店長勉強会マスタ登録情報の削除を行う。
     * @param dto 全国店長勉強会マスタ登録情報DTO
     */
    private void executeDelete(ManageRegistDto dto) {
        // 全国店長勉強会マスタ情報
        UIEntryMst uiEntryMst = dto.getUiEntryMst();
        // 修正ユーザ設定
        uiEntryMst.setLastUser(dto.getUserId());
        // 修正プログラム設定
        uiEntryMst.setLastPgm(LOGIC_ID.substring(0, 7));
        // 削除フラグ設定
        uiEntryMst.setSakujoFlg(ManageRegistConstants.SAKUJO_FLG_ON);
        // update
        getUiEntryMstDao().updateDeleteFlg(uiEntryMst);
    }

    /**
     * エントリー年を取得する。
     * @param openFromDt 開催開始日
     * @return エントリー年
     */
    private String createEntryYear(String openFromDt) {
        try {
            // 開催開始日の年度取得
            return DateManager.getCurrentYear(openFromDt);
        } catch (Exception e) {
            throw new FtlSystemException(ManageRegistConstants.MSG_ERR_ENTRY_YEAR);
        }
    }

    /**
     * エントリー回を生成する。
     * @param entryCd   エントリーコード
     * @param entryYear エントリー年
     * @return エントリー回
     */
    private String createEntryKai(String entryCd, String entryYear) {
        // コードフォーマッタ
        CodeFormatter formatter = new CodeFormatter(3);
        formatter.setFormatPattern(ManageRegistConstants.CF_ENTRY_KAI);

        // 最大エントリー回取得
        int maxEntryKai = getUiEntryMstDao().selectMaxEntryKai(entryCd, entryYear);

        // 最大エントリー回＋１
        return formatter.format(String.valueOf(maxEntryKai + 1), true);
    }

    /**
     * 全国店長勉強会日付情報を登録する。
     * @param uiEntryDate 全国店長勉強会日付情報
     * @param usertypeCd  ユーザタイプコード
     * @param dayKbn    　日付区分
     * @param fromDt    　開始日
     * @param toDt      　終了日
     */
    private void insertEntryDate(
        UIEntryDate uiEntryDate,
        String usertypeCd,
        String dayKbn,
        String fromDt,
        String toDt) {
        // ユーザタイプコード、日付、開始日、終了日設定
        uiEntryDate.setUsertypeCd(usertypeCd);
        uiEntryDate.setDayKbn(dayKbn);
        uiEntryDate.setFromDt(fromDt);
        uiEntryDate.setToDt(toDt);
        // 全国店長勉強会日付情報登録
        getUiEntryDateDao().insert(uiEntryDate);        
    }

    /**
     * 全国店長勉強会日付情報を更新する。
     * @param dateInfoList 全国店長勉強会日付情報リスト
     * @param uiEntryMst   全国店長勉強会マスタ情報
     * @param userId       ユーザID
     * @param logicId      ロジックID
     * @param entryYear    エントリー年
     * @param entryKai     エントリー回
     */
    private void updateEntryDate(
        List dateInfoList,
        UIEntryMst uiEntryMst,
        String userId,
        String logicId,
        String entryYear,
        String entryKai) {

        // 全国店長勉強会日付情報を更新
        for (int i = 0; i < dateInfoList.size(); i++) {
            UIEntryDate dateInfo = (UIEntryDate) dateInfoList.get(i);
            // 修正ユーザ、プログラム設定
            dateInfo.setLastUser(userId);
            dateInfo.setLastPgm(logicId);

            // 開催開始終了日設定
            if (ManageRegistConstants.USERTYPE_CD_ETC.equals(dateInfo.getUsertypeCd())
                && ManageRegistConstants.DAY_KBN_OPEN.equals(dateInfo.getDayKbn())) {
                dateInfo.setFromDt(uiEntryMst.getOpenFrom());
                dateInfo.setToDt(uiEntryMst.getOpenTo());
            // オーナー：表示開始終了日設定
            } else if (ManageRegistConstants.USERTYPE_CD_ONER.equals(dateInfo.getUsertypeCd())
                && ManageRegistConstants.DAY_KBN_DISP.equals(dateInfo.getDayKbn())) {
                dateInfo.setFromDt(uiEntryMst.getOnerDispFrom());
                dateInfo.setToDt(uiEntryMst.getOnerDispTo());
            // オーナー：申込開始終了日設定
            } else if (ManageRegistConstants.USERTYPE_CD_ONER.equals(dateInfo.getUsertypeCd())
                && ManageRegistConstants.DAY_KBN_ENTRY.equals(dateInfo.getDayKbn())) {
                dateInfo.setFromDt(uiEntryMst.getOnerEntryFrom());
                dateInfo.setToDt(uiEntryMst.getOnerEntryTo());
            // 本部：表示開始終了日設定            
            } else if (ManageRegistConstants.USERTYPE_CD_HONBU.equals(dateInfo.getUsertypeCd())
                && ManageRegistConstants.DAY_KBN_DISP.equals(dateInfo.getDayKbn())) {
                dateInfo.setFromDt(uiEntryMst.getHonbuDispFrom());
                dateInfo.setToDt(uiEntryMst.getHonbuDispTo());
            // 本部：申込開始終了日設定            
            } else if (ManageRegistConstants.USERTYPE_CD_HONBU.equals(dateInfo.getUsertypeCd())
                && ManageRegistConstants.DAY_KBN_ENTRY.equals(dateInfo.getDayKbn())) {
                dateInfo.setFromDt(uiEntryMst.getHonbuEntryFrom());
                dateInfo.setToDt(uiEntryMst.getHonbuEntryTo());
            }

            // 全国店長勉強会マスタ情報の更新
            if (entryYear.equals(uiEntryMst.getEntryYear())) {
                getUiEntryDateDao().update(dateInfo);
            } else {
                int result = getUiEntryDateDao().updatePKey(dateInfo, entryYear, entryKai);
                // 排他チェック
                if (result == 0) {
                    throw new CannotExecuteWithReasonException(
                        ManageRegistConstants.MSG_EXCLUSION_CHK,
                        ManageRegistConstants.MSG_UPDATE);
                }
            }
        }
    }

    /**
     * 全国店長勉強会セレクション情報を登録する。
     * @param uiEntrySelection セレクション情報
     * @param selectionList    セレクションリスト
     * @param selectionKbn     セレクション区分
     */
    private void insertEntrySelection(
        UIEntrySelection uiEntrySelection, List selectionList, String selectionKbn) {

        // コードフォーマッタ
        CodeFormatter formatter = new CodeFormatter(2);
        formatter.setFormatPattern(ManageRegistConstants.CF_SEL_INDEX);

        // セレクション区分設定
        uiEntrySelection.setSelectionKbn(selectionKbn);
        // セレクション情報登録
        for(int i = 0; i < selectionList.size(); i++) {
            UIEntrySelection info = (UIEntrySelection) selectionList.get(i);
            uiEntrySelection.setSelectionIndex(
                formatter.format(String.valueOf(i + 1), true));
            uiEntrySelection.setSelectionName(info.getSelectionName());
            getUiEntrySelectionDao().insert(uiEntrySelection);
        }        
    }

    /**
     * 全国店長勉強会セレクション情報を更新する。
     * @param selectionList セレクションリスト
     * @param userId        ユーザID
     * @param logicId       ロジックID
     * @param entryYear     エントリー年
     * @param entryKai      エントリー回
     */
    private void updateEntrySelection(List selectionList, String userId,
        String logicId, String entryYear, String entryKai) {

        // 全国店長勉強会セレクション情報更新       
        for (int i = 0; i < selectionList.size(); i++) {
            UIEntrySelection info = (UIEntrySelection) selectionList.get(i);
            // 修正ユーザ、プログラム設定
            info.setLastUser(userId);
            info.setLastPgm(logicId);
            // 全国店長勉強会セレクション情報の更新
            if (entryYear.equals(info.getEntryYear())) {
                getUiEntrySelectionDao().update(info);
            } else {        
                int result = getUiEntrySelectionDao().updatePKey(info, entryYear, entryKai);
                // 排他チェック
                if (result == 0) {
                    throw new CannotExecuteWithReasonException(
                        ManageRegistConstants.MSG_EXCLUSION_CHK,
                        ManageRegistConstants.MSG_UPDATE);
                }
            }
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