package jp.co.isid.mos.bird.entry.nationalentry.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.entry.nationalentry.dto.NationalEntryDto;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryDutyInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryJoinInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntrySelectInfo;
import jp.co.isid.mos.bird.entry.nationalentry.entity.UINatiEntryState;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 全国大会　申込
 * static 処理Utilクラス
 *
 * @author xlee
 *
 */
public class NationalEntryUtil {
	/** オプショナル（ツアー関連）先頭インデックス */
    public static final int OPT_TOUR_FIRST_INDEX = 4;
	/**
	 *  取得した情報をDTOに設定
	 *  @param dto
	 *  @param prmMap
	 *  @param searchKbn
	 *  @return dto
	 */
    public static NationalEntryDto setNatiEntryInfo (
    		NationalEntryDto nationalEntryDto, Map prmMap, String searchKbn) {

		Map selectionKbnMap = null;
		UINatiEntryState uiNatiEntryState = null;
		UINatiEntryDutyInfo uiNatiEntryDutyInfo = null;
        List natiEntryJoinInfoList = null;

        if(prmMap.containsKey(NationalEntryDto.MAPKEY_STATE)) {
        	uiNatiEntryState = (UINatiEntryState)prmMap.get(NationalEntryDto.MAPKEY_STATE);
        }
        if(prmMap.containsKey(NationalEntryDto.MAPKEY_SELECTION)) {
        	selectionKbnMap = (Map)prmMap.get(NationalEntryDto.MAPKEY_SELECTION);
        }
        if(prmMap.containsKey(NationalEntryDto.MAPKEY_DUTYINFO)) {
        	uiNatiEntryDutyInfo = (UINatiEntryDutyInfo)prmMap.get(NationalEntryDto.MAPKEY_DUTYINFO);
        }
        if(prmMap.containsKey(NationalEntryDto.MAPKEY_JOININFO)) {
        	natiEntryJoinInfoList = (List)prmMap.get(NationalEntryDto.MAPKEY_JOININFO);
        }

    	String entryFlg = "";
    	Timestamp lastTime = null;

    	if(uiNatiEntryState == null) {
    		if(nationalEntryDto.getViewKbn().equals(NationalEntryDto.VIEW_KBN_E)) {
                entryFlg = "";
    		} else if(nationalEntryDto.getViewKbn().equals(NationalEntryDto.VIEW_KBN_V)) {
    			entryFlg = "";
    		}
    		lastTime = DateManager.getCurrentTimestamp();
    	} else {
    		//2007.01.26　追加 【力欄追加ボタン】を押下した場合、参加者情報がすべて空白であれば、
    		//ＤＢには不参加で登録、参加者情報は削除される。但し、画面には参加に表示する
    		if(NationalEntryDto.KBN_RESEARCH_EXEC.equals(searchKbn)) {
    			if(NationalEntryDto.ENTRY_STATE_NG.equals(uiNatiEntryState.getEntryFlg())) {
    				entryFlg = NationalEntryDto.ENTRY_STATE_OK;
    			} else {
    				entryFlg = uiNatiEntryState.getEntryFlg();
    			}
    		} else {
    			entryFlg = uiNatiEntryState.getEntryFlg();
    		}
    		lastTime = uiNatiEntryState.getLastTmsp();
    	}

        if(uiNatiEntryDutyInfo != null) {
            //1.申込責任者
        	nationalEntryDto.setPrmDutyOnerName(uiNatiEntryDutyInfo.getOnerNameKj());
        	nationalEntryDto.setPrmDutyEntryState(entryFlg);
        	nationalEntryDto.setPrmDutyLastTmsp(lastTime);
        	nationalEntryDto.setPrmDutySoufuName(uiNatiEntryDutyInfo.getSoufuName());
        	nationalEntryDto.setPrmDutyTel(uiNatiEntryDutyInfo.getTel());
        	nationalEntryDto.setPrmDutyName(uiNatiEntryDutyInfo.getName());
        	nationalEntryDto.setPrmDutyJobType(uiNatiEntryDutyInfo.getJobType());
      	}
        //申込責任者が不参加の場合-入力欄追加ボタン使用不可、フォーム非表示
        if(entryFlg.equals(NationalEntryDto.ENTRY_STATE_NG) || entryFlg.equals("")) {
        	nationalEntryDto.setPrmInputAppendFlg(NationalEntryDto.INPUT_APPEND_NG);
        	nationalEntryDto.setPrmFormNonViewFlg(NationalEntryDto.VIEW_FORM_NG);
        } else if(entryFlg.equals(NationalEntryDto.ENTRY_STATE_OK)) {
        	if(natiEntryJoinInfoList.size() < 30) {
        		nationalEntryDto.setPrmInputAppendFlg(NationalEntryDto.INPUT_APPEND_OK);
        	} else {
        		nationalEntryDto.setPrmInputAppendFlg(NationalEntryDto.INPUT_APPEND_NG);
        	}
        	nationalEntryDto.setPrmFormNonViewFlg(NationalEntryDto.VIEW_FORM_OK);
        }
        //２．申込参加者
        nationalEntryDto.setPrmJoinList(natiEntryJoinInfoList);
        //３．最後の入力するフォームのシーケンスコード
        nationalEntryDto.setPrmSeqNo(String.valueOf(natiEntryJoinInfoList.size()));
        //４．セレクション情報
        if(selectionKbnMap != null) {
        	nationalEntryDto.setSelectionEntryKbn((List) selectionKbnMap.get(NationalEntryDto.SELECTION1));
        	nationalEntryDto.setSelectionOptionInfo((List) selectionKbnMap.get(NationalEntryDto.SELECTION2));
        }
		return nationalEntryDto;
    }

