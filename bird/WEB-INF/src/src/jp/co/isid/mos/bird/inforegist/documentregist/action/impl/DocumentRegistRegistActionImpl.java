/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.inforegist.documentregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
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
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.inforegist.documentregist.action.DocumentRegistRegistAction;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistConditionDto;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistFormDto;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.CheckBunshoRegInfoLogic;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.CheckKanrenBunshoLogic;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * 文書登録 編集画面アクション
 * @author xnkusama
 */
public class DocumentRegistRegistActionImpl implements CommonAction, DocumentRegistRegistAction {
    /* アクションID */
    public static final String initialize_ACTION_ID   = "BIF003A09";
    public static final String cancel_ACTION_ID       = "BIF003A10";
    public static final String changeTab_ACTION_ID    = "BIF003A11";
    public static final String regist_ACTION_ID       = "BIF003A12";
    public static final String callForm_ACTION_ID     = "BIF003A22";
    public static final String uploadMainFile_ACTION_ID   = "BIF003A23";
    public static final String deleteMainFile_ACTION_ID   = "BIF003A24";
    public static final String uploadAttachFile_ACTION_ID = "BIF003A25";
    public static final String deleteAttachFile_ACTION_ID = "BIF003A26";
    public static final String callFormKanrenBunsho_ACTION_ID = "BIF003A27";
    public static final String deleteKanrenBunsho_ACTION_ID   = "BIF003A28";
    public static final String changeCategory_ACTION_ID   = "BIF003A29";
    
    /* ビューID */
    private static final String VIEWID_CONDITION= "BIF003V01";
    private static final String VIEWID_EDIT     = "BIF003V02";
    private static final String VIEWID_CONFIRM  = "BIF003V03";
    private static final String VIEWID_PUBLICTARGET_SEARCH  = "BCO002V01";
    private static final String VIEWID_DOCUMENT_SEARCH      = "BCO001V01";

    /* 定数 */
    // 掲載期間プルダウンに表示する日数
    private static final int KIKAN_LIST_NUM = 90;
    
    // タイトル最大文字数
    private final static int TITLE_MAX_LENGTH = 80; 
    
    // ファイル名最大文字数
    private final static int FILE_NAME_MAX_LENGTH = 60;
    
    
    /* 情報種別 */
    private String infoShu;
    /* DTO */
    private DocumentRegistRegistFormDto documentRegistRegistFormDto;
    private DocumentRegistConditionDto documentRegistConditionDto;
    private UIBunshoInfo uiBunshoInfo;
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;
    private DocumentSearchDto documentSearchDto;
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;
    /* ロジック */
//    //【情報制限区分の取得】
//    private GetLimitKbnInfoLogic getLimitKbnInfoLogic;
    //【カテゴリ一覧の取得】
    private GetCategoryLogic getCategoryLogic;
    //【サブカテゴリ一覧の取得】
    private GetSubCategoryLogic getSubCategoryLogic;
    //【登録内容のチェック】
    private CheckBunshoRegInfoLogic checkBunshoRegInfoLogic;
    //【ファイルアップロード】
    private UploadLogic uploadLogic;
    //【関連文書の重複チェック】
    private CheckKanrenBunshoLogic checkKanrenBunshoLogic;
    /* アクション */
    //【ファイルアップロード】
    private FileUploadAction fileUploadAction;
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
//        UIBunshoInfo entityBunsho = new UIBunshoInfo();
        // 編集EntityをDTOへセット
        if (getDocumentRegistConditionDto().isFlgCondToReg()) {
            //添付ファイル情報のクリア
            getDocumentRegistRegistFormDto().clearUploadFile();
            
            int index = getDocumentRegistConditionDto().getSelectedListIndex();
            if (index >= 0) {
                UIBunshoInfo entity = (UIBunshoInfo) getDocumentRegistConditionDto().getListBunsho().get(index);
//                setUiBunshoInfo(entity);
                setUiBunshoInfo(getNewEditEntity(entity));
                
            }
            else {
                setUiBunshoInfo(new UIBunshoInfo());
            }
            getDocumentRegistConditionDto().setFlgCondToReg(false);

            getDocumentRegistRegistFormDto().setEditEntity(getUiBunshoInfo());

//            // 選択タブ
//            getDocumentRegistRegistFormDto().setTabIndex(1);

            // 新規モードの場合、初期値セット
            setInitValue(getUiBunshoInfo());
            
            // 検索した関連文書をセット
            getDocumentRegistRegistFormDto().setListKanrenBunsho(getDocumentRegistConditionDto().getListKanrenBunsho());
        }
        // 公開対象
        if (publicTargetSearchDto.isActionFlg()) {

            // 公開対象選択の結果反映
            try {
                PropertyUtils.copyProperties(publicTargetDto, publicTargetSearchDto);
            } catch (Exception e) {
                throw new FtlSystemException("文書登録");
            }

            // アクションフラグクリア
            publicTargetSearchDto.setActionFlg(false);
        }
        // 関連文書情報
        if (getDocumentSearchDto().isActionFlg()) {
            List listAddKanrenBunsho = getDocumentSearchDto().getDocumentList();
            List listMarge = getCheckKanrenBunshoLogic()
                                    .check(getDocumentRegistRegistFormDto().getListKanrenBunsho(),
                                           listAddKanrenBunsho);
            getDocumentRegistRegistFormDto().setListKanrenBunsho(listMarge);
            getDocumentSearchDto().setActionFlg(false);
        }
        
