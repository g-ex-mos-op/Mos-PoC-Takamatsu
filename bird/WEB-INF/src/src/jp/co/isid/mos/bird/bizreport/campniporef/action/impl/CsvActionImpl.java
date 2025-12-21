/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campniporef.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.bizreport.campniporef.util.CampNipoRefUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;


/**
 * CSVダウンロードアクション
 * 
 * @author xkinu
 *
 */
public class CsvActionImpl extends CsvOutput2ActionImpl {
	private static final String ACTION_ID = CampNipoRefUtil.SCREEN_ID+"A2";
    /* アクションID：CSVダウンロード処理 */
    public static final String downloadCsv_ACTION_ID = ACTION_ID+"1";
    
    /** DTO【自画面リクエスト用】*/
    private RequestNipoDto campNipoRefViewDto;
    /** DTO【自画面セッション用】*/
    private SessionNipoDto campNipoRefSesDto;
	/**
	 * @return campNipoRefSesDto を戻します。
	 */
	public SessionNipoDto getCampNipoRefSesDto() {
		return campNipoRefSesDto;
	}
	/**
	 * @param campNipoRefSesDto 設定する campNipoRefSesDto。
	 */
	public void setCampNipoRefSesDto(SessionNipoDto campNipoRefSesDto) {
		this.campNipoRefSesDto = campNipoRefSesDto;
	}
	/**
	 * @return campNipoRefViewDto を戻します。
	 */
	public RequestNipoDto getCampNipoRefViewDto() {
		return campNipoRefViewDto;
	}
	/**
	 * @param campNipoRefViewDto 設定する campNipoRefReqDto。
	 */
	public void setCampNipoRefViewDto(RequestNipoDto campNipoRefReqDto) {
		this.campNipoRefViewDto = campNipoRefReqDto;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl#downloadCsv()
	 */
	public void downloadCsv() throws IOException, ApplicationException {
    	//1.【自画面共通】表示検索データ設定処理
    	((RequestNipoDto) campNipoRefViewDto).setSelfSessionDto(getCampNipoRefSesDto());
        super.setCsvOutputDto(campNipoRefViewDto);
		// TODO 自動生成されたメソッド・スタブ
		super.downloadCsv();
	}
	
}
