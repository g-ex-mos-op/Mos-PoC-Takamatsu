/*
 * 作成日: 2006/03/01
 *
 */
package jp.co.isid.mos.bird.communication.notificationreference.action.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.common.dto.PortalSearchInfoDto;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.communication.common.util.CommuniCationUtil;
import jp.co.isid.mos.bird.communication.notificationreference.action.NotificationReferenceAction;
import jp.co.isid.mos.bird.communication.notificationreference.dto.NotificationReferenceDto;
import jp.co.isid.mos.bird.communication.notificationreference.entity.UIViewTutatu;
import jp.co.isid.mos.bird.communication.notificationreference.logic.GetGyoutaiLogic;
import jp.co.isid.mos.bird.communication.notificationreference.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.action.FileDownloadAction;
import jp.co.isid.mos.bird.framework.action.impl.FileDownloadActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.FullTextSearchLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;



/**
 * 通達照会
 *
 * @author m.onodera
 * 更新日：2008/02/19 ASPAC T.Kinugawa WAS高負荷対応
 * 更新日：2009/06/22 ASPAC T.Kinugawa 既読リンク文字色変更対応
 */
public class NotificationReferenceActionImpl extends FileDownloadActionImpl implements FileDownloadAction, NotificationReferenceAction {

    // アクションID定義
    public static final String initialize_ACTION_ID = "BCM002A01";
    public static final String search_ACTION_ID     = "BCM002A02";
    public static final String changePage_ACTION_ID = "BCM002A03";
    public static final String changeTab_ACTION_ID  = "BCM002A04";
    /* 情報種別:通達 */
    protected static final String INFO_SHU = "02";

    // 通達文書情報
    private NotificationReferenceDto notificationReferenceDto;
    // プルダウン情報
    private PullDownMenuDto pullDownMenuDto;
    // FullTextSearch用
    private FullTextSearchDto fullTextSearchTutatuDto;
    // Birdユーザー情報
    private BirdUserInfo birdUserInfo;
    //Bird日付情報
    private BirdDateInfo birdDateInfo;
    /** BIRD共通DTO【画面間Request】*/
    private PortalSearchInfoDto portalSearchInfoDto;

    private String selectCateId;
    private int selectPageNumber;
    private int maxPageCount;
    /* 年月出力月数 */
    private static int NENGETU_DISPLAY_MONTH = 36;

    /*【ロジック】*/
    //業態の取得
    private GetGyoutaiLogic getGyoutaiLogic;
    //関連文書の取得
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;
    //FullTextSearch 共通ロジック
    private FullTextSearchLogic fullTextSearchLogic;

