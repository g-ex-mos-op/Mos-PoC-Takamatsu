package jp.co.isid.mos.bird.config.campaignmasterregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.config.campaignmasterregist.action.CampMasterRegistCondAction;
import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistConst;
import jp.co.isid.mos.bird.config.campaignmasterregist.common.CampaignMasterRegistUtil;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterListDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.dto.CampaignMasterRegistDto;
import jp.co.isid.mos.bird.config.campaignmasterregist.entity.MstCampaign;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.GetCampaignListLogic;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.GetMenuListLogic;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.GetMiseSibuInfoLogic;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl.GetCampaignListLogicImpl;
import jp.co.isid.mos.bird.config.campaignmasterregist.logic.impl.GetMiseSibuInfoLogicImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * キャンペーンマスタ登録 条件画面Action
 * @author xnkusama
 *
 * 更新日:2011/07/26 ASPAC オープン中の店舗が存在する支部情報を取得できるよう対応
 */
public class CampMasterRegistCondActionImpl implements
        CampMasterRegistCondAction {
    /* Action ID */
    public static final String initialize_ACTION_ID = "BCF006A01";
    public static final String doNew_ACTION_ID = "BCF006A02";
    public static final String doEdit_ACTION_ID = "BCF006A03";
    public static final String doViewKako_ACTION_ID = "BCF006A04";
    public static final String doViewEditable_ACTION_ID = "BCF006A05";
    /* DTO */
    private PullDownMenuDto pullDownMenuDto;
    private CampaignMasterListDto campmasterregistReqDto;
    private CampaignMasterRegistDto campmasterregistDto;
    
    /* LOGIC */
    // キャンペーン情報の取得
    private GetCampaignListLogic campmasterregistGetCampaignListLogic;
    // 対象店舗情報の取得
    private GetMiseSibuInfoLogic campmasterregistGetMiseSibuInfoLogic;
    // メニュー情報の取得
    private GetMenuListLogic campmasterregistGetMenuListLogic;
    
    /* 共通 */
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    
    /* HTMLパラメータ */
    private int listSelectedIndex;
    
    /**
     * 新規登録
     * @return
     * 
     * 更新日:2011/07/26 ASPAC オープン中の店舗が存在する支部情報を取得できるよう対応
     */
    public String doNew() {
        // 登録モードを設定
        getCampmasterregistDto().setRegistMode(CampaignMasterRegistConst.REGIST_MODE_INSERT);
        // キャンペーン対象期間マスタを作成しDTOにセット
        getCampmasterregistDto().setEntityMstCampaign(new MstCampaign());
        // 空の対象メニューリストを設定します。
        getCampmasterregistDto().setEntityMstMenuList(
        		CampaignMasterRegistUtil.makeEditMenuList(new ArrayList(0)));

        // 対象店舗情報の取得
        getCampmasterregistGetMiseSibuInfoLogic()
            .execute(getBirdDateInfo()
            		, null, GetMiseSibuInfoLogicImpl.SEARCH_MODE_ZENTEN
            		, getCampmasterregistDto());
        // 会社コードをセット ※2008/03/21現在　会社選択機能がないためオンコード
        getCampmasterregistDto().setCompanyCd("00");
        // 親メニュー追加可能フラグセット
        getCampmasterregistDto().setFlgOyaMenuAdd(true);
    	//確認ダイアログメッセージへ空を設定します。
    	getCampmasterregistDto().setConfirmMsg("");
        return CampaignMasterRegistConst.VIEW_ID_EDIT;
    }

    /**
     * 編集
     * @return 
     * 更新日:2011/07/26 ASPAC オープン中の店舗が存在する支部情報を取得できるよう対応
     */
    public String doEdit() {
        // 選択されたキャンペーン情報を取得
        MstCampaign mstCamp = (MstCampaign) getCampmasterregistDto().getListRegistCampaign().get(getListSelectedIndex());
        
        //キャンペーン情報取得
        List listCamp = getCampmasterregistGetCampaignListLogic()
                            .execute(null, 
                                     GetCampaignListLogicImpl.SEARCH_MODE_CAMP, 
                                     mstCamp.getCampId(), 
                                     mstCamp.getCompanyCd());

        //取得したキャンペーン対象期間マスタとキャンペーン情報をDTOへセット
        mstCamp = (MstCampaign) listCamp.get(0);
        getCampmasterregistDto().setEntityMstCampaign(mstCamp);
        getCampmasterregistDto().setCampId(mstCamp.getCampId());
        getCampmasterregistDto().setCompanyCd(mstCamp.getCompanyCd());
        getCampmasterregistDto().setCondTaishoTenpo(mstCamp.getTargetKbn());
        
        //メニュー情報の取得
        List listMenu = getCampmasterregistGetMenuListLogic().execute(mstCamp.getCampId(), getCampmasterregistDto().getCondMode() == CampaignMasterRegistConst.CAMP_LIST_MODE_KAKO);
        getCampmasterregistDto().setEntityMstMenuList(
        		CampaignMasterRegistUtil.makeEditMenuList(listMenu));

        //親メニュー追加可能フラグセット
        if (listMenu.size() < CampaignMasterRegistConst.OYA_MENU_MAX_SIZE) {
            getCampmasterregistDto().setFlgOyaMenuAdd(true);
        }
        else {
            getCampmasterregistDto().setFlgOyaMenuAdd(false);
        }
        
        
        // 対象店舗情報の取得
        getCampmasterregistGetMiseSibuInfoLogic()
            .execute(getBirdDateInfo()
            		, mstCamp.getCampId(), mstCamp.getTargetKbn()
            		, getCampmasterregistDto());
        
        // 登録モード、個店アップロード表示モードを設定
        if (getCampmasterregistDto().getCondMode() == CampaignMasterRegistConst.CAMP_LIST_MODE_EDITABLE) {
            getCampmasterregistDto().setRegistMode(CampaignMasterRegistConst.REGIST_MODE_UPDATE);
        }
        else if (getCampmasterregistDto().getCondMode() == CampaignMasterRegistConst.CAMP_LIST_MODE_KAKO) {
            getCampmasterregistDto().setRegistMode(CampaignMasterRegistConst.REGIST_MODE_READONLY);
        }
        
        if (CampaignMasterRegistConst.TARGET_KBN_MISE.equals(mstCamp.getTargetKbn())) {
            getCampmasterregistDto().setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_REGISTED);
        }
        else {
            getCampmasterregistDto().setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_NORMAL);
        }
        
        if (CampaignMasterRegistConst.REGIST_MODE_READONLY == getCampmasterregistDto().getRegistMode()) {
            return CampaignMasterRegistConst.VIEW_ID_CONFIRM;
        }
    	//確認ダイアログメッセージへ空を設定します。
    	getCampmasterregistDto().setConfirmMsg("");
        return CampaignMasterRegistConst.VIEW_ID_EDIT;
    }

    /**
     * 過去キャンペーン表示
     * @return
     */
    public String doViewKako() {
        //キャンペーン情報取得
        List listCamp = getCampmasterregistGetCampaignListLogic().execute(getBirdDateInfo().getSysDate(), GetCampaignListLogicImpl.SEARCH_MODE_KAKO, null, null);
        
        // キャンペーン一覧をDTOへセット
        getCampmasterregistDto().setListRegistCampaign(listCamp);
        
        // 一覧表示モードを「過去キャンペーン」にセット
        getCampmasterregistDto().setCondMode(CampaignMasterRegistConst.CAMP_LIST_MODE_KAKO);
        
        return null;
    }

    /**
     * 編集可能キャンペーン表示
     * @return
     */
    public String doViewEditable() {
        //キャンペーン情報取得
        List listCamp = getCampmasterregistGetCampaignListLogic().execute(getBirdDateInfo().getSysDate(), GetCampaignListLogicImpl.SEARCH_MODE_EDITABLE, null, null);
        
        // キャンペーン一覧をDTOへセット
        getCampmasterregistDto().setListRegistCampaign(listCamp);

        // 一覧表示モードを「編集可能キャンペーン」にセット
        getCampmasterregistDto().setCondMode(CampaignMasterRegistConst.CAMP_LIST_MODE_EDITABLE);
        
        return null;
    }

    /**
     * 初期処理
     */
    public String initialize() {
        // キャンペーン一覧の取得（メニューから遷移か登録処理後のみ）
        if (getPullDownMenuDto().isClearFlg() || getCampmasterregistDto().isFlgNeedCampList()) {
            //キャンペーン情報取得
            List listCamp = getCampmasterregistGetCampaignListLogic().execute(getBirdDateInfo().getSysDate(), GetCampaignListLogicImpl.SEARCH_MODE_EDITABLE, null, null);
            
            // キャンペーン一覧をDTOへセット
            getCampmasterregistDto().setListRegistCampaign(listCamp);
            
            getCampmasterregistDto().setFlgNeedCampList(false);

            // 一覧表示モードを「編集可能キャンペーン」にセット
            getCampmasterregistDto().setCondMode(CampaignMasterRegistConst.CAMP_LIST_MODE_EDITABLE);
        }
        // メニューから遷移された場合のみ処理
        if (getPullDownMenuDto().isClearFlg()) {
            // メニューから遷移フラグをクリア
            getPullDownMenuDto().setClearFlg(false);
            
            // システム日付セット
            getCampmasterregistDto().setSysDate(getBirdDateInfo().getSysDate());
            
            // ユーザー情報保持
            getCampmasterregistDto().setBirdUserInfo(getBirdUserInfo());
        }
        // 登録用データのクリア処理
        initData();
        
        return null;
    }
    
    private void initData() {
        getCampmasterregistDto().setRegistMode(CampaignMasterRegistConst.REGIST_MODE_INSERT);
        getCampmasterregistDto().setCondTaishoTenpo(CampaignMasterRegistConst.TARGET_KBN_ZENTEN);
        getCampmasterregistDto().setCampId(null);
        getCampmasterregistDto().setCompanyCd(null);
        getCampmasterregistDto().setUploadFileName(null);
        getCampmasterregistDto().setUploadFileData(null);
        getCampmasterregistDto().setUploadErrorFlg(false);
        getCampmasterregistDto().setUploadStatus(CampaignMasterRegistConst.KOTEN_UPLOAD_STATUS_NORMAL);
        getCampmasterregistDto().setErrElement(null);
        getCampmasterregistDto().setErrIndexOya(0);
        getCampmasterregistDto().setErrIndexKo(0);
    }

    public GetCampaignListLogic getCampmasterregistGetCampaignListLogic() {
        return campmasterregistGetCampaignListLogic;
    }

    public void setCampmasterregistGetCampaignListLogic(
            GetCampaignListLogic campmasterregistGetCampaignListLogic) {
        this.campmasterregistGetCampaignListLogic = campmasterregistGetCampaignListLogic;
    }

    public CampaignMasterRegistDto getCampmasterregistDto() {
        return campmasterregistDto;
    }

    public void setCampmasterregistDto(CampaignMasterRegistDto campmasterregistDto) {
        this.campmasterregistDto = campmasterregistDto;
    }

    public CampaignMasterListDto getCampmasterregistReqDto() {
        return campmasterregistReqDto;
    }

    public void setCampmasterregistReqDto(
            CampaignMasterListDto campmasterregistReqDto) {
        this.campmasterregistReqDto = campmasterregistReqDto;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public int getListSelectedIndex() {
        return listSelectedIndex;
    }

    public void setListSelectedIndex(int listSelectedIndex) {
        this.listSelectedIndex = listSelectedIndex;
    }

    public GetMiseSibuInfoLogic getCampmasterregistGetMiseSibuInfoLogic() {
        return campmasterregistGetMiseSibuInfoLogic;
    }

    public void setCampmasterregistGetMiseSibuInfoLogic(
            GetMiseSibuInfoLogic campmasterregistGetMiseSibuInfoLogic) {
        this.campmasterregistGetMiseSibuInfoLogic = campmasterregistGetMiseSibuInfoLogic;
    }

    public GetMenuListLogic getCampmasterregistGetMenuListLogic() {
        return campmasterregistGetMenuListLogic;
    }

    public void setCampmasterregistGetMenuListLogic(
            GetMenuListLogic campmasterregistGetMenuListLogic) {
        this.campmasterregistGetMenuListLogic = campmasterregistGetMenuListLogic;
    }

}
