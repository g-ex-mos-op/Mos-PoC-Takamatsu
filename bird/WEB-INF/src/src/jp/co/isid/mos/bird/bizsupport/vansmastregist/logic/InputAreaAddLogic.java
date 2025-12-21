/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic;

import java.util.List;

/**
 * 入力欄の追加ロジックインターフェース
 * @author narita
 */
public interface InputAreaAddLogic {

    /**
     * 入力欄の追加を行う
     * @param shohinList 管理元別商品リスト
     * @param addMode 追加区分
     * @param addSelectSoko 倉庫コード
     * @param addSelectKanri 管理元コード
     * @return shohinList 管理元別商品リスト
     */
    public List execute(List shohinList,int addMode,String addSelectSoko,String addSelectKanri);
    
	/**
     * 登録商品数の取得を行う
     * @param shohinList 代表商品リスト 
     * @return int 登録商品数
     */
    public int getShohinCount(List shohinList,String sokoCd,int firstCount );
    
    public String getAddFocusName(List shohinList,String addSelectKanri,String addSelectSoko);
}
