package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistAbilityCheckListAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.UpdateInterviewLogic;

/**
 * マスターライセンス結果登録 面接確認画面アクション
 * @author kusama
 */
public class MlResultRegistInterviewConfirmActionImpl implements MlResultRegistAbilityCheckListAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BSM008A51";
    public static final String regist_ACTION_ID     = "BSM008A52";
    public static final String back_ACTION_ID       = "BSM008A53";
    
    /* LOGIC */
    private UpdateInterviewLogic mlrrUpdateInterviewLogic;
    /* DTO */
    private MlResultRegistDto mlResultRegistDto;

    /**
     * 初期処理
     */
    public String initialize() {
        
        return null;
    }
    
    /**
     * 戻る
     */
    public String back() {
        return MlResultRegistCommon.VIEW_ID_INTERVIEW_EDIT;
    }
    
    /**
     * 実行
     */
    public String regist() {
        //登録ロジック実行
        getMlrrUpdateInterviewLogic().execute(getMlResultRegistDto());
        
        return MlResultRegistCommon.VIEW_ID_CONDITION;
    }

    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }

    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }

    public UpdateInterviewLogic getMlrrUpdateInterviewLogic() {
        return mlrrUpdateInterviewLogic;
    }

    public void setMlrrUpdateInterviewLogic(
            UpdateInterviewLogic mlrrUpdateInterviewLogic) {
        this.mlrrUpdateInterviewLogic = mlrrUpdateInterviewLogic;
    }
}
