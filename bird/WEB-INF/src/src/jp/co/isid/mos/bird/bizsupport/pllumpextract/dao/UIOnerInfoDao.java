/*
 * 作成日: 2006/04/17
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.UIOnerInfo;


/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface UIOnerInfoDao {
	public static final Class BEAN = UIOnerInfo.class;
    
    public static final String getOnerInfo_ARGS = "companyCd,onerCd";
    
    /**
     * オーナー情報の取得
     * @param companyCd 管理会社企業コード
     * @param onerCd オーナー情報 
     * @return オーナー情報
     */
    public List getOnerInfo(String companyCd, String onerCd );
}
