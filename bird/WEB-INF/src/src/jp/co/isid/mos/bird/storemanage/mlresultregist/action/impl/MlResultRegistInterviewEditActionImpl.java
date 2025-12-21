package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistAbilityCheckListAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.CheckInterviewLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.JudgeInterviewLogic;

/**
 * マスターライセンス結果登録 能力チェック対象者選択画面アクション
 * @author kusama
 */
public class MlResultRegistInterviewEditActionImpl implements MlResultRegistAbilityCheckListAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BSM008A41";
    public static final String regist_ACTION_ID     = "BSM008A42";
    public static final String back_ACTION_ID       = "BSM008A43";
    
    /* LOGIC */
    private JudgeInterviewLogic mlrrJudgeInterviewLogic;
    private CheckInterviewLogic mlrrCheckInterviewLogic;
    /* DTO */
    private MlResultRegistDto mlResultRegistDto;

    /**
     * 初期処理
     */
    public String initialize() {
        if (getMlResultRegistDto().isCondToEditFlg()) {
            getMlResultRegistDto().setCondToEditFlg(false);
        }
        
        return null;
    }
    
    /**
     * 戻る
     */
    public String back() {
        return MlResultRegistCommon.VIEW_ID_CONDITION;
    }
    
    /**
     * 実行
     */
    public String regist() {
        //入力チェック
        getMlrrCheckInterviewLogic().execute(getMlResultRegistDto());
        //合否判定
        getMlrrJudgeInterviewLogic().execute(getMlResultRegistDto());
        
        return MlResultRegistCommon.VIEW_ID_INTERVIEW_CONFIRM;
    }

    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }

    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }

    public JudgeInterviewLogic getMlrrJudgeInterviewLogic() {
        return mlrrJudgeInterviewLogic;
    }

    public void setMlrrJudgeInterviewLogic(
            JudgeInterviewLogic mlrrJudgeInterviewLogic) {
        this.mlrrJudgeInterviewLogic = mlrrJudgeInterviewLogic;
    }

    public CheckInterviewLogic getMlrrCheckInterviewLogic() {
        return mlrrCheckInterviewLogic;
    }

    public void setMlrrCheckInterviewLogic(
            CheckInterviewLogic mlrrCheckInterviewLogic) {
        this.mlrrCheckInterviewLogic = mlrrCheckInterviewLogic;
    }

}
