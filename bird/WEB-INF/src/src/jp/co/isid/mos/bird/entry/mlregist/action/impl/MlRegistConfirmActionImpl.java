/*
 * 作成日: 2006/06/08
 *
 */
package jp.co.isid.mos.bird.entry.mlregist.action.impl;

import jp.co.isid.mos.bird.entry.mlregist.action.MlRegistConfirmAction;
import jp.co.isid.mos.bird.entry.mlregist.common.MlRegistConstants;
import jp.co.isid.mos.bird.entry.mlregist.dto.MlRegistDto;
import jp.co.isid.mos.bird.entry.mlregist.logic.UpdateEntryLogic;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * マスタライセンスマスタ登録確認アクション
 * 
 * @author xyuchida
 */
public class MlRegistConfirmActionImpl implements MlRegistConfirmAction {

    // DTO
    private MlRegistDto mlRegistDto;
    private PullDownMenuDto pullDownMenuDto;
    // Logic
    private UpdateEntryLogic updateEntryLogic;

    public MlRegistDto getMlRegistDto() {
        return mlRegistDto;
    }
    public void setMlRegistDto(MlRegistDto mlRegistDto) {
        this.mlRegistDto = mlRegistDto;
    }
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public UpdateEntryLogic getUpdateEntryLogic() {
        return updateEntryLogic;
    }
    public void setUpdateEntryLogic(UpdateEntryLogic updateEntryLogic) {
        this.updateEntryLogic = updateEntryLogic;
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
     * 登録
     * @return 画面遷移情報
     */
    public String regist() {

        // マスタライセンスマスタ更新(登録/更新/削除)
        getUpdateEntryLogic().execute(getMlRegistDto());

        // 画面を初期化する為フラグON
        getPullDownMenuDto().setClearFlg(true);

        // 条件画面へ遷移
        return MlRegistConstants.VIEWID_SELECT;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // 編集モードにより戻り先を切り替える
        String navigation = null;
        if (getMlRegistDto().getEditMode() != MlRegistConstants.EDIT_MODE_DELETE) {
            // 新規、更新の場合、編集画面へ遷移
            navigation = MlRegistConstants.VIEWID_EDIT;
        } else {
            // 削除の場合、条件画面へ遷移
            navigation = MlRegistConstants.VIEWID_SELECT;
        }
        return navigation;
    }
}
