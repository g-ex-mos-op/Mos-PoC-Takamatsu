package jp.co.isid.mos.bird.entry.common.code;

/**
 * エントリーコード定数クラス
 * 
 * @author xkinu
 */
public class EntryCode {

	/** エントリーコード：ベーシック研修(01) */
    public static final String BASIC = "01";

    /** エントリーコード：出張特別研修(05) */
    public static final String TRIP = "05";

    /** エントリーコード：マスターライセンス(10) */
    public static final String MASTERLICENSE = "10";

    /** エントリーコード：全国大会(15) */
    public static final String NATIONAL = "15";

    /** エントリーコード：永年勤続(20) */
    public static final String LONGTIME = "20";

    /** エントリーコード：事業方針説明会(25) */
    public static final String PROJECTPLAN = "25";

    /** エントリーコード：更新研修(30) */
    public static final String EXTENDTRAINING = "30";

    /** エントリーコード：全国店長勉強会(35) */
    public static final String MANAGERSTUDY = "35";

    /** エントリーコード：e-learning(50) */
    public static final String ELEARNING = "50";
    /**
     * 外部からインスタンス化できない
     */
    private EntryCode() {
    	super();
    }
}
