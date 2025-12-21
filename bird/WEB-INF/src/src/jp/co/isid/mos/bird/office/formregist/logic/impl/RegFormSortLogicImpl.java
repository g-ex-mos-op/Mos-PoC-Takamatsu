package jp.co.isid.mos.bird.office.formregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.office.formregist.dao.UIFormInfoDao;
import jp.co.isid.mos.bird.office.formregist.entity.UIFormInfo;
import jp.co.isid.mos.bird.office.formregist.logic.RegFormSortLogic;

/**
 * フォームダウンロード順序の登録
 * @author xytamura
 */
public class RegFormSortLogicImpl implements RegFormSortLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BOF001L07";
    public static final String PGM_ID = "BOF001";
    
    
    /**
     * フォーム情報のDao
     */
    private UIFormInfoDao uIFormInfoDao;
    
    /**
     * フォームダウンロード順序の登録
     * @param List フォーム情報EntityのList
     * @param String ユーザＩＤ
     * @exception ApplicationException
     */
    public void execute(List entityList, String userId) throws ApplicationException {
        // 事前条件、入力値チェック
        validate(entityList);
        
        // 順位項目の前ゼロ付加
        formatValue(entityList);
        
        //重複チェック
        checkDuplicateSortSeq(entityList);
        
        entityList = sortForm( entityList);
        
        formatValue( entityList );
        
        // フォーム情報の更新
        for (int i = 0; i < entityList.size(); i++) {
            UIFormInfo entity = (UIFormInfo) entityList.get(i);
            entity.setLastUser(userId);
            entity.setLastPgm(PGM_ID);
            Timestamp beforeUpdate =entity.getLastTmsp();
            getUIFormInfoDao().updateForm(entity);
            entity.setLastTmsp(beforeUpdate);
        }
    }
    
    /**
     * formのソートを行う
     * @param bunshoList
     * @return
     */
    private List sortForm( List formList) {
        List sortList = new ArrayList();

        if ( formList.size() >= 1000 ) {
            throw new CannotExecuteException("順位設定をすることが");
        }

        sortList.add( formList.get(0));
        for ( int i = 1; i < formList.size(); i++ ) {
            UIFormInfo orgEntity = (UIFormInfo) formList.get(i);
            
            if ( orgEntity.getSortSeq().equals("") ) {
                orgEntity.setSortSeq("0000");
            }
            for ( int k = 0; k < sortList.size(); k++ ) {
                UIFormInfo defEntity = (UIFormInfo)sortList.get(k);
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
            UIFormInfo entity = (UIFormInfo)sortList.get(i);
            entity.setSortSeq( String.valueOf( i * 10 ));
        }
        
        return sortList;
    }
    /**
     * 事前条件
     * @param List フォーム情報EntityのList
     * @throws ApplicationException
     */
    private void validate(List entityList) throws ApplicationException
    {
        CodeVerifier codeVerifier = new CodeVerifier(5);
        
        // 必須チェック
        if (entityList == null || entityList.size() == 0) {
            throw new NotNullException("フォーム情報");
        }
        // 数値、必須チェック
        for (int i = 0; i < entityList.size(); i++) {
            String chkVal = ((UIFormInfo) entityList.get(i)).getSortSeq();
            String num = String.valueOf(i + 1);
            
            if(!codeVerifier.validate(chkVal)){
                throw new InvalidInputException("順序の" + num + "行目");
            }
        }

    }
    
    /**
     * 順序項目の前ゼロ付加編集
     * @param List フォーム情報EntityのList
     */
    private void formatValue(List entityList) {
        // Code Formatter
        CodeFormatter formatter = new CodeFormatter(4, "0000");
        formatter.setFormatPattern("0000");
        
        for (int i = 0; i < entityList.size(); i++) {
            String val = ((UIFormInfo) entityList.get(i)).getSortSeq();
            //ブランク行はそのまま
            if(!isNull(val)){
                ((UIFormInfo) entityList.get(i)).setSortSeq(formatter.format(val, false));
            }
        }
    }
    
    /**
     * 順序の重複チェック
     * @param entityList フォーム情報EntityのList
     */
    private void checkDuplicateSortSeq(List entityList){
        
        for(int i = 0; i < entityList.size(); i++){
            String val1 = ((UIFormInfo) entityList.get(i)).getSortSeq();
            for(int j = i + 1; j < entityList.size(); j++){
                String val2 = ((UIFormInfo) entityList.get(j)).getSortSeq();
                //ブランク行は無視
                if( !isNull(val1) && !isNull(val2) && val1.equals(val2)){
                    throw new DuplicateDataException("順序");
                }
                
            }            
        }
    }
    
    /**
     * ブランク文字チェック
     * @param str チェック対象文字
     * @return trueはブランク
     */
    private boolean isNull(String str){
        if(str == null || str.trim().length() == 0){
            return true;    
        }
        return false;
    }
    
    
    /**
     * @return uIFormInfoDao を戻します。
     */
    public UIFormInfoDao getUIFormInfoDao() {
        return uIFormInfoDao;
    }
    /**
     * @param formInfoDao uIFormInfoDao を設定。
     */
    public void setUIFormInfoDao(UIFormInfoDao formInfoDao) {
        uIFormInfoDao = formInfoDao;
    }
}
