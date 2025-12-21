/*
 * 作成日: 2006/02/21
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;
import jp.co.isid.mos.bird.config.categoryregist.entity.CategoryRegistEntity;
import jp.co.isid.mos.bird.config.categoryregist.logic.CheckCategoryLogic;
import jp.co.isid.mos.bird.config.categoryregist.logic.SearchSubCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.Verifier;

/**
 * @author xyuchida
 *
 */
public class CheckCategoryLogicImpl implements CheckCategoryLogic {

    private static final int MAXLENGTH_CATEGORYNAME = 40;
    private static final int MAXLENGTH_SORTKEY = 3;

    /**
     * 情報サブカテゴリ検索ロジック
     */
    private SearchSubCategoryLogic searchSubCategoryLogic;

    /**
     * 日本語文字列用バリデータ
     */
    private Verifier categoryRegistVerifier;

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
     * 日本語文字列用バリデータを取得します。
     * @return 日本語文字列用バリデータ
     */
    public Verifier getCategoryRegistVerifier() {
        return categoryRegistVerifier;
    }

    /**
     * 日本語文字列用バリデータを設定します。
     * @param categoryRegistVerifier 日本語文字列用バリデータ
     */
    public void setCategoryRegistVerifier(Verifier categoryRegistVerifier) {
        this.categoryRegistVerifier = categoryRegistVerifier;
    }

    /**
     * 登録内容チェック
     * 
     * @param categoryRegistDto カテゴリ登録DTO
     */
    public void execute(CategoryRegistDto categoryRegistDto) {

        int loopCnt = 0;
        CodeVerifier codeVerifier = new CodeVerifier(3);
        
        // 編集リストをチェック
        for (Iterator it = categoryRegistDto.getEditList().iterator(); it.hasNext();) {

            CategoryRegistEntity categoryRegistEntity = (CategoryRegistEntity) it.next();

            // 削除チェック状態判定
            if (!categoryRegistEntity.isChkFlg()) {

                String sortKey = categoryRegistEntity.getSortKey();
                sortKey = (sortKey == null) ? "" : sortKey.replaceAll("^[\\s　]+|[\\s　]+$", "");
                
                // スペース除去
                String cateName = categoryRegistEntity.getNewCateName();
                cateName = (cateName == null) ? "" : cateName.replaceAll("^[\\s　]+|[\\s　]+$", "");
                // 入力有無判定
                if (cateName.length() > 0) {

                    // 文字数チェック
                    if (cateName.getBytes().length > MAXLENGTH_CATEGORYNAME) {
                        throw new InvalidInputException(categoryRegistDto.getTargetName(), "newCateName", loopCnt);
                    }

                    // 不正文字チェック
                    if (!getCategoryRegistVerifier().validate(cateName)) {
                        throw new InvalidInputException(categoryRegistDto.getTargetName(), "newCateName", loopCnt);
                    }

                } else {

                    // （新規レコード かつ ソート順が未入力）以外、未入力はエラー
                    if (!(categoryRegistEntity.isInsertFlg() && sortKey.length() == 0)) {
                        throw new NoInputException(categoryRegistDto.getTargetName(), "newCateName", loopCnt);
                    }
                }
                
                // ソートキー
                if (sortKey.length() > 0) {
                    // 文字数チェック
                    if (sortKey.getBytes().length > MAXLENGTH_SORTKEY) {
                        throw new InvalidInputException("ソート順", "sortKey", loopCnt);
                    }
                    // 不正文字チェック
                    if (!codeVerifier.validate(sortKey)) {
                        throw new InvalidInputException("ソート順", "sortKey", loopCnt);
                    }
                }
                else {
                    // （新規レコード かつ カテゴリ名称が未入力）以外、未入力はエラー
                    if (!(categoryRegistEntity.isInsertFlg() && cateName.length() == 0)) {
                        throw new NoInputException("ソート順", "sortKey", loopCnt);
                    }
                }

            // 削除チェックON かつ 対象がカテゴリの場合のみチェックを行う
            } else if (categoryRegistDto.getTarget() == CategoryRegistDto.TARGET_CATEGORY) {

                // 情報サブカテゴリ検索
                List subCategoryList = getSearchSubCategoryLogic().execute(
                        categoryRegistEntity.getInfoShu(),
                        categoryRegistEntity.getCateId());

                // サブカテゴリが存在する場合エラー
                if (subCategoryList != null && subCategoryList.size() > 0) {

                    throw new CannotExecuteWithReasonException(
                            "カテゴリー’" + categoryRegistEntity.getOldCateName() + "’はサブカテゴリーが登録されている", "削除");
                }
            }
            loopCnt++;
        }

        for (int i = 0; i < categoryRegistDto.getEditList().size(); i++) {
            CategoryRegistEntity entityI = (CategoryRegistEntity) categoryRegistDto.getEditList().get(i);
            if (entityI.isChkFlg()) {
                continue;
            }
            for (int j = i+1; j < categoryRegistDto.getEditList().size(); j++) {
                CategoryRegistEntity entityJ = (CategoryRegistEntity) categoryRegistDto.getEditList().get(j);
                if (entityJ.isChkFlg()) {
                    continue;
                }
                if (isNull(entityI.getSortKey()) || isNull(entityJ.getSortKey())) {
                    continue;
                }
                if (entityI.getNewCateName().equals(entityJ.getNewCateName())) {
                    throw new DuplicateDataException(categoryRegistDto.getTargetName(), "newCateName", i);
                }
                if (entityI.getSortKey().equals(entityJ.getSortKey())) {
                    throw new DuplicateDataException("ソート順", "sortKey", i);
                }
            }
        }
        
        //登録可能件数チェック
        int cntRegit = 0;
        for (int i = 0; i < categoryRegistDto.getEditList().size(); i++) {
            CategoryRegistEntity entity = (CategoryRegistEntity) categoryRegistDto.getEditList().get(i);
            if (!entity.isChkFlg()
                    && !(entity.getNewCateName() == null || entity.getNewCateName().length() <= 0)) {
                cntRegit++;
            }
        }
        if (cntRegit >= 100) {
            throw new CannotExecuteException("登録可能数を超えている為、登録");
        }
    }
    
    private boolean isNull(String value) {
        return value == null || "".equals(value.trim()) ? true : false;
    }
}