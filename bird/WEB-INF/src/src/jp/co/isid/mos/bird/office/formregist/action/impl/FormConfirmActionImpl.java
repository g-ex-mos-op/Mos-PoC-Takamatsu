/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.office.formregist.action.impl;

import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.entity.MstSubCategoryInfo;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.FileDeleteAction;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.action.impl.FileDeleteActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.office.formregist.action.FormConfirmAction;
import jp.co.isid.mos.bird.office.formregist.dto.FormConditionDto;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;
import jp.co.isid.mos.bird.office.formregist.logic.RegFormLogic;
import jp.co.isid.mos.bird.office.formregist.logic.impl.UploadFormLogicImpl;

/**
 * フォーム登録 確認画面アクション
 * @author xytamura
 */
public class FormConfirmActionImpl implements CommonAction, FormConfirmAction {
    /* アクションID */
    public static final String init_ACTION_ID = "BOF001A22";

    public static final String regist_ACTION_ID = "BOF001A23";

    public static final String changeTab_ACTION_ID = "BOF001A24";

    public static final String cancel_ACTION_ID = "BOF001A25";

    public static final String upload_ACTION_ID = "BOF001A26";

    public static final String delete_ACTION_ID = "BOF001A28";
    /* ビューID */
    private static final String VIEWID_CONDITION = "BOF001V01";

    private static final String VIEWID_EDIT = "BOF001V02";

    private static final String VIEWID_CONFIRM = "BOF001V03";


    private static final String VIEWID_PUBLICTARGET_SEARCH = "BCO002V01";

    /* DTO */
    private FormConditionDto formConditionDto;

    private FormRegistFormDto formRegistFormDto;

    //    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;

    private UIFormInfo uiFormInfo;

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;

    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    /* ロジック */
    // 文書情報の登録ロジック
    private RegFormLogic regFormLogic;

    // アップロードロジック
    private UploadFormLogicImpl uploadFormLogic;


//    /* タブ切替用Index */
//    private int selectedTabIndex = 1;
//
//    /* 定数 */
//    // タブIndex：文書内容
//    private static final int TAB_INDEX_BUNSHO = 1;
//
//    // タブIndex：文書内容
//    private static final int TAB_INDEX_FILE = 2;

    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        if (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_DELETE
                && getFormConditionDto().isFlgCondToReg()) {
            int index = getFormConditionDto().getSelectedListIndex();
            UIFormInfo entity = (UIFormInfo) getFormConditionDto()
                    .getListForm().get(index);
            getFormRegistFormDto().setEditEntity(entity);
            setUiFormInfo(entity);
            getFormConditionDto().setFlgCondToReg(false);
        }
        // カテゴリ、サブカテゴリ名セット
        setCategoryNameLimitKbnName(getFormRegistFormDto().getEditEntity());

        return null;
    }

