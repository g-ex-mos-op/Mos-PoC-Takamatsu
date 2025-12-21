/*
 * 作成日: 2006/02/13
 * 修正日: 2007/02/13 オーナーユーザーで会社変更時にBM03USCPが更新されない問題を修正 
 * 修正日: 2008/12/05 e-mossles廃止に伴いBR08UCIF関連の処理を削除
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountedit.dao.UIKeiyakuTypeDao;
import jp.co.isid.mos.bird.bizadmin.accountedit.dto.AccounteditDto;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.GetRevisionDataLogic;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.GetCtlPasswordPolicyLogic;
import jp.co.isid.mos.bird.bizadmin.accountedit.logic.GetLoginFailInfoLogic;
import jp.co.isid.mos.bird.bizadmin.common.code.ZokuseiKbn;
import jp.co.isid.mos.bird.bizadmin.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserGyotaiDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserOnerDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIKeiyakuType;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.LengthVerifier;

/**
 * ユーザーアカウント情報取得設定
 * 
 * 作成日:2010/03/19
 * @author xkinu
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 *
 */
public class GetRevisionDataLogicImpl implements GetRevisionDataLogic {
	
    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BBA001L01";

	private UIUserDao bizadminUIUserDao;
	private CodCompanyDao bizadminCodCompanyDao;
	private UIUserCompanyDao bizadminUIUserCompanyDao;
	private UIUserGyotaiDao bizadminUIUserGyotaiDao;
	private UIUserOnerDao bizadminUIUserOnerDao;
	private UIUserMiseDao bizadminUIUserMiseDao;
    private UIKeiyakuTypeDao accounteditKeiyakuTypeDao;
    /** LOGIC【パスワード誤回数情報取得】 */
    private GetLoginFailInfoLogic getLoginFailInfoLogic;
    /** LOGIC【パスワードポリシー取得】*/
    private GetCtlPasswordPolicyLogic accounteditGetCtlPasswordPolicyLogic;
	
