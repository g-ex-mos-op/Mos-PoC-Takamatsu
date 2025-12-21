package jp.co.isid.mos.bird.analysis.kakouriage.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.kakouriage.action.KakoUriageAction;
import jp.co.isid.mos.bird.analysis.kakouriage.code.KakoUriageConst;
import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageDto;
import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageReqDto;
import jp.co.isid.mos.bird.analysis.kakouriage.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.analysis.kakouriage.logic.SearchKakoUriageLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
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
public class KakoUriageActionImpl implements KakoUriageAction {
    /**ACTION_ID*/
    public static final String initialize_ACTION_ID = "BDT005A01";
    public static final String callMiseForm_ACTION_ID = "BDT005A02";
    public static final String execute_ACTION_ID = "BDT005A03";
    public static final String downloadCsv_ACTION_ID = "BDT005A04";

    /**DTO*/
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private PullDownMenuDto pullDownMenuDto;
    private MiseSearchDto miseSearchDto;
    private KakoUriageDto kakouriageDto;
    private KakoUriageReqDto kakouriageReqDto;
    private KakoUriageReqDto kakouriageViewDto;
    
    /**LOGIC*/
    private ConditionInfoLogic kakouriageConditionLogic;
    private SearchKakoUriageLogic kakouriageSearchLogic;
    
    /**ACTION*/
    private CsvOutputAction kakouriageCsvAction;
    
    /**
     * 実行
     */
    public String execute() {
        //１．複数ウィンドウ対応
        if (getKakouriageReqDto().isNewWindowFlg()) {
            getKakouriageReqDto().setWindowId(getKakouriageDto().updateWindowid());
            getKakouriageReqDto().setNewWindowFlg(false);
        }
        //２．検索ロジック実行
        getKakouriageSearchLogic().execute(getKakouriageDto(), getKakouriageReqDto());
        //３．検索条件保持
        saveDtoInfo(getKakouriageReqDto());
        //４．検索結果保持
        saveSearchedData(true);
        //検索済フラグセット
        getKakouriageDto().setSearchedFlg(getKakouriageReqDto().getWindowId(), true);
        //再検索フラグセット
        getKakouriageDto().setResearchFlg(getKakouriageReqDto().getWindowId(), true);
        
        return null;
    }

    /**
     * CSVダウンロード
     */
    public String downloadCsv() {
        //現在表示しているデータがセッションDTOに存在しない場合
        if (getKakouriageDto().getListSearchData(getKakouriageReqDto().getWindowId()) == null) {
            //１．検索ロジック実行
            getKakouriageSearchLogic().execute(getKakouriageDto(), getKakouriageViewDto());
            saveDtoInfo(getKakouriageViewDto());
        }
        else {
            getKakouriageViewDto().setListData(getKakouriageDto().getListSearchData(getKakouriageReqDto().getWindowId()));
            getKakouriageViewDto().setListHeader(getKakouriageDto().getListSearchDataHeader(getKakouriageReqDto().getWindowId()));
        }
        //２．共通CSVアクション実行
        try {
            getKakouriageViewDto().setUserTypeCd(getKakouriageDto().getUserTypeCd());
            getKakouriageCsvAction().downloadCsv();
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
        getMiseSearchDto().setNavigationCase(KakoUriageConst.VIEW_ID);
        getMiseSearchDto().setInitialFlag(true);
        getMiseSearchDto().setNeedReturnKind(true);
        getMiseSearchDto().setWindowId(getKakouriageReqDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getKakouriageReqDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);

        //現在選択されている検索条件を保持
        saveSearchedData(false);
        
        return KakoUriageConst.VIEW_ID_MISESEARCH;
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
            getKakouriageReqDto().setWindowId(getMiseSearchDto().getWindowId());
            //DTO情報をロード
            loadDtoInfo();
            //検索済み情報がある場合はDTOへセット
            if (getKakouriageDto().isSearchedFlg(getKakouriageReqDto().getWindowId())) {
                if (getKakouriageDto().getListSearchData(getKakouriageReqDto().getWindowId()) == null) {
                    //検索済み結果がセッションDTOにない場合は、再検索
                    getKakouriageSearchLogic().execute(getKakouriageDto(), getKakouriageReqDto());
                    saveSearchedData(true);
                }
                else {
                    getKakouriageReqDto().setListData(getKakouriageDto().getListSearchData(getKakouriageReqDto().getWindowId()));
                    getKakouriageReqDto().setListHeader(getKakouriageDto().getListSearchDataHeader(getKakouriageReqDto().getWindowId()));
                }
            }
            //店コードが選択されていた場合は、DTOへセット
            if (MiseSearchDto.RETURNKIND_SELECT == getMiseSearchDto().getReturnKind()) {
                getKakouriageReqDto().setHyojiTaisho(getMiseSearchDto().getMiseCd());
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
                getKakouriageReqDto().setCompanyCd(companyCd);
                getKakouriageReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                getKakouriageReqDto().setHyojiTaisho(miseCd);
                SelectItem itemKikan = (SelectItem) getKakouriageDto().getListTaishoKikan().get(0);
                getKakouriageReqDto().setTaishoKikan(itemKikan.getValue().toString());
                //検索処理実行
                execute();
            }
        }
        //再検索フラグセット
        getKakouriageReqDto().setReSearchFlg(getKakouriageDto().isResearchFlg(getKakouriageReqDto().getWindowId()));
        return null;
    }

