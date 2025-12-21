package jp.co.isid.mos.bird.analysis.menubeturef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIMenu;

/**
 * メニュー別種別売上個数エンティティ
 * 
 * 作成日:2008/09/10
 * @author xkinu
 *
 */
public class UIUriageShubetu extends UIMenu {
    
    public static final String TABLE = "BD10NMSL";
    
    public static final String kosuKouseiHi_COLUMN = "KOSU_KOUSEI_HI";
    
    public static final String kosuEat_COLUMN = "KOSU_EAT";
    
    public static final String kosuKouseiEat_COLUMN = "KOSU_KOUSEI_HI_EAT";
    
    public static final String kosuTake_COLUMN = "KOSU_TAKE";
    
    public static final String kosuKouseiTake_COLUMN = "KOSU_KOUSEI_HI_TAKE";
    
    public static final String kosuTel_COLUMN = "KOSU_TEL";
    
    public static final String kosuKouseiTel_COLUMN = "KOSU_KOUSEI_HI_TEL";
    
    public static final String kosuDrive_COLUMN = "KOSU_DRIVE";
    
    public static final String kosuKouseiDrive_COLUMN = "KOSU_KOUSEI_HI_DRIVE";
    
    public static final String kosuTakuhai_COLUMN = "KOSU_TAKUHAI";
    
    public static final String kosuKouseiTakuhai_COLUMN = "KOSU_KOUSEI_HI_TAKUHAI";
    
    public static final String kosuGaihan_COLUMN = "KOSU_GAIHAN";
    
    public static final String kosuKouseiGaihan_COLUMN = "KOSU_KOUSEI_HI_GAIHAN";
    
    public static final String kosuSyubetsu07_COLUMN = "KOSU_SYUBETSU_07";
    
    public static final String kosuKouseiSyubetsu07_COLUMN = "KOSU_KOUSEI_HI_SYUBETSU_07";
    
    public static final String kosuSyubetsu08_COLUMN = "KOSU_SYUBETSU_08";
    
    public static final String kosuKouseiSyubetsu08_COLUMN = "KOSU_KOUSEI_HI_SYUBETSU_08";
    
    public static final String kosuSyubetsu09_COLUMN = "KOSU_SYUBETSU_09";
    
    public static final String kosuKouseiSyubetsu09_COLUMN = "KOSU_KOUSEI_HI_SYUBETSU_09";
    
    public static final String kosuSyubetsu10_COLUMN = "KOSU_SYUBETSU_10";
    
    public static final String kosuKouseiSyubetsu10_COLUMN = "KOSU_KOUSEI_HI_SYUBETSU_10";
    
    public static final String kosuNettake_COLUMN = "KOSU_NET_TAKE";
    
    public static final String kosuKouseiNettake_COLUMN = "KOSU_KOUSEI_HI_NET_TAKE";
    
    public static final String kosuNettakuhai_COLUMN = "KOSU_NET_TAKUHAI";
    
    public static final String kosuKouseiNettakuhai_COLUMN = "KOSU_KOUSEI_HI_NET_TAKUHAI";
    
    public static final String kosuSyubetsu13_COLUMN = "KOSU_SYUBETSU_13";
    
    public static final String kosuKouseiSyubetsu13_COLUMN = "KOSU_KOUSEI_HI_SYUBETSU_13";
    
    public static final String kosuSyubetsu14_COLUMN = "KOSU_SYUBETSU_14";
    
    public static final String kosuKouseiSyubetsu14_COLUMN = "KOSU_KOUSEI_HI_SYUBETSU_14";
    
    public static final String kosuSyubetsu15_COLUMN = "KOSU_SYUBETSU_15";
    
    public static final String kosuKouseiSyubetsu15_COLUMN = "KOSU_KOUSEI_HI_SYUBETSU_15";
    
//    public static final String kosuEtc_COLUMN = "KOSU_ETC";
//    
//    public static final String kosuKouseiEtc_COLUMN = "KOSU_KOUSEI_HI_ETC";
    
