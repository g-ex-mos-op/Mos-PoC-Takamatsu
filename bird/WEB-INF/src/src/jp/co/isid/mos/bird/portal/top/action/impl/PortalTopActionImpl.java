/*
 * 作成日: 2006/2/1
 */
package jp.co.isid.mos.bird.portal.top.action.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.common.code.UserShozokuKbn;
import jp.co.isid.mos.bird.common.dao.MstUserShozokuDao;
import jp.co.isid.mos.bird.common.dto.OutLinkDto;
import jp.co.isid.mos.bird.common.entity.CtlPasswordPolicy;
import jp.co.isid.mos.bird.common.entity.MstUserShozoku;
import jp.co.isid.mos.bird.common.logic.GetOutLinkLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.portal.login.dto.MatrixDto;
import jp.co.isid.mos.bird.portal.top.action.PortalTopAction;
import jp.co.isid.mos.bird.portal.top.action.inside.impl.SokuhoInsideactionImpl;
import jp.co.isid.mos.bird.portal.top.dto.PortalTopDto;
import jp.co.isid.mos.bird.portal.top.entity.UIOsirase;
import jp.co.isid.mos.bird.portal.top.logic.RegMyMenuLogic;
import jp.co.isid.mos.bird.portal.top.util.PortalTopUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ポータルトップアクションクラス
 * @author itamoto
 */
public class PortalTopActionImpl implements PortalTopAction {

    /* アクションID */
    public static String initialize_ACTION_ID    = "BPO001A01";
    public static String startWhatsNew_ACTION_ID  = PortalTopUtil.SCREEN_ID+"A02";
    public static String addMyMenu_ACTION_ID    = PortalTopUtil.SCREEN_ID+"A03";
    public static String deleteMyMenu_ACTION_ID    = PortalTopUtil.SCREEN_ID+"A04";
    /**
     * DTO【外部リンク情報保持】
     */
    private OutLinkDto outLinkDto;
    /* DTO */
	private MatrixDto matrixDto;
	
    /* portalTopDto */
    private PortalTopDto portalTopDto;
    /* GetOutLinkLogic */
    private GetOutLinkLogic portalTopGetOutLinkLogic;
    /* DAO */
    private MstUserShozokuDao mstUserShozokuDao;
    
    private LinkActionImpl portalTopLinkAction;
    /**
     * 売上速報アクション
     */
    private SokuhoInsideactionImpl portalTopSokuhoAction;
    /**
     * マイメニュー登録ロジック
     */
    private RegMyMenuLogic portalTopRegMyMenuLogic;
    /* 登録日（お知らせ詳細画面パラメータ） */
    private String regDate;
    /* シーケンス（お知らせ詳細画面パラメータ）*/
    private String seq;
    
    HashMap birdPasswordPolicy;

