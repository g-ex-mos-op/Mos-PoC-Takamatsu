/*
 * 作成日: 2006/3/27
 */
package jp.co.isid.mos.bird.bizsupport.plcontentconfirm.action.impl;

import java.math.BigDecimal;
import java.util.Iterator;

import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetUserOnerLogic;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetZenPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.plcontentconfirm.action.PlContentConfirmAction;
import jp.co.isid.mos.bird.bizsupport.plcontentconfirm.dto.PlContentConfirmDto;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.MSTUserMiseInfo;
import jp.co.isid.mos.bird.bizsupport.plregist.entity.TrnZenPLInfo;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

import org.seasar.framework.log.Logger;

/**
 * P/Lデータ内容確認　確認画面アクションクラス
 * @author itamoto
 */
public class PlContentConfirmActionImpl implements PlContentConfirmAction {

    private static Logger logger_ = Logger.getLogger(PlContentConfirmActionImpl.class);

    /* アクションID */
    public static String initialize_ACTION_ID        = "BBS003A04";
    public static String cancel_ACTION_ID            = "BBS003A07";
    
    /* VIEW_ID */
    public final String VIEW_ID                  = "BBS003";
    public final String plContentForm_VIEW_ID    = "BBS003V01";
    public final String plContentConfirm_VIEW_ID = "BBS003V02";
    public final String onerSearch_VIEW_ID       = "BCO006V01";

    /* P/L内容確認PDF抽出データ用Dto */
    private PlContentConfirmDto plContentConfirmDto;
    
    /* P/Lデータ入力用Dto */
    private PlRegistDto plRegistSecDto;
    /* P/Lデータエンティティ */
    private TrnPLInfo trnPLInfoSec;
    /* 前月P/Lデータエンティティ */
    private TrnZenPLInfo trnZenPLInfoSec;
    /* P/Lデータ取得ロジック */
    private GetPLDataLogic getPLDataLogic;
    /* 前月P/Lデータ取得ロジック */
    private GetZenPLDataLogic getZenPLDataLogic;
    /* ユーザー対応オーナーの検索Logic */
    private GetUserOnerLogic getUserOnerLogic;

    /* 編集画面表示時のタブ指定 デフォルト：0、月次損益計算書：1、内訳：2、借入金：3 */
    private int selectedTab;
    
    
    /* ログイン情報 */
    private BirdUserInfo birdUserInfo;
    /* システム日付情報 */
    private BirdDateInfo birdDateInfo;
    
    /**
     * 日付情報の設定
     * @return
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    /**
     * 日付情報の設定
     * @param birdDateInfo
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    /**
     * BIRDログイン情報の設定
     * @return
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    /**
     * BIRDログイン情報の設定
     * @param birdUserInfo
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    
    /**
	 * P/L内容確認PDF抽出データ用Dtoの設定
	 * @return plContentConfirmDto を戻します。
	 */
	public PlContentConfirmDto getPlContentConfirmDto() {
		return plContentConfirmDto;
	}
	/**
	 * P/L内容確認PDF抽出データ用Dtoの設定
	 * @param plContentConfirmDto plContentConfirmDto を設定。
	 */
	public void setPlContentConfirmDto(PlContentConfirmDto plContentConfirmDto) {
		this.plContentConfirmDto = plContentConfirmDto;
	}
	
    /**
	 * P/Lデータ入力用Dtoの設定
	 * @return plRegistDto を戻します。
	 */
	public PlRegistDto getPlRegistSecDto() {
		return plRegistSecDto;
	}
	/**
	 * P/Lデータ入力用Dtoの設定
	 * @param plRegistDto plRegistDto を設定。
	 */
	public void setPlRegistSecDto(PlRegistDto plRegistSecDto) {
		this.plRegistSecDto = plRegistSecDto;
	}
	
