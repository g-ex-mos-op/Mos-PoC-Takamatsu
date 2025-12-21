/**
 * 
 */
package jp.co.isid.mos.bird.inforegist.notificationregist.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.code.InfoShu;
import jp.co.isid.mos.bird.common.entity.TrnControlShozoku;
import jp.co.isid.mos.bird.common.entity.UIViewKanrenBunsho;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.inforegist.notificationregist.dao.TrnOpenSibuDao;
import jp.co.isid.mos.bird.inforegist.notificationregist.dao.UIHoldOpenTargetInfoDao;
import jp.co.isid.mos.bird.inforegist.notificationregist.dao.UIKanrenBunshoDao;
import jp.co.isid.mos.bird.inforegist.notificationregist.dao.UIShozokuDao;
import jp.co.isid.mos.bird.inforegist.notificationregist.dto.CsvDownloadDto;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.TrnOpenSibu;
import jp.co.isid.mos.bird.inforegist.notificationregist.entity.UIHoldOpenTargetInfo;

/**
 * 本日登録分CSVデータ作成ロジック
 * 
 * 作成日:2010/02/23
 * @author xkinu
 *
 */
public class CsvTodayInsertListLogicImpl implements CsvOutputLogic {
    /* ロジックID */
    public static final String LOGIC_ID = "BIF002L10";

	private static final String SAKUJO = "1";
	/** 日付フォーマッター */
    private static DateFormatter formatter = new DateFormatter();
    //DAO【関連文書の取得】
    private UIKanrenBunshoDao notificationUIKanrenBunshoDao;
    /* DAO【お知らせ詳細情報】 */
    private UIShozokuDao notificationRegistUIShozokuDao;
    /* DAO【公開対象支部情報】 */
    private TrnOpenSibuDao notificationRegistTrnOpenSibuDao;
    /* DAO【お知らせ詳細情報】 */
    private UIHoldOpenTargetInfoDao uiHoldOpenTargetInfoDao;

