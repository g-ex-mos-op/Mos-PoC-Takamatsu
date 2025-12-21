/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointmstregist.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.common.dao.UISeidoPointDao;
import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.common.PointMstRegistCommon;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dao.UIPointDao;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dao.UISeidoDao;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dao.UIToukyuDao;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.NendoPointDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.PointDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.SeidoDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.entity.UIPointMst;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.logic.GetPointInfoLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;

/**
 * 付与ポイント検索ロジック
 *
 * @author yushuncheng
 *
 */
public class GetPointInfoLogicImpl implements GetPointInfoLogic {

	/**
	 * 付与ポイントDao
	 */
	private UIPointDao uIPointDao;

	/**
	 * 会社等級Dao
	 */
	private UIToukyuDao uIToukyuDao;

	/**
	 * 株式報酬制度ポイントDao
	 */
	private UISeidoPointDao uISeidoPointDao;

	private UISeidoDao uiSeidoDao;

	/**
	 * 会社等級Daoを取得します。
	 *
	 * @return uIToukyuDao 会社等級Dao
	 */
	public UIToukyuDao getuIToukyuDao() {
		return uIToukyuDao;
	}

	/**
	 * 会社等級Daoを設定します。
	 *
	 * @param uIToukyuDao
	 *            会社等級Dao
	 */
	public void setuIToukyuDao(UIToukyuDao uIToukyuDao) {
		this.uIToukyuDao = uIToukyuDao;
	}

	/**
	 * 付与ポイントDaoを取得します。
	 *
	 * @return uIPointDao 付与ポイントDao
	 */
	public UIPointDao getuIPointDao() {
		return uIPointDao;
	}

	/**
	 * 付与ポイントDaoを設定します。
	 *
	 * @param uIPointDao
	 *            付与ポイントDao
	 */
	public void setuIPointDao(UIPointDao uIPointDao) {
		this.uIPointDao = uIPointDao;
	}

	public UISeidoPointDao getuISeidoPointDao() {
		return uISeidoPointDao;
	}

	public void setuISeidoPointDao(UISeidoPointDao uISeidoPointDao) {
		this.uISeidoPointDao = uISeidoPointDao;
	}

	public UISeidoDao getUiSeidoDao() {
		return uiSeidoDao;
	}

	public void setUiSeidoDao(UISeidoDao uiSeidoDao) {
		this.uiSeidoDao = uiSeidoDao;
	}

	/**
	 * 付与ポイントリストを取得します。
	 *
	 * @param seidoDataDto
	 *            株式報酬制度Dto
	 * @param pointDataDto
	 *            付与ポイントDto
	 */
	public void execute(SeidoDataDto seidoDataDto, PointDataDto pointDataDto) {

		// 一覧画面で、選択した年度計画情報の取得
		UISeidoMst selDto = seidoDataDto.getSeidoList().get(seidoDataDto.getCondTarget());
		pointDataDto.setLastTamp(null);

		String tourokuNo = selDto.getTourokuNo();

		// DBに、選択計画の詳細情報の取得
		UISeidoMst uISeidoMst = getUiSeidoDao().getSeidoInfo(tourokuNo, null);

		// 基本ポイント情報の取得
		List<UIPointMst> pointList = getuIPointDao().getPointInfo(tourokuNo);
		List<NendoPointDataDto> nendoList = new ArrayList<NendoPointDataDto>();

		// DBから、付与ポイント⇒画面の基本ポイントの設定
		if (pointList != null && !pointList.isEmpty()) {
			uISeidoMst.setStartNendo(pointList.get(0).getNendo());
			uISeidoMst.setEndNendo(pointList.get(pointList.size() - 1).getNendo());
			String oldNendo = "";
			for (int i = 0; i < pointList.size(); i++) {
				UIPointMst pointInfo = pointList.get(i);
				// 基本ポイントの最大修正時間の取得
				if (pointDataDto.getLastTamp() == null || pointDataDto.getLastTamp().before(pointInfo.getLastTmsp())) {
					pointDataDto.setLastTamp(pointInfo.getLastTmsp());
				}
				String newNendo = pointInfo.getNendo();
				// 年度が不一致の場合、次へ年度のポイントリストを作成
				if (!oldNendo.equals(newNendo)) {
					NendoPointDataDto nendoPointDataDto = new NendoPointDataDto();
					nendoPointDataDto.setNendo(newNendo);
					List<UIPointMst> nendoPointList = new ArrayList<UIPointMst>();
					nendoPointList.add(pointInfo);
					nendoPointDataDto.setPointList(nendoPointList);

					nendoList.add(nendoPointDataDto);
					oldNendo = newNendo;
				} else {
					// 共通の年度場合、該当年度のポイントリストに追加
					NendoPointDataDto nendoPointDataDto = nendoList.get(nendoList.size() - 1);
					nendoPointDataDto.getPointList().add(pointInfo);
				}
			}

			// 違い会社の間、分割表示フラグの設定
			for (int i = 0; i < nendoList.size(); i++) {
				PointMstRegistCommon.SetPointMstSplitFlag(nendoList.get(i).getPointList());
			}
		}

		// 編集画面の年度計画情報の設定
		pointDataDto.setSeidoInfo(uISeidoMst);
		pointDataDto.setNendoPointList(nendoList);

		return;
	}

