
/*
 * 作成日: 2006/02/17
 */
package jp.co.isid.mos.bird.storeinfo.miseref.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserMise;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.storeinfo.miseref.action.MiseRefCondAction;
import jp.co.isid.mos.bird.storeinfo.miseref.dto.MiseRefDto;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.CodCompanyJoho;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.CompanyJohoLogic;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.KotenJohoLogic;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.KotenLinkJohoLogic;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.SchMiseListLogic;

/**
 * 個店ポータル画面アクション
 * @author xnkusama
 */
public class MiseRefCondActionImpl implements CommonAction, MiseRefCondAction {

    /* アクションID */
    public static final String initialize_ACTION_ID   = "BSI001A01";
    public static final String callMiseForm_ACTION_ID = "BSI001A02";
    public static final String execute_ACTION_ID      = "BSI001A03";
    /* ビューID */
    private static final String VIEWID_CONDITION  = "BSI001V01"; //条件画面
    private static final String VIEWID_RESULT     = "BSI001V02"; //結果画面
    private static final String VIEWID_MISESEARCH = "BCO008V01"; //店検索

    /*【ロジック】*/
    //ユーザー所属管理会社の検索
    private CompanyJohoLogic companyJohoLogic;
    //店リストの検索
    private SchMiseListLogic schMiseListLogic;
    //個店情報の検索
    private KotenJohoLogic kotenJohoLogic;
    //個店リンク情報の取得
    private KotenLinkJohoLogic kotenLinkJohoLogic;
    
    /*【DTO】*/
    private MiseRefDto miseRefDto;
    private MiseSearchDto miseSearchDto;
    private PullDownMenuDto pullDownMenuDto;
    
//    /**
//     * 初期処理
//     * @return
//     */
//    public String initialize() {
//    	
//        if (getPullDownMenuDto().isClearFlg()) {
//            getMiseRefDto().setCondCompanyCd("");
//            getMiseRefDto().setCondMiseCd("");
//            getMiseRefDto().setMstMise(null);
//            getPullDownMenuDto().setClearFlg(false);
//        }
//        
//        // 
//        getMiseRefDto().setFlgCondToEdit(false);
//        
//        // ロジック【ユーザー所属管理会社の検索】
//        List listCompany = getCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
//        getMiseRefDto().setListCompany(listCompany);
//        
//        // 店検索戻り値のセット
//        if (getMiseSearchDto().isActionFlg()) {
//            getMiseRefDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
//            getMiseSearchDto().setActionFlg(false);
//        }
//        return null;
//    }
    
    private void setPullData(String userTypeCd){
        
        if (userTypeCd.equals("02")) {
            // ユーザタイプ「オーナー」の場合
            List listUserOner = getBirdUserInfo().getUserOner();
            
            UIUserOner uIUserOner = null;
            String companyCd  = "";
            List listPullMise = null;
            for(Iterator ite = listUserOner.iterator(); ite.hasNext();){
                uIUserOner = (UIUserOner) ite.next();
                companyCd  = uIUserOner.getCompanyCd();
                // ロジック【店リストの検索ロジック】
                listPullMise = 
                    getSchMiseListLogic().execute(companyCd, uIUserOner.getOnerCd());
                
                if(companyCd.equals("00")){
                    getMiseRefDto().setListMiseMos(listPullMise);
                    
                } else if (companyCd.equals("60")){
                    getMiseRefDto().setListMiseTomos(listPullMise);
                    
                } else if (companyCd.equals("70")){
                    getMiseRefDto().setListMiseSikina(listPullMise);
                }
            }
            
            listPullMise = null;
            companyCd  = getMiseRefDto().getCondCompanyCd();
//            companyCd  = ((CodCompanyJoho) listCompany.get(0)).getCompanyCd();
            if(companyCd.equals("00")){
                listPullMise = getMiseRefDto().getListMiseMos();
                
            } else if (companyCd.equals("60")){
                listPullMise = getMiseRefDto().getListMiseTomos();
                
            } else if (companyCd.equals("70")){
                listPullMise = getMiseRefDto().getListMiseSikina();
            }
            getMiseRefDto().setListPullMise(listPullMise);
            
        } else if (userTypeCd.equals("03")) {
            // ユーザタイプ「店舗」の場合
            List listUserMise = getBirdUserInfo().getUserMise();
            getMiseRefDto().setListUserMise(listUserMise);
            getMiseRefDto().setCondCompanyCd(((UIUserMise)listUserMise.get(0)).getCompanyCd());
            getMiseRefDto().setCondMiseCd(((UIUserMise)listUserMise.get(0)).getMiseCd());
        }
    }
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        String userTypeCd = "";
        
        // ロジック【ユーザー所属管理会社の検索】
        List listCompany = getCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
        getMiseRefDto().setListCompany(listCompany);
             
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        //ログインユーザ情報取得
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
        .getAttribute("birdUserInfo");
     
