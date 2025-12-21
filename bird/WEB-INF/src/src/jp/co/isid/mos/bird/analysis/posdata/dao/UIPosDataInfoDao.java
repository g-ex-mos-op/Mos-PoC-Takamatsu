package jp.co.isid.mos.bird.analysis.posdata.dao;

import java.util.List;
import jp.co.isid.mos.bird.analysis.posdata.entity.UIPosDataInfo;

/**
 * 取得店舗コード情報Dao
 * @author xkinu
 *
 */
public interface UIPosDataInfoDao {
	
    public static final Class BEAN = UIPosDataInfo.class;
    
    public static final String getPosDataList_ARGS = "companyCd," +
									    		"onerId," +
									    		"dataDt";
    
    /**
     * 取得店舗コード情報の取得
     * 
     * @param onerId オーナーID
     * @param userTypeCd ユーザータイプコード
     * @param dataDt 集信データ日
     * @return List
     */
    public List getPosDataList(
              String companyCd
            , String onerId
            , String dataDt);    
}
