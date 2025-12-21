package jp.co.isid.mos.bird.analysis.posdata.entity;

/**
 * 
 * 作成日:
 * @modifier xkinu 2013/03 zipファイルダウンロード対応
 *
 */
public class UIPosDataInfo {
    
    public static final String TABLE = "BT51POSD";
    
    public static final String creDt_COLUMN = "CRE_DT";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String dataKbn_COLUMN = "DATA_KBN";
    
    public static final String dataNo_COLUMN = "DATA_NO";
    
    public static final String data_COLUMN = "DATA";
    
    public static final String sibu_cd_COLUMN = "SIBU_CD";
    /**
     * 作成日
     */
    private String creDt;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * データ区分
     */
    private String dataKbn;
    
    /**
     * データＮＯ
     */
    private String dataNo;
    
    /**
     * データ
     */
    private String data;
    
    /**
     * 支部コード
     * @adder xkinu 2013/03 zipファイルダウンロード対応
     */
    private String sibuCd;
    
    /**
     * 作成日を取得します。
     * @return 作成日
     */
    public String getCreDt() {
        return creDt;
    }
    /**
     * 作成日を設定します。
     * @param creDt 作成日
     */
    public void setCreDt(String creDt) {
        this.creDt = creDt;
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
     * データ区分を取得します。
     * @return データ区分
     */
    public String getDataKbn() {
        return dataKbn;
    }
    /**
     * データ区分を設定します。
     * @param dataKbn データ区分
     */
    public void setDataKbn(String dataKbn) {
        this.dataKbn = dataKbn;
    }
    
    /**
     * データＮＯを取得します。
     * @return データＮＯ
     */
    public String getDataNo() {
        return dataNo;
    }
    /**
     * データＮＯを設定します。
     * @param dataNo データＮＯ
     */
    public void setDataNo(String dataNo) {
        this.dataNo = dataNo;
    }
    
    /**
     * データを取得します。
     * @return データ
     */
    public String getData() {
        return data;
    }
    /**
     * データを設定します。
     * @param data データ
     */
    public void setData(String data) {
        this.data = data;
    }
	/**
     * 支部コード
     * @adder xkinu 2013/03 zipファイルダウンロード対応
	 * @return クラス変数sibuCd を戻します。
	 */
	public String getSibuCd() {
		return sibuCd;
	}
	/**
     * 支部コード
     * @adder xkinu 2013/03 zipファイルダウンロード対応
	 * @param sibuCd を クラス変数sibuCdへ設定します。
	 */
	public void setSibuCd(String sibuCd) {
		this.sibuCd = sibuCd;
	}
    
}
