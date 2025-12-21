package jp.co.isid.mos.bird.bizsupport.campcheckamount.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountConstants;

public class UISpotQuantityInfo {
    
    public static final String TABLE = "CT01JMST";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String shoCdDai_COLUMN = "SHO_CD_DAI";
    
    public static final String posNameKna_COLUMN = "POS_NAME_KNA";
    
    public static final String tankaUri_COLUMN = "TANKA_URI";
    
    public static final String yuMushoKbn_COLUMN = "YU_MUSHO_KBN";
    
    public static final String shoAmount_COLUMN = "SHO_AMOUNT";
    
    public static final String mushoEAmt_COLUMN = "MUSHO_E_AMT";
    
    public static final String yushoEAmt_COLUMN = "YUSHO_E_AMT";
    
    public static final String yushoTuika_COLUMN = "YUSHO_TUIKA";
    
    public static final String mushoAmt_COLUMN = "MUSHO_AMT";
    
    public static final String yushoAmt_COLUMN = "YUSHO_AMT";
    
    public static final String posNisugata_COLUMN = "POS_NISUGATA";
    
    public static final String posIrime_COLUMN = "POS_IRIME";
    
    public static final String miseYushoAmt_COLUMN = "MISE_YUSHO_AMT";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 代表商品コード
     */
    private String shoCdDai;
    
    /**
     * 代表商品名称
     */
    private String posNameKna;
    
    /**
     * 仮売上単価
     */
    private BigDecimal tankaUri;
    
    /**
     * 有償無償区分
     */
    private String yuMushoKbn;
    
    /**
     * 受注数量
     */
    private BigDecimal shoAmount;
    
    /**
     * 無償最終数量
     */
    private BigDecimal mushoEAmt;
    
    /**
     * 一括有償最終数量
     */
    private BigDecimal yushoEAmt;
    
    /**
     * 有償追加数量
     */
    private BigDecimal yushoTuika;
    
    /**
     * 無償数量(画面表示用)
     */
    private BigDecimal mushoAmt;
    
    /**
     * 有償数量(画面表示用)
     */
    private BigDecimal yushoAmt;
    
    /**
     * 合計(画面表示用)
     */
    private BigDecimal amtSum;
    
    /**
     * POS荷姿(CSV出力用）
     */
    private String posNisugata;
    
    /**
     * 入り目メモ(CSV出力用）
     */
    private String posIrime;
    
