/*
 * 作成日: 2006/11/16
 */
package jp.co.isid.mos.bird.entry.projectplanstatusinfo.action.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.svsearchnew.dto.SvSearchDto;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.action.ConditionAction;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.dto.ProjectPlanStatusInfoDto;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIHonbuTehaiList;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIShukketu;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.entity.UIStatusInfo;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.ConditionLogic;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.CourseAttendanceListLogic;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.HonbuTetaiListLogic;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.impl.CourseAttendanceListLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.logic.impl.HonbuTetaiListLogicImpl;
import jp.co.isid.mos.bird.entry.projectplanstatusinfo.util.ProjectPlanStatusInfoUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
/**
 * 事業方針説明会申込状況確認 条件画面 アクションクラス
 * 
 * @author xamaruyama
 */
public class ConditionActionImpl implements ConditionAction {
    
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A01";
    /* アクションID：SV検索処理 */
    public static final String callSvForm_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A02";
    /* アクションID：戻る処理 */
    public static final String back_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A03";
    /* アクションID：実行（検索）処理 */
    public static final String search_ACTION_ID = ProjectPlanStatusInfoUtil.SCREEN_ID+"A04";
    
    /** DTO【事業方針説明会申込状況確認】*/
    private ProjectPlanStatusInfoDto projectPlanStatusInfoDto;
    /**
     * ロジック【条件項目の取得】
     */
    private ConditionLogic projectPlanStatusInfoConditionLogic;
    /**
     * ロジック【出欠席検索】
     */
    private CourseAttendanceListLogic projectPlanStatusInfoCourseAttendanceListLogic;
    /**
     * ロジック【宿泊検索】
     */
    private HonbuTetaiListLogic projectPlanStatusInfoHonbuTetaiListLogic;

    /** 共通DTO【事業方針説明会申込状況】*/
    private EventListDto eventListDto;
    /** 共通DTO【SV検索】 */
    private SvSearchDto svSearchDto;

