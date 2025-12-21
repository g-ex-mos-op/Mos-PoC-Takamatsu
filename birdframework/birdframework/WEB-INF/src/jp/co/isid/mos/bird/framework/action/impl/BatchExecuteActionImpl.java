/*
 * 作成日: 2005/11/16
 */
package jp.co.isid.mos.bird.framework.action.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.isid.mos.bird.framework.action.BatchExecuteAction;
import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.framework.dto.BatchExecuteDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.BatchExecuteLogic;

/**
 * バッチ起動のアクションクラス
 * @author xytamura
 */
public class BatchExecuteActionImpl implements BatchExecuteAction {

    public static final String executeBatch_ACTION_ID = "BFW001A01";
    
    /**
     * HttpServletResponse
     */
    private HttpServletResponse httpServletResponse;

    /**
     * httpServletRequest
     */
    private HttpServletRequest httpServletRequest;

    /**
     * 使用するＤＴＯ
     */
    private BatchExecuteDto batchExecuteDto;

    /**
     * 使用するロジック
     */
    private BatchExecuteLogic batchExecuteLogic;

    /**
     * HttpServletResponse設定処理
     * @param httpServletResponse
     */
    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    /**
     * HttpServletResponse取得処理
     * @return HttpServletResponse
     */
    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    /**
     * httpServletRequest設定処理
     * @param httpServletRequest
     */
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * httpServletRequest取得処理
     * @return httpServletRequest
     */
    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    /**
     * ＤＴＯを取得します。
     * @return ＤＴＯ
     */
    public BatchExecuteDto getBatchExecuteDto() {
        return batchExecuteDto;
    }

    /**
     * 使用するＤＴＯをセットします。
     * @param batchExecuteDto
     */
    public void setBatchExecuteDto(BatchExecuteDto batchExecuteDto) {
        this.batchExecuteDto = batchExecuteDto;
    }

    /**
     * ロジックを取得します。
     * @return ロジック
     */
    public BatchExecuteLogic getBatchExecuteLogic() {
        return batchExecuteLogic;
    }

    /**
     * 使用するロジックをセットします。
     * @param batchExecuteLogic ロジック
     */
    public void setBatchExecuteLogic(BatchExecuteLogic batchExecuteLogic) {
        this.batchExecuteLogic = batchExecuteLogic;
    }

    /**
     * バッチ起動を行います。
     */
    public String executeBatch() {
        BatchExecuteDto localBatchExecuteDto = null;

        try {

            //チェック
            batchExecuteLogic.validate(batchExecuteDto);

            //実行ジョブをセット
            batchExecuteDto.setExecuteJobName(batchExecuteLogic
                    .getExecuteBatch(batchExecuteDto));

            //事前処理
            localBatchExecuteDto = batchExecuteLogic
                    .preProcessing(batchExecuteDto);

            //パラメータを再取得
            String[] job = localBatchExecuteDto.getExecuteJobName();

            if (job == null || job.length == 0) {
                throw new FtlSystemException("バッチ起動", "バッチが存在しません");
            }
            //バッチ実行
            startBatch(job);

            //事後処理
            batchExecuteLogic.postProcessing(localBatchExecuteDto);
        } catch (Exception ex) {
            //異常終了の事後処理
            batchExecuteLogic.errProcessing(localBatchExecuteDto);
            if (ex instanceof ApplicationException) {
                throw (ApplicationException) ex;
            } else {
                throw new FtlSystemException("バッチ起動", ex.toString(), ex);
            }
        }
        return batchExecuteLogic.getPageKey(localBatchExecuteDto);

    }

    /**
     * 実行処理
     * @param job 実行するコマンド
     */
    private synchronized void startBatch(final String[] job) {

        String[] execute = convertBatch(job);

        int result = -1;
        Process process = null;
        Runtime runtime = Runtime.getRuntime();
        try {
            //バッチを実行
            process = runtime.exec(execute);
            while (result != 0) {
                result = process.waitFor();
                if (result >= 0) {
                    break;
                }
            }
        } catch (Exception ex) {
            throw new FtlSystemException("バッチ起動", ex.toString(), ex);
        }
    }

    /**
     * 実行形式に成型します。
     * @param job 実行するコマンド
     * @return 成型されたコマンド
     */
    private String[] convertBatch(final String[] job) {
        String scriptName = BirdContext.getProperty("commonProperty",
                "scriptName");
        String[] executeJob = new String[job.length + 1];

        executeJob[0] = scriptName;
        for (int i = 0; i < job.length; i++) {
            executeJob[i + 1] = job[i];
        }
        return executeJob;
    }

}

