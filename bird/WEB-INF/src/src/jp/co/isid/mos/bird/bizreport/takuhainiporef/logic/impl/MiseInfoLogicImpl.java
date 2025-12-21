package jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizreport.common.code.NipoZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.ShukeiKbn;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.code.TenpoShubetu;
import jp.co.isid.mos.bird.bizreport.common.code.UserType;
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.KikanSiteiUtil;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoCommon;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dao.MiseInfoDao;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dao.TakuMstInfoDao;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dao.TotalMiseCountDao;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.MiseDetail;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.MiseInfo;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.TakuMstInfo;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.MiseInfoLogic;
import jp.co.isid.mos.bird.common.dao.CtlBirdUserDao;
import jp.co.isid.mos.bird.common.entity.CtlBirdUser;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 店舗別の宅配売上情報を取得ロジック
 *
 * 変更履歴：2007/06/06 当月月報、月別月報の場合は日次用のSQLを使用するように変更
 * @author xjung
 */
public class MiseInfoLogicImpl implements MiseInfoLogic {

	/** 店舗別の宅配売上情報を取得ロジックID */
    public static final String LOGIC_ID = "BBR003L02";

    /** 店舗別宅配売上情報Dao */
	private MiseInfoDao miseInfoDao;

    /** 宅配種別情報Dao */
    private TakuMstInfoDao takuMstInfoDao;
 
    /** 対象店舗数取得Dao */
	private TotalMiseCountDao totalMiseCountDao;
    
    private CtlBirdUserDao ctlBirdUserDao;
    
