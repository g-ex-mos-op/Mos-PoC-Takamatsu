/*
 * 作成日: 2008/10/7
 */
package jp.co.isid.mos.bird.bizreport.moscardniporef.dao;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.moscardniporef.entity.UISuiiNipo;


/**
 * オーナー営業月報ＣＳＶ用データ検索Dao
 * 
 * @author xayumi
 */
public interface UIOnerGepoCSVDao {

    public static final Class BEAN = UISuiiNipo.class;

    public static final String selectSuiNipo_ARGS = "companyCd, onerCd, taishoYM";

    /**
     * オーナー 推移表データ情報の取得
     * 
     * ユーザータイプがオーナーの場合に実行される検索SQL実行処理です。
     * 
     * @param companyCd 会社コード
     * @param onerCd    オーナーコード
     * @param taishoYM  対象月(YYYY/MM)
     * @return
     */
    public List selectSuiNipo(String companyCd, String onerCd, String taishoYM);
    
}