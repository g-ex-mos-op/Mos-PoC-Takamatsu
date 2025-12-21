/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.posreportregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistCommon;
import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.dao.UIMiseChkInfoDao;
import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIMiseChkInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIPosReportMiseInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.CheckInputParamLogic;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;

/**
 * 入力値チェックロジックインターフェース
 * 
 * 作成日:2010/11/18
 * @author xkinu
 *
 */
public class CheckInputParamLogicImpl implements CheckInputParamLogic {
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR007L02";
    /**
     * DAO【店舗チェック情報】
     */
    private UIMiseChkInfoDao posReportRegistUIMiseChkInfoDao;
    /** BIRD日付情報 */
	private BirdDateInfo birdDateInfo;
    /** DTO【POS速報設定情報保持】 */
	private PosReportRegistDto posReportRegistDto;
    /**
     * 事前条件処理
     * 
     * @param birdDateInfo
     * @param posReportRegistDto
     */
    private void validate(final BirdDateInfo birdDateInfo
			, final PosReportRegistDto posReportRegistDto)
    {
        if(birdDateInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("日付情報");
        }
        if(posReportRegistDto == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("POS速報設定情報");
        }
        setBirdDateInfo(birdDateInfo);
        setPosReportRegistDto(posReportRegistDto);
        
    }

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.bizreport.posreportregist.logic.CheckInputParamLogic#execute(jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto)
	 */
	public void execute(BirdDateInfo birdDateInfo, PosReportRegistDto registDto) {
		//０．事前条件処理を行います。
		validate(birdDateInfo, registDto);

		List listPos = registDto.getListPosReport();
        //１．新しく変数.更新データ有無フラグを生成し、falseを設定します。
		//    更新データ有無チェックを行います。
        boolean prosFlg = false;
        //１－１．DTO【POS速報設定情報保持】．全店対象変更用 集信開始日 or 全店対象変更用 集信終了日が
        //       null又は’’（ブランク)でない場合は、全店対象情報の登録情報有りとし、処理１の変数.更新データ有無フラグへtrueを設定します。
        prosFlg = (registDto.isDelete() || !CommonUtil.isNull(registDto.getShuDtStart()) || !CommonUtil.isNull(registDto.getShuDtEnd()));
        //１－２．POS速報店舗リストに更新データか削除データが１件以上含まれているかをチェックする
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite.next();
            if((posinfo.isInsertFlg() && !CommonUtil.isNull(posinfo.getMiseCd()))
            		|| posinfo.getDelFlg()) 
            {
                prosFlg = true;
                break;
            }
        }
        //１－３．処理１－１の全店対象情報の登録情報がなく、更新データ、あるいは削除データが１件もない場合には、
        //        Exceptionにエラーメッセージを設定し、処理を返します。
        if(!prosFlg) {
            throw new GenericMessageException("更新対象のデータはありません。");
        }
        
