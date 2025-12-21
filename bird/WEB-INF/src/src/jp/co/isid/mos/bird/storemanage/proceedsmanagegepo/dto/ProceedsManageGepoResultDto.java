package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto;

import java.util.List;

/**
 * 売上金管理月報・結果DTOクラス
 *
 * @author xjung
 */
public class ProceedsManageGepoResultDto {
// 検索後表示項目
    /**
     * 対象店舗情報
     */
    private String taishoTenpoInfo;

    /**
     * 売上金管理月報・結果リスト
     */
    private List resutList;

    /**
     * 値引・結果リスト
     */
    private List resultNebikiList;

    /**
     * 集計区分名称２
     */
    private String kaikeiKbnName2;

    /**
     * 集計区分名称３
     */
    private String kaikeiKbnName3;

    /**
     * 集計区分名称４
     */
    private String kaikeiKbnName4;

    /**
     * 集計区分名称５
     */
    private String kaikeiKbnName5;

    /**
     * 集計区分名称６
     */
    private String kaikeiKbnName6;

    /**
     * 集計区分名称７
     */
    private String kaikeiKbnName7;

    /**
     * 集計区分名称８
     */
    private String kaikeiKbnName8;

    /**
     * 集計区分名称９
     */
    private String kaikeiKbnName9;

    /**
     * 集計区分名称１０
     */
    private String kaikeiKbnName10;

    /**
     * 集計区分名称１１
     */
    private String kaikeiKbnName11;

    /**
     * チケット名称１
     */
    private String tcktName1;

    /**
     * チケット名称２
     */
    private String tcktName2;

    /**
     * チケット名称３
     */
    private String tcktName3;

    /**
     * チケット名称４
     */
    private String tcktName4;

    /**
     * チケット名称５
     */
    private String tcktName5;

    /**
     * チケット名称６
     */
    private String tcktName6;

    /**
     * チケット名称７
     */
    private String tcktName7;

    /**
     * チケット名称８
     */
    private String tcktName8;

    /**
     * チケット名称９
     */
    private String tcktName9;

    /**
     * チケット名称１０
     */
    private String tcktName10;

    /**
     * チケット名称１１
     */
    private String tcktName11;

    /**
     * チケット名称１２
     */
    private String tcktName12;

    /**
     * チケット名称１３
     */
    private String tcktName13;

    /**
     * チケット名称１４
     */
    private String tcktName14;

    /**
     * チケット名称１５
     */
    private String tcktName15;

    /**
     * 値引名称１
     */
    private String nebikiName1;

    /**
     * 値引名称２
     */
    private String nebikiName2;
    /**
     * 値引名称３
     */
    private String nebikiName3;
    /**
     * 値引名称４
     */
    private String nebikiName4;
    /**
     * 値引名称５
     */
    private String nebikiName5;
    /**
     * 値引名称６
     */
    private String nebikiName6;
    /**
     * 値引名称７
     */
    private String nebikiName7;
    /**
     * 値引名称８
     */
    private String nebikiName8;
    /**
     * 値引名称９
     */
    private String nebikiName9;

    /**
     * 明細リンク表示フラグ
     */
    private String meisaiDispFlg;

//add 2019/07/15 USI張  #34 begin
    /**
     * 売上と消費税明細リンク表示フラグ
     */
    private String uriageMeisaiDispFlg;
//add 2019/07/15 USI張  #34 end

//add 2020/05/07 USI張 begin
    /**
     * dポイント、株主優待モスポイント情報・結果リスト
     */
    private List resutPointList;
//add 2020/05/07 USI張 end

//  検索後表示項目getter/setter
    /**
     * 対象店舗情報を取得する
     * @return String 対象店舗情報
     */
    public String getTaishoTenpoInfo() {
        return taishoTenpoInfo;
    }

    /**
     * 対象店舗情報を設定する
     * @param taishoTenpoInfo 対象店舗情報
     */
    public void setTaishoTenpoInfo(String taishoTenpoInfo) {
        this.taishoTenpoInfo = taishoTenpoInfo;
    }

    /**
     * 売上金管理月報・結果リストを取得する
     * @return List 売上金管理月報・結果リスト
     */
    public List getResultList() {
        return resutList;
    }

    /**
     * 売上金管理月報・結果リストを設定する
     * @param resutList 売上金管理月報・結果リスト
     */
    public void setResultList(List resutList) {
        this.resutList = resutList;
    }

    /**
     * を戻します。
     * @return kaikeiKbnName2
     */
    public String getKaikeiKbnName2() {
        return kaikeiKbnName2;
    }

    /**
     * 集計区分名称２を設定します。
     * @param kaikeiKbnName2 集計区分名称２
     */
    public void setKaikeiKbnName2(String kaikeiKbnName2) {
        this.kaikeiKbnName2 = kaikeiKbnName2;
    }

