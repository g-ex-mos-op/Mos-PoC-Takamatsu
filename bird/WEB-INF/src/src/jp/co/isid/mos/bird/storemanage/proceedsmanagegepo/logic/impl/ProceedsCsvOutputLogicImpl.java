package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.NebikiInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.PointInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.ProceedsManageGepoInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UserCompanyInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.NebikiLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.PointLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.ProceedsManageGepoLogic;

/**
 * 売上金管理月報情報CSVダウンロードロジック
 *
 * @author xjung
 */
public class ProceedsCsvOutputLogicImpl  implements CsvOutputLogic {
	/** 売上金管理月報情報CSVダウンロードロジックID */
    public static final String LOGIC_ID = "BSM013L03";

    /** 日付フォーマッタ */
    private static final DateFormatter df = new DateFormatter();

    /** 対象店舗情報取得ロジック */
    private MstMiseInfoLogic mstMiseInfoLogic;

	/** 売上金管理月報情報取得ロジック */
    private ProceedsManageGepoLogic proceedsManageGepoLogic;

    /** 値引情報ロジック */
    private NebikiLogic nebikiLogic;

// add 2020/05/07 USI張  begin
    /** dポイント、株主優待モスポイント情報取得ロジック */
    private PointLogic pointLogic;
// add 2020/05/07 USI張  end

	/**
	 * CSVファイル名称を取得する
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	String 			CSVファイル名称
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
        ProceedsManageGepoDto dao = (ProceedsManageGepoDto) csvOutputDto;
        // CSVファイル名称
        StringBuffer sb = new StringBuffer();
        sb.append(ProceedsConstants.CSV_FILE_NAME );
        sb.append(dao.getOldTaishoYM());
        sb.append(ProceedsConstants.CSV_EXTENSION);

   		return sb.toString();
	}

    /**
     * 入力チェックをする
     * @param csvOutputDto  CSV出力DTO
     */
    public void validate(CsvOutputDto csvOutputDto) {
        // 処理なし
    }

