/*
 * 作成日: 2006/01/25
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.framework.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author xytamura
 *
 */
public class BirdMessageFormatter {
    
    private static final String CONTEXT_NAME = "BRDMessages";
    
    private BirdMessageFormatter(){
        
    }

    public static String getMessage(String messagesId, String[] messagesArgs){
        
        String[] param = new String[]{"","","",""};
        for(int i = 0; i < messagesArgs.length; i++){
            param[i] = messagesArgs[i];
        }
        String msg = MessageFormat.format(ResourceBundle.getBundle(CONTEXT_NAME).getString(messagesId), param);
        return msg;    
    }
    
}
