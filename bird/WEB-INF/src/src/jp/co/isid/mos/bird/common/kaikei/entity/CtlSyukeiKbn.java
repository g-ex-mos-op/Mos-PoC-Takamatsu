package jp.co.isid.mos.bird.common.kaikei.entity;

/**
 * 会計集計区分のEntityクラス
 * @author xkawa
 *
 */
public class CtlSyukeiKbn {
    
    public static final String TABLE = "PM25KKBN";
    
    public static final String syukeiCd_COLUMN = "SYUKEI_CD";
    
    public static final String syukeiName_COLUMN = "SYUKEI_NAME";
    
    public static final String kaikeiKbnCnt_COLUMN = "KKBN_CNT";
    
    
    /**
     * 集計区分コード
     */
    private String syukeiCd;
    
    /**
     * 集計区分名称
     */
    private String syukeiName;

    /**
     * 会計区分数
     */
    private String kaikeiKbnCnt;

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
     * 会計区分数を戻します。
     * @return kaikeiKbnCnt 会計区分数
     */
    public String getKaikeiKbnCnt() {
        return kaikeiKbnCnt;
    }

    /**
     * 会計区分数を設定します。
     * @param kaikeiKbnCnt 会計区分数
     */
    public void setKaikeiKbnCnt(String kaikeiKbnCnt) {
        this.kaikeiKbnCnt = kaikeiKbnCnt;
    }
}