	/**
	 * 店舗別の宅配売上情報を取得する
	 * @param dto 条件部情報DTO
	 * @return Map 店舗別宅配売上情報
	 */
    public Map execute(NipoRefConditionParameterDto dto) {
    	Map resultMap = new HashMap();
    	// 会社コード
		String companyCd = dto.getCompanyCd();
        // ユーザタイプ
    	String userType = dto.getBirdUserInfo().getMstUser().getUserTypeCd();
        // ユーザID
		String userId= dto.getBirdUserInfo().getMstUser().getUser_id();
        // 制限区分
		boolean limitFlg = dto.getBirdUserInfo().isLimit();
        // 対象期間
		String taishoKikan = dto.getTaishoKikanCd();
		// 前年データ種別
		String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
			dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();
		// 対象期間From、To取得
		String kikanFrom = TakuhaiNipoConstants.EMPTY;
		String kikanTo = TakuhaiNipoConstants.EMPTY;        
        
		// 対象期間が期日指定日報の場合
		if (TaishoKikan.DAY1.equals(taishoKikan)) {
			kikanFrom = dto.getKikanNipo();
        // 対象期間が当月の場合
   		} else if (TaishoKikan.MONTHAPP.equals(taishoKikan)) {
            kikanFrom = dto.getKikanCurrMonth();
			kikanTo = dto.getKikanCurrMonth();
        // 対象期間が月別の場合
        } else if (TaishoKikan.MONTH.equals(taishoKikan)) {
			kikanFrom = dto.getKikanYM();
			kikanTo = dto.getKikanYM();
        // 対象期間が期別期報の場合
		} else if (TaishoKikan.KIBETU.equals(taishoKikan)) {
			// 年、期から対象期間FromToを取得
			List kibetuList = KikanSiteiUtil.henkanKiMonth(
								dto.getKikanYear(),
								dto.getKikanKibetu());
			kikanFrom = (String) kibetuList.get(0);
			kikanTo = (String) kibetuList.get(1);
        // 対象期間が期間指定の場合
        } else if(TaishoKikan.DAYS.equals(taishoKikan)) {
			kikanFrom = dto.getKikanFrom();
			kikanTo = dto.getKikanTo();
		} else {
			throw new InvalidInputException(TakuhaiNipoConstants.MSG_TAISHO_KIKAN);
		}

		// 入力チェック
		validate(dto, kikanFrom, kikanTo);
 
		// 宅配種別情報
		List takuList = getTakuMstInfoDao().select();

		// 宅配種別情報データが０件の場合
		if (takuList == null || takuList.isEmpty()) {
			throw new NoResultException();
		} 

		List tempList = new ArrayList();
        List miseInfoList = new ArrayList();
        String dispMiseCd = TakuhaiNipoConstants.EMPTY;

		// オーナーユーザの場合
     	if (UserType.ONER.equals(userType)) {
            //オーナーコード取得
            String onerCd = TakuhaiNipoConstants.EMPTY;
            List ownerList = dto.getBirdUserInfo().getUserOner();
            for (Iterator it = ownerList.iterator(); it.hasNext();) {
                UIUserOner uIUserOner = (UIUserOner) it.next();
                if (companyCd.equals(uIUserOner.getCompanyCd())) {
                    onerCd = uIUserOner.getOnerCd();
                    break;
                }
            }
   
            // 前年データ種別
			zenDataShu = dto.getZenDataZennenOthCd();

			// 入力チェック
			validateOner(onerCd, zenDataShu);

			// 対象店舗数取得
			int totalTenpoCount = searchTotalTenpoCount(
									companyCd,
									onerCd,
									taishoKikan,
									kikanFrom,
									kikanTo);

			// 店舗別売上情報取得
    		tempList = searchMiseInfoOner(
		    				companyCd,
		    				onerCd,
		    				taishoKikan,
		    				kikanFrom,
		    				kikanTo,
		    				zenDataShu);

    		// 画面表示又はCSVダウンロード用のデータ作成
            for (int i = 0; i < tempList.size(); i++) {
                MiseInfo info = (MiseInfo) tempList.get(i);
                for (Iterator it = takuList.iterator(); it.hasNext();) {
                    TakuMstInfo takuInfo = (TakuMstInfo) it.next();
                    if (!TakuhaiNipoCommon.isNull(info.getTakuDetailCd())
                        && info.getTakuDetailCd().equals(takuInfo.getTakuDetailCd())) {
                        // 店舗別宅配売上情報設定
                        MiseDetail detail = setMiseDetailData(info, userType, zenDataShu, null);
                        // 宅配明細名称設定
                        detail.setTakuDetailName(takuInfo.getTakuDetailName());
                        // 前年比・構成比算出
                        miseInfoList.add(setCalulateData(detail, zenDataShu, dispMiseCd));
                        dispMiseCd = detail.getDispMiseCd();
                        break;
                    }
                }
            }

            // 対象店舗数をMapへ格納
            resultMap.put(TakuhaiNipoConstants.MAP_TENPO_COUNT, String.valueOf(totalTenpoCount));
            // 店別宅配売上情報リストをMapへ格納
            resultMap.put(TakuhaiNipoConstants.MAP_MISE_RST_LIST, miseInfoList);

            return resultMap;
    	}
 
        /** 本部ユーザーの場合 ***************************************************************/

        // 前年データ種別取得
        zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(dto.getTenpoShubetuCd()) ?
                dto.getZenDataZennenCd() : dto.getZenDataZennenOthCd();

        // 入力チェック
		validateHonbu(dto, zenDataShu);
        
/* 20081209追加 SV対応 Start -----------------------*/
        // SVコード
        String svCd    = dto.getSvCd();
        // SV名称
        String svName = "";
        
        /* 集計区分で『SV指定(担当店一覧)』が選択された場合 */
        if(dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)){
            // SVコードの前ゼロ付加
            CodeFormatter formatter = new CodeFormatter(8);
            formatter.setFormatPattern("00000000");
            if (!TakuhaiNipoCommon.isNull(svCd)) {                
                svCd = formatter.format(svCd, true);
            }
            //SVユーザー情報取得           
            CtlBirdUser ctlBirdUser = getCtlBirdUserDao().getBirdCtlBirdUser(svCd);
            if (ctlBirdUser==null) {
                throw new NoResultException();
            }
            svName = ctlBirdUser.getUserNameKj(); 
        }
/* 20081209追加 End --------------------------------*/ 
        String[] linkParams = TakuhaiNipoCommon.getLinkParams(dto.getLinkParams());
        // 店舗種別
        String tenpoShu =  dto.getTenpoShubetuCd();
        // 対象店舗
		String taishoTenpo = dto.getTaishoTenpoCd();
        // 集計区分
		String shukeiKbn = dto.getShukeiKbnCd();
        // 支部コード
		String sibuCd = dto.getSibuCd();
	    // リンク区分取得
		String linkKbnCd = TakuhaiNipoCommon.getLinkKbnCd(dto.getClassName(), shukeiKbn);
		// 宅配種別リストを取得
		List takuKbnCdList = getTakuDetailCdList(linkParams, takuList);
 
		// 店舗別売上情報取得
		tempList = searchMiseInfoHonbu(
				companyCd,
				userId,
				limitFlg,
				tenpoShu,
				taishoTenpo, 
				shukeiKbn,
				taishoKikan,
				kikanFrom,
				kikanTo,
				zenDataShu,
				linkKbnCd,
				sibuCd,
				takuKbnCdList,
                svCd);

