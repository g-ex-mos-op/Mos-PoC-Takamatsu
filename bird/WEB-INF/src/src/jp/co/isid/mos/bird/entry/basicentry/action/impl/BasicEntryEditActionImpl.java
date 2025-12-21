/*
 * 作成日: 2006/6/05
 */
package jp.co.isid.mos.bird.entry.basicentry.action.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.entry.basicentry.action.BasicEntryEditAction;
import jp.co.isid.mos.bird.entry.basicentry.common.BasicEntryCommon;
import jp.co.isid.mos.bird.entry.basicentry.dto.BasicEntryDto;
import jp.co.isid.mos.bird.entry.basicentry.dto.SessionKeyDto;
import jp.co.isid.mos.bird.entry.basicentry.entity.MstMise;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryMst;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.basicentry.entity.UIStaff;
import jp.co.isid.mos.bird.entry.basicentry.logic.CheckEntryLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.CheckEntryNumberLimitLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.SearchStaffLogic;
import jp.co.isid.mos.bird.entry.basicentry.logic.UpdateEntryInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffRegistIFDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;

/**
 * 研修参加申込　編集画面アクションクラス
 * @author kusama 
 */
public class BasicEntryEditActionImpl implements BasicEntryEditAction {

    private static Logger logger_ = Logger.getLogger(BasicEntryEditActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID   = "BEN002A11";
    public static String cancel_ACTION_ID       = "BEN002A12";
    public static String addRow_ACTION_ID       = "BEN002A13";
    public static String addStaff_ACTION_ID     = "BEN002A14";
    public static String editStaff_ACTION_ID    = "BEN002A15";
    public static String regist_ACTION_ID       = "BEN002A16";
    public static String registCancel_ACTION_ID = "BEN002A17";

    /* DTO */
    // 汎用研修マスタ登録用DTO
    private BasicEntryDto basicEntryDto;
    // セッションキー保持DTO
    private SessionKeyDto basicEntrySessionKeyDto;
    // 店検索DTO
    private MiseSearchDto miseSearchDto;
    // スタッフ追加画面
    private StaffRegistIFDto staffRegistIFDto;
    
    /* ロジック */
    // ユーザー所属会社一覧取得ロジック
    private CompanyJohoLogic basicEntryCompanyJohoLogic; 
    // スタッフ一覧の取得ロジック
    private SearchStaffLogic basicEntrySearchStaffLogic;
    // 定員チェックロジック
    private CheckEntryNumberLimitLogic basicEntryCheckEntryNumberLimitLogic;
    // 登録内容チェックロジック
    private CheckEntryLogic basicEntryCheckEntryLogic;
    // 研修エントリー情報の更新
    UpdateEntryInfoLogic basicEntryUpdateEntryInfoLogic;
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
	public void setBasicEntryDto(BasicEntryDto hanyoApplicationDto) {
		this.basicEntryDto = hanyoApplicationDto;
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
            for (Iterator ite = getBasicEntryDto().getListEntryState().iterator(); ite.hasNext();) {
                // 該当行のEntityを取得
                UIEntryState uiEntryState = (UIEntryState) ite.next();
// add start xkhata 2006/08/18
                if ( uiEntryState.getGuideKbn().equals("OTHER") ) {
                    uiEntryState.setReadOnlyFlg( true );
                } else {
                    uiEntryState.setReadOnlyFlg( false );
                }
// end
                if (uiEntryState.getStaffId() != null && !"".equals(uiEntryState.getStaffId())) {
                    // 処理１で取得した情報をセット
                    for (Iterator ite2 = getBasicEntryDto().getListStaff().iterator(); ite2.hasNext();) {
                        UIStaff uiStaff = (UIStaff) ite2.next();
                        if (uiEntryState.getStaffId().equals(uiStaff.getStaffId())) {
                            uiEntryState.setSex(uiStaff.getSex());
                            uiEntryState.setNameKnaInput(uiStaff.getStaffLNameKna() + " " + uiStaff.getStaffFNameKna());
                            bolSetStaffInfo = true;
                            break;
                        }
                    }
                    if (!bolSetStaffInfo) {
                        //スタッフ編集画面でスタッフ一覧の対象外になった場合は、関連情報をクリア
                        uiEntryState.setSex("0");
                        uiEntryState.setNameKnaInput("");
                        uiEntryState.setJob("");
                    }
                }
            }
            
            getStaffRegistIFDto().setActionFlg(false);
        }
        else {
            //情報セットフラグ
            boolean bolSetStaffInfo = false;
            // 選択済みのスタッフ情報を再選択
            for (Iterator ite = getBasicEntryDto().getListEntryState().iterator(); ite.hasNext();) {
                // 該当行のEntityを取得
                UIEntryState uiEntryState = (UIEntryState) ite.next();
                
//   add start xkhata 2006/08/18
                if ( uiEntryState.getGuideKbn().equals("OTHER") ) {
                    uiEntryState.setReadOnlyFlg( false );
                } else {
                    uiEntryState.setReadOnlyFlg( true );
                }
// end
                if (uiEntryState.getStaffId() != null && !"".equals(uiEntryState.getStaffId())) {
                    // 処理１で取得した情報をセット
                    for (Iterator ite2 = getBasicEntryDto().getListStaff().iterator(); ite2.hasNext();) {
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
                    }
                }
            }            
        }
        
        // ３．同一オーナーでの申込数チェック
        if (getBasicEntryDto().isFlgEdtiAlert()) {
            //11人目以降の警告
            getBasicEntryDto().setEditAlertMsg(getBasicEntryDto().getMsgMosikomiLimitAlert());
        }
        else {
            getBasicEntryDto().setEditAlertMsg("");
        }
        getBasicEntryDto().setFlgEdtiAlert(false);
        
        return null;
    }

