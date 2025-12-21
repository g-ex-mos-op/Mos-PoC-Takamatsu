/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.nationalentry.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto.EntryStaffSearchDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.nationalentry.action.NatiEntryEditAction;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryJoinInfo;
import jp.co.isid.mos.bird.entry.nationalentry.logic.ExecNatiEntryRegistLogic;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntryInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntryMiseInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntrySyokuiInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.util.CheckInputParam;
import jp.co.isid.mos.bird.entry.nationalentry.util.NationalEntryUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * 事業方針説明会申込　編集画面アクション
 *
 * @author xlee
 */
public class NatiEntryEditActionImpl implements NatiEntryEditAction {

	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID =  "BEN015A01";

    /** アクションID定義:戻るアクションアクション */
    public static final String backMove_ACTION_ID = "BEN015A02";

	/** アクションID定義:スタッフ選択索アクション */
    public static final String callStaffForm_ACTION_ID = "BEN015A03";

    /** アクションID定義:スタッフ選択索アクション */
    public static final String cancelEntry_ACTION_ID = "BEN015A04";

    /** アクションID定義:入力欄の追加アクション */
    public static final String addInputSpace_ACTION_ID = "BEN015A05";

    /** アクションID定義:登録終了アクション */
    public static final String registEntry_ACTION_ID = "BEN015A06";

    /** VIEWID定義:編集画面ID */
    private static final String VIEWID_EDIT    = "BEN015V01";

    /** VIEWID定義:確認画面ID */
    private static final String VIEWID_CONFIRM = "BEN015V02";

    /** VIEWID定義:スタッフ選択画面ID */
    private static final String VIEWID_STAFFSELECT = "BEN093V01";

    /** VIEWID定義:イベント申込一覧画面ID */
    private static final String VIEWID_EVENTLIST = "BEN091V01";

    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;

	/** 全国大会DTO：session */
	private NationalEntryDto nationalEntryDto;

    /** スタッフ情報 */
    private EntryStaffSearchDto entryStaffSearchDto;

    /** 各種イベント申込*/
    private EventListDto eventListDto;

    /** 全国大会：入力欄追加・登録終了処理ロジック */
    private ExecNatiEntryRegistLogic execNatiEntryRegistLogic;

    /** 全国大会： */
    private GetNatiEntryInfoLogic getNatiEntryInfoLogic;

    /**
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * 日付情報を取得します。
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * 日付情報を設定します。
     * @param birdDateInfo 日付情報
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    /**
     * 全国大会DTOを取得します。:session
     * @return  全国大会DTO
     */
    public NationalEntryDto getNationalEntryDto() {
        return nationalEntryDto;
    }

    /**
     *  全国大会DTOを取得します。:session
     * @param nationalEntryDto　 全国大会DTO
     */
	public void setNationalEntryDto(NationalEntryDto nationalEntryDto) {
		this.nationalEntryDto = nationalEntryDto;
	}

    /**
     *  スタッフ情報DTOを取得します。
     * @return　スタッフ情報DTO
     */
	public EntryStaffSearchDto getEntryStaffSearchDto() {
		return entryStaffSearchDto;
	}

    /**
     *  スタッフ情報DTOを設定します。
     * @param entryStaffSearchDto　 スタッフ情報DTO
     */
	public void setEntryStaffSearchDto(EntryStaffSearchDto entryStaffSearchDto) {
		this.entryStaffSearchDto = entryStaffSearchDto;
	}

    /**
     *  各種イベント申込DTOを取得します。
     * @return　各種イベント申込DTO
     */
	public EventListDto getEventListDto() {
		return eventListDto;
	}

    /**
     *  各種イベント申込DTOを設定します。
     * @param eventListDto　 各種イベント申込DTO
     */
	public void setEventListDto(EventListDto eventListDto) {
		this.eventListDto = eventListDto;
	}

    /**
     *　ロジックを取得します。
     * @return 　入力欄追加処理または登録・変更・削除処理ロジック
     */
    public GetNatiEntryInfoLogic getNatiEntryInfoLogic() {
        return getNatiEntryInfoLogic;
    }

