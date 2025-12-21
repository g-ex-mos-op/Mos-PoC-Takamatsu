/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic;

import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistDto;

/**
 * 同姓同名スタッフ存在チェック
 * 
 * 作成日:2009/08/28
 * @author xkinu
 *
 */
public interface CheckDuplicateNameLogic {
	/**
	 * 同姓同名スタッフ存在チェック処理実行
	 * 
	 * @param staffRegistDto
	 * @return
	 */
	public boolean execute(StaffRegistDto staffRegistDto);
}
