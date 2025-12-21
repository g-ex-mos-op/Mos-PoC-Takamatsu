/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.dto;

import java.util.List;

/**
 * オーナ情報保持用Dto<br>
 * 
 * 使用注意：オーナ検索を呼び出す際には、以下３点の設定が必要<br>
 *   1. 初期化 clear();<br>
 * 　2. 遷移元ページを設定(検索画面から元画面に戻る際のアクションの戻り値の設定)<br>
 * 　3. 検索初期処理起動フラグをtrueに設定
 * @author itamoto
 */
public class OwnerSearchDto {

    public static final int RETURNKIND_INIT = 0;
    public static final int RETURNKIND_SELECT = 1;
    public static final int RETURNKIND_CANCEL = 2;

    /* 遷移元ページ */
    private String navigationCase;
    /* 企業コード */
    private String companyCd;
    /* オーナコード */
    private String onerCd;
    /* オーナ名称 */
    private String onerNameKj;
    /* オーナ名称（かな）*/
    private String onerNameKna;
    /* 検索初期処理起動フラグ */
    private boolean initFlag;
    /* 遷移区分要否フラグ */
    private boolean needReturnKind;
    /* アクションフラグ */
    private boolean actionFlag;
    /* 遷移区分 =0:初期値 =1:決定 =2:戻る */
    private int returnKind;
    
    /* 企業コードリスト(検索用 会社リスト指定用) */
    private List rCompanyCdList;

    /* ウインドウID */
    private int windowId;

    /**
     * 遷移元ページの取得
     * @return urlMapping を戻します。
     */
    public String getNavigationCase() {
        return navigationCase;
    }
    /**
     * 遷移元ページの設定
     * @param urlMapping urlMapping を設定。
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    /**
     * 企業コード取得
     * @return companyCd を戻します。
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コード設定
     * @param companyCd companyCd を設定。
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    /**
     * オーナコード取得
     * @return onerCd を戻します。
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * オーナコード設定
     * @param onerCd onerCd を設定。
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    /**
     * オーナ名称取得
     * @return onerNameKj を戻します。
     */
    public String getOnerNameKj() {
        return onerNameKj;
    }
    /**
     * オーナ名称設定
     * @param onerNameKj onerNameKj を設定。
     */
    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }
    /**
     * オーナ名称（カナ）取得
     * @return onerNameKna を戻します。
     */
    public String getOnerNameKna() {
        return onerNameKna;
    }
    /**
     * オーナ名称（カナ）設定
     * @param onerNameKna onerNameKna を設定。
     */
    public void setOnerNameKna(String onerNameKna) {
        this.onerNameKna = onerNameKna;
    }
    /**
     * 検索初期処理起動フラグの取得
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
     * 遷移区分要否フラグ取得
     * @return 遷移区分要否フラグ
     */
    public boolean isNeedReturnKind() {
        return needReturnKind;
    }
    /**
     * 遷移区分要否フラグ設定
     * @param needReturnKind 遷移区分要否フラグ
     */
    public void setNeedReturnKind(boolean needReturnKind) {
        this.needReturnKind = needReturnKind;
    }
    /**
     * アクションフラグの取得
     * @return actionFlag を戻します。
     */
    public boolean isActionFlag() {
        return actionFlag;
    }
    /**
     * アクションフラグの設定
     * @param actionFlag actionFlag を設定。
     */
    public void setActionFlag(boolean actionFlag) {
        this.actionFlag = actionFlag;
    }
    /**
     * 遷移区分取得
     * @return 遷移区分
     */
    public int getReturnKind() {
        return returnKind;
    }
    /**
     * 遷移区分設定
     * @param returnKind 遷移区分
     */
    public void setReturnKind(int returnKind) {
        this.returnKind = returnKind;
    }

    /**
     * 企業コードリストを取得します。
     * @return 企業コードリスト
     */
    public List getRCompanyCdList() {
        return rCompanyCdList;
    }
    /**
     * 企業コードリストを設定します。
     * @param rCompanyCdList 企業コードリスト
     */
    public void setRCompanyCdList(List rCompanyCdList) {
        this.rCompanyCdList = rCompanyCdList;
    }

    /**
     * ウインドウID取得
     * @return ウインドウID
     */
	public int getWindowId() {
		return windowId;
	}
    /**
     * ウインドウID設定
     * @param windowId ウインドウID
     */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

    /**
     * オーナ情報クリア処理<br>
     */
    public void clear() {
        setInitFlag(false);
        setOnerCd(null);
        setOnerNameKj(null);
        setOnerNameKna(null);
        setNavigationCase(null);
        setCompanyCd(null);
        setNeedReturnKind(false);
        setActionFlag(false);
        setReturnKind(RETURNKIND_INIT);
        setRCompanyCdList(null);
        setWindowId(0);
    }
}
