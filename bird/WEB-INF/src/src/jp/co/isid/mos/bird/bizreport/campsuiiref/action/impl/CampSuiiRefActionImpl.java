package jp.co.isid.mos.bird.bizreport.campsuiiref.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campsuiiref.action.CampSuiiRefAction;
import jp.co.isid.mos.bird.bizreport.campsuiiref.common.CampSuiiRefConst;
import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.campsuiiref.util.CampSuiiUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * キャンペーン売上推移画面Action
 * @author xnkusama
 *
 */
public class CampSuiiRefActionImpl implements CampSuiiRefAction {
    /* Action ID */
    public static final String initialize_ACTION_ID = "BBR012A01";
    public static final String doChangeCamp_ACTION_ID = "BBR012A02";
    public static final String doMiseSearch_ACTION_ID = "BBR012A03";
    public static final String doExecute_ACTION_ID = "BBR012A04";
    public static final String doCsvDownload_ACTION_ID = "BBR012A05";
    
    /** LOGIC */
    private ConditionLogic campsuiirefConditionLogic;
    private SearchLogic campsuiirefSearchLogic;

    /** DTO */
    private RequestSuiiDto campsuiirefRequestSuiiDto;
    private RequestSuiiDto campsuiirefViewRequestSuiiDto;
    private SessionSuiiDto campsuiirefSessionSuiiDto;
    private PullDownMenuDto pullDownMenuDto;
    private MiseSearchDto miseSearchDto;
    
    /** ACTION */
    private CsvOutput2ActionImpl campsuiirefCsvAction;
    
    /** その他 */
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;

