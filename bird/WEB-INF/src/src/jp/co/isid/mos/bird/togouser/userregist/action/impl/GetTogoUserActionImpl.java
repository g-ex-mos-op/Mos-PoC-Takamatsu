/*
 * 作成日: 2008/11/06
 */
package jp.co.isid.mos.bird.togouser.userregist.action.impl;


import java.util.List;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.BirdUserListDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.togouser.login.dto.TogoUserLoginDto;
import jp.co.isid.mos.bird.togouser.userregist.action.GetTogoUserAction;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.togouser.userregist.dto.CodBumonDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.CodCompanyDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.TogoUserListDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.togouser.userregist.logic.CheckNewUserIdLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetBumonCompanyLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetCompanyInfoLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetUserListLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetUserLogic;


/**
 * @author K.Nihonyanagi
 */
public class GetTogoUserActionImpl implements GetTogoUserAction, CommonAction {
	
    public static final String initialize_ACTION_ID = "BUR001A01"; 
    public static final String edit_ACTION_ID = "BUR001A02"; 
    public static final String MOSFOOD_COMPANY_CD = "00"; 
    
    //DAO
    private UIBirdUserDao uIBirdUserDao;
    
    //DTO
    private BirdUserListDto birdUserListDto;
    private CodBumonDto codBumonDto;
    private CodCompanyDto codCompanyDto;
    private UITogoUserDto uiTogoUserDto;
    private TogoUserLoginDto togoUserLoginDto;
    private TogoUserListDto togoUserListDto;
    //ENTITY
    private BirdUserInfo birdUserInfo;
	private UIBirdUser uiBirdUser;
    private BirdDateInfo birdDateInfo;
    
    //ロジックゲッター用
    private GetUserLogic gettogouserlogic;
    private GetCompanyInfoLogic getCompanyInfoLogic;
    private GetBumonCompanyLogic getBumonCompanyLogic;
    private GetUserListLogic getUserListLogic;
    private CheckNewUserIdLogic checkNewUserIdLogic;
    private DateInfoSetupLogic dateInfoSetupLogic;
    
    /* ==================================================
     * アクション名：初期処理
     * アクションID：BUR001A01
     * ==================================================
     */
    public String initialize(){
        if (getUiTogoUserDto().getLoginUserId() == null ){
            getUiTogoUserDto().setLoginUserId(
                    getTogoUserLoginDto().getUserId());
        }
        getUiTogoUserDto().clearInputs();
        
        if ( getTogoUserListDto().isInitFlg() ){
                getTogoUserListDto().clearInputs();
        }
        
        //システム日付取得用
        //===========================================================
        /* ロジック【日付情報取得】を実行 */
        BirdDateInfo birdDateInfo = getDateInfoSetupLogic().getBirdDateInfo();
        getUiTogoUserDto().setBirdDateInfo(birdDateInfo);
        //===========================================================
        
        return null;
    } 
    
    /* ==================================================
     * アクション名：新規登録
     * アクションID：BUR001A02
     * ==================================================
     */
	public String edit(){

		        
		/*入力されたユーザIDチェック*/
//		if(getUiTogoUserDto().getUiBirdUser().getUserId()==null
        if(getUiTogoUserDto().getInputUserId()==null
                || getUiTogoUserDto().getInputUserId().equals("")){
            throw new NotNullException("ユーザーID");
		}else{
			if(idCheck(getUiTogoUserDto().getInputUserId())){
				throw new InvalidInputException("ユーザー");
			}
		}
        /* ユーザーID前ゼロ付加 */
        CodeFormatter formatter = new CodeFormatter(8);
        formatter.setFormatPattern("00000000");
        getUiTogoUserDto().setInputUserId(formatter.format(
                getUiTogoUserDto().getInputUserId(), true));
        
        getCheckNewUserIdLogic().execute(getUiTogoUserDto().getInputUserId());
        
        /* 統合ユーザ履歴情報 取得 */
        setUiTogoUserDto(getGettogouserlogic().execute(getUiTogoUserDto().getInputUserId(), getUiTogoUserDto()));
                
        /* 会社情報 取得 */
        List companyList = getGetCompanyInfoLogic().execute("");
        getCodCompanyDto().setCompList(companyList);        
        
        /* 部門情報取得 */
        List bumonList = getGetBumonCompanyLogic().execute(MOSFOOD_COMPANY_CD);
        getCodBumonDto().setBumonList(bumonList);
                
        return "BUR001V02";
	}


    /* ==================================================
     * アクション名：検索
     * アクションID：BUR001A03
     * ==================================================
     */
    public String search(){
        if(getUiTogoUserDto().getInputUserId()==null
                || getUiTogoUserDto().getInputUserId().equals("")){
            throw new NotNullException("ユーザーID");
        }else{
            if(idCheck(getUiTogoUserDto().getInputUserId())){
                throw new InvalidInputException("ユーザー");
            }
        }
        List henshuUserList = getGetUserListLogic().execute(getUiTogoUserDto().getInputUserId().toUpperCase());
        getTogoUserListDto().setUserList(henshuUserList);

        getTogoUserListDto().setSelectedUser("0");
        getTogoUserListDto().setInitFlg(false);
        
        return null;
    } 
    