    /**
     * 集計区分名称３を戻します。
     * @return kaikeiKbnName3 集計区分名称３
     */
    public String getKaikeiKbnName3() {
        return kaikeiKbnName3;
    }

    /**
     * を設定します。
     * @param kaikeiKbnName3
     */
    public void setKaikeiKbnName3(String kaikeiKbnName3) {
        this.kaikeiKbnName3 = kaikeiKbnName3;
    }

    /**
     * 集計区分名称４を取得する
     * @return String 集計区分名称４
     */
    public String getKaikeiKbnName4() {
        return kaikeiKbnName4;
    }

    /**
     * 集計区分名称４を設定する
     * @param kaikeiKbnName4 集計区分名称４
     */
    public void setKaikeiKbnName4(String kaikeiKbnName4) {
        this.kaikeiKbnName4 = kaikeiKbnName4;
    }

    /**
     * 集計区分名称５を取得する
     * @return String 集計区分名称５
     */
    public String getKaikeiKbnName5() {
        return kaikeiKbnName5;
    }

    /**
     * 集計区分名称５を設定する
     * @param kaikeiKbnName5 集計区分名称５
     */
    public void setKaikeiKbnName5(String kaikeiKbnName5) {
        this.kaikeiKbnName5 = kaikeiKbnName5;
    }

    /**
     * 集計区分名称６を取得する
     * @return String 集計区分名称６
     */
    public String getKaikeiKbnName6() {
        return kaikeiKbnName6;
    }

    /**
     * 集計区分名称６を設定する
     * @param kaikeiKbnName6 集計区分名称６
     */
    public void setKaikeiKbnName6(String kaikeiKbnName6) {
        this.kaikeiKbnName6 = kaikeiKbnName6;
    }

    /**
     * 集計区分名称７を取得する
     * @return String 集計区分名称７
     */
    public String getKaikeiKbnName7() {
        return kaikeiKbnName7;
    }

    /**
     * 集計区分名称７を設定する
     * @param kaikeiKbnName7 集計区分名称７
     */
    public void setKaikeiKbnName7(String kaikeiKbnName7) {
        this.kaikeiKbnName7 = kaikeiKbnName7;
    }

    /**
     * 集計区分名称８を取得する
     * @return String 集計区分名称８
     */
    public String getKaikeiKbnName8() {
        return kaikeiKbnName8;
    }

    /**
     * 集計区分名称８を設定する
     * @param kaikeiKbnName8 集計区分名称８
     */
    public void setKaikeiKbnName8(String kaikeiKbnName8) {
        this.kaikeiKbnName8 = kaikeiKbnName8;
    }

    /**
     * 集計区分名称９を取得する
     * @return String 集計区分名称９
     */
    public String getKaikeiKbnName9() {
        return kaikeiKbnName9;
    }

    /**
     * 集計区分名称９を設定する
     * @param kaikeiKbnName9 集計区分名称９
     */
    public void setKaikeiKbnName9(String kaikeiKbnName9) {
        this.kaikeiKbnName9 = kaikeiKbnName9;
    }

    /**
     * 集計区分名称１０を取得する
     * @return String 集計区分名称１０
     */
    public String getKaikeiKbnName10() {
        return kaikeiKbnName10;
    }

    /**
     * 集計区分名称１０を設定する
     * @param kaikeiKbnName10 集計区分名称１０
     */
    public void setKaikeiKbnName10(String kaikeiKbnName10) {
        this.kaikeiKbnName10 = kaikeiKbnName10;
    }

    /**
     * 集計区分名称１１を取得する
     * @return String 集計区分名称１１
     */
    public String getKaikeiKbnName11() {
        return kaikeiKbnName11;
    }

    /**
     * 集計区分名称１１を設定する
     * @param kaikeiKbnName11 集計区分名称１１
     */
    public void setKaikeiKbnName11(String kaikeiKbnName11) {
        this.kaikeiKbnName11 = kaikeiKbnName11;
    }

    /**
     * チケット名称１を取得する
     * @return String チケット名称１
     */
    public String getTcktName1() {
        return tcktName1;
    }

    /**
     * チケット名称１を設定する
     * @param tcktName1 チケット名称１
     */
    public void setTcktName1(String tcktName1) {
        this.tcktName1 = tcktName1;
    }

    /**
     * チケット名称２を取得する
     * @return String チケット名称２
     */
    public String getTcktName2() {
        return tcktName2;
    }

    /**
     * チケット名称２を設定する
     * @param tcktName2 チケット名称２
     */
    public void setTcktName2(String tcktName2) {
        this.tcktName2 = tcktName2;
    }

    /**
     * チケット名称３を取得する
     * @return String チケット名称３
     */
    public String getTcktName3() {
        return tcktName3;
    }

