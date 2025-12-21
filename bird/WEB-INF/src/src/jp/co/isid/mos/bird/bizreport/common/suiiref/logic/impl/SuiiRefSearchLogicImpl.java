/**
 * 
 */
package jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.code.SiteKbn;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.suiiref.code.TaishoKikan;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;
import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.UITabData;
import jp.co.isid.mos.bird.bizreport.common.suiiref.util.SuiiRefUtil;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 推移表共通LOGIC【検索処理】
 * 
 * 作成日:2013/04/25
 * @author xkinu
 *
 */
public abstract class SuiiRefSearchLogicImpl {
	
    /* 表示日次月数:年度:12ヶ月*/
    protected static final int MONTH_CNT_NENDO = 12;
    /* 表示日次月数:任意月指定:13ヶ月*/
    protected static final int MONTH_CNT = 13;
	/**
	 * 事前条件処理
	 * 
	 * @param parameterDto
	 */
	private void validate(SuiiRefParameterDto parameterDto) 
	{
		if (getBirdUserInfo() == null) {
			throw new MissingDataException("ユーザー情報");
		}
		if (getBirdDateInfo() == null) {
			throw new MissingDataException("日付情報");
		}
		if (parameterDto == null) {
			throw new MissingDataException("検索パラメータ情報");
		}
		String companyCd = parameterDto.getCompanyCd();
		if (CommonUtil.isNull(companyCd)) {
			throw new MissingDataException("会社コード");
		}
		String tenpoShubetu = parameterDto.getTenpoShubetu();
		if (CommonUtil.isNull(tenpoShubetu)) {
			throw new MissingDataException("店舗種別");
		}
		
		String taishoKikan = parameterDto.getTaishoKikan();
		if (CommonUtil.isNull(taishoKikan)) {
			throw new MissingDataException("対象期間");
		}
		String kikanSitei = parameterDto.getKikanSitei();
		if (CommonUtil.isNull(kikanSitei)) {
			throw new MissingDataException("期間指定");
		}
		String taishoJoken = parameterDto.getTaishoJoken();
		if (CommonUtil.isNull(taishoJoken)) {
			throw new MissingDataException("対象条件");
		}
		if (!TaishoJoken.CODE_ALL.equals(taishoJoken)) {
			String hyojiTaisho = parameterDto.getHyojiTaisho();
			//個店コード入力時
			if(TaishoJoken.CODE_MISE.equals(taishoJoken)) {
				boolean isAlphabet = true;
	            //対象条件が全社以外の場合表示対象コードチェック
	            CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
				if (CommonUtil.isNull(hyojiTaisho)) {
					throw new NotNullException("表示対象", "miseCd", 0);
				}
                //コードフォーマットチェック
                if(!codeVeri.validate(hyojiTaisho) || hyojiTaisho.getBytes().length > 5) {
                    throw new InvalidInputException("表示対象", "miseCd", 0);
                }
				CodeFormatter cdf = new CodeFormatter(5, "00000");
                cdf.setFormatPattern("00000");
                parameterDto.setHyojiTaisho(cdf.format(hyojiTaisho, true));
			}
			else {
				if (CommonUtil.isNull(hyojiTaisho)) {
					throw new NotNullException("表示対象", "miseCd", 0);
				}
			}
		}
		String zennenDataShubetu = parameterDto.getZennenDataShubetu();
		if (CommonUtil.isNull(zennenDataShubetu)) {
			throw new NotNullException("前年データ種別");
		}

	}
	/* 
	 * 対象タブ検索処理
	 * 
	 * @see jp.co.isid.mos.bird.bizreport.common.suiiref.logic.impl.SuiiRefSearchLogicImpl#search(jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto, jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefResultDto)
	 */
	private List search(SuiiRefParameterDto parameterDto) {
		String taishoKikan = parameterDto.getTaishoKikan();
		if(parameterDto.isSuiiTypeGepo()) {
			//月次タブの場合、月次検索処理を行います。
			if(TaishoKikan.CODE_MONTH.equals(taishoKikan)) {
				return createListGepoNini(parameterDto);
			}
			else if (TaishoKikan.CODE_NENDO.equals(taishoKikan) )
			{
				return createListGepoNendo(parameterDto);
			}
		}
		else {
			//年月タブの場合、日次検索処理を行います。
			return createListNipo(parameterDto);			
		}
		return null;
	}
	/**
     * 日次データ検索処理
     * 
     * @param parameterDto 画面データ保持クラス
     * @return
     * @throws Exception
     */
	protected abstract List selectListNipo(SuiiRefParameterDto parameterDto);
	/**
     * 月次データ検索処理
     * 
     * ＜任意月指定の場合＞
     * 　【日次】売上計上日数分＋合計行のデータを検索＆算出を行う。
     * 　指定年月含めて過去12ヶ月分のデータを検索します。
     *   ただし、指定年月が当年当月(アプリ日付の年月)の場合は
     *   直近13ヶ月』仕様になり、指定年月含めて過去13ヶ月分のデータを検索
     *   【月次】の合計・平均値には当年当月の値が含まれない値になります。
     *   ソート順は降順
     *               
     * ＜年度の場合＞
     * 　【月次】指定年度の〔12ヶ月分〕、〔期別〕、〔通期〕、〔合計〕の計20行のデータを検索＆算出を行う。
     *   ソート順は降順
     * 
     * @param parameterDto 推移表共通DTO【検索条件】
     * @param fromYYYYMM
     * @param toYYYYMM
     * @return
     */
    protected abstract List selectListGepo(
    		SuiiRefParameterDto parameterDto, String fromYYYYMM, String toYYYYMM);
    /**
     * 未来月次検索処理
     * 
     * @param parameterDto 推移表共通DTO【検索条件】
     * @param kijunYm　基準年月
     * @param futureYMFrom 未来開始年月
     * @param futureYMTo 未来終了年月
     * @param zennenYMFrom 前年開始年月
     * @param zennenYMTo 前年終了年月
     * @return
     */
    protected abstract List selectListGepoFuture(
    		SuiiRefParameterDto parameterDto
    		, String kijunYm, String futureYMFrom, String futureYMTo
    		, String zennenYMFrom, String zennenYMTo);
    /**
     * 当月予算取得処理
     * 
     * @param parameterDto 推移表共通DTO【検索条件】
     * @param togetuYm 当月年月
     * @return
     */
    protected abstract BigDecimal selectTogetuYosan(SuiiRefParameterDto parameterDto, String togetuYm);
    /**
     * 日次データ生成検索処理
     * 
     * @param parameterDto 推移表共通DTO【検索条件】
     * @return
     */
    /**
     * 日次データ生成検索処理
     * 
     * @param parameterDto 推移表共通DTO【検索条件】
     * @return
     */
    private List createListNipo(SuiiRefParameterDto parameterDto) 
    {
        //日次データ検索
        List listNipo = selectListNipo(parameterDto);
        if(listNipo != null && listNipo.size() > 0) {
            int scale = 0;//初期値0桁
        	String pattern = parameterDto.getCodCompany().getDispFormat();
        	if(!CommonUtil.isNull(pattern)) {
        		if(pattern.indexOf(".")>=0) {
        			scale = pattern.length() - pattern.indexOf(".") - 1;
            	}
        	}
            //日次データ検索結果のサマリエンティティーを生成
        	SuiiRefUIEntity sumentity = createEntityNipoSum("body_sum5", "月合計", listNipo, 0, listNipo.size(), scale);
            //実績値のみで月合計行を作成した後、未来日データを結合します。
            listNipo.addAll(selectListNipoFuture(parameterDto));
            //サマリ値を格納した日次データを日次Listの最後の行へ格納する。
            listNipo.add(listNipo.size(), sumentity);
        }
        return listNipo;
        
    }
    /**
     * 推移表データ検索処理(月次)
     * 
     * ・期間指定の年月含める過去13ヶ月分を表示します。
     * ・期間指定の年月の前年の年月の表示は”前年同月”と表示します。
     * ・期間指定の年月が当月(システム日付の年月)の場合は、
     * 合計・平均値に値を含めず前年同月の値を含めます。
     *   ソート順は降順
     *   
     * @param parameterDto 推移表共通DTO【検索条件】
     * @return
     * @throws Exception
     */
    private List createListGepoNini(SuiiRefParameterDto parameterDto) {
        String sysDate = getBirdDateInfo().getSysDate();
        String kikanSiteiFromYm = parameterDto.getKikanSiteiFrom();
        String kikanSiteiToYm = parameterDto.getKikanSiteiTo();
        //期間指定の年月が当月(システム日付の年月)の場合か否か
        boolean isTargetSysYm = sysDate.substring(0, 6).equals(kikanSiteiToYm);
        //DAO【月次推移表データ】検索を実行し、List[[月次データ]]を取得します。
        List listSuiiGepo = selectListGepo(parameterDto, kikanSiteiFromYm, kikanSiteiToYm);        
        //歯抜けでデータが取得される可能性があるので、データが存在しない月の穴埋め処理を行います。
        List listNewGepo = new ArrayList(0);
        String taishoYm = "";
        for(int i=0; i<MONTH_CNT; i++) {
            try{
                //対象年月
                taishoYm = DateManager.getPrevMonth( kikanSiteiToYm, i );
            }catch (Exception ex) {
                throw new FtlSystemException("推移表日次データ検索処理内で"
                        , "期間指定年月を加算する際のDateManager.getNextMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            SuiiRefUIEntity entityGepo = pickupGepoEntity(listSuiiGepo, taishoYm);
            if(entityGepo == null) {
            	entityGepo = newGepoEntity();
            	entityGepo.setEigyoDt(taishoYm);
            	entityGepo.setEigyoDtLabel(taishoYm);
            }
            String userSiteKbn = getBirdUserInfo().getEmosslesAppId();
            if(i==0 && isTargetSysYm
            		&& (SiteKbn.UMI.equals(userSiteKbn) || SiteKbn.OHISAMA.equals(userSiteKbn))) {
            	//当月予算取得処理を実行し、当月予算を取得します。
            	BigDecimal decYosan = selectTogetuYosan(parameterDto, kikanSiteiToYm);
            	if(decYosan != null) {
                    /* 比率計算開始 */
                    //予算達成率算出
            		BigDecimal yosantasseiritu = Calculator.percentage(entityGepo.getUriage() ,decYosan, 2);
            		entityGepo.setYosan(decYosan);
            		entityGepo.setYosanTasseiRitu(yosantasseiritu);
					//値引き後予算達成率算出 add xou.zoubun 2019/11/18
					BigDecimal yosantasseirituAfterNebiki = Calculator.percentage(entityGepo.getUriageAfterNebiki() ,decYosan, 2);
					entityGepo.setTasseiAfterNebiki(yosantasseirituAfterNebiki);
            	}
            }
            listNewGepo.add(entityGepo);
            
        }// end of for

        int scale = 0;//初期値0桁
    	String pattern = parameterDto.getCodCompany().getDispFormat();
    	if(!CommonUtil.isNull(pattern)) {
    		if(pattern.indexOf(".")>=0) {
    			scale = pattern.length() - pattern.indexOf(".") - 1;
        	}
    	}
        //期間指定の年月が当月(システム日付の年月)の場合は、
        //合計・平均値に値を含めず前年同月の値を含める
        int startIndex = isTargetSysYm?1:0;
        int endIndex = isTargetSysYm?listNewGepo.size():12;
        // 12ヶ月合計エンティティを作成
        SuiiRefUIEntity entiry12Sum = createEntityGepoSum("body_sum5", "12ヶ月合計", listNewGepo, startIndex, endIndex, scale);
        //12ヶ月合計結果を月次Listへ格納
        listNewGepo.add(listNewGepo.size(), entiry12Sum);
        
        int avgCnt = 0;
        for(int i=startIndex; i<endIndex; i++) {
            if(listNewGepo.get(i) != null && ((SuiiRefUIEntity)listNewGepo.get(i)).getUriage().compareTo(new BigDecimal("0")) > 0) {
                avgCnt++;
            }
        }        
        //12ヶ月平均結果を月次Listへ格納
        listNewGepo.add(listNewGepo.size(), createEntity12Avg(entiry12Sum, avgCnt, scale));
        //List[[月次]]をリターンします。
        return listNewGepo;
    }
    /**
     * 推移表データ検索処理(月次)
     * 
     * ＜年度の場合＞
     * 　【月次】指定年度の〔12ヶ月分〕、〔期別〕、〔通期〕、〔合計〕の計20行のデータを検索＆算出を行う。
     *   ソート順は昇順
     * 
	 * @modifire 2008/07/14 T.Kinugawa(ASPAC) 年度月次画面の当年以降未来月の当年売上・客数・客単価は
	 *                                      全て0(ゼロ)と表示するよう変更する。
     * @param parameterDto
     * @return
     * @throws Exception
     */
    private List createListGepoNendo(SuiiRefParameterDto parameterDto) {
        String kikanSiteiFromYm = parameterDto.getKikanSiteiFrom();
        String kikanSiteiToYm = parameterDto.getKikanSiteiTo();
        String futureFrom = getBirdDateInfo().getSysDate();//未来日開始日（システム日付）      
        String futureTo = kikanSiteiToYm+"31";//未来日終了日
        String sysDateYm = futureFrom.substring(0,6);//システム日付の年月
        String appDate = getBirdDateInfo().getAppDate();
        kikanSiteiToYm = futureTo.compareTo(appDate) > 0? appDate.substring(0,6):kikanSiteiToYm;

        int sysdateIndex = MONTH_CNT_NENDO;
        String taishoYm = "";
        
        //月次データ取得
        List listSuiiGepo = selectListGepo(parameterDto, kikanSiteiFromYm, kikanSiteiToYm);
        //累計用当年当月日次データ
        List listSuiiNipoTogetu = null;
        List listSuiiGepoFuture = null;
        String futureYMFrom = null;
        String futureYMTo = null;
        
        if(futureFrom.compareTo(futureTo) <= 0) {
            String zennenYMFrom = null;
            String zennenYMTo = null;
            try{
            	//システム日付の年月から未来日データとします。
            	futureYMFrom = sysDateYm;
                futureYMTo = futureTo.substring(0,6);
                zennenYMFrom = DateManager.getPrevMonth(futureYMFrom, MONTH_CNT_NENDO) ;
                zennenYMTo = DateManager.getPrevMonth(futureYMTo, MONTH_CNT_NENDO);
            }catch (Exception ex) {
                throw new FtlSystemException("推移表未来日データ検索処理内で"
                        , "期間算出する際のDateManager.getPrevMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            listSuiiGepoFuture = selectListGepoFuture(parameterDto, kikanSiteiFromYm, futureYMFrom, futureYMTo, zennenYMFrom, zennenYMTo);
        }
        
        for(int i=0; i<MONTH_CNT_NENDO; i++) {
            try{
                //対象年月
                taishoYm = DateManager.getNextMonth( kikanSiteiFromYm, i );
            }catch (Exception ex) {
                throw new FtlSystemException("推移表日次データ検索処理内で"
                        , "期間指定年月を加算する際のDateManager.getNextMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            //日次データ検索結果のサマリエンティティーを生成
            SuiiRefUIEntity entityGepo = pickupGepoEntity(listSuiiGepo, taishoYm);
            
            if(sysDateYm.equals(taishoYm)){
                //システム日付の年月の場合
                sysdateIndex = i;
                listSuiiNipoTogetu = new ArrayList(0);
                if(entityGepo ==null) {
                	entityGepo = newGepoEntity();
                	entityGepo.setEigyoDt(taishoYm);
                	entityGepo.setEigyoDtLabel(taishoYm);
    	            //月次Listへ格納
    	            listSuiiGepo.add(i, entityGepo);
                }
            }
            if(listSuiiGepoFuture != null && taishoYm.compareTo(futureYMFrom) >= 0) {
                    //月次データを取得
            	SuiiRefUIEntity entityGepoFuture = pickupGepoEntity(listSuiiGepoFuture, taishoYm);
        	        if(entityGepo != null && entityGepoFuture != null) {
           	            //月次Listへ格納
           	            listSuiiGepo.set(i, entityGepoFuture);
        	        }
        	        else if(entityGepo == null) {
        	        	if(entityGepoFuture != null) {
	           	            //月次Listへ格納
	           	            listSuiiGepo.add(i, entityGepoFuture);
        	        	} else {
        	        		SuiiRefUIEntity entityKara = newGepoEntity();
        		        	entityKara.setEigyoDt(taishoYm);
        		        	entityKara.setEigyoDtLabel(taishoYm);
        		            //月次Listへ格納
        		            listSuiiGepo.add(i, entityKara);
        	        	}
        	        }
            }
            else if(entityGepo == null) {
            	SuiiRefUIEntity entityKara = newGepoEntity();
	        	entityKara.setEigyoDt(taishoYm);
	        	entityKara.setEigyoDtLabel(taishoYm);
	            //月次Listへ格納
	            listSuiiGepo.add(i, entityKara);
	        }
            
        }// end of for
        
        int scale = 0;//初期値0桁
    	String pattern = parameterDto.getCodCompany().getDispFormat();
    	if(!CommonUtil.isNull(pattern)) {
    		if(pattern.indexOf(".")>=0) {
    			scale = pattern.length() - pattern.indexOf(".") - 1;
        	}
    	}
        //各期別データ設定処理
        settingKibetuData(listSuiiGepo, sysdateIndex, listSuiiNipoTogetu, scale);


        return listSuiiGepo;
    }
    /**
     * 日次合計エンティティ作成処理
     * 
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param startRowIndex 集計開始インデックス
     * @param endRowIndex   集計終了インデックス
     * @param scale         売上値小数桁数(海外対応用)
     */
    protected abstract SuiiRefUIEntity createEntityNipoSum(String cssClassName, String taishoTitle, List listResult
    		, int startRowIndex, int endRowIndex, int scale);
    /**
     * 未来日データ取得処理
     * 
     * @param parameterDto 推移表共通DTO【検索条件】
     * @return
     */
    protected abstract List selectListNipoFuture(SuiiRefParameterDto parameterDto);
    /**
     * 12ヶ月合計エンティティ作成処理
     * 
     * @param entiry12Sum
     * @param avgCnt
     */
    protected abstract SuiiRefUIEntity createEntity12Avg(SuiiRefUIEntity entiry12Sum, int avgCnt, int scale); 

    /**
     * 対象年月前年月うるう年月か否か判断処理
     * 
     * ＜うるう年の判定方法＞
     * うるう年であるかどうかは、以下の方法で判定できます。 
     * 1.判定する年が 4 で割り切れる場合は手順 2. に進みます。割り切れない場合は手順 5. に進みます。 
     * 2.その年が 100 で割り切れる場合は手順 3. に進みます。割り切れない場合は手順 4. に進みます。 
     * 3.その年が 400 で割り切れる場合は手順 4. に進みます。割り切れない場合は手順 5. に進みます。 
     * 4.その年はうるう年です (この年は 366 日です)。 
     * 5.その年はうるう年ではありません (この年は 365 日です)。 
     */
    protected boolean isLasyYmLeapYearMonth(final String taishoYm) {
    	if(!"02".equals(taishoYm.substring(4,6))){
    		return false;
    	}
    	int lastYear = 0;
    	try {
    		lastYear = Integer.valueOf(DateManager.getPrevMonth(taishoYm, 12).substring(0, 4)).intValue();
    	}
    	catch (Exception e) {
    		throw new FtlSystemException("売上推移表画面：うるう年判定処理");
    	}
    	//1.判定する年が 4 で割り切れる場合は手順 2. に進みます。
    	if(lastYear%4==0) {
			//2.その年が 100 で割り切れる場合は手順 3. に進みます。
    		if(lastYear%100==0) {
				//3.その年が 400 で割り切れる場合は手順 4. に進みます。
    			if(lastYear%400 == 0) {
        			//4.その年はうるう年です (この年は 366 日です)。 
    				return true;
    			}
    		}
    		//2.その年が 100 で割り切れない場合は手順 4. に進みます。 
    		else {
    			//4.その年はうるう年です (この年は 366 日です)。 
    			return true;
    		}
    	}
      	//5.その年はうるう年ではありません (この年は 365 日です)。 
   		return false;
    }
    /**
     * タブ(昇順)作成取得処理
     * 
     * @param startYm 開始年月 yyyyMM
     * @param monthCnt
     * @return
     */
    private List createListTabAbs(final String startYm, int monthCnt) {       
        List listTab = new  ArrayList();
        String taishoYm = "";
        for (int i=0; i<monthCnt; i++) {
            try{
                //対象年月
                taishoYm = DateManager.getNextMonth( startYm, i);
            }catch (Exception ex) {
                throw new FtlSystemException("推移表タブ作成処理内で"
                        , "期間指定年月を加算する際のDateManager.getNextMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
        	UITabData eTab = new UITabData();
        	eTab.setCode(taishoYm);
        	eTab.setLabel(taishoYm);
        	listTab.add(eTab);

        }// end of for (int i=0; i<listCnt; i++)
        return listTab;
    }
    /**
     * タブ(降順)作成取得処理
     * 
     * @param startYm 開始年月 yyyyMM
     * @param monthCnt
     * @return
     */
    private List createListTabDesc(final String startYm, int monthCnt) {
        
        List listTab = new  ArrayList();
        String taishoYm = "";
        for (int i=0; i<monthCnt; i++) {
            try{
                //対象年月
                taishoYm = DateManager.getPrevMonth( startYm, i);
            }catch (Exception ex) {
                throw new FtlSystemException("推移表タブ作成処理内で"
                        , "期間指定年月を加算する際のDateManager.getNextMonthメソッド処理で例外が発生しました。"
                        , ex);
            }
            //YY/MMフォーマット
        	UITabData eTab = new UITabData();
        	eTab.setCode(taishoYm);
        	eTab.setLabel(taishoYm);
        	listTab.add(eTab);

        }// end of for (int i=0; i<listCnt; i++)
        return listTab;
    }
	/* 
	 * 結果取得処理
	 * @param 推移表共通DTO【検索条件】
	 * @param 推移表共通DTO【結果情報】
	 * @see jp.co.isid.mos.bird.bizreport.moscardsuiiref.logic.SearchLogic#execute(jp.co.isid.mos.bird.framework.control.BirdUserInfo, jp.co.isid.mos.bird.framework.control.BirdDateInfo, jp.co.isid.mos.bird.bizreport.common.suiiref.dto.SuiiRefParameterDto)
	 */
	public SuiiRefResultDto execute(
			boolean isCsv, SuiiRefParameterDto parameterDto, SuiiRefResultDto suiiRefResultDto) 
	{
		//０．事前条件処理
		validate(parameterDto);
		//１．推移表共通DTO【結果情報】がnullの場合、下記の処理を行います。
		boolean isFocusDefault = false;
		if(suiiRefResultDto == null || CommonUtil.isNull(parameterDto.getFocusTab())) {
			//1-8.推移表共通DTO【結果情報】生成取得処理を実行し、戻り値をパラメータ.推移表共通DTO【結果情報】へ代入します。
			suiiRefResultDto = newSuiiRefResultDto();
			isFocusDefault = true;
		}
		if(suiiRefResultDto.getListUITabData()== null || suiiRefResultDto.getListUITabData().isEmpty()) {
	        String userTypeCd = getBirdUserInfo().getMstUser().getUserTypeCd();
	        String taishoJoken = parameterDto.getTaishoJoken();
	        String taishoJokenName = TaishoJoken.getName(userTypeCd, taishoJoken);
	        //1-1.推移表共通DTO【検索条件】.対象条件名称へ推移表共通CODE【対象条件コード定数クラス】名称取得処理で取得した値を設定します。
	        parameterDto.setTaishoJokenName(taishoJokenName);
	        //1-2.変数.表示対象名称へ""(空)を設定します。
	        String hyojiTaishoName = "";
	        //1-3.推移表共通DTO【検索条件】.List[[対象条件]]から
	        //    推移表共通DTO【検索条件】.対象条件のUILists[対象条件]を取得します。
	        UILists uiTaishoJoken = (UILists)TaishoJoken.getUILists(taishoJoken, parameterDto.getListTaishoJoken());
	        //1-4.処理1-3のUILists[対象条件].データ件数取得処理で戻り値が0以上の場合、下記の処理を行います。
	        if(uiTaishoJoken.getListDataCnt()>0) {
		        //4-1.UILists[対象条件].List[[データ]]を変数.List[CodHyojiTaisho[表示対象]]とします。
		        List listHyojitaisho = uiTaishoJoken.getListData();
		        //4-2.変数.List[CodHyojiTaisho[表示対象]]の件数分下記の処理を行います。
		        for (int ht=0; ht<listHyojitaisho.size(); ht++) {
		        	//for-1.現行インデックスのCodHyojiTaisho[表示対象]を取得します。
		        	CodHyojiTaisho eHyojiTaisho = (CodHyojiTaisho)listHyojitaisho.get(ht);
		        	//for-2.CodHyojiTaisho[表示対象].表示対象コード＝推移表共通DTO【検索条件】.表示対象の場合、下記の処理を行います。
		        	if(eHyojiTaisho.getHyojitaishoCd().equals(parameterDto.getHyojiTaisho())) {
		        		//for-2-1.変数.表示対象名称へCodHyojiTaisho[表示対象].表示対象名称を設定します。
		        		hyojiTaishoName = eHyojiTaisho.getHyojitaishoName();
		        		//for-2-2.for処理終了
		        		break;
		        	}
		        	//for-3.処理for-1へ
		        }//end of for (int ht=0; ht<listHyojitaisho.size(); ht++)
	        }//end of if(uiTaishoJoken.getListDataCnt()>0)
	        //1-5．推移表共通DTO【検索条件】.表示対象名称へ変数.表示対象名称を設定します。
	        parameterDto.setHyojiTaishoName(hyojiTaishoName.trim());
			//1-6．DAO【タブ情報】検索を実行し、List[UITabData[店舗数]]を取得します。
			List listMiseCnt = selectListMiseCnt(isCsv, parameterDto);
	        //1-7.推移表共通DTO【検索条件】.再検索制御フラグへtrueを設定します。
	    	parameterDto.setResearchFlg(true);
			//1-8．List[UITabData[店舗数]]が０件の場合、下記のExceptionを発生させます。
	        if (listMiseCnt == null || listMiseCnt.isEmpty()) {
	        	throw new NoResultException("");
	        }
	        //1-9．推移表共通DTO【結果情報】.該当データ存在判断フラグへtrueを設定する。
	        suiiRefResultDto.setExistData(true);
	        //1-10．List[UITabData[店舗数]]のインデックス0番目を変数.UITabData[0番目]として取得します。
	        UITabData firstTab = (UITabData) listMiseCnt.get(0);
	        //1-11．(本部ユーザー用)
	        if(TaishoJoken.CODE_MISE.equals(taishoJoken)) {
	        	//推移表共通DTO【検索条件】.表示対象名称へ
	        	//変数.UITabData[0番目].表示対象名称を設定します。
	        	parameterDto.setHyojiTaishoName(firstTab.getHtName().trim());
	        }
	        String taishoKikan = parameterDto.getTaishoKikan();
	        if(isCsv) {
	        	//Csv-0.推移表共通DTO【検索条件】.対象期間＝"任意月指定"の場合、下記の処理を行います。
	        	if(TaishoKikan.CODE_MONTH.equals(taishoKikan)) {
	        		//Label-1.タブ(降順)作成取得処理を実行し、戻り値を変数.List[[タブ情報]]とします。
	        		List listTabData = createListTabDesc(parameterDto.getKikanSitei(), MONTH_CNT);
	        		//Label-2.変数.List[[タブ情報]]のインデックス12番目のUITabData[タブ情報].ラベルへ"前年同月"を設定します。
	        		UITabData dogetuTab = (UITabData) listTabData.get(MONTH_CNT-1);
		        	dogetuTab.setLabel(SuiiRefUtil.EIGYO_DT_LABEL_DOGETU);
		        	//Label-3.List[[タブ情報]]へインデックス13番目のUITabData[タブ情報]を追加します。
			        listMiseCnt.add(dogetuTab);
	        	}
		        //Csv-1．推移表共通DTO【結果情報】.List[[タブ情報]]へList[[タブ情報]]を設定します。
				suiiRefResultDto.setListUITabData(listMiseCnt);
	        	//Csv-2.推移表共通DTO【結果情報】.Map[タブ情報]へ、変数.UITabData[0番目].コードをキーに、変数.UITabData[0番目]を格納します。
	        	suiiRefResultDto.setUITabData(firstTab.getCode(), firstTab);
	        }
	        else{
		        //Tab-1．変数.List[[タブ情報]]を生成します。
		        List listTabData = new ArrayList(0);
		        //Tab-2.推移表共通DTO【検索条件】.対象期間＝"任意月指定"の場合、下記の処理を行います。
		        if(TaishoKikan.CODE_MONTH.equals(taishoKikan)) {
		        	//NINI-1.タブ(降順)作成取得処理を実行し、過去13ヶ月分(タブ【前年同月】含む、タブ【月次】は含まれない)のList[[タブ]]を取得します。
		        	//NINI-2.変数.List[[タブ情報]]へ処理NINI-1のList[[タブ]]を設定します。
		        	listTabData = createListTabDesc(parameterDto.getKikanSitei(), MONTH_CNT);
		            //NINI-3.変数.List[[タブ情報]]のインデックス1番目へ変数.UITabData[0番目]を追加設定します。
		        	listTabData.add(1, firstTab);
		        	//NINI-4.変数.List[[タブ情報]]のインデックス13番目のUITabData[タブ情報].ラベルへ"前年同月"を設定します。
		        	UITabData dogetuTab = (UITabData) listTabData.get(MONTH_CNT);
		        	dogetuTab.setLabel(SuiiRefUtil.EIGYO_DT_LABEL_DOGETU);
		        	if(isFocusDefault) {
			        	//NINI-5.推移表共通DTO【検索条件】.フォーカスタブへ推移表共通DTO【検索条件】.期間指定を設定します。
			        	parameterDto.setFocusTab(parameterDto.getKikanSitei());
		        	}
		        }
		        //Tab-3.処理Tab-2以外の場合は推移表共通DTO【検索条件】.対象期間＝"年度"とし、下記の処理を行います。
		        else {
			        //NENDO-1.推移表共通定数クラス.期間指定開始年月取得処理を実行し、期間指定開始日を取得します。
			        //	パラメータ：推移表共通DTO【検索条件】.対象期間
		        	//NENDO-2.タブ(昇順)作成取得処理を実行し、対象年度の12ヶ月分(タブ【月次】は含まれない)のList[[タブ]]を取得します。
		        	//NENDO-3.変数.List[[タブ情報]]へ処理NENDO-2のList[[タブ]]を設定します。
		        	listTabData = createListTabAbs(parameterDto.getKikanSiteiFrom(), MONTH_CNT_NENDO);
		            //NENDO-4.変数.List[[タブ情報]]のインデックス0番目へ変数.UITabData[0番目]を設定します。
		        	listTabData.add(0, firstTab);
		        	if(isFocusDefault) {
			        	//NENDO-5.推移表共通DTO【検索条件】.フォーカスタブへ対象期間定数クラス.年度を設定します。
			        	parameterDto.setFocusTab(firstTab.getCode());
		        	}
		        }
				//Tab-4.変数.List[[タブ情報]]の件数分、下記の処理を行います。
		        for(int t=0; t<listTabData.size(); t++) {
		        	//for-1.変数.List[[タブ情報]]から現行インデックス位置のUITabData[タブ情報]を取得します。
		        	UITabData eTab = (UITabData)listTabData.get(t);
		        	//for-2.[月次]タブ以外でかつ、アプリ日付の年月よりも未来の[タブ情報]の場合、
		        	if(!eTab.isSuiiTypeGepo() && eTab.getCode().compareTo(getBirdDateInfo().getAppDate().substring(0, 6))>0) {
		        		//UITabData[タブ情報].未来判断値へtrueを設定します。
		        		eTab.setFuture(true);
		        	}
		        	//for-3.推移表共通DTO【結果情報】.Map[タブ情報]へ、UITabData[タブ情報].コードをキーに、UITabData[タブ情報]を格納します。
		        	suiiRefResultDto.setUITabData(eTab.getCode(), eTab);
		        }
		        //Tab-5.List[UITabData[店舗数]]の店舗数をList[[タブ情報]]の対象タブへ設定します。
		        for(int m=0; m<listMiseCnt.size(); m++) {
		        	UITabData eMiseCnt = (UITabData)listMiseCnt.get(m);
		        	UITabData eTab = suiiRefResultDto.getUITabData(eMiseCnt.getCode());
		        	eTab.setMiseCnt(eMiseCnt.getMiseCnt());
		        	eTab.setMiseCntAll(eMiseCnt.getMiseCntAll());
		        }
		        //Tab-6．推移表共通DTO【結果情報】.List[[タブ情報]]へList[[タブ情報]]を設定します。
				suiiRefResultDto.setListUITabData(listTabData);
	        }
		}
		//３．データ検索処理を行います。
		//SEARCH-1．推移表共通DTO【結果情報】.フォーカス対象UITabData[タブ情報]取得処理を実行し、戻値をUITabData[フォーカスタブ情報]とします。
		UITabData uiFocusTab = (suiiRefResultDto.getUITabData(parameterDto.getFocusTab()));
		//SEARCH-2．UITabData[フォーカスタブ情報].全店舗数(当年or前年or予算のみ)>0の場合、下記の処理を行います。
		List listTabResult = null;
		if(uiFocusTab.getMiseCntAll()>0) {
			//1.対象タブ検索処理を実行し、戻り値List[[検索結果]]を取得します。
			listTabResult = search(parameterDto);
			//2.UITabData[フォーカスタブ情報]が月次タブの場合、List[[検索結果]]へリンク情報を設定します。
			if(uiFocusTab.isSuiiTypeGepo()) {
				//LINK-1.推移表共通DTO【結果情報】.List[[タブ情報]]の年月(過去・当月まで)のデータへリンク情報を設定します。
				List listTabData = suiiRefResultDto.getListUITabData();
				for(int t=0; t<listTabData.size(); t++) {
					UITabData eTab = (UITabData)listTabData.get(t);
					SuiiRefUIEntity eGepo = pickupGepoEntity(listTabResult, eTab.getCode());
					if(eGepo != null) {
						eGepo.setEigyoDtLabel(eTab.getLabel());
						eGepo.setLink(!eTab.isFuture());
						eGepo.setExistData(eTab.getMiseCntAll()>0);
					}
				}
			}
		}
		//SEARCH-3．推移表共通DTO【結果情報】.List[[フォーカスタブ検索結果]]へ設定します。
		suiiRefResultDto.setListFocusTabResult(listTabResult);
		//SEARCH-4．推移表共通DTO【結果情報】.フォーカスタブへ推移表共通DTO【検索条件】.フォーカスタブを設定します。
		suiiRefResultDto.setFocusTab(parameterDto.getFocusTab());
        //SEARCH-5．推移表共通DTO【結果情報】換算フラグへDTO【検索条件】.換算フラグを設定する。
        suiiRefResultDto.setKansanFlg(parameterDto.isKansan());
		//４．推移表共通DTO【結果情報】をリターンします。
		return suiiRefResultDto;
	}
    /**
     * 対象年月Entity[月次]取得処理
     * 
     * 
     * @param listGepo List[SuiiRefUIGepo[月次]]
     * @param targetYm
     * @return SuiiRefUIGepo 推移表共通Entity[月次]
     */
    private SuiiRefUIEntity pickupGepoEntity(List listGepo, String targetYm) {
        for(int i=0; i<listGepo.size(); i++) {
        	SuiiRefUIEntity entity = (SuiiRefUIEntity)listGepo.get(i);
            if(entity == null) {
                break;
            }
            else if(entity.getEigyoDt().equals(targetYm)) {
                return entity;
            }
        }
        return null;
    }
    /**
     * 年度用　期別合計行生成処理
     * @param listSuiiGepo 
     * @param sysdateIndex システム日付年月の月次データインデックス番号
     * @param listSuiiNipoTogetu
     * @param scale
     * @throws Exception
     */
    private void settingKibetuData(List listSuiiGepo, int sysdateIndex, List listSuiiNipoTogetu, int scale) 
    {
        //第一四半期結果エンティティを作成
    	SuiiRefUIEntity entiry1Hanki = createEntityGepoSum("body_sum1", "第一四半期", listSuiiGepo, 0, (sysdateIndex>3? 3:sysdateIndex), scale);
        //第二四半期結果エンティティを作成
    	SuiiRefUIEntity entiry2Hanki = createEntityGepoSum("body_sum1", "第二四半期", listSuiiGepo, 3, (sysdateIndex>6? 6:sysdateIndex), scale);
        //上期結果エンティティを作成
    	SuiiRefUIEntity entiryKami   = createEntityGepoSum("body_sum2", "上期", listSuiiGepo, 0, (sysdateIndex>6? 6:sysdateIndex), scale);
        //第三四半期結果エンティティを作成
    	SuiiRefUIEntity entiry3Hanki = createEntityGepoSum("body_sum1", "第三四半期", listSuiiGepo, 6, (sysdateIndex>9? 9:sysdateIndex), scale);
        //第四四半期結果エンティティを作成
    	SuiiRefUIEntity entiry4Hanki = createEntityGepoSum("body_sum1", "第四四半期", listSuiiGepo, 9, (sysdateIndex>12? 12:sysdateIndex), scale);
        //下期結果エンティティを作成
    	SuiiRefUIEntity entirySimo   = createEntityGepoSum("body_sum2", "下期", listSuiiGepo, 6, (sysdateIndex>12? 12:sysdateIndex), scale);
        //年度累計エンティティを作成
    	SuiiRefUIEntity entiryRuikei = createEntityGepoRuikei("body_sum3", "年度累計", listSuiiGepo, sysdateIndex, listSuiiNipoTogetu, scale);
        //通期エンティティを作成
    	SuiiRefUIEntity entiryTuuki  = createEntityGepoSum("body_sum5", "通期", listSuiiGepo, 0, listSuiiGepo.size(), scale);
        
        //第一四半期結果を月次Listへ格納
        listSuiiGepo.add(3, entiry1Hanki);
        //第二四半期結果を月次Listへ格納
        listSuiiGepo.add(7, entiry2Hanki);
        //上期結果を月次Listへ格納
        listSuiiGepo.add(8, entiryKami);
        //第三四半期結果を月次Listへ格納
        listSuiiGepo.add(12, entiry3Hanki);
        //第四四半期結果を月次Listへ格納
        listSuiiGepo.add(16, entiry4Hanki);
        //下期結果を月次Listへ格納
        listSuiiGepo.add(17, entirySimo);
        //年度累計結果を月次Listへ格納
        listSuiiGepo.add(18, entiryRuikei);
        //通期結果を月次Listへ格納
        listSuiiGepo.add(19, entiryTuuki);
    }
    /**
     * 月次合計エンティティ作成処理
     * 
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param startRowIndex 集計開始インデックス
     * @param endRowIndex   集計終了インデックス
     * @param scale         売上値小数桁数(海外対応用)
     * @return
     */
    protected abstract SuiiRefUIEntity createEntityGepoSum(
    		String cssClassName, String taishoTitle, List listResult
    		, int startRowIndex, int endRowIndex, int scale);
    /**
     * 累計エンティティ作成処理
     * 
     * @param cssClassName CSSクラス名
     * @param taishoTitle  対象ラベル
     * @param listResult   List[[検索結果]]
     * @param endRowIndex   集計終了インデックス
	 * @param listSuiiNipoTogetu 当月日報
     * @param scale         売上値小数桁数(海外対応用)
     * @return　SuiiRefUIEntity[累計]
     */
    protected abstract SuiiRefUIEntity createEntityGepoRuikei(
    		String cssClassName, String taishoTitle
    		, List listResult, int endRowIndex, List listSuiiNipoTogetu, int scale);
	/**
	 * 店舗数検索結果取得処理
	 * 
	 * DAO【タブ情報】検索を実行し、List[UITabData[店舗数]]を取得します。
	 * @param isCsv CSVフラグ
	 * @param parameterDto　推移表共通DTO【検索条件】
	 * @return
	 */
	protected abstract List selectListMiseCnt(boolean isCsv, SuiiRefParameterDto parameterDto);
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * CTL【BIRDユーザー情報】取得処理
     * @return birdUserInfo を戻します。
     */
    protected BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
	/**
	 * CTL【BIRD日付情報】取得処理
	 * @return birdDateInfo を戻します。
	 */
    protected BirdDateInfo getBirdDateInfo() {
		return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
	}
    /**
     * DTO【結果情報】生成取得処理
     * @return SuiiRefResultDto
     */
    protected abstract SuiiRefResultDto newSuiiRefResultDto();
    /**
     * 月次エンティティ生成取得処理
     * @return SuiiRefResultDto
     */
    protected abstract SuiiRefUIEntity newGepoEntity();
}
