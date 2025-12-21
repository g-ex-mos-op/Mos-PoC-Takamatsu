/*
 * 作成日: 2006/1/12
 */
package jp.co.isid.mos.bird.common.dao;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.MstSibu;

/**
 * 支部
 * @author xytamura
 */
public interface MstSibuDao {

    public static final Class BEAN = MstSibu.class;
    public static final String getAllSibu_ARGS = "companyCd";
    public static final String getSibu_ARGS = "companyCd, sibuCd";
    public static final String getSibuTorikomi_ARGS = "companyCd, userId, limit";
    public static final String getSibuHoyuTenpo_ARGS = "companyCd, userId, limit";

    /**
     * 支部を全件取得する
     * @param companyCd 企業コード
     * @return 支部
     */
    public List getAllSibu(String companyCd);

    /**
     * 支部を全件取得する
     * @param companyCd 企業コード
     * @param sibuCd 支部コード
     * @return 支部
     */
    public List getSibu(String companyCd, String sibuCd);

    /**
     * 支部取込コード(エリア大)としての支部情報の取得。
     * (マスタライセンスPh4対応にて作成)
     * 
     * @param companyCd 企業コード
     * @param userId    ユーザID
     * @param limit     制限区分
     * @return List
     */
    public List getSibuTorikomi(String companyCd, String userId, boolean limit);
    
    /**
     * 店舗が保有している支部情報を取得
     * 
     * @param companyCd 企業コード
     * @param userId    ユーザID
     * @param limit     制限区分
     * @return List
     */
    public List getSibuHoyuTenpo(String companyCd, String userId, boolean limit);

}
