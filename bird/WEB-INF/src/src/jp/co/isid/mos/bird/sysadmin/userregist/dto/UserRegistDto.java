/*
 * 作成日: 2006/2/16
 * 更新日: 2006/07/03 パスワード変更追加対応　K.INAZAWA
 * 更新日: 2006/07/12 本部ユーザー登録対応　　K.INAZAWA
 */
package jp.co.isid.mos.bird.sysadmin.userregist.dto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.sysadmin.userregist.entity.CodKeiyakuType;


/**
 * ユーザ登録用Dto<br>
 * @author itamoto
 */
public class UserRegistDto {
    
    /* VIEW_ID */
    public final String VIEW_ID                = "BSA006";
    public final String select_VIEW_ID         = "BSA006V01";
    public final String editOwner_VIEW_ID      = "BSA006V02";
    public final String confirmOwner_VIEW_ID   = "BSA006V03";
    public final String editStore_VIEW_ID      = "BSA006V04";
    public final String confirmStore_VIEW_ID   = "BSA006V05";
    public final String editHonbu_VIEW_ID      = "BSA006V06";
    public final String confirmHonbu_VIEW_ID   = "BSA006V07";
    public final String ownerSearch_VIEW_ID    = "BCO006V01";
    public final String businessSearch_VIEW_ID = "BCO007V01";
    public final String miseSearch_VIEW_ID     = "BCO008V01";

    public final String setupUserRoll_VIEW_ID  = "BSA011V03";
    
    private static List userTypeList = new ArrayList(0);
    /* 検索初期処理起動フラグ */
    private boolean initFlag;
    
    private String userType;
    /* ログインユーザー設定区分*/
    private String seteiKbn;

    private int userLevel=0;
    /* ユーザー初期パスワード 20060703ADD*/
    private String userPassWord;
    /* ユーザー初期パスワード確認用 20060703ADD*/
    private String userPassWordKakunin;
    /* 会社プルダウンリスト */
    private List userCompanyList;
    /* オーナー会社リスト（プルダウン用） */
    private List companyList;
    /* 部門リスト（プルダウン用） */
    private List bumonList;

    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    private UIUser uIUser = new UIUser();
    
    private UIUserCompany userCompany = new UIUserCompany();
    
    private List userGyotaiList;
    
    private List userOnerList;
    
    private UIUserMise userMise = null;
/*************************** 2007/01/31 add start xamaruyama ***************************/
    
    /* 契約タイププルダウン */
    private List keiyakuList;
    
    /* 契約タイププルダウンで選択されたもの */
    private String keiyakuChoice;
    
    /* オプション選択で選択されたもの（オプション機能） */
    private boolean optionFunctionFlg;
    
    /* オプション選択で選択されたもの（データ提供能） */
    private boolean optionDataFlg;
    
    /* ロールコード */
    private List roleCode;

