/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dao.UISuiiNipoDao;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchSuiiNipoLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 日別推移検索結果取得ロジック
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public class SearchSuiiNipoLogicImpl implements SearchSuiiNipoLogic {
	/** ロジックID */
	public static final String LOGIC_ID = JikanBetuRefUtil.LOGIC_ID_SEARCH_SUII;

	/**
	 * DAO【日別推移日別用】
	 */
	private UISuiiNipoDao jikanBetuRefUISuiiNipoDao;
	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto)
	 */
	public List execute(RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);

		//２．DAO【日別推移】.検索を実行し、出力データ用の戻り値List[[日別推移]]を取得します。
		List listCsvData = getJikanBetuRefUISuiiNipoDao().select(
					requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
					requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
					requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(), requestDto.getBlockCd(),
					requestDto.getKikanSitei(),  requestDto.getKikanSitei().substring(4, 6));
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listCsvData == null || listCsvData.size() == 0) {
			throw new NoResultException();
		}
        
		//４．List[[日別推移]]をリターンする。
		return listCsvData;
	}

	/**
	 * 事前条件処理
	 * 
	 * @param dto
	 */
	private void validate(MenuBetuReqDto requestDto) {
		if (CommonUtil.isNull(requestDto.getUserId())) {
			throw new MissingDataException("ユーザータイプコード");
		}
		if (CommonUtil.isNull(requestDto.getUserId())) {
			throw new MissingDataException("ユーザーID");
		}
		String companyCd = requestDto.getCompanyCd();
		if (CommonUtil.isNull(companyCd)) {
			throw new NotNullException("会社コード");
		}
		String taishoJoken = requestDto.getTaishoJoken();
		if (CommonUtil.isNull(taishoJoken)) {
			throw new MissingDataException("対象条件");
		}
		if (!TaishoJoken.CODE_ALL.equals(taishoJoken)) {
			String hyojiTaisho = requestDto.getHyojiTaisho();
			if (CommonUtil.isNull(hyojiTaisho)) {
				throw new NotNullException("表示対象");
			}
			boolean isAlphabet = true;
            //対象条件が全社以外の場合表示対象コードチェック
            CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
			if(TaishoJoken.CODE_MISE.equals(taishoJoken)) {
                //コードフォーマットチェック
                if(!codeVeri.validate(hyojiTaisho) || hyojiTaisho.length() > 5) {
                    throw new InvalidInputException("表示対象", "miseCd", 0);
                }
				CodeFormatter cdf = new CodeFormatter(5, "00000");
                cdf.setFormatPattern("00000");
                requestDto.setHyojiTaisho(cdf.format(hyojiTaisho, true));
			}
		}
//		String taishoDt = requestDto.getTaishoKikan();
//		if (CommonUtil.isNull(taishoDt)) {
//			throw new NotNullException("対象期間");
//		}
		String shukeiKbn = requestDto.getKikanSitei();
		if (CommonUtil.isNull(shukeiKbn)) {
			throw new NotNullException("対象年月");
		}
	}
	/**
	 * @return jikanBetuRefUISuiiNipoDao を戻します。
	 */
	public UISuiiNipoDao getJikanBetuRefUISuiiNipoDao() {
		return jikanBetuRefUISuiiNipoDao;
	}

	/**
	 * @param jikanBetuRefUISuiiNipoDao を クラス変数jikanBetuRefUISuiiNipoDaoへ設定します。
	 */
	public void setJikanBetuRefUISuiiNipoDao(UISuiiNipoDao jikanBetuRefUISuiiNipoDao) {
		this.jikanBetuRefUISuiiNipoDao = jikanBetuRefUISuiiNipoDao;
	}
}
