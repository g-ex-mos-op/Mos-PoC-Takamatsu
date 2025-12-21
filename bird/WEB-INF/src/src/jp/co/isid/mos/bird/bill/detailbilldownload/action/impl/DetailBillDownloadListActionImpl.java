/*
 * 作成日: 2006/08/03
 *
 */
package jp.co.isid.mos.bird.bill.detailbilldownload.action.impl;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import jp.co.isid.mos.bird.bill.detailbilldownload.action.DetailBillDownloadListAction;
import jp.co.isid.mos.bird.bill.detailbilldownload.dto.DetailBillDownloadListDto;
import jp.co.isid.mos.bird.bill.detailbilldownload.logic.GetSeikyuListLogic;
import jp.co.isid.mos.bird.bill.detailbilldownload.logic.GetUrikakeListLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import java.util.Iterator;
import java.util.List;

/**
 * ご請求明細ダウンロードアクション
 * 
 * @author xwatanabe
 */
public class DetailBillDownloadListActionImpl implements DetailBillDownloadListAction {

    /** ビューID */
    private static final String VIEWID_BILLDOWNLOAD          = "BBS010V01"; //ご請求明細ダウンロード
    private static final String VIEWID_ONERSEARCH            = "BCO006V01"; //オーナー選択画面

    /** アクションID定義 */
    public static final String initialize_ACTION_ID          = "BBS010A01"; //初期処理
    public static final String execution01_ACTION_ID         = "BBS010A02"; //オーナー選択
    public static final String callSearchForm_ACTION_ID      = "BBS010A03"; //請求先一覧の表示

    /** モス会社コード */
    private static final String COMPANY_CD_MOS = "00";

    /** ユーザータイプ */
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_ONER  = "02";

