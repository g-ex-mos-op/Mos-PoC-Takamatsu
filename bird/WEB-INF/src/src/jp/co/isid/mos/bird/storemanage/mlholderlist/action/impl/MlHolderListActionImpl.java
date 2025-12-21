/*
 * 作成日: 2006/04/20
 */
package jp.co.isid.mos.bird.storemanage.mlholderlist.action.impl;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import jp.co.isid.mos.bird.common.logic.GetSibuTorikomiLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutput2ActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.storemanage.common.util.StoreManageUtil;
import jp.co.isid.mos.bird.storemanage.mlholderlist.action.MlHolderListAction;
import jp.co.isid.mos.bird.storemanage.mlholderlist.dto.MlHolderListDto;
import jp.co.isid.mos.bird.storemanage.mlholderlist.entity.UIMLHolder;
import jp.co.isid.mos.bird.storemanage.mlholderlist.logic.MlHolderListLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * マスタライセンス保持者店舗別一覧アクションクラス
 * 
 * 更新日:2008/06/02 マスターライセンス制度変更対応
 *        一括ダウンロードボタンを追加対応により、
 *        MHA権限ユーザ判断処理ロジックを追加しました。
 * @author lee
 */
public class MlHolderListActionImpl extends CsvOutput2ActionImpl
	implements MlHolderListAction
{
	private static final String SCREEN_ID= "BSM005";
	/* アクションID */
	public static final String initialize_ACTION_ID = 	"BSM005A01";
	public static final String onerSearch_ACTION_ID = 	"BSM005A02";
	public static final String miseSrearch_ACTION_ID = 	"BSM005A03";
	public static final String result_ACTION_ID = 		"BSM005A04";
	
    /* pullDownMenuDto */
    private PullDownMenuDto pullDownMenuDto;
	/** 共通ロジック【ユーザの汎用画面ロール制御情報取得】*/
    private GetGamenRoleLogic gamenRoleLogic;
    /*[LOGIC] 2007/01/10 add*/
    private MlHolderListLogic mlHolderListLogic;
    
	private MlHolderListDto mlHolderListDto; 
	/* 店舗選択 */
	private MiseSearchDto miseSearchDto;
	/* オーナー選択 */
	private OwnerSearchDto ownerSearchDto;
	
	private BirdUserInfo birdUserInfo;
	/* 支部取得 */
    private GetSibuTorikomiLogic getSibuTorikomiLogic;
	
	/* 初期処理 */
	public String initialize() {
		
		// 個店ポータルから遷移時の処理①
        // 共通DTOへ値をセット
        CommonCodeDto commonCodeDto = getCommonCodeDto();
        if (commonCodeDto == null) {
        	commonCodeDto = new CommonCodeDto();
        }
		if (commonCodeDto.getUseCommonDto()) {
			pullDownMenuDto.setClearFlg(true);
		}
		
		/* 初期化 */
		if(pullDownMenuDto.isClearFlg()){
//		if(!mlHolderListDto.isSelectinitFlg()){
            S2Container container = SingletonS2ContainerFactory.getContainer();
			HttpServletRequest request = (HttpServletRequest) container
					.getComponent("request");
			BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession()
					.getAttribute("birdUserInfo");
			BirdDateInfo birdDateInfo = (BirdDateInfo) request.getSession()
			.getAttribute("birdDateInfo");

			mlHolderListDto.clear();
			mlHolderListDto.setSelectFlg(0);
            mlHolderListDto.setSysDate(birdDateInfo.getSysDate());
			/* 会社コードセット */
			mlHolderListDto.setCompanyCd("00");
			/* sessionからユーザタイプ取得 */
			mlHolderListDto.setUsertypeCd(birdUserInfo.getMstUser().getUserTypeCd());
					
			// ユーザタイプ本部(01)の場合
			if(mlHolderListDto.getUsertypeCd().equals("01")){
				/* 支部取得 */
                mlHolderListDto.setSibuList(getGetSibuTorikomiLogic().execute(
                        mlHolderListDto.getCompanyCd(),birdUserInfo.getUserID(), birdUserInfo.isLimit()));
			}

			// ユーザタイプオーナ(02)の場合
			if(mlHolderListDto.getUsertypeCd().equals("02")){
				mlHolderListDto.setOnerCd(getOnerCd());
				mlHolderListDto.setSelectFlg(1);
// change start add inazawa 2007/01/10
//				MlHolderListLogic mlHolderListLogic = (MlHolderListLogic) container.getComponent(MlHolderListLogicImpl.class);
//				mlHolderListLogic.execute(mlHolderListDto);
                getMlHolderListLogic().execute(mlHolderListDto);
// change end
				if(mlHolderListDto.getResultList() == null || mlHolderListDto.getResultList().size() == 0){
					mlHolderListDto.setSelectinitFlg(false);
					throw new NoResultException("");
				}
// add start xkhata 20060822 csvダウンロード対応
                UIMLHolder unit = (UIMLHolder)mlHolderListDto.getResultList().get(0);
                mlHolderListDto.setTmpSelectFlg(mlHolderListDto.getSelectFlg());
                mlHolderListDto.setTmpSibuNm(unit.getSibuNameKj());
                mlHolderListDto.setTmpMiseCd(mlHolderListDto.getMiseCd());
                mlHolderListDto.setTmpMiseNm(unit.getMiseNameKj());
                mlHolderListDto.setTmpOnerCd(mlHolderListDto.getOnerCd());
                mlHolderListDto.setTmpOnerNm(unit.getOnerNameKj());
// add end
                
			}
			
			// 個店ポータルから遷移時の処理②
			if (commonCodeDto.getUseCommonDto()) {
				mlHolderListDto.setSelectFlg(2);
				mlHolderListDto.setMiseCd(commonCodeDto.getMiseCd());
				// 検索メソッド呼出
				result();
			}
//2008/06/02 ADD start xkinu　一括ダウンロードボタン追加対応
            //MHA権限ユーザ判断処理
            boolean mhaUser = StoreManageUtil.isMhaUser(getGamenRoleLogic()
            		, getBirdUserInfo().getUserID()
            		, SCREEN_ID
            		, "01");
			getMlHolderListDto().setMha(mhaUser);
//2008/06/02 ADD end xkinu　一括ダウンロードボタン追加対応
            
			pullDownMenuDto.setClearFlg(false);
		}

		
		/* 店情報取得 */
		if (getMiseSearchDto().isActionFlg()) {
			mlHolderListDto.setMiseCd(getMiseSearchDto().getMiseCd());
			getMiseSearchDto().setActionFlg(false);
		}
		
		/*オーナーコード取得*/
		if(getOwnerSearchDto().isActionFlag()){
			mlHolderListDto.setOnerCd(getOwnerSearchDto().getOnerCd());
			getOwnerSearchDto().setActionFlag(false);
		}

		return null;
	}
	
	/* 検索 */
	public String result(){

		/* 検索条件セット */
//		if(select == 0){
//			mlHolderListDto.setOnerCd(null);
//			mlHolderListDto.setMiseCd(null);
//		}else if(select == 1){
//			mlHolderListDto.setSitenCd(null);
//			mlHolderListDto.setMiseCd(null);
//		}else if(select == 2){
//			mlHolderListDto.setSitenCd(null);
//			mlHolderListDto.setOnerCd(null);
//		}		
		/* マスタライセンス保持者店舗別一覧取得 */
// change start inazawa 2007/01/10        
//		S2Container container = SingletonS2ContainerFactory.getContainer();
//		MlHolderListLogic mlHolderListLogic = (MlHolderListLogic) container.getComponent(MlHolderListLogicImpl.class);
//		mlHolderListLogic.execute(mlHolderListDto);
        getMlHolderListLogic().execute(mlHolderListDto);
// change end        
		if(mlHolderListDto.getResultList() == null || mlHolderListDto.getResultList().size() == 0){
			mlHolderListDto.setSelectinitFlg(false);
			throw new NoResultException("");
		}
		mlHolderListDto.setSelectinitFlg(true);
        
        /* 検索条件を保持 */
        UIMLHolder unit = (UIMLHolder)mlHolderListDto.getResultList().get(0);
        mlHolderListDto.setTmpSelectFlg(mlHolderListDto.getSelectFlg());
        mlHolderListDto.setTmpSibuNm(unit.getSibuNameKj());
        mlHolderListDto.setTmpMiseCd(mlHolderListDto.getMiseCd());
        mlHolderListDto.setTmpMiseNm(unit.getMiseNameKj());
        mlHolderListDto.setTmpOnerCd(mlHolderListDto.getOnerCd());
        mlHolderListDto.setTmpOnerNm(unit.getOnerNameKj());
        
		return null;
	}
	
	/* オーナー選択処理 */
	public String onerSearch(){
		getOwnerSearchDto().clear();
		getOwnerSearchDto().setInitFlag(true);
		getOwnerSearchDto().setNavigationCase(mlHolderListDto.condition_VIEW_ID);
		return mlHolderListDto.onerSearch_VIEW_ID;
	}
	
	/* 店舗選択処理 */
	public String miseSrearch(){
		getMiseSearchDto().setActionFlg(true);
		getMiseSearchDto().setInitialFlag(true);
		getMiseSearchDto().setNavigationCase(mlHolderListDto.condition_VIEW_ID);
		return mlHolderListDto.miseSearch_VIEW_ID;
	}
	
	/* Sessionのユーザ対応オーナー.管理会社企業コード='00'のオーナーコード */
	private String getOnerCd(){
		String s_onerCd = null;
// add start xkhata 20060822 システムエラー対応
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");
// add end
		for (Iterator it = birdUserInfo.getUserOner().iterator(); it.hasNext();) {
			UIUserOner uiUserOner = (UIUserOner) it.next();
			if (uiUserOner.getCompanyCd().equals("00")) {
				s_onerCd = uiUserOner.getOnerCd();
			}
		}
		return s_onerCd;
	}
	/**
	 * @return birdUserInfo を戻します。
	 */
	public BirdUserInfo getBirdUserInfo() {
		return birdUserInfo;
	}
	/**
	 * @param birdUserInfo birdUserInfo を設定。
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
	 * @param miseSearchDto miseSearchDto を設定。
	 */
	public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
		this.miseSearchDto = miseSearchDto;
	}
	
	/**
	 * @return ownerSearchDto を戻します。
	 */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
	/**
	 * @param ownerSearchDto ownerSearchDto を設定。
	 */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
	}
	/**
	 * @return mlHolderListDto を戻します。
	 */
	public MlHolderListDto getMlHolderListDto() {
		return mlHolderListDto;
	}
	/**
	 * @param mlHolderListDto mlHolderListDto を設定。
	 */
	public void setMlHolderListDto(MlHolderListDto mlHolderListDto) {
		this.mlHolderListDto = mlHolderListDto;
	}
    /**
     * @return getSibuTorikomiLogic を戻します。
     */
    public GetSibuTorikomiLogic getGetSibuTorikomiLogic() {
        return getSibuTorikomiLogic;
    }

    /**
     * @param getSibuTorikomiLogic 設定する getSibuTorikomiLogic。
     */
    public void setGetSibuTorikomiLogic(GetSibuTorikomiLogic getSibuTorikomiLogic) {
        this.getSibuTorikomiLogic = getSibuTorikomiLogic;
    }
	/**
	 * pullDownMenuDtoの設定
	 * @return pullDownMenuDto を戻します。
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}
	/**
	 * pullDownMenuDtoの設定
	 * @param pullDownMenuDto pullDownMenuDto を設定。
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}
	
	private CommonCodeDto getCommonCodeDto() {
		S2Container container = SingletonS2ContainerFactory.getContainer();
		return (CommonCodeDto) container.getComponent(CommonCodeDto.class);
	}

    public MlHolderListLogic getMlHolderListLogic() {
        return mlHolderListLogic;
    }

    public void setMlHolderListLogic(MlHolderListLogic mlHolderListLogic) {
        this.mlHolderListLogic = mlHolderListLogic;
    }

	/**
	 * @return gamenRoleLogic を戻します。
	 */
	public GetGamenRoleLogic getGamenRoleLogic() {
		return gamenRoleLogic;
	}

	/**
	 * @param gamenRoleLogic 設定する gamenRoleLogic。
	 */
	public void setGamenRoleLogic(GetGamenRoleLogic gamenRoleLogic) {
		this.gamenRoleLogic = gamenRoleLogic;
	}
}
