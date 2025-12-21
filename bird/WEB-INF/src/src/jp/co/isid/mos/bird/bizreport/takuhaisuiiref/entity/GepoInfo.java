package jp.co.isid.mos.bird.bizreport.takuhaisuiiref.entity;

import java.math.BigDecimal;

public class GepoInfo {
    
    public static final String TABLE                     = "BT73TAKU";
    public static final String eigyoDt_COLUMN            = "EIGYO_DT";
    public static final String tenpoCnt_COLUMN           = "TENPO_CNT";
    public static final String takuTaishoTenpoCnt_COLUMN = "TAKU_TAISHO_TENPO_CNT";
    public static final String uriage_COLUMN             = "URIAGE";
    public static final String uriageZen_COLUMN          = "URIAGE_ZEN";
    public static final String uriageTaku_COLUMN         = "URIAGE_TAKU";
    public static final String uriageTakuZen_COLUMN      = "URIAGE_TAKU_ZEN";
    public static final String kyakusu_COLUMN            = "KYAKUSU";
    public static final String kyakusuZen_COLUMN         = "KYAKUSU_ZEN";
    public static final String kyakusuTaku_COLUMN        = "KYAKUSU_TAKU";
    public static final String kyakusuTakuZen_COLUMN     = "KYAKUSU_TAKU_ZEN";
    public static final String tenpoCntHosei_COLUMN         = "TENPO_CNT_HOSEI";
    public static final String takuTaishoTenpoCntHosei_COLUMN         = "TENPO_CNT_HOSEI";
    public static final String uriageHosei_COLUMN             = "URIAGE_HOSEI";
    public static final String uriageZenHosei_COLUMN          = "URIAGE_ZEN_HOSEI";
    public static final String uriageTakuHosei_COLUMN         = "URIAGE_TAKU_HOSEI";
    public static final String uriageTakuZenHosei_COLUMN      = "URIAGE_TAKU_ZEN_HOSEI";
    public static final String kyakusuHosei_COLUMN            = "KYAKUSU_HOSEI";
    public static final String kyakusuZenHosei_COLUMN         = "KYAKUSU_ZEN_HOSEI";
    public static final String kyakusuTakuHosei_COLUMN        = "KYAKUSU_TAKU_HOSEI";
    public static final String kyakusuTakuZenHosei_COLUMN     = "KYAKUSU_TAKU_ZEN_HOSEI";

    /** 行タイプ */
    private int rowType = 0;

    //--------------------------
    //月次(売上)タブ用
    //--------------------------
    /** 年月 */
    private String eigyoDt;
    /** 宅配対象店舗数 */
    private int takuTaishoTenpoCnt;
    /** 売上 */
    private BigDecimal uriage         = new BigDecimal(0);
    /** 前年売上 */
    private BigDecimal uriageZen      = new BigDecimal(0);
    /** 宅配売上 */
    private BigDecimal uriageTaku     = new BigDecimal(0);
    /** 前年宅配売上 */
    private BigDecimal uriageTakuZen  = new BigDecimal(0);
    /** 客数 */
    private BigDecimal kyakusu        = new BigDecimal(0);
    /** 前年客数 */
    private BigDecimal kyakusuZen     = new BigDecimal(0);
    /** 宅配件数 */
    private BigDecimal kyakusuTaku    = new BigDecimal(0);
    /** 前年宅配件数 */
    private BigDecimal kyakusuTakuZen = new BigDecimal(0);
    
    
    //--------------------------
    // 計算項目
    //--------------------------
    /** 前年比売上 */
    private BigDecimal zenhiUriage       = new BigDecimal(0);
    /** 前年比宅配売上 */
    private BigDecimal zenhiUriageTaku   = new BigDecimal(0);
    /** 構成比(売上) */
    private BigDecimal kouseihiUriage    = new BigDecimal(0);
    /** 宅配売上平均 */
    private BigDecimal takuUriageAvg     = new BigDecimal(0);
    /** 前年比客数 */
    private BigDecimal zenhiKyakusu      = new BigDecimal(0);
    /** 前年比宅配件数 */
    private BigDecimal zenhiKyakusuTaku  = new BigDecimal(0);
    /** 構成比(件数) */
    private BigDecimal kouseihiKyakusu   = new BigDecimal(0);
    /** 宅配件数平均 */
    private BigDecimal takuKensuAvg      = new BigDecimal(0);
    /** 客単価 */
    private BigDecimal kyakuTanka        = new BigDecimal(0);
    /** 前年客単価 */
    private BigDecimal kyakuTankaZen    = new BigDecimal(0);
    /** 前年比客単価 */
    private BigDecimal zenhiKyakuTanka   = new BigDecimal(0);
    /** 宅配客単価(補正) */
    private BigDecimal kyakuTankaTakuHosei     = new BigDecimal(0);
    /** 宅配前年客単価(補正) */
    private BigDecimal kyakuTankaTakuZenHosei  = new BigDecimal(0);


