/*
 * 作成日: 2005/11/16
 */
package jp.co.isid.mos.bird.framework.logic;

import jp.co.isid.mos.bird.framework.dto.BatchExecuteDto;

/**
 * バッチ起動ロジックのインターフェイス
 * @author xytamura
 */
public interface BatchExecuteLogic {
    
    /**
     * 実行するジョブをリターンするように実装します。
     * diconファイルからセットされた値を使用したい場合はBatchExecuteDto#getExecuteJobName()の
     * 戻り値をリターンするように実装します。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     * @return 実行するジョブ
     */
    public String[] getExecuteBatch(final BatchExecuteDto batchExecuteDto);

    /**
     * チェック処理を実装します。最初に呼び出されます。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     */
    public void validate(final BatchExecuteDto batchExecuteDto);

    /**
     * 事前処理を実装します。バッチ起動前に呼び出されます。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     * @return 画面が使用するＤＴＯ
     */
    public BatchExecuteDto preProcessing(final BatchExecuteDto batchExecuteDto);

    /**
     * 正常終了時の事後処理を実装します。バッチ起動後に呼び出されます。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     */
    public void postProcessing(final BatchExecuteDto batchExecuteDto);

    /**
     * 異常終了時の事後処理を実装します。バッチ起動後に呼び出されます。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     */
    public void errProcessing(final BatchExecuteDto batchExecuteDto);

    /**
     * 遷移先を取得します。
     * @param batchExecuteDto 画面が使用するＤＴＯ
     */
    public String getPageKey(final BatchExecuteDto batchExecuteDto);

    
    
}