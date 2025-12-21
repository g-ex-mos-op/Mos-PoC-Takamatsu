package jp.co.isid.mos.bird.bizsupport.campcheckamount.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.action.CampCheckAmountAction;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountCommon;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.common.CampCheckAmountConstants;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dto.CampCheckAmountDto;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dto.CampCheckAmountSaveDto;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetBlockInfoLogic;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetCampaignInfoLogic;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetSetQuantityInfoLogic;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetSpotQuantityInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.common.logic.GetOnerLogic;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

/**
 * キャンペーン設定数量確認　条件・結果画面アクション
 *
 * @author xlee
 */
public class CampCheckAmountActionImpl implements CampCheckAmountAction {

	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID = "BBS027A01";

	/** アクションID定義:店検索フォームアクション */
    public static final String callMiseForm_ACTION_ID = "BBS027A02";

    /** アクションID定義:実行アクション */
    public static final String execute_ACTION_ID = "BBS027A03";

    /** アクションID定義:タブ切替アクション */
    public static final String changeTab_ACTION_ID = "BBS027A04";

    /* -- DTO -- */
    /**　店検索DTO */
    private MiseSearchDto miseSearchDto;

    /**　オーナー検索DTO */
    private OwnerSearchDto ownerSearchDto;

    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;

	/** キャンペーン設定数量確認DTO　：request */
	private CampCheckAmountDto campCheckAmountDto;

	/** 条件保持用DTO　：request */
	private CampCheckAmountSaveDto campCheckAmountSaveDto;

    /* -- LOGIC -- */
    /** ブロック情報取得ロジック */
    private GetBlockInfoLogic getBlockInfoLogic;

    /**　対象キャンペーン情報取得ロジック */
    private GetCampaignInfoLogic getCampaignInfoLogic;

    /** 設定数量情報取得ロジック */
    private GetSetQuantityInfoLogic getSetQuantityInfoLogic;

    /** スポット数量情報取得ロジック */
    private GetSpotQuantityInfoLogic getSpotQuantityInfoLogic;

    /** 支部情報取得 */
    private GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic;

    /** 店舗情報取得 */
    private GetMiseLogic getMiseLogic;

    /** オーナー情報取得 */
    private GetOnerLogic getOnerLogic;

    /**
     * 初期処理
     *
     * @return 画面遷移情報
     */
    public String initialize() {

    	//1-1.ユーザタイプ判定
    	String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        //1-2.ユーザータイプを設定する
        getCampCheckAmountDto().setUserTypeCd(userTypeCd);

    	//2.システム日付
    	String sysDate = getBirdDateInfo().getSysDate();

        //3-1.対象キャンペーン情報を取得
		List taishoCmpList = getGetCampaignInfoLogic().execute(CampCheckAmountCommon.getPosPrevDate(sysDate), sysDate,
				CampCheckAmountCommon.getPosEndDatePrev1(sysDate));

        //3-2.対象キャンペーン情報List
        getCampCheckAmountDto().setTaishoCmpList(taishoCmpList);

        /* ****** 本部ユーザ ****** */
        if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(userTypeCd)) {
            //4-1.支部情報を取得
        	List taishoSibuList = getSibuHoyuTenpoLogic().execute(getCampCheckAmountDto().getCompanyCd(),
        							  getBirdUserInfo().getUserID(),
        							  getBirdUserInfo().isLimit());
        	//4-2.支部情報設定
        	getCampCheckAmountDto().setTaishoSibuList(taishoSibuList);
            //4-3.ブロック情報を取得
        	List taishoBlockList = getGetBlockInfoLogic().execute();
        	//4-4.ブロック情報設定
        	getCampCheckAmountDto().setTaishoBlockList(taishoBlockList);
        }

