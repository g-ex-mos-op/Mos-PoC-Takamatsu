package jp.co.isid.mos.bird.inforegist.documentregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * 文書登録編集画面DTO
 * @author xnkusama
 */
public class DocumentRegistRegistFormDto implements UploadDto, DownloadDto {

    
/* ↓↓↓【DB関連情報】↓↓↓ */
    /* 登録日 */
    //private String regDate;
    /* シーケンス番号 */
    //private String seq;
    /* 関連文書 */
    private List listKanrenBunsho;
    
/* ↓↓↓【画面制御用】↓↓↓ */
    /* タブ （選択中のタブ １：文書内容  ２：ファイル内容）*/
    private int tabIndex = 1;
    /* 関連文書ダウンロードIndex */
    private int downloadKanrenIndex;
    /* 掲載期間From */
    private List listFrom;
    /* 掲載期間To */
    private List listTo;
    /* 情報制限 */
    private List listLimitKbn;
    /* カテゴリ */
    private List listCategory;
    /* サブカテゴリ */
    private List listSubCategory;
    /* 編集中Entity */
    private UIBunshoInfo editEntity;
    /* 編集モード */
    private int regMode;

/* ↓↓↓アップロード関連↓↓↓ */    
    // アップロードIndex
    private int uploadIndex;
    // テンポラリファイル名
    private String tempFileName;
    // 共通アップロードアクション用プロパティ
    private UploadedFile uploadedFile;
    private String uploadFileName;
    
    // アップロードファイル（情報本体のファイル）
    private UploadedFile uploadedMainFile;
    private String uploadTempFileNameMain;
    private String uploadFileNameMain;
//    // アップロードファイル（添付ファイル１）
//    private UploadedFile uploadedAttachFile1;
    private String uploadTempFileNameTenpu1;
    private String uploadFileNameTenpu1;
//    // アップロードファイル（添付ファイル２）
//    private UploadedFile uploadedAttachFile2;
    private String uploadTempFileNameTenpu2;
    private String uploadFileNameTenpu2;
//    // アップロードファイル（添付ファイル３）
//    private UploadedFile uploadedAttachFile3;
    private String uploadTempFileNameTenpu3;
    private String uploadFileNameTenpu3;

    // 添付ファイルアップロード用
    private String attachName;
    private UploadedFile uploadedAttachFile;
    // 添付ファイル 削除用コンボ
    private boolean cmbDelFlg1;
    private boolean cmbDelFlg2;
    private boolean cmbDelFlg3;
    
    /**
     * @param tabIndex tabIndex を設定。
     */
    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
    /**
     * @return tabIndex を戻します。
     */
    public int getTabIndex() {
        return tabIndex;
    }
    /**
     * @return listFrom を戻します。
     */
    public List getListFrom() {
        return listFrom;
    }
    /**
     * @param listFrom listFrom を設定。
     */
    public void setListFrom(List listFrom) {
        this.listFrom = listFrom;
    }
    /**
     * @return listTo を戻します。
     */
    public List getListTo() {
        return listTo;
    }
    /**
     * @param listTo listTo を設定。
     */
    public void setListTo(List listTo) {
        this.listTo = listTo;
    }
    /**
     * @param listLimitKbn listLimitKbn を設定。
     */
    public void setListLimitKbn(List listLimitKbn) {
        this.listLimitKbn = listLimitKbn;
    }
    /**
     * @return listLimitKbn を戻します。
     */
    public List getListLimitKbn() {
        return listLimitKbn;
    }
    /**
     * @return listCategory を戻します。
     */
    public List getListCategory() {
        return listCategory;
    }
    /**
     * @param listCategory listCategory を設定。
     */
    public void setListCategory(List listCategory) {
        this.listCategory = listCategory;
    }
    /**
     * @return listSubCategory を戻します。
     */
    public List getListSubCategory() {
        return listSubCategory;
    }
    /**
     * @param listSubCategory listSubCategory を設定。
     */
    public void setListSubCategory(List listSubCategory) {
        this.listSubCategory = listSubCategory;
    }
    /**
     * @param editEntity editEntity を設定。
     */
    public void setEditEntity(UIBunshoInfo editEntity) {
        this.editEntity = editEntity;
    }
    /**
     * @return editEntity を戻します。
     */
    public UIBunshoInfo getEditEntity() {
        return editEntity;
    }
    /**
     * @param regMode regMode を設定。
     */
    public void setRegMode(int regMode) {
        this.regMode = regMode;
    }
    /**
     * @return regMode を戻します。
     */
    public int getRegMode() {
        return regMode;
    }

