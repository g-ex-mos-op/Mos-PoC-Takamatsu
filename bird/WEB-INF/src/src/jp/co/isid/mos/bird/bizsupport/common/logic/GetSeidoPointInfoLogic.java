/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.common.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;

/**
 * 株式報酬制度ポイント検索ロジックインターフェース
 * @author yushuncheng
 *
 */
public interface GetSeidoPointInfoLogic {

	public List<UISeidoMst> execute();

}
