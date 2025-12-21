package jp.co.isid.mos.bird.togouser.filterregist.dto;

import java.sql.Timestamp;
import java.util.List;



import jp.co.isid.mos.bird.framework.control.BirdDateInfo;

public class BumonFilterDto {
  
    private List bumonFilterList;
    /**
     * 部門コードFrom
     */
    private List filterSortlist;
    /**
     * 部門コードFrom
     */
    private String bumonCdFrom;
    
    /**
     * 部門コードTo
     */
    private String bumonCdTo;
    
    /**
     * フィルタ適用日
     */
    private String tekiyouDt;
    
    /**
     * AD連携フラグ
     */
    private String groupCd001Rflg;
    
    /**
     * e-learning連携フラグ
     */
    private String groupCd002Rflg;
    
    /**
     * e-mossles連携フラグ
     */
    private String groupCd003Rflg;
    
    /**
     * 会計連携フラグ
     */
    private String groupCd004Rflg;
    
    /**
     * VPN連携フラグ
     */
    private String groupCd005Rflg;
    
    /**
     * マスタ管理連携フラグ
     */
    private String groupCd006Rflg;
    
    /**
     * 販売管理連携フラグ
     */
    private String groupCd007Rflg;
    
    /**
     * POS集配信連携フラグ
     */
    private String groupCd008Rflg;
    
    /**
     * ReportSurfer連携フラグ
     */
    private String groupCd009Rflg;
    
    /**
     * 直営連携フラグ
     */
    private String groupCd010Rflg;
    
    /**
     * 統合ユーザ管理連携フラグ
     */
    private String groupCd011Rflg;
    
    /**
     * 予備２
     */
    private String groupCd012Rflg;
    
    /**
     * 予備３
     */
    private String groupCd013Rflg;
    
    /**
     * 予備４
     */
    private String groupCd014Rflg;
    
    /**
     * 予備５
     */
    private String groupCd015Rflg;
    
    /**
     * 予備６
     */
    private String groupCd016Rflg;
    
    /**
     * 予備７
     */
    private String groupCd017Rflg;
    
    /**
     * 予備８
     */
    private String groupCd018Rflg;
    
    /**
     * 予備９
     */
    private String groupCd019Rflg;
    
    /**
     * 予備１０
     */
    private String groupCd020Rflg;
    
    private String groupCd;
    
    private String groupName;
    
    private String sortSeq;
    
    private String groupCdRflg;
    
    private String renkeiFlg;
    /**
     * 修正ユーザー
     */
    private String lastUser;
    
    /**
     * 修正プログラム
     */
    private String lastPgm;
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    private Timestamp lastTmsp;
    
    private BirdDateInfo birdDateInfo;
    
    private String sysDate;
    /**
     * 部門コードFromを取得します。
     * @return 部門コードFrom
     */
    public String getBumonCdFrom() {
        return bumonCdFrom;
    }
    /**
     * 部門コードFromを設定します。
     * @param bumonCdFrom 部門コードFrom
     */
    public void setBumonCdFrom(String bumonCdFrom) {
        this.bumonCdFrom = bumonCdFrom;
    }
    
    /**
     * 部門コードToを取得します。
     * @return 部門コードTo
     */
    public String getBumonCdTo() {
        return bumonCdTo;
    }
    /**
     * 部門コードToを設定します。
     * @param bumonCdTo 部門コードTo
     */
    public void setBumonCdTo(String bumonCdTo) {
        this.bumonCdTo = bumonCdTo;
    }
    
    /**
     * フィルタ適用日を取得します。
     * @return フィルタ適用日
     */
    public String getTekiyouDt() {
        return tekiyouDt;
    }
    /**
     * フィルタ適用日を設定します。
     * @param tekiyouDt フィルタ適用日
     */
    public void setTekiyouDt(String tekiyouDt) {
        this.tekiyouDt = tekiyouDt;
    }
    