    //DTO
    private OwnerSearchDto ownerSearchDto;  /* オーナ検索情報用Dto */

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
        
        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        DetailBillDownloadListDto detailBillDownloadListDto = (DetailBillDownloadListDto) container.getComponent(DetailBillDownloadListDto.class);
        GetUrikakeListLogic getUrikakeListLogic = (GetUrikakeListLogic) container.getComponent("detailBillDownload.getUrikakeListLogic");
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);

        //----------------------------------
        //メニュー画面より遷移してきた時
        //----------------------------------
        if (pullDownMenuDto.isClearFlg()) {

            //DTOをクリア
            detailBillDownloadListDto.clear();
            //会社コードに「モスフード」をセット
            String companyCd = COMPANY_CD_MOS;
            detailBillDownloadListDto.setCompanayCd(companyCd);

            //ユーザタイプの取得
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            if (USER_TYPE_HONBU.equals(userTypeCd)) {
                //①ユーザタイプが「本部」の時

                //DTOにセット
                detailBillDownloadListDto.setUserTypeCd(userTypeCd);   //ユーザタイプ
                detailBillDownloadListDto.setOnerCdAreaFlg(true);     //オーナーコード選択エリアの表示

            } else if (USER_TYPE_ONER.equals(userTypeCd)) {
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

                //売掛先リスト(ご請求先一覧)取得
                List urikakeList = getUrikakeListLogic.execute(companyCd, onerCd);

                //DTOにセット
                detailBillDownloadListDto.setUrikakeList(urikakeList);      //売掛先リスト
                detailBillDownloadListDto.setUserTypeCd(userTypeCd);        //ユーザタイプ
                detailBillDownloadListDto.setOnerCd(onerCd);                //オーナーコード
                detailBillDownloadListDto.setUrikakeListAreaFlg(true);     //請求先選択エリアの表示
            }
            //後処理
            pullDownMenuDto.setClearFlg(false);

        } else {
        //----------------------------------
        //メニュー画面以外から遷移してきた時
        //----------------------------------
            // オーナ選択結果取得
            if (getOwnerSearchDto().isActionFlag()) {
                
                String onerCd = getOwnerSearchDto().getOnerCd();
                String companyCd = detailBillDownloadListDto.getCompanayCd();

                //売掛先リスト取得
                List urikakeList = getUrikakeListLogic.execute(companyCd, onerCd);
                
                //DTOにセット
                detailBillDownloadListDto.setOnerCd(onerCd);               //オーナーコード
                detailBillDownloadListDto.setUrikakeList(urikakeList);     //売掛先リスト
                detailBillDownloadListDto.setUrikakeListAreaFlg(true);    //請求先一覧選択エリア・表示
                detailBillDownloadListDto.setSeikyuListAreaFlg(false);    //請求書一覧選択エリア・非表示
                getOwnerSearchDto().setActionFlag(false);
            }
        }
        
        //自画面へ遷移
        return null;
    }

    /**
     * オーナーコード検索画面への遷移処理
     * @return オーナーコード検索画面のViewID
     */
    public String callSearchForm() {
        
        //オーナーコード検索画面に遷移
        OwnerSearchDto ownerSearchDto = getOwnerSearchDto();
        ownerSearchDto.clear();
        ownerSearchDto.setInitFlag(true);
        ownerSearchDto.setNavigationCase(VIEWID_BILLDOWNLOAD);

        return VIEWID_ONERSEARCH;
    }
    
    /**
     * 実行ボタン(1)が押された時の処理
     * 入力されたオーナーコードより請求先(売掛先)一覧のリストを取得する
     * @return 
     */
    public String execution01() {
        
        //パラメータ取得
        String companyCd = detailBillDownloadListDto.getCompanayCd();   //会社コード
        String onerCd = detailBillDownloadListDto.getOnerCd();          //オーナーコード
            
        //売掛先リスト取得
        List urikakeList = getUrikakeListLogic.execute(companyCd, onerCd);
        
        //DTOにセット
        detailBillDownloadListDto.setUrikakeList(urikakeList);
        detailBillDownloadListDto.setUrikakeListAreaFlg(true);    //請求先一覧選択エリア・表示
        detailBillDownloadListDto.setSeikyuListAreaFlg(false);    //請求書一覧選択エリア・非表示

        //自画面へ遷移
        return null;
    }

    /**
     * 実行ボタン(2)が押された時の処理
     * 選択された「売掛先コード」を元に請求書一覧を取得する。
     * @return 
     */
    public String execution02() {

        //パラメータ取得
        String companyCd = detailBillDownloadListDto.getCompanayCd();   //会社コード
        String onerCd    = detailBillDownloadListDto.getOnerCd();       //オーナーコード
        String urikakeCd = detailBillDownloadListDto.getUrikakeCd();    //売掛先コード
        
        //検収日付取得
        String kenshuDt = getBirdDateInfo().getKenshuDt();

        //請求書一覧取得
        List seikyuList = getSeikyuListLogic.execute(companyCd, onerCd, urikakeCd, kenshuDt);

        //最新の情報(リストの最後)のインデックスを保持しておく
        int lastIndex = seikyuList.size() - 1;
            
        //DTOにセット
        detailBillDownloadListDto.setSeikyuList(seikyuList);        //請求書一覧リスト
        detailBillDownloadListDto.setLastIndex(lastIndex);          //請求書一覧リストのサイズ
        detailBillDownloadListDto.setRadioNengetu(lastIndex);       //請求書一覧リストの初期選択
        detailBillDownloadListDto.setSeikyuListAreaFlg(true);      //請求書一覧選択エリアの表示

        //自画面に遷移
        return null;
    }

    
    /**
     * ご請求明細ダウンロードDTO
     */
    private DetailBillDownloadListDto detailBillDownloadListDto;
    /**
    * ご請求明細ダウンロードDTOを取得します。
    * @return ご請求明細ダウンロードDTO
    */
    public DetailBillDownloadListDto getDetailBillDownloadListDto() {
        return detailBillDownloadListDto;
    }
    /**
    * ご請求明細ダウンロードDTOを設定します。
    * @param detailBillDownloadListDto ご請求明細ダウンロード
    */
    public void setDetailBillDownloadListDto(DetailBillDownloadListDto detailBillDownloadListDto) {
        this.detailBillDownloadListDto = detailBillDownloadListDto;
    }

    
    /**
     * 売掛先リスト取得ロジック
     */
    private GetUrikakeListLogic getUrikakeListLogic;

    /**
     * 売掛先リスト取得ロジックを取得します。
     * @return 売掛先リスト取得ロジック
     */
    public GetUrikakeListLogic getGetUrikakeListLogic() {
         return getUrikakeListLogic;
    }
    /**
    * 売掛先リスト取得ロジックを設定します。
    * @param getUrikakeListLogic 売掛先リスト取得ロジック
    */
    public void setGetUrikakeListLogic(GetUrikakeListLogic getUrikakeListLogic) {
        this.getUrikakeListLogic = getUrikakeListLogic;
    }
    
    /**
     * 請求書一覧取得ロジック
     */
     private GetSeikyuListLogic getSeikyuListLogic;
    /**
     * 請求書一覧取得ロジックを取得します。
     * @return 請求書一覧取得ロジック
     */
     public GetSeikyuListLogic getGetSeikyuListLogic() {
         return getSeikyuListLogic;
     }
      /**
      * 請求書一覧取得ロジックを設定します。
      * @param getUrikakeListLogic 請求書一覧ロジック
      */
      public void setGetSeikyuListLogic(GetSeikyuListLogic getSeikyuListLogic) {
          this.getSeikyuListLogic = getSeikyuListLogic;
      }

      /**
      * オーナ選択Dtoの取得
      * @return ownerSearchDto を戻します。
      */
     public OwnerSearchDto getOwnerSearchDto() {
         return ownerSearchDto;
     }
     /**
      * オーナ選択Dtoの設定
      * @param ownerSearchDto ownerSearchDto を設定。
      */
     public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
         this.ownerSearchDto = ownerSearchDto;
     }

    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;
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
}
