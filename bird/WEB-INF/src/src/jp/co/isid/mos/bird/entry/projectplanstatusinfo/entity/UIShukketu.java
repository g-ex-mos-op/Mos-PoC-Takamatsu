package jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity;

import java.math.BigDecimal;

public class UIShukketu {
    
    public static final String TABLE = "BT46KENS";
    
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
     * 未登録オーナー
     */
//    private BigDecimal onerNoregistCnt = new BigDecimal("0");
    
    /**
     * 出席人数
     */
    private BigDecimal attendCnt = new BigDecimal("0");
    
    /**
     * 出席人数
     */
    private BigDecimal absentCnt = new BigDecimal("0");
    
    /**
     * 出席オーナーを取得します。
     * @return 出席オーナー
     */
    public BigDecimal getOnerAttendCnt() {
        return onerAttendCnt;
    }
    /**
     * 出席オーナーを設定します。
     * @param onerAttendCnt 出席オーナー
     */
    public void setOnerAttendCnt(BigDecimal onerAttendCnt) {
        this.onerAttendCnt = onerAttendCnt;
    }
    
    /**
     * 欠席オーナーを取得します。
     * @return 欠席オーナー
     */
    public BigDecimal getOnerAbsentCnt() {
        return onerAbsentCnt;
    }
    /**
     * 欠席オーナーを設定します。
     * @param onerAbsentCnt 欠席オーナー
     */
    public void setOnerAbsentCnt(BigDecimal onerAbsentCnt) {
        this.onerAbsentCnt = onerAbsentCnt;
    }
    
    /**
     * 未登録オーナーを取得します。
     * @return 未登録オーナー
     */
    public BigDecimal getOnerNoregistCnt() {
        return getOnerTotalCnt().subtract(getOnerAttendCnt().add(getOnerAbsentCnt()));
    }
    /**
     * 未登録オーナーを設定します。
     * @param onerNoregistCnt 未登録オーナー
     */
    public void setOnerNoregistCnt(BigDecimal onerNoregistCnt) {
//        this.onerNoregistCnt = onerNoregistCnt;
    }
    
    /**
     * 出席人数を取得します。
     * @return 出席人数
     */
    public BigDecimal getAttendCnt() {
        return attendCnt;
    }
    /**
     * 出席人数を設定します。
     * @param attendCnt 出席人数
     */
    public void setAttendCnt(BigDecimal attendCnt) {
        this.attendCnt = attendCnt;
    }
    /**
     * @return onerTotalCnt を戻します。
     */
    public BigDecimal getOnerTotalCnt() {
        return onerTotalCnt;
    }
    /**
     * @param onerTotalCnt 設定する onerTotalCnt。
     */
    public void setOnerTotalCnt(BigDecimal onerTotalCnt) {
        this.onerTotalCnt = onerTotalCnt;
    }
    /**
     * @return absentCnt を戻します。
     */
    public BigDecimal getAbsentCnt() {
        return absentCnt;
    }
    /**
     * @param absentCnt 設定する absentCnt。
     */
    public void setAbsentCnt(BigDecimal absentCnt) {
        this.absentCnt = absentCnt;
    }
    
}
