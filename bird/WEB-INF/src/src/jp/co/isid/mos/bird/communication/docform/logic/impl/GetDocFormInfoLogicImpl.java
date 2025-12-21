package jp.co.isid.mos.bird.communication.docform.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.communication.docform.code.SearchField;
import jp.co.isid.mos.bird.communication.docform.common.DocFormCont;
import jp.co.isid.mos.bird.communication.docform.dto.FullTextSearchDocDto;
import jp.co.isid.mos.bird.communication.docform.dto.FullTextSearchFormDto;
import jp.co.isid.mos.bird.communication.docform.dto.RequestDto;
import jp.co.isid.mos.bird.communication.docform.entity.UIViewDocFormInfo;
import jp.co.isid.mos.bird.communication.docform.logic.GetDocFormInfoLogic;
import jp.co.isid.mos.bird.communication.docform.logic.SearchLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

/**
 * 文書・フォーム検索ロジック
 * @author xnkusama
 *
 * 更新日:2008/12/11 xkinu FullTextSearch対応
 */
public class GetDocFormInfoLogicImpl implements GetDocFormInfoLogic {

    /**************
     * ロジックID *
     **************/
    public static final String LOGIC_ID = "BCM004L03";

    /************
     * ロジック *
     ************/
    //【LOGIC】検索ロジック
    private SearchLogic docformSearchLogic;

    //ソート順：カテゴリソート順、カテゴリID 昇順、サブカテゴリーID 昇順、ソートSEQ 昇順
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((UIViewDocFormInfo) obj1).getCateSortKey()
                        + ((UIViewDocFormInfo) obj1).getCateId()
                        + ((UIViewDocFormInfo) obj1).getSubCateSortKey()
                        + ((UIViewDocFormInfo) obj1).getSubCateId()
                        + ((UIViewDocFormInfo) obj1).getSortSeq();

            String val2 = ((UIViewDocFormInfo) obj2).getCateSortKey()
                        + ((UIViewDocFormInfo) obj2).getCateId()
                        + ((UIViewDocFormInfo) obj2).getSubCateSortKey()
                        + ((UIViewDocFormInfo) obj2).getSubCateId()
                        + ((UIViewDocFormInfo) obj2).getSortSeq();

