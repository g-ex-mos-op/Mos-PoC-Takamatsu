/*
 * 作成日: 2008/10/28
 */
package jp.co.isid.mos.bird.togouser.login.logic.impl;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.dao.CtlLoginFailKaisuDao;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.togouser.login.logic.UserInfoSetupLogic;


/**
 * ユーザー認証に失敗した回数の更新を行います。
 * @author K.Nihonyanagi
 */
public class UserInfoSetupLogicImpl implements UserInfoSetupLogic {

    private final String GAMEN_ID = "BUR000";
    private CtlLoginFailKaisuDao clfkaisuDao = null;
    
    public void execute(final String userId, final String torokuKbn) {
        CtlLoginFailKaisu failKaisu = getClfkaisuDao().getCtlLoginFailKaisu(userId);

        //２．[ログイン失敗回数]が取得できた場合
        if (failKaisu != null) {
            //  ２-a、パラメータ．登録区分='0'(成功)の場合
            //    ２-a-1、取得した[ログイン失敗回数]を編集する。
            //            [ログイン失敗回数]．ログイン失敗回数　←　0
            //    ２-a-2、Dao【ログイン失敗回数情報の更新】を実行する。
            //            パラメータ： 上記で編集した[ログイン失敗回数]
            if ("0".equals(torokuKbn)){
                failKaisu.setLoginFail(new BigDecimal("0"));
                failKaisu.setLastUser(userId);
                failKaisu.setLastPgm(GAMEN_ID);
                failKaisu.setLastTmsp(DateManager.getCurrentTimestamp());
                getClfkaisuDao().updateCtlLoginFailKaisu(failKaisu);
            }
            //  ２-b、パラメータ．登録区分='1'(失敗)の場合
            //    ２-b-1、取得した[ログイン失敗回数]を編集する。
            //            [ログイン失敗回数]．ログイン失敗回数　←　[ログイン失敗回数]．ログイン失敗回数＋１
            //    ２-b-2、Dao【ログイン失敗回数情報の更新】を実行する。
            //            パラメータ： 上記で編集した[ログイン失敗回数]
            else if("1".equals(torokuKbn)){
                failKaisu.setLoginFail(failKaisu.getLoginFail().add(new BigDecimal("1")));
                getClfkaisuDao().updateCtlLoginFailKaisu(failKaisu);
            }
            
        }
        //３． [ログイン失敗回数]が取得できなかった場合    
        else{
            //  ３-a、パラメータ．登録区分='1'(失敗)の場合   
            //  ３-a-1、新規[ログイン失敗回数]を作成し、以下の項目を編集する。   
            //          [ログイン失敗回数]．ユーザーID　←　パラメータ．ユーザーID 
            //          [ログイン失敗回数]．ログイン失敗回数　←　1  
            //  ３-a-2、Dao【ログイン失敗回数情報の新規登録】を実行する。 
            //          パラメータ： 上記で新規作成した[ログイン失敗回数]   
            if ("1".equals(torokuKbn)){
                CtlLoginFailKaisu newLoginFailKaisu = new CtlLoginFailKaisu();
                newLoginFailKaisu.setUserId(userId);
                newLoginFailKaisu.setLoginFail(new BigDecimal("1"));
                newLoginFailKaisu.setLastUser(userId);
                newLoginFailKaisu.setLastPgm(GAMEN_ID);
                newLoginFailKaisu.setLastTmsp(DateManager.getCurrentTimestamp());
                getClfkaisuDao().insertCtlLoginFailKaisu(newLoginFailKaisu);
            }
        }
        
        
    }

    public CtlLoginFailKaisuDao getClfkaisuDao() {
        return clfkaisuDao;
    }

    public void setClfkaisuDao(CtlLoginFailKaisuDao clfkaisu) {
        this.clfkaisuDao = clfkaisu;
    }


    
}
