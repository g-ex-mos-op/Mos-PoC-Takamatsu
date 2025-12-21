/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;
import jp.co.isid.mos.bird.commonform.businesssearch.dto.BusinessSearchDto;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.sysadmin.userregist.action.UserRegistEditHonbuAction;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.CheckUserLogic;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.GetBumonLogic;

/**
 * 登録画面（本部）アクションクラス
 * @author inazawa
 */
public class UserRegistEditHonbuActionImpl implements UserRegistEditHonbuAction {

//    private static Logger logger_ = Logger.getLogger(UserRegistEditHonbuActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BSA006A03";
    public static String regist_ACTION_ID            = "BSA006A04";
    public static String cancel_ACTION_ID            = "BSA006A05";
    public static String searchOner_ACTION_ID        = "BSA006A06";
    public static String searchGyotai_ACTION_ID      = "BSA006A07";
    public static String clearGyotai_ACTION_ID       = "BSA006A08";
    
    /* userRegistDto */
    private UserRegistDto userRegistDto;
    /* ownerSearchDto */
    private OwnerSearchDto ownerSearchDto;
    /* businessSearchDto */
    private BusinessSearchDto businessSearchDto;


    /* 登録内容チェックロジック */
    private CheckUserLogic userRegistCheckUserLogic;
    /* userRegistGetBumonLogic */
    private GetBumonLogic   userRegistGetBumonLogic;
    
    /* オーナ選択値入力positionパラメータ */
    private int ownerSearchPosition;
    
    /**
	 * ユーザ登録Dtoの設定
	 * @return userRegistDto を戻します。
	 */
	public UserRegistDto getUserRegistDto() {
		return userRegistDto;
	}
	/**
	 * ユーザ登録Dtoの設定
	 * @param userRegistDto userRegistDto を設定。
	 */
	public void setUserRegistDto(UserRegistDto userRegistDto) {
		this.userRegistDto = userRegistDto;
	}
    
