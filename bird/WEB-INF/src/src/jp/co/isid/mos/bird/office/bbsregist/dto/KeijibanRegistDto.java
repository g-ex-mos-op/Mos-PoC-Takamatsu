/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * 掲示板登録 参照/編集画面DTO
 * @author xytamura
 */
public class KeijibanRegistDto implements UploadDto, DownloadDto {

    public static final String VIEW_ID = "BOF003";
    

    /* カテゴリ */
    private List listCategory;


    /* 編集中Entity */
    private UIKeijiban editEntity;
    /* 参照用Entity */
    private UIKeijiban refEntity;
    
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
    private String uploadTempFileNameTenpu1;

    private String uploadFileNameTenpu1;

    //    // アップロードファイル（添付ファイル２）
    private String uploadTempFileNameTenpu2;

    private String uploadFileNameTenpu2;

    //    // アップロードファイル（添付ファイル３）
    private String uploadTempFileNameTenpu3;

    private String uploadFileNameTenpu3;

    // 添付ファイルアップロード用
    private String attachName;

    private UploadedFile uploadedAttachFile;

    // 添付ファイル 削除用コンボ
    private boolean cmbDelFlg1;

    private boolean cmbDelFlg2;

    private boolean cmbDelFlg3;

    //メンテナンス可能かどうか
    private boolean editFlg;
    
    /* 添付ファイル 選択インデックス */
    private int selectedAttachFileIndex;

    //登録画面→条件画面フラグ
    private boolean regToCondFlg;   
    
//  add start xkhata 
    // 削除画面 → 条件画面
    private boolean delToCondFlg;
// add end
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
     * 編集対象の掲示板エンティティを設定します。
     * @param 編集対象の掲示板一覧エンティティ
     */
    public void setEditEntity(UIKeijiban editEntity) {
        this.editEntity = editEntity;
    }

    /**
     * 編集対象の掲示板エンティティを取得します。
     * @return 編集対象の掲示板一覧エンティティ
     */
    public UIKeijiban getEditEntity() {
        return editEntity;
    }

    
    /**
     * 参照対象の掲示板エンティティを取得します。
     * @return 参照対象の掲示板エンティティ 
     */
    public UIKeijiban getRefEntity() {
        return refEntity;
    }

    /**
     * 参照対象の掲示板エンティティを設定します。
     * @param 参照対象の掲示板エンティティ
     */
    public void setRefEntity(UIKeijiban refEntity) {
        this.refEntity = refEntity;
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
     * アップロードファイル（添付）を設定できるかどうか
     * @return
     */
    public boolean isAddAttachFile() {
        //編集不可の場合
        if(!isEditFlg()){
           return false; 
        }
        
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
     * データのクリアを行います。
     */
    public void clearData() {
        setAttachName("");
        setUploadedAttachFile(null);
        setCmbDelFlg1(false);
        setCmbDelFlg2(false);
        setCmbDelFlg3(false);
        setUploadFileNameTenpu1("");
        setUploadFileNameTenpu2("");
        setUploadFileNameTenpu3("");
        setUploadTempFileNameTenpu1("");
        setUploadTempFileNameTenpu2("");
        setUploadTempFileNameTenpu3("");
    }

    /**
     * メンテナンス可能かどうかを取得します。
     * @return editFlg 
     */
    public boolean isEditFlg(){
        return editFlg;
    }

    /**
     * メンテナンス可能かどうかを設定します。
     * @param editFlg
     */
    public void setEditFlg(boolean editFlg) {
        this.editFlg = editFlg;
    }
    
    /**
     * 添付ファイル 選択インデックスを設定します。
     * @param 添付ファイル 選択インデックス
     */
    public void setSelectedAttachFileIndex(int selectedAttachFileIndex) {
        this.selectedAttachFileIndex = selectedAttachFileIndex;
    }

    /**
     * 添付ファイル 選択インデックスを取得します。
     * @return 添付ファイル 選択インデックス
     */
    public int getSelectedAttachFileIndex() {
        return selectedAttachFileIndex;
    }

    /**
     * 登録画面→条件画面フラグを取得します。
     * @return 登録画面→条件画面フラグ 
     */
    public boolean isRegToCondFlg() {
        return regToCondFlg;
    }

    /**
     * 登録画面→条件画面フラグを設定します。
     * @param 登録画面→条件画面フラグ
     */
    public void setRegToCondFlg(boolean regToCondFlg) {
        this.regToCondFlg = regToCondFlg;
    }

    public boolean isDelToCondFlg() {
        return delToCondFlg;
    }

    public void setDelToCondFlg(boolean delToCondFlg) {
        this.delToCondFlg = delToCondFlg;
    }

    
        
}