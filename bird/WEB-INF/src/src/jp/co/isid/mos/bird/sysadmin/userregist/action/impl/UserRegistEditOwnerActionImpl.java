/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.sysadmin.userregist.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.commonform.businesssearch.dto.BusinessSearchDto;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.sysadmin.userregist.action.UserRegistEditOwnerAction;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.CheckUserLogic;

/**
 * 登録画面（オーナー）アクションクラス
 * @author itamoto
 */
public class UserRegistEditOwnerActionImpl implements UserRegistEditOwnerAction {

//    private static Logger logger_ = Logger.getLogger(UserRegistEditOwnerActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BSA006A03";
    public static String confirm_ACTION_ID            = "BSA006A04";
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
    
    /* オーナ選択対象会社コード */
	private String onerSearchCompanyCd;
    
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
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
		/*オーナーコード取得*/
		if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) {
			if(OwnerSearchDto.RETURNKIND_SELECT == getOwnerSearchDto().getReturnKind()){
				String selectCompanyCd = getOwnerSearchDto().getCompanyCd();
				String selectCd = getOwnerSearchDto().getOnerCd();
				String selectName = getOwnerSearchDto().getOnerNameKj();
				List onerList = userRegistDto.getUserOnerList();
				for (Iterator i = onerList.iterator(); i.hasNext();) {
					UIUserOner entity = (UIUserOner) i.next();
					if(selectCompanyCd.equals(entity.getCompanyCd())) {
						entity.setOnerCd(selectCd);
						entity.setOnerNameKj(selectName);
					}
				}
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
        
//        userRegistDto.setUserLevel(2);
        return null;
    }

	/**
     * 新規登録処理
     */
    public String confirm(){
    	// 入力チェック オーナマスタ取得
    	userRegistCheckUserLogic.execute(userRegistDto);
        return userRegistDto.confirmOwner_VIEW_ID;
    }

    /**
     * 取消処理
     */
    public String cancel(){
        return userRegistDto.select_VIEW_ID;
    }

	/**
	 * オーナー選択ボタン処理
	 */
	public String searchOner(){
        // 初期化＆遷移元セット＆オーナ選択初期処理起動フラグON
        getOwnerSearchDto().clear();                        
        getOwnerSearchDto().setNavigationCase(userRegistDto.editOwner_VIEW_ID);
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setNeedReturnKind(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
       	listCompany.add(onerSearchCompanyCd);
        getOwnerSearchDto().setRCompanyCdList(listCompany);
		return "BCO006V01";
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
    	getBusinessSearchDto().setNavigationCase(userRegistDto.editOwner_VIEW_ID);
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
    
/*************************** 2007/01/31 add start xamaruyama ***************************/
    
	/**
	 * @return クラス変数onerSearchCompanyCd を戻します。
	 */
	public String getOnerSearchCompanyCd() {
		return onerSearchCompanyCd;
	}
	/**
	 * @param onerSearchCompanyCd を クラス変数onerSearchCompanyCdへ設定します。
	 */
	public void setOnerSearchCompanyCd(String onerSearchCompanyCd) {
		this.onerSearchCompanyCd = onerSearchCompanyCd;
	}

/*************************** 2007/01/31 add end xamaruyama ***************************/
    
}

