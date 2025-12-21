/*
 * 作成日: 2006/3/7
 */
package jp.co.isid.mos.bird.storeinfo.onerref.logic;

import java.util.List;

/**
 * ユーザ所属管理会社の検索インターフェース
 * @author itamoto
 */
public interface CompanyJohoLogic {

    public List execute(String userId);
}
