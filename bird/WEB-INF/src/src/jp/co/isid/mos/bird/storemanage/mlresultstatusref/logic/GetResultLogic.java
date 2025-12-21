/*
 * 作成日: 2006/04/13
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.RequestDto;

/**
 * 結果情報取得処理ロジック
 * 
 * 更新日:209/09/08　xkinu　支部制限を検索結果取得時に設定するため下記の修正を行いました。
 *                          1.executeメソッドのパラメータMlResultStatusRefDtoをRequestDtoに変更しました。
 *                          2.executeメソッドにBIRDユーザ情報とBIRD日付情報を追加しました。
 * @author Noh
 */
public interface GetResultLogic {
	/**
	 * 検索処理実行処理
	 * 
	 * @param birdUserInfo
	 * @param birdDateInfo
	 * @param dto
	 */
	public void execute(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo, RequestDto dto);
	
}
