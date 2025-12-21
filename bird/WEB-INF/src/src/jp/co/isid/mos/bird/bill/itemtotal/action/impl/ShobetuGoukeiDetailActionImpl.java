/*
 * 作成日: 2006/08/17
 */
package jp.co.isid.mos.bird.bill.itemtotal.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bill.itemtotal.action.ShobetuGoukeiDetailAction;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDetailDto;
import jp.co.isid.mos.bird.bill.itemtotal.dto.ShobetuGoukeiDto;
import jp.co.isid.mos.bird.bill.itemtotal.entity.UIMiseInfo;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetMeisaiInfoLogic;
import jp.co.isid.mos.bird.bill.itemtotal.logic.GetRirekiInfoLogic;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 商品別合計の明細・履歴アクション
 * 
 * @author xlee
 */
public class ShobetuGoukeiDetailActionImpl implements ShobetuGoukeiDetailAction {

    /** アクションID定義:初期化アクション */
	public static final String initialize_ACTION_ID = "BBS012A01";
    
    /** VIEWID定義:明細・履歴画面 */
    private static final String SEARCH_DETAIL = "BBS011V01";
        
    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;

	/** 商品別合計DTO */
	private ShobetuGoukeiDto shobetuGoukeiDto;
	
	/** 商品別合計明細・履歴DTO*/
	private ShobetuGoukeiDetailDto shobetuGoukeiDetailDto;
    
	/** 明細情報取得ロジック */
    private GetMeisaiInfoLogic getMeisaiInfoLogic;
    
    /** 履歴情報取得ロジック */
    private GetRirekiInfoLogic getRirekiInfoLogic;
       
    /**
     * メニューDTOを取得します。
     * @return メニューDTO
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * メニューDTOを設定します。
     * @param pullDownMenuDto メニューDTO
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    
    /**
     * 商品別合計DTOを取得します。:Request
     * @return お買上詳細明細情報DTO
     */
    public ShobetuGoukeiDto getShobetuGoukeiDto() {
        return shobetuGoukeiDto;
    }

    /**
     * 商品別合計DTOを設定します。
     * @param buyingListViewDto　お買上詳細明細情報DTO
     */
    public void setShobetuGoukeiDto(ShobetuGoukeiDto shobetuGoukeiDto) {
        this.shobetuGoukeiDto = shobetuGoukeiDto;
    }
 
    /**
     * 商品別合計明細・履歴DTOを取得します。:Request
     * @return  商品別合計明細・履歴DTO
     */
    public ShobetuGoukeiDetailDto getShobetuGoukeiDetailDto() {
        return shobetuGoukeiDetailDto;
    }

    /**
     * 商品別合計明細・履歴DTOを設定します。
     * @param shobetuGoukeiDetailDto　 商品別合計明細・履歴
     */
    public void setShobetuGoukeiDetailDto(ShobetuGoukeiDetailDto shobetuGoukeiDetailDto) {
        this.shobetuGoukeiDetailDto = shobetuGoukeiDetailDto;
    }
    
    /**
     * 商品別合計明細情報ロジックを取得します。
     * @return 商品別合計明細情報ロジック
     */
    public GetMeisaiInfoLogic getMeisaiInfoLogic() {
        return getMeisaiInfoLogic;
    }

    /**
     * 商品別合計明細情報ロジックを設定します。
     * @param getMeisaiInfoLogic 商品別合計明細情報ロジック
     */
    public void setMeisaiInfoLogic(GetMeisaiInfoLogic getMeisaiInfoLogic) {
        this.getMeisaiInfoLogic = getMeisaiInfoLogic;
    }
    
    /**
     * 商品別合計履歴情報ロジックを取得します。
     * @return 商品別合計履歴情報ロジック
     */
    public GetRirekiInfoLogic getRirekiInfoLogic() {
        return getRirekiInfoLogic;
    }

