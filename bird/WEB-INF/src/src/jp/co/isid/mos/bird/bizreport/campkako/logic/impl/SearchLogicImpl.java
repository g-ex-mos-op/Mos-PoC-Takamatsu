package jp.co.isid.mos.bird.bizreport.campkako.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.dao.UINipoMiseDao;
import jp.co.isid.mos.bird.bizreport.campkako.dao.UINipoSibuDao;
import jp.co.isid.mos.bird.bizreport.campkako.entity.UINipoMise;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campkako.util.CampKakoUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.logic.GetCampMenuLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 検索結果取得ロジック
 * 
 * @author xnkusama
 * 
 * 更新日:2011/06/24 xkinu ADD 期間合計時に店舗種別の条件追加対応に伴う店舗種別を引数へ追加
 *
 */
public class SearchLogicImpl implements SearchLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampKakoUtil.SCREEN_ID + "L04";
    /** DAO【支部一覧日報情報】*/
    private UINipoSibuDao campNipoRefUINipoSibuDao;
    /** DAO【店舗一覧日報情報】*/
    private UINipoMiseDao campNipoRefUINipoMiseDao;
	/** LOGIC【キャンペーンメニュー取得】*/
	private GetCampMenuLogic commonCampaignGetCampMenuKakoLogic;
    
    /**
     * 
     */
	public List execute(RequestNipoDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		
		String userId = requestDto.getSelfSessionDto().getBirdUserInfo().getUserID();
		String userTypeCd = requestDto.getSelfSessionDto().getUserTypeCd();
		boolean limitFlg = requestDto.getSelfSessionDto().getBirdUserInfo().isLimit();
		String companyCd = requestDto.getCompanyCd();
		String tenpoShubetu = requestDto.getTenpoShubetu();
		String campId = requestDto.getCampId();
		String menuTotaledKbn = requestDto.getMenuTotaledKbn();
		String shukeiKbn = requestDto.getShukeiKbn();
		String hyojiTaisho = requestDto.getHyojiTaisho();
		List listUINipo = null;
		//２．下記の判断で検索を実行する。
        //【支部一覧画面】
		if(requestDto.getViewId().equals(CampKakoUtil.VIEW_ID_SIBU)) {
			//A．DAO【支部一覧日報情報】を実行し、戻り値List[[検索結果]]を取得する。
            if (MenuTotaledKbn.CODE_TOTAL.equals(requestDto.getMenuTotaledKbn())
                    && ShukeiKbn.OUT_RC.equals(requestDto.getShukeiKbn())) {
                //メニュー：合計 かつ 集計区分：直営・販社含まない
				listUINipo = getCampNipoRefUINipoSibuDao().selectGokeiSibu(
        						      userId, userTypeCd, limitFlg
        							, companyCd, tenpoShubetu, campId, menuTotaledKbn
        							, shukeiKbn, requestDto.getMstCampDate().getCampFrom()
        							, requestDto.getMstCampDate().getCampTo());
            }
            else {
                //（メニュー：合計 かつ 集計区分：直営・販社含まない）以外
                listUINipo = getCampNipoRefUINipoSibuDao().select(
                                      userId, userTypeCd, limitFlg
                                    , companyCd, tenpoShubetu, campId, menuTotaledKbn
                                    , shukeiKbn, requestDto.getMstCampDate().getCampFrom()
                                    , requestDto.getMstCampDate().getCampTo());
            }
		}
        //【店一覧画面】
		else if(requestDto.getViewId().equals(CampKakoUtil.VIEW_ID_MISE)) {
			//B．DAO【店舗一覧日報情報】を実行し、戻り値List[[検索結果]]を取得する。
            listUINipo = getCampNipoRefUINipoMiseDao()
                            .select(userId, userTypeCd, limitFlg
                                    , companyCd, tenpoShubetu, campId, menuTotaledKbn
                                    , shukeiKbn
                                    , requestDto.getMstCampDate().getCampFrom()
                                    , requestDto.getMstCampDate().getCampTo()
                                    , hyojiTaisho, null
                                    , requestDto.getRankKind());
		}
		//３－３．検索結果値の判断をする。
		if(listUINipo == null) {
			return null;
		}
		
        //検索結果が1レコード以下（合計行があるので最低1行）は、結果をクリア
		if(listUINipo.size() <= 1) {
			listUINipo = new ArrayList(0);
		}

        // 店別画面の場合のみ合計行処理
        if(requestDto.getViewId().equals(CampKakoUtil.VIEW_ID_MISE)
                && !MenuTotaledKbn.CODE_TOTAL.equals(menuTotaledKbn)
                && !listUINipo.isEmpty()) {
            //総合計行
            UINipoMise nipoSoGokei = (UINipoMise) listUINipo.get(listUINipo.size() - 1);
            //メニュー別合計行
            UINipoMise nipoMenuGokei = (UINipoMise) listUINipo.get(listUINipo.size() - 2);
            //売上系の総合計をメニュー別合計行の値に置き換える
            nipoSoGokei.setUriage(nipoMenuGokei.getUriage());
            nipoSoGokei.setUriageZen(nipoMenuGokei.getUriageZen());
            nipoSoGokei.setKyakusu(nipoMenuGokei.getKyakusu());
            nipoSoGokei.setKyakusuZen(nipoMenuGokei.getKyakusuZen());
        }

        // メニュー集計区分が『メニュー合計』の場合は下記の処理を行います。
        if(menuTotaledKbn.equals(MenuTotaledKbn.CODE_TOTAL)) {
            //1.LOGIC【キャンペーンメニュー取得】を実行します。戻り値List[[単品メニュー]]を取得します。
            List listMenu = getCommonCampaignGetCampMenuKakoLogic().execute(campId, MenuTotaledKbn.CODE_TANPIN);
            //2.DTO【自画面Session】.List[[メニュー]]へ処理1の戻り値List[[単品メニュー]]を設定します。
            requestDto.getSelfSessionDto().setListMenu(campId, menuTotaledKbn, listMenu);
        }
        
        //５．List[[タブ情報]]をリターンする。
		return listUINipo;
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
		String shukeiKbn = requestDto.getShukeiKbn();
		if(CommonUtil.isNull(shukeiKbn)) {
			throw new NotNullException("集計区分");
		}
		if(!TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken())) {
			String hyojiTaisho = requestDto.getHyojiTaisho();
			if(CommonUtil.isNull(hyojiTaisho)) {
				throw new NotNullException("対象支部");
			}
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
	/**
	 * @return commonCampGetCampMenuLogic を戻します。
	 */
	public GetCampMenuLogic getCommonCampaignGetCampMenuKakoLogic() {
		return commonCampaignGetCampMenuKakoLogic;
	}
	/**
	 * @param commonCampGetCampMenuLogic 設定する commonCampGetCampMenuLogic。
	 */
	public void setCommonCampaignGetCampMenuKakoLogic(
			GetCampMenuLogic commonCampGetCampMenuLogic) {
		this.commonCampaignGetCampMenuKakoLogic = commonCampGetCampMenuLogic;
	}
}
