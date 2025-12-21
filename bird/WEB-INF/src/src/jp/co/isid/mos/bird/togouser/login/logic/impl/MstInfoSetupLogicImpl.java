/*
 * 作成日: 2008/10/28
 */
package jp.co.isid.mos.bird.togouser.login.logic.impl;

import jp.co.isid.mos.bird.framework.exception.NoSessionException;
import jp.co.isid.mos.bird.togouser.login.dao.UITogoUserDao;
import jp.co.isid.mos.bird.togouser.login.entity.UITogoUser;
import jp.co.isid.mos.bird.togouser.login.logic.MstInfoSetupLogic;

/**
 * ログイン時にユーザー関連情報をセッションに保持しておく
 * @author K.Nihonyanagi
 */
public class MstInfoSetupLogicImpl implements MstInfoSetupLogic {

    private UITogoUserDao uiTUsrDao = null;
    
    public UITogoUser execute(final String userId) {
        //１．Dao【ユーザー情報の取得】を実行
        //パラメータ： パラメータ．ユーザーID
        //    　　　　※ 取得できない場合、エラー【E0413】
        //２． 戻り値．ユーザー情報を生成する。
        //３．処理１～２で取得したマスタ情報を戻り値．ユーザー情報にセットする
        //　　※ 格納時のキーは、BirdUserInfoクラスを参照
        UITogoUser uiTusr = (UITogoUser)uiTUsrDao.getUserInfo(userId);
        if (uiTusr == null) {
            throw new NoSessionException();
        }
        
        //４．処理３の結果をリターン
        return (uiTusr);
        
    }
}
