
/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.action.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storemanage.misemaintenance.action.MiseMaintenanceEditAction;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodCompanyJoho;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstBukken;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstChintai;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMise;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseKaiso;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstMiseYobi;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstSB;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstTO;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.TrnURL;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.CheckContentsLogic;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.GetEditPulldownInfoLogic;

/**
 * 個店情報メンテナンス画面アクション
 * @author xnkusama
 */
public class MiseMaintenanceEditActionImpl implements CommonAction, MiseMaintenanceEditAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BSM001A04";
    public static String execute_ACTION_ID      = "BSM001A05";
    public static String back_ACTION_ID         = "BSM001A06";
    public static String changeTab_ACTION_ID    = "BSM001A07";
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BSM001V01";
    private static final String VIEWID_CONFIRM_MOS  = "BSM001V21";
    private static final String VIEWID_CONFIRM_OTHER= "BSM001V22";
    
    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    // 選択タブIndex
    private int tabIndex;
    /*【ロジック】*/
    private CheckContentsLogic checkContentsLogic;
    private GetEditPulldownInfoLogic getEditPulldownInfoLogic;
    /*【DTO】*/
    private MiseMaintenanceDto miseMaintenanceDto;
    
    /* 編集画面表示時のタブ指定 
     * デフォルト*/
    private int selectedTab;
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        // 条件画面から遷移時の処理
        if (getMiseMaintenanceDto().isFlgCondToEdit()) {
            //Entityセット
            Map map = getMiseMaintenanceDto().getMapMiseInfo();
            //編集用EntityをDTOへセット
            setEntity(map);
            // 初期値セット
            getMiseMaintenanceDto().setUserId(getBirdUserInfo().getUserID());  
            
            // タブの初期化
            getMiseMaintenanceDto().setSelectedTab(0);
            //ロジック【編集画面用プルダウン情報取得】を実行する。
            getGetEditPulldownInfoLogic().execute(getMiseMaintenanceDto());
        }
        
        // 
        getMiseMaintenanceDto().setFlgCondToEdit(false);
        return null;
    }
    

    /**
     * 実行処理
     * @return  
     */
    public String execute() {
        // 入力チェック
        getCheckContentsLogic().execute(getMiseMaintenanceDto());

//20060525 追加 start -----------------------------------------------------------------------------        
        List listPullCompany = getMiseMaintenanceDto().getListCompany();
        if (listPullCompany != null) {
            for (int i = 0; i < listPullCompany.size(); i++) {
                CodCompanyJoho codCompanyJoho = (CodCompanyJoho) listPullCompany.get(i);
                if (getMiseMaintenanceDto().getCondCompanyCd().equals(codCompanyJoho.getCompanyCd())) {
                    getMiseMaintenanceDto().setSearchedCondCompanyName(codCompanyJoho.getCompanyName());
                    break;
                }
            }
        }
//20060525 追加 end ------------------------------------------------------------------------------
        
        if (getMiseMaintenanceDto().isMosCompany()) {
            return VIEWID_CONFIRM_MOS;
        }
        else {
            return VIEWID_CONFIRM_OTHER;
        }
    }
    
    /**
     * 戻る
     * @return 
     */
    public String back() {
        return VIEWID_CONDITION;
    }
    
    /**
     * 2006/05/31 追加
     * タブ切替
     * @return
     */
    public String changeTab() {
        // 編集画面表示時のタブ指定
        miseMaintenanceDto.setSelectedTab(selectedTab);
        
        return null;
    }
    
    /**
     * 2006/05/31 追加
     * 編集画面表示時のタブ指定の設定
     * @return selectedTab を戻します。
     */
    public int getSelectedTab() {
        return selectedTab;
    }
    /**
     * 2006/05/31 追加
     * 編集画面表示時のタブ指定の設定
     * @param selectedTab selectedTab を設定。
     */
    public void setSelectedTab(int selectedTab) {
        this.selectedTab = selectedTab;
    }

    /**
     * 物件・賃貸情報タブ  物件情報 行追加
     */
    public String addRowBukken() {
        List listBukken = (List) getMiseMaintenanceDto().getListBukken();
        MstBukken mstBukken = new MstBukken();
        mstBukken.setCompanyCd(getMiseMaintenanceDto().getCondCompanyCd());
        mstBukken.setMiseCd(getMiseMaintenanceDto().getCondMiseCd());
        mstBukken.setKeiyakuUpdate("");
        mstBukken.setKeiyakuOner("");
        mstBukken.setYachinZeiKbn("");
        listBukken.add(mstBukken);
        return null;
    }
    /**
     * 物件・賃貸情報タブ  物件情報 行削除
     */
    public String delRowBukken() {
        List listBukken = (List) getMiseMaintenanceDto().getListBukken();
        if (listBukken != null) {
            for (Iterator ite = listBukken.iterator(); ite.hasNext();) {
                MstBukken mstBukken = (MstBukken) ite.next();
                if (mstBukken.isDelFlg()) {
                    ite.remove();
                }
            }
        }
        return null;
    }
    
    /**
     * 物件・賃貸情報タブ  賃貸情報 行追加
     */
    public String addRowChintai() {
        List listChintai = (List) getMiseMaintenanceDto().getListChintai();
        MstChintai mstChintai = new MstChintai();
        mstChintai.setCompanyCd(getMiseMaintenanceDto().getCondCompanyCd());
        mstChintai.setMiseCd(getMiseMaintenanceDto().getCondMiseCd());
        mstChintai.setMiseLeaseShu("");
        mstChintai.setMiseLeaseStart("");
        mstChintai.setMiseLeaseEnd("");
        listChintai.add(mstChintai);
        return null;
    }
    /**
     * 物件・賃貸情報タブ  賃貸情報 行削除
     */
    public String delRowChintai() {
        List listChintai = (List) getMiseMaintenanceDto().getListChintai();
        if (listChintai != null) {
            for (Iterator ite = listChintai.iterator(); ite.hasNext();) {
                MstChintai mstChintai = (MstChintai) ite.next();
                if (mstChintai.isDelFlg()) {
                    ite.remove();
                }
            }
        }
        return null;
    }

    /**
     * 改装・T/O・S/Bタブ  店改装 行追加
     */
    public String addRowKaiso() {
        List listMiseKaiso = (List) getMiseMaintenanceDto().getListMiseKaiso();
        MstMiseKaiso mstMiseKaiso = new MstMiseKaiso();
        mstMiseKaiso.setCompanyCd(getMiseMaintenanceDto().getCondCompanyCd());
        mstMiseKaiso.setMiseCd(getMiseMaintenanceDto().getCondMiseCd());
        mstMiseKaiso.setKaisoSta("");
        mstMiseKaiso.setKaisoEnd("");
        mstMiseKaiso.setKaisoNaiyo("");
        listMiseKaiso.add(mstMiseKaiso);
        return null;
    }
    /**
     * 改装・T/O・S/Bタブ  店改装 行削除
     */
    public String delRowKaiso() {
        List listMiseKaiso = (List) getMiseMaintenanceDto().getListMiseKaiso();
        if (listMiseKaiso != null) {
            for (Iterator ite = listMiseKaiso.iterator(); ite.hasNext();) {
                MstMiseKaiso mstMiseKaiso = (MstMiseKaiso) ite.next();
                if (mstMiseKaiso.isDelFlg()) {
                    ite.remove();
                }
            }
        }
        return null;
    }

    /**
     * 改装・T/O・S/Bタブ  T/O 行追加
     */
    public String addRowTO() {
        List listTO = (List) getMiseMaintenanceDto().getListTO();
        MstTO mstTO = new MstTO();
        mstTO.setToDate("");
        mstTO.setOldOnerCd("");
        mstTO.setOldOnerName("");
        listTO.add(mstTO);
        return null;
    }
    /**
     * 改装・T/O・S/Bタブ  T/O 行削除
     */
    public String delRowTO() {
        List listTO = (List) getMiseMaintenanceDto().getListTO();
        if (listTO != null) {
            for (Iterator ite = listTO.iterator(); ite.hasNext();) {
                MstTO mstTO = (MstTO) ite.next();
                if (mstTO.isDelFlg()) {
                    ite.remove();
                }
            }
        }
        return null;
    }

    /**
     * 改装・T/O・S/Bタブ  S/B 行追加
     */
    public String addRowSB() {
        List listSB = (List) getMiseMaintenanceDto().getListSB();
        MstSB mstSB = new MstSB();
        mstSB.setSbDate("");
        mstSB.setOldMiseCd("");
        mstSB.setOldMiseName("");
        listSB.add(mstSB);
        return null;
    }
    /**
     * 改装・T/O・S/Bタブ  S/B 行削除
     */
    public String delRowSB() {
        List listSB = (List) getMiseMaintenanceDto().getListSB();
        if (listSB != null) {
            for (Iterator ite = listSB.iterator(); ite.hasNext();) {
                MstSB mstSB = (MstSB) ite.next();
                if (mstSB.isDelFlg()) {
                    ite.remove();
                }
            }
        }
        return null;
    }

    /**
     * ロジックで取得したEntityを編集用にDTOへセット
     * @param mapEntity
     */
    private void setEntity(Map mapEntity) {
        // 店基本
        List listMstMise = (List)mapEntity.get(MiseMaintenanceDto.MAPKEY_KIHON);
        MstMise mstMise;
        if (listMstMise == null || listMstMise.size() == 0) {
            mstMise = new MstMise();
        }
        else {
            mstMise = (MstMise) listMstMise.get(0);
        }
        getMiseMaintenanceDto().setMstMise(mstMise);
        // 店拡張マスタ
        List listMiseYobi = (List) mapEntity.get(MiseMaintenanceDto.MAPKEY_YOBI);
        MstMiseYobi mstMiseYobi;
        if (listMiseYobi == null || listMiseYobi.size() == 0) {
            mstMiseYobi = new MstMiseYobi();
        }
        else {
            mstMiseYobi = (MstMiseYobi) listMiseYobi.get(0);
        }
        getMiseMaintenanceDto().setMstMiseYobi(mstMiseYobi);
        // イベント実施状況
        List listMstEventStatus = (List) mapEntity.get(MiseMaintenanceDto.MAPKEY_EVENTSTATUS);
        getMiseMaintenanceDto().setListMstEventStatus(listMstEventStatus);
        // 地図URL情報
        List listTrnUrl = (List) mapEntity.get(MiseMaintenanceDto.MAPKEY_URL);
        TrnURL trnUrl;
        if (listTrnUrl == null || listTrnUrl.size() == 0) {
            trnUrl = new TrnURL();
        }
        else {
            trnUrl = (TrnURL) listTrnUrl.get(0);
        }
        getMiseMaintenanceDto().setTrnUrl(trnUrl);
    }
    
    /**
     * BIRD日付情報設定処理
     * @param birdDateInfo birdDateInfo を設定。
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * BIRDユーザー情報設定処理
     * @param birdUserInfo birdUserInfo を設定。
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    
    /**
     * @return miseMaintenanceDto を戻します。
     */
    public MiseMaintenanceDto getMiseMaintenanceDto() {
        return miseMaintenanceDto;
    }
    /**
     * @param miseMaintenanceDto miseMaintenanceDto を設定。
     */
    public void setMiseMaintenanceDto(MiseMaintenanceDto miseMaintenanceDto) {
        this.miseMaintenanceDto = miseMaintenanceDto;
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
    /**
     * @return checkContentsLogic を戻します。
     */
    public CheckContentsLogic getCheckContentsLogic() {
        return checkContentsLogic;
    }
    /**
     * @param checkContentsLogic checkContentsLogic を設定。
     */
    public void setCheckContentsLogic(CheckContentsLogic checkContentsLogic) {
        this.checkContentsLogic = checkContentsLogic;
    }
    /**
     * @return getEditPulldownInfoLogic を戻します。
     */
    public GetEditPulldownInfoLogic getGetEditPulldownInfoLogic() {
        return getEditPulldownInfoLogic;
    }
    /**
     * @param getEditPulldownInfoLogic getEditPulldownInfoLogic を設定。
     */
    public void setGetEditPulldownInfoLogic(
            GetEditPulldownInfoLogic getEditPulldownInfoLogic) {
        this.getEditPulldownInfoLogic = getEditPulldownInfoLogic;
    }
}