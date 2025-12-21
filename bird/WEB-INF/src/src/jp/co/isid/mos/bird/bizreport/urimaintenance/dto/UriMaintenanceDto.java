package jp.co.isid.mos.bird.bizreport.urimaintenance.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.SyukeiKbnResultData;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIUriMaintenanceResult;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIMeisaiKeigenTaxInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;

/**
 * 売上修正DTO
 * @author
 */
public class UriMaintenanceDto{


    /**
     * 会社プルダウンリスト
     */
    private List listCompany;


    /**
     * 対象年月プルダウンリスト
     */
    private List listTargetYm;


    /**
     * アプリ日付(YYYY/MM)
     * ※対象年月プルダウン初期値
     */
    private String appMonth;


    /**
     * 会社コード
     */
    private String condCompanyCd;


    /**
     * 会社名称
     */
    private String condCompanyNm;


    /**
     * 店舗コード
     */
    private String condMiseCd;


    /**
     * 店舗名称
     */
    private String condMiseNm;


    /**
     * 対象年月
     */
    private String condTargetYm;


    /**
     * ユーザID
     */
    private String userId;


    /**
     * アプリ日付
     */
    private String appDate;


    /**
     * システム日付
     */
    private String sysDate;


    /**
     * タブインデックス
     */
    private int viewIndex;


    /**
     * タブインデックス
     */
    private int subIndex;


    /**
     * 売上情報リスト
     */
    private HashMap listUri = new HashMap();


    /**
     * 売上情報リスト(元データ比較用)
     */
    private List listUriTmp;


    /**
     * 売上情報リスト(修正前比較用)
     */
    private List listUriPre;


    /**
     * 編集モードフラグ
     */
    private boolean editMode;


    /**
     * ヘッダー情報
     */
    private UriMainteHeader header;

    /**
     * 会計区分編集画面遷移時のメッセージ
     */
    private static final String MSG_BACK_KKBN_ALERT = "決定前のデータはクリアされます。よろしいですか？";
    /**
     * 会計区分編集画面遷移時のメッセージ
     */
    public String getMsgKKbnBackAlert() {
        return MSG_BACK_KKBN_ALERT;
    }
    public void setMsgKKbnBackAlert(String msg) {
    }
    /**
     * List[[集計区分情報]]
     * 作成日:2012/07/26
     * 構成：[jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn]
     */
    private List listSyukeiKbn = new ArrayList(0);
    /**
     * List[[会計区分情報]]
     * 作成日:2012/07/26
     * 構成：[jp.co.isid.mos.bird.common.kaikei.entity.CtlKaikeiKbnInfo]
     */
    private List listKaikeiKbn = new ArrayList(0);
    /**
     * List[[会計区分別在高日次修正]]
     * 作成日:2012/07/26
     * 構成：[jp.co.isid.mos.bird.common.kaikei.entity.TrnSyuseiAridaka]
     */
    private List listKKbnSearchData = new ArrayList(0);
    /**
     * Map[集計区分別会計区分マスタ情報]
     * 作成日:2012/07/26
     * 構成：{KEY:集計区分コード, jp.co.isid.mos.bird.common.entity.UILists}
     */
    private Map mapSyukeiKbnMstData = new HashMap();
    /**
     * Map[集計区分別タブ別結果Map情報]
     * 作成日:2012/07/26
     * 構成：キー：集計区分コード 値：SyukeiKbnData.Map[タブ別結果][UIListDataHeader]
     */
    private Map mapSKbnResultData  = new HashMap();
    /**
     * 売上と消費税修正リンク表示フラグ
     */
    private String uriageMeisaiDispFlg;
  //add 2019/07/09 USI欒 #34 begin
    /**
     * Map[現金在高（日次）売上消費税明細情報リスト]
     * 作成日:2019/07/09
     */
    private List listGetKeigenTaxData = new ArrayList(0);
    /**
    * 現金在高（日次）売上消費税明細情報リストのサイズ
    * 作成日:2019/07/09
    */
    private int listGetKeigenTaxDataSize;
	/**
     * Map[現金在高（日次）売上消費税明細情報合計]
     * 作成日:2019/07/09
     */
    private UIMeisaiKeigenTaxInfo uiMeisaiKeigenTaxInfo = new UIMeisaiKeigenTaxInfo();
 //add 2019/07/09 USI欒 #34 end

