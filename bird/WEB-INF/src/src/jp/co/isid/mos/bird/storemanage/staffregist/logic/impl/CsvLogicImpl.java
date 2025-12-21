/*
 * 作成日: 2009/08/27
 *
 */
package jp.co.isid.mos.bird.storemanage.staffregist.logic.impl;

import java.util.ArrayList;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.staffregist.dao.UIStaffLicensInfoDao;
import jp.co.isid.mos.bird.storemanage.staffregist.dto.StaffDownloadDto;
import jp.co.isid.mos.bird.storemanage.staffregist.entity.UIStaffLicensInfo;

/**
 * CSVダウンロードロジック
 * 
 * 作成日:2009/08/27
 * @author xkinu
 *
 */
public class CsvLogicImpl implements CsvOutputLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BSM004L09";
    
    /** DAO【スタッフライセンス情報検索】*/
    private UIStaffLicensInfoDao staffRegistUIStaffLicensInfoDao;
    /**
     * ファイル名取得
     * FILE_NAME=MLSTAFF_ALL.csv
     */
	public String getFileName(CsvOutputDto csvOutputDto) {
        return "MLSTAFF_ALL.csv";
	}
	/**
	 * CSV出力処理
	 * 
     *  (非 Javadoc)
	 * @see jp.co.isid.mos.bird.framework.logic.CsvOutputLogic#getOutputData(jp.co.isid.mos.bird.framework.dto.CsvOutputDto)
	 */
	public List getOutputData(CsvOutputDto csvOutputDto)  {
        // DTO
		StaffDownloadDto dto = (StaffDownloadDto) csvOutputDto;
		String sysNendoTo = "";
		try {
			//当年度の最後の月の年月を取得します。
			sysNendoTo = DateManager.getNextYear(dto.getSysNendo(),1)+"03";
		}
		catch(Exception ex) {
            throw new FtlSystemException("年月リストを取得"
                    , "指定年["+dto.getSysNendo()+"]の翌年を計算する際のDateManager.getNextYearメソッド処理で例外が発生しました。"
                    , ex);
		}
		//１．ロジック【検索結果取得】を実行する。戻り値のList[[タブ別検索結果]]を取得します。
		List listSearchData = getStaffRegistUIStaffLicensInfoDao().select(dto.getSysNendo()+"04", sysNendoTo, dto.getCompanyCd());
		if(listSearchData.size() <1) {
			throw new NoResultException("");
		}
		//２．List[[CSV出力用リスト]]を生成します。
        List listCSV = new ArrayList();
        //３．条件項目ヘッダ作成
		addHeader(listCSV, dto);
        //４．明細部ヘッダー作成
		listCSV.add(getHeader());
        //５．明細部データ作成
		addDetailData(listCSV, listSearchData);
		//６．List[[CSV出力用リスト]]をリターンします。
		return listCSV;
	}
	/**
	 * 
	 * @param listCSV
	 * @param reqViewDto
	 */
	private void addHeader(List listCSV, StaffDownloadDto dto) {
        //List[[1行目]]を生成します。
	    List listHeader1Row = new ArrayList();
	    listHeader1Row.add("会社");
	    listHeader1Row.add(dto.getCompanyName());
        //List[[1行目]]を設定
        listCSV.add(listHeader1Row);
        
        List listHeader2Row = new ArrayList();
	    listHeader2Row.add("対象条件");
	    listHeader2Row.add("全て");
        //List[[2行目]]を設定
        listCSV.add(listHeader2Row);
        
        //空白行
        listCSV.add(new ArrayList());
	}
	/**;
	 * 支部一覧用データ項目名称格納処理
	 * 
	 * @param reqViewDto
	 * @param listTabSearchData
	 */
	private List getHeader() {
		List listHeader = new ArrayList(0);
			listHeader.add("オーナーコード");
			listHeader.add("オーナー名称");
			listHeader.add("支部取込コード");
			listHeader.add("支部取込名称");
			listHeader.add("店コード");
			listHeader.add("店名称");
			listHeader.add("スタッフID");
			listHeader.add("氏名");
			listHeader.add("性別");
			listHeader.add("職位");
			listHeader.add("活動状況");
			listHeader.add("ライセンス番号");
			listHeader.add("取得年月");
			listHeader.add("当年度更新研修");
			listHeader.add("初回受験年度");
			listHeader.add("初回受験回");
			listHeader.add("総合結果");
			listHeader.add("前提研修");
			listHeader.add("備考");
		return listHeader;
	}
	/**
	 * データ部格納処理
	 * 
	 * @param listCSV
	 * @param listSearchData
	 */
    private void addDetailData(List listCSV, List listSearchData) {
		for(int i=0; i<listSearchData.size(); i++) {
			UIStaffLicensInfo entity = (UIStaffLicensInfo)listSearchData.get(i);
			List rowData = new ArrayList(0);
			rowData.add(changeBlank(entity.getOnerCd()));
			rowData.add(changeBlank(entity.getOnerNameKj()));
			rowData.add(changeBlank(entity.getAreaDai()));
			rowData.add(changeBlank(entity.getSibuName()));
			rowData.add(changeBlank(entity.getMiseCd1()));
			rowData.add(changeBlank(entity.getMiseNameKj()));
			rowData.add(changeBlank(entity.getStaffId()));
			rowData.add(changeBlank(entity.getStaffName()));
			rowData.add(changeBlank(entity.getSex()));
			rowData.add(changeBlank(entity.getJob()));
			rowData.add(changeBlank(entity.getSituation()));
			rowData.add(changeBlank(entity.getLicenseNo()));
			rowData.add(changeBlank(entity.getLicenseDt()));
			rowData.add(changeBlank(entity.getKCourseStatus()));
			rowData.add(changeBlank(entity.getFirstLicenseYear()));
			rowData.add(changeBlank(entity.getFirstLicenseKai()));
			rowData.add(changeBlank(entity.getTotalResult()));
			rowData.add(changeBlank(entity.getZCourseStatus()));
			rowData.add(changeBlank(entity.getNote()));
			//1行分のデータを格納します。
			listCSV.add(rowData);
		}
    }
    /**
     * 妥当性チェック
     */
	public void validate(CsvOutputDto csvOutputDto) {
        // 画面DTO
        if (csvOutputDto == null) {
            throw new NotNullException("スタッフ情報DTO");
        }

	}
	/**
	 * null値の場合ブランクを返す。
	 * @param value
	 * @return
	 */
	public static String changeBlank(String value) {
		if(value == null) {
			return "";
		}
		return value;
	}
	/**
	 * 
	 * @return
	 */
	public UIStaffLicensInfoDao getStaffRegistUIStaffLicensInfoDao() {
		return staffRegistUIStaffLicensInfoDao;
	}
	/**
	 * 
	 * @param dao
	 */
	public void setStaffRegistUIStaffLicensInfoDao(
			UIStaffLicensInfoDao dao) {
		this.staffRegistUIStaffLicensInfoDao = dao;
	}
}
