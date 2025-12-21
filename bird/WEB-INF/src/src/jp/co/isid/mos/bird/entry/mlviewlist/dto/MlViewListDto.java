package jp.co.isid.mos.bird.entry.mlviewlist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

/**
 * マスターライセンス研修申込状況確認Dto
 * @author Aspac
 */
public class MlViewListDto implements DownloadDto, CsvOutputDto {

    
    public static final String onerSearch_VIEW_ID      = "BCO006V01";
    public static final String miseSearch_VIEW_ID      = "BCO008V01";
    public static final String condition_VIEW_ID       = "BEN009V01";
    
    
    /// 検索条件 ///////////////////////////////////////////////////
    
    private String onerCd;
    private String miseCd;
    
    /* ユーザータイプコード */
    private String userTypeCd;
    /* システム日付 */
    private String sysDate;
    /* システム日付の翌日日付 */
    private String sysNextDate;
    /* ユーザーID */
    private String userId;
    
    /* 企業コード */
    private String searchCompanyCd;
    /* 支部リスト */
    private List searchSibuList;
    /* 支部コード */
    private String searchSibuCd;
    /* 支部名称 */
    private String searchSibuNm;
    /* オーナーコード */
    private String searchOnerCd;
    /* オーナー名称 */
    private String searchOnerNm;
    /* 店舗コード */
    private String searchMiseCd;
    /* 店舗名称 */
    private String searchMiseNm;
    /* 選択フラグ */
    private String searchSelectFlg;
    
    
    /// ライセンス情報 /////////////////////////////////////////////
    
    /* タイトル */
    private String lisenceTitle;
    /* 実施予定期間From */
    private String lisenceCarryFrom;
    /* 実施予定期間To */
    private String lisenceCarryTo;
    /* 受付期間From */
    private String lisenceReceiptFrom;
    /* 受付期間To */
    private String lisenceReceiptTo;
    /* 申込状況 */
    private String lisenceEntryCnt;
    
    
    /// マスタライセンス一覧データ ////////////////////////////////////
    
    /* 一覧 */
    private List mlListData;
    /* 一覧件数 */
    private int mlListDataSize;
    
    /* エントリーコード */
    private String mlEntryCd;
    /* エントリー年 */
    private String mlEntryYear;
    /* エントリー回 */
    private String mlEntryKai;
    /* エントリータイトル */
    private String mlEntryTitle;
    /* 開催場所 */
    private String mlEntryPlace;
    /* 開催名 */
    private String mlEntryNameRyaku;
    /* 研修(出張/更新)開催日FROM */
    private String mlFromDt;
    /* 研修(出張/更新)開催日TO */
    private String mlToDt;
    /* 本部：登録開始日 */
    private String mlHonbuInputDtFrom;
    /* 本部：登録終了日 */
    private String mlHonbuInputDtTo;
    /* オーナー：登録開始日 */
    private String mlOnerInputDtFrom;
    /* オーナー：登録終了日 */
    private String mlOnerInputDtTo;
    /* 定員 */
    private String mlNumberLimit;
    /* 申込人数 */
    private String mlEntryNum;
    /* 備考 */
    private String note;
    /* 選択ラジオボタンIndex */
    private String indexFlg;
    
    /// マスターライセンスエントリー一覧データ ////////////////////////////////////
    
    /* エントリーリスト */
    private List entryList;
    /* エントリー件数 */
    private int entryListSize;
    
    /* エントリーコード */
    private String entryCd;
    /* エントリー年 */
    private String entryYear;
    /* エントリー回 */
    private String entryKai;
    /* オーナーコード */
    private String entryOnerCd;
    /* オーナー名称 */
    private String entryOnerNameKj;
    /* 店コード */
    private String entryMiseCd;
    /* 店名称 */
    private String entryMiseNameKj;
    /* スタッフID */
    private String entryStaffId;
    /* スタッフ名称(受験者氏名) */
    private String entryStaffNameKj;
    /* 受験番号 */
    private String entryExamNo;
    /* 受験希望地 */
    private String entryPlaceName;
    /* 能力フラグ */
    private boolean entryAbilityChk;
    /* 筆記フラグ */
    private boolean entryExamChk;
    /* 面接フラグ */
    private boolean entryInterviewChk;
    /* 研修状況 */
    private String entryCourseStatus;
    /* オーナー契約フラグ */
    private String entryKeiyakuFlg;
    
    /* 受験番号採番機能使用可否 true:使用可 */
    private boolean executableSaiban;
    
    
    /// Getter & Setter ////////////////////////////////////
    
