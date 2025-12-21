package jp.co.isid.mos.bird.common.kaikei.entity;

/**
 * 会計集計区分のEntityクラス
 * @author xkawa
 *
 */
public class CtlKaikeiKbnInfo {
    
    public static final String TABLE = "PM25KKBN";
    
    public static final String kkbnCd_COLUMN = "KKBN_CD";
    
    public static final String mstDt_COLUMN = "MST_DT";
    
    public static final String kkbnName_COLUMN = "KKBN_NAME";
    
    public static final String kkbnZok_COLUMN = "KKBN_ZOK";
    
    public static final String kkbnTanka_COLUMN = "KKBN_TANKA";
    
    public static final String dispFlg_COLUMN = "DISP_FLG";
    
    public static final String syukeiCd_COLUMN = "SYUKEI_CD";
    
    public static final String syukeiName_COLUMN = "SYUKEI_NAME";
    
    public static final String couponFlg_COLUMN = "COUPON_FLG";
    
    public static final String couponId_COLUMN = "COUPON_ID";
    
    public static final String ticketCd_COLUMN = "TICKET_CD";
    

    /**
     * 会計区分コード
     */
    private String kkbnCd;
    
    /**
     * マスタ更新日
     */
    private String mstDt;
    
    /**
     * 会計区分名称
     */
    private String kkbnName;
    
    /**
     * 会計区分属性
     */
    private String kkbnZok;
    
    /**
     * 会計区分単価
     */
    private String kkbnTanka;
    
    /**
     * 表示フラグ
     */
    private String dispFlg;
    
    /**
     * 集計区分コード
     */
    private String syukeiCd;
    
    /**
     * 集計区分名称
     */
    private String syukeiName;
    
    /**
     * クーポンフラグ
     */
    private String couponFlg;
    
    /**
     * クーポンID
     */
    private String couponId;
    
    /**
     * チケットコード
     */
    private String ticketCd;

// setter/getter

    /**
     * 会計区分コードを戻します。
     * @return kkbnCd 会計区分コード
     */
    public String getKkbnCd() {
        return kkbnCd;
    }

    /**
     * 会計区分コードを設定します。
     * @param kkbnCd 会計区分コード
     */
    public void setKkbnCd(String kkbnCd) {
        this.kkbnCd = kkbnCd;
    }

    /**
     * マスタ更新日を戻します。
     * @return mstDt マスタ更新日
     */
    public String getMstDt() {
        return mstDt;
    }

    /**
     * マスタ更新日を設定します。
     * @param mstDt マスタ更新日
     */
    public void setMstDt(String mstDt) {
        this.mstDt = mstDt;
    }

    /**
     * 会計区分名称を戻します。
     * @return kkbnName 会計区分名称
     */
    public String getKkbnName() {
        return kkbnName;
    }

    /**
     * 会計区分名称を設定します。
     * @param kkbnName 会計区分名称
     */
    public void setKkbnName(String kkbnName) {
        this.kkbnName = kkbnName;
    }

    /**
     * 会計区分属性を戻します。
     * @return kkbnZok 会計区分属性
     */
    public String getKkbnZok() {
        return kkbnZok;
    }

    /**
     * 会計区分属性を設定します。
     * @param kkbnZok 会計区分属性
     */
    public void setKkbnZok(String kkbnZok) {
        this.kkbnZok = kkbnZok;
    }

    /**
     * 会計区分単価を戻します。
     * @return kkbnTanka 会計区分単価
     */
    public String getKkbnTanka() {
        return kkbnTanka;
    }

    /**
     * 会計区分単価を設定します。
     * @param kkbnTanka 会計区分単価
     */
    public void setKkbnTanka(String kkbnTanka) {
        this.kkbnTanka = kkbnTanka;
    }

    /**
     * 表示フラグを戻します。
     * @return dispFlg 表示フラグ
     */
    public String getDispFlg() {
        return dispFlg;
    }

    /**
     * 表示フラグを設定します。
     * @param dispFlg 表示フラグ
     */
    public void setDispFlg(String dispFlg) {
        this.dispFlg = dispFlg;
    }
    
    /**
     * 集計区分コードを戻します。
     * @return syukeiCd 集計区分コード
     */
    public String getSyukeiCd() {
        return syukeiCd;
    }

    /**
     * 集計区分コードを設定します。
     * @param syukeiCd 集計区分コード
     */
    public void setSyukeiCd(String syukeiCd) {
        this.syukeiCd = syukeiCd;
    }

    /**
     * 集計区分名称を戻します。
     * @return syukeiName 集計区分名称
     */
    public String getSyukeiName() {
        return syukeiName;
    }

    /**
     * 集計区分名称を設定します。
     * @param syukeiName 集計区分名称
     */
    public void setSyukeiName(String syukeiName) {
        this.syukeiName = syukeiName;
    }

    /**
     * クーポンフラグを戻します。
     * @return couponFlg クーポンフラグ
     */
    public String getCouponFlg() {
        return couponFlg;
    }

    /**
     * クーポンフラグを設定します。
     * @param couponFlg クーポンフラグ
     */
    public void setCouponFlg(String couponFlg) {
        this.couponFlg = couponFlg;
    }

    /**
     * クーポンIDを戻します。
     * @return couponId クーポンID
     */
    public String getCouponId() {
        return couponId;
    }

    /**
     * クーポンIDを設定します。
     * @param couponId クーポンID
     */
    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    /**
     * チケットコードを戻します。
     * @return ticketCd チケットコード
     */
    public String getTicketCd() {
        return ticketCd;
    }

    /**
     * チケットコードを設定します。
     * @param ticketCd チケットコード
     */
    public void setTicketCd(String ticketCd) {
        this.ticketCd = ticketCd;
    }
    
}
