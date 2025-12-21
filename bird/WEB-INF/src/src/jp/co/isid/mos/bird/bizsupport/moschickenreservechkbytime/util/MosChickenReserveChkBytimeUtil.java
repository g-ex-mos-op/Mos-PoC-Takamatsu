package jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.dto.MosChickenReserveChkBytimeDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIMiseInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveSumInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveTimeInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UIReserveTotalInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.entity.UITitleInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.GetMiseInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservechkbytime.logic.GetTitleInfoLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 時間帯別予約確認表
 * static 処理Utilクラス
 * 
 * @author xlee
 *
 */
public class MosChickenReserveChkBytimeUtil {
    
    /** ユーザータイプ：本部 */
    public static final String USER_TYPE_HONBU = "01";

    /** ユーザータイプ：オーナー */
    public static final String USER_TYPE_ONER = "02";
    
    /** ユーザータイプ：店舗 */
    public static final String USER_TYPE_TENPO = "03";
    
    /** 時間帯：条件15分単位 */
    public static final String [] TIME_ZONE15 = {"00","15","30","45"};
    
    /** ユーザータイプ：店舗 */
    public static final String [] TIME_ZONE30 = {"00","30"};
    
    /** ユーザータイプ：店舗 */
    public static final String [] TIME_ZONE60 = {"00"};
    
    /** ユーザータイプ：店舗 */
    public static final String KBN_SUBTOTAL = "SUBTOTAL";
    
    /** ユーザータイプ：店舗 */
    public static final String KBN_TOTAL = "TOTAL";
    
    /** ユーザータイプ：店舗 */
    public static final String KBN_MEISAI = "MEISAI";
    
    /** ユーザータイプ：店舗 */
    public static final String KBN_TITLE = "TITLE";
    
    /** ユーザータイプ：店舗 */
    public static final String KBN_SUBTITLE = "SUBTITLE";
    
    /** 時間帯：条件15分単位 */
    public static final String KBN_TIME = "TIME";
    
    /** 時間帯：条件30分単位 */
    public static final String KBN_AMT = "AMT";

    /** 時間帯：条件60分単位 */
    public static final int TIMEZN_SIZE = 12;
    
    /** ページ区分 */
    public static final String MAP_NM = "MAP_PAGE";
    
    /** Fontサイズ：14 */
    public static final String FONT_SZ14 = "14";
    
    /** Fontサイズ：12 */
    public static final String FONT_SZ12 = "12";
    
    /** Fontサイズ：11 */
    public static final String FONT_SZ11 = "11";
    
    /** Fontサイズ：10*/
    public static final String FONT_SZ10 = "10";
    
    /** PDF出力時の最大行数 */
    public static final int PDF_MAX_LINE = 34;
    
    public static final String PDF_LAST_TIME = "2300";
    
    /**  */
    public static final String PDF_SPACE_SUBTOT = "S";
    
    /**  */
    public static final String PDF_SPACE_TOT = "T";
    
    /** DateFormatter */
    private static DateFormatter formatter = new DateFormatter();
    
    
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
     * @param getMosChickenByTimeTitleInfoLogic　タイトル情報
     * @param getMosChickenByTimeMiseInfoLogic　店舗情報
     */
    public static void setConditionExecute(
    		MosChickenReserveChkBytimeDto mosChickenReserveChkBytimeDto,
    		GetTitleInfoLogic getMosChickenByTimeTitleInfoLogic,
    		GetMiseInfoLogic getMosChickenByTimeMiseInfoLogic) {

    	//システム日付
    	String sysDate = mosChickenReserveChkBytimeDto.getSysDate();
    	//本部ユーザの場合、オーナーコード
    	String onerCd = mosChickenReserveChkBytimeDto.getCondOnerCd();
    	//オーナーユーザ、店舗ユーザの場合、ユーザID
    	String userId = mosChickenReserveChkBytimeDto.getUserId();
    	//会社コード
    	String companyCd = mosChickenReserveChkBytimeDto.getCondCompanyCd();

		//タイトル取得
    	List titleInfoList = getMosChickenByTimeTitleInfoLogic.execute(sysDate);
    	//タイトル情報がない場合、エラー表示
    	if (titleInfoList == null || titleInfoList.size() == 0) {
    		mosChickenReserveChkBytimeDto.setErrFlg(true);
    	} else {
	    	//店舗リスト
	    	List taishoTenpoList = new ArrayList();
	    	//対象店舗
			taishoTenpoList = getMosChickenByTimeMiseInfoLogic.execute(onerCd, userId, sysDate, mosChickenReserveChkBytimeDto.getUserTypeCd(), companyCd); //対象店舗
			
	    	int i = 0;
	    	for (Iterator ite = titleInfoList.iterator(); ite.hasNext();) {
	    		UITitleInfo uiTitleInfo = (UITitleInfo) ite.next();
	    		if(i == 0) {
	    			//対象期間
	    			mosChickenReserveChkBytimeDto.setCondListTaishoDt(makeTaishoKikanList(uiTitleInfo.getTargetFrom(), uiTitleInfo.getTargetTo()));
	    			mosChickenReserveChkBytimeDto.setCondTaishoDt(sysDate); //対象期間初期値：システム日付
	    		}
	    		break;
	    	}
	        List listTimeUnit = new ArrayList();
	        listTimeUnit.add(new SelectItem("15", "15分単位"));
	        listTimeUnit.add(new SelectItem("30", "30分単位"));
	        listTimeUnit.add(new SelectItem("60", "1時間単位"));
	    	
	    	mosChickenReserveChkBytimeDto.setCondListTitle(titleInfoList);
	    	mosChickenReserveChkBytimeDto.setCondListTenpo(taishoTenpoList);
	    	mosChickenReserveChkBytimeDto.setCondListTimeUnit(listTimeUnit);
	    	mosChickenReserveChkBytimeDto.setCondTimeUnitCd("30"); //初期値
	    	mosChickenReserveChkBytimeDto.setCondTenpoCd(null);
    	}
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
                day = DateManager.getPrevDate(targetTo,i);
            }catch ( Exception ex ) {
                throw new FtlSystemException("時間帯別予約状況確認表");
            }
            SelectItem item = new SelectItem(day, formatter.format
                	(day, DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD));
                dayList.add( item );
            if (day.equals(targetFrom)) {
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
    		MosChickenReserveChkBytimeDto mosChickenReserveChkBytimeDto,
    		GetMiseInfoLogic getMiseInfoLogic,
    		String sysDate) {
    	
		String changeTitleCd = mosChickenReserveChkBytimeDto.getCondTitleCd();
		String targetFrom = "";
		String targetTo = "";
		//検索条件選択初期化する
		mosChickenReserveChkBytimeDto.serchCondClear();
    	//店舗リスト
		List taishoTenpoList = mosChickenReserveChkBytimeDto.getCondListTenpo();
    	
		//セッションからデータを取得する
		List listTitle = mosChickenReserveChkBytimeDto.getCondListTitle();
		for (int i = 0 ; i < listTitle.size(); i++) {
			UITitleInfo uiTitleInfo = (UITitleInfo) listTitle.get(i);
			if(uiTitleInfo.getCkanriNo().equals(changeTitleCd)) {
				targetFrom = uiTitleInfo.getTargetFrom();
				targetTo = uiTitleInfo.getTargetTo();
				break;
			}
		}
		List tmpTaishoKikan = makeTaishoKikanList(targetFrom, targetTo);
		mosChickenReserveChkBytimeDto.setCondTitleCd(changeTitleCd); //タイトル管理番号
		mosChickenReserveChkBytimeDto.setCondListTenpo(taishoTenpoList); //対象店舗
		mosChickenReserveChkBytimeDto.setCondListTaishoDt(tmpTaishoKikan); //対象期間
		mosChickenReserveChkBytimeDto.setCondTaishoDt(sysDate); //対象期間FROM初期値
		mosChickenReserveChkBytimeDto.setCondTimeUnitCd("30"); //初期値
    }
    
