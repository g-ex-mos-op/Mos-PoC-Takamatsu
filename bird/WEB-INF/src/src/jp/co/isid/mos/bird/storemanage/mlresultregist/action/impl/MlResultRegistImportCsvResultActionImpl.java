/*
 * 作成日: 2006/07/21
 *
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistImportCsvResultAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.EntryDate;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetTargetMLLogic;

/**
 * CSV取込結果Action
 * 
 * @author xyuchida
 */
public class MlResultRegistImportCsvResultActionImpl implements
        MlResultRegistImportCsvResultAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BSM008A71";
    public static final String back_ACTION_ID       = "BSM008A72";

    // マスターライセンス結果登録DTO
    private MlResultRegistDto mlResultRegistDto;
    // Logic
    private GetTargetMLLogic mlrrGetTargetMLLogic;

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
        return null;
    }

    /**
     * 終了
     * 
     * @return 画面遷移情報
     */
    public String back() {
        // 更新者数更新の為、一覧再取得
        List listTarget = getMlrrGetTargetMLLogic().execute("00", getMlResultRegistDto().getSysDate());
        getMlResultRegistDto().setListTarget(listTarget);
        EntryDate entryDate = (EntryDate) getMlResultRegistDto().getListTarget().get(getMlResultRegistDto().getCondTarget());
        getMlResultRegistDto().setEntryDate(entryDate);
        // CSV取込条件画面へ遷移
        return MlResultRegistCommon.VIEW_ID_CSVIMPORT;
    }

    /**
     * @return mlResultRegistDto を戻します。
     */
    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }

    /**
     * @param mlResultRegistDto 設定する mlResultRegistDto。
     */
    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }

    /**
     * @return mlrrGetTargetMLLogic を戻します。
     */
    public GetTargetMLLogic getMlrrGetTargetMLLogic() {
        return mlrrGetTargetMLLogic;
    }

    /**
     * @param mlrrGetTargetMLLogic 設定する mlrrGetTargetMLLogic。
     */
    public void setMlrrGetTargetMLLogic(GetTargetMLLogic mlrrGetTargetMLLogic) {
        this.mlrrGetTargetMLLogic = mlrrGetTargetMLLogic;
    }
}
