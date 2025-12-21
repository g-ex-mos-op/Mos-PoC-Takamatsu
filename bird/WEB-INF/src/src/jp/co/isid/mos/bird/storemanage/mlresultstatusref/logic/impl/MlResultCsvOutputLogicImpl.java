/*
 * 作成日: 2006/04/19
 */
package jp.co.isid.mos.bird.storemanage.mlresultstatusref.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.EntryYearInfoDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.dto.MlResultStatusRefDto;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.entity.UIMLResultStatus;
import jp.co.isid.mos.bird.storemanage.mlresultstatusref.util.PrivateUtil;

/**
 * @author Noh
 * 更新日:2009/09/03 xkinu 一括ダウンロードCSV項目へ"スタッフID"の追加対応
 */
public class MlResultCsvOutputLogicImpl implements CsvOutputLogic {

	/* ロジックID */
    public static final String LOGIC_ID = "BSM007L03";

	/**
	 * CSVファイル名取得処理
	 *
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		MlResultStatusRefDto dto = (MlResultStatusRefDto) csvOutputDto;
		if(PrivateUtil.DL_TYPE_NENDOKAI == dto.getEntryFlg()){
			return "MLKEKKA" +dto.getEntryYear()+"NENDO"+dto.getLatestEntryKai()+"KAI.csv";
		}else if(PrivateUtil.DL_TYPE_NENDO == dto.getEntryFlg()){
			return "MLKEKKA" +dto.getEntryYear()+"NENDO.csv";
		}else if(PrivateUtil.DL_TYPE_IKKATU == dto.getEntryFlg()){
			return "MLKEKKA.csv";
		}else{
			return "MLKEKKADEFAULT.csv";
		}
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 * 更新日:2009/09/03 xkinu 一括ダウンロードCSV項目へ"スタッフID"の追加対応
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
		/* DTO */
		MlResultStatusRefDto dto = (MlResultStatusRefDto) csvOutputDto;
		/* CSV出力用List */
        List listCSV = new ArrayList();

        List listEntity = dto.getResultList();
        UIMLResultStatus subunit = (UIMLResultStatus)listEntity.get(0);

        /* ヘッダ */
        List listHeader = dto.getKikanList();
        int gokaku = 0;
        int hugokaku = 0;

        for(int i=0 ; i < listHeader.size() ; i++){
        	EntryYearInfoDto headerUnit = (EntryYearInfoDto)listHeader.get(i);
        	gokaku +=(Integer.valueOf(headerUnit.getGokaku())).intValue();
        	hugokaku +=(Integer.valueOf(headerUnit.getHugokaku())).intValue();
        }


        List listHeaderRow1 = new ArrayList();
        List listHeaderRow2 = new ArrayList();
        List listHeaderRow3 = new ArrayList();

        /* 会社 */
   	 	listHeaderRow1.add("会社：");
       	listHeaderRow1.add(dto.getCompanyName());
        listCSV.add(listHeaderRow1);


        /* 対象条件 */
        if(PrivateUtil.DL_TYPE_IKKATU == dto.getEntryFlg()
        		|| dto.getSelectFlg() == 3){
        	listHeaderRow2.add("対象条件：");
        	listHeaderRow2.add("全て");
        }
        else if(dto.getSelectFlg()==0){
        	listHeaderRow2.add("対象支部：");
        	listHeaderRow2.add(subunit.getSibuName());
        }else if(dto.getSelectFlg() == 1){
        	listHeaderRow2.add("対象オーナー：");
        	listHeaderRow2.add(subunit.getOnerNameKj());
        }else if(dto.getSelectFlg() == 2){
        	listHeaderRow2.add("対象店舗：");
        	listHeaderRow2.add(subunit.getMiseNameKj());
        }
        listCSV.add(listHeaderRow2);

        /* 実地時期 */
        if(PrivateUtil.DL_TYPE_NENDOKAI == dto.getEntryFlg()){
        	listHeaderRow3.add("実地時期：");
        	listHeaderRow3.add(dto.getEntryYear() + "年 " +Integer.valueOf(dto.getLatestEntryKai()).toString()+ "回");
        }else if(PrivateUtil.DL_TYPE_NENDO == dto.getEntryFlg() ){
        	listHeaderRow3.add("年度指定：");
        	listHeaderRow3.add(dto.getEntryYear()+ "年");
        }else if(PrivateUtil.DL_TYPE_IKKATU == dto.getEntryFlg()){
        	listHeaderRow3.add("時期：");
        	listHeaderRow3.add(dto.getBandleYear3() + "年 ～ " + dto.getBandleYear1() + "年");
        }
        listCSV.add(listHeaderRow3);