	/**
	 * CSV出力データを作成する
	 * @param	csvOutputDto	CSV出力DTO
	 * @return	List 			CSV出力データリスト
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
        // 数値タイプの文字列フォーマッタ(定数)
        NumericFormatter numF = new NumericFormatter();
        // 数値タイプの文字列フォーマッタ(定数) 2013/02 追加 海外売上集信対応
        NumericFormatter formatterUriage = new NumericFormatter(true, 0, true);

        // 売上金管理月報情報DTO取得
        ProceedsManageGepoDto dto = (ProceedsManageGepoDto) csvOutputDto;

        // 検索ﾌﾗｸﾞ設定
        dto.setSearchFlg(false);

        // 対象店舗情報
        String taishoTenpoInfo = "";
        if(ProceedsConstants.ZENTEN_CD.equals(dto.getOldTaishoTenpoCd())) {
        	taishoTenpoInfo = ProceedsConstants.ZENTEN_NAME;
        }
        else {
	        taishoTenpoInfo = getMstMiseInfoLogic().getMiseCdName(
	                                    dto.getOldCompanyCd(),
	                                    dto.getOldTaishoTenpoCd());
        }

        // 売上金管理月報情報取得
        Map result = getProceedsManageGepoLogic().execute(
                        dto.getUserType(),
                        dto.getUserId(),
                        dto.isLimitKbn(),
                        dto.getOnerCd(),
                        dto.getOldCompanyCd(),
                        dto.getOldTaishoTenpoCd(),
                        dto.getOldTaishoYM());
        // 検索ﾌﾗｸﾞ設定
        dto.setSearchFlg(true);

        //begin add 2019/09/17 xou.zoubun 軽減税率項目の追加
        //軽減税率開始日から軽減税率項目の追加表示
        SimpleDateFormat sdf = new SimpleDateFormat(DateFormatter.PATTERN_MONTH);
        String keigenStartFlg = "0";
        try {
            Date taisyoYM = sdf.parse(dto.getOldTaishoYM());
            if (!taisyoYM.before(sdf.parse(ProceedsConstants.URIAGE_MEISAI_MIN_MONTH))) {
                keigenStartFlg = "1";
            }
        } catch (ParseException e) {
            // 必要なし
        }
        //end add 2019/09/17 xou.zoubun 軽減税率項目の追加

        // 売上金管理月報情報リスト
        List rstList = (List) result.get(ProceedsConstants.MAP_RST_LIST);

        // 値引情報取得
        Map nebikiResult = getNebikiLogic().execute(
        		dto.getUserType(),
                dto.getUserId(),
                dto.isLimitKbn(),
                dto.getOnerCd(),
                dto.getOldCompanyCd(),
                dto.getOldTaishoTenpoCd(),
                dto.getOldTaishoYM(), rstList);



        //値引情報リスト設定
        List nebikiRst = (List) nebikiResult.get(ProceedsConstants.MAP_NEBIKI_RST_LIST);

// add 2020/05/07 USI張 begin
        // dポイント、株主優待モスポイント情報取得
        List pointResult = getPointLogic().execute(
        		dto.getUserType(),
                dto.getUserId(),
                dto.isLimitKbn(),
                dto.getOnerCd(),
                dto.getOldCompanyCd(),
                dto.getOldTaishoTenpoCd(),
                dto.getOldTaishoYM(), rstList);
// add 2020/05/07 USI張 end

        // CSV出力データリスト、ヘッダー部情報取得
        List outputList = getHeaderData(dto, result, nebikiResult,  taishoTenpoInfo, keigenStartFlg);


        // データ部情報取得
        for (int i = 0; i < rstList.size(); i++) {
        	List rowList = new ArrayList();
            // 売上金管理月報情報
            ProceedsManageGepoInfo info = (ProceedsManageGepoInfo) rstList.get(i);
            // 営業日
            rowList.add(df.format(info.getEigyoDt(),
                ProceedsConstants.PATTERN_DAY_WEEK_KJ, DateFormatter.DATE_TYPE_YMD));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getUriage())));      // 売上
            if ("1".equals(keigenStartFlg)) {
                rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getUriage1())));     // 通常税率対象売上
                rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getUriage2())));     // 軽減税率対象売上
            }
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebiki())));      // 値引合計
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTax())));         // 消費税
            if ("1".equals(keigenStartFlg)) {
                rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTax1())));        // 通常税率対象消費税
                rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTax2())));        // 軽減税率対象消費税
            }
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getGcHanKin())));    // 金券販売等
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getUrikakeKen())));  // 前受・売掛件数
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getUrikakeKin())));  // 前受・売掛金額
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getGcUriKin())));    // Ｇｶｰﾄﾞ回収
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getInvKin())));      // 招待券回収
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getGenkin())));      // 現金合計
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getAridakaJitu())));  // 現金在高
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKajou())));       // 過剰
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getFusoku())));      // 不足
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKyakusu())));     // 客数
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getCanKen())));      // 売上取消件数
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getCanKin())));      // 売上取消金額
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getAllcanKen())));   // ｵｰﾀﾞｰ票ｷｬﾝｾﾙ件数
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getAllcanKin())));   // ｵｰﾀﾞｰ票ｷｬﾝｾﾙ金額
            // 会計区分４～１１の件数・金額
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen4())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin4())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen5())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin5())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen6())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin6())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen7())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin7())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen8())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin8())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen9())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin9())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen10())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin10())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKen11())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getKaikeiKin11())));
            // チケット１～１５の販売件数・金額
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen1())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin1())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen2())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin2())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen3())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin3())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen4())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin4())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen5())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin5())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen6())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin6())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen7())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin7())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen8())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin8())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen9())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin9())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen10())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin10())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen11())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin11())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen12())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin12())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen13())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin13())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen14())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin14())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKen15())));
            rowList.add(numF.format(ProceedsCommon.setBicEmpty(info.getTieketKin15())));

            outputList.add(rowList);
        }


        // 値引データ部情報取得
        for (int i = 0; i < nebikiRst.size(); i++) {

        	List optList = (List)outputList.get(i+5);

        	// 値引情報
        	NebikiInfo info = (NebikiInfo) nebikiRst.get(i);

        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_1())));  //値引件数1
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_1())));  //値引金額1
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_1())));  //値引税額1
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_2())));  //値引件数2
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_2())));  //値引金額2
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_2())));  //値引税額2
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_3())));  //値引件数3
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_3())));  //値引金額3
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_3())));  //値引税額3
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_4())));  //値引件数4
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_4())));  //値引金額4
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_4())));  //値引税額4
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_5())));  //値引件数5
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_5())));  //値引金額5
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_5())));  //値引税額5
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_6())));  //値引件数6
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_6())));  //値引金額6
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_6())));  //値引税額6
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_7())));  //値引件数7
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_7())));  //値引金額7
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_7())));  //値引税額7
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_8())));  //値引件数8
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_8())));  //値引金額8
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_8())));  //値引税額8
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKen_9())));  //値引件数9
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiKin_9())));  //値引金額9
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(info.getNebikiTax_9())));  //値引税額9

        }

// add 2020/03/18 USI張 begin
        //List[[CSV出力用リスト]]現行行の最後に現行行Entity[dポイント、株主優待モスポイントデタ．ポイント数計]を追加します
        for (int i = 0; i < pointResult.size(); i++) {

        	List optList = (List)outputList.get(i+5);

        	// dポイント、株主優待モスポイント情報
        	PointInfo pointInfo = (PointInfo) pointResult.get(i);

        	optList.add(numF.format(ProceedsCommon.setBicEmpty(pointInfo.getPointKei())));
// add 2021/09/04 USI戚 begin
        	//List[[CSV出力用リスト]]現行行に現行行Entity[dポイント（ネット）デタ．ポイント数計]を追加します。
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(pointInfo.getPointNetKei())));
// add 2021/09/04 USI戚 end
// add 2020/06/09 USI張 begin
        	//List[[CSV出力用リスト]]現行行に現行行Entity[dポイントデタ．株主優待券チャージ金額]を追加します。
        	optList.add(numF.format(ProceedsCommon.setBicEmpty(pointInfo.getYChargeKin())));
// add 2020/06/09 USI張 end
        }
// add 2020/03/18 USI張 end
		return outputList;
	}

	/**
	 * ヘッダー部情報を作成する
	 * @param dto             売上金管理月報DTO
     * @param result          売上金管理月報情報
     * @param nebikiResult    値引情報
     * @param taishoTenpoInfo 対象店舗(コード＋名称)
     * @param keigenStartFlg  軽減税率開始フラグ
	 * @return	List           ヘッダー部情報リスト
	 */
	public List getHeaderData(ProceedsManageGepoDto dto, Map result, Map nebikiResult, String taishoTenpoInfo, String keigenStartFlg) {
		// ヘッダー部情報リスト
		List headerList = new ArrayList();

        // 会計区分名称リスト
        List syukeiKbnList = dto.getSyukeiKbnList();
        // チケット販売名称リスト
        List tckList = (List) result.get(ProceedsConstants.MAP_TCK_NAME_LIST);

        //値引区分名称リスト
        List nkbnList = (List) nebikiResult.get(ProceedsConstants.MAP_NK_NAME_LIST);

        //１行目:会社
        List head1stList = new ArrayList();
        head1stList.add(ProceedsConstants.CSV_HD_COMPANY);
        for (Iterator it = dto.getCompanyList().iterator(); it.hasNext();) {
            UserCompanyInfo info = (UserCompanyInfo) it.next();
            if (dto.getOldCompanyCd().equals(info.getCompanyCd())) {
                head1stList.add(ProceedsCommon.setEmpty(info.getCompanyName()));
            }
        }

        // ２行目:対象店舗
        List head2ndList = new ArrayList();
        head2ndList.add(ProceedsConstants.CSV_HD_TAISHO_TENPO);
        head2ndList.add(ProceedsCommon.setEmpty(taishoTenpoInfo));

        // ３行目:対象年月
        List head3rdList = new ArrayList();
        head3rdList.add(ProceedsConstants.CSV_HD_TAISHO_YM);
        head3rdList.add(df.format(dto.getOldTaishoYM(),
            DateFormatter.PATTERN_MONTH_KANJI_YM, DateFormatter.DATE_TYPE_YM));

        // ４行目
        List head4thList = new ArrayList();

        // ５行目:データ部ヘッダ作成
        List head5thList = new ArrayList();
        head5thList.add(ProceedsConstants.CSV_DT_EIGYO_DT);     // 営業日
        head5thList.add(ProceedsConstants.CSV_DT_URIAGE);       // 売上
        //begin add 2019/09/17 xou.zoubun 軽減税率項目の追加
        if ("1".equals(keigenStartFlg)) {
            head5thList.add(ProceedsConstants.CSV_DT_URIAGE1);       // 通常税率対象売上
            head5thList.add(ProceedsConstants.CSV_DT_URIAGE2);       // 軽減税率対象売上
        }
        //end add 2019/09/17 xou.zoubun 軽減税率項目の追加
        head5thList.add(ProceedsConstants.CSV_DT_NEBIKI);       // 値引合計
        head5thList.add(ProceedsConstants.CSV_DT_TAX);          // 消費税
        //begin add 2019/09/17 xou.zoubun 軽減税率項目の追加
        if ("1".equals(keigenStartFlg)) {
            head5thList.add(ProceedsConstants.CSV_DT_TAX1);         // 通常税率対象消費税
            head5thList.add(ProceedsConstants.CSV_DT_TAX2);         // 軽減税率対象消費税
        }
        //end add 2019/09/17 xou.zoubun 軽減税率項目の追加
        head5thList.add(ProceedsConstants.CSV_DT_GC_HAN_KIN);   // 金券販売等
        head5thList.add(ProceedsConstants.CSV_DT_URIKAKE_KEN);  // 前受・売掛件数
        head5thList.add(ProceedsConstants.CSV_DT_URIKAKE_KIN);  // 前受・売掛金額
        head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(1)).getSyukeiName()); // Ｇｶｰﾄﾞ回収
        head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(0)).getSyukeiName()); // 招待券回収
        head5thList.add(ProceedsConstants.CSV_DT_GENKIN);       // 現金合計
        head5thList.add(ProceedsConstants.CSV_DT_ARIDAKA_JITU);  // 現金在高
        head5thList.add(ProceedsConstants.CSV_DT_KAJOU);        // 過剰
        head5thList.add(ProceedsConstants.CSV_DT_FUSOKU);       // 不足
        head5thList.add(ProceedsConstants.CSV_DT_KYAKUSU);      // 客数
        head5thList.add(ProceedsConstants.CSV_DT_CAN_KEN);      // 売上取消件数
        head5thList.add(ProceedsConstants.CSV_DT_CAN_KIN);      // 売上取消金額
        head5thList.add(ProceedsConstants.CSV_DT_ALLCAN_KEN);   // ｵｰﾀﾞｰ票ｷｬﾝｾﾙ件数
        head5thList.add(ProceedsConstants.CSV_DT_ALLCAN_KIN);   // ｵｰﾀﾞｰ票ｷｬﾝｾﾙ金額

        // 会計区分４～１１の件数・金額名称
        if (syukeiKbnList != null && syukeiKbnList.size() == 10) {
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(2)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(2)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(3)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(3)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(4)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(4)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(5)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(5)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(6)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(6)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(7)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(7)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(8)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(8)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(9)).getSyukeiName() + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add(((CtlSyukeiKbn)syukeiKbnList.get(9)).getSyukeiName() + ProceedsConstants.CSV_DT_KIN_NAME);
        }
        // チケット１～１５の販売件数・金額名称
        if (tckList != null && tckList.size() == 15) {
            head5thList.add((String) tckList.get(0) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(0) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(1) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(1) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(2) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(2) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(3) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(3) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(4) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(4) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(5) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(5) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(6) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(6) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(7) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(7) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(8) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(8) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(9) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(9) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(10) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(10) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(11) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(11) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(12) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(12) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(13) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(13) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) tckList.get(14) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) tckList.get(14) + ProceedsConstants.CSV_DT_KIN_NAME);
        }

        // 値引１～９の件数・金額・税額名称
        if (nkbnList != null && nkbnList.size() == 9) {
            head5thList.add((String) nkbnList.get(0) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(0) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(0) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(1) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(1) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(1) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(2) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(2) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(2) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(3) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(3) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(3) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(4) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(4) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(4) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(5) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(5) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(5) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(6) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(6) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(6) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(7) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(7) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(7) + ProceedsConstants.CSV_DT_ZEI_NAME);
            head5thList.add((String) nkbnList.get(8) + ProceedsConstants.CSV_DT_KEN_NAME);
            head5thList.add((String) nkbnList.get(8) + ProceedsConstants.CSV_DT_KIN_NAME);
            head5thList.add((String) nkbnList.get(8) + ProceedsConstants.CSV_DT_ZEI_NAME);

        }

