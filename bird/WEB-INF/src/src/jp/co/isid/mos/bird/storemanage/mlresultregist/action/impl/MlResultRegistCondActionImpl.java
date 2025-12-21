/*
 * 作成日: 2006/07/18
 * 修正日: 2007/01/16 マスタライセンスPh4対応 
 */
package jp.co.isid.mos.bird.storemanage.mlresultregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.logic.GetSibuTorikomiLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dao.CtlGamenRoleDao;
import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.mlresultregist.action.MlResultRegistCondAction;
import jp.co.isid.mos.bird.storemanage.mlresultregist.common.MlResultRegistCommon;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dao.MSTSibuCategoryInfoDao;
import jp.co.isid.mos.bird.storemanage.mlresultregist.dto.MlResultRegistDto;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.EntryDate;
import jp.co.isid.mos.bird.storemanage.mlresultregist.entity.TrnEntryStatus;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetAbilityCheckStaffListLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetInterviewResultLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetMstInterviewLogic;
import jp.co.isid.mos.bird.storemanage.mlresultregist.logic.GetTargetMLLogic;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * マスターライセンス結果登録
 * 
 * @author kusama
 */
public class MlResultRegistCondActionImpl implements MlResultRegistCondAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID   = "BSM008A01";
    public static final String regist_ACTION_ID       = "BSM008A02";
    public static final String importCsv_ACTION_ID    = "BSM008A03";
    public static final String callOnerForm_ACTION_ID = "BSM008A04";
    public static final String callMiseForm_ACTION_ID = "BSM008A05";

    /** DAO */
    //支部情報取得DAO
    private MSTSibuCategoryInfoDao mlrrMstSibuCategoryInfoDao;
    //汎用画面ロール制御DAO
    private CtlGamenRoleDao ctlGamenRoleDao;

    /** DTO */
    //マスターライセンス結果登録DTO
    private MlResultRegistDto mlResultRegistDto;
    //店検索
    private MiseSearchDto miseSearchDto;
    //オーナー検索DTO
    private OwnerSearchDto ownerSearchDto;

    /** LOGIC */
    private GetTargetMLLogic mlrrGetTargetMLLogic;
    private GetAbilityCheckStaffListLogic mlrrGetAbilityCheckStaffListLogic;
    private GetInterviewResultLogic mlrrGetInterviewResultLogic;
    private GetMstInterviewLogic mlrrGetMstInterviewLogic;
    private GetSibuTorikomiLogic sibuTorikomiLogic;
    private GetGamenRoleLogic gamenRoleLogic;


    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {

        if (getPullDownMenuDto().isClearFlg()) {

            //ユーザータイプコード設定
            String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
            getMlResultRegistDto().setUserTypeCd(userTypeCd);

            //ログインユーザーID、名称設定
            getMlResultRegistDto().setLoginUserId(getBirdUserInfo().getUserID());
            //---2006/09/28 ユーザー名をtrimする
            //getMlResultRegistDto().setLoginUserName(getBirdUserInfo().getMstUser().getUser_name());
            String userNameKj = getBirdUserInfo().getMstUser().getUser_name();
            if (userNameKj != null) {
                userNameKj = userNameKj.trim();
            }
            getMlResultRegistDto().setLoginUserName(userNameKj);

            //システム日付設定
            String sysDate = getBirdDateInfo().getSysDate();
            getMlResultRegistDto().setSysDate(sysDate);

            //----------------------
            // 支部リスト作成
            //----------------------
            List sibuList;
            if (MlResultRegistCommon.USER_TYPE_CD_HONBU.equals(userTypeCd)) {

                //---20070110 変更(マスタライセンスPh4対応)
                //makeSibuList();
                sibuList = makeAreaSibuList();
                
                //DTOにセット
                getMlResultRegistDto().setListSibu(sibuList);
            }

            //----------------------
            // 登録対象一覧取得
            //----------------------
            List listTarget = getMlrrGetTargetMLLogic().execute("00", sysDate);
            getMlResultRegistDto().setListTarget(listTarget);


            //--2007/01/09 一括取込可能ユーザか否か
            boolean flg = isIkkatuTorikomiUser(getBirdUserInfo().getUserID());
            getMlResultRegistDto().setIkkatuTorikomiFlg(flg);

            //後処理
            getPullDownMenuDto().setClearFlg(false);
            getMlResultRegistDto().setClearCond(true);
        }
        
        if (getMlResultRegistDto().isClearCond()) {
            //検索条件のデフォルトセット
            getMlResultRegistDto().setCondType("1");
            getMlResultRegistDto().setCondTarget(0);
            getMlResultRegistDto().setCondOnlyEntry(true);
            getMlResultRegistDto().setClearCond(false);
            if (getMlResultRegistDto().isEmptyListTarget()) {
                throw new NotExistException("対象");
            }
        }

        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            getMlResultRegistDto().setCondMiseCd(getMiseSearchDto().getMiseCd());

            //change start xkhata 20061115 フォーカス不備対応            
            //getMlResultRegistDto().setCondType("2");
            getMlResultRegistDto().setCondType("3");
            //change end
            getMiseSearchDto().setActionFlg(false);
        }

        // オーナ検索戻り値セット
        if (getOwnerSearchDto().isActionFlag()) {
            getMlResultRegistDto().setCondOnerCd(getOwnerSearchDto().getOnerCd());

            //change start xkhata 20061115 フォーカス不備対応            
            //getMlResultRegistDto().setCondType("1");
            getMlResultRegistDto().setCondType("2");
            //change end            

            ownerSearchDto.setActionFlag(false);
        }
        
        // 自画面へ遷移
        return null;
    }

    /**
     * 一括取込処理が可能なユーザか否かを判別する
     * @return true･･･可能なユーザ、false･･･可能なユーザでない
     */
    private boolean isIkkatuTorikomiUser(String userId) {

        //引数用DTO作成
        GamenRoleDto dto = new GamenRoleDto();
        dto.setUserId(userId);
        dto.setGamenId(MlResultRegistCommon.VIEW_ID);
        dto.setBunruiCd(MlResultRegistCommon.BR11_BUNRUI_IKKATU_TORIKOMI);
        
        List list;
        try{
            list = gamenRoleLogic.excute(dto);
        }catch(NotExistException ne){
            return false;
        }
        
        if(list != null && list.size() > 0){
            return true;
        }
        
        return false;
    }

    /**
     * 受験結果登録
     * @return 遷移先
     */
    public String regist() {

        //入力チェック
        validate(getMlResultRegistDto());

        //遷移先
        String viewId = null;

        //---------------------------------------------
        // (選択した)登録対象の情報を取得・DTOにセット
        //---------------------------------------------
        int index = getMlResultRegistDto().getCondTarget();
        EntryDate entryDate = (EntryDate)getMlResultRegistDto().getListTarget().get(index);

        //情報取得
        String entryCd      = entryDate.getEntryCd();
        String entryYear    = entryDate.getEntryYear();
        String entryKai     = entryDate.getEntryKai();
        String targetKamoku = entryDate.getTargetKamoku();

        //DTOにセット
        getMlResultRegistDto().setEntryCd(entryCd);                 //エントリーコード
        getMlResultRegistDto().setEntryYear(entryYear);             //エントリー年
        getMlResultRegistDto().setEntryKai(entryKai);               //エントリー回
        getMlResultRegistDto().setCondTargetKamoku(targetKamoku);   //対象科目
        getMlResultRegistDto().setEntryDate(entryDate);

        //-------------------------------
        // 「能力チェック」を選択した時
        //-------------------------------
        if ("0".equals(targetKamoku)) {

            // 対象者一覧取得
            List listEntryStaff = getMlrrGetAbilityCheckStaffListLogic().execute(getMlResultRegistDto());
            if (listEntryStaff == null || listEntryStaff.isEmpty()) {
                throw new NotExistException("対象者");
            }

            getMlResultRegistDto().setListAbilitiyStaffList(listEntryStaff);
 
            int abilityIndex = -1;
            for (int i = 0, n = listEntryStaff.size(); i < n; i++) {
                TrnEntryStatus trnEntryStatus = (TrnEntryStatus) listEntryStaff.get(i);
                if (!trnEntryStatus.getSub1Result().equals(MlResultRegistCommon.RESULT_EXEMPT)) {
                    abilityIndex = i;
                    break;
                }
            }
            getMlResultRegistDto().setSelectedAbilityCheckIndex(abilityIndex);

            //能力チェック一覧画面へ遷移
            viewId = MlResultRegistCommon.VIEW_ID_ABILITY_LIST;
        }
        //-------------------------------
        // 「面接」を選択した時
        //-------------------------------
        else if ("1".equals(targetKamoku)) {

            // 面接データ取得
            getInterviewData();
            
            //能力チェック対象者選択画面へ遷移
            viewId = MlResultRegistCommon.VIEW_ID_INTERVIEW_EDIT;
        }

        //編集画面遷移フラグOn
        getMlResultRegistDto().setCondToEditFlg(true);

        return viewId;
    }
    
    /**
     * 入力チェック
     */
    private void validate(MlResultRegistDto dto) {

        String condType = dto.getCondType();

        //オーナーが選択されているの時、オーナーコードをチェック
        if(MlResultRegistCommon.COND_TYPE_ONER.equals(condType)){

            String onerCd = dto.getCondOnerCd();

            //必須チェック
            if(onerCd == null || onerCd.trim().length() == 0){
                throw new NoInputException("オーナーコード", "condOnerCd", 0);
            }
            
            // 半角数字
            CodeVerifier codeVerifier = new CodeVerifier();
            if (!codeVerifier.validate(onerCd)) {
                throw new InvalidInputException("オーナーコード", "condOnerCd", 0);
            }
            
            //OKの時 ---> 00000形にフォーマットして、DTOに再セット
            if(onerCd != null && onerCd.length() > 0 && onerCd.length() < 5){
                
                CodeFormatter cdf = new CodeFormatter(5, "00000");
                cdf.setFormatPattern("00000");
                onerCd = cdf.format(onerCd, true);
                dto.setCondOnerCd(onerCd);
            }
        }

        //店舗が選択されているの時、店舗コードをチェック
        if(MlResultRegistCommon.COND_TYPE_MISE.equals(condType)){

            String miseCd = dto.getCondMiseCd();

            //必須チェック
            if(miseCd == null || miseCd.trim().length() == 0){
                throw new NoInputException("店舗コード", "condMiseCd", 0);
            }
            
            // 半角数字
            CodeVerifier codeVerifier = new CodeVerifier();
            if (!codeVerifier.validate(miseCd)) {
                throw new InvalidInputException("店舗コード", "condMiseCd", 0);
            }
            
            //OKの時 ---> 00000形にフォーマットして、DTOに再セット
            if(miseCd != null && miseCd.length() > 0 && miseCd.length() < 5){
                
                CodeFormatter cdf = new CodeFormatter(5, "00000");
                cdf.setFormatPattern("00000");
                miseCd = cdf.format(miseCd, true);
                dto.setCondOnerCd(miseCd);
            }
        }
    }

    /**
     * 合格者一括取込
     */
    public String importCsv() {

        //--2007/01/09 一括取込可能なユーザか否か
        boolean tmp = isIkkatuTorikomiUser(getBirdUserInfo().getUserID());
        if(!tmp){
            throw new CannotAccessException();
        }

        //選択した対象のキー情報をDTOへセット
        int index = getMlResultRegistDto().getCondTarget();
        EntryDate entryDate = (EntryDate) getMlResultRegistDto().getListTarget().get(index);

        getMlResultRegistDto().setEntryCd(entryDate.getEntryCd());
        getMlResultRegistDto().setEntryYear(entryDate.getEntryYear());
        getMlResultRegistDto().setEntryKai(entryDate.getEntryKai());
        getMlResultRegistDto().setEntryDate(entryDate);

        return MlResultRegistCommon.VIEW_ID_CSVIMPORT;
    }
    
    /**
     * 店検索アクション
     */
    public String callMiseForm() {
        getMiseSearchDto().setNavigationCase(MlResultRegistCommon.VIEW_ID_CONDITION);
        getMiseSearchDto().setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMlResultRegistDto().getCondCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        return MlResultRegistCommon.VIEW_ID_MISESEARCH;
    }
    
    /**
     * オーナー検索アクション
     */
    public String callOnerForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(MlResultRegistCommon.VIEW_ID_CONDITION);
        getOwnerSearchDto().setInitFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
