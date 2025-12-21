package jp.co.isid.mos.bird.bizsupport.energyamount.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.energyamount.common.EnergyAmountConst;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UIMiseInfoDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.MstUserOnerInfoDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UIEnergyAmountDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UINoInputMeterDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UIOutOfSubjectAmtDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UISuiiAvgDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UISuiiDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dto.SuiiRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UIMiseInfo;
import jp.co.isid.mos.bird.bizsupport.energyamount.entity.MstUserOnerInfo;
import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UIOutOfSubjectAmt;
import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UISuiiDetaile;
import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UISuiiUnit;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingConfigException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

/**
 * エネルギー使用量推移表CSVロジック
 * 
 * 作成日:2010/05/19
 * @author xkinu
 *
 */
public class SuiiCsvLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBS032L03";
    // Formatter
    private DateFormatter dateFmtYMSKj = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);

    /*DAO*/
    private UINoInputMeterDao energyamountUINoInputMeterDao;
    private UIEnergyAmountDao energyamountUIEnergyAmountDao;
    private MstUserOnerInfoDao energyamountMstUserOnerInfoDao;
    private UIMiseInfoDao energyamountUIMiseInfoDao;
    /** DAO【全国平均原単位】*/
    private UISuiiAvgDao energyamountUISuiiAvgDao;
    /** DAO【メータ入力店舗別管理項目設定(対象外)】*/
    private UIOutOfSubjectAmtDao energyamountUIOutOfSubjectAmtDao;
    /** DAO【メータ入力値推移情報】*/
    private UISuiiDao energyamountUISuiiDao;
    private MstSibuDao mstSibuDao;
    /*Formatter*/
    /** 数値型：売上・店舗数用 */
    NumericFormatter numFmtdgt0 = new NumericFormatter(false, 0, true);
    /** 数値型：使用量・前年対比用 */
    NumericFormatter numFmtdgt2 = new NumericFormatter(false, 2);
    /** 数値型：原単位用 */
    NumericFormatter numFmtdgt3 = new NumericFormatter(false, 3);
	private static final BigDecimal dec0 = new BigDecimal("0");
    /**
     * 事前確認処理
     */
    public void validate(CsvOutputDto csvOutputDto) {
    	SuiiRequestDto requestDto = (SuiiRequestDto) csvOutputDto;
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("自画面リクエストDTO");
        }
        if ((csvOutputDto instanceof SuiiRequestDto) == false) {
            throw new MissingConfigException("推移表CSVダウンロード対象のリクエストDTO");
        }
        if ( null==requestDto.getBirdUserInfo() ) {
        	throw new MissingConfigException("ユーザー情報");
        }
        if ( null==requestDto.getBirdDateInfo() ) {
        	throw new MissingConfigException("日付情報");
        }
        if ( CommonUtil.isNull(requestDto.getCompanyCd()) ) {
        	throw new MissingConfigException("会社コード");
        }
        if ( CommonUtil.isNull(requestDto.getNendo()) ) {
        	throw new NotNullException("対象年月");
        }
        if ( CommonUtil.isNull(requestDto.getMeterKbn()) ) {
        	throw new NotNullException("メーター区分");
        }
        if(TaishoJoken.CODE_MISE.equals(requestDto.getTaishoJoken())) {
        	String miseCd = requestDto.getTaishoCd();
            //店舗コードの必須チェック
        	if(CommonUtil.isNull(requestDto.getTaishoCd())) {
        		throw new NotNullException("店舗コード", "suiiMiseCd", 0);
        	}
            //店舗コードの入力値の整合性チェック
			boolean isAlphabet = true;
            //対象条件が全社以外の場合表示対象コードチェック
            CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
            //コードフォーマットチェック
            if(!codeVeri.validate(miseCd) || miseCd.length() > 5) {
                throw new InvalidInputException("表示対象", "miseCd", 0);
            }
			CodeFormatter cdf = new CodeFormatter(5, "00000");
            cdf.setFormatPattern("00000");
            requestDto.setTaishoCd(cdf.format(miseCd, true));
        }

    }
    /**
     * CSVファイル名称取得処理
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        SuiiRequestDto requestDto = (SuiiRequestDto) csvOutputDto;
        String userTypeCd = requestDto.getBirdUserInfo().getMstUser().getUserTypeCd();
        if(UserType.isHonbu(userTypeCd)) {
       		return "ENERGYSUII_"+requestDto.getTaishoJoken()+requestDto.getNendo()+".csv";
        }
        return "ENERGYSUII"+requestDto.getNendo()+".csv";
    }
    /**
     * CSVデータ作成取得処理
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
    	SuiiRequestDto requestDto = (SuiiRequestDto) csvOutputDto;

		//１．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //２．条件項目ヘッダ作成
		addHeader(listCSV, requestDto);
        //空白行
        listCSV.add(new ArrayList(0));
        List listOutputMonths = new ArrayList(12);
        try {
	        for(int i=0; i<12; i++) {
	        	listOutputMonths.add(DateManager.getNextMonth(requestDto.getNendoFromYm(), i));
	        }
        }
        catch (Exception ex) {
        	throw new FtlSystemException("対象年度の年月の取得に失敗しました。");
        }
        //３. 全国平均原単位作成
		addNationalUnitAvg(listCSV, requestDto, listOutputMonths);
        //空白行
        listCSV.add(new ArrayList(0));
        
        //５．明細部ヘッダー作成
		addListHeader(listCSV, requestDto, listOutputMonths);
        //６．明細部データ作成
		addList(listCSV, requestDto, listOutputMonths);
		//７．List[[CSV出力用リスト]]をリターンします。
		return listCSV;
    }
	/**
	 * ヘッダ部作成設定処理
	 * 
	 * @param listCSV
	 * @param requestDto
	 * @param tanpoCnt
     * @add xmatsuo
     * 更新日：2014/11/17 仕様追加 対象条件に対象条件「支部」が選択されていた場合で選択支部が「すべて」の場合
     * 　　　　支部名称に「全店」を格納する。
	 */
	private void addHeader(List listCSV, SuiiRequestDto requestDto) {
		String userTypeCd = requestDto.getBirdUserInfo().getMstUser().getUserTypeCd();
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //キャンペーン情報
        listHeader1Row.add("対象条件：");
        if(UserType.isHonbu(userTypeCd)) {
        	if(TaishoJoken.CODE_MISE.equals(requestDto.getTaishoJoken())) {
            	List listMiseInfo = getEnergyamountUIMiseInfoDao().select(
            			requestDto.getCompanyCd(), requestDto.getTaishoCd());
            	if(listMiseInfo == null || listMiseInfo.size()==0) {
        			//該当データなし
        			throw new NoResultException("");
            	}
            	UIMiseInfo miseInfo = (UIMiseInfo)listMiseInfo.get(0);
            	listHeader1Row.add(miseInfo.getMiseCd()+" "+miseInfo.getMiseNameKj().trim());//店舗名称を設定します。
        	}
        	else if(TaishoJoken.CODE_SIBU.equals(requestDto.getTaishoJoken())) {
                //add start 松尾
                //選択支部が「すべて」の場合、支部名称に「全店」を設定します。
                if(requestDto.getTaishoCd().equals(EnergyAmountConst.TAISHO_JOKEN_ALL)){
               
                    listHeader1Row.add("全店");
                //選択支部が「すべて」以外の場合、支部名称を設定します。   
                } else {
         
                    MstSibu mstSibu = (MstSibu) getMstSibuDao().getSibu(requestDto.getCompanyCd(), requestDto.getTaishoCd()).get(0);
                    if(mstSibu == null) {
            			//該当データなし
            			throw new NoResultException("");
                    }

                listHeader1Row.add(mstSibu.getSibuName().trim());
        	   
                }
                //add end
            }   
        else if(UserType.isOner(userTypeCd)) {
        	//DAO【ユーザーオーナー情報】取得処理
        	List listOnerInfo = getEnergyamountMstUserOnerInfoDao().select(
        			requestDto.getBirdUserInfo().getUserID(), requestDto.getCompanyCd());
        	if(listOnerInfo == null || listOnerInfo.size()==0) {
        		throw new NotExistException("ユーザーオーナー情報");
        	}
        	MstUserOnerInfo onerInfo = (MstUserOnerInfo)listOnerInfo.get(0);
        	listHeader1Row.add(onerInfo.getOnerCd()+" "+onerInfo.getOnerNameKj().trim());//オーナー名称を設定します。
        }
        else if(UserType.isTenpo(userTypeCd)) {
        	//DAO【ユーザー店舗情報】取得処理
        	List listMiseInfo = getEnergyamountUIMiseInfoDao().selectByUserMise(
        			requestDto.getBirdUserInfo().getUserID(), requestDto.getCompanyCd());
        	if(listMiseInfo == null || listMiseInfo.size()==0) {
        		throw new NotExistException("ユーザー店舗情報");
        	}
        	UIMiseInfo miseInfo = (UIMiseInfo)listMiseInfo.get(0);
        	listHeader1Row.add(miseInfo.getMiseCd()+" "+miseInfo.getMiseNameKj().trim());//店舗名称を設定します。
        }
        listCSV.add(listHeader1Row);
        
        /** 2行目作成 */
        List listHeader2Row = new ArrayList();
        //検索対象キャンペーン期間情報
        listHeader2Row.add("対象期間：");
       	listHeader2Row.add(requestDto.getNendo()+"年度");
        listCSV.add(listHeader2Row);
        
        /** 3行目作成 */
        List listHeader3Row = new ArrayList();
        //対象店舗数情報
        listHeader3Row.add("メーター区分：");
        listHeader3Row.add(requestDto.getMeterKbnName());
        listCSV.add(listHeader3Row);
	}}
	/**
	 * 全国平均原単位作成処理
	 * 
	 * システム日付の年月以降は年月以外は全て空(ブランク)を設定します。
	 * 
	 * @param listCSV
	 * @param requestDto
	 * @param listOutputMonths
	 * @param listAvg
	 */
	private void addNationalUnitAvg(
			List listCSV, SuiiRequestDto requestDto, List listOutputMonths)
	{
		//BIRDユーザー情報
		BirdUserInfo birdUserInfo = requestDto.getBirdUserInfo();
		//ユーザータイプコードを取得
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		//DAO【平均原単位】検索結果取得処理
		List listAvg = getEnergyamountUISuiiAvgDao().select(
				  requestDto.getBirdDateInfo().getSysDate().substring(0,6)
				, requestDto.getCompanyCd()
				, requestDto.getNendoFromYm(), requestDto.getNendoToYm()
				, requestDto.getNendoLastFromYm(), requestDto.getNendoLastToYm()
				, requestDto.getMeterKbn()
				, requestDto.getTaishoJoken(), requestDto.getTaishoCd()
				, userTypeCd, birdUserInfo.getUserID(), UserType.isHonbu(userTypeCd)?birdUserInfo.isLimit():false);
		if(listAvg.size()==0) {
			//該当データなし
			throw new NoResultException("");
		}
		List listAvgHeaderRow = new ArrayList(0);
		listAvgHeaderRow.add("");
		for(int m=0; m<listOutputMonths.size(); m++) {
			//年月を設定します。
			listAvgHeaderRow.add(dateFmtYMSKj.format(listOutputMonths.get(m), true));			
		}
		//全国平均ヘッダー部を設定します。
		listCSV.add(listAvgHeaderRow);
		String sysMonth = requestDto.getBirdDateInfo().getSysDate().substring(0,6);
		String[] rowItem = new String[] {
			 "当年対象店舗数","前年対象店舗数"
			,"当年電力全国平均原単位(kWh/百万円）","前年電力全国平均原単位(kWh/百万円）"
			,"当年ガス全国平均原単位（m3/百万円）","前年ガス全国平均原単位（m3/百万円）"
			,"当年水道全国平均原単位（m3/百万円）","前年水道全国平均原単位（m3/百万円）"
			};
		for(int r=0; r<rowItem.length; r++) {
        	int cntMonth = 0;
	        int dataIndex = 0;
	        boolean isSetData = false;
	        String yearType= rowItem[r].substring(0,1);
			/** 行作成 */
	        List listRow = new ArrayList();
	        listRow.add(rowItem[r]);
    		//12か月分処理します。
        	while(cntMonth<listOutputMonths.size()) {
        		String atMonth = (String)listOutputMonths.get(cntMonth);
        		if (dataIndex<listAvg.size()) {
		        	UISuiiUnit eUnitAvg = (UISuiiUnit)listAvg.get(dataIndex);
	        		if(atMonth.equals(eUnitAvg.getMeterYm())) {
	        			dataIndex++;
	        			if ( (atMonth.compareTo(sysMonth)<0 || "前".equals(yearType)) 
	        					&& (("当".equals(yearType) && eUnitAvg.getTenpoCnt()>0)
	        					      || ("前".equals(yearType) && eUnitAvg.getLastTenpoCnt()>0)) 
	        				)
	        			{
		        			//システム日付の年月以前で、データた存在する場合
				        	switch (r) {
							case 0:
								//当年対象店舗
					        	listRow.add(getNumFmtdgt0(new BigDecimal(eUnitAvg.getTenpoCnt()), false));
								break;
							case 1:
								//前年対象店舗
					        	listRow.add(getNumFmtdgt0(new BigDecimal(eUnitAvg.getLastTenpoCnt()), false));			
								break;
							case 2:
								//当年電力全国平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getEpGnt(), false));
								break;
							case 3:
								//前年電力全国平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getLastEpGnt(), false));
								break;
							case 4:
								//当年ガス全国平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getGasGnt(), false));
								break;
							case 5:
								//前年ガス全国平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getLastGasGnt(), false));
								break;
							case 6:
								//当年水道全国平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getWaterGnt(), false));
								break;
							case 7:
								//前年水道全国平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getLastWaterGnt(), false));
								break;
			
							default:
								break;
							}
		        		isSetData=true;
	        			}// end of if ( (atMonth.compareTo(sysMonth)<0 || "前".equals(yearType))
	        		}//end of if(atMonth.equals(eUnitAvg.getMeterYm()))
	        	}// end of if (dataIndex<listAvg.size())
	        		
		        if (isSetData==false) {
		        	listRow.add("");
		        }
	        	cntMonth++;
		        isSetData = false;
        	}//end of while(cntMonth<listOutputMonths.length)
	        //1行分のデータを格納します。
	        listCSV.add(listRow);
		}// end of for(int r=0; r<rowItem.length; r++)
     		
		//４．ログインユーザーがオーナーの場合、保有店平均原単位を作成します。
		if(UserType.isOner(userTypeCd)) {
	        //空白行
	        listCSV.add(new ArrayList(0));
			addOnerUnitAvg(listCSV, requestDto, listOutputMonths);
		}
	}
	/**
	 * 保有店舗平均原単位作成処理
	 * 
	 * システム日付の年月以降は年月以外は全て空(ブランク)を設定します。
	 * 
	 * @param listCSV
	 * @param requestDto
	 * @param listOutputMonths
	 * @param listAvg
	 */
	private void addOnerUnitAvg(
			List listCSV, SuiiRequestDto requestDto, List listOutputMonths)
	{
		//BIRDユーザー情報
		BirdUserInfo birdUserInfo = requestDto.getBirdUserInfo();
		//ユーザータイプコードを取得
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		//DAO【平均原単位】検索結果取得処理
		List listAvg = getEnergyamountUISuiiAvgDao().selectByOner(
				  requestDto.getBirdDateInfo().getSysDate().substring(0,6)
				, requestDto.getCompanyCd()
				, requestDto.getNendoFromYm(), requestDto.getNendoToYm()
				, requestDto.getNendoLastFromYm(), requestDto.getNendoLastToYm()
				, requestDto.getMeterKbn()
				, requestDto.getTaishoJoken(), requestDto.getTaishoCd()
				, userTypeCd, birdUserInfo.getUserID(), UserType.isHonbu(userTypeCd)?birdUserInfo.isLimit():false);
		if(listAvg.size()==0) {
			//該当データなし
			throw new NoResultException("");
		}
		String sysMonth = requestDto.getBirdDateInfo().getSysDate().substring(0,6);
		String[] rowItem = new String[] {
			"保有店電力原単位"
			,"保有店ガス原単位"
			,"保有店水道原単位"
			};
		for(int r=0; r<rowItem.length; r++) {
        	int cntMonth = 0;
	        int dataIndex = 0;
	        boolean isSetData = false;
			/** 行作成 */
	        List listRow = new ArrayList();
	        listRow.add(rowItem[r]);
    		//12か月分処理します。
        	while(cntMonth<listOutputMonths.size()) {
        		String atMonth = (String)listOutputMonths.get(cntMonth);
        		if (dataIndex<listAvg.size()) {
		        	UISuiiUnit eUnitAvg = (UISuiiUnit)listAvg.get(dataIndex);
	        		if(atMonth.equals(eUnitAvg.getMeterYm())) {
	        			dataIndex++;
	        			if ( atMonth.compareTo(sysMonth)<0 && eUnitAvg.getTenpoCnt()>0) {
		        			//システム日付の年月以前の場合
				        	switch (r) {
							case 0:
								//保有店電力平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getEpGnt(), false));
								break;
							case 1:
								//保有店ガス平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getGasGnt(), false));
								break;
							case 2:
								//保有店水道平均原単位
					        	listRow.add(getNumFmtdgt3(eUnitAvg.getWaterGnt(), false));
								break;
							default:
								break;
							}
		        		isSetData=true;
	        			}// end of if ( (atMonth.compareTo(sysMonth)<0 || "前".equals(yearType))
	        		}//end of if(atMonth.equals(eUnitAvg.getMeterYm()))
	        	}// end of if (dataIndex<listAvg.size())
	        		
		        if (isSetData==false) {
		        	listRow.add("");
		        }
	        	cntMonth++;
		        isSetData = false;
        	}//end of while(cntMonth<listOutputMonths.length)
	        //1行分のデータを格納します。
	        listCSV.add(listRow);
		}// end of for(int r=0; r<rowItem.length; r++)
     		
	}

	/**
	 * データ項目名称格納処理
	 * 
	 * @param listCSV
	 * @param requestDto
	 */
	private void addListHeader(List listCSV, SuiiRequestDto requestDto, List listOutputMonths) {
		//１．ヘッダー部1行目のリストをインスタンス化します。 */
		List listHeader1 = new ArrayList(0);
		listHeader1.add("");
		listHeader1.add("");
		listHeader1.add("");
		listHeader1.add("");
		for(int m=0; m<listOutputMonths.size(); m++) {
			//年月をyyyy/MM月の形式で設定します。
			listHeader1.add(dateFmtYMSKj.format((String)listOutputMonths.get(m), true));
			listHeader1.add("");
		}
		listHeader1.add("累計");
		listCSV.add(listHeader1);
		
		//２．ヘッダー部1行目のリストをインスタンス化します。 */
		List listHeader2 = new ArrayList(0);
		listHeader2.add("店コード");
		listHeader2.add("店名称");
		listHeader2.add("種別");
		listHeader2.add("※対象外");
		for(int m=0; m<listOutputMonths.size(); m++) {
			listHeader2.add("実績");
			listHeader2.add("区分");
		}
		listHeader2.add("");
		listCSV.add(listHeader2);
		listOutputMonths.add("RUIKEI");
	}
    
	/**
	 * 全国平均原単位作成処理
	 * 
	 * システム日付の年月以降は年月以外は全て空(ブランク)を設定します。
	 * @param listCSV
	 * @param requestDto
	 */
	private void addList(List listCSV, SuiiRequestDto requestDto, List listOutputMonths) {
		//BIRDユーザー情報
		BirdUserInfo birdUserInfo = requestDto.getBirdUserInfo();
		//ユーザータイプコードを取得
		String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
		String sysMonth = requestDto.getBirdDateInfo().getSysDate().substring(0,6);
		//DAO【メータ入力値推移情報】検索結果取得処理
		List listData = getEnergyamountUISuiiDao().select(
				  requestDto.getBirdDateInfo().getSysDate().substring(0,6)
				, requestDto.getCompanyCd()
				, requestDto.getNendoFromYm(), requestDto.getNendoToYm()
				, requestDto.getNendoLastFromYm(), requestDto.getNendoLastToYm(), requestDto.getMeterKbn()
				, requestDto.getTaishoJoken(), requestDto.getTaishoCd()
				, userTypeCd, birdUserInfo.getUserID(), UserType.isHonbu(userTypeCd)?birdUserInfo.isLimit():false);
		if(listData.size()==0) {
			//該当データなし
			throw new NoResultException("");
		}
		//DAO【メータ入力店舗別管理項目設定(対象外)】検索結果取得処理
		List listOutOfSubjectData = getEnergyamountUIOutOfSubjectAmtDao().select(
				  requestDto.getCompanyCd(), requestDto.getMeterKbn()
				, requestDto.getTaishoJoken(), requestDto.getTaishoCd()
				, userTypeCd, birdUserInfo.getUserID(), UserType.isHonbu(userTypeCd)?birdUserInfo.isLimit():false);
		Map mapOutData = new HashMap();
		for(int o=0; o<listOutOfSubjectData.size(); o++) {
			UIOutOfSubjectAmt entity = (UIOutOfSubjectAmt)listOutOfSubjectData.get(o);
			mapOutData.put(entity.getMiseCd(), entity);
		}
		String[] rowItem = new String[] {"売上"
			,"当年電力使用量","前年電力使用量"
			,"当年電力売上原単位","前年電力売上原単位"
			,"売上原単位前年対比"
			,"当年ガス使用量","前年ガス使用量"
			,"当年ガス売上原単位","前年ガス売上原単位"
			,"売上原単位前年対比"
			,"当年水道使用量","前年水道使用量"
			,"当年水道売上原単位","前年水道売上原単位"
			,"売上原単位前年対比"
			};

        boolean isSetData = false;

        for(int i=0; i<listData.size(); i++) {
        	int dataIndex = i;
        	UISuiiDetaile entity = (UISuiiDetaile)listData.get(i);
			String miseCd = entity.getTaishoCd();
			for(int r=0; r<rowItem.length; r++) {
				i=dataIndex;
				int cntMonth = 0;
				/** 行作成 */
		        List listRow = new ArrayList();
		        if(r==0) {
		        	listRow.add(miseCd);//店舗コード					
		        	listRow.add(entity.getTaishoName().trim());//店舗名称
			        listRow.add(rowItem[r]);
			        listRow.add("");
		        }
		        else {
			        listRow.add("");
			        listRow.add("");
			        listRow.add(rowItem[r]);
			        //対象外を設定します。
			        UIOutOfSubjectAmt eOutOfSubject = (UIOutOfSubjectAmt)mapOutData.get(entity.getTaishoCd());
			        if(eOutOfSubject==null) {
			        	listRow.add("");
			        }
			        else {
			        	
			        	if(r>=1 && r<=5){
							//当年電力使用量・前年電力使用量
							//当年電力売上原単位・前年電力売上原単位
							//前年比(電力)
					       	listRow.add(getOutOfSubjectLabel(eOutOfSubject.getEpFlg()));
			        	}
			        	else if(r>=6 && r<=10){
							//当年ガス使用量・前年ガス使用量
							//当年ガス売上原単位・前年ガス売上原単位
							//前年比(ガス)
					       	listRow.add(getOutOfSubjectLabel(eOutOfSubject.getGasFlg()));
			        	}
			        	else if(r>=11 && r<=15){
							//当年水道使用量・前年水道使用量
							//当年水道売上原単位。前年水道売上原単位
							//前年比(水道)
					       	listRow.add(getOutOfSubjectLabel(eOutOfSubject.getWaterFlg()));
						}
			        }
		        }
		        while(cntMonth<listOutputMonths.size()) {
		        	String atMonth = (String)listOutputMonths.get(cntMonth);
		        	//12か月分処理します。
	        		if (i<listData.size()) {
	        			entity = (UISuiiDetaile)listData.get(i);
	        			boolean isRuikei = "RUIKEI".equals(atMonth);
	        			if(miseCd.equals(entity.getTaishoCd()) && atMonth.equals(entity.getMeterYm())) {
				        	if((atMonth.compareTo(sysMonth)<0
				        			|| (r==2 || r==4 || r==5 || r==7 || r==9 || r==10 || r==12 || r==14 || r==15))
				        			   || isRuikei
				        	   ) 
				        	{
				        		//システム日付の年月以前で、データた存在する場合
					        	switch (r) {
									case 0:
										//売上
										listRow.add(getNumFmtdgt0(entity.getUriage(), isRuikei));
										break;
									case 1:
										//当年電力使用量
										listRow.add(getNumFmtdgt2(entity.getEpAmt(), isRuikei));
										break;
									case 2:
										//前年電力使用量
							        	listRow.add(getNumFmtdgt2(entity.getLastEpAmt(), isRuikei));
										break;
									case 3:
										//当年電力売上原単位
										if(isRuikei) {
											entity.setEpGnt(getGnt(entity.getUriage(), entity.getEpAmt()));
										}
										listRow.add(getNumFmtdgt3(entity.getEpGnt(), isRuikei));
										break;
									case 4:
										//前年電力売上原単位
										if(isRuikei) {
											entity.setLastEpGnt(getGnt(entity.getLastUriage(), entity.getLastEpAmt()));
										}
							        	listRow.add(getNumFmtdgt3(entity.getLastEpGnt(), isRuikei));
										break;
									case 5:
										//前年対比(電力)
							        	listRow.add(getZennenTaiHi(sysMonth, entity.getMeterYm()
								        		, entity.getEpGnt(), entity.getLastEpGnt(), entity.getEpZennenHi(), isRuikei));
										break;
									case 6:
										//当年ガス使用量
							        	listRow.add(getNumFmtdgt2(entity.getGasAmt(), isRuikei));
										break;
									case 7:
										//前年ガス使用量
							        	listRow.add(getNumFmtdgt2(entity.getLastGasAmt(), isRuikei));
										break;
									case 8:
										//当年ガス売上原単位
										if(isRuikei) {
											entity.setGasGnt(getGnt(entity.getUriage(), entity.getGasAmt()));
										}
							        	listRow.add(getNumFmtdgt3(entity.getGasGnt(), isRuikei));
										break;
									case 9:
										//前年ガス売上原単位
										if(isRuikei) {
											entity.setLastGasGnt(getGnt(entity.getLastUriage(), entity.getLastGasAmt()));
										}
							        	listRow.add(getNumFmtdgt3(entity.getLastGasGnt(), isRuikei));
										break;
									case 10:
										//前年対比(ガス)
								        listRow.add(getZennenTaiHi(sysMonth, entity.getMeterYm()
									        		, entity.getGasGnt(), entity.getLastGasGnt(), entity.getGasZennenHi(), isRuikei));
											
										break;
									case 11:
										//当年水道使用量
							        	listRow.add(getNumFmtdgt2(entity.getWaterAmt(), isRuikei));
										break;
									case 12:
										//前年水道使用量
							        	listRow.add(getNumFmtdgt2(entity.getLastWaterAmt(), isRuikei));
										break;
									case 13:
										//当年水道売上原単位
										if(isRuikei) {
											entity.setWaterGnt(getGnt(entity.getUriage(), entity.getWaterAmt()));
										}
							        	listRow.add(getNumFmtdgt3(entity.getWaterGnt(), isRuikei));
										break;
									case 14:
										//前年水道売上原単位
										if(isRuikei) {
											entity.setLastWaterGnt(getGnt(entity.getLastUriage(), entity.getLastWaterAmt()));
										}
							        	listRow.add(getNumFmtdgt3(entity.getLastWaterGnt(), isRuikei));
										break;
									case 15:
										//前年対比(水道)
							        	listRow.add(getZennenTaiHi(sysMonth, entity.getMeterYm()
								        		, entity.getWaterGnt(), entity.getLastWaterGnt(), entity.getWaterZennenHi(), isRuikei));
										break;
					
									default:
										break;
								}//end of switch (r)
				        		if(isRuikei==false) {
				        			//累計以外は区分の値を設定します。
						        	switch (r) {
										case 0:
											//売上
											listRow.add("");
											break;
										case 1:
											//当年電力使用量
											listRow.add(getKbnLabel(entity.getDataMtDt(), entity.getEpMtFlg()));
											break;
										case 2:
											//前年電力使用量
											listRow.add(getKbnLabel(entity.getLastDataMtDt(), entity.getLastEpMtFlg()));
											break;
										case 3:
											//当年電力売上原単位
											listRow.add(getKbnLabel(entity.getDataMtDt(), entity.getEpMtFlg()));
											break;
										case 4:
											//前年電力売上原単位
											listRow.add(getKbnLabel(entity.getLastDataMtDt(), entity.getLastEpMtFlg()));
											break;
										case 5:
											//前年比(電力)
											listRow.add("");//何も設定しない
											break;
										case 6:
											//当年ガス使用量
											listRow.add(getKbnLabel(entity.getDataMtDt(), entity.getGasMtFlg()));
											break;
										case 7:
											//前年ガス使用量
											listRow.add(getKbnLabel(entity.getLastDataMtDt(), entity.getLastGasMtFlg()));
											break;
										case 8:
											//当年ガス売上原単位
											listRow.add(getKbnLabel(entity.getDataMtDt(), entity.getGasMtFlg()));
											break;
										case 9:
											//前年ガス売上原単位
											listRow.add(getKbnLabel(entity.getLastDataMtDt(), entity.getLastGasMtFlg()));
											break;
										case 10:
											//前年比(ガス)
											listRow.add("");//何も設定しない
											break;
										case 11:
											//当年水道使用量
											listRow.add(getKbnLabel(entity.getDataMtDt(), entity.getWaterMtFlg()));
											break;
										case 12:
											//前年水道使用量
											listRow.add(getKbnLabel(entity.getLastDataMtDt(), entity.getLastWaterMtFlg()));
											break;
										case 13:
											//当年水道売上原単位
											listRow.add(getKbnLabel(entity.getDataMtDt(), entity.getWaterMtFlg()));
											break;
										case 14:
											//前年水道売上原単位
											listRow.add(getKbnLabel(entity.getLastDataMtDt(), entity.getLastWaterMtFlg()));
											break;
										case 15:
											//前年比(水道)
											listRow.add("");//何も設定しない
											break;
						
										default:
											break;
									}//end of switch (r)
				        		}//end of if("RUIKEI".equals(atMonth)==false)
				        		isSetData=true;
		        			}//end of if((atMonth.compareTo(sysMonth)<0
			        		if ((i+1)<listData.size() && (cntMonth+1)<listOutputMonths.size()) {
			        			UISuiiDetaile nextEntity = (UISuiiDetaile)listData.get(i+1);
				        		if(miseCd.equals(nextEntity.getTaishoCd()))	{
				        			i++;
			        			}
			        		}
	        			}//end of if(miseCd.equals(entity.getTaishoCd()) && atMonth.equals(entity.getMeterYm()))
	        		}//end of if (i<listData.size())
		        	if (isSetData==false) {
			        	listRow.add("");
						listRow.add("");
		        	}
		        	isSetData=false;
		        	cntMonth++;
		        }// end of while(cntMonth<listOutputMonths.size())
		        //1行分のデータを格納します。
		        listCSV.add(listRow);
			}// end of for(int r=0; r<rowItem.length; r++)
    		while ((i+1)<listData.size()) {
    			i++;
    			UISuiiDetaile nextEntity = (UISuiiDetaile)listData.get(i);
        		if(!miseCd.equals(nextEntity.getTaishoCd()))	{
        			i--;
        			break;
    			}
    		}
		}// end of for(int i=0; i<listData.size(); i++)
	}	
    /**
     * 小数点以下無しのカンマ付き数値変換処理
     * @param num
     * @return
     */
    private String getNumFmtdgt0(BigDecimal num, boolean isNotBlank) {
    	if(isNotBlank || num != null) {
    		return numFmtdgt0.format(num==null?dec0:num);
    	}
    	return "";
    }
    /**
     * 小数点以下第二位のカンマ付き数値変換処理
     * @param num
     * @return
     */
    private String getNumFmtdgt2(BigDecimal num, boolean isNotBlank) {
    	if(isNotBlank || num != null) {
    		return numFmtdgt2.format(num==null?dec0:num);
    	}
    	return "";
    }
    /**
     * 小数点以下第二位のカンマ付き数値変換処理
     * @param num
     * @return
     */
    private String getNumFmtdgt3(BigDecimal num, boolean isNotBlank) {
    	if(isNotBlank || num != null) {
    		return numFmtdgt3.format(num==null?dec0:num);
    	}
    	return "";
    }
   
    /**
     * 対象外判断ラベル取得処理
     * 
     * @param flg
     * @return
     */
    private String getOutOfSubjectLabel(String flg) {
    	return "0".equals(flg)?"※":"";
    }
    /**
     * メンテ修正データ判断ラベル取得処理
     * @param dt
     * @return
     */
    private String getDataMtDtLabel(String dt) {
    	return CommonUtil.isNull(dt)?"":"★";
    }
    /**
     * 原単位計算値取得処理
     * 
     * @param uriage
     * @param amt
     * @return
     */
    private BigDecimal getGnt(BigDecimal uriage, BigDecimal amt) {
		BigDecimal dec1000000 = new BigDecimal("1000000");
		BigDecimal decGnt = new BigDecimal("0.000");
		if(amt != null && uriage != null && dec0.compareTo(amt) < 0 && dec0.compareTo(uriage) < 0 ) {
			decGnt = Calculator.divide(amt, uriage.divide(dec1000000, 10, BigDecimal.ROUND_HALF_UP), 3);
		}
		return decGnt;
    }
    /**
     * 
     * @param sysYm
     * @param menteYm
     * @param gnt
     * @param lastGnt
     * @param zennenTaiHi
     * @param isRuikei
     * @return
     */
    private String getZennenTaiHi(String sysYm, String menteYm
    		, BigDecimal gnt, BigDecimal lastGnt, BigDecimal zennenTaiHi, boolean isRuikei) {
    	boolean isDispYm = isRuikei?true:(menteYm.compareTo(sysYm)<0);
    	boolean isNullGnt = gnt==null;
    	boolean isNullLastGnt = lastGnt==null;
    	if (isDispYm && !isNullGnt && !isNullLastGnt) {
			return getNumFmtdgt2(zennenTaiHi, isRuikei)+"%";
		} else if((!isDispYm || isNullGnt)	&& isNullLastGnt && isRuikei==false) {
				return "";
		}
		return"0.00%";
    }
    /**
     * 自動修正判断ラベル取得処理
     * @param flg
     * @return
     */
    private String getKbnLabel(String mtDt, String flg) {
    	String label = getDataMtDtLabel(mtDt);
    	return CommonUtil.isNull(label)?("1".equals(flg)?"※":""):label;
    }
    public UIEnergyAmountDao getEnergyamountUIEnergyAmountDao() {
        return energyamountUIEnergyAmountDao;
    }

    public void setEnergyamountUIEnergyAmountDao(
            UIEnergyAmountDao energyamountUIEnergyAmountDao) {
        this.energyamountUIEnergyAmountDao = energyamountUIEnergyAmountDao;
    }

    public UINoInputMeterDao getEnergyamountUINoInputMeterDao() {
        return energyamountUINoInputMeterDao;
    }

    public void setEnergyamountUINoInputMeterDao(
            UINoInputMeterDao energyamountUINoInputMeterDao) {
        this.energyamountUINoInputMeterDao = energyamountUINoInputMeterDao;
    }

	/**
	 * @return クラス変数energyamountMstUserOnerInfoDao を戻します。
	 */
	public MstUserOnerInfoDao getEnergyamountMstUserOnerInfoDao() {
		return energyamountMstUserOnerInfoDao;
	}
	/**
	 * @param energyamountMstUserOnerInfoDao を クラス変数energyamountMstUserOnerInfoDaoへ設定します。
	 */
	public void setEnergyamountMstUserOnerInfoDao(
			MstUserOnerInfoDao energyamountMstUserOnerInfoDao) {
		this.energyamountMstUserOnerInfoDao = energyamountMstUserOnerInfoDao;
	}
	/**
	 * @return クラス変数energyamountUIMiseInfoDao を戻します。
	 */
	public UIMiseInfoDao getEnergyamountUIMiseInfoDao() {
		return energyamountUIMiseInfoDao;
	}
	/**
	 * @param energyamountUIMiseInfoDao を クラス変数energyamountUIMiseInfoDaoへ設定します。
	 */
	public void setEnergyamountUIMiseInfoDao(
			UIMiseInfoDao energyamountUIMiseInfoDao) {
		this.energyamountUIMiseInfoDao = energyamountUIMiseInfoDao;
	}
	/**
	 * @return クラス変数energyamountUISuiiAvgDao を戻します。
	 */
	public UISuiiAvgDao getEnergyamountUISuiiAvgDao() {
		return energyamountUISuiiAvgDao;
	}
	/**
	 * @param energyamountUISuiiAvgDao を クラス変数energyamountUISuiiAvgDaoへ設定します。
	 */
	public void setEnergyamountUISuiiAvgDao(UISuiiAvgDao energyamountUISuiiAvgDao) {
		this.energyamountUISuiiAvgDao = energyamountUISuiiAvgDao;
	}
	/**
	 * @return クラス変数energyamountUIOutOfSubjectAmtDao を戻します。
	 */
	public UIOutOfSubjectAmtDao getEnergyamountUIOutOfSubjectAmtDao() {
		return energyamountUIOutOfSubjectAmtDao;
	}
	/**
	 * @param energyamountUIOutOfSubjectAmtDao を クラス変数energyamountUIOutOfSubjectAmtDaoへ設定します。
	 */
	public void setEnergyamountUIOutOfSubjectAmtDao(
			UIOutOfSubjectAmtDao energyamountUIOutOfSubjectAmtDao) {
		this.energyamountUIOutOfSubjectAmtDao = energyamountUIOutOfSubjectAmtDao;
	}
	/**
	 * @return クラス変数energyamountUISuiiDao を戻します。
	 */
	public UISuiiDao getEnergyamountUISuiiDao() {
		return energyamountUISuiiDao;
	}
	/**
	 * @param energyamountUISuiiDao を クラス変数energyamountUISuiiDaoへ設定します。
	 */
	public void setEnergyamountUISuiiDao(UISuiiDao energyamountUISuiiDao) {
		this.energyamountUISuiiDao = energyamountUISuiiDao;
	}
	/**
	 * @return クラス変数mstSibuDao を戻します。
	 */
	public MstSibuDao getMstSibuDao() {
		return mstSibuDao;
	}
	/**
	 * @param mstSibuDao を クラス変数mstSibuDaoへ設定します。
	 */
	public void setMstSibuDao(MstSibuDao mstSibuDao) {
		this.mstSibuDao = mstSibuDao;
	}
}