        if (getPullDownMenuDto().isClearFlg()) {
            getMiseRefDto().setCondCompanyCd("");
            getMiseRefDto().setCondMiseCd("");
            getMiseRefDto().setMstMise(null);
            getPullDownMenuDto().setClearFlg(false);
            
            // 20060607 追加 start ---------------------------------
            getMiseRefDto().setListPullMise(null);
            //ユーザタイプDTOにセット
            userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            getMiseRefDto().setUserTypeCd(userTypeCd);
            
            getMiseRefDto().setCondCompanyCd(((CodCompanyJoho) listCompany.get(0)).getCompanyCd());
            //条件画面プルダウンデータセット
            setPullData(userTypeCd);
            
            if(userTypeCd.equals("03")){
                // ユーザタイプ「店舗」の場合
                return execute();
            }         
            //20060607 追加 end -------------------------------------
        }
        
        getMiseRefDto().setFlgCondToEdit(false);
      
        // 店検索戻り値のセット
        if (getMiseSearchDto().isActionFlg()) {
            getMiseRefDto().setCondMiseCd(getMiseSearchDto().getMiseCd());
            getMiseSearchDto().setActionFlg(false);

            //20061121 ダイレクト結果表示対応
            return execute();
        }
                
        //自画面へ遷移
        return null;
    }
    
    /**
     * 店リスト読込処理
     * @return 画面遷移情報
     */
    public String loadMiseList() {
        List listPullMise = null;
        
        getMiseRefDto().setListPullMise(null);
        
        String companyCd = getMiseRefDto().getCondCompanyCd();
        if(companyCd.equals("00")){
            listPullMise = getMiseRefDto().getListMiseMos();
            
            
        } else if (companyCd.equals("60")){
            listPullMise = getMiseRefDto().getListMiseTomos();
            
        } else if (companyCd.equals("70")){
            listPullMise = getMiseRefDto().getListMiseSikina();
            
        }
        
        getMiseRefDto().setListPullMise(listPullMise);

        return null;
    }
    

    /**
     * 検索条件より個店情報を検索する処理
     * @return 画面遷移情報(結果画面)
     */
    public String execute() throws ApplicationException {

        //検索条件を取得
        String condCompanyCd = getMiseRefDto().getCondCompanyCd();
        String condMiseCd    = getMiseRefDto().getCondMiseCd();

        //---------------------------------
        // ロジック【個店情報の検索】実行
        //---------------------------------
        Map mapKotenJoho = getKotenJohoLogic().execute(condMiseCd, condCompanyCd);
        
        //検索結果をDTOにセット
        getMiseRefDto().setMapMiseInfo(mapKotenJoho);

        
        //---------------------------------
        // ロジック【個店リンク情報の取得】実行
        //---------------------------------
        List listKotenLink = getKotenLinkJohoLogic().execute(getBirdUserInfo().getUserID());

        //検索結果をDTOにセット
        getMiseRefDto().setListKotenLinkJoho(listKotenLink);

        
        //条件画面 --> 編集画面の遷移フラグを立てる
        getMiseRefDto().setFlgCondToEdit(true);

        //検索条件を退避
        getMiseRefDto().setSearchedCondCompanyCd(condCompanyCd);
        getMiseRefDto().setSearchedCondMiseCd(condMiseCd);

        //検索条件の会社コードに対応する会社名称を取得
        List listPullCompany = getMiseRefDto().getListCompany();
        if (listPullCompany != null) {
            for (int i = 0; i < listPullCompany.size(); i++) {
                CodCompanyJoho codCompanyJoho = (CodCompanyJoho) listPullCompany.get(i);
                if (condCompanyCd.equals(codCompanyJoho.getCompanyCd())) {

                    //会社名称
                    String condCompanyName = codCompanyJoho.getCompanyName();

                    //DTOにセット
                    getMiseRefDto().setSearchedCondCompanyName(condCompanyName);
                    break;
                }
            }
        }
        //結果画面へ遷移
        return VIEWID_RESULT;
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
        listCompany.add(getMiseRefDto().getCondCompanyCd());
        miseSearchDto.setRCompanyCdList(listCompany);
        return VIEWID_MISESEARCH;
    }    
        
    /**
     * 関連リンクアクション
     * @author xnkusama
     */
    public String startOutLink() {
    	//TODO 外部リンク実装
    	return null;
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
     * @return schMiseListLogic を戻します。
     */
    public SchMiseListLogic getSchMiseListLogic() {
        return schMiseListLogic;
    }
    /**
     * @param schMiseListLogic schMiseListLogic を設定。
     */
    public void setSchMiseListLogic(SchMiseListLogic schMiseListLogic) {
        this.schMiseListLogic = schMiseListLogic;
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
	public MiseRefDto getMiseRefDto() {
		return miseRefDto;
	}
	public void setMiseRefDto(MiseRefDto miseRefDto) {
		this.miseRefDto = miseRefDto;
	}

	public KotenLinkJohoLogic getKotenLinkJohoLogic() {
		return kotenLinkJohoLogic;
	}
	public void setKotenLinkJohoLogic(KotenLinkJohoLogic kotenLinkJohoLogic) {
		this.kotenLinkJohoLogic = kotenLinkJohoLogic;
	}
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
}