    //--------------------------
    // 表示スタイル
    //--------------------------
    /** クラス：前年比売上 */
    private String classZenHiUriage;
    /** クラス：前年比宅配売上 */
    private String classZenHiUriageTaku;
    /** クラス：前年比客数 */
    private String classZenHiKyaku;
    /** クラス：前年比宅配件数 */
    private String classZenHiKyakuTaku;
    /** クラス：前年比客単価 */
    private String classZenHiKyakuTanka;

    //CSV項目用
    /** 売上(補正) */
    private BigDecimal uriageHosei         = new BigDecimal(0);
    /** 前年売上(補正) */
    private BigDecimal uriageZenHosei      = new BigDecimal(0);
    /** 宅配売上(補正) */
    private BigDecimal uriageTakuHosei     = new BigDecimal(0);
    /** 前年宅配売上(補正) */
    private BigDecimal uriageTakuZenHosei  = new BigDecimal(0);
    /** 客数(補正) */
    private BigDecimal kyakusuHosei        = new BigDecimal(0);
    /** 前年客数(補正) */
    private BigDecimal kyakusuZenHosei     = new BigDecimal(0);
    /** 宅配件数(補正) */
    private BigDecimal kyakusuTakuHosei    = new BigDecimal(0);
    /** 前年宅配件数(補正) */
    private BigDecimal kyakusuTakuZenHosei = new BigDecimal(0);
    /** 客単価(補正) */
    private BigDecimal kyakuTankaHosei     = new BigDecimal(0);
    /** 前年客単価(補正) */
    private BigDecimal kyakuTankaZenHosei  = new BigDecimal(0);
    
    
    /////////////計算項目///////////////////////////////////////

      /**
       * 前年比売上を取得します。(売上/前年実績*100)
       * @return 前年比売上
       */
      public BigDecimal getZenhiUriage() {
          return zenhiUriage;
      }
      /**
       * 前年比売上を設定します。(売上/前年実績*100)
       * @return 前年比売上
       */
      public void setZenhiUriage(BigDecimal num) {
          this.zenhiUriage = num;
      }

      /**
       * 前年比宅配売上を取得します。(宅配売上/前年実績(宅配)*100)
       * @return 前年比宅配売上
       */
      public BigDecimal getZenhiUriageTaku() {
          return zenhiUriageTaku;
      }
      /**
       * 前年比宅配売上を設定します。(宅配売上/前年実績(宅配)*100)
       * @return 前年比宅配売上
       */
      public void setZenhiUriageTaku(BigDecimal num) {
          this.zenhiUriageTaku = num;
      }

      /**
       * 構成比(売上)を取得します。(宅配売上/売上*100)
       * @return 構成比(売上)
       */
      public BigDecimal getKouseihiUriage() {
          return kouseihiUriage;
      }
      /**
       * 構成比(売上)を設定します。(宅配売上/売上*100)
       * @return 構成比(売上)
       */
      public void setKouseihiUriage(BigDecimal num) {
          this.kouseihiUriage = num;
      }

