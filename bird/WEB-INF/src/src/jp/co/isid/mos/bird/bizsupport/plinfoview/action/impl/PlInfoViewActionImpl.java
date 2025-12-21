/*
 * 作成日: 2006/04/03
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.plinfoview.action.PlInfoViewAction;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dto.PlInfoViewDto;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetPLInfoLogic;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetSibuCategoryLogic;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetUserOnerLogic;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MstMiseInfo;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * P/L照会画面アクション
 * @author xnkusama
 * @modifier xyamauchi 2015/3/9 変更管理CGOC09001対応（欠陥修正）
 */
public class PlInfoViewActionImpl implements PlInfoViewAction, CommonAction  {

    /* アクションID */
    public static final String initialize_ACTION_ID = "BBS006A01";
    public static final String search_ACTION_ID     = "BBS006A02";
    public static final String callMiseForm_ACTION_ID = "BBS006A03";
    public static final String callOnerForm_ACTION_ID = "BBS006A04";
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BBS006V01";
    private static final String VIEWID_RESULT       = "BBS006V02";
    private static final String VIEWID_GENKAINFO    = "BBS006V03";
    private static final String VIEWID_RESULT_RC    = "BBS006V04";
    private static final String VIEWID_MISESEARCH = "BCO008V01";//店選択
    private static final String VIEWID_ONERSEARCH = "BCO006V01";//オーナー選択検索
    /* ユーザータイプ */
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_ONER  = "02";
    private static final String USER_TYPE_MISE  = "03";
    /* LOGIC */
    private GetSibuCategoryLogic getSibuCategoryLogic;
    private GetUserOnerLogic getUserOnerLogic;
    private GetPLInfoLogic getPlInfoLogic;
    private GetMiseInfoLogic getMiseInfoLogic;
    /* DTO */
    private PlInfoViewDto plInfoViewDto;
    private PlInfoViewDto plInfoViewCommonDto;
    private MiseSearchDto miseSearchDto;
    private OwnerSearchDto ownerSearchDto;
    
    /* プルダウン情報Mapキー */
    private static final String PULLDOWN_MAPKEY_SIBU    = "sibu";
    private static final String PULLDOWN_MAPKEY_JIGYOU  = "jigyou";
    private static final String PULLDOWN_MAPKEY_SLAREA  = "slarea";
    private static final String PULLDOWN_MAPKEY_BLOCK   = "block";
    
    /* 条件画面 対象年月表示月数 */
    private static final int NENGETU_DISPLAY_MONTH = 25;
            
