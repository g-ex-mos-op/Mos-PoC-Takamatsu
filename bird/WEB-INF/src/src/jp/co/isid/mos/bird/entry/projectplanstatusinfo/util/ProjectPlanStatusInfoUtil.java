package jp.co.isid.mos.bird.entry.projectplanstatusinfo.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


/**
 * 事業方針説明会申込状況確認
 * static 処理保持クラス
 * 
 * @author xamaruyama
 *
 */
public class ProjectPlanStatusInfoUtil {

    /** VIEWID：SV選択 */
    public static final String VIEW_ID_SVSEARCH   = "BCO011V01";
    /** VIEWID：事業方針共通条件画面 */
    public static final String VIEW_ID_COMMONCONDITION   = "BEN092V01";
    /** 画面ID */
    public static final String SCREEN_ID = "BEN012";
    /** VIEWID：初期(条件)画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /** VIEWID：事業方針説明会、懇親会、共栄会定時総会画面 */
    public static final String VIEW_ID_ATTENDANCE = SCREEN_ID+"V02";
    /** VIEWID：本部手配宿泊画面 */
    public static final String VIEW_ID_LODGER = SCREEN_ID+"V03";
    /** エントリーコード：事業方針説明会コード 「25」*/
    public static final String ENTRY_CD = "25";
    /** 対象条件値：全オーナー "0" */
    public static final String TAISHO_JOKEN_ALLONSER = "0";
    /** 対象条件：支部 "1" */
    public static final String TAISHO_JOKEN_SIBU = "1";
    /** 対象条件：SVコード "2" */
    public static final String TAISHO_JOKEN_SV = "2";
    /** 対象：事業方針説明会 "0" */
    public static final String TAISHO_PROJECTPLAN = "0";
    /** 対象：懇親会 "1" */
    public static final String TAISHO_KONSIN = "1";
    /** 対象：共栄会定時総会 "2" */
    public static final String TAISHO_KYOEIKAI = "2";
    /** 対象：本部手配宿泊 "3" */
    public static final String TAISHO_HONBU_SHUKUHAKU = "3";
    /** 対象：結果確認ダウンロード "4" */
    public static final String TAISHO_DOWNLOAD = "4";
    /** 区分：全て "2" */
    public static final String KBN_ALL = "2";
    /** 区分：出席 "0" */
    public static final String KBN_SHUSSEKI = "0";
    /** 区分：欠席 "1" */
    public static final String KBN_KESSEKI = "1";
    /** 区分：未登録 "3" */
    public static final String KBN_MITOUROKU = "3";
    /** 対象条件名称：全オーナー "0" */
    public static final String LABEL_TAISHO_JOKEN_ALLONSER = "全オーナー";
    /** 対象条件名称：支部 "1" */
    public static final String LABEL_TAISHO_JOKEN_SIBU = "支部";
    /** 対象条件名称：SVコード "2" */
    public static final String LABEL_TAISHO_JOKEN_SV = "SVコード";
    /** 対象：事業方針説明会 "0" */
    public static final String LABEL_TAISHO_PROJECTPLAN = "事業方針説明会";
    /** 対象：懇親会 "1" */
    public static final String LABEL_TAISHO_KONSIN = "懇親会";
    /** 対象：共栄会定時総会 "2" */
    public static final String LABEL_TAISHO_KYOEIKAI = "共栄会定時総会";
    /** 対象：本部手配宿泊 "3" */
    public static final String LABEL_TAISHO_HONBU_SHUKUHAKU = "本部手配宿泊";
    /** 対象：結果確認ダウンロード "4" */
    public static final String LABEL_TAISHO_DOWNLOAD = "結果確認ダウンロード";
    /** 区分：全て "2" */
    public static final String LABEL_KBN_ALL = "全て";
    /** 区分：出席 "0" */
    public static final String LABEL_KBN_SHUSSEKI = "出席";
    /** 区分：欠席 "1" */
    public static final String LABEL_KBN_KESSEKI = "欠席";
    /** 区分：未登録 "3" */
    public static final String LABEL_KBN_MITOUROKU = "未登録";

