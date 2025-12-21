package jp.co.isid.mos.bird.storemanage.misemaintenance.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstGyotai;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMise;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseYobi;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.TrnURL;

/**
 * 個店情報メンテナンス
 * @author xnkusama
 * 
 * 更新日:2011/07/08 xkinu ガス&エアコン種別項目追加対応
 */
public class MiseMaintenanceDto implements DownloadDto {

    // 店情報Mapのキー
    public static final String MAPKEY_KIHON = "listMstMise";
    public static final String MAPKEY_KAISO = "listMstMiseKaiso";
    public static final String MAPKEY_TO = "listMstTO";
    public static final String MAPKEY_SB = "listMstSB";
    public static final String MAPKEY_CHINTAI = "listMstChintai";
    public static final String MAPKEY_EVENTSTATUS = "listMstEventStatus";
    public static final String MAPKEY_YOBI = "listMstMiseYobi";
    public static final String MAPKEY_BUKKEN = "listMstBukken";
    public static final String MAPKEY_GYOTAI = "listMstGyotai";
    public static final String MAPKEY_INSENTIVE = "listMstInsentive";
    public static final String MAPKEY_HDC = "listMstHDC";
    public static final String MAPKEY_INSENTIVECD = "listCodInsentiveCd";
    public static final String MAPKEY_URL = "listTrnURL";
    public static final String MAPKEY_EVENT_BUNRUI = "listEventBunrui";
    
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";

    // ユーザーID
    private String userId;
    /*制御関連*/
    // 条件画面-->編集画面遷移
    private boolean flgCondToEdit;
    
    //条件画面：会社リスト
    private List listCompany;
    //条件画面：店コード
    private String condMiseCd;
    //条件画面：会社コード
    private String condCompanyCd;
    //編集画面：店舗情報
    private Map mapMiseInfo;
    //編集画面：タブIndex
    private int tabIndex;
    //編集画面：分煙種別
    private List listPulldownSmoke;
    //編集画面：家賃税区分
    private List listPulldownYachin;
    //編集画面：賃貸店舗種別
    private List listPulldownChintai;
    //編集画面：交通手段
    private List listPulldownAccessWay;

    private MstMise mstMise;
    private MstMiseYobi mstMiseYobi;
    private TrnURL trnUrl;
    private List listMstEventStatus;
    
    //検索結果として表示されているデータの検索条件
    private String searchedCondCompanyName;
    
    /* 編集画面表示時のタブ指定 デフォルト*/
    private int selectedTab;

    //編集画面プルダウン：ガス種別
    private List listShubetuGas;
    //編集画面プルダウン：エアコン種別
    private List listShubetuAircon;
    /**
     * エアコン種別プルダウンリスト取得処理
     * 
	 * @return クラス変数listShubetuAircon を戻します。
	 */
	public List getListShubetuAircon() {
		return listShubetuAircon;
	}
	/**
	 * エアコン種別プルダウンリスト設定処理
	 * 
	 * @param listShubetuAircon を クラス変数listShubetuAirconへ設定します。
	 */
	public void setListShubetuAircon(List listShubetuAircon) {
		this.listShubetuAircon = listShubetuAircon;
	}
	/**
	 * ガス種別プルダウンリスト取得処理
	 * 
	 * @return クラス変数listShubetuGas を戻します。
	 */
	public List getListShubetuGas() {
		return listShubetuGas;
	}
	/**
	 * ガス種別プルダウンリスト設定処理
	 * @param listShubetuGas を クラス変数listShubetuGasへ設定します。
	 */
	public void setListShubetuGas(List listShubetuGas) {
		this.listShubetuGas = listShubetuGas;
	}
	/**
     * @return listCompany を戻します。
     */
    public List getListCompany() {
        return listCompany;
    }
    /**
     * @param listCompany listCompany を設定。
     */
    public void setListCompany(List listCompany) {
        this.listCompany = listCompany;
    }
    /**
     * @return condCompanyCd を戻します。
     */
    public String getCondCompanyCd() {
        return condCompanyCd;
    }
    /**
     * @param condCompanyCd condCompanyCd を設定。
     */
    public void setCondCompanyCd(String condCompanyCd) {
        this.condCompanyCd = condCompanyCd;
    }
    /**
     * @return condMiseCd を戻します。
     */
    public String getCondMiseCd() {
    	CodeFormatter formatter = new CodeFormatter(5);
    	formatter.setFormatPattern("00000");
        return formatter.format(condMiseCd, true);
    }
    /**
     * @param condMiseCd condMiseCd を設定。
     */
    public void setCondMiseCd(String condMiseCd) {
        this.condMiseCd = condMiseCd;
    }
    /**
     * @return tabIndex を戻します。
     */
    public int getTabIndex() {
        return tabIndex;
    }
//    /**
//     * @param tabIndex tabIndex を設定。
//     */
//    public void setTabIndex(int tabIndex) {
//        this.tabIndex = tabIndex;
//    }
//    /**
//     * @return listTab を戻します。
//     */    
//    public List getListTab() {
//        List listTab = new ArrayList();
//        
//        // モス用 タブ
//        listTab.add("基本情報");
//        listTab.add("日付・店舗継承");
//        listTab.add("特性・営業時間");
//        listTab.add("地図・物件情報");
//        listTab.add("客席情報");
//        listTab.add("店舗契約情報");
//        listTab.add("改装・T/O・S/B");
//        listTab.add("HDC");
//        listTab.add("インセンティブ");
//        listTab.add("サービス実施状況");
//        listTab.add("その他");
//        
//        return listTab;
//    }
    
//  public List getListTab() {
//  List listTab = new ArrayList();
//  
//  // モス用 タブ
//  listTab.add("店基本情報");
//  listTab.add("客席情報");
//  listTab.add("物件・賃貸情報");
//  listTab.add("改装・T/O・S/B");
//  listTab.add("HDC・他");
//  listTab.add("インセンティブ");
//  listTab.add("サービス実施状況");
//  listTab.add("メモ");
//  
//  return listTab;
//}
    
