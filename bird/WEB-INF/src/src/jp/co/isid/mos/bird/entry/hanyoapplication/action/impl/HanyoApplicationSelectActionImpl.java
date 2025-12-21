/*
 * 作成日: 2006/6/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.action.HanyoApplicationSelectAction;
import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryDate;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchEntryInfoLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.UserMiseJohoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 研修参加申込　条件画面アクションクラス
 * @author kusama
 */
public class HanyoApplicationSelectActionImpl implements HanyoApplicationSelectAction {

    /* アクションID */
    public static String initialize_ACTION_ID     = "BEN005A01";
    public static String changePullDown_ACTION_ID = "BEN005A02";
    public static String regist_ACTION_ID         = "BEN005A03";

    /* 汎用研修マスタ登録用DTO */
    private HanyoApplicationDto hanyoApplicationDto;
    /* セッションキー保持DTO */
    private SessionKeyDto hanyoRegistSessionKeyDto;
    /* 店検索DTO */
    private MiseSearchDto miseSearchDto;
    
    /* ロジック */
    // エントリーマスタ管理の検索ロジック
    private SearchEntryLogic searchEntryLogic;
    // 研修エントリー情報の検索ロジック
    private SearchEntryInfoLogic hanyoAppSearchEntryInfoLogic;
    // ユーザー所属会社一覧取得ロジック
    private CompanyJohoLogic hanyoApplicationCompanyJohoLogic;
    // オーナー保有店舗一覧取得ロジック
    private UserMiseJohoLogic hanyoAppUserMiseJohoLogic;
    private CheckEntryNumberLimitLogic hanyoAppCheckEntryNumberLimitLogic;
    /* ラヂオ選択index */
    private int index;

	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @return hanyoRegistDto を戻します。
	 */
	public HanyoApplicationDto getHanyoApplicationDto() {
		return hanyoApplicationDto;
	}
	/**
	 * 汎用研修マスタ登録用DTOの設定
	 * @param hanyoRegistDto hanyoRegistDto を設定。
	 */
	public void setHanyoApplicationDto(HanyoApplicationDto hanyoApplicationDto) {
		this.hanyoApplicationDto = hanyoApplicationDto;
	}

	/**
	 * エントリーマスタ管理の検索ロジックの設定
	 * @return searchEntryLogic を戻します。
	 */
	public SearchEntryLogic getSearchEntryLogic() {
		return searchEntryLogic;
	}
	/**
	 * エントリーマスタ管理の検索ロジックの設定
	 * @param searchEntryLogic searchEntryLogic を設定。
	 */
	public void setSearchEntryLogic(SearchEntryLogic searchEntryLogic) {
		this.searchEntryLogic = searchEntryLogic;
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
		// 初期処理
		if (getPullDownMenuDto().isClearFlg()) {
            // Session内の情報をクリア
            clearInfo();
            
            getHanyoApplicationDto().setBirdDateInfo(getBirdDateInfo());
            getHanyoApplicationDto().setBirdUserInfo(getBirdUserInfo());
            getHanyoApplicationDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            
			// １．ロジック【エントリーマスタ管理の検索】を実行する。
            getHanyoApplicationDto().setEntryCd(HanyoApplicationCommon.ENTRYCD_SHUTTYO);
            // ユーザータイプが「オーナー」の場合、保有店舗一覧を取得
            if (HanyoApplicationCommon.USER_TYPE_CD_ONER.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
                List listOner = getBirdUserInfo().getUserOner();
                for (Iterator ite = listOner.iterator(); ite.hasNext();) {
                    UIUserOner uiUserOner = (UIUserOner) ite.next();
                    if ("00".equals(uiUserOner.getCompanyCd())) {
                        getHanyoApplicationDto().setCondOnerCd(uiUserOner.getOnerCd());
                    }
                }
            }
            else {
                getHanyoApplicationDto().setCondOnerCd("");
            }
			changePullDown();
			
            // ラジオボタンの選択状態をクリア
            getHanyoApplicationDto().setSelectIndex(getDefaultIndex());
            // オーナーユーザー情報セキュリティフラグ クリア
            getHanyoApplicationDto().setDispSecurityInfo("");
            
			getPullDownMenuDto().setClearFlg(false);
		}
//--- 2006/06/19 企業コード＝モスのみとする
        // ロジック【ユーザー所属管理会社の検索】
//        List listCompany = getHanyoApplicationCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
//        getHanyoApplicationDto().setListCompany(listCompany);
//        if (!HanyoApplicationCommon.USER_TYPE_CD_HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
//            getHanyoApplicationDto().setCondCompanyCd(((CodCompanyJoho) listCompany.get(0)).getCompanyCd());
//        }
//        else {
//            getHanyoApplicationDto().setCondCompanyCd("");
//        }
        List listCompany = getHanyoApplicationCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
        getHanyoApplicationDto().setListCompany(listCompany);
        getHanyoApplicationDto().setCondCompanyCd(BasicEntryCommon.COMPANY_CD_MOS);
        
        // 登録処理終了後の初期化処理
        if (getHanyoApplicationDto().isCondClearFlg()) {
            getHanyoApplicationDto().setCondMiseCd("");
            getHanyoApplicationDto().setEntryCd(HanyoApplicationCommon.ENTRYCD_SHUTTYO);
            getHanyoApplicationDto().setCondClearFlg(false);
            changePullDown();
            // ラジオボタンの選択状態をクリア
            getHanyoApplicationDto().setSelectIndex(getDefaultIndex());
            // オーナーユーザー情報セキュリティフラグ クリア
            getHanyoApplicationDto().setDispSecurityInfo("");
            getHanyoApplicationDto().setListEntryCount(0);
            getHanyoApplicationDto().setEntryAfterCount(0);
        }
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            getHanyoApplicationDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }

