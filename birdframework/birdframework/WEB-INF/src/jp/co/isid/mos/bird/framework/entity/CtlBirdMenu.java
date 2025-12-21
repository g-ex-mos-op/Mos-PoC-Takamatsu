package jp.co.isid.mos.bird.framework.entity;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

public class CtlBirdMenu {

    public static final String TABLE = "BR02BMNU";

    public static final String menuId_COLUMN = "MENU_ID";

    public static final String menuName_COLUMN = "MENU_NAME";

    public static final String subMenuId_COLUMN = "SUB_MENU_ID";

    public static final String subMenuName_COLUMN = "SUB_MENU_NAME";

    public static final String viewId_COLUMN = "VIEW_ID";

    public static final String pckgName_COLUMN = "PCKG_NAME";

    public static final String initViewId_COLUMN = "INIT_VIEW_ID";

    public static final String param_COLUMN = "PARAM";

    public static final String sortSeq_COLUMN = "SORT_SEQ";

    public static final String menuDispKbn_COLUMN = "MENU_DISP_KBN";

    public static final String url_COLUMN = "URL";

    public static final String linkParam_COLUMN = "LINK_PARAM";

    public static final String paramUserId_COLUMN = "PARAM_USER_ID";

    public static final String paramUserPswd_COLUMN = "PARAM_USER_PSWD";

    public static final String label_COLUMN = "LABEL";

    public static final String readme_COLUMN = "README";

    public static final String linkSort_COLUMN = "LINK_SORT";

    public static final String disableStaTmsp_COLUMN = "DISABLE_STA_TMSP";

    public static final String disableEndTmsp_COLUMN = "DISABLE_END_TMSP";

    public static final String mainteMsg_COLUMN = "MAINTE_MSG";

    public static final String imgPath_COLUMN = "IMG_PATH";

    public static final String logFlg_COLUMN = "LOG_FLG";

    public static final String gaisysFlg_COLUMN = "GAISYS_FLG";

    public static final String enableFlg_COLUMN = "ENABLE_FLG";

    public static final String extraFlg_COLUMN = "EXTRA_FLG";

    public static final String customizeFlg_COLUMN = "CUSTOMIZE_FLG";

    private static final String CSS_SELECT_CLASS_NAME = "menu_current_cell";

    /**
     * メニューID
     */
    private String menuId;

    /**
     * メニュー名
     */
    private String menuName;

    /**
     * サブメニューID
     */
    private String subMenuId;

    /**
     * サブメニュー名
     */
    private String subMenuName;

    /**
     * ビューID
     */
    private String viewId;

    /**
     * パッケージ名
     */
    private String pckgName;

    /**
     * 初期ビューID
     */
    private String initViewId;

    /**
     * パラメータ
     */
    private String param;

    /**
     * ソート順
     */
    private String sortSeq;

    /**
     * メニュー表示区分
     */
    private String menuDispKbn;

    /**
     * URL
     */
    private String url;

    /**
     * 外部リンクパラメータ
     */
    private String linkParam;

    /**
     * ユーザーIDパラメータ名
     */
    private String paramUserId;

    /**
     * パスワードパラメータ名
     */
    private String paramUserPswd;

    /**
     * リンク名称
     */
    private String label;

    /**
     * 説明
     */
    private String readme;

    /**
     * 外部リンクソート順
     */
    private String linkSort;

    /**
     * 使用不可開始日時
     */
    private Timestamp disableStaTmsp;

    /**
     * 使用不可終了日時
     */
    private Timestamp disableEndTmsp;

    /**
     * メンテナンスメッセージ
     */
    private String mainteMsg;

    /**
     * 画像アイコン名
     */
    private String imgPath;

    /**
     * ログフラグ
     */
    private String logFlg;

    /**
     * 外部システムフラグ
     */
    private String gaisysFlg;

    /**
     * 上限拡張フラグ
     */
    private String enableFlg;

    /**
     * 個別設定フラグ
     */
    private String extraFlg;

    /**
     * メニュー表示区分
     */
    private String customizeFlg;


    /**
     * サブメニュー
     */
    private List subMenuList;

    /**
     * 現在選択されているか
     */
    private boolean selectFlg;

    /**
     * メニューIDを取得します。
     * @return メニューID
     */
    public String getMenuId() {
        return menuId;
    }
    /**
     * メニューIDを設定します。
     * @param menuId メニューID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * メニュー名を取得します。
     * @return メニュー名
     */
    public String getMenuName() {
        return menuName;
    }
    /**
     * メニュー名を設定します。
     * @param menuName メニュー名
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * サブメニューIDを取得します。
     * @return サブメニューID
     */
    public String getSubMenuId() {
        return subMenuId;
    }
    /**
     * サブメニューIDを設定します。
     * @param subMenuId サブメニューID
     */
    public void setSubMenuId(String subMenuId) {
        this.subMenuId = subMenuId;
    }