	/**
	 *  取得した情報をDTOに設定
	 *  @param userId
	 *  @param seqno
	 *  @return
	 */
    public static UINatiEntryJoinInfo setDefaultJoinInfo (String userId, String seqNo) {

    	UINatiEntryJoinInfo uiNatiEntryJoinInfo = new UINatiEntryJoinInfo();
		uiNatiEntryJoinInfo.setSeqNo(seqNo);
		uiNatiEntryJoinInfo.setMiseCd("");
		uiNatiEntryJoinInfo.setLNameKj("");
		uiNatiEntryJoinInfo.setFNameKj("");
		uiNatiEntryJoinInfo.setLNameKna("");
		uiNatiEntryJoinInfo.setFNameKna("");
		uiNatiEntryJoinInfo.setProposeKbn("");
		uiNatiEntryJoinInfo.setOptional1("");
		uiNatiEntryJoinInfo.setOptional2("");
		uiNatiEntryJoinInfo.setOptional3("");
		uiNatiEntryJoinInfo.setOptional4("");
		uiNatiEntryJoinInfo.setOptional5("");
		uiNatiEntryJoinInfo.setOptional6("");
		uiNatiEntryJoinInfo.setOptional7("");
		uiNatiEntryJoinInfo.setOptional8("");
		uiNatiEntryJoinInfo.setOptional9("");
		uiNatiEntryJoinInfo.setOptional10("");
		uiNatiEntryJoinInfo.setOptional11("");
		uiNatiEntryJoinInfo.setOptional12("");
		uiNatiEntryJoinInfo.setOptional13("");
		uiNatiEntryJoinInfo.setOptional14("");
		uiNatiEntryJoinInfo.setOptional15("");
		uiNatiEntryJoinInfo.setOptional16("");
		uiNatiEntryJoinInfo.setOptional21("");
		uiNatiEntryJoinInfo.setOptional22("");
		uiNatiEntryJoinInfo.setOptional23("");
		uiNatiEntryJoinInfo.setOptional24("");
		uiNatiEntryJoinInfo.setOptional25("");
		uiNatiEntryJoinInfo.setOptional26("");
		uiNatiEntryJoinInfo.setOptional27("");
		uiNatiEntryJoinInfo.setOptional28("");
		uiNatiEntryJoinInfo.setOptional29("");
		uiNatiEntryJoinInfo.setOptional30("");
		uiNatiEntryJoinInfo.setJobType("");
		uiNatiEntryJoinInfo.setNote1("");
		uiNatiEntryJoinInfo.setNote("");
		uiNatiEntryJoinInfo.setSex("");
		uiNatiEntryJoinInfo.setBirthday_Year("");
		uiNatiEntryJoinInfo.setBirthday_Month("");
		uiNatiEntryJoinInfo.setBirthday_Day("");
		uiNatiEntryJoinInfo.setAge("");
		uiNatiEntryJoinInfo.setFirstUser(userId);
		uiNatiEntryJoinInfo.setLastUser(userId);

		return uiNatiEntryJoinInfo;
    }

