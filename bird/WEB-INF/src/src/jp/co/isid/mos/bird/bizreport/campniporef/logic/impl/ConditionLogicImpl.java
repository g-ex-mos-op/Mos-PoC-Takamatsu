/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.logic.SetCompanyLogic;
import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.logic.CompanyListLogic;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 条件項目の取得設定ロジック
 * 
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = CampNipoRefUtil.SCREEN_ID+"L03";
    /** LOGIC【会社情報取得】*/
    CompanyListLogic companyListLogic;
    /** LOGIC【条件項目会社情報設定】*/
    SetCompanyLogic campNipoRefSetCompanyLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto)
	 */
	public void execute(RequestNipoDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		//２．会社コードプルダウンリスト設定
		Map params = new HashMap();
		List listCompany = getCompanyListLogic().execute(params);
		requestDto.getSelfSessionDto().setListCompany(listCompany);
		CodCompany com = (CodCompany)(requestDto.getSelfSessionDto().getListCompany().get(0));
		requestDto.setCompanyCd(com.getCompanyCd());
		//３．会社変更ロジック実行
		getCampNipoRefSetCompanyLogic().execute(requestDto);
		
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
    }
	/**
	 * @return companyListLogic を戻します。
	 */
	public CompanyListLogic getCompanyListLogic() {
		return companyListLogic;
	}
	/**
	 * @param companyListLogic 設定する companyListLogic。
	 */
	public void setCompanyListLogic(CompanyListLogic companyListLogic) {
		this.companyListLogic = companyListLogic;
	}
	/**
	 * @return campNipoRefSetCompanyLogic を戻します。
	 */
	public SetCompanyLogic getCampNipoRefSetCompanyLogic() {
		return campNipoRefSetCompanyLogic;
	}
	/**
	 * @param campNipoRefSetCompanyLogic 設定する campNipoRefSetCompanyLogic。
	 */
	public void setCampNipoRefSetCompanyLogic(
			SetCompanyLogic campNipoRefSetCompanyLogic) {
		this.campNipoRefSetCompanyLogic = campNipoRefSetCompanyLogic;
	}
}
