/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.action.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.ordertimerequired.action.CsvAction;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto.SessionDto;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.util.OrderTimeRequiredUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * オーダー提供時間ダウンロード画面
 * 初期画面アクション
 * 
 * 作成日:2009/10/15
 * @author xkinu
 *
 */
public class CsvActionImpl extends CsvOutput2ActionImpl implements CsvAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = OrderTimeRequiredUtil.ACTION_ID+"01";
    /* アクションID：参照 */
    public static final String callMiseForm_ACTION_ID = OrderTimeRequiredUtil.ACTION_ID+"02";
    /* アクションID：ダウンロード */
    public static final String downloadCsv_ACTION_ID = OrderTimeRequiredUtil.ACTION_ID+"03";
    
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** 共通DTO【プルダウンメニューDTO】*/
    private PullDownMenuDto pullDownMenuDto;
    /** 共通DTO【店舗選択】*/
    private MiseSearchDto miseSearchDto;
    
    /** DTO【自画面Session】 */
    private SessionDto orderTimeRequiredSesDto;
    /** DTO【自画面Request】 */
    private RequestDto orderTimeRequiredReqDto;
    /** LOGIC【条件項目取得】*/
    private ConditionLogic orderTimeRequiredConditionLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行います。
        if(getPullDownMenuDto().isClearFlg()){
            //1.DTO【プルダウンメニュー】.クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            //2．複数WindowID設定
            int windowId = windowId = getOrderTimeRequiredSesDto().createWindowId();
            getOrderTimeRequiredReqDto().setWindowId(windowId);
            //3．BIRD情報をDTO【自画面Session】へ設定します。
            getOrderTimeRequiredSesDto().setBirdDateInfo(getBirdDateInfo());
            getOrderTimeRequiredSesDto().setBirdUserInfo(getBirdUserInfo());
            //4．LOGIC【条件項目値設定】を実行し、初期データ設定処理を行います。
            getOrderTimeRequiredConditionLogic().execute(getOrderTimeRequiredReqDto());
            //5．条件項目情報をDTO【自画面Session】へ設定します。
            getOrderTimeRequiredSesDto().setListMise(getOrderTimeRequiredReqDto().getListMise());
            getOrderTimeRequiredSesDto().setListKikan(getOrderTimeRequiredReqDto().getListKikan());

        }
        //２．店舗選択画面から遷移したきた場合。
        else if(MiseSearchDto.RETURNKIND_INIT != getMiseSearchDto().getReturnKind()) {
            try {
                //1．DTO【自画面Request用】．ウィンドウIDへDTO【店舗選択】.ウィンドウIDを設定します。
                setOrderTimeRequiredReqDto(
                		getOrderTimeRequiredSesDto().getHoldReqDto(getMiseSearchDto().getWindowId()));
                
                //2．店舗を選択後遷移してきた場合。
                if(getMiseSearchDto().getReturnKind() == MiseSearchDto.RETURNKIND_SELECT){
                    String selectedCd = getMiseSearchDto().getMiseCd();
                    //2-1．DTO【自画面Request】.表示対象へ共通DTO【店舗選択】.店舗コードを設定します。
                    getOrderTimeRequiredReqDto().setMiseCd(selectedCd);
                }
            }
            //3．finally処理で下記の処理を行います。
            finally {
            	//3-1.DTO【自画面Session用】．共通画面遷移時のDTO保持オブジェクトへ
            	//    DTO【店舗選択】.ウィンドウIDでnullを設定し、店選択画面遷移時に保持したDTOを削除します。
                getOrderTimeRequiredSesDto().setHoldReqDto(getMiseSearchDto().getWindowId(), null);
                
                //3-2．DTO【店舗選択】.遷移区分を初期値に戻します。
                getMiseSearchDto().setReturnKind(MiseSearchDto.RETURNKIND_INIT);
                //3-3．DTO【店舗選択】.クリア処理を実行します。
                getMiseSearchDto().clear();
            }
        }
        //３．条件項目情報をDTO【自画面Session】へ設定します。
        getOrderTimeRequiredReqDto().setListMise(getOrderTimeRequiredSesDto().getListMise());
        getOrderTimeRequiredReqDto().setListKikan(getOrderTimeRequiredSesDto().getListKikan());
        //４．Nullをリターンします。
        return null;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.tenantpaypdfregist.action.ConditionAction#callMiseForm()
	 */
	public String callMiseForm() {
        int windowId = getOrderTimeRequiredReqDto().getWindowId();
        //１．共通DTO【店舗選択】遷移元情報へ初期画面VIEWIDを設定します。
        getMiseSearchDto().setNavigationCase(OrderTimeRequiredUtil.VIEW_ID);
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
        getOrderTimeRequiredSesDto().setHoldReqDto(windowId, getOrderTimeRequiredReqDto());
        //７．選択画面遷移VIEWIDをリターンします。
        return CommonUtil.VIEW_ID_MISESEARCH;
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
	 * @return orderTimeRequiredSesDto を戻します。
	 */
	public SessionDto getOrderTimeRequiredSesDto() {
		return orderTimeRequiredSesDto;
	}

	/**
	 * @param sessionDto を クラス変数orderTimeRequiredSesDtoへ設定します。
	 */
	public void setOrderTimeRequiredSesDto(SessionDto sessionDto) {
		this.orderTimeRequiredSesDto = sessionDto;
	}

	/**
	 * @return orderTimeRequiredReqDto を戻します。
	 */
	public RequestDto getOrderTimeRequiredReqDto() {
		return orderTimeRequiredReqDto;
	}

	/**
	 * @param registReqDto を クラス変数orderTimeRequiredReqDtoへ設定します。
	 */
	public void setOrderTimeRequiredReqDto(RequestDto registReqDto) {
		this.orderTimeRequiredReqDto = registReqDto;
	}

	/**
	 * @return orderTimeRequiredConditionLogic を戻します。
	 */
	public ConditionLogic getOrderTimeRequiredConditionLogic() {
		return orderTimeRequiredConditionLogic;
	}

	/**
	 * @param conditionLogic を クラス変数orderTimeRequiredConditionLogicへ設定します。
	 */
	public void setOrderTimeRequiredConditionLogic(
			ConditionLogic conditionLogic) {
		this.orderTimeRequiredConditionLogic = conditionLogic;
	}
}
