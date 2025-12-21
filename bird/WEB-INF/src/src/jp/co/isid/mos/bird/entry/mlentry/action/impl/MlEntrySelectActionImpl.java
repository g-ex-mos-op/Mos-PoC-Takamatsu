package jp.co.isid.mos.bird.entry.mlentry.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.entry.mlentry.action.MlEntrySelectAction;
import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.mlentry.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetMlListLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.GetPlaceListLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.MlEntryUtilLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchOnerLogic;
import jp.co.isid.mos.bird.entry.mlentry.logic.UserMiseJohoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * マスターライセンス受験申込 条件画面アクションクラス
 * @author Aspac
 */
public class MlEntrySelectActionImpl implements MlEntrySelectAction {

    /* アクションID */
    public static String initialize_ACTION_ID     = "BEN008A01";
    public static String regist_ACTION_ID         = "BEN008A02";

    /* DTO */
    private MlEntryDto mlEntryDto;
    private SessionKeyDto mlEntrySessionKeyDto;
    private MiseSearchDto miseSearchDto;
    
    /* ロジック */
    private GetMlListLogic mlEntryGetMlListLogic;
    private CompanyJohoLogic mlEntryCompanyJohoLogic;
    private UserMiseJohoLogic mlEntryUserMiseJohoLogicImpl;
    private CheckEntryNumberLimitLogic mlEntryCheckEntryNumberLimitLogic;
    private MlEntryUtilLogic mlEntryUtilLogic;
    private SearchOnerLogic mlSearchOnerLogic;
    private SearchEntryLogic mlSearchEntryLogic;
    private GetPlaceListLogic getPlaceListLogic;
    
    
    /**
     * マスターライセンス選択インデックス
     */
    private int index;

    /**
     * マスターライセンス選択インデックスを取得する
     * @return index
     */
    public int getIndex() {
        return index;
    }
    /**
     * マスターライセンス選択インデックスを設定する
     * @param index
     */
    public void setIndex(int index) {
        this.index = index;
    }

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {

        // MstUser取得
        MstUser mstUser = getBirdUserInfo().getMstUser();
        // ユーザータイプ判別
        String userTypeCd = mstUser.getUserTypeCd();
        // システム日付取得
        String sysdate = getBirdDateInfo().getSysDate();
        
        // システム日付の翌日日付取得
        String sysnextdate = "";
        try{
            sysnextdate = DateManager.getNextDate(sysdate, 1);
        }
        catch (Exception e) {
            throw new FtlSystemException("翌日日付取得時に");
        }
        
        // 初期処理
		if (getPullDownMenuDto().isClearFlg()) {
            
            getMlEntryDto().allClear();
            getMlEntryDto().setBirdDateInfo(getBirdDateInfo());
            getMlEntryDto().setBirdUserInfo(getBirdUserInfo());
            getMlEntryDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            
            // ユーザータイプが「オーナー」の場合、保有店舗一覧を取得
            if (MlEntryCommon.USER_TYPE_CD_ONER.equals(userTypeCd)) {
                List listOner = getBirdUserInfo().getUserOner();
                for (Iterator ite = listOner.iterator(); ite.hasNext();) {
                    UIUserOner uiUserOner = (UIUserOner) ite.next();
                    if ("00".equals(uiUserOner.getCompanyCd())) {
                        getMlEntryDto().setCondOnerCd(uiUserOner.getOnerCd());
                    }
                }
            }
            else {
                getMlEntryDto().setCondOnerCd("");
            }
            
            getPullDownMenuDto().setClearFlg(false);
		}
        
		List listCompany = getMlEntryCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
		getMlEntryDto().setListCompany(listCompany);
        getMlEntryDto().setCondCompanyCd(MlEntryCommon.COMPANY_CD_MOS);
        
        // マスターライセンス一覧取得
        List listMlList = getMlEntryGetMlListLogic()
                                .execute(sysdate, sysnextdate, 
                                         MlEntryCommon.ENTRYCD_LICENSE,
                                         getMlEntryDto().getCondOnerCd(),
                                         getMlEntryDto().getUserTypeCd());
        getMlEntryDto().setListEntryMst(listMlList);
        
        if(mlEntryDto.getListEntryMst() == null || mlEntryDto.getListEntryMst().size() <= 0 ) {
            throw new NotExistException("登録されたマスタライセンス", "", "");
        }
        
        if(isNull(getMlEntryDto().getDispSecurityInfo())) {
            // マスタライセンスの初期選択を設定
            getMlEntryDto().setSelectIndex(getDefaultIndex());
        }
        
        
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            getMlEntryDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
        
        return null;
    }

