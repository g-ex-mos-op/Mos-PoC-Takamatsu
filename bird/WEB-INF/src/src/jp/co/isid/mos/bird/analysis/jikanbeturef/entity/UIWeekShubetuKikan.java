package jp.co.isid.mos.bird.analysis.jikanbeturef.entity;

import java.math.BigDecimal;

public class UIWeekShubetuKikan extends UAWeekTime {
    
    public static final String TABLE = "BD18JSSL";
    
    public static final String weekType_COLUMN = "WEEK_TYPE";
    
    public static final String weekKbn_COLUMN = "WEEK_KBN";
    
    public static final String kyakusuEat_COLUMN = "KYAKUSU_EAT";
    
    public static final String kingakuEat_COLUMN = "KINGAKU_EAT";
    
    public static final String kyakusuTake_COLUMN = "KYAKUSU_TAKE";
    
    public static final String kingakuTake_COLUMN = "KINGAKU_TAKE";
    
    public static final String kyakusuTel_COLUMN = "KYAKUSU_TEL";
    
    public static final String kingakuTel_COLUMN = "KINGAKU_TEL";
    
    public static final String kyakusuDrive_COLUMN = "KYAKUSU_DRIVE";
    
    public static final String kingakuDrive_COLUMN = "KINGAKU_DRIVE";
    
    public static final String kyakusuTakuhai_COLUMN = "KYAKUSU_TAKUHAI";
    
    public static final String kingakuTakuhai_COLUMN = "KINGAKU_TAKUHAI";
    
    public static final String kyakusuGaihan_COLUMN = "KYAKUSU_GAIHAN";
    
    public static final String kingakuGaihan_COLUMN = "KINGAKU_GAIHAN";
    
    public static final String kyakusuSyubetsu07_COLUMN = "KYAKUSU_SYUBETSU_07";
    
    public static final String kingakuSyubetsu07_COLUMN = "KINGAKU_SYUBETSU_07";
    
    public static final String kyakusuSyubetsu08_COLUMN = "KYAKUSU_SYUBETSU_08";
    
    public static final String kingakuSyubetsu08_COLUMN = "KINGAKU_SYUBETSU_08";
    
    public static final String kyakusuSyubetsu09_COLUMN = "KYAKUSU_SYUBETSU_09";
    
    public static final String kingakuSyubetsu09_COLUMN = "KINGAKU_SYUBETSU_09";
    
    public static final String kyakusuSyubetsu10_COLUMN = "KYAKUSU_SYUBETSU_10";
    
    public static final String kingakuSyubetsu10_COLUMN = "KINGAKU_SYUBETSU_10";
    
    public static final String kyakusuNettake_COLUMN = "KYAKUSU_NET_TAKE";
    
    public static final String kingakuNettake_COLUMN = "KINGAKU_NET_TAKE";
    
    public static final String kyakusuNetTakuhai_COLUMN = "KYAKUSU_NET_TAKUHAI";
    
    public static final String kingakuNetTakuhai_COLUMN = "KINGAKU_NET_TAKUHAI";
    
    public static final String kyakusuSyubetsu13_COLUMN = "KYAKUSU_SYUBETSU_13";
    
    public static final String kingakuSyubetsu13_COLUMN = "KINGAKU_SYUBETSU_13";
    
    public static final String kyakusuSyubetsu14_COLUMN = "KYAKUSU_SYUBETSU_14";
    
    public static final String kingakuSyubetsu14_COLUMN = "KINGAKU_SYUBETSU_14";
    
    public static final String kyakusuSyubetsu15_COLUMN = "KYAKUSU_SYUBETSU_15";
    
    public static final String kingakuSyubetsu15_COLUMN = "KINGAKU_SYUBETSU_15";
    
    public static final String kyakusuEtc_COLUMN = "KYAKUSU_ETC";
    
    public static final String kingakuEtc_COLUMN = "KINGAKU_ETC";
    
    public static final String kyakusuAll_COLUMN = "KYAKUSU_ALL";
    
    public static final String kingakuAll_COLUMN = "KINGAKU_ALL";
    
    public static final String kingakuConp_COLUMN = "KINGAKU_COMPONENT";
    
    /**
     * —j“úí•Ê
     */
    private BigDecimal weekType;
    
    /**
     * —j“ú‹æ•ª
     */
    private BigDecimal weekKbn;
    
    /**
     * E^‚s ”„ã
     */
    private BigDecimal kingakuEat;
    
