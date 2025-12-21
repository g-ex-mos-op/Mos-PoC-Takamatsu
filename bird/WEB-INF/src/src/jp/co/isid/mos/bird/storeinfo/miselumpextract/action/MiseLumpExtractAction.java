/*
 * 作成日: 2006/5/25
 */
package jp.co.isid.mos.bird.storeinfo.miselumpextract.action;

/**
 * 研修申込状況照会 照会画面アクションインターフェース
 * @author xayumi
 */
public interface MiseLumpExtractAction {

    /**
     * 条件画面(初期処理)
     */
    public String initialize();

    /**
     * CSVダウンロード
     */
    public String downloadCsv();

   
}