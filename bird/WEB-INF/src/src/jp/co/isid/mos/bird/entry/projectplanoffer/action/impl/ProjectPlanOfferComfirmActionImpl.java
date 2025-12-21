/*
 * 作成日: 2006/11/21
 *
 */
package jp.co.isid.mos.bird.entry.projectplanoffer.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.eventlist.dto.EventListDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.action.ProjectPlanOfferConfirmAction;
import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferBakDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferMiseInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferInfoLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.GetOfferMiseInfoLogic;
import jp.co.isid.mos.bird.entry.projectplanoffer.util.ProjectPlanOfferUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 事業方針説明会申込　確認画面アクション
 * 
 * 更新日 2011/02/18 ASPAC 申込終了日以降にイベント名称のリンクを押下するとシステムエラーが発生。
 *                   出席者用のインデックス(int personIndex = 1;)を追加し対応しました。
 * @author xlee
 */
public class ProjectPlanOfferComfirmActionImpl implements ProjectPlanOfferConfirmAction {
	
	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID = "BEN011A07";
    
    /** アクションID定義:初期化アクション */
    public static final String backMove_ACTION_ID = "BEN011A08";
	
    /** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_EDIT    = "BEN011V01";
    
    /** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_ININ = "BEN011V02";
    
    /** VIEWID定義:オーナー選択画面 */
    private static final String VIEWID_EVENTLIST = "BEN091V01";
    
    
    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;
    
	/** 事業方針説明会申込：session */
	private ProjectPlanOfferDto projectPlanOfferDto;
	
	/** 事業方針説明会申込：保持用session */
	ProjectPlanOfferBakDto projectPlanOfferBakDto;
    
    /** 各種イベント申込*/
    private EventListDto eventListDto;
	
    /** 入力データチェックロジック */
    private CheckOfferInputParamLogic checkOfferInputParamLogic;
    
    private GetOfferInfoLogic getOfferInfoLogic;
    
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
     * 各種イベント申込DTOを取得します。
     * @return　各種イベント申込DTO
     */
	public EventListDto getEventListDto() {
		return eventListDto;
	}
	
    /**
     * 各種イベント申込DTOを設定します。
     * @param eventListDto　各種イベント申込DTO
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
     * 入力データチェックロジックを取得します。
     * @return 　入力データチェックロジック
     */
    public GetOfferInfoLogic getGetOfferInfoLogic() {
        return getOfferInfoLogic;
    }

    /**
     * 入力データチェックロジックを設定します。
     * @param checkOfferInputParamLogic　入力データチェックロジック
     */
    public void setGetOfferInfoLogic(GetOfferInfoLogic getOfferInfoLogic) {
        this.getOfferInfoLogic = getOfferInfoLogic;
    }
    
    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
   	
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        //日付情報
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        //事業方針説明会申込のDTOの取得
        ProjectPlanOfferDto projectPlanOfferDto = (ProjectPlanOfferDto) container.getComponent("projectPlanOfferDto");
        //店舗リスト取得ロジック
        GetOfferMiseInfoLogic getOfferMiseInfoLogic = (GetOfferMiseInfoLogic) container.getComponent("projectplanoffer.getOfferMiseInfoLogic");
        //申込情報取得ロジック
        GetOfferInfoLogic getOfferInfoLogic = (GetOfferInfoLogic) container.getComponent("projectplanoffer.getOfferInfoLogic");

