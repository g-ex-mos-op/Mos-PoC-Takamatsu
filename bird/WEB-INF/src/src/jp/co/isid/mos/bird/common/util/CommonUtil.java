/**
 * 2008/06/20
 */
package jp.co.isid.mos.bird.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.framework.dto.GamenRoleDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.GetGamenRoleLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 共通定数クラス
 * 
 * @author xkinu
 *
 */
public class CommonUtil {
    /** 会社コード：モス */
    public static final String COMPANY_CD_MOS = "00";
    /** 会社コード：トモス */
    public static final String COMPANY_CD_TOMOS = "60";
    /** 会社コード：四季菜 */
    public static final String COMPANY_CD_SIKINA = "70";
    /** VIEWID：個店ポータル：照会画面 */
    public static final String VIEW_ID_MISEREF   = "BSI001V02";
    /** VIEWID：オーナー：照会画面 */
    public static final String VIEW_ID_ONERREF   = "BSI002V02";
    /** VIEWID：店舗選択 */
    public static final String VIEW_ID_MISESEARCH   = "BCO008V01";
    /** VIEWID：オーナー選択 */
    public static final String VIEW_ID_OWNERSEARCH  = "BCO006V01";
    /** VIEWID：メニュー選択画面 */
    public static final String VIEW_ID_SEARCH_MENU = "BCO012V01";
    /* VIEWID：複数Window機能の操作エラー画面 */
    public static final String operationErr_VIEW_ID = "operation.Err";
    /** アクション種類:非稼動*/
    public static final int ACTION_KIND_NULL = -1;
    /** アクション種類:初期化*/
    public static final int ACTION_KIND_INIT = 0;
    /** アクション種類:選択済み*/
    public static final int ACTION_KIND_SELECT = 1;
    /** アクション種類:戻り*/
    public static final int ACTION_KIND_CANCEL = 2;
    /** アクション種類:稼動中*/
    public static final int ACTION_KIND_EXEC = 9;
    
    /** 時間枠:未来*/
    public static final boolean FUTURE = true;
    /** 時間枠:過去*/
    public static final boolean PAST = false;

    /** ソート:降順*/
    public static final boolean SORT_DESC = true;
    /** ソート:昇順*/
    public static final boolean SORT_ASC = false;

    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();

    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * ユーザ権限判断処理
     * 
     * ユーザ様の処理が可能なユーザか否かを判別します。
     * 
     * @param gamenRoleLogic 共通ロジック【ユーザの汎用画面ロール制御情報取得】
     * @param userId ユーザID
     * @param gamenId 画面ID
     * @param bunruiCd 分類コード
     * @return true･･･可能なユーザ、false･･･可能なユーザでない
     */
    public static boolean isRoleUser(GetGamenRoleLogic gamenRoleLogic
    		, String userId
    		, String gamenId, String bunruiCd) {

        //引数用DTO作成
        GamenRoleDto dto = new GamenRoleDto();
        dto.setUserId(userId);
        dto.setGamenId(gamenId);
        dto.setBunruiCd(bunruiCd);
        
        List list;
        try{
            list = gamenRoleLogic.excute(dto);
        }catch(NotExistException ne){
            return false;
        }
        
        if(list != null && list.size() > 0){
            return true;
        }
        
        return false;
    }
    /**
     * 
     * @param value
     * @return
     */
    public static String rtrim(String value) {
        if (value == null || "".equals(value)) {
            return value;
        }
        int pos = value.length();
        for (int i = value.length(); i > 0; i--) {
            if (" ".equals(value.substring(i -1, i))) {
                pos = i - 1;
            }
            else {
                break;
            }
        }
        return value.substring(0, pos);
    }
    /**
     * 右側全半角スーペーストリム処理
     * @param value
     * @return
     */
    public static String rAllSpaceTrim(String value) {
        if (value == null) {
            return value;
        }
        int pos = value.length();
        for (int i = value.length(); i > 0; i--) {
            if (" ".equals(value.substring(i -1, i))
            		|| "　".equals(value.substring(i -1, i)))
            {
                pos = i - 1;
            }
            else {
                break;
            }
        }
        return value.substring(0, pos);
    }
    /**
     * 日付リストを取得する
     * 
     * @param startDt アプリ日付などの基準となる日付が入ります。
     * @param days startDtを含めての日数(マイナス値不可)
     * @param timeSpace true:未来、false:過去
     * @param sortType true:降順、false:昇順
     * @return
     */
    public static List creatListDay(String startDt, int days, boolean timeSpace, boolean sortType) {
        List list = new ArrayList();
        String day = startDt;
        //時間枠が過去で降順の場合は0
        //              昇順の場合は日数
        //時間枠が未来で降順の場合は日数
        //              昇順の場合は0
        boolean isCntUp = (PAST==timeSpace && SORT_DESC ==sortType) || (FUTURE==timeSpace && SORT_ASC == sortType);
        int idays = isCntUp?0:days;
        while (0<=idays && days>idays) {
            try {
            	//時間枠を判断し、未来又は過去の日付を取得します。
           		day = timeSpace?DateManager.getNextDate(startDt, idays):DateManager.getPrevDate(startDt, idays);
	            String name = formatter.format(day, DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD);
	            list.add(idays, createSelectItem(day, name));
	        }catch (Exception ex) {
	            throw new FtlSystemException("期間指定生成"
	                    , "指定開始日["+startDt+"]から["+idays+"]を計算する際のDateManagerメソッド処理で例外が発生しました。"
	                    , ex);
	        }
	        if(isCntUp) {
	        	idays++;
	        }
	        else {
	        	idays--;
	        }
        }
        return list;
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private static SelectItem createSelectItem(String code, String name) {
        SelectItem entity = new SelectItem();
        entity.setValue(code);
        entity.setLabel(name);
        return entity;
    }
	/**
	 * yyyyMMddをyyyy/MM/dd変換処理します。
	 * @param ymd
	 * @return
	 */
	public static String formattYMDSlash(String ymd) {
		return formatter.format(ymd, DateFormatter.PATTERN_SLASH
				, DateFormatter.DATE_TYPE_YMD);
	}
    /**
     * 指定文字列内改行文字JAVA用変換処理と変換後文字列取得処理
     * 
     * 
     * @param targetword 指定文字列
     * @return
     */
    public static String changeEnterWordDBtoJSF(String targetword){
        String changedWord = targetword.replaceAll("`", "\n");
        return changedWord;
    }
    /**
     * 指定文字列内改行文字DB用変換処理と変換後文字列取得処理
     * 
     * @param targetword 指定文字列
     * @return
     */
    public static String changeEnterWordJSFtoDB(String targetword){
        String changedWord = targetword.replaceAll("\r\n", "`");
        changedWord = changedWord.replaceAll("\n", "`");
        return changedWord;
    }

}
