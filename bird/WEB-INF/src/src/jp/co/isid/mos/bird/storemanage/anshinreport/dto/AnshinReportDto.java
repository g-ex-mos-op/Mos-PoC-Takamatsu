package jp.co.isid.mos.bird.storemanage.anshinreport.dto;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;


/**
 * あんしん点検結果報告画面DTO
 * 作成日：2012/01/18
 * @author Y.KAWASHIMA
 */
public class AnshinReportDto implements DownloadDto {
    /* VIEW_ID */
    public final String VIEW_ID				= "BSM018";
    public final String condition_VIEW_ID		= "BSM018V01";
    public final String result_VIEW_ID			= "BSM018V01";
    public final String onerSearch_VIEW_ID		= "BCO006V01";
    public final String miseSearch_VIEW_ID		= "BCO008V01";
    
    /* 条件画面表示モード 非表示:0、表示:1 */
    private int selectViewMode;

    /* 会社コード */
    private String companyCd;
    
    /* 会社名 */
    private String companyName;
    
    /*ユーザーID */
    private String userId;

    /* 本部コード */
    private String sitenCd;
    
    /* オーナーコード */
    private String onerCd;
    /* オーナー名称 */
    private String onerName;
    
    /* 店舗コード */
    private String miseCd;
    /* 店舗名称 */
    private String miseName;
    
    /* 対象条件 */
    private String taishoJoken;
    
    /* 対象条件リスト */
    private List taishoJokenList;
    
     /* 実施年度 */
    private String jissiNendo;
    
    /* 年度リスト */
    private List taishoNendo;
    
    /* 	ユーザタイプ */
    private String usertypeCd;
    
    /* 結果リスト */
    private List resultList;

    /* 店リスト */
    private List miseList;

    /* オーナーリスト */
    private List onerList;
    
    /* システム日付 */
    private String sysDate;
    
    /* ダウンロード対象店舗 index */
    private int resultIndex;
    
    /*ダウンロード対象データリストindex*/
    private int listIndex;

    /*再検索フラグ*/
    private String reExecuteFlg;
    /*会社リスト*/
    List companyList;
/**
	 * 条件画面表示モードの設定
	 * @return selectViewMode を戻します。
	 */
	public int getSelectViewMode() {
		return selectViewMode;
	}
	/**
	 * 条件画面表示モードの設定
	 * @param selectViewMode selectViewMode を設定。
	 */
	public void setSelectViewMode(int selectViewMode) {
		this.selectViewMode = selectViewMode;
	}
	
 	/**
	 * @return miseList を戻します。
	 */
	public List getMiseList() {
		return miseList;
	}
	/**
	 * @param miseList miseList を設定。
	 */
	public void setMiseList(List miseList) {
		this.miseList = miseList;
	}
	/**
	 * @return usertypeCd を戻します。
	 */
	public String getUsertypeCd() {
		return usertypeCd;
	}
	