    /**
     * 実行
     */
    public String regist() {
        
        if (MlEntryCommon.USER_TYPE_CD_ONER.equals(getMlEntryDto().getUserTypeCd())
                && isNull(getMlEntryDto().getDispSecurityInfo())) {
            
            int selectIndex = getMlEntryDto().getSelectIndex();
            UIEntryMst uiEntryMst = (UIEntryMst) getMlEntryDto().getListEntryMst().get(selectIndex);
            //セキュリティーメッセージをアラート表示する。
            if ("1".equals(uiEntryMst.getEditFlg())) {
                getMlEntryDto().setDispSecurityInfo("1");
                return null;
            }
        }
        
        
        // 選択されたコースの情報をDTOへセット
        setSelectedEntryInfo();
        
        // 選択されたマスターライセンスの情報を検索する
        getSearchOnerLogic().execute(getMlEntryDto());
        getSearchEntryLogic().execute(getMlEntryDto());

        //---2006/08/29 参照モードの場合は、新規エントリー行を追加しない
        if (!(MlEntryCommon.USER_TYPE_CD_ONER.equals(getMlEntryDto().getUserTypeCd()) && isOnerUserDispOnly())) {        
            // 新規エントリーレコード生成
            getMlEntryUtilLogic().makeNewEntryRec(getMlEntryDto());
        }
        
        // 受験希望地一覧プルダウン用リスト作成
        makePlaceList();
        
        // オーナーユーザーで下記の条件を満たす場合は、参照モードにする
        // 登録終了日 ＜ システム日付 ＜ 表示終了日
        if (MlEntryCommon.USER_TYPE_CD_ONER.equals(getMlEntryDto().getUserTypeCd()) && isOnerUserDispOnly()) {
            return MlEntryCommon.VIEW_ID_CONFIRM;
        }
        
        //「エントリー」欄セット
        for (Iterator ite = getMlEntryDto().getListEntryStateRegist().iterator(); ite.hasNext();) {
            UIEntryState entity = (UIEntryState) ite.next();
            if (!isNull(entity.getExamNo())) {
                entity.setEntryMsg(getMlEntryDto().getExamMsg() + entity.getExamNo());
            }
            else {
                entity.setEntryMsg(getMlEntryDto().getExamNewMsg());
            }
        }
        
        return MlEntryCommon.VIEW_ID_EDIT;//編集画面
        
    }
    
    /**
     * 受験希望地プルダウン用リスト作成
     */
    private void makePlaceList() {
        
        // 受験希望地一覧の取得
        List listPlace = getGetPlaceListLogic().execute(
                getMlEntryDto().getEntryCd(),
                getMlEntryDto().getEntryYear(),
                getMlEntryDto().getEntryKai());
        
        getMlEntryDto().setListPlace(listPlace);
    }
    
    
    
