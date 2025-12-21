package jp.co.isid.mos.bird.bizsupport.noinputoner.dto;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.noinputoner.entity.MSTSibuCategoryInfo;
import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 未入力オーナー一覧
 * @author xnkusama
 */
public class NoInputOnerDto implements CsvOutputDto {

    // 1ページの表示最大件数
    private static final int PAGE_MAX_SIZE = 100;
    // 表示月数
    private static final int NENGETU_DISPLAY_MONTH = 13;
    
    /* 検索条件 */
    // 企業コード
    private String condCompanyCd;
    // 対象年月
    private String condNengetu;
    // 未／到着
    private String condInput;
    // 支部
    private String condSibuCd;
    // 入力用 対象年月
    private String condNengetuInp;
    // 入力用 未／到着
    private String condInputInp;
    // 入力用 支部
    private String condSibuCdInp;
    // 支部選択プルダウン
    private List listCondSibu;
    // 店指定ページ移動
    private String condJumpMiseCd;
    // オーナー指定ページ移動
    private String condJumpOnerCd;
    // 対象年月リスト
    private List condListTargetYM;
    /* 検索結果 */
    private List listNoInputOner;
    private List listAllData;
    /* ページ遷移関連 */
    private int currentPageNumber;
    private int count;
    /* クローズ店を含むフラグ */
    private boolean closeMiseFlg;
    
    
	public String getCondInput() {
		return condInput;
	}
	public void setCondInput(String condInput) {
		this.condInput = condInput;
	}
	public String getCondNengetu() {
		return condNengetu;
	}
	public void setCondNengetu(String condNengetu) {
		this.condNengetu = condNengetu;
	}
	public String getCondSibuCd() {
		return condSibuCd;
	}
	public void setCondSibuCd(String condSibuCd) {
		this.condSibuCd = condSibuCd;
	}
	public List getListCondSibu() {
		return listCondSibu;
	}
	public void setListCondSibu(List listCondSibu) {
		this.listCondSibu = listCondSibu;
	}
	public List getListNoInputOner() {
		return listNoInputOner;
	}
	public void setListNoInputOner(List listNoInputOner) {
		this.listNoInputOner = listNoInputOner;
	}
    public int getListNoInputOnerSize() {
        List list = getListNoInputOner();
        if (list == null) {
            return 0; 
        }
        return list.size();
    }
    /**
     * １３ヶ月分のテーブルヘッダ用リスト
     */
    public List getListTableHeader() throws ApplicationException {
        List listTableHeader = new ArrayList();
        int miseHeaderCnt = 1;
        try {
            if (getCondNengetu() == null || "".equals(getCondNengetu().trim())) {
                return listTableHeader;
            }
            for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
                if (i != 0 && i == (5 + 6 * (miseHeaderCnt - 1))) {
                    listTableHeader.add("店舗名称");
                    miseHeaderCnt++;
                }
                listTableHeader.add(DateManager.getPrevMonth(getCondNengetu(), i));
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("PL未入力オーナー一覧 テーブルヘッダ作成");
        }
        return listTableHeader;
    }
    
    /**
     * CSV用項目ヘッダリスト
     * @return
     * @throws ApplicationException
     */
    public List getListCsvTableHeader() throws ApplicationException {
        List listTableHeader = new ArrayList();
        DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        
        listTableHeader.add("支部コード");
        listTableHeader.add("支部名称");
        listTableHeader.add("オーナーコード");
        listTableHeader.add("オーナー名称");
        listTableHeader.add("店コード");
        listTableHeader.add("店舗名称");
        listTableHeader.add("クローズ日");
        try {
            if (getCondNengetu() == null || "".equals(getCondNengetu().trim())) {
                return listTableHeader;
            }
            for (int i = 0; i < NENGETU_DISPLAY_MONTH; i++) {
                listTableHeader.add(dateFormatter.format(DateManager.getPrevMonth(getCondNengetuInp(), i), true));
//                listTableHeader.add(dateFormatter.format(DateManager.getPrevMonth(getCondNengetu(), i), true));
            }
        }
        catch (Exception ex) {
            throw new FtlSystemException("PL入力状況確認 テーブルヘッダ作成");
        }
        return listTableHeader;
    }
    
