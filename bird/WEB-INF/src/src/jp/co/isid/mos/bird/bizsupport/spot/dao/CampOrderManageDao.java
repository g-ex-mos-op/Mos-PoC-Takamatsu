package jp.co.isid.mos.bird.bizsupport.spot.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.spot.entity.CmpOrderMngTable;



/**
 * 対象キャンペーン受注管理情報Daoクラス
 *
 * @author xsong
 */
public interface CampOrderManageDao {

    //対象キャンペーン情報エンティティ
	public static final Class BEAN = CmpOrderMngTable.class;
    
	public static final String getCampExisDatatList_ARGS = "sysDateMinOne, sysDateMinThr";
	 
	/**
	 * 対象キャンペーン受注管理情報を取得します。
	 * @param sysDateMinOne　システム日付 - 1日
	 * @param sysDateMinThr  システム日付 - 3日
	 * @return 対象キャンペーン受注管理情報
	 */
	public List getCampExisDatatList(String sysDateMinOne, String sysDateMinThr);
    	
}