package jp.co.isid.mos.bird.inforegist.notificationregist.entity;

import jp.co.isid.mos.bird.common.entity.TrnControlGyotaiKobetu;

/**
 * 公開対象支部情報
 * 
 * 作成日:2010/02/23
 * @author xkinu
 *
 */
public class TrnOpenSibu extends TrnControlGyotaiKobetu {
    
    public static final String kobetsuName_COLUMN = "KOBETSU_NAME";
    
    /**
     * 個別設定名称
     * 支部名称などになります。
     */
    private String kobetsuName;

	/**
	 * @return クラス変数kobetsuName を戻します。
	 */
	public String getKobetsuName() {
		return kobetsuName;
	}

	/**
	 * @param kobetsuName を クラス変数kobetsuNameへ設定します。
	 */
	public void setKobetsuName(String kobetsuName) {
		this.kobetsuName = kobetsuName;
	}
    
}
