/*
 * 作成日: 2006/09/19
 *
 */
package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.action.MosChickenSaleStateViewResultAction;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dto.MosChickenSaleStateViewDto;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetSaleStateTotalInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util.MosChichenSaleStateUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * モスチキン販売状況一覧　検索結果画面アクション
 * 
 * @author xlee
 */
public class MosChickenSaleStateViewResultActionImpl implements MosChickenSaleStateViewResultAction {
	
	/** アクションID定義:初期化アクション */
    public static final String initialize_ACTION_ID = "BBS017A01";
    
    /** アクションID定義:初期化アクション */
    public static final String backMove_ACTION_ID = "BBS017A06";

    /** アクションID定義:初期化アクション */
    public static final String onchangeTitle_ACTION_ID = "BBS017A04";
    
    /** アクションID定義:初期化アクション */
    public static final String reSearchExecute_ACTION_ID = "BBS017A06";

    /** アクションID定義:初期化アクション */
    public static final String changePage_ACTION_ID = "BBS017A07";
	
	/** VIEWID定義:オーナー選択画面から戻る画面IDを保持 */
    private static final String VIEWID_CONDITION    = "BBS017V01";

	/** モスチキン販売状況一覧：session */
	private MosChickenSaleStateViewDto mosChickenSaleStateViewDto;    
	    
    /** メニューDTO */
    private PullDownMenuDto pullDownMenuDto;
    
    /** オーナーコード取得ロジック */
    private GetSaleStateTotalInfoLogic getSaleStateTotalInfoLogic;

    /** ユーザ関連情報 */
    private BirdUserInfo birdUserInfo;

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /** オーナーコード取得ロジック */
    private GetMiseInfoLogic getMiseInfo;
    
    /**
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * 日付情報を取得します。
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * 日付情報を設定します。
     * @param birdDateInfo 日付情報
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    
    /**
     * モスチキン販売状況一覧DTOを取得します。:session
     * @return  モスチキン販売状況一覧DTO
     */
    public MosChickenSaleStateViewDto getMosChickenSaleStateViewDto() {
        return mosChickenSaleStateViewDto;
    }

    /**
     *  モスチキン販売状況一覧DTOを設定します。
     * @param buyingListViewDto　 モスチキン販売状況一覧DTO
     */
    public void setMosChickenSaleStateViewDto(MosChickenSaleStateViewDto mosChickenSaleStateViewDto) {
        this.mosChickenSaleStateViewDto = mosChickenSaleStateViewDto;
    }
        
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
     * オーナーコード取得ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetSaleStateTotalInfoLogic getGetSaleStateTotalInfoLogic() {
        return getSaleStateTotalInfoLogic;
    }

    /**
     * オーナーコード取得ロジックを設定します。
     * @param getOnerCdLogic　オーナーコード取得ロジック
     */
    public void setGetSaleStateTotalInfoLogic(GetSaleStateTotalInfoLogic getSaleStateTotalInfoLogic) {
        this.getSaleStateTotalInfoLogic = getSaleStateTotalInfoLogic;
    }
    
    /**
     * オーナーコード取得ロジックを取得します。
     * @return 　オーナーコード取得ロジック
     */
    public GetMiseInfoLogic getGetMiseInfoLogic() {
        return getMiseInfo;
    }

