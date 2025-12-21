/*
 * 作成日: 2006/07/05
 */
package jp.co.isid.mos.bird.office.bbsregist.action.impl;

import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;
import jp.co.isid.mos.bird.framework.util.FileUtil;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.office.bbsregist.action.KeijibanRegistAction;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanConditionDto;
import jp.co.isid.mos.bird.office.bbsregist.dto.KeijibanRegistDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;
import jp.co.isid.mos.bird.office.bbsregist.logic.CheckEditAllowedLogic;
import jp.co.isid.mos.bird.office.bbsregist.logic.CheckKeijibanInfoLogic;
import jp.co.isid.mos.bird.office.bbsregist.logic.RegKeijibanLogic;

/**
 * 掲示板登録 登録画面アクション
 * @author xytamura
 */
public class KeijibanRegistActionImpl implements CommonAction,
        KeijibanRegistAction {
    /* アクションID */
    public static final String initialize_ACTION_ID = "BOF003A13";

    public static final String cancel_ACTION_ID = "BOF003A14";

    public static final String regist_ACTION_ID = "BOF003A15";

    public static final String uploadAttachFile_ACTION_ID = "BOF003A16";

    public static final String deleteAttachFile_ACTION_ID = "BOF003A17";

    /* DTO */
    private KeijibanRegistDto keijibanRegistDto;

    private KeijibanConditionDto keijibanConditionDto;


    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;

    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    // 【カテゴリ一覧の取得】
    private GetCategoryLogic getCategoryLogic;

    // 【登録内容のチェック】
    private CheckKeijibanInfoLogic checkKeijibanInfoLogic;

    // 【掲示板情報の登録】
    private RegKeijibanLogic regKeijibanLogic;

    // 【更新可能ユーザチェック】
    private CheckEditAllowedLogic checkEditAllowedLogic;

    // 【ファイルアップロード】
    private UploadLogic uploadLogic;

    /* アクション */
    // 【ファイルアップロード】
    private FileUploadAction fileUploadAction;

    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        if (getKeijibanConditionDto().isFlgCondToReg()) {
            getKeijibanRegistDto().clearData();
            // 新規モードの場合、初期値セット
            if (getKeijibanRegistDto().getRegMode() == KeijibanConditionDto.REG_MODE_INSERT) {
                UIKeijiban entity = new UIKeijiban();
                setInitValue(entity);
                getKeijibanRegistDto().setEditEntity(entity);
                getKeijibanRegistDto().setEditFlg(true);

                // 編集モードの場合
            } else if ((getKeijibanRegistDto().getRegMode() == KeijibanConditionDto.REG_MODE_UPDATE)
                    || (getKeijibanRegistDto().getRegMode() == KeijibanConditionDto.REG_MODE_DELETE)) {

                // 【ロジック】更新可能ユーザチェック
                boolean isEdit = getCheckEditAllowedLogic().execute(
                        getKeijibanRegistDto().getEditEntity(),
                        birdUserInfo.getMstUser());
                getKeijibanRegistDto().setEditFlg(isEdit);

            }
            makeCategoryPulldown();
            getKeijibanConditionDto().setFlgCondToReg(false);
        }

        return null;
    }

    /**
     * 登録処理
     * @return String
     */
    public String regist() {
        // ロジック【登録内容のチェック】
        getCheckKeijibanInfoLogic().execute(getKeijibanRegistDto());
        // ロジック【掲示板情報の登録】
        getRegKeijibanLogic().execute(getKeijibanRegistDto(),
                getBirdUserInfo().getMstUser(), getBirdDateInfo());
        // アップロード
        upload();
        getKeijibanRegistDto().setRegToCondFlg(true);
        return KeijibanConditionDto.VIEWID_CONDITION;
    }

    /**
     * 取消
     * @return
     */
    public String cancel() {
        if (getKeijibanRegistDto().getRegMode() == KeijibanConditionDto.REG_MODE_INSERT) {
            return KeijibanConditionDto.VIEWID_CONDITION;
        }
        return KeijibanConditionDto.VIEWID_REF;
    }

    /**
     * @param conditionDto conditionDto を設定。
     */
    public void setKeijibanConditionDto(
            KeijibanConditionDto keijibanConditionDto) {
        this.keijibanConditionDto = keijibanConditionDto;
    }

    /**
     * @return conditionDto を戻します。
     */
    public KeijibanConditionDto getKeijibanConditionDto() {
        return keijibanConditionDto;
    }

    /**
     * @param registFormDto registFormDto を設定。
     */
    public void setKeijibanRegistDto(KeijibanRegistDto keijibanRegistDto) {
        this.keijibanRegistDto = keijibanRegistDto;
    }

    /**
     * @return registFormDto を戻します。
     */
    public KeijibanRegistDto getKeijibanRegistDto() {
        return keijibanRegistDto;
    }

    /*
     * カテゴリプルダウン用リスト作成処理
     */
    private void makeCategoryPulldown() throws ApplicationException {
        getKeijibanRegistDto().setListCategory(null);

        // ロジック【カテゴリ一覧の取得】
        List listCategory = getGetCategoryLogic().getCategory(
                KeijibanConditionDto.INFO_SHU);
        // DTOへセット
        getKeijibanRegistDto().setListCategory(listCategory);
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
     * @param birdDateInfo birdDateInfo を設定。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
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
     * @return getCategoryLogic を戻します。
     */
    public GetCategoryLogic getGetCategoryLogic() {
        return getCategoryLogic;
    }

    /**
     * @param getCategoryLogic getCategoryLogic を設定。
     */
    public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
        this.getCategoryLogic = getCategoryLogic;
    }


    /**
     * @param checkFormRegInfoLogic checkFormRegInfoLogic を設定。
     */
    public void setCheckKeijibanInfoLogic(
            CheckKeijibanInfoLogic checkKeijibanInfoLogic) {
        this.checkKeijibanInfoLogic = checkKeijibanInfoLogic;
    }

    /**
     * @return checkBunshoRegInfoLogic を戻します。
     */
    public CheckKeijibanInfoLogic getCheckKeijibanInfoLogic() {
        return checkKeijibanInfoLogic;
    }

    /**
     * @return fileUploadAction を戻します。
     */
    public FileUploadAction getFileUploadAction() {
        return fileUploadAction;
    }

    /**
     * @param fileUploadAction fileUploadAction を設定。
     */
    public void setFileUploadAction(FileUploadAction fileUploadAction) {
        this.fileUploadAction = fileUploadAction;
    }

    /**
     * @return uploadLogic を戻します。
     */
    public UploadLogic getUploadLogic() {
        return uploadLogic;
    }

    /**
     * @param uploadLogic uploadLogic を設定。
     */
    public void setUploadLogic(UploadLogic uploadLogic) {
        this.uploadLogic = uploadLogic;
    }

    /*
     * Null、空文字チェック
     */
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }

    // /**
    // * セッションクリア処理
    // */
    // private void clearSession() {
    // // getKeijibanConditionDto().clearData();
    // getKeijibanRegistDto().clearData();
    // // getPublicTargetSearchDto().clearData();
    // setUIKeijiban(null);
    // }

    /**
     * 初期値設定処理
     * @param entity
     */
    private void setInitValue(UIKeijiban entity) {
        if (getKeijibanRegistDto().getRegMode() == KeijibanConditionDto.REG_MODE_INSERT) {
            if (entity == null) {
                entity = new UIKeijiban();
            }
            entity.setFirstUser(getBirdUserInfo().getUserID());
            entity.setFirstUserName(getBirdUserInfo().getMstUser()
                    .getUser_name());
        }
    }

    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile() {

        if (getKeijibanRegistDto().isCmbDelFlg1()) {
            // 添付ファイル１情報削除
            getKeijibanRegistDto().getEditEntity().setAttachFl1("");
            getKeijibanRegistDto().getEditEntity().setAttachName1("");
            getKeijibanRegistDto().setUploadFileNameTenpu1("");
            getKeijibanRegistDto().setUploadTempFileNameTenpu1("");
            getKeijibanRegistDto().setCmbDelFlg1(false);

        }
        if (getKeijibanRegistDto().isCmbDelFlg2()) {
            // 添付ファイル２情報削除
            getKeijibanRegistDto().getEditEntity().setAttachFl2("");
            getKeijibanRegistDto().getEditEntity().setAttachName2("");
            getKeijibanRegistDto().setUploadFileNameTenpu2("");
            getKeijibanRegistDto().setUploadTempFileNameTenpu2("");
            getKeijibanRegistDto().setCmbDelFlg2(false);
        }
        if (getKeijibanRegistDto().isCmbDelFlg3()) {
            // 添付ファイル３情報削除
            getKeijibanRegistDto().getEditEntity().setAttachFl3("");
            getKeijibanRegistDto().getEditEntity().setAttachName3("");
            getKeijibanRegistDto().setUploadFileNameTenpu3("");
            getKeijibanRegistDto().setUploadTempFileNameTenpu3("");
            getKeijibanRegistDto().setCmbDelFlg3(false);
        }
        return null;
    }

    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile() {
        getKeijibanRegistDto().setUploadedFile(
                getKeijibanRegistDto().getUploadedAttachFile());

        if (getKeijibanRegistDto().getAttachName() == null) {
            throw new NotNullException("添付ファイルタイトル", "uploadFileTenpuTitle",
                    null);
        }
        if (getKeijibanRegistDto().getAttachName().getBytes().length > 80) {
            throw new InvalidInputException("添付ファイルタイトル",
                    "uploadFileTenpuTitle", null);
        }

        if (getKeijibanRegistDto().getUploadedAttachFile() == null) {
            throw new NotNullException("添付ファイル", "uploadFileTenpu", null);
        }
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if (!defJap.validate(getKeijibanRegistDto().getAttachName())) {
            throw new InvalidInputException("添付ファイルタイトル",
                    "uploadFileTenpuTitle", null);
        }

// add start xkhata 20070627
        String file = getKeijibanRegistDto().getUploadedFile().getName();
        String filename = file.substring(file.lastIndexOf(FileUtil.getFileSeparator()) + 1);
        DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
        if ( !dj.validate(filename)) {
            throw new InvalidInputException("添付ファイル", "uploadFileTenpu", null);
        }
// add end
        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getKeijibanRegistDto());
        getFileUploadAction().setUploadLogic(getUploadLogic());

        // ファイルアップロードアクション実行
        String ret = getFileUploadAction().uploadTemporary();

        // 共通アクションで設定された値を保持
        setAttachFileInfo();
        return ret;
    }

    /*
     * 添付ファイル１～３の登録可能箇所を取得
     */
    private int getEmptyAttachIndex() {
        int index = -1;
        if (isNull(getKeijibanRegistDto().getEditEntity().getAttachName1())) {
            index = 1;
        } else if (isNull(getKeijibanRegistDto().getEditEntity()
                .getAttachName2())) {
            index = 2;
        } else if (isNull(getKeijibanRegistDto().getEditEntity()
                .getAttachName3())) {
            index = 3;
        }
        return index;
    }

    /*
     * 添付ファイル情報設定処理
     */
    private void setAttachFileInfo() {

        LengthVerifier atachVeri = new LengthVerifier(60);
        if (!atachVeri.validate(getKeijibanRegistDto().getUploadFileName())) {
            throw new GenericCommentException("添付ファイル名は全角３０文字まで",
                    "uploadFileTenpu", null);
        }

        // ファイル存在チェック（空のファイルは不可）
        if (getKeijibanRegistDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル",
                    "uploadFileTenpu", null);
        }

        switch (getEmptyAttachIndex()) {
        case 1:
            getKeijibanRegistDto().setUploadFileNameTenpu1(
                    getKeijibanRegistDto().getUploadFileName());
            getKeijibanRegistDto().setUploadTempFileNameTenpu1(
                    getKeijibanRegistDto().getTempFileName());
            getKeijibanRegistDto().getEditEntity().setAttachFl1(
                    getKeijibanRegistDto().getUploadFileName());
            getKeijibanRegistDto().getEditEntity().setAttachName1(
                    getKeijibanRegistDto().getAttachName());
            break;
        case 2:
            getKeijibanRegistDto().setUploadFileNameTenpu2(
                    getKeijibanRegistDto().getUploadFileName());
            getKeijibanRegistDto().setUploadTempFileNameTenpu2(
                    getKeijibanRegistDto().getTempFileName());
            getKeijibanRegistDto().getEditEntity().setAttachFl2(
                    getKeijibanRegistDto().getUploadFileName());
            getKeijibanRegistDto().getEditEntity().setAttachName2(
                    getKeijibanRegistDto().getAttachName());
            break;
        case 3:
            getKeijibanRegistDto().setUploadFileNameTenpu3(
                    getKeijibanRegistDto().getUploadFileName());
            getKeijibanRegistDto().setUploadTempFileNameTenpu3(
                    getKeijibanRegistDto().getTempFileName());
            getKeijibanRegistDto().getEditEntity().setAttachFl3(
                    getKeijibanRegistDto().getUploadFileName());
            getKeijibanRegistDto().getEditEntity().setAttachName3(
                    getKeijibanRegistDto().getAttachName());
            break;
        default:
            break;
        }
        getKeijibanRegistDto().setAttachName("");
        getKeijibanRegistDto().setUploadFileName("");
    }

    /**
     * regKeijibanLogicを取得します。
     * @return regKeijibanLogic
     */
    public RegKeijibanLogic getRegKeijibanLogic() {
        return regKeijibanLogic;
    }

    /**
     * regKeijibanLogicを設定します。
     * @param regKeijibanLogic
     */
    public void setRegKeijibanLogic(RegKeijibanLogic regKeijibanLogic) {
        this.regKeijibanLogic = regKeijibanLogic;
    }

    /**
     * checkEditAllowedLogicを取得します。
     * @return checkEditAllowedLogic
     */
    public CheckEditAllowedLogic getCheckEditAllowedLogic() {
        return checkEditAllowedLogic;
    }

    /**
     * checkEditAllowedLogicを設定します。
     * @param checkEditAllowedLogic
     */
    public void setCheckEditAllowedLogic(
            CheckEditAllowedLogic checkEditAllowedLogic) {
        this.checkEditAllowedLogic = checkEditAllowedLogic;
    }

    /**
     * アップロード
     * @return
     */
    public String upload() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        FileUploadAction action = (FileUploadAction) container
                .getComponent("keijibanRegistUploadTenpuAction");

        action.setUploadDto(getKeijibanRegistDto());
        action.setUploadLogic(getUploadLogic());
        for (int i = 2; i <= 4; i++) {
            getKeijibanRegistDto().setUploadIndex(i);
            switch (i) {
            case 2:
                getKeijibanRegistDto().setTempFileName(
                        getKeijibanRegistDto().getUploadTempFileNameTenpu1());
                getKeijibanRegistDto().setUploadFileName(
                        getKeijibanRegistDto().getUploadFileNameTenpu1());
                getKeijibanRegistDto().setUploadIndex(2);
                break;
            case 3:
                getKeijibanRegistDto().setTempFileName(
                        getKeijibanRegistDto().getUploadTempFileNameTenpu2());
                getKeijibanRegistDto().setUploadFileName(
                        getKeijibanRegistDto().getUploadFileNameTenpu2());
                getKeijibanRegistDto().setUploadIndex(3);
                break;
            case 4:
                getKeijibanRegistDto().setTempFileName(
                        getKeijibanRegistDto().getUploadTempFileNameTenpu3());
                getKeijibanRegistDto().setUploadFileName(
                        getKeijibanRegistDto().getUploadFileNameTenpu3());
                getKeijibanRegistDto().setUploadIndex(4);
                break;
            }

            if (getKeijibanRegistDto().getTempFileName() != null
                    && getKeijibanRegistDto().getTempFileName().trim().length() != 0) {
                action.upload();
            }
        }
        return null;
    }

}