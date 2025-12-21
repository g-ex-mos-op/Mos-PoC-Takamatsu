/**
 * 
 */
package jp.co.isid.mos.bird.analysis.common.menubetu.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.TaishoJoken;
import jp.co.isid.mos.bird.analysis.common.menubetu.code.TenpoShubetu;
import jp.co.isid.mos.bird.analysis.common.menubetu.entity.UIAnalysis;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * メニュー別リクエスト用DTO
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public abstract class MenuBetuReqDto  implements CsvOutputDto{
    
    /** DTO【自画面Session】 */
    private MenuBetuSesDto menuBetuSesDto;
	/**
	 * リクエスト対象VIEWID
	 */
	private String viewId;
	/**
	 * リクエスト対象ブラウザのウィンドウID
	 */
	private boolean newWindowFlg;
	/**
	 * リクエスト対象ブラウザのウィンドウID
	 */
	private int windowId;
    /**
     * 検索結果リスト
     */
    private List listSearchData = new ArrayList(0);
    /**
     * 会社コード
     */ 
    private String companyCd;
    /**
     * 会社名称
     */ 
    private String companyName;
    /**
     * 店舗種別
     */ 
    private String tenpoShubetu = TenpoShubetu.CODE_ALL;
    /**
     * 対象条件
     */ 
    private String taishoJoken;
    /**
     * 表示対象
     */ 
    private String hyojiTaisho;
    /**
     * ブロックコード
     */ 
    private String blockCd;
    /**
     * 対象期間
     */
    private String taishoKikan;
    /**
     * 期間指定
     */
    private String kikanSitei;
    /**
     * 対象期間
     */
    private String taishoKikanDay;
    /**
     * 期間指定
     */
    private String kikansiteiday;
	/**
	 * リクエストCSV区分
	 */
	private String csvDataKbn;
    /**
     * タブ切り替え検索
     */
     private String tabSearchFlg = "SEACH";
    /**
     * 店舗名称
     */
    private String tenpoName;
     
	/**
	 * リクエスト対象ブラウザのウィンドウID取得処理
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}
	/**
	 * リクエスト対象ブラウザのウィンドウID設定処理
	 * @param windowId 設定する windowId。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * 別ウィンドウオープン判断フラグ
	 * 
	 * @return newWindowFlg を戻します。
	 */
	public boolean isNewWindowFlg() {
		return newWindowFlg;
	}
	/**
	 * @param newWindowFlg 設定する newWindowFlg。
	 */
	public void setNewWindowFlg(boolean newWindowFlg) {
		this.newWindowFlg = newWindowFlg;
	}
    /**
     * 検索データ存在判断処理
     * @return
     */
    public boolean isExistSearchedData() {
		return (getListSearchData() != null && getListSearchData().size()>0);
    }
	/**
	 * 表示対象名称
	 * @return hyojiTaishoName を戻します。
	 */
	public String getHyojiTaishoName() {
		if(TaishoJoken.CODE_ALL.equals(getTaishoJoken())) {
			return getTaishoJokenName();
		}
		if(UserType.HONBU.equals(getUserTypeCd())
				&& TaishoJoken.CODE_MISE.equals(getTaishoJoken())) {
	    	List listSearchData = getListSearchData();
	        if (listSearchData == null || listSearchData.isEmpty()) { 
	            return "";
	        }
	        UIAnalysis eTotalRow = ((UIAnalysis)listSearchData.get(0));
	   		return eTotalRow.getTargetNameKj();
		}
		return getMenuBetuSesDto().getHyojiTaishoName(getTaishoJoken(), getHyojiTaisho());
	}
    /**
     * 対象店舗数取得処理
     * 
     * @param windowId
     * @return
     */
    public BigDecimal getTaishoTenpoCnt() {
    	List listSearchData = getListSearchData();
        if (listSearchData == null || listSearchData.isEmpty()) { 
            return new BigDecimal("0");
        }
        UIAnalysis eTotalRow = ((UIAnalysis)listSearchData.get(0));
   		return eTotalRow.getTenpoCnt();
    }

	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}
	/**
	 * @param companyCd 設定する companyCd。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
	/**
	 * 対象条件取得祖処理
	 * @return taishoJoken を戻します。
	 */
	public String getTaishoJoken() {
		return taishoJoken;
	}

	/**
	 * 対象条件設定処理
	 * 
	 * @param taishoJoken 設定する taishoJoken。
	 */
	public void setTaishoJoken(String taishoJoken) {
		this.taishoJoken = taishoJoken;
	}
	/**
	 * 対象条件名称取得祖処理
	 * @return 対象条件名称 を戻します。
	 */
	public String getTaishoJokenName() {
		return TaishoJoken.getName(getUserTypeCd(), getTaishoJoken());
	}
	
	/**
	 * 表示対象取得処理
	 * 
	 * @return hyojiTaisho を戻します。
	 */
	public String getHyojiTaisho() {
		return hyojiTaisho;
	}

	/**
	 * 表示対象設定処理
	 * 
	 * @param hyojiTaisho 設定する hyojiTaisho。
	 */
	public void setHyojiTaisho(String hyojiTaisho) {
		this.hyojiTaisho = hyojiTaisho;
	}
	/**
	 * 会社名称の取得処理
	 * 
	 * @return companyName を戻します。
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}
	/**
	 * @param viewId 設定する viewId。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}
	/**
	 * 検索結果取得処理
	 * 
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData() {
		return listSearchData;
	}
	/**
	 * @param listSearchData 設定する listSearchData。
	 */
	public void setListSearchData(List listSearchData) {
		this.listSearchData = listSearchData;
	}
	/**
	 * @return userTypeCd を戻します。
	 */
	public String getUserTypeCd() {
		return getBirdUserInfo().getMstUser().getUserTypeCd();
	}
    
	/**
	 * @return taishoKikan を戻します。
	 */
	public String getTaishoKikan() {
		return taishoKikan;
	}

	/**
	 * @param taishoKikan を クラス変数taishoKikanへ設定します。
	 */
	public void setTaishoKikan(String taishoKikan) {
		this.taishoKikan = taishoKikan;
	}

    /**
     * ブロックコード を戻します。
     * @return ブロックコード
     */
    public String getBlockCd() {
        return blockCd;
    }

    /**
     * ブロックコード を設定します。
     * @param ブロックコード
     */
    public void setBlockCd(String blockCd) {
        this.blockCd = blockCd;
    }

    /**
     * ブロック名称 を戻します。
     * @return ブロック名称
     */
    public String getBlockName() {
        
        return  getMenuBetuSesDto().getBlockName(getBlockCd());
    }
    
    /**
     * @return kikanSitei を戻します。
     */
    public String getKikanSitei() {
        return kikanSitei;
    }
    /**
     * @param kikanSitei を クラス変数kikansiteiへ設定します。
     */
    public void setKikanSitei(String kikansitei) {
        this.kikanSitei = kikansitei;
    }
    //山内追加分
    /**
     * @return kikanSitei を戻します。
     */
    public String getKikanSiteiTo() {
        return kikansiteiday;
    }

    /**
     * @param kikanSitei を クラス変数kikansiteiへ設定します。
     */
    public void setKikanSiteiTo(String kikanSiteiDay) {
        this.kikansiteiday = kikanSiteiDay;
    }
    /**
     * @return taishoKikan を戻します。
     */
    public String getTaishoKikanDay() {
        return taishoKikanDay;
    }

    /**
     * @param taishoKikan を クラス変数taishoKikanへ設定します。
     */
    public void setTaishoKikanDay(String taishoKikanDay) {
        this.taishoKikanDay = taishoKikanDay;
    }
	/**
	 * @param companyName を クラス変数companyNameへ設定します。
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 店舗種別名称取得処理
	 * 
	 * @return
	 */
	public String getTenpoShubetuName() {
		return TenpoShubetu.getName(getTenpoShubetu());
	}

	/**
	 * @return tenpoShubetu を戻します。
	 */
	public String getTenpoShubetu() {
		return tenpoShubetu;
	}

	/**
	 * @param tenpoShubetu を クラス変数tenpoShubetuへ設定します。
	 */
	public void setTenpoShubetu(String tenpoShubetu) {
		this.tenpoShubetu = tenpoShubetu;
	}

	/**
	 * @return userId を戻します。
	 */
	public String getUserId() {
		return getBirdUserInfo().getUserID();
	}
	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return getMenuBetuSesDto().getBirdDateInfo();
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return getMenuBetuSesDto().getBirdUserInfo();
	}
	/**
	 * プルダウンリストの値を設定します。
	 * 
	 * @param sessionDto
	 */
	public abstract void setPullDownLists();
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public abstract void getSearchedDataDto();
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto(int windowId) {
		setWindowId(windowId);
		/* プルダウン情報を設定します。*/
		setPullDownLists();
		setCompanyCd(getMenuBetuSesDto().getCompanyCd(windowId));
		getSearchedDataDto();
		//検索データ設定
		setListSearchData(getMenuBetuSesDto().getListSearchData(windowId));
		
	}
	/**
	 * 検索済み条件値設定処理
	 * @param dto
	 */
	public void setSearchedData(List listSearch) {
		getMenuBetuSesDto().setCompanyCd(getWindowId(), getCompanyCd());
	    //３．DTO【自画面Session】．List[[検索結果]]へ処理１の戻り値のList[[検索結果]]を設定します。					
		getMenuBetuSesDto().setListSearchData(getWindowId(), listSearch);
	}
	/**
	 * 検索結果該当データなし対象情報を全てNullを設定します。
	 *  
	 * @param windowId
	 */
	public void setNoResultSearchedData() {
		getMenuBetuSesDto().setCompanyCd(windowId, null);		
		//検索データ設定
		getMenuBetuSesDto().removeListSearchData(windowId);
	}

	/**
	 * @return menuBetuSesDto を戻します。
	 */
	public MenuBetuSesDto getMenuBetuSesDto() {
		return menuBetuSesDto;
	}

	/**
	 * @param menuBetuSesDto を クラス変数menuBetuSesDtoへ設定します。
	 */
	public void setMenuBetuSesDto(MenuBetuSesDto menuBetuSesDto) {
		this.menuBetuSesDto = menuBetuSesDto;
	}
	/**
	 * @return csvDataKbn を戻します。
	 */
	public String getCsvDataKbn() {
		return csvDataKbn;
	}
	/**
	 * @param csvDataKbn を クラス変数csvDataKbnへ設定します。
	 */
	public void setCsvDataKbn(String csvDataKbn) {
		this.csvDataKbn = csvDataKbn;
	}
    //山内追加分
    /**
     * @return kikanSitei を戻します。
     */
    public String gettabSearchFlg() {
        return tabSearchFlg;
    }
    //山内追加
    /**
     * @param kikanSitei を クラス変数kikansiteiへ設定します。
     */
    public void settabSearchFlg(String tabSearchFlg) {
        this.tabSearchFlg = tabSearchFlg;
    }
    
    /**
     * 店舗名称取得処理(店舗ユーザーログイン時の対象店舗に使用) add 2011/2/7
     * 
     * @return tenpoName を戻します。
     */
    public String getTenpoName() {
        return tenpoName;
    }
    
    /**
     * 店舗名称取得処理(店舗ユーザーログイン時の対象店舗に使用) add 2011/2/7
     * 
     * @param tenpoName 設定する tenpoName。
     */
    public void setTenpoName(String tenpoName) {
        this.tenpoName = tenpoName;
    }
}
