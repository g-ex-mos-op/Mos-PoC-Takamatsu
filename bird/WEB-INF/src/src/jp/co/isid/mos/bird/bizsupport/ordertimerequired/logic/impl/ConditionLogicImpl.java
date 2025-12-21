/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.ordertimerequired.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizsupport.ordertimerequired.util.OrderTimeRequiredUtil;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetHyojiTaishoLogicImpl;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * オーダー提供時間ダウンロード
 * 
 * 作成日:2009/10/16
 * @author xkinu
 *
 */
public class ConditionLogicImpl implements ConditionLogic {
    /** ロジックID */
    public static final String LOGIC_ID = OrderTimeRequiredUtil.SCREEN_ID+"L01";
	/** LOGIC【表示対象取得】*/
	private GetHyojiTaishoLogic getHyojiTaishoLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizsupport.ordertimerequired.logic.ConditionLogic#execute(jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.bizsupport.ordertimerequired.dto.RequestDto)
	 */
	public void execute(RequestDto requestDto) {
		//１．事前条件処理
		validate(requestDto);

		//２．クラス変数[BIRD日付情報]からアプリ日付を取得します。
		String appDate = getBirdDateInfo().getAppDate();

		//３．共通【static定数クラス】.年月日リスト作成を実行し、アプリ日付から過去60日分のList[[期間]]を取得する。
		List listKikan = CommonUtil.creatListDay(appDate, 60, CommonUtil.PAST, CommonUtil.SORT_DESC);
		//４．過去60日分のList[[期間]]をパラメータ.DTO【自画面Request】.List[[期間]]へ設定します。
		requestDto.setListKikan(listKikan);
		//５．クラス変数[BIRDユーザ情報]からユーザタイプを取得します。
		String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
		//６．処理５のユーザタイプがオーナーか店舗の場合は、店舗情報を取得します。
		if(UserType.ONER.equals(userTypeCd) || UserType.TENPO.equals(userTypeCd)) {
			//1.共通LOGIC【表示対象取得】のパラメータ用Map[パラメータ]を生成します。
    		Map params = new HashMap();
    		params.put(GetHyojiTaishoLogicImpl.PM_APP_DATE, appDate);
    		params.put(GetHyojiTaishoLogicImpl.PM_COMPANY_CD, requestDto.getCompanyCd());
    		params.put(GetHyojiTaishoLogicImpl.PM_TAISHOJOKEN, TaishoJoken.CODE_MISE);
    		//2.共通LOGIC【表示対象取得】を実行し、Map[対象情報]を取得します。
	        Map logigMap = getGetHyojiTaishoLogic().execute(params);
	        //3.処理2のMap[対象情報]からList[[表示対象]]を取得します。
	        List listHyojiTaisho = (List)logigMap.get(GetHyojiTaishoLogicImpl.RK_LIST);
	        //4.処理3のList[[表示対象]]のデータが1件以上存在する場合は下記の処理を行います。
       		if (listHyojiTaisho != null && listHyojiTaisho.size() > 0) {
		        //4-1.DTO【自画面Request】へList[[表示対象]]を格納する。
       			requestDto.setListMise(listHyojiTaisho);
        		//4-2．ログインユーザが店舗の場合は下記の処理を行います。
        		if(UserType.TENPO.equals(userTypeCd)) {
        			//TUser-1.処理3のList[[表示対象]]のインデックス0番目のEntity[表示対象]を取得します。
        			CodHyojiTaisho miseInfo = (CodHyojiTaisho)listHyojiTaisho.get(0);
        			//TUser-2.Entity[表示対象].店コードをパラメータ.DTO【自画面Request】.店コードへ設定します。
        			requestDto.setMiseCd(miseInfo.getHyojitaishoCd());
        			//TUser-3.Entity[表示対象].店名称をパラメータ.DTO【自画面Request】.店名称へ設定します。
        			requestDto.setMiseNameKj(miseInfo.getHyojitaishoName());
        		}
	        }
		}
		
	}
    /**
     * 事前条件処理
     * 
     * @param dto
     */
    private void validate(RequestDto requestDto)
    {
    	if(requestDto == null) {
    		throw new MissingDataException("リクエスト用DTO");
    	}
    	//初期値を設定します。
    	requestDto.clear();
    }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
	/**
	 * BIRD日付情報取得処理
	 * @return birdDateInfo を戻します。
	 */
	private BirdDateInfo getBirdDateInfo() {
		return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
	}
	/**
	 * @return getHyojiTaishoLogic を戻します。
	 */
	public GetHyojiTaishoLogic getGetHyojiTaishoLogic() {
		return getHyojiTaishoLogic;
	}
	/**
	 * @param getHyojiTaishoLogic を クラス変数getHyojiTaishoLogicへ設定します。
	 */
	public void setGetHyojiTaishoLogic(GetHyojiTaishoLogic getHyojiTaishoLogic) {
		this.getHyojiTaishoLogic = getHyojiTaishoLogic;
	}

}