    /**
     * ‚s^‚n”„ã
     */
    private BigDecimal kingakuTake;
    
    /**
     * ‚s‚d‚k ”„ã
     */
    private BigDecimal kingakuTel;
    
    /**
     * ‚c^‚s ”„ã
     */
    private BigDecimal kingakuDrive;
    
    /**
     * ‘î”z”„ã
     */
    private BigDecimal kingakuTakuhai;
    
    /**
     * ŠO”Ì”„ã
     */
    private BigDecimal kingakuGaihan = new BigDecimal("0");
    
    /**
     * í•Ê‚V”„ã
     */
    private BigDecimal kingakuSyubetsu07 = new BigDecimal("0");
    
    /**
     * í•Ê‚W”„ã
     */
    private BigDecimal kingakuSyubetsu08 = new BigDecimal("0");
    
    /**
     * í•Ê‚X”„ã
     */
    private BigDecimal kingakuSyubetsu09 = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚O”„ã
     */
    private BigDecimal kingakuSyubetsu10 = new BigDecimal("0");
    
    /**
     * ƒlƒbƒgƒeƒCƒN”„ã
     */
    private BigDecimal kingakuNettake = new BigDecimal("0");
    
    /**
     * ƒlƒbƒg‘î”z”„ã
     */
    private BigDecimal kingakuNetTakuhai = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚R”„ã
     */
    private BigDecimal kingakuSyubetsu13 = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚S”„ã
     */
    private BigDecimal kingakuSyubetsu14 = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚T”„ã
     */
    private BigDecimal kingakuSyubetsu15 = new BigDecimal("0");
    
    /**
     * ŠO”ÌE‚»‚Ì‘¼”„ã
     */
    private BigDecimal kingakuEtc;
    
    /**
     * E^‚s ‹q”
     */
    private BigDecimal kyakusuEat;
    
    /**
     * ‚s^‚n‹q”
     */
    private BigDecimal kyakusuTake;
    
    /**
     * ‚s‚d‚k ‹q”
     */
    private BigDecimal kyakusuTel;
    
    /**
     * ‚c^‚s ‹q”
     */
    private BigDecimal kyakusuDrive;
    
    /**
     * ‘î”z‹q”
     */
    private BigDecimal kyakusuTakuhai;
    
    /**
     * ŠO”Ì‹q”
     */
    private BigDecimal kyakusuGaihan = new BigDecimal("0");
    
    /**
     * í•Ê‚V‹q”
     */
    private BigDecimal kyakusuSyubetsu07 = new BigDecimal("0");
    
    /**
     * í•Ê‚W‹q”
     */
    private BigDecimal kyakusuSyubetsu08 = new BigDecimal("0");
    
    /**
     * í•Ê‚X‹q”
     */
    private BigDecimal kyakusuSyubetsu09 = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚O‹q”
     */
    private BigDecimal kyakusuSyubetsu10 = new BigDecimal("0");
    
    /**
     * ƒlƒbƒgƒeƒCƒN‹q”
     */
    private BigDecimal kyakusuNettake = new BigDecimal("0");
    
    /**
     * ƒlƒbƒg‘î”z‹q”
     */
    private BigDecimal kyakusuNetTakuhai = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚R‹q”
     */
    private BigDecimal kyakusuSyubetsu13 = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚S‹q”
     */
    private BigDecimal kyakusuSyubetsu14 = new BigDecimal("0");
    
    /**
     * í•Ê‚P‚T‹q”
     */
    private BigDecimal kyakusuSyubetsu15 = new BigDecimal("0");
    
    /**
     * ŠO”ÌE‚»‚Ì‘¼‹q”
     */
    private BigDecimal kyakusuEtc;
    
    /**
     * ‡ŒvŒ”
     */
    private BigDecimal kyakusuAll;

    /**
     * ‡ŒvŒ”
     */
    private BigDecimal kingakuAll;
    
    /**
     * ‹àŠz\¬”ä
     */
    private BigDecimal kingakuConpl;
    
//    /**
//     * —j“úí•Ê‚ğæ“¾‚µ‚Ü‚·B
//     * @return —j“úí•Ê
//     */
//    public BigDecimal getWeekType() {
//        return weekType;
//    }
//    /**
//     * —j“úí•Ê‚ğİ’è‚µ‚Ü‚·B
//     * @param weekType —j“úí•Ê
//     */
//    public void setWeekType(BigDecimal weekType) {
//        this.weekType = weekType;
//    }
    
