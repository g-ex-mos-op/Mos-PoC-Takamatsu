package jp.co.isid.mos.bird.bizsupport.moschickensalestateview.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.dto.MosChickenSaleStateViewDto;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UIMiseInfo;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UISaleStateTotalInfo;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.entity.UITitleInfo;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetShohinGroupInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickensalestateview.logic.GetTitleInfoLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DefaultFormatter;

/**
 * モスチキン管理マスタ登録
 * static 処理保持クラス
 *
 * @author xkinu
 *
 */
public class MosChichenSaleStateUtil {

    /** ユーザータイプ：オーナー */
	public static final String DATE_FROM = "0401";

    /** ユーザータイプ：オーナー */
    public static final String DATE_TO = "0331";

    public static final String PAGE_PROC_BEFWEK = "1";

    public static final String PAGE_PROC_BEFDAY = "2";

    public static final String PAGE_PROC_TODAY = "3";

    public static final String PAGE_PROC_AFTDAY = "4";

    public static final String PAGE_PROC_AFTWEK = "5";

    public static final String PAGE_NOT = "0";

    public static final String PAGE_FIRST = "1";

    public static final String PAGE_LAST = "2";

    public static final String PAGE_TODAY = "3";

    /** ユーザータイプ：本部 */
    public static final String USER_TYPE_HONBU = "01";

    /** ユーザータイプ：オーナー */
    public static final String USER_TYPE_ONER = "02";

    /** ユーザータイプ：店舗 */
    public static final String USER_TYPE_TENPO = "03";

    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();

    private static DefaultFormatter trimFormatter= new DefaultFormatter();

    private MosChichenSaleStateUtil() {
        super();
    }

    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

    /**
     * 検索条件の設定処理
     *
     * @param mosChickenSaleStateViewDto DTO
     * @param getTitleInfoLogic　タイトル情報
     * @param getShohinGroupInfoLogic　商品グループ情報
     * @param getMiseInfoLogic　店舗情報
     * @param sysDate　システム日付
     */
    public static void setConditionExecute(
    		MosChickenSaleStateViewDto mosChickenSaleStateViewDto,
    		GetTitleInfoLogic getTitleInfoLogic,
    		GetShohinGroupInfoLogic getShohinGroupInfoLogic,
    		GetMiseInfoLogic getMiseInfoLogic,
    		String sysDate) {

        	//アプリ日付
        	String appDate = mosChickenSaleStateViewDto.getAppDate();
        	//本部ユーザの場合、オーナーコード
        	String onerCd = mosChickenSaleStateViewDto.getCondOnerCd();
        	//オーナーユーザ、店舗ユーザの場合、ユーザID
        	String userId = mosChickenSaleStateViewDto.getUserId();
        	//会社コード
        	String companyCd = mosChickenSaleStateViewDto.getCondCompanyCd();

        	String frDate = "";
        	String toDate = "";

        	try {
    			frDate = DateManager.getPrevYear(DateManager.getCurrentYear(appDate), 2) + DATE_FROM;
    			toDate = DateManager.getNextYear(appDate.substring(0,4),1) + DATE_TO;
    		} catch (Exception e) {
    			throw new FtlSystemException("モスチキン販売状況一覧のタイトル取得処理");
    		}
    		//タイトル取得
        	List titleInfoList = getTitleInfoLogic.execute(frDate, toDate);
        	//商品グループMAP
        	Map shohinGroupInfoMap = new HashMap();
        	//店舗リスト
        	List taishoTenpoList = new ArrayList();

        	int i = 0;
        	for (Iterator ite = titleInfoList.iterator(); ite.hasNext();) {
        		UITitleInfo uiTitleInfo = (UITitleInfo) ite.next();
        		String ckanriNo = uiTitleInfo.getCkanriNo();
        		//商品グループ
        		List shohinGroupInfoList = getShohinGroupInfoLogic.execute(ckanriNo);
        		shohinGroupInfoMap.put(ckanriNo, shohinGroupInfoList);

        		//対象店舗
        		taishoTenpoList = getMiseInfoLogic.execute(onerCd, userId, mosChickenSaleStateViewDto.getUserTypeCd(), companyCd); //対象店舗

        		if(i == 0) {
        			mosChickenSaleStateViewDto.setCondListShohinGroup(shohinGroupInfoList); //商品グループ
        			//対象期間
        			mosChickenSaleStateViewDto.setCondListTaishoKikan(makeTaishoKikanList(uiTitleInfo.getTargetFrom(), uiTitleInfo.getTargetTo()));
        			mosChickenSaleStateViewDto.setCondTaishoKikanCdFr(uiTitleInfo.getDefaultFrom()); //対象期間FROM初期値
        			mosChickenSaleStateViewDto.setCondTaishoKikanCdTo(uiTitleInfo.getDefaultTo()); //対象期間TO初期値
        		}
        		i++;
        	}
        	List<UIMiseInfo> newTaishoTenpoList = new ArrayList<UIMiseInfo>();
            for (i=0;i<taishoTenpoList.size();i++) {
            	UIMiseInfo miseInfo = (UIMiseInfo) taishoTenpoList.get(i);
            	miseInfo.setMiseNameKj(trimFormatter.trimWideHalfSpace(miseInfo.getMiseNameKj()));
            	//エラー処理：
            	if (MosChichenSaleStateUtil.isNull(sysDate)) {
                    throw new NotNullException("システム日付"); //E0506 システム日付
                } else if (sysDate.compareTo(miseInfo.getCloseDate()) > 0) {
                	miseInfo.setMiseNameKj(miseInfo.getMiseNameKj() + "(CLOSE)");
                }
            	newTaishoTenpoList.add(miseInfo);
    		}
        	mosChickenSaleStateViewDto.setCondListTitle(titleInfoList);
        	mosChickenSaleStateViewDto.setCondMapShohinGroup(shohinGroupInfoMap); //全体保持
        	mosChickenSaleStateViewDto.setCondListTenpo(newTaishoTenpoList);
        	mosChickenSaleStateViewDto.setCondTenpoCd(null);
    }

