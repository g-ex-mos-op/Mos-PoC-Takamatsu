package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.entity.MstUserCompany;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.common.logic.GetUserCompanyLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.action.ProceedsManageGepoAction;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoResultDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MstMiseInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UserCompanyInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.NebikiLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.PointLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.ProceedsConditionLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.ProceedsManageGepoLogic;

/**
 * 売上金管理月報アクション
 *
 * @author xjung
 */
public class ProceedsManageGepoActionImpl	implements ProceedsManageGepoAction {

    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BSM013A01";

    /** 対象店舗プルダウン取得アクションID */
    public static final String searchMiseList_ACTION_ID = "BSM013A02";

    /** 検索アクションID */
    public static final String search_ACTION_ID = "BSM013A03";

    /** タブ変更アクションID */
    public static final String changeTab_ACTION_ID = "BSM013A04";

    /** 店舗検索アクションID */
    public static final String searchMiseCd_ACTION_ID = "BSM013A05";

    /** 店検索情報DTO */
    private MiseSearchDto miseSearchDto;

    /** 売上金管理月報・条件部DTO */
    private ProceedsManageGepoDto proceedsManageGepoDto;

    /** 売上金管理月報・結果DTO */
    private ProceedsManageGepoResultDto proceedsManageGepoResultDto;

    /** 条件部情報取得ロジック */
    private ProceedsConditionLogic proceedsConditionLogic;

    /** 対象店舗情報取得ロジック */
    private MstMiseInfoLogic mstMiseInfoLogic;

    /** 売上金管理月報取得ロジック */
    private ProceedsManageGepoLogic proceedsManageGepoLogic;

    /** 値引取得ロジック */
    private NebikiLogic nebikiLogic;

    /** ユーザー所属会社取得ロジック */
    private GetUserCompanyLogic getUserCompanyLogic;

