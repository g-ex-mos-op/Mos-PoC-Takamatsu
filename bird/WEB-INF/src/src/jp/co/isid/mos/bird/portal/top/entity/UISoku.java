package jp.co.isid.mos.bird.portal.top.entity;

import java.math.BigDecimal;

public class UISoku {
    
    public static final String TABLE = "BS09SOKU";
    
    public static final String dtType_COLUMN = "DT_TYPE";
    
    public static final String dtName_COLUMN = "DT_NAME";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String segmentType_COLUMN = "SEGMENT_TYPE";
    
    public static final String segmentName_COLUMN = "SEGMENT_NAME";
    
    public static final String kizonFlg_COLUMN = "KIZON_FLG";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String yosan_COLUMN = "YOSAN";
    
    public static final String yosanhiMikomi_COLUMN = "YOSANHI_MIKOMI";

    public static final String yosanHi_COLUMN = "YOSAN_HI";

    public static final String zenUriage_COLUMN = "ZEN_URIAGE";
    
    public static final String zenUriageDoujitu_COLUMN = "ZEN_URIAGE_DOUJITU";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String zenKyakusu_COLUMN = "ZEN_KYAKUSU";
    
    public static final String zenKyakusuDoujitu_COLUMN = "ZEN_KYAKUSU_DOUJITU";
    
    public static final String tenpoCount_COLUMN = "TENPO_COUNT";
    
    public static final String zenTenpoCount_COLUMN = "ZEN_TENPO_COUNT";
    
    public static final String uriageZenHi_COLUMN = "URIAGE_ZEN_HI";

    public static final String uriageZenHiDoujitu_COLUMN = "URIAGE_ZEN_HI_DOUJITU";

    public static final String kyakusuZenHi_COLUMN = "KYAKUSU_ZEN_HI";

    public static final String kyakusuZenHiDoujitu_COLUMN = "KYAKUSU_ZEN_HI_DOUJITU";

    public static final String kyakutanka_COLUMN = "KYAKUTANKA";
    
    public static final String zenKyakutanka_COLUMN = "ZEN_KYAKUTANKA";
    
    public static final String zenKyakutankaDoujitu_COLUMN = "ZEN_KYAKUTANKA_DOUJITU";
    
    public static final String kyakutankaZenHi_COLUMN = "KYAKUTANKA_ZEN_HI";
    
    public static final String kyakutankaZenHiDoujitu_COLUMN = "KYAKUTANKA_ZEN_HI_DOUJITU";
    
    public static final String uriageNet_COLUMN = "URIAGE_NET";
    
    public static final String zenUriageNet_COLUMN = "ZEN_URIAGE_NET";
    
    public static final String kyakusuNet_COLUMN = "KYAKUSU_NET";
    
    public static final String zenKyakusuNet_COLUMN = "ZEN_KYAKUSU_NET";
    
    public static final String uriageZenHiNet_COLUMN = "URIAGE_ZEN_HI_NET";

    public static final String kyakusuZenHiNet_COLUMN = "KYAKUSU_ZEN_HI_NET";

    public static final String kyakutankaNet_COLUMN = "KYAKUTANKA_NET";
    
    public static final String zenKyakutankaNet_COLUMN = "ZEN_KYAKUTANKA_NET";
    
    public static final String kyakutankaZenHiNet_COLUMN = "KYAKUTANKA_ZEN_HI_NET";
    /**
     * 対象期間種別
     * 1:本日、2:当月、3:当期
     */
    private String dtType;
    /**
     * 対象年月日名称
     * 本日・当月・当期
     */
    private String dtName;
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * セグメントタイプ
     */
    private String segmentType;
    
    /**
     * 既存店フラグ
     */
    private String kizonFlg;
    
    /**
     * 売上額
     */
    private BigDecimal uriage = new BigDecimal("0");
    
    /**
     * 計画額
     */
    private BigDecimal yosan = new BigDecimal("0");
    
