/*
 * 作成日:2007/01/18
 */
package jp.co.isid.mos.bird.entry.ownerchange.dto;

import java.util.List;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.entry.ownerchange.entity.MstStaffInfo;

/**
 * オーナー間異動登録Dto
 * @author xkonishi
 *
 */
public class OwnerChangeDto extends CommonDto {

    /**
     * 会社コード
     */
    private String companyCd;

    /**
     * 会社名称
     */
    private String companyName;

    /**
     * 会社リスト
     */
    private List companyList;
    
    /**
     * 条件区分
     */
    private String kbn;
    
    /**
     * オーナーコード
     */
    private String onerCd;

    /**
     * オーナー名
     */
    private String onerNameKj;

    /**
     * 表示用オーナーコード
     */
    private String dispOnerCd;

    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * スタッフ情報リスト
     */
    private List staffInfoList;

    /**
     * 対象スタッフ情報
     */
    private MstStaffInfo mstStaffInfo;

    /**
     * 異動日
     */
    private String moveDt;

    /**
     * 変更後オーナーコード
     */
    private String changeOnerCd;
    
    /**
     * 変更後オーナー名称
     */
    private String changeOnerName;
    
    /**
     * 変更後店コード
     */
    private String changeMiseCd;
    
    /**
     * 変更後店名称
     */
    private String changeMiseName;
    
    /**
     * 変更後店舗リスト
     */
    private List miseList;



    public String getOnerNameKj() {
        return onerNameKj;
    }

    public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj = onerNameKj;
    }

    public List getCompanyList() {
        return companyList;
    }
    
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getKbn() {
        return kbn;
    }

    public void setKbn(String kbn) {
        this.kbn = kbn;
    }

    public String getMiseCd() {
        return miseCd;
    }

    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    public String getOnerCd() {
        return onerCd;
    }

    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

    public String getCompanyCd() {
        return companyCd;
    }

    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }

    public MstStaffInfo getMstStaffInfo() {
        return mstStaffInfo;
    }

    public void setMstStaffInfo(MstStaffInfo mstStaffInfo) {
        this.mstStaffInfo = mstStaffInfo;
    }

    public List getStaffInfoList() {
        return staffInfoList;
    }

    public void setStaffInfoList(List staffInfoList) {
        this.staffInfoList = staffInfoList;
    }

    public String getChangeOnerCd() {
        return changeOnerCd;
    }

    public void setChangeOnerCd(String changeOnerCd) {
        this.changeOnerCd = changeOnerCd;
    }

    public String getChangeOnerName() {
        return changeOnerName;
    }

    public void setChangeOnerName(String changeOnerName) {
        this.changeOnerName = changeOnerName;
    }

    public List getMiseList() {
        return miseList;
    }

    public void setMiseList(List miseList) {
        this.miseList = miseList;
    }

    public String getChangeMiseCd() {
        return changeMiseCd;
    }

    public void setChangeMiseCd(String changeMiseCd) {
        this.changeMiseCd = changeMiseCd;
    }

    public String getChangeMiseName() {
        return changeMiseName;
    }

    public void setChangeMiseName(String changeMiseName) {
        this.changeMiseName = changeMiseName;
    }

    public String getDispOnerCd() {
        return dispOnerCd;
    }

    public void setDispOnerCd(String dispOnerCd) {
        this.dispOnerCd = dispOnerCd;
    }

    public String getMoveDt() {
        return moveDt;
    }

    public void setMoveDt(String moveDt) {
        this.moveDt = moveDt;
    }
}