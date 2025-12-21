/*
 * 作成日: 2006/1/31
 */
package jp.co.isid.mos.bird.office.formregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.office.formregist.action.FormRegistRankAction;
import jp.co.isid.mos.bird.office.formregist.dto.FormConditionDto;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistRankDto;
import jp.co.isid.mos.bird.office.formregist.logic.GetFormInfoLogic;
import jp.co.isid.mos.bird.office.formregist.logic.GetFormSortLogic;
import jp.co.isid.mos.bird.office.formregist.logic.RegFormSortLogic;

/**
 * フォーム登録 フォームダウンロード順位設定画面アクション
 * @author xytamura
 */
public class FormRegistRankActionImpl implements CommonAction,
        FormRegistRankAction {
    public static String init_ACTION_ID = "BIF003A27";

    public static String regist_ACTION_ID = "BIF003A28";

    public static String cancel_ACTION_ID = "BIF003A29";


    /* DTO */
    private FormConditionDto formConditionDto;

    private FormRegistRankDto formRegistRankDto;

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;


    /* ロジック */
    // フォームダウンロード順序を取得
    private GetFormSortLogic getFormSortLogic;

    // フォームダウンロード順序の登録
    private RegFormSortLogic regFormSortLogic;

    // フォーム一覧情報の取得
    private GetFormInfoLogic getFormInfoLogic;
    
    private static final String VIEWID_CONDITION = "BOF001V01";

    /**
     * 初期処理
     * @return
     */
    public String initialize() {

        /* １．ロジック【フォームダウンロード順序を取得】を実行する。 */
        List listForm = getGetFormSortLogic().execute(
                getFormConditionDto().getNengetu(),
                getFormConditionDto().getSelectedCateId(),
                getFormConditionDto().getSelectedSubCateId());

        FormRegistRankDto dto = getFormRegistRankDto();
        dto.setListForm(listForm);
        setFormRegistRankDto(dto);

        return null;
    }

    /**
     * 取消
     * @return
     */
    public String cancel() {
        return VIEWID_CONDITION;
    }

    /**
     * 登録処理
     * @return String
     */
    public String regist() {
        /* １．ロジック【フォームダウンロード順序の登録】を実行する。 */
        getRegFormLogic().execute(getFormRegistRankDto().getListForm(), getBirdUserInfo().getUserID());

        //--- 2007/08/06 登録した内容を反映させるために再検索をおこなう
        // ロジック【フォーム情報の取得】
        List listForm = getGetFormInfoLogic().execute(
                getFormConditionDto().getNengetu(),
                getFormConditionDto().getCateId(),
                getFormConditionDto().getSelectedSubCateId(),
                getBirdUserInfo().getUserID(),
                getFormConditionDto().getCurrentPageNumber(),
                getBirdDateInfo().getSysDate());

        getFormConditionDto().setListForm(listForm);
        
        return VIEWID_CONDITION;
    }

    /**
     * @param conditionDto conditionDto を設定。
     */
    public void setFormConditionDto(FormConditionDto formConditionDto) {
        this.formConditionDto = formConditionDto;
    }

    /**
     * @return conditionDto を戻します。
     */
    public FormConditionDto getFormConditionDto() {
        return formConditionDto;
    }

    /**
     * BIRDユーザー情報設定処理
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * @return getBunshoSortLogic を戻します。
     */
    public GetFormSortLogic getGetFormSortLogic() {
        return getFormSortLogic;
    }

    /**
     * @param getBunshoSortLogic getBunshoSortLogic を設定。
     */
    public void setGetFormSortLogic(GetFormSortLogic getFormSortLogic) {
        this.getFormSortLogic = getFormSortLogic;
    }

    /**
     * @param registRankDto registRankDto を設定。
     */
    public void setFormRegistRankDto(FormRegistRankDto formRegistRankDto) {
        this.formRegistRankDto = formRegistRankDto;
    }

    /**
     * @return registRankDto を戻します。
     */
    public FormRegistRankDto getFormRegistRankDto() {
        return formRegistRankDto;
    }


    /**
     * @return regBunshoSortLogic を戻します。
     */
    public RegFormSortLogic getRegFormLogic() {
        return regFormSortLogic;
    }

    /**
     * @param regBunshoSortLogic regBunshoSortLogic を設定。
     */
    public void setRegFormLogic(RegFormSortLogic regFormSortLogic) {
        this.regFormSortLogic = regFormSortLogic;
    }

    public GetFormInfoLogic getGetFormInfoLogic() {
        return getFormInfoLogic;
    }

    public void setGetFormInfoLogic(GetFormInfoLogic getFormInfoLogic) {
        this.getFormInfoLogic = getFormInfoLogic;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

}