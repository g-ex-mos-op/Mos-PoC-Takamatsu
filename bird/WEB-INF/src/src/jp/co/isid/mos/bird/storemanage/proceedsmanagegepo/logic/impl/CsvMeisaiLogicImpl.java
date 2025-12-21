package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlKaikeiKbnInfoDao;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlSyukeiKbnDao;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlKaikeiKbnInfo;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.common.kaikei.entity.GetterMethod;
import jp.co.isid.mos.bird.common.kaikei.entity.TrnAridakaMeisai;
import jp.co.isid.mos.bird.common.kaikei.util.KaikeiUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.TrnAridakaMeisaiDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.PointInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UserCompanyInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.PointLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.ProceedsManageGepoLogic;

/**
 * 明細一括ダウンロード
 *
 * @author xjung
 */
public class CsvMeisaiLogicImpl  implements CsvOutputLogic {
	/** 明細一括ダウンロードロジックID */
    public static final String LOGIC_ID = "BSM013L07";

    /** 日付フォーマッタ */
    private static final DateFormatter df = new DateFormatter();

    /** 会計集計区分情報取得ロジック */
    private CtlSyukeiKbnDao ctlSyukeiKbnDao;

    /** 会計区分情報取得ロジック */
    private CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao;

    /** 会計区分別在高取得ロジック */
    private TrnAridakaMeisaiDao trnAridakaMeisaiDao;

    /** 対象店舗情報取得ロジック */
    MstMiseInfoLogic mstMiseInfoLogic;

// add 2020/05/08 USI張  begin
    /** 売上金管理月報情報取得ロジック */
    private ProceedsManageGepoLogic proceedsManageGepoLogic;

    /** dポイント、株主優待モスポイント情報取得ロジック */
    PointLogic pointLogic;
// add 2020/05/08 USI張  end
	/**
	 * CSVファイル名称を取得する
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	String 			CSVファイル名称
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
        ProceedsManageGepoDto dao = (ProceedsManageGepoDto) csvOutputDto;
        // CSVファイル名称
        StringBuffer sb = new StringBuffer();
        sb.append(ProceedsConstants.CSV_MEISAI_FILE_NAME );
        sb.append(dao.getOldTaishoYM());
        sb.append(ProceedsConstants.CSV_EXTENSION);

   		return sb.toString();
	}

    /**
     * 入力チェックをする
     * @param csvOutputDto  CSV出力DTO
     * 更新日:2014/12/12 周春建　会計区分拡張対応
     */
    public void validate(CsvOutputDto csvOutputDto) {

        // 売上金管理月報情報DTO取得
        ProceedsManageGepoDto dto = (ProceedsManageGepoDto) csvOutputDto;

        // １．DTO【Session情報】．検索ﾌﾗｸﾞへfalse(検索不可)を設定する
        dto.setSearchFlg(false);

        // 必須入力チェック
        if (dto.getUserId() == null || dto.getUserId().trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_USER_ID);
        }

        if (dto.getUserType() == null || dto.getUserType().trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_USER_ID);
        }


        // 1.Dao【会計集計区分情報】．検索を実行し、戻り値List[[集計区分情報]]を取得する
        List syukeiKbnList = getCtlSyukeiKbnDao().select(ProceedsConstants.BIRD_DISP_FLG_ON);

        if (syukeiKbnList == null || syukeiKbnList.isEmpty()) {
        	throw new NoResultException();
        }

        // 2.Dao【会計区分情報】．検索を実行し、戻り値List[[会計区分情報]]を取得する
        //List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(ProceedsConstants.BIRD_DISP_FLG_ON);
        // 必須入力チェック
        if (dto.getOldCompanyCd() == null || dto.getOldCompanyCd().trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD);
        }
        //会計区分マスタの抽出条件には会社コードも含める
        List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(ProceedsConstants.BIRD_DISP_FLG_ON,dto.getOldCompanyCd());

        if (kaikeiKbnList == null || kaikeiKbnList.isEmpty()) {
        	throw new NoResultException();
        }

        // 3．変数．Map[集計区分別会計区分マスタ情報]を生成する
        Map kaikeiInfoMaster = KaikeiUtil.makeKaikeiSyukeiKbnInfo(syukeiKbnList, kaikeiKbnList);
        dto.setKaikeiMasterInfo(kaikeiInfoMaster);