      /**
       * 宅配売上平均を取得します。
       * @return 宅配売上平均
       */
      public BigDecimal getTakuUriageAvg() {
          return takuUriageAvg;
      }
      /**
       * 宅配売上平均を設定します。
       * @param 宅配売上平均
       */
      public void setTakuUriageAvg(BigDecimal num) {
          this.takuUriageAvg = num;
      }

      /**
       * 前年比客数を取得します。(客数/前年客数*100)
       * @return 前年比客数
       */
      public BigDecimal getZenhiKyakusu() {
          return zenhiKyakusu;
      }
      /**
       * 前年比客数を設定します。(客数/前年客数*100)
       * @return 前年比客数
       */
      public void setZenhiKyakusu(BigDecimal num) {
          this.zenhiKyakusu = num;
      }

      /**
       * 前年比宅配件数を取得します。(宅配件数/前年宅配件数*100)
       * @return 前年比宅配件数
       */
      public BigDecimal getZenhiKyakusuTaku() {
          return zenhiKyakusuTaku;
      }
      /**
       * 前年比宅配件数を設定します。(宅配件数/前年宅配件数*100)
       * @return 前年比宅配件数
       */
      public void setZenhiKyakusuTaku(BigDecimal num) {
          this.zenhiKyakusuTaku = num;
      }
      
      /**
       * 構成比(客数)を取得します。(宅配件数/客数*100)
       * @return 構成比(件数)
       */
      public BigDecimal getKouseihiKyakusu() {
          return kouseihiKyakusu;
      }
      /**
       * 構成比(客数)を設定します。(宅配件数/客数*100)
       * @return 構成比(件数)
       */
      public void setKouseihiKyakusu(BigDecimal num) {
          this.kouseihiKyakusu = num;
      }
      
      /**
       * 宅配件数平均を取得します。
       * @return 宅配件数平均
       */
      public BigDecimal getTakuKensuAvg() {
          return takuKensuAvg;
      }
      /**
       * 宅配件数平均を設定します。
       * @param 宅配件数平均
       */
      public void setTakuKensuAvg(BigDecimal num) {
          this.takuKensuAvg = num;
      }
      
      /**
       * 客単価を取得します。(宅配売上/宅配客数)
       * @return 客単価宅配
       */
      public BigDecimal getKyakuTanka() {
          return kyakuTanka;
      }
      /**
       * 客単価を設定します。(宅配売上/宅配客数)
       * @return 客単価宅配
       */
      public void setKyakuTanka(BigDecimal num) {
          this.kyakuTanka = num;
      }
      
      /**
       * 前年比客単価を取得します。(客単価/前年客単価)
       * @return 前年比客単価
       */
      public BigDecimal getZenhiKyakuTanka() {
          return zenhiKyakuTanka;
      }
      /**
       * 前年比客単価を設定します。(客単価/前年客単価)
       * @return 前年比客単価
       */
      public void setZenhiKyakuTanka(BigDecimal num) {
          this.zenhiKyakuTanka = num;
      }

      /////////////////以下・スタイルシート用クラス//////////////////////////////////

      /**
       * クラス(前年比売上)を取得します。
       * @return クラス(前年比売上)
       */
      public String getClassZenHiUriage() {
          return classZenHiUriage;
      }
      /**
       * クラス(前年比売上)をセットします。
       * @return クラス(前年比売上)
       */
      public void setClassZenHiUriage(String str) {
          this.classZenHiUriage = str;
      }
      
      /**
       * クラス(前年比宅配売上)を取得します。
       * @return クラス(前年比宅配売上)
       */
      public String getClassZenHiUriageTaku() {
          return classZenHiUriageTaku;
      }
      /**
       * クラス(前年比宅配売上)をセットします。
       * @return クラス(前年比宅配売上)
       */
      public void setClassZenHiUriageTaku(String str) {
          this.classZenHiUriageTaku = str;
      }
      
      /**
       * クラス(前年比客数)を取得します。
       * @return クラス(前年比客数)
       */
      public String getClassZenHiKyaku() {
          return classZenHiKyaku;
      }
      /**
       * クラス(前年比客数)をセットします。
       * @return クラス(前年比売上)
       */
      public void setClassZenHiKyaku(String str) {
          this.classZenHiKyaku = str;
      }
      