	/**
	 *  入力欄追加処理の場合、
	 *  ＤＢに登録されてないフォームも再設定する
	 * @param tmpJoinInfo
	 * @param newJoinInfo
	 * @param checkNatiEntryInputParamLogic
	 * @return List
	 */
    public static List setReJoinInfo(List tmpJoinInfo,
    		List newJoinInfo) {

    	List resetJoinInfo = new ArrayList();

    	int newCnt = 0;
		for(int i = 0; i < tmpJoinInfo.size(); i++) {
			UINatiEntryJoinInfo oldNatiEntryJoinInfo =
				(UINatiEntryJoinInfo)tmpJoinInfo.get(i);

			if(!CheckInputParam.chkAllJoinInfo(oldNatiEntryJoinInfo)) {
				UINatiEntryJoinInfo newNatiEntryJoinInfo =
					(UINatiEntryJoinInfo) newJoinInfo.get(newCnt);
				newNatiEntryJoinInfo.setSeqNo(String.valueOf(i+1));
				resetJoinInfo.add(newNatiEntryJoinInfo);
				newCnt++;
			} else {
				resetJoinInfo.add(oldNatiEntryJoinInfo);
			}
		}
		return resetJoinInfo;
    }

	/**
	 *  登録（更新）する情報を設定する
	 *  @param dto
	 *  @return map　登録する情報が設定されているマップ
	 */
    public static Map setRegistInfo (NationalEntryDto nationalEntryDto) {
		Map regInfoMap = new HashMap();
		if(nationalEntryDto.getEditKbn().equals(NationalEntryDto.EDIT_KBN_INSERT)) {
			regInfoMap.put(NationalEntryDto.MAPKEY_PROC, NationalEntryDto.PROC_KBN_INSERT);
		} else {
			regInfoMap.put(NationalEntryDto.MAPKEY_PROC, NationalEntryDto.PROC_KBN_UPDATE);
		}
		//処理区分
		if(nationalEntryDto.getEditKbn().equals(NationalEntryDto.EDIT_KBN_INSERT)) {
			regInfoMap.put(NationalEntryDto.MAPKEY_PROC, NationalEntryDto.PROC_KBN_INSERT);
		} else {
			regInfoMap.put(NationalEntryDto.MAPKEY_PROC, NationalEntryDto.PROC_KBN_UPDATE);
		}

		//オーナー別エントリ状況
		UINatiEntryState uiNatiEntrySatate = new UINatiEntryState();
		uiNatiEntrySatate.setEntryCd(nationalEntryDto.getCondEntryCd());
		uiNatiEntrySatate.setEntryYear(nationalEntryDto.getCondEntryYear());
		uiNatiEntrySatate.setEntryKai(nationalEntryDto.getCondEntryKai());
		uiNatiEntrySatate.setCompanyCd(nationalEntryDto.getCondCompanyCd());
		uiNatiEntrySatate.setOnerCd(nationalEntryDto.getCondOnerCd());
		uiNatiEntrySatate.setEntryFlg(nationalEntryDto.getPrmDutyEntryState());
		uiNatiEntrySatate.setLastTmsp(nationalEntryDto.getPrmDutyLastTmsp());
		uiNatiEntrySatate.setFirstUser(nationalEntryDto.getUserId());
		uiNatiEntrySatate.setLastUser(nationalEntryDto.getUserId());
		regInfoMap.put(NationalEntryDto.MAPKEY_STATE, uiNatiEntrySatate);

		//責任者
		UINatiEntryDutyInfo uiNatiEntryDutyInfo = new UINatiEntryDutyInfo();
		uiNatiEntryDutyInfo.setEntryCd(nationalEntryDto.getCondEntryCd());
		uiNatiEntryDutyInfo.setEntryYear(nationalEntryDto.getCondEntryYear());
		uiNatiEntryDutyInfo.setEntryKai(nationalEntryDto.getCondEntryKai());
		uiNatiEntryDutyInfo.setCompanyCd(nationalEntryDto.getCondCompanyCd());
		uiNatiEntryDutyInfo.setOnerCd(nationalEntryDto.getCondOnerCd());
		uiNatiEntryDutyInfo.setSoufuName(nationalEntryDto.getPrmDutySoufuName());
		uiNatiEntryDutyInfo.setAtesakiKbn("00");
		uiNatiEntryDutyInfo.setTel(nationalEntryDto.getPrmDutyTel());
		uiNatiEntryDutyInfo.setName(nationalEntryDto.getPrmDutyName());
		uiNatiEntryDutyInfo.setJobType(
				CommonUtil.isNull(nationalEntryDto.getPrmDutyJobType()) ? "" : nationalEntryDto.getPrmDutyJobType());
		uiNatiEntryDutyInfo.setFirstUser(nationalEntryDto.getUserId());
		uiNatiEntryDutyInfo.setLastUser(nationalEntryDto.getUserId());
		regInfoMap.put(NationalEntryDto.MAPKEY_DUTYINFO, uiNatiEntryDutyInfo);

		//参加者情報の設定
		List newJoinInfo = new ArrayList();
		for(int i = 0 ; i < nationalEntryDto.getPrmJoinList().size(); i++) {
			UINatiEntryJoinInfo uiNatiEntryJoinInfo = (UINatiEntryJoinInfo) nationalEntryDto.getPrmJoinList().get(i);
			uiNatiEntryJoinInfo.setEntryCd(nationalEntryDto.getCondEntryCd());
			uiNatiEntryJoinInfo.setEntryYear(nationalEntryDto.getCondEntryYear());
			uiNatiEntryJoinInfo.setEntryKai(nationalEntryDto.getCondEntryKai());
			uiNatiEntryJoinInfo.setCompanyCd(nationalEntryDto.getCondCompanyCd());
			uiNatiEntryJoinInfo.setOnerCd(nationalEntryDto.getCondOnerCd());
			uiNatiEntryJoinInfo.setSeqNo(String.valueOf(i+1));
			uiNatiEntryJoinInfo.setJobType(CommonUtil.isNull(
					uiNatiEntryJoinInfo.getJobType()) ? "" : uiNatiEntryJoinInfo.getJobType());
			uiNatiEntryJoinInfo.setNote(CommonUtil.isNull(
					uiNatiEntryJoinInfo.getNote()) ? "" : uiNatiEntryJoinInfo.getNote());
            uiNatiEntryJoinInfo.setNote1(CommonUtil.isNull(
                    uiNatiEntryJoinInfo.getNote1()) ? "" : uiNatiEntryJoinInfo.getNote1());
			newJoinInfo.add(uiNatiEntryJoinInfo);
		}
		//参加者
		regInfoMap.put(NationalEntryDto.MAPKEY_JOININFO, newJoinInfo);

		return regInfoMap;
    }

