package jp.co.isid.mos.bird.common.code;

/**
 * 所属区分定数クラス
 * 
 * @author xnkusama
 */
public class UserShozokuKbn {

	/** 所属区分:本部(1) */
    public static final String HONBU = "1";

    /** 所属区分:オーナー(2) */
    public static final String ONER = "2";

    /** 所属区分：直営店(3) */
    public static final String RC_TENPO = "3";

    /** 所属区分:FC店(4) */
    public static final String FC_TENPO = "4";

    /** 所属区分:販社(5) */
    public static final String HANSHA = "5";

    /** 所属区分:販社店(6) */
    public static final String HANSHA_TENPO = "6";

    /**
     * 外部からインスタンス化できない
     */
    private UserShozokuKbn() {
    	super();
    }
}
