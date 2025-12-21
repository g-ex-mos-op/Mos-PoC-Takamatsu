package jp.co.isid.mos.bird.bizsupport.spot.action.impl;


import java.util.List;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bizsupport.spot.action.CampNotOrderShopAction;
import jp.co.isid.mos.bird.bizsupport.spot.dto.SpotResultDataDto;
import jp.co.isid.mos.bird.bizsupport.spot.dto.SpotSelectDataDto;
import jp.co.isid.mos.bird.bizsupport.spot.logic.CampListLogic;
import jp.co.isid.mos.bird.bizsupport.spot.logic.CampResultDataLogic;
import jp.co.isid.mos.bird.bizsupport.spot.logic.TenpoDataLogic;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;

/**
 * スポット未受注店画面アクションクラス
 * @author xsong
 */
public class CampNotOrderShopActionImpl implements CampNotOrderShopAction {

    /** 初期処理アクションID */
    public static final String initialize_ACTION_ID = "BBS028A01";
 
    /** 画面VIEW_ID */
    public static final String VIEW_ID = "BBS028V01";
    
    /** 対象キャンペーン取得ロジック */   
    private CampListLogic cmpListLogic;
    
    /**  支部取込コードによる支部情報ロジック  */
    private GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic;
    
    /** スポット未受注店情報取得ロジック */
    private TenpoDataLogic tenpoDataLogic;
    
    /** キャンペーン情報取得ロジック　*/
    private CampResultDataLogic cmpResultDataLogic;
    
    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;
    
    /**  条件部DTO */
    private SpotSelectDataDto spotSelectDataDto;

    /** 結果部DTO　*/
    private SpotResultDataDto spotResultDataDto;
    
  
	/**
	 * 初期処理
	 */
    
	public String initialize() {

		// コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        
        //日付関連情報
        BirdDateInfo birdDateInfo =
            	(BirdDateInfo) container.getComponent(BirdDateInfo.class);
        //ユーザー関連情報
        BirdUserInfo birdUserInfo =
        	(BirdUserInfo) container.getComponent(BirdUserInfo.class);
    
        //システム日付
        String sysDate = birdDateInfo.getSysDate(); 	      
        
        // メニューから遷移された場合
		if (getPullDownMenuDto().isClearFlg()) {
			
			//クリアフラグ設定
			getPullDownMenuDto().setClearFlg(false);
		
			//初期化する
			getSpotSelectDataDto().setDispFlg(false);
			getSpotSelectDataDto().setCmpDataSelectVal("");
			getSpotSelectDataDto().setSibuDataSelectVal("");
			getSpotSelectDataDto().setDataDispFlag(false);
			
 		} 
  			
        //対象キャンペーンプルダウン取得
		List cmpPullList = getCmpListLogic().getList(sysDate);
		
		if (cmpPullList == null	 || cmpPullList.isEmpty()) {
				
			    //対象キャンペーンプルダウン設定
			    getSpotSelectDataDto().setCmpDataList(cmpPullList);
				getSpotSelectDataDto().setDataDispFlag(false);
				throw new NotExistException("POS受注期間中のキャンペーン");
		} else {
						
				//対象キャンペーンプルダウン設定
				getSpotSelectDataDto().setCmpDataList(cmpPullList);
				//支部プルダウン設定
				getSpotSelectDataDto().setSibuDataList(getSibuHoyuTenpoLogic().execute("00", birdUserInfo.getUserID(), birdUserInfo.isLimit()));
		    							
				if (getSpotResultDataDto() == null ) {
					
					getSpotSelectDataDto().setDataDispFlag(false);
					throw new NoResultException("");
                
				} else {
					
					if (getSpotResultDataDto().getResultTable() != null 
							&& getSpotResultDataDto().getResultTable().isEmpty()) {
						
						getSpotSelectDataDto().setDataDispFlag(false);
						throw new NoResultException("");
				
					} else if (getSpotResultDataDto().getResultTable() != null 
							&& !getSpotResultDataDto().getResultTable().isEmpty()) {
					
						getSpotSelectDataDto().setDispFlg(true);
					}
					
				}
						
		    }
				
		// 自画面へ遷移
		return null;
	}

