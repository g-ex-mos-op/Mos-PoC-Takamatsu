/*
 * 作成日: 2006/02/14
 * 更新日: 2006/07/04 パスワード変更追加対応　K.INAZAWA
 */
package jp.co.isid.mos.bird.bizadmin.accountedit.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIUser;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserCompany;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserMise;
import jp.co.isid.mos.bird.bizadmin.common.entity.UIUserOner;
import jp.co.isid.mos.bird.common.code.SetteiKbn;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;



/**
 * @author 慮
 *
 */
public class AccounteditDto {
	
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;
    /* ユーザー初期パスワード確認用 20060703ADD*/
    private String userPassWordKakunin;
	/*  システム日付 */
    private String sysDt;
    /* 業態のリスト　*/
    private List userGyotaiList;
    /* オーナーの情報　リスト(オーナーのみ使用)　*/
    private List userOnerList;
    /* 変更ビューID*/
    private String pageId;
    /* ログインユーザー設定区分*/
    private String seteiKbn;
    
    private String userPassWord;
    /*編集画面用パスワード*/
    private String userPassWordImple;
    /*編集画面用パスワード確認*/
    private String userPassWordKakuninImple;
    /* 会社コード*/
    private String kaisyaCd;
    /* 会社リスト（プルダウン用） */
    private List companyList;
    /* 選択用会社リスト（画面用) */
    private List userCompanyList;
    /* 所属会社情報(本部のみ使用) */
    private UIUserCompany userCompany;
    /* ユーザー対応オーナーの会社リスト(本部のみ使用)  */
    private UIUserOner userOner;
    /* ユーザー対応店舗の会社リスト(店舗のみ使用)  */
    private UIUserMise userMise;
    /* オーナー契約タイプ */
    private String keiyakuType;
    
    /* 他画面からの遷移時の戻り画面ViewID */
    private String returnViewId;

    private UIUser uIUser;
    
    /*画面タイトル*/
    private String gamenTitle;
    
    /* マトリクスリセットフラグ */
    private boolean matrixResetFlg = false;
    
    private CtlLoginFailKaisu ctlLoginFailKaisu;
    /** ロック文言 */
    private String pwLockMsg;
    
    /* ロック解除フラグ */
    private boolean pwLockResetFlg = false;
    /** 現在有効なセッションキー値 */
    private String sessionKeyNow;
    /** ウィンドウ別セッションキー情報Map */
    private Map sessionKey = new HashMap();
    
	/**
     * ウィンドウIDを生成します。
     */
    public int createWindowId() {
    	maxWindowId++;
        return maxWindowId;
    }
	public String getLabelLimitKbn(){
		return "1".equals(getUIUser().getLimitKbn())?"あり":"なし";
	}
    /**
     * リセット状態ラベル表示値取得
     * 
     * @param flg
     * @return
     */
    private static String getLabelResetFlg(boolean flg){
        return flg?"する":"しない";
    }
	/** ご利用停止文言 */
	public String getLabelStopFlg(){
		return "1".equals(getUIUser().getStopFlg())?"ON":"OFF";
	}
	/*
	 *	ご請求フラグ  請求フラグ（1：請求対象 、2：請求対象外） 	
	 */
	public String getLabelSekyuFlg(){
		return "1".equals(getUIUser().getSekyuFlg())?"する":"しない";
	}
    /**
     * マトリックスリセット状態ラベル表示値取得
     * @return
     */
    public String getMatrixResetFlgLabel(){
        return getLabelResetFlg(isMatrixResetFlg());
    }
    /**
     * ロック解状態ラベル表示値取得
     * @return
     */
    public String getPwResetFlgLabel(){
        return getLabelResetFlg(isPwLockResetFlg());
    }
	/**
	 * @return seteiKbn を戻します。
	 */
	public String getSeteiKbn() {
		return seteiKbn;
	}
	/**
	 * @param seteiKbn seteiKbn を設定。
	 */
	public void setSeteiKbn(String seteiKbn) {
		this.seteiKbn = seteiKbn;
	}
    
