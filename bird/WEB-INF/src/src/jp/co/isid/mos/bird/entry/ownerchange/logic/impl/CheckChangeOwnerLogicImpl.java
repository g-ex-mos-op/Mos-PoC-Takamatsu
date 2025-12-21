/*
 * 作成日:2007/01/17
 */
package jp.co.isid.mos.bird.entry.ownerchange.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.entry.ownerchange.logic.CheckChangeOwnerLogic;


/**
 * 変更後オーナーチェックロジック
 * @author xkonishi
 *
 */
public class CheckChangeOwnerLogicImpl implements CheckChangeOwnerLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BEN023L07";
    
    /**
     * 変更後オーナーチェック
     * @param 店舗情報
     */
    public boolean execute(List miseInfo) {
        
        boolean isEmpty = false;
        // 変更したオーナーの保有店舗が全てクローズ店の場合
        if(miseInfo == null || miseInfo.isEmpty() ){
            isEmpty = true;
        }
        return isEmpty;
    }
}