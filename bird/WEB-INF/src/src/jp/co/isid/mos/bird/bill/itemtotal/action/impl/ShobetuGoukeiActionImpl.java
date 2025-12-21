/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.itemtotal.action.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bill.itemtotal.action.ShobetuGoukeiAction;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDto;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiInfoDto;
import jp.co.isid.mos.bird.bill.itemtotal.entity.UIMiseInfo;
import jp.co.isid.mos.bird.bill.itemtotal.entity.UIOnerCd;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetBunruiInfoLogic;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetOnerCdLogic;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetShohinBetuInfoLogic;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetUriageDakaInfoLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * 商品別合計アクション
 *
 * @author xlee
 */
public class ShobetuGoukeiActionImpl implements ShobetuGoukeiAction {

    /** アクションID定義:初期化アクション */
	public static final String initialize_ACTION_ID = "BBS011A01";

    /** アクションID定義:初期化アクション */
	public static final String taishoTenpoExecute_ACTION_ID = "BBS011A02";

    /** アクションID定義: */
    public static final String searchExecute_ACTION_ID = "BBS011A03";

    /** VIEWID定義:明細・履歴画面 */
    private static final String VIEWID_DETAIL = "BBS011V02";

	/** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_CONDITION    = "BBS011V01";

    /** VIEWID定義:オーナー選択画面 */
    private static final String VIEWID_ONERSEARCH = "BCO006V01";

    /** ユーザータイプ：本部 */
    private static final String USER_TYPE_HONBU = "01";

    /** ユーザータイプ：オーナー */
    private static final String USER_TYPE_ONER = "02";

    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;

    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;

    /** オーナー情報 */
    private OwnerSearchDto ownerSearchDto;

	/** 商品別合計DTO */
	private ShobetuGoukeiDto shobetuGoukeiDto;

	/** 商品別情報DTO */
	private ShobetuGoukeiInfoDto shobetuGoukeiInfoDto;

    /** 対象店舗リスト取得ロジック */
    private GetMiseInfoLogic getMiseInfoLogic;

    /** 分類情報取得ロジック */
    private GetBunruiInfoLogic getBunruiInfoLogic;

    /** 商品別情報取得ロジック */
    private GetShohinBetuInfoLogic getShohinBetuInfoLogic;

    /** 売上高情報取得ロジック */
    private GetUriageDakaInfoLogic getUriageDakaInfoLogic;

    /* 全角削除*/
    private static DefaultFormatter trimFormatter= new DefaultFormatter();

