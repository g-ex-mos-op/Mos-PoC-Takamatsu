package jp.co.isid.mos.bird.bizsupport.campcheckamount.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.campcheckamount.dto.CampCheckAmountDto;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.dto.CampCheckAmountSaveDto;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UISetQuantityInfo;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.entity.UISpotQuantityInfo;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetSetQuantityInfoLogic;
import jp.co.isid.mos.bird.bizsupport.campcheckamount.logic.GetSpotQuantityInfoLogic;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.common.logic.GetOnerLogic;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.InputConstraintException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;

/**
 *　キャンペーん設定数量確認　共通クラス
 *
 * @author xlee
 */
public class CampCheckAmountCommon {

    /**
     * Null判断処理を行う
     * @param str チェック対象の文字列
     * @return boolean true：空かNullの場合
     */
    public static boolean isNull(String str) {
        return (str != null && str.trim().length() > 0) ? false : true;
    }

	/**
	 * POS受注開始日を取得する
	 * @param sysDate システム日付
	 * @return String POS受注開始日
	 */
    public static String getPosPrevDate(String sysDate) {
    	String posDate = "";
    	try {
    		posDate = DateManager.getPrevDate(sysDate,2);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return posDate;
    }

	/**
	 * POS受注終了日を取得する
	 * @param sysDate システム日付
	 * @return String POS受注開始日
	 */
    public static String getPosEndDatePrev1(String sysDate) {
    	String posEndDate = "";
    	try {
    		posEndDate = DateManager.getPrevDate(sysDate,1);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return posEndDate;
    }



    /**
	 * 検索処理を行う
	 * @param campCheckAmountDto DTO
	 * @param getSetQuantityInfoLogic 設定数量ロジック
	 * @param getSpotQuantityInfoLogic スポット数量ロジック
	 * @return List 検索結果
	 */
    public static CampCheckAmountDto getSearchKbn(
    		CampCheckAmountDto campCheckAmountDto,
    		GetSetQuantityInfoLogic getSetQuantityInfoLogic,
    		GetSpotQuantityInfoLogic getSpotQuantityInfoLogic,
    		GetMiseLogic getMiseLogic,
    		GetOnerLogic getOnerLogic) {

    	//店コードの桁数を５桁にあわせる。
    	campCheckAmountDto.setTaishoMiseCd(formatMiseCd(campCheckAmountDto.getTaishoMiseCd()));

    	//キャンペーンNOが""であるのは対象なしのみ：設定数量情報の取得ロジックを実行
    	checkValidator(
    			"",
    			campCheckAmountDto.getTaishoCond(),
    			campCheckAmountDto.getCompanyCd(),
    			campCheckAmountDto.getTaishoCmpNo(),
    			campCheckAmountDto.getTaishoMiseCd(),
    			campCheckAmountDto.getTaishoOnerCd(),
    			campCheckAmountDto.getTaishoSibuCd(),
    			campCheckAmountDto.getTaishoBlockCd(),
    			campCheckAmountDto.getSysDate(),
    			campCheckAmountDto.getUserTypeCd(),
    			getMiseLogic,
    			getOnerLogic);

    	List resultList_set = new ArrayList();
    	List resultList_spot = new ArrayList();

    	if(isNull(campCheckAmountDto.getTaishoCmpNo())) {
    		//キャンペーンコードが存在しない場合,設定数量のみ
    		campCheckAmountDto.setTabViewFlg(CampCheckAmountConstants.TAB_VIEW_SET);
    		campCheckAmountDto =
    			getResult(campCheckAmountDto, getSetQuantityInfoLogic,
    					getSpotQuantityInfoLogic, getMiseLogic, getOnerLogic,
    					CampCheckAmountConstants.PROC_KBN_EXEC);
    	} else {
        	//設定数量タブの場合
    		if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(campCheckAmountDto.getUserTypeCd())) {
    			//本部ユーザの場合
    			resultList_set =
    				getSetQuantityInfoLogic.execute(campCheckAmountDto.getTaishoCond(),
    					campCheckAmountDto.getCompanyCd(),
    					campCheckAmountDto.getTaishoMiseCd(),
    					campCheckAmountDto.getTaishoSibuCd(),
    					campCheckAmountDto.getTaishoBlockCd(),
    					campCheckAmountDto.getSysDate(),
    					campCheckAmountDto.getOnerCdList(),
    					false);


    		} else if(CampCheckAmountConstants.USER_TYPE_ONER.equals(campCheckAmountDto.getUserTypeCd())) {
    			//オーナーユーザの場合
    			resultList_set =
    				getSetQuantityInfoLogic.execute(campCheckAmountDto.getCompanyCd(),
    					campCheckAmountDto.getSysDate(),
    					campCheckAmountDto.getOnerCdList(),
    					false);
    		}
    		//ディフォルト：スポット数量
    		if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(campCheckAmountDto.getUserTypeCd())) {
    			//本部ユーザの場合
    			resultList_spot =
    				getSpotQuantityInfoLogic.execute(campCheckAmountDto.getTaishoCond(),
    						campCheckAmountDto.getCompanyCd(),
    						campCheckAmountDto.getTaishoCmpNo(),
    						campCheckAmountDto.getTaishoMiseCd(),
    						campCheckAmountDto.getTaishoSibuCd(),
    						campCheckAmountDto.getTaishoBlockCd(),
    						campCheckAmountDto.getSysDate(),
        					campCheckAmountDto.getOnerCdList());
    		} else if(CampCheckAmountConstants.USER_TYPE_ONER.equals(campCheckAmountDto.getUserTypeCd())) {
    			//オーナーユーザの場合
    			resultList_spot =
    				getSpotQuantityInfoLogic.execute(campCheckAmountDto.getCompanyCd(),
    					campCheckAmountDto.getTaishoCmpNo(),
    					campCheckAmountDto.getSysDate(),
    					campCheckAmountDto.getOnerCdList());
    		}
    		if(!(resultList_set == null || resultList_set.size() == 0) &&
    				!(resultList_spot == null || resultList_spot.size() == 0)) {
    			if(isNull(campCheckAmountDto.getTabKbn())) {
    				campCheckAmountDto.setTabKbn(CampCheckAmountConstants.TAB_KBN_SPOT);
    			}
    			campCheckAmountDto.setTabViewFlg(CampCheckAmountConstants.TAB_VIEW_BOTH);
    		} else {
    			if(!(resultList_set == null || resultList_set.size() == 0)) {
    				campCheckAmountDto.setTabKbn(CampCheckAmountConstants.TAB_KBN_SET);
    				campCheckAmountDto.setTabViewFlg(CampCheckAmountConstants.TAB_VIEW_SET);
    			} else if(!(resultList_spot == null || resultList_spot.size() == 0)) {
    				campCheckAmountDto.setTabKbn(CampCheckAmountConstants.TAB_KBN_SPOT);
    				campCheckAmountDto.setTabViewFlg(CampCheckAmountConstants.TAB_VIEW_SPOT);
    			} else {
    				throw new NoResultException(); //E0102
    			}
    		}
    		campCheckAmountDto =
    			getResult(campCheckAmountDto, getSetQuantityInfoLogic,
    					getSpotQuantityInfoLogic, getMiseLogic, getOnerLogic,
    					CampCheckAmountConstants.PROC_KBN_EXEC);
    	}

		return campCheckAmountDto;
    }

