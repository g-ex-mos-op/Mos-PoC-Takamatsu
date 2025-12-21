/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.commonform.functionsearch.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.functionsearch.action.FunctionSearchAction;
import jp.co.isid.mos.bird.commonform.functionsearch.dto.FunctionSearchConditionDto;
import jp.co.isid.mos.bird.commonform.functionsearch.dto.FunctionSearchDto;
import jp.co.isid.mos.bird.commonform.functionsearch.entity.UIFunction;
import jp.co.isid.mos.bird.commonform.functionsearch.logic.SearchFunctionCategoryLogic;
import jp.co.isid.mos.bird.commonform.functionsearch.logic.SearchFunctionLogic;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 機能選択処理アクションクラス
 * @author itamoto
 */
public class FunctionSearchActionImpl implements FunctionSearchAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BCO005A01";
    public static String select_ACTION_ID       = "BCO005A02";
    public static String search_ACTION_ID       = "BCO005A03";
    public static String cancel_ACTION_ID       = "BCO005A04";

    /* 機能情報Dto */
    private FunctionSearchDto functionSearchDto;
    /* 機能選択検索用Dto */
    private FunctionSearchConditionDto functionSearchConditionDto;
    /* SearchFunctionCategoryLogic */
    private SearchFunctionCategoryLogic searchFunctionCategoryLogic;
    /* SearchFunctionLogic */
    private SearchFunctionLogic searchFunctionLogic;
    /* 機能リスト */
    private List functionList;
    
    /**
     * 機能情報Dto設定処理
     * @return functionSearchDto
     */
    public FunctionSearchDto getFunctionSearchDto() {
        return functionSearchDto;
    }
    /**
     * 機能情報Dto設定処理
     * @param functionSearchDto
     */
    public void setFunctionSearchDto(FunctionSearchDto functionSearchDto) {
        this.functionSearchDto = functionSearchDto;
    }

    /**
     * 機能検索用Dto設定処理
     * @return functionSearchConditionDto
     */
    public FunctionSearchConditionDto getFunctionSearchConditionDto() {
        return functionSearchConditionDto;
    }
    /**
     * 機能検索用Dto設定処理
     * @param functionSearchConditionDto
     */
    public void setFunctionSearchConditionDto(
            FunctionSearchConditionDto functionSearchConditionDto) {
        this.functionSearchConditionDto = functionSearchConditionDto;
    }

    /**
     * 機能カテゴリリスト用ロジック設定処理
     * @return searchFunctionLogic
     */
    public SearchFunctionCategoryLogic getSearchFunctionCategoryLogic() {
        return searchFunctionCategoryLogic;
    }
    /**
     * 機能カテゴリリスト用ロジック設定処理
     * @param searchFunctionLogic
     */
    public void setSearchFunctionCategoryLogic(
            SearchFunctionCategoryLogic searchFunctionCategoryLogic) {
        this.searchFunctionCategoryLogic = searchFunctionCategoryLogic;
    }

    /**
     * 機能検索用ロジック設定処理
     * @return searchFunctionLogic
     */
    public SearchFunctionLogic getSearchFunctionLogic() {
        return searchFunctionLogic;
    }
    /**
     * 機能検索用ロジック設定処理
     * @param searchFunctionLogic
     */
    public void setSearchFunctionLogic(SearchFunctionLogic searchFunctionLogic) {
        this.searchFunctionLogic = searchFunctionLogic;
    }

    /**
     * 機能リストの設定処理
     * @return functionList を戻します。
     */
    public List getFunctionList() {
        return functionList;
    }
    /**
     * 機能リストの設定処理
     * @param functionList functionList を設定。
     */
    public void setFunctionList(List functionList) {
        this.functionList = functionList;
    }

    /**
     * 機能選択初期処理
     * @return 画面遷移情報
	 */
	public String initialize() {
	    if (functionSearchDto.isInitFlag()) {
            // 検索情報初期化
            functionSearchConditionDto.clear();
// start 20060418 xkhata 選択無し時の遷移対応
            setFunctionList( new ArrayList() );
// end
	        // 機能カテゴリリスト取得
            functionSearchConditionDto
                    .setCategoryList(searchFunctionCategoryLogic
                            .execute(functionSearchConditionDto));
            // initフラグOFF
            functionSearchDto.setInitFlag(false);
        }
        return null;
    }

    /**
     * 機能検索処理
     * @return 画面遷移情報
	 */
	public String search() {
        // 検索結果取得
        setFunctionList(searchFunctionLogic.execute(functionSearchConditionDto));
        // ０件チェック
        if (functionList.size() <= 0) {
            throw new NoResultException("");
        } else {
            // データ件数セット
            functionSearchConditionDto.setFunctionListSize(functionList.size());
        }
        return null;
    }

    /**
     * 機能選択決定処理
     * @return 画面遷移情報
     */
	public String select() {
        // 選択情報チェック
        validate();
        
        // 機能選択DTOに選択情報をセット
        for (Iterator i = functionList.iterator(); i.hasNext();) {
            UIFunction entity = (UIFunction) i.next();
            if (!entity.isCheckFlg()) {
                i.remove();
            }
        }
        functionSearchDto.setFunctionList(functionList);

        // アクションフラグtrue
        functionSearchDto.setActionFlag(true);
        // 検索情報初期化
        functionSearchConditionDto.clear();
        return functionSearchDto.getNavigationCase();
	}

    /**
     * 機能選択取消処理
     * @return 画面遷移情報
	 */
	public String cancel() {
        // 検索情報初期化
        functionSearchConditionDto.clear();
        // アクションフラグfalse
        functionSearchDto.setActionFlag(false);
        return functionSearchDto.getNavigationCase();
	}

    /**
     * 入力チェック
     */
    private void validate() {
        // 選択情報チェックボックス 1つ以上選択
        boolean flg = false;
        for (Iterator i = functionList.iterator(); i.hasNext();) {
            UIFunction entity = (UIFunction) i.next();
            if (entity.isCheckFlg()) {
                flg = true;
                break;
            }
        }
        if (!flg) {
            throw new NotNullException("機能選択", "", true);
        }
    }
}

