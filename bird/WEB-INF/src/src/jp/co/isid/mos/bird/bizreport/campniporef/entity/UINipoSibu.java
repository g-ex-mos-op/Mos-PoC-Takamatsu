/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 支部一覧用キャンペーン日報エンティティ
 * 
 * @author xkinu
 *
 */
public class UINipoSibu implements UINipo {
    
    public static final String rowType_COLUMN = "ROW_TYPE";

    public static final String taishoTenpoCnt_COLUMN = "TAISHO_TENPO_CNT";
	
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String kazuKei_COLUMN = "KAZU_KEI";
    
    public static final String menuUriage_COLUMN = "MENU_URIAGE";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String uriageZen_COLUMN = "URIAGE_ZEN";

    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String kyakusuZen_COLUMN = "KYAKUSU_ZEN";

    public static final String netUriage_COLUMN = "NET_URIAGE";
    
    public static final String netUriageZen_COLUMN = "NET_URIAGE_ZEN";
    
    public static final String netKyakusu_COLUMN = "NET_KYAKUSU";
    
    public static final String netKyakusuZen_COLUMN = "NET_KYAKUSU_ZEN";
        
    public static final String groupRowNo_COLUMN = "GROUP_ROW_NO";
    public static final String menuCd_COLUMN = "MENU_CD";
    public static final String menuNameKj_COLUMN = "MENU_NAME_KJ";
    public static final String tenpoCnt_COLUMN = "TENPO_CNT";

    /**
     * 対象店舗数
     */
    private BigDecimal tenpoCnt;
    /**
     * 対象店舗数
     */
    private BigDecimal taishoTenpoCnt;
    /**
     * 企業コード
     */
    private String companyCd;
    /**
     * 行の種類
	 * HONBU_CD, JOGYOU_CD, SLAREA_CD, SIBU_CD, AREA_DAI_CD, BLOCK_CD, MISE_CD
     */
    private String rowType;
    /**
     * グループ内行番号
	 * 
     */
    private BigDecimal groupRowNo;
    
    /**
     * メニューor集約メニューコード
     */
    private String menuCd;
    /**
     * メニューor集約メニュー名称
     */
    private String menuNameKj;

    /**
     * 販売個数
     */
    private BigDecimal kazuKei = new BigDecimal("0");
        
    /**
     * 販売金額
     */
    private BigDecimal menuUriage = new BigDecimal("0");
    /**
     * 売上高
     */
    private BigDecimal uriage = new BigDecimal("0");    
        
    /**
     * 前年売上高
     */
    private BigDecimal uriageZen = new BigDecimal("0");
    
    /**
     * 客数
     */
    private BigDecimal kyakusu = new BigDecimal("0");
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen = new BigDecimal("0");
    
    /**
     * NET売上高
     */
    private BigDecimal netUriage = new BigDecimal("0");
    
    /**
     * NET前年売上高
     */
    private BigDecimal netUriageZen = new BigDecimal("0");
    
    /**
     * NET客数
     */
    private BigDecimal netKyakusu = new BigDecimal("0");
    /**
     * NET前年客数
     */
    private BigDecimal netKyakusuZen = new BigDecimal("0");
    
   
    /**
     * TRタグスタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getTrCssClass() {
    	return CommonUtil.getTrCssClass(getRowType());
    }
	/**
	 * @return taishoTenpoCnt を戻します。
	 */
	public BigDecimal getTaishoTenpoCnt() {
		return taishoTenpoCnt;
	}

	/**
	 * @param taishoTenpoCnt 設定する taishoTenpoCnt。
	 */
	public void setTaishoTenpoCnt(BigDecimal taishoTenpoCnt) {
		this.taishoTenpoCnt = taishoTenpoCnt;
	}
	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * @param companyCd 設定する companyCd。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * 行種類取得処理
	 * 
	 * 種類：HONBU_CD, JIGYOU_CD, SLAREA_CD, SIBU_CD, BLOCK_CD, MISE_CD
	 * @return rowType を戻します。
	 */
	public String getRowType() {
		return rowType;
	}

