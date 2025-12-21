/*
 * 作成日: 2008/11/06
 */
package jp.co.isid.mos.bird.togouser.filterregist.action.impl;


import java.util.List;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.DateInfoSetupLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.togouser.filterregist.action.GetTogoFilterAction;
import jp.co.isid.mos.bird.togouser.filterregist.dto.BumonFilterDto;
import jp.co.isid.mos.bird.togouser.filterregist.dto.TogoUserInfoDto;
import jp.co.isid.mos.bird.togouser.filterregist.entity.CodBumonName;
import jp.co.isid.mos.bird.togouser.filterregist.entity.UIBumonFilter;
import jp.co.isid.mos.bird.togouser.filterregist.entity.UITogoUserSaisin;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetCodBumonLogic;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetTogoFilterInfoLogic;
import jp.co.isid.mos.bird.togouser.filterregist.logic.GetTogoUserLogic;
import jp.co.isid.mos.bird.togouser.filterregist.logic.UpdateUserFilterLogic;
import jp.co.isid.mos.bird.togouser.userregist.dto.TogoUserListDto;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;

/**
 * @author S.yamauchi
 */
public class GetTogoFilterActionImpl implements GetTogoFilterAction, CommonAction {
	
    public static final String initialize_ACTION_ID = "BUR002A01"; 
    public static final String search_ACTION_ID = "BUR002A02";
    public static final String regist_ACTION_ID = "BUR002A04";
    public static final String MOSFOOD_COMPANY_CD = "00"; 
    
    //DTO
    private BumonFilterDto bumonFilterDto;
    private TogoUserInfoDto togoUserInfoDto;   
    //ENTITY
    private UITogoUserSaisin uiTogoUserSaisin;
    private CodBumonName codBumonName;
    private UIBumonFilter uiBumonFilter;
    //ロジックゲッター用
    private GetTogoUserLogic getTogoUserListLogic;
    private GetTogoFilterInfoLogic getTogoFilterInfoLogic;
    private GetCodBumonLogic getCodBumonLogic;    
    private UpdateUserFilterLogic updateUserFilterLogic;
    private DateInfoSetupLogic dateInfoSetupLogic;
    /* ==================================================
     * アクション名：初期処理
     * アクションID：BUR002A01
     * ==================================================
     */
    public String initialize(){
        //後で初期化実装
        /* ロジック【日付情報取得】を実行 */
        BirdDateInfo birdDateInfo = getDateInfoSetupLogic().getBirdDateInfo();
        getTogoUserInfoDto().setBirdDateInfo(birdDateInfo);
        return null;
    } 


    /* ==================================================
     * アクション名：検索
     * アクションID：BUR002A02
     * ==================================================
     */
    public String search(){
        if(getTogoUserInfoDto().getUserId()==null
                || getTogoUserInfoDto().getUserId().equals("")){
            getTogoUserInfoDto().setUserlist(null);
            getBumonFilterDto().setBumonFilterList(null);
            throw new NotNullException("ユーザーID");
        }else{
            if(idCheck(getTogoUserInfoDto().getUserId())){
                throw new InvalidInputException("ユーザー");
            }
        }
        String useId = getTogoUserInfoDto().getUserId();
        List selectUserList = getGetTogoUserLogic().execute(useId);
        if(selectUserList.size()==0){
            getTogoUserInfoDto().setUserlist(null);
            getBumonFilterDto().setBumonFilterList(null);
            throw new NotExistException("該当ユーザー");
        }else{            
            getTogoUserInfoDto().setUserlist(selectUserList);
        }    
        uiTogoUserSaisin = (UITogoUserSaisin)selectUserList.get(0);
        //DTOにセット
        String strBumonCd = uiTogoUserSaisin.getBumonCdKakutei8();
        if(strBumonCd == null || strBumonCd.equals("")){
            //部門マスタ．部門マスタの検索
            String strSalaryCd = uiTogoUserSaisin.getSalaryCd();
            CodeFormatter formatter = new CodeFormatter(8);
            formatter.setFormatPattern("00000000");
            strSalaryCd = formatter.format(strSalaryCd, true);
            if (!strSalaryCd.equals("")){
                List BumonNameList =  getGetCodBumonLogic().execute(strSalaryCd);
                codBumonName = (CodBumonName)BumonNameList.get(0);
               //統合ユーザーDTOに部門セット
                uiTogoUserSaisin.setBumonName(codBumonName.getBumonName());
                uiTogoUserSaisin.setBumonCdKakutei8(codBumonName.getBumonCd());
            }    

        }
        strBumonCd = uiTogoUserSaisin.getBumonCdKakutei8();

        String strUserId = uiTogoUserSaisin.getUserId();
        List BumonFilterList = getGetTogoFilterInfoLogic().execute(strUserId,strBumonCd);
        uiBumonFilter = (UIBumonFilter) BumonFilterList.get(0);

        if(BumonFilterList.size()==0){
            getTogoUserInfoDto().setUserlist(null);
            getBumonFilterDto().setBumonFilterList(null);
            throw new NotExistException("該当データ");
        }

        getBumonFilterDto().setBumonFilterList(BumonFilterList);

        
        return null;
    } 

    /* ==================================================
     * アクション名：画面遷移(戻るボタン押し)
     * アクションID：
     * ==================================================
     */
    public String back(){
        getBumonFilterDto().initialize();
        getTogoUserInfoDto().initialize();
        return "BUR002V01";
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

    /* ==================================================
     * アクション名：登録
     * アクションID：BUR002A04
     * ==================================================
     */
    public String regist(){
        List userSaisinList = getTogoUserInfoDto().getUserlist();
        List userInfoList = getBumonFilterDto().getBumonFilterList();
        String sysDate = getTogoUserInfoDto().getBirdDateInfo().getSysDate();
        String strLoginUser = getTogoUserInfoDto().getStrLoginUser();
        
        getUpdateUserFilterLogic().execute(userInfoList,userSaisinList, sysDate, strLoginUser);
        getBumonFilterDto().initialize();
        getTogoUserInfoDto().setUserlist(null);
    return null;
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
    