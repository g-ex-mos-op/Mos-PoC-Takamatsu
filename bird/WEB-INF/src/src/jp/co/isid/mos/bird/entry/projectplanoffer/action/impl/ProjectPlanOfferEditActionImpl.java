/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.dto.EntryStaffSearchDto;
import jp.co.isid.mos.bird.entry.commonform.entrystaffsearch.entity.MstStaff;
import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.action.ProjectPlanOfferEditAction;
import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferBakDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferMiseInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferInfoLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferMiseInfoLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferRegistLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.util.ProjectPlanOfferUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 事業方針説明会申込　編集画面アクション
 * 
 * @author xlee
 */
public class ProjectPlanOfferEditActionImpl implements ProjectPlanOfferEditAction {
	
	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID =  "BEN011A01";
    
	/** アクションID定義:タブ切替アクション */
    public static final String changeTab_ACTION_ID = "BEN011A09";
    
	/** アクションID定義:店舗切替アクション */
    public static final String changeMise_ACTION_ID = "BEN011A10";
    
	/** アクションID定義:スタッフ選択索アクション */
    public static final String callStaffForm_ACTION_ID = "BEN011A11";
    
    /** アクションID定義:登録終了アクション */
    public static final String registOffer_ACTION_ID = "BEN011A03";
    
    /** アクションID定義:戻るアクションアクション */
    public static final String backMove_ACTION_ID = "BEN011A02";
	
    /** VIEWID定義:編集画面ID */
    private static final String VIEWID_EDIT    = "BEN011V01";
    
    /** VIEWID定義:委任状画面ID */
    private static final String VIEWID_ININ = "BEN011V02";
    
    /** VIEWID定義:確認画面ID */
    private static final String VIEWID_CONFIRM = "BEN011V03";
    
    /** VIEWID定義:スタッフ選択画面ID */
    private static final String VIEWID_STAFFSELECT = "BEN093V01";
    
    /** VIEWID定義:イベント申込一覧画面ID */
    private static final String VIEWID_EVENTLIST = "BEN091V01";
    
    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;
    
	/** 事業方針説明会申込：session */
	private ProjectPlanOfferDto projectPlanOfferDto;
	
	/** 事業方針説明会申込：保持用session */
	ProjectPlanOfferBakDto projectPlanOfferBakDto;
	
    /** スタッフ情報 */    
    private EntryStaffSearchDto entryStaffSearchDto;
    
    /** 各種イベント申込*/
    private EventListDto eventListDto;
	
    /** 事業方針説明会申込 */
    private CheckOfferInputParamLogic checkOfferInputParamLogic;
    
    /** 事業方針説明会申込 */
    private GetOfferMiseInfoLogic getOfferMiseInfoLogic;
    
    /** 事業方針説明会申込 */
    private GetOfferRegistLogic getOfferRegistLogic;
    
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
     * 事業方針説明会申込DTOを取得します。:session
     * @return  事業方針説明会申込DTO
     */
    public ProjectPlanOfferDto getProjectPlanOfferDto() {
        return projectPlanOfferDto;
    }

    /**
     *  事業方針説明会申込DTOを設定します。
     * @param buyingListViewDto　 事業方針説明会申込DTO
     */
    public void setProjectPlanOfferDto(ProjectPlanOfferDto projectPlanOfferDto) {
        this.projectPlanOfferDto = projectPlanOfferDto;
    }
    
    /**
     * 事業方針説明会申込DTOを取得します。:session
     * @return  事業方針説明会申込DTO
     */
    public ProjectPlanOfferBakDto getProjectPlanOfferBakDto() {
        return projectPlanOfferBakDto;
    }