    /**
     * 対象期間リストの設定処理
     *
     * @param targetFrom　対象期間の開始日
     * @param targetTo　対象期間の終了日
     * @param tmpTaishoKikanList　対象期間リスト
     */
    private static List makeTaishoKikanList(String targetFrom, String targetTo) {
    	//日付リスト
        List dayList = new ArrayList();

        String day = "";
        int i = 0;
        while( true ) {
            try {
            	// 日付取得(YYYYMMDD)
                day = DateManager.getNextDate(targetFrom,i);
            }catch ( Exception ex ) {
                throw new FtlSystemException("モスチキン販売状況一覧");
            }
            SelectItem item = item = new SelectItem(day, formatter.format
                	(day, DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD));
                dayList.add( item );

            if (day.equals(targetTo)) {
                break;
            }

            day = "";
            i++;

        }
        return dayList;
    }

    /**
     * 対象期間リストの設定処理
     *
     * @param targetFrom　対象期間の開始日
     * @param targetTo　対象期間の終了日
     * @param tmpTaishoKikanList　対象期間リスト
     */
    public static List makeDtList(String targetFrom, String targetTo) {
    	//日付リスト
        List dayList = new ArrayList();

        String day = "";
        int i = 0;
        while( true ) {
            try {
            	// 日付取得(YYYYMMDD)
                day = DateManager.getNextDate(targetFrom,i);
            }catch ( Exception ex ) {
                throw new FtlSystemException("モスチキン販売状況一覧");
            }
            dayList.add(day);
            if (day.equals(targetTo)) {
                break;
            }

            day = "";
            i++;

        }
        return dayList;
    }

    /**
     * タイトル切替処理
     *
     * @param mosChickenSaleStateViewDto DTO
     * @param getMiseInfoLogic　店舗情報
     */
    public static void setChangeTitleExecute(
    		MosChickenSaleStateViewDto mosChickenSaleStateViewDto) {
		String changeTitleCd = mosChickenSaleStateViewDto.getCondTitleCd();
		String defaultFrom = "";
		String defaultTo = "";
		String targetFrom = "";
		String targetTo = "";
		//検索条件選択初期化する
		mosChickenSaleStateViewDto.serchCondClear();
    	//店舗リスト
    	List taishoTenpoList = mosChickenSaleStateViewDto.getCondListTenpo();

		//セッションからデータを取得する
		List listTitle = mosChickenSaleStateViewDto.getCondListTitle();
		for (int i = 0 ; i < listTitle.size(); i++) {
			UITitleInfo uiTitleInfo = (UITitleInfo) listTitle.get(i);
			if(uiTitleInfo.getCkanriNo().equals(changeTitleCd)) {
				defaultFrom = uiTitleInfo.getDefaultFrom();
				defaultTo = uiTitleInfo.getDefaultTo();
				targetFrom = uiTitleInfo.getTargetFrom();
				targetTo = uiTitleInfo.getTargetTo();
				break;
			}
		}

		Map shohinGroupInfoMap = mosChickenSaleStateViewDto.getCondMapShohinGroup();
		List shohinGroupInfoList = (List) shohinGroupInfoMap.get(changeTitleCd);

		List tmpTaishoKikan = makeTaishoKikanList(targetFrom, targetTo);

		mosChickenSaleStateViewDto.setCondListShohinGroup(shohinGroupInfoList); //商品グループ
		mosChickenSaleStateViewDto.setCondListTenpo(taishoTenpoList); //対象店舗
		mosChickenSaleStateViewDto.setCondListTaishoKikan(tmpTaishoKikan); //対象期間
		mosChickenSaleStateViewDto.setCondTaishoKikanCdFr(defaultFrom); //対象期間FROM初期値
		mosChickenSaleStateViewDto.setCondTaishoKikanCdTo(defaultTo); //対象期間TO初期値
    }

