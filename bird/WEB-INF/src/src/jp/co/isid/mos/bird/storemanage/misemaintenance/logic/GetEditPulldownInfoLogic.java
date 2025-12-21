/*
 * 作成日: 2006/02/28
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.logic;

import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;

/**
 * 編集画面プルダウン情報取得 ロジック インターフェイス
 * @author xnkusama
 * 
 * 更新日:2011/07/08 xkinu ガス&エアコン種別項目追加対応
 */
public interface GetEditPulldownInfoLogic {
    /**
     * 編集画面プルダウン情報の検索を行う
     * @param MiseMaintenanceDto sessionDto セッション情報保持DTO
     */
    public void execute(MiseMaintenanceDto sessionDto);
}