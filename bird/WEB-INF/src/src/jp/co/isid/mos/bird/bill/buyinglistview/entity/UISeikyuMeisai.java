package jp.co.isid.mos.bird.bill.buyinglistview.entity;

import java.math.BigDecimal;

public class UISeikyuMeisai {
    
    public static final String TABLE = "BT37URTR";
    
    public static final String urikakeCd_COLUMN = "URIKAKE_CD";
    
    public static final String urikakeName_COLUMN = "URIKAKE_NAME";
    
    public static final String urikakeYm_COLUMN = "URIKAKE_YM";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String seikyuId_COLUMN = "SEIKYU_ID";
    
    public static final String ukanriNo_COLUMN = "UKANRI_NO";
    
    public static final String denpyoNo_COLUMN = "DENPYO_NO";
    
    public static final String dataShu_COLUMN = "DATA_SHU";
    
    public static final String denpyoKbn_COLUMN = "DENPYO_KBN";
    
    public static final String shoCdJitu_COLUMN = "SHO_CD_JITU";
    
    public static final String shoNameKj_COLUMN = "SHO_NAME_KJ";
    
    public static final String nohinUnit_COLUMN = "NOHIN_UNIT";
    
    public static final String shoAmount_COLUMN = "SHO_AMOUNT";
    
    public static final String nohinSaki_COLUMN = "NOHIN_SAKI";
    
    public static final String nohinTanka_COLUMN = "NOHIN_TANKA";
    
    public static final String uriKin_COLUMN = "URI_KIN";
    
    public static final String mushouUri_COLUMN = "MUSHOU_URI";
    
    public static final String staxUri_COLUMN = "STAX_URI";
    
    public static final String taxCd_COLUMN = "TAX_CD";
    
    public static final String sotoTax_COLUMN = "SOTO_TAX";
    
    public static final String uchiTax_COLUMN = "UCHI_TAX";
    
    public static final String nohinDtJ_COLUMN = "NOHIN_DT_J";
    
    public static final String kenshuDt_COLUMN = "KENSHU_DT";
    
    public static final String tekiyou_COLUMN = "TEKIYOU";
    
    public static final String seikyuDt_COLUMN = "SEIKYU_DT";
    
    public static final String shoCdDai_COLUMN = "SHO_CD_DAI";
    
    public static final String seBnrName_COLUMN = "SE_BNR_NAME";
    
    public static final String nisuName_COLUMN = "NISU_NAME";
//begin add 2019/06/13 xou.zoubun 税率マーク追加
    public static final String mark_COLUMN = "MARK";
//end add 2019/06/13

    /**
     * 売掛先コード
     */
    private String urikakeCd;
    
    /**
     * 売掛先名称
     */
    private String urikakeName;
    
    /**
     * 売掛残高年月
     */
//    private String urikakeYm;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 請求ＩＤ
     */
//    private String seikyuId;
    
    /**
     * 売掛管理ＮＯ
     */
//    private String ukanriNo;
    
    /**
     * 伝票ＮＯ
     */
    private String denpyoNo;
    
    /**
     * データ種別
     */
//    private String dataShu;
    
    /**
     * 伝票区分
     */
//    private String denpyoKbn;
    
    /**
     * 実商品コード
     */
    private String shoCdJitu;
    
    /**
     * 実商品名称
     */
    private String shoNameKj;
    
    /**
     * 納品単位区分
     */
//    private String nohinUnit;
    
    /**
     * 納品数量
     */
    private BigDecimal shoAmount;
    
    /**
     * 納品先コード
     */
//    private String nohinSaki;
    
    /**
     * 納品単価
     */
    private BigDecimal nohinTanka;
    
    /**
     * 売上金額
     */
    private BigDecimal uriKin;
    
    /**
     * 無償区分（売上）
     */
//    private String mushouUri;
    
    /**
     * 消費税区分（売上）
     */
    private String staxUri;
    
    /**
     * 消費税率コード
     */
//    private String taxCd;
    
    /**
     * 外税消費税
     */
    private BigDecimal sotoTax;
    
    /**
     * 算出内税消費税
     */
    private BigDecimal uchiTax;
    
    /**
     * 納品実績日
     */
    private String nohinDtJ;
    
    /**
     * 検収確定日
     */
//    private String kenshuDt;
    
    /**
     * 摘要
     */
    private String tekiyou;
    
    /**
     * 請求日
     */
//    private String seikyuDt;
    
    /**
     * 代表商品コード
     */
    private String shoCdDai;
    
    /**
     * 請求分類名称
     */
    private String seBnrName;
    