    /**
     * サブメニュー名を取得します。
     * @return サブメニュー名
     */
    public String getSubMenuName() {
        if(subMenuName != null){
            return subMenuName.trim();
        }
        return "";
    }
    /**
     * サブメニュー名を設定します。
     * @param subMenuName サブメニュー名
     */
    public void setSubMenuName(String subMenuName) {
        this.subMenuName = subMenuName;
    }

    /**
     * ビューIDを取得します。
     * @return ビューID
     */
    public String getViewId() {
        return viewId;
    }
    /**
     * ビューIDを設定します。
     * @param viewId ビューID
     */
    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    /**
     * パッケージ名を取得します。
     * @return パッケージ名
     */
    public String getPckgName() {
        if(pckgName != null){
            return pckgName.trim();
        }
        return "";
    }
    /**
     * パッケージ名を設定します。
     * @param pckgName パッケージ名
     */
    public void setPckgName(String pckgName) {
        this.pckgName = pckgName;
    }

    /**
     * 初期ビューIDを取得します。
     * @return 初期ビューID
     */
    public String getInitViewId() {
        if(initViewId != null){
            return initViewId.trim();
        }
        return "";
    }
    /**
     * 初期ビューIDを設定します。
     * @param initViewId 初期ビューID
     */
    public void setInitViewId(String initViewId) {
        this.initViewId = initViewId;
    }

    /**
     * パラメータを取得します。
     * @return パラメータ
     */
    public String getParam() {
        if(param != null){
            return param.trim();
        }
        return "";
    }
    /**
     * パラメータを設定します。
     * @param param パラメータ
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * ソート順を取得します。
     * @return ソート順
     */
    public String getSortSeq() {
        return sortSeq;
    }
    /**
     * ソート順を設定します。
     * @param sortSeq ソート順
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }

    /**
     * メニュー表示区分を取得します。
     * @return メニュー表示区分
     */
    public String getMenuDispKbn() {
        return menuDispKbn;
    }
    /**
     * メニュー表示区分を設定します。
     * @param menuDispKbn メニュー表示区分
     */
    public void setMenuDispKbn(String menuDispKbn) {
        this.menuDispKbn = menuDispKbn;
    }

    /**
     * URLを取得します。
     * @return URL
     */
    public String getUrl() {
        return url;
    }
    /**
     * URLを設定します。
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 外部リンクパラメータを取得します。
     * @return 外部リンクパラメータ
     */
    public String getLinkParam() {
        return linkParam;
    }
    /**
     * 外部リンクパラメータを設定します。
     * @param linkParam 外部リンクパラメータ
     */
    public void setLinkParam(String linkParam) {
        this.linkParam = linkParam;
    }

    /**
     * ユーザーIDパラメータ名を取得します。
     * @return ユーザーIDパラメータ名
     */
    public String getParamUserId() {
        return paramUserId;
    }
    /**
     * ユーザーIDパラメータ名を設定します。
     * @param paramUserId ユーザーIDパラメータ名
     */
    public void setParamUserId(String paramUserId) {
        this.paramUserId = paramUserId;
    }

    /**
     * パスワードパラメータ名を取得します。
     * @return パスワードパラメータ名
     */
    public String getParamUserPswd() {
        return paramUserPswd;
    }
    /**
     * パスワードパラメータ名を設定します。
     * @param paramUserPswd パスワードパラメータ名
     */
    public void setParamUserPswd(String paramUserPswd) {
        this.paramUserPswd = paramUserPswd;
    }

    /**
     * リンク名称を取得します。
     * @return リンク名称
     */
    public String getLabel() {
        return label;
    }
    /**
     * リンク名称を設定します。
     * @param label リンク名称
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 説明を取得します。
     * @return 説明
     */
    public String getReadme() {
        return readme;
    }
    /**
     * 説明を設定します。
     * @param readme 説明
     */
    public void setReadme(String readme) {
        this.readme = readme;
    }

    /**
     * 外部リンクソート順を取得します。
     * @return 外部リンクソート順
     */
    public String getLinkSort() {
        return linkSort;
    }
    /**
     * 外部リンクソート順を設定します。
     * @param linkSort 外部リンクソート順
     */
    public void setLinkSort(String linkSort) {
        this.linkSort = linkSort;
    }

