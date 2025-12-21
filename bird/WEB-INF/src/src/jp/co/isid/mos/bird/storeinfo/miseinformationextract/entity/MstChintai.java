package jp.co.isid.mos.bird.storeinfo.miseinformationextract.entity;

public class MstChintai {
	/** 店コード */
	private String miseCd;
	/** 賃貸店舗種別 */
	private String miseLeaseShu;
	/** 賃貸店舗開始日 */
	private String miseLeaseStart;
	/** 賃貸店舗終了日 */
	private String miseLeaseEnd;

    /**
     * 店コードを取得します。
     * @return 店コード
     */
    public String getMiseCd() {
        return convString(miseCd);
    }

    /**
     * 店コードを設定します。
     * @param miseCd 店コード
     */
    public void setMiseCd(String miseCd) {
        this.miseCd = miseCd;
    }

    /**
     * 賃貸店舗種別を取得します。
     * @return 賃貸店舗種別
     */
    public String getMiseLeaseShu() {
        return convString(miseLeaseShu);
    }

    /**
     * 賃貸店舗種別を設定します。
     * @param miseLeaseShu 賃貸店舗種別
     */
    public void setMiseLeaseShu(String miseLeaseShu) {
        this.miseLeaseShu = miseLeaseShu;
    }

    /**
     * 賃貸店舗開始日を取得します。
     * @return 賃貸店舗開始日
     */
    public String getMiseLeaseStart() {
        return convString(miseLeaseStart);
    }

    /**
     * 賃貸店舗開始日を設定します。
     * @param miseLeaseStart 賃貸店舗開始日
     */
    public void setMiseLeaseStart(String miseLeaseStart) {
        this.miseLeaseStart = miseLeaseStart;
    }

    /**
     * 賃貸店舗終了日を取得します。
     * @return 賃貸店舗終了日
     */
    public String getMiseLeaseEnd() {
        return convString(miseLeaseEnd);
    }

    /**
     * 賃貸店舗終了日を設定します。
     * @param miseLeaseEnd 賃貸店舗終了日
     */
    public void setMiseLeaseEnd(String miseLeaseEnd) {
        this.miseLeaseEnd = miseLeaseEnd;
    }

    private String convString(String str) {
        String ret = str;
        if (str == null) {
            ret = "";
        }
        ret = ret.trim();
        return ret;
    }
}
