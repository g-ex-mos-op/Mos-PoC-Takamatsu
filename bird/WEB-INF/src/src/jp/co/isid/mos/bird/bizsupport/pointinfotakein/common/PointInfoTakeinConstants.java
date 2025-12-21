/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.common;

/**
 * ポイント情報取込 定数クラス
 * @author yushuncheng
 *
 */
public class PointInfoTakeinConstants {

    /* 定数 */
    // 画面ID
    public static final String VIEW_ID = "BBS037";
    // 画面ID:ポイント情報取込 初期画面
    public static final String SCREEN_ID= "BBS037V01";

    // 処理選択 １：基本ポイント付与
    public static final int PROCESS_MODE_HPRI = 1;
    // 処理選択 ２：海外赴任精算
    public static final int PROCESS_MODE_KAIGAI_HPRI = 2;
    // 処理選択 ３：退職
    public static final int PROCESS_MODE_TSES = 3;
    // 処理選択 ４：役員情報登録
    public static final int PROCESS_MODE_YPRI = 4;

    public static final String ERROR_COL_TORI_SAKUJO_FLG = "に「0」または「1」以外が入力されています。";

    public static final String ERROR_COL_ISNULL = "が入力されていません。";

    public static final String ERROR_COL_VALUE_MIS = "に誤りがあります。";

    public static final String ERROR_COL_LEN_OVER = "が桁数オーバーです。";

    public static final String ERROR_COL_NO_DATA = "が存在しません。";

    public static final String ERROR_COL_KAIGAI_FLG = "に「1」またはスペース以外が入力されています。";

    public static final String ERROR_DATA_EXIST = "「年度、社員番号、入社日」は同ファイルのほかのデータと重複したため、取込できません。";

    public static final String POINT_ERROR_DATA_EXIST = "「年度、社員番号、ポイント付与種別」は同ファイルのほかのデータと重複したため、取込できません。";

    public static final String NENUSE_ERROR_DATA_EXIST = "「年度、社員番号」は同ファイルのほかのデータと重複したため、取込できません。";

    // CSVタイトル
    public static final String CSV_HEADER_HPRI = "削除対象フラグ,年度,社員番号,会社コード,等級,海外赴任中,備考";
    public static final String CSV_HEADER_KAIGAI_HPRI = "削除対象フラグ,年度,社員番号,備考";
    public static final String CSV_HEADER_RTESE = "削除対象フラグ,年度,社員番号,会社コード,退職年月日,入社年月日,勤続年数,退職事由,備考";
    public static final String CSV_HEADER_YPRI = "削除対象フラグ,年度,社員番号,会社コード,ポイント付与種別,ポイント,備考";


    public static final String KAIGAI_FLG_1 = "1";

    // 付与ポイント種別 １：基本ポイント付与
    public static final String POINT_SHU_1 = "1";

    // 付与ポイント種別 ２：海外赴任精算
    public static final String POINT_SHU_2 = "2";

    // 付与ポイント種別 ３：役員ポイント
    public static final String POINT_SHU_3 = "3";

    // 付与ポイント種別 ４：業績ポイント
    public static final String POINT_SHU_4 = "4";

    //--- CSVアップロード ---//
    // CSV項目列数
    public static final int CSV_HPRI_UPLOAD_COL_NUM = 7;
    public static final int CSV_KAIGAI_HPRI_UPLOAD_COL_NUM = 4;
    public static final int CSV_RTSES_UPLOAD_COL_NUM = 8;
    public static final int CSV_YPRI_UPLOAD_COL_NUM = 7;

    // 位置：取込削除フラグ列
    public static final int CSV_COL_TORI_SAKUJO_FLG = 0;
    // 位置：年度列
    public static final int CSV_COL_NENDO = 1;
    // 位置：社員番号列
    public static final int CSV_COL_USER_ID = 2;
    // 位置：会社コード列
    public static final int CSV_COL_HPRI_KB_COMPANY_CD = 3;
    // 位置：等級列
    public static final int CSV_COL_RANK_CD = 4;
    // 位置：海外赴任中フラグ列
    public static final int CSV_COL_KAIGAI_FLG = 5;


    // 位置：会社コード列
    public static final int CSV_COL_TSES_KB_COMPANY_CD = 3;
    // 位置：退職日列
    public static final int CSV_COL_TAISHOKU_DT = 4;
    // 位置：入社日列
    public static final int CSV_COL_NYUSYA_DT = 5;
    // 位置：退職事由列
    public static final int CSV_COL_RETIRE_CD = 6;

    // 位置：会社コード列
    public static final int CSV_COL_YPRI_KB_COMPANY_CD = 3;
    // 位置：付与ポイント種別列
    public static final int CSV_COL_POINT_SHU_NAME = 4;
    // 位置：ポイント列
    public static final int CSV_COL_POINT = 5;

}
