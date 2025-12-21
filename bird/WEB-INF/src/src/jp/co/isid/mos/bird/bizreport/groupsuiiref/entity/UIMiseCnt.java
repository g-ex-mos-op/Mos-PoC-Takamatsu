package jp.co.isid.mos.bird.bizreport.groupsuiiref.entity;


/**
 * 対象店舗件数情報
 * 
 * @author xkinu
 *
 */
public class UIMiseCnt {
    
    public static final String TABLE = "BM01TENM";
    
    public static final String miseCnt_COLUMN      = "MISE_CNT";
    public static final String miseCd_COLUMN       = "MISE_CD";
    public static final String miseNameKj_COLUMN   = "MISE_NAME_KJ";
    public static final String miseOpenFlg_COLUMN  = "MISE_OPEN_FLG";
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 売上
     */
    private Integer miseCnt = new Integer(0);

    /** 店舗オープンフラグ */
    private String miseOpenFlg;

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
    public void setMiseNameKj(String name) {
        this.miseNameKj = name;
    }
	/**
	 * @return クラス変数miseCnt を戻します。
	 */
	public Integer getMiseCnt() {
		return miseCnt;
	}
	/**
	 * @param miseCnt を クラス変数miseCntへ設定します。
	 */
	public void setMiseCnt(Integer miseCnt) {
		this.miseCnt = miseCnt;
	}
	/**
	 * @return クラス変数miseOpenFlg を戻します。
	 */
	public String getMiseOpenFlg() {
		return miseOpenFlg;
	}
	/**
	 * @param miseOpenFlg を クラス変数miseOpenFlgへ設定します。
	 */
	public void setMiseOpenFlg(String miseOpenFlg) {
		this.miseOpenFlg = miseOpenFlg;
	}
    /**
     * 表示対象名称(店舗の場合、CLOSE判定文字列付)を取得します。
     * @return CLOSE文字列付の店舗名称
     */
    public String getHyojitaishoNameAddClose() {
        
        if("0".equals(getMiseOpenFlg()) ){
            //クローズしている場合
            return miseNameKj + " (CLOSE)";
	    } else {
	        return miseNameKj;
	    }
    }
    
}
