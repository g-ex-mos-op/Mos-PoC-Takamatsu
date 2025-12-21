package jp.co.isid.mos.bird.analysis.sibuaverage.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.sibuaverage.action.SibuAverageAction;
import jp.co.isid.mos.bird.analysis.sibuaverage.code.ShukeiKbn;
import jp.co.isid.mos.bird.analysis.sibuaverage.code.SibuAverageConst;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageReqDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.ChangeOnerLogic;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.GetSibuAverageLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 支部平均比較アクション
 * @author xnkusama
 *
 */
public class SibuAverageActionImpl implements SibuAverageAction {
    /**ACTION_ID*/
    public static final String initialize_ACTION_ID = "BDT006A01";
    public static final String callMiseForm_ACTION_ID = "BDT006A02";
    public static final String callOnerForm_ACTION_ID = "BDT006A03";
    public static final String execute_ACTION_ID = "BDT006A04";
    public static final String downloadCsv_ACTION_ID = "BDT006A05";

    /**DTO*/
    private BirdUserInfo birdUserInfo;
    private BirdDateInfo birdDateInfo;
    private PullDownMenuDto pullDownMenuDto;
    private MiseSearchDto miseSearchDto;
    private OwnerSearchDto ownerSearchDto;
    private SibuAverageDto sibuaverageDto;
    /** DTO【Request情報】*/
    private SibuAverageReqDto sibuaverageReqDto;
    /** DTO【検索結果】*/
    private SibuAverageReqDto sibuaverageViewDto;
    
    /**LOGIC*/
    private ConditionInfoLogic sibuaverageConditionLogic;
    private GetSibuAverageLogic sibuaverageSearchLogic;
    /**LOGIC【オーナー変更ロジック】**/
    private ChangeOnerLogic sibuaverageChangeOnerLogic;
    
    /**ACTION*/
    private CsvOutputAction sibuaverageCsvAction;
    
    /**
     * 実行
     */
    public String execute() {
        getSibuaverageReqDto().setUserTypeCd(getSibuaverageDto().getUserTypeCd());
        //１．複数ウィンドウ対応
        if (getSibuaverageReqDto().isNewWindowFlg()) {
            getSibuaverageReqDto().setWindowId(getSibuaverageDto().updateWindowid());
            getSibuaverageReqDto().setNewWindowFlg(false);
        }

        //２．LOGIC【検索ロジック】を実行します。
        boolean searchEndFlg = getSibuaverageSearchLogic().execute(getSibuaverageDto(), getSibuaverageReqDto());
        if (searchEndFlg) {
        	if (UserType.isHonbu(getSibuaverageDto().getUserTypeCd())) {
        		getSibuaverageReqDto().setSibuMode(false);
        	}
        }
    	//３．再検索フラグセット
        getSibuaverageDto().setResearchFlg(getSibuaverageReqDto().getWindowId(), true);

        //本部ユーザーの場合、支部コードをクリア
        if (UserType.HONBU.equals(getSibuaverageDto().getUserTypeCd())) {
            getSibuaverageReqDto().setTaishoSibu("");
        }
        
        return null;
    }

