/*
 * 作成日: 2008/04/25
 *
 */
package jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefReqDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.entity.UIShubetuSuii;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * 日次CSVダウンロードロジック
 * 
 * @author xnkusama
 */
public class ShubetuSuiiRefDayCsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT003L03";

    // Formatter
    private DateFormatter dateFormatterDE = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日（'E'）'");
    private DateFormatter dateFormatterYM = new DateFormatter(DateFormatter.DATE_TYPE_YM, "yyyy'年'MM'月'");
    private NumericFormatter numericFormatterDigit0  = new NumericFormatter(true, 0);
    private NumericFormatter numericFormatterDigit2  = new NumericFormatter(true, 2);
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        ShubetuSuiiRefReqDto dto = (ShubetuSuiiRefReqDto) csvOutputDto;
        return "URISHUSUIIDAY" + dto.getKikan() + ".csv";
	}

	/**
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        List listCSV = new ArrayList();
        ShubetuSuiiRefReqDto dto = (ShubetuSuiiRefReqDto) csvOutputDto;
        
        //ヘッダ（1-3行目）
        listCSV.addAll(makeHeader(dto));
        //行ヘッダ
        listCSV.add(makeRowHeader());
        //データ部
        listCSV.addAll(makeMainData(dto));
        
		return listCSV;
	}
    
    /**
     * CSVヘッダー部作成
     * １行目：キャンペーン名、対象期間、前年データ種別
     * ２行目：対象条件、メニュー
     * ３行目：対象店舗数、店舗種別
     * @param csvOutputDto
     * @return
     */
    private List makeHeader(ShubetuSuiiRefReqDto dto) {
        List listHeader = new ArrayList();
        
        
        // １行目
        List listHeader1 = new ArrayList();
        listHeader1.add("表示対象：");
        listHeader1.add(dto.getHyojiTaishoDisp());
        listHeader1.add("");
        listHeader1.add("前年データ種別：");
        listHeader1.add(dto.getZenDataShuName());
        
        // ２行目
        List listHeader2 = new ArrayList();
        listHeader2.add("対象期間：");
        listHeader2.add(dateFormatterYM.format(dto.getKikan(), true));
        listHeader2.add("");
        listHeader2.add("対象店舗数：");
        listHeader2.add(numericFormatterDigit0.format(dto.getTaishoTenpoCnt(), true));
        
        // ３行目
        List listHeader3 = new ArrayList();
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            listHeader3.add("店舗種別：");
            listHeader3.add(dto.getTenpoShuName());
        }
        
        // ４行目
        List listHeader4 = new ArrayList();
        listHeader4.add("");
        
        
        listHeader.add(listHeader1);
        listHeader.add(listHeader2);
        listHeader.add(listHeader3);
        listHeader.add(listHeader4);

        return listHeader;
    }
    
    /**
     * 行ヘッダー作成
     * @return
     */
    private List makeRowHeader() {
        List listRowHeader = new ArrayList();
        
        listRowHeader.add("営業日");
        listRowHeader.add("Ｅ／Ｉ 売上");
        listRowHeader.add("Ｅ／Ｉ 売上前年実績");
        listRowHeader.add("Ｅ／Ｉ 前年比(売上)");
        listRowHeader.add("Ｔ／Ｏ 売上");
        listRowHeader.add("Ｔ／Ｏ 前年実績");
        listRowHeader.add("Ｔ／Ｏ 前年比(売上)");
        listRowHeader.add("ＴＥＬ 売上");
        listRowHeader.add("ＴＥＬ 前年実績");
        listRowHeader.add("ＴＥＬ 前年比(売上)");
        listRowHeader.add("Ｄ／Ｔ 売上");
        listRowHeader.add("Ｄ／Ｔ 前年実績");
        listRowHeader.add("Ｄ／Ｔ 前年比(売上)");
        listRowHeader.add("宅配 売上");
        listRowHeader.add("宅配 前年実績");
        listRowHeader.add("宅配 前年比(売上)");
        listRowHeader.add("外販 売上");
        listRowHeader.add("外販 前年実績");
        listRowHeader.add("外販 前年比(売上)");
        listRowHeader.add("種別７ 売上");
        listRowHeader.add("種別７ 前年実績");
        listRowHeader.add("種別７ 前年比(売上)");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
//        listRowHeader.add("種別８ 売上");
//        listRowHeader.add("種別８ 前年実績");
//        listRowHeader.add("種別８ 前年比(売上)");
//        listRowHeader.add("種別９ 売上");
//        listRowHeader.add("種別９ 前年実績");
//        listRowHeader.add("種別９ 前年比(売上)");
        listRowHeader.add("委託宅配 売上");
        listRowHeader.add("委託宅配 前年実績");
        listRowHeader.add("委託宅配 前年比(売上)");
        listRowHeader.add("シェアデリ 売上");
        listRowHeader.add("シェアデリ 前年実績");
        listRowHeader.add("シェアデリ 前年比(売上)");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end

//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） begin
//        listRowHeader.add("種別１０ 売上");
//        listRowHeader.add("種別１０ 前年実績");
//        listRowHeader.add("種別１０ 前年比(売上)");
        listRowHeader.add("Ubereats 売上");
        listRowHeader.add("Ubereats 前年実績");
        listRowHeader.add("Ubereats 前年比(売上)");
//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） end

        listRowHeader.add("ネットテイク 売上");
        listRowHeader.add("ネットテイク 前年実績");
        listRowHeader.add("ネットテイク 前年比(売上)");
        listRowHeader.add("ネット宅配 売上");
        listRowHeader.add("ネット宅配 前年実績");
        listRowHeader.add("ネット宅配 前年比(売上)");

//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
//        listRowHeader.add("種別１３ 売上");
//        listRowHeader.add("種別１３ 前年実績");
//        listRowHeader.add("種別１３ 前年比(売上)");
//        listRowHeader.add("種別１４ 売上");
//        listRowHeader.add("種別１４ 前年実績");
//        listRowHeader.add("種別１４ 前年比(売上)");
//        listRowHeader.add("種別１５ 売上");
//        listRowHeader.add("種別１５ 前年実績");
//        listRowHeader.add("種別１５ 前年比(売上)");
        listRowHeader.add("ネットイート 売上");
        listRowHeader.add("ネットイート 前年実績");
        listRowHeader.add("ネットイート 前年比(売上)");
        listRowHeader.add("フルセルフイート 売上");
        listRowHeader.add("フルセルフイート 前年実績");
        listRowHeader.add("フルセルフイート 前年比(売上)");
        listRowHeader.add("フルセルフテイク 売上");
        listRowHeader.add("フルセルフテイク 前年実績");
        listRowHeader.add("フルセルフテイク 前年比(売上)");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end

//        listRowHeader.add("外販・その他 売上");
//        listRowHeader.add("外販・その他 前年実績");
//        listRowHeader.add("外販・その他 前年比(売上)");
        listRowHeader.add("種別計(売上)");
        listRowHeader.add("前年種別計(売上)");
        listRowHeader.add("種別前年比(売上)");
        listRowHeader.add("Ｅ／Ｉ 客数");
        listRowHeader.add("Ｅ／Ｉ 客数前年実績");
        listRowHeader.add("Ｅ／Ｉ 前年比(客数)");
        listRowHeader.add("Ｔ／Ｏ 客数");
        listRowHeader.add("Ｔ／Ｏ 前年実績");
        listRowHeader.add("Ｔ／Ｏ 前年比(客数)");
        listRowHeader.add("ＴＥＬ 客数");
        listRowHeader.add("ＴＥＬ 前年実績");
        listRowHeader.add("ＴＥＬ 前年比(客数)");
        listRowHeader.add("Ｄ／Ｔ 客数");
        listRowHeader.add("Ｄ／Ｔ 前年実績");
        listRowHeader.add("Ｄ／Ｔ 前年比(客数)");
        listRowHeader.add("宅配 客数");
        listRowHeader.add("宅配 前年実績");
        listRowHeader.add("宅配 前年比(客数)");
        listRowHeader.add("外販 客数");
        listRowHeader.add("外販 前年実績");
        listRowHeader.add("外販 前年比(客数)");
        listRowHeader.add("種別７ 客数");
        listRowHeader.add("種別７ 前年実績");
        listRowHeader.add("種別７ 前年比(客数)");

//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
//        listRowHeader.add("種別８ 客数");
//        listRowHeader.add("種別８ 前年実績");
//        listRowHeader.add("種別８ 前年比(客数)");
//        listRowHeader.add("種別９ 客数");
//        listRowHeader.add("種別９ 前年実績");
//        listRowHeader.add("種別９ 前年比(客数)");
        listRowHeader.add("委託宅配 客数");
        listRowHeader.add("委託宅配 前年実績");
        listRowHeader.add("委託宅配 前年比(客数)");
        listRowHeader.add("シェアデリ 客数");
        listRowHeader.add("シェアデリ 前年実績");
        listRowHeader.add("シェアデリ 前年比(客数)");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end

//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） begin
//        listRowHeader.add("種別１０ 客数");
//        listRowHeader.add("種別１０ 前年実績");
//        listRowHeader.add("種別１０ 前年比(客数)");
        listRowHeader.add("Ubereats 客数");
        listRowHeader.add("Ubereats 前年実績");
        listRowHeader.add("Ubereats 前年比(客数)");
//modify 2019/01/28 fukasawa.morimasa 「売上種別１０ → Ubereats」にヘッダ文言を変更（e-mossles/BusinessObjects 売上種別追加の件） end

        listRowHeader.add("ネットテイク 客数");
        listRowHeader.add("ネットテイク 前年実績");
        listRowHeader.add("ネットテイク 前年比(客数)");
        listRowHeader.add("ネット宅配 客数");
        listRowHeader.add("ネット宅配 前年実績");
        listRowHeader.add("ネット宅配 前年比(客数)");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 begin
//        listRowHeader.add("種別１３ 客数");
//        listRowHeader.add("種別１３ 前年実績");
//        listRowHeader.add("種別１３ 前年比(客数)");
//        listRowHeader.add("種別１４ 客数");
//        listRowHeader.add("種別１４ 前年実績");
//        listRowHeader.add("種別１４ 前年比(客数)");
//        listRowHeader.add("種別１５ 客数");
//        listRowHeader.add("種別１５ 前年実績");
//        listRowHeader.add("種別１５ 前年比(客数)");
        listRowHeader.add("ネットイート 客数");
        listRowHeader.add("ネットイート 前年実績");
        listRowHeader.add("ネットイート 前年比(客数)");
        listRowHeader.add("フルセルフイート 客数");
        listRowHeader.add("フルセルフイート 前年実績");
        listRowHeader.add("フルセルフイート 前年比(客数)");
        listRowHeader.add("フルセルフテイク 客数");
        listRowHeader.add("フルセルフテイク 前年実績");
        listRowHeader.add("フルセルフテイク 前年比(客数)");
//modify 2023/09/25 fukasawa.morimasa 「売上種別8、9、13、14、15」のヘッダ文言を変更 end

//        listRowHeader.add("外販・その他 客数");
//        listRowHeader.add("外販・その他 前年実績");
//        listRowHeader.add("外販・その他 前年比(客数)");
        listRowHeader.add("種別計(客数)");
        listRowHeader.add("前年種別計(客数)");
        listRowHeader.add("種別前年比(客数)");
        
        return listRowHeader;
    }
    /**
     * CSVデータ部作成
     * @param csvOutputDto
     * @return
     */
    private List makeMainData(ShubetuSuiiRefReqDto dto) {
        List listCsv = new ArrayList();
        
        for (Iterator ite = dto.getListNipoData().iterator(); ite.hasNext();) {
            UIShubetuSuii entity = (UIShubetuSuii) ite.next();
            List listRow = new ArrayList();
            listRow.add(dateFormatterDE.format(entity.getEigyoDt(), true));
            //売上
            listRow.add(numericFormatterDigit0.format(entity.getEatKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getEatKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getEatKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakeKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakeKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getTakeKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTelKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTelKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getTelKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getDriveKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getDriveKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getDriveKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakuhaiKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakuhaiKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getTakuhaiKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getGaihanKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getGaihanKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getGaihanKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu07Kin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu07KinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu07KinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu08Kin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu08KinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu08KinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu09Kin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu09KinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu09KinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu10Kin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu10KinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu10KinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakeKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakeKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getNettakeKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakuhaiKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakuhaiKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getNettakuhaiKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu13Kin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu13KinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu13KinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu14Kin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu14KinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu14KinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu15Kin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu15KinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu15KinZennenhi(), true));
//            listRow.add(numericFormatterDigit0.format(entity.getOtherKin(), true));
//            listRow.add(numericFormatterDigit0.format(entity.getOtherKinZen(), true));
//            listRow.add(numericFormatterDigit2.format(entity.getOtherKinZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getShubetuKeiKin(), true));
            listRow.add(numericFormatterDigit0.format(entity.getShubetuKeiKinZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getShubetuKeiKinZennenhi(), true));
            //客数
            listRow.add(numericFormatterDigit0.format(entity.getEatKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getEatKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getEatKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakeKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakeKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getTakeKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTelKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTelKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getTelKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getDriveKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getDriveKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getDriveKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakuhaiKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getTakuhaiKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getTakuhaiKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getGaihanKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getGaihanKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getGaihanKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu07Ken(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu07KenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu07KenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu08Ken(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu08KenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu08KenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu09Ken(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu09KenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu09KenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu10Ken(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu10KenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu10KenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakeKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakeKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getNettakeKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakuhaiKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getNettakuhaiKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getNettakuhaiKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu13Ken(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu13KenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu13KenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu14Ken(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu14KenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu14KenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu15Ken(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSyubetsu15KenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSyubetsu15KenZennenhi(), true));
//            listRow.add(numericFormatterDigit0.format(entity.getOtherKen(), true));
//            listRow.add(numericFormatterDigit0.format(entity.getOtherKenZen(), true));
//            listRow.add(numericFormatterDigit2.format(entity.getOtherKenZennenhi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getShubetuKeiKen(), true));
            listRow.add(numericFormatterDigit0.format(entity.getShubetuKeiKenZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getShubetuKeiKenZennenhi(), true));

            listCsv.add(listRow);
        }
        return listCsv;
    }
    
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("画面情報");
        }

	}
}
