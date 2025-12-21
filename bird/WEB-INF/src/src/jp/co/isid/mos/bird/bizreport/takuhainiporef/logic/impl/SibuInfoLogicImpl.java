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
import jp.co.isid.mos.bird.bizreport.common.common.NipoRefConstants;
import jp.co.isid.mos.bird.bizreport.common.dto.NipoRefConditionParameterDto;
import jp.co.isid.mos.bird.bizreport.common.util.KikanSiteiUtil;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoCommon;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.common.TakuhaiNipoConstants;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dao.SibuInfoDao;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dao.TakuMstInfoDao;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.dao.TotalMiseCountDao;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.SibuDetail;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.SibuInfo;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.entity.TakuMstInfo;
import jp.co.isid.mos.bird.bizreport.takuhainiporef.logic.SibuInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ConstraintsViolationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotRelevantException;
import jp.co.isid.mos.bird.framework.util.Calculator;

/**
 * 支部別の宅配売上情報を取得ロジック
 *
 * 変更履歴：2007/06/06 当月月報、月別月報の場合は日次用のSQLを使用するように変更
 * ⇒ 前年対象店、前年同月で期間指定と月報で前年実績の値が異なる
 * 条件：期間指定 2007/05/01～2007/05/31
 * 　　　当月月報 2007/05（テスト環境）
 * 
 * ⇒ 期間内にクローズした店がある場合に
 * 	日次：クローズした日までの値
 * 	月次：指定期間全部の値
 * ⇒ 営業日報、屋号日報は、宅配日報日次と同じ動作になっているので、宅配日報月次を修正する。
 * 	⇒ 当月月報、月別月報の場合は、日次用SQLをFrom：月初、To：月末で指定して使用する。
 * 
 * @author xjung
 * @deprecated 2008/03/24 T.Kinugawa 屋号別月次テーブル修正対応
 */
public class SibuInfoLogicImpl implements SibuInfoLogic {
    /** 支部別の宅配売上情報を取得ロジックID */
    public static final String LOGIC_ID = "BBR003L01";

	/** 支部別宅配売上情報取得Dao */
    private SibuInfoDao sibuInfoDao;

	/** 宅配種別情報取得Dao */
    private TakuMstInfoDao takuMstInfoDao;

	/** 対象店舗数取得Dao */
	private TotalMiseCountDao totalMiseCountDao;
    
	/**
	 * 支部別宅配売上情報を取得する
	 *
	 * @param dto           検索条件Dto
     * @param isDownLoadFlg CSVダウンロードアクションフラグ
	 * @return Map 支部別宅配売上情報(対象店舗数、有無別リスト、タイプ別リスト)
	 * 
	 * @deprecated 2008/03/24 T.Kinugawa 屋号別月次テーブル修正対応
	 * 
	 */
    public Map execute (NipoRefConditionParameterDto dto, boolean isDownLoadFlg) {
		Map resultMap = new HashMap();
		// 会社コード
		String companyCd = dto.getCompanyCd();
		// ユーザID
		String userId= dto.getBirdUserInfo().getMstUser().getUser_id();
		// 制限区分
		boolean limitFlg = dto.getBirdUserInfo().isLimit();
		// 店舗種別
		String tenpoShu =  dto.getTenpoShubetuCd();
		// 対象店舗
		String taishoTenpo = dto.getTaishoTenpoCd();
		// 集計区分
		String shukeiKbn = dto.getShukeiKbnCd();
		// 対象期間
		String taishoKikan = dto.getTaishoKikanCd();
		// 前年データ取得
		String zenDataShu = TenpoShubetu.CODE_ZENNEN.equals(tenpoShu) ?
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
		} else if (TaishoKikan.DAYS.equals(taishoKikan)) {
			kikanFrom = dto.getKikanFrom();
			kikanTo = dto.getKikanTo();
		} else {
			throw new InvalidInputException(TakuhaiNipoConstants.MSG_TAISHO_KIKAN);
		}

