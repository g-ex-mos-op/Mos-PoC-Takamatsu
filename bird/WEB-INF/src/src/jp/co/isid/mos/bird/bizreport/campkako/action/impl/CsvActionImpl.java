/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;


/**
 * CSVダウンロードアクション
 * 
 * @author xnkusama
 *
 */
public class CsvActionImpl extends CsvOutput2ActionImpl {
    /* アクションID：CSVダウンロード処理 */
    public static final String downloadCsv_ACTION_ID = "BBR14A08";
    
    /** DTO【自画面リクエスト用】*/
    private RequestNipoDto campKakoViewRequestDto;
    /** DTO【自画面セッション用】*/
    private SessionNipoDto campKakoSessionDto;
	/**
	 * @return campNipoRefSesDto を戻します。
	 */
	public SessionNipoDto getCampKakoSessionDto() {
		return campKakoSessionDto;
	}
	/**
	 * @param campNipoRefSesDto 設定する campNipoRefSesDto。
	 */
	public void setCampKakoSessionDto(SessionNipoDto campNipoRefSesDto) {
		this.campKakoSessionDto = campNipoRefSesDto;
	}
	/**
	 * @return campNipoRefViewDto を戻します。
	 */
	public RequestNipoDto getCampKakoViewRequestDto() {
		return campKakoViewRequestDto;
	}
	/**
	 * @param campKakoViewRequestDto 設定する campNipoRefReqDto。
	 */
	public void setCampKakoViewRequestDto(RequestNipoDto campNipoRefReqDto) {
		this.campKakoViewRequestDto = campNipoRefReqDto;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl#downloadCsv()
	 */
	public void downloadCsv() throws IOException, ApplicationException {
    	//1.【自画面共通】表示検索データ設定処理
    	((RequestNipoDto) campKakoViewRequestDto).setSelfSessionDto(getCampKakoSessionDto());
        super.setCsvOutputDto(campKakoViewRequestDto);
		// ダウンロード処理
		super.downloadCsv();
	}
	
}
