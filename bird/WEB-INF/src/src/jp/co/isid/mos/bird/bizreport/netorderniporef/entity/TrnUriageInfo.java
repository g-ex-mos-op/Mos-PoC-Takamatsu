package jp.co.isid.mos.bird.bizreport.netorderniporef.entity;

import java.math.BigDecimal;

public class TrnUriageInfo {
    
    public static final String TABLE = "BD54NNIP";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String sibuCd_COLUMN = "SIBU_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String uriageNsum_COLUMN = "URIAGE_NSUM";
    
    public static final String uriageNtake_COLUMN = "URIAGE_NTAKE";
    
    public static final String uriageNtaku_COLUMN = "URIAGE_NTAKU";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String kyakusuNsum_COLUMN = "KYAKUSU_NSUM";
    
    public static final String kyakusuNtake_COLUMN = "KYAKUSU_NTAKE";
    
    public static final String kyakusuNtaku_COLUMN = "KYAKUSU_NTAKU";
    
    public static final String openKbn_COLUMN = "OPEN_KBN";
    
    public static final String miseKbnNsum_COLUMN = "MISE_KBN_NSUM";
    
    public static final String miseKbnNtake_COLUMN = "MISE_KBN_NTAKE";
    
    public static final String miseKbnNtaku_COLUMN = "MISE_KBN_NTAKU";
    
    public static final String openKbnZen_COLUMN = "OPEN_KBN_ZEN";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    
    public static final String uriageNsumZen_COLUMN = "URIAGE_NSUM_ZEN";
    
    public static final String uriageNtakeZen_COLUMN = "URIAGE_NTAKE_ZEN";
    
    public static final String uriageNtakuZen_COLUMN = "URIAGE_NTAKU_ZEN";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String kyakusuNsumZen_COLUMN = "KYAKUSU_NSUM_ZEN";
    
    public static final String kyakusuNtakeZen_COLUMN = "KYAKUSU_NTAKE_ZEN";
    
    public static final String kyakusuNtakuZen_COLUMN = "KYAKUSU_NTAKU_ZEN";
    
    public static final String onerYosan_COLUMN = "ONER_YOSAN";
    
    public static final String uriYosan_COLUMN = "URI_YOSAN";
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 売上
     */
    private BigDecimal uriage = new BigDecimal("0");
    
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 当年OPEN区分
     */
    private BigDecimal openKbn = new BigDecimal("0");
    
    /**
     * 前年OPEN区分
     */
    private BigDecimal openKbnZen = new BigDecimal("0");
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen = new BigDecimal("0");
    
    /**
     * オーナー予算
     */
    private BigDecimal onerYosan = new BigDecimal("0");
    
    /**
     * 事業計画予算
     */
    private BigDecimal uriYosan = new BigDecimal("0");
    
    /**
     * ネット注文実績店舗数
     */
    private BigDecimal miseKbnNsum = new BigDecimal("0");
    
    /**
     * ネット注文売上
     */
    private BigDecimal uriageNsum = new BigDecimal("0");
    
    /**
     * 前年ネット注文実績
     */
    private BigDecimal uriageNsumZen = new BigDecimal("0");
    
    /**
     * ネット注文件数
     */
    private BigDecimal kyakusuNsum = new BigDecimal("0");
    
    /**
     * 前年ネット注文件数
     */
    private BigDecimal kyakusuNsumZen = new BigDecimal("0");
    
    /**
     * ネットテイク実績店舗数
     */
    private BigDecimal miseKbnNtake = new BigDecimal("0");
    
    /**
     * ネットテイク売上
     */
    private BigDecimal uriageNtake = new BigDecimal("0");
    
    /**
     * 前年ネットテイク実績
     */
    private BigDecimal uriageNtakeZen = new BigDecimal("0");
    
    /**
     * ネットテイク件数
     */
    private BigDecimal kyakusuNtake = new BigDecimal("0");
    
    /**
     * 前年ネットテイク件数
     */
    private BigDecimal kyakusuNtakeZen = new BigDecimal("0");
    
    /**
     * ネット宅配実績店舗数
     */
    private BigDecimal miseKbnNtaku = new BigDecimal("0");
    
    /**
     * ネット宅配売上
     */
    private BigDecimal uriageNtaku = new BigDecimal("0");
    
    /**
     * 前年ネット宅配実績
     */
    private BigDecimal uriageNtakuZen = new BigDecimal("0");
    
