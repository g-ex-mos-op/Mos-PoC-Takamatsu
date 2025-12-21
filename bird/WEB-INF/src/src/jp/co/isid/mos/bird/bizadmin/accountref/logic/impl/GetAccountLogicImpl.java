/*
 * 作成日: 2006/03/03
 * 修正日: 2008/12/05 e-mossles廃止に伴いBR08UCIF関連の処理を削除
 *  
 */
package jp.co.isid.mos.bird.bizadmin.accountref.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.accountref.dto.AccountRefDto;
import jp.co.isid.mos.bird.bizadmin.accountref.logic.GetAccountLogic;
import jp.co.isid.mos.bird.bizadmin.common.code.ZokuseiKbn;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserGyotaiDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserMiseDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserOnerDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * アカウント照会情報取得処理
 * 
 * @author Noh
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 */
public class GetAccountLogicImpl implements GetAccountLogic {
	
    /* ロジックＩＤ*/ 
	public static final String LOGIC_ID = "BBA002L01";

	private UIUserDao bizadminUIUserDao;
	/** DAO【ユーザー所属会社情報】*/
	private UIUserCompanyDao bizadminUIUserCompanyDao;
	/** DAO【ユーザー管理業態情報】*/
	private UIUserGyotaiDao bizadminUIUserGyotaiDao;
	/** DAO【ユーザー対応オーナー情報】*/
	private UIUserOnerDao bizadminUIUserOnerDao;
	/** DAO【ユーザー対応店舗情報】*/
	private UIUserMiseDao bizadminUIUserMiseDao;
    /**
     * 事前条件処理
     * @param userId
     */
    private void validate(String userId) {
    	if(CommonUtil.isNull(userId)) {
    		throw new NotNullException("ユーザーID", "userIdIn", 0);
    	}
		CodeVerifier codeVerifier = new CodeVerifier(true);
		if(!codeVerifier.validate(userId.trim())){
			throw new InvalidInputException("ユーザーID", "userIdIn", 0);
		}
    }
	/**
	 * 照会情報取得処理
     * @modifier xkinu 2013/01/24 海外売上集信対応 海外の会社は含まないよう改修
	 * 
	 * @param dto
	 * @param userId 照会対象のユーザーID
	 * @return
	 */
	public String execute(AccountRefDto dto, String userId){
		//０．事前条件処理を行います。
		validate(userId);
		//１．
        CodeFormatter formatter = new CodeFormatter(8);
        formatter.setFormatPattern("00000000");
        //照会対象のユーザーIDを８桁フォーマットを行います。
        userId = formatter.format(userId, true);
        
        UIUser userInfo = bizadminUIUserDao.getUser(userId);
		if(userInfo == null) {
			throw new NoResultException();
		}
		/*ユーザID*/
		dto.setUserInfo(userInfo);
		
		String userTypeCd = userInfo.getUsertypeCd();
		String pageNum=null;
		/*ユーザ所属会社 */
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
		if(UserType.isHonbu(userTypeCd)){		/*本部*/
			List listUserCompany = bizadminUIUserCompanyDao.select(userInfo.getUserId(), false, isForeignIn);
			if(listUserCompany != null && listUserCompany.size() >= 1) {
		        for (Iterator i = listUserCompany.iterator(); i.hasNext();) {
		        	UIUserCompany company = (UIUserCompany) i.next();
		            if(ZokuseiKbn.SHOZOKU.equals(company.getZokuseiKbn())){
		            	dto.setUserCompany(company);
		                break;
		            }
		        }
            }
			/*ユーザ管理業態*/
			List listUserGyotai = bizadminUIUserGyotaiDao.select(userInfo.getUserId());
			if(listUserGyotai == null || listUserGyotai.size() <0){
				throw new NoResultException();
			}
			/*業態*/
			dto.setListUserGyotai(listUserGyotai);
			/*ユーザ対応オーナー*/
			List listUserOner = bizadminUIUserOnerDao.select(userInfo.getUserId(), ZokuseiKbn.KEIYAKU, isForeignIn);
			//DTO【アカウント情報保持】.List[[ユーザ対応オーナー]]へ
			//処理１で取得したList[[ユーザ対応オーナー]]を設定します。
			if(listUserOner != null && listUserOner.size() >=1){
				dto.setUserOner((UIUserOner)listUserOner.get(0));
			}
			pageNum = "BBA002V04";
		}
		else if(UserType.isOner(userTypeCd)){	/*オーナー*/
			/*ユーザ対応オーナー*/
			List listUserOner = bizadminUIUserOnerDao.selectAllComp(userInfo.getUserId(), null, isForeignIn);
			//DTO【アカウント情報保持】.List[[ユーザ対応オーナー]]へ
			//処理１で取得したList[[ユーザ対応オーナー]]を設定します。
			dto.setListUserOner(listUserOner);
			/*ユーザ管理業態*/
			List listUserGyotai = bizadminUIUserGyotaiDao.select(userInfo.getUserId());
			if(listUserGyotai == null || listUserGyotai.size() <0){
				throw new NoResultException();
			}
			/*業態*/
			dto.setListUserGyotai(listUserGyotai);
			pageNum = "BBA002V02";
		}
		else if(UserType.isTenpo(userTypeCd)){												/*店舗*/
			/*ユーザ対応店舗*/
			UIUserMise userMise = bizadminUIUserMiseDao.select(userInfo.getUserId());
			if(userMise == null){
				throw new NoResultException();
			}
            dto.setUserMise(userMise);
			pageNum = "BBA002V03";
		}
		return pageNum;
		
	}
	/**
	 * @return クラス変数bizadminUIUserCompanyDao を戻します。
	 */
	public UIUserCompanyDao getBizadminUIUserCompanyDao() {
		return bizadminUIUserCompanyDao;
	}
	/**
	 * @param bizadminUIUserCompanyDao を クラス変数bizadminUIUserCompanyDaoへ設定します。
	 */
	public void setBizadminUIUserCompanyDao(
			UIUserCompanyDao bizadminUIUserCompanyDao) {
		this.bizadminUIUserCompanyDao = bizadminUIUserCompanyDao;
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
	 * @return クラス変数bizadminUIUserGyotaiDao を戻します。
	 */
	public UIUserGyotaiDao getBizadminUIUserGyotaiDao() {
		return bizadminUIUserGyotaiDao;
	}
	/**
	 * @param bizadminUIUserGyotaiDao を クラス変数bizadminUIUserGyotaiDaoへ設定します。
	 */
	public void setBizadminUIUserGyotaiDao(UIUserGyotaiDao bizadminUIUserGyotaiDao) {
		this.bizadminUIUserGyotaiDao = bizadminUIUserGyotaiDao;
	}
	/**
	 * @return クラス変数bizadminUIUserMiseDao を戻します。
	 */
	public UIUserMiseDao getBizadminUIUserMiseDao() {
		return bizadminUIUserMiseDao;
	}
	/**
	 * @param bizadminUIUserMiseDao を クラス変数bizadminUIUserMiseDaoへ設定します。
	 */
	public void setBizadminUIUserMiseDao(UIUserMiseDao bizadminUIUserMiseDao) {
		this.bizadminUIUserMiseDao = bizadminUIUserMiseDao;
	}
	/**
	 * @return クラス変数bizadminUIUserOnerDao を戻します。
	 */
	public UIUserOnerDao getBizadminUIUserOnerDao() {
		return bizadminUIUserOnerDao;
	}
	/**
	 * @param bizadminUIUserOnerDao を クラス変数bizadminUIUserOnerDaoへ設定します。
	 */
	public void setBizadminUIUserOnerDao(UIUserOnerDao bizadminUIUserOnerDao) {
		this.bizadminUIUserOnerDao = bizadminUIUserOnerDao;
	}
}
