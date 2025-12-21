package jp.co.isid.mos.bird.bizreport.netorderniporef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 
 *
 */
public class TrnSibuUriageNipoRelate {
    
    /**
     * 管理会社企業コード
     */
    private String companyCd;
    
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名称クラス
     */
    private String sibuNameClass;
    
    /**
     * 支部名称
     */
    private String sibuName;
    
    /**
     * 当年店舗数
     */
    private BigDecimal tenpoCount;
    
    /**
     * 前年店舗数
     */
    private BigDecimal zenTenpoCount;
    
    /**
     * 当年店舗数/前年店舗数
     */
    private String tenpoCountSlash;
    
    /**
     * 本部コード
     */
    private String honbuCd;
    
    /**
     * 本部名称
     */
    private String honbuName;
    
    /**
     * 事業部コード
     */
    private String jigyoCd;
    
    /**
     * 事業部名称
     */
    private String jigyoName;
    
    /**
     * 営業エリアコード
     */
    private String slareaCd;
    
    /**
     * 営業エリア名称
     */
    private String slareaName;
    
    /**
     * 売上クラス
     */
    private String uriageClass;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 予算クラス
     */
    private String yosanClass;
    
    /**
     * 予算
     */
    private BigDecimal yosan;
    
    /**
     * 達成率クラス
     */
    private String tasseiYosanClass;
    
    /**
     * 達成率(予算差)
     */
    private BigDecimal tasseiYosan;
    
    /**
     * 前年実績クラス
     */
    private String zenUriageClass;
    
    /**
     * 前年実績
     */
    private BigDecimal zenUriage;
    
    /**
     * 前年比クラス
     */
    private String zenHiSaClass;
    
    /**
     * 前年比(前年差)
     */
    private BigDecimal zenHiSa;
    
    /**
     * オーナー予算クラス
     */
    private String onerYosanClass;
    
    /**
     * オーナー予算
     */
    private BigDecimal onerYosan;
    
    /**
     * 表示区分
     */
    private String dispKbn;
    
    /**
     * trのクラス
     */
    private String rClass;
    
    /**
     * 営業日補正用店舗数
     */
    private BigDecimal hoseiTenpoCnt;
    
    /**
     * 営業日補正用前年店舗数
     */
    private BigDecimal hoseiZenTenpoCnt;
    
    /**
     * ネット注文実績店舗数
     */
    private BigDecimal miseKbnNsum;
    
    /**
     * ネット注文売上
     */
    private BigDecimal uriageNsum;
    
    /**
     * 前年ネット注文実績
     */
    private BigDecimal uriageNsumZen;
    
    /**
     * 前年比(ネット注文売上)
     */
    private BigDecimal uriageNsumZenHi;
    
    /**
     * 構成比(売上)
     */
    private BigDecimal uriageNsumKoseiHi;
    
    /**
     * ネットテイク実績店舗数
     */
    private BigDecimal miseKbnNtake;
    
    /**
     * ネットテイク売上
     */
    private BigDecimal uriageNtake;
    
    /**
     * 前年ネットテイク実績
     */
    private BigDecimal uriageNtakeZen;
    
    /**
     * 前年比(ネットテイク売上)
     */
    private BigDecimal uriageNtakeZenHi;
    
    /**
     * 構成比(売上)
     */
    private BigDecimal uriageNtakeKoseiHi;
    
    /**
     * ネット宅配実績店舗数
     */
    private BigDecimal miseKbnNtaku;
    
    /**
     * ネット宅配売上
     */
    private BigDecimal uriageNtaku;
    
    /**
     * 前年ネット宅配実績
     */
    private BigDecimal uriageNtakuZen;
    
    /**
     * 前年比(ネット宅配売上)
     */
    private BigDecimal uriageNtakuZenHi;
    
    /**
     * 構成比(売上)
     */
    private BigDecimal uriageNtakuKoseiHi;
    
