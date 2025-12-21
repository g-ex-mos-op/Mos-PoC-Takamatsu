/*
 * 作成日: 2006/12/19
 */
package jp.co.isid.mos.bird.entry.longserviceoffer.action.impl;

import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto.EntryStaffSearchDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.action.LongserviceOfferEditAction;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferCommon;
import jp.co.isid.mos.bird.entry.longserviceoffer.common.LongserviceOfferConstants;
import jp.co.isid.mos.bird.entry.longserviceoffer.dto.LongserviceOfferDto;
import jp.co.isid.mos.bird.entry.longserviceoffer.entity.UIOfferEntry;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.CheckOfferLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.InsertOfferLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.SearchOfferLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.SearchOfferMiseLogic;
import jp.co.isid.mos.bird.entry.longserviceoffer.logic.UpdateOfferLogic;
import jp.co.isid.mos.bird.entry.longserviceregist.common.LongserviceRegistConstants;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;

/**
 * 永年勤続申請　編集画面アクションクラス
 * @author narita
 */
public class LongserviceOfferEditActionImpl implements LongserviceOfferEditAction {

	/** アクションID定義 */
    public static final String initialize_ACTION_ID = "BEN018A01";
    public static final String insert_ACTION_ID = "BEN018A02";
    public static final String regist_ACTION_ID = "BEN018A03";
    public static final String callStaffForm_ACTION_ID = "BEN018A04";
    public static final String back_ACTION_ID = "BEN018A05";

    /** 永年勤続申請登録情報DTO */
    private LongserviceOfferDto longserviceOfferDto;

    /** 永年勤続申請情報チェックロジック */
    private CheckOfferLogic checkOfferLogic;

    /** 永年勤続申請情報更新ロジック */
    private UpdateOfferLogic updateUserLogic;

    /** 永年勤続申請情報検索ロジック */
    private SearchOfferLogic searchOfferLogic;

    /** 各種イベント申込 */
    private EventListDto eventListDto;

    /** 店舗情報取得ロジック */
    private SearchOfferMiseLogic searchOfferMiseLogic;

    /** 永年勤続申請情報入力欄追加ロジック */
    private InsertOfferLogic insertOfferLogic;

    /** スタッフ情報 */
    private EntryStaffSearchDto entryStaffSearchDto;

	/** 全国大会DTO：session */
	private NationalEntryDto nationalEntryDto;

	private int staffSelectNo;

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //日付情報を取得
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
    	getLongserviceOfferDto().setSysDate(birdDateInfo.getSysDate());

        //複数ウィンドウ制御用のセッションキー生成
        String key = getLongserviceOfferDto().getMkSession()._makeSessionKey();

        int initFlg = 0;

        // 各種イベント申込みまたは全国大会から遷移された場合
        if (getEventListDto().getInitFlag() == LongserviceOfferConstants.INIT_FLG_SELECT ||
        		getEventListDto().getInitFlag() == LongserviceOfferConstants.INIT_FLG_NATIONAL) {

	        int windowId = getEventListDto().getWindowId();
	        String entryCd =  getEventListDto().getEntryCd();
	        String entryYear = getEventListDto().getEntryYear();
	        String entryKai = getEventListDto().getEntryKai();
	        String companyCd = getEventListDto().getCompanyCd();
	        String onerCd = getEventListDto().getOnerCd();
	        initFlg =  getEventListDto().getInitFlag();

	        // ウィンドウIDを設定してからその他コードを設定する
	        getLongserviceOfferDto().setWindowId(windowId);
	        getLongserviceOfferDto().setEntryCd(entryCd);
	        getLongserviceOfferDto().setEntryYear(entryYear);
	        getLongserviceOfferDto().setEntryKai(entryKai);
	        getLongserviceOfferDto().setCompanyCd(companyCd);
	        getLongserviceOfferDto().setOnerCd(onerCd);

	        // セッションキーの設定
            getLongserviceOfferDto().setNowSessionKey(key);
            getLongserviceOfferDto().setSessionKey(key);

        	// 画面起動フラグの処理値設定
        	getEventListDto().setInitFlag(0);
        	// 追加ボタン有効フラグの処理値設定
        	getLongserviceOfferDto().setInsertBtnFlg(true);
        	// 編集可能フラグの初期設定
        	getLongserviceOfferDto().setEditFlg(true);
        	// データカウントの初期設定
        	getLongserviceOfferDto().setMstCount(0);
        	getLongserviceOfferDto().setEntryCount(0);
            getLongserviceOfferDto().setNowEntryCount("00");
            getLongserviceOfferDto().setStaffBottonNo("0");
        	// エディットモードの初期設定
        	getLongserviceOfferDto().setEditMode(LongserviceOfferConstants.EDIT_MODE_INIT);
        }