    /**
     * 年齢を計算する
     *
     * @return 年齢
     */
    public static int chgToAgeFrDate (String birthDay , String sysDt) {
    	int age = 0;
	    if(!CommonUtil.isNull(birthDay)) {

	    	int sysYY = Integer.parseInt(sysDt.substring(0,4));
	    	int sysMM = Integer.parseInt(sysDt.substring(4,6));
	    	int sysDD = Integer.parseInt(sysDt.substring(6,8));

	    	int birthYY = Integer.parseInt(birthDay.substring(0,4));
	    	int birthMM = Integer.parseInt(birthDay.substring(4,6));
	    	int birthDD = Integer.parseInt(birthDay.substring(6,8));

	    	age = sysYY - birthYY;
	    	if(sysMM < birthMM) {
	    		age -= 1;
	    	} else if(sysMM == birthMM) {
	    		if(sysDD < birthDD) {
	    			age -= 1;
	    		}
	    	}
	    }
	    return age;
 	}

	/**
	 * 必須項目がすべて未入力かどうか / すべての項目が未入力かどうかの処理
	 * @param nationalEntryDto
	 * @param tmpJoinInfo
	 * @param checkNatiEntryInputParamLogic
	 * @return 未入力フォーム個数
	 */
    public static int [] cntReqItem (
    		NationalEntryDto nationalEntryDto,
    		List tmpJoinInfo) {

    	int[] reqItemCnt = new int[2];
    	int tmpNotNullCnt = 0;
    	int tmpAllNullCnt = 0;

		for(int i = 0 ; i < tmpJoinInfo.size(); i++) {
			UINatiEntryJoinInfo uiNatiEntryJoinInfo =
				(UINatiEntryJoinInfo)nationalEntryDto.getPrmJoinList().get(i);
			if(CheckInputParam.checkRegistValidtity(uiNatiEntryJoinInfo)) {
				tmpNotNullCnt++;
			}
			if(CheckInputParam.chkAllJoinInfo(uiNatiEntryJoinInfo)) {
				tmpAllNullCnt++;
			}
		}
		reqItemCnt[0] = tmpNotNullCnt;
		reqItemCnt[1] = tmpAllNullCnt;

		return reqItemCnt;
 	}

