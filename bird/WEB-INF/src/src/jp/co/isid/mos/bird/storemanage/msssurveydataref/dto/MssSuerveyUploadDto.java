/*
 * 作成日: 2006/04/19
 */
package jp.co.isid.mos.bird.storemanage.msssurveydataref.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.UploadDto;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * @author lee
 */
public class MssSuerveyUploadDto implements UploadDto {
    
	/* VIEW_ID */
    public final String VIEW_ID				= "BSM012";
    public final String upload_VIEW_ID		= "BSM012V02";
//    public final String condition_VIEW_ID		= "BSM007V01";
    public final String result_VIEW_ID			= "BSM004V01";
    public final String onerSearch_VIEW_ID		= "BCO006V01";
    public final String miseSearch_VIEW_ID		= "BCO008V01";
    
    /* 会社コード */
    private String companyCd;
    
    
    /* 選択Flage 0:ファイル転送　1:支部洗え替え */
    private int selectFlg; 
        
    /*処理区分 0:取り込み処理 1:支部洗え替え*/
    private String shoriKbn; 
    
    /*対象年度*/
    private String nendo;

    /*対象回数*/
    private String kai;
    
    /* システム日付 */
    private String sysDate;
    
    /*基準年度*/
    private String kijunNendo;
    
    /*対象年度*/
    private List taishoNendo;

    /*対象回*/
    private List taishoKai;

    
    /*取り込みファイルタイプ*/
    private int fileFlg;

    /*アップデータフラグ*/
    private int updateFlg;
    
    /*ファイル存在チェック*/
    private int fileCheck;
    private String confMsg;

    // 共通アップロードアクション用プロパティ
    private UploadedFile uploadedFile;
    private String uploadFileKoumokuName;
    private String uploadFileHyoukaName;
    private String uploadFileChousaName;
    private String uploadFileName;
    
    // アップロードファイル（情報本体のファイル）
    private UploadedFile uploadedMainFile;
    private String fileKind;
    /**/
    private int registFlg;
    /**/
    private String tempFileName;
    /**
	 * @return selectFlg を戻します。
	 */
	public int getSelectFlg() {
		return selectFlg;
	}
	/**
	 * @param selectFlg selectFlg を設定。 
	 */
	public void setSelectFlg(int selectFlg) {
		this.selectFlg = selectFlg;
	}
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return condition_VIEW_ID を戻します。
	 */
	public String getUpload_VIEW_ID() {
		return upload_VIEW_ID;
	}
    /**
     * shoriKbnを戻す
     * @return shoriKbn
     */
    public String getShoriKbn() {
        return shoriKbn;
    }
    /**
     * shoriKbnをセット
     * @param shoriKbn
     */
    public void setShoriKbn(String shoriKbn) {
        this.shoriKbn = shoriKbn;
    }
    /**
     * nendoを戻す
     * @return nendo
     */
    public String getNendo() {
        return nendo;
    }
    /**
     * nendoをセット
     * @param nendo
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }
    /**
     * kaiを戻す
     * @return kai
     */
    public String getKai() {
        return kai;
    }
    /**
     * kaiをセット
     * @param kai
     */
    public void setKai(String kai) {
        this.kai = kai;
    }
    /**
     * sysDateを取得
     * @return sysDate
     */
    public String getSysDate() {
        return sysDate;
    }
    /**
     * sysDateをセット
     * @param sysDate
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * getS2Container処理
     * @return
     */
    public S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * kijunNendoを取得
     * @return kijunNendo
     */
    public String getKijunNendo() {
        return kijunNendo;
    }
    /**
     * kijunNendoをセット
     * @param kijunNendo
     */
    public void setKijunNendo(String kijunNendo) {
        this.kijunNendo = kijunNendo;
    }
    /**
     * taishoNendoを取得
     * @return taishoNendo
     */
    public List getTaishoNendo() {
        return taishoNendo;
    }
    /**
     * taishoNendoをセット
     * @param taishoNendo
     */
    public void setTaishoNendo(List taishoNendo) {
        this.taishoNendo = taishoNendo;
    }
    /**
     * fileFlgを取得
     * @return fileFlg
     */
    public int getFileFlg() {
        return fileFlg;
    }
    /**
     * fileFlgをセット
     * @param fileFlg
     */
    public void setFileFlg(int fileFlg) {
        this.fileFlg = fileFlg;
    }
    /**
     * updateFlgを取得
     * @return updateFlg
     */
    public int getUpdateFlg() {
        return updateFlg;
    }
    /**
     * updateFlgをセット
     * @param updateFlg
     */
    public void setUpdateFlg(int updateFlg) {
        this.updateFlg = updateFlg;
    }

    public void clear(){
        setCompanyCd(null);
        setNendo(null);
        setUpdateFlg(0);
        setUploadFileName(null);
        setTaishoNendo(null);
        setUpdateFlg(0);
        setUploadFileHyoukaName("");
        setUploadFileKoumokuName("");
        setUploadFileChousaName("");
        setConfMsg("");
        setFileCheck(0);
    }
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
    public String getTempFileName() {
        return tempFileName;
    }
    public void setTempFileName(String tempFileName) {
        this.tempFileName = tempFileName;
    }
    /**
     * taishoKaiを取得
     * @return taishoKai
     */
    public List getTaishoKai() {
        return taishoKai;
    }
    /**
     * taishoKaiをセット
     * @param taishoKai
     */
    public void setTaishoKai(List taishoKai) {
        this.taishoKai = taishoKai;
    }
    public int getRegistFlg() {
        return registFlg;
    }
    public void setRegistFlg(int registFlg) {
        this.registFlg = registFlg;
    }
    public UploadedFile getUploadedMainFile() {
        return uploadedMainFile;
    }
    public void setUploadedMainFile(UploadedFile uploadedMainFile) {
        this.uploadedMainFile = uploadedMainFile;
    }
    public String getFileKind() {
        if(getFileFlg() == 1){
            fileKind = "項目マスタ";
        }else if(getFileFlg() == 2){
            fileKind = "評価データ";
        }else if(getFileFlg() == 3){
            fileKind = "調査データ";
        }
        
        return fileKind;
    }
    public String getUploadFileKoumokuName() {
        return uploadFileKoumokuName;
    }
    public void setUploadFileKoumokuName(String uploadFileKoumokuName) {
        this.uploadFileKoumokuName = uploadFileKoumokuName;
    }
    public String getUploadFileHyoukaName() {
        return uploadFileHyoukaName;
    }
    public void setUploadFileHyoukaName(String uploadFileHyoukaName) {
        this.uploadFileHyoukaName = uploadFileHyoukaName;
    }
    public String getUploadFileChousaName() {
        return uploadFileChousaName;
    }
    public void setUploadFileChousaName(String uploadFileChousaName) {
        this.uploadFileChousaName = uploadFileChousaName;
    }
    public int getFileCheck() {
        return fileCheck;
    }
    public void setFileCheck(int fileCheck) {
        this.fileCheck = fileCheck;
    }
    public String getConfMsg() {
        return confMsg;
    }
    public void setConfMsg(String confMsg) {
        this.confMsg = confMsg;
    }
	
}
