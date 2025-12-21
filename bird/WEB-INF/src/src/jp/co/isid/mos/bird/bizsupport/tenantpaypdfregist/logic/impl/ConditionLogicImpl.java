/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

/**
 * 初期化ロジック
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = TenantPayPdfRegistUtil.LOGIC_ID_CONDITION;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.campniporef.logic.ConditionLogic#execute(jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto, jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto)
	 */
	public void execute(RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);
		//２．システム日付の翌月・当月含めて過去14ヶ月分のList[[対象年月]]を作成します。
		String sysDate = requestDto.getSessionDto().getBirdDateInfo().getSysDate();
		List listTaishoYm = TenantPayPdfRegistUtil.creatListNengetu(sysDate, 14);
		//３．DTO【自画面Session】.List[[対象年月]]へ処理２のList[[対象年月]]を設定ます。
		requestDto.getSessionDto().setListTaishoYm(listTaishoYm);
		//４．DTO【自画面Request】.List[[対象年月]]へ処理２のList[[対象年月]]を設定ます。
		requestDto.setListTaishoYm(listTaishoYm);
        //５．DTO【自画面Request】.店コードへデフォルト値として空の値を設定します。
		requestDto.setMiseCd("");
        //６．DTO【自画面Request】.対象年月へデフォルト値としてシステム日付の当月の値を設定します。
		requestDto.setTaishoYm(sysDate.substring(0,6));
        //７．DTO【自画面Request】.回数へデフォルト値として空の値を設定します。
		requestDto.setKaisuu("");
	}
    /**
     * 事前条件処理
     * 
     * @param requestDto
     */
    private void validate(RequestDto requestDto)
    {
    	//パラメータDTO【自画面リクエスト】必須チェック
        if (requestDto == null) {
            throw new MissingDataException("リクエスト情報");
        }
    	//パラメータDTO【自画面リクエスト】.DTO【自画面セッション】必須チェック
		SessionDto sessionDto = requestDto.getSessionDto();
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        //パラメータDTO【自画面リクエスト】.DTO【自画面セッション】.BIRD日付情報必須チェック
        if (sessionDto.getBirdDateInfo() == null) {
            throw new MissingDataException("BIRD日付情報");
        }
        //パラメータDTO【自画面リクエスト】.DTO【自画面セッション】.BIRDユーザー情報必須チェック
        if (sessionDto.getBirdUserInfo() == null) {
            throw new MissingDataException("BIRDユーザー情報");
        }
    }
}
