/*
 * 作成日: 2006/1/19
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.common.logic.GetCategoryLogic;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.control.BirdContext;
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
import jp.co.isid.mos.bird.inforegist.notificationregist.action.RegistAction;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.ConditionDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.RegistFormDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UITutatuInfo;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.CheckKanrenLogic;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.CheckTutatuLogic;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * 通達登録 編集画面アクション
 * 
 * 更新日：2011/04/15 ASAPC 「通達」から「お知らせ」へ画面名称変更対応
 * 2010/05/28 xkinu 公開開始日をyyyy/MM/ddの形式からyyyy/MM/dd('E')形式に変更
 * 2010/03/15 xkinu 公開開始日をシステム日付から未来日10日分を前日から未来日11日分表示に変更
 * @author m.onodera
 */
public class RegistActionImpl implements CommonAction, RegistAction {
    /* アクションID */
    public static final String init_ACTION_ID                 = "BIF002A07";
    public static final String regist_ACTION_ID               = "BIF002A08";
    public static final String cancel_ACTION_ID               = "BIF002A09";
    public static final String uploadMainFile_ACTION_ID       = "BIF002A10";
    public static final String deleteMainFile_ACTION_ID       = "BIF002A11";
    public static final String uploadAttachFile_ACTION_ID     = "BIF002A12";
    public static final String deleteAttachFile_ACTION_ID     = "BIF002A13";
    
    public static final String callForm_ACTION_ID             = "BIF002A14";
    public static final String callFormKanrenBunsho_ACTION_ID = "BIF002A15";
    public static final String deleteKanrenBunsho_ACTION_ID   = "BIF002A16";
    
    /* ビューID */
    private static final String VIEWID_CONDITION            = "BIF002V01";
    private static final String VIEWID_EDIT                 = "BIF002V02";
    private static final String VIEWID_CONFIRM              = "BIF002V03";
    private static final String VIEWID_PUBLICTARGET_SEARCH  = "BCO002V01";
    private static final String VIEWID_DOCUMENT_SEARCH      = "BCO001V01";

    // ファイル名最大文字数
    private final static int FILE_NAME_MAX_LENGTH = 60;
    
    /* DTO */
    private RegistFormDto notificationRegistFormDto;
    private ConditionDto notificationRegistConditionDto;
    private UITutatuInfo uiTutatuInfo;
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;
    private DocumentSearchDto documentSearchDto;
    
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /* ロジック */
    //【カテゴリ一覧の取得】
    private GetCategoryLogic getCategoryLogic;
    //【登録内容のチェック】
    private CheckTutatuLogic checkTutatuLogic;
    //【ファイルアップロード】
    private UploadLogic notificationRegistUploadLogic;
    //【関連文書の重複チェック】
    private CheckKanrenLogic checkKanrenLogic;
    
    /* アクション */
    //【ファイルアップロード】
    private FileUploadAction fileUploadAction;
    