    /**
     * 計画見込比
     */
    private BigDecimal yosanhiMikomi = new BigDecimal("0.00");
    
    /**
     * 計画比
     */
    private BigDecimal yosanHi = new BigDecimal("0.00");
    
    /**
     * 前年売上
     */
    private BigDecimal zenUriage = new BigDecimal("0");
    
    /**
     * 前年売上（同日）
     */
    private BigDecimal zenUriageDoujitu = new BigDecimal("0");
    
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal zenKyakusu = new BigDecimal("0");
    
    /**
     * 前年客数（同日）
     */
    private BigDecimal zenKyakusuDoujitu = new BigDecimal("0");
    
    /**
     * 月末店舗数
     */
    private BigDecimal tenpoCount = new BigDecimal("0");
    
    /**
     * 前年月末店舗数
     */
    private BigDecimal zenTenpoCount = new BigDecimal("0");
    
    /**
     * セグメント名称
     */
    private String segmentName;

    private BigDecimal uriageZenHi = new BigDecimal("0.00");

    private BigDecimal uriageZenHiDoujitu = new BigDecimal("0.00");

    private BigDecimal kyakusuZenHi = new BigDecimal("0.00");

    private BigDecimal kyakusuZenHiDoujitu = new BigDecimal("0.00");

    private BigDecimal kyakutanka = new BigDecimal("0");
    
    private BigDecimal zenKyakutanka = new BigDecimal("0");
    
    private BigDecimal zenKyakutankaDoujitu = new BigDecimal("0");
    
    private BigDecimal kyakutankaZenHi = new BigDecimal("0.00");
    
    private BigDecimal kyakutankaZenHiDoujitu = new BigDecimal("0.00");
    
    /** NET売上額 */
    private BigDecimal uriageNet = new BigDecimal("0");
    /** NET前年売上 */
    private BigDecimal zenUriageNet = new BigDecimal("0");
    /** NET客数 */
    private BigDecimal kyakusuNet = new BigDecimal("0");
    /** NET前年客数 */
    private BigDecimal zenKyakusuNet = new BigDecimal("0");
    /** NET売上前年比 */
    private BigDecimal uriageZenHiNet = new BigDecimal("0.00");
    /** NET客数前年比 */
    private BigDecimal kyakusuZenHiNet = new BigDecimal("0.00");
    /** NET客単価 */
    private BigDecimal kyakutankaNet = new BigDecimal("0");
    /** NET前年客単価 */
    private BigDecimal zenKyakutankaNet = new BigDecimal("0");
    /** NET前年比 */
    private BigDecimal kyakutankaZenHiNet = new BigDecimal("0.00");

    /**
     * 対象年月日を取得します。
     * @return 対象年月日
     */
    public String getDtName() {
        return dtName;
    }
    /**
     * 対象年月日を設定します。
     * @param dtName 対象年月日
     */
    public void setDtName(String dt) {
        this.dtName = dt;
    }
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * セグメントタイプを取得します。
     * @return セグメントタイプ
     */
    public String getSegmentType() {
        return segmentType;
    }
    /**
     * セグメントタイプを設定します。
     * @param segmentType セグメントタイプ
     */
    public void setSegmentType(String segmentType) {
        this.segmentType = segmentType;
    }
    
    /**
     * 既存店フラグを取得します。
     * @return 既存店フラグ
     */
    public String getKizonFlg() {
        return kizonFlg;
    }
    /**
     * 既存店フラグを設定します。
     * @param kizonFlg 既存店フラグ
     */
    public void setKizonFlg(String kizonFlg) {
        this.kizonFlg = kizonFlg;
    }
    
    /**
     * 売上額を取得します。
     * @return 売上額
     */
    public BigDecimal getUriage() {
        return uriage;
    }
    /**
     * 売上額を設定します。
     * @param uriage 売上額
     */
    public void setUriage(BigDecimal uriage) {
        this.uriage = uriage;
    }
    