    /**
     * ファイル名取得処理
     * 
     *  「OSIRASEyyyymmdd.csv」とする。（yyyymmddはシステム日付）
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		// TODO 自動生成されたメソッド・スタブ
		return "OSIRASE"+getSysDate(csvOutputDto)+".csv";
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
		
		//１．【お知らせ詳細情報】本日登録分データ取得を実行します。
		List listCsvData = getListCsvData(csvOutputDto);
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(csvOutputDto, listCSV);
        //４．明細部ヘッダー作成
		addListHeader(listCSV);
        //５．明細部データ作成
		addList(listCSV, listCsvData, csvOutputDto);
		//６．List[[CSV出力用リスト]]をリターンします。
		return listCSV;
	}
	/**
	 * ヘッダ部作成設定処理
	 * 
	 * @param listCSV
	 */
	private void addHeader(CsvOutputDto csvOutputDto, List listCSV) {
		
        /** 1行目作成 */
        List listHeader1Row = new ArrayList();
        //キャンペーン情報
        listHeader1Row.add("更新日：");
        listHeader1Row.add(
        		formatter.format(getSysDate(csvOutputDto)
        				, DateFormatter.PATTERN_SLASH
        				, DateFormatter.DATE_TYPE_YMD)
        		);
        listCSV.add(listHeader1Row);
        //空白行
        listCSV.add(new ArrayList());
        //空白行
        listCSV.add(new ArrayList());
        //空白行
        listCSV.add(new ArrayList());
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param listCSV
	 * @param requestDto
	 * 
	 * 更新日：2010/06/01 xkinu 「登録日」「区分」追加
	 */
	private void addListHeader(List listCSV) {
		//１．ヘッダー部1行目のリストをインスタンス化します。
		List listHeader1 = new ArrayList(0);
		listHeader1.add("公開開始日");
		listHeader1.add("カテゴリ");
		listHeader1.add("登録日");//<--2010/06/01 ADD
		listHeader1.add("区分");//<--2010/06/01 ADD
		listHeader1.add(InfoShu.TUTATU_NAME + "管理No");
		listHeader1.add("ファイル名");
		listHeader1.add("タイトル");
		listHeader1.add("リンクファイル名");
		listHeader1.add("添付ファイル名");
		listHeader1.add("所属");
		listHeader1.add("業態");
		listHeader1.add("業態別設定");
		listHeader1.add("公開対象支部");
		listHeader1.add("個店別設定");
		listHeader1.add("個店別設定店舗数");

		listCSV.add(listHeader1);
	}
	/**
	 * データ項目名称格納処理
	 * 
	 * @param listData
	 * @param requestDto
	 * @param listTabSearchData
	 */
	private void addList(List listCSV, List listCsvData, CsvOutputDto csvOutputDto) {

		String lastRegDtSeqNo = "";
		//１．処理２で取得したList[[CSV出力用リスト]]へ
		//    List[[本日登録分データ]]の各行の情報を設定します。
		for(int i=0; i<listCsvData.size(); i++) {
			//for-1.対象インデックス行のエンティティ[本日登録分データ]を取得する。
			UIHoldOpenTargetInfo entity = (UIHoldOpenTargetInfo)listCsvData.get(i);
			String thisRegDtSeqNo = entity.getRegDate()+entity.getSeq();
			//for-2.1行分のList[[行情報]]をインスタンス化します。
			List rowData = new ArrayList(0);
			if(lastRegDtSeqNo.equals(thisRegDtSeqNo)) {
				if(SAKUJO.equals(entity.getSakujoFlg())) {
					continue;
				}
				rowData.add("");//公開開始日
				rowData.add("");//カテゴリ名称
				rowData.add("");//登録日yyyy/MM/dd
				rowData.add("");//区分
				rowData.add("");//管理番号
				rowData.add("");//ファイル名
				rowData.add("");//タイトル
			}
			else {
				//for-3.下記の値をList[[行情報]]へaddします。
				/** 公開開始日 */
				rowData.add(formatter.format(entity.getPubDate()
	    				, DateFormatter.PATTERN_SLASH_WEEK
	    				, DateFormatter.DATE_TYPE_YMD));
				rowData.add(entity.getCateName());   //カテゴリ名称
				rowData.add(
						formatter.format(entity.getRegDate()
								, DateFormatter.PATTERN_SLASH
								, DateFormatter.DATE_TYPE_YMD));//登録日yyyy/MM/dd
				boolean isSakujoData = "1".equals(entity.getSakujoFlg());
				rowData.add(isSakujoData?"削除":(entity.getRegDate().equals(getSysDate(csvOutputDto)) ?"新規":"編集"));   //区分
				rowData.add(entity.getKanriNo());    //管理番号
				rowData.add(entity.getFileName());   //ファイル名
				rowData.add(entity.getTitle());      //タイトル
			}
			if(SAKUJO.equals(entity.getSakujoFlg())==false) {
				if(lastRegDtSeqNo.equals(thisRegDtSeqNo) == false) {
					rowData.add(entity.getLinkFiles());  //リンクファイル名
					//添付ファイル
					String tempFileName = "";
					for(int t=0; t<3; t++) {
						if(CommonUtil.isNull(entity.getTempFileName(t))) {
							continue;
						}
						if(CommonUtil.isNull(tempFileName)==false) {
							tempFileName+=",";
						}
						tempFileName+=entity.getTempFileName(t);
					}
					rowData.add(tempFileName);//添付ファイル
					rowData.add(entity.getShozokuName());//所属
				}
				else {
					rowData.add("");  //リンクファイル名
					rowData.add("");//添付ファイル
					rowData.add("");//所属
				}
				rowData.add(entity.getGyotaiKbnName()); //業態
				rowData.add(entity.getKobetsuUmu()); //業態別設定有無
				rowData.add(entity.getPubSibuName());//公開対象支部
				rowData.add(entity.getMiseUmu());    //個店別設定有無
				rowData.add(entity.getKotenCnt());//個店別設定店舗数
			}//if(lastSeqNo.equals(entity) == false)
			else {
				//削除の文書の場合は空(””ブランク)で設定します。
				rowData.add("");  //リンクファイル名
				rowData.add("");//添付ファイル
				rowData.add("");//所属
				rowData.add(""); //業態
				rowData.add(""); //業態別設定有無
				rowData.add("");//公開対象支部
				rowData.add("");    //個店別設定有無
				rowData.add("");//個店別設定店舗数
			}
			
			//1行分のデータを格納します。
			listCSV.add(rowData);
			lastRegDtSeqNo = thisRegDtSeqNo;
		}
	}
	/**
	 * 実行処理
	 * 
	 * @param birdDateInfo
	 * @param birdUserInfo
	 * @param sessionDto
	 * @param searchEngineDto
	 * @param listSearchData
	 * @return
	 */
	private List getListCsvData(CsvOutputDto csvOutputDto)
	{
		String lastTmspDt = formatter.format(getSysDate(csvOutputDto), DateFormatter.PATTERN_CROSS
				, DateFormatter.DATE_TYPE_YMD);
		//１．【お知らせ詳細情報】本日登録分データ取得を実行し、
		//    戻り値を変数．List[[本日登録分データ]]へ設定します。
		List listTodayInsertData = getUiHoldOpenTargetInfoDao().select(lastTmspDt);
		if(listTodayInsertData.size()==0) {
			throw new NoResultException("");
		}
		//２．Framework共通LOGIC【関連文書取得】を実行し、List[[関連文書]]を取得します。
        List listKanrenBunsho = getNotificationUIKanrenBunshoDao().select(lastTmspDt);
        //３．SEQの文字列をキーに関連文書の連結文字列を作成します。
        //３－１．変数.Map[関連文書情報]をインスタンス化します。
        Map mapKanren = new HashMap();
        //３－２．処理２で取得した[[関連文書]]の件数分、下記の処理を行います。
        for(int k=0; k<listKanrenBunsho.size(); k++) {
        	//for-1.対象インデックス行のエンティティ[関連文書]を取得します。
        	UIViewKanrenBunsho entity = (UIViewKanrenBunsho)listKanrenBunsho.get(k);
        	//for-2.変数.対象キーとして、処理for-1の[関連文書].SEQの値を設定します。
        	settingMap(entity.getMotoRegDate()+entity.getMotoSeq(), mapKanren, entity.getFileName());
        }//end of for
        
        //４．Framework共通DAO【公開対象所属情報】検索を実行し、List[[公開対象所属]]を取得します。
        List listShozoku = getNotificationRegistUIShozokuDao().select(lastTmspDt);
        //５．SEQの文字列をキーに所属名の連結文字列を作成します。
		//５－１．変数.Map[所属名]をインスタンス化します。
        Map mapShozoku = new HashMap();
        //５－２．所属名称をMapへ格納します。
        for(int s=0; s<listShozoku.size(); s++) {	
        	//for-1.対象インデックス行のエンティティ[関連文書]を取得します。
        	TrnControlShozoku entity = (TrnControlShozoku)listShozoku.get(s);
        	//for-2.変数.対象キーとして、処理for-1の[公開対象所属].SEQの値を設定します。
        	settingMap(entity.getRegDate()+entity.getSeq(), mapShozoku, entity.getShozokuName());
        }//end of for
        
        //６．DAO【公開対象業態支部】検索を実行し、List[[公開対象業態支部]]を取得します。
        List listPublicSibu = getNotificationRegistTrnOpenSibuDao().select(lastTmspDt);
        //７．SEQの文字列をキーに業態別個別(支部・エリア大))名称の連結文字列を作成します。
        //７－１．変数.Map[業態別個別名]をインスタンス化します。
        Map mapPublicSibu = new HashMap();
        //７－２．業態別個別名称をMapへ格納します。
        for(int s=0; s<listPublicSibu.size(); s++) {	
        	TrnOpenSibu entity = (TrnOpenSibu)listPublicSibu.get(s);
        	//for-2.変数.対象キーとして、処理for-1の[公開対象所属].SEQの値を設定します。
        	settingMap(entity.getRegDate()+entity.getSeq()+entity.getGyotaiKbn(), mapPublicSibu, entity.getKobetsuName());
        }//end of for
        
        //８．処理１で取得したList[[本日登録分データ]]の各行へ関連文書と各公開対象情報を設定します。
        for (int rowNo =0; rowNo<listTodayInsertData.size(); rowNo++) {
        	//for-１．対象インデックス行のエンティティ[本日登録分データ]を取得する。
            UIHoldOpenTargetInfo eView = (UIHoldOpenTargetInfo) listTodayInsertData.get(rowNo);
            //for-2．変数.対象キー生成し、処理for-1の[本日登録分データ].SEQの値を設定します。
            String key = eView.getRegDate()+eView.getSeq();
            //for-3．[本日登録分データ].リンクファイル名へ
            //       処理３－１の変数.Map[関連文書情報]から変数.対象キーをキーとして取得した値を設定します。
            eView.setLinkFiles((String)mapKanren.get(key));
            eView.setShozokuName((String)mapShozoku.get(key));

            key = key+eView.getGyotaiKbn();
            //for-6.[本日登録分データ].業態別設定有無＝”有”の場合は
            if ("有".equals(eView.getKobetsuUmu()) ) {
                //      [本日登録分データ].業態別個別名へ
                //       処理９－１の変数.Map[業態別個別名]から変数.対象キーをキーとして取得した値を設定します。
            	eView.setPubSibuName((String)mapPublicSibu.get(key));
            }
            //for-7.[本日登録分データ].個店別指定店舗数が”有”で無い場合は
            if ("有".equals(eView.getMiseUmu()) ==false ) {
                //      [本日登録分データ].個店別指定店舗数へnull値を設定します。
            	eView.setKotenCnt(null);
            }
        }//end of for
        
        //９．CSV出力データをリターンします。
        return listTodayInsertData;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#validate(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public void validate(CsvOutputDto csvOutputDto) {
		if(csvOutputDto == null) {
			throw new MissingDataException("CSV作成用DTO");
		}
		if(getSysDate(csvOutputDto) == null) {
			throw new MissingDataException("システム日付");
		}
	}
	/**
	 * 
	 * @param key　対象キー
	 * @param map　連結文字保持用Map
	 * @param word 連結対象文字
	 * @return
	 */
	private void settingMap(String key, Map map, String word) {
    	//for-3.変数.連結文字列をStringの""(空：ブランク)で生成します。
    	String words = "";
    	//for-4.処理３－１で生成したMap[連結文字]に
    	//      キーとして変数.対象キーが存在する場合は、その値の文字列の後ろに”、”を連結させ
    	//      その値を変数.関連文書連結文字列へ設定します。
    	if(map.containsKey(key)) {
    		words = (String)map.get(key)+",";
    	}
    	//for-5.変数.関連文書連結文字列の後ろに現行[関連文書].ファイル名称を連結します。
    	words += word.trim();
		//for-6.Map[関連文書情報]格納へ変数.対象キーをキーに、
		//      変数.関連文書連結文字列を格納します。
		map.put(key, words);

	}
	/**
	 * システム日付取得処理
	 * 
	 * @param csvOutputDto
	 * @return
	 */
	private String getSysDate(CsvOutputDto csvOutputDto) {
		return ((CsvDownloadDto)csvOutputDto).getSysDate();
	}
	/**
	 * @return クラス変数notificationRegistTrnOpenSibuDao を戻します。
	 */
	public TrnOpenSibuDao getNotificationRegistTrnOpenSibuDao() {
		return notificationRegistTrnOpenSibuDao;
	}

	/**
	 * @param notificationRegistTrnOpenSibuDao を クラス変数notificationRegistTrnOpenSibuDaoへ設定します。
	 */
	public void setNotificationRegistTrnOpenSibuDao(
			TrnOpenSibuDao notificationRegistTrnOpenSibuDao) {
		this.notificationRegistTrnOpenSibuDao = notificationRegistTrnOpenSibuDao;
	}

	/**
	 * @return クラス変数uiHoldOpenTargetInfoDao を戻します。
	 */
	public UIHoldOpenTargetInfoDao getUiHoldOpenTargetInfoDao() {
		return uiHoldOpenTargetInfoDao;
	}

	/**
	 * @param uiHoldOpenTargetInfoDao を クラス変数uiHoldOpenTargetInfoDaoへ設定します。
	 */
	public void setUiHoldOpenTargetInfoDao(
			UIHoldOpenTargetInfoDao uiHoldOpenTargetInfoDao) {
		this.uiHoldOpenTargetInfoDao = uiHoldOpenTargetInfoDao;
	}

	/**
	 * @return クラス変数notificationUIKanrenBunshoDao を戻します。
	 */
	public UIKanrenBunshoDao getNotificationUIKanrenBunshoDao() {
		return notificationUIKanrenBunshoDao;
	}

	/**
	 * @param notificationUIKanrenBunshoDao を クラス変数notificationUIKanrenBunshoDaoへ設定します。
	 */
	public void setNotificationUIKanrenBunshoDao(
			UIKanrenBunshoDao notificationUIKanrenBunshoDao) {
		this.notificationUIKanrenBunshoDao = notificationUIKanrenBunshoDao;
	}

	/**
	 * @return クラス変数notificationRegistUIShozokuDao を戻します。
	 */
	public UIShozokuDao getNotificationRegistUIShozokuDao() {
		return notificationRegistUIShozokuDao;
	}

	/**
	 * @param notificationRegistUIShozokuDao を クラス変数notificationRegistUIShozokuDaoへ設定します。
	 */
	public void setNotificationRegistUIShozokuDao(
			UIShozokuDao notificationRegistUIShozokuDao) {
		this.notificationRegistUIShozokuDao = notificationRegistUIShozokuDao;
	}
	

}
