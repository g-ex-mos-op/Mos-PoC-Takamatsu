/*
 * 作成日: 2006/6/05
 */
package jp.co.isid.mos.bird.entry.hanyoapplication.action.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.action.HanyoApplicationEditAction;
import jp.co.isid.mos.bird.entry.hanyoapplication.common.HanyoApplicationCommon;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.HanyoApplicationDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.MstMise;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryOwner;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.hanyoapplication.entity.UIStaff;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchEntryLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.SearchStaffLogic;
import jp.co.isid.mos.bird.entry.hanyoapplication.logic.UpdateEntryInfoLogic;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistIFDto;

/**
 * 研修参加申込　編集画面アクションクラス
 * @author kusama 
 */
public class HanyoApplicationEditActionImpl implements HanyoApplicationEditAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BEN005A11";
    public static String cancel_ACTION_ID       = "BEN005A12";
    public static String addRow_ACTION_ID       = "BEN005A13";
    public static String addStaff_ACTION_ID     = "BEN005A14";
    public static String editStaff_ACTION_ID    = "BEN005A15";
    public static String regist_ACTION_ID       = "BEN005A16";
    public static String registCancel_ACTION_ID = "BEN005A17";

    /* DTO */
    // 汎用研修マスタ登録用DTO
    private HanyoApplicationDto hanyoApplicationDto;
    // セッションキー保持DTO
    private SessionKeyDto hanyoRegistSessionKeyDto;
    // 店検索DTO
    private MiseSearchDto miseSearchDto;
    // スタッフ追加画面
    private StaffRegistIFDto staffRegistIFDto;
    
    /* ロジック */
    // エントリーマスタ管理の検索ロジック
    private SearchEntryLogic searchEntryLogic;
    // ユーザー所属会社一覧取得ロジック
    private CompanyJohoLogic hanyoApplicationCompanyJohoLogic; 
    // スタッフ一覧の取得ロジック
    private SearchStaffLogic hanyoApplicationSearchStaffLogic;
    // 定員チェックロジック
    private CheckEntryNumberLimitLogic hanyoAppCheckEntryNumberLimitLogic;
    // 登録内容チェックロジック
    private CheckEntryLogic hanyoAppCheckEntryLogic;
    // 研修エントリー情報の更新
    private UpdateEntryInfoLogic hanyoAppUpdateEntryInfoLogic;
    
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
        // １．スタッフ一覧プルダウン用リスト作成
        makeStaffList();
        
        // ２．スタッフ追加画面からの戻り値セット
        if (getStaffRegistIFDto().isActionFlg()) {
            //情報セットフラグ
            boolean bolSetStaffInfo = false;
            // 選択済みのスタッフ情報を再選択
            for (Iterator ite = getHanyoApplicationDto().getListEntryState().iterator(); ite.hasNext();) {
                // 該当行のEntityを取得
                UIEntryState uiEntryState = (UIEntryState) ite.next();
                
// add start xkhata 20060822 その他のreadonly対策
                if ( !uiEntryState.getGuideKbn().equals( "OTHER" ) ) {
                    uiEntryState.setReadOnlyFlg( true );
                } else {
                    uiEntryState.setReadOnlyFlg( false );
                }
// add end
                if (uiEntryState.getStaffId() != null && !"".equals(uiEntryState.getStaffId())) {
                    // 処理１で取得した情報をセット
                    for (Iterator ite2 = getHanyoApplicationDto().getListStaff().iterator(); ite2.hasNext();) {
                        UIStaff uiStaff = (UIStaff) ite2.next();
                        if (uiEntryState.getStaffId().equals(uiStaff.getStaffId())) {
                            uiEntryState.setSex(uiStaff.getSex());
                            uiEntryState.setNameKnaInput(uiStaff.getStaffLNameKna() + " " + uiStaff.getStaffFNameKna());
                            uiEntryState.setStaffSelect(uiStaff.getStaffSelect());
                            bolSetStaffInfo = true;
                            break;
                        }
                    }
                    if (!bolSetStaffInfo) {
                        //スタッフ編集画面でスタッフ一覧の対象外になった場合は、関連情報をクリア
                        uiEntryState.setSex("0");
                        uiEntryState.setNameKnaInput("");
                        uiEntryState.setJob("");
                        uiEntryState.setStaffSelect("");
                    }
                }
            }
            
            getStaffRegistIFDto().setActionFlg(false);
        }
        else {
            //情報セットフラグ
            boolean bolSetStaffInfo = false;
            
            // 選択済みのスタッフ情報を再選択
            for (Iterator ite = getHanyoApplicationDto().getListEntryState().iterator(); ite.hasNext();) {
                // 該当行のEntityを取得
                UIEntryState uiEntryState = (UIEntryState) ite.next();

//   add start xkhata 20060822 その他のreadonly対策
                if ( !uiEntryState.getGuideKbn().equals( "OTHER" ) ) {
                    uiEntryState.setReadOnlyFlg( true );
                } else {
                    uiEntryState.setReadOnlyFlg( false );
                }
//   add end

                if (uiEntryState.getStaffId() != null && !"".equals(uiEntryState.getStaffId())) {
                    // 処理１で取得した情報をセット
                    for (Iterator ite2 = getHanyoApplicationDto().getListStaff().iterator(); ite2.hasNext();) {
                        UIStaff uiStaff = (UIStaff) ite2.next();
                        if (uiEntryState.getStaffId().equals(uiStaff.getStaffId())) {
                            bolSetStaffInfo = true;
                            break;
                        }
                    }
                    if (!bolSetStaffInfo) {
                        //スタッフ編集画面でスタッフ一覧の対象外になった場合は、関連情報をクリア
                        uiEntryState.setSex("0");
                        uiEntryState.setNameKnaInput("");
                        uiEntryState.setJob("");
                        uiEntryState.setStaffSelect("");
                    }
                }
            }            
        }
        
