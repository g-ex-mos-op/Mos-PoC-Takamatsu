package jp.co.isid.mos.bird.office.formregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * フォーム登録編集画面DTO
 * @author xytamura
 */
public class FormRegistFormDto implements UploadDto, DownloadDto {


    /* 関連文書 */
    private List listKanrenBunsho;

//    /* ↓↓↓【画面制御用】↓↓↓ */
//    /* タブ （選択中のタブ １：文書内容 ２：ファイル内容） */
//    private int tabIndex = 1;

    /* 関連文書ダウンロードIndex */
    private int downloadKanrenIndex;

    /* 掲載期間From */
    private List listFrom;

    /* 掲載期間To */
    private List listTo;

//    /* 情報制限 */
//    private List listLimitKbn;

    /* カテゴリ */
    private List listCategory;

    /* サブカテゴリ */
    private List listSubCategory;

    /* 編集中Entity */
    private UIFormInfo editEntity;

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

    
//    /**
//     * タブのインデックスを設定します。
//     * @param タブのインデックス
//     */
//    public void setTabIndex(int tabIndex) {
//        this.tabIndex = tabIndex;
//    }
//
//    /**
//     * タブのインデックスを取得します。
//     * @return タブのインデックス
//     */
//    public int getTabIndex() {
//        return tabIndex;
//    }

    /**
     * フォーム情報を取得します。
     * @return フォーム情報
     */
    public List getListFrom() {
        return listFrom;
    }

    /**
     * フォーム情報を設定します。
     * @param フォーム情報
     */
    public void setListFrom(List listFrom) {
        this.listFrom = listFrom;
    }

    /**
     * 掲載期間ＴＯを設定します。
     * @return 掲載期間ＴＯ
     */
    public List getListTo() {
        return listTo;
    }

    /**
     * 掲載期間ＴＯを取得します。
     * @param 掲載期間ＴＯ
     */
    public void setListTo(List listTo) {
        this.listTo = listTo;
    }

//    /**
//     * 情報制限区分を設定します。
//     * @param 情報制限区分
//     */
//    public void setListLimitKbn(List listLimitKbn) {
//        this.listLimitKbn = listLimitKbn;
//    }
//
//    /**
//     * 情報制限区分を取得します。
//     * @return 情報制限区分
//     */
//    public List getListLimitKbn() {
//        return listLimitKbn;
//    }

    /**
     * カテゴリ一覧を取得します。
     * @return カテゴリ一覧
     */
    public List getListCategory() {
        return listCategory;
    }

    /**
     * カテゴリ一覧を設定します。
     * @param カテゴリ一覧
     */
    public void setListCategory(List listCategory) {
        this.listCategory = listCategory;
    }

    /**
     * サブカテゴリ一覧を取得します。
     * @return サブカテゴリ一覧
     */
    public List getListSubCategory() {
        return listSubCategory;
    }

    /**
     * サブカテゴリ一覧を設定します。
     * @param サブカテゴリ一覧
     */
    public void setListSubCategory(List listSubCategory) {
        this.listSubCategory = listSubCategory;
    }

    /**
     * 編集対象のフォーム一覧エンティティを設定します。
     * @param 編集対象のフォーム一覧エンティティ
     */
    public void setEditEntity(UIFormInfo editEntity) {
        this.editEntity = editEntity;
    }

    /**
     * 編集対象のフォーム一覧エンティティを取得します。
     * @return 編集対象のフォーム一覧エンティティ
     */
    public UIFormInfo getEditEntity() {
        return editEntity;
    }

    /**
     * 登録モードを設定します。
     * @param 登録モード
     */
    public void setRegMode(int regMode) {
        this.regMode = regMode;
    }

    /**
     * 登録モードを設定します。
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

    /**
     * テンポラリファイル名取得
     */
    public String getTempFileName() {
        return this.tempFileName;
    }

    /**
     * テンポラリファイル名設定処理
     */
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
     * アップロードファイル（メイン）を取得します。
     * @return アップロードファイル（メイン）
     */
    public UploadedFile getUploadedMainFile() {
        return uploadedMainFile;
    }

    /**
     * アップロードファイル（メイン）を設定します。
     * @param uploadedMainFile アップロードファイル（メイン）
     */
    public void setUploadedMainFile(UploadedFile uploadedMainFile) {
        this.uploadedMainFile = uploadedMainFile;
    }

    /**
     * 選択されたアップロードファイルのインデックスを取得します。
     * @return 選択されたアップロードファイルのインデックス
     */
    public int getUploadIndex() {
        return uploadIndex;
    }

    /**
     * 選択されたアップロードファイルのインデックスを設定します。
     * @param 選択されたアップロードファイルのインデックス
     */
    public void setUploadIndex(int uploadIndex) {
        this.uploadIndex = uploadIndex;
    }

    /**
     * アップロードファイル名（メイン）を取得します。
     * @return uploadFileNameMain を戻します。
     */
    public String getUploadFileNameMain() {
        return uploadFileNameMain;
    }

