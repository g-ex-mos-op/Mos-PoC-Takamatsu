
/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storeinfo.miseref.action.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storeinfo.miseref.action.MiseRefAction;
import jp.co.isid.mos.bird.storeinfo.miseref.dto.MiseRefDto;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.CodCompanyJoho;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.CodKotenLinkJoho;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstMise;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstMiseYobi;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.TrnURL;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.KotenJohoLogic;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.KotenLinkJohoLogic;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.SchMiseListLogic;
import jp.co.isid.mos.bird.storeinfo.util.LinkUrlCreator;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 文書公開画面アクション
 * @author xnkusama
 */
public class MiseRefActionImpl implements CommonAction, MiseRefAction {

    /* アクションID */
    public static final String initialize_ACTION_ID   = "BSI001A04";
    public static final String callMiseForm_ACTION_ID = "BSI001A05";
    public static final String execute_ACTION_ID      = "BSI001A06";
    public static final String changeTab_ACTION_ID    = "BSI001A07";
    public static final String startOutLink_ACTION_ID = "BSI001A08";
    public static final String callOnerInfo_ACTION_ID = "BSI001A09";
    /* ビューID */
//    private static final String VIEWID_CONDITION    = "BSI001V01"; //条件画面
    private static final String VIEWID_RESULT       = "BSI001V02"; //結果画面
    private static final String VIEWID_MISESEARCH   = "BCO008V01";//店検索
    private static final String VIEWID_ONERINOF     = "BSI002V02";
    
//    /* Birdユーザー情報 */
//    private BirdUserInfo birdUserInfo;
//    /* Bird日付情報 */
//    private BirdDateInfo birdDateInfo;

    /*【アクション】*/
    private MenuActionImpl menuAction;
    /*【ロジック】*/
    //ユーザー所属管理会社の検索
    private CompanyJohoLogic companyJohoLogic;
    //個店情報の検索
    private KotenJohoLogic kotenJohoLogic;
    //個店リンク情報の取得
    private KotenLinkJohoLogic kotenLinkJohoLogic;
    //店リストの検索
    private SchMiseListLogic schMiseListLogic;
    
    /*【DTO】*/
    private MiseRefDto miseRefDto;
    private MiseSearchDto miseSearchDto;
    private PullDownMenuDto pullDownMenuDto;
    // 選択タブIndex
    private int tabIndex;
    
    private void setPullData(String userTypeCd){
        
        if (userTypeCd.equals("02")) {
            // ユーザタイプ「オーナー」の場合
            List listUserOner = getBirdUserInfo().getUserOner();
            
            UIUserOner uIUserOner = null;
            String companyCd  = "";
            List listPullMise = null;
            for(Iterator ite = listUserOner.iterator(); ite.hasNext();){
                uIUserOner = (UIUserOner) ite.next();
                companyCd  = uIUserOner.getCompanyCd();
                // ロジック【店リストの検索ロジック】
                listPullMise = 
                    getSchMiseListLogic().execute(companyCd, uIUserOner.getOnerCd());
                
                if(companyCd.equals("00")){
                    getMiseRefDto().setListMiseMos(listPullMise);
                    
                } else if (companyCd.equals("60")){
                    getMiseRefDto().setListMiseTomos(listPullMise);
                    
                } else if (companyCd.equals("70")){
                    getMiseRefDto().setListMiseSikina(listPullMise);
                }
            }
            
            listPullMise = null;
            companyCd  = getMiseRefDto().getCondCompanyCd();
//            companyCd  = ((CodCompanyJoho) listCompany.get(0)).getCompanyCd();
            if(companyCd.equals("00")){
                listPullMise = getMiseRefDto().getListMiseMos();
                
            } else if (companyCd.equals("60")){
                listPullMise = getMiseRefDto().getListMiseTomos();
                
            } else if (companyCd.equals("70")){
                listPullMise = getMiseRefDto().getListMiseSikina();
            }
            getMiseRefDto().setListPullMise(listPullMise);
            
        } else if (userTypeCd.equals("03")) {
            // ユーザタイプ「店舗」の場合
            List listUserMise = getBirdUserInfo().getUserMise();
            getMiseRefDto().setListUserMise(listUserMise);
            getMiseRefDto().setCondCompanyCd(((UIUserMise)listUserMise.get(0)).getCompanyCd());
            getMiseRefDto().setCondMiseCd(((UIUserMise)listUserMise.get(0)).getMiseCd());
        }
    }

    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        //ログインユーザ情報取得
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
        .getAttribute("birdUserInfo");
        
        // ロジック【ユーザー所属管理会社の検索】
        List listCompany = getCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
        getMiseRefDto().setListCompany(listCompany);
        
