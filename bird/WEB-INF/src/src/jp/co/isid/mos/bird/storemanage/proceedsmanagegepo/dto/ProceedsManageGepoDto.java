package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 売上金管理月報・条件部DTOクラス
 *
 * @author xjung
 */
public class ProceedsManageGepoDto implements CsvOutputDto {
// 条件部リスト
    /**
     * 管理会社企業情報リスト
     */
    private Map companyList = new HashMap();

    /**
     * 対象店舗情報リスト
     */
    private Map taishoTenpoList = new HashMap();

    /**
     * 対象年月リスト
     */
    private List taishoYMList;

    /**
     * 明細対象年月リスト
     */
    private List meisaiTaishoYMList;

    /**
     * 集計区分情報
     */
    private List syukeiKbnList;

    /**
     * 会計区分情報
     */
    private List kaikaiKbnList;

    /**
     * 集計区分別会計区分マスタ情報
     */
    private Map kaikeiMasterInfo;

// 	条件部リストgetter/setter
	/**
	 * 会社リストを取得する
	 * @return List 会社リスト
	 */
	public List getCompanyList() {
        return (List) companyList.get(new Integer(getWindowId()));
	}

	/**
	 * 会社リストを設定する
	 * @param companyList 会社リスト
	 */
	public void setCompanyList(List companyList) {
        this.companyList.put(new Integer(getWindowId()), companyList);
	}

	/**
	 * 会社リストを取得する
	 * @param windowId ウィンドウID
	 * @return List 会社リスト
	 */
	public List getCompanyListWindowId(int windowId) {
        return (List) companyList.get(new Integer(windowId));
	}

	/**
	 * 会社リストを設定する
	 * @param windowId ウィンドウID
	 * @param companyList 会社リスト
	 */
	public void setCompanyListWindowId(int windowId, List companyList) {
        this.companyList.put(new Integer(windowId), companyList);
	}

    /**
     * 会社リストサイズを取得する
     * @return int 会社リストサイズ
     */
    public int getCompanyListSize() {
        return (List) companyList.get(new Integer(getWindowId())) == null ?
            0 : ((List) companyList.get(new Integer(getWindowId()))).size();
    }

	/**
	 * 対象店舗リストを取得する
	 * @return List 対象店舗リスト
	 */
	public List getTaishoTenpoList() {
        return (List) taishoTenpoList.get(new Integer(getWindowId()));
	}

	/**
	 * 対象店舗リストを設定する
	 * @param taishoTenpoList 対象店舗リスト
	 */
	public void setTaishoTenpoList(List taishoTenpoList) {
        this.taishoTenpoList.put(new Integer(getWindowId()), taishoTenpoList);
	}

	/**
	 * 対象店舗リストを取得する
	 * @param windowId ウィンドウID
	 * @return List 対象店舗リスト
	 */
	public List getTaishoTenpoListWindowId(int windowId) {
        return (List) taishoTenpoList.get(new Integer(windowId));
	}

	/**
	 * 対象店舗リストを設定する
	 * @param windowId ウィンドウID
	 * @param taishoTenpoList 対象店舗リスト
	 */
	public void setTaishoTenpoListWindowId(int windowId, List companyList) {
		this.taishoTenpoList.put(new Integer(windowId), companyList);
	}


	/**
	 * 対象年月(yyyy/MM)リストを取得する
	 * @return List 対象年月(yyyy/MM)リスト
	 */
	public List getTaishoYMList() {
		return taishoYMList;
	}

	/**
	 * 対象年月(yyyy/MM)リストを設定する
	 * @param taishoYMList 対象年月(yyyy/MM)リスト
	 */
	public void setTaishoYMList(List taishoYMList) {
		this.taishoYMList = taishoYMList;
	}

	/**
	 * 明細対象年月リストを取得する
	 * @return meisaiTaishoYMList 明細対象年月リスト
	 */
	public List getMeisaiTaishoYMList() {
		return meisaiTaishoYMList;
	}

	/**
	 * 明細対象年月リストを設定する
	 * @param meisaiTaishoYMList 明細対象年月リスト
	 */
	public void setMeisaiTaishoYMList(List meisaiTaishoYMList) {
		this.meisaiTaishoYMList = meisaiTaishoYMList;
	}

	/**
	 * 集計区分情報を戻します。
	 * @return syukeiKbnList 集計区分情報
	 */
	public List getSyukeiKbnList() {
	    return syukeiKbnList;
	}

	/**
	 * 集計区分情報を設定します。
	 * @param syukeiKbnList 集計区分情報
	 */
	public void setSyukeiKbnList(List syukeiKbnList) {
	    this.syukeiKbnList = syukeiKbnList;
	}

