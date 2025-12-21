package jp.co.isid.mos.bird.bizsupport.energyamount.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.energyamount.common.EnergyAmountConst;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UIEnergyAmountDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dao.UINoInputMeterDao;
import jp.co.isid.mos.bird.bizsupport.energyamount.dto.EnergyAmountRequestDto;
import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UIEnergyAmount;
import jp.co.isid.mos.bird.bizsupport.energyamount.entity.UINoInputMeter;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstSibuDao;
import jp.co.isid.mos.bird.common.entity.MstSibu;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

public class EnergyAmountCsvLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBS032L02";

    /*DAO*/
    private UINoInputMeterDao energyamountUINoInputMeterDao;
    private UIEnergyAmountDao energyamountUIEnergyAmountDao;
    private MstSibuDao mstSibuDao;
    
    /*Formatter*/
    private NumericFormatter numFormatter = new NumericFormatter(true, 2);
    private NumericFormatter numFormatterUriage = new NumericFormatter(true, 0, true);
    
    public String getFileName(CsvOutputDto csvOutputDto) {
        EnergyAmountRequestDto dto = (EnergyAmountRequestDto) csvOutputDto;
        if (EnergyAmountConst.TAISHO_DATA_NOINPUT.equals(dto.getTaishoData())) {
            return "METERMINYURYOKU.csv";
        }
        else {
            return "ENERGYSIYORYO.csv";
        }
    }

    public List getOutputData(CsvOutputDto csvOutputDto) {
        EnergyAmountRequestDto dto = (EnergyAmountRequestDto) csvOutputDto;
        List listCsvData;
        
        if (EnergyAmountConst.TAISHO_DATA_NOINPUT.equals(dto.getTaishoData())) {
            //未入力店舗一覧
            listCsvData = getNoInputData(dto);
        }
        else {
            //エネルギー使用量
            listCsvData = getEnergyAmount(dto);
        }
        
        return listCsvData;
    }

    public void validate(CsvOutputDto csvOutputDto) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /**
     * 未入力店舗一覧データ取得処理
     */
    private List getNoInputData(EnergyAmountRequestDto dto) {
        List listCsvData = new ArrayList();
        
        //対象年月
        String taishoNengetu = dto.getTaishoNengetuNoinput();
        //対象年月前月
        String taishoNengetuZen = getCalcMonth(taishoNengetu, -1);
        
        
        //検索処理(未入力店舗一覧)
        List listSearchData = getEnergyamountUINoInputMeterDao()
                                .selectNoInput(dto.getCompanyCd(),
                                               taishoNengetu,
                                               taishoNengetuZen,
                                               dto.getTaishoJoken(),
                                               dto.getMeterKbnNoinput(),
                                               dto.getUserId(),
                                               dto.isLimitFlg());
        
        //検索処理(異常値店舗一覧)
        List listSearchDataUnusual = getEnergyamountUINoInputMeterDao()
                                        .selectUnusualMeter(dto.getCompanyCd(),
                                                            taishoNengetu,
                                                            taishoNengetuZen,
                                                            dto.getTaishoJoken(),
                                                            dto.getMeterKbnNoinput(),
                                                            dto.getUserId(),
                                                            dto.isLimitFlg());
        
        if ((listSearchData == null || listSearchData.isEmpty())
                && (listSearchDataUnusual == null || listSearchDataUnusual.isEmpty())) 
        {
            throw new NoResultException();
        }
        
        //検索条件行作成
        listCsvData.addAll(makeSearchCond(dto));
        //空行追加
        listCsvData.add(new ArrayList());
        //未入力店舗一覧ヘッダ作成
        listCsvData.addAll(makeHeader(dto, taishoNengetu, taishoNengetuZen, true));
        //未入力店舗一覧データ作成
        listCsvData.addAll(makeData(dto, listSearchData));
        //空行追加
        listCsvData.add(new ArrayList());
        //異常値店舗一覧ヘッダ作成
        listCsvData.addAll(makeHeader(dto, taishoNengetu, taishoNengetuZen, false));
        //異常値店舗一覧データ作成
        listCsvData.addAll(makeData(dto, listSearchDataUnusual));
        
        
        return listCsvData;
    }

    /**
     * エネルギー使用量データ取得処理
     * @param dto
     * @return
     */
    private List getEnergyAmount(EnergyAmountRequestDto dto) {
        List listCsvData = new ArrayList();
        //対象年月
        String taishoNengetu = dto.getTaishoNengetu();
        //対象年月翌月
        String taishoNengetuPlus1 = getCalcMonth(taishoNengetu, 1);
        //対象年月前年
        String taishoNengetuZennen = getCalcMonth(taishoNengetu, -12);
        //対象年月前月
        String targetYMZengetu = getCalcMonth(taishoNengetu, -1);
        
        
        List listSearchData = new ArrayList();
        //データ取得
        if (EnergyAmountConst.TAISHO_KIKAN_NENGETU.equals(dto.getTaishoKikan())) {
            listSearchData = getEnergyamountUIEnergyAmountDao().selectNengetu(dto.getCompanyCd(),
                                                                              taishoNengetu,
                                                                              dto.getTaishoJoken(),
                                                                              dto.getMeterKbn(),
                                                                              taishoNengetuZennen,
                                                                              taishoNengetuPlus1,
                                                                              dto.getUserTypeCd(),
                                                                              dto.getUserId(),
                                                                              targetYMZengetu,
                                                                              dto.isLimitFlg());
            
            if (listSearchData == null || listSearchData.isEmpty()) {
                throw new NoResultException();
            }

        }
        else if (EnergyAmountConst.TAISHO_KIKAN_NENDO.equals(dto.getTaishoKikan())) {
            String kikanFrom = dto.getTaishoNendo() + "04";
            String kikanTo   = getCalcMonth(kikanFrom, 11);
            String zenKikanFrom = getCalcMonth(kikanFrom, -12);
            String zenKikanTo   = getCalcMonth(kikanFrom, -1);
            listSearchData = getEnergyamountUIEnergyAmountDao().selectNendo(dto.getCompanyCd(),
                                                                            dto.getTaishoJoken(),
                                                                            dto.getMeterKbn(),
                                                                            kikanFrom,
                                                                            kikanTo,
                                                                            zenKikanFrom,
                                                                            zenKikanTo,
                                                                            dto.getSysDate(),
                                                                            dto.getUserTypeCd(),
                                                                            dto.getUserId(),
                                                                            dto.isLimitFlg());
            if (listSearchData == null || listSearchData.size() == 1) {
                //集計行があるのでサイズ＝１は該当データなし
                throw new NoResultException();
            }

            //抜けている年月を埋める
            fillNengetu(listSearchData, dto.getTaishoNendo());
        }
        
        
        
        //検索条件行作成
        listCsvData.addAll(makeSearchCondAmount(dto));
        if (EnergyAmountConst.TAISHO_KIKAN_NENDO.equals(dto.getTaishoKikan())) {
            //ヘッダ作成
            listCsvData.addAll(makeHeaderAmountNendo());
            //データ行作成
            listCsvData.addAll(makeDataAmountNendo(dto,listSearchData));
        }
        else {
            //ヘッダ作成
            listCsvData.addAll(makeHeaderAmountNengetu(dto, taishoNengetu, taishoNengetuPlus1));
            //データ行作成
            listCsvData.addAll(makeDataAmountNengetu(dto, listSearchData));
        }
        
        
        return listCsvData;
    }
    
    /**
     * 検索条件行リスト作成(エネルギー使用量)
     * @param dto
     * @return
     */
    private List makeSearchCondAmount(EnergyAmountRequestDto dto) {
        List listSearchCond = new ArrayList();
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        
        //1行目
        List listHeader = new ArrayList();
        listHeader.add("対象条件:");
        if (EnergyAmountConst.TAISHO_JOKEN_ALL.equals(dto.getTaishoJoken())
                || !UserType.HONBU.equals(dto.getUserTypeCd())) 
        {
            listHeader.add("全店");
        }
        else {
            MstSibu mstSibu = (MstSibu) getMstSibuDao().getSibu(dto.getCompanyCd(), dto.getTaishoJoken()).get(0);
            listHeader.add(mstSibu.getSibuCd() + " " + mstSibu.getSibuName());
        }
        listHeader.add("");
        listHeader.add("エネルギー使用量結果"); //---2008/10/02 add プレ後対応
        listSearchCond.add(listHeader);
        
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("対象期間:");
        //年度指定
        if (EnergyAmountConst.TAISHO_KIKAN_NENDO.equals(dto.getTaishoKikan())) {
            listHeader2.add(dto.getTaishoNendo() + "年度");
        }
        //年月指定
        else {
            listHeader2.add(dateFormatter.format(dto.getTaishoNengetu(), true));
        }
        listSearchCond.add(listHeader2);
        
        //3行目
        List listHeader3 = new ArrayList();
        listHeader3.add("メーター区分:");
        listHeader3.add(getMeterKbnName(dto.getMeterKbn()));
        listSearchCond.add(listHeader3);
        
        return listSearchCond;
    }
    
    /**
     * 検索条件行リスト作成
     * @param dto
     * @return
     */
    private List makeSearchCond(EnergyAmountRequestDto dto) {
        List listSearchCond = new ArrayList();
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        
        String sibuInfo = "";
        if (EnergyAmountConst.TAISHO_JOKEN_ALL.equals(dto.getTaishoJoken())) {
            sibuInfo = "全店";
        }
        else {
            MstSibu mstSibu = (MstSibu) getMstSibuDao().getSibu(dto.getCompanyCd(), dto.getTaishoJoken()).get(0);
            sibuInfo = mstSibu.getSibuCd() + " " + mstSibu.getSibuName();
        }
        //1行目
        List listHeader1 = new ArrayList();
        listHeader1.add("対象条件：");
        listHeader1.add(sibuInfo);
        listSearchCond.add(listHeader1);
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("対象期間：");
        listHeader2.add(dateFormatter.format(dto.getTaishoNengetuNoinput(), true));
        listSearchCond.add(listHeader2);
        
        return listSearchCond;
        
    }
    
    /**
     * 未入力店舗一覧ヘッダリスト作成
     * @param dto
     * @param taishoNengetuZen
     * @param mode true:未入力店舗一覧  false:異常値店舗一覧
     */
    private List makeHeader(EnergyAmountRequestDto dto, String taishoNengetu, String taishoNengetuZen, boolean mode) {
        List listHeader = new ArrayList();
        //月変換フォーマッタ
        DateFormatter dtF = new DateFormatter(DateFormatter.DATE_TYPE_YM, "M'月'");
        //対象年月（M月）
        String taishoMonth = dtF.format(taishoNengetu, true);
        //対象年月前月（M月）
        String taishoMonthZen = dtF.format(taishoNengetuZen, true);
        
        //1行目
        List listHeader1 = new ArrayList();
        if (mode) {
            listHeader1.add("未入力店舗一覧");
        }
        else {
            listHeader1.add("異常値店舗一覧");
        }
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("電灯");
        listHeader1.add(taishoMonth + "電灯");
        listHeader1.add(taishoMonthZen + "電灯");
        listHeader1.add("");
        listHeader1.add("動力");
        listHeader1.add(taishoMonth + "動力");
        listHeader1.add(taishoMonthZen + "動力");
        listHeader1.add("");
        listHeader1.add("ガス");
        listHeader1.add(taishoMonth + "ガス");
        listHeader1.add(taishoMonthZen + "ガス");
        listHeader1.add("");
        listHeader1.add("水道");
        listHeader1.add(taishoMonth + "水道");
        listHeader1.add(taishoMonthZen + "水道");
        listHeader1.add("");
        listHeader.add(listHeader1);
        
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("事業本部ｺｰﾄﾞ");
        listHeader2.add("事業本部名称");
        listHeader2.add("ｴﾘｱｺｰﾄﾞ");
        listHeader2.add("ｴﾘｱ名称");
        listHeader2.add("支部ｺｰﾄﾞ");
        listHeader2.add("支部名称");
        listHeader2.add("店ｺｰﾄﾞ");
        listHeader2.add("店名称");
        listHeader2.add("新オープン");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("電灯");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("動力");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("ガス");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("水道");
        listHeader.add(listHeader2);
        
        return listHeader;
    }
    
    /**
     * エネルギー使用量（年度指定）ヘッダリスト作成
     * @param dto
     * @param taishoNengetuZen
     * @param mode true:未入力店舗一覧  false:異常値店舗一覧
     */
    private List makeHeaderAmountNendo() {
        List listHeader = new ArrayList();
        
        //1行目
        List listHeader1 = new ArrayList();
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");                        
        listHeader1.add("");
        listHeader1.add("電灯(kwt)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("動力(kwt)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("ガス(m3)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("水道(m3)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader.add(listHeader1);
        
        //2行目
        List listHeader2 = new ArrayList();
        listHeader2.add("年月");          //---1
        listHeader2.add("売上");
        listHeader2.add("前年売上");
        listHeader2.add("営業日数");
        listHeader2.add("前年営業日数");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader.add(listHeader2);
        
        return listHeader;
    }
    
    /**
     * エネルギー使用量（年月指定）ヘッダリスト作成
     * @param dto
     * @param taishoNengetuZen
     * @param mode true:未入力店舗一覧  false:異常値店舗一覧
     */
    private List makeHeaderAmountNengetu(EnergyAmountRequestDto dto, String taishoNengetu, String taishoNengetuPlus1) {
        List listHeader = new ArrayList();
        //月変換フォーマッタ
        DateFormatter dtF = new DateFormatter(DateFormatter.DATE_TYPE_YM, "M'月'");
        //対象年月（M月）
        String taishoMonth = dtF.format(taishoNengetu, true);
        //対象年月翌月（M月）
        String taishoMonthPlus1 = dtF.format(taishoNengetuPlus1, true);
        
        //1行目
        List listHeader1 = new ArrayList();
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            listHeader1.add("");
            listHeader1.add("");
            listHeader1.add("");
            listHeader1.add("");                        
            listHeader1.add("");
            listHeader1.add("");
            listHeader1.add("");
            listHeader1.add("");
        }

        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("全項目");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("電灯");
        listHeader1.add(taishoMonthPlus1 + "電灯");
        listHeader1.add(taishoMonth + "電灯");
        listHeader1.add("電灯(kwt)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("動力");
        listHeader1.add(taishoMonthPlus1 + "動力");
        listHeader1.add(taishoMonth + "動力");
        listHeader1.add("動力(kwt)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("ガス");
        listHeader1.add(taishoMonthPlus1 + "ガス");
        listHeader1.add(taishoMonth + "ガス");
        listHeader1.add("ガス(m3)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("水道");
        listHeader1.add(taishoMonthPlus1 + "水道");
        listHeader1.add(taishoMonth + "水道");
        listHeader1.add("水道(m3)");
        listHeader1.add("");
        listHeader1.add("売上100万円当り使用量");
        listHeader1.add("");
        listHeader1.add("");
        listHeader1.add("データメンテ");
        listHeader.add(listHeader1);
        
        //2行目
        List listHeader2 = new ArrayList();
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            listHeader2.add("事業本部ｺｰﾄﾞ");
            listHeader2.add("事業本部名称");
            listHeader2.add("ｴﾘｱｺｰﾄﾞ");
            listHeader2.add("ｴﾘｱ名称");
            listHeader2.add("支部ｺｰﾄﾞ");
            listHeader2.add("支部名称");
            listHeader2.add("ﾌﾞﾛｯｸｺｰﾄﾞ");
            listHeader2.add("ﾌﾞﾛｯｸ名称");
        }
        listHeader2.add("店ｺｰﾄﾞ");
        listHeader2.add("店名称");
        listHeader2.add("新オープン");
        listHeader2.add("対象外");
        listHeader2.add("売上");
        listHeader2.add("前年売上");
        listHeader2.add("営業日数");
        listHeader2.add("前年営業日数");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader2.add("対象外");
        listHeader2.add("採用値");
        listHeader2.add("採用値");
        listHeader2.add("当年使用量");
        listHeader2.add("前年使用量");
        listHeader2.add("当年/百万");
        listHeader2.add("前年/百万");
        listHeader2.add("前年比");
        listHeader2.add("実施済");
        listHeader.add(listHeader2);
        
        return listHeader;
    }
    
    /**
     * 一覧データリスト作成
     */
    private List makeData(EnergyAmountRequestDto dto, List listResult) {
        List listData = new ArrayList();
        
        for (Iterator ite = listResult.iterator(); ite.hasNext();) {
            UINoInputMeter entity = (UINoInputMeter) ite.next();
            List listRow = new ArrayList();
            listRow.add(entity.getJigyouCd());
            listRow.add(entity.getJigyouName());
            listRow.add(entity.getSlareaCd());
            listRow.add(entity.getSlareaName());
            listRow.add(entity.getSibuCd());
            listRow.add(entity.getSibuName());
            listRow.add(entity.getMiseCd());
            listRow.add(entity.getMiseNameKj());
            listRow.add(entity.getNewOpen());
            listRow.add(getNoInputDisp(entity.getElectricFlg()));
            listRow.add(numFormatter.format(entity.getElectricMeter()));
            listRow.add(numFormatter.format(entity.getElectricMeterZengetu()));
            listRow.add(numFormatter.format(entity.getElectricAmt()));
            listRow.add(getNoInputDisp(entity.getPowerFlg()));
            listRow.add(numFormatter.format(entity.getPowerMeter()));
            listRow.add(numFormatter.format(entity.getPowerMeterZengetu()));
            listRow.add(numFormatter.format(entity.getPowerAmt()));
            listRow.add(getNoInputDisp(entity.getGasFlg()));
            listRow.add(numFormatter.format(entity.getGasMeter()));
            listRow.add(numFormatter.format(entity.getGasMeterZengetu()));
            listRow.add(numFormatter.format(entity.getGasAmt()));
            listRow.add(getNoInputDisp(entity.getWaterFlg()));
            listRow.add(numFormatter.format(entity.getWaterMeter()));
            listRow.add(numFormatter.format(entity.getWaterMeterZengetu()));
            listRow.add(numFormatter.format(entity.getWaterAmt()));
            
            listData.add(listRow);
            ite.remove();
        }
        
        return listData;
    }    
    
    /**
     * 一覧データリスト(エネルギー使用量 年度指定)作成
     */
    private List makeDataAmountNendo(EnergyAmountRequestDto dto, List listResult) {
        List listData = new ArrayList();
        //年月変換フォーマッタ
        DateFormatter dtF = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);

        for (Iterator ite = listResult.iterator(); ite.hasNext();) {
            UIEnergyAmount entity = (UIEnergyAmount) ite.next();
            List listRow = new ArrayList();
            
            listRow.add(dtF.format(entity.getMeterYm(), true));
            listRow.add(numFormatterUriage.format(entity.getUriage()));
            listRow.add(numFormatterUriage.format(entity.getZenUriage()));
            listRow.add(numFormatterUriage.format(entity.getEigyoDays()));
            listRow.add(numFormatterUriage.format(entity.getZenEigyoDays()));
            listRow.add(numFormatter.format(entity.getElectricAmt()));
            listRow.add(numFormatter.format(entity.getElectricAmtZennen()));
            listRow.add(numFormatter.format(entity.getElectricAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getElectricAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getElectricAmtUriagehiZennenhi()));
            listRow.add(numFormatter.format(entity.getPowerAmt()));
            listRow.add(numFormatter.format(entity.getPowerAmtZennen()));
            listRow.add(numFormatter.format(entity.getPowerAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getPowerAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getPowerAmtUriagehiZennenhi()));
            listRow.add(numFormatter.format(entity.getGasAmt()));
            listRow.add(numFormatter.format(entity.getGasAmtZennen()));
            listRow.add(numFormatter.format(entity.getGasAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getGasAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getGasAmtUriagehiZennenhi()));
            listRow.add(numFormatter.format(entity.getWaterAmt()));
            listRow.add(numFormatter.format(entity.getWaterAmtZennen()));
            listRow.add(numFormatter.format(entity.getWaterAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getWaterAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getWaterAmtUriagehiZennenhi()));
            
            listData.add(listRow);
            ite.remove();
        }
        
        return listData;
    }    
    
    /**
     * 一覧データリスト(エネルギー使用量 年月指定)作成
     */
    private List makeDataAmountNengetu(EnergyAmountRequestDto dto, List listResult) {
        List listData = new ArrayList();

        for (Iterator ite = listResult.iterator(); ite.hasNext();) {
            UIEnergyAmount entity = (UIEnergyAmount) ite.next();
            List listRow = new ArrayList();
            if (UserType.HONBU.equals(dto.getUserTypeCd())) {
                listRow.add(entity.getJigyouCd());
                listRow.add(entity.getJigyouName());
                listRow.add(entity.getSlareaCd());
                listRow.add(entity.getSlareaName());
                listRow.add(entity.getSibuCd());
                listRow.add(entity.getSibuName());
                listRow.add(entity.getBlockCd());
                listRow.add(entity.getBlockName());
            }
            listRow.add(entity.getMiseCd());
            listRow.add(entity.getMiseNameKj());
            listRow.add(entity.getNewOpen());
            listRow.add(getAllNoInputDisp(entity, dto.getTaishoData()));
            listRow.add(numFormatterUriage.format(entity.getUriage()));
            listRow.add(numFormatterUriage.format(entity.getZenUriage()));
            listRow.add(numFormatterUriage.format(entity.getEigyoDays()));
            listRow.add(numFormatterUriage.format(entity.getZenEigyoDays()));
            listRow.add(getNoInputDisp(entity.getElectricFlg()));//
            listRow.add(numFormatter.format(entity.getElectricMeterYokugetu()));
            listRow.add(numFormatter.format(entity.getElectricMeter()));
            listRow.add(numFormatter.format(entity.getElectricAmt()));
            listRow.add(numFormatter.format(entity.getElectricAmtZennen()));
            listRow.add(numFormatter.format(entity.getElectricAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getElectricAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getElectricAmtUriagehiZennenhi()));
            listRow.add(getNoInputDisp(entity.getPowerFlg()));//
            listRow.add(numFormatter.format(entity.getPowerMeterYokugetu()));
            listRow.add(numFormatter.format(entity.getPowerMeter()));
            listRow.add(numFormatter.format(entity.getPowerAmt()));
            listRow.add(numFormatter.format(entity.getPowerAmtZennen()));
            listRow.add(numFormatter.format(entity.getPowerAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getPowerAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getPowerAmtUriagehiZennenhi()));
            listRow.add(getNoInputDisp(entity.getGasFlg()));//
            listRow.add(numFormatter.format(entity.getGasMeterYokugetu()));
            listRow.add(numFormatter.format(entity.getGasMeter()));
            listRow.add(numFormatter.format(entity.getGasAmt()));
            listRow.add(numFormatter.format(entity.getGasAmtZennen()));
            listRow.add(numFormatter.format(entity.getGasAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getGasAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getGasAmtUriagehiZennenhi()));
            listRow.add(getNoInputDisp(entity.getWaterFlg()));//
            listRow.add(numFormatter.format(entity.getWaterMeterYokugetu()));
            listRow.add(numFormatter.format(entity.getWaterMeter()));
            listRow.add(numFormatter.format(entity.getWaterAmt()));
            listRow.add(numFormatter.format(entity.getWaterAmtZennen()));
            listRow.add(numFormatter.format(entity.getWaterAmtUriagehi()));
            listRow.add(numFormatter.format(entity.getWaterAmtUriagehiZennen()));
            listRow.add(numFormatter.format(entity.getWaterAmtUriagehiZennenhi()));
            listRow.add(getDataMaintenance(entity.getDataMtDt()));
            
            listData.add(listRow);
            ite.remove();
        }
        
        return listData;
    }    
    
    /**
     * メーター区分名称取得
     */
    private String getMeterKbnName(String meterKbn) {
        String meterKbnName = "";
        if (EnergyAmountConst.METER_KBN_TENPO.equals(meterKbn)) {
            meterKbnName = EnergyAmountConst.METER_KBN_TENPO_NAME;
        }
        else if (EnergyAmountConst.METER_KBN_OFFICE.equals(meterKbn)) {
            meterKbnName = EnergyAmountConst.METER_KBN_OFFICE_NAME;
        }
        return meterKbnName;
    }
    /**
     * 対象外 表示変換
     */
    private String getNoInputDisp(String flg) {
        return EnergyAmountConst.METER_NO_INPUT_FLG.equals(flg) ? EnergyAmountConst.NO_INPUT_VIEW  : "";
    }
    /**
     * 全項目対象外 表示変換
     */
    private String getAllNoInputDisp(Object entityObj, String taishoData) {
        boolean isNoInput = false;
        if (EnergyAmountConst.TAISHO_DATA_NOINPUT.equals(taishoData)) {
            UINoInputMeter entity = (UINoInputMeter) entityObj;
            isNoInput = EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getElectricFlg())
                                && EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getPowerFlg())
                                && EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getGasFlg())
                                && EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getWaterFlg());
        }
        else {
            UIEnergyAmount entity = (UIEnergyAmount) entityObj;
            isNoInput = EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getElectricFlg())
                            && EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getPowerFlg())
                            && EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getGasFlg())
                            && EnergyAmountConst.METER_NO_INPUT_FLG.equals(entity.getWaterFlg());
        }
        
        return  isNoInput ? EnergyAmountConst.NO_INPUT_VIEW : "";
    }
    /**
     * 
     */
    private String getDataMaintenance(String dataMtDt) {
        return CommonUtil.isNull(dataMtDt) ? "" : EnergyAmountConst.DATA_MAINTENANCE_DONE;
    }
    /**
     * 年月の計算
     * @param date
     * @param append
     * @return
     */
    private String getCalcMonth(String date, int append) {
        String month = date;
        String retMonth = "";
        if (date.length() > 6) {
            month = date.substring(0, 6);
        }
        try {
            retMonth =  DateManager.getNextMonth(month, append);
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
        return retMonth;
    }
    
    private void fillNengetu(List listData, String nendo) {
        String loopNengetu = nendo + "04";
        int loopCnt = 0;
        try {
            while (DateManager.getCurrentYear(loopNengetu).equals(nendo)) {
                boolean isExist = false;
                for (Iterator ite = listData.iterator(); ite.hasNext();) {
                    UIEnergyAmount entity = (UIEnergyAmount) ite.next();
                    if (loopNengetu.equals(entity.getMeterYm())) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    UIEnergyAmount entityBlank = new UIEnergyAmount();
                    entityBlank.setMeterYm(loopNengetu);
                    listData.add(loopCnt, entityBlank);
                }
                loopNengetu = DateManager.getNextMonth(loopNengetu, 1);
                loopCnt++;
                
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("日付", null, ex);
        }
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

    public MstSibuDao getMstSibuDao() {
        return mstSibuDao;
    }

    public void setMstSibuDao(MstSibuDao mstSibuDao) {
        this.mstSibuDao = mstSibuDao;
    }
}
