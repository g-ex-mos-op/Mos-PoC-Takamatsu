package jp.co.isid.mos.bird.storemanage.mlresultregist.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.EntryDate;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnAbilityCheckResult;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnEntryStatus;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.UICsvRecord;

import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 * マスターライセンス結果登録DTO
 */
public class MlResultRegistDto {

    /************
     *   共通   * 
     ************/
    //ユーザータイプコード
    private String userTypeCd;
    //ログインユーザーID
    private String loginUserId;
    //ログインユーザー名称
    private String loginUserName;
    //システム日付
    private String sysDate;
    //対象エントリー情報
    private EntryDate entryDate;
    //条件画面から各編集画面への遷移フラグ
    private boolean bCondToEditFlg;
    
    /************
     * 条件画面 * 
     ************/
    /* 検索条件 */
    //検索条件ラジオ
    private String condType;
    //会社コード
    private String condCompanyCd;
    //支部
    private String condSibuCd;
    //オーナーコード
    private String condOnerCd;
    //店コード
    private String condMiseCd;
    //エントリー者のみ表示
    private boolean condOnlyEntry;
    //対象
    private int condTarget;
    //対象科目
    private String condTargetKamoku;
    //エントリーコード
    private String entryCd;
    //エントリー年
    private String entryYear;
    //エントリー回
    private String entryKai;

    //支部コードリスト
    private List listSibu;
    //対象一覧リスト
    private List listTarget;
    //条件画面クリアフラグ
    private boolean clearCond;
    
    //一括取込可能
    private boolean ikkatuTorikomiFlg;
    /** 設定ライセンス取得年 */
    private String licenseYear;
    /** 設定ライセンス取得月 */
    private String licenseMonth;
    
    /****************
     * 能力チェック * 
     ****************/
    //対象者選択Index
    private int selectedAbilityCheckIndex;
    //対象者一覧
    private List listAbilitiyStaffList;
    //選択スタッフID
    private String selectAbilityCheckStaffId;
    //選択したスタッフのエントリー状況
    private TrnEntryStatus selectAbilityCechkStaffEntryStatus;
    //マスターライセンス能力チェック結果（カテゴリー結果）
    private TrnAbilityCheckResult trnAbilityCheckCategoryResult;
    //前回カテゴリー結果
    private TrnAbilityCheckResult trnAbilityCheckLastCategoryResult;
    //今回カテゴリー結果(編集画面表示用)
    private TrnAbilityCheckResult trnAbilityCheckViewCategoryResult;
    //マスターライセンス能力チェック明細結果
    private List abilityCheckDetailResultList;
    //マスターラインセンス能力チェックカテゴリー情報
    private List abilityCheckCategoryInfoList;
    //能力チェック結果
    private String abilityCheckResult;
    //選択タブIndex
    private int selectedTabIndex;

    /***********
     * CSV取込 * 
     ***********/
    private UploadedFile uploadedFile;
    private List csvRecordList;

    /************
     *   面接   * 
     ************/
    //面接情報一覧
    private List listInterviewResult;
    //面接マスタ情報
    private List listMstInterview;
    