    /**
     * 確認画面の各項目に対する名称を取得する処理
     * @param nationalEntryDto
     * @return　dto
     */
    public static NationalEntryDto selectionNm (NationalEntryDto nationalEntryDto) {

    	List proposeKbnInfo = nationalEntryDto.getSelectionEntryKbn();
    	List optionInfo = nationalEntryDto.getSelectionOptionInfo();
    	List tenpoInfo = nationalEntryDto.getCondListMise();
    	List newJoinInfo = new ArrayList();

    	for(int i = 0 ; i < nationalEntryDto.getPrmJoinList().size(); i++) {
			UINatiEntryJoinInfo uiNatiEntryJoinInfo =
				(UINatiEntryJoinInfo) nationalEntryDto.getPrmJoinList().get(i);

			//１．店舗名称を取得
			for(int j = 0 ; j < tenpoInfo.size(); j++) {
				SelectItem tenpoItem = (SelectItem)tenpoInfo.get(j);
				if(uiNatiEntryJoinInfo.getMiseCd().equals(tenpoItem.getValue())) {
					uiNatiEntryJoinInfo.setMiseNm(tenpoItem.getLabel());
					break;
				}
			}
			//職務名を取得
			uiNatiEntryJoinInfo.setJobType(nationalEntryDto.getSyokuiName(uiNatiEntryJoinInfo.getJobTypeCd()));
			//２．申込区分を取得
			for(int j = 0 ; j < proposeKbnInfo.size(); j++) {

				UINatiEntrySelectInfo uiNatiEntrySelectInfo = (UINatiEntrySelectInfo)proposeKbnInfo.get(j);
				if(uiNatiEntryJoinInfo.getProposeKbn().equals(uiNatiEntrySelectInfo.getSelectionIndex())) {
					uiNatiEntryJoinInfo.setProposeKbnNm(uiNatiEntrySelectInfo.getSelectionName());
					break;
				}
			}

			//３．オプショナルを取得
			for(int j = 0 ; j < optionInfo.size(); j++) {

				String optional = "";
				UINatiEntrySelectInfo uiNatiEntrySelectInfo = (UINatiEntrySelectInfo)optionInfo.get(j);

				if(j == 0) {
					optional = uiNatiEntryJoinInfo.getOptional1();
				} else if(j == 1) {
					optional = uiNatiEntryJoinInfo.getOptional2();
				} else if(j == 2) {
					optional = uiNatiEntryJoinInfo.getOptional3();
				} else if(j == 3) {
					optional = uiNatiEntryJoinInfo.getOptional4();
				} else if(j == 4) {
					optional = uiNatiEntryJoinInfo.getOptional5();
				} else if(j == 5) {
					optional = uiNatiEntryJoinInfo.getOptional6();
				} else if(j == 6) {
					optional = uiNatiEntryJoinInfo.getOptional7();
				} else if(j == 7) {
					optional = uiNatiEntryJoinInfo.getOptional8();
				} else if(j == 8) {
					optional = uiNatiEntryJoinInfo.getOptional9();
				} else if(j == 9) {
					optional = uiNatiEntryJoinInfo.getOptional10();
				}else if(j==10){
					optional = uiNatiEntryJoinInfo.getOptional11();
				}else if(j==11){
					optional = uiNatiEntryJoinInfo.getOptional12();
				}else if(j==12){
					optional = uiNatiEntryJoinInfo.getOptional13();
				}else if(j==13){
					optional = uiNatiEntryJoinInfo.getOptional14();
				}else if(j==14){
					optional = uiNatiEntryJoinInfo.getOptional15();
				}else if(j==15){
					optional = uiNatiEntryJoinInfo.getOptional16();
				}else if(j==20){
					optional = uiNatiEntryJoinInfo.getOptional21();
				}else if(j==21){
					optional = uiNatiEntryJoinInfo.getOptional22();
				}else if(j==22){
					optional = uiNatiEntryJoinInfo.getOptional23();
				}else if(j==23){
					optional = uiNatiEntryJoinInfo.getOptional24();
				}else if(j==24){
					optional = uiNatiEntryJoinInfo.getOptional25();
				}else if(j==25){
					optional = uiNatiEntryJoinInfo.getOptional26();
				}else if(j==26){
					optional = uiNatiEntryJoinInfo.getOptional27();
				}else if(j==27){
					optional = uiNatiEntryJoinInfo.getOptional28();
				}else if(j==28){
					optional = uiNatiEntryJoinInfo.getOptional29();
				}else if(j==29){
					optional = uiNatiEntryJoinInfo.getOptional30();
				}

				if(optional.equals("1")) {
					if(j == 0) {
						uiNatiEntryJoinInfo.setOptional1Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 1) {
						uiNatiEntryJoinInfo.setOptional2Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 2) {
						uiNatiEntryJoinInfo.setOptional3Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 3) {
						uiNatiEntryJoinInfo.setOptional4Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 4) {
						uiNatiEntryJoinInfo.setOptional5Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 5) {
						uiNatiEntryJoinInfo.setOptional6Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 6) {
						uiNatiEntryJoinInfo.setOptional7Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 7) {
						uiNatiEntryJoinInfo.setOptional8Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 8) {
						uiNatiEntryJoinInfo.setOptional9Nm(uiNatiEntrySelectInfo.getSelectionName());
					} else if(j == 9) {
						uiNatiEntryJoinInfo.setOptional10Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 10) {
						uiNatiEntryJoinInfo.setOptional11Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 11) {
						uiNatiEntryJoinInfo.setOptional12Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 12) {
						uiNatiEntryJoinInfo.setOptional13Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 13) {
						uiNatiEntryJoinInfo.setOptional14Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 14) {
						uiNatiEntryJoinInfo.setOptional15Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 15) {
						uiNatiEntryJoinInfo.setOptional16Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 20) {
						uiNatiEntryJoinInfo.setOptional21Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 21) {
						uiNatiEntryJoinInfo.setOptional22Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 22) {
						uiNatiEntryJoinInfo.setOptional23Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 23) {
						uiNatiEntryJoinInfo.setOptional24Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 24) {
						uiNatiEntryJoinInfo.setOptional25Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 25) {
						uiNatiEntryJoinInfo.setOptional26Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 26) {
						uiNatiEntryJoinInfo.setOptional27Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 27) {
						uiNatiEntryJoinInfo.setOptional28Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 28) {
						uiNatiEntryJoinInfo.setOptional29Nm(uiNatiEntrySelectInfo.getSelectionName());
					}else if(j == 29) {
						uiNatiEntryJoinInfo.setOptional30Nm(uiNatiEntrySelectInfo.getSelectionName());
					}
				} else {
					if(j == 0) {
						uiNatiEntryJoinInfo.setOptional1Nm(null);
					} else if(j == 1) {
						uiNatiEntryJoinInfo.setOptional2Nm(null);
					} else if(j == 2) {
						uiNatiEntryJoinInfo.setOptional3Nm(null);
					} else if(j == 3) {
						uiNatiEntryJoinInfo.setOptional4Nm(null);
					} else if(j == 4) {
						uiNatiEntryJoinInfo.setOptional5Nm(null);
					} else if(j == 5) {
						uiNatiEntryJoinInfo.setOptional6Nm(null);
					} else if(j == 6) {
						uiNatiEntryJoinInfo.setOptional7Nm(null);
					} else if(j == 7) {
						uiNatiEntryJoinInfo.setOptional8Nm(null);
					} else if(j == 8) {
						uiNatiEntryJoinInfo.setOptional9Nm(null);
					} else if(j == 9) {
						uiNatiEntryJoinInfo.setOptional10Nm(null);
					}else if(j == 10) {
						uiNatiEntryJoinInfo.setOptional11Nm(null);
					}else if(j == 11) {
						uiNatiEntryJoinInfo.setOptional12Nm(null);
					}else if(j == 12) {
						uiNatiEntryJoinInfo.setOptional13Nm(null);
					}else if(j == 13) {
						uiNatiEntryJoinInfo.setOptional14Nm(null);
					}else if(j == 14) {
						uiNatiEntryJoinInfo.setOptional15Nm(null);
					}else if(j == 15) {
						uiNatiEntryJoinInfo.setOptional16Nm(null);
					}else if(j == 20) {
						uiNatiEntryJoinInfo.setOptional21Nm(null);
					}else if(j == 21) {
						uiNatiEntryJoinInfo.setOptional22Nm(null);
					}else if(j == 22) {
						uiNatiEntryJoinInfo.setOptional23Nm(null);
					}else if(j == 23) {
						uiNatiEntryJoinInfo.setOptional24Nm(null);
					}else if(j == 24) {
						uiNatiEntryJoinInfo.setOptional25Nm(null);
					}else if(j == 25) {
						uiNatiEntryJoinInfo.setOptional26Nm(null);
					}else if(j == 26) {
						uiNatiEntryJoinInfo.setOptional27Nm(null);
					}else if(j == 27) {
						uiNatiEntryJoinInfo.setOptional28Nm(null);
					}else if(j == 28) {
						uiNatiEntryJoinInfo.setOptional29Nm(null);
					}else if(j == 29) {
						uiNatiEntryJoinInfo.setOptional30Nm(null);
					}
				}
			}
			newJoinInfo.add(uiNatiEntryJoinInfo);
    	}
		return nationalEntryDto;
 	}

