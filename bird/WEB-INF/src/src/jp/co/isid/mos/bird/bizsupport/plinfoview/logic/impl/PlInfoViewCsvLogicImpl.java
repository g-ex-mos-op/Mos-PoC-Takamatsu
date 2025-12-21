/*
 * 作成日: 2006/04/25
 * 更新日：2006/08/14 xkhata 12ヶ月合計、平均構成比出力バグ対応
 */
package jp.co.isid.mos.bird.bizsupport.plinfoview.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.plinfoview.dao.UIPLRCDataInfoDao;
import jp.co.isid.mos.bird.bizsupport.plinfoview.dto.PlInfoViewDto;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.CodBlock;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.MSTSibuCategoryInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLDataInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.entity.UIPLRCDataInfo;
import jp.co.isid.mos.bird.bizsupport.plinfoview.logic.GetPLInfoLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.Calculator;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * PL照会 CSVロジック
 * @author xnkusama
 */
public class PlInfoViewCsvLogicImpl implements CsvOutputLogic {

    
    /* ロジックID */    
    public static final String LOGIC_ID = "BBS006L04";
    
    private static final int OUTPUT_NUMBER = 12;
    
    private UIPLRCDataInfoDao uiPlRcDataInfoDao;
    private GetPLInfoLogic getPlInfoLogic;
    
    /* 該当データがない月のマークとしてPL_TYPEにセット */
    private static final String PL_TYPE_EMPTY_MARK = "Z";
    // ユーザータイプコード：本部
    private static final String USER_TYPE_HONBU = "01";
    private static final String USER_TYPE_ONER  = "02";
    private static final String USER_TYPE_TENPO = "03";
    
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        String mode = ((PlInfoViewDto) csvOutputDto).getCsvMode();
        String fileName = "PLSHOKAI.csv";
        
        if (PlInfoViewDto.CSV_MODE_RC_KOSEHI_ARI.equals(mode)) {
            fileName = "PLCHOKU.csv";
        }
        else if (PlInfoViewDto.CSV_MODE_RC_KOSEHI_NASI.equals(mode)) {
            fileName = "PLCHOKU_NORATE.csv";
        }
        else if (PlInfoViewDto.CSV_MODE_FC_KINGAKU.equals(mode)) {
            fileName = "PLSHOKAI.csv";
        }
        else if (PlInfoViewDto.CSV_MODE_FC_KOSEHI.equals(mode)) {
            fileName = "PLSHOKAI_KOU.csv";
        }
        else {
            fileName = "PLDATA.csv";
        }
		return fileName;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
        // データクリア
        Map map12Gokei = new HashMap();
        
        // DTO
        PlInfoViewDto dto = (PlInfoViewDto) csvOutputDto;
        
        // 検索処理
        getGetPlInfoLogic()
                 .execute(dto, 
                 		  dto.getSysDt(), 
                          dto.getAppDt(), 
                          dto.getUserId());
        // CSV出力用List
        List listCSV = new ArrayList();
        
        try {
            // ヘッダ部
            makeHeader(dto, listCSV);
            
            // 空白行
            listCSV.add(new ArrayList());
            
//TODO タイプによる切替が必要
            // データ部
            if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_FC_KINGAKU)
                    || dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_FC_KOSEHI))
            {
            	makeDataFC(dto, listCSV, map12Gokei);
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_BUPPAN_INFO)
                    || dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_GENZAIRYO_INFO)
                    || dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_HOUSO_INFO)
                    || dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_KEIHI_INFO)
                    || dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_KONETUHI_INFO)
                    || dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_YASAI_INFO))
            {
                makeDataGenka(dto, listCSV);
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_RC_KOSEHI_ARI)
                    || dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_RC_KOSEHI_NASI))
            {
                makeDataRC(dto, listCSV);
            }
        }
        catch (Exception ex) {
        	throw new FtlSystemException("CSV作成");
        }
		return listCSV;
	}

    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
	}

    /* ヘッダ部 */
    private void makeHeader(PlInfoViewDto dto, List listCsv) {
        String code = "";
        String name = "";
        List listTaishoJoken = new ArrayList();
        List listHyojiTaisho = new ArrayList();
        List listOpenDT      = new ArrayList();
        List listOnerName    = new ArrayList();
        List listTenpoShu    = new ArrayList();
        List listKbn         = new ArrayList();
//        List listTani        = new ArrayList();
        
        // 対象条件、表示対象
        listTaishoJoken.add("対象条件");
        listHyojiTaisho.add("表示対象");
        
        String condTaishoTenpo = dto.getCondTaishoTenpo();
        if (condTaishoTenpo == null) {
        	condTaishoTenpo = "";
        }
        if ("".equals(condTaishoTenpo)) {
            listTaishoJoken.add("FC全社");
            listHyojiTaisho.add("");
        }
        else if ("2".equals(condTaishoTenpo)) {
            listTaishoJoken.add("事業本部");
            code = dto.getCondJigyouCd();
            if (code == null) {
                code = "";
            }
            for (Iterator ite = dto.getCondListJigyouCd().iterator(); ite.hasNext();) {
            	MSTSibuCategoryInfo entity = (MSTSibuCategoryInfo) ite.next();
                if (code.trim().equals(entity.getJigyouCd())) {
                    name = entity.getJigyouName();
                    break;
                }
            }
            listHyojiTaisho.add(name);
        }
        else if ("3".equals(condTaishoTenpo)) {
            listTaishoJoken.add("営業エリア");
            code = dto.getCondSlareaCd();
            if (code == null) {
                code = "";
            }
            for (Iterator ite = dto.getCondListSlareaCd().iterator(); ite.hasNext();) {
                MSTSibuCategoryInfo entity = (MSTSibuCategoryInfo) ite.next();
                if (code.trim().equals(entity.getSlareaCd())) {
                    name = entity.getSlareaName();
                    break;
                }
            }
            listHyojiTaisho.add(name);
        }
        else if ("4".equals(condTaishoTenpo)) {
            listTaishoJoken.add("支部");
            code = dto.getCondSibuCd();
            if (code == null) {
                code = "";
            }
            for (Iterator ite = dto.getCondListSibuCd().iterator(); ite.hasNext();) {
                MSTSibuCategoryInfo entity = (MSTSibuCategoryInfo) ite.next();
                if (code.trim().equals(entity.getSibuCd())) {
                    name = entity.getSibuName();
                    break;
                }
            }
            listHyojiTaisho.add(name);
            code = dto.getCondBlockCd();
            if (code == null) {
                code = "";
            }
            if (code.trim().equals("")) {
            	name = "全ブロック";
            }
            else {
                for (Iterator ite = dto.getCondListBlockCd().iterator(); ite.hasNext();) {
                	CodBlock codBlock = (CodBlock) ite.next();
                    if (code.trim().equals(codBlock.getBlockCd())) {
                    	name = codBlock.getBlockName();
                        break;
                    }
                }
            }
            listHyojiTaisho.add(name);
        }
        else if ("6".equals(condTaishoTenpo)) {
            listTaishoJoken.add("店舗");
            listHyojiTaisho.add(dto.getCondMiseCd() + " " + dto.getCondMiseName());
            // オープン日
            listOpenDT.add("オープン日");
            DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
            listOpenDT.add(dateFormatter.format(dto.getCondMiseOpenDt(), true));
            // オーナー
            listOnerName.add("オーナー");
            listOnerName.add(dto.getCondMiseOnerName());
        }
        else if ("7".equals(condTaishoTenpo)) {
            listTaishoJoken.add("オーナー");
            listHyojiTaisho.add(dto.getCondOnerCd() + " " + dto.getCondOnerName());
        }

        /* 単位 */
        listTaishoJoken.add("");
        listTaishoJoken.add("単位");
        if (PlInfoViewDto.CSV_MODE_FC_KOSEHI.equals(dto.getCsvMode())) {
            listTaishoJoken.add("％");
        }
        else {
            listTaishoJoken.add("円");
        }
        /* 対象数 */
        if (!"4".equals(condTaishoTenpo)) {
            listHyojiTaisho.add("");
        }
        listHyojiTaisho.add("対象数");
        if ("6".equals(condTaishoTenpo) && dto.isResultFcRc()) {
            listHyojiTaisho.add(new Integer(1));
        }
        else {
            listHyojiTaisho.add(new Integer(dto.getEntityTougetu().getRowCount()));
        }
        
        //【店舗種別】、【検索タイプ】
        if ("6".equals(condTaishoTenpo)) {
        }
        else if ("7".equals(condTaishoTenpo)) {
            listTenpoShu.add("検索タイプ");
            listTenpoShu.add(getSearchType(dto.getCondSearchType(), dto));
        }
        else {
            listTenpoShu.add("店舗種別");
            listTenpoShu.add(getTenpoShuName(dto.getCondTenpoShu()));
        }
        
        // 区分
        String csvMode = dto.getCsvMode();
        if (csvMode.equals(PlInfoViewDto.CSV_MODE_GENKA_BUPPAN_INFO)
                || csvMode.equals(PlInfoViewDto.CSV_MODE_GENKA_GENZAIRYO_INFO)
                || csvMode.equals(PlInfoViewDto.CSV_MODE_GENKA_HOUSO_INFO)
                || csvMode.equals(PlInfoViewDto.CSV_MODE_GENKA_KEIHI_INFO)
                || csvMode.equals(PlInfoViewDto.CSV_MODE_GENKA_KONETUHI_INFO)
                || csvMode.equals(PlInfoViewDto.CSV_MODE_GENKA_YASAI_INFO))
        {
            listTenpoShu.add("");
            listTenpoShu.add("区分");
            if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_BUPPAN_INFO)) {
                listTenpoShu.add("物販原価");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_GENZAIRYO_INFO)) {
                listTenpoShu.add("原材料原価");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_HOUSO_INFO)) {
                listTenpoShu.add("包装資材原価");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_KEIHI_INFO)) {
                listTenpoShu.add("経費情報");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_KONETUHI_INFO)) {
                listTenpoShu.add("水道光熱費情報");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_YASAI_INFO)) {
                listTenpoShu.add("野菜原価");
            }
            
