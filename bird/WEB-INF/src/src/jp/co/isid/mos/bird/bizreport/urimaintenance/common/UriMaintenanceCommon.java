package jp.co.isid.mos.bird.bizreport.urimaintenance.common;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIUriMaintenanceResult;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIKKbnReviseData;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 * 売上修正共通【静的処理クラス】
 * 
 */
public class UriMaintenanceCommon {
    /**
     * ユーザーオペレーション整合性チェック
     * 
     * 複数ウィンドウ不可の画面の為、ユーザーオペレーションの整合性チェックを行います。
     * 現行のセッションに保持されているオペレーション制御キーとリクエストされた画面のオペレーションキーが
     * 一致するかで判断します。
     * true：一致する場合　 false：一致しない場合
     * 一致しない場合、複数ウィンドウやブラウザの戻るボタンなどを利用したオペレーションとみなし
     * 整合性無しとしてfalseを返します。
     * @return
     */
    public static boolean isUserOperationError(UriMaintenanceDto sessionDto) {
    	MakeSessionKey makeSessionKey = new MakeSessionKey();
        return !makeSessionKey.isValidSessionKey( 
        		sessionDto.getSessionKey()
                ,  sessionDto.getViewSessionKey());
    }
    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }

    /**
     * Null判断処理を行う
     * @param list チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(List list) {
        return (list != null && list.size() > 0) ? false : true;
    }
    
    /**
     * 渡された文字列がNullの場合、空白("”)に変換する
     * 渡された文字列がNull以外場合、Trim()する
     * @param str 文字列
     * @return String パラメータがNULLの場合空白("")
     */
    public static String setEmpty(String str) {
    	if(str == null ||  str.trim().length() == 0){
    		return GyotaibetuNipoConstants.EMPTY;
    	}else{
    		return str.trim();
    	}
    }
    /**
     * 日別合計値計算処理
     * 
     * @param listTabResult
     * @param isDouki
     * @return
     */
    public static List calculateDayTotal(List listTabResult, boolean isDouki) {
	    List listDayTotal = new ArrayList(0);
		UIUriMaintenanceResult uiResultData = (UIUriMaintenanceResult)listTabResult.get(0);
		//日数分下記の処理を行います。
		for(int i=0; i<uiResultData.getListData().size(); i++) {
			BigDecimal total[] = {new BigDecimal("0"), new BigDecimal("0")};
			//タブ分下記の処理を行います。
			for(int t=0; t<listTabResult.size(); t++) {
				UIUriMaintenanceResult uiTabResult = (UIUriMaintenanceResult)listTabResult.get(t);
				//for2-2.Map[タブ別
				UIKKbnReviseData eRevising = (UIKKbnReviseData)uiTabResult.getListData().get(i);
				total[0] = total[0].add(eRevising.getReviseKenTotal1_4());
				total[1] = total[1].add(eRevising.getReviseKinTotal1_4());
			}
			listDayTotal.add(total);
		}
		for(int i=0; i<listDayTotal.size(); i++) {
			BigDecimal total[] = (BigDecimal[])listDayTotal.get(i);
			for(int t=0; t<listTabResult.size(); t++) {
				UIUriMaintenanceResult uiTabResult = (UIUriMaintenanceResult)listTabResult.get(t);
				//for2-2.Map[タブ別
				UIKKbnReviseData eRevising = (UIKKbnReviseData)uiTabResult.getListData().get(i);
				eRevising.setReviseKenTotal(total[0]);
				eRevising.setReviseKinTotal(total[1]);
				if(isDouki) {
					//修正前の値に同じ合計値を設定する。
					eRevising.setBd31KenTotal(total[0]);
					eRevising.setBd31KinTotal(total[1]);
				}
			}
		}
		return listDayTotal;
    }

    
    /**
     * String から BigDecimalへ変換
     * @return パラメータ値がnull、または数値以外の場合は「0」を戻す
     */
    public static BigDecimal convDecimal(String st) {
        return (st == null || !isNumericFormat(st)) ? new BigDecimal("0") : new BigDecimal(st);
    }
    
    
    /**
     * 数値チェック
     */
    public static boolean isNumericFormat(String val) {
        NumericVerifier numericVerifier = new NumericVerifier(false);
        if (!isNull(val)
                && !numericVerifier.validate(val)) {
            return false;
        }
        return true;
    }
    
    
    /**
     * 指定年月の日付リストを生成します。
     * @param  yyyymmdd 年月日(YYYYMMdd)
     * @param  cnt    リスト個数
     * @return List   期間データ(YYYYMMddのリスト)
     * @exception ApplicationException
     */
    public static List makeListDate(String yyyymmdd, int cnt) throws ApplicationException {

        //返却用リスト
        List list = new ArrayList();

        if(yyyymmdd == null || yyyymmdd.length() != 8 ){
            //空のリストを返却
            return list;
        }
        
        for (int i = 0; i < cnt; i++) {
            String code = "";
            try {
                code = DateManager.getNextDate(yyyymmdd, i);
            }catch (Exception ex) {
                throw new FtlSystemException("期間指定生成"
                        , "アプリ日付年月["+yyyymmdd+"]から["+i+"]を引く際のDateManager.getNextDateメソッド処理で例外が発生しました。"
                        , ex);
            }
            list.add(code);
        }
        return list;
    }
 
    
    
    /**
     * 売上修正Entityを生成する
     * ※BT65SADY+BT95NBKI用Entity⇒BT97ADUP+BT96NBUP用Entityにコピー
     * ※新規追加フラグに固定でTrueを設定
     * @param uriMainteWorkInfo
     * @param uriMainteInfo
     * @param sysdate
     */
    public static void copyEntity(UIUriMainteWorkInfo uriMainteWorkInfo, UIUriMainteInfo uriMainteInfo, String sysdate) {          
        
        //----------------------
        // 新規追加フラグ 
        //----------------------
        uriMainteWorkInfo.setInsertFlg(true);
        
        //----------------------
        // 更新項目No
        //----------------------
        uriMainteWorkInfo.setUpNo96("000000000000000000000000000");
        uriMainteWorkInfo.setUpNo97("0000000000000000000000000000000000000000000000000000000000000000000000000000");
        
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        uriMainteWorkInfo.setDataKbn("");
        uriMainteWorkInfo.setBatupDt("");
        uriMainteWorkInfo.setUpDt(sysdate);
        uriMainteWorkInfo.setLastTmsp96(currentTimestamp);
        uriMainteWorkInfo.setLastTmsp97(currentTimestamp);
        uriMainteWorkInfo.setFlg1("");
        uriMainteWorkInfo.setFlg2("");
        uriMainteWorkInfo.setFlg3("");
        
        //----------------------
        // 現金在高
        //----------------------
        uriMainteWorkInfo.setCompanyCd(uriMainteInfo.getCompanyCd());
        uriMainteWorkInfo.setMiseCd(uriMainteInfo.getMiseCd());
        uriMainteWorkInfo.setEigyoDt(uriMainteInfo.getEigyoDt());
        uriMainteWorkInfo.setTenkoKbn(uriMainteInfo.getTenkoKbn());
        uriMainteWorkInfo.setU01Uriage(uriMainteInfo.getUriage());
        uriMainteWorkInfo.setU02MenuUri(uriMainteInfo.getMenuUri());
        uriMainteWorkInfo.setU03BuppanUri(uriMainteInfo.getBuppanUri());
        uriMainteWorkInfo.setU04Nebiki(uriMainteInfo.getNebiki());
        uriMainteWorkInfo.setU05Nebikigo(uriMainteInfo.getNebikigo());
        uriMainteWorkInfo.setU06Tax(uriMainteInfo.getTax());
        uriMainteWorkInfo.setU07MinusKen(uriMainteInfo.getMinusKen());
        uriMainteWorkInfo.setU08MinusKin(uriMainteInfo.getMinusKin());
        uriMainteWorkInfo.setU09NebikiKen(uriMainteInfo.getNebiki());
        uriMainteWorkInfo.setU10NebikiKin(uriMainteInfo.getNebikiKin());
        uriMainteWorkInfo.setU11PNebikiKen(uriMainteInfo.getPNebikiKen());
        uriMainteWorkInfo.setU12PNebikiKin(uriMainteInfo.getPNebikiKin());
        uriMainteWorkInfo.setU13GenkinKen(uriMainteInfo.getGenkinKen());
        uriMainteWorkInfo.setU14GenkinKin(uriMainteInfo.getGenkinKin());
        
        uriMainteWorkInfo.setU15KaikeiKen2(uriMainteInfo.getKaikeiKen2());
        uriMainteWorkInfo.setU16KaikeiKin2(uriMainteInfo.getKaikeiKin2());
        uriMainteWorkInfo.setU17KaikeiKen3(uriMainteInfo.getKaikeiKen3());
        uriMainteWorkInfo.setU18KaikeiKin3(uriMainteInfo.getKaikeiKin3());
        uriMainteWorkInfo.setU19KaikeiKen4(uriMainteInfo.getKaikeiKen4());
        uriMainteWorkInfo.setU20KaikeiKin4(uriMainteInfo.getKaikeiKin4());
        uriMainteWorkInfo.setU21KaikeiKen5(uriMainteInfo.getKaikeiKen5());
        uriMainteWorkInfo.setU22KaikeiKin5(uriMainteInfo.getKaikeiKin5());
        uriMainteWorkInfo.setU23KaikeiKen6(uriMainteInfo.getKaikeiKen6());
        uriMainteWorkInfo.setU24KaikeiKin6(uriMainteInfo.getKaikeiKin6());
        uriMainteWorkInfo.setU25KaikeiKen7(uriMainteInfo.getKaikeiKen7());
        uriMainteWorkInfo.setU26KaikeiKin7(uriMainteInfo.getKaikeiKin7());
        uriMainteWorkInfo.setU27KaikeiKen8(uriMainteInfo.getKaikeiKen8());
        uriMainteWorkInfo.setU28KaikeiKin8(uriMainteInfo.getKaikeiKin8());
        uriMainteWorkInfo.setU29KaikeiKen9(uriMainteInfo.getKaikeiKen9());
        uriMainteWorkInfo.setU30KaikeiKin9(uriMainteInfo.getKaikeiKin9());
        uriMainteWorkInfo.setU31KaikeiKen10(uriMainteInfo.getKaikeiKen10());
        uriMainteWorkInfo.setU32KaikeiKin10(uriMainteInfo.getKaikeiKin10());
        uriMainteWorkInfo.setU33KaikeiKen11(uriMainteInfo.getKaikeiKen11());
        uriMainteWorkInfo.setU34KaikeiKin11(uriMainteInfo.getKaikeiKin11());
        
        uriMainteWorkInfo.setU35TieketKen1(uriMainteInfo.getTieketKen1());
        uriMainteWorkInfo.setU36TieketKin1(uriMainteInfo.getTieketKin1());
        uriMainteWorkInfo.setU37TieketKen2(uriMainteInfo.getTieketKen2());
        uriMainteWorkInfo.setU38TieketKin2(uriMainteInfo.getTieketKin2());
        uriMainteWorkInfo.setU39TieketKen3(uriMainteInfo.getTieketKen3());
        uriMainteWorkInfo.setU40TieketKin3(uriMainteInfo.getTieketKin3());
        uriMainteWorkInfo.setU41TieketKen4(uriMainteInfo.getTieketKen4());
        uriMainteWorkInfo.setU42TieketKin4(uriMainteInfo.getTieketKin4());
        uriMainteWorkInfo.setU43TieketKen5(uriMainteInfo.getTieketKen5());
        uriMainteWorkInfo.setU44TieketKin5(uriMainteInfo.getTieketKin5());
        uriMainteWorkInfo.setU45TieketKen6(uriMainteInfo.getTieketKen6());
        uriMainteWorkInfo.setU46TieketKin6(uriMainteInfo.getTieketKin6());
        uriMainteWorkInfo.setU47TieketKen7(uriMainteInfo.getTieketKen7());
        uriMainteWorkInfo.setU48TieketKin7(uriMainteInfo.getTieketKin7());
        uriMainteWorkInfo.setU49TieketKen8(uriMainteInfo.getTieketKen8());
        uriMainteWorkInfo.setU50TieketKin8(uriMainteInfo.getTieketKin8());
        uriMainteWorkInfo.setU51TieketKen9(uriMainteInfo.getTieketKen9());
        uriMainteWorkInfo.setU52TieketKin9(uriMainteInfo.getTieketKin9());
        uriMainteWorkInfo.setU53TieketKen10(uriMainteInfo.getTieketKen10());
        uriMainteWorkInfo.setU54TieketKin10(uriMainteInfo.getTieketKin10());
        uriMainteWorkInfo.setU55TieketKen11(uriMainteInfo.getTieketKen11());
        uriMainteWorkInfo.setU56TieketKin11(uriMainteInfo.getTieketKin11());
        uriMainteWorkInfo.setU57TieketKen12(uriMainteInfo.getTieketKen12());
        uriMainteWorkInfo.setU58TieketKin12(uriMainteInfo.getTieketKin12());
        uriMainteWorkInfo.setU59TieketKen13(uriMainteInfo.getTieketKen13());
        uriMainteWorkInfo.setU60TieketKin13(uriMainteInfo.getTieketKin13());
        uriMainteWorkInfo.setU61TieketKen14(uriMainteInfo.getTieketKen14());
        uriMainteWorkInfo.setU62TieketKin14(uriMainteInfo.getTieketKin14());
        uriMainteWorkInfo.setU63TieketKen15(uriMainteInfo.getTieketKen15());
        uriMainteWorkInfo.setU64TieketKin15(uriMainteInfo.getTieketKin15());
        
        uriMainteWorkInfo.setU65Nyukin(uriMainteInfo.getNyukin());
        uriMainteWorkInfo.setU66Shukin(uriMainteInfo.getShukin());
        uriMainteWorkInfo.setU67AridakaCal(uriMainteInfo.getAridakaCal());
        uriMainteWorkInfo.setU68AridakaJitu(uriMainteInfo.getAridakaJitu());
        uriMainteWorkInfo.setU69Kajou(uriMainteInfo.getKajou());
        uriMainteWorkInfo.setU70Fusoku(uriMainteInfo.getFusoku());
        uriMainteWorkInfo.setU71CanKen(uriMainteInfo.getCanKen());
        uriMainteWorkInfo.setU72CanKin(uriMainteInfo.getCanKin());
        uriMainteWorkInfo.setU73ChengeCnt(uriMainteInfo.getChengeCnt());
        uriMainteWorkInfo.setU74Kyakusu(uriMainteInfo.getKyakusu());
        uriMainteWorkInfo.setU75AllcanKen(uriMainteInfo.getAllcanKen());
        uriMainteWorkInfo.setU76AllcanKin(uriMainteInfo.getAllcanKin());
        
        
        //----------------------
        // 取引
        //----------------------
        uriMainteWorkInfo.setU01NebikiKen1(uriMainteInfo.getNebikiKen1());
        uriMainteWorkInfo.setU02NebikiKin1(uriMainteInfo.getNebikiKin1());
        uriMainteWorkInfo.setU03NebikiTax1(uriMainteInfo.getNebikiTax1());
        uriMainteWorkInfo.setU04NebikiKen2(uriMainteInfo.getNebikiKen2());
        uriMainteWorkInfo.setU05NebikiKin2(uriMainteInfo.getNebikiKin2());
        uriMainteWorkInfo.setU06NebikiTax2(uriMainteInfo.getNebikiTax2());
        uriMainteWorkInfo.setU07NebikiKen3(uriMainteInfo.getNebikiKen3());
        uriMainteWorkInfo.setU08NebikiKin3(uriMainteInfo.getNebikiKin3());
        uriMainteWorkInfo.setU09NebikiTax3(uriMainteInfo.getNebikiTax3());
        uriMainteWorkInfo.setU10NebikiKen4(uriMainteInfo.getNebikiKen4());
        uriMainteWorkInfo.setU11NebikiKin4(uriMainteInfo.getNebikiKin4());
        uriMainteWorkInfo.setU12NebikiTax4(uriMainteInfo.getNebikiTax4());
        uriMainteWorkInfo.setU13NebikiKen5(uriMainteInfo.getNebikiKen5());
        uriMainteWorkInfo.setU14NebikiKin5(uriMainteInfo.getNebikiKin5());
        uriMainteWorkInfo.setU15NebikiTax5(uriMainteInfo.getNebikiTax5());
        uriMainteWorkInfo.setU16NebikiKen6(uriMainteInfo.getNebikiKen6());
        uriMainteWorkInfo.setU17NebikiKin6(uriMainteInfo.getNebikiKin6());
        uriMainteWorkInfo.setU18NebikiTax6(uriMainteInfo.getNebikiTax6());
        uriMainteWorkInfo.setU19NebikiKen7(uriMainteInfo.getNebikiKen7());
        uriMainteWorkInfo.setU20NebikiKin7(uriMainteInfo.getNebikiKin7());
        uriMainteWorkInfo.setU21NebikiTax7(uriMainteInfo.getNebikiTax7());
        uriMainteWorkInfo.setU22NebikiKen8(uriMainteInfo.getNebikiKen8());
        uriMainteWorkInfo.setU23NebikiKin8(uriMainteInfo.getNebikiKin8());
        uriMainteWorkInfo.setU24NebikiTax8(uriMainteInfo.getNebikiTax8());
        uriMainteWorkInfo.setU25NebikiKen9(uriMainteInfo.getNebikiKen9());
        uriMainteWorkInfo.setU26NebikiKin9(uriMainteInfo.getNebikiKin9());
        uriMainteWorkInfo.setU27NebikiTax9(uriMainteInfo.getNebikiTax9());
        
            
    }

    /**
     * 売上情報リストを生成する
     * @return
     */
    public static void createUriMainteList(List listUri) {
        
        //合計レコード削除
        if(!UriMaintenanceCommon.isNull(listUri)) {
            if(listUri.size()>=1) {
                int lastIndex = listUri.size()-1;
                UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo)listUri.get(lastIndex);
                if(entity.isTotalFlg()) {
                    listUri.remove(lastIndex);
                }
            }
        }
        
        UIUriMainteWorkInfo totalRec = new UIUriMainteWorkInfo();
        totalRec.setTotalFlg(true);
        
        for (Iterator ite = listUri.iterator(); ite.hasNext();) {
            UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo) ite.next();
            
            
            //値引金計(値引区分1～9金額)
            BigDecimal nebikiKinKei = new BigDecimal(0);
            nebikiKinKei = entity.getDecU02NebikiKin1().add(entity.getDecU05NebikiKin2()).add(entity.getDecU08NebikiKin3())
                .add(entity.getDecU11NebikiKin4()).add(entity.getDecU14NebikiKin5()).add(entity.getDecU17NebikiKin6())
                .add(entity.getDecU20NebikiKin7()).add(entity.getDecU23NebikiKin8()).add(entity.getDecU26NebikiKin9());
            entity.setDecU10NebikiKin(nebikiKinKei);
            
            //値引税計(値引区分1～9消費税)