    /**
     * E/I個数
     */
    private BigDecimal kosuEat = new BigDecimal("0");
    
    /**
     * E/Iの個数構成比
     */
    private BigDecimal kosuKouseiEat = new BigDecimal("0");
    
    /**
     * T/O個数
     */
    private BigDecimal kosuTake = new BigDecimal("0");
    
    /**
     * T/Oの個数構成比
     */
    private BigDecimal kosuKouseiTake = new BigDecimal("0");
    
    /**
     * TEL個数
     */
    private BigDecimal kosuTel = new BigDecimal("0");
    
    /**
     * TELの個数構成比
     */
    private BigDecimal kosuKouseiTel = new BigDecimal("0");
    
    /**
     * D/T個数
     */
    private BigDecimal kosuDrive = new BigDecimal("0");
    
    /**
     * D/Tの個数構成比
     */
    private BigDecimal kosuKouseiDrive = new BigDecimal("0");
    
    /**
     * 宅配個数
     */
    private BigDecimal kosuTakuhai = new BigDecimal("0");
    
    /**
     * 宅配の個数構成比
     */
    private BigDecimal kosuKouseiTakuhai = new BigDecimal("0");
    
    /**
     * 外販個数
     */
    private BigDecimal kosuGaihan = new BigDecimal("0");
    
    /**
     * 外販構成比
     */
    private BigDecimal kosuKouseiGaihan = new BigDecimal("0");
    
    /**
     * 種別７個数
     */
    private BigDecimal kosuSyubetsu07 = new BigDecimal("0");
    
    /**
     * 種別７構成比
     */
    private BigDecimal kosuKouseiSyubetsu07 = new BigDecimal("0");
    
    /**
     * 種別８個数
     */
    private BigDecimal kosuSyubetsu08 = new BigDecimal("0");
    
    /**
     * 種別８構成比
     */
    private BigDecimal kosuKouseiSyubetsu08 = new BigDecimal("0");
    
    /**
     * 種別９個数
     */
    private BigDecimal kosuSyubetsu09 = new BigDecimal("0");
    
    /**
     * 種別９構成比
     */
    private BigDecimal kosuKouseiSyubetsu09 = new BigDecimal("0");
    
    /**
     * 種別１０個数
     */
    private BigDecimal kosuSyubetsu10 = new BigDecimal("0");
    
    /**
     * 種別１０構成比
     */
    private BigDecimal kosuKouseiSyubetsu10 = new BigDecimal("0");
    
    /**
     * ネットテイク個数
     */
    private BigDecimal kosuNettake = new BigDecimal("0");
    
    /**
     * ネットテイク構成比
     */
    private BigDecimal kosuKouseiNettake = new BigDecimal("0");
    
    /**
     * ネット宅配個数
     */
    private BigDecimal kosuNettakuhai = new BigDecimal("0");
    
    /**
     * ネット宅配構成比
     */
    private BigDecimal kosuKouseiNettakuhai = new BigDecimal("0");
    
    /**
     * 種別１３個数
     */
    private BigDecimal kosuSyubetsu13 = new BigDecimal("0");
    
    /**
     * 種別１３構成比
     */
    private BigDecimal kosuKouseiSyubetsu13 = new BigDecimal("0");
    
    /**
     * 種別１４個数
     */
    private BigDecimal kosuSyubetsu14 = new BigDecimal("0");
    
    /**
     * 種別１４構成比
     */
    private BigDecimal kosuKouseiSyubetsu14 = new BigDecimal("0");
    
    /**
     * 種別１５個数
     */
    private BigDecimal kosuSyubetsu15 = new BigDecimal("0");
    
