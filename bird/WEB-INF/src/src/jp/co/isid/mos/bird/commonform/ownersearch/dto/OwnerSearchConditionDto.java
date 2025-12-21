/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.ownersearch.dto;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * オーナ検索処理Dto
 * @author itamoto
 */
public class OwnerSearchConditionDto {

    /* 会社コード */
    private Map kaisyaCd = new HashMap();
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
    /* SV名称 */
    private Map svNameKj = new HashMap();
    /* SVコード */
    private Map svCd = new HashMap();
    /* 検索結果オーナリスト */
    private Map onerSearchList = new LinkedHashMap();
    /* 検索結果表示件数 */
    private Map resultDispSize = new HashMap();
    /* 会社リスト */
    private Map companyList = new HashMap();
    /* 支部リスト */
    private Map sibuList = new HashMap();
// add start xkhata 2006/05/16 条件追加対応
    /* ソートタイプ */
    private Map sortSeq = new HashMap();
    /* 契約期間を含む */
    private Map inEnd = new HashMap();
// end
    /* 50音検索キーリスト */
    private Map indexSearchList = new HashMap();

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
     * 検索結果リスト設定処理
     * @return onerSearchList
     */
    public List getOnerSearchList() {
        return (List) onerSearchList.get(new Integer(getWindowId()));
    }
    /**
     * 検索結果リスト設定処理
     * @param onerSearchList
     */
    public void setOnerSearchList(List onerSearchList) {
        // 現在ウインドウIDのデータを保持しているか判定
        Integer windowId = new Integer(getWindowId());
        if (this.onerSearchList.containsKey(windowId)) {
            // 順番を入れ替える為、前回データを削除しておく
            this.onerSearchList.remove(windowId);

        // 最大数を超える
        } else if (this.onerSearchList.size() > 0 && this.onerSearchList.size() >= getMaxSize()) {
            // 最古データを削除
            this.onerSearchList.remove(this.onerSearchList.keySet().toArray()[0]);
        }
        // set
        this.onerSearchList.put(windowId, onerSearchList);
    }
    /**
     * 検索結果リスト存在判定
     * @return 判定結果
     */
    public boolean isExistOnerSearchList() {
        return onerSearchList != null && onerSearchList.containsKey(new Integer(getWindowId()));
    }
    /**
     * 検索結果リストサイズ設定処理
     * @return onerSearchListSize
     */
    public int getOnerSearchListSize() {
        return (getOnerSearchList() == null) ? 0 : getOnerSearchList().size();
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
  
// add start xkhata 2006/05/16 条件追加対応
    /**
     * ソート順取得
     * @return
     */
    public String getSortSeq() {
        return (String) sortSeq.get(new Integer(getWindowId()));
    }
    
    /**
     * ソート順設定
     * @param sortSeq
     */
    public void setSortSeq( String sortSeq ) {
        this.sortSeq.put(new Integer(getWindowId()), sortSeq);
    }
    
    /**
     * 契約期間を含むを取得
     * @return
     */
    public String getInEnd() {
        return (String) inEnd.get(new Integer(getWindowId()));
    }
    
    /**
     * 契約期間を含むを設定
     * @param inEnd
     */
    public void setInEnd( String inEnd ) {
        this.inEnd.put(new Integer(getWindowId()), inEnd);
    }
// end
    /**
     * 検索情報クリア処理<br>
     * （パラメータfalse設定時、会社コード、会社リスト、支部リストはクリアしない）
     * @param flag
     */
    public void clear(boolean flag) {
        if (flag) {
            setKaisyaCd(null);
            setCompanyList(null);
            setSibuList(null);
        }
        setSibuCd(null);
        setMiseCd(null);
        setMiseNameKj(null);
        setOnerCd(null);
        setOnerNameKj(null);
        setSvCd(null);
        setSvNameKj(null);
        setOnerSearchList(null);
// add start xkhata 2006/05/16 条件追加対応
        setSortSeq( null );
        setInEnd( null );
// end
        setResultDispSize(0);
    }
    
    /**
     * SVコード取得
     * @return svCd
     */
	public String getSvCd() {
        return (String) svCd.get(new Integer(getWindowId()));
	}
    /**
     * SVコード設定
     * @return svCd
     */
	public void setSvCd(String svCd) {
        this.svCd.put(new Integer(getWindowId()), svCd);
	}
    /**
     * SV名称取得
     * @return svNameKj
     */
	public String getSvNameKj() {
        return (String) svNameKj.get(new Integer(getWindowId()));
	}
    /**
     * SV名称設定
     * @return svNameKj
     */
	public void setSvNameKj(String svNameKj) {
        this.svNameKj.put(new Integer(getWindowId()), svNameKj);
	}
    /**
     * 50音検索キーリスト取得
     * @return 50音検索キーリスト
     */
    public List getIndexSearchList() {
        return (List) indexSearchList.get(new Integer(getWindowId()));
    }
    /**
     * 50音検索キーリスト設定
     * @param indexSearchList 50音検索キーリスト
     */
    public void setIndexSearchList(List indexSearchList) {
        this.indexSearchList.put(new Integer(getWindowId()), indexSearchList);
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
