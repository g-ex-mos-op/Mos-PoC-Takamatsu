/**
 *
 */
package jp.co.isid.mos.bird.bizreport.common.camp.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.MstCampDate;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.Uriage;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * キャンペーン照会画面リクエスト用DTO
 *
 * @author xkinu
 *
 */
public abstract class RequestDto implements CsvOutputDto{
	/**
	 * 自画面SessionDTO
	 */
	private SessionDto selfSessionDto;
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
     * 年度リスト
     */
    private List listsNendo = new ArrayList(0);
    /**
     * プルダウン用キャンペーンリスト
     */
    private List listsCamp = new ArrayList(0);
    /**
     * 対象条件プルダウンリスト
     */
    private List listsTaishoJoken= new ArrayList(0);
    /**
     * 表示対象プルダウンリスト
     */
    private List listsHyojiTaisho= new ArrayList(0);
    /**
     * 検索結果リスト
     */
    private List listSearchData = new ArrayList(0);
    /**
     * 会社コード
     */
    private String companyCd;
    /**
     * 年度
     */
    private String nendo;
    /**
     * キャンペーン識別番号
     */
    private String campId;
    /**
     * 店舗種別
     */
    private String tenpoShubetu;
    /**
     * 対象条件
     */
    private String taishoJoken;
    /**
     * 表示対象
     */
    private String hyojiTaisho;
    /**
     * 前年データ種別
     */
    private String zennenDataShubetu;
    /**
     * メニュー集計区分
     */
    private String menuTotaledKbn;

	/**
	 * 表示対象名称
	 * @return hyojiTaishoName を戻します。
	 */
	public String getHyojiTaishoName() {
		if(getSelfSessionDto() == null) {
			return "";
		}
		return getSelfSessionDto().getHyojiTaishoName(getCampId(), getTaishoJoken(), getHyojiTaisho());
	}

	/**
	 * @return nendo を戻します。
	 */
	public String getNendo() {
		return nendo;
	}

