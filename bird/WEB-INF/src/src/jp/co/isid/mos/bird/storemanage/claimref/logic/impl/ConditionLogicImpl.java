/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.dao.CodCompanyDao;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;
import jp.co.isid.mos.bird.storemanage.claimref.logic.ConditionLogic;
import jp.co.isid.mos.bird.storemanage.claimref.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.storemanage.claimref.util.VoiceRefUtil;

/**
 * 条件項目の取得設定ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = VoiceRefUtil.SCREEN_ID+"L02";
    /** DAO【会社情報取得】*/
    private CodCompanyDao codCompanyDao;
	/** 共通ロジック【ユーザの汎用画面ロール制御情報取得】*/
    private GetGamenRoleLogic gamenRoleLogic;
    /** LOGIC【条件項目会社情報設定】*/
    private SetCompanyLogic voiceRefSetCompanyLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionVoiceDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestVoiceDto)
	 */
	public void execute(SessionDto sessionDto, RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		//２．会社コードプルダウンリスト設定
		List listCompany = getCodCompanyDao().select(sessionDto.getBirdUserInfo().getUserID());
		sessionDto.setListCompany(listCompany);
		CodCompany com = (CodCompany)(sessionDto.getListCompany().get(0));
		requestDto.setCompanyCd(com.getCompanyCd());
		//３．会社変更ロジック実行
		getVoiceRefSetCompanyLogic().execute(sessionDto, requestDto);
		//４．対象年月を設定します。
		sessionDto.setListTaishoNengetu();
        //個店ポータル権限ユーザ判断処理
        boolean isView = CommonUtil.isRoleUser(getGamenRoleLogic()
        		, sessionDto.getBirdUserInfo().getUserID()
        		, VoiceRefUtil.SCREEN_ID
        		, "01");
        sessionDto.setViewKotenPortal(isView);
		
	}
    /**
     * 事前条件処理
     * 
     * @param dto
     */
    private void validate(RequestDto requestDto)
    {
        if (requestDto.getSelfSessionDto() == null) {
            throw new MissingDataException("セッション情報");
        }
    }
	/**
	 * @return codCompanyDao を戻します。
	 */
	public CodCompanyDao getCodCompanyDao() {
		return codCompanyDao;
	}
	/**
	 * @param codCompanyDao 設定する codCompanyDao。
	 */
	public void setCodCompanyDao(CodCompanyDao codCompanyDao) {
		this.codCompanyDao = codCompanyDao;
	}
	/**
	 * @return voiceRefSetCompanyLogic を戻します。
	 */
	public SetCompanyLogic getVoiceRefSetCompanyLogic() {
		return voiceRefSetCompanyLogic;
	}
	/**
	 * @param voiceRefSetCompanyLogic 設定する voiceRefSetCompanyLogic。
	 */
	public void setVoiceRefSetCompanyLogic(
			SetCompanyLogic voiceRefSetCompanyLogic) {
		this.voiceRefSetCompanyLogic = voiceRefSetCompanyLogic;
	}
	/**
	 * @return gamenRoleLogic を戻します。
	 */
	public GetGamenRoleLogic getGamenRoleLogic() {
		return gamenRoleLogic;
	}
	/**
	 * @param gamenRoleLogic 設定する gamenRoleLogic。
	 */
	public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
		this.gamenRoleLogic = gamenRoleLogic;
	}
}
