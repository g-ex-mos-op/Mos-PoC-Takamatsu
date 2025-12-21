package jp.co.isid.mos.bird.bizreport.posreportregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.MstRealTimeSchedule;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.common.dto.CommonDto;


/**
 * POS速報設定登録DTO
 * @author 
 */
public class PosReportRegistDto extends CommonDto {
    
    
    /**
     * POS速報設定リスト
     */
    private List listPosReport;
    /**
     * 確認画面兼更新用POS速報設定リスト
     */
    private List listRegistData;
    
    /**
     * 企業コード
     */
    private String posCompanyCd;
    
    /**
     * 設定可能日付開始日
     */
    private String possibleDtSta;

    /**
     * 設定可能日付終了日
     */
    private String possibleDtEnd;
    
    /**
     * selectIndex
     */
    private int selectIndex;
    
    /**
     * POS速報設定リスト重複チェックフラグ
     * true：重複 false：重複なし
     */
    private boolean chkDblFlg;
    
    /**
     * 登録処理完了フラグ
     * @return
     */
    private boolean registFlg;
    
    /**
     * POS設定件数リミットオーバーフラグ
     */
    private boolean limitOverFlg;

    /**
     * 全店対象用 登録済みEntity
     */
    private MstRealTimeSchedule mstRealTimeSchedule;
    /**
     * 全店対象用 削除チェックボックス値
     */
    private boolean delete = false;
    /**
     * 全店対象用 集信開始日
     */
    private String shuDtStart;
    /**
     * 全店対象用 集信終了日
     */
    private String shuDtEnd;

    private boolean addRow = false;
    private int addRowIndex = 0;

    public boolean isAddRow() {
		return addRow;
	}

	public void setAddRow(boolean addRow) {
		this.addRow = addRow;
	}
	public boolean isLimitOverFlg() {
        return limitOverFlg;
    }

    public void setLimitOverFlg(boolean limitOverFlg) {
        this.limitOverFlg = limitOverFlg;
    }

    public boolean isChkDblFlg() {
        return chkDblFlg;
    }

    public void setChkDblFlg(boolean chkDblFlg) {
        this.chkDblFlg = chkDblFlg;
    }

    public List getListPosReport() {
        return listPosReport;
    }

    public void setListPosReport(List listPosReport) {
        this.listPosReport = listPosReport;
    }
    
    public String getPosCompanyCd() {
        return posCompanyCd;
    }

    public void setPosCompanyCd(String posCompanyCd) {
        this.posCompanyCd = posCompanyCd;
    }

    public String getPossibleDtEnd() {
        return possibleDtEnd;
    }

    public void setPossibleDtEnd(String possibleDtEnd) {
        this.possibleDtEnd = possibleDtEnd;
    }

    public String getPossibleDtSta() {
        return possibleDtSta;
    }

    public void setPossibleDtSta(String possibleDtSta) {
        this.possibleDtSta = possibleDtSta;
    }
    
    
    public int getSelectIndex() {
        return selectIndex;
    }
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public boolean isRegistFlg() {
        return registFlg;
    }

    public void setRegistFlg(boolean registFlg) {
        this.registFlg = registFlg;
    }

    /**
     * POS速報設定リストの件数を返す
     * @return
     */
    public int getListPosReportSize() {
        int size = 0;
        if(getListPosReport()!=null) {
            size =getListPosReport().size();
        }
        return size;
    }

    /**
     * プロパティ初期化
     */
    public void setDefault() {
    	setPosCompanyCd("00");
        setListPosReport(null);
        setPossibleDtSta("");
        setPossibleDtEnd("");
        setSelectIndex(0);
        setChkDblFlg(false);
        setRegistFlg(false);
        setLimitOverFlg(false);
        //全店対象用プロパティ
        setMstRealTimeSchedule(null);
        setDelete(false);
        setShuDtStart("");
        setShuDtEnd("");        
    }
	/**
	 * 全店対象用 登録済みEntity取得処理
	 * 
	 * @return
	 */
	public MstRealTimeSchedule getMstRealTimeSchedule() {
		return mstRealTimeSchedule;
	}
	/**
	 * 全店対象用 登録済みEntity設定処理
	 * 
	 * @param mstRealTimeSchedule
	 */
	public void setMstRealTimeSchedule(MstRealTimeSchedule mstRealTimeSchedule) {
		this.mstRealTimeSchedule = mstRealTimeSchedule;
	}
	/**
	 * 全店対象用 削除チェックボックス値取得処理
	 * @return
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * 全店対象用 削除チェックボックス値設定処理
	 * @param deleteFlg
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * 全店対象用 集信開始日取得処理
	 * @return
	 */
	public String getShuDtStart() {
		return shuDtStart;
	}
	/**
	 * 全店対象用 集信開始日設定処理
	 * @param shuDtStart
	 */
	public void setShuDtStart(String shuDtStart) {
		this.shuDtStart = shuDtStart;
	}
    /**
     * 全店対象用 集信終了日取得処理
     * @return
     */
	public String getShuDtEnd() {
		return shuDtEnd;
	}
	/**
	 * 全店対象用 集信終了日設定処理
	 * @param shuDtEnd
	 */
	public void setShuDtEnd(String shuDtEnd) {
		this.shuDtEnd = shuDtEnd;
	}
    /**
     * 全店対象用 確認画面の処理ラベル取得処理
     * 
     * ””・新規・変更・削除の４パターンの
     * いづれか１つの文字列が戻されます。
     * 
     * @return String 処理ラベル
     */
    public String getLabelShori() {
    	String label = "";
    	if (!CommonUtil.isNull(getShuDtStart())
    			&& !CommonUtil.isNull(getShuDtEnd())) 
    	{
//    		if(getMstRealTimeSchedule()==null) {
//    			label = PosReportRegistConstants.PRO_STATE_STR_INS;//新規
//    		}
//    		else {
//    			label = PosReportRegistConstants.PRO_STATE_STR_UPD;//更新
//    		}
    	}
    	else if(isDelete()){
    		label = PosReportRegistConstants.PRO_STATE_STR_DEL;//削除
    	}
    	return label;
    }

	public int getAddRowIndex() {
		return addRowIndex;
	}

	public void setAddRowIndex(int addRowIndex) {
		this.addRowIndex = addRowIndex;
	}

	public List getListRegistData() {
		return listRegistData;
	}

	public void setListRegistData(List listConfirm) {
		this.listRegistData = listConfirm;
	}
   
}
