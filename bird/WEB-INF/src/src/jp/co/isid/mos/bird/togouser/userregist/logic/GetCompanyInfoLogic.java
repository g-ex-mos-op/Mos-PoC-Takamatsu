/*
 * 作成日: 2008/11/07
 *
 */
package jp.co.isid.mos.bird.togouser.userregist.logic;

import java.util.List;

/**
 * 会社リストの取得インターフェース
 * @author K.Nihonyanagi
 *
 */
public interface GetCompanyInfoLogic {

    public List execute(String companyCd);
}
