/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.action;

import jp.co.isid.mos.bird.framework.action.CommonAction;

/**
 * ポイントマスタメンテナンス
 * @author yushuncheng
 *
 */
public interface PointMstRegistEditAction extends CommonAction {

	/**
     * 編集画面
     *
     * @return 初期処理
     */
    public String initialize();

    /**
     * 対象年度追加
     * @return 対象年度追加処理
     */
    public String addNendo();

    /**
     * 年度削除
     * @return 年度削除処理
     */
    public String deleteNendo();

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back();

    public String insert();

}
