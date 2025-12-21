/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.action.impl;

import jp.co.isid.mos.bird.config.categoryregist.action.CategoryRegistConfirmAction;
import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;
import jp.co.isid.mos.bird.config.categoryregist.logic.CreateBlankCategoryLogic;
import jp.co.isid.mos.bird.config.categoryregist.logic.UpdateCategoryLogic;
import jp.co.isid.mos.bird.config.categoryregist.logic.UpdateSubCategoryLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * @author xyuchida
 *
 */
public class CategoryRegistConfirmActionImpl implements
        CategoryRegistConfirmAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BCF004A08";
    public static final String cancel_ACTION_ID = "BCF004A09";
    public static final String regist_ACTION_ID = "BCF004A10";

    private static final String VIEWID_CONDITION = "BCF004V01";
    private static final String VIEWID_EDIT = "BCF004V02";

    /**
     * カテゴリ登録DTO
     */
    private CategoryRegistDto categoryRegistDto;

    /**
     * 情報カテゴリ更新ロジック
     */
    private UpdateCategoryLogic updateCategoryLogic;

    /**
     * 情報サブカテゴリ更新ロジック
     */
    private UpdateSubCategoryLogic updateSubCategoryLogic;

    /**
     * ブランクカテゴリ生成ロジック
     */
    private CreateBlankCategoryLogic createBlankCategoryLogic;

    /**
     * ユーザ関連情報
     */
    private BirdUserInfo birdUserInfo;

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
     * 情報カテゴリ更新ロジックを取得します。
     * @return 情報カテゴリ更新ロジック
     */
    public UpdateCategoryLogic getUpdateCategoryLogic() {
        return updateCategoryLogic;
    }

    /**
     * 情報カテゴリ更新ロジックを設定します。
     * @param updateCategoryLogic 情報カテゴリ更新ロジック
     */
    public void setUpdateCategoryLogic(UpdateCategoryLogic updateCategoryLogic) {
        this.updateCategoryLogic = updateCategoryLogic;
    }

    /**
     * 情報サブカテゴリ更新ロジックを取得します。
     * @return 情報サブカテゴリ更新ロジック
     */
    public UpdateSubCategoryLogic getUpdateSubCategoryLogic() {
        return updateSubCategoryLogic;
    }

    /**
     * 情報サブカテゴリ更新ロジックを設定します。
     * @param updateSubCategoryLogic 情報サブカテゴリ更新ロジック
     */
    public void setUpdateSubCategoryLogic(
            UpdateSubCategoryLogic updateSubCategoryLogic) {
        this.updateSubCategoryLogic = updateSubCategoryLogic;
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
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
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
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String cancel() {

        // 行追加
        getCategoryRegistDto().getEditList().addAll(
                getCreateBlankCategoryLogic().execute(getCategoryRegistDto()));

        // 編集画面へ遷移
        return VIEWID_EDIT;
    }

    /**
     * 登録
     * 
     * @return 画面遷移情報
     */
    public String regist() {

        // 対象判定
        switch (getCategoryRegistDto().getTarget()) {
        case CategoryRegistDto.TARGET_CATEGORY:
            // 情報カテゴリ更新
            getUpdateCategoryLogic().execute(getBirdUserInfo().getUserID(), getCategoryRegistDto());
            break;

        case CategoryRegistDto.TARGET_SUBCATEGORY:
            // 情報サブカテゴリ更新
            getUpdateSubCategoryLogic().execute(getBirdUserInfo().getUserID(), getCategoryRegistDto());
            break;

        default:
            throw new FtlSystemException(regist_ACTION_ID);
        }

        // 条件画面表示更新の為に初期処理フラグON
        getCategoryRegistDto().setClearFlg(true);

        // 条件画面へ遷移
        return VIEWID_CONDITION;
    }
}