        //全店対象期間情報チェック処理を行います。
        checkAllInfo();
        //店舗一覧チェック処理を行います。
        checkMiseList();
        return;
	}
	/**
	 * 全店対象期間情報チェック処理
	 */
	private void checkAllInfo() {
		boolean isExistStartDt = !CommonUtil.isNull(getPosReportRegistDto().getShuDtStart());
		boolean isExistEndDt = !CommonUtil.isNull(getPosReportRegistDto().getShuDtEnd());
        //２．DTO【POS速報設定情報保持】．全店対象変更用 集信開始日が空欄の状態で、
        //    DTO【POS速報設定情報保持】．全店対象変更用 集信終了日が入力済みであった場合、
        //    下記の設定をしExceptionを発生させます。
        if( !isExistStartDt && isExistEndDt) {
        	throw new NoInputException("集信開始日","allShuDtStart", 0);
        }
        //３．DTO【POS速報設定情報保持】．全店対象変更用 集信開始日が入力済みの状態で、
        //    DTO【POS速報設定情報保持】．全店対象変更用 集信終了日が空欄であった場合、
        //    下記の設定をしExceptionを発生させます。
        if( isExistStartDt && !isExistEndDt) {
        	throw new NoInputException("集信終了日","allShuDtEnd", 0);
        }
        //４．下記の条件を満たしている場合、全店対象用の処理ステータスの設定を下記の処理で行います。
        //    条件1：DTO【POS速報設定情報保持】．全店対象変更用 集信開始日　！＝ null
        //    条件2：DTO【POS速報設定情報保持】．全店対象変更用 集信終了日　！＝ null
        if(isExistStartDt && isExistEndDt) {
        	try {
        	//Ⅰ．メソッド．集信日妥当性チェック処理を実行します。
        	checkDate(getPosReportRegistDto().getShuDtStart()
        			, getPosReportRegistDto().getShuDtEnd()
        			, 0);
        	}
        	catch (ApplicationException appEx) {
        		if( appEx.getHtmlElementName().indexOf("Sta") >= 0) {
        			appEx.setHtmlElementName("allShuDtStart");
        		}
        		if( appEx.getHtmlElementName().indexOf("End") >= 0) {
        			appEx.setHtmlElementName("allShuDtEnd");
        		}
        		throw appEx;
        	}
        	//Ⅱ．DTO【POS速報設定情報保持】．削除フラグへfalseを設定します。
        	getPosReportRegistDto().setDelete(false);
        }
	}
	/**
	 * 店舗一覧チェック処理
	 */
	private void checkMiseList() 
	{
		String sysDate = getBirdDateInfo().getSysDate();
		List listPos = getPosReportRegistDto().getListPosReport();
        //--------------------------
        // 店舗リストを生成する
        //--------------------------
        List listMise = new ArrayList(0);
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite.next();
            if(posinfo.isInsertFlg()) {
                listMise.add(posinfo.getMiseCd());
            }
        }
        //-------------------------
        //店コードの半角チェック
        //2007/02/26 add inazawa
        //-------------------------
        int iCnt = 0;
        CodeVerifier codeVerifier = new CodeVerifier();
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo entity = (UIPosReportMiseInfo) ite.next();
            
            // 店舗コード未入力はチェック対象外
            if(!PosReportRegistCommon.isNull(entity.getMiseCd())) {
                
                if(entity.getMiseCd().trim() != null && 
                        !entity.getMiseCd().trim().equals("") && 
                        !codeVerifier.validate(entity.getMiseCd().trim())){
                    throw new InvalidInputException("店コード","miseCd",iCnt);
                }
            }
            iCnt++;
        }
        
        //-------------------------------
        // 店舗チェックリストを生成する
        //-------------------------------
        List listChkMise = new ArrayList(0);
        if(listMise.size() > 0) {
            listChkMise = getPosReportRegistUIMiseChkInfoDao().getMiseChkInfo(
            		listMise, getPosReportRegistDto().getPosCompanyCd());
        }
        
        
        //------------------------------------------
        // POS新規登録リスト新規更新データチェック
        //------------------------------------------
        int listIndex = 0;
        List chkDblList = new ArrayList(0);
        
        List errMiseList  = new ArrayList(0);
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo entity = (UIPosReportMiseInfo) ite.next();
            
            //--------------------------------------
            //　店舗コード未入力はチェック対象外
            //--------------------------------------
            if(!PosReportRegistCommon.isNull(entity.getMiseCd())) {
            
                //-----------------------------
                // ステータス異常店舗の複数登録チェック
                // 2007/02/26 add inazawa
                //-----------------------------
                if(!entity.isInsertFlg() && (PosReportRegistConstants.HAIS_RSLT_ST_NOFILE.equals(entity.getHaisRsltSt())
                        || PosReportRegistConstants.HAIS_RSLT_ST_TUSHINFURYO.equals(entity.getHaisRsltSt()))){
                    errMiseList.add(entity.getMiseCd());
                }
                
                // 新規更新データチェック
                if(entity.isInsertFlg()) {
                    
                    //----------------------
                    // 入力必須チェック
                    //----------------------
                    if(!PosReportRegistCommon.isNull(entity.getMiseCd())) {
                        if (PosReportRegistCommon.isNull(entity.getShuDtSta())) {
	                        throw new GenericMessageException("集信開始日は必須です。", "shuDtSta", listIndex);
	                    }
	                    if(PosReportRegistCommon.isNull(entity.getShuDtEnd())) {
	                        throw new GenericMessageException("集信終了日は必須です。", "shuDtEnd", listIndex);
	                    }
                    }
                    
                    
                    //---------------------
                    // 店舗コードチェック
                    //---------------------
                    boolean hitFlg = false;
                    String closeDt = "";
                    String newTelNo = "";
                    for (Iterator miseIte = listChkMise.iterator(); miseIte.hasNext();) {
                        UIMiseChkInfo miseEntity = (UIMiseChkInfo) miseIte.next();
                        if(miseEntity.getMiseCd().equals(entity.getMiseCd())) {
                            hitFlg = true;
                            closeDt = miseEntity.getCloseDt();
                            newTelNo = miseEntity.getP4TelNo();
                        }
                    }
                    if(!hitFlg) {
                        throw new GenericMessageException("店コードが存在しません。", "miseCd", listIndex);
                    }
                    if(sysDate.compareTo(closeDt) >= 0) {
                        throw new GenericMessageException("クローズ店は指定できません。", "miseCd", listIndex);
                    }
                    if(PosReportRegistCommon.isNull(newTelNo)) {
                        throw new GenericMessageException("ネットワーク番号未設定店舗は指定できません。", "miseCd", listIndex);
                    }
                    
                    
                    //---------------------
                    // 日付けチェック
                    //---------------------
                    String shuDtSta = entity.getShuDtSta();
                    String shuDtEnd = entity.getShuDtEnd();
                    checkDate(shuDtSta, shuDtEnd, listIndex);
                    
                    
                    //-----------------------------
                    // 配信結果ステータスチェック
                    //-----------------------------
                    String haisRslSt = entity.getHaisRsltSt();
                    if(shuDtSta.compareTo(sysDate) >= 0 &&
                    		sysDate.compareTo(shuDtEnd) >= 0 &&
                                (PosReportRegistConstants.HAIS_RSLT_ST_NOFILE.equals(haisRslSt) || 
                                		PosReportRegistConstants.HAIS_RSLT_ST_TUSHINFURYO.equals(haisRslSt))) 
                    {
                        throw new GenericMessageException("現在設定中でステータスが異常である店舗は指定できません。", "shuDtSta", listIndex);
                    }
                    
                    //-----------------------------
                    // ステータス異常店舗の複数登録チェック
                    // 2007/02/26 add inazawa
                    //-----------------------------
                    for (int i = 0;errMiseList.size()>i;i++) {
                        String errMiseCd =  (String)errMiseList.get(i);
                        if(errMiseCd.equals(entity.getMiseCd())){
                            throw new GenericMessageException("現在設定中でステータスが異常である店舗は指定できません。", "miseCd", listIndex);
                        }
                    }
                    //---------------------------
                    // 店舗コード重複チェック
                    //---------------------------
                    String miseCd = entity.getMiseCd();
                    
                    for (int i=0; i<chkDblList.size(); i++) {
                        UIPosReportMiseInfo chkDblInfo = (UIPosReportMiseInfo) chkDblList.get(i);
                        if(chkDblInfo.getMiseCd().equals(miseCd)) {
                        	String checkShuDtSta = chkDblInfo.getShuDtSta();
                        	String checkShuDtEnd = chkDblInfo.getShuDtEnd();
                            if(shuDtSta.compareTo(checkShuDtSta) == 0 || shuDtSta.compareTo(checkShuDtEnd) == 0
                            		|| shuDtEnd.compareTo(checkShuDtSta) == 0 || shuDtEnd.compareTo(checkShuDtEnd) == 0) {
                                throw new GenericMessageException("同一店舗で期間が重複しています。", "miseCd", listIndex);
                            }
                            else if(shuDtSta.compareTo(checkShuDtSta) < 0) {
                                if(shuDtEnd.compareTo(checkShuDtSta) > 0) {
                                    throw new GenericMessageException("同一店舗で期間が重複しています。", "miseCd", listIndex);
                                }
                            }
                            else if(shuDtSta.compareTo(checkShuDtSta) > 0) {
                                if(chkDblInfo.getShuDtEnd().compareTo(shuDtSta) > 0) {
                                    throw new GenericMessageException("同一店舗で期間が重複しています。", "miseCd", listIndex);
                                }
                            }
                        }
                    }
                    chkDblList.add(entity);
                }
                
            }
            listIndex++;
            
        }
	}
	/**
	 * 日付妥当性チェック処理
	 * 
	 * @param shuDtSta 集信開始日
	 * @param shuDtEnd 集信終了日
	 */
	private void checkDate(String shuDtSta, String shuDtEnd, int indexNo) {
		String sysDate = getBirdDateInfo().getSysDate();
        // 集信開始日フォーマットチェック
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YMD);
        if (!PosReportRegistCommon.isNull(shuDtSta) &&
                !dateVerifier.validate(shuDtSta.trim())) {
            throw new GenericMessageException(
            		"集信開始日が正しくありません。","shuDtSta", indexNo);
        }
        // 集信終了日フォーマットチェック
        if (!PosReportRegistCommon.isNull(shuDtEnd) &&
                !dateVerifier.validate(shuDtEnd.trim())) {
            throw new GenericMessageException(
            		"集信終了日が正しくありません。","shuDtEnd", indexNo);
        }
        
        String sysdate60 = "";
        try {
            sysdate60 = DateManager.getNextDate(sysDate, 60);
        } catch (Exception e) {
        }
        // 集信開始日の期間チェック
        if(sysDate.compareTo(shuDtSta) >= 0 || shuDtSta.compareTo(sysdate60) >= 0) {
            throw new GenericMessageException(
//            		"集信開始日は翌日以降60日後未満でなければなりません。"
            		"集信開始日は「操作日＋1日」～「操作日＋59日」間の日付でなければなりません。"
            		, "shuDtSta", indexNo);
        }
        
        String sysdate73 = "";
        try {
            sysdate73 = DateManager.getNextDate(sysDate, 73);
        } catch (Exception e) {
        }
        // 集信終了日の期間チェック
        if(sysDate.compareTo(shuDtEnd) >= 0 || shuDtEnd.compareTo(sysdate73) >= 0) {
            throw new GenericMessageException(
//            		"集信終了日は翌日以降73日後未満でなければなりません。"
            		"集信終了日は「操作日＋1日」～「操作日＋72日」間の日付でなければなりません。"
            		, "shuDtEnd", indexNo);
        }
        // 集信開始日と集信終了日の整合性チェック
        if(shuDtSta.compareTo(shuDtEnd) > 0) {
            throw new GenericMessageException(
            		"集信開始日<=集信終了日でなければなりません。"
            		, "shuDtSta", indexNo);
        }

	}
    /**
     * POS速報設定DTOを取得します。
     * @return POS速報設定DTO
     */
    private PosReportRegistDto getPosReportRegistDto() {
        return posReportRegistDto;
    }
    /**
     * POS速報設定DTOを設定します。
     * @param POS速報設定DTO
     */
    private void setPosReportRegistDto(PosReportRegistDto posReportRegistDto) {
        this.posReportRegistDto = posReportRegistDto;
    }
    /**
     * BIRD日付情報取得処理
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
        return this.birdDateInfo;
    }
    /**
     * BIRD日付情報設定処理
     * @param birdDateInfo
     */
    private void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
	public UIMiseChkInfoDao getPosReportRegistUIMiseChkInfoDao() {
		return posReportRegistUIMiseChkInfoDao;
	}

	public void setPosReportRegistUIMiseChkInfoDao(
			UIMiseChkInfoDao posReportRegistUIMiseChkInfoDao) {
		this.posReportRegistUIMiseChkInfoDao = posReportRegistUIMiseChkInfoDao;
	}

}