	/**
	 * @param nendo 設定する nendo。
	 */
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
    /**
	 * @return campId を戻します。
	 */
	public String getCampId() {
		return campId;
	}
	/**
	 * @param campId 設定する campId。
	 */
	public void setCampId(String campId) {
		this.campId = campId;
	}
	/**
	 * キャンペーンタイトル取得処理
	 * @return
	 */
	public String getCampTitle() {
		return getMstCampDate().getCampTitle();
	}
	/**
	 * キャンペーン対象期間開始日取得処理
	 * @return
	 */
	public String getCampFrom() {
		return getMstCampDate().getCampFrom();
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
	 * 店舗種別取得処理
	 *
	 * @return tenpoShubetu を戻します。
	 */
	public String getTenpoShubetu() {
		return tenpoShubetu;
	}
	/**
	 * 店舗種別名称取得処理
	 *
	 * @return tenpoShubetuName を戻します。
	 */
	public String getTenpoShubetuName() {
		return TenpoShubetu.getName(getTenpoShubetu());
	}
	/**
	 * @param tenpoShubetu 設定する tenpoShubetu。
	 */
	public void setTenpoShubetu(String tenpoShubetu) {
		this.tenpoShubetu = tenpoShubetu;
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
		return TaishoJoken.getName(getTaishoJoken());
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
	 * @return zennenDataShubetu を戻します。
	 */
	public String getZennenDataShubetu() {
		return zennenDataShubetu;
	}
	/**
	 * 前年データ種別名称取得処理
	 *
	 * @return zennenDataShubetuName を戻します。
	 */
	public String getZennenDataShubetuName() {
		return ZennenDataShubetu.getName(getZennenDataShubetu());
	}
	/**
	 * @param zennenDataShubetu 設定する zennenDataShubetu。
	 */
	public void setZennenDataShubetu(String zennenDataShubetu) {
		this.zennenDataShubetu = zennenDataShubetu;
	}
	/**
	 * @return menuTotaledKbn を戻します。
	 */
	public String getMenuTotaledKbn() {
		return menuTotaledKbn;
	}
	/**
	 * @param menuTotaledKbn 設定する menuTotaledKbn。
	 */
	public void setMenuTotaledKbn(String menuTotaledKbn) {
		this.menuTotaledKbn = menuTotaledKbn;
	}
	/**
	 * @return menuTotaledKbn を戻します。
	 */
	public String getMenuTotaledKbnName() {
		return MenuTotaledKbn.getName(getMenuTotaledKbn());
	}
	/**
	 * @return companyName を戻します。
	 */
	public String getCompanyName() {
		List listCompany = getSelfSessionDto().getListCompany();
		for(int i=0; i<listCompany.size(); i++) {
			CodCompany entity = (CodCompany)listCompany.get(i);
			if(getCampId().equals(entity.getCompanyCd())) {
				return entity.getCompanyName();
			}
		}
		return "";
	}
	/**
	 * リクエスト対象キャンペーン期間マスタエンティティ取得処理
	 *
	 * @return mstCampDate を戻します。
	 */
	public MstCampDate getMstCampDate() {
		if(getSelfSessionDto() != null && getNendo() != null){
			List listCamp = getSelfSessionDto().getListCamp(getCompanyCd(), getNendo());
			for(int i=0; i<listCamp.size(); i++) {
				MstCampDate entity = (MstCampDate)listCamp.get(i);
				if(getCampId().equals(entity.getCampId())) {
					return entity;
				}
			}
		}
		return null;
	}
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
     *
     * @param windowId
     * @return
     */
    public boolean isExistSearchedData() {
		if(getSelfSessionDto() == null) {
			return false;
		}
		return getSelfSessionDto().isExistSearchedData(getWindowId());
    }
    /**
     * 検索実行判断処理
     *
     * @param windowId
     * @return
     */
    public boolean isMustSearch() {
		if(getSelfSessionDto() == null) {
			return false;
		}
		return getSelfSessionDto().isMustSearch(getWindowId());
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
    	Uriage eTotalRow = ((Uriage)listSearchData.get(listSearchData.size()-1));
   		return eTotalRow.getTaishoTenpoCnt();
    }
    /**
     * 検索済み判断処理
     *
     * @param windowId
     * @return
     */
    public boolean isSearched() {
		if(getSelfSessionDto() == null) {
			return false;
		}
		return getSelfSessionDto().isSearched(getWindowId());
    }

	/**
	 * @return listNendo を戻します。
	 */
	public List getListsNendo() {
		return listsNendo;
	}

	/**
	 * @param listNendo 設定する listNendo。
	 */
	public void setListsNendo(List listNendo) {
		this.listsNendo = listNendo;
	}

	/**
	 * @return listsCamp を戻します。
	 */
	public List getListsCamp() {
		return listsCamp;
	}

	/**
	 * @param listsCamp 設定する listsCamp。
	 */
	public void setListsCamp(List listsCamp) {
		this.listsCamp = listsCamp;
	}
	/**
	 * @return listsHyojiTaisho を戻します。
	 */
	public List getListsHyojiTaisho() {
		return listsHyojiTaisho;
	}
	/**
	 * @param listsHyojiTaisho 設定する listsHyojiTaisho。
	 */
	public void setListsHyojiTaisho(List listsHyojiTaisho) {
		this.listsHyojiTaisho = listsHyojiTaisho;
	}
	/**
	 * @return selfSessionDto を戻します。
	 */
	public SessionDto getSelfSessionDto() {
		return selfSessionDto;
	}
	/**
	 * @param selfSessionDto 設定する selfSessionDto。
	 */
	public void setSelfSessionDto(SessionDto selfSessionDto){
		this.selfSessionDto = selfSessionDto;
	}
	/**
	 *
	 * 条件項目の表示プルダウンリストを設定します
	 *
	 * 【事前条件】：(ウィンドウID・会社コード・年度)が設定されていること
	 */
	public void setPullDownData(SessionDto selfSessionDto) {
		//自画面SessionDTOを設定します。
		setSelfSessionDto(selfSessionDto);
        //1．年度リストを設定します。
		setListsNendo(getSelfSessionDto().getListNendo(getCompanyCd()));
        //2．キャンペーンプルダウンリストを設定します。
		setListsCamp(getSelfSessionDto().getListCamp(getCompanyCd(), getNendo()));
        //3．対象条件プルダウンリストを設定します。
		setListTaishoJoken(getSelfSessionDto().getListTaishoJoken(getCompanyCd()));
	}

	/**
	 * @return listsTaishoJoken を戻します。
	 */
	public List getListTaishoJoken() {
		return listsTaishoJoken;
	}

	/**
	 * @param listsTaishoJoken 設定する listsTaishoJoken。
	 */
	public void setListTaishoJoken(List listsTaishoJoken) {
		this.listsTaishoJoken = listsTaishoJoken;
	}

}
