/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.RowType;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 店舗一覧用キャンペーン日報エンティティ
 * 
 * @author xkinu
 *
 */
public class UINipoMise implements UINipo {
    
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

    public static final String blockCd_COLUMN = "BLOCK_CD";
    public static final String blockNameKj_COLUMN = "BLOCK_NAME";
    public static final String miseCd_COLUMN = "MISE_CD";
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    public static final String tenpoShubetu_COLUMN = "TENPO_SHUBETU";
    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";    
    public static final String eigyoDaysZen_COLUMN = "EIGYO_DAYS_ZEN";
    public static final String openKbn_COLUMN = "OPEN_KBN";    
    public static final String openKbnZen_COLUMN = "OPEN_KBN_ZEN";
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    public static final String tenkoKbnZen_COLUMN = "TENKO_KBN_ZEN";
    public static final String sibuTenpoCnt_COLUMN = "SIBU_TENPO_CNT";
    public static final String allTenpoCnt_COLUMN = "ALL_TENPO_CNT";
    public static final String rankInSibu_COLUMN = "RANK_IN_SIBU";
    public static final String rankInAll_COLUMN = "RANK_IN_ALL";

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
	
    /**
     * ブロックコード
     */
    private String blockCd;
    /**
     * ブロックコード
     */
    private String blockName;
    /**
     * 店舗コード
     */
    private String miseCd;
    /**
     * 店舗コード
     */
    private String miseNameKj;
    /**
     * 店舗種別
     */
    private String tenpoShubetu;
    /**
     * 天候区分
     */
    private String tenkoKbn;
    /**
     * 前年天候区分
     */
    private String tenkoKbnZen;
    /**
     * 営業日数
     */
    private BigDecimal eigyoDays = new BigDecimal("0");
    /**
     * 前年営業日数
     */
    private BigDecimal eigyoDaysZen = new BigDecimal("0");
    