	/**
     * 集計区分
     * 作成日:2012/07/26
     */
    private String syukeiKbn;
    /**
     * 集計区分名称
     * 作成日:2012/07/26
     */
    private String syukeiKbnName;
    /** 集計会計区分タブNo */
    private String skTabNo;

    /**
     * 集計会計区分タブNo取得処理
	 * @return クラス変数skTabNo を戻します。
	 */
	public String getSkTabNo() {
		return skTabNo;
	}


	/**
	 * @param skTabNo を クラス変数skTabNoへ設定します。
	 */
	public void setSkTabNo(String skTabNo) {
		this.skTabNo = skTabNo;
	}


	/**
     * 初期化
     *
     * 更新日:2012/07/27 ADD 会計区分追加対応
     */
    public void doClear() {
        setCondCompanyCd("");
        setCondCompanyNm("");
        setCondMiseCd("");
        setCondMiseNm("");
        setCondTargetYm("");
        setAppMonth("");
        clearRegistData();
    }
	/**
     * 登録情報初期化
     *
     * 更新日:2012/07/27 ADD 会計区分追加対応
     */
    public void clearRegistData() {
    	setSessioniKey(null);
    	setViewSessionKey(null);
        setListUri(null);
        setListUriTmp(null);
        setListUriPre(null);
        setListUriSize(0);
        setViewIndex(0);
        setSubIndex(0);
        setEditMode(false);

        //2012/07/27 追加
        setListKKbnSearchData(new ArrayList(0));
        setListSyukeiKbn(new ArrayList(0));
        setListKaikeiKbn(new ArrayList(0));
        setMapSyukeiKbnMstData(new HashMap());
        setMapSKbnResultData(new HashMap());
        setSkTabNo("01");
//add 2019/07/09 USI欒 #34 begin
        setListGetKeigenTaxData(new ArrayList(0));
        setUiMeisaiKeigenTaxInfo(null);
//add 2019/07/09 USI欒 #34 end
    }


    public List getListCompany() {
        return listCompany;
    }
    public void setListCompany(List listCompany) {
        this.listCompany = listCompany;
    }

    public List getListTargetYm() {
        return listTargetYm;
    }
    public void setListTargetYm(List listTargetYm) {
        this.listTargetYm = listTargetYm;
    }

    public String getAppMonth() {
        return appMonth;
    }
    public void setAppMonth(String appMonth) {
        this.appMonth = appMonth;
    }

