package jp.co.isid.mos.bird.entry.projectplanoffer.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanoffer.dto.ProjectPlanOfferDto;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferDutyPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferEntrustInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferJoinPersonInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferMiseInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.entity.UIOfferNoticeInfo;
import jp.co.isid.mos.bird.entry.projectplanoffer.logic.CheckOfferInputParamLogic;

/**
 * 事業方針説明会　申込
 * static 処理Utilクラス
 * 
 * @author xlee
 *
 */
public class ProjectPlanOfferUtil {
	
    /** 指定改行文字 */
    private static final String _ENTER_WORD = "`";
    
    /** 指定改行文字 */
    private static final String _ENTER = "\n";
    
    /** 一行の最大文字数 */
    private static final int CHAR_CNT = 54;
    
	
    /**
     * 取得した情報をDTOに設定
     * 
     * @param projectPlanOfferDto DTO
     * @param offerInfoList　DBから取得した情報
     * @param prmMap　パラメータから取得した情報
     */
    public static ProjectPlanOfferDto setOfferInfo (
    		ProjectPlanOfferDto projectPlanOfferDto, List offerInfoList, Map prmMap) {
    	if(offerInfoList != null && offerInfoList.size() > 0) {
        	//1-1.注意事項
        	UIOfferNoticeInfo uiOfferNoticeInfo = (UIOfferNoticeInfo)offerInfoList.get(0);
        	projectPlanOfferDto.setNoticeTxt(changeNotice(uiOfferNoticeInfo.getNotice1()));
        	//１-2．委任状文言
        	projectPlanOfferDto.setIninNoteTxt(uiOfferNoticeInfo.getNote());
        	//2.申込責任者
        	UIOfferDutyPersonInfo uiOfferDutyPersonInfo = (UIOfferDutyPersonInfo)offerInfoList.get(1);
        	projectPlanOfferDto.setPrmDutyOnerName(uiOfferDutyPersonInfo.getOnerNameKj());
        	projectPlanOfferDto.setPrmDutySoufuName(uiOfferDutyPersonInfo.getSoufuName());
        	projectPlanOfferDto.setPrmDutyName(uiOfferDutyPersonInfo.getName());
        	projectPlanOfferDto.setPrmDutyJobType(uiOfferDutyPersonInfo.getJobType());
        	projectPlanOfferDto.setPrmDutyAddress1(uiOfferDutyPersonInfo.getAddress1());
        	projectPlanOfferDto.setPrmDutyAddress2(uiOfferDutyPersonInfo.getAddress2());
        	projectPlanOfferDto.setPrmDutyAddress3(uiOfferDutyPersonInfo.getAddress3());
        	projectPlanOfferDto.setPrmDutyTel(uiOfferDutyPersonInfo.getTel());
        	if(projectPlanOfferDto.getPrmEditKbn().equals(ProjectPlanOfferDto.EDIT_KBN_INSERT)) {
        		projectPlanOfferDto.setPrmDutyZip(uiOfferDutyPersonInfo.getZip().substring(0,3) + "-" + uiOfferDutyPersonInfo.getZip().substring(3,7));
        	} else {
        		projectPlanOfferDto.setPrmDutyZip(uiOfferDutyPersonInfo.getZip());
        	}
        	//3.申込参加者
            projectPlanOfferDto = settJoinInfoList(projectPlanOfferDto, (List) offerInfoList.get(2));
        	
        	//4.委任状
        	UIOfferEntrustInfo uiOfferEntrustInfo = (UIOfferEntrustInfo)offerInfoList.get(3);
        	projectPlanOfferDto.setPrmIninFName(uiOfferEntrustInfo.getIninFName());
        	projectPlanOfferDto.setPrmIninLName(uiOfferEntrustInfo.getIninLName());
        	projectPlanOfferDto.setPrmIninMiseCd(uiOfferEntrustInfo.getIninMiseCd());
        	projectPlanOfferDto.setPrmIninMiseNm(getMiseNm(projectPlanOfferDto.getCondListMise(), uiOfferEntrustInfo.getIninMiseCd()));
        	projectPlanOfferDto.setPrmIninMiseTotal(uiOfferEntrustInfo.getIninMiseTotal());
        	projectPlanOfferDto.setPrmIninFirstUser(uiOfferEntrustInfo.getFirstUser());
        	projectPlanOfferDto.setPrmIninFirstPgm(uiOfferEntrustInfo.getFirstPgm());
        	projectPlanOfferDto.setPrmIninFirstTmsp(uiOfferEntrustInfo.getFirstTmsp());
        	projectPlanOfferDto.setPrmIninLastUser(uiOfferEntrustInfo.getLastUser());
        	projectPlanOfferDto.setPrmIninLastPgm(uiOfferEntrustInfo.getLastPgm());
        	projectPlanOfferDto.setPrmIninLastTmsp(uiOfferEntrustInfo.getLastTmsp());
    	} else {
            UIOfferDutyPersonInfo uiOfferDutyPersonInfo = null;
            List joinInfo = null;
            UIOfferEntrustInfo uiOfferEntrustInfo = null;
            
            if(prmMap.containsKey(ProjectPlanOfferDto.MAP_KEY_DUTY)) {
            	uiOfferDutyPersonInfo = (UIOfferDutyPersonInfo)prmMap.get(ProjectPlanOfferDto.MAP_KEY_DUTY);
            }
            if(prmMap.containsKey(ProjectPlanOfferDto.MAP_KEY_JOIN)) {
            	joinInfo = (List) prmMap.get(ProjectPlanOfferDto.MAP_KEY_JOIN);
            }
            if(prmMap.containsKey(ProjectPlanOfferDto.MAP_KEY_ININ)) {
            	uiOfferEntrustInfo = (UIOfferEntrustInfo)prmMap.get(ProjectPlanOfferDto.MAP_KEY_ININ);
            }
            
            if(uiOfferDutyPersonInfo != null) {
	            //1.申込責任者
		      	projectPlanOfferDto.setPrmDutyOnerName(uiOfferDutyPersonInfo.getOnerNameKj());
		      	projectPlanOfferDto.setPrmDutySoufuName(uiOfferDutyPersonInfo.getSoufuName());
		      	projectPlanOfferDto.setPrmDutyName(uiOfferDutyPersonInfo.getName());
		      	projectPlanOfferDto.setPrmDutyJobType(uiOfferDutyPersonInfo.getJobType());
		      	projectPlanOfferDto.setPrmDutyAddress1(uiOfferDutyPersonInfo.getAddress1());
		      	projectPlanOfferDto.setPrmDutyAddress2(uiOfferDutyPersonInfo.getAddress2());
		      	projectPlanOfferDto.setPrmDutyAddress3(uiOfferDutyPersonInfo.getAddress3());
		      	projectPlanOfferDto.setPrmDutyTel(uiOfferDutyPersonInfo.getTel());
		      	projectPlanOfferDto.setPrmDutyZip(uiOfferDutyPersonInfo.getZip());
	      	}
            //2．申込参加者
            projectPlanOfferDto = settJoinInfoList(projectPlanOfferDto, joinInfo);

	      	if(uiOfferEntrustInfo != null) {
		      	//3.委任状
	      		//場合１．委任状が登録されている状態で、更新する時共栄会定時総会へ参加する人が存在、委任状がいらない場合、
	      		//削除処理後、確認画面へ委任状情報を表示しない
	        	String[] procKbn = (String[])prmMap.get(ProjectPlanOfferDto.MAP_KEY_PROC);
	        	String ininKbn = procKbn[1];
	        	
	        	if(ininKbn.equals(ProjectPlanOfferDto.PROC_KBN_DELETE)) {
			      	projectPlanOfferDto.setPrmIninFName("");
			      	projectPlanOfferDto.setPrmIninLName("");
			      	projectPlanOfferDto.setPrmIninMiseCd("");
			      	projectPlanOfferDto.setPrmIninMiseTotal("");
		        	projectPlanOfferDto.setPrmIninFirstUser("");
		        	projectPlanOfferDto.setPrmIninFirstPgm("");
		        	projectPlanOfferDto.setPrmIninFirstTmsp(null);
		        	projectPlanOfferDto.setPrmIninLastUser("");
		        	projectPlanOfferDto.setPrmIninLastPgm("");
		        	projectPlanOfferDto.setPrmIninLastTmsp(null);
	        	} else {
			      	projectPlanOfferDto.setPrmIninFName(uiOfferEntrustInfo.getIninFName());
			      	projectPlanOfferDto.setPrmIninLName(uiOfferEntrustInfo.getIninLName());
			      	projectPlanOfferDto.setPrmIninMiseCd(uiOfferEntrustInfo.getIninMiseCd());
			      	projectPlanOfferDto.setPrmIninMiseNm(getMiseNm(projectPlanOfferDto.getCondListMise(), uiOfferEntrustInfo.getIninMiseCd()));
			      	projectPlanOfferDto.setPrmIninMiseTotal(uiOfferEntrustInfo.getIninMiseTotal());
		        	projectPlanOfferDto.setPrmIninFirstUser(uiOfferEntrustInfo.getFirstUser());
		        	projectPlanOfferDto.setPrmIninFirstPgm(uiOfferEntrustInfo.getFirstPgm());
		        	projectPlanOfferDto.setPrmIninFirstTmsp(uiOfferEntrustInfo.getFirstTmsp());
		        	projectPlanOfferDto.setPrmIninLastUser(uiOfferEntrustInfo.getLastUser());
		        	projectPlanOfferDto.setPrmIninLastPgm(uiOfferEntrustInfo.getLastPgm());
		        	projectPlanOfferDto.setPrmIninLastTmsp(uiOfferEntrustInfo.getLastTmsp());	
	        	}
	      	}
    	}
		return projectPlanOfferDto;
    }
    