    /**
     * —j“ú‹æ•ª‚ğæ“¾‚µ‚Ü‚·B
     * @return —j“ú‹æ•ª
     */
    public BigDecimal getWeekKbn() {
        return weekKbn;
    }
    /**
     * —j“ú‹æ•ª‚ğİ’è‚µ‚Ü‚·B
     * @param weekKbn —j“ú‹æ•ª
     */
    public void setWeekKbn(BigDecimal weekKbn) {
        this.weekKbn = weekKbn;
    }
    
    /**
     * E^‚s ”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return E^‚s ”„ã
     */
    public BigDecimal getKingakuEat() {
        return kingakuEat;
    }
    /**
     * E^‚s ”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuEat E^‚s ”„ã
     */
    public void setKingakuEat(BigDecimal kingakuEat) {
        this.kingakuEat = kingakuEat;
    }
    
    /**
     * ‚s^‚n”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚s^‚n”„ã
     */
    public BigDecimal getKingakuTake() {
        return kingakuTake;
    }
    /**
     * ‚s^‚n”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuTake ‚s^‚n”„ã
     */
    public void setKingakuTake(BigDecimal kingakuTake) {
        this.kingakuTake = kingakuTake;
    }
    
    /**
     * ‚s‚d‚k ”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚s‚d‚k ”„ã
     */
    public BigDecimal getKingakuTel() {
        return kingakuTel;
    }
    /**
     * ‚s‚d‚k ”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuTel ‚s‚d‚k ”„ã
     */
    public void setKingakuTel(BigDecimal kingakuTel) {
        this.kingakuTel = kingakuTel;
    }
    
    /**
     * ‚c^‚s ”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚c^‚s ”„ã
     */
    public BigDecimal getKingakuDrive() {
        return kingakuDrive;
    }
    /**
     * ‚c^‚s ”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuDrive ‚c^‚s ”„ã
     */
    public void setKingakuDrive(BigDecimal kingakuDrive) {
        this.kingakuDrive = kingakuDrive;
    }
    
    /**
     * ‘î”z”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ‘î”z”„ã
     */
    public BigDecimal getKingakuTakuhai() {
        return kingakuTakuhai;
    }
    /**
     * ‘î”z”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuTakuhai ‘î”z”„ã
     */
    public void setKingakuTakuhai(BigDecimal kingakuTakuhai) {
        this.kingakuTakuhai = kingakuTakuhai;
    }
    
    /**
     * ŠO”Ì”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ŠO”Ì”„ã
     */
	public BigDecimal getKingakuGaihan() {
		return kingakuGaihan;
	}
	/**
     * ŠO”Ì”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuGaihan ŠO”Ì”„ã
     */
	public void setKingakuGaihan(BigDecimal kingakuGaihan) {
		this.kingakuGaihan = kingakuGaihan;
	}
	
	/**
     * í•Ê‚V”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return í•Ê‚V”„ã
     */
	public BigDecimal getKingakuSyubetsu07() {
		return kingakuSyubetsu07;
	}
	/**
     * í•Ê‚V”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuSyubetsu07 í•Ê‚V”„ã
     */
	public void setKingakuSyubetsu07(BigDecimal kingakuSyubetsu07) {
		this.kingakuSyubetsu07 = kingakuSyubetsu07;
	}
	
	/**
     * í•Ê‚W”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return í•Ê‚W”„ã
     */
	public BigDecimal getKingakuSyubetsu08() {
		return kingakuSyubetsu08;
	}
	/**
     * í•Ê‚W”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuSyubetsu08 í•Ê‚W”„ã
     */
	public void setKingakuSyubetsu08(BigDecimal kingakuSyubetsu08) {
		this.kingakuSyubetsu08 = kingakuSyubetsu08;
	}
	
	/**
     * í•Ê‚X”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return í•Ê‚X”„ã
     */
	public BigDecimal getKingakuSyubetsu09() {
		return kingakuSyubetsu09;
	}
	/**
     * í•Ê‚X”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuSyubetsu09 í•Ê‚X”„ã
     */
	public void setKingakuSyubetsu09(BigDecimal kingakuSyubetsu09) {
		this.kingakuSyubetsu09 = kingakuSyubetsu09;
	}
	