    /**
     * 初期処理
     */
    public String initialize() {
        
        // SessionDtoをRequestDtoにセット
        getCampsuiirefRequestSuiiDto().setSelfSessionDto(getCampsuiirefSessionSuiiDto());
        // RequestDtoに年度をセット(年度＝システム日付)
        getCampsuiirefRequestSuiiDto().setNendo(DateManager.getCurrentYear(getBirdDateInfo().getSysDate()));
        
        // １．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if (getPullDownMenuDto().isClearFlg()) {
            // DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
            // COMMON【自画面】にstaticメソッド．初期化処理メソッドを使用し、処理を行う。
            init(getCampsuiirefRequestSuiiDto(), getCampsuiirefSessionSuiiDto());
            getCampsuiirefSessionSuiiDto().removeListSearchData(getCampsuiirefRequestSuiiDto().getWindowId());
        }
        else {
            // ２．店舗選択画面から遷移された場合、下記の処理を行う。
            // 店検索画面からの戻り(店決定・戻るボタン)
            if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT)  {
                setCampsuiirefRequestSuiiDto(getCampsuiirefSessionSuiiDto().getMiseCallJoken(getMiseSearchDto().getWindowId()));
                getCampsuiirefRequestSuiiDto().setWindowId(getMiseSearchDto().getWindowId());
                //1.【自画面共通】表示検索データ設定処理
                CampSuiiUtil.setViewSuii(getCampsuiirefSearchLogic()
                        , getCampsuiirefSessionSuiiDto()
                        , getCampsuiirefRequestSuiiDto()
                        , getCampsuiirefViewRequestSuiiDto());
                if (getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT) {
                    getCampsuiirefRequestSuiiDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                    getCampsuiirefRequestSuiiDto().setHyojiTaisho(getMiseSearchDto().getMiseCd());
                }
                
                getMiseSearchDto().clear();
            }
            else {
                if((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
                    // BIRD内遷移情報をセット
                    doActionBirdLink();
                    // BIRD内遷移フラグをリセット
                    getCommonCodeDto().clear();
                    // 検索処理
                    doExecute();
                }
                CampSuiiUtil.setViewSuii(getCampsuiirefSearchLogic()
                        , getCampsuiirefSessionSuiiDto()
                        , getCampsuiirefRequestSuiiDto()
                        , getCampsuiirefViewRequestSuiiDto());
            }
        }
        
        
        return null;
    }
    
    /**
     * 店検索
     * @return
     */
    public String doSearchMise() {
        //セッションDTOへ現在の検索条件を保持
        getCampsuiirefSessionSuiiDto().setMiseCallJoken(getCampsuiirefRequestSuiiDto());
        
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(CampSuiiRefConst.VIEW_ID_HONBU);
        miseSearchDto.setInitialFlag(true);
        miseSearchDto.setNeedReturnKind(true);
        miseSearchDto.setWindowId(getCampsuiirefRequestSuiiDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getCampsuiirefRequestSuiiDto().getCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        
        return CampSuiiRefConst.VIEW_ID_MISESEARCH;
    }
    
    /**
     * 検索
     * @return
     */
    public String doExecute() {
        //「別ウィンドウに表示」の場合
        if (getCampsuiirefRequestSuiiDto().isNewWindowFlg()) {
            // Session DTOのウィンドウIDを更新
            getCampsuiirefRequestSuiiDto().setWindowId(getCampsuiirefSessionSuiiDto().updateWindowid());
            getCampsuiirefRequestSuiiDto().setNewWindowFlg(false);
        }
        //検索済みフラグをクリア
        getCampsuiirefSessionSuiiDto().setSearchedFlg(getCampsuiirefRequestSuiiDto().getWindowId(), false);
        // 検索条件をSessionDtoにセット
        //店コード5桁変換
        if (TaishoJoken.CODE_MISE.equals(getCampsuiirefRequestSuiiDto().getTaishoJoken())) {
            CodeFormatter codeFormatter = new CodeFormatter(5);
            codeFormatter.setFormatPattern("00000");
            getCampsuiirefRequestSuiiDto().setHyojiTaisho(codeFormatter.format(getCampsuiirefRequestSuiiDto().getHyojiTaisho(), true));
        }
        CampSuiiUtil.searchSuii(getCampsuiirefSearchLogic(), getCampsuiirefSessionSuiiDto(), getCampsuiirefRequestSuiiDto());
        //検索済みフラグをセット
        getCampsuiirefSessionSuiiDto().setSearchedFlg(getCampsuiirefRequestSuiiDto().getWindowId(), true);
        
        return null;
    }

    
    /**
     * COMMON【自画面】 staticメソッド．初期化処理のロジック
     * @param requestDto
     * @param sessionDto
     */
    private void init(RequestSuiiDto requestDto, SessionSuiiDto sessionDto) {
        //１．DTO【自画面Session】．BIRDユーザ情報へ[S2コンテナー]BIRDユーザー情報を設定する。
        getCampsuiirefSessionSuiiDto().setBirdUserInfo(getBirdUserInfo());
        //２．DTO【自画面Session】．BIRD日付情報へ[S2コンテナー]BIRD日付情報を設定する。
        //３．DTO【自画面Session】．アプリ日付へ[S2コンテナー]BIRD日付情報からアプリ日付を設定する。
        getCampsuiirefSessionSuiiDto().setBirdDateInfo(getBirdDateInfo());
        //４．DTO【自画面Session】へ新しくWindowIDを設定する。
        getCampsuiirefSessionSuiiDto().createWindowId();
        //５．ロジック【条件項目の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
        getCampsuiirefConditionLogic().execute(requestDto);
        //
        resetDefault(requestDto, sessionDto);
        //検索済みフラグ 全クリア
        getCampsuiirefSessionSuiiDto().clearSearchedFlg();
    }
    
    /**
     * DTO【自画面Request】 publicメソッド．resetDefaultのロジック
     * @param requestDto
     * @param sessionDto
     */
    private void resetDefault(RequestSuiiDto requestDto, SessionSuiiDto sessionDto) {
        requestDto.setInitialazeData(sessionDto);
    }

    /**
     * BIRD内画面からリンクされた場合の処理
     */
    private void doActionBirdLink() {
        // 初期化処理
        init(getCampsuiirefRequestSuiiDto(), getCampsuiirefSessionSuiiDto());
        // 店コードが指定されている場合は、対象条件：個店をデフォルトにする
        String miseCd = getCommonCodeDto().getMiseCd();
        if (!CommonUtil.isNull(miseCd)) {
            getCampsuiirefRequestSuiiDto().setTaishoJoken(TaishoJoken.CODE_MISE);
            getCampsuiirefRequestSuiiDto().setHyojiTaisho(miseCd);
        }
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

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    public ConditionLogic getCampsuiirefConditionLogic() {
        return campsuiirefConditionLogic;
    }

    public void setCampsuiirefConditionLogic(
            ConditionLogic campsuiirefConditionLogic) {
        this.campsuiirefConditionLogic = campsuiirefConditionLogic;
    }

    public RequestSuiiDto getCampsuiirefRequestSuiiDto() {
        return campsuiirefRequestSuiiDto;
    }

    public void setCampsuiirefRequestSuiiDto(
            RequestSuiiDto campsuiirefRequestSuiiDto) {
        this.campsuiirefRequestSuiiDto = campsuiirefRequestSuiiDto;
    }

    public SessionSuiiDto getCampsuiirefSessionSuiiDto() {
        return campsuiirefSessionSuiiDto;
    }

    public void setCampsuiirefSessionSuiiDto(
            SessionSuiiDto campsuiirefSessionSuiiDto) {
        this.campsuiirefSessionSuiiDto = campsuiirefSessionSuiiDto;
    }

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public SearchLogic getCampsuiirefSearchLogic() {
        return campsuiirefSearchLogic;
    }

    public void setCampsuiirefSearchLogic(SearchLogic campsuiirefSearchLogic) {
        this.campsuiirefSearchLogic = campsuiirefSearchLogic;
    }

    public CsvOutput2ActionImpl getCampsuiirefCsvAction() {
        return campsuiirefCsvAction;
    }

    public void setCampsuiirefCsvAction(CsvOutput2ActionImpl campsuiirefCsvAction) {
        this.campsuiirefCsvAction = campsuiirefCsvAction;
    }

    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

    public RequestSuiiDto getCampsuiirefViewRequestSuiiDto() {
        return campsuiirefViewRequestSuiiDto;
    }

    public void setCampsuiirefViewRequestSuiiDto(
            RequestSuiiDto campsuiirefViewRequestSuiiDto) {
        this.campsuiirefViewRequestSuiiDto = campsuiirefViewRequestSuiiDto;
    }
}
