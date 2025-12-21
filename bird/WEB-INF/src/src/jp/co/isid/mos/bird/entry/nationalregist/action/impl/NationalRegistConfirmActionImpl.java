package jp.co.isid.mos.bird.entry.nationalregist.action.impl;

import jp.co.isid.mos.bird.entry.nationalregist.action.NationalRegistConfirmAction;
import jp.co.isid.mos.bird.entry.nationalregist.common.NationalRegistConstants;
import jp.co.isid.mos.bird.entry.nationalregist.dto.NationalRegistDto;
import jp.co.isid.mos.bird.entry.nationalregist.logic.UpdateEntryLogic;

/**
 * 全国大会マスタ登録 確認アクション
 *
 * @author xjung
 */
public class NationalRegistConfirmActionImpl implements NationalRegistConfirmAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BEN014A08";

    /** 登録アクションID */
    public static final String regist_ACTION_ID = "BEN014A09";

    /** 戻るアクションID */
    public static final String back_ACTION_ID = "BEN014A10";

    /** 全国大会マスタ登録情報DTO */
    private NationalRegistDto nationalRegistDto;

    /** 全国大会マスタ情報登録ロジック */
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
        if (!getNationalRegistDto().isValidSessionKey()) {
            return NationalRegistConstants.VIEW_ID_ERR;
        }

        // 全国大会マスタ情報を登録
        getUpdateEntryLogic().execute(getNationalRegistDto());

        // 全国大会マスタ登録情報を削除
        getNationalRegistDto().clear();

        // 編集モード設定
        getNationalRegistDto().setEditMode(NationalRegistConstants.EDIT_MODE_INIT);

        // 条件画面へ遷移
        return NationalRegistConstants.VIEW_ID_SELECT;
    }

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getNationalRegistDto().isValidSessionKey()) {
            return NationalRegistConstants.VIEW_ID_ERR;
        }

        // 対象処理が削除の場合
        if (getNationalRegistDto().getEditMode() == NationalRegistConstants.EDIT_MODE_DELETE) {
            // 編集モード設定
            getNationalRegistDto().setEditMode(NationalRegistConstants.EDIT_MODE_RETURN);

            // 条件画面へ遷移
            return NationalRegistConstants.VIEW_ID_SELECT;
        }

        // 編集画面へ遷移
        return NationalRegistConstants.VIEW_ID_EDIT;
    }

    /**
     * 全国大会マスタ登録情報DTOを取得する。
     * @return 全国大会マスタ登録情報DTO
     */
    public NationalRegistDto getNationalRegistDto() {
        return nationalRegistDto;
    }

    /**
     * 全国大会マスタ登録情報DTOを設定する。
     * @param nationalRegistDto 全国大会マスタ登録情報DTO
     */
    public void setNationalRegistDto(NationalRegistDto nationalRegistDto) {
        this.nationalRegistDto = nationalRegistDto;
    }

    /**
     * 全国大会マスタ情報登録ロジックを取得する。
     * @return 全国大会マスタ情報登録ロジック
     */
    public UpdateEntryLogic getUpdateEntryLogic() {
        return updateEntryLogic;
    }

    /**
     * 全国大会マスタ情報登録ロジックを設定する。
     * @param updateEntryLogic 全国大会マスタ情報登録ロジック
     */
    public void setUpdateEntryLogic(UpdateEntryLogic updateEntryLogic) {
        this.updateEntryLogic = updateEntryLogic;
    }    
}