    public String getCondCompanyCd() {
        return condCompanyCd;
    }
    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }
    public String getCondMiseCd() {
        return condMiseCd;
    }
    public void setCondMiseCd(String condMiseCd) {
        this.condMiseCd = condMiseCd;
    }
    public String getCondMiseNm() {
        return condMiseNm;
    }
    public void setCondMiseNm(String condMiseNm) {
        this.condMiseNm = condMiseNm;
    }
    public String getCondTargetYm() {
        return condTargetYm;
    }
    public void setCondTargetYm(String targetYm) {
        this.condTargetYm = targetYm;
    }
    public String getCondCompanyNm() {
        return condCompanyNm;
    }
    public void setCondCompanyNm(String condCompanyNm) {
        this.condCompanyNm = condCompanyNm;
    }

    public String getAppDate() {
        return appDate;
    }
    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }
    public String getSysDate() {
        return sysDate;
    }
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getViewIndex() {
        return viewIndex;
    }
    public void setViewIndex(int viewIndex) {
        this.viewIndex = viewIndex;
    }
    public int getSubIndex() {
        return subIndex;
    }
    public void setSubIndex(int subIndex) {
        this.subIndex = subIndex;
    }

    public List getListUri() {
        return (List)listUri.get(new Integer(getWindowId()));
    }
    public void setListUri(List listUri) {
    	this.listUri = new HashMap();
        this.listUri.put(new Integer(getWindowId()), listUri);
    }

    public List getListUriTmp() {
        return listUriTmp;
    }
    public void setListUriTmp(List listUriTmp) {
        this.listUriTmp = listUriTmp;
    }
    public List getListUriPre() {
        return listUriPre;
    }
    public void setListUriPre(List listUriPre) {
        this.listUriPre = listUriPre;
    }


    /**
     * 売上情報リストの件数を返す
     * @return
     */
    public int getListUriSize() {
        int size = 0;
        if(getListUri()!=null) {
            size =getListUri().size();
        }
        return size;
    }
    public void setListUriSize(int listUriSize) {
    }

    /**
     * 編集画面遷移時のメッセージ
     */
    private static final String MSG_BACK_ALERT = "編集中のデータはクリアされます。よろしいですか？";

    /**
     * 編集画面遷移時のメッセージ
     */
    public String getMsgBackAlert() {
        return MSG_BACK_ALERT;
    }
    public void setMsgBackAlert(String msg) {
    }

    /**
     * ヘッダー情報を取得します
     * @return
     */
    public UriMainteHeader getHeader() {
        return header;
    }
    /**
     * ヘッダー情報を設定します
     * @param header
     */
    public void setHeader(UriMainteHeader header) {
        this.header = header;
    }


    /**
     * 編集モードフラグを取得する
     * @return
     */
    public boolean isEditMode() {
        return editMode;
    }
    /**
     * 編集モードフラグを設定する
     * @param editMode
     */
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    /***************セッションキー作成*********************/

    /* セッションKey */
    private String viewSessionKey;
    /* セッションKey */
    private String sessionKey;
    /**
     * ウィンドウID
     */
    private int windowId = 0;
    /**
     * 最大ウィンドウID
     */
    private int maxWindowId = 0;


    ////////////////
    //　複数Window対応
    // セッションキー作成
    ////////////////
    /**
     * viewSessionKey取得
     */
    public String getViewSessionKey() {
        return this.viewSessionKey;
    }

    /**
     * viewSessionKey取得
     * @param viewSessionKey
     */
    public void setViewSessionKey( String viewSessionKey ) {
        this.viewSessionKey = viewSessionKey;
    }

    /**
     * sessionKey取得
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * sessionKey取得
     * @param newKey
     */
    public void setSessioniKey( String newKey ) {
    	sessionKey = newKey;
    }

    public int getWindowId() {
        return windowId;
    }
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }
    public int getMaxWindowId() {
        return maxWindowId;
    }
    public void setMaxWindowId(int maxWindowId) {
        this.maxWindowId = maxWindowId;
    }

    // ウィンドウID生成
    public int createWindowId() {
        int newWindowId = getMaxWindowId() + 1;
        setMaxWindowId(newWindowId);
        return newWindowId;
    }
    public void updateWindowid() {
        setWindowId(createWindowId());
    }

    /**
     * 合計行のEntityを取得
     */
    public UIUriMainteWorkInfo getTotalLineEntity() {
        UIUriMainteWorkInfo entity = null;
        for (int i=0; i<getListUriSize(); i++) {
            entity = (UIUriMainteWorkInfo) getListUri().get(i);
            if (entity.isTotalFlg()) {
                break;
            }
        }
        return entity;
    }


	/**
	 * @return クラス変数listKaikeiKbn を戻します。
	 */
	public List getListKaikeiKbn() {
		return listKaikeiKbn;
	}


	/**
	 * @param listKaikeiKbn を クラス変数listKaikeiKbnへ設定します。
	 */
	public void setListKaikeiKbn(List listKaikeiKbn) {
		this.listKaikeiKbn = listKaikeiKbn;
	}


	/**
	 * @return クラス変数listSyukeiKbn を戻します。
	 */
	public List getListSyukeiKbn() {
		return listSyukeiKbn;
	}


	/**
	 * @param listSyukeiKbn を クラス変数listSyukeiKbnへ設定します。
	 */
	public void setListSyukeiKbn(List listSyukeiKbn) {
		this.listSyukeiKbn = listSyukeiKbn;
	}

	/**
	 * @return クラス変数syukeiKbn を戻します。
	 */
	public String getSyukeiKbn() {
		return syukeiKbn;
	}


	/**
	 * @param syukeiKbn を クラス変数syukeiKbnへ設定します。
	 */
	public void setSyukeiKbn(String syukeiKbn) {
		this.syukeiKbn = syukeiKbn;
	}


	/**
	 * @return クラス変数syukeiKbnName を戻します。
	 */
	public String getSyukeiKbnName() {
		return syukeiKbnName;
	}


	/**
	 * @param syukeiKbnName を クラス変数syukeiKbnNameへ設定します。
	 */
	public void setSyukeiKbnName(String syukeiKbnName) {
		this.syukeiKbnName = syukeiKbnName;
	}


	/**
	 *  Map[集計区分別会計区分結果情報]取得処理
	 * @return クラス変数mapSKbnResultData を戻します。
	 */
	public Map getMapSKbnResultData() {
		return (Map)mapSKbnResultData.get(new Integer(getWindowId()));
	}


	/**
	 * Map[集計区分別会計区分結果情報]設定処理
	 * @param resultData を クラス変数mapSKbnResultDataへ設定します。
	 */
	public void setMapSKbnResultData(Map resultData) {
		this.mapSKbnResultData = new HashMap();
		this.mapSKbnResultData.put(new Integer(getWindowId()), resultData);
	}


	/**
	 * Map[集計区分別会計区分マスタ情報]取得処理
	 *
	 * @return クラス変数mapSyukeiKbnMstData を戻します。
	 */
	public Map getMapSyukeiKbnMstData() {
		return mapSyukeiKbnMstData;
	}


	/**
	 * Map[集計区分別会計区分マスタ情報]設定処理
	 * @param mapMstData を クラス変数mapSyukeiKbnMstDataへ設定します。
	 */
	public void setMapSyukeiKbnMstData(Map mapMstData) {
		this.mapSyukeiKbnMstData = mapMstData;
	}
	/**
	 * Map[タブ別結果情報]設定処理
	 *
	 * @param resultData
	 */
	public void setSyukeiKbnResultData(SyukeiKbnResultData resultData) {
		if(getMapSKbnResultData() != null) {
			getMapSKbnResultData().put(getSyukeiKbn(), resultData);
		}
	}
	/**
	 * 集計区分結果情報保持エンティティ
	 *
	 * SyukeiKbnResultData[タブ別結果情報]取得処理
	 * @return jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.SyukeiKbnResultData
	 */
	public SyukeiKbnResultData getSyukeiKbnResultData() {
		if(getMapSKbnResultData() != null) {
			return (SyukeiKbnResultData)getMapSKbnResultData().get((getSyukeiKbn()));
		}
		return null;
	}
	/**
	 * UIUriMaintenanceResult[タブ情報]取得処理
	 * @return
	 */
	public UIUriMaintenanceResult getResultData() {
		if(getSyukeiKbnResultData() == null) {
			return null;
		}
		return (UIUriMaintenanceResult) getSyukeiKbnResultData().getMapTabResult().get(getSkTabNo());
	}
	/**
	 * UIUriMaintenanceResult[タブ情報]取得処理
	 * @return
	 */
	public void setResultData(UIUriMaintenanceResult resutlData) {
		if(getSyukeiKbnResultData() != null) {
			getSyukeiKbnResultData().getMapTabResult().put(getSkTabNo(), resutlData);
		}
	}


	/**
     * List[[会計区分別在高日次修正]]取得
     * 作成日:2012/07/26
     * 構成：[jp.co.isid.mos.bird.common.kaikei.entity.TrnSyuseiAridaka]
	 * @return クラス変数listKKbnSearchData を戻します。
	 */
	public List getListKKbnSearchData() {
		return listKKbnSearchData;
	}

	/**
     * List[[会計区分別在高日次修正]]設定
     * 作成日:2012/07/26
     * 構成：[jp.co.isid.mos.bird.common.kaikei.entity.TrnSyuseiAridaka]
	 * @param list を クラス変数listKKbnSearchDataへ設定します。
	 */
	public void setListKKbnSearchData(List list) {
		this.listKKbnSearchData = list;
	}
