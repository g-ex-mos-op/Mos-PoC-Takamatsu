package jp.co.isid.mos.bird.bizreport.moscardniporef.entity;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

public class TrnSibuKyakusuNipoRelate {
        
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
     * 本部コード
     */
    private String honbuCd;
    
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
     * 客数クラス
     */
    private String kyakusuClass;
    
    /**
     * 客数
     */
    private BigDecimal kyakusu;
    
    /**
     * 前年客数クラス
     */
    private String zenKyakusuClass;
    
    /**
     * 前年客数
     */
    private BigDecimal zenKyakusu;
    
    /**
     * 前年比クラス
     */
    private String zenHiClass;
    
    /**
     * 前年比
     */
    private BigDecimal zenHi;
    
    /**
     * 客単価クラス
     */
    private String kyakuTankaClass;
    
    /**
     * 客単価
     */
    private BigDecimal kyakuTanka;
    
    /**
     * 前年比(客単価)クラス
     */
    private String zenHiTankaClass;
    
    /**
     * 前年比(客単価)
     */
    private BigDecimal zenHiTanka;
    
    /**
     * 表示区分
     */
    private String dispKbn;
    
    /**
     * trのクラス
     */
    private String rClass;
    
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
     * 客数クラスを取得します。
     * @return 客数クラス
     */
    public String getKyakusuClass() {
        return kyakusuClass;
    }
    /**
     * 客数クラスを設定します。
     * @param kyakusuClass 客数クラス
     */
    public void setKyakusuClass(String kyakusuClass) {
        this.kyakusuClass = kyakusuClass;
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
     * 前年客数クラスを取得します。
     * @return 前年客数クラス
     */
    public String getZenKyakusuClass() {
        return zenKyakusuClass;
    }
    /**
     * 前年客数クラスを設定します。
     * @param zenKyakusuClass 前年客数クラス
     */
    public void setZenKyakusuClass(String zenKyakusuClass) {
        this.zenKyakusuClass = zenKyakusuClass;
    }
    
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getZenKyakusu() {
        return zenKyakusu;
    }
    /**
     * 前年客数を設定します。
     * @param zenKyakusu 前年客数
     */
    public void setZenKyakusu(BigDecimal zenKyakusu) {
        this.zenKyakusu = zenKyakusu;
    }
    
    /**
     * 前年比クラスを取得します。
     * @return 前年比クラス
     */
    public String getZenHiClass() {
        return zenHiClass;
    }
    /**
     * 前年比クラスを設定します。
     * @param zenHiClass 前年比クラス
     */
    public void setZenHiClass(String zenHiClass) {
        this.zenHiClass = zenHiClass;
    }
    
    /**
     * 前年比を取得します。
     * @return 前年比
     */
    public BigDecimal getZenHi() {
        return zenHi;
    }
    /**
     * 前年比を設定します。
     * @param zenHi 前年比
     */
    public void setZenHi(BigDecimal zenHi) {
        this.zenHi = zenHi;
    }
    
    /**
     * 客単価クラスを取得します。
     * @return 客単価クラス
     */
    public String getKyakuTankaClass() {
        return kyakuTankaClass;
    }
    /**
     * 客単価クラスを設定します。
     * @param kyakuTankaClass 客単価クラス
     */
    public void setKyakuTankaClass(String kyakuTankaClass) {
        this.kyakuTankaClass = kyakuTankaClass;
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
     * 前年比(客単価)クラスを取得します。
     * @return 前年比(客単価)クラス
     */
    public String getZenHiTankaClass() {
        return zenHiTankaClass;
    }
    /**
     * 前年比(客単価)クラスを設定します。
     * @param zenHiTankaClass 前年比(客単価)クラス
     */
    public void setZenHiTankaClass(String zenHiTankaClass) {
        this.zenHiTankaClass = zenHiTankaClass;
    }
    
    /**
     * 前年比(客単価)を取得します。
     * @return 前年比(客単価)
     */
    public BigDecimal getZenHiTanka() {
        return zenHiTanka;
    }
    /**
     * 前年比(客単価)を設定します。
     * @param zenHiTanka 前年比(客単価)
     */
    public void setZenHiTanka(BigDecimal zenHiTanka) {
        this.zenHiTanka = zenHiTanka;
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
    
}