    /**
     * 店名称の取得
     * @param miseInfoList
     * @param miseCd
     * @return
     */
    private static String getMiseNm(List miseInfoList, String miseCd) {
    	String miseNm = "";
    	
    	for (Iterator ite = miseInfoList.iterator(); ite.hasNext();) {
    		
    		UIOfferMiseInfo uiOfferMiseInfo = (UIOfferMiseInfo) ite.next();
    	
    		if(miseCd.equals(uiOfferMiseInfo.getMiseCd())) {
    			miseNm = uiOfferMiseInfo.getMiseNameKj();
    			break;
    		}
    	}
    	return miseNm;
    }
    
    /**
     * 登録するパラメータ情報をマップへ設定する
     * @param projectPlanOfferDto　DTO
     * @param checkOfferInputParamLogic　チェックロジック
     * @return　情報が保持されているマップ
     */
    public static Map setRegistInfo(ProjectPlanOfferDto projectPlanOfferDto,
    		Map paramMap,
    		CheckOfferInputParamLogic checkOfferInputParamLogic) {
    	
    	String [] procKbn = new String [2];
    	boolean kyoeiChkFlg = checkOfferInputParamLogic.chkKyoeiFlg(projectPlanOfferDto.getPrmJoinPersonList());
    	
    	//1．削除の場合、キーが必要
    	List keyList = new ArrayList();
    	keyList.add(projectPlanOfferDto.getCondEntryCd());
    	keyList.add(projectPlanOfferDto.getCondEntryYear());
    	keyList.add(projectPlanOfferDto.getCondEntryKai());
    	keyList.add(projectPlanOfferDto.getCondCompanyCd());
    	keyList.add(projectPlanOfferDto.getCondOnerCd());
    	paramMap.put(ProjectPlanOfferDto.MAP_KEY_UNIQKEY, keyList);
    	
		//2.以前登録されているのに、申込責任者以外の参加者１，２，３情報がすべてない場合
		if(projectPlanOfferDto.getPrmEditKbn().equals(ProjectPlanOfferDto.EDIT_KBN_UPDATE)) {
			procKbn[0] = ProjectPlanOfferDto.PROC_KBN_UPDATE;
			boolean chkInfo = checkOfferInputParamLogic.chkJoinPersInfo(projectPlanOfferDto.getPrmJoinPersonList());
			if(chkInfo) {
	    		procKbn[0] = ProjectPlanOfferDto.PROC_KBN_DELETE;
	    		procKbn[1] = ProjectPlanOfferDto.PROC_KBN_DELETE;
	    		paramMap.put(ProjectPlanOfferDto.MAP_KEY_PROC, procKbn);
	    		paramMap.remove(ProjectPlanOfferDto.MAP_KEY_JOIN);
	    		paramMap.remove(ProjectPlanOfferDto.MAP_KEY_ININ);
	    		return paramMap;
    		} else {
    			//修正モードで、申込参加者がすべて欠席の場合
    			if(kyoeiChkFlg) {
    				
    				/* 2007.01.16
    				 * 編集画面→委任状画面へ遷移委任状情報を入力→委任状画面の戻る→
    				 * 編集画面の登録・終了→委任状画面へ遷移
    				 * 委任状情報があるかどうかの判断はしなく委任状画面からの処理かどうかを判断する
    				 */
//    				委任状の必須データが存在する場合、登録処理    				
//    		    	if (!(checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninFName()) ||
//        			checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninLName()) ||
//        			checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninMiseCd()) ||
//        			checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninMiseTotal()))) {
    				if(projectPlanOfferDto.getValChkMode().equals(ProjectPlanOfferDto.VALCHK_MODE_ININ)) {
			    		if(projectPlanOfferDto.getPrmIninKbn().equals(ProjectPlanOfferDto.EDIT_KBN_INSERT)) {
			    			procKbn[1] = ProjectPlanOfferDto.PROC_KBN_INSERT;
			    		} else if(projectPlanOfferDto.getPrmIninKbn().equals(ProjectPlanOfferDto.EDIT_KBN_UPDATE)) {
			    			procKbn[1] = ProjectPlanOfferDto.PROC_KBN_UPDATE;
			    		}
    				} else {
    		    		procKbn[1] = "";
    		    		//委任状画面へ遷移
    		    		return null;
    		    	}
    			} else {
//    				if ((checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninFName()) &&
//    	        			checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninLName()))) {
//    					procKbn[1] = "";
//    				} else 
    				//2007.1.15 
    				if(projectPlanOfferDto.getPrmIninKbn().equals(ProjectPlanOfferDto.EDIT_KBN_UPDATE)) {
    					procKbn[1] = ProjectPlanOfferDto.PROC_KBN_DELETE;
		    		} else {
		    			procKbn[1] = "";
		    		}
    			}
    		}
    	} else if(projectPlanOfferDto.getPrmEditKbn().equals(ProjectPlanOfferDto.EDIT_KBN_INSERT)) {
    		procKbn[0] = ProjectPlanOfferDto.PROC_KBN_INSERT;

    		//3-1.参加者情報が１人でも存在するかどうか
    		//3-2．参加者の全員が共栄会定時総会へ欠席かどうか,委任状入力情報があるか
        	List joinPers = projectPlanOfferDto.getPrmJoinPersonList();
        	int persCnt = 0;
        	for(int i = 0; i < joinPers.size(); i++) {
        		UIOfferJoinPersonInfo uiOfferJoinPersonInfo = (UIOfferJoinPersonInfo)joinPers.get(i);
        		if(checkOfferInputParamLogic.isNull(uiOfferJoinPersonInfo.getLNameKna())
        				&& checkOfferInputParamLogic.isNull(uiOfferJoinPersonInfo.getFNameKna())) {
        			persCnt += 0;
        		} else {
        			persCnt += 1;
        		}
        	}
    		if(persCnt > 0) {
    			/* 207.01.16
    			 * 共栄会定時総会が参加者全員欠席(kyoeiChkFlg == true)かつ
    			 * 編集画面からの処理であればnullをリタンー
    			 * 委任状画面からの処理であれば処理区分（procKbn[1]）にはPROC_KBN_INSERT(登録)区分を設定
    			 * それ以外の場合は委任状に対する登録・更新・削除処理はしない
    			 */
    			if(kyoeiChkFlg && 
    					projectPlanOfferDto.getValChkMode().equals(ProjectPlanOfferDto.VALCHK_MODE_EDIT)) {
    				return null;
    			} else {
    				if(projectPlanOfferDto.getValChkMode().equals(ProjectPlanOfferDto.VALCHK_MODE_ININ)) {
    					procKbn[1] = ProjectPlanOfferDto.PROC_KBN_INSERT;
    				} else {
    				//procKbn[1] = ProjectPlanOfferDto.PROC_KBN_INSERT;
    					procKbn[1] = "";
    				}
    			}
    		} else {
	    		procKbn[0] = ProjectPlanOfferDto.PROC_KBN_DELETE;
	    		procKbn[1] = ProjectPlanOfferDto.PROC_KBN_DELETE;
	    		paramMap.put(ProjectPlanOfferDto.MAP_KEY_PROC, procKbn);
	    		paramMap.remove(ProjectPlanOfferDto.MAP_KEY_JOIN);
	    		paramMap.remove(ProjectPlanOfferDto.MAP_KEY_ININ);
	    		return paramMap;
    		}
    	}
    	paramMap.put(ProjectPlanOfferDto.MAP_KEY_PROC, procKbn);
		
		return paramMap;
    }
    
