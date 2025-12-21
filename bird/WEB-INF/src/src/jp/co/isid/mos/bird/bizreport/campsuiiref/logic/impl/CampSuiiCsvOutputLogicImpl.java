/*
 * 作成日: 2008/04/25
 *
 */
package jp.co.isid.mos.bird.bizreport.campsuiiref.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.campsuiiref.entity.UISuii;
import jp.co.isid.mos.bird.bizreport.campsuiiref.logic.SearchLogic;
import jp.co.isid.mos.bird.bizreport.common.camp.code.MenuTotaledKbn;
import jp.co.isid.mos.bird.bizreport.common.camp.code.TaishoJoken;
import jp.co.isid.mos.bird.bizreport.common.camp.code.ZennenDataShubetu;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.RequestSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.dto.SessionSuiiDto;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.CodCampMenu;
import jp.co.isid.mos.bird.bizreport.common.camp.entity.UILists;
import jp.co.isid.mos.bird.bizreport.common.camp.util.CommonUtil;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * CSVダウンロードロジック
 * 
 * @author xnkusama
 */
public class CampSuiiCsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR012L05";

    /*【ロジック】データ検索ロジック*/
    private SearchLogic campsuiirefSearchLogic;
    // Formatter
    private DateFormatter dateFormatterYMD = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_SLASH);
    private NumericFormatter numericFormatterDigit0  = new NumericFormatter(true, 0, true);
    private NumericFormatter numericFormatterDigit2  = new NumericFormatter(true, 2);
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return "CAMPSUII.csv";
	}

	/**
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        List listCSV = new ArrayList();
        RequestSuiiDto dto = (RequestSuiiDto) csvOutputDto;
        
        ((SessionSuiiDto)(dto.getSelfSessionDto())).getSearchedDataDto(dto.getWindowId(), dto);

        // 検索結果データが存在しない場合は、再検索を行う
        List listSearchedData = dto.getListSearchData();
        if (listSearchedData == null || listSearchedData.isEmpty()) {
            listSearchedData = getCampsuiirefSearchLogic().execute((SessionSuiiDto) dto.getSelfSessionDto(), dto);
            dto.setListSearchData(listSearchedData);
        }

        listCSV.addAll(makeHeader(csvOutputDto));
        listCSV.add(makeRowHeader(csvOutputDto));
        listCSV.addAll(makeMainData(csvOutputDto));
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
    private List makeHeader(CsvOutputDto csvOutputDto) {
        RequestSuiiDto dto = (RequestSuiiDto) csvOutputDto;
        List listHeader = new ArrayList();
        
        
        // １行目
        List listHeader1 = new ArrayList();
        listHeader1.add("キャンペーン：");
        listHeader1.add(dto.getCampTitle());
        listHeader1.add("");
        listHeader1.add("対象期間：");
        // 対象期間Toの判断
        String campTo = "";
        if (dto.getMstCampDate().getCampTo().compareTo(dto.getSelfSessionDto().getBirdDateInfo().getAppDate()) > 0) {
            campTo = dto.getSelfSessionDto().getBirdDateInfo().getAppDate();
        }
        else {
            campTo = dto.getMstCampDate().getCampTo();
        }
        String campKikan = dateFormatterYMD.format(dto.getMstCampDate().getCampFrom(), true)
                         + "～"
                         + dateFormatterYMD.format(campTo, true);
        listHeader1.add(campKikan);
        listHeader1.add("");
        listHeader1.add("前年データ種別：");
        listHeader1.add(dto.getZennenDataShubetuName());
        
        // ２行目
        List listHeader2 = new ArrayList();
        listHeader2.add("表示対象：");
        String taishoJoken = dto.getHyojiTaishoName();
//        if (TaishoJoken.CODE_ALL.equals(dto.getTaishoJoken()) 
//                && !UserType.HONBU.equals(dto.getSelfSessionDto().getUserTypeCd())) {
//            taishoJoken = "全店";
//        }
//        else {
//            taishoJoken = dto.getTaishoJokenName() + "　" + dto.getHyojiTaishoName();
//        }
        if (TaishoJoken.CODE_SIBU.equals(dto.getTaishoJoken())) {
            taishoJoken += "　" + dto.getBlockName();
        }
        listHeader2.add(taishoJoken);
        listHeader2.add("");
        listHeader2.add("メニュー：");
        listHeader2.add(dto.getMenuTotaledKbnName() + "　（" + getMenuInfo(csvOutputDto) + "）");
        
        // ３行目
        List listHeader3 = new ArrayList();
        listHeader3.add("対象店舗数：");
        listHeader3.add(numericFormatterDigit0.format(dto.getTaishoTenpoCnt()));
        if (UserType.HONBU.equals(dto.getSelfSessionDto().getUserTypeCd())) {
            listHeader3.add("");
            listHeader3.add("店舗種別：");
            listHeader3.add(dto.getTenpoShubetuName());
        }
        
        // ４行目（ブランク行）
        List listHeader4 = new ArrayList();
        listHeader4.add("");
        
        listHeader.add(listHeader1);
        listHeader.add(listHeader2);
        listHeader.add(listHeader3);
        listHeader.add(listHeader4);
        return listHeader;
    }
    
    private List makeRowHeader(CsvOutputDto csvOutputDto) {
        RequestSuiiDto dto = (RequestSuiiDto) csvOutputDto;
        List listRowHeader = new ArrayList();
        
        listRowHeader.add("営業日");
        if (TaishoJoken.CODE_MISE.equals(dto.getTaishoJoken())) {
            listRowHeader.add("天候");
        }
        listRowHeader.add("販売個数");
        listRowHeader.add("販売金額");
        listRowHeader.add("金額構成比");
        listRowHeader.add("売上");
        if (ZennenDataShubetu.CODE_HOSEI.equals(dto.getZennenDataShubetu())) {
            listRowHeader.add("前年比対象売上");
        }
        listRowHeader.add("前年売上");
        if (TaishoJoken.CODE_MISE.equals(dto.getTaishoJoken())) {
            listRowHeader.add("前年天候");
        }
        listRowHeader.add("前年比（売上）");
        listRowHeader.add("客数");
        listRowHeader.add("前年客数");
        listRowHeader.add("前年比（客数）");
        listRowHeader.add("客単価");
        listRowHeader.add("前年客単価");
        listRowHeader.add("前年比（客単価）");
        return listRowHeader;
    }
    /**
     * CSVデータ部作成
     * @param csvOutputDto
     * @return
     */
    private List makeMainData(CsvOutputDto csvOutputDto) {
        RequestSuiiDto dto = (RequestSuiiDto) csvOutputDto;
        List listSearchedData = dto.getListSearchData();
        List listCsv = new ArrayList();
        for (Iterator ite = listSearchedData.iterator(); ite.hasNext();) {
            UISuii entity = (UISuii) ite.next();
            List listRow = new ArrayList();
            if (CommonUtil.isNull(entity.getEigyoDt())) {
                listRow.add("総合計");
            }
            else {
                listRow.add(dateFormatterYMD.format(entity.getEigyoDt(), true));
            }
            if (TaishoJoken.CODE_MISE.equals(dto.getTaishoJoken())) {
                if (!CommonUtil.isNull(entity.getEigyoDt())) {
                    listRow.add(entity.getTenkoKbnKigou());
                }
                else {
                    listRow.add("");
                }
            }
            listRow.add(numericFormatterDigit0.format(entity.getKazuKei(), true));
            listRow.add(numericFormatterDigit0.format(entity.getMenuUriage(), true));
            listRow.add(numericFormatterDigit2.format(entity.getKingakuKoseiHi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getUriage(), true));
            if (ZennenDataShubetu.CODE_HOSEI.equals(dto.getZennenDataShubetu())) {
                listRow.add(numericFormatterDigit0.format(entity.getNetUriage(), true));
                listRow.add(numericFormatterDigit0.format(entity.getNetUriageZen(), true));
            }
            else {
                listRow.add(numericFormatterDigit0.format(entity.getUriageZen(), true));
            }
            if (TaishoJoken.CODE_MISE.equals(dto.getTaishoJoken())) {
                if (!CommonUtil.isNull(entity.getEigyoDt())) {
                    listRow.add(entity.getTenkoKbnZenKigou());
                }
                else {
                    listRow.add("");
                }
            }
            if (ZennenDataShubetu.CODE_HOSEI.equals(dto.getZennenDataShubetu())) {
                listRow.add(numericFormatterDigit2.format(entity.getNetUriageZennenHi(), true));
            }
            else {
                listRow.add(numericFormatterDigit2.format(entity.getUriageZennenHi(), true));
            }
            listRow.add(numericFormatterDigit0.format(entity.getKyakusu(), true));
            if (ZennenDataShubetu.CODE_HOSEI.equals(dto.getZennenDataShubetu())) {
                listRow.add(numericFormatterDigit0.format(entity.getNetKyakusuZen(), true));
                listRow.add(numericFormatterDigit2.format(entity.getNetKyakusuZennenHi(), true));
            }
            else {
                listRow.add(numericFormatterDigit0.format(entity.getKyakusuZen(), true));
                listRow.add(numericFormatterDigit2.format(entity.getKyakusuZennenHi(), true));
            }
            listRow.add(numericFormatterDigit0.format(entity.getKyakutanka(), true));
            if (ZennenDataShubetu.CODE_HOSEI.equals(dto.getZennenDataShubetu())) {
                listRow.add(numericFormatterDigit0.format(entity.getNetKyakutankaZen(),true));
                listRow.add(numericFormatterDigit2.format(entity.getNetKyakutankaZennenHi(), true));
            }
            else {
                listRow.add(numericFormatterDigit0.format(entity.getKyakutankaZen(),true));
                listRow.add(numericFormatterDigit2.format(entity.getKyakutankaZennenHi(), true));
            }
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
            throw new NotNullException("キャンペーン売上推移 画面DTO");
        }

	}
    public SearchLogic getCampsuiirefSearchLogic() {
        return campsuiirefSearchLogic;
    }

    public void setCampsuiirefSearchLogic(SearchLogic campsuiirefSearchLogic) {
        this.campsuiirefSearchLogic = campsuiirefSearchLogic;
    }
    
    /**
     * メニュー一覧文字列作成処理
     * @param csvOutputDto
     * @return
     */
    private String getMenuInfo(CsvOutputDto csvOutputDto) {
        RequestSuiiDto dto = (RequestSuiiDto) csvOutputDto;
        String menuInfo = "";
        if (MenuTotaledKbn.CODE_TOTAL.equals(dto.getMenuTotaledKbn())) {
            StringBuffer sb = new StringBuffer();
            for (Iterator ite = dto.getListsMenu().iterator(); ite.hasNext();) {
                UILists uiLists = (UILists) ite.next();
                if (uiLists.getKey().equals(dto.getCampId())) {
                    for (Iterator ite2 = uiLists.getListData().iterator(); ite2.hasNext();) {
                        UILists uiListsTotaledKbn = (UILists) ite2.next();
                        if (MenuTotaledKbn.CODE_TANPIN.equals(uiListsTotaledKbn.getKey())) {
                            for (Iterator ite3 = uiListsTotaledKbn.getListData().iterator(); ite3.hasNext();) {
                                CodCampMenu codCampMenu = (CodCampMenu) ite3.next();
                                if (sb.length() != 0) {
                                    sb.append("、");
                                }
                                sb.append(codCampMenu.getName());
                            }
                        }
                    }
                    break;
                }
            }
            menuInfo = sb.toString();
        }
        else {
            menuInfo = dto.getMenuNameKj();
        }
        return menuInfo;
    }
}
