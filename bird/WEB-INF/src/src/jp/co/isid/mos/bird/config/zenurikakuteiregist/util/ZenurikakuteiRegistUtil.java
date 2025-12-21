/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

/**
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public class ZenurikakuteiRegistUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BCF008";
    /** VIEW_ID：編集画面 */
    public static final String VIEW_ID_EDIT = SCREEN_ID+"V01";
    /** VIEW_ID：編集画面 */
    public static final String VIEW_ID_CONFIRM = SCREEN_ID+"V02";
    /** 確定ステータス：確定値 */
    public static final String STATUS_KAKUTEI = "1";
    /** 確定ステータス：計算値 */
    public static final String STATUS_KEISAN = "0";
    /** 確定ステータス：確定値 */
    public static final String STATUS_KAKUTEI_KJ = "確定値";
    /** 確定ステータス：計算値 */
    public static final String STATUS_KEISAN_KJ = "計算値";
    /**
     * 年月リストを取得する
     * 
     * @param sysDate システム日付
     * @param cnts   当月含めて未来？ヶ月
     * @return
     */
    public static List creatListStatus() {
        List list = new ArrayList();
        list.add(0, createEntity(STATUS_KEISAN, getStatusName(STATUS_KEISAN)));
        list.add(0, createEntity(STATUS_KAKUTEI, getStatusName(STATUS_KAKUTEI)));
        return list;
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private static SelectItem createEntity(String code, String name) {
        SelectItem entity = new SelectItem();
        entity.setValue(code);
        entity.setLabel(name);
        return entity;
    }
    /**
     * ステータス名称取得処理
     * 
     * @param status
     * @return
     */
    public static String getStatusName(String status) {
    	if(STATUS_KEISAN.equals(status)) {
    		return STATUS_KEISAN_KJ;
    	}
    	if(STATUS_KAKUTEI.equals(status)) {
    		return STATUS_KAKUTEI_KJ;
    	}
    	return "";
    }
}
