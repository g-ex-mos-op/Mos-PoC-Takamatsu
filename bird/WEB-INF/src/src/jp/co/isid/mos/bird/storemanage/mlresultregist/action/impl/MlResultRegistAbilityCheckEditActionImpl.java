package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistAbilityCheckEditAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckAbilityCheckLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.JudgeAbilityCheckLogic;

/**
 * マスターライセンス結果登録 能力チェック登録画面アクション
 * @author kusama
 */
public class MlResultRegistAbilityCheckEditActionImpl implements MlResultRegistAbilityCheckEditAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BSM008A21";
    public static final String confirm_ACTION_ID    = "BSM008A22";
    public static final String back_ACTION_ID       = "BSM008A23";

    /* LOGIC */
    private JudgeAbilityCheckLogic judgeAbilityCheckLogic;
    private CheckAbilityCheckLogic mlrrCheckAbilityCheckLogic;

    /* DTO */
    private MlResultRegistDto mlResultRegistDto;

    /**
     * 初期処理
     */
    public String initialize() {
        return null;
    }

    /**
     * 確認
     */
    public String confirm() {

        // 入力値チェック
        getMlrrCheckAbilityCheckLogic().execute(getMlResultRegistDto());
        // 合格判定
        getJudgeAbilityCheckLogic().execute(getMlResultRegistDto());

        // 確認画面へ遷移
        return MlResultRegistCommon.VIEW_ID_ABILITY_CONFIRM;
    }

    /**
     * 戻る
     */
    public String back() {
        // 一覧画面へ遷移
        return MlResultRegistCommon.VIEW_ID_ABILITY_LIST;
    }

    public JudgeAbilityCheckLogic getJudgeAbilityCheckLogic() {
        return judgeAbilityCheckLogic;
    }

    public void setJudgeAbilityCheckLogic(
            JudgeAbilityCheckLogic judgeAbilityCheckLogic) {
        this.judgeAbilityCheckLogic = judgeAbilityCheckLogic;
    }

    public CheckAbilityCheckLogic getMlrrCheckAbilityCheckLogic() {
        return mlrrCheckAbilityCheckLogic;
    }

    public void setMlrrCheckAbilityCheckLogic(
            CheckAbilityCheckLogic mlrrCheckAbilityCheckLogic) {
        this.mlrrCheckAbilityCheckLogic = mlrrCheckAbilityCheckLogic;
    }

    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }

    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }
}