        // 前年比・構成比算出及び宅配種別名称を取得
        for (int i = 0; i < tempList.size(); i++) {
            MiseInfo info = (MiseInfo) tempList.get(i);
            if ((ShukeiKbn.IN_RC.equals(shukeiKbn)
                    && !TakuhaiNipoCommon.isNull(info.getMiseCd()))
                || (ShukeiKbn.OUT_RC.equals(shukeiKbn)
                    && !(TakuhaiNipoCommon.isNull(info.getSibuCd())
                        || TakuhaiNipoCommon.isNull(info.getTakuDetailCd())))
                || (ShukeiKbn.SV_CD.equals(shukeiKbn)
                        && !TakuhaiNipoCommon.isNull(info.getTakuDetailCd()))){
                // 画面表示又はCSVダウンロード用のデータ作成
                for (Iterator it = takuList.iterator(); it.hasNext();) {
                    TakuMstInfo takuInfo = (TakuMstInfo) it.next();
                    if (!TakuhaiNipoCommon.isNull(takuInfo.getTakuDetailCd())
                        && takuInfo.getTakuDetailCd().equals(info.getTakuDetailCd())) {
                        // 店舗別宅配売上情報設定
                        MiseDetail detail = setMiseDetailData(info, userType, zenDataShu, shukeiKbn);
                        // 宅配明細名称設定
                        detail.setTakuDetailName(takuInfo.getTakuDetailName());
                        // 前年比・構成比算出
                        miseInfoList.add(setCalulateData(detail, zenDataShu, dispMiseCd));
                        dispMiseCd = detail.getDispMiseCd();
                        break;
                    }
                }
            }
        }

        // 店別宅配売上情報リストをMapへ格納
		resultMap.put(TakuhaiNipoConstants.MAP_MISE_RST_LIST, miseInfoList);
        //20081209追加 SVコード、SV名称
        resultMap.put( TakuhaiNipoConstants.MAP_SV_CD  , svCd );
        resultMap.put( TakuhaiNipoConstants.MAP_SV_NAME, svName );

    	return resultMap;
    }

	/**
	 * 入力チェックをする
	 * @param dto			条件部情報DTO
	 * @param kikanFrom	対象期間From
	 * @param kikanTo		対象期間To
	 */
	private void validate(NipoRefConditionParameterDto dto, String kikanFrom, String kikanTo) {
		if (dto == null) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_CONDITION);
		}
		if (TakuhaiNipoCommon.isNull(dto.getCompanyCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_COMPANY_CD);
		}
		if (TakuhaiNipoCommon.isNull(dto.getBirdUserInfo().getMstUser().getUser_id())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_USER_ID);
		}
		if (TakuhaiNipoCommon.isNull(dto.getTaishoKikanCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_TAISHO_KIKAN);
		}
		if (TakuhaiNipoCommon.isNull(kikanFrom)) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_KIKAN_FROM);
		}
		if ((TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())
			|| TaishoKikan.KIBETU.equals(dto.getTaishoKikanCd()))
			&& TakuhaiNipoCommon.isNull(kikanTo)) {
				throw new NotNullException(TakuhaiNipoConstants.MSG_KIKAN_TO);
		}
		if (TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())) {
			if(!KikanSiteiUtil.validateDateFromTo(kikanFrom, kikanTo)) {
				throw new ConstraintsViolationException(TakuhaiNipoConstants.MSG_FROMTO, TakuhaiNipoConstants.EMPTY);
			}

			if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
				throw new NotRelevantException(TakuhaiNipoConstants.MSG_KIKAN, TakuhaiNipoConstants.MSG_LIMIT_DAY);
			}
		}
	}

	/**
	 * 入力チェックをする【オーナーユーザのみ】
	 * @param onerCd			オーナーコード
	 * @param zenDataShubetu	前年データ種別
	 */
	private void validateOner(String onerCd, String zenDataShubetu) {
		if (TakuhaiNipoCommon.isNull(onerCd)) {
			throw new NotExistException(TakuhaiNipoConstants.MSG_ONER_CD);
		}
		if (TakuhaiNipoCommon.isNull(zenDataShubetu)) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_ZEN_DATA);
		}
	}

	/**
	 * 入力チェックをする【本部ユーザのみ】
	 * @param dto			条件部情報DTO
	 * @param zenDataShu	前年データ種別
	 */
	private void validateHonbu(NipoRefConditionParameterDto dto, String zenDataShu) {
		if (TakuhaiNipoCommon.isNull(dto.getTenpoShubetuCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_TENPO_SHU);
		}
		if (TakuhaiNipoCommon.isNull(dto.getTaishoTenpoCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_TAISHO_TENPO);
		}
		if (TakuhaiNipoCommon.isNull(dto.getShukeiKbnCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_SHUKEI_KBN);
		}
		if (TakuhaiNipoCommon.isNull(zenDataShu)) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_ZEN_DATA);
		}
