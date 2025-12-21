package jp.co.isid.mos.bird.storeinfo.miseref.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.DownloadDto;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstGyotai;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstMise;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstMiseYobi;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.TrnURL;

/**
 * 個店情報メンテナンス
 * @author xnkusama
 */
public class MiseRefDto implements DownloadDto {

    // 店情報Mapのキー
    public static final String MAPKEY_KIHON        = "listMstMise";
    public static final String MAPKEY_KAISO        = "listMstMiseKaiso";
    public static final String MAPKEY_TO           = "listMstTO";
    public static final String MAPKEY_SB           = "listMstSB";
    public static final String MAPKEY_CHINTAI      = "listMstChintai";
    public static final String MAPKEY_EVENTSTATUS  = "listMstEventStatus";
    public static final String MAPKEY_YOBI         = "listMstMiseYobi";
    public static final String MAPKEY_BUKKEN       = "listMstBukken";
    public static final String MAPKEY_GYOTAI       = "listMstGyotai";
    public static final String MAPKEY_INSENTIVE    = "listMstInsentive";
    public static final String MAPKEY_HDC          = "listMstHDC";
    public static final String MAPKEY_INSENTIVECD  = "listCodInsentiveCd";
    public static final String MAPKEY_URL          = "listTrnURL";
    public static final String MAPKEY_EVENT_BUNRUI = "listEventBunrui";    
    //20060606 追加
    public static final String MAPKEY_STAFF        = "listMstStaff";
    
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
    //結果画面：店舗情報
    private Map mapMiseInfo = new HashMap();
    //結果画面：タブIndex
    private int tabIndex;
    //結果画面：個店リンク情報
    private List listKotenLinkJoho;

    //検索結果として表示されているデータの検索条件
    private String searchedCondMiseCd;
    private String searchedCondCompanyCd;
    private String searchedCondCompanyName;
    
    private MstMise mstMise;
    private MstMiseYobi mstMiseYobi;
    private TrnURL trnUrl;
    private List listMstEventStatus;
    //private List listChintai;
    
    // ユーザタイプコード
    private String userTypeCd;
    
    private List listUserOner;  
    private List listMiseMos;
    private List listMiseTomos;
    private List listMiseSikina;
    private List listUserMise;
    //  条件画面：店リスト
    private List listPullMise;
    
