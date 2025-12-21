/*
 * 作成日: 2006/08/17
 *
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.action.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jp.co.isid.mos.bird.bill.demanddeposithistory.action.DemandDepositHistoryCondAction;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dto.DemandDepositHistoryDto;
import jp.co.isid.mos.bird.bill.demanddeposithistory.logic.GetSeikyuNyukinInfoLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * ご請求ご入金履歴、条件画面アクション
 * 
 * @author xwatanabe
 */
public class DemandDepositHistoryCondActionImpl implements CommonAction , DemandDepositHistoryCondAction {

    /** アクションID */
    public static final String initialize_ACTION_ID   	= "BBS009A01";
    public static final String execute_ACTION_ID         = "BBS009A02";
    public static final String callOnerForm_ACTION_ID    = "BBS009A03";
    
    /** ビューID */
    public static final String VIEW_ID_OWNERSEARCH       = "BCO006V01";   //オーナー検索フォーム
    public static final String VIEWID_DemanDepHisCond    = "BBS009V01";   //ご請求ご入金履歴条件画面
    public static final String VIEWID_DemanDepHis        = "BBS009V02";   //ご請求ご入金履歴画面
   
    /** DTO */
    private DemandDepositHistoryDto demandDepositHistoryDto;                //ご請求ご入金履歴DTO
    private OwnerSearchDto ownerSearchDto;                                  //オーナー検索DTO

    /** LOGIG */
    private GetSeikyuNyukinInfoLogic  getSeikyuNyukinInfoLogic;             //請求入金情報LOGIC

    /** 日付情報 */
    private BirdDateInfo birdDateInfo;

    /** ユーザータイプコード：01:本部 */
    public static final String USERTYPE_HONBU  = "01";
    /** ユーザータイプコード：02:オーナー */
    public static final String USERTYPE_ONER   = "02";
    /** ユーザータイプコード：03:店舗 */
    public static final String USERTYPE_MISE   = "03";