    /** 店舗情報取得ロジック */
    private GetMiseLogic getMiseLogic;

// add 2020/05/08 USI張  begin
    /** dポイント、株主優待モスポイント情報取得ロジック */
    private PointLogic pointLogic;
// add 2020/05/08 USI張  end
    /**
	 * 初期処理
	 */
	public String initialize() {
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // メニューDTO取得
        PullDownMenuDto pullDownMenuDto =
            (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);

        // メニューから遷移された場合
        if (pullDownMenuDto.isClearFlg()) {
            // クリアフラグ設定
            pullDownMenuDto.setClearFlg(false);

            // ユーザー関連情報
            BirdUserInfo birdUserInfo =
                (BirdUserInfo) container.getComponent(BirdUserInfo.class);
            // 日付関連情報
            BirdDateInfo birdDateInfo =
                (BirdDateInfo) container.getComponent(BirdDateInfo.class);

            // ウィンドウID生成
            getProceedsManageGepoDto().updateWindowId();
            // ユーザタイプ設定
            getProceedsManageGepoDto().setUserType(birdUserInfo.getMstUser().getUserTypeCd());
            // ユーザID
            getProceedsManageGepoDto().setUserId(birdUserInfo.getMstUser().getUser_id());
            // 制限区分
            getProceedsManageGepoDto().setLimitKbn(birdUserInfo.isLimit());
            // タブ区分設定
            getProceedsManageGepoDto().setTabKbn(ProceedsConstants.TAB_0);
            // サブタブ区分設定
            getProceedsManageGepoDto().setSubTabKbn(ProceedsConstants.SUB_TAB_0);
            // 検索ボタン名設定
            getProceedsManageGepoDto().setSchBtName(ProceedsConstants.BT_EXECUTE);
            // 検索ﾌﾗｸﾞ設定
            getProceedsManageGepoDto().setSearchFlg(false);

            // システム日付設定
            getProceedsManageGepoDto().setSysDate(birdDateInfo.getSysDate());

            // 条件部情報取得
            Map map = getProceedsConditionLogic().execute(
                        birdUserInfo.getMstUser().getUser_id(),
                        birdDateInfo.getAppDate());
            // 会社
            List companyList = (List) map.get(ProceedsConstants.MAP_COMPANY_LIST);
            // 会社コード設定
            UserCompanyInfo info = (UserCompanyInfo) companyList.get(0);
            getProceedsManageGepoDto().setCompanyCd(info.getCompanyCd());
            // 会社プルダウン設定
            getProceedsManageGepoDto().setCompanyList(companyList);

            // 対象年月プルダウン
            getProceedsManageGepoDto().setTaishoYMList(
                (List) map.get(ProceedsConstants.MAP_TAISHO_YM_LIST));

            // 集計区分情報設定
            getProceedsManageGepoDto().setSyukeiKbnList((List) map.get(ProceedsConstants.MAP_SK_LIST));

            // オーナーユーザの場合
            if (ProceedsConstants.ONER.equals(birdUserInfo.getMstUser().getUserTypeCd())) {
                //オーナーコード取得
                List ownerList = birdUserInfo.getUserOner();
                for (Iterator it = ownerList.iterator(); it.hasNext();) {
                    UIUserOner uIUserOner = (UIUserOner) it.next();
                    if (getProceedsManageGepoDto().getCompanyCd().equals(uIUserOner.getCompanyCd())) {
                        getProceedsManageGepoDto().setOnerCd(uIUserOner.getOnerCd());
                        break;
                    }
                }

                // 対象店舗プルダウンを取得する
                getProceedsManageGepoDto().setTaishoTenpoList(
                    getMstMiseInfoLogic().execute(
                        getProceedsManageGepoDto().getCompanyCd(),
                        getProceedsManageGepoDto().getOnerCd(),
                        birdDateInfo.getAppDate()));
                // 対象店舗プルダウンの先頭行へ『全店』を追加する。
                MstMiseInfo entity = new MstMiseInfo();
                entity.setMiseCd(ProceedsConstants.ZENTEN_CD);
                entity.setMiseNameKj(ProceedsConstants.ZENTEN_NAME);
                getProceedsManageGepoDto().getTaishoTenpoList().add(0, entity);
            }

            /**
             * 店舗ユーザーログイン時の処理を追加
             */
            if (ProceedsConstants.MISE.equals(birdUserInfo.getMstUser().getUserTypeCd())){

                List miseList = birdUserInfo.getUserMise();
                UIUserMise uIUserMise = (UIUserMise)miseList.get(0);
                String companyCd = uIUserMise.getCompanyCd();
                String miseCd = uIUserMise.getMiseCd();

                //会社名を取得
                String companyName = "";
                String userId = getProceedsManageGepoDto().getUserId();
                List list = getUserCompanyLogic.execute(userId, null);
                if(list != null && list.size() > 0){
                    for(int i=0; i < list.size(); i++){
                        MstUserCompany entity = (MstUserCompany)list.get(i);
                        if(companyCd.equals(entity.getCompanyCd())){
                            companyName = entity.getCompanyName();
                            break;
                        }
                    }
                }

                //店舗名を取得
                String miseName ="";
                MstMise mise = getMiseLogic.execute(companyCd, miseCd);
                String miseNameKj = mise.getMiseNameKj().trim();

                //店舗クローズ日がアプリ日付以前か判断、クローズしていれば「CLOSE」を設定。
                if (mise.getCloseDt().compareTo(birdDateInfo.getAppDate()) < 0) {
                    miseNameKj = miseNameKj + " (CLOSE)";
                }

                getProceedsManageGepoDto().setTaishoTenpoCd(miseCd);
                getProceedsManageGepoDto().setTaishoTenpo(miseNameKj);

            }

        // 店検索画面からの戻り(店決定・戻るボタン)
        } else if (getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT
            || getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_CANCEL) {
            // 遷移区分設定
            getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
            // 店が選択された場合
            if (getMiseSearchDto().isActionFlg()) {
                // アクションフラグ設定
                getMiseSearchDto().setActionFlg(false);
                // 取得した「店コード」をDTOにセット
                getProceedsManageGepoDto().setTaishoTenpoCd(getMiseSearchDto().getMiseCd());
            }
            //ウィンドウID
            getProceedsManageGepoDto().setWindowId(Integer.parseInt(getMiseSearchDto().getReturnWindowId()));

            // 売上金管理月報情報取得
            this.searchProceedsManageGepo(
                getProceedsManageGepoDto().getOldCompanyCd(),
                getProceedsManageGepoDto().getOldTaishoTenpoCd(),
                getProceedsManageGepoDto().getOldTaishoYM());
        }

		// 自画面へ遷移
		return null;
	}

