/*
 * 作成日: 2006/04/07
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.MstSVOnerInfo;

/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface MstSvOnerInfoDao {

    public static final Class BEAN = MstSVOnerInfo.class;

    public static final String getSVOnerInfo_ARGS = "userId,companyCd";

    /**
     * SVオーナー情報の取得
     * @param userId ユーザID
     * @return オーナーリスト
     */
    public List getSVOnerInfo(String userId, String companyCd);

}
