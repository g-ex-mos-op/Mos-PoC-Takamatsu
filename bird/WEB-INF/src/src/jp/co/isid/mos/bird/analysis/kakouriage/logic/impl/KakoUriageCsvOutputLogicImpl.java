/*
 * 作成日: 2008/10/02
 *
 */
package jp.co.isid.mos.bird.analysis.kakouriage.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageReqDto;
import jp.co.isid.mos.bird.analysis.kakouriage.entity.UIKakoUriage;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.NumericFormatter;

/**
 * CSVダウンロードロジック
 * 
 * @author xnkusama
 */
public class KakoUriageCsvOutputLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT005L03";

    // Formatter
    private NumericFormatter numericFormatterDigit0  = new NumericFormatter(true, 0);
    private NumericFormatter numericFormatterDigit2  = new NumericFormatter(true, 2);
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return "KAKOURI.csv";
	}

	/**
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        List listCSV = new ArrayList();
        KakoUriageReqDto dto = (KakoUriageReqDto) csvOutputDto;
        
        //ヘッダ（1-3行目）
        listCSV.addAll(makeHeader(dto));
        //行ヘッダ
        listCSV.add(makeRowHeader(dto));
        //データ部
        listCSV.addAll(makeMainData(dto));
        
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
    private List makeHeader(KakoUriageReqDto dto) {
        List listHeader = new ArrayList();
        
        
        // １行目
        List listHeader1 = new ArrayList();
        listHeader1.add("対象条件：");
        listHeader1.add(dto.getHyojiTaishoDisp());
        
        // ２行目
        List listHeader2 = new ArrayList();
        listHeader2.add("対象期間：");
        listHeader2.add(dto.getTaishoKikanDisp());
        
        // ３行目
        List listHeader3 = new ArrayList();
        listHeader3.add("表示対象：");
        if ("1".equals(dto.getTabMode())) {
            listHeader3.add("客数");
        }
        else {
            listHeader3.add("売上");
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
    private List makeRowHeader(KakoUriageReqDto dto) {
        List listRowHeader = new ArrayList();

        for (Iterator ite = dto.getListHeader().iterator(); ite.hasNext();) {
            listRowHeader.add(ite.next());
        }
        
        return listRowHeader;
    }
    /**
     * CSVデータ部作成
     * @param csvOutputDto
     * @return
     */
    private List makeMainData(KakoUriageReqDto dto) {
        List listCsv = new ArrayList();
        
        for (int i = 0; i< dto.getListData().size(); i++) {
            UIKakoUriage entity = (UIKakoUriage) dto.getListData().get(i);
            List listRow = new ArrayList();
            listRow.add(entity.getMonth());
            if ("1".equals(dto.getTabMode())) {
                //客数
                if (i == 1) {
                    //比率行
                    listRow.add(numericFormatterDigit2.format(entity.getNendo2Kyakusu(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo3Kyakusu(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo4Kyakusu(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo5Kyakusu(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo6Kyakusu(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo7Kyakusu(), true));
                }
                else {
                    //金額行
                    listRow.add(numericFormatterDigit0.format(entity.getNendo2Kyakusu(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo3Kyakusu(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo4Kyakusu(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo5Kyakusu(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo6Kyakusu(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo7Kyakusu(), true));
                }
            }
            else {
                //売上
                if (i == 1) {
                    //比率行
                    listRow.add(numericFormatterDigit2.format(entity.getNendo2(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo3(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo4(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo5(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo6(), true));
                    listRow.add(numericFormatterDigit2.format(entity.getNendo7(), true));
                }
                else {
                    //金額行
                    listRow.add(numericFormatterDigit0.format(entity.getNendo2(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo3(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo4(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo5(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo6(), true));
                    listRow.add(numericFormatterDigit0.format(entity.getNendo7(), true));
                }
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
            throw new NotNullException("画面情報");
        }

	}
}