    	// メニューから遷移された場合のクリア処理
        if (getPullDownMenuDto().isClearFlg()) {
            getMiseRefDto().setCondCompanyCd("");
            getMiseRefDto().setCondMiseCd("");
            getMiseRefDto().setMstMise(null);
            getPullDownMenuDto().setClearFlg(false);
            //20060607追加
            getMiseRefDto().setListPullMise(null);
        }
        
        // BIRD内画面から遷移された場合の処理
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
        	String companyCd = getCommonCodeDto().getCompanyCd();
        	String miseCd = getCommonCodeDto().getMiseCd();
        	if (!isNull(companyCd) && !isNull(miseCd)) {
                
                getMiseRefDto().setCondMiseCd(miseCd);
                getMiseRefDto().setCondCompanyCd(companyCd);
                
                //20060621 追加 start ------------------------------------------
                //ユーザタイプDTOにセット
                String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
                getMiseRefDto().setUserTypeCd(userTypeCd);
                //条件画面プルダウンデータセット
                setPullData(userTypeCd);                       
                //20060621 追加 end --------------------------------------------
                
        		execute();
        	}
        }
        // e-mosslesから遷移された場合
        if (isEmos()) {
            execute();
        }
        
        // 条件画面から遷移時の処理
        if (getMiseRefDto().isFlgCondToEdit()) {
            //条件画面から遷移時のクリア処理
        	getMiseRefDto().clearEditInit();
            //Entityセット
            Map map = getMiseRefDto().getMapMiseInfo();
            //編集用EntityをDTOへセット
            setEntity(map);
            // ロジック【個店リンク情報の取得】
            List listKotenLink = getKotenLinkJohoLogic().execute(getBirdUserInfo().getUserID());
            // リンク情報の加工しDTOへセット
            getMiseRefDto().setListKotenLinkJoho(makeListLinkJoho(listKotenLink));
            // 初期値セット
            getMiseRefDto().setUserId(getBirdUserInfo().getUserID());

        }
        
        // 
        getMiseRefDto().setFlgCondToEdit(false);
       
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {            
            getMiseRefDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
            
            //20061121 ダイレクト結果表示対応
            return execute();
        }
        return null;
    }
    
    /**
     * 検索条件より個店情報を検索する処理
     * @return 画面遷移情報(結果画面)
     */
    public String execute() throws ApplicationException {

        //検索条件を取得
        String condCompanyCd = getMiseRefDto().getCondCompanyCd();
        String condMiseCd    = getMiseRefDto().getCondMiseCd();

        //---------------------------------
        // ロジック【個店情報の検索】実行
        //---------------------------------
        Map mapKotenJoho = getKotenJohoLogic().execute(condMiseCd, condCompanyCd);
        
        //検索結果をDTOにセット
        getMiseRefDto().setMapMiseInfo(mapKotenJoho);

        //条件画面 --> 編集画面の遷移フラグを立てる
        getMiseRefDto().setFlgCondToEdit(true);

        //検索条件を退避
        getMiseRefDto().setSearchedCondCompanyCd(condCompanyCd);
        getMiseRefDto().setSearchedCondMiseCd(condMiseCd);

        //検索条件の会社コードに対応する会社名称を取得
        List listPullCompany = getMiseRefDto().getListCompany();
        if (listPullCompany != null) {
            for (int i = 0; i < listPullCompany.size(); i++) {
                CodCompanyJoho codCompanyJoho = (CodCompanyJoho) listPullCompany.get(i);
                if (condCompanyCd.equals(codCompanyJoho.getCompanyCd())) {

                    //会社名称
                    String condCompanyName = codCompanyJoho.getCompanyName();

                    //DTOにセット
                    getMiseRefDto().setSearchedCondCompanyName(condCompanyName);
                    break;
                }
            }
        }
        //結果画面へ遷移
        return VIEWID_RESULT;
    }
    
    /**
     * 関連リンクアクション
     * @author xnkusama
     */
    public String startOutLink() {
    	// リクエストよりリンクID取得
    	String linkId = "";
        Enumeration e = getHttpServletRequest().getParameterNames();
        while (e.hasMoreElements()) {
            String pName = (String) e.nextElement();
            if (pName.lastIndexOf("linkId") != -1) {
                linkId = getHttpServletRequest().getParameter(pName);
                break;
            }
        }

        // リンクIDより個店情報リンク情報を取得
        CodKotenLinkJoho codKotenLinkJoho = null;
        for (Iterator ite = getMiseRefDto().getListKotenLinkJoho().iterator(); ite.hasNext();) {
        	codKotenLinkJoho = (CodKotenLinkJoho) ite.next();
        	if (linkId.equals(codKotenLinkJoho.getKLinkId())) {
        		break;
        	}
        }
        
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
        	commonCodeDto = new CommonCodeDto();
        }
        
