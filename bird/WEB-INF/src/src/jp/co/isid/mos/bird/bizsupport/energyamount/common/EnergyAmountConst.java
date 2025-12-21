package jp.co.isid.mos.bird.bizsupport.energyamount.common;

/**
 * エネルギー使用量ダウンロード画面定数クラス
 * @author xnkusama
 *
 */
public class EnergyAmountConst {

    /**対象データ*/
    public static final String TAISHO_DATA_LIST = "LIST";
    public static final String TAISHO_DATA_NOINPUT = "NOINPUT";
    public static final String TAISHO_DATA_SUII = "SUII";
    /**対象期間*/
    public static final String TAISHO_KIKAN_NENGETU = "NENGETU";
    public static final String TAISHO_KIKAN_NENDO = "NENDO";
    /**対象期間名称*/
    public static final String TAISHO_KIKAN_NENGETU_NAME = "年月指定";
    public static final String TAISHO_KIKAN_NENDO_NAME = "年度累計";
    /**メーター区分*/
    public static final String METER_KBN_TENPO = "0";
    public static final String METER_KBN_OFFICE = "1";
    /**メーター区分名称*/
    public static final String METER_KBN_TENPO_NAME = "店舗";
    public static final String METER_KBN_OFFICE_NAME = "事務所";
    /**対象条件 すべて*/
    public static final String TAISHO_JOKEN_ALL = "ALL";
    
    
    //入力対象外 
    public static final String METER_NO_INPUT_FLG = "0";
    //入力対象
    public static final String METER_INPUT_FLG = "1";
    //入力対象外 表示用文字
    public static final String NO_INPUT_VIEW = "※";
    //データメンテ実施済み
    public static final String DATA_MAINTENANCE_DONE = "※";
}
