package jp.co.isid.mos.bird.bizreport.takuhainiporef.entity;

import java.math.BigDecimal;

/**
 * 支部別宅配売上情報(画面表示・CSV用)エンティティクラス
 * 
 * @author xjung
 */
public class SibuDetail {
    
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
    private String SibuName;
    
    /**
     * 支部名称(画面用)
     */
    private String dispSibuCd;
    
    /**
     * 支部名称(画面用)
     */
    private String dispSibuName;
    
    /**
     * 支部名称表示フラグ
     */
    private boolean dispFlg;
    
    /**
     * 宅配コード
     */
    private String takuCd;
    
    /**
     * 宅配名称
     */
    private String takuName;
    
    /**
     * 宅配明細コード
     */
    private String takuDetailCd;
    
    /**
     * 宅配明細名称
     */
    private String takuDetailName;
    
    /**
     * 当年宅配実績店数
     */
    private int tenpoCount;
    
    /**
     * 前年比対象宅配実績店数
     */
    private int tenpoCountHose;
    
    /**
     * 売上
     */
    private BigDecimal uriage;
    
    /**
     * 前年売上
     */
    private BigDecimal uriageZen;
    
    /**
     * 前年比対象売上
     */
    private BigDecimal uriageHose;
    
    /**
     * 前年比対象前年売上
     */
    private BigDecimal uriageZenHose;
    
    /**
     * 前年比（売上）
     */
    private BigDecimal zenHiUriage;
    
    /**
     * 宅配売上
     */
    private BigDecimal uriageTaku;
    
    /**
     * 前年宅配売上
     */
    private BigDecimal uriageTakuZen;
    
    /**
     * 前年比対象宅配売上
     */
    private BigDecimal uriageTakuHose;
    
    /**
     * 前年比対象前年宅配売上
     */
    private BigDecimal uriageTakuZenHose;
    
    /**
     * 前年比（宅配）
     */
    private BigDecimal zenHiUriageTaku;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 前年客数
     */
    private BigDecimal kyakusuZen;
    
    /**
     * 前年比対象客数
     */
    private BigDecimal kyakusuHose;
    
    /**
     * 前年比対象前年客数
     */
    private BigDecimal kyakusuZenHose;
    
    /**
     * 前年比(客数)
     */
    private BigDecimal zenHiKyakusu;
    
    /**
     * 宅配件数
     */
    private BigDecimal kyakusuTaku;
    
    /**
     * 前年宅配客数
     */
    private BigDecimal kyakusuTakuZen;
    
    /**
     * 前年比対象宅配件数
     */
    private BigDecimal kyakusuTakuHose;
    
    /**
     * 前年比対象前年宅配件数
     */
    private BigDecimal kyakusuTakuZenHose;
    
    /**
     * 前年比（宅配）
     */
    private BigDecimal zenHiKyakusuTaku;
    
    /**
     * 構成比(売上)
     */
    private BigDecimal kouseiHiUri;
    
    /**
     * 構成比(客数)
     */
    private BigDecimal kouseiHiKyaku;
    
    /**
     * 客単価
     */
    private BigDecimal kyakuTanka;
    
    /**
     * 前年客単価
     */
    private BigDecimal zenKyakuTanka;
    
    /**
     * 前年比対象客単価
     */
    private BigDecimal kyakuTankaHose;
    
    /**
     * 前年比対象前年客単価
     */
    private BigDecimal kyakuTanKaZenHose;
    
    /**
     * 前年比（客単価）
     */
    private BigDecimal zenHiKyakuTanka;
    
    /**
     * 行Cｓｓクラス
     */
    private String rClass;
    
    /**
     * 前年比(売上)TdCssクラス
     */
    private String zenHiUriTdClass;
    
    /**
     * 前年比(宅配売上)TdCssクラス
     */
    private String zenHiUriTakuTdClass;
    
    /**
     * 構成比(売上)TdCssクラス
     */
    private String kouseiHiUriTdClass;
    
    /**
     * 前年比(件数)TdCssクラス
     */
    private String zenHiKyaTdClass;
    
