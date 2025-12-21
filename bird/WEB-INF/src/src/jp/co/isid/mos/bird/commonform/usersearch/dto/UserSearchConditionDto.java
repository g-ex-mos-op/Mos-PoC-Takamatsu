/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.usersearch.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * ユーザー検索処理Dto
 * 
 * 更新日：2006/03/13 「最初へ」「最後へ」ボタン追加対応
 * 更新日：2006/06/28  使用停止チェックフラグ及びロールリスト用プルダウン追加対応@author inazawa
 * 更新日: 2007/04/24 複数ウィンドウ対応
 * @author m.onodera
 */
public class UserSearchConditionDto {
    
    private static final int DEFAULT_CURRENT_PAGE_NUMBER = 1;
    private static final int DEFAULT_MAX_PAGE_COUNT = 30;
    
    /** ページ制御 */
    private HashMap currentPageNumber = new HashMap();;
    private HashMap count = new HashMap();;
    private HashMap maxPageCount = new HashMap();;


    /** 条件フィールド-入力項目 */	
    /* 会社コード */
    private HashMap kaisyaCd = new HashMap();
    /* ユーザー名 */
    private HashMap userName = new HashMap();
    /* 部門コード */
    private HashMap bumonCd = new HashMap();
    /* オーナコード */
    private HashMap onerCd = new HashMap();
    /* オーナ名 */
    private HashMap onerNameKj = new HashMap();
    /* 店コード */
    private HashMap miseCd = new HashMap();
    /* 店名称 */
    private HashMap miseNameKj = new HashMap();
    /* ロールコード ADD 2006/06/28*/
    private HashMap roleCd = new HashMap();
    /* 使用停止 checkbox ADD 2006/06/28*/
    private HashMap stopFlg = new HashMap();
    /* ユーザID(条件) checkbox ADD 2006/12/13*/
    private HashMap userIdCond = new HashMap();

    /** 条件フィールド-選択項目 */	
    /* 会社リスト（プルダウン用） */
    private HashMap companyList = new HashMap();
    /* 部門リスト（プルダウン用） */
    private HashMap bumonList = new HashMap();
    /* ロール（プルダウン用） ADD 2006/06/28*/
    private HashMap roleList = new HashMap();

    /** 結果リスト */	
    /* 検索結果ユーザーリストサイズ */
    private HashMap userSearchListSize = new HashMap();
    /* 検索結果ユーザーリスト */
    private HashMap userSearchList = new LinkedHashMap();
    /* 検索結果ユーザーhonbuリスト */
    private HashMap userSearchHonbuList = new HashMap();
    /* 検索結果ユーザーonerリスト */
    private HashMap userSearchOnerList = new HashMap();
    /* 検索結果ユーザーmiseリスト */
    private HashMap userSearchMiseList = new HashMap();
    
