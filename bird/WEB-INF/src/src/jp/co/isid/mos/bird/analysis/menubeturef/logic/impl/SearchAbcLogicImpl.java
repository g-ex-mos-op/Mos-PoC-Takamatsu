/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dao.UIAbcDayDao;
import jp.co.isid.mos.bird.analysis.menubeturef.dao.UIAbcMonthDao;
import jp.co.isid.mos.bird.analysis.menubeturef.dao.UILastYearEigyoDtDao;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.entity.UIAbcMonth;
import jp.co.isid.mos.bird.analysis.menubeturef.entity.UILastYearEigyoDt;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchAbcLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * ABC分析表検索結果取得ロジック
 * 
 * 作成日:2008/09/17
 * @author xkinu
 *
 */
public class SearchAbcLogicImpl implements SearchAbcLogic {
	/** ロジックID */
	public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_SEARCH_ABC;

	/**
	 * DAO【前年営業日情報】
	 */
	private UILastYearEigyoDtDao menuBetuRefUILastYearEigyoDtDao;

	/**
	 * DAO【ABC分析表日別用】
	 */
	private UIAbcDayDao menuBetuRefUIAbcDayDao;

	/**
	 * DAO【ABC分析表月別用】
	 */
	private UIAbcMonthDao menuBetuRefUIAbcMonthDao;

	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto)
	 */
	public List execute(RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);

		//２．DTO【自画面Request】.対象期間の値を判断し、CSV出力データList[[ABC分析表]]を取得します。
		List listCsvData = null;
		String kikanSitei = requestDto.getKikanSitei();
		//２－１．DTO【自画面Request】.対象期間==『期日指定』の場合、下記の処理を行います。
        String lastMonth = null;
        String lastYearMonth = null;
		if (TaishoKikan.DAY
				.equals(requestDto.getTaishoKikan())) {
			//1.DAO【前年営業日情報】検索を実行し、[前年営業日情報]を取得します。
			UILastYearEigyoDt zennnenDtInfo = getMenuBetuRefUILastYearEigyoDtDao().select(
					requestDto.getCompanyCd(), kikanSitei);
			//2.DAO【ABC分析表日別用】.検索を実行し出力データ用の戻り値List[[ABC分析表]]を取得します。
			listCsvData = getMenuBetuRefUIAbcDayDao().select(
					requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
					requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
					requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),
					requestDto.getKikanSitei(), zennnenDtInfo.getDoyoDt()
					, zennnenDtInfo.getDogetuMonth(), zennnenDtInfo.getDoyoMonth(), requestDto.isCsv(), requestDto.isOutputSmenu());
		} else {
			try {
				//1.パラメータ.DTO【Request】.期間指定から前月の年月を算出します。
				lastMonth = DateManager.getPrevMonth(
						kikanSitei, 1);
				//2.DTO【自画面Request】.期間指定日の前月の年月へ処理1の前月の年月を設定します。
				requestDto.setKikanSiteiLastMonth(lastMonth);
				//3.パラメータ.DTO【Request】.期間指定から前年の年月を算出します。
				lastYearMonth = DateManager.getPrevMonth(
						kikanSitei, 12);
			} catch (ApplicationException appEx) {
				throw appEx;
			} catch (Exception ex) {
				throw new FtlSystemException("[メニュー別売上]検索結果取得ロジック",
						"期間指定の値から前月の年月を取得する処理でエラーが発生しました。", ex);
			}

			//4.DAO【ABC分析表月別用】.検索を実行し、出力データ用の戻り値List[[ABC分析表]]を取得します。
            if (UserType.HONBU.equals(requestDto.getUserTypeCd()) 
                    && (TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken()) || TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())))
            {
                //【本部ユーザー かつ 対象条件：全社or支部の場合】
                //売上データ取得
                listCsvData = getMenuBetuRefUIAbcMonthDao().selectSibu(
                        requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
                        requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
                        requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),
                        requestDto.getKikanSitei(), lastYearMonth, lastMonth, requestDto.isCsv(), requestDto.isOutputSmenu());
            }
            else if (UserType.ONER.equals(requestDto.getUserTypeCd())) {
                //【オーナーユーザー】
                listCsvData = getMenuBetuRefUIAbcMonthDao().selectOner(
                        requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
                        requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
                        requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),
                        requestDto.getKikanSitei(), lastYearMonth, lastMonth, requestDto.isCsv(), requestDto.isOutputSmenu());
            }
            else {
    			listCsvData = getMenuBetuRefUIAbcMonthDao().select(
    					requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
    					requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
    					requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),
    					requestDto.getKikanSitei(), lastYearMonth, lastMonth, requestDto.isCsv(), requestDto.isOutputSmenu());
            }
		}
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listCsvData == null || listCsvData.size() == 0) {
			throw new NoResultException();
		}

        if (TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())) {
            if ((UserType.HONBU.equals(requestDto.getUserTypeCd()) 
                            && (TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken()) || TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())))
                    || 
                (UserType.ONER.equals(requestDto.getUserTypeCd()) && TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken())))
            {
                //販売店舗数取得
                List listHanbaiTenpoCnt = getMenuBetuRefUIAbcMonthDao().selectHanbaiTenpoCnt(
                        requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
                        requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
                        requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),
                        requestDto.getKikanSitei(), lastYearMonth, lastMonth, requestDto.isCsv(), requestDto.isOutputSmenu());
                //対象店舗数取得
                int intTaishoTenpoCnt = getMenuBetuRefUIAbcMonthDao().selectTaishoTenpoCnt(
                        requestDto.getUserId(), requestDto.getUserTypeCd(), requestDto.getBirdUserInfo().isLimit(),
                        requestDto.getCompanyCd(), requestDto.getTenpoShubetu(),
                        requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),
                        requestDto.getKikanSitei(), lastYearMonth, lastMonth);
                
                //販売店舗数セット
                matchingHonbuSibuData(listCsvData, listHanbaiTenpoCnt);
                //対象店舗数を1件目の「TENPO_CNT」にセット
                UIAbcMonth entityTotal = (UIAbcMonth) listCsvData.get(0);
                entityTotal.setTenpoCnt(new BigDecimal(intTaishoTenpoCnt));
            }
        }
        
		//４．List[[ABC分析表]]をリターンする。
		return listCsvData;
	}

    /**
     * 売上データに販売店舗数データを追加
     * （本部ユーザーかつ対象条件＝全社or支部のみ）
     * @param listMainData
     * @param listTenpoCnt
     * @return
     */
    private void matchingHonbuSibuData(List listMainData, List listTenpoCnt) {
        for (Iterator ite = listTenpoCnt.iterator(); ite.hasNext();) {
            UIAbcMonth entityTenpoCnt = (UIAbcMonth) ite.next();
            for (Iterator ite2 = listMainData.iterator(); ite2.hasNext();) {
                UIAbcMonth entityMainData = (UIAbcMonth) ite2.next();
                
                if (isEqual(entityTenpoCnt.getMenuBunrui(), entityMainData.getMenuBunrui())
                        && isEqual(entityTenpoCnt.getMenuCd(), entityMainData.getMenuCd())
//                        && isEqual(entityTenpoCnt.getTanka(), entityMainData.getTanka())
                        && isEqual(entityTenpoCnt.getSumMenuCd(), entityMainData.getSumMenuCd()))
                {
                    entityMainData.setHanbaiTenpoCnt(entityTenpoCnt.getHanbaiTenpoCnt());
                }
            }
        }
    }
    
    private boolean isEqual(Object val1, Object val2) {
        if (val1 == null) {
            return (val2 == null);
        }
        else if (val2 == null) {
            return (val1 == null);
        }
        if (val1 instanceof String) {
            return ((String) val1).trim().equals(((String) val2).trim());
        }
        else {
            return val1.equals(val2);
        }
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
			throw new MissingDataException("会社コード");
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
	 * DAO【ABC分析表日別用】取得処理
	 * 
	 * @return menuBetuRefUIAbcDayDao を戻します。
	 */
	public UIAbcDayDao getMenuBetuRefUIAbcDayDao() {
		return menuBetuRefUIAbcDayDao;
	}

	/**
	 * DAO【ABC分析表日別用】設定処理
	 * 
	 * @param dao を クラス変数menuBetuRefUIAbcDayDaoへ設定します。
	 */
	public void setMenuBetuRefUIAbcDayDao(UIAbcDayDao dao) {
		this.menuBetuRefUIAbcDayDao = dao;
	}

	/**
	 * DAO【ABC分析表月別用】取得処理
	 * 
	 * @return menuBetuRefUIAbcMonthDao を戻します。
	 */
	public UIAbcMonthDao getMenuBetuRefUIAbcMonthDao() {
		return menuBetuRefUIAbcMonthDao;
	}

	/**
	 * DAO【ABC分析表月別用】設定処理
	 * 
	 * @param dao を クラス変数menuBetuRefUIAbcMonthDaoへ設定します。
	 */
	public void setMenuBetuRefUIAbcMonthDao(UIAbcMonthDao dao) {
		this.menuBetuRefUIAbcMonthDao = dao;
	}

	/**
	 * @return menuBetuRefUILastYearEigyoDtDao を戻します。
	 */
	public UILastYearEigyoDtDao getMenuBetuRefUILastYearEigyoDtDao() {
		return menuBetuRefUILastYearEigyoDtDao;
	}

	/**
	 * @param dao を クラス変数menuBetuRefUILastYearEigyoDtDaoへ設定します。
	 */
	public void setMenuBetuRefUILastYearEigyoDtDao(UILastYearEigyoDtDao dao) {
		this.menuBetuRefUILastYearEigyoDtDao = dao;
	}
}
