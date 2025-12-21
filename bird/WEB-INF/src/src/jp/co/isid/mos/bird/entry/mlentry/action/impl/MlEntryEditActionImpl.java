package jp.co.isid.mos.bird.entry.mlentry.action.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.common.EntryStaffSearchConst;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto.EntryStaffSearchDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;
import jp.co.isid.mos.bird.entry.mlentry.action.MlEntryEditAction;
import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIStaff;
import jp.co.isid.mos.bird.entry.mlentry.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetEntryInfoLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetPlaceNameLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetStaffInfoLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.MlEntryUtilLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchOnerLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.UpdateEntryInfoLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistIFDto;


/**
 *  マスターライセンス受験申込　編集画面アクションクラス
 * @author Aspac 
 */
public class MlEntryEditActionImpl implements MlEntryEditAction {

    
    /* アクションID */
    public static String initialize_ACTION_ID   = "BEN008A11";
    public static String cancel_ACTION_ID       = "BEN008A12";
    public static String addRow_ACTION_ID       = "BEN008A13";
    public static String addStaff_ACTION_ID     = "BEN008A14";
    public static String editStaff_ACTION_ID    = "BEN008A15";
    public static String regist_ACTION_ID       = "BEN008A16";
    public static String registCancel_ACTION_ID = "BEN008A17";
    
    
    /* DTO */
    // マスタ登録用DTO
    private MlEntryDto mlEntryDto;
    // セッションキー保持DTO
    private SessionKeyDto mlEntrySessionKeyDto;
    // 店検索DTO
    private MiseSearchDto miseSearchDto;
    // スタッフ追加画面
    private StaffRegistIFDto staffRegistIFDto;
    // スタッフ情報    
    private EntryStaffSearchDto entryStaffSearchDto;
    
    /* ロジック */
    // ユーザー所属会社一覧取得ロジック
    private CompanyJohoLogic mlEntryCompanyJohoLogic;
    // 定員チェックロジック
    private CheckEntryNumberLimitLogic mlEntryCheckEntryNumberLimitLogic;
    // 登録内容チェックロジック
    private CheckEntryLogic mlEntryCheckEntryLogic;
    // 研修エントリー情報の更新
    UpdateEntryInfoLogic mlEntryUpdateEntryInfoLogic;
    // 共通ロジック
    private MlEntryUtilLogic mlEntryUtilLogic;
    // エントリー情報の検索・設定
    private SearchEntryLogic mlSearchEntryLogic;
    // エントリーオーナー情報の検索・設定
    private SearchOnerLogic mlSearchOnerLogic;
    // エントリー状況取得ロジック
    private GetEntryInfoLogic getEntryInfoLogic;
    
    // スタッフ情報取得ロジック
    private GetStaffInfoLogic getStaffInfoLogic;
    // 受験地名称取得ロジック
    private GetPlaceNameLogic getPlaceNameLogic;

    
    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        
        //エントリーリスト取得
        List entryList = (List)getMlEntryDto().getListEntryStateRegist();
        
