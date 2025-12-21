/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.office.formregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.common.logic.GetSubCategoryLogic;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.UploadLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.FileUtil;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.office.formregist.action.FormRegistAction;
import jp.co.isid.mos.bird.office.formregist.dto.FormConditionDto;
import jp.co.isid.mos.bird.office.formregist.dto.FormRegistFormDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;
import jp.co.isid.mos.bird.office.formregist.logic.CheckFormRegInfoLogic;
import jp.co.isid.mos.bird.office.formregist.logic.CheckKanrenBunshoLogic;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * フォーム登録 編集画面アクション
 * @author xytamura
 */
public class FormRegistActionImpl implements CommonAction, FormRegistAction {
    /* アクションID */
    public static final String initialize_ACTION_ID = "BOF001A11";

    public static final String regist_ACTION_ID = "BOF001A12";

    public static final String cancel_ACTION_ID = "BOF001A13";

    public static final String callFormKokai_ACTION_ID = "BOF001A14";

    public static final String callFormKanrenBunsho_ACTION_ID = "BOF001A15";

    public static final String deleteKanrenBunsho_ACTION_ID = "BOF001A16";

    public static final String changeTab_ACTION_ID = "BOF001A17";

    public static final String uploadMainFile_ACTION_ID = "BOF001A18";

    public static final String deleteMainFile_ACTION_ID = "BOF001A19";

    public static final String uploadAttachFile_ACTION_ID = "BOF001A20";

    public static final String deleteAttachFile_ACTION_ID = "BOF001A21";

    public static final String changeCategory_ACTION_ID = "BOF001A22";
    
    /* ビューID */
    private static final String VIEWID_CONDITION = "BOF001V01";

    private static final String VIEWID_EDIT = "BOF001V02";

    private static final String VIEWID_CONFIRM = "BOF001V03";

    private static final String VIEWID_PUBLICTARGET_SEARCH = "BCO002V01";

    private static final String VIEWID_DOCUMENT_SEARCH = "BCO001V01";

    /* 情報種別 */
    private String infoShu;

//    /* タブ切替用Index */
//    private int selectedTabIndex = 1;

//    // 保存サブディレクトリ
//    private String subDir;

    /* DTO */
    private FormRegistFormDto formRegistFormDto;

    private FormConditionDto formConditionDto;

    private UIFormInfo uiFormInfo;

    //    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;

    private DocumentSearchDto documentSearchDto;

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;

    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

//    /* ロジック */
//    //【情報制限区分の取得】
//    private GetLimitKbnInfoLogic getLimitKbnInfoLogic;

    //【カテゴリ一覧の取得】
    private GetCategoryLogic getCategoryLogic;

    //【サブカテゴリ一覧の取得】
    private GetSubCategoryLogic getSubCategoryLogic;

    //【登録内容のチェック】
    private CheckFormRegInfoLogic checkFormRegInfoLogic;

    //【ファイルアップロード】
    private UploadLogic uploadLogic;

    //【関連文書の重複チェック】
    private CheckKanrenBunshoLogic checkKanrenBunshoLogic;

    /* アクション */
    //【ファイルアップロード】
    private FileUploadAction fileUploadAction;

