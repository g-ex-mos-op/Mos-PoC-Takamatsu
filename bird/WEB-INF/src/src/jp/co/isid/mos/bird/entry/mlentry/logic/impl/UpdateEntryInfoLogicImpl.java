package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryOnerDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryStateDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIResultStateDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIStaffDao;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryInfo;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryState;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIResultState;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIStaff;
import jp.co.isid.mos.bird.entry.mlentry.logic.UpdateEntryInfoLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * マスターライセンスエントリー情報の更新
 * @author Aspac
 */
public class UpdateEntryInfoLogicImpl implements UpdateEntryInfoLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN008L08";
    
    
    //オーナー別エントリー状況
    private UIEntryOnerStatusDao uiEntryOnerStatusMlEntryDao;
    //エントリーオーナー宛先情報
    private UIEntryOnerDao uiEntryOnerMlEntryDao;
    //マスターライセンスエントリー状況
    private UIEntryStateDao uiEntryStateMlEntryDao;
    //スタッフマスタ
    private UIStaffDao uiStaffMlEntryDao;
    
    
    //マスターライセンスエントリー状況 (BT23MLEJ)
    private UIEntryDao uiEntryDao; 
    
    //エントリー結果状況履歴 (BT32MLKR)
    private UIResultStateDao uiResultStateDao;
    
    /**
     * エントリー情報の登録更新を行う。
     * @param MlEntryDto     
     * */
    public void execute(MlEntryDto dto) throws ApplicationException {
        
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        
        // 入力情報
        List listEntry = dto.getListEntryStateRegist();
        
        // 入力情報の有無をチェック
        String entryFlg = MlEntryCommon.ENTRY_FLG_FUSANKA;
        for (Iterator ite = listEntry.iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();
            if (uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD) ||
                    uiEntryState.isInsertFlg()) {
                entryFlg = MlEntryCommon.ENTRY_FLG_SANKA;
                break;
            }
        }
        
        
        /* 更新対象レコードを設定 */
        setRegistRecord(currentTimestamp, dto);
        
        // オーナー別エントリー状況の更新 (BT20ENON)
        doEntryOnerStatus(dto, currentTimestamp, entryFlg);
        
        // エントリーオーナー宛先情報の更新 (BT21ENOJ)
        doEntryOner(dto, currentTimestamp, entryFlg);
        
        // エントリー状況の更新 (BT23MLEJ)
        doEntryState(dto, currentTimestamp);
        
        // マスターライセンス結果状況履歴の更新 (BT32MLKR)
        doEntryResult(dto, currentTimestamp);
        
		// スタッフマスタ更新 (BM12STAF)
		updateMstStaff(dto, currentTimestamp);
        
    }


    /**
     * スタッフID別エントリー状況リストから各テーブルの更新リストを生成する
     * ※エントリー状況(BT23MLEJ)を更新するデータのリストを生成する。
     * ※結果状況履歴(BT32MLKR)を更新するデータのリストを生成する。
     * ※加盟店スタッフ(BM12STAF)を更新するデータのリストを生成する。
     */
    private void setRegistRecord(Timestamp currentTimestamp, MlEntryDto dto){
        
        List listEntry  = new ArrayList();//BT23MLEJ更新リスト
        List listResult = new ArrayList();//BT32MLKR更新リスト
        List listStaff  = new ArrayList();//BM12STAF更新リスト
        
        UIStaff uiStaff = null;
        UIEntryInfo uiEntry = null;
        UIResultState uiResult = null;
        
        for (Iterator ite = dto.getListEntryStateRegist().iterator(); ite.hasNext();) {
            UIEntryState uiEntryState = (UIEntryState) ite.next();

            uiEntry  = new UIEntryInfo();
            uiStaff  = new UIStaff();
            uiResult = new UIResultState();
            
            //スタッフIDがないデータ(画面上で追加入力欄が未入力)を更新対象から落とす。
            if(uiEntryState.getStaffId() == null || uiEntryState.getStaffId().equals("")) {
                uiEntryState.setInsertFlg(false);
                continue;
            }
            
            //エントリー済＋履歴あり
            //エントリー未＋履歴あり
            if (uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_NO_ENTRY_RECORD)){

                
                //***************************************************
                //*** 結果状況履歴 (BT32MLKR) ***********************
                //***************************************************
                
                uiResult.setInsertFlg(true);
                uiResult.setStaffId(uiEntryState.getStaffId());
                uiResult.setLicenseKbn(MlEntryCommon.LICENSE_KBN_IPPAN);//一般ユーザ
				
                //総受験回数をインクリメント
                //再エントリ基準年度が有効期間内(2年度以内)であれば引継ぎ
                //有効期間がすぎていれば今年の年度で更新
				String count = "0";
				String baseY = "";
                String examNo = "";
                //過去履歴がなく今回の受験にエントリー済み
				if(uiEntryState.getEntryYear().equals(uiEntryState.getTotalLastYear()) &&
					uiEntryState.getEntryKai().equals(uiEntryState.getTotalLastKai())) {
					count = "1";
                    examNo = uiEntryState.getExamNo();
                    baseY = dto.getEntryYear();//再エントリ基準年度に本年度を更新
				}
				else {
				    
                    //前回引きつき有効期限を越えている。あるいは過去に受験していない。
                    if(isNull(uiEntryState.getTotalLastYear()) ||
                            isNull(uiEntryState.getTotalLastKai())) {
                        baseY = dto.getEntryYear();//本年度
                        uiResult.setReentryFlg(MlEntryCommon.REENTRY_FLG_DEF);//初回エントリー
                        count = "1";//受験回数1回目
                    }
                    //有効履歴を引き継ぐ
                    else {
                        baseY = uiEntryState.getReentryBaseYear();//基準年度
                        uiResult.setReentryFlg(MlEntryCommon.REENTRY_FLG_RE);//再エントリー
                        examNo = uiEntryState.getExamNo();//前回受験番号
                        count = uiEntryState.getEntryCount();//受験回数を更新
                        if(count != null && !count.equals("")) {
                            count = String.valueOf(Integer.parseInt(count.trim()) + 1);
                        }
                    }
				}
				uiResult.setExamNo(examNo);
                uiResult.setEntryCount(count);
                uiResult.setReentryBaseYear(baseY);
                
                //最終受験年度・回には今回の年度・回を登録
                //総合結果には、前回の総合結果を登録
				uiResult.setTotalLastYear(dto.getEntryYear());
				uiResult.setTotalLastKai(dto.getEntryKai());
				uiResult.setTotalResult(MlEntryCommon.TOTAL_RESULT_FLG_MIJYUKEN);
                
                //現在最新履歴の能力テスト結果が『合格』『免除』であれば『免除』
                //現在最新履歴の能力チェック結果が『未受験』『不合格』であれば『未受験』
                if(uiEntryState.getSub1Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU) ||
                        uiEntryState.getSub1Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO)) {
                    uiResult.setSub1Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO);
                    uiResult.setSub1LastKai(uiEntryState.getSub1LastKai());
                    uiResult.setSub1LastYear(uiEntryState.getSub1LastYear());
                } else if(uiEntryState.getSub1Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN) ||
                            uiEntryState.getSub1Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU)) {
                    uiResult.setSub1Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);
                    uiResult.setSub1LastKai(dto.getEntryKai());
                    uiResult.setSub1LastYear(dto.getEntryYear());
                }
                
                //現在最新履歴の筆記テスト結果が『合格』『免除』であれば『免除』
                //現在最新履歴の筆記チェック結果が『未受験』『不合格』であれば『未受験』
                if(uiEntryState.getSub2Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU) ||
                        uiEntryState.getSub2Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO)) {
                    uiResult.setSub2Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO);
                    uiResult.setSub2LastKai(uiEntryState.getSub2LastKai());
                    uiResult.setSub2LastYear(uiEntryState.getSub2LastYear());
                } else if(uiEntryState.getSub2Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN) ||
                            uiEntryState.getSub2Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU)) {
                    uiResult.setSub2Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);
                    uiResult.setSub2LastKai(dto.getEntryKai());
                    uiResult.setSub2LastYear(dto.getEntryYear());
                }
                
                //現在最新履歴の面接チェック結果が『合格』『免除』であれば『免除』
                //現在最新履歴の面接チェック結果が『未受験』であれば『未受験』
                //現在最新履歴の面接チェック結果が『不合格』であれば『未受験』
                //現在最新履歴の面接チェック結果が『受験不可』であれば『未受験』
                if(uiEntryState.getSub3Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_GOUKAKU)||
                        uiEntryState.getSub3Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO)){
                    uiResult.setSub3Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MENJYO);
                    uiResult.setSub3LastKai(uiEntryState.getSub3LastKai());
                    uiResult.setSub3LastYear(uiEntryState.getSub3LastYear());
                } else if(uiEntryState.getSub3Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN)) {
                        uiResult.setSub3Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);
                        uiResult.setSub3LastKai(dto.getEntryKai());
                        uiResult.setSub3LastYear(dto.getEntryYear());
                } else if(uiEntryState.getSub3Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_FUGOUKAKU)){
                    //2022.03.22 kobayashi 面接複数回受験対応 from
                    //if(uiEntryState.getBeforeFlg().equals(MlEntryCommon.BEFORE_SUB3_JYUKEN)) {
                    //    uiResult.setSub3Result(MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA);
                    //} else {
                    //    uiResult.setSub3Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);
                    //    uiResult.setSub3LastKai(dto.getEntryKai());
                    //    uiResult.setSub3LastYear(dto.getEntryYear());
                    //}
                    uiResult.setSub3Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);
                    uiResult.setSub3LastKai(dto.getEntryKai());
                    uiResult.setSub3LastYear(dto.getEntryYear());                
                    //2022.03.22 kobayashi 面接複数回受験対応 to
                } else if(uiEntryState.getSub3Result().equals(MlEntryCommon.SUB_BASE_RESULT_FLG_JYUKENFUKA)){
                    uiResult.setSub3Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);
                    uiResult.setSub3LastKai(dto.getEntryKai());
                    uiResult.setSub3LastYear(dto.getEntryYear());                    
                }
                
                
                //更新時は自動生成SQLのパラムに登録情報を指定しないことで更新を省略
                //登録時のみ登録データが追加される。
                uiResult.setFirstUser(dto.getBirdUserInfo().getUserID()); //登録ユーザ
                uiResult.setFirstPgm(LOGIC_ID.substring(0, 6));           //登録プログラム
                uiResult.setFirstTmsp(currentTimestamp);                  //登録時タイムスタンプ
                uiResult.setLastUser(dto.getBirdUserInfo().getUserID());  //修正ユーザ
                uiResult.setLastPgm(LOGIC_ID.substring(0, 6));            //修正プログラム
                uiResult.setLastTmsp(currentTimestamp);                   //修正時タイムスタンプ
                
                
                
                //***************************************************
                //*** エントリー状況 (BT23MLEJ) *********************
                //***************************************************
                
                uiEntry.setInsertFlg(true);
                uiEntry.setEntryCd(dto.getEntryCd());
                uiEntry.setEntryYear(dto.getEntryYear());
                uiEntry.setEntryKai(dto.getEntryKai());
                uiEntry.setStaffId(uiEntryState.getStaffId());
                uiEntry.setCompanyCd(dto.getCondCompanyCd());
                uiEntry.setOnerCd(uiEntryState.getOnerCd());
                uiEntry.setExamNo(examNo);
                uiEntry.setEntryPlaceCd(uiEntryState.getEntryPlaceCd());
                uiEntry.setNote(uiEntryState.getNote());
                uiEntry.setAbilityChk(uiEntryState.getAbilityChk());
                uiEntry.setExamChk(uiEntryState.getExamChk());
                uiEntry.setInterviewChk(uiEntryState.getInterviewChk());
                uiEntry.setEmpExpYear(uiEntryState.getEmpExpYear());
                uiEntry.setEmpExpMonth(uiEntryState.getEmpExpMonth());
                uiEntry.setPaExpYear(uiEntryState.getPaExpYear());
                uiEntry.setPaExpMonth(uiEntryState.getPaExpMonth());
                uiEntry.setJob(uiEntryState.getJob());
                
                //更新時は自動生成SQLのパラムに登録情報を指定しないことで更新を省略
                //登録時のみ登録データが追加される。
                uiEntry.setFirstUser(dto.getBirdUserInfo().getUserID());  //登録ユーザ
                uiEntry.setFirstPgm(LOGIC_ID.substring(0, 6));            //登録プログラム
                uiEntry.setFirstTmsp(currentTimestamp);                   //登録時タイムスタンプ
                uiEntry.setLastUser(dto.getBirdUserInfo().getUserID());   //修正ユーザ
                uiEntry.setLastPgm(LOGIC_ID.substring(0, 6));             //修正プログラム
                uiEntry.setLastTmsp(currentTimestamp);                    //修正時タイムスタンプ
                
            }
            
            //エントリー済＋履歴なし ※空レコード(今回受験データ)は存在するが有効な過去履歴がない状態。
            //新規エントリー(エントリー未済み)
            else if(uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD) ||
                        uiEntryState.isInsertFlg()) {
				
                
                //***************************************************
                //*** 結果状況履歴 (BT32MLKR) ***********************
                //***************************************************
                
                uiResult.setInsertFlg(true);
                uiResult.setStaffId(uiEntryState.getStaffId());
                uiResult.setLicenseKbn(MlEntryCommon.LICENSE_KBN_IPPAN);//一般ユーザ
                uiResult.setTotalResult(MlEntryCommon.TOTAL_RESULT_FLG_MIJYUKEN);//総合結果 : 未受験
                uiResult.setTotalLastYear(dto.getEntryYear());
                uiResult.setTotalLastKai(dto.getEntryKai());
                uiResult.setEntryCount("1");//総受験回数
                uiResult.setReentryBaseYear(dto.getEntryYear());//再エントリー基準年度に本年度を登録
                uiResult.setReentryFlg(MlEntryCommon.REENTRY_FLG_DEF);//初回エントリー
                uiResult.setSub1LastKai(dto.getEntryKai());
                uiResult.setSub1LastYear(dto.getEntryYear());
                uiResult.setSub1Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);  //チェックステータス：未受験
                uiResult.setSub2LastKai(dto.getEntryKai());
                uiResult.setSub2LastYear(dto.getEntryYear());
                uiResult.setSub2Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);  //チェックステータス：未受験
                uiResult.setSub3LastKai(dto.getEntryKai());
                uiResult.setSub3LastYear(dto.getEntryYear());
                uiResult.setSub3Result(MlEntryCommon.SUB_BASE_RESULT_FLG_MIJYUKEN);  //チェックステータス：未受験
                if(isNull(uiEntryState.getExamNo())) {
                    uiResult.setExamNo("");
                }else{
                    //受験番号採番済み
                    uiResult.setExamNo(uiEntryState.getExamNo());
                }
                
                uiResult.setFirstUser(dto.getBirdUserInfo().getUserID()); //登録ユーザ
                uiResult.setFirstPgm(LOGIC_ID.substring(0, 6));           //登録プログラム
                uiResult.setFirstTmsp(currentTimestamp);                  //登録時タイムスタンプ
                uiResult.setLastUser(dto.getBirdUserInfo().getUserID());  //修正ユーザ
                uiResult.setLastPgm(LOGIC_ID.substring(0, 6));            //修正プログラム
                uiResult.setLastTmsp(currentTimestamp);                   //修正時タイムスタンプ
                
                
                //***************************************************
                //*** エントリー状況 (BT23MLEJ) *********************
                //***************************************************
                
                uiEntry.setInsertFlg(true);
                uiEntry.setEntryCd(dto.getEntryCd());
                uiEntry.setEntryYear(dto.getEntryYear());
                uiEntry.setEntryKai(dto.getEntryKai());
                uiEntry.setStaffId(uiEntryState.getStaffId());
                uiEntry.setCompanyCd(dto.getCondCompanyCd());
                uiEntry.setOnerCd(uiEntryState.getOnerCd());
                uiEntry.setEntryPlaceCd(uiEntryState.getEntryPlaceCd());
                uiEntry.setNote(uiEntryState.getNote());
                uiEntry.setAbilityChk(uiEntryState.getAbilityChk());
                uiEntry.setExamChk(uiEntryState.getExamChk());
                uiEntry.setInterviewChk(uiEntryState.getInterviewChk());
                uiEntry.setEmpExpYear(uiEntryState.getEmpExpYear());
                uiEntry.setEmpExpMonth(uiEntryState.getEmpExpMonth());
                uiEntry.setPaExpYear(uiEntryState.getPaExpYear());
                uiEntry.setPaExpMonth(uiEntryState.getPaExpMonth());
                uiEntry.setJob(uiEntryState.getJob());
                if(isNull(uiEntryState.getExamNo())) {
                    uiEntry.setExamNo("");
                }else{
                    //受験番号採番済み
                    uiEntry.setExamNo(uiEntryState.getExamNo());
                }
                
                
	            uiEntry.setFirstUser(dto.getBirdUserInfo().getUserID());
	            uiEntry.setFirstPgm(LOGIC_ID.substring(0, 6));
	            uiEntry.setFirstTmsp(currentTimestamp);
                uiEntry.setLastUser(dto.getBirdUserInfo().getUserID());
                uiEntry.setLastPgm(LOGIC_ID.substring(0, 6));
                uiEntry.setLastTmsp(currentTimestamp);
                
            }
            
            //スタッフマスタ (BM12STAF)
            uiStaff.setStaffId(uiEntryState.getStaffId());
            uiStaff.setJob(uiEntryState.getJob());
            
            //スタッフマスタには新規レコード追加するケースはないため
            //登録情報は必要ない。
            uiStaff.setLastUser(dto.getBirdUserInfo().getUserID());  //修正ユーザ
            uiStaff.setLastPgm(LOGIC_ID.substring(0, 6));            //修正プログラム
            uiStaff.setLastTmsp(currentTimestamp);                   //修正時タイムスタンプ
            
            
            if (uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_RECORD) ||
                    uiEntryState.getStateFlg().equals(MlEntryCommon.LIST_STATES_FLG_ENTRY_NO_RECORD) ||
                        uiEntryState.isInsertFlg()) {
                listEntry.add(uiEntry);
                listResult.add(uiResult);
                listStaff.add(uiStaff);
            }
        }
        dto.setListEntry(listEntry);
        dto.setListResult(listResult);
        dto.setListStaff(listStaff);
    }



    /**
     * オーナー別エントリー状況の更新 (BT20ENON)
     * ※受験しない⇒　新規ではない⇒　データ削除
     * ※受験する　⇒　新規　　　　⇒　データ作成
     * ※受験する　⇒　新規ではない⇒　なにもしない
     */
    private void doEntryOnerStatus(MlEntryDto dto, Timestamp currentTimestamp, String entryFlg) {
        
        UIEntryOnerStatus uiEntryOnerStatus = dto.getUiEntryOnerStatus();
        
        if (entryFlg.equals(MlEntryCommon.ENTRY_FLG_FUSANKA)) {
            if (!uiEntryOnerStatus.isInsertFlg()) {
                getUiEntryOnerStatusMlEntryDao().deleteEntryOner(uiEntryOnerStatus);
            }
        }
        else {
            if (uiEntryOnerStatus.isInsertFlg()) {
                uiEntryOnerStatus.setEntryFlg(entryFlg);
                uiEntryOnerStatus.setFirstUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerStatus.setFirstPgm(LOGIC_ID.substring(0, 6));
                uiEntryOnerStatus.setFirstTmsp(currentTimestamp);
                uiEntryOnerStatus.setLastUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerStatus.setLastPgm(LOGIC_ID.substring(0, 6));
                uiEntryOnerStatus.setLastTmsp(currentTimestamp);
                getUiEntryOnerStatusMlEntryDao().insertEntryOner(uiEntryOnerStatus);
            }
        }
    }
    
    /**
     * エントリーオーナー宛先情報の更新 (BT21ENOJ)
     * ※受験しない⇒　新規ではない⇒　担当者・報告者情報データ削除
     * ※受験する　⇒　新規　　　　⇒　担当者・報告者情報データ作成
     * ※受験する　⇒　新規ではない⇒　担当者・報告者情報データ更新
     */
    private void doEntryOner(MlEntryDto dto, Timestamp currentTimestamp, String entryFlg) {
        
        //担当者・報告者情報
        UIEntryOner uiEntryOnerTanto = dto.getUiEntryOnerTanto();
        UIEntryOner uiEntryOnerKekkaHokoku = dto.getUiEntryOnerKekkaHokoku();
        
        if (entryFlg.equals(MlEntryCommon.ENTRY_FLG_FUSANKA)) {
            if (!uiEntryOnerTanto.isInsertFlg()) {
                getUiEntryOnerMlEntryDao().deleteEntryOner(uiEntryOnerTanto);
            }
            if (!uiEntryOnerKekkaHokoku.isInsertFlg()) {
                getUiEntryOnerMlEntryDao().deleteEntryOner(uiEntryOnerKekkaHokoku);
            }
        }
        else {
            uiEntryOnerTanto.setLastUser(dto.getBirdUserInfo().getUserID());
            uiEntryOnerTanto.setLastPgm(LOGIC_ID.substring(0, 6));
            if (!uiEntryOnerTanto.isInsertFlg()) {
                getUiEntryOnerMlEntryDao().updateEntryOner(uiEntryOnerTanto);
            }
            else {
                uiEntryOnerTanto.setFirstUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerTanto.setFirstPgm(LOGIC_ID.substring(0, 6));
                uiEntryOnerTanto.setFirstTmsp(currentTimestamp);
                getUiEntryOnerMlEntryDao().insertEntryOner(uiEntryOnerTanto);
            }
            
            uiEntryOnerKekkaHokoku.setLastUser(dto.getBirdUserInfo().getUserID());
            uiEntryOnerKekkaHokoku.setLastPgm(LOGIC_ID.substring(0, 6));
            if (!uiEntryOnerKekkaHokoku.isInsertFlg()) {
                getUiEntryOnerMlEntryDao().updateEntryOner(uiEntryOnerKekkaHokoku);
            }
            else {
                uiEntryOnerKekkaHokoku.setFirstUser(dto.getBirdUserInfo().getUserID());
                uiEntryOnerKekkaHokoku.setFirstPgm(LOGIC_ID.substring(0, 6));
                uiEntryOnerKekkaHokoku.setFirstTmsp(currentTimestamp);
                getUiEntryOnerMlEntryDao().insertEntryOner(uiEntryOnerKekkaHokoku);
            }
        }
        
    }
    
    /**
     * マスターライセンスエントリー状況の更新 (BT23MLEJ)
     */
    private void doEntryState(MlEntryDto dto, Timestamp currentTimestamp) {
        
        List listStaff = (ArrayList)dto.getListEntryEnableStaff();
        for(Iterator ite = listStaff.iterator(); ite.hasNext();) {
            UIStaff delStaff = (UIStaff) ite.next();
            //最新のエントリー状況を全クリア
            getUiEntryDao().deleteEntry(dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), delStaff.getStaffId());        
        }
        
        //更新エントリーリスト
        List listEntry = dto.getListEntry();
        
        for(Iterator ite = listEntry.iterator(); ite.hasNext();) {
            UIEntryInfo uiEntry = (UIEntryInfo) ite.next();
            
            try{
                getUiEntryDao().insertEntry(uiEntry);
            }
            catch(SQLRuntimeException se) {
                SQLException e2 = (SQLException) se.getCause();
                if( e2.getSQLState().equals("23505") ) {//重複レコードによりインサート不可(今回分のエントリー状況が登録済み)
                    getUiEntryDao().updateEntry(uiEntry);
                }
            }
        }
    }
    
    
    /**
     * マスターライセンス結果状況履歴の更新 (BT32MLKR)
     * ※空レコードを生成する
     * @param dto
     * @param currentTimestamp
     */
    private void doEntryResult(MlEntryDto dto, Timestamp currentTimestamp) {

        List listStaff = (ArrayList)dto.getListEntryEnableStaff();
        for(Iterator ite = listStaff.iterator(); ite.hasNext();) {
            UIStaff delStaff = (UIStaff) ite.next();
            //最新の履歴を全クリア
            getUiResultStateDao().deleteResultState(dto.getEntryYear(), dto.getEntryKai(), delStaff.getStaffId());        
        }
        
        //更新履歴リスト
        List listResult = dto.getListResult();
        
        for (Iterator ite = listResult.iterator(); ite.hasNext();) {
            UIResultState uiResultState = (UIResultState) ite.next();
            
            try{
                getUiResultStateDao().insertResultState(uiResultState);
            }
            catch(SQLRuntimeException se) {
                SQLException e2 = (SQLException) se.getCause();
                if( e2.getSQLState().equals("23505") ) {//重複レコードによりインサート不可(今回分のエントリー状況が登録済み)
                    getUiResultStateDao().updateResultState(uiResultState);
                }
            }
        }
    }
    
    
    /**
     * 加盟店スタッフマスタ更新 (BM12STAF)
     * @param uiEntryState
     */
    private void updateMstStaff(MlEntryDto dto, Timestamp currentTimestamp) {
        
        //更新スタッフリスト
		List listStaff = dto.getListStaff();
        
        for (Iterator ite = listStaff.iterator(); ite.hasNext();) {
            UIStaff uiStaff = (UIStaff) ite.next();
            getUiStaffMlEntryDao().updateStaff(uiStaff);
        }
    }
    
    
    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    
    
    public UIEntryOnerStatusDao getUiEntryOnerStatusMlEntryDao() {
        return uiEntryOnerStatusMlEntryDao;
    }
    public void setUiEntryOnerStatusMlEntryDao(
            UIEntryOnerStatusDao uiEntryOnerStatusMlEntryDao) {
        this.uiEntryOnerStatusMlEntryDao = uiEntryOnerStatusMlEntryDao;
    }
    public UIEntryOnerDao getUiEntryOnerMlEntryDao() {
        return uiEntryOnerMlEntryDao;
    }
    public void setUiEntryOnerMlEntryDao(
            UIEntryOnerDao uiEntryOwnerDaoDao) {
        this.uiEntryOnerMlEntryDao = uiEntryOwnerDaoDao;
    }
    public UIEntryStateDao getUiEntryStateMlEntryDao() {
        return uiEntryStateMlEntryDao;
    }
    public void setUiEntryStateMlEntryDao(
            UIEntryStateDao uiEntryStateDaoDao) {
        this.uiEntryStateMlEntryDao = uiEntryStateDaoDao;
    }
    public UIEntryDao getUiEntryDao() {
        return uiEntryDao;
    }
    public void setUiEntryDao(UIEntryDao uiEntryDao) {
        this.uiEntryDao = uiEntryDao;
    }
	public UIResultStateDao getUiResultStateDao() {
		return uiResultStateDao;
	}
	public void setUIResultStateDao(UIResultStateDao uiResultStateDao) {
		this.uiResultStateDao = uiResultStateDao;
	}
    public UIStaffDao getUiStaffMlEntryDao() {
        return uiStaffMlEntryDao;
    }
    public void setUiStaffMlEntryDao(UIStaffDao uiStaffMlEntryDao) {
        this.uiStaffMlEntryDao = uiStaffMlEntryDao;
    }


}
