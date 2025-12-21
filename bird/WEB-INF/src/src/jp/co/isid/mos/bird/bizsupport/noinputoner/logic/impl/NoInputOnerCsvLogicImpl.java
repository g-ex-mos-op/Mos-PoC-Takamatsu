/*
 * 作成日: 2006/04/05
 */
package jp.co.isid.mos.bird.bizsupport.noinputoner.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.dto.NoInputOnerDto;
import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.UITenpoInfo;
import jp.co.isid.mos.bird.bizsupport.noinputoner.logic.GetPLDataStateLogic;
import jp.co.isid.mos.bird.bizsupport.noinputoner.text.converter.InputKbnConverter;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * P/L未入力オーナー一覧 CSVロジック
 * @author xnkusama
 */
public class NoInputOnerCsvLogicImpl implements CsvOutputLogic {

    
    /* ロジックID */
    private static final String LOGIC_ID = "BBS004L05";
    
    
	/**
	 * 過去26ヶ月のPLデータの入力状況を取得ロジック
	 */
	private GetPLDataStateLogic getPLDataStateLogic;

	/**
	 * 過去26ヶ月のPLデータの入力状況を取得ロジックの設定
	 * @return getPLDataStateLogic を戻します。
	 */
	public GetPLDataStateLogic getGetPLDataStateLogic() {
		return getPLDataStateLogic;
	}
	/**
	 * 過去26ヶ月のPLデータの入力状況を取得ロジックの設定
	 * @param getPLDataStateLogic getPLDataStateLogic を設定。
	 */
	public void setGetPLDataStateLogic(
			GetPLDataStateLogic getPLDataStateLogic) {
		this.getPLDataStateLogic = getPLDataStateLogic;
	}

	/**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        NoInputOnerDto noInputOnerDto = (NoInputOnerDto) csvOutputDto;
		return "ONERLIST_" + noInputOnerDto.getCondNengetuInp() + ".csv";
//		return "ONERLIST_" + noInputOnerDto.getCondNengetu() + ".csv";
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
        // DTO
        NoInputOnerDto dto = (NoInputOnerDto) csvOutputDto;
        // CSV出力用List
        List listCSV = new ArrayList();
        // Converter, Formatter
        InputKbnConverter conv = new InputKbnConverter();
        DateFormatter formatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);

        // 検索結果List（過去26ヶ月のPLデータの入力状況を取得ロジックから取得）
        List listEntity = getGetPLDataStateLogic()
        					.execute(dto.getCondCompanyCd(), 
        							 dto.getCondNengetuInp(),
									 dto.getCondSibuCdInp(),
									 dto.getCondInputInp(),
                                     dto.isCloseMiseFlg());

        if (listEntity == null || listEntity.size() == 0) {
            throw new NotExistException("出力データ");
        }
        
        //条件項目ヘッダ
        List listHeaderRow1 = new ArrayList();
        List listHeaderRow2 = new ArrayList();
        List listHeaderRow3 = new ArrayList();
        listHeaderRow1.add("対象年月");
        listHeaderRow1.add(formatter.format(dto.getCondNengetuInp(), true));
//        listHeaderRow1.add(formatter.format(dto.getCondNengetu(), true));
        if (dto.isCloseMiseFlg()){
            listHeaderRow1.add("(クローズ店を含む)");
        }
        else {
            listHeaderRow1.add("(クローズ店を含まない)");
        }
        
        listHeaderRow2.add("状況");
        if (dto.getCondInputInp() != null && !"".equals(dto.getCondInputInp())) {
        	listHeaderRow2.add(dto.getCondInputName());
        }
        else {
            listHeaderRow2.add("全状況");
        }
        listHeaderRow3.add("支部");
        if (dto.getCondSibuCdInp() != null) {
//        if (dto.getCondSibuCd() != null) {
        	listHeaderRow3.add(dto.getCondSibuCdInp() + " " + dto.getCondSibuName());
//        	listHeaderRow3.add(dto.getCondSibuCd() + " " + dto.getCondSibuName());
        }
        else {
        	listHeaderRow3.add("全支部");
        }
        listCSV.add(listHeaderRow1);
        listCSV.add(listHeaderRow2);
        listCSV.add(listHeaderRow3);
        //空白行
        listCSV.add(new ArrayList());
        //明細部項目ヘッダ
        listCSV.add(((NoInputOnerDto) csvOutputDto).getListCsvTableHeader());
        //明細データ
        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
            List listRow = new ArrayList();
            UITenpoInfo entity = (UITenpoInfo) ite.next();
            listRow.add(entity.getSibuCd());
            listRow.add(entity.getSibuName());
            listRow.add(entity.getOnerCd());
            listRow.add(entity.getOnerNameKj());
            listRow.add(entity.getMiseCd());
            listRow.add(entity.getMiseNameKj());
            listRow.add(entity.getCloseDt());
            listRow.add(conv.convert(entity.getPlDt1()));
            listRow.add(conv.convert(entity.getPlDt2()));
            listRow.add(conv.convert(entity.getPlDt3()));
            listRow.add(conv.convert(entity.getPlDt4()));
            listRow.add(conv.convert(entity.getPlDt5()));
            listRow.add(conv.convert(entity.getPlDt6()));
            listRow.add(conv.convert(entity.getPlDt7()));
            listRow.add(conv.convert(entity.getPlDt8()));
            listRow.add(conv.convert(entity.getPlDt9()));
            listRow.add(conv.convert(entity.getPlDt10()));
            listRow.add(conv.convert(entity.getPlDt11()));
            listRow.add(conv.convert(entity.getPlDt12()));
            listRow.add(conv.convert(entity.getPlDt13()));

            
            listCSV.add(listRow);
        }
		return listCSV;
	}

    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
		NoInputOnerDto noInputOnerDto = (NoInputOnerDto) csvOutputDto;

        NoInputOnerDto dto = (NoInputOnerDto) csvOutputDto;
//        List listEntity = getPLDataStateLogic.execute(dto.getCondCompanyCd(), dto
//				.getCondNengetuInp(), dto.getCondSibuCdInp(), dto.getCondInputInp());
//        if (listEntity == null || listEntity.size() == 0) {
//			throw new NotExistException("出力データ");
//		}
//        if (noInputOnerDto.getListAllData() == null ||
//                noInputOnerDto.getListAllData().size() == 0)
//        {
//            throw new NotExistException("出力データ");
//        }
	}
}
