/*
 * 作成日: 2006/02/16
 *
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountedit.action.AccountCheckAction;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccountEditRequestDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccounteditDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.AccountCheckLogic;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserGyotai;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.businesssearch.dto.BusinessSearchDto;
import jp.co.isid.mos.bird.commonform.businesssearch.entity.UIGyotai;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * アカウント情報変更（編集画面）アクション
 * 
 * @author 慮
 * 更新日:2009/12/14 xkinu 2009/12/14の改修に伴いアクションIDの修正を行いました。
 */
public class AccountCheckActionImpl implements AccountCheckAction {

    public static final String initialize_ACTION_ID       = "BBA001A11";
    public static final String update_check_ACTION_ID     = "BBA001A12";
    public static final String searchOner_ACTION_ID       = "BBA001A13"; 
    public static final String searchMise_ACTION_ID       = "BBA001A14"; 
    public static final String businessSearch_ACTION_ID   = "BBA001A15"; 
    public static final String businessDelete_ACTION_ID   = "BBA001A16"; 
    public static final String back_ACTION_ID             = "BBA001A17"; 
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
	private AccountCheckLogic accounteditCheckLogic;
	private AccounteditDto accounteditDto;
	/** DTO【リクエスト用】*/
	private AccountEditRequestDto accounteditReqDto;
	private MiseSearchDto miseSearchDto;
	private OwnerSearchDto ownerSearchDto;
	private BusinessSearchDto businessSearchDto;
	private String onerSearchCompanyCd;
    /* セッションキー */
    private MakeSessionKey sessionKey = new MakeSessionKey();