    /**
     * ネット宅配件数
     */
    private BigDecimal kyakusuNtaku = new BigDecimal("0");
    
    /**
     * 前年ネット宅配件数
     */
    private BigDecimal kyakusuNtakuZen = new BigDecimal("0");
    
    /**
     * 管理会社企業コードを取得します。
     * @return 管理会社企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 管理会社企業コードを設定します。
     * @param companyCd 管理会社企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * 支部コードを取得します。
     * @return 支部コード
     */
    public String getSibuCd() {
        return sibuCd;
    }
    /**
     * 支部コードを設定します。
     * @param sibuCd 支部コード
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd = sibuCd;
    }
    
    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return miseCd;
    }
    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }
    
    /**
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 売上を取得します。
     * @return 売上
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上を設定します。
     * @param uriage 売上
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getKyakusu() {
        return kyakusu;
    }
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(BigDecimal kyakusu) {
        this.kyakusu = kyakusu;
    }
    
    /**
     * 当年OPEN区分を取得します。
     * @return 当年OPEN区分
     */
    public BigDecimal getOpenKbn() {
        return openKbn;
    }
    /**
     * 当年OPEN区分を設定します。
     * @param openKbn 当年OPEN区分
     */
    public void setOpenKbn(BigDecimal openKbn) {
        this.openKbn = openKbn;
    }
    
    /**
     * 前年OPEN区分を取得します。
     * @return 前年OPEN区分
     */
    public BigDecimal getOpenKbnZen() {
        return openKbnZen;
    }
    /**
     * 前年OPEN区分を設定します。
     * @param openKbnZen 前年OPEN区分
     */
    public void setOpenKbnZen(BigDecimal openKbnZen) {
        this.openKbnZen = openKbnZen;
    }
    
    /**
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getUriageZen() {
        return uriageZen;
    }
    /**
     * 前年売上を設定します。
     * @param uriageZen 前年売上
     */
    public void setUriageZen(BigDecimal uriageZen) {
        this.uriageZen = uriageZen;
    }
    
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getKyakusuZen() {
        return kyakusuZen;
    }
    /**
     * 前年客数を設定します。
     * @param kyakusuZen 前年客数
     */
    public void setKyakusuZen(BigDecimal kyakusuZen) {
        this.kyakusuZen = kyakusuZen;
    }
    
    /**
     * オーナー予算を取得します。
     * @return オーナー予算
     */
    public BigDecimal getOnerYosan() {
        return onerYosan;
    }
    /**
     * オーナー予算を設定します。
     * @param onerYosan オーナー予算
     */
    public void setOnerYosan(BigDecimal onerYosan) {
        this.onerYosan = onerYosan;
    }
    
