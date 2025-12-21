/*
 * 作成日: 2006/02/16
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizadmin.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizadmin.common.entity.UIGroupTogoMise;



/**
 * グループ統合店舗情報
 * 
 * 作成日:2010/03/19
 * @author xkinu
 *
 */
public interface UIGroupTogoMiseDao {
	
	public static final Class BEAN = UIGroupTogoMise.class;
	
	public static final String selectMise_ARGS = "miseCd, companyCd";
    
    public static final String selectHansha_ARGS  = "miseCd, companyCd";
	
	public List selectMise(String miseCd, String companyCd);
    
    /**
     * 2007/06/15 追加　xayumi
     * 
     * 販社オーナーの検索(selectHansha)
     * @param miseCd  店コード
     * @param companyCd  管理会社企業コード
     * @return 検索結果
     */
    public String selectHansha(String miseCd, String companyCd);

}
