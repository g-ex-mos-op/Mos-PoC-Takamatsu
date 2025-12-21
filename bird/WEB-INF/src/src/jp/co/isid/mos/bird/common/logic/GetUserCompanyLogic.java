/**
 * 
 */
package jp.co.isid.mos.bird.common.logic;

import java.util.List;

/**
 * ユーザ所属会社リスト取得Logic
 * 
 * @author xyuchida
 */
public interface GetUserCompanyLogic {

    // 閲覧
    public static final String ZOKUSEI_KBN_READ = "0";
    // 所属
    public static final String ZOKUSEI_KBN_BELONG = "1";
    // 契約
    public static final String ZOKUSEI_KBN_CONTRACT = "2";

    /**
     * ユーザ所属会社リスト取得
     * 
     * @param userId ユーザID
     * @param zokuseiKbnList 属性区分リスト  = null : 条件としない
     * @return ユーザ所属会社リスト
     */
    public List execute(String userId, List zokuseiKbnList);
}
