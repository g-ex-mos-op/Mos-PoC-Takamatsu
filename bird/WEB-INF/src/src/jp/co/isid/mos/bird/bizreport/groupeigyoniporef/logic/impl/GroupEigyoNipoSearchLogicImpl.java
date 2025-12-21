package jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.KikanSiteiUtil;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoCommon;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.common.EigyoNipoConstants;
import jp.co.isid.mos.bird.bizreport.groupeigyoniporef.logic.GroupEigyoNipoSearchLogic;
import jp.co.isid.mos.bird.common.dao.CtlBirdUserDao;
import jp.co.isid.mos.bird.common.entity.CtlBirdUser;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 検索条件取得ロジック
 *
 * @author xnarita
 */
public class GroupEigyoNipoSearchLogicImpl implements GroupEigyoNipoSearchLogic {

    /** 検索条件取得ロジックID */
    public static final String LOGIC_ID = "BBR001L07";
    
    private CtlBirdUserDao ctlBirdUserDao;

    /**
     * 検索条件を取得する
     * @param   dto 条件部情報Dto
     * @return  Map 検索条件情報
     */
    public Map getSearchData(NipoRefConditionParameterDto dto) {
    	Map paramMap = new HashMap();

        // 管理会社企業コード
        String companyCd = dto.getCompanyCd();
        // 前年データ種別
        String dataShu = new String();
        // 対象期間
        String kikanInfo = dto.getTaishoKikanCd();
        // 対象期間From
        String kikanFrom = EigyoNipoConstants.EMPTY;
        // 対象期間To
        String kikanTo = EigyoNipoConstants.EMPTY;
        
        // 日報
        if (TaishoKikan.DAY1.equals(kikanInfo)) {
            kikanFrom = dto.getKikanNipo();
        // 当月月報
        } else if (TaishoKikan.MONTHAPP.equals(kikanInfo)) {
//            kikanFrom = dto.getKikanCurrMonth();
            kikanFrom = dto.getKikanCurrMonth() + "01";
            kikanTo = (dto.getBirdDateInfo()).getAppDate();
        // 月報
        } else if (TaishoKikan.MONTH.equals(kikanInfo)) {
            kikanFrom = dto.getKikanYM();
        // 期報
        } else if (TaishoKikan.KIBETU.equals(kikanInfo)) {
            List monthList = KikanSiteiUtil.henkanKiMonth(dto.getKikanYear(), dto.getKikanKibetu());
            kikanFrom = (String)monthList.get(0);
            kikanTo = (String)monthList.get(1);
        // 期間指定
        } else if (TaishoKikan.DAYS.equals(kikanInfo)) {
            kikanFrom = dto.getKikanFrom();
            kikanTo = dto.getKikanTo();
            // 対象期間From、To整合性チェック
            if(!KikanSiteiUtil.validateDateFromTo(kikanFrom, kikanTo)) {
                throw new ConstraintsViolationException
                (EigyoNipoConstants.MSG_FROMTO, EigyoNipoConstants.EMPTY);
            }
            // 制限期間チェック(９２日以内であること)
            if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
                throw new NotRelevantException
                (EigyoNipoConstants.MSG_KIKAN, EigyoNipoConstants.MSG_LIMIT_DAY);
            }
        }

        // 入力チェック
        validate(dto, kikanFrom, kikanTo);

