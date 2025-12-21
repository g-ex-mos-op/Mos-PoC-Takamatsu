/*
 * 作成日: 2008/11/14
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.code.UserShozokuKbn;
import jp.co.isid.mos.bird.common.dao.CtlBirdUserDao;
import jp.co.isid.mos.bird.common.dao.CtlTogoUserRirekiDao;
import jp.co.isid.mos.bird.common.dao.CtlUserKakoPWDao;
import jp.co.isid.mos.bird.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.common.entity.CtlBirdUser;
import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;
import jp.co.isid.mos.bird.common.entity.CtlTogoUserRireki;
import jp.co.isid.mos.bird.common.entity.CtlUserKakoPW;
import jp.co.isid.mos.bird.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.common.logic.ValidatePasswordLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * パスワードチェック ロジック
 * @author xnkusama
 *
 */
public class ValidatePasswordLogicImpl implements ValidatePasswordLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L22";
    
    /*DAO*/
    private CtlUserKakoPWDao ctlUserKakoPWDao;
    private CtlBirdUserDao ctlBirdUserDao;
    private CtlTogoUserRirekiDao ctlTogoUserRirekiDao;
    private MstUserShozokuDao mstUserShozokuDao;
    /*LOGIC*/
    private DateInfoSetupLogic dateInfoSetupLogic;
    /*定数エラーMSG*/
    private static final String ERR_MSG_01 = "パスワード";
    private static final String ERR_MSG_02 = "文字以上入力してください。";
    private static final String ERR_MSG_03 = "半角英数・半角記号で入力してください。";
    private static final String ERR_MSG_04 = "種類以上の文字を使用してください。";
    private static final String ERR_MSG_05 = "連続で同じ文字は使用できません。";
    private static final String ERR_MSG_06_01 = "過去";
    private static final String ERR_MSG_06_02 = "回で使用したパスワードは使用できません。";
    private static final String ERR_MSG_07 = "前回と同じパスワードは使用できません。";
    private static final String ERR_MSG_08_01 = "パスワード変更後";
    private static final String ERR_MSG_08_02 = "日間は変更できません。";
    private static final String ERR_MSG_09 = "半角英数字・記号の";
    private static final String ERR_MSG_10 = "種類以上の文字を使用してください。";
    private static final String ERR_MSG_11 = "文字以内で入力してください。";
    /*パスワード変更期間チェックをする画面ID*/
    private static final String CHANGE_TERM_CHECK_VIEW_ID = "BPO000";
    
    /*文字種*/
    //半角数値
    private static final String HANKAKU_NUMBER = "0123456789 ";
    //半角アルファベット
    private static final String HANKAKU_LATIN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    //禁則文字
    private static final String EXP_1 = "%{";
    private static final String EXP_2 = "${";
    /*パスワードポリシー*/
    private Map birdPasswordPolicy;
    
    
    
    /**
     * 入力されたパスワードの妥当性チェックを行う
     * ※下記のチェックの有無をパラメータisPaswordPolicyAllCheckで指定できます。
     * 　trueの場合、下記の全てのチェックが有効になります。
     * 　falseの場合は下記の全てのチェックが無効になります。
     * 　　同一パスワード禁止世代数チェックあり
     *     パスワード変更期間チェックあり
     *     前回からの最低変更文字数チェックあり
     *     使用文字数チェックあり
     *     使用文字種チェックあり
     *     連続同一文字許可チェックあり
     * @param userId
     * @param passwd
     * @param userShozokuKbn
     * @param isPaswordPolicyAllCheck true：同一パスワード禁止世代数チェックあり
     *                                       パスワード変更期間チェックあり
     *                                       前回からの最低変更文字数チェックあり
     *                                       使用文字数チェックあり
     *                                       使用文字種チェックあり
     *                                       連続同一文字許可チェックあり
     */
    public void execute(String userId, String passwd, String userShozokuKbn
    		, boolean isPaswordPolicyAllCheck) {
        //パスワード右側のスペースは無視する
        String inputPasswd = CommonUtil.rtrim(passwd);
        //パラメータチェック
        validate(userId, inputPasswd, userShozokuKbn);
        
        //統合ユーザ履歴情報
        CtlTogoUserRireki entityCtlTogoUserRireki = getCtlTogoUserRirekiDao().getCtlTogoUserRireki(userId);
        //BIRDユーザー情報
        CtlBirdUser entityCtlBirdUser = getCtlBirdUserDao().getBirdCtlBirdUser(userId);
        
        //チェックタイプ取得
        String pswdCheckType = getPswdCheckType(userId, userShozokuKbn);
        //パスワードポリシー定義Entity取得
        CtlPasswordPolicy passwdPolicy = (CtlPasswordPolicy) getBirdPasswordPolicy().get(String.valueOf(pswdCheckType));
        
        if (isPaswordPolicyAllCheck) {
            //1.変更禁止期間チェック
            checkDenyChangeTerm(userId, inputPasswd, passwdPolicy, entityCtlTogoUserRireki, entityCtlBirdUser);
            //2.同一パスワード禁止世代数チェック
        	checkDenyGenerationCnt(userId, inputPasswd, passwdPolicy, entityCtlTogoUserRireki, entityCtlBirdUser);
	        //3.前回からの最低変更文字数チェック
	       	checkMustChangeCharCnt(userId, inputPasswd, passwdPolicy, entityCtlTogoUserRireki, entityCtlBirdUser);
        }
        //4.最低文字数チェック
        checkMinimumCharCnt(inputPasswd, passwdPolicy);
        //5.最高文字数チェック
        checkMaxCharCnt(inputPasswd, passwdPolicy);
        //6.使用可能文字チェック
        checkUsableCharCode(inputPasswd, passwdPolicy);
        //7.禁則文字チェック
        checkDisableTextCode(inputPasswd);
        if (isPaswordPolicyAllCheck) {
	        //8.使用文字数チェック
	        checkMinUseCharCnt(inputPasswd, passwdPolicy);
	        //9.使用文字種チェック
	        checkMinUseCharTypeCnt(inputPasswd, passwdPolicy);
	        //10.連続同一文字許可チェック
	        checkContinuousCharCnt(inputPasswd, passwdPolicy);
        }
    }

    /**
     * 入力されたパスワードの妥当性チェックを行う
     * @param userId
     * @param passwd
     * @param userShozokuKbn
     * @author xnkusama 2008/11/14
     */
    public void execute(String userId, String passwd, String userShozokuKbn) {
        execute(userId, passwd, userShozokuKbn, false);
    }

    /**
     * 変更禁止期間チェック
     * @param userId
     * @param passwd
     * @param passwdPolicy
     * @param entityCtlTogoUserRireki
     * @param entityCtlBirdUser
     */
    private void checkDenyChangeTerm(String userId, String passwd, CtlPasswordPolicy passwdPolicy, CtlTogoUserRireki entityCtlTogoUserRireki, CtlBirdUser entityCtlBirdUser) {
        //設定値がゼロまたは新規登録の場合は、チェックなし
        if (passwdPolicy.getDenyChangeTerm() == 0) {
            return;
        }
        
        String updateDt  = "";
        String lastPgm = "";
        if (entityCtlTogoUserRireki != null) {
            updateDt = entityCtlTogoUserRireki.getPswdUpdtDt();
            lastPgm = entityCtlTogoUserRireki.getLastPgm();
        }
        else if (entityCtlBirdUser != null) {
            updateDt = entityCtlBirdUser.getPswdUpdtDt();
            lastPgm = entityCtlBirdUser.getLastPgm();
        }
        else {
            //[統合ユーザ履歴情報]、[BIRDユーザ情報]が取得できない＝新規なのでチェックなし
            return;
        }        
        //前回変更画面がチェック対象画面以外の場合はチェックなし
        if (CommonUtil.isNull(lastPgm)) {
            lastPgm = "";
        }
        if (!lastPgm.startsWith(CHANGE_TERM_CHECK_VIEW_ID)) {
            return;
        }
        BirdDateInfo birdDateInfo = getDateInfoSetupLogic().getBirdDateInfo();
        
        //変更禁止期間チェック
        try {
            updateDt = DateManager.getNextDate(updateDt, passwdPolicy.getDenyChangeTerm());
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        if (updateDt.compareTo(birdDateInfo.getSysDate()) >= 0) {
            throw new InvalidInputException(ERR_MSG_01, ERR_MSG_08_01 + passwdPolicy.getDenyChangeTerm() + ERR_MSG_08_02);
        }
    }

    /**
     * 前回からの最低変更文字数チェック
     * @param userId
     * @param passwd
     * @param passwdPolicy
     * @param entityCtlTogoUserRireki
     * @param entityCtlBirdUser
     */
    private void checkMustChangeCharCnt(String userId, String passwd, CtlPasswordPolicy passwdPolicy, CtlTogoUserRireki entityCtlTogoUserRireki, CtlBirdUser entityCtlBirdUser) {
        String oldPasswd  = "";
        if (entityCtlTogoUserRireki != null) {
            oldPasswd = entityCtlTogoUserRireki.getUserPswd();
        }
        else if (entityCtlBirdUser != null) {
            oldPasswd = String.valueOf(entityCtlBirdUser.getUserPswd());
        }
        else {
            //[統合ユーザ履歴情報]、[BIRDユーザ情報]が取得できない＝新規なのでチェックなし
            return;
        }
        
        int cnt = 0;
        //新パスワードを基準に1文字ずつチェック
        for (int i = 0; i < passwd.length(); i++) {
            //旧パスワードが新パスワードより短い場合、その文字は変更ありとみなす
            if (oldPasswd.length() < i + 1) {
                cnt++;
                continue;
            }
            if (!passwd.substring(i, i + 1).equals(oldPasswd.substring(i, i + 1))) {
                cnt++;
            }
        }
        //旧パスワードが新パスワードより長い場合、文字数の差を変更文字数としてカウント
        if (oldPasswd.length() > passwd.length()) {
            cnt = cnt + (oldPasswd.length() - passwd.length());
        }
        
        //変更文字数チェック
        if (cnt < passwdPolicy.getMustChangeCharCnt()) {
            throw new InvalidInputException(ERR_MSG_01, ERR_MSG_07);
        }
    }
    
    /**
     * 同一パスワード禁止世代数チェック
     * @param userId
     * @param passwd
     * @param passwdPolicy
     * @param entityCtlTogoUserRireki
     * @param entityCtlBirdUser
     */
    private void checkDenyGenerationCnt(String userId, String passwd, CtlPasswordPolicy passwdPolicy, CtlTogoUserRireki entityCtlTogoUserRireki, CtlBirdUser entityCtlBirdUser) {
        //設定値がゼロまたは新規登録の場合は、チェックなし
        if (passwdPolicy.getDenyGenerationCnt() == 0) {
            return;
        }
        //[統合ユーザ履歴情報]、[BIRDユーザ情報]が取得できない＝新規なのでチェックなし
        if (entityCtlTogoUserRireki == null && entityCtlBirdUser == null) {
            return;
        }

        boolean chk = true;
        
        //設定値が2以上の場合、[[ユーザ過去パスワード]]]と比較チェック
        if (passwdPolicy.getDenyGenerationCnt() >= 1) {
            //Dao【ユーザー過去パスワード】．ユーザーパスワード情報の検索を実行
            List listPasswdRireki = getCtlUserKakoPWDao().getCtlPassword(userId);
            
            if (listPasswdRireki != null && !listPasswdRireki.isEmpty()) {
                for (int i = 0; i < listPasswdRireki.size(); i++) {
                    if (i >= passwdPolicy.getDenyGenerationCnt()) {
                        break;
                    }
                    CtlUserKakoPW entity = (CtlUserKakoPW) listPasswdRireki.get(i);
                    if (passwd.equals(new String(entity.getUserPswd()))) {
                        chk = false;
                        break;
                    }
                }
            }
        }
        
        if (!chk) {
            throw new InvalidInputException(ERR_MSG_01, ERR_MSG_06_01 + passwdPolicy.getDenyGenerationCnt() + ERR_MSG_06_02);
        }

    }

    /**
     * 連続同一文字許可チェック
     * @param passwd
     * @param passwdPolicy
     */
    private void checkContinuousCharCnt(String passwd, CtlPasswordPolicy passwdPolicy) {
        //設定値がゼロの場合は、チェックなし
        if (passwdPolicy.getContinuousCharCnt() == 0) {
            return;
        }
        
        String basisChar = "";
        int cnt = 0;
        for (int i = 0; i < passwd.length(); i++) {
            String chr = passwd.substring(i, i+1);
            if (basisChar.equals(chr)) {
                cnt++;
                if (cnt > passwdPolicy.getContinuousCharCnt()) {
                    throw new InvalidInputException(ERR_MSG_01, ERR_MSG_05);
                }
            }
            else {
                cnt = 0;
            }
        }
    }
    
    /**
     * 使用文字種チェック
     * @param passwd
     * @param passwdPolicy
     */
    private void checkMinUseCharTypeCnt(String passwd, CtlPasswordPolicy passwdPolicy) {
        boolean bHankakuNumber = false;
        boolean bHankakuLatin = false;
        boolean bOther = false;
        int cntCharType = 0;
        //1文字ずつ文字種をチェックしカウント
        for (int i = 0; i < passwd.length(); i++) {
            String chr = passwd.substring(i, i + 1);
            //半角数値
            if (chr.indexOf(HANKAKU_NUMBER) > 0) {
                if (!bHankakuNumber) {
                    cntCharType++;
                }
                bHankakuNumber = true;
            }
            //半角アルファベット
            else if (chr.indexOf(HANKAKU_LATIN) > 0) {
                if (!bHankakuLatin) {
                    cntCharType++;
                }
                bHankakuLatin = true;
            }
            //半角数値
            else {
                if (!bOther) {
                    cntCharType++;
                }
                bOther = true;
            }
        }
        //文字種数チェック
        if (cntCharType < passwdPolicy.getMinUseCharTypeCnt()) {
            throw new InvalidInputException(ERR_MSG_01, ERR_MSG_09 + passwdPolicy.getMinUseCharTypeCnt() + ERR_MSG_10);
        }
    }
    
    /**
     * 使用文字数チェック
     * @param passwd
     * @param passwdPolicy
     */
    private void checkMinUseCharCnt(String passwd, CtlPasswordPolicy passwdPolicy) {
        //パスワードを1文字ずつListへ格納
        List listChar = new ArrayList();
        for (int i = 0; i < passwd.length(); i++) {
            listChar.add((new Character(passwd.charAt(i))).toString());
        }
        //Listをソート
        Collections.sort(listChar);
        //重複文字を削除
        String basisChar = "";
        for (Iterator ite = listChar.iterator(); ite.hasNext();) {
            String chr = (String) ite.next();
            if (basisChar.equals(chr)) {
                ite.remove();
            }
            else {
                basisChar = chr;
            }
        }
        //使用文字数をチェック
        if (listChar.size() < passwdPolicy.getMinUseCharCnt()) {
            throw new InvalidInputException(ERR_MSG_01, passwdPolicy.getMinUseCharCnt() + ERR_MSG_04);
        }
    }
    /**
     * 最低文字数チェック
     * @param passwd
     * @param passwdPolicy
     */
    private void checkMinimumCharCnt(String passwd, CtlPasswordPolicy passwdPolicy) {
        int passwdLen = 0;
        if (passwd != null) {
            passwdLen = passwd.length();
        }
        if (passwdLen < passwdPolicy.getMinCharCnt()) {
            throw new InvalidInputException(ERR_MSG_01, passwdPolicy.getMinCharCnt() + ERR_MSG_02);
        }
    }
    /**
     * 最高文字数チェック
     * @param passwd
     * @param passwdPolicy
     */
    private void checkMaxCharCnt(String passwd, CtlPasswordPolicy passwdPolicy) {
        int passwdLen = 0;
        if (passwd != null) {
            passwdLen = passwd.length();
        }
        if (passwdLen > passwdPolicy.getMaxCharCnt()) {
            throw new InvalidInputException(ERR_MSG_01, passwdPolicy.getMaxCharCnt() + ERR_MSG_11);
        }
    }

    /**
     * 使用可能文字チェック
     * @param passwd
     * @param passwdPolicy
     */
    private void checkUsableCharCode(String passwd, CtlPasswordPolicy passwdPolicy) {
        boolean chk = true;
        for (int i = 0; i < passwd.length(); i++) {
            byte[] bytes;
            try {
                bytes = passwd.substring(i, i+1).getBytes("Shift_JIS");
            }
            catch (java.io.UnsupportedEncodingException e) {
                chk = false;
                break;
            }
            if (bytes.length != 1) {
                chk = false;
                break;
            }
            int value = bytes[0];
            if (value < 0) {
                value = value + 256;
            }
            if (value < passwdPolicy.getUsableCharCodeFrom() || value > passwdPolicy.getUsableCharCodeTo()) {
                chk = false;
                break;
            }
            
        }
        if (!chk) {
            throw new InvalidInputException(ERR_MSG_01, ERR_MSG_03);
        }
    }
    
    /**
     * パスワードの禁則文字チェック
     * @param passwd
     */
    private void checkDisableTextCode(String passwd) {
        boolean chk = false;
        //パスワード文字列中に%{または${が入っている場合、chkにfalseをする
        if (passwd.indexOf(EXP_1) > -1 || passwd.indexOf(EXP_2) > -1) {
        	chk = true;
        }
        if (chk) {
            throw new InvalidInputException(ERR_MSG_01, "${ や %{ はパスワードには利用できません。");
        }
    }

    /**
     * チェック用ユーザータイプ判定
     * @param userId
     * @param userShozokuKbn
     * @return
     */
    private String getPswdCheckType(String userId, String userShozokuKbn) {
        String pswdCheckType = "";
        
        //パラメータ．ユーザー所属区分≠ブランクの場合
        if (!CommonUtil.isNull(userShozokuKbn)) {
//            pswdCheckType = convShozokuKbnToCheckType(userShozokuKbn);
            pswdCheckType = userShozokuKbn;
        }
        else {
            //共通Dao【ユーザ所属．ユーザー所属の取得】を実行
            List listUserShozoku = getMstUserShozokuDao().getMstUserShozoku(userId);
            if (listUserShozoku != null && !listUserShozoku.isEmpty()) {
                //取得できた場合、1件目の所属区分でチェックタイプを取得
//                pswdCheckType = convShozokuKbnToCheckType(((MstUserShozoku) listUserShozoku.get(0)).getShozokuKbn());
                pswdCheckType = ((MstUserShozoku) listUserShozoku.get(0)).getShozokuKbn();
            }
            else {
                // 共通Dao【統合ユーザ履歴情報．ユーザー所属の取得】を実行
                CtlTogoUserRireki entityCtlTogoUserRireki = getCtlTogoUserRirekiDao().getCtlTogoUserRireki(userId);
                // 取得できた場合はチェックタイプ＝本部とする
                if (entityCtlTogoUserRireki != null) {
                    pswdCheckType = UserShozokuKbn.HONBU;
                }
            }
        }
        if (CommonUtil.isNull(pswdCheckType)) {
            throw new FtlSystemException("パスワードチェック", "ユーザー所属情報が取得できませんでした。");
        }
        return pswdCheckType;
    }
    
//    /**
//     * ユーザー所属区分からパスワードチェックタイプを取得
//     * @param userShozokuKbn
//     * @return
//     */
//    private int convShozokuKbnToCheckType(String userShozokuKbn) {
//        int pswdCheckType = 0;
//        if (UserShozokuKbn.HONBU.equals(userShozokuKbn)) {
//            pswdCheckType = CHECK_TYPE_HONBU;
//        }
//        else if (UserShozokuKbn.ONER.equals(userShozokuKbn)) {
//            pswdCheckType = CHECK_TYPE_ONER;
//        }
//        else if (UserShozokuKbn.RC_TENPO.equals(userShozokuKbn)) {
//            pswdCheckType = CHECK_TYPE_HONBU;
//        }
//        else if (UserShozokuKbn.FC_TENPO.equals(userShozokuKbn)) {
//            pswdCheckType = CHECK_TYPE_ONER;
//        }
//        else if (UserShozokuKbn.HANSHA.equals(userShozokuKbn)) {
//            pswdCheckType = CHECK_TYPE_HANSHA;
//        }
//        else if (UserShozokuKbn.HANSHA_TENPO.equals(userShozokuKbn)) {
//            pswdCheckType = CHECK_TYPE_HANSHA;
//        }
//        return pswdCheckType;
//    }
    /**
     * パラメータチェック
     * @param userId
     * @param passwd
     * @param userShozokuKbn
     */
    private void validate(String userId, String passwd, String userShozokuKbn) {
        //ユーザーID必須チェック
        if (CommonUtil.isNull(userId)) {
            throw new NotNullException("ユーザーID");
        }
        //パスワード必須チェック
        if (passwd == null) {
            throw new NotNullException("パスワード");
        }
    }
    
    public CtlBirdUserDao getCtlBirdUserDao() {
        return ctlBirdUserDao;
    }

    public void setCtlBirdUserDao(CtlBirdUserDao ctlBirdUserDao) {
        this.ctlBirdUserDao = ctlBirdUserDao;
    }

    public CtlUserKakoPWDao getCtlUserKakoPWDao() {
        return ctlUserKakoPWDao;
    }

    public void setCtlUserKakoPWDao(CtlUserKakoPWDao ctlTogoUserPWDao) {
        this.ctlUserKakoPWDao = ctlTogoUserPWDao;
    }

    public CtlTogoUserRirekiDao getCtlTogoUserRirekiDao() {
        return ctlTogoUserRirekiDao;
    }

    public void setCtlTogoUserRirekiDao(CtlTogoUserRirekiDao ctlTogoUserRirekiDao) {
        this.ctlTogoUserRirekiDao = ctlTogoUserRirekiDao;
    }

    public MstUserShozokuDao getMstUserShozokuDao() {
        return mstUserShozokuDao;
    }

    public void setMstUserShozokuDao(MstUserShozokuDao mstUserShozokuDao) {
        this.mstUserShozokuDao = mstUserShozokuDao;
    }

    public Map getBirdPasswordPolicy() {
        return birdPasswordPolicy;
    }

    public void setBirdPasswordPolicy(Map birdPasswordPolicy) {
        this.birdPasswordPolicy = birdPasswordPolicy;
    }

    public DateInfoSetupLogic getDateInfoSetupLogic() {
        return dateInfoSetupLogic;
    }

    public void setDateInfoSetupLogic(DateInfoSetupLogic dateInfoSetupLogic) {
        this.dateInfoSetupLogic = dateInfoSetupLogic;
    }

}
