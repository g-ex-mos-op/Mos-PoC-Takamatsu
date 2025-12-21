package jp.co.isid.mos.bird.storeinfo.common.dto;


/**
 * 衛生情報共通DTO
 *
 * @author xlee
 */
public class EiseiCommonDto{

    /** 画面区分 */
    public static final String PGKBN_TOTAL= "TOTAL";

    /** 画面区分 */
    public static final String PGKBN_ADVISOR= "ADVISOR";


	/**
	 * 使用フラグ
	 */
	private boolean isUseDto;

    /**
     * 対象店舗
     */
    private String condTenpoCd;

    /**
     * 実施年度
     */
    private String condNendo;

    /**
     * 実施回
     */
    private String condKai;

    /**
     * 会社コード
     */
    private String companyCd;

    /**
     *
     *
     */
    public void clear() {
    	setCondTenpoCd("");
    	setCondNendo("");
    	setCondKai("");
        setCompanyCd("");
        setIsUseDto(false);
    }

    /**
     * 使用フラグを取得します。
     * @return 使用フラグ
     */
    public boolean getIsUseDto() {
		return isUseDto;
    }

    /**
     * 使用フラグを設定します。
     * @param isUseDto 使用フラグ
     */
    public void setIsUseDto(boolean isUseDto) {
        this.isUseDto = isUseDto;
    }

    /**
     * 店舗コードを取得します。
     * @return 店舗コード
     */
    public String getCondTenpoCd() {
		return condTenpoCd;
    }

    /**
     * 店舗コードを設定します。
     * @param condTenpoCd 店舗コード
     */
    public void setCondTenpoCd(String condTenpoCd) {
        this.condTenpoCd = condTenpoCd;
    }

    /**
     * 実施年度を取得します。
     * @return 実施年度
     */
    public String getCondNendo() {
		return condNendo;
    }

    /**
     * 実施年度を取得します。
     * @return condNendo　実施年度
     */
    public void setCondNendo(String condNendo) {
    	this.condNendo = condNendo;
    }

    /**
     * 実施回を取得します。
     * @return 実施回
     */
    public String getCondKai() {
		return condKai;
    }

    /**
     * 実施回を取得します。
     * @return 実施回
     */
    public void setCondKai(String condKai) {
    	this.condKai = condKai;
    }

	/**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
		return companyCd;
    }

	/**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public void setCompanyCd(String companyCd) {
    	this.companyCd = companyCd;
    }
}