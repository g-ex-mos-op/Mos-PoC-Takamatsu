package jp.co.isid.mos.bird.commonform.svsearchnew.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * SV検索処理Dto
 * 
 * @author kusama
 */
public class SvSearchConditionDto {

    /* 会社コード */
    private Map companyCd = new HashMap();
    /* 支部コード */
    private Map sibuCd = new HashMap();
    /* オーナ名 */
    private Map onerNameKj = new HashMap();
    /* オーナコード */
    private Map onerCd = new HashMap();
    /* 店名 */
    private Map miseNameKj = new HashMap();
    /* 店コード */
    private Map miseCd = new HashMap();
    /* 検索結果SVコード */
    private Map resultSvCd = new HashMap();
    /* 会社リスト */
    private Map companyList = new LinkedHashMap();
    /* sibuList */
    private Map sibuList = new LinkedHashMap();;
    /* ソートタイプ */
    private Map sortSeq = new HashMap();;
    /* ウインドウID */
    private int windowId;
    /* 最大ウインドウID */
    private int maxWindowId;
    private int maxSize;
    /* 呼出元画面ウインドウID */
    private Map parentViewWindowId = new HashMap();
    /* 遷移元ページ */
    private Map navigationCase = new HashMap();

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
    }
    
    /**
     * 会社コード設定処理
     * @return companyCd
     */
    public String getCompanyCd() {
		return (String) companyCd.get(new Integer(getWindowId()));
	}
    /**
     * 会社コード設定処理
     * @param companyCd
     */
	public void setCompanyCd(String companyCd) {
		this.companyCd.put(new Integer(getWindowId()), companyCd);
	}
    /**
     * 支部コード設定処理
     * @return sibuCd
     */
    public String getSibuCd() {
        return (String) sibuCd.get(new Integer(getWindowId()));
    }
    /**
     * 支部コード設定処理
     * @param sibuCd
     */
    public void setSibuCd(String sibuCd) {
        this.sibuCd.put(new Integer(getWindowId()), sibuCd);
    }
    /**
     * 店コード設定処理11
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
		this.miseCd.put(new Integer(getWindowId()), miseCd);
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
     * オーナコード設定処理
     * @return onerCd
     */
	public String getOnerCd() {
		return (String) onerCd.get(new Integer(getWindowId()));
	}
    /**
     * オーナコード設定処理
     * @param onerCd
     */
	public void setOnerCd(String onerCd) {
		this.onerCd.put(new Integer(getWindowId()), onerCd);
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
     * 結果会社コード設定処理
     * @return resultSvCd
     */
	public String getResultSvCd() {
		return (String) resultSvCd.get(new Integer(getWindowId()));
	}
    /**
     * 結果会社コード設定処理
     * @param resultSvCd
     */
	public void setResultSvCd(String resultSvCd) {
		this.resultSvCd.put(new Integer(getWindowId()), resultSvCd);
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
     * 支部リスト設定
     * @return sibuList
     */
    public List getSibuList() {
        return (List) sibuList.get(new Integer(getWindowId()));
    }
    /**
     * 支部リスト設定
     * @param sibuList
     */
    public void setSibuList(List sibuList) {
        this.sibuList.put(new Integer(getWindowId()), sibuList);
    }
  
    /**
     * ソート順取得
     * @return
     */
    public String getSortSeq() {
        return (String) this.sortSeq.get(new Integer(getWindowId()));
    }
    
    /**
     * ソート順設定
     * @param sortSeq
     */
    public void setSortSeq(String sortSeq) {
        this.sortSeq.put(new Integer(getWindowId()), sortSeq);
    }
    
    /**
     * 検索情報クリア処理<br>
     * （パラメータfalse設定時、会社コード、会社リスト、支部リストはクリアしない）
     * @param flag
     */
    public void clearCondition(boolean flag) {
        if (flag) {
            setCompanyCd(null);
            setCompanyList(null);
            setSibuList(null);
        }
        setSibuCd(null);
        setMiseCd(null);
        setMiseNameKj(null);
        setOnerCd(null);
        setOnerNameKj(null);
        setResultSvCd(null);
        setSortSeq( null );
    }
    
    /**
     * 検索情報クリア処理<br>
     * @param flag
     */
    public void clear() {
        setCompanyCd(null);
        setCompanyList(null);
        setSibuList(null);
        setSibuCd(null);
        setMiseCd(null);
        setMiseNameKj(null);
        setOnerCd(null);
        setOnerNameKj(null);
        setResultSvCd(null);
        setSortSeq( null );

        setParentViewWindowId(0);
        setNavigationCase(null);
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
}