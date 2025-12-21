/*
 * 作成日: 2006/06/14
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.logic.impl;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.entry.mlregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.mlregist.dao.UIEntryMasterDao;
import jp.co.isid.mos.bird.entry.mlregist.dao.UIEntryPlaceDao;
import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryMaster;
import jp.co.isid.mos.bird.entry.mlregist.entity.UIEntryPlace;
import jp.co.isid.mos.bird.entry.mlregist.logic.UpdateEntryLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * @author xyuchida
 */
public class UpdateEntryLogicImpl implements UpdateEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN007L05";

    private UIEntryMasterDao uiEntryMasterDao;
    private UIEntryDateDao uiEntryDateDao;
    private UIEntryPlaceDao uiEntryPlaceDao;

    public UIEntryMasterDao getUiEntryMasterDao() {
        return uiEntryMasterDao;
    }
    public void setUiEntryMasterDao(UIEntryMasterDao uiEntryMasterDao) {
        this.uiEntryMasterDao = uiEntryMasterDao;
    }
    public UIEntryDateDao getUiEntryDateDao() {
        return uiEntryDateDao;
    }
    public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
        this.uiEntryDateDao = uiEntryDateDao;
    }
    public UIEntryPlaceDao getUiEntryPlaceDao() {
        return uiEntryPlaceDao;
    }
    public void setUiEntryPlaceDao(UIEntryPlaceDao uiEntryPlaceDao) {
        this.uiEntryPlaceDao = uiEntryPlaceDao;
    }

    /**
     * マスタライセンスエントリー更新
     * 
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     */
    public void execute(MlRegistDto mlRegistDto) {

        // 編集モード判定
        switch (mlRegistDto.getEditMode()) {
        // 新規
        case 1:
            // エントリー年 = 開催開始日の年度
            String entryYear = DateManager.getCurrentYear(mlRegistDto.getUiEntryDateOpen().getFromDt());
            // エントリー回生成
            String entryKai = createEntryKai(mlRegistDto.getUiEntryMaster().getEntryCd(), entryYear);

            // insert
            insertEntry(mlRegistDto, entryYear, entryKai);

            break;

        // 変更
        case 2:
            // 開催開始日から新エントリー年を取得
            String newEntryYear = DateManager.getCurrentYear(mlRegistDto.getUiEntryDateOpen().getFromDt());

            // エントリー年が変化したか判定
            if (newEntryYear.compareTo(mlRegistDto.getUiEntryMaster().getEntryYear()) == 0) {
                // エントリー年に変化が無い為、更新を行う
                updateEntry(mlRegistDto);
            } else {
                // 旧エントリー年のレコードを論理削除
                deleteEntry(mlRegistDto);
                // 削除フラグクリア
                mlRegistDto.getUiEntryMaster().setSakujoFlg(MlRegistConstants.SAKUJO_FLG_OFF);
                // エントリー回再採番
                String newEntryKai = createEntryKai(mlRegistDto.getUiEntryMaster().getEntryCd(), newEntryYear);
                // 新エントリー年で登録
                insertEntry(mlRegistDto, newEntryYear, newEntryKai);
            }

            break;

        // 削除
        case 3:
            // delete
            deleteEntry(mlRegistDto);

            break;

        default:
            break;
        }
    }

    /**
     * エントリー回生成
     * 
     * @param entryCd エントリーコード
     * @param entryYear エントリー年
     * @return エントリー回
     */
    private String createEntryKai(String entryCd, String entryYear) {

        // 最大エントリー回取得
        int maxEntryKai = getUiEntryMasterDao().getMaxEntryKai(entryCd, entryYear);

        CodeFormatter formatter = new CodeFormatter(3);
        formatter.setFormatPattern("000");

        // インクリメントした値をフォーマット
        return formatter.format(String.valueOf(maxEntryKai + 1), true);
    }

    /**
     * エントリー登録
     * 
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     * @param entryYear エントリー年
     * @param entryKai エントリー回
     */
    private void insertEntry(MlRegistDto mlRegistDto, String entryYear, String entryKai) {

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // ユーザID取得
        String userId = mlRegistDto.getUserId();

        // エントリーマスタ管理
        UIEntryMaster uiEntryMaster = mlRegistDto.getUiEntryMaster();
        uiEntryMaster.setEntryYear(entryYear);
        uiEntryMaster.setEntryKai(entryKai);
        uiEntryMaster.setNote((uiEntryMaster.getNote() == null) ? "" : uiEntryMaster.getNote());
        uiEntryMaster.setFirstUser(userId);
        uiEntryMaster.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryMaster.setFirstTmsp(currentTimestamp);
        uiEntryMaster.setLastUser(userId);
        uiEntryMaster.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryMaster.setLastTmsp(currentTimestamp);

        // エントリー日付管理
        // 開催日
        UIEntryDate uiEntryDateOpen = mlRegistDto.getUiEntryDateOpen();
        uiEntryDateOpen.setEntryYear(entryYear);
        uiEntryDateOpen.setEntryKai(entryKai);
        uiEntryDateOpen.setFirstUser(userId);
        uiEntryDateOpen.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateOpen.setFirstTmsp(currentTimestamp);
        uiEntryDateOpen.setLastUser(userId);
        uiEntryDateOpen.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateOpen.setLastTmsp(currentTimestamp);
        // 表示：オーナー
        UIEntryDate uiEntryDateDisplayOner = mlRegistDto.getUiEntryDateDisplayOner();
        uiEntryDateDisplayOner.setEntryYear(entryYear);
        uiEntryDateDisplayOner.setEntryKai(entryKai);
        uiEntryDateDisplayOner.setFirstUser(userId);
        uiEntryDateDisplayOner.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateDisplayOner.setFirstTmsp(currentTimestamp);
        uiEntryDateDisplayOner.setLastUser(userId);
        uiEntryDateDisplayOner.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateDisplayOner.setLastTmsp(currentTimestamp);
        // 表示：本部
        UIEntryDate uiEntryDateDisplayHead = mlRegistDto.getUiEntryDateDisplayHead();
        uiEntryDateDisplayHead.setEntryYear(entryYear);
        uiEntryDateDisplayHead.setEntryKai(entryKai);
        uiEntryDateDisplayHead.setFirstUser(userId);
        uiEntryDateDisplayHead.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateDisplayHead.setFirstTmsp(currentTimestamp);
        uiEntryDateDisplayHead.setLastUser(userId);
        uiEntryDateDisplayHead.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateDisplayHead.setLastTmsp(currentTimestamp);
        // 申込：オーナー
        UIEntryDate uiEntryDateApplyOner = mlRegistDto.getUiEntryDateApplyOner();
        uiEntryDateApplyOner.setEntryYear(entryYear);
        uiEntryDateApplyOner.setEntryKai(entryKai);
        uiEntryDateApplyOner.setFirstUser(userId);
        uiEntryDateApplyOner.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateApplyOner.setFirstTmsp(currentTimestamp);
        uiEntryDateApplyOner.setLastUser(userId);
        uiEntryDateApplyOner.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateApplyOner.setLastTmsp(currentTimestamp);
        // 申込：本部
        UIEntryDate uiEntryDateApplyHead = mlRegistDto.getUiEntryDateApplyHead();
        uiEntryDateApplyHead.setEntryYear(entryYear);
        uiEntryDateApplyHead.setEntryKai(entryKai);
        uiEntryDateApplyHead.setFirstUser(userId);
        uiEntryDateApplyHead.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateApplyHead.setFirstTmsp(currentTimestamp);
        uiEntryDateApplyHead.setLastUser(userId);
        uiEntryDateApplyHead.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateApplyHead.setLastTmsp(currentTimestamp);
        // 結果登録
        UIEntryDate uiEntryDateRegist = mlRegistDto.getUiEntryDateRegist();
        uiEntryDateRegist.setEntryYear(entryYear);
        uiEntryDateRegist.setEntryKai(entryKai);
        uiEntryDateRegist.setFirstUser(userId);
        uiEntryDateRegist.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateRegist.setFirstTmsp(currentTimestamp);
        uiEntryDateRegist.setLastUser(userId);
        uiEntryDateRegist.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateRegist.setLastTmsp(currentTimestamp);
        // 結果表示：オーナー
        UIEntryDate uiEntryDateResultOner = mlRegistDto.getUiEntryDateResultOner();
        uiEntryDateResultOner.setEntryYear(entryYear);
        uiEntryDateResultOner.setEntryKai(entryKai);
        uiEntryDateResultOner.setFirstUser(userId);
        uiEntryDateResultOner.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateResultOner.setFirstTmsp(currentTimestamp);
        uiEntryDateResultOner.setLastUser(userId);
        uiEntryDateResultOner.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateResultOner.setLastTmsp(currentTimestamp);
        // 結果表示：本部
        UIEntryDate uiEntryDateResultHead = mlRegistDto.getUiEntryDateResultHead();
        uiEntryDateResultHead.setEntryYear(entryYear);
        uiEntryDateResultHead.setEntryKai(entryKai);
        uiEntryDateResultHead.setFirstUser(userId);
        uiEntryDateResultHead.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateResultHead.setFirstTmsp(currentTimestamp);
        uiEntryDateResultHead.setLastUser(userId);
        uiEntryDateResultHead.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        uiEntryDateResultHead.setLastTmsp(currentTimestamp);

        // エントリー受験地管理
        List uiEntryPlaceList = mlRegistDto.getUiEntryPlaceList();
        for (Iterator it = uiEntryPlaceList.iterator(); it.hasNext();) {
            UIEntryPlace uiEntryPlace = (UIEntryPlace) it.next();
            uiEntryPlace.setEntryYear(entryYear);
            uiEntryPlace.setEntryKai(entryKai);
            uiEntryPlace.setEntryPlaceName((uiEntryPlace.getEntryPlaceName() == null) ? "" : uiEntryPlace.getEntryPlaceName());
            uiEntryPlace.setFirstUser(userId);
            uiEntryPlace.setFirstPgm(MlRegistConstants.SCREENID_ML_REGIST);
            uiEntryPlace.setFirstTmsp(currentTimestamp);
            uiEntryPlace.setLastUser(userId);
            uiEntryPlace.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
            uiEntryPlace.setLastTmsp(currentTimestamp);
        }

        // insert
        getUiEntryMasterDao().insertEntry(uiEntryMaster);
        getUiEntryDateDao().insertEntryDate(uiEntryDateOpen);
        getUiEntryDateDao().insertEntryDate(uiEntryDateDisplayOner);
        getUiEntryDateDao().insertEntryDate(uiEntryDateDisplayHead);
        getUiEntryDateDao().insertEntryDate(uiEntryDateApplyOner);
        getUiEntryDateDao().insertEntryDate(uiEntryDateApplyHead);
        getUiEntryDateDao().insertEntryDate(uiEntryDateRegist);
        getUiEntryDateDao().insertEntryDate(uiEntryDateResultOner);
        getUiEntryDateDao().insertEntryDate(uiEntryDateResultHead);
        getUiEntryPlaceDao().insertEntryPlaceList(uiEntryPlaceList);
    }

    /**
     * エントリー更新
     * 
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     */
    private void updateEntry(MlRegistDto mlRegistDto) {

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // ユーザID取得
        String userId = mlRegistDto.getUserId();

        // エントリーマスタ管理
        UIEntryMaster uiEntryMaster = mlRegistDto.getUiEntryMaster();
        uiEntryMaster.setNote((uiEntryMaster.getNote() == null) ? "" : uiEntryMaster.getNote());
        uiEntryMaster.setLastUser(userId);
        uiEntryMaster.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);

        // エントリー日付管理
        // 開催日
        UIEntryDate uiEntryDateOpen = mlRegistDto.getUiEntryDateOpen();
        uiEntryDateOpen.setLastUser(userId);
        uiEntryDateOpen.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        // 表示：オーナー
        UIEntryDate uiEntryDateDisplayOner = mlRegistDto.getUiEntryDateDisplayOner();
        uiEntryDateDisplayOner.setLastUser(userId);
        uiEntryDateDisplayOner.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        // 表示：本部
        UIEntryDate uiEntryDateDisplayHead = mlRegistDto.getUiEntryDateDisplayHead();
        uiEntryDateDisplayHead.setLastUser(userId);
        uiEntryDateDisplayHead.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        // 申込：オーナー
        UIEntryDate uiEntryDateApplyOner = mlRegistDto.getUiEntryDateApplyOner();
        uiEntryDateApplyOner.setLastUser(userId);
        uiEntryDateApplyOner.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        // 申込：本部
        UIEntryDate uiEntryDateApplyHead = mlRegistDto.getUiEntryDateApplyHead();
        uiEntryDateApplyHead.setLastUser(userId);
        uiEntryDateApplyHead.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        // 結果登録
        UIEntryDate uiEntryDateRegist = mlRegistDto.getUiEntryDateRegist();
        uiEntryDateRegist.setLastUser(userId);
        uiEntryDateRegist.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        getUiEntryDateDao().updateEntryDate(uiEntryDateRegist);
        // 結果表示：オーナー
        UIEntryDate uiEntryDateResultOner = mlRegistDto.getUiEntryDateResultOner();
        uiEntryDateResultOner.setLastUser(userId);
        uiEntryDateResultOner.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        // 結果表示：本部
        UIEntryDate uiEntryDateResultHead = mlRegistDto.getUiEntryDateResultHead();
        uiEntryDateResultHead.setLastUser(userId);
        uiEntryDateResultHead.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);

        // エントリー受験地管理
        List uiEntryPlaceList = mlRegistDto.getUiEntryPlaceList();
        for (Iterator it = uiEntryPlaceList.iterator(); it.hasNext();) {
            UIEntryPlace uiEntryPlace = (UIEntryPlace) it.next();
            uiEntryPlace.setLastUser(userId);
            uiEntryPlace.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
            uiEntryPlace.setEntryPlaceName((uiEntryPlace.getEntryPlaceName() == null) ? "" : uiEntryPlace.getEntryPlaceName());
        }

        // update
        getUiEntryMasterDao().updateEntry(uiEntryMaster);
        getUiEntryDateDao().updateEntryDate(uiEntryDateOpen);
        getUiEntryDateDao().updateEntryDate(uiEntryDateDisplayOner);
        getUiEntryDateDao().updateEntryDate(uiEntryDateDisplayHead);
        getUiEntryDateDao().updateEntryDate(uiEntryDateApplyOner);
        getUiEntryDateDao().updateEntryDate(uiEntryDateApplyHead);
        getUiEntryDateDao().updateEntryDate(uiEntryDateResultOner);
        getUiEntryDateDao().updateEntryDate(uiEntryDateResultHead);
        getUiEntryPlaceDao().updateEntryPlaceList(uiEntryPlaceList);
    }

    /**
     * エントリー削除
     * 
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     */
    private void deleteEntry(MlRegistDto mlRegistDto) {
        // エントリーマスタ管理
        UIEntryMaster uiEntryMaster = mlRegistDto.getUiEntryMaster();
        uiEntryMaster.setLastUser(mlRegistDto.getUserId());
        uiEntryMaster.setLastPgm(MlRegistConstants.SCREENID_ML_REGIST);
        // 論理削除
        uiEntryMaster.setSakujoFlg(MlRegistConstants.SAKUJO_FLG_ON);
        // update
        getUiEntryMasterDao().updateEntry(uiEntryMaster);
    }
}