    /**
     * スタッフ一覧プルダウン用リスト作成
     */
    private void makeStaffList() {
        // スタッフ一覧の取得
        Map mapStaff = getBasicEntrySearchStaffLogic().execute(getBasicEntryDto());
        List listStaff = (List) mapStaff.get("listStaff");
        // 一行目に「研修生を選択して下さい。」を追加
        UIStaff entity = new UIStaff();
        entity.setStaffId("");
        entity.setStaffSelect("研修生を選択して下さい。");
        listStaff.add(0, entity);
        
        getBasicEntryDto().setListStaff(listStaff);
    }
    
    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String cancel() {
        // 条件画面の入力値をクリア
        getBasicEntryDto().setCondClearFlg(true);
        
    	return BasicEntryCommon.VIEW_ID_SELECT;
	}

    /**
     * 入力欄追加
     * update) 2006/07/05 入力欄追加前にチェック、登録処理を行う（登録・終了ボタン押下と同等処理）
     */
    public String addRow() {
        // 登録処理--- 2006/07/05 add
        doRegist();
        
        List listStaff = getBasicEntryDto().getListEntryState();
        
        // １．申込定員のチェック
        getBasicEntryCheckEntryNumberLimitLogic().execute(getBasicEntryDto(), BasicEntryCommon.NUMBER_CHECK_MODE_EDIT);
        
        // ２．新規行追加
        UIEntryState entity = new UIEntryState();
        entity.setEntryCd(getBasicEntryDto().getEntryCd());
        entity.setEntryYear(getBasicEntryDto().getEntryYear());
        entity.setEntryKai(getBasicEntryDto().getEntryKai());
        entity.setStaffId("");
        entity.setOnerCd(getBasicEntryDto().getCondOnerCd());
        entity.setGuideKbn(BasicEntryCommon.GUIDE_KBN_KEKKA);
        entity.setOtherFlg1("1");
        entity.setOtherFlg2("1");
        entity.setOtherFlg3("1");
        entity.setInsertFlg(true);
        
        // 受講案内送付先情報セット
        UIEntryOner uiEntryOwner = getBasicEntryDto().getUiEntryOnerMosikomiSekinin();
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
        doRegist();
        
        return BasicEntryCommon.VIEW_ID_CONFIRM;
    }

    /**
     * 登録処理
     */
    private void doRegist() {
        /* 受講案内送付先、スタッフ情報を[研修エントリー情報]にセット */
        setStaffSofusakiInfo();
        
        /* 新規、更新対象のレコード数 */
        setRegistCount();
        
        /* 入力チェック処理 */
        getBasicEntryCheckEntryLogic().execute(getBasicEntryDto());
        
        /* 登録処理 */
        // 研修エントリー情報の更新
        getBasicEntryUpdateEntryInfoLogic().execute(getBasicEntryDto());
    }
    
    /**
     * 新規、更新対象のレコード数取得
     */
    private void setRegistCount() {
        int count = 0;
        for (Iterator ite = getBasicEntryDto().getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (!isNull(uiEntryState.getStaffId())) {
                count++;
            }
        }
        getBasicEntryDto().setInsupEntryStateNum(count);
    }
    
