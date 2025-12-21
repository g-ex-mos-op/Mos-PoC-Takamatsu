package jp.co.isid.mos.bird.analysis.rankref.entity;

import java.math.BigDecimal;

/**
 * 売上ランクエンティティクラス
 * 
 * 作成日:2008/10/21
 * @author xkinu
 *
 */
public class UIRank {
    public static final String rank_COLUMN = "RANK_NO";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String uriage_COLUMN = "URIAGE";

    public static final String yosan_COLUMN = "YOSAN";
    
    public static final String yosanTasseiRitu_COLUMN = "YOSAN_TASSEI_RITU";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";

    public static final String uriageZennenHi_COLUMN = "URIAGE_ZENNEN_HI";

    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    
    public static final String kyakusuZennenHi_COLUMN = "KYAKUSU_ZENNEN_HI";
    
    public static final String kyakutanka_COLUMN = "KYAKUTANKA";
    
    public static final String kyakutankaZen_COLUMN = "KYAKUTANKA_ZEN";
    
    public static final String kyakutankaZennenHi_COLUMN = "KYAKUTANKA_ZENNEN_HI";

    /**
     * ランク
     */
    private BigDecimal rankNo = new BigDecimal("0");
    /**
     * 店舗コード
     */
    private String miseCd = "";
    /** 
     * 店舗名称
     */
    private String miseNameKj = "";
    
    /**
     * 売上高
     */
    private BigDecimal uriage = new BigDecimal("0");    
    /**
     * 売上高
     */
    private BigDecimal yosan = new BigDecimal("0");    
    /**
     * 予算達成率
     */
    private BigDecimal yosanTasseiRitu = new BigDecimal("0");    
        
    /**
     * 前年売上高
     */
    private BigDecimal uriageZen = new BigDecimal("0");
    /**
     * 売上前年比
     */
    private BigDecimal uriageZennenHi = new BigDecimal("0");
    
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen = new BigDecimal("0");
    /**
     * 客数前年比
     */
    private BigDecimal kyakusuZennenHi = new BigDecimal("0");
    
    /**
     * 客単価
     */
    private BigDecimal kyakutanka = new BigDecimal("0");
    
    /**
     * 前年客単価
     */
    private BigDecimal kyakutankaZen = new BigDecimal("0");
    /**
     * 客単価前年比
     */
    private BigDecimal kyakutankaZennenHi = new BigDecimal("0");
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
	 * 前年比(売上)
	 * @param uriageZennenHi を クラス変数uriageZennenHiへ設定します。
	 */
	public void setUriageZennenHi(BigDecimal uriageZennenHi) {
		this.uriageZennenHi = uriageZennenHi;
	}
	/**
	 * 前年比(売上)
	 * @return uriageZennenHi を戻します。
	 */
	public BigDecimal getUriageZennenHi() {
		return uriageZennenHi;
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
	 * 前年比(客数)設定処理
	 * 
	 * @param kyakusuZennenHi を クラス変数kyakusuZennenHiへ設定します。
	 */
	public void setKyakusuZennenHi(BigDecimal kyakusuZennenHi) {
		this.kyakusuZennenHi = kyakusuZennenHi;
	}
	/**
	 * 前年比(客数)
	 * @return kyakusuZennenHi を戻します。
	 */
	public BigDecimal getKyakusuZennenHi() {
		return kyakusuZennenHi;
	}    
	/**
	 * 客単価設定処理
	 * @param kyakutanka を クラス変数kyakutankaへ設定します。
	 */
	public void setKyakutanka(BigDecimal kyakutanka) {
		this.kyakutanka = kyakutanka;
	}
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakutanka() {
    	return kyakutanka;
    }
	/**
	 * 前年(客単価)設定処理
	 * @param kyakutankaZen を クラス変数kyakutankaZenへ設定します。
	 */
	public void setKyakutankaZen(BigDecimal kyakutankaZen) {
		this.kyakutankaZen = kyakutankaZen;
	}
    /**
     * 前年(客単価)を取得します。
     * @return 前年客単価
     */
    public BigDecimal getKyakutankaZen() {
        return kyakutankaZen;
    }
	/**
	 * 前年比(客単価)
	 * @param kyakutankaZennenHi を クラス変数kyakutankaZennenHiへ設定します。
	 */
	public void setKyakutankaZennenHi(BigDecimal kyakutankaZennenHi) {
		this.kyakutankaZennenHi = kyakutankaZennenHi;
	}
	/**
	 * 前年比(客単価)
	 * @return kyakutankaZennenHi を戻します。
	 */
	public BigDecimal getKyakutankaZennenHi() {
		return kyakutankaZennenHi;
	}
	/**
	 * @return rankNo を戻します。
	 */
	public BigDecimal getRankNo() {
		return rankNo;
	}
	/**
	 * @param rankNo を クラス変数rankNoへ設定します。
	 */
	public void setRankNo(BigDecimal rankNo) {
		this.rankNo = rankNo;
	}
	/**
	 * @return yosan を戻します。
	 */
	public BigDecimal getYosan() {
		return yosan;
	}
	/**
	 * @param yosan を クラス変数yosanへ設定します。
	 */
	public void setYosan(BigDecimal yosan) {
		this.yosan = yosan;
	}
	/**
	 * @return yosanTasseiRitu を戻します。
	 */
	public BigDecimal getYosanTasseiRitu() {
		return yosanTasseiRitu;
	}
	/**
	 * @param yosanTasseiRitu を クラス変数yosanTasseiRituへ設定します。
	 */
	public void setYosanTasseiRitu(BigDecimal yosanTasseiRitu) {
		this.yosanTasseiRitu = yosanTasseiRitu;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd を クラス変数miseCdへ設定します。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}
	/**
	 * @param miseNameKj を クラス変数miseNameKjへ設定します。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}
}
