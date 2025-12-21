package jp.co.isid.mos.bird.analysis.shubetusuiiref.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.shubetusuiiref.action.ShubetuSuiiRefAction;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.code.ShubetuSuiiRefConst;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefReqDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.SearchShubetuSuiiLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 種別売上推移表アクション
 * @author xnkusama
 *
 */
public class ShubetuSuiiRefActionImpl implements ShubetuSuiiRefAction {
    /**ACTION_ID*/
    public static final String initialize_ACTION_ID = "BDT003A01";
    public static final String execute_ACTION_ID = "BDT003A02";
    public static final String downloadDayCsv_ACTION_ID = "BDT003A03";
    public static final String downloadMonthCsv_ACTION_ID = "BDT003A04";
    public static final String callMiseForm_ACTION_ID = "BDT003A05";

    /**DTO*/
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private PullDownMenuDto pullDownMenuDto;
    private MiseSearchDto miseSearchDto;
    private ShubetuSuiiRefDto shubetuSuiiRefDto;
    private ShubetuSuiiRefReqDto shubetuSuiiRefReqDto;
    private ShubetuSuiiRefReqDto shubetuSuiiRefReqViewDto;
    
    /**LOGIC*/
    private ConditionInfoLogic shubetuSuiiRefConditionLogic;
    private SearchShubetuSuiiLogic shubetuSuiiRefSearchNipoLogic;
    private SearchShubetuSuiiLogic shubetuSuiiRefSearchGepoLogic;
    
    /**ACTION*/
    private CsvOutputAction shubetusuiirefCsvAction;
    private CsvOutputAction shubetusuiirefGepoCsvAction;
    
    /**
     * 実行
     */
    public String execute() {
        //１．複数ウィンドウ対応
        if (getShubetuSuiiRefReqDto().isNewWindowFlg()) {
            getShubetuSuiiRefReqDto().setWindowId(getShubetuSuiiRefDto().updateWindowid());
            getShubetuSuiiRefReqDto().setNewWindowFlg(false);
        }
        //２．検索ロジック実行
        getShubetuSuiiRefSearchNipoLogic().execute(getShubetuSuiiRefDto(), getShubetuSuiiRefReqDto());
        //３．検索条件保持
        saveDtoInfo();
        //４．検索結果保持
        saveSearchedData(true);
        //検索済フラグセット
        getShubetuSuiiRefDto().setSearchedFlg(getShubetuSuiiRefReqDto().getWindowId(), true);
        //再検索フラグセット
        getShubetuSuiiRefDto().setResearchFlg(getShubetuSuiiRefReqDto().getWindowId(), true);
        
        return null;
    }

    /**
     * 日次種別売上推移CSVダウンロード
     */
    public String downloadDayCsv() {
        //１．検索ロジック実行
        getShubetuSuiiRefSearchNipoLogic().execute(getShubetuSuiiRefDto(), getShubetuSuiiRefReqDto());
        //２．共通CSVアクション実行
        try {
            getShubetuSuiiRefReqDto().setUserTypeCd(getShubetuSuiiRefDto().getUserTypeCd());
            getShubetusuiirefCsvAction().downloadCsv();
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV出力処理", null, ex);
        }
        
        return null;
    }

    /**
     * 月次種別売上推移CSVダウンロード
     */
    public String downloadMonthCsv() {
        //０．リクエストDTOへアプリ日付をセット
        getShubetuSuiiRefReqDto().setAppDate(getShubetuSuiiRefDto().getBirdDateInfo().getAppDate());
        //１．検索ロジック実行
        getShubetuSuiiRefSearchGepoLogic().execute(getShubetuSuiiRefDto(), getShubetuSuiiRefReqDto());
        //２．共通CSVアクション実行
        try {
            getShubetuSuiiRefReqDto().setUserTypeCd(getShubetuSuiiRefDto().getUserTypeCd());
            getShubetusuiirefGepoCsvAction().downloadCsv();
        }
        catch (Exception ex) {
            throw new FtlSystemException("CSV出力処理", null, ex);
        }
        
        return null;
    }

    /**
     * 店検索遷移
     */
    public String callMiseForm() {
        //店検索DTOへ遷移元情報をセット
        getMiseSearchDto().setNavigationCase(ShubetuSuiiRefConst.VIEW_ID);
        getMiseSearchDto().setInitialFlag(true);
        getMiseSearchDto().setNeedReturnKind(true);
        getMiseSearchDto().setWindowId(getShubetuSuiiRefReqDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getShubetuSuiiRefReqDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);

        //現在選択されている検索条件を保持
        saveSearchedData(false);
        
        return ShubetuSuiiRefConst.VIEW_ID_MISESEARCH;
    }
    
