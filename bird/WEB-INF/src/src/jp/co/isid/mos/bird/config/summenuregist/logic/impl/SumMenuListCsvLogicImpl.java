/*
 * 作成日: 2008/09/24
 *
 */
package jp.co.isid.mos.bird.config.summenuregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.config.summenuregist.dao.MstSumMenuDao;
import jp.co.isid.mos.bird.config.summenuregist.entity.MstSumMenu;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;

/**
 * 集約設定一覧ダウンロードロジック
 * 
 * @author xnkusama
 */
public class SumMenuListCsvLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BCF009L06";

    /*DAO*/
    private MstSumMenuDao sumMenuRegistMstSumMenuDao;
    
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return "SYUYAKU.csv";
	}

	/**
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        List listCSV = new ArrayList();
        
        //表示データ取得
        List listData = getSumMenuRegistMstSumMenuDao().selectSumMenuCsv();
        //データ確認
        if (listData == null || listData.isEmpty()) {
            throw new NoResultException();
        }
        //行ヘッダ(1行目)
        listCSV.add(makeRowHeader());
        //データ部
        listCSV.addAll(makeMainData(listData));
        
        //後処理
        listData = null;
        
		return listCSV;
	}
    
    /**
     * 行ヘッダー作成
     * @return
     */
    private List makeRowHeader() {
        List listRowHeader = new ArrayList();
        
        listRowHeader.add("集約先メニューコード");
        listRowHeader.add("集約先メニュー名");
        listRowHeader.add("子メニューコード");
        listRowHeader.add("子メニュー名");
        listRowHeader.add("換算値");
        
        return listRowHeader;
    }
    /**
     * CSVデータ部作成
     * @param csvOutputDto
     * @return
     */
    private List makeMainData(List listData) {
        List listCsv = new ArrayList();
        
        for (Iterator ite = listData.iterator(); ite.hasNext();) {
            MstSumMenu entity = (MstSumMenu) ite.next();
            List listRow = new ArrayList();
            //
            listRow.add(entity.getSumMenuCd());
            listRow.add(entity.getSumMenuName());
            listRow.add(entity.getMenuCd());
            listRow.add(entity.getMenuName());
            listRow.add(entity.getConvValue());

            listCsv.add(listRow);
        }
        return listCsv;
    }
    
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
	}

    public MstSumMenuDao getSumMenuRegistMstSumMenuDao() {
        return sumMenuRegistMstSumMenuDao;
    }

    public void setSumMenuRegistMstSumMenuDao(
            MstSumMenuDao sumMenuRegistMstSumMenuDao) {
        this.sumMenuRegistMstSumMenuDao = sumMenuRegistMstSumMenuDao;
    }
}
