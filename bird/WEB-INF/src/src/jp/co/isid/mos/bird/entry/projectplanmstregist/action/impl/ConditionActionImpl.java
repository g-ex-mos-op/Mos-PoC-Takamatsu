package jp.co.isid.mos.bird.entry.projectplanmstregist.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.action.ConditionAction;
import jp.co.isid.mos.bird.entry.projectplanmstregist.dto.ProjectPlanMstRegistDto;
import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.UICourseInfo;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.CheckDeleteDataLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl.CheckDeleteDataLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl.SearchLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 事業方針説明会マスタ登録
 * 初期画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class ConditionActionImpl implements ConditionAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A01";
    /* アクションID：新規登録 */
    public static final String insert_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A02";
    /* アクションID：編集 */
    public static final String update_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A03";
    /* アクションID：削除 */
    public static final String delete_ACTION_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"A04";
    /* DTO【プルダウンメニュー】 */
    private PullDownMenuDto pullDownMenuDto;
    /* DTO【事業方針説明会マスタ登録】*/
    private ProjectPlanMstRegistDto dto;
    /* ロジック【条件項目情報の取得】*/
    private ConditionLogic conditionLogic;
    /* ロジック【事業方針説明会マスタ情報の検索】*/
    private SearchLogic searchLogic;
    /* ロジック【削除処理不可判断処理】*/
    private CheckDeleteDataLogic checkDeleteDataLogic;
    /* セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();

    /**
     * 初期処理 アクション
     * 
     * １．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
     *    １－１．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
     *    １－２．【DTO】WindowIdの更新を実行する。
     *    １－３．【DTO】クリア処理を実行する。
     *    １－４．複数ウィンドウ制御用セッションKey生成
     *    １－５．処理１－４で生成した複数ウィンドウ制御用セッションKeyを【DTO】へ設定する。
     *    １－６．BIRDユーザー情報からユーザーIDを取得し、【DTO】へ設定する。
     *    １－７．BIRD日付情報からシステム日付を取得し、【DTO】へ設定する。
     *    １－８．BIRD日付情報からアプリ日付を取得し、【DTO】へ設定する。
     * 
     * ２．対象事業方針説明会情報がnullの場合は、下記の処理を行う。
     *    ２－１．ロジック【条件項目情報の取得】を実行する。
     *    ２－２．処理２－１対象事業方針説明会情報を【DTO】へ設定する。
     *    ２－３．処理２－１対象事業方針説明会情報に更新・削除対象のデータがありる場合は、
     *    　　　　【DTO】選択済み対象コースインデックスへデフォルトを設定する。
     *    　　　　直近の説明会をデフォルト値とします。
     * 
     * ３．nullをリターンする。
     * 
     * @return null 画面ID
     */
    public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()){
            //１－１．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
            //１－２．【DTO】WindowIdの更新を実行する。
            getProjectPlanMstRegistDto().updateWindowid();
            //１－３．【DTO】クリア処理を実行する。
            getProjectPlanMstRegistDto().clear();
            //１－４．複数ウィンドウ制御用セッションKey生成
            String key = mkSession._makeSessionKey();
            //１－５．処理１－４で生成した複数ウィンドウ制御用セッションKeyを【DTO】へ設定する。
            getProjectPlanMstRegistDto().setNowSessionKey(key);
            getProjectPlanMstRegistDto().setSessionKey(key);
            //１－６．BIRDユーザー情報からユーザーIDを取得し、【DTO】へ設定する。
            getProjectPlanMstRegistDto().setUserId(getBirdUserInfo().getUserID());
            //１－７．BIRD日付情報からシステム日付を取得し、【DTO】へ設定する。
            String sysDate = getBirdDateInfo().getSysDate();
            getProjectPlanMstRegistDto().setSysDate(sysDate);
            //１－８．BIRD日付情報からアプリ日付を取得し、【DTO】へ設定する。
            getProjectPlanMstRegistDto().setAppDate(getBirdDateInfo().getAppDate());
            
        }
        String sysDate = getProjectPlanMstRegistDto().getSysDate();
        //２．対象事業方針説明会情報がnullの場合は、下記の処理を行う。
        List listCourseInfo = getProjectPlanMstRegistDto().getListCourseInfo();
        if(getProjectPlanMstRegistDto().getListCourseInfo() == null){
            Map params = new HashMap();
            params.put(ConditionLogicImpl.PK_ENTRY_CD, ProjectPlanMstRegistUtil.ENTRY_CD);
            params.put(ConditionLogicImpl.PK_SYSDATE, sysDate);
            //２－１．ロジック【条件項目情報の取得】を実行する。
            listCourseInfo = getProjectPlanMstRegistConditionLogic().execute(params);
            //２－２．処理２－１で取得した対象事業方針説明会情報を【DTO】へ設定する。
            getProjectPlanMstRegistDto().setListCourseInfo(listCourseInfo);
            //２－３. 処理２－１対象事業方針説明会情報が存在する場合下記の処理を行う。
            if(listCourseInfo != null && listCourseInfo.size() > 0){
                String sysNendo = getProjectPlanMstRegistDto().getSysNendo();
                UICourseInfo entity =  ((UICourseInfo)listCourseInfo.get(0));
                //エントリー年
                String entryYear = entity.getEntryYear();
                //Ⅲ－１．下記の条件を満たしている場合、下記の判断処理を行う。
                //   条件１．エントリー年 >= システム年度 
                if(entryYear.compareTo(sysNendo) >=0){
                    getProjectPlanMstRegistDto().setTargetCourseIndex("0");
                }
                else{
                    //編集・削除対象データ存在フラグ’存在しない'に設定。
                    getProjectPlanMstRegistDto().setRegistDataExit(true);
                }
            }
            else{
                //編集・削除対象データ存在フラグ’存在しない'に設定。
                getProjectPlanMstRegistDto().setRegistDataExit(true);
            }
        }
        //３．nullをリターンする。
        return null;
    }

    /**
     * 新規登録 アクション
     * 
     * １． sessionKey有効チェックを行い、有効でない場合は下記の処理を行う。
     *    １－１． 有効でない場合は操作エラー画面のVIEWIDをリターンする。
     *    
     * ２．【DTO】クリア処理を実行する。
     * 
     * ３．新しく[対象事業方針説明会マスタ情報]のエンティティーを生成する。
     * 
     * ４．処理３の登録対象用Listを【DTO】管理マスタリストへ設定する。
     * 
     * ５．編集画面のVIEWIDをリターンする。
     * 
     * @return 編集画面ID
     */
    public String insert() {
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return ProjectPlanMstRegistUtil.operationErr_VIEW_ID;
        }
        //２．【DTO】クリア処理を実行する。
        getProjectPlanMstRegistDto().clear();
        //３．新しく[対象事業方針説明会マスタ情報]のエンティティーを生成する。
        UICourseInfo entity = new UICourseInfo();
        //３－１．委任状へデフォルト文言を設定する。
        entity.setNote(ProjectPlanMstRegistUtil.NOTE_DEFAULT);
        //３－２．注意事項へデフォルト文言を設定する。
        entity.setNotice1(ProjectPlanMstRegistUtil.changeEnterWordDBtoJSF(ProjectPlanMstRegistUtil.NOTICE1_DEFAULT));
        //４．処理３の登録対象用Listを【DTO】管理マスタリストへ設定する。
        getProjectPlanMstRegistDto().setEntityMstData(entity);
        getProjectPlanMstRegistDto().setModeType(ProjectPlanMstRegistUtil.MODETYPE_INSERT);
        //５．編集画面のVIEWIDをリターンする。
        return ProjectPlanMstRegistUtil.VIEW_ID_EDIT;
    }

    /**
     * 編集 アクション
     * 
     * １． sessionKey有効チェックを行い、有効でない場合は下記の処理を行う。
     *    １－１． 有効でない場合は操作エラー画面のVIEWIDをリターンする。
     *    
     * ２．選択対象の【DTO】対象事業方針説明会情報のエンティティーを抜き出す。
     * 
     * ３．処理２[対象事業方針説明会情報]を【DTO】編集対象事業方針説明会情報エンティティーへ設定する。
     *
     * ４．ロジック【事業方針説明会マスタ情報の検索】を実行する。
     *  パラメーター      処理２[対象事業方針説明会情報].エントリーコード
     *                    処理２[対象事業方針説明会情報].エントリー年
     *                    処理２[対象事業方針説明会情報].エントリー回
     * 
     * ５．【DTO】へ処理４の戻り値[[事業方針説明会マスタ]]を設定する。
     * 
     * ６．【DTO】へ処理４の戻り値[[事業方針説明会日付マスタ]]を設定する。
     * 
     * ７．【DTO】へ処理４の戻り値[[事業方針説明会文言マスタ]]を設定する。
     * 
     * ８．【DTO】処理タイプへ'UPDATE'を設定する。
     * 
     * ９．編集画面のVIEWIDをリターンする。
     * 
     * @return 編集画面ID
     */
    public String update() {
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return ProjectPlanMstRegistUtil.operationErr_VIEW_ID;
        }
        //２．選択対象の【DTO】対象事業方針説明会情報のエンティティーを抜き出す。
        UICourseInfo entity = getSelectedEntity();
        //３．処理２[対象事業方針説明会情報]を【DTO】編集対象事業方針説明会情報エンティティーへ設定する。
        getProjectPlanMstRegistDto().setEntityMstData(entity);
        //４～７の処理を行う。
        settingRegistData(entity);
        //８．【DTO】処理タイプへ'UPDATE'を設定する。
        getProjectPlanMstRegistDto().setModeType(ProjectPlanMstRegistUtil.MODETYPE_UPDATE);
        //９．編集画面のVIEWIDをリターンする。
        return ProjectPlanMstRegistUtil.VIEW_ID_EDIT;
    }

    /**
     * 削除 アクション
     * 
     * １． sessionKey有効チェックを行い、有効でない場合は下記の処理を行う。
     *    １－１． 有効でない場合は操作エラー画面のVIEWIDをリターンする。
     *    
     * ２．選択対象の【DTO】対象事業方針説明会情報のエンティティーを抜き出す。
     * 
     * ３．処理２[対象事業方針説明会情報]を【DTO】編集対象事業方針説明会情報エンティティーへ設定する。
     *
     * ４．ロジック【削除処理不可判断処理】を実行する。
     *  パラメーター      処理２[対象事業方針説明会情報]
     *  　　　　　　　　　【DTO】システム日付
     * 
     * ５．ロジック【事業方針説明会マスタ情報の検索】を実行する。
     *  パラメーター      処理２[対象事業方針説明会情報].エントリーコード
     *                    処理２[対象事業方針説明会情報].エントリー年
     *                    処理２[対象事業方針説明会情報].エントリー回
     * 
     * ６．【DTO】へ処理４の戻り値[[事業方針説明会マスタ]]を設定する。
     * 
     * ７．【DTO】へ処理４の戻り値[[事業方針説明会日付マスタ]]を設定する。
     * 
     * ８．【DTO】へ処理４の戻り値[[事業方針説明会文言マスタ]]を設定する。
     * 
     * ９．【DTO】処理タイプへ'DELETE'を設定する。
     * 
     * １０．確認画面のVIEWIDをリターンする。
     * 
     * @return 確認(or照会)画面ID
     */
    public String delete() {
        //１． sessionKey有効チェックを行う。
        if (!isValidSessionKey()) {
            //１－１． 有効でない場合は操作エラー画面に遷移
            return ProjectPlanMstRegistUtil.operationErr_VIEW_ID;
        }
        //２．選択対象の【DTO】対象事業方針説明会情報のエンティティーを抜き出す。
        UICourseInfo entity = getSelectedEntity();
        //３．処理２[対象事業方針説明会情報]を【DTO】編集対象事業方針説明会情報エンティティーへ設定する。
        getProjectPlanMstRegistDto().setEntityMstData(entity);
        //４．ロジック【削除処理不可判断処理】を実行する。
        //パラメーター
        Map params = new HashMap();
        params.put(CheckDeleteDataLogicImpl.PK_ENTITY_COURSE, entity);
        params.put(CheckDeleteDataLogicImpl.PK_SYSDATE, getProjectPlanMstRegistDto().getSysDate());
        //ロジック実行
        getProjectPlanMstRegistCheckDeleteDataLogic().execute(params);
        //５～８の処理を行う。
        settingRegistData(entity);
        //９．【DTO】処理タイプへ'DELETE'を設定する。
        getProjectPlanMstRegistDto().setModeType(ProjectPlanMstRegistUtil.MODETYPE_DELETE);
        //１０．確認画面のVIEWIDをリターンする。
        return ProjectPlanMstRegistUtil.VIEW_ID_CONFIRM;

    }
    /**
     * １．ロジック【事業方針説明会マスタ情報の検索】を実行する。
     *  パラメーター      処理２[対象事業方針説明会情報].エントリーコード
     *                    処理２[対象事業方針説明会情報].エントリー年
     *                    処理２[対象事業方針説明会情報].エントリー回
     * 
     * ２．【DTO】へ処理４の戻り値[[事業方針説明会マスタ]]を設定する。
     * 
     * ３．【DTO】へ処理４の戻り値[[事業方針説明会日付マスタ]]を設定する。
     * 
     * ４．【DTO】へ処理４の戻り値[[事業方針説明会文言マスタ]]を設定する。
     * 
     * @param entity　処理２[対象事業方針説明会情報]
     */
    private void settingRegistData(UICourseInfo entity){
        //１．ロジック【事業方針説明会マスタ情報の検索】を実行する。
        //パラメーター
        Map params = new HashMap();
        params.put(SearchLogicImpl.PK_ENTRY_CD, entity.getEntryCd());
        params.put(SearchLogicImpl.PK_ENTRY_YEAR, entity.getEntryYear());
        params.put(SearchLogicImpl.PK_ENTRY_KAI, entity.getEntryKai());
        //ロジック実行
        Map rParam = getProjectPlanMstRegistSearchLogic().execute(params);
        
        //２．【DTO】へ処理４の戻り値[[事業方針説明会マスタ]]を設定する。
        getProjectPlanMstRegistDto().setListMst((List)rParam.get(SearchLogicImpl.RK_LIST_MST));
        //３．【DTO】へ処理４の戻り値[[事業方針説明会日付マスタ]]を設定する。
        getProjectPlanMstRegistDto().setListMstDate((List)rParam.get(SearchLogicImpl.RK_LIST_MST_DATE));
        //４．【DTO】へ処理４の戻り値[[事業方針説明会文言マスタ]]を設定する。
        getProjectPlanMstRegistDto().setListMstNotice((List)rParam.get(SearchLogicImpl.RK_LIST_MST_NOTICE));
        
    }
    /**
     * 選択対象対象事業方針説明会情報エンティティー生成取得処理
     * 
     * 選択対象の【DTO】対象事業方針説明会情報のエンティティーを抜き出す。
     * 新しいエンティティーへ抜き出したエンティティーの値を設定します。
     * @return
     */
    private UICourseInfo getSelectedEntity() {
        List listCourseInfo = getProjectPlanMstRegistDto().getListCourseInfo();
        int selectedIndex = Integer.valueOf(getProjectPlanMstRegistDto().getTargetCourseIndex()).intValue();
        UICourseInfo entityMoto = (UICourseInfo)listCourseInfo.get(selectedIndex);
        UICourseInfo entity = new UICourseInfo();
        //マスタデータ設定
        entity.setEntryCd(entityMoto.getEntryCd());
        entity.setEntryYear(entityMoto.getEntryYear());
        entity.setEntryKai(entityMoto.getEntryKai());
        entity.setEntryTitle(entityMoto.getEntryTitle());
        entity.setEntryPlace(entityMoto.getEntryPlace());
        entity.setNote(entityMoto.getNote());
        entity.setNumberLimit(entityMoto.getNumberLimit());
        //日付データ設定
        entity.setExecDt(entityMoto.getExecDt());
        entity.setDispFrom(entityMoto.getDispFrom());
        entity.setDispTo(entityMoto.getDispTo());
        entity.setEntryFrom(entityMoto.getEntryFrom());
        entity.setEntryTo(entityMoto.getEntryTo());
        entity.setOnerDispFrom(entityMoto.getOnerDispFrom());
        entity.setOnerDispTo(entityMoto.getOnerDispTo());
        entity.setOnerEntryFrom(entityMoto.getOnerEntryFrom());
        entity.setOnerEntryTo(entityMoto.getOnerEntryTo());
        //文言データ
        entity.setNotice1(ProjectPlanMstRegistUtil.changeEnterWordDBtoJSF(entityMoto.getNotice1()));
        
        return entity;
    }
    /**
     * DTO【事業方針説明会マスタ登録】取得処理
     * @return
     */
    public ProjectPlanMstRegistDto getProjectPlanMstRegistDto() {
        return dto;
    }

    /**
     * DTO【事業方針説明会マスタ登録】設定処理
     * @return
     */
    public void setProjectPlanMstRegistDto(ProjectPlanMstRegistDto dto) {
        this.dto = dto;
    }

    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }

    /**
     * @return conditionLogic を戻します。
     */
    public ConditionLogic getProjectPlanMstRegistConditionLogic() {
        return conditionLogic;
    }

    /**
     * @param conditionLogic 設定する。
     */
    public void setProjectPlanMstRegistConditionLogic(
            ConditionLogic logic) {
        this.conditionLogic = logic;
    }

    /**
     * @return searchLogic を戻します。
     */
    public SearchLogic getProjectPlanMstRegistSearchLogic() {
        return searchLogic;
    }

    /**
     * @param searchLogic 設定する。
     */
    public void setProjectPlanMstRegistSearchLogic(
            SearchLogic logic) {
        this.searchLogic = logic;
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

    /**
     * ロジック【削除処理不可判断処理】取得処理
     * 
     * @return checkDeleteDataLogic を戻します。
     */
    public CheckDeleteDataLogic getProjectPlanMstRegistCheckDeleteDataLogic() {
        return checkDeleteDataLogic;
    }

    /**
     * ロジック【削除処理不可判断処理】設定処理
     * 
     * @param checkDeleteDataLogic 設定する checkDeleteDataLogic。
     */
    public void setProjectPlanMstRegistCheckDeleteDataLogic(CheckDeleteDataLogic logic) {
        this.checkDeleteDataLogic = logic;
    }

}