//        // 必須入力チェック
//        if (dto.getOldCompanyCd() == null || dto.getOldCompanyCd().trim().length() == 0) {
//            throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD);
//        }

        String taisyoTenpoCd = dto.getOldTaishoTenpoCd();
        if (ProceedsConstants.HONBU.equals(dto.getUserType()) &&
                (taisyoTenpoCd == null || taisyoTenpoCd.trim().length() == 0)) {
            throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO);
        } else if (taisyoTenpoCd == null || taisyoTenpoCd.trim().length() == 0) {
            dto.setOldTaishoTenpoCd(ProceedsConstants.ZENTEN_CD);
        } else {
            CodeVerifier codeVeri = new CodeVerifier(true);
            // コードフォーマットチェック
            if(!codeVeri.validate(taisyoTenpoCd) || taisyoTenpoCd.length() > 5) {
                throw new InvalidInputException(ProceedsConstants.MSG_TAISHO_TENPO, "taishoTenpoCd", 0);
            }
            CodeFormatter cdf = new CodeFormatter(5, "00000");
            cdf.setFormatPattern("00000");
            dto.setOldTaishoTenpoCd(cdf.format(taisyoTenpoCd, true));
        }

        if (dto.getOldTaishoYM() == null || dto.getOldTaishoYM().trim().length() == 0) {
            throw new NotNullException(ProceedsConstants.MSG_TAISHO_YM);
        }
    }

	/**
	 * CSV出力データを作成する
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	List 			CSV出力データリスト
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

        // 売上金管理月報情報DTO取得
        ProceedsManageGepoDto dto = (ProceedsManageGepoDto) csvOutputDto;

        // １．DTO【Session情報】．検索ﾌﾗｸﾞへfalse(検索不可)を設定する
        dto.setSearchFlg(false);

        //２．Dao【会計区分別在高】．検索を実行し、対象店舗のList[[会計区分別在高]]を取得します。
        List result = getTrnAridakaMeisaiDao().select(dto.getSysDate(), dto.getUserId(), dto.getUserType()
                                                    , dto.getOldCompanyCd(),dto.getOldTaishoTenpoCd(),dto.getOldTaishoYM()
                                                    , dto.isLimitKbn());

// add 2020/05/08 USI張  begin
        // 売上金管理月報情報取得
        Map manageResult = getProceedsManageGepoLogic().execute(
                        dto.getUserType(),
                        dto.getUserId(),
                        dto.isLimitKbn(),
                        dto.getOnerCd(),
                        dto.getOldCompanyCd(),
                        dto.getOldTaishoTenpoCd(),
                        dto.getOldTaishoYM());

        // 売上金管理月報情報リスト
        List manageList = (List) manageResult.get(ProceedsConstants.MAP_RST_LIST);

        //３．ロジック【dポイント、株主優待モスポイント情報取得】を実行し、dポイント、株主優待モスポイント情報リストを取得します。
        List pointResult =getPointLogic().execute(
                dto.getUserType(),
                dto.getUserId(),
                dto.isLimitKbn(),
                dto.getOnerCd(),
                dto.getOldCompanyCd(),
                dto.getOldTaishoTenpoCd(),
                dto.getOldTaishoYM(),
                manageList);
// add 2020/05/08 USI張  end

        // 件数が0件の場合
        if (result == null || result.isEmpty()) {
            throw new NoResultException();
        }
        // 合計のみの場合
        if (ProceedsCommon.isNull(((TrnAridakaMeisai)result.get(0)).getEigyoDt())){
            throw new NoResultException();
        }

        // ４．新しくList[[CSV出力用リスト]]を生成します
        List outputList = new ArrayList();

        // ５．条件項目ヘッダ作成
        outputList = getHeaderData(dto);

        // ６．明細部ヘッダー作成
        outputList.addAll(getMeisaiHeader(dto));

        // ７．明細部データ作成
// modify 2020/05/08 USI張  begin
//        outputList.addAll(getMeisaiData(dto, result));
        outputList.addAll(getMeisaiData(dto, result, pointResult));
// modify 2020/05/08 USI張  end
        // ８．DTO【Session情報】．検索ﾌﾗｸﾞへtrue(検索可)を設定する
        dto.setSearchFlg(true);

		return outputList;
	}

	/**
	 * ヘッダー部情報を作成する
	 * @param dto             売上金管理月報DTO
	 * @return	List           ヘッダー部情報リスト
	 */
	public List getHeaderData(ProceedsManageGepoDto dto) {
		// ヘッダー部情報リスト
		List headerList = new ArrayList();

        //１．List[[条件１行目]]を生成し、下記の会社出力情報を格納します。List[[CSV出力用リスト]]へ格納します。
        List head1stList = new ArrayList();
        head1stList.add(ProceedsConstants.CSV_HD_COMPANY);
        for (Iterator it = dto.getCompanyList().iterator(); it.hasNext();) {
            UserCompanyInfo info = (UserCompanyInfo) it.next();
            if (dto.getOldCompanyCd().equals(info.getCompanyCd())) {
                head1stList.add(ProceedsCommon.setEmpty(info.getCompanyName()));
            }
        }

        headerList.add(head1stList);

        // ２行目:対象店舗
        String taisyoTenpoName = "";
        String taishoTenpoCd = dto.getTaishoTenpoCd();


        if (ProceedsConstants.ZENTEN_CD.equals(taishoTenpoCd)) {
            // DTO【Session情報】．検索済対象店舗＝”99999”（ProceedsConstants.ZENTEN_CD）の場合は、全店を設定
            taisyoTenpoName = ProceedsConstants.ZENTEN_NAME;
        } else {
            taisyoTenpoName = getMstMiseInfoLogic().getMiseCdName(dto.getOldCompanyCd(), taishoTenpoCd);
        }
        List head2ndList = new ArrayList();
        head2ndList.add(ProceedsConstants.CSV_HD_TAISHO_TENPO);
        head2ndList.add(ProceedsCommon.setEmpty(taisyoTenpoName));

        headerList.add(head2ndList);

        // ３行目:対象年月
        List head3rdList = new ArrayList();
        head3rdList.add(ProceedsConstants.CSV_HD_TAISHO_YM);
        head3rdList.add(df.format(dto.getOldTaishoYM(),
            DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM));

        headerList.add(head3rdList);
        // ４行目
        List head4thList = new ArrayList();

        headerList.add(head4thList);

        return headerList;
	}

    /**
     * 明細ヘッダー部情報を作成する
     * @param dto 売上金管理月報DTO
     * @return 明細ヘッダー部情報リスト
     */
    private List getMeisaiHeader(ProceedsManageGepoDto dto) {
        // 明細ヘッダー部情報リスト
        List meisaiHeaderList = new ArrayList();

        // ヘッダー１行目：集計区分名称
        List head1List = new ArrayList();
        head1List.add(ProceedsConstants.CSV_MEISAI_DT_SYUKEIKBN_NAME);

        // ヘッダー２行：営業日(会計区分)
        List head2List = new ArrayList();
        head2List.add(ProceedsConstants.CSV_DT_EIGYO_DT);

        Map kaikeiMasterInfo = dto.getKaikeiMasterInfo();
        for (Iterator ite = dto.getSyukeiKbnList().iterator(); ite.hasNext();) {
            CtlSyukeiKbn syukeiKbn = (CtlSyukeiKbn)ite.next();
            // 表示対象の場合に処理
            if (kaikeiMasterInfo.containsKey(syukeiKbn.getSyukeiCd())) {
                // 集計区分名称を格納
                head1List.add(syukeiKbn.getSyukeiName());
                // 会計区分
                List kaikeiKbnList = ((UILists)kaikeiMasterInfo.get(syukeiKbn.getSyukeiCd())).getListData();
                // 会計区分数分×２＋１の””（空）を格納
                int emptyNum = (kaikeiKbnList.size() * 2) + 1;
                for (int i = 0; i < emptyNum; i++) {
                    head1List.add(ProceedsConstants.EMPTY);
                }

                // 会計区分名処理
                for (Iterator ite2 = kaikeiKbnList.iterator(); ite2.hasNext();) {
                    CtlKaikeiKbnInfo kaikeiKbn = (CtlKaikeiKbnInfo)ite2.next();
                    head2List.add(kaikeiKbn.getKkbnName() + ProceedsConstants.CSV_DT_KEN_NAME);
                    head2List.add(kaikeiKbn.getKkbnName() + ProceedsConstants.CSV_DT_KIN_NAME);
                }
                head2List.add(ProceedsConstants.CSV_MEISAI_DT_GOUKEIKEN);
                head2List.add(ProceedsConstants.CSV_MEISAI_DT_GOUKEIKIN);
            }
        }

// add 2020/05/07 USI張 begin
        //List[[ヘッダー１行目]]へ”dポイント”の文字列(ProceedsConstants.CSV_DT_POINT)を格納します。
        head1List.add(ProceedsConstants.CSV_DT_POINT);
        //List[[ヘッダー2行目]]へ”進呈（単位:ポイント）”文字列(ProceedsConstants.CSV_DT_POINT_PRT)を格納します。
        head2List.add(ProceedsConstants.CSV_DT_POINT_PRT);
// add 2020/05/07 USI張 end

// add 2021/09/03 USI戚 begin
        //List[[ヘッダー１行目]]へ”dポイントネット注文利用”の文字列(ProceedsConstants.CSV_DT_POINT_NET)を格納します。
        head1List.add(ProceedsConstants.CSV_DT_POINT_NET);
        //List[[ヘッダー2行目]]へ”進呈（単位:ポイント）”文字列(ProceedsConstants.CSV_DT_POINT_NET_PRT)を格納します。
        head2List.add(ProceedsConstants.CSV_DT_POINT_NET_PRT);
// add 2021/09/03 USI戚 end

// add 2020/06/09 USI張 begin
        //List[[ヘッダー１行目]]へ”株主優待券”の文字列(ProceedsConstants.CSV_DT_POINT)を格納します。
        head1List.add(ProceedsConstants.CSV_DT_KABUPOINT);
        //List[[ヘッダー2行目]]へ”株主優待券チャージ金額”文字列(ProceedsConstants.CSV_DT_KABUPOINT_PRT)を格納します。
        head2List.add(ProceedsConstants.CSV_DT_KABUPOINT_PRT);
// add 2020/06/09 USI張 end
        meisaiHeaderList.add(head1List);
        meisaiHeaderList.add(head2List);



        return meisaiHeaderList;
    }