    /**
	 * @return birdPasswordPolicy を戻します。
	 */
	public HashMap getBirdPasswordPolicy() {
		return birdPasswordPolicy;
	}
	/**
	 * @param birdPasswordPolicy を クラス変数birdPasswordPolicyへ設定します。
	 */
	public void setBirdPasswordPolicy(HashMap birdPasswordPolicy) {
		this.birdPasswordPolicy = birdPasswordPolicy;
	}
	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {
		portalTopDto.setPswAlert(false);
	    if (getMatrixDto().isPswdUpdtChekFlg()) {
	    	getMatrixDto().setPswdUpdtChekFlg(false);
	    	
	    	getPortalTopDto().clear();
	    	
	    	portalTopDto.setBirdUserInfo(getBirdUserInfo());
	    	portalTopDto.setBirdDateInfo(getBirdDateInfo());

            portalTopDto.setInitFlag(true);
        	String psUpDt = getBirdUserInfo().getMstUser().getPswdUpdtDt();
        	//パスワード管理ポリシー情報取得
            String shozokuKbn = UserShozokuKbn.HONBU;
            List listUserShozoku = getMstUserShozokuDao().getMstUserShozoku(getBirdUserInfo().getUserID());
            if (listUserShozoku != null && !listUserShozoku.isEmpty()) {
                //取得できた場合、1件目の所属区分でチェックタイプを取得
//                pswdCheckType = convShozokuKbnToCheckType(((MstUserShozoku) listUserShozoku.get(0)).getShozokuKbn());
                shozokuKbn = ((MstUserShozoku) listUserShozoku.get(0)).getShozokuKbn();
            }
    		CtlPasswordPolicy policy = (CtlPasswordPolicy)getBirdPasswordPolicy().get(shozokuKbn);
    	    if(policy.getWarningTerm() >0) {
	    		String valiDate = psUpDt;
                String warnDate;
                int availableTerm = 0;
	        	try {
	        		//パスワード有効期限日
	        		valiDate = DateManager.getNextDate(psUpDt, policy.getPswdAvailableTerm());
                    warnDate = DateManager.getPrevDate(valiDate, policy.getWarningTerm() + 1);
                    
                    
                    while (valiDate.compareTo(DateManager.getNextDate(getBirdDateInfo().getSysDate(), availableTerm)) > 0) {
                        availableTerm++;
                    }
	        	}
	        	catch(Exception e) {
	        		throw new FtlSystemException("ポータルトップアクション", "パスワード有効期限アラート機能処理", e);
	        	}
	        	if(getBirdDateInfo().getSysDate().compareTo(warnDate)>=0) {
                    DateFormatter dateFromatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
	        		//パスワード有効期限切日から警告期間内になったらアラートを表示する。
	        		portalTopDto.setPswAlert(true);
                    portalTopDto.setPswdAlertMsg("あと" + (availableTerm + 1) + "日でパスワードが切れます。有効期間は" + dateFromatter.format(valiDate, true) + "までです。");
	        	}
    	    }
    	    getPortalTopSokuhoAction().initialize();
        }
        return null;
    }
    /**
     * 外部リンク情報取得ロジック取得
	 * @return portalTopGetOutLinkLogic を戻します。
	 */
	public GetOutLinkLogic getPortalTopGetOutLinkLogic() {
		return portalTopGetOutLinkLogic;
	}
	/**
	 * 外部リンク情報取得ロジック設定
	 * @param portalTopGetOutLinkLogic を クラス変数portalTopGetOutLinkLogicへ設定します。
	 */
	public void setPortalTopGetOutLinkLogic(GetOutLinkLogic portalTopGetOutLinkLogic) {
		this.portalTopGetOutLinkLogic = portalTopGetOutLinkLogic;
	}
    
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.portal.top.action.PortalTopAction#addMyMenu()
	 */
	public String addMyMenu() {
		//１．
		//getPortalTopDto().setTargetMyMenuViewId(getInitViewId());
		getBirdUserInfo().setInfo(getPortalTopRegMyMenuLogic().add(getBirdUserInfo(), getPortalTopDto())
				, BirdUserInfo.MY_MENU_LIST);
		return null;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.portal.top.action.PortalTopAction#deleteMyMenu()
	 */
	public String deleteMyMenu() {
		//１．
		getPortalTopDto().setTargetMyMenuViewId(getInitViewId());
		getBirdUserInfo().setInfo(getPortalTopRegMyMenuLogic().delete(getBirdUserInfo(), getPortalTopDto())
				, BirdUserInfo.MY_MENU_LIST);
		getPortalTopLinkAction().setLinkType("mymenu");
		return null;
	}
    /**
     * お知らせ詳細起動処理
     * @return 画面遷移情報
     */
    public String startWhatsNew() {
        // お知らせ詳細情報をDtoにセット
        for (Iterator i = portalTopDto.getListOsirase().iterator(); i.hasNext();) {
            UIOsirase wnew = (UIOsirase) i.next();
            if (wnew.getRegDate().equals(regDate) && wnew.getSeq().equals(seq)) {
                portalTopDto.setPubDateFrom(wnew.getPubDateFrom());
                portalTopDto.setPubOrgName(wnew.getPubOrgName());
                portalTopDto.setTitle(wnew.getTitle());
                portalTopDto.setSimpleMsg(wnew.getSimpleMsg());
                portalTopDto.setMessage(wnew.getMessage());
                break;
            }
        }
        return null;
    }

    /**
     * ポータルページ用Dto設定処理
     * @return portalTopDto
     */
    public PortalTopDto getPortalTopDto() {
        return portalTopDto;
    }
    /**
     * ポータルページ用Dto設定処理
     * @param portalTopDto
     */
    public void setPortalTopDto(PortalTopDto portalTopDto) {
        this.portalTopDto = portalTopDto;
    }

	/**
	 * @return portalTopRegMyMenuLogic を戻します。
	 */
	public RegMyMenuLogic getPortalTopRegMyMenuLogic() {
		return portalTopRegMyMenuLogic;
	}


	/**
	 * @param portalTopRegMyMenuLogic を クラス変数portalTopRegMyMenuLogicへ設定します。
	 */
	public void setPortalTopRegMyMenuLogic(RegMyMenuLogic portalTopRegMyMenuLogic) {
		this.portalTopRegMyMenuLogic = portalTopRegMyMenuLogic;
	}
	/**
	 * @return showViewId を戻します。
	 */
	private String getInitViewId() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");

        String showViewId = "BPO001V01";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {	
            String s = (String) e.nextElement();
            if (s.indexOf("initViewId") != -1) {
                if (!CommonUtil.isNull(showViewId)) {
                	showViewId = request.getParameter(s);
                }
                break;
            }
        }
		return showViewId;
	}
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
	/**
	 * @return matrixDto を戻します。
	 */
	public MatrixDto getMatrixDto() {
		return matrixDto;
	}
	/**
	 * @param matrixDto を クラス変数matrixDtoへ設定します。
	 */
	public void setMatrixDto(MatrixDto matrixDto) {
		this.matrixDto = matrixDto;
	}
	/**
	 * @return outLinkDto を戻します。
	 */
	public OutLinkDto getOutLinkDto() {
		return outLinkDto;
	}
	/**
	 * @param outLinkDto を クラス変数outLinkDtoへ設定します。
	 */
	public void setOutLinkDto(OutLinkDto outLinkDto) {
		this.outLinkDto = outLinkDto;
	}
	/**
	 * @return regDate を戻します。
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate を クラス変数regDateへ設定します。
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return seq を戻します。
	 */
	public String getSeq() {
		return seq;
	}
	/**
	 * @param seq を クラス変数seqへ設定します。
	 */
	public void setSeq(String seq) {
		this.seq = seq;
	}
    public MstUserShozokuDao getMstUserShozokuDao() {
        return mstUserShozokuDao;
    }
    public void setMstUserShozokuDao(MstUserShozokuDao mstUserShozokuDao) {
        this.mstUserShozokuDao = mstUserShozokuDao;
    }
	public SokuhoInsideactionImpl getPortalTopSokuhoAction() {
		return portalTopSokuhoAction;
	}
	public void setPortalTopSokuhoAction(SokuhoInsideactionImpl portalTopSokuhoAction) {
		this.portalTopSokuhoAction = portalTopSokuhoAction;
	}
	public LinkActionImpl getPortalTopLinkAction() {
		return portalTopLinkAction;
	}
	public void setPortalTopLinkAction(LinkActionImpl portalTopLinkAction) {
		this.portalTopLinkAction = portalTopLinkAction;
	}
}

