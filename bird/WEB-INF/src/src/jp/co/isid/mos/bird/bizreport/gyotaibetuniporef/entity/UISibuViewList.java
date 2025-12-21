package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity;
import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;

public class UISibuViewList {
 
    /**
     * 支部コード
     */
    private String sibuCd;
    
    /**
     * 支部名
     */
    private String sibuName;
    
    /**
     * 本部コード
     */
    private String honbuCd;
    
    /**
     * 本部名称
     */
    private String honbuName;
    
    /**
     * 事業本部コード
     */
    private String jigyouCd;
    
    /**
     * 事業本部名称
     */
    private String jigyouName;
    
    /**
     * 営業エリアコード
     */
    private String slareaCd;
    
    /**
     * 営業エリア名称
     */
    private String slareaName;
    
    /**
     * 屋号区分
     */
    private String mosgKbn;
    
    /**
     * 屋号区分(モスファクトリー)
     */
    private String mosfKbn;
    
    /**
     * 店舗数
     */
    private int count;
    
    /**
     * 補正店舗数
     */
    private int hoseiCount;
    
    /**
     * 売上高
     */
    private BigDecimal uriage;
    
    /**
     * 事業計画予算
     */
    private BigDecimal yosan;
    
    /**
     * 客数
     */
    private int kyakusu;
    
    /**
     * 前年売上高
     */
    private BigDecimal zennenUriage;
    
    /**
     * 前年客数
     */
    private int zennenKyakusu;
    
    /**
     * 前年客単価
     */
    private BigDecimal zennenKyakuTanka;
        
    /**
     * 前年営業日数
     */
    private String zennenEigyoDays;
    
    /**
     * 営業日数
     */
    private String eigyoDays;
    
    /**
     * 達成率
     */
    private BigDecimal taseiRitu;
      
    /**
     * 前年比（売上）
     */
    private BigDecimal zennenHiUriage;
    
    /**
     * 前年比（客数）
     */
    private BigDecimal zennenHiKyakusu;   
    
    /**
     * 客単価
     */
    private BigDecimal KyakuTanka;
    
    /**
     * 前年比（客単価）
     */
    private BigDecimal zennenHiKyakuTanka;
    
    /**
     * 前年比対象売上
     */
    private BigDecimal zennenHiTaisyoUriage;
    /**
     * 前年比対象客数
     */
    private BigDecimal zennenHiTaisyoKyakusu;
    /**
     * 前年比対象単価
     */
    private BigDecimal zennenHiTaisyoKyakuTanka;
    
    /**
     * 前年比対象前年売上
     */
    private BigDecimal zennenHiTaisyoMaeUriage;
    /**
     * 前年比対象前年客数
     */
    private BigDecimal zennenHiTaisyoMaeKyakusu;
    /**
     * 前年比対象前年単価
     */
    private BigDecimal zennenHiTaisyoMaeKyakuTanka;
        
    /**
     * バックグラウンドカラー
     */
    private String cssClass;
    
    private String taseiRituClass;
    
    private String kyakuTankaClass;
    
    private String zennenHiKyakusuClass;
    
    private String zennenHiUriageClass;
    
    private String zennenHiKyakuTankaClass;
    
    /**
     * 表示区分
     */
    private String dispCode;
    
    /**
     * 表示フラグ
     */
    private boolean viewFlg;
    
    /**
     * 屋号コード
     */
    private String yagoCd;
    
    /**
     * 詳細屋号コード
     */
    private String yagoDtlCd;
    
    /**
     * 屋号名称
     */
    private String yagoName;
    
    /**
     * 詳細屋号名称
     */
    private String yagoDtlName;
    