    /**
     * モスorモス以外で表示するタブを切替える
     * 　HDC・他、インセンティブはモスのみ表示
     * @return
     */
    public boolean isViewTab(int index) {
        if (index == 4 || index == 5) {
            if (isMosCompany()) {
                return false;
            }
        }
        return true;
    }
    /**
     * モスorモス以外か判断値取得処理
     * 
     * @return true:モス　false:モス以外
     */
    public boolean isMosCompany() {
    	return COMPANY_CD_MOS.equals(getCondCompanyCd());
    }
    /**
     * @return flgCondToEdit を戻します。
     */
    public boolean isFlgCondToEdit() {
        return flgCondToEdit;
    }
    /**
     * @param flgCondToEdit flgCondToEdit を設定。
     */
    public void setFlgCondToEdit(boolean flgCondToEdit) {
        this.flgCondToEdit = flgCondToEdit;
    }
    /**
     * @return mapMiseInfo を戻します。
     */
    public Map getMapMiseInfo() {
        return mapMiseInfo;
    }
    /**
     * @param mapMiseInfo mapMiseInfo を設定。
     */
    public void setMapMiseInfo(Map mapMiseInfo) {
        this.mapMiseInfo = mapMiseInfo;
    }
    
    /**
     * 業態履歴取得処理
     * @return
     */
    public MstGyotai getMstGyotai() {
        List listMstGyotai = (List) getMapMiseInfo().get(MAPKEY_GYOTAI);
        if (listMstGyotai == null || listMstGyotai.size() == 0) {
            return null;
        }
        return (MstGyotai) listMstGyotai.get(0);
    }
    
    /**
     * 物件情報取得処理
     * @return
     */
    public List getListBukken() {
        return (List) getMapMiseInfo().get(MAPKEY_BUKKEN);
    }

    /**
     * 賃貸情報取得処理
     * @return
     */
    public List getListChintai() {
        return (List) getMapMiseInfo().get(MAPKEY_CHINTAI);
    }

    /**
     * 店改装履歴情報取得処理
     * @return
     */
    public List getListMiseKaiso() {
        return (List) getMapMiseInfo().get(MAPKEY_KAISO);
    }

    /**
     * テイクオーバー履歴情報取得処理
     * @return
     */
    public List getListTO() {
        return (List) getMapMiseInfo().get(MAPKEY_TO);
    }

    /**
     * スクラップビルド履歴情報取得処理
     * @return
     */
    public List getListSB() {
        return (List) getMapMiseInfo().get(MAPKEY_SB);
    }

    /**
     * HDC受賞履歴情報取得処理
     * @return
     */
    public List getListHDC() {
        return (List) getMapMiseInfo().get(MAPKEY_HDC);
    }

