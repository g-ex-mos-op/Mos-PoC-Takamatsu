/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.inforegist.documentregist.action.impl;

import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.entity.MstSubCategoryInfo;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.FileDeleteAction;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.action.impl.FileDeleteActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.inforegist.documentregist.action.DocumentRegistConfirmAction;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistConditionDto;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistFormDto;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.RegBunshoLogic;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.impl.UploadLogicImpl;

/**
 * 文書登録 確認画面アクション
 * @author xnkusama
 */
public class DocumentRegistConfirmActionImpl implements CommonAction, DocumentRegistConfirmAction {
    /* アクションID */
    public static final String init_ACTION_ID       = "BIF003A15";
    public static final String changeTab_ACTION_ID  = "BIF003A16";
    public static final String cancel_ACTION_ID     = "BIF003A17";
    public static final String regist_ACTION_ID     = "BIF003A18";
    public static final String upload_ACTION_ID     = "BIF003A19";
    public static final String delete_ACTION_ID = "BIF003A30";
    /* ビューID */
    private static final String VIEWID_CONDITION= "BIF003V01";
    private static final String VIEWID_EDIT     = "BIF003V02";
    private static final String VIEWID_CONFIRM  = "BIF003V03";
    private static final String VIEWID_PUBLICTARGET_SEARCH = "BCO002V01";

    /* DTO */
    private DocumentRegistConditionDto documentRegistConditionDto;
    private DocumentRegistRegistFormDto documentRegistRegistFormDto;
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;
    private UIBunshoInfo uiBunshoInfo;
    private PullDownMenuDto pullDownMenuDto;

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;
    /* ロジック */
    // 文書情報の登録ロジック
    private RegBunshoLogic regBunshoLogic;
    // アップロードロジック
    private UploadLogicImpl uploadLogic;
//    /* タブ切替用Index */
//    private int selectedTabIndex = 1;
//
//    /* 定数 */
//    // タブIndex：文書内容
//    private static final int TAB_INDEX_BUNSHO = 1;
//    // タブIndex：文書内容
//    private static final int TAB_INDEX_FILE = 2;

    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        if (getDocumentRegistConditionDto().getRegMode() == DocumentRegistConditionDto.REG_MODE_DELETE
                && getDocumentRegistConditionDto().isFlgCondToReg())
        {
            int index = getDocumentRegistConditionDto().getSelectedListIndex();
            UIBunshoInfo entity = (UIBunshoInfo) getDocumentRegistConditionDto().getListBunsho().get(index);
            getDocumentRegistRegistFormDto().setEditEntity(entity);
            setUiBunshoInfo(entity);

            getDocumentRegistConditionDto().setFlgCondToReg(false);
        }
        // 登録モードが削除以外のとき
        if ( getDocumentRegistConditionDto().getRegMode() != DocumentRegistConditionDto.REG_MODE_DELETE  ) {
	        // カテゴリ、サブカテゴリ名セット
	        setCategoryName(getDocumentRegistRegistFormDto().getEditEntity());

        }
        return null;
    }

