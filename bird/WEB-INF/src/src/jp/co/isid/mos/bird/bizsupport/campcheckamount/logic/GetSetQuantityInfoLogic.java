package jp.co.isid.mos.bird.bizsupport.campcheckamount.logic;

import java.util.List;


/**
 *　設定数量確認取得ロジック
 *
 * @author xlee
 */
public interface GetSetQuantityInfoLogic {

    /**
     * 設定数量確認取得
     * @param taishoCond 対象条件
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @param sibuCd 支部コード
     * @param blockCd ブロックコード
     * @param sysDate　システム日付
     * @param downloadFlag　ダウンロードフラグ
     * @return　設定数量確認情報
     */
    public List execute(String taishoCond, String companyCd, String miseCd, String sibuCd, String blockCd, String sysDate, List onerCdList,boolean downloadFlag);


    /**
     * 設定数量確認リストを取得:オーナーユーザ
     * @param companyCd 会社コード
     * @param sysDate　システム日付
     * @param onerCd　オーナーコード
     * @param downloadFlag　ダウンロードフラグ
     * @return　設定数量確認情報
     */
    public List execute(String companyCd, String sysDate, List onerCdList,boolean downloadFlag);
}
