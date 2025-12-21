/**
 * 
 */
package jp.co.isid.mos.bird.bizsupport.energyamount.logic;

import java.util.Map;

import jp.co.isid.mos.bird.framework.control.BirdUserInfo;

/**
 * 条件項目の取得設定ロジック
 * 
 * @author xnkusama
 *
 */
public interface ConditionInfoLogic {
    /*戻り値Mapのキー*/
    //支部一覧
    public static final String MAP_KEY_SIBU = "SIBU";
    //エネルギー使用量 年月指定用プルダウン
    public static final String MAP_KEY_ENERGY_NENGETU = "ENERGY_NENGETU";
    //エネルギー使用量 年度累計用プルダウン
    public static final String MAP_KEY_ENERGY_NENDO = "ENERGY_NENDO";
    //未入力店舗一覧 年月指定用プルダウン
    public static final String MAP_KEY_NOINPUT_NENGETU = "NOINPUT_NENGETU";
    //対象期間用プルダウン
    public static final String MAP_KEY_TAISHO_KIKAN = "TAISHO_KIKAN";
    //未入力店舗一覧 対象期間用プルダウン
    public static final String MAP_KEY_NOINPUT_TAISHO_KIKAN = "NOINPUT_TAISHO_KIKAN";
    //メーター区分用プルダウン
    public static final String MAP_KEY_METER_KBN = "METER_KBN";
    //未入力店舗一覧 メーター区分用プルダウン
    public static final String MAP_KEY_NOINPUT_METER_KBN = "NOINPUT_METER_KBN";
    //推移表用：営業エリアプルダウン　追加日：2010/05/14 xkinu
    public static final String MAP_KEY_SUII_TAISHO_JOKEN = "SUII_TAISHO_JOKEN";
    //推移表用：支部プルダウン　追加日：2010/05/14 xkinu
    public static final String MAP_KEY_SUII_SIBU = "SUII_SIBU";
    //推移表用：営業エリアプルダウン　追加日：2010/05/14 xkinu
    public static final String MAP_KEY_SUII_SLAREA = "SUII_SLAREA";
    //推移表用：年度累計用プルダウン　追加日：2010/05/14 xkinu
    public static final String MAP_KEY_SUII_NENDO = "SUII_NENDO";
    
	/**
	 * 処理実行
	 * 
	 * @param companyCd
     * @param sysDate
	 * @return
	 */
    public Map execute(String companyCd, String sysDate, BirdUserInfo birdUserInfo);

}