    /**
     *
     * ロジックを設定します。
     * @param execNatiEntryRegistLogic　入力欄追加処理または登録・変更・削除処理ロジック
     */
    public void setNatiEntryInfoLogic(GetNatiEntryInfoLogic getNatiEntryInfoLogic) {
        this.getNatiEntryInfoLogic = getNatiEntryInfoLogic;
    }

    /**
     *　入力欄追加処理または
     *　登録・変更・削除処理ロジックを取得します。
     * @return 　入力欄追加処理または登録・変更・削除処理ロジック
     */
    public ExecNatiEntryRegistLogic getExecNatiEntryRegistLogic() {
        return execNatiEntryRegistLogic;
    }

    /**
     * 入力欄追加処理または
     * 登録・変更・削除処理ロジックを設定します。
     * @param execNatiEntryRegistLogic　入力欄追加処理または登録・変更・削除処理ロジック
     */
    public void setExecNatiEntryRegistLogic(ExecNatiEntryRegistLogic execNatiEntryRegistLogic) {
        this.execNatiEntryRegistLogic = execNatiEntryRegistLogic;
    }

    /**
     * 初期処理
     *
     * @return 画面遷移情報
     */
    public String initialize() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //日付情報を取得
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        //全国大会のDTOの取得
        NationalEntryDto nationalEntryDto = (NationalEntryDto) container.getComponent("nationalEntryDto");
        //店舗リスト
        GetNatiEntryMiseInfoLogic getNatiEntryMiseInfoLogic = (GetNatiEntryMiseInfoLogic) container.getComponent("nationalEntry.getNatiEntryMiseInfoLogic");
        //職位リスト
        GetNatiEntrySyokuiInfoLogic getNatiEntrySyokuiInfoLogic = (GetNatiEntrySyokuiInfoLogic) container.getComponent("nationalEntry.getNatiEntrySyokuiInfoLogic");
        //申込情報
        GetNatiEntryInfoLogic getNatiEntryInfoLogic = (GetNatiEntryInfoLogic) container.getComponent("nationalEntry.getNatiEntryInfoLogic");