	/**
	 * オーナ選択Dtoの設定
	 * @return ownerSearchDto を戻します。
	 */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	/**
	 * オーナ選択Dtoの設定
	 * @param ownerSearchDto ownerSearchDto を設定。
	 */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}
	
	/**
	 * 業態選択Dtoの設定
	 * @return businessSearchDto を戻します。
	 */
	public BusinessSearchDto getBusinessSearchDto() {
		return businessSearchDto;
	}
	/**
	 * 業態選択Dtoの設定
	 * @param businessSearchDto businessSearchDto を設定。
	 */
	public void setBusinessSearchDto(BusinessSearchDto businessSearchDto) {
		this.businessSearchDto = businessSearchDto;
	}
	
	/**
	 * 登録内容チェックロジックの設定
	 * @return userRegistCheckUserLogic を戻します。
	 */
	public CheckUserLogic getUserRegistCheckUserLogic() {
		return userRegistCheckUserLogic;
	}
	/**
	 * 登録内容チェックロジックの設定
	 * @param userRegistCheckUserLogic userRegistCheckUserLogic を設定。
	 */
	public void setUserRegistCheckUserLogic(CheckUserLogic checkUserLogic) {
		this.userRegistCheckUserLogic = checkUserLogic;
	}
    /**
     * 部門取得ロジックの取得
     * @return userRegistGetBumonLogic
     */
    public GetBumonLogic getUserRegistGetBumonLogic() {
        return userRegistGetBumonLogic;
    }
    /**
     * 部門取得ロジックの取得
     * @param userRegistGetBumonLogic
     */
    public void setUserRegistGetBumonLogic(GetBumonLogic getBumonLogic) {
        this.userRegistGetBumonLogic = getBumonLogic;
    }

	/**
	 * オーナ選択値入力positionパラメータの設定
	 * @return ownerSearchPosition を戻します。
	 */
	public int getOwnerSearchPosition() {
		return ownerSearchPosition;
	}
	/**
	 * オーナ選択値入力positionパラメータの設定
	 * @param ownerSearchPosition ownerSearchPosition を設定。
	 */
	public void setOwnerSearchPosition(int ownerSearchPosition) {
		this.ownerSearchPosition = ownerSearchPosition;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        // オーナ選択情報取得
            // 会社コードリスト取得
        if (!getUserRegistDto().isInitFlag()) {
            //初期表示用部門Listの設定
            userRegistDto.setBumonList(
            		getUserRegistGetBumonLogic().execute(userRegistDto.getUserCompany().getRCompanyCd()));
        }
		/*オーナーコード取得*/
		if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) {
			if(OwnerSearchDto.RETURNKIND_SELECT == getOwnerSearchDto().getReturnKind()){
				String selectCompanyCd = getOwnerSearchDto().getCompanyCd();
				String selectCd = getOwnerSearchDto().getOnerCd();
				String selectName = getOwnerSearchDto().getOnerNameKj();
				userRegistDto.getUserOner().setCompanyCd(selectCompanyCd);
				userRegistDto.getUserOner().setOnerCd(selectCd);
				userRegistDto.getUserOner().setOnerNameKj(selectName);
			}
            //DTO【オーナー選択】.クリア処理を実行します。
			getOwnerSearchDto().clear();
		}
		/*業態取得*/
        else if(getBusinessSearchDto().getReturnKind() != BusinessSearchDto.RETURNKIND_INIT) {
			if(BusinessSearchDto.RETURNKIND_SELECT == getBusinessSearchDto().getReturnKind()){
				userRegistDto.setUserGyotaiList(getBusinessSearchDto().getResultGyotaiList());
			}
	        //DTO【業態選択】.クリア処理を実行します。
			getBusinessSearchDto().clear();
		}
        return null;
    }

	/**
     * 確認ボタン処理
     */
    public String regist(){
    	userRegistCheckUserLogic.execute(userRegistDto);
        return userRegistDto.confirmHonbu_VIEW_ID;
    }

    /**
     * 取消処理
     */
    public String cancel(){
        return userRegistDto.select_VIEW_ID;
    }


    /**
     * 業態選択起動処理
     */
    public String searchGyotai(){
		ArrayList gyotaiList = new ArrayList(); 
		if(userRegistDto.getUserGyotaiList()!=null && userRegistDto.getUserGyotaiList().size() > 0){
			for(int i=0 ; i<userRegistDto.getUserGyotaiList().size() ; i++){
				UIGyotai unit = new UIGyotai();
				if(userRegistDto.getUserGyotaiList().get(i) instanceof UIGyotai){
					UIGyotai data1 = (UIGyotai)userRegistDto.getUserGyotaiList().get(i);
					unit.setGyotaiKbn(data1.getGyotaiKbn());
					unit.setCheckedGyotai(true);
					unit.setGyotaiName(data1.getGyotaiName());
				}
				if(userRegistDto.getUserGyotaiList().get(i) instanceof UIUserGyotai){
					UIUserGyotai data2 = (UIUserGyotai)userRegistDto.getUserGyotaiList().get(i);
					unit.setGyotaiKbn(data2.getGyotaiKbn());
					unit.setCheckedGyotai(true);
					unit.setGyotaiName(data2.getGyotaiName());
				}
				gyotaiList.add(unit);
			}
		}

		getBusinessSearchDto().setResultGyotaiList(gyotaiList);
    	getBusinessSearchDto().setNavigationCase(userRegistDto.editHonbu_VIEW_ID);
    	getBusinessSearchDto().setNeedReturnKind(true);
		return "BCO007V01";
    }

    /**
     * 業態クリア処理
     */
    public String clearGyotai(){
    	userRegistDto.setUserGyotaiList(new ArrayList(0));
    	return null;
    }
	/**
	 * オーナー選択ボタン処理
	 */
	public String searchOner(){
        // 初期化＆遷移元セット＆オーナ選択初期処理起動フラグON
        getOwnerSearchDto().clear();                        
        getOwnerSearchDto().setNavigationCase(userRegistDto.editHonbu_VIEW_ID);
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setNeedReturnKind(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
       	listCompany.add(userRegistDto.getUserCompany().getRCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
		return "BCO006V01";
	}

    /**
     * 部門リスト読込処理
     * @return 画面遷移情報
     */
    public String loadBumonList() {
        userRegistDto.setBumonList(getUserRegistGetBumonLogic().execute(
                userRegistDto.getUserCompany().getRCompanyCd()));
        userRegistDto.setInitFlag(true);
        return null;
    }


}

