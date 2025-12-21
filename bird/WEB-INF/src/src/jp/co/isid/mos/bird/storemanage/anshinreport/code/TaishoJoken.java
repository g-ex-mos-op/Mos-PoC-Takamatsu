package jp.co.isid.mos.bird.storemanage.anshinreport.code;

import java.util.List;

/**
 * 対象条件コード定数クラス
 * コードデータのリストの生成、コードデータからの名称取得できる機能を保持しているクラスです。
 * @author xnkusama
 */
public class TaishoJoken {

    /** コード値：オーナー */
    public static final String CODE_ONER  = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_ONER;
    /** コード値：店舗(個店) */
    public static final String CODE_MISE  = jp.co.isid.mos.bird.common.code.TaishoJoken.CODE_MISE;
    
    /** 対象条件配列 */
    public static final String[][] CODE_TABLE = new String [][]{
        {CODE_MISE, "店舗"},
        {CODE_ONER, "オーナー"}};

    /**
     * 外部からインスタンス化できない
     */
    private TaishoJoken() {
    	super();
    }  

    /**
     * 対象条件リストを取得する<br>
     * @return List　   対象条件リスト
     */
    public static List getPullDownList() {

        return CodeUtil.getPullDownList(CODE_TABLE);
    }

    /**
     * コードの名称を取得する<br>
     * @param  String  ユーザタイプコード
     * @param	String	コード
     * @return	String 	コード名称
     */
    public static String getName(String code) {
        
        return CodeUtil.getName(code, CODE_TABLE);
    }

}