	/**
	 * 実行 / 再検索用
	 */
	public String actionSearch() {
		
		 //結果ヘッダ部取得
		 setSpotResultDataDto(
				 getCmpResultDataLogic().execute(
						 getSpotSelectDataDto().getCmpDataSelectVal(),
				         getSpotSelectDataDto().getSibuDataSelectVal()));
		
		 if(getSpotResultDataDto() != null) {
			
			 getSpotSelectDataDto().setDataDispFlag(true);
			
			 //スポット未受注店情報取得
			 getSpotResultDataDto().setResultTable(
					 getTenpoDataLogic().execute(
							 getSpotSelectDataDto().getCmpDataSelectVal(),
							 getSpotSelectDataDto().getSibuDataSelectVal()));
			 
			 if(getSpotResultDataDto().getResultTable() != null 
						 && !getSpotResultDataDto().getResultTable().isEmpty()) {
			
				 getSpotSelectDataDto().setDispFlg(true);
			
			 }
		
		 } else {
			 
			 getSpotSelectDataDto().setDataDispFlag(false);
		 }
		
		return null;
	}

	
	/**
	 * 条件部DTOを取得します。
	 * @return　spotSelectDataDto　条件部DTO
	 */
	public SpotSelectDataDto getSpotSelectDataDto() {
		return spotSelectDataDto;
	}


	/**
	 * 条件部DTOを設定します。
	 * @param spotSelectDataDto　条件部DTO
	 */
	public void setSpotSelectDataDto(SpotSelectDataDto spotSelectDataDto) {
		this.spotSelectDataDto = spotSelectDataDto;
	}

	
	/**
	 * 対象キャンペーン取得ロジックを取得します。
	 * @return　cmpListLogic　対象キャンペーン取得ロジック
	 */
	public CampListLogic getCmpListLogic() {
		return cmpListLogic;
	}


	/**
	 * 対象キャンペーン取得ロジックを設定します。
	 * @param cmpListLogic　対象キャンペーン取得ロジック
	 */
	public void setCmpListLogic(CampListLogic cmpListLogic) {
		this.cmpListLogic = cmpListLogic;
	}

	/**
	 * キャンペーン情報を取得します。
	 * @return　cmpResultDataLogic　キャンペーン情報
	 */
	public CampResultDataLogic getCmpResultDataLogic() {
		return cmpResultDataLogic;
	}

	/**
	 * キャンペーン情報を設定します。
	 * @param cmpResultDataLogic　キャンペーン情報
	 */
	public void setCmpResultDataLogic(CampResultDataLogic cmpResultDataLogic) {
		this.cmpResultDataLogic = cmpResultDataLogic;
	}

	/**
	 * スポット未受注店情報取得ロジックを取得します。
	 * @return　tenpoDataLogic　スポット未受注店情報取得ロジック
	 */
	public TenpoDataLogic getTenpoDataLogic() {
		return tenpoDataLogic;
	}

	/**
	 * スポット未受注店情報取得ロジックを設定します。
	 * @param tenpoDataLogic　スポット未受注店情報取得ロジック
	 */
	public void setTenpoDataLogic(TenpoDataLogic tenpoDataLogic) {
		this.tenpoDataLogic = tenpoDataLogic;
	}

	
	/**
	 * メニューDTOを取得します。
	 * @return　pullDownMenuDto　メニューDTO
	 */
	public PullDownMenuDto getPullDownMenuDto() {
		return pullDownMenuDto;
	}

	/**
	 * メニューDTOを設定します。
	 * @param pullDownMenuDto　メニューDTO
	 */
	public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
		this.pullDownMenuDto = pullDownMenuDto;
	}

	/**
	 * 結果部DTOを取得します。
	 * @return　spotResultDataDto　結果部DTO
	 */
	public SpotResultDataDto getSpotResultDataDto() {
		return spotResultDataDto;
	}

	/**
	 * 結果部DTOを設定します。
	 * @param spotResultDataDto　結果部DTO
	 */
	public void setSpotResultDataDto(SpotResultDataDto spotResultDataDto) {
		this.spotResultDataDto = spotResultDataDto;
	}

	/**
	 * 支部取込コードによる支部情報ロジックを取得します。
	 * @return　sibuHoyuTenpoLogic　支部取込コードによる支部情報ロジック
	 */
	public GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic() {
		return sibuHoyuTenpoLogic;
	}

	/**
	 * 支部取込コードによる支部情報ロジックを設定します。
	 * @param sibuHoyuTenpoLogic　支部取込コードによる支部情報ロジック
	 */
	public void setSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic) {
		this.sibuHoyuTenpoLogic = sibuHoyuTenpoLogic;
	}


}