    /**
     * 検索条件：未／到着 の表示用
     * 2006/04/07 未入力とエラーに変更
     * @return
     */
    public String getCondInputName() {
        String name = "";
        if ("1".equals(getCondInputInp())) {
//        if ("1".equals(getCondInput())) {
            name = "エラー";
        }
        else if ("0".equals(getCondInputInp())) {
//        else if ("0".equals(getCondInput())) {
            name = "未入力";
        }
        return name;
    }
    
    /**
     * 検索条件：支部名称取得
     * @return
     */
    public String getCondSibuName() {
        String name = "";
        if (getCondSibuCdInp() == null || "".equals(getCondSibuCdInp().trim())) {
//        if (getCondSibuCd() == null || "".equals(getCondSibuCd().trim())) {
            return name;
        }
        for (Iterator ite = getListCondSibu().iterator(); ite.hasNext();) {
            MSTSibuCategoryInfo entity = (MSTSibuCategoryInfo) ite.next();
            if (getCondSibuCdInp().equals(entity.getSibuCd())) {
//            if (getCondSibuCd().equals(entity.getSibuCd())) {
                name = entity.getSibuName();
                break;
            }
        }
        return name;
    }
    public int getFirstPageNumber() {
        return 1;
    }
    public int getLastPageNumber() {
        return (getCount() == 0) ? 1 : (getCount() - 1) / getMaxPageCount() + 1;
    }
    public int getPreviousPageNumber() {
        return (isExistPreviousPage()) ? getCurrentPageNumber() - 1 : getCurrentPageNumber();
    }
    public int getNextPageNumber() {
        return (isExistNextPage()) ? getCurrentPageNumber() + 1 : getCurrentPageNumber();
    }
    public int getPageFirstRecordNumber() {
        return (getCurrentPageNumber() - 1) * getMaxPageCount();
    }
    public boolean isFirstPage() {
        return getCurrentPageNumber() == getFirstPageNumber();
    }
    public boolean isLastPage() {
        return getCurrentPageNumber() == getLastPageNumber();
    }
    public boolean isExistPreviousPage() {
        return getCurrentPageNumber() > getFirstPageNumber();
    }
    public boolean isExistNextPage() {
        return getCurrentPageNumber() < getLastPageNumber();
    }
    public int getCurrentPageNumber() {
        return currentPageNumber;
    }
    public void setCurrentPageNumber(int currentPageNumber) {
        if (currentPageNumber >= getFirstPageNumber() && currentPageNumber <= getLastPageNumber()) {
            this.currentPageNumber = currentPageNumber;
        }
    }
    public int getMaxPageCount() {
        return PAGE_MAX_SIZE;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        if (count >= 0) {
            this.count = count;
        }
    }
	public List getListAllData() {
		return listAllData;
	}
	public void setListAllData(List listAllData) {
		this.listAllData = listAllData;
	}
	public String getCondJumpMiseCd() {
		return condJumpMiseCd;
	}
	public void setCondJumpMiseCd(String condJumpMiseCd) {
		this.condJumpMiseCd = condJumpMiseCd;
	}
	public String getCondJumpOnerCd() {
		return condJumpOnerCd;
	}
	public void setCondJumpOnerCd(String condJumpOnerCd) {
		this.condJumpOnerCd = condJumpOnerCd;
	}
	public String getCondCompanyCd() {
        //TODO 2006/04/10企業コード固定 
        //      モス以外の企業コードが使われる場合は修正が必要
        //return condCompanyCd;
        return "00";
	}
	public void setCondCompanyCd(String condCompanyCd) {
		this.condCompanyCd = condCompanyCd;
	}
	public String getCondInputInp() {
		return condInputInp;
	}
	public void setCondInputInp(String condInputInp) {
		this.condInputInp = condInputInp;
	}
	public String getCondNengetuInp() {
		return condNengetuInp;
	}
	public void setCondNengetuInp(String condNengetuInp) {
		this.condNengetuInp = condNengetuInp;
	}
	public String getCondSibuCdInp() {
		return condSibuCdInp;
	}
	public void setCondSibuCdInp(String condSibuCdInp) {
		this.condSibuCdInp = condSibuCdInp;
	}
	public List getCondListTargetYM() {
		return condListTargetYM;
	}
	public void setCondListTargetYM(List condListTargetYM) {
		this.condListTargetYM = condListTargetYM;
	}
    public boolean isCloseMiseFlg() {
        return closeMiseFlg;
    }
    public void setCloseMiseFlg(boolean closeMiseFlg) {
        this.closeMiseFlg = closeMiseFlg;
    }
}