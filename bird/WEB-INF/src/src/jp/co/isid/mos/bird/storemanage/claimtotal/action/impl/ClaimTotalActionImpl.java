package jp.co.isid.mos.bird.storemanage.claimtotal.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.storemanage.claimref.util.VoiceRefUtil;
import jp.co.isid.mos.bird.storemanage.claimtotal.action.ClaimTotalAction;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalRequestDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalSessionDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.storemanage.claimtotal.logic.SearchLogic;
import jp.co.isid.mos.bird.storemanage.common.util.StoreManageUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class ClaimTotalActionImpl implements ClaimTotalAction {
    public static final String initialize_ACTION_ID = "BSM017A01";
    public static final String doExecute_ACTION_ID = "BSM017A02";
    public static final String doSearchMise_ACTION_ID = "BSM017A03";
    public static final String doSearchOner_ACTION_ID = "BSM017A04";

    private static final String VIEW_ID_MISESEARCH = "BCO008V01";
    private static final String VIEW_ID_ONERSEARCH = "BCO006V01";
    private static final String VIEW_ID_CLAIMTOTAL = "BSM017V01";
    
    /* LOGIC */
    private ConditionInfoLogic claimtotalConditionInfoLogic;
    private SearchLogic claimtotalSearchLogic;
    /* DTO */
    private ClaimTotalSessionDto claimtotalSessionDto;
    private ClaimTotalRequestDto claimtotalRequestDto;
    private ClaimTotalRequestDto claimtotalRequestViewDto;
    private BirdDateInfo birdDateInfo;
    private BirdUserInfo birdUserInfo;
    private PullDownMenuDto pullDownMenuDto;
    private MiseSearchDto miseSearchDto;
    private OwnerSearchDto ownerSearchDto;
    
    public String doSearchMise() {
        //セッションDTOへ現在の検索条件を保持
        getClaimtotalSessionDto().setSearchFormDto(getClaimtotalRequestDto());
        //店検索DTOへ情報をセット
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(VIEW_ID_CLAIMTOTAL);
        miseSearchDto.setInitialFlag(true);
        miseSearchDto.setNeedReturnKind(true);
        miseSearchDto.setWindowId(getClaimtotalRequestDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getClaimtotalRequestDto().getCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        
        return VIEW_ID_MISESEARCH;
    }

    public String doSearchOner() {
        //セッションDTOへ現在の検索条件を保持
        getClaimtotalSessionDto().setSearchFormDto(getClaimtotalRequestDto());
        //店検索DTOへ情報をセット
        OwnerSearchDto onerSearchDto = getOwnerSearchDto();
        onerSearchDto.setNavigationCase(VIEW_ID_CLAIMTOTAL);
        onerSearchDto.setInitFlag(true);
        onerSearchDto.setNeedReturnKind(true);
        onerSearchDto.setWindowId(getClaimtotalRequestDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getClaimtotalRequestDto().getCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        
        return VIEW_ID_ONERSEARCH;
    }

    public String doExecute() {
        //店コード前ゼロ付加
        if (TaishoJoken.CODE_MISE.equals(getClaimtotalRequestDto().getTaishoJoken())) {
            CodeFormatter codeFormatter = new CodeFormatter(5);
            codeFormatter.setFormatPattern("00000");
            getClaimtotalRequestDto().setHyojiTaisho(codeFormatter.format(getClaimtotalRequestDto().getHyojiTaisho(), true));
        }
        //オーナー、店舗ユーザーの場合は、対象条件をセットする
        setTaishoJokenValue();
        
        searchData();
        return null;
    }

    /**
     * オーナー、店舗ユーザーの場合、パラメータ：対象条件、表示対象をセット
     */
    private void setTaishoJokenValue() {
        if (UserType.ONER.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
            getClaimtotalRequestDto().setTaishoJoken(TaishoJoken.CODE_ONER);
            List listOner = getBirdUserInfo().getUserOner();
            getClaimtotalRequestDto().setHyojiTaisho(((UIUserOner) listOner.get(0)).getOnerCd());
        }
        if (UserType.TENPO.equals(getBirdUserInfo().getMstUser().getUserTypeCd())) {
            getClaimtotalRequestDto().setTaishoJoken(TaishoJoken.CODE_MISE);
            List listMise = getBirdUserInfo().getUserMise();
            getClaimtotalRequestDto().setHyojiTaisho(((UIUserMise) listMise.get(0)).getMiseCd());
        }
    }
    public String initialize() {
        
        if (getPullDownMenuDto().isClearFlg()) {
            init();
            getPullDownMenuDto().setClearFlg(false);
        }
        else {
            //店検索
            if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT)  {
                setClaimtotalRequestDto(getClaimtotalSessionDto().getSearchFormDto(getMiseSearchDto().getWindowId()));
                //ウィンドウIDセット
                getClaimtotalRequestDto().setWindowId(getMiseSearchDto().getWindowId());
                if (getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT) {
                    getClaimtotalRequestDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                    getClaimtotalRequestDto().setHyojiTaisho(getMiseSearchDto().getMiseCd());
                }
                
                getMiseSearchDto().clear();
                //現在の対象年月で検索
                searchData();
                //1.【自画面共通】表示検索データ設定処理
                setView(getClaimtotalSessionDto()
                        , getClaimtotalRequestDto()
                        , getClaimtotalRequestViewDto());
            }
            //オーナー検索
            else if (getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT)  {
                setClaimtotalRequestDto(getClaimtotalSessionDto().getSearchFormDto(getOwnerSearchDto().getWindowId()));
                //ウィンドウIDセット
                getClaimtotalRequestDto().setWindowId(getOwnerSearchDto().getWindowId());
                if (getOwnerSearchDto().getReturnKind() == OwnerSearchDto.RETURNKIND_SELECT) {
                    getClaimtotalRequestDto().setTaishoJoken(TaishoJoken.CODE_ONER);
                    getClaimtotalRequestDto().setHyojiTaisho(getOwnerSearchDto().getOnerCd());
                }
                
                getOwnerSearchDto().clear();
                //現在の対象年月で検索
                searchData();
                //1.【自画面共通】表示検索データ設定処理
                setView(getClaimtotalSessionDto()
                        , getClaimtotalRequestDto()
                        , getClaimtotalRequestViewDto());
            }
            //個店ポータル、オーナー照会
            else if (getCommonCodeDto().getUseCommonDto()) {
                //初期化処理
                init();
                //条件をセット
                SelectItem selectItem = (SelectItem) getClaimtotalSessionDto().getListTaishoNengetu().get(0);
                getClaimtotalRequestDto().setTaishoNengetu(selectItem.getValue().toString());
                if (!StoreManageUtil.isNull(getCommonCodeDto().getMiseCd())) {
                    getClaimtotalRequestDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                    getClaimtotalRequestDto().setHyojiTaisho(getCommonCodeDto().getMiseCd());
                }
                else if (!StoreManageUtil.isNull(getCommonCodeDto().getOnerCd())) {
                    getClaimtotalRequestDto().setTaishoJoken(TaishoJoken.CODE_ONER);
                    getClaimtotalRequestDto().setHyojiTaisho(getCommonCodeDto().getOnerCd());
                }
                //検索
                searchData();
                getCommonCodeDto().clear();
            }
            setView(getClaimtotalSessionDto()
                    , getClaimtotalRequestDto()
                    , getClaimtotalRequestViewDto());
        }
        
        return null;
    }

    /**
     * お客様の声リンク
     */
    public String linkClaim() {
        CommonCodeDto dto = getCommonCodeDto();
        if (dto == null) {
            dto = new CommonCodeDto();
        }
        //オーナー、店舗ユーザーの対象条件、表示対象をセット
        setTaishoJokenValue();
        
        //遷移もとのViewID
        dto.setNavigationCase(StoreManageUtil.VIEW_ID_CLAIM_TOTAL);
        //会社コード
        dto.setCompanyCd(getClaimtotalRequestViewDto().getCompanyCd());
        //年月
        dto.setParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_NENGETU, getClaimtotalRequestDto().getLinkTaishoNengetu());
        //対象条件
        dto.setParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_TAISHOJOKEN, getClaimtotalRequestViewDto().getTaishoJoken());
        //表示対象
        dto.setParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_HYOJITAISHO, getClaimtotalRequestViewDto().getHyojiTaisho());
        //タイプコード
        dto.setParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_TYPECD, getClaimtotalRequestDto().getLinkRowType());
        //中分類
        dto.setParam(StoreManageUtil.CLAIM_BIRDLINK_PARAM_BNRM, getClaimtotalRequestDto().getLinkBnrM());
        
        dto.setUseCommonDto(true);

        return VoiceRefUtil.VIEW_ID_LIST;
    }
    
    /**
     * 表示検索データ設定処理
     * 
     * @param logic
     * @param requestDto
     * @param viewDto
     */
    private void setView(ClaimTotalSessionDto sessionDto, ClaimTotalRequestDto requestDto, ClaimTotalRequestDto viewDto) {
        if(sessionDto.isMustSearch(requestDto.getWindowId()) && sessionDto.isSearchedFlg(requestDto.getWindowId())) {
            //セッションに検索データが存在しない場合、検索処理の実行を行います。
            searchData();
        }
        //２．表示検索データが存在する場合、下記の処理を行う。
        if(sessionDto.isExistSearchedData(requestDto.getWindowId())) {
            //DTO【自画面照会情報保持】へDTO【自画面セッション用】.検索済み条件値格納リクエストDTOを設定します。
            sessionDto.getSearchedDataDto(requestDto.getWindowId(), viewDto);
        }
    }
    
    /**
     * 検索処理
     * @param sessionDto
     * @param requestDto
     */
    private void searchData() {
        getClaimtotalSessionDto().setSearchedFlg(getClaimtotalRequestDto().getWindowId(), false);
        getClaimtotalSearchLogic().execute(getClaimtotalSessionDto(), getClaimtotalRequestDto());
        getClaimtotalSessionDto().setSearchedDataDto(getClaimtotalRequestDto());
        getClaimtotalSessionDto().setSearchedFlg(getClaimtotalRequestDto().getWindowId(), true);
    }
    
    /**
     * 画面初期化処理
     */
    private void init() {
        getClaimtotalSessionDto().setBirdDateInfo(getBirdDateInfo());
        getClaimtotalSessionDto().setBirdUserInfo(getBirdUserInfo());
        getClaimtotalSessionDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
        // WindowID設定
        getClaimtotalRequestDto().setWindowId(getClaimtotalSessionDto().updateWindowid());
        // 条件情報取得
        getClaimtotalConditionInfoLogic().execute(getClaimtotalSessionDto(), getClaimtotalRequestDto());
    }
    
    public ConditionInfoLogic getClaimtotalConditionInfoLogic() {
        return claimtotalConditionInfoLogic;
    }

    public void setClaimtotalConditionInfoLogic(
            ConditionInfoLogic claimtotalConditionInfoLogic) {
        this.claimtotalConditionInfoLogic = claimtotalConditionInfoLogic;
    }

    public ClaimTotalRequestDto getClaimtotalRequestDto() {
        return claimtotalRequestDto;
    }

    public void setClaimtotalRequestDto(ClaimTotalRequestDto claimtotalRequestDto) {
        this.claimtotalRequestDto = claimtotalRequestDto;
    }

    public ClaimTotalSessionDto getClaimtotalSessionDto() {
        return claimtotalSessionDto;
    }

    public void setClaimtotalSessionDto(ClaimTotalSessionDto claimtotalSessionDto) {
        this.claimtotalSessionDto = claimtotalSessionDto;
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

    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

    public ClaimTotalRequestDto getClaimtotalRequestViewDto() {
        return claimtotalRequestViewDto;
    }

    public void setClaimtotalRequestViewDto(
            ClaimTotalRequestDto claimtotalRequestViewDto) {
        this.claimtotalRequestViewDto = claimtotalRequestViewDto;
    }

    public SearchLogic getClaimtotalSearchLogic() {
        return claimtotalSearchLogic;
    }

    public void setClaimtotalSearchLogic(SearchLogic claimtotalSearchLogic) {
        this.claimtotalSearchLogic = claimtotalSearchLogic;
    }

    private CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }
    
    
}
