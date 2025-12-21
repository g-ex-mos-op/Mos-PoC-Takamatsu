/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.util;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.Kibetu;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionDto;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 営業日報系共通処理
 * 
 * 作成日:2013/01/11
 * @author xkinu
 *
 */
public class NipoRefUtil {
    /**
     * 画面表示デフォルトサブタグ区分を取得する
     * @param subTagKbn     サブタグ区分
     * @return String デフォルトサブタグ区分
     */
    public static String getDefaultSubTagKbn(String subTagKbn) {
        return BizReportConstants.SUB_TAG_1.equals(subTagKbn)
            || BizReportConstants.SUB_TAG_3.equals(subTagKbn) ?
            BizReportConstants.SUB_TAG_1 : BizReportConstants.SUB_TAG_0;
    }
    /**
     * 他画面遷移時事前処理
     * 
     * @param conditionDto　　日報共通DTO【条件部情報】
     * @param searchParamDto　日報共通DTO【検索条件】
     * @param resultParamDto　日報共通DTO【結果条件】
     * @param isNewWindow
     */
    public static void callScreenInitialize(NipoRefConditionDto conditionDto
    		, NipoRefConditionParameterDto searchParamDto, NipoRefConditionParameterDto resultParamDto
    		, boolean isNewWindow)
    {
        //１．別ウィンドウフラグがtrueの場合
        if (isNewWindow) {
            //日報共通DTO【条件部情報】.MAXウィンドウIDカウントアップし、MAXウィンドウIDを取得します。
            int newWindowId = conditionDto.updateWindowId();
            //DTO【検索条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            searchParamDto.setWindowId(newWindowId);
            //DTO【結果条件】.ウィンドウIDへ MAXウィンドウIDを設定します。
            resultParamDto.setWindowId(newWindowId);
        }
        //２．日報共通DTO【検索条件】.初期アクションフラグへtrueを設定します。
        searchParamDto.setInitActionFlg(true);
        resultParamDto.setLinkParams("");
    }
	/**
	 * 対象期間を取得する
	 * 
	 * @param dto 日報共通DTO【条件部情報】
	 * @return String 対象期間情報
	 */
	public static String getCsvTaishoKikan(NipoRefConditionParameterDto dto) {
		// 日付フォーマッタ
		DateFormatter df = new DateFormatter();
		// 対象期間情報
		StringBuffer kikan = new StringBuffer();

		// 対象期間
		kikan.append(TaishoKikan.getName(
			dto.getBirdUserInfo().getMstUser().getUserTypeCd(),
			dto.getTaishoKikanCd()));

		// 期日指定日報
		if (TaishoKikan.DAY1.equals(dto.getTaishoKikanCd())) {
			kikan.append(df.format(dto.getKikanNipo(),
				DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
			kikan.append("分");
		// 当月月報
		} else if (TaishoKikan.MONTHAPP.equals(dto.getTaishoKikanCd())) {
			// 処理無し
		// 月別月報告
		} else if (TaishoKikan.MONTH.equals(dto.getTaishoKikanCd())) {
			kikan.append(df.format(dto.getKikanYM(),
				DateFormatter.PATTERN_MONTH_SLASH, DateFormatter.DATE_TYPE_YM));
			kikan.append("分");
		// 期別期報
		} else if (TaishoKikan.KIBETU.equals(dto.getTaishoKikanCd())) {
			kikan.append(dto.getKikanYear());
			kikan.append("年");
			kikan.append(Kibetu.getName(dto.getKikanKibetu()));
		// 期間指定
		} else if (TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())) {
			kikan.append(df.format(dto.getKikanFrom(),
				DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
			kikan.append(" から ");
			kikan.append(df.format(dto.getKikanTo(),
				DateFormatter.PATTERN_SLASH, DateFormatter.DATE_TYPE_YMD));
			kikan.append("まで");
		}
		return kikan.toString();
	}
	/**
	 * 検索事前処理
	 * 
	 * @param conditionDto
	 * @param parameterDto
	 */
	public static void searchInitialize(
			NipoRefConditionDto conditionDto
			, NipoRefConditionParameterDto parameterDto) {
    	//１．日報共通DTO【結果条件】.CTRL【BIRD日付情報】へ日報共通DTO【条件部情報】.CTRL【BIRD日付情報】を設定します。
		parameterDto.setBirdDateInfo(conditionDto.getBirdDateInfo());
    	//２．日報共通DTO【結果条件】.CTRL【BIRDログインユーザー情報】へ日報共通DTO【条件部情報】.CTRL【BIRDログインユーザー情報】を設定します。
		parameterDto.setBirdUserInfo(conditionDto.getBirdUserInfo());
    	//３．日報共通DTO【結果条件】.当月月報へ日報共通DTO【条件部情報】.当月月報を設定します。
		parameterDto.setKikanCurrMonth(conditionDto.getKikanCurrMonth());
		//４．日報共通DTO【結果条件】.[会社情報]へ
		//    日報共通DTO【条件部情報】.List[[会社]]から対象会社コードのCodCompany[会社]を取得し設定します。
		parameterDto.setCodCompany(conditionDto.getEntityCodCompany(parameterDto));
		//５．日報共通DTO【結果条件】.会社名称へ
		//    日報共通DTO【条件部情報】.List[[会社]]から対象会社コードの名称を取得し設定します。
		parameterDto.setCompanyName(conditionDto.getCompanyName(parameterDto));
		if(parameterDto.isSvFlg()) {
		}
		//６．日報共通DTO【結果条件】.集計区分＝SV指定(担当店一覧)以外の場合
		else {
			//1.日報共通DTO【結果条件】.SVコードへ""(空)を設定します。
			parameterDto.setSvCd("");
		}
    	//７．処理終了
	}
	/**
	 * 検索事前処理
	 * 
	 * @param conditionDto
	 * @param parameterDto
	 * @param taishoJoken
	 */
	public static void searchInitialize(
			NipoRefConditionDto conditionDto
			, NipoRefConditionParameterDto parameterDto
			, String taishoJoken) {
		searchInitialize(conditionDto, parameterDto);
		parameterDto.setTaishoJoken(taishoJoken);
		//７．入力チェック
		searchValidate(parameterDto);
    	//８．処理終了
	}
	public static boolean equals(final String val1, final String val2) {
		if(CommonUtil.isNull(val1)!=CommonUtil.isNull(val2) ) return false;
		if(val1!=null) {
			if(!val1.equals(val2)) return false;
		}
		return true;
	}
    /**
     * 入力チェックをする
     * @param paramDto           条件部情報DTO
     * @param kikanFrom     対象期間From
     * @param kikanTo       対象期間To
     */
    public static void searchValidate(NipoRefConditionParameterDto paramDto) {
        if (paramDto == null) {
            throw new NotNullException("検索条件情報");
        }
        if (paramDto.getBirdUserInfo() == null) {
            throw new MissingDataException("ログインユーザー情報");
        }
        if (paramDto.getBirdUserInfo().getMstUser() == null) {
            throw new MissingDataException("ログインユーザー情報");
        }
        if (CommonUtil.isNull(paramDto.getCompanyCd())) {
            throw new NotNullException("会社");
        }
        //本部ユーザー用
        if(UserType.isHonbu(paramDto.getUserTypeCd())) {
        	validateHonbu(paramDto);
        }
        //オーナーユーザー用
        if(UserType.isOner(paramDto.getUserTypeCd())) {
        	validateOner(paramDto);
        }
    }
    /**
     * 対象期間・期間指定入力チェック
     * @param paramDto
     */
    private static void validateKikan(NipoRefConditionParameterDto paramDto) {
        if (CommonUtil.isNull(paramDto.getTaishoKikanCd())) {
            throw new NotNullException("対象期間");
        }
        boolean isDays = TaishoKikan.DAYS.equals(paramDto.getTaishoKikanCd());
        boolean isKibetu = TaishoKikan.KIBETU.equals(paramDto.getTaishoKikanCd());
        String kikanFrom = paramDto.getKikanSiteiFrom();
        String kikanTo = paramDto.getKikanSiteiTo();
        //期間指定(FROM or 年度)必須チェック
        if (CommonUtil.isNull(kikanFrom)) {
        	String msg = "期間指定";
        	if (isDays){
        		msg += "FROM";
        	}
        	if (isKibetu) {
        		msg += "年度";
        	}
            throw new NotNullException(msg);
        }
        //期間指定TO or 期 必須チェック
        if (TaishoKikan.DAYS.equals(paramDto.getTaishoKikanCd())
            || isKibetu) {
            if (CommonUtil.isNull(kikanTo)) {
            	String msg = "期間指定";
            	if (isDays){
            		msg += "TO";
            	}
            	if (isKibetu) {
            		msg += "期";
            	}
                throw new NotNullException(msg);
            }
            if (isDays) {
                if(!KikanSiteiUtil.validateDateFromTo(kikanFrom, kikanTo)) {
                    throw new ConstraintsViolationException("期間指定はFROM < TOで", EigyoNipoConstants.EMPTY);
                }

                if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
                    throw new NotRelevantException("期間", "９２日以内");
                }
            }
        }
    }

    /**
     * 本部用入力チェック
     * @param paramDto
     * @param searchType
     */
    private static void validateHonbu(NipoRefConditionParameterDto paramDto) {
        if (CommonUtil.isNull(paramDto.getTenpoShubetuCd())) {            
            throw new NotNullException("店舗種別");
        }
        if (CommonUtil.isNull(paramDto.getTaishoTenpoCd())) {
            throw new NotNullException("対象店舗");
        }
        if (CommonUtil.isNull(paramDto.getShukeiKbnCd())) {
            throw new NotNullException("集計区分");
        }
        /* 集計区分で『SV指定(担当店一覧)』が選択された場合 */
        if(paramDto.isSvFlg()){
            // SVコード必須チェック 
            if (CommonUtil.isNull(paramDto.getSvCd())) {
                throw new NotNullException("SVコード");
            }
        }
        //支部(店舗一覧)検索の場合
        else if(TaishoJoken.CODE_SIBU.equals(paramDto.getTaishoJoken())) {
        	if(!paramDto.isSvFlg()) {
        		//支部コードの必須チェックを行います。
                if (CommonUtil.isNull(paramDto.getSibuCd())) {
                    throw new NotNullException("対象支部");
                }
            }
        	
        }
        //対象期間・期間指定入力チェック
        validateKikan(paramDto);
        if (CommonUtil.isNull(paramDto.getZennenDataShubetu())) {
            throw new NotNullException("前年データ種別");
        }
    }

    /**
     * オーナー用入力チェック
     * @param paramDto
     */
    private static void validateOner(NipoRefConditionParameterDto paramDto) {
        List ownerList = paramDto.getBirdUserInfo().getUserOner();
        // オーナーコード取得            
        String onerCd = null;
        for (Iterator it = ownerList.iterator(); it.hasNext();) {
            UIUserOner uIUserOner = (UIUserOner) it.next();
            if (paramDto.getCompanyCd().equals(uIUserOner.getCompanyCd())) {
                onerCd = uIUserOner.getOnerCd();
                break;
            }
        }
        if (CommonUtil.isNull(onerCd)) {
            throw new NotExistException("オーナーコード");
        }
        if (CommonUtil.isNull(paramDto.getZennenDataShubetu())) {
            throw new NotNullException("前年データ種別");
        }
    }
    /**
     * 天候区分名称を返します。
     * @return
     */
    public static String getTenkoKbnLabel(BigDecimal kbn) {
    	String label = "";
        if (kbn == null) {
        	return label;
        }
        String strKbn = kbn.toString();
        if ("1".equals(strKbn)) {
        	label = "晴";
        }
        else if ("2".equals(strKbn)) {
        	label = "曇";
        }
        else if ("3".equals(strKbn)) {
        	label = "雨";
        }
        else if ("4".equals(strKbn)) {
        	label = "雪";
        }
        else if ("5".equals(strKbn)) {
        	label = "嵐";
        }
        return label;
    }
    /**
     * 前年対象区分(日本語1文字)を取得します。
     * @return
     */
    public static String getTenpoShubetuKbnLabel(String kbn) {
        
        String label = "";
        if ("1".equals(kbn)) {
            label = "前";
        } else if ("2".equals(kbn)) {
            label = "予";
        } else if ("3".equals(kbn)) {
            label = "新";
        }
        
        return label;
    }
    /**
     * 渡された文字列がNullの場合、空白("”)に変換する
     * @param str 文字列
     * @return String パラメータがNULLの場合空白("")
     */
    public static String setEmpty(String str) {
        return CommonUtil.isNull(str) ? "" : str.trim();
    }
    /**
     * CSSクラス取得処理
     * @param taishoJoken
     * @return
     */
    public static String getCssClass(String taishoJoken) {
    	if(TaishoJoken.CODE_MISE.equals(taishoJoken)
    			|| TaishoJoken.CODE_MISE.equals(taishoJoken))
    	{
    		return NipoRefConstants.CSS_TR_CLASS;
    	}
    	if(TaishoJoken.CODE_SLAREA.equals(taishoJoken)) {
    		/** 行CSS：エリア計 */
    		return NipoRefConstants.CSS_TR_CLASS_SLAREA;
    	}
    	if(TaishoJoken.CODE_JIGYOU.equals(taishoJoken)) {
    		/** 行CSS：事業本部計 */
    		return NipoRefConstants.CSS_TR_CLASS_JIGYOU;
    	}
    	if(TaishoJoken.CODE_HONBU.equals(taishoJoken)) {
    		/** 行CSS：本部計 */
    		return NipoRefConstants.CSS_TR_CLASS_HONBU;
    	}
    	if(TaishoJoken.CODE_SEGMENT.equals(taishoJoken)) {
    		/** 行CSS：セグメント計 */
    		return NipoRefConstants.CSS_TR_CLASS_SEGMENT;
    	}
    	if(TaishoJoken.CODE_ALL.equals(taishoJoken)) {
    		/** 行CSS：総合計 */  
    		return NipoRefConstants.CSS_TR_CLASS_TOTAL;
    	}
    	return NipoRefConstants.CSS_TR_CLASS;
    }
}
