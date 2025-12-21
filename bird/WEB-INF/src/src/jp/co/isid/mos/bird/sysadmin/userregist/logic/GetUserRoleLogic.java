/*
 * 作成日: 2007/02/01
 */
package jp.co.isid.mos.bird.sysadmin.userregist.logic;

import java.util.List;
import java.util.Map;

/**
 * 契約タイププルダウン取得ロジックインターフェース
 * @author xamaruyama
 */
public interface GetUserRoleLogic {

    /**
     * 登録内容のチェック処理
     * @return 
     */
    public List execute(Map roleParam);
}