        /* 空白行 */
        listCSV.add(new ArrayList());

        /* 項目 */
        List listitems = new ArrayList();
        listitems.add("オーナーコード");
        listitems.add("オーナー名称");
        listitems.add("支部取込コード");
        listitems.add("支部取込名称");
        listitems.add("店コード");
        listitems.add("店名称");
        listitems.add("受験年度");
        listitems.add("受験回");
        listitems.add("合格者数");
        listitems.add("不合格者数");
        listitems.add("保留者数");
        listitems.add("無効者数");
        listitems.add("受験番号");
        listitems.add("受験者氏名");
        listitems.add("総合結果");
        listitems.add("能力チェック");
        listitems.add("筆記テスト");
        listitems.add("面接");
        listitems.add("研修修了");
        if(UserType.HONBU.equals(dto.getUserTypeCd())
        		&& PrivateUtil.DL_TYPE_IKKATU == dto.getEntryFlg())
        {
        	//本部ユーザで一括ダウンロードの場合はスタッフIDを表示します。
        	listitems.add("スタッフID");
        }
        listCSV.add(listitems);

        /* 本文データ */
        for(int j=0 ; j<dto.getResultList().size() ; j++){
        	List listUnit = new ArrayList();
        	UIMLResultStatus unit = (UIMLResultStatus) listEntity.get(j);
            listUnit.add(unit.getOnerCd());//オーナーコード
        	listUnit.add(unit.getOnerNameKj());//オーナー名称
            listUnit.add(unit.getSibuCd());//支部取込コード
            listUnit.add(unit.getSibuName());//支部取込名称
            listUnit.add(unit.getMiseCd());//店コード
        	listUnit.add(unit.getMiseNameKj());//店名称
        	listUnit.add(unit.getTotalLastYear());//受験年度
        	listUnit.add(unit.getTotalLastKai());//受験回


        	/* 合格者数,不合格者数設定 */
        	boolean flg = false;
        	for(int k=0 ; k<dto.getKikanList().size() ; k++){
        		EntryYearInfoDto sogo=(EntryYearInfoDto)dto.getKikanList().get(k);
        		if(unit.getTotalLastYear().equals(sogo.getEntryYear())){
        			if(unit.getTotalLastKai().equals(sogo.getEntryKai())){
        				listUnit.add(sogo.getGokaku());
        				listUnit.add(sogo.getHugokaku());
                        listUnit.add(sogo.getHoryu());
                        listUnit.add(sogo.getMukou());
        				flg = true;
        			}
        		}
        	}
        	if(!flg){
				listUnit.add("");
				listUnit.add("");
                listUnit.add("");
                listUnit.add("");
        	}

        	listUnit.add(unit.getExamNo());//受験番号
        	listUnit.add(unit.getStaffName());//受験者氏名
        	listUnit.add((unit.getTotalResult().equals("1")?"研修未了":unit.getTotalResult()));//総合結果
        	listUnit.add(unit.getSub1Result());//能力チェック
        	listUnit.add(unit.getSub2Result());//筆記テスト
        	listUnit.add(unit.getSub3Result());//面接
        	listUnit.add((unit.getCourseStatus().equals("0")?"未修了":unit.getCourseStatus()));//研修修了
            if(UserType.HONBU.equals(dto.getUserTypeCd())
            		&& PrivateUtil.DL_TYPE_IKKATU == dto.getEntryFlg())
            {
            	//本部ユーザで一括ダウンロードの場合はスタッフIDを表示します。
            	listUnit.add(unit.getStaffId());//スタッフID
            }

        	listCSV.add(listUnit);
        }

		return listCSV;
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#validate(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public void validate(CsvOutputDto csvOutputDto) {
		MlResultStatusRefDto dto = (MlResultStatusRefDto) csvOutputDto;

        if (dto.getResultList() == null ||
        		dto.getResultList().size() == 0)
        {
            throw new NotExistException("出力データ");
        }

	}


}
