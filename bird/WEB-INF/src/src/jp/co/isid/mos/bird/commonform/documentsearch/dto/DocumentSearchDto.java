/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.documentsearch.dto;

import java.util.List;

/**
 * 関連文書選択情報保持用Dto<br>
 * 
 * 使用注意：関連文書検索を呼び出す際には、以下３点の設定が必要<br>
 *   1. 初期化 clear();<br>
 * 　2. 遷移元ページを設定(検索画面から元画面に戻る際のアクションの戻り値の設定)<br>
 * 　3. 検索初期処理起動フラグをtrueに設定
 * @author m.onodera
 */
public class DocumentSearchDto {

    /* 遷移元ページ */
    private String navigationCase;
    /* 検索初期処理起動フラグ */
    private boolean initFlg;
    /* 関連文書選択情報リスト */
    private List documentList;
    /* アクションフラグ */
    private boolean actionFlg;
    
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
     * @return initFlg を戻します。
     */
    public boolean isInitFlg() {
        return initFlg;
    }
    /**
     * 検索初期処理起動フラグの設定
     * @param initFlg initFlg を設定。
     */
    public void setInitFlg(boolean initFlg) {
        this.initFlg = initFlg;
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
     * 関連文書選択情報リストの設定
     * @return documentList を戻します。
     */
    public List getDocumentList() {
        return documentList;
    }
    /**
     * 関連文書選択情報リストの設定
     * @param dcmentList dcmentList を設定。
     */
    public void setDocumentList(List documentList) {
        this.documentList = documentList;
    }

    /**
     * 関連文書情報クリア処理<br>
     */
    public void clear() {
        setInitFlg(false);
        setNavigationCase(null);
        setDocumentList(null);
    }
}