// add 2020/05/08 USI張 begin
// modify 2021/09/04 USI戚 begin
        //List[[ヘッダー5行目]]へdポイント進呈（単位:ポイント）”文字列(ProceedsConstants.CSV_DT_POINT_PRT)を格納します
        //head5thList.add(ProceedsConstants.CSV_DT_POINT_PRT);
        head5thList.add(ProceedsConstants.CSV_DT_POINT+ProceedsConstants.CSV_DT_POINT_PRT);
// modify 2021/09/04 USI戚 end
// add 2020/05/08 USI張 end
// add 2021/09/04 USI戚 begin
        //List[[ヘッダー5行目]]へdポイントネット注文利用進呈（単位:ポイント）”文字列(ProceedsConstants.CSV_DT_POINT_PRT)を格納します
        head5thList.add(ProceedsConstants.CSV_DT_POINT_NET+ProceedsConstants.CSV_DT_POINT_NET_PRT);
// add 2021/09/04 USI戚 end
// add 2020/06/11 USI張 begin
      //List[[ヘッダー5行目]]へ"株主優待券チャージ金額"文字列(ProceedsConstants.CSV_DT_KABUPOINT_PRT)を格納します
        head5thList.add(ProceedsConstants.CSV_DT_KABUPOINT_PRT);