    /**
     * 店舗を切り替えた場合
     * @param defMiseCd　ディフォルト店コード
     * @param miseList　店舗リスト
     */
    public static ProjectPlanOfferDto choiceMise(
    		ProjectPlanOfferDto projectPlanOfferDto, 
    		CheckOfferInputParamLogic checkOfferInputParamLogic) {
    	
		int i = 0;
		String tmpTabNo = projectPlanOfferDto.getPrmTabNo();
		String tmpMiseCd = projectPlanOfferDto.getPrmMiseCd();

		//-----
		String [] tmpMiseCdL = projectPlanOfferDto.getPrmMiseCdList();
		String [] tmpMiseNameL = projectPlanOfferDto.getMiseNameList();
		String [] tmpSibuNameL = projectPlanOfferDto.getSibuNameList();
		
		if(tmpMiseCdL == null) {
			tmpMiseCdL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		if(tmpMiseNameL == null) {
			tmpMiseNameL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		if(tmpSibuNameL == null) {
			tmpSibuNameL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		//申込責任者タブから遷移した場合
		if(checkOfferInputParamLogic.isNull(tmpTabNo) || tmpTabNo.equals("0")) {
			tmpTabNo = "";
		}
		
		if(checkOfferInputParamLogic.isNull(tmpTabNo)) {
			//既存に登録されている店コードを取得する
			List joinPList = projectPlanOfferDto.getPrmJoinPersonList();
			List tempMiseInsList = new ArrayList();
	    	for(int p = 0; p < joinPList.size(); p++) {
	    		UIOfferJoinPersonInfo uiOfferJoinPersonInfo = (UIOfferJoinPersonInfo)joinPList.get(p);
	    		tempMiseInsList.add(uiOfferJoinPersonInfo.getMiseCd());
	    	}
  			
			for(int j = 1 ; j < ProjectPlanOfferDto.FORM_SIZE; j++) {
				
				for (Iterator ite = projectPlanOfferDto.getCondListMise().iterator(); ite.hasNext();) {
		    		
		    		UIOfferMiseInfo uiOfferMiseInfo = (UIOfferMiseInfo) ite.next();
		    		
	    			if(i == 0) {
	    				if(checkOfferInputParamLogic.isNull(uiOfferMiseInfo.getMiseFirst())) {
	    					projectPlanOfferDto.setFirstSibuName("");
	    					projectPlanOfferDto.setFirstMiseCd("");
	    				} else {
	    					//申込取消の時、初期化するため、
	    					projectPlanOfferDto.setFirstSibuName(uiOfferMiseInfo.getSibuName());
	    					projectPlanOfferDto.setFirstMiseCd(uiOfferMiseInfo.getMiseFirst());
	    				}
	    			}
	    			if(tempMiseInsList.get(j - 1).equals("")) {
	    				if(checkOfferInputParamLogic.isNull(uiOfferMiseInfo.getMiseFirst())) {
							tmpMiseNameL[j] = "";
			    			tmpMiseCdL[j] = "";
			    			tmpSibuNameL[j] = "";	    					
	    				} else if(uiOfferMiseInfo.getMiseFirst().equals(uiOfferMiseInfo.getMiseCd())) {
							tmpMiseNameL[j] = uiOfferMiseInfo.getMiseNameKj();
			    			tmpMiseCdL[j] = uiOfferMiseInfo.getMiseFirst();
			    			tmpSibuNameL[j] = uiOfferMiseInfo.getSibuName();
						}
	    			} else {
	    				if(tempMiseInsList.get(j - 1).equals(uiOfferMiseInfo.getMiseCd())) {
							tmpMiseNameL[j] = uiOfferMiseInfo.getMiseNameKj();
			    			tmpMiseCdL[j] = uiOfferMiseInfo.getMiseCd();
			    			tmpSibuNameL[j] = uiOfferMiseInfo.getSibuName();
						}
	    			}
		    		i++;
		    	}
			}
		} else if(!checkOfferInputParamLogic.isNull(tmpTabNo)) {
			
			for (Iterator ite = projectPlanOfferDto.getCondListMise().iterator(); ite.hasNext();) {
	    		
	    		UIOfferMiseInfo uiOfferMiseInfo = (UIOfferMiseInfo) ite.next();
	    		
    			if(i == 0) {
    				//申込取消の時、初期化するため、
        			projectPlanOfferDto.setFirstSibuName(uiOfferMiseInfo.getSibuName());
        			projectPlanOfferDto.setFirstMiseCd(uiOfferMiseInfo.getMiseFirst());
    			}
			
				if(tmpMiseCd.equals(uiOfferMiseInfo.getMiseCd())) {
					tmpMiseCdL[Integer.parseInt(tmpTabNo)] = tmpMiseCd;
					tmpMiseNameL[Integer.parseInt(tmpTabNo)] = uiOfferMiseInfo.getMiseNameKj();
					tmpSibuNameL[Integer.parseInt(tmpTabNo)] = uiOfferMiseInfo.getSibuName();
				}
				i++;
			}
		}
    	projectPlanOfferDto.setPrmMiseCdList(tmpMiseCdL);
    	projectPlanOfferDto.setMiseNameList(tmpMiseNameL);
    	projectPlanOfferDto.setSibuNameList(tmpSibuNameL);
    	
    	return projectPlanOfferDto;
	}

    /**
     * 登録する情報をDTOから各エンティティへ保持する
     * @param projectPlanOfferDto　DTO
     * @param checkOfferInputParamLogic　チェックロジック
     * @return　Map 登録する内容が保持されているマップ
     */
    public static Map setEntityRegInfoMap(ProjectPlanOfferDto projectPlanOfferDto,
    		CheckOfferInputParamLogic checkOfferInputParamLogic) {
    	Map tmpInfoMap = new HashMap();
    	
    	//1.申込責任者
    	UIOfferDutyPersonInfo uiOfferDutyPersonInfo = new UIOfferDutyPersonInfo();
    	uiOfferDutyPersonInfo.setOnerNameKj(projectPlanOfferDto.getPrmDutyOnerName());
    	uiOfferDutyPersonInfo.setEntryCd(projectPlanOfferDto.getCondEntryCd());
    	uiOfferDutyPersonInfo.setEntryYear(projectPlanOfferDto.getCondEntryYear());
    	uiOfferDutyPersonInfo.setEntryKai(projectPlanOfferDto.getCondEntryKai());
    	uiOfferDutyPersonInfo.setCompanyCd(projectPlanOfferDto.getCondCompanyCd());
    	uiOfferDutyPersonInfo.setOnerCd(projectPlanOfferDto.getCondOnerCd());
    	uiOfferDutyPersonInfo.setSoufuName(projectPlanOfferDto.getPrmDutySoufuName());
    	uiOfferDutyPersonInfo.setTel(projectPlanOfferDto.getPrmDutyTel());
    	uiOfferDutyPersonInfo.setName(projectPlanOfferDto.getPrmDutyName());
    	uiOfferDutyPersonInfo.setJobType(checkOfferInputParamLogic.isNull(
    			projectPlanOfferDto.getPrmDutyJobType()) ? "" : projectPlanOfferDto.getPrmDutyJobType());
    	uiOfferDutyPersonInfo.setAddress1(projectPlanOfferDto.getPrmDutyAddress1());
    	uiOfferDutyPersonInfo.setAddress2(
    			checkOfferInputParamLogic.isNull(
	        			projectPlanOfferDto.getPrmDutyAddress2()) ? "" : projectPlanOfferDto.getPrmDutyAddress2());
    	uiOfferDutyPersonInfo.setAddress3((
    			checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmDutyAddress3()) ? "" : projectPlanOfferDto.getPrmDutyAddress3()));
    	uiOfferDutyPersonInfo.setZip(projectPlanOfferDto.getPrmDutyZip());
    	uiOfferDutyPersonInfo.setAtesakiKbn(ProjectPlanOfferDto.ATESAKI_KBN);
    	
    	//2.申込参加者
    	List newJoinPers = new ArrayList();
    	List joinPers = projectPlanOfferDto.getPrmJoinPersonList();
    	//int tmpTabNo = 1;
    	for(int i = 0; i < joinPers.size(); i++) {
    		UIOfferJoinPersonInfo uiOfferJoinPersonInfo = (UIOfferJoinPersonInfo)joinPers.get(i);
    		if (checkOfferInputParamLogic.checkRegistValidtity(uiOfferJoinPersonInfo)) {
    			//入力データがある場合だけをセットする
    			uiOfferJoinPersonInfo.setEntryCd(projectPlanOfferDto.getCondEntryCd());
	    		uiOfferJoinPersonInfo.setEntryYear(projectPlanOfferDto.getCondEntryYear());
	    		uiOfferJoinPersonInfo.setEntryKai(projectPlanOfferDto.getCondEntryKai());
	    		uiOfferJoinPersonInfo.setCompanyCd(projectPlanOfferDto.getCondCompanyCd());
	    		uiOfferJoinPersonInfo.setOnerCd(projectPlanOfferDto.getCondOnerCd());
	    		uiOfferJoinPersonInfo.setMiseCd(projectPlanOfferDto.getPrmMiseCdList()[i+1]);
	    		uiOfferJoinPersonInfo.setTabNo(String.valueOf(i+1));
	    		uiOfferJoinPersonInfo.setAbsenceReason(checkOfferInputParamLogic.isNull(
	    				uiOfferJoinPersonInfo.getAbsenceReason()) ? "" : uiOfferJoinPersonInfo.getAbsenceReason());
	    		uiOfferJoinPersonInfo.setNote(checkOfferInputParamLogic.isNull(
	    				uiOfferJoinPersonInfo.getNote()) ? "" : uiOfferJoinPersonInfo.getNote());
	    		uiOfferJoinPersonInfo.setYobiFlg1("");
	    		uiOfferJoinPersonInfo.setYobiFlg2("");
	    		uiOfferJoinPersonInfo.setYobiFlg3("");
	        	newJoinPers.add(uiOfferJoinPersonInfo);
	        	//tmpTabNo++;
    		}
    	}
    	//---2007/01/15 参加者の共栄定時会
    	boolean kyoeiChkFlg = checkOfferInputParamLogic.chkKyoeiFlg(projectPlanOfferDto.getPrmJoinPersonList());
    	if(kyoeiChkFlg) {
    		////---2007/01/15 委任状の有無判断を氏名（氏）から店コードに変更
    		//if(!(checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninFName()))) {
	    	//if(!(checkOfferInputParamLogic.isNull(projectPlanOfferDto.getPrmIninMiseCd()))) {
		    	//3.委任状
		    	UIOfferEntrustInfo uiOfferEntrustInfo = new UIOfferEntrustInfo();
		    	uiOfferEntrustInfo.setEntryCd(projectPlanOfferDto.getCondEntryCd());
		    	uiOfferEntrustInfo.setEntryYear(projectPlanOfferDto.getCondEntryYear());
		    	uiOfferEntrustInfo.setEntryKai(projectPlanOfferDto.getCondEntryKai());
		    	uiOfferEntrustInfo.setCompanyCd(projectPlanOfferDto.getCondCompanyCd());
		    	uiOfferEntrustInfo.setOnerCd(projectPlanOfferDto.getCondOnerCd());    	
		    	uiOfferEntrustInfo.setIninFName(projectPlanOfferDto.getPrmIninFName());
		    	uiOfferEntrustInfo.setIninLName(projectPlanOfferDto.getPrmIninLName());
		    	uiOfferEntrustInfo.setIninMiseCd(projectPlanOfferDto.getPrmIninMiseCd());
		    	uiOfferEntrustInfo.setIninMiseTotal(projectPlanOfferDto.getPrmIninMiseTotal());
		    	uiOfferEntrustInfo.setFirstUser(projectPlanOfferDto.getPrmIninFirstUser());
		    	uiOfferEntrustInfo.setFirstPgm(projectPlanOfferDto.getPrmIninFirstPgm());
		    	uiOfferEntrustInfo.setFirstTmsp(projectPlanOfferDto.getPrmIninFirstTmsp());
		    	uiOfferEntrustInfo.setLastUser(projectPlanOfferDto.getPrmIninLastUser());
		    	uiOfferEntrustInfo.setLastPgm(projectPlanOfferDto.getPrmIninLastPgm());
		    	uiOfferEntrustInfo.setLastTmsp(projectPlanOfferDto.getPrmIninLastTmsp());
		    	tmpInfoMap.put(ProjectPlanOfferDto.MAP_KEY_ININ, uiOfferEntrustInfo);
	    	//}
    	}
    	tmpInfoMap.put(ProjectPlanOfferDto.MAP_KEY_DUTY, uiOfferDutyPersonInfo);
    	tmpInfoMap.put(ProjectPlanOfferDto.MAP_KEY_JOIN, newJoinPers);
    	return tmpInfoMap;
    }
    
    /**
     * 改行処理を行う
     * @param notice
     * @return　改行した文字列
     */
    private static String changeNotice(String notice) {
        String addBrMemo = "";

        int index = 0;
        boolean endFlg = true;
        StringBuffer enterLine = new StringBuffer();
        
        if(notice != null && !notice.equals("")){
            while(endFlg){
                index = notice.indexOf(_ENTER_WORD);
                if(index == -1) {
                    endFlg = false;
                } else{
                	String topMemo = notice.substring(0, index);
                	if(topMemo.length() > CHAR_CNT) {
                		enterLine = enterLine(topMemo, enterLine);
                	} else {
                		enterLine.append(topMemo);
                		enterLine.append(_ENTER);
                	}
                	notice = notice.substring(index+1);
                }
            }
        	if(notice.length() > CHAR_CNT) {
        		enterLine = enterLine(notice, enterLine);
        	} else {
        		enterLine.append(notice);
        	}
            addBrMemo = enterLine.toString();
        }
        return addBrMemo;
    }
    
    /**
     * 改行処理を行う
     * @param notice
     * @return　改行した文字列
     */
    private static StringBuffer enterLine(String tmpTxt, StringBuffer retNotice) {
    	
    	int iTopTxt = tmpTxt.length() / CHAR_CNT;
    	int iModTxt = tmpTxt.length() % CHAR_CNT;
    	
    	if(iTopTxt > 0) {
    		for(int i = 0 ; i < iTopTxt; i++) {
    			if(i != 0) {
    				retNotice.append(_ENTER);
    			}
    			retNotice.append(tmpTxt.substring((i*CHAR_CNT), ((i+1)*CHAR_CNT)));
    		}
    		if(iModTxt > 0) {
    			retNotice.append(_ENTER + tmpTxt.substring(tmpTxt.length() - iModTxt) + _ENTER );
    		}
    	} else {
    		retNotice.append(tmpTxt);
    	}
        return retNotice;
    }
    
    
    /**
     * 
     * @param projectPlanOfferDto
     * @param joinInfo
     * @return
     */
    private static ProjectPlanOfferDto settJoinInfoList(ProjectPlanOfferDto projectPlanOfferDto, List joinInfo) {
    	
		//-----
    	String [] tmpMiseCdL = projectPlanOfferDto.getPrmMiseCdList();
		String [] tmpMiseNameL = projectPlanOfferDto.getMiseNameList();
		String [] tmpSibuNameL = projectPlanOfferDto.getSibuNameList(); 

		if(tmpMiseCdL == null) {
			tmpMiseCdL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		if(tmpMiseNameL == null) {
			tmpMiseNameL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		if(tmpSibuNameL == null) {
			tmpSibuNameL = new String[ProjectPlanOfferDto.FORM_SIZE];
		}
		
      	if(joinInfo != null) {
      		for(int i = 0 ; i < joinInfo.size(); i++) {
      			UIOfferJoinPersonInfo uiOfferJoinPersonInfo = (UIOfferJoinPersonInfo)joinInfo.get(i);
      			tmpMiseCdL[i + 1] = uiOfferJoinPersonInfo.getMiseCd();
      		}
      		
      		//2.申込参加者が3名以下の場合
      		if(joinInfo.size() < 3) {
      			
      			int listSz = 3 - joinInfo.size();
      			
      			for(int i = 0 ; i < listSz; i++) {
        			UIOfferJoinPersonInfo uiOfferJoinPersonInfo = new UIOfferJoinPersonInfo();
        			uiOfferJoinPersonInfo.setTabNo(String.valueOf(listSz + joinInfo.size()));
        			uiOfferJoinPersonInfo.setMiseCd("");
        			uiOfferJoinPersonInfo.setLNameKj("");
        			uiOfferJoinPersonInfo.setLNameKna("");
        			uiOfferJoinPersonInfo.setFNameKna("");
        			uiOfferJoinPersonInfo.setSex("");
        			uiOfferJoinPersonInfo.setAge("");
        			uiOfferJoinPersonInfo.setJigyoFlg("");
        			uiOfferJoinPersonInfo.setKonshinFlg("");
        			uiOfferJoinPersonInfo.setKyoeiFlg("");
        			uiOfferJoinPersonInfo.setShukuhakuFlg("");
        			uiOfferJoinPersonInfo.setNote("");
        			uiOfferJoinPersonInfo.setAbsenceReason("");
        			uiOfferJoinPersonInfo.setFirstUser("");
        			uiOfferJoinPersonInfo.setFirstPgm("");
        			uiOfferJoinPersonInfo.setFirstTmsp(null);
        			uiOfferJoinPersonInfo.setLastUser("");
        			uiOfferJoinPersonInfo.setLastPgm("");
        			uiOfferJoinPersonInfo.setLastTmsp(null);
        			joinInfo.add(uiOfferJoinPersonInfo);
      			}
      		}
      		projectPlanOfferDto.setPrmJoinPersonList(joinInfo);
      		projectPlanOfferDto.setPrmMiseCdList(tmpMiseCdL);
      		projectPlanOfferDto.setMiseNameList(tmpMiseNameL);
      		projectPlanOfferDto.setSibuNameList(tmpSibuNameL);
      	}
      	return projectPlanOfferDto;
    }
}