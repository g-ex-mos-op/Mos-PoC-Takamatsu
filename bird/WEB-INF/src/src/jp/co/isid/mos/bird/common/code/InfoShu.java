package jp.co.isid.mos.bird.common.code;

/**
 * 情報種別
 * 
 * 作成日:2011/04/18
 * @author xkinu
 *
 */
public class InfoShu {

    // 情報種別：連絡
	public static final String CONTACT = "01";
    // 情報種別：通達
	public static final String TUTAU = "02";
    // 情報種別：文書
	public static final String BUNSHO = "03";
    // 情報種別：フォーム
	public static final String FORM = "04";
    // 情報種別：お知らせ
	public static final String OSIRASE = "05";
    // 情報種別：社内掲示板
	public static final String BBS = "06";
    // 情報種別：連絡
	public static final String CONTACT_NAME = "連絡";
    // 情報種別：通達
	public static final String TUTATU_NAME = "お知らせ";
    // 情報種別：文書
	public static final String BUNSHO_NAME = "文書";
    // 情報種別：フォーム
	public static final String FORM_NAME = "フォーム";
    // 情報種別：お知らせ
	public static final String OSIRASE_NAME = "インフォメーション";
    // 情報種別：掲示板
	public static final String BBS_NAME = "社内掲示板";

    /**
     * 外部からインスタンス化できない
     */
    private InfoShu() {
    	super();
    }
    /**
     * 名称取得処理
     * 
     * @param cd
     * @return　名称
     */
    public static String getName(String cd) {
        if(CONTACT.equals(cd)){
            return CONTACT_NAME;
        }else if(TUTAU.equals(cd)){
            return TUTATU_NAME;
        }else if(BUNSHO.equals(cd)){
            return BUNSHO_NAME;
        }else if(FORM.equals(cd)){
            return FORM_NAME;
        }else if(OSIRASE.equals(cd)){
            return OSIRASE_NAME;
        }
        return "";    
    }
}
