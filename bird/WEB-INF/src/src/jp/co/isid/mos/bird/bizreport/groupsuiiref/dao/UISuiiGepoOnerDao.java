/*
 * 作成日: 2006/05/29
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiGepo;


/**
 * 推移表日次データ情報検索Dao
 * 
 * @author xkinu
 */
public interface UISuiiGepoOnerDao {

    public static final Class BEAN = UISuiiGepo.class;

    public static final String select_ARGS = "userId, userTypeCd, onerCd, companyCd, zennenDatashubetu, taishoJoken, hyojiTaisho, kikanFrom, kikanTo";
    /**
     * オーナー・店舗用 推移表データ情報の取得
     * 
     * ユーザータイプが本部以外の場合に実行される検索SQL実行処理です。
     * 
     * @param userId ユーザーID
     * @param userTypeCd ユーザータイプコード
     * @param companyCd 会社コード
     * @param zennenDatashubetu 前年データ種別 [前年同日・前年同月・前年同曜]
     * @param taishoJoken 対象条件 [全社・モスバーガー・モスバーガー以外・事業本部・営業エリア・支部・個店]
     * @param hyojiTaisho 表示対象
     * @param kikanFrom 期間開始年月 yyyyMM
     * @param kikanTo 期間終了年月 yyyyMM
     * @return List
     */
    public List select(
              String userId
            , String userTypeCd
            , String onerCd
            , String companyCd
            , String zennenDatashubetu
            , String taishoJoken
            , String hyojiTaiso
            , String kikanFrom
            , String kikanTo);    


}