	/**
     * í•Ê‚P‚O”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return í•Ê‚P‚O”„ã
     */
	public BigDecimal getKingakuSyubetsu10() {
		return kingakuSyubetsu10;
	}
	/**
     * í•Ê‚P‚O”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuSyubetsu10 í•Ê‚P‚O”„ã
     */
	public void setKingakuSyubetsu10(BigDecimal kingakuSyubetsu10) {
		this.kingakuSyubetsu10 = kingakuSyubetsu10;
	}
	
	/**
     * ƒlƒbƒgƒeƒCƒN”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ƒlƒbƒgƒeƒCƒN”„ã
     */
	public BigDecimal getKingakuNettake() {
		return kingakuNettake;
	}
	/**
     * ƒlƒbƒgƒeƒCƒN”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuNettake ƒlƒbƒgƒeƒCƒN”„ã
     */
	public void setKingakuNettake(BigDecimal kingakuNettake) {
		this.kingakuNettake = kingakuNettake;
	}
	
	/**
     * ƒlƒbƒg‘î”z”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ƒlƒbƒg‘î”z”„ã
     */
	public BigDecimal getKingakuNetTakuhai() {
		return kingakuNetTakuhai;
	}
	/**
     * ƒlƒbƒg‘î”z”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuNetTakuhai ƒlƒbƒg‘î”z”„ã
     */
	public void setKingakuNetTakuhai(BigDecimal kingakuNetTakuhai) {
		this.kingakuNetTakuhai = kingakuNetTakuhai;
	}
	
	/**
     * í•Ê‚P‚R”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return í•Ê‚P‚R”„ã
     */
	public BigDecimal getKingakuSyubetsu13() {
		return kingakuSyubetsu13;
	}
	/**
     * í•Ê‚P‚R”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuSyubetsu13 í•Ê‚P‚R”„ã
     */
	public void setKingakuSyubetsu13(BigDecimal kingakuSyubetsu13) {
		this.kingakuSyubetsu13 = kingakuSyubetsu13;
	}
	
	/**
     * í•Ê‚P‚S”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return í•Ê‚P‚S”„ã
     */
	public BigDecimal getKingakuSyubetsu14() {
		return kingakuSyubetsu14;
	}
	/**
     * í•Ê‚P‚S”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuSyubetsu14 í•Ê‚P‚S”„ã
     */
	public void setKingakuSyubetsu14(BigDecimal kingakuSyubetsu14) {
		this.kingakuSyubetsu14 = kingakuSyubetsu14;
	}
	
	/**
     * í•Ê‚P‚T”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return í•Ê‚P‚T”„ã
     */
	public BigDecimal getKingakuSyubetsu15() {
		return kingakuSyubetsu15;
	}
	/**
     * í•Ê‚P‚T”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuSyubetsu15 í•Ê‚P‚T”„ã
     */
	public void setKingakuSyubetsu15(BigDecimal kingakuSyubetsu15) {
		this.kingakuSyubetsu15 = kingakuSyubetsu15;
	}
    
    /**
     * ŠO”ÌE‚»‚Ì‘¼”„ã‚ğæ“¾‚µ‚Ü‚·B
     * @return ŠO”ÌE‚»‚Ì‘¼”„ã
     */
    public BigDecimal getKingakuEtc() {
        return kingakuEtc;
    }
    /**
     * ŠO”ÌE‚»‚Ì‘¼”„ã‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuEtc ŠO”ÌE‚»‚Ì‘¼”„ã
     */
    public void setKingakuEtc(BigDecimal kingakuEtc) {
        this.kingakuEtc = kingakuEtc;
    }
    
    /**
     * E^‚s ‹q”‚ğæ“¾‚µ‚Ü‚·B
     * @return E^‚s ‹q”
     */
    public BigDecimal getKyakusuEat() {
        return kyakusuEat;
    }
    /**
     * E^‚s ‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuEat E^‚s ‹q”
     */
    public void setKyakusuEat(BigDecimal kyakusuEat) {
        this.kyakusuEat = kyakusuEat;
    }
    
    /**
     * ‚s^‚n‹q”‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚s^‚n‹q”
     */
    public BigDecimal getKyakusuTake() {
        return kyakusuTake;
    }
    /**
     * ‚s^‚n‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuTake ‚s^‚n‹q”
     */
    public void setKyakusuTake(BigDecimal kyakusuTake) {
        this.kyakusuTake = kyakusuTake;
    }
    