    /**
     * CSVダウンロード
     */
    public String downloadCsv() {
    	int windowId = getSibuaverageReqDto().getWindowId();
        //現在表示しているデータがセッションDTOに存在しない場合
    	SibuAverageReqDto searchedDto = getSibuaverageDto().getSearchedDataDto(windowId);
    	if ((searchedDto != null && searchedDto.getListData()==null) 
        		&& getSibuaverageDto().isSearchedFlg(windowId))
        {
            //１．検索ロジック実行
            getSibuaverageSearchLogic().execute(getSibuaverageDto(), getSibuaverageViewDto());
            searchedDto = getSibuaverageDto().getSearchedDataDto(windowId);
        }
        setSibuaverageViewDto(searchedDto);
        //２．共通CSVアクション実行
        try {
            getSibuaverageViewDto().setUserTypeCd(getSibuaverageDto().getUserTypeCd());
            ((CsvOutput2ActionImpl)getSibuaverageCsvAction()).setCsvOutputDto(getSibuaverageViewDto());
            getSibuaverageCsvAction().downloadCsv();
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
        getSibuaverageReqDto().setUserTypeCd(getSibuaverageDto().getUserTypeCd());
        //店検索DTOへ遷移元情報をセット
        getMiseSearchDto().setNavigationCase(SibuAverageConst.VIEW_ID);
        getMiseSearchDto().setInitialFlag(true);
        getMiseSearchDto().setNeedReturnKind(true);
        getMiseSearchDto().setWindowId(getSibuaverageReqDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getSibuaverageReqDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);

        //現在選択されている検索条件を保持
        getSibuaverageDto().setRequestDto(getSibuaverageReqDto());
        
        return SibuAverageConst.VIEW_ID_MISESEARCH;
    }
    
    /**
     * オーナー検索遷移
     */
    public String callOnerForm() {
        getSibuaverageReqDto().setUserTypeCd(getSibuaverageDto().getUserTypeCd());
        //店検索DTOへ遷移元情報をセット
        getOwnerSearchDto().setNavigationCase(SibuAverageConst.VIEW_ID);
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setNeedReturnKind(true);
        getOwnerSearchDto().setWindowId(getSibuaverageReqDto().getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getSibuaverageReqDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);

        //現在選択されている検索条件を保持
        getSibuaverageDto().setRequestDto(getSibuaverageReqDto());
        
        return SibuAverageConst.VIEW_ID_ONERSEARCH;
    }
    
    /**
     * 初期処理
     */
    public String initialize() {
        //メニューから遷移された場合
        if (getPullDownMenuDto().isClearFlg()) {
            init();
        }
        //BIRD内画面から遷移された場合（店、オーナーコード指定の場合のみ動作）
        else if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            //初期処理
            init();
            String companyCd = getCommonCodeDto().getCompanyCd();
            String miseCd = getCommonCodeDto().getMiseCd();
            String onerCd = getCommonCodeDto().getOnerCd();
            //会社コード、店orオーナーコードが指定されている場合は、デフォルト条件で検索
            if (!CommonUtil.isNull(companyCd) && !CommonUtil.isNull(miseCd)) {
                getSibuaverageReqDto().setCompanyCd(companyCd);
                getSibuaverageReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
                getSibuaverageReqDto().setHyojiTaishoMise(miseCd);
                SelectItem itemKikan = (SelectItem) getSibuaverageDto().getListKikanSitei().get(0);
                getSibuaverageReqDto().setKikanSitei(itemKikan.getValue().toString());
                //検索処理実行
                execute();
            }
            else if (!CommonUtil.isNull(companyCd) && !CommonUtil.isNull(onerCd)) {
                getSibuaverageReqDto().setCompanyCd(companyCd);
                getSibuaverageReqDto().setTaishoJoken(TaishoJoken.CODE_ONER);
                getSibuaverageReqDto().setHyojiTaishoOner(onerCd);
                SelectItem itemKikan = (SelectItem) getSibuaverageDto().getListKikanSitei().get(0);
                getSibuaverageReqDto().setKikanSitei(itemKikan.getValue().toString());
                //検索処理実行
                execute();
            }
        }
        else {
        	int windowId = getSibuaverageReqDto().getWindowId();
	        //店検索から遷移された場合
	        if (MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
	        	windowId = getMiseSearchDto().getWindowId();
		        //検索画面遷移時のDTO【Request情報】設定
		        setSibuaverageReqDto(getSibuaverageDto().getRequestDto(windowId));
		        getSibuaverageDto().removeRequestDto(windowId);
	            //店コードが選択されていた場合は、DTOへセット
	            if (MiseSearchDto.RETURNKIND_SELECT == getMiseSearchDto().getReturnKind()) {
	                getSibuaverageReqDto().setHyojiTaishoMise(getMiseSearchDto().getMiseCd());
	            }
	            //店検索DTOクリア
	            getMiseSearchDto().clear();
	        }
	        //オーナー検索から遷移された場合
	        else if (OwnerSearchDto.RETURNKIND_INIT != getOwnerSearchDto().getReturnKind()) {
	            //ウィンドウIDセット
	        	windowId = getOwnerSearchDto().getWindowId();
		        //検索画面遷移時のDTO【Request情報】設定
		        setSibuaverageReqDto(getSibuaverageDto().getRequestDto(windowId));
		        getSibuaverageDto().removeRequestDto(windowId);
	            //オーナーコードが選択されていた場合は、DTOへセット
	            if (OwnerSearchDto.RETURNKIND_SELECT == getOwnerSearchDto().getReturnKind()) {
	                getSibuaverageReqDto().setHyojiTaishoOner(getOwnerSearchDto().getOnerCd());
	                getSibuaverageReqDto().setSibuMode(false);
	            }
	            //オーナー検索DTOクリア
	            getOwnerSearchDto().clear();
	        }
	        //検索済み情報がある場合はDTOへセット
        	SibuAverageReqDto searchedDto = getSibuaverageDto().getSearchedDataDto(windowId);
            if ((searchedDto != null && searchedDto.getListData()==null) 
            		&& getSibuaverageDto().isSearchedFlg(windowId))
            {
                //検索済み結果がセッションDTOにない場合は、再検索
                getSibuaverageSearchLogic().execute(getSibuaverageDto(), searchedDto);
                searchedDto = getSibuaverageDto().getSearchedDataDto(windowId);
            }
            setSibuaverageViewDto(searchedDto);
	        
        }
        String targetOnerCd = getSibuaverageReqDto().getTargetOnerCd();
        if(CommonUtil.isNull(targetOnerCd) || getSibuaverageDto().getListShukeiKbn(targetOnerCd) == null) {
        	getSibuaverageReqDto().setListShukeiKbn(ShukeiKbn.getUIListPullDownList());
        }
        else {
        	getSibuaverageReqDto().setListShukeiKbn(getSibuaverageDto().getListShukeiKbn(targetOnerCd));
        }
        getSibuaverageReqDto().setUserTypeCd(getSibuaverageDto().getUserTypeCd());
        //再検索フラグセット
        getSibuaverageReqDto().setReSearchFlg(getSibuaverageDto().isResearchFlg(getSibuaverageReqDto().getWindowId()));
        return null;
    }

