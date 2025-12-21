package jp.co.isid.mos.bird.framework.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.action.CsvDownloadAction;

/**
 * ダウンロード用
 * 初期処理ロジックインターフェース
 * @author xkinu
 *
 */
public interface DownloadInitLogic {
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = CsvDownloadAction.PK_USERINFO;
    /** 
     * パラメーターKey：BIRD日付情報 
     */
    public static final String PK_DATEINFO = CsvDownloadAction.PK_DATEINFO;
    /** 
     * パラメーターKey：CSV出力用DTO 
     */
    public static final String PK_CSVOUTPUTDTO = CsvDownloadAction.PK_CSVOUTPUTDTO;
    /**
     * 実行処理
     * 
     * @param params
     * @return
     */
    public Map execute(final Map params);

}
