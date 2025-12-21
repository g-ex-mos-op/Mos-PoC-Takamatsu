/*
 * 作成日: 2006/04/19
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto;

/**
 * @author Noh
 */
public class EntryYearInfoDto {
	
	/* 開始日 */
	private String fromDt;
	
	/* 終了日 */
	private String toDt;
	
	/* エントリー年 */
	private String entryYear;
	
	/* エントリー回 */
	private String entryKai;
	
	/* 合格 */
	private String gokaku;
	
	/* 不合格 */
	private String hugokaku;
    
    /* 保留 */
    private String horyu;
    
    /* 無効 */
    private String mukou;
	
	
	/**
	 * @return YYYYMMDD -> YYYY年 MM月 
	 */
	private String ouputYearMon(String str){
		if(str.length()==6 || str.length()==8){
	        String sYear = str.substring(0, 4);
	        String sMonth = str.substring(4, 6);
	        String year = Integer.valueOf(sYear).toString();
	        String month = Integer.valueOf(sMonth).toString();
	        return year + "年　" + month + "月";
		}else{
			return "";
		}
	}
  

	/**
	 * @return entryKai を戻します。
	 */
	public String getEntryKai() {
		return entryKai;
	}
	/**
	 * @param entryKai entryKai を設定。
	 */
	public void setEntryKai(String entryKai) {
		this.entryKai = entryKai;
	}
	/**
	 * @return entryYear を戻します。
	 */
	public String getEntryYear() {
		return entryYear;
	}
	/**
	 * @param entryYear entryYear を設定。
	 */
	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}
	/**
	 * @return fromDt を戻します。
	 */
	public String getFromDt() {
		return ouputYearMon(fromDt);
	}
	/**
	 * @param fromDt fromDt を設定。
	 */
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	/**
	 * @return gokaku を戻します。
	 */
	public String getGokaku() {
		return gokaku;
	}
	/**
	 * @param gokaku gokaku を設定。
	 */
	public void setGokaku(String gokaku) {
		this.gokaku = gokaku;
	}
	/**
	 * @return hugokaku を戻します。
	 */
	public String getHugokaku() {
		return hugokaku;
	}
	/**
	 * @param hugokaku hugokaku を設定。
	 */
	public void setHugokaku(String hugokaku) {
		this.hugokaku = hugokaku;
	}
	/**
	 * @return toDt を戻します。
	 */
	public String getToDt() {
		return ouputYearMon(toDt);
	}
	/**
	 * @param toDt toDt を設定。
	 */
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
    
    /**
     * @return horyu を戻します。
     */
    public String getHoryu() {
        return horyu;
    }
    /**
     * @param horyu horyu を設定。
     */
    public void setHoryu(String horyu) {
        this.horyu = horyu;
    }
    
    /**
     * @return mukou を戻します。
     */
    public String getMukou() {
        return mukou;
    }
    /**
     * @param mukou mukou を設定。
     */
    public void setMukou(String mukou) {
        this.mukou = mukou;
    }
}
