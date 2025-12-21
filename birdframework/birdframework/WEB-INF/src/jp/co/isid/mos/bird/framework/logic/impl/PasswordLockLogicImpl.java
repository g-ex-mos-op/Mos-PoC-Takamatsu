/*
 * 作成日: 2008/10/28
 */
package jp.co.isid.mos.bird.framework.logic.impl;

import java.math.BigDecimal;

import jp.co.isid.mos.bird.framework.dao.CtlLoginFailKaisuDao;
import jp.co.isid.mos.bird.framework.entity.CtlLoginFailKaisu;
import jp.co.isid.mos.bird.framework.logic.PasswordLockLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;


/**
 * ユーザー認証に失敗した回数の更新を行います。
 * @author xnkusama
 */
public class PasswordLockLogicImpl implements PasswordLockLogic {
    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BFW000L15";
    
    private CtlLoginFailKaisuDao ctlLoginFailKaisuDao;
    
    public void execute(final String userId, final String torokuKbn, String gamenId) {
        CtlLoginFailKaisu failKaisu = getCtlLoginFailKaisuDao().getCtlLoginFailKaisu(userId);

        if (gamenId == null) {
            gamenId = "";
        }
        //２．[ログイン失敗回数]が取得できた場合
        if (failKaisu != null) {
            //  ２-a、パラメータ．登録区分='0'(成功)の場合
            //    ２-a-1、取得した[ログイン失敗回数]を編集する。
            //            [ログイン失敗回数]．ログイン失敗回数　←　0
            //    ２-a-2、Dao【ログイン失敗回数情報の更新】を実行する。
            //            パラメータ： 上記で編集した[ログイン失敗回数]
            if (LOGIN_SUCCESS.equals(torokuKbn)){
                failKaisu.setLoginFail(new BigDecimal("0"));
                failKaisu.setLastUser(userId);
                failKaisu.setLastPgm(gamenId);
                failKaisu.setLastTmsp(DateManager.getCurrentTimestamp());
                getCtlLoginFailKaisuDao().updateCtlLoginFailKaisu(failKaisu);
            }
            //  ２-b、パラメータ．登録区分='1'(失敗)の場合
            //    ２-b-1、取得した[ログイン失敗回数]を編集する。
            //            [ログイン失敗回数]．ログイン失敗回数　←　[ログイン失敗回数]．ログイン失敗回数＋１
            //    ２-b-2、Dao【ログイン失敗回数情報の更新】を実行する。
            //            パラメータ： 上記で編集した[ログイン失敗回数]
            else if(LOGIN_FAILURE.equals(torokuKbn)){
                failKaisu.setLoginFail(failKaisu.getLoginFail().add(new BigDecimal("1")));
                getCtlLoginFailKaisuDao().updateCtlLoginFailKaisu(failKaisu);
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
            if (LOGIN_FAILURE.equals(torokuKbn)){
                CtlLoginFailKaisu newLoginFailKaisu = new CtlLoginFailKaisu();
                newLoginFailKaisu.setUserId(userId);
                newLoginFailKaisu.setLoginFail(new BigDecimal("1"));
                newLoginFailKaisu.setLastUser(userId);
                newLoginFailKaisu.setLastPgm(gamenId);
                newLoginFailKaisu.setLastTmsp(DateManager.getCurrentTimestamp());
                getCtlLoginFailKaisuDao().insertCtlLoginFailKaisu(newLoginFailKaisu);
            }
        }
        
        
    }

    public CtlLoginFailKaisuDao getCtlLoginFailKaisuDao() {
        return ctlLoginFailKaisuDao;
    }

    public void setCtlLoginFailKaisuDao(CtlLoginFailKaisuDao ctlLoginFailKaisuDao) {
        this.ctlLoginFailKaisuDao = ctlLoginFailKaisuDao;
    }


    
}
