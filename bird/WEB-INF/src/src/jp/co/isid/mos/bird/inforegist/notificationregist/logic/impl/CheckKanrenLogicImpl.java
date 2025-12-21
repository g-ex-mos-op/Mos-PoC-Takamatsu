package jp.co.isid.mos.bird.inforegist.notificationregist.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UIDocSearch;
import jp.co.isid.mos.bird.inforegist.notificationregist.logic.CheckKanrenLogic;

public class CheckKanrenLogicImpl implements CheckKanrenLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF002L04";

    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
        	String infoShu1 = ((UIDocSearch) obj1).getInfoShu();
        	String infoShu2 = ((UIDocSearch) obj2).getInfoShu();
        	
        	int i_infoShu = infoShu1.compareTo(infoShu2);
        	
        	if(i_infoShu != 0) {
        		return i_infoShu;
        	}
            String val1 = ((UIDocSearch) obj1).getRegDate()
                        + ((UIDocSearch) obj1).getSeq();
            
            String val2 = ((UIDocSearch) obj2).getRegDate()
                        + ((UIDocSearch) obj2).getSeq();
            return val2.compareTo(val1);
        }
    }
    /*
     *  (非 Javadoc)
     * @see jp.co.isid.mos.bird.inforegist.notificationregist.logic.CheckKanrenLogic#execute(java.util.List, java.util.List)
     */
    public List execute(List listKanren, List listAddKanren) {
        
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

        return listKanren;
    }
}
