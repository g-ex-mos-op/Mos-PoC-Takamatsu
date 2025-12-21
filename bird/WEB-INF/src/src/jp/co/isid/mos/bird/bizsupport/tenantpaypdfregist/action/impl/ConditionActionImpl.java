/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.MstCategoryShubetuInfo;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.entity.TrnPayDetaileStatement;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dao.MstCategoryShubetuInfoDao;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SearchedDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.SearchLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.FileDownloadActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * テナント入金明細PDF登録画面
 * 初期画面アクション
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public class ConditionActionImpl extends FileDownloadActionImpl implements ConditionAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"01";
    /* アクションID：参照 */
    public static final String callMiseForm_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"02";
    /* アクションID：参照 */
    public static final String search_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"03";
	   /* アクションID：参照 */
    public static final String download_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"04";
    /* アクションID：新規登録 */
    public static final String callInsertForm_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"05";
    /* アクションID：編集 */
    public static final String callUpdateForm_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"06";
    /* アクションID：編集 */
    public static final String callDeleteForm_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"06";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** 共通DTO【店舗選択】*/
    private MiseSearchDto miseSearchDto;
    
    /** DTO【自画面Session】 */
    private SessionDto tenantPayPdfRegistSesDto;
    /** DTO【自画面Request】 */
    private RequestDto tenantPayPdfRegistReqDto;
    /** DTO【自画面検索結果保持】 */
    private SearchedDto tenantPayPdfRegistSearchedDto;
    /** DAO【カテゴリー種別情報】*/
    private MstCategoryShubetuInfoDao tenantPayPdfRegistMstCategoryShubetuInfoDao;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic tenantPayPdfRegistConditionLogic;
    /** LOGIC【検索結果取得】*/
    private SearchLogic tenantPayPdfRegistSearchLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //1.DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //2．複数WindowID設定
            int windowId = windowId = getTenantPayPdfRegistSesDto().updateWindowid();
            getTenantPayPdfRegistReqDto().setWindowId(windowId);
            //3．BIRD情報をDTO【自画面Session】へ設定します。
            getTenantPayPdfRegistSesDto().setBirdDateInfo(getBirdDateInfo());
            getTenantPayPdfRegistSesDto().setBirdUserInfo(getBirdUserInfo());
            //4．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            getTenantPayPdfRegistConditionLogic().execute(getTenantPayPdfRegistReqDto());       				
            //７．DTO【自画面Request】.回数へデフォルト値として空の値を設定します。
    		getTenantPayPdfRegistSearchedDto().clear();
    		getTenantPayPdfRegistSesDto().setListSearchData(null);
        }
        //２．店舗選択画面から遷移したきた場合。
        else if(MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            try {
                //1．DTO【自画面Request用】．ウィンドウIDへDTO【店舗選択】.ウィンドウIDを設定します。
                setTenantPayPdfRegistReqDto((RequestDto)getTenantPayPdfRegistSesDto().getHoldReqDto(getMiseSearchDto().getWindowId()));
                
                //2．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //2-1．DTO【自画面Request】.表示対象へ共通DTO【店舗選択】.店舗コードを設定します。
                    getTenantPayPdfRegistReqDto().setMiseCd(selectedCd);
                }
                //3.遷移前の画面で、検索済みデータを表示していた場合は、
                //  そのデータをDTO【自画面Request用】へ設定します。
                TenantPayPdfRegistUtil.setView(getTenantPayPdfRegistSearchLogic()
                		, getTenantPayPdfRegistReqDto()
                		, getTenantPayPdfRegistSearchedDto());
            }
            //4．finally処理で下記の処理を行います。
            finally {
            	//4-1.DTO【自画面Session用】．共通画面遷移時のDTO保持オブジェクトへ
            	//    DTO【店舗選択】.ウィンドウIDでnullを設定し、店選択画面遷移時に保持したDTOを削除します。
                getTenantPayPdfRegistSesDto().setHoldReqDto(getMiseSearchDto().getWindowId(), null);
                //4-2．DTO【店舗選択】.遷移区分を初期値に戻します。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //4-3．DTO【店舗選択】.クリア処理を実行します。
                getMiseSearchDto().clear();
            }
        }
        //３．処理１・２以外の場合下記の処理を行います。
        else {
        	//1.【自画面共通】表示検索データ設定処理
        	TenantPayPdfRegistUtil.setView(getTenantPayPdfRegistSearchLogic()
            		, getTenantPayPdfRegistReqDto()
            		, getTenantPayPdfRegistSearchedDto());
        }
        //４．Nullをリターンします。
        return null;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#callMiseForm()
	 */
	public String callMiseForm() {
        int windowId = getTenantPayPdfRegistReqDto().getWindowId();
        //１．共通DTO【店舗選択】遷移元情報へ初期画面VIEWIDを設定します。
        getMiseSearchDto().setNavigationCase(TenantPayPdfRegistUtil.VIEW_ID_CONDITION);
        //２．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getMiseSearchDto().setInitialFlag(true);
        //３．共通DTO【店舗選択】遷移区分要否フラグへtrueを設定します。
        getMiseSearchDto().setNeedReturnKind(true);
        //４．共通DTO【店舗選択】ウィンドウIDへDTO【自画面Request用】ウィンドウIDを設定します。
        getMiseSearchDto().setWindowId(windowId);
        //５．共通DTO【店舗選択】会社コードリストへ対象会社コードを保持したListを設定します。
        List listCompany = new ArrayList();
        listCompany.add(CommonUtil.COMPANY_CD_MOS);
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //６．現ウィンドウID の検索対象条件項目値の保管を行う。
        getTenantPayPdfRegistSesDto().setHoldReqDto(windowId, getTenantPayPdfRegistReqDto());
        //７．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISESEARCH;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#search()
	 */
	public String search() {
		//１．DTO【自画面検索対象情報】へDTO【自画面リクエスト】の検索条件をコピーします。
		getTenantPayPdfRegistSearchedDto().copySearchData(getTenantPayPdfRegistReqDto());
		//２．LOGIG【検索処理】を実行します。
		TenantPayPdfRegistUtil.search(getTenantPayPdfRegistSearchLogic()
				, getTenantPayPdfRegistSearchedDto());
		getTenantPayPdfRegistSearchedDto().setSelectedIndex(0);
		//３．Nullをリターンします。
		return null;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#callInsertForm()
	 */
	public String callInsertForm() {
		//１．DTO【自画面セッション】.登録タイプへ’新規’を設定します。
		getTenantPayPdfRegistSesDto().setRegistType(TenantPayPdfRegistUtil.REGIST_TYPE_INSERT);
		//２．DTO【自画面セッション】.[登録情報エンティティ]へnewした[入金明細PDF情報エンティティ]を設定します。
		TrnPayDetaileStatement eRegist = new TrnPayDetaileStatement();
		eRegist.setKeisanYm(getBirdDateInfo().getSysDate().substring(0,6));
		getTenantPayPdfRegistSesDto().setRegistDataEntity(eRegist);
		//３．DAO【カテゴリー種別情報】検索を実行し、取得結果をDTO【自画面セッション】.[カテゴリー種別情報]へ設定します。
		List listCategoryInfo = getTenantPayPdfRegistMstCategoryShubetuInfoDao().select(eRegist.getKeiCategory(), eRegist.getKeiShu());
		MstCategoryShubetuInfo categoryInfo = (MstCategoryShubetuInfo)listCategoryInfo.get(0);
		getTenantPayPdfRegistSesDto().setCategoryShubetuInfo(categoryInfo);
		//４．編集画面遷移VIEW_IDをリターンします。
		return TenantPayPdfRegistUtil.VIEW_ID_EDIT;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#callUpdateForm()
	 */
	public String callUpdateForm() {
		//１．DTO【自画面セッション】.登録タイプへ’更新’を設定します。
		getTenantPayPdfRegistSesDto().setRegistType(TenantPayPdfRegistUtil.REGIST_TYPE_UPDATE);
		//２．DTO【自画面セッション】.[登録情報エンティティ]へ
		//    検索結果の一覧から選択した[入金明細PDF情報エンティティ]を設定します。
		int selectedIndex = getTenantPayPdfRegistSearchedDto().getSelectedIndex();
		TrnPayDetaileStatement eRegist = (TrnPayDetaileStatement)getTenantPayPdfRegistSearchedDto().getListSearchData().get(selectedIndex);
		getTenantPayPdfRegistSesDto().setRegistDataEntity(TenantPayPdfRegistUtil.cloneEntity(eRegist));
		//DAO【カテゴリー種別情報】検索を実行し、取得結果をDTO【自画面セッション】.[カテゴリー種別情報]へ設定します。
		List listCategoryInfo = getTenantPayPdfRegistMstCategoryShubetuInfoDao().select(eRegist.getKeiCategory(), eRegist.getKeiShu());
		MstCategoryShubetuInfo categoryInfo = (MstCategoryShubetuInfo)listCategoryInfo.get(0);
		getTenantPayPdfRegistSesDto().setCategoryShubetuInfo(categoryInfo);
		//４．編集画面遷移VIEW_IDをリターンします。
		return TenantPayPdfRegistUtil.VIEW_ID_EDIT;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#callDeleteForm()
	 */
	public String callDeleteForm() {
		//１．DTO【自画面セッション】.登録タイプへ’削除’を設定します。
		getTenantPayPdfRegistSesDto().setRegistType(TenantPayPdfRegistUtil.REGIST_TYPE_DELETE);
		//２．DTO【自画面セッション】.[登録情報エンティティ]へ
		//    検索結果の一覧から選択した[入金明細PDF情報エンティティ]を設定します。
		int selectedIndex = getTenantPayPdfRegistSearchedDto().getSelectedIndex();
		TrnPayDetaileStatement eRegist = (TrnPayDetaileStatement)getTenantPayPdfRegistSearchedDto().getListSearchData().get(selectedIndex);
		getTenantPayPdfRegistSesDto().setRegistDataEntity(eRegist);
		//３．確認画面遷移VIEW_IDをリターンします。
		return TenantPayPdfRegistUtil.VIEW_ID_CONFIRM;
	}
	
	/**
	 * BIRD間の遷移時使用情報保持DTO取得処理
	 * 
	 * @return
	 */
    public CommonCodeDto getCommonCodeDto() {
        S2Container container = SingletonS2ContainerFactory.getContainer();
        return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
    }

	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}

	/**
	 * @param birdDateInfo を クラス変数birdDateInfoへ設定します。
	 */
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
		this.birdDateInfo = birdDateInfo;
	}

	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}

	/**
	 * @param birdUserInfo を クラス変数birdUserInfoへ設定します。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}

	/**
	 * @return miseSearchDto を戻します。
	 */
	public MiseSearchDto getMiseSearchDto() {
		return miseSearchDto;
	}

	/**
	 * @param miseSearchDto を クラス変数miseSearchDtoへ設定します。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto を クラス変数pullDownMenuDtoへ設定します。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	/**
	 * @return tenantPayPdfRegistSesDto を戻します。
	 */
	public SessionDto getTenantPayPdfRegistSesDto() {
		return tenantPayPdfRegistSesDto;
	}

	/**
	 * @param sessionDto を クラス変数tenantPayPdfRegistSesDtoへ設定します。
	 */
	public void setTenantPayPdfRegistSesDto(SessionDto sessionDto) {
		this.tenantPayPdfRegistSesDto = sessionDto;
	}

	/**
	 * @return tenantPayPdfRegistReqDto を戻します。
	 */
	public RequestDto getTenantPayPdfRegistReqDto() {
		return tenantPayPdfRegistReqDto;
	}

	/**
	 * @param registReqDto を クラス変数tenantPayPdfRegistReqDtoへ設定します。
	 */
	public void setTenantPayPdfRegistReqDto(RequestDto registReqDto) {
		this.tenantPayPdfRegistReqDto = registReqDto;
	}

	/**
	 * @return tenantPayPdfRegistConditionLogic を戻します。
	 */
	public ConditionLogic getTenantPayPdfRegistConditionLogic() {
		return tenantPayPdfRegistConditionLogic;
	}

	/**
	 * @param conditionLogic を クラス変数tenantPayPdfRegistConditionLogicへ設定します。
	 */
	public void setTenantPayPdfRegistConditionLogic(
			ConditionLogic conditionLogic) {
		this.tenantPayPdfRegistConditionLogic = conditionLogic;
	}

	/**
	 * @return tenantPayPdfRegistSearchLogic を戻します。
	 */
	public SearchLogic getTenantPayPdfRegistSearchLogic() {
		return tenantPayPdfRegistSearchLogic;
	}

	/**
	 * @param searchLogic を クラス変数tenantPayPdfRegistSearchLogicへ設定します。
	 */
	public void setTenantPayPdfRegistSearchLogic(
			SearchLogic searchLogic) {
		this.tenantPayPdfRegistSearchLogic = searchLogic;
	}

	/**
	 * @return tenantPayPdfRegistSearchedDto を戻します。
	 */
	public SearchedDto getTenantPayPdfRegistSearchedDto() {
		return tenantPayPdfRegistSearchedDto;
	}

	/**
	 * @param searchedDto を クラス変数tenantPayPdfRegistSearchedDtoへ設定します。
	 */
	public void setTenantPayPdfRegistSearchedDto(
			SearchedDto searchedDto) {
		this.tenantPayPdfRegistSearchedDto = searchedDto;
	}

	public MstCategoryShubetuInfoDao getTenantPayPdfRegistMstCategoryShubetuInfoDao() {
		return tenantPayPdfRegistMstCategoryShubetuInfoDao;
	}

	public void setTenantPayPdfRegistMstCategoryShubetuInfoDao(
			MstCategoryShubetuInfoDao tenantPayPdfRegistMstCategoryShubetuInfoDao) {
		this.tenantPayPdfRegistMstCategoryShubetuInfoDao = tenantPayPdfRegistMstCategoryShubetuInfoDao;
	}
}
