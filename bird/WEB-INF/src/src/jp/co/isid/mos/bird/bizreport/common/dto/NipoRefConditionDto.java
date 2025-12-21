package jp.co.isid.mos.bird.bizreport.common.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.logic.impl.ConditionLogicImpl;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 総合営業日報 条件部情報共通DTOクラス
 * 
 * @author xjung
 */
public class NipoRefConditionDto {
    /**
     * 管理会社企業情報リスト
     */
    private List companyList;
    
    /**
     * 店舗種別情報リスト
     */
    private List tenpoShubetuList;

    /**
     * 集計区分情報リスト
     */
    private List shukeiKbnList;
    
    /**
     * 対象店舗情報リスト
     */
    private List taishoTenpoList;
    
    /**
     * 対象期間情報リスト
     */
    private List taishoKikanList;
    
    /**
     * 期間指定Fromリスト
     */
    private List kikanFromList;

    /**
     * 期間指定Toリスト
     */
    private List kikanToList;
   
    /**
     * 期間指定年月(yyyy/MM)リスト
     */
    private List kikanYMList;
    
    /**
     * 期間指定期別年(yyyy)リスト
     */
    private List kikanYearList;
    
    /**
     * 期間指定期別リスト
     */
    private List kikanKibetuList;
    /**
     * 期間指定:当月月報
     */
    private String kikanCurrMonth;

    /**
     * 前年データ種別(前年対象店)リスト
     */
    private List zenDataZennenList;

    /**
     * 前年データ種別(前年対象店以外)リスト
     */
    private List zenDataZennenOthList;
    /**
     * 営業月報抽出用の期間指定年月(yyyy/MM)リスト
     */
    private List kikanGepoYMList;

    /**
     *  DTO【検索条件情報】保持Map
     *  windowIdをキーに格納されています。
     */
    private Map mapSearchParameter = new HashMap();
    /**
     * DTO【検索結果条件情報】保持Map
     *  windowIdをキーに格納されています。
     */
    private Map mapResultParameter = new HashMap(); 
    /** MOSCARDメニューアクセス権限判断値 */
    private boolean isAccessMoscard = false;
    
    /** ネット注文メニューアクセス権限判断値 */
    private boolean isAccessNetorder = false;

    /**
     * 会社リストを取得する
     * @return List 会社リスト
     */
    public List getCompanyList() {
        return companyList;
    }

    /**
     * 会社リストを設定する
     * @param companyList 会社リスト
     */
    public void setCompanyList(List companyList) {
        this.companyList = companyList;
    }

    /**
     * 店舗種別リストを取得する
     * @return List 店舗種別リスト
     */
    public List getTenpoShubetuList() {
        return tenpoShubetuList;
    }

    /**
     * 店舗種別リストを設定する
     * @param tenpoShubetuList 店舗種別リスト
     */
    public void setTenpoShubetuList(List tenpoShubetuList) {
        this.tenpoShubetuList = tenpoShubetuList;
    }

    /**
     * 集計区分リストを取得する
     * @return List 集計区分リスト
     */
    public List getShukeiKbnList() {
        return shukeiKbnList;
    }

    /**
     * 集計区分リストを設定する
     * @param shukeiKbnList 集計区分リスト
     */
    public void setShukeiKbnList(List shukeiKbnList) {
        this.shukeiKbnList = shukeiKbnList;
    }

    /**
     * 対象店舗リストを取得する
     * @return List 対象店舗リスト
     */
    public List getTaishoTenpoList() {
        return taishoTenpoList;
    }

    /**
     * 対象店舗リストを設定する
     * @param taishoTenpoList 対象店舗リスト
     */
    public void setTaishoTenpoList(List taishoTenpoList) {
        this.taishoTenpoList = taishoTenpoList;
    }

    /**
     * 対象期間リストを取得する
     * @return List 対象期間リスト
     */
    public List getTaishoKikanList() {
        return taishoKikanList;
    }

    /**
     * 対象期間リストを設定する
     * @param taishoKikanList 対象期間リスト
     */
    public void setTaishoKikanList(List taishoKikanList) {
        this.taishoKikanList = taishoKikanList;
    }

    /**
     * 期間指定Fromリストを取得する
     * @return List 期間指定Fromリスト
     */
    public List getKikanFromList() {
        return kikanFromList;
    }

