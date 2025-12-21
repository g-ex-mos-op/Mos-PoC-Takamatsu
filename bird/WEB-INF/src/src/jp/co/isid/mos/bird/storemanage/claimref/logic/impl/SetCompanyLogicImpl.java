/**
 * 
 */
package jp.co.isid.mos.bird.storemanage.claimref.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetHyojiTaishoLogicImpl;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;
import jp.co.isid.mos.bird.storemanage.claimref.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.storemanage.claimref.util.VoiceRefUtil;

/**
 * 会社条件項目情報設定ロジック
 * 
 * @author xkinu
 *
 */
public class SetCompanyLogicImpl implements SetCompanyLogic {
    /** ロジックID */
    public static final String LOGIC_ID = VoiceRefUtil.SCREEN_ID+"L01";
	/** LOGIC【表示対象取得】*/
	private GetHyojiTaishoLogic commonGetHyojiTaishoLogic;
	/**
	 * 
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCompanyLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto, java.lang.String)
	 */
	public void execute(SessionDto sessionDto, RequestDto requestDto) {
		//１.事前条件処理
		validate(requestDto);
		String companyCd = requestDto.getCompanyCd();
		//２.DTO【自画面Session】.List[[対象条件]]を取得します。
    	List listTaishoJoken = sessionDto.getListTaishoJoken(companyCd);
    	//３.DTO【自画面Session】.List[[対象条件]]の件数分下記の処理を行います。
    	for (int i=0; i<listTaishoJoken.size(); i++) {
    		//3-1.現行インデックスの[対象条件]から対象条件を取得します。
    		SelectItem kbn = (SelectItem)listTaishoJoken.get(i);
    		String taishoJoken = kbn.getValue().toString();
    		if(TaishoJoken.CODE_ALL.equals(taishoJoken)) {
    			continue;
    		}
    		//3-2. 共通ロジック【表示対象データ検索ロジック】実行し、List[[事業本部]]を取得する。
    		Map params = new HashMap();
    		params.put(GetHyojiTaishoLogicImpl.PM_COMPANY_CD, companyCd);
    		params.put(GetHyojiTaishoLogicImpl.PM_TAISHOJOKEN, taishoJoken);
    		params.put(GetHyojiTaishoLogicImpl.PM_APP_DATE, sessionDto.getBirdDateInfo().getAppDate());
	        Map logigMap = getCommonGetHyojiTaishoLogic().execute(params);
	        List listHyojiTaisho = (List)logigMap.get(GetHyojiTaishoLogicImpl.RK_LIST);
       		if (listHyojiTaisho != null && listHyojiTaisho.size() > 0) {
		        //DTO【自画面Session】へ対象条件コードをキーにList[[表示対象]]を格納する。
	        	sessionDto.setListHyojiTaisho(companyCd, taishoJoken, listHyojiTaisho);
	        }
       		if(TaishoJoken.CODE_SIBU.equals(taishoJoken)) {
       			//１．List[[表示対象ブロック]]を取得します。
       			List listBlock = (List)logigMap.get(GetHyojiTaishoLogicImpl.RK_LIST_BLOCK);
       			if(listBlock != null && listBlock.size()>0) {
       			//２．List[[表示対象ブロック]]が存在する場合は、
       			//DTO【自画面Session】List[[ブロック]]へList[[表示対象ブロック]]を格納する。
       				sessionDto.setListBlock(listBlock);
       			}
       		}
    	}
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
	 * @return commonGetHyojiTaishoLogic を戻します。
	 */
	public GetHyojiTaishoLogic getCommonGetHyojiTaishoLogic() {
		return commonGetHyojiTaishoLogic;
	}
	/**
	 * @param commonGetHyojiTaishoLogic 設定する commonGetHyojiTaishoLogic。
	 */
	public void setCommonGetHyojiTaishoLogic(
			GetHyojiTaishoLogic commonGetHyojiTaishoLogic) {
		this.commonGetHyojiTaishoLogic = commonGetHyojiTaishoLogic;
	}

}
