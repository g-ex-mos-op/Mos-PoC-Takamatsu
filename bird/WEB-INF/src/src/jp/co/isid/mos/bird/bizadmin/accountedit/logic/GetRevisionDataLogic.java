/*
 * 作成日: 2006/02/13
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic;

/**
 * ユーザーアカウント情報取得設定
 * ロジックインターフェース
 */
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccounteditDto;


public interface GetRevisionDataLogic {
	/**
	 * 実行処理
	 * 
	 * ユーザーアカウント情報取得設定を行います。
	 * @param userId 修正対象ユーザーID
	 * @param accounteditDto　ユーザーアカウント情報保持DTO
	 */
	public void execute(String userId, AccounteditDto accounteditDto);

}