        // 表示データの取得処理　処理を行うのは初期表示時又は入力欄追加又は登録が正常処理された場合
        if(getLongserviceOfferDto().getEditMode() == LongserviceOfferConstants.EDIT_MODE_INIT ||
        	getLongserviceOfferDto().getEditMode() == LongserviceOfferConstants.EDIT_MODE_RETURN){

			// オーナー情報を取得
			getLongserviceOfferDto().setUIOnerInfo(getSearchOfferLogic().executeOner(
					getLongserviceOfferDto().getCompanyCd(),getLongserviceOfferDto().getOnerCd() ));
	        // 申込責任者情報を取得
			getLongserviceOfferDto().setUIOfferMst(getSearchOfferLogic().executeMst(
														getLongserviceOfferDto(),
														getNationalEntryDto(),
														initFlg));
			// 申請者情報を取得
			getLongserviceOfferDto().setUIOfferEntryList(getSearchOfferLogic().executeInfo(getLongserviceOfferDto()));
			// 申請者情報の最大ソートＮｏを取得する
			getLongserviceOfferDto().setMaxSeqNo(getSearchOfferLogic().getMaxSeqNo(getLongserviceOfferDto()));

			// 確認モードではない場合に店リストを取得する
            if(getEventListDto().getEntryTermKind() != 1){
	        	// 対象店舗リスト
            	List miseList = getSearchOfferMiseLogic().execute(
	        			getLongserviceOfferDto().getCompanyCd(),
	        			getLongserviceOfferDto().getOnerCd(),
	        			getLongserviceOfferDto().getSysDate());
	        	// 店舗リスト設定
	        	getLongserviceOfferDto().setUIOfferMiseList(miseList);
            }
        }

        // スタッフフォームからの戻り処理
        if(getLongserviceOfferDto().getEditMode() == LongserviceOfferConstants.EDIT_MODE_STAFF){
        	if(getEntryStaffSearchDto().getReturnKind() == EntryStaffSearchDto.RETURNKIND_SELECT){
	        	// スタッフフォームＤＴＯからスタッフ情報を取得
	        	MstStaff mstStaff = getEntryStaffSearchDto().getMstStaff();
	        	// 対象のソート番号のリストへセットする
	        	int no = getLongserviceOfferDto().getStaffSelectNo();
	        	UIOfferEntry uIOfferEntry = (UIOfferEntry)getLongserviceOfferDto().getUIOfferEntryList().get(no);
	        	uIOfferEntry.setFNameKj( mstStaff.getStaffFNameKj().trim() );// 氏名-氏
	        	uIOfferEntry.setLNameKj( mstStaff.getStaffLNameKj().trim() );// 氏名-名
	        	uIOfferEntry.setFNameRm( LongserviceOfferConstants.EMPTY );// ローマ字-氏
	        	uIOfferEntry.setLNameRm( LongserviceOfferConstants.EMPTY );// ローマ字-名
	        	uIOfferEntry.setSex( mstStaff.getSex().trim() );// 性別
	        	uIOfferEntry.setMiseCd( mstStaff.getMiseCd1().trim() );// 店コード

	    	 	if(mstStaff.getBirthday() != null && mstStaff.getBirthday().trim().length() !=0 ){
	    	 		String strBirthday = mstStaff.getBirthday().trim();
	            	// 西暦-年セット
	            	uIOfferEntry.setBirthday_Year(strBirthday.substring(0,4) );
	            	// 西暦-月セット
	            	uIOfferEntry.setBirthday_Month(strBirthday.substring(4,6) );
	            	// 西暦-日セット
	            	uIOfferEntry.setBirthday_Day(strBirthday.substring(6,8) );
		        	uIOfferEntry.setAge( LongserviceOfferCommon.getAge(birdDateInfo.getSysDate(),mstStaff.getBirthday()) );// 年齢
	    	 	}
        	}
        	// ウインドウID復元
        	getLongserviceOfferDto().setWindowId(getEntryStaffSearchDto().getWindowId());
        	getEntryStaffSearchDto().setReturnKind(EntryStaffSearchDto.RETURNKIND_INIT);
        }