    public String getCondCompanyCd() {
        return condCompanyCd;
    }
    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }
    public String getCondMiseCd() {
        return condMiseCd;
    }
    public void setCondMiseCd(String condMiseCd) {
        this.condMiseCd = condMiseCd;
    }
    public String getCondOnerCd() {
        return condOnerCd;
    }
    public void setCondOnerCd(String condOnerCd) {
        this.condOnerCd = condOnerCd;
    }
    public boolean isCondOnlyEntry() {
        return condOnlyEntry;
    }
    public void setCondOnlyEntry(boolean condOnlyEntry) {
        this.condOnlyEntry = condOnlyEntry;
    }
    public String getCondSibuCd() {
        return condSibuCd;
    }
    public void setCondSibuCd(String condSibuCd) {
        this.condSibuCd = condSibuCd;
    }
    public int getCondTarget() {
        return condTarget;
    }
    public void setCondTarget(int condTarget) {
        this.condTarget = condTarget;
    }
    public String getCondTargetKamoku() {
        return condTargetKamoku;
    }
    public void setCondTargetKamoku(String condTargetKamoku) {
        this.condTargetKamoku = condTargetKamoku;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public String getCondType() {
        return condType;
    }
    public void setCondType(String condType) {
        this.condType = condType;
    }
    public List getListSibu() {
        return listSibu;
    }
    public void setListSibu(List listSibu) {
        this.listSibu = listSibu;
    }
    public boolean isClearCond() {
        return clearCond;
    }
    public void setClearCond(boolean clearCond) {
        this.clearCond = clearCond;
    }
    public List getListTarget() {
        return listTarget;
    }
    public void setListTarget(List listTarget) {
        this.listTarget = listTarget;
    }
    public boolean isEmptyListTarget() {
        return getListTarget() == null || getListTarget().isEmpty();
    }
    public String getEntryCd() {
        return entryCd;
    }
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    public String getEntryKai() {
        return entryKai;
    }
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    public String getEntryYear() {
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    public List getListAbilitiyStaffList() {
        return listAbilitiyStaffList;
    }
    public void setListAbilitiyStaffList(List listAbilitiyStaffList) {
        this.listAbilitiyStaffList = listAbilitiyStaffList;
    }
    public int getSelectedAbilityCheckIndex() {
        return selectedAbilityCheckIndex;
    }
    public void setSelectedAbilityCheckIndex(int selectedAbilityCheckIndex) {
        this.selectedAbilityCheckIndex = selectedAbilityCheckIndex;
    }
    public String getSelectAbilityCheckStaffId() {
        return selectAbilityCheckStaffId;
    }
    public void setSelectAbilityCheckStaffId(String selectAbilityCheckStaffId) {
        this.selectAbilityCheckStaffId = selectAbilityCheckStaffId;
    }
    public String getLoginUserId() {
        return loginUserId;
    }
    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }
    public String getLoginUserName() {
        return loginUserName;
    }
    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }
    public String getSysDate() {
        return sysDate;
    }
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    public TrnEntryStatus getSelectAbilityCechkStaffEntryStatus() {
        return selectAbilityCechkStaffEntryStatus;
    }
    public void setSelectAbilityCechkStaffEntryStatus(
            TrnEntryStatus selectAbilityCechkStaffEntryStatus) {
        this.selectAbilityCechkStaffEntryStatus = selectAbilityCechkStaffEntryStatus;
    }
    public EntryDate getEntryDate() {
        return entryDate;
    }
    public void setEntryDate(EntryDate entryDate) {
        this.entryDate = entryDate;
    }
    public TrnAbilityCheckResult getTrnAbilityCheckCategoryResult() {
        return trnAbilityCheckCategoryResult;
    }
    public void setTrnAbilityCheckCategoryResult(
            TrnAbilityCheckResult trnAbilityCheckCategoryResult) {
        this.trnAbilityCheckCategoryResult = trnAbilityCheckCategoryResult;
    }
    public TrnAbilityCheckResult getTrnAbilityCheckLastCategoryResult() {
        return trnAbilityCheckLastCategoryResult;
    }
    public void setTrnAbilityCheckLastCategoryResult(
            TrnAbilityCheckResult trnAbilityCheckLastCategoryResult) {
        this.trnAbilityCheckLastCategoryResult = trnAbilityCheckLastCategoryResult;
    }
    public TrnAbilityCheckResult getTrnAbilityCheckViewCategoryResult() {
        return trnAbilityCheckViewCategoryResult;
    }
    public void setTrnAbilityCheckViewCategoryResult(
            TrnAbilityCheckResult trnAbilityCheckViewCategoryResult) {
        this.trnAbilityCheckViewCategoryResult = trnAbilityCheckViewCategoryResult;
    }
    public List getAbilityCheckDetailResultList() {
        return abilityCheckDetailResultList;
    }
    public void setAbilityCheckDetailResultList(
            List abilityCheckDetailResultList) {
        this.abilityCheckDetailResultList = abilityCheckDetailResultList;
    }
    public List getAbilityCheckCategoryInfoList() {
        return abilityCheckCategoryInfoList;
    }
    public void setAbilityCheckCategoryInfoList(
            List abilityCheckCategoryInfoList) {
        this.abilityCheckCategoryInfoList = abilityCheckCategoryInfoList;
    }
    public String getAbilityCheckResult() {
        return abilityCheckResult;
    }
    public void setAbilityCheckResult(String abilityCheckResult) {
        this.abilityCheckResult = abilityCheckResult;
    }
    public int getSelectedTabIndex() {
        return selectedTabIndex;
    }
    public void setSelectedTabIndex(int selectedTabIndex) {
        this.selectedTabIndex = selectedTabIndex;
    }

    public List getCsvRecordList() {
        return csvRecordList;
    }
    public void setCsvRecordList(List csvRecordList) {
        this.csvRecordList = csvRecordList;
    }
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    public boolean isExistCsvError() {
        boolean result = false;
        if (getCsvRecordList() != null) {
            for (Iterator it = getCsvRecordList().iterator(); it.hasNext();) {
                if (((UICsvRecord) it.next()).getUiCsvErrorInfo().isExistError()) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public List getListInterviewResult() {
        return listInterviewResult;
    }
    public void setListInterviewResult(List listInterviewResult) {
        this.listInterviewResult = listInterviewResult;
    }
    public List getListMstInterview() {
        return listMstInterview;
    }
    public void setListMstInterview(List listMstInterview) {
        this.listMstInterview = listMstInterview;
    }
    public boolean isCondToEditFlg() {
        return bCondToEditFlg;
    }
    public void setCondToEditFlg(boolean condToEditFlg) {
        bCondToEditFlg = condToEditFlg;
    }
    /**
     * @return ikkatuTorikomiFlg を戻します。
     */
    public boolean isIkkatuTorikomiFlg() {
        return ikkatuTorikomiFlg;
    }
    /**
     * @param ikkatuTorikomiFlg 設定する ikkatuTorikomiFlg。
     */
    public void setIkkatuTorikomiFlg(boolean ikkatuTorikomiFlg) {
        this.ikkatuTorikomiFlg = ikkatuTorikomiFlg;
    }
	/**
	 * 指定ライセンス取得月取得処理
	 * @return licenseMonth を戻します。
	 */
	public String getLicenseMonth() {
		return licenseMonth;
	}
	/**
	 * 指定ライセンス取得月設定処理
	 * @param licenseMonth 設定する licenseMonth。
	 */
	public void setLicenseMonth(String licenseMonth) {
		this.licenseMonth = licenseMonth;
	}
	/**
	 * 指定ライセンス取得年取得処理
	 * @return licenseYear を戻します。
	 */
	public String getLicenseYear() {
		return licenseYear;
	}
	/**
	 * 指定ライセンス取得年取得処理
	 * @param year を licenseYearへ設定します。
	 */
	public void setLicenseYear(String year) {
		licenseYear = year;
	}
	/**
	 * 指定ライセンス取得年月取得処理
	 * 
	 * @return
	 */
	public String getReservedLicenseDt() {
		if(getLicenseYear() != null && getLicenseMonth() != null) {
			return getLicenseYear()+getLicenseMonth();
		}
		return "";
	}
	/**
	 * 設定ライセンス取得年保持リスト取得処理
	 * 
	 * 対象マスターライセンスの開催日FORMの年含めて
	 * 向こう2年分のリストを作成します。
	 * 
	 * @return listLicenseYear を戻します。
	 */
	public List getListLicenseYear() {
		List listYear = new ArrayList(0);
		int startYear = Integer.valueOf(getEntryDate().getFromDt().substring(0,4)).intValue();
		for(int i=0; i<2; i++) {
			SelectItem item = new SelectItem();
			String value= String.valueOf(startYear+i);
			String label= value;
			item.setValue(value);
			item.setLabel(label);
			listYear.add(item);
		}
		return listYear;
	}
}