	/**
	 * 対象店舗プルダウン取得
	 */
	public String searchMiseList() {
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // 日付関連情報
        BirdDateInfo birdDateInfo =
            (BirdDateInfo) container.getComponent(BirdDateInfo.class);

        // 対象店舗プルダウンクリア
        getProceedsManageGepoDto().setTaishoTenpoList(null);

        try {
            // 売上金管理月報情報取得
            this.searchProceedsManageGepo(
                getProceedsManageGepoDto().getOldCompanyCd(),
                getProceedsManageGepoDto().getOldTaishoTenpoCd(),
                getProceedsManageGepoDto().getOldTaishoYM());

        } finally {
            // 対象店舗プルダウンを取得する
            getProceedsManageGepoDto().setTaishoTenpoList(
                getMstMiseInfoLogic().execute(
                    getProceedsManageGepoDto().getCompanyCd(),
                    getProceedsManageGepoDto().getOnerCd(),
                    birdDateInfo.getAppDate()));
            // 対象店舗プルダウンの先頭行へ『全店』を追加する。
            MstMiseInfo entity = new MstMiseInfo();
            entity.setMiseCd(ProceedsConstants.ZENTEN_CD);
            entity.setMiseNameKj(ProceedsConstants.ZENTEN_NAME);
            getProceedsManageGepoDto().getTaishoTenpoList().add(0, entity);
        }

        // 自画面へ遷移
        return null;
	}

    /**
     * 検索
     * @return String 遷移先ビューID
     */
    public String search() {
        // 既存検索条件クリア
        getProceedsManageGepoDto().clearOldCondition();
        // 検索ﾌﾗｸﾞ設定
        getProceedsManageGepoDto().setSearchFlg(true);
        // 検索条件保持
        getProceedsManageGepoDto().setOldCompanyCd(getProceedsManageGepoDto().getCompanyCd());
        getProceedsManageGepoDto().setOldTaishoTenpoCd(getProceedsManageGepoDto().getTaishoTenpoCd());
        getProceedsManageGepoDto().setOldTaishoYM(getProceedsManageGepoDto().getTaishoYM());

        // 売上金管理月報情報取得
        this.searchProceedsManageGepo(
            getProceedsManageGepoDto().getCompanyCd(),
            getProceedsManageGepoDto().getTaishoTenpoCd(),
            getProceedsManageGepoDto().getTaishoYM());

        // タブ区分設定
        getProceedsManageGepoDto().setTabKbn(ProceedsConstants.TAB_0);
        // サブタブ区分設定
        getProceedsManageGepoDto().setSubTabKbn(ProceedsConstants.SUB_TAB_0);
        // 検索ボタン名設定
        getProceedsManageGepoDto().setSchBtName(ProceedsConstants.BT_RESEARCH);

        // 自画面へ遷移
        return null;
    }

    /**
	 * タブ変更
	 */
	public String changeTab() {
        // サブタブ区分設定
        if (ProceedsConstants.TAB_0.equals(getProceedsManageGepoDto().getTabKbn())) {
            getProceedsManageGepoDto().setSubTabKbn(ProceedsConstants.SUB_TAB_0);
        } else if (ProceedsConstants.TAB_1.equals(getProceedsManageGepoDto().getTabKbn())) {
            getProceedsManageGepoDto().setSubTabKbn(ProceedsConstants.SUB_TAB_2);
        } else if (ProceedsConstants.TAB_2.equals(getProceedsManageGepoDto().getTabKbn())) {
            getProceedsManageGepoDto().setSubTabKbn(ProceedsConstants.SUB_TAB_4);
        } else if (ProceedsConstants.TAB_3.equals(getProceedsManageGepoDto().getTabKbn())) {
            getProceedsManageGepoDto().setSubTabKbn(ProceedsConstants.SUB_TAB_7);
        }
// add 2020/05/08 USI張  begin
        else if (ProceedsConstants.TAB_4.equals(getProceedsManageGepoDto().getTabKbn())) {
            getProceedsManageGepoDto().setSubTabKbn(ProceedsConstants.SUB_TAB_10);
        }
// add 2020/05/08 USI張  end


        // 売上金管理月報情報取得
        this.searchProceedsManageGepo(
            getProceedsManageGepoDto().getOldCompanyCd(),
            getProceedsManageGepoDto().getOldTaishoTenpoCd(),
            getProceedsManageGepoDto().getOldTaishoYM());

        // 自画面へ遷移
        return null;
	}

