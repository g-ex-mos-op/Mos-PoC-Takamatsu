/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.rankref.code.KibetuKbn;
import jp.co.isid.mos.bird.analysis.rankref.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.rankref.dao.UIRankDao;
import jp.co.isid.mos.bird.analysis.rankref.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.rankref.logic.SearchLogic;
import jp.co.isid.mos.bird.analysis.rankref.util.RankRefUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 売上ランク情報検索結果取得ロジック
 * 
 * 作成日:2008/10/21
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
	/** ロジックID */
	public static final String LOGIC_ID = RankRefUtil.LOGIC_ID_SEARCH;

	/**
	 * DAO【売上ランク情報】
	 */
	private UIRankDao rankRefUIRankDao;
	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.rankref.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.rankref.dto.RequestDto)
	 */
	public List execute(RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		String kikanSitei = requestDto.getKikanSitei();
		String kikanSiteiTo = "";
		if(TaishoKikan.KIBETU.equals(requestDto.getTaishoKikan())) {
			try {
				kikanSiteiTo = DateManager.getNextYear(kikanSitei, 1)+"03";
			}
			catch (Exception ex) {
	            throw new FtlSystemException("年月リストを取得"
	                    , "基準値["+kikanSitei+"]へ1を計算する際のDateManager.getNextYearメソッド処理で例外が発生しました。"
	                    , ex);
			}
			if(KibetuKbn.KI_KAMI.equals(requestDto.getKibetuKbn())) {
				kikanSiteiTo = kikanSitei+"09";
				kikanSitei = kikanSitei+"04";
			}
			else if(KibetuKbn.KI_SIMO.equals(requestDto.getKibetuKbn())) {
				kikanSitei = kikanSitei+"10";
			}
			else if(KibetuKbn.KI_TOTAL.equals(requestDto.getKibetuKbn())) {
				kikanSitei = kikanSitei+"04";				
			}
		}
		//２．DAO【売上ランク情報】.検索を実行し、出力データ用の戻り値List[[売上ランク情報]]を取得します。
		List listCsvData = getRankRefUIRankDao().select(
					requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
					requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
					requestDto.getTaishoKikan(), kikanSitei,  kikanSiteiTo,
					requestDto.getRankType(), requestDto.getRankTarget()
					);
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listCsvData == null || listCsvData.size() == 0) {
			throw new NoResultException();
		}
		//４．List[[売上ランク情報]]をリターンする。
		return listCsvData;
	}

	/**
	 * 事前条件処理
	 * 
	 * @param dto
	 */
	private void validate(RequestDto requestDto) {
		if (CommonUtil.isNull(requestDto.getUserId())) {
			throw new MissingDataException("ユーザータイプコード");
		}
		if (CommonUtil.isNull(requestDto.getUserId())) {
			throw new MissingDataException("ユーザーID");
		}
		String companyCd = requestDto.getCompanyCd();
		if (CommonUtil.isNull(companyCd)) {
			throw new MissingDataException("会社コード", "companyCd", 0);
		}
		String tenpoShubetu = requestDto.getTenpoShubetu();
		if (CommonUtil.isNull(tenpoShubetu)) {
			throw new MissingDataException("店舗種別", "tenpoShubetu", 0);
		}
		String taishoKikan = requestDto.getTaishoKikan();
		if (CommonUtil.isNull(taishoKikan)) {
			throw new MissingDataException("対象期間", "taishoKikan", 0);
		}
		String kikanSitei = requestDto.getKikanSitei();
		if (CommonUtil.isNull(kikanSitei)) {
			throw new MissingDataException("期間指定", taishoKikan+"kikanSitei", 0);
		}
		if(TaishoKikan.KIBETU.equals(taishoKikan)) {
			String kibetuKbn = requestDto.getKibetuKbn();
			if (CommonUtil.isNull(kibetuKbn)) {
				throw new MissingDataException("期別期報区分", "kibetuKbn", 0);
			}
		}
	}
	/**
	 * @return rankRefUIRankDao を戻します。
	 */
	public UIRankDao getRankRefUIRankDao() {
		return rankRefUIRankDao;
	}

	/**
	 * @param rankRefUIRankDao を クラス変数rankRefUIRankDaoへ設定します。
	 */
	public void setRankRefUIRankDao(UIRankDao rankRefUIRankDao) {
		this.rankRefUIRankDao = rankRefUIRankDao;
	}
}
