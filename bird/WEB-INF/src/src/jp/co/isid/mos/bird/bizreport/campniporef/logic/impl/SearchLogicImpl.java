/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campniporef.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.campniporef.dao.UINipoMiseDao;
import jp.co.isid.mos.bird.bizreport.campniporef.dao.UINipoSibuDao;
import jp.co.isid.mos.bird.bizreport.campniporef.entity.UINipo;
import jp.co.isid.mos.bird.bizreport.campniporef.entity.UITabSearchData;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 検索結果取得ロジック
 * 
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampNipoRefUtil.SCREEN_ID+"L04";
    /** DAO【支部一覧日報情報】*/
    private UINipoSibuDao campNipoRefUINipoSibuDao;
    /** DAO【店舗一覧日報情報】*/
    private UINipoMiseDao campNipoRefUINipoMiseDao;
    /** 店舗種別：全店 用の前年売上項目用接尾辞 */
    private String TENPO_SHU_ALL_ZENNEN_SUFFIX = "TONEN";

    /* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.SearchLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto)
	 */
	public List execute(RequestNipoDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		
		String appDate = requestDto.getSelfSessionDto().getBirdDateInfo().getAppDate();
		String sysDate = requestDto.getSelfSessionDto().getBirdDateInfo().getSysDate();
		String userId = requestDto.getSelfSessionDto().getBirdUserInfo().getUserID();
		String userTypeCd = requestDto.getSelfSessionDto().getUserTypeCd();
		boolean limitFlg = requestDto.getSelfSessionDto().getBirdUserInfo().isLimit();
		String companyCd = requestDto.getCompanyCd();
		String campId = requestDto.getCampId();
		String menuTotaledKbn = requestDto.getMenuTotaledKbn();
		String tenpoShubetu = requestDto.getTenpoShubetu();
		String shukeiKbn = requestDto.getShukeiKbn();
		String campFrom = requestDto.getMstCampDate().getCampFrom();
		String campTo = requestDto.getMstCampDate().getCampTo();
		String taishoKikan = requestDto.getTaishoKikan();
		String taishoDt = requestDto.getTaishoDt();
		String taishoDtTo = requestDto.getTaishoDtTo();
		String taishoJoken = requestDto.getTaishoJoken();
		String hyojiTaisho = requestDto.getHyojiTaisho();
		String zennenDataShubetu = requestDto.getZennenDataShubetu();
		String taishoDtFromLastDay = "";
		String targetZenneClm = zennenDataShubetu+"_"+tenpoShubetu;
        if (TenpoShubetu.CODE_ALL.equals(tenpoShubetu)) {
            targetZenneClm += TENPO_SHU_ALL_ZENNEN_SUFFIX;
        }

		//２．List[[検索結果]]を取得し、DTO【自画面Session】へ設定する。
		//３－１．UITabSearchData[検索結果情報]をインスタンス化します。
		UITabSearchData uiSearchData = new UITabSearchData();
		uiSearchData.setKey(taishoKikan);
		uiSearchData.setKeyName(TaishoKikan.getName(taishoKikan));
		
		String targetTable = "BD03CPML";
		//対象期間が『期間合計』の場合、検索対象テーブルへ『BD12CPRI』を設定する。
		if(TaishoKikan.TOTAL.equals(taishoKikan)) {
			targetTable = "BD12CPRI";
			taishoDt = campFrom;
			taishoDtTo = appDate.compareTo(campTo)>0?campTo:appDate;
		}
		try {
			taishoDtFromLastDay = DateManager.getPrevDate(taishoDt, 1);
		}
		catch(Exception e) {
			throw new FtlSystemException("キャンペーン日報の検索結果取得ロジック"
					,"キャンペーン開始日前日の日付を算出できませんでした。",e);
		}
		//３－２．List[[検索結果]]の対象店舗数をUITabData[タブ情報].照会ヘッダー部対象期間へ設定する。
		String viewTaishoKikan = CommonUtil.formattYMDSlash(taishoDt);
		if(!TaishoKikan.DAY.equals(taishoKikan)) {
			viewTaishoKikan = viewTaishoKikan+"～"+CommonUtil.formattYMDSlash(taishoDtTo);
		}
		uiSearchData.setTaishoKikan(viewTaishoKikan);
		BigDecimal decTenpoCnt = new BigDecimal("0");
		BigDecimal decTaishoTenpoCnt = new BigDecimal("0");
		List listUINipo = null;
		//３－３．下記の判断で検索を実行します。
		if(requestDto.getViewId().equals(CampNipoRefUtil.VIEW_ID_SIBU)) {
			//A．DAO【支部一覧日報情報】を実行し、戻り値List[[検索結果]]を取得する。
			if(TaishoKikan.DAY.equals(taishoKikan)) {
				listUINipo = getCampNipoRefUINipoSibuDao().select(targetTable, sysDate
						    , userId, userTypeCd, limitFlg
							, companyCd, campId, menuTotaledKbn
							, tenpoShubetu, shukeiKbn
							, taishoKikan, taishoDt, taishoDtTo
							, taishoJoken, hyojiTaisho, null
							, zennenDataShubetu, taishoDtFromLastDay
							, targetZenneClm);
			}
			else {
				listUINipo = getCampNipoRefUINipoSibuDao().selectDays(targetTable, sysDate
					    , userId, userTypeCd, limitFlg
						, companyCd, campId, menuTotaledKbn
						, tenpoShubetu, shukeiKbn
						, taishoKikan, taishoDt, taishoDtTo
						, taishoJoken, hyojiTaisho, null
						, zennenDataShubetu, taishoDtFromLastDay
						, targetZenneClm);
				
			}
		}
		else if(requestDto.getViewId().equals(CampNipoRefUtil.VIEW_ID_MISE)) {
			//B．DAO【店舗一覧日報情報】を実行し、戻り値List[[検索結果]]を取得する。
			if(TaishoKikan.DAY.equals(taishoKikan)) {
				listUINipo = getCampNipoRefUINipoMiseDao().select(targetTable, sysDate
					        , userId, userTypeCd, limitFlg
							, companyCd, campId, menuTotaledKbn
							, tenpoShubetu, shukeiKbn
							, taishoKikan, taishoDt, taishoDtTo
							, taishoJoken, hyojiTaisho, null
							, zennenDataShubetu
							, requestDto.getRankKind(), taishoDtFromLastDay
							, targetZenneClm);
			}
			else {
				listUINipo = getCampNipoRefUINipoMiseDao().selectDays(targetTable, sysDate
				        , userId, userTypeCd, limitFlg
						, companyCd, campId, menuTotaledKbn
						, tenpoShubetu, shukeiKbn
						, taishoKikan, taishoDt, taishoDtTo
						, taishoJoken, hyojiTaisho, null
						, zennenDataShubetu
						, requestDto.getRankKind(), taishoDtFromLastDay
						, targetZenneClm);
			}
		}
		//３－５．List[[検索結果]]の対象店舗数をUITabData[タブ情報].照会ヘッダー部対象店舗数へ設定する。
		//３－４．検索結果値の判断をする。
		if(listUINipo == null || listUINipo.size()==0) {
			return null;
		}
		if(listUINipo.size()>0){
			//最終行のエンティティーを取得します。
			UINipo lastData = (UINipo)listUINipo.get(listUINipo.size()-1);
			if(lastData.getTaishoTenpoCnt() != null) {
				decTaishoTenpoCnt = lastData.getTaishoTenpoCnt();
			}
			if(lastData.getTenpoCnt() != null) {
				decTenpoCnt = lastData.getTenpoCnt();
			}
		}
		if(decTenpoCnt.compareTo(new BigDecimal("0"))==0) {
			listUINipo = new ArrayList(0);
		}
		uiSearchData.setTaishoTenpoCnt(decTaishoTenpoCnt);
		//３－６．List[[検索結果]]をUITabData[タブ情報]へ設定する。
		uiSearchData.setListData(listUINipo);
			
		//５．List[[タブ情報]]をリターンする。
		List listSearchData = new ArrayList(0);
		listSearchData.add(uiSearchData);
		return listSearchData;
	}
	
    /**
     * 事前条件処理
     * 
     * @param dto
     */
    private void validate(RequestNipoDto requestDto)
    {	
        if (requestDto.getSelfSessionDto() == null) {
            throw new MissingDataException("セッション情報");
        }
    	BirdDateInfo dateInfo = requestDto.getSelfSessionDto().getBirdDateInfo();
        if (dateInfo == null) {
            throw new MissingDataException("BIRD日付情報");
        }
    	BirdUserInfo userInfo = requestDto.getSelfSessionDto().getBirdUserInfo();
        if (userInfo == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
		String companyCd = requestDto.getCompanyCd();
		if(CommonUtil.isNull(companyCd)) {
			throw new NotNullException("会社コード");
		}
		String campId = requestDto.getCampId();
		if(CommonUtil.isNull(campId)) {
			throw new NotNullException("キャンペーン");
		}
		String menuTotaledKbn = requestDto.getMenuTotaledKbn();
		if(CommonUtil.isNull(menuTotaledKbn)) {
			throw new NotNullException("メニュー集計区分");
		}
		String tenpoShubetu = requestDto.getTenpoShubetu();
		if(CommonUtil.isNull(tenpoShubetu)) {
			throw new NotNullException("店舗種別");
		}
		String shukeiKbn = requestDto.getShukeiKbn();
		if(CommonUtil.isNull(shukeiKbn)) {
			throw new NotNullException("集計区分");
		}
		String taishoKikan = requestDto.getTaishoKikan();
		if(CommonUtil.isNull(taishoKikan)) {
			throw new MissingDataException("対象期間");
		}
		if(TaishoKikan.TOTAL.equals(taishoKikan) == false) {
			//対象期間が『単日』又は『期間指定』の場合、
			//対象日(開始日)のチェックを行います。
			String taishoDt = requestDto.getTaishoDt();
			if(CommonUtil.isNull(taishoDt)) {
				throw new NotNullException("対象日");
			}
			if(TaishoKikan.DAYS.equals(taishoKikan)) {
				//対象期間が『期間指定』の場合、
				//対象終了日のチェックを行います。
				String taishoDtTo = requestDto.getTaishoDtTo();
				if(CommonUtil.isNull(taishoDtTo)) {
					throw new NotNullException("対象終了日");
				}
				if(taishoDt.compareTo(taishoDtTo)>0) {
					throw new NotRelevantException("期間指定","FROM < TO");
				}
			}
		}
		String taishoJoken = requestDto.getTaishoJoken();
		if(CommonUtil.isNull(taishoJoken)) {
			throw new MissingDataException("対象条件");
		}
		if(!TaishoJoken.CODE_ALL.equals(taishoJoken)) {
			String hyojiTaisho = requestDto.getHyojiTaisho();
			if(CommonUtil.isNull(hyojiTaisho)) {
				throw new NotNullException("対象支部");
			}
		}
		String zennenDataShubetu = requestDto.getZennenDataShubetu();
		if(CommonUtil.isNull(zennenDataShubetu)) {
			throw new NotNullException("前年データ種別");
		}
    }
	/**
	 * @return campNipoRefUINipoMiseDao を戻します。
	 */
	public UINipoMiseDao getCampNipoRefUINipoMiseDao() {
		return campNipoRefUINipoMiseDao;
	}
	/**
	 * @param campNipoRefUINipoMiseDao 設定する campNipoRefUINipoMiseDao。
	 */
	public void setCampNipoRefUINipoMiseDao(UINipoMiseDao campNipoRefUINipoMiseDao) {
		this.campNipoRefUINipoMiseDao = campNipoRefUINipoMiseDao;
	}
	/**
	 * @return campNipoRefUINipoSibuDao を戻します。
	 */
	public UINipoSibuDao getCampNipoRefUINipoSibuDao() {
		return campNipoRefUINipoSibuDao;
	}
	/**
	 * @param campNipoRefUINipoSibuDao 設定する campNipoRefUINipoSibuDao。
	 */
	public void setCampNipoRefUINipoSibuDao(UINipoSibuDao campNipoRefUINipoSibuDao) {
		this.campNipoRefUINipoSibuDao = campNipoRefUINipoSibuDao;
	}
}
