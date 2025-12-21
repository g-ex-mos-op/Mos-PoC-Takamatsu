/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.action.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.action.CategoryRegistEditAction;
import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;
import jp.co.isid.mos.bird.config.categoryregist.entity.CategoryRegistEntity;
import jp.co.isid.mos.bird.config.categoryregist.logic.CheckCategoryLogic;
import jp.co.isid.mos.bird.config.categoryregist.logic.CreateBlankCategoryLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * @author xyuchida
 *
 */
public class CategoryRegistEditActionImpl implements CategoryRegistEditAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BCF004A04";
    public static final String cancel_ACTION_ID = "BCF004A05";
    public static final String confirm_ACTION_ID = "BCF004A06";
    public static final String addLine_ACTION_ID = "BCF004A07";

    private static final String VIEWID_CONDITION = "BCF004V01";
    private static final String VIEWID_CONFIRM = "BCF004V03";

    /**
     * カテゴリ登録DTO
     */
    private CategoryRegistDto categoryRegistDto;

    /**
     * 登録内容チェックロジック
     */
    private CheckCategoryLogic checkCategoryLogic;

    /**
     * ブランクカテゴリ生成ロジック
     */
    private CreateBlankCategoryLogic createBlankCategoryLogic;

    /**
     * ソート
     */
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((CategoryRegistEntity) obj1).getSortKey();
            
            String val2 = ((CategoryRegistEntity) obj2).getSortKey();
            
            return val1.compareTo(val2);
        }
    }

    /**
     * カテゴリ登録DTOを取得します。
     * @return カテゴリ登録DTO
     */
    public CategoryRegistDto getCategoryRegistDto() {
        return categoryRegistDto;
    }

    /**
     * カテゴリ登録DTOを設定します。
     * @param categoryRegistDto カテゴリ登録DTO
     */
    public void setCategoryRegistDto(CategoryRegistDto categoryRegistDto) {
        this.categoryRegistDto = categoryRegistDto;
    }

    /**
     * 登録内容チェックロジックを取得します。
     * @return 登録内容チェックロジック
     */
    public CheckCategoryLogic getCheckCategoryLogic() {
        return checkCategoryLogic;
    }

    /**
     * 登録内容チェックロジックを設定します。
     * @param checkCategoryLogic 登録内容チェックロジック
     */
    public void setCheckCategoryLogic(CheckCategoryLogic checkCategoryLogic) {
        this.checkCategoryLogic = checkCategoryLogic;
    }

    /**
     * ブランクカテゴリ生成ロジックを取得します。
     * @return ブランクカテゴリ生成ロジック
     */
    public CreateBlankCategoryLogic getCreateBlankCategoryLogic() {
        return createBlankCategoryLogic;
    }

    /**
     * ブランクカテゴリ生成ロジックを設定します。
     * @param createBlankCategoryLogic ブランクカテゴリ生成ロジック
     */
    public void setCreateBlankCategoryLogic(
            CreateBlankCategoryLogic createBlankCategoryLogic) {
        this.createBlankCategoryLogic = createBlankCategoryLogic;
    }

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        // 自画面へ遷移
        return null;
    }

    /**
     * 確認
     * 
     * @return 画面遷移情報
     */
    public String confirm() {

        // 入力チェック
        getCheckCategoryLogic().execute(getCategoryRegistDto());

        // 空白行除去
        removeBlankCategory(getCategoryRegistDto().getEditList());
        
        // ソート順設定
        doSortKey();

        // 確認画面へ遷移
        return VIEWID_CONFIRM;
    }

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String cancel() {

        // 条件画面へ遷移
        return VIEWID_CONDITION;
    }

    /**
     * 行追加
     * 
     * @return 画面遷移情報
     */
    public String addLine() {

        // 空白行除去
        removeBlankCategory(getCategoryRegistDto().getEditList());

        // 行追加
        getCategoryRegistDto().getEditList().addAll(
                getCreateBlankCategoryLogic().execute(getCategoryRegistDto()));

        // 自画面へ遷移
        return null;
    }

    /**
     * ブランクカテゴリ除去
     * 
     * @param categoryList 処理対象カテゴリリスト
     */
    private void removeBlankCategory(List categoryList) {
        for (Iterator it = categoryList.iterator(); it.hasNext();) {
            CategoryRegistEntity categoryRegistEntity = (CategoryRegistEntity) it.next();
            if (categoryRegistEntity.isInsertFlg()
                    && (categoryRegistEntity.getNewCateName() == null
                    || categoryRegistEntity.getNewCateName().length() <= 0)) {
                it.remove();
            }
        }
    }
    
    /**
     * ソート順設定処理
     */
    private void doSortKey() {
        // ソート
        Collections.sort(getCategoryRegistDto().getEditList(), new SortComparator());

        // ソート順採番
        CodeFormatter codeFormatter = new CodeFormatter(3);
        codeFormatter.setFormatPattern("000");
        int count = 1;
        for (Iterator ite = getCategoryRegistDto().getEditList().iterator(); ite.hasNext();) {
            CategoryRegistEntity entity = (CategoryRegistEntity) ite.next();
            if (!entity.isChkFlg()) {
                // 削除対象以外
                entity.setSortKey(codeFormatter.format(String.valueOf(count * 10), true));
                count++;
            }
        }
    }
}
