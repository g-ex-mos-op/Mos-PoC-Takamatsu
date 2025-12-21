/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campkako.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;


/**
 * CSVダウンロードアクション
 * 
 * @author xnkusama
 *
 */
public class CsvSuiiActionImpl extends CsvOutput2ActionImpl {
    /* アクションID：CSVダウンロード処理 */
    public static final String downloadCsv_ACTION_ID = "BBR14A36";
    
    /** DTO【自画面リクエスト用】*/
    private RequestSuiiDto campKakoViewSuiiRequestDto;
    /** DTO【自画面セッション用】*/
    private SessionSuiiDto campKakoSuiiSessionDto;
	/**
	 * @return campNipoRefSesDto を戻します。
	 */
	public SessionSuiiDto getCampKakoSuiiSessionDto() {
		return campKakoSuiiSessionDto;
	}
	/**
	 * @param campNipoRefSesDto 設定する campNipoRefSesDto。
	 */
	public void setCampKakoSuiiSessionDto(SessionSuiiDto campKakoSuiiSessionDto) {
		this.campKakoSuiiSessionDto = campKakoSuiiSessionDto;
	}
	/**
	 * @return campNipoRefViewDto を戻します。
	 */
	public RequestSuiiDto getCampKakoViewSuiiRequestDto() {
		return campKakoViewSuiiRequestDto;
	}
	/**
	 * @param campKakoRequestDto 設定する campNipoRefReqDto。
	 */
	public void setCampKakoViewSuiiRequestDto(RequestSuiiDto campKakoSuiiRequestDto) {
		this.campKakoViewSuiiRequestDto = campKakoSuiiRequestDto;
	}
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl#downloadCsv()
	 */
	public void downloadCsv() throws IOException, ApplicationException {

//        // 現在、検索結果として表示されている検索条件をリクエストDTOにセット
//        getCampKakoViewSuiiRequestDto().setPullDownData(getCampKakoSuiiSessionDto());
//        setSearchedCond();
//        // 検索結果をリクエストDTOへセット
//        getCampKakoViewSuiiRequestDto().setListSearchData(getCampKakoSuiiSessionDto().getListSearchData(getCampKakoViewSuiiRequestDto().getWindowId()));
//        // CSVアクション実行
//        super.downloadCsv();
        //1.【自画面共通】表示検索データ設定処理
        ((RequestSuiiDto) getCampKakoViewSuiiRequestDto()).setSelfSessionDto(getCampKakoSuiiSessionDto());
        super.setCsvOutputDto(getCampKakoViewSuiiRequestDto());
        // ダウンロード処理
        super.downloadCsv();
	}

//    private void setSearchedCond() {
//        int windowId = getCampKakoViewSuiiRequestDto().getWindowId();
//        getCampKakoViewSuiiRequestDto().setCompanyCd(getCampKakoSuiiSessionDto().getCompanyCd(windowId));
//        getCampKakoViewSuiiRequestDto().setNendo(getCampKakoSuiiSessionDto().getNendo(windowId));
//        getCampKakoViewSuiiRequestDto().setCampId(getCampKakoSuiiSessionDto().getCampId(windowId));
//        getCampKakoViewSuiiRequestDto().setMenuTotaledKbn(getCampKakoSuiiSessionDto().getMenuTotaledKbn(windowId));
//        getCampKakoViewSuiiRequestDto().setMenuCd(getCampKakoSuiiSessionDto().getMenuCd(windowId));
//        getCampKakoViewSuiiRequestDto().setTenpoShubetu(getCampKakoSuiiSessionDto().getTenpoShubetu(windowId));
//        getCampKakoViewSuiiRequestDto().setTaishoJoken(getCampKakoSuiiSessionDto().getTaishoJoken(windowId));
//        if (TaishoJoken.CODE_MISE.equals(getCampKakoSuiiSessionDto().getTaishoJoken(windowId))) {
//            getCampKakoViewSuiiRequestDto().setHyojiTaisho(getCampKakoSuiiSessionDto().getHyojiTaisho(windowId));
//        }
//        //getCampsuiirefRequestSuiiDto().setHyojiTaisho(getCampsuiirefSessionSuiiDto().getHyojiTaisho(windowId));
//        getCampKakoViewSuiiRequestDto().setBlockCd(getCampKakoSuiiSessionDto().getBlockCd(windowId));
//        getCampKakoViewSuiiRequestDto().setZennenDataShubetu(getCampKakoSuiiSessionDto().getZennenDataShubetu(windowId));
//        
//    }
    
}