	/**
	 * 会計区分情報を取得する
	 * @return kaikaiKbnList 会計区分情報
	 */
	public List getKaikaiKbnList() {
		return kaikaiKbnList;
	}

	/**
	 * 会計区分情報を設定する
	 * @param kaikaiKbnList 会計区分情報
	 */
	public void setKaikaiKbnList(List kaikaiKbnList) {
		this.kaikaiKbnList = kaikaiKbnList;
	}

    /**
     * 集計区分別会計区分マスタ情報を戻します。
     * @return kaikeiInfoMaster 集計区分別会計区分マスタ情報
     */
    public Map getKaikeiMasterInfo() {
        return kaikeiMasterInfo;
    }

    /**
     * 集計区分別会計区分マスタ情報を設定します。
     * @param kaikeiMasterInfo 集計区分別会計区分マスタ情報
     */
    public void setKaikeiMasterInfo(Map kaikeiMasterInfo) {
        this.kaikeiMasterInfo = kaikeiMasterInfo;
    }

    /**
     * 集計区分情報を戻します。
     * @param syukeiKbn 集計区分コード
     * @return UILists 集計区分情報
     */
   public UILists getKaikeiMasterInfoSyukeiKbn(String syukeiKbn) {
       return (UILists) kaikeiMasterInfo.get(syukeiKbn);
   }

// ログインユーザ情報
    /**
     * ユーザタイプ
     */
    private String userType;

    /**
     * ユーザID
     */
    private String userId;

    /**
     * 制限フラグ
     */
    private boolean limitKbn;

    /**
     * オーナーコード
     */
    private String onerCd;


//	ログインユーザ情報getter/setter
    /**
     * ユーザタイプを取得する
     * @return ユーザタイプ
     */
    public String getUserType() {
        return userType;
    }

    /**
     * ユーザタイプを設定する
     * @param userType ユーザタイプ
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * ユーザIDを取得する
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定する
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 制限フラグを取得する
     * @return 制限フラグ
     */
    public boolean isLimitKbn() {
        return limitKbn;
    }

    /**
     * 制限フラグを設定する
     * @param limitKbn 制限フラグ
     */
    public void setLimitKbn(boolean limitKbn) {
        this.limitKbn = limitKbn;
    }

    /**
     * オーナーコードを取得する
     * @return オーナーコード
     */
    public String getOnerCd() {
        return onerCd;
    }

    /**
     * オーナーコードを設定する
     * @param onerCd オーナーコード
     */
   public void setOnerCd(String onerCd) {
        this.onerCd = onerCd;
    }

//	ウインドウ管理
	/**
	 * ウインドウID
	 */
	private int windowId;

    /**
     * MAXウィインドウID
     */
    private int maxWindowId;

//	ウインドウ管理getter/setter
	/**
	 * ウインドウIDを取得する
	 * @return ウインドウID
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * ウインドウIDを設定する
	 * @param windowId ウインドウID
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

    /**
     * MAXウィンドウIDを取得する
     * @return MAXウィンドウID
     */
    public int getMaxWindowId() {
        return maxWindowId;
    }

    /**
     * MAXウィンドウIDを設定する
     * @param maxWindowId MAXウィンドウID
     */
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }

    /**
     * ウィンドウIDを生成する
     */
    public void updateWindowId() {
        this.setWindowId(createWindowId());
    }

    /**
     * MAXウィンドウIDを生成する
     * @return int  MAXウィンドウID
     */
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }

//	検索条件
	/**
	 * 管理会社企業情報
	 */
	private Map companyCd = new HashMap();

	/**
	 * 対象店舗情報
	 */
	private Map taishoTenpoCd = new HashMap();

	/**
	 * 対象年月
	 */
	private Map taishoYM = new HashMap();

    /**
     * 管理会社企業情報(一覧検索条件)
     */
    private Map oldCompanyCd = new HashMap();

    /**
     * 対象店舗情報(一覧検索条件)
     */
    private Map oldTaishoTenpoCd = new HashMap();

    /**
     * 対象年月(一覧検索条件)
     */
    private Map oldTaishoYM = new HashMap();

    /**
     * 店舗名称
     */
    private String taishoTenpo;

