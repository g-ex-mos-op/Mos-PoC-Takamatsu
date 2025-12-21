/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.functionsearch.dto;

import java.util.List;

/**
 * 機能検索処理Dto
 * @author itamoto
 */
public class FunctionSearchConditionDto {

    /* 機能カテゴリID */
    private String functionCategoryId;
    /* 機能カテゴリ名 */
    private String functionCategoryName;
    /* 機能名 */
    private String functionName;
    /* categoryList */
    private List categoryList;
    /* 検索結果機能リストSize */
    private int functionListSize;

    /**
     * 機能カテゴリIDの設定
     * @return functionCategoryId を戻します。
     */
    public String getFunctionCategoryId() {
        return functionCategoryId;
    }
    /**
     * 機能カテゴリIDの設定
     * @param functionCategoryId functionCategoryId を設定。
     */
    public void setFunctionCategoryId(String functionCategoryId) {
        this.functionCategoryId = functionCategoryId;
    }
    /**
     * カテゴリリストの設定
     * @return categoryList を戻します。
     */
    public List getCategoryList() {
        return categoryList;
    }
    /**
     * カテゴリリストの設定
     * @param categoryList categoryList を設定。
     */
    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }
    /**
     * 機能カテゴリ名の設定
     * @return functionCategory を戻します。
     */
    public String getFunctionCategoryName() {
        return functionCategoryName;
    }
    /**
     * 機能カテゴリ名の設定
     * @param functionCategory functionCategory を設定。
     */
    public void setFunctionCategoryName(String functionCategoryName) {
        this.functionCategoryName = functionCategoryName;
    }
    /**
     * 機能リストサイズの設定
     * @return functionListSize を戻します。
     */
    public int getFunctionListSize() {
        return functionListSize;
    }
    /**
     * 機能リストサイズの設定
     * @param functionListSize functionListSize を設定。
     */
    public void setFunctionListSize(int functionListSize) {
        this.functionListSize = functionListSize;
    }
    /**
     * 機能名の設定
     * @return functionName を戻します。
     */
    public String getFunctionName() {
        return functionName;
    }
    /**
     * 機能名の設定
     * @param functionName functionName を設定。
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     * 検索情報クリア処理<br>
     * @param flag
     */
    public void clear() {
        this.setCategoryList(null);
        this.setFunctionCategoryId(null);
        this.setFunctionCategoryName(null);
        this.setFunctionListSize(0);
        this.setFunctionName(null);
    }
}