    /**
     * 初期処理
     */
    public String initialize() {
        //メニューから遷移された場合
        if (getPullDownMenuDto().isClearFlg()) {
            init();
        }
        //店検索から遷移された場合
        if (MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            //ウィンドウIDセット
            getShubetuSuiiRefReqDto().setWindowId(getMiseSearchDto().getWindowId());
            //DTO情報をロード
            loadDtoInfo();
            //検索済み情報がある場合はDTOへセット
            if (getShubetuSuiiRefDto().isSearchedFlg(getShubetuSuiiRefReqDto().getWindowId())) {
                if (getShubetuSuiiRefDto().getListSearchData(getShubetuSuiiRefReqDto().getWindowId()) == null) {
                    //検索済み結果がセッションDTOにない場合は、再検索
                    getShubetuSuiiRefSearchNipoLogic().execute(getShubetuSuiiRefDto(), getShubetuSuiiRefReqDto());
                    saveSearchedData(true);
                }
                else {
                    getShubetuSuiiRefReqDto().setListNipoData(getShubetuSuiiRefDto().getListSearchData(getShubetuSuiiRefReqDto().getWindowId()));
                }
            }
            //店コードが選択されていた場合は、DTOへセット
            if (MiseSearchDto.RETURNKIND_SELECT == getMiseSearchDto().getReturnKind()) {
                getShubetuSuiiRefReqDto().setHyojiTaishoMise(getMiseSearchDto().getMiseCd());
            }
            //店検索DTOクリア
            getMiseSearchDto().clear();
        }
        //BIRD内画面から遷移された場合（店コード指定の場合のみ動作）
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            //初期処理
            init();
            String companyCd = getCommonCodeDto().getCompanyCd();
            String miseCd = getCommonCodeDto().getMiseCd();
            //会社コード、店コードが指定されている場合は、デフォルト条件で検索
            if (companyCd != null && miseCd != null) {
                getShubetuSuiiRefReqDto().setCompanyCd(companyCd);
                getShubetuSuiiRefReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                getShubetuSuiiRefReqDto().setHyojiTaishoMise(miseCd);
                SelectItem itemKikan = (SelectItem) getShubetuSuiiRefDto().getListKikan().get(0);
                getShubetuSuiiRefReqDto().setKikan(itemKikan.getValue().toString());
                SelectItem itemZenDataShu = (SelectItem) getShubetuSuiiRefDto().getListZenDataShu().get(0);
                getShubetuSuiiRefReqDto().setZenDataShu(itemZenDataShu.getValue().toString());
                //検索処理実行
                execute();
            }
        }
        //再検索フラグセット
        getShubetuSuiiRefReqDto().setReSearchFlg(getShubetuSuiiRefDto().isResearchFlg(getShubetuSuiiRefReqDto().getWindowId()));
        return null;
    }

    /**
     * 共通初期処理
     */
    private void init() {
        //DTO初期化処理
        initDto();
        //複数ウィンドウ制御
        getShubetuSuiiRefReqDto().setWindowId(getShubetuSuiiRefDto().createWindowId());
        //検索条件情報取得
        getShubetuSuiiRefConditionLogic()
            .execute(getShubetuSuiiRefDto(), getShubetuSuiiRefReqDto());
        //フラグクリア
        getPullDownMenuDto().setClearFlg(false);
    }
    
    /**
     * DTO初期化処理
     */
    private void initDto() {
        //ユーザー、日付情報をDTOへセット
        getShubetuSuiiRefDto().setBirdUserInfo(getBirdUserInfo());
        getShubetuSuiiRefDto().setBirdDateInfo(getBirdDateInfo());
        getShubetuSuiiRefDto().setUserId(getBirdUserInfo().getUserID());
        getShubetuSuiiRefDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
        //オーナーユーザーの場合、対象条件の初期値を「店舗」とする--2008/12/17 本部ユーザーも個店をデフォルトに変更
        //if (UserType.ONER.equals(getShubetuSuiiRefDto().getUserTypeCd())) {
            getShubetuSuiiRefReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
        //}
    }
    
    /**
     * 検索条件保持
     */
    private void saveDtoInfo() {
        //検索済み条件を保存用DTOへセット
        getShubetuSuiiRefReqViewDto().setCompanyCd(getShubetuSuiiRefReqDto().getCompanyCd());
        getShubetuSuiiRefReqViewDto().setTenpoShu(getShubetuSuiiRefReqDto().getTenpoShu());
        getShubetuSuiiRefReqViewDto().setTaishoJoken(getShubetuSuiiRefReqDto().getTaishoJoken());
        getShubetuSuiiRefReqViewDto().setTaishoKikan(getShubetuSuiiRefReqDto().getTaishoKikan());
        getShubetuSuiiRefReqViewDto().setHyojiTaishoSibu(getShubetuSuiiRefReqDto().getHyojiTaishoSibu());
        getShubetuSuiiRefReqViewDto().setHyojiTaishoMise(getShubetuSuiiRefReqDto().getHyojiTaishoMise());
        getShubetuSuiiRefReqViewDto().setKikan(getShubetuSuiiRefReqDto().getKikan());
        getShubetuSuiiRefReqViewDto().setZenDataShu(getShubetuSuiiRefReqDto().getZenDataShu());
        getShubetuSuiiRefReqViewDto().setWindowId(getShubetuSuiiRefReqDto().getWindowId());
        getShubetuSuiiRefReqViewDto().setTenpoShuName(getShubetuSuiiRefReqDto().getTenpoShuName());
        getShubetuSuiiRefReqViewDto().setZenDataShuName(getShubetuSuiiRefReqDto().getZenDataShuName());
        getShubetuSuiiRefReqViewDto().setTaishoTenpoCnt(getShubetuSuiiRefReqDto().getTaishoTenpoCnt());
        getShubetuSuiiRefReqViewDto().setHyojiTaishoDisp(getShubetuSuiiRefReqDto().getHyojiTaishoDisp());
    }
    
    /**
     * 検索条件ロード
     */
    private void loadDtoInfo() {
        this.shubetuSuiiRefReqDto = getShubetuSuiiRefDto().getSearchFormInfo(getShubetuSuiiRefReqDto().getWindowId());
        this.shubetuSuiiRefReqViewDto = getShubetuSuiiRefDto().getSearchedInfo(getShubetuSuiiRefReqDto().getWindowId());
    }
    
    /**
     * 検索結果・条件保持
     * @param bSaveSearchDataFlg true:検索結果も保存する
     */
    private void saveSearchedData(boolean bSaveSearchDataFlg) {
        if (bSaveSearchDataFlg) {
            //検索結果保持
            getShubetuSuiiRefDto().setListSearchData(getShubetuSuiiRefReqDto().getWindowId(), getShubetuSuiiRefReqDto().getListNipoData());
        }
        //検索条件保持
        getShubetuSuiiRefDto().setSearchFormInfo(getShubetuSuiiRefReqDto().getWindowId(), getShubetuSuiiRefReqDto());
        getShubetuSuiiRefDto().setSearchedInfo(getShubetuSuiiRefReqDto().getWindowId(), getShubetuSuiiRefReqViewDto());
    }
    
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public ShubetuSuiiRefDto getShubetuSuiiRefDto() {
        return shubetuSuiiRefDto;
    }

    public void setShubetuSuiiRefDto(ShubetuSuiiRefDto shubetuSuiiRefDto) {
        this.shubetuSuiiRefDto = shubetuSuiiRefDto;
    }

    public ShubetuSuiiRefReqDto getShubetuSuiiRefReqDto() {
        return shubetuSuiiRefReqDto;
    }

    public void setShubetuSuiiRefReqDto(ShubetuSuiiRefReqDto shubetuSuiiRefReqDto) {
        this.shubetuSuiiRefReqDto = shubetuSuiiRefReqDto;
    }

    public ConditionInfoLogic getShubetuSuiiRefConditionLogic() {
        return shubetuSuiiRefConditionLogic;
    }

    public void setShubetuSuiiRefConditionLogic(
            ConditionInfoLogic shubetuSuiiRefConditionLogic) {
        this.shubetuSuiiRefConditionLogic = shubetuSuiiRefConditionLogic;
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

    public SearchShubetuSuiiLogic getShubetuSuiiRefSearchNipoLogic() {
        return shubetuSuiiRefSearchNipoLogic;
    }

    public void setShubetuSuiiRefSearchNipoLogic(
            SearchShubetuSuiiLogic shubetuSuiiRefSearchNipoLogic) {
        this.shubetuSuiiRefSearchNipoLogic = shubetuSuiiRefSearchNipoLogic;
    }

    public ShubetuSuiiRefReqDto getShubetuSuiiRefReqViewDto() {
        return shubetuSuiiRefReqViewDto;
    }

    public void setShubetuSuiiRefReqViewDto(
            ShubetuSuiiRefReqDto shubetuSuiiRefReqViewDto) {
        this.shubetuSuiiRefReqViewDto = shubetuSuiiRefReqViewDto;
    }

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public CsvOutputAction getShubetusuiirefCsvAction() {
        return shubetusuiirefCsvAction;
    }

    public void setShubetusuiirefCsvAction(CsvOutputAction shubetusuiirefCsvAction) {
        this.shubetusuiirefCsvAction = shubetusuiirefCsvAction;
    }

    public SearchShubetuSuiiLogic getShubetuSuiiRefSearchGepoLogic() {
        return shubetuSuiiRefSearchGepoLogic;
    }

    public void setShubetuSuiiRefSearchGepoLogic(
            SearchShubetuSuiiLogic shubetuSuiiRefSearchGepoLogic) {
        this.shubetuSuiiRefSearchGepoLogic = shubetuSuiiRefSearchGepoLogic;
    }

    public CsvOutputAction getShubetusuiirefGepoCsvAction() {
        return shubetusuiirefGepoCsvAction;
    }

    public void setShubetusuiirefGepoCsvAction(
            CsvOutputAction shubetusuiirefGepoCsvAction) {
        this.shubetusuiirefGepoCsvAction = shubetusuiirefGepoCsvAction;
    }

    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

}
