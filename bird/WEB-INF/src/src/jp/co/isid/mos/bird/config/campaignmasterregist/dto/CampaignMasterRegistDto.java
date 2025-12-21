package jp.co.isid.mos.bird.config.campaignmasterregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.common.code.AbstShukeiKbn;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampaign;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * キャンペーンマスタ登録 編集DTO
 * 
 * 更新日:2012/12/26 xkinu「販社」→「MSC」へ変更対応
 * @author xnkusama
 *
 */
public class CampaignMasterRegistDto implements CsvOutputDto {

    /**
     * 対象店舗
     *   1:全店　　2:支部　　3:個店
     */
    private String condTaishoTenpo;
    /**
     * 条件画面表示モード
     *  1:編集可能キャンペーン　　2:過去キャンペーン
     */
    private int condMode;
    /**
     * 登録モード
     * 1:新規　　2:編集　　3:照会
     */
    private int registMode;
    /**
     * 対象期間マスタEntity
     */
    private MstCampaign entityMstCampaign;
    /**
     * 対象メニューマスタList
     */
    private List entityMstMenuList;
    /**
     * 編集中データの会社コード
     */
    private String companyCd;
    /**
     * 編集中 キャンペーン識別番号
     */
    private String campId;
    /**
     * 個店アップロードファイル名
     */
    private String uploadFileName;
    /**
     * 個店アップロードファイルデータ
     */
    private List uploadFileData;
    /**
     * 個店アップロードファイルエラー
     */
    private boolean uploadErrorFlg;
    /**
     * 個店アップロード表示モード
     */
    private int uploadStatus;
    /**
     * 支部一覧
     */
    private List listSibu;
    /**
     * 直営・販社含む 支部一覧
     */
    private List listAreaDai;
    /**
     * 支部一覧エリア大フラグ
     * 　true:エリア大（直営・販社含）
     */
    private boolean flgAreaDai;

    /**
     * 編集可能キャンペーン一覧リスト
     */
    private List listRegistCampaign;
    
    /**
     * 過去キャンペーン一覧リスト
     */
    private List listKakoCampaign;

    /**
     * システム日付
     */
    private String sysDate;
    
    /**
     * ユーザー情報
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * 親メニュー追加可能フラグ
     */
    private boolean flgOyaMenuAdd = false;
    
    /**
     * キャンペーン一覧再取得フラグ
     */
    private boolean flgNeedCampList = false;
    
    /**
     * 親メニューエラーインデックス
     */
    private int errIndexOya = 0;
    
    /**
     * 子メニューエラーインデックス
     */
    private int errIndexKo = 0;
    
    /**
     * エラーエレメント
     */
    private String errElement;
    
    /**
     * 対象期間開始日変更チェック用フラグ
     */
    private String warningMsg1;
    
    /**
     * 対象期間終了日変更チェック用フラグ
     */
    private String warningMsg2;
    /**
     * 確認ダイアログ用メッセージ
     */
    private String confirmMsg;
    
    /**
     * 確認ダイアログ用メッセージ取得処理
     * 
	 * @return クラス変数confirmMsg を戻します。
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}
	/**
	 * 確認ダイアログ起動判断値取得処理
	 * 
	 * @return
	 */
	public boolean isExeConfirmMsg() {
		return !CommonUtil.isNull(getConfirmMsg());
	}

	/**
	 * 確認ダイアログ用メッセージ設定処理
	 * 
	 * @param confirmMsg を クラス変数confirmMsgへ設定します。
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}

	/**
     * 編集可能キャンペーン一覧の件数
     * @return
     */
    public int getListRegistCampaignSize() {
        if (getListRegistCampaign() == null) {
            return 0;
        }
        return getListRegistCampaign().size();
    }
    
    public int getCondMode() {
        return condMode;
    }

    public void setCondMode(int condMode) {
        this.condMode = condMode;
    }