//    /**
//     * タブ変更処理
//     * @return
//     */
//    public String changeTab() {
//        switch (getSelectedTabIndex()) {
//        case TAB_INDEX_BUNSHO:
//            getFormRegistFormDto().setTabIndex(1);
//            getFormRegistFormDto().setEditEntity(getUiFormInfo());
//            break;
//        case TAB_INDEX_FILE:
//            getFormRegistFormDto().setTabIndex(2);
//            getFormRegistFormDto().setEditEntity(getUiFormInfo());
//        default:
//        }
//        return null;
//    }

    /**
     * 取消
     * @return
     */
    public String cancel() {
        String ret = null;
        if (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_INSERT) {
            ret = VIEWID_EDIT;
        } else if (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_UPDATE) {
            ret = VIEWID_EDIT;
        } else if (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_DELETE) {
            ret = VIEWID_CONDITION;
        }

        return ret;
    }

    /**
     * 登録処理
     * @return String
     */
    public String regist() {
        // 編集モード設定
        getFormRegistFormDto().setRegMode(getFormConditionDto().getRegMode());
        // 新規時のキー設定
        if (getFormRegistFormDto().getRegMode() == FormConditionDto.REG_MODE_INSERT) {
            // 登録日
            getUiFormInfo().setRegDate(getBirdDateInfo().getSysDate());
        }
        // ロジック【フォームの登録】実行
        getRegFormLogic().execute(getFormRegistFormDto(),
                getPublicTargetSearchDto(), getBirdUserInfo().getMstUser());
        // デリート処理
        if (getFormRegistFormDto().getRegMode() == FormConditionDto.REG_MODE_UPDATE) {
        	deleteServerFiles();
        }

        // アップロード
        upload();

		//「削除」モードファイル削除処理
        if (FormConditionDto.REG_MODE_DELETE == getFormRegistFormDto().getRegMode()) {
        	delete();
    	}
        // セッションクリア処理
        clearSession();
        return VIEWID_CONDITION;
    }

    /**
     * アップロード
     * @return
     */
    public String upload() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        FileUploadAction action = (FileUploadAction) container
                .getComponent("formRegistCommonUploadAction");

        action.setUploadDto(getFormRegistFormDto());
        action.setUploadLogic(getUploadFormLogic());
        for (int i = 1; i <= 4; i++) {
            getFormRegistFormDto().setUploadIndex(i);
            switch (i) {
            case 1:
                getFormRegistFormDto().setTempFileName(
                        getFormRegistFormDto().getUploadTempFileNameMain());
                getFormRegistFormDto().setUploadFileName(
                        getFormRegistFormDto().getUploadFileNameMain());
                getFormRegistFormDto().setUploadIndex(1);
                break;
            case 2:
                getFormRegistFormDto().setTempFileName(
                        getFormRegistFormDto().getUploadTempFileNameTenpu1());
                getFormRegistFormDto().setUploadFileName(
                        getFormRegistFormDto().getUploadFileNameTenpu1());
                getFormRegistFormDto().setUploadIndex(2);
                break;
            case 3:
                getFormRegistFormDto().setTempFileName(
                        getFormRegistFormDto().getUploadTempFileNameTenpu2());
                getFormRegistFormDto().setUploadFileName(
                        getFormRegistFormDto().getUploadFileNameTenpu2());
                getFormRegistFormDto().setUploadIndex(3);
                break;
            case 4:
                getFormRegistFormDto().setTempFileName(
                        getFormRegistFormDto().getUploadTempFileNameTenpu3());
                getFormRegistFormDto().setUploadFileName(
                        getFormRegistFormDto().getUploadFileNameTenpu3());
                getFormRegistFormDto().setUploadIndex(4);
                break;
            }

            if (getFormRegistFormDto().getTempFileName() != null &&
                    getFormRegistFormDto().getTempFileName().trim().length() != 0) {
                action.upload();
            }
        }
        return null;
    }

    /**
     * 削除処理
     * @return
     */
    public String delete() {
    	getFormRegistFormDto().setUploadTempFileNameMain("削除対象");
    	getFormRegistFormDto().setUploadTempFileNameTenpu1("削除対象");
    	getFormRegistFormDto().setUploadTempFileNameTenpu2("削除対象");
    	getFormRegistFormDto().setUploadTempFileNameTenpu3("削除対象");
    	deleteServerFiles();
        return null;
    }
    /**
     * 登録済みファイル削除処理
     *
     * @return
     */
    private void deleteServerFiles() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        FileDeleteAction action = (FileDeleteAction) container.getComponent(FileDeleteActionImpl.class);

        action.setUploadDto(getFormRegistFormDto());
        action.setDeleteUploadLogic(getUploadFormLogic());

        for (int i = 1; i <= 4; i++) {
            getFormRegistFormDto().setUploadIndex(i);
            switch (i) {
                case 1:
                    getFormRegistFormDto().setTempFileName(getFormRegistFormDto().getUploadTempFileNameMain());
                    getFormRegistFormDto().setUploadIndex(1);
                    break;
                case 2:
                    getFormRegistFormDto().setTempFileName(getFormRegistFormDto().getUploadTempFileNameTenpu1());
                    getFormRegistFormDto().setUploadIndex(2);
                    break;
                case 3:
                    getFormRegistFormDto().setTempFileName(getFormRegistFormDto().getUploadTempFileNameTenpu2());
                    getFormRegistFormDto().setUploadIndex(3);
                    break;
                case 4:
                    getFormRegistFormDto().setTempFileName(getFormRegistFormDto().getUploadTempFileNameTenpu3());
                    getFormRegistFormDto().setUploadIndex(4);
                    break;
            }

            if (getFormRegistFormDto().getTempFileName() != null &&
                    getFormRegistFormDto().getTempFileName().trim().length() != 0) {
                action.delete(getFormRegistFormDto().getUploadIndex());
            }
        }
    }


    /**
     * 共通フォーム 公開対象を開く
     */
    public String callFormKokai() {

        // 公開対象選択をパラメータを設定(参照モード)
        publicTargetSearchDto.setReferenceFlg(true);
        publicTargetSearchDto.setInitFlag(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_CONFIRM);

        // 公開対象選択へ遷移
        return VIEWID_PUBLICTARGET_SEARCH;
    }

    /**
     * @param regBunshoLogic regBunshoLogic を設定。
     */
    public void setRegFormLogic(RegFormLogic regFormLogic) {
        this.regFormLogic = regFormLogic;
    }

    /**
     * @return regBunshoLogic を戻します。
     */
    public RegFormLogic getRegFormLogic() {
        return regFormLogic;
    }

    /**
     * @param registFormDto registFormDto を設定。
     */
    public void setFormRegistFormDto(FormRegistFormDto formRegistFormDto) {
        this.formRegistFormDto = formRegistFormDto;
    }

    /**
     * @return registFormDto を戻します。
     */
    public FormRegistFormDto getFormRegistFormDto() {
        return formRegistFormDto;
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

//    /**
//     * @return selectedTabIndex を戻します。
//     */
//    public int getSelectedTabIndex() {
//        return selectedTabIndex;
//    }
//
//    /**
//     * @param selectedTabIndex selectedTabIndex を設定。
//     */
//    public void setSelectedTabIndex(int selectedTabIndex) {
//        this.selectedTabIndex = selectedTabIndex;
//    }

    /**
     * @param uiBunshoInfo uiBunshoInfo を設定。
     */
    public void setUiFormInfo(UIFormInfo uiFormInfo) {
        this.uiFormInfo = uiFormInfo;
    }

    /**
     * @return uiBunshoInfo を戻します。
     */
    public UIFormInfo getUiFormInfo() {
        return uiFormInfo;
    }

    /**
     * @return uploadLogic を戻します。
     */
    public UploadFormLogicImpl getUploadFormLogic() {
        return uploadFormLogic;
    }

    /**
     * @param uploadLogic uploadLogic を設定。
     */
    public void setUploadFormLogic(UploadFormLogicImpl uploadFormLogic) {
        this.uploadFormLogic = uploadFormLogic;
    }

    //    /**
    //     * @return publicTargetDto を戻します。
    //     */
    //    public PublicTargetDto getPublicTargetDto() {
    //        return publicTargetDto;
    //    }
    //
    //    /**
    //     * @param publicTargetDto publicTargetDto を設定。
    //     */
    //    public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
    //        this.publicTargetDto = publicTargetDto;
    //    }

    /**
     * @return publicTargetSearchDto を戻します。
     */
    public PublicTargetSearchDto getPublicTargetSearchDto() {
        return publicTargetSearchDto;
    }

    /**
     * @param publicTargetSearchDto publicTargetSearchDto を設定。
     */
    public void setPublicTargetSearchDto(
            PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
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
     * セッションクリア処理
     */
    private void clearSession() {
        getFormRegistFormDto().clearData();
        getFormConditionDto().clearData();
        setFormRegistFormDto(null);
        setFormConditionDto(null);
        setUiFormInfo(null);
        getPublicTargetSearchDto().clearData();

    }

    /**
     * BIRD日付情報設定処理
     * @param birdDateInfo birdDateInfo を設定。
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * カテゴリ、サブカテゴリ名処理
     * @param entity
     */
    private void setCategoryNameLimitKbnName(UIFormInfo entity) {
        List listCate = null;
        List listSubCate = null;

//        List listLimit = getFormRegistFormDto().getListLimitKbn();

        //編集画面から遷移してきた（新規、編集の場合）
        if (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_INSERT
                || getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_UPDATE) {
            listCate = getFormRegistFormDto().getListCategory();
            listSubCate = getFormRegistFormDto().getListSubCategory();


        //条件画面から遷移してきた（削除の場合）
        }else if(getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_DELETE){
            listCate = getFormConditionDto().getListCategory();
            listSubCate = getFormConditionDto().getListSubCategory();
        }
        for (int i = 0; i < listCate.size(); i++) {
            MstCategoryInfo mstCate = (MstCategoryInfo) listCate.get(i);
            if (entity.getCateId().equals(mstCate.getCateId())) {
                entity.setCateName(mstCate.getCateName());
            }
        }
        for (int i = 0; i < listSubCate.size(); i++) {
            MstSubCategoryInfo mstSubCate = (MstSubCategoryInfo) listSubCate
                    .get(i);
            if (entity.getSubCateId().equals(mstSubCate.getSubCateId())) {
                entity.setSubCateName(mstSubCate.getSubCateName());
            }
        }
//        for (int i = 0; i < listLimit.size(); i++) {
//            CodInfoLimitInfo limit = (CodInfoLimitInfo) listLimit
//                    .get(i);
//            if (entity.getLimitKbn().equals(limit.getLimitKbn())) {
//                entity.setLimitKbnName(limit.getLimitKbnName());
//            }
//        }



    }
}