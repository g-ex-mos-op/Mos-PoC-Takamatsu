/*
 * 作成日: 2006/04/13
 *
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIMLStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIRenewalStaff;

/**
 * マスタライセンス研修修了登録
 * CSVダウンロードロジック
 * 
 * @author xkinu
 */
public class CsvOutputLogicImpl implements CsvOutputLogic {
    /* ロジックID */    
    public static final String LOGIC_ID = "BSM006L03";
    /* 修了（予定）コース状況 */
    private String COURSE_STATUS_MISYURYO   = "0";  //未修了
    private String COURSE_STATUS_SYURYO     = "1";  //修了
    private String COURSE_STATUS_YOTEI      = "2";  //予定
    
    /*【ロジック】編集データ検索ロジック*/
    private EditLogicImpl editLogic;
    /**
     * ファイル名取得
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        MlCompletionregistDto dto = (MlCompletionregistDto) csvOutputDto;
		return "ML" + dto.getEntryNameR() + ".csv";
	}

	/**
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto) {

        // DTO取得
        MlCompletionregistDto dto = (MlCompletionregistDto) csvOutputDto;

        // DTO事前処理
        dto.setEntryData();

        //------------------------------------------
        // ロジック【編集画面出力データ検索】実行。
        //------------------------------------------
        Map logicMap = getEditLogic().execute(dto);

        // CSV出力用List
        List listCSV = new ArrayList();
        // Formatter
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        // 検索結果List
        List listEntity = (List)logicMap.get("listStaff");
        
        //条件項目ヘッダ作成
        List listHeaderRow1 = new ArrayList();
        List listHeaderRow2 = new ArrayList();
        List listHeaderRow3 = new ArrayList();
        listHeaderRow1.add("研修名");
        listHeaderRow1.add(dto.getEntryNameKj());
        listHeaderRow1.add("");
        listHeaderRow1.add("支部");
        if(dto.getSibuCd() == null || dto.getSibuCd().length()==0){
            listHeaderRow1.add("全支部");
        }else{
            listHeaderRow1.add(dto.getSibuCd() + " " + dto.getSibuName());
        }
        
        listHeaderRow2.add("タイトル");
        listHeaderRow2.add(dto.getEntryTitle());
        if(dto.getOptionFlg()) {
	        listHeaderRow2.add("オプション");
	        listHeaderRow2.add(dto.getOptionNote());
        }
        
        listHeaderRow3.add("会社");
        listHeaderRow3.add(dto.getCompanyName());
        
       	//条件項目ヘッダ設定
        listCSV.add(listHeaderRow1);
        listCSV.add(listHeaderRow2);
        listCSV.add(listHeaderRow3);

        //空白行
        listCSV.add(new ArrayList());
        //明細部項目ヘッダ
        listCSV.add(dto.getListCsvTableHeader());
        //明細データ
        for (Iterator ite = listEntity.iterator(); ite.hasNext();) {
            List listRow = new ArrayList();
            if(dto.isRenewalEntry()) {
                UIRenewalStaff entity = (UIRenewalStaff) ite.next();
                listRow.add(entity.getOnerCd());
                listRow.add(entity.getOnerNameKj());
                listRow.add(entity.getAreaDai());
                listRow.add(entity.getSibuName());
                listRow.add(entity.getMiseCd());
                listRow.add(entity.getMiseNameKj());
                listRow.add(trim(entity.getStaffLNameKj()) + "　" + trim(entity.getStaffFNameKj()));
                listRow.add(getCourseStatusName(entity.getCourseStatus()));
                listRow.add(dateFormatter.format(entity.getCompleCourseDt(), true));
            }else{
                UIMLStaff entity = (UIMLStaff) ite.next();
                listRow.add(entity.getOnerCd());
                listRow.add(entity.getOnerNameKj());
                listRow.add(entity.getAreaDai());
                listRow.add(entity.getSibuName());
                listRow.add(entity.getMiseCd());
                listRow.add(entity.getMiseNameKj());
                listRow.add(trim(entity.getStaffLNameKj()) + "　" + trim(entity.getStaffFNameKj()));
                listRow.add(getCourseStatusName(entity.getCourseStatus()));
                listRow.add(dateFormatter.format(entity.getCompleCourseDt(), true));
                if(dto.isTripEntry()) {
                	//出張研修の場合は点数を表示
                    listRow.add(entity.getComplePoint());
                }
            }
 
            
            listCSV.add(listRow);
        }
		return listCSV;
	}

    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {

        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("マスタライセンス研修修了登録 画面DTO");
        }
        MlCompletionregistDto dto = (MlCompletionregistDto) csvOutputDto;

        //入力値を取得
        String sibuCd = dto.getSibuCd();            //支部(取込)コード
        boolean optionFlg = dto.getOptionFlg();    //オプション
        
        //「エントリー者のみ表示」がOFFの時、支部選択必須チェック
        if(!optionFlg && (sibuCd == null || sibuCd.length() ==0)){
            String msg = "エントリー者のみをチェックしていない場合、支部選択";
            throw new NotNullException(msg, "sibuCd", 0);
        }
	}
    
    /**
     * 修了（予定）コース状況（名称）取得
     */
    private String getCourseStatusName(String courseStatus) {
        String name = "";
        if (COURSE_STATUS_MISYURYO.equals(courseStatus)) {
            name = "未修了";
        }
        if (COURSE_STATUS_SYURYO.equals(courseStatus)) {
            name = "修了";
        }
        if (COURSE_STATUS_YOTEI.equals(courseStatus)) {
            name = "予定";
        }
        return name;
    }
    
    /**
     * トリム処理
     */
    private String trim(String value) {
        if (isNull(value)) {
            return "";
        }
        else {
            return value.trim();
        }
    }

    /**
     * Nullチェック処理
     * @param str
     * @return
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    
    /**
     * 条件画面出力データ検索ロジック取得処理
     * @return editLogic を戻します。
     */
    public EditLogicImpl getEditLogic() {
        return editLogic;
    }
    /**
     * 条件画面出力データ検索ロジック設定処理
     * @param editLogic を設定。
     */
    public void setEditLogic(EditLogicImpl logic) {
        this.editLogic = logic;
    }
    
}
