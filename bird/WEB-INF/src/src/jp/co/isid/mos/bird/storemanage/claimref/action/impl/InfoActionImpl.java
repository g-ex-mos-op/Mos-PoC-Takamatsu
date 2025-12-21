/**
 * 2008/06/23
 */
package jp.co.isid.mos.bird.storemanage.claimref.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.storemanage.claimref.action.InfoAction;
import jp.co.isid.mos.bird.storemanage.claimref.dto.RequestDto;
import jp.co.isid.mos.bird.storemanage.claimref.dto.SessionDto;
import jp.co.isid.mos.bird.storemanage.claimref.entity.UIVoiceInfo;
import jp.co.isid.mos.bird.storemanage.claimref.logic.SearchLogic;
import jp.co.isid.mos.bird.storemanage.claimref.util.VoiceRefUtil;

/**
 * 個店管理：『お客様の声』
 * 詳細画面アクション
 * 
 * @author xkinu
 *
 */
public class InfoActionImpl implements InfoAction {
	private static final String ACTION_ID = VoiceRefUtil.SCREEN_ID+"A1";
    /* アクションID：初期処理(初期画面用) */
    public static final String initialize_ACTION_ID = ACTION_ID+"1";

    /** DTO【自画面セッション用】*/
    private SessionDto voiceRefSesDto;
    /** DTO【自画面照会情報保持】*/
    private RequestDto voiceRefViewDto;
    
    /** LOGIC【検索結果取得】*/
    private SearchLogic voiceRefSearchLogic;

	public String initialize() {
        //１.DTO【自画面Request】へプルダウンリストを設定します。
		if(getVoiceRefViewDto().getViewInfo() == null) {
			//セッションに検索データが存在しない場合、検索処理の実行を行います。
	        //１.ロジック【キャンペーン日報情報の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
			List listSearch = (List)voiceRefSearchLogic.execute(getVoiceRefSesDto(), getVoiceRefViewDto());
			//３．処理１の戻り値のList[[検索結果]]が０件の場合、下記の処理を行う。
			if(listSearch.size() < 1) {
				throw new NoResultException();
			}
		    //４．DTO【自画面View】．UIVoiceInfo[詳細検索結果]へ
			//処理１の戻り値のList[[検索結果]]のインデックス0番目を設定する。					
			getVoiceRefViewDto().setViewInfo((UIVoiceInfo)listSearch.get(0));
		}
		return null;
	}
	/**
	 * @return voiceRefSearchLogic を戻します。
	 */
	public SearchLogic getVoiceRefSearchLogic() {
		return voiceRefSearchLogic;
	}

	/**
	 * @param voiceRefSearchLogic 設定する voiceRefSearchLogic。
	 */
	public void setVoiceRefSearchLogic(SearchLogic voiceRefSearchLogic) {
		this.voiceRefSearchLogic = voiceRefSearchLogic;
	}

	/**
	 * @return voiceRefSesDto を戻します。
	 */
	public SessionDto getVoiceRefSesDto() {
		return voiceRefSesDto;
	}

	/**
	 * @param voiceRefSesDto 設定する voiceRefSesDto。
	 */
	public void setVoiceRefSesDto(SessionDto voiceRefSesDto) {
		this.voiceRefSesDto = voiceRefSesDto;
	}

	/**
	 * @return voiceRefViewDto を戻します。
	 */
	public RequestDto getVoiceRefViewDto() {
		return voiceRefViewDto;
	}

	/**
	 * @param voiceRefViewDto 設定する voiceRefViewDto。
	 */
	public void setVoiceRefViewDto(RequestDto voiceRefViewDto) {
		this.voiceRefViewDto = voiceRefViewDto;
	}

}
