package jp.co.isid.mos.bird.bizreport.netorderreport.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.netorderreport.entity.NetOrderReport;

/**
 * ネット注文帳票情報の取得インターフェース
 * @author zzw
 *
 */
public interface NetOrderReportDataDao {

	 public static final Class BEAN = NetOrderReport.class;
	 public static final String selectNetOrderReport_ARGS = "staDate" +
	                                                             ",endDate" +
			                                                     ",orderByCd";
	 /**
	  * ネット注文帳票情報の取得
	  * @param staDate
	  * @param endDate
	  * @param OrderByCd
	  * @return
	  */
	 public List selectNetOrderReport(String staDate,String endDate,String orderByCd);


}
