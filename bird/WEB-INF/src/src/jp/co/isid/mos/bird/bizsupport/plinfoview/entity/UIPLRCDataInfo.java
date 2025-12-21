package jp.co.isid.mos.bird.bizsupport.plinfoview.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

public class UIPLRCDataInfo {
    
    public static final String TABLE = "WT54PLRC";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String plYm_COLUMN = "PL_YM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String koumokuNo_COLUMN = "KOUMOKU_NO";
    
    public static final String dispOrder_COLUMN = "DISP_ORDER";
    
    public static final String koumokuName_COLUMN = "KOUMOKU_NAME";
    
    public static final String koumokuZoku_COLUMN = "KOUMOKU_ZOKU";
    
    public static final String kingaku_COLUMN = "KINGAKU";
    
    public static final String dispColor_COLUMN = "DISP_COLOR";
    
    public static final String dispPosition_COLUMN = "DISP_POSITION";
    
    public static final String kouseihiMoto_COLUMN = "KOUSEIHI_MOTO";
    
    public static final String lastTmsp_COLUMN = "LAST_TMSP";
    
    /**
     * 企業コード
     */
    private String companyCd;
    
    /**
     * ＰＬ年月
     */
    private String plYm;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 項目番号
     */
    private String koumokuNo;
    
    /**
     * 表示順
     */
    private BigDecimal dispOrder;
    
    /**
     * 項目名称
     */
    private String koumokuName;
    
    /**
     * 項目属性
     */
    private String koumokuZoku;
    
    /**
     * 金額
     */
    private BigDecimal kingaku;
    
    /**
     * 表示色
     */
    private String dispColor;
    
    /**
     * 文字位置
     */
    private String dispPosition;
    
    /**
     * 構成比元区分
     */
    private String kouseihiMoto;
    
    /**
     * 更新日
     */
    private String lastTmsp;
    
    /**
     * 金額（前年）
     */
    private BigDecimal kingakuZennen;
    
    /**
     * 構成比元金額
     */
    private BigDecimal kouseihiMotoKingaku;
    
    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
    }
    
    /**
     * ＰＬ年月を取得します。
     * @return ＰＬ年月
     */
    public String getPlYm() {
        return plYm;
    }
    /**
     * ＰＬ年月を設定します。
     * @param plYm ＰＬ年月
     */
    public void setPlYm(String plYm) {
        this.plYm = plYm;
    }
    
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
     * 項目番号を取得します。
     * @return 項目番号
     */
    public String getKoumokuNo() {
        return koumokuNo;
    }
    /**
     * 項目番号を設定します。
     * @param koumokuNo 項目番号
     */
    public void setKoumokuNo(String koumokuNo) {
        this.koumokuNo = koumokuNo;
    }
    
    public BigDecimal getKoumokuNoNum() {
        BigDecimal num = new BigDecimal("0");
        try {
        	num = new BigDecimal(getKoumokuNo());
        }
        catch (Exception ex) {
        	throw new FtlSystemException("PL照会");
        }
        return num;
    }
    
    /**
     * 表示順を取得します。
     * @return 表示順
     */
    public BigDecimal getDispOrder() {
        return dispOrder;
    }
    /**
     * 表示順を設定します。
     * @param dispOrder 表示順
     */
    public void setDispOrder(BigDecimal dispOrder) {
        this.dispOrder = dispOrder;
    }
    
    /**
     * 項目名称を取得します。
     * @return 項目名称
     */
    public String getKoumokuName() {
        return koumokuName;
    }
    /**
     * 項目名称を設定します。
     * @param koumokuName 項目名称
     */
    public void setKoumokuName(String koumokuName) {
        this.koumokuName = koumokuName;
    }
    
    /**
     * 項目名称（表示用）
     * 　文字位置を考慮した文字列を返します
     * @return
     */
    public String getKoumokuNameDisp() {
        String ret = getKoumokuName();
        if ("01".equals(getDispPosition())) {
        }
        else if ("02".equals(getDispPosition())) {
            ret = "　" + ret;
        }
        else if ("03".equals(getDispPosition())) {
            ret = "　　" + ret;
        }
        return ret;
    }
    /**
     * 項目名称テーブル表示位置
     * @return
     */
    public String getKoumokuNameTextAlign() {
        String align = "left";
        if ("04".equals(getDispPosition())) {
            align = "right";
        }
        return align;
    }
    /**
     * 項目属性を取得します。
     * @return 項目属性
     */
    public String getKoumokuZoku() {
        return koumokuZoku;
    }
    /**
     * 項目属性を設定します。
     * @param koumokuZoku 項目属性
     */
    public void setKoumokuZoku(String koumokuZoku) {
        this.koumokuZoku = koumokuZoku;
    }
    
    /**
     * 金額を取得します。
     * @return 金額
     */
    public BigDecimal getKingaku() {
        return kingaku;
    }
    /**
     * 金額を設定します。
     * @param kingaku 金額
     */
    public void setKingaku(BigDecimal kingaku) {
        this.kingaku = kingaku;
    }
    
    /**
     * 表示色を取得します。
     * @return 表示色
     */
    public String getDispColor() {
        return dispColor;
    }
    /**
     * 表示色を設定します。
     * @param dispColor 表示色
     */
    public void setDispColor(String dispColor) {
        this.dispColor = dispColor;
    }
    
    /**
     * 文字位置を取得します。
     * @return 文字位置
     */
    public String getDispPosition() {
        return dispPosition;
    }
    /**
     * 文字位置を設定します。
     * @param dispPosition 文字位置
     */
    public void setDispPosition(String dispPosition) {
        this.dispPosition = dispPosition;
    }
    
    /**
     * 構成比元区分を取得します。
     * @return 構成比元区分
     */
    public String getKouseihiMoto() {
        return kouseihiMoto;
    }
    /**
     * 構成比元区分を設定します。
     * @param kouseihiMoto 構成比元区分
     */
    public void setKouseihiMoto(String kouseihiMoto) {
        this.kouseihiMoto = kouseihiMoto;
    }
    
    /**
     * 更新日を取得します。
     * @return 更新日
     */
    public String getLastTmsp() {
        return lastTmsp;
    }
    /**
     * 更新日を設定します。
     * @param lastTmsp 更新日
     */
    public void setLastTmsp(String lastTmsp) {
        this.lastTmsp = lastTmsp;
    }
    
	public BigDecimal getKingakuZennen() {
		return kingakuZennen;
	}
	public void setKingakuZennen(BigDecimal kingakuZennen) {
		this.kingakuZennen = kingakuZennen;
	}
	public BigDecimal getKouseihiMotoKingaku() {
		return kouseihiMotoKingaku;
	}
	public void setKouseihiMotoKingaku(BigDecimal kouseihiMotoKingaku) {
		this.kouseihiMotoKingaku = kouseihiMotoKingaku;
	}
}