    /**
     * 編集画面、確認画面に遷移可能なコースの最初のインデックスを取得
     * @return
     */
    private int getDefaultIndex() {
        int index = -1;
        if (getMlEntryDto().getListEntryMst() != null 
                && getMlEntryDto().getListEntryMst().size() > 0) {
            
            for (int i = 0; i < getMlEntryDto().getListEntryMst().size(); i++) {
                UIEntryMst entryMst = (UIEntryMst) getMlEntryDto().getListEntryMst().get(i);
                //本部ユーザーの場合、編集可能フラグ＝１の場合のみ
                if (MlEntryCommon.USER_TYPE_CD_HONBU.equals(getMlEntryDto().getUserTypeCd())
                        && "1".equals(entryMst.getEditFlg())) {
                    index = i;
                    break;
                }
                //オーナーユーザーの場合、編集可能フラグまたは表示可能フラグ＝１の場合のみ
                if (MlEntryCommon.USER_TYPE_CD_ONER.equals(getMlEntryDto().getUserTypeCd())
                        && ("1".equals(entryMst.getEditFlg()) || "1".equals(entryMst.getDisplayFlg()))) {
                    index = i;
                    break;
                }
            }
        }
        
        return index;
    }

    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }
    
    /**
     * オーナーユーザー 照会モード判別
     * @return true:照会モード
     */
    private boolean isOnerUserDispOnly() {
        boolean flg = false;
        // 選択行インデックス
        int selectIndex = getMlEntryDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        UIEntryMst entityMst = (UIEntryMst) getMlEntryDto().getListEntryMst().get(selectIndex);
        
        String sysDate = getMlEntryDto().getSysDate();
        if (sysDate.compareTo(entityMst.getOnerInputDtTo()) > 0
                && sysDate.compareTo(entityMst.getOnerOutputDtTo()) <= 0)
        {
            flg = true;
        }
        return flg;
    }
    
    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm() {
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(MlEntryCommon.VIEW_ID_SELECT);
        miseSearchDto.setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMlEntryDto().getCondCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        return MlEntryCommon.VIEW_ID_MISESEARCH;
    }
    
    
    /**
     * 編集、削除時に選択行のエントリー情報をDTOへセットする
     */
    private void setSelectedEntryInfo() {
        // 選択行インデックス
        int selectIndex = getMlEntryDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        UIEntryMst entityMst = (UIEntryMst) getMlEntryDto().getListEntryMst().get(selectIndex);
        getMlEntryDto().setEntryCd(entityMst.getEntryCd());
        getMlEntryDto().setEntryYear(entityMst.getEntryYear());
        getMlEntryDto().setEntryKai(entityMst.getEntryKai());
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
    

    
    public MlEntryDto getMlEntryDto() {
        return mlEntryDto;
    }
    public void setMlEntryDto(MlEntryDto mlEntryDto) {
        this.mlEntryDto = mlEntryDto;
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
    
    
    public GetMlListLogic getMlEntryGetMlListLogic() {
        return mlEntryGetMlListLogic;
    }
    public void setMlEntryGetMlListLogic(
            GetMlListLogic mlEntryGetMlListLogicImpl) {
        this.mlEntryGetMlListLogic = mlEntryGetMlListLogicImpl;
    }
    public CompanyJohoLogic getMlEntryCompanyJohoLogic() {
        return mlEntryCompanyJohoLogic;
    }
    public void setMlEntryCompanyJohoLogic(
            CompanyJohoLogic mlEntryCompanyJohoLogic) {
        this.mlEntryCompanyJohoLogic = mlEntryCompanyJohoLogic;
    }
    public UserMiseJohoLogic getMlEntryUserMiseJohoLogicImpl() {
        return mlEntryUserMiseJohoLogicImpl;
    }
    public void setMlEntryUserMiseJohoLogicImpl(
            UserMiseJohoLogic mlEntryUserMiseJohoLogicImpl) {
        this.mlEntryUserMiseJohoLogicImpl = mlEntryUserMiseJohoLogicImpl;
    }
    public SearchOnerLogic getSearchOnerLogic() {
        return mlSearchOnerLogic;
    }
    public void setSearchOnerLogic(
            SearchOnerLogic mlSearchOnerLogicImpl) {
        this.mlSearchOnerLogic = mlSearchOnerLogicImpl;
    }
    public SearchEntryLogic getSearchEntryLogic() {
        return mlSearchEntryLogic;
    }
    public void setSearchEntryLogic(
            SearchEntryLogic mlSearchEntryLogicImpl) {
        this.mlSearchEntryLogic = mlSearchEntryLogicImpl;
    }
    public CheckEntryNumberLimitLogic getMlEntryCheckEntryNumberLimitLogic() {
        return mlEntryCheckEntryNumberLimitLogic;
    }
    public void setMlEntryCheckEntryNumberLimitLogic(
            CheckEntryNumberLimitLogic mlEntryCheckEntryNumberLimitLogic) {
        this.mlEntryCheckEntryNumberLimitLogic = mlEntryCheckEntryNumberLimitLogic;
    }
    public MlEntryUtilLogic getMlEntryUtilLogic() {
        return mlEntryUtilLogic;
    }
    public void setMlEntryUtilLogic(
            MlEntryUtilLogic mlEntryUtilLogicImpl) {
        this.mlEntryUtilLogic = mlEntryUtilLogicImpl;
    }
    public GetPlaceListLogic getGetPlaceListLogic() {
        return getPlaceListLogic;
    }
    public void setGetPlaceListLogic(GetPlaceListLogic getPlaceListLogic) {
        this.getPlaceListLogic = getPlaceListLogic;
    }
    
}