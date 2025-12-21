package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.entity;
import java.math.BigDecimal;

public class UIMiseListInfo {
    
    public static final String TABLE = "BT60ZNIP";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String blockCd_COLUMN = "BLOCK_CD";
    
    public static final String blockName_COLUMN = "BLOCK_NAME";
    
    public static final String tenpoShubetu_COLUMN = "TENPO_SHUBETU";
    
    public static final String kyugyoKbn_COLUMN = "KYUGYOU";
    
    public static final String mosgKbn_COLUMN = "MOSG_KBN";
    
    public static final String yagoDtlName_COLUMN = "YAGO_DTL_NAME";
    
    public static final String tenkoKbn_COLUMN = "TENKO_KBN";
    
    public static final String uriage_COLUMN = "URIAGE";
    
    public static final String yosan_COLUMN = "URI_YOSAN";
    
    public static final String kyakusu_COLUMN = "KYAKUSU";
    
    public static final String zennenUriage_COLUMN = "URIAGE_ZEN";
    
    public static final String zennenKyakusu_COLUMN = "KYAKUSU_ZEN";
    
    public static final String eigyoDays_COLUMN = "EIGYO_DAYS";
    
    public static final String zennenEigyoDays_COLUMN = "EIGYO_DAYS_ZEN";
    
    public static final String tenkoKbnZen_COLUMN = "TENKO_KBN_ZEN";
    
    public static final String count_COLUMN = "COUNT";
    /** カラム名称：売上のNET値 */
    public static final String zennenHiTaisyoUriage_COLUMN = "ZENNEN_HI_TAISYO_URIAGE";
    /** カラム名称：客数のNET値 */
    public static final String zennenHiTaisyoKyakusu_COLUMN = "ZENNEN_HI_TAISYO_KYAKUSU";
    /** カラム名称：前年売上のNET値 */
    public static final String zennenHiTaisyoMaeUriage_COLUMN = "ZENNEN_HI_TAISYO_MAE_URIAGE";
    /** カラム名称：前年件数のNET値 */
    public static final String zennenHiTaisyoMaeKyakusu_COLUMN = "ZENNEN_HI_TAISYO_MAE_KYAKUSU";
    
    /**
     * 店舗数
     */
    private int count;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称（漢字）
     */
    private String miseNameKj;
    
    /**
     * ブロックコード
     */
    private String blockCd;
    
    /**
     * ブロック名
     */
    private String blockName;
    
    /**
     * 店種
     */
    private String tenpoShubetu;
    
    /**
     * 店種名
     */
    private String tenpoShubetuName;
    
    /**
     * 天候
     */
    private String tenkoKbn;

    /**
     * 前年天候
     */
    private String tenkoKbnZen;
    
    /**
     * 天候名
     */
    private String tenkoKbnName;

    /**
     * 前年天候名
     */
    private String tenkoKbnZenName;
    
    /**
     * 休業
     */
    private String kyugyoKbn;
    
    /**
     * 休業名
     */
    private String kyugyoKbnName;
    
    /**
     * 前年休業
     */
    private String kyugyoKbnZen;
    
    /**
     * 前年休業名
     */
    private String kyugyoKbnZenName;
    
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
     *屋号コード
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
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称（漢字）を設定します。
     * @param miseNameKj 店名称（漢字）
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
                     
    /**
     * 屋号区分を取得します。
     * @return 屋号区分
     */
    public String getMosgKbn() {
        return mosgKbn;
    }
    /**
     * 屋号区分を設定します。
     * @param mosgKbn 屋号区分
     */
    public void setMosgKbn(String mosgKbn) {
        this.mosgKbn = mosgKbn;
    }

