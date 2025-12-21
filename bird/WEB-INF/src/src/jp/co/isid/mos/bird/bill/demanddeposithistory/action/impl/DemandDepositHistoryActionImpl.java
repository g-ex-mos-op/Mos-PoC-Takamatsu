/*
 * 作成日: 2006/08/17
 *
 */
package jp.co.isid.mos.bird.bill.demanddeposithistory.action.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.bill.demanddeposithistory.action.DemandDepositHistoryAction;
import jp.co.isid.mos.bird.bill.demanddeposithistory.dto.DemandDepositHistoryDto;
import jp.co.isid.mos.bird.bill.demanddeposithistory.logic.GetSeikyuNyukinInfoLogic;
import jp.co.isid.mos.bird.bill.demanddeposithistory.logic.GetSeikyuUtiwakeInfoLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CommonCodeDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * ご請求ご入金履歴、結果画面アクション
 *
 * @author xwatanabe
 */
public class DemandDepositHistoryActionImpl implements DemandDepositHistoryAction {

    /** アクションID */
    public static final String initialize_ACTION_ID        = "BBS009A04";
    public static final String back_ACTION_ID              = "BBS009A05";
    public static final String selectDetail_ACTION_ID      = "BBS009A06";

    /** ビューID */
    private static final String VIEWID_DemanDepHis         = "BBS009V02";   //ご請求ご入金履歴画面
    private static final String VIEWID_DemanDepHisUtiwake  = "BBS009V03";   //ご請求ご入金履歴内訳画面
    private static final String VIEW_ID_OWNERSEARCH        = "BCO006V01";   //オーナー検索フォーム

    /** DTO */
    private DemandDepositHistoryDto demandDepositHistoryDto;                //ご請求ご入金履歴DTO
    private OwnerSearchDto ownerSearchDto;                                  //オーナー検索DTO

    /** LOGIG */
    private GetSeikyuNyukinInfoLogic  getSeikyuNyukinInfoLogic;             //請求入金情報LOGIC
    private GetSeikyuUtiwakeInfoLogic getSeikyuUtiwakeInfoLogic;            //請求内訳情報LOGIC

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

    /** パラメータ */
    String seikyushoId;     //請求書ID
    String urikakeCd;       //売掛先コード