    /**
     * 日付フォーマット処理(yyyy年MM月(yyyy年MM月DD日(曜日))
     * @param  date        変換前の日付文字列(yyyymmdd)
     * @return formatDate　変換後の日付文字列(yyyy年MM月DD日(曜日))
     */
    public static List makeTimeZnList(String startTimeHH, String endTimeHH, String timeUnitCd){
        List resDtList = new ArrayList();
    	int gapHH = Integer.parseInt(endTimeHH) - Integer.parseInt(startTimeHH) + 1;
    	
    	String tmpTimeZone [] = null;

    	if(timeUnitCd.equals("15")) {
    		tmpTimeZone = TIME_ZONE15;
    	} else if(timeUnitCd.equals("30")) {
    		tmpTimeZone = TIME_ZONE30;
    	} else if(timeUnitCd.equals("60")) {
    		tmpTimeZone = TIME_ZONE60;
    	}
    	
    	//CSVやPDFに表示する時間を単位別に設定
    	int tmpHH = Integer.parseInt(startTimeHH);
    	
    	for(int i = 0; i < gapHH; i++) {
    		
    		for(int j = 0; j < tmpTimeZone.length; j++) {
    			resDtList.add(((tmpHH < 10) ? "0" + tmpHH : String.valueOf(tmpHH)) + tmpTimeZone[j]);
    		}
    		tmpHH++;
    	}
    	return resDtList;
    }
    
    /**
     *　検索条件が一つしかない場合、値を設定する 
     * @param dto
     * @return MosChickenReserveChkBytimeDto
     */
    public static MosChickenReserveChkBytimeDto chkParamValue(MosChickenReserveChkBytimeDto dto){
        //タイトル管理番号,店コード,お渡し日
    	String ckanriNo = dto.getCondTitleCd();
        String miseCd = dto.getCondTenpoCd();
        String reservDt = dto.getCondTaishoDt();
        
        if(isNull(ckanriNo)) {
        	List tmpTitleList = dto.getCondListTitle();
        	UITitleInfo uiTitleInfo = (UITitleInfo)tmpTitleList.get(0);
        	ckanriNo = uiTitleInfo.getCkanriNo();
        	dto.setCondTitleCd(ckanriNo);
        }
        if(isNull(reservDt)) {
        	List tmpTenpoList = dto.getCondListTaishoDt();
        	reservDt = (String)((SelectItem)tmpTenpoList.get(0)).getValue();
        	dto.setCondTaishoDt(reservDt);
        }
        if(isNull(miseCd)) {
        	List tmpTenpoList = dto.getCondListTenpo();
        	UIMiseInfo uiMiseInfo = (UIMiseInfo)tmpTenpoList.get(0);
        	miseCd = uiMiseInfo.getMiseCd();
        	dto.setCondTenpoCd(miseCd);
        }
        
    	return dto;
    }

    /**
     * 日付フォーマット処理(yyyy年MM月(yyyy年MM月DD日(曜日))
     * @param  date        変換前の日付文字列(yyyymmdd)
     * @return formatDate　変換後の日付文字列(yyyy年MM月DD日(曜日))
     */
    public static String formatYMDKanji(String date){
        
        DateFormatter daFom = new DateFormatter();
        
        String formatDate = "";
        
        try {
            formatDate = 
                daFom.format(
                    date,
                    DateFormatter.PATTERN_KANJI_YMD,
                    DateFormatter.DATE_TYPE_YMD);
            
        } catch (Exception e) {
            throw new FtlSystemException("日付フォーマット処理");
        }
        return formatDate;
    }
    
    /**
     * 日付フォーマット処理(yyyy年MM月(yyyy年MM月DD日(曜日))
     * @param  date        変換前の日付文字列(yyyymmdd)
     * @return formatDate　変換後の日付文字列(yyyy年MM月DD日(曜日))
     */
    public static String formatDayWeek(String date){        
        DateFormatter daFom = new DateFormatter();        
        String formatDate = "";        
        try {
            formatDate = 
                daFom.format(
                    date, 
                    DateFormatter.PATTERN_DAY_OF_WEEK,
                    DateFormatter.DATE_TYPE_YMD);
            
        } catch (Exception e) {
            throw new FtlSystemException("日付フォーマット処理");
        }
        return formatDate;
    }

