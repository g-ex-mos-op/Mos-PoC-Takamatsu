package jp.co.isid.mos.bird.entry.projectplanmstregist.action.impl;

import java.util.HashMap;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.action.EditAction;
import jp.co.isid.mos.bird.entry.projectplanmstregist.dto.ProjectPlanMstRegistDto;
import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.UICourseInfo;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl.CheckInputDataLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 事業方針説明会マスタ登録
 * 編集画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class EditActionImpl implements EditAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A11";
    /* アクションID：戻る */
    public static final String back_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A12";
    /* アクションID：確認 */
    public static final String confirm_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A13";
    /* DTO【プルダウンメニュー】 */
    private PullDownMenuDto pullDownMenuDto;
    /* DTO【事業方針説明会マスタ登録】*/
    private ProjectPlanMstRegistDto dto = null;
    /** ロジック【入力値チェック】*/
    private CheckInputDataLogic checkInputDataLogic = null;
    /** セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();
    
    /**
     * @return ProjectPlanMstRegistDto を戻します。
     */
    public ProjectPlanMstRegistDto getProjectPlanMstRegistDto() {
        return dto;
    }
    /**
     * @param ProjectPlanMstRegistDto 設定する ProjectPlanMstRegistDto。
     */
    public void setProjectPlanMstRegistDto(
            ProjectPlanMstRegistDto dto) {
        this.dto = dto;
    }
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize(){
        return null;
    }
    /**
     * 戻る アクション
     * 
     * １． sessionKey有効チェックを行い、有効でない場合は下記の処理を行う。
     *    １－１． 有効でない場合は操作エラー画面のVIEWIDをリターンする。
     * 
     * ２．初期画面のVIEWIDをリターンする。
     * 
     * @return 初期画面ID
     */
    public String back(){
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return ProjectPlanMstRegistUtil.operationErr_VIEW_ID;
        }
        //２．初期画面のVIEWIDをリターンする。
        return ProjectPlanMstRegistUtil.VIEW_ID_CONDITION;
    }
    /**
     * 確認 アクション
     * 
     * １． sessionKey有効チェックを行い、有効でない場合は下記の処理を行う。
     *    １－１． 有効でない場合は操作エラー画面のVIEWIDをリターンする。
     * 
     * ２．ロジック【入力値チェック】を実行する。
     * 
     * ３．確認画面のVIEWIDをリターンする。
     * 
     * @return 確認画面ID
     */
    public String confirm(){
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return ProjectPlanMstRegistUtil.operationErr_VIEW_ID;
        }
        //２．ロジック【入力値チェック】を実行する。
        Map params = new HashMap();
        UICourseInfo entity = getProjectPlanMstRegistDto().getEntityMstData();
        params.put(CheckInputDataLogicImpl.PK_ENTITY_COURSE, entity);
        params.put(CheckInputDataLogicImpl.PK_READONLY_FLG, new Boolean(getProjectPlanMstRegistDto().isReadOnly()));
        getProjectPlanMstRegistCheckInputDataLogic().execute(params);
        
        //３．確認画面のVIEWIDをリターンする。
        return ProjectPlanMstRegistUtil.VIEW_ID_CONFIRM;
    }
    /**
     * @return projectPlanMstRegistCheckInputDataLogic を戻します。
     */
    public CheckInputDataLogic getProjectPlanMstRegistCheckInputDataLogic() {
        return checkInputDataLogic;
    }
    /**
     * @param projectPlanMstRegistCheckInputDataLogic 設定する projectPlanMstRegistCheckInputDataLogic。
     */
    public void setProjectPlanMstRegistCheckInputDataLogic(
            CheckInputDataLogic logic) {
        this.checkInputDataLogic = logic;
    }
    /**
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * @param pullDownMenuDto 設定する pullDownMenuDto。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    /**
     * 操作エラー判断処理
     * 
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * @return
     */
    private boolean isValidSessionKey(){
        return mkSession.isValidSessionKey( 
                getProjectPlanMstRegistDto().getNowSessionKey()
                  ,  getProjectPlanMstRegistDto().getSessionKey() );
    }
}
