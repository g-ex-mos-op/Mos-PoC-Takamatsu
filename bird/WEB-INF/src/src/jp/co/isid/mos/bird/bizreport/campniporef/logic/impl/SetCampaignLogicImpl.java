/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic.impl;

import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCampaignLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * キャンペーン条件項目情報設定ロジック
 * 
 * @author xkinu
 *
 */
public class SetCampaignLogicImpl implements SetCampaignLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampNipoRefUtil.SCREEN_ID+"L01";
	/** LOGIC【表示対象取得】*/
	private GetHyojiTaishoLogic commonCampGetHyojiTaishoLogic;

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
            throw new MissingDataException("会社コード");
        }
        if (CommonUtil.isNull(requestDto.getCampId())) {
            throw new MissingDataException("キャンペーン識別番号");
        }
    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCampaignLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto, java.lang.String)
	 */
	public void execute(RequestNipoDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		//２．集計区分の分のList[[表示対象]]を取得し、DTO【自画面Session】へ設定する。
		settingHyojiTaisho((SessionNipoDto)requestDto.getSelfSessionDto(), requestDto.getCompanyCd(), requestDto.getCampId());
	}
    /**
     * 表示対象 情報DTO格納処理
     * 
     * ロジック【表示対象データ検索ロジック】実行
     * 
     * @param sessionDto
     * @param companyCd
     * @param campId
     */
    private void settingHyojiTaisho(SessionNipoDto sessionDto, String companyCd, String campId) {
    	String userTypeCd = sessionDto.getUserTypeCd();
    	if(UserType.HONBU.equals(userTypeCd) || UserType.ONER.equals(userTypeCd)) {
	    	//1.DTO【自画面Session】.List[[集計区分]]を取得します。
	    	List listShukeiKbn = sessionDto.getListShukeiKbn();
	    	//2.DTO【自画面Session】.List[[集計区分]]の件数分下記の処理を行います。
	    	for (int i=0; i<listShukeiKbn.size(); i++) {
	    		//2-1.現行インデックスの[集計区分]から集計区分を取得します。
	    		SelectItem kbn = (SelectItem)listShukeiKbn.get(i);
	    		String shukeiKbn = kbn.getValue().toString();
	    		//2-2. ロジック【表示対象データ検索ロジック】実行し、List[[事業本部]]を取得する。
		        Map logigMap = getCommonCampGetHyojiTaishoLogic().execute(
		        		sessionDto.getBirdDateInfo()
		        		, sessionDto.getBirdUserInfo()
		        		, companyCd, campId, shukeiKbn);
		        List listHyojiTaisho = (List)logigMap.get(GetHyojiTaishoLogic.RK_LIST);
	       		if (listHyojiTaisho != null && listHyojiTaisho.size() > 0) {
			        //DTO【自画面Session】へ対象条件コードをキーにList[[表示対象]]を格納する。
		        	sessionDto.setListHyojiTaisho(campId, shukeiKbn, listHyojiTaisho);
		        }
	    	}
    	}
    	else if(UserType.TENPO.equals(userTypeCd)) {
    		//2-2. ロジック【表示対象データ検索ロジック】実行し、List[[店舗]]を取得する。
	        Map logigMap = getCommonCampGetHyojiTaishoLogic().execute(
	        		sessionDto.getBirdDateInfo()
	        		, sessionDto.getBirdUserInfo()
	        		, companyCd, campId, TaishoJoken.CODE_MISE);
	        List listHyojiTaisho = (List)logigMap.get(GetHyojiTaishoLogic.RK_LIST);
       		if (listHyojiTaisho != null && listHyojiTaisho.size() > 0) {
		        //DTO【自画面Session】へ対象条件コードをキーにList[[表示対象]]を格納する。
	        	sessionDto.setListHyojiTaisho(campId, TaishoJoken.CODE_MISE, listHyojiTaisho);
	        }
    	}
    }
	/**
	 * @return commonCampGetHyojiTaishoLogic を戻します。
	 */
	public GetHyojiTaishoLogic getCommonCampGetHyojiTaishoLogic() {
		return commonCampGetHyojiTaishoLogic;
	}
	/**
	 * @param commonCampGetHyojiTaishoLogic 設定する commonCampGetHyojiTaishoLogic。
	 */
	public void setCommonCampGetHyojiTaishoLogic(
			GetHyojiTaishoLogic commonCampGetHyojiTaishoLogic) {
		this.commonCampGetHyojiTaishoLogic = commonCampGetHyojiTaishoLogic;
	}
}
