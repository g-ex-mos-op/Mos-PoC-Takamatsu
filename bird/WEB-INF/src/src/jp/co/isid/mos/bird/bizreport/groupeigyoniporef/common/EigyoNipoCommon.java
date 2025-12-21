package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.dto.ResultDto;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnSibuKyakusuNipoRelate;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnSibuUriageNipoRelate;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.entity.TrnUriageNipoInfo;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 営業日報 共通クラス
 * 
 * @author xjung
 */
public class EigyoNipoCommon {

    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }

    /**
     * 渡された文字列がNullの場合、空白("”)に変換する
     * @param str 文字列
     * @return String パラメータがNULLの場合空白("")
     */
    public static String setEmpty(String str) {
        return (str == null || str.trim().length() == 0) ?
                EigyoNipoConstants.EMPTY : str.trim();
    }

    /**
     * 合計区分を取得する
     * @param trClass 行CSSクラス名
     * @return String 合計区分
     */
    public static String getSumKbn(String trClass) {
        
        if (trClass == null || NipoRefConstants.CSS_TR_CLASS.equals(trClass)) {
			return TaishoJoken.CODE_SIBU;
	    } else if (NipoRefConstants.CSS_TR_CLASS_SLAREA.equals(trClass)) {
	    	return TaishoJoken.CODE_SLAREA;
	    } else if (NipoRefConstants.CSS_TR_CLASS_JIGYOU.equals(trClass)) {
	    	return TaishoJoken.CODE_JIGYOU;
	    } else if (NipoRefConstants.CSS_TR_CLASS_HONBU.equals(trClass)) {
	    	return TaishoJoken.CODE_HONBU;	    
        } else if (NipoRefConstants.CSS_TR_CLASS_SEGMENT.equals(trClass)) {
            return TaishoJoken.CODE_SEGMENT;
        }  else if (NipoRefConstants.CSS_TR_CLASS_TOTAL.equals(trClass)) {
	    	return TaishoJoken.CODE_ALL;
	    }
		return EigyoNipoConstants.EMPTY;
    }


    /**
     * 通貨換算支部別処理
     * 
     * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
     * @param codCompany
     * @param resultDto
     * @return
     */
    public static ResultDto changeCurrency(
    		NipoRefConditionParameterDto parameterDto, ResultDto resultDto)
    {
    	//1.日報共通DTO【Request情報】を生成し、変数DTO【日本円換算結果】とします。
    	ResultDto changeResultDto = new ResultDto();
    	//2.DTO【結果情報】.List[[売上リスト]]にデータが存在する場合、下記の処理を行います。
    	if(resultDto.getUriageList() != null && !resultDto.getUriageList().isEmpty()) {
	    	CodCompany codCompany = parameterDto.getCodCompany();
	        //日報共通DTO【結果条件】.CodCompany[会社].為替相場を取得します。
        	BigDecimal decRate = codCompany.getRate();
        	//DTO【結果情報】.List[[売上リスト]]に格納されているエンティティが[支部一覧用]の場合、下記の処理を行います。
        	if (resultDto.getUriageList().get(0) instanceof TrnSibuUriageNipoRelate) {
	        	//DTO【結果情報】.List[[売上リスト]]の件数分、下記の処理を行います。
        		//外貨値をCodCompany[管理会社情報].為替相場で円換算を行い、
	        	changeResultDto.setUriageList(
	        			changeCurrencyUriage(resultDto.getUriageList(), decRate, false));
	        	//客数
	        	changeResultDto.setKyakusuList(
	        			changeCurrencyKyakusu(changeResultDto.getUriageList()
	        					, resultDto.getKyakusuList(), decRate, false));
	        	if(resultDto.isSearchTypeSibuList() && parameterDto.isAveDispFlg()) {
		            //売上・客数平均データ
	            	changeResultDto.setUriAvgList(
	            			changeCurrencyUriage(resultDto.getUriAvgList(), decRate, true));
	            	changeResultDto.setKyakuAvgList(
	            			changeCurrencyKyakusu(changeResultDto.getUriAvgList()
	            					, resultDto.getKyakuAvgList(), decRate, true));
	        	}
        	}
        	//DTO【結果情報】.List[[売上リスト]]に格納されているエンティティが[支部一覧用]以外の場合、下記の処理を行います。
        	else {
	        	//売上
	        	changeResultDto.setUriageList(
	        			changeCurrencyUriageMise(resultDto.getUriageList(), decRate, false));
	        	//客数
	        	changeResultDto.setKyakusuList(
	        			changeCurrencyKyakusuMise(changeResultDto.getUriageList()
	        					, resultDto.getKyakusuList(), decRate, false));
        	}
    	}
        changeResultDto.setTenpoCount(resultDto.getTenpoCount());
    	//変数DTO【日本円換算結果】.該当データ存在判断フラグへDTO【検索結果】.該当データ存在判断フラグを設定します。
        changeResultDto.setExistDataFlg(resultDto.isExistDataFlg());
        //変数DTO【日本円換算結果】.換算フラグへDTO【結果条件】.換算フラグを設定します。
        changeResultDto.setKansanFlg(parameterDto.isKansan());
        //変数DTO【日本円換算結果】.検索一覧タイプへDTO【検索結果】.検索一覧タイプを設定します。
        changeResultDto.setSearchListType(resultDto.getSearchListType());
        //処理1の変数DTO【日本円換算結果】をリターンします。
    	return changeResultDto;
    }
    /**
     * 円換算換算処理を行います。
     * 外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
     * 
     * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
     * @param listResult
     * @param decRate　BigDecimal:CodCompany[管理会社情報].為替相場
     * @param isAvg 平均データか否か
     * @return
     */
    public static List changeCurrencyUriage(
    		List listResult, BigDecimal decRate, boolean isAvg)
    {
    	//変数List[[換算データ]]を生成します。
    	List listChanged = new ArrayList(0);
    	if(listResult == null || listResult.isEmpty()) {
    		return listChanged;
    	}
    	//売上データの場合
    	if (listResult.get(0) instanceof TrnSibuUriageNipoRelate) {
    		//List[[売上データ]]の件数分、下記の処理を行います。
            for(int i=0; i<listResult.size(); i++) {
            	//変数[日報情報]としてList[[売上データ]]から現行Entityを取得します。
				TrnSibuUriageNipoRelate eUriage = (TrnSibuUriageNipoRelate) listResult.get(i);
				//変数[日報情報]のクローン[日報情報]を生成します。
	        	TrnSibuUriageNipoRelate eChanged = cloneEntityUriage(eUriage);
	        	//クローン[日報情報].売上へ[変数[日報情報].売上×CodCompany[管理会社情報].為替相場＋0.5]少数点以下切捨値
	        	eChanged.setUriage(changeCurrency(eUriage.getUriage(),(decRate)));//売上
	        	//クローン[日報情報].前年実績へ[変数[日報情報].前年実績×CodCompany[管理会社情報].為替相場＋0.5]少数点以下切捨値
	        	eChanged.setZenUriage(changeCurrency(eUriage.getZenUriage(),decRate));//前年売上
	        	//クローン[日報情報].予算へ[変数[日報情報].予算×CodCompany[管理会社情報].為替相場＋0.5]少数点以下切捨値
	        	eChanged.setYosan(changeCurrency(eUriage.getYosan(),decRate));//予算
	        	//クローン[日報情報].オーナー予算へ[変数[日報情報].オーナー予算×CodCompany[管理会社情報].為替相場＋0.5]少数点以下切捨値
	        	eChanged.setOnerYosan(changeCurrency(eUriage.getOnerYosan(),decRate));//予算
	        	if(isAvg) {
	        		//平均値表示の場合は、予算差＝売上－予算を算出
	        		eChanged.setTasseiYosan(eChanged.getUriage().subtract(eChanged.getYosan()));
	        		//平均値表示の場合は、前年売上差＝売上－前年売上を算出
	        		eChanged.setZenHiSa(eChanged.getUriage().subtract(eChanged.getZenUriage()));
	        	}
	        	//List[[換算データ]]へクローン[日報情報]を格納します。
	        	listChanged.add(eChanged);
            }
		}
    	//List[[換算データ]]をリターンします。
    	return listChanged;
    }
	/**
	 * 外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
	 * 
	 * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
	 * @param listUriage
	 * @param listKyakusu
	 * @param decRate　BigDecimal:CodCompany[管理会社情報].為替相場
	 * @param isAvg 平均データか否か
	 * @return
	 */
	public static List changeCurrencyKyakusu(
			List listUriage, List listKyakusu, BigDecimal decRate, boolean isAvg)
	{
    	//変数List[[換算データ]]を生成します。
		List listChanged = new ArrayList(0);
		if(listUriage == null || listUriage.isEmpty() 
				|| listKyakusu == null || listKyakusu.isEmpty()) {
			return listChanged;
		}
		//客数データの場合
        for(int i=0; i<listKyakusu.size(); i++) {
        	TrnSibuUriageNipoRelate eUriage = (TrnSibuUriageNipoRelate) listUriage.get(i);
			TrnSibuKyakusuNipoRelate eKyakusu = (TrnSibuKyakusuNipoRelate) listKyakusu.get(i);
			//換算対象Entityのクローンを生成します。
			TrnSibuKyakusuNipoRelate eChanged = cloneEntityKyakusu(eKyakusu);
			//客単価＝売上÷客数(小数点以下を四捨五入します。)
			eChanged.setKyakuTanka(
					Calculator.divide(eUriage.getUriage(), eKyakusu.getKyakusu()));
			listChanged.add(eChanged);
        }
		return listChanged;
	}
    /**
     * 売上情報クローン生成処理
     * 
     * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
     * @param nipoRelate
     * @return TrnSibuUriageNipoRelate
     */
    private static TrnSibuUriageNipoRelate cloneEntityUriage(TrnSibuUriageNipoRelate uriage) {
    	TrnSibuUriageNipoRelate eNewUriage = new TrnSibuUriageNipoRelate();
    	eNewUriage.setCompanyCd(uriage.getCompanyCd());
    	eNewUriage.setDispKbn(uriage.getDispKbn());
    	eNewUriage.setHonbuCd(uriage.getHonbuCd());
    	eNewUriage.setHonbuName(uriage.getHonbuName());
    	eNewUriage.setJigyoCd(uriage.getJigyoCd());
    	eNewUriage.setJigyoName(uriage.getJigyoName());
    	eNewUriage.setSlareaCd(uriage.getSlareaCd());
    	eNewUriage.setSlareaName(uriage.getSlareaName());
    	eNewUriage.setSibuCd(uriage.getSibuCd());
    	eNewUriage.setSibuName(uriage.getSibuName());
    	eNewUriage.setSibuNameClass(uriage.getSibuNameClass());
    	eNewUriage.setHoseiTenpoCnt(uriage.getHoseiTenpoCnt());
    	eNewUriage.setHoseiZenTenpoCnt(uriage.getHoseiZenTenpoCnt());
    	eNewUriage.setOnerYosan(uriage.getOnerYosan());
    	eNewUriage.setOnerYosanClass(uriage.getOnerYosanClass());
    	eNewUriage.setRClass(uriage.getRClass());
    	eNewUriage.setTasseiYosan(uriage.getTasseiYosan());
    	eNewUriage.setTasseiYosanClass(uriage.getTasseiYosanClass());
    	eNewUriage.setTenpoCount(uriage.getTenpoCount());
    	eNewUriage.setTenpoCountSlash(uriage.getTenpoCountSlash());
    	eNewUriage.setUriage(uriage.getUriage());
    	eNewUriage.setUriageClass(uriage.getUriageClass());
    	eNewUriage.setYosan(uriage.getYosan());
    	eNewUriage.setYosanClass(uriage.getYosanClass());
    	eNewUriage.setYosanMiseCnt(uriage.getYosanMiseCnt());
    	eNewUriage.setZenUriage(uriage.getZenUriage());
    	eNewUriage.setZenUriageClass(uriage.getZenUriageClass());
    	eNewUriage.setZenHiSa(uriage.getZenHiSa());
    	eNewUriage.setZenHiSaClass(uriage.getZenHiSaClass());
    	eNewUriage.setZenTenpoCount(uriage.getZenTenpoCount());
    	return eNewUriage;
    }
    /**
     * 客数情報クローン生成処理
     * 
     * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
     * @param nipoRelate
     * @return TrnSibuKyakusuNipoRelate
     */
    private static TrnSibuKyakusuNipoRelate cloneEntityKyakusu(TrnSibuKyakusuNipoRelate kyakusu) {
    	TrnSibuKyakusuNipoRelate eNewKayku = new TrnSibuKyakusuNipoRelate();
    	eNewKayku.setCompanyCd(kyakusu.getCompanyCd());
    	eNewKayku.setDispKbn(kyakusu.getDispKbn());
    	eNewKayku.setHonbuCd(kyakusu.getHonbuCd());
    	eNewKayku.setHonbuName(kyakusu.getHonbuName());
    	eNewKayku.setJigyoCd(kyakusu.getJigyoCd());
    	eNewKayku.setJigyoName(kyakusu.getJigyoName());
    	eNewKayku.setSlareaCd(kyakusu.getSlareaCd());
    	eNewKayku.setSlareaName(kyakusu.getSlareaName());
    	eNewKayku.setSibuCd(kyakusu.getSibuCd());
    	eNewKayku.setSibuName(kyakusu.getSibuName());
    	eNewKayku.setSibuNameClass(kyakusu.getSibuNameClass());
    	eNewKayku.setTenpoCount(kyakusu.getTenpoCount());
    	eNewKayku.setTenpoCountSlash(kyakusu.getTenpoCountSlash());
    	eNewKayku.setRClass(kyakusu.getRClass());
    	eNewKayku.setKyakusu(kyakusu.getKyakusu());
    	eNewKayku.setKyakusuClass(kyakusu.getKyakusuClass());
    	eNewKayku.setKyakuTanka(kyakusu.getKyakuTanka());
    	eNewKayku.setKyakuTankaClass(kyakusu.getKyakuTankaClass());
    	eNewKayku.setZenHi(kyakusu.getZenHi());
    	eNewKayku.setZenHiClass(kyakusu.getZenHiClass());
    	eNewKayku.setZenKyakusu(kyakusu.getZenKyakusu());
    	eNewKayku.setZenKyakusuClass(kyakusu.getZenKyakusuClass());
    	eNewKayku.setZenHiTanka(kyakusu.getZenHiTanka());
    	eNewKayku.setZenHiTankaClass(kyakusu.getZenHiTankaClass());
    	eNewKayku.setZenTenpoCount(kyakusu.getZenTenpoCount());
    	return eNewKayku;
    }
	/**
	 * 外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
	 * 
	 * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
	 * @param listUriage
	 * @param listKyakusu
	 * @param decRate　BigDecimal:CodCompany[管理会社情報].為替相場
	 * @param isAvg 平均データか否か
	 * @return
	 */
	public static List changeCurrency(
			List listResult, BigDecimal decRate, boolean isAvg)
	{
    	//変数List[[換算データ]]を生成します。
		List listChanged = new ArrayList(0);
		if(listResult == null || listResult.isEmpty() ) {
			return listChanged;
		}
		//客数データの場合
        for(int i=0; i<listResult.size(); i++) {
        	TrnUriageNipoInfo eResult = (TrnUriageNipoInfo) listResult.get(i);
			//換算対象Entityのクローンを生成します。
        	TrnUriageNipoInfo eChanged = cloneEntity(eResult);
        	eChanged.setUriage(changeCurrency(eResult.getUriage(),decRate));//売上
        	eChanged.setUriageZen(changeCurrency(eResult.getUriageZen(),decRate));//前年売上
        	eChanged.setYosan(changeCurrency(eResult.getYosan(),decRate));//予算
        	if(isAvg) {
        		//前年売上差＝売上－前年売上
        		eChanged.setZenHiUri(eChanged.getUriage().subtract(eChanged.getUriageZen()));
        	}
			//客単価＝売上÷客数(小数点以下を四捨五入します。)
			eChanged.setTanka(
					Calculator.divide(eChanged.getUriage(), eChanged.getKyakusu()));
			eChanged.setTankaZen(
					Calculator.divide(eChanged.getUriageZen(), eChanged.getKyakusuZen()));
			listChanged.add(eChanged);
        }
		return listChanged;
	}
    /**
     * 外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
     * 
     * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
     * @param listResult
     * @param decRate　BigDecimal:CodCompany[管理会社情報].為替相場
     * @param isAvg 平均データか否か
     * @return
     */
    public static List changeCurrencyUriageMise(
    		List listResult, BigDecimal decRate, boolean isAvg)
    {
    	//変数List[[換算データ]]を生成します。
    	List listChanged = new ArrayList(0);
    	if(listResult == null || listResult.isEmpty()) {
    		return listChanged;
    	}
    	//売上データの場合
        for(int i=0; i<listResult.size(); i++) {
        	TrnUriageNipoInfo eUriage = (TrnUriageNipoInfo) listResult.get(i);
			//換算対象Entityのクローンを生成します。
        	TrnUriageNipoInfo eChanged = cloneEntity(eUriage);
        	listChanged.add(eChanged);
        	eChanged.setYosan(changeCurrency(eUriage.getYosan(),decRate));//予算
        	//新店・その他予算計 
        	if(NipoRefConstants.CSS_TR_CLASS_SEGMENT.equals(eUriage.getRClass())) {
        		continue;
        	}
        	eChanged.setUriage(changeCurrency(eUriage.getUriage(),decRate));//売上
        	eChanged.setUriageZen(changeCurrency(eUriage.getUriageZen(),decRate));//前年売上
        	if(isAvg) {
        		//前年売上差＝売上－前年売上
        		eChanged.setZenHiUri(eChanged.getUriage().subtract(eChanged.getUriageZen()));
        	}
        }
    	return listChanged;
    }
	/**
	 * 外貨値をCodCompany[管理会社情報].為替相場で円換算を行います。
	 * 
	 * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
	 * @param listUriage
	 * @param listKyakusu
	 * @param decRate　BigDecimal:CodCompany[管理会社情報].為替相場
	 * @param isAvg 平均データか否か
	 * @return
	 */
	public static List changeCurrencyKyakusuMise(
			List listUriage, List listKyakusu, BigDecimal decRate, boolean isAvg)
	{
		List listChanged = new ArrayList(0);
		if(listUriage == null || listUriage.isEmpty() 
				|| listKyakusu == null || listKyakusu.isEmpty()) {
			return listChanged;
		}
		//客数データの場合
        for(int i=0; i<listKyakusu.size(); i++) {
        	TrnUriageNipoInfo eUriage = (TrnUriageNipoInfo) listUriage.get(i);
        	if(NipoRefConstants.CSS_TR_CLASS_SEGMENT.equals(eUriage.getRClass())) {
        		eUriage = (TrnUriageNipoInfo) listUriage.get((i+1));
        	}
        	TrnUriageNipoInfo eKyakusu = (TrnUriageNipoInfo) listKyakusu.get(i);
			//換算対象Entityのクローンを生成します。
        	TrnUriageNipoInfo eChanged = cloneEntity(eKyakusu);
			//客単価＝売上÷客数(小数点以下を四捨五入します。)
			eChanged.setTanka(
					Calculator.divide(eUriage.getUriage(), eKyakusu.getKyakusu()));
			eChanged.setTankaZen(
					Calculator.divide(eUriage.getUriageZen(), eKyakusu.getKyakusuZen()));
			listChanged.add(eChanged);
        }
		return listChanged;
	}

    /**
     * 売上情報クローン生成処理
     * 
     * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
     * @param nipoRelate
     * @return TrnUriageNipoInfo
     */
    private static TrnUriageNipoInfo cloneEntity(TrnUriageNipoInfo uriage) {
    	TrnUriageNipoInfo eNewUriage = new TrnUriageNipoInfo();
    	eNewUriage.setCompanyCd(uriage.getCompanyCd());
    	eNewUriage.setHonbuCd(uriage.getHonbuCd());
    	eNewUriage.setHonbuName(uriage.getHonbuName());
    	eNewUriage.setJigyoCd(uriage.getJigyoCd());
    	eNewUriage.setJigyoName(uriage.getJigyoName());
    	eNewUriage.setSlareaCd(uriage.getSlareaCd());
    	eNewUriage.setSlareaName(uriage.getSlareaName());
    	eNewUriage.setSibuCd(uriage.getSibuCd());
    	eNewUriage.setSibuName(uriage.getSibuName());
    	eNewUriage.setBlockCd(uriage.getBlockCd());
    	eNewUriage.setBlockName(uriage.getBlockName());
    	eNewUriage.setMiseCd(uriage.getMiseCd());
    	eNewUriage.setMiseNameKj(uriage.getMiseNameKj());
    	eNewUriage.setEigyoDays(uriage.getEigyoDays());
    	eNewUriage.setEigyoDaysZen(uriage.getEigyoDaysZen());
    	eNewUriage.setKbn1(uriage.getKbn1());
    	eNewUriage.setOpenKbn(uriage.getOpenKbn());
    	eNewUriage.setOpenKbnZen(uriage.getOpenKbnZen());
    	eNewUriage.setRClass(uriage.getRClass());
    	eNewUriage.setTenkoKbn(uriage.getTenkoKbn());
    	eNewUriage.setTenkoKbnZen(uriage.getTenkoKbnZen());
    	eNewUriage.setUriage(uriage.getUriage());
    	eNewUriage.setUriageZen(uriage.getUriageZen());
    	eNewUriage.setYosan(uriage.getYosan());
    	eNewUriage.setTassei(uriage.getTassei());
    	eNewUriage.setTasseiUriClass(uriage.getTasseiUriClass());
    	eNewUriage.setZenHiUri(uriage.getZenHiUri());
    	eNewUriage.setZenUriClass(uriage.getZenUriClass());
    	eNewUriage.setKyakusu(uriage.getKyakusu());
    	eNewUriage.setKyakusuZen(uriage.getKyakusuZen());
    	eNewUriage.setTanka(uriage.getTanka());
    	eNewUriage.setTankaZen(uriage.getTankaZen());
    	eNewUriage.setZenHiKyaku(uriage.getZenHiKyaku());
    	eNewUriage.setZenKyaClass(uriage.getZenKyaClass());
    	eNewUriage.setZenHiTanka(uriage.getZenHiTanka());
    	eNewUriage.setZenTanClass(uriage.getZenTanClass());
     	return eNewUriage;
    }
    /**
     * 掛算処理.
     * <P/>
     * 掛算処理を行います。<BR/>
     * 小数点以下を、四捨五入後切り捨てます。<BR/>
     * signum()[負値：-1   ゼロ：0    正値：1]
     * @return java.math.BigDecimal 計算結果
     * @param numerator java.math.BigDecimal 左辺
     * @param denominator java.math.BigDecimal 右辺
     * @param scale int 小数点以下の桁数
     */
    public static BigDecimal changeCurrency(BigDecimal numerator, BigDecimal denominator) {
        if (numerator == null || numerator.signum() == 0
        		|| denominator == null) {
        	return new BigDecimal("0");
        }
        NumericFormatter formatter = new NumericFormatter(false, "0", true);
        
        String formatedValue = formatter.format(numerator.multiply(denominator));
        return new BigDecimal(formatedValue);
    }
}