/*
 * 作成日: 2006/04/19
 */
package jp.co.isid.mos.bird.storemanage.mlholderlist.dto;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * @author lee
 */
public class MlHolderListDto implements CsvOutputDto {
    
	/* VIEW_ID */
    public final String VIEW_ID				= "BSM007";
    public final String condition_VIEW_ID		= "BSM005V01";
//    public final String condition_VIEW_ID		= "BSM007V01";
    public final String result_VIEW_ID			= "BSM004V01";
    public final String onerSearch_VIEW_ID		= "BCO006V01";
    public final String miseSearch_VIEW_ID		= "BCO008V01";
    
    /* 支部リスト */
    private List sibuList;
    
    /* 会社コード */
    private String companyCd;
    
    /* 本部コード */
    private String sitenCd;
    
    /* オーナーコード */
    private String onerCd;
    
    /* 店舗コード */
    private String miseCd;
    
    /* 選択Flage 0:支部　1:オーナー　2:店舗  */
    private int selectFlg; 
    
    /* 選択したデータ */
    private String selectValue;
    
    /* 	ユーザタイプ */
    private String usertypeCd;

    /* スタッフ情報の検索取得 */
    private List resultList;
    
    /* 初期設定 */
    private boolean selectinitFlg;
    
    /* 検索区分 */
    private boolean kensaku;
    
    
    /* 検索条件 選択フラグ保持  0:支部　1:オーナー　2:店舗  */
    private int tmpSelectFlg;

    /* 検索条件 支部名称保持 */
    private String tmpSibuNm;
    
    /* 検索条件 オーナーコード保持 */
    private String tmpOnerCd;
    
    /* 検索条件 店舗コード保持 */
    private String tmpMiseCd;
    
    /* 検索条件 オーナー名称保持 */
    private String tmpOnerNm;
    
    /* 検索条件 店舗名称保持 */
    private String tmpMiseNm;

    /* システム日付 */
    private String sysDate;
        
    private boolean allDownload = false;
    
    private boolean mha = false;
    /**
	 * @return sibuList を戻します。
	 */
	public List getSibuList() {
		return sibuList;
	}
	/**
	 * @param sibuList sibuList を設定。
	 */
	public void setSibuList(List sibuList) {
		this.sibuList = sibuList;
	}
	/**
	 * @return kensaku を戻します。
	 */
	public boolean isKensaku() {
		return kensaku;
	}
	/**
	 * @param kensaku kensaku を設定。
	 */
	public void setKensaku(boolean kensaku) {
		this.kensaku = kensaku;
	}
	/**
	 * @return selectinitFlg を戻します。
	 */
	public boolean isSelectinitFlg() {
		return selectinitFlg;
	}
	/**
	 * @param selectinitFlg selectinitFlg を設定。
	 */
	public void setSelectinitFlg(boolean selectinitFlg) {
		this.selectinitFlg = selectinitFlg;
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
	 * @return selectFlg を戻します。
	 */
	public int getSelectFlg() {
		return selectFlg;
	}
	/**
	 * @param selectFlg selectFlg を設定。
	 */
	public void setSelectFlg(int selectFlg) {
		this.selectFlg = selectFlg;
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
	 * @return selectValue を戻します。
	 */
	public String getSelectValue() {
		return selectValue;
	}
	/**
	 * @param selectValue selectValue を設定。
	 */
	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

    /**
     * @return sysDate を戻します。
     */
    public String getSysDate() {
        return sysDate;
    }
    /**
     * @param sysDate sysDate を設定。
     */
    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public void clear(){
		setCompanyCd(null);
		setMiseCd(null);
		setOnerCd(null);
		setResultList(null);
		setSelectValue(null);
		setSitenCd(null);
		setUsertypeCd(null);
		setSibuList(null);
	}
	
	/**
     * 検索結果リストサイズ設定処理
     * @return resultList
     */
    public int getResultListSize() {
        return (resultList == null) ? 0 : resultList.size();
    }
    

    public String getTmpSibuNm() {
        return tmpSibuNm;
    }
    public void setTmpSibuNm(String tmpSibuNm) {
        this.tmpSibuNm = tmpSibuNm;
    }
    public String getTmpMiseCd() {
        return tmpMiseCd;
    }
    public void setTmpMiseCd(String tmpMiseCd) {
        this.tmpMiseCd = tmpMiseCd;
    }
    public String getTmpMiseNm() {
        return tmpMiseNm;
    }
    public void setTmpMiseNm(String tmpMiseNm) {
        this.tmpMiseNm = tmpMiseNm;
    }
    public String getTmpOnerCd() {
        return tmpOnerCd;
    }
    public void setTmpOnerCd(String tmpOnerCd) {
        this.tmpOnerCd = tmpOnerCd;
    }
    public String getTmpOnerNm() {
        return tmpOnerNm;
    }
    public void setTmpOnerNm(String tmpOnerNm) {
        this.tmpOnerNm = tmpOnerNm;
    }
    public int getTmpSelectFlg() {
        return tmpSelectFlg;
    }
    public void setTmpSelectFlg(int tmpSelectFlg) {
        this.tmpSelectFlg = tmpSelectFlg;
    }
	/**
	 * @return allDownload を戻します。
	 */
	public boolean isAllDownload() {
		return allDownload;
	}
	/**
	 * @param allDownload 設定する allDownload。
	 */
	public void setAllDownload(boolean allDownload) {
		this.allDownload = allDownload;
	}
	/**
	 * @return mha を戻します。
	 */
	public boolean isMha() {
		return mha;
	}
	/**
	 * @param mha 設定する mha。
	 */
	public void setMha(boolean mha) {
		this.mha = mha;
	}



}
