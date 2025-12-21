/*
 * 作成日: 2008/11/13
 */
package jp.co.isid.mos.bird.togouser.userregist.logic.impl;
import java.util.List;

import jp.co.isid.mos.bird.common.logic.ValidatePasswordLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.GenericCommentException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuKatakanaBugFixedVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.HankakuKatakanaVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.JinmeiVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.ZenkakuKatakanaVerifier;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.togouser.userregist.dao.UIBirdUserDao;
import jp.co.isid.mos.bird.togouser.userregist.dao.UITogoUserRirekiDao;
import jp.co.isid.mos.bird.togouser.userregist.dto.UITogoUserDto;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIBirdUser;
import jp.co.isid.mos.bird.togouser.userregist.entity.UIUserCompany;
import jp.co.isid.mos.bird.togouser.userregist.logic.CheckUserInfoLogic;

/**
 * 登録内容のチェック処理ロジック
 * @author K.Nihonyanagi
 */
public class CheckUserInfoLogicImpl implements CheckUserInfoLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BUR001L04";

    /* 使用DAO */
    private UITogoUserRirekiDao uiTogoUserRirekiDao;
    private UIBirdUserDao uiBirdUserDao;
    
    private ValidatePasswordLogic validatePasswordLogic;
    
    public List execute(UITogoUserDto uiTogoUserDto) {
        
        //nullエンティティをnewしておく
        initializeDtoEntity(uiTogoUserDto);
        
        String henkoKbn = uiTogoUserDto.getHenkoKbn();
        //DTOから使用するクラスを抽出しておく(前処理)
        UIBirdUser uiBirdUser= uiTogoUserDto.getUiBirdUser();
        UIUserCompany uiUserCompany = uiTogoUserDto.getUiUserCompany();
        
        
        // １、.以下の項目がブランクでないかチェックする。  
        // 　　　　　パラメータ．[BIRDユーザー情報]．ユーザID  がブランクの場合、E0506（ユーザID）を発生
        // 　　　　　パラメータ．[BIRDユーザー情報]．ユーザ名称  がブランクの場合、E0506（ユーザ名称）を発生
        // 　　　　　パラメータ．[ユーザー所属会社]．企業コードがブランクの場合、E0506（会社）を発生
        if (uiBirdUser.getUserId() == null
                || "".equals(uiBirdUser.getUserId().trim())){
            throw new NotNullException("ユーザID");
        }
        if (uiBirdUser.getUserNameKj() == null
                || "".equals(uiBirdUser.getUserNameKj().trim())){
            throw new NotNullException("ユーザ名称");
        }
        if (uiUserCompany.getRCompanyCd() == null || "".equals(uiUserCompany.getRCompanyCd().trim())){
            //変更区分が2(参照)でない場合
            if (!"2".equals(henkoKbn)) {
                throw new NotNullException("会社");
            }
        }
                
        // ２、パラメータ．変更区分＝0（新規）もしくは2（参照）の場合、以下の項目がブランクでないかチェックする。
        // 　　　　　パラメータ．[BIRDユーザー情報]．パスワード  がブランクの場合、E0506（パスワード）を発生
        // 　　　　　パラメータ．DTO．確認用パスワードがブランクの場合、E0506（確認用パスワード）を発生
        if ("0".equals(henkoKbn) || "2".equals(henkoKbn)) {
            if ("".equals(new String(uiBirdUser.getUserPswd()))) {
                throw new NotNullException("パスワード");
            }
            if ("".equals(new String(uiBirdUser.getUserPswdConfirm()))) {
                throw new NotNullException("確認用パスワード");
            }
        }
        
        // ３、 
        // 　　　　　3-a、パラメータ．[BIRDユーザー情報]．パスワード≠ パラメータ．DTO．確認用パスワードの場合、  
        //   　　   E0606（"パスワードと確認用パスワード"、"同じ"）を発生      
        if ( !(new String(uiBirdUser.getUserPswd()).equals(
                new String(uiBirdUser.getUserPswdConfirm()))) ) {
            throw new NotRelevantException("パスワードと確認用パスワード", "同じ");
        }
    
        
        // ４、パラメータ．変更区分＝0（新規）の場合  
        // 　　　４-a、Dao【BIRDユーザー情報．BIRDユーザー情報の取得】を実行する。 
        // 　　　　　　　パラメータ：    パラメータ．[BIRDユーザー情報]．ユーザID         
        //           
        // 　　　４-b、Dao【統合ユーザ履歴情報．ユーザー情報の取得】を実行する。  
        // 　　　　　　　パラメータ：    パラメータ．[BIRDユーザー情報]．ユーザID         
        //           
        // 　　　４-c、１の[BIRDユーザー情報]の取得件数≠0 且つ２の[統合ユーザ履歴情報]の取得件数≠0の場合 
        // 　　　　　　　E0607("そのユーザーは登録済み")
        if ("0".equals(henkoKbn)) {
            if (getUiBirdUserDao().getBirdUser(uiBirdUser.getUserId()) != null
             || getUiTogoUserRirekiDao().getUITogoUserRireki(uiBirdUser.getUserId()) != null) {
                throw new GenericCommentException("そのユーザーは登録済み");
            }
        }
        
        
        // ５、パラメータ．[BIRDユーザ情報]．パスワード≠ブランクの場合、 
        //     ５-a、共通ロジック【パスワードのチェック】を実行する。          
        // 　　　パラメータ：    パラメータ．[BIRDユーザー情報]．ユーザID、'1'(本部)
        if (!"".equals(new String(uiBirdUser.getUserPswd()))){
            getValidatePasswordLogic().execute(
                    uiBirdUser.getUserId(),
                    new String(uiBirdUser.getUserPswd()),
                    "1");
        }
        
        // ６．ユーザ名称チェック
        checkUserName(uiBirdUser);
        
        // ７．メールアドレスのチェック add 2020/09/16 xou.zoubun
        checkMailAddress(uiTogoUserDto.getInputMailAdd1());

        checkTaishokuDate(uiBirdUser);
        
        return null;
    }
  

    /**
     * 退職日が妥当か確認する。
     * @param uiBirdUser 
     */
    private void checkTaishokuDate(UIBirdUser uiBirdUser) {
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        if (!dateVerifier.validate(uiBirdUser.getTaishokuDt())) {
            throw new InvalidInputException("退職日","taishokuDt",null);
        }
        DateFormatter dateFormatter = 
            new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
        uiBirdUser.setTaishokuDt(
                dateFormatter.format(
                        uiBirdUser.getTaishokuDt(),
                        DateFormatter.PATTERN_NORMAL,
                        DateFormatter.DATE_TYPE_YMD
                )
        );
    }

    /**
     * ユーザ名、ユーザ名(ｶﾅ)が妥当か確認する。
     * @param uiBirdUser 
     */
    private void checkUserName(UIBirdUser uiBirdUser) {
        //      ユーザ名チェック
        if (uiBirdUser.getUserNameKj() == null) {
            throw new NotNullException("ユーザー名","userNameKj",null);
        }
        else {
            // 文字型の欄に文字以外の型が入力されていないかチェック
            //2006/05/02 外字だが、人名のため使用可能になるように修正
            //          DefaultJapaneseVerifier defaultJapaneseVerifier = new DefaultJapaneseVerifier();
            DefaultJapaneseVerifier defaultJapaneseVerifier = new JinmeiVerifier();
            if (!defaultJapaneseVerifier.validate(uiBirdUser.getUserNameKj())) {
                throw new InvalidInputException("ユーザー名","userNameKj",null);
            }
            // 文字列長チェック
            if (uiBirdUser.getUserNameKj() == null
                    && uiBirdUser.getUserNameKj().getBytes().length > 20) {
                throw new InvalidInputException("ユーザー名","userNameKj",null);
            }
        }

        // 文字型の欄に文字以外の型が入力されていないかチェック
        HankakuKatakanaBugFixedVerifier hankakuKatakanaBugFixedVerifier = new HankakuKatakanaBugFixedVerifier();
        if (!hankakuKatakanaBugFixedVerifier.doValidate(uiBirdUser.getUserNameKana())) {
            throw new InvalidInputException("ユーザー名（ｶﾅ）","userNameKana",null);
        }
        // 文字列長チェック
        if ( uiBirdUser.getUserNameKana() == null
                && uiBirdUser.getUserNameKana().getBytes().length > 20) {
            throw new InvalidInputException("ユーザー名（ｶﾅ）","userNameKana",null);
        }
    }

	/**
	 * メールアドレスチェック add 2020/09/16 xou.zoubun
     * @param str
	 */
	private void checkMailAddress(String str){
		if ((!CommonUtil.isNull(str)) &&
		  ((str.indexOf("@") < 0) ||
		  (str.indexOf(".") < 0) ||
		  (str.indexOf("@") != str.lastIndexOf("@")))){
            throw new InvalidInputException("メールアドレス","mailAdd",null);
		}
		if (!CommonUtil.isNull(str)) {
	    	String mailStr = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@._-";
	    	for (int i=0; i<str.length(); i++) {
	    		char checkChar = str.charAt(i);
	    		if (mailStr.indexOf(checkChar,0)==-1){
	                throw new InvalidInputException("メールアドレス","mailAdd",null);
	    		}
	    	}
		}
	}

    public UITogoUserRirekiDao getUiTogoUserRirekiDao() {
        return uiTogoUserRirekiDao;
    }

    public void setUiTogoUserRirekiDao(UITogoUserRirekiDao uiTogoUserRirekiDao) {
        this.uiTogoUserRirekiDao = uiTogoUserRirekiDao;
    }

    public UIBirdUserDao getUiBirdUserDao() {
        return uiBirdUserDao;
    }

    public void setUiBirdUserDao(UIBirdUserDao uiBirdUserDao) {
        this.uiBirdUserDao = uiBirdUserDao;
    }

    
    /**
     * 「DTO.ENTITY.PROPERTY」の記述がJSFでできなかったため、
     * 「DTO.PROPERTY」を急遽用意し、それを当初予定していた
     * 「DTO.ENTITY.PROPERTY」にセットする処理
     */
    private void initializeDtoEntity(UITogoUserDto uiTUserDto) {

        //UIBirdUserエンティティがnullの場合newする
        if (uiTUserDto.getUiBirdUser() == null) {
            uiTUserDto.setUiBirdUser(new UIBirdUser());
        }
        //UIUserCompanyエンティティがnullの場合newする
        if (uiTUserDto.getUiUserCompany() == null) {
            uiTUserDto.setUiUserCompany(new UIUserCompany());
        }
        
        //パスワードのセット処理
        if (uiTUserDto.getInputPassword() == null) {
            uiTUserDto.getUiBirdUser().setUserPswd("".getBytes());
        }else{
            uiTUserDto.getUiBirdUser().setUserPswd(
                uiTUserDto.getInputPassword().getBytes());
        }
        
        //確認用パスワードのセット処理
        if (uiTUserDto.getInputPasswordConfirm() == null) {
            uiTUserDto.getUiBirdUser().setUserPswdConfirm("".getBytes());
        }else{
            uiTUserDto.getUiBirdUser().setUserPswdConfirm(
                uiTUserDto.getInputPasswordConfirm().getBytes());
        }
        
        //以下、新規か変更の場合
        if ("0".equals(uiTUserDto.getHenkoKbn()) || "1".equals(uiTUserDto.getHenkoKbn())) {
            //新規の場合のみユーザIDをセット
            if ("0".equals(uiTUserDto.getHenkoKbn())) {
                uiTUserDto.getUiBirdUser().setUserId(
                        uiTUserDto.getInputUserId());
            }
            //ユーザ名称
            uiTUserDto.getUiBirdUser().setUserNameKj(
                    uiTUserDto.getInputUserNameKJ());
            //ユーザ名称カナ
            uiTUserDto.getUiBirdUser().setUserNameKana(
                    uiTUserDto.getInputUserNameKana());
            //退職日
            if (uiTUserDto.getInputTaishokuDt() == null) {
                uiTUserDto.getUiBirdUser().setTaishokuDt("");            
            }else{
                uiTUserDto.getUiBirdUser().setTaishokuDt(
                    uiTUserDto.getInputTaishokuDt());
            }
            //部門コード
            uiTUserDto.getUiBirdUser().setBumonCd(
                    uiTUserDto.getInputUserBumon());
            //会社コード
            uiTUserDto.getUiUserCompany().setRCompanyCd(
                    uiTUserDto.getInputUserCompanyCode());
        }
    }



    public ValidatePasswordLogic getValidatePasswordLogic() {
        return validatePasswordLogic;
    }



    public void setValidatePasswordLogic(ValidatePasswordLogic validatePasswordLogic) {
        this.validatePasswordLogic = validatePasswordLogic;
    }
    
}
