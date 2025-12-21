/*
 * 作成日: 2005/11/28
 * 更新日: 2007/04/20 DI用のInterface定義は不要なので削除
 */
package jp.co.isid.mos.bird.framework.action;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * CSVダウンロードアクション用Interface
 * 
 * 事前処理、Exception発生時処理機能が付いているCSVダウンロードアクションです。
 * @author xkinu
 */
public interface CsvDownloadAction {
    /** 
     * パラメーターKey：BIRDユーザー情報 
     */
    public static final String PK_USERINFO = "userInfo";
    /** 
     * パラメーターKey：BIRD日付情報 
     */
    public static final String PK_DATEINFO = "dateInfo";
    /** 
     * パラメーターKey：Exceptioオブジェクト 
     */
    public static final String PK_EXCEPTION = "exception";
    /** 
     * パラメーターKey：CSV出力用DTO 
     */
    public static final String PK_CSVOUTPUTDTO = "csvoutputDto";

//--- 2007/04/20 delete start
//    /**
//     * HttpServletResponse設定処理
//     * @param httpServletResponse
//     */
//    public void setResponse(HttpServletResponse httpServletResponse);
//
//    /**
//     * 事前処理ロジック設定処理
//     * @param initLogic
//     */
//    public void setInitLogic(final DownloadInitLogic initLogic);
//
//    /**
//     * CSV出力用ロジック設定処理
//     * @param downloadLogic
//     */
//    public void setCsvOutputLogic(final CsvOutputLogic downloadLogic);
//
//    /**
//     * Exception発生時処理ロジック設定処理
//     * 
//     * ダウンロード メイン処理中にExceptionが発生した場合に実行されるロジックです。
//     * @param initLogic
//     */
//    public void setFinalLogic(final DownloadFinalLogic handleLogic);
//
//    /**
//     * CSV出力用DTO設定処理
//     * @param csvOutputDto
//     */
//    public void setCsvOutputDto(final CsvOutputDto csvOutputDto);
//--- 2007/04/20 delete end
    /**
     * ダウンロード メイン処理
     * @throws ApplicationException
     */
    public void download();
}