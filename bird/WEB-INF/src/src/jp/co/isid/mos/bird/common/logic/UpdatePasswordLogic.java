/*
 * 作成日: 2008/11/14
 */
package jp.co.isid.mos.bird.common.logic;


/**
 * パスワード更新ロジック インターフェイス
 * @author xnkusama
 */
public interface UpdatePasswordLogic {
    /*定数*/
    public static final int HENKO_KBN_INSERT = 0;
    public static final int HENKO_KBN_UPDATE = 1;
    /**
     * パスワード更新
     * @param userId
     * @param passwd
     * @param henkoKbn
     * @param lastUser
     * @param lastPgm
     */
    public void execute(String userId, String passwd, int henkoKbn, String lastUser, String lastPgm);
}
