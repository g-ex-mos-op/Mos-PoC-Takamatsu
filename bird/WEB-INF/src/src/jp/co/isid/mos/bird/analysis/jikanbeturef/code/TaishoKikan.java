package jp.co.isid.mos.bird.analysis.jikanbeturef.code;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.CodeUtil;
import jp.co.isid.mos.bird.common.entity.UILists;

/**
 * 対象期間コード定数クラス
 * 
 * @author xkinu
 */
public class TaishoKikan extends CodeUtil {

    /** 対象期間：期日指定 */
    public static final String KIKAN = "KIKAN";
    /** 対象期間：月別 */
    public static final String MONTH = "MONTH";
 
    /** 対象期間:本部用 */
    public static final String[][] CODE_TABLE = new String [][]{
        {MONTH, "対象年月"}
    ,   {KIKAN, "期間指定"}};

    /**
     * 外部からインスタンス化できない
     */
    private TaishoKikan() {
        super();
    }

    /**
     * コードの名称を取得する<br>
     * @param   code        コード
     * @return  String      コード名称
     */
    public static String getName(String code) {
        return CodeUtil.getName(code, CODE_TABLE); 
    }
    /**
     * プルダウンリストを取得する 
     * @return  List        プルダウンリスト
     */
    public static List getUIListPullDownList() {
        List pullDownList = new ArrayList();
        for(int i = 0; i < CODE_TABLE.length; i++) {
            UILists item = new UILists();
            item.setKey(CODE_TABLE[i][0]);
            item.setKeyName(CODE_TABLE[i][1]);
            pullDownList.add(item);
        }
        return pullDownList;
    }
    /**
     * プルダウンリストを取得する
     * 渡されたコード分のリストを作成する
     * @param   codeTable   コードリスト
     * @param   arrCode     コード配列
     * @return  List        ﾌﾟﾙﾀﾞｳﾝﾘｽﾄ(ｺｰﾄﾞ配列がNULLの場合、NULLをﾘﾀｰﾝする)
     */
    public static List getUIListPullDownList(String[] arrCode) {
        List pullDownList = new ArrayList();

        if(arrCode == null) {
            return pullDownList;
        }

        for(int i= 0; i < arrCode.length; i++) {
            String code = arrCode[i];

            for(int j = 0; j < CODE_TABLE.length; j++) {
                if (CODE_TABLE[j][0].equals(code)) {
                    UILists item = new UILists();
                    item.setKey(CODE_TABLE[j][0]);
                    item.setKeyName(CODE_TABLE[j][1]);
                    pullDownList.add(item);
                    break;
                }
            }
        }

        return pullDownList;
    }
}
