/*
 * 作成日: 2006/11/16
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.action.impl;


import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.action.LodgerAction;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dto.ProjectPlanStatusInfoDto;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
/**
 * 事業方針説明会申込状況確認 アクションクラス
 * 
 * @author xamaruyama
 */
public class LodgerActionImpl implements LodgerAction {
    
    /** アクションID：初期処理 */
    public static final String initialize_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A21";
    /** アクションID：SV検索処理 */
    public static final String callSvForm_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A22";
    /** アクションID：戻る処理 */
    public static final String back_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A23";
    /** アクションID：実行（検索）処理 */
    public static final String search_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A24";
    /** アクション【初期画面】*/
    private ConditionActionImpl projectPlanStatusInfoConditionAction;
    /** 共通DTO【SV検索】 */
    private SvSearchDto svSearchDto;
    /** DTO【事業方針説明会申込状況確認】*/
    private ProjectPlanStatusInfoDto projectPlanStatusInfoDto;
    
    /**
     * @return projectPlanStatusInfoDto を戻します。
     */
    public ProjectPlanStatusInfoDto getProjectPlanStatusInfoDto() {
        return projectPlanStatusInfoDto;
    }
    /**
     * @param projectPlanStatusInfoDto 設定する projectPlanStatusInfoDto。
     */
    public void setProjectPlanStatusInfoDto(
            ProjectPlanStatusInfoDto projectPlanStatusInfoDto) {
        this.projectPlanStatusInfoDto = projectPlanStatusInfoDto;
    }
    /**
     * 初期化処理
     */
    public String initialize() {
        //１．SV選択画面から遷移してきた場合。
        if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
            //１－１．【DTO】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
            getProjectPlanStatusInfoDto().setWindowId(getNewSvSearchDto().getWindowId());
            getProjectPlanStatusInfoDto().settingJokenParam();
            //１－２．SVを選択後遷移してきた場合。
            if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                //1．SVコードを設定する。
                getProjectPlanStatusInfoDto().setSvCd(getNewSvSearchDto().getSvCd());
                getProjectPlanStatusInfoDto().setPSvCd(getNewSvSearchDto().getSvCd());
            }
            //１－３．DTO【SV選択】.遷移区分を初期値に戻す。
            getNewSvSearchDto().setReturnKind(SvSearchDto.RETURNKIND_INIT);
            //１－４．DTO【SV選択】.クリア処理を実行する。
            getNewSvSearchDto().clear();
        }
        return null;
    }
    /**
     * 戻るボタン処理
     */
    public String callSvForm() {
        return getProjectPlanStatusInfoConditionAction().callingSvForm(ProjectPlanStatusInfoUtil.VIEW_ID_LODGER);
    }
    /**
     * 戻るボタン処理
     */
    public String back() {
        return getProjectPlanStatusInfoConditionAction().back();
    }
    /**
     * 再検索ボタン処理
     */
    public String search() {
        return getProjectPlanStatusInfoConditionAction().search();
    }

    /**
     * @return projectPlanStatusInfoConditionAction を戻します。
     */
    public ConditionActionImpl getProjectPlanStatusInfoConditionAction() {
        return projectPlanStatusInfoConditionAction;
    }

    /**
     * @param projectPlanStatusInfoConditionAction 設定する projectPlanStatusInfoConditionAction。
     */
    public void setProjectPlanStatusInfoConditionAction(
            ConditionActionImpl projectPlanStatusInfoConditionAction) {
        this.projectPlanStatusInfoConditionAction = projectPlanStatusInfoConditionAction;
    }
    /**
     * SV検索DTO取得処理
     * @return svSearchDto
     */
    public SvSearchDto getNewSvSearchDto() {
        return svSearchDto;
    }
    
    /**
     * SV検索DTO設定処理
     * 
     * @param svSearchDto
     */
    public void setNewSvSearchDto(SvSearchDto dto) {
        this.svSearchDto = dto;
    }
}