        /* ******* 初期処理 ****** */
        if (getPullDownMenuDto().isClearFlg()) {
        	//newWindowIDを設定(店検索の時、検索条件を保持する為)
        	getCampCheckAmountSaveDto().updateWindowid();
        	/* ****** デフォルト ****** */
        	//1.実行フラグ: 0
        	getCampCheckAmountDto().setExecFlg(CampCheckAmountConstants.EXEC_INIT_FLG);
        	//2.ボタン名：実行
        	getCampCheckAmountDto().setButtonNm(CampCheckAmountConstants.BUTTON_NM_INIT);
        	//3.対象条件：個店
        	getCampCheckAmountDto().setTaishoCond(CampCheckAmountConstants.TAISHO_COND_TENPO);
            //4.タブの設定:スポット数量（但し、対象キャンペーンが存在しない場合は設定数量タブがデフォルト）
            if(taishoCmpList == null || taishoCmpList.size() == 0) {
            	getCampCheckAmountDto().setTabKbn(CampCheckAmountConstants.TAB_KBN_SET);
            } else {
            	getCampCheckAmountDto().setTabKbn(CampCheckAmountConstants.TAB_KBN_SPOT);
            }
        	//5.メニューフラグfalseにセット
        	getPullDownMenuDto().setClearFlg(false);
        } else {
        	if((getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
            	//2007.03.05 個店ポタル・オーナー情報照会から遷移した場合newWindowIDを設定
            	getCampCheckAmountSaveDto().updateWindowid();
            	//2007.03.05 実行フラグ: 0
            	getCampCheckAmountDto().setExecFlg(CampCheckAmountConstants.EXEC_INIT_FLG);
        		//2007.02.26　追加
        		//個店ポタルもしくはオーナー情報照会から遷移した場合
            	//1.ボタン名：実行で設定
            	getCampCheckAmountDto().setButtonNm(CampCheckAmountConstants.BUTTON_NM_INIT);
            	//2.対象キャンペーンリストが存在する場合
            	if(0 != getCampCheckAmountDto().getTaishoCmpListSz()) {
					String kotenCampNo = getGetCampaignInfoLogic().executeKoten(
							CampCheckAmountCommon.getPosPrevDate(sysDate), sysDate,
							CampCheckAmountCommon.getPosEndDatePrev1(sysDate));
	                getCampCheckAmountDto().setTaishoCmpNo(kotenCampNo);
	                //2007.03.02 タブの区分追加
	                getCampCheckAmountDto().setTabKbn(CampCheckAmountConstants.TAB_KBN_SPOT);
            	} else {
            		//2007.03.02 タブの区分追加
            		getCampCheckAmountDto().setTabKbn(CampCheckAmountConstants.TAB_KBN_SET);
            	}
            	//3.共通コードＤＴＯに店コードが存在する場合、画面の対象条件は個店、表示対象には渡された店コードを設定する
            	if(!CampCheckAmountCommon.isNull(getCommonCodeDto().getMiseCd())) {
            		getCampCheckAmountDto().setTaishoCond(CampCheckAmountConstants.TAISHO_COND_TENPO);
            		getCampCheckAmountDto().setTaishoMiseCd(getCommonCodeDto().getMiseCd());
            	} else if(!CampCheckAmountCommon.isNull(getCommonCodeDto().getOnerCd())) {
            	//4.共通コードＤＴＯにオーナーコードが存在する場合、
            		getCampCheckAmountDto().setTaishoCond(CampCheckAmountConstants.TAISHO_COND_OWNER);
            		getCampCheckAmountDto().setTaishoOnerCd(getCommonCodeDto().getOnerCd());
            	}
            	//5.実行する
                execute();
        	} else if ((getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT) ||
        	         (getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT)) {
        		//1.店選択画面から遷移した場合
        		if(getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT) {
        			getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
        			//1-1.保持されている以前の検索条件を取得する。
        			getCampCheckAmountSaveDto().setWindowId(getMiseSearchDto().getWindowId());
        		} else if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) {
                    //【DTO】//オーナー選択.遷移情報を無効に設定する。
                	getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
                    //DTO【販売状況一覧】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
                	getCampCheckAmountSaveDto().setWindowId(getOwnerSearchDto().getWindowId());
        		}
    			//1-2.保持した検索条件を取得する。
    			setCampCheckAmountDto(
		        		CampCheckAmountCommon.getCondSave(getCampCheckAmountDto(),getCampCheckAmountSaveDto()));

		        if(CampCheckAmountConstants.EXEC_RESERCH_END_FLG.equals(getCampCheckAmountDto().getExecFlg())) {
		        	//1-2-1.
		        	getCampCheckAmountDto().setCallExecKbn(CampCheckAmountConstants.CALL_EXEC_KBN);
		        	//1-2-2.以前、実行処理が正常処理された後、店検索した場合
		        	execute();
		        } else if(CampCheckAmountConstants.EXEC_INIT_FLG.equals(getCampCheckAmountDto().getExecFlg())) {
		        	//1-2-3.実行する前、店検索した場合
		        	getCampCheckAmountDto().setButtonNm(CampCheckAmountConstants.BUTTON_NM_INIT);
		        } else {
		        	//1-2-4.以前、実行処理で例外が発生した後、店検索した場合
		        	getCampCheckAmountDto().setButtonNm(CampCheckAmountConstants.BUTTON_NM_REEXEC);
		        }
				// 1-3.表示用検索条件をセッションから取得
    			setCampCheckAmountDto(
		        		CampCheckAmountCommon.getViewCondSave(getCampCheckAmountDto(),getCampCheckAmountSaveDto()));
	            // 1-4.店決定
				if(getMiseSearchDto().isActionFlg()){
		            //1-4-1.店コードの設定：選択した店コードを設定
					getCampCheckAmountDto().setTaishoMiseCd(getMiseSearchDto().getMiseCd());
					getMiseSearchDto().setActionFlg(false);
				} else if(getOwnerSearchDto().isActionFlag()){
		            //オーナーコードの設定
					getCampCheckAmountDto().setTaishoOnerCd(getOwnerSearchDto().getOnerCd());
					getOwnerSearchDto().setActionFlag(false);
				}
        	} else {
    			//2-1.実行後、エラーが発生した場合はボタン名を設定
    			if(getCampCheckAmountDto().getResultListSz() == null){
	    			if(CampCheckAmountConstants.EXEC_INIT_FLG.equals(getCampCheckAmountDto().getExecFlg())) {
	    				getCampCheckAmountDto().setButtonNm(CampCheckAmountConstants.BUTTON_NM_INIT);
	    			} else {
	    				getCampCheckAmountDto().setButtonNm(CampCheckAmountConstants.BUTTON_NM_REEXEC);
	    			}
    			}
				// 2-2.表示用検索条件をセッションから取得
    			setCampCheckAmountDto(
		        		CampCheckAmountCommon.getViewCondSave(getCampCheckAmountDto(),getCampCheckAmountSaveDto()));
    		}
        }
        // 自画面へ遷移
        return null;
    }

    /**
     * 店検索フォーム
     *
     * @return　店検索画面
     */
    public String callMiseForm() {
    	getMiseSearchDto().clear();
        //1.戻る先設定
    	getMiseSearchDto().setNavigationCase(CampCheckAmountConstants.VIEWID_CONDITION);
        //2.初期化フラグ
        getMiseSearchDto().setInitialFlag(true);
        //3.複数WindowID
        getMiseSearchDto().setWindowId(getCampCheckAmountSaveDto().getWindowId());
        //4.会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getBirdUserInfo().getMstUser().getRCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //5.
        getMiseSearchDto().setNeedReturnKind(true);
        //6.表示用セッションに設定
        CampCheckAmountCommon.setViewCondSave(getCampCheckAmountDto(), getCampCheckAmountSaveDto());

        return CampCheckAmountConstants.VIEWID_MISESEARCH;
    }

    /**
     * オーナー検索フォーム
     *
     * @return　オーナー検索画面
     */
    public String callOnerForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(CampCheckAmountConstants.VIEWID_CONDITION);
        getOwnerSearchDto().setInitFlag(true);
        //複数WindowID
        getOwnerSearchDto().setWindowId(getCampCheckAmountSaveDto().getWindowId());
        //オーナー選択.遷移情報を有効に設定。
        getOwnerSearchDto().setNeedReturnKind(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getCampCheckAmountDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //6.表示用セッションに設定
        CampCheckAmountCommon.setViewCondSave(getCampCheckAmountDto(), getCampCheckAmountSaveDto());

        return CampCheckAmountConstants.VIEWID_ONERSEARCH;
    }

    /**
     * 検索処理
     *
     * @return 画面遷移情報
     */
	public String execute() {
		//1.画面から渡されないパラメータ：システム日付設定
		getCampCheckAmountDto().setSysDate(getBirdDateInfo().getSysDate());

		//2.再検索実行前、フラグをクリア
		if(!CampCheckAmountConstants.EXEC_INIT_FLG.equals(getCampCheckAmountDto().getExecFlg())) {
			getCampCheckAmountDto().setExecFlg(CampCheckAmountConstants.EXEC_RESERCH_STR_FLG);
		}

		//3.オーナユーザの場合、対象条件がオーナーの場合
		if(CampCheckAmountConstants.USER_TYPE_ONER.equals(getCampCheckAmountDto().getUserTypeCd())) {
			getCampCheckAmountDto().setOnerCdList(
					CampCheckAmountCommon.getOnerList(getBirdUserInfo(), getCampCheckAmountDto().getCompanyCd()));
		}
		if(CampCheckAmountConstants.TAISHO_COND_OWNER.equals(getCampCheckAmountDto().getTaishoCond())) {
			List tmpOnerCd = new ArrayList();
			tmpOnerCd.add(getCampCheckAmountDto().getTaishoOnerCd());
			getCampCheckAmountDto().setOnerCdList(tmpOnerCd);
		}

		//4.実行前セッションにパラメータを設定：
        CampCheckAmountCommon.setCondSave(getCampCheckAmountDto(), getCampCheckAmountSaveDto());

		//5.検索後DTOへ設定
		setCampCheckAmountDto(CampCheckAmountCommon.getSearchKbn(
				getCampCheckAmountDto(),
				getGetSetQuantityInfoLogic(),
				getGetSpotQuantityInfoLogic(),
				getGetMiseLogic(),
				getGetOnerLogic()));

		//6.実行結果をセッションに保持
        CampCheckAmountCommon.setCondSave(getCampCheckAmountDto(), getCampCheckAmountSaveDto());

		return null;
	}

    /**
     * タブの切替処理
     *
     * @return 画面遷移情報
     */
	public String changeTab() {
		//1.画面から渡されないパラメータ：システム日付設定
		getCampCheckAmountDto().setSysDate(getBirdDateInfo().getSysDate());

		//2.オーナユーザの場合、対象条件がオーナーの場合
		if(CampCheckAmountConstants.USER_TYPE_ONER.equals(getCampCheckAmountDto().getUserTypeCd())) {

			getCampCheckAmountDto().setOnerCdList(
					CampCheckAmountCommon.getOnerList(getBirdUserInfo(), getCampCheckAmountDto().getCompanyCd()));
		}

        //3.保持された条件を取得するが、タブ区分だけはrequestDTOから取得する
		String tmpTabKbn = getCampCheckAmountDto().getTabKbn();
		//4.表示用セッションにrequestDto設定
        CampCheckAmountCommon.setViewCondSave(getCampCheckAmountDto(), getCampCheckAmountSaveDto());
		//5.検索用セッションから検索条件取得
        setCampCheckAmountDto(CampCheckAmountCommon.getCondSave(getCampCheckAmountDto(), getCampCheckAmountSaveDto()));
        //6.タブ区分設定
		getCampCheckAmountDto().setTabKbn(tmpTabKbn);
		if(CampCheckAmountConstants.TAISHO_COND_OWNER.equals(getCampCheckAmountDto().getTaishoCond())) {
			List tmpOnerCd = new ArrayList();
			tmpOnerCd.add(getCampCheckAmountDto().getTaishoOnerCd());
			getCampCheckAmountDto().setOnerCdList(tmpOnerCd);
		}

		//7.検索後DTOへ設定
		setCampCheckAmountDto(CampCheckAmountCommon.getResult(
				getCampCheckAmountDto(),
				getGetSetQuantityInfoLogic(),
				getGetSpotQuantityInfoLogic(),
				getGetMiseLogic(),
				getGetOnerLogic(),
				CampCheckAmountConstants.PROC_KBN_CHGTAB));

		return null;
	}

    /**
     * Seaser2Containaer取得処理
     *
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRDユーザー情報取得処理
     *
     * @return
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD日付情報取得処理
     *
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * 共通コードDTO取得
     * @return 共通コードDTO
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) getS2Container().getComponent(CommonCodeDto.class);
    }

    /**
     * メニューDTOを取得します。
     * @return メニューDTO
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * メニューDTOを設定します。
     * @param pullDownMenuDto
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * 店検索DTO取得処理
     * @return
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * 店検索DTO設定処理
     *
     * @param miseSearchDto
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    /**
     * オーナー検索DTO取得処理
     * @return
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    /**
     * オーナー検索DTO設定処理
     *
     * @param ownerSearchDto
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

    /**
     * キャンペーン設定数量確認DTOを取得します。:request
     * @return  キャンペーン設定数量確認DTO
     */
    public CampCheckAmountDto getCampCheckAmountDto() {
    	return campCheckAmountDto;
    }

    /**
     *  キャンペーン設定数量確認DTOを設定します。
     * @param campCheckAmountDto　 キャンペーン設定数量確認DTO
     */
    public void setCampCheckAmountDto(CampCheckAmountDto campCheckAmountDto) {
    	this.campCheckAmountDto = campCheckAmountDto;
    }

    /**
     * 条件保持DTOを取得します。:session
     * @return  条件保持DTO
     */
    public CampCheckAmountSaveDto getCampCheckAmountSaveDto() {
    	return campCheckAmountSaveDto;
    }

    /**
     *  条件保持DTOを設定します。
     * @param campCheckAmountSaveDto　 条件保持DTO
     */
    public void setCampCheckAmountSaveDto(CampCheckAmountSaveDto campCheckAmountSaveDto) {
    	this.campCheckAmountSaveDto = campCheckAmountSaveDto;
    }

    /**
     * ブロック情報取得ロジックを取得します。
     * @return 　ブロック情報取得ロジック
     */
    public GetBlockInfoLogic getGetBlockInfoLogic() {
        return getBlockInfoLogic;
    }

    /**
     * ブロック情報取得ロジックを設定します。
     * @param getBlockInfoLogic　ブロック情報取得ロジック
     */
    public void setGetBlockInfoLogic(GetBlockInfoLogic getBlockInfoLogic) {
        this.getBlockInfoLogic = getBlockInfoLogic;
    }

    /**
     * 対象キャンペーン情報取得ロジックを取得します。
     * @return 　対象キャンペーン情報取得ロジック
     */
    public GetCampaignInfoLogic getGetCampaignInfoLogic() {
        return getCampaignInfoLogic;
    }

    /**
     * 対象キャンペーン情報取得ロジックを設定します。
     * @param getCampaignInfoLogic　対象キャンペーン情報取得ロジック
     */
    public void setGetCampaignInfoLogic(GetCampaignInfoLogic getCampaignInfoLogic) {
        this.getCampaignInfoLogic = getCampaignInfoLogic;
    }

    /**
     * 設定数量情報取得ロジックを取得します。
     * @return 　設定数量情報取得ロジック
     */
    public GetSetQuantityInfoLogic getGetSetQuantityInfoLogic() {
        return getSetQuantityInfoLogic;
    }

    /**
     * 設定数量情報取得ロジックを設定します。
     * @param getSetQuantityInfoLogic　設定数量情報取得ロジック
     */
    public void setGetSetQuantityInfoLogic(GetSetQuantityInfoLogic getSetQuantityInfoLogic) {
        this.getSetQuantityInfoLogic = getSetQuantityInfoLogic;
    }

    /**
     * スポット数量情報取得ロジックを取得します。
     * @return 　スポット数量情報取得ロジック
     */
    public GetSpotQuantityInfoLogic getGetSpotQuantityInfoLogic() {
        return getSpotQuantityInfoLogic;
    }

    /**
     * スポット数量情報取得ロジックを設定します。
     * @param getSpotQuantityInfoLogic　スポット数量情報取得ロジック
     */
    public void setGetSpotQuantityInfoLogic(GetSpotQuantityInfoLogic getSpotQuantityInfoLogic) {
        this.getSpotQuantityInfoLogic = getSpotQuantityInfoLogic;
    }

    /**
     * 支部情報取得ロジックを取得します。
     * @return 支部情報取得ロジック。
     */
    public GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic() {
        return sibuHoyuTenpoLogic;
    }

    /**
     * 支部情報取得ロジックを設定します。
     * @param getSibuTorikomiLogic 支部情報取得ロジック
     */
    public void setSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic) {
        this.sibuHoyuTenpoLogic = sibuHoyuTenpoLogic;
    }

    /**
     * 店舗情報取得ロジックを取得します。
     * @return 店舗情報取得ロジック。
     */
    public GetMiseLogic getGetMiseLogic() {
        return getMiseLogic;
    }

    /**
     * 店舗情報取得ロジックを設定します。
     * @param getMiseLogic 店舗情報取得ロジック
     */
    public void setGetMiseLogic(GetMiseLogic getMiseLogic) {
        this.getMiseLogic = getMiseLogic;
    }

    /**
     * オーナー情報取得ロジックを取得します。
     * @return オーナー情報取得ロジック。
     */
    public GetOnerLogic getGetOnerLogic() {
        return getOnerLogic;
    }

    /**
     * オーナー情報取得ロジックを設定します。
     * @param getOnerLogic オーナー情報取得ロジック
     */
    public void setGetOnerLogic(GetOnerLogic getOnerLogic) {
        this.getOnerLogic = getOnerLogic;
    }
}