    /* ウインドウID */
    private int windowId;
    /* 最大ウインドウID */
    private int maxWindowId;
    private int maxSize;
    /* 呼出元画面ウインドウID */
    private Map parentViewWindowId = new HashMap();
    /* 遷移元ページ */
    private Map navigationCase = new HashMap();
    /* 遷移区分要否フラグ */
    private Map needReturnKind = new HashMap();

    
    /**
     * 会社コード設定処理
     * @return kaisyaCd
     */
    public String getKaisyaCd() {
        return (String) kaisyaCd.get(new Integer(getWindowId()));
	}
    /**
     * 会社コード設定処理
     * @param kaisyaCd
     */
	public void setKaisyaCd(String kaisyaCd) {
        this.kaisyaCd.put(new Integer(getWindowId()), kaisyaCd);
	}
    /**
     * ユーザー名設定処理
     * @return userName
     */
    public String getUserName() {
        return (String) userName.get(new Integer(getWindowId()));
	}
    /**
     * ユーザー名設定処理
     * @param userName
     */
	public void setUserName(String userName) {
        this.userName.put(new Integer(getWindowId()), userName);
	}
    /**
     * 部門コード設定処理
     * @return bumonCd
     */
    public String getBumonCd() {
        return (String) bumonCd.get(new Integer(getWindowId()));
    }
    /**
     * 部門コード設定処理
     * @param bumonCd
     */
    public void setBumonCd(String bumonCd) {
        this.bumonCd.put(new Integer(getWindowId()), bumonCd);
    }
    /**
     * オーナコード設定処理
     * @return miseNameKj
     */
	public String getOnerCd() {
        return (String) onerCd.get(new Integer(getWindowId()));
	}
    /**
     * オーナコード設定処理
     * @param miseNameKj
     */
	public void setOnerCd(String onerCd) {
        String code;
		if (onerCd != null) {
			CodeFormatter codefm = new CodeFormatter(5);
			codefm.setFormatPattern("00000");
			code = codefm.format(onerCd, true).trim();
		}
		else {
			code = onerCd;
		}
        this.onerCd.put(new Integer(getWindowId()), code);
	}
    /**
     * オーナ名設定処理
     * @return onerNameKj
     */
	public String getOnerNameKj() {
        return (String) onerNameKj.get(new Integer(getWindowId()));
	}
    /**
     * オーナ名設定処理
     * @param onerNameKj
     */
	public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj.put(new Integer(getWindowId()), onerNameKj);
	}
    /**
     * 店コード設定処理
     * @return miseCd
     */
	public String getMiseCd() {
        return (String) miseCd.get(new Integer(getWindowId()));
	}
    /**
     * 店コード設定処理
     * @param miseCd
     */
	public void setMiseCd(String miseCd) {
        String code;
		if (miseCd != null) {
			CodeFormatter codefm = new CodeFormatter(5);
			codefm.setFormatPattern("00000");
			code = codefm.format(miseCd, true).trim();
		}
		else {
			code = miseCd;
		}
        this.miseCd.put(new Integer(getWindowId()), code);
	}
    /**
     * 店名設定処理
     * @return miseNameKj
     */
	public String getMiseNameKj() {
        return (String) miseNameKj.get(new Integer(getWindowId()));
	}
    /**
     * 店名設定処理
     * @param miseNameKj
     */
	public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj.put(new Integer(getWindowId()), miseNameKj);
	}
	
    

    /**
     * 会社リスト設定
     * @return companyList
     */
    public List getCompanyList() {
        return (List) companyList.get(new Integer(getWindowId()));
    }
    /**
     * 会社リスト設定
     * @param companyList
     */
    public void setCompanyList(List companyList) {
        this.companyList.put(new Integer(getWindowId()), companyList);
    }
    /**
     * 部門リスト設定
     * @return bumonList
     */
    public List getBumonList() {
        return (List) bumonList.get(new Integer(getWindowId()));
    }
    /**
     * 部門リスト設定
     * @param bumonList
     */
    public void setBumonList(List bumonList) {
        this.bumonList.put(new Integer(getWindowId()), bumonList);
    }


    
    /**
     * 検索結果honbuリスト設定処理
     * @return onerSearchList
     */
    public List getUserSearchHonbuList() {
        return (List) userSearchHonbuList.get(new Integer(getWindowId()));
    }
    /**
     * 検索結果honbuリスト設定処理
     * @param onerSearchList
     */
    public void setUserSearchHonbuList(List userSearchHonbuList) {
        this.userSearchHonbuList.put(new Integer(getWindowId()), userSearchHonbuList);
    }
    /**
     * 検索結果onerリスト設定処理
     * @return onerSearchList
     */
    public List getUserSearchOnerList() {
        return (List) userSearchOnerList.get(new Integer(getWindowId()));
    }
    /**
     * 検索結果onerリスト設定処理
     * @param onerSearchList
     */
    public void setUserSearchOnerList(List userSearchOnerList) {
        this.userSearchOnerList.put(new Integer(getWindowId()), userSearchOnerList);
    }
    /**
     * 検索結果miseリスト設定処理
     * @return onerSearchList
     */
    public List getUserSearchMiseList() {
        return (List) userSearchMiseList.get(new Integer(getWindowId()));
    }
    /**
     * 検索結果miseリスト設定処理
     * @param onerSearchList
     */
    public void setUserSearchMiseList(List userSearchMiseList) {
        this.userSearchMiseList.put(new Integer(getWindowId()), userSearchMiseList);
    }
    /**
     * 検索結果リスト設定処理
     * @return onerSearchList
     */
    public List getUserSearchList() {
        return (List) userSearchList.get(new Integer(getWindowId()));
    }
    /**
     * 検索結果リスト設定処理
     * @param onerSearchList
     */
    public void setUserSearchList(List userSearchList) {
        // 現在ウインドウIDのデータを保持しているか判定
        Integer windowId = new Integer(getWindowId());
        if (this.userSearchList.containsKey(windowId)) {
            // 順番を入れ替える為、前回データを削除しておく
            this.userSearchList.remove(windowId);

        // 最大数を超える
        } else if (this.userSearchList.size() > 0 && this.userSearchList.size() >= getMaxSize()) {
            // 最古データを削除
            this.userSearchList.remove(this.userSearchList.keySet().toArray()[0]);
        }
        // set
        this.userSearchList.put(windowId, userSearchList);
    }
    
    /**
     * 検索結果リスト存在判定
     * @return 判定結果
     */
    public boolean isExistUserSearchList() {
        return userSearchList != null && userSearchList.containsKey(new Integer(getWindowId()));
    }
    
    /**
     * 検索結果リストサイズ設定処理
     * @return onerSearchListSize
     */
    public int getUserSearchListSize() {
        return ((Integer) userSearchListSize.get(new Integer(getWindowId()))).intValue();
    }
    /**
     * 検索結果リストサイズ設定処理
     * @param onerSearchListSize
     */
    public void setUserSearchListSize(int userSearchListSize) {
        this.userSearchListSize.put(new Integer(getWindowId()), new Integer(userSearchListSize));
    }
	
    /**
     * 検索情報クリア処理<br>
     * （パラメータfalse設定時、会社コード、会社リスト、部門リストはクリアしない）
     * @param flag
     */
    public void clear(boolean flag) {
        if (flag) {
            setKaisyaCd(null);
            setCompanyList(null);
            setBumonList(null);
            setBumonCd(null);
        }
        setUserName(null);
        setOnerCd(null);
        setOnerNameKj(null);
        setMiseCd(null);
        setMiseNameKj(null);
        setRoleCd(null);
        setStopFlg(false);
        setUserSearchList(null);
        setUserSearchHonbuList(null);
        setUserSearchOnerList(null);
        setUserSearchMiseList(null);
        setUserSearchListSize(0);
        setUserIdCond(null);
    }
    /**
     * ロールリスト設定
     * 作成日2006/06/28
     * @return　roleList
     * @author inazawa
     */
    public List getRoleList() {
        return (List) roleList.get(new Integer(getWindowId()));
    }
    /**
     * ロールリスト設定
     * 作成日2006/06/28
     * @param　roleList
     * @author inazawa
     */
    public void setRoleList(List roleList) {
        this.roleList.put(new Integer(getWindowId()), roleList);
    }
    /**
     * ロールコード設定
     * 作成日2006/06/28
     * @return　roleList
     * @author inazawa
     */
    public String getRoleCd() {
        return (String) roleCd.get(new Integer(getWindowId()));
    }
    /**
     * ロールコード設定
     * 作成日2006/06/28
     * @param　rolecd
     * @author inazawa
     */
    public void setRoleCd(String roleCd) {
        String code;
        if (roleCd != null) {
            CodeFormatter codefm = new CodeFormatter(3);
            codefm.setFormatPattern("000");
            code = codefm.format(roleCd, true).trim();
        }
        else {
            code = roleCd;
        }
        this.roleCd.put(new Integer(getWindowId()), code);
    }

    /**
     * 使用停止設定
     * @return　stopFlg
     */
    public boolean isStopFlg() {
        if (stopFlg.containsKey(new Integer(getWindowId()))) {
            return ((Boolean) stopFlg.get(new Integer(getWindowId()))).booleanValue();
        }
        else {
            return false;
        }
    }
    /**
     * 使用停止設定
     * @param　stopFlg
     */
    public void setStopFlg(boolean stopFlg) {
        this.stopFlg.put(new Integer(getWindowId()), new Boolean(stopFlg));
    }

    /**
     * ユーザID(条件)を取得します
     * @return　userIdCond
     */
    public String getUserIdCond() {
        return (String) userIdCond.get(new Integer(getWindowId()));
    }
    /**
     * ユーザID(条件)を設定します
     * @param　userIdCond
     */
    public void setUserIdCond(String userIdCond) {
        this.userIdCond.put(new Integer(getWindowId()), userIdCond);
    }

    