    /**
     * 使用不可開始日時を取得します。
     * @return 使用不可開始日時
     */
    public Timestamp getDisableStaTmsp() {
        return disableStaTmsp;
    }
    /**
     * 使用不可開始日時を設定します。
     * @param disableStaTmsp 使用不可開始日時
     */
    public void setDisableStaTmsp(Timestamp disableStaTmsp) {
        this.disableStaTmsp = disableStaTmsp;
    }

    /**
     * 使用不可終了日時を取得します。
     * @return 使用不可終了日時
     */
    public Timestamp getDisableEndTmsp() {
        return disableEndTmsp;
    }
    /**
     * 使用不可終了日時を設定します。
     * @param disableEndTmsp 使用不可終了日時
     */
    public void setDisableEndTmsp(Timestamp disableEndTmsp) {
        this.disableEndTmsp = disableEndTmsp;
    }

    /**
     * メンテナンスメッセージを取得します。
     * @return メンテナンスメッセージ
     */
    public String getMainteMsg() {
        return mainteMsg;
    }
    /**
     * メンテナンスメッセージを設定します。
     * @param mainteMsg メンテナンスメッセージ
     */
    public void setMainteMsg(String mainteMsg) {
        this.mainteMsg = mainteMsg;
    }

    /**
     * 画像アイコン名を取得します。
     * @return 画像アイコン名
     */
    public String getImgPath() {
        return imgPath;
    }
    /**
     * 画像アイコン名を設定します。
     * @param imgPath 画像アイコン名
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * ログフラグを取得します。
     * @return ログフラグ
     */
    public String getLogFlg() {
        return logFlg;
    }
    /**
     * ログフラグを設定します。
     * @param logFlg ログフラグ
     */
    public void setLogFlg(String logFlg) {
        this.logFlg = logFlg;
    }

    /**
     * 外部システムフラグを取得します。
     * @return 外部システムフラグ
     */
    public String getGaisysFlg() {
        return gaisysFlg;
    }
    /**
     * 外部システムフラグを設定します。
     * @param gaisysFlg 外部システムフラグ
     */
    public void setGaisysFlg(String gaisysFlg) {
        this.gaisysFlg = gaisysFlg;
    }

    /**
     * 上限拡張フラグを取得します。
     * @return 上限拡張フラグ
     */
    public String getEnableFlg() {
        return enableFlg;
    }
    /**
     * 上限拡張フラグを設定します。
     * @param enableFlg 上限拡張フラグ
     */
    public void setEnableFlg(String enableFlg) {
        this.enableFlg = enableFlg;
    }

    /**
     * 個別設定フラグを取得します。
     * @return 個別設定フラグ
     */
    public String getExtraFlg() {
        return extraFlg;
    }
    /**
     * 個別設定フラグを設定します。
     * @param extraFlg 個別設定フラグ
     */
    public void setExtraFlg(String extraFlg) {
        this.extraFlg = extraFlg;
    }

    /**
     * メニュー表示区分を取得します。
     * @return メニュー表示区分
     */
    public String getCustomizeFlg() {
        return customizeFlg;
    }
    /**
     * メニュー表示区分を設定します。
     * @param customizeFlg メニュー表示区分
     */
    public void setCustomizeFlg(String customizeFlg) {
        this.customizeFlg = customizeFlg;
    }

    /**
     * レイヤー名を取得します。
     * @return レイヤー名
     */
    public String getLayerName() {
        String layer = "Layer";
        if("00".equals(getMenuId())){
            return layer +  getSubMenuId();
        }
        return layer +  getMenuId();
    }
    public boolean isSelectFlg() {
        return selectFlg;
    }
    public void setSelectFlg(boolean selectFlg) {
        this.selectFlg = selectFlg;
    }
    public List getSubMenuList() {
        return subMenuList;
    }
    public void setSubMenuList(List subMenuList) {
        this.subMenuList = subMenuList;
    }

    /**
     * cssのクラス名を取得します。
     * @return cssクラス名
     */
    public String getCssClassName(){
        if(isSelectFlg()){
            return CSS_SELECT_CLASS_NAME;
        }
        return getViewId();
    }
    /**
     * サブメニュー名称Byte上桁数取得処理
     *
     * @return int Byte桁数
     * @version 2011/04/15
     */
    public int getSubMenuNameByteLength() {
    	if(getSubMenuName() != null) {
    		try {
				return getSubMenuName().trim().getBytes("Windows-31j").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    	}
    	return 0;
    }
}