        // 店舗種別
        String tenShu = dto.getTenpoShubetuCd();
        // 前年データ種別
        dataShu = TenpoShubetu.CODE_ZENNEN.equals(tenShu) ?
            dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();
        // 本部ユーザーの時
        if (UserType.HONBU.equals(dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
            // 集計区分
            String areaDaiFlg = dto.getShukeiKbnCd();
            // 対象店舗
            String taishoTenpo = dto.getTaishoTenpoCd();            
            // SVコード 20081209追加
            String svCd = dto.getSvCd();
            // SV名称   20081209追加
            String svName = "";
 
            // 入力チェック
            validateHonbu(dto, dataShu, svCd);
            
            /* 2008/12/09追加  SV対応  xayumi
             * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
            if(dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)){
                // SVコードの前ゼロ付加
                CodeFormatter formatter = new CodeFormatter(8);
                formatter.setFormatPattern("00000000");
                svCd = formatter.format(svCd, true);
                
                //SVユーザー情報取得           
                CtlBirdUser ctlBirdUser = getCtlBirdUserDao().getBirdCtlBirdUser(svCd);
                if (ctlBirdUser==null) {
                    throw new NoResultException();
                }
                svName = ctlBirdUser.getUserNameKj(); 
            }

            paramMap.put(EigyoNipoConstants.MAP_USER_ID, dto.getBirdUserInfo().getMstUser().getUser_id());
            paramMap.put(EigyoNipoConstants.MAP_TENSHU, tenShu);
            paramMap.put(EigyoNipoConstants.MAP_LIMIT_FLG,new Boolean(dto.getBirdUserInfo().isLimit()));
            paramMap.put(EigyoNipoConstants.MAP_AREA_DAI_FLG, areaDaiFlg);
            paramMap.put(EigyoNipoConstants.MAP_TAISHO_TENPO, taishoTenpo);
            //20081209追加 SVコード、SV名称
            paramMap.put( EigyoNipoConstants.SV_CD  , EigyoNipoCommon.setEmpty(svCd));
            paramMap.put( EigyoNipoConstants.SV_NAME, EigyoNipoCommon.setEmpty(svName));

        // オーナーユーザーの時
        } else if (UserType.ONER.equals(dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
            // オーナーコード取得            
            String onerCd = null;
            List ownerList = dto.getBirdUserInfo().getUserOner();
            for (Iterator it = ownerList.iterator(); it.hasNext();) {
                UIUserOner uIUserOner = (UIUserOner) it.next();
                if (companyCd.equals(uIUserOner.getCompanyCd())) {
                    onerCd = uIUserOner.getOnerCd();
                    break;
                }
            }
            // 入力チェック
            validateOner(onerCd, dataShu);

            paramMap.put(EigyoNipoConstants.MAP_ONER_CD, onerCd );
        }
        paramMap.put(EigyoNipoConstants.MAP_COMPANY_CD,companyCd);
        paramMap.put(EigyoNipoConstants.MAP_DATASHU, dataShu);             
        paramMap.put(EigyoNipoConstants.MAP_TAISHO_KIKAN , kikanInfo );             
        paramMap.put(EigyoNipoConstants.MAP_KIKAN_FROM, kikanFrom );
        paramMap.put(EigyoNipoConstants.MAP_KIKAN_TO, kikanTo );
        paramMap.put(EigyoNipoConstants.MAP_USER_TYPE_CD, dto.getBirdUserInfo().getMstUser().getUserTypeCd());
        //売上値小数桁数を設定(2013/02追加 海外売上集信対応)
    	Integer digitCnt = new Integer(0);//初期値0桁
        if(dto.getCodCompany() !=null) {
        	String pattern = dto.getCodCompany().getDispFormat();
        	if(!CommonUtil.isNull(dto.getCodCompany().getDispFormat())) {
        		if(pattern.indexOf(".")>=0) {
        			digitCnt = new Integer(pattern.length() - pattern.indexOf(".") - 1);
            	}
        	}
        }
        paramMap.put(EigyoNipoConstants.MAP_DIGIT_CNT, digitCnt);

        return paramMap;
    }

    /**
     * 入力チェックをする
     * @param dto           条件部情報DTO
     * @param kikanFrom     対象期間From
     * @param kikanTo       対象期間To
     */
    private void validate(NipoRefConditionParameterDto dto, String kikanFrom, String kikanTo) {
        if (dto == null) {
            throw new NotNullException(EigyoNipoConstants.MSG_CONDITION);
        }
        if (EigyoNipoCommon.isNull(dto.getCompanyCd())) {
            throw new NotNullException(EigyoNipoConstants.MSG_COMPANY_CD);
        }
        if (EigyoNipoCommon.isNull( dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
            throw new NotNullException(EigyoNipoConstants.MSG_USER_TYPE);
        }
        if (EigyoNipoCommon.isNull(dto.getTaishoKikanCd())) {
            throw new NotNullException(EigyoNipoConstants.MSG_TAISHO_KIKAN);
        }
        if (EigyoNipoCommon.isNull(kikanFrom)) {
            throw new NotNullException(EigyoNipoConstants.MSG_KIKAN_FROM);
        }
        if ((TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())
            || TaishoKikan.KIBETU.equals(dto.getTaishoKikanCd()))
            && EigyoNipoCommon.isNull(kikanTo)) {
                throw new NotNullException(EigyoNipoConstants.MSG_KIKAN_TO);
        }
        if (TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())) {
            if(!KikanSiteiUtil.validateDateFromTo(kikanFrom, kikanTo)) {
                throw new ConstraintsViolationException(EigyoNipoConstants.MSG_FROMTO, EigyoNipoConstants.EMPTY);
            }

            if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
                throw new NotRelevantException(EigyoNipoConstants.MSG_KIKAN, EigyoNipoConstants.MSG_LIMIT_DAY);
            }
        }
    }

