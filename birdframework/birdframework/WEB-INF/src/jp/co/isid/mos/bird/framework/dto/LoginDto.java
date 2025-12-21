package jp.co.isid.mos.bird.framework.dto;


/**
 * ログインDTO
 * 
 * 更新日 2012/02/09 xkinu 追加 p-mosslesからの遷移フラグ
 * 2006/05/01 e-mosslesからの遷移フラグ追加
 * @author xnkusama
 */
public class LoginDto implements CsvOutputDto {

    /* ユーザーID */
    private String _userId;
    /* パスワード */
    private String _userPswd;
    /* セッションブリッジキー */
    private String _sessionBridgeKey;
    /* 現在パスワード */
    private String _oldPassword;
    /* 新パスワード */
    private String _newPassword;
    /* 新パスワード確認用 */
    private String _newPasswordKakunin;
    /* 更新日 */
    private String _pswdUpdtDt;
    /* セッションエラー時のログインし直し用MSG */
    private String msgSessionError;
    /* e-mosslesから遷移か */
    private boolean fromEmosFlg;
    //パスワードポリシー関連情報
    /* パスワードロック回数 */
    private int pswdLockCnt = 0;
    /* パスワード有効期限 */
    private int pswdAvailableTerm = 0; 
    /* 画面ID */
    private String gamenId;
    
    private boolean pmossles=false;
    
    /**
	 * @return クラス変数pmossles を戻します。
	 */
	public boolean isPmossles() {
		return pmossles;
	}
	/**
	 * @param pmossles を クラス変数pmosslesへ設定します。
	 */
	public void setPmossles(boolean pmossles) {
		this.pmossles = pmossles;
	}
	/**
     * @param userId _userId を設定。
     */
    public void setUserId(String userId) {
        this._userId = userId;
    }
    /**
     * @return _userId を戻します。
     */
    public String getUserId() {
        return _userId;
    }
    /**
     * @param _userPswd _userPswd を設定。
     */
    public void setUserPswd(String userPswd) {
        this._userPswd = userPswd;
    }
    /**
     * @return _userPswd を戻します。
     */
    public String getUserPswd() {
        return _userPswd;
    }
    /**
     * @param _sessionBridgeKey _sessionBridgeKey を設定。
     */
    public void setSessionBridgeKey(String _sessionBridgeKey) {
        this._sessionBridgeKey = _sessionBridgeKey;
    }
    /**
     * @return _sessionBridgeKey を戻します。
     */
    public String getSessionBridgeKey() {
        return _sessionBridgeKey;
    }
    /**
     * @param _oldPassword _oldPassword を設定。
     */
    public void setOldPassword(String _oldPassword) {
        this._oldPassword = _oldPassword;
    }
    /**
     * @return _oldPassword を戻します。
     */
    public String getOldPassword() {
        return _oldPassword;
    }
    /**
     * @param _newPasswordKakunin _newPasswordKakunin を設定。
     */
    public void setNewPasswordKakunin(String _newPasswordKakunin) {
        this._newPasswordKakunin = _newPasswordKakunin;
    }
    /**
     * @return _newPasswordKakunin を戻します。
     */
    public String getNewPasswordKakunin() {
        return _newPasswordKakunin;
    }
    /**
     * @param _newPassword _newPassword を設定。
     */
    public void setNewPassword(String _newPassword) {
        this._newPassword = _newPassword;
    }
    /**
     * @return _newPassword を戻します。
     */
    public String getNewPassword() {
        return _newPassword;
    }
    /**
     * @param _pswdUpdtDt _pswdUpdtDt を設定。
     */
    public void setPswdUpdtDt(String _pswdUpdtDt) {
        this._pswdUpdtDt = _pswdUpdtDt;
    }
    /**
     * @return _pswdUpdtDt を戻します。
     */
    public String getPswdUpdtDt() {
        return _pswdUpdtDt;
    }

    /**
     * @return msgSessionError を戻します。
     */
    public String getMsgSessionError() {
        return msgSessionError;
    }
    /**
     * @param msgSessionError msgSessionError を設定。
     */
    public void setMsgSessionError(String msgSessionError) {
        this.msgSessionError = msgSessionError;
    }
    
    
    /**
     * e-mosslesから遷移かを取得します。
     * @return true:e-mosslesから遷移、false:BIRDから
     */
    public boolean isFromEmosFlg() {
        return fromEmosFlg;
    }
    
    /**
     * e-mosslesから遷移かを設定します。
     * @param true:e-mosslesから遷移、false:BIRDから
     */
    public void setFromEmosFlg(boolean fromEmosFlg) {
        this.fromEmosFlg = fromEmosFlg;
    }
    public int getPswdLockCnt() {
        return pswdLockCnt;
    }
    public void setPswdLockCnt(int pswdLockCnt) {
        this.pswdLockCnt = pswdLockCnt;
    }
    public String getGamenId() {
        return gamenId;
    }
    public void setGamenId(String gamenId) {
        this.gamenId = gamenId;
    }
    public int getPswdAvailableTerm() {
        return pswdAvailableTerm;
    }
    public void setPswdAvailableTerm(int pswdAvailableTerm) {
        this.pswdAvailableTerm = pswdAvailableTerm;
    }
}