    /**
     * OPEN区分
     */
    private BigDecimal openKbn = new BigDecimal("0");
    /**
     * 前年OPEN区分
     */
    private BigDecimal openKbnZen = new BigDecimal("0");
    /**
     * 支部内対象店舗数
     */
    private BigDecimal sibuTenpoCnt = new BigDecimal("0");
    /**
     * 全国対象店舗数
     */
    private BigDecimal allTenpoCnt = new BigDecimal("0");
    /**
     * 支部順位
     */
    private BigDecimal rankInSibu = new BigDecimal("0");
    /**
     * 全国順位
     */
    private BigDecimal rankInAll = new BigDecimal("0");

	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}

	/**
	 * @param miseCd 設定する eigyoDt。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}

	/**
	 * @return miseNameKj を戻します。
	 */
	public String getMiseNameKj() {
		return miseNameKj;
	}

	/**
	 * @param miseNameKj 設定する miseNameKj。
	 */
	public void setMiseNameKj(String miseNameKj) {
		this.miseNameKj = miseNameKj;
	}

	/**
	 * @return openKbnZen を戻します。
	 */
	public BigDecimal getOpenKbnZen() {
		return openKbnZen;
	}

	/**
	 * @param openKbnZen 設定する openKbnZen。
	 */
	public void setOpenKbnZen(BigDecimal openKbnZen) {
		this.openKbnZen = openKbnZen;
	}

	/**
	 * 天候区分取得処理
	 * @return tenkoKbn を戻します。
	 */
	public String getTenkoKbn() {
		return tenkoKbn;
	}

	/**
	 * 天候区分設定処理
	 * @param tenkoKbn 設定する tenkoKbn。
	 */
	public void setTenkoKbn(String tenkoKbn) {
		this.tenkoKbn = tenkoKbn;
	}
	/**
	 * 天候区分漢字取得処理
	 * @return tenkoKbn を戻します。
	 */
	public String getTenkoKbnKigou() {
		return CommonUtil.getTenkoKj(getTenkoKbn());
	}

	/**
	 * 前年天候区分取得処理
	 * @return tenkoKbnZen を戻します。
	 */
	public String getTenkoKbnZen() {
		return tenkoKbnZen;
	}

	/**
	 * 前年天候区分設定処理
	 * @param tenkoKbnZen 設定する tenkoKbnZen。
	 */
	public void setTenkoKbnZen(String tenkoKbnZen) {
		this.tenkoKbnZen = tenkoKbnZen;
	}
	/**
	 * 前年天候区分漢字取得処理
	 * @return tenkoKbn を戻します。
	 */
	public String getTenkoKbnZenKigou() {
		return CommonUtil.getTenkoKj(getTenkoKbnZen());
	}

	/**
	 * @return blockCd を戻します。
	 */
	public String getBlockCd() {
		return blockCd;
	}

	/**
	 * @param blockCd 設定する blockCd。
	 */
	public void setBlockCd(String blockCd) {
		this.blockCd = blockCd;
	}

	/**
	 * @param blockName 設定する blockName。
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	/**
	 * @return eigyoDays を戻します。
	 */
	public BigDecimal getEigyoDays() {
		return eigyoDays;
	}

	/**
	 * @param eigyoDays 設定する eigyoDays。
	 */
	public void setEigyoDays(BigDecimal eigyoDays) {
		this.eigyoDays = eigyoDays;
	}

	/**
	 * @return eigyoDaysZen を戻します。
	 */
	public BigDecimal getEigyoDaysZen() {
		return eigyoDaysZen;
	}

	/**
	 * @param eigyoDaysZen 設定する eigyoDaysZen。
	 */
	public void setEigyoDaysZen(BigDecimal eigyoDaysZen) {
		this.eigyoDaysZen = eigyoDaysZen;
	}

	/**
	 * @return openKbn を戻します。
	 */
	public BigDecimal getOpenKbn() {
		return openKbn;
	}

	/**
	 * @param openKbn 設定する openKbn。
	 */
	public void setOpenKbn(BigDecimal openKbn) {
		this.openKbn = openKbn;
	}

	/**
	 * @return tenpoShubetu を戻します。
	 */
	public String getTenpoShubetu() {
		return tenpoShubetu;
	}

	/**
	 * @param tenpoShubetu 設定する tenpoShubetu。
	 */
	public void setTenpoShubetu(String tenpoShubetu) {
		this.tenpoShubetu = tenpoShubetu;
	}
	/**
	 * 店舗種別略語名称取得
	 * 
	 * @return tenpoShubetu を戻します。
	 */
	public String getTenpoShubetuRyakuKj() {
		return CommonUtil.getTenpoShubetuKj(getTenpoShubetu());
	}

	/**
	 * @return rankInAll を戻します。
	 */
	public BigDecimal getRankInAll() {
		return rankInAll;
	}

	/**
	 * @param rankInAll 設定する rankInAll。
	 */
	public void setRankInAll(BigDecimal rankInAll) {
		this.rankInAll = rankInAll;
	}

	/**
	 * @return rankInSibu を戻します。
	 */
	public BigDecimal getRankInSibu() {
		return rankInSibu;
	}

	/**
	 * @param rankInSibu 設定する rankInSibu。
	 */
	public void setRankInSibu(BigDecimal rankInSibu) {
		this.rankInSibu = rankInSibu;
	}

	/**
	 * @return allTenpoCnt を戻します。
	 */
	public BigDecimal getAllTenpoCnt() {
		return allTenpoCnt;
	}

	/**
	 * @param allTenpoCnt 設定する allTenpoCnt。
	 */
	public void setAllTenpoCnt(BigDecimal allTenpoCnt) {
		this.allTenpoCnt = allTenpoCnt;
	}

	/**
	 * @return sibuTenpoCnt を戻します。
	 */
	public BigDecimal getSibuTenpoCnt() {
		return sibuTenpoCnt;
	}

	/**
	 * @param sibuTenpoCnt 設定する sibuTenpoCnt。
	 */
	public void setSibuTenpoCnt(BigDecimal sibuTenpoCnt) {
		this.sibuTenpoCnt = sibuTenpoCnt;
	}

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
	 * ブロック名称取得処理
	 * 
	 * @return eigyoDt を戻します。
	 */
	public String getBlockName() {
        if (!RowType.CD_MENU.equals(getRowType())) {
            return CampNipoRefUtil.getRowTitleName(getRowType(), blockName);
        }
        else {
            if (getGroupRowNo().intValue() == 1) {
                return CampNipoRefUtil.getRowTitleName(getRowType(), blockName);
            }
            else {
                return "";
            }
        }
	}
    /**
	 * エリア大コード対象店舗名称取得処理
	 * 
	 * @return eigyoDt を戻します。
	 */
	public String getMiseNameKjForAreaDai() {
        if (!RowType.CD_MENU.equals(getRowType())) {
            return CampNipoRefUtil.getRowTitleName(getRowType(), getMiseNameKj());
        }
        else {
            if (getGroupRowNo().intValue() == 1) {
                return CampNipoRefUtil.getRowTitleName(getRowType(), getMiseNameKj());
            }
            else {
                return "";
            }
        }
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
