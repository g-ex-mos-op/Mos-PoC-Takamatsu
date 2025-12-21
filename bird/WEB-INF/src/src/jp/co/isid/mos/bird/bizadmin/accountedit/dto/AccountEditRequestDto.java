package jp.co.isid.mos.bird.bizadmin.accountedit.dto;


/**
 * 複数ウィンドウ制御用リクエストDTO
 * @author xnkusama
 *
 */
public class AccountEditRequestDto {
	private String userId;
	/**
	 * リクエスト対象ブラウザのウィンドウID
	 */
	private int windowId;
	/**
	 * 操作手順制御用セッションキー
	 */
    private String sessionKey;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

	/**
	 * @return クラス変数windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * @param windowId を クラス変数windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**
	 * @return クラス変数userId を戻します。
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId を クラス変数userIdへ設定します。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
}
