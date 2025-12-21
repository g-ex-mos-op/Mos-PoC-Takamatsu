/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.common;

/**
 * マスタライセンスマスタ登録定数クラス
 * @author xyuchida
 */
public class MlRegistConstants {

    private MlRegistConstants() {
        // インスタンス生成禁止
    }

    /** 画面ID */
    public static final String SCREENID_ML_REGIST = "BEN007";

    /** ビューID */
    public static final String VIEWID_SELECT = "BEN007V01";
    public static final String VIEWID_EDIT = "BEN007V02";
    public static final String VIEWID_CONFIRM = "BEN007V03";

    /** 画面制御 */
    // 1ページあたりの表示件数
    public static final int MAX_PAGE_COUNT = 10;
    // 編集モード
    public static final int EDIT_MODE_INSERT = 1;
    public static final int EDIT_MODE_UPDATE = 2;
    public static final int EDIT_MODE_DELETE = 3;

    /** エントリーマスタ管理 */
    // エントリーコード
    public static final String ENTRY_CD_ML = "10";
    // 削除フラグ
    public static final String SAKUJO_FLG_ON = "1";
    public static final String SAKUJO_FLG_OFF = "0";

    /** エントリー日付管理 */
    // ユーザタイプコード
    public static final String USERTYPE_CODE_HONBU = "01";
    public static final String USERTYPE_CODE_OWNER = "02";
    public static final String USERTYPE_CODE_OTHER = "99";
    // 日付区分
    public static final String DAY_KBN_OPEN = "01";
    public static final String DAY_KBN_DISPLAY = "03";
    public static final String DAY_KBN_APPLY = "04";
    public static final String DAY_KBN_RESULT = "05";
    public static final String DAY_KBN_REGIST = "06";
}