    /**
     * 種別１５構成比
     */
    private BigDecimal kosuKouseiSyubetsu15 = new BigDecimal("0");
    
//    /**
//     * 外販・その他個数
//     */
//    private BigDecimal kosuEtc = new BigDecimal("0");
//    
//    /**
//     * 外販・その他の個数構成比
//     */
//    private BigDecimal kosuKouseiEtc = new BigDecimal("0");
    
    /**
     * E/I個数を取得します。
     * @return E/I個数
     */
    public BigDecimal getKosuEat() {
        return kosuEat;
    }
    /**
     * E/I個数を設定します。
     * @param kosuEat E/I個数
     */
    public void setKosuEat(BigDecimal kosuEat) {
        this.kosuEat = kosuEat;
    }
    
    /**
     * E/Iの個数構成比を取得します。
     * @return E/Iの個数構成比
     */
    public BigDecimal getKosuKouseiHiEat() {
        return kosuKouseiEat;
    }
    /**
     * E/Iの個数構成比を設定します。
     * @param kosuKouseiEat E/Iの個数構成比
     */
    public void setKosuKouseiHiEat(BigDecimal kosuKouseiEat) {
        this.kosuKouseiEat = kosuKouseiEat;
    }
    
    /**
     * T/O個数を取得します。
     * @return T/O個数
     */
    public BigDecimal getKosuTake() {
        return kosuTake;
    }
    /**
     * T/O個数を設定します。
     * @param kosuTake T/O個数
     */
    public void setKosuTake(BigDecimal kosuTake) {
        this.kosuTake = kosuTake;
    }
    
    /**
     * T/Oの個数構成比を取得します。
     * @return T/Oの個数構成比
     */
    public BigDecimal getKosuKouseiHiTake() {
        return kosuKouseiTake;
    }
    /**
     * T/Oの個数構成比を設定します。
     * @param kosuKouseiTake T/Oの個数構成比
     */
    public void setKosuKouseiHiTake(BigDecimal kosuKouseiTake) {
        this.kosuKouseiTake = kosuKouseiTake;
    }
    
    /**
     * TEL個数を取得します。
     * @return TEL個数
     */
    public BigDecimal getKosuTel() {
        return kosuTel;
    }
    /**
     * TEL個数を設定します。
     * @param kosuTel TEL個数
     */
    public void setKosuTel(BigDecimal kosuTel) {
        this.kosuTel = kosuTel;
    }
    
    /**
     * TELの個数構成比を取得します。
     * @return TELの個数構成比
     */
    public BigDecimal getKosuKouseiHiTel() {
        return kosuKouseiTel;
    }
    /**
     * TELの個数構成比を設定します。
     * @param kosuKouseiTel TELの個数構成比
     */
    public void setKosuKouseiHiTel(BigDecimal kosuKouseiTel) {
        this.kosuKouseiTel = kosuKouseiTel;
    }
    
    /**
     * D/T個数を取得します。
     * @return D/T個数
     */
    public BigDecimal getKosuDrive() {
        return kosuDrive;
    }
    /**
     * D/T個数を設定します。
     * @param kosuDrive D/T個数
     */
    public void setKosuDrive(BigDecimal kosuDrive) {
        this.kosuDrive = kosuDrive;
    }
    
    /**
     * D/Tの個数構成比を取得します。
     * @return D/Tの個数構成比
     */
    public BigDecimal getKosuKouseiHiDrive() {
        return kosuKouseiDrive;
    }
    /**
     * D/Tの個数構成比を設定します。
     * @param kosuKouseiDrive D/Tの個数構成比
     */
    public void setKosuKouseiHiDrive(BigDecimal kosuKouseiDrive) {
        this.kosuKouseiDrive = kosuKouseiDrive;
    }
    
    /**
     * 宅配個数を取得します。
     * @return 宅配個数
     */
    public BigDecimal getKosuTakuhai() {
        return kosuTakuhai;
    }
    /**
     * 宅配個数を設定します。
     * @param kosuTakuhai 宅配個数
     */
    public void setKosuTakuhai(BigDecimal kosuTakuhai) {
        this.kosuTakuhai = kosuTakuhai;
    }
    
