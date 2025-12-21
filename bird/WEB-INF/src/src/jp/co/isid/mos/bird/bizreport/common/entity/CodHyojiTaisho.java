package jp.co.isid.mos.bird.bizreport.common.entity;

public class CodHyojiTaisho {
    
    public static final String TABLE = "BM10GSIB";
    
    public static final String hyojitaishoCd_COLUMN   = "HYOJITAISHO_CD";
    
    public static final String hyojitaishoName_COLUMN = "HYOJITAISHO_NAME";

    public static final String miseOpenFlg_COLUMN     = "MISE_OPEN_FLG";
    
    /** 店舗オープンフラグ */
    private String miseOpenFlg;

    /** 表示対象コード */
    private String hyojitaishoCd;
    
    /** 表示対象名称 */
    private String hyojitaishoName;

   

    /**
     * 表示対象コードを取得します。
     * @return 表示対象コード
     */
    public String getHyojitaishoCd() {
        return hyojitaishoCd;
    }
    /**
     * 表示対象コードを設定します。
     * @param hyojitaishoCd 表示対象コード
     */
    public void setHyojitaishoCd(String hyojitaishoCd) {
        this.hyojitaishoCd = hyojitaishoCd;
    }
    
    /**
     * 表示対象名称を取得します。
     * @return 表示対象名称
     */
    public String getHyojitaishoName() {
        return hyojitaishoName;
    }
    /**
     * 表示対象名称を設定します。
     * @param hyojitaishoName 表示対象名称
     */
    public void setHyojitaishoName(String hyojitaishoName) {
        this.hyojitaishoName = hyojitaishoName;
    }


    /**
     * 表示対象名称(店舗の場合、CLOSE判定文字列付)を取得します。
     * @return CLOSE文字列付の店舗名称
     */
    public String getHyojitaishoNameAddClose() {
        
        if(getMiseOpenFlg().equals("1") ){
            return hyojitaishoName;
        } else {
            //クローズしている場合
            return hyojitaishoName + " (CLOSE)";
        }
    }

    /**
     * 店舗オープンフラグを取得します。
     * @return miseOpenFlg
     */
    public String getMiseOpenFlg() {
        return miseOpenFlg;
    }
    /**
     * 店舗オープンフラグを設定します。
     * @param flg(1:オープン、0:クローズ)
     */
    public void setMiseOpenFlg(String miseOpenFlg) {
        this.miseOpenFlg = miseOpenFlg;
    }

}
