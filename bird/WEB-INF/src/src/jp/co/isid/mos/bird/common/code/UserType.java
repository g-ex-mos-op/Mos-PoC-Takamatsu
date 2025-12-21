package jp.co.isid.mos.bird.common.code;

/**
 * ユーザタイプコード定数クラス
 * 
 * @author xkinu
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
    /**
     * 本部コード判断処理
     * 
     * 指定コードが本部のタイプコードか判断します。
     * @param cd
     * @return
     */
    public static boolean isHonbu(String cd) {
    	return HONBU.equals(cd);
    }
    /**
     * オーナーコード判断処理
     * 
     * 指定コードがオーナーのタイプコードか判断します。
     * @param cd
     * @return
     */
    public static boolean isOner(String cd) {
    	return ONER.equals(cd);
    }
    /**
     * 店舗コード判断処理
     * 
     * 指定コードが店舗のタイプコードか判断します。
     * @param cd
     * @return
     */
    public static boolean isTenpo(String cd) {
    	return TENPO.equals(cd);
    }
    /**
     * 管理者コード判断処理
     * 
     * 指定コードが管理者のタイプコードか判断します。
     * @param cd
     * @return
     */
    public static boolean isKanri(String cd) {
    	return KANRI.equals(cd);
    }
}