	/**
	 * @return resultList を戻します。
	 */
	public List getResultList() {
		return resultList;
	}
	/**
	 * @param resultList resultList を設定。
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	/**
	 * @param usertypeCd usertypeCd を設定。
	 */
	public void setUsertypeCd(String usertypeCd) {
		this.usertypeCd = usertypeCd;
	}
	/**
	 * @return sitenCd を戻します。
	 */
	public String getSitenCd() {
		return sitenCd;
	}
	/**
	 * @param sitenCd sitenCd を設定。
	 */
	public void setSitenCd(String sitenCd) {
		this.sitenCd = sitenCd;
	}
	/**
	 * @return miseCd を戻します。
	 */
	public String getMiseCd() {
		return miseCd;
	}
	/**
	 * @param miseCd miseCd を設定。
	 */
	public void setMiseCd(String miseCd) {
		this.miseCd = miseCd;
	}
	/**
     * @return miseName を戻します。
     */
    public String getMiseName() {
        return miseName;
    }
    /**
     * @param miseName を設定します。
     */
    public void setMiseName(String miseName) {
        this.miseName = miseName;
    }
    /**
	 * @return onerCd を戻します。
	 */
	public String getOnerCd() {
		return onerCd;
	}
	/**
	 * @param onerCd onerCd を設定。
	 */
	public void setOnerCd(String onerCd) {
		this.onerCd = onerCd;
	}
	/**
     * 対象条件
     * @return taishoJoken を戻します。
     */
    public String getTaishoJoken() {
        return taishoJoken;
    }
    /**
     * 対象条件
     * @param taishoJoken を設定します。
     */
    public void setTaishoJoken(String taishoJoken) {
        this.taishoJoken = taishoJoken;
    }
    /**
     * 対象条件リスト
     * @return taishoJokenList を戻します。
     */
    public List getTaishoJokenList() {
        return taishoJokenList;
    }
    /**
     * 対象条件リスト
     * @param taishoJokenList を設定します。
     */
    public void setTaishoJokenList(List taishoJokenList) {
        this.taishoJokenList = taishoJokenList;
    }
    /**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd companyCd を設定。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * @return condition_VIEW_ID を戻します。
	 */
	public String getCondition_VIEW_ID() {
		return condition_VIEW_ID;
	}
	/**
	 * @return jissiNendo を戻します。
	 */
	public String getJissiNendo() {
		return jissiNendo;
	}
	/**
	 * @param jissiNendo jissiNendo を設定。
	 */
	public void setJissiNendo(String jissiNendo) {
		this.jissiNendo = jissiNendo;
	}
	/**
	 * @return entryYearList を戻します。
	 */
    /**
     * @return sysDate を戻します。
     */
    public String getSysDate() {
        return sysDate;
    }
    /**
     * @param String sysDate を設定。
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * オーナーリスト取得
     * @return onerList
     */
    public List getOnerList() {
        return onerList;
    }
    /**
     * オーナーリストをセット
     * @param onerList
     */
    public void setOnerList(List onerList) {
        this.onerList = onerList;
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * getS2Container処理
     * @return
     */
    public S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * ダウンロードindex
     * @param listIndex を設定。
     */
    public void setListIndex(int index) {
        this.listIndex = index;
    }

    /**
     * ダウンロードindex
     * @return listIndex を戻します。
     */
    public int getListIndex() {
        return listIndex;
    }
    /**
     * ダウンロード対象店舗 index
     * @return resultIndex を設定。
     */
    public int getResultIndex() {
        return resultIndex;
    }
    /**
     * ダウンロード対象店舗 index を取得
     * @param resultIndex を戻します。
     */
    public void setResultIndex(int resultIndex) {
        this.resultIndex = resultIndex;
    }
    /**
     * onerNameを取得
     * @return onerName
     */
    public String getOnerName() {
        return onerName;
    }
    /**
     * onerNameをセット
     * @param onerName
     */
    public void setOnerName(String onerName) {
        this.onerName = onerName;
    }
    
    /**
     * クリア
     */
    public void clear(){
        setMiseCd(null);
        setOnerCd(null);
        setResultList(null);
        setJissiNendo(null);
        setOnerName(null);
        setReExecuteFlg(null);
        setCompanyList(null);
    }
    public void clearList(){
        setResultList(null);
    }
    public List getTaishoNendo() {
        return taishoNendo;
    }
    public void setTaishoNendo(List taishoNendo) {
        this.taishoNendo = taishoNendo;
    }
    public String getReExecuteFlg() {
        return reExecuteFlg;
    }
    public void setReExecuteFlg(String reExecuteFlg) {
        this.reExecuteFlg = reExecuteFlg;
    }
    /**
     * 会社コードリスト 取得処理
     * 
     * @return listCompany を戻します。
     */
    public List getCompanyList() {
        return companyList;
    }
    /**
     * 会社コードリスト 設定処理
     * 
     * @param companyList を設定。
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    public String getCompanyName(String cd) {
        if(getCompanyList() == null) {
            return null;
        }
        for (int i = 0; i < getCompanyList().size(); i++) {
            CodCompany data = (CodCompany) getCompanyList().get(i);
            if(data.getCompanyCd().equals(cd)) {
                return data.getCompanyName();
            }
        }
        return  ""; 
   }
    public String getCompanyName() {
        return this.companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}