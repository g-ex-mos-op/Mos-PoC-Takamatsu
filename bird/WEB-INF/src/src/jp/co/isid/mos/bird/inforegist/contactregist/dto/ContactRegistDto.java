/*
 * 作成日: 2006/01/25
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.dto;

import java.util.Iterator;
import java.util.List;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import jp.co.isid.mos.bird.common.entity.MstCategoryInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.inforegist.contactregist.entity.UIRenrakuInfo;

/**
 * 連絡登録ＤＴＯ
 * @author xyuchida
 *
 */
public class ContactRegistDto extends UIRenrakuInfo implements UploadDto, DownloadDto {
    
    //登録モード
    public static final int EDIT_MODE_DEFAULT = 0;
    public static final int EDIT_MODE_REGIST = 1;
    public static final int EDIT_MODE_UPDATE = 2;
    public static final int EDIT_MODE_DELETE = 3;

    private static final String FILENAME_DELIMITER = "_";
    private static final int MAX_ATTACH_FILE = 3;

    private int editMode;
    private List targetDateList;
    private List categoryList;

    //添付ファイル関連
    private int index;
    private boolean checkAttachFile1;
    private boolean checkAttachFile2;
    private boolean checkAttachFile3;
    private String tempFileName1;
    private String tempFileName2;
    private String tempFileName3;
    private String inputTitle;
    private List deleteFileNameList;
    private UploadedFile uploadedFile;
 
    //公開開始日のプルダウン
    private List listKokaiKaisi;
    //関連文書
    private List listKanrenBunsho;
    //選択された関連ファイルのインデックス
    private int downloadKanrenIndex;

    /**
     * @return editMode を戻します。
     */
    public int getEditMode() {
        return editMode;
    }
    /**
     * @param editMode editMode を設定。
     */
    public void setEditMode(int editMode) {
        this.editMode = editMode;
    }
    /**
     * @return targetDateList を戻します。
     */
    public List getTargetDateList() {
        return targetDateList;
    }
    /**
     * @param targetDateList targetDateList を設定。
     */
    public void setTargetDateList(List targetDateList) {
        this.targetDateList = targetDateList;
    }
    /**
     * @return categoryList を戻します。
     */
    public List getCategoryList() {
        return categoryList;
    }
    /**
     * @param categoryList categoryList を設定。
     */
    public void setCategoryList(List categoryList) {
        this.categoryList = categoryList;
    }

    public String getCategoryName() {
        String categoryName = null;
        for (Iterator it = getCategoryList().iterator(); it.hasNext();) {
            MstCategoryInfo mstCategoryInfo = (MstCategoryInfo) it.next();
            if (mstCategoryInfo.getCateId().equals(getCateId())) {
                categoryName = mstCategoryInfo.getCateName();
            }
        }
        return categoryName;
    }

