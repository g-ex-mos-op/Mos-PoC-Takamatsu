/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCampaignLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.dao.MstCampDateDao;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.bizreport.common.entity.CodKikanSitei;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * 会社条件項目情報設定ロジック
 * 
 * @author xkinu
 *
 */
public class SetCompanyLogicImpl implements SetCompanyLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampNipoRefUtil.SCREEN_ID+"L02";
	/* DAO【キャンペーン日付マスタ】*/
	MstCampDateDao commonCampaignMstCampDateDao;
	/* LOGIC【キャンペーン変更】*/
	SetCampaignLogic campNipoRefChangeCampaignLogic;
	/**
	 * 
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCompanyLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto, java.lang.String)
	 */
	public void execute(RequestNipoDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		SessionNipoDto sessionDto = (SessionNipoDto)(requestDto.getSelfSessionDto());
		//２．パラメーター．DTO【Request】．DTO【Session】．BIRD日付情報からアプリ日付を取得します。
		String appDate = sessionDto.getBirdDateInfo().getAppDate();
		//キャンペーン共通【static定数クラス】.年度リスト作成取得を実行し、List[[年度]]を取得する。
		List listNendo = CommonUtil.creatListNendo(appDate, 1);
		//４．List[[年度]]をパラメータ.DTO【自画面Session】.List[[年度]]へ設定する。
		sessionDto.setListNendo(requestDto.getCompanyCd(), listNendo);
		String nendo = ((CodKikanSitei)listNendo.get(0)).getCode();
		
		//５．DAO【キャンペーン対象日付マスタ】.表示公開対象検索を実行し、
		//    検索結果List[[キャンペーン]]を取得する。
		List listCampaign = getCommonCampaignMstCampDateDao().selectOpen(
				  sessionDto.getBirdDateInfo().getSysDate()
				, sessionDto.getBirdUserInfo().getUserID()
				, sessionDto.getUserTypeCd()
				, sessionDto.getBirdUserInfo().isLimit()
				, requestDto.getCompanyCd()
				, 1);
		//６．処理５の検索結果List[[キャンペーン]]の結果件数が０件の場合は、
		//    ここでリターンします。
		if(listCampaign.size()<1) {
			return;
		}
		//７．処理５の検索結果List[[キャンペーン]]を
		//    パラメータ.DTO【自画面Session】.List[[キャンペーン]]へ設定する。
		sessionDto.setListCamp(requestDto.getCompanyCd(), nendo, listCampaign);
		
		//８．処理５検索結果List[[キャンペーン]]件数分、LOGIC【キャンペーン変更】を実行する。
		for(int i=0; i<listCampaign.size(); i++) {
			String campId = ((MstCampDate)listCampaign.get(i)).getCampId();
			requestDto.setCampId(campId);
			getCampNipoRefChangeCampaignLogic().execute(requestDto);
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
	public SetCampaignLogic getCampNipoRefChangeCampaignLogic() {
		return campNipoRefChangeCampaignLogic;
	}
	/**
	 * @param logic 設定する campNipoRefChangeCampaignLogic。
	 */
	public void setCampNipoRefChangeCampaignLogic(
			SetCampaignLogic logic) {
		this.campNipoRefChangeCampaignLogic = logic;
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
