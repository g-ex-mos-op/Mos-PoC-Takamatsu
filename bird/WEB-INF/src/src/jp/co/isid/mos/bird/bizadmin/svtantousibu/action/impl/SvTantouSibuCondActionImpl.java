package jp.co.isid.mos.bird.bizadmin.svtantousibu.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizadmin.svtantousibu.action.SvTantouSibuCondAction;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.common.SvTantouSibuCommon;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.dto.SvTantouSibuDto;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.RemoteSibuLogic;
import jp.co.isid.mos.bird.bizadmin.svtantousibu.logic.TantouUploadLogic;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchDto;
import jp.co.isid.mos.bird.framework.action.CsvDownloadAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

/**
 * リモート閲覧支部・SV担当店登録 初期画面アクション
 *
 * @author xnkusama
 */
public class SvTantouSibuCondActionImpl implements SvTantouSibuCondAction {
    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BBA004A01";

    /** 実行アクションID */
    public static final String execute_ACTION_ID = "BEN004A02";

    /** ユーザー検索アクションID */
    public static final String callUserSearch_ACTION_ID = "BEN004A03";

    /* LOGIC */
    //共通ロジック：支部一覧取得（保有店が存在する支部のみ）
    private GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic;
    //リモート閲覧支部の取得ロジック
    private RemoteSibuLogic svTantouSibuRemoteSibuLogic;
    //CSVアップロード処理ロジック
    private TantouUploadLogic svTantouSibuTantouUploadLogic;
    
    /* DTO */
    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;
    /** リモート閲覧支部・SV担当店登録DTO */
    private SvTantouSibuDto svTantouSibuDto;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /** ユーザー検索DTO */
    private UserSearchDto userSearchDto;

    /* ACTION */
    private CsvDownloadAction svTantouSibuErrorCsvAction;
    /**
     * 初期処理
     * @return 画面遷移情報
     */
    public String initialize() {
        String viewId = null;
        
        /* メニューから遷移した場合の処理 */
        if (getPullDownMenuDto().isClearFlg()) {
            //DTOクリア
            getSvTantouSibuDto().clearInitDto();
            getPullDownMenuDto().setClearFlg(false);
            //BirdUserInfoセット
            getSvTantouSibuDto().setBirdUserInfo(getBirdUserInfo());
            //複数ウィンドウセッションキー生成
            MakeSessionKey make = new MakeSessionKey();
            String sessionKey = make._makeSessionKey();
            
            getSvTantouSibuDto().setSessioniKey(sessionKey);
            getSvTantouSibuDto().setViewSessionKey(sessionKey);

        }
        /* ユーザー検索から戻ってきた場合の処理 */
        else if (getUserSearchDto().getReturnKind() != UserSearchDto.RETURNKIND_INIT) {
            int returnKind = getUserSearchDto().getReturnKind();
            //DTO【ユーザー検索】.遷移区分を初期値に設定する。
            getUserSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
            //WindowIDをセット
            getSvTantouSibuDto().setWindowId(getUserSearchDto().getWindowId());
            //【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
            MakeSessionKey make = new MakeSessionKey();
            if ( !make.isValidSessionKey( getSvTantouSibuDto().getViewSessionKey(), getSvTantouSibuDto().getSessionKey() ) ) {
                return make.operationErr_VIEW_ID;
            }
            //ユーザーを選択した場合は、編集画面へ遷移する
            if(returnKind == UserSearchDto.RETURNKIND_SELECT){        
                getSvTantouSibuDto().setUserId(getUserSearchDto().getUserId());
                executeRemoteSibu();
                viewId = SvTantouSibuCommon.VIEW_ID_REMOTESIBU_EDIT;
            }
            getUserSearchDto().clear();
        }
        /* 共通初期処理 */
        //支部プルダウン作成・設定
        getSvTantouSibuDto().setListSibu(makeSibuPulldown());
        //システム日付設定
        getSvTantouSibuDto().setSysDate(getBirdDateInfo().getSysDate());
        
        return viewId;
    }

    /**
     * ユーザー検索
     * @return
     */
    public String callUserSearch() {
        //複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey( getSvTantouSibuDto().getViewSessionKey(), getSvTantouSibuDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }

        //会社コードリスト作成（モスのみ）
        List listCompany = new ArrayList();
        listCompany.add(SvTantouSibuCommon.COMPANY_CD_MOS);
        
        getUserSearchDto().clear();
        getUserSearchDto().setNavigationCase(SvTantouSibuCommon.VIEW_ID_INIT);
        getUserSearchDto().setInitFlag(true);
        getUserSearchDto().setWindowId(getSvTantouSibuDto().getWindowId());
        getUserSearchDto().setNeedReturnKind(true);
        getUserSearchDto().setRCompanyCdList(listCompany);
        return SvTantouSibuCommon.VIEW_ID_USER_SEARCH;
    }

