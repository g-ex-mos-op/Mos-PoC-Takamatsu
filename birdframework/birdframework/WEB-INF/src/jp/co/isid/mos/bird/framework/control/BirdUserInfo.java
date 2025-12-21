/*
 * 作成日: 2006/01/11
 */
package jp.co.isid.mos.bird.framework.control;

import java.util.HashMap;
import java.util.List;

import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIUserEmosslesAppId;

/**
 * ログイン時に取得したユーザー関連情報の管理クラス
 * @author xnkusama
 */
public class BirdUserInfo {

    private String  _userId;
    private HashMap _map;
    private boolean local;  //内部接続フラグ true：内部接続
    private boolean pmossles = false;

    public static final String MST_USER = "MST_USER";
    public static final String MST_USER_ROLE = "MST_USER_ROLE";
    public static final String MST_USER_ONER = "MST_USER_ONER";
    public static final String MST_USER_MISE = "MST_USER_MISE";
    public static final String MST_USER_COMPANY = "MST_USER_COMPANY";
    public static final String SESSION_BRIDGE_KEY = "SESSION_BRIDGE_KEY";
    public static final String TRN_EMOSSLES_APP_ID = "EMOS_APP_ID";
    public static final String MY_MENU_LIST = "MY_MENU_LIST";
    public static final String BROWSER_TYPE = "BROWSER_TYPE";


    /**
     * コンストラクタ
     * @param userId
     */
    public BirdUserInfo() {
        super();
    }
    /**
     * コンストラクタ
     * @param userId
     */
    public BirdUserInfo(String userId) {
        _userId = userId;
    }

    /**
     * 情報を格納する
     * @param obj 格納する情報
     * @param key キー
     */
    public void setInfo(final Object obj, final String key) {
        if (_map == null) {
            _map = new HashMap();
        }
        _map.put(key, obj);
    }

    /**
     * 格納した情報を取得
     * @param key
     * @return
     */
    public Object getInfo(final String key) {
        return _map.get(key);
    }

    /**
     * BIRDユーザー情報取得
     * @return MstUser
     */
    public MstUser getMstUser() {
        if (_map == null) {
            return null;
        }
        return (MstUser) _map.get(MST_USER);
    }

    /**
     * インスタンスのユーザーIDを取得
     * @return
     */
    public String getUserID() {
        return this._userId;
    }

    /**
     * ユーザーロール情報取得
     * @return CtlUserRole
     */
    public List getCtlUserRole() {
        return (List) _map.get(MST_USER_ROLE);
    }

    /**
     * ユーザーオーナー情報取得
     * @return UIUserOner
     */
    public List getUserOner() {
        return (List) _map.get(MST_USER_ONER);
    }

    /**
     * ユーザー所属会社情報取得
     * @return CtlUserCompany
     */
    public List getUserCompany() {
        return (List) _map.get(MST_USER_COMPANY);
    }

    /**
     * ユーザー対応店舗情報取得
     * @return UIUserMise
     */
    public List getUserMise() {
        return (List) _map.get(MST_USER_MISE);
    }

    public void setUserId(String userId) {
        this._userId = userId;
    }

    /**
     * 制限フラグ取得 true:制限あり
     * @return
     */
	public boolean isLimit() {
        String limitKbn = getMstUser().getLimitKbn();

        if ("0".equals(limitKbn)) {
            return false;
        }
        if (isLocal()) {
            return false;
        }
        else {
        	return true;
        }
	}
    /**
     * 内部接続フラグ取得処理 true：内部接続
     * @return
     */
	public boolean isLocal() {
		return local;
	}
	public void setLocal(boolean local) {
		this.local = local;
	}
	/**
	 * e-mosslesサイト区分取得処理
	 * @return
	 */
	public String getEmosslesAppId() {
		return ((UIUserEmosslesAppId) _map.get(TRN_EMOSSLES_APP_ID)).getAppId();
	}
	/**
	 * browserType 取得処理
	 * @return
	 */
	public String getBrowserType() {
		return (String) _map.get(BROWSER_TYPE);
	}
	/**
	 * マイメニューリスト取得処理
	 * @return
	 */
	public List getListMyMenu() {
		return (List) (_map.get(MY_MENU_LIST));
	}
	public boolean isPmossles() {
		return pmossles;
	}
	public void setPmossles(boolean pmossles) {
		this.pmossles = pmossles;
	}
}