/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dao.UIWeekDao;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchWeekLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 曜日別検索結果取得ロジック
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public class SearchWeekLogicImpl implements SearchWeekLogic {
	/** ロジックID */
	public static final String LOGIC_ID = JikanBetuRefUtil.LOGIC_ID_SEARCH_WEEK;
	/**
	 * DAO【曜日別】
	 */
	private UIWeekDao jikanBetuRefUIWeekDao;
	
    private List listSearchData = null;
    
    private int kikanSize = 6; //対象期間の文字数 
	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto)
	 */
	public List execute(RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
        requestDto.settabSearchFlg("SEACH");
		//２．DTO【自画面Request】.対象期間の値を判断し、CSV出力データList[[曜日別]]を取得します。
		String kikanSitei = requestDto.getKikanSitei(); 
		String zennenYM = JikanBetuRefUtil.getLastYearMonth(kikanSitei, 12);
		// DTO【自画面Request】.対象期間==『期日指定』の場合、DAO【曜日別日別用】.検索を実行し
		// 戻り値List[[曜日別]]を取得します。
		listSearchData = getJikanBetuRefUIWeekDao().select(
				requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
				requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
				requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),requestDto.getBlockCd(),
				kikanSitei, kikanSitei.substring(4, 6), 
				zennenYM, requestDto.isCsvMode());
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listSearchData == null || listSearchData.size() == 0) {
			throw new NoResultException();
		}
		//４．List[[曜日別]]をリターンする。
		return listSearchData;
	}

	/**
	 * 事前条件処理
	 * 
	 * @param dto
	 */
	private void validate(MenuBetuReqDto requestDto) {
        if (requestDto.getTaishoKikan().equals("KIKAN") &&  requestDto.gettabSearchFlg().equals("TABSEARCH")) {
            requestDto.settabSearchFlg("SEACH");
            throw new CannotExecuteException("期間指定で画面検索は");
        }
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
		String shukeiKbn = requestDto.getKikanSitei();
		if (CommonUtil.isNull(shukeiKbn)) {
			throw new NotNullException("対象年月");
		}
	}

	/**
	 * DAO【曜日別】取得処理
	 * @return jikanBetuRefUIWeekDao を戻します。
	 */
	public UIWeekDao getJikanBetuRefUIWeekDao() {
		return jikanBetuRefUIWeekDao;
	}

	/**
	 * DAO【曜日別】設定処理
	 * @param dao を クラス変数jikanBetuRefUIWeekDaoへ設定します。
	 */
	public void setJikanBetuRefUIWeekDao(UIWeekDao dao) {
		this.jikanBetuRefUIWeekDao = dao;
	}
}