    /**
     * 計画額を取得します。
     * @return 計画額
     */
    public BigDecimal getYosan() {
        return yosan;
    }
    /**
     * 計画額を設定します。
     * @param yosan 計画額
     */
    public void setYosan(BigDecimal yosan) {
        this.yosan = yosan;
    }
    
    /**
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getZenUriage() {
        return zenUriage;
    }
    /**
     * 前年売上を設定します。
     * @param zenUriage 前年売上
     */
    public void setZenUriage(BigDecimal zenUriage) {
        this.zenUriage = zenUriage;
    }
    
    /**
     * 前年売上（同日）を取得します。
     * @return 前年売上（同日）
     */
    public BigDecimal getZenUriageDoujitu() {
        return zenUriageDoujitu;
    }
    /**
     * 前年売上（同日）を設定します。
     * @param zenUriageDoujitu 前年売上（同日）
     */
    public void setZenUriageDoujitu(BigDecimal zenUriageDoujitu) {
        this.zenUriageDoujitu = zenUriageDoujitu;
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
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getZenKyakusu() {
        return zenKyakusu;
    }
    /**
     * 前年客数を設定します。
     * @param zenKyakusu 前年客数
     */
    public void setZenKyakusu(BigDecimal zenKyakusu) {
        this.zenKyakusu = zenKyakusu;
    }
    
    /**
     * 前年客数（同日）を取得します。
     * @return 前年客数（同日）
     */
    public BigDecimal getZenKyakusuDoujitu() {
        return zenKyakusuDoujitu;
    }
    /**
     * 前年客数（同日）を設定します。
     * @param zenKyakusuDoujitu 前年客数（同日）
     */
    public void setZenKyakusuDoujitu(BigDecimal zenKyakusuDoujitu) {
        this.zenKyakusuDoujitu = zenKyakusuDoujitu;
    }
    
    /**
     * 店舗数を取得します。
     * @return 店舗数
     */
    public BigDecimal getTenpoCount() {
        return tenpoCount;
    }
    /**
     * 店舗数を設定します。
     * @param tenpoCount 店舗数
     */
    public void setTenpoCount(BigDecimal tenpoCount) {
        this.tenpoCount = tenpoCount;
    }
        
    /**
     * セグメント名称を取得します。
     * @return セグメント名称
     */
    public String getSegmentName() {
        return segmentName;
    }
    /**
     * セグメント名称を設定します。
     * @param segmentName セグメント名称
     */
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
	/**
	 * @return kyakusuZenHi を戻します。
	 */
	public BigDecimal getKyakusuZenHi() {
		return kyakusuZenHi;
	}
	/**
	 * @param kyakusuZenHi を クラス変数kyakusuZenHiへ設定します。
	 */
	public void setKyakusuZenHi(BigDecimal kyakusuZenHi) {
		this.kyakusuZenHi = kyakusuZenHi;
	}
	/**
	 * @return kyakusuZenHiDoujitu を戻します。
	 */
	public BigDecimal getKyakusuZenHiDoujitu() {
		return kyakusuZenHiDoujitu;
	}
	/**
	 * @param kyakusuZenHiDoujitu を クラス変数kyakusuZenHiDoujituへ設定します。
	 */
	public void setKyakusuZenHiDoujitu(BigDecimal kyakusuZenHiDoujitu) {
		this.kyakusuZenHiDoujitu = kyakusuZenHiDoujitu;
	}
	/**
	 * @return kyakutanka を戻します。
	 */
	public BigDecimal getKyakutanka() {
		return kyakutanka;
	}
	/**
	 * @param kyakutanka を クラス変数kyakutankaへ設定します。
	 */
	public void setKyakutanka(BigDecimal kyakutanka) {
		this.kyakutanka = kyakutanka;
	}
	/**
	 * @return zenKyakutanka を戻します。
	 */
	public BigDecimal getZenKyakutanka() {
		return zenKyakutanka;
	}
	/**
	 * @param zenKyakutanka を クラス変数kyakutankaZenへ設定します。
	 */
	public void setZenKyakutanka(BigDecimal kyakutankaZen) {
		this.zenKyakutanka = kyakutankaZen;
	}
	/**
	 * @return kyakutankaZenHi を戻します。
	 */
	public BigDecimal getKyakutankaZenHi() {
		return kyakutankaZenHi;
	}
	/**
	 * @param kyakutankaZenHi を クラス変数kyakutankaZenHiへ設定します。
	 */
	public void setKyakutankaZenHi(BigDecimal kyakutankaZenHi) {
		this.kyakutankaZenHi = kyakutankaZenHi;
	}
	/**
	 * @return kyakutankaZenHiDoujitu を戻します。
	 */
	public BigDecimal getKyakutankaZenHiDoujitu() {
		return kyakutankaZenHiDoujitu;
	}
	/**
	 * @param kyakutankaZenHiDoujitu を クラス変数kyakutankaZenHiDoujituへ設定します。
	 */
	public void setKyakutankaZenHiDoujitu(BigDecimal kyakutankaZenHiDoujitu) {
		this.kyakutankaZenHiDoujitu = kyakutankaZenHiDoujitu;
	}
	/**
	 * @return uriageZenHi を戻します。
	 */
	public BigDecimal getUriageZenHi() {
		return uriageZenHi;
	}
	/**
	 * @param uriageZenHi を クラス変数uriageZenHiへ設定します。
	 */
	public void setUriageZenHi(BigDecimal uriageZenHi) {
		this.uriageZenHi = uriageZenHi;
	}
	/**
	 * @return uriageZenHiDoujitu を戻します。
	 */
	public BigDecimal getUriageZenHiDoujitu() {
		return uriageZenHiDoujitu;
	}
	/**
	 * @param uriageZenHiDoujitu を クラス変数uriageZenHiDoujituへ設定します。
	 */
	public void setUriageZenHiDoujitu(BigDecimal uriageZenHiDoujitu) {
		this.uriageZenHiDoujitu = uriageZenHiDoujitu;
	}
	/**
	 * @return yosanHi を戻します。
	 */
	public BigDecimal getYosanHi() {
		return yosanHi;
	}
	/**
	 * @param yosanHi を クラス変数yosanHiへ設定します。
	 */
	public void setYosanHi(BigDecimal yosanHi) {
		this.yosanHi = yosanHi;
	}
	/**
	 * @return yosanhiMikomi を戻します。
	 */
	public BigDecimal getYosanhiMikomi() {
		return yosanhiMikomi;
	}
	/**
	 * @param yosanhiMikomi を クラス変数yosanMikomiへ設定します。
	 */
	public void setYosanhiMikomi(BigDecimal yosanMikomi) {
		this.yosanhiMikomi = yosanMikomi;
	}
	/**
	 * @return zenKyakutankaDoujitu を戻します。
	 */
	public BigDecimal getZenKyakutankaDoujitu() {
		return zenKyakutankaDoujitu;
	}
	/**
	 * @param zenKyakutankaDoujitu を クラス変数zenKyakutankaDoujituへ設定します。
	 */
	public void setZenKyakutankaDoujitu(BigDecimal zenKyakutankaDoujitu) {
		this.zenKyakutankaDoujitu = zenKyakutankaDoujitu;
	}
	/**
	 * 対象期間種別取得処理
	 * 
     * 1:本日、2:当月、3:当期
	 * @return dtType を戻します。
	 */
	public String getDtType() {
		return dtType;
	}
	/**
	 * 対象期間種別設定処理
	 * 
	 * @param dtType を クラス変数dtTypeへ設定します。
	 */
	public void setDtType(String dtType) {
		this.dtType = dtType;
	}
	/**
	 * @return zenTenpoCount を戻します。
	 */
	public BigDecimal getZenTenpoCount() {
		return zenTenpoCount;
	}
	/**
	 * @param zenTenpoCount を クラス変数zenTenpoCountへ設定します。
	 */
	public void setZenTenpoCount(BigDecimal zenTenpoCount) {
		this.zenTenpoCount = zenTenpoCount;
	}
	/**
	 * @return kyakusuNet を戻します。
	 */
	public BigDecimal getKyakusuNet() {
		return kyakusuNet;
	}
	/**
	 * @param kyakusuNet を クラス変数kyakusuNetへ設定します。
	 */
	public void setKyakusuNet(BigDecimal kyakusuNet) {
		this.kyakusuNet = kyakusuNet;
	}
	/**
	 * @return kyakusuZenHiNet を戻します。
	 */
	public BigDecimal getKyakusuZenHiNet() {
		return kyakusuZenHiNet;
	}
	/**
	 * @param kyakusuZenHiNet を クラス変数kyakusuZenHiNetへ設定します。
	 */
	public void setKyakusuZenHiNet(BigDecimal kyakusuZenHiNet) {
		this.kyakusuZenHiNet = kyakusuZenHiNet;
	}
	/**
	 * @return kyakutankaNet を戻します。
	 */
	public BigDecimal getKyakutankaNet() {
		return kyakutankaNet;
	}
	/**
	 * @param kyakutankaNet を クラス変数kyakutankaNetへ設定します。
	 */
	public void setKyakutankaNet(BigDecimal kyakutankaNet) {
		this.kyakutankaNet = kyakutankaNet;
	}
	/**
	 * @return kyakutankaZenHiNet を戻します。
	 */
	public BigDecimal getKyakutankaZenHiNet() {
		return kyakutankaZenHiNet;
	}
	/**
	 * @param kyakutankaZenHiNet を クラス変数kyakutankaZenHiNetへ設定します。
	 */
	public void setKyakutankaZenHiNet(BigDecimal kyakutankaZenHiNet) {
		this.kyakutankaZenHiNet = kyakutankaZenHiNet;
	}
	/**
	 * @return uriageNet を戻します。
	 */
	public BigDecimal getUriageNet() {
		return uriageNet;
	}
	/**
	 * @param uriageNet を クラス変数uriageNetへ設定します。
	 */
	public void setUriageNet(BigDecimal uriageNet) {
		this.uriageNet = uriageNet;
	}
	/**
	 * @return uriageZenHiNet を戻します。
	 */
	public BigDecimal getUriageZenHiNet() {
		return uriageZenHiNet;
	}
	/**
	 * @param uriageZenHiNet を クラス変数uriageZenHiNetへ設定します。
	 */
	public void setUriageZenHiNet(BigDecimal uriageZenHiNet) {
		this.uriageZenHiNet = uriageZenHiNet;
	}
	/**
	 * @return zenKyakusuNet を戻します。
	 */
	public BigDecimal getZenKyakusuNet() {
		return zenKyakusuNet;
	}
	/**
	 * @param zenKyakusuNet を クラス変数zenKyakusuNetへ設定します。
	 */
	public void setZenKyakusuNet(BigDecimal zenKyakusuNet) {
		this.zenKyakusuNet = zenKyakusuNet;
	}
	/**
	 * @return zenKyakutankaNet を戻します。
	 */
	public BigDecimal getZenKyakutankaNet() {
		return zenKyakutankaNet;
	}
	/**
	 * @param zenKyakutankaNet を クラス変数zenKyakutankaNetへ設定します。
	 */
	public void setZenKyakutankaNet(BigDecimal zenKyakutankaNet) {
		this.zenKyakutankaNet = zenKyakutankaNet;
	}
	/**
	 * @return zenUriageNet を戻します。
	 */
	public BigDecimal getZenUriageNet() {
		return zenUriageNet;
	}
	/**
	 * @param zenUriageNet を クラス変数zenUriageNetへ設定します。
	 */
	public void setZenUriageNet(BigDecimal zenUriageNet) {
		this.zenUriageNet = zenUriageNet;
	}
}
