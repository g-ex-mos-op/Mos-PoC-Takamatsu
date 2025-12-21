/**
 * 
 */
package jp.co.isid.mos.bird.analysis.common.menubetu.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.RowType;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.dao.UIJikanBetuDao;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIJikanBetu;
import jp.co.isid.mos.bird.analysis.common.menubetu.logic.SearchJikanBetuLogic;
import jp.co.isid.mos.bird.analysis.common.menubetu.util.MenubetuUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * メニュー別時間帯別売上検索結果取得ロジック
 * 
 * 作成日:2008/09/17
 * @author xkinu
 *
 */
public class SearchJikanBetuLogicImpl implements SearchJikanBetuLogic {
	/** ロジックID */
	public static final String LOGIC_ID = MenubetuUtil.SCREEN_ID + "L01";
	/**
	 * DAO【メニュー別時間帯別売上】
	 */
	private UIJikanBetuDao analysisMenuBetuUIJikanBetuDao;
	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto)
	 */
	public List execute(MenuBetuReqDto requestDto) {
		//１．事前条件処理
		validate(requestDto);

		//２．DAO【メニュー別時間帯別売上】.検索を実行し
			// CSV出力データ用の戻り値List[[メニュー別時間帯別売上]]を取得します。
		List listCsvData = getAnalysisMenuBetuUIJikanBetuDao().select(
					requestDto.getUserId(), requestDto.getUserTypeCd()
					, requestDto.getBirdUserInfo().isLimit(),
					requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
					requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(), requestDto.getBlockCd(),
					requestDto.getKikanSitei());
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listCsvData == null || listCsvData.size() == 0) {
			throw new NoResultException();
		}
		//４．処理２の戻り値List[[メニュー別時間帯別売上]]の『合計』行(先頭行)のエンティティの値から
		//    時間帯構成比行を作成します。
		UIJikanBetu eTotal = (UIJikanBetu)listCsvData.get(0);

		BigDecimal kingakuTotal = eTotal.getKingaku();//金額
		BigDecimal kosuTotal = eTotal.getKosu();//個数
		UIJikanBetu eJikantaiKouseiHi = new UIJikanBetu();
		eJikantaiKouseiHi.setRowType(RowType.CD_JKOUSEIHI);
		eJikantaiKouseiHi.setMenuNameKj("時間帯構成比");
		eJikantaiKouseiHi.setKingaku(Calculator.percentage(eTotal.getKingaku(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu(Calculator.percentage(eTotal.getKosu(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin00(Calculator.percentage(eTotal.getKin00(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu00(Calculator.percentage(eTotal.getKosu00(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin01(Calculator.percentage(eTotal.getKin01(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu01(Calculator.percentage(eTotal.getKosu01(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin02(Calculator.percentage(eTotal.getKin02(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu02(Calculator.percentage(eTotal.getKosu02(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin03(Calculator.percentage(eTotal.getKin03(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu03(Calculator.percentage(eTotal.getKosu03(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin04(Calculator.percentage(eTotal.getKin04(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu04(Calculator.percentage(eTotal.getKosu04(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin05(Calculator.percentage(eTotal.getKin05(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu05(Calculator.percentage(eTotal.getKosu05(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin06(Calculator.percentage(eTotal.getKin06(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu06(Calculator.percentage(eTotal.getKosu06(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin07(Calculator.percentage(eTotal.getKin07(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu07(Calculator.percentage(eTotal.getKosu07(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin08(Calculator.percentage(eTotal.getKin08(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu08(Calculator.percentage(eTotal.getKosu08(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin09(Calculator.percentage(eTotal.getKin09(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu09(Calculator.percentage(eTotal.getKosu09(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin10(Calculator.percentage(eTotal.getKin10(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu10(Calculator.percentage(eTotal.getKosu10(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin11(Calculator.percentage(eTotal.getKin11(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu11(Calculator.percentage(eTotal.getKosu11(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin12(Calculator.percentage(eTotal.getKin12(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu12(Calculator.percentage(eTotal.getKosu12(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin13(Calculator.percentage(eTotal.getKin13(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu13(Calculator.percentage(eTotal.getKosu13(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin14(Calculator.percentage(eTotal.getKin14(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu14(Calculator.percentage(eTotal.getKosu14(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin15(Calculator.percentage(eTotal.getKin15(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu15(Calculator.percentage(eTotal.getKosu15(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin16(Calculator.percentage(eTotal.getKin16(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu16(Calculator.percentage(eTotal.getKosu16(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin17(Calculator.percentage(eTotal.getKin17(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu17(Calculator.percentage(eTotal.getKosu17(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin18(Calculator.percentage(eTotal.getKin18(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu18(Calculator.percentage(eTotal.getKosu18(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin19(Calculator.percentage(eTotal.getKin19(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu19(Calculator.percentage(eTotal.getKosu19(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin20(Calculator.percentage(eTotal.getKin20(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu20(Calculator.percentage(eTotal.getKosu20(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin21(Calculator.percentage(eTotal.getKin21(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu21(Calculator.percentage(eTotal.getKosu21(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin22(Calculator.percentage(eTotal.getKin22(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu22(Calculator.percentage(eTotal.getKosu22(), kosuTotal, 2));
		eJikantaiKouseiHi.setKin23(Calculator.percentage(eTotal.getKin23(), kingakuTotal, 2));
		eJikantaiKouseiHi.setKosu23(Calculator.percentage(eTotal.getKosu23(), kosuTotal, 2));
		listCsvData.add(1,eJikantaiKouseiHi);
		// ５．List[[メニュー別時間帯別売上]]をリターンする。
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
        
		String taishoDt = requestDto.getTaishoKikan();
		if (CommonUtil.isNull(taishoDt)) {
			throw new NotNullException("対象期間");
		}
		String shukeiKbn = requestDto.getKikanSitei();
		if (CommonUtil.isNull(shukeiKbn)) {
			throw new NotNullException("期間指定");
		}
	}

	/**
	 * @return analysisMenuBetuUIJikanBetuDao を戻します。
	 */
	public UIJikanBetuDao getAnalysisMenuBetuUIJikanBetuDao() {
		return analysisMenuBetuUIJikanBetuDao;
	}

	/**
	 * @param analysisMenuBetuUIJikanBetuDao を クラス変数analysisMenuBetuUIJikanBetuDaoへ設定します。
	 */
	public void setAnalysisMenuBetuUIJikanBetuDao(
			UIJikanBetuDao analysisMenuBetuUIJikanBetuDao) {
		this.analysisMenuBetuUIJikanBetuDao = analysisMenuBetuUIJikanBetuDao;
	}
}