        //スタッフ編集・登録
        if (getStaffRegistIFDto().isActionFlg()) {
            getStaffRegistIFDto().setActionFlg(false);
            
            UIStaff uIStaff = getGetStaffInfoLogic().execute(getStaffRegistIFDto().getStaffId());
            int staffIndex = getMlEntryDto().getAddStaffIndex();
            UIEntryState entryState = (UIEntryState)entryList.get(staffIndex);
            
            // 必須項目
            entryState.setStaffId(uIStaff.getStaffId());
            entryState.setStaffNameKj(uIStaff.getStaffLNameKj().trim() + " " + uIStaff.getStaffFNameKj().trim());
            entryState.setStaffNameKna(uIStaff.getStaffLNameKna().trim() + " " + uIStaff.getStaffFNameKna().trim());
            entryState.setSex(uIStaff.getSex());
            entryState.setMiseCd1(uIStaff.getMiseCd1());                
           	entryState.setMiseNameKj((uIStaff.getMiseNameKj() == null) ? "" : uIStaff.getMiseNameKj().trim());
            
        }
        
        
        //スタッフ選択
        if (getEntryStaffSearchDto().getReturnKind() != 0){
            
            //決定
            if(getEntryStaffSearchDto().getReturnKind() == 1){
                
                int staffIndex = getMlEntryDto().getAddStaffIndex();
                
                MstStaff mstStaff = getEntryStaffSearchDto().getMstStaff();
                UIEntryState entryState = (UIEntryState)entryList.get(staffIndex);
                
                entryState.setStaffId(mstStaff.getStaffId());
                entryState.setStaffNameKj(mstStaff.getStaffLNameKj().trim()+" "+mstStaff.getStaffFNameKj().trim());
                entryState.setStaffNameKna(mstStaff.getStaffLNameKna().trim()+" "+mstStaff.getStaffFNameKna().trim());
                entryState.setSex(mstStaff.getSex());
                entryState.setMiseCd1(mstStaff.getMiseCd1());
                entryState.setMiseNameKj(mstStaff.getMiseCd1Name().trim());
                entryState.setJob(mstStaff.getJob().trim());
                
                //エントリー状況取得
                UIEntryState staffState = getGetEntryInfoLogic().execute(
                        getMlEntryDto().getEntryYear(),
                        getMlEntryDto().getEntryKai(),
                        mstStaff.getStaffId());
                
                entryState.setStateFlg(staffState.getStateFlg());
                entryState.setEmpExpYear(staffState.getEmpExpYear());
                entryState.setEmpExpMonth(staffState.getEmpExpMonth());
                entryState.setPaExpYear(staffState.getPaExpYear());
                entryState.setPaExpMonth(staffState.getPaExpMonth());
                entryState.setReentryBaseYear(staffState.getReentryBaseYear());
                entryState.setAbilityChk(staffState.getAbilityChk());
                entryState.setAbilityFlg(staffState.isAbilityFlg());
                entryState.setExamChk(staffState.getExamChk());
                entryState.setExamFlg(staffState.isExamFlg());
                entryState.setInterviewChk(staffState.getInterviewChk());
                entryState.setInterviewFlg(staffState.isInterviewFlg());
                entryState.setExamNo(staffState.getExamNo());
                entryState.setNote(staffState.getNote());
                entryState.setEntryPlaceCd(staffState.getEntryPlaceCd());
                entryState.setSub1Result(staffState.getSub1Result());
                entryState.setSub2Result(staffState.getSub2Result());
                entryState.setSub3Result(staffState.getSub3Result());
                entryState.setSub1LastYear(staffState.getSub1LastYear());
                entryState.setSub2LastYear(staffState.getSub2LastYear());
                entryState.setSub3LastYear(staffState.getSub3LastYear());
                entryState.setSub1LastKai(staffState.getSub1LastKai());
                entryState.setSub2LastKai(staffState.getSub2LastKai());
                entryState.setSub3LastKai(staffState.getSub3LastKai());
                entryState.setTotalResult(staffState.getTotalResult());
                entryState.setTotalLastYear(staffState.getTotalLastYear());
                entryState.setTotalLastKai(staffState.getTotalLastKai());
                entryState.setEntryCount(staffState.getEntryCount());
                entryState.setBeforeFlg(staffState.getBeforeFlg());
                entryState.setInsertFlg(true);
                
                if(isNull(entryState.getJob())) entryState.setJob(staffState.getJob());
                
            }
            getEntryStaffSearchDto().setReturnKind(0);
            getEntryStaffSearchDto().setMode(EntryStaffSearchConst.MODE_NORMAL);
        }
        
        
        
        // スタッフ取消をされたエンティティを削除
        for (Iterator ite = entryList.iterator(); ite.hasNext();) {
            UIEntryState entity = (UIEntryState) ite.next();
            if(isNull(entity.getStaffId())){
                ite.remove();//エンティティの削除
            }
        }
        //新規エントリーレコード生成
        getMlEntryUtilLogic().makeNewEntryRec(getMlEntryDto());
        
        // エントリー者Noの割り当て
        getMlEntryUtilLogic().addEntryNo(getMlEntryDto().getListEntryStateRegist());
        