    /**
     * @return index を戻します。
     */
    public int getIndex() {
        return index;
    }
    /**
     * @param index index を設定。
     */
    public void setIndex(int index) {
        this.index = index;
    }
    /**
     * @return checkAttachFile1 を戻します。
     */
    public boolean isCheckAttachFile1() {
        return checkAttachFile1;
    }
    /**
     * @param checkAttachFile1 checkAttachFile1 を設定。
     */
    public void setCheckAttachFile1(boolean checkAttachFile1) {
        this.checkAttachFile1 = checkAttachFile1;
    }
    /**
     * @return checkAttachFile2 を戻します。
     */
    public boolean isCheckAttachFile2() {
        return checkAttachFile2;
    }
    /**
     * @param checkAttachFile2 checkAttachFile2 を設定。
     */
    public void setCheckAttachFile2(boolean checkAttachFile2) {
        this.checkAttachFile2 = checkAttachFile2;
    }
    /**
     * @return checkAttachFile3 を戻します。
     */
    public boolean isCheckAttachFile3() {
        return checkAttachFile3;
    }
    /**
     * @param checkAttachFile3 checkAttachFile3 を設定。
     */
    public void setCheckAttachFile3(boolean checkAttachFile3) {
        this.checkAttachFile3 = checkAttachFile3;
    }
    /**
     * @return tempFileName1 を戻します。
     */
    public String getTempFileName1() {
        return tempFileName1;
    }
    /**
     * @param tempFileName1 tempFileName1 を設定。
     */
    public void setTempFileName1(String tempFileName1) {
        this.tempFileName1 = tempFileName1;
    }
    /**
     * @return tempFileName2 を戻します。
     */
    public String getTempFileName2() {
        return tempFileName2;
    }
    /**
     * @param tempFileName2 tempFileName2 を設定。
     */
    public void setTempFileName2(String tempFileName2) {
        this.tempFileName2 = tempFileName2;
    }
    /**
     * @return tempFileName3 を戻します。
     */
    public String getTempFileName3() {
        return tempFileName3;
    }
    /**
     * @param tempFileName3 tempFileName3 を設定。
     */
    public void setTempFileName3(String tempFileName3) {
        this.tempFileName3 = tempFileName3;
    }
    /**
     * @return inputTitle を戻します。
     */
    public String getInputTitle() {
        return inputTitle;
    }
    /**
     * @param inputTitle inputTitle を設定。
     */
    public void setInputTitle(String inputTitle) {
        this.inputTitle = inputTitle;
    }
    /**
     * @return deleteFileNameList を戻します。
     */
    public List getDeleteFileNameList() {
        return deleteFileNameList;
    }
    /**
     * @param deleteFileNameList deleteFileNameList を設定。
     */
    public void setDeleteFileNameList(List deleteFileNameList) {
        this.deleteFileNameList = deleteFileNameList;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getTempFileName() {
        String tempFileName = "";
        switch (getIndex()) {
        case 1:
            tempFileName = getTempFileName1();
            break;
        case 2:
            tempFileName = getTempFileName2();
            break;
        case 3:
            tempFileName = getTempFileName3();
            break;
        default:
            break;
        }
        return tempFileName;
    }

    public void setTempFileName(String tempFileName) {
        switch (getIndex()) {
        case 1:
            setTempFileName1(tempFileName);
            break;
        case 2:
            setTempFileName2(tempFileName);
            break;
        case 3:
            setTempFileName3(tempFileName);
            break;
        default:
            break;
        }
    }

    public String getUploadFileName() {
        String uploadFileName = "";
        switch (getIndex()) {
        case 1:
            uploadFileName = getAttachFl1();
            break;
        case 2:
            uploadFileName = getAttachFl2();
            break;
        case 3:
            uploadFileName = getAttachFl3();
            break;
        default:
            break;
        }
        // ファイル名生成
        return createUploadFileName(uploadFileName);
    }

    public void setUploadFileName(String uploadFileName) {
        switch (getIndex()) {
        case 1:
            setAttachFl1(uploadFileName);
            break;
        case 2:
            setAttachFl2(uploadFileName);
            break;
        case 3:
            setAttachFl3(uploadFileName);
            break;
        default:
            break;
        }
    }

    public void setAttachFileTitle(String attachFileTitle) {
        switch (getIndex()) {
        case 1:
            setAttachName1(attachFileTitle);
            break;
        case 2:
            setAttachName2(attachFileTitle);
            break;
        case 3:
            setAttachName3(attachFileTitle);
            break;
        default:
            break;
        }
    }

    /**
     * 添付ファイルのあきインデックスを取得します。
     */
    public int getBlankIndex() {
        int index = 0;
        for (index = 1; index <= MAX_ATTACH_FILE; index++) {
            if (index == 1 && (getAttachFl1() == null || getAttachFl1().length() == 0)) {
                break;
            } else if (index == 2 && (getAttachFl2() == null || getAttachFl2().length() == 0)) {
                break;
            } else if (index == 3 && (getAttachFl3() == null || getAttachFl3().length() == 0)) {
                break;
            }
        }
        return index;
    }

    private String createUploadFileName(String uploadFileName) {
        return getRegDate()
                + FILENAME_DELIMITER
                + getSeq()
                + FILENAME_DELIMITER
                //添付ファイルシーケンス番号２～４    
                + String.valueOf(getIndex() + 1)
                + FILENAME_DELIMITER
                + uploadFileName;
    }
    
    /**
     * 公開開始日のプルダウンを取得します。
     * @return 公開開始日のプルダウン  
     */
    public List getListKokaiKaisi() {
        return listKokaiKaisi;
    }
    
    /**
     * 公開開始日のプルダウンを設定します。
     * @param 公開開始日のプルダウン
     */
    public void setListKokaiKaisi(List listKokaiKaisi) {
        this.listKokaiKaisi = listKokaiKaisi;
    }
    
    /**
     * 編集モードを取得します。
     * @return 編集モード
     */
    public String getEditModeName(){
        String str = "";
        if(getEditMode() == EDIT_MODE_REGIST){
            str = "新規";
        }else if(getEditMode() == EDIT_MODE_UPDATE){
            str = "編集";
        }else if(getEditMode() == EDIT_MODE_DELETE){
            str = "削除";
        }
        return str;
    }
    
    /**
     * 関連ファイルを取得します。
     * @return 関連ファイル
     */
    public List getListKanrenBunsho() {
        return listKanrenBunsho;
    }
    
    /**
     * 関連ファイルを設定します
     * @param 関連ファイル
     */
    public void setListKanrenBunsho(List listKanrenBunsho) {
        this.listKanrenBunsho = listKanrenBunsho;
    }
    
    /**
     * 関連ファイルの件数を取得します。
     * @return 関連ファイルの件数
     */
    public int getListKanrenBunshoSize() {
        int size = 0;
        if (getListKanrenBunsho() != null) {
            size = getListKanrenBunsho().size();
        }
        return size;
    }
    
    /**
     * 選択された関連ファイルのインデックスを取得します。
     * @return 選択された関連ファイルのインデックス
     */
    public int getDownloadKanrenIndex() {
        return downloadKanrenIndex;
    }
    
    /**
     * 選択された関連ファイルのインデックスを設定します。
     * @param downloadKanrenIndex 選択された関連ファイルのインデックス
     */
    public void setDownloadKanrenIndex(int downloadKanrenIndex) {
        this.downloadKanrenIndex = downloadKanrenIndex;
    }

    
}
