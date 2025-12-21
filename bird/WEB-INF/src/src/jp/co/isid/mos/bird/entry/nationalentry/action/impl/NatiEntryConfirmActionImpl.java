/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.nationalentry.action.impl;

import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.nationalentry.action.NatiEntryConfirmAction;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryJoinInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiLongServiceInfo;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntryInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntryMiseInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.logic.GetNatiEntrySyokuiInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.logic.LongServiceInfoLogic;
import jp.co.isid.mos.bird.entry.nationalentry.util.CheckInputParam;
import jp.co.isid.mos.bird.entry.nationalentry.util.NationalEntryUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 事業方針説明会申込　確認画面アクション
 * 
 * @author xlee
 */
public class NatiEntryConfirmActionImpl implements NatiEntryConfirmAction {
	
	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID = "BEN015A07";
    
    /** アクションID定義:戻るアクション */
    public static final String backMove_ACTION_ID = "BEN015A08";
    
    /** アクションID定義:終了アクション */
    public static final String procEnd_ACTION_ID = "BEN015A09";
    
    /** アクションID定義:永年勤続申請アクション */
    public static final String moveLongService_ACTION_ID = "BEN015A10";
	
    /** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_EDIT    = "BEN015V01";
    
    /** VIEWID定義:オーナー選択画面 */
    private static final String VIEWID_EVENTLIST = "BEN091V01";
    
    /** VIEWID定義:永年勤続申込画面 */
    private static final String VIEWID_LONGSERVICE = "BEN018V01";
    
    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;
    
	/** 全国大会DTO：session */
	private NationalEntryDto nationalEntryDto;
    
    /** 各種イベント申込*/
    private EventListDto eventListDto;
	
    /** 全国大会： 申込情報*/
    private GetNatiEntryInfoLogic getNatiEntryInfoLogic;
    
    /** 全国大会：店舗情報 */
    private GetNatiEntryMiseInfoLogic getNatiEntryMiseInfoLogic;
    
    /** 全国大会：永年勤続情報 */
    private LongServiceInfoLogic longServiceInfoLogic;
    
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
    public GetNatiEntryMiseInfoLogic getNatiEntryMiseInfoLogic() {
        return getNatiEntryMiseInfoLogic;
    }

    /**
     * 入力欄追加処理または
     * 登録・変更・削除処理ロジックを設定します。
     * @param execNatiEntryRegistLogic　入力欄追加処理または登録・変更・削除処理ロジック
     */
    public void setNatiEntryMiseInfoLogic(GetNatiEntryMiseInfoLogic getNatiEntryMiseInfoLogic) {
        this.getNatiEntryMiseInfoLogic = getNatiEntryMiseInfoLogic;
    }
    
    /**
     *　入力欄追加処理または
     *　登録・変更・削除処理ロジックを取得します。
     * @return 　入力欄追加処理または登録・変更・削除処理ロジック
     */
    public LongServiceInfoLogic getLongServiceInfoLogic() {
        return longServiceInfoLogic;
    }