    public MstCampaign getEntityMstCampaign() {
        return entityMstCampaign;
    }

    public void setEntityMstCampaign(MstCampaign entityMstCampaign) {
        this.entityMstCampaign = entityMstCampaign;
    }

    public List getEntityMstMenuList() {
        return entityMstMenuList;
    }

    public void setEntityMstMenuList(List entityMstMenuList) {
        this.entityMstMenuList = entityMstMenuList;
    }

    public int getRegistMode() {
        return registMode;
    }

    public void setRegistMode(int registMode) {
        this.registMode = registMode;
    }

    public boolean isFlgAreaDai() {
        return flgAreaDai;
    }

    public void setFlgAreaDai(boolean flgAreaDai) {
        this.flgAreaDai = flgAreaDai;
    }

    public List getListAreaDai() {
        return listAreaDai;
    }

    public void setListAreaDai(List listAreaDai) {
        this.listAreaDai = listAreaDai;
    }

    public List getListSibu() {
        return listSibu;
    }

    public void setListSibu(List listSibu) {
        this.listSibu = listSibu;
    }

    public String getCampId() {
        return campId;
    }

    public void setCampId(String campId) {
        this.campId = campId;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public String getCondTaishoTenpo() {
        return condTaishoTenpo;
    }

    public void setCondTaishoTenpo(String condTaishoTenpo) {
        this.condTaishoTenpo = condTaishoTenpo;
    }

    public boolean isUploadErrorFlg() {
        return uploadErrorFlg;
    }

    public void setUploadErrorFlg(boolean uploadErrorFlg) {
        this.uploadErrorFlg = uploadErrorFlg;
    }

    public List getUploadFileData() {
        return uploadFileData;
    }

    public void setUploadFileData(List uploadFileData) {
        this.uploadFileData = uploadFileData;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public List getListKakoCampaign() {
        return listKakoCampaign;
    }

    public void setListKakoCampaign(List listKakoCampaign) {
        this.listKakoCampaign = listKakoCampaign;
    }

    public List getListRegistCampaign() {
        return listRegistCampaign;
    }

    public void setListRegistCampaign(List listRegistCampaign) {
        this.listRegistCampaign = listRegistCampaign;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public boolean isFlgOyaMenuAdd() {
        return flgOyaMenuAdd;
    }

    public void setFlgOyaMenuAdd(boolean flgOyaMenuAdd) {
        this.flgOyaMenuAdd = flgOyaMenuAdd;
    }

    public boolean isFlgNeedCampList() {
        return flgNeedCampList;
    }

    public void setFlgNeedCampList(boolean flgNeedCampList) {
        this.flgNeedCampList = flgNeedCampList;
    }

    public int getErrIndexKo() {
        return errIndexKo;
    }

    public void setErrIndexKo(int errIndexKo) {
        this.errIndexKo = errIndexKo;
    }

    public int getErrIndexOya() {
        return errIndexOya;
    }

    public void setErrIndexOya(int errIndexOya) {
        this.errIndexOya = errIndexOya;
    }

    public String getErrElement() {
        return errElement;
    }

    public void setErrElement(String errElement) {
        this.errElement = errElement;
    }

    public int getUploadFileDataSize() {
        return uploadFileData == null ? 0 : uploadFileData.size();
    }

    public String getWarningMsg1() {
        return warningMsg1;
    }

    public void setWarningMsg1(String warningMsg1) {
        this.warningMsg1 = warningMsg1;
    }

    public String getWarningMsg2() {
        return warningMsg2;
    }

    public void setWarningMsg2(String warningMsg2) {
        this.warningMsg2 = warningMsg2;
    }
    /**
     * 集計区分:エリア大ラベル取得処理
     * 
     * 作成日:2012/12/26 xkinu「販社」→「MSC」へ変更対応に伴い新規追加
     * @return
     */
    public String getLabelAreaDai() {
    	return AbstShukeiKbn.LABEL_IN_RC;
    }
}