package jp.co.isid.mos.bird.inforegist.documentregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
import jp.co.isid.mos.bird.inforegist.documentregist.dao.UIBunshoInfoDao;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.RegBunshoSortLogic;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 文書照会順序設定
 *
 * 2006/04/03  排他制御対応
 */
public class RegBunshoSortLogicImpl implements RegBunshoSortLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BIF003L05";
    
    /**
     * 文書照会順序の登録
     * @param List 文書情報EntityのList
     * @exception ApplicationException
     */
    public void registBunsho(List entityList) throws ApplicationException {
        // 事前条件、入力値チェック
        validate(entityList);
        
        // 順位項目の前ゼロ付加
        formatValue(entityList);
        
        entityList = sortBunsho(entityList);
        
        formatValue( entityList);
        S2Container container = SingletonS2ContainerFactory.getContainer();
        // 文書情報の更新
        UIBunshoInfoDao dao =
            (UIBunshoInfoDao) container.getComponent(UIBunshoInfoDao.class);
        
        for (int i = 0; i < entityList.size(); i++) {
            Timestamp beforeUpdate =((UIBunshoInfo) entityList.get(i)).getLastTmsp();
            dao.updateBunsho((UIBunshoInfo) entityList.get(i));
            ((UIBunshoInfo) entityList.get(i)).setLastTmsp(beforeUpdate);            
            
        }
    }
    
    /**
     * 文書のソートを行う
     * @param bunshoList
     * @return
     */
    private List sortBunsho( List bunshoList) {
        List sortList = new ArrayList();

        if ( bunshoList.size() >= 1000 ) {
            throw new CannotExecuteException("順位設定をすることが");
        }

        sortList.add( bunshoList.get(0));
        for ( int i = 1; i < bunshoList.size(); i++ ) {
            UIBunshoInfo orgEntity = (UIBunshoInfo) bunshoList.get(i);
            
            if ( orgEntity.getSortSeq().equals("") ) {
                orgEntity.setSortSeq("0000");
            }
            for ( int k = 0; k < sortList.size(); k++ ) {
                UIBunshoInfo defEntity = (UIBunshoInfo)sortList.get(k);
                if ( orgEntity.getSortSeq().compareTo( defEntity.getSortSeq()) < 0) {
                    sortList.add(k,orgEntity);
                    break;
                } else if ( orgEntity.getSortSeq().compareTo( defEntity.getSortSeq()) == 0) {
                    sortList.add( k + 1,orgEntity );
                    break;
                } else if ( orgEntity.getSortSeq().compareTo( defEntity.getSortSeq()) > 0) {
                    if ( k == sortList.size() - 1 ) {
                        sortList.add( orgEntity );
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }       
        
        for ( int i = 0; i < sortList.size(); i++ ) {
            UIBunshoInfo entity = (UIBunshoInfo)sortList.get(i);
            entity.setSortSeq( String.valueOf( i * 10 ));
        }
        
        return sortList;
    }
    /**
     * 事前条件
     * @param List 文書情報EntityのList
     * @throws ApplicationException
     */
    private void validate(List entityList) throws ApplicationException
    {
        //数値バリデーター
        NumericVerifier numericVerifier = new NumericVerifier(false, 4);
        
        // 必須チェック
        if (entityList == null || entityList.size() == 0) {
            throw new NotNullException("文書情報");
        }
        // 数値、必須チェック
        for (int i = 0; i < entityList.size(); i++) {
            String chkVal = ((UIBunshoInfo) entityList.get(i)).getSortSeq();
            if (chkVal != null && !"".equals(chkVal.trim())) {
                if (!numericVerifier.validate(chkVal)) {
                    throw new InvalidInputException("順序");
                }
            }
            
        }
        // 重複チェック
        Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String key1 = ((UIBunshoInfo) obj1).getSortSeq();
                String key2 = ((UIBunshoInfo) obj2).getSortSeq();
                return key1.compareTo(key2);
            }
        };
        Collections.sort(entityList, sortComparator);
        String oldKey = "";
        String newKey = "";
        for (Iterator ite = entityList.iterator(); ite.hasNext();) {
            UIBunshoInfo entity = (UIBunshoInfo) ite.next();
            newKey = entity.getSortSeq();
            if (newKey != null && !"".equals(newKey.trim())) {
                if (oldKey.equals(newKey)) {
                    throw new DuplicateDataException("順序");
                }
            }
            oldKey = newKey;
        }
        

    }
    
    /**
     * 順位項目の前ゼロ付加編集
     * @param List 文書情報EntityのList
     */
    private void formatValue(List entityList) {
        // Code Formatter
        CodeFormatter formatter = new CodeFormatter(4, "0000");
        formatter.setFormatPattern("0000");
        
        for (int i = 0; i < entityList.size(); i++) {
            String val = ((UIBunshoInfo) entityList.get(i)).getSortSeq();
            if (val != null && !"".equals(val.trim())) {
                ((UIBunshoInfo) entityList.get(i)).setSortSeq(formatter.format(val, false));
            }
        }
    }
}
