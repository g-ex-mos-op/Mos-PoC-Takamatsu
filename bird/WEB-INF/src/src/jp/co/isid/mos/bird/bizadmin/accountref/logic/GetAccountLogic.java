/*
 * 作成日: 2006/03/03
 */
package jp.co.isid.mos.bird.bizadmin.accountref.logic;

import jp.co.isid.mos.bird.bizadmin.accountref.dto.AccountRefDto;

/**
 * アカウント情報取得処理
 * @author 慮
 */
public interface GetAccountLogic {
	/**
	 * アカウント情報取得
	 * 実行処理
	 * 
	 * @param dto　アカウント情報保持DTO
	 * @param userId
	 * @return 画面ID(VIEW_ID)
	 */
	public String execute(AccountRefDto dto, String userId);
}
