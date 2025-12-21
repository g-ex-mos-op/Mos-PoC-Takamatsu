/*
 * 作成日: 2006/05/30
 *
 */
package jp.co.isid.mos.bird.entry.basicregist.action.impl;

import jp.co.isid.mos.bird.entry.basicregist.action.BasicRegistConfirmAction;
import jp.co.isid.mos.bird.entry.basicregist.common.BasicRegistConstants;
import jp.co.isid.mos.bird.entry.basicregist.dto.BasicRegistDto;
import jp.co.isid.mos.bird.entry.basicregist.logic.UpdateEntryLogic;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * ベーシック研修マスタ登録確認アクション
 * 
 * @author xyuchida
 */
public class BasicRegistConfirmActionImpl implements BasicRegistConfirmAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN001A21";
    public static final String regist_ACTION_ID = "BEN001A22";
    public static final String back_ACTION_ID = "BEN001A23";

    private BasicRegistDto basicRegistDto;
    private PullDownMenuDto pullDownMenuDto;
    private UpdateEntryLogic updateEntryLogic;

    public BasicRegistDto getBasicRegistDto() {
        return basicRegistDto;
    }
    public void setBasicRegistDto(BasicRegistDto basicRegistDto) {
        this.basicRegistDto = basicRegistDto;
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
        // ベーシック研修マスタ更新(登録/更新/削除)
        getUpdateEntryLogic().execute(getBasicRegistDto());

        // 画面を初期化する為フラグON
        getPullDownMenuDto().setClearFlg(true);

        // 条件画面へ遷移
        return BasicRegistConstants.VIEWID_SELECT;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // 編集モードにより戻り先を切り替える
        String navigation = null;
        if (getBasicRegistDto().getEditMode() != BasicRegistConstants.EDIT_MODE_DELETE) {
            // 新規、更新の場合、編集画面へ遷移
            navigation = BasicRegistConstants.VIEWID_EDIT;
        } else {
            // 削除の場合、条件画面へ遷移
            navigation = BasicRegistConstants.VIEWID_SELECT;
        }
        return navigation;
    }
}
