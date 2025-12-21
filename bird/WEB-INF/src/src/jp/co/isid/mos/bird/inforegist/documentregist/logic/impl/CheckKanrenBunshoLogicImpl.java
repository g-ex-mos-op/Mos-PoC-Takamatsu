package jp.co.isid.mos.bird.inforegist.documentregist.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.CheckKanrenBunshoLogic;

public class CheckKanrenBunshoLogicImpl implements CheckKanrenBunshoLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF003L06";
    
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((UIDocSearch) obj1).getInfoShu()
                        + ((UIDocSearch) obj1).getRegDate()
                        + ((UIDocSearch) obj1).getSeq();
            
            String val2 = ((UIDocSearch) obj2).getInfoShu()
                        + ((UIDocSearch) obj2).getRegDate()
                        + ((UIDocSearch) obj2).getSeq();
            
            return val1.compareTo(val2);
        }
    }
    /**
     * 関連文書の重複チェック
     * @param List 登録済み関連文書リスト
     * @param List 追加関連文書リスト
     * @exception ApplicationException
     */
    public List check(List listKanren, List listAddKanren) throws ApplicationException {
        
        // ２つのリストを結合
        if (listKanren == null && listAddKanren == null) {
            return new ArrayList();
        }
        if (listKanren == null) {
            return listAddKanren;
        }
        if (listAddKanren == null) {
            return listKanren;
        }

// 2006/03/22 xkhata 関連文書重複対応
        List cpListKanren = new ArrayList();
        
        for ( int i = 0; i < listAddKanren.size(); i++ ) {
            boolean entityAfFlg = true;
        	UIDocSearch entityAf = (UIDocSearch) listAddKanren.get(i);
            entityAf.setCheckedDoc( false );
            for ( int j = 0; j < listKanren.size(); j++ ) {
            	UIDocSearch entityBf = (UIDocSearch) listKanren.get(j);
                if ( entityAf.getInfoShu().equals( entityBf.getInfoShu()) ) {
                	if ( entityAf.getRegDate().equals( entityBf.getRegDate() ) ) {
                		if ( entityAf.getSeq().equals( entityBf.getSeq() ) ) {
                			entityAfFlg = false;
                            break;
                        }
                    }
                }
            }
            
            if ( entityAfFlg ) {
            	cpListKanren.add(entityAf);
            }
        }
        
        listKanren.addAll( cpListKanren );
        
        Collections.sort(listKanren, new SortComparator());

// end
        
        
/*        listKanren.addAll(listAddKanren);
        
        Collections.sort(listKanren, new SortComparator());
        
        // 重複行を検索
        for (int i = 0; i < listKanren.size(); i++) {
            UIDocSearch entityBf = (UIDocSearch) listKanren.get(i);
            // チェックボックス部分のフラグクリア
            entityBf.setCheckedDoc(false);
            if (i + 1 < listKanren.size()) {
                UIDocSearch entityAf = (UIDocSearch) listKanren.get(i + 1);
                if (entityBf.getInfoShu().equals(entityAf.getInfoShu()) &&
                        entityBf.getRegDate().equals(entityAf.getRegDate()) &&
                        entityBf.getSeq().equals(entityAf.getSeq()))
                {
                    listDelIndex.add(String.valueOf(i));
                }
            }
        }
        // 重複行を削除
        for (int i = 0; i < listDelIndex.size(); i++) {
            int index = Integer.valueOf((String) listDelIndex.get(i)).intValue();
            listKanren.remove(index);
        }
*/        
        return listKanren;
    }
}
