package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto;

/**
 * 売上金管理月報明細・DTOクラス
 * 
 * @author xogawa
 */
public abstract class MeisaiRequestDto {

	/**
	 * ウインドウID
	 */
	private int windowId;

	/**
	 * 会社コード
	 */
	private String companyCd;

	/**
	 * 対象店舗コード
	 */
	private String taishoTenpoCd;

	/**
	 * 対象年月
	 */
	private String taishoYM;

	/**
	 * 集計区分
	 */
	private String syukeiCd;

	/**
	 * 集計区分名称
	 */
	private String syukeiNm;

	/**
	 * 対象店舗名称
	 */
	private String taishoTenpo;
	
	/**
	 * タブNo
	 */
	private String tabNo;


	/**
	 * ウインドウIDを取得する
	 * @return windowId ウインドウID
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * ウインドウIDを設定する
	 * @param windowId ウインドウID
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**
	 * 会社コードを取得する
	 * @return companyCd 会社コード
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * 会社コードを設定する
	 * @param companyCd 会社コード
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * 対象店舗コードを取得する
	 * @return taishoTenpoCd 対象店舗コード
	 */
	public String getTaishoTenpoCd() {
		return taishoTenpoCd;
	}

	/**
	 * 対象店舗コードを設定する
	 * @param taishoTenpoCd 対象店舗コード
	 */
	public void setTaishoTenpoCd(String taishoTenpoCd) {
		this.taishoTenpoCd = taishoTenpoCd;
	}

	/**
	 * 対象年月を取得する
	 * @return taishoYM 対象年月
	 */
	public String getTaishoYM() {
		return taishoYM;
	}

	/**
	 * 対象年月を設定する
	 * @param taishoYM 対象年月
	 */
	public void setTaishoYM(String taishoYM) {
		this.taishoYM = taishoYM;
	}

	/**
	 * 集計区分を取得する
	 * @return syukeiCd 集計区分
	 */
	public String getSyukeiCd() {
		return syukeiCd;
	}

	/**
	 * 集計区分を設定する
	 * @param syukeiCd 集計区分
	 */
	public void setSyukeiCd(String syukeiCd) {
		this.syukeiCd = syukeiCd;
	}

	/**
	 * 集計区分名称を取得する
	 * @return syukeiNm 集計区分名称
	 */
	public String getSyukeiNm() {
		return syukeiNm;
	}

	/**
	 * 集計区分名称を設定する
	 * @param syukeiNm 集計区分名称
	 */
	public void setSyukeiNm(String syukeiNm) {
		this.syukeiNm = syukeiNm;
	}

	/**
	 * 対象店舗名称を取得する
	 * @return taishoTenpo 対象店舗名称
	 */
	public String getTaishoTenpo() {
		return taishoTenpo;
	}

	/**
	 * 対象店舗名称を設定する
	 * @param taishoTenpo 対象店舗名称
	 */
	public void setTaishoTenpo(String taishoTenpo) {
		this.taishoTenpo = taishoTenpo;
	}

	/**
	 * タグNoを取得する
	 * @return tabNo タグNo
	 */
	public String getTabNo() {
		return tabNo;
	}

	/**
	 * タグNoを設定する
	 * @param tabNo タグNo
	 */
	public void setTabNo(String tabNo) {
		this.tabNo = tabNo;
	}

}