	/**
	 * 行種類設定処理
	 * @param rowType 設定する rowType。
	 */
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}
	/**
	 * メニューコードor集約メニューコード取得処理
	 * 
	 * @return menuCd メニューコードor集約メニューコードを返します。
	 */
	public String getMenuCd() {
		return menuCd;
	}

	/**
	 * メニューコードor集約メニューコード設定処理
	 * @param menuCd 設定する menuCd。
	 */
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	/**
	 * メニュー名称or集約メニュー名称取得処理
	 * 
	 * @return menuNameKj メニューコードor集約メニューコードを返します。
	 */
	public String getMenuNameKj() {
		return menuNameKj;
	}

	/**
	 * メニュー名称or集約メニュー名称設定処理
	 * @param menuNameKj 設定する menuNameKj。
	 */
	public void setMenuNameKj(String menuNameKj) {
		this.menuNameKj = menuNameKj;
	}

	/**
	 * @return kyakusu を戻します。
	 */
	public BigDecimal getKyakusu() {
		return kyakusu;
	}

	/**
	 * @param kyakusu 設定する kyakusu。
	 */
	public void setKyakusu(BigDecimal kyakusu) {
		this.kyakusu = kyakusu;
	}

	/**
	 * @return kyakusuZen を戻します。
	 */
	public BigDecimal getKyakusuZen() {
		return kyakusuZen;
	}

	/**
	 * @param kyakusuZen 設定する kyakusuZen。
	 */
	public void setKyakusuZen(BigDecimal kyakusuZen) {
		this.kyakusuZen = kyakusuZen;
	}

	/**
	 * @return netKyakusu を戻します。
	 */
	public BigDecimal getNetKyakusu() {
		return netKyakusu;
	}

	/**
	 * @param netKyakusu 設定する netKyakusu。
	 */
	public void setNetKyakusu(BigDecimal netKyakusu) {
		this.netKyakusu = netKyakusu;
	}

	/**
	 * @return netKyakusuZen を戻します。
	 */
	public BigDecimal getNetKyakusuZen() {
		return netKyakusuZen;
	}

	/**
	 * @param netKyakusuZen 設定する netKyakusuZen。
	 */
	public void setNetKyakusuZen(BigDecimal netKyakusuZen) {
		this.netKyakusuZen = netKyakusuZen;
	}
	/**
	 * @return netUriage を戻します。
	 */
	public BigDecimal getNetUriage() {
		return netUriage;
	}

	/**
	 * @param netUriage 設定する netUriage。
	 */
	public void setNetUriage(BigDecimal netUriage) {
		this.netUriage = netUriage;
	}

	/**
	 * @return netUriageZen を戻します。
	 */
	public BigDecimal getNetUriageZen() {
		return netUriageZen;
	}

	/**
	 * @param netUriageZen 設定する netUriageZen。
	 */
	public void setNetUriageZen(BigDecimal netUriageZen) {
		this.netUriageZen = netUriageZen;
	}
	/**
	 * @return uriage を戻します。
	 */
	public BigDecimal getUriage() {
		return uriage;
	}

	/**
	 * @param uriage 設定する uriage。
	 */
	public void setUriage(BigDecimal uriage) {
		this.uriage = uriage;
	}

	/**
	 * @return uriageZen を戻します。
	 */
	public BigDecimal getUriageZen() {
		return uriageZen;
	}

	/**
	 * @param uriageZen 設定する uriageZen。
	 */
	public void setUriageZen(BigDecimal uriageZen) {
		this.uriageZen = uriageZen;
	}

	/**
	 * @return kazuKei を戻します。
	 */
	public BigDecimal getKazuKei() {
		return kazuKei;
	}

	/**
	 * @param kazuKei 設定する kazuKei。
	 */
	public void setKazuKei(BigDecimal kazuKei) {
		this.kazuKei = kazuKei;
	}

	/**
	 * @return menuUriage を戻します。
	 */
	public BigDecimal getMenuUriage() {
		return menuUriage;
	}

	/**
	 * @param menuUriage 設定する menuUriage。
	 */
	public void setMenuUriage(BigDecimal menuUriage) {
		this.menuUriage = menuUriage;
	}
	/**
	 * 金額構成比取得処理
	 * 
	 * 売上/販売金額*100 小数点第3位四捨五入後、少数点第3以下切り捨て
	 * @return 金額構成比
	 */
	public BigDecimal getKingakuKoseiHi() {
		return Calculator.percentage(getMenuUriage(), getUriage(), 2);
	}
	/**
	 * グループ内行番号取得処理
	 * 
	 * @return groupRowNo を戻します。
	 */
	public BigDecimal getGroupRowNo() {
		return groupRowNo;
	}
	/**
	 * グループ内行番号設定処理
	 * 
	 * @param groupRowNo 設定する groupRowNo。
	 */
	public void setGroupRowNo(BigDecimal groupRowNo) {
		this.groupRowNo = groupRowNo;
	}
	/**
	 * @return tenpoCnt を戻します。
	 */
	public BigDecimal getTenpoCnt() {
		return tenpoCnt;
	}
	/**
	 * @param tenpoCnt 設定する tenpoCnt。
	 */
	public void setTenpoCnt(BigDecimal tenpoCnt) {
		this.tenpoCnt = tenpoCnt;
	}

    public static final String honbuCd_COLUMN = "HONBU_CD";
    public static final String honbuName_COLUMN = "HONBU_NAME";
    public static final String jigyouCd_COLUMN = "JIGYOU_CD";
    public static final String jigyouName_COLUMN = "JIGYOU_NAME";
    public static final String slareaCd_COLUMN = "SLAREA_CD";
    public static final String slareaName_COLUMN = "SLAREA_NAME";
    public static final String sibuCd_COLUMN = "SIBU_CD";
    public static final String sibuName_COLUMN = "SIBU_NAME";
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
     * 支部コード
     */
    private String sibuCd;
    /**
     * 支部名称
     */
    private String sibuName;
	/**
	 * @return honbuCd を戻します。
	 */
	public String getHonbuCd() {
		return honbuCd;
	}

	/**
	 * @param honbuCd 設定する honbuCd。
	 */
	public void setHonbuCd(String honbuCd) {
		this.honbuCd = honbuCd;
	}

	/**
	 * @return honbuName を戻します。
	 */
	public String getHonbuName() {
		return honbuName;
	}

	/**
	 * @param honbuName 設定する honbuName。
	 */
	public void setHonbuName(String honbuName) {
		this.honbuName = honbuName;
	}

	/**
	 * @return jigyouCd を戻します。
	 */
	public String getJigyouCd() {
		return jigyouCd;
	}

	/**
	 * @param jigyouCd 設定する jigyouCd。
	 */
	public void setJigyouCd(String jigyouCd) {
		this.jigyouCd = jigyouCd;
	}

	/**
	 * @return jigyouName を戻します。
	 */
	public String getJigyouName() {
		return jigyouName;
	}

	/**
	 * @param jigyouName 設定する jigyouName。
	 */
	public void setJigyouName(String jigyouName) {
		this.jigyouName = jigyouName;
	}

	/**
	 * @return slareaCd を戻します。
	 */
	public String getSlareaCd() {
		return slareaCd;
	}

	/**
	 * @param slareaCd 設定する slareaCd。
	 */
	public void setSlareaCd(String slareaCd) {
		this.slareaCd = slareaCd;
	}

	/**
	 * @return slareaName を戻します。
	 */
	public String getSlareaName() {
		return slareaName;
	}

	/**
	 * @param slareaName 設定する slareaName。
	 */
	public void setSlareaName(String slareaName) {
		this.slareaName = slareaName;
	}

	/**
	 * @return sibuCd を戻します。
	 */
	public String getSibuCd() {
		return sibuCd;
	}

	/**
	 * @param sibuCd 設定する sibuCd。
	 */
	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}

	/**
	 * 支部名称設定処理
	 * @param sibuName 設定する sibuName。
	 */
	public void setSibuName(String sibuName) {
		this.sibuName = sibuName;
	}

	public static final String kingakuKouseiHi_COLUMN = "KINGAKU_KOUSEI_HI";

    public static final String uriageZennenHi_COLUMN = "URIAGE_ZEN_HI";

    public static final String netUriageZennenHi_COLUMN = "NET_URIAGE_ZEN_HI";

    public static final String kyakusuZennenHi_COLUMN = "KYAKUSU_ZEN_HI";

    public static final String netKyakusuZennenHi_COLUMN = "NET_KYAKUSU_ZEN_HI";

    public static final String kyakutanka_COLUMN = "KYAKUTANKA";
    
    public static final String kyakutankaZen_COLUMN = "KYAKUTANKA_ZEN";
    
    public static final String kyakutankaZennenHi_COLUMN = "KYAKUTANKA_ZEN_HI";
    
    public static final String netKyakutanka_COLUMN = "NET_KYAKUTANKA";
    
    public static final String netKyakutankaZen_COLUMN = "NET_KYAKUTANKA_ZEN";

    public static final String netKyakutankaZennenHi_COLUMN = "NET_KYAKUTANKA_ZEN_HI";
    /**
     * 金額構成比
     */
    private BigDecimal kingakuKouseiHi = new BigDecimal("0");

    /**
     * 売上前年比
     */
    private BigDecimal uriageZennenHi = new BigDecimal("0");
    /**
     * NET売上前年比
     */
    private BigDecimal netUriageZennenHi = new BigDecimal("0");
    /**
     * 客数前年比
     */
    private BigDecimal kyakusuZennenHi = new BigDecimal("0");
    /**
     * NET客数前年比
     */
    private BigDecimal netKyakusuZennenHi = new BigDecimal("0");
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
     * NET客単価
     */
    private BigDecimal netKyakutanka = new BigDecimal("0");
    /**
     * NET前年客単価
     */
    private BigDecimal netKyakutankaZen = new BigDecimal("0");
    /**
     * NET客単価前年比
     */
    private BigDecimal netKyakutankaZennenHi = new BigDecimal("0");

    /**
	 * 支部名称取得処理
	 * 
	 * @return eigyoDt を戻します。
	 */
	public String getSibuName() {
		return CampNipoRefUtil.getRowTitleName(getRowType(), sibuName);
	}


	/**
	 * @return kyakutanka を戻します。
	 */
	public BigDecimal getKyakutanka() {
		return kyakutanka;
	}

	/**
	 * @param kyakutanka 設定する kyakutanka。
	 */
	public void setKyakutanka(BigDecimal kyakutanka) {
		this.kyakutanka = kyakutanka;
	}

	/**
	 * @return kyakutankaZen を戻します。
	 */
	public BigDecimal getKyakutankaZen() {
		return kyakutankaZen;
	}

	/**
	 * @param kyakutankaZen 設定する kyakutankaZen。
	 */
	public void setKyakutankaZen(BigDecimal kyakutankaZen) {
		this.kyakutankaZen = kyakutankaZen;
	}

	/**
	 * @return netKyakutanka を戻します。
	 */
	public BigDecimal getNetKyakutanka() {
		return netKyakutanka;
	}

	/**
	 * @param netKyakutanka 設定する netKyakutanka。
	 */
	public void setNetKyakutanka(BigDecimal netKyakutanka) {
		this.netKyakutanka = netKyakutanka;
	}

	/**
	 * @return netKyakutankaZen を戻します。
	 */
	public BigDecimal getNetKyakutankaZen() {
		return netKyakutankaZen;
	}

	/**
	 * @param netKyakutankaZen 設定する netKyakutankaZen。
	 */
	public void setNetKyakutankaZen(BigDecimal netKyakutankaZen) {
		this.netKyakutankaZen = netKyakutankaZen;
	}

	/**
	 * @return kyakutankaZennenHi を戻します。
	 */
	public BigDecimal getKyakutankaZennenHi() {
		return kyakutankaZennenHi;
	}

	/**
	 * @param kyakutankaZennenHi 設定する kyakutankaZennenHi。
	 */
	public void setKyakutankaZennenHi(BigDecimal kyakutankaZennenHi) {
		this.kyakutankaZennenHi = kyakutankaZennenHi;
	}

	/**
	 * @return netKyakutankaZennenHi を戻します。
	 */
	public BigDecimal getNetKyakutankaZennenHi() {
		return netKyakutankaZennenHi;
	}

	/**
	 * @param netKyakutankaZennenHi 設定する netKyakutankaZennenHi。
	 */
	public void setNetKyakutankaZennenHi(BigDecimal netKyakutankaZennenHi) {
		this.netKyakutankaZennenHi = netKyakutankaZennenHi;
	}

	/**
	 * @return kyakusuZennenHi を戻します。
	 */
	public BigDecimal getKyakusuZennenHi() {
		return kyakusuZennenHi;
	}

	/**
	 * @param kyakusuZennenHi 設定する kyakusuZennenHi。
	 */
	public void setKyakusuZennenHi(BigDecimal kyakusuZennenHi) {
		this.kyakusuZennenHi = kyakusuZennenHi;
	}

	/**
	 * @return netKyakusuZennenHi を戻します。
	 */
	public BigDecimal getNetKyakusuZennenHi() {
		return netKyakusuZennenHi;
	}

	/**
	 * @param netKyakusuZennenHi 設定する netKyakusuZennenHi。
	 */
	public void setNetKyakusuZennenHi(BigDecimal netKyakusuZennenHi) {
		this.netKyakusuZennenHi = netKyakusuZennenHi;
	}

	/**
	 * @return netUriageZennenHi を戻します。
	 */
	public BigDecimal getNetUriageZennenHi() {
		return netUriageZennenHi;
	}

	/**
	 * @param netUriageZennenHi 設定する netUriageZennenHi。
	 */
	public void setNetUriageZennenHi(BigDecimal netUriageZennenHi) {
		this.netUriageZennenHi = netUriageZennenHi;
	}

	/**
	 * @return uriageZennenHi を戻します。
	 */
	public BigDecimal getUriageZennenHi() {
		return uriageZennenHi;
	}

	/**
	 * @param uriageZennenHi 設定する uriageZennenHi。
	 */
	public void setUriageZennenHi(BigDecimal uriageZennenHi) {
		this.uriageZennenHi = uriageZennenHi;
	}

	/**
	 * @return kingakuKouseiHi を戻します。
	 */
	public BigDecimal getKingakuKouseiHi() {
		return kingakuKouseiHi;
	}

	/**
	 * @param kingakuKouseiHi 設定する kingakuKouseiHi。
	 */
	public void setKingakuKouseiHi(BigDecimal kingakuKouseiHi) {
		this.kingakuKouseiHi = kingakuKouseiHi;
	}
}
