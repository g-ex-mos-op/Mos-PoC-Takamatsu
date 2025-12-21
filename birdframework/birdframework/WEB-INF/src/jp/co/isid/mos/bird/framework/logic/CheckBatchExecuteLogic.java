/*
 * 作成日: 2006/01/19
 *
 */
package jp.co.isid.mos.bird.framework.logic;

/**
 * @author xytamura
 *
 */
public interface CheckBatchExecuteLogic {
    /**
     * バッチ排他チェックを実行する
     * @param id アクションＩＤまたは、ロジックＩＤ
     */
    public abstract void execute(final String id);
}