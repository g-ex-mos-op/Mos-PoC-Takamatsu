package jp.co.isid.mos.bird.entry.nationalviewlist.entity;

import java.math.BigDecimal;

public class UIAttendAbsentCntInfo {
    
    public static final String TABLE = "BT20ENON";
    
    public static final String onerTotalCnt_COLUMN = "ONER_TOTAL_CNT";
    
    public static final String onerAttendCnt_COLUMN = "ONER_ATTEND_CNT";
    
    public static final String onerAbsentCnt_COLUMN = "ONER_ABSENT_CNT";
    
    public static final String onerNoregistCnt_COLUMN = "ONER_NOREGIST_CNT";
    
    public static final String attendCnt_COLUMN = "ATTEND_CNT";
    
    public static final String absentCnt_COLUMN = "ABSENT_CNT";
    
    /**
     * 全オーナー数
     */
    private BigDecimal onerTotalCnt = new BigDecimal("0");
    
    /**
     * 出席オーナー
     */
    private BigDecimal onerAttendCnt = new BigDecimal("0");
    
    /**
     * 欠席オーナー
     */
    private BigDecimal onerAbsentCnt = new BigDecimal("0");
    
    /**
     * 出席人数
     */
    private BigDecimal attendCnt = new BigDecimal("0");
    
    /**
     * 欠席人数
     */
    private BigDecimal absentCnt = new BigDecimal("0");
    
    /**
     * 全オーナー数を取得します。
     * @return 全オーナー数
     */
    public java.math.BigDecimal getOnerTotalCnt() {
        return onerTotalCnt;
    }
    /**
     * 全オーナー数を設定します。
     * @param onerTotalCnt 全オーナー数
     */
    public void setOnerTotalCnt(java.math.BigDecimal onerTotalCnt) {
        this.onerTotalCnt = onerTotalCnt;
    }
    
    /**
     * 出席オーナーを取得します。
     * @return 出席オーナー
     */
    public java.math.BigDecimal getOnerAttendCnt() {
        return onerAttendCnt;
    }
    /**
     * 出席オーナーを設定します。
     * @param onerAttendCnt 出席オーナー
     */
    public void setOnerAttendCnt(java.math.BigDecimal onerAttendCnt) {
        this.onerAttendCnt = onerAttendCnt;
    }
    
    /**
     * 欠席オーナーを取得します。
     * @return 欠席オーナー
     */
    public java.math.BigDecimal getOnerAbsentCnt() {
        return onerAbsentCnt;
    }
    /**
     * 欠席オーナーを設定します。
     * @param onerAbsentCnt 欠席オーナー
     */
    public void setOnerAbsentCnt(java.math.BigDecimal onerAbsentCnt) {
        this.onerAbsentCnt = onerAbsentCnt;
    }
    
    /**
     * 未登録オーナーを取得します。
     * @return 未登録オーナー
     */
    public java.math.BigDecimal getOnerNoregistCnt() {
        return (getOnerTotalCnt().subtract(getOnerAttendCnt())).subtract(getOnerAbsentCnt());
    }
    
    /**
     * 出席人数を取得します。
     * @return 出席人数
     */
    public java.math.BigDecimal getAttendCnt() {
        return attendCnt;
    }
    /**
     * 出席人数を設定します。
     * @param attendCnt 出席人数
     */
    public void setAttendCnt(java.math.BigDecimal attendCnt) {
        this.attendCnt = attendCnt;
    }
    
    /**
     * 欠席人数を取得します。
     * @return 欠席人数
     */
    public java.math.BigDecimal getAbsentCnt() {
        return absentCnt;
    }
    /**
     * 欠席人数を設定します。
     * @param absentCnt 欠席人数
     */
    public void setAbsentCnt(java.math.BigDecimal absentCnt) {
        this.absentCnt = absentCnt;
    }
    
}
