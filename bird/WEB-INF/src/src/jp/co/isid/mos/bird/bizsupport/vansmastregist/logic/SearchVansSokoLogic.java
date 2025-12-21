/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.logic;

import java.util.List;

/**
 * 対象倉庫情報の取得ロジックインターフェース
 * @author narita
 */
public interface SearchVansSokoLogic {

    /**
     * 対象倉庫情報の取得を行う
     * @return sokoList 対象倉庫情報のリスト
     */
    public List execute();
    
    /**
     * 対象倉庫情報のリストから管理元名称を取得する
     * @param sokoList 対象倉庫情報のリスト
     * @param kanriMoto 管理元コード
     * @return kanriMotoKj 管理元名称
     */
    public String getKanriMotoKj(List sokoList, String kanriMoto);
}

