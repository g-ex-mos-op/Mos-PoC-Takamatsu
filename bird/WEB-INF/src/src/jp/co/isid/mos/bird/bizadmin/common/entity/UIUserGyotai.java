/*
 * 作成日: 2006/02/09
 *
 */
package jp.co.isid.mos.bird.bizadmin.common.entity;


/**
 * ユーザー管理業態
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public class UIUserGyotai extends MstUserGyotai{
	
	public static final String gyotaiName_COLUMN = "GYOTAI_KBN_NAME";

	/**
    * 業態区分
    */
	private String gyotaiName;
	/**
	 * @return gyotaiName を戻します。
	 */
	public String getGyotaiName() {
		return gyotaiName;
	}
	/**
	 * @param gyotaiName gyotaiName を設定。
	 */
	public void setGyotaiName(String gyotaiName) {
		this.gyotaiName = gyotaiName;
	}
}