//  ---------------------------
	/**
	 * コンストラクタ
	 * 
	 */
	public UserSearchConditionDto() {
		//this(DEFAULT_MAX_PAGE_COUNT);
	}

//	/**
//	 * コンストラクタ
//	 * 
//	 * @param maxPageCount ページ表示件数
//	 */
//	public UserSearchConditionDto(int maxPageCount) {
//		setMaxPageCount((maxPageCount > 0) ? maxPageCount : DEFAULT_MAX_PAGE_COUNT);
//		setCurrentPageNumber(DEFAULT_CURRENT_PAGE_NUMBER);
//	}

	public int getCurrentPageNumber() {
        if (currentPageNumber.containsKey(new Integer(getWindowId()))) {
            return ((Integer) currentPageNumber.get(new Integer(getWindowId()))).intValue();
        }
        else { 
            return 0;
        }
	}
	public void setCurrentPageNumber(int currentPageNumber) {
		if (currentPageNumber >= getFirstPageNumber() && currentPageNumber <= getLastPageNumber()) {
            this.currentPageNumber.put(new Integer(getWindowId()), new Integer(currentPageNumber));
		}
	}

	public int getCount() {
        if (count.containsKey(new Integer(getWindowId()))) {
            return ((Integer) count.get(new Integer(getWindowId()))).intValue();
        }
        else {
            return 0;
        }
	}
	public void setCount(int count) {
		if (count >= 0) {
            this.count.put(new Integer(getWindowId()), new Integer(count));
		}
	}

	public int getMaxPageCount() {
        if (maxPageCount.containsKey(new Integer(getWindowId()))) {
            return ((Integer) maxPageCount.get(new Integer(getWindowId()))).intValue();
        }
        else {
            return 0;
        }
	}
	public void setMaxPageCount(int maxPageCount) {
		if (maxPageCount > 0) {
            this.maxPageCount.put(new Integer(getWindowId()), new Integer(maxPageCount));
		}
	}

	//先頭ページ番号    : 常に1。
	public int getFirstPageNumber() {
		return 1;
	}
	//最終ページ番号    : (全件数 - 1) / 表示件数 + 1。全0件、1件表示の時に注意。
	public int getLastPageNumber() {
		return (getCount() == 0) ? 1 : (getCount() - 1) / getMaxPageCount() + 1;
	}
	//前のページ番号    : 現在ページ - 1。先頭ページの場合、同じページ番号。
	public int getPreviousPageNumber() {
		return (isExistPreviousPage()) ? getCurrentPageNumber() - 1 : getCurrentPageNumber();
	}
	//次のページ番号    : 現在ページ + 1。最終ページの場合、同じページ番号。
	public int getNextPageNumber() {
		return (isExistNextPage()) ? getCurrentPageNumber() + 1 : getCurrentPageNumber();
	}
	//ページ先頭件数    : offsetに使いたい。(現在ページ - 1) * 件数。0～で取れる。
	public int getPageFirstRecordNumber() {
		return (getCurrentPageNumber() - 1) * getMaxPageCount();
	}
	//先頭ページ？      : 現在ページ == 先頭ページ
	public boolean isFirstPage() {
		return getCurrentPageNumber() == getFirstPageNumber();
	}
	//最終ページ？      : 現在ページ == 最終ページ
	public boolean isLastPage() {
		return getCurrentPageNumber() == getLastPageNumber();
	}
	//前のページ有？    : 現在ページ > 先頭ページ
	public boolean isExistPreviousPage() {
		return getCurrentPageNumber() > getFirstPageNumber();
	}
	//次のページ有？    : 現在ページ < 最終ページ
	public boolean isExistNextPage() {
		return getCurrentPageNumber() < getLastPageNumber();
	}
    
    /**
     * ウインドウID取得
     * @return ウインドウID
     */
    public int getWindowId() {
        return windowId;
    }
    /**
     * ウインドウID設定
     * @param windowId ウインドウID
     */
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    /**
     * 最大ウインドウID取得
     * @return 最大ウインドウID
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }
    /**
     * 最大ウインドウID設定
     * @param maxWindowId 最大ウインドウID
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }
    /**
     * 最大データ保持件数取得
     * @return 最大データ保持件数
     */
    public int getMaxSize() {
        return maxSize;
    }
    /**
     * 最大データ保持件数設定
     * @param maxSize 最大データ保持件数
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    /**
     * ウインドウID生成
     * @return 採番されたウインドウID
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    /**
     * ウインドウID更新
     */
    public void updateWindowid() {
        setWindowId(createWindowId());
        setMaxPageCount(DEFAULT_MAX_PAGE_COUNT);
        setCurrentPageNumber(DEFAULT_CURRENT_PAGE_NUMBER);
    }

    /**
     * 呼出元画面ウインドウID取得
     * @return
     */
    public int getParentViewWindowId() {
        return ((Integer) parentViewWindowId.get(new Integer(getWindowId()))).intValue();
    }
    /**
     * 呼出元画面ウインドウID設定
     * @param parentViewWindowId
     */
    public void setParentViewWindowId(int parentViewWindowId) {
        this.parentViewWindowId.put(new Integer(getWindowId()), new Integer(parentViewWindowId));
    }
    /**
     * 遷移元ページ取得
     * @return 遷移元ページ
     */
    public String getNavigationCase() {
        return (String) navigationCase.get(new Integer(getWindowId()));
    }
    /**
     * 遷移元ページ設定
     * @param navigationCase 遷移元ページ
     */
    public void setNavigationCase(String navigationCase) {
        this.navigationCase.put(new Integer(getWindowId()), navigationCase);
    }
    /**
     * 遷移区分要否フラグ取得
     * @return 遷移区分要否フラグ
     */
    public boolean isNeedReturnKind() {
        return ((Boolean) needReturnKind.get(new Integer(getWindowId()))).booleanValue();
    }
    /**
     * 遷移区分要否フラグ設定
     * @param needReturnKind 遷移区分要否フラグ
     */
    public void setNeedReturnKind(boolean needReturnKind) {
        this.needReturnKind.put(new Integer(getWindowId()), Boolean.valueOf(needReturnKind));
    }
}