    /**
     * 条件画面-->編集画面 遷移時のクリア処理
     *
     */
    public void clearEditInit() {
        setTabIndex(0);
        setMstMise(null);
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
    /**
     * @param tabIndex tabIndex を設定。
     */
    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
//    /**
//     * @return listTab を戻します。
//     */
//    public List getListTab() {
//        List listTab = new ArrayList();
//        
//        // モス用 タブ
////20060403 CHANGE start Nakajima タブ内容分割対応
//        //listTab.add("店基本情報");
//        listTab.add("基本情報");
//        listTab.add("日付・店舗継承");
//        listTab.add("特性・営業時間");
//        listTab.add("地図・物件情報");
////20060403 CHANGE end   Nakajima タブ内容分割対応
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
    
    
    /**
     * モスorモス以外で表示するタブを切替える
     * 　HDC・他、インセンティブはモスのみ表示
     * @return
     */
/*    public boolean isViewTab(int index) {
        if (index == 7 || index == 8) {
            if (COMPANY_CD_MOS.equals(getCondCompanyCd())) {
                return false;
            }
        }
        return true;
    }
*/
    
    
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
       
        if (getMapMiseInfo() == null 
           || getMapMiseInfo().get(MAPKEY_GYOTAI) == null 
           || ((List) getMapMiseInfo().get(MAPKEY_GYOTAI)).size() == 0) {
            return null;
        }
        
        List listMstGyotai = (List) getMapMiseInfo().get(MAPKEY_GYOTAI);
        
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
    
    public List getListEventBunrui() {
    	return (List) getMapMiseInfo().get(MAPKEY_EVENT_BUNRUI);
    }
	public List getListKotenLinkJoho() {
		return listKotenLinkJoho;
	}
	public void setListKotenLinkJoho(List listKotenLinkJoho) {
		this.listKotenLinkJoho = listKotenLinkJoho;
	}
	public String getSearchedCondCompanyCd() {
		return searchedCondCompanyCd;
	}
	public void setSearchedCondCompanyCd(String searchedCondCompanyCd) {
		this.searchedCondCompanyCd = searchedCondCompanyCd;
	}
	public String getSearchedCondMiseCd() {
		return searchedCondMiseCd;
	}
	public void setSearchedCondMiseCd(String searchedCondMiseCd) {
		this.searchedCondMiseCd = searchedCondMiseCd;
	}

//20060525 追加 start -------------------------------------------------------------
    public String getSearchedCondCompanyName() {
        return searchedCondCompanyName;
    }
    public void setSearchedCondCompanyName(String searchedCondCompanyName) {
        this.searchedCondCompanyName = searchedCondCompanyName;
    }
//20060525 追加 end --------------------------------------------------------------
    
//20060606 追加 start ------------------------------------------------------------- 
    /**
     * 加盟店スタッフ情報取得処理
     * @return
     */
    public List getListStaff() {
        return (List) getMapMiseInfo().get(MAPKEY_STAFF);
    }
    
    /**
     * ユーザタイプコードの設定
     * @return userTypeCd を戻します。
     */
    public String getUserTypeCd() {
        return userTypeCd;
    }
    /**
     * ユーザタイプコードの設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
    
    /**
     * ログインユーザのユーザ対応店の設定
     * @return listUserMise を戻します。
     */
    public List getUserMise() {
        return listUserMise;
    }
    /**
     * ログインユーザのユーザ対応店の設定
     * @param listUserMise listUserMise を設定。
     */
    public void setListUserMise(List listUserMise) {
        this.listUserMise = listUserMise;
    }
    
    
    /**
     * ログインユーザのユーザ対応オーナーの設定
     * @return listUserOner を戻します。
     */
    public List getUserOner() {
        return listUserOner;
    }
    /**
     * ログインユーザのユーザ対応オーナーの設定
     * @param listUserOner listUserOner を設定。
     */
    public void setListUserOner(List listUserOner) {
        this.listUserOner = listUserOner;
    }
    
    /**
     * 条件画面の店コードプルダウン用、店リストの設定
     * @return listPullMise を戻します。
     */
    public List getListPullMise() {
        return listPullMise;
    }
    /**
     * 条件画面の店コードプルダウン用、店リストの設定
     * @param listPullOner listPullOner を設定。
     */
    public void setListPullMise(List listPullMise) {
        this.listPullMise = listPullMise;
    }
    
    
    /**
     * 条件画面の店コードプルダウン用、店リスト(モス)の設定
     * @return userTypeCd を戻します。
     */
    public List getListMiseMos() {
        return listMiseMos;
    }
    /**
     * 条件画面の店コードプルダウン用、店リスト(モス)の設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setListMiseMos(List listPullMiseMos) {
        this.listMiseMos = listPullMiseMos;
    }
    
    /**
     * 条件画面の店コードプルダウン用、店リスト(トモス)の設定
     * @return userTypeCd を戻します。
     */
    public List getListMiseTomos() {
        return listMiseTomos;
    }
    /**
     * 条件画面の店コードプルダウン用、店リスト(トモス)の設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setListMiseTomos(List listPullMiseTomos) {
        this.listMiseTomos = listPullMiseTomos;
    }
    
    /**
     * 条件画面の店コードプルダウン用、店リスト(四季菜)の設定
     * @return userTypeCd を戻します。
     */
    public List getListMiseSikina() {
        return listMiseSikina;
    }
    /**
     * 条件画面の店コードプルダウン用、店リスト(四季菜)の設定
     * @param userTypeCd userTypeCd を設定。
     */
    public void setListMiseSikina(List listPullMiseSikina) {
        this.listMiseSikina = listPullMiseSikina;
    }
    
//20060606 追加 end --------------------------------------------------------------
    /**
     * オーナー存在チェック
     * 存在する場合はtrueを返します。
     */
    public boolean isExistsOner() {
    	if(getMstMise()==null) {
    		return false;
    	}
    	return !CommonUtil.isNull(getMstMise().getOnerCd());
    }
}