/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.functionsearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.functionsearch.entity.UIFunction;

/**
 * 機能選択情報取得
 * @author itamoto
 */
public interface UIFunctionDao {

    public static final Class BEAN = UIFunction.class;
    public static final String select_ARGS = "SUB_MENU_ID, SUB_MENU_NAME";

    /**
     * 機能カテゴリ情報を取得
     * @return List      検索結果
     */
    public List selectMainMenu();

    /**
     * 機能情報を取得
     * @param functionCategory 機能カテゴリ
     * @param functionName 機能名
     * @return List      検索結果
     */
    public List select(String functionCategory, String functionName);
}