	/**
	 * P/Lデータエンティティの設定
	 * @return trnPLInfoSec を戻します。
	 */
	public TrnPLInfo getTrnPLInfoSec() {
		return trnPLInfoSec;
	}
	/**
	 * P/Lデータエンティティの設定
	 * @param trnPLInfoSec trnPLInfoSec を設定。
	 */
	public void setTrnPLInfoSec(TrnPLInfo trnPLInfoSec) {
		this.trnPLInfoSec = trnPLInfoSec;
	}

	/**
	 * 前月P/Lデータエンティティの設定
	 * @return trnZenPLInfoSec を戻します。
	 */
	public TrnZenPLInfo getTrnZenPLInfoSec() {
		return trnZenPLInfoSec;
	}
	/**
	 * 前月P/Lデータエンティティの設定
	 * @param trnZenPLInfoSec trnZenPLInfoSec を設定。
	 */
	public void setTrnZenPLInfoSec(TrnZenPLInfo trnZenPLInfoSec) {
		this.trnZenPLInfoSec = trnZenPLInfoSec;
	}

	/**
	 * P/Lデータ取得ロジックの設定
	 * @return getPLDataLogic を戻します。
	 */
	public GetPLDataLogic getGetPLDataLogic() {
		return getPLDataLogic;
	}
	/**
	 * P/Lデータ取得ロジックの設定
	 * @param getPLDataLogic getPLDataLogic を設定。
	 */
	public void setGetPLDataLogic(GetPLDataLogic getPLDataLogic) {
		this.getPLDataLogic = getPLDataLogic;
	}

	/**
	 * 前月P/Lデータ取得ロジックの設定
	 * @return getZenPLDataLogic を戻します。
	 */
	public GetZenPLDataLogic getGetZenPLDataLogic() {
		return getZenPLDataLogic;
	}
	/**
	 * 前月P/Lデータ取得ロジックの設定
	 * @param getZenPLDataLogic getZenPLDataLogic を設定。
	 */
	public void setGetZenPLDataLogic(GetZenPLDataLogic getZenPLDataLogic) {
		this.getZenPLDataLogic = getZenPLDataLogic;
	}

    /**
     * ユーザー対応オーナーの検索Logicの設定
     * @return getUserOnerLogic を戻します。
     */
    public GetUserOnerLogic getGetUserOnerLogic() {
        return getUserOnerLogic;
    }
    /**
     * ユーザー対応オーナーの検索Logicの設定
     * @param getUserOnerLogic getUserOnerLogic を設定。
     */
    public void setGetUserOnerLogic(GetUserOnerLogic getUserOnerLogic) {
        this.getUserOnerLogic = getUserOnerLogic;
    }
    