    /**
     * 共通初期処理
     */
    private void init() {
        //DTO初期化処理
        initDto();
        //複数ウィンドウ制御
        getKakouriageReqDto().setWindowId(getKakouriageDto().createWindowId());
        //検索条件情報取得
        getKakouriageConditionLogic()
            .execute(getKakouriageDto(), getKakouriageReqDto());
        //フラグクリア
        getPullDownMenuDto().setClearFlg(false);
    }
    
    /**
     * DTO初期化処理
     */
    private void initDto() {
        //ユーザー、日付情報をDTOへセット
        getKakouriageDto().setBirdUserInfo(getBirdUserInfo());
        getKakouriageDto().setBirdDateInfo(getBirdDateInfo());
        getKakouriageDto().setUserId(getBirdUserInfo().getUserID());
        getKakouriageDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
        //オーナーユーザーの場合、対象条件の初期値を「店舗」とする
        if (UserType.HONBU.equals(getKakouriageDto().getUserTypeCd())) {
            getKakouriageReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
        }
    }
    
    /**
     * 検索条件保持
     */
    private void saveDtoInfo(KakoUriageReqDto dto) {
        //検索済み条件を保存用DTOへセット
        getKakouriageViewDto().setCompanyCd(dto.getCompanyCd());
        getKakouriageViewDto().setTaishoJoken(dto.getTaishoJoken());
        getKakouriageViewDto().setHyojiTaisho(dto.getHyojiTaisho());
        getKakouriageViewDto().setTaishoKikan(dto.getTaishoKikan());
        getKakouriageViewDto().setWindowId(dto.getWindowId());
        getKakouriageViewDto().setTaishoKikanDisp(dto.getTaishoKikanDisp());
        getKakouriageViewDto().setTaishoTenpoCnt(dto.getTaishoTenpoCnt());
        getKakouriageViewDto().setHyojiTaishoDisp(dto.getHyojiTaishoDisp());
    }
    
    /**
     * 検索条件ロード
     */
    private void loadDtoInfo() {
        this.kakouriageReqDto = getKakouriageDto().getSearchFormInfo(getKakouriageReqDto().getWindowId());
        this.kakouriageViewDto = getKakouriageDto().getSearchedInfo(getKakouriageReqDto().getWindowId());
    }
    
    /**
     * 検索結果・条件保持
     * @param bSaveSearchDataFlg true:検索結果も保存する
     */
    private void saveSearchedData(boolean bSaveSearchDataFlg) {
        if (bSaveSearchDataFlg) {
            //検索結果保持
            getKakouriageDto().setListSearchData(getKakouriageReqDto().getWindowId(), getKakouriageReqDto().getListData());
            getKakouriageDto().setListSearchDataHeader(getKakouriageReqDto().getWindowId(), getKakouriageReqDto().getListHeader());
        }
        //検索条件保持
        getKakouriageDto().setSearchFormInfo(getKakouriageReqDto().getWindowId(), getKakouriageReqDto());
        getKakouriageDto().setSearchedInfo(getKakouriageReqDto().getWindowId(), getKakouriageViewDto());
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

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

    public KakoUriageDto getKakouriageDto() {
        return kakouriageDto;
    }

    public void setKakouriageDto(KakoUriageDto kakouriageDto) {
        this.kakouriageDto = kakouriageDto;
    }

    public KakoUriageReqDto getKakouriageReqDto() {
        return kakouriageReqDto;
    }

    public void setKakouriageReqDto(KakoUriageReqDto kakouriageReqDto) {
        this.kakouriageReqDto = kakouriageReqDto;
    }

    public KakoUriageReqDto getKakouriageViewDto() {
        return kakouriageViewDto;
    }

    public void setKakouriageViewDto(KakoUriageReqDto kakouriageViewDto) {
        this.kakouriageViewDto = kakouriageViewDto;
    }

    public CsvOutputAction getKakouriageCsvAction() {
        return kakouriageCsvAction;
    }

    public void setKakouriageCsvAction(CsvOutputAction kakouriageCsvAction) {
        this.kakouriageCsvAction = kakouriageCsvAction;
    }

    public ConditionInfoLogic getKakouriageConditionLogic() {
        return kakouriageConditionLogic;
    }

    public void setKakouriageConditionLogic(
            ConditionInfoLogic kakouriageConditionLogic) {
        this.kakouriageConditionLogic = kakouriageConditionLogic;
    }

    public SearchKakoUriageLogic getKakouriageSearchLogic() {
        return kakouriageSearchLogic;
    }

    public void setKakouriageSearchLogic(SearchKakoUriageLogic kakouriageSearchLogic) {
        this.kakouriageSearchLogic = kakouriageSearchLogic;
    }

}
