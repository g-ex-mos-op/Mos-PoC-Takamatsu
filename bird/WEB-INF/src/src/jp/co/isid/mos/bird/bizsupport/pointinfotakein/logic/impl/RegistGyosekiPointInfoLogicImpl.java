/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.common.PointInfoTakeinConstants;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.PointShuDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIOfficialPointHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIOfficialPointReadHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIStaffPointHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dao.UIStaffPointReadHisDao;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIOfficialPointHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIOfficialPointReadHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIStaffPointHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.entity.UIStaffPointReadHisInfo;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.RegistGyosekiPointInfoLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 業績ポイント登録ロジック
 *
 * @author yushuncheng
 *
 */
public class RegistGyosekiPointInfoLogicImpl implements RegistGyosekiPointInfoLogic {

	/**
	 * 社員付与ポイント履歴登録Dao
	 */
	private UIStaffPointHisDao staffPointHisDao;

	private UIStaffPointReadHisDao staffPointReadHisDao;

	private UIOfficialPointHisDao officialPointHisDao;

	private UIOfficialPointReadHisDao officialPointReadHisDao;

	private PointShuDao pointShuDao;

	/**
	 * 業績ポイント情報登録
	 *
	 * @param PointInfoTakeinDto
	 *            画面DTO
	 * @return
	 */
	public boolean execute(PointInfoTakeinDto dto) {

		List<UISeidoMst> seidoList = dto.getListSeido();

		// 「株式報酬制度」データが存在チェック
		if (seidoList == null || seidoList.size() == 0) {
			// 「株式報酬制度」コンボボックスを選択されていない（データなし）場合
			throw new GenericMessageException("株式報酬制度を選択してください。");
		} else {

			String conSeidoCd = dto.getConSeidoCd();
			/*
			 * プルダウンで選択した業績ポイント作成の作成対象データより、 株式報酬制度名称（BM76SEID）を検索し、下記チェックへ行く
			 */
			for (int i = 0; i < seidoList.size(); i++) {
				UISeidoMst seidoInfo = seidoList.get(i);
				if (conSeidoCd.equals(seidoInfo.getSeidoCd())) {
					/*
					 * 「業績連動係数」、「業績ポイント付与年月日」が設定されていない場合、エラーとする。
					 */
					if (seidoInfo.getgRendoKeisu() == null || seidoInfo.getgRendoKeisu().trim().isEmpty()
							|| seidoInfo.getgPointDate() == null || seidoInfo.getgPointDate().trim().isEmpty()) {
						throw new GenericMessageException("株式報酬制度の詳細データが設定されていません。");
					}

					String maxNendo = seidoInfo.getEndNendo();
					BigDecimal decKeisu = new BigDecimal(seidoInfo.getgRendoKeisu()).subtract(new BigDecimal(1));

					// 業績ポイントデータの削除
					getStaffPointHisDao().deletePointHisByNendo(maxNendo, PointInfoTakeinConstants.POINT_SHU_4);

					// 業績ポイントデータの計算
					List<UIStaffPointHisInfo> listPoint = getStaffPointHisDao().getSumPointList(maxNendo,
							seidoInfo.getStartNendo());

					List<UIStaffPointReadHisInfo> insertBD62List = new ArrayList<UIStaffPointReadHisInfo>();
					for (int j = 0; j < listPoint.size(); j++) {
						UIStaffPointHisInfo entityDb = listPoint.get(j);
						UIStaffPointReadHisInfo entityRead = new UIStaffPointReadHisInfo();
						entityRead.setTmsp(DateManager.getCurrentTimestamp());
						entityRead.setToriSakujoFlg("0");
						entityDb.setNendo(maxNendo);
						BigDecimal sumPoint = entityDb.getPoint();
						entityDb.setPoint(sumPoint.multiply(decKeisu));
						entityDb.setPointShu(PointInfoTakeinConstants.POINT_SHU_4);
						entityDb.setBikou("");
						entityDb.setFirstUser(dto.getBirdUserInfo().getUserID());
						entityDb.setFirstPgm(PointInfoTakeinConstants.VIEW_ID);
						entityDb.setFirstTmsp(DateManager.getCurrentTimestamp());
						entityDb.setLastUser(dto.getBirdUserInfo().getUserID());
						entityDb.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
						entityDb.setLastTmsp(DateManager.getCurrentTimestamp());
						try {
							PropertyUtils.copyProperties(entityRead, entityDb);
							insertBD62List.add(entityRead);
						} catch (Exception e) {
							throw new FtlSystemException("");
						}
					}

					if (listPoint != null && listPoint.size() > 0) {
						getStaffPointHisDao().insertStaffPointHisList(listPoint);
					}

					if(insertBD62List.size()>0){
						getStaffPointReadHisDao().insertStaffPointReadHisList(insertBD62List);
					}

					// 役員用業績ポイントデータの削除
					getOfficialPointHisDao().deletePfficialPointHisByNendo(maxNendo, PointInfoTakeinConstants.POINT_SHU_4);

					//業績ポイント.合計ポイント * (株式報酬制度名称．役員用業績連動係数(BM76SEID) - 1)
					BigDecimal decKeisuYakuin = new BigDecimal(seidoInfo.getgRendoKeisuYakuin()).subtract(new BigDecimal(1));
					// 業績ポイントデータの計算
					List<UIOfficialPointHisInfo> listPointBD60 = getOfficialPointHisDao().getSumPointList(maxNendo,
							seidoInfo.getStartNendo());

					//ポイント種別名称を取得
					String pointShuName = getPointShuDao().selectPointShuName(PointInfoTakeinConstants.POINT_SHU_4);
					List<UIOfficialPointReadHisInfo> insertBD63List = new ArrayList<UIOfficialPointReadHisInfo>();
					for(int j=0;j<listPointBD60.size();j++){
						UIOfficialPointHisInfo officialPointHisInfo = listPointBD60.get(j);
						UIOfficialPointReadHisInfo officialPointReadHisInfo = new UIOfficialPointReadHisInfo();
						officialPointReadHisInfo.setTmsp(DateManager.getCurrentTimestamp());
						officialPointReadHisInfo.setToriSakujoFlg("0");
						officialPointHisInfo.setNendo(maxNendo);
						officialPointHisInfo.setPoint(officialPointHisInfo.getPoint().multiply(decKeisuYakuin));
						officialPointHisInfo.setPointShu(PointInfoTakeinConstants.POINT_SHU_4);
						officialPointHisInfo.setBikou("");
						officialPointHisInfo.setPointShuName(pointShuName);
						officialPointHisInfo.setFirstUser(dto.getBirdUserInfo().getUserID());
						officialPointHisInfo.setFirstPgm(PointInfoTakeinConstants.VIEW_ID);
						officialPointHisInfo.setFirstTmsp(DateManager.getCurrentTimestamp());
						officialPointHisInfo.setLastUser(dto.getBirdUserInfo().getUserID());
						officialPointHisInfo.setLastPgm(PointInfoTakeinConstants.VIEW_ID);
						officialPointHisInfo.setLastTmsp(DateManager.getCurrentTimestamp());
						try {
							PropertyUtils.copyProperties(officialPointReadHisInfo, officialPointHisInfo);
							insertBD63List.add(officialPointReadHisInfo);
						} catch (Exception e) {
							throw new FtlSystemException("");
						}
					}
					if(listPointBD60.size()>0){
						getOfficialPointHisDao().insertOfficialPointHisList(listPointBD60);
					}
					if(insertBD63List.size()>0){
						getOfficialPointReadHisDao().insertOfficialPointReadHisList(insertBD63List);
					}
					break;
				}
			}
		}

		return true;
	}

