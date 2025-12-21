package jp.co.isid.mos.bird.bizreport.common.camp.entity;

import java.math.BigDecimal;

/**
 * 売上用エンティティーインターフェース
 * 
 * @author xkinu
 *
 */
public interface Uriage {
    
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
        
    /**
     * TRタグスタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getTrCssClass();
    /**
     * 対象店舗数を取得します。
     * @return 企業コード
     */
    public BigDecimal getTaishoTenpoCnt();
    /**
     * 対象店舗数を設定します。
     * @param cnt 企業コード
     */
    public void setTaishoTenpoCnt(BigDecimal cnt);

    /**
     * 企業コードを取得します。
     * @return 企業コード
     */
    public String getCompanyCd();
    /**
     * 企業コードを設定します。
     * @param companyCd 企業コード
     */
    public void setCompanyCd(String companyCd);
    
    /**
     * 販売個数を取得します。
     * @return 販売個数
     */
    public BigDecimal getKazuKei();
    /**
     * 販売個数を設定します。
     * @param cnt 販売個数
     */
    public void setKazuKei(BigDecimal cnt);
    /**
     * 販売金額を取得します。
     * @return 販売金額
     */
    public BigDecimal getMenuUriage();
    /**
     * 販売金額を設定します。
     * @param uriage 販売金額
     */
    public void setMenuUriage(BigDecimal uriage);
    /**
     * 金額構成比を取得します。
     * @return 金額構成比
     */
    public BigDecimal getKingakuKoseiHi();
    /**
     * 金額構成比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getKingakuKoseiHiCssClass();
    /**
     * 売上高を取得します。
     * @return 売上高
     */
    public BigDecimal getUriage();
    /**
     * 売上高を設定します。
     * @param uriage 売上高
     */
    public void setUriage(BigDecimal uriage);
    
    /**
     * 前年売上高を取得します。
     * @return 前年売上高
     */
    public BigDecimal getUriageZen();
    /**
     * 前年売上高を設定します。
     * @param uriageZen 前年売上高
     */
    public void setUriageZen(BigDecimal uriageZen);
    
    /**
     * 売上前年比を取得します。
     * @return 売上前年比
     */
    public BigDecimal getUriageZennenHi();
    /**
     * 売上前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getUriageZennenHiCssClass();
    
    /**
     * 客数を取得します。
     * @return 客数
     */
    public BigDecimal getKyakusu();
    /**
     * 客数を設定します。
     * @param kyakusu 客数
     */
    public void setKyakusu(BigDecimal kyakusu);
    
    /**
     * 前年客数を取得します。
     * @return 前年客数
     */
    public BigDecimal getKyakusuZen();
    /**
     * 前年客数を設定します。
     * @param kyakusuZen 前年客数
     */
    public void setKyakusuZen(BigDecimal kyakusuZen);
    
    /**
     * 客数前年比を取得します。
     * @return 客数前年比
     */
    public BigDecimal getKyakusuZennenHi();
    /**
     * 客数前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getKyakusuZennenHiCssClass();
    
    /**
     * 客単価を取得します。
     * @return 客単価
     */
    public BigDecimal getKyakutanka();
    
    /**
     * 前年客単価を取得します。
     * @return 前年客単価
     */
    public BigDecimal getKyakutankaZen();
    /**
    /**
     * 客単価前年比を取得します。
     * @return 客単価前年比
     */
    public BigDecimal getKyakutankaZennenHi();
    /**
     * 客単価前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getKyakutankaZennenHiCssClass();
    /**
     * NET売上高を取得します。
     * @return NET売上高
     */
    public BigDecimal getNetUriage();
    /**
     * NET売上高を設定します。
     * @param netUriage NET売上高
     */
    public void setNetUriage(BigDecimal netUriage);
    
    /**
     * NET前年売上高を取得します。
     * @return NET前年売上高
     */
    public BigDecimal getNetUriageZen();
    /**
     * NET前年売上高を設定します。
     * @param netUriageZen NET前年売上高
     */
    public void setNetUriageZen(BigDecimal netUriageZen);
    
    /**
     * NET売上前年比を取得します。
     * @return NET売上前年比
     */
    public BigDecimal getNetUriageZennenHi();
    /**
     * NET売上前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getNetUriageZennenHiCssClass();
    
    /**
     * NET客数を取得します。
     * @return NET客数
     */
    public BigDecimal getNetKyakusu();
    /**
     * NET客数を設定します。
     * @param netKyakusu NET客数
     */
    public void setNetKyakusu(BigDecimal netKyakusu);
    
    /**
     * NET前年客数を取得します。
     * @return NET前年客数
     */
    public BigDecimal getNetKyakusuZen();
    /**
     * NET前年客数を設定します。
     * @param netKyakusuZen NET前年客数
     */
    public void setNetKyakusuZen(BigDecimal netKyakusuZen);
    
    /**
     * NET客数前年比を取得します。
     * @return NET客数前年比
     */
    public BigDecimal getNetKyakusuZennenHi();
    /**
     * NET客数前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getNetKyakusuZennenHiCssClass();
    /**
     * NET客単価を取得します。
     * @return NET客単価
     */
    public BigDecimal getNetKyakutanka();
    
    /**
     * NET前年客単価を取得します。
     * @return NET前年客単価
     */
    public BigDecimal getNetKyakutankaZen();
    
    /**
     * NET客単価前年比を取得します。
     * @return NET客単価前年比
     */
    public BigDecimal getNetKyakutankaZennenHi();
    /**
     * NET客単価前年比文字列スタイルシートクラスを取得します。
     * @return クラス名称
     */
    public String getNetKyakutankaZennenHiCssClass();
    
}
