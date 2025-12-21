/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.common.action.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.log.Logger;
import org.seasar.jsf.util.MessageUtil;

import jp.co.isid.mos.bird.common.action.OutLinkAction;
import jp.co.isid.mos.bird.common.dto.OutLinkDto;
import jp.co.isid.mos.bird.common.entity.UIChannelParam;
import jp.co.isid.mos.bird.common.entity.UIOutLink;
import jp.co.isid.mos.bird.common.logic.GetChannelParamLogic;
import jp.co.isid.mos.bird.common.logic.GetOutLinkLogic;
import jp.co.isid.mos.bird.common.util.BlowfishUtil;
import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NoTargetException;
import jp.co.isid.mos.bird.framework.logic.RegOutputLogLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * ポータルトップ外部リンクアクション
 *
 * 作成日:2009/01/09
 * @author xkinu
 *
 */
public class OutLinkActionImpl extends MenuActionImpl implements OutLinkAction {
	/**
	 * CommonCodeDtoのsetParamのパラメータキー:PARAM
	 */
	public static final String CommonCodeDto_PKEY_PARAM = "PARAM";

	/* モスチャンネルのリンクID */
	private static final String LINK_ID_CHANNEL = "0025";
	/* ミニサイトのリンクID */
	private static final String LINK_ID_MINISITE = "0030";
    /* MenuActionImpl */
    private MenuActionImpl menuAction;
    /* outLinkDto */
    private OutLinkDto commonOutLinkDto;
    /* GetOutLinkLogic */
    private RegOutputLogLogic regOutputLogLogic ;
    /* GetOutLinkLogic */
    private GetOutLinkLogic commonGetOutLinkLogic;
// add 2018/04/27 USI蔡 begin
    private GetChannelParamLogic commonGetChannelParamLogic ;
// add 2018/04/27 USI蔡 end

    private static Logger logger_ = Logger.getLogger(OutLinkActionImpl.class);

	String linkId = "";
	/**
     * 外部リンク起動処理
     * @return 画面遷移情報
     */
	public String linkUp() {
		try {
	        // 外部リンク情報をDtoにセット
			String targetLinkId = getCommonOutLinkDto().getLinkId();
			String dougaCd = getCommonOutLinkDto().getDougaCd();
			List listInfo  = getCommonGetOutLinkLogic().execute(getBirdUserInfo(), new String[]{"2", "3"}, targetLinkId,dougaCd);
			if(listInfo.size()==0) {
				throw new NoTargetException("このリンクの使用権限");
			}
			UIOutLink targetLink = (UIOutLink) listInfo.get(0);
			if(LINK_ID_CHANNEL.equals(targetLinkId)){
// modify 2018/04/27 USI蔡 begin
//				String param = BlowfishUtil.setEncrypt(getBirdUserInfo().getUserID(), targetLink.getDougaCd());
				List listParamInfo = getChannelParamLogic().execute(getBirdUserInfo().getUserID());
				if (listParamInfo == null || listParamInfo.size() == 0 ) {
					throw new NoTargetException("このリンクの使用権限");
				}
				String userSakuseiFlg = ((UIChannelParam)listParamInfo.get(0)).getUserSakuseiFlg();
				String onerCd = ((UIChannelParam)listParamInfo.get(0)).getOnerCd();
				String param = BlowfishUtil.setEncrypt(getBirdUserInfo().getUserID(), targetLink.getDougaCd(), userSakuseiFlg, onerCd);
// modify 2018/04/27 USI蔡 end
				targetLink.setParam(param);
				log(targetLink.getUrl()+param);
			} else if (LINK_ID_MINISITE.equals(targetLinkId)) {  //add 2017/9/4 暗号化したパラメータで、ミニサイトへアクセス
				String param = BlowfishUtil.setEncryptMiniSiteUrl(getBirdUserInfo().getUserID());
				targetLink.setParam(param);
				log(targetLink.getUrl()+param);
			}
			getCommonOutLinkDto().setTargetLinkInfo(targetLink);
	        // 現在日付取
			Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
			Calendar calendar = Calendar.getInstance();
			// メンテナンス開始時刻
			calendar.set(2000,01,01,0,0,0);
			// 開始時刻がnullの場合は9999/12/31 23:59:59:999-999-999のTimestampを設定します。
	        Timestamp startTimestamp = targetLink.getDisableStaTmp()==null?new Timestamp(calendar.getTimeInMillis()):targetLink.getDisableStaTmp();
			// メンテナンス終了時刻
			calendar.set(9999,12,31,23,59,59);
			// 終了時刻がnullの場合は9999/12/31 23:59:59:999-999-999のTimestampを設定します。
	        Timestamp endTimestamp = targetLink.getDisableEndTmp()==null?new Timestamp(calendar.getTimeInMillis()):targetLink.getDisableEndTmp();
			if((targetLink.getDisableStaTmp() == null && targetLink.getDisableEndTmp() == null)
					|| currentTimestamp.compareTo(startTimestamp) < 0
					|| currentTimestamp.compareTo(endTimestamp) > 0 ) {

				//メンテナンスで無い為、メンテナンスフラグにfalseを設定します。
				getCommonOutLinkDto().setMenteFlg(false);
				if("1".equals(targetLink.getLogFlg())) {
			        S2Container container = SingletonS2ContainerFactory.getContainer();
			        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
					//ログの出力を行います。
					getRegOutputLogLogic().execute(request, getBirdUserInfo(), targetLinkId);
				}
				if("0".equals(targetLink.getGaisysFlg())) {
					//１．プルダウンメニューが選択された時と同じ処理を行います。
					//１－１．画面IDを設定します。（マイメニュー登録に使用します）
					setSelectedViewId(targetLink.getViewId());
					//１－２．表示VIEWIDを設定します。（ヘルプに使用します。）
					setShowViewId(targetLink.getInitViewId());
					//１－３．メニュープルダウンエリアの表示中メニュー背景色の制御
					doSelectMainMenu(targetLink.getUrl());
                    //PulldownMenuDtoのクリアフラグセット
                    getPullDownMenuDto().setClearFlg(true);
					//内部の場合
			        CommonCodeDto commonCodeDto = getCommonCodeDto();
			        if (commonCodeDto == null) {
			            commonCodeDto = new CommonCodeDto();
			        }
			        commonCodeDto.clear();
			        commonCodeDto.setUseCommonDto(true);
			        commonCodeDto.setParam(CommonCodeDto_PKEY_PARAM, targetLink.getParam());

	                //メニュー情報を設定
	                getMenuAction().setSelectedMainMenu(targetLink.getUrl(), null, null);
					//内部の画面へ遷移します。
					return targetLink.getUrl();
				}
			}

	        //メニュー情報を設定
	        getMenuAction().setSelectedMainMenu(targetLink.getInitViewId(), null, null);

			return targetLink.getInitViewId();
		}
		catch (ApplicationException appExc) {
			getCommonOutLinkDto().setExceptionMessage(
					MessageUtil.getErrorMessage(appExc.getMessageId(),
							appExc.getMessageArgs()).getDetail());
			return "XXX000V01";
		}
    }
	/**
	 * BIRD間の遷移時使用情報保持DTO取得処理
	 *
	 * @return
	 */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

