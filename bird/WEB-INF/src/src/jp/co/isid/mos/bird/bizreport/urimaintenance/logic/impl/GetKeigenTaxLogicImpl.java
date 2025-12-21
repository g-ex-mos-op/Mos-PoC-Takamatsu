/**
 *
 */
package jp.co.isid.mos.bird.bizreport.urimaintenance.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.GetKeigenTaxDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.GetKeigenTaxData;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIMeisaiKeigenTaxInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.GetKeigenTaxLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 * LOGIC【軽減税率売上修正検索】
 *
 * 作成日:2019/07/09
 * @author USI欒
 *
 */
public class GetKeigenTaxLogicImpl implements GetKeigenTaxLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR008L10";

	/** Dao【会計区分別在高日別修正】*/
	private GetKeigenTaxDao uriMaintenanceGetKeigenTaxDao;


	/**
	 * 事前条件処理
	 *
	 * @param sessionDto
	 */
	private void validate(UriMaintenanceDto sessionDto){
		//処理に必要な内部情報は満たされているか。
		if(CommonUtil.isNull(sessionDto.getSysDate())) {
			throw new MissingDataException("システム日付");
		}
		if(CommonUtil.isNull(sessionDto.getCondCompanyCd())) {
			throw new MissingDataException("会社コード");
		}
		if(CommonUtil.isNull(sessionDto.getCondMiseCd())) {
			throw new MissingDataException("店コード");
		}
		if(CommonUtil.isNull(sessionDto.getCondTargetYm())) {
			throw new MissingDataException("対象年月");
		}

	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.urimaintenance.logic.SearchKaikeiKbnTrnData#execute(jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto, jp.co.isid.mos.bird.bizreport.urimaintenance.dto.RequestDto)
	 */
	public void execute(UriMaintenanceDto sessionDto) {
		//０．事前条件処理
		validate(sessionDto);
		//1.List[[修正状況検索結果]]を取得します。
		List listSearchReviseData = getUriMaintenanceGetKeigenTaxDao().select(
				sessionDto.getSysDate()
			  , sessionDto.getCondCompanyCd()
			  , sessionDto.getCondMiseCd()
			  , sessionDto.getCondTargetYm());
		//3．処理２のList[[修正状況検索結果]]がnull又はデータが存在しない場合は下記のException発生させます。
		if(listSearchReviseData.isEmpty() || listSearchReviseData.size()==1) {
			throw new NoResultException();
		}
		GetKeigenTaxData getKeigenTaxData = new GetKeigenTaxData();
		UIMeisaiKeigenTaxInfo uiMeisaiKeigenTaxInfo = new UIMeisaiKeigenTaxInfo();
		BigDecimal total[] = {new BigDecimal("0"), new BigDecimal("0"),new BigDecimal("0"), new BigDecimal("0"),
							new BigDecimal("0"), new BigDecimal("0"),new BigDecimal("0"), new BigDecimal("0"),
							new BigDecimal("0"), new BigDecimal("0"),new BigDecimal("0"), new BigDecimal("0")};
		for(int i=0;i<listSearchReviseData.size()-1;i++) {
			getKeigenTaxData =(GetKeigenTaxData)listSearchReviseData.get(i);
			total[0] = total[0].add(getKeigenTaxData.getUriage1());
			total[1] = total[1].add(getKeigenTaxData.getUriage2());
			total[2] = total[2].add(getKeigenTaxData.getUriage3());
			total[3] = total[3].add(getKeigenTaxData.getUriage4());
			total[4] = total[4].add(getKeigenTaxData.getUriage5());
			total[5] = total[5].add(getKeigenTaxData.getTax1());
			total[6] = total[6].add(getKeigenTaxData.getTax2());
			total[7] = total[7].add(getKeigenTaxData.getTax3());
			total[8] = total[8].add(getKeigenTaxData.getTax4());
			total[9] = total[9].add(getKeigenTaxData.getTax5());
			total[10] = total[10].add(getKeigenTaxData.getUriage1()).add(getKeigenTaxData.getUriage2()).add(getKeigenTaxData.getUriage3()).add(getKeigenTaxData.getUriage4()).add(getKeigenTaxData.getUriage5());
			total[11] = total[11].add(getKeigenTaxData.getTax1()).add(getKeigenTaxData.getTax2()).add(getKeigenTaxData.getTax3()).add(getKeigenTaxData.getTax4()).add(getKeigenTaxData.getTax5());
		}
		uiMeisaiKeigenTaxInfo.setSumUriage1(total[0]);
		uiMeisaiKeigenTaxInfo.setSumUriage2(total[1]);
		uiMeisaiKeigenTaxInfo.setSumUriage3(total[2]);
		uiMeisaiKeigenTaxInfo.setSumUriage4(total[3]);
		uiMeisaiKeigenTaxInfo.setSumUriage5(total[4]);
		uiMeisaiKeigenTaxInfo.setSumTax1(total[5]);
		uiMeisaiKeigenTaxInfo.setSumTax2(total[6]);
		uiMeisaiKeigenTaxInfo.setSumTax3(total[7]);
		uiMeisaiKeigenTaxInfo.setSumTax4(total[8]);
		uiMeisaiKeigenTaxInfo.setSumTax5(total[9]);
		uiMeisaiKeigenTaxInfo.setTotalUriage(total[10]);
		uiMeisaiKeigenTaxInfo.setTotalTax(total[11]);

		//getKeigenTaxData.setSumUriage1((listSearchReviseData.size()));
		//4．DTO【Session情報】.List[[現金在高（日次）売上消費税明細]]へList[[修正状況検索結果]]を設定します。
		sessionDto.setListGetKeigenTaxData(listSearchReviseData);
		sessionDto.setUiMeisaiKeigenTaxInfo(uiMeisaiKeigenTaxInfo);
		sessionDto.setListGetKeigenTaxDataSize(listSearchReviseData.size());
		//５．処理終了
	}
	/**
	 * Dao【会計区分別在高日別修正】取得処理
	 * @return クラス変数uriMaintenanceTrnSyuseiAridakaDao を戻します。
	 */
	public GetKeigenTaxDao getUriMaintenanceGetKeigenTaxDao() {
		return uriMaintenanceGetKeigenTaxDao;
	}
	/**
	 * Dao【会計区分別在高日別修正】設定処理
	 * @param dao を クラス変数uriMaintenanceTrnSyuseiAridakaDaoへ設定します。
	 */
	public void setUriMaintenanceGetKeigenTaxDao(
			GetKeigenTaxDao dao) {
		this.uriMaintenanceGetKeigenTaxDao = dao;
	}

}