// 検索条件getter/setter
	/**
	 * 会社コードを取得する
	 * @return 会社コード
	 */
	public String getCompanyCd() {
		return (String) companyCd.get(new Integer(getWindowId()));
	}

	/**
	 * 会社コードを設定する
	 * @param companyCd 会社コード
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd.put(new Integer(getWindowId()), companyCd);
	}

	/**
	 * 対象店舗を取得する
	 * @return 対象店舗
	 */
	public String getTaishoTenpoCd() {
		return (String) taishoTenpoCd.get(new Integer(getWindowId()));
	}

	/**
	 * 対象店舗を設定する
	 * @param taishoTenpoCd 対象店舗
	 */
	public void setTaishoTenpoCd(String taishoTenpoCd) {
		this.taishoTenpoCd.put(new Integer(getWindowId()), taishoTenpoCd);
	}

	/**
	 * 対象年月を取得する
	 * @return 対象年月
	 */
	public String getTaishoYM() {
		return (String) taishoYM.get(new Integer(getWindowId()));
	}

	/**
	 * 対象年月を設定する
	 * @param taishoYM 対象年月
	 */
	public void setTaishoYM(String taishoYM) {
		this.taishoYM.put(new Integer(getWindowId()), taishoYM);
	}

    /**
     * 会社コード(一覧検索条件)を取得する
     * @return 会社コード(一覧検索条件)
     */
    public String getOldCompanyCd() {
        return (String) oldCompanyCd.get(new Integer(getWindowId()));
    }

    /**
     * 会社コード(一覧検索条件)を設定する
     * @param oldCompanyCd 会社コード(一覧検索条件)
     */
    public void setOldCompanyCd(String oldCompanyCd) {
        this.oldCompanyCd.put(new Integer(getWindowId()), oldCompanyCd);
    }

    /**
     * 対象店舗(一覧検索条件)を取得する
     * @return 対象店舗(一覧検索条件)
     */
    public String getOldTaishoTenpoCd() {
        return (String) oldTaishoTenpoCd.get(new Integer(getWindowId()));
    }

    /**
     * 対象店舗(一覧検索条件)を設定する
     * @param oldTaishoTenpoCd 対象店舗(一覧検索条件)
     */
    public void setOldTaishoTenpoCd(String oldTaishoTenpoCd) {
        this.oldTaishoTenpoCd.put(new Integer(getWindowId()), oldTaishoTenpoCd);
    }

    /**
     * 対象年月(一覧検索条件)を取得する
     * @return 対象年月(一覧検索条件)
     */
    public String getOldTaishoYM() {
        return (String) oldTaishoYM.get(new Integer(getWindowId()));
    }

    /**
     * 対象年月(一覧検索条件)を設定する
     * @param oldTaishoYM 対象年月(一覧検索条件)
     */
    public void setOldTaishoYM(String oldTaishoYM) {
        this.oldTaishoYM.put(new Integer(getWindowId()), oldTaishoYM);
    }

// 画面表示制御項目
    /**
     * タブ区分
     */
    private Map tabKbn = new HashMap();

    /**
     * サブタブ区分
     */
    private Map subTabKbn = new HashMap();

    /**
     * 検索ボタン名
     */
    private Map schBtName = new HashMap();

    /**
     * 検索フラグ
     */
    private Map searchFlg = new HashMap();

