package jp.co.isid.mos.bird.bizsupport.campcheckamount.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UISetQuantityInfo;

/**
 *　設定数量情報
 *
 * @author xlee
 */
public interface UISetQuantityInfoDao {

    public static final Class BEAN = UISetQuantityInfo.class;

    public static final String getSetQuantityInfo_ARGS = "taishoCond, companyCd, miseCd, sibuCd, blockCd, sysDate, onerCdList,downloadFlag";

    public static final String getOnerSetQuantityInfo_ARGS = "companyCd, sysDate, onerCdList,downloadFlag";

    /**
     *　設定数量情報の取得
     * @param taishoCond　対象条件
     * @param companyCd　企業コード
     * @param miseCd　店コード
     * @param sibuCd　支部コード
     * @param blockCd　ブロックコード
     * @param sysDate　システム日付
     * @param downloadFlag　ダウンロードフラグ
     * @return 設定数量情報
     */
    public List getSetQuantityInfo(String taishoCond, String companyCd, String miseCd, String sibuCd, String blockCd, String sysDate, List onerCdList,boolean downloadFlag);

    public List getOnerSetQuantityInfo(String companyCd, String sysDate, List onerCdList,boolean downloadFlag);
}
