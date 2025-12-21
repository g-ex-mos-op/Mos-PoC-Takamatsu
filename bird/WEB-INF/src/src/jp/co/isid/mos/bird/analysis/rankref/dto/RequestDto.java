/**
 * 
 */
package jp.co.isid.mos.bird.analysis.rankref.dto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.common.menubetu.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.analysis.rankref.code.KibetuKbn;
import jp.co.isid.mos.bird.analysis.rankref.code.Rank;
import jp.co.isid.mos.bird.analysis.rankref.code.RankTarget;
import jp.co.isid.mos.bird.analysis.rankref.code.TaishoKikan;
import jp.co.isid.mos.bird.analysis.rankref.code.TenpoShubetu;
import jp.co.isid.mos.bird.analysis.rankref.util.RankRefUtil;
import jp.co.isid.mos.bird.common.entity.CodCompany;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;

/**
 * 売上ランク画面用
 * リクエスト用DTO
 * 
 * 作成日:2008/09/08
 * @author xkinu
 *
 */
public class RequestDto   implements CsvOutputDto{
    /** DTO【自画面Session】 */
    private SessionDto rankRefSesDto;
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
     * 期間指定リスト
     */
    private List listKikanSitei = new ArrayList(0);
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
    private String tenpoShubetu;
    /**
     * 前年データ種別
     */
    private String zennenDataShubetu;
    /**
     * 対象期間
     */
    private String taishoKikan;
    /**
     * 期間指定
     */
    private String kikanSitei;
    /**
     * 期別期報区分
     */
    private String kibetuKbn;
    /**
     * ランク
     */
    private String rankType;
    /**
     * ランク(対象)
     */
    private String rankTarget;

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
		return getRankRefSesDto().getBirdDateInfo();
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return getRankRefSesDto().getBirdUserInfo();
	}
	/**
	 * 初期化処理
	 * 
	 * 1.画面VIEW_IDを設定
	 * 2.ウィンドウIDの生成と設定
	 * 3.条件項目情報の設定
	 * 4.デフォルト値の設定
	 */
	public void initialaze() {
		//１．画面VIEW_IDを設定します。
		setViewId(RankRefUtil.VIEW_ID);
        //２.DTO【自画面Session】.WindowID作成処理を行い、複数ウィンドウ機能で新規WindowID設定する。
        int windowId = getRankRefSesDto().createWindowId();
        //３.DTO【自画面Request】.WindowIDへ処理3の新規WindowIDを設定します。
        setWindowId(windowId);
		//４．デフォルト値としてインデックス0番目の会社コードを設定します。
		CodCompany com = (CodCompany)(getRankRefSesDto().getListCompany().get(0));
		setCompanyCd(com.getCompanyCd());
		//５．DTO【自画面Request】.店舗種別へデフォルト値として『全店』の値を設定します。
		setTenpoShubetu(TenpoShubetu.CODE_ALL);
		//５．DTO【自画面Request】.前年データ種別へデフォルト値として『前年同月』の値を設定します。
		setZennenDataShubetu(ZennenDataShubetu.CODE_DOGETU);	
        //６．DTO【自画面Request】.List[[対象期間]]へ処理４のプルダウンリストList[[対象期間]]を設定します。
		setListKikanSitei(getRankRefSesDto().getListKikanSitei());
        //７．DTO【自画面Request】.対象期間へデフォルト値として『月別』の値を設定します。
		setTaishoKikan(TaishoKikan.MONTH);
        //８．DTO【自画面Request】.期間指定へデフォルト値としてto の値を設定します。
		UILists taishoKikanMonth = ((UILists)listKikanSitei.get(0));
        SelectItem sitem = (SelectItem)(taishoKikanMonth.getListData().get(0));
        setKikanSitei((String)sitem.getValue());
        //９．DTO【自画面Request】.ランクへデフォルト値として『ベスト１００』の値を設定します。
        setRankType(Rank.BEST_100);
        //10．DTO【自画面Request】.ランク対象へデフォルト値として『売上』の値を設定します。
        setRankTarget(RankTarget.URIAGE);
	}
	/**
	 * プルダウンリストの値を設定します。
	 * 
	 * @param sessionDto
	 */
	public void setPullDownLists() {
		setListKikanSitei(getRankRefSesDto().getListKikanSitei());
	}
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto() {
		setCompanyCd(getRankRefSesDto().getCompanyCd(getWindowId()));
		setTenpoShubetu(getRankRefSesDto().getTenpoShubetu(getWindowId()));
		setZennenDataShubetu(getRankRefSesDto().getZennenDataShubetu(getWindowId()));
		setTaishoKikan(getRankRefSesDto().getTaishoKikan(getWindowId()));
		setKikanSitei(getRankRefSesDto().getKikanSitei(getWindowId()));
		setKibetuKbn(getRankRefSesDto().getKibetuKbn(getWindowId()));
		setRankType(getRankRefSesDto().getRankType(getWindowId()));
		setRankTarget(getRankRefSesDto().getRankTarget(getWindowId()));
	}
	/**
	 * 検索済み条件値のセッション保持処理
	 * @param listSearchData
	 */
	public void setSearchedData(List listSearchData) {
	    //３．DTO【自画面Session】．List[[検索結果]]へ処理１の戻り値のList[[検索結果]]を設定します。					
		getRankRefSesDto().setListSearchData(getWindowId(), listSearchData);
		//２．指定条件値をDTO【自画面Session】へ保持します。
		getRankRefSesDto().setCompanyCd(getWindowId(), getCompanyCd());
		getRankRefSesDto().setTenpoShubetu(getWindowId(), getTenpoShubetu());
		getRankRefSesDto().setZennenDataShubetu(getWindowId(), getZennenDataShubetu());
		getRankRefSesDto().setTaishoKikan(getWindowId(), getTaishoKikan());
		getRankRefSesDto().setKikanSitei(getWindowId(), getKikanSitei());
		getRankRefSesDto().setKibetuKbn(getWindowId(), getKibetuKbn());
		getRankRefSesDto().setRankType(getWindowId(), getRankType());
		getRankRefSesDto().setRankTarget(getWindowId(), getRankTarget());
	}
	/**
	 * 検索結果該当データなし対象情報を全てNullを設定します。
	 *  
	 * @param windowId
	 */
	public void setNoResultSearchedData() {
		//検索データ設定
		getRankRefSesDto().removeListSearchData(getWindowId());
		getRankRefSesDto().setCompanyCd(getWindowId(), null);		
		getRankRefSesDto().setTenpoShubetu(getWindowId(), null);
		getRankRefSesDto().setZennenDataShubetu(getWindowId(), null);
		getRankRefSesDto().setTaishoKikan(getWindowId(), null);
		getRankRefSesDto().setKikanSitei(getWindowId(), null);
		getRankRefSesDto().setKibetuKbn(getWindowId(), null);
		getRankRefSesDto().setRankType(getWindowId(), null);
		getRankRefSesDto().setRankTarget(getWindowId(), null);
	}
	/**
	 * 指定RequestDTOへ検索済み条件値格納します。
	 */
	public void getSearchedDataDto(int windowId) {
		setWindowId(windowId);
		/* プルダウン情報を設定します。*/
		setPullDownLists();
		setCompanyCd(getRankRefSesDto().getCompanyCd(windowId));
		getSearchedDataDto();
		//検索データ設定
		setListSearchData(getRankRefSesDto().getListSearchData(windowId));
		
	}
    /**
     * 検索済み判断処理
     * @return boolean true:検索済み false:検索していない
     */
    public boolean isSearched() {
		return getRankRefSesDto().isSearched(getWindowId());
    }
    /**
     * 検索データ存在判断処理
     * @return
     */
    public boolean isExistSearchedData() {
		return (getListSearchData() != null && getListSearchData().size()>0);
    }
    /**
     * ランクプルダウンリスト情報取得処理
     * @return
     */
	public List getListRank() {
		return Rank.getPullDownList();
	}
	/**
	 * ランク対象情報保持リスト取得処理
	 * @return
	 */
	public List getListRankTarget() {
		return RankTarget.getPullDownList();
	}
	/**
	 * @return userTypeCd を戻します。
	 */
	public String getUserTypeCd() {
		return getBirdUserInfo().getMstUser().getUserTypeCd();
	}
	/**
	 * 前年データ種別取得処理
	 * @return
	 */
	public String getZennenDataShubetu() {
		return zennenDataShubetu;
	}
	/**
	 * 前年データ種別名称取得処理
	 * @return
	 */
	public String getZennenDataShubetuName() {
		return ZennenDataShubetu.getName(getZennenDataShubetu(), getUserTypeCd());
	}

	/**
	 * @return companyCd を戻します。
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * @param companyCd を クラス変数companyCdへ設定します。
	 */
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * @return companyName を戻します。
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName を クラス変数companyNameへ設定します。
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return kikanSitei を戻します。
	 */
	public String getKikanSitei() {
		return kikanSitei;
	}

	/**
	 * @param kikanSitei を クラス変数kikanSiteiへ設定します。
	 */
	public void setKikanSitei(String kikanSitei) {
		this.kikanSitei = kikanSitei;
	}

	/**
	 * @return listSearchData を戻します。
	 */
	public List getListSearchData() {
		return listSearchData;
	}

	/**
	 * @param listSearchData を クラス変数listSearchDataへ設定します。
	 */
	public void setListSearchData(List listSearchData) {
		this.listSearchData = listSearchData;
	}

	/**
	 * @return newWindowFlg を戻します。
	 */
	public boolean isNewWindowFlg() {
		return newWindowFlg;
	}

	/**
	 * @param newWindowFlg を クラス変数newWindowFlgへ設定します。
	 */
	public void setNewWindowFlg(boolean newWindowFlg) {
		this.newWindowFlg = newWindowFlg;
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
	 * @return viewId を戻します。
	 */
	public String getViewId() {
		return viewId;
	}

	/**
	 * @param viewId を クラス変数viewIdへ設定します。
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	/**
	 * @return windowId を戻します。
	 */
	public int getWindowId() {
		return windowId;
	}

	/**
	 * @param windowId を クラス変数windowIdへ設定します。
	 */
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}
	/**
	 * セッション用DTO取得処理
	 * @return
	 */
	private SessionDto getRankRefSesDto() {
		return rankRefSesDto;
	}

	/**
	 * @param rankRefSesDto を クラス変数rankRefSesDtoへ設定します。
	 */
	public void setRankRefSesDto(SessionDto rankRefSesDto) {
		this.rankRefSesDto = rankRefSesDto;
	}
	/**
	 * 店舗種別名称取得処理
	 * @return
	 */
	public String getTenpoShubetuName() {
		return TenpoShubetu.getName(tenpoShubetu);
	}
	/**
	 * @return rankTarget を戻します。
	 */
	public String getRankTarget() {
		return rankTarget;
	}
	/**
	 * @param rankTarget を クラス変数rankTargetへ設定します。
	 */
	public void setRankTarget(String rankTarget) {
		this.rankTarget = rankTarget;
	}
	/**
	 * @return rankType を戻します。
	 */
	public String getRankType() {
		return rankType;
	}
	/**
	 * @param rankType を クラス変数rankTypeへ設定します。
	 */
	public void setRankType(String rankType) {
		this.rankType = rankType;
	}
	/**
	 * ランク名称取得処理
	 * @return
	 */
	public String getRankTypeName() {
		return Rank.getName(getRankType());
	}
	/**
	 * ランク対象名称取得処理
	 * @return
	 */
	public String getRankTargetName() {
		return RankTarget.getName(getRankTarget());
	}
	/**
	 * 店舗種別プルダウン情報取得処理
	 * 
	 * @return listTenpoShubetu を戻します。
	 */
	public List getListTenpoShubetu() {
		return TenpoShubetu.getPullDownList();
	}
	/**
	 * 対象期間プルダウン情報取得処理
	 * 
	 * @return listTaishoKikan を戻します。
	 */
	public List getListTaishoKikan() {
		return TaishoKikan.getPullDownList();
	}
	/**
	 * @return listKikanSitei を戻します。
	 */
	public List getListKikanSitei() {
		return listKikanSitei;
	}
	/**
	 * @param listKikanSitei を クラス変数listKikanSiteiへ設定します。
	 */
	public void setListKikanSitei(List listKikanSitei) {
		this.listKikanSitei = listKikanSitei;
	}
	/**
	 * 期別期報プルダウンリスト情報取得処理
	 * @return
	 */
	public List getListKibetuKbn() {
		return KibetuKbn.getPullDownList();
	}
	/**
	 * @return kibetuKbn を戻します。
	 */
	public String getKibetuKbn() {
		return kibetuKbn;
	}
	/**
	 * @param kibetuKbn を クラス変数kibetuKbnへ設定します。
	 */
	public void setKibetuKbn(String kibetuKbn) {
		this.kibetuKbn = kibetuKbn;
	}
	/**
	 * @param zennenDataShubetu を クラス変数zennenDataShubetuへ設定します。
	 */
	public void setZennenDataShubetu(String zennenDataShubetu) {
		this.zennenDataShubetu = zennenDataShubetu;
	}
	/**
	 * 期別期報名称取得処理
	 * 
	 * @return
	 */
	public String getKibetuKbnName() {
		return KibetuKbn.getName(getKibetuKbn());
	}
}