    /* 定数 */
    // 掲載期間プルダウンに表示する日数
    private static final int KIKAN_LIST_NUM = 90;

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
        if (getFormConditionDto().isFlgCondToReg()) {
            //添付ファイル情報のクリア
            getFormRegistFormDto().clearUploadFile();
            
            // 新規モードの場合、初期値セット
            if(getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_INSERT){
                UIFormInfo entity = new UIFormInfo();
                setInitValue(entity);
                setUiFormInfo(entity);
                getFormRegistFormDto().setListKanrenBunsho(new ArrayList());;
                
            //編集モードの場合                
            }else if((getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_UPDATE) || (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_DELETE)){
                int index = getFormConditionDto().getSelectedListIndex();
                if (index >= 0) {
                    UIFormInfo entity = (UIFormInfo) getFormConditionDto().getListForm().get(index);
                    UIFormInfo editTarget = new UIFormInfo();
                    try{
                        //値をコピー
                        PropertyUtils.copyProperties(editTarget, entity);
                        
                    }catch(Exception ex){
                        throw new FtlSystemException("フォーム登録", ex.toString(), ex);
                    }
//                    setUiFormInfo(entity);
                    setUiFormInfo(editTarget);
                    //関連文書情報をセット
                    getFormRegistFormDto().setListKanrenBunsho(getFormConditionDto().getListKanrenBunsho());
                }
            }
            getFormConditionDto().setFlgCondToReg(false);
            // 選択タブ
//            getFormRegistFormDto().setTabIndex(1);
            getFormRegistFormDto().setEditEntity(getUiFormInfo());

        }
        // 公開対象
        if (publicTargetSearchDto.isActionFlg()) {

            // アクションフラグクリア
            publicTargetSearchDto.setActionFlg(false);
        }
        // 関連文書情報
        if (getDocumentSearchDto().isActionFlg()) {
            List listAddKanrenBunsho = getDocumentSearchDto().getDocumentList();
            List listMarge = getCheckKanrenBunshoLogic()
                                    .execute(getFormRegistFormDto().getListKanrenBunsho(),
                                           listAddKanrenBunsho);
            getFormRegistFormDto().setListKanrenBunsho(listMarge);
            getDocumentSearchDto().setActionFlg(false);
        }
        
        

//        // 初期編集タブをセット
//        if (getFormRegistFormDto().getTabIndex() == 0
//                || getFormConditionDto().isFlgCondToReg()) 
//        {
//            getFormRegistFormDto().setTabIndex(1);
//        }
        // 掲載期間プルダウン作成
        makeKikanPulldown();
//        // 情報制限プルダウン作成
//        makeLimitKbnPulldown();
        // カテゴリ、サブカテゴリ情報取得
        makeCategoryPulldown();
        
//        ErrHtmlElementDto dto = (ErrHtmlElementDto)SingletonS2ContainerFactory.getContainer().getComponent(ErrHtmlElementDto.class);
//        if("formNaiyo".equals(dto.getHtmlTabName())){
//            setSelectedTabIndex(1);
//            changeTab();
//        }else if("formFile".equals(dto.getHtmlTabName())){
//            setSelectedTabIndex(2);
//            changeTab();
//        }
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
     * 登録処理
     * @return String
     */
    public String regist() {
        // 編集中EntityをDTOへセット
        getFormRegistFormDto().setEditEntity(getUiFormInfo());
        // ロジック【登録内容のチェック】
        getCheckFormRegInfoLogic().execute(getFormRegistFormDto(),
                getPublicTargetSearchDto());

        return VIEWID_CONFIRM;
    }

    /**
     * 取消
     * @return
     */
    public String cancel() {
        // セッションクリア処理
        clearSession();
        return VIEWID_CONDITION;
    }

    /**
     * 関連文書選択フォーム
     * @return
     */
    public String callFormKanrenBunsho() {
        // 関連文書選択のパラメータ設定
        getDocumentSearchDto().setInitFlg(true);
        getDocumentSearchDto().setNavigationCase(VIEWID_EDIT);

        return VIEWID_DOCUMENT_SEARCH;
    }