    /**
     * 期間指定Fromリストを設定する
     * @param kikanFromList 期間指定Fromリスト
     */
    public void setKikanFromList(List kikanFromList) {
        this.kikanFromList = kikanFromList;
    }

    /**
     * 期間指定Toリストを取得する
     * @return List 期間指定Toリスト
     */
    public List getKikanToList() {
        return kikanToList;
    }

    /**
     * 期間指定Toリストを設定する
     * @param kikanToList 期間指定Toリスト
     */
    public void setKikanToList(List kikanToList) {
        this.kikanToList = kikanToList;
    }

    /**
     * 期間指定年月(yyyy/MM)リストを取得する
     * @return List 期間指定年月(yyyy/MM)リスト
     */
    public List getKikanYMList() {
        return kikanYMList;
    }

    /**
     * 期間指定年月(yyyy/MM)リストを設定する
     * @param kikanYMList 期間指定年月(yyyy/MM)リスト
     */
    public void setKikanYMList(List kikanYMList) {
        this.kikanYMList = kikanYMList;
    }

    /**
     * 期間指定期別年(yyyy)リストを取得する
     * @return List 期間指定期別年(yyyy)リスト
     */
    public List getKikanYearList() {
        return kikanYearList;
    }

    /**
     * 期間指定期別年(yyyy)リストを設定する
     * @param kikanYearList 期間指定期別年(yyyy)リスト
     */
    public void setKikanYearList(List kikanYearList) {
        this.kikanYearList = kikanYearList;
    }

    /**
     * 期間指定期別リストを取得する
     * @return List 期間指定期別リスト
     */
    public List getKikanKibetuList() {
        return kikanKibetuList;
    }

    /**
     * 期間指定期別リストを設定する
     * @param kikanKibetuList 期間指定期別リスト
     */
    public void setKikanKibetuList(List kikanKibetuList) {
        this.kikanKibetuList = kikanKibetuList;
    }

    /**
     * 前年データ種別(前年対象店)リストを取得する
     * @return List 前年データ種別(前年対象店)リスト
     */
    public List getZenDataZennenList() {
        return zenDataZennenList;
    }

    /**
     * 前年データ種別(前年対象店)リストを設定する
     * @param zenDataZennenList 前年データ種別(前年対象店)リスト
     */
    public void setZenDataZennenList(List zenDataZennenList) {
        this.zenDataZennenList = zenDataZennenList;
    }

    /**
     * 前年データ種別(前年対象店以外)リストを取得する
     * @return List 前年データ種別(前年対象店以外)リスト
     */
    public List getZenDataZennenOthList() {
        return zenDataZennenOthList;
    }

    /**
     * 前年データ種別(前年対象店以外)リストを設定する
     * @param zenDataZennenOthList 前年データ種別(前年対象店以外)リスト
     */
    public void setZenDataZennenOthList(List zenDataZennenOthList) {
        this.zenDataZennenOthList = zenDataZennenOthList;
    }

// ログインユーザ情報
    /**
     * ログインユーザ情報
     */
    private BirdUserInfo birdUserInfo;
    
    /**
     * 個店ポータルアクセスフラグ
     */
    private boolean portalAccessFlg;

//  getter/setter
    /**
     * ログインユーザ情報取得する
     * @return BirdUserInfo ログインユーザ情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ログインユーザ情報を設定する
     * @param birdUserInfo ログインユーザ情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

//	ウィインドウ管理
	/**
	 * ウィインドウID
	 */
	private int windowId;

	/**
	 * MAXウィインドウID
	 */
	private int maxWindowId;
	
//	ウィンドウ管理getter/setter
	/**
	 * ウィンドウIDを取得する
	 * @return ウィインドウID
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * ウィンドウIDを設定する
	 * @param windowId ウィンドウID
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	/**
	 * MAXウィンドウIDを取得する
	 * @return MAXウィンドウID
	 */
	public int getMaxWindowId() {
		return maxWindowId;
	}

	/**
	 * MAXウィンドウIDを設定する
	 * @param maxWindowId MAXウィンドウID
	 */
	public void setMaxWindowId(int maxWindowId) {
		this.maxWindowId = maxWindowId;
	}


	/**
	 * ウィンドウIDを生成する
	 */ 
	public int updateWindowId() {
		int windowId = createWindowId();
		this.setWindowId(windowId);
		return windowId;
	}

