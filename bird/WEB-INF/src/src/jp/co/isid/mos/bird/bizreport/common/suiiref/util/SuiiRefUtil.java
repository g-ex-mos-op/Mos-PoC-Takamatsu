/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.suiiref.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.UITabData;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.Formatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 推移表共通定数クラス
 * 
 * 作成日:2013/04/22
 * @author xkinu
 *
 */
public class SuiiRefUtil {
    /* VIEWID：売上推移表結果画面 */
    public static final String VIEW_ID_U = "BBR002V01";
    /* VIEWID：宅配推移表画面 */
    public static final String VIEW_ID_T = "BBR004V01";
    /* VIEWID：宅配推移表店舗用画面 */
    public static final String VIEW_ID_T_MISE = "BBR004V02";
    /* VIEWID：MOS CARD推移表結果画面 */
    public static final String VIEW_ID_M = "BBR016V01";
    /* VIEWID：NET ORDER推移表結果画面 */
    public static final String VIEW_ID_N = "BBR018V01";
    
    public static final String SUII_TYPE_GEPO = "GEPO";
    
    public static NumericFormatter numFmtdgt0 = new NumericFormatter(true, 0, true);
    public static NumericFormatter numFmtdgt2 = new NumericFormatter(true, 2);
    // Formatter
    private static DateFormatter dateFmtYMKj = new DateFormatter(DateFormatter.DATE_TYPE_YM, "yyyy'年'MM'月'");
    private static DateFormatter dateFmtddEKj = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日('E')'");

    /** 年月ラベル*/
    public static final String EIGYO_DT_LABEL_DOGETU = "前年同月";
    public static final String EIGYO_DT_LABEL_12AVG = "12ヶ月平均";
    public static final String EIGYO_DT_LABEL_12TOTAL = "12ヶ月合計";
    public static final String EIGYO_DT_LABEL_RUIKEI = "年度累計";
    public static final String EIGYO_DT_LABEL_TUUKI = "通期";
    public static final String EIGYO_DT_LABEL_KAMI = "上期";
    public static final String EIGYO_DT_LABEL_SIMO = "下期";
    public static final String EIGYO_DT_LABEL_HANKI_1 = "第一四半期";
    public static final String EIGYO_DT_LABEL_HANKI_2 = "第二四半期";
    public static final String EIGYO_DT_LABEL_HANKI_3 = "第三四半期";
    public static final String EIGYO_DT_LABEL_HANKI_4 = "第四四半期";
    public static final String EIGYO_DT_LABEL_GETU = "月合計";
    
