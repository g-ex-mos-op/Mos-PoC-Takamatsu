/*
 * 作成日: 2006/12/26
 */
package jp.co.isid.mos.bird.entry.nationalentry.logic;


import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiLongServiceInfo;

/**
 * 永年勤続申込情報取得ロジック
 * 
 * @author xlee
 */
public interface LongServiceInfoLogic {

    /**
     * 永年勤続申込情報を取得
     * @param kbn 処理区分
     * @param entryYear エントリー年
     * @param sysDate システム日付
     * @param userTypeCd ユーザタイプ
     * @return 永年勤続申込情報
     */
    public UINatiLongServiceInfo execute(String entryYear, String sysDate, String userTypeCd);
}