    /**
     * 入力欄追加処理または
     * 登録・変更・削除処理ロジックを設定します。
     * @param execNatiEntryRegistLogic　入力欄追加処理または登録・変更・削除処理ロジック
     */
    public void setLongServiceInfoLogic(LongServiceInfoLogic longServiceInfoLogic) {
        this.longServiceInfoLogic = longServiceInfoLogic;
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
        //ユーザ情報
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        //全国大会のDTOの取得
        NationalEntryDto nationalEntryDto = (NationalEntryDto) container.getComponent("nationalEntryDto");
        //店舗リスト
        GetNatiEntryMiseInfoLogic getNatiEntryMiseInfoLogic = (GetNatiEntryMiseInfoLogic) container.getComponent("nationalEntry.getNatiEntryMiseInfoLogic");
        // 職位リスト
        GetNatiEntrySyokuiInfoLogic getNatiEntrySyokuiInfoLogic = (GetNatiEntrySyokuiInfoLogic) container.getComponent("nationalEntry.getNatiEntrySyokuiInfoLogic");
        //申込情報
        GetNatiEntryInfoLogic getNatiEntryInfoLogic = (GetNatiEntryInfoLogic) container.getComponent("nationalEntry.getNatiEntryInfoLogic");
        //永年勤続情報
		LongServiceInfoLogic longServiceInfoLogic = (LongServiceInfoLogic) container.getComponent("nationalEntry.longServiceInfoLogic");
		
        //複数ウィンドウセッションキー生成
        MakeSessionKey make = new MakeSessionKey();
        String sessionKey = make._makeSessionKey();
        
        //システム日付、ユーザＩＤ、ユーザタイプ
        String sysDate = birdDateInfo.getSysDate();
        String userId = birdUserInfo.getUserID();
        String userType = birdUserInfo.getMstUser().getUserTypeCd();
        nationalEntryDto.setSysDate(sysDate);
        nationalEntryDto.setUserId(userId);
        nationalEntryDto.setUserType(userType);
        
        if(getEventListDto().getEntryTermKind() == 1) {
            /* 
             * ウィンドウID, エントリーコード, エントリー年, エントリー回, 会社コード , オーナーコード, 申込期間区分
             */
	        int windowId = getEventListDto().getWindowId();
	        String entryCd =  getEventListDto().getEntryCd();
	        String entryYear = getEventListDto().getEntryYear();
	        String entryKai = getEventListDto().getEntryKai();
	        String companyCd = getEventListDto().getCompanyCd();
	        String onerCd = getEventListDto().getOnerCd(); 
	        
	        //1.ウィンドウIDを設定してからその他コードを設定する
	        nationalEntryDto.setWindowId(windowId);
	        nationalEntryDto.setCondEntryCd(entryCd);
	        nationalEntryDto.setCondEntryYear(entryYear);
	        nationalEntryDto.setCondEntryKai(entryKai);
	        nationalEntryDto.setCondCompanyCd(companyCd);
	        nationalEntryDto.setCondOnerCd(onerCd);
	        
        	//2.セッションの設定
	        nationalEntryDto.setViewSessionKey( sessionKey );
	        nationalEntryDto.setSessioniKey( sessionKey );
        	
        	//3.画面区分を設定
        	nationalEntryDto.setViewKbn(NationalEntryDto.VIEW_KBN_V);
        	
        	//4. 申込情報
        	Map entryInfoMap = (Map) getNatiEntryInfoLogic.execute(nationalEntryDto);
        	
    		//5. DTOに設定
    		nationalEntryDto = NationalEntryUtil.setNatiEntryInfo(
    				nationalEntryDto,entryInfoMap,null);
    		
    		//6.　参加者の情報が１名ですが、すべて未入力の場合（初期値を持っている）
    		if(nationalEntryDto.getPrmJoinList().size() == 1 &&
    				CheckInputParam.chkAllJoinInfo(
        					(UINatiEntryJoinInfo)nationalEntryDto.getPrmJoinList().get(0))) {
    			if(nationalEntryDto.getPrmDutyEntryState().equals("")) {
    				nationalEntryDto.setNoDataFlg(NationalEntryDto.NO_DATA_ENTRY);
    			} else if(nationalEntryDto.getPrmDutyEntryState().equals("1")) {
    				nationalEntryDto.setNoDataFlg("");
    			}
    			nationalEntryDto.setPrmJoinList(null);
    		} else {
            	//7-1. 対象店舗リスト
            	List miseList = getNatiEntryMiseInfoLogic.execute(companyCd, onerCd, sysDate);
            	//7-2. 店舗リスト設定
            	nationalEntryDto.setCondListMise(miseList);
            	nationalEntryDto = NationalEntryUtil.selectionNm(nationalEntryDto);
            	
            	// 職位リスト
            	List syokuiList = getNatiEntrySyokuiInfoLogic.execute();
            	// 職位リスト設定
            	nationalEntryDto.setCondListSyokui(syokuiList);
    		}
        } else {
        	//1.未入力の参加者情報は表示しない為、リストから削除する
        	nationalEntryDto.setPrmJoinList(
        			NationalEntryUtil.removeJoinInfo(nationalEntryDto.getPrmDutyEntryState(),
        					nationalEntryDto.getPrmJoinList()));

        	//2．申込区分・オプショナルの名称の設定
        	nationalEntryDto = NationalEntryUtil.selectionNm(nationalEntryDto);
        	//3.画面区分を設定
        	nationalEntryDto.setViewKbn(NationalEntryDto.VIEW_KBN_E);
        }
        //永年勤続申込有効フラグ設定
        UINatiLongServiceInfo uiNatiLongServiceInfo = 
			longServiceInfoLogic.execute(
					nationalEntryDto.getCondEntryYear(), 
					nationalEntryDto.getSysDate(), 
					nationalEntryDto.getUserType());
        
        if(uiNatiLongServiceInfo == null) {
        	nationalEntryDto.setPrmLongServButFlg("1");
        } else {
        	nationalEntryDto.setPrmLongServButFlg("0");
        }
        // 自画面へ遷移
        return null;
    }

