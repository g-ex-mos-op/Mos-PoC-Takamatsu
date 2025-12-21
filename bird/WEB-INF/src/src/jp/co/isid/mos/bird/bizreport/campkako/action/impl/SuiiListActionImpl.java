package jp.co.isid.mos.bird.bizreport.campkako.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campkako.action.SuiiListAction;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SuiiConditionLogic;
import jp.co.isid.mos.bird.bizreport.campkako.logic.SuiiSearchLogic;
import jp.co.isid.mos.bird.bizreport.campkako.util.CampKakoUtil;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionNipoDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * キャンペーン過去売上
 * @author xnkusama
 *
 */
public class SuiiListActionImpl implements SuiiListAction {
    /* Action ID */
    public static final String initialize_ACTION_ID = "BBR014A31";
    public static final String doChangeNendo_ACTION_ID = "BBR014A32";
    public static final String doChangeTab_ACTION_ID = "BBR014A33";
    public static final String doExecute_ACTION_ID = "BBR014A34";
    public static final String doSearchMise_ACTION_ID = "BBR014A35";
    
    /** LOGIC */
    private SuiiSearchLogic campKakoSuiiSearchLogic;
    private SuiiConditionLogic campKakoSuiiConditionLogic;

    /** DTO */
    private RequestSuiiDto campKakoSuiiRequestDto;
    private SessionSuiiDto campKakoSuiiSessionDto;
    private RequestSuiiDto campKakoViewSuiiRequestDto;
    private SessionNipoDto campKakoSessionDto;
    private PullDownMenuDto pullDownMenuDto;
    private MiseSearchDto miseSearchDto;
    
    /** その他 */
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;

