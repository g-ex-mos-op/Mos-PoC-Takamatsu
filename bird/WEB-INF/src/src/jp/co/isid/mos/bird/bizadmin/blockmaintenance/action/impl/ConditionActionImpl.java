/**
 *
 */
package jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.ConditionAction;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.common.BlockMaintenanceUtil;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto.RequestDto;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.dto.SessionDto;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.entity.UIMiseBlockInfo;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.ConditionLogic;
import jp.co.isid.mos.bird.bizadmin.blockmaintenance.logic.SearchLogic;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;

/**
 * ブロックメンテナンス
 * 初期画面用アクション
 *
 * 2007/05/10 支部プルダウンを店舗の存在する支部のみに変更
 * @author xkinu
 *
 */
public class ConditionActionImpl implements ConditionAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID
    	= BlockMaintenanceUtil.ACTION_ID_CONDITION+"1";
    /* アクションID：検索処理 */
    public static final String search_ACTION_ID
    	= BlockMaintenanceUtil.ACTION_ID_CONDITION+"2";

    /* DTO【自画面SessionDto】 */
    private SessionDto blockMainteSesDto;
    /* DTO【自画面RequestDto】 */
    private RequestDto blockMainteReqDto;
    /* DTO【プルダウンメニュー】 */
    private PullDownMenuDto pullDownMenuDto;

    /* LOGIC【条件項目情報の取得】ロジック */
    private ConditionLogic blockMainteConditionLogic;
    /* LOGIC【検索結果の取得】ロジック */
    private SearchLogic blockMainteSearchLogic;
    /** ロジック【支部の取得】*/
    private GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic = null;

    /* セッションキー作成クラス生成 */
    MakeSessionKey mkSession = new MakeSessionKey();
    /* 全角削除*/
    private static DefaultFormatter trimFormatter= new DefaultFormatter();

	/**
     * 初期処理 アクション
     *
     * １．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
     *     １．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
     *     ２．DTO【自画面SessionDto】.クリア処理をおこなう。
     *     ３．複数ウィンドウ機能で新規WindowID設定する。
     *     ４．新規セッションKeyを生成する。
     *     ５．処理４で生成した新規セッションKeyをDTO【自画面SessionDto】.現行セッションKeyへ設定する。
     *     ６．処理４で生成した新規セッションKeyを
     *     　　処理３で設定したWindowIDをKeyに
     *     ７．ロジック【条件項目情報の取得】用パラメーターMapを生成する。
     *     ８．ロジック【条件項目情報の取得】を実行
     *     　　パラメーター：BIRDユーザー情報
     *     ９．処理８の実行結果Map[[会社リスト]]をDTO【自画面SessionDto】.会社リストへ設定する。
     *     １０．処理８の実行結果Map[[会社別支部情報]]をDTO【自画面SessionDto】.会社別支部情報へ設定する。
     *     １１．処理８の実行結果Map[[ブロックリスト]]をDTO【自画面SessionDto】.ブロックリストへ設定する。
     *
	 * @see jp.co.isid.mos.bird.bizadmin.blockmaintenance.action.ConditionAction#initialize()
	 */
	public String initialize() {
        //１．DTO【プルダウンメニュー】.クリアフラグがtrueの場合下記の処理を行う。
        if(getPullDownMenuDto().isClearFlg()){
            //１．DTO【プルダウンメニュー】.クリアフラグをfalseに設定する。
            getPullDownMenuDto().setClearFlg(false);
        	//２．DTO【自画面SessionDto】.クリア処理をおこなう。
            //(以前の条件項目が残るのを防止するため)
            getBlockMainteReqDto().clear();
            //３．複数ウィンドウ機能で新規WindowID設定する。
            int windowId = getBlockMainteSesDto().createWindowId();
            getBlockMainteReqDto().setWindowId(windowId);

			//４．新規セッションKeyを生成する。
			String key = mkSession._makeSessionKey();
			//５．処理４で生成した新規セッションKeyを
			//        DTO【自画面SessionDto】.現行セッションKeyへ設定する。
			getBlockMainteSesDto().setNowSessionKey(key);
			//６．処理４で生成した新規セッションKeyを
			//        処理３で設定したWindowIDをKeyに
			//        DTO【自画面SessionDto】.セッションKey保持Mapへ設定する。
			getBlockMainteSesDto().setSessionKey(windowId, key);

            //７．ロジック【条件項目情報の取得】用パラメーターMapを生成する。
	        Map params = new HashMap();
	        //８．ロジック【条件項目情報の取得】を実行
	        //パラメーター：BIRDユーザー情報
	        params.put(ConditionLogic.PK_USERINFO, getBirdUserInfo());
	        Map reParam = getBlockMainteConditionLogic().execute(params);

	        //９．処理８の実行結果Map[[会社リスト]]を
	        //        DTO【自画面SessionDto】.会社リストへ設定する。
	        getBlockMainteSesDto().setListCompany((List)reParam.get(ConditionLogic.RK_LIST_COMPANY));
	        //１０．処理８の実行結果Map[[会社別支部情報]]を
	        //        DTO【自画面SessionDto】.会社別支部情報へ設定する。
	        getBlockMainteSesDto().setListSibu((List)reParam.get(ConditionLogic.RK_LIST_SIBU));
	        //１１．処理８の実行結果Map[[ブロックリスト]]を
	        //        DTO【自画面SessionDto】.ブロックリストへ設定する。
	        getBlockMainteSesDto().setListBlock((List)reParam.get(ConditionLogic.RK_LIST_BLOCK));
	        //１２．処理８の実行結果Map[[対象ブロックリスト]]を
	        //        DTO【自画面SessionDto】.対象ブロックリストへ設定する。
	        getBlockMainteSesDto().setListDispBlock((List)reParam.get(ConditionLogic.RK_LIST_DISPBLOCK));
        }

		return null;
	}

    /**
     * 検索 アクション
     *
     * １．ロジック【検索結果の取得】を実行
     * ２．結果をDTOにセットする。
     * @return 確認(or照会)画面ID
     */
    public String search() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        //１．ロジック【検索結果の取得】を実行
        Map params = new HashMap();
        String companyCd = getBlockMainteReqDto().getTargetCompanyCd();
        String sibuCd = getBlockMainteReqDto().getTargetSibuCd();
        //パラメーター：BIRDユーザー情報
        params.put(SearchLogic.PK_USERINFO, getBirdUserInfo());
        //パラメーター：会社コード
        params.put(SearchLogic.PK_COMPANY_CD, companyCd);
        //パラメーター：支部コード
        params.put(SearchLogic.PK_SIBU_CD, sibuCd);
        //パラメーター：支部コード
        params.put(SearchLogic.PK_SYSDATE, getBirdDateInfo().getSysDate());

        List listSearchData = getBlockMainteSearchLogic().execute(params);
        if(listSearchData.size() == 0){
            getBlockMainteSesDto().setListSearchData(null);
            //throw new NotExistException("該当データ");
            throw new NoResultException();
        }
        //２．結果をDTOにセットする。
        getBlockMainteSesDto().setTargetCompanyCd(companyCd);
        getBlockMainteSesDto().setTargetSibuCd(sibuCd);
        List<UIMiseBlockInfo> newList = new ArrayList<UIMiseBlockInfo>();
        for (int i=0; i<listSearchData.size(); i++){
        	UIMiseBlockInfo entity = (UIMiseBlockInfo)listSearchData.get(i);
        	entity.setMiseNameKj(trimFormatter.trimWideHalfSpace(entity.getMiseNameKj()));
        	newList.add(entity);
        }
        getBlockMainteSesDto().setListSearchData(newList);

        return BlockMaintenanceUtil.VIEW_ID_EDIT;
    }
    /**
     * 会社プルダウンリスト変更 アクション
     *
     * １．ロジック【支部の取得】を実行
     * ２．結果をDTOにセットする。
     * @return 自画面ID
     */
    public String changeCompany() {
    	// 【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
		if (!isValidSessionKey()) {
			return BlockMaintenanceUtil.operationErr_VIEW_ID;
		}
        //１．ロジック【検索結果の取得】を実行
        String companyCd = getBlockMainteReqDto().getTargetCompanyCd();
        List listSibu = getSibuHoyuTenpoLogic().execute(companyCd, "", false);
        //２．結果をDTOにセットする。
        getBlockMainteSesDto().setListSibu(listSibu);

        return null;
    }

	/**
	 * @return blockMainteConditionLogic を戻します。
	 */
	public ConditionLogic getBlockMainteConditionLogic() {
		return blockMainteConditionLogic;
	}

	/**
	 * @param blockMainteConditionLogic 設定する blockMainteConditionLogic。
	 */
	public void setBlockMainteConditionLogic(
			ConditionLogic blockMainteConditionLogic) {
		this.blockMainteConditionLogic = blockMainteConditionLogic;
	}

	/**
	 * @return blockMainteSearchLogic を戻します。
	 */
	public SearchLogic getBlockMainteSearchLogic() {
		return blockMainteSearchLogic;
	}

	/**
	 * @param blockMainteSearchLogic 設定する blockMainteSearchLogic。
	 */
	public void setBlockMainteSearchLogic(SearchLogic blockMainteSearchLogic) {
		this.blockMainteSearchLogic = blockMainteSearchLogic;
	}

	/**
	 * @return blockMainteSesDto を戻します。
	 */
	public SessionDto getBlockMainteSesDto() {
		return blockMainteSesDto;
	}

	/**
	 * @param blockMainteSesDto 設定する blockMainteSesDto。
	 */
	public void setBlockMainteSesDto(SessionDto blockMainteSesDto) {
		this.blockMainteSesDto = blockMainteSesDto;
	}

	/**
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * @param pullDownMenuDto 設定する pullDownMenuDto。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
    /**
     *
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * 操作エラー判断処理
     *
     * 現行のウィンドウが無効の場合は false
     *                   有効の場合は true
     * @return
     */
    private boolean isValidSessionKey(){
        return mkSession.isValidSessionKey(
                     getBlockMainteSesDto().getNowSessionKey()
                  ,  getBlockMainteSesDto().getSessionKey(getBlockMainteReqDto().getWindowId()) );
    }

	/**
	 * @return blockMainteReqDto を戻します。
	 */
	public RequestDto getBlockMainteReqDto() {
		return blockMainteReqDto;
	}

	/**
	 * @param blockMainteReqDto 設定する blockMainteReqDto。
	 */
	public void setBlockMainteReqDto(RequestDto blockMainteReqDto) {
		this.blockMainteReqDto = blockMainteReqDto;
	}

    /**
     * @return getSibuLogic を戻します。
     */
    public GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic() {
        return sibuHoyuTenpoLogic;
    }
    /**
     * @param getSibuLogic 設定する getSibuLogic。
     */
    public void setSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic getSibuLogic) {
        this.sibuHoyuTenpoLogic = getSibuLogic;
    }

}