    /**
     * 日付フォーマット処理(yyyy/MM/dd)
     * @param  date        変換前の日付文字列(yyyymm)
     * @return formatDate　変換後の日付文字列(yyyy/MM/dd)
     */
    public static String formatYMD(String date){
        
        DateFormatter daFom = new DateFormatter();
        
        String formatDate = "";
        
        try {
            formatDate = 
                daFom.format(
                    date, 
                    DateFormatter.PATTERN_SLASH,
                    DateFormatter.DATE_TYPE_YMD);
            
        } catch (Exception e) {
            throw new FtlSystemException("日付フォーマット処理");
        }
        return formatDate;
    }
    
    /**
     * 日付フォーマット処理(yyyy/MM/dd)
     * @param  date        変換前の日付文字列(yyyymm)
     * @return formatDate　変換後の日付文字列(yyyy/MM/dd)
     */
    public static String formatCurrentTime(){
        
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'/'MM'/'dd HH':'mm", Locale.JAPAN);
        String ret = "";
        synchronized (sdf) {
            sdf.setLenient(true);
            ret = sdf.format(currentTime);
        }
        return ret;
    }
    
    /**
     * 検索結果の設定処理
     * 
     * @param reserveList List
     * @param reserveSumList List
     * @param timeUnit String
     * @return totalList List
     */
    public static List makeCsvInfoList(List reserveList, List reserveTimeList, List reserveSumList, MosChickenReserveChkBytimeDto dto) {
    	
    	//最初の販売時間と最後の販売時間を取得：
    	UIReserveTimeInfo uiReserveTimeInfo = (UIReserveTimeInfo)reserveTimeList.get(0);
    	String resStartTime = uiReserveTimeInfo.getReserveStateTime();
    	String resEndTime = uiReserveTimeInfo.getReserveEndTime();
    	//最初の予約時間の時
    	String targetStartTimeHH = resStartTime.substring(0,2);
    	//最後の予約時間の時
    	String targetEndTimeHH = resEndTime.substring(0,2);
    	
    	//時間単位別にセットした時間リスト
    	List resDtList = makeTimeZnList(targetStartTimeHH, targetEndTimeHH, dto.getCondTimeUnitCd());//new ArrayList();
    	
    	//総合計/合計のリストセット
    	List totalSumList = new ArrayList();
    	List subTotalSumList = new ArrayList();
    	
    	for(int i = 0 ; i < reserveSumList.size(); i++) {
    		
    		UIReserveSumInfo uiReserveSumInfo = (UIReserveSumInfo)reserveSumList.get(i);
    		
    		if(uiReserveSumInfo.getSumGroup() == null || uiReserveSumInfo.getSumGroup().equals("")) {
    			totalSumList.add(uiReserveSumInfo);
    		} else {
    			subTotalSumList.add(uiReserveSumInfo);
    		}
    	}
    	
    	//新しい予約情報を設定する
    	Map newReserveMap = new HashMap();
    	//リタンーする配列
    	List newReserveList = new ArrayList();
    	//以前、商品グループ
    	String preShohinGroupCd = "";
    	//以前、集計グループ
    	String preSumGroup = "";
    	//以前、メニュー
    	String preMenuCd = "";
    	
    	//ヘッダを格納
    	UIReserveTotalInfo uiReserveTotalInfo = new UIReserveTotalInfo();
    	newReserveMap = getHeaderInfo(uiReserveTotalInfo, dto, resDtList, "");
    	newReserveList.add(newReserveMap);
    	
		//時間単位別リスト
		List tmpTimeZn = null;
		//予約数
		List tmpReserveAmt = null;
		
		//メニューグループ名と各メニューの予約詳細情報
    	for(int i = 0; i < reserveList.size(); i++) {
    		UIReserveInfo uiReserveInfo = (UIReserveInfo) reserveList.get(i);
    		
    		if(preShohinGroupCd.equals("")) {
    			newReserveMap = new HashMap();
    			//商品グループ
    			uiReserveTotalInfo = new UIReserveTotalInfo();
    			uiReserveTotalInfo.setShohinGroupCd(uiReserveInfo.getMenuGroup());
    			uiReserveTotalInfo.setShohinGroupNm(uiReserveInfo.getMenuGroupNm());
    			newReserveMap.put(KBN_SUBTITLE, uiReserveTotalInfo);
    			newReserveList.add(newReserveMap);
    			
    		} else if(!(preShohinGroupCd.equals("") || preShohinGroupCd.equals(uiReserveInfo.getMenuGroup()))) {
    			//最後の明細・合計・総合系を設定する
    			setCsvLastLine(uiReserveTotalInfo, newReserveMap, newReserveList, tmpTimeZn, tmpReserveAmt,
				resDtList, subTotalSumList, totalSumList, dto.getCondTimeUnitCd(), preShohinGroupCd, preSumGroup);
    			
    			//新商品グループ名
				newReserveMap = new HashMap();
    			uiReserveTotalInfo = new UIReserveTotalInfo();
				uiReserveTotalInfo.setShohinGroupCd(uiReserveInfo.getMenuGroup());
    			uiReserveTotalInfo.setShohinGroupNm(uiReserveInfo.getMenuGroupNm());
    			newReserveMap.put(KBN_SUBTITLE, uiReserveTotalInfo);
    			newReserveList.add(newReserveMap);
    			//メニューコード初期化
    			preMenuCd = "";
    			preSumGroup = "";
    		}
    		if(preMenuCd.equals("")) { //見出し出力
				newReserveMap = new HashMap();
    			uiReserveTotalInfo = new UIReserveTotalInfo();    			
    			uiReserveTotalInfo.setMenuCd("");
    			uiReserveTotalInfo.setMenuNm("");
    			tmpTimeZn = new ArrayList();
    			for(int j = 0 ; j < resDtList.size(); j++) {
    				String reserveDt = (String) resDtList.get(j);
    				tmpTimeZn.add(reserveDt.substring(0,2)+":"+reserveDt.substring(2,4)+"-");
    			}
    			uiReserveTotalInfo.setTimeZn(tmpTimeZn);
    			newReserveMap.put(KBN_MEISAI, uiReserveTotalInfo);
    			newReserveList.add(newReserveMap);
    			//初期化
				newReserveMap = new HashMap();
    			uiReserveTotalInfo = new UIReserveTotalInfo();
    			uiReserveTotalInfo.setMenuCd(uiReserveInfo.getMenuCd());
    			uiReserveTotalInfo.setMenuNm(uiReserveInfo.getMenuNameKj());
    			
    			tmpTimeZn = new ArrayList();
    			tmpReserveAmt = new ArrayList();
    		} else if(!(preMenuCd.equals("") || preMenuCd.equals(uiReserveInfo.getMenuCd()))) {
				List tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, dto.getCondTimeUnitCd());
				uiReserveTotalInfo.setTimeZn((List) tmpTimeAmt.get(0));
				uiReserveTotalInfo.setReserveAmt((List) tmpTimeAmt.get(1));
    			newReserveMap.put(KBN_MEISAI, uiReserveTotalInfo);
    			newReserveList.add(newReserveMap);
    			
				if(!preSumGroup.equals(uiReserveInfo.getSumGroup())) {
					//合計
					newReserveMap = new HashMap();
					uiReserveTotalInfo = new UIReserveTotalInfo();
					uiReserveTotalInfo.setMenuNm("合計(換算数)");
	    			tmpTimeZn = new ArrayList();
	    			tmpReserveAmt = new ArrayList();
	    			
					for(int j = 0; j < subTotalSumList.size(); j++) {
						UIReserveSumInfo subTotSumInfo = (UIReserveSumInfo)subTotalSumList.get(j);
						if(preShohinGroupCd.equals(subTotSumInfo.getMenuGroup()) 
								&& preSumGroup.equals(subTotSumInfo.getSumGroup())) {
							tmpTimeZn.add(subTotSumInfo.getReserveTime());
							tmpReserveAmt.add(subTotSumInfo.getReserveTotal());
						}
					}
					tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, dto.getCondTimeUnitCd());
					uiReserveTotalInfo.setTimeZn((List) tmpTimeAmt.get(0));
					uiReserveTotalInfo.setReserveAmt((List) tmpTimeAmt.get(1));
	    			newReserveMap.put(KBN_SUBTOTAL, uiReserveTotalInfo);
	    			newReserveList.add(newReserveMap);
				}
    			newReserveMap = new HashMap();
    			uiReserveTotalInfo = new UIReserveTotalInfo();
    			tmpTimeZn = new ArrayList();
    			tmpReserveAmt = new ArrayList();
    			uiReserveTotalInfo.setMenuCd(uiReserveInfo.getMenuCd());
    			uiReserveTotalInfo.setMenuNm(uiReserveInfo.getMenuNameKj());
    		}
			tmpTimeZn.add(uiReserveInfo.getReserveHh()+uiReserveInfo.getReserveMm());
			tmpReserveAmt.add(uiReserveInfo.getReserveAmt());
			
