package jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.KikanSiteiUtil;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoCommon;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.common.GyotaibetuNipoConstants;
import jp.co.isid.mos.bird.bizreport.gyotaibetuniporef.logic.GyotaibetuNipoSearchLogic;
import jp.co.isid.mos.bird.common.dao.CtlBirdUserDao;
import jp.co.isid.mos.bird.common.entity.CtlBirdUser;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 
 * @author xkinu
 * @modifier xkinu 2008/02/29 T.Kinugawa(ASPAC) PK-010102-003.01 総合営業日報検索機能追加 欠陥一覧.xls　欠陥No.8対応
 * 
 */
public class GyotaibetuNipoSearchLogicImpl implements GyotaibetuNipoSearchLogic {
    public static final String LOGIC_ID = "BBR005L06";
    private CtlBirdUserDao ctlBirdUserDao;
                  
    /**
     * 検索条件の取得
     * @return
     */
    public Map getSearchData(NipoRefConditionParameterDto dto) {
               
    	Map paramMap = new HashMap();     
        
        // 本部ユーザーの時
        if ( dto.getBirdUserInfo().getMstUser().getUserTypeCd().equals( UserType.HONBU ) ) {          
            
        	// 管理会社企業コード
            String companyCd = dto.getCompanyCd();
            // ユーザID
            String userId = dto.getBirdUserInfo().getMstUser().getUser_id();
    		// 制限区分
    		boolean limitFlg = dto.getBirdUserInfo().isLimit();
    		// 店舗種別
    		String tenpoShu =  dto.getTenpoShubetuCd();
    		// 集計区分
    		String shukeiKbn = dto.getShukeiKbnCd();
    		// 対象期間
    		String taishoKikan = dto.getTaishoKikanCd();
    		// 対象店舗
    		String taishoTenpo = dto.getTaishoTenpoCd();
            // SVコード 20081209追加
            String svCd = dto.getSvCd();
            // SV名称   20081209追加
            String svName = "";
    		
    		// 前年データ取得
    		String zenDataShu = null;
    		if (TenpoShubetu.CODE_ZENNEN.equals(tenpoShu)) {
    			zenDataShu = dto.getZenDataZennenCd();
    		} else {
    			zenDataShu = dto.getZenDataZennenOthCd();
    		}
            
    		// 対象期間From、To取得
    		String kikanFrom = GyotaibetuNipoConstants.EMPTY;
    		String kikanTo = GyotaibetuNipoConstants.EMPTY;
    		if (TaishoKikan.DAY1.equals(taishoKikan)) {
    			kikanFrom = dto.getKikanNipo();
    		}
    		else if (TaishoKikan.MONTH.equals(taishoKikan)) {
    			kikanFrom = dto.getKikanYM();
    			kikanTo = dto.getKikanYM();
    		}
    		else if (TaishoKikan.MONTHAPP.equals(taishoKikan)) {
    			kikanFrom = dto.getKikanCurrMonth();
    			kikanTo = getBirdDateInfo().getAppDate();
    		}
    		else if (TaishoKikan.KIBETU.equals(taishoKikan)) {
    			List kibetuList = KikanSiteiUtil.henkanKiMonth(
    				dto.getKikanYear(),
    				dto.getKikanKibetu());
    			kikanFrom = (String) kibetuList.get(0);
    			kikanTo = (String) kibetuList.get(1);
    		}
    		else if (TaishoKikan.DAYS.equals(taishoKikan)) {
    			kikanFrom = dto.getKikanFrom();
    			kikanTo = dto.getKikanTo();
    		}
    		else {
    			throw new InvalidInputException(GyotaibetuNipoConstants.MSG_TAISHO_KIKAN);
    		}
            
    		// 入力チェック
    		validate(dto, zenDataShu, kikanFrom, kikanTo, svCd);
     		kikanValidate(dto, kikanFrom, kikanTo);
            
            /* 2008/12/09追加  SV対応  xayumi
             * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
            if(shukeiKbn.equals(ShukeiKbn.SV_CD)){
                // SVコードの前ゼロ付加
                CodeFormatter formatter = new CodeFormatter(8);
                formatter.setFormatPattern("00000000");
                if (!GyotaibetuNipoCommon.isNull(svCd)) {                
                    svCd = formatter.format(svCd, true);
                }
                //SVユーザー情報取得           
                CtlBirdUser ctlBirdUser = getCtlBirdUserDao().getBirdCtlBirdUser(svCd);
                if (ctlBirdUser==null) {
                    throw new NoResultException();
                }
                svName = ctlBirdUser.getUserNameKj(); 
            }
    		
            paramMap.put( GyotaibetuNipoConstants.COMPANY_CD,companyCd );
            paramMap.put( GyotaibetuNipoConstants.USER_ID, userId);
            paramMap.put( GyotaibetuNipoConstants.TENSHU, tenpoShu );
            paramMap.put( GyotaibetuNipoConstants.DATASHU, zenDataShu );
            paramMap.put( GyotaibetuNipoConstants.TAISHO_KIKAN,taishoKikan );           
            paramMap.put( GyotaibetuNipoConstants.LIMIT_FLG,new Boolean( limitFlg ) );            
            paramMap.put( GyotaibetuNipoConstants.KIKAN_FROM, kikanFrom );
            paramMap.put( GyotaibetuNipoConstants.KIKAN_TO, kikanTo );
            paramMap.put( GyotaibetuNipoConstants.TAISHO_TENPO, taishoTenpo );
            paramMap.put( GyotaibetuNipoConstants.USER_TYPE_CD, dto.getBirdUserInfo().getMstUser().getUserTypeCd() );
            paramMap.put( GyotaibetuNipoConstants.SHUKEI_KBN, shukeiKbn );
            //20081209追加 SVコード、SV名称
            paramMap.put( GyotaibetuNipoConstants.SV_CD  , svCd );
            paramMap.put( GyotaibetuNipoConstants.SV_NAME, svName );
           
        } else if (  dto.getBirdUserInfo().getMstUser().getUserTypeCd().equals( UserType.ONER ) ) {
        	
    		// 会社コード
    		String companyCd = dto.getCompanyCd();
    		
            boolean onerExistFlg = false;
            
            List onerList = dto.getBirdUserInfo().getUserOner();

             UIUserOner oner = new UIUserOner();
             
             for ( int i = 0; i < onerList.size(); i++ ) {
                 oner = (UIUserOner)onerList.get(i);
                 if ( companyCd.equals( oner.getCompanyCd() ) ) {
                     onerExistFlg = true;
                     break;
                 }
             }
             if ( !onerExistFlg ) {
                 throw new NotExistException("オーナー");
             }
             
             String onerCd = oner.getOnerCd();
             
             //前年データ種別
             String dataOnerInfo = dto.getZenDataZennenOthCd();
             
     		// 対象期間
     		String taishoKikan = dto.getTaishoKikanCd();
             
     		// 対象期間From、To取得
     		String kikanFrom = GyotaibetuNipoConstants.EMPTY;
     		String kikanTo = GyotaibetuNipoConstants.EMPTY;
     		if (TaishoKikan.DAY1.equals(taishoKikan)) {
     			kikanFrom = dto.getKikanNipo();
     		}
     		else if (TaishoKikan.MONTH.equals(taishoKikan)) {
     			kikanFrom = dto.getKikanYM();
    			kikanTo = dto.getKikanYM();
     		}
     		else if (TaishoKikan.MONTHAPP.equals(taishoKikan)) {
     			kikanFrom = dto.getKikanCurrMonth();
    			kikanTo = dto.getKikanCurrMonth();
     		}
     		else if (TaishoKikan.KIBETU.equals(taishoKikan)) {
     			List kibetuList = KikanSiteiUtil.henkanKiMonth(
     				dto.getKikanYear(),
     				dto.getKikanKibetu());
    			kikanFrom = (String) kibetuList.get(0);
    			kikanTo = (String) kibetuList.get(1);
     		}
     		else if (TaishoKikan.DAYS.equals(taishoKikan)) {
     			kikanFrom = dto.getKikanFrom();
     			kikanTo = dto.getKikanTo();
     		}
     		else {
     			throw new InvalidInputException(GyotaibetuNipoConstants.MSG_TAISHO_KIKAN);
     		}
             
    		// 入力チェック
     		kikanValidate(dto, kikanFrom, kikanTo);
     		
             paramMap.put( GyotaibetuNipoConstants.COMPANY_CD,companyCd);
             paramMap.put( GyotaibetuNipoConstants.ONER_CD, onerCd );
             paramMap.put( GyotaibetuNipoConstants.TAISHO_KIKAN , taishoKikan );
             paramMap.put( GyotaibetuNipoConstants.DATASHU, dataOnerInfo );
             paramMap.put( GyotaibetuNipoConstants.KIKAN_FROM, kikanFrom );
             paramMap.put( GyotaibetuNipoConstants.KIKAN_TO, kikanTo );
             paramMap.put( GyotaibetuNipoConstants.USER_TYPE_CD, dto.getBirdUserInfo().getMstUser().getUserTypeCd() );
        }

        return paramMap;
    }
    
	/**
	 * 入力チェックをする<br>
	 * @param dto			検索条件Dto
	 * @param zenDataShu	前年データ種別
	 * @param kikanFrom	対象期間From
	 * @param kikanTo		対象期間To
     * @param svCd         SVコード 2008/12/09追加
	 */
	private void validate(NipoRefConditionParameterDto dto, String zenDataShu, String kikanFrom, String kikanTo, String svCd) {
		/*if (dto == null) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_CONDITION);
		}
		if (GyotaibetuNipoCommon.isNull(dto.getCompanyCd())) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_COMPANY_CD);
		}
		if (GyotaibetuNipoCommon.isNull(dto.getBirdUserInfo().getMstUser().getUser_id())) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_USER_ID);
		}
		if (GyotaibetuNipoCommon.isNull(dto.getTenpoShubetuCd())) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_TENPO_SHU);
		}
		if (GyotaibetuNipoCommon.isNull(dto.getTaishoTenpoCd())) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_TAISHO_TENPO);
		}
		if (GyotaibetuNipoCommon.isNull(dto.getShukeiKbnCd())) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_SHUKEI_KBN);
		}
		if (GyotaibetuNipoCommon.isNull(dto.getTaishoKikanCd())) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_TAISHO_KIKAN);
		}
		if (GyotaibetuNipoCommon.isNull(kikanFrom)) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_KIKAN_FROM);
		}*/
		if (GyotaibetuNipoCommon.isNull(zenDataShu)) {
			throw new NotNullException(GyotaibetuNipoConstants.MSG_ZEN_DATA);
		}
		// 集計区分＝直営業店を含む且つ、対象店舗＝FCのみの場合、エラー
//		if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())
//			&& TaishoTenpo.FC.equals(dto.getTaishoTenpoCd())) {
//			throw new IllegalOperationException
//				(GyotaibetuNipoConstants.MSG_FC, GyotaibetuNipoConstants.MSG_SENTAKU);
//		}
        
        /* 
         * 2008/12/09追加 xayumi SV対応
         * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
        if(dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)){
            // SVコード必須チェック 
            if (GyotaibetuNipoCommon.isNull(svCd)) {
                throw new NotNullException(GyotaibetuNipoConstants.MSG_SV_CD);
            }
        }
	}
	
	/**
	 * 入力チェックをする<br>
	 * @param dto			検索条件Dto
	 * @param kikanFrom	対象期間From
	 * @param kikanTo		対象期間To
	 */
	private void kikanValidate(NipoRefConditionParameterDto dto, String kikanFrom, String kikanTo) {
		
		// 対象期間が期間指定又は期別指定の場合、対象期間Toチェック
		if ((TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())
			|| TaishoKikan.KIBETU.equals(dto.getTaishoKikanCd()))
			&& GyotaibetuNipoCommon.isNull(kikanTo)) {
				throw new NotNullException(GyotaibetuNipoConstants.MSG_KIKAN_TO);
		}
		// 対象期間が期間指定の場合
		if (TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())) {			
			// 対象期間From、To整合性チェック
			if(!KikanSiteiUtil.validateDateFromTo(kikanFrom, kikanTo)) {
				throw new ConstraintsViolationException
				(GyotaibetuNipoConstants.MSG_FROMTO, GyotaibetuNipoConstants.EMPTY);
			}
			// 制限期間チェック(９２日以内であること)
			if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
				throw new NotRelevantException
				(GyotaibetuNipoConstants.MSG_KIKAN, GyotaibetuNipoConstants.MSG_LIMIT_DAY);
			}
		}
	}
    
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
    	return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    public CtlBirdUserDao getCtlBirdUserDao() {
        return ctlBirdUserDao;
    }

    public void setCtlBirdUserDao(CtlBirdUserDao ctlBirdUserDao) {
        this.ctlBirdUserDao = ctlBirdUserDao;
    }
}
