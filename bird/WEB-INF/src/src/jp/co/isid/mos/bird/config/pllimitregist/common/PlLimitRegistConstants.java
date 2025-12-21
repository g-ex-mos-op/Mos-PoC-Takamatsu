/*
 * 作成日: 2006/08/30
 *
 */
package jp.co.isid.mos.bird.config.pllimitregist.common;


/**
 * @author xyuchida
 */
public class PlLimitRegistConstants {

    private PlLimitRegistConstants() {
        // インスタンス生成禁止
    }

    /** 画面ID */
    public static final String SCREENID_PL_LIMIT_REGIST = "BCF005";

    /** ビューID */
    public static final String VIEWID_CONDITION = "BCF005V01";
    public static final String VIEWID_EDIT = "BCF005V02";
    public static final String VIEWID_CONFIRM = "BCF005V03";

    /* P/タイプ */
    public static final String PL_TYPE_HONSYA = "0";
    public static final String PL_TYPE_TENPO = "1";

    /* 上下限タイプ */
    public static final String LIMIT_TYPE_SUM = "0";
    public static final String LIMIT_TYPE_RATIO = "1";

    /* 構成比指定不可フラグ */
    public static final String KOSEIHI_FLG_OFF = "0";
    public static final String KOSEIHI_FLG_ON = "1";

    /* 設定タイプ-上下限値セパレータ */
    public static final String LIMIT_SEPARATOR = ":";
}
