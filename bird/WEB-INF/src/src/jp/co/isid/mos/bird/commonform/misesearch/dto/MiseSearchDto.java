/*
 * 作成日: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.dto;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public class MiseSearchDto {

    public static final int RETURNKIND_INIT = 0;
    public static final int RETURNKIND_SELECT = 1;
    public static final int RETURNKIND_CANCEL = 2;

    /**
     * 遷移元情報
     */
    private String navigationCase;

    /**
     * 初期処理フラグ
     */
    private boolean initialFlag;

    /**
     * 遷移区分要否フラグ
     */
    private boolean needReturnKind;

    /**
     * 企業コードリスト
     */
    private List rCompanyCdList;

    /**
     * アクションフラグ
     * 
     * true:決定 false:戻る
     */
    private boolean actionFlg;

    /**
     * 遷移区分
     * =0:初期値 =1:決定 =2:戻る
     */
    private int returnKind;

    /**
     * 選択店コード
     */
    private String miseCd;

    /**
     * ウインドウID
     */
    private int windowId;

    /** 
     * 遷移元ウィンドウID
     * 複数ウィンドウ対応用
     */
    private String returnWindowId;

    /**
     * 遷移元情報を取得します。
     * 
     * @return 遷移元情報
     */
    public String getNavigationCase() {
        return navigationCase;
    }

    /**
     * 遷移元情報を設定します。
     * 
     * @param navigationCase 遷移元情報
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
    }

    /**
     * 初期処理フラグを取得します。
     * 
     * @return 初期処理フラグ
     */
    public boolean isInitialFlag() {
        return initialFlag;
    }

    /**
     * 初期処理フラグを設定します。
     * 
     * @param initialFlag 初期処理フラグ
     */
    public void setInitialFlag(boolean initialFlag) {
        this.initialFlag = initialFlag;
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
     * アクションフラグを取得します。
     * 
     * @return アクションフラグ
     */
    public boolean isActionFlg() {
        return actionFlg;
    }

    /**
     * アクションフラグを設定します。
     * 
     * @param actionFlg アクションフラグ
     */
    public void setActionFlg(boolean actionFlg) {
        this.actionFlg = actionFlg;
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
     * 選択店コードを取得します。
     * 
     * @return 選択店コード
     */
	public String getMiseCd() {
		return miseCd;
	}

    /**
     * 選択店コードを設定します。
     * 
     * @param miseCd 選択店コード
     */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
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
     * 遷移元ウィンドウID情報を取得します。
     * 
     * @return 遷移元ウィンドウID情報
     */
    public String getReturnWindowId() {
        return returnWindowId;
    }

    /**
     * 遷移元ウィンドウID情報を設定します。
     * 
     * @param navigationCase 遷移元ウィンドウID情報
     */
    public void setReturnWindowId(String id) {
        this.returnWindowId = id;
    }

    /**
     * 初期化
     */
    public void clear() {
        setInitialFlag(false);
        setNeedReturnKind(false);
        setNavigationCase(null);
        setRCompanyCdList(null);

        setActionFlg(false);
        setReturnKind(RETURNKIND_INIT);
        setMiseCd(null);

        setWindowId(0);
        setReturnWindowId(null);
    }
}
