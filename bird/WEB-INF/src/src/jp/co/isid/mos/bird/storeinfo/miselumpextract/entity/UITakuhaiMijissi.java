package jp.co.isid.mos.bird.storeinfo.miselumpextract.entity;

/**
 * 
 * @author 
 * 更新日:2012/11/08 宅配区分の名称をベダからDB(BM47TDMS)取得に変更対応。
 *
 */
public class UITakuhaiMijissi {
    
    public static final String TABLE = "BT73TAKU";
    
    public static final String companyCd_COLUMN = "COMPANY_CD";
    
    public static final String miseCd_COLUMN = "MISE_CD";
    
    public static final String miseNameKj_COLUMN = "MISE_NAME_KJ";
    
    public static final String takuhaiKbn_COLUMN = "TAKUHAI_KBN";
    
    public static final String takuhaiKbnName_COLUMN = "TAKUHAI_KBN_NAME";
    
    public static final String eventStartDt_COLUMN = "EVENT_START_DT";
    
    public static final String eventEndDt_COLUMN = "EVENT_END_DT";
    
    public static final String henkoDt_COLUMN = "HENKO_DT";
    
    /**
     * 会社コード
     */
    private String companyCd;
    
    /**
     * 店コード
     */
    private String miseCd;
    
    /**
     * 店名称
     */
    private String miseNameKj;
    
    /**
     * 宅配区分
     */
    private String takuhaiKbn;
    /**
     * 宅配区分名称(BM47TDMS)
     * 追加日:2012/11/08
     */
    private String takuhaiKbnName;
    /**
     * 開始日
     */
    private String eventStartDt;
    
    /**
     * 終了日
     */
    private String eventEndDt;
    
    /**
     * 変更日
     */
    private String henkoDt;
    
    /**
     * 会社コードを取得します。
     * @return 会社コード
     */
    public String getCompanyCd() {
        return companyCd;
    }
    /**
     * 会社コードを設定します。
     * @param companyCd 会社コード
     */
    public void setCompanyCd(String companyCd) {
        this.companyCd = companyCd;
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
     * 宅配区分を取得します。
     * @return 宅配区分
     */
    public String getTakuhaiKbn() {
        return takuhaiKbn;
    }
    /**
     * 宅配区分を設定します。
     * @param takuhaiKbn 宅配区分
     */
    public void setTakuhaiKbn(String takuhaiKbn) {
        this.takuhaiKbn = takuhaiKbn;
    }
    /**
     * 宅配区分名称を取得します。
     * @return 宅配区分名称
     * 更新日:2012/11/08 宅配区分の名称をベダからDB(BM47TDMS)取得に変更対応。
     */
    public String getTakuhaiKbnName() {
        return this.takuhaiKbnName;
    }
    
    /**
     * 開始日を取得します。
     * @return 開始日
     */
    public String getEventStartDt() {
        return eventStartDt;
    }
    /**
     * 開始日を設定します。
     * @param eventStartDt 開始日
     */
    public void setEventStartDt(String eventStartDt) {
        this.eventStartDt = eventStartDt;
    }
    
    /**
     * 終了日を取得します。
     * @return 終了日
     */
    public String getEventEndDt() {
        return eventEndDt;
    }
    /**
     * 終了日を設定します。
     * @param eventEndDt 終了日
     */
    public void setEventEndDt(String eventEndDt) {
        this.eventEndDt = eventEndDt;
    }
    
    /**
     * 変更日を取得します。
     * @return 変更日
     */
    public String getHenkoDt() {
        return henkoDt;
    }
    /**
     * 変更日を設定します。
     * @param henkoDt 変更日
     */
    public void setHenkoDt(String henkoDt) {
        this.henkoDt = henkoDt;
    }
	/**
	 * @param takuhaiKbnName を クラス変数takuhaiKbnNameへ設定します。
	 */
	public void setTakuhaiKbnName(String takuhaiKbnName) {
		this.takuhaiKbnName = takuhaiKbnName;
	}
    
}