    /**
     *  事業方針説明会申込DTOを設定します。
     * @param buyingListViewDto　 事業方針説明会申込DTO
     */
    public void setProjectPlanOfferBakDto(ProjectPlanOfferBakDto projectPlanOfferBakDto) {
        this.projectPlanOfferBakDto = projectPlanOfferBakDto;
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
     * 入力データチェックロジックを取得します。
     * @return 　入力データチェックロジック
     */
    public CheckOfferInputParamLogic getCheckOfferInputParamLogic() {
        return checkOfferInputParamLogic;
    }

    /**
     * 入力データチェックロジックを設定します。
     * @param checkOfferInputParamLogic　入力データチェックロジック
     */
    public void setCheckOfferInputParamLogic(CheckOfferInputParamLogic checkOfferInputParamLogic) {
        this.checkOfferInputParamLogic = checkOfferInputParamLogic;
    }
    
    /**
     * 店情報取得ロジックを取得します。
     * @return 　 店情報取得ロジック
     */
    public GetOfferMiseInfoLogic getGetOfferMiseInfoLogic() {
        return getOfferMiseInfoLogic;
    }

    /**
     *  店情報取得ロジックを設定します。
     * @param getOfferMiseInfoLogic　 店情報取得ロジック
     */
    public void setGetOfferMiseInfoLogic(GetOfferMiseInfoLogic getOfferMiseInfoLogic) {
        this.getOfferMiseInfoLogic = getOfferMiseInfoLogic;
    }
    
    /**
     *登録・変更・削除処理ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetOfferRegistLogic getOfferRegistLogic() {
        return getOfferRegistLogic;
    }

    /**
     * 登録・変更・削除処理ロジックを設定します。
     * @param getOfferRegistLogic　オーナーコード取得ロジック
     */
    public void setGetOfferRegistLogic(GetOfferRegistLogic getOfferRegistLogic) {
        this.getOfferRegistLogic = getOfferRegistLogic;
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
        //事業方針説明会申込のDTOの取得
        ProjectPlanOfferDto projectPlanOfferDto = (ProjectPlanOfferDto) container.getComponent("projectPlanOfferDto");
        //店舗リスト
        GetOfferMiseInfoLogic getOfferMiseInfoLogic = (GetOfferMiseInfoLogic) container.getComponent("projectplanoffer.getOfferMiseInfoLogic");
        //申込情報
        GetOfferInfoLogic getOfferInfoLogic = (GetOfferInfoLogic) container.getComponent("projectplanoffer.getOfferInfoLogic");
        
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
        if (getEventListDto().getInitFlag() == 1) {
        	
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
	        projectPlanOfferDto.setWindowId(windowId);
        	projectPlanOfferDto.setCondEntryCd(entryCd);
        	projectPlanOfferDto.setCondEntryYear(entryYear);
        	projectPlanOfferDto.setCondEntryKai(entryKai);
        	projectPlanOfferDto.setCondCompanyCd(companyCd);
        	projectPlanOfferDto.setCondOnerCd(onerCd);
        	//セッションの設定
            projectPlanOfferDto.setViewSessionKey( sessionKey );
            projectPlanOfferDto.setSessioniKey( sessionKey );
        	
        	//1-1. 対象店舗リスト
        	List miseList = getOfferMiseInfoLogic.execute(companyCd, onerCd, sysDate);
        	//1-2. 店舗リスト設定
        	projectPlanOfferDto.setCondListMise(miseList);
        	
        	//2. 申込情報
        	projectPlanOfferDto = (ProjectPlanOfferDto) getOfferInfoLogic.execute(projectPlanOfferDto);
        	List offerInfoList = projectPlanOfferDto.getInitInfoList();
    		
    		//3. 画面へDBから取得した値を設定する
    		projectPlanOfferDto = ProjectPlanOfferUtil.setOfferInfo(projectPlanOfferDto, offerInfoList, null);
        	
        	//4. 一号店コードと支部名設定
        	projectPlanOfferDto = ProjectPlanOfferUtil.choiceMise(
        			projectPlanOfferDto, getCheckOfferInputParamLogic());
        	//5.
        	projectPlanOfferDto.setPrmMiseCd(projectPlanOfferDto.getPrmMiseCdList()[1]);
        	projectPlanOfferDto.setSibuName(projectPlanOfferDto.getSibuNameList()[1]);
        	projectPlanOfferDto.setMiseName(projectPlanOfferDto.getMiseNameList()[1]);
    		
        	//6. 初期タブ設定
        	projectPlanOfferDto.setPrmTabNo(ProjectPlanOfferDto.TABNO_DUTY);
        	projectPlanOfferDto.setErrFlg("");

        	//7. 画面起動フラグを処理値設定
        	getEventListDto().setInitFlag(0);
        } else if(getEntryStaffSearchDto().getReturnKind() != 0){
        	
            if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
                return make.operationErr_VIEW_ID;
            }
            //DTO【販売状況一覧】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
        	projectPlanOfferDto.setWindowId(getEntryStaffSearchDto().getWindowId());
			//決定
			if(getEntryStaffSearchDto().getReturnKind() == 1){
				getProjectPlanOfferDto().setPrmTabNo(getProjectPlanOfferDto().getPrmTabNo());
				//検索条件の設定
				projectPlanOfferDto.setPrmJoinPersonList(settJoinPersInfo(getEntryStaffSearchDto().getMstStaff()));
			}
			getEntryStaffSearchDto().setReturnKind(0);
        }
        // 自画面へ遷移
        return null;
    }