		// 入力チェック
		validate(dto, zenDataShu, kikanFrom, kikanTo);

        // 宅配種別情報
        List takuList = getTakuMstInfoDao().select();

        // 宅配種別情報データが０件の場合
        if (takuList == null || takuList.isEmpty()) {
            throw new NoResultException();
        }
        String dispType = TakuhaiNipoCommon.isDispTypeDetail(dto.getSubTagKbn())? "DETAIL":"";
		// 支部別宅配売上情報取得 
		List tmpSibuInfoList = searchSibuInfo(
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
                    			new ArrayList()
                    			, dispType);

        // アクションがCSVダウンロード以外の場合、対象店舗数取得
        if (!isDownLoadFlg) {
        	int tenpoCount = searchTotalTenpoCount(
                                companyCd,
                                userId,
                                limitFlg,
                                tenpoShu,
                                taishoTenpo, 
                                shukeiKbn,
                                taishoKikan,
                                kikanFrom,
                                kikanTo,
                                zenDataShu);
            // 対象店舗数格納
            resultMap.put(TakuhaiNipoConstants.MAP_TENPO_COUNT, String.valueOf(tenpoCount));
        }

        List sibuInfoList = tmpSibuInfoList;
        // 画面表示又はCSVダウンロード用のデータ作成
        List tempTypeList = new ArrayList();
//        int hoseCount = 0;
		for (int i = 0; i < sibuInfoList.size(); i++) {
			SibuInfo info = (SibuInfo) sibuInfoList.get(i);
            for (Iterator it = takuList.iterator(); it.hasNext();) {
                TakuMstInfo takuInfo = (TakuMstInfo) it.next();
                if (!TakuhaiNipoCommon.isNull(info.getTakuDetailCd())
                    && info.getTakuDetailCd().equals(takuInfo.getTakuDetailCd())) {
                    SibuDetail detail = new SibuDetail();
                    // 集計区分を含む且つ、(支部コード=NOT NULL、宅配明細コード=NOT NULL)
                    // 又は (本部コード=NULL、宅配明細コード=NOT NULL)の場合
                    // 集計区分を含まない且つ、宅配明細コード=NOT NULLの場合
                    if ((ShukeiKbn.IN_RC.equals(shukeiKbn)
                        && (!TakuhaiNipoCommon.isNull(info.getSibuCd()) 
                                && !TakuhaiNipoCommon.isNull(info.getTakuDetailCd()))
                            || (TakuhaiNipoCommon.isNull(info.getHonbuCd())
                                && !TakuhaiNipoCommon.isNull(info.getTakuDetailCd())))
                        || (ShukeiKbn.OUT_RC.equals(shukeiKbn)
                            && !TakuhaiNipoCommon.isNull(info.getTakuDetailCd()))) {
                        // 支部別宅配売上情報設定
                        detail = setSibuDetailData(info, zenDataShu);
                        // 宅配、宅配明細コード、名称設定
                        detail.setTakuCd(takuInfo.getTakuCd());
                        detail.setTakuName(takuInfo.getTakuName());
                        detail.setTakuDetailCd(takuInfo.getTakuDetailCd());
                        detail.setTakuDetailName(takuInfo.getTakuDetailName());
                        tempTypeList.add(detail); 
                    } 
                    break;
                }
            }
		}
        // タイプ別売上・件数：前年比、構成比算出、TDのCSS設定
        List typeRstList = new ArrayList();
        for (int i = 0; i < tempTypeList.size(); i++) {
            SibuDetail info = (SibuDetail) tempTypeList.get(i);
            SibuDetail lastInfo = (i==0? new SibuDetail() : (SibuDetail) tempTypeList.get(i-1));
            typeRstList.add((SibuDetail) setCalulateData(info, zenDataShu, lastInfo));
        }
        // タイプ別売上情報設定
        resultMap.put(TakuhaiNipoConstants.MAP_RST_LIST, (List) typeRstList);
        return resultMap;
    }

    /**
	 * 入力チェックをする
	 * @param dto			検索条件Dto
	 * @param zenDataShu	前年データ種別
	 * @param kikanFrom	対象期間From
	 * @param kikanTo		対象期間To
	 */
	private void validate(NipoRefConditionParameterDto dto, String zenDataShu, String kikanFrom, String kikanTo) {
		if (dto == null) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_CONDITION);
		}
		if (TakuhaiNipoCommon.isNull(dto.getCompanyCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_COMPANY_CD);
		}
		if (TakuhaiNipoCommon.isNull(dto.getBirdUserInfo().getMstUser().getUser_id())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_USER_ID);
		}
		if (TakuhaiNipoCommon.isNull(dto.getTenpoShubetuCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_TENPO_SHU);
		}
		if (TakuhaiNipoCommon.isNull(dto.getTaishoTenpoCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_TAISHO_TENPO);
		}
		if (TakuhaiNipoCommon.isNull(dto.getShukeiKbnCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_SHUKEI_KBN);
		}
		if (TakuhaiNipoCommon.isNull(dto.getTaishoKikanCd())) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_TAISHO_KIKAN);
		}
		if (TakuhaiNipoCommon.isNull(kikanFrom)) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_KIKAN_FROM);
		}
		// 対象期間が期間指定又は期別指定の場合、対象期間Toチェック
		if ((TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())
			|| TaishoKikan.KIBETU.equals(dto.getTaishoKikanCd()))
			&& TakuhaiNipoCommon.isNull(kikanTo)) {
				throw new NotNullException(TakuhaiNipoConstants.MSG_KIKAN_TO);
		}
		// 対象期間が期間指定の場合
		if (TaishoKikan.DAYS.equals(dto.getTaishoKikanCd())) {			
			// 対象期間From、To整合性チェック
			if(!KikanSiteiUtil.validateDateFromTo(kikanFrom, kikanTo)) {
				throw new ConstraintsViolationException
				(TakuhaiNipoConstants.MSG_FROMTO, TakuhaiNipoConstants.EMPTY);
			}
			// 制限期間チェック(９２日以内であること)
			if(!KikanSiteiUtil.validateDateLimit(kikanFrom, kikanTo)) {
				throw new NotRelevantException
				(TakuhaiNipoConstants.MSG_KIKAN, TakuhaiNipoConstants.MSG_LIMIT_DAY);
			}
		}
		if (TakuhaiNipoCommon.isNull(zenDataShu)) {
			throw new NotNullException(TakuhaiNipoConstants.MSG_ZEN_DATA);
		}
		// 集計区分＝直営業店を含む且つ、対象店舗＝FCのみの場合、エラー
