/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.logic;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.NendoPointDataDto;

/**
 * 会社等級検索ロジックインターフェース
 * @author yushuncheng
 *
 */
public interface GetToukyuInfoLogic {

	public List<NendoPointDataDto> execute();

}