	public UIStaffPointHisDao getStaffPointHisDao() {
		return staffPointHisDao;
	}

	public void setStaffPointHisDao(UIStaffPointHisDao staffPointHisDao) {
		this.staffPointHisDao = staffPointHisDao;
	}

	public UIStaffPointReadHisDao getStaffPointReadHisDao() {
		return staffPointReadHisDao;
	}

	public void setStaffPointReadHisDao(UIStaffPointReadHisDao staffPointReadHisDao) {
		this.staffPointReadHisDao = staffPointReadHisDao;
	}

	public UIOfficialPointHisDao getOfficialPointHisDao() {
		return officialPointHisDao;
	}

	public void setOfficialPointHisDao(UIOfficialPointHisDao officialPointHisDao) {
		this.officialPointHisDao = officialPointHisDao;
	}

	public UIOfficialPointReadHisDao getOfficialPointReadHisDao() {
		return officialPointReadHisDao;
	}

	public void setOfficialPointReadHisDao(UIOfficialPointReadHisDao officialPointReadHisDao) {
		this.officialPointReadHisDao = officialPointReadHisDao;
	}

	public PointShuDao getPointShuDao() {
		return pointShuDao;
	}

	public void setPointShuDao(PointShuDao pointShuDao) {
		this.pointShuDao = pointShuDao;
	}

}
