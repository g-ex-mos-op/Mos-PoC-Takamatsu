/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.staffregist.dto;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * ダウンロード用DTO
 * 
 * 作成日:2009/08/27
 * @author xkinu
 *
 */
public class StaffDownloadDto implements CsvOutputDto {
	/**システム日付 **/
	private String sysDate;
	/** ユーザマスタ */
    private MstUser mstUser;

    private String companyCd = "00";
    
    private String companyName = "モスフードサービス";
    
    public String getSysDate() {
		return sysDate;
	}
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}
	/** システム日付年度取得処理 */
	public String getSysNendo() {
		return DateManager.getCurrentYear(getSysDate());
	}
	/** ユーザマスタ取得処理 */
	public MstUser getMstUser() {
		return mstUser;
	}
	/** ユーザマスタ設定処理 */
	public void setMstUser(MstUser mstUser) {
		this.mstUser = mstUser;
	}
	/**
	 * 会社コード取得処理
	 * @return
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * 会社コード設定処理
	 * @param companyCd
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
