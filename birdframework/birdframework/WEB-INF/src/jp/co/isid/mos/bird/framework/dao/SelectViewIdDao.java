package jp.co.isid.mos.bird.framework.dao;

import jp.co.isid.mos.bird.framework.entity.SelectViewID;

/**
 * @author xsong
 * セッションエラー時遷移先情報DAO
 */
public interface SelectViewIdDao {
	
	/** 遷移先ViewId */
	public Class BEAN = SelectViewID.class;

    /**
     * 遷移先ViewID取得時のパラメータ
     */
	public static final String getViewID_ARGS = "id";
 
    /**
     * 遷移先ViewIDを取得する。
     *  
     * @param 画面ID
     * @return 遷移先ViewID
     */    
    public String getViewID(final String id);
}