    /**
     * オーナーコード取得ロジックを設定します。
     * @param getOnerCdLogic　オーナーコード取得ロジック
     */
    public void setGetMiseInfoLogic(GetMiseInfoLogic getMiseInfo) {
        this.getMiseInfo = getMiseInfo;
    }
    
    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        // 自画面へ遷移
        return null;
    }

    /**
     * オーナーコード入力画面へ戻る処理
     * 
     * @return 画面遷移情報
     */
	public String backMove() {
		getMosChickenSaleStateViewDto().setActionKbn("BACK");
		
		// オーナー検索画面へ遷移する
		return VIEWID_CONDITION;
	}

    /**
     * 再検索処理
     * 
     * @return 画面遷移情報
     */   
	public String reSearchExecute() {
	    //DAOを実行する為のパラメータ
        String sysDate = getBirdDateInfo().getSysDate();

        //ロジックの実行
        List searchResultList = getGetSaleStateTotalInfoLogic().execute(getMosChickenSaleStateViewDto(),sysDate);

        //表示用データ設定
        MosChichenSaleStateUtil.setResult(getMosChickenSaleStateViewDto(), searchResultList, sysDate);
        
		// 再検索を実行
		return null;
	}

    /**
     * タイトル切替処理
     * 
     * @return 画面遷移情報
     */  
	public String onchangeTitle() {
    	MosChichenSaleStateUtil.setChangeTitleExecute(getMosChickenSaleStateViewDto());
		// 再検索を実行
		return null;
	}

    /**
     * ページ遷移処理
     * 
     * @return 画面遷移情報
     */  	
	public String changePage() {
		//画面に表示されているリストの最初日付
		String firstDate = getMosChickenSaleStateViewDto().getPageFirstDt();
		String sysDt = getBirdDateInfo().getSysDate();
		//一週間前
		if(getMosChickenSaleStateViewDto().getPageKbn().equals(MosChichenSaleStateUtil.PAGE_PROC_BEFWEK)) {	
			String prevWekDate = "";
			try {
				prevWekDate = DateManager.getPrevDate(firstDate, 7);
			} catch (Exception e) {
				throw new FtlSystemException("モスチキン販売状況一覧のページ遷移処理：一週間前");
			}
			MosChichenSaleStateUtil.setPageList(getMosChickenSaleStateViewDto(), prevWekDate, "PREV", sysDt);
		} else if(getMosChickenSaleStateViewDto().getPageKbn().equals(MosChichenSaleStateUtil.PAGE_PROC_BEFDAY)) {
		//一日前
			String prevOneDate = "";
			try {
				prevOneDate = DateManager.getPrevDate(firstDate, 1);
			} catch (Exception e) {
				throw new FtlSystemException("モスチキン販売状況一覧のページ遷移処理：一日前");
			}
			MosChichenSaleStateUtil.setPageList(getMosChickenSaleStateViewDto(), prevOneDate, "PREV", sysDt);
		} else if(getMosChickenSaleStateViewDto().getPageKbn().equals(MosChichenSaleStateUtil.PAGE_PROC_TODAY)) {
		//今日
			
			String startDt = getMosChickenSaleStateViewDto().getCondTaishoKikanCdFr();
			String endDt = getMosChickenSaleStateViewDto().getCondTaishoKikanCdTo();
			if(Integer.parseInt(sysDt) >= Integer.parseInt(startDt) &&
					Integer.parseInt(sysDt) <= Integer.parseInt(endDt)) {
				MosChichenSaleStateUtil.setPageList(getMosChickenSaleStateViewDto(), sysDt, "TODAY", sysDt);
			} else {
				MosChichenSaleStateUtil.setPageList(null, null, null, sysDt);
			}
		} else if(getMosChickenSaleStateViewDto().getPageKbn().equals(MosChichenSaleStateUtil.PAGE_PROC_AFTDAY	)) {
		//一日後
			String nextOneDate = "";
			try {
				nextOneDate = DateManager.getNextDate(firstDate,1);
			} catch (Exception e) {
				throw new FtlSystemException("モスチキン販売状況一覧のページ遷移処理：一日後");
			}
			MosChichenSaleStateUtil.setPageList(getMosChickenSaleStateViewDto(), nextOneDate, "NEXT", sysDt);
		} else if(getMosChickenSaleStateViewDto().getPageKbn().equals(MosChichenSaleStateUtil.PAGE_PROC_AFTWEK)) {
		//一週間後
			String nextWekDate = "";
			try {
				nextWekDate = DateManager.getNextDate(firstDate,7);
			} catch (Exception e) {
				throw new FtlSystemException("モスチキン販売状況一覧のページ遷移処理：一週間後");
			}
			MosChichenSaleStateUtil.setPageList(getMosChickenSaleStateViewDto(), nextWekDate, "NEXT", sysDt);
		}			
		// ページ遷移
		return null;
	}
}
