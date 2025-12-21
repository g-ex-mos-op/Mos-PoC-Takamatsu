package jp.co.isid.mos.bird.bizreport.campkako.logic.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.logic.SetCampaignLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.bizreport.campkako.util.CampKakoUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.dao.MstCampDateDao;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 会社条件項目情報設定ロジック
 * 
 * @author xnkusama
 *
 */
public class SetCompanyLogicImpl implements SetCompanyLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampKakoUtil.SCREEN_ID + "L02";
	/* DAO【キャンペーン対象期間マスタ】*/
	private MstCampDateDao commonCampaignMstCampDateDao;
	/* LOGIC【キャンペーン変更】*/
	private SetCampaignLogic campKakoSetCampaignLogic;
    
    
    /* 年度開始月日 */
    private static final String NENDO_START_MD = "0401";
    /* 年度終了月日 */
    private static final String NENDO_END_MD = "0331";
    
    /**
     * 
     */
	public void execute(RequestNipoDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		SessionNipoDto sessionDto = (SessionNipoDto)(requestDto.getSelfSessionDto());
		//２．パラメーター．DTO【Request】．DTO【Session】．BIRD日付情報からアプリ日付を取得します。
		String appDate = sessionDto.getBirdDateInfo().getAppDate();
		//キャンペーン共通【static定数クラス】.年度リスト作成取得を実行し、List[[年度]]を取得する。
		List listNendo = CommonUtil.creatListNendo(appDate, 3);
		//４．List[[年度]]をパラメータ.DTO【自画面Session】.List[[年度]]へ設定する。
		sessionDto.setListNendo(requestDto.getCompanyCd(), listNendo);
        
        for (Iterator ite = listNendo.iterator(); ite.hasNext();) {
    		String nendo = ((CodKikanSitei)ite.next()).getCode();
    		
    		//５．DAO【キャンペーン対象日付マスタ】.表示公開対象検索を実行し、
    		//    検索結果List[[キャンペーン]]を取得する。
    		List listCampaign = getCommonCampaignMstCampDateDao().selectClose(
                    sessionDto.getBirdDateInfo().getSysDate()
    				, sessionDto.getBirdUserInfo().getUserID()
    				, sessionDto.getUserTypeCd()
    				, sessionDto.getBirdUserInfo().isLimit()
    				, requestDto.getCompanyCd()
    				, nendo + NENDO_START_MD
                    , ((new Integer(nendo)).intValue() + 1) + NENDO_END_MD);
    		//７．処理５の検索結果List[[キャンペーン]]を
    		//    パラメータ.DTO【自画面Session】.List[[キャンペーン]]へ設定する。
    		sessionDto.setListCamp(requestDto.getCompanyCd(), nendo, (listCampaign != null && listCampaign.isEmpty()) ? null : listCampaign);
    		
    		//８．処理５検索結果List[[キャンペーン]]件数分、LOGIC【キャンペーン変更】を実行する。
            requestDto.setNendo(nendo);
			getCampKakoSetCampaignLogic().execute(requestDto);
        }
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
        if (requestDto.getSelfSessionDto().getBirdDateInfo() == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        if (requestDto.getSelfSessionDto().getBirdUserInfo() == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
        if (CommonUtil.isNull(requestDto.getCompanyCd())) {
            throw new NotNullException("会社コード");
        }
    }
	/**
	 * @return campNipoRefChangeCampaignLogic を戻します。
	 */
	public SetCampaignLogic getCampKakoSetCampaignLogic() {
		return campKakoSetCampaignLogic;
	}
	/**
	 * @param logic 設定する campNipoRefChangeCampaignLogic。
	 */
	public void setCampKakoSetCampaignLogic(
			SetCampaignLogic logic) {
		this.campKakoSetCampaignLogic = logic;
	}
	/**
	 * @return commonCampaignMstCampDateDao を戻します。
	 */
	public MstCampDateDao getCommonCampaignMstCampDateDao() {
		return commonCampaignMstCampDateDao;
	}
	/**
	 * @param dao 設定する commonCampaignMstCampDateDao。
	 */
	public void setCommonCampaignMstCampDateDao(
			MstCampDateDao dao) {
		this.commonCampaignMstCampDateDao = dao;
	}

}