    /**
     * 他画面遷移時事前処理
     * 
     * @param conditionDto　　日報共通DTO【条件部情報】
     * @param searchParamDto　日報共通DTO【検索条件】
     * @param resultParamDto　日報共通DTO【結果条件】
     * @param isNewWindow
     */
    public static void callScreenInitialize(SuiiRefConditionDto conditionDto
    		, SuiiRefParameterDto searchParamDto, SuiiRefParameterDto resultParamDto
    		, boolean isNewWindow)
    {
        //１．別ウィンドウフラグがtrueの場合、下記の処理を行います。
        if (isNewWindow) {
            //1.推移表共通DTO【条件部情報】.MAXウィンドウIDカウントアップし、MAXウィンドウIDを取得します。
            int newWindowId = conditionDto.updateWindowId();
            //2.DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //3.DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //２．推移表共通DTO【検索条件】.初期アクションフラグへtrueを設定します。
        searchParamDto.setInitActionFlg(true);
    }
    /**
     * 検索事前処理
     * @param conditionDto
     * @param searchParamDto
     */
    public static void searchInitialize(SuiiRefConditionDto conditionDto
    		, SuiiRefParameterDto searchParamDto) {
		//１．推移表共通DTO【結果条件】.[会社情報]へ
		//  推移表共通DTO【条件部情報】.List[[会社]]から対象会社コードのCodCompany[会社]を取得し設定します。
    	searchParamDto.setCodCompany(conditionDto.getEntityCodCompany(searchParamDto));
		//２．推移表共通DTO【結果条件】.List[[対象条件]]へ
    	//    推移表共通DTO【条件部情報】から推移表共通DTO【結果条件】.会社コード対象のList[[対象条件]]を取得し設定します。
    	searchParamDto.setListTaishoJoken(conditionDto.getListsTaishoJoken(searchParamDto.getCompanyCd()));
    	//３．推移表共通DTO【結果条件】.ブロック名称へ推移表共通DTO【条件部情報】.List[[ブロック]]から名称を取得し設定します。
    	searchParamDto.setBlockName(conditionDto.getBlockName(searchParamDto.getBlockCd()));
    }
    /**
     * 期間指定開始年月取得処理
     * 
     * @param taishoKikan
     * @param kikanSitei  期間指定
     * @return yyyyMM
     */
    public static String getKikanSiteiFrom(String taishoKikan, String kikanSitei) {
        String kikanSiteiFrom = "";
        //任意月指定の場合
        if( TaishoKikan.CODE_MONTH.equals(taishoKikan) ) {
        	try {
              kikanSiteiFrom = DateManager.getPrevMonth( kikanSitei, 12 );
        	}
        	catch(Exception e) {
        		throw new FtlSystemException("任意月指定の開始年月取得処理","", e);
        	}
        }
        //年度の場合
        else if( TaishoKikan.CODE_NENDO.equals(taishoKikan) ) {
        	kikanSiteiFrom = kikanSitei+"04";
        }
        return kikanSiteiFrom;
    }
    /**
     * 期間指定終了年月取得処理
     * 
     * @param taishoKikan
     * @param kikanSitei  期間指定
     * @return yyyyMM
     */
    public static String getKikanSiteiTo(String taishoKikan, String kikanSitei) {
        String kikanSiteiTo = "";
        //任意月指定の場合
        if(TaishoKikan.CODE_MONTH.equals(taishoKikan)) {
            kikanSiteiTo =  kikanSitei;
        }
        //年度の場合
        else if( TaishoKikan.CODE_NENDO.equals(taishoKikan) ) {
        	try {
             kikanSiteiTo = DateManager.getNextYear(kikanSitei,1)+"03";
        	}
        	catch(Exception e) {
        		throw new FtlSystemException("年度の年取得処理","", e);
        	}
        }
        return kikanSiteiTo;
    }
    /**
     * 期間(From)に日付情報を付加する
     * @param kikanFrom
     */
    public static String setKikanFromDay(String kikanFrom) {
        if(!CommonUtil.isNull(kikanFrom)) {
            if(kikanFrom.length()==6) kikanFrom += "01";  
        }
        return kikanFrom;
    }
    /**
     * 期間(To)に日付情報を付加する
     * @param kikanTo
     */
    public static String setKikanToDay(String kikanTo) {
        if(!CommonUtil.isNull(kikanTo)) {
            if(kikanTo.length()==6) kikanTo += "31";  
        }
        return kikanTo;
    }
    /**
     * 
     * @param val1
     * @param val2
     * @return
     */
	public static boolean equals(final String val1, final String val2) {
		if(CommonUtil.isNull(val1)!=CommonUtil.isNull(val2) ) return false;
		if(val1!=null) {
			if(!val1.equals(val2)) return false;
		}
		return true;
	}
    /**
     * ログインユーザーオーナーコード
     * 
     * @param birdUserInfo
     * @param companyCd
     * @return
     */
    public static UIUserOner getUserOnerCd(BirdUserInfo birdUserInfo, String companyCd) {
        List ownerList = birdUserInfo.getUserOner();
        for (Iterator it = ownerList.iterator(); it.hasNext();) {
            UIUserOner uIUserOner = (UIUserOner) it.next();
            String compCd = uIUserOner.getCompanyCd();
          	if(compCd.equals(companyCd)) {
          		return uIUserOner;
          	}
        }
        return null;

    }
    /**
     * タブエリア表示メッセージ取得処理
     * 
     * 1.指定月が当月（システム日付を含む月）の場合は、「12ヶ月合計と平均には当月は含みません」と固定メッセージを表示する。
     * 2.指定月が前月以前の場合は、「12ヶ月合計と平均には前年同月は含みません」と固定メッセージを表示する。
     * 更新日:2013/05/13 CHANGE 文言の変更(MOSCARDのみ)
     *  「合計」と「平均」は、前年同月数値を含みません。
     *  「合計」と「平均」は、当月数値を含みません。
     * @return
     */
    public static String getTabMsg(String sysDate, SuiiRefParameterDto parameterDto) {
    	if(TaishoKikan.CODE_MONTH.equals(parameterDto.getTaishoKikan())
    			&& SuiiRefUtil.SUII_TYPE_GEPO.equals(parameterDto.getFocusTab()))
    	{
    		//CTRL【BIRD日付情報】.システム年月
	    	if(sysDate.substring(0, 6).compareTo(parameterDto.getKikanSitei()) >0) 
	    	{
	    		//指定月がCTRL【BIRD日付情報】.システム年月の前月以前の場合は、
	    		//「12ヶ月合計と平均には前年同月は含みません」と固定メッセージを表示する。
	    		return "「合計」と「平均」は、前年同月数値を含みません。";
	    	}
	    	else {
				//指定月が当月（システム日付を含む月）の場合は、
	    		//「12ヶ月合計と平均には当月は含みません」と固定メッセージを表示する。
				return "「合計」と「平均」は、当月数値を含みません。";
	    	}
		}
    		return "";
    }
    /**
     * 売上データ集計行生成取得処理
     * 
     * @param sumentity
     * @param cssClassName
     * @param taishoTitle
     * @param lists
     * @param startRowIndex
     * @param endRowIndex
     * @return
     */
    public static void createSumEntity(
    		SuiiRefUIEntity sumentity, String cssClassName, String taishoTitle
    		, List lists, int startRowIndex, int endRowIndex, int scale) 
    {
        sumentity.setEigyoDt(taishoTitle);
        sumentity.setEigyoDtLabel(taishoTitle);
        
        if(lists==null) {
            return;
        }
        
        int cnt = lists.size();
        if( cnt == 0 || cnt <= startRowIndex) {
            return;
        }
        
        BigDecimal uriage = nullCheckAndFormated(sumentity.getUriage(), scale);
//add 2019/07/15 #35 USI張 begin
        //値引
        BigDecimal nebiki = nullCheckAndFormated(sumentity.getNebiki(), scale);
        //値引後売上
        BigDecimal uriageAfterNebiki = nullCheckAndFormated(sumentity.getUriageAfterNebiki(), scale);
//add 2019/07/15 #35 USI張 end
        BigDecimal yosan = nullCheckAndFormated(sumentity.getYosan(), 0);
        BigDecimal eigyonissu = nullCheckAndFormated(sumentity.getEigyoDays(), 0);
        BigDecimal kyakusu = nullCheckAndFormated(sumentity.getKyakusu(), 0);
 
        BigDecimal uriagezen = nullCheckAndFormated(sumentity.getUriageZen(), scale);
//add 2019/07/15 #35 USI張 begin
        //値引後前年実績
        BigDecimal uriageZenAfterNebiki = nullCheckAndFormated(sumentity.getUriageZenAfterNebiki(), scale);
//add 2019/07/15 #35 USI張 end
        BigDecimal eigyonissuzen = nullCheckAndFormated(sumentity.getEigyoDaysZen(), 0);
        BigDecimal kyakusuzen = nullCheckAndFormated(sumentity.getKyakusuZen(), 0);

        //NET
//begin add 2020/02/20 xou.zoubun
        BigDecimal net_nebiki = nullCheckAndFormated(sumentity.getNetNebiki(), scale);
        BigDecimal net_uriageafternebiki = nullCheckAndFormated(sumentity.getNetUriageAfterNebiki(), scale);
//end add 2020/02/20 xou.zoubun
        BigDecimal net_uriage = nullCheckAndFormated(sumentity.getNetUriage(), scale);
        BigDecimal net_uriagezen = nullCheckAndFormated(sumentity.getNetUriageZen(), scale);
        BigDecimal net_kyakusu = nullCheckAndFormated(sumentity.getNetKyakusu(), 0);
        BigDecimal net_kyakusuzen = nullCheckAndFormated(sumentity.getNetKyakusuZen(), 0);


        
        for(int i=startRowIndex; i<endRowIndex; i++) {
        	SuiiRefUIEntity entity = (SuiiRefUIEntity)lists.get(i);
            if(entity == null) continue; 
            uriage     = uriage.add(entity.getUriage());
//add 2019/07/15 #35 USI張 begin
            nebiki            = nebiki.add(entity.getNebiki());
            uriageAfterNebiki = uriageAfterNebiki.add(entity.getUriageAfterNebiki());
//add 2019/07/15 #35 USI張 end
            yosan      = yosan.add(entity.getYosan());
            eigyonissu = eigyonissu.add(entity.getEigyoDays());
            uriagezen  = uriagezen.add(entity.getUriageZen());
//add 2019/07/15 #35 USI張 begin
            uriageZenAfterNebiki = uriageZenAfterNebiki.add(entity.getUriageZenAfterNebiki());
//add 2019/07/15 #35 USI張 end
            eigyonissuzen = eigyonissuzen.add(entity.getEigyoDaysZen());
            kyakusu    = kyakusu.add(entity.getKyakusu());
            kyakusuzen = kyakusuzen.add(entity.getKyakusuZen());
            //NET値
//begin add 2020/02/20 xou.zoubun
            net_nebiki = net_nebiki.add(entity.getNetNebiki());
            net_uriageafternebiki = net_uriageafternebiki.add(entity.getNetUriageAfterNebiki());
//end add 2020/02/20 xou.zoubun
            net_uriage     = net_uriage.add(entity.getNetUriage());
            net_uriagezen  =  net_uriagezen.add(entity.getNetUriageZen());
            net_kyakusu    =  net_kyakusu.add(entity.getNetKyakusu());
            net_kyakusuzen =  net_kyakusuzen.add(entity.getNetKyakusuZen());
        }
        
        sumentity.setCssClassName(cssClassName);
        sumentity.setUriage(uriage);
//add 2019/07/15 #35 USI張 begin
        sumentity.setNebiki(nebiki);
        sumentity.setUriageAfterNebiki(uriageAfterNebiki);
//add 2019/07/15 #35 USI張 end
        sumentity.setYosan(yosan);
        sumentity.setEigyoDays(eigyonissu);
        sumentity.setUriageZen(uriagezen);
//add 2019/07/15 #35 USI張 begin
        sumentity.setUriageZenAfterNebiki(uriageZenAfterNebiki);
//add 2019/07/15 #35 USI張 end
        sumentity.setEigyoDaysZen(eigyonissuzen);
        sumentity.setKyakusu(kyakusu);
        sumentity.setKyakusuZen(kyakusuzen);
        // NET値
//begin add 2020/02/20 xou.zoubun
        sumentity.setNetNebiki(net_nebiki);
        sumentity.setNetUriageAfterNebiki(net_uriageafternebiki);
//end add 2020/02/20 xou.zoubun
        sumentity.setNetUriage(net_uriage);
        sumentity.setNetUriageZen(net_uriagezen);
        sumentity.setNetKyakusu(net_kyakusu);
        sumentity.setNetKyakusuZen(net_kyakusuzen);
        //単価計算処理
        calcTanka(sumentity, scale);
        //比率計算処理
        calcHiritu(sumentity);
    }
    /**
     * 12ヶ月売上データ平均値算出設定
     * 
     * @param entiry12Sum 12ヶ月合計エンティティ
     * @param avgCnt 
     * @return
     * @throws Exception
     */
    public static void createEntity12Avg(SuiiRefUIEntity entiry12Avg, SuiiRefUIEntity entiry12Sum, int avgCnt, int scale) 
    {
         //12ヶ月平均エンティティを作成
        BigDecimal decAvcCnt = new BigDecimal(avgCnt);
        entiry12Avg.setCssClassName("body_sum1");
        //当年
        entiry12Avg.setEigyoDt("12ヶ月平均");
        entiry12Avg.setEigyoDtLabel(entiry12Avg.getEigyoDt());
        //売上
        entiry12Avg.setUriage(Calculator.divide(entiry12Sum.getUriage(), decAvcCnt, scale));
//add 2019/10/01 #35 xou.zoubun begin
        entiry12Avg.setNebiki(Calculator.divide(entiry12Sum.getNebiki(), decAvcCnt, scale));
        entiry12Avg.setUriageAfterNebiki(Calculator.divide(entiry12Sum.getUriageAfterNebiki(), decAvcCnt, scale));
//add 2019/10/01 #35 xou.zoubun end
        entiry12Avg.setUriageZen(Calculator.divide(entiry12Sum.getUriageZen(), decAvcCnt, scale));
        entiry12Avg.setNetUriage(Calculator.divide(entiry12Sum.getNetUriage(), decAvcCnt, scale));
        entiry12Avg.setNetUriageZen(Calculator.divide(entiry12Sum.getNetUriageZen(), decAvcCnt, scale));
//add 2019/10/01 #35 xou.zoubun begin
        //値引後前年実績
        entiry12Avg.setUriageZenAfterNebiki(Calculator.divide(entiry12Sum.getUriageZenAfterNebiki(), decAvcCnt, scale));
//add 2019/10/01 #35 xou.zoubun end
        //予算
        entiry12Avg.setYosan(Calculator.divide(entiry12Sum.getYosan(), decAvcCnt));
        //営業日数
        entiry12Avg.setEigyoDays(Calculator.divide(entiry12Sum.getEigyoDays(), decAvcCnt));
        entiry12Avg.setEigyoDaysZen(Calculator.divide(entiry12Sum.getEigyoDaysZen(), decAvcCnt));
         //客数
        entiry12Avg.setKyakusu(Calculator.divide(entiry12Sum.getKyakusu(), decAvcCnt));
        entiry12Avg.setKyakusuZen(Calculator.divide(entiry12Sum.getKyakusuZen(), decAvcCnt));
        entiry12Avg.setNetKyakusu(Calculator.divide(entiry12Sum.getNetKyakusu(), decAvcCnt));
        entiry12Avg.setNetKyakusuZen(Calculator.divide(entiry12Sum.getNetKyakusuZen(), decAvcCnt));
        //単価計算処理
        calcTanka(entiry12Avg, scale);
        //比率計算処理
        calcHiritu(entiry12Avg);
    }
    /**
     * 単価計算処理
     * 
     * @param entity
     * @param scale
     */
    public static void calcTanka(SuiiRefUIEntity entity, int scale) {
        //客単価
        entity.setKyakutanka(Calculator.divide(entity.getUriage(), entity.getKyakusu(), scale));
        entity.setKyakutankaZen(Calculator.divide(entity.getUriageZen(), entity.getKyakusuZen(), scale));
        entity.setNetKyakutanka(Calculator.divide(entity.getNetUriage(), entity.getNetKyakusu(), scale));
        entity.setNetKyakutankaZen(Calculator.divide(entity.getNetUriageZen(), entity.getNetKyakusuZen(), scale));
    }
    /**
     * 比率計算処理
     * @param entity
     */
    public static void calcHiritu(SuiiRefUIEntity entity) {
    	//予算達成率
    	BigDecimal tasRitu = Calculator.percentage(entity.getUriage(), entity.getYosan(),2);
    	entity.setYosanTasseiRitu(tasRitu.compareTo(new BigDecimal("0"))!=1?new BigDecimal("0.00"):tasRitu);
//add 2019/07/18 #35 USI張 begin
    	//値引後達成率
    	BigDecimal tasseiAfterNebiki = Calculator.percentage(entity.getUriageAfterNebiki(), entity.getYosan(),2);
    	entity.setTasseiAfterNebiki(tasseiAfterNebiki.compareTo(new BigDecimal("0"))!=1?new BigDecimal("0.00"):tasseiAfterNebiki);
    	//値引後売上前年比
    	BigDecimal uriageZennenhiAfterNebiki = Calculator.percentage(entity.getUriageAfterNebiki(), entity.getUriageZenAfterNebiki(),2);
    	entity.setUriageZennenhiAfterNebiki(uriageZennenhiAfterNebiki.compareTo(new BigDecimal("0"))!=1?new BigDecimal("0.00"):uriageZennenhiAfterNebiki);
//add 2019/07/18 #35 USI張 end
    	//売上(前年比)
        entity.setUriageZennenhi(Calculator.percentage(entity.getUriage(), entity.getUriageZen(),2));
        entity.setNetUriageZennenhi(Calculator.percentage(entity.getNetUriage(), entity.getNetUriageZen(),2));
//begin add 2020/02/18 xou.zoubun 値引き後前年比＝値引き後前年比対象売上/値引後前年実績
        BigDecimal netUriageZennenhiAfterNebiki = Calculator.percentage(entity.getNetUriageAfterNebiki(), entity.getUriageZenAfterNebiki(),2);
        entity.setNetUriageZennenhiAfterNebiki(netUriageZennenhiAfterNebiki.compareTo(new BigDecimal("0"))!=1?new BigDecimal("0.00"):netUriageZennenhiAfterNebiki);
//end add 2020/02/18 xou.zoubun
        //客数(前年比)
        entity.setKyakusuZennenhi(Calculator.percentage(entity.getKyakusu(), entity.getKyakusuZen(),2));
        entity.setNetKyakusuZennenhi(Calculator.percentage(entity.getNetKyakusu(), entity.getNetKyakusuZen(),2));
        //客単価(前年比)
        entity.setKyakutankaZennenhi(Calculator.percentage(entity.getKyakutanka(), entity.getKyakutankaZen(),2));
        entity.setNetKyakutankaZennenhi(Calculator.percentage(entity.getNetKyakutanka(), entity.getNetKyakutankaZen(),2));
    }
    /**
     * 
     * @param value
     * @param scale
     * @return
     */
    public static BigDecimal nullCheckAndFormated(BigDecimal value , int scale) {
    	Formatter formatter = new NumericFormatter(true, scale, true);
    	String strtValue="0";
    	if(value!=null) {
    		strtValue= formatter.format(value, false);
    	}
    	else {
    		strtValue= formatter.format(strtValue, false);
    	}
        return new BigDecimal(strtValue);
    }
	/**
	 * ヘッダー情報作成
	 * 
	 * @param dto
	 * @param focusTabData
	 * @param listCsv
	 */
	public static void csvSetHeader(BirdUserInfo birdUserInfo, SuiiRefParameterDto dto, UITabData focusTabData, List listCsv)  {
       /**
         * 条件項目ヘッダ作成
         *  1行目：会社
         *  2行目：店舗種別 
         *  3行目：前年データ種別
         *  4行目：対象条件
         *  5行目：表示対象
         *  6行目：対象期間
         *  7行目：列１：取得店舗数　列３：表示通貨
         */
        List listHeaderRowCompany = new ArrayList();
        List listHeaderRowTenpoShubetu = new ArrayList();
        List listHeaderRowZenDataShubetu = new ArrayList();
        List listHeaderRowTaishoJoken = new ArrayList();
        List listHeaderRowHyojiTaisho = new ArrayList();
        List listHeaderRowTaishoKikan = new ArrayList();
        List listHeaderRowKikanSitei = new ArrayList();
        List listHeaderRowTaishoTenpoSu = new ArrayList();

        //条件項目ヘッダ設定
        //会社情報
        listHeaderRowCompany.add("会社");
        listHeaderRowCompany.add(dto.getCodCompany().getCompanyName());
        //条件項目へ設定
        listCsv.add(listHeaderRowCompany);
        
        boolean isHonbuUser = UserType.isHonbu(birdUserInfo.getMstUser().getUserTypeCd());
        if(isHonbuUser) {
            listHeaderRowTenpoShubetu.add("店舗種別");
            listHeaderRowTenpoShubetu.add(dto.getTenpoShubetuName());
            listCsv.add(listHeaderRowTenpoShubetu);
        }
        
        listHeaderRowZenDataShubetu.add("前年データ種別");
        listHeaderRowZenDataShubetu.add(dto.getZennenDataShubetuName());
        listCsv.add(listHeaderRowZenDataShubetu);
            listHeaderRowTaishoJoken.add("対象条件");
            listHeaderRowTaishoJoken.add(dto.getTaishoJokenName());
            listCsv.add(listHeaderRowTaishoJoken);
        if(dto.getHyojiTaishoName() != null) {
            listHeaderRowHyojiTaisho.add("表示対象");
            String hyojiTaishoName = dto.getHyojiTaishoName();
//対応保留　2011/04に発覚したブロック名称未設定不具合。
//            if(UserType.isHonbu(dto.getUsertypeCd())
//            		&& TaishoJoken.CODE_SIBU.equals(dto.getTaishoJoken())
//            		&& dto.getBlockCd() != null)
//            {
//            	//支部指定のブロックコード選択時はブロック名称も設定します。
//            	hyojiTaishoName = dto.getHyojiTaishoName() + " "+ dto.getBlockName();
//            }
//対応保留　2011/04に発覚したブロック名称未設定不具合。
        	listHeaderRowHyojiTaisho.add(hyojiTaishoName);
            listCsv.add(listHeaderRowHyojiTaisho);
        }
        boolean isGepo = dto.isSuiiTypeGepo();
        if(isHonbuUser && isGepo){
            listHeaderRowTaishoKikan.add("対象期間");
            listHeaderRowTaishoKikan.add(dto.getTaishoKikanName());
            listCsv.add(listHeaderRowTaishoKikan);
        }
        listHeaderRowKikanSitei.add("期間指定");
        if(isGepo){
            //月報
            if(dto.getTaishoKikan().equals(TaishoKikan.CODE_NENDO)){
                listHeaderRowKikanSitei.add(dto.getKikanSitei()+"年度");
            }else{
                listHeaderRowKikanSitei.add(dateFmtYMKj.format(dto.getKikanSitei(),true));
            }
        }else{
            //日報
            listHeaderRowKikanSitei.add(dateFmtYMKj.format(dto.getFocusTab(),true));
        }
        listCsv.add(listHeaderRowKikanSitei);
        
        NumericFormatter numFmt = new NumericFormatter();
        listHeaderRowTaishoTenpoSu.add("対象店舗数");
        listHeaderRowTaishoTenpoSu.add(numFmt.format(String.valueOf(focusTabData.getMiseCnt())));
        //選択会社が海外の場合は表示通貨情報を出力
        if(BizReportConstants.FOREING_ON.equals(dto.getCodCompany().getForeignFlg())) {
            listHeaderRowTaishoTenpoSu.add("表示通貨："+(dto.isKansan()?"日本円":dto.getCodCompany().getCurrencyName()));
        }
        
        
        listCsv.add(listHeaderRowTaishoTenpoSu);

        //空白行
        listCsv.add(new ArrayList());
	}
	/**
	 * データ設定処理
	 * 
	 * @param entity
	 * @param listRow
	 * @param isGepo
	 * @param isHosei
	 * @param isTaishoJokenMise
     * @param pointCount
	 */
	public static void csvSetData(SuiiRefUIEntity entity, List listRow
//modify 2019/07/18 #35 USI張 begin
//			,  boolean isGepo, boolean isHosei, boolean isTaishoJokenMise, int pointCount) {
			,  boolean isGepo, boolean isHosei, boolean isTaishoJokenMise, boolean isNebiki, int pointCount) {
//modify 2019/07/18 #35 USI張 end
        //換算ボタン実行時のフォーマットを設定
        NumericFormatter numFmtdgtConvert = new NumericFormatter(true, pointCount, true);
        
		if(isGepo){
			listRow.add(dateFmtYMKj.format(entity.getEigyoDtLabel(), true));
		}
		else {
	        listRow.add(dateFmtddEKj.format(entity.getEigyoDtLabel(), true));
		}
        listRow.add(numFmtdgtConvert.format(entity.getUriage()));
//add 2019/07/18 #35 USI張 begin
        if(isNebiki) {
        	//値引
            listRow.add(numFmtdgtConvert.format(entity.getNebiki()));
            //値引後売上
            listRow.add(numFmtdgtConvert.format(entity.getUriageAfterNebiki()));
        }
//add 2019/07/18 #35 USI張 end
        listRow.add(numFmtdgt0.format(entity.getYosan()));
        listRow.add(numFmtdgt2.format(entity.getYosanTasseiRitu()));
//add 2019/07/18 #35 USI張 begin
        if(isNebiki) {
        	//値引後達成率
            listRow.add(numFmtdgt2.format(entity.getTasseiAfterNebiki()));
        }
//add 2019/07/18 #35 USI張 end
        if(isTaishoJokenMise) {
            if(isGepo) {
                //対象条件が個店又は店舗の場合営業日数を格納
                listRow.add(entity.getEigyoDays());
            }else {
                //対象条件が個店又は店舗の場合天候区分を格納
                listRow.add(entity.getTenkoKbnKj());
            }
            
        }
        if(isHosei) {
            listRow.add(numFmtdgtConvert.format(entity.getNetUriage()));
//begin add 2020/02/19 xou.zoubun
            if(isNebiki) {
                listRow.add(numFmtdgtConvert.format(entity.getNetNebiki())); //前年比対象値引き
                listRow.add(numFmtdgtConvert.format(entity.getNetUriageAfterNebiki())); //前年比対象値引き後売上
            }
//end add 2020/02/19 xou.zoubun
            listRow.add(numFmtdgtConvert.format(entity.getNetUriageZen()));
//add 2019/07/18 #35 USI張 begin
            //値引後前年実績
            if(isNebiki) {
                listRow.add(numFmtdgtConvert.format(entity.getUriageZenAfterNebiki()));
            }
//add 2019/07/18 #35 USI張 end
            listRow.add(numFmtdgt2.format(entity.getNetUriageZennenhi()));
//add 2019/07/18 #35 USI張 begin
            if(isNebiki) {
//begin modify 2020/02/19 xou.zoubun
                //listRow.add(numFmtdgt2.format(entity.getUriageZennenhiAfterNebiki()));
                listRow.add(numFmtdgt2.format(entity.getNetUriageZennenhiAfterNebiki()));
//end modify 2020/02/19 xou.zoubun
            }
//add 2019/07/18 #35 USI張 end
        }else{
            listRow.add(numFmtdgtConvert.format(entity.getUriageZen()));
//add 2019/07/18 #35 USI張 begin
            //値引後前年実績
            if(isNebiki) {
                listRow.add(numFmtdgtConvert.format(entity.getUriageZenAfterNebiki()));
            }
//add 2019/07/18 #35 USI張 end

            listRow.add(numFmtdgt2.format(entity.getUriageZennenhi()));
//add 2019/07/18 #35 USI張 begin
            if(isNebiki) {
                listRow.add(numFmtdgt2.format(entity.getUriageZennenhiAfterNebiki()));
            }
//add 2019/07/18 #35 USI張 end
        }
        if(isTaishoJokenMise) {
            if(isGepo) {
                //対象条件が個店又は店舗の場合営業日数を格納
                listRow.add(entity.getEigyoDaysZen());
            }
            else {
                //対象条件が個店又は店舗の場合天候区分を格納
                listRow.add(entity.getTenkoKbnZenKj());
            }
            
        }
        listRow.add(numFmtdgt0.format(entity.getKyakusu()));
        if(isHosei) {
            listRow.add(numFmtdgt0.format(entity.getNetKyakusu()));
            listRow.add(numFmtdgt0.format(entity.getNetKyakusuZen()));
            listRow.add(numFmtdgt2.format(entity.getNetKyakusuZennenhi()));
        }else{
            listRow.add(numFmtdgt0.format(entity.getKyakusuZen()));
            listRow.add(numFmtdgt2.format(entity.getKyakusuZennenhi()));
        }
        listRow.add(numFmtdgtConvert.format(entity.getKyakutanka()));
        if(isHosei) {
            listRow.add(numFmtdgtConvert.format(entity.getNetKyakutanka()));
            listRow.add(numFmtdgtConvert.format(entity.getNetKyakutankaZen()));
            listRow.add(numFmtdgt2.format(entity.getNetKyakutankaZennenhi()));
        }else{
            listRow.add(numFmtdgtConvert.format(entity.getKyakutankaZen()));
            listRow.add(numFmtdgt2.format(entity.getKyakutankaZennenhi()));
        }

	}
	/**
	 * データ項目名作成取得処理
	 * 
	 * @param parameterDto 
	 * @return　List[[項目名]]
	 */
    //modify xou.zoubun 2019/11/13  	public static List csvGetDataTitle(SuiiRefParameterDto parameterDto) {
	public static List csvGetDataTitle(SuiiRefParameterDto parameterDto, boolean isUriTab) {
		boolean isGepo = parameterDto.isSuiiTypeGepo();
		boolean isHosei  = parameterDto.isSelectHosei();
//add 2019/07/18 #35 USI張 begin
		boolean isNebiki = parameterDto.getNebikiFlg() && isUriTab;
//add 2019/07/18 #35 USI張 end
    	List listCsvTableHeader = new ArrayList();
        if(isGepo) {
            listCsvTableHeader.add("年月");
        }
        else{
            listCsvTableHeader.add("営業日");
        }
		listCsvTableHeader.add("売上");
//add 2019/07/18 #35 USI張 begin
		if(isNebiki) {
			listCsvTableHeader.add("値引");
			listCsvTableHeader.add("値引後売上");
		}
//add 2019/07/18 #35 USI張 end
		listCsvTableHeader.add("予算");
        listCsvTableHeader.add("達成率");
//add 2019/07/18 #35 USI張 begin
        if(isNebiki) {
			listCsvTableHeader.add("値引後達成率");
        }
//add 2019/07/18 #35 USI張 end
        String taishoJoiken = parameterDto.getTaishoJoken();
        if(taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
            if(isGepo) {
                listCsvTableHeader.add("営業日数");
            }else{
                listCsvTableHeader.add("天候");
            }
        }
        if(isHosei) {
            listCsvTableHeader.add("前年比対象売上");
//end add 2020/02/19 xou.zoubun 前年比対象項目
            if(isNebiki) {
                listCsvTableHeader.add("前年比対象値引");
                listCsvTableHeader.add("前年比対象値引後売上");
            }
//end add 2020/02/19 xou.zoubun
            listCsvTableHeader.add("前年比対象前年実績");
        }else{
            listCsvTableHeader.add("前年実績");
        }
//add 2019/07/18 #35 USI張 begin
        if(isNebiki) {
            listCsvTableHeader.add("値引後前年実績");
        }
//add 2019/07/18 #35 USI張 end
        listCsvTableHeader.add("前年比(売上)");
//add 2019/07/18 #35 USI張 begin
        if(isNebiki) {
            listCsvTableHeader.add("値引後前年比(売上)");
        }
//add 2019/07/18 #35 USI張 end
        if(taishoJoiken.equals(TaishoJoken.CODE_MISE)) {
            if(isGepo) {
                listCsvTableHeader.add("前年営業日数");
            }else{
                listCsvTableHeader.add("前年天候");
            }
        }
        listCsvTableHeader.add("客数");
        if(isHosei) {
            listCsvTableHeader.add("前年比対象客数");
            listCsvTableHeader.add("前年比対象前年客数");
        }else{
            listCsvTableHeader.add("前年客数");
        }
        listCsvTableHeader.add("前年比(客数)");
        listCsvTableHeader.add("客単価");
        if(isHosei) {
            listCsvTableHeader.add("前年比対象客単価");
            listCsvTableHeader.add("前年比対象前年客単価");
        }else{
            listCsvTableHeader.add("前年客単価");
        }
        listCsvTableHeader.add("前年比(客単価)");
        
        return listCsvTableHeader;
    }

}