    /**
     * ‚s‚d‚k ‹q”‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚s‚d‚k ‹q”
     */
    public BigDecimal getKyakusuTel() {
        return kyakusuTel;
    }
    /**
     * ‚s‚d‚k ‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuTel ‚s‚d‚k ‹q”
     */
    public void setKyakusuTel(BigDecimal kyakusuTel) {
        this.kyakusuTel = kyakusuTel;
    }
    
    /**
     * ‚c^‚s ‹q”‚ğæ“¾‚µ‚Ü‚·B
     * @return ‚c^‚s ‹q”
     */
    public BigDecimal getKyakusuDrive() {
        return kyakusuDrive;
    }
    /**
     * ‚c^‚s ‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuDrive ‚c^‚s ‹q”
     */
    public void setKyakusuDrive(BigDecimal kyakusuDrive) {
        this.kyakusuDrive = kyakusuDrive;
    }
    
    /**
     * ‘î”z‹q”‚ğæ“¾‚µ‚Ü‚·B
     * @return ‘î”z‹q”
     */
    public BigDecimal getKyakusuTakuhai() {
        return kyakusuTakuhai;
    }
    /**
     * ‘î”z‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuTakuhai ‘î”z‹q”
     */
    public void setKyakusuTakuhai(BigDecimal kyakusuTakuhai) {
        this.kyakusuTakuhai = kyakusuTakuhai;
    }
    
    /**
     * ŠO”Ì‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return ŠO”Ì‹q”
	 */
	public BigDecimal getKyakusuGaihan() {
		return kyakusuGaihan;
	}
	/**
	 * ŠO”Ì‹q”İ’è‚µ‚Ü‚·B
	 * @param kyakusuGaihan ŠO”Ì‹q”
	 */
	public void setKyakusuGaihan(BigDecimal kyakusuGaihan) {
		this.kyakusuGaihan = kyakusuGaihan;
	}
	
	/**
     * í•Ê‚V‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return í•Ê‚V‹q”
	 */
	public BigDecimal getKyakusuSyubetsu07() {
		return kyakusuSyubetsu07;
	}
	/**
	 * í•Ê‚V‹q”‚ğİ’è‚µ‚Ü‚·B
	 * @param kyakusuSyubetsu07 í•Ê‚V‹q”
	 */
	public void setKyakusuSyubetsu07(BigDecimal kyakusuSyubetsu07) {
		this.kyakusuSyubetsu07 = kyakusuSyubetsu07;
	}
	
	/**
     * í•Ê8‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return í•Ê8‹q”
	 */
	public BigDecimal getKyakusuSyubetsu08() {
		return kyakusuSyubetsu08;
	}
	/**
	 * í•Ê8‹q”‚ğİ’è‚µ‚Ü‚·B
	 * @param kyakusuSyubetsu08 í•Ê8‹q”
	 */
	public void setKyakusuSyubetsu08(BigDecimal kyakusuSyubetsu08) {
		this.kyakusuSyubetsu08 = kyakusuSyubetsu08;
	}
	
	/**
     * í•Ê9‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return í•Ê9‹q”
	 */
	public BigDecimal getKyakusuSyubetsu09() {
		return kyakusuSyubetsu09;
	}
	/**
	 * í•Ê9‹q”‚ğİ’è‚µ‚Ü‚·B
	 * @param kyakusuSyubetsu09 í•Ê9‹q”
	 */
	public void setKyakusuSyubetsu09(BigDecimal kyakusuSyubetsu09) {
		this.kyakusuSyubetsu09 = kyakusuSyubetsu09;
	}
	
	/**
     * í•Ê10‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return í•Ê10‹q”
	 */
	public BigDecimal getKyakusuSyubetsu10() {
		return kyakusuSyubetsu10;
	}
	/**
	 * í•Ê10‹q”‚ğİ’è‚µ‚Ü‚·B
	 * @param kyakusuSyubetsu10 í•Ê10‹q”
	 */
	public void setKyakusuSyubetsu10(BigDecimal kyakusuSyubetsu10) {
		this.kyakusuSyubetsu10 = kyakusuSyubetsu10;
	}
	
	/**
     * ƒlƒbƒgƒeƒCƒN‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return ƒlƒbƒgƒeƒCƒN‹q”
	 */
	public BigDecimal getKyakusuNettake() {
		return kyakusuNettake;
	}
	/**
	 * ƒlƒbƒgƒeƒCƒN‹q”İ’è‚µ‚Ü‚·B
	 * @param kyakusuNettake ƒlƒbƒgƒeƒCƒN‹q”
	 */
	public void setKyakusuNettake(BigDecimal kyakusuNettake) {
		this.kyakusuNettake = kyakusuNettake;
	}
	
