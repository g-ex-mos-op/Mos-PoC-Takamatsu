/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.camp.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 日報エンティティーインターフェース
 * 
 * @author xkinu
 *
 */
public abstract class Nipo implements Uriage {
	
    public static final String groupRowNo_COLUMN = "GROUP_ROW_NO";
    public static final String menuCd_COLUMN = "MENU_CD";
    public static final String menuName_COLUMN = "MENU_NAME";
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
	 * 前年比(客数)
	 * @return kyakusuZennenHi を戻します。
	 */
	public BigDecimal getKyakusuZennenHi() {
		return Calculator.percentage(getKyakusu(), getKyakusuZen(), 2);
	}

	/**
	 * 客単価
	 * @return kyakutanka を戻します。
	 */
	public BigDecimal getKyakutanka() {
		return Calculator.divide(getUriage(), getKyakusu(), 0);
	}

	/**
	 * 前年客単価
	 * @return kyakutankaZen を戻します。
	 */
	public BigDecimal getKyakutankaZen() {
		return Calculator.divide(getUriageZen(), getKyakusuZen(), 0);
	}

	/**
	 * 前年比(客単価)
	 * @return kyakutankaZennenHi を戻します。
	 */
	public BigDecimal getKyakutankaZennenHi() {
		return Calculator.percentage(getKyakutanka(), getKyakutankaZen(), 2);
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
	 * NET前年比(客数)
	 * @return netKyakusuZennenHi を戻します。
	 */
	public BigDecimal getNetKyakusuZennenHi() {
		return Calculator.percentage(getNetKyakusu(), getNetKyakusuZen(), 2);
	}

	/**
	 * NET客単価
	 * @return netKyakutanka を戻します。
	 */
	public BigDecimal getNetKyakutanka() {
		return Calculator.divide(getNetUriage(), getNetKyakusu(), 0);
	}

	/**
	 * NET前年客単価
	 * @return netKyakutankaZen を戻します。
	 */
	public BigDecimal getNetKyakutankaZen() {
		return Calculator.divide(getNetUriageZen(), getNetKyakusuZen(), 0);
	}

	/**
	 * NET前年比(客単価)
	 * @return netKyakutankaZennenHi を戻します。
	 */
	public BigDecimal getNetKyakutankaZennenHi() {
		return Calculator.percentage(getNetKyakutanka(), getNetKyakutankaZen(), 2);
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
	 * NET前年比(売上)
	 * @return netUriageZennenHi を戻します。
	 */
	public BigDecimal getNetUriageZennenHi() {
		return Calculator.percentage(getNetUriage(), getNetUriageZen(), 2);
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
	 * 前年比(売上)
	 * @return uriageZennenHi を戻します。
	 */
	public BigDecimal getUriageZennenHi() {
		return Calculator.percentage(getUriage(), getUriageZen(), 2);
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
}