    /**
     * AD連携フラグを取得します。
     * @return AD連携フラグ
     */
    public String getGroupCd001Rflg() {
        return groupCd001Rflg;
    }
    /**
     * AD連携フラグを設定します。
     * @param groupCd001Rflg AD連携フラグ
     */
    public void setGroupCd001Rflg(String groupCd001Rflg) {
        this.groupCd001Rflg = groupCd001Rflg;
    }
    
    /**
     * e-learning連携フラグを取得します。
     * @return e-learning連携フラグ
     */
    public String getGroupCd002Rflg() {
        return groupCd002Rflg;
    }
    /**
     * e-learning連携フラグを設定します。
     * @param groupCd002Rflg e-learning連携フラグ
     */
    public void setGroupCd002Rflg(String groupCd002Rflg) {
        this.groupCd002Rflg = groupCd002Rflg;
    }
    
    /**
     * e-mossles連携フラグを取得します。
     * @return e-mossles連携フラグ
     */
    public String getGroupCd003Rflg() {
        return groupCd003Rflg;
    }
    /**
     * e-mossles連携フラグを設定します。
     * @param groupCd003Rflg e-mossles連携フラグ
     */
    public void setGroupCd003Rflg(String groupCd003Rflg) {
        this.groupCd003Rflg = groupCd003Rflg;
    }
    
    /**
     * 会計連携フラグを取得します。
     * @return 会計連携フラグ
     */
    public String getGroupCd004Rflg() {
        return groupCd004Rflg;
    }
    /**
     * 会計連携フラグを設定します。
     * @param groupCd004Rflg 会計連携フラグ
     */
    public void setGroupCd004Rflg(String groupCd004Rflg) {
        this.groupCd004Rflg = groupCd004Rflg;
    }
    
    /**
     * VPN連携フラグを取得します。
     * @return VPN連携フラグ
     */
    public String getGroupCd005Rflg() {
        return groupCd005Rflg;
    }
    /**
     * VPN連携フラグを設定します。
     * @param groupCd005Rflg VPN連携フラグ
     */
    public void setGroupCd005Rflg(String groupCd005Rflg) {
        this.groupCd005Rflg = groupCd005Rflg;
    }
    
    /**
     * マスタ管理連携フラグを取得します。
     * @return マスタ管理連携フラグ
     */
    public String getGroupCd006Rflg() {
        return groupCd006Rflg;
    }
    /**
     * マスタ管理連携フラグを設定します。
     * @param groupCd006Rflg マスタ管理連携フラグ
     */
    public void setGroupCd006Rflg(String groupCd006Rflg) {
        this.groupCd006Rflg = groupCd006Rflg;
    }
    
    /**
     * 販売管理連携フラグを取得します。
     * @return 販売管理連携フラグ
     */
    public String getGroupCd007Rflg() {
        return groupCd007Rflg;
    }
    /**
     * 販売管理連携フラグを設定します。
     * @param groupCd007Rflg 販売管理連携フラグ
     */
    public void setGroupCd007Rflg(String groupCd007Rflg) {
        this.groupCd007Rflg = groupCd007Rflg;
    }
    
    /**
     * POS集配信連携フラグを取得します。
     * @return POS集配信連携フラグ
     */
    public String getGroupCd008Rflg() {
        return groupCd008Rflg;
    }
    /**
     * POS集配信連携フラグを設定します。
     * @param groupCd008Rflg POS集配信連携フラグ
     */
    public void setGroupCd008Rflg(String groupCd008Rflg) {
        this.groupCd008Rflg = groupCd008Rflg;
    }
    
    /**
     * ReportSurfer連携フラグを取得します。
     * @return ReportSurfer連携フラグ
     */
    public String getGroupCd009Rflg() {
        return groupCd009Rflg;
    }
    /**
     * ReportSurfer連携フラグを設定します。
     * @param groupCd009Rflg ReportSurfer連携フラグ
     */
    public void setGroupCd009Rflg(String groupCd009Rflg) {
        this.groupCd009Rflg = groupCd009Rflg;
    }
    
