/*
 * 作成日: 2006/4/19
 */
package jp.co.isid.mos.bird.entry.hanyoregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;

import jp.co.isid.mos.bird.entry.common.dao.UIEntryCourseDao;
import jp.co.isid.mos.bird.entry.common.entity.UIEntryCourse;
import jp.co.isid.mos.bird.entry.hanyoregist.common.HanyoRegistCommon;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryDateDao;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryMstDao;
import jp.co.isid.mos.bird.entry.hanyoregist.dao.UIEntryNoticeDao;
import jp.co.isid.mos.bird.entry.hanyoregist.dto.HanyoRegistDto;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoregist.entity.UIEntryNotice;
import jp.co.isid.mos.bird.entry.hanyoregist.logic.UpdateUserLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * エントリーマスタ管理の更新ロジック
 * @author itamoto
 */
public class UpdateUserLogicImpl implements UpdateUserLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN004L02";

    /**
     * エントリー日付管理（UIEntryDateDao）
     */
    private UIEntryDateDao uiEntryDateDao;

    /**
     * エントリーマスタ管理（UIEntryMstDao）
     */
    private UIEntryMstDao uiEntryMstDao;

    /**
     * エントリーコース管理
     */
    private UIEntryCourseDao uiEntryCourseDao;

//  add start 2007/01/09 マスタライセンス　４次対応        
    /**
     * エントリー文言情報
     */
    private UIEntryNoticeDao entryNoticeDao;