    /**
     * チケット名称３を設定する
     * @param tcktName3 チケット名称３
     */
    public void setTcktName3(String tcktName3) {
        this.tcktName3 = tcktName3;
    }

    /**
     * チケット名称４を取得する
     * @return String チケット名称４
     */
    public String getTcktName4() {
        return tcktName4;
    }

    /**
     * チケット名称４を設定する
     * @param tcktName4 チケット名称４
     */
    public void setTcktName4(String tcktName4) {
        this.tcktName4 = tcktName4;
    }

    /**
     * チケット名称５を取得する
     * @return String チケット名称５
     */
    public String getTcktName5() {
        return tcktName5;
    }

    /**
     * チケット名称５を設定する
     * @param tcktName5 チケット名称５
     */
    public void setTcktName5(String tcktName5) {
        this.tcktName5 = tcktName5;
    }

    /**
     * チケット名称６を取得する
     * @return String チケット名称６
     */
    public String getTcktName6() {
        return tcktName6;
    }

    /**
     * チケット名称６を設定する
     * @param tcktName6 チケット名称６
     */
    public void setTcktName6(String tcktName6) {
        this.tcktName6 = tcktName6;
    }

    /**
     * チケット名称７を取得する
     * @return String チケット名称７
     */
    public String getTcktName7() {
        return tcktName7;
    }

    /**
     * チケット名称７を設定する
     * @param tcktName7 チケット名称７
     */
    public void setTcktName7(String tcktName7) {
        this.tcktName7 = tcktName7;
    }

    /**
     * チケット名称８を取得する
     * @return String チケット名称８
     */
    public String getTcktName8() {
        return tcktName8;
    }

    /**
     * チケット名称８を設定する
     * @param tcktName8 チケット名称８
     */
    public void setTcktName8(String tcktName8) {
        this.tcktName8 = tcktName8;
    }

    /**
     * チケット名称９を取得する
     * @return String チケット名称９
     */
    public String getTcktName9() {
        return tcktName9;
    }

    /**
     * チケット名称９を設定する
     * @param tcktName9 チケット名称９
     */
    public void setTcktName9(String tcktName9) {
        this.tcktName9 = tcktName9;
    }

    /**
     * チケット名称１０を取得する
     * @return String チケット名称１０
     */
    public String getTcktName10() {
        return tcktName10;
    }

    /**
     * チケット名称１０を設定する
     * @param tcktName10 チケット名称１０
     */
    public void setTcktName10(String tcktName10) {
        this.tcktName10 = tcktName10;
    }

    /**
     * チケット名称１１を取得する
     * @return String チケット名称１１
     */
    public String getTcktName11() {
        return tcktName11;
    }

    /**
     * チケット名称１１を設定する
     * @param tcktName11 チケット名称１１
     */
    public void setTcktName11(String tcktName11) {
        this.tcktName11 = tcktName11;
    }

    /**
     * チケット名称１２を取得する
     * @return String チケット名称１２
     */
    public String getTcktName12() {
        return tcktName12;
    }

    /**
     * チケット名称１２を設定する
     * @param tcktName12 チケット名称１２
     */
    public void setTcktName12(String tcktName12) {
        this.tcktName12 = tcktName12;
    }

    /**
     * チケット名称１３を取得する
     * @return String チケット名称１３
     */
    public String getTcktName13() {
        return tcktName13;
    }

    /**
     * チケット名称１３を設定する
     * @param tcktName13 チケット名称１３
     */
    public void setTcktName13(String tcktName13) {
        this.tcktName13 = tcktName13;
    }

    /**
     * チケット名称１４を取得する
     * @return String チケット名称１４
     */
    public String getTcktName14() {
        return tcktName14;
    }

    /**
     * チケット名称１４を設定する
     * @param tcktName14 チケット名称１４
     */
    public void setTcktName14(String tcktName14) {
        this.tcktName14 = tcktName14;
    }

    /**
     * チケット名称１５を取得する
     * @return String チケット名称１５
     */
    public String getTcktName15() {
        return tcktName15;
    }

    /**
     * チケット名称１５を設定する
     * @param tcktName15 チケット名称１５
     */
    public void setTcktName15(String tcktName15) {
        this.tcktName15 = tcktName15;
    }


    /**
     * 値引名称１を取得する
     * @return nebikiName1 値引名称１
     */
	public String getNebikiName1() {
		return nebikiName1;
	}

	/**
     * 値引名称１を設定する
     * @param nebikiName1 値引名称１
     */
	public void setNebikiName1(String nebikiName1) {
		this.nebikiName1 = nebikiName1;
	}

	 /**
     * 値引名称２を取得する
     * @return nebikiName2 値引名称２
     */
	public String getNebikiName2() {
		return nebikiName2;
	}

	/**
     * 値引名称２を取得する
     * @param nebikiName2 値引名称２
     */
	public void setNebikiName2(String nebikiName2) {
		this.nebikiName2 = nebikiName2;
	}