    /**
     * コンストラクター
     *
     */
    private ProjectPlanStatusInfoUtil() {
        super();
    }
    /**
     * String用Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * 条件項目『対象条件』プルダウン用リストデータ作成取得処理
     * 
     * @return List 
     */
    public static List getListTaishoJoken(){
        List list = new ArrayList();
        list.add(new SelectItem(TAISHO_JOKEN_ALLONSER, LABEL_TAISHO_JOKEN_ALLONSER));
        list.add(new SelectItem(TAISHO_JOKEN_SIBU, LABEL_TAISHO_JOKEN_SIBU));
        list.add(new SelectItem(TAISHO_JOKEN_SV, LABEL_TAISHO_JOKEN_SV));
        return list;
    }
    /**
     * 条件項目『対象』プルダウン用リストデータ作成取得処理
     * 
     * @return List 
     */
    public static List getListTaisho(){
        List list = new ArrayList();
        list.add(new SelectItem(TAISHO_PROJECTPLAN, LABEL_TAISHO_PROJECTPLAN));
        list.add(new SelectItem(TAISHO_KONSIN, LABEL_TAISHO_KONSIN));
        list.add(new SelectItem(TAISHO_KYOEIKAI, LABEL_TAISHO_KYOEIKAI));
        list.add(new SelectItem(TAISHO_HONBU_SHUKUHAKU, LABEL_TAISHO_HONBU_SHUKUHAKU));
        list.add(new SelectItem(TAISHO_DOWNLOAD, LABEL_TAISHO_DOWNLOAD));
        return list;
    }
    /**
     * ダウンロード条件項目『区分』プルダウン用リストデータ作成取得処理
     * 
     * @return List 
     */
    public static List getListKbn(){
        List list = new ArrayList();
        list.add(new SelectItem(KBN_ALL, LABEL_KBN_ALL));
        list.add(new SelectItem(KBN_SHUSSEKI, LABEL_KBN_SHUSSEKI));
        list.add(new SelectItem(KBN_KESSEKI, LABEL_KBN_KESSEKI));
        list.add(new SelectItem(KBN_MITOUROKU, LABEL_KBN_MITOUROKU));
        return list;
    }
    /**
     * 条件項目『対象条件』名称取得処理
     * 
     * @param target
     * @return
     */
    public static String getTaishoJokenName(String target){
        String targetName = "";
        if(TAISHO_JOKEN_ALLONSER.equals(target)) {
            targetName = LABEL_TAISHO_JOKEN_ALLONSER;
        }
        else if(TAISHO_JOKEN_SIBU.equals(target)) {
            targetName = LABEL_TAISHO_JOKEN_SIBU;
        }
        else if(TAISHO_JOKEN_SV.equals(target)) {
            targetName = LABEL_TAISHO_JOKEN_SV;
        }
        return targetName;
    }
    /**
     * 条件項目『対象』名称取得処理
     * 
     * @param target
     * @return
     */
    public static String getTaishoName(String target){
        String targetName = "";
        if(TAISHO_PROJECTPLAN.equals(target)) {
            targetName = LABEL_TAISHO_PROJECTPLAN;
        }
        else if(TAISHO_KONSIN.equals(target)) {
            targetName = LABEL_TAISHO_KONSIN;
        }
        else if(TAISHO_KYOEIKAI.equals(target)) {
            targetName = LABEL_TAISHO_KYOEIKAI;
        }
        else if(TAISHO_HONBU_SHUKUHAKU.equals(target)) {
            targetName = LABEL_TAISHO_HONBU_SHUKUHAKU;
        }
        else if(TAISHO_DOWNLOAD.equals(target)) {
            targetName = LABEL_TAISHO_DOWNLOAD;
        }
        return targetName;
    }
    /**
     * ダウンロード条件項目『区分』名称取得処理
     * 
     * @param target
     * @return
     */
    public static String getKbnName(String target){
        String targetName = "";
        if(KBN_ALL.equals(target)) {
            targetName = LABEL_KBN_ALL;
        }
        else if(KBN_SHUSSEKI.equals(target)) {
            targetName = LABEL_KBN_SHUSSEKI;
        }
        else if(KBN_KESSEKI.equals(target)) {
            targetName = LABEL_KBN_KESSEKI;
        }
        else if(KBN_MITOUROKU.equals(target)) {
            targetName = LABEL_KBN_MITOUROKU;
        }
        return targetName;
    }
}