    /**
     * 客数を取得します。
     * @return 客数
     */
    public int getKyakusu() {
        return kyakusu;
    }
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(int kyakusu) {
        this.kyakusu = kyakusu;
    }        
    
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
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
	public String getTaseiRituClass() {
		return taseiRituClass;
	}
	public void setTaseiRituClass(String taseiRituClass) {
		this.taseiRituClass = taseiRituClass;
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
	public String getBlockCd() {
		return blockCd;
	}
	public void setBlockCd(String blockCd) {
		this.blockCd = blockCd;
	}
	public String getYagoCd() {
		return yagoCd;
	}
	public void setYagoCd(String yagoCd) {
		this.yagoCd = yagoCd;
	}
	public String getDispCode() {
		return dispCode;
	}
	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
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
	public String getTenpoShubetu() {
		return tenpoShubetu;
	}
	public void setTenpoShubetu(String tenpoShubetu) {
		this.tenpoShubetu = tenpoShubetu;
	}
	public String getTenkoKbn() {
		return tenkoKbn;
	}
	public void setTenkoKbn(String tenkoKbn) {
		this.tenkoKbn = tenkoKbn;
	}
	public void setZennenKyakusu(int zennenKyakusu) {
		this.zennenKyakusu = zennenKyakusu;
	}
	public BigDecimal getTaseiRitu() {
		return taseiRitu;
	}
	public void setTaseiRitu(BigDecimal taseiRitu) {
		this.taseiRitu = taseiRitu;
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
	public BigDecimal getZennenHiKyakuTanka() {
		return zennenHiKyakuTanka;
	}
	public void setZennenHiKyakuTanka(BigDecimal zennenHiKyakuTanka) {
		this.zennenHiKyakuTanka = zennenHiKyakuTanka;
	}
	public BigDecimal getZennenHiUriage() {
		return zennenHiUriage;
	}
	public void setZennenHiUriage(BigDecimal zennenHiUriage) {
		this.zennenHiUriage = zennenHiUriage;
	}
	public BigDecimal getZennenKyakuTanka() {
		return zennenKyakuTanka;
	}
	public void setZennenKyakuTanka(BigDecimal zennenKyakuTanka) {
		this.zennenKyakuTanka = zennenKyakuTanka;
	}
	public int getZennenKyakusu() {
		return zennenKyakusu;
	}
	public String getTenpoShubetuName() {
		return tenpoShubetuName;
	}
	public void setTenpoShubetuName(String tenpoShubetuName) {
		this.tenpoShubetuName = tenpoShubetuName;
	}
	public String getZennenHiKyakusuClass() {
		return zennenHiKyakusuClass;
	}
	public void setZennenHiKyakusuClass(String zennenHiKyakusuClass) {
		this.zennenHiKyakusuClass = zennenHiKyakusuClass;
	}
	public String getZennenHiKyakuTankaClass() {
		return zennenHiKyakuTankaClass;
	}
	public void setZennenHiKyakuTankaClass(String zennenHiKyakuTankaClass) {
		this.zennenHiKyakuTankaClass = zennenHiKyakuTankaClass;
	}
	public String getZennenHiUriageClass() {
		return zennenHiUriageClass;
	}
	public void setZennenHiUriageClass(String zennenHiUriageClass) {
		this.zennenHiUriageClass = zennenHiUriageClass;
	}
	public String getTenkoKbnZen() {
		return tenkoKbnZen;
	}
	public void setTenkoKbnZen(String tenkoKbnZen) {
		this.tenkoKbnZen = tenkoKbnZen;
	}
	public String getTenkoKbnName() {
		return tenkoKbnName;
	}
	public void setTenkoKbnName(String tenkoKbnName) {
		this.tenkoKbnName = tenkoKbnName;
	}
	public String getTenkoKbnZenName() {
		return tenkoKbnZenName;
	}
	public void setTenkoKbnZenName(String tenkoKbnZenName) {
		this.tenkoKbnZenName = tenkoKbnZenName;
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
	public BigDecimal getZennenHiTaisyoUriage() {
		return zennenHiTaisyoUriage;
	}
	public void setZennenHiTaisyoUriage(BigDecimal zennenHiTaisyoUriage) {
		this.zennenHiTaisyoUriage = zennenHiTaisyoUriage;
	}
	public String getKyugyoKbn() {
		return kyugyoKbn;
	}
	public void setKyugyoKbn(String kyugyoKbn) {
		this.kyugyoKbn = kyugyoKbn;
	}
	public String getKyugyoKbnName() {
		return kyugyoKbnName;
	}
	public void setKyugyoKbnName(String kyugyoKbnName) {
		this.kyugyoKbnName = kyugyoKbnName;
	}
	public String getKyugyoKbnZen() {
		return kyugyoKbnZen;
	}
	public void setKyugyoKbnZen(String kyugyoKbnZen) {
		this.kyugyoKbnZen = kyugyoKbnZen;
	}
	public String getKyugyoKbnZenName() {
		return kyugyoKbnZenName;
	}
	public void setKyugyoKbnZenName(String kyugyoKbnZenName) {
		this.kyugyoKbnZenName = kyugyoKbnZenName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isViewFlg() {
		return viewFlg;
	}
	public void setViewFlg(boolean viewFlg) {
		this.viewFlg = viewFlg;
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
	
	public void clear(String dispCode){
			
		this.setUriage(new BigDecimal(0));
		this.setYosan(new BigDecimal(0));
		this.setKyakusu( 0 );
		this.setTaseiRitu(new BigDecimal(0));
		this.setKyakuTanka(new BigDecimal(0));
		this.setZennenUriage(new BigDecimal(0));
		this.setZennenKyakusu(0);
		this.setZennenHiKyakuTanka(new BigDecimal(0));
		this.setZennenHiKyakusu(new BigDecimal(0));
		this.setZennenHiUriage(new BigDecimal(0));
		this.setCount( 0 );
		this.setZennenHiTaisyoUriage(new BigDecimal(0));
		this.setZennenHiTaisyoMaeUriage(new BigDecimal(0));
		this.setZennenHiTaisyoKyakusu(new BigDecimal(0));
		this.setZennenHiTaisyoMaeKyakusu(new BigDecimal(0));
		this.setZennenHiTaisyoKyakuTanka(new BigDecimal(0));
		this.setZennenHiTaisyoMaeKyakuTanka(new BigDecimal(0));
    	
    	//ディスプレイコードのセット
		this.setDispCode(dispCode);		
	}
}
