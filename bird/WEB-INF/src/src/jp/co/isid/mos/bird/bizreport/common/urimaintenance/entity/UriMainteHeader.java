package jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity;

/**
 * 売上修正 共通項目名クラス
 * 
 * @author mwatanabe
 */
public class UriMainteHeader {

    /************************/
    /**     各タブ共通     **/
    /************************/
    /** 対象店舗 */
    private String targetTenpo;
    /** 営業日 */
    private String eigyoDate;

    /************************/
    /**     売上金タブ     **/
    /************************/
    /** 売上高 */
    private String uriage;
    /** 消費税 */
    private String tax;
    /** 現金金額 */
    private String genkinKin;
    /** 実現金有高 */
    private String aridakaJitu;
    /** 現金過不足 */
    private String genkinKabusoku;
    /** 客数 */
    private String kyakusu;
    /** 取消件数 */
    private String canKen;
    /** 取消金額 */
    private String canKin;
    /** 更新者 */
    private String updateUser;

    /************************/
    /**   前受・売掛タブ   **/
    /************************/
    /** 集計区分2 */
    private String kkbnName2;
    /** 集計区分3 */
    private String kkbnName3;
    /** 集計区分4 */
    private String kkbnName4;
    /** 集計区分5 */
    private String kkbnName5;
    /** 集計区分6 */
    private String kkbnName6;

    /** 集計区分7 */
    private String kkbnName7;
    /** 集計区分8 */
    private String kkbnName8;
    /** 集計区分9 */
    private String kkbnName9;
    /** 集計区分10 */
    private String kkbnName10;
    /** 集計区分11 */
    private String kkbnName11;
    
    /** 集計区分2:[明細]リンク判断値 */
    private boolean onSkbn2Link = false;
    /** 集計区分3:[明細]リンク判断値 */
    private boolean onSkbn3Link = false;
    /** 集計区分4:[明細]リンク判断値 */
    private boolean onSkbn4Link = false;
    /** 集計区分5:[明細]リンク判断値 */
    private boolean onSkbn5Link = false;
    /** 集計区分6:[明細]リンク判断値 */
    private boolean onSkbn6Link = false;

    /** 集計区分7:[明細]リンク判断値 */
    private boolean onSkbn7Link = false;
    /** 集計区分8:[明細]リンク判断値 */
    private boolean onSkbn8Link = false;
    /** 集計区分9:[明細]リンク判断値 */
    private boolean onSkbn9Link = false;
    /** 集計区分10:[明細]リンク判断値 */
    private boolean onSkbn10Link = false;
    /** 集計区分11:[明細]リンク判断値 */
    private boolean onSkbn11Link = false;

    /************************/
    /**      販売タブ      **/
    /************************/
    /** チケット販売1 */
    private String tcktName1;
    /** チケット販売2 */
    private String tcktName2;
    /** チケット販売3 */
    private String tcktName3;
    /** チケット販売4 */
    private String tcktName4;
    /** チケット販売5 */
    private String tcktName5;

    /** チケット販売6 */
    private String tcktName6;
    /** チケット販売7 */
    private String tcktName7;
    /** チケット販売8 */
    private String tcktName8;
    /** チケット販売9 */
    private String tcktName9;
    /** チケット販売10 */
    private String tcktName10;

    /** チケット販売11 */
    private String tcktName11;
    /** チケット販売12 */
    private String tcktName12;
    /** チケット販売13 */
    private String tcktName13;
    /** チケット販売14 */
    private String tcktName14;
    /** チケット販売15 */
    private String tcktName15;

    /************************/
    /**     値引きタブ     **/
    /************************/
    /** 値引区分1 */
    private String nkbnName1;
    /** 値引区分2 */
    private String nkbnName2;
    /** 値引区分3 */
    private String nkbnName3;

    /** 値引区分4 */
    private String nkbnName4;
    /** 値引区分5 */
    private String nkbnName5;
    /** 値引区分6 */
    private String nkbnName6;

    /** 値引区分7 */
    private String nkbnName7;
    /** 値引区分8 */
    private String nkbnName8;
    /** 値引区分9 */
    private String nkbnName9;

    ///////////////////////////////////////////////////////////////////////////////
    