      /**
       * クラス(前年比宅配件数)を取得します。
       * @return クラス(前年比宅配件数)
       */
      public String getClassZenHiKyakuTaku() {
          return classZenHiKyakuTaku;
      }
      /**
       * クラス(前年比宅配件数)をセットします。
       * @return クラス(前年比宅配件数)
       */
      public void setClassZenHiKyakuTaku(String str) {
          this.classZenHiKyakuTaku = str;
      }

      /**
       * クラス(前年比客単価)を取得します。
       * @return クラス(前年比客単価)
       */
      public String getClassZenHiKyakuTanka() {
          return classZenHiKyakuTanka;
      }
      /**
       * クラス(前年比客単価)をセットします。
       * @return クラス(前年比客単価)
       */
      public void setClassZenHiKyakuTanka(String str) {
          this.classZenHiKyakuTanka = str;
      }


    ///////////////以下、セッター・ゲッター/////////////////////
    /**
     * 営業日を取得します。
     * @return 営業日
     */
    public String getEigyoDt() {
        return eigyoDt;
    }
    /**
     * 営業日を設定します。
     * @param eigyoDt 営業日
     */
    public void setEigyoDt(String eigyoDt) {
        this.eigyoDt = eigyoDt;
    }
    
    /**
     * 販売店舗数を取得します。
     * @return 販売店舗数
     */
    public int getTakuTaishoTenpoCnt() {
        return takuTaishoTenpoCnt;
    }
    /**
     * 販売店舗数を設定します。
     * @param hanTenpoCnt 販売店舗数
     */
    public void setTakuTaishoTenpoCnt(int hanTenpoCnt) {
        this.takuTaishoTenpoCnt = hanTenpoCnt;
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
     * 前年宅配件数を取得します。
     * @return 前年宅配件数
     */
    public BigDecimal getKyakusuTakuZen() {
        return kyakusuTakuZen;
    }
    /**
     * 前年宅配件数を設定します。
     * @param kyakusuTakuZen 前年宅配件数
     */
    public void setKyakusuTakuZen(BigDecimal kyakusuTakuZen) {
        this.kyakusuTakuZen = kyakusuTakuZen;
    }
    
    /**
     * 行タイプを取得します。
     * @return int 行タイプ
     */
    public int getRowType() {
        return rowType;
    }
    /**
     * 行タイプを設定します。
     * @param int 行タイプ
     */
    public void setRowType(int num) {
        this.rowType = num;
    }
    /**
     * @return kyakusuHosei を戻します。
     */
    public BigDecimal getKyakusuHosei() {
        return kyakusuHosei;
    }
    /**
     * @param kyakusuHosei 設定する kyakusuHosei。
     */
    public void setKyakusuHosei(BigDecimal kyakusuHosei) {
        this.kyakusuHosei = kyakusuHosei;
    }
    /**
     * @return kyakusuTakuHosei を戻します。
     */
    public BigDecimal getKyakusuTakuHosei() {
        return kyakusuTakuHosei;
    }
    /**
     * @param kyakusuTakuHosei 設定する kyakusuTakuHosei。
     */
    public void setKyakusuTakuHosei(BigDecimal kyakusuTakuHosei) {
        this.kyakusuTakuHosei = kyakusuTakuHosei;
    }
    /**
     * @return kyakusuTakuZenHosei を戻します。
     */
    public BigDecimal getKyakusuTakuZenHosei() {
        return kyakusuTakuZenHosei;
    }
    /**
     * @param kyakusuTakuZenHosei 設定する kyakusuTakuZenHosei。
     */
    public void setKyakusuTakuZenHosei(BigDecimal kyakusuTakuZenHosei) {
        this.kyakusuTakuZenHosei = kyakusuTakuZenHosei;
    }
    /**
     * @return kyakusuZenHosei を戻します。
     */
    public BigDecimal getKyakusuZenHosei() {
        return kyakusuZenHosei;
    }
    /**
     * @param kyakusuZenHosei 設定する kyakusuZenHosei。
     */
    public void setKyakusuZenHosei(BigDecimal kyakusuZenHosei) {
        this.kyakusuZenHosei = kyakusuZenHosei;
    }
    /**
     * @return kyakuTankaHosei を戻します。
     */
    public BigDecimal getKyakuTankaHosei() {
        return kyakuTankaHosei;
    }
    /**
     * @param kyakuTankaHosei 設定する kyakuTankaHosei。
     */
    public void setKyakuTankaHosei(BigDecimal kyakuTankaHosei) {
        this.kyakuTankaHosei = kyakuTankaHosei;
    }
    /**
     * @return kyakuTankaZenHosei を戻します。
     */
    public BigDecimal getKyakuTankaZenHosei() {
        return kyakuTankaZenHosei;
    }
    /**
     * @param kyakuTankaZenHosei 設定する kyakuTankaZenHosei。
     */
    public void setKyakuTankaZenHosei(BigDecimal kyakuTankaZenHosei) {
        this.kyakuTankaZenHosei = kyakuTankaZenHosei;
    }
    /**
     * @return uriageHosei を戻します。
     */
    public BigDecimal getUriageHosei() {
        return uriageHosei;
    }
    /**
     * @param uriageHosei 設定する uriageHosei。
     */
    public void setUriageHosei(BigDecimal uriageHosei) {
        this.uriageHosei = uriageHosei;
    }
    /**
     * @return uriageTakuHosei を戻します。
     */
    public BigDecimal getUriageTakuHosei() {
        return uriageTakuHosei;
    }
    /**
     * @param uriageTakuHosei 設定する uriageTakuHosei。
     */
    public void setUriageTakuHosei(BigDecimal uriageTakuHosei) {
        this.uriageTakuHosei = uriageTakuHosei;
    }
    /**
     * @return uriageTakuZenHosei を戻します。
     */
    public BigDecimal getUriageTakuZenHosei() {
        return uriageTakuZenHosei;
    }
    /**
     * @param uriageTakuZenHosei 設定する uriageTakuZenHosei。
     */
    public void setUriageTakuZenHosei(BigDecimal uriageTakuZenHosei) {
        this.uriageTakuZenHosei = uriageTakuZenHosei;
    }
    /**
     * @return uriageZenHosei を戻します。
     */
    public BigDecimal getUriageZenHosei() {
        return uriageZenHosei;
    }
    /**
     * @param uriageZenHosei 設定する uriageZenHosei。
     */
    public void setUriageZenHosei(BigDecimal uriageZenHosei) {
        this.uriageZenHosei = uriageZenHosei;
    }
    /**
     * @return kyakuTankaZen を戻します。
     */
    public BigDecimal getKyakuTankaZen() {
        return kyakuTankaZen;
    }
    /**
     * @param kyakuTankaZen 設定する kyakuTankaZen。
     */
    public void setKyakuTankaZen(BigDecimal kyakuTankaZen) {
        this.kyakuTankaZen = kyakuTankaZen;
    }
	/**
	 * @return kyakuTankaTakuHosei を戻します。
	 */
	public BigDecimal getKyakuTankaTakuHosei() {
		return kyakuTankaTakuHosei;
	}
	/**
	 * @param kyakuTankaTakuHosei 設定する kyakuTankaTakuHosei。
	 */
	public void setKyakuTankaTakuHosei(BigDecimal kyakuTankaTakuHosei) {
		this.kyakuTankaTakuHosei = kyakuTankaTakuHosei;
	}
	/**
	 * @return kyakuTankaTakuZenHosei を戻します。
	 */
	public BigDecimal getKyakuTankaTakuZenHosei() {
		return kyakuTankaTakuZenHosei;
	}
	/**
	 * @param kyakuTankaTakuZenHosei 設定する kyakuTankaTakuZenHosei。
	 */
	public void setKyakuTankaTakuZenHosei(BigDecimal kyakuTankaTakuZenHosei) {
		this.kyakuTankaTakuZenHosei = kyakuTankaTakuZenHosei;
	}


}