// add 2020/06/11 USI張 end
        headerList.add(head1stList);
        headerList.add(head2ndList);
        headerList.add(head3rdList);
        headerList.add(head4thList);
        headerList.add(head5thList);

        return headerList;
	}

    /**
     * 対象店舗情報取得ロジックを取得する
     * @return 対象店舗情報取得ロジック
     */
    public MstMiseInfoLogic getMstMiseInfoLogic() {
        return mstMiseInfoLogic;
    }

    /**
     * 対象店舗情報取得ロジックを設定する
     * @param mstMiseInfoLogic 対象店舗情報取得ロジック
     */
    public void setMstMiseInfoLogic(MstMiseInfoLogic mstMiseInfoLogic) {
        this.mstMiseInfoLogic = mstMiseInfoLogic;
    }

	/**
	 * 売上金管理月報情報取得ロジックを取得する
	 * @return	ProceedsManageGepoLogic 売上金管理月報情報取得ロジック
	 */
	public ProceedsManageGepoLogic getProceedsManageGepoLogic() {
		return this.proceedsManageGepoLogic;
	}

	/**
	 * 売上金管理月報情報取得ロジックを設定する
	 * @param proceedsManageGepoLogic 売上金管理月報情報取得ロジック
	 */
	public void setProceedsManageGepoLogic(ProceedsManageGepoLogic proceedsManageGepoLogic) {
		this.proceedsManageGepoLogic = proceedsManageGepoLogic;
	}

	/**
	 * 値引情報取得ロジックを取得する
	 * @return nebikiLogic 値引情報取得ロジック
	 */
	public NebikiLogic getNebikiLogic() {
		return nebikiLogic;
	}

	/**
	 * 値引情報取得ロジックを設定する
	 * @param nebikiLogic 値引情報取得ロジック
	 */
	public void setNebikiLogic(NebikiLogic nebikiLogic) {
		this.nebikiLogic = nebikiLogic;
	}

// add 2020/05/08 USI張  begin
	/**
	 * dポイント、株主優待モスポイント情報取得ロジックを取得する
	 * @return pointLogic dポイント、株主優待モスポイント情報取得ロジック
	 */
	public PointLogic getPointLogic() {
		return pointLogic;
	}

	/**
	 * dポイント、株主優待モスポイント情報取得ロジックを設定する
	 * @param pointLogic dポイント、株主優待モスポイント情報取得ロジック
	 */
	public void setPointLogic(PointLogic pointLogic) {
		this.pointLogic = pointLogic;
	}
// add 2020/05/08 USI張  end
}