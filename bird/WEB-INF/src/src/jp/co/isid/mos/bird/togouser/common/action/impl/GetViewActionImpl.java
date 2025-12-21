/*
 * 作成日: 2008/11/06
 */
package jp.co.isid.mos.bird.togouser.common.action.impl;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.togouser.common.action.GetViewAction;
import jp.co.isid.mos.bird.togouser.common.dto.TogoUserMenuDto;
import jp.co.isid.mos.bird.togouser.filterregist.dto.BumonFilterDto;
import jp.co.isid.mos.bird.togouser.filterregist.dto.TogoUserInfoDto;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetCodBumonLogic;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetTogoFilterInfoLogic;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetTogoUserLogic;
import jp.co.isid.mos.bird.togouser.filterregist.logic.UpdateUserFilterLogic;
import jp.co.isid.mos.bird.togouser.userregist.dto.CodCompanyDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.TogoUserListDto;

/**
 * @author S.yamauchi
 */
public class GetViewActionImpl implements GetViewAction, CommonAction {
	
    public static final String init_ACTION_ID = "BUR003A01";  
    public static final String move_ACTION_ID = "BUR003A02"; ; 
    
    //DTO
    private CodCompanyDto codCompanyDto;
    private BumonFilterDto bumonFilterDto;
    private TogoUserInfoDto togoUserInfoDto;   
    private TogoUserListDto togoUserListDto;
    private TogoUserMenuDto togoUserMenuDto;
    //ロジックゲッター用
    private GetTogoUserLogic getTogoUserListLogic;
    private GetTogoFilterInfoLogic getTogoFilterInfoLogic;
    private GetCodBumonLogic getCodBumonLogic;    
    private UpdateUserFilterLogic updateUserFilterLogic;
    private DateInfoSetupLogic dateInfoSetupLogic;
    /* ==================================================
     * アクション名：初期処理
     * アクションID：BUR001A01
     * ==================================================
     */
    public String initialize(){
        //後で初期化実装
        /* ロジック【日付情報取得】を実行 */
//        BirdDateInfo birdDateInfo = getDateInfoSetupLogic().getBirdDateInfo();
//        getTogoUserInfoDto().setBirdDateInfo(birdDateInfo);
        return null;
    } 

    
    /* ==================================================
     * アクション名：画面遷移
     * アクションID：BUR003A01
     * ==================================================
     */
    public String move(){
        String viewid = getTogoUserMenuDto().getInitViewId();
        getTogoUserListDto().clearInputs();
        getBumonFilterDto().initialize();
        getTogoUserInfoDto().initialize();
        return viewid;
    } 
    
    public TogoUserMenuDto getTogoUserMenuDto() {
        return togoUserMenuDto;
    }

    public void setTogoUserMenuDto(TogoUserMenuDto TogoUserMenuDto) {
        this.togoUserMenuDto = TogoUserMenuDto;
    }
    
    public TogoUserListDto getTogoUserListDto() {
        return togoUserListDto;
    }

    public void setTogoUserListDto(TogoUserListDto togoUserListDto) {
        this.togoUserListDto = togoUserListDto;
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

    public CodCompanyDto getCodCompanyDto() {
        return codCompanyDto;
    }

    public void setCodCompanyDto(CodCompanyDto codCompanyDto) {
        this.codCompanyDto = codCompanyDto;
    }

    //山内追加
    public GetTogoUserLogic getGetTogoUserLogic() {
        return getTogoUserListLogic;
    }

    public void setGetTogoUserLogic(GetTogoUserLogic getTogoUserListLogic) {
        this.getTogoUserListLogic = getTogoUserListLogic;
    }
    
    public TogoUserInfoDto getTogoUserInfoDto() {
        return togoUserInfoDto;
    }

    public void setTogoUserInfoDto(TogoUserInfoDto togoUserInfoDto) {
        this.togoUserInfoDto = togoUserInfoDto;
    }
    
    public GetTogoFilterInfoLogic getGetTogoFilterInfoLogic() {
        return getTogoFilterInfoLogic;
    }

    public void setGetTogoFilterInfoLogic(GetTogoFilterInfoLogic getTogoFilterInfoLogic) {
        this.getTogoFilterInfoLogic = getTogoFilterInfoLogic;
    }
    
    public BumonFilterDto getBumonFilterDto() {
        return bumonFilterDto;
    }

    public void setBumonFilterDto(BumonFilterDto bumonFilterDto) {
        this.bumonFilterDto = bumonFilterDto;
    }
    
    public GetCodBumonLogic getGetCodBumonLogic() {
        return getCodBumonLogic;
    }

    public void setGetCodBumonLogic(GetCodBumonLogic getCodBumonLogic) {
        this.getCodBumonLogic = getCodBumonLogic;
    }
    
    public UpdateUserFilterLogic getUpdateUserFilterLogic() {
        return updateUserFilterLogic;
    }

    public void setUpdateUserFilterLogic(UpdateUserFilterLogic updateUserFilterLogic) {
        this.updateUserFilterLogic = updateUserFilterLogic;
    }

    public DateInfoSetupLogic getDateInfoSetupLogic() {
        return dateInfoSetupLogic;
    }

    public void setDateInfoSetupLogic(DateInfoSetupLogic dateInfoSetupLogic) {
        this.dateInfoSetupLogic = dateInfoSetupLogic;
    }
}
    