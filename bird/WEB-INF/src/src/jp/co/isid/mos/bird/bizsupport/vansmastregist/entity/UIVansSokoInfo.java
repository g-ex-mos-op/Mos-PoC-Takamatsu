/*
 * 作成日: 2007/02/08
 */
package jp.co.isid.mos.bird.bizsupport.vansmastregist.entity;

/**
 * 対象倉庫情報
 * @author narita
 */
public class UIVansSokoInfo {
    
    public static final String TABLE = "TM17SOKO";
    
    public static final String kanriMoto_COLUMN = "KANRI_MOTO";
    
    public static final String sokoNameKj_COLUMN = "SOKO_NAME_KJ";
    
    /**
     * 管理元コード
     */
    private String kanriMoto;
    
    /**
     * 倉庫名称
     */
    private String sokoNameKj;
    
    /**
     * 管理元コードを取得します。
     * @return 管理元コード
     */
    public String getKanriMoto() {
        return kanriMoto;
    }
    /**
     * 管理元コードを設定します。
     * @param kanriMoto 管理元コード
     */
    public void setKanriMoto(String kanriMoto) {
        this.kanriMoto = kanriMoto;
    }
    
    /**
     * 倉庫名称を取得します。
     * @return 倉庫名称
     */
    public String getSokoNameKj() {
        return sokoNameKj;
    }
    /**
     * 倉庫名称を設定します。
     * @param sokoNameKj 倉庫名称
     */
    public void setSokoNameKj(String sokoNameKj) {
        this.sokoNameKj = sokoNameKj;
    }
    
}