    /**
     * 初期化処理
     * @return 画面遷移情報
     */
    public String initialize() {
        //画面遷移先ID
        String viewId = null;   //nullは自画面
        S2Container container = SingletonS2ContainerFactory.getContainer();
        HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
        BirdUserInfo birdUserInfo = (BirdUserInfo) request.getSession().getAttribute("birdUserInfo");
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        DemandDepositHistoryDto demandDepositHistoryDto = (DemandDepositHistoryDto) container.getComponent(DemandDepositHistoryDto.class);

        if (pullDownMenuDto.isClearFlg() || (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto())) {
        //***********************
        //山内追加スタート
        //**********************
        //DTOをクリア
        demandDepositHistoryDto.clear();
        String companyCd = COMPANY_CD_MOS;
        demandDepositHistoryDto.setCompanyCd(companyCd);
        String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
        if (USERTYPE_HONBU.equals(userTypeCd)) {
            //①ユーザタイプが「本部」の時

            //DTOにセット
            demandDepositHistoryDto.setUserType(userTypeCd);    //ユーザタイプ
        } else if (USERTYPE_ONER.equals(userTypeCd)) {
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
            demandDepositHistoryDto.setUserType(userTypeCd);     //ユーザタイプ
            demandDepositHistoryDto.setOnerCd(onerCd);           //オーナーコード
            validate();
            onerexeqt() ;
        }
        //画面遷移
        //return viewId;
        // return viewId;
        pullDownMenuDto.setClearFlg(false);

        } else {
                // オーナ選択画面の戻り
                if (getOwnerSearchDto().isActionFlag()) {

                    //オーナーコードの取得・セット
                    String onerCd = getOwnerSearchDto().getOnerCd();
                    demandDepositHistoryDto.setOnerCd(onerCd);          //オーナーコード

                    //オーナーDTO後処理
                    ownerSearchDto.setActionFlag(false);               //アクションフラグをセット

                    //請求入金履歴画面の情報取得と次画面へのIDを返却
                    viewId = searchAgain();

                }
            }
        //2006/10/27 追加 start
        // BIRD内画面から遷移された場合の処理
        if (getCommonCodeDto() != null && getCommonCodeDto().getUseCommonDto()) {
            //オーナーコードをセット
            String onerCd = getCommonCodeDto().getOnerCd();
            demandDepositHistoryDto.setOnerCd(onerCd);
            //実行
            onerexeqt();
            //一覧画面へ遷移
            return viewId;
        }
        return viewId;
        }
    /**
     * 請求内訳情報の取得
     * 内訳画面へ取得と遷移
     */
    public String selectDetail(){

        //パラメータ取得
        String companyCd = demandDepositHistoryDto.getCompanyCd();  //会社コード
        String seikyushoId = this.seikyushoId;                     //請求書ID
        String urikakeCd = this.urikakeCd;                         //売掛先コード

        //-------------------------------
        //請求内訳情報取得ロジックの実行
        //-------------------------------
        HashMap hmap = getSeikyuUtiwakeInfoLogic.execute(companyCd, urikakeCd, seikyushoId);

        //DTOにセット
        //-------------------------------
        //各リストをDTOにセット
        //-------------------------------
        List tmp;

        //内訳請求書履歴リスト
        tmp = (List)hmap.get("utiwakeBillList");
        demandDepositHistoryDto.setUtiwakeBillList(tmp);
// add 2023/02/07 USI範  begin
        //税率別の内訳
        tmp = (List)hmap.get("tenPercentList");
        demandDepositHistoryDto.setTenPercentList(tmp);
        tmp = (List)hmap.get("gennZeiList");
        demandDepositHistoryDto.setGennZeiList(tmp);
        tmp = (List)hmap.get("keikaSotiList");
        demandDepositHistoryDto.setKeikaSotiList(tmp);
        tmp = (List)hmap.get("hiKazeiList");
        demandDepositHistoryDto.setHiKazeiList(tmp);
// add 2023/02/07 USI範  end
        //店別請求情報リスト
        tmp = (List)hmap.get("miseSeikyuList");
        demandDepositHistoryDto.setMiseSeikyuList(tmp);
        if (tmp != null) {
            demandDepositHistoryDto.setMiseSeikyuListSize(tmp.size());
        } else {
            demandDepositHistoryDto.setMiseSeikyuListSize(0);
        }

        //オーナー商品分類別請求履歴リスト
        tmp = (List)hmap.get("onerSeikyuBnruiList");
        demandDepositHistoryDto.setOnerSeikyuBnruiList(tmp);

        //店商品分類別請求履歴リスト
        tmp = (List)hmap.get("miseSeikyuBnruiList");
        demandDepositHistoryDto.setMiseSeikyuBnruiList(tmp);
        if (tmp != null) {
            demandDepositHistoryDto.setMiseSeikyuBnruiListSize(tmp.size());
        } else {
            demandDepositHistoryDto.setMiseSeikyuBnruiListSize(0);
        }

        //内訳画面へ遷移
        return VIEWID_DemanDepHisUtiwake;
    }