    /**
     * @return aridakaJitu を戻します。
     */
    public String getAridakaJitu() {
        return aridakaJitu;
    }
    /**
     * @param aridakaJitu 設定する aridakaJitu。
     */
    public void setAridakaJitu(String aridakaJitu) {
        this.aridakaJitu = aridakaJitu;
    }
    /**
     * @return canKen を戻します。
     */
    public String getCanKen() {
        return canKen;
    }
    /**
     * @param canKen 設定する canKen。
     */
    public void setCanKen(String canKen) {
        this.canKen = canKen;
    }
    /**
     * @return canKin を戻します。
     */
    public String getCanKin() {
        return canKin;
    }
    /**
     * @param canKin 設定する canKin。
     */
    public void setCanKin(String canKin) {
        this.canKin = canKin;
    }
    /**
     * @return eigyoDate を戻します。
     */
    public String getEigyoDate() {
        return eigyoDate;
    }
    /**
     * @param eigyoDate 設定する eigyoDate。
     */
    public void setEigyoDate(String eigyoDate) {
        this.eigyoDate = eigyoDate;
    }
    /**
     * @return genkinKabusoku を戻します。
     */
    public String getGenkinKabusoku() {
        return genkinKabusoku;
    }
    /**
     * @param genkinKabusoku 設定する genkinKabusoku。
     */
    public void setGenkinKabusoku(String genkinKabusoku) {
        this.genkinKabusoku = genkinKabusoku;
    }
    /**
     * @return genkinKin を戻します。
     */
    public String getGenkinKin() {
        return genkinKin;
    }
    /**
     * @param genkinKin 設定する genkinKin。
     */
    public void setGenkinKin(String genkinKin) {
        this.genkinKin = genkinKin;
    }
    /**
     * @return kkbnName10 を戻します。
     */
    public String getKkbnName10() {
        return kkbnName10;
    }
    /**
     * @param kkbnName10 設定する kkbnName10。
     */
    public void setKkbnName10(String kkbnName10) {
        this.kkbnName10 = kkbnName10;
    }
    /**
     * @return kkbnName11 を戻します。
     */
    public String getKkbnName11() {
        return kkbnName11;
    }
    /**
     * @param kkbnName11 設定する kkbnName11。
     */
    public void setKkbnName11(String kkbnName11) {
        this.kkbnName11 = kkbnName11;
    }
    /**
     * @return kkbnName2 を戻します。
     */
    public String getKkbnName2() {
        return kkbnName2;
    }
    /**
     * @param kkbnName2 設定する kkbnName2。
     */
    public void setKkbnName2(String kkbnName2) {
        this.kkbnName2 = kkbnName2;
    }
    /**
     * @return kkbnName3 を戻します。
     */
    public String getKkbnName3() {
        return kkbnName3;
    }
    /**
     * @param kkbnName3 設定する kkbnName3。
     */
    public void setKkbnName3(String kkbnName3) {
        this.kkbnName3 = kkbnName3;
    }
    /**
     * @return kkbnName4 を戻します。
     */
    public String getKkbnName4() {
        return kkbnName4;
    }
    /**
     * @param kkbnName4 設定する kkbnName4。
     */
    public void setKkbnName4(String kkbnName4) {
        this.kkbnName4 = kkbnName4;
    }
    /**
     * @return kkbnName5 を戻します。
     */
    public String getKkbnName5() {
        return kkbnName5;
    }
    /**
     * @param kkbnName5 設定する kkbnName5。
     */
    public void setKkbnName5(String kkbnName5) {
        this.kkbnName5 = kkbnName5;
    }
    /**
     * @return kkbnName6 を戻します。
     */
    public String getKkbnName6() {
        return kkbnName6;
    }
    /**
     * @param kkbnName6 設定する kkbnName6。
     */
    public void setKkbnName6(String kkbnName6) {
        this.kkbnName6 = kkbnName6;
    }
    /**
     * @return kkbnName7 を戻します。
     */
    public String getKkbnName7() {
        return kkbnName7;
    }
    /**
     * @param kkbnName7 設定する kkbnName7。
     */
    public void setKkbnName7(String kkbnName7) {
        this.kkbnName7 = kkbnName7;
    }
    /**
     * @return kkbnName8 を戻します。
     */
    public String getKkbnName8() {
        return kkbnName8;
    }
    /**
     * @param kkbnName8 設定する kkbnName8。
     */
    public void setKkbnName8(String kkbnName8) {
        this.kkbnName8 = kkbnName8;
    }
    /**
     * @return kkbnName9 を戻します。
     */
    public String getKkbnName9() {
        return kkbnName9;
    }
    /**
     * @param kkbnName9 設定する kkbnName9。
     */
    public void setKkbnName9(String kkbnName9) {
        this.kkbnName9 = kkbnName9;
    }
    /**
     * @return kyakusu を戻します。
     */
    public String getKyakusu() {
        return kyakusu;
    }
    /**
     * @param kyakusu 設定する kyakusu。
     */
    public void setKyakusu(String kyakusu) {
        this.kyakusu = kyakusu;
    }
    /**
     * @return nkbnName1 を戻します。
     */
    public String getNkbnName1() {
        return nkbnName1;
    }
    /**
     * @param nkbnName1 設定する nkbnName1。
     */
    public void setNkbnName1(String nkbnName1) {
        this.nkbnName1 = nkbnName1;
    }
    /**
     * @return nkbnName2 を戻します。
     */
    public String getNkbnName2() {
        return nkbnName2;
    }
    /**
     * @param nkbnName2 設定する nkbnName2。
     */
    public void setNkbnName2(String nkbnName2) {
        this.nkbnName2 = nkbnName2;
    }
    /**
     * @return nkbnName3 を戻します。
     */
    public String getNkbnName3() {
        return nkbnName3;
    }
    /**
     * @param nkbnName3 設定する nkbnName3。
     */
    public void setNkbnName3(String nkbnName3) {
        this.nkbnName3 = nkbnName3;
    }
    /**
     * @return nkbnName4 を戻します。
     */
    public String getNkbnName4() {
        return nkbnName4;
    }
    /**
     * @param nkbnName4 設定する nkbnName4。
     */
    public void setNkbnName4(String nkbnName4) {
        this.nkbnName4 = nkbnName4;
    }
    /**
     * @return nkbnName5 を戻します。
     */
    public String getNkbnName5() {
        return nkbnName5;
    }
    /**
     * @param nkbnName5 設定する nkbnName5。
     */
    public void setNkbnName5(String nkbnName5) {
        this.nkbnName5 = nkbnName5;
    }
    /**
     * @return nkbnName6 を戻します。
     */
    public String getNkbnName6() {
        return nkbnName6;
    }
    /**
     * @param nkbnName6 設定する nkbnName6。
     */
    public void setNkbnName6(String nkbnName6) {
        this.nkbnName6 = nkbnName6;
    }
    /**
     * @return nkbnName7 を戻します。
     */
    public String getNkbnName7() {
        return nkbnName7;
    }
    /**
     * @param nkbnName7 設定する nkbnName7。
     */
    public void setNkbnName7(String nkbnName7) {
        this.nkbnName7 = nkbnName7;
    }
    /**
     * @return nkbnName8 を戻します。
     */
    public String getNkbnName8() {
        return nkbnName8;
    }
    /**
     * @param nkbnName8 設定する nkbnName8。
     */
    public void setNkbnName8(String nkbnName8) {
        this.nkbnName8 = nkbnName8;
    }
    /**
     * @return nkbnName9 を戻します。
     */
    public String getNkbnName9() {
        return nkbnName9;
    }
    /**
     * @param nkbnName9 設定する nkbnName9。
     */
    public void setNkbnName9(String nkbnName9) {
        this.nkbnName9 = nkbnName9;
    }
    /**
     * @return targetTenpo を戻します。
     */
    public String getTargetTenpo() {
        return targetTenpo;
    }
    /**
     * @param targetTenpo 設定する targetTenpo。
     */
    public void setTargetTenpo(String targetTenpo) {
        this.targetTenpo = targetTenpo;
    }
    /**
     * @return tax を戻します。
     */
    public String getTax() {
        return tax;
    }
    /**
     * @param tax 設定する tax。
     */
    public void setTax(String tax) {
        this.tax = tax;
    }
    /**
     * @return tcktName1 を戻します。
     */
    public String getTcktName1() {
        return tcktName1;
    }
    /**
     * @param tcktName1 設定する tcktName1。
     */
    public void setTcktName1(String tcktName1) {
        this.tcktName1 = tcktName1;
    }
    /**
     * @return tcktName10 を戻します。
     */
    public String getTcktName10() {
        return tcktName10;
    }
    /**
     * @param tcktName10 設定する tcktName10。
     */
    public void setTcktName10(String tcktName10) {
        this.tcktName10 = tcktName10;
    }
    /**
     * @return tcktName11 を戻します。
     */
    public String getTcktName11() {
        return tcktName11;
    }
    /**
     * @param tcktName11 設定する tcktName11。
     */
    public void setTcktName11(String tcktName11) {
        this.tcktName11 = tcktName11;
    }
    /**
     * @return tcktName12 を戻します。
     */
    public String getTcktName12() {
        return tcktName12;
    }
    /**
     * @param tcktName12 設定する tcktName12。
     */
    public void setTcktName12(String tcktName12) {
        this.tcktName12 = tcktName12;
    }
    /**
     * @return tcktName13 を戻します。
     */
    public String getTcktName13() {
        return tcktName13;
    }
    /**
     * @param tcktName13 設定する tcktName13。
     */
    public void setTcktName13(String tcktName13) {
        this.tcktName13 = tcktName13;
    }
    /**
     * @return tcktName14 を戻します。
     */
    public String getTcktName14() {
        return tcktName14;
    }
    /**
     * @param tcktName14 設定する tcktName14。
     */
    public void setTcktName14(String tcktName14) {
        this.tcktName14 = tcktName14;
    }
    /**
     * @return tcktName15 を戻します。
     */
    public String getTcktName15() {
        return tcktName15;
    }
    /**
     * @param tcktName15 設定する tcktName15。
     */
    public void setTcktName15(String tcktName15) {
        this.tcktName15 = tcktName15;
    }
    /**
     * @return tcktName2 を戻します。
     */
    public String getTcktName2() {
        return tcktName2;
    }
    /**
     * @param tcktName2 設定する tcktName2。
     */
    public void setTcktName2(String tcktName2) {
        this.tcktName2 = tcktName2;
    }
    /**
     * @return tcktName3 を戻します。
     */
    public String getTcktName3() {
        return tcktName3;
    }
    /**
     * @param tcktName3 設定する tcktName3。
     */
    public void setTcktName3(String tcktName3) {
        this.tcktName3 = tcktName3;
    }
    /**
     * @return tcktName4 を戻します。
     */
    public String getTcktName4() {
        return tcktName4;
    }
    /**
     * @param tcktName4 設定する tcktName4。
     */
    public void setTcktName4(String tcktName4) {
        this.tcktName4 = tcktName4;
    }
    /**
     * @return tcktName5 を戻します。
     */
    public String getTcktName5() {
        return tcktName5;
    }
    /**
     * @param tcktName5 設定する tcktName5。
     */
    public void setTcktName5(String tcktName5) {
        this.tcktName5 = tcktName5;
    }
    /**
     * @return tcktName6 を戻します。
     */
    public String getTcktName6() {
        return tcktName6;
    }
    /**
     * @param tcktName6 設定する tcktName6。
     */
    public void setTcktName6(String tcktName6) {
        this.tcktName6 = tcktName6;
    }
    /**
     * @return tcktName7 を戻します。
     */
    public String getTcktName7() {
        return tcktName7;
    }
    /**
     * @param tcktName7 設定する tcktName7。
     */
    public void setTcktName7(String tcktName7) {
        this.tcktName7 = tcktName7;
    }
    /**
     * @return tcktName8 を戻します。
     */
    public String getTcktName8() {
        return tcktName8;
    }
    /**
     * @param tcktName8 設定する tcktName8。
     */
    public void setTcktName8(String tcktName8) {
        this.tcktName8 = tcktName8;
    }
    /**
     * @return tcktName9 を戻します。
     */
    public String getTcktName9() {
        return tcktName9;
    }
    /**
     * @param tcktName9 設定する tcktName9。
     */
    public void setTcktName9(String tcktName9) {
        this.tcktName9 = tcktName9;
    }
    /**
     * @return updateUser を戻します。
     */
    public String getUpdateUser() {
        return updateUser;
    }
    /**
     * @param updateUser 設定する updateUser。
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * @return uriage を戻します。
     */
    public String getUriage() {
        return uriage;
    }
    /**
     * @param uriage 設定する uriage。
     */
    public void setUriage(String uriage) {
        this.uriage = uriage;
    }
	/**
	 * @return クラス変数onSkbn10Link を戻します。
	 */
	public boolean isOnSkbn10Link() {
		return onSkbn10Link;
	}
	/**
	 * @param onSkbn10Link を クラス変数onSkbn10Linkへ設定します。
	 */
	public void setOnSkbn10Link(boolean onSkbn10Link) {
		this.onSkbn10Link = onSkbn10Link;
	}
	/**
	 * @return クラス変数onSkbn11Link を戻します。
	 */
	public boolean isOnSkbn11Link() {
		return onSkbn11Link;
	}
	/**
	 * @param onSkbn11Link を クラス変数onSkbn11Linkへ設定します。
	 */
	public void setOnSkbn11Link(boolean onSkbn11Link) {
		this.onSkbn11Link = onSkbn11Link;
	}
	/**
	 * @return クラス変数onSkbn2Link を戻します。
	 */
	public boolean isOnSkbn2Link() {
		return onSkbn2Link;
	}
	/**
	 * @param onSkbn2Link を クラス変数onSkbn2Linkへ設定します。
	 */
	public void setOnSkbn2Link(boolean onSkbn2Link) {
		this.onSkbn2Link = onSkbn2Link;
	}
	/**
	 * @return クラス変数onSkbn3Link を戻します。
	 */
	public boolean isOnSkbn3Link() {
		return onSkbn3Link;
	}
	/**
	 * @param onSkbn3Link を クラス変数onSkbn3Linkへ設定します。
	 */
	public void setOnSkbn3Link(boolean onSkbn3Link) {
		this.onSkbn3Link = onSkbn3Link;
	}
	/**
	 * @return クラス変数onSkbn4Link を戻します。
	 */
	public boolean isOnSkbn4Link() {
		return onSkbn4Link;
	}
	/**
	 * @param onSkbn4Link を クラス変数onSkbn4Linkへ設定します。
	 */
	public void setOnSkbn4Link(boolean onSkbn4Link) {
		this.onSkbn4Link = onSkbn4Link;
	}
	/**
	 * @return クラス変数onSkbn5Link を戻します。
	 */
	public boolean isOnSkbn5Link() {
		return onSkbn5Link;
	}
	/**
	 * @param onSkbn5Link を クラス変数onSkbn5Linkへ設定します。
	 */
	public void setOnSkbn5Link(boolean onSkbn5Link) {
		this.onSkbn5Link = onSkbn5Link;
	}
	/**
	 * @return クラス変数onSkbn6Link を戻します。
	 */
	public boolean isOnSkbn6Link() {
		return onSkbn6Link;
	}
	/**
	 * @param onSkbn6Link を クラス変数onSkbn6Linkへ設定します。
	 */
	public void setOnSkbn6Link(boolean onSkbn6Link) {
		this.onSkbn6Link = onSkbn6Link;
	}
	/**
	 * @return クラス変数onSkbn7Link を戻します。
	 */
	public boolean isOnSkbn7Link() {
		return onSkbn7Link;
	}
	/**
	 * @param onSkbn7Link を クラス変数onSkbn7Linkへ設定します。
	 */
	public void setOnSkbn7Link(boolean onSkbn7Link) {
		this.onSkbn7Link = onSkbn7Link;
	}
	/**
	 * @return クラス変数onSkbn8Link を戻します。
	 */
	public boolean isOnSkbn8Link() {
		return onSkbn8Link;
	}
	/**
	 * @param onSkbn8Link を クラス変数onSkbn8Linkへ設定します。
	 */
	public void setOnSkbn8Link(boolean onSkbn8Link) {
		this.onSkbn8Link = onSkbn8Link;
	}
	/**
	 * @return クラス変数onSkbn9Link を戻します。
	 */
	public boolean isOnSkbn9Link() {
		return onSkbn9Link;
	}
	/**
	 * @param onSkbn9Link を クラス変数onSkbn9Linkへ設定します。
	 */
	public void setOnSkbn9Link(boolean onSkbn9Link) {
		this.onSkbn9Link = onSkbn9Link;
	}
	/**
	 * 集計区分2～11のリンク制御フラグ変更処理
	 * @param flg
	 */
	public void changeSkbnAllLinkStatus(boolean flg) {
		setOnSkbn2Link(flg);
		setOnSkbn3Link(flg);
		setOnSkbn4Link(flg);
		setOnSkbn5Link(flg);
		setOnSkbn6Link(flg);
		setOnSkbn7Link(flg);
		setOnSkbn8Link(flg);
		setOnSkbn9Link(flg);
		setOnSkbn10Link(flg);
		setOnSkbn11Link(flg);
	}
}