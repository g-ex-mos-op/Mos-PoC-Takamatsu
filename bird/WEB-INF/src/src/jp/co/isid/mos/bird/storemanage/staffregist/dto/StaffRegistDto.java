/*
 * 作成日: 2006/05/23
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.dto;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaff;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.MstStaffMise;
import jp.co.isid.mos.bird.storemanage.staffregist.util.StaffRegistUtil;

/**
 * @author xyuchida
 */
public class StaffRegistDto {

    private String companyCd;
    private String onerCd;
    private int staffIndex;
    private MstStaff mstStaff;
    private MstStaff mstStaffTmp;
    private List mstStaffList;
    private List mstStaffMiseList;
    private boolean leaveReturnFlg;     //休職日復職日表示フラグ
    // 氏名、フリガナ、性別、生年月日を編集できるか
    private boolean specificItemEdit;
    
    /**
     * 退職者フラグ
     * ※ 0：退職者を含まない 1：含む
     */
    private String retireFlg = "0";
    
    /**
     * スタッフ活動プルダウンリスト
     * ※MHAユーザのみ「休職中」を含む
     * @return
     */
    private List situationKbnList;
 
    /**
     * 同姓同名確認アラート出力判断値
     * ON:未了承、OFF:了承済み
     */
    private String duplicateNameAlert = StaffRegistUtil.ALERT_OFF;    
    
    /**
     * 同姓同名存在判断値
     * false:存在無し、true:存在有り
     */
    private boolean duplicateName = false;    
    
	public List getSituationKbnList() {
        return situationKbnList;
    }
    public void setSituationKbnList(List situationKbnList) {
        this.situationKbnList = situationKbnList;
    }
    public String getCompanyCd() {
        return companyCd;
    }
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    public String getOnerCd() {
        return onerCd;
    }
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
    public int getStaffIndex() {
        return staffIndex;
    }
    public void setStaffIndex(int staffIndex) {
        this.staffIndex = staffIndex;
    }
    public MstStaff getMstStaff() {
        return mstStaff;
    }
    public void setMstStaff(MstStaff mstStaff) {
        this.mstStaff = mstStaff;
    }
    public MstStaff getMstStaffTmp() {
        return mstStaffTmp;
    }
    public void setMstStaffTmp(MstStaff mstStaffTmp) {
        this.mstStaffTmp = mstStaffTmp;
    }
    public List getMstStaffList() {
        return mstStaffList;
    }
    public void setMstStaffList(List mstStaffList) {
        this.mstStaffList = mstStaffList;
    }
    public List getMstStaffMiseList() {
        return mstStaffMiseList;
    }
    public void setMstStaffMiseList(List mstStaffMiseList) {
        this.mstStaffMiseList = mstStaffMiseList;
    }
    public int getMstStaffListSize() {
        return (getMstStaffList() == null) ? 0 : getMstStaffList().size(); 
    }
    public String getMiseName() {
        String miseName = null;
        List mstStaffMiseList = getMstStaffMiseList();
        if (mstStaffMiseList != null) {
            for (Iterator it = mstStaffMiseList.iterator(); it.hasNext();) {
                MstStaffMise mstStaffMise = (MstStaffMise) it.next();
                if (mstStaffMise.getMise_cd().equals(getMstStaff().getMiseCd1())) {
                    miseName = mstStaffMise.getMiseNameKjWithClose();
                    break;
                }
            }
        }
        return miseName;
    }
    public String getRetireFlg() {
        return retireFlg;
    }
    public void setRetireFlg(String retireFlg) {
        this.retireFlg = retireFlg;
    }

    public boolean isExistExceptRetireStaff() {
        boolean result = false;
        if (getMstStaffList() != null) {
            for (Iterator it = getMstStaffList().iterator(); it.hasNext();) {
                MstStaff mstStaff = (MstStaff) it.next();
                if (!mstStaff.getSituationKbn().equals("2")) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean isLeaveReturnFlg() {
        return this.leaveReturnFlg;
    }
    public void setLeaveReturnFlg(boolean leaveReturnFlg) {
        this.leaveReturnFlg = leaveReturnFlg;
    }
    public boolean isSpecificItemEdit() {
        return specificItemEdit;
    }
    public void setSpecificItemEdit(boolean specificItemEdit) {
        this.specificItemEdit = specificItemEdit;
    }
    /**
	 * 同姓同名了承確認アラート判断値取得処理
	 * 
	 * 9:未了承　1:了承済
	 */
	public String getDuplicateNameAlert() {
		return duplicateNameAlert;
	}
	/**
	 * 同姓同名了承確認アラート判断値設定処理
	 * 
	 * 9:未了承　1:了承済
	 * @param flg
	 */
	public void setDuplicateNameAlert(String flg) {
		this.duplicateNameAlert = flg;
	}
	public boolean isDuplicateName() {
		return duplicateName;
	}
	public void setDuplicateName(boolean duplicateName) {
		this.duplicateName = duplicateName;
	}
}
