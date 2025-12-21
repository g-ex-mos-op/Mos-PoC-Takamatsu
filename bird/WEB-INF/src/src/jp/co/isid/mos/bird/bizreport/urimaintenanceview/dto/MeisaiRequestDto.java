/*
 * 作成日:2012/08/09
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto;

/**
 * 売上修正確認明細・DTOクラス
 * 
 * @author xkawa
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
	 * 会社名称
	 */
	private String companyName;

    /**
     * 修正日
     */
    private String syuseiDate;

	/**
	 * 集計区分
	 */
	private String syukeiCd;

	/**
	 * 集計区分名称
	 */
	private String syukeiNm;


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
     * 会社名称を戻します。
     * @return companyName 会社名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 会社名称を設定します。
     * @param companyName 会社名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 修正日を戻します。
     * @return syuseiDate 修正日
     */
    public String getSyuseiDate() {
        return syuseiDate;
    }

    /**
     * 修正日を設定します。
     * @param syuseiDate 修正日
     */
    public void setSyuseiDate(String syuseiDate) {
        this.syuseiDate = syuseiDate;
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

}