    private SearchLogic notificationReferenceSearchLogic;
/*--- DTO ---*/
    //通達文書情報
    public NotificationReferenceDto getNotificationReferenceDto() {
        return notificationReferenceDto;
    }
    public void setNotificationReferenceDto(NotificationReferenceDto notificationReferenceDto) {
        this.notificationReferenceDto = notificationReferenceDto;
    }
    //プルダウンメニュー情報
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }
    //FullTextSearch用
    public void setFullTextSearchTutatuDto(final FullTextSearchDto fullTextSearchTutatuDto) {
        this.fullTextSearchTutatuDto = fullTextSearchTutatuDto;
    }
	private FullTextSearchDto getFullTextSearchTutatuDto() {
	    return this.fullTextSearchTutatuDto;
    }

    /*--- LOGIC ---*/
    //業態情報
    public GetGyoutaiLogic getGetGyoutaiLogic() {
        return getGyoutaiLogic;
    }
    public void setGetGyoutaiLogic(GetGyoutaiLogic getGyoutaiLogic) {
        this.getGyoutaiLogic = getGyoutaiLogic;
    }
    //関連文書
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }
    //FullTextSearch 共通ロジック
    public void setFullTextSearchLogic(final FullTextSearchLogic fullTextSearchLogic) {
        this.fullTextSearchLogic = fullTextSearchLogic;
    }
    private FullTextSearchLogic getFullTextSearchLogic() {
        return this.fullTextSearchLogic;
    }


    public String getSelectCateId() {
        return selectCateId;
    }
    public void setSelectCateId(String selectCateId) {
        this.selectCateId = selectCateId;
    }

    public int getSelectPageNumber() {
        return selectPageNumber;
    }
    public void setSelectPageNumber(int selectPageNumber) {
        this.selectPageNumber = selectPageNumber;
    }
    public int getMaxPageCount() {
        return maxPageCount;
    }
    public void setMaxPageCount(int maxPageCount) {
        this.maxPageCount = maxPageCount;
    }



    /**
     *
 	 * 更新日:2009/06/22 xkinu 既読リンク文字色変更対応
 	 * 更新日:2013/09/03 周春建　201308-004_e-mosslesツールバーにお知らせ（タイトル検索）を追加
    */
    public String initialize() {

        //１．初期処理判定（メニューから遷移された場合のみ）
        if (getPullDownMenuDto().isClearFlg()) {
            //１－１．BIRD共通DTO【プルダウンメニュー】クリアフラグをfalseに設定します。
            getPullDownMenuDto().setClearFlg(false);
            // DTO初期化
            getNotificationReferenceDto().setPubDate(null);
            getNotificationReferenceDto().setPubDateTo(null);
            getNotificationReferenceDto().setInputPubDate(null);
            getNotificationReferenceDto().setGyotaiKbn(null);
            getNotificationReferenceDto().setInputGyotaiKbn(null);
            getNotificationReferenceDto().setTitle(null);
            getNotificationReferenceDto().setInputTitle(null);
            getNotificationReferenceDto().setSearchWord(null);
            getNotificationReferenceDto().setInputSearchWord(null);
            getNotificationReferenceDto().setCateId(null);
            getNotificationReferenceDto().setSelectedAttachFileIndex(null);
            getNotificationReferenceDto().setCategoryList(null);
            getNotificationReferenceDto().setTutatuList(null);
            getNotificationReferenceDto().setMapFileNameIndex(new HashMap());//xkinu 既読リンク文字色変更対応
            getNotificationReferenceDto().setTutatuCount(0);
            getFullTextSearchTutatuDto().setMatchingData(null);

            // 公開開始日リスト生成
            List listPubDate = createTargetDateList();
            getNotificationReferenceDto().setPubDateList(listPubDate);
            // 業態リスト取得
            getNotificationReferenceDto().setGyotaiList(
                    getGetGyoutaiLogic().execute(getBirdUserInfo().getUserID()));

            //今月の通達を取得
            getNotificationReferenceDto().setCateId(null);
            //システム日付のYYYYMM
            String fromMonth = "";
            try {
                fromMonth = DateManager.getPrevMonth(getBirdDateInfo().getSysDate().substring(0,6), 1);
            }
            catch (Exception ex) {
                throw new FtlSystemException("日付処理", null, ex);
            }
            getNotificationReferenceDto().setInputPubDate(fromMonth);
            getNotificationReferenceDto().setInputPubDateTo(getBirdDateInfo().getSysDate().substring(0,6));
            getNotificationReferenceDto().setInputGyotaiKbn(null);
            getNotificationReferenceDto().setInputTitle(null);
            getNotificationReferenceDto().setInputSearchWord(null);
            try{
        		//２．DTO【リクエスト元情報保持DTO】.アクション種類が”初期化”の場合、
        		//　　総合検索画面から呼び出されたと判断し、下記の処理を行います。
                if(CommonUtil.ACTION_KIND_INIT == getPortalSearchInfoDto().getActionKind()) {
                    //２－１.DTO【リクエスト元情報保持DTO】.アクション種類へ”稼動中”を設定します。
        			getPortalSearchInfoDto().setActionKind(CommonUtil.ACTION_KIND_EXEC);
					if ("2".equals(getPortalSearchInfoDto().getSearchKbnFlg())) {
						// 必須チェック
						if (CommonUtil.isNull(getPortalSearchInfoDto()
								.getSearchWord())) {
							throw new NoInputException("検索文字",
									"inputSearchWord", 0);
						}
						// ２－２．検索対象文字列をDTO【自画面】全文検索へ設定します。
						getNotificationReferenceDto().setInputSearchWord(
								getPortalSearchInfoDto().getSearchWord());
					} else {
						// 必須チェック
						if (CommonUtil.isNull(getPortalSearchInfoDto()
								.getSearchWord())) {
							throw new NoInputException("検索文字", "inputTitle", 0);
						}
						// ２－２．検索対象文字列をDTO【自画面】全文検索へ設定します。
						getNotificationReferenceDto().setInputTitle(
								getPortalSearchInfoDto().getSearchWord());
					}
	    			// ２－３．DTO【自画面】公開開始月FromへDTO【自画面】公開開始月.最後尾(最過去月)の値を設定します。
	    			SelectItem fromSelectItem = (SelectItem)(listPubDate.get(listPubDate.size()-1));
	    			getNotificationReferenceDto().setInputPubDate(fromSelectItem.getValue().toString());
                }
            	//３．検索処理を実行します。
    			execSearch();
            }catch (NoResultException e) {
                //４．該当データが存在しない場合で、かつ総合検索からのアクションの場合は、
            	//    結果なしのメッセージを出力する。
            	if(CommonUtil.ACTION_KIND_EXEC == getPortalSearchInfoDto().getActionKind()
            			&& !CommonUtil.isNull(getPortalSearchInfoDto().getSearchWord())) {
            		throw e;
            	}
            	//５．処理４以外の場合は、
                //結果なしのメッセージがでないようにハンドリング
            }
            finally {
                //６．DTO【リクエスト元情報保持DTO】.アクション種類へ”非稼動”を設定します。
    			getPortalSearchInfoDto().setActionKind(CommonUtil.ACTION_KIND_NULL);
            }

        }
        S2Container container = SingletonS2ContainerFactory.getContainer();
    	HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
    	String downloadFileName = (String)request.getParameter("downloadFileName");
    	if(downloadFileName != null && !"".equals(downloadFileName)){
    		download();
    	}
        //２．自画面へ遷移
        return null;
    }

    /**
     * 検索処理
     */
    public String search() {
    	execSearch();
    	return null;

    }
    /**
     * 検索処理
	 * 更新日:2009/06/22 xkinu 既読リンク文字色変更対応
     */
    private void execSearch() {
    	String searchWord = getNotificationReferenceDto().getInputSearchWord();
    	String title = getNotificationReferenceDto().getInputTitle();
        MetaCharVerifier verifier = new MetaCharVerifier();
        if (!CommonUtil.isNull(searchWord) && !verifier.validate(searchWord)) {
            throw new InvalidInputException("全文検索","inputSearchWord", 1);
        }
        if (!CommonUtil.isNull(title) && !verifier.validate(title)) {
            throw new InvalidInputException("タイトル","inputTitle", 1);
        }
        //初期化
        getNotificationReferenceDto().setCateId(null);
        getNotificationReferenceDto().setTutatuList(null);
        getNotificationReferenceDto().setMapFileNameIndex(new HashMap());//xkinu 既読リンク文字色変更対応
        getNotificationReferenceDto().setCategoryList(null);
        getFullTextSearchTutatuDto().setMatchingData(null);
        getNotificationReferenceDto().setCount(0);
        getNotificationReferenceDto().setTutatuCount(0);
        // ページ番号初期化
        getNotificationReferenceDto().setCurrentPageNumber(1);

        // 入力用一時プロパティから検索条件設定
        getNotificationReferenceDto().setPubDate(getNotificationReferenceDto().getInputPubDate());
        getNotificationReferenceDto().setPubDateTo(getNotificationReferenceDto().getInputPubDateTo());
        getNotificationReferenceDto().setGyotaiKbn(getNotificationReferenceDto().getInputGyotaiKbn());
        getNotificationReferenceDto().setTitle(getNotificationReferenceDto().getInputTitle());
        getNotificationReferenceDto().setSearchWord(getNotificationReferenceDto().getInputSearchWord());
        //全文検索項目
        String word = getNotificationReferenceDto().getSearchWord();

        Map params = new HashMap();
        params.put(SearchLogic.PK_USERINFO, birdUserInfo);
        params.put(SearchLogic.PK_PUBDATE, getNotificationReferenceDto().getPubDate());
        params.put(SearchLogic.PK_PUBDATE_TO, getNotificationReferenceDto().getPubDateTo());
        params.put(SearchLogic.PK_GYOTAIKBN, getNotificationReferenceDto().getGyotaiKbn());
        params.put(SearchLogic.PK_TITLE, getNotificationReferenceDto().getTitle());
        params.put(SearchLogic.PK_SYSDATE, birdDateInfo.getSysDate());

        //全文検索項目が空の場合はカテゴリ単位の表示を行なう。
        if(word == null || "".equals(word.trim())){

        }else{
            //全文検索項目の入力があった場合はFullTextSearch検索を行なう。

            getFullTextSearchTutatuDto().setSearchKikanFrom(null);
            getFullTextSearchTutatuDto().setSearchKikanTo(null);
            getFullTextSearchTutatuDto().setSearchWord(word.trim());

            // FullTextSearch検索
            getFullTextSearchLogic().search(getFullTextSearchTutatuDto());

            if (getFullTextSearchTutatuDto() == null || getFullTextSearchTutatuDto().getSearchResultList().isEmpty()) {
                throw new NoResultException();
            }
            params.put(SearchLogic.PK_FULLTEXTSEARCH_DTO, getFullTextSearchTutatuDto());
        }

        //LOGIC【通達情報検索取得処理】を実行する。
        Map rparams = getNotificationReferenceSearchLogic().execute(params);
        List tutatuList = (List)rparams.get(SearchLogic.RK_LIST_SEARCHDATA);
        if (tutatuList == null || tutatuList.size() <= 0) {
        	throw new NoResultException();
        }
        // カテゴリリスト取得
        List categoryList = (List)rparams.get(SearchLogic.RK_LIST_SEARCHDATA_CATEGORY);
        // カテゴリリスト設定
        getNotificationReferenceDto().setCategoryList(categoryList);
        //全文検索 後処理
        if(!(word == null || "".equals(word.trim()))) {
        	getFullTextSearchTutatuDto().setFileAccessInfoEntity(tutatuList);
            getFullTextSearchLogic().matchingData(getFullTextSearchTutatuDto());
            tutatuList = getFullTextSearchTutatuDto().getMatchingData();
            if (tutatuList == null || tutatuList.size() <= 0) {
            	throw new NoResultException();
            }
        }

        //全件検索結果リスト設定
        getNotificationReferenceDto().setListAllTutatu(tutatuList);
        //表示対象タブの通達情報設定処理
        getNotificationReferenceDto().settingListTutatu();
        //関連文書情報取得
        setKanrenFile(getNotificationReferenceDto().getTutatuList());

        //件数をセット
        getNotificationReferenceDto().setTutatuCount(getNotificationReferenceDto().getCount());

        setFullTextSearchTutatuDto(null);
    }
    /**
     *
     */
    public String changePage() {
        //選択されたページ番号を設定
        getNotificationReferenceDto().setCurrentPageNumber(getSelectPageNumber());
        //表示対象タブの通達情報設定処
        getNotificationReferenceDto().settingListTutatu();
        //関連文書情報取得
        setKanrenFile(getNotificationReferenceDto().getTutatuList());
        // 自画面へ遷移
        return null;
    }

    public String changeTab() {
        getNotificationReferenceDto().setCount(0);
        getNotificationReferenceDto().setTutatuCount(0);
        // ページ番号初期化
        getNotificationReferenceDto().setCurrentPageNumber(1);
        // 選択されたカテゴリIDを設定
        getNotificationReferenceDto().setCateId(getSelectCateId());
        //表示対象タブの通達情報設定処
        getNotificationReferenceDto().settingListTutatu();
        //関連文書情報取得
        setKanrenFile(getNotificationReferenceDto().getTutatuList());
        // 件数をセット
        getNotificationReferenceDto().setTutatuCount(getNotificationReferenceDto().getCount());

        // 自画面へ遷移
        return null;
    }
    /**
     * 関連文書ファイル設定処理
     *
     * @param listTutatu
     */
    private void setKanrenFile(List listTutatu) {
        String sysDate = getBirdDateInfo().getSysDate();
        String publicTutatuDate = CommuniCationUtil.getTutatuPubDate(sysDate);
        //関連文書情報取得
        for (Iterator it = listTutatu.iterator(); it.hasNext();) {
        	//１．対象[通達情報]を取得する。
            UIViewTutatu uiViewTutatu = (UIViewTutatu) it.next();
            //関連文書保持件数チェック
            int cnt = uiViewTutatu.getKanrenFileCnt();
            if(cnt>0) {
                //関連文書保持件数が1件以上あるデータの関連文書情報を取得する。
            	if(uiViewTutatu.getKanrenFileList() != null){
            		//既に関連文書情報を取得している場合は、スキップする。
            		continue;
            	}
        		//関連文書保持件数が1件以上あるデータの関連文書情報を取得する。
                String regDate = uiViewTutatu.getRegDate();
                String seq = uiViewTutatu.getSeq();
                //LOGIC【関連文書取得】を実行し、[[関連文書]]を取得する。
                List listKanrenBunsho = getGetKanrenBunshoInfoLogic().execute(
                		getBirdUserInfo().getUserID(),
                		INFO_SHU, regDate, seq, sysDate, publicTutatuDate);
                //対象[通達情報]へ[[関連文書]]を設定する。
                uiViewTutatu.setKanrenFileList(listKanrenBunsho);
            }else{
            	//1件以上関連文書があるデータ以外関連文書情報を取得しない。
                uiViewTutatu.setKanrenFileList(new ArrayList());
            }
        }
    }
    /**
     * 対象年月リスト作成
     *
     * @param baseDate 基準日付
     * @param range 月数
     * @return 対象年月リスト
     */
    private List createTargetDateList() {

        DateFormatter formatter = new DateFormatter();

        // 現在日付取得
        String sysDate = getBirdDateInfo().getSysDate();
        String sysMonth = formatter.format(sysDate, DateFormatter.PATTERN_MONTH, DateFormatter.DATE_TYPE_YMD);

        // 現在月から過去24ヶ月分の年月リスト作成
        List listMonth = new ArrayList();
        for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(sysMonth, i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("条件画面初期処理");
            }

            SelectItem item = new SelectItem(
                                        month,
                                        formatter.format(month, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM));
            listMonth.add(item);
        }

        return listMonth;
    }
	/**
	 * @return birdDateInfo を戻します。
	 */
	public BirdDateInfo getBirdDateInfo() {
		return birdDateInfo;
	}
	/**
	 * @param birdDateInfo 設定する birdDateInfo。
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
	 * @param birdUserInfo 設定する birdUserInfo。
	 */
	public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
		this.birdUserInfo = birdUserInfo;
	}
	/**
	 * @return notificationReferenceSearchLogic を戻します。
	 */
	public SearchLogic getNotificationReferenceSearchLogic() {
		return notificationReferenceSearchLogic;
	}
	/**
	 * @param notificationReferenceSearchLogic 設定する notificationReferenceSearchLogic。
	 */
	public void setNotificationReferenceSearchLogic(
			SearchLogic notificationReferenceSearchLogic) {
		this.notificationReferenceSearchLogic = notificationReferenceSearchLogic;
	}
	/**
	 * @return portalSearchInfoDto を戻します。
	 */
	public PortalSearchInfoDto getPortalSearchInfoDto() {
		return portalSearchInfoDto;
	}
	/**
	 * @param portalSearchInfoDto を クラス変数portalSearchInfoDtoへ設定します。
	 */
	public void setPortalSearchInfoDto(PortalSearchInfoDto portalSearchInfoDto) {
		this.portalSearchInfoDto = portalSearchInfoDto;
	}
    public void download() throws ApplicationException {
    	S2Container container = SingletonS2ContainerFactory.getContainer();
    	HttpServletRequest request = (HttpServletRequest) container.getComponent("request");
    	getNotificationReferenceDto().setDownloadFileName((String)request.getParameter("downloadFileName"));
    	super.download();
    }

}