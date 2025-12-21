/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.usersearch.dao;

import java.util.List;

import jp.co.isid.mos.bird.commonform.usersearch.entity.UIGroupTogoUser;

/**
 * ユーザー検索情報取得
 * @author m.onodera
 */
public interface UIGroupTogoUserDao {

    public static final Class BEAN = UIGroupTogoUser.class;

//    public static final String selectHonbu_ARGS = "R_COMPANY_CD, BUMON_CD, USER_NAME_KJ,ROLE_CD,STOP_FLG";
    public static final String selectHonbu_ARGS = "R_COMPANY_CD, BUMON_CD, USER_NAME_KJ,ROLE_CD,STOP_FLG, userIdCond";

//    public static final String selectOner_ARGS = "R_COMPANY_CD, USER_NAME_KJ, ONER_CD, ONER_NAME_KJ,,ROLE_CD,STOP_FLG";
    public static final String selectOner_ARGS = "R_COMPANY_CD, USER_NAME_KJ, ONER_CD, ONER_NAME_KJ,,ROLE_CD,STOP_FLG, userIdCond";

//    public static final String selectMise_ARGS = "R_COMPANY_CD, USER_NAME_KJ, MISE_CD, MISE_NAME_KJ, ONER_CD, ONER_NAME_KJ,,ROLE_CD,STOP_FLG";
    public static final String selectMise_ARGS = "R_COMPANY_CD, USER_NAME_KJ, MISE_CD, MISE_NAME_KJ, ONER_CD, ONER_NAME_KJ,,ROLE_CD,STOP_FLG, userIdCond";

    public static final String selectMiseFromOwner_ARGS = "COMPANY_CD, ONER_CD, STOP_FLG";

    /**
     * 検索条件よりユーザー情報を取得
     * @param kaisyaCd 会社コード
     * @param bumonCd 部門コード
     * @param userName ユーザー名称
     * @param roleCd 
     * @param strStopFlg 
     * @return List 検索結果
     */
//    public List selectHonbu(String kaisyaCd, String bumonCd, String userName, String roleCd, String strStopFlg);
    public List selectHonbu(String kaisyaCd, String bumonCd, String userName, String roleCd, String strStopFlg, String userIdCond);

    /**
     * 検索条件よりユーザー情報を取得
     * @param kaisyaCd 会社コード
     * @param userName ユーザー名称
     * @param onerCd オーナコード
     * @param onerName オーナ名称
     * @param roleCd 
     * @param strStopFlg 
     * @return List 検索結果
     */
//    public List selectOner(String kaisyaCd, String userName, String onerCd, String onerName, String roleCd, String strStopFlg);
    public List selectOner(String kaisyaCd, String userName, String onerCd, String onerName, String roleCd, String strStopFlg, String userIdCond);

    /**
     * 検索条件よりユーザー情報を取得
     * @param kaisyaCd 会社コード
     * @param userName ユーザー名称
     * @param miseCd   
     * @param miseName 
     * @param onerCd オーナコード
     * @param onerName オーナ名称
     * @param roleCd 
     * @param strStopFlg 
     * @return List 検索結果
     */
    public List selectMise(
            String kaisyaCd, String userName, String miseCd, String miseName, String onerCd, String onerName, 
            String roleCd, String strStopFlg, String userIdCond);
//  public List selectMise(
//  String kaisyaCd, String userName, String miseCd, String miseName, 
//  String onerCd, String onerName, String roleCd, String strStopFlg);

    
    /**
     * オーナーの保有する店舗のユーザーを取得
     * @param kaisyaCd 会社コード
     * @param onerCd オーナコード
     * @param strStopFlg  使用停止フラグ
     * @return 検索結果
     */
    public List selectMiseFromOwner(String kaisyaCd, String onerCd,
            String strStopFlg);

}
