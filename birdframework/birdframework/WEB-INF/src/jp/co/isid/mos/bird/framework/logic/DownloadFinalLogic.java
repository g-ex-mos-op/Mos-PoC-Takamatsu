package jp.co.isid.mos.bird.framework.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.action.CsvDownloadAction;

/**
 * ダウンロード用
 * Exception発生時処理ロジックインターフェース
 * 
 * ダウンロード メイン処理中にExceptionが発生した場合に実行されるロジックです。
 * @author xkinu
 *
 */
public interface DownloadFinalLogic {
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = CsvDownloadAction.PK_USERINFO;
    /** 
     * パラメーターKey：BIRD日付情報 
     */
    public static final String PK_DATEINFO = CsvDownloadAction.PK_DATEINFO;
    /** 
     * パラメーターKey：Exceptioオブジェクト 
     */
    public static final String PK_EXCEPTION = CsvDownloadAction.PK_EXCEPTION;
    /** 
     * パラメーターKey：CSV出力用DTO 
     */
    public static final String PK_CSVOUTPUTDTO = CsvDownloadAction.PK_CSVOUTPUTDTO;
    /**
     * 実行処理
     * 
     * @param params
     */
    public void execute(final Map params);

}
