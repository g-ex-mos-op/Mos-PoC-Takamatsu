/**
 * 
 */
package jp.co.isid.mos.bird.communication.common.util;

import java.util.Calendar;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 情報共有共通定数クラス
 * 
 * 作成日:2010/03/24
 * @author xkinu
 *
 */
public class CommuniCationUtil {
	/**
	 * 通達公開開始時刻：14時
	 */
	public static final int PUBLIC_TIME_TUTATU = 14;
	/**
	 * 通達最終公開日
	 * @param sysDate
	 * @return
	 */
	public static String getTutatuPubDate(String sysDate) {
		//公開範囲終了日(公開時間仕様追加対応)
		Calendar cal = Calendar.getInstance();
		String tutatuPubDate = sysDate;
		try {
			//16時より前の場合はシステム日付の前日の日付を
			//16時以後の場合はシステム日付を、公開対象日の終了日として設定します。
			tutatuPubDate = PUBLIC_TIME_TUTATU>cal.get(Calendar.HOUR_OF_DAY)?DateManager.getPrevDate(sysDate, 1):sysDate;
		}
		catch (Exception ex) {
			throw new FtlSystemException(InfoShu.TUTATU_NAME + "用公開範囲終了日生成"
                    , "指定日["+sysDate+"]から[1]を計算する際のDateManager.getPrevDateメソッド処理で例外が発生しました。"
                    , ex);
		}
		return tutatuPubDate;
	}
}