        //「エントリー」欄セット
        for (Iterator ite = getMlEntryDto().getListEntryStateRegist().iterator(); ite.hasNext();) {
            UIEntryState entity = (UIEntryState) ite.next();
            
            //エントリー欄表示を設定する
            setExamNoDisp(entity);
        }
        return null;
    }
    
    
    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {
        getMlEntryDto().clear();
    	return MlEntryCommon.VIEW_ID_SELECT;
	}

    /**
     * 入力欄追加
     * 入力欄追加前にチェック、登録処理を行う（登録・終了ボタン押下と同等処理）
     */
    public String addRow() {
        //スタッフ編集・スタッフ追加ボタンフラグクリア
        getMlEntryDto().staffFlgClear();
        // 登録処理
        doRegist();
        
        // エントリー情報の検索・設定
        getSearchEntryLogic().execute(getMlEntryDto());
        
        return MlEntryCommon.VIEW_ID_EDIT;
    }
    

    

    
    /**
     * 登録・終了
     */
    public String regist() {

        //スタッフ編集・スタッフ追加ボタンフラグクリア
        getMlEntryDto().staffFlgClear();
        // 登録処理
        doRegist();
        
        // エントリー情報の検索・設定
        getSearchEntryLogic().execute(getMlEntryDto());
        
        // 更新リストから空データを削除
        getMlEntryUtilLogic().delEntryNoDate(getMlEntryDto());
        
        
        List listEntryState = getMlEntryDto().getListEntryStateRegist();
        
        for (Iterator ite = listEntryState.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            
            // 受験希望地名称を取得
            String placeName = getGetPlaceNameLogic().execute(
                    getMlEntryDto().getEntryCd(),
                    getMlEntryDto().getEntryYear(),
                    getMlEntryDto().getEntryKai(),
                    uiEntryState.getEntryPlaceCd());
            
            uiEntryState.setEntryPlaceName(placeName);
        }
        
        //確認画面の表示を設定する
        for (Iterator ite = getMlEntryDto().getListEntryStateRegist().iterator(); ite.hasNext();) {
            UIEntryState entity = (UIEntryState) ite.next();
            
            //エントリー欄表示を設定する
            setExamNoDisp(entity);
            
            // 能力・筆記・面接ステータス
            entity.setAbilityChkMsg(getChkStateMsg(entity.getAbilityChk()));
            entity.setExamChkMsg(getChkStateMsg(entity.getExamChk()));
            entity.setInterviewChkMsg(getChkStateMsg(entity.getInterviewChk()));
            
        }
        
        return MlEntryCommon.VIEW_ID_CONFIRM;
    }
    
    
    /**
     * エントリー欄表示を設定する
     * @param UIEntryState
     */
    private void setExamNoDisp(UIEntryState entity){
        
        //新規エントリー(採番されていない)
        if (isNull(entity.getExamNo())) {
            entity.setEntryMsg(getMlEntryDto().getExamNewMsg());
        }
        //新規エントリー(採番されている)
        else if(entity.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD)) {
            
            entity.setEntryMsg(getMlEntryDto().getExamNewMsg() + "：" + entity.getExamNo());
        }
        //前回受験番号引継ぎ
        else if(entity.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    entity.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_RECORD)) {
            entity.setEntryMsg(getMlEntryDto().getExamMsg() + entity.getExamNo());
        }
    }
    
    /**
     * 能力・筆記・面接チェックの文言を設定する
     * @param state チェック状況（0～4）
     * @return String チェック状況（受験/免除/受験不可/申込無し）
     */
    private String getChkStateMsg(String state) {
        String stateMsg = "";
        if(MlEntryCommon.CHK_STATE_JYUKEN.equals(state)) {
            stateMsg = MlEntryCommon.CHK_STATE_STR_JYUKEN;
        }
        else if(MlEntryCommon.CHK_STATE_MENJO.equals(state)) {
            stateMsg = MlEntryCommon.CHK_STATE_STR_MENJO;
        }
        else if(MlEntryCommon.CHK_STATE_JYUKEN_FUKA.equals(state)) {
            stateMsg = MlEntryCommon.CHK_STATE_STR_JYUKEN_FUKA;
        }
        else if(MlEntryCommon.CHK_STATE_MIJYUKEN.equals(state)) {
            stateMsg = MlEntryCommon.CHK_STATE_STR_MIJYUKEN;
        }
        
        return stateMsg;
    }
    
    
    /**
     * 登録処理
     */
    private void doRegist() {
        
        getMlEntryDto().setInsupEntryStateNum(getMlEntryDto().getListEntryStateRegist().size());
        
        // エントリーオーナー情報の有無をチェックする
        getSearchOnerLogic().isEntryOnerInfo(getMlEntryDto());
        
        // スタッフIDを取得する(既存データ削除で使用)
        getMlEntryUtilLogic().makeListEntryEnableStaff(getMlEntryDto());
        
        // エントリー状況リストの能力・筆記・面接チェックを設定する
        getMlEntryUtilLogic().makeEntryListChkStates(getMlEntryDto().getListEntryStateRegist());
        
        // 入力チェック処理
        getMlEntryCheckEntryLogic().execute(getMlEntryDto());
        
        // 登録処理
        getMlEntryUpdateEntryInfoLogic().execute(getMlEntryDto());
    }
    
    
    
    /**
     * 申込取消
     */
    public String registCancel() {
        //スタッフ編集・スタッフ追加ボタンフラグクリア
        getMlEntryDto().staffFlgClear();

        return null;
    }
    
    /**
     * 新規スタッフ追加
     */
    public String addStaff() {
        //スタッフ編集・スタッフ追加ボタンフラグクリア
        getMlEntryDto().staffFlgClear();

        // 新規
        getStaffRegistIFDto().setEditMode(1);
        getStaffRegistIFDto().setCompanyCd(getMlEntryDto().getCondCompanyCd());
        getStaffRegistIFDto().setOnerCd(getMlEntryDto().getCondOnerCd());
        getStaffRegistIFDto().setNavigationCase(MlEntryCommon.VIEW_ID_EDIT);
        getStaffRegistIFDto().setInitialFlag(true);
//add start MLレグレーションテスト後修正 inazawa
        getMlEntryDto().setAddStaffFlg(true);
//add end 
        return MlEntryCommon.VIEW_ID_STAFFREGIST;
    }
    
    /**
     * スタッフ編集
     */
    public String editStaff() {
        //スタッフ編集・スタッフ追加ボタンフラグクリア
        getMlEntryDto().staffFlgClear();

        String staffId = getMlEntryDto().getEditStaffId();
        
        if (staffId == null || staffId.equals("")) {
            //スタッフが選択されていない場合は、遷移なし
            return null;
        }
        
        getStaffRegistIFDto().setEditMode(2);
        getStaffRegistIFDto().setStaffId(staffId);
        getStaffRegistIFDto().setNavigationCase(MlEntryCommon.VIEW_ID_EDIT);
        getStaffRegistIFDto().setInitialFlag(true);
//  add start MLレグレーションテスト後修正 inazawa
        getMlEntryDto().setEditStaffFlg(true);
//add end 
        
        return MlEntryCommon.VIEW_ID_STAFFREGIST;
    }
    
    
    /**
     * スタッフ選択
     * 
     * @return　オーナー検索画面
     */
    public String selectStaff() {
        
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getEntryStaffSearchDto().clear();
        getEntryStaffSearchDto().setNavigationCase(MlEntryCommon.VIEW_ID_EDIT);
        getEntryStaffSearchDto().setInitialFlag(true);
        
        getEntryStaffSearchDto().setMode(EntryStaffSearchConst.MODE_MLENTRY);
        getEntryStaffSearchDto().setCompanyCd(getMlEntryDto().getCondCompanyCd());
        getEntryStaffSearchDto().setOnerCd(getMlEntryDto().getCondOnerCd());
        getEntryStaffSearchDto().setEntryYear(getMlEntryDto().getEntryYear());
        getEntryStaffSearchDto().setEntryKai(getMlEntryDto().getEntryKai());
//add start MLレグレーションテスト後修正 inazawa
//        getMlEntryDto().setEditStaffFlg(false);
//        getMlEntryDto().setAddStaffFlg(false);
//add end 
        
        return MlEntryCommon.VIEWID_STAFFSELECT;
    }
    
    
    
    /**
	 * nullチェック
	 */
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
    
    
    public SessionKeyDto getMlEntrySessionKeyDto() {
        return mlEntrySessionKeyDto;
    }
    public void setMlEntrySessionKeyDto(SessionKeyDto sessionKeyDto) {
        this.mlEntrySessionKeyDto = sessionKeyDto;
    }
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }
    public StaffRegistIFDto getStaffRegistIFDto() {
        return staffRegistIFDto;
    }
    public void setStaffRegistIFDto(StaffRegistIFDto staffRegistIFDto) {
        this.staffRegistIFDto = staffRegistIFDto;
    }
    public EntryStaffSearchDto getEntryStaffSearchDto() {
        return entryStaffSearchDto;
    }
    public void setEntryStaffSearchDto(EntryStaffSearchDto entryStaffSearchDto) {
        this.entryStaffSearchDto = entryStaffSearchDto;
    }
    public MlEntryDto getMlEntryDto() {
        return mlEntryDto;
    }
    public void setMlEntryDto(MlEntryDto mlEntryDto) {
        this.mlEntryDto = mlEntryDto;
    }
    
    
    public CompanyJohoLogic getMlEntryCompanyJohoLogic() {
        return mlEntryCompanyJohoLogic;
    }
    public void setMlEntryCompanyJohoLogic(
            CompanyJohoLogic companyJohoLogic) {
        this.mlEntryCompanyJohoLogic = companyJohoLogic;
    }
    public CheckEntryNumberLimitLogic getMlEntryCheckEntryNumberLimitLogic() {
        return mlEntryCheckEntryNumberLimitLogic;
    }
    public void setMlEntryCheckEntryNumberLimitLogic(
            CheckEntryNumberLimitLogic appCheckEntryNumberLimitLogic) {
        this.mlEntryCheckEntryNumberLimitLogic = appCheckEntryNumberLimitLogic;
    }
    public CheckEntryLogic getMlEntryCheckEntryLogic() {
        return mlEntryCheckEntryLogic;
    }
    public void setMlEntryCheckEntryLogic(CheckEntryLogic appCheckEntryLogic) {
        this.mlEntryCheckEntryLogic = appCheckEntryLogic;
    }
    public UpdateEntryInfoLogic getMlEntryUpdateEntryInfoLogic() {
        return mlEntryUpdateEntryInfoLogic;
    }
    public void setMlEntryUpdateEntryInfoLogic(
            UpdateEntryInfoLogic mlEntryUpdateEntryInfoLogic) {
        this.mlEntryUpdateEntryInfoLogic = mlEntryUpdateEntryInfoLogic;
    }
    public MlEntryUtilLogic getMlEntryUtilLogic() {
        return mlEntryUtilLogic;
    }
    public void setMlEntryUtilLogic(
            MlEntryUtilLogic mlEntryUtilLogicImpl) {
        this.mlEntryUtilLogic = mlEntryUtilLogicImpl;
    }
    public SearchEntryLogic getSearchEntryLogic() {
        return mlSearchEntryLogic;
    }
    public void setSearchEntryLogic(
            SearchEntryLogic mlSearchEntryLogicImpl) {
        this.mlSearchEntryLogic = mlSearchEntryLogicImpl;
    }
    public SearchOnerLogic getSearchOnerLogic() {
        return mlSearchOnerLogic;
    }
    public void setSearchOnerLogic(
            SearchOnerLogic mlSearchOnerLogicImpl) {
        this.mlSearchOnerLogic = mlSearchOnerLogicImpl;
    }
    public GetEntryInfoLogic getGetEntryInfoLogic() {
        return getEntryInfoLogic;
    }
    public void setGetEntryInfoLogic(GetEntryInfoLogic getEntryInfoLogic) {
        this.getEntryInfoLogic = getEntryInfoLogic;
    }
    public GetStaffInfoLogic getGetStaffInfoLogic() {
        return getStaffInfoLogic;
    }
    public void setGetStaffInfoLogic(GetStaffInfoLogic getStaffInfoLogic) {
        this.getStaffInfoLogic = getStaffInfoLogic;
    }
    public GetPlaceNameLogic getGetPlaceNameLogic() {
        return getPlaceNameLogic;
    }
    public void setGetPlaceNameLogic(GetPlaceNameLogic getPlaceNameLogic) {
        this.getPlaceNameLogic = getPlaceNameLogic;
    }

    
}