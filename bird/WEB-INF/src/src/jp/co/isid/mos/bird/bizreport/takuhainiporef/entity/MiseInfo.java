package jp.co.isid.mos.bird.bizreport.takuhainiporef.entity;

import java.math.BigDecimal;

/**
 * 店舗別宅配売上情報エンティティクラス
 * 
 * @author xjung
 */
public class MiseInfo {
    /** テーブル名称 */
    public static final String TABLE = "BM01TENM";
    /** カラム名称：支部コード */   
    public static final String sibuCd_COLUMN = "SIBU_CD";
    /** カラム名称：支部名称 */    
    public static final String sibuName_COLUMN = "SIBU_NAME";
    /** カラム名称：ブロックコード */    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    /** カラム名称：ブロック名称 */
    public static final String blockName_COLUMN = "BLOCK_NAME";
    /** カラム名称：店コード */
    public static final String miseCd_COLUMN = "MISE_CD";
    /** カラム名称：店名称 */
    public static final String miseName_COLUMN = "MISE_NAME";
    /** カラム名称：宅配明細コード */
    public static final String takuDetailCd_COLUMN = "TAKU_DETAIL_CD";
    /** カラム名称：宅配日数 */
    public static final String eigyoDaysTaku_COLUMN = "EIGYO_DAYS_TAKU";
    /** カラム名称：売上 */
    public static final String uriage_COLUMN = "URIAGE";
    /** カラム名称：宅配売上 */
    public static final String uriageTaku_COLUMN = "URIAGE_TAKU";
    /** カラム名称：客数 */
    public static final String kyakusu_COLUMN = "KYAKUSU";
    /** カラム名称：宅配件数 */
    public static final String kyakusuTaku_COLUMN = "KYAKUSU_TAKU";
    /** カラム名称：前年売上 */
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";
    /** カラム名称：前年宅配売上 */
    public static final String uriageTakuZen_COLUMN = "URIAGE_TAKU_ZEN";
    /** カラム名称：前年件数 */
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";
    /** カラム名称：前年宅配件数 */
    public static final String kyakusuTakuZen_COLUMN = "KYAKUSU_TAKU_ZEN";
    /** カラム名称：宅配実績NET値がある店舗数 */
    public static final String tenpoCountNet_COLUMN = "TENPO_COUNT_NET";
    /** カラム名称：宅配NET値の営業日数 */
    public static final String eigyoDaysTakuNet_COLUMN = "EIGYO_DAYS_TAKU_NET";
    /** カラム名称：売上のNET値 */
    public static final String uriageNet_COLUMN = "URIAGE_NET";
    /** カラム名称：宅配売上のNET値 */
    public static final String uriageTakuNet_COLUMN = "URIAGE_TAKU_NET";
    /** カラム名称：客数のNET値 */
    public static final String kyakusuNet_COLUMN = "KYAKUSU_NET";
    /** カラム名称：宅配件数のNET値 */
    public static final String kyakusuTakuNet_COLUMN = "KYAKUSU_TAKU_NET";
    /** カラム名称：前年売上のNET値 */
    public static final String uriageZenNet_COLUMN = "URIAGE_ZEN_NET";
    /** カラム名称：前年宅配売上のNET値 */
    public static final String uriageTakuZenNet_COLUMN = "URIAGE_TAKU_ZEN_NET";
    /** カラム名称：前年件数のNET値 */
    public static final String kyakusuZenNet_COLUMN = "KYAKUSU_ZEN_NET";
    /** カラム名称：前年宅配件数のNET値 */
    public static final String kyakusuTakuZenNet_COLUMN = "KYAKUSU_TAKU_ZEN_NET";
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称
     */
    private String sibuName;

    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名称
     */
    private String blockName;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseName;
    
    /**
     * 宅配明細コード
     */
    private String takuDetailCd;
    
    /**
     * 宅配日数
     */
    private BigDecimal eigyoDaysTaku;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 宅配売上
     */
    private BigDecimal uriageTaku;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 宅配客数
     */
    private BigDecimal kyakusuTaku;
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen;
    
    /**
     * 前年宅配売上
     */
    private BigDecimal uriageTakuZen;
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen;
    
