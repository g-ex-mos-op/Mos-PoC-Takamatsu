/*
 * 作成日: 2006/02/13
 * 修正日: 2007/02/13 オーナーユーザーで会社変更時にBM03USCPが更新されない問題を修正 
 * 修正日: 2008/12/05 e-mossles廃止に伴いBR08UCIF関連の処理を削除
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserCompanyDao;
import jp.co.isid.mos.bird.bizadmin.common.dao.UIUserOnerDao;
import jp.co.isid.mos.bird.bizadmin.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.sysadmin.userregist.dao.CodBumonDao;
import jp.co.isid.mos.bird.sysadmin.userregist.dao.CodKeiyakuTypeDao;
import jp.co.isid.mos.bird.sysadmin.userregist.dto.UserRegistDto;
import jp.co.isid.mos.bird.sysadmin.userregist.logic.SettingDataLogic;

/**
 * @author 慮
 * @modifier xkinu 2013/01/24 海外売上集信対応　
 *
 */
public class SettingDataLogicImpl implements SettingDataLogic {
	
    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BSA006L01";

    /** Dao【契約プルダウンの値取得】 */
    private CodKeiyakuTypeDao userRegistCodKeiyakuTypeDao;
	private UIUserCompanyDao bizadminUIUserCompanyDao;
	private CodCompanyDao bizadminCodCompanyDao;
	private UIUserOnerDao bizadminUIUserOnerDao;
    /** LOGIC【部門情報】 */
    private CodBumonDao userRegistCodBumonDao;
	
	/**
	 * 実行処理
	 * 
	 * 登録データ保持の入れ物を作成しDTOへ設定します。
	 */
	public void execute(UserRegistDto sessionDto){
		UIUser userData = new UIUser();
		userData.setLimitKbn("");
		userData.setStopFlg("0");
		userData.setSekyuFlg("1");
		//ユーザー情報
		sessionDto.setUIUser(userData);

        //本部
		if(UserType.isHonbu(sessionDto.getUserType())){
				executeHonbu(sessionDto);
		}
		//オーナー
		else if(UserType.isOner(sessionDto.getUserType())){
			executeOner(sessionDto);
		}
		else if(UserType.isTenpo(sessionDto.getUserType())) {
			executeMise(sessionDto);
		}
	}
	/**
	 * 本部用
     * @modifier xkinu 2013/01/24 海外売上集信対応 海外の会社は含まないよう改修
	 * @param sessionDto
	 */
	private void executeHonbu(UserRegistDto sessionDto) {
		//ユーザータイプ設定
		sessionDto.getUIUser().setUsertypeCd(UserType.HONBU);
		/*ユーザ所属会社 */
        UIUserCompany defaultUserCompany = new UIUserCompany();
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
		List userCompanyList = bizadminUIUserCompanyDao.selectAllCompany("", false, isForeignIn);
        if(userCompanyList.size()>0){
        	UIUserCompany defaultComp = (UIUserCompany)userCompanyList.get(0);
        	defaultUserCompany.setRCompanyCd(defaultComp.getRCompanyCd());
        	defaultUserCompany.setRCompanyName(defaultComp.getRCompanyName());
        	defaultUserCompany.setCompanyCd(defaultComp.getCompanyCd());
        	defaultUserCompany.setCompanyName(defaultComp.getCompanyName());
            sessionDto.setUserCompany(defaultUserCompany);
        }
        else {
			throw new NotExistException("所属会社");
		}
        
		/* 	会社プルダウン */
		sessionDto.setUserCompanyList(userCompanyList);
		/* 	業態 */
		sessionDto.setUserGyotaiList(new ArrayList(0));
        /* 全管理会社情報 */
    	sessionDto.setCompanyList(bizadminCodCompanyDao.select(isForeignIn));
		//部門
		List listBumon = userRegistCodBumonDao.select(defaultUserCompany.getRCompanyCd());
		sessionDto.setBumonList(listBumon);
		//ユーザー対応オーナー
		UIUserOner eOner = new UIUserOner();
		eOner.setCompanyCd(defaultUserCompany.getCompanyCd());
		eOner.setCompanyName(defaultUserCompany.getCompanyName());
        sessionDto.setUserOner(eOner);
	}
	/**
	 * オーナーユーザー用
     * @modifier xkinu 2013/01/24 海外売上集信対応 海外の会社は含まないよう改修
	 * @param sessionDto
	 */
	private void executeOner(UserRegistDto sessionDto) {
        // 契約タイププルダウンをDTOにセットする（List型）。
       	sessionDto.setKeiyakuList(getUserRegistCodKeiyakuTypeDao().getKeiyaku());
		//ユーザータイプ設定
		sessionDto.getUIUser().setUsertypeCd(UserType.ONER);
		/*ユーザ対応オーナー*/
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
		List onerList = getBizadminUIUserOnerDao().selectAllComp(sessionDto.getUIUser().getUserId(), null, isForeignIn);
		sessionDto.setUserOnerList(onerList);
		/* 	業態setting */
		sessionDto.setUserGyotaiList(new ArrayList(0));
	}
	/**
	 * 店舗ユーザー用
	 * @param sessionDto
	 */
	private void executeMise(UserRegistDto sessionDto) {
		boolean isForeignIn=false;//2013/01/24 追加(海外の会社は含まない)
		//ユーザータイプ設定
		sessionDto.getUIUser().setUsertypeCd(UserType.TENPO);
		/*ユーザ所属会社 */
        UIUserCompany defaultUserCompany = new UIUserCompany();
		List companyList = bizadminCodCompanyDao.select(isForeignIn);
        if(companyList.size()>0){
        	CodCompany defaultComp = (CodCompany)companyList.get(0);
        	defaultUserCompany.setRCompanyCd(defaultComp.getCompanyCd());
        	defaultUserCompany.setRCompanyName(defaultComp.getCompanyName());
            sessionDto.setUserCompany(defaultUserCompany);
        }
        else {
			throw new NotExistException("所属会社");
		}
       sessionDto.setCompanyList(companyList);
		/*ユーザ対応店舗*/
		UIUserMise mise = new UIUserMise();
		mise.setCompanyCd(defaultUserCompany.getRCompanyCd());
		mise.setCompanyName(defaultUserCompany.getRCompanyName());
		/*ユーザ対応店舗*/
		sessionDto.setUserMise(mise);
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
	/**
	 * @return クラス変数bizadminCodBumonDao を戻します。
	 */
	public CodBumonDao getUserRegistCodBumonDao() {
		return userRegistCodBumonDao;
	}
	/**
	 * @param userRegistCodBumonDao を クラス変数bizadminCodBumonDaoへ設定します。
	 */
	public void setUserRegistCodBumonDao(CodBumonDao bizadminCodBumonDao) {
		this.userRegistCodBumonDao = bizadminCodBumonDao;
	}
	/**
	 * @return クラス変数userRegistCodKeiyakuTypeDao を戻します。
	 */
	public CodKeiyakuTypeDao getUserRegistCodKeiyakuTypeDao() {
		return userRegistCodKeiyakuTypeDao;
	}
	/**
	 * @param userRegistCodKeiyakuTypeDao を クラス変数userRegistCodKeiyakuTypeDaoへ設定します。
	 */
	public void setUserRegistCodKeiyakuTypeDao(
			CodKeiyakuTypeDao userRegistCodKeiyakuTypeDao) {
		this.userRegistCodKeiyakuTypeDao = userRegistCodKeiyakuTypeDao;
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
}

	