// modify 2020/05/08 USI張  begin
//    private List getMeisaiData(ProceedsManageGepoDto dto, List result) {
    private List getMeisaiData(ProceedsManageGepoDto dto, List result, List pointResult) {
// modify 2020/05/08 USI張  end
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter numF = new NumericFormatter();

        // 明細データ部情報リスト
        List meisaiDataList = new ArrayList();


        //１．変数．Map[集計区分対象メソッド]を生成します。
        Map mapBd30GetterMtd = new HashMap();

        // ２．対象メソッドMap作成。DTO【Session情報】．List[[会計(集計)区分情報]]件数分処理
        Map kaikeiMasterInfo = dto.getKaikeiMasterInfo();
        for (Iterator ite = dto.getSyukeiKbnList().iterator(); ite.hasNext();) {
            CtlSyukeiKbn syukeiKbn = (CtlSyukeiKbn)ite.next();
            // 表示対象の場合に処理
            if (kaikeiMasterInfo.containsKey(syukeiKbn.getSyukeiCd())) {

                // DTO【Session情報】．Map[集計区分別会計区分情報]からList[[会計区分]]を取得します。
                List kaikeiKbnList = ((UILists)kaikeiMasterInfo.get(syukeiKbn.getSyukeiCd())).getListData();

                // Entity[会計区分別在高]クラスのメソッドオブジェクト変数．Method配列[会計区分別在高]を取得する。
                List listBD30GetMethod = KaikeiUtil.getListGetterMethod(kaikeiKbnList, TrnAridakaMeisai.class, "Kaikei");

                // 集計区分をキーに集計区分対象メソッド格納
                mapBd30GetterMtd.put(syukeiKbn.getSyukeiCd(),listBD30GetMethod);
            }
        }

        // ３．List[[会計区分在高]]の件数分処理
        for (Iterator ite2 = result.iterator(); ite2.hasNext();) {
            // List[[会計区分在高]]から現行行Entity[会計区分在高]を取得します。
            TrnAridakaMeisai meisaiData = (TrnAridakaMeisai)ite2.next();

            // List[[CSV用会計区分在高]]を生成します。
            List rowList = new ArrayList();
            // 営業日(List[[CSV用会計区分在高]]へEntity[会計区分在高]（dd日（E)）のフォーマット値を格納します。)
            if (ite2.hasNext()) {
                rowList.add(df.format(meisaiData.getEigyoDt(),
                    ProceedsConstants.PATTERN_DAY_WEEK_KJ, DateFormatter.DATE_TYPE_YMD));
            } else {
                // 最後は"合計"
                rowList.add(ProceedsConstants.LABEL_SUM);
            }

            // DTO【Session情報】．List[[会計(集計)区分情報]]件数分下記の処理を行います。
            for (Iterator ite3 = dto.getSyukeiKbnList().iterator(); ite3.hasNext();) {
                // DTO【Session情報】．List[[会計(集計)区分情報]]から現行行Entity[会計(集計)区分情報]を取得します。
                CtlSyukeiKbn syukeiKbn = (CtlSyukeiKbn)ite3.next();
                // 表示対象の場合に処理
                if (kaikeiMasterInfo.containsKey(syukeiKbn.getSyukeiCd())) {

                    //９．変数．Map[修正件数Getterメソッド]、変数．Map[修正金額Getterメソッド]を生成します。
                    List listBd30KenGetterMtd = (List)mapBd30GetterMtd.get(syukeiKbn.getSyukeiCd());

                    //変数.合計件数、変数.合計金額を生成。
                    BigDecimal totalKen = new BigDecimal("0");
                    BigDecimal totalKin = new BigDecimal("0");

                    // Method配列[対象会計区分件数]の件数分合算処理を行います。
                    for (int i = 0; i< listBd30KenGetterMtd.size(); i++) {
                        GetterMethod getterMtd = (GetterMethod)listBd30KenGetterMtd.get(i);
                        // 変数．Map[修正件数Getterメソッド]からList[[会計区分]]会計区分をキーに取得した[Method]を実行した値
                        BigDecimal ken = new BigDecimal("0");
                        BigDecimal kin = new BigDecimal("0");
                        try {
                            // 各Getterメソッドを実行し件数を取得します。
                            Method getRevisedKenMethod = (Method)getterMtd.getMethodKen();
                            ken = (BigDecimal)getRevisedKenMethod.invoke(meisaiData, new Object[]{});
                            // 各Getterメソッドを実行し金額を取得します。
                            Method getRevisedKinMethod = (Method)getterMtd.getMethodKin();
                            // 変数．Map[修正件数Getterメソッド]からList[[会計区分]]会計区分をキーに取得した[Method]を実行した値
                            kin = (BigDecimal)getRevisedKinMethod.invoke(meisaiData, new Object[]{});
                        } catch (Exception e) {
                            throw new FtlSystemException("売上金管理月報画面：明細一括ダウンロード", null, e);
                        }
                        rowList.add(numF.format(ProceedsCommon.setBicEmpty(ken)));
                        rowList.add(numF.format(ProceedsCommon.setBicEmpty(kin)));
                        // 変数.合計件数計算
                        totalKen = totalKen.add(ken);
                        //変数.合計金額計算
                        totalKin = totalKin.add(kin);

                    }
                    // 合計値を格納します。
                    rowList.add(numF.format(ProceedsCommon.setBicEmpty(totalKen)));
                    rowList.add(numF.format(ProceedsCommon.setBicEmpty(totalKin)));
                }
            }

            meisaiDataList.add(rowList);
        }

// add 2020/05/07 USI張 begin
        // List[[dポイント、株主優待モスポイントデタ]]から現行行Entity[dポイント、株主優待モスポイントデータ]を取得します
        for (int i = 0; i < pointResult.size(); i++) {
        	List optList = (List)meisaiDataList.get(i);

        	//List[[CSV出力用リスト]]現行行の最後に現行行Entity[dポイント、株主優待モスポイントデタ．ポイント数計]を追加します
        	PointInfo pointInfo = (PointInfo) pointResult.get(i);
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(pointInfo.getPointKei())));
// add 2021/09/03 USI戚 begin
        	//List[[CSV出力用リスト]]現行行に現行行Entity[dポイント、株主優待モスポイントデタ．ポイント(ネット)数計]を追加します。
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(pointInfo.getPointNetKei())));
// add 2021/09/03 USI戚 end
// add 2020/06/09 USI張 begin
        	//List[[CSV出力用リスト]]現行行に現行行Entity[dポイント、株主優待モスポイントデタ．株主優待券チャージ金額]を追加します。
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(pointInfo.getYChargeKin())));
// add 2020/06/09 USI張 end
        }
