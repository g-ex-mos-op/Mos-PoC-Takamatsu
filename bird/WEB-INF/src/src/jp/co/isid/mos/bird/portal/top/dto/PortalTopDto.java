/*
 * 作成日: 2006/1/16
 */
package jp.co.isid.mos.bird.portal.top.dto;

import java.util.List;

import jp.co.isid.mos.bird.common.code.SearchRequestViewId;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.CtlBirdMenu;

/**
 * ポータルトップページ用Dto<br>
 * @author itamoto
 */
public class PortalTopDto {
    /**
     * DTO【メニュープルダウン】
     */
    private PullDownMenuDto pullDownMenuDto;
	/**
	 * ログインユーザ情報
	 */
	private BirdUserInfo birdUserInfo;
	/**
	 * 日付情報
	 */
	private BirdDateInfo birdDateInfo;

	private boolean pswAlert;
    private String pswdAlertMsg;

	/**
	 * モス閲覧者判断値
	 * true:閲覧者、false:非閲覧者
	 */
	private boolean mosVisitor= false;

	/* 外部リンク情報 */
	private List listOutLink;
    /* お知らせ情報 */
    private List listOsirase;

    /* 売上速報情報 */
    private List listSokuho;
    /* 初期処理フラグ */
    private boolean initFlag;

    /* お知らせ詳細 発信日 */
    private String pubDateFrom;
    /* お知らせ詳細 発信者 */
    private String pubOrgName;
    /* お知らせ詳細 タイトル */
    private String title;
    /* お知らせ詳細 メッセージ */
    private String simpleMsg;
    /* お知らせ詳細 詳細 */
    private String message;

    /** 通達更新日 */
    private String tutatuLastUpdateDt;

    private List listSchedule;
    /**
     * 対象マイメニューVIEWID
     */
    private String targetMyMenuViewId;

    private String pdfType;

    /**
     * 営業日(アプリ日付)の前月末日
     */
    private String appLastMonthDay;

    private int count;

    private String dougaCd;

    public void clear() {
    	setBirdUserInfo(null);
    	setInitFlag(true);
    	setListOutLink(null);
    	setListSokuho(null);
    	setListOsirase(null);
    	setListSchedule(null);
    	setTutatuLastUpdateDt(null);
    	setCount(-1);
    }

    /**
     * お知らせ情報の設定
     * @return listOsirase を戻します。
     */
    public List getListOsirase() {
        return listOsirase;
    }
    /**
     * お知らせ情報の設定
     * @param listOsirase listOsirase を設定。
     */
    public void setListOsirase(List whatsNewList) {
        this.listOsirase = whatsNewList;
    }

	/**
     * お知らせ情報サイズの設定
	 * @return whatsNewListSize を戻します。
	 */
	public int getListOsiraseSize() {
		return (listOsirase == null) ? 0 : listOsirase.size();
	}

