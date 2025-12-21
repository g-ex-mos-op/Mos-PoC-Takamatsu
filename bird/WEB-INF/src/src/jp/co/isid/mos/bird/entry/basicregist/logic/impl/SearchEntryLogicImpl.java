/*
 * 作成日: 2006/06/30
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.basicregist.common.BasicRegistConstants;
import jp.co.isid.mos.bird.entry.basicregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;
import jp.co.isid.mos.bird.entry.basicregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicregist.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.entry.common.dao.UIEntryCourseDao;
import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author xyuchida
 */
public class SearchEntryLogicImpl implements SearchEntryLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN001L02";

    private UIEntryDateDao uiEntryDateDao;
    private UIEntryCourseDao uiEntryCourseDao;

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

        // エントリーマスタ管理コピー
        UIEntryMst uiEntryMst = new UIEntryMst();
        try {
            PropertyUtils.copyProperties(
                    uiEntryMst,
                    basicRegistDto.getBasicEntryList().get(basicRegistDto.getSelectIndex()));
        } catch (Exception e) {
            throw new FtlSystemException("");
        }
        basicRegistDto.setUiEntryMst(uiEntryMst);

        // キー項目取得
        String entryCd = uiEntryMst.getEntryCd();
        String entryYear = uiEntryMst.getEntryYear();
        String entryKai = uiEntryMst.getEntryKai();

        // エントリー日付管理取得
        // ベーシック研修(開催日)
        basicRegistDto.setUiEntryDateBasic(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, BasicRegistConstants.USERTYPE_CODE_KANRI, BasicRegistConstants.DAY_KBN_BASIC));
        // 臨店自習コース(その他)
        basicRegistDto.setUiEntryDateVisit(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, BasicRegistConstants.USERTYPE_CODE_KANRI, BasicRegistConstants.DAY_KBN_VISIT));
        // 表示(本部)
        basicRegistDto.setUiEntryDateDisplayHead(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, BasicRegistConstants.USERTYPE_CODE_HONBU, BasicRegistConstants.DAY_KBN_DISPLAY));
        // 表示(オーナー)
        basicRegistDto.setUiEntryDateDisplayOner(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, BasicRegistConstants.USERTYPE_CODE_OWNER, BasicRegistConstants.DAY_KBN_DISPLAY));
        // 申込(本部)
        basicRegistDto.setUiEntryDateApplyHead(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, BasicRegistConstants.USERTYPE_CODE_HONBU, BasicRegistConstants.DAY_KBN_APPLY));
        // 申込(オーナー)
        basicRegistDto.setUiEntryDateApplyOner(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, BasicRegistConstants.USERTYPE_CODE_OWNER, BasicRegistConstants.DAY_KBN_APPLY));
        // 結果登録
        basicRegistDto.setUiEntryDateResult(getUiEntryDateDao().getEntryDate(
                entryCd, entryYear, entryKai, BasicRegistConstants.USERTYPE_CODE_HONBU, BasicRegistConstants.DAY_KBN_RESULT));

        // エントリーコース管理取得
        List entryCourseList = getUiEntryCourseDao().getEntryCourseList(entryCd, entryYear, entryKai);
        UIEntryCourse uiEntryCourse = null;
        if (entryCourseList != null && !entryCourseList.isEmpty()) {
            uiEntryCourse = (UIEntryCourse) entryCourseList.get(0);
        } else {
            uiEntryCourse = new UIEntryCourse();
            uiEntryCourse.setEntryCd(entryCd);
        }
        basicRegistDto.setUiEntryCourse(uiEntryCourse);
    }
}