    /**
     * 商品別合計履歴情報ロジックを設定します。
     * @param getRirekiInfoLogic　商品別合計履歴情報ロジック
     */
    public void setRirekiInfoLogic(GetRirekiInfoLogic getRirekiInfoLogic) {
        this.getRirekiInfoLogic = getRirekiInfoLogic;
    }

   
    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        
        ShobetuGoukeiDto shobetuGoukeiDto = (ShobetuGoukeiDto) container.getComponent("shobetuGoukeiDto");
        ShobetuGoukeiDetailDto shobetuGoukeiDetailDto = (ShobetuGoukeiDetailDto) container.getComponent("shobetuGoukeiDetailDto");

        GetMeisaiInfoLogic getMeisaiInfoLogic = (GetMeisaiInfoLogic) container.getComponent("itemtotal.getMeisaiInfoLogic");
        GetRirekiInfoLogic getRirekiInfoLogic = (GetRirekiInfoLogic) container.getComponent("itemtotal.getRirekiInfoLogic");
        
        // 初期処理
        if (pullDownMenuDto.isClearFlg()) {
            pullDownMenuDto.setClearFlg(false);
            return SEARCH_DETAIL; //検索の初期画面へ遷移
        } else {
        	String newWindFlg = shobetuGoukeiDto.getNewWindowFlg();
        	shobetuGoukeiDetailDto.setNewWindowFlg(newWindFlg);
        	//複数ウィンドウID
        	int windowId = shobetuGoukeiDto.getWindowId();
        	shobetuGoukeiDetailDto.setWindowId(windowId);
        	
        	//2007.01.24　李
        	//String tenpoCd = shobetuGoukeiDto.getCondTaishoTenpoCd();
        	String tenpoCd = shobetuGoukeiDto.getResultTaishoTenpoCd();
        	String tenpoNm = "";
        	List taishoTenpoList = shobetuGoukeiDto.getCondTaishoTenpoList();
        	for(int i = 0; i < taishoTenpoList.size(); i++) {
        		UIMiseInfo uiMise = (UIMiseInfo)taishoTenpoList.get(i);
        		if(uiMise.getMiseCd().equals(tenpoCd)) {
        			tenpoNm = uiMise.getMiseNameKj();
        		}
        	}
        	shobetuGoukeiDetailDto.setTenpoNm(tenpoNm);
        	
        	//2007.01.24　李
        	//String condYMEnd = shobetuGoukeiDto.getCondTaishoKikanCd();
        	String condYMEnd = shobetuGoukeiDto.getResultTaishoKikanCd();
        	String condYMStr = "";
        	try {
				condYMStr = DateManager.getPrevMonth(condYMEnd, 13);
			} catch (Exception e) {
				throw new FtlSystemException("商品別合計明細・履歴情報取得処理");
			}
			String shoCdJitu = shobetuGoukeiDto.getShoCdJitu();
			String shoCdJituNm = shobetuGoukeiDto.getShoCdJituNm();
			
			shobetuGoukeiDetailDto.setShoCdJitu(shoCdJitu);
			shobetuGoukeiDetailDto.setShoCdJituNm(shoCdJituNm);
        	
			//2006.09.08 明細・履歴両方データがない場合だけエラーメッセージを表示する
			//LOGICで処理したエラー処理をアクションで
			List tmpRirekiList = getRirekiInfoLogic.execute(tenpoCd,shoCdJitu,condYMStr,condYMEnd);
			List tmpMeisaiiList = getMeisaiInfoLogic.execute(tenpoCd,shoCdJitu,condYMEnd);
			if ((tmpRirekiList == null || tmpRirekiList.size() == 0) 
					&& (tmpMeisaiiList == null || tmpMeisaiiList.size() == 0)) {
	            throw new NoResultException(); //E0103　履歴情報
	        }
    		shobetuGoukeiDetailDto.setRirekiInfoList(tmpRirekiList); //パラメータ４
    		shobetuGoukeiDetailDto.setMeisaiInfoList(tmpMeisaiiList); //パラメータ３
            // 自画面へ遷移
            return null;
        }
    }
}