	/**
	 * @return linkId を戻します。
	 */
	public String getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId を クラス変数linkIdへ設定します。
	 */
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	/**
	 * @return commonGetOutLinkLogic を戻します。
	 */
	public GetOutLinkLogic getCommonGetOutLinkLogic() {
		return commonGetOutLinkLogic;
	}

	/**
	 * @param commonGetOutLinkLogic を クラス変数commonGetOutLinkLogicへ設定します。
	 */
	public void setCommonGetOutLinkLogic(GetOutLinkLogic commonGetOutLinkLogic) {
		this.commonGetOutLinkLogic = commonGetOutLinkLogic;
	}

	// add 2018/04/27 USI蔡 begin
	/**
	 * @return commonGetChannelParamLogic を戻します。
	 */
	public GetChannelParamLogic getChannelParamLogic() {
		return commonGetChannelParamLogic;
	}

	/**
	 * @param commonGetChannelParamLogic を クラス変数commonGetChannelParamLogicへ設定します。
	 */
	public void setChannelParamLogic(GetChannelParamLogic commonGetChannelParamLogic) {
		this.commonGetChannelParamLogic = commonGetChannelParamLogic;
	}
	// add 2018/04/27 USI蔡 end

	/**
	 * @return commonOutLinkDto を戻します。
	 */
	public OutLinkDto getCommonOutLinkDto() {
		return commonOutLinkDto;
	}

	/**
	 * @param commonOutLinkDto を クラス変数commonOutLinkDtoへ設定します。
	 */
	public void setCommonOutLinkDto(OutLinkDto commonOutLinkDto) {
		this.commonOutLinkDto = commonOutLinkDto;
	}
	/**
	 * @return regOutputLogLogic を戻します。
	 */
	public RegOutputLogLogic getRegOutputLogLogic() {
		return regOutputLogLogic;
	}
	/**
	 * @param regOutputLogLogic を クラス変数regOutputLogLogicへ設定します。
	 */
	public void setRegOutputLogLogic(RegOutputLogLogic regOutputLogLogic) {
		this.regOutputLogLogic = regOutputLogLogic;
	}
    public MenuActionImpl getMenuAction() {
        return menuAction;
    }
    public void setMenuAction(MenuActionImpl menuAction) {
        this.menuAction = menuAction;
    }

    private void log(String param) {
    	StringBuffer buf = new StringBuffer(100);

    	 S2Container container = null;
         container = SingletonS2ContainerFactory.getContainer();
         HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
         BirdUserInfo userInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");
         MstUser mstUser = (userInfo == null) ? null : userInfo.getMstUser();

         buf.append(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
         buf.append(" " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
         buf.append(" "+"########");
         buf.append(" "+"BPO001A52");
         buf.append(" "
                 + ((mstUser == null) ? "########"
                         : (mstUser.getUser_id() == null) ? "########"
                                 : mstUser.getUser_id()));
         HttpServletRequest hr = (HttpServletRequest) request;
         String sUserRemoteIP = hr.getHeader( "x-forwarded-for" );
         if(sUserRemoteIP == null || "".equals(sUserRemoteIP)){
         	buf.append(" " + request.getRemoteHost());
         }else{
         	buf.append(" " + sUserRemoteIP);
         }

         buf.append(" " + request.getHeader("user-agent"));
         buf.append(" " + param);
         logger_.info(buf);
    }
}

