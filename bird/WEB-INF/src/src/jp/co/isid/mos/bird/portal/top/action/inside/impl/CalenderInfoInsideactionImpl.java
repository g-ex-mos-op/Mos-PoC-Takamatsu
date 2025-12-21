/**
 * 
 */
package jp.co.isid.mos.bird.portal.top.action.inside.impl;

import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.portal.top.action.inside.CalenderInfoInsideaction;
import jp.co.isid.mos.bird.portal.top.dto.CalenderInfoDto;
import jp.co.isid.mos.bird.portal.top.logic.GetCalenderInfoLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * カレンダー情報アクション
 * 
 * 作成日:2009/04/06
 * @author xkinu
 *
 */
public class CalenderInfoInsideactionImpl implements CalenderInfoInsideaction {
    /* アクションID */
    public static String initialize_ACTION_ID =  PortalTopUtil.SCREEN_ID+"A61";
    public static String calenderInfo_ACTION_ID     = PortalTopUtil.SCREEN_ID+"A62";
    public static String nextMonth_ACTION_ID     = PortalTopUtil.SCREEN_ID+"A63";
    public static String lastMonth_ACTION_ID     = PortalTopUtil.SCREEN_ID+"A64";
	/**
	 * LOGIC【カレンダー情報取得ロジック】
	 */
	private GetCalenderInfoLogic portalTopGetCalenderInfoLogic;
	/* DTO【カレンダー情報】*/
	private CalenderInfoDto portalTopCalenderInfoDto;
	
	/**
     * 初期処理
     */
	public String initialize() {
		return null;
	}
	/**
	 * カレンダー情報処理
	 * 
	 * カレンダー情報画面を表示します。
	 * @return
	 */
	public String calenderInfo() {
		//１．変数．対象年月をインスタンス化し、営業日（アプリ日付）から年月の値を設定します。
		String targeMonth = getBirdDateInfo().getAppDate().substring(0,6);
		//２．LOGIC【カレンダー情報取得】を実行し、戻り値DTO【カレンダー情報】をクラス変数.DTO【カレンダー情報】へ代入します。
		setPortalTopCalenderInfoDto(
				getPortalTopGetCalenderInfoLogic().execute(getBirdUserInfo(), targeMonth));
		getPortalTopCalenderInfoDto().setTargetMonthFrom(isTargetMonthFrom());
		getPortalTopCalenderInfoDto().setTargetMonthTo(isTargetMonthTo());
		//カレンダー情報画面のVIEWIDをリターンします。
		return PortalTopUtil.VIEW_ID_CALENDERINFO;
	}
	/**
	 * 次月処理
	 * 
	 * 表示中の翌月のカレンダー情報画面を表示します。
	 * @return
	 */
	public String nextMonth(){
		String targeMonth = getPortalTopCalenderInfoDto().getTargetMonth();
		try {
			targeMonth = DateManager.getNextMonth(targeMonth,1);
			
		}catch (Exception e) {
			throw new FtlSystemException("カレンダー情報：次月処理","",e);
		}
		setPortalTopCalenderInfoDto(getPortalTopGetCalenderInfoLogic().execute(getBirdUserInfo(), targeMonth));
		getPortalTopCalenderInfoDto().setTargetMonthFrom(isTargetMonthFrom());
		getPortalTopCalenderInfoDto().setTargetMonthTo(isTargetMonthTo());
		return null;
	}
	/**
	 * 前月処理
	 * 
	 * 表示中の前月のカレンダー情報画面を表示します。
	 * @return
	 */
	public String lastMonth(){
		String targeMonth = getPortalTopCalenderInfoDto().getTargetMonth();
		try {
			targeMonth = DateManager.getPrevMonth(targeMonth,1);
		}catch (Exception e) {
			throw new FtlSystemException("カレンダー情報：前月処理","",e);
		}
		setPortalTopCalenderInfoDto(getPortalTopGetCalenderInfoLogic().execute(getBirdUserInfo(), targeMonth));
		getPortalTopCalenderInfoDto().setTargetMonthFrom(isTargetMonthFrom());
		getPortalTopCalenderInfoDto().setTargetMonthTo(isTargetMonthTo());
		return null;
	}
	/**
	 * 対象年月日が照会範囲の開始年月か否かの判断
	 * @return targetMonthFrom を戻します。
	 */
	private boolean isTargetMonthFrom() {
		//１．変数．対象年月をインスタンス化し、営業日（アプリ日付）から年月の値を設定します。
		String appMonth = getBirdDateInfo().getAppDate().substring(0,6);
		String fromYM = "";
		try {
			fromYM = DateManager.getPrevMonth(appMonth, 35);
		}
		catch (Exception e) {
			throw new FtlSystemException("対象年月照会範囲取得"
					,"年月["+appMonth+"]から35ヶ月前の年月値が取れませんでした。"
					,e);
		}
		return getPortalTopCalenderInfoDto().getTargetMonth().compareTo(fromYM) <= 0;
	}

	/**
	 * 対象年月照会範囲の開始年月か否かの判断
	 * @return targetMonthTo を戻します。
	 */
	private boolean isTargetMonthTo() {
		//１．変数．対象年月をインスタンス化し、営業日（アプリ日付）から年月の値を設定します。
		String appMonth = getBirdDateInfo().getAppDate().substring(0,6);
		String toYM = "";
		String currentYear = DateManager.getCurrentYear(appMonth+"01");
		try {
			toYM = DateManager.getNextYear(currentYear,1)+"03";
		}
		catch (Exception e) {
			throw new FtlSystemException("対象年月照会範囲取得"
					,"年["+currentYear+"]の翌年の値が取れませんでした。"
					,e);
		}
		return getPortalTopCalenderInfoDto().getTargetMonth().compareTo(toYM) >= 0;
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
	 * @return portalTopGetCalenderInfoLogic を戻します。
	 */
	public GetCalenderInfoLogic getPortalTopGetCalenderInfoLogic() {
		return portalTopGetCalenderInfoLogic;
	}
	/**
	 * @param portalTopGetCalenderInfoLogic を クラス変数portalTopGetCalenderInfoLogicへ設定します。
	 */
	public void setPortalTopGetCalenderInfoLogic(
			GetCalenderInfoLogic portalTopGetCalenderInfoLogic) {
		this.portalTopGetCalenderInfoLogic = portalTopGetCalenderInfoLogic;
	}
	/**
	 * @return portalTopCalenderInfoDto を戻します。
	 */
	public CalenderInfoDto getPortalTopCalenderInfoDto() {
		return portalTopCalenderInfoDto;
	}
	/**
	 * @param portalTopCalenderInfoDto を クラス変数portalTopCalenderInfoDtoへ設定します。
	 */
	public void setPortalTopCalenderInfoDto(CalenderInfoDto portalTopCalenderInfoDto) {
		this.portalTopCalenderInfoDto = portalTopCalenderInfoDto;
	}

}
