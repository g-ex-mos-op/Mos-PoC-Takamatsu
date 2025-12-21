/*
 * 作成日: 2006/04/12
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity;

/**
 * 
 * 更新日:2009/09/03 xkinu 本部ユーザのみ一括ダウンロードCSV項目へ"スタッフID"の追加対応
 * @author Noh
 */
public class UIMLResultStatus {
	
	public static final String TABLE = "BT32MLKR";

    public static final String onerCd_COLUMN = "ONER_CD";

    public static final String onerNameKj_COLUMN="ONER_NAME_KJ";
	
    public static final String sibuCd_COLUMN = "SIBU_CD";

    public static final String sibuName_COLUMN = "SIBU_NAME";

    public static final String miseCd_COLUMN = "MISE_CD";

	public static final String miseNameKj_COLUMN="MISE_NAME_KJ";
	
	public static final String totalLastYear_COLUMN="TOTAL_LAST_YEAR";
	
	public static final String totalLastKai_COLUMN="TOTAL_LAST_KAI";
	
	public static final String examNo_COLUMN="EXAM_NO";
	
	public static final String staffId_COLUMN="STAFF_ID";
	
	public static final String staffLNameKj_COLUMN="STAFF_L_NAME_KJ";
	
	public static final String staffFNameKj_COLUMN="STAFF_F_NAME_KJ";
	
	public static final String totalResult_COLUMN="TOTAL_RESULT";
	
	public static final String sub1Result_COLUMN="SUB1_RESULT";
	
	public static final String sub2Result_COLUMN="SUB2_RESULT";
	
	public static final String sub3Result_COLUMN="SUB3_RESULT";
	
	public static final String courseStatus_COLUMN="COURSE_STATUS";

    /**
     * オーナーコード
     */
    private String onerCd;

    /**
	 * オーナー名称（漢字）
	 */
	private String onerNameKj;

    /**
     * 支部取込コード
     */
    private String sibuCd;

    /**
     * 支部取込名称
     */
    private String sibuName;

    /**
     * 店コード
     */
    private String miseCd;

    /**
	 * 店名称（漢字）
	 */
	private String miseNameKj;
	
	/**
	 * 受験年度
	 */
	private String totalLastYear;
	
	/**
	 * 受験回
	 */
	private String totalLastKai;
	
	/**
	 * 受験番号
	 */
	private String examNo;
	
	/**
	 * スタッフID
	 * 作成日:2009/09/02 一括ダウンロード時のみスタッフID出力対応
	 */
	private String staffId;

	/**
	 * スタッフ氏　（漢字）
	 */
	private String staffLNameKj;
	
	/**
	 * スタッフ名　（漢字）
	 */
	private String staffFNameKj;
	
	/**
	 * 総合結果
	 */
	private String totalResult;
	
	/**
	 * 能力チェック  結果
	 */
	private String sub1Result;
	
	/**
	 * 筆記テスト  結果
	 */
	private String sub2Result;
	
	/**
	 * 面接  結果
	 */
	private String sub3Result;
	
	/**
	 * 修了（予定）コース状況
	 */
	private String courseStatus;
	
	/**
	 *  @return subResult データ変更
	 */
	private String subresult(String str){
		if(str.equals("0")){
			return "不合格";
		}else if(str.equals("1")){
			return "合格";
		}else if(str.equals("2")){
			return "免除";
        }else if(str.equals("3")){
            return "受験不可";
		}else if(str.equals("9")){
			return "未受験";
		}else{
			return "";
		}
		
	}
	/**
	 *  @return courseStatus データ変更
	 */
	private String courseStatusresult(String str){
		if(str.equals("0")){
			return "0";
		}else if(str.equals("1")){
			return "修了 ";
		}else if(str.equals("2")){
			return "予定";
		}else{
			return "";
		}
	}
	/**
	 *  @return courseStatus データ変更
	 */
	private String totalresult(String str){
		if(str.equals("0")){
			return "無効";
        }else if(str.equals("1")){
            return "保留";
        }else if(str.equals("2")){
            return "不合格";
		}else if(str.equals("3")){
		    return "発行待ち";
		}else if(str.equals("4")){
            return "発行済";
		}else if(str.equals("9")){
			return "未受験";
		}else{
			return "";
		}
	}
	