//    /**
//     * タブ変更処理
//     * @return
//     */
//    public String changeTab() {
//        switch (getSelectedTabIndex()) {
//        case TAB_INDEX_BUNSHO:
//            getDocumentRegistRegistFormDto().setTabIndex(1);
//            getDocumentRegistRegistFormDto().setEditEntity(getUiBunshoInfo());
//            break;
//        case TAB_INDEX_FILE:
//            getDocumentRegistRegistFormDto().setTabIndex(2);
//            getDocumentRegistRegistFormDto().setEditEntity(getUiBunshoInfo());
//        default:
//    }
//        return null;
//    }

    /**
     * 取消
     * @return
     */
    public String cancel() {
        String ret = null;
        if (getDocumentRegistConditionDto().getRegMode() == DocumentRegistConditionDto.REG_MODE_INSERT) {
            ret = VIEWID_EDIT;
        }
        else if (getDocumentRegistConditionDto().getRegMode() == DocumentRegistConditionDto.REG_MODE_UPDATE) {
            ret = VIEWID_EDIT;
        }
        else if (getDocumentRegistConditionDto().getRegMode() == DocumentRegistConditionDto.REG_MODE_DELETE) {
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
        getDocumentRegistRegistFormDto().setRegMode(getDocumentRegistConditionDto().getRegMode());
        // 新規時のキー設定
        if (getDocumentRegistRegistFormDto().getRegMode() == DocumentRegistConditionDto.REG_MODE_INSERT) {
            // 登録日
            getUiBunshoInfo().setRegDate(getBirdDateInfo().getSysDate());
        }
        // ロジック【文書の登録】実行
        getRegBunshoLogic().registBunsho(getDocumentRegistRegistFormDto(), getPublicTargetDto(), getBirdUserInfo().getMstUser());

        // 編集の場合は削除処理実行
        if (getDocumentRegistRegistFormDto().getRegMode() == DocumentRegistConditionDto.REG_MODE_UPDATE) {
        	deleteServerFiles();
        }
        // アップロード
        upload();

		//「削除」モードファイル削除処理
        if (DocumentRegistConditionDto.REG_MODE_DELETE == getDocumentRegistRegistFormDto().getRegMode()) {
        	delete();
    	}
        // セッションクリア処理
        clearSession();

        getPullDownMenuDto().setClearFlg(true);

        return VIEWID_CONDITION;
    }

    /**
     * アップロード
     * @return
     */
    public String upload() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        FileUploadAction action = (FileUploadAction) container.getComponent("DocumentRegistCommonUploadAction");

        action.setUploadDto(getDocumentRegistRegistFormDto());
        action.setUploadLogic(getUploadLogic());

        for (int i = 1; i <= 4; i++) {
            getDocumentRegistRegistFormDto().setUploadIndex(i);
            switch (i) {
                case 1:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameMain());
                    getDocumentRegistRegistFormDto().setUploadFileName(getDocumentRegistRegistFormDto().getUploadFileNameMain());
                    getDocumentRegistRegistFormDto().setUploadIndex(1);
                    break;
                case 2:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameTenpu1());
                    getDocumentRegistRegistFormDto().setUploadFileName(getDocumentRegistRegistFormDto().getUploadFileNameTenpu1());
                    getDocumentRegistRegistFormDto().setUploadIndex(2);
                    break;
                case 3:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameTenpu2());
                    getDocumentRegistRegistFormDto().setUploadFileName(getDocumentRegistRegistFormDto().getUploadFileNameTenpu2());
                    getDocumentRegistRegistFormDto().setUploadIndex(3);
                    break;
                case 4:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameTenpu3());
                    getDocumentRegistRegistFormDto().setUploadFileName(getDocumentRegistRegistFormDto().getUploadFileNameTenpu3());
                    getDocumentRegistRegistFormDto().setUploadIndex(4);
                    break;
            }

            if (getDocumentRegistRegistFormDto().getTempFileName() != null &&
                    getDocumentRegistRegistFormDto().getTempFileName().trim().length() != 0) {
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
    	getDocumentRegistRegistFormDto().setUploadTempFileNameMain("削除対象");
    	getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu1("削除対象");
    	getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu2("削除対象");
    	getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu3("削除対象");
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

        action.setUploadDto(getDocumentRegistRegistFormDto());
        action.setDeleteUploadLogic(getUploadLogic());

        for (int i = 1; i <= 4; i++) {
            getDocumentRegistRegistFormDto().setUploadIndex(i);
            switch (i) {
                case 1:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameMain());
                    getDocumentRegistRegistFormDto().setUploadIndex(1);
                    break;
                case 2:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameTenpu1());
                    getDocumentRegistRegistFormDto().setUploadIndex(2);
                    break;
                case 3:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameTenpu2());
                    getDocumentRegistRegistFormDto().setUploadIndex(3);
                    break;
                case 4:
                    getDocumentRegistRegistFormDto().setTempFileName(getDocumentRegistRegistFormDto().getUploadTempFileNameTenpu3());
                    getDocumentRegistRegistFormDto().setUploadIndex(4);
                    break;
            }

            if (getDocumentRegistRegistFormDto().getTempFileName() != null &&
                    getDocumentRegistRegistFormDto().getTempFileName().trim().length() != 0) {
                action.delete(getDocumentRegistRegistFormDto().getUploadIndex());
            }
        }
    }



    /**
     * 共通フォーム 公開対象を開く
     */
    public String callForm() {

        // 公開対象選択をパラメータを設定(参照モード)
    	publicTargetSearchDto = new PublicTargetSearchDto();
        publicTargetSearchDto.setReferenceFlg(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_CONFIRM);
        publicTargetSearchDto.setInfoShu(publicTargetDto.getInfoShu());
        publicTargetSearchDto.setRegDate(publicTargetDto.getRegDate());
        publicTargetSearchDto.setSeq(publicTargetDto.getSeq());
        publicTargetSearchDto.setListTrnControlCompany(publicTargetDto.getListTrnControlCompany());
        publicTargetSearchDto.setListTrnControlShozoku(publicTargetDto.getListTrnControlShozoku());
        publicTargetSearchDto.setListTrnControlGyotai(publicTargetDto.getListTrnControlGyotai());
        publicTargetSearchDto.setListTrnControlGyotaiKobetu(publicTargetDto.getListTrnControlGyotaiKobetu());
        publicTargetSearchDto.setListTrnControlGyotaiTenpo(publicTargetDto.getListTrnControlGyotaiTenpo());

        // 公開対象選択へ遷移
        return VIEWID_PUBLICTARGET_SEARCH;
    }

    /**
     * @param regBunshoLogic regBunshoLogic を設定。
     */
    public void setRegBunshoLogic(RegBunshoLogic regBunshoLogic) {
        this.regBunshoLogic = regBunshoLogic;
    }

    /**
     * @return regBunshoLogic を戻します。
     */
    public RegBunshoLogic getRegBunshoLogic() {
        return regBunshoLogic;
    }

    /**
     * @param registFormDto registFormDto を設定。
     */
    public void setDocumentRegistRegistFormDto(DocumentRegistRegistFormDto registFormDto) {
        this.documentRegistRegistFormDto = registFormDto;
    }

    /**
     * @return registFormDto を戻します。
     */
    public DocumentRegistRegistFormDto getDocumentRegistRegistFormDto() {
        return documentRegistRegistFormDto;
    }

    /**
     * @param conditionDto conditionDto を設定。
     */
    public void setDocumentRegistConditionDto(DocumentRegistConditionDto dto) {
        this.documentRegistConditionDto = dto;
    }

    /**
     * @return conditionDto を戻します。
     */
    public DocumentRegistConditionDto getDocumentRegistConditionDto() {
        return documentRegistConditionDto;
    }
