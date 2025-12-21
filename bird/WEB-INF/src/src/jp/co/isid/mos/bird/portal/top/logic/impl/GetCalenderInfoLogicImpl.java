/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.portal.top.dao.UICalenderDayInfoDao;
import jp.co.isid.mos.bird.portal.top.dao.UICalenderMonthInfoDao;
import jp.co.isid.mos.bird.portal.top.dao.UIShozokuCompanyDao;
import jp.co.isid.mos.bird.portal.top.dto.CalenderInfoDto;
import jp.co.isid.mos.bird.portal.top.entity.UICalenderMonthInfo;
import jp.co.isid.mos.bird.portal.top.entity.UIShozokuCompany;
import jp.co.isid.mos.bird.portal.top.logic.GetCalenderInfoLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

/**
 * カレンダー情報取得ロジック
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public class GetCalenderInfoLogicImpl implements GetCalenderInfoLogic {
    /** ロジックID */
    public static final String LOGIC_ID = PortalTopUtil.SCREEN_ID+"L07";
    /* 閲覧対象会社情報 */
    private UIShozokuCompanyDao portalTopUIShozokuCompanyDao;
	/** DAO【日次カレンダー情報取得】*/
	private UICalenderDayInfoDao portalTopUICalenderDayInfoDao;
	/** DAO【月次カレンダー情報取得】*/
	private UICalenderMonthInfoDao portalTopUICalenderMonthInfoDao;
	/**
	 * カレンダー情報取得処理
	 * 
	 * @param birdUserInfo ログインユーザー情報
	 * @param targetMonth 対象年月
	 * @return ロジック内でインスタンス化したDTO【カレンダー情報】を戻します。
	 * @see jp.co.isid.mos.bird.portal.top.logic.GetCalenderInfoLogic#execute(java.lang.String)
	 */
	public CalenderInfoDto execute(BirdUserInfo birdUserInfo, String targetMonth) {
		//１．DTO【カレンダー情報】をインスタンス化します。
		CalenderInfoDto infoDto = new CalenderInfoDto();
		infoDto.setTargetMonth(targetMonth);
		//２．DAO【日次カレンダー情報取得】検索を実行し、List[[日次データ]]を取得します。
		//３．処理１のList[[日次データ]]をDTO【カレンダー情報】.List[[日次データ]]へ格納します。
		infoDto.setListDayInfo(getPortalTopUICalenderDayInfoDao().select(getCompany(birdUserInfo), targetMonth));
		//４．DAO【月次カレンダー情報取得】検索を実行し、List[[月次データ]]を取得します。
		String targetLastYM = "";
		try {
			targetLastYM = DateManager.getPrevMonth(targetMonth, 12);
		}
		catch (Exception e) {
			throw new FtlSystemException("カレンダー情報取得ロジック","月次カレンダー情報取得時のパラメータ前年月取得処理",e);
		}
		List listMonthInfo = getPortalTopUICalenderMonthInfoDao().select(targetMonth, targetLastYM);
		//４－１．処理２の月次データが２件無い場合、下記の処理を行います。
		if(listMonthInfo.size()<2) {
			String eigyoYm = "";
			if(0<listMonthInfo.size()) {
				eigyoYm = ((UICalenderMonthInfo)listMonthInfo.get(0)).getEigyoYM();
			}
			if(0==listMonthInfo.size() || eigyoYm.equals(targetMonth)==false) {
				UICalenderMonthInfo eThis = new UICalenderMonthInfo();
				eThis.setEigyoYM(targetMonth);
				listMonthInfo.add(0, eThis);
			}
			if(0==listMonthInfo.size() || eigyoYm.equals(targetLastYM)==false) {
				UICalenderMonthInfo eLast = new UICalenderMonthInfo();
				eLast.setEigyoYM(targetLastYM);
				listMonthInfo.add(1, eLast);
			}
		}
		//５．処理２のList[[月次データ]]をDTO【カレンダー情報】.List[[月次データ]]へ格納します。
		infoDto.setListMonthInfo(listMonthInfo);
		
		//６．DTO【カレンダー情報】をリターンします。
		return infoDto;
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
    private String getCompany(BirdUserInfo userInfo) {
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
        if(listVisisitorCompany.size()==0) {
        	return "";
        }
        
        return (String)listVisisitorCompany.get(0);
    }
    /**
	 * @return portalTopUICalenderDayInfoDao を戻します。
	 */
	public UICalenderDayInfoDao getPortalTopUICalenderDayInfoDao() {
		return portalTopUICalenderDayInfoDao;
	}
	/**
	 * @param portalTopUICalenderDayInfoDao を クラス変数portalTopUICalenderDayInfoDaoへ設定します。
	 */
	public void setPortalTopUICalenderDayInfoDao(
			UICalenderDayInfoDao portalTopUICalenderDayInfoDao) {
		this.portalTopUICalenderDayInfoDao = portalTopUICalenderDayInfoDao;
	}
	/**
	 * @return portalTopUICalenderMonthInfoDao を戻します。
	 */
	public UICalenderMonthInfoDao getPortalTopUICalenderMonthInfoDao() {
		return portalTopUICalenderMonthInfoDao;
	}
	/**
	 * @param portalTopUICalenderMonthInfoDao を クラス変数portalTopUICalenderMonthInfoDaoへ設定します。
	 */
	public void setPortalTopUICalenderMonthInfoDao(
			UICalenderMonthInfoDao portalTopUICalenderMonthInfoDao) {
		this.portalTopUICalenderMonthInfoDao = portalTopUICalenderMonthInfoDao;
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
