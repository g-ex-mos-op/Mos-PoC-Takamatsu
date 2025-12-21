package jp.co.isid.mos.bird.bizsupport.pointmstregist.dto;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;
import jp.co.isid.mos.bird.bizsupport.common.util.CheckUtils;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.common.PointMstRegistCommon;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.common.PointMstRegistConstants;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.entity.UIPointMst;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * 付与ポイントDTOクラス
 * @author yushuncheng
 *
 */
public class PointDataDto {

	/**
	 * 株式報酬制度情報
	 */
	private UISeidoMst seidoInfo;

	/**
	 * 年度付与ポイントリスト
	 */
	private List<NendoPointDataDto> nendoPointList;

	/**
	 * 最小年度
	 */
	private String minNendo;

	/**
	 * 最大年度
	 */
	private String maxNendo;

	/**
     * 編集モード
     *  = 1 : 新規
     *  = 2 : 更新
     */
    private int editMode;

	/**
	 * 削除年度番号
	 */
	private String deleteNo;

	/**
	 * 最大の修正時ﾀｲﾑｽﾀﾝﾌﾟ
	 */
	private java.sql.Timestamp lastTamp;

	/**
	 * 入力データの有効チェック
	 * @return
	 */
	public boolean checkInputData() {

		UISeidoMst seidoDto = getSeidoInfo();
		// 中期経営計画のチェック
		if(seidoDto != null) {
			// 中期経営計画名称のチェック
			if(CommonUtil.isNull(seidoDto.getPlanName())) {
				throw new NotNullException("中期経営計画");
			}else if(!CheckUtils.isLengthOver(seidoDto.getPlanName(), 60)) {
				String msg = String.format(PointMstRegistConstants.ERROR_LEN_OVER, "中期経営計画", 60);
				throw new GenericMessageException(msg);
			}

	        NumericVerifier numericVerifier2_1 = new NumericVerifier(false, 2, 1);
			// 業績連動係数（社員）のチェック
			if(CommonUtil.isNull(seidoDto.getgRendoKeisu())) {
				throw new NotNullException("業績連動係数（社員）");
			}else if(!numericVerifier2_1.validate(seidoDto.getgRendoKeisu())) {
				throw new InvalidInputException("業績連動係数（社員）");
			}

			// 業績連動係数（役員）のチェック
			if (CommonUtil.isNull(seidoDto.getgRendoKeisuYakuin())) {
				throw new NotNullException("業績連動係数（役員）");
			} else if (!numericVerifier2_1.validate(seidoDto.getgRendoKeisuYakuin())) {
				throw new InvalidInputException("業績連動係数（役員）");
			}

			DateVerifier formmter = new DateVerifier();
			// 業績ポイント付与年月日
			if(CommonUtil.isNull(seidoDto.getgPointDate())) {
				throw new NotNullException("業績ポイント付与年月日");
			}else if(!formmter.validate(seidoDto.getgPointDate(), DateVerifier.DATE_TYPE_YMD)) {
				throw new InvalidInputException("業績ポイント付与年月日");
			}
		}

		// 備考のチェック
		if(!CommonUtil.isNull(seidoDto.getBikou())){
			if (!CheckUtils.isLengthOver(seidoDto.getBikou(), 100)) {
				String msg = String.format(PointMstRegistConstants.ERROR_LEN_OVER, "備考", 100);
				throw new GenericMessageException(msg);
			}
		}else{
			seidoDto.setBikou("");
		}

		// 基本ポイントのチェック
		List<NendoPointDataDto> listNendo = getNendoPointList();
		if(listNendo.size() == 0) {
			throw new NotNullException("基本ポイント");
		}
		NumericVerifier numericVerifier3_0 = new NumericVerifier(false, 3, 0);
		String nendos = "";
		int minNendo = 10000;
		for(int i=0; i<listNendo.size(); i++) {
			NendoPointDataDto pointDataDto = listNendo.get(i);
			if(CommonUtil.isNull(pointDataDto.getNendo())) {
				throw new GenericMessageException("年度" + PointMstRegistConstants.ERROR_REQUEST, "nendo", i);
			}else if(!CheckUtils.isDate(pointDataDto.getNendo()+"0101", CheckUtils.DATE_FORMAT_YYYYMMDD)) {
				throw new GenericMessageException("年度" + PointMstRegistConstants.ERROR_INVALID_VALUE, "nendo", i);
			}
			nendos += pointDataDto.getNendo() + ";";
			int nendo = new Integer(pointDataDto.getNendo());
			if(minNendo > nendo) {
				minNendo = nendo;
			}

			List<UIPointMst> listPoint  = pointDataDto.getPointList();
			for(int j=0; j<listPoint.size(); j++) {
				UIPointMst pointDto = listPoint.get(j);
				if(CommonUtil.isNull(pointDto.getKihonPoint())) {
					throw new GenericMessageException(
							"付与ポイント" + PointMstRegistConstants.ERROR_REQUEST + "(年度：" + pointDataDto.getNendo() +"  "+ "会社："
									+ pointDto.getKbCompanyName()+"  " + "等級：" + pointDto.getRankName() + ")",
							"kihonPoint", j);
				}else if(!numericVerifier3_0.validate(pointDto.getKihonPoint())) {
					throw new GenericMessageException(
							"付与ポイント" + PointMstRegistConstants.ERROR_INVALID_VALUE + "(年度：" + pointDataDto.getNendo()+"  " + "会社："
									+ pointDto.getKbCompanyName()+"  " + "等級：" + pointDto.getRankName() + ")",
							"kihonPoint", j);
				}
			}
		}
		// 最小年度の設定
		setMinNendo(String.valueOf(minNendo));
		// 最大年度の設定
		setMaxNendo(String.valueOf(minNendo + listNendo.size() - 1));

		// 年度連番のチェック
		for(int n=0; n<listNendo.size() ; n++) {
			String checkNendo = minNendo + ";";
			int cnt = PointMstRegistCommon.getCount(nendos, checkNendo);
			if(cnt == 1) {
				nendos = nendos.replace(checkNendo, "");
				minNendo += 1;
			}else if(cnt == 0) {
				throw new GenericMessageException(minNendo + "年度"+ PointMstRegistConstants.ERROR_DATA_MISS);
			}else if(cnt > 1) {
				throw new GenericMessageException(PointMstRegistConstants.ERROR_DATA_REPEAT);
			}
		}

		return true;
	}

