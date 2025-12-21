package jp.co.isid.mos.bird.bizsupport.pointmstregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * ポイントマスタメンテナンス
 * @author yushuncheng
 *
 */
public interface PointMstRegistRefAction extends CommonAction {

	/**
     * 初期処理
     *
     *  @return 画面遷移情報
     */
    public String initialize();

    /**
     * 新規処理
     * @return 編集画面
     */
    public String insert();

    /**
     * 編集処理
     * @return 編集画面
     */
    public String edit();

    /**
     * 計画削除処理
     * @return  初期画面
     */
    public String delete();
}