// change start xkhata 20061115 オーナー選択画面遷移時会社プルダウン空対応
//        listCompany.add(getBirdUserInfo().getMstUser().getRCompanyCd());
        listCompany.add(getMlResultRegistDto().getCondCompanyCd() );
// change end        
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        return MlResultRegistCommon.VIEW_ID_ONERSEARCH;
    }

// 20070110 削除(マスタライセンスPh4対応)
//    /**
//     * 支部リスト作成
//     */
//    private void makeSibuList() {
//        //---20060718 会社コードはMOS固定
//        List listSibu = getMlrrMstSibuCategoryInfoDao().getSibuInfo(
//                "00", getBirdUserInfo().getUserID(), getBirdUserInfo().isLimit());
//        getMlResultRegistDto().setListSibu(listSibu);
//    }

    /**
     * 支部取込コードでの支部リスト作成
     * (マスタライセンスPh4対応にて作成)
     */
    private List makeAreaSibuList() {
        
        String compCd  = "00";      //会社コードはMOS固定
        String userId  = getBirdUserInfo().getUserID();
        boolean limit = getBirdUserInfo().isLimit();

        //リスト取得
        List sibuList = sibuTorikomiLogic.execute(compCd, userId, limit);

        //返却
        return sibuList;
    }

    /**
     * 面接データ取得
     */
    private void getInterviewData() {

        //マスターライセンス面接結果取得
        List listInterview = getMlrrGetInterviewResultLogic().execute(getMlResultRegistDto());
        getMlResultRegistDto().setListInterviewResult(listInterview);

        //面接マスタ取得
        List listMstInterview = getMlrrGetMstInterviewLogic().execute();
        getMlResultRegistDto().setListMstInterview(listMstInterview);
    }
    
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    private PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

    public MlResultRegistDto getMlResultRegistDto() {
        return mlResultRegistDto;
    }

    public void setMlResultRegistDto(MlResultRegistDto mlResultRegistDto) {
        this.mlResultRegistDto = mlResultRegistDto;
    }

    public MSTSibuCategoryInfoDao getMlrrMstSibuCategoryInfoDao() {
        return mlrrMstSibuCategoryInfoDao;
    }

    public void setMlrrMstSibuCategoryInfoDao(
            MSTSibuCategoryInfoDao mlrrMstSibuCategoryInfoDao) {
        this.mlrrMstSibuCategoryInfoDao = mlrrMstSibuCategoryInfoDao;
    }

    public GetTargetMLLogic getMlrrGetTargetMLLogic() {
        return mlrrGetTargetMLLogic;
    }

    public void setMlrrGetTargetMLLogic(GetTargetMLLogic mlrrGetTargetMLLogic) {
        this.mlrrGetTargetMLLogic = mlrrGetTargetMLLogic;
    }

    public GetAbilityCheckStaffListLogic getMlrrGetAbilityCheckStaffListLogic() {
        return mlrrGetAbilityCheckStaffListLogic;
    }

    public void setMlrrGetAbilityCheckStaffListLogic(
            GetAbilityCheckStaffListLogic mlrrGetAbilityCheckStaffListLogic) {
        this.mlrrGetAbilityCheckStaffListLogic = mlrrGetAbilityCheckStaffListLogic;
    }

    public GetInterviewResultLogic getMlrrGetInterviewResultLogic() {
        return mlrrGetInterviewResultLogic;
    }

    public void setMlrrGetInterviewResultLogic(
            GetInterviewResultLogic mlrrGetInterviewResultLogic) {
        this.mlrrGetInterviewResultLogic = mlrrGetInterviewResultLogic;
    }

    public GetMstInterviewLogic getMlrrGetMstInterviewLogic() {
        return mlrrGetMstInterviewLogic;
    }

    public void setMlrrGetMstInterviewLogic(
            GetMstInterviewLogic mlrrGetMstInterviewLogic) {
        this.mlrrGetMstInterviewLogic = mlrrGetMstInterviewLogic;
    }

    /**
     * @return ctlGamenRoleDao を戻します。
     */
    public CtlGamenRoleDao getCtlGamenRoleDao() {
        return ctlGamenRoleDao;
    }

    /**
     * @param ctlGamenRoleDao 設定する ctlGamenRoleDao。
     */
    public void setCtlGamenRoleDao(CtlGamenRoleDao ctlGamenRoleDao) {
        this.ctlGamenRoleDao = ctlGamenRoleDao;
    }

    /**
     * @return sibuTorikomiLogic を戻します。
     */
    public GetSibuTorikomiLogic getSibuTorikomiLogic() {
        return sibuTorikomiLogic;
    }

    /**
     * @param sibuTorikomiLogic 設定する sibuTorikomiLogic。
     */
    public void setSibuTorikomiLogic(GetSibuTorikomiLogic sibuTorikomiLogic) {
        this.sibuTorikomiLogic = sibuTorikomiLogic;
    }

    /**
     * @return gamenRoleLogic を戻します。
     */
    public GetGamenRoleLogic getGamenRoleLogic() {
        return gamenRoleLogic;
    }

    /**
     * @param gamenRoleLogic 設定する gamenRoleLogic。
     */
    public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
        this.gamenRoleLogic = gamenRoleLogic;
    }
}
