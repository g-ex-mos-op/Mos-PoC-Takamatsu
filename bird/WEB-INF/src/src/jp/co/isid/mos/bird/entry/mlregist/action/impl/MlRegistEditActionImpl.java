/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.action.impl;

import jp.co.isid.mos.bird.entry.mlregist.action.MlRegistEditAction;
import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.logic.CheckEntryLogic;

/**
 * マスタライセンスマスタ登録編集アクション
 * 
 * @author xyuchida
 */
public class MlRegistEditActionImpl implements MlRegistEditAction {

    // DTO
    private MlRegistDto mlRegistDto;
    // Logic
    private CheckEntryLogic checkEntryLogic;

    public MlRegistDto getMlRegistDto() {
        return mlRegistDto;
    }
    public void setMlRegistDto(MlRegistDto mlRegistDto) {
        this.mlRegistDto = mlRegistDto;
    }
    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }
    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        // 自画面へ遷移
        return null;
    }

    /**
     * 確認
     * @return 画面遷移情報
     */
    public String confirm() {

        // 入力値チェック
        getCheckEntryLogic().execute(getMlRegistDto());

        // 確認画面へ遷移
        return MlRegistConstants.VIEWID_CONFIRM;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // 条件画面へ遷移
        return MlRegistConstants.VIEWID_SELECT;
    }
}
