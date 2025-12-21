/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.groupsuiiref.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.common.BizReportConstants;
import jp.co.isid.mos.bird.bizreport.common.entity.CodCompany;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiGepo;
import jp.co.isid.mos.bird.bizreport.groupsuiiref.entity.UISuiiNipo;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * static 処理保持クラス
 * 
 * @author xkinu
 *
 */
public class GroupSuiiRefUtil {
	/** 画面ID：BBR002 */
	public static final String SCREEN_ID = "BBR002";
    /* ACTIONID：初期画面 */
    public static final String ACTION_ID = SCREEN_ID+"A0";
    
    /* LOGICID：初期画面項目情報の取得 */
    public static final String LOGIC_ID_CONDITION = SCREEN_ID+"L01";
    /* LOGICID：検索対象情報の取得 */
    public static final String LOGIC_ID_SEARCH = SCREEN_ID+"L02";
    /* LOGICID：予算存在チェックロジックＩＤ */
    public static final String LOGIC_ID_EXISTYOSAN = SCREEN_ID+"L03";
    /* LOGICID：CSVダウンロード */
    public static final String LOGIC_ID_CSVOUTPUT = SCREEN_ID+"L04";
    
    
    /* VIEWID：店選択画面 */
    public static final String VIEW_ID_MISESEARCH   = "BCO008V01";
    /* VIEWID：初期画面 */
    public static final String VIEW_ID_CONDITION = SCREEN_ID+"V01";
    /* VIEWID：照会画面 */
    public static final String VIEW_ID_CONFIRM = SCREEN_ID+"V02";
    
    /* ブロックコード：すべて */
    public static final String BLOCK_CD_ALL = "ALL";
    /* ブロックコード：未登録 */
    public static final String BLOCK_CD_MISETTEI = "000";
    
    /**
     * コンストラクター
     *
     */
    private GroupSuiiRefUtil(){
        super();
    }
    