    /**
     * 初期処理
     */
    public String initialize() {
        
        
        // １．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if (getPullDownMenuDto().isClearFlg()) {
            // DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
            // COMMON【自画面】にstaticメソッド．初期化処理メソッドを使用し、処理を行う。
            init(getCampKakoSuiiRequestDto(), getCampKakoSuiiSessionDto());
            getCampKakoSuiiSessionDto().removeListSearchData(getCampKakoSuiiRequestDto().getWindowId());
        }
        // ２．店舗選択画面から遷移された場合、下記の処理を行う。
        // 店検索画面からの戻り(店決定・戻るボタン)
        else { 
            if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT)  {
                setCampKakoSuiiRequestDto(getCampKakoSuiiSessionDto().getMiseCallJoken(getMiseSearchDto().getWindowId()));
                //ウィンドウIDセット
                getCampKakoSuiiRequestDto().setWindowId(getMiseSearchDto().getWindowId());
                //1.【自画面共通】表示検索データ設定処理
                CampKakoUtil.setViewSuii(getCampKakoSuiiSearchLogic()
                        , getCampKakoSuiiSessionDto()
                        , getCampKakoSuiiRequestDto()
                        , getCampKakoViewSuiiRequestDto());
                if (getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT) {
                    getCampKakoSuiiRequestDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                    getCampKakoSuiiRequestDto().setHyojiTaisho(getMiseSearchDto().getMiseCd());
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
    
                CampKakoUtil.setViewSuii(getCampKakoSuiiSearchLogic()
                        , getCampKakoSuiiSessionDto()
                        , getCampKakoSuiiRequestDto()
                        , getCampKakoViewSuiiRequestDto());
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
        getCampKakoSuiiSessionDto().setMiseCallJoken(getCampKakoSuiiRequestDto());
        //店検索DTOへ情報をセット
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(CampKakoUtil.VIEW_ID_SUII);
        miseSearchDto.setInitialFlag(true);
        miseSearchDto.setNeedReturnKind(true);
        miseSearchDto.setWindowId(getCampKakoSuiiRequestDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getCampKakoSuiiRequestDto().getCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        
        return CampKakoUtil.VIEW_ID_MISESEARCH;
    }
    
    /**
     * 検索
     * @return
     */
    public String doExecute() {
        //「別ウィンドウに表示」の場合
        if (getCampKakoSuiiRequestDto().isNewWindowFlg()) {
            // Session DTOのウィンドウIDを更新
            getCampKakoSuiiRequestDto().setWindowId(getCampKakoSuiiSessionDto().updateWindowid());
            getCampKakoSuiiRequestDto().setNewWindowFlg(false);
        }
        //検索済みフラグをクリア
        getCampKakoSuiiSessionDto().setSearchedFlg(getCampKakoSuiiRequestDto().getWindowId(), false);
        //店コード5桁変換
        if (TaishoJoken.CODE_MISE.equals(getCampKakoSuiiRequestDto().getTaishoJoken())) {
            CodeFormatter codeFormatter = new CodeFormatter(5);
            codeFormatter.setFormatPattern("00000");
            getCampKakoSuiiRequestDto().setHyojiTaisho(codeFormatter.format(getCampKakoSuiiRequestDto().getHyojiTaisho(), true));
        }

        CampKakoUtil.searchSuii(getCampKakoSuiiSearchLogic(), getCampKakoSuiiSessionDto(), getCampKakoSuiiRequestDto());
        //検索済みフラグをセット
        getCampKakoSuiiSessionDto().setSearchedFlg(getCampKakoSuiiRequestDto().getWindowId(), true);
        return null;
    }

    /**
     * 年度変更処理
     */
    public String changeNendo() {
        //１．キャンペーンリスト
        List listCamp = getCampKakoSuiiSessionDto().getListCamp(getCampKakoSuiiRequestDto().getCompanyCd(), getCampKakoSuiiRequestDto().getNendo());
        getCampKakoSuiiRequestDto().setListsCamp(listCamp);
        
        //メニュー、対象条件のクリア
        getCampKakoSuiiRequestDto().setMenuTotaledKbn("");
        getCampKakoSuiiRequestDto().setMenuCd("");
//---2008/08/05 対象条件のデフォルトを「全社」に変更        
        getCampKakoSuiiRequestDto().setTaishoJoken(TaishoJoken.CODE_ALL);
        getCampKakoSuiiRequestDto().setHyojiTaisho("");
        
        return null;
    }

    /**
     * タブ変更処理
     *
     */
    public String changeTab() {
        getPullDownMenuDto().setClearFlg(true);
        if (UserType.HONBU.equals(getCampKakoSuiiSessionDto().getUserTypeCd())) {
            return CampKakoUtil.VIEW_ID_SIBU;
        }
        else {
            return CampKakoUtil.VIEW_ID_MISE;
        }
    }
    
    /**
     * COMMON【自画面】 staticメソッド．初期化処理のロジック
     * @param requestDto
     * @param sessionDto
     */
    private void init(RequestSuiiDto requestDto, SessionSuiiDto sessionDto) {
        //１．DTO【自画面Session】．BIRDユーザ情報へ[S2コンテナー]BIRDユーザー情報を設定する。
        getCampKakoSuiiSessionDto().setBirdUserInfo(getBirdUserInfo());
        //２．DTO【自画面Session】．BIRD日付情報へ[S2コンテナー]BIRD日付情報を設定する。
        //３．DTO【自画面Session】．アプリ日付へ[S2コンテナー]BIRD日付情報からアプリ日付を設定する。
        getCampKakoSuiiSessionDto().setBirdDateInfo(getBirdDateInfo());
        //４．DTO【自画面Session】へ新しくWindowIDを設定する。
        requestDto.setWindowId(getCampKakoSuiiSessionDto().updateWindowid());
        //
        getCampKakoSuiiRequestDto().setSelfSessionDto(getCampKakoSuiiSessionDto());
        //５．ロジック【条件項目の取得】を実行する。戻り値のMap[[条件項目]]を取得します。
        getCampKakoSuiiConditionLogic().execute(requestDto);
        //
        resetDefault(requestDto, sessionDto);
        //
        getCampKakoSuiiSessionDto().clearSearchedFlg();
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
        init(getCampKakoSuiiRequestDto(), getCampKakoSuiiSessionDto());
        // 店コードが指定されている場合は、対象条件：個店をデフォルトにする
        String miseCd = getCommonCodeDto().getMiseCd();
        if (!CommonUtil.isNull(miseCd)) {
            getCampKakoSuiiRequestDto().setTaishoJoken(TaishoJoken.CODE_MISE);
            getCampKakoSuiiRequestDto().setHyojiTaisho(miseCd);
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

    public RequestSuiiDto getCampKakoSuiiRequestDto() {
        return campKakoSuiiRequestDto;
    }

    public void setCampKakoSuiiRequestDto(
            RequestSuiiDto campsuiirefRequestSuiiDto) {
        this.campKakoSuiiRequestDto = campsuiirefRequestSuiiDto;
    }

    public SessionSuiiDto getCampKakoSuiiSessionDto() {
        return campKakoSuiiSessionDto;
    }

    public void setCampKakoSuiiSessionDto(
            SessionSuiiDto campsuiirefSessionSuiiDto) {
        this.campKakoSuiiSessionDto = campsuiirefSessionSuiiDto;
    }

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public SuiiSearchLogic getCampKakoSuiiSearchLogic() {
        return campKakoSuiiSearchLogic;
    }

    public void setCampKakoSuiiSearchLogic(SuiiSearchLogic campsuiirefSearchLogic) {
        this.campKakoSuiiSearchLogic = campsuiirefSearchLogic;
    }

    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

    public SuiiConditionLogic getCampKakoSuiiConditionLogic() {
        return campKakoSuiiConditionLogic;
    }

    public void setCampKakoSuiiConditionLogic(
            SuiiConditionLogic campKakoConditionSuiiLogic) {
        this.campKakoSuiiConditionLogic = campKakoConditionSuiiLogic;
    }

    public RequestSuiiDto getCampKakoViewSuiiRequestDto() {
        return campKakoViewSuiiRequestDto;
    }

    public void setCampKakoViewSuiiRequestDto(
            RequestSuiiDto campKakoViewSuiiRequestDto) {
        this.campKakoViewSuiiRequestDto = campKakoViewSuiiRequestDto;
    }

    public SessionNipoDto getCampKakoSessionDto() {
        return campKakoSessionDto;
    }

    public void setCampKakoSessionDto(SessionNipoDto campKakoSessionDto) {
        this.campKakoSessionDto = campKakoSessionDto;
    }
}
