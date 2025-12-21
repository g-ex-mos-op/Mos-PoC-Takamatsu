/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.EditAction;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.logic.CheckInputDataLogic;
import jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.util.TenantPayPdfRegistUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.FileUploadActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.FileUtil;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * テナント入金明細PDF登録
 * 編集画面アクション
 * 
 * 作成日:2009/06/19
 * @author xkinu
 *
 */
public class EditActionImpl extends FileUploadActionImpl implements EditAction {
    /* アクションID：初期化 */
    public static final String initialize_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"11";
    /* アクションID：戻る */
    public static final String back_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"12";
    /* アクションID：（店舗）検索 */
    public static final String callMiseForm_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"13";
    /* アクションID：決定（アップロード） */
    public static final String uploadTemporary_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"14";
    /* アクションID：削除（アップロード取り消し） */
    public static final String remove_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"15";
    /* アクションID：確認 */
    public static final String auth_ACTION_ID = TenantPayPdfRegistUtil.ACTION_ID+"16";

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

    /** LOGIC【入力データ確認】*/
    private CheckInputDataLogic tenantPayPdfRegistCheckInputDataLogic;
    /**
	 * 初期化アクション
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.EditAction#initialize()
	 */
	public String initialize() {
		//１．店舗選択画面から遷移したきた場合。
        if(MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            try {
                //1．DTO【自画面Request用】．ウィンドウIDへDTO【店舗選択】.ウィンドウIDを設定します。
                setTenantPayPdfRegistReqDto((RequestDto)getTenantPayPdfRegistSesDto().getHoldReqDto(getMiseSearchDto().getWindowId()));
            	//2．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //2-1．DTO【自画面Request】.表示対象へ共通DTO【店舗選択】.店舗コードを設定します。
                    getTenantPayPdfRegistSesDto().getRegistDataEntity().setUrikakeCd(selectedCd);
                    getTenantPayPdfRegistSesDto().getRegistDataEntity().setUrikakeName("");
                }
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
        //２．クラス変数【セッションキー作成クラス】で新規セッションKeyを生成する。
		//３．処理２で生成した新規セッションKeyを
		//        DTO【自画面Session】.現行セッションKeyへ設定する。
		//４．処理２で生成した新規セッションKeyを
		//        DTO【自画面Request】.セッションKey保持Mapへ設定する。
        getTenantPayPdfRegistReqDto().setSesstionKey(getTenantPayPdfRegistSesDto().makeSessionKey());
        //５．nullをリターンします。
		return null;
	}

	/**
	 * 戻るアクション
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.EditAction#back()
	 */
	public String back() {
		//初期画面へ戻ります。
		return TenantPayPdfRegistUtil.VIEW_ID_CONDITION;
	}

	/**
	 * 決定（アップロード）アクション
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.EditAction#callMiseForm()
	 */
	public String callMiseForm() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getTenantPayPdfRegistSesDto().isValidSessionKey(getTenantPayPdfRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
        int windowId = getTenantPayPdfRegistReqDto().getWindowId();
        //２．共通DTO【店舗選択】遷移元情報へ編集画面VIEWIDを設定します。
        getMiseSearchDto().setNavigationCase(TenantPayPdfRegistUtil.VIEW_ID_EDIT);
        //３．共通DTO【店舗選択】初期化フラグへtrueを設定します。
        getMiseSearchDto().setInitialFlag(true);
        //４．共通DTO【店舗選択】遷移区分要否フラグへtrueを設定します。
        getMiseSearchDto().setNeedReturnKind(true);
        //５．共通DTO【店舗選択】ウィンドウIDへDTO【自画面Request用】ウィンドウIDを設定します。
        getMiseSearchDto().setWindowId(windowId);
        //６．共通DTO【店舗選択】会社コードリストへ対象会社コードを保持したListを設定します。
        List listCompany = new ArrayList();
        listCompany.add(CommonUtil.COMPANY_CD_MOS);
        getMiseSearchDto().setRCompanyCdList(listCompany);
        //７．現ウィンドウID の検索対象条件項目値の保管を行う。
        getTenantPayPdfRegistSesDto().setHoldReqDto(windowId, getTenantPayPdfRegistReqDto());
        //８．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISESEARCH;
	}
	/**
	 * 決定アクション（仮アップロード）
	 */
	public String uploadTemporary() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getTenantPayPdfRegistSesDto().isValidSessionKey(getTenantPayPdfRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．継承クラスのテンポラリファイル作成処理を実行します。
        String ret = super.uploadTemporary();
        //３．DTO【自画面Session】.[登録情報].ファイル名称へDTO【自画面Session】.アップロードファイル名称を設定します。
        String file = getTenantPayPdfRegistSesDto().getUploadedFile().getName();
        String filename = file.substring(file.lastIndexOf(FileUtil.getFileSeparator()) + 1);
        getTenantPayPdfRegistSesDto().getRegistDataEntity().setFileName(filename);
        //４．処理２の戻り値をリターンします。
		return ret;
	}
	/**
	 * 削除（アップロード取り消し）アクション
	 * 
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.EditAction#removePdf()
	 */
	public String removePdf() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getTenantPayPdfRegistSesDto().isValidSessionKey(getTenantPayPdfRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．DTO【自画面Session】.[登録情報].ファイル名称へ空(null)を設定します。
		getTenantPayPdfRegistSesDto().getRegistDataEntity().setFileName(null);
		//３．nullをリターンします。
		return null;
	}

	/**
	 * 確認アクション
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.EditAction#auth()
	 */
	public String auth() {
    	//１．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!getTenantPayPdfRegistSesDto().isValidSessionKey(getTenantPayPdfRegistReqDto())) {
			return CommonUtil.operationErr_VIEW_ID;
		}
		//２．LOGIC【入力データ確認】を実行します。
		getTenantPayPdfRegistCheckInputDataLogic().execute(getTenantPayPdfRegistSesDto());
		
		// ３．確認画面遷移VIEWIDをリターンします。
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
	 * @return tenantPayPdfRegistCheckInputDataLogic を戻します。
	 */
	public CheckInputDataLogic getTenantPayPdfRegistCheckInputDataLogic() {
		return tenantPayPdfRegistCheckInputDataLogic;
	}

	/**
	 * @param tenantPayPdfRegistCheckInputDataLogic を クラス変数tenantPayPdfRegistCheckInputDataLogicへ設定します。
	 */
	public void setTenantPayPdfRegistCheckInputDataLogic(
			CheckInputDataLogic tenantPayPdfRegistCheckInputDataLogic) {
		this.tenantPayPdfRegistCheckInputDataLogic = tenantPayPdfRegistCheckInputDataLogic;
	}

}