	/**
	 * 年度付与ポイントを追加します。
	 *
	 * @param pointDataDto
	 *            付与ポイントDto
	 */
	public void addNendo(PointDataDto pointDataDto) {

		List<UIPointMst> touyuList = getuIToukyuDao().getToukyuInfo();

		NendoPointDataDto nendoPointDataDto = new NendoPointDataDto();
		nendoPointDataDto.setAddFlag(true);
		PointMstRegistCommon.SetPointMstSplitFlag(touyuList);
		nendoPointDataDto.setPointList(touyuList);

		pointDataDto.getNendoPointList().add(nendoPointDataDto);
	}

	/**
	 * 新規登録 を実行する。
	 *
	 * @param pointDataDto
	 *            付与ポイントDto
	 * @param userInfo
	 *            ユーザ情報Dto
	 */
	public void insertDate(PointDataDto pointDataDto, BirdUserInfo userInfo) {
		String tourokuNoAfter = "";
		int seq = 1;
		BigDecimal kihonPoint = null;
		UISeidoMst uiSeidoMst = pointDataDto.getSeidoInfo();
		List uiPointList = pointDataDto.getNendoPointList();
		String userId = userInfo.getUserID();
		String maxNendo = pointDataDto.getMaxNendo();
		String minNendo = pointDataDto.getMinNendo();
		// 業績連動係数（社員）取得
		BigDecimal gRendoKeisu = new BigDecimal(uiSeidoMst.getgRendoKeisu());
		// 業績連動係数（役員）取得
		BigDecimal gRendoKeisuYakuin = new BigDecimal(uiSeidoMst.getgRendoKeisuYakuin());
		uiSeidoMst.setFirstUser(userId);
		uiSeidoMst.setLastUser(userId);
		// 【株式報酬制度名称】(BM76SEID) 最大登録番号取得+1
		String tourokuNo = getUiSeidoDao().getMaxtourNo();
		if (tourokuNo == null || "".equals(tourokuNo)) {
			tourokuNoAfter = "0001";
		} else {
			tourokuNoAfter = String.format("%04d", Integer.valueOf(tourokuNo) + 1);
		}
		int checkNendoExit = getuIPointDao().checkNendoExist(tourokuNoAfter, maxNendo, minNendo);
		if (checkNendoExit > 0) {
			throw new GenericMessageException("設定された年度期間は既存の計画と重複する年度があります。重複年度を削除してください。");
		}
		uiSeidoMst.setTourokuNo(tourokuNoAfter);
		uiSeidoMst.setDataVer("001");
		// DAO【株式報酬制度名称】(BM76SEID)．登録 を実行する。
		getUiSeidoDao().insertSeid(uiSeidoMst, gRendoKeisu,gRendoKeisuYakuin);

		for (int i = 0; i < uiPointList.size(); i++) {
			// 対象年度を取得する。
			NendoPointDataDto nendoPointData = (NendoPointDataDto) uiPointList.get(i);
			// 「株式報酬制度名称」情報を取得する。
			List<UIPointMst> pointList = nendoPointData.getPointList();
			for (int j = 0; j < pointList.size(); j++) {
				UIPointMst uiPointMst = pointList.get(j);
				// パラメーター.付与ポイントのデータを設定します。
				kihonPoint = new BigDecimal(uiPointMst.getKihonPoint());
				uiPointMst.setFirstUser(userId);
				uiPointMst.setLastUser(userId);
				uiPointMst.setTourokuNo(tourokuNoAfter);
				uiPointMst.setDataVer("001");
				uiPointMst.setTourokuSeq(String.format("%03d", seq));
				uiPointMst.setNendo(nendoPointData.getNendo());
				uiPointMst.setFirstUser(userId);
				uiPointMst.setLastUser(userId);
				// 付与ポイント登録
				getuIPointDao().insertHuyp(uiPointMst, kihonPoint);
				// 登録番号連番+1
				seq++;
			}
		}
	}

