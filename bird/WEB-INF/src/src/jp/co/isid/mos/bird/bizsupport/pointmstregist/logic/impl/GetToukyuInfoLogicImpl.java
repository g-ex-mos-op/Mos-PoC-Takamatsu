package jp.co.isid.mos.bird.bizsupport.pointmstregist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointmstregist.common.PointMstRegistCommon;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dao.UIToukyuDao;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.dto.NendoPointDataDto;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.entity.UIPointMst;
import jp.co.isid.mos.bird.bizsupport.pointmstregist.logic.GetToukyuInfoLogic;

/**
 * 会社等級検索ロジック
 * @author yushuncheng
 *
 */
public class GetToukyuInfoLogicImpl implements GetToukyuInfoLogic {

	/**
	 * 会社等級Dao
	 */
	private UIToukyuDao uIToukyuDao;

	/**
	 * 会社等級Daoを取得します。
	 * @return uIToukyuDao 会社等級Dao
	 */
	public UIToukyuDao getuIToukyuDao() {
		return uIToukyuDao;
	}

	/**
	 * 会社等級Daoを設定します。
	 * @param uIToukyuDao 会社等級Dao
	 */
	public void setuIToukyuDao(UIToukyuDao uIToukyuDao) {
		this.uIToukyuDao = uIToukyuDao;
	}

	/**
	 * 会社等級リストを取得します。
	 */
	@Override
	public List<NendoPointDataDto> execute() {

		List<UIPointMst> touyuList = getuIToukyuDao().getToukyuInfo();

		List<NendoPointDataDto> nendoList = new ArrayList<NendoPointDataDto>();
		NendoPointDataDto nendoPointDataDto = new NendoPointDataDto();
		nendoPointDataDto.setAddFlag(true);
		PointMstRegistCommon.SetPointMstSplitFlag(touyuList);
		nendoPointDataDto.setPointList(touyuList);
		nendoList.add(nendoPointDataDto);

		return nendoList;
	}

}