    /**
     * 前年宅配客数
     */
    private BigDecimal kyakusuTakuZen;
 
    /**
     * 宅配実績店舗数
     */
    private int tenpoCountNet;
    /**
     * 前年比対象宅配日数
     */
    private BigDecimal eigyoDaysTakuNet;
    /**
     * 前年比対象売上
     */
    private BigDecimal uriageNet;
    
    /**
     * 前年比対象宅配売上
     */
    private BigDecimal uriageTakuNet;
    
    /**
     * 前年比対象客数
     */
    private BigDecimal kyakusuNet;
    
    /**
     * 前年比対象宅配客数
     */
    private BigDecimal kyakusuTakuNet;
    
    /**
     * 前年比対象前年売上
     */
    private BigDecimal uriageZenNet;
    
    /**
     * 前年比対象前年宅配売上
     */
    private BigDecimal uriageTakuZenNet;
    
    /**
     * 前年比対象前年客数
     */
    private BigDecimal kyakusuZenNet;
    
    /**
     * 前年比対象前年宅配客数
     */
    private BigDecimal kyakusuTakuZenNet;
    
    /**
	 * @return kyakusuNet を戻します。
	 */
	public BigDecimal getKyakusuNet() {
		return kyakusuNet;
	}
	/**
	 * @param kyakusuNet 設定する kyakusuNet。
	 */
	public void setKyakusuNet(BigDecimal kyakusuNet) {
		this.kyakusuNet = kyakusuNet;
	}
	/**
	 * @return kyakusuTakuNet を戻します。
	 */
	public BigDecimal getKyakusuTakuNet() {
		return kyakusuTakuNet;
	}
	/**
	 * @param kyakusuTakuNet 設定する kyakusuTakuNet。
	 */
	public void setKyakusuTakuNet(BigDecimal kyakusuTakuNet) {
		this.kyakusuTakuNet = kyakusuTakuNet;
	}
	/**
	 * @return kyakusuTakuZenNet を戻します。
	 */
	public BigDecimal getKyakusuTakuZenNet() {
		return kyakusuTakuZenNet;
	}
	/**
	 * @param kyakusuTakuZenNet 設定する kyakusuTakuZenNet。
	 */
	public void setKyakusuTakuZenNet(BigDecimal kyakusuTakuZenNet) {
		this.kyakusuTakuZenNet = kyakusuTakuZenNet;
	}
	/**
	 * @return kyakusuZenNet を戻します。
	 */
	public BigDecimal getKyakusuZenNet() {
		return kyakusuZenNet;
	}
	/**
	 * @param kyakusuZenNet 設定する kyakusuZenNet。
	 */
	public void setKyakusuZenNet(BigDecimal kyakusuZenNet) {
		this.kyakusuZenNet = kyakusuZenNet;
	}
	/**
	 * 
	 * @return tenpoCountNet を戻します。
	 */
	public int getTenpoCountNet() {
		return tenpoCountNet;
	}
	/**
	 * @param tenpoCountNet 設定する tenpoCountNet。
	 */
	public void setTenpoCountNet(int tenpoCountNet) {
		this.tenpoCountNet = tenpoCountNet;
	}
	/**
	 * @return uriageNet を戻します。
	 */
	public BigDecimal getUriageNet() {
		return uriageNet;
	}
	/**
	 * @param uriageNet 設定する uriageNet。
	 */
	public void setUriageNet(BigDecimal uriageNet) {
		this.uriageNet = uriageNet;
	}
	/**
	 * @return uriageTakuNet を戻します。
	 */
	public BigDecimal getUriageTakuNet() {
		return uriageTakuNet;
	}
	/**
	 * @param uriageTakuNet 設定する uriageTakuNet。
	 */
	public void setUriageTakuNet(BigDecimal uriageTakuNet) {
		this.uriageTakuNet = uriageTakuNet;
	}
	/**
	 * @return uriageTakuZenNet を戻します。
	 */
	public BigDecimal getUriageTakuZenNet() {
		return uriageTakuZenNet;
	}
	/**
	 * @param uriageTakuZenNet 設定する uriageTakuZenNet。
	 */
	public void setUriageTakuZenNet(BigDecimal uriageTakuZenNet) {
		this.uriageTakuZenNet = uriageTakuZenNet;
	}
	/**
	 * @return uriageZenNet を戻します。
	 */
	public BigDecimal getUriageZenNet() {
		return uriageZenNet;
	}
	/**
	 * @param uriageZenNet 設定する uriageZenNet。
	 */
	public void setUriageZenNet(BigDecimal uriageZenNet) {
		this.uriageZenNet = uriageZenNet;
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return sibuName;
    }
    /**
     * 支部名称を設定します。
     * @param sibuName 支部名称
     */
    public void setSibuName(String sibuName) {
        this.sibuName = sibuName;
    }

