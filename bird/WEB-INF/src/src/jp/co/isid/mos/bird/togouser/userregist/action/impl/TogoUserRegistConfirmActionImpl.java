/*
 * 作成日: 2008/11/06
 */
package jp.co.isid.mos.bird.togouser.userregist.action.impl;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.togouser.login.dto.TogoUserLoginDto;
import jp.co.isid.mos.bird.togouser.userregist.action.TogoUserRegistConfirmAction;
import jp.co.isid.mos.bird.togouser.userregist.dto.CodBumonDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.CodCompanyDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.TogoUserListDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;
import jp.co.isid.mos.bird.togouser.userregist.logic.UpdateUserLogic;

/**
 * 確認画面アクションクラス
 * @author K.Nihonyanagi
 */
public class TogoUserRegistConfirmActionImpl implements TogoUserRegistConfirmAction{

    public static final String initialize_ACTION_ID = "BUR001A07"; 
    public static final String regist_ACTION_ID = "BUR001A08"; 
    public static final String cancel_ACTION_ID = "BUR001A09"; 
    
    
    /* 使用するロジック */
    private UpdateUserLogic updateUserLogic;
    private DateInfoSetupLogic dateInfoSetupLogic;
    
    /* 使用するDTO */
    private UITogoUserDto uiTogoUserDto;
    private TogoUserLoginDto togoUserLoginDto;
    private TogoUserListDto togoUserListDto;
    private CodBumonDto codBumonDto;
    private CodCompanyDto codCompanyDto;
    
    /* 使用するEntity */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        return null;
    }

    /**
     * 登録処理
     */
    public String regist(){
        
//        //システム日付取得用
//        //===========================================================
//        /* ロジック【日付情報取得】を実行 */
//        BirdDateInfo birdDateInfo = getDateInfoSetupLogic().getBirdDateInfo();
//        getUiTogoUserDto().setBirdDateInfo(birdDateInfo);
//        //===========================================================
        
        //更新ロジック
        getUpdateUserLogic().execute(getUiTogoUserDto());
        
        //更新完了時のDTOクリア処理
        getUiTogoUserDto().clearInputs();
        getTogoUserListDto().clearInputs();
        getCodBumonDto().clearData();
        getCodCompanyDto().clearData();
        
        return "BUR001V01";
    }


    /**
	 * 戻るボタン処理
	 */
    public String cancel() {
        return "BUR001V02";
    }
    

    
    /**
     * ユーザ情報の更新ロジックの設定
     * @return updateUserLogic を戻します。
     */
    public UpdateUserLogic getUpdateUserLogic() {
        return updateUserLogic;
    }
    /**
     * ユーザ情報の更新ロジックの設定
     * @param updateUserLogic updateUserLogic を設定。
     */
    public void setUpdateUserLogic(UpdateUserLogic updateUserLogic) {
        this.updateUserLogic = updateUserLogic;
    }

    public UITogoUserDto getUiTogoUserDto() {
        return uiTogoUserDto;
    }

    public void setUiTogoUserDto(UITogoUserDto uiTogoUserDto) {
        this.uiTogoUserDto = uiTogoUserDto;
    }

    public TogoUserLoginDto getTogoUserLoginDto() {
        return togoUserLoginDto;
    }

    public void setTogoUserLoginDto(TogoUserLoginDto togoUserLoginDto) {
        this.togoUserLoginDto = togoUserLoginDto;
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

    public TogoUserListDto getTogoUserListDto() {
        return togoUserListDto;
    }

    public void setTogoUserListDto(TogoUserListDto togoUserListDto) {
        this.togoUserListDto = togoUserListDto;
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
    
    
}

