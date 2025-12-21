/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.campsuiiref.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.bizreport.campsuiiref.util.CampSuiiUtil;
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
	private static final String ACTION_ID = CampSuiiUtil.SCREEN_ID + "A2";
    /* アクションID：CSVダウンロード処理 */
    public static final String downloadCsv_ACTION_ID = ACTION_ID + "1";
    
    /** DTO【自画面リクエスト用】*/
    private RequestSuiiDto campsuiirefViewRequestSuiiDto;
    /** DTO【自画面セッション用】*/
    private SessionSuiiDto campsuiirefSessionSuiiDto;
	/**
	 * @return campNipoRefSesDto を戻します。
	 */
	public SessionSuiiDto getCampsuiirefSessionSuiiDto() {
		return campsuiirefSessionSuiiDto;
	}
	/**
	 * @param campNipoRefSesDto 設定する campNipoRefSesDto。
	 */
	public void setCampsuiirefSessionSuiiDto(SessionSuiiDto campsuiirefSessionSuiiDto) {
		this.campsuiirefSessionSuiiDto = campsuiirefSessionSuiiDto;
	}
	/**
	 * @return campNipoRefViewDto を戻します。
	 */
	public RequestSuiiDto getCampsuiirefViewRequestSuiiDto() {
		return campsuiirefViewRequestSuiiDto;
	}
	/**
	 * @param campKakoRequestDto 設定する campNipoRefReqDto。
	 */
	public void setCampsuiirefViewRequestSuiiDto(RequestSuiiDto campsuiirefViewRequestSuiiDto) {
		this.campsuiirefViewRequestSuiiDto = campsuiirefViewRequestSuiiDto;
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
        ((RequestSuiiDto) getCampsuiirefViewRequestSuiiDto()).setSelfSessionDto(getCampsuiirefSessionSuiiDto());
        super.setCsvOutputDto(getCampsuiirefViewRequestSuiiDto());
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