    /**
     * 宅配の個数構成比を取得します。
     * @return 宅配の個数構成比
     */
    public BigDecimal getKosuKouseiHiTakuhai() {
        return kosuKouseiTakuhai;
    }
    /**
     * 宅配の個数構成比を設定します。
     * @param kosuKouseiTakuhai 宅配の個数構成比
     */
    public void setKosuKouseiHiTakuhai(BigDecimal kosuKouseiTakuhai) {
        this.kosuKouseiTakuhai = kosuKouseiTakuhai;
    }
    
    /**
     * 外販個数を取得します。
     * @return 外販個数
     */
	public BigDecimal getKosuGaihan() {
		return kosuGaihan;
	}
	/**
     * 外販個数を設定します。
     * @param kosuGaihan 外販個数
     */
	public void setKosuGaihan(BigDecimal kosuGaihan) {
		this.kosuGaihan = kosuGaihan;
	}
	
	/**
     * 外販構成比を取得します。
     * @return 外販構成比
     */
	public BigDecimal getKosuKouseiHiGaihan() {
		return kosuKouseiGaihan;
	}
	/**
     * 外販構成比を設定します。
     * @param kosuKouseiGaihan 外販構成比
     */
	public void setKosuKouseiHiGaihan(BigDecimal kosuKouseiGaihan) {
		this.kosuKouseiGaihan = kosuKouseiGaihan;
	}
	
    /**
     * 種別７個数を取得します。
     * @return 種別７個数
     */
	public BigDecimal getKosuSyubetsu07() {
		return kosuSyubetsu07;
	}
	/**
     * 種別７個数を設定します。
     * @param kosuSyubetsu07 種別７個数
     */
	public void setKosuSyubetsu07(BigDecimal kosuSyubetsu07) {
		this.kosuSyubetsu07 = kosuSyubetsu07;
	}
	
	/**
     * 種別７個数構成比を取得します。
     * @return 種別７個数構成比
     */
	public BigDecimal getKosuKouseiHiSyubetsu07() {
		return kosuKouseiSyubetsu07;
	}
	/**
     * 種別７個数構成比を設定します。
     * @param kosuKouseiSyubetsu07 種別７個数構成比
     */
	public void setKosuKouseiHiSyubetsu07(BigDecimal kosuKouseiSyubetsu07) {
		this.kosuKouseiSyubetsu07 = kosuKouseiSyubetsu07;
	}
	
    /**
     * 種別８個数を取得します。
     * @return 種別８個数
     */
	public BigDecimal getKosuSyubetsu08() {
		return kosuSyubetsu08;
	}
	/**
     * 種別８個数を設定します。
     * @param kosuSyubetsu08 種別８個数
     */
	public void setKosuSyubetsu08(BigDecimal kosuSyubetsu08) {
		this.kosuSyubetsu08 = kosuSyubetsu08;
	}
	
	/**
     * 種別８個数構成比を取得します。
     * @return 種別８個数構成比
     */
	public BigDecimal getKosuKouseiHiSyubetsu08() {
		return kosuKouseiSyubetsu08;
	}
	/**
     * 種別８個数構成比を設定します。
     * @param kosuKouseiSyubetsu08 種別８個数構成比
     */
	public void setKosuKouseiHiSyubetsu08(BigDecimal kosuKouseiSyubetsu08) {
		this.kosuKouseiSyubetsu08 = kosuKouseiSyubetsu08;
	}
	
    /**
     * 種別９個数を取得します。
     * @return 種別９個数
     */
	public BigDecimal getKosuSyubetsu09() {
		return kosuSyubetsu09;
	}
	/**
     * 種別９個数を設定します。
     * @param kosuSyubetsu09 種別９個数
     */
	public void setKosuSyubetsu09(BigDecimal kosuSyubetsu09) {
		this.kosuSyubetsu09 = kosuSyubetsu09;
	}
	