			preShohinGroupCd = uiReserveInfo.getMenuGroup();
			preSumGroup = uiReserveInfo.getSumGroup();
    		preMenuCd = uiReserveInfo.getMenuCd();
			
    		//最後
    		if(i == reserveList.size() -1){
    			//最後の明細、合計、総合計情報をセットする
    			setCsvLastLine(uiReserveTotalInfo, newReserveMap, newReserveList, tmpTimeZn, tmpReserveAmt,
    					resDtList, subTotalSumList, totalSumList, dto.getCondTimeUnitCd(), preShohinGroupCd, preSumGroup);
    		}
    	}
    	return newReserveList;
    }
     
	/**
	 * CSV最後の明細、合計、総合計を設定します。
	 * 
	 * @param uiReserveTotalInfo　データ保持用
	 * @param newReserveMap Entity保持用
	 * @param newReserveList Map保持用
	 * @param tmpTimeZn 時間帯
	 * @param tmpReserveAmt 予約数
	 * @param resDtList 時間帯全体
	 * @param subTotalSumList 合計リスト
	 * @param totalSumList 総合計リスト
	 * @param timeUnitCd 時間単位
	 * @param preShohinGroupCd 商品グループ
	 * @param preSumGroup　集計グループ
	 */
    private static void setCsvLastLine(
    		UIReserveTotalInfo uiReserveTotalInfo,  		
    		Map newReserveMap,    		
    		List newReserveList,    	
    		List tmpTimeZn, 
    		List tmpReserveAmt,     	
    		List resDtList,     		
    		List subTotalSumList,    	
    		List totalSumList,    		
    		String timeUnitCd,     		
    		String preShohinGroupCd,
    		String preSumGroup) {
    	//商品グループが変わる前の明細
		List tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, timeUnitCd);
		uiReserveTotalInfo.setTimeZn((List) tmpTimeAmt.get(0));
		uiReserveTotalInfo.setReserveAmt((List) tmpTimeAmt.get(1));
		newReserveMap.put(KBN_MEISAI, uiReserveTotalInfo);
		newReserveList.add(newReserveMap);
		
		//商品グループが変わる前の合計
		newReserveMap = new HashMap();
		uiReserveTotalInfo = new UIReserveTotalInfo();
		uiReserveTotalInfo.setMenuNm("合計(換算数)");
		tmpTimeZn = new ArrayList();
		tmpReserveAmt = new ArrayList();
		
		for(int j = 0; j < subTotalSumList.size(); j++) {
			UIReserveSumInfo subTotSumInfo = (UIReserveSumInfo)subTotalSumList.get(j);
			if(preShohinGroupCd.equals(subTotSumInfo.getMenuGroup()) 
					&& preSumGroup.equals(subTotSumInfo.getSumGroup())) {
				tmpTimeZn.add(subTotSumInfo.getReserveTime());
				tmpReserveAmt.add(subTotSumInfo.getReserveTotal());
			}
		}
		tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, timeUnitCd);
		uiReserveTotalInfo.setTimeZn((List) tmpTimeAmt.get(0));
		uiReserveTotalInfo.setReserveAmt((List) tmpTimeAmt.get(1));
		newReserveMap.put(KBN_SUBTOTAL, uiReserveTotalInfo);
		newReserveList.add(newReserveMap);
		
		//商品グループが変わる前の総合計
		newReserveMap = new HashMap();
		uiReserveTotalInfo = new UIReserveTotalInfo();
		uiReserveTotalInfo.setMenuNm("総合計(換算数)");
		tmpTimeZn = new ArrayList();
		tmpReserveAmt = new ArrayList();
		
		for(int j = 0; j < totalSumList.size(); j++) {
			UIReserveSumInfo totSumInfo = (UIReserveSumInfo)totalSumList.get(j);
			if(preShohinGroupCd.equals(totSumInfo.getMenuGroup())) {
				tmpTimeZn.add(totSumInfo.getReserveTime());
				tmpReserveAmt.add(totSumInfo.getReserveTotal());
			}
		}
		tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, timeUnitCd);
		uiReserveTotalInfo.setTimeZn((List) tmpTimeAmt.get(0));
		uiReserveTotalInfo.setReserveAmt((List) tmpTimeAmt.get(1));
		newReserveMap.put(KBN_TOTAL, uiReserveTotalInfo);
		newReserveList.add(newReserveMap);
    }
    
    /**
     * 時間帯の開始時間、終了時間を取得します。
     * 
     * @param reserveTimeList 予約が存在する時間の最初と最後の時間リスト
     * @return 時間帯が設定されているリスト
     */
    public static String[] getResTime(List reserveTimeList) {
    	String [] resTimeZn = new String [2];
    	
    	//最初の販売時間と最後の販売時間を取得：
    	UIReserveTimeInfo uiReserveTimeInfo = (UIReserveTimeInfo)reserveTimeList.get(0);
    	String resStartTime = uiReserveTimeInfo.getReserveStateTime();
    	String resEndTime = uiReserveTimeInfo.getReserveEndTime();
    	//最初の予約時間の時
    	String targetStartTimeHH = resStartTime.substring(0,2);
    	//最後の予約時間の時
    	String targetEndTimeHH = resEndTime.substring(0,2);
    	
    	resTimeZn[0] = targetStartTimeHH;
    	resTimeZn[1] = targetEndTimeHH;
    	
    	return resTimeZn;
    }

    /**
     * 時間単位に対する時間帯・予約数を設定します。 
     * 
     * @param timeZnList
     * @param amtList
     * @param timeList
     * @param timeUnitCd
     * @return List　設定された時間帯または予約数リスト
     */

    private static List makeResult(List timeZnList, List amtList, List timeList, String timeUnitCd) {
    	
    	List tmpResultList = new ArrayList();
    	List tmpTimeZnList = new ArrayList();
    	List tmpAmtZnList = new ArrayList();
    	
    	for(int i = 0 ; i < timeList.size(); i++) {
    		String timeDt = (String)timeList.get(i);
    		tmpTimeZnList.add(timeDt);
    		
    		if(timeDt.equals("")) {
    			//日付がないなら予約数もなし
    			tmpAmtZnList.add("");
    		} else {
    			BigDecimal amtZn = new BigDecimal(0);
	    		for(int j = 0 ; j < timeZnList.size(); j++) {
	    			String timeZnRDt = (String)timeZnList.get(j);
	    			BigDecimal timeZnRAmt = (BigDecimal) amtList.get(j);
	    			
		    		String tmpDtHH = timeZnRDt.substring(0,2);
		    		String tmpDtMM = timeZnRDt.substring(2,4);
	    			
	    			int tmpDt = Integer.parseInt(tmpDtMM);
		    		
		    		if(timeUnitCd.equals("15")) {
		    			if(tmpDt >= 0 && tmpDt < 15) {
		    				tmpDtMM = "00";
		    			} else if(tmpDt >= 15 && tmpDt < 30) {
		    				tmpDtMM = "15";
		    			} else if(tmpDt >= 30 && tmpDt < 45) {
		    				tmpDtMM = "30";
		    			} else if(tmpDt >= 45 && tmpDt < 60) {
		    				tmpDtMM = "45";
		    			}
		    		} else if(timeUnitCd.equals("30")) {
		    			if(tmpDt >= 0 && tmpDt < 30) {
		    				tmpDtMM = "00";
		    			} else if(tmpDt >= 30 && tmpDt < 60) {
		    				tmpDtMM = "30";
		    			}
		    		} else if(timeUnitCd.equals("60")) {
		    			if(tmpDt >= 0 && tmpDt < 60) {
		    				tmpDtMM = "00";
		    			}
		    		}
		    		timeZnRDt = tmpDtHH + tmpDtMM;
		    		
		    		if(timeDt.equals(timeZnRDt)) {
		    			amtZn = amtZn.add(timeZnRAmt);
		    		}
	    		}
	    		tmpAmtZnList.add(amtZn);
    		}
    	}
    	tmpResultList.add(tmpTimeZnList);
    	tmpResultList.add(tmpAmtZnList);
    	return tmpResultList;
    }
    
	/**
	 * CSV・PDF共通ヘッダ情報を設定します。
	 * @param uiReserveTotalInfo　
	 * @param dto　　
	 * @param resDtList 
	 * @return　設定したヘッダ情報
	 */
    private static Map getHeaderInfo(UIReserveTotalInfo uiReserveTotalInfo, 
    		MosChickenReserveChkBytimeDto dto, List resDtList, String lastTimeZn) {
    	Map newReserveMap = new HashMap();
    	uiReserveTotalInfo.setTenpoCd(dto.getCondTenpoCd());
    	String tenpoNm = "";
    	if(dto.getCondTenpoNm() == null || dto.getCondTenpoNm().equals("")) {
    		tenpoNm = getTenpoNm(dto.getCondTenpoCd(), dto.getCondListTenpo());    		
    	} else {
    		tenpoNm = dto.getCondTenpoNm();
    	}
    	uiReserveTotalInfo.setTenpoNm(tenpoNm);
    	uiReserveTotalInfo.setReserveDt(dto.getCondTaishoDt());
    	uiReserveTotalInfo.setTimeStart((String) resDtList.get(0)); //時間帯Start
    	String endTime = "";
    	if(resDtList.get(resDtList.size()-1).equals("") ||
    			isNull((String) resDtList.get(resDtList.size()-1))) {
    		endTime = lastTimeZn;
    	} else {
    		endTime = (String) resDtList.get(resDtList.size()-1);
    	}
    	uiReserveTotalInfo.setTimeEnd(endTime); //時間帯End
    	uiReserveTotalInfo.setTimeUnit(dto.getCondTimeUnitCd());
    	newReserveMap.put(KBN_TITLE, uiReserveTotalInfo);
    	return newReserveMap;
    }
    
    /**
     * 検索結果の設定処理
     * 
     * @param reserveList List
     * @param reserveSumList List
     * @param timeUnit String
     * @return totalList List
     */
    public static Map makePdfInfoList(List reserveList, List reserveTimeList, List reserveSumList, MosChickenReserveChkBytimeDto dto) {
    	
    	//最初の販売時間と最後の販売時間を取得：
    	String resTime[]  = (String [])getResTime(reserveTimeList);
    	//最初の予約時間の時
    	String targetStartTimeHH = resTime[0];
    	//最後の予約時間の時
    	String targetEndTimeHH = resTime[1];
    	
    	//時間単位別にセットした時間リスト
    	List resDtList = makeTimeZnList(targetStartTimeHH, targetEndTimeHH, dto.getCondTimeUnitCd());//new ArrayList();
    	
    	//総合計/合計のリストセット
    	List totalSumList = new ArrayList();
    	List subTotalSumList = new ArrayList();
    	
    	for(int i = 0 ; i < reserveSumList.size(); i++) {
    		
    		UIReserveSumInfo uiReserveSumInfo = (UIReserveSumInfo)reserveSumList.get(i);
    		
    		if(uiReserveSumInfo.getSumGroup() == null || uiReserveSumInfo.getSumGroup().equals("")) {
    			totalSumList.add(uiReserveSumInfo);
    		} else {
    			subTotalSumList.add(uiReserveSumInfo);
    		}
    	}
    	String lastTimeZn = "";
        //頁数
        int pageCnt = 0;
        boolean addFlg = false;
        
        int divVal = resDtList.size() / MosChickenReserveChkBytimeUtil.TIMEZN_SIZE;
        int modVal = resDtList.size() % MosChickenReserveChkBytimeUtil.TIMEZN_SIZE;
        
        if(divVal == 0) {
        	pageCnt = 1;
        	if(modVal < TIMEZN_SIZE) {
        		addFlg = true;
        	}
        	
        } else if(divVal > 0) {
        	if(modVal == 0) {
        		pageCnt = divVal;
        	} else {
        		pageCnt = divVal + 1;
        		addFlg = true;
        	}
        }
        //最後ページが12時間帯未満又は
        //1ページしかない場合、12時間帯未満
        if(addFlg) {
			lastTimeZn = (String) resDtList.get(resDtList.size()-1);
			String tmpTimeZone [] = null;
		    if(dto.getCondTimeUnitCd().equals("15")) {
	    		tmpTimeZone = TIME_ZONE15;
	    	} else if(dto.getCondTimeUnitCd().equals("30")) {
	    		tmpTimeZone = TIME_ZONE30;
	    	} else if(dto.getCondTimeUnitCd().equals("60")) {
	    		tmpTimeZone = TIME_ZONE60;
	    	}
		    int lastTimeHH = Integer.parseInt(lastTimeZn.substring(0,2));
			//ページの時間帯が12未満
			for(int i = 0; i < (TIMEZN_SIZE - modVal); i++) {
				++lastTimeHH;
	    		if(lastTimeHH >= 24) {
	    			resDtList.add("");
	    		} else {
	    			for(int j = 0; j < tmpTimeZone.length; j++) {
	    				resDtList.add(((lastTimeHH < 10) ? "0" + lastTimeHH : String.valueOf(lastTimeHH)) + tmpTimeZone[j]);
	    			}
	    		}
	    	}
        }
        
        //MAP[LIST[MAP]]
        Map newPdfTotalInfoMap = new HashMap();
        
        for(int p = 0 ; p < pageCnt; p++) {
        	//pg区分
        	int pgI = p * TIMEZN_SIZE;
        	//最後のページサイズ
			int lastPgI = (p+1) * TIMEZN_SIZE;

        	//新しい予約情報を設定する
	        List newReserveList = new ArrayList();
	        //新しい予約情報を設定する
	    	Map newReserveMap = new HashMap();
	    	//新しいリストを保持する
	    	List newPdfTotalInfoList = new ArrayList();
	    	//以前、商品グループ
	    	String preShohinGroupCd = "";
	    	//以前、集計グループ
	    	String preSumGroup = "";
	    	//以前、メニュー
	    	String preMenuCd = "";

	    	//ヘッダを格納
	    	UIReserveTotalInfo uiReserveTotalInfo = new UIReserveTotalInfo();
	    	newReserveMap = getHeaderInfo(uiReserveTotalInfo, dto, resDtList, lastTimeZn);
	    	newReserveList.add(newReserveMap);
	    	
			//時間単位別リスト
			List tmpTimeZn = null;
			//予約数
			List tmpReserveAmt = null;
			
			//メニューグループ名と各メニューの予約詳細情報
	    	for(int i = 0; i < reserveList.size(); i++) {
	    		UIReserveInfo uiReserveInfo = (UIReserveInfo) reserveList.get(i);
	    		
	    		if(preShohinGroupCd.equals("")) {
	    			newReserveMap = new HashMap();
	    			//商品グループ
	    			uiReserveTotalInfo = new UIReserveTotalInfo();
	    			uiReserveTotalInfo.setShohinGroupCd(uiReserveInfo.getMenuGroup());
	    			uiReserveTotalInfo.setShohinGroupNm(uiReserveInfo.getMenuGroupNm());
	    			newReserveMap.put(KBN_SUBTITLE, uiReserveTotalInfo);
	    			newReserveList.add(newReserveMap);
	    			
	    		} else if(!(preShohinGroupCd.equals("") || preShohinGroupCd.equals(uiReserveInfo.getMenuGroup()))) { 
	    			//最後の明細・合計・総合計設定する
	    			setPdfLastLine(uiReserveTotalInfo, newReserveMap, newReserveList, newPdfTotalInfoList, 
	    					tmpTimeZn, tmpReserveAmt, resDtList, subTotalSumList, 
	    					totalSumList, dto.getCondTimeUnitCd(), preShohinGroupCd, 
	    					preSumGroup, pgI, lastPgI);
	    			
	    			//Map保持用のリストクリア
	    			newReserveList = new ArrayList();
	    			//新商品グループ名
					newReserveMap = new HashMap();
	    			uiReserveTotalInfo = new UIReserveTotalInfo();
					uiReserveTotalInfo.setShohinGroupCd(uiReserveInfo.getMenuGroup());
	    			uiReserveTotalInfo.setShohinGroupNm(uiReserveInfo.getMenuGroupNm());
	    			newReserveMap.put(KBN_SUBTITLE, uiReserveTotalInfo);
	    			newReserveList.add(newReserveMap);
	    			//メニューコード初期化
	    			preMenuCd = "";
	    			preSumGroup = "";
	    		}
	    		//見出し出力
	    		if(preMenuCd.equals("")) { 
					newReserveMap = new HashMap();
	    			uiReserveTotalInfo = new UIReserveTotalInfo();    			
	    			uiReserveTotalInfo.setMenuCd("");
	    			uiReserveTotalInfo.setMenuNm("");
	    			tmpTimeZn = new ArrayList();
	    			for(int pg = pgI ; pg < lastPgI ; pg++) {
	    				String reserveDt = (String) resDtList.get(pg);
	    				if(reserveDt.equals("")) {
	    					tmpTimeZn.add("");
	    				} else {
	    					tmpTimeZn.add(reserveDt.substring(0,2)+":"+reserveDt.substring(2,4)+"-");
	    				}
	    			}
	    			uiReserveTotalInfo.setTimeZn(tmpTimeZn);
	    			newReserveMap.put(KBN_MEISAI, uiReserveTotalInfo);
	    			newReserveList.add(newReserveMap);

					newReserveMap = new HashMap();
	    			uiReserveTotalInfo = new UIReserveTotalInfo();
	    			uiReserveTotalInfo.setMenuCd(uiReserveInfo.getMenuCd());
	    			uiReserveTotalInfo.setMenuNm(uiReserveInfo.getMenuNameKj());
	    			
	    			tmpTimeZn = new ArrayList();
	    			tmpReserveAmt = new ArrayList();
	    		} else  if(!(preMenuCd.equals("") || preMenuCd.equals(uiReserveInfo.getMenuCd()))) {
	    			List tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, dto.getCondTimeUnitCd());
	    			//ページ別時間帯・予約数
	    			List newTimeAmt = makeTimeAmt(tmpTimeAmt, pgI, lastPgI);
	    			
					uiReserveTotalInfo.setTimeZn((List) newTimeAmt.get(0));
					uiReserveTotalInfo.setReserveAmt((List) newTimeAmt.get(1));
	    			newReserveMap.put(KBN_MEISAI, uiReserveTotalInfo);
	    			newReserveList.add(newReserveMap);
	    			
					if(!preSumGroup.equals(uiReserveInfo.getSumGroup())) {
						//合計
						newReserveMap = new HashMap();
						uiReserveTotalInfo = new UIReserveTotalInfo();
						uiReserveTotalInfo.setMenuNm("合計(換算数)");
		    			tmpTimeZn = new ArrayList();
		    			tmpReserveAmt = new ArrayList();
		    			
						for(int j = 0; j < subTotalSumList.size(); j++) {
							UIReserveSumInfo subTotSumInfo = (UIReserveSumInfo)subTotalSumList.get(j);
							if(preShohinGroupCd.equals(subTotSumInfo.getMenuGroup()) &&
									preSumGroup.equals(subTotSumInfo.getSumGroup())) {
								tmpTimeZn.add(subTotSumInfo.getReserveTime());
								tmpReserveAmt.add(subTotSumInfo.getReserveTotal());
							}
						}
						tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, dto.getCondTimeUnitCd());
		    			//ページ別時間帯・予約数
		    			newTimeAmt = makeTimeAmt(tmpTimeAmt, pgI, lastPgI);
		    			
						uiReserveTotalInfo.setTimeZn((List) newTimeAmt.get(0));
						uiReserveTotalInfo.setReserveAmt((List) newTimeAmt.get(1));
		    			newReserveMap.put(KBN_SUBTOTAL, uiReserveTotalInfo);
		    			newReserveList.add(newReserveMap);
		    			newReserveList.add(PDF_SPACE_SUBTOT);
					}
	    			newReserveMap = new HashMap();
	    			uiReserveTotalInfo = new UIReserveTotalInfo();
	    			tmpTimeZn = new ArrayList();
	    			tmpReserveAmt = new ArrayList();
	    			uiReserveTotalInfo.setMenuCd(uiReserveInfo.getMenuCd());
	    			uiReserveTotalInfo.setMenuNm(uiReserveInfo.getMenuNameKj());
	    		}
				tmpTimeZn.add(uiReserveInfo.getReserveHh()+uiReserveInfo.getReserveMm());
				tmpReserveAmt.add(uiReserveInfo.getReserveAmt());
				
				//処理した商品グループコード、集計グループ、メニューコードを設定
				preShohinGroupCd = uiReserveInfo.getMenuGroup();
				preSumGroup = uiReserveInfo.getSumGroup();
	    		preMenuCd = uiReserveInfo.getMenuCd();
	    		
	    		//最後
	    		if(i == reserveList.size() -1){
	    			
	    			//最後の明細・合計・総合計設定する
	    			setPdfLastLine(uiReserveTotalInfo, newReserveMap, newReserveList, newPdfTotalInfoList, 
	    					tmpTimeZn, tmpReserveAmt, resDtList, subTotalSumList, totalSumList, 
	    					dto.getCondTimeUnitCd(), preShohinGroupCd, 
	    					preSumGroup, pgI, lastPgI);
	    		}
	    	}
	    	newPdfTotalInfoMap.put(MAP_NM + p , newPdfTotalInfoList);
        }
        return newPdfTotalInfoMap;
    }
    
    
	/**
	 * PDF最後の明細、合計、総合計を設定します。
	 * 
	 * @param uiReserveTotalInfo　データ保持用
	 * @param newReserveMap Entity保持用
	 * @param newReserveList Map保持用
	 * @param newPdfTotalInfoList List保持用
	 * @param tmpTimeZn 時間帯
	 * @param tmpReserveAmt 予約数
	 * @param resDtList 時間帯全体
	 * @param subTotalSumList 合計リスト
	 * @param totalSumList 総合計リスト
	 * @param timeUnitCd 時間単位
	 * @param preShohinGroupCd 商品グループ
	 * @param preSumGroup　集計グループ
	 * @param pgI　
	 * @param lastPgI　
	 */
    private static void setPdfLastLine(
    		UIReserveTotalInfo uiReserveTotalInfo,  		
    		Map newReserveMap,    		
    		List newReserveList, 
    		List newPdfTotalInfoList,
    		List tmpTimeZn, 
    		List tmpReserveAmt,     	
    		List resDtList,
    		List subTotalSumList,    	
    		List totalSumList,    		
    		String timeUnitCd,     		
    		String preShohinGroupCd,
    		String preSumGroup,
    		int pgI,
    		int lastPgI) {
		//商品グループが割らす前の明細
		List tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, timeUnitCd);
		//ページ別時間帯・予約数
		List newTimeAmt = makeTimeAmt(tmpTimeAmt, pgI, lastPgI);
		
		uiReserveTotalInfo.setTimeZn((List) newTimeAmt.get(0));
		uiReserveTotalInfo.setReserveAmt((List) newTimeAmt.get(1));
		newReserveMap.put(KBN_MEISAI, uiReserveTotalInfo);
		newReserveList.add(newReserveMap);
		
		//合計
		newReserveMap = new HashMap();
		uiReserveTotalInfo = new UIReserveTotalInfo();
		uiReserveTotalInfo.setMenuNm("合計(換算数)");
		tmpTimeZn = new ArrayList();
		tmpReserveAmt = new ArrayList();
		
		for(int j = 0; j < subTotalSumList.size(); j++) {
			UIReserveSumInfo subTotSumInfo = (UIReserveSumInfo)subTotalSumList.get(j);
			if(preShohinGroupCd.equals(subTotSumInfo.getMenuGroup()) &&
					preSumGroup.equals(subTotSumInfo.getSumGroup())) {
				tmpTimeZn.add(subTotSumInfo.getReserveTime());
				tmpReserveAmt.add(subTotSumInfo.getReserveTotal());
			}
		}
		tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, timeUnitCd);
		//ページ別時間帯・予約数
		newTimeAmt = makeTimeAmt(tmpTimeAmt, pgI, lastPgI);
		
		uiReserveTotalInfo.setTimeZn((List) newTimeAmt.get(0));
		uiReserveTotalInfo.setReserveAmt((List) newTimeAmt.get(1));
		newReserveMap.put(KBN_SUBTOTAL, uiReserveTotalInfo);
		newReserveList.add(newReserveMap);
		newReserveList.add(PDF_SPACE_SUBTOT);
		
		//総合計
		newReserveMap = new HashMap();
		uiReserveTotalInfo = new UIReserveTotalInfo();
		uiReserveTotalInfo.setMenuNm("総合計(換算数)");
		tmpTimeZn = new ArrayList();
		tmpReserveAmt = new ArrayList();
		
		for(int j = 0; j < totalSumList.size(); j++) {
			UIReserveSumInfo totSumInfo = (UIReserveSumInfo)totalSumList.get(j);
			if(preShohinGroupCd.equals(totSumInfo.getMenuGroup())) {
				tmpTimeZn.add(totSumInfo.getReserveTime());
				tmpReserveAmt.add(totSumInfo.getReserveTotal());
			}
		}
		tmpTimeAmt = makeResult(tmpTimeZn, tmpReserveAmt, resDtList, timeUnitCd);
		//ページ別時間帯・予約数
		newTimeAmt = makeTimeAmt(tmpTimeAmt, pgI, lastPgI);
		
		uiReserveTotalInfo.setTimeZn((List) newTimeAmt.get(0));
		uiReserveTotalInfo.setReserveAmt((List) newTimeAmt.get(1));
		newReserveMap.put(KBN_TOTAL, uiReserveTotalInfo);
		newReserveList.add(newReserveMap);
		newReserveList.add(PDF_SPACE_TOT);
		newReserveList.add(PDF_SPACE_TOT);
		newPdfTotalInfoList.add(newReserveList);
    }
    
	/**
	 * PDF用、ページ別時間帯・予約数リストを設定。
	 * 1ページ毎に【12】時間帯を設定します。
	 * 
	 * @param tmpTimeAmt 時間帯・予約数リスト
	 * @param pgI ページの最初
	 * @param lastPgI　ページの最後
	 * @return　設定されたリスト
	 */
    private static List makeTimeAmt(List tmpTimeAmt, int pgI, int lastPgI) {
    	List retTimeAmt = new ArrayList();
    	
		//時間帯12
		List tmpTime = (List) tmpTimeAmt.get(0);
		List tmpAmt = (List) tmpTimeAmt.get(1);
		
		List newTime = new ArrayList();
		List newAmt = new ArrayList();
		
		if(lastPgI > tmpTime.size()) {
			lastPgI = tmpTime.size();
		}
		for(int pg = pgI ; pg < lastPgI ; pg++) {
			newTime.add(tmpTime.get(pg));
			newAmt.add(tmpAmt.get(pg));
		}
		retTimeAmt.add(newTime);
		retTimeAmt.add(newAmt);
		return retTimeAmt;
    }
    
    /**
     * 検索条件の店舗コードから店舗名を取得します。
     * 
     * @param tmpTenpoCd　店舗コード
     * @param tmpTenpoList 店舗リスト
     * @return 店舗名
     */
    private static String getTenpoNm(String tmpTenpoCd, List tmpTenpoList) {
    	String tenpoNm = "";
    	
    	for(int i = 0 ; i < tmpTenpoList.size(); i++) {
    		UIMiseInfo miseInfo = (UIMiseInfo) tmpTenpoList.get(i);
    		if(miseInfo.getMiseCd().equals(tmpTenpoCd)) {
    			tenpoNm = miseInfo.getMiseNameKj();
    			break;
    		}
    	}
    	return tenpoNm;
    }
}
