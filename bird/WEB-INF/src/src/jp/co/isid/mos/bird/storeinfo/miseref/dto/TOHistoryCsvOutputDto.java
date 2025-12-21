package jp.co.isid.mos.bird.storeinfo.miseref.dto;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

public class TOHistoryCsvOutputDto implements CsvOutputDto {

	/** 管理会社企業コード */
	private String condCompanyCd;

	/** 店コード */
	private String condMiseCd;

	/** 店名称 */
	private String miseNm;

	/** 新店時店コード*/
	private String miseCdShinten;

	/** 最新店コード */
	private String miseCdSaishin;

	/** 引継ぎ元店コード*/
	private String miseCdMoto;

	/** 引継ぎ先店コード*/
	private String miseCdSaki;

	/** オーナーコード */
	private String onerCd;

	/** オーナー名称 */
	private String onerNm;

	/** 引継ぎオープン日 */
	private String hikitugi_date;

	/** オープン日 */
	private String open_date;

	/** クローズ日 */
	private String close_date;

	/**
	 * 管理会社企業コードを取得します。
	 * @return 管理会社企業コード
	 */
	public String getCondCompanyCd() {
		return condCompanyCd;
	}

	/**
	 * 管理会社企業コードを設定します。
	 * @param companyCd
	 */
	public void setCondCompanyCd(String companyCd) {
		this.condCompanyCd = companyCd;
	}

	/**
	 * 店コードを取得します。
	 * @return 店コード
	 */
	public String getCondMiseCd() {
		return condMiseCd;
	}

	/**
	 * 店コードを設定します。
	 * @param miseCd
	 */
	public void setCondMiseCd(String miseCd) {
		this.condMiseCd = miseCd;
	}

	/**
	 * 店名称を取得します。
	 * @return 店コード
	 */
	public String getMiseNm() {
		return miseNm;
	}

	/**
	 * 店名称を設定します。
	 * @param miseNm
	 */
	public void setMiseNm(String miseNm) {
		this.miseNm = miseNm;
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
	 * 引継ぎ元店コードを取得します。
	 * @return 引継ぎ元店コード
	 */
	public String getMiseCdMoto() {
		return miseCdMoto;
	}

	/**
	 * 引継ぎ元店コードを設定します。
	 * @param miseCdMoto
	 */
	public void setMiseCdMoto(String miseCdMoto) {
		this.miseCdMoto = miseCdMoto;
	}

	/**
	 * 引継ぎ先店コードを取得します。
	 * @return 引継ぎ先店コード
	 */
	public String getMiseCdSaki() {
		return miseCdSaki;
	}

	/**
	 * 引継ぎ先店コードを設定します。
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
	public String getOnerNm() {
		return onerNm;
	}

	/**
	 * オーナー名称を設定します。
	 * @param onerNm
	 */
	public void setOnerNm(String onerNm) {
		this.onerNm = onerNm;
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
