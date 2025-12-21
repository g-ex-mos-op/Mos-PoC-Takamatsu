/*
 * 作成日: 2008/11/17
 *
 */
package jp.co.isid.mos.bird.common.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.CtlBirdUserDao;
import jp.co.isid.mos.bird.common.dao.CtlTogoUserRirekiDao;
import jp.co.isid.mos.bird.common.dao.CtlUserKakoPWDao;
import jp.co.isid.mos.bird.common.entity.CtlBirdUser;
import jp.co.isid.mos.bird.common.entity.CtlTogoUserRireki;
import jp.co.isid.mos.bird.common.entity.CtlUserKakoPW;
import jp.co.isid.mos.bird.common.logic.UpdatePasswordLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * @author xnkusama
 *
 */
public class UpdatePasswordLogicImpl implements UpdatePasswordLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BCN000L22";
    /*定数*/
    private static final int HENKO_KBN_INSERT = 0;
    private static final int HENKO_KBN_UPDATE = 1;
    /*DAO*/
    private CtlUserKakoPWDao ctlUserKakoPWDao;
    private CtlBirdUserDao ctlBirdUserDao;
    private CtlTogoUserRirekiDao ctlTogoUserRirekiDao;
    /*LOGIC*/
    private DateInfoSetupLogic dateInfoSetupLogic;
    /**
     * パスワード更新
     * @param userId
     * @param passwd
     * @param henkoKbn
     * @param lastUser
     * @param lastPgm
     */
    public void execute(String userId, 
                          String passwd, 
                          int henkoKbn, 
                          String lastUser, 
                          String lastPgm) 
    {
        // パラメータチェック
        validate(userId, passwd, henkoKbn, lastUser, lastPgm);
        // 共通Dao【統合ユーザ履歴情報．ユーザー情報の取得】を実行する。
        CtlTogoUserRireki ctlTogoUserRireki = getCtlTogoUserRirekiDao().getCtlTogoUserRireki(userId);
        // 共通Dao【BIRDユーザ情報．ユーザー情報の取得】を実行する。
        CtlBirdUser ctlBirdUser = getCtlBirdUserDao().getBirdCtlBirdUser(userId);
        // システム日付取得
        BirdDateInfo birdDateInfo = getDateInfoSetupLogic().getBirdDateInfo();
        // パラメータ．変更区分＝'0'(新規)の場合
        if (henkoKbn == HENKO_KBN_INSERT) {
            doInsert(userId, passwd, lastUser, lastPgm, ctlTogoUserRireki, ctlBirdUser, birdDateInfo.getSysDate());
        }
        //パラメータ．変更区分＝'1'(変更)の場合
        else if (henkoKbn == HENKO_KBN_UPDATE) {
            doUpdate(userId, passwd, lastUser, lastPgm, ctlTogoUserRireki, ctlBirdUser, birdDateInfo.getSysDate());
        }
    }
    
    /**
     * 新規登録処理
     * @param userId
     * @param passwd
     * @param lastUser
     * @param lastPgm
     * @param ctlTogoUserRireki
     * @param ctlBirdUser
     * @param sysDate
     */
    private void doInsert(String userId, 
                            String passwd, 
                            String lastUser, 
                            String lastPgm, 
                            CtlTogoUserRireki ctlTogoUserRireki, 
                            CtlBirdUser ctlBirdUser,
                            String sysDate) 
    {
        //[ユーザ過去パスワード]を新規作成し以下の項目にセットする。
        //[ユーザ過去パスワード]の最大SEQを取得 
        CtlUserKakoPW ctlUserKakoPW = new CtlUserKakoPW();
        ctlUserKakoPW.setUserId(userId);
        ctlUserKakoPW.setPswdUpdtDt(sysDate);
        ctlUserKakoPW.setUserPswd(passwd.getBytes());
        ctlUserKakoPW.setLastPswd("".getBytes());
        ctlUserKakoPW.setLastUser(lastUser);
        ctlUserKakoPW.setLastPgm(lastPgm);
        ctlUserKakoPW.setLastTmsp(DateManager.getCurrentTimestamp());
        ctlUserKakoPW.setSeqNo(1);
        //Dao【ユーザ過去パスワード．ユーザパスワード履歴の追加】を実行する。
        getCtlUserKakoPWDao().insertCtlPassword(ctlUserKakoPW);
        //[BIRDユーザ情報]の以下の項目をセットする。
        ctlBirdUser.setLastPswd("".getBytes());
        ctlBirdUser.setUserPswd(passwd.getBytes());
        ctlBirdUser.setPswdUpdtDt(sysDate);
        ctlBirdUser.setLastUser(lastUser);
        ctlBirdUser.setLastPgm(lastPgm);
        //ctlBirdUser.setLastTmsp(DateManager.getCurrentTimestamp());
        //Dao【BIRDユーザ情報．ユーザーパスワードの更新】を実行する。
        getCtlBirdUserDao().updateBirdCtlBirdUser(ctlBirdUser);
        //処理１で[統合ユーザ履歴情報]が取得できた場合（パラメータがnullでない場合）
        if (ctlTogoUserRireki != null) {
            //[統合ユーザ履歴情報]の以下の項目にセットする。
            ctlTogoUserRireki.setUserPswd(passwd);
            ctlTogoUserRireki.setLastUser(lastUser);
            ctlTogoUserRireki.setLastPgm(lastPgm);
            //ctlTogoUserRireki.setLastTmsp(DateManager.getCurrentTimestamp());
            //Dao【統合ユーザ履歴情報．ユーザーパスワードの更新】を実行する。
            getCtlTogoUserRirekiDao().updateCtlTogoUserRireki(ctlTogoUserRireki);
        }
    }
    
    /**
     * 更新処理
     * @param userId
     * @param passwd
     * @param lastUser
     * @param lastPgm
     * @param ctlTogoUserRireki
     * @param ctlBirdUser
     * @param sysDate
     */
    private void doUpdate(String userId, 
                            String passwd, 
                            String lastUser, 
                            String lastPgm, 
                            CtlTogoUserRireki ctlTogoUserRireki, 
                            CtlBirdUser ctlBirdUser,
                            String sysDate) 
    {
        //[ユーザ過去パスワード]を新規作成し以下の項目にセットする。
        //[ユーザ過去パスワード]の最大SEQを取得 
        int maxSeq = getCtlUserKakoPWDao().getMaxSeq(userId, sysDate);
        
        //[パスワード履歴]テーブルから現在のパスワード(更新後の前回パスワードとなる)を取得する。
        byte[] lastPswd = getLastPswd(userId);
        
        CtlUserKakoPW ctlUserKakoPW = new CtlUserKakoPW();
        ctlUserKakoPW.setUserId(userId);
        ctlUserKakoPW.setPswdUpdtDt(sysDate);
        ctlUserKakoPW.setUserPswd(passwd.getBytes());
        ctlUserKakoPW.setLastPswd(lastPswd);
        ctlUserKakoPW.setLastUser(lastUser);
        ctlUserKakoPW.setLastPgm(lastPgm);
        ctlUserKakoPW.setLastTmsp(DateManager.getCurrentTimestamp());
        ctlUserKakoPW.setSeqNo(maxSeq + 1);
        //Dao【ユーザ過去パスワード．ユーザパスワード履歴の追加】を実行する。
        getCtlUserKakoPWDao().insertCtlPassword(ctlUserKakoPW);
        //[BIRDユーザ情報]の以下の項目をセットする。
        ctlBirdUser.setLastPswd(lastPswd);
        ctlBirdUser.setUserPswd(passwd.getBytes());
        ctlBirdUser.setPswdUpdtDt(sysDate);
        ctlBirdUser.setLastUser(lastUser);
        ctlBirdUser.setLastPgm(lastPgm);
        //ctlBirdUser.setLastTmsp(DateManager.getCurrentTimestamp());
        //Dao【BIRDユーザ情報．ユーザーパスワードの更新】を実行する。
        getCtlBirdUserDao().updateBirdCtlBirdUser(ctlBirdUser);
        //処理１で[統合ユーザ履歴情報]が取得できた場合（パラメータがnullでない場合）
        if (ctlTogoUserRireki != null) {
            //[統合ユーザ履歴情報]の以下の項目にセットする。
            ctlTogoUserRireki.setUserPswd(passwd);
            ctlTogoUserRireki.setLastUser(lastUser);
            ctlTogoUserRireki.setLastPgm(lastPgm);
            //ctlTogoUserRireki.setLastTmsp(DateManager.getCurrentTimestamp());
            //Dao【統合ユーザ履歴情報．ユーザーパスワードの更新】を実行する。
            getCtlTogoUserRirekiDao().updateCtlTogoUserRireki(ctlTogoUserRireki);
        }
    }
    
    /**
     * パスワード履歴テーブル(IR52PASS)から指定したユーザの最新のパスワードを抽出する
     * @param userId
     * @param maxSeq
     */
    private byte[] getLastPswd(String userId) {
        List list = getCtlUserKakoPWDao().getCtlPassword(userId);
        
        if (list != null) {
            CtlUserKakoPW ctlUserKakoPW = (CtlUserKakoPW)list.get(0);
            return ctlUserKakoPW.getUserPswd();
        }
        
        return null;
    }

    /**
     * パラメータチェック
     * @param userId
     * @param passwd
     * @param henkoKbn
     * @param lastUser
     * @param lastPgm
     */
    private void validate(String userId, 
                            String passwd, 
                            int henkoKbn, 
                            String lastUser, 
                            String lastPgm)  
    {
        //ユーザーID必須チェック
        if (CommonUtil.isNull(userId)) {
            throw new NotNullException("ユーザーID");
        }
        //パスワード必須チェック
        if (CommonUtil.isNull(passwd)) {
            throw new NotNullException("パスワード");
        }
        //パスワード必須チェック
        if (henkoKbn != HENKO_KBN_INSERT && henkoKbn != HENKO_KBN_UPDATE) {
            throw new InvalidInputException("パラメータ：変更区分の指定");
        }
        //更新ユーザー必須チェック
        if (CommonUtil.isNull(lastUser)) {
            throw new NotNullException("更新ユーザー");
        }
        //更新プログラムID必須チェック
        if (CommonUtil.isNull(lastPgm)) {
            throw new NotNullException("更新プログラムID");
        }
    }
    
    public CtlBirdUserDao getCtlBirdUserDao() {
        return ctlBirdUserDao;
    }
    public void setCtlBirdUserDao(CtlBirdUserDao ctlBirdUserDao) {
        this.ctlBirdUserDao = ctlBirdUserDao;
    }
    public CtlTogoUserRirekiDao getCtlTogoUserRirekiDao() {
        return ctlTogoUserRirekiDao;
    }
    public void setCtlTogoUserRirekiDao(CtlTogoUserRirekiDao ctlTogoUserRirekiDao) {
        this.ctlTogoUserRirekiDao = ctlTogoUserRirekiDao;
    }
    public CtlUserKakoPWDao getCtlUserKakoPWDao() {
        return ctlUserKakoPWDao;
    }
    public void setCtlUserKakoPWDao(CtlUserKakoPWDao ctlUserKakoPWDao) {
        this.ctlUserKakoPWDao = ctlUserKakoPWDao;
    }

    public DateInfoSetupLogic getDateInfoSetupLogic() {
        return dateInfoSetupLogic;
    }

    public void setDateInfoSetupLogic(DateInfoSetupLogic dateInfoSetupLogic) {
        this.dateInfoSetupLogic = dateInfoSetupLogic;
    }
}
