/*
 * 作成日: 2006/01/20
 *
 */
package jp.co.isid.mos.bird.commonform.businesssearch.dto;

import java.util.List;

/**
 * @author xyuchida
 *
 */
public class BusinessSearchDto {

    public static final int RETURNKIND_INIT = 0;
    public static final int RETURNKIND_SELECT = 1;
    public static final int RETURNKIND_CANCEL = 2;
	/**
	 * 遷移元ウィンドウＩＤ
	 */
    private int windowId;
	/**
	 * 遷移元情報
	 */
    private String navigationCase;

    /**
     * アクションフラグ
     * 
     * true:決定（選択済み） false:戻る
     */
    private boolean actionFlg;

    /**
     * 遷移区分
     * =0:初期値 =1:決定 =2:戻る
     */
    private int returnKind = RETURNKIND_INIT;
    /**
     * 遷移区分要否フラグ
     */
    private boolean needReturnKind;
    /**
	 * 結果業態リスト
	 */
	private List resultGyotaiList;

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
     * 結果業態リストを取得します。
     * 
     * @return 結果業態リスト
     */
	public List getResultGyotaiList() {
		return resultGyotaiList;
	}

    /**
     * 結果業態リストを設定します。
     * 
     * @param resultGyotaiList 結果業態リスト
     */
	public void setResultGyotaiList(List resultGyotaiList) {
		this.resultGyotaiList = resultGyotaiList;
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
	 * @return クラス変数returnKind を戻します。
	 */
	public int getReturnKind() {
		return returnKind;
	}

	/**
	 * @param returnKind を クラス変数returnKindへ設定します。
	 */
	public void setReturnKind(int returnKind) {
		this.returnKind = returnKind;
	}
    /**
     * 初期化
     */
    public void clear() {
        setNeedReturnKind(false);
        setNavigationCase(null);
        setResultGyotaiList(null);

        setActionFlg(false);
        setReturnKind(RETURNKIND_INIT);

        setWindowId(0);
    }

	/**
	 * @return クラス変数needReturnKind を戻します。
	 */
	public boolean isNeedReturnKind() {
		return needReturnKind;
	}

	/**
	 * @param needReturnKind を クラス変数needReturnKindへ設定します。
	 */
	public void setNeedReturnKind(boolean needReturnKind) {
		this.needReturnKind = needReturnKind;
	}
}