    /** モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
 

    /**
     * 初期化処理
     * @return 画面遷移情報
     */
    public String initialize() {
    	
        //画面遷移先ID
        String viewId = null;   //nullは自画面
        
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");           
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        DemandDepositHistoryDto demandDepositHistoryDto = (DemandDepositHistoryDto) container.getComponent(DemandDepositHistoryDto.class);

        //----------------------------------
        //メニュー画面より遷移してきた時
        //2006/10/27追加
        //または、BIRD内画面から遷移された時
        //----------------------------------
        if (pullDownMenuDto.isClearFlg() || (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {

            //DTOをクリア
            demandDepositHistoryDto.clear();
            //会社コードに「モスフード」をセット
            String companyCd = COMPANY_CD_MOS;
            demandDepositHistoryDto.setCompanyCd(companyCd);

            //ユーザタイプの取得
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            if (USERTYPE_HONBU.equals(userTypeCd)) {
                //①ユーザタイプが「本部」の時
                
                //DTOにセット
                demandDepositHistoryDto.setUserType(userTypeCd);    //ユーザタイプ
            } else if (USERTYPE_ONER.equals(userTypeCd)) {
                //②ユーザタイプが「オーナー」の時

                //オーナーコード取得
                String onerCd = "";
                List ownerList = birdUserInfo.getUserOner();
                for (Iterator it = ownerList.iterator(); it.hasNext();) {
                    UIUserOner uIUserOner = (UIUserOner) it.next();
                    if (uIUserOner.getCompanyCd().equals(companyCd)) {
                        onerCd = uIUserOner.getOnerCd();
                        break;
                    }
                }
                
                //DTOにセット
                demandDepositHistoryDto.setUserType(userTypeCd);     //ユーザタイプ
                demandDepositHistoryDto.setOnerCd(onerCd);           //オーナーコード
            }

            //後処理
            pullDownMenuDto.setClearFlg(false);

        } else {
        //----------------------------------
        //メニュー画面以外から遷移してきた時
        //----------------------------------
           
            // オーナ選択画面の戻り
            if (getOwnerSearchDto().isActionFlag()) {
                
                //オーナーコードの取得・セット
                String onerCd = getOwnerSearchDto().getOnerCd();
                demandDepositHistoryDto.setOnerCd(onerCd);          //オーナーコード

                //オーナーDTO後処理
                ownerSearchDto.setActionFlag(false);               //アクションフラグをセット
                
                //請求入金履歴画面の情報取得と次画面へのIDを返却
                viewId = execute();
            }
        }
        //2006/10/27 追加 start
        // BIRD内画面から遷移された場合の処理 
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            //オーナーコードをセット
            String onerCd = getCommonCodeDto().getOnerCd();
            demandDepositHistoryDto.setOnerCd(onerCd);    
            //実行
            execute();
            //一覧画面へ遷移
            return VIEWID_DemanDepHis;
        }
        
    	//次の画面に遷移
        return viewId;
    }
    
    /**
     * 検索ボタンの処理
     * オーナー検索画面に遷移する
     * @return オーナーコード検索画面のViewID
     */
    public String callOnerForm() {
    	       
       	//　オーナー検索フォームの設定
        OwnerSearchDto ownerSearchDto = getOwnerSearchDto();
        ownerSearchDto.clear();
        ownerSearchDto.setInitFlag(true);
        ownerSearchDto.setNavigationCase(VIEWID_DemanDepHisCond);
        
        // オーナー検索フォームへ遷移
        return VIEW_ID_OWNERSEARCH;
    }
       
    /**
     * 実行ボタンの処理実行
     * 請求入金情報を取得し、結果画面へ遷移する。
     * @return 画面遷移情報
     */
    public String execute(){
    	
    	//オーナーコードチェック
    	validate();
        
        //-------------------------------
        //請求入金情報取得ロジックの実行
        //-------------------------------
        String companyCd = demandDepositHistoryDto.getCompanyCd();  //会社コード
        String onerCd    = demandDepositHistoryDto.getOnerCd();     //オーナーコード
        String kenshuDt  = getBirdDateInfo().getKenshuDt();         //検収日付

        HashMap map = getSeikyuNyukinInfoLogic.execute(companyCd, onerCd, kenshuDt);

        //最新タブ内容の取得とDTOへのセット
        List saisinTabList = (List)(map.get("saisinTabList"));
        demandDepositHistoryDto.setSaisinTabList(saisinTabList);
        if(saisinTabList != null && !saisinTabList.isEmpty()) {
            demandDepositHistoryDto.setSaisinTabListSize(saisinTabList.size());
        }
        
        //履歴タブ内容の取得とDTOへのセット
        List rirekiTabList = (List)(map.get("rirekiTabList"));
        demandDepositHistoryDto.setRirekiTabList(rirekiTabList);
        if(rirekiTabList != null && !rirekiTabList.isEmpty()) {
            demandDepositHistoryDto.setRirekiTabListSize(rirekiTabList.size());
        }
        
    	//一覧画面へ遷移
    	return VIEWID_DemanDepHis;
    }
    
    /**
     * 入力チェック
     */
    private void validate() {
        // オーナーコード必須チェック
    	String onerCd = demandDepositHistoryDto.getOnerCd();
        if (onerCd == null || onerCd.equals("")) {
            throw new NotNullException("オーナーコード", "onerCd", "");
        }
        // 半角数字
		CodeVerifier codeVerifier = new CodeVerifier();
		if (onerCd != null && !onerCd.equals("")
				&& !codeVerifier.validate(onerCd)) {
			throw new InvalidInputException("オーナーコード", "onerCd", "");
		}
	    // オーナーコード桁数
	    if (onerCd != null && onerCd.getBytes().length > 5) {
	        throw new InvalidInputException("オーナーコード", "onerCd", "");
	    }
    }

    //////////////////////////////////////////////////////////////////////////////////
    
    /**
     * ご請求ご入金履歴DTOを取得します。
     * @return ご請求ご入金履歴DTO
     */
	public DemandDepositHistoryDto getDemandDepositHistoryDto() {
		return demandDepositHistoryDto;
	}
    /**
     * ご請求ご入金履歴DTOをセットします。
     * @return ご請求ご入金履歴DTO
     */
	public void setDemandDepositHistoryDto(DemandDepositHistoryDto demandDepositHistoryDto) {
		this.demandDepositHistoryDto = demandDepositHistoryDto;
	}

    /**
     * オーナ選択Dtoの取得
     * @param ownerSearchDto を戻します。
     */
	public OwnerSearchDto getOwnerSearchDto() {
		return ownerSearchDto;
	}
    /**
     * オーナ選択Dtoの設定
     * @return ownerSearchDto ownerSearchDto を設定。
     */
	public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
		this.ownerSearchDto = ownerSearchDto;
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
     * 請求入金情報Logicを取得します。
     * @return 請求入金情報Logic
     */
    public GetSeikyuNyukinInfoLogic getGetSeikyuNyukinInfoLogic() {
        return getSeikyuNyukinInfoLogic;
    }
    /**
     * 請求入金情報Logicをセットします。
     * @return 請求入金情報Logic
     */
    public void setGetSeikyuNyukinInfoLogic(
            GetSeikyuNyukinInfoLogic getSeikyuNyukinInfoLogic) {
        this.getSeikyuNyukinInfoLogic = getSeikyuNyukinInfoLogic;
    }
    
    /**
     * 共通コードDTO取得
     * @return 共通コードDTO
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) SingletonS2ContainerFactory.getContainer().getComponent(CommonCodeDto.class);
    }

}
