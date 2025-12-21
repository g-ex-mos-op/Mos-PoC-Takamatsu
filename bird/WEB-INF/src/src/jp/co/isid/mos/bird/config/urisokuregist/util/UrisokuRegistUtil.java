/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.commonform.menusearch.dto.MenuSearchDto;
import jp.co.isid.mos.bird.config.urisokuregist.dto.SessionDto;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIUsrMenu;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * [機能設定]【売上速報設定】
 * staticクラス
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class UrisokuRegistUtil {
	/** 画面ID */
	public static final String SCREEN_ID = "BCF007";
    /** VIEW_ID：初期画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /** VIEW_ID：編集画面 */
    public static final String VIEW_ID_EDIT_MISECNT = SCREEN_ID+"V02";
    /** VIEW_ID：編集画面 */
    public static final String VIEW_ID_EDIT_TVCM = SCREEN_ID+"V03";
    /** VIEW_ID：編集画面 */
    public static final String VIEW_ID_EDIT_MENU = SCREEN_ID+"V04";
    /** VIEW_ID：編集画面 */
    public static final String VIEW_ID_EDIT_CAMP = SCREEN_ID+"V05";
    /** VIEW_ID：確認画面 */
    public static final String VIEW_ID_CONFIRM = SCREEN_ID+"V06";
	/** コード：対象日*/
	public static final String FRAME_KBN_LEFT = "01";
	/** コード：期間合計 */
	public static final String FRAME_KBN_CENTER = "02";
	/** コード：期間合計 */
	public static final String FRAME_KBN_RIGHT = "03";
	
	public static final String SELECTED = "1";
	public static final String DATA_KBN_COMMENT = "0";
	public static final String DATA_KBN_MENU = "1";
	/** テーブル：デフォルト */
	public static final String [][] FRAME_KBN_TABLE = {
		{FRAME_KBN_LEFT, "左"}
		 ,  {FRAME_KBN_CENTER,"中央"}
		 ,  {FRAME_KBN_RIGHT,"右"}
	};
	public static final int[] WEEKS = {Calendar.SUNDAY
		,Calendar.MONDAY
		,Calendar.TUESDAY
		,Calendar.WEDNESDAY
		,Calendar.THURSDAY
		,Calendar.FRIDAY
		,Calendar.SATURDAY
	};

    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
	private static final CodeFormatter code2Formatter = new CodeFormatter(2);
    /**
     * 年月リストを取得する
     * 
     * @param sysDate システム日付
     * @param cnts   当月含めて未来？ヶ月
     * @return
     */
    public static List creatListNengetu(String sysDate, int cnts) {
        List list = new ArrayList();
        String startNengetu = sysDate.substring(0,6);
        for (int index=0; index<cnts; index++) {
            try {
           		String nengetu = DateManager.getNextMonth(startNengetu, index);
	            String name = formatter.format(nengetu, DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM);
	            list.add(index, createKikanEntity(nengetu, name));
	        }catch (Exception ex) {
	            throw new FtlSystemException("期間指定生成"
	                    , "指定開始日["+startNengetu+"]から["+index+"]を計算する際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
	                    , ex);
	        }
        }
        return list;
    }
    /**
     * エンティティ生成処理
     * @param code
     * @param name
     * @return
     */
    private static SelectItem createKikanEntity(String code, String name) {
        SelectItem entity = new SelectItem();
        entity.setValue(code);
        entity.setLabel(name);
        return entity;
    }
    /**
     * 指定フレーム区分データ取得処理
     * 
     * @param dto
     * @param frameKbn
     * @return
     */
    public static UILists getFrameData(SessionDto dto, String frameKbn) {
    	List listData = dto.getListRegistDataMenu();
    	for (int f=0; f<listData.size(); f++) {
    		UILists frame = (UILists)listData.get(f);
    		if(frame.getKey().equals(frameKbn)) {
    			return frame;
    		}
    	}
    	return null;
    }
    /**
     * 選択済みメニュー情報格納処理
     * 
     * @param sessionDto
     * @param menuDto
     */
    public static void setSelectedMenuInfo(SessionDto sessionDto, MenuSearchDto menuDto) {
    	List listRegistMenu = sessionDto.getListRegistDataMenu();
    	int targetIndex = sessionDto.getSelectedSearchMenuIndex();
    	for(int i=0; i<listRegistMenu.size(); i++) {
    		UILists frame = (UILists)listRegistMenu.get(i);
    		if(sessionDto.getSelectedTabSub().equals(frame.getKey())) {
    			List listMenu = frame.getListData();
    			UIUsrMenu entity = (UIUsrMenu)listMenu.get(targetIndex);
    			entity.setMenuCd(menuDto.getMenuCd());
    			entity.setMenuNameKj(menuDto.getMenuNameKj());
    			break;
    		}
    	}
    }
    /**
     * フレーム区分別メニューリスト生成処理
     * 
     * @param taishoYm
     * @param frameKbn
     * @return
     */
    public static List createListUIUsrMenu(String taishoYm, String frameKbn) {
        code2Formatter.setFormatPattern("00");
        List listMenuData = new ArrayList(0);
        for(int i=1; i<=20; i++) {
        	//while-1.新しいentity[売上速報商品グループ]をインスタンス化します。
        	UIUsrMenu eMenu = new UIUsrMenu();
        	eMenu.setUriSokuYm(taishoYm);
        	eMenu.setFrameKbn(frameKbn);
        	eMenu.setSeqNo(code2Formatter.format(String.valueOf(i),true));
        	//while-2.新しいentity[売上速報商品グループ]をインスタンス化します。
        	listMenuData.add(eMenu);
        }
        return listMenuData;
    }
}