        nationalEntryDto.setSysDate(birdDateInfo.getSysDate());
        //複数ウィンドウセッションキー生成
        MakeSessionKey make = new MakeSessionKey();
        String sessionKey = make._makeSessionKey();
        /*
         * システム日付
         * ウィンドウID
         * エントリーコード
         * エントリー年
         * エントリー回
         * 会社コード
         * オーナーコード
         * 申込期間区分
         */
        if (getEventListDto().getInitFlag() == EventListDto.INITFLAG_ON) {

        	if(getEventListDto().getEntryTermKind() == 1) {
        		//初期表示の時、申込期間は終了だが、表示期間の内であれば、確認画面へ遷移する
        		return VIEWID_CONFIRM;
        	}
	        String sysDate = birdDateInfo.getSysDate();
	        int windowId = getEventListDto().getWindowId();
	        String entryCd =  getEventListDto().getEntryCd();
	        String entryYear = getEventListDto().getEntryYear();
	        String entryKai = getEventListDto().getEntryKai();
	        String companyCd = getEventListDto().getCompanyCd();
	        String onerCd = getEventListDto().getOnerCd();

	        //ウィンドウIDを設定してからその他コードを設定する
	        nationalEntryDto.setWindowId(windowId);
	        nationalEntryDto.setCondEntryCd(entryCd);
	        nationalEntryDto.setCondEntryYear(entryYear);
	        nationalEntryDto.setCondEntryKai(entryKai);
	        nationalEntryDto.setCondCompanyCd(companyCd);
	        nationalEntryDto.setCondOnerCd(onerCd);
	        nationalEntryDto.setUserId(getBirdUserInfo().getUserID());
	        nationalEntryDto.setUserType(getBirdUserInfo().getMstUser().getUserTypeCd());

        	//セッションの設定
	        nationalEntryDto.setViewSessionKey( sessionKey );
	        nationalEntryDto.setSessioniKey( sessionKey );

        	// 職位リスト
        	List syokuiList = getNatiEntrySyokuiInfoLogic.execute();
        	// 職位リスト設定
        	nationalEntryDto.setCondListSyokui(syokuiList);

        	//1.　画面区分を設定
        	nationalEntryDto.setViewKbn(NationalEntryDto.VIEW_KBN_E);
        	//2. 申込情報
        	Map entryInfoMap = (Map) getNatiEntryInfoLogic.execute(nationalEntryDto);

    		//3. DTOに設定
    		nationalEntryDto = NationalEntryUtil.setNatiEntryInfo(
    				nationalEntryDto,entryInfoMap,null);

        	nationalEntryDto.setErrFlg("");
        	nationalEntryDto.setInitFlg(NationalEntryDto.INITFLG_TRUE);
        	nationalEntryDto.setBtnKbn(NationalEntryDto.BTN_FALSE);
        	nationalEntryDto.setBackKbn(NationalEntryDto.BTN_FALSE);
        	//5. 画面起動フラグを処理値設定
        	getEventListDto().setInitFlag(0);

        	//4-1. 対象店舗リスト
        	List miseList = getNatiEntryMiseInfoLogic.execute(companyCd, onerCd, sysDate);
        	//4-2. 店舗リスト設定
        	nationalEntryDto.setCondListMise(miseList);

        } else if(getEntryStaffSearchDto().getReturnKind() != EntryStaffSearchDto.RETURNKIND_INIT){

            if (!make.isValidSessionKey( getNationalEntryDto().getViewSessionKey(),getNationalEntryDto().getSessionKey())) {
                return make.operationErr_VIEW_ID;
            }
            //DTO【販売状況一覧】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
        	nationalEntryDto.setWindowId(getEntryStaffSearchDto().getWindowId());
			//決定
			if(getEntryStaffSearchDto().getReturnKind() == EntryStaffSearchDto.RETURNKIND_SELECT){
				//検索条件の設定
				nationalEntryDto.setPrmJoinList(settJoinInfo(
						nationalEntryDto.getPrmJoinList(),getEntryStaffSearchDto().getMstStaff()));
			}
			getEntryStaffSearchDto().setReturnKind(EntryStaffSearchDto.RETURNKIND_INIT);
			nationalEntryDto.setInitFlg(NationalEntryDto.INITFLG_FALSE);
        } else {
        	//確認画面から編集画面へ戻った場合
        	if(NationalEntryDto.BTN_TRUE.equals(getNationalEntryDto().getBackKbn())) {
        		getNationalEntryDto().setErrFlg("");
        		getNationalEntryDto().setInitFlg(NationalEntryDto.INITFLG_TRUE);
        		getNationalEntryDto().setBtnKbn(NationalEntryDto.BTN_FALSE);
        		getNationalEntryDto().setEditKbn(NationalEntryDto.EDIT_KBN_UPDATE);
        		getNationalEntryDto().setBackKbn(NationalEntryDto.BTN_FALSE);
        	} else {
        		nationalEntryDto.setInitFlg(NationalEntryDto.INITFLG_FALSE);
        	}
        }
        // 自画面へ遷移
        return null;
    }

	/**
	　* 戻る処理　共通：各種イベント申込一覧へ遷移する
	　*
	　* @return 画面遷移情報
	　*/
	public String backMove() {
		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();

        if (!make.isValidSessionKey( getNationalEntryDto().getViewSessionKey(),getNationalEntryDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
    	//各種イベント一覧に戻る値を設定する
		getEventListDto().setWindowId(getNationalEntryDto().getWindowId());
		getEventListDto().setReturnKind(2);
		//入力情報のクリア
		getNationalEntryDto().clear(NationalEntryDto.PGKBN_END);

		//各種イベント一覧へ遷移
		return VIEWID_EVENTLIST;
	}

	/**
	 * スタッフ選択処理
	 *
	 * @return 画面遷移情報
	 */
	public String callStaffForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
    	getEntryStaffSearchDto().clear();
    	getEntryStaffSearchDto().setNavigationCase(VIEWID_EDIT);
    	getEntryStaffSearchDto().setInitialFlag(true);
    	getEntryStaffSearchDto().setCompanyCd(getNationalEntryDto().getCondCompanyCd());
    	getEntryStaffSearchDto().setOnerCd(getNationalEntryDto().getCondOnerCd());
        //複数WindowID
    	getEntryStaffSearchDto().setWindowId(getNationalEntryDto().getWindowId());

    	//入力した申込参加者情報を維持
    	getNationalEntryDto().setErrFlg("");
    	getNationalEntryDto().setPrmSeqNo(getNationalEntryDto().getPrmSeqNo());
    	getNationalEntryDto().setPrmJoinList(getNationalEntryDto().getPrmJoinList());
    	getNationalEntryDto().setBtnKbn(NationalEntryDto.BTN_FALSE);

        return VIEWID_STAFFSELECT;
	}

	/**
	 * 入力欄の追加処理
	 *
	 * @return 画面遷移情報
	 */
	public String addInputArea() {
		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();

        if (!make.isValidSessionKey( getNationalEntryDto().getViewSessionKey(),getNationalEntryDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }

		//１．画面上に存在するフォームを取得する
		List tmpJoinInfo = getNationalEntryDto().getPrmJoinList();

		/*
         * ３．参加者編集フォームの必須項目が未入力かどうかをチェック
		 * ４．参加者編集フォームのすべての項目が未入力かどうかをチェック
		 * cntReqItem[0]:必須項目が未入力であるフォーム数
		 * cntReqItem[1]:すべての項目が未入力であるフォーム数
		 */
		int [] cntReqItem = NationalEntryUtil.cntReqItem(
				getNationalEntryDto(), tmpJoinInfo);

		int notNullCnt = cntReqItem[0];
		int allNullCnt = cntReqItem[1];

		if(getNationalEntryDto().getEditKbn().equals(NationalEntryDto.EDIT_KBN_INSERT) &&
				notNullCnt == tmpJoinInfo.size() &&
				allNullCnt == tmpJoinInfo.size()) {
			//ＤＢへ登録せず、入力フォームだけを追加
			CheckInputParam.validater(getNationalEntryDto(), NationalEntryDto.BTN_INPUTAPPEND);
		} ////2007.01.26 追加　更新の場合、すべての参加者情報が空白であれば不参加で登録する為
		else if(
			(getNationalEntryDto().getEditKbn().equals(NationalEntryDto.EDIT_KBN_UPDATE) &&
			notNullCnt == tmpJoinInfo.size() && allNullCnt == tmpJoinInfo.size()) ||
			notNullCnt < tmpJoinInfo.size()) {

			//---------------------------------------------------------------------------------
			//入力内容のチェック
			CheckInputParam.validater(getNationalEntryDto(), NationalEntryDto.BTN_INPUTAPPEND);

			//2007.01.26 追加　更新の場合、すべての参加者情報が空白であれば不参加で登録する為
			if(getNationalEntryDto().getEditKbn().equals(NationalEntryDto.EDIT_KBN_UPDATE) &&
					notNullCnt == tmpJoinInfo.size() && allNullCnt == tmpJoinInfo.size()) {
				getNationalEntryDto().setPrmDutyEntryState(NationalEntryDto.ENTRY_STATE_NG);
			}
			//登録する情報を設定する
			Map regInfoMap = NationalEntryUtil.setRegistInfo(getNationalEntryDto());
			getExecNatiEntryRegistLogic().execute(regInfoMap);
			//---------------------------------------------------------------------------------
			//再検索する
	    	Map entryInfoMap = (Map) getNatiEntryInfoLogic().execute(getNationalEntryDto());
			//DTOに設定
			setNationalEntryDto(NationalEntryUtil.setNatiEntryInfo(
					getNationalEntryDto(), entryInfoMap, NationalEntryDto.KBN_RESEARCH_EXEC));
		}
		//６．新しい情報の再取得
		tmpJoinInfo =
			NationalEntryUtil.setReJoinInfo(tmpJoinInfo ,
					getNationalEntryDto().getPrmJoinList());

		int nextSeqNo = tmpJoinInfo.size() + 1;
		if(nextSeqNo >= 30) {
			//２．追加するフォームが30であれば、次からはフォーム追加できないように設定
			getNationalEntryDto().setPrmInputAppendFlg(NationalEntryDto.INPUT_APPEND_NG);
		}

		//７．すべて初期値を持っている参加者入力欄を追加する
		tmpJoinInfo.add(
	 		NationalEntryUtil.setDefaultJoinInfo(
	 				getNationalEntryDto().getUserId(), String.valueOf(nextSeqNo)));

		//８．新しい情報を再設定
		getNationalEntryDto().setPrmJoinList(tmpJoinInfo);

		//９．フォーム数を再設定
		getNationalEntryDto().setErrFlg("");
		getNationalEntryDto().setPrmSeqNo(String.valueOf(tmpJoinInfo.size()));
		getNationalEntryDto().setBtnKbn(NationalEntryDto.BTN_TRUE);

		return null;
	}

	/**
	 * 登録処理
	 *
	 * @return 画面遷移情報
	 */
	public String registEntry() {

		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();

        if (!make.isValidSessionKey( getNationalEntryDto().getViewSessionKey(),getNationalEntryDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }

		//１．入力内容のチェック
        CheckInputParam.validater(getNationalEntryDto(), NationalEntryDto.BTN_DBINSERT);

		//２.ＤＢへ登録
		Map regInfoMap = NationalEntryUtil.setRegistInfo(getNationalEntryDto());
		getExecNatiEntryRegistLogic().execute(regInfoMap);

		//３.確認画面へ遷移する
		return VIEWID_CONFIRM;
	}

	 /**
	 * 共通のスタッフ選択画面から取得した参加者情報を設定する。
	 * @param　mstStaff　スタッフ情報
	 * @return List　参加者１，２，３情報
	 */
	private List settJoinInfo(List joinInfoList , MstStaff mstStaff) {
	 	String seqNo = getNationalEntryDto().getPrmSeqNo();

	 	UINatiEntryJoinInfo uiNatiEntryJoinInfo =
	 		(UINatiEntryJoinInfo) joinInfoList.get(Integer.parseInt(seqNo)-1);
	 	//職位コード取得
	 	uiNatiEntryJoinInfo.setJobTypeCd(getNationalEntryDto().getSyokuiCd(mstStaff.getJob().trim()));
	 	uiNatiEntryJoinInfo.setLNameKna(mstStaff.getStaffLNameKna().trim());
	 	uiNatiEntryJoinInfo.setFNameKna(mstStaff.getStaffFNameKna().trim());
	 	uiNatiEntryJoinInfo.setLNameKj(mstStaff.getStaffLNameKj().trim());
	 	uiNatiEntryJoinInfo.setFNameKj(mstStaff.getStaffFNameKj().trim());
	 	uiNatiEntryJoinInfo.setSex(mstStaff.getSex().trim());
	 	uiNatiEntryJoinInfo.setAge(String.valueOf(NationalEntryUtil.chgToAgeFrDate(
	 			mstStaff.getBirthday().trim(), getBirdDateInfo().getSysDate())));

	 	if(mstStaff.getBirthday() != null && mstStaff.getBirthday().trim().length() != 0){
	 		String strBirthday = mstStaff.getBirthday().trim();
	 	 	// 西暦-年セット
		 	uiNatiEntryJoinInfo.setBirthday_Year(strBirthday.substring(0, 4));
	    	// 西暦-月セット
		 	uiNatiEntryJoinInfo.setBirthday_Month(strBirthday.substring(4, 6));
	    	// 西暦-日セット
		 	uiNatiEntryJoinInfo.setBirthday_Day(strBirthday.substring(6, 8));
	 	}

	 	uiNatiEntryJoinInfo.setMiseCd(mstStaff.getMiseCd1());
	 	List nJoinInfoList = new ArrayList();
	 	for(int i = 0 ; i < joinInfoList.size(); i++) {
	 		if(Integer.parseInt(seqNo) == (i+1)) {
	 			nJoinInfoList.add(uiNatiEntryJoinInfo);
	 		} else {
	 			nJoinInfoList.add(joinInfoList.get(i));
	 		}
	 	}
	 	return nJoinInfoList;
	}
}
