package jp.co.isid.mos.bird.inforegist.notificationregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UITutatuInfo;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * 通達登録編集画面DTO
 * @author m.onodera
 */
public class RegistFormDto implements UploadDto ,DownloadDto {

    /* 関連文書 */
    private List listKanrenBunsho;
    
/* ↓↓↓【画面制御用】↓↓↓ */
    /* 関連文書ダウンロードIndex */
    private int downloadKanrenIndex;
    /* カテゴリ */
    private List listCategory;
    /* 編集中Entity */
    private UITutatuInfo editEntity;
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
    
    //公開開始日のプルダウン
    private List listKokaiKaisi = null;
    
    //関連先として登録している文書
    private List listKanrenSaki;

    //
    private boolean todayInsertDLBt = false;
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
     * @param editEntity editEntity を設定。
     */
    public void setEditEntity(UITutatuInfo editEntity) {
        this.editEntity = editEntity;
    }
    /**
     * @return editEntity を戻します。
     */
    public UITutatuInfo getEditEntity() {
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
     * 公開開始日のプルダウンを取得します。
     * @return 公開開始日
     */
    public List getListKokaiKaisi() {
        return listKokaiKaisi;
    }
    
    /**
     * 公開開始日のプルダウンを設定します。
     * @param listKokaiKaisi 公開開始日
     */
    public void setListKokaiKaisi(List listKokaiKaisi) {
        this.listKokaiKaisi = listKokaiKaisi;
    }

//    private String inputTitle;
    
    /**
     * @return inputTitle を戻します。
     */
//        public String getInputTitle() {
//        return inputTitle;
//    }
    /**
     * @param inputTitle inputTitle を設定。
     */
//        public void setInputTitle(String inputTitle) {
//        this.inputTitle = inputTitle;
//    }
    
    /**
     * /関連先として登録している文書を取得します。
     * @return /関連先として登録している文書 
     */
    public List getListKanrenSaki() {
        return listKanrenSaki;
    }
    /**
     * /関連先として登録している文書を設定します。
     * @param /関連先として登録している文書
     */
    public void setListKanrenSaki(List listKanrenSaki) {
        this.listKanrenSaki = listKanrenSaki;
    }
    
    /**
     * 関連先として登録している文書の件数を取得します。
     * @return 件数
     */
    public int getListKanrenSakiSize(){
        if(getListKanrenSaki() != null){
            return getListKanrenSaki().size();
        }
        return 0;
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
	/**
	 * @return クラス変数todayInsertDLBt を戻します。
	 */
	public boolean isTodayInsertDLBt() {
		return todayInsertDLBt;
	}
	/**
	 * @param todayInsertDLBt を クラス変数todayInsertDLBtへ設定します。
	 */
	public void setTodayInsertDLBt(boolean todayInsertDLBt) {
		this.todayInsertDLBt = todayInsertDLBt;
	}
    //ファイル情報クリア処理
    public void clearFileName(){
        setUploadTempFileNameMain(null);
        setUploadTempFileNameTenpu1(null);
        setUploadTempFileNameTenpu2(null);
        setUploadTempFileNameTenpu3(null);
    }

}
