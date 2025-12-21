package jp.co.isid.mos.bird.entry.nationalregist.action.impl;

import jp.co.isid.mos.bird.entry.nationalregist.action.NationalRegistEditAction;
import jp.co.isid.mos.bird.entry.nationalregist.common.NationalRegistConstants;
import jp.co.isid.mos.bird.entry.nationalregist.dto.NationalRegistDto;
import jp.co.isid.mos.bird.entry.nationalregist.logic.CheckEntryLogic;

/**
 * 全国大会マスタ登録 編集アクション
 *
 * @author xjung
 * 
 * 更新日:2012/06/25 xkinu オプション登録変更(歯抜け)対応
 *    (※修正前は歯抜けになっていた場合は順番を詰めて登録する仕様でした。)
 */
public class NationalRegistEditActionImpl implements NationalRegistEditAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BEN014A05";

    /** 確認アクションID */
    public static final String confirm_ACTION_ID = "BEN014A06";

    /** 戻るアクションID */
    public static final String back_ACTION_ID = "BEN014A07";

    /** 全国大会マスタ登録情報DTO */
    private NationalRegistDto nationalRegistDto;

    /** 全国大会マスタ情報チェックロジック */
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
     * 
     * 更新日:2012/06/25 xkinu オプション登録変更(歯抜け)対応
     *                         (歯抜け)詰め処理を削除しました。
     */
    public String confirm() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getNationalRegistDto().isValidSessionKey()) {
            return NationalRegistConstants.VIEW_ID_ERR;
        }
        // 全国大会マスタ情報の事前チェック実施
        getCheckEntryLogic().execute(getNationalRegistDto());
        // 条件画面へ遷移
        return NationalRegistConstants.VIEW_ID_CONFIRM;
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

        // 編集モード設定
        getNationalRegistDto().setEditMode(NationalRegistConstants.EDIT_MODE_RETURN);

        // 条件画面へ遷移
        return NationalRegistConstants.VIEW_ID_SELECT;
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
     * 全国大会マスタ情報チェックロジックを取得する。
     * @return 全国大会マスタ情報チェックロジック
     */
    public CheckEntryLogic getCheckEntryLogic() {
        return checkEntryLogic;
    }

    /**
     * 全国大会マスタ情報チェックロジックを設定する。
     * @param checkEntryLogic 全国大会マスタ情報チェックロジック
     */
    public void setCheckEntryLogic(CheckEntryLogic checkEntryLogic) {
        this.checkEntryLogic = checkEntryLogic;
    }
}