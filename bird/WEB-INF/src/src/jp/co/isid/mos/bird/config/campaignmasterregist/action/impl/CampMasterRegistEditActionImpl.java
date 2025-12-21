package jp.co.isid.mos.bird.config.campaignmasterregist.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.campaignmasterregist.action.CampMasterRegistEditAction;
import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistConst;
import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistUtil;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterListDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.CheckCampaignInfoLogic;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.CheckMiseUploadLogic;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.CreateMenuListLogic;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidDataException;

/**
 * キャンペーンマスタ登録 編集画面Action
 *
 */
public class CampMasterRegistEditActionImpl implements
        CampMasterRegistEditAction {

    /* Action ID */
    public static final String initialize_ACTION_ID = "BCF006A11";
    public static final String doBack_ACTION_ID = "BCF006A12";
    public static final String doConfirm_ACTION_ID = "BCF006A13";
    public static final String doAddOyaMenu_ACTION_ID = "BCF006A14";
    public static final String doAddKoMenu_ACTION_ID = "BCF006A15";
    public static final String doCsvDownload_ACTION_ID = "BCF006A16";
    
    /**
     * 子メニュー追加アクション用パラメータ
     * 何番目の親メニュー対してアクション実行されたか
     */
    private int oyaMenuIndex;
    
    /** DTO */
    private CampaignMasterRegistDto campmasterregistDto;
    private CampaignMasterListDto campmasterregistReqDto;
    
    /** ACTION */
    private CsvOutput2ActionImpl campmasterregistCsvAction;
    
    /** LOGIC */
    private CheckCampaignInfoLogic campmasterregistCheckCampaignInfoLogic;
    private CheckMiseUploadLogic campmasterregistCheckMiseUploadLogic;
    private CreateMenuListLogic campmasterregistCreateMenuListLogic;
    
    /**
     * 戻る
     * @return
     */
    public String doBack() {
    	//確認ダイアログメッセージへ空を設定します。
    	getCampmasterregistDto().setConfirmMsg("");
        return CampaignMasterRegistConst.VIEW_ID_CONDITION;
    }

    /**
     * 確認
     * @return 
     */
    public String doConfirm() {
    	//確認ダイアログメッセージへ空を設定します。
    	getCampmasterregistDto().setConfirmMsg("");
        // パラメータ．対象店舗＝３（個店）の場合、【ロジック】個店情報チェックを実行
        if (CampaignMasterRegistConst.TARGET_KBN_MISE.equals(getCampmasterregistDto().getCondTaishoTenpo())) {
            if (!((CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_NOERROR == getCampmasterregistDto().getUploadStatus()
                    || CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_REGISTED == getCampmasterregistDto().getUploadStatus())
                  && getCampmasterregistReqDto().getKotenUploadFile() == null)) {
                // アップロード情報をクリア
                getCampmasterregistDto().setUploadErrorFlg(false);
                getCampmasterregistDto().setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_NORMAL);
                getCampmasterregistCheckMiseUploadLogic()
                    .execute(getCampmasterregistDto(), getCampmasterregistReqDto());
            }
        }
        else {
            // アップロード情報をクリア
            getCampmasterregistDto().setUploadErrorFlg(false);
            getCampmasterregistDto().setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_NORMAL);
            
        }
        
        try {
            // 入力内容チェック
            getCampmasterregistCheckCampaignInfoLogic().execute(getCampmasterregistDto());
        }
        finally {
            // アップロード情報をセット
            if (CampaignMasterRegistConst.TARGET_KBN_MISE.equals(getCampmasterregistDto().getCondTaishoTenpo())) {
                if (getCampmasterregistDto().isUploadErrorFlg()) {
                    getCampmasterregistDto().setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_ERROR);
                }
                else {
                    if (getCampmasterregistDto().getUploadStatus() != CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_REGISTED) {
                        getCampmasterregistDto().setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_NOERROR);
                    }
                }
            }
        }
        if (getCampmasterregistDto().isUploadErrorFlg()) {
            throw new InvalidDataException("ファイルにエラー");
        }
        //設定されたメニューコードに対しての集約先や換算値を取得し、
        //集約先メニューコードでソートされたリストを作成します。
        List listMenu = getCampmasterregistCreateMenuListLogic().execute(getCampmasterregistDto().getEntityMstMenuList());
        //DTO【自画面SessionDTO】.[[対象メニュー]]へ[[登録対象メニュー情報]]リストを設定します。
        getCampmasterregistDto().setEntityMstMenuList(CampaignMasterRegistUtil.makeEditMenuList(listMenu));
        if(CommonUtil.isNull(getCampmasterregistDto().getConfirmMsg())) {
        	//確認画面へ遷移します。
        	return CampaignMasterRegistConst.VIEW_ID_CONFIRM;
        }
        else {
        	//確認ダイアログ表示のため現行画面へ戻ります。
        	return null;
        }
    }
	/**
     * 確認ダイアログ用確認
     * @return 
     */
    public String doConfirmDialog() {
    	//確認ダイアログメッセージへ空を設定します。
    	getCampmasterregistDto().setConfirmMsg("");
    	//確認画面へ遷移します。
    	return CampaignMasterRegistConst.VIEW_ID_CONFIRM;
    }

    /**
     * CSVダウンロード
     *  登録済み、エラー店舗の両方で使用
     * @return
     */
    public String doCsvDownload() {
    	//確認ダイアログメッセージへ空を設定します。
    	getCampmasterregistDto().setConfirmMsg("");
        try {
            getCampmasterregistCsvAction().downloadCsv();
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }

    public String initialize() {
        return null;
    }

    public int getOyaMenuIndex() {
        return oyaMenuIndex;
    }

    public void setOyaMenuIndex(int oyaMenuIndex) {
        this.oyaMenuIndex = oyaMenuIndex;
    }

    public CampaignMasterRegistDto getCampmasterregistDto() {
        return campmasterregistDto;
    }

    public void setCampmasterregistDto(CampaignMasterRegistDto campmasterregistDto) {
        this.campmasterregistDto = campmasterregistDto;
    }

    public CheckCampaignInfoLogic getCampmasterregistCheckCampaignInfoLogic() {
        return campmasterregistCheckCampaignInfoLogic;
    }

    public void setCampmasterregistCheckCampaignInfoLogic(
            CheckCampaignInfoLogic campmasterregistCheckCampaignInfoLogicImpl) {
        this.campmasterregistCheckCampaignInfoLogic = campmasterregistCheckCampaignInfoLogicImpl;
    }

    public CheckMiseUploadLogic getCampmasterregistCheckMiseUploadLogic() {
        return campmasterregistCheckMiseUploadLogic;
    }

    public void setCampmasterregistCheckMiseUploadLogic(
            CheckMiseUploadLogic campmasterregistCheckMiseUploadLogicImpl) {
        this.campmasterregistCheckMiseUploadLogic = campmasterregistCheckMiseUploadLogicImpl;
    }

    public CampaignMasterListDto getCampmasterregistReqDto() {
        return campmasterregistReqDto;
    }

    public void setCampmasterregistReqDto(
            CampaignMasterListDto campmasterregistReqDto) {
        this.campmasterregistReqDto = campmasterregistReqDto;
    }

    public CsvOutput2ActionImpl getCampmasterregistCsvAction() {
        return campmasterregistCsvAction;
    }

    public void setCampmasterregistCsvAction(
            CsvOutput2ActionImpl campmasterregistCsvAction) {
        this.campmasterregistCsvAction = campmasterregistCsvAction;
    }

	/**
	 * @return campmasterregistCreateMenuListLogic を戻します。
	 */
	public CreateMenuListLogic getCampmasterregistCreateMenuListLogic() {
		return campmasterregistCreateMenuListLogic;
	}

	/**
	 * @param campmasterregistCreateMenuListLogic 設定する campmasterregistCreateMenuListLogic。
	 */
	public void setCampmasterregistCreateMenuListLogic(
			CreateMenuListLogic campmasterregistCreateMenuListLogic) {
		this.campmasterregistCreateMenuListLogic = campmasterregistCreateMenuListLogic;
	}

}
