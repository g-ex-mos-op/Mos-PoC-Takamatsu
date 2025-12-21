/**
 *
 */
package jp.co.isid.mos.bird.common.entity;

import java.sql.Timestamp;

/**
 * 作成日:2009/01/09
 * @author xkinu
 *
 */
public class UIOutLink {
    public static final String menuId_COLUMN = "MENU_ID";

    public static final String menuName_COLUMN = "MENU_NAME";

    public static final String subMenuId_COLUMN = "SUB_MENU_ID";

    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";

    public static final String initViewId_COLUMN = "INIT_VIEW_ID";

    public static final String viewId_COLUMN = "VIEW_ID";

    public static final String linkId_COLUMN = "LINK_ID";

    public static final String url_COLUMN = "URL";

    public static final String param_COLUMN = "PARAM";

    public static final String paramUserId_COLUMN = "PARAM_USER_ID";

    public static final String paramUserPswd_COLUMN = "PARAM_USER_PSWD";

    public static final String disableStaTmp_COLUMN = "DISABLE_STA_TMSP";

    public static final String disableEndTmp_COLUMN = "DISABLE_END_TMSP";

    public static final String mainteMsg_COLUMN = "MAINTE_MSG";

    public static final String imgPath_COLUMN = "IMG_PATH";

    public static final String logFlg_COLUMN = "LOG_FLG";

    public static final String gaisysFlg_COLUMN = "GAISYS_FLG";


    /**
     * メニューID
     */
    private String menuId;

    /**
     * メニュー名称
     */
    private String menuName;

    /**
     * サブメニューID
     */
    private String subMenuId;

    /**
     * サブメニュー名称
     */
    private String subMenuName;

    /**
     * 初期遷移VIEWID
     */
    private String initViewId;

    /**
     * 画面ID
     */
    private String viewId;

    /**
     * URL
     */
    private String url;

    /**
     * 外部リンクID
     */
    private String linkId;

    /**
     * 外部リンクパラム
     */
    private String param;

    /**
     * アイコンPATH
     */
    private String imgPath;

    private String paramUserId;

    private String paramUserPswd;

    private Timestamp disableStaTmp;

    private Timestamp disableEndTmp;

    private String mainteMsg;

    private String logFlg;

    private String gaisysFlg;

    private String dougaName;

    private String dougaCd;

    /**
	 * @return disableEndTmp を戻します。
	 */
	public Timestamp getDisableEndTmp() {
		return disableEndTmp;
	}

	/**
	 * @param disableEndTmp を クラス変数disableEndTmpへ設定します。
	 */
	public void setDisableEndTmp(Timestamp disableEndTmp) {
		this.disableEndTmp = disableEndTmp;
	}

	/**
	 * @return disableStaTmp を戻します。
	 */
	public Timestamp getDisableStaTmp() {
		return disableStaTmp;
	}

	/**
	 * @param disableStaTmp を クラス変数disableStaTmpへ設定します。
	 */
	public void setDisableStaTmp(Timestamp disableStaTmp) {
		this.disableStaTmp = disableStaTmp;
	}

	/**
	 * @return gaisysFlg を戻します。
	 */
	public String getGaisysFlg() {
		return gaisysFlg;
	}

	/**
	 * @param gaisysFlg を クラス変数gaisysFlgへ設定します。
	 */
	public void setGaisysFlg(String gaisysFlg) {
		this.gaisysFlg = gaisysFlg;
	}

	/**
	 * @return logFlg を戻します。
	 */
	public String getLogFlg() {
		return logFlg;
	}

	/**
	 * @param logFlg を クラス変数logFlgへ設定します。
	 */
	public void setLogFlg(String logFlg) {
		this.logFlg = logFlg;
	}

	/**
	 * @return mainteMsg を戻します。
	 */
	public String getMainteMsg() {
		return mainteMsg;
	}

	/**
	 * @param mainteMsg を クラス変数mainteMsgへ設定します。
	 */
	public void setMainteMsg(String mainteMsg) {
		this.mainteMsg = mainteMsg;
	}

	/**
	 * @return menuId を戻します。
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId を クラス変数menuIdへ設定します。
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return menuName を戻します。
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName を クラス変数menuNameへ設定します。
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * @return subMenuId を戻します。
	 */
	public String getSubMenuId() {
		return subMenuId;
	}

	/**
	 * @param subMenuId を クラス変数subMenuIdへ設定します。
	 */
	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}

	/**
	 * @return subMenuName を戻します。
	 */
	public String getSubMenuName() {
		return subMenuName;
	}

	/**
	 * @param subMenuName を クラス変数subMenuNameへ設定します。
	 */
	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	/**
	 * @return initViewId を戻します。
	 */
	public String getInitViewId() {
		return initViewId;
	}

	/**
	 * @param initViewId を クラス変数initViewIdへ設定します。
	 */
	public void setInitViewId(String initViewId) {
		this.initViewId = initViewId;
	}

	/**
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}

	/**
	 * @param viewId を クラス変数viewIdへ設定します。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	/**
	 * @return imgPath を戻します。
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @param imgPath を クラス変数imgPathへ設定します。
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * @return param を戻します。
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param を クラス変数paramへ設定します。
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return linkId を戻します。
	 */
	public String getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId を クラス変数linkIdへ設定します。
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	/**
	 * @return url を戻します。
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url を クラス変数urlへ設定します。
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return paramUserId を戻します。
	 */
	public String getParamUserId() {
		return paramUserId;
	}

	/**
	 * @param paramUserId を クラス変数paramUserIdへ設定します。
	 */
	public void setParamUserId(String paramUserId) {
		this.paramUserId = paramUserId;
	}

	/**
	 * @return paramUserPswd を戻します。
	 */
	public String getParamUserPswd() {
		return paramUserPswd;
	}

	/**
	 * @param paramUserPswd を クラス変数paramUserPswdへ設定します。
	 */
	public void setParamUserPswd(String paramUserPswd) {
		this.paramUserPswd = paramUserPswd;
	}

	public String getDougaName() {
		return dougaName;
	}

	public void setDougaName(String dougaName) {
		this.dougaName = dougaName;
	}

	public String getDougaCd() {
		return dougaCd;
	}

	public void setDougaCd(String dougaCd) {
		this.dougaCd = dougaCd;
	}

}
