/*
 * 作成日: 2008/11/19
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.CtlConvShozokuComp;


/**
 * 所属コード会社変換Dao
 * 
 * @author xnkusama
 */
public interface CtlConvShozokuCompDao {

    public static final Class BEAN = CtlConvShozokuComp.class;

    public static final String getUserShozokuComapany_ARGS = "bumonCd";
    
    /**
     * ユーザー所属会社の取得
     * @param bumonCd
     * @return CtlConvShozokuComp
     */
    public List getUserShozokuComapany(String bumonCd);
    
}