//		if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())
//			&& TaishoTenpo.FC.equals(dto.getTaishoTenpoCd())) {
//			throw new IllegalOperationException(TakuhaiNipoConstants.MSG_FC, TakuhaiNipoConstants.MSG_SENTAKU);
//		}

        /* 
         * 2008/12/09追加 xayumi SV対応
         * 集計区分で『SV指定(担当店一覧)』が選択された場合 */
        if(dto.getShukeiKbnCd().equals(ShukeiKbn.SV_CD)){
            // SVコード必須チェック 
            if (TakuhaiNipoCommon.isNull(dto.getSvCd())) {
                throw new NotNullException(TakuhaiNipoConstants.MSG_SV_CD);
            }
        }else{
            if (TakuhaiNipoCommon.isNull(dto.getSibuCd())) {
                throw new NotNullException(TakuhaiNipoConstants.MSG_SIBU_CD);
            }
        }
	}

    /**
     * 対象宅配明細コードを取得する
     * @param subTagKbn     サブタグ区分
     * @param takuKbn       [宅配種別区分,名称,　宅配種別タイプ(有無orタイプ)
     * @param takuList      宅配種別情報リスト
     * @return List         対象宅配明細コード
     */
    private List getTakuDetailCdList(String[] takuKbn, List takuList) {
        List takuDetailCdList = new ArrayList();
        // 支部名が押下された場合
        if (TakuhaiNipoCommon.isNull(takuKbn[0])) {
            return takuDetailCdList;
        }
        // 種別が押下された場合
        if ("TYPE".equals(takuKbn[2])) {
            // サブタグがタイプ別の場合
            takuDetailCdList.add(takuKbn[0]);
            return takuDetailCdList;
        }
        // サブタグが有無別の場合、宅配明細コードを取得
        for (int i = 0; i < takuList.size(); i++) {
            TakuMstInfo info = (TakuMstInfo) takuList.get(i);
            if(takuKbn[0].equals(info.getTakuCd())) {
                takuDetailCdList.add(info.getTakuDetailCd().trim());
            }
        }
        // 宅配明細コードリストが０件の場合
        if (takuDetailCdList == null || takuDetailCdList.isEmpty()) {
            throw new NoResultException();
        }
        return takuDetailCdList;    
    } 

    /**
     * 対象店舗数を取得する
     * @param companyCd     会社コード
     * @param onerCd        オーナーID
     * @param taishoKikan   対象期間
     * @param kikanFrom     対象期間From
     * @param kikanTo       対象期間To
     * @return  int         対象店舗数
     */
    private int searchTotalTenpoCount(
        String companyCd,
        String onerCd,
        String taishoKikan,
        String kikanFrom,
        String kikanTo) {
        // 対象店舗数
        int tatalTenpoCount = 0;
        // 対象期間が期別期報の場合
        if (TaishoKikan.MONTHAPP.equals(taishoKikan) 
        		|| TaishoKikan.MONTH.equals(taishoKikan)
        		|| TaishoKikan.KIBETU.equals(taishoKikan)) {
            tatalTenpoCount = getTotalMiseCountDao().selectOnerCountGepo(
                    companyCd,
                    onerCd,
                    kikanFrom);
        }
        else {
            tatalTenpoCount = getTotalMiseCountDao().selectOnerCountNipo(
                    companyCd,
                    onerCd,
                    taishoKikan,
                    kikanFrom,
                    kikanTo);
        }

        return tatalTenpoCount;
    }

    /**
     * 店舗別宅配売上情報を取得する【オーナー用】
     * @param companyCd     会社コード
     * @param onerCd        オーナーID
     * @param taishoKikan   対象期間
     * @param kikanFrom     対象期間From
     * @param kikanTo       対象期間To
     * @param zenDataShu    前年データ種別
     * @return List         宅配売上情報リスト
     */
    private List searchMiseInfoOner(
        String companyCd,
        String onerCd,
        String taishoKikan,
        String kikanFrom,
        String kikanTo,
        String zenDataShu) {
        List tempList = new ArrayList();
 
        // 対象期間が『当月月報』or 『月別月報』or『期別期報』の場合
        if (TaishoKikan.MONTHAPP.equals(taishoKikan) 
        		|| TaishoKikan.MONTH.equals(taishoKikan)
                || TaishoKikan.KIBETU.equals(taishoKikan)) {
            tempList = getMiseInfoDao().selectOnerMiseGepoInfo(
                companyCd,
                onerCd,
                taishoKikan,
                kikanFrom,
                zenDataShu);
        }
        else {
            tempList = getMiseInfoDao().selectOnerMiseNipoInfo(
                companyCd,
                onerCd,
                taishoKikan,
                kikanFrom,
                kikanTo,
                zenDataShu);
        }

        // データが０件の場合
        if (tempList == null || tempList.isEmpty()) {
            throw new NoResultException();
        }
        MiseInfo info = (MiseInfo) tempList.get(0);
        if (TakuhaiNipoCommon.isNull(info.getMiseCd())) {
            throw new NoResultException();
        }

        return tempList;
    }

    /**
     * 店舗別宅配売上情報を取得する【本部用】
     * @param companyCd         会社コード
     * @param userId            ユーザID
     * @param limitFlg          制限区分
     * @param tenpoShu          店舗種別
     * @param taishoTenpo       対象店舗
     * @param shukeiKbn         集計区分
     * @param taishoKikan       対象期間
     * @param kikanFrom         対象期間From
     * @param kikanTo           対象期間To
     * @param zenDataShu        前年データ種別
     * @param linkKbnCd         リンク区分
     * @param sibuCd            支部コード
     * @param takuKbnCdList     宅配明細コードリスト
     * @param svCd              SVコード 2008/12/09追加 SV対応
     * 
     * @return List             店舗別宅配売上情報リスト
     */
    private List searchMiseInfoHonbu(
        String companyCd,
        String userId,
        boolean limitFlg,
        String tenpoShu,
        String taishoTenpo, 
        String shukeiKbn,
        String taishoKikan,
        String kikanFrom,
        String kikanTo,
        String zenDataShu,
        String linkKbnCd,
        String sibuCd,
        List takuKbnCdList,
        String svCd) {
        List tempList = new ArrayList();
        // 対象期間が『当月月報』or 『月別月報』or『期別期報』の場合
        if (TaishoKikan.MONTHAPP.equals(taishoKikan) 
        		|| TaishoKikan.MONTH.equals(taishoKikan)
                || TaishoKikan.KIBETU.equals(taishoKikan)) {
                tempList = getMiseInfoDao().selectMiseGepoInfo(
                    companyCd,
                    userId,
                    limitFlg,
                    tenpoShu,
                    taishoTenpo, 
                    shukeiKbn,
                    taishoKikan,
                    kikanFrom,
                    kikanTo,
                    zenDataShu,
                    linkKbnCd,
                    sibuCd,
                    takuKbnCdList,
                    svCd);
        }
        else {
            tempList = getMiseInfoDao().selectMiseNipoInfo(
                companyCd,
                userId,
                limitFlg,
                tenpoShu,
                taishoTenpo, 
                shukeiKbn,
                taishoKikan,
                kikanFrom,
                kikanTo,
                zenDataShu,
                linkKbnCd,
                sibuCd,
                takuKbnCdList,
                svCd);
        }

        // データが０件の場合
        if (tempList == null || tempList.isEmpty()) {
            throw new NoResultException();           
        }
        MiseInfo info = (MiseInfo) tempList.get(0);
        if (TakuhaiNipoCommon.isNull(info.getSibuCd())) {
            throw new NoResultException();
        }
 
        return tempList;
    }

	/**
	 * 店舗別宅配売上情報を設定する【画面表示・CSV用】
	 * @param info			店舗別宅配売上情報
	 * @param userType		ユーザタイプ
	 * @param zenDataShu	前年データ種別
     * @param shukeiKbn    集計区分 2008/12/09追加
     * 
	 * @return MiseDetail	店舗別宅配売上情報(画面・CSV用)
	 */
	private MiseDetail setMiseDetailData(
		MiseInfo info,
		String userType,
		String zenDataShu,
        String shukeiKbn){
		MiseDetail detail = new MiseDetail();

        // 支部コード・名称、ブロックコード・名称、店コード・名称
		detail.setSibuCd(info.getSibuCd());
        detail.setSibuName(info.getSibuName());
		detail.setBlockCd(info.getBlockCd());
		detail.setBlockName(info.getBlockName());
		detail.setMiseCd(info.getMiseCd());
		detail.setMiseName(info.getMiseName());
        //宅配営業日数
		detail.setEigyoDaysTaku(info.getEigyoDaysTaku());
    	detail.setUriage(TakuhaiNipoCommon.setBicEmpty(info.getUriage()));		
    	detail.setUriageTaku(TakuhaiNipoCommon.setBicEmpty(info.getUriageTaku()));
		detail.setKyakusu(TakuhaiNipoCommon.setBicEmpty(info.getKyakusu()));
		detail.setKyakusuTaku(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuTaku()));

		// 前年データ種別が同月営業日補正の場合
		if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {
            // 前年比対象・前年実績、前年宅配売上、前年客数、前年宅配件数          
			detail.setUriageZen(TakuhaiNipoCommon.setBicEmpty(info.getUriageZenNet()));
			detail.setUriageTakuZen(TakuhaiNipoCommon.setBicEmpty(info.getUriageTakuZenNet()));
			detail.setKyakusuZen(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuZenNet()));
			detail.setKyakusuTakuZen(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuTakuZenNet()));
        // 前年データ種別が同月営業日補正以外の場合
		} else {
            // 前年実績、前年宅配売上、前年客数、前年宅配件数
            detail.setEigyoDaysTakuHose(info.getEigyoDaysTaku());
			detail.setUriageZen(TakuhaiNipoCommon.setBicEmpty(info.getUriageZen()));
			detail.setUriageTakuZen(TakuhaiNipoCommon.setBicEmpty(info.getUriageTakuZen()));
			detail.setKyakusuZen(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuZen()));
			detail.setKyakusuTakuZen(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuTakuZen()));			
		}
        detail.setEigyoDaysTakuHose(info.getEigyoDaysTakuNet());
		detail.setUriageHose(TakuhaiNipoCommon.setBicEmpty(info.getUriageNet()));
		detail.setUriageTakuHose(TakuhaiNipoCommon.setBicEmpty(info.getUriageTakuNet()));
		detail.setKyakusuHose(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuNet()));
		detail.setKyakusuTakuHose(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuTakuNet()));

        // 店行：画面用店コード・名称、行CSSクラス、比TDCSSクラス
		if (!TakuhaiNipoCommon.isNull(info.getMiseCd())) {
			detail.setDispMiseCd(info.getMiseCd());
            String miseName = UserType.ONER.equals(userType) ? info.getMiseName() :
                info.getMiseCd() + TakuhaiNipoConstants.SPACE + info.getMiseName();
			detail.setDispMiseName(miseName);
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS);
        // ブロック合計：画面用店コード・名称、行CSSクラス、比TDCSSクラス
		} else if (!TakuhaiNipoCommon.isNull(info.getBlockCd())) {
			detail.setDispMiseCd(info.getBlockCd());
			detail.setDispMiseName(TakuhaiNipoCommon.concatSumTitle(info.getBlockName()));
			detail.setBlockName(TakuhaiNipoCommon.concatSumTitle(info.getBlockName()));
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS_SLAREA);
            
        /* 2008/12/09追加 
         * 集計区分で『SV指定(担当店一覧)』が選択された場合
         * 支部合計：画面用店コード・名称、行CSSクラス、比TDCSSクラス
         */
        } else if (shukeiKbn!=null && ShukeiKbn.SV_CD.equals(shukeiKbn)) {
            String dispMiseCd = "";
            String rClass     = "";
            String sumTitle   = "";    
            
            if(!TakuhaiNipoCommon.isNull(info.getSibuCd())){
                dispMiseCd = TakuhaiNipoConstants.TOTAL;
                sumTitle   = TakuhaiNipoConstants.LABEL_SIBU_SUM;
                rClass     = NipoRefConstants.CSS_TR_CLASS_HONBU;
                    
            } else if(!TakuhaiNipoCommon.isNull(info.getTakuDetailCd())) {
                dispMiseCd = TakuhaiNipoConstants.TOTAL_SV;
                sumTitle = TakuhaiNipoConstants.LABEL_TOTAL_SUM;      
                rClass   = NipoRefConstants.CSS_TR_CLASS_TOTAL;
            }
            detail.setDispMiseCd(dispMiseCd);
            detail.setDispMiseName(sumTitle);
            detail.setBlockName(sumTitle);
            detail.setRClass(rClass); 
            
        // 支部合計：画面用店コード・名称、行CSSクラス、比TDCSSクラス
		} else if (!TakuhaiNipoCommon.isNull(info.getTakuDetailCd())) {
			String sumTitle = UserType.ONER.equals(userType) ?
				TakuhaiNipoConstants.LABEL_TOTAL_SUM : TakuhaiNipoConstants.LABEL_SIBU_SUM;
			detail.setDispMiseCd(TakuhaiNipoConstants.TOTAL);
			detail.setDispMiseName(sumTitle);
			detail.setBlockName(sumTitle);
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS_TOTAL);
		}	

		return detail;
	}

	/**
     * 前年比と構成比を算出する 
	 * @param info         店舗別宅配売上情報      
	 * @param zenDataShu   前年データ種別
	 * @param dispSibuCd   前明細の画面表示用支部コード
	 * @return MiseDetail  店舗別宅配売上情報
	 */
	private MiseDetail setCalulateData(MiseDetail info, String zenDataShu, String dispSibuCd) {
		BigDecimal zenHiUriage = new BigDecimal(0);				// 前年比(売上)
		BigDecimal zenHiUriageTaku = new BigDecimal(0);			// 前年比(宅配)売上
		BigDecimal zenHiKyaku = new BigDecimal(0);				// 前年比(客数)
		BigDecimal zenHiKyakuTaku = new BigDecimal(0);			// 前年比(宅配)件数
		BigDecimal koseHiUri = new BigDecimal(0);				// 構成比・売上
		BigDecimal koseKyaku = new BigDecimal(0);				// 構成比・件数
		BigDecimal kyakuTanka = new BigDecimal(0);				// 客単価
		BigDecimal zenKyakuTanka = new BigDecimal(0);			// 前年客単価
		BigDecimal kyakuTankaHose = new BigDecimal(0);			// 客単価(補正)
		BigDecimal zenHikyakuTanka = new BigDecimal(0);			// 前年比
		BigDecimal takuHeikinUri = new BigDecimal(0);			// 宅配平均売上
		BigDecimal takuHeikinKyakusu = new BigDecimal(0);		// 宅配平均件数
		BigDecimal takuHeikinUriHose = new BigDecimal(0);		// 宅配平均売上
		BigDecimal takuHeikinKyakusuHose = new BigDecimal(0);	// 宅配平均件数

		// 構成比・売上:宅配売上/売上*100
		koseHiUri = Calculator.percentage(info.getUriageTaku(), info.getUriage(), 2);
		// 構成比・件数:宅配件数/客数*100
		koseKyaku = Calculator.percentage(info.getKyakusuTaku(), info.getKyakusu(), 2);
		// 客単価:客単価＝宅配売上/宅配件数
		kyakuTanka = Calculator.divide(info.getUriageTaku(), info.getKyakusuTaku(), 0);
		// 前年客単価:前年宅配売上/前年宅配件数
		zenKyakuTanka = Calculator.divide(info.getUriageTakuZen(), info.getKyakusuTakuZen(), 0);

		if (NipoZennenDataShubetu.CODE_HOSEI.equals(zenDataShu)) {	
			// 前年比(売上):売上/前年実績*100
			zenHiUriage = Calculator.percentage(info.getUriageHose(), info.getUriageZen(), 2);
			// 前年比(宅配)売上:宅配売上/前年実績(宅配)*100
			zenHiUriageTaku = Calculator.percentage(info.getUriageTakuHose(), info.getUriageTakuZen(), 2);
			// 前年比(客数):客数/前年客数*１００
			zenHiKyaku = Calculator.percentage(info.getKyakusuHose(), info.getKyakusuZen(), 2);
			// 前年比(宅配)件数:宅配件数/前年件数(宅配)*100
			zenHiKyakuTaku = Calculator.percentage(info.getKyakusuTakuHose(), info.getKyakusuTakuZen(), 2);
			// 客単価(補正):宅配売上/宅配件数
			kyakuTankaHose = Calculator.divide(info.getUriageTakuHose(), info.getKyakusuTakuHose(), 0);
			// 前年比(客単価):客単価/前年客単価*100
			zenHikyakuTanka = Calculator.percentage(kyakuTankaHose, zenKyakuTanka, 2);
			// 宅配平均売上:宅配売上/宅配日数
			takuHeikinUri = Calculator.divide(info.getUriageTaku(), info.getEigyoDaysTaku(), 0);
			// 宅配平均件数:宅配件数/宅配日数
			takuHeikinKyakusu = Calculator.divide(info.getKyakusuTaku(), info.getEigyoDaysTaku(), 0);
			// 宅配平均売上(補正):宅配売上/宅配日数
			takuHeikinUriHose = Calculator.divide(info.getUriageTakuHose(), info.getEigyoDaysTakuHose(), 0);
			// 宅配平均件数(補正):宅配件数/宅配日数
			takuHeikinKyakusuHose = Calculator.divide(info.getKyakusuTakuHose(), info.getEigyoDaysTakuHose(), 0);
		}
		else {
			// 前年比(売上):売上/前年実績*100
			zenHiUriage = Calculator.percentage(info.getUriage(), info.getUriageZen(), 2);
			// 前年比(宅配)売上:宅配売上/前年実績(宅配)*100
			zenHiUriageTaku = Calculator.percentage(info.getUriageTaku(), info.getUriageTakuZen(), 2);
			// 前年比(客数):客数/前年客数*１００
			zenHiKyaku = Calculator.percentage(info.getKyakusu(), info.getKyakusuZen(), 2);
			// 前年比(宅配)件数:宅配件数/前年件数(宅配)*100
			zenHiKyakuTaku = Calculator.percentage(info.getKyakusuTaku(), info.getKyakusuTakuZen(), 2);
			// 宅配平均売上:宅配売上/宅配日数
			takuHeikinUri = Calculator.divide(info.getUriageTaku(), info.getEigyoDaysTaku(), 0);
			// 宅配平均件数:宅配件数/宅配日数
			takuHeikinKyakusu = Calculator.divide(info.getKyakusuTaku(), info.getEigyoDaysTaku(), 0);
			// 前年比(客単価):客単価/前年客単価*100
			zenHikyakuTanka = Calculator.percentage(kyakuTanka, zenKyakuTanka, 2);
		}

		info.setZenHiUriage(zenHiUriage);
		info.setZenHiUriageTaku(zenHiUriageTaku);
		info.setZenHiKyakusu(zenHiKyaku);
		info.setZenHiKyakusuTaku(zenHiKyakuTaku);
		info.setKouseiHiUri(koseHiUri);
		info.setKouseiHiKyaku(koseKyaku);
		info.setKyakuTanka(kyakuTanka);
		info.setKyakuTankaHose(kyakuTankaHose);
		info.setZenKyakuTanka(zenKyakuTanka);
		info.setZenHiKyakuTanka(zenHikyakuTanka);
		info.setTakuHeikinUri(takuHeikinUri);
		info.setTakuHeikinKyakusu(takuHeikinKyakusu);
		info.setTakuHeikinUriHose(takuHeikinUriHose);
		info.setTakuHeikinKyakusuHose(takuHeikinKyakusuHose);
        // 前の画面用店コードと一致する場合店店名称へ空白文字設定
		if (dispSibuCd.equals(info.getDispMiseCd())) {
			info.setDispMiseName(TakuhaiNipoConstants.EMPTY);
		}
        // TdCssクラス設定
        info.setZenHiUriTdClass(TakuhaiNipoCommon.getHiTdCssClassName
            (info.getZenHiUriage(), info.getRClass()));
        info.setZenHiUriTakuTdClass(TakuhaiNipoCommon.getHiTdCssClassName
            (info.getZenHiUriageTaku(), info.getRClass()));
        info.setKouseiHiUriTdClass(NipoRefConstants.CSS_TR_CLASS.equals(info.getRClass())
            ? TakuhaiNipoConstants.TD_NUM_GREEN : TakuhaiNipoConstants.TD_NUM);
        info.setZenHiKyaTdClass(TakuhaiNipoCommon.getHiTdCssClassName
            (info.getZenHiKyakusu(), info.getRClass()));
        info.setZenHiKyaTakuTdClass(TakuhaiNipoCommon.getHiTdCssClassName
            (info.getZenHiKyakusuTaku(), info.getRClass()));
        info.setKouseiHiKyaTdClass(NipoRefConstants.CSS_TR_CLASS.equals(info.getRClass())
            ? TakuhaiNipoConstants.TD_NUM_GREEN : TakuhaiNipoConstants.TD_NUM);
        info.setZenKyakuTankaTdClass(TakuhaiNipoCommon.getHiTdCssClassName
            (info.getZenHiKyakuTanka(), info.getRClass()));

		return info;
	}

	/**
     * 店舗別宅配売上情報Daoを取得する
	 * @return MiseInfoDao 店舗別宅配売上情報Dao
	 */
	public MiseInfoDao getMiseInfoDao() {
		return this.miseInfoDao;
	}

	/**
     * 店舗別宅配売上情報Daoを設定する
	 * @param miseInfoDao  店舗別宅配売上情報Dao
	 */
	public void setMiseInfoDao(MiseInfoDao miseInfoDao) {
		this.miseInfoDao = miseInfoDao;
	}
    
    /**
     * 宅配種別情報Daooを取得する
     * @return TakuMstInfoDao 宅配種別情報Dao
     */
	public TakuMstInfoDao getTakuMstInfoDao() {
		return this.takuMstInfoDao;
	}
 
    /**
     * 宅配種別情報Daoを設定する
     * @param takuMstInfoDao  宅配種別情報Dao
     */
	public void setTakuMstInfoDao(TakuMstInfoDao takuMstInfoDao) {
		this.takuMstInfoDao = takuMstInfoDao;
	}
 
    /**
     * 対象店舗数取得Daoを取得する
     * @return TotalMiseCountDao 対象店舗数取得Dao
     */
	public TotalMiseCountDao getTotalMiseCountDao() {
		return this.totalMiseCountDao;
	}
  
    /**
     * 対象店舗数取得Daoを設定する
     * @param totalMiseCountDao  対象店舗数取得Dao
     */
	public void setTotalMiseCountDao(TotalMiseCountDao totalMiseCountDao) {
		this.totalMiseCountDao = totalMiseCountDao;
	}

    public CtlBirdUserDao getCtlBirdUserDao() {
        return ctlBirdUserDao;
    }

    public void setCtlBirdUserDao(CtlBirdUserDao ctlBirdUserDao) {
        this.ctlBirdUserDao = ctlBirdUserDao;
    }
}