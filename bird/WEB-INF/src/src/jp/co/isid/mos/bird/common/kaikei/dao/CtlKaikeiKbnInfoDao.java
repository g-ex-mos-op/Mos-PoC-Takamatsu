/*
 * 作成日: 2012/07/25
 */
package jp.co.isid.mos.bird.common.kaikei.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.kaikei.entity.CtlKaikeiKbnInfo;


/**
 * 会計集計区分情報取得
 * 
 * @author xkawa
 * 更新日:2014/12/10 周春建　会計区分拡張対応
 */
public interface CtlKaikeiKbnInfoDao {

    public static final Class BEAN = CtlKaikeiKbnInfo.class;
    //public static final String select_ARGS = "birdDisFlg";
    public static final String select_ARGS = "birdDisFlg,companyCd";

    /**
     * 会計集計区分情報取得の取得
     * @param String birdDisFlg BIRD表示フラグ
     * @param String companyCd 会社コード
     * @return List
     */
    //public List select(String birdDisFlg);
    public List select(String birdDisFlg,String companyCd);

}            
