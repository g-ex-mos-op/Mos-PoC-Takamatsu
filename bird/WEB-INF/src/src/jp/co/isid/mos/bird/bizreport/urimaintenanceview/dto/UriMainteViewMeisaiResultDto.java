/*
 * 作成日:2012/08/09
 */
package jp.co.isid.mos.bird.bizreport.urimaintenanceview.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.SyukeiKbnResultData;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIUriMaintenanceResult;

/**
 * 売上修正確認明細・結果DTOクラス
 * 
 * @author xkawa
 */
public class UriMainteViewMeisaiResultDto extends MeisaiRequestDto {

    /** 集計区分結果情報 */
    SyukeiKbnResultData syukeiKbnResultData;

//add 2019/08/16 USI張  #34 begin
    /** List[対象店舗リスト] */
    private String listTenpo;

    /** List[現金在高（日次）売上消費税明細情報リスト] */
    private List listGetKeigenTaxData;
//add 2019/08/16 USI張  #34 end

    /** タブNo */
    private String tabNo;
    
    /**
     * 集計区分結果情報を戻します。
     * @return syukeiKbnResultData 集計区分結果情報
     */
    public SyukeiKbnResultData getSyukeiKbnResultData() {
        return syukeiKbnResultData;
    }

    /**
     * 集計区分結果情報を設定します。
     * @param syukeiKbnResultData 集計区分結果情報
     */
    public void setSyukeiKbnResultData(SyukeiKbnResultData syukeiKbnResultData) {
        this.syukeiKbnResultData = syukeiKbnResultData;
    }

    /**
     * タブNoを戻します。
     * @return tabNo タブNo
     */
    public String getTabNo() {
        return tabNo;
    }

    /**
     * タブNoを設定します。
     * @param tabNo タブNo
     */
    public void setTabNo(String tabNo) {
        this.tabNo = tabNo;
    }

    /**
     * UIUriMaintenanceResult[タブ情報]取得処理
     * @return 
     */
    public UIUriMaintenanceResult getResultData() {
        if(getSyukeiKbnResultData() == null) {
            return null;
        }
        return (UIUriMaintenanceResult) getSyukeiKbnResultData().getMapTabResult().get(getTabNo());
    }
    /**
     * UIUriMaintenanceResult[タブ情報]取得処理
     * @return 
     */
    public void setResultData(UIUriMaintenanceResult resutlData) {
        if(getSyukeiKbnResultData() != null) {
            getSyukeiKbnResultData().getMapTabResult().put(getTabNo(), resutlData);
        }
    }

//add 2019/08/16 USI張  #34 begin
	/**
	 * listGetKeigenTaxDataを取得します。
	 * @return listGetKeigenTaxData
	 */
	public List getListGetKeigenTaxData() {
		return listGetKeigenTaxData;
	}

	/**
	 * listGetKeigenTaxDataを設定します。
	 * @param listGetKeigenTaxData
	 */
	public void setListGetKeigenTaxData(List listGetKeigenTaxData) {
		this.listGetKeigenTaxData = listGetKeigenTaxData;
	}

	/**
	 * listTenpoを取得します。
	 * @return listTenpo
	 */
	public String getListTenpo() {
		return listTenpo;
	}

	/**
	 * listTenpoを設定します。
	 * @param listTenpo
	 */
	public void setListTenpo(String listTenpo) {
		this.listTenpo = listTenpo;
	}
//add 2019/08/16 USI張  #34 end

}
