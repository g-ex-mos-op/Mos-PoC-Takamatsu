/*
 * 作成日: 2016/07/19
 */
package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.analysis.common.dao.UIGroupTogoOwnerDao;
import jp.co.isid.mos.bird.analysis.common.entity.UIGroupTogoOwner;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountCommon;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountConstants;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UIBlockInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UICampaignInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dao.UISetQuantityInfoDao;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dto.CampCheckAmountSaveDto;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UIBlockInfo;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UICampaignInfo;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UISetQuantityInfo;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 【CSV出力情報取得】ロジック
 *
 * @author SHSC
 */
public class QuantityInfoCsvLogicImpl implements CsvOutputLogic {

	/** ロジックID */
	public static final String LOGIC_ID = "BBS027L06";

	/**
	 * 設定数量情報DAO
	 */
	private UISetQuantityInfoDao uisetQuantityInfoDao;

	/**
	 * キャンペーン情報DAO
	 */
	private UICampaignInfoDao uiCampaignInfoDao;

	/**
	 * ブロック情報DAO
	 */
	private UIBlockInfoDao uiBlockInfoDao;

	/**
	 * 支部情報DAO
	 */
	private MstSibuDao mstSibuDao;

	/**
	 * オーナー情報DAO
	 */
	private UIGroupTogoOwnerDao uIGroupTogoOwnerDao;

	NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0);

	/**
	 * ファイル名取得処理
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		return CampCheckAmountConstants.CMPSETAMTCSV_FILE_NAME;
	}

	/**
	 * 設定数量情報DAOを取得します。
	 *
	 * @return 設定数量情報DAO
	 */
	public UISetQuantityInfoDao getUisetQuantityInfoDao() {
		return uisetQuantityInfoDao;
	}

	/**
	 * 設定数量情報DAOを設定します。
	 *
	 * @param uisetQuantityInfoDao
	 *            設定数量情報DAO
	 */
	public void setUisetQuantityInfoDao(UISetQuantityInfoDao uisetQuantityInfoDao) {
		this.uisetQuantityInfoDao = uisetQuantityInfoDao;
	}

	/**
	 * オーナー情報DAOを取得します。
	 *
	 * @return オーナー情報DAO
	 */
	public UIGroupTogoOwnerDao getuIGroupTogoOwnerDao() {
		return uIGroupTogoOwnerDao;
	}

	/**
	 * オーナー情報DAOを設定します。
	 *
	 * @param uIGroupTogoOwnerDao
	 *            オーナー情報DAO
	 */
	public void setuIGroupTogoOwnerDao(UIGroupTogoOwnerDao uIGroupTogoOwnerDao) {
		this.uIGroupTogoOwnerDao = uIGroupTogoOwnerDao;
	}

	/**
	 * キャンペーン情報DAOを取得します。
	 *
	 * @return キャンペーン情報DAO
	 */
	public UICampaignInfoDao getUICampaignInfoDao() {
		return uiCampaignInfoDao;
	}

	/**
	 * キャンペーン情報DAOを設定します。
	 *
	 * @param uiCampaignInfoDao
	 *            キャンペーン情報DAO
	 */
	public void setUICampaignInfoDao(UICampaignInfoDao uiCampaignInfoDao) {
		this.uiCampaignInfoDao = uiCampaignInfoDao;
	}

	/**
	 * ブロック情報DAOを取得します。
	 *
	 * @return ブロック情報DAO
	 */
	public UIBlockInfoDao getUIBlockInfoDao() {
		return uiBlockInfoDao;
	}

	/**
	 * ブロック情報DAOを設定します。
	 *
	 * @param uiBlockInfoDao
	 *            ブロック情報DAO
	 */
	public void setUIBlockInfoDao(UIBlockInfoDao uiBlockInfoDao) {
		this.uiBlockInfoDao = uiBlockInfoDao;
	}

	/**
	 * 支部情報DAOを取得します。
	 *
	 * @return 支部情報DAO
	 */
	public MstSibuDao getMstSibuDao() {
		return mstSibuDao;
	}

	/**
	 * 支部情報DAOを設定します。
	 *
	 * @param uiBlockInfoDao
	 *            支部情報DAO
	 */
	public void setMstSibuDao(MstSibuDao mstSibuDao) {
		this.mstSibuDao = mstSibuDao;
	}

	/**
	 * CSV出力メイン処理
	 *
	 * (非 Javadoc)
	 *
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getOutputData(CsvOutputDto csvOutputDto) {
		CampCheckAmountSaveDto dto = (CampCheckAmountSaveDto) csvOutputDto;

		String taishoCond = dto.getTmpTaishoCond();
		String companyCd = CampCheckAmountConstants.COMPANY_CD;
		String cmpNo = dto.getTmpTaishoCmpNo();
		String miseCd = dto.getTmpTaishoMiseCd();
		String onerCd = dto.getTmpTaishoOnerCd();
		String sibuCd = dto.getTmpTaishoSibuCd();
		String blockCd = dto.getTmpTaishoBlockCd();

		List tmpCSVResult = new ArrayList();

		if (CampCheckAmountConstants.USER_TYPE_HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
			// 対象条件がオーナーであれば
			List tmpOnerCd = new ArrayList();
			if (CampCheckAmountConstants.TAISHO_COND_OWNER.equals(taishoCond)) {
				tmpOnerCd.add(onerCd);
			}

			tmpCSVResult = getUisetQuantityInfoDao().getSetQuantityInfo(taishoCond, companyCd, miseCd, sibuCd, blockCd,
					getBirdDateInfo().getSysDate(), tmpOnerCd,true);

		} else if (CampCheckAmountConstants.USER_TYPE_ONER.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
			List onerCdList = CampCheckAmountCommon.getOnerList(getBirdUserInfo(), companyCd);

			tmpCSVResult = getUisetQuantityInfoDao().getOnerSetQuantityInfo(companyCd, getBirdDateInfo().getSysDate(),
					onerCdList,true);
		}

		if (tmpCSVResult == null || tmpCSVResult.size() == 0) {
			// MSG【E0102】’該当データーがありません。’
			throw new NoResultException();
		}

		List listCSV = new ArrayList();

		String campName = "";
		String sibuName = "";
		String blockName = "";
		String miseName = "";

		// キャンペーン名
		List tmpCamp = getUICampaignInfoDao().getCampaignInfo(
				CampCheckAmountCommon.getPosPrevDate(getBirdDateInfo().getSysDate()), getBirdDateInfo().getSysDate(),
				CampCheckAmountCommon.getPosEndDatePrev1(getBirdDateInfo().getSysDate()));
		for (Iterator ite = tmpCamp.iterator(); ite.hasNext();) {
			UICampaignInfo campInfo = (UICampaignInfo) ite.next();
			if (campInfo.getCmpNo().equals(cmpNo)) {
				campName = campInfo.getCmpName();
				break;
			}
		}
		if (CampCheckAmountConstants.USER_TYPE_HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
			if (CampCheckAmountConstants.TAISHO_COND_TENPO.equals(taishoCond)) {
				for (Iterator ite = tmpCSVResult.iterator(); ite.hasNext();) {
					// Listからentityへキャストする
					UISetQuantityInfo spotInfo = (UISetQuantityInfo) ite.next();
					if (spotInfo.getMiseCd().equals(miseCd)) {
						miseName = miseCd + " " + spotInfo.getMiseNameKj();
						break;
					}
				}
			} else if (CampCheckAmountConstants.TAISHO_COND_SIBU.equals(taishoCond)) {
				// 支部名
				List tmpSibu = getMstSibuDao().getSibuTorikomi(companyCd, getBirdUserInfo().getUserID(),
						getBirdUserInfo().isLimit());
				for (Iterator ite = tmpSibu.iterator(); ite.hasNext();) {
					MstSibu sibuInfo = (MstSibu) ite.next();
					if (sibuInfo.getSibuCd().equals(sibuCd)) {
						sibuName = sibuInfo.getSibuName();
						break;
					}
				}
				// ブロック名
				List tmpBlock = getUIBlockInfoDao().getBlockInfo();
				for (Iterator ite = tmpBlock.iterator(); ite.hasNext();) {
					UIBlockInfo blockInfo = (UIBlockInfo) ite.next();
					if (blockInfo.getBlockCd().equals(blockCd)) {
						blockName = blockInfo.getBlockName();
						break;
					}
				}
			} else if (CampCheckAmountConstants.TAISHO_COND_OWNER.equals(taishoCond)) {
				UIGroupTogoOwner ownerName = getuIGroupTogoOwnerDao().select(onerCd);
				miseName = onerCd + " " + ownerName.getOnerNameKj();

			}
		}

		settingHeader(listCSV, campName, sibuName, blockName, miseCd, miseName, sibuCd, blockCd, taishoCond);

		List listDataTitle = makeDataTitle();
		listCSV.add(listDataTitle);

		BigDecimal sumAmount = new BigDecimal(0);

		try {
			for (Iterator ite = tmpCSVResult.iterator(); ite.hasNext();) {
				UISetQuantityInfo entity = (UISetQuantityInfo) ite.next();
				// １行分の[CSV出力データ]リスト作成
				List listData = make1RowData(entity);
				// 上記で作成した[CSV出力データ]を[[CSV出力データ]]リストへ格納
				listCSV.add(listData);
				sumAmount = sumAmount.add(entity.getKoteiAmt());
			}
		} catch (Exception ex) {
			throw new FtlSystemException("CSV作成 申込状況確認：結果データ設定処理");
		}
		// [[CSV出力データ]]リストをリターンする。
		return listCSV;
	}

	/**
	 * CSVレイアウトヘッダ部を作成する。
	 *
	 * @param dto
	 * @param listCsv
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void settingHeader(List listCsv, String campName, String sibuName, String blockName, String miseCd,
			String miseName, String sibuCd, String blockCd, String taishoCond) {

		List header1 = new ArrayList();
		List header2 = new ArrayList();
		List header3 = new ArrayList();
		List header4 = new ArrayList();

		try {
			// 一行目
			header1.add(CampCheckAmountConstants.CSV_HD_CMP);
			header1.add(campName);

			if (CampCheckAmountConstants.USER_TYPE_HONBU.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
				// ニ行目
				header2.add(CampCheckAmountConstants.CSV_HD_TAISHO_COND);
				if (CampCheckAmountConstants.TAISHO_COND_TENPO.equals(taishoCond)
						|| CampCheckAmountConstants.TAISHO_COND_OWNER.equals(taishoCond)) {
					if (CampCheckAmountCommon.isNull(sibuName) && CampCheckAmountCommon.isNull(blockName)) {
						header2.add(miseName != null ? miseName.trim() : "");
					} else {
						header2.add(sibuName + " " + blockName);
					}
				} else {
					header2.add(sibuCd + " " + sibuName);
					// 三行目
					if (CampCheckAmountCommon.isNull(blockCd) && CampCheckAmountCommon.isNull(blockName)) {
						header3.add("");
					} else {
						header3.add("");
						header3.add(blockCd + " " + blockName);
					}

				}

			} else if (CampCheckAmountConstants.USER_TYPE_ONER.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
				header2.add("");
			}

			listCsv.add(header1);
			listCsv.add(header2);
			listCsv.add(header3);
			listCsv.add(header4);
		} catch (Exception ex) {
			throw new FtlSystemException("CSV作成 申込状況確認：ヘッダ部作成処理");
		}
	}

	/**
	 * CSVレイアウトデータ部タイトルを作成する。
	 *
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List makeDataTitle() {
		List list = new ArrayList();
		// データのヘッダ名を設定する
		list.add(CampCheckAmountConstants.CSV_HD_SIBU_CD);
		list.add(CampCheckAmountConstants.CSV_HD_SIBU_NM);
		list.add(CampCheckAmountConstants.CSV_HD_MISE_CD);
		list.add(CampCheckAmountConstants.CSV_HD_MISE_NM);
		list.add(CampCheckAmountConstants.CSV_HD_KOTEI);
		list.add(CampCheckAmountConstants.CSV_HD_KOTEIAMT);
		return list;
	}

	public void validate(CsvOutputDto csvOutputDto) {

	}

	/**
	 * １行分のリスト作成取得処理
	 *
	 * @param entity
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List make1RowData(UISetQuantityInfo entity) {

		List listData = new ArrayList();

		listData.add(entity.getSibuCd());

		listData.add(entity.getSibuName());

		listData.add(entity.getMiseCd());

		listData.add(entity.getMiseNameKj());

		listData.add(entity.getKoteiCdName());

		listData.add(numFmtdgt0.format(entity.getKoteiAmt().toString(), "#,##0"));

		return listData;
	}

	/**
	 * Seaser2Containaer取得処理
	 *
	 * @return
	 */
	private S2Container getS2Container() {
		return SingletonS2ContainerFactory.getContainer();
	}

	/**
	 * BIRDユーザー情報取得処理
	 *
	 * @return
	 */
	private BirdUserInfo getBirdUserInfo() {
		return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
	}

	/**
	 * BIRD日付情報取得処理
	 *
	 * @return
	 */
	private BirdDateInfo getBirdDateInfo() {
		return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
	}
}