//    /**
//     * @return selectedTabIndex を戻します。
//     */
//    public int getSelectedTabIndex() {
//        return selectedTabIndex;
//    }
//    /**
//     * @param selectedTabIndex selectedTabIndex を設定。
//     */
//    public void setSelectedTabIndex(int selectedTabIndex) {
//        this.selectedTabIndex = selectedTabIndex;
//    }
    /**
     * @param uiBunshoInfo uiBunshoInfo を設定。
     */
    public void setUiBunshoInfo(UIBunshoInfo uiBunshoInfo) {
        this.uiBunshoInfo = uiBunshoInfo;
    }

    /**
     * @return uiBunshoInfo を戻します。
     */
    public UIBunshoInfo getUiBunshoInfo() {
        return uiBunshoInfo;
    }
    /**
     * @return uploadLogic を戻します。
     */
    public UploadLogicImpl getUploadLogic() {
        return uploadLogic;
    }
    /**
     * @param uploadLogic uploadLogic を設定。
     */
    public void setUploadLogic(UploadLogicImpl uploadLogic) {
        this.uploadLogic = uploadLogic;
    }

    /**
     * @return publicTargetDto を戻します。
     */
    public PublicTargetDto getPublicTargetDto() {
        return publicTargetDto;
    }

    /**
     * @param publicTargetDto publicTargetDto を設定。
     */
    public void setPublicTargetDto(PublicTargetDto publicTargetDto) {
        this.publicTargetDto = publicTargetDto;
    }

    /**
     * @return publicTargetSearchDto を戻します。
     */
    public PublicTargetSearchDto getPublicTargetSearchDto() {
        return publicTargetSearchDto;
    }

    /**
     * @param publicTargetSearchDto publicTargetSearchDto を設定。
     */
    public void setPublicTargetSearchDto(PublicTargetSearchDto publicTargetSearchDto) {
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
        setDocumentRegistRegistFormDto(null);
        setDocumentRegistConditionDto(null);
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
     * カテゴリ、サブカテゴリ名設定処理
     * @param entity
     */
    private void setCategoryName(UIBunshoInfo entity) {
        //List listCate = getConditionDto().getListCategory();
        List listCate = getDocumentRegistRegistFormDto().getListCategory();
        for (int i = 0; i < listCate.size(); i++) {
            MstCategoryInfo mstCate = (MstCategoryInfo) listCate.get(i);
            if (entity.getCateId().equals(mstCate.getCateId())) {
                entity.setCateName(mstCate.getCateName());
            }
        }

        //List listSubCate = getConditionDto().getListSubCategory();
        List listSubCate = getDocumentRegistRegistFormDto().getListSubCategory();
        for (int i = 0; i < listSubCate.size(); i++) {
            MstSubCategoryInfo mstSubCate = (MstSubCategoryInfo) listSubCate.get(i);
            if (entity.getSubCateId().equals(mstSubCate.getSubCateId())) {
                entity.setSubCateName(mstSubCate.getSubCateName());
            }
        }
    }

    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

}