    /**
     * 初期処理
     */
	public String initialize() {
		//設定済みウィンドウID値と有無
        int setedwindowId = -1;
		// 店情報取得
		if(getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT) {
			setedwindowId =getMiseSearchDto().getWindowId();
			//1．店舗を選択後遷移してきた場合。
			if (MiseSearchDto.RETURNKIND_SELECT == getMiseSearchDto().getReturnKind()) {
				accounteditDto.getUserMise().setMiseCd(getMiseSearchDto().getMiseCd());
			}
            //DTO【店舗選択】.クリア処理を実行します。
            getMiseSearchDto().clear();
		}
		/*オーナーコード取得*/
		if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) {
			if(OwnerSearchDto.RETURNKIND_SELECT == getOwnerSearchDto().getReturnKind()){
				String selectCompanyCd = getOwnerSearchDto().getCompanyCd();
				String selectCd = getOwnerSearchDto().getOnerCd();
				String selectName = getOwnerSearchDto().getOnerNameKj();
				if(UserType.isHonbu(accounteditDto.getUIUser().getUsertypeCd())) {
					accounteditDto.getUserOner().setCompanyCd(getOwnerSearchDto().getCompanyCd());
					accounteditDto.getUserOner().setOnerCd(selectCd);
					accounteditDto.getUserOner().setOnerNameKj(selectName);
				}
				else {
					List onerList = accounteditDto.getUserOnerList();
					for (Iterator i = onerList.iterator(); i.hasNext();) {
						UIUserOner entity = (UIUserOner) i.next();
						if(selectCompanyCd.equals(entity.getCompanyCd())) {
							entity.setOnerCd(selectCd);
							entity.setOnerNameKj(selectName);
						}
					}
				}
			}
			setedwindowId =getOwnerSearchDto().getWindowId();
            //DTO【オーナー選択】.クリア処理を実行します。
			getOwnerSearchDto().clear();
		}
		/*業態取得*/
		if(getBusinessSearchDto().getReturnKind() != BusinessSearchDto.RETURNKIND_INIT) {
			if(BusinessSearchDto.RETURNKIND_SELECT == getBusinessSearchDto().getReturnKind()){
				accounteditDto.setUserGyotaiList(getBusinessSearchDto().getResultGyotaiList());
			}
			setedwindowId =getBusinessSearchDto().getWindowId();
	        //DTO【業態選択】.クリア処理を実行します。
			getBusinessSearchDto().clear();
		}
		if(setedwindowId>-1) {
			//
			accounteditReqDto.setWindowId(setedwindowId);
			//取得済みセッションキーの再設定をします。
			accounteditReqDto.setSessionKey(accounteditDto.getSessionKey(setedwindowId));
		}
		return null;
	}	
	/**
	 * 確認ボタン処理
	 */
	public String update_check(){
        //セッションキーチェック
        if (!sessionKey.isValidSessionKey(accounteditDto.getSessionKeyNow(), accounteditReqDto.getSessionKey())) {
            return CommonUtil.operationErr_VIEW_ID;
        }
		/*登録内容チェック */
		accounteditCheckLogic.execute(accounteditDto);
		return pageSelect();
	}
	
	/* 店コード選択処理 */
	public String searchMise(){
		getMiseSearchDto().setInitialFlag(true);
		getMiseSearchDto().setNeedReturnKind(true);
		getMiseSearchDto().setNavigationCase(accounteditDto.getPageId());
		getMiseSearchDto().setWindowId(accounteditReqDto.getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(accounteditDto.getUserCompany().getRCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
		return "BCO008V01";
	}
	/*オーナーコード選択処理*/
	public String searchOner(){
        // 初期化＆遷移元セット＆オーナ選択初期処理起動フラグON
        getOwnerSearchDto().clear();                        
        getOwnerSearchDto().setNavigationCase(accounteditDto.getPageId());
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setNeedReturnKind(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        if(UserType.isHonbu(accounteditDto.getUIUser().getUsertypeCd())) {
        	listCompany.add(accounteditDto.getUserOner().getCompanyCd());
        }
        if(UserType.isOner(accounteditDto.getUIUser().getUsertypeCd())) {
        	listCompany.add(onerSearchCompanyCd);
        }        
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        getOwnerSearchDto().setWindowId(accounteditReqDto.getWindowId());

		return "BCO006V01";
	}
	/*業態選択処理*/
	public String businessSearch(){
		ArrayList gyotaiList = new ArrayList(); 
		if(accounteditDto.getUserGyotaiList()!=null && accounteditDto.getUserGyotaiList().size() > 0){
			for(int i=0 ; i<accounteditDto.getUserGyotaiList().size() ; i++){
				UIGyotai unit = new UIGyotai();
				if(accounteditDto.getUserGyotaiList().get(i) instanceof UIGyotai){
					UIGyotai data1 = (UIGyotai)accounteditDto.getUserGyotaiList().get(i);
					unit.setGyotaiKbn(data1.getGyotaiKbn());
					unit.setCheckedGyotai(true);
					unit.setGyotaiName(data1.getGyotaiName());
				}
				if(accounteditDto.getUserGyotaiList().get(i) instanceof UIUserGyotai){
					UIUserGyotai data2 = (UIUserGyotai)accounteditDto.getUserGyotaiList().get(i);
					unit.setGyotaiKbn(data2.getGyotaiKbn());
					unit.setCheckedGyotai(true);
					unit.setGyotaiName(data2.getGyotaiName());
				}
				gyotaiList.add(unit);
			}
		}

		getBusinessSearchDto().setResultGyotaiList(gyotaiList);
    	getBusinessSearchDto().setNavigationCase(accounteditDto.getPageId());
    	getBusinessSearchDto().setWindowId(accounteditReqDto.getWindowId());
    	getBusinessSearchDto().setNeedReturnKind(true);
		return "BCO007V01";
	}
	/*業態クリア処理*/
	public String businessDelete(){
		accounteditDto.setUserGyotaiList(new ArrayList(0));
		return null;
	}
	
	/* ユーザタイプ別ビューID 取得　*/
	private String pageSelect(){
		String userTypeCd = accounteditDto.getUIUser().getUsertypeCd();
		if(UserType.isHonbu(userTypeCd)){
             return accounteditDto.isSystemAdminUser()?"BBA001V08":"BBA001V09";
		}
		else if(UserType.isOner(userTypeCd)){
            return accounteditDto.isSystemAdminUser()?"BBA001V10":"BBA001V11";
		}
        return accounteditDto.isSystemAdminUser()?"BBA001V12":"BBA001V13";
	}
	/**
	 * @return ownerSearchDto を戻します。
	 */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	/**
	 * @param ownerSearchDto ownerSearchDto を設定。
	 */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}
	/**
	 * @return miseSearchDto を戻します。
	 */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}
	/**
	 * @param miseSearchDto miseSearchDto を設定。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}

	/**
	 * @return accounteditdto を戻します。
	 */
	public AccounteditDto getAccounteditDto() {
		return accounteditDto;
	}
	/**
	 * @param accounteditdto accounteditdto を設定。
	 */
	public void setAccounteditDto(AccounteditDto accounteditDto) {
		this.accounteditDto = accounteditDto;
	}
	/**
	 * @return accounteditCheckLogic を戻します。
	 */
	public AccountCheckLogic getAccounteditCheckLogic() {
		return accounteditCheckLogic;
	}
	/**
	 * @param accounteditCheckLogic accounteditCheckLogic を設定。
	 */
	public void setAccounteditCheckLogic(AccountCheckLogic accountCheckLogic) {
		this.accounteditCheckLogic = accountCheckLogic;
	}
	
	/**
	 * @return businessSearchDto を戻します。
	 */
	public BusinessSearchDto getBusinessSearchDto() {
		return businessSearchDto;
	}
	/**
	 * @param businessSearchDto businessSearchDto を設定。
	 */
	public void setBusinessSearchDto(BusinessSearchDto businessSearchDto) {
		this.businessSearchDto = businessSearchDto;
	}
	/**
     *戻る処理 
	 */
	public String back(){
        String returnViewId = "BBA001V01";
        if (!CommonUtil.isNull(accounteditDto.getReturnViewId())) {
            returnViewId = accounteditDto.getReturnViewId();
        }
        return returnViewId;
	}

	/**
	 * @return クラス変数accountEditRequestDto を戻します。
	 */
	public AccountEditRequestDto getAccounteditReqDto() {
		return accounteditReqDto;
	}
	/**
	 * @param accounteditReqDto を クラス変数accountEditRequestDtoへ設定します。
	 */
	public void setAccounteditReqDto(AccountEditRequestDto accountEditRequestDto) {
		this.accounteditReqDto = accountEditRequestDto;
	}
	/**
	 * @return クラス変数birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
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
}