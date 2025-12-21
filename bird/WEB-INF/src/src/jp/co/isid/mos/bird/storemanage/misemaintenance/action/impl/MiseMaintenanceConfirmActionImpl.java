/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.storemanage.misemaintenance.action.MiseMaintenanceConfirmAction;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodAccessWay;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodAirConditioner;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodChintai;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodEventPatternCd;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodGas;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodSmoke;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodYachinZei;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstBukken;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstChintai;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.MstEventStatus;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.UpdateKotenLogic;

/**
 * 個店情報メンテナンス画面アクション
 * @author xnkusama
 * 
 * 更新日:2011/07/11 xkinu 店舗ガス種別追加対応
 */
public class MiseMaintenanceConfirmActionImpl implements CommonAction, MiseMaintenanceConfirmAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BSM001A04";
    public static String execute_ACTION_ID      = "BSM001A05";
    public static String back_ACTION_ID         = "BSM001A06";
    public static String changeTab_ACTION_ID    = "BSM001A07";
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BSM001V01";
    private static final String VIEWID_EDIT_MOS     = "BSM001V11";
    private static final String VIEWID_EDIT_OTHER   = "BSM001V12";

    /* Birdユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /* Bird日付情報 */
    private BirdDateInfo birdDateInfo;

    // 選択タブIndex
    private int tabIndex;
    /*【ロジック】*/
    private UpdateKotenLogic updateKotenLogic;
    /*【DTO】*/
    private MiseMaintenanceDto miseMaintenanceDto;
    
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
    	// プルダウン項目の名称セット
    	setPulldownName();
    	
        return null;
    }
    

    /**
     * 実行処理
     * @return  
     */
    public String execute() {
        // ロジック【個店情報の更新】を実行
        getUpdateKotenLogic().execute(getMiseMaintenanceDto());
        
        // 条件画面で選択された値をクリア
        getMiseMaintenanceDto().setCondCompanyCd("");
        getMiseMaintenanceDto().setCondMiseCd("");
        
        return VIEWID_CONDITION;
    }
    
    /**
     * 戻る
     * @return 
     */
    public String back() {
        // タブの初期化
        getMiseMaintenanceDto().setSelectedTab(0);
        
        if (getMiseMaintenanceDto().isMosCompany()) {
            return VIEWID_EDIT_MOS;
        }
        else {
            return VIEWID_EDIT_OTHER;
        }
    }
    
    /**
     * タブ切替
     * @return
     */
    public String changeTab() {
//        getMiseMaintenanceDto().setTabIndex(getTabIndex());
        getMiseMaintenanceDto().setSelectedTab(getTabIndex());
        return null;
    }

    /**
     * プルダウン名称設定処理
     * 
     * 更新日:2011/07/11 xkinu 店舗ガス種別追加対応
     *
     */
    private void setPulldownName() {
//		2011/07/11 xkinu ADD start 店舗ガス種別追加対応
    	// ガス種別
    	String gasKbn = getMiseMaintenanceDto().getMstMiseYobi().getYobiKbn1();
    	List listPullGas = getMiseMaintenanceDto().getListShubetuGas();
    	getMiseMaintenanceDto().getMstMiseYobi().setGasShuName("");
    	if (!CommonUtil.isNull(gasKbn)) {
			for (int j = 0; j < listPullGas.size(); j++) {
				CodGas eCode = (CodGas) listPullGas.get(j);
				if (eCode.getGasShu().equals(gasKbn)) {
					getMiseMaintenanceDto().getMstMiseYobi().setGasShuName(eCode.getGasShuName());
					break;
				}
			}
    	}
    	   
    	// エアコン種別
    	String airconKbn = getMiseMaintenanceDto().getMstMiseYobi().getYobiKbn2();
    	List listPullAircon = getMiseMaintenanceDto().getListShubetuAircon();
    	getMiseMaintenanceDto().getMstMiseYobi().setAirConShuName("");
    	if (!CommonUtil.isNull(airconKbn)) {
			for (int j = 0; j < listPullAircon.size(); j++) {
				CodAirConditioner eCode = (CodAirConditioner) listPullAircon.get(j);
				if (eCode.getAirConShu().equals(airconKbn)) {
					getMiseMaintenanceDto().getMstMiseYobi().setAirConShuName(eCode.getAirConShuName());
					break;
				}
			}
    	}
//    	2011/07/11 xkinu ADD end 店舗ガス種別追加対応

    	// 家賃税区分
    	List listBukken = getMiseMaintenanceDto().getListBukken();
    	List listPulldown = getMiseMaintenanceDto().getListPulldownYachin();
    	if (listBukken != null) {
    		for (int i = 0; i < listBukken.size(); i++) {
    			MstBukken entity = (MstBukken) listBukken.get(i);
    			String key = entity.getYachinZeiKbn();
    			entity.setYachinZeiKbnName("");
    			if (listPulldown != null) {
	    			for (int j = 0; j < listPulldown.size(); j++) {
	    				CodYachinZei codYachinZei = (CodYachinZei) listPulldown.get(j);
	    				if (key.equals(codYachinZei.getYachinZeiKbn())) {
	    					entity.setYachinZeiKbnName(codYachinZei.getYachinZeiKbnName());
	    					break;
	    				}
	    			}
    			}
    		}
    	}
    	// 賃貸店舗
    	List listChintai = getMiseMaintenanceDto().getListChintai();
    	List listPullChintai = getMiseMaintenanceDto().getListPulldownChintai();
    	if (listChintai != null) {
    		for (int i = 0; i < listChintai.size(); i++) {
    			MstChintai entity = (MstChintai) listChintai.get(i);
    			String key = entity.getMiseLeaseShu();
    			entity.setMiseLeaseName("");
    			if (listPullChintai != null) {
	    			for (int j = 0; j < listPullChintai.size(); j++) {
	    				CodChintai codChintai = (CodChintai) listPullChintai.get(j);
	    				if (key.equals(codChintai.getMiseLeaseShu())) {
	    					entity.setMiseLeaseName(codChintai.getMiseLeaseName());
	    					break;
	    				}
	    			}
    			}
    		}
    	}
   
    	// 分煙区分
    	String sepSmokeShu = getMiseMaintenanceDto().getMstMise().getSepSmokeShu();
    	List listPullSmoke = getMiseMaintenanceDto().getListPulldownSmoke();
    	getMiseMaintenanceDto().getMstMise().setSepSmokeShuName("");
    	if (listPullSmoke != null) {
			for (int j = 0; j < listPullSmoke.size(); j++) {
				CodSmoke codSmoke = (CodSmoke) listPullSmoke.get(j);
				if (sepSmokeShu.equals(codSmoke.getSepSmokeShu())) {
					getMiseMaintenanceDto().getMstMise().setSepSmokeShuName(codSmoke.getSepShokeShuName());
					break;
				}
			}
    	}
        
//20060519 追加 start ------------------------------------------------------------------------------
        //イベントパターンコード
        List listMstEventStatus = getMiseMaintenanceDto().getListMstEventStatus();
        List listEventPatternCd = null;
        String eventPatternCd   =  "";
        if (listMstEventStatus != null) {
            for(int i = 0; i < listMstEventStatus.size(); i++){
                MstEventStatus entity = (MstEventStatus) listMstEventStatus.get(i);
                entity.setEventPatternName("");
                eventPatternCd = entity.getEventPatternCd();
                
                listEventPatternCd = entity.getListEventPatternCd();
                if(listEventPatternCd != null){
                    for (int j = 0; j < listEventPatternCd.size(); j++) {
                        CodEventPatternCd codEventPatternCd = (CodEventPatternCd) listEventPatternCd.get(j);
                        if (eventPatternCd.equals(codEventPatternCd.getEventPatternCd())) {
                            entity.setEventPatternName(codEventPatternCd.getEventPatternName());
                            break;
                        }
                    }
                }
            }  
        }
        
        
        // 交通手段
        String accessWay = getMiseMaintenanceDto().getMstMise().getAccessWay();
        List listPullAccessWay = getMiseMaintenanceDto().getListPulldownAccessWay();
        getMiseMaintenanceDto().getMstMise().setAccessWayName("");
        if (listPullAccessWay != null) {
            for (int j = 0; j < listPullAccessWay.size(); j++) {
                CodAccessWay codAccessWay = (CodAccessWay) listPullAccessWay.get(j);
                if (accessWay.equals(codAccessWay.getAccessWay())) {
                    getMiseMaintenanceDto().getMstMise().setAccessWayName(codAccessWay.getAccessWayName());
                    break;
                }
            }
        }
        
//20060519 追加 end --------------------------------------------------------------------------------        

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
     * @return updateKotenLogic を戻します。
     */
    public UpdateKotenLogic getUpdateKotenLogic() {
        return updateKotenLogic;
    }
    /**
     * @param updateKotenLogic updateKotenLogic を設定。
     */
    public void setUpdateKotenLogic(UpdateKotenLogic updateKotenLogic) {
        this.updateKotenLogic = updateKotenLogic;
    }
}