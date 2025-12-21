package jp.co.isid.mos.bird.entry.manageregist.action.impl;

import jp.co.isid.mos.bird.entry.manageregist.action.ManageRegistConfirmAction;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistConstants;
import jp.co.isid.mos.bird.entry.manageregist.dto.ManageRegistDto;
import jp.co.isid.mos.bird.entry.manageregist.logic.UpdateEntryLogic;

/**
 * 全国店長勉強会マスタ登録 確認アクション
 *
 * @author xjung
 */
public class ManageRegistConfirmActionImpl implements ManageRegistConfirmAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BEN020A08";

    /** 登録アクションID */
    public static final String regist_ACTION_ID = "BEN020A09";

    /** 戻るアクションID */
    public static final String back_ACTION_ID = "BEN020A10";

    /** 全国店長勉強会マスタ登録情報DTO */
    private ManageRegistDto manageRegistDto;

    /** 全国店長勉強会マスタ情報登録ロジック */
    private UpdateEntryLogic updateEntryLogic;

    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        return null;
    }

    /**
     * 登録
     * @return 画面遷移情報
     */
    public String regist() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getManageRegistDto().isValidSessionKey()) {
            return ManageRegistConstants.VIEW_ID_ERR;
        }

        // 全国店長勉強会マスタ情報を登録
        getUpdateEntryLogic().execute(getManageRegistDto());

        // 全国店長勉強会マスタ登録情報を削除
        getManageRegistDto().clear();

        // 編集モード設定
        getManageRegistDto().setEditMode(ManageRegistConstants.EDIT_MODE_INIT);

        // 条件画面へ遷移
        return ManageRegistConstants.VIEW_ID_SELECT;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getManageRegistDto().isValidSessionKey()) {
            return ManageRegistConstants.VIEW_ID_ERR;
        }

        // 対象処理が削除の場合
        if (getManageRegistDto().getEditMode() == ManageRegistConstants.EDIT_MODE_DELETE) {
            // 編集モード設定
            getManageRegistDto().setEditMode(ManageRegistConstants.EDIT_MODE_RETURN);

            // 条件画面へ遷移
            return ManageRegistConstants.VIEW_ID_SELECT;
        }

        // 編集画面へ遷移
        return ManageRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 全国店長勉強会マスタ登録情報DTOを取得する。
     * @return 全国店長勉強会マスタ登録情報DTO
     */
    public ManageRegistDto getManageRegistDto() {
        return manageRegistDto;
    }

    /**
     * 全国店長勉強会マスタ登録情報DTOを設定する。
     * @param manageRegistDto 全国店長勉強会マスタ登録情報DTO
     */
    public void setManageRegistDto(ManageRegistDto manageRegistDto) {
        this.manageRegistDto = manageRegistDto;
    }

    /**
     * 全国店長勉強会マスタ情報登録ロジックを取得する。
     * @return 全国店長勉強会マスタ情報登録ロジック
     */
    public UpdateEntryLogic getUpdateEntryLogic() {
        return updateEntryLogic;
    }

    /**
     * 全国店長勉強会マスタ情報登録ロジックを設定する。
     * @param updateEntryLogic 全国店長勉強会マスタ情報登録ロジック
     */
    public void setUpdateEntryLogic(UpdateEntryLogic updateEntryLogic) {
        this.updateEntryLogic = updateEntryLogic;
    }    
}