    /**
     * 店舗を変更した場合、それに対する支部名を表示するように設定する
     * 
     * @return 画面遷移情報
     */
	public String changeTab() {
		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        
        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(), getProjectPlanOfferDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        //店情報設定
		String [] tmpMiseCdL = getProjectPlanOfferDto().getPrmMiseCdList();
		String [] tmpMiseNameL = getProjectPlanOfferDto().getMiseNameList();
		String [] tmpSibuNameL = getProjectPlanOfferDto().getSibuNameList();
		
		if(tmpMiseCdL == null) {
			tmpMiseCdL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		if(tmpMiseNameL == null) {
			tmpMiseNameL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		if(tmpSibuNameL == null) {
			tmpSibuNameL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		//タブに対するデータを保持する
		String prevTabNo = getProjectPlanOfferDto().getPrmPrevTabNo();
		String tmpMiseCd = getProjectPlanOfferDto().getPrmMiseCd();
		
		//エラーが発生した状態で、タブ移動があった場合
		getProjectPlanOfferDto().setErrFlg(null);
		
		if(!prevTabNo.equals(ProjectPlanOfferDto.TABNO_DUTY)) {
			for (Iterator ite = getProjectPlanOfferDto().getCondListMise().iterator(); ite.hasNext();) {
			
	    		UIOfferMiseInfo uiOfferMiseInfo = (UIOfferMiseInfo) ite.next();
			
				if(!getCheckOfferInputParamLogic().isNull(tmpMiseCd) && tmpMiseCd.equals(uiOfferMiseInfo.getMiseCd())) {
					tmpMiseCdL[Integer.parseInt(prevTabNo)] = tmpMiseCd;
					tmpMiseNameL[Integer.parseInt(prevTabNo)] = uiOfferMiseInfo.getMiseNameKj();
					tmpSibuNameL[Integer.parseInt(prevTabNo)] = uiOfferMiseInfo.getSibuName();
					break;
				}
			}
		}
		getProjectPlanOfferDto().setPrmMiseCdList(tmpMiseCdL);
		getProjectPlanOfferDto().setMiseNameList(tmpMiseNameL);
		getProjectPlanOfferDto().setSibuNameList(tmpSibuNameL);
		
		String tabNo = getProjectPlanOfferDto().getPrmTabNo();
		//店を変えた後、支部名を変更
		getProjectPlanOfferDto().setPrmMiseCd(getProjectPlanOfferDto().getPrmMiseCdList()[Integer.parseInt(tabNo)]);
		getProjectPlanOfferDto().setSibuName(getProjectPlanOfferDto().getSibuNameList()[Integer.parseInt(tabNo)]);
		
    	//読み直し
    	//ウィンドウIDを設定する
    	getProjectPlanOfferBakDto().setWindowId(getProjectPlanOfferDto().getWindowId());
		if(tabNo.equals(ProjectPlanOfferDto.TABNO_DUTY)) {
	    	getProjectPlanOfferDto().setPrmDutyOnerName(getProjectPlanOfferBakDto().getBakDutyOnerName());
	    	getProjectPlanOfferDto().setPrmDutySoufuName(getProjectPlanOfferBakDto().getBakDutySofuName());
	    	getProjectPlanOfferDto().setPrmDutyName(getProjectPlanOfferBakDto().getBakDutyName());
	    	getProjectPlanOfferDto().setPrmDutyJobType(getProjectPlanOfferBakDto().getBakDutyJobType());
	    	getProjectPlanOfferDto().setPrmDutyAddress1(getProjectPlanOfferBakDto().getBakDutyAddress1());
	    	getProjectPlanOfferDto().setPrmDutyAddress2(getProjectPlanOfferBakDto().getBakDutyAddress2());
	    	getProjectPlanOfferDto().setPrmDutyAddress3(getProjectPlanOfferBakDto().getBakDutyAddress3());
	    	getProjectPlanOfferDto().setPrmDutyTel(getProjectPlanOfferBakDto().getBakDutyTel());
	    	getProjectPlanOfferDto().setPrmDutyZip(getProjectPlanOfferBakDto().getBakDutyZip());
	    	getProjectPlanOfferDto().setPrmMiseCdList(getProjectPlanOfferBakDto().getBakMiseCdList());
	    	getProjectPlanOfferDto().setMiseNameList(getProjectPlanOfferBakDto().getBakMiseNameList());
	    	getProjectPlanOfferDto().setSibuNameList(getProjectPlanOfferBakDto().getBakSibuNameList());
	    	
	    	//選択されているタブ以外のタブの情報を保持する為
	    	getProjectPlanOfferBakDto().setBakJoinPersonList(getProjectPlanOfferDto().getPrmJoinPersonList());
		} else {
    		getProjectPlanOfferBakDto().setBakDutyOnerName(getProjectPlanOfferDto().getPrmDutyOnerName());
    		getProjectPlanOfferBakDto().setBakDutySoufuName(getProjectPlanOfferDto().getPrmDutySoufuName());
    		getProjectPlanOfferBakDto().setBakDutyName(getProjectPlanOfferDto().getPrmDutyName());
    		getProjectPlanOfferBakDto().setBakDutyJobType(getProjectPlanOfferDto().getPrmDutyJobType());
    		getProjectPlanOfferBakDto().setBakDutyAddress1(getProjectPlanOfferDto().getPrmDutyAddress1());
    		getProjectPlanOfferBakDto().setBakDutyAddress2(getProjectPlanOfferDto().getPrmDutyAddress2());
    		getProjectPlanOfferBakDto().setBakDutyAddress3(getProjectPlanOfferDto().getPrmDutyAddress3());
    		getProjectPlanOfferBakDto().setBakDutyTel(getProjectPlanOfferDto().getPrmDutyTel());
    		getProjectPlanOfferBakDto().setBakDutyZip(getProjectPlanOfferDto().getPrmDutyZip());
    		getProjectPlanOfferBakDto().setBakMiseCdList(getProjectPlanOfferDto().getPrmMiseCdList());
    		getProjectPlanOfferBakDto().setBakMiseNameList(getProjectPlanOfferDto().getMiseNameList());
    		getProjectPlanOfferBakDto().setBakSibuNameList(getProjectPlanOfferDto().getSibuNameList());
    		
    		//選択されているタブ以外のタブの情報を保持する為
    		getProjectPlanOfferBakDto().setBakJoinPersonList(getProjectPlanOfferDto().getPrmJoinPersonList());
		}
		return null;
	}
	
    /**
     * オーナーコード入力画面へ戻る処理
     * 
     * @return　画面遷移情報
     */	
	public String changeMise() {

		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        
        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
		
		ProjectPlanOfferUtil.choiceMise(
				getProjectPlanOfferDto(), 
				getCheckOfferInputParamLogic()
				);
		
		int tabNo = Integer.parseInt(getProjectPlanOfferDto().getPrmTabNo());
		//店を変えた後、支部名を変更
		getProjectPlanOfferDto().setPrmMiseCd(getProjectPlanOfferDto().getPrmMiseCdList()[tabNo]);
		getProjectPlanOfferDto().setSibuName(getProjectPlanOfferDto().getSibuNameList()[tabNo]);
		
		return null;
	}
	
    /**
     * スタッフ選択フォーム
     * 
     * @return　オーナー検索画面
     */
    public String callStaffForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
    	getEntryStaffSearchDto().clear();
    	getEntryStaffSearchDto().setNavigationCase(VIEWID_EDIT);
    	getEntryStaffSearchDto().setInitialFlag(true);
    	getEntryStaffSearchDto().setCompanyCd(getProjectPlanOfferDto().getCondCompanyCd());
    	getEntryStaffSearchDto().setOnerCd(getProjectPlanOfferDto().getCondOnerCd());
        //複数WindowID
    	getEntryStaffSearchDto().setWindowId(getProjectPlanOfferDto().getWindowId());
        //オーナーコードを保持
        getProjectPlanOfferDto().setPrmTabNo(getProjectPlanOfferDto().getPrmTabNo());
        getProjectPlanOfferDto().setPrmJoinPersonList(getProjectPlanOfferDto().getPrmJoinPersonList());
        
        return VIEWID_STAFFSELECT;
    }
	
    /**
     * タイトル切替処理
     * 
     * @return　画面遷移情報
     */
    public String registOffer() {

		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        
        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        //タブの移動なしで、登録した場合、以前データを設定する
		getProjectPlanOfferBakDto().setBakJoinPersonList(getProjectPlanOfferDto().getPrmJoinPersonList());
        
        //1.　登録・更新・削除情報を設定
    	Map paramChkMap = ProjectPlanOfferUtil.setEntityRegInfoMap(
    			getProjectPlanOfferDto(), 
    			getCheckOfferInputParamLogic());
    	
    	//2007.1.15　登録・終了が行われる画面モードを設定
    	getProjectPlanOfferDto().setValChkMode(ProjectPlanOfferDto.VALCHK_MODE_EDIT);
        
    	//2.　登録・更新・削除情報を設定
    	Map paramMap = ProjectPlanOfferUtil.setRegistInfo(
    			getProjectPlanOfferDto(), 
    			paramChkMap,
    			getCheckOfferInputParamLogic());
    	
    	if(paramMap == null) {
    		 getCheckOfferInputParamLogic().validater(getProjectPlanOfferDto(), paramChkMap);
    		//3.委任状
    		return VIEWID_ININ;
    	} else {
    		String [] procKbn =  (String[]) paramMap.get(ProjectPlanOfferDto.MAP_KEY_PROC);
    		
    	    if(procKbn[0].equals(ProjectPlanOfferDto.PROC_KBN_DELETE) &&
    				procKbn[1].equals(ProjectPlanOfferDto.PROC_KBN_DELETE)) {
    	    	if(getProjectPlanOfferDto().getPrmEditKbn().equals(ProjectPlanOfferDto.EDIT_KBN_INSERT)) {
    	    		//新規の場合、
    	    		//確認用
    	    		getCheckOfferInputParamLogic().validater(getProjectPlanOfferDto(), paramChkMap);
    	    		getProjectPlanOfferDto().setPrmInfo(paramChkMap);
    	    		getProjectPlanOfferDto().setDelFlg(ProjectPlanOfferDto.PROC_KBN_DELETE);
    	    		return VIEWID_CONFIRM;
    	    	} else {	
    			//削除処理の場合、削除フラグの設定
    	    		getProjectPlanOfferDto().setDelFlg(ProjectPlanOfferDto.PROC_KBN_DELETE);
    	    	}
    		} else {
    			if(procKbn[0].equals(ProjectPlanOfferDto.PROC_KBN_INSERT) &&
    					((List)paramMap.get(ProjectPlanOfferDto.MAP_KEY_JOIN)).size() == 0) {
    				//申込責任者情報をセッションへ設定する
    	    		getProjectPlanOfferBakDto().setBakDutyOnerName(getProjectPlanOfferDto().getPrmDutyOnerName());
    	    		getProjectPlanOfferBakDto().setBakDutySoufuName(getProjectPlanOfferDto().getPrmDutySoufuName());
    	    		getProjectPlanOfferBakDto().setBakDutyName(getProjectPlanOfferDto().getPrmDutyName());
    	    		getProjectPlanOfferBakDto().setBakDutyJobType(getProjectPlanOfferDto().getPrmDutyJobType());
    	    		getProjectPlanOfferBakDto().setBakDutyAddress1(getProjectPlanOfferDto().getPrmDutyAddress1());
    	    		getProjectPlanOfferBakDto().setBakDutyAddress2(getProjectPlanOfferDto().getPrmDutyAddress2());
    	    		getProjectPlanOfferBakDto().setBakDutyAddress3(getProjectPlanOfferDto().getPrmDutyAddress3());
    	    		getProjectPlanOfferBakDto().setBakDutyTel(getProjectPlanOfferDto().getPrmDutyTel());
    	    		getProjectPlanOfferBakDto().setBakDutyZip(getProjectPlanOfferDto().getPrmDutyZip());
    			}
    	        //3. 入力データチェック
    	        getCheckOfferInputParamLogic().validater(getProjectPlanOfferDto(), paramChkMap);
    	        
    			if(procKbn[1].equals(ProjectPlanOfferDto.PROC_KBN_UPDATE)) {
    				//委任状の処理区分が更新（UPDATE）の場合
    				return VIEWID_ININ;
    			}
            	//4. 登録または更新者ID
        	    paramMap.put(ProjectPlanOfferDto.MAP_KEY_USERID, getBirdUserInfo().getUserID());
    		}
    	    //5. DB処理
    	    getOfferRegistLogic().execute(paramMap);
    	    
    	    //6.　確認用の情報
    	    getProjectPlanOfferDto().setPrmInfo(paramMap);
    	    
    	    //7. 確認画面へ遷移
    	    return VIEWID_CONFIRM;
    	}
    }


	/**
     * 各種イベント一覧画面へ戻る処理
     * 
     * @return　画面遷移情報
     */	
	public String backMove() {
		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        
        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }		
    	//入力情報のクリア
		getEventListDto().setWindowId(getProjectPlanOfferDto().getWindowId());
		getEventListDto().setReturnKind(2);
		getProjectPlanOfferDto().clear();
    	getProjectPlanOfferBakDto().clear();
    	
		//各種イベント一覧へ遷移
		return VIEWID_EVENTLIST;
	}
    
    /**
     * 共通のスタッフ選択画面から取得した参加者情報を設定する。
     * @param　mstStaff　スタッフ情報
     * @return List　参加者１，２，３情報
     */
    private List settJoinPersInfo(MstStaff mstStaff) {
    	String tabNo = getProjectPlanOfferDto().getPrmTabNo();
    	
    	UIOfferJoinPersonInfo uiOfferJoinPersonInfo =  (UIOfferJoinPersonInfo) getProjectPlanOfferBakDto().getBakJoinPersonList().get(Integer.parseInt(tabNo) - 1);
    	uiOfferJoinPersonInfo.setLNameKna(mstStaff.getStaffLNameKna().trim());
    	uiOfferJoinPersonInfo.setFNameKna(mstStaff.getStaffFNameKna().trim());
    	uiOfferJoinPersonInfo.setLNameKj(mstStaff.getStaffLNameKj().trim());
    	uiOfferJoinPersonInfo.setFNameKj(mstStaff.getStaffFNameKj().trim());
    	uiOfferJoinPersonInfo.setSex(mstStaff.getSex().trim());
    	uiOfferJoinPersonInfo.setAge("");
    	uiOfferJoinPersonInfo.setTabNo(tabNo.trim());
    	uiOfferJoinPersonInfo.setMiseCd(mstStaff.getMiseCd1());
    		
    	for (Iterator ite = getProjectPlanOfferDto().getCondListMise().iterator(); ite.hasNext();) {
    		
    		UIOfferMiseInfo uiOfferMiseInfo = (UIOfferMiseInfo) ite.next();
    	
    		if(uiOfferMiseInfo.getMiseCd().equals(mstStaff.getMiseCd1())) {
    			getProjectPlanOfferDto().setPrmMiseCd(uiOfferMiseInfo.getMiseCd());
    			getProjectPlanOfferDto().setSibuName(uiOfferMiseInfo.getSibuName());
    			getProjectPlanOfferDto().setMiseName(uiOfferMiseInfo.getMiseNameKj());
    			
    			break;
    		}
    	} 
    	
		ProjectPlanOfferUtil.choiceMise(
				getProjectPlanOfferDto(), 
				getCheckOfferInputParamLogic()
				);
    	
    	//	年齢
    	//----------------------------------------------------------
    	String birthDay = mstStaff.getBirthday().trim();
    	int age = 0;
    	if(!getCheckOfferInputParamLogic().isNull(birthDay)) {
        	String sysDt = getBirdDateInfo().getSysDate();
        	
	    	int sysYY = Integer.parseInt(sysDt.substring(0,4));
	    	int sysMM = Integer.parseInt(sysDt.substring(4,6));
	    	int sysDD = Integer.parseInt(sysDt.substring(6,8));
	    	
	    	int birthYY = Integer.parseInt(birthDay.substring(0,4));
	    	int birthMM = Integer.parseInt(birthDay.substring(4,6));
	    	int birthDD = Integer.parseInt(birthDay.substring(6,8));
	    	
	    	age = sysYY - birthYY;
	    	if(sysMM < birthMM) {
	    		age -= 1;
	    	} else if(sysMM == birthMM) {
	    		if(sysDD < birthDD) {
	    			age -= 1;
	    		}
	    	}
	    	uiOfferJoinPersonInfo.setAge(String.valueOf(age));
    	}
    	//-----------------------------------------------------------
    	
    	List joinPersInfoList = new ArrayList();
    	if(tabNo.equals("1")) {
    		joinPersInfoList.add(uiOfferJoinPersonInfo);
    		joinPersInfoList.add(getProjectPlanOfferBakDto().getBakJoinPersonList().get(1));
    		joinPersInfoList.add(getProjectPlanOfferBakDto().getBakJoinPersonList().get(2));
    	} else if(tabNo.equals("2")) {
    		joinPersInfoList.add(getProjectPlanOfferBakDto().getBakJoinPersonList().get(0));
    		joinPersInfoList.add(uiOfferJoinPersonInfo);
    		joinPersInfoList.add(getProjectPlanOfferBakDto().getBakJoinPersonList().get(2));
    	} else if(tabNo.equals("3")) {
    		joinPersInfoList.add(getProjectPlanOfferBakDto().getBakJoinPersonList().get(0));
    		joinPersInfoList.add(getProjectPlanOfferBakDto().getBakJoinPersonList().get(1));
    		joinPersInfoList.add(uiOfferJoinPersonInfo);
    	}
    	return joinPersInfoList;
	}
}