    /**
     * 検索
     * @return
     */
    public String search() {

        // 検索結果をクリア
        getPlInfoViewDto().setEntityTougetu(null);
        getPlInfoViewDto().setEntityZennen(null);
        getPlInfoViewDto().setListEntityTogetuPlusZennen(null);
        
        String sysDt = getBirdDateInfo().getSysDate();
        String appDt = getBirdDateInfo().getAppDate();
        
        // 共通情報コピー
        getPlInfoViewDto().setUserTypeCd(getPlInfoViewCommonDto().getUserTypeCd());
        getPlInfoViewDto().setLimitKbn(getPlInfoViewCommonDto().isLimitKbn());

        // 検索条件を保存
        getPlInfoViewCommonDto().setCondTargetYM(getPlInfoViewDto().getCondTargetYM());
        getPlInfoViewCommonDto().setCondTaishoTenpo(getPlInfoViewDto().getCondTaishoTenpo());
        getPlInfoViewCommonDto().setCondTaishoTenpoOner(getPlInfoViewDto().getCondTaishoTenpoOner());
        getPlInfoViewCommonDto().setCondCompanyCd(getPlInfoViewDto().getCondCompanyCd());
        getPlInfoViewCommonDto().setCondJigyouCd(getPlInfoViewDto().getCondJigyouCd());
        getPlInfoViewCommonDto().setCondSlareaCd(getPlInfoViewDto().getCondSlareaCd());
        getPlInfoViewCommonDto().setCondSibuCd(getPlInfoViewDto().getCondSibuCd());
        getPlInfoViewCommonDto().setCondBlockCd(getPlInfoViewDto().getCondBlockCd());
        getPlInfoViewCommonDto().setCondMiseCd(getPlInfoViewDto().getCondMiseCd());
        getPlInfoViewCommonDto().setCondMiseCdOner(getPlInfoViewDto().getCondMiseCdOner());
        getPlInfoViewCommonDto().setCondMiseName(getPlInfoViewDto().getCondMiseName());
        getPlInfoViewCommonDto().setCondOnerCd(getPlInfoViewDto().getCondOnerCd());
        getPlInfoViewCommonDto().setCondOnerName(getPlInfoViewDto().getCondOnerName());
        getPlInfoViewCommonDto().setCondTenpoShu(getPlInfoViewDto().getCondTenpoShu());
        getPlInfoViewCommonDto().setCondSearchType(getPlInfoViewDto().getCondSearchType());
        getPlInfoViewCommonDto().setCsvMode(getPlInfoViewDto().getCsvMode());
        
//      add start xkhata 2007-02-07
        getPlInfoViewDto().setUriageFlg(getPlInfoViewCommonDto().getUriageFlg());
        
// add end

        //---2006/05/22 add 最終検索時の対象店舗を保持（店、オーナー検索時の対処）
        getPlInfoViewDto().setCondLastSearchTaishoTenpo(getPlInfoViewDto().getCondTaishoTenpo());
        // 検索ロジック実行
        getGetPlInfoLogic().execute(getPlInfoViewDto(), sysDt, appDt, getBirdUserInfo().getUserID());
        
        // 検索ロジックにて設定されるFC/RCモードを保存
        getPlInfoViewCommonDto().setResultFcRC(getPlInfoViewDto().isResultFcRc());

        if (getPlInfoViewDto().isResultFcRc()) {
            return VIEWID_RESULT_RC;
        }
        else {
            return VIEWID_RESULT;
        }
    }

    /**
     * 初期アクション
     * @return
     */
    public String initialize() {
        // ユーザータイプの設定
        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
        getPlInfoViewCommonDto().setUserTypeCd(userTypeCd);
        // 制限区分設定
        getPlInfoViewCommonDto().setLimitKbn(getBirdUserInfo().isLimit());
        // メニューから遷移された場合
        if (getPullDownMenuDto() != null && getPullDownMenuDto().isClearFlg()) {
            // 検索条件項目の初期化
            getPlInfoViewDto().setCondTargetYM("");
            getPlInfoViewDto().setCondTaishoTenpo("");
            getPlInfoViewDto().setCondTaishoTenpoOner("");
            getPlInfoViewDto().setCondTenpoShu("");
            getPlInfoViewDto().setCondMiseCd("");
            getPlInfoViewDto().setCondOnerCd("");
            getPlInfoViewDto().setCondMiseCdOner("");
            // アプリ日付、システム日付、ログインユーザーIDセット
            getPlInfoViewCommonDto().setAppDt(getBirdDateInfo().getAppDate());
            getPlInfoViewCommonDto().setSysDt(getBirdDateInfo().getSysDate());
            getPlInfoViewCommonDto().setUserId(getBirdUserInfo().getUserID());
            // 検索結果項目の初期化
            //TODO 初期化処理を追加すること

            // ウィンドウID初期値設定
            getPlInfoViewCommonDto().updateWindowid();
            
// add start xkhata 2007/02/13
            getPlInfoViewCommonDto().setUriageFlg(true);
// add end

            if (getPlInfoViewCommonDto().isLimitKbn() && userTypeCd.equals(USER_TYPE_HONBU)) {
                getPlInfoViewDto().setCondTaishoTenpo("3");
            }
            getPullDownMenuDto().setClearFlg(false);
        }

        // BIRD内画面から遷移された場合の処理
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            String companyCd = getCommonCodeDto().getCompanyCd();
            String miseCd = getCommonCodeDto().getMiseCd();
            if (!isNull(companyCd) && !isNull(miseCd)) {
                getPlInfoViewDto().setCondMiseCd(miseCd);
                getPlInfoViewDto().setCondCompanyCd(companyCd);
                // add Start 2015/3/3
                // アプリ日付、システム日付、ログインユーザーIDセット
                getPlInfoViewCommonDto().setAppDt(getBirdDateInfo().getAppDate());
                getPlInfoViewCommonDto().setSysDt(getBirdDateInfo().getSysDate());
                getPlInfoViewCommonDto().setUserId(getBirdUserInfo().getUserID());     
                // add End
                DateFormatter formatter = new DateFormatter();
                String sysDate = getBirdDateInfo().getSysDate();
                String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
                try {
                    getPlInfoViewDto().setCondTargetYM(DateManager.getPrevMonth(sysMonth, 1));
                }
                catch (Exception ex) {
                    throw new FtlSystemException("デフォルト年月設定");
                }
                // ウィンドウID初期値設定
                getPlInfoViewCommonDto().updateWindowid();
                
                if (userTypeCd.equals(USER_TYPE_HONBU)) {
                    getPlInfoViewDto().setCondTaishoTenpo("6");
                }
                else if (userTypeCd.equals(USER_TYPE_ONER)) {
                    getPlInfoViewDto().setCondTaishoTenpoOner("4");
                }
                getPullDownMenuDto().setClearFlg(false);
            }
        }

