/*
 * 作成日: 2006/02/22
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.config.categoryregist.dto.CategoryRegistDto;
import jp.co.isid.mos.bird.config.categoryregist.entity.CategoryRegistEntity;
import jp.co.isid.mos.bird.config.categoryregist.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.config.categoryregist.entity.MstSubCategoryInfo;
import jp.co.isid.mos.bird.config.categoryregist.logic.CreateBlankCategoryLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * @author xyuchida
 *
 */
public class CreateBlankCategoryLogicImpl implements CreateBlankCategoryLogic {

    /**
     * 生成レコード数
     */
    private int lines;

    /**
     * 生成レコード数を取得します。
     * @return 生成レコード数
     */
    public int getLines() {
        return lines;
    }

    /**
     * 生成レコード数を設定します。
     * @param lines 生成レコード数
     */
    public void setLines(int lines) {
        this.lines = lines;
    }

    /**
     * ブランクカテゴリ生成
     * 
     * @param categoryRegistDto カテゴリ登録DTO
     * @return ブランクカテゴリリスト
     */
    public List execute(CategoryRegistDto categoryRegistDto) {

        List categoryList = new ArrayList();

        // 指定数ブランクカテゴリ追加
        for (int i = 0; i < getLines(); i++) {

            CategoryRegistEntity categoryRegistEntity = null;

            // 対象により生成するインスタンスを切替
            switch (categoryRegistDto.getTarget()) {
            case CategoryRegistDto.TARGET_CATEGORY:
                // カテゴリ
                categoryRegistEntity = new MstCategoryInfo();
                break;

            case CategoryRegistDto.TARGET_SUBCATEGORY:
                // サブカテゴリ
                categoryRegistEntity = new MstSubCategoryInfo();
                break;

            default:
                throw new FtlSystemException("カテゴリ登録");
            }

            // 新規フラグON
            categoryRegistEntity.setInsertFlg(true);
            categoryList.add(categoryRegistEntity);
        }

        return categoryList;
    }
}
