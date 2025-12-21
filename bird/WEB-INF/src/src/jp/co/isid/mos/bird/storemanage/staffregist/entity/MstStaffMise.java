package jp.co.isid.mos.bird.storemanage.staffregist.entity;

/**
 * @author xylee
 */
public class MstStaffMise {
	
	public static final String TABLE = "BM01TENM";
	
	public static final String mise_cd_COLUMN = "MISE_CD";
	
	public static final String mise_name_kj_COLUMN = "MISE_NAME_KJ";

    public static final String closeFlag_COLUMN = "CLOSE_FLAG";

    /**
     * 店コード1
     */
    private String mise_cd;
   
    /**
     * 店名
     */
    private String mise_name_kj;

    /**
     * クローズフラグ
     *      = true : CLOSE店
     */
    private boolean closeFlag;

    /**
	 * @return mise_cd を戻します。
	 */
	public String getMise_cd() {
		return mise_cd;
	}
	/**
	 * @param mise_cd mise_cd を設定。
	 */
	public void setMise_cd(String mise_cd) {
		this.mise_cd = mise_cd;
	}
	/**
	 * @return mise_name_kj を戻します。
	 */
	public String getMise_name_kj() {
		return mise_name_kj;
	}
	/**
	 * @param mise_name_kj mise_name_kj を設定。
	 */
	public void setMise_name_kj(String mise_name_kj) {
		this.mise_name_kj = mise_name_kj;
	}
    /**
     * @return closeFlag を戻します。
     */
    public boolean isCloseFlag() {
        return closeFlag;
    }
    /**
     * @param closeFlag closeFlag を設定。
     */
    public void setCloseFlag(boolean closeFlag) {
        this.closeFlag = closeFlag;
    }

    public String getMiseNameKjWithClose() {
        return getMise_name_kj() + ((isCloseFlag()) ? "(CLOSE)" : "");
    }
}