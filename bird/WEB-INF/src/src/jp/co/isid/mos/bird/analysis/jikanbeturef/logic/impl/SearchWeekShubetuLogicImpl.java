/**
 * 
 */
package jp.co.isid.mos.bird.analysis.jikanbeturef.logic.impl;

import java.math.BigDecimal;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.dto.MenuBetuReqDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.code.RowType;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dao.UIWeekShubetuDao;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dao.UIWeekShubetuKikanDao;
import jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto;
import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UIWeekShubetu;
import jp.co.isid.mos.bird.analysis.jikanbeturef.entity.UIWeekShubetuKikan;
import jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchWeekShubetuLogic;
import jp.co.isid.mos.bird.analysis.jikanbeturef.util.JikanBetuRefUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 曜日別売上種別情報検索ロジック
 * 
 * 作成日:2008/09/25
 * @author xkinu
 *
 */
public class SearchWeekShubetuLogicImpl implements SearchWeekShubetuLogic {
	/** ロジックID */
	public static final String LOGIC_ID = JikanBetuRefUtil.LOGIC_ID_SEARCH_SHUBETU;

	/**
	 * DAO【曜日別売上種別情報】
	 */
	private UIWeekShubetuDao jikanBetuReUIWeekShubetuDao;
    private UIWeekShubetuKikanDao jikanBetuRefUIKikanShubetuDao;
	/*
	 * (非 Javadoc)
	 * 
	 * @see jp.co.isid.mos.bird.analysis.jikanbeturef.logic.SearchLogic#execute(jp.co.isid.mos.bird.analysis.jikanbeturef.dto.RequestDto)
	 */
	public List execute(RequestDto requestDto) {
		//１．事前条件処理
//System.out.println(requestDto.getTaishoKikan());
//System.out.println(requestDto.gettabSearchFlg());
//        if (requestDto.getTaishoKikan().equals("KIKAN") &&  requestDto.gettabSearchFlg().equals("TABSEARCH")) {
//            requestDto.settabSearchFlg("SEACH");
//            throw new CannotExecuteException("期間指定で画面検索");
//        }
		validate(requestDto);
        requestDto.settabSearchFlg("SEACH");
        List listSearchData = null;
        if(requestDto.getTaishoKikan().equals("MONTH")){
		String kikanSitei = requestDto.getKikanSitei();
    		//２．DAO【曜日別売上種別売上情報】.検索を実行し、CSV出力データ用の戻り値List[[曜日別売上種別売上情報]]を取得します。
               listSearchData = getJikanBetuReUIWeekShubetuDao().select(
    				requestDto.getUserId(), requestDto.getUserTypeCd()
    			, requestDto.getBirdUserInfo().isLimit()
    			, requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
    			, requestDto.getTaishoJoken(), requestDto.getHyojiTaisho(),requestDto.getBlockCd()
    			, kikanSitei, kikanSitei.substring(4, 6), requestDto.isCsvMode());
        }else{
            String kikanSitei = requestDto.getKikanSitei();
            //２．DAO【曜日別売上種別売上情報】.検索を実行し、CSV出力データ用の戻り値List[[曜日別売上種別売上情報]]を取得します。
               listSearchData = getjikanBetuRefUIKikanShubetuDao().select(
                    requestDto.getUserId(), requestDto.getUserTypeCd()
                , requestDto.getBirdUserInfo().isLimit()
                , requestDto.getCompanyCd(), requestDto.getTenpoShubetu()
                , requestDto.getTaishoJoken(), requestDto.getHyojiTaisho()
                , kikanSitei, kikanSitei.substring(4, 6), requestDto.isCsvMode()
                ,requestDto.getKikanSiteiTo(),kikanSitei.substring(0, 6), requestDto.getKikanSiteiTo().substring(0, 6));
        }
		//３．データが存在しない場合は、Exceptionを発生します。
		if (listSearchData == null || listSearchData.size() == 0) {
			throw new NoResultException();
		}
        if(requestDto.getTaishoKikan().equals("MONTH")){
    		for(int i=0; i<listSearchData.size(); i++) {
    			UIWeekShubetu entity = (UIWeekShubetu)listSearchData.get(i);
    			if(RowType.CD_TOTAL.equals(entity.getRowType())) {
    				//種別構成比行のエンティティを作成し、値を設定します。
    				UIWeekShubetu createEntity = new UIWeekShubetu();
    				BigDecimal kingakuTotal = entity.getKouseihiKingaku();
    				BigDecimal kyakusuTotal = entity.getKouseihiKyakusu();
    				createEntity.setRowType(RowType.CD_KOUSEIHI);
    				createEntity.setWeekType(entity.getWeekType());
    				createEntity.setWeekKbn(entity.getWeekKbn());
    				createEntity.setWeekName(entity.getWeekName());
    				createEntity.setTmElem("");
    				createEntity.setTmKbn("");
    				createEntity.setKingaku(Calculator.percentage(kingakuTotal, kingakuTotal, 2));
    				createEntity.setKyakusu(Calculator.percentage(kyakusuTotal, kyakusuTotal, 2));
    				createEntity.setKingakuEat(Calculator.percentage(entity.getKingakuEat(), kingakuTotal, 2));
    				createEntity.setKyakusuEat(Calculator.percentage(entity.getKyakusuEat(), kyakusuTotal, 2));
    				createEntity.setKingakuTake(Calculator.percentage(entity.getKingakuTake(), kingakuTotal, 2));
    				createEntity.setKyakusuTake(Calculator.percentage(entity.getKyakusuTake(), kyakusuTotal, 2));
    				createEntity.setKingakuTel(Calculator.percentage(entity.getKingakuTel(), kingakuTotal, 2));
    				createEntity.setKyakusuTel(Calculator.percentage(entity.getKyakusuTel(), kyakusuTotal, 2));
    				createEntity.setKingakuDrive(Calculator.percentage(entity.getKingakuDrive(), kingakuTotal, 2));
    				createEntity.setKyakusuDrive(Calculator.percentage(entity.getKyakusuDrive(), kyakusuTotal, 2));
    				createEntity.setKingakuTakuhai(Calculator.percentage(entity.getKingakuTakuhai(), kingakuTotal, 2));
    				createEntity.setKyakusuTakuhai(Calculator.percentage(entity.getKyakusuTakuhai(), kyakusuTotal, 2));
    				createEntity.setKingakuGaihan(Calculator.percentage(entity.getKingakuGaihan(), kingakuTotal, 2));
    				createEntity.setKyakusuGaihan(Calculator.percentage(entity.getKyakusuGaihan(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu07(Calculator.percentage(entity.getKingakuSyubetsu07(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu07(Calculator.percentage(entity.getKyakusuSyubetsu07(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu08(Calculator.percentage(entity.getKingakuSyubetsu08(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu08(Calculator.percentage(entity.getKyakusuSyubetsu08(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu09(Calculator.percentage(entity.getKingakuSyubetsu09(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu09(Calculator.percentage(entity.getKyakusuSyubetsu09(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu10(Calculator.percentage(entity.getKingakuSyubetsu10(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu10(Calculator.percentage(entity.getKyakusuSyubetsu10(), kyakusuTotal, 2));
    				createEntity.setKingakuNettake(Calculator.percentage(entity.getKingakuNettake(), kingakuTotal, 2));
    				createEntity.setKyakusuNettake(Calculator.percentage(entity.getKyakusuNettake(), kyakusuTotal, 2));
    				createEntity.setKingakuNetTakuhai(Calculator.percentage(entity.getKingakuNetTakuhai(), kingakuTotal, 2));
    				createEntity.setKyakusuNetTakuhai(Calculator.percentage(entity.getKyakusuNetTakuhai(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu13(Calculator.percentage(entity.getKingakuSyubetsu13(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu13(Calculator.percentage(entity.getKyakusuSyubetsu13(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu14(Calculator.percentage(entity.getKingakuSyubetsu14(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu14(Calculator.percentage(entity.getKyakusuSyubetsu14(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu15(Calculator.percentage(entity.getKingakuSyubetsu15(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu15(Calculator.percentage(entity.getKyakusuSyubetsu15(), kyakusuTotal, 2));
    				createEntity.setKingakuEtc(Calculator.percentage(entity.getKingakuEtc(), kingakuTotal, 2));
    				createEntity.setKyakusuEtc(Calculator.percentage(entity.getKyakusuEtc(), kyakusuTotal, 2));
    				i++;
    				listSearchData.add(i, createEntity);
    			}
            } 
    		//４．List[[曜日別売上種別売上情報]]をリターンする。
            return listSearchData;
		}else{
            for(int i=0; i<listSearchData.size(); i++) {
                UIWeekShubetuKikan entity = (UIWeekShubetuKikan)listSearchData.get(i);
                if(RowType.CD_TOTAL.equals(entity.getRowType())) {
                    //種別構成比行のエンティティを作成し、値を設定します。
                    UIWeekShubetuKikan createEntity = new UIWeekShubetuKikan(); 
                    BigDecimal kingakuTotal = entity.getKingakuAll();
                    BigDecimal kyakusuTotal = entity.getKyakusuAll();
                    createEntity.setRowType(RowType.CD_KOUSEIHI);
                    createEntity.setWeekType(entity.getWeekType());
                    createEntity.setWeekKbn(entity.getWeekKbn());
                    createEntity.setWeekName(entity.getWeekName());
                    createEntity.setTmElem("");
                    createEntity.setTmKbn("");
                    createEntity.setKingakuAll(Calculator.percentage(kingakuTotal, kingakuTotal, 2));
    //                createEntity.setKingakuAll(Calculator.percentage(kyakusuTotal, kyakusuTotal, 2));
                    createEntity.setKingakuEat(Calculator.percentage(entity.getKingakuEat(), kingakuTotal, 2));
                    createEntity.setKyakusuEat(Calculator.percentage(entity.getKyakusuEat(), kyakusuTotal, 2));
                    createEntity.setKingakuTake(Calculator.percentage(entity.getKingakuTake(), kingakuTotal, 2));
                    createEntity.setKyakusuTake(Calculator.percentage(entity.getKyakusuTake(), kyakusuTotal, 2));
                    createEntity.setKingakuTel(Calculator.percentage(entity.getKingakuTel(), kingakuTotal, 2));
                    createEntity.setKyakusuTel(Calculator.percentage(entity.getKyakusuTel(), kyakusuTotal, 2));
                    createEntity.setKingakuDrive(Calculator.percentage(entity.getKingakuDrive(), kingakuTotal, 2));
                    createEntity.setKyakusuDrive(Calculator.percentage(entity.getKyakusuDrive(), kyakusuTotal, 2));
                    createEntity.setKingakuTakuhai(Calculator.percentage(entity.getKingakuTakuhai(), kingakuTotal, 2));
                    createEntity.setKyakusuTakuhai(Calculator.percentage(entity.getKyakusuTakuhai(), kyakusuTotal, 2));
                    createEntity.setKingakuGaihan(Calculator.percentage(entity.getKingakuGaihan(), kingakuTotal, 2));
    				createEntity.setKyakusuGaihan(Calculator.percentage(entity.getKyakusuGaihan(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu07(Calculator.percentage(entity.getKingakuSyubetsu07(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu07(Calculator.percentage(entity.getKyakusuSyubetsu07(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu08(Calculator.percentage(entity.getKingakuSyubetsu08(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu08(Calculator.percentage(entity.getKyakusuSyubetsu08(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu09(Calculator.percentage(entity.getKingakuSyubetsu09(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu09(Calculator.percentage(entity.getKyakusuSyubetsu09(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu10(Calculator.percentage(entity.getKingakuSyubetsu10(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu10(Calculator.percentage(entity.getKyakusuSyubetsu10(), kyakusuTotal, 2));
    				createEntity.setKingakuNettake(Calculator.percentage(entity.getKingakuNettake(), kingakuTotal, 2));
    				createEntity.setKyakusuNettake(Calculator.percentage(entity.getKyakusuNettake(), kyakusuTotal, 2));
    				createEntity.setKingakuNetTakuhai(Calculator.percentage(entity.getKingakuNetTakuhai(), kingakuTotal, 2));
    				createEntity.setKyakusuNetTakuhai(Calculator.percentage(entity.getKyakusuNetTakuhai(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu13(Calculator.percentage(entity.getKingakuSyubetsu13(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu13(Calculator.percentage(entity.getKyakusuSyubetsu13(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu14(Calculator.percentage(entity.getKingakuSyubetsu14(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu14(Calculator.percentage(entity.getKyakusuSyubetsu14(), kyakusuTotal, 2));
    				createEntity.setKingakuSyubetsu15(Calculator.percentage(entity.getKingakuSyubetsu15(), kingakuTotal, 2));
    				createEntity.setKyakusuSyubetsu15(Calculator.percentage(entity.getKyakusuSyubetsu15(), kyakusuTotal, 2));
                    createEntity.setKingakuEtc(Calculator.percentage(entity.getKingakuEtc(), kingakuTotal, 2));
                    createEntity.setKyakusuEtc(Calculator.percentage(entity.getKyakusuEtc(), kyakusuTotal, 2));
                    i++;
                    listSearchData.add(i, createEntity);
                }
            }
            return listSearchData;
		} 
        //４．List[[曜日別売上種別売上情報]]をリターンする。
        
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
//		String taishoDt = requestDto.getTaishoKikan();
//		if (CommonUtil.isNull(taishoDt)) {
//			throw new NotNullException("対象期間");
//		}
		String shukeiKbn = requestDto.getKikanSitei();
		if (CommonUtil.isNull(shukeiKbn)) {
			throw new NotNullException("対象年月", "kikanSitei", 0);
		}
        if(requestDto.getTaishoKikan().equals("KIKAN")){
            String shukeiKbnTo = requestDto.getKikanSiteiTo();
            if(shukeiKbn.compareTo(shukeiKbnTo)>0) {
                throw new NotRelevantException("期間指定To", "期間指定Fromと同じ又は未来日");
            }
        }
	}
	
	/**
	 * @return jikanBetuReUIWeekShubetuDao を戻します。
	 */
	public UIWeekShubetuDao getJikanBetuReUIWeekShubetuDao() {
		return jikanBetuReUIWeekShubetuDao;
	}

	/**
	 * @param dao を クラス変数jikanBetuReUIWeekShubetuDaoへ設定します。
	 */
	public void setJikanBetuReUIWeekShubetuDao(UIWeekShubetuDao dao) {
		this.jikanBetuReUIWeekShubetuDao = dao;
	}
    
    /**
     * @return jikanBetuReUIWeekShubetuDao を戻します。
     */
    public UIWeekShubetuKikanDao getjikanBetuRefUIKikanShubetuDao() {
        return jikanBetuRefUIKikanShubetuDao;
    }

    /**
     * @param dao を クラス変数jikanBetuReUIWeekShubetuDaoへ設定します。
     */
    public void setjikanBetuRefUIKikanShubetuDao(UIWeekShubetuKikanDao dao) {
        this.jikanBetuRefUIKikanShubetuDao= dao;
    }
}