    /**
     * ブロックコードを取得します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return blockCd;
    }
    /**
     * ブロックコードを設定します。
     * @param blockCd ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }
    
    /**
     * ブロック名称を取得します。
     * @return ブロック名称
     */
    public String getBlockName() {
        return blockName;
    }
    /**
     * ブロック名称を設定します。
     * @param blockName ブロック名称
     */
    public void setBlockName(String blockName) {
        this.blockName = blockName;
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
     * 店名称（漢字）を取得します。
     * @return 店名称（漢字）
     */
    public String getMiseName() {
        return miseName;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseName 店名称（漢字）
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }
    
    /**
     * 宅配明細コードを取得します。
     * @return 宅配明細コード
     */
    public String getTakuDetailCd() {
        return takuDetailCd;
    }
    /**
     * 宅配明細コードを設定します。
     * @param takuDetailCd 宅配明細コード
     */
    public void setTakuDetailCd(String takuDetailCd) {
        this.takuDetailCd = takuDetailCd;
    }
    
    /**
     * 宅配日数を取得します。
     * @return 宅配日数
     */
    public BigDecimal getEigyoDaysTaku() {
        return eigyoDaysTaku;
    }
    /**
     * 宅配日数を設定します。
     * @param eigyoDaysTaku 宅配日数
     */
    public void setEigyoDaysTaku(BigDecimal eigyoDaysTaku) {
        this.eigyoDaysTaku = eigyoDaysTaku;
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
     * 宅配売上を取得します。
     * @return 宅配売上
     */
    public BigDecimal getUriageTaku() {
        return uriageTaku;
    }
    /**
     * 宅配売上を設定します。
     * @param uriageTaku 宅配売上
     */
    public void setUriageTaku(BigDecimal uriageTaku) {
        this.uriageTaku = uriageTaku;
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
     * 宅配客数を取得します。
     * @return 宅配客数
     */
    public BigDecimal getKyakusuTaku() {
        return kyakusuTaku;
    }
    /**
     * 宅配客数を設定します。
     * @param kyakusuTaku 宅配客数
     */
    public void setKyakusuTaku(BigDecimal kyakusuTaku) {
        this.kyakusuTaku = kyakusuTaku;
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
     * 前年宅配売上を取得します。
     * @return 前年宅配売上
     */
    public BigDecimal getUriageTakuZen() {
        return uriageTakuZen;
    }
    /**
     * 前年宅配売上を設定します。
     * @param uriageTakuZen 前年宅配売上
     */
    public void setUriageTakuZen(BigDecimal uriageTakuZen) {
        this.uriageTakuZen = uriageTakuZen;
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
     * 前年宅配客数を取得します。
     * @return 前年宅配客数
     */
    public BigDecimal getKyakusuTakuZen() {
        return kyakusuTakuZen;
    }
    /**
     * 前年宅配客数を設定します。
     * @param kyakusuTakuZen 前年宅配客数
     */
    public void setKyakusuTakuZen(BigDecimal kyakusuTakuZen) {
        this.kyakusuTakuZen = kyakusuTakuZen;
    }
	/**
	 * @return eigyoDaysTakuNet を戻します。
	 */
	public BigDecimal getEigyoDaysTakuNet() {
		return eigyoDaysTakuNet;
	}
	/**
	 * 前年比対象宅配日数
	 * @param eigyoDaysTakuNet 設定する eigyoDaysTakuNet。
	 */
	public void setEigyoDaysTakuNet(BigDecimal eigyoDaysTakuNet) {
		this.eigyoDaysTakuNet = eigyoDaysTakuNet;
	}
    
}