	/**
	 * 検索処理を行う
	 * @param campCheckAmountDto DTO
	 * @param getSetQuantityInfoLogic 設定数量ロジック
	 * @param getSpotQuantityInfoLogic スポット数量ロジック
	 * @return List 検索結果
	 */
    public static CampCheckAmountDto getResult(
    		CampCheckAmountDto campCheckAmountDto,
    		GetSetQuantityInfoLogic getSetQuantityInfoLogic,
    		GetSpotQuantityInfoLogic getSpotQuantityInfoLogic,
    		GetMiseLogic getMiseLogic,
    		GetOnerLogic getOnerLogic,
    		String procKbn) {

    	if(CampCheckAmountConstants.PROC_KBN_CHGTAB.equals(procKbn)) {

    		//店コードの桁数を５桁にあわせる。
    		campCheckAmountDto.setTaishoMiseCd(formatMiseCd(campCheckAmountDto.getTaishoMiseCd()));

    		//キャンペーンNOが""であるのは対象なしのみ：設定数量情報の取得ロジックを実行
    		checkValidator(
    			campCheckAmountDto.getTabKbn(),
    			campCheckAmountDto.getTaishoCond(),
    			campCheckAmountDto.getCompanyCd(),
    			campCheckAmountDto.getTaishoCmpNo(),
    			campCheckAmountDto.getTaishoMiseCd(),
    			campCheckAmountDto.getTaishoOnerCd(),
    			campCheckAmountDto.getTaishoSibuCd(),
    			campCheckAmountDto.getTaishoBlockCd(),
    			campCheckAmountDto.getSysDate(),
    			campCheckAmountDto.getUserTypeCd(),
    			getMiseLogic,
    			getOnerLogic);
    	}
    	List resultList = new ArrayList();

    	//設定数量タブの場合
		if(CampCheckAmountConstants.TAB_KBN_SET.equals(campCheckAmountDto.getTabKbn())) {
			if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(campCheckAmountDto.getUserTypeCd())) {
				//本部ユーザの場合
				resultList =
					getSetQuantityInfoLogic.execute(campCheckAmountDto.getTaishoCond(),
						campCheckAmountDto.getCompanyCd(),
						campCheckAmountDto.getTaishoMiseCd(),
						campCheckAmountDto.getTaishoSibuCd(),
						campCheckAmountDto.getTaishoBlockCd(),
						campCheckAmountDto.getSysDate(),
    					campCheckAmountDto.getOnerCdList(),
    					false);

			} else if(CampCheckAmountConstants.USER_TYPE_ONER.equals(campCheckAmountDto.getUserTypeCd())) {
				//オーナーユーザの場合
				resultList =
					getSetQuantityInfoLogic.execute(campCheckAmountDto.getCompanyCd(),
						campCheckAmountDto.getSysDate(),
						campCheckAmountDto.getOnerCdList(),
						false);
			}

			if(resultList == null || resultList.size() == 0) {
				throw new NoResultException(); //E0102
			}

			String oldMiseCd = "";
			List nResultList = new ArrayList();

        	for (Iterator ite = resultList.iterator(); ite.hasNext();) {
                //Listからentityへキャストする
        		UISetQuantityInfo entity = (UISetQuantityInfo) ite.next();

        		if(oldMiseCd.equals(entity.getMiseCd())) {
        			entity.setCnt(new BigDecimal(0));
        			entity.setMiseCd("");
        			entity.setMiseNameKj("");
        			nResultList.add(entity);
        		} else if(!oldMiseCd.equals(entity.getMiseCd())) {
        			//最初
        			nResultList.add(entity);
        			oldMiseCd = entity.getMiseCd();
        		}
            }
	    	//結果を設定
			campCheckAmountDto.setResultListTab2(nResultList);
		} else {
			//ディフォルト：スポット数量
			if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(campCheckAmountDto.getUserTypeCd())) {
				//本部ユーザの場合
				resultList =
					getSpotQuantityInfoLogic.execute(campCheckAmountDto.getTaishoCond(),
							campCheckAmountDto.getCompanyCd(),
							campCheckAmountDto.getTaishoCmpNo(),
							campCheckAmountDto.getTaishoMiseCd(),
							campCheckAmountDto.getTaishoSibuCd(),
							campCheckAmountDto.getTaishoBlockCd(),
							campCheckAmountDto.getSysDate(),
	    					campCheckAmountDto.getOnerCdList());
			} else if(CampCheckAmountConstants.USER_TYPE_ONER.equals(campCheckAmountDto.getUserTypeCd())) {
				//オーナーユーザの場合
				resultList =
					getSpotQuantityInfoLogic.execute(campCheckAmountDto.getCompanyCd(),
						campCheckAmountDto.getTaishoCmpNo(),
						campCheckAmountDto.getSysDate(),
						campCheckAmountDto.getOnerCdList());
			}