	public String getDeleteNo() {
		return deleteNo;
	}

	public void setDeleteNo(String deleteNo) {
		this.deleteNo = deleteNo;
	}

	/**
	 * 株式報酬制度データを取得します。
	 * @return seidoInfo 株式報酬制度データ
	 */
	public UISeidoMst getSeidoInfo() {
		return seidoInfo;
	}

	/**
	 * 株式報酬制度を設定します。
	 * @param seidoInfo 株式報酬制度
	 */
	public void setSeidoInfo(UISeidoMst seidoInfo) {
		this.seidoInfo = seidoInfo;
	}

	/**
	 * 年度付与ポイントデータListを取得します。
	 * @return nendoPointList 付与ポイントデータList
	 */
	public List<NendoPointDataDto> getNendoPointList() {
		return nendoPointList;
	}

	/**
	 *  年度付与ポイントデータListを設定します。
	 * @param nendoPointList 付与ポイントデータList
	 */
	public void setNendoPointList(List<NendoPointDataDto> nendoPointList) {
		this.nendoPointList = nendoPointList;
	}

	public int getEditMode() {
		return editMode;
	}

	public void setEditMode(int editMode) {
		this.editMode = editMode;
	}

    public String getMinNendo() {
		return minNendo;
	}

	public void setMinNendo(String minNendo) {
		this.minNendo = minNendo;
	}

	public String getMaxNendo() {
		return maxNendo;
	}

	public void setMaxNendo(String maxNendo) {
		this.maxNendo = maxNendo;
	}

	public java.sql.Timestamp getLastTamp() {
		return lastTamp;
	}

	public void setLastTamp(java.sql.Timestamp lastTamp) {
		this.lastTamp = lastTamp;
	}

	public int getNendoCnt() {
		return this.nendoPointList.size();
	}

	public int getPointCnt() {
		int pointCnt = 0;
		int nendoCnt = this.nendoPointList.size();
		if(nendoCnt > 0) {
			pointCnt = this.nendoPointList.get(nendoCnt -1).getPointList().size();
		}
		return pointCnt;
	}

}
