/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.action.impl;

import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.config.categoryregist.action.CategoryRegistConditionAction;
import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;
import jp.co.isid.mos.bird.config.categoryregist.logic.CreateBlankCategoryLogic;
import jp.co.isid.mos.bird.config.categoryregist.logic.SearchCategoryLogic;
import jp.co.isid.mos.bird.config.categoryregist.logic.SearchSubCategoryLogic;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * @author xyuchida
 *
 */
public class CategoryRegistConditionActionImpl implements
        CategoryRegistConditionAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BCF004A01";
    public static final String search_ACTION_ID = "BCF004A02";
    public static final String selectType_ACTION_ID = "BCF004A03";

    private static final String VIEWID_EDIT = "BCF004V02";

    /**
     * カテゴリ登録DTO
     */
    private CategoryRegistDto categoryRegistDto;

    /**
     * メニューDTO
     */
    private PullDownMenuDto pullDownMenuDto;

    /**
     * 情報カテゴリ検索ロジック
     */
    private SearchCategoryLogic searchCategoryLogic;

    /**
     * 情報サブカテゴリ検索ロジック
     */
    private SearchSubCategoryLogic searchSubCategoryLogic;

    /**
     * ブランクカテゴリ生成ロジック
     */
    private CreateBlankCategoryLogic createBlankCategoryLogic;

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
     * メニューDTOを取得します。
     * @return メニューDTO
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * メニューDTOを設定します。
     * @param pullDownMenuDto メニューDTO
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * 情報カテゴリ検索ロジックを取得します。
     * @return 情報カテゴリ検索ロジック
     */
    public SearchCategoryLogic getSearchCategoryLogic() {
        return searchCategoryLogic;
    }

    /**
     * 情報カテゴリ検索ロジックを設定します。
     * @param searchCategoryLogic 情報カテゴリ検索ロジック
     */
    public void setSearchCategoryLogic(SearchCategoryLogic searchCategoryLogic) {
        this.searchCategoryLogic = searchCategoryLogic;
    }

    /**
     * 情報サブカテゴリ検索ロジックを取得します。
     * @return 情報サブカテゴリ検索ロジック
     */
    public SearchSubCategoryLogic getSearchSubCategoryLogic() {
        return searchSubCategoryLogic;
    }

    /**
     * 情報サブカテゴリ検索ロジックを設定します。
     * @param searchSubCategoryLogic 情報サブカテゴリ検索ロジック
     */
    public void setSearchSubCategoryLogic(
            SearchSubCategoryLogic searchSubCategoryLogic) {
        this.searchSubCategoryLogic = searchSubCategoryLogic;
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

        // 初期処理判定
        if (getPullDownMenuDto().isClearFlg()
                || getCategoryRegistDto().isClearFlg()) {

            // 条件項目初期値設定
            getCategoryRegistDto().setTarget(CategoryRegistDto.TARGET_CATEGORY);
            getCategoryRegistDto().setInfoShu(
                    (String) ((SelectItem) getCategoryRegistDto().getInfoShuList().get(0)).getValue());
            getCategoryRegistDto().setCateId(null);

            // 情報カテゴリ検索
            getCategoryRegistDto().setCategoryList(
                    getSearchCategoryLogic().execute(getCategoryRegistDto().getInfoShu()));

            getPullDownMenuDto().setClearFlg(false);
            getCategoryRegistDto().setClearFlg(false);
        }

        // 自画面へ遷移
        return null;
    }

    /**
     * 検索
     * 
     * @return 画面遷移情報
     */
    public String search() {

        List editList = null;
        String infoShu = getCategoryRegistDto().getInfoShu();
        // 対象判定
        switch (getCategoryRegistDto().getTarget()) {
        case CategoryRegistDto.TARGET_CATEGORY:

            // 情報カテゴリ検索
            editList = getSearchCategoryLogic().execute(infoShu);

            break;

        case CategoryRegistDto.TARGET_SUBCATEGORY:

            // 情報種別チェック
            if (InfoShu.CONTACT.equals(infoShu)
                || InfoShu.TUTAU.equals(infoShu)
                || InfoShu.BBS.equals(infoShu)) 
            {
                throw new CannotExecuteException(
                		InfoShu.getName(infoShu)+"はサブカテゴリーを登録", "");
            }
            
            // 情報サブカテゴリ検索
            editList = getSearchSubCategoryLogic().execute(
            		infoShu, getCategoryRegistDto().getCateId());

            break;

        default:
            throw new FtlSystemException(search_ACTION_ID);
        }

        // 行追加
        editList.addAll(getCreateBlankCategoryLogic().execute(getCategoryRegistDto()));

        // 編集対象リスト設定
        getCategoryRegistDto().setEditList(editList);

        // 編集画面へ遷移
        return VIEWID_EDIT;
    }

    /**
     * タイプ選択
     * 
     * @return 画面遷移情報
     */
    public String selectType() {

        // 情報カテゴリ検索
        getCategoryRegistDto().setCategoryList(
                getSearchCategoryLogic().execute(getCategoryRegistDto().getInfoShu()));
        getCategoryRegistDto().setCateId(null);

        // 自画面へ遷移
        return null;
    }
}
