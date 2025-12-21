/**
 * 
 */
package jp.co.isid.mos.bird.config.urisokuregist.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.urisokuregist.dao.TrnTvcmDao;
import jp.co.isid.mos.bird.config.urisokuregist.dao.UIMiseCntDao;
import jp.co.isid.mos.bird.config.urisokuregist.dao.UIUsrCampDao;
import jp.co.isid.mos.bird.config.urisokuregist.entity.TrnTvcm;
import jp.co.isid.mos.bird.config.urisokuregist.entity.UIMiseCnt;
import jp.co.isid.mos.bird.config.urisokuregist.logic.GetRegistDataLogic;
import jp.co.isid.mos.bird.config.urisokuregist.logic.GetShohinMenuLogic;
import jp.co.isid.mos.bird.config.urisokuregist.util.UrisokuRegistUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * [機能設定]【売上速報設定】
 * 編集情報取得ロジック
 * 
 * 作成日:2008/08/06
 * @author xkinu
 *
 */
public class GetRegistDataLogicImpl implements GetRegistDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = UrisokuRegistUtil.SCREEN_ID+"L02";

    /** DAO【売上速報レポート用店舗数】*/
	private UIMiseCntDao urisokuRegistUIMiseCntDao;
	/** DAO【TVCM放映日】*/
	private TrnTvcmDao urisokuRegistTrnTvcmDao;
	/** DAO【売上速報レポート用キャンペーン】*/
	private UIUsrCampDao urisokuRegistUIUsrCampDao;
	
	/** LOGIC【売上速報レポート用商品メニューの取得】*/
	private GetShohinMenuLogic urisokuRegistGetShohinMenuLogic;

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.config.urisokuregist.logic.GetRegistDataLogic#execute(java.lang.String)
	 */
	public List execute(String taishoYm) {
        //１．事前条件判断処理
        validate(taishoYm);
        //２．変数List[[編集情報]]をインスタンス化します。
        List listRegistData = new ArrayList(0);
        //３．内部処理＜売上速報レポート用店舗数情報取得処理＞を実行し、
        //    処理２のList[[編集情報]]へ戻り値Uilist[店舗数情報]を追加します。
        listRegistData.add(getRegistDataMiseCnt(taishoYm));
        //４．内部処理＜TVCM放映日情報取得処理＞を実行し、
        //    処理２のList[[編集情報]]へ戻り値Uilist[TVCM放映日]を追加します。
        listRegistData.add(getRegistDataTvcm(taishoYm));
        //５．内部処理＜商品メニュー情報取得処理＞を実行し、
        //    処理２のList[[編集情報]]へ戻り値Uilist[商品メニュー]を追加します。
        listRegistData.add(getRegistDataShohinMenu(taishoYm));
        //６．内部処理＜キャンペーン情報取得処理＞を実行し、
        //    処理２のList[[編集情報]]へ戻り値Uilist[キャンペーン]を追加します。
        listRegistData.add(getRegistDataCamp(taishoYm));
        //７．処理２のList[[編集情報]]をリターンします。
		return listRegistData;
	}
	/**
	 * 売上速報レポート用店舗数情報取得処理
	 * 
	 * 店舗数の情報をDBから取得します。
	 * 
	 * @param taishoYm
	 * @return
	 */
	private UILists getRegistDataMiseCnt(String taishoYm) {
        //１．変数.UILists[前月末店舗数設定]をインスタンス化します。
		UILists eTab = new UILists();
        //２．変数.List[[当前店舗数]]をインスタンス化します。
        List listData = new ArrayList(0);
        //３．変数.配列[対象年月]をインスタンス化します。
        String[] taishoYms = {"",""};
        try {
	        //４．処理３の[[対象年月]][0]へパラメータ.対象年月の前月の年月を設定します。
	        taishoYms[0] = DateManager.getPrevMonth(taishoYm, 1);
	        //５．処理３の[[対象年月]][1]へ処理３の[[対象年月]][0]の前年の年月を設定します。
	        taishoYms[1] = DateManager.getPrevMonth(taishoYms[0], 12);
        }
        catch (Exception ex) {
    		throw new FtlSystemException("売上速報レポート用店舗数情報取得処理"
    				, "DateManager.getPrevMonthメソッド処理で例外が発生しました。"
    				, ex);
        }
        //６．処理３の[[対象年月]]のサイズ分下記の処理を行います。
        for(int i=0; i<taishoYms.length; i++) {
	        //Tab1－1．Dao【売上速報レポート用店舗数】.検索を実行し、
	        //          結果TrnUsrMiseCnt[対象店舗数]を取得する。
        	UIMiseCnt eMiseCnt = getUrisokuRegistUIMiseCntDao().select(taishoYms[i]);
        	//Tab1－2．処理Tab1-1の検索結果がnullの場合、TrnUsrMiseCnt[店舗数]へ下記の値を設定します。
	        if(eMiseCnt == null) {
	        	//1).処理Tab1－1のTrnUsrMiseCnt[対象店舗数]を新しくインスタンス化します。
	        	eMiseCnt = new UIMiseCnt();
	        	//2).TrnUsrMiseCnt[対象店舗数].売上速報対象年月へ処理３の[[対象年月]][現行インデックス]を設定します。
	        	eMiseCnt.setTaishoYm(taishoYms[i]);
	        	//3).TrnUsrMiseCnt[対象店舗数].店舗数へ0(ゼロ)を設定します。
	        	eMiseCnt.setMiseCntAll(new BigDecimal("0"));
	        }
	        //Tab1－3．変数.List[[当前店舗数]]へTrnUsrMiseCnt[店舗数]を格納します。
	        listData.add(i, eMiseCnt);
        }
        //７．処理１のUILists[前月末店舗数設定].キーへ
        //    売上速報レポート用店舗数画面VIEW_IDを設定します。
        eTab.setKey(UrisokuRegistUtil.VIEW_ID_EDIT_MISECNT);
        //８．処理１のUILists[前月末店舗数設定].キー名称へ
        //    タブ名称となる”前月末店舗数設定”を設定します。
        eTab.setKeyName("前月末店舗数設定");
        //９．処理１のUILists[前月末店舗数設定].[[データリスト]]へ
        //    処理２のList[[当前店舗数]]を設定します。
        eTab.setListData(listData);
        //１０．処理１のUILists[前月末店舗数設定]をリターンします。
		return eTab;
	}
	/**
	 * TVCM放映日情報取得処理
	 * 
	 * TVCM放映日の情報をDBから取得します。
	 * @param taishoYm
	 * @return
	 */
	private UILists getRegistDataTvcm(String taishoYm) {
        //１．変数.UILists[TVCM放映日設定]をインスタンス化します。
		UILists eTab = new UILists();
        //２．変数.List[[TVCM放映日]]をインスタンス化します。
        List listData = new ArrayList(0);
        //３．変数.String配列[[対象年月]]をインスタンス化します。
        String[] taishoYms = {"",""};
        try {
	        //４．処理３の[[対象年月]][0]へパラメータ.対象年月を設定します。
        	taishoYms[0] = taishoYm;
	        //５．処理３の[[対象年月]][1]へ処理３の[[対象年月]][0]の前年の年月を設定します。
        	taishoYms[1] = DateManager.getPrevMonth(taishoYms[0], 12);
        }
        catch (Exception ex) {
    		throw new FtlSystemException("TVCM放映日情報取得処理"
    				, "対象年月1日目["+taishoYms[0]+"]から["+12+"]ヶ月を引く際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
    				, ex);
        }
        //６．処理３の[[対象年月]]のサイズ分下記の処理を行います。
        for(int i=0; i<taishoYms.length; i++) {
	        //Tab2-1．Dao【TVCM放映日】.検索を実行し、処理３の[[対象年月]].[現行インデックス]の年月の結果List[[対象月TVCM放映日]]を取得します。
	        List listRegist = getUrisokuRegistTrnTvcmDao().select(taishoYms[i]);
	        //Tab2-2．変数.月初日を処理３の[[対象年月]].[現行インデックス]の年月の1日のyyyyMMdd値で生成します。
	        String taishoYmStartDay = taishoYms[i]+"01";
	        //Tab2-3．変数.月末日を処理３の[[対象年月]]の月末日DDの値で生成します。
	        String lastDayOfMonth = DateManager.getLastDayOfMonth(taishoYmStartDay);
	        
	        int dayCnt = Integer.valueOf(lastDayOfMonth).intValue();
	        //Tab2-4.for文で変数.月末日分（変数.月初日～月末まで)下記の処理を行います。
	        for(int d=0; d<dayCnt; d++) {
	        	//for-1.変数.現行日を変数.月初日に現行インデックス分日追加した日付の値で作成します。
	        	String tvcmDt = taishoYmStartDay;
	        	try {
	        		tvcmDt = DateManager.getNextDate(taishoYmStartDay, d);
	        	}
	        	catch (Exception ex) {
	        		throw new FtlSystemException("TVCM放映日情報取得処理"
	        				, "対象年月1日目["+taishoYmStartDay+"]から["+d+"]日を引く際のDateManager.getNextDateメソッド処理で例外が発生しました。"
	        				, ex);
	        	}
        		if(listRegist.size() <= d 
        				|| !tvcmDt.equals(((TrnTvcm)listRegist.get(d)).getTvcmDt())) {
        			//for-2.対象日の放映日のデータが存在しない場合は、
        			//新しいentity[放映日]をインスタンス化し、下記の値を設定します。
        			TrnTvcm newTrnTvcm = new TrnTvcm();
        			//新しいentity[放映日].放映日 = 現行日
           			newTrnTvcm.setTvcmDt(tvcmDt);
           			//新しいentity[放映日].選択フラグ = false
           			newTrnTvcm.setSelectFlg(false);
           			//for-3.List[[対象月TVCM放映日]]へfor文現行インデックスの位置に新しいentity[放映日]を追加します。
           		 	listRegist.add(d, newTrnTvcm);
        		}
	        	
	        }
	        int firstWeekOfMonth = Calendar.SATURDAY;
	        int lastWeekOfMonth = Calendar.SUNDAY;
	        try {
	        	//Tab2-5.変数.月初日の曜日値を取得します。
		        firstWeekOfMonth = DateManager.getWeek(taishoYmStartDay);
	        	//Tab2-6.変数.月末日の曜日値を取得します。
		        lastWeekOfMonth = DateManager.getWeek(taishoYms[i]+lastDayOfMonth);
	    	}
	    	catch (Exception ex) {
	    		throw new FtlSystemException("TVCM放映日情報取得処理"
	    				, "DateManager.getWeekメソッド処理で例外が発生しました。"
	    				, ex);
	    	}
	        //Tab2-7.処理３の[[対象年月]]の1日が日曜日以外の場合、
	        //日曜日から処理３の[[対象年月]]1日の前日の曜日までの
	        //空のエンティティを追加します。
	        if(Calendar.SUNDAY != firstWeekOfMonth) {
		        for(int w=0; w<UrisokuRegistUtil.WEEKS.length; w++) {
		        	if(UrisokuRegistUtil.WEEKS[w] == firstWeekOfMonth) {
		        		break;
		        	}
        			TrnTvcm newTrnTvcm = new TrnTvcm();
           			newTrnTvcm.setSelectFlg(false);
           		 	listRegist.add(w,newTrnTvcm);
		        }
	        }
	        //Tab2-8.処理３の[[対象年月]]の1日が土曜日以外の場合、
	        //処理３の[[対象年月]]1日の翌日の曜日から土曜日までの
	        //空のエンティティを追加します。
	        if(Calendar.SATURDAY != lastWeekOfMonth) {
	        	boolean weekPassed = false;
	        	for(int w=0; w<UrisokuRegistUtil.WEEKS.length; w++) {
		        	if(UrisokuRegistUtil.WEEKS[w] == lastWeekOfMonth) {
		        		weekPassed = true;
		        		continue;
		        	}
		        	if(weekPassed){
	        			TrnTvcm newTrnTvcm = new TrnTvcm();
	           			newTrnTvcm.setSelectFlg(false);
	           		 	listRegist.add(newTrnTvcm);
		        	}
		        }
	        }
	        //Tab2-9.List[[画面表示用データ保持]]の組み立てをします。
	        List listTaishoTvcm = new ArrayList(0);
	        for(int d=0; d<listRegist.size(); d++) {
	        	int week = 0;
	        	List listWeek = new ArrayList(0);
	        	while(week<7) {
	        		if(week>0) {
	        			d++;
	        		}
	        		listWeek.add(week, listRegist.get(d));
	        		week++;
	        	}
	        	listTaishoTvcm.add(listWeek);
	        }
	        //Tab2-10．UILists[対象年月タブ情報]をインスタンス化します。
	        UILists eTvcm = new UILists();
	        eTvcm.setKey(taishoYms[i]);
	        eTvcm.setKeyName(taishoYms[i]);
	        eTvcm.setListData(listTaishoTvcm);
	        
        	//Tab2-11．処理２のList[[TVCM放映日]]へ処理Tab2-10のUILists[対象年月タブ情報]を格納します。
	        listData.add(i, eTvcm);
        }
        //７．処理１のUILists[テレビCM放映日設定].キーへ
        //    テレビCM放映日設定画面VIEW_IDを設定します。
        eTab.setKey(UrisokuRegistUtil.VIEW_ID_EDIT_TVCM);
        //８．処理１のUILists[テレビCM放映日設定].キー名称へ
        //    タブ名称となる”テレビCM放映日設定”を設定します。
        eTab.setKeyName("テレビCM放映日設定");
        //９．処理１のUILists[テレビCM放映日設定].[[データリスト]]へ
        //    処理２のList[[TVCM放映日]]を設定します。
        eTab.setListData(listData);
        //１０．処理１のUILists[前月末店舗数設定]をリターンします。
		return eTab;
	}
	/**
	 * 商品メニュー情報取得処理
	 * 
	 * 商品メニューの情報をDBから取得します。
	 * 
	 * @param taishoYm
	 * @return
	 */
	private UILists getRegistDataShohinMenu(String taishoYm) {
        //１．変数.UILists[商品メニュー設定]をインスタンス化します。
		UILists eTab = new UILists();
        //２．変数.List[[商品グループ情報]]をインスタンス化します。
        List listData = new ArrayList(0);
        //３．画面内共通【staticクラス】.FRAME_KBN_TABLEのサイズ分下記の処理を行います。
        for(int i=0; i<UrisokuRegistUtil.FRAME_KBN_TABLE.length; i++) {
	        //for-1．LOGIC【売上速報レポート用商品メニューの取得】を実行し、
	        //          結果List[[商品メニュー情報]]を取得する。
	        List listRegist  = getUrisokuRegistGetShohinMenuLogic().execute(taishoYm, UrisokuRegistUtil.FRAME_KBN_TABLE[i][0]);
	        //for-2．UILists[タブFRAME]をインスタンス化します。
	        UILists eFrame = new UILists();
	        eFrame.setKey(UrisokuRegistUtil.FRAME_KBN_TABLE[i][0]);
	        eFrame.setKeyName(UrisokuRegistUtil.FRAME_KBN_TABLE[i][1]);
	        eFrame.setListData(listRegist);
	        
        	//for-3．変数.List[[商品グループ情報]]へUILists[タブFRAME]を格納します。
	        listData.add(eFrame);
        }
        //４．処理１のUILists[商品メニュー設定].キーへ
        //    売上速報商品メニュー設定画面VIEW_IDを設定します。
        eTab.setKey(UrisokuRegistUtil.VIEW_ID_EDIT_MENU);
        //５．処理１のUILists[商品メニュー設定].キー名称へ
        //    タブ名称となる”商品メニュー設定”を設定します。
        eTab.setKeyName("商品メニュー設定");
        //６．処理１のUILists[商品メニュー設定].[[データリスト]]へ
        //    処理２のList[[商品グループ情報]]を設定します。
        eTab.setListData(listData);
        //７．UILists[商品メニュー設定]をリターンします。
		return eTab;
	}
	/**
	 * キャンペーン情報取得処理
	 * 
	 * キャンペーンの情報をDBから取得します。
	 * @param taishoYm
	 * @return
	 */
	private UILists getRegistDataCamp(String taishoYm) {
        //１．変数.UILists[キャンペーン設定]をインスタンス化します。
		UILists eTab = new UILists();
        //２．変数.List[[キャンペーン情報]]をインスタンス化します。
        List listData = new ArrayList(0);
        //３．DAO【売上速報レポート用キャンペーン】.検索を実行します。
        listData = getUrisokuRegistUIUsrCampDao().select(CommonUtil.COMPANY_CD_MOS, taishoYm);
        //４．処理１のUILists[商品メニュー設定].キーへ
        //    売上速報キャンペーン設定画面VIEW_IDを設定します。
        eTab.setKey(UrisokuRegistUtil.VIEW_ID_EDIT_CAMP);
        eTab.setKeyName("キャンペーン設定");
        eTab.setListData(listData);
        //５．UILists[キャンペーン設定]をリターンします。
		return eTab;
	}

    /**
     * 事前条件処理
     * @param taishoYm
     * @throws MissingDataException
     */
    private void validate(final String taishoYm) {
        if(CommonUtil.isNull(taishoYm)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("対象レポート年月");
        }
    }

	/**
	 * 売上速報レポート用商品メニューの取得ロジック取得処理
	 * 
	 * @return urisokuGetShohinMenuLogic を戻します。
	 */
	public GetShohinMenuLogic getUrisokuRegistGetShohinMenuLogic() {
		return urisokuRegistGetShohinMenuLogic;
	}
	/**
	 *  売上速報レポート用商品メニューの取得ロジック設定処理
	 *  
	 * @param logic urisokuGetShohinMenuLogicへ設定します。
	 */
	public void setUrisokuRegistGetShohinMenuLogic(
			GetShohinMenuLogic logic) {
		this.urisokuRegistGetShohinMenuLogic = logic;
	}
	/**
	 * @return urisokuRegistTrnTvcmDao を戻します。
	 */
	public TrnTvcmDao getUrisokuRegistTrnTvcmDao() {
		return urisokuRegistTrnTvcmDao;
	}
	/**
	 * @param dao urisokuRegistTrnTvcmDaoへ設定します。
	 */
	public void setUrisokuRegistTrnTvcmDao(TrnTvcmDao dao) {
		this.urisokuRegistTrnTvcmDao = dao;
	}
	/**
	 * @return urisokuRegistUIUsrCampDao を戻します。
	 */
	public UIUsrCampDao getUrisokuRegistUIUsrCampDao() {
		return urisokuRegistUIUsrCampDao;
	}
	/**
	 * @param dao urisokuRegistUIUsrCampDaoへ設定します。
	 */
	public void setUrisokuRegistUIUsrCampDao(
			UIUsrCampDao dao) {
		this.urisokuRegistUIUsrCampDao = dao;
	}
	/**
	 * @return urisokuRegistUIMiseCntDao を戻します。
	 */
	public UIMiseCntDao getUrisokuRegistUIMiseCntDao() {
		return urisokuRegistUIMiseCntDao;
	}
	/**
	 * @param dao urisokuRegistTrnUsrMiseCntDaoへ設定します。
	 */
	public void setUrisokuRegistUIMiseCntDao(
			UIMiseCntDao dao) {
		this.urisokuRegistUIMiseCntDao = dao;
	}

}
