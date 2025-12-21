/*
 * 作成日: 2006/07/05
 *
 */
package jp.co.isid.mos.bird.office.bbsregist.dao;

import java.util.List;

import jp.co.isid.mos.bird.office.bbsregist.entity.UIKeijiban;

/**
 * 掲示板情報DAO
 * @author xytamura
 */
public interface UIKeijibanDao {

    public Class BEAN = UIKeijiban.class;

    public static final String getKeijibanInfo_ARGS = "cateid, from, to";

    public static final String getKeijibanInfoForSearchKeyword_ARGS = "keyWord, titleFlg, msgFlg, from, to";

    public static final String countKeijiban_ARGS = "cateid";

    public static final String countKeijibanForSearchKeyword_ARGS = "keyWord, titleFlg, msgFlg ";

    
    public static final String getNumber_ARGS = "regDate";

    /**
     * 掲示板情報の取得
     * @param cateid カテゴリーID
     * @param from ページFROM
     * @param to ページTO
     * @return
     */
    public List getKeijibanInfo(String cateid, int from, int to);

    /**
     * 掲示板情報の取得(キーワード検索用)
     * @param keyWord 検索文字
     * @param titleFlg タイトルフラグ
     * @param msgFlg メッセージフラグ
     * @param from ページFROM
     * @param to ページTO
     * @return
     */
    public List getKeijibanInfoForSearchKeyword(String keyWord, String titleFlg, String msgFlg,
            int from, int to);

    /**
     * 総件数の取得
     * @param cateid カテゴリーID
     * @return
     */
    public int countKeijiban(String cateid);



    /**
     * 総件数の取得(キーワード検索用)
     * @param keyWord 検索文字
     * @param titleFlg タイトルフラグ
     * @param msgFlg メッセージフラグ
     * @return
     */
    public int countKeijibanForSearchKeyword(String keyWord, String titleFlg, String msgFlg);

    
    /**
     * 最大シーケンス番号の取得
     * @param regDate 登録日
     * @return
     */
    public int getNumber(String regDate);

    /**
     * 掲示板情報の登録
     * @param UIKeijiban 掲示板情報
     * @return
     */
    public int insertKeijibanInfo(UIKeijiban entity);

    /**
     * 掲示板情報の更新
     * @param UIKeijiban 掲示板情報
     * @return
     */
    public int updateKeijibanInfo(UIKeijiban entity);

}
