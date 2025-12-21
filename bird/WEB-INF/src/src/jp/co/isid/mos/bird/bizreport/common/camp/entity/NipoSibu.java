/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.entity;


/**
 * 支部一覧用日報エンティティー
 * 
 * @author xkinu
 *
 */
public abstract class NipoSibu extends Nipo {
    public static final String honbuCd_COLUMN = "HONBU_CD";
    public static final String honbuName_COLUMN = "HONBU_NAME";
    public static final String jigyouCd_COLUMN = "JIGYOU_CD";
    public static final String jigyouName_COLUMN = "JIGYOU_NAME";
    public static final String slareaCd_COLUMN = "SLAREA_CD";
    public static final String slareaName_COLUMN = "SLAREA_NAME";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String sibuName_COLUMN = "SIBU_NAME";
    /**
     * 本部コード
     */
    private String honbuCd;
    /**
     * 本部名称
     */
    private String honbuName;
    /**
     * 事業本部コード
     */
    private String jigyouCd;
    /**
     * 事業本部名称
     */
    private String jigyouName;
    /**
     * 営業エリアコード
     */
    private String slareaCd;
    /**
     * 営業エリア名称
     */
    private String slareaName;
    /**
     * 支部コード
     */
    private String sibuCd;
    /**
     * 支部名称
     */
    private String sibuName;
	/**
	 * @return honbuCd を戻します。
	 */
	public String getHonbuCd() {
		return honbuCd;
	}

	/**
	 * @param honbuCd 設定する honbuCd。
	 */
	public void setHonbuCd(String honbuCd) {
		this.honbuCd = honbuCd;
	}

	/**
	 * @return honbuName を戻します。
	 */
	public String getHonbuName() {
		return honbuName;
	}

	/**
	 * @param honbuName 設定する honbuName。
	 */
	public void setHonbuName(String honbuName) {
		this.honbuName = honbuName;
	}

	/**
	 * @return jigyouCd を戻します。
	 */
	public String getJigyouCd() {
		return jigyouCd;
	}

	/**
	 * @param jigyouCd 設定する jigyouCd。
	 */
	public void setJigyouCd(String jigyouCd) {
		this.jigyouCd = jigyouCd;
	}

	/**
	 * @return jigyouName を戻します。
	 */
	public String getJigyouName() {
		return jigyouName;
	}

	/**
	 * @param jigyouName 設定する jigyouName。
	 */
	public void setJigyouName(String jigyouName) {
		this.jigyouName = jigyouName;
	}

	/**
	 * @return slareaCd を戻します。
	 */
	public String getSlareaCd() {
		return slareaCd;
	}

	/**
	 * @param slareaCd 設定する slareaCd。
	 */
	public void setSlareaCd(String slareaCd) {
		this.slareaCd = slareaCd;
	}

	/**
	 * @return slareaName を戻します。
	 */
	public String getSlareaName() {
		return slareaName;
	}

	/**
	 * @param slareaName 設定する slareaName。
	 */
	public void setSlareaName(String slareaName) {
		this.slareaName = slareaName;
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
	 * 支部名称取得処理
	 * @return sibuName を戻します。
	 */
	public String getSibuName() {
		return sibuName;
	}

	/**
	 * 支部名称設定処理
	 * @param sibuName 設定する sibuName。
	 */
	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}
}