    /**
     * インセンティブ履歴取得処理
     * @return
     */
    public List getListMstInsentive() {
    	return (List) getMapMiseInfo().get(MAPKEY_INSENTIVE);
    }
    
    /**
     * インセンティブコード 情報取得処理
     * @return
     */
    public List getListCodInsentive() {
        return (List) getMapMiseInfo().get(MAPKEY_INSENTIVECD);
    } 
  

    /**
     * @param mstMiseYobi mstMiseYobi を設定。
     */
    public void setMstMiseYobi(MstMiseYobi mstMiseYobi) {
        this.mstMiseYobi = mstMiseYobi;
    }
    /**
     * @return mstMiseYobi を戻します。
     */
    public MstMiseYobi getMstMiseYobi() {
        return mstMiseYobi;
    }
    
    /**
     * サービス実施状況タブ 実施状況プルダウン用
     * @return
     */
    public List getPulldownEventStatus() {
        List list = new ArrayList();
        SelectItem item1 = new SelectItem("0", "未実施");
        list.add(item1);
        SelectItem item2 = new SelectItem("1", "実施");
        list.add(item2);
        SelectItem item3 = new SelectItem("2", "一部実施");
        list.add(item3);
        SelectItem item4 = new SelectItem("3", "未設定");
        list.add(item4);
        return list;
    }
    /**
     * @return mstEventStatus を戻します。
     */
    public List getListMstEventStatus() {
        return listMstEventStatus;
    }
    /**
     * @param mstEventStatus mstEventStatus を設定。
     */
    public void setListMstEventStatus(List mstEventStatus) {
        this.listMstEventStatus = mstEventStatus;
    }
    /**
     * @return userId を戻します。
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId userId を設定。
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return trnUrl を戻します。
     */
    public TrnURL getTrnUrl() {
        return trnUrl;
    }
    /**
     * @param trnUrl trnUrl を設定。
     */
    public void setTrnUrl(TrnURL trnUrl) {
        this.trnUrl = trnUrl;
    }
    /**
     * @return mstMise を戻します。
     */
    public MstMise getMstMise() {
        return mstMise;
    }
    /**
     * @param mstMise mstMise を設定。
     */
    public void setMstMise(MstMise mstMise) {
        this.mstMise = mstMise;
    }
    /**
     * @return listPulldownSmoke を戻します。
     */
    public List getListPulldownSmoke() {
        return listPulldownSmoke;
    }
    /**
     * @param listPulldownSmoke listPulldownSmoke を設定。
     */
    public void setListPulldownSmoke(List listPulldownSmoke) {
        this.listPulldownSmoke = listPulldownSmoke;
    }
    /**
     * @return listPulldownYachin を戻します。
     */
    public List getListPulldownYachin() {
        return listPulldownYachin;
    }
    /**
     * @param listPulldownYachin listPulldownYachin を設定。
     */
    public void setListPulldownYachin(List listPulldownYachin) {
        this.listPulldownYachin = listPulldownYachin;
    }
    /**
     * @return listPulldownChintai を戻します。
     */
    public List getListPulldownChintai() {
        return listPulldownChintai;
    }
    /**
     * @param listPulldownChintai listPulldownChintai を設定。
     */
    public void setListPulldownChintai(List listPulldownChintai) {
        this.listPulldownChintai = listPulldownChintai;
    }
    /**
     * @return listPulldownAccessWay を戻します。
     */
    public List getListPulldownAccessWay() {
        return listPulldownAccessWay;
    }
    /**
     * @param listPulldownAccessWay listPulldownAccessWay を設定。
     */
    public void setListPulldownAccessWay(List listPulldownAccessWay) {
        this.listPulldownAccessWay = listPulldownAccessWay;
    }
    
    public List getListEventBunrui() {
    	return (List) getMapMiseInfo().get(MAPKEY_EVENT_BUNRUI);
    }
    
//20060525 追加 start -------------------------------------------------------------
    public String getSearchedCondCompanyName() {
        return searchedCondCompanyName;
    }
    public void setSearchedCondCompanyName(String searchedCondCompanyName) {
        this.searchedCondCompanyName = searchedCondCompanyName;
    }
    
    /**
     * 編集画面表示時のタブ指定の設定
     * @return selectedTab を戻します。
     */
    public int getSelectedTab() {
        return selectedTab;
    }
    /**
     * 編集画面表示時のタブ指定の設定
     * @param selectedTab selectedTab を設定。
     */
    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }
//20060525 追加 end --------------------------------------------------------------
}