    /**
     * 事前条件処理
     * @param userId
     */
    private void validate(String userId)
    {
        if (CommonUtil.isNull(userId)) {
            throw new NotNullException("ユーザーID", "userId", 0);
        }
		CodeVerifier codeVerifier = new CodeVerifier(true);
		if(!codeVerifier.validate(userId.trim())){
			throw new InvalidInputException("ユーザー", "userId", 0);
		}
        LengthVerifier lengthVerifier = new LengthVerifier(8);
        if ( !lengthVerifier.validate( userId ) ) {
        	throw new InvalidInputException("ユーザーID", "userId", 0);
        }
    }
	/**
	 * 実行処理
	 */
	public void execute(String userId, AccounteditDto sessionDto ){
		//事前条件処理
		validate(userId);
        /* ユーザーID前ゼロ付加 */
        CodeFormatter formatter = new CodeFormatter(8);
        formatter.setFormatPattern("00000000");
        userId = formatter.format(userId, true);
        
		if(getBizadminUIUserDao().getUser(userId) == null){
			//ユーザ情報
			throw new NoResultException();
		}
		UIUser uIUser = getBizadminUIUserDao().getUser(userId);
		//ユーザー情報をDTO【自画面Session】.[ユーザー情報]へ設定します。
		sessionDto.setUIUser(uIUser);
        /*パスワード*/
        String strPswd = new  String(uIUser.getUserPswd());
        /*パスワード確認*/
        sessionDto.setUserPassWordKakunin(strPswd);
        //本部
		if(UserType.isHonbu(uIUser.getUsertypeCd())){
				executeHonbu(sessionDto);
		}
		//オーナー
		else if(UserType.isOner(uIUser.getUsertypeCd())){
			executeOner(sessionDto);
		}
		else if(UserType.isTenpo(uIUser.getUsertypeCd())) {
			executeMise(sessionDto);
		}
		//ログインユーザーがシステム管理者の場合
		if(sessionDto.isSystemAdminUser()){
			//LOGIC【パスワード誤回数情報取得】を実行し、[パスワード誤回数]を取得します。
			CtlLoginFailKaisu ctlLoginFailKaisu = getGetLoginFailInfoLogic().execute(uIUser.getUserId());
			//上記で取得した[パスワード誤回数]を更新用に保持します。
			sessionDto.setCtlLoginFailKaisu(ctlLoginFailKaisu);
			//LOGIC【パスワードポリシー取得】を実行し、編集ユーザーのパスワードポリシーを取得します。
			CtlPasswordPolicy ctlPswdPolicy = getAccounteditGetCtlPasswordPolicyLogic().execute(uIUser.getUserId());
	        int maxFailCnt = ctlPswdPolicy.getFailureToLockCnt();
	    	//５．編集対象ユーザーがパスワードポリシーのパスワード誤回数上限値を設定されていない場合は下記の処理を行います。
	        int nowFailCnt = ctlLoginFailKaisu.getLoginFail().intValue();
	        if(maxFailCnt>0 && maxFailCnt <= nowFailCnt) {
	        	//５－１．戻り値[パスワード誤回数].失敗回数を0(ゼロ)に設定します。
	        	sessionDto.setPwLockMsg("ロック状態");
	        }
		}
	}
	/**
	 * 本部用
     * @modifier xkinu 2013/01/24 海外売上集信対応 海外の会社は含まないよう改修
	 * @param sessionDto
	 */
	private void executeHonbu(AccounteditDto sessionDto) {
		UIUser uIUser = sessionDto.getUIUser();
        /* 契約タイプ取得 */
        UIKeiyakuType uiKeiyakuType = getAccounteditKeiyakuTypeDao().getKeiyakuType(uIUser.getUserId());
        if (uiKeiyakuType != null) {
        	sessionDto.setKeiyakuType(uiKeiyakuType.getKeiyakuType());
        }
		/*ユーザ管理業態*/
		List gyotai = getBizadminUIUserGyotaiDao().select(uIUser.getUserId());
		/*ユーザ所属会社 */
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
        UIUserCompany company = null;
		List userCompanyList = bizadminUIUserCompanyDao.select(uIUser.getUserId(), false, isForeignIn);
		if(userCompanyList == null || userCompanyList.size()==0) {
			throw new NotExistException("ユーザ所属会社");
		}
        for (Iterator i = userCompanyList.iterator(); i.hasNext();) {
            company = (UIUserCompany) i.next();
            if(ZokuseiKbn.SHOZOKU.equals(company.getZokuseiKbn())){
                sessionDto.setUserCompany(company);
                break;
            }
        }
		if(company==null){
			throw new NotExistException("ユーザ所属会社");
		}
		/* 	業態 */
		sessionDto.setUserGyotaiList(gyotai);
        /* 全管理会社情報 */
    	sessionDto.setUserCompanyList(userCompanyList);
    	sessionDto.setCompanyList(getBizadminCodCompanyDao().select(isForeignIn));
		/*ユーザ対応オーナー*/
		List onerList = getBizadminUIUserOnerDao().select(uIUser.getUserId(), null, isForeignIn);
		UIUserOner eOner = new UIUserOner();
		for (Iterator i = onerList.iterator(); i.hasNext();) {
			eOner = (UIUserOner) i.next();
			break;
        }
		sessionDto.setUserOnerList(onerList);
        sessionDto.setUserOner(eOner);
        
	}
	/**
	 * オーナーユーザー用
     * @modifier xkinu 2013/01/24 海外売上集信対応 海外の会社は含まないよう改修
	 * @param sessionDto
	 */
	private void executeOner(AccounteditDto sessionDto) {
		UIUser uIUser = sessionDto.getUIUser();

        /* 契約タイプ取得 */
        UIKeiyakuType uiKeiyakuType = getAccounteditKeiyakuTypeDao().getKeiyakuType(uIUser.getUserId());
        if (uiKeiyakuType != null) {
        	sessionDto.setKeiyakuType(uiKeiyakuType.getKeiyakuType());
        }
		/*ユーザ管理業態*/
		List gyotai = getBizadminUIUserGyotaiDao().select(uIUser.getUserId());
		if(gyotai==null) {
			throw new NoResultException();
		}
        /*所属会社取得*/
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
        List companyList = bizadminUIUserCompanyDao.selectAllCompany(uIUser.getUserId(), true, isForeignIn);
        sessionDto.setUserCompanyList(companyList);
		/*ユーザ対応オーナー*/
		List onerList = getBizadminUIUserOnerDao().selectAllComp(sessionDto.getUIUser().getUserId(), null, isForeignIn);
		sessionDto.setUserOnerList(onerList);
		/* 	業態setting */
		sessionDto.setUserGyotaiList(gyotai);
	}
	/**
	 * 店舗ユーザー用
     * @modifier xkinu 2013/01/24 海外売上集信対応 海外の会社は含まないよう改修
	 * @param sessionDto
	 */
	private void executeMise(AccounteditDto sessionDto) {
		UIUser uIUser = sessionDto.getUIUser();

        /* 契約タイプ取得 */
        UIKeiyakuType uiKeiyakuType = getAccounteditKeiyakuTypeDao().getKeiyakuType(uIUser.getUserId());
        if (uiKeiyakuType != null) {
        	sessionDto.setKeiyakuType(uiKeiyakuType.getKeiyakuType());
        }
		/*ユーザ所属会社 */
        /*所属会社取得*/
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
		List companyList = bizadminUIUserCompanyDao.select(uIUser.getUserId(), true, isForeignIn);
        for (Iterator i = companyList.iterator(); i.hasNext();) {
        	UIUserCompany company = (UIUserCompany) i.next();
            if(company.isChecked()){
                sessionDto.setUserCompany(company);
            }
        }
        sessionDto.setCompanyList(getBizadminCodCompanyDao().select(isForeignIn));
		/*ユーザ対応店舗*/
		UIUserMise mise = getBizadminUIUserMiseDao().select(uIUser.getUserId());
		if(mise==null){
			throw new NoResultException();
		}
		/*ユーザ対応店舗*/
		sessionDto.setUserMise(mise);
	}
	/**
	 * @return クラス変数accounteditGetCtlPasswordPolicyLogic を戻します。
	 */
	public GetCtlPasswordPolicyLogic getAccounteditGetCtlPasswordPolicyLogic() {
		return accounteditGetCtlPasswordPolicyLogic;
	}
	/**
	 * @param accounteditGetCtlPasswordPolicyLogic を クラス変数accounteditGetCtlPasswordPolicyLogicへ設定します。
	 */
	public void setAccounteditGetCtlPasswordPolicyLogic(
			GetCtlPasswordPolicyLogic accounteditGetCtlPasswordPolicyLogic) {
		this.accounteditGetCtlPasswordPolicyLogic = accounteditGetCtlPasswordPolicyLogic;
	}
	/**
	 * @return クラス変数accounteditKeiyakuTypeDao を戻します。
	 */
	public UIKeiyakuTypeDao getAccounteditKeiyakuTypeDao() {
		return accounteditKeiyakuTypeDao;
	}
	/**
	 * @param accounteditKeiyakuTypeDao を クラス変数accounteditKeiyakuTypeDaoへ設定します。
	 */
	public void setAccounteditKeiyakuTypeDao(
			UIKeiyakuTypeDao accounteditKeiyakuTypeDao) {
		this.accounteditKeiyakuTypeDao = accounteditKeiyakuTypeDao;
	}
	/**
	 * @return クラス変数accounteditUIUserGyotaiDao を戻します。
	 */
	public UIUserGyotaiDao getBizadminUIUserGyotaiDao() {
		return bizadminUIUserGyotaiDao;
	}
	/**
	 * @param bizadminUIUserGyotaiDao を クラス変数accounteditUIUserGyotaiDaoへ設定します。
	 */
	public void setBizadminUIUserGyotaiDao(
			UIUserGyotaiDao accounteditUIUserGyotaiDao) {
		this.bizadminUIUserGyotaiDao = accounteditUIUserGyotaiDao;
	}
	/**
	 * @return クラス変数accounteditUIUserMiseDao を戻します。
	 */
	public UIUserMiseDao getBizadminUIUserMiseDao() {
		return bizadminUIUserMiseDao;
	}
	/**
	 * @param bizadminUIUserMiseDao を クラス変数accounteditUIUserMiseDaoへ設定します。
	 */
	public void setBizadminUIUserMiseDao(UIUserMiseDao accounteditUIUserMiseDao) {
		this.bizadminUIUserMiseDao = accounteditUIUserMiseDao;
	}
	/**
	 * @return クラス変数accounteditUIUserOnerDao を戻します。
	 */
	public UIUserOnerDao getBizadminUIUserOnerDao() {
		return bizadminUIUserOnerDao;
	}
	/**
	 * @param bizadminUIUserOnerDao を クラス変数accounteditUIUserOnerDaoへ設定します。
	 */
	public void setBizadminUIUserOnerDao(UIUserOnerDao accounteditUIUserOnerDao) {
		this.bizadminUIUserOnerDao = accounteditUIUserOnerDao;
	}
	/**
	 * @return クラス変数getLoginFailInfoLogic を戻します。
	 */
	public GetLoginFailInfoLogic getGetLoginFailInfoLogic() {
		return getLoginFailInfoLogic;
	}
	/**
	 * @param getLoginFailInfoLogic を クラス変数getLoginFailInfoLogicへ設定します。
	 */
	public void setGetLoginFailInfoLogic(GetLoginFailInfoLogic getLoginFailInfoLogic) {
		this.getLoginFailInfoLogic = getLoginFailInfoLogic;
	}
	/**
	 * @return クラス変数accounteditUIUserCompanyDao を戻します。
	 */
	public UIUserCompanyDao getBizadminUIUserCompanyDao() {
		return bizadminUIUserCompanyDao;
	}
	/**
	 * @param bizadminUIUserCompanyDao を クラス変数accounteditUIUserCompanyDaoへ設定します。
	 */
	public void setBizadminUIUserCompanyDao(
			UIUserCompanyDao accounteditUIUserCompanyDao) {
		this.bizadminUIUserCompanyDao = accounteditUIUserCompanyDao;
	}
	/**
	 * @return クラス変数bizadminUIUserDao を戻します。
	 */
	public UIUserDao getBizadminUIUserDao() {
		return bizadminUIUserDao;
	}
	/**
	 * @param bizadminUIUserDao を クラス変数bizadminUIUserDaoへ設定します。
	 */
	public void setBizadminUIUserDao(UIUserDao bizadminUIUserDao) {
		this.bizadminUIUserDao = bizadminUIUserDao;
	}
	/**
	 * @return クラス変数bizadminCodCompanyDao を戻します。
	 */
	public CodCompanyDao getBizadminCodCompanyDao() {
		return bizadminCodCompanyDao;
	}
	/**
	 * @param bizadminCodCompanyDao を クラス変数bizadminCodCompanyDaoへ設定します。
	 */
	public void setBizadminCodCompanyDao(CodCompanyDao bizadminCodCompanyDao) {
		this.bizadminCodCompanyDao = bizadminCodCompanyDao;
	}
}

	