	/**
	 * @return userOnerList を戻します。
	 */
	public List getUserOnerList() {
		return userOnerList;
	}
	/**
	 * @param userOnerList userOnerList を設定。
	 */
	public void setUserOnerList(List onerList) {
		this.userOnerList = onerList;
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
	 * @return userGyotaiList を戻します。
	 */
	public List getUserGyotaiList() {
		return userGyotaiList;
	}
	/**
	 * @param userGyotaiList userGyotaiList を設定。
	 */
	public void setUserGyotaiList(List goutaiList) {
		this.userGyotaiList = goutaiList;
	}


	/**
	 * @return pageId を戻します。
	 */
	public String getPageId() {
		return pageId;
	}
	/**
	 * @param pageId pageId を設定。
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public void clear(){
		setCompanyList(null);
		setUserGyotaiList(null);
        setKaisyaCd(null);
		setPageId(null);
        setUserCompanyList(null);
        setUserCompany(null);
		setUserOnerList(null);
        setUserOner(null);
        setUIUser(null);
        setUserMise(null);
        setUserPassWordKakunin(null);
        setMatrixResetFlg(false);
        setPwLockMsg(null);
        setPwLockResetFlg(false);
        setSessionKeyNow(null);
	}
    /**
     * 修正用パスワードをセット
     * @return  userPassWordImple
     */
    public String getUserPassWordImple() {
        return CommonUtil.rtrim(userPassWordImple);
    }
    /**
     * 修正用パスワードを返す
     * @param userPassWordImple
     */
    public void setUserPassWordImple(String userPassWordImple) {
        this.userPassWordImple = userPassWordImple;
    }
    
    /**
     * 修正用パスワード（確認用）をセット
     * @return  userPassWordKakuninImple
     */
    public String getUserPassWordKakuninImple() {
        return CommonUtil.rtrim(userPassWordKakuninImple);
    }
    /**
     * 修正用パスワード（確認用）を返す
     * @param userPassWordKakuninImple
     */
    public void setUserPassWordKakuninImple(String userPassWordKakuninImple) {
        this.userPassWordKakuninImple = userPassWordKakuninImple;
    }
    /**
     * パスワードリセット有無判断処理
     * 
     * @return  userPassWordImple
     */
    public boolean isPassWordReset() {
    	if(getUserPassWordImple() != null 
    			&& getUserPassWordImple().length()>0) {
    		return true;
    	}
    	return false;
    }
    /**
     * uIUserを返す 
     * @return uIUser
     */
    public UIUser getUIUser() {
        return uIUser;
    }
    /**
     * uIUserをセット
     * @param user
     */
    public void setUIUser(UIUser user) {
        uIUser = user;
    }
    public List getCompanyList() {
        return companyList;
    }
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }
    public String getKaisyaCd() {
        return kaisyaCd==null?"":kaisyaCd;
    }
    public void setKaisyaCd(String kaisyaCd) {
        this.kaisyaCd = kaisyaCd;
    }
    public String getKeiyakuType() {
        return keiyakuType;
    }
    public void setKeiyakuType(String keiyakuType) {
        this.keiyakuType = keiyakuType;
    }
    public String getReturnViewId() {
        return returnViewId;
    }
    public void setReturnViewId(String returnViewId) {
        this.returnViewId = returnViewId;
    }
    public String getGamenTitle() {
        return gamenTitle;
    }
    public void setGamenTitle(String gamenTitle) {
        this.gamenTitle = gamenTitle;
    }
    public boolean isMatrixResetFlg() {
        return matrixResetFlg;
    }
    public void setMatrixResetFlg(boolean matrixResetFlg) {
        this.matrixResetFlg = matrixResetFlg;
    }
    public String getSysDt() {
        return sysDt;
    }
    public void setSysDt(String sysDt) {
        this.sysDt = sysDt;
    }
	/**
	 * @return pwLockResetFlg を戻します。
	 */
	public boolean isPwLockResetFlg() {
		return pwLockResetFlg;
	}
	/**
	 * ロック解除フラグ
	 * @param pwLockResetFlg を クラス変数pwLockResetFlgへ設定します。
	 */
	public void setPwLockResetFlg(boolean pwLockResetFlg) {
		this.pwLockResetFlg = pwLockResetFlg;
	}
	/**
	 * @return ctlLoginFailKaisu を戻します。
	 */
	public CtlLoginFailKaisu getCtlLoginFailKaisu() {
		return ctlLoginFailKaisu;
	}
	/**
	 * @param ctlLoginFailKaisu を クラス変数ctlLoginFailKaisuへ設定します。
	 */
	public void setCtlLoginFailKaisu(CtlLoginFailKaisu ctlLoginFailKaisu) {
		this.ctlLoginFailKaisu = ctlLoginFailKaisu;
	}
	/**
	 * ロック状態文字列取得処理
	 * 
	 * ユーザIDの右側にロック状態の場合に表示される
	 * ”ロック状態”の文字列を返します。
	 * @return
	 */
	public String getPwLockMsg() {
		return pwLockMsg;
	}
	/**
	 * ロック文言設定処理
	 * 
	 * "ロック状態"の文字列が設定されます。
	 */
	public void setPwLockMsg(String msg) {
		pwLockMsg = msg;
	}
	/**
	 * システム管理者か否か判断処理
	 * 
	 * @return true:システム管理者
	 */
	public boolean isSystemAdminUser() {
	    /* システム管理者設定区分 */
		return SetteiKbn.isSystemAdminUser(getSeteiKbn());

	}
	/**
	 * ユーザー管理者か否か判断処理
	 * 
	 * @return true:ユーザー管理者
	 */
	public boolean isUserAdminUser() {
	    /* ユーザー管理者設定区分 */
		return SetteiKbn.isUserAdminUser(getSeteiKbn());

	}
	/**
	 * @return クラス変数sessionKey を戻します。
	 */
	public String getSessionKey(int windowId) {
		return (String)sessionKey.get(new Integer(windowId));
	}
	/**
	 * @param sessionKey を クラス変数sessionKeyへ設定します。
	 */
	public void setSessionKey(int windowId, String sessionKey) {
		this.sessionKey.put(new Integer(windowId), sessionKey);
	}
	/**
	 * @return クラス変数sessionKeyNow を戻します。
	 */
	public String getSessionKeyNow() {
		return sessionKeyNow;
	}
	/**
	 * @param sessionKeyNow を クラス変数sessionKeyNowへ設定します。
	 */
	public void setSessionKeyNow(String sessionKeyNow) {
		this.sessionKeyNow = sessionKeyNow;
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
	 * @return クラス変数listUserCompany を戻します。
	 */
	public List getUserCompanyList() {
		return userCompanyList;
	}
	/**
	 * @param userCompanyList を クラス変数listUserCompanyへ設定します。
	 */
	public void setUserCompanyList(List listUserCompany) {
		this.userCompanyList = listUserCompany;
	}
	/**
	 * @return クラス変数userOner を戻します。
	 */
	public UIUserOner getUserOner() {
		return userOner;
	}
	/**
	 * @param userOner を クラス変数userOnerへ設定します。
	 */
	public void setUserOner(UIUserOner userOner) {
		this.userOner = userOner;
	}
	/**
	 * @return クラス変数userCompany を戻します。
	 */
	public UIUserCompany getUserCompany() {
		return userCompany;
	}
	/**
	 * @param userCompany を クラス変数userCompanyへ設定します。
	 */
	public void setUserCompany(UIUserCompany userCompany) {
		this.userCompany = userCompany;
	}
	/**
	 * @return クラス変数userPassWord を戻します。
	 */
	public String getUserPassWord() {
		return userPassWord;
	}
	/**
	 * @param userPassWord を クラス変数userPassWordへ設定します。
	 */
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	/**
	 * ユーザー名(ご利用者名)入力可能判断処理
	 * 
	 * @return true:入力可
	 */
	public boolean isInputUserName() {
		return ("01".equals(getKeiyakuType()) == false
			&& "03".equals(getKeiyakuType()) == false);
	}
	/**
	 * 使用停止チェック判断処理
	 * 
	 * @return true:チェックあり
	 */
	public boolean isCheckStopFLg() {
		return "1".equals(getUIUser().getStopFlg());
	}
	/**
	 * 使用停止値設定処理
	 * 
	 * @param check true:チェックあり
	 */
	public void setCheckStopFLg(boolean check) {
		getUIUser().setStopFlg(check?"1":"0");
	}
}