	 /**
     * 値引名称３を取得する
     * @return nebikiName3 値引名称３
     */
	public String getNebikiName3() {
		return nebikiName3;
	}

	/**
     * 値引名称３を設定する
     * @param nebikiName3 値引名称３
     */
	public void setNebikiName3(String nebikiName3) {
		this.nebikiName3 = nebikiName3;
	}

	 /**
     * 値引名称４を取得する
     * @return nebikiName4 値引名称４
     */
	public String getNebikiName4() {
		return nebikiName4;
	}

	/**
     * 値引名称４を設定する
     * @param nebikiName4 値引名称４
     */
	public void setNebikiName4(String nebikiName4) {
		this.nebikiName4 = nebikiName4;
	}

	 /**
     * 値引名称５を取得する
     * @return nebikiName5 値引名称５
     */
	public String getNebikiName5() {
		return nebikiName5;
	}

	/**
     * 値引名称５を設定する
     * @param nebikiName5 値引名称５
     */
	public void setNebikiName5(String nebikiName5) {
		this.nebikiName5 = nebikiName5;
	}

	 /**
     * 値引名称６を取得する
     * @return nebikiName6 値引名称６
     */
	public String getNebikiName6() {
		return nebikiName6;
	}

	/**
     * 値引名称６を設定する
     * @param nebikiName6 値引名称６
     */
	public void setNebikiName6(String nebikiName6) {
		this.nebikiName6 = nebikiName6;
	}

	 /**
     * 値引名称７を取得する
     * @return nebikiName7 値引名称７
     */
	public String getNebikiName7() {
		return nebikiName7;
	}

	/**
     * 値引名称７を設定する
     * @param nebikiName7 値引名称７
     */
	public void setNebikiName7(String nebikiName7) {
		this.nebikiName7 = nebikiName7;
	}

	 /**
     * 値引名称８を取得する
     * @return nebikiName8 値引名称８
     */
	public String getNebikiName8() {
		return nebikiName8;
	}

	/**
     * 値引名称８を設定する
     * @param nebikiName8 値引名称８
     */
	public void setNebikiName8(String nebikiName8) {
		this.nebikiName8 = nebikiName8;
	}

	 /**
     * 値引名称９を取得する
     * @return nebikiName9 値引名称９
     */
	public String getNebikiName9() {
		return nebikiName9;
	}

	/**
     * 値引名称９を設定する
     * @param nebikiName9 値引名称９
     */
	public void setNebikiName9(String nebikiName9) {
		this.nebikiName9 = nebikiName9;
	}

	/**
	 * 値引・結果リストを取得する
	 * @return resultNebikiList 値引・結果リスト
	 */
	public List getResultNebikiList() {
		return resultNebikiList;
	}

	/**
	 * 値引・結果リストを設定する
	 * @param resultNebikiList 値引・結果リスト
	 */
	public void setResultNebikiList(List resultNebikiList) {
		this.resultNebikiList = resultNebikiList;
	}

    /**
     * 明細リンク表示フラグを戻します。
     * @return meisaiDispFlg 明細リンク表示フラグ
     */
    public String getMeisaiDispFlg() {
        return meisaiDispFlg;
    }

    /**
     * 明細リンク表示フラグを設定します。
     * @param meisaiDispFlg 明細リンク表示フラグ
     */
    public void setMeisaiDispFlg(String meisaiDispFlg) {
        this.meisaiDispFlg = meisaiDispFlg;
    }

//add 2019/07/15 USI張  #34 begin
    /**
     * 売上と消費税明細リンク表示フラグを戻します。
     * @return uriageMeisaiDispFlg 売上と消費税明細リンク表示フラグ
     */
	public String getUriageMeisaiDispFlg() {
		return uriageMeisaiDispFlg;
	}

    /**
     * 売上と消費税明細リンク表示フラグを設定します。
     * @param uriageMeisaiDispFlg 売上と消費税明細リンク表示フラグ
     */
	public void setUriageMeisaiDispFlg(String uriageMeisaiDispFlg) {
		this.uriageMeisaiDispFlg = uriageMeisaiDispFlg;
	}
//add 2019/07/15 USI張  #34 end

//add 2020/05/07 USI張 begin
	/**
	 * dポイント、株主優待モスポイント・結果リストを戻する
	 * @return resutPointList dポイント、株主優待モスポイント・結果リスト
	 */
	public List getResutPointList() {
		return resutPointList;
	}

	/**
	 * dポイント、株主優待モスポイント・結果リストを設定する
	 * @param resutPointList dポイント、株主優待モスポイント・結果リスト
	 */
	public void setResutPointList(List resutPointList) {
		this.resutPointList = resutPointList;
	}
//add 2020/05/07 USI張 end
}