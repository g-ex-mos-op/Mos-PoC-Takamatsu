/*
 * 作成日: 2006/02/07
 *
 */
package jp.co.isid.mos.bird.inforegist.contactregist.logic.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.control.BirdContext;
import jp.co.isid.mos.bird.inforegist.contactregist.logic.ContactRegistFileDeleteLogic;

/**
 * @author xyuchida
 *
 */
public class ContactRegistFileDeleteLogicImpl implements
        ContactRegistFileDeleteLogic {

    public static final String LOGIC_ID = "BIF001L05";

    public void execute(List targetFileNameList) {

        // ファイル格納パス取得
        String filePath = BirdContext.getProperty("fileProperty", "filePathRenraku");

        for (Iterator it = targetFileNameList.iterator(); it.hasNext();) {
            // ファイル削除
            (new File(filePath + File.separator + it.next())).delete();
        }
    }
}