    /**
     * 検索結果の設定処理
     *
     * @param mosChickenSaleStateViewDto DTO
     * @param searchResultList　検索全体結果リスト
     * @param sysDate　システム日付
     */
    public static void setResult(MosChickenSaleStateViewDto mosChickenSaleStateViewDto,
    		List searchResultList, String sysDate) {

    	List titleList = (List) searchResultList.get(0);

        int limitSize = 0;

        boolean btControlList [] = {false,false,false};
        //最初であるので、
        btControlList[0] = true;
        if(titleList.size() >= 8) {
        	if(titleList.size() == 8) {
        		btControlList[1] = true;
        		btControlList[2] = true;
        	}
        	limitSize = 8;
        } else {
        	//サイズが7日以下であれば、ページボタンは使用不可になる
        	limitSize = titleList.size();
        	btControlList[1] = true;
        	btControlList[2] = true;
        }

        List nTitleList = new ArrayList();

        for(int i = 0; i < limitSize; i++) {
        	UISaleStateTotalInfo uiTotalInfo = (UISaleStateTotalInfo) titleList.get(i);
        	String viewDt = uiTotalInfo.getViewDt();
        	if(i == 1) {
    			mosChickenSaleStateViewDto.setPageFirstDt(viewDt);
        	}
        	nTitleList.add(titleList.get(i));
        }
        //今日のボタン制御
        if(titleList.size()-1 > 7) {
	        if((Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdFr()) <= (Integer.parseInt(sysDate))) &&
	        		(Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdTo()) >= (Integer.parseInt(sysDate)))) {
	        	btControlList[1] = false;
	        } else {
	        	btControlList[1] = true;
	        }
        }
        List sumList = (List) searchResultList.get(1);
        List nSumList = new ArrayList();
        for(int i = 0; i < limitSize; i++) {
        	nSumList.add(sumList.get(i));
        }
        List detailList = (List) searchResultList.get(2);
        List nDetailList = new ArrayList();

        for(int i = 0; i < detailList.size(); i++) {
        	List tmpDetail = (List) detailList.get(i);
        	List nTmpDetail = new ArrayList();
        	//メニューの日付が同じでなく、７日間以下の場合
        	if(tmpDetail.size() < limitSize) {
        		limitSize = tmpDetail.size();
        	}
        	for(int j = 0; j < limitSize; j++) {
        		nTmpDetail.add(tmpDetail.get(j));
        	}
        	nDetailList.add(nTmpDetail);
        }
    	//結果リストを保持する
        mosChickenSaleStateViewDto.setResultTitleList(nTitleList);
        mosChickenSaleStateViewDto.setResultSumList(nSumList);
        mosChickenSaleStateViewDto.setResultDetailList(nDetailList);
        mosChickenSaleStateViewDto.setPageBtControl(btControlList);

    	//全体リスト表示用リストに保持する
        mosChickenSaleStateViewDto.setPageList(searchResultList);
    }

    /**
     * ページ遷移処理
     *
     * @param mosChickenSaleStateViewDto DTO
     * @param baseDate　画面へ表示する際、開始する日付
     * @param kbn　前または後の区分
     */
    public static void setPageList(MosChickenSaleStateViewDto mosChickenSaleStateViewDto,
    		String baseDate, String kbn, String sysDate) {

    	//ページボタンがすべて使用不可の場合はページ処理がない
    	boolean btControlList [] = {false, false, false};
    	if(mosChickenSaleStateViewDto == null && baseDate == null && kbn == null) {
    		btControlList[1] = true;
    	}

        if((Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdFr()) <= (Integer.parseInt(sysDate))) &&
        		(Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdTo()) >= (Integer.parseInt(sysDate)))) {
        	btControlList[1] = false;
        } else {
        	btControlList[1] = true;
        }

    	List allList = mosChickenSaleStateViewDto.getPageList();
    	List titleList = (List) allList.get(0);
    	List nTitleList = new ArrayList();

        List sumList = (List) allList.get(1);
        List nSumList = new ArrayList();

        List detailList = (List) allList.get(2);
        List nDetailList = new ArrayList();

    	int tmpSetKbn =0;
    	int limitSize = 0;

    	//基準になる日付が指定期間の対象期間より大きい場合
    	if(kbn.equals("PREV")) {
    		if(Integer.parseInt(baseDate) < Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdFr())) {
    			baseDate = mosChickenSaleStateViewDto.getCondTaishoKikanCdFr();
    		}
    	} else if(kbn.equals("NEXT")){
    		if(Integer.parseInt(baseDate) > Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdTo())) {
    			baseDate = mosChickenSaleStateViewDto.getCondTaishoKikanCdTo(); //最後の日付になる
    		}
    	} else if(kbn.equals("TODAY")) {
    		String prevToday = "";
    		String nextToday = "";
    		try {
    			prevToday = DateManager.getPrevDate(baseDate,3);
				nextToday = DateManager.getNextDate(baseDate,3);
			} catch (Exception e) {
				throw new FtlSystemException("モスチキン販売状況一覧のページ遷移処理：今日");
			}
			if(Integer.parseInt(prevToday) < Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdFr())) {
				prevToday = mosChickenSaleStateViewDto.getCondTaishoKikanCdFr();
			} else if(Integer.parseInt(nextToday) > Integer.parseInt(mosChickenSaleStateViewDto.getCondTaishoKikanCdTo())) {
    			nextToday = mosChickenSaleStateViewDto.getCondTaishoKikanCdTo();
    		}
			baseDate = prevToday;
			limitSize = Integer.parseInt(nextToday) - Integer.parseInt(prevToday);
    	}

    	if(baseDate.equals(mosChickenSaleStateViewDto.getCondTaishoKikanCdFr())) {
    		btControlList[0] = true;
    	}
		for(int i = 0; i < titleList.size(); i++) {
			UISaleStateTotalInfo uiTotalInfo = (UISaleStateTotalInfo) titleList.get(i);
			String tmpViewDt = uiTotalInfo.getViewDt();

			if(i == 0) {
				//タイトル部分のリストをセットする
				nTitleList.add(titleList.get(i));
			}
			if(tmpViewDt != null && Integer.parseInt(baseDate) == Integer.parseInt(tmpViewDt)) {
				tmpSetKbn = i;
				break;
			}
		}

		limitSize = titleList.size() - tmpSetKbn;

        if(limitSize >= 8) {
        	//次へページがある場合
        	limitSize = tmpSetKbn + 7;
        	btControlList[2] = false;
        } else {
        	//最後のページ
        	//結果が７日を超えるが、最後のページは７日を超えない場合７日まで表示する。
        	limitSize = tmpSetKbn + 7;
        	if(limitSize > titleList.size()) {
        		tmpSetKbn = tmpSetKbn - (limitSize - titleList.size());
        		limitSize = tmpSetKbn + 7;
        	}
        	btControlList[2] = true;
        }

		for(int i = tmpSetKbn; i < limitSize; i++) {
			if(i == tmpSetKbn) {
				//該当頁の最初日付をセットする
				UISaleStateTotalInfo uiTotalInfo = (UISaleStateTotalInfo) titleList.get(i);
				mosChickenSaleStateViewDto.setPageFirstDt(uiTotalInfo.getViewDt());
			}
			nTitleList.add(titleList.get(i));
		}
		//タイトル部分のリストをセットする
		nSumList.add(sumList.get(0));
		for(int i = tmpSetKbn; i < limitSize; i++) {
			nSumList.add(sumList.get(i));
		}
		//タイトル部分のリストをセットする
		for(int i = 0; i < detailList.size(); i++) {
			List tmpDetail = (List) detailList.get(i);
			List nTmpDetail = new ArrayList();
			nTmpDetail.add(tmpDetail.get(0));
			for(int j = tmpSetKbn; j < limitSize; j++) {
				nTmpDetail.add(tmpDetail.get(j));
			}
			nDetailList.add(nTmpDetail);
		}
    	//結果リストを保持する
    	mosChickenSaleStateViewDto.setResultTitleList(nTitleList);
    	mosChickenSaleStateViewDto.setResultSumList(nSumList);
    	mosChickenSaleStateViewDto.setResultDetailList(nDetailList);

    	//全体リスト表示用リストに保持する
    	mosChickenSaleStateViewDto.setPageList(allList);
    	mosChickenSaleStateViewDto.setPageBtControl(btControlList);
    }

    /**
     * 日付に対する曜日を取得する
     * @param yy 年
     * @param mm 月
     * @param dd 日
     * @return String 曜日名
     */
    public static String getDayOFWeek(int yy, int mm, int dd) {

    	String[] dayOfWeek = {"日","月","火","水","木","金","土"};

    	Calendar c1 = new GregorianCalendar();
    	c1.set(yy,mm-1,dd);
    	String day = dayOfWeek[c1.get(Calendar.DAY_OF_WEEK)-1];
    	return day;
    }
}