//add 2019/07/09 USI欒 #34 begin
	/**
     * List[[現金在高（日次）売上消費税明細]]取得
     * 作成日:2019/07/09
	 * @return クラス変数listGetKeigenTaxData を戻します。
	 */
    public List getListGetKeigenTaxData() {
		return listGetKeigenTaxData;
	}
	/**
     * List[[現金在高（日次）売上消費税明細]]設定
     * 作成日:2019/07/09
	 * @param listGetKeigenTaxData を クラス変数listGetKeigenTaxDataへ設定します。
	 */
	public void setListGetKeigenTaxData(List listGetKeigenTaxData) {
		this.listGetKeigenTaxData = listGetKeigenTaxData;
	}

	/**
     * [現金在高（日次）売上消費税明細]合計情報取得
     * 作成日:2019/07/09
	 * @return クラス変数uiMeisaiKeigenTaxInfo を戻します。
	 */
	public UIMeisaiKeigenTaxInfo getUiMeisaiKeigenTaxInfo() {
		return uiMeisaiKeigenTaxInfo;
	}
	/**
     * [現金在高（日次）売上消費税明細]合計情報取得
     * 作成日:2019/07/09
	 * @param uiMeisaiKeigenTaxInfo を クラス変数uiMeisaiKeigenTaxInfoへ設定します。
	 */
	public void setUiMeisaiKeigenTaxInfo(UIMeisaiKeigenTaxInfo uiMeisaiKeigenTaxInfo) {
		this.uiMeisaiKeigenTaxInfo = uiMeisaiKeigenTaxInfo;
	}
	/**
     * [現金在高（日次）売上消費税明細]合計情報取得リストのサイズ
     * 作成日:2019/07/09
	 * @return クラス変数listGetKeigenTaxDataSize を戻します。
	 */
    public int getListGetKeigenTaxDataSize() {
		return listGetKeigenTaxDataSize;
	}
	/**
     * [現金在高（日次）売上消費税明細]合計情報リストのサイズ取得
     * 作成日:2019/07/09
	 * @return クラス変数listGetKeigenTaxDataSize を戻します。
	 */
	public void setListGetKeigenTaxDataSize(int listGetKeigenTaxDataSize) {
		this.listGetKeigenTaxDataSize = listGetKeigenTaxDataSize;
	}
    /**
     * 売上と消費税明細リンク表示フラグを戻します。
     * @return uriageMeisaiDispFlg 売上と消費税明細リンク表示フラグ
     */
	public String getUriageMeisaiDispFlg() {
		return uriageMeisaiDispFlg;
	}

    /**
     * 売上と消費税明細リンク表示フラグを設定します。
     * @param uriageMeisaiDispFlg 売上と消費税明細リンク表示フラグ
     */
	public void setUriageMeisaiDispFlg(String uriageMeisaiDispFlg) {
		this.uriageMeisaiDispFlg = uriageMeisaiDispFlg;
	}
//add 2019/07/09 USI欒 #34 end

}
