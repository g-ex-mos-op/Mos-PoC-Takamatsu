package jp.co.isid.mos.bird.storeinfo.onerref.action.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.MenuActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storeinfo.onerref.action.OwnerInfoReferenceAction;
import jp.co.isid.mos.bird.storeinfo.onerref.dto.OwnerReferenceDto;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.CodCompanyJoho;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.CodOnerLinkJoho;
import jp.co.isid.mos.bird.storeinfo.onerref.entity.MstMise;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.MstMiseJohoLogic;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.MstOnerNameLogic;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.OnerJohoLogic;
import jp.co.isid.mos.bird.storeinfo.onerref.logic.OnerLinkJohoLogic;
import jp.co.isid.mos.bird.storeinfo.util.LinkUrlCreator;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class OwnerInfoReferenceActionImpl implements OwnerInfoReferenceAction {

    /* アクションID */
    public static String initialize_ACTION_ID        = "BSI002A01";
    public static String selectOnerSearch_ACTION_ID  = "BSI002A02";
    public static String search_ACTION_ID            = "BSI002A03";
    public static String selectLink_ACTION_ID        = "BSI002A04";
    public static String callMiseInfo_ACTION_ID      = "BSI002A05";

    /* オーナ検索情報用Dto */
    private OwnerSearchDto ownerSearchDto;
    /* オーナ情報照会入力フォーム用Dto */
    private OwnerReferenceDto ownerReferenceDto;

    /* ユーザー所属管理会社の検索Logic */
    private CompanyJohoLogic companyJohoLogic;
    /* オーナー情報の取得Logic */
    private OnerJohoLogic onerJohoLogic;
    /* オーナー照会リンク情報の取得Logic */
    private OnerLinkJohoLogic onerLinkJohoLogic;
    /* 店マスタ情報の取得Logic */
    private MstMiseJohoLogic mstMiseJohoLogic;
    /* オーナー名称の取得Logic */
    private MstOnerNameLogic mstOnerNameLogic;
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;

    /* MenuAction */
    private MenuActionImpl menuAction;
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
   
    /* 個店ポータル遷移情報店コード */
    private String miseInfoMiseCd;
    
    /* オーナ選択遷移元情報 */
    private String onerSearchNavigationCase;

    /**
     * オーナ選択Dtoの設定
     * @return ownerSearchDto を戻します。
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }
    /**
     * オーナ選択Dtoの設定
     * @param ownerSearchDto ownerSearchDto を設定。
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }
    
    /**
     * オーナ情報照会用Dtoの設定
     * @return onerReferenceDto を戻します。
     */
    public OwnerReferenceDto getOwnerReferenceDto() {
        return ownerReferenceDto;
    }
    /**
     * オーナ情報照会用Dtoの設定
     * @param onerReferenceDto onerReferenceDto を設定。
     */
    public void setOwnerReferenceDto(OwnerReferenceDto ownerReferenceDto) {
        this.ownerReferenceDto = ownerReferenceDto;
    }
    
	/**
	 * ユーザー所属管理会社の検索Logicの設定
	 * @return companyJohoLogic を戻します。
	 */
	public CompanyJohoLogic getCompanyJohoLogic() {
		return companyJohoLogic;
	}
	/**
	 * ユーザー所属管理会社の検索Logicの設定
	 * @param companyJohoLogic companyJohoLogic を設定。
	 */
	public void setCompanyJohoLogic(CompanyJohoLogic companyJohoLogic) {
		this.companyJohoLogic = companyJohoLogic;
	}

	/**
	 * オーナー情報の取得Logicの設定
	 * @return onerJohoLogic を戻します。
	 */
	public OnerJohoLogic getOnerJohoLogic() {
		return onerJohoLogic;
	}
	/**
	 * オーナー情報の取得Logicの設定
	 * @param onerJohoLogic onerJohoLogic を設定。
	 */
	public void setOnerJohoLogic(OnerJohoLogic onerJohoLogic) {
		this.onerJohoLogic = onerJohoLogic;
	}

	/**
	 * オーナー照会リンク情報の取得Logicの設定
	 * @return onerLinkJohoLogic を戻します。
	 */
	public OnerLinkJohoLogic getOnerLinkJohoLogic() {
		return onerLinkJohoLogic;
	}
	/**
	 * オーナー照会リンク情報の取得Logicの設定
	 * @param onerLinkJohoLogic onerLinkJohoLogic を設定。
	 */
	public void setOnerLinkJohoLogic(OnerLinkJohoLogic onerLinkJohoLogic) {
		this.onerLinkJohoLogic = onerLinkJohoLogic;
	}
    
    // 20060607 追加 start -----------------------------------------
    /**
     * 店マスタ情報の取得Logicの設定
     * @return mstMiseJohoLogic を戻します。
     */
    public MstMiseJohoLogic getMstMiseJohoLogic() {
        return mstMiseJohoLogic;
    }
    /**
     * 店マスタ情報の取得Logicの設定
     * @param mstMiseJohoLogic mstMiseJohoLogic を設定。
     */
    public void setMstMiseJohoLogic(MstMiseJohoLogic mstMiseJohoLogic) {
        this.mstMiseJohoLogic = mstMiseJohoLogic;
    }
    
    /**
     * オーナー名称の取得Logicの設定
     * @return mstOnerNameLogic を戻します。
     */
    public MstOnerNameLogic getMstOnerNameLogic() {
        return mstOnerNameLogic;
    }
    /**
     *  オーナー名称の取得Logicの設定
     * @param mstOnerNameLogic mstOnerNameLogic を設定。
     */
    public void setMstOnerNameLogic(MstOnerNameLogic mstOnerNameLogic) {
        this.mstOnerNameLogic = mstOnerNameLogic;
    }
    // 20060607 追加 end -------------------------------------------
    
	/**
	 * BIRDログイン情報の設定
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * BIRDログイン情報の設定
	 * @param birdUserInfo birdUserInfo を設定。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * BIRD日付情報の設定
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * BIRD日付情報の設定
	 * @param birdDateInfo birdDateInfo を設定。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
     * 初期処理判定Dtoの設定
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    /**
     * 初期処理判定Dtoの設定
     * @param pullDownMenuDto pullDownMenuDto を設定。
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

	/**
	 * 個店ポータル遷移情報店コードの設定
	 * @return miseInfoMiseCd を戻します。
	 */
	public String getMiseInfoMiseCd() {
		return miseInfoMiseCd;
	}
	/**
	 * 個店ポータル遷移情報店コードの設定
	 * @param miseInfoMiseCd miseInfoMiseCd を設定。
	 */
	public void setMiseInfoMiseCd(String miseInfoMiseCd) {
		this.miseInfoMiseCd = miseInfoMiseCd;
	}

	/**
	 * オーナ選択遷移元情報の設定
	 * @return onerSearchNavigationCase を戻します。
	 */
	public String getOnerSearchNavigationCase() {
		return onerSearchNavigationCase;
	}
	/**
	 * オーナ選択遷移元情報の設定
	 * @param onerSearchNavigationCase onerSearchNavigationCase を設定。
	 */
	public void setOnerSearchNavigationCase(String onerSearchNavigationCase) {
		this.onerSearchNavigationCase = onerSearchNavigationCase;
	}
    
    private void setPullData(String userTypeCd){
        
        if (userTypeCd.equals("02")  ) {
            // ユーザタイプ「オーナー」の場合
            List listUserOner = birdUserInfo.getUserOner();
            UIUserOner uIUserOner = null;
            String companyCd  = "";
            List listPullOner = null;
            for (Iterator ite = listUserOner.iterator(); ite.hasNext();) {
                uIUserOner = (UIUserOner) ite.next();
                companyCd  = uIUserOner.getCompanyCd();
                listPullOner = 
                    getMstOnerNameLogic().execute(
                        uIUserOner.getCompanyCd(), uIUserOner.getOnerCd());
                
                if(companyCd.equals("00")){
                    getOwnerReferenceDto().setListOnerMos(listPullOner);
                    
                } else if (companyCd.equals("60")){
                    getOwnerReferenceDto().setListOnerTomos(listPullOner);
                    
                } else if (companyCd.equals("70")){
                    getOwnerReferenceDto().setListOnerSikina(listPullOner);
                }
            }
            
            listPullOner = null;
            companyCd  = ownerReferenceDto.getCompanyCd();
//            companyCd  = ((CodCompanyJoho) getOwnerReferenceDto().getCompanyList().get(0)).getCompanyCd();
            if(companyCd.equals("00")){
                listPullOner = getOwnerReferenceDto().getListOnerMos();
                
            } else if (companyCd.equals("60")){
                listPullOner = getOwnerReferenceDto().getListOnerTomos();
                
            } else if (companyCd.equals("70")){
                listPullOner = getOwnerReferenceDto().getListOnerSikina();
                
            }
            getOwnerReferenceDto().setListPullOner(listPullOner);
            
        } else if(userTypeCd.equals("03")){
            // ユーザタイプ「店舗」の場合
            List listUserMise = birdUserInfo.getUserMise();
            // 【店マスタの検索】（オーナーを取得する）
            MstMise mstMise = 
                getMstMiseJohoLogic().execute(
                        ((UIUserMise)listUserMise.get(0)).getCompanyCd(), 
                        ((UIUserMise)listUserMise.get(0)).getMiseCd());
            
            getOwnerReferenceDto().setCompanyCd(mstMise.getCompanyCd());
            getOwnerReferenceDto().setOnerCd(mstMise.getOnerCd());
        }
    }

	/**
     * オーナー情報照会初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {
        
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container
                .getComponent("request");
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
                .getAttribute("birdUserInfo");
        
    	// 初期処理
    	if (pullDownMenuDto.isClearFlg()) {
        	// ユーザー所属管理会社の検索
			ownerReferenceDto.setCompanyList(companyJohoLogic
					.execute(birdUserInfo.getUserID()));
        	pullDownMenuDto.setClearFlg(false);

        	// ログイン情報DTO保持
        	ownerReferenceDto.setBirdUserInfo(birdUserInfo);
            
            // 条件項目のクリア
            getOwnerReferenceDto().setCompanyCd("");
            getOwnerReferenceDto().setOnerCd("");
            
            //20060607追加 start ------------------------------------------
            getOwnerReferenceDto().setListPullOner(null);
            //ユーザタイプDTOにセット
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            ownerReferenceDto.setUserTypeCd(userTypeCd);
            ownerReferenceDto.setCompanyCd(((CodCompanyJoho) getOwnerReferenceDto().getCompanyList().get(0)).getCompanyCd());
            //条件画面プルダウンデータセット
            setPullData(userTypeCd);
            
            if(userTypeCd.equals("03")){
                // ユーザタイプ「店舗」の場合
                return search();
            }
            //20060607追加 end -------------------------------------------
    	}
    	
        // BIRD内画面から遷移された場合の処理
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            
			// ユーザー所属管理会社の検索
			ownerReferenceDto.setCompanyList(companyJohoLogic
					.execute(birdUserInfo.getUserID()));
			pullDownMenuDto.setClearFlg(false);

            // ログイン情報DTO保持
            ownerReferenceDto.setBirdUserInfo(birdUserInfo);

            
			String companyCd = getCommonCodeDto().getCompanyCd();
			String onerCd = getCommonCodeDto().getOnerCd();
			if (!isNull(companyCd) && !isNull(onerCd)) {
				ownerReferenceDto.setOnerCd(onerCd);
				ownerReferenceDto.setCompanyCd(companyCd);
                
                //20060607追加 start ------------------------------------------
                //ユーザタイプDTOにセット
                String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
                getOwnerReferenceDto().setUserTypeCd(userTypeCd);
                //条件画面プルダウンデータセット
                setPullData(userTypeCd);
                // 20060607追加 end -------------------------------------------
                
				search();
			}
		}

        // オーナ選択結果取得
    	if (getOwnerSearchDto().isActionFlag()) {
            getOwnerReferenceDto().setOnerCd(getOwnerSearchDto().getOnerCd());
			ownerSearchDto.setActionFlag(false);

            //20061121 ダイレクト結果表示対応
            return search();
        }
        return null;
    }
    
    /**
     * 店リスト読込処理
     * @return 画面遷移情報
     */
    public String loadOnerList() {
        List listPullOner = null;
        
        getOwnerReferenceDto().setListPullOner(null);
        
        String companyCd = getOwnerReferenceDto().getCompanyCd();
        if(companyCd.equals("00")){
            listPullOner = getOwnerReferenceDto().getListOnerMos();
            
            
        } else if (companyCd.equals("60")){
            listPullOner = getOwnerReferenceDto().getListOnerTomos();
            
        } else if (companyCd.equals("70")){
            listPullOner = getOwnerReferenceDto().getListOnerSikina();
            
        }
        
        getOwnerReferenceDto().setListPullOner(listPullOner);

        return null;
    }
    

    /**
     * オーナー検索処理
     * @return 画面遷移情報
     */
    public String selectOnerSearch() {
    	// オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(onerSearchNavigationCase);
        getOwnerSearchDto().setInitFlag(true);
        // 選択された会社コードセット
        List list = new ArrayList();
        list.add(ownerReferenceDto.getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(list);

        return "BCO006V01";
    }

    /**
     * 検索
     * @return 画面遷移情報
     */
    public String search() {
    	// 入力チェック
    	validate();
    	// オーナ情報検索
    	onerJohoLogic.execute(ownerReferenceDto);
    	// オーナ照会リンク情報取得
    	ownerReferenceDto.setBirdUserInfo(birdUserInfo);
    	ownerReferenceDto
				.setCodOnerLinkJohoList(makeListLinkJoho(onerLinkJohoLogic
						.execute(ownerReferenceDto)));
        
//20060525 追加 start -----------------------------------------------------------------------------        
        List listPullCompany = ownerReferenceDto.getCompanyList();
        if (listPullCompany != null) {
            for (int i = 0; i < listPullCompany.size(); i++) {
                CodCompanyJoho codCompanyJoho = (CodCompanyJoho) listPullCompany.get(i);
                if (ownerReferenceDto.getCompanyCd().equals(codCompanyJoho.getCompanyCd())) {
                    ownerReferenceDto.setSearchedCondCompanyName(codCompanyJoho.getCompanyName());
                    break;
                }
            }
        }
//20060525 追加 end ------------------------------------------------------------------------------ 
        
		return "BSI002V02";
	}
    
    /**
     * 入力チェック
     */
    private void validate() {
        // オーナーコード必須チェック
    	String onerCd = ownerReferenceDto.getOnerCd();
        if (onerCd == null || onerCd.equals("")) {
            throw new NotNullException("オーナーコード", "onerCd", "");
        }
        // 半角数字
		CodeVerifier codeVerifier = new CodeVerifier();
		if (onerCd != null && !onerCd.equals("")
				&& !codeVerifier.validate(onerCd)) {
			throw new InvalidInputException("オーナーコード", "onerCd", "");
		}
	    // オーナーコード桁数
	    if (onerCd != null && onerCd.getBytes().length > 5) {
	        throw new InvalidInputException("オーナーコード", "onerCd", "");
	    }
    }
    
	/**
	 * ナビ表示用のリンク情報List作成
	 */
	private List makeListLinkJoho(List listLink) {
		// パラメータ変換値をセット
		return LinkUrlCreator.createLinkUrl(getParamMap(), listLink);
	}
		
	/**
	 * 関連リンク作成用 パラメータ情報
	 */
	private Map getParamMap() {
		Map map = new HashMap();
        try {
            String dtTougetu = getBirdDateInfo().getAppDate().substring(0, 6);
            String dtZengetu = DateManager.getPrevMonth(dtTougetu, 1);
            String appDt = getBirdDateInfo().getAppDate();
            String appDtMinus1 = DateManager.getPrevDate(appDt, 1);
            String nendo = getNendo();
            String nendoBefore4 = DateManager.getPrevYear(nendo, 4);

    		// オーナコード
    		map.put("onerCd", ownerReferenceDto.getMstOner().getOnerCd());
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

	/**
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

    /**
     * 関連リンク一覧の選択
     * @return 画面遷移情報
     */
    public String selectLink() {
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

        // リンクIDよりオーナ照会リンク情報を取得
        CodOnerLinkJoho codOnerLinkJoho = null;
        for (Iterator ite = ownerReferenceDto.getCodOnerLinkJohoList()
				.iterator(); ite.hasNext();) {
			codOnerLinkJoho = (CodOnerLinkJoho) ite.next();
			if (linkId.equals(codOnerLinkJoho.getOLinkId())) {
				break;
			}
		}
        
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
        	commonCodeDto = new CommonCodeDto();
        }
        commonCodeDto.setOnerCd(ownerReferenceDto.getMstOner().getOnerCd());
		commonCodeDto.setCompanyCd(ownerReferenceDto.getMstOner()
				.getCompanyCd());
        
        if (codOnerLinkJoho != null) {
            if ("1".equals(codOnerLinkJoho.getSiteKbn())) {
                getMenuAction().doSelectMainMenu(codOnerLinkJoho.getUrl());
            }
        	return codOnerLinkJoho.getUrl();
        }
        else {
        	return null;
        }
	}

    /**
     * 個店ポータル照会へ遷移
     * @return 画面遷移情報
     */
    public String callMiseInfo() {
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
            commonCodeDto = new CommonCodeDto();
        }
        commonCodeDto.setMiseCd(miseInfoMiseCd);
        commonCodeDto.setCompanyCd(ownerReferenceDto.getMstOner().getCompanyCd());
        commonCodeDto.setUseCommonDto(true);
        return "BSI001V02";
    }

    
	private HttpServletRequest getHttpServletRequest() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		return (HttpServletRequest) container.getComponent("request");
	}

	private CommonCodeDto getCommonCodeDto() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
	}

    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    public MenuActionImpl getMenuAction() {
        return menuAction;
    }
    public void setMenuAction(MenuActionImpl menuAction) {
        this.menuAction = menuAction;
    }
}