/*
 * 作成日: 2006/02/17
 *
 */
package jp.co.isid.mos.bird.config.categoryregist.dto;

import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.config.categoryregist.entity.MstCategoryInfo;

/**
 * @author xyuchida
 *
 */
public class CategoryRegistDto {

    public static final int TARGET_CATEGORY = 1;
    public static final int TARGET_SUBCATEGORY = 2;

    private static final String TARGET_NAME_CATEGORY = "カテゴリー";
    private static final String TARGET_NAME_SUBCATEGORY = "サブカテゴリー";

    /**
     * 初期処理フラグ
     */
    private boolean clearFlg;

    /**
     * 情報種別
     */
    private String infoShu;

    /**
     * 処理対象区分
     */
    private int target;

    /**
     * カテゴリID
     */
    private String cateId;

    /**
     * 情報種別リスト
     */
    private List infoShuList;

    /**
     * カテゴリリスト
     */
    private List categoryList;

    /**
     * 編集対象カテゴリリスト
     */
    private List editList;

    /**
     * 初期処理フラグを取得します。
     * @return 初期処理フラグ
     */
    public boolean isClearFlg() {
        return clearFlg;
    }

    /**
     * 初期処理フラグを設定します。
     * @param clearFlg 初期処理フラグ
     */
    public void setClearFlg(boolean clearFlg) {
        this.clearFlg = clearFlg;
    }

    /**
     * 情報種別を取得します。
     * @return 情報種別
     */
    public String getInfoShu() {
        return infoShu;
    }

    /**
     * 情報種別を設定します。
     * @param infoShu 情報種別
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }

    /**
     * 処理対象区分を取得します。
     * @return 処理対象区分
     */
    public int getTarget() {
        return target;
    }

    /**
     * 処理対象区分を設定します。
     * @param target 処理対象区分
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * カテゴリIDを取得します。
     * @return カテゴリID
     */
    public String getCateId() {
        return cateId;
    }

    /**
     * カテゴリIDを設定します。
     * @param cateId カテゴリID
     */
    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    /**
     * 情報種別リストを取得します。
     * @return 情報種別リスト
     */
    public List getInfoShuList() {
        return infoShuList;
    }

    /**
     * 情報種別リストを設定します。
     * @param infoShuList 情報種別リスト
     */
    public void setInfoShuList(List infoShuList) {
        this.infoShuList = infoShuList;
    }

    /**
     * カテゴリリストを取得します。
     * @return カテゴリリスト
     */
    public List getCategoryList() {
        return categoryList;
    }

    /**
     * カテゴリリストを設定します。
     * @param categoryList カテゴリリスト
     */
    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * 編集対象カテゴリリストを取得します。
     * @return 編集対象カテゴリリスト
     */
    public List getEditList() {
        return editList;
    }

    /**
     * 編集対象カテゴリリストを設定します。
     * @param categoryList 編集対象カテゴリリスト
     */
    public void setEditList(List editList) {
        this.editList = editList;
    }

    /**
     * 情報種別名を取得します。
     * @return 情報種別名
     */
    public String getInfoShuName() {
        String infoShuName = "";
        for (Iterator it = getInfoShuList().iterator(); it.hasNext();) {
            SelectItem selectItem = (SelectItem) it.next();
            if (getInfoShu().equals(selectItem.getValue())) {
                infoShuName = selectItem.getLabel();
                break;
            }
        }
        return infoShuName;
    }

    /**
     * 処理対象区分名を取得します。
     * @return 処理対象区分名
     */
    public String getTargetName() {
        String targetName = "";
        switch (getTarget()) {
        case TARGET_CATEGORY:
            targetName = TARGET_NAME_CATEGORY;
            break;
        case TARGET_SUBCATEGORY:
            targetName = TARGET_NAME_SUBCATEGORY;
            break;
        default:
            break;
        }
        return targetName;
    }

    /**
     * カテゴリ名を取得します。
     * @return カテゴリ名
     */
    public String getCateName() {
        String cateName = "";
        for (Iterator it = getCategoryList().iterator(); it.hasNext();) {
            MstCategoryInfo mstCategoryInfo = (MstCategoryInfo) it.next();
            if (getCateId().equals(mstCategoryInfo.getCateId())) {
                cateName = mstCategoryInfo.getOldCateName();
                break;
            }
        }
        return cateName;
    }
}
