package jp.co.isid.mos.bird.entry.projectplanmstregist.util;



/**
 * 事業方針説明会マスタ登録
 * static 処理保持クラス
 * 
 * @author xkinu
 *
 */
public class ProjectPlanMstRegistUtil {
    /** ユーザータイプコード：その他 「99」*/
    public static final String USER_TYPE_CD_ETC = "99";
    /** ユーザータイプコード：本部 「01」*/
    public static final String USER_TYPE_CD_HONBU = "01";
    /** ユーザータイプコード：オーナー 「02」*/
    public static final String USER_TYPE_CD_ONER = "02";
    /** ユーザータイプコード：店舗 「03」*/
    public static final String USER_TYPE_CD_MISE = "03";
    /** VIEW_ID：操作エラー(複数Window対応用）*/
    public static final String operationErr_VIEW_ID = "operation.Err";
    /** 画面ID：BEN010 */
    public static final String SCREEN_ID = "BEN010";
    /** VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /** VIEWID：編集画面 */
    public static final String VIEW_ID_EDIT = SCREEN_ID+"V02";
    /** VIEWID：確認or参照画面 */
    public static final String VIEW_ID_CONFIRM = SCREEN_ID+"V03";
    /** エントリーコード：事業方針説明会コード 「25」*/
    public static final String ENTRY_CD = "25";
    /** 日付区分：開催日 「01」*/
    public static final String DAY_KBN_EXECDT  = "01";
    /** 日付区分：申込 「04」*/
    public static final String DAY_KBN_ENTRYDT = "04";
    /** 日付区分：表示 「03」*/
    public static final String DAY_KBN_DISPDT  = "03";
    /**
     * 新規登録判断フラグ値：新規登録処理時
     */
    public static final String MODETYPE_INSERT = "INSERT";
    /**
     * 新規登録判断フラグ値：更新登録処理時
     */
    public static final String MODETYPE_UPDATE = "UPDATE";
    /**
     * 新規登録判断フラグ値：更新登録処理時
     */
    public static final String MODETYPE_DELETE = "DELETE";
    /**
     * 委任状デフォルト文字列
     */
    public static final String NOTE_DEFAULT = "私は、第○回モスバーガー共栄会定時総会における議決権の行使を、議長に委任いたします。";

    public static final String NOTICE1_DEFAULT = "※申込終了日を過ぎますと、修正・削除はできません。`" +
            "　内容変更に関しては本部・営業推進グループに`" +
            "　お問い合わせください。`" +
            "`" +
            "【注意事項】`" +
            "※事業方針説明会にお一人も出席されない場合は、`" +
            "　申込責任者欄と欠席理由欄をご入力ください。`" +
            "　（欠席理由欄は５０文字以内でご入力ください）`" +
            "`" +
            "※事業方針説明会にお二人以上参加される場合は、`" +
            "　参加費用としてお二人目からお一人につき`" +
            "　５，０００円を申し受けます。（交通費も各自負担）`" +
            "`" +
            "※共栄会定時総会にお一人も出席されない場合は、`" +
            "　委任状を登録してください。";
    /**
     * コンストラクター
     *
     */
    private ProjectPlanMstRegistUtil() {
        super();
    }
    /**
     * 指定文字列内改行文字JAVA用変換処理と変換後文字列取得処理
     * 
     * 
     * @param targetword 指定文字列
     * @return
     */
    public static String changeEnterWordDBtoJSF(String targetword){
        String changedWord = targetword.replaceAll("`", "\n");
        return changedWord;
    }
    /**
     * 指定文字列内改行文字DB用変換処理と変換後文字列取得処理
     * 
     * @param targetword 指定文字列
     * @return
     */
    public static String changeEnterWordJSFtoDB(String targetword){
        String changedWord = targetword.replaceAll("\r\n", "`");
        changedWord = changedWord.replaceAll("\n", "`");
        return changedWord;
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
}