// add end    
    
    /**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @return uiEntryDateDao を戻します。
	 */
	public UIEntryDateDao getUiEntryDateDao() {
		return uiEntryDateDao;
	}
	/**
	 * エントリー日付管理（UIEntryDateDao）の設定
	 * @param uiEntryDateDao uiEntryDateDao を設定。
	 */
	public void setUiEntryDateDao(UIEntryDateDao uiEntryDateDao) {
		this.uiEntryDateDao = uiEntryDateDao;
	}

	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @return uiEntryMstDao を戻します。
	 */
	public UIEntryMstDao getUiEntryMstDao() {
		return uiEntryMstDao;
	}
	/**
	 * エントリーマスタ管理（UIEntryMstDao）の設定
	 * @param uiEntryMstDao uiEntryMstDao を設定。
	 */
	public void setUiEntryMstDao(UIEntryMstDao uiEntryMstDao) {
		this.uiEntryMstDao = uiEntryMstDao;
	}

    /**
     * エントリーコース管理（UIEntryCourseDao）の設定
     * @return uiEntryCourseDao を戻します。
     */
    public UIEntryCourseDao getUiEntryCourseDao() {
        return uiEntryCourseDao;
    }
    /**
     * エントリーコース管理（UIEntryCourseDao）の設定
     * @param uiEntryCourseDao uiEntryCourseDao を設定。
     */
    public void setUiEntryCourseDao(UIEntryCourseDao uiEntryCourseDao) {
        this.uiEntryCourseDao = uiEntryCourseDao;
    }

    /**
     * エントリーマスタ管理・エントリー日付管理の更新を行う
     * @param hanyoRegistDto     
     * */
    public List execute(HanyoRegistDto hanyoRegistDto) {
    	// １．必須のパラメータが満たされていること。E0506
    	// ２．パラメータが妥当であること。E0505
    	// ※パラメータ．登録モードが'0'(新規)、又は、が'1'(編集)の場合、[[エントリー日付管理]]必須。
    	// ※パラメータ．登録モードが'0'(新規)の場合、エントリーコード・システム日付必須。
    	
    	// １．パラメータ．登録モードが、'0'(新規) の場合
    	// ①．Dao【エントリーマスタ管理．エントリー回のMAX値取得】を実行する。
    	// パラメータ：  パラメータ.エントリーコード
    	// ②．パラメータ.[エントリーマスタ管理]に下記データをセットする。
    	// ・パラメータ.[エントリーマスタ管理].エントリーコード← パラメータ.エントリーコード
    	// ・パラメータ.[エントリーマスタ管理].エントリー年← パラメータ.システム日付のYYYY
    	// ・パラメータ.[エントリーマスタ管理].エントリー回← 1．①で取得したエントリー回のMAX値
    	// ・パラメータ.[エントリーマスタ管理].予備フラグ1← 空白
    	// ・パラメータ.[エントリーマスタ管理].予備フラグ2← 空白
    	// ③．Dao【エントリーマスタ管理．エントリーマスタ管理の新規登録】を実行する。
    	// パラメータ：  １．②で編集した、パラメータ.[エントリーマスタ管理]
    	// ④．パラメータ.[エントリー日付管理]に下記データをセットする。※パラメータ.[[エントリー日付管理]]全件に対して行う。
    	// ・パラメータ.[エントリー日付管理].エントリーコード← パラメータ.エントリーコード
    	// ・パラメータ.[エントリー日付管理].エントリー年← パラメータ.システム日付のYYYY
    	// ・パラメータ.[エントリー日付管理].エントリー回← 1．①で取得したエントリー回のMAX値
    	// ⑤．Dao【エントリー日付管理．エントリー日付管理の新規登録】を実行する。
    	// パラメータ：  １．④で編集した、パラメータ.[[エントリー日付管理]]
        // ２．パラメータ．登録モードが、'1'(編集) の場合
        // ①．Dao【エントリーマスタ管理．エントリーマスタ管理の更新】を実行する。
        // パラメータ：  パラメータ.[エントリーマスタ管理]
        // ②．Dao【エントリー日付管理．エントリー日付管理の更新】を実行する。
        // パラメータ：  パラメータ.[[エントリー日付管理]]
        // ３．パラメータ．登録モードが、'2'(削除) の場合
        // ①．パラメータ.[エントリーマスタ管理]に下記データをセットする。
        // ・パラメータ.[エントリーマスタ管理].削除フラグ← ’1’
        // ②．Dao【エントリーマスタ管理．エントリーマスタ管理の更新】を実行する。
        // パラメータ：  ３．①で編集したパラメータ.[エントリーマスタ管理]
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        CodeFormatter formatter = new CodeFormatter(3);
        formatter.setFormatPattern("000");

        // エントリーマスタ管理
        UIEntryMst uiEntryMst = hanyoRegistDto.getUiEntryMst();
        // エントリー日付管理 開催日
        UIEntryDate uiEntryDateKaisai = hanyoRegistDto.getUiEntryDateKaisai();
        // エントリー日付管理 本部表示
        UIEntryDate uiEntryDateHonbuHyoji = hanyoRegistDto.getUiEntryDateHonbuHyoji();
        // エントリー日付管理 オーナー表示
        UIEntryDate uiEntryDateOnerHyoji = hanyoRegistDto.getUiEntryDateOnerHyoji();
        // エントリー日付管理 本部登録
        UIEntryDate uiEntryDateHonbuToroku = hanyoRegistDto.getUiEntryDateHonbuToroku();
        // エントリー日付管理 オーナー登録
        UIEntryDate uiEntryDateOnerToroku = hanyoRegistDto.getUiEntryDateOnerToroku();
        // エントリー日付管理 結果
        UIEntryDate uiEntryDateKekka = hanyoRegistDto.getUiEntryDateKekka();
        // エントリーコース管理
        UIEntryCourse uiEntryCourse = hanyoRegistDto.getUiEntryCourse();
//add start inazawa 2007/01/09 マスタライセンス　４次対応
        UIEntryNotice entryNotice  = hanyoRegistDto.getUIEntryNotice();
        if(!hanyoRegistDto.getEntryCd().equals(HanyoRegistCommon.KOUSIN_KENSHU)){
            // エントリー文言情報
            //文言２
            entryNotice.setNotice2(isNulltran(entryNotice.getNotice2()));
        }
// add end        

        // 新規、編集、削除 共通
        // エントリーマスタ管理
        uiEntryMst.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryMst.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // エントリー日付管理 開催日
        uiEntryDateKaisai.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryDateKaisai.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // エントリー日付管理 本部表示
        uiEntryDateHonbuHyoji.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryDateHonbuHyoji.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // エントリー日付管理 オーナー表示
        uiEntryDateOnerHyoji.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryDateOnerHyoji.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // エントリー日付管理 本部登録
        uiEntryDateHonbuToroku.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryDateHonbuToroku.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // エントリー日付管理 オーナー登録
        uiEntryDateOnerToroku.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryDateOnerToroku.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // エントリー日付管理 結果
        uiEntryDateKekka.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryDateKekka.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // エントリーコース管理
        uiEntryCourse.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
        uiEntryCourse.setLastPgm(HanyoRegistCommon.VIEW_ID);
        // 備考のnull対応
        if (isNull(uiEntryMst.getNote())) {
            uiEntryMst.setNote("");
        }

        if (hanyoRegistDto.getEditMode().equals(HanyoRegistCommon.EDIT_MODE_INSERT)) {
            // 【新規】
            // エントリー年に開催日の年度(YYYY)をセット
            hanyoRegistDto.setEntryYear(DateManager.getCurrentYear(uiEntryDateKaisai.getFromDt()));
            // エントリー回のMaxを取得
            int maxEntryKai = getUiEntryMstDao().getMaxEntryKai(hanyoRegistDto.getEntryCd(), hanyoRegistDto.getEntryYear());
            String entryKai = formatter.format(String.valueOf(maxEntryKai + 1), true);
            hanyoRegistDto.setEntryKai(entryKai);
            // エントリーマスタ管理
            uiEntryMst.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryMst.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryMst.setEntryKai(entryKai);
            uiEntryMst.setSpareFlg1("");
            uiEntryMst.setSpareFlg2("");
            uiEntryMst.setSakujoFlg(HanyoRegistCommon.SAKUJO_FLG_OFF);
            uiEntryMst.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryMst.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryMst.setFirstTmsp(currentTimestamp);
            uiEntryMst.setLastTmsp(currentTimestamp);
            // エントリー日付管理 開催日
            uiEntryDateKaisai.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryDateKaisai.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryDateKaisai.setEntryKai(entryKai);
            uiEntryDateKaisai.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryDateKaisai.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryDateKaisai.setFirstTmsp(currentTimestamp);
            uiEntryDateKaisai.setLastTmsp(currentTimestamp);
            // エントリー日付管理 本部表示
            uiEntryDateHonbuHyoji.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryDateHonbuHyoji.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryDateHonbuHyoji.setEntryKai(entryKai);
            uiEntryDateHonbuHyoji.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryDateHonbuHyoji.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryDateHonbuHyoji.setFirstTmsp(currentTimestamp);
            uiEntryDateHonbuHyoji.setLastTmsp(currentTimestamp);
            // エントリー日付管理 オーナー表示
            uiEntryDateOnerHyoji.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryDateOnerHyoji.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryDateOnerHyoji.setEntryKai(entryKai);
            uiEntryDateOnerHyoji.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryDateOnerHyoji.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryDateOnerHyoji.setFirstTmsp(currentTimestamp);
            uiEntryDateOnerHyoji.setLastTmsp(currentTimestamp);
            // エントリー日付管理 本部登録
            uiEntryDateHonbuToroku.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryDateHonbuToroku.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryDateHonbuToroku.setEntryKai(entryKai);
            uiEntryDateHonbuToroku.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryDateHonbuToroku.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryDateHonbuToroku.setFirstTmsp(currentTimestamp);
            uiEntryDateHonbuToroku.setLastTmsp(currentTimestamp);
            // エントリー日付管理 オーナー登録
            uiEntryDateOnerToroku.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryDateOnerToroku.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryDateOnerToroku.setEntryKai(entryKai);
            uiEntryDateOnerToroku.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryDateOnerToroku.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryDateOnerToroku.setFirstTmsp(currentTimestamp);
            uiEntryDateOnerToroku.setLastTmsp(currentTimestamp);
            // エントリー日付管理 結果
            uiEntryDateKekka.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryDateKekka.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryDateKekka.setEntryKai(entryKai);
            uiEntryDateKekka.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryDateKekka.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryDateKekka.setFirstTmsp(currentTimestamp);
            uiEntryDateKekka.setLastTmsp(currentTimestamp);
            // エントリーコース管理
            uiEntryCourse.setEntryCd(hanyoRegistDto.getEntryCd());
            uiEntryCourse.setEntryYear(hanyoRegistDto.getEntryYear());
            uiEntryCourse.setEntryKai(entryKai);
            uiEntryCourse.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
            uiEntryCourse.setFirstPgm(HanyoRegistCommon.VIEW_ID);
            uiEntryCourse.setFirstTmsp(currentTimestamp);
            uiEntryCourse.setLastTmsp(currentTimestamp);
// add start inazawa 2007/01/09 マスタライセンス　４次対応
            if(!hanyoRegistDto.getEntryCd().equals(HanyoRegistCommon.KOUSIN_KENSHU)){
                entryNotice.setEntryCd(hanyoRegistDto.getEntryCd());
                entryNotice.setEntryYear(hanyoRegistDto.getEntryYear());
                entryNotice.setEntryKai(entryKai);
                entryNotice.setNotice1("");
                entryNotice.setNotice2(isNulltran(entryNotice.getNotice2()));
                entryNotice.setNotice3("");
                entryNotice.setFirstUser(hanyoRegistDto.getBirdUserInfo().getUserID());
                entryNotice.setFirstPgm(HanyoRegistCommon.VIEW_ID);
                entryNotice.setFirstTmsp(currentTimestamp);
                entryNotice.setLastUser(hanyoRegistDto.getBirdUserInfo().getUserID());
                entryNotice.setLastPgm(HanyoRegistCommon.VIEW_ID);
                entryNotice.setLastTmsp(currentTimestamp);
            }
// add end            

            // insert
            getUiEntryMstDao().insertEntry(uiEntryMst);
            getUiEntryDateDao().insertEntryDate(uiEntryDateKaisai);
            getUiEntryDateDao().insertEntryDate(uiEntryDateHonbuHyoji);
            getUiEntryDateDao().insertEntryDate(uiEntryDateOnerHyoji);
            getUiEntryDateDao().insertEntryDate(uiEntryDateHonbuToroku);
            getUiEntryDateDao().insertEntryDate(uiEntryDateOnerToroku);
            getUiEntryDateDao().insertEntryDate(uiEntryDateKekka);
            getUiEntryCourseDao().insertEntryCourse(uiEntryCourse);
//          add start inazawa 2007/01/09 マスタライセンス　４次対応
            if(!hanyoRegistDto.getEntryCd().equals(HanyoRegistCommon.KOUSIN_KENSHU)){
                getEntryNoticeDao().insertEntryNotice(entryNotice);
            }
//          add end
        } else if (hanyoRegistDto.getEditMode().equals(HanyoRegistCommon.EDIT_MODE_UPDATE)) {
            // 【編集】
            // 新エントリー年取得
            String newEntryYear = DateManager.getCurrentYear(uiEntryDateKaisai.getFromDt());

            uiEntryCourse.setLastTmsp(currentTimestamp);

            // エントリー年が変化したか判定
            if (newEntryYear.compareTo(hanyoRegistDto.getEntryYear()) == 0) {
                // エントリー年に変化が無い為、更新を行う
                getUiEntryMstDao().updateEntry(uiEntryMst);
                getUiEntryDateDao().updateEntryDate(uiEntryDateKaisai);
                getUiEntryDateDao().updateEntryDate(uiEntryDateHonbuHyoji);
                getUiEntryDateDao().updateEntryDate(uiEntryDateOnerHyoji);
                getUiEntryDateDao().updateEntryDate(uiEntryDateHonbuToroku);
                getUiEntryDateDao().updateEntryDate(uiEntryDateOnerToroku);
                getUiEntryDateDao().updateEntryDate(uiEntryDateKekka);
                getUiEntryCourseDao().updateEntryCourse(uiEntryCourse);
//              add start inazawa 2007/01/09 マスタライセンス　４次対応
                if(!hanyoRegistDto.getEntryCd().equals(HanyoRegistCommon.KOUSIN_KENSHU)){
                    getEntryNoticeDao().updateEntryNotice(entryNotice);
                }
//              add end

            } else {
                // 旧エントリー年のレコードを論理削除
                uiEntryMst.setSakujoFlg(HanyoRegistCommon.SAKUJO_FLG_ON);
                getUiEntryMstDao().updateEntry(uiEntryMst);

                // エントリー回のMaxを再取得
                int maxEntryKai = getUiEntryMstDao().getMaxEntryKai(hanyoRegistDto.getEntryCd(), newEntryYear);
                String newEntryKai = formatter.format(String.valueOf(maxEntryKai + 1), true);

                // DTOにセット
                hanyoRegistDto.setEntryYear(newEntryYear);
                hanyoRegistDto.setEntryKai(newEntryKai);

                // 新エントリー年、回で登録
                uiEntryMst.setEntryYear(newEntryYear);
                uiEntryMst.setEntryKai(newEntryKai);
                uiEntryMst.setSakujoFlg(HanyoRegistCommon.SAKUJO_FLG_OFF);
                uiEntryDateKaisai.setEntryYear(newEntryYear);
                uiEntryDateKaisai.setEntryKai(newEntryKai);
                uiEntryDateHonbuHyoji.setEntryYear(newEntryYear);
                uiEntryDateHonbuHyoji.setEntryKai(newEntryKai);
                uiEntryDateOnerHyoji.setEntryYear(newEntryYear);
                uiEntryDateOnerHyoji.setEntryKai(newEntryKai);
                uiEntryDateHonbuToroku.setEntryYear(newEntryYear);
                uiEntryDateHonbuToroku.setEntryKai(newEntryKai);
                uiEntryDateOnerToroku.setEntryYear(newEntryYear);
                uiEntryDateOnerToroku.setEntryKai(newEntryKai);
                uiEntryDateKekka.setEntryYear(newEntryYear);
                uiEntryDateKekka.setEntryKai(newEntryKai);
                uiEntryCourse.setEntryYear(newEntryYear);
                uiEntryCourse.setEntryKai(newEntryKai);

                // insert
                getUiEntryMstDao().insertEntry(uiEntryMst);
                getUiEntryDateDao().insertEntryDate(uiEntryDateKaisai);
                getUiEntryDateDao().insertEntryDate(uiEntryDateHonbuHyoji);
                getUiEntryDateDao().insertEntryDate(uiEntryDateOnerHyoji);
                getUiEntryDateDao().insertEntryDate(uiEntryDateHonbuToroku);
                getUiEntryDateDao().insertEntryDate(uiEntryDateOnerToroku);
                getUiEntryDateDao().insertEntryDate(uiEntryDateKekka);
                getUiEntryCourseDao().insertEntryCourse(uiEntryCourse);
            }

        } else if (hanyoRegistDto.getEditMode().equals(HanyoRegistCommon.EDIT_MODE_DELETE)) {
            // 【削除】
            uiEntryMst.setSakujoFlg(HanyoRegistCommon.SAKUJO_FLG_ON);
            // update
            getUiEntryMstDao().updateEntry(uiEntryMst);
        }

        return null;
    }

    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
//  add start 2007/01/09 マスタライセンス　４次対応
    /**
     * エントリー文言情報DAOの取得
     * @return entryNoticeDao
     */
    public UIEntryNoticeDao getEntryNoticeDao() {
        return entryNoticeDao;
    }
    /**
     * エントリー文言情報DAOの設定
     * @param entryNotice
     */
    public void setEntryNoticeDao(UIEntryNoticeDao entryNoticeDao) {
        this.entryNoticeDao = entryNoticeDao;
    }
    /**
     * nullチェック
     */
    public String isNulltran(String str) {
        if (str == null) {
            return "";
        }

        return str.trim();
    }

// add end    
}