    /**
     * 関連文書削除
     * @return
     */
    public String deleteKanrenBunsho() {
        List l = getFormRegistFormDto().getListKanrenBunsho();

        for (int i = 0; i < l.size(); i++) {
            UIDocSearch entity = (UIDocSearch) l.get(i);
            if (entity.isCheckedDoc()) {
                l.remove(i);
                i--;
                continue;
            }
        }
        return null;
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

    /*
     * 掲載期間プルダウン用リスト作成処理
     */
    private void makeKikanPulldown() {
        DateFormatter formatter = new DateFormatter();
        // 現在日付取得
        String sysDate = getBirdDateInfo().getSysDate();

        List listFrom = new ArrayList();
        List listTo = new ArrayList();
        for (int i = 0; i < KIKAN_LIST_NUM; i++) {
            String date = "";
            try {
                date = DateManager.getNextDate(sysDate, i);
            } catch (Exception ex) {
                throw new FtlSystemException("編集画面初期処理");
            }

            SelectItem item = new SelectItem(date, formatter.format(date,
                    DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
            listFrom.add(item);
            listTo.add(item);
        }
        //FROMのみ 掲載期間Fromの値が上で作成したリストに含まれない場合追加する
        String pubDateFrom = getUiFormInfo().getPubDateFrom();
        if (pubDateFrom != null && !"".equals(pubDateFrom.trim())) {
            if (sysDate.compareTo(pubDateFrom) > 0) {
                listFrom.add(0, new SelectItem(pubDateFrom, 
                                                formatter.format(pubDateFrom, 
                                                                 DateFormatter.PATTERN_SLASH, 
                                                                 DateFormatter.DATE_TYPE_YMD)));
            }
        }       
        //TOのみ「9999/12/31」を追加
        SelectItem item = new SelectItem("99991231", "9999/12/31");
        listTo.add(0, item);

        getFormRegistFormDto().setListFrom(listFrom);
        getFormRegistFormDto().setListTo(listTo);

    }

//    /*
//     * 情報制限プルダウン用リスト作成処理
//     */
//    private void makeLimitKbnPulldown() throws ApplicationException {
//        // ロジック【情報制限の取得】
//        List listLimitKbn = getGetLimitKbnInfoLogic().execute();
//
//        // DTOへセット
//        getFormRegistFormDto().setListLimitKbn(listLimitKbn);
//    }

    /*
     * カテゴリ、サブカテゴリプルダウン用リスト作成処理
     */
    private void makeCategoryPulldown() throws ApplicationException {
        getFormRegistFormDto().setListCategory(null);
        getFormRegistFormDto().setListSubCategory(null);
        
        // ロジック【カテゴリ一覧の取得】
        List listCategory = getGetCategoryLogic().getCategory(getInfoShu());
        // DTOへセット
        getFormRegistFormDto().setListCategory(listCategory);
        // ロジック【サブカテゴリ一覧の取得】
        String cateId = "";
        if (getUiFormInfo() != null && !isNull(getUiFormInfo().getCateId())) {
            cateId = getUiFormInfo().getCateId();
        }else{
            MstCategoryInfo mstCate = (MstCategoryInfo) listCategory.get(0);
            cateId = (String) mstCate.getCateId();

        }
        List listSubCategory = getGetSubCategoryLogic().getSubCategory(
                getInfoShu(), cateId);
        getFormRegistFormDto().setListSubCategory(listSubCategory);

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

//    /**
//     * @param getLimitKbnInfoLogic getLimitKbnInfoLogic を設定。
//     */
//    public void setGetLimitKbnInfoLogic(
//            GetLimitKbnInfoLogic getLimitKbnInfoLogic) {
//        this.getLimitKbnInfoLogic = getLimitKbnInfoLogic;
//    }
//
//    /**
//     * @return getLimitKbnInfoLogic を戻します。
//     */
//    public GetLimitKbnInfoLogic getGetLimitKbnInfoLogic() {
//        return getLimitKbnInfoLogic;
//    }

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
     * @return getSubCategoryLogic を戻します。
     */
    public GetSubCategoryLogic getGetSubCategoryLogic() {
        return getSubCategoryLogic;
    }

    /**
     * @param getSubCategoryLogic getSubCategoryLogic を設定。
     */
    public void setGetSubCategoryLogic(GetSubCategoryLogic getSubCategoryLogic) {
        this.getSubCategoryLogic = getSubCategoryLogic;
    }

    /**
     * @param infoShu infoShu を設定。
     */
    public void setInfoShu(String infoShu) {
        this.infoShu = infoShu;
    }

    /**
     * @return infoShu を戻します。
     */
    public String getInfoShu() {
        return infoShu;
    }

    /**
     * @param checkFormRegInfoLogic checkFormRegInfoLogic を設定。
     */
    public void setCheckFormRegInfoLogic(
            CheckFormRegInfoLogic checkFormRegInfoLogic) {
        this.checkFormRegInfoLogic = checkFormRegInfoLogic;
    }

    /**
     * @return checkBunshoRegInfoLogic を戻します。
     */
    public CheckFormRegInfoLogic getCheckFormRegInfoLogic() {
        return checkFormRegInfoLogic;
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

    //    /**
    //     * @return publicTargetDto を戻します。
    //     */
    //    public PublicTargetDto getPublicTargetDto() {
    //        return publicTargetDto;
    //    }
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

//    /**
//     * @param subDir subDir を設定。
//     */
//    public void setSubDir(String subDir) {
//        this.subDir = subDir;
//    }

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

    /**
     * セッションクリア処理
     */
    private void clearSession() {
//        getFormConditionDto().clearData();
        getFormRegistFormDto().clearData();
        getPublicTargetSearchDto().clearData();
        setUiFormInfo(null);
    }

    /**
     * 初期値設定処理
     * @param entity
     */
    private void setInitValue(UIFormInfo entity) {
        MstUser userInfo = getBirdUserInfo().getMstUser();
        if (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_INSERT) {
            if (entity == null) {
                entity = new UIFormInfo();
            }
            entity.setPubUser(userInfo.getUser_id());
            entity.setUserNameKj(userInfo.getUser_name());
            entity.setPubOrg(userInfo.getBumonCd());
            entity.setPubOrgName(userInfo.getBumonName());
            entity.setFirstUser(userInfo.getUser_id());
            entity.setLastUser(userInfo.getUser_id());
        } else if (getFormConditionDto().getRegMode() == FormConditionDto.REG_MODE_UPDATE) {
            entity.setLastUser(userInfo.getUser_id());
        }
    }

    /**
     * 共通フォーム 公開対象を開く
     */
    public String callFormKokai() {

        getFormRegistFormDto().setEditEntity(getUiFormInfo());
        // 公開対象選択のパラメータ設定
        publicTargetSearchDto.setReferenceFlg(false);
        publicTargetSearchDto.setInitFlag(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_EDIT);
        
        
        //        try {
        //            PropertyUtils.copyProperties(publicTargetSearchDto, publicTargetDto);
        //        } catch (Exception e) {
        //            throw new FtlSystemException("文書登録");
        //        }

        // 公開対象選択へ遷移
        return VIEWID_PUBLICTARGET_SEARCH;
    }

    /**
     * ファイル削除処理
     * @return
     */
    public String deleteMainFile() {
        // ファイル名を削除
//        getFormRegistFormDto().getEditEntity().setTitle("");
        getFormRegistFormDto().getEditEntity().setFileName("");
        getFormRegistFormDto().setUploadedMainFile(null);
        return null;
    }

    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile() {

        if (getFormRegistFormDto().isCmbDelFlg1()) {
            // 添付ファイル１情報削除
            getUiFormInfo().setAttachFl1("");
            getUiFormInfo().setAttachName1("");
            //getFormRegistFormDto().setUploadedAttachFile1(null);
            getFormRegistFormDto().setUploadFileNameTenpu1("");
            getFormRegistFormDto().setUploadTempFileNameTenpu1("");
            getFormRegistFormDto().setCmbDelFlg1(false);
        }
        if (getFormRegistFormDto().isCmbDelFlg2()) {
            // 添付ファイル２情報削除
            getUiFormInfo().setAttachFl2("");
            getUiFormInfo().setAttachName2("");
            //getFormRegistFormDto().setUploadedAttachFile2(null);
            getFormRegistFormDto().setUploadFileNameTenpu2("");
            getFormRegistFormDto().setUploadTempFileNameTenpu2("");
            getFormRegistFormDto().setCmbDelFlg2(false);
        }
        if (getFormRegistFormDto().isCmbDelFlg3()) {
            // 添付ファイル３情報削除
            getUiFormInfo().setAttachFl3("");
            getUiFormInfo().setAttachName3("");
            //getFormRegistFormDto().setUploadedAttachFile3(null);
            getFormRegistFormDto().setUploadFileNameTenpu3("");
            getFormRegistFormDto().setUploadTempFileNameTenpu3("");
            getFormRegistFormDto().setCmbDelFlg3(false);
        }
        return null;
    }

    /**
     * フォームアップロード処理
     * @return
     */
    public String uploadMainFile() {
        getFormRegistFormDto().setUploadedFile(
                getFormRegistFormDto().getUploadedMainFile());

        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getFormRegistFormDto());
        getFileUploadAction().setUploadLogic(getUploadLogic());

        // ファイル選択チェック
        if (getFormRegistFormDto().getUploadedFile() == null) {
            throw new NotNullException("ファイル", "uploadFile", null);
        }
        
        // ファイルアップロードアクション実行
        String ret = getFileUploadAction().uploadTemporary();
        
        if ( getFormRegistFormDto().getUploadedMainFile() == null ) {
            throw new NotNullException("ファイル名", "uploadFile", null);
        }
        
        String file = getFormRegistFormDto().getUploadedFile().getName();
        String filename = file.substring(file.lastIndexOf(FileUtil.getFileSeparator()) + 1);
        
        LengthVerifier fileVeri = new LengthVerifier(60);
        if ( !fileVeri.validate(filename)) {
            throw new GenericCommentException("ファイル名は全角３０文字まで", "uploadFile", null);
        }
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if ( ! defJap.validate( getFormRegistFormDto().getUploadFileName() ) ) {
            throw new InvalidInputException("ファイル名", "uploadFile", null);
        }

        // ファイル存在チェック（空のファイルは不可）
        if (getFormRegistFormDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFile", null);
        }
        
        // 共通アクションで設定された値を保持
        getFormRegistFormDto().setUploadFileNameMain(
                getFormRegistFormDto().getUploadFileName());
        getFormRegistFormDto().setUploadTempFileNameMain(
                getFormRegistFormDto().getTempFileName());
        getUiFormInfo().setFileName(getFormRegistFormDto().getUploadFileName());
        return ret;
    }

    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile() {
        getFormRegistFormDto().setUploadedFile(
                getFormRegistFormDto().getUploadedAttachFile());

        if ( getFormRegistFormDto().getAttachName() == null ) {
            throw new NotNullException("添付ファイルタイトル", "uploadFileTenpuTitle", null);
        }
        if(getFormRegistFormDto().getAttachName().getBytes().length > 80){
            throw new InvalidInputException("添付ファイルタイトル", "uploadFileTenpuTitle", null);
        }
        
        if ( getFormRegistFormDto().getUploadedAttachFile() == null ) {
            throw new NotNullException("添付ファイル", "uploadFileTenpu", null);
        }
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if (!defJap.validate( getFormRegistFormDto().getAttachName()) ) {
            throw new InvalidInputException("添付ファイルタイトル", "uploadFileTenpuTitle", null);
        }

        if (!defJap.validate( getFormRegistFormDto().getAttachName()) ) {
            throw new InvalidInputException("添付ファイルタイトル", "uploadFileTenpuTitle", null);
        }
        
// add start xkhata 
        String file = getFormRegistFormDto().getUploadedFile().getName();
        String filename = file.substring(file.lastIndexOf(FileUtil.getFileSeparator()) + 1);
        DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
        if (!dj.validate(filename)) {
            throw new InvalidInputException("添付ファイル", "uploadFileTenpu", null);
        }
// add end
        
        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getFormRegistFormDto());
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
        if (isNull(getFormRegistFormDto().getEditEntity().getAttachName1())) {
            index = 1;
        } else if (isNull(getFormRegistFormDto().getEditEntity()
                .getAttachName2())) {
            index = 2;
        } else if (isNull(getFormRegistFormDto().getEditEntity()
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
        if ( !atachVeri.validate( getFormRegistFormDto().getUploadFileName()) ) {
            throw new GenericCommentException("添付ファイル名は全角３０文字まで", "uploadFileTenpu", null);
        }       

        // ファイル存在チェック（空のファイルは不可）
        if (getFormRegistFormDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFileTenpu", null);
        }
        
        switch (getEmptyAttachIndex()) {
        case 1:
            getFormRegistFormDto().setUploadFileNameTenpu1(
                    getFormRegistFormDto().getUploadFileName());
            getFormRegistFormDto().setUploadTempFileNameTenpu1(
                    getFormRegistFormDto().getTempFileName());
            getUiFormInfo().setAttachFl1(
                    getFormRegistFormDto().getUploadFileName());
            getUiFormInfo().setAttachName1(
                    getFormRegistFormDto().getAttachName());
            break;
        case 2:
            getFormRegistFormDto().setUploadFileNameTenpu2(
                    getFormRegistFormDto().getUploadFileName());
            getFormRegistFormDto().setUploadTempFileNameTenpu2(
                    getFormRegistFormDto().getTempFileName());
            getUiFormInfo().setAttachFl2(
                    getFormRegistFormDto().getUploadFileName());
            getUiFormInfo().setAttachName2(
                    getFormRegistFormDto().getAttachName());
            break;
        case 3:
            getFormRegistFormDto().setUploadFileNameTenpu3(
                    getFormRegistFormDto().getUploadFileName());
            getFormRegistFormDto().setUploadTempFileNameTenpu3(
                    getFormRegistFormDto().getTempFileName());
            getUiFormInfo().setAttachFl3(
                    getFormRegistFormDto().getUploadFileName());
            getUiFormInfo().setAttachName3(
                    getFormRegistFormDto().getAttachName());
            break;
        default:
            break;
        }
        getFormRegistFormDto().setAttachName("");
        getFormRegistFormDto().setUploadFileName("");
    }

    /**
     * @return documentSearchDto を戻します。
     */
    public DocumentSearchDto getDocumentSearchDto() {
        return documentSearchDto;
    }

    /**
     * @param documentSearchDto documentSearchDto を設定。
     */
    public void setDocumentSearchDto(DocumentSearchDto documentSearchDto) {
        this.documentSearchDto = documentSearchDto;
    }

    /**
     * @return checkKanrenBunshoLogic を戻します。
     */
    public CheckKanrenBunshoLogic getCheckKanrenBunshoLogic() {
        return checkKanrenBunshoLogic;
    }

    /**
     * @param checkKanrenBunshoLogic checkKanrenBunshoLogic を設定。
     */
    public void setCheckKanrenBunshoLogic(
            CheckKanrenBunshoLogic checkKanrenBunshoLogic) {
        this.checkKanrenBunshoLogic = checkKanrenBunshoLogic;
    }

    /**
     * カテゴリ変更
     */
    public String changeCategory() {
        //選択されているサブカテゴリをクリア
        getUiFormInfo().setSubCateId("");
        
        return null;
    }
}