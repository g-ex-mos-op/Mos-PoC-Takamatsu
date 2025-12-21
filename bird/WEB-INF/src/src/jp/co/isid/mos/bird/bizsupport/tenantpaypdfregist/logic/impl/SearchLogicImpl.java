/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dao.TrnPayDetaileStatementDao;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * ABC分析表検索結果取得ロジック
 * 
 * 作成日:2008/09/17
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
	/** ロジックID */
	public static final String LOGIC_ID = TenantPayPdfRegistUtil.LOGIC_ID_SEARCH;

	/**
	 * DAO【テナント入金明細PDF情報】
	 */
	private TrnPayDetaileStatementDao tenantPayPdfRegistTrnPayDetaileStatementDao;

	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.SearchLogic#execute(jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto)
	 */
	public List execute(RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);

		//２．DAO【テナント入金明細PDF情報】.検索を実行し、戻り値List[[テナント入金明細PDF情報]]を取得します。
		List listSearch = getTenantPayPdfRegistTrnPayDetaileStatementDao().select(
				CommonUtil.COMPANY_CD_MOS
				, requestDto.getMiseCd(), requestDto.getTaishoYm(), requestDto.getKaisuu());
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listSearch == null || listSearch.size() == 0) {
			throw new NoResultException();
		}
		//４．List[[テナント入金明細PDF情報]]をリターンする。
		return listSearch;
	}

    
	/**
	 * 事前条件処理
	 * 
	 * @param dto
	 */
	private void validate(RequestDto requestDto) {
    	//パラメータDTO【自画面リクエスト】必須チェック
        if (requestDto == null) {
            throw new MissingDataException("リクエスト情報");
        }
    	//パラメータDTO【自画面リクエスト】.DTO【自画面セッション】必須チェック
		SessionDto sessionDto = requestDto.getSessionDto();
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        //パラメータDTO【自画面リクエスト】.DTO【自画面セッション】.BIRD日付情報必須チェック
        if (sessionDto.getBirdDateInfo() == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        //パラメータDTO【自画面リクエスト】.DTO【自画面セッション】.BIRDユーザー情報必須チェック
        if (sessionDto.getBirdUserInfo() == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
		String miseCd = requestDto.getMiseCd();
		String taishoYm = requestDto.getTaishoYm();
		if (CommonUtil.isNull(miseCd)
				&& CommonUtil.isNull(taishoYm)) {
			throw new NoInputException("店コード又は、対象年月");
		}
		if (!CommonUtil.isNull(miseCd)) {
			boolean isAlphabet = true;
            //対象条件が全社以外の場合表示対象コードチェック
            CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
                //コードフォーマットチェック
                if(!codeVeri.validate(miseCd) || miseCd.length() > 5) {
                    throw new InvalidInputException("店コード", "miseCd", 0);
                }
				CodeFormatter cdf = new CodeFormatter(5, "00000");
                cdf.setFormatPattern("00000");
                requestDto.setMiseCd(cdf.format(miseCd, true));
		}
	}


	/**
	 * DAO【テナント入金明細PDF情報】設定処理
	 * 
	 * @return tenantPayPdfRegistTrnPayDetaileStatementDao を戻します。
	 */
	public TrnPayDetaileStatementDao getTenantPayPdfRegistTrnPayDetaileStatementDao() {
		return tenantPayPdfRegistTrnPayDetaileStatementDao;
	}


	/**
	 * DAO【テナント入金明細PDF情報】取得処理
	 * 
	 * @param dao を クラス変数tenantPayPdfRegistTrnPayDetaileStatementDaoへ設定します。
	 */
	public void setTenantPayPdfRegistTrnPayDetaileStatementDao(
			TrnPayDetaileStatementDao dao) {
		this.tenantPayPdfRegistTrnPayDetaileStatementDao = dao;
	}

}