	/**
	 * 編集画面表示時のタブ指定の設定
	 * @return selectedTab を戻します。
	 */
	public int getSelectedTab() {
		return selectedTab;
	}
	/**
	 * 編集画面表示時のタブ指定の設定
	 * @param selectedTab selectedTab を設定。
	 */
	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
	}

	/**
     * 初期処理
     * @return 画面遷移情報
     */
	public String initialize() {

		// 通常初期処理
		if (plRegistSecDto.isInitFlg()) {
			// エンティティ初期化
			setTrnPLInfoSec(new TrnPLInfo());
			setTrnZenPLInfoSec(new TrnZenPLInfo());
			// タブの初期化
			plRegistSecDto.setSelectedTab(1);
	
			// ２．PLデータ入力の条件画面より遷移してきた場合
			// ロジック【PLデータの取得】を実行する。
			getPLDataLogic.execute(plRegistSecDto);

			// ①[PLデータ]の結果が０件の場合
			if (plRegistSecDto.getTrnPLInfo() == null) {
				// デフォルト値設定
				setDefaultPlData();
				if (plRegistSecDto.getTrnZenPLInfo() == null) {
					setDefaultZenPlData();
				} else {
					setTrnZenPLInfoSec(plRegistSecDto.getTrnZenPLInfo());
				}

				// [POS前月売上].売上高を、編集画面の売上高項目にセットし、他は空(0)で作成
				trnPLInfoSec
						.setUriage((plRegistSecDto.getTrnPosZenUriage() == null) ? BigDecimal
								.valueOf(0)
								: plRegistSecDto.getTrnPosZenUriage().getUriage());

				// 条件画面情報セット
				trnPLInfoSec.setPlYm(plRegistSecDto.getTargetYM());
				// 本社P/Lの場合
				if (plRegistSecDto.getPlFlg() == 1) {
					trnPLInfoSec.setPlType("0");
					trnPLInfoSec.setMiseCd(plRegistSecDto.getHeadOfficePlOnerCd());
					trnPLInfoSec.setMiseNameKj(plRegistSecDto.getHeadOfficePlOnerName());
                    plRegistSecDto.setMiseCloseDate("");//クローズ店クリア
				}
				// 店舗P/Lの場合
				else if (plRegistSecDto.getPlFlg() == 0) {
					String miseNameKj = null;
		            for (Iterator i = plRegistSecDto.getStorePlList().iterator(); i.hasNext();) {
		            	MSTUserMiseInfo entity = (MSTUserMiseInfo) i.next();
		            	if (entity.getMiseCd().equals(plRegistSecDto.getStorePlMiseCd())) {
		            		miseNameKj = entity.getMiseNameKj();
		            		break;
		            	}
		            }
					trnPLInfoSec.setPlType("1");
					trnPLInfoSec.setMiseCd(plRegistSecDto.getStorePlMiseCd());
					trnPLInfoSec.setMiseNameKj(miseNameKj);
				}
                
                
				// オーナコード&企業コード設定
				trnPLInfoSec.setOnerCd(plRegistSecDto.getOnerCd());
				trnPLInfoSec.setCompanyCd("00");
			}
			// ②[PLデータ]の結果がある場合
			// [PLデータ]の結果を編集画面の各項目にセット
			else {
				setTrnPLInfoSec(plRegistSecDto.getTrnPLInfo());
				if (plRegistSecDto.getTrnZenPLInfo() == null) {
					setDefaultZenPlData();
				} else {
					setTrnZenPLInfoSec(plRegistSecDto.getTrnZenPLInfo());
				}
			}
			
            // 店舗プルダウン表示設定
            setPullListMise();
            
            // 本社P/Lの場合
            if (plRegistSecDto.getPlFlg() == 1) {
                plRegistSecDto.setMiseCloseDate("");//クローズ店クリア
            }
            // 店舗P/Lの場合
            else {
                getGetUserOnerLogic().getMiseCloseDt(getPlRegistSecDto(),trnPLInfoSec.getMiseCd());
            }
            
			// ４．以下の項目設定を行う
			// 比率の設定
			setRatio();
			plRegistSecDto.setInitFlg(false);

			// PDF出力用データをDTOに格納
			// エンティティ
			plContentConfirmDto.setTrnPLInfo(trnPLInfoSec);
			plContentConfirmDto.setTrnZenPLInfo(trnZenPLInfoSec);
			// 比率
			plContentConfirmDto.setUriagedakaRatio(plRegistSecDto.getUriagedakaRatio());
			plContentConfirmDto.setUriagegenkaRatio(plRegistSecDto.getUriagegenkaRatio());
			plContentConfirmDto.setUriageSoRiekiRatio(plRegistSecDto.getUriageSoRiekiRatio());
		}
        return null;
    }

	
	/**
	 * 比率の設定
	 */
	private void setRatio() {
		// ４．以下の項目設定を行う
		// 売上高比率にはデフォルトで100%を表示
		plRegistSecDto.setUriagedakaRatio(BigDecimal.valueOf(100));

		// 売上原価比率には売上原価/売上高*100の小数点第一位まで
		BigDecimal sum = new BigDecimal("0.0");
		if (trnPLInfoSec.getUriagegenka() == null
				|| trnPLInfoSec.getUriagedaka() == null
				|| trnPLInfoSec.getUriagegenka().equals(BigDecimal.valueOf(0))
				|| trnPLInfoSec.getUriagedaka().equals(BigDecimal.valueOf(0))) {
			plRegistSecDto.setUriagegenkaRatio(BigDecimal.valueOf(0));
		} else {
			plRegistSecDto.setUriagegenkaRatio(sum.add(
					trnPLInfoSec.getUriagegenka()
							.multiply(BigDecimal.valueOf(100))).divide(
					trnPLInfoSec.getUriagedaka(), BigDecimal.ROUND_DOWN));
		}
		
		// 売上総利益比率には売上総利益/売上高*100の小数点第一位まで
		if (trnPLInfoSec.getUriageSoRieki() == null
				|| trnPLInfoSec.getUriagedaka() == null
				|| trnPLInfoSec.getUriageSoRieki().equals(BigDecimal.valueOf(0))
				|| trnPLInfoSec.getUriagedaka().equals(BigDecimal.valueOf(0))) {
			plRegistSecDto.setUriageSoRiekiRatio(BigDecimal.valueOf(0));
		} else {
			plRegistSecDto.setUriageSoRiekiRatio(sum.add(
					trnPLInfoSec.getUriageSoRieki().multiply(
							BigDecimal.valueOf(100))).divide(
					trnPLInfoSec.getUriagedaka(), BigDecimal.ROUND_DOWN));
		}
	}
	
    /**
     * 次の店舗の編集画面を表示
     * @return 画面遷移情報
     */
    public String next(){

        plRegistSecDto.setBirdUserInfo(birdUserInfo);
        plRegistSecDto.setBirdDateInfo(birdDateInfo);        
        
        // 保持情報クリア
        setTrnPLInfoSec(new TrnPLInfo());
        setTrnZenPLInfoSec(new TrnZenPLInfo());
        plRegistSecDto.clear();
        
        // ロジック【PLデータの取得】を実行する。
        plRegistSecDto.setTrnPLInfo(null);
        plRegistSecDto.setTrnZenPLInfo(null);
        getPLDataLogic.execute(plRegistSecDto);
        
        // 編集画面初期処理フラグON
        plRegistSecDto.setInitFlg(true);
        
        // [PLデータ]の結果が０件の場合
        plRegistSecDto.setPdfDLDisabledFlg(false);
        if (plRegistSecDto.getTrnPLInfo() == null) {
            plRegistSecDto.setPdfDLDisabledFlg(true);
            throw new NoResultException("");
        }
        
        // 店舗P/Lに設定 0:店舗PL 1:本部PL
        plRegistSecDto.setPlFlg(0);
        
        return plContentConfirm_VIEW_ID;
    }
    
    
    /**
	 * 戻る
	 * @return 画面遷移情報
	 */
    public String cancel(){
        
        // 店舗プルダウン初期表示設定
        //plRegistSecDto.setStorePlMiseCd(trnPLInfoSec.getMiseCd());
        
    	return plContentForm_VIEW_ID;
    }

    
    /**
     * 店舗プルダウンリストの表示を設定
     * ※現在プルダウンの後の店舗にする。 
     */
    private void setPullListMise() {
        
        // 店舗選択プルダウンのデフォルトを、現在の店舗の次の店舗にする。
        // 本社P/Lの場合、店舗リストの最初を指定
        if (plRegistSecDto.getPlFlg() == 1) {
            for (Iterator it = plRegistSecDto.getStorePlList().iterator(); it.hasNext();) {
                MSTUserMiseInfo mstUserMiseInfo = (MSTUserMiseInfo) it.next();
                plRegistSecDto.setStorePlMiseCd(mstUserMiseInfo.getMiseCd());
                break;
            }
        }
        // 店舗P/Lの場合、現在編集店舗の次の店舗を指定
        else if (plRegistSecDto.getPlFlg() == 0) {
            boolean findFlg = false;
            boolean setFlg = false;
            for (Iterator it = plRegistSecDto.getStorePlList().iterator(); it.hasNext();) {
                MSTUserMiseInfo mstUserMiseInfo = (MSTUserMiseInfo) it.next();
                if (findFlg) {
                    plRegistSecDto.setStorePlMiseCd(mstUserMiseInfo.getMiseCd());
                    setFlg = true;
                    break;
                }
                if (mstUserMiseInfo.getMiseCd().equals(trnPLInfoSec.getMiseCd())) {
                    findFlg = true;
                }
            }
            if (setFlg == false) {
                for (Iterator it = plRegistSecDto.getStorePlList().iterator(); it.hasNext();) {
                    MSTUserMiseInfo mstUserMiseInfo = (MSTUserMiseInfo) it.next();
                    plRegistSecDto.setStorePlMiseCd(mstUserMiseInfo.getMiseCd());
                    break;
                }               
            }
        }
    }
    
    
    /**
	 * デフォルトP/Lデータ設定
	 * @return 画面遷移情報
	 */
    private void setDefaultPlData() {
//    	// 月次損益計算書
//    	trnPLInfo.setUriagedaka(BigDecimal.valueOf(0));
//    	trnPLInfo.setHouteiFukuri(BigDecimal.valueOf(0));
//    	trnPLInfo.setShuzen(BigDecimal.valueOf(0));
//    	trnPLInfo.setUriagegenka(BigDecimal.valueOf(0));
//    	trnPLInfo.setFukuriKousei(BigDecimal.valueOf(0));
//    	trnPLInfo.setYobi(BigDecimal.valueOf(0));
//    	trnPLInfo.setUriageSoRieki(BigDecimal.valueOf(0));
//    	trnPLInfo.setKousai(BigDecimal.valueOf(0));
//    	trnPLInfo.setZappi(BigDecimal.valueOf(0));
//    	trnPLInfo.setSalary(BigDecimal.valueOf(0));
//    	trnPLInfo.setRyohi(BigDecimal.valueOf(0));
//    	trnPLInfo.setKeihiShokei(BigDecimal.valueOf(0));
//    	trnPLInfo.setYachin(BigDecimal.valueOf(0));
//    	trnPLInfo.setTusin(BigDecimal.valueOf(0));
//    	trnPLInfo.setShokyakuRieki(BigDecimal.valueOf(0));
//    	trnPLInfo.setSuikouHi(BigDecimal.valueOf(0));
//    	trnPLInfo.setLease(BigDecimal.valueOf(0));
//    	trnPLInfo.setGenkaShokyaku(BigDecimal.valueOf(0));
//    	trnPLInfo.setRoyalty(BigDecimal.valueOf(0));
//    	trnPLInfo.setSharyo(BigDecimal.valueOf(0));
//    	trnPLInfo.setEigaiShueki(BigDecimal.valueOf(0));
//    	trnPLInfo.setTesuryo(BigDecimal.valueOf(0));
//    	trnPLInfo.setSozei(BigDecimal.valueOf(0));
//    	trnPLInfo.setEigaiHiyo(BigDecimal.valueOf(0));
//    	trnPLInfo.setKoukoku(BigDecimal.valueOf(0));
//    	trnPLInfo.setHoken(BigDecimal.valueOf(0));
//    	trnPLInfo.setHonshahiHai(BigDecimal.valueOf(0));
//    	trnPLInfo.setShoumou(BigDecimal.valueOf(0));
//    	trnPLInfo.setUnchin(BigDecimal.valueOf(0));
//    	trnPLInfo.setRieki(BigDecimal.valueOf(0));
//    	
//    	// 内訳
//    	trnPLInfo.setUriage(BigDecimal.valueOf(0));
//    	trnPLInfo.setBuppan(BigDecimal.valueOf(0));
//    	trnPLInfo.setUriUchiwake(BigDecimal.valueOf(0));
//    	trnPLInfo.setElec(BigDecimal.valueOf(0));
//    	trnPLInfo.setGas(BigDecimal.valueOf(0));
//    	trnPLInfo.setWater(BigDecimal.valueOf(0));
//    	trnPLInfo.setOther(BigDecimal.valueOf(0));
//    	trnPLInfo.setSuikouUchiwake(BigDecimal.valueOf(0));
//    	trnPLInfo.setGenzairyoShire(BigDecimal.valueOf(0));
//    	trnPLInfo.setGenzairyoZaiko(BigDecimal.valueOf(0));
//    	trnPLInfo.setGenzairyoKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setYasaiShire(BigDecimal.valueOf(0));
//    	trnPLInfo.setYasaiZaiko(BigDecimal.valueOf(0));
//    	trnPLInfo.setYasaiKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setHouzaiShire(BigDecimal.valueOf(0));
//    	trnPLInfo.setHouzaiZaiko(BigDecimal.valueOf(0));
//    	trnPLInfo.setHouzaiKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setBuppanShire(BigDecimal.valueOf(0));
//    	trnPLInfo.setBuppanZaiko(BigDecimal.valueOf(0));
//    	trnPLInfo.setBuppanKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setTouSiireKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setTouZaikoKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setSashihikiKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setYakuinSalary(BigDecimal.valueOf(0));
//    	trnPLInfo.setYakuinBonus(BigDecimal.valueOf(0));
//    	trnPLInfo.setYakuinRetire(BigDecimal.valueOf(0));
//    	trnPLInfo.setYakuinKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setSalarySalary(BigDecimal.valueOf(0));
//    	trnPLInfo.setSalaryBonus(BigDecimal.valueOf(0));
//    	trnPLInfo.setSalaryRetire(BigDecimal.valueOf(0));
//    	trnPLInfo.setSalaryKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setZakkyuSalary(BigDecimal.valueOf(0));
//    	trnPLInfo.setZakkyuBonus(BigDecimal.valueOf(0));
//    	trnPLInfo.setZakkyuRetire(BigDecimal.valueOf(0));
//    	trnPLInfo.setZakkyuKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setKyuryoKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setBonusKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setRetireKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setSalaryUtiKei(BigDecimal.valueOf(0));
//    	
//    	// 借入金(メモ含む）
//    	trnPLInfo.setKashiireUp(BigDecimal.valueOf(0));
//    	trnPLInfo.setKashiireDown(BigDecimal.valueOf(0));
//    	trnPLInfo.setKashiireZandaka(BigDecimal.valueOf(0));
//    	trnPLInfo.setKappuUp(BigDecimal.valueOf(0));
//    	trnPLInfo.setKappuDown(BigDecimal.valueOf(0));
//    	trnPLInfo.setKappuZandaka(BigDecimal.valueOf(0));
//    	trnPLInfo.setLeaseUp(BigDecimal.valueOf(0));
//    	trnPLInfo.setLeaseDown(BigDecimal.valueOf(0));
//    	trnPLInfo.setLeaseZandaka(BigDecimal.valueOf(0));
//    	trnPLInfo.setTouZoukaKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setTouGenshoKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setTouZandakaKei(BigDecimal.valueOf(0));
//    	trnPLInfo.setMemo("");
    }

    /**
	 * デフォルト前月P/Lデータ設定
	 * @return 画面遷移情報
	 */
    private void setDefaultZenPlData() {
    	// 内訳
    	trnZenPLInfoSec.setGenzairyoZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfoSec.setYasaiZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfoSec.setHouzaiZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfoSec.setBuppanZaiko(BigDecimal.valueOf(0));
    	trnZenPLInfoSec.setTouZaikoKei(BigDecimal.valueOf(0));

    	// 借入金(メモ含む）
    	trnZenPLInfoSec.setKashiireZandaka(BigDecimal.valueOf(0));
    	trnZenPLInfoSec.setKappuZandaka(BigDecimal.valueOf(0));
    	trnZenPLInfoSec.setLeaseZandaka(BigDecimal.valueOf(0));
    	trnZenPLInfoSec.setTouZandakaKei(BigDecimal.valueOf(0));
    }
}