        // 初期編集タブをセット
        if (getDocumentRegistRegistFormDto().getTabIndex() == 0
                || getDocumentRegistConditionDto().isFlgCondToReg()) 
        {
            getDocumentRegistRegistFormDto().setTabIndex(1);
        }
        // 掲載期間プルダウン作成
        makeKikanPulldown();
//        // 情報制限プルダウン作成
//        makeLimitKbnPulldown();
        // カテゴリ、サブカテゴリ情報取得
        makeCategoryPulldown();
        return null;
    }
    
//    /**
//     * タブ変更処理
//     * @return
//     */
//    public String changeTab() {
//        switch (getSelectedTabIndex()) {
//            case TAB_INDEX_BUNSHO:
//                getDocumentRegistRegistFormDto().setTabIndex(1);
//                getDocumentRegistRegistFormDto().setEditEntity(getUiBunshoInfo());
//                break;
//            case TAB_INDEX_FILE:
//                getDocumentRegistRegistFormDto().setTabIndex(2);
//                getDocumentRegistRegistFormDto().setEditEntity(getUiBunshoInfo());
//            default:
//        }
//        return null;
//    }
    
    /**
     * 登録処理
     * @return String
     */
    public String regist() {
    	
//    	// 情報制限区分リスト
//    	List listInfo = getDocumentRegistRegistFormDto().getListLimitKbn();
//    	for (int i = 0; i < listInfo.size(); i++ ) {
//    		CodInfoLimitInfo entity = (CodInfoLimitInfo)listInfo.get(i);
//    		if ( entity.getLimitKbn().equals( getUiBunshoInfo().getLimitKbn()) ) {
//    			getUiBunshoInfo().setLimitKbnName( entity.getLimitKbnName() );
//    			break;
//    		}
//    	}

        // 編集中EntityをDTOへセット
        getDocumentRegistRegistFormDto().setEditEntity(getUiBunshoInfo());
        // ロジック【登録内容のチェック】
        getCheckBunshoRegInfoLogic().checkBunshoRegInfo(getDocumentRegistRegistFormDto(), getPublicTargetDto());
        
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
// 2006/03/15 xkhata 関連文書削除
        
        List kanrenBunshoList = getDocumentRegistRegistFormDto().getListKanrenBunsho();
        
        for ( int i = kanrenBunshoList.size() - 1; i > - 1; i-- ) {
            UIDocSearch uIDocSearch = (UIDocSearch)kanrenBunshoList.get(i);
            if ( uIDocSearch.isCheckedDoc() ) {
            	kanrenBunshoList.remove(i);
            }
        }
// end        
        
        return null;
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
            }
            catch (Exception ex) {
                throw new FtlSystemException("編集画面初期処理");
            }
                                     
            SelectItem item = new SelectItem(
                                        date, 
                                        formatter.format(date, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD)); 
            listFrom.add(item);
            listTo.add(item);
        }
        //FROMのみ 掲載期間Fromの値が上で作成したリストに含まれない場合追加する
        String pubDateFrom = getUiBunshoInfo().getPubDateFrom();
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
        
        getDocumentRegistRegistFormDto().setListFrom(listFrom);
        getDocumentRegistRegistFormDto().setListTo(listTo);

    }
    /**
     * カテゴリプルダウン変更処理
     * 
     * @return
     */
    public String changeCategory(){
        //選択されているサブカテゴリをクリア
        getUiBunshoInfo().setSubCateId("");
    	return null;
    }

    /*
     * カテゴリ、サブカテゴリプルダウン用リスト作成処理
     */
    private void makeCategoryPulldown() throws ApplicationException {
        getDocumentRegistRegistFormDto().setListCategory(null);
        // ロジック【カテゴリ一覧の取得】
        List listCategory = getGetCategoryLogic().getCategory(getInfoShu());
        // DTOへセット
        getDocumentRegistRegistFormDto().setListCategory(listCategory);
        // ロジック【サブカテゴリ一覧の取得】
        makeSubCategoryPulldown();
    }
    /*
     * カテゴリ、サブカテゴリプルダウン用リスト作成処理
     */
    private void makeSubCategoryPulldown() {
        getDocumentRegistRegistFormDto().setListSubCategory(null);        
        // ロジック【サブカテゴリ一覧の取得】
        String cateId;
        if (getUiBunshoInfo() != null && !isNull(getUiBunshoInfo().getCateId())) {
             cateId = getUiBunshoInfo().getCateId();
        }
        else {
            MstCategoryInfo mstCate = (MstCategoryInfo) getDocumentRegistRegistFormDto().getListCategory().get(0);
            cateId = (String) mstCate.getCateId();
        }
        List listSubCategory = getGetSubCategoryLogic().getSubCategory(getInfoShu(), cateId);
        getDocumentRegistRegistFormDto().setListSubCategory(listSubCategory);
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
     * @param checkBunshoRegInfoLogic checkBunshoRegInfoLogic を設定。
     */
    public void setCheckBunshoRegInfoLogic(CheckBunshoRegInfoLogic checkBunshoRegInfoLogic) {
        this.checkBunshoRegInfoLogic = checkBunshoRegInfoLogic;
    }

    /**
     * @return checkBunshoRegInfoLogic を戻します。
     */
    public CheckBunshoRegInfoLogic getCheckBunshoRegInfoLogic() {
        return checkBunshoRegInfoLogic;
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
    public void setPublicTargetSearchDto(
            PublicTargetSearchDto publicTargetSearchDto) {
        this.publicTargetSearchDto = publicTargetSearchDto;
    }
    
//    /**
//     * @return subDir を戻します。
//     */
//    public String getSubDir() {
//        return BirdContext.getProperty("fileProperty", "filePathBunsho");
//    }
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
        //getConditionDto().clearRegistToCond();
    }
    
    /**
     * 初期値設定処理
     * @param entity
     */
    private void setInitValue(UIBunshoInfo entity) {
        MstUser userInfo = getBirdUserInfo().getMstUser();
        // キー情報
        if (getDocumentRegistConditionDto().getRegMode() == DocumentRegistConditionDto.REG_MODE_INSERT) {
        	entity.setRegDate(getBirdDateInfo().getSysDate());
            entity.setPubUser(userInfo.getUser_id());
            entity.setUserNameKj(userInfo.getUser_name());
            entity.setPubOrg(userInfo.getBumonCd());
            entity.setPubOrgName(userInfo.getBumonName());
            entity.setFirstUser(userInfo.getUser_id());
            entity.setSakujoFlg("0");
            entity.setRCompanyCd(userInfo.getRCompanyCd());
        }
        
        entity.setLastUser(userInfo.getUser_id());
    }

    /**
     * 共通フォーム 公開対象を開く
     */
    public String callForm() throws Exception {

        getDocumentRegistRegistFormDto().setEditEntity(getUiBunshoInfo());
        // 公開対象選択のパラメータ設定
        publicTargetSearchDto.setReferenceFlg(false);
        publicTargetSearchDto.setInitFlag(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_EDIT);
        publicTargetSearchDto.setInfoShu(publicTargetDto.getInfoShu());
        publicTargetSearchDto.setRegDate(publicTargetDto.getRegDate());
        publicTargetSearchDto.setSeq(publicTargetDto.getSeq());

        //PropertyUtils.copyProperties(publicTargetSearchDto, publicTargetDto);
        publicTargetSearchDto.setListTrnControlCompany(publicTargetDto.getListTrnControlCompany());
        publicTargetSearchDto.setListTrnControlGyotai(publicTargetDto.getListTrnControlGyotai());
        publicTargetSearchDto.setListTrnControlGyotaiKobetu(publicTargetDto.getListTrnControlGyotaiKobetu());
        publicTargetSearchDto.setListTrnControlGyotaiTenpo(publicTargetDto.getListTrnControlGyotaiTenpo());
        publicTargetSearchDto.setListTrnControlShozoku(publicTargetDto.getListTrnControlShozoku());

        // 公開対象選択へ遷移
        return VIEWID_PUBLICTARGET_SEARCH;
    }

    /**
     * ファイル削除処理
     * @return
     */
    public String deleteMainFile() {
        // タイトル、ファイル名を削除
//--- 2006/03/23 タイトルは削除しないように変更
//        getDocumentRegistRegistFormDto().getEditEntity().setTitle("");
        getDocumentRegistRegistFormDto().getEditEntity().setFileName("");
        getDocumentRegistRegistFormDto().setUploadedMainFile(null);
        return null;
    }

    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile() {
        
        if (getDocumentRegistRegistFormDto().isCmbDelFlg1()) {
            // 添付ファイル１情報削除
            getUiBunshoInfo().setAttachFl1("");
            getUiBunshoInfo().setAttachName1("");
            //getRegistFormDto().setUploadedAttachFile1(null);
            getDocumentRegistRegistFormDto().setUploadFileNameTenpu1("");
            getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu1("");
            getDocumentRegistRegistFormDto().setCmbDelFlg1(false);
        }
        if (getDocumentRegistRegistFormDto().isCmbDelFlg2()) {
            // 添付ファイル２情報削除
            getUiBunshoInfo().setAttachFl2("");
            getUiBunshoInfo().setAttachName2("");
            //getRegistFormDto().setUploadedAttachFile2(null);
            getDocumentRegistRegistFormDto().setUploadFileNameTenpu2("");
            getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu2("");
            getDocumentRegistRegistFormDto().setCmbDelFlg2(false);
        }
        if (getDocumentRegistRegistFormDto().isCmbDelFlg3()) {
            // 添付ファイル３情報削除
            getUiBunshoInfo().setAttachFl3("");
            getUiBunshoInfo().setAttachName3("");
            //getRegistFormDto().setUploadedAttachFile3(null);
            getDocumentRegistRegistFormDto().setUploadFileNameTenpu3("");
            getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu3("");
            getDocumentRegistRegistFormDto().setCmbDelFlg3(false);
        }
        return null;
    }

    /**
     * 文書ファイルアップロード処理
     * @return
     */
    public String uploadMainFile() {
    	    	
        getDocumentRegistRegistFormDto().setUploadedFile(getDocumentRegistRegistFormDto().getUploadedMainFile());

        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getDocumentRegistRegistFormDto());
        getFileUploadAction().setUploadLogic(getUploadLogic());

        // ファイル選択チェック
        if (getDocumentRegistRegistFormDto().getUploadedFile() == null) {
            throw new NotNullException("ファイル", "uploadFile", null);
        }
        
        // ファイルアップロードアクション実行
        String ret = getFileUploadAction().uploadTemporary();
        
        // ファイル名称のサイズチェック
        LengthVerifier lent = new LengthVerifier( FILE_NAME_MAX_LENGTH );
        if (!lent.validate( getDocumentRegistRegistFormDto().getUploadFileName() ) ) {
        	throw new GenericCommentException("ファイル名は全角３０文字まで", "uploadFile", null);
        }
        
// 2006/03/22 xkhata 禁則文字対応
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if ( ! defJap.validate( getDocumentRegistRegistFormDto().getUploadFileName() ) ) {
        	throw new InvalidInputException("ファイル名");
        }
// end
        // ファイル存在チェック（空のファイルは不可）
        if (getDocumentRegistRegistFormDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFile", null);
        }
        
        // 共通アクションで設定された値を保持
        getDocumentRegistRegistFormDto().setUploadFileNameMain(getDocumentRegistRegistFormDto().getUploadFileName());
        getDocumentRegistRegistFormDto().setUploadTempFileNameMain(getDocumentRegistRegistFormDto().getTempFileName());
        getUiBunshoInfo().setFileName(getDocumentRegistRegistFormDto().getUploadFileName());
        
        return ret;
    }
    
    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile() {
    	
    	
// 2006/03/06 xkhata 添付ファイル関連のチェック
    	if ( getDocumentRegistRegistFormDto().getAttachName() == null ) {
    		throw new NotNullException("添付ファイルタイトル", "uploadFileTenpuTitle", null);
    	}
        
    	if ( getDocumentRegistRegistFormDto().getUploadedAttachFile() == null ) {
    		throw new NotNullException("添付ファイル", "uploadFileTenpu", null);
    	}
    	
    	LengthVerifier atachVeri = new LengthVerifier( TITLE_MAX_LENGTH );
    	if ( !atachVeri.validate( getDocumentRegistRegistFormDto().getAttachName()) ) {
    		throw new GenericCommentException("添付ファイルタイトルは全角４０文字まで", "uploadFileTenpuTitle", null);
    	}   	
    	
// 2006/03/22 xkhata 禁則文字対応
    	DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if (!defJap.validate( getDocumentRegistRegistFormDto().getAttachName()) ) {
        	throw new InvalidInputException("添付ファイルタイトル", "uploadFileTenpuTitle", null);
        }
// end        
        getDocumentRegistRegistFormDto().setUploadedFile(getDocumentRegistRegistFormDto().getUploadedAttachFile());
        
        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getDocumentRegistRegistFormDto());
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
        if (isNull(getDocumentRegistRegistFormDto().getEditEntity().getAttachName1())) {
            index = 1;
        }
        else if (isNull(getDocumentRegistRegistFormDto().getEditEntity().getAttachName2())) {
            index = 2;
        }
        else if (isNull(getDocumentRegistRegistFormDto().getEditEntity().getAttachName3())) {
            index = 3;
        }
        return index;
    }

    /*
     * 添付ファイル情報設定処理
     */
    private void setAttachFileInfo() {
        
    	LengthVerifier lent = new LengthVerifier( FILE_NAME_MAX_LENGTH );
        if (!lent.validate( getDocumentRegistRegistFormDto().getUploadFileName()) ) {
        	throw new GenericCommentException("ファイル名は全角３０文字まで", "uploadFileTenpu", null);
        }
        
// 2006/03/22 xkhata 禁則文字㈱対応
        DefaultJapaneseVerifier defJapanese = new DefaultJapaneseVerifier();
        if ( !defJapanese.validate( getDocumentRegistRegistFormDto().getUploadFileName()) ) {
        	throw new InvalidInputException("ファイル名", "uploadFileTenpu", null); 
        }
// end
        // ファイル存在チェック（空のファイルは不可）
        if (getDocumentRegistRegistFormDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFileTenpu", null);
        }
        
        switch (getEmptyAttachIndex()) {
            case 1:
                getDocumentRegistRegistFormDto().setUploadFileNameTenpu1(getDocumentRegistRegistFormDto().getUploadFileName());
                getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu1(getDocumentRegistRegistFormDto().getTempFileName());
                getUiBunshoInfo().setAttachFl1(getDocumentRegistRegistFormDto().getUploadFileName());
                getUiBunshoInfo().setAttachName1(getDocumentRegistRegistFormDto().getAttachName());
                break;
            case 2:
                getDocumentRegistRegistFormDto().setUploadFileNameTenpu2(getDocumentRegistRegistFormDto().getUploadFileName());
                getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu2(getDocumentRegistRegistFormDto().getTempFileName());
                getUiBunshoInfo().setAttachFl2(getDocumentRegistRegistFormDto().getUploadFileName());
                getUiBunshoInfo().setAttachName2(getDocumentRegistRegistFormDto().getAttachName());
                break;
            case 3:
                getDocumentRegistRegistFormDto().setUploadFileNameTenpu3(getDocumentRegistRegistFormDto().getUploadFileName());
                getDocumentRegistRegistFormDto().setUploadTempFileNameTenpu3(getDocumentRegistRegistFormDto().getTempFileName());
                getUiBunshoInfo().setAttachFl3(getDocumentRegistRegistFormDto().getUploadFileName());
                getUiBunshoInfo().setAttachName3(getDocumentRegistRegistFormDto().getAttachName());
                break;
            default:
                break;
        }
        getDocumentRegistRegistFormDto().setAttachName("");
        getDocumentRegistRegistFormDto().setUploadFileName("");
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
    
    private UIBunshoInfo getNewEditEntity(UIBunshoInfo entityFrom) {
        UIBunshoInfo entity = new UIBunshoInfo();
        entity.setAttachFl1(entityFrom.getAttachFl1());
        entity.setAttachFl2(entityFrom.getAttachFl2());
        entity.setAttachFl3(entityFrom.getAttachFl3());
        entity.setAttachName1(entityFrom.getAttachName1());
        entity.setAttachName2(entityFrom.getAttachName2());
        entity.setAttachName3(entityFrom.getAttachName3());
        entity.setCateId(entityFrom.getCateId());
        entity.setCateName(entityFrom.getCateName());
        entity.setDiscription(entityFrom.getDiscription());
        entity.setDownloadAttachIndex(entityFrom.getDownloadAttachIndex());
        entity.setFileName(entityFrom.getFileName());
        entity.setFirstPgm(entityFrom.getFirstPgm());
        entity.setFirstTmsp(entityFrom.getFirstTmsp());
        entity.setFirstUser(entityFrom.getFirstUser());
        entity.setLastPgm(entityFrom.getLastPgm());
        entity.setLastTmsp(entityFrom.getLastTmsp());
        entity.setLastUser(entityFrom.getLastUser());
        entity.setLimitKbn(entityFrom.getLimitKbn());
        entity.setLimitKbnName(entityFrom.getLimitKbnName());
        entity.setPubDateFrom(entityFrom.getPubDateFrom());
        entity.setPubDateTo(entityFrom.getPubDateTo());
        entity.setPubOrg(entityFrom.getPubOrg());
        entity.setPubOrgName(entityFrom.getPubOrgName());
        entity.setPubUser(entityFrom.getPubUser());
        entity.setRCompanyCd(entityFrom.getRCompanyCd());
        entity.setRegDate(entityFrom.getRegDate());
        entity.setSakujoFlg(entityFrom.getSakujoFlg());
        entity.setSeq(entityFrom.getSeq());
        entity.setSortSeq(entityFrom.getSortSeq());
        entity.setSubCateCount(entityFrom.getSubCateCount());
        entity.setSubCateId(entityFrom.getSubCateId());
        entity.setSubCateName(entityFrom.getSubCateName());
        entity.setTitle(entityFrom.getTitle());
        entity.setUserNameKj(entityFrom.getUserNameKj());
        entity.setBumonName(entityFrom.getBumonName());
        return entity;
    }

}