    /**
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * 日付情報を取得します。
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * 日付情報を設定します。
     * @param birdDateInfo 日付情報
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
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
     * @param pullDownMenuDto メニューDTO
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * オーナ検索DTOを取得します。
     * @param オーナ検索DTO
     */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}

    /**
     * オーナ検索DTOを設定します。
     * @param ownerSearchDto オーナ検索DTO
     */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}

    /**
     * 商品別合計DTOを取得します。:Request
     * @return 商品別合計DTO
     */
    public ShobetuGoukeiDto getShobetuGoukeiDto() {
        return shobetuGoukeiDto;
    }

    /**
     * 商品別合計DTOを設定します。
     * @param buyingListViewDto　商品別合計情報DTO
     */
    public void setShobetuGoukeiDto(ShobetuGoukeiDto shobetuGoukeiDto) {
        this.shobetuGoukeiDto = shobetuGoukeiDto;
    }

    /**
     * 商品別情報DTOを取得します。:Request
     * @return 商品別情報DTO
     */
    public ShobetuGoukeiInfoDto getShobetuGoukeiInfoDto() {
        return shobetuGoukeiInfoDto;
    }

    /**
     * 商品別情報DTOを設定します。
     * @param shobetuGoukeiInfoDto　商品別情報DTO
     */
    public void setShobetuGoukeiInfoDto(ShobetuGoukeiInfoDto shobetuGoukeiInfoDto) {
        this.shobetuGoukeiInfoDto = shobetuGoukeiInfoDto;
    }

    /**
     * 店情報ロジックを取得します。
     * @return 店情報リスト
     */
    public GetMiseInfoLogic getMiseInfoLogic() {
        return getMiseInfoLogic;
    }

    /**
     *店情報ロジックを取得します。
     * @param getMiseInfoLogic　店情報リスト
     */
    public void setMiseInfoLogic(GetMiseInfoLogic getMiseInfoLogic) {
        this.getMiseInfoLogic = getMiseInfoLogic;
    }

    /**
     *　請求書分類情報を取得します。
     * @return 請求書分類情報
     */
    public GetBunruiInfoLogic getBunruiInfoLogic() {
        return getBunruiInfoLogic;
    }

    /**
     * 請求書分類情報を設定します。
     * @param getBunruiInfoLogic　請求書分類情報
     */
    public void setBunruiInfoLogic(GetBunruiInfoLogic getBunruiInfoLogic) {
        this.getBunruiInfoLogic = getBunruiInfoLogic;
    }

    /**
     *　商品別情報を取得します。
     * @return 商品別情報
     */
    public GetShohinBetuInfoLogic getShohinBetuInfoLogic() {
        return getShohinBetuInfoLogic;
    }

    /**
     * 商品別情報を設定します。
     * @param getShohinBetuInfoLogic　商品別情報
     */
    public void setShohinBetuInfoLogic(GetShohinBetuInfoLogic getShohinBetuInfoLogic) {
        this.getShohinBetuInfoLogic = getShohinBetuInfoLogic;
    }

    /**
     * 売上高情報を取得します。
     * @return 売上高情報
     */
    public GetUriageDakaInfoLogic getUriageDakeInfoLogic() {
        return getUriageDakaInfoLogic;
    }

    /**
     * 売上高情報を設定します。
     * @param getUriageDakeInfoLogic　売上高情報
     */
    public void setUriageDakeInfoLogic(GetUriageDakaInfoLogic getUriageDakeInfoLogic) {
        this.getUriageDakaInfoLogic = getUriageDakeInfoLogic;
    }

    /**
     * 初期処理
     *
     * @return 画面遷移情報
     */
    public String initialize() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);

        ShobetuGoukeiDto shobetuGoukeiDto = (ShobetuGoukeiDto) container.getComponent("shobetuGoukeiDto");

        GetOnerCdLogic getOnerCdLogic = (GetOnerCdLogic) container.getComponent("itemtotal.getOnerCdLogic");
        GetMiseInfoLogic getMiseInfoLogic = (GetMiseInfoLogic) container.getComponent("itemtotal.getMiseInfoLogic");

        // 初期処理
        if (pullDownMenuDto.isClearFlg()) {
        	//複数ウィンドウID
        	shobetuGoukeiDto.updateWindowid();

        	// メニューから遷移された場合、DTO初期化する
        	shobetuGoukeiDto.clear();
        	// ユーザタイプ判定
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            // ユーザID
            String userId =  birdUserInfo.getMstUser().getUser_id();
        	// アプリ日付取得
            String appDate = birdDateInfo.getAppDate();
            //アプリ日付をDTOに保存
            shobetuGoukeiDto.setAppDate(appDate);
            shobetuGoukeiDto.setNewWindowFlg("");

            //本部ユーザーの場合
            if (userTypeCd.equals(USER_TYPE_HONBU)) {
            	//ユーザータイプをDTOに保存
                shobetuGoukeiDto.setUserTypeCd(userTypeCd);
            	//検索項目の初期化:
            	shobetuGoukeiDto.setCondOnerCd("");
            } else if (userTypeCd.equals(USER_TYPE_ONER)) {

            	//オーナーユーザーの場合:オーナーコード取得
            	UIOnerCd uiOnerCd = getOnerCdLogic.execute(userId);
            	//オーナーコード
            	String onerCd = uiOnerCd.getOnerCd();
            	//DTOにオーナーコード設定
            	shobetuGoukeiDto.setCondOnerCd(onerCd);
            	//2007.01.25李
            	shobetuGoukeiDto.setResultOnerCd(onerCd);
            	//システム日付
//            	String sysDate = getBirdDateInfo().getSysDate();
                String sysDate = birdDateInfo.getSysDate();

            	//対象期間リスト作成（当月含め１４ヶ月を表示）
            	makeTaishoKikan(getShobetuGoukeiDto().getAppDate());

            	/*
            	 	対象店舗情報を検索
            	 	param1:オーナーコード-onerCd
            	 	param1:システム日付-sysDate
            	 */
            	List miseInfoList = getMiseInfoLogic.execute(sysDate, onerCd);
            	List<UIMiseInfo> newList = new ArrayList<UIMiseInfo>();
            	if(miseInfoList.size()>0){
                 for (int i=0; i<miseInfoList.size(); i++){
                	 UIMiseInfo entity = (UIMiseInfo)miseInfoList.get(i);
                	 String str=entity.getMiseNameKj();
                	 if(str.endsWith("(CLOSE)")){
                		 entity.setMiseNameKj(trimFormatter.trimWideHalfSpace(str.substring(0, str.length()-7))+"(CLOSE)");
                	 }
                 	newList.add(entity);
                 }
            	}
            	getShobetuGoukeiDto().setCondTaishoTenpoList(newList);
//---2006/12/29 add kusama オーナー名称
                UIMiseInfo uiMiseInfo = (UIMiseInfo)miseInfoList.get(0);
                getShobetuGoukeiDto().setCondOnerNm(uiMiseInfo.getOnerNameKj());
            } else {
                throw new CannotAccessException();
            }
            pullDownMenuDto.setClearFlg(false);
        } else {
            CommonCodeDto commonCodeDto = (CommonCodeDto) container.getComponent(CommonCodeDto.class);
	        OwnerSearchDto ownerSearchDto = (OwnerSearchDto) container.getComponent(OwnerSearchDto.class);

            // オーナー選択から戻り
			if(ownerSearchDto.getReturnKind() != OwnerSearchDto.RETURNKIND_INIT) { //ownerSearchDto.isActionFlag()){
	            //検索条件の設定
				//shobetuGoukeiDto.setCondOnerCd(ownerSearchDto.getOnerCd());
				//ownerSearchDto.setActionFlag(false);

	            //【DTO】//オーナー選択.遷移情報を無効に設定する。
	        	ownerSearchDto.setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
	            //DTO【販売状況一覧】ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
	        	shobetuGoukeiDto.setWindowId(ownerSearchDto.getWindowId());
				// オーナー選択から戻り
				if(getOwnerSearchDto().isActionFlag()){
		            //オーナーコードの設定
					shobetuGoukeiDto.setCondOnerCd(ownerSearchDto.getOnerCd());

					getOwnerSearchDto().setActionFlag(false);
				}

			}
            //---2006/12/27 個店ポータル等のBIRD内画面からの遷移
            else if (commonCodeDto != null && commonCodeDto.getUseCommonDto()) {
                // メニューから遷移された場合、DTO初期化する
                shobetuGoukeiDto.clear();
                // ユーザタイプ判定
                String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
                shobetuGoukeiDto.setUserTypeCd(userTypeCd);
                // アプリ日付取得
                String appDate = birdDateInfo.getAppDate();
                //アプリ日付をDTOに保存
                shobetuGoukeiDto.setAppDate(appDate);
                shobetuGoukeiDto.setNewWindowFlg("");

                executeFromBird(commonCodeDto);
            }
        }
        // 自画面へ遷移
        return null;
    }

    /**
     * オーナー検索フォーム
     *
     * @return　オーナー検索画面
     */
    public String callOnerForm() {
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(VIEWID_CONDITION);
        getOwnerSearchDto().setInitFlag(true);
        //複数WindowID
        getOwnerSearchDto().setWindowId(getShobetuGoukeiDto().getWindowId());
        //オーナー選択.遷移情報を有効に設定。
        getOwnerSearchDto().setNeedReturnKind(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getShobetuGoukeiDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //オーナーコードを保持
        getShobetuGoukeiDto().setCondOnerCd(getShobetuGoukeiDto().getCondOnerCd());
        return VIEWID_ONERSEARCH;
    }

    /**
     * オーナーコードに該当する店舗リストを取得します
     *
     * @return　遷移する画面
     */
    public String taishoTenpoExecute() {
    	//初期化
    	getShobetuGoukeiDto().clearExcute();

    	//対象店舗リストや対象期間を取得する
    	//　オーナーコードチェック
    	validate();

    	//オーナーコード取得する
    	String onerCd = getShobetuGoukeiDto().getCondOnerCd();
    	String sysDate = getBirdDateInfo().getSysDate();
    	/*
    	    対象店舗情報を検索
    	  param1:オーナーコード-onerCd
    	  param1:システム日付-sysDate
    	*/
    	List miseInfoList = getMiseInfoLogic().execute(sysDate, onerCd);
    	List<UIMiseInfo> newList = new ArrayList<UIMiseInfo>();
    	if(miseInfoList.size()>0){
         for (int i=0; i<miseInfoList.size(); i++){
        	 UIMiseInfo entity = (UIMiseInfo)miseInfoList.get(i);
        	 String str=entity.getMiseNameKj();
        	 if(str.endsWith("(CLOSE)")){
        		 entity.setMiseNameKj(trimFormatter.trimWideHalfSpace(str.substring(0, str.length()-7))+"(CLOSE)");
        	 }
         	newList.add(entity);
         }
    	}
    	getShobetuGoukeiDto().setCondTaishoTenpoList(newList);

    	UIMiseInfo uiMiseInfo = (UIMiseInfo)miseInfoList.get(0);
    	getShobetuGoukeiDto().setCondOnerNm(uiMiseInfo.getOnerNameKj());

    	//対象期間リスト作成（当月含め１４ヶ月を表示）
    	makeTaishoKikan(getShobetuGoukeiDto().getAppDate());
    	//2007.01.24 李
    	getShobetuGoukeiDto().setResultOnerCd(onerCd);

        return null;
    }

    /**
     *　商品別情報を取得
     *
     * @return　遷移する画面
     */
    public String searchExecute() {
    	//初期化
    	getShobetuGoukeiDto().clearSearchExcute();

        //選択された対象店舗名設定
    	for(int i = 0; i < getShobetuGoukeiDto().getCondTaishoTenpoList().size(); i++) {
    		UIMiseInfo uiMiseInfo =  (UIMiseInfo) getShobetuGoukeiDto().getCondTaishoTenpoList().get(i);
    		if(uiMiseInfo.getMiseCd().equals(getShobetuGoukeiDto().getCondTaishoTenpoCd())) {
    			getShobetuGoukeiDto().setCondTaishoTenpoNm(uiMiseInfo.getMiseNameKj());
    			break;
    		}
    	}
    	/* 分類情報を取得する
    	 */
    	List bunruiInfoList = getBunruiInfoLogic().execute(getShobetuGoukeiDto());

        //１行目のタブは７個、２行目のタブは８個で作成する
        List tmpBunruiList = new ArrayList();
        List newBunruiList = new ArrayList();

        for(int i = 0; i < bunruiInfoList.size(); i++) {

        	tmpBunruiList.add(bunruiInfoList.get(i));
        	if(i == 6 || i == 14) {
        		newBunruiList.add(tmpBunruiList);
        		tmpBunruiList = new ArrayList();
        	}
        }
        getShobetuGoukeiDto().setBunruiInfoSubList(newBunruiList);
        getShobetuGoukeiDto().setBunruiInfoList(getShobetuGoukeiDto().getBunruiInfoSubList());
        //append 2007.01.24 李
        //検索条件を結果条件へ設定
        //getShobetuGoukeiDto().setResultOnerCd(getShobetuGoukeiDto().getCondOnerCd());
        getShobetuGoukeiDto().setResultTaishoKikanCd(getShobetuGoukeiDto().getCondTaishoKikanCd());
        getShobetuGoukeiDto().setResultTaishoTenpoCd(getShobetuGoukeiDto().getCondTaishoTenpoCd());

    	/*　売上高情報取得する
    	 * param1 : 店コード
    	 * param2 : 売掛年月
    	 * "01893","200606"
    	 */
    	BigDecimal uriageDakeInfo = getUriageDakeInfoLogic().execute(
    			getShobetuGoukeiDto().getCondTaishoTenpoCd(),getShobetuGoukeiDto().getCondTaishoKikanCd());
    	getShobetuGoukeiDto().setUriageDakaInfo(uriageDakeInfo);


    	/*　商品別情報を取得する
    	 * param1 : 店コード
    	 * param2 : 売掛年月
    	 */
    	List shohinBetuInfoList = getShohinBetuInfoLogic().execute(getShobetuGoukeiDto());
    	getShobetuGoukeiDto().setShohinbetuInfoList(shohinBetuInfoList);

    	return null;
    }

    /**
     * オーナー検索フォーム
     *
     * @return　オーナー検索画面
     */
    public String callDetailForm() {

        return VIEWID_DETAIL;
    }

    /**
     * 入力チェック
     */
    private void validate() {
        // オーナーコード必須チェック
    	String onerCd = getShobetuGoukeiDto().getCondOnerCd();

        // 半角数字
		CodeVerifier codeVerifier = new CodeVerifier();
		if (onerCd != null && !onerCd.equals("")
				&& !codeVerifier.validate(onerCd)) {
			//以前検索結果がある場合はクリア
			if(getShobetuGoukeiDto().getShohinbetuInfoListSize() > 0) {
				getShobetuGoukeiDto().setShohinbetuInfoList(null);
			}
			throw new InvalidInputException("オーナーコード", "onerCd", "");
		}
    }

    /**
     *　対象期間リストを設定する
     *　アプリ日付から１４ヶ月分の日付を設定する
     * @param appDate　アプリ日付
     * @return 対象期間
     */
    private void makeTaishoKikan(String appDate) {

    	String appYm = appDate.substring(0,6);
    	List taishoKikanList = new ArrayList();
        /** DateFormatter */
        DateFormatter formatter = new DateFormatter();
    	for(int i = 0 ; 14 > i ; i++) {
//    		int YY = Integer.parseInt(appYm.substring(0,4));
//    		int MM = Integer.parseInt(appYm.substring(4,6));
//
//    		int newMM = MM - i;
//    		int newYY = 0;
//
//    		if(newMM <= 0) {
//    			newMM = 12 + newMM;
//    			newYY = YY - 1;
//    		} else {
//    			newYY = YY;
//    		}
//    		if(newMM < 10) {
//    		}
//    		taishoKikanList.add(new SelectItem(String.valueOf(newYY) + (newMM < 10 ? "0" + String.valueOf(newMM): String.valueOf(newMM)),
//    				String.valueOf(newYY) +"/"+ (newMM < 10 ? "0" + String.valueOf(newMM): String.valueOf(newMM))));
            String yyyyMm;
            try {
                yyyyMm = DateManager.getPrevMonth(appYm,i);
            } catch (Exception e) {
                throw new FtlSystemException("商品別合計情報初期処理");
            }
            taishoKikanList.add(new SelectItem(yyyyMm,formatter.format
                    (yyyyMm, DateFormatter.PATTERN_MONTH_SLASH,DateFormatter.DATE_TYPE_YM)));
    	}

    	//前月が選択されるように
    	try {
			getShobetuGoukeiDto().setCondTaishoKikanCd(DateManager.getPrevMonth(appYm, 1));
		} catch (Exception e) {
			throw new FtlSystemException("商品別合計情報初期処理");
		}
    	getShobetuGoukeiDto().setCondTaishoKikanList(taishoKikanList);
    }

    /**
     * BIRD内画面から遷移された場合のアクション
     *　CommonCodeDtoのオーナーコードを元にtaishoTenpoExecuteアクションを実行する
     */
    private void executeFromBird(CommonCodeDto dto) {
        String onerCd = dto.getOnerCd();
        if (onerCd != null && !"".equals(onerCd.trim())) {
            getShobetuGoukeiDto().setCompanyCd(dto.getCompanyCd());
            getShobetuGoukeiDto().setCondOnerCd(onerCd);
            taishoTenpoExecute();
        }
    }
}