    /**
     * ユーザー初期パスワードの設定
     * 作成日：20060703
     * @return  userPassWord
     * @author inazawa
     */
    public String getUserPassWord() {
        return userPassWord;
    }
    /**
     * ユーザー初期パスワードの設定
     * 作成日：20060703
     * @param  userPassWord
     * @author inazawa
     */
    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }
    /**
     * ユーザー初期パスワード(確認用)の設定
     * 作成日：20060703
     * @return  userPassWordKakunin
     * @author inazawa
     */
    public String getUserPassWordKakunin() {
        return userPassWordKakunin;
    }
    /**
     * ユーザー初期パスワード(確認用)の設定
     * 作成日：20060703
     * @param  userPassWordKakunin
     * @author inazawa
     */
    public void setUserPassWordKakunin(String userPassWordKakunin) {
        this.userPassWordKakunin = userPassWordKakunin;
    }
	
	/**
	 * システム日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * システム日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
    /**
     * 会社リスト設定処理
     * @return
     */
    public List getCompanyList() {
        return companyList;
    }
    /**
     * 会社リスト設定
     * @param companyList
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }
    /**
     * 部門リスト設定処理
     * @return
     */
    public List getBumonList() {
        return bumonList;
    }
    /**
     * 部門リスト設定処理
     * @param bumonList
     */
    public void setBumonList(List bumonList) {
        this.bumonList = bumonList;
    }

    /**
     * 初期設定処理
     * @return
     */
    public boolean isInitFlag() {
        return initFlag;
    }
    /**
     * 初期設定処理
     * @param initFlag
     */
    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }    
    /**
     * 情報のクリア
     */
    public void clear() {
    	uIUser = new UIUser();
    	userCompany = new UIUserCompany();
    	setUserGyotaiList(new ArrayList(0));
    	setUserOnerList(null);
    	setUserMise(null);
        setUserPassWord(null);
    	setStopFlg(false);
        setBumonList(null);
        setCompanyList(null);
        setInitFlag(false);
        setKeiyakuList(null);
        setKeiyakuChoice(null);
        setOptionFunctionFlg(false);
        setOptionDataFlg(false);
        setRoleCode(null);
    }
    
    public List getKeiyakuList() {
        return keiyakuList;
    }
    public void setKeiyakuList(List keiyaku) {
        this.keiyakuList = keiyaku;
    }
    public String getKeiyakuChoice() {
        return keiyakuChoice;
    }
    public void setKeiyakuChoice(String keiyakuChoice) {
        this.keiyakuChoice = keiyakuChoice;
    }
    public List getRoleCode() {
        return roleCode;
    }
    public void setRoleCode(List roleCode) {
        this.roleCode = roleCode;
    }
    public boolean getOptionDataFlg() {
        return optionDataFlg;
    }
    public void setOptionDataFlg(boolean optionDataFlg) {
        this.optionDataFlg = optionDataFlg;
    }
    public boolean getOptionFunctionFlg() {
        return optionFunctionFlg;
    }
    public void setOptionFunctionFlg(boolean optionFunctionFlg) {
        this.optionFunctionFlg = optionFunctionFlg;
    }
	/**
	 * @return クラス変数uIUser を戻します。
	 */
	public UIUser getUIUser() {
		return uIUser;
	}
	/**
	 * @param user を クラス変数uIUserへ設定します。
	 */
	public void setUIUser(UIUser user) {
		uIUser = user;
	}
	/**
	 * @return クラス変数seteiKbn を戻します。
	 */
	public String getSeteiKbn() {
		return seteiKbn;
	}
	/**
	 * @param seteiKbn を クラス変数seteiKbnへ設定します。
	 */
	public void setSeteiKbn(String seteiKbn) {
		this.seteiKbn = seteiKbn;
	}
	/**
	 * @return クラス変数userMise を戻します。
	 */
	public UIUserMise getUserMise() {
		return userMise;
	}
	/**
	 * @param userMise を クラス変数userMiseへ設定します。
	 */
	public void setUserMise(UIUserMise userMise) {
		this.userMise = userMise;
	}
	/**
	 * 本部用ユーザー対応オーナー情報取得処理
	 * 
	 * @return クラス変数userOner を戻します。
	 */
	public UIUserOner getUserOner() {
		return (UIUserOner)getUserOnerList().get(0);
	}
	/**
	 * 本部用ユーザー対応オーナー情報設定処理
	 * 
	 * @param userOner を クラス変数userOnerへ設定します。
	 */
	public void setUserOner(UIUserOner userOner) {
		getUserOnerList().set(0, userOner);
	}
	/**
	 * @return クラス変数userOnerList を戻します。
	 */
	public List getUserOnerList() {
		return userOnerList;
	}
	/**
	 * @param userOnerList を クラス変数userOnerListへ設定します。
	 */
	public void setUserOnerList(List userOnerList) {
		this.userOnerList = userOnerList;
	}
	/**
	 * @return クラス変数userGyotaiList を戻します。
	 */
	public List getUserGyotaiList() {
		return userGyotaiList;
	}
	/**
	 * @param userGyotaiList を クラス変数userGyotaiListへ設定します。
	 */
	public void setUserGyotaiList(List userGyotaiList) {
		this.userGyotaiList = userGyotaiList;
	}
	/**
	 * 本部用ユーザー管理会社情報取得処理
	 * 
	 * @return クラス変数userOner を戻します。
	 */
	public UIUserCompany getUserCompany() {
		return userCompany;
	}
	/**
	 * 本部用ユーザー管理会社情報設定処理
	 * 
	 * @param userOner を クラス変数userOnerへ設定します。
	 */
	public void setUserCompany(UIUserCompany userCompany) {
		this.userCompany = userCompany;
	}
	/**
	 * @return クラス変数userType を戻します。
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType を クラス変数userTypeへ設定します。
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * @return クラス変数userLevel を戻します。
	 */
	public int getUserLevel() {
		return userLevel;
	}
	/**
	 * @param userLevel を クラス変数userLevelへ設定します。
	 */
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	/** 制限フラグ文言 */
	public String getLabelLimitKbn(){
		return "1".equals(getUIUser().getLimitKbn())?"あり":"なし";
	}
	/**
	 * 使用停止 チェックフラグの設定
	 * @return stopFlg を戻します。
	 */
	public boolean isStopFlg() {
		return "1".equals(getUIUser().getStopFlg());
	}
	/**
	 * 使用停止 チェックフラグの設定
	 * @param stopFlg stopFlg を設定。
	 */
	public void setStopFlg(boolean stopFlg) {
		getUIUser().setStopFlg(stopFlg?"1":"0");
	}
	/** ご利用停止文言 */
	public String getLabelStopFlg(){
		return isStopFlg()?"ON":"OFF";
	}
	/*
	 *	ご請求フラグ  請求フラグ（1：請求対象 、2：請求対象外） 	
	 */
	public String getLabelSekyuFlg(){
		return "1".equals(getUIUser().getSekyuFlg())?"する":"しない";
	}
	public String getKeiyakuName() {
		if(getKeiyakuChoice() != null && getKeiyakuList() != null) {
			for(int i=0; i<getKeiyakuList().size(); i++) {
				CodKeiyakuType eCod = (CodKeiyakuType)getKeiyakuList().get(i);
				if(eCod.getKeiyakuType().equals(getKeiyakuChoice())) {
					return eCod.getKeiyakuTypeNm();
				}
			}
		}
		return "";
	}
	/**
	 * 画面表示用オーナー情報
	 * (オーナー情報時のみ使用)
	 * 
	 * TRタグ１段につき３個まで表示可能
	 * @return
	 */
	public List getUserOnerScreenList() {
		List userOnerScreenList = new ArrayList(0);
		if(getUserOnerList() != null
				&& getUserOnerList().size()>0) {
			//TRタグの段数を算出します。
			int trCnt = getUserOnerList().size()/3+(getUserOnerList().size()%3>0?1:0);
			for (int r=0; r<trCnt; r++) {
				//段数分のリストを格納します。
				userOnerScreenList.add(new ArrayList(0));
			}
			for (int i=0; i<getUserOnerList().size(); i++) {
				//現行行[ユーザー対象オーナー]の格納対象Entity[段]のインデックスを算出します。
				int rowIndex = i/3;
				//格納対象List[[段]]から格納対象Entity[段]を取得します。
				List listRow = (List)userOnerScreenList.get(rowIndex);
				//格納対象Entity[段]へ現行行[ユーザー対象オーナー]を格納します。
				listRow.add(getUserOnerList().get(i));
			}
		}
		return userOnerScreenList;
	}
	/**
	 * @return クラス変数userTypeList を戻します。
	 */
	public static List getUserTypeList() {
		userTypeList = new ArrayList(0);
		userTypeList.add(new SelectItem(UserType.ONER, "オーナー"));
		userTypeList.add(new SelectItem(UserType.TENPO, "店舗"));
		return userTypeList;
	}
	/**
	 * @return クラス変数userCompanyList を戻します。
	 */
	public List getUserCompanyList() {
		return userCompanyList;
	}
	/**
	 * @param userCompanyList を クラス変数userCompanyListへ設定します。
	 */
	public void setUserCompanyList(List userCompanyList) {
		this.userCompanyList = userCompanyList;
	}
	
}
