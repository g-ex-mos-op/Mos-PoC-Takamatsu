/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dao.UIUriageShubetuDao;
import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIUriageShubetu;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchUriageShubetuLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * メニュー別種別個数情報検索ロジック
 * 
 * 作成日:2008/09/19
 * @author xkinu
 *
 */
public class SearchUriageShubetuLogicImpl implements SearchUriageShubetuLogic {
	/** ロジックID */
	public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_SEARCH_SHUBETU;

	/**
	 * DAO【メニュー別種別売上情報】
	 */
	private UIUriageShubetuDao menuBetuRefUIUriageShubetuDao;
	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto)
	 */
	public List execute(MenuBetuReqDto requestDto) {
		//１．事前条件処理
		validate(requestDto);

		//２．DAO【メニュー別種別売上情報】.検索を実行し、CSV出力データ用の戻り値List[[メニュー別種別売上情報]]を取得します。
        List listCsvData;
        if (TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())
                && UserType.HONBU.equals(requestDto.getUserTypeCd())
                && (TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken()) || TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())))
        {
            listCsvData = getMenuBetuRefUIUriageShubetuDao().selectSibu(
                    requestDto.getUserId(), requestDto.getUserTypeCd()
                , requestDto.getBirdUserInfo().isLimit()
                , requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
                , requestDto.getTaishoJoken(), requestDto.getHyojiTaisho()
                , requestDto.getTaishoKikan(), requestDto.getKikanSitei()
                , requestDto.getKikanSitei().substring(4, 6));
        }
        else {
            listCsvData = getMenuBetuRefUIUriageShubetuDao().select(
                    requestDto.getUserId(), requestDto.getUserTypeCd()
                , requestDto.getBirdUserInfo().isLimit()
                , requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
                , requestDto.getTaishoJoken(), requestDto.getHyojiTaisho()
                , requestDto.getTaishoKikan(), requestDto.getKikanSitei()
                , requestDto.getKikanSitei().substring(4, 6));
        }
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listCsvData == null || listCsvData.size() == 0) {
			throw new NoResultException();
		}
        
        //対象店舗数取得処理
//        if (TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())
//                && UserType.HONBU.equals(requestDto.getUserTypeCd())
//                && (TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken()) || TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())))
//        {
            int intTaishoTenpoCnt = getMenuBetuRefUIUriageShubetuDao().selectTaishoTenpoCnt(
                    requestDto.getUserId(), requestDto.getUserTypeCd()
                , requestDto.getBirdUserInfo().isLimit()
                , requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
                , requestDto.getTaishoJoken(), requestDto.getHyojiTaisho()
                , requestDto.getTaishoKikan(), requestDto.getKikanSitei()
                , requestDto.getKikanSitei().substring(4, 6));
            UIUriageShubetu entity = (UIUriageShubetu) listCsvData.get(0);
            entity.setTenpoCnt(new BigDecimal(intTaishoTenpoCnt));
//        }
        
        
		//４．List[[メニュー別種別売上情報]]をリターンする。
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
	 * @return menuBetuRefUIUriageShubetuDao を戻します。
	 */
	public UIUriageShubetuDao getMenuBetuRefUIUriageShubetuDao() {
		return menuBetuRefUIUriageShubetuDao;
	}

	/**
	 * @param dao を クラス変数menuBetuRefUIUriageShubetuDaoへ設定します。
	 */
	public void setMenuBetuRefUIUriageShubetuDao(UIUriageShubetuDao dao) {
		this.menuBetuRefUIUriageShubetuDao = dao;
	}
}
