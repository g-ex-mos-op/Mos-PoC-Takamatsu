package jp.co.isid.mos.bird.bizreport.common.logic;

import java.util.List;

/**
 * 対象支部情報取得共通ロジック インターフェース
 * 
 * @author xjung
 */
public interface MstSibuInfoLogic {

    /**
     * 対象支部情報を取得する
     * @param companyCd 会社コード
     * @param limitFlg	 制限区分
     * @param userId	 ユーザID
     * @param shukeiKbn 集計区分
     * @return List     対象支部情報
     */
    public List execute(
		String companyCd,
		boolean limitFlg,
		String userId,
		String shukeiKbn);
}