package jp.co.isid.mos.bird.common.logic;

/**
 * パスワードチェック ロジックインターフェイス
 * @author xnkusama
 *
 */
public interface ValidatePasswordLogic {

    /**
     * 入力されたパスワードの妥当性チェックを行う
     * ※ 同一パスワード禁止世代数チェックは行いません
     * ※ パスワード変更期間のチェックは行いません
     * @param userId
     * @param passwd
     * @param userShozokuKbn
     * @author xnkusama 2008/11/14
     */
    public void execute(String userId, String passwd, String userShozokuKbn);
    
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
     * @param isPaswordPolicyAllCheck
     */
    public void execute(String userId, String passwd, String userShozokuKbn
    		, boolean isPaswordPolicyAllCheck);
}