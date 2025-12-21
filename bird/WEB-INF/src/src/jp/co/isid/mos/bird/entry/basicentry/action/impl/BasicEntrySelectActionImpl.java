/*
 * 作成日: 2006/6/16
 */
package jp.co.isid.mos.bird.entry.basicentry.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.entry.basicentry.action.BasicEntrySelectAction;
import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicentry.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.GetBasicListLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.SearchEntryInfoLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.UserMiseJohoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

/**
 * ベーシック研修参加申込 条件画面アクションクラス
 * @author kusama
 */
public class BasicEntrySelectActionImpl implements BasicEntrySelectAction {

    private static Logger logger_ = Logger.getLogger(BasicEntrySelectActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID     = "BEN002A01";
    public static String regist_ACTION_ID         = "BEN002A02";

    /* 汎用研修マスタ登録用DTO */
    private BasicEntryDto basicEntryDto;
    /* セッションキー保持DTO */
    private SessionKeyDto basicEntrySessionKeyDto;
    /* 店検索DTO */
    private MiseSearchDto miseSearchDto;
    
    /* ロジック */
    private GetBasicListLogic basicEntryGetBasicListLogic;
    private SearchEntryInfoLogic basicEntrySearchEntryInfoLogic;
    private CompanyJohoLogic basicEntryCompanyJohoLogic;
    private UserMiseJohoLogic basicEntryUserMiseJohoLogicImpl;
    private CheckEntryNumberLimitLogic basicEntryCheckEntryNumberLimitLogic;
    /* ラヂオ選択index */
    private int index;

	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @return hanyoRegistDto を戻します。
	 */
	public BasicEntryDto getBasicEntryDto() {
		return basicEntryDto;
	}
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @param hanyoRegistDto hanyoRegistDto を設定。
	 */
	public void setBasicEntryDto(BasicEntryDto basicEntryDto) {
		this.basicEntryDto = basicEntryDto;
	}

    /**
     * ラヂオ選択インデックス設定
     * @return index
     */
    public int getIndex() {
        return index;
    }
    /**
     * ラヂオ選択インデックス設定
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
            // Session内の情報をクリア
            clearInfo();
            
            getBasicEntryDto().setBirdDateInfo(getBirdDateInfo());
            getBasicEntryDto().setBirdUserInfo(getBirdUserInfo());
            getBasicEntryDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            
            // ユーザータイプが「オーナー」の場合、保有店舗一覧を取得
            if (BasicEntryCommon.USER_TYPE_CD_ONER.equals(userTypeCd)) {
                List listOner = getBirdUserInfo().getUserOner();
                for (Iterator ite = listOner.iterator(); ite.hasNext();) {
                    UIUserOner uiUserOner = (UIUserOner) ite.next();
                    if ("00".equals(uiUserOner.getCompanyCd())) {
                        getBasicEntryDto().setCondOnerCd(uiUserOner.getOnerCd());
                    }
                }
//                List listMise = getBasicEntryUserMiseJohoLogicImpl().execute(
//                        getBasicEntryDto().getCondCompanyCd(),
//                        getBasicEntryDto().getCondOnerCd(),
//                        getBasicEntryDto().getSysDate());
//                getBasicEntryDto().setListMstMiseOner(listMise);
            }
            else {
                getBasicEntryDto().setCondOnerCd("");
            }

		}

        // 編集画面からの戻り、確認画面から終了時に条件クリア
        if (getBasicEntryDto().isCondClearFlg()) {
            clearInfo();
        }
        
//--- 2006/06/19 企業コード＝モスのみとする
        // ロジック【ユーザー所属管理会社の検索】
//        List listCompany = getBasicEntryCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
//        getBasicEntryDto().setListCompany(listCompany);
//        if (!BasicEntryCommon.USER_TYPE_CD_HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
//            getBasicEntryDto().setCondCompanyCd(((CodCompanyJoho) listCompany.get(0)).getCompanyCd());
//        }
//        else {
//            getBasicEntryDto().setCondCompanyCd("");
//        }
		List listCompany = getBasicEntryCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
		getBasicEntryDto().setListCompany(listCompany);
        getBasicEntryDto().setCondCompanyCd(BasicEntryCommon.COMPANY_CD_MOS);

        // ベーシック研修一覧取得
        List listBasicList = getBasicEntryGetBasicListLogic()
                                .execute(sysdate, sysnextdate, 
                                         BasicEntryCommon.ENTRYCD_BASIC,
                                         getBasicEntryDto().getCondOnerCd(),
                                         getBasicEntryDto().getUserTypeCd());
        getBasicEntryDto().setListBasicListDataInfo(listBasicList);
        
        if (getPullDownMenuDto().isClearFlg() || getBasicEntryDto().isCondClearFlg()) {
            // ラジオボタンの選択状態をクリア
            getBasicEntryDto().setSelectIndex(getDefaultIndex());
            // オーナーユーザー情報セキュリティフラグ クリア
            getBasicEntryDto().setDispSecurityInfo("");
            getPullDownMenuDto().setClearFlg(false);
            getBasicEntryDto().setCondClearFlg(false);
        }
        
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg() || getBasicEntryDto().isCondClearFlg()) {
            getBasicEntryDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }

        return null;
    }

    /**
     * 実行
     */
    public String regist() {
        if (BasicEntryCommon.USER_TYPE_CD_ONER.equals(getBasicEntryDto().getUserTypeCd())
                && isNull(getBasicEntryDto().getDispSecurityInfo())) 
        {
            // 選択行インデックス
            int selectIndex = getBasicEntryDto().getSelectIndex();
            UIEntryMst uiEntryMst = (UIEntryMst) getBasicEntryDto().getListBasicListDataInfo().get(selectIndex);
            if ("1".equals(uiEntryMst.getEditFlg())) {
                getBasicEntryDto().setDispSecurityInfo("1");
                return null;
            }
        }
        
        // 選択されたコースの情報をDTOへセット
        setSelectedEntryInfo();
        // 選択された研修の情報を検索する
        getBasicEntrySearchEntryInfoLogic().execute(getBasicEntryDto());
        // オーナーユーザーで下記の条件を満たす場合は、参照モードにする
        // 登録終了日 ＜ システム日付 ＜ 表示終了日
        if (BasicEntryCommon.USER_TYPE_CD_ONER.equals(getBasicEntryDto().getUserTypeCd())
                && isOnerUserDispOnly()) 
        {
            return BasicEntryCommon.VIEW_ID_CONFIRM;
        }
        
        // 申込定員チェック
        getBasicEntryCheckEntryNumberLimitLogic().execute(getBasicEntryDto(), BasicEntryCommon.NUMBER_CHECK_MODE_SELECT);
        
        // 編集画面へ遷移
        return BasicEntryCommon.VIEW_ID_EDIT;
    }
    
    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm() {
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(BasicEntryCommon.VIEW_ID_SELECT);
        miseSearchDto.setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getBasicEntryDto().getCondCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        return BasicEntryCommon.VIEW_ID_MISESEARCH;
    }
    
    /**
     * 編集画面、確認画面に遷移可能なコースの最初のインデックスを取得
     * @return
     */
    private int getDefaultIndex() {
        int index = -1;
        if (getBasicEntryDto().getListBasicListDataInfo() != null 
                && getBasicEntryDto().getListBasicListDataInfo().size() > 0)
        {
            for (int i = 0; i < getBasicEntryDto().getListBasicListDataInfo().size(); i++) {
                UIEntryMst entryMst = (UIEntryMst) getBasicEntryDto().getListBasicListDataInfo().get(i);
                //本部ユーザーの場合、編集可能フラグ＝１の場合のみ
                if (BasicEntryCommon.USER_TYPE_CD_HONBU.equals(getBasicEntryDto().getUserTypeCd())
                        && "1".equals(entryMst.getEditFlg())) 
                {
                    return i;
                }
                //オーナーユーザーの場合、編集可能フラグまたは表示可能フラグ＝１の場合のみ
                if (BasicEntryCommon.USER_TYPE_CD_ONER.equals(getBasicEntryDto().getUserTypeCd())
                        && ("1".equals(entryMst.getEditFlg()) || "1".equals(entryMst.getDisplayFlg())))
                {
                    return i;
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
        int selectIndex = getBasicEntryDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        UIEntryMst entityMst = (UIEntryMst) getBasicEntryDto().getListBasicListDataInfo().get(selectIndex);
        
        String sysDate = getBasicEntryDto().getSysDate();
        if (sysDate.compareTo(entityMst.getOnerInputDtTo()) > 0
                && sysDate.compareTo(entityMst.getOnerOutputDtTo()) <= 0)
        {
            flg = true;
        }
        return flg;
    }
    
    /**
     * 検索結果クリア
     */
    private void clearResult() {
        getBasicEntryDto().setListEntryDate(null);
        getBasicEntryDto().setListEntryMst(null);
        getBasicEntryDto().setNowEntryCount(0);
        getBasicEntryDto().setStaffBottonIndex(0);
        getBasicEntryDto().setListEntryCount(0);
    }
    /**
     * セッション情報クリア
     */
    private void clearInfo() {
        clearResult();
        getBasicEntryDto().setCondCompanyCd("");
        getBasicEntryDto().setCondMiseCd("");
    }
    
    /**
     * 編集、削除時に選択行のエントリー情報をDTOへセットする
     */
    private void setSelectedEntryInfo() {
        // 選択行インデックス
        int selectIndex = getBasicEntryDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        //UIEntryMst entityMst = (UIEntryMst) getBasicEntryDto().getListEntryMst().get(selectIndex);
        UIEntryMst entityMst = (UIEntryMst) getBasicEntryDto().getListBasicListDataInfo().get(selectIndex);
        getBasicEntryDto().setEntryCd(entityMst.getEntryCd());
        getBasicEntryDto().setEntryYear(entityMst.getEntryYear());
        getBasicEntryDto().setEntryKai(entityMst.getEntryKai());
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
    
    public SessionKeyDto getBasicEntrySessionKeyDto() {
        return basicEntrySessionKeyDto;
    }
    public void setBasicEntrySessionKeyDto(SessionKeyDto sessionKeyDto) {
        this.basicEntrySessionKeyDto = sessionKeyDto;
    }
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }
    public GetBasicListLogic getBasicEntryGetBasicListLogic() {
        return basicEntryGetBasicListLogic;
    }
    public void setBasicEntryGetBasicListLogic(
            GetBasicListLogic basicEntryGetBasicListLogicImpl) {
        this.basicEntryGetBasicListLogic = basicEntryGetBasicListLogicImpl;
    }
    public CompanyJohoLogic getBasicEntryCompanyJohoLogic() {
        return basicEntryCompanyJohoLogic;
    }
    public void setBasicEntryCompanyJohoLogic(
            CompanyJohoLogic basicEntryCompanyJohoLogic) {
        this.basicEntryCompanyJohoLogic = basicEntryCompanyJohoLogic;
    }
    public UserMiseJohoLogic getBasicEntryUserMiseJohoLogicImpl() {
        return basicEntryUserMiseJohoLogicImpl;
    }
    public void setBasicEntryUserMiseJohoLogicImpl(
            UserMiseJohoLogic basicEntryUserMiseJohoLogicImpl) {
        this.basicEntryUserMiseJohoLogicImpl = basicEntryUserMiseJohoLogicImpl;
    }
    public SearchEntryInfoLogic getBasicEntrySearchEntryInfoLogic() {
        return basicEntrySearchEntryInfoLogic;
    }
    public void setBasicEntrySearchEntryInfoLogic(
            SearchEntryInfoLogic basicEntrySearchEntryInfoLogic) {
        this.basicEntrySearchEntryInfoLogic = basicEntrySearchEntryInfoLogic;
    }
    public CheckEntryNumberLimitLogic getBasicEntryCheckEntryNumberLimitLogic() {
        return basicEntryCheckEntryNumberLimitLogic;
    }
    public void setBasicEntryCheckEntryNumberLimitLogic(
            CheckEntryNumberLimitLogic basicEntryCheckEntryNumberLimitLogic) {
        this.basicEntryCheckEntryNumberLimitLogic = basicEntryCheckEntryNumberLimitLogic;
    }
}