	/**
     * ƒlƒbƒg‘î”z‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return ƒlƒbƒg‘î”z‹q”
	 */
	public BigDecimal getKyakusuNetTakuhai() {
		return kyakusuNetTakuhai;
	}
	/**
	 * ƒlƒbƒg‘î”z‹q”İ’è‚µ‚Ü‚·B
	 * @param kyakusuNetTakuhai ƒlƒbƒg‘î”z‹q”
	 */
	public void setKyakusuNetTakuhai(BigDecimal kyakusuNetTakuhai) {
		this.kyakusuNetTakuhai = kyakusuNetTakuhai;
	}
	
	/**
     * í•Ê13‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return í•Ê13‹q”
	 */
	public BigDecimal getKyakusuSyubetsu13() {
		return kyakusuSyubetsu13;
	}
	/**
	 * í•Ê13‹q”‚ğİ’è‚µ‚Ü‚·B
	 * @param kyakusuSyubetsu13 í•Ê13‹q”
	 */
	public void setKyakusuSyubetsu13(BigDecimal kyakusuSyubetsu13) {
		this.kyakusuSyubetsu13 = kyakusuSyubetsu13;
	}
	
	/**
     * í•Ê14‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return í•Ê14‹q”
	 */
	public BigDecimal getKyakusuSyubetsu14() {
		return kyakusuSyubetsu14;
	}
	/**
	 * í•Ê14‹q”‚ğİ’è‚µ‚Ü‚·B
	 * @param kyakusuSyubetsu14 í•Ê14‹q”
	 */
	public void setKyakusuSyubetsu14(BigDecimal kyakusuSyubetsu14) {
		this.kyakusuSyubetsu14 = kyakusuSyubetsu14;
	}
	
	/**
     * í•Ê15‹q”‚ğæ“¾‚µ‚Ü‚·B
	 * @return í•Ê15‹q”
	 */
	public BigDecimal getKyakusuSyubetsu15() {
		return kyakusuSyubetsu15;
	}
	/**
	 * í•Ê15‹q”‚ğİ’è‚µ‚Ü‚·B
	 * @param kyakusuSyubetsu15 í•Ê15‹q”
	 */
	public void setKyakusuSyubetsu15(BigDecimal kyakusuSyubetsu15) {
		this.kyakusuSyubetsu15 = kyakusuSyubetsu15;
	}
    
    /**
     * ŠO”ÌE‚»‚Ì‘¼‹q”‚ğæ“¾‚µ‚Ü‚·B
     * @return ŠO”ÌE‚»‚Ì‘¼‹q”
     */
    public BigDecimal getKyakusuEtc() {
        return kyakusuEtc;
    }
    /**
     * ŠO”ÌE‚»‚Ì‘¼‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuEtc ŠO”ÌE‚»‚Ì‘¼‹q”
     */
    public void setKyakusuEtc(BigDecimal kyakusuEtc) {
        this.kyakusuEtc = kyakusuEtc;
    }
    /**
     * ‡Œv‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuAll@‡Œv‹q”
     */
    public BigDecimal getKingakuAll() {
        return kingakuAll;
    }
    /**
     * ‡Œv‹q”‚ğİ’è‚µ‚Ü‚·B
     * @param kingakuAll@‡Œv‹q”
     */
    public void setKingakuAll(BigDecimal kingakuAll) {
        this.kingakuAll = kingakuAll;
    }
    /**
     * ‡Œv‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuAll@‡Œv‹àŠz
     */
    public BigDecimal getKyakusuAll() {
        return kyakusuAll;
    }
    /**
     * ‡Œv‹àŠz‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuAll@‡Œv‹àŠz
     */
    public void setKyakusuAll(BigDecimal kyakusuAll) {
        this.kyakusuAll = kyakusuAll;
    }
    /**
     * ‹àŠz\¬”ä‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuAll@‡Œv‹àŠz
     */
    public BigDecimal getkingakuConp() {
        return kingakuConpl;
    }
    /**
     * ‹àŠz\¬”ä‚ğİ’è‚µ‚Ü‚·B
     * @param kyakusuAll@‡Œv‹àŠz
     */
    public void setkingakuConp(BigDecimal kingakuConpl) {
        this.kingakuConpl = kingakuConpl;
    }
}