    /**
     * 登録された参加者情報の中、未入力された参加者はリストから削除する処理
     * @param joinInfo
     * @return
     */
    public static List removeJoinInfo (String state, List joinInfo) {
    	List newJoinInfo = new ArrayList();

    	if(state.equals("0")) {
    		//不参加の場合、すべてクリア
    	} else {
    		//未入力だけをクリア
    		int tmpSeqNo = 1;
        	for(int i = 0 ; i < joinInfo.size(); i++) {
    			UINatiEntryJoinInfo uiNatiEntryJoinInfo = (UINatiEntryJoinInfo) joinInfo.get(i);

    			if(!CheckInputParam.checkRegistValidtity(uiNatiEntryJoinInfo)) {
    				uiNatiEntryJoinInfo.setSeqNo(String.valueOf(tmpSeqNo));
    				newJoinInfo.add(uiNatiEntryJoinInfo);
    				tmpSeqNo++;
    			}
        	}
    	}
		return newJoinInfo;
 	}
    /**
     * 空セレクション削除処理
     *
     * ただし、セレクション値が存在するデータに挟まれた空セレクションは残す。(歯抜け対応)
     *
     * @param selectionList
     * @return とりむ済み
     */
    public static void setDisabledFlg(List selectionList, int cols) {
 	   int firstIndex = -1;
 	   int lastIndex = -1;
       for (int i = 0; i < selectionList.size(); i++) {
       	UINatiEntrySelectInfo info = (UINatiEntrySelectInfo) selectionList.get(i);
           if(!info.isEmptySelection()) {
       		   firstIndex = (i/cols)*cols;
	           break;
           }
       }
       for (int i = selectionList.size()-1; 0 <= i ; i--) {
    	   UINatiEntrySelectInfo info = (UINatiEntrySelectInfo) selectionList.get(i);
    	   if(!info.isEmptySelection()) {
    		   // セレクション名称設定
    		   lastIndex = (i/cols)*cols+cols-1;
    		   break;
    	   }
       }
       if(firstIndex==-1) {
    	   //処理終了
    	   return;
       }
       for (int i = 0; i < selectionList.size(); i++) {
          	UINatiEntrySelectInfo info = (UINatiEntrySelectInfo) selectionList.get(i);
          	if(info.isEmptySelection()) {
          		if(i<firstIndex || i>lastIndex) {
          			info.setDisabled(true);
          		}
          	}
       }
    }
}