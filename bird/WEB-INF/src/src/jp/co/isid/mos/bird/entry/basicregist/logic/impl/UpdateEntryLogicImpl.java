/*
 * 作成日: 2006/06/01
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.logic.impl;

import java.sql.Timestamp;

import jp.co.isid.mos.bird.entry.basicregist.common.BasicRegistConstants;
import jp.co.isid.mos.bird.entry.basicregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.basicregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;
import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicregist.logic.UpdateEntryLogic;
import jp.co.isid.mos.bird.entry.common.dao.UIEntryCourseDao;
import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;
import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * @author xyuchida
 */
public class UpdateEntryLogicImpl implements UpdateEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN001L04";

    private UIEntryMstDao uiEntryMstDao;
    private UIEntryDateDao uiEntryDateDao;
    private UIEntryCourseDao uiEntryCourseDao;

    public UIEntryMstDao getUiEntryMstDao() {
        return uiEntryMstDao;
    }
    public void setUiEntryMstDao(UIEntryMstDao uiEntryMstDao) {
        this.uiEntryMstDao = uiEntryMstDao;
    }
    public UIEntryDateDao getUiEntryDateDao() {
        return uiEntryDateDao;
    }
    public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
        this.uiEntryDateDao = uiEntryDateDao;
    }
    public UIEntryCourseDao getUiEntryCourseDao() {
        return uiEntryCourseDao;
    }
    public void setUiEntryCourseDao(UIEntryCourseDao uiEntryCourseDao) {
        this.uiEntryCourseDao = uiEntryCourseDao;
    }

    public void execute(BasicRegistDto basicRegistDto) {

        switch (basicRegistDto.getEditMode()) {
        // 新規
        case 1:
            // エントリー年 = 開催開始日の年度
            String entryYear = DateManager.getCurrentYear(basicRegistDto.getUiEntryDateBasic().getFromDt());
            // エントリー回生成
            String entryKai = createEntryKai(basicRegistDto.getUiEntryMst().getEntryCd(), entryYear);

            // insert
            insertEntry(basicRegistDto, entryYear, entryKai);

            break;

        // 変更
        case 2:
            // 開催開始日から新エントリー年を取得
            String newEntryYear = DateManager.getCurrentYear(basicRegistDto.getUiEntryDateBasic().getFromDt());

            // エントリー年が変化したか判定
            if (newEntryYear.compareTo(basicRegistDto.getUiEntryMst().getEntryYear()) == 0) {
                // エントリー年に変化が無い為、更新を行う
                updateEntry(basicRegistDto);
            } else {
                // 旧エントリー年のレコードを論理削除
                deleteEntry(basicRegistDto);
                // 削除フラグクリア
                basicRegistDto.getUiEntryMst().setSakujoFlg(MlRegistConstants.SAKUJO_FLG_OFF);
                // エントリー回再採番
                String newEntryKai = createEntryKai(basicRegistDto.getUiEntryMst().getEntryCd(), newEntryYear);
                // 新エントリー年で登録
                insertEntry(basicRegistDto, newEntryYear, newEntryKai);
            }

            break;

        // 削除
        case 3:
            // delete
            deleteEntry(basicRegistDto);

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
        int maxEntryKai = getUiEntryMstDao().getMaxEntryKai(entryCd, entryYear);

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
    private void insertEntry(BasicRegistDto basicRegistDto, String entryYear, String entryKai) {

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // ユーザID取得
        String userId = basicRegistDto.getUserId();

        // エントリーマスタ管理
        UIEntryMst uiEntryMst = basicRegistDto.getUiEntryMst();
        uiEntryMst.setEntryYear(entryYear);
        uiEntryMst.setEntryKai(entryKai);
        uiEntryMst.setNote((uiEntryMst.getNote() == null) ? "" : uiEntryMst.getNote());
        uiEntryMst.setFirstUser(userId);
        uiEntryMst.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryMst.setFirstTmsp(currentTimestamp);
        uiEntryMst.setLastUser(userId);
        uiEntryMst.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryMst.setLastTmsp(currentTimestamp);

        // エントリー日付管理
        // ベーシック研修(開催日)
        UIEntryDate uiEntryDateBasic = basicRegistDto.getUiEntryDateBasic();
        uiEntryDateBasic.setEntryYear(entryYear);
        uiEntryDateBasic.setEntryKai(entryKai);
        uiEntryDateBasic.setFirstUser(userId);
        uiEntryDateBasic.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateBasic.setFirstTmsp(currentTimestamp);
        uiEntryDateBasic.setLastUser(userId);
        uiEntryDateBasic.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateBasic.setLastTmsp(currentTimestamp);
        // 臨店自習コース(その他)
        UIEntryDate uiEntryDateVisit = basicRegistDto.getUiEntryDateVisit();
        uiEntryDateVisit.setEntryYear(entryYear);
        uiEntryDateVisit.setEntryKai(entryKai);
        uiEntryDateVisit.setFirstUser(userId);
        uiEntryDateVisit.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateVisit.setFirstTmsp(currentTimestamp);
        uiEntryDateVisit.setLastUser(userId);
        uiEntryDateVisit.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateVisit.setLastTmsp(currentTimestamp);
        // オーナー：表示
        UIEntryDate uiEntryDateDisplayOner = basicRegistDto.getUiEntryDateDisplayOner();
        uiEntryDateDisplayOner.setEntryYear(entryYear);
        uiEntryDateDisplayOner.setEntryKai(entryKai);
        uiEntryDateDisplayOner.setFirstUser(userId);
        uiEntryDateDisplayOner.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateDisplayOner.setFirstTmsp(currentTimestamp);
        uiEntryDateDisplayOner.setLastUser(userId);
        uiEntryDateDisplayOner.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateDisplayOner.setLastTmsp(currentTimestamp);
        // 本部：表示
        UIEntryDate uiEntryDateDisplayHead = basicRegistDto.getUiEntryDateDisplayHead();
        uiEntryDateDisplayHead.setEntryYear(entryYear);
        uiEntryDateDisplayHead.setEntryKai(entryKai);
        uiEntryDateDisplayHead.setFirstUser(userId);
        uiEntryDateDisplayHead.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateDisplayHead.setFirstTmsp(currentTimestamp);
        uiEntryDateDisplayHead.setLastTmsp(currentTimestamp);
        uiEntryDateDisplayHead.setLastUser(userId);
        uiEntryDateDisplayHead.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // オーナー：申込
        UIEntryDate uiEntryDateApplyOner = basicRegistDto.getUiEntryDateApplyOner();
        uiEntryDateApplyOner.setEntryYear(entryYear);
        uiEntryDateApplyOner.setEntryKai(entryKai);
        uiEntryDateApplyOner.setFirstUser(userId);
        uiEntryDateApplyOner.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateApplyOner.setFirstTmsp(currentTimestamp);
        uiEntryDateApplyOner.setLastUser(userId);
        uiEntryDateApplyOner.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateApplyOner.setLastTmsp(currentTimestamp);
        // 本部：申込
        UIEntryDate uiEntryDateApplyHead = basicRegistDto.getUiEntryDateApplyHead();
        uiEntryDateApplyHead.setEntryYear(entryYear);
        uiEntryDateApplyHead.setEntryKai(entryKai);
        uiEntryDateApplyHead.setFirstUser(userId);
        uiEntryDateApplyHead.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateApplyHead.setFirstTmsp(currentTimestamp);
        uiEntryDateApplyHead.setLastUser(userId);
        uiEntryDateApplyHead.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateApplyHead.setLastTmsp(currentTimestamp);
        // 結果登録
        UIEntryDate uiEntryDateResult = basicRegistDto.getUiEntryDateResult();
        uiEntryDateResult.setEntryYear(entryYear);
        uiEntryDateResult.setEntryKai(entryKai);
        uiEntryDateResult.setFirstUser(userId);
        uiEntryDateResult.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateResult.setFirstTmsp(currentTimestamp);
        uiEntryDateResult.setLastUser(userId);
        uiEntryDateResult.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryDateResult.setLastTmsp(currentTimestamp);

        // エントリーコース管理
        UIEntryCourse uiEntryCourse = basicRegistDto.getUiEntryCourse();
        uiEntryCourse.setEntryYear(entryYear);
        uiEntryCourse.setEntryKai(entryKai);
        uiEntryCourse.setFirstUser(userId);
        uiEntryCourse.setFirstPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryCourse.setFirstTmsp(currentTimestamp);
        uiEntryCourse.setLastUser(userId);
        uiEntryCourse.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryCourse.setLastTmsp(currentTimestamp);

        // insert
        getUiEntryMstDao().insertEntry(uiEntryMst);
        getUiEntryDateDao().insertEntryDate(uiEntryDateBasic);
        getUiEntryDateDao().insertEntryDate(uiEntryDateVisit);
        getUiEntryDateDao().insertEntryDate(uiEntryDateDisplayOner);
        getUiEntryDateDao().insertEntryDate(uiEntryDateDisplayHead);
        getUiEntryDateDao().insertEntryDate(uiEntryDateApplyOner);
        getUiEntryDateDao().insertEntryDate(uiEntryDateApplyHead);
        getUiEntryDateDao().insertEntryDate(uiEntryDateResult);
        getUiEntryCourseDao().insertEntryCourse(uiEntryCourse);
    }

    /**
     * エントリー更新
     * 
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     */
    private void updateEntry(BasicRegistDto basicRegistDto) {

        // 現在日付取得
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        // ユーザID取得
        String userId = basicRegistDto.getUserId();

        // エントリーマスタ管理
        UIEntryMst uiEntryMst = basicRegistDto.getUiEntryMst();
        uiEntryMst.setNote((uiEntryMst.getNote() == null) ? "" : uiEntryMst.getNote());
        uiEntryMst.setLastUser(userId);
        uiEntryMst.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);

        // エントリー日付管理
        // ベーシック研修(開催日)
        UIEntryDate uiEntryDateBasic = basicRegistDto.getUiEntryDateBasic();
        uiEntryDateBasic.setLastUser(userId);
        uiEntryDateBasic.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // 臨店実習コース(その他)
        UIEntryDate uiEntryDateVisit = basicRegistDto.getUiEntryDateVisit();
        uiEntryDateVisit.setLastUser(userId);
        uiEntryDateVisit.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // オーナー：表示
        UIEntryDate uiEntryDateDisplayOner = basicRegistDto.getUiEntryDateDisplayOner();
        uiEntryDateDisplayOner.setLastUser(userId);
        uiEntryDateDisplayOner.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // 本部：表示
        UIEntryDate uiEntryDateDisplayHead = basicRegistDto.getUiEntryDateDisplayHead();
        uiEntryDateDisplayHead.setLastUser(userId);
        uiEntryDateDisplayHead.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // オーナー：申込
        UIEntryDate uiEntryDateApplyOner = basicRegistDto.getUiEntryDateApplyOner();
        uiEntryDateApplyOner.setLastUser(userId);
        uiEntryDateApplyOner.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // 本部：申込
        UIEntryDate uiEntryDateApplyHead = basicRegistDto.getUiEntryDateApplyHead();
        uiEntryDateApplyHead.setLastUser(userId);
        uiEntryDateApplyHead.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // 結果
        UIEntryDate uiEntryDateResult = basicRegistDto.getUiEntryDateResult();
        uiEntryDateResult.setLastUser(userId);
        uiEntryDateResult.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);

        // エントリーコース管理
        UIEntryCourse uiEntryCourse = basicRegistDto.getUiEntryCourse();
        uiEntryCourse.setLastUser(userId);
        uiEntryCourse.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        uiEntryCourse.setLastTmsp(currentTimestamp);

        // update
        getUiEntryMstDao().updateEntry(uiEntryMst);
        getUiEntryDateDao().updateEntryDate(uiEntryDateBasic);
        getUiEntryDateDao().updateEntryDate(uiEntryDateVisit);
        getUiEntryDateDao().updateEntryDate(uiEntryDateDisplayOner);
        getUiEntryDateDao().updateEntryDate(uiEntryDateDisplayHead);
        getUiEntryDateDao().updateEntryDate(uiEntryDateApplyOner);
        getUiEntryDateDao().updateEntryDate(uiEntryDateApplyHead);
        getUiEntryDateDao().updateEntryDate(uiEntryDateResult);
        getUiEntryCourseDao().updateEntryCourse(uiEntryCourse);
    }

    /**
     * エントリー削除
     * 
     * @param mlRegistDto マスタライセンスマスタ登録DTO
     */
    private void deleteEntry(BasicRegistDto basicRegistDto) {
        // エントリーマスタ管理
        UIEntryMst uiEntryMst = basicRegistDto.getUiEntryMst();
        uiEntryMst.setLastUser(basicRegistDto.getUserId());
        uiEntryMst.setLastPgm(BasicRegistConstants.SCREENID_BASIC_REGIST);
        // 論理削除
        uiEntryMst.setSakujoFlg(BasicRegistConstants.SAKUJO_FLG_ON);
        // update
        getUiEntryMstDao().updateEntry(uiEntryMst);
    }
}
