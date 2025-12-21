/*
 * 作成日: 2005/11/28
 */
package jp.co.isid.mos.bird.framework.action;


/**
 * バッチ起動のアクションのインターフェイス
 * @author xytamura
 */
public interface BatchExecuteAction {
//    /**
//     * ＤＴＯを取得します。
//     * @return ＤＴＯ
//     */
//    public BatchExecuteDto getBatchExecuteDto();
//
//    /**
//     * 使用するＤＴＯをセットします。
//     * @param batchExecuteDto
//     */
//    public void setBatchExecuteDto(BatchExecuteDto batchExecuteDto);
//
//    /**
//     * ロジックを取得します。
//     * @return ロジック
//     */
//    public BatchExecuteLogic getBatchExecuteLogic();
//
//    /**
//     * 使用するロジックをセットします。
//     * @param batchExecuteLogic ロジック
//     */
//    public void setBatchExecuteLogic(BatchExecuteLogic batchExecuteLogic);

    /**
     * バッチ起動を行います。
     */
    public String executeBatch();
}