    /* ==================================================
     * アクション名：編集
     * アクションID：BUR001A04
     * ==================================================
     */
    public String henshu(){
        if (getTogoUserListDto().getSelectedUser() != null ){
            
            UIBirdUser uiBirdUser = (UIBirdUser) getTogoUserListDto().getUserList().get(
                                               Integer.parseInt(getTogoUserListDto().getSelectedUser())
                                        );
            getUiTogoUserDto().setInputUserId(uiBirdUser.getUserId());

            /* 統合ユーザ履歴情報 取得 */
            setUiTogoUserDto(getGettogouserlogic().execute(getUiTogoUserDto().getInputUserId(), getUiTogoUserDto()));
                    
            /* 会社情報 取得 */
            List companyList = getGetCompanyInfoLogic().execute("");
            getCodCompanyDto().setCompList(companyList);
            
            /* 部門情報取得 */
            List bumonList = null;
            if (getUiTogoUserDto().getUiUserCompany() != null) {
                bumonList = getGetBumonCompanyLogic().execute(
                            getUiTogoUserDto().getUiUserCompany().getRCompanyCd());
            }
            //会社が取得できない(対象ユーザのBM03USCPが空)場合、はモスの部門コードをセットする。
            else {
                bumonList = getGetBumonCompanyLogic().execute(MOSFOOD_COMPANY_CD);
            }
            getCodBumonDto().setBumonList(bumonList);

        }
        else{
            return null;
        }
        return "BUR001V02";
    } 
    /**
	 * 入力されたユーザIDチェック
	 * @return 成功：true, 失敗：false
	 */
	private boolean idCheck(String id){
		CodeVerifier codeVerifier = new CodeVerifier(true);
		if(!codeVerifier.validate(id.trim())){
			return true;
		}
		return false;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return birdUserListDto を戻します。
	 */
	public BirdUserListDto getBirdUserListDto() {
		return birdUserListDto;
	}
	/**
	 * @param birdUserListDto birdUserListDto を設定。
	 */
	public void setBirdUserListDto(BirdUserListDto birdUserListDto) {
		this.birdUserListDto = birdUserListDto;
	}
	

    /*オーナーコード選択処理*/
    /**
     * 部門リスト読込処理
     * @return 画面遷移情報
     */
//    public String loadBumonList() {
//        uiTogoUserDto.getUiBirdUser().setBumonList(getGetBumonLogic().execute());
//        uiTogoUserDto.setInitFlag(false);
//        return null;
//    }
    public GetUserLogic getGettogouserlogic() {
        return gettogouserlogic;
    }

    public void setGettogouserlogic(GetUserLogic getuserlogic) {
        this.gettogouserlogic = getuserlogic;
    }

    public UITogoUserDto getUiTogoUserDto() {
        return uiTogoUserDto;
    }

    public void setUiTogoUserDto(UITogoUserDto uiTogoUserDto) {
        this.uiTogoUserDto = uiTogoUserDto;
    }

    public CodBumonDto getCodBumonDto() {
        return codBumonDto;
    }

    public CodCompanyDto getCodCompanyDto() {
        return codCompanyDto;
    }

    public void setCodBumonDto(CodBumonDto codBumonDto) {
        this.codBumonDto = codBumonDto;
    }

    public void setCodCompanyDto(CodCompanyDto codCompanyDto) {
        this.codCompanyDto = codCompanyDto;
    }

    public UIBirdUser getUiBirdUser() {
        return uiBirdUser;
    }

    public void setUiBirdUser(UIBirdUser uiBirdUser) {
        this.uiBirdUser = uiBirdUser;
    }

    public UIBirdUserDao getUIBirdUserDao() {
        return uIBirdUserDao;
    }

    public void setUIBirdUserDao(UIBirdUserDao birdUserDao) {
        uIBirdUserDao = birdUserDao;
    }

    public GetCompanyInfoLogic getGetCompanyInfoLogic() {
        return getCompanyInfoLogic;
    }

    public void setGetCompanyInfoLogic(GetCompanyInfoLogic getCompanyInfoLogic) {
        this.getCompanyInfoLogic = getCompanyInfoLogic;
    }

    public TogoUserLoginDto getTogoUserLoginDto() {
        return togoUserLoginDto;
    }

    public void setTogoUserLoginDto(TogoUserLoginDto togoUserLoginDto) {
        this.togoUserLoginDto = togoUserLoginDto;
    }

    public TogoUserListDto getTogoUserListDto() {
        return togoUserListDto;
    }

    public void setTogoUserListDto(TogoUserListDto togoUserListDto) {
        this.togoUserListDto = togoUserListDto;
    }

    public GetUserListLogic getGetUserListLogic() {
        return getUserListLogic;
    }

    public void setGetUserListLogic(GetUserListLogic getUserListLogic) {
        this.getUserListLogic = getUserListLogic;
    }

    public CheckNewUserIdLogic getCheckNewUserIdLogic() {
        return checkNewUserIdLogic;
    }

    public void setCheckNewUserIdLogic(CheckNewUserIdLogic checkNewUserIdLogic) {
        this.checkNewUserIdLogic = checkNewUserIdLogic;
    }
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public DateInfoSetupLogic getDateInfoSetupLogic() {
        return dateInfoSetupLogic;
    }

    public void setDateInfoSetupLogic(DateInfoSetupLogic dateInfoSetupLogic) {
        this.dateInfoSetupLogic = dateInfoSetupLogic;
    }

    public GetBumonCompanyLogic getGetBumonCompanyLogic() {
        return getBumonCompanyLogic;
    }

    public void setGetBumonCompanyLogic(GetBumonCompanyLogic getBumonCompanyLogic) {
        this.getBumonCompanyLogic = getBumonCompanyLogic;
    }
}
    