	/**
    * 終了　各種イベント一覧画面へ戻る処理
    * 
    * @return　画面遷移情報
    */	
	public String procEnd() {
		//入力情報のクリア
		getEventListDto().setReturnKind(1);
		getEventListDto().setWindowId(getNationalEntryDto().getWindowId());
		getNationalEntryDto().clear(NationalEntryDto.PGKBN_END);

		//各種イベント一覧へ遷移
		return VIEWID_EVENTLIST;
	}

    
    /**
     * 編集画面へ戻る処理
     * 
     * @return　画面遷移情報
     */	
	public String backMove() {

		//1.NationalEntryDtoをクリア
		getNationalEntryDto().clear(NationalEntryDto.PGKBN_BACK);
		
        //システム日付、ユーザＩＤ、ユーザタイプ
        String sysDate = getBirdDateInfo().getSysDate();
        String userId = getBirdUserInfo().getUserID();
        String userType = getBirdUserInfo().getMstUser().getUserTypeCd();
        getNationalEntryDto().setSysDate(sysDate);
        getNationalEntryDto().setUserId(userId);
        getNationalEntryDto().setUserType(userType);
		
    	//1-1. 対象店舗リスト
    	List miseList = getNatiEntryMiseInfoLogic().execute(
    			getNationalEntryDto().getCondCompanyCd(), 
    			getNationalEntryDto().getCondOnerCd(), 
    			getNationalEntryDto().getSysDate());
    	
    	//1-2. 店舗リスト設定
    	getNationalEntryDto().setCondListMise(miseList);
    	
    	//2. 申込情報
    	Map entryInfoMap = (Map) getNatiEntryInfoLogic().execute(getNationalEntryDto());
    	
		//3. DTOに設定
    	setNationalEntryDto(NationalEntryUtil.setNatiEntryInfo(
				getNationalEntryDto(), entryInfoMap, null));
    	getNationalEntryDto().setBackKbn(NationalEntryDto.BTN_TRUE);
    	
    	//編集画面へ遷移
		return VIEWID_EDIT;
	}
	
	/**
	 * 永年勤続画面へ遷移する処理
	 * 
	 * @return　画面遷移情報
	 */
	public String moveLongService() {
		
		//永年勤続申込情報を取得する
		UINatiLongServiceInfo longServiEntryKai = 
			getLongServiceInfoLogic().execute(
				getNationalEntryDto().getCondEntryYear(), 
				getNationalEntryDto().getSysDate(), 
				getNationalEntryDto().getUserType());
		
		//イベントリストの初期化
		getEventListDto().setInitFlag(EventListDto.INITFLAG_FROMNATIONAL);
		getEventListDto().setEntryTermKind(0);
		getEventListDto().setWindowId(getNationalEntryDto().getWindowId());
        getEventListDto().setEntryCd(longServiEntryKai.getEntryCd()); //永年勤続
        getEventListDto().setEntryYear(longServiEntryKai.getEntryYear());
        getEventListDto().setEntryKai(longServiEntryKai.getEntryKai());
        getEventListDto().setCompanyCd(getNationalEntryDto().getCondCompanyCd());
        getEventListDto().setOnerCd(getNationalEntryDto().getCondOnerCd());
        
        //永年勤続申請
		return VIEWID_LONGSERVICE;
	}
}