//        Map mapParam = getMiseRefDto().getMapMiseInfo();
//        commonCodeDto.setMiseCd((String) mapParam.get("miseCd"));
//        commonCodeDto.setCompanyCd((String) mapParam.get("companyCd"));
        
        //20060601追加 個店ポータル画面からリンクする対応 --------------
        commonCodeDto.setMiseCd(getMiseRefDto().getCondMiseCd());
        commonCodeDto.setCompanyCd(getMiseRefDto().getCondCompanyCd());
        
        if (codKotenLinkJoho != null) {
            //2009/01/14 BIRD画面へ遷移する場合は、選択メニューをハイライトするための処理を実行
            if ("1".equals(codKotenLinkJoho.getSiteKbn())) {
                getMenuAction().doSelectMainMenu(codKotenLinkJoho.getUrl());
            }
            
        	return codKotenLinkJoho.getUrl();
        }
        else {
        	return null;
        }
    }
    
    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm() {
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(VIEWID_RESULT);
        miseSearchDto.setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMiseRefDto().getCondCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        return VIEWID_MISESEARCH;
    }

    public String callOnerInfo() {
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
        commonCodeDto.setOnerCd(getMiseRefDto().getMstMise().getOnerCd());
        commonCodeDto.setCompanyCd(getMiseRefDto().getMstMise().getCompanyCd());
        
    	return VIEWID_ONERINOF;
    }
    /**
     * タブ切替
     * @return
     */
    public String changeTab() {
        getMiseRefDto().setTabIndex(getTabIndex());
        getPullDownMenuDto().setClearFlg(false);
        return null;
    }
    
//    /**
//     * BIRD日付情報設定処理
//     * @param birdDateInfo birdDateInfo を設定。
//     */
//    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
//        this.birdDateInfo = birdDateInfo;
//    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

//    /**
//     * BIRDユーザー情報設定処理
//     * @param birdUserInfo birdUserInfo を設定。
//     */
//    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
//        this.birdUserInfo = birdUserInfo;
//    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    
    /**
     * @return companyJohoLogic を戻します。
     */
    public CompanyJohoLogic getCompanyJohoLogic() {
        return companyJohoLogic;
    }
    /**
     * @param companyJohoLogic companyJohoLogic を設定。
     */
    public void setCompanyJohoLogic(CompanyJohoLogic companyJohoLogic) {
        this.companyJohoLogic = companyJohoLogic;
    }
    /**
     * @return kotenJohoLogic を戻します。
     */
    public KotenJohoLogic getKotenJohoLogic() {
        return kotenJohoLogic;
    }
    /**
     * @param kotenJohoLogic kotenJohoLogic を設定。
     */
    public void setKotenJohoLogic(KotenJohoLogic kotenJohoLogic) {
        this.kotenJohoLogic = kotenJohoLogic;
    }
    /**
     * @return miseSearchDto を戻します。
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * @param miseSearchDto miseSearchDto を設定。
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }
    /**
     * @return tabIndex を戻します。
     */
    public int getTabIndex() {
        return tabIndex;
    }
    /**
     * @param tabIndex tabIndex を設定。
     */
    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
	public MiseRefDto getMiseRefDto() {
		return miseRefDto;
	}
	public void setMiseRefDto(MiseRefDto miseRefDto) {
		this.miseRefDto = miseRefDto;
	}
