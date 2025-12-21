/*
 * 作成日: 2007/07/09
 */
package jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.codbalancelist.common.CodBalanceListUtil;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.dto.RequestDto;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.entity.UICodBalanceList;
import jp.co.isid.mos.bird.bizsupport.codbalancelist.logic.ConditionLogic;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * COD残高一覧 CSVロジック
 * 
 * @author xkinu
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    
    /** ロジックID */
    public static final String LOGIC_ID = CodBalanceListUtil.LOGIC_ID_CSVOUTPUT;
        
	/** LOGIC【初期情報の取得】*/
    private ConditionLogic codBalanceListConditionLogic;
    
    /**
     * CSVアウトプットデータ取得処理
     * 
     * (非 Javadoc)
     * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        //１．ロジック【初期情報の取得】を実行し、戻り値Mapを取得。
        Map rparam = getCodBalanceListConditionLogic().execute(null);
        //ダウンロード許可フラグ取得
        boolean downloadFlg = ((Boolean)rparam.get(ConditionLogic.RK_DOWNLOAD_FLG)).booleanValue();
        ((RequestDto)csvOutputDto).setDownloadFlg(downloadFlg);
        
    	try {
	        //２．処理１の戻り値Map.ダウンロード許可フラグ＝＝false　の場合
	        if(!downloadFlg) {
	        	//NotExistExceptionを生成し、throwする。
	        	throw new NotExistException("CSVデータ");
	        }
	        //３．処理１の戻り値Map.[[COD残高一覧]]を変数[[CSVデータ]]へ代入する。
	        List listDatas = (List)rparam.get(ConditionLogic.RK_LIST_GETDATA);
	
	        //４．[[CSV出力用List]]を生成する。
	        List listCSV = new ArrayList();
	        
	        //５．List[CSVヘッダー部]を作成し、処理４の[[CSV出力用List]]へ追加する。
	        createCsvHeader(listCSV, listDatas);
	        //６．List[CSVデータヘッダー部]を作成し、処理４の[[CSV出力用List]]へ追加する。
	        createCsvDataHeader(listCSV);
	        
	        //７．変数[[CSVデータ]]の件数分のEntity毎にList[1行分データ]を生成し、
	        //    このListを処理４の[[CSV出力用List]]へ追加する。
	        for (int i=0; i < listDatas.size(); i++) {
	            //７－１．指定インデックスの変数[[CSVデータ]].[CSVデータ]を取得する。
	        	UICodBalanceList entity = (UICodBalanceList)listDatas.get(i);
	            //７－２．List[1行分データ]作成
	            List listData = new ArrayList();
	            //７－３．下記の値をList[1行分データ]へ追加する。
	            //売掛先コード
	            listData.add(entity.getUrikakeCd().trim());
	            //売掛先名称
	            listData.add(entity.getOnerName().trim());
	            //残高
	            listData.add(entity.getGenZan());
	            //翌日受注金額
	            listData.add(entity.getJuchuKin());
	            //差引残高
	            listData.add(entity.getHikiZan());
	            //先日付日受注金額
	            listData.add(entity.getSakiJuchuKin());
	            //７－４．List[1行分データ]を処理４の[[CSV出力用List]]へ追加する
	            listCSV.add(listData);
	            
	        }
	        //８．[[CSV出力用List]]をリターンする。
	        return listCSV;
    	}
    	catch (ApplicationException e) {
	        //２－１．処理１の戻り値Mapからメッセージ文字列を取得し、
	        //    DTO【Request用】メッセージへ設定する。
	        String errMsg = (String)rparam.get(ConditionLogic.RK_MSG);
	        ((RequestDto)csvOutputDto).setMsg(errMsg);
	        throw e;
    	}
    }
    /**
     * CSVヘッダー部生成処理
     * 
     * @param listCsv
     * @param listDatas
     */
    private void createCsvHeader(List listCsv, List listDatas) {
        
        List row1 = new ArrayList();
        List row2 = new ArrayList();
        DateFormatter dateFormatter  = new DateFormatter();
        UICodBalanceList entity = (UICodBalanceList)listDatas.get(0);
        // 一行目
        row1.add("算出日：");
        row1.add(
        		dateFormatter.format(
        				entity.getCalcDate()
        				, DateFormatter.PATTERN_NORMAL
        				, DateFormatter.PATTERN_SLASH
        				, false)
        		);
        
        listCsv.add(row1);
        listCsv.add(row2);
           
    }
    /**
     * CSVデータヘッダー部生成処理
     * 
     * @param listCsv
     */
    private void createCsvDataHeader(List listCsv) {
        List row = new ArrayList();
        row.add("売掛先コード");
        row.add("売掛先名称");
        row.add("残高");
        row.add("翌日受注金額");
        row.add("差引残高");
        row.add("先日付受注金額");
        listCsv.add(row);
    	
    }
    /**
     * ファイル名称取得処理
     * 
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        return "COD_BALANCELIST.csv";
        
    }

    public void validate(CsvOutputDto csvOutputDto) {
        // TODO 自動生成されたメソッド・スタブ
    }

	/**
	 *  LOGIC【初期情報の取得】取得処理
	 *  
	 * @return codBalanceListConditionLogic を戻します。
	 */
	public ConditionLogic getCodBalanceListConditionLogic() {
		return codBalanceListConditionLogic;
	}

	/**
	 *  LOGIC【初期情報の取得】設定処理
	 *  
	 * @param codBalanceListConditionLogic 設定する codBalanceListConditionLogic。
	 */
	public void setCodBalanceListConditionLogic(
			ConditionLogic codBalanceListConditionLogic) {
		this.codBalanceListConditionLogic = codBalanceListConditionLogic;
	}

}