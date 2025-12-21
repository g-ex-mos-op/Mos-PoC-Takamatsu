package jp.co.isid.mos.bird.storeinfo.miseref.entity;

public class MstTODlJyoho {

	public static final String TABLE = "BM75MKRI";

	public static final String miseCd_column = "MISE_CD";
	public static final String miseName_column = "MISE_NAME";
	public static final String miseCd_shinten_column = "MISE_CD_SHINTEN";
	public static final String miseCd_saishin_column = "MISE_CD_SAISHIN";
	public static final String miseCd_moto_column = "MISE_CD_MOTO";
	public static final String miseCd_saki_column = "MISE_CD_SAKI";
	public static final String onerCd_column = "ONER_CD";
	public static final String onerName_column = "ONER_NAME";
	public static final String hikituki_date_column = "HIKITUGI_DATE";
	public static final String open_date_column = "OPEN_DATE";
	public static final String close_date_column = "CLOSE_DATE";

	/**
	 * 店コード
	 */
	private String miseCd;

	/**
	 * 店名称
	 */
	private String miseName;

	/**
	 * 新店時店コード
	 */
	private String miseCdShinten;

	/**
	 * 最新店コード
	 */
	private String miseCdSaishin;

	/**
	 * 引継元店コード
	 */
	private String miseCdMoto;

	/**
	 * 引継先店コード
	 */
	private String miseCdSaki;

	/**
	 * オーナーコード
	 */
	private String onerCd;

	/**
	 * オーナー名称
	 */
	private String onerName;

	/**
	 * 引継ぎオープン日
	 */
	private String hikitugi_date;

	/**
	 * オープン日
	 */
	private String open_date;

	/**
	 * クローズ日
	 */
	private String close_date;

	/**
	 * 店コードを取得します。
	 * @return 店コード
	 */
	public String getMiseCd() {
		return miseCd;
	}

	/**
	 * 店コードを設定します。
	 * @param miseCd
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

	/**
	 * 店名称を取得します。
	 * @return 店名称
	 */
	public String getMiseName() {
		return miseName;
	}

	/**
	 * 店名称を設定します。
	 * @param miseNm
	 */
	public void setMiseName(String miseName) {
		this.miseName = miseName;
	}

	/**
	 * 新店時店コードを取得します。
	 * @return 新店時店コード
	 */
	public String getMiseCdShinten() {
		return miseCdShinten;
	}

	/**
	 * 新店時店コードを設定します。
	 * @param miseCdShinten
	 */
	public void setMiseCdShinten(String miseCdShinten) {
		this.miseCdShinten = miseCdShinten;
	}

	/**
	 * 最新店コードを取得します。
	 * @return 最新店コード
	 */
	public String getMiseCdSaishin() {
		return miseCdSaishin;
	}

	/**
	 * 最新店コードを設定します。
	 * @param miseCdSaishin
	 */
	public void setMiseCdSaishin(String miseCdSaishin) {
		this.miseCdSaishin = miseCdSaishin;
	}

	/**
	 * 引継元店コードを取得します。
	 * @return 引継元店コード
	 */
	public String getMiseCdMoto() {
		return miseCdMoto;
	}

	/**
	 * 引継元店コードを設定します。
	 * @param miseCdMoto
	 */
	public void setMiseCdMoto(String miseCdMoto) {
		this.miseCdMoto = miseCdMoto;
	}

	/**
	 * 引継先店コードを取得します。
	 * @return 引継先店コード
	 */
	public String getMiseCdSaki() {
		return miseCdSaki;
	}

	/**
	 * 引継先店コードを設定します。
	 * @param miseCdSaki
	 */
	public void setMiseCdSaki(String miseCdSaki) {
		this.miseCdSaki = miseCdSaki;
	}

	/**
	 * オーナーコードを取得します。
	 * @return オーナーコード
	 */
	public String getOnerCd() {
		return onerCd;
	}

	/**
	 * オーナーコードを設定します。
	 * @param onerCd
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}

	/**
	 * オーナー名称を取得します。
	 * @return オーナー名称
	 */
	public String getOnerName() {
		return onerName;
	}

	/**
	 * オーナー名称を設定します。
	 * @param onerNm
	 */
	public void setOnerName(String onerName) {
		this.onerName = onerName;
	}

	/**
	 * 引継ぎオープン日を取得します。
	 * @return 引継ぎオープン日
	 */
	public String getHikitugi_date() {
		return hikitugi_date;
	}

	/**
	 * 引継ぎオープン日を設定します。
	 * @param hikitugi_date
	 */
	public void setHikitugi_date(String hikitugi_date) {
		this.hikitugi_date = hikitugi_date;
	}

	/**
	 * オープン日を取得します。
	 * @return オープン日
	 */
	public String getOpen_date() {
		return open_date;
	}

	/**
	 * オープン日を設定します。
	 * @param open_date
	 */
	public void setOpen_date(String open_date) {
		this.open_date = open_date;
	}

	/**
	 * クローズ日を取得します。
	 * @return クローズ日
	 */
	public String getClose_date() {
		return close_date;
	}

	/**
	 * クローズ日を設定します。
	 * @param close_date
	 */
	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}
}
