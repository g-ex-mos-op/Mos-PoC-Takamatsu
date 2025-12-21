package jp.co.isid.mos.bird.bizsupport.spot.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.spot.entity.TenpoResult;


/**
 * スポット未受注店情報Daoクラス
 *
 * @author xsong
 */
public interface CampResultTableDao {

    //スポット未受注店情報エンティティ
	public static final Class BEAN = TenpoResult.class;

    
	public static final String getCampResultTableData_ARGS = "sibuCd, cmpNo";
	 
	
	/**
	 * スポット未受注店情報を取得します。
	 * @param sibuCd 支部コード
	 * @param cmpNo　キャンペーンNO
	 */
	public List getCampResultTableData(String sibuCd, String cmpNo);
    	
}