    /**
     * 初期化処理
     */
    public String initialize() {
        
        //１．共通DTO【事業方針説明会申込状況】初期フラグが'1'の場合、下記の処理を行う。
        if (getEventListDto().getInitFlag() == EventListDto.INITFLAG_ON) {
            
            //１－１． 複数WindowID設定
            getProjectPlanStatusInfoDto().updateWindowid();
            
            //１－２．DTOの中身を初期化する。  
            getProjectPlanStatusInfoDto().clear();
            
            //１－３．共通画面から値を取得
            getProjectPlanStatusInfoDto().setCompanyCd(getEventListDto().getCompanyCd());
            getProjectPlanStatusInfoDto().setOnerCd(getEventListDto().getOnerCd());
            getProjectPlanStatusInfoDto().setInitFlg(EventListDto.INITFLAG_ON);
            
            //１－４．フレームワークより値を取得
            getProjectPlanStatusInfoDto().setLimit(getBirdUserInfo().isLimit());
            getProjectPlanStatusInfoDto().setUserId(getBirdUserInfo().getUserID());
            getProjectPlanStatusInfoDto().setSysdate(getBirdDateInfo().getSysDate());
            //条件項目の初期設定
            getProjectPlanStatusInfoDto().setTaishouJokenChoice(ProjectPlanStatusInfoUtil.TAISHO_JOKEN_ALLONSER);
            getProjectPlanStatusInfoDto().setTaishouListChoice(ProjectPlanStatusInfoUtil.TAISHO_PROJECTPLAN);
            
            //１－５．ロジック【条件項目の取得】を実行する。
            Map params = new HashMap();
            //パラメーター
            params.put(ConditionLogicImpl.PK_USERINFO, getBirdUserInfo());
            params.put(ConditionLogicImpl.PK_COMPANY_CD, getEventListDto().getCompanyCd());
            params.put(ConditionLogicImpl.PK_ENTRY_CD, getEventListDto().getEntryCd());
            params.put(ConditionLogicImpl.PK_ENTRY_YEAR, getEventListDto().getEntryYear());
            params.put(ConditionLogicImpl.PK_ENTRY_KAI, getEventListDto().getEntryKai());
            params.put(ConditionLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
            Map logigMap = getProjectPlanStatusInfoConditionLogic().execute(params);
            
            //１－６．処理２－３の戻り値から、[[支部情報]]を取得しDTO【事業方針説明会申込状況確認】.支部リストへ設定する。
            getProjectPlanStatusInfoDto().setSibuList((List)logigMap.get(ConditionLogicImpl.RK_LIST_SIBU));
            
            //１－７．処理２－３の戻り値から、[[対象事業方針説明会申込状況情報]]を取得する。
            List sqlKekka = (List)logigMap.get(ConditionLogicImpl.RK_LIST_STATUS_INFO);
            
            //１－８．処理２－５の結果の値を【DTO】対象事業方針説明会申込状況情報エンティティーへ設定する。
            UIStatusInfo uIStatusInfo = null;
            if (sqlKekka != null && sqlKekka.size() > 0) {
                uIStatusInfo = (UIStatusInfo)sqlKekka.get(0);
            }
            getProjectPlanStatusInfoDto().setEntityUIStatusInfo(uIStatusInfo);
            
            //１－９．対象条件プルダウン作成
            List taishouJokenList = ProjectPlanStatusInfoUtil.getListTaishoJoken();
            getProjectPlanStatusInfoDto().setTaishoJokenList(taishouJokenList);
            
            //１－１０．対象プルダウン作成
            List taishouList = ProjectPlanStatusInfoUtil.getListTaisho();
            getProjectPlanStatusInfoDto().setTaishouList(taishouList);        
            
            //１－１１．区分プルダウン作成
            List kbnList = ProjectPlanStatusInfoUtil.getListKbn();
            getProjectPlanStatusInfoDto().setAttendKbn(kbnList);

            //１－１２． 共通DTO【事業方針説明会申込状況】.初期起動フラグを初期値に設定する。
            getEventListDto().setInitFlag(EventListDto.INITFLAG_OFF);
            
        }
        //２．SV選択画面から遷移してきた場合。
        else if(getNewSvSearchDto().getReturnKind() != SvSearchDto.RETURNKIND_INIT){
            //２－１．【DTO】ウィンドウIDにDTO【SV選択】.ウィンドウIDを設定する。
            getProjectPlanStatusInfoDto().setWindowId(getNewSvSearchDto().getWindowId());
            getProjectPlanStatusInfoDto().settingJokenParam();
            //２－２．SVを選択後遷移してきた場合。
            if(getNewSvSearchDto().getReturnKind() == SvSearchDto.RETURNKIND_SELECT){
                //1．SVコードを設定する。
                getProjectPlanStatusInfoDto().setSvCd(getNewSvSearchDto().getSvCd());
                getProjectPlanStatusInfoDto().setPSvCd(getNewSvSearchDto().getSvCd());
            }
            //２－３．DTO【SV選択】.遷移区分を初期値に戻す。
            getNewSvSearchDto().setReturnKind(SvSearchDto.RETURNKIND_INIT);
            //２－４．DTO【SV選択】.クリア処理を実行する。
            getNewSvSearchDto().clear();
            //２－５．現ウィンドウID の保管データから【DTO】検索対象条件項目へ値の設定を行う。
        }
        return null;
    }
    /**
     * SV検索ボタン処理
     * 
     * @return SV検索フォームViewID
     */
    public String callSvForm()  {
        //１．SV検索フォームViewIDをリターン
        return callingSvForm(ProjectPlanStatusInfoUtil.VIEW_ID_CONDITION);
    }
    /**
     * SV検索フォーム呼び出し処理
     * 
     * @param viewId 遷移元情報
     * @return
     */
    public String callingSvForm(String viewId)  {
        //１．遷移元情報を設定
        getNewSvSearchDto().setNavigationCase(viewId);
        //２．初期化
        getNewSvSearchDto().setInitFlag(true);
        //３．複数WindowID
        getNewSvSearchDto().setWindowId(getProjectPlanStatusInfoDto().getWindowId());
        //４．会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getProjectPlanStatusInfoDto().getCompanyCd());
        getNewSvSearchDto().setRCompanyCdList(listCompany);

        //５．現ウィンドウID の検索対象条件項目値の保管を行う。
        getProjectPlanStatusInfoDto().holdJokenParam();
        //６．画面遷移情報設定
        return ProjectPlanStatusInfoUtil.VIEW_ID_SVSEARCH;
    }

 
    /**
     * 画面の｢戻る｣ボタンを押した場合
     */
    public String back(){
        getProjectPlanStatusInfoDto().clear();
        getEventListDto().setReturnKind(EventListDto.RETURNKIND_BACK);
        return ProjectPlanStatusInfoUtil.VIEW_ID_COMMONCONDITION;
    }
    /**
     * 実行ボタン処理
     * 
     */
    public String search() {
        //１．事前条件処理を行う。
        validate();
        //区分データを初期化する。
        getProjectPlanStatusInfoDto().setAttendKbnChoice(null);
        
        //【DTO】条件項目『対象』
        String targetTaisho = getProjectPlanStatusInfoDto().getTaishouListChoice();
        String viewId = null;
        //２．【DTO】条件項目『対象』の値が”事業方針説明会”or ”懇親会”or ”共栄会定時総会”の場合、下記の処理を行う。
        if (targetTaisho.equals(ProjectPlanStatusInfoUtil.TAISHO_PROJECTPLAN)
                || targetTaisho.equals(ProjectPlanStatusInfoUtil.TAISHO_KONSIN)
                || targetTaisho.equals(ProjectPlanStatusInfoUtil.TAISHO_KYOEIKAI)) {
            //出欠席確認画面出力データ検索処理を実行する。
            viewId = searchAttendance();
            
        }
        //３．【DTO】条件項目『対象』の値が”本部手配宿泊”の場合、下記の処理を行う。
        else if (targetTaisho.equals(ProjectPlanStatusInfoUtil.TAISHO_HONBU_SHUKUHAKU)) {
            //宿泊確認画面出力データ検索処理を実行する。
            viewId = searchLodger();
            
        }
        //４．現ウィンドウID のCSV検索対象条件項目保管処理を行う。
        getProjectPlanStatusInfoDto().holdCsvParam();
        
        return viewId;
    }
    /**
     * 出欠席確認画面出力データ検索処理
     * 
     * @return
     */
    private String searchAttendance(){
        //２－１．ロジック【出欠席情報取得処理】を実行し、[[出欠席情報]]と[[オーナー別出欠席情報]]を取得する。
        Map params = new HashMap();
        params.put(CourseAttendanceListLogicImpl.PK_USERINFO, getBirdUserInfo());
        params.put(CourseAttendanceListLogicImpl.PK_ENTITY_COURSE, getProjectPlanStatusInfoDto().getEntityUIStatusInfo());
        params.put(CourseAttendanceListLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
        params.put(CourseAttendanceListLogicImpl.PK_COMPANY_CD, getProjectPlanStatusInfoDto().getCompanyCd());
        params.put(CourseAttendanceListLogicImpl.PK_TAISHOJOKEN, getProjectPlanStatusInfoDto().getTaishouJokenChoice());
        params.put(CourseAttendanceListLogicImpl.PK_TAISHO, getProjectPlanStatusInfoDto().getTaishouListChoice());
        params.put(CourseAttendanceListLogicImpl.PK_SIBU_CD, getProjectPlanStatusInfoDto().getSibuListChoice());
        params.put(CourseAttendanceListLogicImpl.PK_SV_CD, getProjectPlanStatusInfoDto().getSvCd());
        
        Map rparams = getProjectPlanStatusInfoCourseAttendanceListLogic().execute(params);
        //２－２．処理２－１.結果戻り値から[[出欠席情報]]を取得する。
        List listAttendance = (List)rparams.get(CourseAttendanceListLogicImpl.RK_LIST_SIBUSV_ATTENDANCE);
        //２－３．[[出欠席情報]]の取得結果がない場合は下記の処理を行う。
        if (listAttendance == null || listAttendance.size() == 0) {
            //Ⅰ．前回検索結果データをクリアするため、【DTO】検索結果クリア処理を実行する。
            getProjectPlanStatusInfoDto().searchDataClear();
            //Ⅱ．区分プルダウンをデフォルトとして『全て』に設定する。
            getProjectPlanStatusInfoDto().setAttendKbnChoice(ProjectPlanStatusInfoUtil.KBN_ALL);
            //Ⅲ．Exception　MSG【E0102】を発生させる。
            throw new NoResultException();
        }
        //２－４．処理２－２[[出欠席情報]]結果の一番目[出欠席情報]を取得する。
        UIShukketu uIShukketu = (UIShukketu)listAttendance.get(0);

        //２－５．【DTO】へ処理２－４[出欠席情報]から、出席カウント、欠席カウント、未登録オーナー、出席オーナーを設定する。            
        getProjectPlanStatusInfoDto().setOnerAttendanceCnt(uIShukketu.getOnerAttendCnt());
        getProjectPlanStatusInfoDto().setOnerAbsentCnt(uIShukketu.getOnerAbsentCnt());
        getProjectPlanStatusInfoDto().setOnerMitourokuCnt(uIShukketu.getOnerNoregistCnt());
        getProjectPlanStatusInfoDto().setAttendCnt(uIShukketu.getAttendCnt());
        getProjectPlanStatusInfoDto().setAbsentCnt(uIShukketu.getAbsentCnt());
       
        //２－６．処理２－１.結果戻り値から[[オーナー別出欠席情報]]を取得する。
        List listOnerAttendance = (List)rparams.get(CourseAttendanceListLogicImpl.RK_LIST_ONER_ATTENDANCE);         
        if (listOnerAttendance == null || listOnerAttendance.size() == 0) {
            //Ⅰ．前回検索結果データをクリアするため、【DTO】検索結果クリア処理を実行する。
            //Ⅰ-１．出欠席データ検索結果クリア
            getProjectPlanStatusInfoDto().setSibuInfo(null);
            //Ⅰ-２．宿泊データ検索結果クリア
            getProjectPlanStatusInfoDto().setHonbuTehaiList(null);
            getProjectPlanStatusInfoDto().setSingleSum(null);
            getProjectPlanStatusInfoDto().setTwinSum(null); 
            //Ⅱ．区分プルダウンをデフォルトとして『全て』に設定する。
            getProjectPlanStatusInfoDto().setAttendKbnChoice(ProjectPlanStatusInfoUtil.KBN_ALL);
            //Ⅲ．Exception　MSG【E0102】を発生させる。
            throw new NoResultException();
        }
        //２－８．【DTO】へ処理２－６[[オーナー別出欠席情報]]の値を設定する。    
        getProjectPlanStatusInfoDto().setSibuInfo(listOnerAttendance);

        //２－９．区分プルダウンをデフォルトとして『全て』に設定する。
        getProjectPlanStatusInfoDto().setAttendKbnChoice(ProjectPlanStatusInfoUtil.KBN_ALL);
        //２－１０．出欠席確認画面VIEWIDをリターンする。
        return ProjectPlanStatusInfoUtil.VIEW_ID_ATTENDANCE;
    }
    /**
     * 宿泊確認画面出力データ検索処理
     * 
     * @return
     */
    private String searchLodger(){
        //３－１．本部手配宿泊の支部、オーナー等の情報を取得
        Map params = new HashMap();
        params.put(HonbuTetaiListLogicImpl.PK_USERINFO, getBirdUserInfo());
        params.put(HonbuTetaiListLogicImpl.PK_ENTITY_COURSE, getProjectPlanStatusInfoDto().getEntityUIStatusInfo());
        params.put(HonbuTetaiListLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
        params.put(HonbuTetaiListLogicImpl.PK_COMPANY_CD, getProjectPlanStatusInfoDto().getCompanyCd());
        params.put(HonbuTetaiListLogicImpl.PK_TAISHOJOKEN, getProjectPlanStatusInfoDto().getTaishouJokenChoice());
        params.put(HonbuTetaiListLogicImpl.PK_SIBU_CD, getProjectPlanStatusInfoDto().getSibuListChoice());
        params.put(HonbuTetaiListLogicImpl.PK_SV_CD, getProjectPlanStatusInfoDto().getSvCd());
        List honbuTehaiList = getProjectPlanStatusInfoHonbuTetaiListLogic().execute(params);
        
        if (honbuTehaiList == null || honbuTehaiList.size() == 0) {
            getProjectPlanStatusInfoDto().searchDataClear();
            throw new NoResultException();
        }
        
        getProjectPlanStatusInfoDto().setHonbuTehaiList(honbuTehaiList);
        
        int singleSum = 0;
        int twinSum = 0;
        
        for (int i = 0; i < honbuTehaiList.size(); i++) {
            UIHonbuTehaiList uIHonbuTehaiList = (UIHonbuTehaiList) honbuTehaiList.get(i);
            
            if (uIHonbuTehaiList.getSingleCnt() != null && !uIHonbuTehaiList.getSingleCnt().equals("")) {
                singleSum += Integer.parseInt(uIHonbuTehaiList.getSingleCnt());
            }
            
            if (uIHonbuTehaiList.getTwinCnt() != null && !uIHonbuTehaiList.getTwinCnt().equals("")) {
                twinSum += Integer.parseInt(uIHonbuTehaiList.getTwinCnt());
            }
        }
        
        getProjectPlanStatusInfoDto().setSingleSum(String.valueOf(singleSum));
        getProjectPlanStatusInfoDto().setTwinSum(String.valueOf(twinSum));
        
        return ProjectPlanStatusInfoUtil.VIEW_ID_LODGER;
    }
    /**
     * 事前条件処理
     * 
     * １．SVコード設定の場合
     */
    private void validate() {
        //１．SVコード設定の場合
        if(getProjectPlanStatusInfoDto().getTaishouJokenChoice().equals(ProjectPlanStatusInfoUtil.TAISHO_JOKEN_SV)){
            CodeFormatter cdf = new CodeFormatter(8, "00000000");
            cdf.setFormatPattern("00000000");
            String svCd = getProjectPlanStatusInfoDto().getSvCd();
            if(ProjectPlanStatusInfoUtil.isNull(svCd)){
                throw new NoInputException("SVコード", "svCd", 0);
            }
            HankakuVerifier hankakuVerifier = new HankakuVerifier();
            if(!hankakuVerifier.validate(svCd) || svCd.length() > 8){
                throw new GenericMessageException("SVコードは半角数字8桁以内で入力してください。", "svCd", 0);               
            }
            if(svCd != null && svCd.length() > 0){
                svCd = cdf.format(svCd, true);
            }
            getProjectPlanStatusInfoDto().setSvCd(svCd);
        }
    }
    
    
    public ProjectPlanStatusInfoDto getProjectPlanStatusInfoDto() {
        return projectPlanStatusInfoDto;
    }
    
    public void setProjectPlanStatusInfoDto(ProjectPlanStatusInfoDto projectPlanStatusInfoDto) {
        this.projectPlanStatusInfoDto = projectPlanStatusInfoDto;
    }
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }


    public EventListDto getEventListDto() {
        return eventListDto;
    }


    public void setEventListDto(EventListDto eventListDto) {
        this.eventListDto = eventListDto;
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


    /**
     * @return projectPlanStatusInfoConditionLogic を戻します。
     */
    public ConditionLogic getProjectPlanStatusInfoConditionLogic() {
        return projectPlanStatusInfoConditionLogic;
    }


    /**
     * @param projectPlanStatusInfoConditionLogic 設定する projectPlanStatusInfoConditionLogic。
     */
    public void setProjectPlanStatusInfoConditionLogic(
            ConditionLogic projectPlanStatusInfoConditionLogic) {
        this.projectPlanStatusInfoConditionLogic = projectPlanStatusInfoConditionLogic;
    }
    /**
     * @return projectPlanStatusInfoCourseAttendanceListLogic を戻します。
     */
    public CourseAttendanceListLogic getProjectPlanStatusInfoCourseAttendanceListLogic() {
        return projectPlanStatusInfoCourseAttendanceListLogic;
    }
    /**
     * @param projectPlanStatusInfoCourseAttendanceListLogic 設定する projectPlanStatusInfoCourseAttendanceListLogic。
     */
    public void setProjectPlanStatusInfoCourseAttendanceListLogic(
            CourseAttendanceListLogic projectPlanStatusInfoCourseAttendanceListLogic) {
        this.projectPlanStatusInfoCourseAttendanceListLogic = projectPlanStatusInfoCourseAttendanceListLogic;
    }
    /**
     * @return projectPlanStatusInfoHonbuTetaiListLogic を戻します。
     */
    public HonbuTetaiListLogic getProjectPlanStatusInfoHonbuTetaiListLogic() {
        return projectPlanStatusInfoHonbuTetaiListLogic;
    }
    /**
     * @param projectPlanStatusInfoHonbuTetaiListLogic 設定する projectPlanStatusInfoHonbuTetaiListLogic。
     */
    public void setProjectPlanStatusInfoHonbuTetaiListLogic(
            HonbuTetaiListLogic projectPlanStatusInfoHonbuTetaiListLogic) {
        this.projectPlanStatusInfoHonbuTetaiListLogic = projectPlanStatusInfoHonbuTetaiListLogic;
    }

}