	/**
	 * 編集登録 を実行する。
	 *
	 * @param pointDataDto
	 *            付与ポイントDto
	 * @param userInfo
	 *            ユーザ情報Dto
	 */
	public void updateDate(PointDataDto pointDataDto, BirdUserInfo userInfo) {
		String tourokuNo = "";
		String dataVer = "";
		BigDecimal kihonPoint = null;
		List<UIPointMst> uiPointListByDb = null;
		int maxSeq = 0;
		Map<String, List<UIPointMst>> retMap = null;
		UISeidoMst uiSeidoMst = pointDataDto.getSeidoInfo();
		List uiPointList = pointDataDto.getNendoPointList();
		String userId = userInfo.getUserID();
		String maxNendo = pointDataDto.getMaxNendo();
		String minNendo = pointDataDto.getMinNendo();
		// 業績連動係数（社員）取得
		BigDecimal gRendoKeisu = new BigDecimal(uiSeidoMst.getgRendoKeisu());
		// 業績連動係数（役員）取得
		BigDecimal gRendoKeisuYakuin = new BigDecimal(uiSeidoMst.getgRendoKeisuYakuin());
		uiSeidoMst.setFirstUser(userId);
		uiSeidoMst.setLastUser(userId);
		// データバージョン取得
		dataVer = uiSeidoMst.getDataVer();
		// 登録番号取得
		tourokuNo = uiSeidoMst.getTourokuNo();
		// DBに同じ年度の登録がないかチェック
		int checkNendoExit = getuIPointDao().checkNendoExist(tourokuNo, maxNendo, minNendo);
		if (checkNendoExit > 0) {
			throw new GenericMessageException("設定された年度期間は既存の計画と重複する年度があります。重複年度を削除してください。");
		}
		// DBに、株式報酬制度名称の排他制御チェック
		UISeidoMst seido = getUiSeidoDao().getSeidoInfo(tourokuNo, dataVer);
		if (seido == null || !uiSeidoMst.getLastTmsp().equals(seido.getLastTmsp())) {
			// 排他制御
			throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
		}
		// DBに、付与ポイントの排他制御チェック
		UIPointMst lastPoint = getuIPointDao().getMaxTmsp(tourokuNo);
		if (lastPoint == null || !lastPoint.getLastTmsp().equals(pointDataDto.getLastTamp())) {
			// 排他制御
			throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
		}
		// 株式報酬制度名称の変更のチェック
		if (!seido.getgPointDate().equals(uiSeidoMst.getgPointDate())
				|| !seido.getPlanName().equals(uiSeidoMst.getPlanName())
				|| !seido.getgRendoKeisu().equals(uiSeidoMst.getgRendoKeisu())
				|| !seido.getgRendoKeisuYakuin().equals(uiSeidoMst.getgRendoKeisuYakuin())
				|| !seido.getBikou().equals(uiSeidoMst.getBikou())) {
			// 画面に、中期経営計画の修正場合、DBの更新
			getUiSeidoDao().updateSeid(tourokuNo, dataVer, userId);
			uiSeidoMst.setDataVer(String.format("%03d", Integer.valueOf(dataVer) + 1));
			getUiSeidoDao().insertSeid(uiSeidoMst, gRendoKeisu,gRendoKeisuYakuin);
		}

		// DBに、付与ポイントの最大登録番号連番の取得
		maxSeq = Integer.valueOf(getuIPointDao().getMaxSeq(tourokuNo)) + 1;
		// DBに、付与ポイントの取得
		uiPointListByDb = getuIPointDao().getPointInfo(tourokuNo);
		// 年度Mapの取得
		retMap = getPointMap(uiPointListByDb);
		if (uiPointList.size() == 0) {
			getUiSeidoDao().updateSeid(tourokuNo, null, userId);
		}
		// 画面の年度ポイントリスト⇒DBを更新
		for (int i = 0; i < uiPointList.size(); i++) {
			NendoPointDataDto nendoPointData = (NendoPointDataDto) uiPointList.get(i);
			List<UIPointMst> pointList = nendoPointData.getPointList();
			// 該当年度ポイントが修正場合
			if (retMap.containsKey(nendoPointData.getNendo())) {
				List<UIPointMst> dbpointList = retMap.get(nendoPointData.getNendo());
				UIPointMst entityDb = null;
				//
				for (int j = 0; j < pointList.size(); j++) {
					UIPointMst entityRegist = pointList.get(j);
					entityDb = dbpointList.get(j);
					//
					if (!entityDb.getKihonPoint().equals(entityRegist.getKihonPoint())) {
						String tourokuSeq = entityDb.getTourokuSeq();
						entityDb.setFirstUser(userId);
						entityDb.setLastUser(userId);
						dataVer = entityDb.getDataVer();
						// 修正先のデータの削除フラグを更新
						getuIPointDao().updatePoint(tourokuNo, dataVer, tourokuSeq, userId, null);
						entityDb.setDataVer(String.format("%03d", Integer.valueOf(dataVer) + 1));
						// 修正後のデータを追加
						getuIPointDao().insertHuyp(entityDb, new BigDecimal(entityRegist.getKihonPoint()));
					}
				}
				retMap.remove(nendoPointData.getNendo());
			} else {
				// 該当年度が追加場合
				for (int j = 0; j < pointList.size(); j++) {
					UIPointMst uiPointMst = pointList.get(j);
					kihonPoint = new BigDecimal(uiPointMst.getKihonPoint());
					uiPointMst.setTourokuNo(tourokuNo);
					uiPointMst.setDataVer("001");
					uiPointMst.setTourokuSeq(String.format("%03d", maxSeq));
					uiPointMst.setNendo(nendoPointData.getNendo());
					uiPointMst.setFirstUser(userId);
					uiPointMst.setLastUser(userId);
					getuIPointDao().insertHuyp(uiPointMst, kihonPoint);
					maxSeq++;
				}
			}
		}
		// DBに、削除した年度データを削除
		if (!retMap.isEmpty()) {
			for (Map.Entry<String, List<UIPointMst>> entry : retMap.entrySet()) {
				getuIPointDao().updatePoint(tourokuNo, null, null, userId, entry.getKey());
			}
		}
	}