        // 検索条件画面のリスト作成
        getPlInfoViewCommonDto().setCondListTargetYM(makeCondListTargetYM());
        if (USER_TYPE_HONBU.equals(userTypeCd)) {
            // プルダウン作成
            Map mapPulldownInfo = getGetSibuCategoryLogic()
                                    .execute(getPlInfoViewDto().getCondCompanyCd(),
                                             getBirdUserInfo().getUserID(),
                                             getBirdUserInfo().isLimit());
            
            getPlInfoViewCommonDto().setCondListSibuCd((List) mapPulldownInfo.get(PULLDOWN_MAPKEY_SIBU));
            getPlInfoViewCommonDto().setCondListJigyouCd((List) mapPulldownInfo.get(PULLDOWN_MAPKEY_JIGYOU));
            getPlInfoViewCommonDto().setCondListSlareaCd((List) mapPulldownInfo.get(PULLDOWN_MAPKEY_SLAREA));
            getPlInfoViewCommonDto().setCondListBlockCd((List) mapPulldownInfo.get(PULLDOWN_MAPKEY_BLOCK));
            
// add start xkhata 2007-02-18
            getPlInfoViewCommonDto().setFcRcFlg(true);
// add end
        }
        else if (USER_TYPE_ONER.equals(userTypeCd)) {
            // 店舗プルダウン作成
            List listMise = getGetUserOnerLogic()
                                .execute(getPlInfoViewDto().getCondCompanyCd(), 
                                         getBirdUserInfo().getUserID(), 
                                         getBirdDateInfo().getSysDate());
            getPlInfoViewCommonDto().setCondListOnerTenpo(listMise);
            UIUserOner uiUserOner = (UIUserOner) getBirdUserInfo().getUserOner().get(0);
            getPlInfoViewDto().setCondOnerCd(uiUserOner.getOnerCd());
// add start xkhata 2007-02-18
            getPlInfoViewCommonDto().setFcRcFlg(true);
// add end

        }
        else if (USER_TYPE_MISE.equals(userTypeCd)) {
            UIUserMise uiUserMise = (UIUserMise) getBirdUserInfo().getUserMise().get(0);
            getPlInfoViewDto().setCondCompanyCd(uiUserMise.getCompanyCd());
            getPlInfoViewDto().setCondMiseCd(uiUserMise.getMiseCd());
            
// add start xkhata 2007-02-18
            MstMiseInfo miseInfo = getMiseInfoLogic.execute(uiUserMise.getMiseCd(),uiUserMise.getCompanyCd());
            // 店区分が「_1_」の場合はFC
            if ( "1".equals(miseInfo.getMiseKbn().substring(1, 2))) {
                getPlInfoViewCommonDto().setFcRcFlg(true);
            } else {
                getPlInfoViewCommonDto().setFcRcFlg(false);
            }
// add end
        }