    /**
     * 予算対象店舗数
     * 作成日：2007/06/05 ADD xkinu クローズ店予算表示対応
     */
    private BigDecimal yosanMiseCnt = new BigDecimal("0");
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
     * 支部名称クラスを取得します。
     * @return 支部名称クラス
     */
    public String getSibuNameClass() {
        return sibuNameClass;
    }
    /**
     * 支部名称クラスを設定します。
     * @param sibuNameClass 支部名称クラス
     */
    public void setSibuNameClass(String sibuNameClass) {
        this.sibuNameClass = sibuNameClass;
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
     * 当年店舗数を取得します。
     * @return 当年店舗数
     */
    public BigDecimal getTenpoCount() {
        return tenpoCount;
    }
    /**
     * 当年店舗数を設定します。
     * @param tenpoCount 当年店舗数
     */
    public void setTenpoCount(BigDecimal tenpoCount) {
        this.tenpoCount = tenpoCount;
    }
    
    /**
     * 前年店舗数を取得します。
     * @return 前年店舗数
     */
    public BigDecimal getZenTenpoCount() {
        return zenTenpoCount;
    }
    /**
     * 前年店舗数を設定します。
     * @param zenTenpoCount 前年店舗数
     */
    public void setZenTenpoCount(BigDecimal zenTenpoCount) {
        this.zenTenpoCount = zenTenpoCount;
    }
    
    /**
     * 当年店舗数/前年店舗数を取得します。
     * @return 当年店舗数/前年店舗数
     */
    public String getTenpoCountSlash() {
        NumericFormatter num = new NumericFormatter();
        String returnNum = "(" + num.format( this.tenpoCount,true) + "/" +  num.format( this.zenTenpoCount,true) + ")";
        return returnNum;
    }
    /**
     * 当年店舗数/前年店舗数を設定します。
     * @param tenpoCountSlash 当年店舗数/前年店舗数
     */
    public void setTenpoCountSlash(String tenpoCountSlash) {
        this.tenpoCountSlash = tenpoCountSlash;
    }
    
    /**
     * 本部コードを取得します。
     * @return 本部コード
     */
    public String getHonbuCd() {
        return honbuCd;
    }
    /**
     * 本部コードを設定します。
     * @param honbuCd 本部コード
     */
    public void setHonbuCd(String honbuCd) {
        this.honbuCd = honbuCd;
    }
    
    /**
     * 本部名称を取得します。
     * @return 本部名称
     */
    public String getHonbuName() {
        return honbuName;
    }
    /**
     * 本部名称を設定します。
     * @param honbuName 本部名称
     */
    public void setHonbuName(String honbuName) {
        this.honbuName = honbuName;
    }
    
    /**
     * 事業部コードを取得します。
     * @return 事業部コード
     */
    public String getJigyoCd() {
        return jigyoCd;
    }
    /**
     * 事業部コードを設定します。
     * @param jigyoCd 事業部コード
     */
    public void setJigyoCd(String jigyoCd) {
        this.jigyoCd = jigyoCd;
    }
    
    /**
     * 事業部名称を取得します。
     * @return 事業部名称
     */
    public String getJigyoName() {
        return jigyoName;
    }
    /**
     * 事業部名称を設定します。
     * @param jigyoName 事業部名称
     */
    public void setJigyoName(String jigyoName) {
        this.jigyoName = jigyoName;
    }
    
    /**
     * 営業エリアコードを取得します。
     * @return 営業エリアコード
     */
    public String getSlareaCd() {
        return slareaCd;
    }
    /**
     * 営業エリアコードを設定します。
     * @param slareaCd 営業エリアコード
     */
    public void setSlareaCd(String slareaCd) {
        this.slareaCd = slareaCd;
    }
    
    /**
     * 営業エリア名称を取得します。
     * @return 営業エリア名称
     */
    public String getSlareaName() {
        return slareaName;
    }
    /**
     * 営業エリア名称を設定します。
     * @param slareaName 営業エリア名称
     */
    public void setSlareaName(String slareaName) {
        this.slareaName = slareaName;
    }
    
    /**
     * 売上クラスを取得します。
     * @return 売上クラス
     */
    public String getUriageClass() {
        return uriageClass;
    }
    /**
     * 売上クラスを設定します。
     * @param uriageClass 売上クラス
     */
    public void setUriageClass(String uriageClass) {
        this.uriageClass = uriageClass;
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
     * 予算クラスを取得します。
     * @return 予算クラス
     */
    public String getYosanClass() {
        return yosanClass;
    }
    /**
     * 予算クラスを設定します。
     * @param yosanClass 予算クラス
     */
    public void setYosanClass(String yosanClass) {
        this.yosanClass = yosanClass;
    }
    
    /**
     * 予算を取得します。
     * @return 予算
     */
    public BigDecimal getYosan() {
        return yosan;
    }
    /**
     * 予算を設定します。
     * @param yosan 予算
     */
    public void setYosan(BigDecimal yosan) {
        this.yosan = yosan;
    }
    
    /**
     * 達成率クラスを取得します。
     * @return 達成率クラス
     */
    public String getTasseiYosanClass() {
        return tasseiYosanClass;
    }
    /**
     * 達成率クラスを設定します。
     * @param tasseiYosanClass 達成率クラス
     */
    public void setTasseiYosanClass(String tasseiYosanClass) {
        this.tasseiYosanClass = tasseiYosanClass;
    }
    
    /**
     * 達成率(予算差)を取得します。
     * @return 達成率(予算差)
     */
    public BigDecimal getTasseiYosan() {
        return tasseiYosan;
    }
    /**
     * 達成率(予算差)を設定します。
     * @param tasseiYosan 達成率(予算差)
     */
    public void setTasseiYosan(BigDecimal tasseiYosan) {
        this.tasseiYosan = tasseiYosan;
    }
    
    /**
     * 前年実績クラスを取得します。
     * @return 前年実績クラス
     */
    public String getZenUriageClass() {
        return zenUriageClass;
    }
    /**
     * 前年実績クラスを設定します。
     * @param zenUriageClass 前年実績クラス
     */
    public void setZenUriageClass(String zenUriageClass) {
        this.zenUriageClass = zenUriageClass;
    }
    
    /**
     * 前年実績を取得します。
     * @return 前年実績
     */
    public BigDecimal getZenUriage() {
        return zenUriage;
    }
    /**
     * 前年実績を設定します。
     * @param zenUriage 前年実績
     */
    public void setZenUriage(BigDecimal zenUriage) {
        this.zenUriage = zenUriage;
    }
    
    /**
     * 前年比クラスを取得します。
     * @return 前年比クラス
     */
    public String getZenHiSaClass() {
        return zenHiSaClass;
    }
    /**
     * 前年比クラスを設定します。
     * @param zenHiSaClass 前年比クラス
     */
    public void setZenHiSaClass(String zenHiSaClass) {
        this.zenHiSaClass = zenHiSaClass;
    }
    
    /**
     * 前年比(前年差)を取得します。
     * @return 前年比(前年差)
     */
    public BigDecimal getZenHiSa() {
        return zenHiSa;
    }
    /**
     * 前年比(前年差)を設定します。
     * @param zenHiSa 前年比(前年差)
     */
    public void setZenHiSa(BigDecimal zenHiSa) {
        this.zenHiSa = zenHiSa;
    }
    
    /**
     * オーナー予算クラスを取得します。
     * @return オーナー予算クラス
     */
    public String getOnerYosanClass() {
        return onerYosanClass;
    }
    /**
     * オーナー予算クラスを設定します。
     * @param onerYosanClass オーナー予算クラス
     */
    public void setOnerYosanClass(String onerYosanClass) {
        this.onerYosanClass = onerYosanClass;
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
     * 表示区分を取得します。
     * @return 表示区分
     */
    public String getDispKbn() {
        return dispKbn;
    }
    /**
     * 表示区分を設定します。
     * @param dispKbn 表示区分
     */
    public void setDispKbn(String dispKbn) {
        this.dispKbn = dispKbn;
    }
    
    /**
     * trのクラスを取得します。
     * @return trのクラス
     */
    public String getRClass() {
        return rClass;
    }
    /**
     * trのクラスを設定します。
     * @param rClass trのクラス
     */
    public void setRClass(String rClass) {
        this.rClass = rClass;
    }
    
    /**
     * 補正店舗カウントを取得します。
     * @return
     */
    public BigDecimal getHoseiTenpoCnt() {
        return this.hoseiTenpoCnt;
    }
    
    /**
     * 補正店舗カウントを設定します。
     * @param hoseiTenpoCnt
     */
    public void setHoseiTenpoCnt( BigDecimal hoseiTenpoCnt ) {
        this.hoseiTenpoCnt = hoseiTenpoCnt;
    }
    
    /**
     * 補正前年店舗カウントを取得します。
     * @return
     */
    public BigDecimal getHoseiZenTenpoCnt() {
        return this.hoseiZenTenpoCnt;
    }
    
    /**
     * 補正前年店舗カウントを設定します。
     * @param hoseiZenTenpoCnt
     */
    public void setHoseiZenTenpoCnt( BigDecimal hoseiZenTenpoCnt) {
        this.hoseiZenTenpoCnt = hoseiZenTenpoCnt;
    }
	/**
	 * 予算対象店舗数取得処理
	 * @return yosanMiseCnt を戻します。
	 */
	public BigDecimal getYosanMiseCnt() {
		return yosanMiseCnt;
	}
	/**
	 * 予算対象店舗数設定処理
	 * @param yosanMiseCnt 設定する yosanMiseCnt。
	 */
	public void setYosanMiseCnt(BigDecimal yosanMiseCnt) {
		this.yosanMiseCnt = yosanMiseCnt;
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
	 * @return uriageNsumKoseiHi を戻します。
	 */
	public BigDecimal getUriageNsumKoseiHi() {
		return uriageNsumKoseiHi;
	}
	/**
	 * @param uriageNsumKoseiHi 設定する uriageNsumKoseiHi。
	 */
	public void setUriageNsumKoseiHi(BigDecimal uriageNsumKoseiHi) {
		this.uriageNsumKoseiHi = uriageNsumKoseiHi;
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
	 * @return uriageNsumZenHi を戻します。
	 */
	public BigDecimal getUriageNsumZenHi() {
		return uriageNsumZenHi;
	}
	/**
	 * @param uriageNsumZenHi 設定する uriageNsumZenHi。
	 */
	public void setUriageNsumZenHi(BigDecimal uriageNsumZenHi) {
		this.uriageNsumZenHi = uriageNsumZenHi;
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
	 * @return uriageNtakeKoseiHi を戻します。
	 */
	public BigDecimal getUriageNtakeKoseiHi() {
		return uriageNtakeKoseiHi;
	}
	/**
	 * @param uriageNtakeKoseiHi 設定する uriageNtakeKoseiHi。
	 */
	public void setUriageNtakeKoseiHi(BigDecimal uriageNtakeKoseiHi) {
		this.uriageNtakeKoseiHi = uriageNtakeKoseiHi;
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
	 * @return uriageNtakeZenHi を戻します。
	 */
	public BigDecimal getUriageNtakeZenHi() {
		return uriageNtakeZenHi;
	}
	/**
	 * @param uriageNtakeZenHi 設定する uriageNtakeZenHi。
	 */
	public void setUriageNtakeZenHi(BigDecimal uriageNtakeZenHi) {
		this.uriageNtakeZenHi = uriageNtakeZenHi;
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
	 * @return uriageNtakuKoseiHi を戻します。
	 */
	public BigDecimal getUriageNtakuKoseiHi() {
		return uriageNtakuKoseiHi;
	}
	/**
	 * @param uriageNtakuKoseiHi 設定する uriageNtakuKoseiHi。
	 */
	public void setUriageNtakuKoseiHi(BigDecimal uriageNtakuKoseiHi) {
		this.uriageNtakuKoseiHi = uriageNtakuKoseiHi;
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
	/**
	 * @return uriageNtakuZenHi を戻します。
	 */
	public BigDecimal getUriageNtakuZenHi() {
		return uriageNtakuZenHi;
	}
	/**
	 * @param uriageNtakuZenHi 設定する uriageNtakuZenHi。
	 */
	public void setUriageNtakuZenHi(BigDecimal uriageNtakuZenHi) {
		this.uriageNtakuZenHi = uriageNtakuZenHi;
	}
}