        // 初期表示の時、申込期間は終了だが、表示期間の内であれば、確認画面へ遷移する
        if(getEventListDto().getEntryTermKind() == 1){
           	getLongserviceOfferDto().setEditMode(LongserviceOfferConstants.EDIT_MODE_CONFIRM);
        	return LongserviceOfferConstants.VIEW_ID_CONFIRM;
        }

        return null;
    }

    /**
     * 入力欄追加
     * @return 画面遷移情報
     */
    public String insert() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getLongserviceOfferDto().isValidSessionKey()) {
            return LongserviceRegistConstants.VIEW_ID_ERR;
        }
    	//事前処理
    	settingEditData(getLongserviceOfferDto());

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //日付情報を取得
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);

    	// エディットモードを設定　処理中
    	getLongserviceOfferDto().setEditMode(LongserviceOfferConstants.EDIT_MODE_INSERT);

    	// 登録内容チェックを行う
    	getCheckOfferLogic().execute(
    			getLongserviceOfferDto(),
    			birdDateInfo.getSysDate(),
    			LongserviceOfferConstants.EDIT_MODE_INSERT);

    	// 登録処理を行う
    	getUpdateUserLogic().execute(getLongserviceOfferDto());

    	// 入力欄の追加を行う
    	getInsertOfferLogic().execute(getLongserviceOfferDto());

	    // 編集画面を表示する。
	    return null;
    }

    /**
     * 登録・終了
     * @return 画面遷移情報
     */
    public String regist() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getLongserviceOfferDto().isValidSessionKey()) {
            return LongserviceRegistConstants.VIEW_ID_ERR;
        }
    	//事前処理
    	settingEditData(getLongserviceOfferDto());

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //日付情報を取得
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);

    	// エディットモードを設定　処理中
    	getLongserviceOfferDto().setEditMode(LongserviceOfferConstants.EDIT_MODE_UPDATE);

    	// 登録内容チェックを行う
    	getCheckOfferLogic().execute(
    			getLongserviceOfferDto(),
    			birdDateInfo.getSysDate(),
    			LongserviceOfferConstants.EDIT_MODE_UPDATE);

    	// 登録処理を行う
    	int editMode = getUpdateUserLogic().execute(getLongserviceOfferDto());

    	// エディットモードを設定　登録処理が正常終了
    	getLongserviceOfferDto().setEditMode(editMode);

    	// 確認画面を表示する。
    	return LongserviceOfferConstants.VIEW_ID_CONFIRM;
	}

    /**
     * 戻る
     * @return 画面遷移情報
     */
    public String back() {
        // セッションキー有効チェックを行い、有効でない場合は操作エラー画面へ遷移
        if (!getLongserviceOfferDto().isValidSessionKey()) {
            return LongserviceRegistConstants.VIEW_ID_ERR;
        }

    	// 登録画面で編集・入力されたデータをクリアする。
    	getLongserviceOfferDto().clear();

    	// エディットモードを設定
    	getLongserviceOfferDto().setEditMode(LongserviceOfferConstants.EDIT_MODE_RETURN);

    	// 初期画面を表示する
    	return LongserviceOfferConstants.VIEW_ID_EVENTLIST;
	}

    /**
     * スタッフ選択フォーム
     * @return　スタッフ検索画面
     */
    public String callStaffForm() {
    	//事前処理
    	settingEditData(getLongserviceOfferDto());
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
    	getEntryStaffSearchDto().clear();
    	getEntryStaffSearchDto().setNavigationCase(LongserviceOfferConstants.VIEW_ID_EDIT);
    	getEntryStaffSearchDto().setInitialFlag(true);
    	getEntryStaffSearchDto().setCompanyCd(getLongserviceOfferDto().getCompanyCd());
    	getEntryStaffSearchDto().setOnerCd(getLongserviceOfferDto().getOnerCd());
        //複数WindowID
    	getEntryStaffSearchDto().setWindowId(getLongserviceOfferDto().getWindowId());

    	// エディットモードを設定
    	getLongserviceOfferDto().setEditMode(LongserviceOfferConstants.EDIT_MODE_STAFF);
    	// 対象ソート番号の設定
    	getLongserviceOfferDto().setStaffSelectNo(getStaffSelectNo());

        return LongserviceOfferConstants.VIEWID_STAFFSELECT;
    }
    /**
     * 事前データ設定処理
     *
     * @param dto
     */
    private void settingEditData(LongserviceOfferDto dto) {

    	List entryList = dto.getUIOfferEntryList();
    	if(entryList==null) {
    		return;
    	}
    	for(int i = 0;i < entryList.size(); i++){
    		UIOfferEntry uIOfferEntry = (UIOfferEntry)entryList.get(i);
	        //店舗経験ラジオボタン値(社員orＰ／Ａ)
	        String staffType= uIOfferEntry.getStaffType();
	        //店舗経験年数
	        String expYears = !CommonUtil.isNull(uIOfferEntry.getExpKbn())?uIOfferEntry.getExpKbn().substring(1):"";
	        //画面表示用：社員暦プルダウン値設定
        	uIOfferEntry.setEmployeeYears(LongserviceOfferConstants.JOB_TYPE_EMPLOYEE.equals(staffType)?expYears:"");
        	//画面表示用：Ｐ／Ａ暦プルダウン値設定
        	uIOfferEntry.setParttimerYears(LongserviceOfferConstants.JOB_TYPE_PARTTIMER.equals(staffType)?expYears:"");
    	}

    }

	public LongserviceOfferDto getLongserviceOfferDto() {
		return longserviceOfferDto;
	}
	public void setLongserviceOfferDto(LongserviceOfferDto longserviceOfferDto) {
		this.longserviceOfferDto = longserviceOfferDto;
	}

	/**
	 * 登録内容チェックロジックの設定
	 * @return checkOfferLogic を戻します。
	 */
	public CheckOfferLogic getCheckOfferLogic() {
		return checkOfferLogic;
	}
	/**
	 * 登録内容チェックロジックの設定
	 * @param checkOfferLogic checkOfferLogic を設定。
	 */
	public void setCheckOfferLogic(CheckOfferLogic checkOfferLogic) {
		this.checkOfferLogic = checkOfferLogic;
	}

	public UpdateOfferLogic getUpdateUserLogic() {
		return updateUserLogic;
	}

	public void setUpdateUserLogic(UpdateOfferLogic updateUserLogic) {
		this.updateUserLogic = updateUserLogic;
	}

	public SearchOfferLogic getSearchOfferLogic() {
		return searchOfferLogic;
	}

	public void setSearchOfferLogic(SearchOfferLogic searchOfferLogic) {
		this.searchOfferLogic = searchOfferLogic;
	}

	public EventListDto getEventListDto() {
		return eventListDto;
	}

	public void setEventListDto(EventListDto eventListDto) {
		this.eventListDto = eventListDto;
	}
	public SearchOfferMiseLogic getSearchOfferMiseLogic() {
		return searchOfferMiseLogic;
	}

	public void setSearchOfferMiseLogic(SearchOfferMiseLogic searchOfferMiseLogic) {
		this.searchOfferMiseLogic = searchOfferMiseLogic;
	}

	public EntryStaffSearchDto getEntryStaffSearchDto() {
		return entryStaffSearchDto;
	}

	public void setEntryStaffSearchDto(EntryStaffSearchDto entryStaffSearchDto) {
		this.entryStaffSearchDto = entryStaffSearchDto;
	}

	public InsertOfferLogic getInsertOfferLogic() {
		return insertOfferLogic;
	}

	public void setInsertOfferLogic(InsertOfferLogic insertOfferLogic) {
		this.insertOfferLogic = insertOfferLogic;
	}

	public int getStaffSelectNo() {
		return staffSelectNo;
	}

	public void setStaffSelectNo(int staffSelectNo) {
		this.staffSelectNo = staffSelectNo;
	}

	public NationalEntryDto getNationalEntryDto() {
		return nationalEntryDto;
	}

	public void setNationalEntryDto(NationalEntryDto nationalEntryDto) {
		this.nationalEntryDto = nationalEntryDto;
	}
}

