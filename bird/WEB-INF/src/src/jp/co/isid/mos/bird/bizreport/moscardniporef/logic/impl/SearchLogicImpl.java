package jp.co.isid.mos.bird.bizreport.moscardniporef.logic.impl;

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
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoCommon;
import jp.co.isid.mos.bird.bizreport.moscardniporef.common.MoscardNipoConstants;
import jp.co.isid.mos.bird.bizreport.moscardniporef.logic.SearchLogic;
import jp.co.isid.mos.bird.common.dao.CtlBirdUserDao;
import jp.co.isid.mos.bird.common.entity.CtlBirdUser;
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
public class SearchLogicImpl implements SearchLogic {

    /** 検索条件取得ロジックID */
    public static final String LOGIC_ID = "BBR015L07";
    
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
        String kikanFrom = MoscardNipoConstants.EMPTY;
        // 対象期間To
        String kikanTo = MoscardNipoConstants.EMPTY;
        
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
                (MoscardNipoConstants.MSG_FROMTO, MoscardNipoConstants.EMPTY);
            }
            // 制限期間チェック(９２日以内であること)
            if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
                throw new NotRelevantException
                (MoscardNipoConstants.MSG_KIKAN, MoscardNipoConstants.MSG_LIMIT_DAY);
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

            paramMap.put(MoscardNipoConstants.MAP_USER_ID, dto.getBirdUserInfo().getMstUser().getUser_id());
            paramMap.put(MoscardNipoConstants.MAP_TENSHU, tenShu);
            paramMap.put(MoscardNipoConstants.MAP_LIMIT_FLG,new Boolean(dto.getBirdUserInfo().isLimit()));
            paramMap.put(MoscardNipoConstants.MAP_AREA_DAI_FLG, areaDaiFlg);
            paramMap.put(MoscardNipoConstants.MAP_TAISHO_TENPO, taishoTenpo);
            //20081209追加 SVコード、SV名称
            paramMap.put( MoscardNipoConstants.SV_CD  , MoscardNipoCommon.setEmpty(svCd));
            paramMap.put( MoscardNipoConstants.SV_NAME, MoscardNipoCommon.setEmpty(svName));

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

            paramMap.put(MoscardNipoConstants.MAP_ONER_CD, onerCd );
        }
        paramMap.put(MoscardNipoConstants.MAP_COMPANY_CD,companyCd);
        paramMap.put(MoscardNipoConstants.MAP_DATASHU, dataShu);             
        paramMap.put(MoscardNipoConstants.MAP_TAISHO_KIKAN , kikanInfo );             
        paramMap.put(MoscardNipoConstants.MAP_KIKAN_FROM, kikanFrom );
        paramMap.put(MoscardNipoConstants.MAP_KIKAN_TO, kikanTo );
        paramMap.put(MoscardNipoConstants.MAP_USER_TYPE_CD, dto.getBirdUserInfo().getMstUser().getUserTypeCd());

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
            throw new NotNullException(MoscardNipoConstants.MSG_CONDITION);
        }
        if (MoscardNipoCommon.isNull(dto.getCompanyCd())) {
            throw new NotNullException(MoscardNipoConstants.MSG_COMPANY_CD);
        }
        if (MoscardNipoCommon.isNull( dto.getBirdUserInfo().getMstUser().getUserTypeCd())) {
            throw new NotNullException(MoscardNipoConstants.MSG_USER_TYPE);
        }
        if (MoscardNipoCommon.isNull(dto.getTaishoKikanCd())) {
            throw new NotNullException(MoscardNipoConstants.MSG_TAISHO_KIKAN);
        }
        if (MoscardNipoCommon.isNull(kikanFrom)) {
            throw new NotNullException(MoscardNipoConstants.MSG_KIKAN_FROM);
        }
        if ((TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())
            || TaishoKikan.KIBETU.equals(dto.getTaishoKikanCd()))
            && MoscardNipoCommon.isNull(kikanTo)) {
                throw new NotNullException(MoscardNipoConstants.MSG_KIKAN_TO);
        }
        if (TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())) {
            if(!KikanSiteiUtil.validateDateFromTo(kikanFrom, kikanTo)) {
                throw new ConstraintsViolationException(MoscardNipoConstants.MSG_FROMTO, MoscardNipoConstants.EMPTY);
            }

            if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
                throw new NotRelevantException(MoscardNipoConstants.MSG_KIKAN, MoscardNipoConstants.MSG_LIMIT_DAY);
            }
        }
    }

    /**
     * 入力チェックをする【本部ユーザのみ】
     * @param dto           条件部情報DTO
     * @param zenDataShu    前年データ種別
     */
    private void validateHonbu(NipoRefConditionParameterDto dto, String zenDataShu, String svCd) {
        if (MoscardNipoCommon.isNull(dto.getBirdUserInfo().getMstUser().getUser_id())) {
            throw new NotNullException(MoscardNipoConstants.MSG_USER_ID);
        }
        if (MoscardNipoCommon.isNull(dto.getTenpoShubetuCd())) {            
            throw new NotNullException(MoscardNipoConstants.MSG_TENPO_SHU);
        }
        if (MoscardNipoCommon.isNull(dto.getTaishoTenpoCd())) {
            throw new NotNullException(MoscardNipoConstants.MSG_TAISHO_TENPO);
        }
        if (MoscardNipoCommon.isNull(dto.getShukeiKbnCd())) {
            throw new NotNullException(MoscardNipoConstants.MSG_SHUKEI_KBN);
        }
        if (MoscardNipoCommon.isNull(zenDataShu)) {
            throw new NotNullException(MoscardNipoConstants.MSG_ZEN_DATA);
        }
//        if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())
//            && TaishoTenpo.FC.equals(dto.getTaishoTenpoCd())) {
//            throw new IllegalOperationException(EigyoNipoConstants.MSG_FC, EigyoNipoConstants.MSG_SENTAKU);
//        }
        /* 
         * 2008/12/09追加 xayumi SV対応
         * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
        if(dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)){
            // SVコード必須チェック 
            if (MoscardNipoCommon.isNull(svCd)) {
                throw new NotNullException(MoscardNipoConstants.MSG_SV_CD);
            }
        }
    }

    /**
     * 入力チェックをする【オーナーユーザのみ】
     * @param onerCd            オーナーコード
     * @param zenDataShubetu    前年データ種別
     */
    private void validateOner(String onerCd, String zenDataShubetu) {
        if (MoscardNipoCommon.isNull(onerCd)) {
            throw new NotExistException(MoscardNipoConstants.MSG_ONER_CD);
        }
        if (MoscardNipoCommon.isNull(zenDataShubetu)) {
            throw new NotNullException(MoscardNipoConstants.MSG_ZEN_DATA);
        }
    }

    public CtlBirdUserDao getCtlBirdUserDao() {
        return ctlBirdUserDao;
    }

    public void setCtlBirdUserDao(CtlBirdUserDao ctlBirdUserDao) {
        this.ctlBirdUserDao = ctlBirdUserDao;
    }
 }