    /**
     * アップロードファイル名（メイン）を設定します。
     * @param uploadFileNameMain アップロードファイル名（メイン）
     */
    public void setUploadFileNameMain(String uploadFileNameMain) {
        this.uploadFileNameMain = uploadFileNameMain;
    }

    /**
     *アップロードファイル名（添付１）を取得します。
     * @return アップロードファイル名（添付１）
     */
    public String getUploadFileNameTenpu1() {
        return uploadFileNameTenpu1;
    }

    /**
     * アップロードファイル名（添付１）を設定します。
     * @param uploadFileNameTenpu1 アップロードファイル名（添付１）
     */
    public void setUploadFileNameTenpu1(String uploadFileNameTenpu1) {
        this.uploadFileNameTenpu1 = uploadFileNameTenpu1;
    }

    /**
     * アップロードファイル名（添付２）を取得します。
     * @return アップロードファイル名（添付２）
     */
    public String getUploadFileNameTenpu2() {
        return uploadFileNameTenpu2;
    }

    /**
     * アップロードファイル名（添付２）を設定します。
     * @param uploadFileNameTenpu2 アップロードファイル名（添付２）
     */
    public void setUploadFileNameTenpu2(String uploadFileNameTenpu2) {
        this.uploadFileNameTenpu2 = uploadFileNameTenpu2;
    }

    /**
     * アップロードファイル名（添付３）を取得します。
     * @return アップロードファイル名（添付３）
     */
    public String getUploadFileNameTenpu3() {
        return uploadFileNameTenpu3;
    }

    /**
     * アップロードファイル名（添付３）を設定します。
     * @param uploadFileNameTenpu3 アップロードファイル名（添付３）
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
     * 添付ファイル１の削除チェックボックスを取得します。
     * @return 添付ファイル１の削除チェックボックス
     */
    public boolean isCmbDelFlg1() {
        return cmbDelFlg1;
    }

    /**
     * 添付ファイル１の削除チェックボックスを設定します。
     * @param cmbDelFlg1 添付ファイル１の削除チェックボックス
     */
    public void setCmbDelFlg1(boolean cmbDelFlg1) {
        this.cmbDelFlg1 = cmbDelFlg1;
    }

    /**添付ファイル２の削除チェックボックスを取得します。
     * @return 添付ファイル２の削除チェックボックス
     */
    public boolean isCmbDelFlg2() {
        return cmbDelFlg2;
    }

    /**
     * 添付ファイル２の削除チェックボックスを設定します。
     * @param cmbDelFlg2 添付ファイル２の削除チェックボックス
     */
    public void setCmbDelFlg2(boolean cmbDelFlg2) {
        this.cmbDelFlg2 = cmbDelFlg2;
    }

    /**
     * 添付ファイル３の削除チェックボックスを取得します。
     * @return 添付ファイル３の削除チェックボックス
     */
    public boolean isCmbDelFlg3() {
        return cmbDelFlg3;
    }

    /**
     * 添付ファイル３の削除チェックボックスを設定します。
     * @param cmbDelFlg3 添付ファイル３の削除チェックボックス
     */
    public void setCmbDelFlg3(boolean cmbDelFlg3) {
        this.cmbDelFlg3 = cmbDelFlg3;
    }
    
    /**
     * アップロードファイル（メイン）を設定できるかどうか
     * @return
     */
    public boolean isAddMainFile() {
        return isNull(getEditEntity().getFileName()) ? true : false;
    }

    /**
     * アップロードファイル（添付）を設定できるかどうか
     * @return
     */
    public boolean isAddAttachFile() {
        if (isNull(getEditEntity().getAttachFl1())
                || isNull(getEditEntity().getAttachFl2())
                || isNull(getEditEntity().getAttachFl3())) {
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
     * 関連文書を取得します。
     * @return 関連文書を
     */
    public List getListKanrenBunsho() {
        return listKanrenBunsho;
    }

    /**
     * 関連文書を設定します。
     * @param listKanrenBunsho 関連文書を
     */
    public void setListKanrenBunsho(List listKanrenBunsho) {
        this.listKanrenBunsho = listKanrenBunsho;
    }

    /**
     * 関連文書の件数を取得します。
     * @return 関連文書の件数
     */
    public int getListKanrenBunshoSize() {
        int size = 0;
        if (getListKanrenBunsho() != null) {
            size = getListKanrenBunsho().size();
        }
        return size;
    }

    /**
     * 選択されたダウンロードする関連文書のインデックスを取得します。
     * @return 選択されたダウンロードする関連文書のインデックス
     */
    public int getDownloadKanrenIndex() {
        return downloadKanrenIndex;
    }

    /**
     * 選択されたダウンロードする関連文書のインデックスを設定します。
     * @param downloadKanrenIndex 選択されたダウンロードする関連文書のインデックス
     */
    public void setDownloadKanrenIndex(int downloadKanrenIndex) {
        this.downloadKanrenIndex = downloadKanrenIndex;
    }

    /**
     * データのクリアを行います。
     */
    public void clearData() {
        setEditEntity(null);
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