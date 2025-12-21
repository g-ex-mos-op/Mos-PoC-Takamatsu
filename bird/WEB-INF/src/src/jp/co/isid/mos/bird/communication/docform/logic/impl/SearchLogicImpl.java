/**
 *
 */
package jp.co.isid.mos.bird.communication.docform.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.UIViewKanrenBunsho;
import jp.co.isid.mos.bird.common.logic.GetKanrenBunshoInfoLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.communication.common.util.CommuniCationUtil;
import jp.co.isid.mos.bird.communication.docform.code.SearchField;
import jp.co.isid.mos.bird.communication.docform.common.DocFormCont;
import jp.co.isid.mos.bird.communication.docform.dao.UIDocFormDao;
import jp.co.isid.mos.bird.communication.docform.dto.RequestDto;
import jp.co.isid.mos.bird.communication.docform.entity.UIViewDocFormInfo;
import jp.co.isid.mos.bird.communication.docform.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.FullTextSearchDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.FullTextSearchLogic;
import jp.co.isid.mos.bird.framework.text.validator.DateValidator;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.MetaCharVerifier;
//import com.ultraseek.xpa.search.SearchResult;
import net.n2sm.search.client.api.SearchResult;

/**
 * 作成日:2011/02/25
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {

    /*【DAO】文書フォーム共通検索情報 */
    private UIDocFormDao docformUIDocFormDao;
    //【ロジック】FullTextSearch 共通ロジック
    private FullTextSearchLogic fullTextSearchLogic;
    //関連文書検索
    private GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic;

    /**
     * 事前条件
     * @param String infoShu 情報種別
     * @param String cateId カテゴリID
     * @param String title タイトル
     * @param String sysDate 日付
     * @param BirdUserInfo
     * @param int pageNo ページ番号
     * @throws ApplicationException
     */
    private void validate(
    		BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo
    		, String infoShu, String mapKeyExistFlg
    		, RequestDto requestDto) {
    	//カテゴリID
        String cateId = requestDto.getSearchCateId();
        //全文検索
        String searchWord = requestDto.getSearchWord();
        //タイトル
        String title = requestDto.getSearchTitle();
        //システム日付
        String sysDate = birdDateInfo.getSysDate();
        //リクエストページ番号
        int pageNo = requestDto.getCurrentPageNumber();
        //日付バリデーター
        DateValidator dateValidator = new DateValidator(
                DateVerifier.DATE_TYPE_YM);

        // カテゴリID 、 タイトルのどちらかは必須
        if (CommonUtil.isNull(cateId)
        		&& (CommonUtil.isNull(title) && CommonUtil.isNull(searchWord)))
        {
            throw new NotNullException("カテゴリまたは全文検索かタイトル");
        }
        // システム日付
        if (CommonUtil.isNull(sysDate)) {
            throw new InvalidInputException("日付");
        }
        dateValidator.validate(sysDate);

        //BIRDユーザー情報
        if (birdUserInfo == null) {
            throw new NotNullException("ユーザー情報");
        }

        // ページ
        if (pageNo == 0) {
            throw new NotNullException("ページ");
        }

        // タイトルに「%」、「％」が入力されていないか
        if (!CommonUtil.isNull(title)) {
            MetaCharVerifier verifier = new MetaCharVerifier();
            if (!verifier.validate(title)) {
                throw new InvalidInputException("タイトル");
            }
        }
    }
	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.communication.docform.logic.SearchLogic#execute(jp.co.isid.mos.bird.communication.docform.dto.RequestDto, jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.framework.dto.FullTextSearchDto)
	 */
	public Map execute(BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo
			, String infoShu, String mapKeyExistFlg
			, RequestDto requestDto, FullTextSearchDto fullTextSearchDto)
	{
        // 事前条件
        validate(birdDateInfo, birdUserInfo, infoShu, mapKeyExistFlg, requestDto);

        Map mapRet = new HashMap();
        //２．戻り値Mapへfalseの値を自画面static定数保持クラスの文書データの存在フラグをキーに設定します。
        mapRet.put(mapKeyExistFlg, Boolean.FALSE);
    	//カテゴリID
        String cateId = CommonUtil.isNull(requestDto.getSearchCateId())?null:requestDto.getSearchCateId();
    	//対象タブ
        String targetTab = CommonUtil.isNull(cateId)?null:requestDto.getTargetTab();
        //タイトル
        String title = null;
        //システム日付
        String sysDate = birdDateInfo.getSysDate();
        //リクエストページ番号
        int pageNo = requestDto.getCurrentPageNumber();
        //検索タイプ
        String searchField = requestDto.getSearchField();
        String defSubCate = targetTab;

		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		String userId = birdUserInfo.getUserID();
		boolean kobetsuFlg = true;
		/* BT15IAID 情報アクセス制御業態個別設定 */
		if (UserType.HONBU.equals(userTypeCd)) {
		    // 本部：ユーザー対応オーナーが存在する（＝販社）場合以外は、個別設定の条件を除外する。
		    if (birdUserInfo.getUserOner() == null
		            || birdUserInfo.getUserOner().size() == 0) {
		    	kobetsuFlg = false;
		    }
		}
        boolean isFullTextSearchExec = false;
        //FullTextSearchの検索結果を元に検索条件を作成
        List fullTextSearchSearchWord = null;
        // 文書一覧取得
        if (CommonUtil.isNull(cateId)) {
        	title = CommonUtil.isNull(requestDto.getSearchTitle())?null:requestDto.getSearchTitle();

        	isFullTextSearchExec = !CommonUtil.isNull(requestDto.getSearchWord());
            //１．『全文検索』項目に入力があった場合はFullTextSearch検索を行なう。
            if(isFullTextSearchExec){
                //『全文検索』文字列をDTO【文書用】.検索文字列へ設定します。
            	fullTextSearchDto.setSearchWord(requestDto.getSearchWord().trim());
                //文書へFullTextSearch検索を行います。
                getFullTextSearchLogic().search(fullTextSearchDto);

                if (fullTextSearchDto == null || fullTextSearchDto.getSearchResultList().isEmpty()) {
                    throw new NoResultException();
                }
                fullTextSearchSearchWord = makeFullTextSearchSearchWord(fullTextSearchDto);
            }
            // タイトルによる検索
       		title = !CommonUtil.isNull(title)?"%" + title.trim() + "%":null;
        }
        //DAO【情報共有検索情報】検索を実行します。
        List listSearch = getDocformUIDocFormDao().select(
        		infoShu, cateId, targetTab, title
        		, sysDate, userId, userTypeCd, kobetsuFlg);
        //全文検索 後処理
        if(isFullTextSearchExec) {
        	fullTextSearchDto.setFileAccessInfoEntity(listSearch);
            getFullTextSearchLogic().matchingData(fullTextSearchDto);
            listSearch = fullTextSearchDto.getMatchingData();
        }
        if (listSearch == null || listSearch.size() == 0) {
            throw new NoResultException();
        }
        // サブカテゴリが指定されていない場合、以下の情報を作成
        //  サブカテゴリの一覧、デフォルトサブカテゴリ
        if (!CommonUtil.isNull(cateId) && CommonUtil.isNull(targetTab)) {
            List listSubCate = makeSubCateList(listSearch);
            mapRet.put(DocFormCont.RESULT_MAP_KEY_SUB_CATEGORY_LIST, listSubCate);
            if (!CommonUtil.isNull(requestDto.getPortalLinkSubCateId())) {
                defSubCate = requestDto.getPortalLinkSubCateId();
            }
            else {
                defSubCate = ((UIViewDocFormInfo) listSubCate.get(0)).getSubCateId();
            }
            List newlistSearchKokai = new ArrayList(0);
            for(int i=0;i<listSearch.size();i++) {
            	UIViewDocFormInfo entity = (UIViewDocFormInfo) listSearch.get(i);
            	if(defSubCate.equals(entity.getSubCateId())) {
                	newlistSearchKokai.add(entity);
            	}
            }
            listSearch = newlistSearchKokai;
        }
        if (listSearch == null || listSearch.size() == 0) {
            throw new NoResultException();
        }
        // 件数
        int searchDataCnt = listSearch.size();
        mapRet.put(DocFormCont.RESULT_MAP_KEY_KENSU, new Integer(searchDataCnt));
        if (SearchField.FIELD_DOCFORM.equals(searchField)==false) {
            if (searchDataCnt > DocFormCont.PAGE_MAX_SIZE) {
                int endIndex = pageNo * DocFormCont.PAGE_MAX_SIZE <= searchDataCnt ? pageNo * DocFormCont.PAGE_MAX_SIZE : searchDataCnt;
                listSearch = listSearch.subList((pageNo - 1) * DocFormCont.PAGE_MAX_SIZE, endIndex);
            }
        }
        //関連文書を設定
        setKanrenBunsho(birdUserInfo, sysDate, infoShu, cateId, defSubCate, title, fullTextSearchSearchWord, listSearch);
        //タブ
        mapRet.put(DocFormCont.RESULT_MAP_KEY_DEFAULT_TAB, defSubCate);
        // 表示用データ
        mapRet.put(DocFormCont.RESULT_MAP_KEY_DOC_FORM, listSearch);
        //．戻り値Mapへtrueの値を自画面static定数保持クラスの文書データの存在フラグをキーに設定します。
        mapRet.put(mapKeyExistFlg, Boolean.TRUE);

        // 画面表示用のデータにする
        return mapRet;
	}

	// サブカテゴリリスト作成
    private List makeSubCateList(List listSearch) {
        List listSubCate = new ArrayList();
        String subCate = "";
        for (Iterator e = listSearch.iterator(); e.hasNext();) {
        	UIViewDocFormInfo entity = (UIViewDocFormInfo) e.next();
            if (!subCate.equals(entity.getSubCateId())) {
                listSubCate.add(entity);
                subCate = entity.getSubCateId();
            }
        }

        return listSubCate;
    }
    /**
     * 関連文書設定処理
     *
     * @param birdUserInfo
     * @param sysDate
     * @param infoShu
     * @param cateId
     * @param subCateId
     * @param title
     * @param listSearchKokai
     */
    private void setKanrenBunsho(
    		BirdUserInfo birdUserInfo, String sysDate
    		, String infoShu, String cateId, String subCateId
    		, String title, List fullTextSearchSearchWord
    		, List listSearchKokai)
    {
    	String publicTutatuDate = CommuniCationUtil.getTutatuPubDate(sysDate);
        //検索結果全対象関連文書情報の取得を行います。
    	//LOGIC【関連文書取得】を実行します。
        List listAllKanrenFile = getGetKanrenBunshoInfoLogic().execute(birdUserInfo.getUserID()
        		, infoShu, sysDate, publicTutatuDate
        		, null, null
        		, title, null, cateId, subCateId
        		, fullTextSearchSearchWord);
        //取得したList[[検索結果全対象関連文書情報]]をカテゴリID＋サブカテゴリID＋登録日＋シーケンス番号の文字列の値をキーに
        //Listを生成し各エンティティを格納しMapへそのListを格納します。
        Map mapKanrenFile = new HashMap();
        for(int k = 0; k < listAllKanrenFile.size(); k++) {
        	UIViewKanrenBunsho kanrenBunsho = (UIViewKanrenBunsho)listAllKanrenFile.get(k);
        	//キーとして登録日＋シーケンス番号の連結文字を作成します。
        	String key =kanrenBunsho.getMotoRegDate() + kanrenBunsho.getMotoSeq();
        	if(!mapKanrenFile.containsKey(key)) {
        		mapKanrenFile.put(key, new ArrayList(0));
        	}
        	List listKanrenBunsho = (List)mapKanrenFile.get(key);
        	listKanrenBunsho.add(kanrenBunsho);
        }
    	for(int i = 0; i < listSearchKokai.size(); i++){
    		List listKanren = new ArrayList(0);
            UIViewDocFormInfo entity = (UIViewDocFormInfo)listSearchKokai.get(i);
            if (entity.getKanrenFileCnt()>0) {
                String key = entity.getRegDate() + entity.getSeq();
	            //各文書フォームへ関連文書情報を設定します。
                if(mapKanrenFile.containsKey(key)) {
                	listKanren = (List)mapKanrenFile.get(key);
                }
                //関連文書並び順の整頓処理
                Collections.sort(listKanren, new SortComparator());
           }
            entity.setListKenrenFile(listKanren);
        }
    }
    /**
     * 関連文書ソート処理
     *
     * ソート順：カテゴリーソートキー[昇順]
     *           サブカテゴリーソートキー[昇順]
     *           登録日[昇順]
     *           ソートSEQ[降順]
     */
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
        	UIViewKanrenBunsho kanrenFile1 = (UIViewKanrenBunsho) obj1;
        	UIViewKanrenBunsho kanrenFile2 = (UIViewKanrenBunsho) obj2;
        	//カテゴリーソートキー+サブカテゴリーソートキー+登録日
        	String val1 = kanrenFile1.getSortKeyCate()+kanrenFile1.getSortKeySubCate()+kanrenFile1.getRegDate();
        	String val2 = kanrenFile2.getSortKeyCate()+kanrenFile2.getSortKeySubCate()+kanrenFile2.getRegDate();
            if (!val1.equals(val2)) {
                return val1.compareTo(val2);
            }
            else {
                val1 = kanrenFile1.getSeq();
                val2 = kanrenFile2.getSeq();
                return val2.compareTo(val1);
            }

        }
    }
    /**
     * FullTextSearchの検索結果を元にファイル名の検索条件を作成
     * @param dto
     * @return
     */
    private List makeFullTextSearchSearchWord(FullTextSearchDto dto) {
        List fullTextSearchFileList = new ArrayList();
        if (dto != null) {
            for (Iterator ite = dto.getSearchResultList().iterator(); ite.hasNext();) {
                try {
                    String url = ((SearchResult) ite.next()).getUrlLink();
                    String filename = url.substring(url.lastIndexOf("/") + 1);
                    fullTextSearchFileList.add(filename.substring(0, 8) + filename.substring(9, 13));
                }
                catch (Exception ex) {
                    throw new FtlSystemException("ファイル名", "", ex);
                }
            }
        }

        return fullTextSearchFileList.isEmpty() ? null : fullTextSearchFileList;
    }
    /**
     * 関連文書検索ロジックを取得します。
     * @return 関連文書検索ロジック
     */
    public GetKanrenBunshoInfoLogic getGetKanrenBunshoInfoLogic() {
        return getKanrenBunshoInfoLogic;
    }

    /**
     * 関連文書検索ロジックを設定します。
     * @param 関連文書検索ロジック
     */
    public void setGetKanrenBunshoInfoLogic(
            GetKanrenBunshoInfoLogic getKanrenBunshoInfoLogic) {
        this.getKanrenBunshoInfoLogic = getKanrenBunshoInfoLogic;
    }

	/**
	 * @return fullTextSearchLogic を戻します。
	 */
	public FullTextSearchLogic getFullTextSearchLogic() {
		return fullTextSearchLogic;
	}

	/**
	 * @param fullTextSearchLogic を クラス変数fullTextSearchLogicへ設定します。
	 */
	public void setFullTextSearchLogic(FullTextSearchLogic fullTextSearchLogic) {
		this.fullTextSearchLogic = fullTextSearchLogic;
	}

	/**
	 * @return クラス変数docformUIDocFormDao を戻します。
	 */
	public UIDocFormDao getDocformUIDocFormDao() {
		return docformUIDocFormDao;
	}

	/**
	 * @param docformUIDocFormDao を クラス変数docformUIDocFormDaoへ設定します。
	 */
	public void setDocformUIDocFormDao(UIDocFormDao docformUIDocFormDao) {
		this.docformUIDocFormDao = docformUIDocFormDao;
	}
}
