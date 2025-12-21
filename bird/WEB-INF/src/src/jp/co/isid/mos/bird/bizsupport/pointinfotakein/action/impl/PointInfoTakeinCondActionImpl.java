/**
 *
 */
package jp.co.isid.mos.bird.bizsupport.pointinfotakein.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.common.entity.UISeidoMst;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetSeidoPointInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.action.PointInfoTakeinCondAction;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.common.PointInfoTakeinCommon;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.common.PointInfoTakeinConstants;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.dto.PointInfoTakeinDto;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.PointInfoTakeinUploadLogic;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.RegistGyosekiPointInfoLogic;
import jp.co.isid.mos.bird.bizsupport.pointinfotakein.logic.RegistPointInfoTakeinLogic;
import jp.co.isid.mos.bird.framework.action.CsvDownloadAction;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * ポイント情報取込 初期画面アクション
 * @author yushuncheng
 *
 */
public class PointInfoTakeinCondActionImpl implements PointInfoTakeinCondAction {

	/* DTO */
    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;

    /** ポイント情報取込Dto */
    private PointInfoTakeinDto pointInfoTakeinDto;
    /** ログイン情報 */
    private BirdUserInfo birdUserInfo;

	// Logic
    private GetSeidoPointInfoLogic getSeidoPointInfoLogic;
	private PointInfoTakeinUploadLogic pointInfoTakeinUploadLogic;
    private RegistPointInfoTakeinLogic registPointInfoTakeinLogic;
    private RegistGyosekiPointInfoLogic registGyosekiPointInfoLogic;

	/* ACTION */
    private CsvDownloadAction pointInfoTakeinErrorCsvAction;

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {

		 /* メニューから遷移した場合の処理 */
        if (getPullDownMenuDto().isClearFlg()) {
            //DTOクリア
            getPullDownMenuDto().setClearFlg(false);
            getPointInfoTakeinDto().setProcessMode(PointInfoTakeinConstants.PROCESS_MODE_HPRI);
            //BirdUserInfoセット
            getPointInfoTakeinDto().setBirdUserInfo(getBirdUserInfo());
    		// 表示用フラグの初期化
    		getPointInfoTakeinDto().clearInit();

            //----------------------
            // 株式報酬制度一覧取得
            //----------------------
            List<UISeidoMst> listSeido = getGetSeidoPointInfoLogic().execute();
            getPointInfoTakeinDto().setListSeido(listSeido);
            if(!getPointInfoTakeinDto().isEmptyListTarget()) {
            	getPointInfoTakeinDto().setConSeidoCd(listSeido.get(0).getSeidoCd());
            }
        }

		return null;
	}

	/**
     * 取込
     * @return 画面遷移情報
     */
	public String upload() {

        // ポイント情報アップロード
		// 表示用フラグの初期化
		getPointInfoTakeinDto().clearInit();

		// CSVファイルのチェック
		getPointInfoTakeinUploadLogic().execute(getPointInfoTakeinDto());

        if (getPointInfoTakeinDto().isUploadError()) {
        	// CSVにエラーファイル名の取得
    		if(getPointInfoTakeinDto().getUploadedFile() != null
    				&& getPointInfoTakeinDto().getUploadedFile().getName() != null) {
    			String name = PointInfoTakeinCommon.getCsvErrorName(
    					getPointInfoTakeinDto().getUploadedFile().getName());
            	getPointInfoTakeinDto().setCsvErrorName(name);
        	}
            //CSVにエラーがあった場合
        	//getPointInfoTakeinErrorCsvAction().download();
        }else {
        	// 取込データの論理チェック
        	registPointInfoTakeinLogic.execute(getPointInfoTakeinDto());
        	if (getPointInfoTakeinDto().isUploadError()) {
        		// CSVにエラーファイル名の取得
        		if(getPointInfoTakeinDto().getUploadedFile() != null
        				&& getPointInfoTakeinDto().getUploadedFile().getName() != null) {
        			String name = PointInfoTakeinCommon.getCsvErrorName(
        					getPointInfoTakeinDto().getUploadedFile().getName());
                	getPointInfoTakeinDto().setCsvErrorName(name);
            	}
                // 論理エラーがあった場合
            	//getPointInfoTakeinErrorCsvAction().download();
            }else {
                // アップロード完了
                getPointInfoTakeinDto().setUploadFinish(true);
            }
        }

		return null;
	}

    /**
     * CSVエラーファイルダウンロード
     * @return
     */
    public String errorDownload() {

    	if (getPointInfoTakeinDto().isUploadError()) {
    		// 論理エラーがあった場合
    		getPointInfoTakeinErrorCsvAction().download();
    		// 表示用フラグの初期化
    		getPointInfoTakeinDto().clearInit();
    	}
    	return null;
    }

	/**
     * 実行
     * @return 画面遷移情報
     */
	public String execute() {

		// 表示用フラグの初期化
		getPointInfoTakeinDto().clearInit();

        // 業績ポイント作成
        if(getRegistGyosekiPointInfoLogic().execute(getPointInfoTakeinDto())) {

            // 業績ポイント作成完了設定：完了
            getPointInfoTakeinDto().setKeisanFinish(true);
        }

		return null;
	}


	public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

	public PointInfoTakeinDto getPointInfoTakeinDto() {
		return pointInfoTakeinDto;
	}

	public void setPointInfoTakeinDto(PointInfoTakeinDto pointInfoTakeinDto) {
		this.pointInfoTakeinDto = pointInfoTakeinDto;
	}
    public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	public GetSeidoPointInfoLogic getGetSeidoPointInfoLogic() {
		return getSeidoPointInfoLogic;
	}

	public void setGetSeidoPointInfoLogic(GetSeidoPointInfoLogic getSeidoPointInfoLogic) {
		this.getSeidoPointInfoLogic = getSeidoPointInfoLogic;
	}

	public PointInfoTakeinUploadLogic getPointInfoTakeinUploadLogic() {
		return pointInfoTakeinUploadLogic;
	}

	public void setPointInfoTakeinUploadLogic(PointInfoTakeinUploadLogic pointInfoTakeinUploadLogic) {
		this.pointInfoTakeinUploadLogic = pointInfoTakeinUploadLogic;
	}

	public RegistPointInfoTakeinLogic getRegistPointInfoTakeinLogic() {
		return registPointInfoTakeinLogic;
	}

	public void setRegistPointInfoTakeinLogic(RegistPointInfoTakeinLogic registPointInfoTakeinLogic) {
		this.registPointInfoTakeinLogic = registPointInfoTakeinLogic;
	}

	public RegistGyosekiPointInfoLogic getRegistGyosekiPointInfoLogic() {
		return registGyosekiPointInfoLogic;
	}

	public void setRegistGyosekiPointInfoLogic(RegistGyosekiPointInfoLogic registGyosekiPointInfoLogic) {
		this.registGyosekiPointInfoLogic = registGyosekiPointInfoLogic;
	}

	public CsvDownloadAction getPointInfoTakeinErrorCsvAction() {
		return pointInfoTakeinErrorCsvAction;
	}

	public void setPointInfoTakeinErrorCsvAction(CsvDownloadAction pointInfoTakeinErrorCsvAction) {
		this.pointInfoTakeinErrorCsvAction = pointInfoTakeinErrorCsvAction;
	}

}