			if(resultList == null || resultList.size() == 0) {
				throw new NoResultException(); //E0102
			}

	    	//結果を設定
			campCheckAmountDto.setResultListTab1(resultList);
			//有償金額合計を設定
			BigDecimal yuShoAmtSum = new BigDecimal(0);
        	for (Iterator ite = resultList.iterator(); ite.hasNext();) {
                //Listからentityへキャストする
        		UISpotQuantityInfo entity = (UISpotQuantityInfo) ite.next();
        		yuShoAmtSum = yuShoAmtSum.add(entity.getMiseYushoAmt());
            }
        	campCheckAmountDto.setYuShoAmtSum(yuShoAmtSum);
		}
		//結果リストのサイズを設定する
		campCheckAmountDto.setResultListSz(new BigDecimal(resultList.size()));
    	//タブ区分設定
		campCheckAmountDto.setTabKbn(campCheckAmountDto.getTabKbn());
    	//実行後区分設定
		campCheckAmountDto.setExecFlg(CampCheckAmountConstants.EXEC_RESERCH_END_FLG);
		//ボタン名:再検索
		campCheckAmountDto.setButtonNm(CampCheckAmountConstants.BUTTON_NM_REEXEC);

		return campCheckAmountDto;
    }

	/**
	 * 検索条件のチェック
	 * @param tabKbn
	 * @param taishoCond
	 * @param companyCd
	 * @param taishoCmpNo
	 * @param taishoMiseCd
	 * @param taishoSibuCd
	 * @param taishoBlockCd
	 * @param sysDate
	 * @param userTypeCd
	 * @param getMiseLogic
	 */
    private static void checkValidator(String tabKbn,
    		String taishoCond,
    		String companyCd ,
    		String taishoCmpNo,
    		String taishoMiseCd,
    		String taishoOnerCd,
    		String taishoSibuCd,
    		String taishoBlockCd,
    		String sysDate,
    		String userTypeCd,
    		GetMiseLogic getMiseLogic,
    		GetOnerLogic getOnerLogic) {

		NumericVerifier numericVerifier = new NumericVerifier();
		if(CampCheckAmountCommon.isNull(companyCd)){
	        throw new NotNullException("会社コード"); //E0506 会社コード
	    }
		if(CampCheckAmountConstants.TAB_KBN_SPOT.equals(tabKbn)) {
			if(CampCheckAmountCommon.isNull(taishoCmpNo)){
		        throw new NotNullException("キャンペーンNO"); //E0506
		    }
		}
    	if(CampCheckAmountCommon.isNull(sysDate)){
            throw new NotNullException("システム日付"); //E0506 システム日付
        }
    	//本部ユーザのみチェックする
		if(CampCheckAmountConstants.USER_TYPE_HONBU.equals(userTypeCd)) {
			//エラー処理：
			if(CampCheckAmountCommon.isNull(taishoCond)){
		        throw new NotNullException("対象条件"); //E0506 対象条件
		    }
			if(CampCheckAmountConstants.TAISHO_COND_TENPO.equals(taishoCond)) {
		    	if(CampCheckAmountCommon.isNull(taishoMiseCd)){
		            throw new NoInputException("店コード"); //E0506 店コード
		        } else {
			    	if(!numericVerifier.validate(taishoMiseCd)) {
			    		throw new InputConstraintException("店コード","半角数字５桁以内");
			    	}
			    	if(getMiseLogic.execute(companyCd, taishoMiseCd) == null) {
			    		throw new NotExistException("該当店舗"); //E0103　該当店舗が存在しません。
			    	}
		        }
			} else if(CampCheckAmountConstants.TAISHO_COND_OWNER.equals(taishoCond)) {
		    	if(CampCheckAmountCommon.isNull(taishoOnerCd)){
		            throw new NoInputException("オーナーコード"); //E0506 店コード
		        } else {
			    	if(!numericVerifier.validate(taishoOnerCd)) {
			    		throw new InputConstraintException("オーナーコード","半角数字５桁以内");
			    	}
			    	if(getOnerLogic.execute(companyCd, taishoOnerCd) == null) {
			    		throw new NotExistException("該当オーナー"); //E0103　該当店舗が存在しません。
			    	}
		        }
			} else if(CampCheckAmountConstants.TAISHO_COND_SIBU.equals(taishoCond)) {
		    	if(CampCheckAmountCommon.isNull(taishoSibuCd)){
		            throw new NotNullException("支部コード"); //E0506 支部コード
		        }
		    	if(CampCheckAmountCommon.isNull(taishoBlockCd)){
		            throw new NotNullException("ブロックコード"); //E0506 ブロックコード
		        }
			}
		}
    }

    /**
     * 検索条件の保持
     * @param campCheckAmountDto
     */
    public static void setCondSave(
    		CampCheckAmountDto campCheckAmountDto,
    		CampCheckAmountSaveDto campCheckAmountSaveDto) {
    	campCheckAmountSaveDto.setTmpTaishoCmpNo(campCheckAmountDto.getTaishoCmpNo());
    	campCheckAmountSaveDto.setTmpTaishoCond(campCheckAmountDto.getTaishoCond());
    	campCheckAmountSaveDto.setTmpTaishoMiseCd(campCheckAmountDto.getTaishoMiseCd());
    	campCheckAmountSaveDto.setTmpTaishoOnerCd(campCheckAmountDto.getTaishoOnerCd());
    	campCheckAmountSaveDto.setTmpTaishoSibuCd(campCheckAmountDto.getTaishoSibuCd());
    	campCheckAmountSaveDto.setTmpTaishoBlockCd(campCheckAmountDto.getTaishoBlockCd());
    	if(!CampCheckAmountConstants.CALL_EXEC_KBN.equals(campCheckAmountDto.getCallExecKbn())) {
    		campCheckAmountSaveDto.setViewTaishoCmpNo(campCheckAmountDto.getTaishoCmpNo());
	    	campCheckAmountSaveDto.setViewTaishoCond(campCheckAmountDto.getTaishoCond());
	    	campCheckAmountSaveDto.setViewTaishoMiseCd(campCheckAmountDto.getTaishoMiseCd());
	    	campCheckAmountSaveDto.setViewTaishoOnerCd(campCheckAmountDto.getTaishoOnerCd());
	    	campCheckAmountSaveDto.setViewTaishoSibuCd(campCheckAmountDto.getTaishoSibuCd());
	    	campCheckAmountSaveDto.setViewTaishoBlockCd(campCheckAmountDto.getTaishoBlockCd());
    	}
    	campCheckAmountSaveDto.setTmpExecFlg(campCheckAmountDto.getExecFlg());
    	campCheckAmountSaveDto.setTmpTabKbn(campCheckAmountDto.getTabKbn());
    	campCheckAmountSaveDto.setTmpTabViewFlg(campCheckAmountDto.getTabViewFlg());
	}

    /**
     * 検索条件の保持
     * @param campCheckAmountDto
     */
    public static void setViewCondSave(
    		CampCheckAmountDto campCheckAmountDto,
    		CampCheckAmountSaveDto campCheckAmountSaveDto) {
    	campCheckAmountSaveDto.setViewTaishoCmpNo(campCheckAmountDto.getTaishoCmpNo());
    	campCheckAmountSaveDto.setViewTaishoCond(campCheckAmountDto.getTaishoCond());
    	campCheckAmountSaveDto.setViewTaishoMiseCd(campCheckAmountDto.getTaishoMiseCd());
    	campCheckAmountSaveDto.setViewTaishoOnerCd(campCheckAmountDto.getTaishoOnerCd());
    	campCheckAmountSaveDto.setViewTaishoSibuCd(campCheckAmountDto.getTaishoSibuCd());
    	campCheckAmountSaveDto.setViewTaishoBlockCd(campCheckAmountDto.getTaishoBlockCd());
    	campCheckAmountSaveDto.setTmpExecFlg(campCheckAmountDto.getExecFlg());
    	campCheckAmountSaveDto.setTmpTabKbn(campCheckAmountDto.getTabKbn());
    	campCheckAmountSaveDto.setTmpTabViewFlg(campCheckAmountDto.getTabViewFlg());
	}

    /**
     * 検索条件の取得
     * @param campCheckAmountDto
     */
    public static CampCheckAmountDto getCondSave(
    		CampCheckAmountDto campCheckAmountDto,
    		CampCheckAmountSaveDto campCheckAmountSaveDto) {
    	campCheckAmountDto.setTaishoCmpNo(campCheckAmountSaveDto.getTmpTaishoCmpNo());
    	campCheckAmountDto.setTaishoCond(campCheckAmountSaveDto.getTmpTaishoCond());
    	campCheckAmountDto.setTaishoMiseCd(campCheckAmountSaveDto.getTmpTaishoMiseCd());
    	campCheckAmountDto.setTaishoOnerCd(campCheckAmountSaveDto.getTmpTaishoOnerCd());
    	campCheckAmountDto.setTaishoSibuCd(campCheckAmountSaveDto.getTmpTaishoSibuCd());
    	campCheckAmountDto.setTaishoBlockCd(campCheckAmountSaveDto.getTmpTaishoBlockCd());
    	campCheckAmountDto.setExecFlg(campCheckAmountSaveDto.getTmpExecFlg());
    	campCheckAmountDto.setTabKbn(campCheckAmountSaveDto.getTmpTabKbn());
    	campCheckAmountDto.setTabViewFlg(campCheckAmountSaveDto.getTmpTabViewFlg());

    	return campCheckAmountDto;
	}

    /**
     * 検索条件の取得
     * @param campCheckAmountDto
     */
    public static CampCheckAmountDto getViewCondSave(
    		CampCheckAmountDto campCheckAmountDto,
    		CampCheckAmountSaveDto campCheckAmountSaveDto) {
    	campCheckAmountDto.setTaishoCmpNo(campCheckAmountSaveDto.getViewTaishoCmpNo());
    	campCheckAmountDto.setTaishoCond(campCheckAmountSaveDto.getViewTaishoCond());
    	campCheckAmountDto.setTaishoMiseCd(campCheckAmountSaveDto.getViewTaishoMiseCd());
    	campCheckAmountDto.setTaishoOnerCd(campCheckAmountSaveDto.getViewTaishoOnerCd());
    	campCheckAmountDto.setTaishoSibuCd(campCheckAmountSaveDto.getViewTaishoSibuCd());
    	campCheckAmountDto.setTaishoBlockCd(campCheckAmountSaveDto.getViewTaishoBlockCd());

    	return campCheckAmountDto;
	}

    /**
     * オーナーコードを取得する
     * @param birdUserInfo
     * @param companyCd
     * @return オーナーコードリスト
     */
    public static List getOnerList(BirdUserInfo birdUserInfo, String companyCd) {
		List onerCdList = new ArrayList();
	    for (Iterator it = birdUserInfo.getUserOner().iterator(); it.hasNext();) {
	        UIUserOner uIUserOner = (UIUserOner) it.next();
	        if (uIUserOner.getCompanyCd().equals(companyCd)) {
	            onerCdList.add(uIUserOner.getOnerCd());
	        }
	    }
	    return onerCdList;
    }

    private static String formatMiseCd(String miseCd) {
    	String tmpMiseCd = "";
	    CodeFormatter cdf = new CodeFormatter(5, "00000");
	    cdf.setFormatPattern("00000");
	    if(miseCd != null && miseCd.length() > 0){
	    	tmpMiseCd = cdf.format(miseCd, true);
	    }
	    return tmpMiseCd;
    }
}