	/**
     * 種別９個数構成比を取得します。
     * @return 種別９個数構成比
     */
	public BigDecimal getKosuKouseiHiSyubetsu09() {
		return kosuKouseiSyubetsu09;
	}
	/**
     * 種別９個数構成比を設定します。
     * @param kosuKouseiSyubetsu09 種別９個数構成比
     */
	public void setKosuKouseiHiSyubetsu09(BigDecimal kosuKouseiSyubetsu09) {
		this.kosuKouseiSyubetsu09 = kosuKouseiSyubetsu09;
	}
	
    /**
     * 種別１０個数を取得します。
     * @return 種別１０個数
     */
	public BigDecimal getKosuSyubetsu10() {
		return kosuSyubetsu10;
	}
	/**
     * 種別１０個数を設定します。
     * @param kosuSyubetsu10 種別１０個数
     */
	public void setKosuSyubetsu10(BigDecimal kosuSyubetsu10) {
		this.kosuSyubetsu10 = kosuSyubetsu10;
	}
	
	/**
     * 種別１０個数構成比を取得します。
     * @return 種別１０個数構成比
     */
	public BigDecimal getKosuKouseiHiSyubetsu10() {
		return kosuKouseiSyubetsu10;
	}
	/**
     * 種別１０個数構成比を設定します。
     * @param kosuKouseiSyubetsu10 種別10個数構成比
     */
	public void setKosuKouseiHiSyubetsu10(BigDecimal kosuKouseiSyubetsu10) {
		this.kosuKouseiSyubetsu10 = kosuKouseiSyubetsu10;
	}
	
    /**
     * ネットテイク個数を取得します。
     * @return ネットテイク個数
     */
	public BigDecimal getKosuNettake() {
		return kosuNettake;
	}
	/**
     * ネットテイク個数を設定します。
     * @param kosuNettake ネットテイク個数
     */
	public void setKosuNettake(BigDecimal kosuNettake) {
		this.kosuNettake = kosuNettake;
	}
	
	/**
     * ネットテイク構成比を取得します。
     * @return ネットテイク構成比
     */
	public BigDecimal getKosuKouseiHiNettake() {
		return kosuKouseiNettake;
	}
	/**
     * ネットテイク構成比を設定します。
     * @param kosuKouseiNettake ネットテイク構成比
     */
	public void setKosuKouseiHiNettake(BigDecimal kosuKouseiNettake) {
		this.kosuKouseiNettake = kosuKouseiNettake;
	}
	
    /**
     * ネット宅配個数を取得します。
     * @return ネット宅配個数
     */
	public BigDecimal getKosuNettakuhai() {
		return kosuNettakuhai;
	}
	/**
     * ネット宅配個数を設定します。
     * @param kosuNettakuhai ネット宅配個数
     */
	public void setKosuNettakuhai(BigDecimal kosuNettakuhai) {
		this.kosuNettakuhai = kosuNettakuhai;
	}
	
	/**
     * ネット宅配構成比を取得します。
     * @return ネット宅配構成比
     */
	public BigDecimal getKosuKouseiHiNettakuhai() {
		return kosuKouseiNettakuhai;
	}
	/**
     * ネット宅配構成比を設定します。
     * @param kosuKouseiNettakuhai ネット宅配構成比
     */
	public void setKosuKouseiHiNettakuhai(BigDecimal kosuKouseiNettakuhai) {
		this.kosuKouseiNettakuhai = kosuKouseiNettakuhai;
	}