    /**
     * 再検索ボタン
     * 指定されたオーナーコードでの請求・入金情報を取得し、自画面を再表示する
     * @return
     */
    public String searchAgain() {

        //表示内容クリア
        demandDepositHistoryDto.setSaisinTabList(null);
        demandDepositHistoryDto.setSaisinTabListSize(0);
        demandDepositHistoryDto.setRirekiTabList(null);
        demandDepositHistoryDto.setRirekiTabListSize(0);

        //オーナーコードチェック
        validate();

        //-------------------------------
        //請求入金情報取得ロジックの実行
        //-------------------------------
        String companyCd = demandDepositHistoryDto.getCompanyCd();  //会社コード
        String onerCd    = demandDepositHistoryDto.getOnerCd();     //オーナーコード
        String kenshuDt  = getBirdDateInfo().getKenshuDt();         //検収日付

        HashMap map = getSeikyuNyukinInfoLogic.execute(companyCd, onerCd, kenshuDt);
// add 2023/02/07 USI範  begin
        //最新タブ内容の取得とDTOへのセット
        List tenPercentList = (List)(map.get("tenPercentList"));
        demandDepositHistoryDto.setTenPercentList(tenPercentList);

        List gennZeiList = (List)(map.get("gennZeiList"));
        demandDepositHistoryDto.setGennZeiList(gennZeiList);

        List keikaSotiList = (List)(map.get("keikaSotiList"));
        demandDepositHistoryDto.setKeikaSotiList(keikaSotiList);

        List hiKazeiList = (List)(map.get("hiKazeiList"));
        demandDepositHistoryDto.setHiKazeiList(hiKazeiList);
// add 2023/02/07 USI範  end
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

        //自画面へ遷移
        return null;
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
        ownerSearchDto.setNavigationCase(VIEWID_DemanDepHis);

        // オーナー検索フォームへ遷移
        return VIEW_ID_OWNERSEARCH;
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

    private void onerexeqt() {
        //*********************
         //山内追加分
         //*********************


         //-------------------------------
         //請求入金情報取得ロジックの実行
         //-------------------------------
         String companyCd = demandDepositHistoryDto.getCompanyCd();  //会社コード
         String onerCd    = demandDepositHistoryDto.getOnerCd();     //オーナーコード
         String kenshuDt  = getBirdDateInfo().getKenshuDt();         //検収日付

         HashMap map = getSeikyuNyukinInfoLogic.execute(companyCd, onerCd, kenshuDt);

// add 2023/02/07 USI範 begin
         //今回お買上額内訳内容の取得とDTOへのセット
         List tenPercentList = (List)(map.get("tenPercentList"));
         demandDepositHistoryDto.setTenPercentList(tenPercentList);

         List gennZeiList = (List)(map.get("gennZeiList"));
         demandDepositHistoryDto.setGennZeiList(gennZeiList);

         List keikaSotiList = (List)(map.get("keikaSotiList"));
         demandDepositHistoryDto.setKeikaSotiList(keikaSotiList);

         List hiKazeiList = (List)(map.get("hiKazeiList"));
         demandDepositHistoryDto.setHiKazeiList(hiKazeiList);
// add 2023/02/07 USI範  end
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

         //*********************
         //山内追加分終わり
         //*********************
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
	public void setDemandDepositHistoryDto(
			DemandDepositHistoryDto demandDepositHistoryDto) {
		this.demandDepositHistoryDto = demandDepositHistoryDto;
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
     * 請求内訳情報LOGICを取得します。
     * @return 請求内訳情報LOGIC
     */
    public GetSeikyuUtiwakeInfoLogic getGetSeikyuUtiwakeInfoLogic() {
        return getSeikyuUtiwakeInfoLogic;
    }
    /**
     * 請求内訳情報LOGICをセットします。
     * @return 請求内訳情報LOGIC
     */
    public void setGetSeikyuUtiwakeInfoLogic(GetSeikyuUtiwakeInfoLogic getSeikyuUtiwakeInfoLogic) {
        this.getSeikyuUtiwakeInfoLogic = getSeikyuUtiwakeInfoLogic;
    }

    /**
     * 請求書IDを設定します。
     * @param  seikyushoId 請求書ID
     */
    public void setSeikyushoId(String seikyushoId) {
        this.seikyushoId = seikyushoId;
    }
    /**
     * 請求書IDを取得します。
     * @return 請求書ID
     */
    public String getSeikyushoId() {
        return seikyushoId;
    }

    /**
     * 売掛先コードを設定します。
     * @param  urikakeCd 売掛先コードD
     */
    public void setUrikakeCd(String urikakeCd) {
        this.urikakeCd = urikakeCd;
    }
    /**
     * 売掛先コードを取得します。
     * @return 売掛先コード
     */
    public String getUrikakeCd() {
        return urikakeCd;
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
     * 共通コードDTO取得
     * @return 共通コードDTO
     */
    private CommonCodeDto getCommonCodeDto() {
        return (CommonCodeDto) SingletonS2ContainerFactory.getContainer().getComponent(CommonCodeDto.class);
    }
}