    public boolean isEntryAbilityChk() {
        return entryAbilityChk;
    }
    public void setEntryAbilityChk(boolean entryAbilityChk) {
        this.entryAbilityChk = entryAbilityChk;
    }
    public String getEntryCd() {
        return entryCd;
    }
    public void setEntryCd(String entryCd) {
        this.entryCd = entryCd;
    }
    public String getEntryCourseStatus() {
        return entryCourseStatus;
    }
    public void setEntryCourseStatus(String entryCourseStatus) {
        this.entryCourseStatus = entryCourseStatus;
    }
    public boolean isEntryExamChk() {
        return entryExamChk;
    }
    public void setEntryExamChk(boolean entryExamChk) {
        this.entryExamChk = entryExamChk;
    }
    public String getEntryExamNo() {
        return entryExamNo;
    }
    public void setEntryExamNo(String entryExamNo) {
        this.entryExamNo = entryExamNo;
    }
    public boolean isEntryInterviewChk() {
        return entryInterviewChk;
    }
    public void setEntryInterviewChk(boolean entryInterviewChk) {
        this.entryInterviewChk = entryInterviewChk;
    }
    public String getEntryKai() {
        return entryKai;
    }
    public void setEntryKai(String entryKai) {
        this.entryKai = entryKai;
    }
    public String getEntryKeiyakuFlg() {
        return entryKeiyakuFlg;
    }
    public void setEntryKeiyakuFlg(String entryKeiyakuFlg) {
        this.entryKeiyakuFlg = entryKeiyakuFlg;
    }
    public List getEntryList() {
        return entryList;
    }
    public void setEntryList(List entryList) {
        this.entryList = entryList;
    }
    public int getEntryListSize() {
        if(this.entryList != null) return this.entryList.size();
        else return 0;
    }
    public void setEntryListSize() {
        if(this.entryList != null) this.entryListSize = this.entryList.size();
        else this.entryListSize = 0;
    }
    public String getEntryMiseCd() {
        return entryMiseCd;
    }
    public void setEntryMiseCd(String entryMiseCd) {
        this.entryMiseCd = entryMiseCd;
    }
    public String getEntryMiseNameKj() {
        return entryMiseNameKj;
    }
    public void setEntryMiseNameKj(String entryMiseNameKj) {
        this.entryMiseNameKj = entryMiseNameKj;
    }
    public String getEntryOnerCd() {
        return entryOnerCd;
    }
    public void setEntryOnerCd(String entryOnerCd) {
        this.entryOnerCd = entryOnerCd;
    }
    public String getEntryOnerNameKj() {
        return entryOnerNameKj;
    }
    public void setEntryOnerNameKj(String entryOnerNameKj) {
        this.entryOnerNameKj = entryOnerNameKj;
    }
    public String getEntryPlaceName() {
        return entryPlaceName;
    }
    public void setEntryPlaceName(String entryPlaceName) {
        this.entryPlaceName = entryPlaceName;
    }
    public String getEntryStaffId() {
        return entryStaffId;
    }
    public void setEntryStaffId(String entryStaffId) {
        this.entryStaffId = entryStaffId;
    }
    public String getEntryStaffNameKj() {
        return entryStaffNameKj;
    }
    public void setEntryStaffNameKj(String entryStaffNameKj) {
        this.entryStaffNameKj = entryStaffNameKj;
    }
    public String getEntryYear() {
        return entryYear;
    }
    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }
    public String getIndexFlg() {
        return indexFlg;
    }
    public void setIndexFlg(String indexFlg) {
        this.indexFlg = indexFlg;
    }
    public String getLisenceCarryFrom() {
        return lisenceCarryFrom;
    }
    public void setLisenceCarryFrom(String lisenceCarryFrom) {
        this.lisenceCarryFrom = lisenceCarryFrom;
    }
    public String getLisenceCarryTo() {
        return lisenceCarryTo;
    }
    public void setLisenceCarryTo(String lisenceCarryTo) {
        this.lisenceCarryTo = lisenceCarryTo;
    }
    public String getLisenceEntryCnt() {
        return lisenceEntryCnt;
    }
    public void setLisenceEntryCnt(String lisenceEntryCnt) {
        this.lisenceEntryCnt = lisenceEntryCnt;
    }
    public String getLisenceReceiptFrom() {
        return lisenceReceiptFrom;
    }
    public void setLisenceReceiptFrom(String lisenceReceiptFrom) {
        this.lisenceReceiptFrom = lisenceReceiptFrom;
    }
    public String getLisenceReceiptTo() {
        return lisenceReceiptTo;
    }
    public void setLisenceReceiptTo(String lisenceReceiptTo) {
        this.lisenceReceiptTo = lisenceReceiptTo;
    }
    public String getLisenceTitle() {
        return lisenceTitle;
    }
    public void setLisenceTitle(String lisenceTitle) {
        this.lisenceTitle = lisenceTitle;
    }
    public String getMlEntryCd() {
        return mlEntryCd;
    }
    public void setMlEntryCd(String mlEntryCd) {
        this.mlEntryCd = mlEntryCd;
    }
    public String getMlEntryKai() {
        return mlEntryKai;
    }
    public void setMlEntryKai(String mlEntryKai) {
        this.mlEntryKai = mlEntryKai;
    }
    public String getMlEntryNameRyaku() {
        return mlEntryNameRyaku;
    }
    public void setMlEntryNameRyaku(String mlEntryNameRyaku) {
        this.mlEntryNameRyaku = mlEntryNameRyaku;
    }
    public String getMlEntryNum() {
        return mlEntryNum;
    }
    public void setMlEntryNum(String mlEntryNum) {
        this.mlEntryNum = mlEntryNum;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getMlEntryPlace() {
        return mlEntryPlace;
    }
    public void setMlEntryPlace(String mlEntryPlace) {
        this.mlEntryPlace = mlEntryPlace;
    }
    public String getMlEntryTitle() {
        return mlEntryTitle;
    }
    public void setMlEntryTitle(String mlEntryTitle) {
        this.mlEntryTitle = mlEntryTitle;
    }
    public String getMlEntryYear() {
        return mlEntryYear;
    }
    public void setMlEntryYear(String mlEntryYear) {
        this.mlEntryYear = mlEntryYear;
    }
    public String getMlFromDt() {
        return mlFromDt;
    }
    public void setMlFromDt(String mlFromDt) {
        this.mlFromDt = mlFromDt;
    }
    public String getMlHonbuInputDtFrom() {
        return mlHonbuInputDtFrom;
    }
    public void setMlHonbuInputDtFrom(String mlHonbuInputDtFrom) {
        this.mlHonbuInputDtFrom = mlHonbuInputDtFrom;
    }
    public String getMlHonbuInputDtTo() {
        return mlHonbuInputDtTo;
    }
    public void setMlHonbuInputDtTo(String mlHonbuInputDtTo) {
        this.mlHonbuInputDtTo = mlHonbuInputDtTo;
    }
    public List getMlListData() {
        return mlListData;
    }
    public void setMlListData(List mlListData) {
        this.mlListData = mlListData;
    }
    public int getMlListDataSize() {
        if(this.mlListData != null) return this.mlListData.size();
        else return 0;
    }
    public void setMlListDataSize() {
        if(this.mlListData != null) this.mlListDataSize = this.mlListData.size();
        else this.mlListDataSize = 0;
    }
    public String getMlNumberLimit() {
        return mlNumberLimit;
    }
    public void setMlNumberLimit(String mlNumberLimit) {
        this.mlNumberLimit = mlNumberLimit;
    }
    public String getMlOnerInputDtFrom() {
        return mlOnerInputDtFrom;
    }
    public void setMlOnerInputDtFrom(String mlOnerInputDtFrom) {
        this.mlOnerInputDtFrom = mlOnerInputDtFrom;
    }
    public String getMlOnerInputDtTo() {
        return mlOnerInputDtTo;
    }
    public void setMlOnerInputDtTo(String mlOnerInputDtTo) {
        this.mlOnerInputDtTo = mlOnerInputDtTo;
    }
    public String getMlToDt() {
        return mlToDt;
    }
    public void setMlToDt(String mlToDt) {
        this.mlToDt = mlToDt;
    }
    public String getSearchCompanyCd() {
        return searchCompanyCd;
    }
    public void setSearchCompanyCd(String searchCompanyCd) {
        this.searchCompanyCd = searchCompanyCd;
    }
    public String getSearchMiseCd() {
        return searchMiseCd;
    }
    public void setSearchMiseCd(String searchMiseCd) {
        this.searchMiseCd = searchMiseCd;
    }
    public String getSearchOnerCd() {
        return searchOnerCd;
    }
    public void setSearchOnerCd(String searchOnerCd) {
        this.searchOnerCd = searchOnerCd;
    }
    public String getSearchSelectFlg() {
        return searchSelectFlg;
    }
    public void setSearchSelectFlg(String searchSelectFlg) {
        this.searchSelectFlg = searchSelectFlg;
    }
    public String getSearchSibuCd() {
        return searchSibuCd;
    }
    public void setSearchSibuCd(String searchSibuCd) {
        this.searchSibuCd = searchSibuCd;
    }
    public List getSearchSibuList() {
        return searchSibuList;
    }
    public void setSearchSibuList(List searchSibuList) {
        this.searchSibuList = searchSibuList;
    }
    public String getSysDate() {
        return sysDate;
    }
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }
    public String getSysNextDate() {
        return sysNextDate;
    }
    public void setSysNextDate(String sysNextDate) {
        this.sysNextDate = sysNextDate;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserTypeCd() {
        return userTypeCd;
    }
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    public String getSearchMiseNm() {
        return searchMiseNm;
    }
    public void setSearchMiseNm(String searchMiseNm) {
        this.searchMiseNm = searchMiseNm;
    }
    public String getSearchOnerNm() {
        return searchOnerNm;
    }
    public void setSearchOnerNm(String searchOnerNm) {
        this.searchOnerNm = searchOnerNm;
    }
    public String getSearchSibuNm() {
        return searchSibuNm;
    }
    public void setSearchSibuNm(String searchSibuNm) {
        this.searchSibuNm = searchSibuNm;
    }
    public boolean isExecutableSaiban() {
        return executableSaiban;
    }
    public void setExecutableSaiban(boolean executableSaiban) {
        this.executableSaiban = executableSaiban;
    }

}