// add start xkhata 20070216
            listOpenDT.add("");
            listOpenDT.add("区分");
            if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_BUPPAN_INFO)) {
                listOpenDT.add("物販原価");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_GENZAIRYO_INFO)) {
                listOpenDT.add("原材料原価");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_HOUSO_INFO)) {
                listOpenDT.add("包装資材原価");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_KEIHI_INFO)) {
                listOpenDT.add("経費情報");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_KONETUHI_INFO)) {
                listOpenDT.add("水道光熱費情報");
            }
            else if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_GENKA_YASAI_INFO)) {
                listOpenDT.add("野菜原価");
            }
// add end
        }
        
        listCsv.add(listTaishoJoken);
        listCsv.add(listHyojiTaisho);
        if (!"6".equals(condTaishoTenpo)) {
            listCsv.add(listTenpoShu);
        }
        else if ("6".equals(condTaishoTenpo)) {
            listCsv.add(listOpenDT);
        }
    }
    
    private String getTenpoShuName(String tenpoShu) {
        String tenpoShuName = "";
        
        if (tenpoShu == null || "".equals(tenpoShu)) {
        	tenpoShuName = "全店";
        }
        else if ("1".equals(tenpoShu)) {
        	tenpoShuName = "前年対象店";
        }
        else if ("2".equals(tenpoShu)) {
            tenpoShuName = "予算対象店";
        }
        else if ("3".equals(tenpoShu)) {
            tenpoShuName = "新店";
        }
        return tenpoShuName;
    }
    private String getSearchType(String searchType, PlInfoViewDto dto) {
        String searchTypeName = "";
        
        if (USER_TYPE_ONER.equals(dto.getUserTypeCd())) {
            if (searchType == null || "".equals(searchType)) {
                searchTypeName = "全社";
            }
            else if ("1".equals(searchType)) {
                searchTypeName = "本社";
            }
            else if ("2".equals(searchType)) {
                searchTypeName = "全店合計";
            }
            else if ("3".equals(searchType)) {
                searchTypeName = "全店平均";
            }
            else if ("4".equals(searchType)) {
                searchTypeName = dto.getCondMiseCdOner() + " " + dto.getCondMiseName();
            }
        }
        else {
            if (searchType == null || "".equals(searchType)) {
                searchTypeName = "全社";
            }
            else if ("1".equals(searchType)) {
                searchTypeName = "オーナーのみ";
            }
            else if ("2".equals(searchType)) {
                searchTypeName = "経営店舗合計";
            }
            else if ("3".equals(searchType)) {
                searchTypeName = "経営店舗平均";
            }
        }
        return searchTypeName;
    }
    
    /**
     * データ部 （FC結果画面）
     * @param dto
     * @param listCsv
     * @throws Exception
     * 
     * @更新日:2011/05/06 [差引余剰金]の12月合計値＆各月平均値の不具合対応
     */
    private void makeDataFC(PlInfoViewDto dto, List listCsv, Map map12Gokei) throws Exception {
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        // 開始年月
        String baseYM = dto.getCondTargetYM();
        
        // 項目ヘッダ
        List listKoumokuHeader = new ArrayList();
        listKoumokuHeader.add("項目");
        for (int i = OUTPUT_NUMBER; i >= 0 ; i--) {
        	listKoumokuHeader.add(dateFormatter.format(DateManager.getPrevMonth(baseYM, i), true));
        }
        listKoumokuHeader.add("12ヶ月合計");
        listKoumokuHeader.add("各月平均");
        listCsv.add(listKoumokuHeader);
        
        // 明細部
        List listUriagedaka = new ArrayList();
        List listBuppan = new ArrayList();
        List listUriage = new ArrayList();
        List listGenzairyoKei = new ArrayList();
        List listYasaiKei = new ArrayList();
        List listHouzaiKei = new ArrayList();
        List listBuppanKei = new ArrayList();
        List listUriageGenka = new ArrayList();
        List listUriageSoRieki = new ArrayList();
        List listYakuinKei = new ArrayList();
        List listSalarySalary = new ArrayList();
        List listZakkyuKei = new ArrayList();
        List listSalBonus = new ArrayList();
        List listSalary = new ArrayList();
        List listYachin = new ArrayList();
        List listSuikouHi = new ArrayList();
        List listRoyalty = new ArrayList();
        List listTesuryo = new ArrayList();
        List listKoukoku = new ArrayList();
        List listShoumou = new ArrayList();
        List listHouteiFukuri = new ArrayList();
        List listFukuriKousei = new ArrayList();
        List listKousai = new ArrayList();
        List listRyohi = new ArrayList();
        List listTusin = new ArrayList();
        List listLease = new ArrayList();
        List listSharyo = new ArrayList();
        List listSozei = new ArrayList();
        List listHoken = new ArrayList();
        List listUnchin = new ArrayList();
        List listShuzen = new ArrayList();
        List listZappi = new ArrayList();
        List listKeihiShokei = new ArrayList();
        List listShokyakuRieki = new ArrayList();
        List listGenkaShokyaku = new ArrayList();
        List listEigaiShueki = new ArrayList();
        List listEigaiHiyo = new ArrayList();
        List listHonshahiHai = new ArrayList();
        List listZeibikiMaeRieki = new ArrayList();
        List listRieki = new ArrayList();
        List listCashflow = new ArrayList();
        List listKashiireDown = new ArrayList();
        List listKappuDown = new ArrayList();
//        List listTouGenshoKei = new ArrayList();
        List listSashihikiYojyo = new ArrayList();
        List listKashiireZandaka = new ArrayList();
        List listLeaseZandaka = new ArrayList();
        List listKappuZandaka = new ArrayList();
        List listTouZandakaKei = new ArrayList();
        List listMidasi1 = new ArrayList();
        List listMidasi2 = new ArrayList();
        List listBlank = new ArrayList();
        
        //見出し
        listMidasi1.add("[資金繰り]");
        listMidasi2.add("[当月残高]");
        //ブランク行
        listBlank.add("");
        
        // 項目名セット
        listUriage.add("売上高");
        listBuppan.add("物品販売");
        listUriagedaka.add("売上合計");
        listGenzairyoKei.add("原材料費計");
        listYasaiKei.add("野菜計");
        listHouzaiKei.add("包装資材費計");
        listBuppanKei.add("商品計");
        listUriageGenka.add("売上原価計");
        listUriageSoRieki.add("売上総利益");
        listYakuinKei.add("役員報酬");
        listSalarySalary.add("給料手当");
        listZakkyuKei.add("雑給");
        listSalBonus.add("賞与・退職金");
        listSalary.add("人件費計");
        listYachin.add("家賃地代");
        listSuikouHi.add("水道光熱費");
        listRoyalty.add("ロイヤルティ");
        listTesuryo.add("支払手数料");
        listKoukoku.add("広告費");
        listShoumou.add("消耗品費");
        listHouteiFukuri.add("法定福利費");
        listFukuriKousei.add("福利厚生費");
        listKousai.add("交際費");
        listRyohi.add("旅費交通費");
        listTusin.add("通信費");
        listLease.add("賃貸リース料");
        listSharyo.add("車両費");
        listSozei.add("租税公課");
        listHoken.add("保険料");
        listUnchin.add("運賃");
        listShuzen.add("修繕費");
        listZappi.add("雑費");
        listKeihiShokei.add("経費小計");
        listShokyakuRieki.add("償却前利益");
        listGenkaShokyaku.add("減価償却費");
        listEigaiShueki.add("営業外収益");
        listEigaiHiyo.add("営業外費用");
        listHonshahiHai.add("共通費配賦");
        listZeibikiMaeRieki.add("税引前利益");
        //資金繰り
        listRieki.add("当月利益");
        listCashflow.add("キャッシュフロー");
        listKashiireDown.add("借入返済");
        listKappuDown.add("割賦支払");
//        listTouGenshoKei.add("支払合計");
        //当月残高
        listSashihikiYojyo.add("差引余剰金");
        listKashiireZandaka.add("借入金残高");
        listLeaseZandaka.add("リース残高");
        listKappuZandaka.add("割賦残高");
        listTouZandakaKei.add("借入金等残高");

        boolean flgSet = false;
        // 12ヶ月平均の対象月数
        int avgCount = 0; 
        // 13か月分ループ
        for (int i = OUTPUT_NUMBER; i >= 0 ; i--) {
            // 年月が一致するEntityを探す
            for (Iterator ite = dto.getListEntityTogetuPlusZennen().iterator(); ite.hasNext();) {
				UIPLDataInfo uiPlDataInfo = (UIPLDataInfo) ite.next();
                String month = DateManager.getPrevMonth(baseYM, i);
                String monthAvgTarget = DateManager.getPrevMonth(baseYM, OUTPUT_NUMBER - 1);
                if (month.equals(uiPlDataInfo.getPlYm())) {
                    // 一致した場合、各項目用Listに値をセット
                    listUriage.add(calcFcKingaku(dto, uiPlDataInfo.getUriage(), uiPlDataInfo.getUriagedaka()));
                    listBuppan.add(calcFcKingaku(dto, uiPlDataInfo.getBuppan(), uiPlDataInfo.getUriagedaka()));
                    listUriagedaka.add(calcFcKingaku(dto, uiPlDataInfo.getUriagedaka(), uiPlDataInfo.getUriagedaka()));
                    listGenzairyoKei.add(calcFcKingaku(dto, uiPlDataInfo.getGenzairyoKei(), uiPlDataInfo.getUriagedaka()));
                    listYasaiKei.add(calcFcKingaku(dto, uiPlDataInfo.getYasaiKei(), uiPlDataInfo.getUriagedaka()));
                    listHouzaiKei.add(calcFcKingaku(dto, uiPlDataInfo.getHouzaiKei(), uiPlDataInfo.getUriagedaka()));
                    listBuppanKei.add(calcFcKingaku(dto, uiPlDataInfo.getBuppanKei(), uiPlDataInfo.getUriagedaka()));
                    listUriageGenka.add(calcFcKingaku(dto, uiPlDataInfo.getUriagegenka(), uiPlDataInfo.getUriagedaka()));
                    listUriageSoRieki.add(calcFcKingaku(dto, uiPlDataInfo.getUriageSoRieki(), uiPlDataInfo.getUriagedaka()));
//                    listYakuinKei.add(calcFcKingaku(dto, uiPlDataInfo.getYakuinKei(), uiPlDataInfo.getUriagedaka()));
// add start 20060816 xkhata 役員報酬修正
                    listYakuinKei.add(calcFcKingaku(dto, uiPlDataInfo.getYakuinSalary(), uiPlDataInfo.getUriagedaka()));
// end
                    listSalarySalary.add(calcFcKingaku(dto, uiPlDataInfo.getSalarySalary(), uiPlDataInfo.getUriagedaka()));
//                    listZakkyuKei.add(calcFcKingaku(dto, uiPlDataInfo.getZakkyuKei(), uiPlDataInfo.getUriagedaka()));
// add start 20060815 xkhata 雑給修正
                    listZakkyuKei.add(calcFcKingaku(dto, uiPlDataInfo.getZakkyuSalary(), uiPlDataInfo.getUriagedaka()));
// end
                    listSalBonus.add(calcFcKingaku(dto, uiPlDataInfo.getSalBonus(), uiPlDataInfo.getUriagedaka()));
                    listSalary.add(calcFcKingaku(dto, uiPlDataInfo.getSalary(), uiPlDataInfo.getUriagedaka()));
                    listYachin.add(calcFcKingaku(dto, uiPlDataInfo.getYachin(), uiPlDataInfo.getUriagedaka()));
                    listSuikouHi.add(calcFcKingaku(dto, uiPlDataInfo.getSuikouHi(), uiPlDataInfo.getUriagedaka()));
                    listRoyalty.add(calcFcKingaku(dto, uiPlDataInfo.getRoyalty(), uiPlDataInfo.getUriagedaka()));
                    listTesuryo.add(calcFcKingaku(dto, uiPlDataInfo.getTesuryo(), uiPlDataInfo.getUriagedaka()));
                    listKoukoku.add(calcFcKingaku(dto, uiPlDataInfo.getKoukoku(), uiPlDataInfo.getUriagedaka()));
                    listShoumou.add(calcFcKingaku(dto, uiPlDataInfo.getShoumou(), uiPlDataInfo.getUriagedaka()));
                    listHouteiFukuri.add(calcFcKingaku(dto, uiPlDataInfo.getHouteiFukuri(), uiPlDataInfo.getUriagedaka()));
                    listFukuriKousei.add(calcFcKingaku(dto, uiPlDataInfo.getFukuriKousei(), uiPlDataInfo.getUriagedaka()));
                    listKousai.add(calcFcKingaku(dto, uiPlDataInfo.getKousai(), uiPlDataInfo.getUriagedaka()));
                    listRyohi.add(calcFcKingaku(dto, uiPlDataInfo.getRyohi(), uiPlDataInfo.getUriagedaka()));
                    listTusin.add(calcFcKingaku(dto, uiPlDataInfo.getTusin(), uiPlDataInfo.getUriagedaka()));
                    listLease.add(calcFcKingaku(dto, uiPlDataInfo.getLease(), uiPlDataInfo.getUriagedaka()));
                    listSharyo.add(calcFcKingaku(dto, uiPlDataInfo.getSharyo(), uiPlDataInfo.getUriagedaka()));
                    listSozei.add(calcFcKingaku(dto, uiPlDataInfo.getSozei(), uiPlDataInfo.getUriagedaka()));
                    listHoken.add(calcFcKingaku(dto, uiPlDataInfo.getHoken(), uiPlDataInfo.getUriagedaka()));
                    listUnchin.add(calcFcKingaku(dto, uiPlDataInfo.getUnchin(), uiPlDataInfo.getUriagedaka()));
                    listShuzen.add(calcFcKingaku(dto, uiPlDataInfo.getShuzen(), uiPlDataInfo.getUriagedaka()));
                    listZappi.add(calcFcKingaku(dto, uiPlDataInfo.getZappi(), uiPlDataInfo.getUriagedaka()));
                    listKeihiShokei.add(calcFcKingaku(dto, uiPlDataInfo.getKeihiShokei(), uiPlDataInfo.getUriagedaka()));
                    listShokyakuRieki.add(calcFcKingaku(dto, uiPlDataInfo.getShokyakuRieki(), uiPlDataInfo.getUriagedaka()));
                    listGenkaShokyaku.add(calcFcKingaku(dto, uiPlDataInfo.getGenkaShokyaku(), uiPlDataInfo.getUriagedaka()));
                    listEigaiShueki.add(calcFcKingaku(dto, uiPlDataInfo.getEigaiShueki(), uiPlDataInfo.getUriagedaka()));
                    listEigaiHiyo.add(calcFcKingaku(dto, uiPlDataInfo.getEigaiHiyo(), uiPlDataInfo.getUriagedaka()));
                    listHonshahiHai.add(calcFcKingaku(dto, uiPlDataInfo.getHonshahiHai(), uiPlDataInfo.getUriagedaka()));
                    listZeibikiMaeRieki.add(calcFcKingaku(dto, uiPlDataInfo.getRieki(), uiPlDataInfo.getUriagedaka()));
                    listRieki.add(calcFcKingaku(dto, uiPlDataInfo.getRieki(), uiPlDataInfo.getUriagedaka()));
                    listCashflow.add(calcFcKingaku(dto, uiPlDataInfo.getCashflow(), uiPlDataInfo.getUriagedaka()));
                    listKashiireDown.add(calcFcKingaku(dto, uiPlDataInfo.getKashiireDown(), uiPlDataInfo.getUriagedaka()));
                    listKappuDown.add(calcFcKingaku(dto, uiPlDataInfo.getKappuDown(), uiPlDataInfo.getUriagedaka()));
//                    listTouGenshoKei.add(calcFcKingaku(dto, uiPlDataInfo.getTouGenshoKei(), uiPlDataInfo.getUriagedaka()));
                    listSashihikiYojyo.add(calcFcKingaku(dto, uiPlDataInfo.getSashihikiYojyo(), uiPlDataInfo.getUriagedaka()));
                    listKashiireZandaka.add(calcFcKingaku(dto, uiPlDataInfo.getKashiireZandaka(), uiPlDataInfo.getUriagedaka()));
                    listLeaseZandaka.add(calcFcKingaku(dto, uiPlDataInfo.getLeaseZandaka(), uiPlDataInfo.getUriagedaka()));
                    listKappuZandaka.add(calcFcKingaku(dto, uiPlDataInfo.getKappuZandaka(), uiPlDataInfo.getUriagedaka()));
                    listTouZandakaKei.add(calcFcKingaku(dto, uiPlDataInfo.getTouZandakaKei(), uiPlDataInfo.getUriagedaka()));
                    //12ヶ月合計、平均
                    if (month.compareTo(monthAvgTarget) >= 0) {
                        if ( !PL_TYPE_EMPTY_MARK.equals(uiPlDataInfo.getPlType())) {
                        	avgCount++;
                        }
                        calc12Gokei(UIPLDataInfo.uriagedaka_COLUMN, uiPlDataInfo.getUriagedaka(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.buppan_COLUMN, uiPlDataInfo.getBuppan(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.uriage_COLUMN, uiPlDataInfo.getUriage(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.genzairyoKei_COLUMN, uiPlDataInfo.getGenzairyoKei(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.yasaiKei_COLUMN, uiPlDataInfo.getYasaiKei(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.houzaiKei_COLUMN, uiPlDataInfo.getHouzaiKei(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.buppanKei_COLUMN, uiPlDataInfo.getBuppanKei(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.uriagegenka_COLUMN, uiPlDataInfo.getUriagegenka(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.uriageSoRieki_COLUMN, uiPlDataInfo.getUriageSoRieki(), map12Gokei);
//                        calc12Gokei(UIPLDataInfo.yakuinKei_COLUMN, uiPlDataInfo.getYakuinKei(), map12Gokei);
// add start 20060816 xkhata 役員報酬修正
                        calc12Gokei(UIPLDataInfo.yakuinSalary_COLUMN, uiPlDataInfo.getYakuinSalary(), map12Gokei);
// end
                        calc12Gokei(UIPLDataInfo.salarySalary_COLUMN, uiPlDataInfo.getSalarySalary(), map12Gokei);
//                        calc12Gokei(UIPLDataInfo.zakkyuKei_COLUMN, uiPlDataInfo.getZakkyuKei(), map12Gokei);
// add start 20060815 xkhata 雑給修正
                        calc12Gokei(UIPLDataInfo.zakkyuSalary_COLUMN, uiPlDataInfo.getZakkyuSalary(), map12Gokei);
// end
                        calc12Gokei("SAL_BONUS", uiPlDataInfo.getSalBonus(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.salary_COLUMN, uiPlDataInfo.getSalary(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.yachin_COLUMN, uiPlDataInfo.getYachin(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.suikouHi_COLUMN, uiPlDataInfo.getSuikouHi(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.royalty_COLUMN, uiPlDataInfo.getRoyalty(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.tesuryo_COLUMN, uiPlDataInfo.getTesuryo(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.koukoku_COLUMN, uiPlDataInfo.getKoukoku(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.shoumou_COLUMN, uiPlDataInfo.getShoumou(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.houteiFukuri_COLUMN, uiPlDataInfo.getHouteiFukuri(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.fukuriKousei_COLUMN, uiPlDataInfo.getFukuriKousei(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.kousai_COLUMN, uiPlDataInfo.getKousai(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.ryohi_COLUMN, uiPlDataInfo.getRyohi(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.tusin_COLUMN, uiPlDataInfo.getTusin(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.lease_COLUMN, uiPlDataInfo.getLease(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.sharyo_COLUMN, uiPlDataInfo.getSharyo(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.sozei_COLUMN, uiPlDataInfo.getSozei(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.hoken_COLUMN, uiPlDataInfo.getHoken(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.unchin_COLUMN, uiPlDataInfo.getUnchin(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.shuzen_COLUMN, uiPlDataInfo.getShuzen(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.zappi_COLUMN, uiPlDataInfo.getZappi(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.keihiShokei_COLUMN, uiPlDataInfo.getKeihiShokei(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.shokyakuRieki_COLUMN, uiPlDataInfo.getShokyakuRieki(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.genkaShokyaku_COLUMN, uiPlDataInfo.getGenkaShokyaku(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.eigaiShueki_COLUMN, uiPlDataInfo.getEigaiShueki(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.eigaiHiyo_COLUMN, uiPlDataInfo.getEigaiHiyo(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.honshahiHai_COLUMN, uiPlDataInfo.getHonshahiHai(), map12Gokei);
                        calc12Gokei("ZEIBIKIMAE_RIEKI", uiPlDataInfo.getRieki(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.rieki_COLUMN, uiPlDataInfo.getRieki(), map12Gokei);
                        calc12Gokei("CASH_FLOW", uiPlDataInfo.getCashflow(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.kashiireDown_COLUMN, uiPlDataInfo.getKashiireDown(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.kappuDown_COLUMN, uiPlDataInfo.getKappuDown(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.touGenshoKei_COLUMN, uiPlDataInfo.getTouGenshoKei(), map12Gokei);
                        calc12Gokei("SASHIHIKI_YOJYO", uiPlDataInfo.getSashihikiYojyo(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.kashiireZandaka_COLUMN, uiPlDataInfo.getKashiireZandaka(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.leaseZandaka_COLUMN, uiPlDataInfo.getLeaseZandaka(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.kappuZandaka_COLUMN, uiPlDataInfo.getKappuZandaka(), map12Gokei);
                        calc12Gokei(UIPLDataInfo.touZandakaKei_COLUMN, uiPlDataInfo.getTouZandakaKei(), map12Gokei);
                    }
                    flgSet = true;
                    break;
                }
			}
            // 見つからない場合、空文字をセット
            if (!flgSet) {
                listUriage.add("");
                listBuppan.add("");
                listUriagedaka.add("");
                listGenzairyoKei.add("");
                listYasaiKei.add("");
                listHouzaiKei.add("");
                listBuppanKei.add("");
                listUriageGenka.add("");
                listUriageSoRieki.add("");
                listYakuinKei.add("");
                listSalarySalary.add("");
                listZakkyuKei.add("");
                listSalBonus.add("");
                listSalary.add("");
                listYachin.add("");
                listSuikouHi.add("");
                listRoyalty.add("");
                listTesuryo.add("");
                listKoukoku.add("");
                listShoumou.add("");
                listHouteiFukuri.add("");
                listFukuriKousei.add("");
                listKousai.add("");
                listRyohi.add("");
                listTusin.add("");
                listLease.add("");
                listSharyo.add("");
                listSozei.add("");
                listHoken.add("");
                listUnchin.add("");
                listShuzen.add("");
                listZappi.add("");
                listKeihiShokei.add("");
                listShokyakuRieki.add("");
                listGenkaShokyaku.add("");
                listEigaiShueki.add("");
                listEigaiHiyo.add("");
                listHonshahiHai.add("");
                listZeibikiMaeRieki.add("");
                listRieki.add("");
                listCashflow.add("");
                listKashiireDown.add("");
                listKappuDown.add("");
//                listTouGenshoKei.add("");
                listSashihikiYojyo.add("");
                listKashiireZandaka.add("");
                listLeaseZandaka.add("");
                listKappuZandaka.add("");
                listTouZandakaKei.add("");
            }
            flgSet = false;
        }

        // 12ヶ月合計、平均セット
//        set12GokeiAvg(listUriage, UIPLDataInfo.uriage_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listBuppan, UIPLDataInfo.buppan_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listUriagedaka, UIPLDataInfo.uriagedaka_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listGenzairyoKei, UIPLDataInfo.genzairyoKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listYasaiKei, UIPLDataInfo.yasaiKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listHouzaiKei, UIPLDataInfo.houzaiKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listBuppanKei, UIPLDataInfo.buppanKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listUriageGenka, UIPLDataInfo.uriagegenka_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listUriageSoRieki, UIPLDataInfo.uriageSoRieki_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listYakuinKei, UIPLDataInfo.yakuinKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listSalarySalary, UIPLDataInfo.salarySalary_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listZakkyuKei, UIPLDataInfo.zakkyuKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listSalBonus, "SAL_BONUS", avgCount, map12Gokei);
//        set12GokeiAvg(listSalary, UIPLDataInfo.salary_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listYachin, UIPLDataInfo.yachin_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listSuikouHi, UIPLDataInfo.suikouHi_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listRoyalty, UIPLDataInfo.royalty_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listTesuryo, UIPLDataInfo.tesuryo_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listKoukoku, UIPLDataInfo.koukoku_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listShoumou, UIPLDataInfo.shoumou_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listHouteiFukuri, UIPLDataInfo.houteiFukuri_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listFukuriKousei, UIPLDataInfo.fukuriKousei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listKousai, UIPLDataInfo.kousai_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listRyohi, UIPLDataInfo.ryohi_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listTusin, UIPLDataInfo.tusin_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listLease, UIPLDataInfo.lease_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listSharyo, UIPLDataInfo.sharyo_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listSozei, UIPLDataInfo.sozei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listHoken, UIPLDataInfo.hoken_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listUnchin, UIPLDataInfo.unchin_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listShuzen, UIPLDataInfo.shuzen_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listZappi, UIPLDataInfo.zappi_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listKeihiShokei, UIPLDataInfo.keihiShokei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listShokyakuRieki, UIPLDataInfo.shokyakuRieki_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listGenkaShokyaku, UIPLDataInfo.genkaShokyaku_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listEigaiShueki, UIPLDataInfo.eigaiShueki_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listEigaiHiyo, UIPLDataInfo.eigaiHiyo_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listHonshahiHai, UIPLDataInfo.honshahiHai_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listZeibikiMaeRieki, "ZEIBIKIMAE_RIEKI", avgCount, map12Gokei);
//        set12GokeiAvg(listRieki, UIPLDataInfo.rieki_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listCashflow, "CASH_FLOW", avgCount, map12Gokei);
//        set12GokeiAvg(listKashiireDown, UIPLDataInfo.kashiireDown_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listKappuDown, UIPLDataInfo.kappuDown_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listTouGenshoKei, UIPLDataInfo.touGenshoKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listSashihikiYojyo, UIPLDataInfo.sashihikiKei_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listKashiireZandaka, UIPLDataInfo.kashiireZandaka_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listLeaseZandaka, UIPLDataInfo.leaseZandaka_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listKappuZandaka, UIPLDataInfo.kappuZandaka_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvg(listTouZandakaKei, UIPLDataInfo.touZandakaKei_COLUMN, avgCount, map12Gokei);

// add start 20060814 xkhata 12ヶ月合計、平均構成比出力バグ対応
        set12GokeiAvgRepair(dto,listUriage, UIPLDataInfo.uriage_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listBuppan, UIPLDataInfo.buppan_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listUriagedaka, UIPLDataInfo.uriagedaka_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listGenzairyoKei, UIPLDataInfo.genzairyoKei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listYasaiKei, UIPLDataInfo.yasaiKei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listHouzaiKei, UIPLDataInfo.houzaiKei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listBuppanKei, UIPLDataInfo.buppanKei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listUriageGenka, UIPLDataInfo.uriagegenka_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listUriageSoRieki, UIPLDataInfo.uriageSoRieki_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listYakuinKei, UIPLDataInfo.yakuinSalary_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listSalarySalary, UIPLDataInfo.salarySalary_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listZakkyuKei, UIPLDataInfo.zakkyuSalary_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listSalBonus, "SAL_BONUS", avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listSalary, UIPLDataInfo.salary_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listYachin, UIPLDataInfo.yachin_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listSuikouHi, UIPLDataInfo.suikouHi_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listRoyalty, UIPLDataInfo.royalty_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listTesuryo, UIPLDataInfo.tesuryo_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listKoukoku, UIPLDataInfo.koukoku_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listShoumou, UIPLDataInfo.shoumou_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listHouteiFukuri, UIPLDataInfo.houteiFukuri_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listFukuriKousei, UIPLDataInfo.fukuriKousei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listKousai, UIPLDataInfo.kousai_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listRyohi, UIPLDataInfo.ryohi_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listTusin, UIPLDataInfo.tusin_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listLease, UIPLDataInfo.lease_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listSharyo, UIPLDataInfo.sharyo_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listSozei, UIPLDataInfo.sozei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listHoken, UIPLDataInfo.hoken_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listUnchin, UIPLDataInfo.unchin_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listShuzen, UIPLDataInfo.shuzen_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listZappi, UIPLDataInfo.zappi_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listKeihiShokei, UIPLDataInfo.keihiShokei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listShokyakuRieki, UIPLDataInfo.shokyakuRieki_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listGenkaShokyaku, UIPLDataInfo.genkaShokyaku_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listEigaiShueki, UIPLDataInfo.eigaiShueki_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listEigaiHiyo, UIPLDataInfo.eigaiHiyo_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listHonshahiHai, UIPLDataInfo.honshahiHai_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listZeibikiMaeRieki, "ZEIBIKIMAE_RIEKI", avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listRieki, UIPLDataInfo.rieki_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listCashflow, "CASH_FLOW", avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listKashiireDown, UIPLDataInfo.kashiireDown_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listKappuDown, UIPLDataInfo.kappuDown_COLUMN, avgCount, map12Gokei);
//        set12GokeiAvgRepair(dto,listTouGenshoKei, UIPLDataInfo.touGenshoKei_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listSashihikiYojyo, "SASHIHIKI_YOJYO", avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listKashiireZandaka, UIPLDataInfo.kashiireZandaka_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listLeaseZandaka, UIPLDataInfo.leaseZandaka_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listKappuZandaka, UIPLDataInfo.kappuZandaka_COLUMN, avgCount, map12Gokei);
        set12GokeiAvgRepair(dto,listTouZandakaKei, UIPLDataInfo.touZandakaKei_COLUMN, avgCount, map12Gokei);
// end
        // CSV出力用Listにセット
        //詳細
        listCsv.add(listUriage);
        listCsv.add(listBuppan);
        listCsv.add(listUriagedaka);
        listCsv.add(listGenzairyoKei);
        listCsv.add(listYasaiKei);
        listCsv.add(listHouzaiKei);
        listCsv.add(listBuppanKei);
        listCsv.add(listUriageGenka);
        listCsv.add(listUriageSoRieki);
        listCsv.add(listYakuinKei);
        listCsv.add(listSalarySalary);
        listCsv.add(listZakkyuKei);
        listCsv.add(listSalBonus);
        listCsv.add(listSalary);
        listCsv.add(listYachin);
        listCsv.add(listSuikouHi);
        listCsv.add(listRoyalty);
        listCsv.add(listTesuryo);
        listCsv.add(listKoukoku);
        listCsv.add(listShoumou);
        listCsv.add(listHouteiFukuri);
        listCsv.add(listFukuriKousei);
        listCsv.add(listKousai);
        listCsv.add(listRyohi);
        listCsv.add(listTusin);
        listCsv.add(listLease);
        listCsv.add(listSharyo);
        listCsv.add(listSozei);
        listCsv.add(listHoken);
        listCsv.add(listUnchin);
        listCsv.add(listShuzen);
        listCsv.add(listZappi);
        listCsv.add(listKeihiShokei);
        listCsv.add(listShokyakuRieki);
        listCsv.add(listGenkaShokyaku);
        listCsv.add(listEigaiShueki);
        listCsv.add(listEigaiHiyo);
        listCsv.add(listHonshahiHai);
        listCsv.add(listZeibikiMaeRieki);
        //資金繰り
        listCsv.add(listBlank);
        listCsv.add(listMidasi1);
        listCsv.add(listRieki);
        listCsv.add(listGenkaShokyaku);
        listCsv.add(listCashflow);
        listCsv.add(listKashiireDown);
        listCsv.add(listKappuDown);
//        listCsv.add(listTouGenshoKei);
        listCsv.add(listSashihikiYojyo);
        //当月残高
        listCsv.add(listBlank);
        listCsv.add(listMidasi2);
        listCsv.add(listKashiireZandaka);
        listCsv.add(listLeaseZandaka);
        listCsv.add(listKappuZandaka);
        listCsv.add(listTouZandakaKei);
    }
    
    /**
     * データ部 （RC結果画面）
     * @param dto
     * @param listCsv
     * @throws Exception
     */
    private void makeDataRC(PlInfoViewDto dto, List listCsv) throws Exception {
        // 条件選択年月
        String condYM = dto.getCondTargetYM();
        //年度
        String nendo = DateManager.getCurrentYear(condYM);
        // 構成比ありか？
        boolean isUserKoseihi = (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_RC_KOSEHI_ARI));
        // 項目ヘッダ
        makeRCKoumokuHeader(dto, listCsv);
        
        /* 明細部 */
        List listKoumoku = getUiPlRcDataInfoDao()
                                .getPLRCKoumokuCsv(dto.getCondPlType(),
                                                condYM,
                                                nendo + "04",
                                                dto.getCondCompanyCd(),
                                                dto.getCondMiseCd());
        // RC CSV用のデータ取得（当年度）
        List listRCEntity = getUiPlRcDataInfoDao()
                                .getPLRCDataCsv(dto.getCondPlType(),
                                                condYM,
                                                nendo + "04",
                                                dto.getCondCompanyCd(),
                                                dto.getCondMiseCd());
        
        // 当年期データ
        Map mapTounenData = makeRCTounenData(dto, listRCEntity);
        // 前年データ
        Map mapZennenData = makeRCZennenData(dto);
        
        // 構成比の算出元値をセット
        Map mapKouseihiMoto = new HashMap();
        for (Iterator ite = listRCEntity.iterator(); ite.hasNext();) {
            UIPLRCDataInfo entity = (UIPLRCDataInfo) ite.next();
            if ("1".equals(entity.getKouseihiMoto())) {
                mapKouseihiMoto.put(entity.getPlYm(), entity.getKingaku());
            }
        }

        int entityCnt = 0;
        for (int i = 0; i < listKoumoku.size(); i++) {
            List listRow = new ArrayList();
            UIPLRCDataInfo entityKoumokuInfo = (UIPLRCDataInfo) listKoumoku.get(i);
            //項目名セット
            listRow.add(entityKoumokuInfo.getKoumokuName());
            //上期計、下期計
            BigDecimal bigKamikiKei = new BigDecimal("0");
            BigDecimal bigKamikiKoseiMoto = new BigDecimal("0");
            BigDecimal bigSimokiKei = new BigDecimal("0");
            BigDecimal bigSimokiKoseiMoto = new BigDecimal("0");
            //上期
            for (int j = 0; j < 6; j++) {
                BigDecimal bigKingaku = new BigDecimal("0");
                BigDecimal bigKoseihi = new BigDecimal("0.00");
                String targetYM = DateManager.getNextMonth(nendo + "04", j);
                if (entityCnt < listRCEntity.size()) { 
                    UIPLRCDataInfo entity = (UIPLRCDataInfo) listRCEntity.get(entityCnt);
                    if (entityKoumokuInfo.getKoumokuNo().equals(entity.getKoumokuNo())
                            && entity.getPlYm().equals(targetYM)) {
                        bigKingaku = entity.getKingaku();
                        bigKoseihi = Calculator.percentage(bigKingaku, (BigDecimal)mapKouseihiMoto.get(targetYM), 2);
                        bigKamikiKei = bigKamikiKei.add(bigKingaku);
                        bigKamikiKoseiMoto = bigKamikiKoseiMoto.add((BigDecimal)mapKouseihiMoto.get(targetYM));
                        entityCnt++;
                    }
                }
                listRow.add(bigKingaku);
                if (isUserKoseihi) {
                    listRow.add(bigKoseihi);
                }
            }
            //上期計
            listRow.add(bigKamikiKei);
            //  上期計 構成比元
            BigDecimal bigKamiMoto = new BigDecimal("0");
            if (mapTounenData.containsKey(entityKoumokuInfo.getKoumokuNo() + "_KM")) {
                bigKamiMoto = (BigDecimal) mapTounenData.get(entityKoumokuInfo.getKoumokuNo() + "_KM");
            }
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigKamikiKei, bigKamiMoto, 2));
            }
            //前年上期
            BigDecimal bigZenKamiki = new BigDecimal("0");
            if (mapZennenData.containsKey(entityKoumokuInfo.getKoumokuNo() + "_K")) {
                bigZenKamiki = (BigDecimal) mapZennenData.get(entityKoumokuInfo.getKoumokuNo() + "_K");
            }
            listRow.add(bigZenKamiki);
            //  前年上期 構成比元
            BigDecimal bigZenKamiMoto = new BigDecimal("0");
            if (mapZennenData.containsKey(entityKoumokuInfo.getKoumokuNo() + "_KM")) {
                bigKamiMoto = (BigDecimal) mapZennenData.get(entityKoumokuInfo.getKoumokuNo() + "_KM");
            }
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigZenKamiki, bigZenKamiMoto, 2));
            }
            //上期前年比較
            listRow.add(bigKamikiKei.subtract(bigZenKamiki));
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigKamikiKei, bigZenKamiki, 2));
            }
            //下期
            for (int j = 6; j < 12; j++) {
                BigDecimal bigKingaku = new BigDecimal("0");
                BigDecimal bigKoseihi = new BigDecimal("0.00");
                String targetYM = DateManager.getNextMonth(nendo + "04", j);
                if (entityCnt < listRCEntity.size()) {
                    UIPLRCDataInfo entity = (UIPLRCDataInfo) listRCEntity.get(entityCnt);
                    if (entityKoumokuInfo.getKoumokuNo().equals(entity.getKoumokuNo())
                            && entity.getPlYm().equals(targetYM)) {
                        bigKingaku = entity.getKingaku();
                        bigKoseihi = Calculator.percentage(bigKingaku, (BigDecimal)mapKouseihiMoto.get(targetYM), 2);
                        bigSimokiKei = bigSimokiKei.add(bigKingaku);
                        bigSimokiKoseiMoto = bigSimokiKoseiMoto.add((BigDecimal)mapKouseihiMoto.get(targetYM));
                        entityCnt++;
                    }
                }
                listRow.add(bigKingaku);
                if (isUserKoseihi) {
                    listRow.add(bigKoseihi);
                }
            }
            //下期計
            listRow.add(bigSimokiKei);
            //  下期計 構成比元
            BigDecimal bigSimotMoto = new BigDecimal("0");
            if (mapTounenData.containsKey(entityKoumokuInfo.getKoumokuNo() + "_SM")) {
                bigSimotMoto = (BigDecimal) mapTounenData.get(entityKoumokuInfo.getKoumokuNo() + "_SM");
            }
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigSimokiKei, bigSimotMoto, 2));
            }
            //前年下期
            BigDecimal bigZenSimoki = new BigDecimal("0");
            if (mapZennenData.containsKey(entityKoumokuInfo.getKoumokuNo() + "_S")) {
                bigZenSimoki = (BigDecimal) mapZennenData.get(entityKoumokuInfo.getKoumokuNo() + "_S");
            }
            listRow.add(bigZenSimoki);
            //  前年下期 構成比元
            BigDecimal bigZenSimoMoto = new BigDecimal("0");
            if (mapZennenData.containsKey(entityKoumokuInfo.getKoumokuNo() + "_SM")) {
                bigZenSimoMoto = (BigDecimal) mapZennenData.get(entityKoumokuInfo.getKoumokuNo() + "_SM");
            }
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigZenSimoki, bigZenSimoMoto, 2));
            }
            //下期前年比較
            listRow.add(bigSimokiKei.subtract(bigZenSimoki));
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigSimokiKei, bigZenSimoki, 2));
            }
            //年度計
            listRow.add(bigKamikiKei.add(bigSimokiKei));
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigKamikiKei.add(bigSimokiKei), bigKamiMoto.add(bigSimotMoto), 2));
            }
            //前年度
            listRow.add(bigZenKamiki.add(bigZenSimoki));
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigZenKamiki.add(bigZenSimoki), bigZenKamiMoto.add(bigZenSimoMoto), 2));
            }
            //前年度比較
            listRow.add((bigKamikiKei.add(bigSimokiKei)).subtract(bigZenKamiki.add(bigZenSimoki)));
            if (isUserKoseihi) {
                listRow.add(Calculator.percentage(bigKamikiKei.add(bigSimokiKei), bigZenKamiki.add(bigZenSimoki), 2));
            }
            listCsv.add(listRow);
        }

    }
    
    private Map makeRCTounenData(PlInfoViewDto dto, List listRCEntity) throws Exception {
        Map mapData = new HashMap();
        // 条件選択年月
        String condYM = dto.getCondTargetYM();
        //年度
        String nendo = DateManager.getCurrentYear(condYM);
//        // RC CSV用のデータ取得（前年度）
//        List listRCZenEntity = getUiPlRcDataInfoDao()
//                                .getPLRCDataCsv(dto.getCondPlType(),
//                                                DateManager.getPrevMonth(condYM, 12),
//                                                DateManager.getPrevYear(nendo, 1) + "04",
//                                                dto.getCondCompanyCd(),
//                                                dto.getCondMiseCd());
        
        String oldKoumokuNo = "";
        String kouseihiMoto = "";
        BigDecimal bigKingakuKamiki = new BigDecimal("0");
        BigDecimal bigKingakuSimoki = new BigDecimal("0");
        for (Iterator ite = listRCEntity.iterator(); ite.hasNext();) {
            UIPLRCDataInfo entity = (UIPLRCDataInfo) ite.next();
            kouseihiMoto = entity.getKouseihiMoto();
            if (condYM.compareTo(entity.getPlYm()) > 0) {
                continue;
            }
            if (!oldKoumokuNo.equals("") && !oldKoumokuNo.equals(entity.getKoumokuNo())) {
                mapData.put(oldKoumokuNo + "_K", bigKingakuKamiki);
                mapData.put(oldKoumokuNo + "_S", bigKingakuSimoki);
                if ("1".equals(kouseihiMoto)) {
                    mapData.put(oldKoumokuNo + "_KM", bigKingakuKamiki);
                    mapData.put(oldKoumokuNo + "_SM", bigKingakuSimoki);
                }
                bigKingakuKamiki = new BigDecimal("0");
                bigKingakuSimoki = new BigDecimal("0");                
            }
            if (entity.getPlYm().compareTo(DateManager.getNextMonth(nendo + "04", 5)) > 0) {
                bigKingakuSimoki = bigKingakuSimoki.add(entity.getKingaku());
            }
            else {
                bigKingakuKamiki = bigKingakuKamiki.add(entity.getKingaku());
            }
            oldKoumokuNo = entity.getKoumokuNo();
        }
        if (!mapData.containsKey(oldKoumokuNo)) {
            mapData.put(oldKoumokuNo + "_K", bigKingakuKamiki);
            mapData.put(oldKoumokuNo + "_S", bigKingakuSimoki);
            if ("1".equals(kouseihiMoto)) {
                mapData.put(oldKoumokuNo + "_KM", bigKingakuKamiki);
                mapData.put(oldKoumokuNo + "_SM", bigKingakuSimoki);
            }
        }
        
        return mapData;
    }
    
    private Map makeRCZennenData(PlInfoViewDto dto) throws Exception {
        Map mapData = new HashMap();
        // 条件選択年月
        String condYM = dto.getCondTargetYM();
        //年度
        String nendo = DateManager.getCurrentYear(condYM);
        // RC CSV用のデータ取得（前年度）
        List listRCZenEntity = getUiPlRcDataInfoDao()
                                .getPLRCDataCsv(dto.getCondPlType(),
                                                DateManager.getPrevMonth(condYM, 12),
                                                DateManager.getPrevYear(nendo, 1) + "04",
                                                dto.getCondCompanyCd(),
                                                dto.getCondMiseCd());
        
        String oldKoumokuNo = "";
        String kouseihiMoto = "";
        BigDecimal bigKingakuKamiki = new BigDecimal("0");
        BigDecimal bigKingakuSimoki = new BigDecimal("0");
        for (Iterator ite = listRCZenEntity.iterator(); ite.hasNext();) {
            UIPLRCDataInfo entity = (UIPLRCDataInfo) ite.next();
            kouseihiMoto = entity.getKouseihiMoto();
            if (!oldKoumokuNo.equals("") && !oldKoumokuNo.equals(entity.getKoumokuNo())) {
                mapData.put(oldKoumokuNo + "_K", bigKingakuKamiki);
                mapData.put(oldKoumokuNo + "_S", bigKingakuSimoki);
                if ("1".equals(kouseihiMoto)) {
                    mapData.put(oldKoumokuNo + "_KM", bigKingakuKamiki);
                    mapData.put(oldKoumokuNo + "_SM", bigKingakuSimoki);
                }
                bigKingakuKamiki = new BigDecimal("0");
                bigKingakuSimoki = new BigDecimal("0");                
            }
            //--- 2007/07/01 変数nendoは条件画面で指定された年月の年度なので、1年マイナスする
            //if (entity.getPlYm().compareTo(DateManager.getNextMonth(nendo + "04", 5)) > 0) {
            if (entity.getPlYm().compareTo(DateManager.getNextMonth(DateManager.getPrevYear(nendo, 1) + "04", 5)) > 0) {
                bigKingakuSimoki = bigKingakuSimoki.add(entity.getKingaku());
            }
            else {
                bigKingakuKamiki = bigKingakuKamiki.add(entity.getKingaku());
            }
            oldKoumokuNo = entity.getKoumokuNo();
        }
        if (!mapData.containsKey(oldKoumokuNo)) {
            mapData.put(oldKoumokuNo + "_K", bigKingakuKamiki);
            mapData.put(oldKoumokuNo + "_S", bigKingakuSimoki);
            if ("1".equals(kouseihiMoto)) {
                mapData.put(oldKoumokuNo + "_KM", bigKingakuKamiki);
                mapData.put(oldKoumokuNo + "_SM", bigKingakuSimoki);
            }
        }
        
        return mapData;
    }
    
    private void makeRCKoumokuHeader(PlInfoViewDto dto, List listCsv) throws Exception {
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        // 条件選択年月
        String condYM = dto.getCondTargetYM();
        //年度
        String nendo = DateManager.getCurrentYear(condYM);
        // 構成比ありか？
        boolean isUserKoseihi = (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_RC_KOSEHI_ARI));
        
        List listKoumokuHeader1 = new ArrayList();
        List listKoumokuHeader2 = new ArrayList();
        listKoumokuHeader1.add("");
        listKoumokuHeader2.add("項目");
        //上期
        for (int i = 0; i < 6; i++) {
            listKoumokuHeader1.add(dateFormatter.format(DateManager.getNextMonth(nendo + "04", i), true));
            listKoumokuHeader2.add("金額");
            if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_RC_KOSEHI_ARI)) {
                listKoumokuHeader1.add("");
                listKoumokuHeader2.add("構成比");
            }
        }
        listKoumokuHeader1.add("上期計");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        listKoumokuHeader1.add("前年上期");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        listKoumokuHeader1.add("上期前年比較");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        
        for (int i = 6; i < 12; i++) {
            listKoumokuHeader1.add(dateFormatter.format(DateManager.getNextMonth(nendo + "04", i), true));
            listKoumokuHeader2.add("金額");
            if (dto.getCsvMode().equals(PlInfoViewDto.CSV_MODE_RC_KOSEHI_ARI)) {
                listKoumokuHeader1.add("");
                listKoumokuHeader2.add("構成比");
            }
        }
        //下期ヘッダ
        listKoumokuHeader1.add("下期計");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        listKoumokuHeader1.add("前年下期");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        listKoumokuHeader1.add("下期前年比較");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        listKoumokuHeader1.add("年度計");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        listKoumokuHeader1.add("前年度");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }
        listKoumokuHeader1.add("前年度比較");
        listKoumokuHeader2.add("金額");
        if (isUserKoseihi) {
            listKoumokuHeader1.add("");
            listKoumokuHeader2.add("構成比");
        }

        listCsv.add(listKoumokuHeader1);
        listCsv.add(listKoumokuHeader2);

    }
    /**
     * データ部 （FC結果画面）
     * @param dto
     * @param listCsv
     * @throws Exception
     */
    private void makeDataGenka(PlInfoViewDto dto, List listCsv) throws Exception {
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        String csvMode = dto.getCsvMode();
        
        // 項目ヘッダ
        List listKoumokuHeader = new ArrayList();
        if (PlInfoViewDto.CSV_MODE_GENKA_KONETUHI_INFO.equals(csvMode)) {
            listKoumokuHeader.add("年月");
            listKoumokuHeader.add("電気");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("ガス");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("水道");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("その他");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("合計");
            listKoumokuHeader.add("売上比");
        }
        else if (PlInfoViewDto.CSV_MODE_GENKA_KEIHI_INFO.equals(csvMode)) {
            listKoumokuHeader.add("年月");
            listKoumokuHeader.add("その他１");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("その他２");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("その他３");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("合計");
            listKoumokuHeader.add("売上比");
        }
        else {
            listKoumokuHeader.add("年月");
            listKoumokuHeader.add("前月在庫");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("当月仕入");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("当月在庫");
            listKoumokuHeader.add("売上比");
            listKoumokuHeader.add("原価");
            listKoumokuHeader.add("原価率");
        }
        listCsv.add(listKoumokuHeader);
        
        //データ部
        List listEntity = dto.getListEntityTogetuPlusZennen();
        
        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
        	UIPLDataInfo entity = (UIPLDataInfo) ite.next();
            List listRow = new ArrayList();
            listRow.add(dateFormatter.format(entity.getPlYm(), true));
            if (PlInfoViewDto.CSV_MODE_GENKA_GENZAIRYO_INFO.equals(csvMode)) {
            	listRow.add(entity.getGenzairyoZenZaiko());
                listRow.add(calcKosehi(entity.getGenzairyoZenZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getGenzairyoShire());
                listRow.add(calcKosehi(entity.getGenzairyoShire(), entity.getUriagedaka()));
                listRow.add(entity.getGenzairyoZaiko());
                listRow.add(calcKosehi(entity.getGenzairyoZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getGenzairyoKei());
                listRow.add(calcKosehi(entity.getGenzairyoKei(), entity.getUriagedaka()));
            }
            else if (PlInfoViewDto.CSV_MODE_GENKA_BUPPAN_INFO.equals(csvMode)) {
                listRow.add(entity.getBuppanZenZaiko());
                listRow.add(calcKosehi(entity.getBuppanZenZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getBuppanShire());
                listRow.add(calcKosehi(entity.getBuppanShire(), entity.getUriagedaka()));
                listRow.add(entity.getBuppanZaiko());
                listRow.add(calcKosehi(entity.getBuppanZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getBuppanKei());
                listRow.add(calcKosehi(entity.getBuppanKei(), entity.getUriagedaka()));
            }
            else if (PlInfoViewDto.CSV_MODE_GENKA_HOUSO_INFO.equals(csvMode)) {
                listRow.add(entity.getHouzaiZenZaiko());
                listRow.add(calcKosehi(entity.getHouzaiZenZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getHouzaiShire());
                listRow.add(calcKosehi(entity.getHouzaiShire(), entity.getUriagedaka()));
                listRow.add(entity.getHouzaiZaiko());
                listRow.add(calcKosehi(entity.getHouzaiZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getHouzaiKei());
                listRow.add(calcKosehi(entity.getHouzaiKei(), entity.getUriagedaka()));
            }
            else if (PlInfoViewDto.CSV_MODE_GENKA_KEIHI_INFO.equals(csvMode)) {
                listRow.add(entity.getYobi());
                listRow.add(calcKosehi(entity.getYobi(), entity.getUriagedaka()));
                listRow.add(entity.getYobi());
                listRow.add(calcKosehi(entity.getYobi(), entity.getUriagedaka()));
                listRow.add(entity.getYobi());
                listRow.add(calcKosehi(entity.getYobi(), entity.getUriagedaka()));
                listRow.add(entity.getKeihiShokei());
                listRow.add(calcKosehi(entity.getKeihiShokei(), entity.getUriagedaka()));
            }
            else if (PlInfoViewDto.CSV_MODE_GENKA_KONETUHI_INFO.equals(csvMode)) {
                listRow.add(entity.getElec());
                listRow.add(calcKosehi(entity.getElec(), entity.getUriagedaka()));
                listRow.add(entity.getGas());
                listRow.add(calcKosehi(entity.getGas(), entity.getUriagedaka()));
                listRow.add(entity.getWater());
                listRow.add(calcKosehi(entity.getWater(), entity.getUriagedaka()));
                listRow.add(entity.getSonota());
                listRow.add(calcKosehi(entity.getSonota(), entity.getUriagedaka()));
                listRow.add(entity.getSuikouUchiwake());
                listRow.add(calcKosehi(entity.getSuikouUchiwake(), entity.getUriagedaka()));
            }
            else if (PlInfoViewDto.CSV_MODE_GENKA_YASAI_INFO.equals(csvMode)) {
                listRow.add(entity.getYasaiZenZaiko());
                listRow.add(calcKosehi(entity.getYasaiZenZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getYasaiShire());
                listRow.add(calcKosehi(entity.getYasaiShire(), entity.getUriagedaka()));
                listRow.add(entity.getYasaiZaiko());
                listRow.add(calcKosehi(entity.getYasaiZaiko(), entity.getUriagedaka()));
                listRow.add(entity.getYasaiKei());
                listRow.add(calcKosehi(entity.getYasaiKei(), entity.getUriagedaka()));
            }
            
            listCsv.add(listRow);
        }
        
    }
    
    /**
     * FC版 金額、構成比取得処理
     * @param dto
     * @param big 構成比算出対象の金額
     * @param bigUriageDaka 売上高
     * @return
     */
    private BigDecimal calcFcKingaku(PlInfoViewDto dto, BigDecimal big, BigDecimal bigUriageDaka) {
        BigDecimal retBig = new BigDecimal("0");
        
        if (big != null) {
            if ("04".equals(dto.getCsvMode())) {
                // 構成比算出
                retBig = Calculator.percentage(big, bigUriageDaka, 2);
            }
            else {
            	retBig = big;
            }
        }
        
        return retBig;
    }

    /**
     * 構成比算出
     * @param big 算出もとの数値
     * @param bigUriagedaka 売上高
     * @return 小数2桁の構成比
     */
    private String calcKosehi(BigDecimal big, BigDecimal bigUriagedaka) {
        BigDecimal kosehi = new BigDecimal("0");
        if (big != null && bigUriagedaka != null) {
            kosehi = Calculator.percentage(big, bigUriagedaka, 2);
        }
        
        return kosehi.toString();
    }

    /**
     * 12ヶ月合計、平均セットする
     * @param List 行情報
     * @param String セットする項目
     * @param int 平均計算に使用する月数
     */
    private void set12GokeiAvg(List listData, String key, int cntAvg, Map map12Gokei) {
        BigDecimal bigSum = new BigDecimal("0");
        if (map12Gokei.containsKey(key)) {
        	bigSum = (BigDecimal) map12Gokei.get(key);
        }
        listData.add(bigSum);
        listData.add(Calculator.divide(bigSum, new BigDecimal(String.valueOf(cntAvg))));
    }

// add start 20060814 xkhata 12ヶ月合計、平均構成比出力バグ対応    

    /**
     * 12ヶ月合計、平均セットする
     * @param dto
     * @param List 行情報
     * @param String セットする項目
     * @param int 平均計算に使用する月数
     */
    public void set12GokeiAvgRepair(PlInfoViewDto dto, List listData, String key, int cntAvg, Map map12Gokei) {
        BigDecimal bigSum = new BigDecimal("0");
        BigDecimal bigUriagedaka = new BigDecimal("0");
        
        if (map12Gokei.containsKey(key)) {
            bigSum = (BigDecimal) map12Gokei.get(key);
            bigUriagedaka= (BigDecimal) map12Gokei.get( UIPLDataInfo.uriagedaka_COLUMN );
        }
        
        // 構成比のとき
        if ("04".equals(dto.getCsvMode())) {
            listData.add( Calculator.percentage( bigSum,bigUriagedaka,2) );
            BigDecimal avgData = Calculator.divide(bigSum, new BigDecimal(String.valueOf(cntAvg)));
            BigDecimal avgUriagedakaData = Calculator.divide(bigUriagedaka, new BigDecimal(String.valueOf(cntAvg)));
            listData.add( Calculator.percentage( avgData, avgUriagedakaData,2 ) );
        // 金額のとき
        } else {          
            listData.add(bigSum);
            listData.add(Calculator.divide(bigSum, new BigDecimal(String.valueOf(cntAvg))));
        }
    }
// end
    /**
     * 12ヶ月合計
     *
     */
    private void calc12Gokei(String key, BigDecimal bigKingaku, Map map12Gokei) {
        BigDecimal bigVal = new BigDecimal("0");
        if (map12Gokei.containsKey(key)) {
        	bigVal = (BigDecimal) map12Gokei.get(key);
        }
        bigVal = bigVal.add(bigKingaku);
        
        map12Gokei.put(key, bigVal);
    }
    
    public UIPLRCDataInfoDao getUiPlRcDataInfoDao() {
        return uiPlRcDataInfoDao;
    }

    public void setUiPlRcDataInfoDao(UIPLRCDataInfoDao uiPlRcDataInfoDao) {
        this.uiPlRcDataInfoDao = uiPlRcDataInfoDao;
    }
	public GetPLInfoLogic getGetPlInfoLogic() {
		return getPlInfoLogic;
	}
	public void setGetPlInfoLogic(GetPLInfoLogic getPlInfoLogic) {
		this.getPlInfoLogic = getPlInfoLogic;
	}
}