    /**
     * アップロードオブジェクト取得処理
     * @return UploadedFiel
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
    /**
     * アップロードオブジェクト設定処理
     */
    public void setUploadedFile(UploadedFile uploadFile) {
        this.uploadedFile = uploadFile;
    }
    
    // テンポラリファイル名取得・設定処理
    public String getTempFileName() {
        return this.tempFileName;
    }
    public void setTempFileName(final String tempFileName) {
        this.tempFileName = tempFileName;
    }

    /**
     * アップロードファイル名取得処理
     */
    public String getUploadFileName() {
        return this.uploadFileName;
    }
    /**
     * アップロードファイル名設定処理
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * @return uploadedMainFile を戻します。
     */
    public UploadedFile getUploadedMainFile() {
        return uploadedMainFile;
    }
    /**
     * @param uploadedMainFile uploadedMainFile を設定。
     */
    public void setUploadedMainFile(UploadedFile uploadedMainFile) {
        this.uploadedMainFile = uploadedMainFile;
    }
    /**
     * @return uploadIndex を戻します。
     */
    public int getUploadIndex() {
        return uploadIndex;
    }
    /**
     * @param uploadIndex uploadIndex を設定。
     */
    public void setUploadIndex(int uploadIndex) {
        this.uploadIndex = uploadIndex;
    }
    /**
     * @return uploadFileNameMain を戻します。
     */
    public String getUploadFileNameMain() {
        return uploadFileNameMain;
    }
    /**
     * @param uploadFileNameMain uploadFileNameMain を設定。
     */
    public void setUploadFileNameMain(String uploadFileNameMain) {
        this.uploadFileNameMain = uploadFileNameMain;
    }
    /**
     * @return uploadFileNameTenpu1 を戻します。
     */
    public String getUploadFileNameTenpu1() {
        return uploadFileNameTenpu1;
    }
    /**
     * @param uploadFileNameTenpu1 uploadFileNameTenpu1 を設定。
     */
    public void setUploadFileNameTenpu1(String uploadFileNameTenpu1) {
        this.uploadFileNameTenpu1 = uploadFileNameTenpu1;
    }
    /**
     * @return uploadFileNameTenpu2 を戻します。
     */
    public String getUploadFileNameTenpu2() {
        return uploadFileNameTenpu2;
    }
    /**
     * @param uploadFileNameTenpu2 uploadFileNameTenpu2 を設定。
     */
    public void setUploadFileNameTenpu2(String uploadFileNameTenpu2) {
        this.uploadFileNameTenpu2 = uploadFileNameTenpu2;
    }
    /**
     * @return uploadFileNameTenpu3 を戻します。
     */
    public String getUploadFileNameTenpu3() {
        return uploadFileNameTenpu3;
    }
    /**
     * @param uploadFileNameTenpu3 uploadFileNameTenpu3 を設定。
     */
    public void setUploadFileNameTenpu3(String uploadFileNameTenpu3) {
        this.uploadFileNameTenpu3 = uploadFileNameTenpu3;
    }
    /**
     * @return uploadTempFileNameMain を戻します。
     */
    public String getUploadTempFileNameMain() {
        return uploadTempFileNameMain;
    }
    /**
     * @param uploadTempFileNameMain uploadTempFileNameMain を設定。
     */
    public void setUploadTempFileNameMain(String uploadTempFileNameMain) {
        this.uploadTempFileNameMain = uploadTempFileNameMain;
    }
    /**
     * @return uploadTempFileNameTenpu1 を戻します。
     */
    public String getUploadTempFileNameTenpu1() {
        return uploadTempFileNameTenpu1;
    }
    /**
     * @param uploadTempFileNameTenpu1 uploadTempFileNameTenpu1 を設定。
     */
    public void setUploadTempFileNameTenpu1(String uploadTempFileNameTenpu1) {
        this.uploadTempFileNameTenpu1 = uploadTempFileNameTenpu1;
    }
    /**
     * @return uploadTempFileNameTenpu2 を戻します。
     */
    public String getUploadTempFileNameTenpu2() {
        return uploadTempFileNameTenpu2;
    }
    /**
     * @param uploadTempFileNameTenpu2 uploadTempFileNameTenpu2 を設定。
     */
    public void setUploadTempFileNameTenpu2(String uploadTempFileNameTenpu2) {
        this.uploadTempFileNameTenpu2 = uploadTempFileNameTenpu2;
    }
    /**
     * @return uploadTempFileNameTenpu3 を戻します。
     */
    public String getUploadTempFileNameTenpu3() {
        return uploadTempFileNameTenpu3;
    }
    /**
     * @param uploadTempFileNameTenpu3 uploadTempFileNameTenpu3 を設定。
     */
    public void setUploadTempFileNameTenpu3(String uploadTempFileNameTenpu3) {
        this.uploadTempFileNameTenpu3 = uploadTempFileNameTenpu3;
    }
    /**
     * @return cmbDelFlg1 を戻します。
     */
    public boolean isCmbDelFlg1() {
        return cmbDelFlg1;
    }
    /**
     * @param cmbDelFlg1 cmbDelFlg1 を設定。
     */
    public void setCmbDelFlg1(boolean cmbDelFlg1) {
        this.cmbDelFlg1 = cmbDelFlg1;
    }
    /**
     * @return cmbDelFlg2 を戻します。
     */
    public boolean isCmbDelFlg2() {
        return cmbDelFlg2;
    }
    /**
     * @param cmbDelFlg2 cmbDelFlg2 を設定。
     */
    public void setCmbDelFlg2(boolean cmbDelFlg2) {
        this.cmbDelFlg2 = cmbDelFlg2;
    }
    /**
     * @return cmbDelFlg3 を戻します。
     */
    public boolean isCmbDelFlg3() {
        return cmbDelFlg3;
    }
    /**
     * @param cmbDelFlg3 cmbDelFlg3 を設定。
     */
    public void setCmbDelFlg3(boolean cmbDelFlg3) {
        this.cmbDelFlg3 = cmbDelFlg3;
    }
    
    
    public boolean isAddMainFile() {
        return isNull(getEditEntity().getFileName()) ? true : false;
    }