    /**
     * 有償金額(CSV出力用）
     */
    private BigDecimal miseYushoAmt;
    
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
     * 店名称を取得します。
     * @return 店名称
     */
    public String getMiseNameKj() {
        return miseNameKj;
    }
    /**
     * 店名称を設定します。
     * @param miseNameKj 店名称
     */
    public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj = miseNameKj;
    }
    
    /**
     * 代表商品コードを取得します。
     * @return 代表商品コード
     */
    public String getShoCdDai() {
        return shoCdDai;
    }
    /**
     * 代表商品コードを設定します。
     * @param shoCdDai 代表商品コード
     */
    public void setShoCdDai(String shoCdDai) {
        this.shoCdDai = shoCdDai;
    }
    
    /**
     * 代表商品名称を取得します。
     * @return 代表商品名称
     */
    public String getPosNameKna() {
        return posNameKna;
    }
    /**
     * 代表商品名称を設定します。
     * @param posNameKna 代表商品名称
     */
    public void setPosNameKna(String posNameKna) {
        this.posNameKna = posNameKna;
    }
    
    /**
     * 仮売上単価を取得します。
     * @return 仮売上単価
     */
    public BigDecimal getTankaUri() {
        return tankaUri;
    }
    /**
     * 仮売上単価を設定します。
     * @param tankaUri 仮売上単価
     */
    public void setTankaUri(BigDecimal tankaUri) {
        this.tankaUri = tankaUri;
    }
    
    /**
     * 有償無償区分を取得します。
     * @return 有償無償区分
     */
    public String getYuMushoKbn() {
        return yuMushoKbn;
    }
    /**
     * 有償無償区分を設定します。
     * @param yuMushoKbn 有償無償区分
     */
    public void setYuMushoKbn(String yuMushoKbn) {
        this.yuMushoKbn = yuMushoKbn;
    }
    
    /**
     * 受注数量を取得します。
     * @return 受注数量
     */
    public BigDecimal getShoAmount() {
        return shoAmount;
    }
    /**
     * 受注数量を設定します。
     * @param shoAmount 受注数量
     */
    public void setShoAmount(BigDecimal shoAmount) {
        this.shoAmount = shoAmount;
    }
    
    /**
     * 無償最終数量を取得します。
     * @return 無償最終数量
     */
    public BigDecimal getMushoEAmt() {
        return mushoEAmt;
    }
    /**
     * 無償最終数量を設定します。
     * @param mushoEAmt 無償最終数量
     */
    public void setMushoEAmt(BigDecimal mushoEAmt) {
        this.mushoEAmt = mushoEAmt;
    }
    
    /**
     * 一括有償最終数量を取得します。
     * @return 一括有償最終数量
     */
    public BigDecimal getYushoEAmt() {
        return yushoEAmt;
    }
    /**
     * 一括有償最終数量を設定します。
     * @param yushoEAmt 一括有償最終数量
     */
    public void setYushoEAmt(BigDecimal yushoEAmt) {
        this.yushoEAmt = yushoEAmt;
    }
    
    /**
     * 有償追加数量を取得します。
     * @return 有償追加数量
     */
    public BigDecimal getYushoTuika() {
        return yushoTuika;
    }
    /**
     * 有償追加数量を設定します。
     * @param yushoTuika 有償追加数量
     */
    public void setYushoTuika(BigDecimal yushoTuika) {
        this.yushoTuika = yushoTuika;
    }
    
    /**
     * 無償数量(画面表示用)を取得します。
     * @return 無償数量(画面表示用)
     */
    public BigDecimal getMushoAmt() {
    	/*
    	 * 無償
    	 *      受注数量が0より大きい場合：受注数量
    	 *      数量が0の場合、無償最終数量
    	 * 有償
    	 *      無償最終数量
    	 */
    	if(CampCheckAmountConstants.KBN_MUSHO.equals(getYuMushoKbn())) {
    		if(getShoAmount().compareTo(new BigDecimal(0)) > 0){
    			this.mushoAmt = getShoAmount();
    		} else {
    			this.mushoAmt = getMushoEAmt();
    		}
    	} else {
    		this.mushoAmt = getMushoEAmt();
    	}
        return mushoAmt;
    }
    
    /**
     * 有償数量(画面表示用)を取得します。
     * @return 有償数量(画面表示用)
     */
    public BigDecimal getYushoAmt() {
    	/*
    	 * 無償
    	 *      受注数量が0より大きい場合：受注数量
    	 *      数量が0の場合、有償追加数量
    	 * 有償
    	 *      有償追加数量
    	 */
    	if(CampCheckAmountConstants.KBN_YUSHO.equals(getYuMushoKbn())) {
    		if(getShoAmount().compareTo(new BigDecimal(0)) > 0){
    			this.yushoAmt = getShoAmount();
    		} else {
    			this.yushoAmt = getYushoTuika();
    		}
    	} else {
    		this.yushoAmt = getYushoTuika();
    	}
        return yushoAmt;
    }
    
    /**
     * 合計(画面表示用)を取得します。
     * @return 合計(画面表示用)
     */
    public BigDecimal getAmtSum() {
    	this.amtSum = (getMushoAmt().add(getYushoEAmt())).add(getYushoAmt());
        return amtSum;
    }
    
    /**
     * POS荷姿(CSV出力用）を取得します。
     * @return POS荷姿(CSV出力用）
     */
    public String getPosNisugata() {
        return posNisugata;
    }
    /**
     * POS荷姿(CSV出力用）を設定します。
     * @param posNisugata POS荷姿(CSV出力用）
     */
    public void setPosNisugata(String posNisugata) {
        this.posNisugata = posNisugata;
    }
    
    /**
     * 入り目メモ(CSV出力用）を取得します。
     * @return 入り目メモ(CSV出力用）
     */
    public String getPosIrime() {
        return posIrime;
    }
    /**
     * 入り目メモ(CSV出力用）を設定します。
     * @param posIrime 入り目メモ(CSV出力用）
     */
    public void setPosIrime(String posIrime) {
        this.posIrime = posIrime;
    }
    
    /**
     * 有償金額(CSV出力用）を取得します。
     * @return 有償金額(CSV出力用）
     */
    public BigDecimal getMiseYushoAmt() {
    	this.miseYushoAmt = getTankaUri().multiply(getYushoAmt().add(getYushoEAmt()));
        return miseYushoAmt;
    }
}