    /**
     * 前年比(宅配件数)TdCssクラス
     */
    private String zenHiKyaTakuTdClass;
    
    /**
     * 構成比(件数)TdCssクラス
     */
    private String kouseiHiKyaTdClass;
    
    /**
     * 前年比(客単価)TDCSSクラス
     */
    private String zenKyakuTankaTdClass;
    
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
     * 事業本部コードを取得します。
     * @return 事業本部コード
     */
    public String getJigyouCd() {
        return jigyouCd;
    }
    /**
     * 事業本部コードを設定します。
     * @param jigyouCd 事業本部コード
     */
    public void setJigyouCd(String jigyouCd) {
        this.jigyouCd = jigyouCd;
    }
    
    /**
     * 事業本部名称を取得します。
     * @return 事業本部名称
     */
    public String getJigyouName() {
        return jigyouName;
    }
    /**
     * 事業本部名称を設定します。
     * @param jigyouName 事業本部名称
     */
    public void setJigyouName(String jigyouName) {
        this.jigyouName = jigyouName;
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
     * 支部名称を取得します。
     * @return 支部名称
     */
    public String getSibuName() {
        return SibuName;
    }
    /**
     * 支部名称を設定します。
     * @param SibuName 支部名称
     */
    public void setSibuName(String SibuName) {
        this.SibuName = SibuName;
    }
    
    /**
     * 支部名称(画面用)を取得します。
     * @return 支部名称(画面用)
     */
    public String getDispSibuCd() {
        return dispSibuCd;
    }
    /**
     * 支部名称(画面用)を設定します。
     * @param dispSibuCd 支部名称(画面用)
     */
    public void setDispSibuCd(String dispSibuCd) {
        this.dispSibuCd = dispSibuCd;
    }
    
    /**
     * 支部名称(画面用)を取得します。
     * @return 支部名称(画面用)
     */
    public String getDispSibuName() {
        return dispSibuName;
    }
    /**
     * 支部名称(画面用)を設定します。
     * @param dispSibuName 支部名称(画面用)
     */
    public void setDispSibuName(String dispSibuName) {
        this.dispSibuName = dispSibuName;
    }
    
    /**
     * 支部名称表示フラグを取得します。
     * @return 支部名称表示フラグ
     */
    public boolean getDispFlg() {
        return dispFlg;
    }
    /**
     * 支部名称表示フラグを設定します。
     * @param dispFlg 支部名称表示フラグ
     */
    public void setDispFlg(boolean dispFlg) {
        this.dispFlg = dispFlg;
    }
    
    /**
     * 宅配コードを取得します。
     * @return 宅配コード
     */
    public String getTakuCd() {
        return takuCd;
    }
    /**
     * 宅配コードを設定します。
     * @param takuCd 宅配コード
     */
    public void setTakuCd(String takuCd) {
        this.takuCd = takuCd;
    }
    
    /**
     * 宅配名称を取得します。
     * @return 宅配名称
     */
    public String getTakuName() {
        return takuName;
    }
    /**
     * 宅配名称を設定します。
     * @param takuName 宅配名称
     */
    public void setTakuName(String takuName) {
        this.takuName = takuName;
    }
    
    /**
     * 宅配明細コードを取得します。
     * @return 宅配明細コード
     */
    public String getTakuDetailCd() {
        return takuDetailCd;
    }
    /**
     * 宅配明細コードを設定します。
     * @param takuDetailCd 宅配明細コード
     */
    public void setTakuDetailCd(String takuDetailCd) {
        this.takuDetailCd = takuDetailCd;
    }
    
    /**
     * 宅配明細名称を取得します。
     * @return 宅配明細名称
     */
    public String getTakuDetailName() {
        return takuDetailName;
    }
    /**
     * 宅配明細名称を設定します。
     * @param takuDetailName 宅配明細名称
     */
    public void setTakuDetailName(String takuDetailName) {
        this.takuDetailName = takuDetailName;
    }
    
    /**
     * 当年宅配実績店数を取得します。
     * @return 当年宅配実績店数
     */
    public int getTenpoCount() {
        return tenpoCount;
    }
    /**
     * 当年宅配実績店数を設定します。
     * @param tenpoCount 当年宅配実績店数
     */
    public void setTenpoCount(int tenpoCount) {
        this.tenpoCount = tenpoCount;
    }
    
    /**
     * 前年比対象宅配実績店数を取得します。
     * @return 前年比対象宅配実績店数
     */
    public int getTenpoCountHose() {
        return tenpoCountHose;
    }
    /**
     * 前年比対象宅配実績店数を設定します。
     * @param tenpoCountHose 前年比対象宅配実績店数
     */
    public void setTenpoCountHose(int tenpoCountHose) {
        this.tenpoCountHose = tenpoCountHose;
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
     * 前年売上を取得します。
     * @return 前年売上
     */
    public BigDecimal getUriageZen() {
        return uriageZen;
    }
    /**
     * 前年売上を設定します。
     * @param uriageZen 前年売上
     */
    public void setUriageZen(BigDecimal uriageZen) {
        this.uriageZen = uriageZen;
    }
    
    /**
     * 前年比対象売上を取得します。
     * @return 前年比対象売上
     */
    public BigDecimal getUriageHose() {
        return uriageHose;
    }
    /**
     * 前年比対象売上を設定します。
     * @param uriageHose 前年比対象売上
     */
    public void setUriageHose(BigDecimal uriageHose) {
        this.uriageHose = uriageHose;
    }
    
    /**
     * 前年比対象前年売上を取得します。
     * @return 前年比対象前年売上
     */
    public BigDecimal getUriageZenHose() {
        return uriageZenHose;
    }
    /**
     * 前年比対象前年売上を設定します。
     * @param uriageZenHose 前年比対象前年売上
     */
    public void setUriageZenHose(BigDecimal uriageZenHose) {
        this.uriageZenHose = uriageZenHose;
    }
    
    /**
     * 前年比（売上）を取得します。
     * @return 前年比（売上）
     */
    public BigDecimal getZenHiUriage() {
        return zenHiUriage;
    }
    /**
     * 前年比（売上）を設定します。
     * @param zenHiUriage 前年比（売上）
     */
    public void setZenHiUriage(BigDecimal zenHiUriage) {
        this.zenHiUriage = zenHiUriage;
    }
    
    /**
     * 宅配売上を取得します。
     * @return 宅配売上
     */
    public BigDecimal getUriageTaku() {
        return uriageTaku;
    }
    /**
     * 宅配売上を設定します。
     * @param uriageTaku 宅配売上
     */
    public void setUriageTaku(BigDecimal uriageTaku) {
        this.uriageTaku = uriageTaku;
    }
    
    /**
     * 前年宅配売上を取得します。
     * @return 前年宅配売上
     */
    public BigDecimal getUriageTakuZen() {
        return uriageTakuZen;
    }
    /**
     * 前年宅配売上を設定します。
     * @param uriageTakuZen 前年宅配売上
     */
    public void setUriageTakuZen(BigDecimal uriageTakuZen) {
        this.uriageTakuZen = uriageTakuZen;
    }
    
    /**
     * 前年比対象宅配売上を取得します。
     * @return 前年比対象宅配売上
     */
    public BigDecimal getUriageTakuHose() {
        return uriageTakuHose;
    }
    /**
     * 前年比対象宅配売上を設定します。
     * @param uriageTakuHose 前年比対象宅配売上
     */
    public void setUriageTakuHose(BigDecimal uriageTakuHose) {
        this.uriageTakuHose = uriageTakuHose;
    }
    
    /**
     * 前年比対象前年宅配売上を取得します。
     * @return 前年比対象前年宅配売上
     */
    public BigDecimal getUriageTakuZenHose() {
        return uriageTakuZenHose;
    }
    /**
     * 前年比対象前年宅配売上を設定します。
     * @param uriageTakuZenHose 前年比対象前年宅配売上
     */
    public void setUriageTakuZenHose(BigDecimal uriageTakuZenHose) {
        this.uriageTakuZenHose = uriageTakuZenHose;
    }
    
    /**
     * 前年比（宅配）を取得します。
     * @return 前年比（宅配）
     */
    public BigDecimal getZenHiUriageTaku() {
        return zenHiUriageTaku;
    }
    /**
     * 前年比（宅配）を設定します。
     * @param zenHiUriageTaku 前年比（宅配）
     */
    public void setZenHiUriageTaku(BigDecimal zenHiUriageTaku) {
        this.zenHiUriageTaku = zenHiUriageTaku;
    }
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getKyakusu() {
        return kyakusu;
    }
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(BigDecimal kyakusu) {
        this.kyakusu = kyakusu;
    }
    
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getKyakusuZen() {
        return kyakusuZen;
    }
    /**
     * 前年客数を設定します。
     * @param kyakusuZen 前年客数
     */
    public void setKyakusuZen(BigDecimal kyakusuZen) {
        this.kyakusuZen = kyakusuZen;
    }
    
    /**
     * 前年比対象客数を取得します。
     * @return 前年比対象客数
     */
    public BigDecimal getKyakusuHose() {
        return kyakusuHose;
    }
    /**
     * 前年比対象客数を設定します。
     * @param kyakusuHose 前年比対象客数
     */
    public void setKyakusuHose(BigDecimal kyakusuHose) {
        this.kyakusuHose = kyakusuHose;
    }
    
    /**
     * 前年比対象前年客数を取得します。
     * @return 前年比対象前年客数
     */
    public BigDecimal getKyakusuZenHose() {
        return kyakusuZenHose;
    }
    /**
     * 前年比対象前年客数を設定します。
     * @param kyakusuZenHose 前年比対象前年客数
     */
    public void setKyakusuZenHose(BigDecimal kyakusuZenHose) {
        this.kyakusuZenHose = kyakusuZenHose;
    }
    
    /**
     * 前年比(客数)を取得します。
     * @return 前年比(客数)
     */
    public BigDecimal getZenHiKyakusu() {
        return zenHiKyakusu;
    }
    /**
     * 前年比(客数)を設定します。
     * @param zenHiKyakusu 前年比(客数)
     */
    public void setZenHiKyakusu(BigDecimal zenHiKyakusu) {
        this.zenHiKyakusu = zenHiKyakusu;
    }
    
    /**
     * 宅配件数を取得します。
     * @return 宅配件数
     */
    public BigDecimal getKyakusuTaku() {
        return kyakusuTaku;
    }
    /**
     * 宅配件数を設定します。
     * @param kyakusuTaku 宅配件数
     */
    public void setKyakusuTaku(BigDecimal kyakusuTaku) {
        this.kyakusuTaku = kyakusuTaku;
    }
    
    /**
     * 前年宅配客数を取得します。
     * @return 前年宅配客数
     */
    public BigDecimal getKyakusuTakuZen() {
        return kyakusuTakuZen;
    }
    /**
     * 前年宅配客数を設定します。
     * @param kyakusuTakuZen 前年宅配客数
     */
    public void setKyakusuTakuZen(BigDecimal kyakusuTakuZen) {
        this.kyakusuTakuZen = kyakusuTakuZen;
    }
    
    /**
     * 前年比対象宅配件数を取得します。
     * @return 前年比対象宅配件数
     */
    public BigDecimal getKyakusuTakuHose() {
        return kyakusuTakuHose;
    }
    /**
     * 前年比対象宅配件数を設定します。
     * @param kyakusuTakuHose 前年比対象宅配件数
     */
    public void setKyakusuTakuHose(BigDecimal kyakusuTakuHose) {
        this.kyakusuTakuHose = kyakusuTakuHose;
    }
    
    /**
     * 前年比対象前年宅配件数を取得します。
     * @return 前年比対象前年宅配件数
     */
    public BigDecimal getKyakusuTakuZenHose() {
        return kyakusuTakuZenHose;
    }
    /**
     * 前年比対象前年宅配件数を設定します。
     * @param kyakusuTakuZenHose 前年比対象前年宅配件数
     */
    public void setKyakusuTakuZenHose(BigDecimal kyakusuTakuZenHose) {
        this.kyakusuTakuZenHose = kyakusuTakuZenHose;
    }
    
    /**
     * 前年比（宅配）を取得します。
     * @return 前年比（宅配）
     */
    public BigDecimal getZenHiKyakusuTaku() {
        return zenHiKyakusuTaku;
    }
    /**
     * 前年比（宅配）を設定します。
     * @param zenHiKyakusuTaku 前年比（宅配）
     */
    public void setZenHiKyakusuTaku(BigDecimal zenHiKyakusuTaku) {
        this.zenHiKyakusuTaku = zenHiKyakusuTaku;
    }
    
    /**
     * 構成比(売上)を取得します。
     * @return 構成比(売上)
     */
    public BigDecimal getKouseiHiUri() {
        return kouseiHiUri;
    }
    /**
     * 構成比(売上)を設定します。
     * @param kouseiHiUri 構成比(売上)
     */
    public void setKouseiHiUri(BigDecimal kouseiHiUri) {
        this.kouseiHiUri = kouseiHiUri;
    }
    
    /**
     * 構成比(客数)を取得します。
     * @return 構成比(客数)
     */
    public BigDecimal getKouseiHiKyaku() {
        return kouseiHiKyaku;
    }
    /**
     * 構成比(客数)を設定します。
     * @param kouseiHiKyaku 構成比(客数)
     */
    public void setKouseiHiKyaku(BigDecimal kouseiHiKyaku) {
        this.kouseiHiKyaku = kouseiHiKyaku;
    }
    
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakuTanka() {
        return kyakuTanka;
    }
    /**
     * 客単価を設定します。
     * @param kyakuTanka 客単価
     */
    public void setKyakuTanka(BigDecimal kyakuTanka) {
        this.kyakuTanka = kyakuTanka;
    }
    
    /**
     * 前年客単価を取得します。
     * @return 前年客単価
     */
    public BigDecimal getZenKyakuTanka() {
        return zenKyakuTanka;
    }
    /**
     * 前年客単価を設定します。
     * @param zenKyakuTanka 前年客単価
     */
    public void setZenKyakuTanka(BigDecimal zenKyakuTanka) {
        this.zenKyakuTanka = zenKyakuTanka;
    }
    
    /**
     * 前年比対象客単価を取得します。
     * @return 前年比対象客単価
     */
    public BigDecimal getKyakuTankaHose() {
        return kyakuTankaHose;
    }
    /**
     * 前年比対象客単価を設定します。
     * @param kyakuTankaHose 前年比対象客単価
     */
    public void setKyakuTankaHose(BigDecimal kyakuTankaHose) {
        this.kyakuTankaHose = kyakuTankaHose;
    }
    
    /**
     * 前年比対象前年客単価を取得します。
     * @return 前年比対象前年客単価
     */
    public BigDecimal getKyakuTanKaZenHose() {
        return kyakuTanKaZenHose;
    }
    /**
     * 前年比対象前年客単価を設定します。
     * @param kyakuTanKaZenHose 前年比対象前年客単価
     */
    public void setKyakuTanKaZenHose(BigDecimal kyakuTanKaZenHose) {
        this.kyakuTanKaZenHose = kyakuTanKaZenHose;
    }
    
    /**
     * 前年比（客単価）を取得します。
     * @return 前年比（客単価）
     */
    public BigDecimal getZenHiKyakuTanka() {
        return zenHiKyakuTanka;
    }
    /**
     * 前年比（客単価）を設定します。
     * @param zenHiKyakuTanka 前年比（客単価）
     */
    public void setZenHiKyakuTanka(BigDecimal zenHiKyakuTanka) {
        this.zenHiKyakuTanka = zenHiKyakuTanka;
    }
    
    /**
     * 行Cｓｓクラスを取得します。
     * @return 行Cｓｓクラス
     */
    public String getRClass() {
        return rClass;
    }
    /**
     * 行Cｓｓクラスを設定します。
     * @param rClass 行Cｓｓクラス
     */
    public void setRClass(String rClass) {
        this.rClass = rClass;
    }
    
    /**
     * 前年比(売上)TdCssクラスを取得します。
     * @return 前年比(売上)TdCssクラス
     */
    public String getZenHiUriTdClass() {
        return zenHiUriTdClass;
    }
    /**
     * 前年比(売上)TdCssクラスを設定します。
     * @param zenHiUriTdClass 前年比(売上)TdCssクラス
     */
    public void setZenHiUriTdClass(String zenHiUriTdClass) {
        this.zenHiUriTdClass = zenHiUriTdClass;
    }
    
    /**
     * 前年比(宅配売上)TdCssクラスを取得します。
     * @return 前年比(宅配売上)TdCssクラス
     */
    public String getZenHiUriTakuTdClass() {
        return zenHiUriTakuTdClass;
    }
    /**
     * 前年比(宅配売上)TdCssクラスを設定します。
     * @param zenHiUriTakuTdClass 前年比(宅配売上)TdCssクラス
     */
    public void setZenHiUriTakuTdClass(String zenHiUriTakuTdClass) {
        this.zenHiUriTakuTdClass = zenHiUriTakuTdClass;
    }
    
    /**
     * 構成比(売上)TdCssクラスを取得します。
     * @return 構成比(売上)TdCssクラス
     */
    public String getKouseiHiUriTdClass() {
        return kouseiHiUriTdClass;
    }
    /**
     * 構成比(売上)TdCssクラスを設定します。
     * @param kouseiHiUriTdClass 構成比(売上)TdCssクラス
     */
    public void setKouseiHiUriTdClass(String kouseiHiUriTdClass) {
        this.kouseiHiUriTdClass = kouseiHiUriTdClass;
    }
    
    /**
     * 前年比(件数)TdCssクラスを取得します。
     * @return 前年比(件数)TdCssクラス
     */
    public String getZenHiKyaTdClass() {
        return zenHiKyaTdClass;
    }
    /**
     * 前年比(件数)TdCssクラスを設定します。
     * @param zenHiKyaTdClass 前年比(件数)TdCssクラス
     */
    public void setZenHiKyaTdClass(String zenHiKyaTdClass) {
        this.zenHiKyaTdClass = zenHiKyaTdClass;
    }
    
    /**
     * 前年比(宅配件数)TdCssクラスを取得します。
     * @return 前年比(宅配件数)TdCssクラス
     */
    public String getZenHiKyaTakuTdClass() {
        return zenHiKyaTakuTdClass;
    }
    /**
     * 前年比(宅配件数)TdCssクラスを設定します。
     * @param zenHiKyaTakuTdClass 前年比(宅配件数)TdCssクラス
     */
    public void setZenHiKyaTakuTdClass(String zenHiKyaTakuTdClass) {
        this.zenHiKyaTakuTdClass = zenHiKyaTakuTdClass;
    }
    
    /**
     * 構成比(件数)TdCssクラスを取得します。
     * @return 構成比(件数)TdCssクラス
     */
    public String getKouseiHiKyaTdClass() {
        return kouseiHiKyaTdClass;
    }
    /**
     * 構成比(件数)TdCssクラスを設定します。
     * @param kouseiHiKyaTdClass 構成比(件数)TdCssクラス
     */
    public void setKouseiHiKyaTdClass(String kouseiHiKyaTdClass) {
        this.kouseiHiKyaTdClass = kouseiHiKyaTdClass;
    }
    
    /**
     * 前年比(客単価)TDCSSクラスを取得します。
     * @return 前年比(客単価)TDCSSクラス
     */
    public String getZenKyakuTankaTdClass() {
        return zenKyakuTankaTdClass;
    }
    /**
     * 前年比(客単価)TDCSSクラスを設定します。
     * @param zenKyakuTankaTdClass 前年比(客単価)TDCSSクラス
     */
    public void setZenKyakuTankaTdClass(String zenKyakuTankaTdClass) {
        this.zenKyakuTankaTdClass = zenKyakuTankaTdClass;
    }
    
}