//      delete start inazawa 2006/01/09 マスタライセンス４次対応
        // ３．同一オーナーでの申込数チェック
//        if (getHanyoApplicationDto().isFlgEdtiAlert()) {
//            //11人目以降の警告
//            getHanyoApplicationDto().setEditAlertMsg(getHanyoApplicationDto().getMsgMosikomiLimitAlert());
//        }
//        else {
//            getHanyoApplicationDto().setEditAlertMsg("");
//        }
//        getHanyoApplicationDto().setFlgEdtiAlert(false);
//      delete end
        
        return null;
    }

    /**
     * スタッフ一覧プルダウン用リスト作成
     */
    private void makeStaffList() {
        // スタッフ一覧の取得
        Map mapStaff = getHanyoApplicationSearchStaffLogic().execute(getHanyoApplicationDto());
        List listStaff = (List) mapStaff.get("listStaff");
        // 一行目に「研修生を選択して下さい。」を追加
        UIStaff entity = new UIStaff();
        entity.setStaffId("");
        entity.setStaffSelect("研修生を選択して下さい。");
        listStaff.add(0, entity);
        
        getHanyoApplicationDto().setListStaff(listStaff);
    }
    
    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {
        // 条件画面の入力値をクリア
        getHanyoApplicationDto().setCondClearFlg(true);
        getHanyoApplicationDto().setEntryAfterCount(0);
        getHanyoApplicationDto().setListEntryCount(0);
        getHanyoApplicationDto().setStaffBottonIndex(0);
        
    	return HanyoApplicationCommon.VIEW_ID_SELECT;
	}

    /**
     * 入力欄追加
     * update) 2006/07/05 入力欄追加前にチェック、登録処理を行う（登録・終了ボタン押下と同等処理）
     */
    public String addRow() {
        // 登録処理--- 2006/07/05 add
        doRegist();
        
        List listStaff = getHanyoApplicationDto().getListEntryState();
        
        // １．申込定員のチェック
        getHanyoAppCheckEntryNumberLimitLogic().execute(getHanyoApplicationDto(), HanyoApplicationCommon.NUMBER_CHECK_MODE_EDIT);
        
        // ２．新規行追加
        UIEntryState entity = new UIEntryState();
        entity.setEntryCd(getHanyoApplicationDto().getEntryCd());
        entity.setEntryYear(getHanyoApplicationDto().getEntryYear());
        entity.setEntryKai(getHanyoApplicationDto().getEntryKai());
        entity.setStaffId("");
        entity.setGuideKbn("");
        entity.setOnerCd(getHanyoApplicationDto().getCondOnerCd());
        entity.setInsertFlg(true);
        
        // 受講案内送付先情報セット
        UIEntryOwner uiEntryOwner = getHanyoApplicationDto().getUiEntryOwnerMosikomiSekinin();
        entity.setGuideName(uiEntryOwner.getName());
        entity.setGuideZip(uiEntryOwner.getZip());
        entity.setGuideAdrs1(uiEntryOwner.getAddress1());
        entity.setGuideAdrs2(uiEntryOwner.getAddress2());
        entity.setGuideAdrs3(uiEntryOwner.getAddress3());
        
        listStaff.add(entity);
        
        return null;
    }
    
    /**
     * 登録・終了
     */
    public String regist() {
        // 定員チェック
        getHanyoAppCheckEntryNumberLimitLogic()
                .execute(getHanyoApplicationDto(), HanyoApplicationCommon.NUMBER_CHECK_MODE_REGIST);
        
        doRegist();
        
        return HanyoApplicationCommon.VIEW_ID_CONFIRM;
    }
    
    /**
     * 申込取消
     */
    public String registCancel() {
        return null;
    }
    
    /**
     * 新規スタッフ追加
     */
    public String addStaff() {
        // 新規
        getStaffRegistIFDto().setEditMode(1);
        getStaffRegistIFDto().setCompanyCd(getHanyoApplicationDto().getCondCompanyCd());
        getStaffRegistIFDto().setOnerCd(getHanyoApplicationDto().getCondOnerCd());
        getStaffRegistIFDto().setNavigationCase(HanyoApplicationCommon.VIEW_ID_EDIT);
        getStaffRegistIFDto().setInitialFlag(true);
        
        return HanyoApplicationCommon.VIEW_ID_STAFFREGIST;
    }
    
    /**
     * スタッフ編集
     */
    public String editStaff() {
        // 該当行のEntityを取得
        UIEntryState uiEntryState = (UIEntryState) getHanyoApplicationDto()
                                                    .getListEntryState().get(
                                                            getHanyoApplicationDto().getAddStaffIndex());
        String staffId = uiEntryState.getStaffId();
        if (staffId == null || staffId.equals("")) {
            //スタッフが選択されていない場合は、遷移なし
            return null;
        }
        
        getStaffRegistIFDto().setEditMode(2);
        getStaffRegistIFDto().setStaffId(staffId);
        getStaffRegistIFDto().setNavigationCase(HanyoApplicationCommon.VIEW_ID_EDIT);
        getStaffRegistIFDto().setInitialFlag(true);
        
        return HanyoApplicationCommon.VIEW_ID_STAFFREGIST;
    }

    /**
     * 登録処理
     */
    private void doRegist() {
        // 受講案内送付先、スタッフ情報を[研修エントリー情報]にセット
        setStaffSofusakiInfo();
        
        // 新規、更新対象のレコード数
        setRegistCount();
        
        //入力チェック処理
        getHanyoAppCheckEntryLogic().execute(getHanyoApplicationDto());
        
        // 研修エントリー情報の更新
        getHanyoAppUpdateEntryInfoLogic().execute(getHanyoApplicationDto());
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
     * 受講案内送付先、スタッフ情報を[研修エントリー情報]にセット
     */
    private void setStaffSofusakiInfo() {
        for (Iterator ite = getHanyoApplicationDto().getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            //スタッフ氏名をセット
            if (!isNull(uiEntryState.getStaffId())) {
                for (Iterator ite2 = getHanyoApplicationDto().getListStaff().iterator(); ite2.hasNext();) {
                    UIStaff uiStaff = (UIStaff) ite2.next();
                    if (uiEntryState.getStaffId().equals(uiStaff.getStaffId())) {
                        uiEntryState.setStaffName(uiStaff.getStaffSelect());
                        break;
                    }
                }
            }
            
            //出張研修の場合、受講案内送付先情報をセット
            if (HanyoApplicationCommon.ENTRYCD_SHUTTYO.equals(uiEntryState.getEntryCd())) {
                if (uiEntryState.getGuideKbn().equals("KEKKA")) {
                    uiEntryState.setGuideKbnName("結果報告先");
                }
                else if (uiEntryState.getGuideKbn().equals("OTHER")) {
                    uiEntryState.setGuideKbnName("その他");
                }
                else {
                    String miseCd = uiEntryState.getGuideKbn();
                    for (Iterator ite2 = getHanyoApplicationDto().getListMstMise().iterator(); ite2.hasNext();) {
                        MstMise mstMise = (MstMise) ite2.next();
                        if (miseCd.equals(mstMise.getMiseCd())) {
                            uiEntryState.setGuideKbnName(mstMise.getMiseNameKj());
                            break;
                        }
                    }
                }
            }
            if(isNull(uiEntryState.getSelectedStaffId())){
                uiEntryState.setSelectedStaffId(uiEntryState.getStaffId());
            }

        }
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

//    private S2Container getS2Container() {
//        return SingletonS2ContainerFactory.getContainer(); 
//    }
//    private PullDownMenuDto getPullDownMenuDto() {
//        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
//    }
//    private BirdUserInfo getBirdUserInfo() {
//        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
//    }
//    private BirdDateInfo getBirdDateInfo() {
//        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
//    }
//    
//    /**
//     * 編集、削除時に選択行のエントリー情報をDTOへセットする
//     */
//    private void setSelectedEntryInfo() {
//        // 選択行インデックス
//        int selectIndex = getHanyoApplicationDto().getSelectIndex();    
//        // 選択された行のキー情報を取得
//        UIEntryMst entityMst = (UIEntryMst) getHanyoApplicationDto().getListEntryMst().get(selectIndex);
//        getHanyoApplicationDto().setEntryCd(entityMst.getEntryCd());
//        getHanyoApplicationDto().setEntryYear(entityMst.getEntryYear());
//        getHanyoApplicationDto().setEntryKai(entityMst.getEntryKai());
//    }
    
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
    public SearchStaffLogic getHanyoApplicationSearchStaffLogic() {
        return hanyoApplicationSearchStaffLogic;
    }
    public void setHanyoApplicationSearchStaffLogic(
            SearchStaffLogic hanyoApplicationSearchStaffLogic) {
        this.hanyoApplicationSearchStaffLogic = hanyoApplicationSearchStaffLogic;
    }
    public StaffRegistIFDto getStaffRegistIFDto() {
        return staffRegistIFDto;
    }
    public void setStaffRegistIFDto(StaffRegistIFDto staffRegistIFDto) {
        this.staffRegistIFDto = staffRegistIFDto;
    }
    public CheckEntryNumberLimitLogic getHanyoAppCheckEntryNumberLimitLogic() {
        return hanyoAppCheckEntryNumberLimitLogic;
    }
    public void setHanyoAppCheckEntryNumberLimitLogic(
            CheckEntryNumberLimitLogic hanyoAppCheckEntryNumberLimitLogic) {
        this.hanyoAppCheckEntryNumberLimitLogic = hanyoAppCheckEntryNumberLimitLogic;
    }
    public CheckEntryLogic getHanyoAppCheckEntryLogic() {
        return hanyoAppCheckEntryLogic;
    }
    public void setHanyoAppCheckEntryLogic(CheckEntryLogic hanyoAppCheckEntryLogic) {
        this.hanyoAppCheckEntryLogic = hanyoAppCheckEntryLogic;
    }
    public UpdateEntryInfoLogic getHanyoAppUpdateEntryInfoLogic() {
        return hanyoAppUpdateEntryInfoLogic;
    }
    public void setHanyoAppUpdateEntryInfoLogic(
            UpdateEntryInfoLogic hanyoAppUpdateEntryInfoLogic) {
        this.hanyoAppUpdateEntryInfoLogic = hanyoAppUpdateEntryInfoLogic;
    }
}