//	public PullDownMenuDto getPullDownMenuDto() {
//		return pullDownMenuDto;
//	}
//	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
//		this.pullDownMenuDto = pullDownMenuDto;
//	}

    /**
     * ロジックで取得したEntityを編集用にDTOへセット
     * @param mapEntity
     */
    private void setEntity(Map mapEntity) {
        // 店基本
        List listMstMise = (List)mapEntity.get(MiseRefDto.MAPKEY_KIHON);
        MstMise mstMise;
        if (listMstMise == null || listMstMise.size() == 0) {
            mstMise = new MstMise();
        }
        else {
            mstMise = (MstMise) listMstMise.get(0);
        }
        getMiseRefDto().setMstMise(mstMise);
        // 店拡張マスタ
        List listMiseYobi = (List) mapEntity.get(MiseRefDto.MAPKEY_YOBI);
        MstMiseYobi mstMiseYobi;
        if (listMiseYobi == null || listMiseYobi.size() == 0) {
            mstMiseYobi = new MstMiseYobi();
        }
        else {
            mstMiseYobi = (MstMiseYobi) listMiseYobi.get(0);
        }
        getMiseRefDto().setMstMiseYobi(mstMiseYobi);
        // イベント実施状況
        List listMstEventStatus = (List) mapEntity.get(MiseRefDto.MAPKEY_EVENTSTATUS);
        getMiseRefDto().setListMstEventStatus(listMstEventStatus);
        // 地図URL情報
        List listTrnUrl = (List) mapEntity.get(MiseRefDto.MAPKEY_URL);
        TrnURL trnUrl;
        if (listTrnUrl == null || listTrnUrl.size() == 0) {
            trnUrl = new TrnURL();
        }
        else {
            trnUrl = (TrnURL) listTrnUrl.get(0);
        }
        getMiseRefDto().setTrnUrl(trnUrl);
    }
	public KotenLinkJohoLogic getKotenLinkJohoLogic() {
		return kotenLinkJohoLogic;
	}
	public void setKotenLinkJohoLogic(KotenLinkJohoLogic kotenLinkJohoLogic) {
		this.kotenLinkJohoLogic = kotenLinkJohoLogic;
	}
	/**
	 * ナビ表示用のリンク情報List作成
	 */
	private List makeListLinkJoho(List listLink) {		
		// パラメータ変換値をセット
		Map map = getParamMap();
		
		listLink = LinkUrlCreator.createLinkUrl(map, listLink);
		
		return listLink;
	}
		
	/*
	 * 関連リンク作成用 パラメータ情報
	 */
	private Map getParamMap() throws ApplicationException {
        Map map = new HashMap();
        try {
            BirdDateInfo birdDateInfo = (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
            String dtTougetu = birdDateInfo.getSysDate().substring(0, 6);
            String dtZengetu = DateManager.getPrevMonth(dtTougetu, 1);
            String appDt = birdDateInfo.getAppDate();
            String appDtMinus1 = DateManager.getPrevDate(appDt, 1);
            String nendo = getNendo();
            String nendoBefore4 = DateManager.getPrevYear(nendo, 4);
    		// 店コード
    		map.put("miseCd", getMiseRefDto().getCondMiseCd());
    		// 年度
    		map.put("nendo", nendo);
            // 当月
    		map.put("month", dtTougetu);
            // 前月
            map.put("zengetu", dtZengetu);
            // アプリ日付
            map.put("appDt", appDt);
            // アプリ日付 － １日
            map.put("appDtMinus1", appDtMinus1);
            // ４年度前
            map.put("nendoBefore4", nendoBefore4);
        }
        catch (Exception ex) {
        	throw new FtlSystemException("リンク情報作成");
        }
		return map;
	}
	
	/*
	 * システム日付より年度を取得
	 */
	private String getNendo() {
		String sysDate = getBirdDateInfo().getSysDate();
		if (sysDate.substring(4, 6).compareTo("04") < 0) {
			String nendo =  sysDate.substring(0, 4);
			return String.valueOf(Integer.valueOf(nendo).intValue() - 1);
		}
		else {
			return sysDate.substring(0, 4);
		}
	}
	
	private CommonCodeDto getCommonCodeDto() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
	}

	private HttpServletRequest getHttpServletRequest() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		return (HttpServletRequest) container.getComponent("request");
	}
	
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
    
    /**
     * @return schMiseListLogic を戻します。
     */
    public SchMiseListLogic getSchMiseListLogic() {
        return schMiseListLogic;
    }
    /**
     * @param schMiseListLogic schMiseListLogic を設定。
     */
    public void setSchMiseListLogic(SchMiseListLogic schMiseListLogic) {
        this.schMiseListLogic = schMiseListLogic;
    }
    
    private boolean isEmos() {
        HttpServletRequest request = (HttpServletRequest) getS2Container().getComponent("request");
        
        String companyCd = "";
        String miseCd = "";
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String pName = (String) e.nextElement();
            if (pName.lastIndexOf("COMPANY_CD_E") != -1) {
                companyCd = request.getParameter(pName);
            }
            if (pName.lastIndexOf("MISE_CD_E") != -1) {
                miseCd = request.getParameter(pName);
            }
            if (!"".equals(miseCd) && !"".equals(companyCd)) {
            	getMiseRefDto().setCondCompanyCd(companyCd);
                getMiseRefDto().setCondMiseCd(miseCd);
            }
        }
        
        return !"".equals(miseCd) && !"".equals(companyCd);
    }
    
    private S2Container getS2Container() {
    	return SingletonS2ContainerFactory.getContainer();
    }

    public MenuActionImpl getMenuAction() {
        return menuAction;
    }

    public void setMenuAction(MenuActionImpl menuAction) {
        this.menuAction = menuAction;
    }
}