package jp.co.isid.mos.bird.sysadmin.help.dao;

import jp.co.isid.mos.bird.sysadmin.help.entity.HelpVersion;


/**
 * ヘルプ変更履歴取得Dao
 * 
 * IEキャッシュ対応用
 * 
 * 作成日:2012/03/28
 * @author xkinu
 *
 */
public interface HelpVersionDao {
	
	public Class BEAN = HelpVersion.class;
	public static final String select_SQL = "SELECT *  FROM BR83VERS WHERE TYPE_CD = '"+HelpVersion.TYPE_CD+"'";
	
    /**
     * ヘルプ変更履歴取得
     * @return
     */ 
    public HelpVersion select();

}