        return null;
    }

    /**
     * 編集画面、確認画面に遷移可能なコースの最初のインデックスを取得
     * @return
     */
    private int getDefaultIndex() {
        int index = -1;
        if (getHanyoApplicationDto().getListEntryMst() != null 
                && getHanyoApplicationDto().getListEntryMst().size() > 0)
        {
            for (int i = 0; i < getHanyoApplicationDto().getListEntryMst().size(); i++) {
                UIEntryMst entryMst = (UIEntryMst) getHanyoApplicationDto().getListEntryMst().get(i);
                for (Iterator ite = getHanyoApplicationDto().getListDispEntryList().iterator(); ite.hasNext();) {
                    UIEntryDate entryDate = (UIEntryDate) ite.next();
                    if (entryMst.getEntryCd().equals(entryDate.getEntryCd())
                            && entryMst.getEntryYear().equals(entryDate.getEntryYear())
                            && entryMst.getEntryKai().equals(entryDate.getEntryKai()))
                    {
                        //本部ユーザーの場合、編集可能フラグ＝１の場合のみ
                        if (HanyoApplicationCommon.USER_TYPE_CD_HONBU.equals(getHanyoApplicationDto().getUserTypeCd())
                                && "1".equals(entryDate.getEditFlg())) 
                        {
                            return i;
                        }
                        //オーナーユーザーの場合、編集可能フラグまたは表示可能フラグ＝１の場合のみ
                        if (HanyoApplicationCommon.USER_TYPE_CD_ONER.equals(getHanyoApplicationDto().getUserTypeCd())
                                && ("1".equals(entryDate.getEditFlg()) || "1".equals(entryDate.getDisplayFlg())))
                        {
                            return i;
                        }
                    }
                }
            }
        }
        
        return index;
    }
    
    /**
     * プルダウン変更
     * @return 画面遷移情報
     */
    public String changePullDown() {
        // 前回の検索結果をクリア
        clearResult();
        
    	// １．ロジック【エントリーマスタ管理の検索】を実行する。
    	// パラメータ　：　プルダウンで選択されたエントリーコード（05：出張特別研修   30：更新研修）
		getSearchEntryLogic().execute(getHanyoApplicationDto());

        // ラジオボタンの選択状態をクリア
        getHanyoApplicationDto().setSelectIndex(getDefaultIndex());
    	
    	return null;
	}

    /**
     * 実行
     */
    public String regist() {
        if (HanyoApplicationCommon.USER_TYPE_CD_ONER.equals(getHanyoApplicationDto().getUserTypeCd())
                && isNull(getHanyoApplicationDto().getDispSecurityInfo())) 
        {
            // 選択行インデックス
            int selectIndex = getHanyoApplicationDto().getSelectIndex();
            UIEntryMst uiEntryMst = (UIEntryMst) getHanyoApplicationDto().getListEntryMst().get(selectIndex);
            if ("1".equals(uiEntryMst.getEditFlg())) {
                getHanyoApplicationDto().setDispSecurityInfo("1");
                return null;
            }
        }
        // 選択されたコースの情報をDTOへセット
        setSelectedEntryInfo();
        // 選択された研修の情報を検索する
        getHanyoAppSearchEntryInfoLogic().execute(getHanyoApplicationDto());
        // オーナーユーザーで下記の条件を満たす場合は、参照モードにする
        // 登録終了日 ＜ システム日付 ＜ 表示終了日
        if (HanyoApplicationCommon.USER_TYPE_CD_ONER.equals(getHanyoApplicationDto().getUserTypeCd())
                && isOnerUserDispOnly()) 
        {
            setRegistCount();
            return HanyoApplicationCommon.VIEW_ID_CONFIRM;
        }
        // 申込定員チェック
        getHanyoAppCheckEntryNumberLimitLogic().execute(getHanyoApplicationDto(), HanyoApplicationCommon.NUMBER_CHECK_MODE_SELECT);
        // 編集画面へ遷移
        return HanyoApplicationCommon.VIEW_ID_EDIT;
    }
    
    /**
     * オーナーユーザー 照会モード判別
     * @return true:照会モード
     */
    private boolean isOnerUserDispOnly() {
        boolean flg = false;
        // 選択行インデックス
        int selectIndex = getHanyoApplicationDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        UIEntryMst entityMst = (UIEntryMst) getHanyoApplicationDto().getListEntryMst().get(selectIndex);
        
        String sysDate = getHanyoApplicationDto().getSysDate();
        if (sysDate.compareTo(entityMst.getOnerUketukeTo()) > 0
                && sysDate.compareTo(entityMst.getOnerHyojiTo()) <= 0)
        {
            flg = true;
        }
        return flg;
    }
    /**
     * 新規、更新対象のレコード数取得
     */
    private void setRegistCount() {
        int count = 0;
        for (Iterator ite = getHanyoApplicationDto().getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                count++;
            }
        }
        getHanyoApplicationDto().setInsupEntryStateNum(count);
    }
    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm() {
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(HanyoApplicationCommon.VIEW_ID_SELECT);
        miseSearchDto.setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getHanyoApplicationDto().getCondCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        return HanyoApplicationCommon.VIEW_ID_MISESEARCH;
    }
    
    /**
     * 検索結果クリア
     */
    private void clearResult() {
        getHanyoApplicationDto().setListEntryDate(null);
        getHanyoApplicationDto().setListEntryMst(null);
    }
    /**
     * セッション情報クリア
     */
    private void clearInfo() {
        clearResult();
        getHanyoApplicationDto().setCondCompanyCd("");
        getHanyoApplicationDto().setCondMiseCd("");
        getHanyoApplicationDto().setStaffBottonIndex(0);
        getHanyoApplicationDto().setListEntryCount(0);
        getHanyoApplicationDto().setEntryAfterCount(0);
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
    
    /**
     * 編集、削除時に選択行のエントリー情報をDTOへセットする
     */
    private void setSelectedEntryInfo() {
        // 選択行インデックス
        int selectIndex = getHanyoApplicationDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        UIEntryMst entityMst = (UIEntryMst) getHanyoApplicationDto().getListEntryMst().get(selectIndex);
        getHanyoApplicationDto().setEntryCd(entityMst.getEntryCd());
        getHanyoApplicationDto().setEntryYear(entityMst.getEntryYear());
        getHanyoApplicationDto().setEntryKai(entityMst.getEntryKai());
    }
    public SessionKeyDto getHanyoRegistSessionKeyDto() {
        return hanyoRegistSessionKeyDto;
    }
    public void setHanyoRegistSessionKeyDto(SessionKeyDto sessionKeyDto) {
        this.hanyoRegistSessionKeyDto = sessionKeyDto;
    }
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }
    public CompanyJohoLogic getHanyoApplicationCompanyJohoLogic() {
        return hanyoApplicationCompanyJohoLogic;
    }
    public void setHanyoApplicationCompanyJohoLogic(
            CompanyJohoLogic hanyoApplicationCompanyJohoLogic) {
        this.hanyoApplicationCompanyJohoLogic = hanyoApplicationCompanyJohoLogic;
    }
    public SearchEntryInfoLogic getHanyoAppSearchEntryInfoLogic() {
        return hanyoAppSearchEntryInfoLogic;
    }
    public void setHanyoAppSearchEntryInfoLogic(
            SearchEntryInfoLogic hanyoAppSearchEntryInfoLogic) {
        this.hanyoAppSearchEntryInfoLogic = hanyoAppSearchEntryInfoLogic;
    }
    public UserMiseJohoLogic getHanyoAppUserMiseJohoLogic() {
        return hanyoAppUserMiseJohoLogic;
    }
    public void setHanyoAppUserMiseJohoLogic(
            UserMiseJohoLogic hanyoAppUserMiseJohoLogic) {
        this.hanyoAppUserMiseJohoLogic = hanyoAppUserMiseJohoLogic;
    }
    public CheckEntryNumberLimitLogic getHanyoAppCheckEntryNumberLimitLogic() {
        return hanyoAppCheckEntryNumberLimitLogic;
    }
    public void setHanyoAppCheckEntryNumberLimitLogic(
            CheckEntryNumberLimitLogic hanyoAppCheckEntryNumberLimitLogic) {
        this.hanyoAppCheckEntryNumberLimitLogic = hanyoAppCheckEntryNumberLimitLogic;
    }
}