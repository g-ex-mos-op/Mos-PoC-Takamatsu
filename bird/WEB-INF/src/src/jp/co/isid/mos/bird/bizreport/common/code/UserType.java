package jp.co.isid.mos.bird.bizreport.common.code;

/**
 * ユーザタイプコード定数クラス
 * 
 * @author xjung
 */
public class UserType {

	/** ユーザータイプコード:本部(01) */
    public static final String HONBU = "01";

    /** ユーザータイプコード:オーナー(02) */
    public static final String ONER = "02";

    /** ユーザータイプコード店舗(03) */
    public static final String TENPO = "03";

    /** ユーザータイプコード:管理用(99) */
    public static final String KANRI = "99";

    /**
     * 外部からインスタンス化できない
     */
    private UserType() {
    	super();
    }
}
