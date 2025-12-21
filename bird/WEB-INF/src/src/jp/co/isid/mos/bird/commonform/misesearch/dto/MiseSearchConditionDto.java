/*
 * 作成日: 2005/12/22
 *
 */
package jp.co.isid.mos.bird.commonform.misesearch.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xyuchida
 *
 */
public class MiseSearchConditionDto {

    private Map rCompanyCd = new HashMap();
    private Map sibuCd = new HashMap();
    private Map onerCd = new HashMap();
    private Map onerNameKj = new HashMap();
    private Map miseCd = new HashMap();
    private Map miseNameKj = new HashMap();
    private Map indexSearchKey = new HashMap();
// add start xkhata 2006/05/18
    private Map sortSeq = new HashMap();
    private Map inClose = new HashMap();
    private Map inNaviClose = new HashMap();
// end

    private Map miseSearchList = new LinkedHashMap();
    private Map companyList = new HashMap();
    private Map sibuList = new HashMap();

    /* 検索結果表示件数 */
    private Map resultDispSize = new HashMap();

    /* ウインドウID */
    private int windowId;
    /* 最大ウインドウID */
    private int maxWindowId;
    private int maxSize;
    /* 呼出元画面ウインドウID */
    private Map parentViewWindowId = new HashMap();
    /* 呼出元画面ウインドウID */
    private Map returnWindowId = new HashMap();
    /* 遷移元ページ */
    private Map navigationCase = new HashMap();
    /* 遷移区分要否フラグ */
    private Map needReturnKind = new HashMap();

    public String getRCompanyCd() {
        return (String) rCompanyCd.get(new Integer(getWindowId()));
	}

	public void setRCompanyCd(String rCompanyCd) {
        this.rCompanyCd.put(new Integer(getWindowId()), rCompanyCd);
	}

	public String getSibuCd() {
        return (String) sibuCd.get(new Integer(getWindowId()));
	}

	public void setSibuCd(String sibuCd) {
        this.sibuCd.put(new Integer(getWindowId()), sibuCd);
	}

	public String getOnerCd() {
        return (String) onerCd.get(new Integer(getWindowId()));
	}

	public void setOnerCd(String onerCd) {
        this.onerCd.put(new Integer(getWindowId()), onerCd);
	}

	public String getOnerNameKj() {
        return (String) onerNameKj.get(new Integer(getWindowId()));
	}

	public void setOnerNameKj(String onerNameKj) {
        this.onerNameKj.put(new Integer(getWindowId()), onerNameKj);
	}

	public String getMiseCd() {
        return (String) miseCd.get(new Integer(getWindowId()));
	}

	public void setMiseCd(String miseCd) {
        this.miseCd.put(new Integer(getWindowId()), miseCd);
	}

	public String getMiseNameKj() {
        return (String) miseNameKj.get(new Integer(getWindowId()));
	}

	public void setMiseNameKj(String miseNameKj) {
        this.miseNameKj.put(new Integer(getWindowId()), miseNameKj);
	}

	public int getIndexSearchKey() {
        return ((Integer) indexSearchKey.get(new Integer(getWindowId()))).intValue();
	}

	public void setIndexSearchKey(int indexSearchKey) {
        this.indexSearchKey.put(new Integer(getWindowId()), new Integer(indexSearchKey));
	}
    
    public String getSortSeq() {
        return (String) sortSeq.get(new Integer(getWindowId()));
    }
    public void setSortSeq( String sortSeq ) {
        this.sortSeq.put(new Integer(getWindowId()), sortSeq);
    }
    
    public String getInClose() {
        return (String) inClose.get(new Integer(getWindowId()));
    }
    public void setInClose( String inClose ) {
        this.inClose.put(new Integer(getWindowId()), inClose);
    }

    public String getInNaviClose() {
        return (String) inNaviClose.get(new Integer(getWindowId()));
    }
    public void setInNaviClose( String inNaviClose ) {
        this.inNaviClose.put(new Integer(getWindowId()), inNaviClose);
    }

    public List getMiseSearchList() {
        return (List) miseSearchList.get(new Integer(getWindowId()));
    }

    public void setMiseSearchList(List miseSearchList) {
        // 現在ウインドウIDのデータを保持しているか判定
        Integer windowId = new Integer(getWindowId());
        if (this.miseSearchList.containsKey(windowId)) {
            // 順番を入れ替える為、前回データを削除しておく
            this.miseSearchList.remove(windowId);

        // 最大数を超える
        } else if (this.miseSearchList.size() > 0 && this.miseSearchList.size() >= getMaxSize()) {
            // 最古データを削除
            this.miseSearchList.remove(this.miseSearchList.keySet().toArray()[0]);
        }
        // set
        this.miseSearchList.put(windowId, miseSearchList);
    }

    public boolean isExistMiseSearchList() {
        return miseSearchList != null && miseSearchList.containsKey(new Integer(getWindowId()));
    }

    public int getMiseSearchListSize() {
        return (getMiseSearchList() == null) ? 0 : getMiseSearchList().size();
    }

    public List getCompanyList() {
        return (List) companyList.get(new Integer(getWindowId()));
    }

    public void setCompanyList(List companyList) {
        this.companyList.put(new Integer(getWindowId()), companyList);
    }

    public List getSibuList() {
        return (List) sibuList.get(new Integer(getWindowId()));
    }

    public void setSibuList(List sibuList) {
        this.sibuList.put(new Integer(getWindowId()), sibuList);
    }

    public void clear() {

        setRCompanyCd(null);
        setSibuCd(null);
        setOnerCd(null);
        setOnerNameKj(null);
        setMiseCd(null);
        setMiseNameKj(null);
        setIndexSearchKey(-1);
        setSortSeq(null);
        setInClose(null);
        
        setMiseSearchList(null);
        setCompanyList(null);
        setSibuList(null);

        setResultDispSize(0);
        setParentViewWindowId(0);
        setReturnWindowId(null);
        setNavigationCase(null);
        setNeedReturnKind(false);
    }

    /**
     * 検索結果表示件数取得
     * @return 検索結果表示件数
     */
    public int getResultDispSize() {
        return ((Integer) resultDispSize.get(new Integer(getWindowId()))).intValue();
    }
    /**
     * 検索結果表示件数設定
     * @param resultDispSize 検索結果表示件数
     */
    public void setResultDispSize(int resultDispSize) {
        this.resultDispSize.put(new Integer(getWindowId()), new Integer(resultDispSize));
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
     * 呼出元画面ウインドウID取得
     * @return
     */
    public String getReturnWindowId() {
        return (String) returnWindowId.get(new Integer(getWindowId()));
    }
    /**
     * 呼出元画面ウインドウID設定
     * @param parentViewWindowId
     */
    public void setReturnWindowId(String returnWindowId) {
        this.returnWindowId.put(new Integer(getWindowId()), returnWindowId);
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