    /**
     * 通貨換算処理
     * 
     * 作成日:2013/01/25 (海外売上集信対応に伴い作成)
     * @param codCompany
     * @param resultDto
     * @return
     */
    public static SuiiRefResultDto changeCurrency(
            SuiiRefParameterDto parameterDto, SuiiRefResultDto resultDto)
    {
        //1.List[[日本円換算結果]]を生成します。
        List listChangeResult = new ArrayList();
        //DTO【結果情報】.List[[フォーカスタブ検索結果]]
        List listResult = resultDto.getListFocusTabResult();
        
        //2.DTO【結果情報】.List[[フォーカスタブ検索結果]]にデータが存在する場合、下記の処理を行います。
        if(listResult!= null && !listResult.isEmpty()) {
            CodCompany codCompany = parameterDto.getCodCompany();
            //日報共通DTO【結果条件】.CodCompany[会社].為替相場を取得します。
            BigDecimal decRate = codCompany.getRate();
            //換算小数点を取得
            int pointCount = countPattrn(parameterDto).intValue();
           
            for (int i = 0; i < listResult.size();i++) {
                SuiiRefUIEntity eMoto = (SuiiRefUIEntity)listResult.get(i);
                SuiiRefUIEntity eJp = null;
                
                if (eMoto instanceof UISuiiNipo) {
                	eJp = nipoUriage((UISuiiNipo)eMoto);
                }
                else {
                	eJp = gepoUriage((UISuiiGepo)eMoto);
                }
                   
                eJp.setUriage(changeCurrency(eMoto.getUriage(),decRate));
                eJp.setUriageZen(changeCurrency(eMoto.getUriageZen(),decRate));
                eJp.setYosan(changeCurrency(eMoto.getYosan(),decRate));
                eJp.setNetUriage(changeCurrency(eMoto.getNetUriage(),decRate));
                eJp.setNetUriageZen(changeCurrency(eMoto.getNetUriageZen(),decRate));
                SuiiRefUtil.calcTanka(eJp,pointCount);
                listChangeResult.add(eJp);
            }//end of for (int i = 0; i < listResult.size();i++)

            resultDto.setListFocusTabResult(listChangeResult);
        }
        //変数DTO【日本円換算結果】.換算フラグへDTO【結果条件】.換算フラグを設定します。
        resultDto.setKansanFlg(parameterDto.isKansan());
        //処理1の変数DTO【日本円換算結果】をリターンします。
        return resultDto;
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
    
    /**
     * 売上推移表日報コピー処理
     * 
     * 作成日:2013/05/14 (海外売上集信対応に伴い作成)
     * @param uiSuiiNipo
     * @return UISuiiNipo
     */
    private static UISuiiNipo nipoUriage(UISuiiNipo suii) {
        UISuiiNipo eNewUriage = new UISuiiNipo();
            eNewUriage.setCompanyCd(suii.getCompanyCd());
            eNewUriage.setCssClassName(suii.getCssClassName());
            eNewUriage.setEigyoDays(suii.getEigyoDays());
            eNewUriage.setEigyoDaysZen(suii.getEigyoDaysZen());
            eNewUriage.setEigyoDt(suii.getEigyoDt());
            eNewUriage.setEigyoDtLabel(suii.getEigyoDtLabel());
            eNewUriage.setExistData(suii.isExistData());
            eNewUriage.setKyakusu(suii.getKyakusu());
            eNewUriage.setKyakusuZen(suii.getKyakusuZen());
            eNewUriage.setKyakusuZennenhi(suii.getKyakusuZennenhi());
            eNewUriage.setKyakutanka(suii.getKyakutanka());
            eNewUriage.setKyakutankaZen(suii.getKyakutankaZen());
            eNewUriage.setKyakutankaZennenhi(suii.getKyakutankaZennenhi());
            eNewUriage.setLink(suii.isLink());
            eNewUriage.setNetKyakusu(suii.getNetKyakusu());
            eNewUriage.setNetKyakusuZen(suii.getNetKyakusuZen());
            eNewUriage.setNetKyakusuZennenhi(suii.getNetKyakusuZennenhi());
            eNewUriage.setNetKyakutanka(suii.getNetKyakutanka());
            eNewUriage.setNetKyakutankaZen(suii.getNetKyakutankaZen());
            eNewUriage.setNetKyakutankaZennenhi(suii.getNetKyakutankaZennenhi());
            eNewUriage.setNetUriage(suii.getNetUriage());
            eNewUriage.setNetUriageZen(suii.getNetUriageZen());
            eNewUriage.setNetUriageZennenhi(suii.getNetUriageZennenhi());
            eNewUriage.setTenkoKbn(suii.getTenkoKbn());
            eNewUriage.setTenkoKbnKj(suii.getTenkoKbnKj());
            eNewUriage.setTenkoKbnZen(suii.getTenkoKbnZen());
            eNewUriage.setTenkoKbnZenKj(suii.getTenkoKbnZenKj());
            eNewUriage.setUriage(suii.getUriage());
            eNewUriage.setUriageZen(suii.getUriageZen());
            eNewUriage.setUriageZennenhi(suii.getUriageZennenhi());
            eNewUriage.setYosan(suii.getYosan());
            eNewUriage.setYosanTasseiRitu(suii.getYosanTasseiRitu());
            eNewUriage.setZennenDt(suii.getZennenDt());
        return eNewUriage;
    }
    
    /**
     * 売上推移表月報コピー処理
     * 
     * 作成日:2013/05/14 (海外売上集信対応に伴い作成)
     * @param uiSuiiGepo
     * @return UISuiiGepo
     */
    private static UISuiiGepo gepoUriage(UISuiiGepo suii) {
        UISuiiGepo eNewUriage = new UISuiiGepo();
            eNewUriage.setCompanyCd(suii.getCompanyCd());
            eNewUriage.setCssClassName(suii.getCssClassName());
            eNewUriage.setEigyoDays(suii.getEigyoDays());
            eNewUriage.setEigyoDaysZen(suii.getEigyoDaysZen());
            eNewUriage.setEigyoDt(suii.getEigyoDt());
            eNewUriage.setEigyoDtLabel(suii.getEigyoDtLabel());
            eNewUriage.setExistData(suii.isExistData());
            eNewUriage.setKyakusu(suii.getKyakusu());
            eNewUriage.setKyakusuZen(suii.getKyakusuZen());
            eNewUriage.setKyakusuZennenhi(suii.getKyakusuZennenhi());
            eNewUriage.setKyakutanka(suii.getKyakutanka());
            eNewUriage.setKyakutankaZen(suii.getKyakutankaZen());
            eNewUriage.setKyakutankaZennenhi(suii.getKyakutankaZennenhi());
            eNewUriage.setLink(suii.isLink());
            eNewUriage.setNetKyakusu(suii.getNetKyakusu());
            eNewUriage.setNetKyakusuZen(suii.getNetKyakusuZen());
            eNewUriage.setNetKyakusuZennenhi(suii.getNetKyakusuZennenhi());
            eNewUriage.setNetKyakutanka(suii.getNetKyakutanka());
            eNewUriage.setNetKyakutankaZen(suii.getNetKyakutankaZen());
            eNewUriage.setNetKyakutankaZennenhi(suii.getNetKyakutankaZennenhi());
            eNewUriage.setNetUriage(suii.getNetUriage());
            eNewUriage.setNetUriageZen(suii.getNetUriageZen());
            eNewUriage.setNetUriageZennenhi(suii.getNetUriageZennenhi());
            eNewUriage.setTenkoKbn(suii.getTenkoKbn());
            eNewUriage.setTenkoKbnKj(suii.getTenkoKbnKj());
            eNewUriage.setTenkoKbnZen(suii.getTenkoKbnZen());
            eNewUriage.setTenkoKbnZenKj(suii.getTenkoKbnZenKj());
            eNewUriage.setUriage(suii.getUriage());
            eNewUriage.setUriageZen(suii.getUriageZen());
            eNewUriage.setUriageZennenhi(suii.getUriageZennenhi());
            eNewUriage.setYosan(suii.getYosan());
            eNewUriage.setYosanTasseiRitu(suii.getYosanTasseiRitu());
            eNewUriage.setZennenDt(suii.getZennenDt());
        return eNewUriage;
    }
    /**
     * 小数点以下の桁数判断処理
     * 
     * 作成日:2013/05/14 (海外売上集信対応に伴い作成)
     * @param SuiiRefParameterDto
     * @return pointCount
     */
     public static Integer countPattrn(SuiiRefParameterDto dto) {
        Integer pointCount = new Integer(0);
        if(dto.getCodCompany() !=null) {
            String pattern = dto.getCodCompany().getDispFormat();
            if(dto.isKansan()) {
                pattern = BizReportConstants.FORMAT_JPY;    
            }
            if(!CommonUtil.isNull(dto.getCodCompany().getDispFormat())) {
                if(pattern.indexOf(".")>=0) {
                    pointCount = new Integer(pattern.length() - pattern.indexOf(".") - 1);
                }
            }
        }
        return pointCount;
    }    
}
