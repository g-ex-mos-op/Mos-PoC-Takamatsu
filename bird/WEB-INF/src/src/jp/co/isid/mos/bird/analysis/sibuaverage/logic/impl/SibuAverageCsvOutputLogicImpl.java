/*
 * 作成日: 2008/10/14
 *
 */
package jp.co.isid.mos.bird.analysis.sibuaverage.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageReqDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.entity.UISuiiData;
import jp.co.isid.mos.bird.bizreport.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.util.CommonUtil;
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
public class SibuAverageCsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT006L03";

    // Formatter
    private NumericFormatter numericFormatterDigit0  = new NumericFormatter(true, 0);
    private NumericFormatter numericFormatterDigit2  = new NumericFormatter(true, 2);
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        SibuAverageReqDto dto = (SibuAverageReqDto) csvOutputDto;
        return "SIBUHIKAKU" + dto.getKikanSitei() + ".csv";
	}

	/**
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        List listCSV = new ArrayList();
        SibuAverageReqDto viewDto = (SibuAverageReqDto) csvOutputDto;
        
        //ヘッダ（1-4行目）
        listCSV.addAll(makeHeader(viewDto));
        //行ヘッダ
        makeRowHeader(viewDto, listCSV);
        //データ部
        listCSV.addAll(makeMainData(viewDto));
        //注意喚起メッセージ
        List listMsg = new ArrayList();
        listMsg.add(viewDto.getUserMsg());
        listCSV.add(listMsg);
        
		return listCSV;
	}
    
    /**
     * CSVヘッダー部作成
     * １行目：対象条件
     * ２行目：対象期間
     * ３行目：表示対象
     * @param csvOutputDto
     * @return
     */
    private List makeHeader(SibuAverageReqDto dto) {
        List listHeader = new ArrayList();
        
        
        // １行目
        List listHeader1 = new ArrayList();
        listHeader1.add("表示対象：");
        listHeader1.add(rtrim(dto.getHyojiTaishoDisp()));
        listHeader1.add("");
        listHeader1.add("比較支部：");
        listHeader1.add(rtrim(dto.getTaishoSibuDisp()));
        
        // ２行目
        List listHeader2 = new ArrayList();
        listHeader2.add((UserType.isHonbu(dto.getUserTypeCd())?"集計区分":"支部")+"：");
        listHeader2.add(dto.getShukeiKbnName());
        
        // ３行目
        List listHeader3 = new ArrayList();
        listHeader3.add("対象期間：");
        listHeader3.add(dto.getTaishoKikanDisp());
        
        // ４行目
        List listHeader4 = new ArrayList();
        listHeader4.add("前年データ種別：");
        listHeader4.add(dto.getZenDataShuName());        
        
        listHeader.add(listHeader1);
        listHeader.add(listHeader2);
        listHeader.add(listHeader3);
        listHeader.add(listHeader4);
        // ５行目(空白行)
        List listHeader5 = new ArrayList();
        listHeader5.add("");    
        listHeader.add(listHeader5);

        return listHeader;
    }
    
    /**
     * 行ヘッダー作成
     * @return
     */
    private void makeRowHeader(SibuAverageReqDto dto, List listCsv) {
        List listRowHeader1 = new ArrayList();
        List listRowHeader2 = new ArrayList();
        
        listRowHeader1.add("");
        if (TaishoJoken.CODE_MISE.equals(dto.getTaishoJoken())) {
            listRowHeader1.add(rtrim(dto.getHyojiTaishoDisp()));
        }
        else {
            listRowHeader1.add("全店平均");
        }
        listRowHeader1.add("");
        listRowHeader1.add("前年比");
        listRowHeader1.add("");
        listRowHeader1.add("");
        listRowHeader1.add("支部平均");
        listRowHeader1.add("");
        listRowHeader1.add("前年比");
        listRowHeader1.add("");
        listRowHeader1.add("");
        
        listRowHeader2.add("営業日");
        listRowHeader2.add("売上");
        listRowHeader2.add("前年実績");
        listRowHeader2.add("売上");
        listRowHeader2.add("客数");
        listRowHeader2.add("客単価");
        listRowHeader2.add("売上");
        listRowHeader2.add("前年実績");
        listRowHeader2.add("売上");
        listRowHeader2.add("客数");
        listRowHeader2.add("客単価");
        
        listCsv.add(listRowHeader1);
        listCsv.add(listRowHeader2);
    }
    /**
     * CSVデータ部作成
     * @param csvOutputDto
     * @return
     */
    private List makeMainData(SibuAverageReqDto dto) {
        List listCsv = new ArrayList();
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YMD, "dd'日（'E'）'");
        
        for (int i = 0; i< dto.getListData().size(); i++) {
            UISuiiData entity = (UISuiiData) dto.getListData().get(i);
            List listRow = new ArrayList();
            listRow.add(dateFormatter.format(entity.getEigyoDt(), true));
            listRow.add(numericFormatterDigit0.format(entity.getUriage(), true));
            listRow.add(numericFormatterDigit0.format(entity.getUriageZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getUriageZenhi(), true));
            listRow.add(numericFormatterDigit2.format(entity.getKyakusuZenhi(), true));
            listRow.add(numericFormatterDigit2.format(entity.getKyakutankaZenHi(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSibuUriage(), true));
            listRow.add(numericFormatterDigit0.format(entity.getSibuUriageZen(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSibuUriageZenhi(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSibuKyakusuZenhi(), true));
            listRow.add(numericFormatterDigit2.format(entity.getSibuKyakutankaZenHi(), true));

            listCsv.add(listRow);
        }
        return listCsv;
    }
    /**
     * 全半角スペーストリム処理
     * 
     * nullの場合は空("")を戻します。
     * 作成日:2012/11/26 ASAPC xkinu
     * @return String トリムされた値を戻します。
     */
    private String rtrim(String value) {
    	if(value == null) {
        	return "";
    	}
		return CommonUtil.rAllSpaceTrim(value);
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
