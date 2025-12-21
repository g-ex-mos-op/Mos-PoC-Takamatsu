/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.dto;

import jp.co.isid.mos.bird.bizsupport.energyamount.common.EnergyAmountConst;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.util.DateManager;


/**
 * 作成日:2010/05/06
 * @author xkinu
 *
 */
public class SuiiRequestDto implements CsvOutputDto {

	private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
	private String companyCd = "00";
	private String meterKbn;
	private String nendo;
	private String taishoKikan;
	private String taishoJoken;
	private String taishoCd;
	private String taishoName;

	/**
	 * @return クラス変数meterKbn を戻します。
	 */
	public String getMeterKbn() {
		return meterKbn;
	}
	/**
	 * @param meterKbn を クラス変数meterKbnへ設定します。
	 */
	public void setMeterKbn(String meterKbn) {
		this.meterKbn = meterKbn;
	}
	/**
	 * @return クラス変数nendo を戻します。
	 */
	public String getNendo() {
		return nendo;
	}
	/**
	 * @param nendo を クラス変数nendoへ設定します。
	 */
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
	/**
	 * @return クラス変数taishoCd を戻します。
	 */
	public String getTaishoCd() {
		return taishoCd;
	}
	/**
	 * @return クラス変数taishoJoken を戻します。
	 */
	public String getTaishoJoken() {
		return taishoJoken;
	}
	/**
	 * @param taishoJoken を クラス変数taishoJokenへ設定します。
	 */
	public void setTaishoJoken(String taishoJoken) {
		this.taishoJoken = taishoJoken;
	}
	/**
	 * @return クラス変数companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd を クラス変数companyCdへ設定します。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return クラス変数taishoKikan を戻します。
	 */
	public String getTaishoKikan() {
		return taishoKikan;
	}
	/**
	 * @param taishoKikan を クラス変数taishoKikanへ設定します。
	 */
	public void setTaishoKikan(String taishoKikan) {
		this.taishoKikan = taishoKikan;
	}
	/**
	 * クリア処理
	 *
	 */
	public void clear() {
		setCompanyCd(null);
		setMeterKbn(null);
		setNendo(null);
		setTaishoCd(null);
		setTaishoJoken(null);
		setTaishoKikan(null);
	}
	/**
	 * @return クラス変数taishoName を戻します。
	 */
	public String getTaishoName() {
		return taishoName;
	}
	/**
	 * @param taishoName を クラス変数taishoNameへ設定します。
	 */
	public void setTaishoName(String taishoName) {
		this.taishoName = taishoName;
	}
	/**
	 * @param taishoCd を クラス変数taishoCdへ設定します。
	 */
	public void setTaishoCd(String taishoCd) {
		this.taishoCd = taishoCd;
	}
	/**
	 * @return クラス変数birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * @return クラス変数birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}
	/**
	 * メーター区分名称取得処理
	 * @return
	 */
	public String getMeterKbnName() {
		if(!CommonUtil.isNull(getMeterKbn())) {
			if(EnergyAmountConst.METER_KBN_TENPO.equals(getMeterKbn())) {
				return EnergyAmountConst.METER_KBN_TENPO_NAME;
			}
			if(EnergyAmountConst.METER_KBN_OFFICE.equals(getMeterKbn())) {
				return EnergyAmountConst.METER_KBN_OFFICE_NAME;
			}
		}
		return "";
	}
	/**
	 * 対象年度の開始年月取得処理
	 * 
	 * @return yyyyMMをリターンします。
	 */
	public String getNendoFromYm() {
		if(!CommonUtil.isNull(getNendo())) {
			return getNendo()+"04";
		}
		return "";
	}
	/**
	 * 対象年度の開始年月取得処理
	 * 
	 * @return yyyyMMをリターンします。
	 */
	public String getNendoToYm() {
		if(!CommonUtil.isNull(getNendoFromYm())) {
			try {
				return DateManager.getNextMonth(getNendoFromYm(), 11);
			}
			catch (Exception ex) {
				//何もしない
			}
		}
		return "";
	}
	/**
	 * 対象年度の開始年月取得処理
	 * 
	 * @return yyyyMMをリターンします。
	 */
	public String getNendoLastFromYm() {
		if(!CommonUtil.isNull(getNendoFromYm())) {
			try {
				return DateManager.getPrevMonth(getNendoFromYm(), 12);
			}
			catch (Exception ex) {
				//何もしない
			}
		}
		return "";
	}
	/**
	 * 対象年度の開始年月取得処理
	 * 
	 * @return yyyyMMをリターンします。
	 */
	public String getNendoLastToYm() {
		if(!CommonUtil.isNull(getNendoFromYm())) {
			try {
				return DateManager.getPrevMonth(getNendoFromYm(), 1);
			}
			catch (Exception ex) {
				//何もしない
			}
		}
		return "";
	}
}