// 画面表示制御項目getter/setter
    /**
     * タブ区分を取得する
     * @return タブ区分
     */
    public String getTabKbn() {
        return (String) tabKbn.get(new Integer(getWindowId()));
    }

    /**
     * タブ区分を設定する
     * @param tabKbn タブ区分
     */
    public void setTabKbn(String tabKbn) {
        this.tabKbn.put(new Integer(getWindowId()), tabKbn);
    }

    /**
     * サブタブ区分を取得する
     * @return サブタブ区分
     */
    public String getSubTabKbn() {
        return (String) subTabKbn.get(new Integer(getWindowId()));
    }

    /**
     * サブタブ区分を設定する
     * @param subTabKbn サブタブ区分
     */
    public void setSubTabKbn(String subTabKbn) {
        this.subTabKbn.put(new Integer(getWindowId()), subTabKbn);
    }

    /**
     * 検索ボタン名を取得する
     * @return 検索ボタン名
     */
    public String getSchBtName() {
        return (String) schBtName.get(new Integer(getWindowId()));
    }

    /**
     * 検索ボタン名を設定する
     * @param schBtName 検索ボタン名
     */
    public void setSchBtName(String schBtName) {
        this.schBtName.put(new Integer(getWindowId()), schBtName);
    }

    /**
     * 検索フラグを取得する
     * @return 検索フラグ
     */
    public boolean getSearchFlg() {
        return ((Boolean) searchFlg.get(new Integer(getWindowId()))).booleanValue();
    }

    /**
     * 検索フラグを設定する
     * @param searchFlg 検索フラグ
     */
    public void setSearchFlg(boolean searchFlg) {
        this.searchFlg.put(new Integer(getWindowId()), Boolean.valueOf(searchFlg));
    }

   /**
    * 店舗名称を取得する
    * @return 店舗名称
    */
   public String getTaishoTenpo() {
       return taishoTenpo;
   }

   /**
    * 店舗名称を設定する
    * @param taishoTenpo 店舗名称
    */
  public void setTaishoTenpo(String taishoTenpo) {
       this.taishoTenpo = taishoTenpo;
   }

    /**
     * 既存検索条件をクリアする
     */
    public void clearOldCondition() {
        setOldCompanyCd(null);
        setOldTaishoTenpoCd(null);
        setOldTaishoYM(null);
    }

 // 日付関連情報
    /**
     * システム日次
     */
    String sysDate;

    /**
     * システム日次を戻します。
     * @return sysDate システム日次
     */
    public String getSysDate() {
        return sysDate;
    }

    /**
     * システム日次を設定します。
     * @param sysDate システム日次
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

// 共通画面遷移時保持項目

    /**
     * 共通画面遷移時保持明細条件
     */
    private Map commonTransitionMeisaiJoken = new HashMap();

    /**
     * 共通画面遷移時保持明細結果
     */
    private Map commonTransitionMeisaiResult = new HashMap();

    /**
     * 共通画面遷移時保持売上、消費税の明細結果
     */
    private Map commonTransitionUriageTaxMeisaiResult = new HashMap<>();

	/**
	 * 共通画面遷移時保持明細条件を取得する
 	 * @param windowId ウィンドウID
	 * @return MeisaiRequestJokenDto 売上金管理月報明細照会・条件部DTO
	 */
	public MeisaiRequestJokenDto getCommonTransitionMeisaiJokenWindowId(int windowId) {
		return (MeisaiRequestJokenDto) commonTransitionMeisaiJoken.get(new Integer(windowId));
	}

	/**
	 * 共通画面遷移時保持明細条件を設定する
 	 * @param windowId ウィンドウID
	 * @param meisaiRequestJokenDto 売上金管理月報明細照会・条件部DTO
	 */
	public void setCommonTransitionMeisaiJokenWindowId(int windowId, MeisaiRequestJokenDto meisaiRequestJokenDto) {
		this.commonTransitionMeisaiJoken.put(new Integer(windowId), meisaiRequestJokenDto);
	}


	/**
	 * 共通画面遷移時保持明細結果を取得する
 	 * @param windowId ウィンドウID
	 * @return MeisaiRequestResultDto 売上金管理月報明細照会・結果DTO
	 */
	public MeisaiRequestResultDto getCommonTransitionMeisaiResultWindowId(int windowId) {
		return (MeisaiRequestResultDto) commonTransitionMeisaiResult.get(new Integer(windowId));
	}

	/**
	 * 	 * 共通画面遷移時保持明細結果を設定する
	 * @param windowId ウィンドウID
	 * @param meisaiRequestResultDto 売上金管理月報明細照会・結果DTO
	 */
	public void setCommonTransitionMeisaiResultWindowId(int windowId, MeisaiRequestResultDto meisaiRequestResultDto) {
		this.commonTransitionMeisaiResult.put(new Integer(windowId), meisaiRequestResultDto);
	}

//add 2019/07/16 USI張 #34 begin
	/**
	 * 共通画面遷移時保持明細結果を取得する
 	 * @param windowId ウィンドウID
	 * @return commonTransitionUriageTaxMeisaiResult 売上金管理月報売上、消費税の明細照会・結果DTO
	 */
	public UriageTaxMeisaiRequestResultDto getCommonTransitionUriageTaxMeisaiResult(int windowId) {
		return (UriageTaxMeisaiRequestResultDto) commonTransitionUriageTaxMeisaiResult.get(new Integer(windowId));
	}
//add 2019/07/16 USI張 #34 end

//add 2019/07/16 USI張 #34 begin
	/**
	 * 	 * 共通画面遷移時保持明細結果を設定する
	 * @param windowId ウィンドウID
	 * @param commonTransitionUriageTaxMeisaiResult 売上金管理月報売上、消費税の明細照会・結果DTO
	 */
	public void setCommonTransitionUriageTaxMeisaiResult(int windowId, UriageTaxMeisaiRequestResultDto uriageTaxMeisaiRequestResultDto) {
		this.commonTransitionUriageTaxMeisaiResult.put(new Integer(windowId), uriageTaxMeisaiRequestResultDto);
	}
//add 2019/07/16 USI張 #34 end

}