    public boolean isAddAttachFile() {
        if (isNull(getEditEntity().getAttachFl1())
                || isNull(getEditEntity().getAttachFl2())
                || isNull(getEditEntity().getAttachFl3()))
        {
            return true;
        }
        return false;
    }
    /*
     * Null、空文字チェック
     */
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }
    
    /**
     * @return attachName を戻します。
     */
    public String getAttachName() {
        return attachName;
    }
    /**
     * @param attachName attachName を設定。
     */
    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }
    /**
     * @return uploadedAttachFile を戻します。
     */
    public UploadedFile getUploadedAttachFile() {
        return uploadedAttachFile;
    }
    /**
     * @param uploadedAttachFile uploadedAttachFile を設定。
     */
    public void setUploadedAttachFile(UploadedFile uploadedAttachFile) {
        this.uploadedAttachFile = uploadedAttachFile;
    }
    
    
    public void clearDto() {
        setEditEntity(null);
    }
    /**
     * @return listKanrenBunsho を戻します。
     */
    public List getListKanrenBunsho() {
        return listKanrenBunsho;
    }
    /**
     * @param listKanrenBunsho listKanrenBunsho を設定。
     */
    public void setListKanrenBunsho(List listKanrenBunsho) {
        this.listKanrenBunsho = listKanrenBunsho;
    }
    
    public int getListKanrenBunshoSize() {
        int size = 0;
        if (getListKanrenBunsho() != null) {
            size = getListKanrenBunsho().size();
        }
        return size;
    }
    /**
     * @return downloadKanrenIndex を戻します。
     */
    public int getDownloadKanrenIndex() {
        return downloadKanrenIndex;
    }
    /**
     * @param downloadKanrenIndex downloadKanrenIndex を設定。
     */
    public void setDownloadKanrenIndex(int downloadKanrenIndex) {
        this.downloadKanrenIndex = downloadKanrenIndex;
    }
    
    /**
     * 添付ファイル情報のクリア
     */
    public void clearUploadFile(){
        setUploadTempFileNameMain("");
        setUploadFileNameMain("");
        
        setUploadFileNameTenpu1("");
        setUploadTempFileNameTenpu1("");
        setCmbDelFlg1(false);

        setUploadFileNameTenpu2("");
        setUploadTempFileNameTenpu2("");
        setCmbDelFlg2(false);
        
        setUploadFileNameTenpu3("");
        setUploadTempFileNameTenpu3("");
        setCmbDelFlg3(false);
    }
    
    //ファイル情報クリア処理
    public void clearFileName(){
        setUploadTempFileNameMain(null);
        setUploadTempFileNameTenpu1(null);
        setUploadTempFileNameTenpu2(null);
        setUploadTempFileNameTenpu3(null);
    }
}