        if(getEventListDto().getEntryTermKind() == 1) {
	        String sysDate = birdDateInfo.getSysDate();
	        int windowId = getEventListDto().getWindowId();
	        String entryCd =  getEventListDto().getEntryCd(); 
	        String entryYear = getEventListDto().getEntryYear(); 
	        String entryKai = getEventListDto().getEntryKai(); 
	        String companyCd = getEventListDto().getCompanyCd();  
	        String onerCd = getEventListDto().getOnerCd(); 
	        
	        projectPlanOfferDto.setWindowId(windowId);
        	projectPlanOfferDto.setCondEntryCd(entryCd);
        	projectPlanOfferDto.setCondEntryYear(entryYear);
        	projectPlanOfferDto.setCondEntryKai(entryKai);
        	projectPlanOfferDto.setCondCompanyCd(companyCd);
        	projectPlanOfferDto.setCondOnerCd(onerCd);
        	
        	//対象店舗リスト
        	List miseList = getOfferMiseInfoLogic.execute(companyCd, onerCd, sysDate);
        	//店舗リスト設定
        	projectPlanOfferDto.setCondListMise(miseList);
        	//申込情報
        	projectPlanOfferDto = (ProjectPlanOfferDto) getOfferInfoLogic.execute(projectPlanOfferDto);
        	List offerInfoList = projectPlanOfferDto.getInitInfoList();
    		
    		//画面へDBから取得した値を設定する
    		projectPlanOfferDto = ProjectPlanOfferUtil.setOfferInfo(projectPlanOfferDto, offerInfoList, null);

            //店情報設定
    		String [] tmpMiseCdL =  new String[ProjectPlanOfferDto.FORM_SIZE];
    		String [] tmpMiseNameL =  new String[ProjectPlanOfferDto.FORM_SIZE];
    		String [] tmpSibuNameL =  new String[ProjectPlanOfferDto.FORM_SIZE];
    		int joinCnt = 0;
    		int personIndex = 1;//出席者１～３
    		for(int i = 0 ; i < projectPlanOfferDto.getPrmJoinPersonList().size() ; i++) {
				UIOfferJoinPersonInfo uiOfferJoinPersonInfo = 
					(UIOfferJoinPersonInfo)projectPlanOfferDto.getPrmJoinPersonList().get(i);
				
				if(!uiOfferJoinPersonInfo.getLNameKna().equals("")) {
					joinCnt += 1;
				}
				String miseCd = uiOfferJoinPersonInfo.getMiseCd();
				
				for(int j = 0 ; j < miseList.size(); j++) {
					UIOfferMiseInfo uiOfferMiseInfo = (UIOfferMiseInfo)miseList.get(j);
					if(miseCd.equals(uiOfferMiseInfo.getMiseCd())) {
						tmpMiseCdL[personIndex] = miseCd;
						tmpMiseNameL[personIndex] = uiOfferMiseInfo.getMiseNameKj();
						tmpSibuNameL[personIndex] = uiOfferMiseInfo.getSibuName();
						personIndex++;
						break;
					}
				}
    		}
    		projectPlanOfferDto.setPrmJoinKanaCnt(String.valueOf(joinCnt));
    		projectPlanOfferDto.setPrmMiseCdList(tmpMiseCdL);
    		projectPlanOfferDto.setMiseNameList(tmpMiseNameL);
    		projectPlanOfferDto.setSibuNameList(tmpSibuNameL);
    		projectPlanOfferDto.setPrmConfirmKbn(ProjectPlanOfferDto.EDIT_KBN_CONF_V);
        } else {
        	//画面表示データ設定
        	projectPlanOfferDto.setPrmConfirmKbn(ProjectPlanOfferDto.EDIT_KBN_CONF_E);
        	projectPlanOfferDto = ProjectPlanOfferUtil.setOfferInfo(projectPlanOfferDto, null, projectPlanOfferDto.getPrmInfo());
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
		getEventListDto().setWindowId(getProjectPlanOfferDto().getWindowId());
		getProjectPlanOfferDto().clear();
		getProjectPlanOfferBakDto().clear();

		//各種イベント一覧へ遷移
		return VIEWID_EVENTLIST;
	}

    
    /**
     * 編集画面へ戻る処理
     * 
     * @return　画面遷移情報
     */	
	public String backMove() {
		//複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
		
		if(getProjectPlanOfferDto().getPrmConfirmKbn().equals(ProjectPlanOfferDto.EDIT_KBN_CONF_E)) {
			
			//1.ProjectPlanOfferBakDtoをクリア
			getProjectPlanOfferBakDto().clear();
			
			//2.再検索を行う
			projectPlanOfferDto = (ProjectPlanOfferDto) getGetOfferInfoLogic().execute(projectPlanOfferDto);
        	List offerInfoList = getProjectPlanOfferDto().getInitInfoList();
    		
    		//3. 画面へDBから取得した値を設定する
        	projectPlanOfferDto = ProjectPlanOfferUtil.setOfferInfo(getProjectPlanOfferDto(), offerInfoList, null);
    		
        	//TODO 戻り先指定
        	getProjectPlanOfferDto().setPrmTabNo("");
        	
        	//4. 一号店コードと支部名設定
        	projectPlanOfferDto = ProjectPlanOfferUtil.choiceMise(
        			getProjectPlanOfferDto(), getCheckOfferInputParamLogic());
        	
        	getProjectPlanOfferDto().setPrmMiseCd(getProjectPlanOfferDto().getPrmMiseCdList()[1]);
        	getProjectPlanOfferDto().setSibuName(getProjectPlanOfferDto().getSibuNameList()[1]);
        	getProjectPlanOfferDto().setMiseName(getProjectPlanOfferDto().getMiseNameList()[1]);
        	//5. 初期化設定
        	getProjectPlanOfferDto().setPrmTabNo("0");
        	getProjectPlanOfferDto().setDelFlg("");
        	//6. 保持用クラスへ設定する
    		getProjectPlanOfferBakDto().setBakDutyOnerName(getProjectPlanOfferDto().getPrmDutyOnerName());
    		getProjectPlanOfferBakDto().setBakDutySoufuName(getProjectPlanOfferDto().getPrmDutySoufuName());
    		getProjectPlanOfferBakDto().setBakDutyName(getProjectPlanOfferDto().getPrmDutyName());
    		getProjectPlanOfferBakDto().setBakDutyJobType(getProjectPlanOfferDto().getPrmDutyJobType());
    		getProjectPlanOfferBakDto().setBakDutyAddress1(getProjectPlanOfferDto().getPrmDutyAddress1());
    		getProjectPlanOfferBakDto().setBakDutyAddress2(getProjectPlanOfferDto().getPrmDutyAddress2());
    		getProjectPlanOfferBakDto().setBakDutyAddress3(getProjectPlanOfferDto().getPrmDutyAddress3());
    		getProjectPlanOfferBakDto().setBakDutyTel(getProjectPlanOfferDto().getPrmDutyTel());
    		getProjectPlanOfferBakDto().setBakDutyZip(getProjectPlanOfferDto().getPrmDutyZip());
        	
			if(!(getCheckOfferInputParamLogic().isNull(getProjectPlanOfferDto().getPrmIninFName()) ||
				getCheckOfferInputParamLogic().isNull(getProjectPlanOfferDto().getPrmIninLName()))) {

		        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
		            return make.operationErr_VIEW_ID;
		        }
				//委任状情報がある場合, 委任状画面へ遷移
				getProjectPlanOfferDto().setPrmEditKbn(ProjectPlanOfferDto.EDIT_KBN_UPDATE);
				return VIEWID_ININ;
			}
	        if (!make.isValidSessionKey( getProjectPlanOfferDto().getViewSessionKey(),getProjectPlanOfferDto().getSessionKey())) {
	            return make.operationErr_VIEW_ID;
	        }
			//編集画面へ遷移
			return VIEWID_EDIT;
		} else if(getProjectPlanOfferDto().getPrmConfirmKbn().equals(ProjectPlanOfferDto.EDIT_KBN_CONF_V)) {
			getEventListDto().setReturnKind(2);
			getEventListDto().setWindowId(getProjectPlanOfferDto().getWindowId());
			getProjectPlanOfferDto().clear();
			getProjectPlanOfferBakDto().clear();
			//条件画面へ遷移する
			return VIEWID_EVENTLIST;
		}
		return null;
	}
}