    /**
     * 直営連携フラグを取得します。
     * @return 直営連携フラグ
     */
    public String getGroupCd010Rflg() {
        return groupCd010Rflg;
    }
    /**
     * 直営連携フラグを設定します。
     * @param groupCd010Rflg 直営連携フラグ
     */
    public void setGroupCd010Rflg(String groupCd010Rflg) {
        this.groupCd010Rflg = groupCd010Rflg;
    }
    
    /**
     * 統合ユーザ管理連携フラグを取得します。
     * @return 統合ユーザ管理連携フラグ
     */
    public String getGroupCd011Rflg() {
        return groupCd011Rflg;
    }
    /**
     * 統合ユーザ管理連携フラグを設定します。
     * @param groupCd011Rflg 統合ユーザ管理連携フラグ
     */
    public void setGroupCd011Rflg(String groupCd011Rflg) {
        this.groupCd011Rflg = groupCd011Rflg;
    }
    
    /**
     * 予備２を取得します。
     * @return 予備２
     */
    public String getGroupCd012Rflg() {
        return groupCd012Rflg;
    }
    /**
     * 予備２を設定します。
     * @param groupCd012Rflg 予備２
     */
    public void setGroupCd012Rflg(String groupCd012Rflg) {
        this.groupCd012Rflg = groupCd012Rflg;
    }
    
    /**
     * 予備３を取得します。
     * @return 予備３
     */
    public String getGroupCd013Rflg() {
        return groupCd013Rflg;
    }
    /**
     * 予備３を設定します。
     * @param groupCd013Rflg 予備３
     */
    public void setGroupCd013Rflg(String groupCd013Rflg) {
        this.groupCd013Rflg = groupCd013Rflg;
    }
    
    /**
     * 予備４を取得します。
     * @return 予備４
     */
    public String getGroupCd014Rflg() {
        return groupCd014Rflg;
    }
    /**
     * 予備４を設定します。
     * @param groupCd014Rflg 予備４
     */
    public void setGroupCd014Rflg(String groupCd014Rflg) {
        this.groupCd014Rflg = groupCd014Rflg;
    }
    
    /**
     * 予備５を取得します。
     * @return 予備５
     */
    public String getGroupCd015Rflg() {
        return groupCd015Rflg;
    }
    /**
     * 予備５を設定します。
     * @param groupCd015Rflg 予備５
     */
    public void setGroupCd015Rflg(String groupCd015Rflg) {
        this.groupCd015Rflg = groupCd015Rflg;
    }
    
    /**
     * 予備６を取得します。
     * @return 予備６
     */
    public String getGroupCd016Rflg() {
        return groupCd016Rflg;
    }
    /**
     * 予備６を設定します。
     * @param groupCd016Rflg 予備６
     */
    public void setGroupCd016Rflg(String groupCd016Rflg) {
        this.groupCd016Rflg = groupCd016Rflg;
    }
    
    /**
     * 予備７を取得します。
     * @return 予備７
     */
    public String getGroupCd017Rflg() {
        return groupCd017Rflg;
    }
    /**
     * 予備７を設定します。
     * @param groupCd017Rflg 予備７
     */
    public void setGroupCd017Rflg(String groupCd017Rflg) {
        this.groupCd017Rflg = groupCd017Rflg;
    }
    
    /**
     * 予備８を取得します。
     * @return 予備８
     */
    public String getGroupCd018Rflg() {
        return groupCd018Rflg;
    }
    /**
     * 予備８を設定します。
     * @param groupCd018Rflg 予備８
     */
    public void setGroupCd018Rflg(String groupCd018Rflg) {
        this.groupCd018Rflg = groupCd018Rflg;
    }
    