    /**
     * 共通初期処理
     */
    private void init() {
        //DTO初期化処理
        initDto();
        //複数ウィンドウ制御
        getSibuaverageReqDto().setWindowId(getSibuaverageDto().createWindowId());
        //検索条件情報取得
        getSibuaverageConditionLogic()
            .execute(getSibuaverageDto(), getSibuaverageReqDto());
        //フラグクリア
        getPullDownMenuDto().setClearFlg(false);
    }
    
    /**
     * DTO初期化処理
     */
    private void initDto() {
        //ユーザー、日付情報をDTOへセット
        getSibuaverageDto().setBirdUserInfo(getBirdUserInfo());
        getSibuaverageDto().setBirdDateInfo(getBirdDateInfo());
        //表示対象の初期値セット（初期値：店）
        getSibuaverageReqDto().setTaishoJoken(TaishoJoken.CODE_MISE);
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

    public SibuAverageDto getSibuaverageDto() {
        return sibuaverageDto;
    }

    public void setSibuaverageDto(SibuAverageDto sibuaverageDto) {
        this.sibuaverageDto = sibuaverageDto;
    }

    public SibuAverageReqDto getSibuaverageReqDto() {
        return sibuaverageReqDto;
    }

    public void setSibuaverageReqDto(SibuAverageReqDto sibuaverageReqDto) {
        this.sibuaverageReqDto = sibuaverageReqDto;
    }

    public SibuAverageReqDto getSibuaverageViewDto() {
        return sibuaverageViewDto;
    }

    public void setSibuaverageViewDto(SibuAverageReqDto sibuaverageViewDto) {
        this.sibuaverageViewDto = sibuaverageViewDto;
    }

    public ConditionInfoLogic getSibuaverageConditionLogic() {
        return sibuaverageConditionLogic;
    }

    public void setSibuaverageConditionLogic(
            ConditionInfoLogic sibuaverageConditionLogic) {
        this.sibuaverageConditionLogic = sibuaverageConditionLogic;
    }

    public CsvOutputAction getSibuaverageCsvAction() {
        return sibuaverageCsvAction;
    }

    public void setSibuaverageCsvAction(CsvOutputAction sibuaverageCsvAction) {
        this.sibuaverageCsvAction = sibuaverageCsvAction;
    }

    public GetSibuAverageLogic getSibuaverageSearchLogic() {
        return sibuaverageSearchLogic;
    }

    public void setSibuaverageSearchLogic(GetSibuAverageLogic sibuaverageSearchLogic) {
        this.sibuaverageSearchLogic = sibuaverageSearchLogic;
    }

    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

	/**
	 * @return クラス変数sibuaverageChangeOnerLogic を戻します。
	 */
	public ChangeOnerLogic getSibuaverageChangeOnerLogic() {
		return sibuaverageChangeOnerLogic;
	}

	/**
	 * @param sibuaverageChangeOnerLogic を クラス変数sibuaverageChangeOnerLogicへ設定します。
	 */
	public void setSibuaverageChangeOnerLogic(
			ChangeOnerLogic sibuaverageChangeOnerLogic) {
		this.sibuaverageChangeOnerLogic = sibuaverageChangeOnerLogic;
	}

}
