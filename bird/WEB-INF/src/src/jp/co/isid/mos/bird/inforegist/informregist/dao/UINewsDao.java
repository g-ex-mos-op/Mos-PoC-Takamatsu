
/*
 * 作成日: 2006/2/10
 */
package jp.co.isid.mos.bird.inforegist.informregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.inforegist.informregist.entity.UINews;

/**
 * お知らせ情報取得
 * @author itamoto
 */
public interface UINewsDao {

    public static final Class BEAN = UINews.class;
    public static final String getNews_ARGS  = "REG_DATE, USER_ID";
    public static final String getNumber_ARGS  = "REG_DATE";
    public static final String updateNews_PERSISTENT_PROPS 
							= "title, pubDateFrom, pubDateTo, simpleMsg, message,"
							+ "pubOrg, pubOrgName, attachName1, attachName2, attachName3, "
                            + "attachFl1, attachFl2, attachFl3, sakujoFlg,"
							+ "lastUser, lastPgm, lastTmsp";

    /**
     * お知らせ既存情報一覧の取得
     * @param regDate 選択年月
     * @param userIdユーザーID
     * @return List 検索結果
     */
    public List getNews (String regDate, String userId);

    /**
     * シーケンス番号の取得
     * @param regDate 登録日
     * @return void なし
     */
    public int getNumber (String regDate);
    
    /**
     * 新規お知らせ情報の登録
     * @param uiNews  エンティティ
     * @return int なし
     */
    public int insertNews (UINews uiNews);
    
    /**
     * お知らせ情報の変更
     * @param companyCd  企業コード
     * @return void なし
     */
    public int updateNews (UINews uiNews);
}
