/**
 * 
 */
package jp.co.isid.mos.bird.analysis.menubeturef.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.menubeturef.dao.UIShokuzaiMeyasuDao;
import jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchShokuzaiMeyasuLogic;
import jp.co.isid.mos.bird.analysis.menubeturef.util.MenuBetuRefUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 食材準備目安表情報検索ロジック
 * 
 * 作成日:2008/09/19
 * @author xkinu
 *
 */
public class SearchShokuzaiMeyasuLogicImpl implements SearchShokuzaiMeyasuLogic {
	/** ロジックID */
	public static final String LOGIC_ID = MenuBetuRefUtil.LOGIC_ID_SEARCH_MEYASU;

	/**
	 * DAO【食材準備目安表情報】
	 */
	private UIShokuzaiMeyasuDao menuBetuRefUIShokuzaiMeyasuDao;
	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.menubeturef.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.menubeturef.dto.RequestDto)
	 */
	public List execute(MenuBetuReqDto requestDto) {
		//１．事前条件処理
		validate(requestDto);

		//２．DAO【食材準備目安表情報】.検索を実行し、CSV出力データ用の戻り値List[[食材準備目安表情報]]を取得します。
        List listCsvData;
        if (TaishoKikan.MONTH.equals(requestDto.getTaishoKikan())
                && UserType.HONBU.equals(requestDto.getUserTypeCd())
                && (TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken()) || TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())))
        {
            //本部ユーザー かつ 対象条件＝全社or支部
            listCsvData = getMenuBetuRefUIShokuzaiMeyasuDao().selectSibu(
                    requestDto.getUserId(), requestDto.getUserTypeCd()
                , requestDto.getBirdUserInfo().isLimit()
                , requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
                , requestDto.getTaishoJoken(), requestDto.getHyojiTaisho()
                , requestDto.getTaishoKikan(), requestDto.getKikanSitei()
                , requestDto.getKikanSitei().substring(4, 6));
            
        }
        //『期間指定』の場合
        else if (TaishoKikan.KIKAN.equals(requestDto.getTaishoKikan())) {
        	String kikanSiteiTo = ((RequestDto)requestDto).getKikanSiteiTo();
        	String targetMonth1= requestDto.getKikanSitei().substring(0, 6);
        	//検索期間終了月
        	String endMonth = kikanSiteiTo.substring(0, 6);
        	String targetMonths[] = new String[]{targetMonth1.substring(4,6),null,null,null, null};
        	if(endMonth.compareTo(targetMonth1)>0) {
	        	for(int m=1; m<targetMonths.length; m++) {
	        		String targetMonth = "";
	        		try {
	        			targetMonth = DateManager.getNextMonth(targetMonth1, m);
	        		}
	        		catch(Exception e) {
	        			throw new FtlSystemException("食材準備目安表情報検索ロジック","実行処理.期間指定処理", e);
	        		}
	        		targetMonths[m] = targetMonth.substring(4,6);
	        		if(endMonth.compareTo(targetMonth)<=0) {
	        			break;
	        		}
	        	}
        	}
        	
            listCsvData = getMenuBetuRefUIShokuzaiMeyasuDao().selectKikan(
                    requestDto.getUserId(), requestDto.getUserTypeCd()
                , requestDto.getBirdUserInfo().isLimit()
                , requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
                , requestDto.getHyojiTaisho()
                , requestDto.getTaishoKikan(), requestDto.getKikanSitei(), kikanSiteiTo
                , targetMonths[0], targetMonths[1], targetMonths[2], targetMonths[3], targetMonths[4]);
        }
        else {      	
            listCsvData = getMenuBetuRefUIShokuzaiMeyasuDao().select(
                    requestDto.getUserId(), requestDto.getUserTypeCd()
                , requestDto.getBirdUserInfo().isLimit()
                , requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
                , requestDto.getTaishoJoken(), requestDto.getHyojiTaisho()
                , requestDto.getTaishoKikan(), requestDto.getKikanSitei()
                , requestDto.getKikanSitei().substring(4, 6));

        }
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listCsvData == null || listCsvData.size() == 1) {
			throw new NoResultException();
		}
		//４．List[[食材準備目安表情報]]をリターンする。
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
		String kikanSiteiFrom = requestDto.getKikanSitei();
		if (CommonUtil.isNull(kikanSiteiFrom)) {
			throw new NotNullException("期間指定");
		}
		if(TaishoKikan.KIKAN.equals(taishoDt)) {
			String kikanSiteiTo = ((RequestDto)requestDto).getKikanSiteiTo();
			if (CommonUtil.isNull(kikanSiteiTo)) {
				throw new NotNullException("期間指定");
			}
			if(kikanSiteiFrom.compareTo(kikanSiteiTo)>0) {
				throw new NotRelevantException("期間指定To", "期間指定Fromと同じ又は未来日");
			}
			String maxKikanSiteiTo = "";
			try {
				maxKikanSiteiTo = DateManager.getNextDate(kikanSiteiFrom, 91);
			}
			catch(Exception e) {
				throw new FtlSystemException("食材準備目安表情報検索ロジック","事前条件処理.対象期間", e);
			}
	        if (kikanSiteiTo.compareTo(maxKikanSiteiTo) > 0) {
	            throw new NotRelevantException("対象期間", "92日以内");
	        }
		}
	}

	/**
	 * @return menuBetuRefUIShokuzaiMeyasuDao を戻します。
	 */
	public UIShokuzaiMeyasuDao getMenuBetuRefUIShokuzaiMeyasuDao() {
		return menuBetuRefUIShokuzaiMeyasuDao;
	}

	/**
	 * @param dao を クラス変数menuBetuRefUIShokuzaiMeyasuDaoへ設定します。
	 */
	public void setMenuBetuRefUIShokuzaiMeyasuDao(UIShokuzaiMeyasuDao dao) {
		this.menuBetuRefUIShokuzaiMeyasuDao = dao;
	}
}
