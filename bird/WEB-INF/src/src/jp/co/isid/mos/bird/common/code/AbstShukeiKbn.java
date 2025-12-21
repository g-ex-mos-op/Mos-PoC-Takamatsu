package jp.co.isid.mos.bird.common.code;


/**
 * 集計区分コード定数クラス
 * 
 * 作成日:2012/12/26
 * @author xkinu
 *
 */
public abstract class AbstShukeiKbn extends AbstractCodeUtil {

	/** 集計区分：直営・○○を含まない */
    public static final String OUT_RC = "SIBU_CD";

    /** 集計区分：直営・○○を含む */
    public static final String IN_RC = "AREA_DAI";
	/** 集計区分：直営・○○を含まない */
    public static final String LABEL = "直営・MSC";
	/** 集計区分：直営・○○を含まない */
    public static final String LABEL_OUT_RC = LABEL+"を含まない";
    /** 集計区分：直営・○○を含む */
    public static final String LABEL_IN_RC = LABEL+"を含む";
 
}