    /**
     * 表示タブNo
     */
    private int tabNo;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	/**
	 * 
	 * @return
	 */
	public String getDispTargetCd() {
		if(GyotaibetuNipoConstants.sibu.equals(getDispCode())) {
			return getSibuCd();
		}
		else if(GyotaibetuNipoConstants.area.equals(getDispCode())) {
			return getSlareaCd();
		}
		else if(GyotaibetuNipoConstants.jigyo.equals(getDispCode())) {
			return getJigyouCd();
		}
		else if(GyotaibetuNipoConstants.toukatu.equals(getDispCode())) {
			return getHonbuCd();
		}
		else if(GyotaibetuNipoConstants.total.equals(getDispCode())) {
			return GyotaibetuNipoConstants.ALL;
		}
		return "";
	}
	/**
	 * 
	 * @return
	 */
	public String getDispTargetName() {
		if(GyotaibetuNipoConstants.sibu.equals(getDispCode())) {
			return getSibuName();
		}
		else if(GyotaibetuNipoConstants.area.equals(getDispCode())) {
			return getSlareaName();
		}
		else if(GyotaibetuNipoConstants.jigyo.equals(getDispCode())) {
			return getJigyouName();
		}
		else if(GyotaibetuNipoConstants.toukatu.equals(getDispCode())) {
			return getHonbuName();
		}
		else if(GyotaibetuNipoConstants.total.equals(getDispCode())) {
			return "総合計";
		}
		return "";
	}
	public String getDispCode() {
		return dispCode;
	}

	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}

	public String getHonbuCd() {
		return honbuCd;
	}

	public void setHonbuCd(String honbuCd) {
		this.honbuCd = honbuCd;
	}

	public String getHonbuName() {
		return honbuName;
	}

	public void setHonbuName(String honbuName) {
		this.honbuName = honbuName;
	}

	public String getJigyouCd() {
		return jigyouCd;
	}

	public void setJigyouCd(String jigyouCd) {
		this.jigyouCd = jigyouCd;
	}

	public String getJigyouName() {
		return jigyouName;
	}

	public void setJigyouName(String jigyouName) {
		this.jigyouName = jigyouName;
	}

	public int getKyakusu() {
		return kyakusu;
	}

	public void setKyakusu(int kyakusu) {
		this.kyakusu = kyakusu;
	}

	public BigDecimal getKyakuTanka() {
		return KyakuTanka;
	}

	public void setKyakuTanka(BigDecimal kyakuTanka) {
		KyakuTanka = kyakuTanka;
	}

	public String getKyakuTankaClass() {
		return kyakuTankaClass;
	}

	public void setKyakuTankaClass(String kyakuTankaClass) {
		this.kyakuTankaClass = kyakuTankaClass;
	}

	public String getMosfKbn() {
		return mosfKbn;
	}

	public void setMosfKbn(String mosfKbn) {
		this.mosfKbn = mosfKbn;
	}

	public String getMosgKbn() {
		return mosgKbn;
	}

	public void setMosgKbn(String mosgKbn) {
		this.mosgKbn = mosgKbn;
	}

	public String getSibuCd() {
		return sibuCd;
	}

	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}

	public String getSibuName() {
		return sibuName;
	}

	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}

	public String getSlareaCd() {
		return slareaCd;
	}

	public void setSlareaCd(String slareaCd) {
		this.slareaCd = slareaCd;
	}

	public String getSlareaName() {
		return slareaName;
	}

	public void setSlareaName(String slareaName) {
		this.slareaName = slareaName;
	}

	public int getTabNo() {
		return tabNo;
	}

	public void setTabNo(int tabNo) {
		this.tabNo = tabNo;
	}

	public BigDecimal getTaseiRitu() {
		return taseiRitu;
	}

	public void setTaseiRitu(BigDecimal taseiRitu) {
		this.taseiRitu = taseiRitu;
	}

	public String getTaseiRituClass() {
		return taseiRituClass;
	}

	public void setTaseiRituClass(String taseiRituClass) {
		this.taseiRituClass = taseiRituClass;
	}

	public boolean isViewFlg() {
		return viewFlg;
	}

	public void setViewFlg(boolean viewFlg) {
		this.viewFlg = viewFlg;
	}

	public String getYagoCd() {
		return yagoCd;
	}

	public void setYagoCd(String yagoCd) {
		this.yagoCd = yagoCd;
	}

	public String getYagoDtlCd() {
		return yagoDtlCd;
	}

	public void setYagoDtlCd(String yagoDtlCd) {
		this.yagoDtlCd = yagoDtlCd;
	}

	public String getYagoDtlName() {
		return yagoDtlName;
	}

	public void setYagoDtlName(String yagoDtlName) {
		this.yagoDtlName = yagoDtlName;
	}

	public String getYagoName() {
		return yagoName;
	}

	public void setYagoName(String yagoName) {
		this.yagoName = yagoName;
	}

	public String getZennenEigyoDays() {
		return zennenEigyoDays;
	}

	public void setZennenEigyoDays(String zennenEigyoDays) {
		this.zennenEigyoDays = zennenEigyoDays;
	}

	public BigDecimal getZennenHiKyakusu() {
		return zennenHiKyakusu;
	}

	public void setZennenHiKyakusu(BigDecimal zennenHiKyakusu) {
		this.zennenHiKyakusu = zennenHiKyakusu;
	}

	public String getZennenHiKyakusuClass() {
		return zennenHiKyakusuClass;
	}

	public void setZennenHiKyakusuClass(String zennenHiKyakusuClass) {
		this.zennenHiKyakusuClass = zennenHiKyakusuClass;
	}

	public BigDecimal getZennenHiKyakuTanka() {
		return zennenHiKyakuTanka;
	}

	public void setZennenHiKyakuTanka(BigDecimal zennenHiKyakuTanka) {
		this.zennenHiKyakuTanka = zennenHiKyakuTanka;
	}

	public String getZennenHiKyakuTankaClass() {
		return zennenHiKyakuTankaClass;
	}

	public void setZennenHiKyakuTankaClass(String zennenHiKyakuTankaClass) {
		this.zennenHiKyakuTankaClass = zennenHiKyakuTankaClass;
	}

	public BigDecimal getZennenHiTaisyoKyakusu() {
		return zennenHiTaisyoKyakusu;
	}

	public void setZennenHiTaisyoKyakusu(BigDecimal zennenHiTaisyoKyakusu) {
		this.zennenHiTaisyoKyakusu = zennenHiTaisyoKyakusu;
	}

	public BigDecimal getZennenHiTaisyoKyakuTanka() {
		return zennenHiTaisyoKyakuTanka;
	}

	public void setZennenHiTaisyoKyakuTanka(BigDecimal zennenHiTaisyoKyakuTanka) {
		this.zennenHiTaisyoKyakuTanka = zennenHiTaisyoKyakuTanka;
	}

	public BigDecimal getZennenHiTaisyoUriage() {
		return zennenHiTaisyoUriage;
	}

	public void setZennenHiTaisyoUriage(BigDecimal zennenHiTaisyoUriage) {
		this.zennenHiTaisyoUriage = zennenHiTaisyoUriage;
	}

	public BigDecimal getZennenHiUriage() {
		return zennenHiUriage;
	}

	public void setZennenHiUriage(BigDecimal zennenHiUriage) {
		this.zennenHiUriage = zennenHiUriage;
	}

	public String getZennenHiUriageClass() {
		return zennenHiUriageClass;
	}

	public void setZennenHiUriageClass(String zennenHiUriageClass) {
		this.zennenHiUriageClass = zennenHiUriageClass;
	}

	public int getZennenKyakusu() {
		return zennenKyakusu;
	}

	public void setZennenKyakusu(int zennenKyakusu) {
		this.zennenKyakusu = zennenKyakusu;
	}

	public BigDecimal getZennenKyakuTanka() {
		return zennenKyakuTanka;
	}

	public void setZennenKyakuTanka(BigDecimal zennenKyakuTanka) {
		this.zennenKyakuTanka = zennenKyakuTanka;
	}

	public BigDecimal getZennenHiTaisyoMaeKyakusu() {
		return zennenHiTaisyoMaeKyakusu;
	}

	public void setZennenHiTaisyoMaeKyakusu(BigDecimal zennenHiTaisyoMaeKyakusu) {
		this.zennenHiTaisyoMaeKyakusu = zennenHiTaisyoMaeKyakusu;
	}

	public BigDecimal getZennenHiTaisyoMaeKyakuTanka() {
		return zennenHiTaisyoMaeKyakuTanka;
	}

	public void setZennenHiTaisyoMaeKyakuTanka(
			BigDecimal zennenHiTaisyoMaeKyakuTanka) {
		this.zennenHiTaisyoMaeKyakuTanka = zennenHiTaisyoMaeKyakuTanka;
	}

	public BigDecimal getZennenHiTaisyoMaeUriage() {
		return zennenHiTaisyoMaeUriage;
	}

	public void setZennenHiTaisyoMaeUriage(BigDecimal zennenHiTaisyoMaeUriage) {
		this.zennenHiTaisyoMaeUriage = zennenHiTaisyoMaeUriage;
	}

	public int getHoseiCount() {
		return hoseiCount;
	}

	public void setHoseiCount(int hoseiCount) {
		this.hoseiCount = hoseiCount;
	}

	public String getEigyoDays() {
		return eigyoDays;
	}

	public void setEigyoDays(String eigyoDays) {
		this.eigyoDays = eigyoDays;
	}

	public BigDecimal getUriage() {
		return uriage;
	}

	public void setUriage(BigDecimal uriage) {
		this.uriage = uriage;
	}

	public BigDecimal getYosan() {
		return yosan;
	}

	public void setYosan(BigDecimal yosan) {
		this.yosan = yosan;
	}

	public BigDecimal getZennenUriage() {
		return zennenUriage;
	}

	public void setZennenUriage(BigDecimal zennenUriage) {
		this.zennenUriage = zennenUriage;
	}
    
    
}