	/**
	 * MAXウィンドウIDを生成する
     * @return int  MAXウィンドウID
	 */ 
	public int createWindowId() {
		int newWindowId = getMaxWindowId() + 1;
		setMaxWindowId(newWindowId);
		return newWindowId;
	}

	public boolean isPortalAccessFlg() {
		return portalAccessFlg;
	}

	public void setPortalAccessFlg(boolean portalAccessFlg) {
		this.portalAccessFlg = portalAccessFlg;
	}

    /**
     * 営業月報抽出用の期間指定年月(yyyy/MM)リストを設定する
     * ※前月以前14ヵ月。
     * 
     * @param kikanGepoYMList 期間指定年月(yyyy/MM)リスト
     */
    public void setKikanGepoYMList(List kikanGepoYMList) {
        this.kikanGepoYMList = kikanGepoYMList;
    }
    
    /**
     * 営業月報抽出用の期間指定年月(yyyy/MM)リストを取得する
     * ※前月以前14ヵ月。
     * 
     * @return List 期間指定年月(yyyy/MM)リスト
     */
    public List getKikanGepoYMList() {
        return kikanGepoYMList;
    }
    private BirdDateInfo birdDataInfo;
    /**
     * CTL【e-mossles日付情報】取得処理
     * @return
     */
    public BirdDateInfo getBirdDateInfo() {
        return this.birdDataInfo;
    }
    /**
     * CTL【e-mossles日付情報】設定処理
     * @param birdDataInfo
     */
    public void setBirdDataInfo(BirdDateInfo birdDataInfo) {
        this.birdDataInfo = birdDataInfo;
    }
    /**
     * ログインユーザータイプコード取得処理
     * @return
     */
    public String getUserTypeCd() {
    	return getBirdUserInfo().getMstUser().getUserTypeCd();
    }
    /**
     * 
     * @param windowId
     * @param mapData
     */
    public int initialize(Map mapData) {
    	//新ウィンドウIDを生成します。
    	int newWindowId = createWindowId();        
        // 会社リスト
        setCompanyList
            ((List) mapData.get(BizReportConstants.COMPANY_INFO_LIST));
        // 対象期間リスト
//        getGroupEigyoNipoRefDto().setTaishoKikanList
//            ((List) mapData.get(BizReportConstants.TAISHO_KIKAN_LIST));
        setTaishoKikanList
            ((List) mapData.get(BizReportConstants.TAISHO_KIKAN_LIST));
        // 期間指定年月日FROMリスト
        setKikanFromList
            ((List) mapData.get(BizReportConstants.KIKAN_YMD_LIST));
        // 期間指定年月日TOリスト
        setKikanToList
            ((List) mapData.get(BizReportConstants.KIKAN_YMD_LIST));
        // 期間指定年月リスト
        setKikanYMList
            ((List) mapData.get(BizReportConstants.KIKAN_YM_LIST));
        // 期間指定当月リスト
        setKikanCurrMonth
            ((String) mapData.get(BizReportConstants.KIKAN_CURR_MONTH));      
        
        // ユーザータイプが本部の場合
        if (UserType.isHonbu(getUserTypeCd())) {
            // 店舗種別リス
        	setTenpoShubetuList
                ((List) mapData.get(BizReportConstants.TENPO_SHUBETU_LIST));
            // 集計区分リスト
        	setShukeiKbnList
                ((List) mapData.get(BizReportConstants.SHUKEI_KBN_LIST));
            // 対象店舗リスト
        	setTaishoTenpoList
                ((List) mapData.get(BizReportConstants.TAISHO_TENPO_LIST));
            // 期間指定：期別期間年リスト
        	setKikanYearList
                ((List) mapData.get(BizReportConstants.KIKAN_Y_LIST));
            // 期間指定：期別期間期別リスト
        	setKikanKibetuList
                ((List) mapData.get(BizReportConstants.KIKAN_KIBETU_LIST));
            // 前年データ種別：前年対象店
        	setZenDataZennenList
                ((List) mapData.get(BizReportConstants.ZEN_DATA_LIST));
            // 前年データ種別：前年対象店以外
        	setZenDataZennenOthList
                ((List) mapData.get(BizReportConstants.ZEN_DATA_OTH_LIST));
            // ユーザータイプがオーナーの場合
        } else if (UserType.ONER.equals(getUserTypeCd())) {
            // 前年データ種別
        	setZenDataZennenOthList
                ((List) mapData.get(BizReportConstants.ZEN_DATA_ONER_LIST));
            //営業月報抽出用
            setListOnerTaishoKikan();
            //期間指定年月(yyyy/MM)リスト取得 前月以前14ヵ月を取得。
            setKikanGepoYMList
                ((List) mapData.get(BizReportConstants.KIKAN_GEPO_YM_LIST));
        }
        // 個店ポータルアクセスフラグ
        setPortalAccessFlg(
            ((Boolean) mapData.get(BizReportConstants.PORTAL_ACCESS_FLG)).booleanValue());
        // MOSCARDメニューアクセス権限フラグ
        setAccessMoscard(
        	((Boolean) mapData.get(ConditionLogicImpl.MOSCARD_ACCESS_FLG)).booleanValue());
        //ネット注文メニューアクセス権限フラグ
        setAccessNetorder(
        	((Boolean) mapData.get(ConditionLogicImpl.NETORDER_ACCESS_FLG)).booleanValue());
        setWindowId(newWindowId);
        //新ウィンドウIDをリターンします。
        return newWindowId;
    }
    