        if (getPlInfoViewCommonDto().isCallFormFlag()) {
            // 検索条件復帰
            loadSearchCondition();
            getPlInfoViewCommonDto().setCallFormFlag(false);
        }
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            getPlInfoViewDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
        // オーナ選択結果取得
        if (getOwnerSearchDto().isActionFlag()) {
            getPlInfoViewDto().setCondOnerCd(getOwnerSearchDto().getOnerCd());
            ownerSearchDto.setActionFlag(false);
        }

        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            return search();
        }
        return null;
    }

    /**
     * 店検索フォーム
     * @return
     */
    public String callMiseForm() {
        getMiseSearchDto().setNavigationCase(VIEWID_CONDITION);
        getMiseSearchDto().setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getBirdUserInfo().getMstUser().getRCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        // 検索条件退避
        saveSearchCondition();
        // 共通フォーム呼出フラグON
        getPlInfoViewCommonDto().setCallFormFlag(true);
        return VIEWID_MISESEARCH;
    }

    /**
     * オーナー検索フォーム
     * @return
     */
    public String callOnerForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(VIEWID_CONDITION);
        getOwnerSearchDto().setInitFlag(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getBirdUserInfo().getMstUser().getRCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        // 検索条件退避
        saveSearchCondition();
        // 共通フォーム呼出フラグON
        getPlInfoViewCommonDto().setCallFormFlag(true);
        return VIEWID_ONERSEARCH;
    }
    
    private List makeCondListTargetYM() {
        // 現在日付取得
        DateFormatter formatter = new DateFormatter();
        String sysDate = getBirdDateInfo().getSysDate();
        String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);
        
        // 現在月から過去？ヶ月分の年月リスト作成(2006/04/03 何カ月分必要か未定)
        List listMonth = new ArrayList();
        for (int i = 1; i <= NENGETU_DISPLAY_MONTH; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(sysMonth, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("条件画面初期処理");
            }
                                     
            SelectItem item = new SelectItem(
                                        month, 
                                        formatter.format(month, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM)); 
            listMonth.add(item);
        }
        return listMonth;
    }

    private void saveSearchCondition() {
        // 検索条件退避
        getPlInfoViewCommonDto().setTempTargetYM(getPlInfoViewDto().getCondTargetYM());
        getPlInfoViewCommonDto().setTempTaishoTenpo(getPlInfoViewDto().getCondTaishoTenpo());
        getPlInfoViewCommonDto().setTempTaishoTenpoOner(getPlInfoViewDto().getCondTaishoTenpoOner());
        getPlInfoViewCommonDto().setTempJigyouCd(getPlInfoViewDto().getCondJigyouCd());
        getPlInfoViewCommonDto().setTempSlareaCd(getPlInfoViewDto().getCondSlareaCd());
        getPlInfoViewCommonDto().setTempSibuCd(getPlInfoViewDto().getCondSibuCd());
        getPlInfoViewCommonDto().setTempBlockCd(getPlInfoViewDto().getCondBlockCd());
        getPlInfoViewCommonDto().setTempMiseCd(getPlInfoViewDto().getCondMiseCd());
        getPlInfoViewCommonDto().setTempMiseCdOner(getPlInfoViewDto().getCondMiseCdOner());
        getPlInfoViewCommonDto().setTempOnerCd(getPlInfoViewDto().getCondOnerCd());
        getPlInfoViewCommonDto().setTempTenpoShu(getPlInfoViewDto().getCondTenpoShu());
        getPlInfoViewCommonDto().setTempSearchType(getPlInfoViewDto().getCondSearchType());
    }

    private void loadSearchCondition() {
        // 退避検索条件コピー
        getPlInfoViewDto().setCondTargetYM(getPlInfoViewCommonDto().getTempTargetYM());
        getPlInfoViewDto().setCondTaishoTenpo(getPlInfoViewCommonDto().getTempTaishoTenpo());
        getPlInfoViewDto().setCondTaishoTenpoOner(getPlInfoViewCommonDto().getTempTaishoTenpoOner());
        getPlInfoViewDto().setCondJigyouCd(getPlInfoViewCommonDto().getTempJigyouCd());
        getPlInfoViewDto().setCondSlareaCd(getPlInfoViewCommonDto().getTempSlareaCd());
        getPlInfoViewDto().setCondSibuCd(getPlInfoViewCommonDto().getTempSibuCd());
        getPlInfoViewDto().setCondBlockCd(getPlInfoViewCommonDto().getTempBlockCd());
        getPlInfoViewDto().setCondMiseCd(getPlInfoViewCommonDto().getTempMiseCd());
        getPlInfoViewDto().setCondMiseCdOner(getPlInfoViewCommonDto().getTempMiseCdOner());
        getPlInfoViewDto().setCondOnerCd(getPlInfoViewCommonDto().getTempOnerCd());
        getPlInfoViewDto().setCondTenpoShu(getPlInfoViewCommonDto().getTempTenpoShu());
        getPlInfoViewDto().setCondSearchType(getPlInfoViewCommonDto().getTempSearchType());

        // 退避領域クリア
        getPlInfoViewCommonDto().setTempTargetYM(null);
        getPlInfoViewCommonDto().setTempTaishoTenpo(null);
        getPlInfoViewCommonDto().setTempTaishoTenpoOner(null);
        getPlInfoViewCommonDto().setTempJigyouCd(null);
        getPlInfoViewCommonDto().setTempSlareaCd(null);
        getPlInfoViewCommonDto().setTempSibuCd(null);
        getPlInfoViewCommonDto().setTempBlockCd(null);
        getPlInfoViewCommonDto().setTempMiseCd(null);
        getPlInfoViewCommonDto().setTempMiseCdOner(null);
        getPlInfoViewCommonDto().setTempOnerCd(null);
        getPlInfoViewCommonDto().setTempTenpoShu(null);
        getPlInfoViewCommonDto().setTempSearchType(null);
    }

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
	private PullDownMenuDto getPullDownMenuDto() {
		return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
	}
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
	public PlInfoViewDto getPlInfoViewDto() {
		return plInfoViewDto;
	}
	public void setPlInfoViewDto(PlInfoViewDto plInfoViewDto) {
		this.plInfoViewDto = plInfoViewDto;
	}
    public PlInfoViewDto getPlInfoViewCommonDto() {
        return plInfoViewCommonDto;
    }
    public void setPlInfoViewCommonDto(
            PlInfoViewDto plInfoViewCommonDto) {
        this.plInfoViewCommonDto = plInfoViewCommonDto;
    }
	public GetSibuCategoryLogic getGetSibuCategoryLogic() {
		return getSibuCategoryLogic;
	}
	public void setGetSibuCategoryLogic(
			GetSibuCategoryLogic getSibuCategoryLogic) {
		this.getSibuCategoryLogic = getSibuCategoryLogic;
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
	public GetUserOnerLogic getGetUserOnerLogic() {
		return getUserOnerLogic;
	}
	public void setGetUserOnerLogic(GetUserOnerLogic getUserOnerLogic) {
		this.getUserOnerLogic = getUserOnerLogic;
	}
	public GetPLInfoLogic getGetPlInfoLogic() {
		return getPlInfoLogic;
	}
	public void setGetPlInfoLogic(GetPLInfoLogic getPlInfoLogic) {
		this.getPlInfoLogic = getPlInfoLogic;
	}
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) getS2Container().getComponent(CommonCodeDto.class);
    }
    
//  add start xkhata 2007-02-18
    public GetMiseInfoLogic getGetMiseInfoLogic() {
        return this.getMiseInfoLogic;
    }
    public void setGetMiseInfoLogic( GetMiseInfoLogic getMiseInfoLogic) {
        this.getMiseInfoLogic = getMiseInfoLogic;
    }
// add end
    
}