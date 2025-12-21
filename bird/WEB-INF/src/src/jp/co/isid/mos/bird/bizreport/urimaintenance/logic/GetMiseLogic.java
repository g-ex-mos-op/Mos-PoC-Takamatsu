package jp.co.isid.mos.bird.bizreport.urimaintenance.logic;

import jp.co.isid.mos.bird.common.entity.MstMise;

public interface GetMiseLogic {

    /**
     * 店マスタ情報取得
     * 
     * @param companyCd 会社コード
     * @param miseCd 店コード
     * @return 店マスタ情報
     */
    public MstMise execute(String companyCd, String miseCd);
}