    /**
     * 受講案内送付先、スタッフ情報を[研修エントリー情報]にセット
     */
    private void setStaffSofusakiInfo() {
        for (Iterator ite = getBasicEntryDto().getListEntryState().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            //スタッフ氏名をセット
            if (!isNull(uiEntryState.getStaffId())) {
                for (Iterator ite2 = getBasicEntryDto().getListStaff().iterator(); ite2.hasNext();) {
                    UIStaff uiStaff = (UIStaff) ite2.next();
                    if (uiEntryState.getStaffId().equals(uiStaff.getStaffId())) {
                        uiEntryState.setStaffName(uiStaff.getStaffSelect());
                        break;
                    }
                }
            }
            //受講案内送付先情報をセット
            if (uiEntryState.getGuideKbn().equals("KEKKA")) {
                uiEntryState.setGuideKbnName("結果報告先");
            }
            else if (uiEntryState.getGuideKbn().equals("OTHER")) {
                uiEntryState.setGuideKbnName("その他");
            }
            else {
                String miseCd = uiEntryState.getGuideKbn();
                for (Iterator ite2 = getBasicEntryDto().getListMstMise().iterator(); ite2.hasNext();) {
                    MstMise mstMise = (MstMise) ite2.next();
                    if (miseCd.equals(mstMise.getMiseCd())) {
                        uiEntryState.setGuideKbnName(mstMise.getMiseNameKj());
                        break;
                    }
                }
            }
        }
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
        getStaffRegistIFDto().setCompanyCd(getBasicEntryDto().getCondCompanyCd());
        getStaffRegistIFDto().setOnerCd(getBasicEntryDto().getCondOnerCd());
        getStaffRegistIFDto().setNavigationCase(BasicEntryCommon.VIEW_ID_EDIT);
        getStaffRegistIFDto().setInitialFlag(true);
        
        return BasicEntryCommon.VIEW_ID_STAFFREGIST;
    }
    
    /**
     * スタッフ編集
     */
    public String editStaff() {
        // 該当行のEntityを取得
        UIEntryState uiEntryState = (UIEntryState) getBasicEntryDto()
                                                    .getListEntryState().get(
                                                            getBasicEntryDto().getAddStaffIndex());
        String staffId = uiEntryState.getStaffId();
        if (staffId == null || staffId.equals("")) {
            //スタッフが選択されていない場合は、遷移なし
            return null;
        }
        
        getStaffRegistIFDto().setEditMode(2);
        getStaffRegistIFDto().setStaffId(staffId);
        getStaffRegistIFDto().setNavigationCase(BasicEntryCommon.VIEW_ID_EDIT);
        getStaffRegistIFDto().setInitialFlag(true);
        
        return BasicEntryCommon.VIEW_ID_STAFFREGIST;
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
        int selectIndex = getBasicEntryDto().getSelectIndex();    
        // 選択された行のキー情報を取得
        UIEntryMst entityMst = (UIEntryMst) getBasicEntryDto().getListEntryMst().get(selectIndex);
        getBasicEntryDto().setEntryCd(entityMst.getEntryCd());
        getBasicEntryDto().setEntryYear(entityMst.getEntryYear());
        getBasicEntryDto().setEntryKai(entityMst.getEntryKai());
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
    public CompanyJohoLogic getBasicEntryCompanyJohoLogic() {
        return basicEntryCompanyJohoLogic;
    }
    public void setBasicEntryCompanyJohoLogic(
            CompanyJohoLogic hanyoApplicationCompanyJohoLogic) {
        this.basicEntryCompanyJohoLogic = hanyoApplicationCompanyJohoLogic;
    }
    public SearchStaffLogic getBasicEntrySearchStaffLogic() {
        return basicEntrySearchStaffLogic;
    }
    public void setBasicEntrySearchStaffLogic(
            SearchStaffLogic hanyoApplicationSearchStaffLogic) {
        this.basicEntrySearchStaffLogic = hanyoApplicationSearchStaffLogic;
    }
    public StaffRegistIFDto getStaffRegistIFDto() {
        return staffRegistIFDto;
    }
    public void setStaffRegistIFDto(StaffRegistIFDto staffRegistIFDto) {
        this.staffRegistIFDto = staffRegistIFDto;
    }
    public CheckEntryNumberLimitLogic getBasicEntryCheckEntryNumberLimitLogic() {
        return basicEntryCheckEntryNumberLimitLogic;
    }
    public void setBasicEntryCheckEntryNumberLimitLogic(
            CheckEntryNumberLimitLogic hanyoAppCheckEntryNumberLimitLogic) {
        this.basicEntryCheckEntryNumberLimitLogic = hanyoAppCheckEntryNumberLimitLogic;
    }
    public CheckEntryLogic getBasicEntryCheckEntryLogic() {
        return basicEntryCheckEntryLogic;
    }
    public void setBasicEntryCheckEntryLogic(CheckEntryLogic hanyoAppCheckEntryLogic) {
        this.basicEntryCheckEntryLogic = hanyoAppCheckEntryLogic;
    }
    public UpdateEntryInfoLogic getBasicEntryUpdateEntryInfoLogic() {
        return basicEntryUpdateEntryInfoLogic;
    }
    public void setBasicEntryUpdateEntryInfoLogic(
            UpdateEntryInfoLogic basicEntryUpdateEntryInfoLogic) {
        this.basicEntryUpdateEntryInfoLogic = basicEntryUpdateEntryInfoLogic;
    }
}