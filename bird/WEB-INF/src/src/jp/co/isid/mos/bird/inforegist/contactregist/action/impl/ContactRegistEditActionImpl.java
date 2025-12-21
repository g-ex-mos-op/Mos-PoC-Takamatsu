/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.commonform.documentsearch.dto.DocumentSearchDto;
import jp.co.isid.mos.bird.commonform.publictargetsearch.dto.PublicTargetSearchDto;
import jp.co.isid.mos.bird.framework.action.FileUploadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.FileUtil;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;
import jp.co.isid.mos.bird.inforegist.contactregist.action.ContactRegistEditAction;
import jp.co.isid.mos.bird.inforegist.contactregist.dto.ContactRegistDto;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.CheckRenrakuKanrenBunshoLogic;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.CheckRenrakuLogic;

/**
 * @author xyuchida
 *
 */
public class ContactRegistEditActionImpl implements ContactRegistEditAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BIF001A06";
    public static final String confirm_ACTION_ID = "BIF001A07";
    public static final String cancel_ACTION_ID = "BIF001A08";
    public static final String callForm_ACTION_ID = "BIF001A09";
    public static final String upload_ACTION_ID = "BIF001A10";
    public static final String delete_ACTION_ID = "BIF001A11";

    private static final String VIEWID_SEARCH = "BIF001V01";
    private static final String VIEWID_EDIT = "BIF001V02";
    private static final String VIEWID_CONFIRM = "BIF001V03";
    
    //公開対象選択
    private static final String VIEWID_PUBLICTARGET_SEARCH = "BCO002V01";
    //関連文書選択
    private static final String VIEWID_DOC_SEARCH = "BCO001V01";
    
    private static final int MAX_ATTACH_FILE = 3;

    // ファイル名最大文字数
    private final static int FILE_NAME_MAX_LENGTH = 60;
    
    private ContactRegistDto contactRegistDto;
    //公開対象ＤＴＯ
    private PublicTargetDto publicTargetDto;
    private PublicTargetSearchDto publicTargetSearchDto;
    //関連文書ＤＴＯ
    private DocumentSearchDto documentSearchDto;

    //ロジック
    private CheckRenrakuLogic checkRenrakuLogic;
    private FileUploadAction fileUploadAction;
    private CheckRenrakuKanrenBunshoLogic checkRenrakuKanrenBunshoLogic; 
    
    //公開開始日
    private static final int KOKAI_KAISI_MAX = 10;

    /**
     * @return contactRegistDto を戻します。
     */
    public ContactRegistDto getContactRegistDto() {
        return contactRegistDto;
    }

    /**
     * @param contactRegistDto contactRegistDto を設定。
     */
    public void setContactRegistDto(ContactRegistDto contactRegistDto) {
        this.contactRegistDto = contactRegistDto;
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

    /**
     * @return checkRenrakuLogic を戻します。
     */
    public CheckRenrakuLogic getCheckRenrakuLogic() {
        return checkRenrakuLogic;
    }

    /**
     * @param checkRenrakuLogic checkRenrakuLogic を設定。
     */
    public void setCheckRenrakuLogic(CheckRenrakuLogic checkRenrakuLogic) {
        this.checkRenrakuLogic = checkRenrakuLogic;
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

    public String initialize() {

        if (publicTargetSearchDto.isActionFlg()) {

            // 公開対象選択の結果反映
            publicTargetDto.getListTrnControlCompany().clear();
            publicTargetDto.getListTrnControlCompany().addAll(publicTargetSearchDto.getListTrnControlCompany());
            publicTargetDto.getListTrnControlShozoku().clear();
            publicTargetDto.getListTrnControlShozoku().addAll(publicTargetSearchDto.getListTrnControlShozoku());
            publicTargetDto.getListTrnControlGyotai().clear();
            publicTargetDto.getListTrnControlGyotai().addAll(publicTargetSearchDto.getListTrnControlGyotai());
            publicTargetDto.getListTrnControlGyotaiKobetu().clear();
            publicTargetDto.getListTrnControlGyotaiKobetu().addAll(publicTargetSearchDto.getListTrnControlGyotaiKobetu());
            publicTargetDto.getListTrnControlGyotaiTenpo().clear();
            publicTargetDto.getListTrnControlGyotaiTenpo().addAll(publicTargetSearchDto.getListTrnControlGyotaiTenpo());

            // アクションフラグクリア
            publicTargetSearchDto.setActionFlg(false);
        }
        
        // 関連文書情報
        if (getDocumentSearchDto().isActionFlg()) {
            List listAddKanrenBunsho = getDocumentSearchDto().getDocumentList();
            List listMarge = getCheckRenrakuKanrenBunshoLogic()
                                    .execute(getContactRegistDto().getListKanrenBunsho(),
                                           listAddKanrenBunsho);
            getContactRegistDto().setListKanrenBunsho(listMarge);
            getDocumentSearchDto().setActionFlg(false);
        }
        
        //公開開始のプルダウン作成
        makeKokaiStartDayPulldowon();
        
        // 自画面へ遷移
        return null;
    }

    public String confirm() {

        // 入力値チェック
        checkRenrakuLogic.execute(contactRegistDto, publicTargetDto);

        // 確認画面へ遷移
        return VIEWID_CONFIRM;
    }

    public String cancel() {

        // 検索画面へ遷移
        return VIEWID_SEARCH;
    }

    public String callForm() {

        // 公開対象選択のパラメータ設定
        publicTargetSearchDto.setReferenceFlg(false);
        publicTargetSearchDto.setInitFlag(true);
        publicTargetSearchDto.setNavigationCase(VIEWID_EDIT);
        publicTargetSearchDto.setInfoShu(publicTargetDto.getInfoShu());
        publicTargetSearchDto.setRegDate(publicTargetDto.getRegDate());
        publicTargetSearchDto.setSeq(publicTargetDto.getSeq());
        publicTargetSearchDto.getListTrnControlCompany().clear();
        publicTargetSearchDto.getListTrnControlCompany().addAll(publicTargetDto.getListTrnControlCompany());
        publicTargetSearchDto.getListTrnControlShozoku().clear();
        publicTargetSearchDto.getListTrnControlShozoku().addAll(publicTargetDto.getListTrnControlShozoku());
        publicTargetSearchDto.getListTrnControlGyotai().clear();
        publicTargetSearchDto.getListTrnControlGyotai().addAll(publicTargetDto.getListTrnControlGyotai());
        publicTargetSearchDto.getListTrnControlGyotaiKobetu().clear();
        publicTargetSearchDto.getListTrnControlGyotaiKobetu().addAll(publicTargetDto.getListTrnControlGyotaiKobetu());
        publicTargetSearchDto.getListTrnControlGyotaiTenpo().clear();
        publicTargetSearchDto.getListTrnControlGyotaiTenpo().addAll(publicTargetDto.getListTrnControlGyotaiTenpo());

        // 公開対象選択へ遷移
        return VIEWID_PUBLICTARGET_SEARCH;
    }

    public String upload() {

        // バリデーション
        validate();

        // 未設定のインデックスを設定
        getContactRegistDto().setIndex(getContactRegistDto().getBlankIndex());
        // テンポラリアップロード
        getFileUploadAction().uploadTemporary();

        // 今回入力タイトル設定
        getContactRegistDto().setAttachFileTitle(getContactRegistDto().getInputTitle());
        getContactRegistDto().setInputTitle(null);

        // 自画面へ遷移
        return null;
    }

    public String delete() {

        List deleteFileNameList = getContactRegistDto().getDeleteFileNameList();
        if (getContactRegistDto().isCheckAttachFile1()) {
            getContactRegistDto().setIndex(1);
            deleteFileNameList.add(getContactRegistDto().getUploadFileName());
            getContactRegistDto().setCheckAttachFile1(false);
            getContactRegistDto().setAttachName1(null);
            getContactRegistDto().setAttachFl1(null);
            getContactRegistDto().setTempFileName1(null);
        }
        if (getContactRegistDto().isCheckAttachFile2()) {
            getContactRegistDto().setIndex(2);
            deleteFileNameList.add(getContactRegistDto().getUploadFileName());
            getContactRegistDto().setCheckAttachFile2(false);
            getContactRegistDto().setAttachName2(null);
            getContactRegistDto().setAttachFl2(null);
            getContactRegistDto().setTempFileName2(null);
        }
        if (getContactRegistDto().isCheckAttachFile3()) {
            getContactRegistDto().setIndex(3);
            deleteFileNameList.add(getContactRegistDto().getUploadFileName());
            getContactRegistDto().setCheckAttachFile3(false);
            getContactRegistDto().setAttachName3(null);
            getContactRegistDto().setAttachFl3(null);
            getContactRegistDto().setTempFileName3(null);
        }

        // 自画面へ遷移
        return null;
    }

    private void validate() {

        // アップロード数チェック
        if (getContactRegistDto().getBlankIndex() > MAX_ATTACH_FILE) {
            throw new CannotExecuteWithReasonException("上限数を超える", "アップロード",  "uploadFileTitle", null);
        }

        // タイトル必須チェック
        String inputTitle = getContactRegistDto().getInputTitle();
        if (inputTitle == null || inputTitle.length() == 0) {
            throw new NotNullException("添付ファイルタイトル", "uploadFileTitle", null);
        }
        
        // タイトル文字数チェック
        if (inputTitle.getBytes().length > 80) {
            throw new InvalidInputException("添付ファイルタイトル", "uploadFileTitle", null);
        }
//      add start xkhata 20070627
        DefaultJapaneseVerifier dj = new DefaultJapaneseVerifier();
        if (!dj.validate(inputTitle) ) {
            throw new InvalidInputException("添付ファイルタイトル", "uploadFileTitle", null);
        }
// add end

        // アップロードファイルチェック
        if (getContactRegistDto().getUploadedFile() == null) {
            throw new InvalidInputException("添付ファイル", "uploadTenpuFile", null);
        }

        String file = getContactRegistDto().getUploadedFile().getName();
        String filename = file.substring(file.lastIndexOf(FileUtil.getFileSeparator()) + 1);
        LengthVerifier lent = new LengthVerifier( FILE_NAME_MAX_LENGTH );
        if (!lent.validate(filename)) {
            throw new GenericCommentException("ファイル名は全角３０文字まで", "uploadTenpuFile", null);
        }
        
        // 禁則文字㈱対応
        DefaultJapaneseVerifier defJapanese = new DefaultJapaneseVerifier();
        if ( !defJapanese.validate(filename)) {
            throw new InvalidInputException("ファイル名", "uploadTenpuFile", null); 
        }
        // ファイル存在チェック（空のファイルは不可）
        if (getContactRegistDto().getUploadedFile().getSize() <= 0) {
            throw new GenericCommentException("ファイルが存在しないか、空のファイル", "uploadTenpuFile", null);
        }
        
    }
    
    /**
     * 公開開始日のプルダウンの作成
     */
    private void makeKokaiStartDayPulldowon(){
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        
        DateFormatter formatter = new DateFormatter();
        
        List listkokaiKaisi = new ArrayList();
        Map mapkokaiKaisi = new HashMap();
                
        for (int i = 0; i < KOKAI_KAISI_MAX; i++) {
            String yyyymmdd = "";
            try {
                yyyymmdd = DateManager.getNextDate(birdDateInfo.getSysDate(), i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("編集画面初期処理", ex.toString(), ex);
            }
                                     
            SelectItem item = new SelectItem(yyyymmdd, formatter.format(yyyymmdd, true)); 
            listkokaiKaisi.add(item);
            mapkokaiKaisi.put(yyyymmdd, "");
        }
        //検索したデータがプルダウンに存在しない場合は追加
        String pubDate = getContactRegistDto().getPubDate();
        if(pubDate != null &&  pubDate.trim().length() != 0l){
            if(!mapkokaiKaisi.containsKey(pubDate)){
                SelectItem item = new SelectItem(pubDate, formatter.format(pubDate, true)); 
                listkokaiKaisi.add(item);
            }
        }
        getContactRegistDto().setListKokaiKaisi(listkokaiKaisi);
    }

    /**
     * 関連文書ＤＴＯを取得します。
     * @return 関連文書ＤＴＯ 
     */
    public DocumentSearchDto getDocumentSearchDto() {
        return documentSearchDto;
    }

    /**
     * 関連文書ＤＴＯを設定します。
     * @param 関連文書ＤＴＯ
     */
    public void setDocumentSearchDto(DocumentSearchDto documentSearchDto) {
        this.documentSearchDto = documentSearchDto;
    }

    /**
     * 関連文書の重複チェックロジックを取得します。
     * @return 関連文書の重複チェックロジック 
     */
    public CheckRenrakuKanrenBunshoLogic getCheckRenrakuKanrenBunshoLogic() {
        return checkRenrakuKanrenBunshoLogic;
    }

    /**
     * 関連文書の重複チェックロジックを設定します。
     * @param 関連文書の重複チェックロジック
     */
    public void setCheckRenrakuKanrenBunshoLogic(
            CheckRenrakuKanrenBunshoLogic checkRenrakuKanrenBunshoLogic) {
        this.checkRenrakuKanrenBunshoLogic = checkRenrakuKanrenBunshoLogic;
    }
    
    /**
     * 関連文書選択
     */
    public String callFormKanrenBunsho() {
        // 関連文書選択のパラメータ設定
        getDocumentSearchDto().setInitFlg(true);
        getDocumentSearchDto().setNavigationCase(VIEWID_EDIT);

        return VIEWID_DOC_SEARCH;
    }
    
    /**
     * 関連文書の削除
     */
    public String deleteKanrenBunsho() {
        List kanrenBunshoList = getContactRegistDto().getListKanrenBunsho();
        
        for ( int i = kanrenBunshoList.size() - 1; i > - 1; i-- ) {
            UIDocSearch uIDocSearch = (UIDocSearch)kanrenBunshoList.get(i);
            if ( uIDocSearch.isCheckedDoc() ) {
                kanrenBunshoList.remove(i);
            }
        }
        return null;
    }

    
    
    
}