	/**
	 * 店舗検索
	 */
	public String searchMiseCd() {
        // 遷移区分要否フラグ設定
        getMiseSearchDto().setNeedReturnKind(true);
        //遷移元情報を設定
        getMiseSearchDto().setNavigationCase(ProceedsConstants.VIEW_ID);
        //初期化
        getMiseSearchDto().setInitialFlag(true);

        //遷移元ウィンドウIDを設定
        getMiseSearchDto().setReturnWindowId(
            Integer.toString(getProceedsManageGepoDto().getWindowId()));

        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getProceedsManageGepoDto().getCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);

        //店検索画面へ遷移
        return ProceedsConstants.VIEW_ID_MISE_SCH;
	}


    /**
     * 売上金管理月報情報を取得する
     * @param companyCd 会社コード
     * @param miseCd    店コード
     * @param taishoYM  対象年月
     */
    private void searchProceedsManageGepo(
        String companyCd,
        String miseCd,
        String taishoYM) {
        // 検索ﾌﾗｸﾞがfalseの場合、検索処理終了
        if(!getProceedsManageGepoDto().getSearchFlg()) {
            return;
        }

        // 検索ﾌﾗｸﾞ設定
        getProceedsManageGepoDto().setSearchFlg(false);
        // 対象店舗情報
        if(ProceedsConstants.ZENTEN_CD.equals(miseCd)) {
            getProceedsManageGepoResultDto().setTaishoTenpoInfo(ProceedsConstants.ZENTEN_NAME);
        }
        else {
	        getProceedsManageGepoResultDto().setTaishoTenpoInfo(
	            getMstMiseInfoLogic().getMiseCdName(companyCd, miseCd));
        }

        // 売上金管理月報情報取得
        Map result = getProceedsManageGepoLogic().execute(
                        getProceedsManageGepoDto().getUserType(),
                        getProceedsManageGepoDto().getUserId(),
                        getProceedsManageGepoDto().isLimitKbn(),
                        getProceedsManageGepoDto().getOnerCd(),
                        companyCd,
                        miseCd,
                        taishoYM);



        // 売上金管理月報情報リスト設定
        getProceedsManageGepoResultDto().setResultList((List) result.get(ProceedsConstants.MAP_RST_LIST));

        // 値引情報取得
        Map nebikiResult = this.getNebikiLogic().execute(
        		getProceedsManageGepoDto().getUserType(),
                getProceedsManageGepoDto().getUserId(),
                getProceedsManageGepoDto().isLimitKbn(),
                getProceedsManageGepoDto().getOnerCd(),
                companyCd,
                miseCd,
                taishoYM, getProceedsManageGepoResultDto().getResultList());

        //値引情報リスト設定
        getProceedsManageGepoResultDto().setResultNebikiList((List) nebikiResult.get(ProceedsConstants.MAP_NEBIKI_RST_LIST));

        // 明細リンク表示フラグ設定
        getProceedsManageGepoResultDto().setMeisaiDispFlg((String)result.get(ProceedsConstants.MAP_MEISAI_DISP_FLG));

//add 2019/07/15 USI張 #34 begin
        getProceedsManageGepoResultDto().setUriageMeisaiDispFlg((String)result.get(ProceedsConstants.MAP_URIAGE_MEISAI_DISP_FLG));
//add 2019/07/15 USI張 #34 end

//add 2020/05/08 USI張  begin
        //dポイント、株主優待モスポイント情報取得
        List pointResult = this.getPointLogic().execute(
        		getProceedsManageGepoDto().getUserType(),
                getProceedsManageGepoDto().getUserId(),
                getProceedsManageGepoDto().isLimitKbn(),
                getProceedsManageGepoDto().getOnerCd(),
                companyCd,
                miseCd,
                taishoYM, getProceedsManageGepoResultDto().getResultList());

        getProceedsManageGepoResultDto().setResutPointList((List)pointResult);
//add 2020/05/08 USI張  end
        List syukeiKbnList = getProceedsManageGepoDto().getSyukeiKbnList();
        // 集計区分名称設定
        if (syukeiKbnList != null && syukeiKbnList.size() == 10) {
            getProceedsManageGepoResultDto().setKaikeiKbnName2(((CtlSyukeiKbn)syukeiKbnList.get(0)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName3(((CtlSyukeiKbn)syukeiKbnList.get(1)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName4(((CtlSyukeiKbn)syukeiKbnList.get(2)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName5(((CtlSyukeiKbn)syukeiKbnList.get(3)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName6(((CtlSyukeiKbn)syukeiKbnList.get(4)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName7(((CtlSyukeiKbn)syukeiKbnList.get(5)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName8(((CtlSyukeiKbn)syukeiKbnList.get(6)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName9(((CtlSyukeiKbn)syukeiKbnList.get(7)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName10(((CtlSyukeiKbn)syukeiKbnList.get(8)).getSyukeiName());
            getProceedsManageGepoResultDto().setKaikeiKbnName11(((CtlSyukeiKbn)syukeiKbnList.get(9)).getSyukeiName());
        }
        // チケット販売名称設定
        List tckNameList = (List) result.get(ProceedsConstants.MAP_TCK_NAME_LIST);
        if (tckNameList != null && tckNameList.size() == 15) {
            getProceedsManageGepoResultDto().setTcktName1((String) tckNameList.get(0));
            getProceedsManageGepoResultDto().setTcktName2((String) tckNameList.get(1));
            getProceedsManageGepoResultDto().setTcktName3((String) tckNameList.get(2));
            getProceedsManageGepoResultDto().setTcktName4((String) tckNameList.get(3));
            getProceedsManageGepoResultDto().setTcktName5((String) tckNameList.get(4));
            getProceedsManageGepoResultDto().setTcktName6((String) tckNameList.get(5));
            getProceedsManageGepoResultDto().setTcktName7((String) tckNameList.get(6));
            getProceedsManageGepoResultDto().setTcktName8((String) tckNameList.get(7));
            getProceedsManageGepoResultDto().setTcktName9((String) tckNameList.get(8));
            getProceedsManageGepoResultDto().setTcktName10((String) tckNameList.get(9));
            getProceedsManageGepoResultDto().setTcktName11((String) tckNameList.get(10));
            getProceedsManageGepoResultDto().setTcktName12((String) tckNameList.get(11));
            getProceedsManageGepoResultDto().setTcktName13((String) tckNameList.get(12));
            getProceedsManageGepoResultDto().setTcktName14((String) tckNameList.get(13));
            getProceedsManageGepoResultDto().setTcktName15((String) tckNameList.get(14));
        }
        //　値引区分名称設定
        List nkNameList = (List) nebikiResult.get(ProceedsConstants.MAP_NK_NAME_LIST);
        if (nkNameList != null && nkNameList.size() == 9) {
            getProceedsManageGepoResultDto().setNebikiName1((String) nkNameList.get(0));
            getProceedsManageGepoResultDto().setNebikiName2((String) nkNameList.get(1));
            getProceedsManageGepoResultDto().setNebikiName3((String) nkNameList.get(2));
            getProceedsManageGepoResultDto().setNebikiName4((String) nkNameList.get(3));
            getProceedsManageGepoResultDto().setNebikiName5((String) nkNameList.get(4));
            getProceedsManageGepoResultDto().setNebikiName6((String) nkNameList.get(5));
            getProceedsManageGepoResultDto().setNebikiName7((String) nkNameList.get(6));
            getProceedsManageGepoResultDto().setNebikiName8((String) nkNameList.get(7));
            getProceedsManageGepoResultDto().setNebikiName9((String) nkNameList.get(8));

        }

        // 検索ﾌﾗｸﾞ設定
        getProceedsManageGepoDto().setSearchFlg(true);
    }

    /**
     * 店検索情報DTOを取得する
     * @return 店検索情報DTO
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }

    /**
     * 店検索情報DTOを設定する
     * @param miseSearchDto 店検索情報DTO
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }

    /**
     * 売上金管理月報・条件部DTOを取得する
     * @return 売上金管理月報・条件部DTO
     */
    public ProceedsManageGepoDto getProceedsManageGepoDto() {
        return proceedsManageGepoDto;
    }

    /**
     * 売上金管理月報・条件部DTOを設定する
     * @param proceedsManageGepoDto 売上金管理月報・条件部DTO
     */
    public void setProceedsManageGepoDto(ProceedsManageGepoDto proceedsManageGepoDto) {
        this.proceedsManageGepoDto = proceedsManageGepoDto;
    }

    /**
     * 売上金管理月報・結果DTOを取得する
     * @return 売上金管理月報・結果DTO
     */
    public ProceedsManageGepoResultDto getProceedsManageGepoResultDto() {
        return proceedsManageGepoResultDto;
    }

    /**
     * 売上金管理月報・結果DTOを設定する
     * @param proceedsManageGepoResultDto 売上金管理月報・結果DTO
     */
    public void setProceedsManageGepoResultDto(ProceedsManageGepoResultDto proceedsManageGepoResultDto) {
        this.proceedsManageGepoResultDto = proceedsManageGepoResultDto;
    }

    /**
     * 条件部情報取得ロジックを取得する
     * @return 条件部情報取得ロジック
     */
    public ProceedsConditionLogic getProceedsConditionLogic() {
        return proceedsConditionLogic;
    }

    /**
     * 条件部情報取得ロジックを設定する
     * @param proceedsConditionLogic 条件部情報取得ロジック
     */
    public void setProceedsConditionLogic(ProceedsConditionLogic proceedsConditionLogic) {
        this.proceedsConditionLogic = proceedsConditionLogic;
    }

    /**
     * 対象店舗情報取得ロジックを取得する
     * @return 対象店舗情報取得ロジック
     */
    public MstMiseInfoLogic getMstMiseInfoLogic() {
        return mstMiseInfoLogic;
    }

    /**
     * 対象店舗情報取得ロジックを設定する
     * @param mstMiseInfoLogic 対象店舗情報取得ロジック
     */
    public void setMstMiseInfoLogic(MstMiseInfoLogic mstMiseInfoLogic) {
        this.mstMiseInfoLogic = mstMiseInfoLogic;
    }

    /**
     * 売上金管理月報取得ロジックを取得する
     * @return 売上金管理月報取得ロジック
     */
    public ProceedsManageGepoLogic getProceedsManageGepoLogic() {
        return proceedsManageGepoLogic;
    }

    /**
     * 売上金管理月報取得ロジックを設定する
     * @param proceedsManageGepoLogic 売上金管理月報取得ロジック
     */
    public void setProceedsManageGepoLogic(ProceedsManageGepoLogic proceedsManageGepoLogic) {
        this.proceedsManageGepoLogic = proceedsManageGepoLogic;
    }

    /**
     * 値引取得ロジックを取得する
     * @return nebikiLogic 値引取得ロジック
     */
	public NebikiLogic getNebikiLogic() {
		return nebikiLogic;
	}

	/**
	 * 値引取得ロジックを設定する
	 * @param nebikiLogic 値引取得ロジック
	 */
	public void setNebikiLogic(NebikiLogic nebikiLogic) {
		this.nebikiLogic = nebikiLogic;
	}

    public GetUserCompanyLogic getGetUserCompanyLogic() {
        return getUserCompanyLogic;
    }

    public void setGetUserCompanyLogic(GetUserCompanyLogic getUserCompanyLogic) {
        this.getUserCompanyLogic = getUserCompanyLogic;
    }

    public GetMiseLogic getGetMiseLogic() {
        return getMiseLogic;
    }

    public void setGetMiseLogic(GetMiseLogic getMiseLogic) {
        this.getMiseLogic = getMiseLogic;
    }
// add 2020/05/08 USI張  begin
    /**
     * dポイント、株主優待モスポイント情報取得ロジックを取得する
     * @return pointLogic dポイント、株主優待モスポイント情報取得ロジック
     */
	public PointLogic getPointLogic() {
		return pointLogic;
	}

    /**
     * dポイント、株主優待モスポイント情報取得ロジックを設定する
     * @param pointLogic dポイント、株主優待モスポイント情報取得ロジック
     */
	public void setPointLogic(PointLogic pointLogic) {
		this.pointLogic = pointLogic;
	}
// add 2020/05/08 USI張  end
}