    /**
     * 荷姿名称
     */
    private String nisuName;

//begin add 2019/06/13 xou.zoubun税率マークの追加
    /**
     * 軽減扱いマーク
     */
    private String mark;
//end add 2019/06/13
    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
    }
    /**
     * 売掛先コードを設定します。
     * @param urikakeCd 売掛先コード
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }
    
    /**
     * 売掛先名称を取得します。
     * @return 売掛先名称
     */
    public String getUrikakeName() {
        return urikakeName;
    }
    /**
     * 売掛先名称を設定します。
     * @param urikakeName 売掛先名称
     */
    public void setUrikakeName(String urikakeName) {
        this.urikakeName = urikakeName;
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
     * 伝票ＮＯを取得します。
     * @return 伝票ＮＯ
     */
    public String getDenpyoNo() {
        return denpyoNo;
    }
    /**
     * 伝票ＮＯを設定します。
     * @param denpyoNo 伝票ＮＯ
     */
    public void setDenpyoNo(String denpyoNo) {
        this.denpyoNo = denpyoNo;
    }
    
    /**
     * 実商品コードを取得します。
     * @return 実商品コード
     */
    public String getShoCdJitu() {
        return shoCdJitu;
    }
    /**
     * 実商品コードを設定します。
     * @param shoCdJitu 実商品コード
     */
    public void setShoCdJitu(String shoCdJitu) {
        this.shoCdJitu = shoCdJitu;
    }
    
    /**
     * 実商品名称を取得します。
     * @return 実商品名称
     */
    public String getShoNameKj() {
        return shoNameKj;
    }
    /**
     * 実商品名称を設定します。
     * @param shoNameKj 実商品名称
     */
    public void setShoNameKj(String shoNameKj) {
        this.shoNameKj = shoNameKj;
    }
    
    /**
     * 納品数量を取得します。
     * @return 納品数量
     */
    public BigDecimal getShoAmount() {
        return shoAmount;
    }
    /**
     * 納品数量を設定します。
     * @param shoAmount 納品数量
     */
    public void setShoAmount(BigDecimal shoAmount) {
        this.shoAmount = shoAmount;
    }
    
    /**
     * 納品単価を取得します。
     * @return 納品単価
     */
    public BigDecimal getNohinTanka() {
        return nohinTanka;
    }
    /**
     * 納品単価を設定します。
     * @param nohinTanka 納品単価
     */
    public void setNohinTanka(BigDecimal nohinTanka) {
        this.nohinTanka = nohinTanka;
    }
    
    /**
     * 売上金額を取得します。
     * @return 売上金額
     */
    public BigDecimal getUriKin() {
        return uriKin;
    }
    /**
     * 売上金額を設定します。
     * @param uriKin 売上金額
     */
    public void setUriKin(BigDecimal uriKin) {
        this.uriKin = uriKin;
    }
    
    /**
     * 消費税区分（売上）を取得します。
     * @return 消費税区分（売上）
     */
    public String getStaxUri() {
        return staxUri;
    }
    /**
     * 消費税区分（売上）を設定します。
     * @param staxUri 消費税区分（売上）
     */
    public void setStaxUri(String staxUri) {
        this.staxUri = staxUri;
    }
    
    /**
     * 外税消費税を取得します。
     * @return 外税消費税
     */
    public BigDecimal getSotoTax() {
        return sotoTax;
    }
    /**
     * 外税消費税を設定します。
     * @param sotoTax 外税消費税
     */
    public void setSotoTax(BigDecimal sotoTax) {
        this.sotoTax = sotoTax;
    }
    
    /**
     * 算出内税消費税を取得します。
     * @return 算出内税消費税
     */
    public BigDecimal getUchiTax() {
        return uchiTax;
    }
    /**
     * 算出内税消費税を設定します。
     * @param uchiTax 算出内税消費税
     */
    public void setUchiTax(BigDecimal uchiTax) {
        this.uchiTax = uchiTax;
    }
    
    /**
     * 納品実績日を取得します。
     * @return 納品実績日
     */
    public String getNohinDtJ() {
        return nohinDtJ;
    }
    /**
     * 納品実績日を設定します。
     * @param nohinDtJ 納品実績日
     */
    public void setNohinDtJ(String nohinDtJ) {
        this.nohinDtJ = nohinDtJ;
    }
    
    /**
     * 摘要を取得します。
     * @return 摘要
     */
    public String getTekiyou() {
        return tekiyou;
    }
    /**
     * 摘要を設定します。
     * @param tekiyou 摘要
     */
    public void setTekiyou(String tekiyou) {
        this.tekiyou = tekiyou;
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
     * 請求分類名称を取得します。
     * @return 請求分類名称
     */
    public String getSeBnrName() {
        return seBnrName;
    }
    /**
     * 請求分類名称を設定します。
     * @param seBnrName 請求分類名称
     */
    public void setSeBnrName(String seBnrName) {
        this.seBnrName = seBnrName;
    }
    
    /**
     * 荷姿名称を取得します。
     * @return 荷姿名称
     */
    public String getNisuName() {
        return nisuName;
    }
    /**
     * 荷姿名称を設定します。
     * @param nisuName 荷姿名称
     */
    public void setNisuName(String nisuName) {
        this.nisuName = nisuName;
    }

//begin add 2019/06/13 xou.zoubun 税率マークの追加
    /**
     * マークを取得します。
     * @return マーク
     */
    public String getMark() {
        return mark;
    }
    /**
     * マークを設定します。
     * @param mark マーク
     */
    public void setMark(String mark) {
        this.mark = mark;
    }
//end add 2019/06/13
}
