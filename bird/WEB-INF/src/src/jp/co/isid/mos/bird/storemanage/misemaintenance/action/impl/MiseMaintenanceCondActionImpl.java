
/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storemanage.misemaintenance.action.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.storemanage.misemaintenance.action.MiseMaintenanceCondAction;
import jp.co.isid.mos.bird.storemanage.misemaintenance.dto.MiseMaintenanceDto;
import jp.co.isid.mos.bird.storemanage.misemaintenance.entity.CodCompanyJoho;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.storemanage.misemaintenance.logic.KotenJohoLogic;

/**
 * 個店情報メンテナンス画面アクション
 * @author xnkusama
 */
public class MiseMaintenanceCondActionImpl implements CommonAction, MiseMaintenanceCondAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BSM001A01";
    public static String callMiseForm_ACTION_ID = "BSM001A02";
    public static String execute_ACTION_ID      = "BSM001A03";
    /* ビューID */
    private static final String VIEWID_CONDITION    = "BSM001V01";
    private static final String VIEWID_EDIT_MOS     = "BSM001V11"; //編集画面（モス）
    private static final String VIEWID_EDIT_OTHER   = "BSM001V12"; //編集画面（その他）
    private static final String VIEWID_MISESEARCH   = "BCO008V01"; //店選択

    /*【ロジック】*/
    //ユーザー所属管理会社の検索
    private CompanyJohoLogic companyJohoLogic;
    //個店情報の検索
    private KotenJohoLogic kotenJohoLogic;
    /*【DTO】*/
    private MiseMaintenanceDto miseMaintenanceDto;
    private MiseSearchDto miseSearchDto;
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        if (getPullDownMenuDto().isClearFlg()) {
            getMiseMaintenanceDto().setCondCompanyCd("");
            getMiseMaintenanceDto().setCondMiseCd("");
            getPullDownMenuDto().setClearFlg(false);
        }
        // ロジック【ユーザー所属管理会社の検索】
        List listCompany = getCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
        getMiseMaintenanceDto().setListCompany(listCompany);
        
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            getMiseMaintenanceDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);
        }
        return null;
    }
    

    /**
     * 実行処理
     * @return  
     */
    public String execute() {
        // ロジック【個店情報の検索】
        Map mapKotenJoho = getKotenJohoLogic().execute(getMiseMaintenanceDto().getCondMiseCd(),
                                                       getMiseMaintenanceDto().getCondCompanyCd());
        getMiseMaintenanceDto().setMapMiseInfo(mapKotenJoho);
        
        // 条件画面-->編集画面の遷移フラグを立てる
        getMiseMaintenanceDto().setFlgCondToEdit(true);
        
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
            return VIEWID_EDIT_MOS;
        }
        else {
            return VIEWID_EDIT_OTHER;
        }
        
    }
    
    /**
     * 選択処理
     * @return 
     */
    public String callMiseForm() {
        MiseSearchDto miseSearchDto = getMiseSearchDto();
        miseSearchDto.setNavigationCase(VIEWID_CONDITION);
        miseSearchDto.setInitialFlag(true);
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getMiseMaintenanceDto().getCondCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        return VIEWID_MISESEARCH;
    }
    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
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
     * @return pullDownMenuDto を戻します。
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
}