package jp.co.isid.mos.bird.bizsupport.spot.dao;


import jp.co.isid.mos.bird.bizsupport.spot.entity.CmpResultData;

/**
 * キャンペーン受注管理情報Daoクラス
 *
 * @author xsong
 */
public interface CampResultDataDao {

    //キャンペーン受注管理情報エンティティ
	public static final Class BEAN = CmpResultData.class;
    
	public static final String getCampResultData_ARGS = "campNo, sibuCd";
	
	/**
	 * キャンペーン受注管理情報を取得します。	
	 * @param campNo　キャンペーンNO
	 * @param sibuCd　支部コード
	 * @return キャンペーン受注管理情報エンティティ
	 */
	public CmpResultData getCampResultData(String campNo, String sibuCd);
    	
}