    /**
     * オーナー用対象期間情報リスト
     * 営業日報専用[2013/02現在]
     * ※：オーナー以外は日報共通DTO【条件部情報】対象期間情報リストを使用します。
     */
    private List listOnerTaishoKikan;
    /**
     * 期間指定:営業月報抽出
     */
    private Map kikanGepoYM = new HashMap();   
    
/** 2008/10/6 追加 start *****************************************************/

    /**
     * 期間指定:営業月報抽出用の期間指定年月(yyyy/MM)を取得
     * @return 期間指定:営業月報抽出
     */
    public String getKikanGepoYM() {
        return (String) kikanGepoYM.get(new Integer(getWindowId()));
    }

    /**
     * 期間指定:業月報抽出用の期間指定年月(yyyy/MM)を設定する
     * @param kikanGepoYM 期間指定:営業月報抽出
     */
    public void setKikanGepoYM(String kikanGepoYM) {
        this.kikanGepoYM.put(new Integer(getWindowId()), kikanGepoYM);
    }
    
	/**
	 * @return クラス変数listOnerTaishoKikan を戻します。
	 */
	public List getListOnerTaishoKikan() {
		return listOnerTaishoKikan;
	}

	/**
	 * @param taishoKikanList を クラス変数taishoKikanListへ設定します。
	 */
	public void setListOnerTaishoKikan() {
        //対象期間リストの最後に「営業月報抽出」を追加。
        //宅配日報、屋号日報のプルダウンには出力しない為、個別に追加。
        List listOnerTaishoKikan = new ArrayList();
        if(!getTaishoKikanList().isEmpty()) {
            for (int i=0; i<getTaishoKikanList().size(); i++){
            	listOnerTaishoKikan.add(getTaishoKikanList().get(i));
            }
            SelectItem item = new SelectItem(
            		BizReportConstants.CODE_GEPOCSV[0][0], BizReportConstants.CODE_GEPOCSV[0][1]);
            listOnerTaishoKikan.add(item);                  
    	}
		this.listOnerTaishoKikan = listOnerTaishoKikan;
	}
    /**
     * 会社名称を取得する
     * @param dto 宅配売上日報 条件部情報DTO
     * @return String 会社名称
     */
    public String getCompanyName(NipoRefConditionParameterDto dto) {
    	CodCompany eCompany = getEntityCodCompany(dto);
        return CommonUtil.isNull(eCompany.getCompanyName())?BizReportConstants.EMPTY:eCompany.getCompanyName().trim();
    }
    /**
     * 会社名称を取得する
     * @param dto 宅配売上日報 条件部情報DTO
     * @return String 会社名称
     */
    public CodCompany getEntityCodCompany(NipoRefConditionParameterDto dto) {
    	if(getCompanyList() != null) {
	        for (Iterator it = getCompanyList().iterator(); it.hasNext();) {
	        	CodCompany info = (CodCompany) it.next();
	            if (dto.getCompanyCd().equals(info.getCompanyCd())) {
	            	if (!CommonUtil.isNull(info.getCompanyName())) {
	            		return info;
	            	}
	            	break;
	            }
	        }
    	}
        return new CodCompany();
    }
    /**
     * 対象支部リスト
     */
    private Map taishoSibuList  = new HashMap();
    