    /**
     * 実行
     * @return
     */
    public String execute() {
        //複数ウィンドウ対応
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey( getSvTantouSibuDto().getViewSessionKey(), getSvTantouSibuDto().getSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        
        // 遷移先ViewID
        String viewId = "";
        
        // 処理選択により処理を切り分ける
        if (SvTantouSibuCommon.PROCESS_MODE_REGIST_REMOTE_SIBU.equals(getSvTantouSibuDto().getProcessMode())) {
            executeRemoteSibu();
            viewId = SvTantouSibuCommon.VIEW_ID_REMOTESIBU_EDIT;
        }
        else if (SvTantouSibuCommon.PROCESS_MODE_REGIST_SV_CSV.equals(getSvTantouSibuDto().getProcessMode())) {
            //SV担当店ダウンロード
            //CSVは共通アクションで処理をする為、実行されない
        }
        else if (SvTantouSibuCommon.PROCESS_MODE_REGIST_UPLOAD.equals(getSvTantouSibuDto().getProcessMode())) {
            //SV担当店アップロード登録
            getSvTantouSibuTantouUploadLogic().execute(getSvTantouSibuDto());
            
            if (getSvTantouSibuDto().isUploadError()) {
                //CSVにエラーがあった場合
                getSvTantouSibuErrorCsvAction().download();
                viewId = null;
            }
            else {
                //CSVにエラーがない場合
                viewId = SvTantouSibuCommon.VIEW_ID_TANTOUSIBU_UPLOAD;
            }
        }
        
        // エラーフラグのクリア
        getSvTantouSibuDto().setProcessError(false);
        
        return viewId;
    }

    /** 
     * 支部プルダウン作成処理
     */
    private List makeSibuPulldown() {
        List listSibu = getGetSibuHoyuTenpoLogic()
                            .execute(getSvTantouSibuDto().getCompanyCd(), null, false);
        List listSibuSelectItem = new ArrayList();
        listSibuSelectItem.add(new SelectItem(SvTantouSibuCommon.COND_SIBU_ALL, "すべて"));
        for (Iterator ite = listSibu.iterator(); ite.hasNext();) {
            MstSibu mstSibu = (MstSibu) ite.next();
            SelectItem item = new SelectItem(mstSibu.getSibuCd(), mstSibu.getSibuName());
            listSibuSelectItem.add(item);
        }
        return listSibuSelectItem;
    }
    /**
     * リモート閲覧支部登録 処理
     *
     */
    private void executeRemoteSibu() {
        //リモート閲覧支部登録
        Map mapRemoteSibu = getSvTantouSibuRemoteSibuLogic().execute(getSvTantouSibuDto());
        //ユーザー情報設定
        MstUser mstUser = (MstUser) mapRemoteSibu.get(RemoteSibuLogic.RETURN_MAP_KEY_USER);
        getSvTantouSibuDto().setUserName(mstUser.getUser_name());
        //登録済み情報設定
        getSvTantouSibuDto().setListDbData((List) mapRemoteSibu.get(RemoteSibuLogic.RETURN_MAP_KEY_REMOTESIBU));
        //画面編集用リスト
        getSvTantouSibuDto().setListEditData((List) mapRemoteSibu.get(RemoteSibuLogic.RETURN_MAP_KEY_SIBULIST));
    }
    
    public SvTantouSibuDto getSvTantouSibuDto() {
        return svTantouSibuDto;
    }

    public void setSvTantouSibuDto(SvTantouSibuDto svTantouSibuDto) {
        this.svTantouSibuDto = svTantouSibuDto;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public GetSibuHoyuTenpoLogic getGetSibuHoyuTenpoLogic() {
        return getSibuHoyuTenpoLogic;
    }

    public void setGetSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic) {
        this.getSibuHoyuTenpoLogic = getSibuHoyuTenpoLogic;
    }

    public RemoteSibuLogic getSvTantouSibuRemoteSibuLogic() {
        return svTantouSibuRemoteSibuLogic;
    }

    public void setSvTantouSibuRemoteSibuLogic(
            RemoteSibuLogic svTantouSibuRemoteSibuLogic) {
        this.svTantouSibuRemoteSibuLogic = svTantouSibuRemoteSibuLogic;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public TantouUploadLogic getSvTantouSibuTantouUploadLogic() {
        return svTantouSibuTantouUploadLogic;
    }

    public void setSvTantouSibuTantouUploadLogic(
            TantouUploadLogic svTantouSibuTantouUploadLogic) {
        this.svTantouSibuTantouUploadLogic = svTantouSibuTantouUploadLogic;
    }

    public CsvDownloadAction getSvTantouSibuErrorCsvAction() {
        return svTantouSibuErrorCsvAction;
    }

    public void setSvTantouSibuErrorCsvAction(
            CsvDownloadAction svTantouSibuErrorCsvAction) {
        this.svTantouSibuErrorCsvAction = svTantouSibuErrorCsvAction;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    public UserSearchDto getUserSearchDto() {
        return userSearchDto;
    }

    public void setUserSearchDto(UserSearchDto userSearchDto) {
        this.userSearchDto = userSearchDto;
    }

}