package jp.co.isid.mos.bird.communication.docform.common;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

public class DocFormCont {

    /************ 
     * 情報種別 * 
     ************/
    //文書
    public static final String INFO_SHU_BUNSHO = "03";
    //フォーム
    public static final String INFO_SHU_FORM   = "04";
        
    /*********************
     * 検索結果Mapのキー *
     *********************/
    //文書・フォームデータ
    public static final String RESULT_MAP_KEY_DOC_FORM = "DOC_FORM_LIST";
    //件数
    public static final String RESULT_MAP_KEY_KENSU = "TOTAL_COUNT";
    //サブカテゴリのリスト
    public static final String RESULT_MAP_KEY_SUB_CATEGORY_LIST = "SUB_CATE_LIST";
    //デフォルトのサブカテゴリ
    public static final String RESULT_MAP_KEY_DEFAULT_TAB = "DEF_SUB_CATE";
    //タイトル検索：文書データの存在フラグ
    public static final String RESULT_MAP_KEY_EXIST_BUNSHO = "EXIST_BUNSHO";
    //タイトル検索：フォームデータの存在フラグ
    public static final String RESULT_MAP_KEY_EXIST_FORM = "EXIST_FORM";
    
    /********************
     * １ページ表示件数 *
     ********************/
    public static final int PAGE_MAX_SIZE = 30;
    /**
     * 個別指定判断制御フラグ取得処理
     * 
     * @param birdUserInfo
     * @return true:個別指定あり、false:個別指定なし
     */
    public static boolean getKobetsuFlg(BirdUserInfo birdUserInfo){
        // カテゴリ取得
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		boolean kobetsuFlg = true;
		/* BT15IAID 情報アクセス制御業態個別設定 */
		if (UserType.HONBU.equals(userTypeCd)) {
		    // 本部：ユーザー対応オーナーが存在する（＝販社）場合以外は、個別設定の条件を除外する。
		    if (birdUserInfo.getUserOner() == null
		            || birdUserInfo.getUserOner().size() == 0) {
		    	kobetsuFlg = false;
		    }
		}
		return kobetsuFlg;
    }

}
