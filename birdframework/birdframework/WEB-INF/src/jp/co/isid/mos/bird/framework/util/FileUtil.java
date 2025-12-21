/*
 * 作成日: 2006/03/02
 *
 */
package jp.co.isid.mos.bird.framework.util;

/**
 * ファイル関連ユーティリティクラス
 * @author xytamura
 *
 */
public class FileUtil {

    private static final String  FILE_SEPARATOR_FOR_WINDOWS = "\\";

    private FileUtil(){
    }
    
    /**
     * ファイルセパレータを取得します。
     * @return ファイルセパレータ
     */
    public static String getFileSeparator(){
        return  FILE_SEPARATOR_FOR_WINDOWS;  
    }
    
    
}
