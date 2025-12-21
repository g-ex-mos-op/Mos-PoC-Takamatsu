/*
 * 作成日: 2006/04/17
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
package jp.co.isid.mos.bird.bizsupport.pllumpextract.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pllumpextract.entity.CodCompanyInfo;



/**
 * @author xkhata
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */
public interface CodCompanyInfoDao {

    public static final Class BEAN = CodCompanyInfo.class;

    public static final String getCompanyInfo_ARGS = "rCompanyCd";
    
    public List getCompanyInfo( String rCompanyCd) ;

}