//		if (ShukeiKbn.IN_RC.equals(dto.getShukeiKbnCd())
//			&& TaishoTenpo.FC.equals(dto.getTaishoTenpoCd())) {
//			throw new IllegalOperationException
//				(TakuhaiNipoConstants.MSG_FC, TakuhaiNipoConstants.MSG_SENTAKU);
//		}
	}

    /**
     * 支部別宅配売上情報を取得する
     * @param companyCd     会社コード
     * @param userId        ユーザID
     * @param limitFlg      制限区分
     * @param tenpoShu      店舗種別
     * @param taishoTenpo   対象店舗
     * @param shukeiKbn     集計区分
     * @param taishoKikan   対象期間
     * @param kikanFrom     対象期間From
     * @param kikanTo       対象期間To
     * @param zenDataShu    前年データ種別
     * @param sibuCd        支部コードリスト
     * @return List         支部別宅配売上情報リスト
     */
    private List searchSibuInfo (
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
        List sibuCd
        ,String dispType) {
        // 支部別宅配売上情報リスト
        List sibuInfoList = new ArrayList();

        // 対象期間が『当月月報』or 『月別月報』or『期別期報』の場合
        if (TaishoKikan.MONTHAPP.equals(taishoKikan) 
        		|| TaishoKikan.MONTH.equals(taishoKikan)
                || TaishoKikan.KIBETU.equals(taishoKikan)) {
            sibuInfoList = getSibuInfoDao().selectSibuGepoInfo (
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
                sibuCd
                ,dispType);
        }
        else {
            sibuInfoList = getSibuInfoDao().selectSibuNipoInfo (
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
                    sibuCd
                    , dispType);
        }

        // 支部別宅配売上情報データが０件の場合
        if (sibuInfoList == null || sibuInfoList.isEmpty()) {
            throw new NoResultException();
        }    
        SibuInfo info = (SibuInfo) sibuInfoList.get(0);
        if (TakuhaiNipoCommon.isNull(info.getSibuCd())) {
            throw new NoResultException();
        }
        return sibuInfoList;
    }

    /**
     * 対象店舗数を取得する
     * @param companyCd     会社コード
     * @param userId        ユーザID
     * @param limitFlg      制限区分
     * @param tenpoShu      店舗種別
     * @param taishoTenpo   対象店舗
     * @param shukeiKbn     集計区分
     * @param taishoKikan   対象期間
     * @param kikanFrom     対象期間From
     * @param kikanTo       対象期間To
     * @param zenDataShu    前年データ種別
     * @return int          対象店舗数
     */
    private int searchTotalTenpoCount (
        String companyCd,
        String userId,
        boolean limitFlg,
        String tenpoShu,
        String taishoTenpo,
        String shukeiKbn,
        String taishoKikan,
        String kikanFrom,
        String kikanTo,
        String zenDataShu) {
        // 対象店舗数
        int tatalTenpoCount = 0;

        // 対象期間が『当月月報』or 『月別月報』or『期別期報』の場合
        if (TaishoKikan.MONTHAPP.equals(taishoKikan) 
        		|| TaishoKikan.MONTH.equals(taishoKikan)
                || TaishoKikan.KIBETU.equals(taishoKikan)) {
            // 対象店舗数
            tatalTenpoCount = getTotalMiseCountDao().selectCountGepo (
                    companyCd,
                    userId,
                    limitFlg,
                    tenpoShu,
                    taishoTenpo, 
                    shukeiKbn,
                    taishoKikan,
                    kikanFrom,
                    kikanTo,
                    zenDataShu);
        // 対象期間が期日指定日報、期間指定の場合
        }
        else {
            // 対象店舗数
            tatalTenpoCount = getTotalMiseCountDao().selectCountNipo (
                    companyCd,
                    userId,
                    limitFlg,
                    tenpoShu,
                    taishoTenpo, 
                    shukeiKbn,
                    taishoKikan,
                    kikanFrom,
                    kikanTo,
                    zenDataShu);
        }

        return tatalTenpoCount;
    }

    /**
     * 支部別宅配売上情報を設定する【画面表示・CSV用】
     * @param info			支部別宅配売上情報
     * @param hose			支部別宅配売上情報(前年同月営業日補正)
     * @param zenDataShu	前年データ種別
     * @return SibuDetail	支部別宅配売上情報(画面・CSV用)
     */
    private SibuDetail setSibuDetailData(SibuInfo info, String zenDataShu){
      	SibuDetail detail = new SibuDetail();

      	// 本部コード・名称、事業本部コード・名称、エリアコード・名称、支部コード・名称
		detail.setHonbuCd(info.getHonbuCd());
		detail.setHonbuName(info.getHonbuName());
		detail.setJigyouCd(info.getJigyouCd());
		detail.setJigyouName(info.getJigyouName());
		detail.setSlareaCd(info.getSlareaCd());
		detail.setSlareaName(info.getSlareaName());
		detail.setSibuCd(info.getSibuCd());
		detail.setSibuName(info.getSibuName());
        // 宅配実績店舗数、売上、宅配売上、客数、宅配件数
    	detail.setTenpoCount(info.getTenpoCount());
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
			detail.setUriageZen(TakuhaiNipoCommon.setBicEmpty(info.getUriageZen()));
			detail.setUriageTakuZen(TakuhaiNipoCommon.setBicEmpty(info.getUriageTakuZen()));
			detail.setKyakusuZen(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuZen()));
			detail.setKyakusuTakuZen(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuTakuZen()));			
		}
        // 前年比対象・店舗数、売上、宅配売上、客数、宅配件数
		detail.setTenpoCountHose(info.getTenpoCountNet());
		detail.setUriageHose(TakuhaiNipoCommon.setBicEmpty(info.getUriageNet()));
		detail.setUriageTakuHose(TakuhaiNipoCommon.setBicEmpty(info.getUriageTakuNet()));
		detail.setKyakusuHose(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuNet()));
		detail.setKyakusuTakuHose(TakuhaiNipoCommon.setBicEmpty(info.getKyakusuTakuNet()));

		// 画面表示用データ設定(支部コード・名称取得、行・比TDCSSクラス)
		if (!TakuhaiNipoCommon.isNull(info.getSibuCd())) {
			// 支部計の場合
			detail.setDispSibuCd(info.getSibuCd());
			detail.setDispSibuName(info.getSibuName());
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS);
		} else if (!TakuhaiNipoCommon.isNull(info.getSlareaCd())) {
			// エリア計の場合
			detail.setDispSibuCd(info.getSlareaCd());
			detail.setDispSibuName(TakuhaiNipoCommon.concatSumTitle(info.getSlareaName()));
			detail.setSlareaName(TakuhaiNipoCommon.concatSumTitle(info.getSlareaName()));
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS_SLAREA);
		} else if (!TakuhaiNipoCommon.isNull(info.getJigyouCd())) {
			// 事業本部計の場合
			detail.setDispSibuCd(info.getJigyouCd());	
			detail.setDispSibuName(TakuhaiNipoCommon.concatSumTitle(info.getJigyouName()));
			detail.setJigyouName(TakuhaiNipoCommon.concatSumTitle(info.getJigyouName()));
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS_JIGYOU);

		} else if (!TakuhaiNipoCommon.isNull(info.getHonbuCd())) {
			// 本部計の場合
			detail.setDispSibuCd(info.getHonbuCd());
			detail.setDispSibuName(TakuhaiNipoCommon.concatSumTitle(info.getHonbuName()));
			detail.setHonbuName(TakuhaiNipoCommon.concatSumTitle(info.getHonbuName()));
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS_HONBU);
		} else if (TakuhaiNipoCommon.isNull(info.getHonbuCd()) && !TakuhaiNipoCommon.isNull(info.getTakuDetailCd())) {
			// 総合計
			detail.setDispSibuCd(TakuhaiNipoConstants.TOTAL);
			detail.setDispSibuName(TakuhaiNipoConstants.LABEL_TOTAL_SUM);
			detail.setHonbuName(TakuhaiNipoConstants.LABEL_TOTAL_SUM);
			detail.setRClass(NipoRefConstants.CSS_TR_CLASS_TOTAL);
		}

    	return detail;
    }
 

    /**
     * 前年比と構成比を算出及びCSSクラスの設定をする 
     * @param info			支部別宅配売上情報
     * @param zenDataShu	前年データ種別
     * @param dispSibuCd	支部コード(画面表示用)
     * @return SibuDetail	支部別宅配売上情報(画面・CSV用)
     */
    private SibuDetail setCalulateData(SibuDetail info, String zenDataShu, SibuDetail lastInfo) {
		BigDecimal zenHiUriage = new BigDecimal(0);			// 前年比(売上)
		BigDecimal zenHiUriageTaku = new BigDecimal(0);		// 前年比(宅配)売上
		BigDecimal zenHiKyaku = new BigDecimal(0);			// 前年比(客数)
		BigDecimal zenHiKyakuTaku = new BigDecimal(0);		// 前年比(宅配)件数
		BigDecimal koseHiUri = new BigDecimal(0);			// 構成比・売上
		BigDecimal koseKyaku = new BigDecimal(0);			// 構成比・件数
		BigDecimal kyakuTanka = new BigDecimal(0);			// 客単価
		BigDecimal zenKyakuTanka = new BigDecimal(0);		// 前年客単価
		BigDecimal kyakuTankaHose = new BigDecimal(0);		// 客単価(補正)
		BigDecimal zenHikyakuTanka = new BigDecimal(0);		// 前年比

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
    		// 客単価(補正):宅配売上(補正)/宅配件数(補正)
    		kyakuTankaHose = Calculator.divide(info.getUriageTakuHose(), info.getKyakusuTakuHose(), 0);
    		// 前年比(客単価):宅配客単価(補正)/宅配前年客単価(補正)*100
    		zenHikyakuTanka = Calculator.percentage(kyakuTankaHose, zenKyakuTanka, 2);
    	} else {
    		// 前年比(売上):売上/前年実績*100
    		zenHiUriage = Calculator.percentage(info.getUriage(), info.getUriageZen(), 2);
    		// 前年比(宅配)売上:宅配売上/前年実績(宅配)*100
    		zenHiUriageTaku = Calculator.percentage(info.getUriageTaku(), info.getUriageTakuZen(), 2);
    		// 前年比(客数):客数/前年客数*１００
    		zenHiKyaku = Calculator.percentage(info.getKyakusu(), info.getKyakusuZen(), 2);
    		// 前年比(宅配)件数:宅配件数/前年件数(宅配)*100
    		zenHiKyakuTaku = Calculator.percentage(info.getKyakusuTaku(), info.getKyakusuTakuZen(), 2);
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

		// 前明細と画面表示用の支部コードが一致する場合、空白文字を設定		
//		info.setDispFlg(dispSibuCd.equals(lastInfo.getDispSibuCd()) ? false : true);
		boolean dispFlg = true;
		if(nullClear(info.getHonbuCd()).equals(nullClear(lastInfo.getHonbuCd()))
            && nullClear(info.getJigyouCd()).equals(nullClear(lastInfo.getJigyouCd()))
              && nullClear(info.getSlareaCd()).equals(nullClear(lastInfo.getSlareaCd()))
                && info.getDispSibuCd().equals(lastInfo.getDispSibuCd()) )
        {
			dispFlg = false;
		}
		info.setDispFlg(dispFlg);
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
     * 支部別宅配売上情報取得Daoを取得する
	 * @return SibuInfoDao 支部別宅配売上情報取得Dao
	 */
	public SibuInfoDao getSibuInfoDao() {
		return this.sibuInfoDao;
	}

	/**
     * 支部別宅配売上情報取得Daoを設定する
	 * @param sibuInfoDao 支部別宅配売上情報取得Dao
	 */
	public void setSibuInfoDao(SibuInfoDao sibuInfoDao) {
		this.sibuInfoDao = sibuInfoDao;
	}

    /**
     * 宅配種別情報取得Daoを取得する
     * @return TakuMstInfoDao 宅配種別情報取得Dao
     */
	public TakuMstInfoDao getTakuMstInfoDao() {
		return takuMstInfoDao;
	}
 
    /**
     * 宅配種別情報取得Daoを設定する
     * @param takuMstInfoDao 宅配種別情報取得Dao
     */
	public void setTakuMstInfoDao(TakuMstInfoDao takuMstInfoDao) {
		this.takuMstInfoDao = takuMstInfoDao;
	}
 
    /**
     * 対象店舗数取得Daoを取得する
     * @return TotalMiseCountDao 対象店舗数取得Dao
     */
	public TotalMiseCountDao getTotalMiseCountDao() {
		return totalMiseCountDao;
	}
 
    /**
     * 対象店舗数取得Daoを設定する
     * @param totalMiseCountDao 対象店舗数取得Dao
     */
	public void setTotalMiseCountDao(TotalMiseCountDao totalMiseCountDao) {
		this.totalMiseCountDao = totalMiseCountDao;
	}
	/**
	 * Null値ブランク変換処理
	 * @param word
	 * @return
	 */
	private String nullClear(String word) {
		if(word == null){
			return "";
		}
		return word;
	}

}