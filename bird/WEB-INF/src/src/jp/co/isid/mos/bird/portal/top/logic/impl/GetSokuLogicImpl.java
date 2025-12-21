/*
 * 作成日: 2008/12/26
 */
package jp.co.isid.mos.bird.portal.top.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.portal.top.dao.UIShozokuCompanyDao;
import jp.co.isid.mos.bird.portal.top.dao.UISokuDao;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.entity.UIShozokuCompany;
import jp.co.isid.mos.bird.portal.top.logic.GetSokuLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.log.Logger;

/**
 * 売上速報情報取得処理ロジック
 * 
 * 作成日:2008/12/26
 * @author xkinu
 *
 */
public class GetSokuLogicImpl implements GetSokuLogic {

    /** ロジックID */
    public static final String LOGIC_ID = PortalTopUtil.SCREEN_ID+"L03";

    private static Logger logger_ = Logger.getLogger(GetSokuLogicImpl.class);

    /* 閲覧対象会社情報 */
    private UIShozokuCompanyDao portalTopUIShozokuCompanyDao;
    /* UIWhasNewDao */
    private UISokuDao portalTopUISokuDao;

    /**
     * 売上速報情報取得処理
     * 
     * 閲覧対象条件
     * その１．管理会社(BC05KCOM)に存在する企業コード
     * その２．属性区分が1('所属')の会社コード
     * 
     * 閲覧対象として、管理会社(BC05KCOM)に存在する企業コードで
     * 属性区分が1('所属')の会社コードの情報を検索します。
     * ＜注意＞
     * ただし、本部ユーザの場合、閲覧対象条件に該当する会社コードが無い場合がある為、
     * その場合は、管理会社(BC05KCOM)に存在する企業コードで優先順位 0('閲覧')・2(契約)の順で
     * 一番先頭の会社コード１つのみを閲覧対象として、売上速報情報の検索を行います。
     * 
     */
    public List execute(BirdUserInfo userInfo, BirdDateInfo birdDateInfo, PortalTopDto portalTopDto) {
        
        //１．閲覧対象会社コード配列を作成します。
        String[] companyList = getCompanyList(userInfo);
        if(companyList.length==0) {
        	return null;
        }
        logger_.debug("ユーザ所属会社 r_company_cd [" + companyList+ "]");
        String nendo = "";
        String nendoStartDate = "";
        try {
        	nendo = DateManager.getCurrentYear(birdDateInfo.getAppDate());
        	nendoStartDate = nendo+"0401";
        }
        catch (Exception e) {
        	throw new FtlSystemException("売上速報情報取得処理ロジックの年度取得処理");
        }
        String appDate = birdDateInfo.getAppDate();
        String appMonth = appDate.substring(0, 6);
        String userTypeCd = userInfo.getMstUser().getUserTypeCd();
        if(UserType.HONBU.equals(userTypeCd)) {
        	return getPortalTopUISokuDao().selectBS09(appDate, appMonth	, nendoStartDate, nendo, companyList);
        }
        else {
        	return getPortalTopUISokuDao().selectBT60(userTypeCd, userInfo.getUserID(), appDate, appMonth, companyList);
        }
    }
    /**
     * 閲覧対象会社コード配列取得処理
     * 
     * 閲覧対象条件
     * その１．管理会社(BC05KCOM)に存在する企業コード
     * その２．保有店ある企業コード
     * その３．ユーザタイプコード別の条件を満たしている企業コード
     * 
     * <<本部ユーザの場合の条件>>
     * 閲覧対象として、管理会社(BC05KCOM)に存在する企業コードで
     * 属性区分が1('所属')の会社コードの情報を検索します。
     * ＜!!!注意!!!＞
     * ただし、本部ユーザの場合、閲覧対象条件に該当する会社コードが無い場合がある為、
     * その場合は、管理会社(BC05KCOM)に存在する企業コードで優先順位 0('閲覧')・2(契約)の順で
     * 一番先頭の会社コード１つのみを閲覧対象として、売上速報情報の検索を行います。
     * 
     * <<オーナーユーザの場合の条件>>
     * 閲覧対象として、管理会社(BC05KCOM)に存在する企業コードの会社コード全てが閲覧対象になります。
     * 
     * <<店舗ユーザの場合の条件>>
     * 閲覧対象として、管理会社(BC05KCOM)に存在する企業コードの会社コード全てが閲覧対象になります。
     * 
     * @param listCompany
     * @return
     */
    private String[] getCompanyList(BirdUserInfo userInfo) {
    	List listCompany = getPortalTopUIShozokuCompanyDao().select(userInfo.getUserID());
    	List listVisisitorCompany = new ArrayList(0);
    	//所属の会社コードを設定します。
        for (int i=0; i<listCompany.size(); i++) {
        	UIShozokuCompany entity = (UIShozokuCompany)listCompany.get(i);
        	//本部ユーザの場合
        	if(UserType.HONBU.equals(userInfo.getMstUser().getUserTypeCd())) {
	        	if(i==0 || "1".equals(entity.getZokuseiKbn())) {
	        		listVisisitorCompany.add(entity.getCompanyCd());
	        		if("1".equals(entity.getZokuseiKbn())==false) {
	        			break;
	        		}
	        	}
        	}
        	//オーナーユーザ＆店舗ユーザの場合
        	else {
        		listVisisitorCompany.add(entity.getCompanyCd());
        	}
        }
        String[] companys = new String[listVisisitorCompany.size()];
        for(int i=0; i<listVisisitorCompany.size(); i++) {
        	companys[i] = (String)listVisisitorCompany.get(i);
        }
        return companys;
    }
	/**
	 * @return portalTopUISokuDao を戻します。
	 */
	public UISokuDao getPortalTopUISokuDao() {
		return portalTopUISokuDao;
	}

	/**
	 * @param portalTopUISokuDao を クラス変数portalTopUISokuDaoへ設定します。
	 */
	public void setPortalTopUISokuDao(UISokuDao portalTopUISokuDao) {
		this.portalTopUISokuDao = portalTopUISokuDao;
	}
	/**
	 * @return portalTopUIShozokuCompanyDao を戻します。
	 */
	public UIShozokuCompanyDao getPortalTopUIShozokuCompanyDao() {
		return portalTopUIShozokuCompanyDao;
	}
	/**
	 * @param portalTopUIShozokuCompanyDao を クラス変数portalTopUIShozokuCompanyDaoへ設定します。
	 */
	public void setPortalTopUIShozokuCompanyDao(
			UIShozokuCompanyDao portalTopUIShozokuCompanyDao) {
		this.portalTopUIShozokuCompanyDao = portalTopUIShozokuCompanyDao;
	}
}
