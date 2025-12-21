/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.functionsearch.dto;

import java.util.List;

/**
 * 機能選択情報保持用Dto<br>
 * 
 * 使用注意：機能検索を呼び出す際には、以下３点の設定が必要<br>
 *   1. 初期化 clear();<br>
 * 　2. 遷移元ページを設定(検索画面から元画面に戻る際のアクションの戻り値の設定)<br>
 * 　3. 検索初期処理起動フラグをtrueに設定
 * @author itamoto
 */
public class FunctionSearchDto {

    /* 遷移元ページ */
    private String navigationCase;
    /* 検索初期処理起動フラグ */
    private boolean initFlag;
    /* 機能選択情報リスト */
    private List functionList;
    /* アクションフラグ */
    private boolean actionFlag;
    
    /**
     * 遷移元ページの設定
     * @return navigationCase を戻します。
     */
    public String getNavigationCase() {
        return navigationCase;
    }
    /**
     * 遷移元ページの設定
     * @param navigationCase navigationCase を設定。
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase = navigationCase;
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
     * 機能選択情報リストの設定
     * @return functionList を戻します。
     */
    public List getFunctionList() {
        return functionList;
    }
    /**
     * 機能選択情報リストの設定
     * @param functionList functionList を設定。
     */
    public void setFunctionList(List functionList) {
        this.functionList = functionList;
    }
    
    /**
     * アクションフラグの設定
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
     * 機能情報クリア処理<br>
     */
    public void clear() {
        setInitFlag(false);
        setNavigationCase(null);
        setFunctionList(null);
    }
}
