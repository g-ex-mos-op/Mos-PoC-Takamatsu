/*
 * 作成日: 2006/2/16
 */
package jp.co.isid.mos.bird.togouser.userregist.action.impl;

import jp.co.isid.mos.bird.togouser.login.dto.TogoUserLoginDto;
import jp.co.isid.mos.bird.togouser.userregist.action.TogoUserRegistEditAction;
import jp.co.isid.mos.bird.togouser.userregist.dto.CodBumonDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.CodCompanyDto;
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;
import jp.co.isid.mos.bird.togouser.userregist.entity.CodCompany;
import jp.co.isid.mos.bird.togouser.userregist.logic.CheckUserInfoLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetBumonCompanyLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetBumonLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetBumonNameLogic;
import jp.co.isid.mos.bird.togouser.userregist.logic.GetCompanyInfoLogic;

/**
 * 登録画面アクションクラス
 * @author K.Nihonyanagi
 */
public class TogoUserRegistEditActionImpl implements TogoUserRegistEditAction {

    public static final String initialize_ACTION_ID = "BUR001A03"; 
    public static final String regist_ACTION_ID = "BUR001A04"; 
    public static final String cancel_ACTION_ID = "BUR001A05"; 
    public static final String loadBumonList_ACTION_ID = "BUR001A06"; 
    
    /* 使用するDTO */
    private UITogoUserDto uiTogoUserDto;
    private CodBumonDto codBumonDto;
    private CodCompanyDto codCompanyDto;
    private TogoUserLoginDto togoUserLoginDto;
    
    /* 使用するロジック */
    private CheckUserInfoLogic checkUserInfoLogic;
    private GetBumonLogic getBumonLogic;
    private GetBumonCompanyLogic getBumonCompanyLogic;
    private GetBumonNameLogic getBumonNameLogic;
    private GetCompanyInfoLogic getCompanyInfoLogic;
    
    /**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
        
        //初期遷移の場合
        if (!getUiTogoUserDto().isInitFlag()) {
            getCodCompanyDto().setCompList(
                    getGetCompanyInfoLogic().execute(""));
    
            //初期表示用部門Listの設定
            getCodBumonDto().setBumonList(getGetBumonCompanyLogic().execute("00"));
 
            //変更の場合、入力ボックスにデータをセットする。
            if ("1".equals(getUiTogoUserDto().getHenkoKbn())) {
                //ユーザ名
                getUiTogoUserDto().setInputUserNameKJ(
                        getUiTogoUserDto().getUiBirdUser().getUserNameKj());
                //ユーザ名カナ
                getUiTogoUserDto().setInputUserNameKana(
                        getUiTogoUserDto().getUiBirdUser().getUserNameKana());
                //会社コード
                getUiTogoUserDto().setInputUserCompanyCode(
                        getUiTogoUserDto().getUiUserCompany().getRCompanyCd());
                //部門コード
                getUiTogoUserDto().setInputUserBumon(
                        getUiTogoUserDto().getUiBirdUser().getBumonCd());
                //退職日
                getUiTogoUserDto().setInputTaishokuDt(
                        getUiTogoUserDto().getUiBirdUser().getTaishokuDt());
                
                //部門リストのプルダウン設定
                getCodBumonDto().setBumonList(
                        getGetBumonCompanyLogic().execute(
                                getUiTogoUserDto().getUiUserCompany().getRCompanyCd()
                        )
                );
            }
        }
        getUiTogoUserDto().setInitFlag(true);
        
        return null;
    }


	/**
     * 登録処理
     */
    public String regist(){
        getCheckUserInfoLogic().execute(getUiTogoUserDto());
        
        // 参照モード以外のとき、確認画面表示用に「会社名称」と「部門名称」をDTOにセットしておく。
        if (!"2".equals(getUiTogoUserDto().getHenkoKbn())){
            setNameToDto();
        }
        
        return "BUR001V03";
    }

    /**
     * 取消処理
     */
    public String cancel(){
        getUiTogoUserDto().setInitFlag(false);
        return "BUR001V01";
    }

    /**
     * 画面表示用に「会社名称」と「部門名称」をDTOにセットしておくメソッド
     */
    private void setNameToDto() {
        // TODO 自動生成されたメソッド・スタブ
        getUiTogoUserDto().getUiBirdUser().setBumonName(
                getGetBumonNameLogic().execute(getUiTogoUserDto().getInputUserBumon()));

        CodCompany cComp = (CodCompany) getGetCompanyInfoLogic().execute(getUiTogoUserDto().getInputUserCompanyCode()).get(0);
        getUiTogoUserDto().getUiUserCompany().setRCompanyName(cComp.getCompanyName());
    }
    
    /**
     * 部門リスト読込処理
     * @return 画面遷移情報
     */
    public String loadBumonList() {
        getCodBumonDto().setBumonList(
                getGetBumonCompanyLogic().execute(
                        getUiTogoUserDto().getInputUserCompanyCode()
                )
        );
        getUiTogoUserDto().setInitFlag(true);
        return null;
    }
    
    
    public CheckUserInfoLogic getCheckUserInfoLogic() {
        return checkUserInfoLogic;
    }


    public UITogoUserDto getUiTogoUserDto() {
        return uiTogoUserDto;
    }


    public void setCheckUserInfoLogic(CheckUserInfoLogic checkUserInfoLogic) {
        this.checkUserInfoLogic = checkUserInfoLogic;
    }


    public void setUiTogoUserDto(UITogoUserDto uiTogoUserDto) {
        this.uiTogoUserDto = uiTogoUserDto;
    }

    public GetBumonLogic getGetBumonLogic() {
        return getBumonLogic;
    }


    public void setGetBumonLogic(GetBumonLogic getBumonLogic) {
        this.getBumonLogic = getBumonLogic;
    }


    public GetBumonCompanyLogic getGetBumonCompanyLogic() {
        return getBumonCompanyLogic;
    }


    public void setGetBumonCompanyLogic(GetBumonCompanyLogic getBumonCompanyLogic) {
        this.getBumonCompanyLogic = getBumonCompanyLogic;
    }


    public TogoUserLoginDto getTogoUserLoginDto() {
        return togoUserLoginDto;
    }


    public void setTogoUserLoginDto(TogoUserLoginDto togoUserLoginDto) {
        this.togoUserLoginDto = togoUserLoginDto;
    }


    public GetBumonNameLogic getGetBumonNameLogic() {
        return getBumonNameLogic;
    }


    public GetCompanyInfoLogic getGetCompanyInfoLogic() {
        return getCompanyInfoLogic;
    }


    public void setGetBumonNameLogic(GetBumonNameLogic getBumonNameLogic) {
        this.getBumonNameLogic = getBumonNameLogic;
    }


    public void setGetCompanyInfoLogic(GetCompanyInfoLogic getCompanyInfoLogic) {
        this.getCompanyInfoLogic = getCompanyInfoLogic;
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

