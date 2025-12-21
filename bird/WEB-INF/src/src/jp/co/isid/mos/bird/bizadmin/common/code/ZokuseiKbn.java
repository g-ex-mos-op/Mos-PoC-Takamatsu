package jp.co.isid.mos.bird.bizadmin.common.code;

/**
 * 会社属性区分定数クラス
 * 
 * @author xkinu
 */
public class ZokuseiKbn {

    // 属性区分:0 閲覧
	public static final String ETSURAN = "0";
    // 属性区分:1 所属
	public static final String SHOZOKU = "1";
    // 属性区分:2 契約
    public static final String KEIYAKU = "2";

    /**
     * 外部からインスタンス化できない
     */
    private ZokuseiKbn() {
    	super();
    }
    /**
     * 閲覧判断処理
     * 
     * 指定コードが閲覧か判断します。
     * @param kbn
     * @return
     */
    public static boolean is0Etsuran(String kbn) {
    	return ETSURAN.equals(kbn);
    }
    /**
     * 所属判断処理
     * 
     * 指定区分が所属か判断します。
     * @param kbn　指定区分
     * @return
     */
    public static boolean is1Shozoku(String kbn) {
    	return SHOZOKU.equals(kbn);
    }
    /**
     * 契約判断処理
     * 
     * 指定区分が契約か判断します。
     * @param kbn　指定区分
     * @return
     */
    public static boolean is3Keiyaku(String kbn) {
    	return KEIYAKU.equals(kbn);
    }
}
