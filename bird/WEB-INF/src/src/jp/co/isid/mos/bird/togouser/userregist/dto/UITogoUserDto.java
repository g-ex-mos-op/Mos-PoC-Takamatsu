package jp.co.isid.mos.bird.togouser.userregist.dto;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.togouser.userregist.entity.UITogoUserRireki;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserCompany;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserShozoku;

public class UITogoUserDto {

    private UITogoUserRireki uiTogoUserRireki;
    private UIBirdUser uiBirdUser;
    private UIUserCompany uiUserCompany;
    private UIUserShozoku uiUserShozoku;
    private BirdDateInfo birdDateInfo;
    private String HenkoKbn;
    private String inputUserId;
    private String inputPassword;
    private String inputPasswordConfirm;
    private String inputUserNameKJ;
    private String inputUserNameKana;
    private String inputUserCompanyCode;
    private String inputUserBumon;
    private String inputTaishokuDt;
    private String inputMailAdd1; //add 2020/09/16 xou.zoubun メールアドレスの追加
    private boolean initFlag;
    private String loginUserId;
    
    public String getHenkoKbn() {
        return HenkoKbn;
    }
    public void setHenkoKbn(String henkoKbn) {
        HenkoKbn = henkoKbn;
    }

    public String getInputUserId() {
        return inputUserId;
    }
    public void setInputUserId(String inputUserId) {
        this.inputUserId = inputUserId;
    }
    public String getInputPassword() {
        return inputPassword;
    }
    public String getInputPasswordConfirm() {
        return inputPasswordConfirm;
    }
    public String getInputTaishokuDt() {
        return inputTaishokuDt;
    }
    public String getInputUserBumon() {
        return inputUserBumon;
    }
    public String getInputUserNameKana() {
        return inputUserNameKana;
    }
    public String getInputUserNameKJ() {
        return inputUserNameKJ;
    }
    public String getInputMailAdd1() { //add 2020/09/16 xou.zoubun メールアドレスの追加
        if(inputMailAdd1 == null){
            return "";
        }
        return inputMailAdd1.trim();
    }
    public void setInputPassword(String inputPassword) {
        this.inputPassword = inputPassword;
    }
    public void setInputPasswordConfirm(String inputPasswordConfirm) {
        this.inputPasswordConfirm = inputPasswordConfirm;
    }
    public void setInputTaishokuDt(String inputTaishokuDt) {
        this.inputTaishokuDt = inputTaishokuDt;
    }
    public void setInputUserBumon(String inputUserBumon) {
        this.inputUserBumon = inputUserBumon;
    }
    public void setInputUserNameKana(String inputUserNameKana) {
        this.inputUserNameKana = inputUserNameKana;
    }
    public void setInputUserNameKJ(String inputUserNameKJ) {
        this.inputUserNameKJ = inputUserNameKJ;
    }
    public void setInputMailAdd1(String inputMailAdd1) { //add 2020/09/16 xou.zoubun メールアドレスの追加
        this.inputMailAdd1 = inputMailAdd1;
    }
    public String getInputUserCompanyCode() {
        return inputUserCompanyCode;
    }
    public void setInputUserCompanyCode(String inputUserCompany) {
        this.inputUserCompanyCode = inputUserCompany;
    }
    public boolean isInitFlag() {
        return initFlag;
    }
    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }
    public UIBirdUser getUiBirdUser() {
        return uiBirdUser;
    }
    public UITogoUserRireki getUiTogoUserRireki() {
        return uiTogoUserRireki;
    }
    public UIUserCompany getUiUserCompany() {
        return uiUserCompany;
    }
    public UIUserShozoku getUiUserShozoku() {
        return uiUserShozoku;
    }
    public void setUiBirdUser(UIBirdUser uiBirdUser) {
        this.uiBirdUser = uiBirdUser;
    }
    public void setUiTogoUserRireki(UITogoUserRireki uiTogoUserRireki) {
        this.uiTogoUserRireki = uiTogoUserRireki;
    }
    public void setUiUserCompany(UIUserCompany uiUserCompany) {
        this.uiUserCompany = uiUserCompany;
    }
    public void setUiUserShozoku(UIUserShozoku uiUserShozoku) {
        this.uiUserShozoku = uiUserShozoku;
    }
    
    public String getLoginUserId() {
        return loginUserId;
    }
    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    public String getSysDate() {
        return getBirdDateInfo().getSysDate();
    }

    /**
     * 画面に入力された値(DTO)をすべてクリアする。
     */
    public void clearInputs() {
        this.uiTogoUserRireki = new UITogoUserRireki();
        this.uiBirdUser = new UIBirdUser();
        this.uiUserCompany = new UIUserCompany();
        this.uiUserShozoku = new UIUserShozoku();
        this.birdDateInfo = new BirdDateInfo();
        this.HenkoKbn = "";
        this.inputUserId = "";
        this.inputPassword = "";
        this.inputPasswordConfirm = "";
        this.inputUserNameKJ = "";
        this.inputUserNameKana = "";
        this.inputMailAdd1 = "";
        this.inputUserCompanyCode = "";
        this.inputUserBumon = "";
        this.inputTaishokuDt = "";
        this.initFlag = false;
    }

    public boolean isPassWordReset() {
    	return !CommonUtil.isNull(getInputPassword());
    }
        
}