	/**
	 * 計画削除登録 を実行する。
	 *
	 * @param pointDataDto
	 *            付与ポイントDto
	 * @param userInfo
	 *            ユーザ情報Dto
	 */
	public void deleteDate(String tourokuNo, BirdUserInfo userInfo) {
		String userId = userInfo.getUserID();
		//
		getUiSeidoDao().updateSeid(tourokuNo, null, userId);
		// DBに、削除した年度データを削除
		getuIPointDao().updatePoint(tourokuNo, null, null, userId, null);
	}

	/**
	 * 年度付与ポイント情報により、年度Mapの取得
	 *
	 * @param dbPointList
	 * @return
	 */
	public Map<String, List<UIPointMst>> getPointMap(List<UIPointMst> dbPointList) {
		Map<String, List<UIPointMst>> retMap = new HashMap<String, List<UIPointMst>>();
		String befNendo = "";
		for (int i = 0; i < dbPointList.size(); i++) {
			UIPointMst pointData = dbPointList.get(i);
			if (!befNendo.equals(pointData.getNendo())) {
				List<UIPointMst> pointList = new ArrayList<UIPointMst>();
				pointList.add(pointData);
				retMap.put(pointData.getNendo(), pointList);
				befNendo = pointData.getNendo();
			} else {
				retMap.get(befNendo).add(pointData);
			}
		}
		return retMap;
	}

}