// add 2020/05/07 USI張 end

        return meisaiDataList;
    }


    /**
     * 会計区分情報取得ロジックを戻します。
     * @return ctlKaikeiKbnInfoDao 会計区分情報取得ロジック
     */
    public CtlKaikeiKbnInfoDao getCtlKaikeiKbnInfoDao() {
        return ctlKaikeiKbnInfoDao;
    }

    /**
     * 会計区分情報取得ロジックを設定します。
     * @param ctlKaikeiKbnInfoDao 会計区分情報取得ロジック
     */
    public void setCtlKaikeiKbnInfoDao(CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao) {
        this.ctlKaikeiKbnInfoDao = ctlKaikeiKbnInfoDao;
    }

    /**
     * 会計集計区分情報取得ロジックを戻します。
     * @return ctlSyukeiKbnDao 会計集計区分情報取得ロジック
     */
    public CtlSyukeiKbnDao getCtlSyukeiKbnDao() {
        return ctlSyukeiKbnDao;
    }

    /**
     * 会計集計区分情報取得ロジックを設定します。
     * @param ctlSyukeiKbnDao 会計集計区分情報取得ロジック
     */
    public void setCtlSyukeiKbnDao(CtlSyukeiKbnDao ctlSyukeiKbnDao) {
        this.ctlSyukeiKbnDao = ctlSyukeiKbnDao;
    }

    /**
     * 会計区分別在高取得ロジックを戻します。
     * @return trnAridakaMeisaiDao 会計区分別在高取得ロジック
     */
    public TrnAridakaMeisaiDao getTrnAridakaMeisaiDao() {
        return trnAridakaMeisaiDao;
    }

    /**
     * 会計区分別在高取得ロジックを設定します。
     * @param trnAridakaMeisaiDao 会計区分別在高取得ロジック
     */
    public void setTrnAridakaMeisaiDao(TrnAridakaMeisaiDao trnAridakaMeisaiDao) {
        this.trnAridakaMeisaiDao = trnAridakaMeisaiDao;
    }

    /**
     * 対象店舗情報取得ロジックを戻します。
     * @return mstMiseInfoLogic 対象店舗情報取得ロジック
     */
    public MstMiseInfoLogic getMstMiseInfoLogic() {
        return mstMiseInfoLogic;
    }

    /**
     * 対象店舗情報取得ロジックを設定します。
     * @param mstMiseInfoLogic 対象店舗情報取得ロジック
     */
    public void setMstMiseInfoLogic(MstMiseInfoLogic mstMiseInfoLogic) {
        this.mstMiseInfoLogic = mstMiseInfoLogic;
    }

// add 2020/05/07 USI張  begin
    /**
     * 売上金管理月報情報取得ロジックを戻します。
     * @return proceedsManageGepoLogic 売上金管理月報情報取得ロジック
     */
	public ProceedsManageGepoLogic getProceedsManageGepoLogic() {
		return proceedsManageGepoLogic;
	}

    /**
     * 売上金管理月報情報取得ロジックを設定します。
     * @param proceedsManageGepoLogic 売上金管理月報情報取得ロジック
     */
	public void setProceedsManageGepoLogic(ProceedsManageGepoLogic proceedsManageGepoLogic) {
		this.proceedsManageGepoLogic = proceedsManageGepoLogic;
	}

    /**
     * dポイント、株主優待モスポイント情報取得ロジックを戻します。
     * @return pointLogic dポイント、株主優待モスポイント情報取得ロジック
     */
	public PointLogic getPointLogic() {
		return pointLogic;
	}

    /**
     * dポイント、株主優待モスポイント情報取得ロジックを設定します。
     * @param pointLogic dポイント、株主優待モスポイント情報取得ロジック
     */
	public void setPointLogic(PointLogic pointLogic) {
		this.pointLogic = pointLogic;
	}
// add 2020/05/07 USI張  end
}