    /**
     * 事業計画予算を取得します。
     * @return 事業計画予算
     */
    public BigDecimal getUriYosan() {
        return uriYosan;
    }
    /**
     * 事業計画予算を設定します。
     * @param uriYosan 事業計画予算
     */
    public void setUriYosan(BigDecimal uriYosan) {
        this.uriYosan = uriYosan;
    }
	/**
	 * @return kyakusuNsum を戻します。
	 */
	public BigDecimal getKyakusuNsum() {
		return kyakusuNsum;
	}
	/**
	 * @param kyakusuNsum 設定する kyakusuNsum。
	 */
	public void setKyakusuNsum(BigDecimal kyakusuNsum) {
		this.kyakusuNsum = kyakusuNsum;
	}
	/**
	 * @return kyakusuNsumZen を戻します。
	 */
	public BigDecimal getKyakusuNsumZen() {
		return kyakusuNsumZen;
	}
	/**
	 * @param kyakusuNsumZen 設定する kyakusuNsumZen。
	 */
	public void setKyakusuNsumZen(BigDecimal kyakusuNsumZen) {
		this.kyakusuNsumZen = kyakusuNsumZen;
	}
	/**
	 * @return kyakusuNtake を戻します。
	 */
	public BigDecimal getKyakusuNtake() {
		return kyakusuNtake;
	}
	/**
	 * @param kyakusuNtake 設定する kyakusuNtake。
	 */
	public void setKyakusuNtake(BigDecimal kyakusuNtake) {
		this.kyakusuNtake = kyakusuNtake;
	}
	/**
	 * @return kyakusuNtakeZen を戻します。
	 */
	public BigDecimal getKyakusuNtakeZen() {
		return kyakusuNtakeZen;
	}
	/**
	 * @param kyakusuNtakeZen 設定する kyakusuNtakeZen。
	 */
	public void setKyakusuNtakeZen(BigDecimal kyakusuNtakeZen) {
		this.kyakusuNtakeZen = kyakusuNtakeZen;
	}
	/**
	 * @return kyakusuNtaku を戻します。
	 */
	public BigDecimal getKyakusuNtaku() {
		return kyakusuNtaku;
	}
	/**
	 * @param kyakusuNtaku 設定する kyakusuNtaku。
	 */
	public void setKyakusuNtaku(BigDecimal kyakusuNtaku) {
		this.kyakusuNtaku = kyakusuNtaku;
	}
	/**
	 * @return kyakusuNtakuZen を戻します。
	 */
	public BigDecimal getKyakusuNtakuZen() {
		return kyakusuNtakuZen;
	}
	/**
	 * @param kyakusuNtakuZen 設定する kyakusuNtakuZen。
	 */
	public void setKyakusuNtakuZen(BigDecimal kyakusuNtakuZen) {
		this.kyakusuNtakuZen = kyakusuNtakuZen;
	}
	/**
	 * @return miseKbnNsum を戻します。
	 */
	public BigDecimal getMiseKbnNsum() {
		return miseKbnNsum;
	}
	/**
	 * @param miseKbnNsum 設定する miseKbnNsum。
	 */
	public void setMiseKbnNsum(BigDecimal miseKbnNsum) {
		this.miseKbnNsum = miseKbnNsum;
	}
	/**
	 * @return miseKbnNtake を戻します。
	 */
	public BigDecimal getMiseKbnNtake() {
		return miseKbnNtake;
	}
	/**
	 * @param miseKbnNtake 設定する miseKbnNtake。
	 */
	public void setMiseKbnNtake(BigDecimal miseKbnNtake) {
		this.miseKbnNtake = miseKbnNtake;
	}
	/**
	 * @return miseKbnNtaku を戻します。
	 */
	public BigDecimal getMiseKbnNtaku() {
		return miseKbnNtaku;
	}
	/**
	 * @param miseKbnNtaku 設定する miseKbnNtaku。
	 */
	public void setMiseKbnNtaku(BigDecimal miseKbnNtaku) {
		this.miseKbnNtaku = miseKbnNtaku;
	}
	/**
	 * @return uriageNsum を戻します。
	 */
	public BigDecimal getUriageNsum() {
		return uriageNsum;
	}
	/**
	 * @param uriageNsum 設定する uriageNsum。
	 */
	public void setUriageNsum(BigDecimal uriageNsum) {
		this.uriageNsum = uriageNsum;
	}
	/**
	 * @return uriageNsumZen を戻します。
	 */
	public BigDecimal getUriageNsumZen() {
		return uriageNsumZen;
	}
	/**
	 * @param uriageNsumZen 設定する uriageNsumZen。
	 */
	public void setUriageNsumZen(BigDecimal uriageNsumZen) {
		this.uriageNsumZen = uriageNsumZen;
	}
	/**
	 * @return uriageNtake を戻します。
	 */
	public BigDecimal getUriageNtake() {
		return uriageNtake;
	}
	/**
	 * @param uriageNtake 設定する uriageNtake。
	 */
	public void setUriageNtake(BigDecimal uriageNtake) {
		this.uriageNtake = uriageNtake;
	}
	/**
	 * @return uriageNtakeZen を戻します。
	 */
	public BigDecimal getUriageNtakeZen() {
		return uriageNtakeZen;
	}
	/**
	 * @param uriageNtakeZen 設定する uriageNtakeZen。
	 */
	public void setUriageNtakeZen(BigDecimal uriageNtakeZen) {
		this.uriageNtakeZen = uriageNtakeZen;
	}
	/**
	 * @return uriageNtaku を戻します。
	 */
	public BigDecimal getUriageNtaku() {
		return uriageNtaku;
	}
	/**
	 * @param uriageNtaku 設定する uriageNtaku。
	 */
	public void setUriageNtaku(BigDecimal uriageNtaku) {
		this.uriageNtaku = uriageNtaku;
	}
	/**
	 * @return uriageNtakuZen を戻します。
	 */
	public BigDecimal getUriageNtakuZen() {
		return uriageNtakuZen;
	}
	/**
	 * @param uriageNtakuZen 設定する uriageNtakuZen。
	 */
	public void setUriageNtakuZen(BigDecimal uriageNtakuZen) {
		this.uriageNtakuZen = uriageNtakuZen;
	}
    
}