    public Map getMapTaishoSibuList() {
    	return taishoSibuList;
    }
	/**
	 * 対象支部リストを取得する<br>
	 * @return List 対象支部リスト
	 */
	public List getTaishoSibuList(String key) {
		return (List) taishoSibuList.get(key);
	}

	/**
	 * 対象支部リストを設定する<br>
	 * @param taishoSibuList 対象支部リスト
	 */
	public void setTaishoSibuList(String key, List taishoSibuList) {
		this.taishoSibuList.put(key, taishoSibuList);
	}

	/**
	 * @return クラス変数kikanCurrMonth を戻します。
	 */
	public String getKikanCurrMonth() {
		return kikanCurrMonth;
	}

	/**
	 * @param kikanCurrMonth を クラス変数kikanCurrMonthへ設定します。
	 */
	public void setKikanCurrMonth(String kikanCurrMonth) {
		this.kikanCurrMonth = kikanCurrMonth;
	}

	/**
	 * DTO【検索条件情報】
	 * @return クラス変数resultParameterDto を戻します。
	 */
	public NipoRefConditionParameterDto getResultParameterDto(int windowId) {
		return (NipoRefConditionParameterDto) mapSearchParameter.get(new Integer(windowId));
	}

	/**
	 * DTO【検索条件情報】設定処理
	 * @param resultParameterDto
	 */
	public void setResultParameterDto(	NipoRefConditionParameterDto resultParameterDto) {
		this.mapSearchParameter.put(new Integer(resultParameterDto.getWindowId()), resultParameterDto);
	}

	/**
	 * DTO【検索結果条件情報】取得処理
	 * 
	 * @param windowId
	 * @return
	 */
	public NipoRefConditionParameterDto getSearchParameterDto(int windowId) {
		return (NipoRefConditionParameterDto) mapResultParameter.get(new Integer(windowId));
	}

	/**
	 * DTO【検索結果条件情報】設定処理
	 * @param searchParameterDto
	 */
	public void setSearchParameterDto(NipoRefConditionParameterDto searchParameterDto) {
		this.mapResultParameter.put(new Integer(searchParameterDto.getWindowId()), searchParameterDto);
	}
	public void copyDefaultParamerter(NipoRefConditionParameterDto paramDto) {
		//会社コード設定
		CodCompany eCompany = (CodCompany)getCompanyList().get(0);
		paramDto.setCodCompany(eCompany);
		paramDto.setCompanyCd((String)eCompany.getCompanyCd());
		paramDto.setCompanyName((String)eCompany.getCompanyName());
		//店舗種別
		paramDto.setTenpoShubetuCd(TenpoShubetu.CODE_ALL);
		//対象期間
		SelectItem item = (SelectItem)getTaishoKikanList().get(0);
		paramDto.setTaishoKikanCd((String)item.getValue());
		item = (SelectItem)getZenDataZennenOthList().get(0);
		paramDto.setZenDataZennenOthCd((String)item.getValue());
		if(UserType.isHonbu(getUserTypeCd())) {
			//集計区分
			paramDto.setShukeiKbnCd(ShukeiKbn.OUT_RC);
			//対象店舗
			item = (SelectItem)getTaishoTenpoList().get(0);
			paramDto.setTaishoTenpoCd((String)item.getValue());
			//前年データ種別(前年対象店用)
			item = (SelectItem)getZenDataZennenList().get(0);
			paramDto.setZenDataZennenCd((String)item.getValue());
		}
		paramDto.setSubTagKbn(BizReportConstants.SUB_TAG_0);
	}

	/**
	 * @return クラス変数isAccessMoscard を戻します。
	 */
	public boolean isAccessMoscard() {
		return isAccessMoscard;
	}

	/**
	 * @param isAccessMoscard を クラス変数isAccessMoscardへ設定します。
	 */
	public void setAccessMoscard(boolean isAccessMoscard) {
		this.isAccessMoscard = isAccessMoscard;
	}
	
	/**
	 * @return クラス変数isAccessNetorder を戻します。
	 */
	public boolean isAccessNetorder() {
		return isAccessNetorder;
	}

	/**
	 * @param isAccessNetorder を クラス変数isAccessNetorderへ設定します。
	 */
	public void setAccessNetorder(boolean isAccessNetorder) {
		this.isAccessNetorder = isAccessNetorder;
	}
}