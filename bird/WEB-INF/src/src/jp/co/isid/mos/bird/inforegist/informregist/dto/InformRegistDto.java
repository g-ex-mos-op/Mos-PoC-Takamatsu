/*
 * 作成日: 2006/2/8
 */
package jp.co.isid.mos.bird.inforegist.informregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.dto.UploadDto;
import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * お知らせ情報登録用Dto<br>
 * @author itamoto
 */
public class InformRegistDto implements UploadDto, DownloadDto{
    
    /* VIEW_ID */
    public final String VIEW_ID  = "BIF004";
    public final String search_VIEW_ID  = "BIF004V01";
    public final String edit_VIEW_ID    = "BIF004V02";
    public final String confirm_VIEW_ID = "BIF004V03";
    public final String publicTarget_VIEW_ID = "BCO002V01";
    public final String docSearch_VIEW_ID = "BCO001V01";

    
    // 情報種別（お知らせ）
    public final String INFO_SHU = "05";

    /* 対象年月 */
    private String targetYM;
    /* 対象年月リスト */
    private List targetYMList;

    /* 検索結果情報 */
    private List searchResultList;
//    /* 検索結果情報サイズ */
//    private int searchResultListSize;
    
    /* 登録モード(デフォルト：0、登録：1、変更：2、削除：3、参照：4) */
    private int mode;
    /* 登録モード名称(デフォルト：0、登録：1、変更：2、削除：3) */
//    private String modeName;

    // 添付ファイルアップロード用
    private String attachName;
    private UploadedFile uploadedFile;
    private String uploadFileName;
    private UploadedFile uploadedAttachFile;
    private int uploadIndex;

    // アップロードファイル（添付ファイル１）
    private String uploadTempFileNameTenpu1;
    private String uploadFileNameTenpu1;
    // アップロードファイル（添付ファイル２）
    private String uploadTempFileNameTenpu2;
    private String uploadFileNameTenpu2;
    // アップロードファイル（添付ファイル３）
    private String uploadTempFileNameTenpu3;
    private String uploadFileNameTenpu3;
    
    // テンポラリファイル名
    private String tempFileName;
    
    // 添付ファイル 削除用コンボ
    private boolean cmbDelFlg1;
    private boolean cmbDelFlg2;
    private boolean cmbDelFlg3;
    
    //公開期間FROMのプルダウン
    private List listPubDateFrom;
    //公開期間TOのプルダウン
    private List listPubDateTo;

    private UINews uploadEntity;
    
    //関連文書
    private List listKanrenBunsho;
    //関連文書のインデックス
    private int downloadKanrenIndex;
    
    //添付ファイルのインデックス
    private int downloadIndex;
    
    private boolean existRegistData = false;
    /**
     * 登録モードの設定
     * @return mode を戻します。
     */
    public int getMode() {
        return mode;
    }
    /**
     * 登録モードの設定
     * @param mode mode を設定。
     */
    public void setMode(int mode) {
        this.mode = mode;
    }
    
	/**
	 * 登録モード名称の設定
	 * @return modeName を戻します。
	 */
	public String getModeName() {
		return (getMode() == 1) ? "新規" : (getMode() == 2) ? "更新"
				: (getMode() == 3) ? "削除" : "参照";
	}
	

	/**
     * 対象年月の設定
     * @return targetYM を戻します。
     */
    public String getTargetYM() {
        return targetYM;
    }
    /**
     * 対象年月の設定
     * @param targetYM targetYM を設定。
     */
    public void setTargetYM(String targetYM) {
        this.targetYM = targetYM;
    }
    /**
     * 対象年月リストの設定
     * @return targetYMList を戻します。
     */
    public List getTargetYMList() {
        return targetYMList;
    }
    /**
     * 対象年月リストの設定
     * @param targetYMList targetYMList を設定。
     */
    public void setTargetYMList(List targetYMList) {
        this.targetYMList = targetYMList;
    }

    /**
     * 検索結果の設定
     * @return searchResultList を戻します。
     */
    public List getSearchResultList() {
        return searchResultList;
    }
    /**
     * 検索結果の設定
     * @param searchResultList searchResultList を設定。
     */
    public void setSearchResultList(List searchResultList) {
        this.searchResultList = searchResultList;
    }
    /**
     * 検索結果サイズの設定
     * @return searchResultListSize を戻します。
     */
    public int getSearchResultListSize() {
        return (searchResultList == null) ? 0 : searchResultList.size();
    }
//    /**
//     * 検索結果サイズの設定
//     * @param searchResultListSize searchResultListSize を設定。
//     */
//    public void setSearchResultListSize(int searchResultListSize) {
//        this.searchResultListSize = searchResultListSize;
//    }
    
    /**
     * 情報のクリア
     */
    public void clear() {
        setSearchResultList(null);
//        setSearchResultListSize(0);
        setMode(0);
        if(getListKanrenBunsho() != null){
            getListKanrenBunsho().clear();
        }
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

    /**
     * テンポラリファイル名取得・設定処理
     */ 
    public String getTempFileName() {
        return this.tempFileName;
    }
    
    /**
     * テンポラリファイル名取得・設定処理
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
     * 公開開始日のプルダウンを取得します。
     * @return 公開開始日のプルダウン  
     */
    public List getListPubDateFrom() {
        return listPubDateFrom;
    }
    
    /**
     * 公開開始日のプルダウンを設定します。
     * @param 公開開始日のプルダウン
     */
    public void setListPubDateFrom(List listKokaiKaisi) {
        this.listPubDateFrom = listKokaiKaisi;
    }
    /**
     * アップロードエンティティを取得します。
     * @return アップロードエンティティ 
     */
    public UINews getUploadEntity() {
        return uploadEntity;
    }
    /**
     * アップロードエンティティを設定します。
     * @param アップロードエンティティ
     */
    public void setUploadEntity(UINews uploadEntity) {
        this.uploadEntity = uploadEntity;
    }
    /**
     * アップロードファイルのインデックスを取得します。
     * @return アップロードファイルのインデックス 
     */
    public int getUploadIndex() {
        return uploadIndex;
    }
    /**
     * アップロードファイルのインデックスを設定します。
     * @param アップロードファイルのインデックス
     */
    public void setUploadIndex(int uploadIndex) {
        this.uploadIndex = uploadIndex;
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
    /**
     * 添付ファイルのインデックスを取得します。
     * @return 添付ファイルのインデックス 
     */
    public int getDownloadIndex() {
        return downloadIndex;
    }
    /**
     * 添付ファイルのインデックスを設定します。
     * @param 添付ファイルのインデックス
     */
    public void setDownloadIndex(int downloadIndex) {
        this.downloadIndex = downloadIndex;
    }

    /**
     * 添付ファイル情報のクリア
     */
    public void clearUploadFile(){
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
     * 公開期間TOのプルダウンを取得します。
     * 
     * デフォルト値:99991231
	 * @return listPubDateTo 公開期間TOのプルダウン を戻します。
	 */
	public List getListPubDateTo() {
		return listPubDateTo;
	}
	/**
     * 公開期間TOのプルダウンを設定します。
	 * @param listPubDateTo を クラス変数listPubDateToへ設定します。
	 */
	public void setListPubDateTo(List listPubDateTo) {
		this.listPubDateTo = listPubDateTo;
	}
	/**
	 * @return existRegistData を戻します。
	 */
	public boolean isExistRegistData() {
		return existRegistData;
	}
	/**
	 * @param existRegistData を クラス変数existRegistDataへ設定します。
	 */
	public void setExistRegistData(boolean existRegistData) {
		this.existRegistData = existRegistData;
	}

    
}