            if (!val1.equals(val2)) {
                return val1.compareTo(val2);
            }
            else {
                val1 = ((UIViewDocFormInfo) obj1).getRegDate();
                val2 = ((UIViewDocFormInfo) obj2).getRegDate();
                if (!val1.equals(val2)) {
                    return val2.compareTo(val1);
                }
                else {
                    val1 = ((UIViewDocFormInfo) obj1).getSeq();
                    val2 = ((UIViewDocFormInfo) obj2).getSeq();
                    return val2.compareTo(val1);
                }
            }

        }
    }
    /**
     * 照会対象の文書を取得する
     *
     * @param requestDto
     * @param birdDateInfo
     * @param birdUserInfo
     * @param ultDocDto  文書用FullTextSearchDto (2008/12/11追加)
     * @param ultFormDto フォーム用FullTextSearchDto (2008/12/11追加)
     * @return Map  TOTAL_COUNT:指定サブカテゴリの件数
     * 　　　　　　　SUB_CATE_LIST：サブカテゴリのリスト
     * 　　　　　　　DEF_SUB_CATE：デフォルトサブカテゴリID
     * 　　　　　　　BUNSHO_LIST：表示用データ
     */
    public Map execute(RequestDto requestDto
    		, BirdDateInfo birdDateInfo, BirdUserInfo birdUserInfo
            , FullTextSearchDocDto ultDocDto, FullTextSearchFormDto ultFormDto)
    {
    	//１．戻り値Mapをインスタンス化します。
        Map mapRet = new HashMap();
        //対象
        String searchField = requestDto.getSearchField();
    	//サブカテゴリID
        String targetTab = requestDto.getTargetTab();
        //２．戻り値Mapへfalseの値を自画面static定数保持クラスのフォームデータの存在フラグをキーに設定します。
        mapRet.put(DocFormCont.RESULT_MAP_KEY_EXIST_FORM, Boolean.FALSE);
        //３．戻り値Mapへfalseの値を自画面static定数保持クラスの文書データの存在フラグをキーに設定します。
        mapRet.put(DocFormCont.RESULT_MAP_KEY_EXIST_BUNSHO, Boolean.FALSE);
        //４．変数List[[検索結果]]をインスタンス化します。
        List listDocForm = new ArrayList(0);
        //５．下記の条件に当てはまる時は、文書検索処理を行う。
        //条件１．条件項目『対象』が”文書・フォーム”の場合で
        //        かつパラメーター.対象タブが空(又はNull)の場合か、”文書”の場合。
        //条件２．条件項目『対象』が”文書”の場合(カテゴリー又は検索ボタン押下時の場合)。
        if ((SearchField.FIELD_DOCFORM.equals(searchField)
        		&& (SearchField.FIELD_DOCFORM.equals(targetTab) || DocFormCont.INFO_SHU_BUNSHO.equals(targetTab)) )
        		|| SearchField.FIELD_DOC.equals(searchField))
        {
            try {
                //UltaseekとDB検索結果取得処理で文書の検索結果を取得します。
                Map mapBunsho = getDocformSearchLogic().execute(
                		birdDateInfo, birdUserInfo
                		, DocFormCont.INFO_SHU_BUNSHO, DocFormCont.RESULT_MAP_KEY_EXIST_BUNSHO
                		, requestDto, ultDocDto);
                listDocForm.addAll((List) mapBunsho.get(DocFormCont.RESULT_MAP_KEY_DOC_FORM));
                if( SearchField.FIELD_DOC.equals(searchField)) {
                	return mapBunsho;
                }
               mapRet.put(DocFormCont.RESULT_MAP_KEY_DEFAULT_TAB, SearchField.FIELD_DOC);
               mapRet.put(DocFormCont.RESULT_MAP_KEY_EXIST_BUNSHO, Boolean.TRUE);
            }
            catch (NoResultException ex) {
            	//条件項目『対象』が”文書”の場合の時のみNoResultExceptionをそのままスルーします。
            	if(SearchField.FIELD_DOC.equals(searchField)) {
            		throw ex;
            	}
            }
        }
        //６．下記の条件に当てはまる時は、文書検索処理を行う。
        //条件１．条件項目『対象』が”文書・フォーム”の場合で
        //        かつパラメーター.対象タブが空(又はNull)の場合か、”フォーム”の場合。
        //条件２．条件項目『対象』が”フォーム”の場合(カテゴリー又は検索ボタン押下時の場合)。
        if ((SearchField.FIELD_DOCFORM.equals(searchField)
        		&& (SearchField.FIELD_DOCFORM.equals(targetTab) || DocFormCont.INFO_SHU_FORM.equals(targetTab)) )
        		|| SearchField.FIELD_FORM.equals(searchField))
        {
            try {
                //UltaseekとDB検索結果取得処理でフォームの検索結果を取得します。
                Map mapForm = getDocformSearchLogic().execute(
                		birdDateInfo, birdUserInfo
                		, DocFormCont.INFO_SHU_FORM, DocFormCont.RESULT_MAP_KEY_EXIST_FORM
                		, requestDto, ultFormDto);
                listDocForm.addAll((List) mapForm.get(DocFormCont.RESULT_MAP_KEY_DOC_FORM));
                if( SearchField.FIELD_FORM.equals(searchField)) {
                	return mapForm;
                }
                if(CommonUtil.isNull((String)mapRet.get(DocFormCont.RESULT_MAP_KEY_DEFAULT_TAB))) {
                	mapRet.put(DocFormCont.RESULT_MAP_KEY_DEFAULT_TAB, SearchField.FIELD_FORM);
                }
                else {
                	mapRet.put(DocFormCont.RESULT_MAP_KEY_DEFAULT_TAB, SearchField.FIELD_DOCFORM);
                }
                mapRet.put(DocFormCont.RESULT_MAP_KEY_EXIST_FORM, Boolean.TRUE);
            }
            catch (NoResultException ex) {
            	//条件項目『対象』が”フォーム”の場合の時のみNoResultExceptionをそのままスルーします。
            	if(SearchField.FIELD_FORM.equals(searchField)) {
            		throw ex;
            	}
            }
        }
        //７．条件項目『対象』の値が'文書・フォーム'の場合、下記の処理を行います。
        if (SearchField.FIELD_DOCFORM.equals(searchField)) {
            //７－１．処理４のList[[検索結果]]がnull又はサイズが0の場合、NoResultExceptionを発生します。
            if (listDocForm == null || listDocForm.size() == 0) {
                throw new NoResultException();
            }
            //７－２．並び順の整頓処理
            Collections.sort(listDocForm, new SortComparator());
            //７－３．処理４のList[[検索結果]].サイズを戻り値Map.検索結果件数へ設定します。
            mapRet.put(DocFormCont.RESULT_MAP_KEY_KENSU, new Integer(listDocForm.size()));
            //７－４．１ページ単位の結果をセット
            if (listDocForm.size() > DocFormCont.PAGE_MAX_SIZE) {
                //リクエストページ番号
                int pageNo = requestDto.getCurrentPageNumber();
                int endIndex = pageNo * DocFormCont.PAGE_MAX_SIZE <= listDocForm.size() ? pageNo * DocFormCont.PAGE_MAX_SIZE : listDocForm.size();
                listDocForm = listDocForm.subList((pageNo - 1) * DocFormCont.PAGE_MAX_SIZE, endIndex);
            }
            //７－５．処理４のList[[検索結果]]を戻り値Map.List[[検索結果]]へ設定します。
            mapRet.put(DocFormCont.RESULT_MAP_KEY_DOC_FORM, listDocForm);
        }

        return mapRet;
    }
	/**
	 * @return クラス変数docformSearchLogic を戻します。
	 */
	public SearchLogic getDocformSearchLogic() {
		return docformSearchLogic;
	}
	/**
	 * @param docformSearchLogic を クラス変数docformSearchLogicへ設定します。
	 */
	public void setDocformSearchLogic(SearchLogic docformSearchLogic) {
		this.docformSearchLogic = docformSearchLogic;
	}
}