	/**
     * 売上速報情報の設定
     * @return listSokuho を戻します。
     */
    public List getListSokuho() {
        return listSokuho;
    }
    /**
     * 売上速報情報の設定
     * @param listSokuho listSokuho を設定。
     */
    public void setListSokuho(List sokuList) {
        this.listSokuho = sokuList;
    }
    /**
     * 表示速報セグメント数取得処理
     */
    public int getSokuhoSegmentCnt() {
    	if(getListSokuho() ==null) {
    		return 0;
    	}
    	return getListSokuho().size();
    }
    /**
     * 初期処理フラグの設定
     * @return initFlag を戻します。
     */
    public boolean isInitFlag() {
        return initFlag;
    }
    /**
     * 初期処理フラグの設定
     * @param initFlag initFlag を設定。
     */
    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }

    /**
     * お知らせ詳細 メッセージの設定
     * @return message を戻します。
     */
    public String getMessage() {
        return message;
    }
    /**
     * お知らせ詳細 メッセージの設定
     * @param message message を設定。
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * お知らせ詳細 発信日の設定
     * @return pubDateFrom を戻します。
     */
    public String getPubDateFrom() {
        return pubDateFrom;
    }
    /**
     * お知らせ詳細 発信日の設定
     * @param pubDateFrom pubDateFrom を設定。
     */
    public void setPubDateFrom(String pubDate) {
        this.pubDateFrom = pubDate;
    }
    /**
     * お知らせ詳細 発信者の設定
     * @return pubOrgName を戻します。
     */
    public String getPubOrgName() {
        return pubOrgName;
    }
    /**
     * お知らせ詳細 発信者の設定
     * @param pubOrgName pubOrgName を設定。
     */
    public void setPubOrgName(String pubOrgName) {
        this.pubOrgName = pubOrgName;
    }
    /**
     * お知らせ詳細 メッセージの設定
     * @return simpleMsg を戻します。
     */
    public String getSimpleMsg() {
        return simpleMsg;
    }
    /**
     * お知らせ詳細 メッセージの設定
     * @param simpleMsg simpleMsg を設定。
     */
    public void setSimpleMsg(String simpleMsg) {
        this.simpleMsg = simpleMsg;
    }
    /**
     * お知らせ詳細 タイトルの設定
     * @return title を戻します。
     */
    public String getTitle() {
        return title;
    }
    /**
     * お知らせ詳細 タイトルの設定
     * @param title title を設定。
     */
    public void setTitle(String title) {
        this.title = title;
    }


	/**
	 * @return targetMyMenuViewId を戻します。
	 */
	public String getTargetMyMenuViewId() {
		return targetMyMenuViewId;
	}

	/**
	 * @param targetMyMenuViewId を クラス変数targetMyMenuViewIdへ設定します。
	 */
	public void setTargetMyMenuViewId(String targetMyMenuViewId) {
		this.targetMyMenuViewId = targetMyMenuViewId;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return pswAlert を戻します。
	 */
	public boolean isPswAlert() {
		return pswAlert;
	}

	/**
	 * @param pswAlert を クラス変数pswAlertへ設定します。
	 */
	public void setPswAlert(boolean pswAlert) {
		this.pswAlert = pswAlert;
	}
	/**
	 * @return tutatuLastUpdateDt を戻します。
	 */
	public String getTutatuLastUpdateDt() {
		return tutatuLastUpdateDt;
	}

	/**
	 * @param tutatuLastUpdateDt を クラス変数tutatuLastUpdateDtへ設定します。
	 */
	public void setTutatuLastUpdateDt(String tutatuLastUpdateDt) {
		this.tutatuLastUpdateDt = tutatuLastUpdateDt;
	}

	/**
	 * @return listSchedule を戻します。
	 */
	public List getListSchedule() {
		return listSchedule;
	}

	/**
	 * @param listSchedule を クラス変数listScheduleへ設定します。
	 */
	public void setListSchedule(List listSchedule) {
		this.listSchedule = listSchedule;
	}

	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return mosVisitor を戻します。
	 */
	public boolean isMosVisitor() {
		return mosVisitor;
	}

	/**
	 * @param mosVisitor を クラス変数mosVisitorへ設定します。
	 */
	public void setMosVisitor(boolean mosVisitor) {
		this.mosVisitor = mosVisitor;
	}
	/**
	 * @return pdfType を戻します。
	 */
	public String getPdfType() {
		return pdfType;
	}

	/**
	 * @param pdfType を クラス変数pdfTypeへ設定します。
	 */
	public void setPdfType(String pdfType) {
		this.pdfType = pdfType;
	}
	/**
	 * @return listOutLink を戻します。
	 */
	public List getListOutLink() {
		return listOutLink;
	}

	/**
	 * @param listOutLink を クラス変数listOutLinkへ設定します。
	 */
	public void setListOutLink(List listOutLink) {
		this.listOutLink = listOutLink;
	}

	/**
	 * @return appLastMonthDay を戻します。
	 */
	public String getAppLastMonthDay() {
		return appLastMonthDay;
	}

	/**
	 * @param appLastMonthDay を クラス変数appLastMonthDayへ設定します。
	 */
	public void setAppLastMonthDay(String appLastMonthDay) {
		this.appLastMonthDay = appLastMonthDay;
	}

    public String getPswdAlertMsg() {
        return pswdAlertMsg;
    }

    public void setPswdAlertMsg(String pswdAlertMsg) {
        this.pswdAlertMsg = pswdAlertMsg;
    }
    /**
     * 通達画面アクセス権限有無判断処理
     * @return
     */
    public boolean isActTutatuMemnu() {
        String viewId = SearchRequestViewId.TUTATSU.substring(0,6);
        List menu1= getPullDownMenuDto().getMenu1st();
        boolean isActMenu = isActMemnu(menu1, viewId);
        if (!isActMenu) {
            List menu2= getPullDownMenuDto().getMenu2nd();
            isActMenu = isActMemnu(menu2, viewId);
        }
        if (!isActMenu) {
            List registMenu1 = getPullDownMenuDto().getRegistMenu1st();
            isActMenu = isActMemnu(registMenu1, viewId);
        }
        if (!isActMenu) {
            List registMenu2 = getPullDownMenuDto().getRegistMenu2nd();
            isActMenu = isActMemnu(registMenu2, viewId);
        }
        return isActMenu;
    }
    /**
     * アクセス権限有画面か否か判断処理
     *
     * @param menu メインメニュー
     * @param selectViewId 選択されたメニュー
     */
    private boolean isActMemnu(List menu, String selectViewId){
        //選択されてたメインメニューのフラグをONに設定
        ofMenu:for(int i = 0; i < menu.size(); i++){
            List submenu= ((CtlBirdMenu)menu.get(i)).getSubMenuList();
            for(int j = 0; j < submenu.size(); j++){
            	CtlBirdMenu eSubMenu = ((CtlBirdMenu)submenu.get(j));
                String viewId = eSubMenu.getViewId();
                if(selectViewId.equals(viewId)){
                    return true;
                }
            }
        }
    	return false;
    }
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDougaCd() {
		return dougaCd;
	}

	public void setDougaCd(String dougaCd) {
		this.dougaCd = dougaCd;
	}

}
