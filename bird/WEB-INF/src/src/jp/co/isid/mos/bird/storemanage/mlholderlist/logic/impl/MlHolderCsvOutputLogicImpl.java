/*
 * 作成日: 2006/04/26
 */
package jp.co.isid.mos.bird.storemanage.mlholderlist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.storemanage.mlholderlist.dao.UIMLHolderDao;
import jp.co.isid.mos.bird.storemanage.mlholderlist.dto.MlHolderListDto;
import jp.co.isid.mos.bird.storemanage.mlholderlist.entity.UIMLHolder;

/**
 * @author lee
 * 更新日:2009/09/03 xkinu 一括ダウンロードCSV項目へ"スタッフID"の追加対応
 */
public class MlHolderCsvOutputLogicImpl implements CsvOutputLogic {
	
	/* ロジックID */
    public static final String LOGIC_ID = "BSM005L02";
    /** DAO【ライセンス保持者】*/
    private UIMLHolderDao uIMLHolderDao;

	/**
	 * CSVファイル名取得処理
	 * 
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getFileName(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public String getFileName(CsvOutputDto csvOutputDto) {
		/* DTO */
		MlHolderListDto dto = (MlHolderListDto) csvOutputDto;
        if(dto.isAllDownload()) {
        	//一括ダウンロードの場合
        	return "MLLICENSE_ALL.csv";
        }
		return "MLLICENSE.csv";
	}

	/* (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 * 更新日:2009/09/03 xkinu 一括ダウンロードCSV項目へ"スタッフID"の追加対応
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {
		/* DTO */
		MlHolderListDto dto = (MlHolderListDto) csvOutputDto;
		/* CSV出力用List */
        List listCSV = new ArrayList();
    	List listEntity = dto.getResultList();
    	if(dto.isAllDownload()) {
    		listEntity = uIMLHolderDao.selectMLHoder(dto);
    	}
        
        if(listEntity == null || listEntity.size() == 0){
            throw new MissingDataException("検索結果");
        }
        
        List listHeaderRow1 = new ArrayList();
        List listHeaderRow2 = new ArrayList();
        
        /* 会社 */
        listHeaderRow1.add("会社：");
        if(dto.getCompanyCd().equals("00")){
            listHeaderRow1.add("モスフードサービス");
        }
        listCSV.add(listHeaderRow1);        
        
        /* 対象条件 */
        if(dto.isAllDownload()) {
        	//一括ダウンロードの場合
            listHeaderRow2.add("対象：");
            listHeaderRow2.add("全店");
        }else if(dto.getTmpSelectFlg()==0){
            listHeaderRow2.add("対象支部：");
            listHeaderRow2.add(dto.getTmpSibuNm());
        }else if(dto.getTmpSelectFlg() == 1){
            listHeaderRow2.add("対象オーナー：");
            listHeaderRow2.add(dto.getTmpOnerNm());
        }else if(dto.getTmpSelectFlg() == 2){
            listHeaderRow2.add("対象店舗：");
            listHeaderRow2.add(dto.getTmpMiseCd());
            listHeaderRow2.add(dto.getTmpMiseNm());
        }
        listCSV.add(listHeaderRow2);
        

        /* 空白行 */
        listCSV.add(new ArrayList());
        listCSV.add(new ArrayList());
        
        /* 項目 */
        List listitems = new ArrayList();
//　add start inazawa 20070109 マスタライセンスフェーズ4対応
        listitems.add("オーナーコード");
        listitems.add("オーナー名称");
        listitems.add("支部取込コード");
        listitems.add("支部取込名称");
        listitems.add("店コード");
// add end  
        listitems.add("店名称");
        listitems.add("氏名");
        listitems.add("種別");
        listitems.add("ライセンス番号");
        listitems.add("取得年月");
        listitems.add("更新研修");
        
// add start xkhata 20061120 活動状況追加対応
        listitems.add("活動状況");
// add end
        if(dto.isAllDownload()) {
	    	//一括ダウンロードの場合はスタッフIDを表示します。
	    	listitems.add("スタッフID");
        }
        listCSV.add(listitems);
        

        for(int j=0 ; j<listEntity.size() ; j++){
        	List listUnit = new ArrayList();

        	UIMLHolder unit = (UIMLHolder) listEntity.get(j);
// add start inazawa 20070109 マスタライセンスフェーズ4対応
            listUnit.add(unit.getOnerCd());//オーナーコード
            listUnit.add(unit.getOnerNameKj());//オーナー名称
            listUnit.add(unit.getSibuCd());//支部取込名称
            listUnit.add(unit.getSibuNameKj());//支部取込コード
            listUnit.add(unit.getMiseCd());//店コード
// add end
            listUnit.add(unit.getMiseNameKj());//店舗名
        	listUnit.add(unit.getStaffLNameKj()+" "+unit.getStaffFNameKj());//氏名
        	listUnit.add((unit.getLicenseKbn().equals("01")?"一般":"上級"));//種別
        	listUnit.add(unit.getLicenseNo());//ライセンス番号
        	listUnit.add(unit.getLicenseDt());//取得年月
        	
    		listUnit.add(unit.getRenew1StatusMoji());//更新研修状況
            listUnit.add(unit.getSituationKbnMoji());//活動状況
            if(dto.isAllDownload()) {
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
		MlHolderListDto dto = (MlHolderListDto) csvOutputDto;
    	if(dto.isAllDownload()) {
    		return;
    	}
        if (dto.getResultList() == null || 
        		dto.getResultList().size() == 0)
        {
            throw new NotExistException("出力データ");
        }

	}

	/**
	 * DAO【ライセンス保持者】取得処理
	 *  
	 * @return uIMLHolderDao を戻します。
	 */
	public UIMLHolderDao getUIMLHolderDao() {
		return uIMLHolderDao;
	}

	/**
	 * DAO【ライセンス保持者】設定処理
	 *  
	 * @param holderDao 設定する uIMLHolderDao。
	 */
	public void setUIMLHolderDao(UIMLHolderDao holderDao) {
		uIMLHolderDao = holderDao;
	}


}
