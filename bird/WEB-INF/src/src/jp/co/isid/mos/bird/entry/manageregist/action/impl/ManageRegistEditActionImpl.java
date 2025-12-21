package jp.co.isid.mos.bird.entry.manageregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.entry.manageregist.action.ManageRegistEditAction;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistCommon;
import jp.co.isid.mos.bird.entry.manageregist.common.ManageRegistConstants;
import jp.co.isid.mos.bird.entry.manageregist.dto.ManageRegistDto;
import jp.co.isid.mos.bird.entry.manageregist.entity.UIEntrySelection;
import jp.co.isid.mos.bird.entry.manageregist.logic.CheckEntryLogic;

/**
 * 全国店長勉強会マスタ登録 編集アクション
 *
 * @author xjung
 */
public class ManageRegistEditActionImpl implements ManageRegistEditAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BEN020A05";

    /** 確認アクションID */
    public static final String confirm_ACTION_ID = "BEN020A06";

    /** 戻るアクションID */
    public static final String back_ACTION_ID = "BEN020A07";

    /** 全国店長勉強会マスタ登録情報DTO */
    private ManageRegistDto manageRegistDto;

    /** 全国店長勉強会マスタ情報チェックロジック */
    private CheckEntryLogic checkEntryLogic;

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
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getManageRegistDto().isValidSessionKey()) {
            return ManageRegistConstants.VIEW_ID_ERR;
        }

        // 全国店長勉強会マスタ情報の事前チェック実施
        getCheckEntryLogic().execute(getManageRegistDto());

        // 申込区分名称再設定
        getManageRegistDto().setEntryKbnList(
            resetSelectionName(getManageRegistDto().getEntryKbnList()));

        // 申込区分名称再設定
        getManageRegistDto().setOptionalList(
            resetSelectionName(getManageRegistDto().getOptionalList()));

        // 条件画面へ遷移
        return ManageRegistConstants.VIEW_ID_CONFIRM;
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

        // 編集モード設定
        getManageRegistDto().setEditMode(ManageRegistConstants.EDIT_MODE_RETURN);

        // 条件画面へ遷移
        return ManageRegistConstants.VIEW_ID_SELECT;
    }

    /**
     * セレクション名称を再設定する。
     * @param selectionList セレクションリスト
     * @return セレクションリスト
     */
    private List resetSelectionName(List selectionList) {
        List tempList = new ArrayList();
        // 申込区分名称の画面入力値を取得
        for (int i = 0; i < selectionList.size(); i++) {
            UIEntrySelection info = (UIEntrySelection) selectionList.get(i);
            if (!ManageRegistCommon.isNull(info.getSelectionName())) {
                tempList.add(info.getSelectionName());
            }            
        }
        List newSelectionList = new ArrayList();
        for (int i = 0; i < selectionList.size(); i++) {
            UIEntrySelection info = (UIEntrySelection) selectionList.get(i);
            // セレクション名称設定
            info.setSelectionName(i < tempList.size() ?
                (String) tempList.get(i) : ManageRegistConstants.EMPTY);
            newSelectionList.add(info);
        }
        return newSelectionList;
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
     * 全国店長勉強会マスタ情報チェックロジックを取得する。
     * @return 全国店長勉強会マスタ情報チェックロジック
     */
    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }

    /**
     * 全国店長勉強会マスタ情報チェックロジックを設定する。
     * @param checkEntryLogic 全国店長勉強会マスタ情報チェックロジック
     */
    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }
}