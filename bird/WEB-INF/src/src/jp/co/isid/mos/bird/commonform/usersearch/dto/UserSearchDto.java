/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.usersearch.dto;

import java.util.List;

/**
 * ユーザー情報保持用Dto<br>
 * 
 * 使用注意：ユーザー検索を呼び出す際には、以下２点の設定が必要<br>
 * 　1. 初期化 clear();<br>
 * 　2. 遷移元ページを設定(検索画面から元画面に戻る際のアクションの戻り値の設定)<br>
 * 　3. 検索初期処理起動フラグをtrueに設定
 * 
 * 更新日: 2007/04/24 複数ウィンドウ対応
 * @author m.onodera
 */
public class UserSearchDto {

    public static final int RETURNKIND_INIT = 0;
    public static final int RETURNKIND_SELECT = 1;
    public static final int RETURNKIND_CANCEL = 2;
    
    /* 遷移元ページ */
    private String navigationCase;
    /* ユーザーコード */
    private String userId;
    /* ユーザー名称 */
    private String userName;
    /* ユーザー名称（かな）*/
    private String userNameKana;
    /* 企業コードリスト */
    private List rCompanyCdList;
    /* 検索初期処理起動フラグ */
    private boolean initFlag;
    /* アクションフラグ */
    private boolean actionFlg;
    /* 遷移区分要否フラグ */
    private boolean needReturnKind;
    /* 遷移区分 =0:初期値 =1:決定 =2:戻る */
    private int returnKind;
    /* ウインドウID */
    private int windowId;
    
    /**
     * 遷移元ページの設定
     * @return 遷移元情報 を戻します。
     */
    public String getNavigationCase() {
        return navigationCase;
    }
    /**
     * 遷移元ページの設定
     * @param navigationCase 遷移元情報を設定。
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }
    
    /**
     * ユーザーID設定
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * ユーザーID設定
     * @param userId userId を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * ユーザー名称設定
     * @return userName を戻します。
     */
    public String getUserName() {
        return userName;
    }
    /**
     * ユーザー名称設定
     * @param userName userName を設定。
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * ユーザー名称（カナ）設定
     * @return userNameKana を戻します。
     */
    public String getUserNameKana() {
        return userNameKana;
    }
    /**
     * ユーザー名称（カナ）設定
     * @param userNameKana userNameKana を設定。
     */
    public void setUserNameKana(String userNameKana) {
        this.userNameKana = userNameKana;
    }
   
    /**
     * 検索初期処理起動フラグの設定
     * @return initFlag を戻します。
     */
    public boolean isInitFlag() {
        return initFlag;
    }
    /**
     * 検索初期処理起動フラグの設定
     * @param initFlag initFlag を設定。
     */
    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }
    /**
     * アクションフラグの設定
     * @return actionFlg を戻します。
     */
    public boolean isActionFlg() {
        return actionFlg;
    }
    /**
     * アクションフラグの設定
     * @param actionFlg actionFlg を設定。
     */
    public void setActionFlg(boolean actionFlg) {
        this.actionFlg = actionFlg;
    }

    /**
     * 企業コードリストを取得します。
     * 
     * @return 企業コードリスト
     */
    public List getRCompanyCdList() {
        return rCompanyCdList;
    }

    /**
     * 企業コードリストを設定します。
     * 
     * @param rCompanyCdList 企業コードリスト
     */
    public void setRCompanyCdList(List rCompanyCdList) {
        this.rCompanyCdList = rCompanyCdList;
    }
    
    /**
     * ユーザー情報クリア処理<br>
     */
    public void clear() {
        setInitFlag(false);
        setUserId(null);
        setUserName(null);
        setUserNameKana(null);
        setNavigationCase(null);
        setNeedReturnKind(false);
        setActionFlg(false);
        setReturnKind(RETURNKIND_INIT);
        setWindowId(0);
    }
    
    public boolean isNeedReturnKind() {
        return needReturnKind;
    }
    public void setNeedReturnKind(boolean needReturnKind) {
        this.needReturnKind = needReturnKind;
    }
    public int getReturnKind() {
        return returnKind;
    }
    public void setReturnKind(int returnKind) {
        this.returnKind = returnKind;
    }
    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
}