    /**
     * 11日間
     */
    private static final int KOKAI_KAISI_MAX = 11;
    
    
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
     * @param notificationRegistConditionDto notificationRegistConditionDto を設定。
     */
    public void setNotificationRegistConditionDto(ConditionDto conditionDto) {
        this.notificationRegistConditionDto = conditionDto;
    }
    /**
     * @return notificationRegistConditionDto を戻します。
     */
    public ConditionDto getNotificationRegistConditionDto() {
        return notificationRegistConditionDto;
    }
    /**
     * @param notificationRegistFormDto notificationRegistFormDto を設定。
     */
    public void setNotificationRegistFormDto(RegistFormDto registFormDto) {
        this.notificationRegistFormDto = registFormDto;
    }
    /**
     * @return notificationRegistFormDto を戻します。
     */
    public RegistFormDto getNotificationRegistFormDto() {
        return notificationRegistFormDto;
    }
    /**
     * @param uiTutatuInfo uiTutatuInfo を設定。
     */
    public void setUiTutatuInfo(UITutatuInfo uiTutatuInfo) {
        this.uiTutatuInfo = uiTutatuInfo;
    }
    /**
     * @return uiTutatuInfo を戻します。
     */
    public UITutatuInfo getUiTutatuInfo() {
        return uiTutatuInfo;
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
     * 関連文書重複チェックロジック
     * @return checkKanrenLogic を戻します。
     */
    public CheckKanrenLogic getCheckKanrenLogic() {
        return checkKanrenLogic;
    }
    /**
     * 関連文書重複チェックロジック
     * @param checkKanrenLogic checkKanrenLogic を設定。
     */
    public void setCheckKanrenLogic(CheckKanrenLogic checkKanrenLogic) {
        this.checkKanrenLogic = checkKanrenLogic;
    }
    /**
     * 登録内容チェックロジック
     * @param checkTutatuLogic checkTutatuLogic を設定。
     */
    public void setCheckTutatuLogic(CheckTutatuLogic checkTutatuLogic) {
        this.checkTutatuLogic = checkTutatuLogic;
    }
    /**
     * 登録内容チェックロジック
     * @return checkTutatuLogic を戻します。
     */
    public CheckTutatuLogic getCheckTutatuLogic() {
        return checkTutatuLogic;
    }
    /**
     * カテゴリの取得（共通）ロジック
     * @return getCategoryLogic を戻します。
     */
    public GetCategoryLogic getGetCategoryLogic() {
        return getCategoryLogic;
    }
    /**
     * カテゴリの取得（共通）ロジック
     * @param getCategoryLogic getCategoryLogic を設定。
     */
    public void setGetCategoryLogic(GetCategoryLogic getCategoryLogic) {
        this.getCategoryLogic = getCategoryLogic;
    }
    /**
     * アップロードロジック
     * @return notificationRegistUploadLogic を戻します。
     */
    public UploadLogic getNotificationRegistUploadLogic() {
        return notificationRegistUploadLogic;
    }
    /**
     * アップロードロジック
     * @param notificationRegistUploadLogic notificationRegistUploadLogic を設定。
     */
    public void setNotificationRegistUploadLogic(UploadLogic uploadLogic) {
        this.notificationRegistUploadLogic = uploadLogic;
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
     * 初期処理
     * @return
     */
    public String initialize() {
        // 編集EntityをDTOへセット
        if (getNotificationRegistConditionDto().isFlgCondToReg()) {
            getNotificationRegistConditionDto().setFlgCondToReg(false);
            getNotificationRegistFormDto().clearUploadFile();
            UITutatuInfo editEntity = new UITutatuInfo();
            if(getNotificationRegistConditionDto().getRegMode()==ConditionDto.REG_MODE_INSERT){
	            // 新規モードの場合、初期値セット
	            setInitValue(editEntity);
            }
            else if(getNotificationRegistConditionDto().getRegMode()==ConditionDto.REG_MODE_UPDATE) {
	            int index = getNotificationRegistConditionDto().getSelectedListIndex();
	            if (index >= 0) {
	                UITutatuInfo entity = (UITutatuInfo) getNotificationRegistConditionDto().getListTutatu().get(index);
	                editEntity = entity.cloneEntity();
	            }
            }
            setUiTutatuInfo(editEntity);
            getNotificationRegistFormDto().setEditEntity(editEntity);

        }
        // 公開対象
        if (publicTargetSearchDto.isActionFlg()) {

            // 公開対象選択の結果反映
            try {
                PropertyUtils.copyProperties(publicTargetDto, publicTargetSearchDto);
            } catch (Exception e) {
                throw new FtlSystemException(InfoShu.TUTATU_NAME + "登録");
            }

            // アクションフラグクリア
            publicTargetSearchDto.setActionFlg(false);
        }
        // 関連文書情報
        getNotificationRegistFormDto().setListKanrenBunsho(getNotificationRegistConditionDto().getListKanrenBunsho());
        if (getDocumentSearchDto().isActionFlg()) {
            List listAddKanrenBunsho = getDocumentSearchDto().getDocumentList();
            List listMarge = getCheckKanrenLogic()
                                    .execute(getNotificationRegistFormDto().getListKanrenBunsho(),
                                           listAddKanrenBunsho);
            getNotificationRegistFormDto().setListKanrenBunsho(listMarge);
            getDocumentSearchDto().setActionFlg(false);
        }
        
        // カテゴリ情報取得
        makeCategoryPulldown();
        // 公開開始日のプルダウンを作成 
        makeKokaiStartDayPulldowon();
        
        return null;
    }
    
    /**
     * 登録処理
     * @return String
     */
    public String regist() {
        // 編集中EntityをDTOへセット
        getNotificationRegistFormDto().setEditEntity(getUiTutatuInfo());
        // ロジック【登録内容のチェック】
        getCheckTutatuLogic().checkTutatu(getNotificationRegistFormDto(), getPublicTargetDto());
        
        return VIEWID_CONFIRM;
    }

    /**
     * 取消  
     * @return
     */
    public String cancel() {
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
        List kanrenBunshoList = getNotificationRegistFormDto().getListKanrenBunsho();
        
        for ( int i = kanrenBunshoList.size() - 1; i > - 1; i-- ) {
            UIDocSearch uIDocSearch = (UIDocSearch)kanrenBunshoList.get(i);
            if ( uIDocSearch.isCheckedDoc() ) {
            	kanrenBunshoList.remove(i);
            }
        }

        return null;
    }


    /*
     * カテゴリ、サブカテゴリプルダウン用リスト作成処理
     */
    private void makeCategoryPulldown() throws ApplicationException {
        // ロジック【カテゴリ一覧の取得】
        List listCategory = getGetCategoryLogic().getCategory(ConditionActionImpl.INFO_SHU);
        getNotificationRegistFormDto().setListCategory(listCategory);
    }

    /**
     * 公開開始日のプルダウン
     * 
     * 2010/05/28 xkinu 公開開始日をyyyy/MM/ddの形式からyyyy/MM/dd('E')形式に変更
     * 2010/03/15 xkinu 公開開始日をシステム日付から未来日10日分を前日から未来日11日分表示に変更 
     */
    private void makeKokaiStartDayPulldowon(){
        DateFormatter formatter = new DateFormatter();
        //yyyy/MM/dd('E')のフォーマットを設定します。
        formatter.setFormatPattern(DateFormatter.PATTERN_SLASH_WEEK);
        List listkokaiKaisi = new ArrayList();
        Map mapkokaiKaisi = new HashMap();
        //システム日付前日の日付
        String lastDay = "";
        try {
        	//システム日付前日の日付を取得します。
        	lastDay = DateManager.getPrevDate(birdDateInfo.getSysDate(), 1);
        }
        catch (Exception ex) {
        	throw new FtlSystemException("編集画面初期処理", ex.toString(),ex);
        }
        for (int i = 0; i < KOKAI_KAISI_MAX; i++) {
            String yyyymmdd = "";
            try {
                yyyymmdd = DateManager.getNextDate(lastDay, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("編集画面初期処理", ex.toString(), ex);
            }
                                     
            SelectItem item = new SelectItem(yyyymmdd, formatter.format(yyyymmdd, true)); 
            listkokaiKaisi.add(item);
            mapkokaiKaisi.put(yyyymmdd, "");
        }
        //検索したデータがプルダウンに存在しない場合は追加
        String pubDate = getNotificationRegistFormDto().getEditEntity().getPubDate();
        if(pubDate != null &&  pubDate.trim().length() != 0l){
            if(!mapkokaiKaisi.containsKey(pubDate)){
                SelectItem item = new SelectItem(pubDate, formatter.format(pubDate, true)); 
                listkokaiKaisi.add(item);
            }
        }

        
        getNotificationRegistFormDto().setListKokaiKaisi(listkokaiKaisi);
    }
    
    /**
     * @return subDir を戻します。
     */
    public String getSubDir() {
        return BirdContext.getProperty("fileProperty", "filePathTutatu");
    }
//    /**
//     * @param subDir subDir を設定。
//     */
//    public void setSubDir(String subDir) {
//        this.subDir = subDir;
//    }

    /*
     * Null、空文字チェック
     */
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }
    
    /**
     * 初期値設定処理
     * @param entity
     */
    private void setInitValue(UITutatuInfo entity) {
        // キー情報
        entity.setRegDate(getBirdDateInfo().getSysDate());
        
        MstUser userInfo = getBirdUserInfo().getMstUser();
        entity.setPubUser(userInfo.getUser_id());
        entity.setUserNamekj(userInfo.getUser_name());
        entity.setPubOrg(userInfo.getBumonCd());
        entity.setPubOrgName(userInfo.getBumonName());
        entity.setFirstUser(userInfo.getUser_id());
        entity.setLastUser(userInfo.getUser_id());
        entity.setSakujoFlg("0");
        entity.setRCompanyCd(userInfo.getRCompanyCd());
//--- add 2006/03/23 発効日のデフォルト追加
        entity.setPubDate(getBirdDateInfo().getSysDate());
    }

    /**
     * 共通フォーム 公開対象を開く
     */
    public String callForm() throws Exception {

        getNotificationRegistFormDto().setEditEntity(getUiTutatuInfo());
        // 公開対象選択のパラメータ設定
        publicTargetSearchDto.setReferenceFlg(false);
        publicTargetSearchDto.setInitFlag(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_EDIT);
        publicTargetSearchDto.setInfoShu(publicTargetDto.getInfoShu());
        publicTargetSearchDto.setRegDate(publicTargetDto.getRegDate());
        publicTargetSearchDto.setSeq(publicTargetDto.getSeq());

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
//        getRegistFormDto().getEditEntity().setTitle("");
        getNotificationRegistFormDto().getEditEntity().setFileName("");
        getUiTutatuInfo().setFileName("");
        getNotificationRegistFormDto().setUploadedMainFile(null);
        return null;
    }

    /**
     * 添付ファイル削除処理
     * @return
     */
    public String deleteAttachFile() {
        
        if (getNotificationRegistFormDto().isCmbDelFlg1()) {
            // 添付ファイル１情報削除
            getUiTutatuInfo().setAttachFl1("");
            getUiTutatuInfo().setAttachName1("");
            //getRegistFormDto().setUploadedAttachFile1(null);
            getNotificationRegistFormDto().setUploadFileNameTenpu1("");
            getNotificationRegistFormDto().setUploadTempFileNameTenpu1("");
            getNotificationRegistFormDto().setCmbDelFlg1(false);
        }
        if (getNotificationRegistFormDto().isCmbDelFlg2()) {
            // 添付ファイル２情報削除
            getUiTutatuInfo().setAttachFl2("");
            getUiTutatuInfo().setAttachName2("");
            //getRegistFormDto().setUploadedAttachFile2(null);
            getNotificationRegistFormDto().setUploadFileNameTenpu2("");
            getNotificationRegistFormDto().setUploadTempFileNameTenpu2("");
            getNotificationRegistFormDto().setCmbDelFlg2(false);
        }
        if (getNotificationRegistFormDto().isCmbDelFlg3()) {
            // 添付ファイル３情報削除
            getUiTutatuInfo().setAttachFl3("");
            getUiTutatuInfo().setAttachName3("");
            //getRegistFormDto().setUploadedAttachFile3(null);
            getNotificationRegistFormDto().setUploadFileNameTenpu3("");
            getNotificationRegistFormDto().setUploadTempFileNameTenpu3("");
            getNotificationRegistFormDto().setCmbDelFlg3(false);
        }
        return null;
    }

    /**
     * 通達ファイルアップロード処理
     * @return
     */
    public String uploadMainFile() {
        getNotificationRegistFormDto().setUploadedFile(getNotificationRegistFormDto().getUploadedMainFile());
        
        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getNotificationRegistFormDto());
        getFileUploadAction().setUploadLogic(getNotificationRegistUploadLogic());

        // ファイル選択チェック
        if (getNotificationRegistFormDto().getUploadedFile() == null) {
            throw new NotNullException("ファイル", "uploadFile", null);
        }
        
        String file = getNotificationRegistFormDto().getUploadedFile().getName();
        String filename = file.substring(file.lastIndexOf(FileUtil.getFileSeparator()) + 1);
        
        // ファイル名称のサイズチェック
        LengthVerifier lent = new LengthVerifier( FILE_NAME_MAX_LENGTH );
        if (!lent.validate(filename)) {
            throw new GenericCommentException("ファイル名は全角３０文字まで","uploadFile", null);
        }
        
        // 禁則文字対応
        DefaultJapaneseVerifier defJap = new DefaultJapaneseVerifier();
        if (! defJap.validate(filename)) {
            throw new InvalidInputException("ファイル名","uploadFile", null);
        }
        
        // ファイル存在チェック（空のファイルは不可）
        if (getNotificationRegistFormDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル","uploadFile", null);
        }
        
        // ファイルアップロードアクション実行
        String ret = getFileUploadAction().uploadTemporary();

        // 共通アクションで設定された値を保持
        getNotificationRegistFormDto().setUploadFileNameMain(getNotificationRegistFormDto().getUploadFileName());
        getNotificationRegistFormDto().setUploadTempFileNameMain(getNotificationRegistFormDto().getTempFileName());
        getNotificationRegistFormDto().getEditEntity().setFileName(getNotificationRegistFormDto().getUploadFileName());
        getUiTutatuInfo().setFileName(getNotificationRegistFormDto().getUploadFileName());
        return ret;
    }
    
    /**
     * 添付ファイルアップロード処理
     * @return
     */
    public String uploadAttachFile() {
    	//項目チェック
    	validateUploadAttachFile();
    	
        getNotificationRegistFormDto().setUploadedFile(getNotificationRegistFormDto().getUploadedAttachFile());
        
        // アクションのプロパティ設定
        getFileUploadAction().setUploadDto(getNotificationRegistFormDto());
        getFileUploadAction().setUploadLogic(getNotificationRegistUploadLogic());

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
        if (isNull(getUiTutatuInfo().getAttachName1())) {
            index = 1;
        }
        else if (isNull(getUiTutatuInfo().getAttachName2())) {
            index = 2;
        }
        else if (isNull(getUiTutatuInfo().getAttachName3())) {
            index = 3;
        }
        return index;
    }

    /*
     * 添付ファイル情報設定処理
     */
    private void setAttachFileInfo() {

        LengthVerifier lent = new LengthVerifier( FILE_NAME_MAX_LENGTH );
        if (!lent.validate( getNotificationRegistFormDto().getUploadFileName()) ) {
            throw new GenericCommentException("ファイル名は全角３０文字まで", "uploadFileTenpu", null);
        }
        
        // 禁則文字㈱対応
        DefaultJapaneseVerifier defJapanese = new DefaultJapaneseVerifier();
        if ( !defJapanese.validate( getNotificationRegistFormDto().getUploadFileName()) ) {
            throw new InvalidInputException("ファイル名", "uploadFileTenpu", null); 
        }
        
        // ファイル存在チェック（空のファイルは不可）
        if (getNotificationRegistFormDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadFileTenpu", null);
        }

        switch (getEmptyAttachIndex()) {
            case 1:
                getNotificationRegistFormDto().setUploadFileNameTenpu1(getNotificationRegistFormDto().getUploadFileName());
                getNotificationRegistFormDto().setUploadTempFileNameTenpu1(getNotificationRegistFormDto().getTempFileName());
                getUiTutatuInfo().setAttachFl1(getNotificationRegistFormDto().getUploadFileName());
                getUiTutatuInfo().setAttachName1(getNotificationRegistFormDto().getAttachName());
                getNotificationRegistFormDto().getEditEntity().setAttachFl1(getNotificationRegistFormDto().getUploadFileName());
                getNotificationRegistFormDto().getEditEntity().setAttachName1(getNotificationRegistFormDto().getAttachName());

                break;
            case 2:
                getNotificationRegistFormDto().setUploadFileNameTenpu2(getNotificationRegistFormDto().getUploadFileName());
                getNotificationRegistFormDto().setUploadTempFileNameTenpu2(getNotificationRegistFormDto().getTempFileName());
                getUiTutatuInfo().setAttachFl2(getNotificationRegistFormDto().getUploadFileName());
                getUiTutatuInfo().setAttachName2(getNotificationRegistFormDto().getAttachName());
                getNotificationRegistFormDto().getEditEntity().setAttachFl2(getNotificationRegistFormDto().getUploadFileName());
                getNotificationRegistFormDto().getEditEntity().setAttachName2(getNotificationRegistFormDto().getAttachName());
            
                break;
            case 3:
                getNotificationRegistFormDto().setUploadFileNameTenpu3(getNotificationRegistFormDto().getUploadFileName());
                getNotificationRegistFormDto().setUploadTempFileNameTenpu3(getNotificationRegistFormDto().getTempFileName());
                getUiTutatuInfo().setAttachFl3(getNotificationRegistFormDto().getUploadFileName());
                getUiTutatuInfo().setAttachName3(getNotificationRegistFormDto().getAttachName());
                getNotificationRegistFormDto().getEditEntity().setAttachFl3(getNotificationRegistFormDto().getUploadFileName());
                getNotificationRegistFormDto().getEditEntity().setAttachName3(getNotificationRegistFormDto().getAttachName());
                break;
            default:
                break;
        }
        getNotificationRegistFormDto().setAttachName("");
        getNotificationRegistFormDto().setUploadFileName("");
    }
    /**
     * 添付ファイルアップロード時の項目チェック処理
     * 
     * @throws ApplicationException
     */
    private void validateUploadAttachFile() throws ApplicationException 
	{
    	//添付ファイルタイトル
        String attachName = notificationRegistFormDto.getAttachName();
        //添付ファイル
        UploadedFile upFile = notificationRegistFormDto.getUploadedAttachFile();

        //添付ファイルタイトルの入力が無い場合
        if (isNull(attachName)) {
    		throw new NotNullException("添付ファイルタイトル", "attachName", null);
        }
        if (upFile == null) {
            throw new NotNullException("添付ファイル", "uploadFileTenpu", null);
        }
        if (!isNull(attachName)) {
        	//添付ファイルタイトル
        	if (attachName.getBytes().length > 80) {
        		throw new InvalidInputException("添付ファイルタイトル", "attachName", null);
        	}
// add start xkhata 20070627
            DefaultJapaneseVerifier dej = new DefaultJapaneseVerifier();
            if ( !dej.validate(attachName)) {
                throw new InvalidInputException("添付ファイルタイトル", "attachName", null);
            }
// add end xkhata
        	//添付ファイルタイトルが入力済みで、
	        if (upFile == null) {
	            throw new NotNullException("ファイル", "uploadFileTenpu", null);
	        }
        }
    }

}