    /**
     * 種別１３個数を取得します。
     * @return 種別１３個数
     */
	public BigDecimal getKosuSyubetsu13() {
		return kosuSyubetsu13;
	}
	/**
     * 種別１３個数を設定します。
     * @param kosuSyubetsu13 種別１３個数
     */
	public void setKosuSyubetsu13(BigDecimal kosuSyubetsu13) {
		this.kosuSyubetsu13 = kosuSyubetsu13;
	}
	
	/**
     * 種別１３個数構成比を取得します。
     * @return 種別１３個数構成比
     */
	public BigDecimal getKosuKouseiHiSyubetsu13() {
		return kosuKouseiSyubetsu13;
	}
	/**
     * 種別１３個数構成比を設定します。
     * @param kosuKouseiSyubetsu13 種別13個数構成比
     */
	public void setKosuKouseiHiSyubetsu13(BigDecimal kosuKouseiSyubetsu13) {
		this.kosuKouseiSyubetsu13 = kosuKouseiSyubetsu13;
	}
	
    /**
     * 種別１４個数を取得します。
     * @return 種別１４個数
     */
	public BigDecimal getKosuSyubetsu14() {
		return kosuSyubetsu14;
	}
	/**
     * 種別１４個数を設定します。
     * @param kosuSyubetsu14 種別１４個数
     */
	public void setKosuSyubetsu14(BigDecimal kosuSyubetsu14) {
		this.kosuSyubetsu14 = kosuSyubetsu14;
	}
	
	/**
     * 種別１４個数構成比を取得します。
     * @return 種別１４個数構成比
     */
	public BigDecimal getKosuKouseiHiSyubetsu14() {
		return kosuKouseiSyubetsu14;
	}
	/**
     * 種別１４個数構成比を設定します。
     * @param kosuKouseiSyubetsu14 種別１４個数構成比
     */
	public void setKosuKouseiHiSyubetsu14(BigDecimal kosuKouseiSyubetsu14) {
		this.kosuKouseiSyubetsu14 = kosuKouseiSyubetsu14;
	}
	
    /**
     * 種別１５個数を取得します。
     * @return 種別１５個数
     */
	public BigDecimal getKosuSyubetsu15() {
		return kosuSyubetsu15;
	}
	/**
     * 種別１５個数を設定します。
     * @param kosuSyubetsu15 種別１５個数
     */
	public void setKosuSyubetsu15(BigDecimal kosuSyubetsu15) {
		this.kosuSyubetsu15 = kosuSyubetsu15;
	}
	
	/**
     * 種別１５個数構成比を取得します。
     * @return 種別１５個数構成比
     */
	public BigDecimal getKosuKouseiHiSyubetsu15() {
		return kosuKouseiSyubetsu15;
	}
	/**
     * 種別１５個数構成比を設定します。
     * @param kosuKouseiSyubetsu15 種別１５個数構成比
     */
	public void setKosuKouseiHiSyubetsu15(BigDecimal kosuKouseiSyubetsu15) {
		this.kosuKouseiSyubetsu15 = kosuKouseiSyubetsu15;
	}
	
//	/**
//     * 外販・その他個数を取得します。
//     * @return 外販・その他個数
//     */
//    public BigDecimal getKosuEtc() {
//        return kosuEtc;
//    }
//    /**
//     * 外販・その他個数を設定します。
//     * @param kosuEtc 外販・その他個数
//     */
//    public void setKosuEtc(BigDecimal kosuEtc) {
//        this.kosuEtc = kosuEtc;
//    }
//    
//    /**
//     * 外販・その他の個数構成比を取得します。
//     * @return 外販・その他の個数構成比
//     */
//    public BigDecimal getKosuKouseiHiEtc() {
//        return kosuKouseiEtc;
//    }
//    /**
//     * 外販・その他の個数構成比を設定します。
//     * @param kosuKouseiEtc 外販・その他の個数構成比
//     */
//    public void setKosuKouseiHiEtc(BigDecimal kosuKouseiEtc) {
//        this.kosuKouseiEtc = kosuKouseiEtc;
//    }
    
}