    /**
     * 入力チェックをする【本部ユーザのみ】
     * @param dto           条件部情報DTO
     * @param zenDataShu    前年データ種別
     */
    private void validateHonbu(NipoRefConditionParameterDto dto, String zenDataShu, String svCd) {
        if (EigyoNipoCommon.isNull(dto.getBirdUserInfo().getMstUser().getUser_id())) {
            throw new NotNullException(EigyoNipoConstants.MSG_USER_ID);
        }
        if (EigyoNipoCommon.isNull(dto.getTenpoShubetuCd())) {            
            throw new NotNullException(EigyoNipoConstants.MSG_TENPO_SHU);
        }
        if (EigyoNipoCommon.isNull(dto.getShukeiKbnCd())) {
            throw new NotNullException(EigyoNipoConstants.MSG_SHUKEI_KBN);
        }
        /* 
         * 2008/12/09追加 xayumi SV対応
         * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
        if(dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)){
            // SVコード必須チェック 
            if (EigyoNipoCommon.isNull(svCd)) {
                throw new NotNullException(EigyoNipoConstants.MSG_SV_CD);
            }
        }
        else if (EigyoNipoCommon.isNull(dto.getTaishoTenpoCd())) {
            throw new NotNullException(EigyoNipoConstants.MSG_TAISHO_TENPO);
        }
        if (EigyoNipoCommon.isNull(zenDataShu)) {
            throw new NotNullException(EigyoNipoConstants.MSG_ZEN_DATA);
        }
//        if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())
//            && TaishoTenpo.FC.equals(dto.getTaishoTenpoCd())) {
//            throw new IllegalOperationException(EigyoNipoConstants.MSG_FC, EigyoNipoConstants.MSG_SENTAKU);
//        }
    }

    /**
     * 入力チェックをする【オーナーユーザのみ】
     * @param onerCd            オーナーコード
     * @param zenDataShubetu    前年データ種別
     */
    private void validateOner(String onerCd, String zenDataShubetu) {
        if (EigyoNipoCommon.isNull(onerCd)) {
            throw new NotExistException(EigyoNipoConstants.MSG_ONER_CD);
        }
        if (EigyoNipoCommon.isNull(zenDataShubetu)) {
            throw new NotNullException(EigyoNipoConstants.MSG_ZEN_DATA);
        }
    }

    public CtlBirdUserDao getCtlBirdUserDao() {
        return ctlBirdUserDao;
    }

    public void setCtlBirdUserDao(CtlBirdUserDao ctlBirdUserDao) {
        this.ctlBirdUserDao = ctlBirdUserDao;
    }
 }