//            BigDecimal nebikiTaxKei = new BigDecimal(0);
//            nebikiTaxKei = entity.getDecU03NebikiTax1().add(entity.getDecU06NebikiTax2()).add(entity.getDecU09NebikiTax3())
//                .add(entity.getDecU12NebikiTax4()).add(entity.getDecU15NebikiTax5()).add(entity.getDecU18NebikiTax6())
//                .add(entity.getDecU21NebikiTax7()).add(entity.getDecU24NebikiTax8()).add(entity.getDecU27NebikiTax9());
            
            //値引件数(値引区分1～9件数)
            BigDecimal nebikiKinKen = new BigDecimal(0);
            nebikiKinKen = entity.getDecU01NebikiKen1().add(entity.getDecU04NebikiKen2()).add(entity.getDecU07NebikiKen3())
            .add(entity.getDecU10NebikiKen4()).add(entity.getDecU13NebikiKen5()).add(entity.getDecU16NebikiKen6())
            .add(entity.getDecU19NebikiKen7()).add(entity.getDecU22NebikiKen8()).add(entity.getDecU25NebikiKen9());
            entity.setDecU09NebikiKen(nebikiKinKen);

            //---2007/11/21 update start 「値引計＝値引区分1～9金額」に変更
//            //値引計(値引区分1～9金額＋消費税)
//            BigDecimal nebikiKei = new BigDecimal(0);
//            nebikiKei = nebikiKinKei.add(nebikiTaxKei);
//            entity.setDecU04Nebiki(nebikiKei);
            //値引計＝値引区分1～9金額
            BigDecimal nebikiKei = nebikiKinKei;
            entity.setDecU04Nebiki(nebikiKei);
            //---2007/11/21 update end
            
            //値引後売上
            BigDecimal nebikigoUriage = new BigDecimal(0);
            nebikigoUriage = entity.getDecU01Uriage().subtract(nebikiKei);
            entity.setDecU05Nebikigo(nebikigoUriage);
            
            
            
            //会計計(会計区分2～11金額)
            BigDecimal kaikeiKei = new BigDecimal(0);
            kaikeiKei = entity.getDecU16KaikeiKin2().add(entity.getDecU18KaikeiKin3()).add(entity.getDecU20KaikeiKin4()).add(entity.getDecU22KaikeiKin5())
                            .add(entity.getDecU24KaikeiKin6()).add(entity.getDecU26KaikeiKin7()).add(entity.getDecU28KaikeiKin8())
                            .add(entity.getDecU30KaikeiKin9()).add(entity.getDecU32KaikeiKin10()).add(entity.getDecU34KaikeiKin11());
            
            //チケット計(チケット販売1～15金額)
            BigDecimal ticketKei = new BigDecimal(0);
            ticketKei = entity.getDecU36TieketKin1().add(entity.getDecU38TieketKin2()).add(entity.getDecU40TieketKin3())
                            .add(entity.getDecU42TieketKin4()).add(entity.getDecU44TieketKin5()).add(entity.getDecU46TieketKin6())
                            .add(entity.getDecU48TieketKin7()).add(entity.getDecU50TieketKin8()).add(entity.getDecU52TieketKin9())
                            .add(entity.getDecU54TieketKin10()).add(entity.getDecU56TieketKin11()).add(entity.getDecU58TieketKin12())
                            .add(entity.getDecU60TieketKin13()).add(entity.getDecU62TieketKin14()).add(entity.getDecU64TieketKin15());
            
            
            
            //現金金額算出 = 売上高 - 値引計 + 消費税 + 会計計 + チケット計
            BigDecimal kingaku = new BigDecimal(0);
            kingaku = entity.getDecU01Uriage().subtract(nebikiKei).add(entity.getDecU06Tax()).subtract(kaikeiKei).add(ticketKei);
            entity.setDecU14GenkinKin(kingaku);
            
            //現金過不足算出 = 現金金額 - 実現金在高
            BigDecimal kafusoku = new BigDecimal(0);
            //---2007/04/25 update kusama 現金過不足の算出式修正
            //kafusoku = kingaku.subtract(entity.getDecU68AridakaJitu());
            kafusoku = entity.getDecU68AridakaJitu().subtract(kingaku);
            entity.setDecGenkinKafusoku(kafusoku);
            
            
            
            //過剰金額(現金金額－実現金在高＞0の場合の値)
            //不足金額(現金金額－実現金在高＜0の場合の値)
            BigDecimal kajyoKin = new BigDecimal(0);
            BigDecimal fusokuKin = new BigDecimal(0);
            //---2007/04/25 update kusama 現金過不足の算出式修正
            //BigDecimal kajyoFusokuKin = kingaku.subtract(entity.getDecU68AridakaJitu());
            BigDecimal kajyoFusokuKin = entity.getDecU68AridakaJitu().subtract(kingaku);
            if(kajyoFusokuKin.compareTo(new BigDecimal(0)) > 0){
                kajyoKin = kajyoFusokuKin;
            }
            else {
                fusokuKin = kajyoFusokuKin;
            }
            entity.setDecU69Kajou(kajyoKin);
            entity.setDecU70Fusoku(fusokuKin.abs());
            
            
            //各項目加算
            totalRec.setDecU01Uriage(totalRec.getDecU01Uriage().add(entity.getDecU01Uriage()));
            totalRec.setDecU02MenuUri(totalRec.getDecU02MenuUri().add(entity.getDecU02MenuUri()));
            totalRec.setDecU03BuppanUri(totalRec.getDecU03BuppanUri().add(entity.getDecU03BuppanUri()));
            //totalRec.setDecU04Nebiki(totalRec.getDecU04Nebiki().add(entity.getDecU04Nebiki()));          //値引計　※画面非表示
            //totalRec.setDecU05Nebikigo(totalRec.getDecU05Nebikigo().add(entity.getDecU05Nebikigo()));    //値引後売上　※画面非表示
            totalRec.setDecU06Tax(totalRec.getDecU06Tax().add(entity.getDecU06Tax()));
            totalRec.setDecU07MinusKen(totalRec.getDecU07MinusKen().add(entity.getDecU07MinusKen()));
            totalRec.setDecU08MinusKin(totalRec.getDecU08MinusKin().add(entity.getDecU08MinusKin()));
            //totalRec.setDecU09NebikiKen(totalRec.getDecU09NebikiKen().add(entity.getDecU09NebikiKen()));  //値引件数　※画面非表示
            //totalRec.setDecU10NebikiKin(totalRec.getDecU10NebikiKin().add(entity.getDecU10NebikiKin()));  //値引金額　※画面非表示
            totalRec.setDecU11PNebikiKen(totalRec.getDecU11PNebikiKen().add(entity.getDecU11PNebikiKen()));
            totalRec.setDecU12PNebikiKin(totalRec.getDecU12PNebikiKin().add(entity.getDecU12PNebikiKin()));
            totalRec.setDecU13GenkinKen(totalRec.getDecU13GenkinKen().add(entity.getDecU13GenkinKen()));
            totalRec.setDecU14GenkinKin(totalRec.getDecU14GenkinKin().add(entity.getDecU14GenkinKin()));    //現金金額
            totalRec.setDecU15KaikeiKen2(totalRec.getDecU15KaikeiKen2().add(entity.getDecU15KaikeiKen2()));
            totalRec.setDecU16KaikeiKin2(totalRec.getDecU16KaikeiKin2().add(entity.getDecU16KaikeiKin2()));
            totalRec.setDecU17KaikeiKen3(totalRec.getDecU17KaikeiKen3().add(entity.getDecU17KaikeiKen3()));
            totalRec.setDecU18KaikeiKin3(totalRec.getDecU18KaikeiKin3().add(entity.getDecU18KaikeiKin3()));
            totalRec.setDecU19KaikeiKen4(totalRec.getDecU19KaikeiKen4().add(entity.getDecU19KaikeiKen4()));
            totalRec.setDecU20KaikeiKin4(totalRec.getDecU20KaikeiKin4().add(entity.getDecU20KaikeiKin4()));
            totalRec.setDecU21KaikeiKen5(totalRec.getDecU21KaikeiKen5().add(entity.getDecU21KaikeiKen5()));
            totalRec.setDecU22KaikeiKin5(totalRec.getDecU22KaikeiKin5().add(entity.getDecU22KaikeiKin5()));
            totalRec.setDecU23KaikeiKen6(totalRec.getDecU23KaikeiKen6().add(entity.getDecU23KaikeiKen6()));
            totalRec.setDecU24KaikeiKin6(totalRec.getDecU24KaikeiKin6().add(entity.getDecU24KaikeiKin6()));
            totalRec.setDecU25KaikeiKen7(totalRec.getDecU25KaikeiKen7().add(entity.getDecU25KaikeiKen7()));
            totalRec.setDecU26KaikeiKin7(totalRec.getDecU26KaikeiKin7().add(entity.getDecU26KaikeiKin7()));
            totalRec.setDecU27KaikeiKen8(totalRec.getDecU27KaikeiKen8().add(entity.getDecU27KaikeiKen8()));
            totalRec.setDecU28KaikeiKin8(totalRec.getDecU28KaikeiKin8().add(entity.getDecU28KaikeiKin8()));
            totalRec.setDecU29KaikeiKen9(totalRec.getDecU29KaikeiKen9().add(entity.getDecU29KaikeiKen9()));
            totalRec.setDecU30KaikeiKin9(totalRec.getDecU30KaikeiKin9().add(entity.getDecU30KaikeiKin9()));
            totalRec.setDecU31KaikeiKen10(totalRec.getDecU31KaikeiKen10().add(entity.getDecU31KaikeiKen10()));
            totalRec.setDecU32KaikeiKin10(totalRec.getDecU32KaikeiKin10().add(entity.getDecU32KaikeiKin10()));
            totalRec.setDecU33KaikeiKen11(totalRec.getDecU33KaikeiKen11().add(entity.getDecU33KaikeiKen11()));
            totalRec.setDecU34KaikeiKin11(totalRec.getDecU34KaikeiKin11().add(entity.getDecU34KaikeiKin11()));
            totalRec.setDecU35TieketKen1(totalRec.getDecU35TieketKen1().add(entity.getDecU35TieketKen1()));
            totalRec.setDecU36TieketKin1(totalRec.getDecU36TieketKin1().add(entity.getDecU36TieketKin1()));
            totalRec.setDecU37TieketKen2(totalRec.getDecU37TieketKen2().add(entity.getDecU37TieketKen2()));
            totalRec.setDecU38TieketKin2(totalRec.getDecU38TieketKin2().add(entity.getDecU38TieketKin2()));
            totalRec.setDecU39TieketKen3(totalRec.getDecU39TieketKen3().add(entity.getDecU39TieketKen3()));
            totalRec.setDecU40TieketKin3(totalRec.getDecU40TieketKin3().add(entity.getDecU40TieketKin3()));
            totalRec.setDecU41TieketKen4(totalRec.getDecU41TieketKen4().add(entity.getDecU41TieketKen4()));
            totalRec.setDecU42TieketKin4(totalRec.getDecU42TieketKin4().add(entity.getDecU42TieketKin4()));
            totalRec.setDecU43TieketKen5(totalRec.getDecU43TieketKen5().add(entity.getDecU43TieketKen5()));
            totalRec.setDecU44TieketKin5(totalRec.getDecU44TieketKin5().add(entity.getDecU44TieketKin5()));
            totalRec.setDecU45TieketKen6(totalRec.getDecU45TieketKen6().add(entity.getDecU45TieketKen6()));
            totalRec.setDecU46TieketKin6(totalRec.getDecU46TieketKin6().add(entity.getDecU46TieketKin6()));
            totalRec.setDecU47TieketKen7(totalRec.getDecU47TieketKen7().add(entity.getDecU47TieketKen7()));
            totalRec.setDecU48TieketKin7(totalRec.getDecU48TieketKin7().add(entity.getDecU48TieketKin7()));
            totalRec.setDecU49TieketKen8(totalRec.getDecU49TieketKen8().add(entity.getDecU49TieketKen8()));
            totalRec.setDecU50TieketKin8(totalRec.getDecU50TieketKin8().add(entity.getDecU50TieketKin8()));
            totalRec.setDecU51TieketKen9(totalRec.getDecU51TieketKen9().add(entity.getDecU51TieketKen9()));
            totalRec.setDecU52TieketKin9(totalRec.getDecU52TieketKin9().add(entity.getDecU52TieketKin9()));
            totalRec.setDecU53TieketKen10(totalRec.getDecU53TieketKen10().add(entity.getDecU53TieketKen10()));
            totalRec.setDecU54TieketKin10(totalRec.getDecU54TieketKin10().add(entity.getDecU54TieketKin10()));
            totalRec.setDecU55TieketKen11(totalRec.getDecU55TieketKen11().add(entity.getDecU55TieketKen11()));
            totalRec.setDecU56TieketKin11(totalRec.getDecU56TieketKin11().add(entity.getDecU56TieketKin11()));
            totalRec.setDecU57TieketKen12(totalRec.getDecU57TieketKen12().add(entity.getDecU57TieketKen12()));
            totalRec.setDecU58TieketKin12(totalRec.getDecU58TieketKin12().add(entity.getDecU58TieketKin12()));
            totalRec.setDecU59TieketKen13(totalRec.getDecU59TieketKen13().add(entity.getDecU59TieketKen13()));
            totalRec.setDecU60TieketKin13(totalRec.getDecU60TieketKin13().add(entity.getDecU60TieketKin13()));
            totalRec.setDecU61TieketKen14(totalRec.getDecU61TieketKen14().add(entity.getDecU61TieketKen14()));
            totalRec.setDecU62TieketKin14(totalRec.getDecU62TieketKin14().add(entity.getDecU62TieketKin14()));
            totalRec.setDecU63TieketKen15(totalRec.getDecU63TieketKen15().add(entity.getDecU63TieketKen15()));
            totalRec.setDecU64TieketKin15(totalRec.getDecU64TieketKin15().add(entity.getDecU64TieketKin15()));
            totalRec.setDecU65Nyukin(totalRec.getDecU65Nyukin().add(entity.getDecU65Nyukin()));
            totalRec.setDecU66Shukin(totalRec.getDecU66Shukin().add(entity.getDecU66Shukin()));
            totalRec.setDecU67AridakaCal(totalRec.getDecU67AridakaCal().add(entity.getDecU67AridakaCal()));
            totalRec.setDecU68AridakaJitu(totalRec.getDecU68AridakaJitu().add(entity.getDecU68AridakaJitu()));
            //totalRec.setDecU69Kajou(totalRec.getDecU69Kajou().add(entity.getDecU69Kajou()));                   //過剰金額　※画面非表示
            //totalRec.setDecU70Fusoku(totalRec.getDecU70Fusoku().add(entity.getDecU70Fusoku()));                //不足金額　※画面非表示
            totalRec.setDecU71CanKen(totalRec.getDecU71CanKen().add(entity.getDecU71CanKen()));
            totalRec.setDecU72CanKin(totalRec.getDecU72CanKin().add(entity.getDecU72CanKin()));
            totalRec.setDecU73ChengeCnt(totalRec.getDecU73ChengeCnt().add(entity.getDecU73ChengeCnt()));
            totalRec.setDecU74Kyakusu(totalRec.getDecU74Kyakusu().add(entity.getDecU74Kyakusu()));
            totalRec.setDecU75AllcanKen(totalRec.getDecU75AllcanKen().add(entity.getDecU75AllcanKen()));
            totalRec.setDecU76AllcanKin(totalRec.getDecU76AllcanKin().add(entity.getDecU76AllcanKin()));
            totalRec.setDecU01NebikiKen1(totalRec.getDecU01NebikiKen1().add(entity.getDecU01NebikiKen1()));
            totalRec.setDecU02NebikiKin1(totalRec.getDecU02NebikiKin1().add(entity.getDecU02NebikiKin1()));
            totalRec.setDecU03NebikiTax1(totalRec.getDecU03NebikiTax1().add(entity.getDecU03NebikiTax1()));
            totalRec.setDecU04NebikiKen2(totalRec.getDecU04NebikiKen2().add(entity.getDecU04NebikiKen2()));
            totalRec.setDecU05NebikiKin2(totalRec.getDecU05NebikiKin2().add(entity.getDecU05NebikiKin2()));
            totalRec.setDecU06NebikiTax2(totalRec.getDecU06NebikiTax2().add(entity.getDecU06NebikiTax2()));
            totalRec.setDecU07NebikiKen3(totalRec.getDecU07NebikiKen3().add(entity.getDecU07NebikiKen3()));
            totalRec.setDecU08NebikiKin3(totalRec.getDecU08NebikiKin3().add(entity.getDecU08NebikiKin3()));
            totalRec.setDecU09NebikiTax3(totalRec.getDecU09NebikiTax3().add(entity.getDecU09NebikiTax3()));
            totalRec.setDecU10NebikiKen4(totalRec.getDecU10NebikiKen4().add(entity.getDecU10NebikiKen4()));
            totalRec.setDecU11NebikiKin4(totalRec.getDecU11NebikiKin4().add(entity.getDecU11NebikiKin4()));
            totalRec.setDecU12NebikiTax4(totalRec.getDecU12NebikiTax4().add(entity.getDecU12NebikiTax4()));
            totalRec.setDecU13NebikiKen5(totalRec.getDecU13NebikiKen5().add(entity.getDecU13NebikiKen5()));
            totalRec.setDecU14NebikiKin5(totalRec.getDecU14NebikiKin5().add(entity.getDecU14NebikiKin5()));
            totalRec.setDecU15NebikiTax5(totalRec.getDecU15NebikiTax5().add(entity.getDecU15NebikiTax5()));
            totalRec.setDecU16NebikiKen6(totalRec.getDecU16NebikiKen6().add(entity.getDecU16NebikiKen6()));
            totalRec.setDecU17NebikiKin6(totalRec.getDecU17NebikiKin6().add(entity.getDecU17NebikiKin6()));
            totalRec.setDecU18NebikiTax6(totalRec.getDecU18NebikiTax6().add(entity.getDecU18NebikiTax6()));
            totalRec.setDecU19NebikiKen7(totalRec.getDecU19NebikiKen7().add(entity.getDecU19NebikiKen7()));
            totalRec.setDecU20NebikiKin7(totalRec.getDecU20NebikiKin7().add(entity.getDecU20NebikiKin7()));
            totalRec.setDecU21NebikiTax7(totalRec.getDecU21NebikiTax7().add(entity.getDecU21NebikiTax7()));
            totalRec.setDecU22NebikiKen8(totalRec.getDecU22NebikiKen8().add(entity.getDecU22NebikiKen8()));
            totalRec.setDecU23NebikiKin8(totalRec.getDecU23NebikiKin8().add(entity.getDecU23NebikiKin8()));
            totalRec.setDecU24NebikiTax8(totalRec.getDecU24NebikiTax8().add(entity.getDecU24NebikiTax8()));
            totalRec.setDecU25NebikiKen9(totalRec.getDecU25NebikiKen9().add(entity.getDecU25NebikiKen9()));
            totalRec.setDecU26NebikiKin9(totalRec.getDecU26NebikiKin9().add(entity.getDecU26NebikiKin9()));
            totalRec.setDecU27NebikiTax9(totalRec.getDecU27NebikiTax9().add(entity.getDecU27NebikiTax9()));
            totalRec.setDecGenkinKafusoku(totalRec.getDecGenkinKafusoku().add(entity.getDecGenkinKafusoku()));  //現金過不足
        }
        
        
        //新規合計レコード追加
        listUri.add(totalRec);
        
    }

}