	/**
	 *  @return 
	 */
	public String getStaffName(){
		return staffLNameKj + "  " + staffFNameKj;
	}
	/**
	 * @return courseStatus を戻します。
	 */
	public String getCourseStatus() {
		return courseStatusresult(courseStatus);
	}
	/**
	 * @param courseStatus courseStatus を設定。
	 */
	public void setCourseStatus(String courseStatus) {
		this.courseStatus = courseStatus;
	}
	/**
	 * @return examNo を戻します。
	 */
	public String getExamNo() {
		return examNo;
	}
	/**
	 * @param examNo examNo を設定。
	 */
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
    /**
     * @return miseCd を戻します。
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * @param miseCd 設定する miseCd。
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
	/**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}
	/**
	 * @param miseNameKj miseNameKj を設定。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}
    /**
     * @return onerCd を戻します。
     */
    public String getOnerCd() {
        return onerCd;
    }
    /**
     * @param onerCd 設定する onerCd。
     */
    public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }
	/**
	 * @return onerNameKj を戻します。
	 */
	public String getOnerNameKj() {
		return onerNameKj;
	}
	/**
	 * @param onerNameKj onerNameKj を設定。
	 */
	public void setOnerNameKj(String onerNameKj) {
		this.onerNameKj = onerNameKj;
	}
    /**
     * @return sibuCd を戻します。
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * @param sibuCd 設定する sibuCd。
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    /**
     * @return sibuName を戻します。
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * @param sibuName 設定する sibuName。
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }
	/**
	 * @return staffFNameKj を戻します。
	 */
	public String getStaffFNameKj() {
		return staffFNameKj;
	}
	/**
	 * @param staffFNameKj staffFNameKj を設定。
	 */
	public void setStaffFNameKj(String staffFNameKj) {
		this.staffFNameKj = staffFNameKj;
	}
	/**
	 * @return staffLNameKj を戻します。
	 */
	public String getStaffLNameKj() {
		return staffLNameKj;
	}
	/**
	 * @param staffLNameKj staffLNameKj を設定。
	 */
	public void setStaffLNameKj(String staffLNameKj) {
		this.staffLNameKj = staffLNameKj;
	}
	/**
	 * @return sub1Result を戻します。
	 */
	public String getSub1Result() {
		return subresult(sub1Result);
	}
	/**
	 * @param sub1Result sub1Result を設定。
	 */
	public void setSub1Result(String sub1Result) {
		this.sub1Result = sub1Result;
	}
	/**
	 * @return sub2Result を戻します。
	 */
	public String getSub2Result() {
		return subresult(sub2Result);
	}
	/**
	 * @param sub2Result sub2Result を設定。
	 */
	public void setSub2Result(String sub2Result) {
		this.sub2Result = sub2Result;
	}
	/**
	 * @return sub3Result を戻します。
	 */
	public String getSub3Result() {
		return subresult(sub3Result);
	}
	/**
	 * @param sub3Result sub3Result を設定。
	 */
	public void setSub3Result(String sub3Result) {
		this.sub3Result = sub3Result;
	}
	/**
	 * @return totalLastKai を戻します。
	 */
	public String getTotalLastKai() {
		return totalLastKai;
//		return Integer.valueOf(totalLastKai).toString();
	}
	/**
	 * @param totalLastKai totalLastKai を設定。
	 */
	public void setTotalLastKai(String totalLastKai) {
		this.totalLastKai = totalLastKai;
	}
	/**
	 * @return totalLastYear を戻します。
	 */
	public String getTotalLastYear() {
		return totalLastYear;
	}
	/**
	 * @param totalLastYear totalLastYear を設定。
	 */
	public void setTotalLastYear(String totalLastYear) {
		this.totalLastYear = totalLastYear;
	}
	/**
	 * @return totalResult を戻します。
	 */
	public String getTotalResult() {
		return totalresult(totalResult);
	}
	/**
	 * @param totalResult totalResult を設定。
	 */
	public void setTotalResult(String totalResult) {
		this.totalResult = totalResult;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
}