    /**
     * 予備９を取得します。
     * @return 予備９
     */
    public String getGroupCd019Rflg() {
        return groupCd019Rflg;
    }
    /**
     * 予備９を設定します。
     * @param groupCd019Rflg 予備９
     */
    public void setGroupCd019Rflg(String groupCd019Rflg) {
        this.groupCd019Rflg = groupCd019Rflg;
    }
    
    /**
     * 予備１０を取得します。
     * @return 予備１０
     */
    public String getGroupCd020Rflg() {
        return groupCd020Rflg;
    }
    /**
     * 予備１０を設定します。
     * @param groupCd020Rflg 予備１０
     */
    public void setGroupCd020Rflg(String groupCd020Rflg) {
        this.groupCd020Rflg = groupCd020Rflg;
    }
    
    /**
     * 修正ユーザーを取得します。
     * @return 修正ユーザー
     */
    public String getLastUser() {
        return lastUser;
    }
    /**
     * 修正ユーザーを設定します。
     * @param lastUser 修正ユーザー
     */
    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }
    
    /**
     * 修正プログラムを取得します。
     * @return 修正プログラム
     */
    public String getLastPgm() {
        return lastPgm;
    }
    /**
     * 修正プログラムを設定します。
     * @param lastPgm 修正プログラム
     */
    public void setLastPgm(String lastPgm) {
        this.lastPgm = lastPgm;
    }
    
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを取得します。
     * @return 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public Timestamp getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 修正時ﾀｲﾑｽﾀﾝﾌﾟを設定します。
     * @param lastTmsp 修正時ﾀｲﾑｽﾀﾝﾌﾟ
     */
    public void setLastTmsp(Timestamp lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    public List getBumonFilterList() {
        return bumonFilterList;
    }
    public void setBumonFilterList(List bumonFilterList) {
        this.bumonFilterList = bumonFilterList;
    }
    public List getFilterSortlist() {
        return filterSortlist;
    }
    public void setFilterSortlist(List filterSortlist) {
        this.filterSortlist = filterSortlist;
    }
    public String getGroupCd() {
        return groupCd;
    }
    public void setGroupCd(String groupCd) {
        this.groupCd = groupCd;
    }
    public String getGroupCdRflg() {
        return groupCdRflg;
    }
    public void setGroupCdRflg(String groupCdRflg) {
        this.groupCdRflg = groupCdRflg;
    }
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getSortSeq() {
        return sortSeq;
    }
    public void setSortSeq(String sortSeq) {
        this.sortSeq = sortSeq;
    }
    public String getRenkeiFlg() {
        return renkeiFlg;
    }
    public void setRenkeiFlg(String renkeiFlg) {
        this.renkeiFlg = renkeiFlg;
    }
    
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    public String getSysDate() {
        return getBirdDateInfo().getSysDate();
    }
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public void initialize() {
        this.bumonFilterList = null;
        this.filterSortlist = null;
        this.bumonCdFrom = "";
        this.bumonCdTo = "";
        this.tekiyouDt = "";
        this.groupCd001Rflg = "";
        this.groupCd002Rflg = "";
        this.groupCd003Rflg = "";
        this.groupCd004Rflg = "";
        this.groupCd005Rflg = "";
        this.groupCd006Rflg = "";
        this.groupCd007Rflg = "";
        this.groupCd008Rflg = "";
        this.groupCd009Rflg = "";
        this.groupCd010Rflg = "";
        this.groupCd011Rflg = "";
        this.groupCd012Rflg = "";
        this.groupCd013Rflg = "";
        this.groupCd014Rflg = "";
        this.groupCd015Rflg = "";
        this.groupCd016Rflg = "";
        this.groupCd017Rflg = ""; 
        this.groupCd018Rflg = "";
        this.groupCd019Rflg = "";
        this.groupCd020Rflg = "";
        this.groupCd = "";
        this.groupName = "";
        this.sortSeq = "";
        this.groupCdRflg = "";
        this.renkeiFlg = "";
    }
}
